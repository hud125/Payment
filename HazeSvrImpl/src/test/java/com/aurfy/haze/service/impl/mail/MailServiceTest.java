package com.aurfy.haze.service.impl.mail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.aurfy.haze.service.api.mail.MailService;
import com.aurfy.haze.service.bean.mail.MailBean;
import com.aurfy.haze.service.impl.ServiceUnitTest;

public class MailServiceTest extends ServiceUnitTest{

	
	@Autowired
	private MailService mailService;
	
	@Test
	public void testSendEmail() {
		//是否选择默认方式发送
		MailBean mailBean = new MailBean();
		//接受邮箱
		List<String> addList = new ArrayList<String>();
		addList.add("15501501302@163.com");
		mailBean.setAddressees(addList);
		//抄送邮箱
		List<String> ccList = new ArrayList<String>();
		ccList.add("414039913@qq.com");
		mailBean.setCc(ccList);
		//密件抄送
//		mailBean.setBcc();
		//邮件标题
		mailBean.setTitle("test test");
		//邮件正文
		mailBean.setText("hello word");
		//附件
//		List<File> files = new ArrayList<File>();
		//添加附件
//		files.add(new File(""));
//		mailBean.setAttachments(files);
		//发送
		Assert.assertTrue(mailService.sendMail(mailBean));
	}
	
}
