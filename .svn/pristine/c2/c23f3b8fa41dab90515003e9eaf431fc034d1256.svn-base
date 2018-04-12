package org.my431.platform.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.my431.platform.web.support.DateConverter;
import org.my431.platform.web.support.StringConverter;
import org.springframework.util.ReflectionUtils;

import com.opensymphony.xwork2.ActionSupport;


/**
 * 简单封装Struts DispatchAction的基类.提供一些基本的简化函数,将不断增强.
 * 
 * @author calvin
 */
public class StrutsAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Logger log = Logger.getLogger(StrutsAction.class);

	public static final String DIRECTLY_MESSAGE_KEY = "message";
	
	public HttpServletRequest request;  
	
	public HttpServletResponse response;

	static {
		registConverter();
	}

	/**
	 * 设置Struts 中数字<->字符串转换，字符串为空值时,数字默认为null，而不是0.
	 * 也可以在web.xml中设置struts的参数达到相同效果，在这里设置可以防止用户漏设web.xml.
	 */
	public static void registConverter() {
		ConvertUtils.register(new StringConverter(), String.class);
		ConvertUtils.register(new IntegerConverter(null), Integer.class);
		ConvertUtils.register(new LongConverter(null), Long.class);
		ConvertUtils.register(new FloatConverter(null), Float.class);
		ConvertUtils.register(new DoubleConverter(null), Double.class);
		ConvertUtils.register(new DateConverter("yyyy-MM-dd"), Date.class);
	}

	/**
	 * 直接输出纯字符串.
	 */
	protected void renderText(HttpServletResponse response, String text) {
		try {
			response.setContentType("text/plain;charset=UTF-8");
			response.getWriter().write(text);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 直接输出纯HTML.
	 */
	protected void renderHtml(HttpServletResponse response, String text) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(text);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 直接输出纯XML.
	 */
	protected void renderXML(HttpServletResponse response, String text) {
		try {
			response.setContentType("text/xml;charset=UTF-8");
			response.getWriter().write(text);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 将FormBean中的内容通过BeanUtils的copyProperties()绑定到Object中.
	 * 因为BeanUtils中两个参数的顺序很容易搞错，因此封装此函数.
	 */
	protected void bindEntity(Object form, Object object) {
		if (form != null) {
			try {
				BeanUtils.copyProperties(object, form);
			} catch (Exception e) {
				ReflectionUtils.handleReflectionException(e);
			}
		}
	}
	/**
	 * ajax 请求返回视图
	 * 
	 * @param data
	 *            ajax结果
	 * @return
	 */
	protected String setAjaxData(String data) {
		try {
			if (data != null && !data.trim().equals("")) {
				// 此时请求 的 字符集 为 struts.i18n.encoding
				//String ENDODING = ServletActionContext.getRequest().getCharacterEncoding();
				// 设置相应的 字符集 为 struts.i18n.encoding
				//ServletActionContext.getResponse().setCharacterEncoding(ENDODING);
				ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
				PrintWriter pw = ServletActionContext.getResponse().getWriter();
				pw.append(data);
				pw.flush();
				pw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @author hyl
	 * 将数据传到前台
	 * */
	public void setAttribute(String key, Object value){
		request.setAttribute(key, value);
	}
	/**
	 * @author hyl
	 * 获取前台数据
	 * */
	public String getParameter(String key){
		String keyStr=request.getParameter(key);
		return keyStr;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
		
	}
	
	public boolean isNullOrEmptyString(Object obj){
		if(obj!=null&&!obj.equals("")&&!obj.equals("null")){
			return false;
		}else{
			return true;
		}
	}
}
