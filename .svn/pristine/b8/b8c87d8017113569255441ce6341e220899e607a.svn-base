package org.my431.base.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DownloadServlet extends HttpServlet {
	static final private String CONTENT_TYPE = "text/html; charset=GBK";

	// Initialize global variables
	public void init() throws ServletException {
	}

	// Process the HTTP Get request
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		// 要下载的文件�?
		// 之所以这样处理，主要是因为文件名的中文化问题，这样处理的话，中文文件名也会正常显�?
		String downloadfile = new String((request.getParameter("downFile")).getBytes("iso8859-1"), "UTF-8");
		String downloadfileName = new String((request.getParameter("downName")).getBytes("iso8859-1"), "UTF-8");

		ServletContext context = getServletContext();
		ServletConfig config = getServletConfig();

		// 获取要下载文件所在的目录，这里是对应于服务器上的物理路径
		// 目录的格式是这样�?
		// 根目录（WEB主目录所对应的实际物理目录）
		// +虚拟目录（下载文件存放的子目录）
		//String downloadpath = context.getRealPath(File.separator)+ config.getInitParameter("downloadPath") + File.separator;
		String downloadpath = context.getRealPath(File.separator);
		// 构建下载文件的对�?
		java.io.File file = new java.io.File(downloadpath + downloadfile);
		
		if(!file.exists()){
			response.sendRedirect("no_file.jsp");
		}else{
			// 获得文件的长�?
			long filesize = file.length();
			// 设置输出格式
			String fileType=getFiletype(downloadfile);
			String type= "";
			
			if(downloadfileName.lastIndexOf(".") != -1){
				type = downloadfileName.substring(downloadfileName.lastIndexOf("."), downloadfileName.length());
			}
			
			response.addHeader("content-type", "application/x-msdownload;");
			if(type!=null&&fileType.equals(type)){
				response.addHeader("Content-Disposition", "attachment; filename=" + new String(downloadfileName.getBytes("gb2312"), "iso8859-1"));
			}else{
				response.addHeader("Content-Disposition", "attachment; filename=" + new String((downloadfileName+"."+fileType).getBytes("gb2312"), "iso8859-1"));
			}
			
			//response.addHeader("Content-Disposition", "attachment; filename="+ response.encodeURL(downloadfile));
			//response.setHeader("Content-disposition","attachment; filename=" +  new String((downloadfile).getBytes("gb2312"), "iso8859-1"));
			response.addHeader("content-length", Long.toString(filesize));
			// 向客户端写入文件
			java.io.FileInputStream fin = new java.io.FileInputStream(file);
			byte[] b = new byte[1];
			int j = 0;
			while ((j = fin.read(b)) > 0) {
				response.getOutputStream().write(b);
			}
			fin.close();
		}
	}

	// Process the HTTP Post request
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// Clean up resources
	public void destroy() {
	}
	
	// 取文件名后缀
	private static String getFiletype(String fileName) {

		String type = "";
		if (fileName == null || fileName.equals(""))
			return type;
		int position = fileName.lastIndexOf(".");
		if (position != -1) {
			type = fileName.substring(position + 1, fileName.length());
		}
		return type;
	}
}
