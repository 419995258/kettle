package org.my431.plugin.email;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 创建人：ice
 * 创建日期:2011-6-9
 */
public class MailPlugin {

	private static final Log log = LogFactory.getLog(MailPlugin.class);
	public MailPlugin() {
	}

	

	/**
	 * 
	 * @param mailObj
	 * @return
	 */
	public static synchronized boolean sendMail(ServerEntity server,
			MailEntity mailObj) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		if (null == server.getSmtp() || null == server.getAddress()
				|| server.getPassword() == null) {
			log.info("发送邮件服务器地址或密码有误");
			return false;
		} else {
			props.put("mail.smtp.host", server.getSmtp());
		}
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		try {
			Multipart mm = new MimeMultipart();
			message.setFrom(new InternetAddress(server.getAddress()));
			if (mailObj.getSendAddress() != null) {
				for (String to : mailObj.getSendAddress()) {
					message.addRecipient(Message.RecipientType.TO,
							new InternetAddress(to));
				}
			} else {
				log.info("请输入收件人地址后再发送！");
			}
			message.setSubject(mailObj.getTitle());
			message.setSentDate(new Date());
			BodyPart mdp = new MimeBodyPart();
			mdp.setContent(mailObj.getContent(), "text/html;charset=gb2312");
			mm.addBodyPart(mdp);
			if (null != mailObj.getAttachments()) {
				for (String path : mailObj.getAttachments()) {
					BodyPart mdps = new MimeBodyPart();
					FileDataSource fds = new FileDataSource(path);
					DataHandler dh = new DataHandler(fds);
					int separatorIndex = path.lastIndexOf(File.separator);
					if (separatorIndex == -1) {
						mdps.setFileName(path);
					} else {
						mdps.setFileName(new String(path.substring(
								separatorIndex).getBytes(), "utf-8"));
					}
					mdps.setDataHandler(dh);
					mm.addBodyPart(mdps);
				}
			}
			if (mailObj.getTosAddress() != null) {
				for (String c : mailObj.getTosAddress()) {
					message.setRecipients(Message.RecipientType.CC,
							InternetAddress.parse(c));
				}
			}
			message.setContent(mm);
			message.saveChanges();
			Transport transport = session.getTransport("smtp");
			transport.connect(server.getSmtp(), server.getAddress(), server
					.getPassword());
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			log.info("发送通知邮件成功：" + getMailAddress(mailObj.getSendAddress()));
			return true;
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		log.info("发送通知邮件失败：" + getMailAddress(mailObj.getSendAddress()));
		return false;
	}

	/**
	 * 
	 * @param mailAddrs
	 * @return
	 */
	private static String getMailAddress(List<String> mailAddrs) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (String mail : mailAddrs) {
			sb.append(mail).append(",");
		}
		return sb.append("}").toString();
	}
}
