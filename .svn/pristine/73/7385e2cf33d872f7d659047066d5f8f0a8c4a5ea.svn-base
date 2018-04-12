package org.my431.platform.filter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class FileCaptureFilter implements Filter{
private String protDirPath;

public void init(FilterConfig filterConfig) throws ServletException    { 
protDirPath = filterConfig.getServletContext().getRealPath("/");

}   
public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws IOException, ServletException    { 
  String fileName = protDirPath + "/portal/index.html"; 
  PrintStream out = new PrintStream(response.getOutputStream(),true,"UTF-8");; 
  FileCaptureResponseWrapper responseWrapper = new FileCaptureResponseWrapper((HttpServletResponse)response); 
  chain.doFilter(request, responseWrapper);  // fill responseWrapper up 
  String html = responseWrapper.toString();//得到的html页面结果字符串
  responseWrapper.writeFile(fileName);  // dump the contents 写成html文件，也可以保存在内存
  responseWrapper.writeResponse( out );   // back to browser 
//  responseWrapper.sendRedirect("/portal/index.jsp");
}   
public void destroy() {}
}