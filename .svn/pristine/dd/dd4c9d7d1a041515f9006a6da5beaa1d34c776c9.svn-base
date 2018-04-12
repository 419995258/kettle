/**
 * 
 */
package org.my431.platform.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 90
 * Date:2017-7-6上午10:25:26 
 */
public class XssFilter implements Filter {  
	@SuppressWarnings("unused")  
	private FilterConfig filterConfig;  
	public void destroy() {  
	    this.filterConfig = null;  
	}  
	public void doFilter(ServletRequest request, ServletResponse response,  
	        FilterChain chain) throws IOException, ServletException {
		HttpServletRequest theRequest=((HttpServletRequest) request);
		String path=theRequest.getServletPath();
		String ext="";
		if(path.lastIndexOf(".")==-1){
			
		}else{
			ext=path.substring(path.lastIndexOf("."), path.length());
		}
		if(".jspx".equals(ext)){
			if(path.contains("BaseQuery")){
				chain.doFilter(request, response);
			}else{
				chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);  //过滤
			}
		}else{
			chain.doFilter(request, response);
		}
	    
	}  
	public void init(FilterConfig filterConfig) throws ServletException {  
	    this.filterConfig = filterConfig;  
	}     
}  
