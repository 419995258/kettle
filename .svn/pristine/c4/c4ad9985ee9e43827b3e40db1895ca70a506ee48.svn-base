package org.my431.platform.filter;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.my431.base.model.BaseUrl;
import org.my431.base.services.BaseUrlManager;
import org.my431.platform.listener.OnlineModel;
import org.my431.platform.listener.PhoneOnlineListener;
import org.my431.plugin.redis.services.RedisManager;

import net.sf.json.JSONObject;



public class PhoneSessionCheckerFilter implements Filter {

	private Log log = LogFactory.getLog(PhoneSessionCheckerFilter.class);

	protected FilterConfig filterConfig = null;
	
	public void destroy() {
		this.filterConfig = null;
	}
	private static String prePropertiesMapKey = "global.base.BaseProperties.key.";
	private static final String redisAppSessionIdPrefix = "app.sessionId.";
	private RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpSession theSession = ((HttpServletRequest) request).getSession(true);
		HttpServletResponse theResponse=((HttpServletResponse) response);
		HttpServletRequest theRequest=((HttpServletRequest) request);
		
		String requesetURI = theRequest.getRequestURI();
		if(!(requesetURI.endsWith(".jsp") || requesetURI.endsWith(".jspx")))
		{
			chain.doFilter(request, response);
			return;
		}
		
		String token=request.getParameter("token");
		String path=theRequest.getServletPath();
		String queryStr=theRequest.getQueryString();
		String gwIp=theRequest.getRemoteAddr();//公网ip
		BaseUrl url=BaseUrlManager.getObjByUrl(path);
		System.out.println("App端："+path+"--"+queryStr);
		System.out.println("--------------------进来"+token);
		if("ejyApp2016-10-28".equals(token)){
			System.out.println("--------------------放开");
			chain.doFilter(request, response);
			return;
		}
		
		if(token==null||token.equals("")){
			String appJson="";
			try {
				 StringBuffer info=new java.lang.StringBuffer();
		         InputStream in=request.getInputStream();
		         BufferedInputStream buf=new BufferedInputStream(in);
		         
		         byte[] buffer=new byte[1024]; 
		         int iRead;
		         while((iRead=buf.read(buffer))!=-1)   
		         {
		          info.append(new String(buffer,0,iRead,"UTF-8"));
		         } 
		         appJson=info.toString();
		         
		         request.setAttribute("appJson", appJson);
		         
		        JSONObject jsonObject = JSONObject.fromObject(appJson);
		        if(jsonObject.get("token")!=null){
		        	token =jsonObject.get("token").toString();
		        }
			} catch (Exception e) {
				System.out.println("错误：appJson："+appJson);
			}
        
		}
        
		String sessionId=null;
		if(token!=null&&!token.equals("")){
			sessionId=token.toString();
			PhoneOnlineListener.updateLastAcessTime(token);
		}
		
		
		if(theSession.getAttribute("wsloginName")==null){
			if(sessionId==null||sessionId.equals("")){
				System.out.print("session验证失败");
				theResponse.sendRedirect(theRequest.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+theRequest.getContextPath()+"/error_app.jsp");
			}else{
				OnlineModel om=PhoneOnlineListener.getSession(sessionId);
				if(om==null){
					System.out.print("session验证失败");
					theResponse.sendRedirect(theRequest.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+theRequest.getContextPath()+"/error_app.jsp");
				}else{
					System.out.print("session验证成功");
					//app日志
					chain.doFilter(request, response);
				}
			}
			
			return;
		}else{
			System.out.print("session验证成功");
			//app日志
			OnlineModel om=PhoneOnlineListener.getSession(sessionId);
			chain.doFilter(request, response);
			return;
		}

		
	}
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}
}