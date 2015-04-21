package com.aurfy.haze.web.controller.payment;

import static com.aurfy.haze.utils.StringUtils.formatMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.aurfy.haze.core.model.payment.PayCredential;
import com.aurfy.haze.service.api.CRUDService;
import com.aurfy.haze.service.api.payment.PaymentService;
import com.aurfy.haze.service.bean.CRUDBean;
import com.aurfy.haze.service.bean.bank.ProcessReference;
import com.aurfy.haze.service.bean.infra.mer.MerchantBean;
import com.aurfy.haze.service.bean.txn.MerTxnBean;
import com.aurfy.haze.service.client.ServiceClient;
import com.aurfy.haze.service.exceptions.DuplicateException;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.utils.EncodingUtils;
import com.aurfy.haze.web.controller.PGWConf;
import com.aurfy.haze.web.converter.PaymentConverter;
import com.aurfy.haze.web.exceptions.ConflictException;
import com.aurfy.haze.web.exceptions.UnprocessableException;
import com.aurfy.haze.web.exceptions.WebException;
import com.aurfy.haze.web.helper.PGWUtils;
import com.aurfy.haze.web.vo.txn.ExpressPayReqVO;
import com.aurfy.haze.web.vo.txn.ExpressPayRespVO;

/**
 * 1.当银行卡等敏感信息需要加密时，比如用RSA算法，那么一般让商户使用Aurfy的公钥进行加密，Aurfy再用对应私钥进行解密。<br />
 * 2.签名算法可以用MD5，RSA，主要是为了保证信息不被篡改。<br />
 * 
 * 
 * @author rocket
 *
 */
@RestController
@RequestMapping(value = "/pay")
public class PaymentController {

	private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

	@RequestMapping(value = "/generateSignature", method = RequestMethod.POST)
	public String generateSignature(HttpServletRequest request,
			@ModelAttribute("payReq") @Validated ExpressPayReqVO payReq, BindingResult errors) {
		List<String> columns = new ArrayList<String>();
		columns.add(PGWConf.BROWSER_NOFIFY_URL);
		columns.add(PGWConf.SERVER_NOTIFY_URL);
		payReq = PGWUtils.encodeUrl(payReq, EncodingUtils.UTF_8.displayName(), columns);
		return PGWUtils.generateSignature(payReq, payReq.getMerCode(), payReq.getSignMethod());
	}

	/**
	 * 
	 * payment doExpressPay 本接口交易类型固定，为PURCHASE
	 * 
	 * @param request
	 * @param bank
	 * @return
	 */
	@RequestMapping(value = "/doExpressPay", method = RequestMethod.POST)
	public ExpressPayRespVO doExpressPay(HttpServletRequest request,
			@ModelAttribute("payReq") @Validated ExpressPayReqVO payReq, BindingResult errors) throws WebException {
		try {
			if (errors.hasErrors()) {
				return null;
			}

			List<String> columns = new ArrayList<String>();
			columns.add(PGWConf.BROWSER_NOFIFY_URL);
			columns.add(PGWConf.SERVER_NOTIFY_URL);
			payReq = PGWUtils.encodeUrl(payReq, EncodingUtils.UTF_8.toString(), columns);
			String signature = PGWUtils.generateSignature(payReq, payReq.getMerCode(), payReq.getSignMethod());
			if (!signature.equals(payReq.getSignature())) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("msg", "签名验证失败");
				new ModelAndView("index", "map", map);
			}

			PaymentService payService = ServiceClient.createPaymentService();
			CRUDService crudService = ServiceClient.createCRUDService();

			// get merchant
			MerchantBean mer = new MerchantBean();
			mer.setMerchantCode(payReq.getMerCode());
			List<CRUDBean> list = crudService.retrieveBy(mer.getClass(), mer, 0, 0).getPageData();
			if (list == null || list.size() == 0) {
				throw new RuntimeException("Not found this merchant in system!");
			}
			mer = (MerchantBean) list.get(0);
			MerTxnBean merTxnBean = PaymentConverter.VO2MerBean(payReq, mer.getID());

			logger.debug("1.save merchant transaction.");
			merTxnBean = payService.saveMerTxn(merTxnBean);
			PayCredential payCredential = PaymentConverter.VO2PayCredential(payReq);// payCredential是持卡人信息

			logger.debug("2.do express pay by merchant transaction info.");
			ProcessReference processReference = payService.doExpressPay(merTxnBean, payCredential);

			ExpressPayRespVO payResp = new ExpressPayRespVO();
			payResp.setProtocolVer("");
			payResp.setCharsetEncoding("");
			payResp.setTransType(merTxnBean.getMerOrder().getTxnType().toString());
			payResp.setRefId(processReference.getProcessId());
			payResp.setMerCode(merTxnBean.getMerOrder().getMerRef().getMerCode());
			payResp.setOrderId(payReq.getOrderId());
			payResp.setSignMethod(payReq.getSignMethod());

			String signature2 = PGWUtils.generateSignature(payResp, payResp.getMerCode(), payReq.getSignMethod());
			payResp.setSignature(signature2);
			return payResp;

		} catch (DuplicateException dpe) {
			final String msg = formatMessage("duplicate transaction not allowed");
			logger.error(msg, dpe);
			throw new ConflictException(msg, dpe);
		} catch (ServiceException se) {
			final String msg = formatMessage("DoExpressPay failed or others");
			logger.error(msg, se);
			throw new UnprocessableException(msg, se);
		} catch (Exception ex) {
			final String msg = formatMessage("default exception");
			logger.error(msg, ex);
			throw new WebException(msg, ex);
		}
	}
}