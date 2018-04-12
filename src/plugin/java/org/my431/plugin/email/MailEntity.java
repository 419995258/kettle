package org.my431.plugin.email;

import java.util.List;

/**
 * 
 * 创建人：ice
 * 创建日期:2011-6-9
 */
public class MailEntity {

	// 发送邮件标题
	private String title;
	// 邮件内容
	private String content;
	// 发送地址
	private List<String> sendAddress;
	// 抄送地址
	private List<String> tosAddress;
	// 所加附件
	private List<String> attachments;

	public MailEntity() {
	}
	
	public MailEntity(String title,String content,List<String> sendAddress) {
		this.title = title;
		this.content = content;
		this.sendAddress = sendAddress;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = new TemplateForMail().mailTemp(content);
	}

	public List<String> getSendAddress() {
		return sendAddress;
	}

	public void setSendAddress(List<String> sendAddress) {
		this.sendAddress = sendAddress;
	}

	public List<String> getTosAddress() {
		return tosAddress;
	}

	public void setTosAddress(List<String> tosAddress) {
		this.tosAddress = tosAddress;
	}

	public List<String> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}

}
