package org.my431.platform.filter;


import java.io.IOException;
import java.net.URLEncoder;

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
import org.my431.base.services.BaseAreaManager;
import org.my431.base.services.BaseRoleUrlMapManager;
import org.my431.base.services.BaseUrlManager;
import org.my431.platform.listener.OnlineModel;
import org.my431.taglib.My431Function;



public class BaseSessionCheckerFilter implements Filter {

	private Log log = LogFactory.getLog(BaseSessionCheckerFilter.class);

	protected FilterConfig filterConfig = null;
	
	private BaseAreaManager baseAreaManager=(BaseAreaManager)org.my431.platform.utils.ContextUtils.getBean("baseAreaManager");

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpSession theSession = ((HttpServletRequest) request).getSession(true);
		HttpServletResponse theResponse=((HttpServletResponse) response);
		HttpServletRequest theRequest=((HttpServletRequest) request);

		String areaCode=request.getParameter("areaCode");
		if(areaCode!=null&&!areaCode.equals("")){
			if(areaCode.equals("-1")){
				theSession.setAttribute("areaLabel", "全国");
			}else{
				theSession.setAttribute("areaLabel", baseAreaManager.getLabelByCode(areaCode));
			}
		}
		
		if(theSession.getAttribute("wsloginName")==null){
			theResponse.sendRedirect(theRequest.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+theRequest.getContextPath()+"/error.jsp");
			return;
		}else{
			
			String isAuthenticatedUrl=My431Function.getValueByCode("isAuthenticatedUrl");
			String path=theRequest.getServletPath();
			String queryStr=theRequest.getQueryString();
			System.out.println(path+"--"+queryStr);
			String ext="";
			if(path.lastIndexOf(".")==-1){
				
			}else{
				ext=path.substring(path.lastIndexOf("."), path.length());
			}
			
			if(ext.equals(".jsp")||ext.equals(".jspx")){
				if(isAuthenticatedUrl!=null&&isAuthenticatedUrl.equals("1")){
					String wsDefaultRoleCode=theSession.getAttribute("wsDefaultRoleCode").toString();
					if(wsDefaultRoleCode!=null){
						System.out.println(path);
						BaseUrl url=BaseUrlManager.getObjByUrl(path);
						boolean ispass=true;
						if(url==null){
							ispass=false;
						}else{
							Object obj=theSession.getAttribute("logined");
							Object objUserId=theSession.getAttribute("wsuserId");
							OnlineModel  onlineModel=new OnlineModel();
							String userId="";
							if(obj!=null){
								onlineModel=(OnlineModel) obj;
							}
							if(objUserId!=null){
								userId=(String) objUserId;
							}
							//if(!BaseRoleUrlMapManager.isPass(wsDefaultRoleCode, url)){
							if(!BaseRoleUrlMapManager.isPass(wsDefaultRoleCode, url)){
								ispass=false;
							}
						}
						if(ispass){
							chain.doFilter(request, response);
							return;
						}else{
							theResponse.sendRedirect(theRequest.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+theRequest.getContextPath()+"/url_error.jsp?path="+URLEncoder.encode(path,"utf-8"));
							
							return;
						}
						
					}else{
						theResponse.sendRedirect(theRequest.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+theRequest.getContextPath()+"/url_error.jsp?path="+URLEncoder.encode(path,"utf-8"));
						return;
					}

				}else{
					chain.doFilter(request, response);
					return;
				}
			}else{
				chain.doFilter(request, response);
				return;
			}
			
		
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