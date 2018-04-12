package org.my431.plugin.email;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class EMailUtils {
	
	public static String host; //邮件默认smtp地址   
	public static String from;//邮件默认发送地址  
	public static String pwd;//默认邮件密码
	
	static {
		init();
	}
	
	/**
	 * 
	 * @param title 邮件标题
	 * @param content 邮件正文
	 * @param to 发送地址 如果是String 类型 只向该地址发送 如果是List类型 向List中每个元素所指向的邮箱地址发送
	 * @param attachments 附件地址列表
	 * @param cc 抄送地址列表
	 * 
	 * @return 是否发送成功
	 */
	public static boolean textMail(String title ,String content,Object to,List cc ,List...attachments) {
		init();
		return sendMail(EMailUtils.host,EMailUtils.from,EMailUtils.pwd,title,content,to,cc,false,attachments);
	}
	
	/**
	 * 
	 * @param title 邮件标题
	 * @param content 邮件正文
	 * @param to 发送地址 如果是String 类型 只向该地址发送 如果是List类型 向List中每个元素所指向的邮箱地址发送
	 * @param attachments 附件地址列表
	 * @param cc 抄送地址列表
	 * 
	 * @return 是否发送成功
	 */
	public static boolean htmlMail(String title ,String content,Object to,List cc ,List...attachments) {
		init();
		return sendMail(EMailUtils.host,EMailUtils.from,EMailUtils.pwd,title,content,to,cc,true,attachments); 
	}
	
	/**
	 * 
	 * @param host 发送方 邮箱服务器地址 如mail.sina.com.cn
	 * @param from 发送方 邮箱地址
	 * @param pwd  发送方 邮箱密码
	 * @param title 邮件标题
	 * @param content 邮件正文
	 * @param to  发送地址 如果是String 类型 只向该地址发送 如果是List类型 向List中每个元素所指向的邮箱地址发送
	 * @param cc 抄送地址列表
	 * @param attachments 附件地址列表
	 * 
	 * @return 是否发送成功
	 */
	public static boolean textMail(String host,String from,String pwd,String title ,String content,Object to,List cc ,List...attachments) {
		return sendMail(host,from,pwd,title,content,to,cc,false,attachments);
	}
	
	/**
	 * 
	 * @param host 发送方 邮箱服务器地址 如mail.lasun.com.cn
	 * @param from 发送方 邮箱地址
	 * @param pwd  发送方 邮箱密码
	 * @param title 邮件标题
	 * @param content 邮件正文
	 * @param to  发送地址 如果是String 类型 只向该地址发送 如果是List类型 向List中每个元素所指向的邮箱地址发送
	 * @param cc 抄送地址列表
	 * @param attachments 附件地址列表
	 * 
	 * @return 是否发送成功
	 */
	public static boolean htmlMail(String host,String from,String pwd,String title ,String content,Object to,List cc ,List...attachments) {
		return sendMail(host,from,pwd,title,content,to,cc, true,attachments); 
	}
	
	/**
	 * 
	 * @param host 发送方 邮箱服务器地址 如mail.lasun.com.cn
	 * @param from 发送方 邮箱地址
	 * @param pwd 发送方 邮箱密码
	 * @param title 邮件标题
	 * @param content 邮件正文
	 * @param tos 发送地址 如果是String 类型 只向该地址发送 如果是List类型 向List中每个元素所指向的邮箱地址发送
	 * @param isHtml true为html格式邮件 false 为文本格式邮件
	 * @param attachments 附件地址列表
	 * @return 是否发送成功
	 */
	private static synchronized boolean sendMail(String host,String from,String pwd,String title ,String content,Object tos ,List<String> cc ,boolean isHtml,List...attachments){
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		//MyAuthenticator myauth = null;
		
		if(null == host || null == from || pwd == null ) {
			System.out.println("发送邮件地址信息不全");
			return false;
		}else {
			//myauth = new MyAuthenticator(from,pwd);
			props.put("mail.smtp.host", host);
		}
		 	
	    //Session session = Session.getDefaultInstance(props, myauth); 
		Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);
	    try {
	    	Multipart mm = new MimeMultipart();
			message.setFrom(new InternetAddress(from));
			/* 添加发送地址*/
			if(tos instanceof String ) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress((String)tos));
			}else if (tos instanceof List) {
				for(String to :(List<String>)tos) {
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				}
			}else {
				System.out.println("ClassCastException");
				return false;
			}
		    message.setSubject(title); //设置标题
		    message.setSentDate(new Date());//设置发送时间
		   
		    if(!isHtml && attachments == null){ //文本格式
			    message.setText(content);
		    }else { //html格式
		         BodyPart mdp=new MimeBodyPart();
		         mdp.setContent(content,"text/html;charset=gb2312");
		         mm.addBodyPart(mdp);
		    }
		    /* 添加附件*/
		    if(null != attachments ) {
		    	for(List<String> paths : attachments) {
		    		for(String path :paths){
			    		BodyPart mdp=new MimeBodyPart();
			    		mdp=new MimeBodyPart();
			    		FileDataSource fds=new FileDataSource(path);
			    		DataHandler dh=new DataHandler(fds);
			    		
			    		int separatorIndex = path.lastIndexOf(File.separator);
			    		if( separatorIndex == -1)  {
			    			mdp.setFileName(path);
			    		}else {
			    			mdp.setFileName(new String(path.substring(separatorIndex).getBytes(),"ISO8859-1"));
			    		}
				    	mdp.setDataHandler(dh);
				    	mm.addBodyPart(mdp);
		    		}
		    	}
		    }
		    
		    /* 添加抄送 */
		    if(null != cc && cc.size() != 0 ) {
		    	for(String c : cc) {
		    		message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(c));
		    	}
		    }
		    message.setContent(mm);
		    message.saveChanges(); 
		    Transport transport=session.getTransport("smtp");
		    transport.connect(host,from,pwd);//
		    transport.sendMessage(message,message.getAllRecipients());
		    transport.close();
		    return true;
	        //Transport.send(message,message.getAllRecipients());
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		return false;
	}
	
	/**
	 * 初始化邮件发送系统地址
	 */
	public static void init() {
		PropertiesConfiguration config = null;
		try {
			config = new PropertiesConfiguration("email.properties");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		} 
       host = config.getString("email.defaultHost").trim();
       from = config.getString("email.defaultFrom").trim();
       pwd = config.getString("email.defaultPwd").trim();
	}
	
	public static void main(String args[]) throws Exception {
		List<String> listAddr = new ArrayList<String>();// 收件人
		listAddr.add("25754703@qq.com");
		String title="test标题";
		String content="测试内容，测试内容";
		
		String smtp = EMailUtils.host;//邮件服务
		String address = EMailUtils.from;//邮件地址
		String password = EMailUtils.pwd;//邮件密码
		ServerEntity server = new ServerEntity(smtp,address,password);
		
		MailEntity mail = new MailEntity(title,content,listAddr);
		// 发送邮件
		MailPlugin.sendMail(server, mail);
	}

}
