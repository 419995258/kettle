package org.my431.base.xmlrpc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.common.XmlRpcHttpRequestConfig;
import org.apache.xmlrpc.server.AbstractReflectiveHandlerMapping;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcHandlerMapping;
import org.apache.xmlrpc.webserver.XmlRpcServlet;


@SuppressWarnings("serial")
public class XmlRpcServiceServlet extends XmlRpcServlet {
	public static Logger logger = Logger.getLogger(XmlRpcServiceServlet.class);

	@SuppressWarnings("unchecked")
	private static ThreadLocal clientIpAddress = new ThreadLocal();

	public static String getClientIpAddress() {
		return (String) clientIpAddress.get();
	}

	private boolean isAuthenticated(String pUserName, String pPassword) {
		
		if(pUserName.equals("gsgb12#$qwERZXcv68533")){
			return true;
		}else{
			return false;
		}
	}

	protected XmlRpcHandlerMapping newXmlRpcHandlerMapping() throws XmlRpcException {
		PropertyHandlerMapping mapping = new PropertyHandlerMapping();
		@SuppressWarnings("unused")
		AbstractReflectiveHandlerMapping.AuthenticationHandler handler = new AbstractReflectiveHandlerMapping.AuthenticationHandler() {
			public boolean isAuthorized(XmlRpcRequest pRequest) {
				XmlRpcHttpRequestConfig config = (XmlRpcHttpRequestConfig) pRequest.getConfig();
				return isAuthenticated(config.getBasicUserName(), config.getBasicPassword());
			};
		};
		mapping.setAuthenticationHandler(handler);
		
		mapping.addHandler("base", BaseService.class);
		
		return mapping;
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest pRequest, HttpServletResponse pResponse) throws IOException, ServletException {
		clientIpAddress.set(pRequest.getRemoteAddr());
		logger.debug("Client Ip Address: " + getClientIpAddress());
		super.doPost(pRequest, pResponse);
	}

}
