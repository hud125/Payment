package com.unionpay.upop.sdk.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.unionpay.upop.sdk.PayRequest;
import com.unionpay.upop.sdk.SdkConf;
import com.unionpay.upop.sdk.common.BackStageUPSupport;
import com.unionpay.upop.sdk.common.FrontUPSupport;
import com.unionpay.upop.sdk.enums.Service;

/**
 * 名称：支付工具类 功能：工具类，可以生成付款表单等 类属性：公共类 版本：1.0 日期：2011-03-11 作者：中国银联UPOP团队 版权：中国银联
 * 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。该代码仅供参考。
 * */
public class PayUtils {

	public static String trimToEmpty(String s) {
		return s == null ? "" : s.trim();
	}

	public static boolean isNotBlank(String s) {
		return !isBlank(s);
	}

	public static boolean isBlank(String s) {
		return (s == null || s.trim().length() == 0);
	}

	public static String getCurrentTime() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	/**
	 * <p>
	 * Capitalizes a String changing the first letter to title case as per {@link Character#toTitleCase(char)}. No other
	 * letters are changed.
	 * </p>
	 *
	 * A <code>null</code> input String returns <code>null</code>.</p>
	 *
	 * <pre>
	 * capitalize(null)  = null
	 * capitalize("")    = ""
	 * capitalize("cat") = "Cat"
	 * capitalize("cAt") = "CAt"
	 * </pre>
	 *
	 * @param str
	 *            the String to capitalize, may be null
	 * @return the capitalized String, <code>null</code> if null String input
	 */
	public static String capitalize(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuffer(strLen).append(Character.toTitleCase(str.charAt(0))).append(str.substring(1))
				.toString();
	}

	public static String getProperty(Object instance, String propertyName) {
		String value = null;
		try {
			String methodName = "get" + PayUtils.capitalize(propertyName);
			Method method = instance.getClass().getMethod(methodName);
			value = String.class.cast(method.invoke(instance));
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return value;
	}

	public static String getPropFilename(Class<?> clazz) {
		return clazz.getSimpleName() + ".properties";
	}

	public static PayRequest mockRequest(Class<?> clazz) {
		return SerializationUtils.readObject(getPropFilename(clazz), PayRequest.class);
	}

	public static FrontUPSupport getFrontUpSupport(Service service) {
		FrontUPSupport support = null;
		try {
			support = FrontUPSupport.class.cast(service.getImplClassName().newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return support;
	}

	public static BackStageUPSupport getBackStageUpSupport(Service service) {
		BackStageUPSupport support = null;
		try {
			support = BackStageUPSupport.class.cast(service.getImplClassName().newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return support;
	}

	public static PropLoader getPropLoader(Class<?> clazz) {
		return PropManager.getInstance(getPropFilename(clazz));
	}

	/**
	 * 生成发送到银联的数据表单
	 * 
	 * @param 请求参数数组
	 * @param 签名方式
	 * @return
	 */
	public static String createPayHtml(PayRequest payRequest) {
		Map<String, String> map = createRequestMap(SdkConf.reqVo, payRequest);

		logMap("Data send to upop " + Service.FRONT_PAY.getUrl() + ": ", map, ',');

		String payForm = generateAutoSubmitForm(Service.FRONT_PAY.getUrl(), map);

		return payForm;
	}

	private static void logMap(String info, Map<String, String> map, char s) {
		System.out.println(info);
		System.out.println("    [" + joinMapValue(map, s) + "]");
	}

	public static String createPostDataForBackTrans(String[] keyVo, PayRequest payRequest) {
		Map<String, String> map = createRequestMap(keyVo, payRequest);
		return joinMapValueBySpecial(map, '&', payRequest.getCharset());
	}

	private static Map<String, String> createRequestMap(String[] keyVo, PayRequest payRequest) {
		Map<String, String> map = new TreeMap<String, String>();
		for (int i = 0; i < keyVo.length; i++) {
			map.put(keyVo[i], getProperty(payRequest, keyVo[i]));
		}
		map.put("signature",
				signMap(map, payRequest.getSignMethod(), payRequest.getSecurityKey(), payRequest.getCharset()));
		map.put("signMethod", payRequest.getSignMethod());
		return map;
	}

	/**
	 * 生成加密钥
	 * 
	 * @param map
	 * @param secretKey
	 *            商城密钥
	 * @return
	 */
	private static String signMap(Map<String, String> map, String signMethod, String securityKey, String charset) {
		String joinString = joinMapValue(map, '&');
		System.out.println(">>>signMethod: " + signMethod);
		System.out.println(">>>signData: " + joinString);

		String signature = null;

		if ("RSA".equalsIgnoreCase(signMethod)) {
			String signData = sha1(joinString, charset);
			signature = signWithRSA(signData);
		} else {
			String strBeforeMd5 = joinString + "&" + md5(securityKey, charset);

			System.out.println(strBeforeMd5);
			signature = md5(strBeforeMd5, charset);
		}

		return signature;
	}

	private static String signWithRSA(String signData) {
		try {
			// System.out.println("sign data: " + signData);

			String certFile = PropManager.getMerInstance().getProperty("mer_cert_file");
			String passwd = PropManager.getMerInstance().getProperty("mer_cert_passwd");
			PrivateKey privateKey = getPrivateKey(certFile, passwd);

			Signature dsa = Signature.getInstance("SHA1WithRSA");
			dsa.initSign(privateKey);
			dsa.update(signData.getBytes());
			byte[] signature = dsa.sign();
			BASE64Encoder base64Encoder = new BASE64Encoder();
			return base64Encoder.encode(signature);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
		}
	}

	private static boolean verifyWithRSA(String signData, String signature) {

		String publicKeyPath = PropManager.getMerInstance().getProperty("cup_cert_file");
		ObjectInputStream pubObjectIs = null;
		try {
			InputStream is = PayUtils.class.getResourceAsStream("/" + publicKeyPath);
			CertificateFactory factory = CertificateFactory.getInstance("X.509");

			Certificate cert = factory.generateCertificate(is);
			PublicKey publicKey = cert.getPublicKey();
			Signature signCheck = Signature.getInstance("SHA1WithRSA");
			signCheck.initVerify(publicKey);
			signCheck.update(signData.getBytes());
			BASE64Decoder base64Decoder = new BASE64Decoder();
			return signCheck.verify(base64Decoder.decodeBuffer(signature));
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (pubObjectIs != null) {
				try {
					pubObjectIs.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	/**
	 * 验证签名
	 * 
	 * @return
	 */
	public static boolean verifySignature(Map<String, String> map, String securityKey, String charset) {
		String signature = map.get("signature");
		String signMethod = map.get("signMethod");

		if (isBlank(signature)) {
			return false;
		}

		try {
			// remove signature and signMethod for verification
			map.remove("signature");
			map.remove("signMethod");
			if ("RSA".equalsIgnoreCase(signMethod)) {
				String joinString = joinMapValue(map, '&');
				String signData = sha1(joinString, charset);
				boolean result = verifyWithRSA(signData, signature);

				System.out.println(">>>signMethod: " + signMethod);
				System.out.println(">>sha1: " + signData);
				System.out.println(">>>verify data: " + joinMapValue(map, '&'));
				System.out.println(">>>verify signature: " + result);
				return result;
			} else {
				String signData = joinMapValue(map, '&') + "&" + md5(securityKey, charset);

				String sig = md5(signData, charset);

				boolean result = signature.equals(sig);

				System.out.println(">>>" + signMethod);
				System.out.println(">>>" + joinMapValue(map, '&') + "&" + md5(securityKey, charset));
				System.out.println(">>>" + result);
				return result;
			}
		} finally {
			// restore signature and signMethod.
			map.put("signature", signature);
			map.put("signMethod", signMethod);
		}
	}

	public static Map<String, String> getResponse(String str) {
		String regex = "(.*?cupReserved\\=)(\\{[^}]+\\})(.*)";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);

		String reserved = "";
		if (matcher.find()) {
			reserved = matcher.group(2);
		}

		String result = str.replaceFirst(regex, "$1$3");
		Map<String, String> responseMap = splitMapString(result, "&");
		responseMap.put("cupReserved", reserved);

		return responseMap;
	}

	/**
	 * Splits map string. E.g., mapString is "a=1;b=2", delimeter is ";" Result is: Map: {key=a, value=1, key=b,
	 * value=2}
	 * 
	 * @param mapString
	 * @param delimeter
	 * @return
	 */
	public static Map<String, String> splitMapString(String mapString, String delimeter) {
		String[] nameValuePairs = mapString.split(Pattern.quote(delimeter));
		Map<String, String> orderInfo = new TreeMap<String, String>();
		for (String nameValuePair : nameValuePairs) {
			int index = nameValuePair.indexOf("=");
			String name = nameValuePair.substring(0, index);
			String value = nameValuePair.substring(index + 1);
			orderInfo.put(name, value);
		}
		return orderInfo;
	}

	public static String joinMapValue(Map<String, String> map, char connector) {
		StringBuffer b = new StringBuffer();
		boolean first = true;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (!first) {
				b.append(connector);
			}
			first = false;

			b.append(entry.getKey());
			b.append('=');
			if (entry.getValue() != null) {
				b.append(trimToEmpty(entry.getValue()));
			}
		}
		return b.toString();
	}

	public static String joinMapValueBySpecial(Map<String, String> map, char connector, String charset) {
		StringBuffer b = new StringBuffer();
		for (Map.Entry<String, String> entry : map.entrySet()) {

			b.append(entry.getKey());
			b.append('=');
			if (entry.getValue() != null) {
				try {
					b.append(java.net.URLEncoder.encode(trimToEmpty(entry.getValue()), charset));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			b.append(connector);
		}
		return b.toString();
	}

	/**
	 * get the md5 hash of a string
	 * 
	 * @param str
	 * @return
	 */
	private static String md5(String str, String encoding) {

		if (str == null) {
			return null;
		}

		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(str.getBytes(encoding));
		} catch (NoSuchAlgorithmException e) {

			return str;
		} catch (UnsupportedEncodingException e) {
			return str;
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}

	/**
	 * 查询方法
	 * 
	 * @param strURL
	 * @param req
	 * @return
	 */
	/*
	 * public String doPostQueryCmd(String strURL, String[] valueVo, String[] keyVo) {
	 * 
	 * 
	 * PostMethod post = null; try { post = (PostMethod) new UTF8PostMethod(strURL); //URL uRL = new URL(strURL);
	 * System.out.println("URL:" + strURL); post.setContentChunked(true); //post.setPath(uRL.getPath());
	 * 
	 * // Get HTTP client HttpClient httpclient = new HttpClient();
	 * 
	 * NameValuePair[] params = new NameValuePair[keyVo.length]; for (int i = 0; i < keyVo.length; i++) { params[i] =
	 * new NameValuePair(keyVo[i], valueVo[i]); }
	 * 
	 * //httpclient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,QuickPayConf.charset);
	 * 
	 * post.setRequestBody(params);
	 * 
	 * // 设置超时时间 httpclient.setTimeout(30000); //httpclient.getHostConfiguration().setHost(uRL.getHost(),
	 * uRL.getPort());
	 * 
	 * int result = httpclient.executeMethod(post);
	 * 
	 * post.getRequestCharSet(); byte[] resultInputByte; if (result == 200) { resultInputByte = post.getResponseBody();
	 * return new String(resultInputByte,QuickPayConf.charset); } else { System.out.println("返回错误"); } } catch
	 * (Exception ex) { System.out.println(ex); } finally { post.releaseConnection(); } return null; }
	 */

	/**
	 * 查询方法
	 * 
	 * @param strURL
	 * @param req
	 * @return
	 */
	public static String doPostQueryCmd(String strURL, String req, String charset) {

		System.out.println(">>>url : " + strURL);

		String result = null;
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			URL url = new URL(strURL);
			URLConnection con = url.openConnection();
			if (con instanceof HttpsURLConnection) {
				((HttpsURLConnection) con).setHostnameVerifier(new HostnameVerifier() {
					@Override
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				});
			}
			con.setUseCaches(false);
			con.setDoInput(true);
			con.setDoOutput(true);
			out = new BufferedOutputStream(con.getOutputStream());
			byte outBuf[] = req.getBytes(charset);
			out.write(outBuf);
			out.close();
			System.out.println(">>>send data : " + req);
			System.out.println(">>>data send done!");
			in = new BufferedInputStream(con.getInputStream());
			result = ReadByteStream(in, charset);
			System.out.println(">>>receive data : " + result);
		} catch (Exception ex) {
			System.out.print(ex);
			return "";
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
		if (result == null)
			return "";
		else
			return result;
	}

	private static String ReadByteStream(BufferedInputStream in, String charset) throws IOException {
		LinkedList<Mybuf> bufList = new LinkedList<Mybuf>();
		int size = 0;
		byte buf[];
		do {
			buf = new byte[128];
			int num = in.read(buf);
			if (num == -1)
				break;
			size += num;
			bufList.add(new Mybuf(buf, num));
		} while (true);
		buf = new byte[size];
		int pos = 0;
		for (ListIterator<Mybuf> p = bufList.listIterator(); p.hasNext();) {
			Mybuf b = p.next();
			for (int i = 0; i < b.size;) {
				buf[pos] = b.buf[i];
				i++;
				pos++;
			}

		}

		return new String(buf, charset);
	}

	/**
	 * 生成自动提交到银联系统的表单数据
	 * 
	 * @param 提交的银联系统地址
	 * @param 表单中请求数据map
	 * @return
	 */
	private static String generateAutoSubmitForm(String actionUrl, Map<String, String> paramMap) {
		StringBuilder html = new StringBuilder();
		html.append("<script language=\"javascript\">window.onload=function(){document.pay_form.submit();}</script>\n");
		html.append("<form id=\"pay_form\" name=\"pay_form\" action=\"").append(actionUrl)
				.append("\" method=\"post\">\n");

		for (String key : paramMap.keySet()) {
			String value = paramMap.get(key);
			value = (value == null) ? "" : value;
			html.append("<input type=\"hidden\" name=\"" + key + "\" id=\"" + key + "\" value=\"" + value + "\">\n");
		}
		html.append("</form>\n");
		return html.toString();
	}

	private static PrivateKey getPrivateKey(String certName, String passwd) {

		try {
			KeyStore ks = KeyStore.getInstance("pkcs12");
			InputStream is = PayUtils.class.getResourceAsStream("/" + certName);

			char[] nPassword = null;
			if ((passwd == null) || passwd.trim().equals("")) {
				nPassword = null;
			} else {
				nPassword = passwd.toCharArray();
			}
			ks.load(is, nPassword);
			is.close();

			Enumeration<String> e = ks.aliases();
			String keyAlias = null;
			if (e.hasMoreElements()) // we are reading just one certificate.
			{
				keyAlias = (String) e.nextElement();
			}

			PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
			// Certificate cert = ks.getCertificate(keyAlias);

			return prikey;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String sha1(String str, String encoding) {

		if (str == null) {
			return null;
		}

		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("SHA-1");
			messageDigest.reset();
			messageDigest.update(str.getBytes(encoding));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer strBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				strBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			else
				strBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return strBuff.toString();
	}

	public static void main(String[] args) {
		String data = "charset=UTF-8&cupReserved={cardNumber=621234************1&cardType=02&cupCertIndex=41&merCertIndex=11}&exchangeDate=&exchangeRate=456&merAbbr=中航服ceshi&merId=301110060109500&orderAmount=10000&orderCurrency=156&orderNumber=4562499921144562&qid=111111111111111111111&respCode=00&respMsg=Success!&respTime=20110823174418&settleAmount=10000&settleCurrency=156&settleDate=0420&traceNumber=009768&traceTime=0823174043&transType=01&version=1.0.0";
		String signature = "gFGgHrQJtzMF2Fw4XJzTxINmBHsIzHS1ifwQh2LjLc/0Y5DurhBohQZJvGpb8HK0yT4/9zzfw6pv8JzhF6WqAwWLjiCKgtpxtWUnZ8pIdWUMK19G0hZZI8Caz+MKXWnG9R8/hVtJPHx9iWO1uvhBGoubBvNxG4RPl3hntz92VC25XO/MArYlRQZw2PFeVPVGzFK9XQa9DT/bmunZ5q+CTU6ehAJjZWmbNdsiWoyK9st9W1FDao5mOG4OSby2Z+9xoPBIB8xa7HxUtYp3jcRMJYFkEHTfOrcHeKBpXFaB+1Ev+g5vag+kAE2uuXE55lFyW3kldY4EqjtbaA0XKbJG1g==";

		if (PayUtils.verifyWithRSA(data, signature)) {
			System.out.println("signature true");
		} else {
			System.out.println("false");
		}
	}
}

class Mybuf {

	public byte buf[];
	public int size;

	public Mybuf(byte b[], int s) {
		buf = b;
		size = s;
	}
}
