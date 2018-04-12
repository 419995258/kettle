package org.my431.base.mobile;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import org.my431.util.DateFormater;
//该Demo主要解决Java在Linux等环境乱码的问题
public class mdSmsSend_u  {
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		//输入软件序列号和密码
		String sn="SDK-BBX-010-19225";
		String pwd="d46fc4cB";
//		String mobiles="13810000123";
		String mobiles="15010007997";
		String content=URLEncoder.encode("验证码为333(切勿告知他人),请在页面中输入以完成验证，有问题请致电400-8980-910 "+DateFormater.DateTimeToString(new Date())+"【信息化进展系统】", "utf8");	
		Client client=new Client(sn,pwd,"http://sdk.entinfo.cn:8060/webservice.asmx");
		
		String result_mt = client.mdSmsSend_u(mobiles, content, "", "", "");
		
		if(result_mt.startsWith("-")||result_mt.equals(""))//发送短信，如果是以负号开头就是发送失败。
		{
			System.out.print("发送失败！返回值为："+result_mt+"请查看webservice返回值对照表");
			return;
		}
		//输出返回标识，为小于19位的正数，String类型的。记录您发送的批次。
		else
		{
			System.out.print("发送成功，返回值为："+result_mt+DateFormater.DateTimeToString(new Date()));
		}
		
//		String result=client.getBalance();
//		System.out.println(result);

		
	}
}

