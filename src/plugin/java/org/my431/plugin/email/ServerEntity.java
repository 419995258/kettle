package org.my431.plugin.email;

/**
 * 邮件服务器配置文件
 * 创建人：ice
 * 创建日期:2011-6-9
 * </pre>
 */
public class ServerEntity {
	private String smtp;
	private String address;
	private String password;

	public ServerEntity() {
	}
	
	public ServerEntity(String smtp,String address,String password) {
		this.smtp = smtp;
		this.address = address;
		this.password = password;
	}

	public String getSmtp() {
		return smtp;
	}

	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
