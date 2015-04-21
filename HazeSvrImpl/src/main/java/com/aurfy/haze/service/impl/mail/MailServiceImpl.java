package com.aurfy.haze.service.impl.mail;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.aurfy.haze.conf.HazeDefaultConfig;
import com.aurfy.haze.service.api.mail.MailService;
import com.aurfy.haze.service.bean.mail.MailBean;
import com.aurfy.haze.service.conf.ServiceImplConstant.AOP_NAME;

@Service(AOP_NAME.MAIL_SERVICE)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MailServiceImpl implements MailService {
	
	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
	
//	发件人
	private static final String sender = HazeDefaultConfig.getMailProps().getProperty("sender");
//	发件人邮箱密码
	private static final String sendPSW = HazeDefaultConfig.getMailProps().getProperty("password");
//	发件人邮箱smtpHost
	private static final String smtpHost = HazeDefaultConfig.getMailProps().getProperty("smtpHost");
//	是否授权(如果发件邮箱为企业邮箱建议授权，默认为true)
	private static final String authorize = HazeDefaultConfig.getMailProps().getProperty("authorize");
//	发送端口
	private static final String sendPort = HazeDefaultConfig.getMailProps().getProperty("sendPort");
	
	@Override
	public boolean sendMail(MailBean mailBean) {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", smtpHost);
		properties.put("mail.smtp.auth", authorize);
		properties.put("mail.smtp.port", sendPort);
        Session session = Session.getInstance(properties);
        MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(sender));
			message.setSentDate(new Date());
			//收件人
			List<String> addressees = mailBean.getAddressees();
			if(addressees != null && addressees.size() > 0) {
				for(String addresse:addressees) {
					message.setRecipient(RecipientType.TO, new InternetAddress(addresse));
				}
			}
			//抄送
			List<String> ccs = mailBean.getCc();
			if(ccs != null && ccs.size() > 0) {
				for(String cc:ccs) {
					message.addRecipient(RecipientType.CC, new InternetAddress(cc));
				}
			}
			//密件抄送
			List<String> bccs = mailBean.getBcc();
			if(bccs != null && bccs.size() > 0) {
				for(String bcc:bccs) {
					message.addRecipient(RecipientType.BCC, new InternetAddress(bcc));
				}
			}
			message.setSubject(MimeUtility.encodeText(mailBean.getTitle(), "utf-8", "B"));
			// 设置发送内容
            Multipart multipart = new MimeMultipart();
            MimeBodyPart contentPart = new MimeBodyPart();
            contentPart.setText(mailBean.getText());
            multipart.addBodyPart(contentPart);
            //设置附件
            List<File> attachments = mailBean.getAttachments();
            if(attachments != null && attachments.size() > 0){
                for(int i = 0; i < attachments.size(); i++){
                    MimeBodyPart attachmentPart = new MimeBodyPart();
                    FileDataSource source = new FileDataSource(attachments.get(i));
                    attachmentPart.setDataHandler(new DataHandler(source));
                    attachmentPart.setFileName(MimeUtility.encodeWord(attachments.get(i).getName(), "utf-8", null));
                    multipart.addBodyPart(attachmentPart);
                }
            }
            message.setContent(multipart);
			//登陆服务器
			Transport transport = session.getTransport("smtp");
			transport.connect(smtpHost, sender, sendPSW);
			transport.sendMessage(message, message.getRecipients(RecipientType.TO));
			if(ccs != null && ccs.size() > 0) {
				transport.sendMessage(message, message.getRecipients(RecipientType.CC));
			}
			if(bccs != null && bccs.size() > 0) {
				transport.sendMessage(message, message.getRecipients(RecipientType.BCC));
			}
			transport.close();
			
		} catch (AddressException e) {
			logger.error(e.getMessage());
			return false;
		} catch (NoSuchProviderException e) {
			logger.error(e.getMessage());
			return false;
		} catch (MessagingException e) {
			logger.error(e.getMessage());
			return false;
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}
}
