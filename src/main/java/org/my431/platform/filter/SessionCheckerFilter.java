package org.my431.platform.filter;




import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class SessionCheckerFilter implements Filter {

	private Log log = LogFactory.getLog(SessionCheckerFilter.class);

	protected FilterConfig filterConfig = null;

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpSession theSession = ((HttpServletRequest) request)
				.getSession(true);
		String servletPath = ((HttpServletRequest) request).getServletPath();
		String componentLink = servletPath;

		boolean pass = true; // 验证通过
		chain.doFilter(request, response);
		return;
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