package org.my431.platform.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.my431.platform.utils.ContextUtils;
import org.my431.plugin.redis.services.RedisManager;
import org.my431.taglib.My431Function;
import org.my431.util.sortUrl.MD5BaseShortUrlService;



public class ViewSessionCheckerFilter implements Filter {

	private Log log = LogFactory.getLog(ViewSessionCheckerFilter.class);

	protected FilterConfig filterConfig = null;
	

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse theResponse=((HttpServletResponse) response);
		HttpServletRequest theRequest=((HttpServletRequest) request);
		
		String requesetURI = theRequest.getRequestURI();
		
		String token=request.getParameter("token");
		
		System.out.println("requesetURI:"+requesetURI);
		requesetURI=requesetURI.substring(requesetURI.lastIndexOf("/")+1, requesetURI.lastIndexOf("/")+6);
		
		RedisManager redisManager=(RedisManager)ContextUtils.getBean("redisManager");
		MD5BaseShortUrlService s=new MD5BaseShortUrlService();
		System.out.println("short:"+requesetURI);
		String longUrl=s.lookupLong(requesetURI,redisManager)+"&token="+token;
		
		if(token==null){
			if(longUrl.contains("Project")){
				String id=longUrl.substring(longUrl.indexOf("id=")+3, longUrl.indexOf("&"));
				String baseUrl=My431Function.getValueByCode("gb.app.view.base");
				theResponse.sendRedirect(baseUrl+"viewProject.jsp?id="+id);
			}
			if(longUrl.contains("School")){
				String id=longUrl.substring(longUrl.indexOf("orgCode=")+8, longUrl.indexOf("&"));
				String baseUrl=My431Function.getValueByCode("gb.app.view.base");
				theResponse.sendRedirect(baseUrl+"viewSchool.jsp?orgCode="+id);
			}
			if(longUrl.contains("Item")){
				String id=longUrl.substring(longUrl.indexOf("id=")+3, longUrl.indexOf("&"));
				String baseUrl=My431Function.getValueByCode("gb.app.view.base");
				theResponse.sendRedirect(baseUrl+"viewItem.jsp?id="+id);
			}
		}else{
			System.out.println("longUrl:"+longUrl);
			theResponse.sendRedirect(longUrl);
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