package org.my431.platform.utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ContextUtils extends ContextLoaderListener {
	private static final Log log = LogFactory.getLog(ContextUtils.class);
	private static ApplicationContext context;

	public static ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext ctx) {
		context = ctx;
	}

	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		ServletContext context = event.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		log.debug("ctx initialized...    ctx:"+ctx);
		setContext(ctx);
	}
	
	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}
}
