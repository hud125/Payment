package com.aurfy.haze.service.bean.mail;

import java.io.File;
import java.util.List;

import com.aurfy.haze.service.bean.Bean;

/**
 * Email body
 * 
 * @author gqi
 *
 */
public class MailBean implements Bean {

	private List<String> addressees;

	private List<String> cc;

	private List<String> bcc;

	private String title;

	private String text;

	private List<File> attachments;

	/**
	 * @return addressees 收件人
	 */
	public List<String> getAddressees() {
		return addressees;
	}

	/**
	 * @param addressees
	 *            收件人
	 */
	public void setAddressees(List<String> addressees) {
		this.addressees = addressees;
	}

	/**
	 * @return Carbon copy 抄送
	 */
	public List<String> getCc() {
		return cc;
	}

	/**
	 * @param cc
	 *            Carbon copy 抄送
	 */
	public void setCc(List<String> cc) {
		this.cc = cc;
	}

	/**
	 * @return 密件抄送
	 */
	public List<String> getBcc() {
		return bcc;
	}

	/**
	 */
	public void setBcc(List<String> bcc) {
		this.bcc = bcc;
	}

	/**
	 * @return title 标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return text 正文
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            正文
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return attachment 附件
	 */
	public List<File> getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments
	 *            附件
	 */
	public void setAttachments(List<File> attachments) {
		this.attachments = attachments;
	}

}
