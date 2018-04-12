package org.my431.platform.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.my431.base.json.JsonUtil;

public class StatisticalFilter implements Filter {

	public void init(FilterConfig arg0) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		String areaCode = servletRequest.getParameter("areaCode");
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		String uri = httpServletRequest.getRequestURI();
		if (uri.indexOf("_map_pad.jsp") != -1
				&& ("011".equals(areaCode) || "012".equals(areaCode)// 四个直辖市单独处理
						|| "031".equals(areaCode) || "050".equals(areaCode))) {
			String url = httpServletRequest.getRequestURL().toString();
			statisticalWrapper(servletRequest, servletResponse, areaCode, url);
		} else {
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	public static void statisticalWrapper(ServletRequest servletRequest,
			ServletResponse servletResponse, String areaCode, String url) {
		StringBuffer queryString = new StringBuffer();
		Enumeration<String> parameterNames = servletRequest.getParameterNames();
		while (parameterNames.hasMoreElements()) // 拼接参数(queryString)
		{
			String parameterName = parameterNames.nextElement();
			if (!parameterName.equals("areaCode")) {
				queryString.append(parameterName);
				queryString.append("=");
				queryString.append(servletRequest.getParameter(parameterName));
				queryString.append("&");
			}
		}
		if (queryString.length() > 0) {
			queryString.deleteCharAt(queryString.length() - 1);
			url = url + "?" + queryString.toString();
		}

		url = url.indexOf("?") == -1 ? url + "?" : url + "&";

		String areaCode1 = "areaCode=" + areaCode + "001";
		//String areaCode2 = "areaCode=" + areaCode + "002";

		// 越过直辖市市辖区、县，直接访问下层
		String json1 = getHttpBodyAsString(url + areaCode1);
		//String json2 = getHttpBodyAsString(url + areaCode2);

//		String json = JsonUtil
//				.jsonFormatter(json1.substring(0, json1.lastIndexOf("}"))
//						+ ","
//						+ json2.substring(json2.indexOf("{") + 1, json2
//								.length()));

		String json = JsonUtil.jsonFormatter(json1);
		// System.out.println(json);
		try {
			servletResponse.setCharacterEncoding("UTF-8");
			// servletResponse.setContentType("application/json");
			servletResponse.setContentType("text/html");
			servletResponse.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getHttpBodyAsString(String url) {
		String result = "";
		try {
			URLConnection connection = new URL(url).openConnection();
			InputStream is = connection.getInputStream();
			InputStreamReader reader=new InputStreamReader(is,"utf-8"); 
			int r=0;  
			while((r=reader.read())!=-1){  
				result+=(char)r;  
			}
			is.close();  
			reader.close();  
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		}
		return result;
	}

	public static void main(String[] args) {
	}
}
