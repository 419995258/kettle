package org.my431.util;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.i18n.LocaleContextHolder;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
public class HtmlUtil {
    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式  
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式  
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式  
    private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符  
      
    /** 
     * @param htmlStr 
     * @return 
     *  删除Html标签 
     */  
    public static String delHTMLTag(String htmlStr) {  
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);  
        Matcher m_script = p_script.matcher(htmlStr);  
        htmlStr = m_script.replaceAll(""); // 过滤script标签  
  
        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);  
        Matcher m_style = p_style.matcher(htmlStr);  
        htmlStr = m_style.replaceAll(""); // 过滤style标签  
  
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);  
        Matcher m_html = p_html.matcher(htmlStr);  
        htmlStr = m_html.replaceAll(""); // 过滤html标签  
  
        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);  
        Matcher m_space = p_space.matcher(htmlStr);  
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签  
        return htmlStr.trim(); // 返回文本字符串  
    }  
      
    public static String getTextFromHtml(String htmlStr){  
        htmlStr = delHTMLTag(htmlStr);  
        htmlStr = htmlStr.replaceAll("&nbsp;", "");  
        htmlStr = htmlStr.substring(0, htmlStr.indexOf("。")+1);  
        return htmlStr;  
    }  
    
    
    public static String getHtmlFile(String urlStr){  
        URL url;  
        try {  
            if (urlStr.indexOf("?") != -1) {  
                urlStr = urlStr + "&locale="  
                        + LocaleContextHolder.getLocale().toString();  
            } else {  
                urlStr = urlStr + "?locale="  
                        + LocaleContextHolder.getLocale().toString();  
            }  
            System.out.println("-------"+urlStr);
            url = new URL(urlStr);  
            URLConnection uc = url.openConnection();  
            InputStream fis = uc.getInputStream();  
           /* URLConnection rulConnection = url.openConnection();  
            
	        HttpURLConnection httpUrlConnection  =  (HttpURLConnection) rulConnection;
	        httpUrlConnection.setConnectTimeout(30000);
            httpUrlConnection.setReadTimeout(30000);
            httpUrlConnection.connect();
            String code = new Integer(httpUrlConnection.getResponseCode()).toString();
            String message = httpUrlConnection.getResponseMessage();
            System.out.println("getResponseCode code ="+ code);
            System.out.println("getResponseMessage message ="+ message);
	            
	        InputStream fis = rulConnection.getInputStream();  */
             
            ByteArrayOutputStream tidyOutStream; //输出流
                Reader reader;  
                
                ByteArrayOutputStream  bos  =  new  ByteArrayOutputStream();  
                int  ch;  
                while((ch=fis.read())!=-1)  
                {  
                        bos.write(ch);  
                }  
                fis.close();  
                byte[]  bs  =  bos.toByteArray();  
                bos.close();  
                String basil=new String(bs,"UTF-8");
            return basil.toString();  
        } catch (IOException e) { 
            e.printStackTrace();
            return "";
        }  
  
    }  
    
    
	public static ByteArrayOutputStream parseHTML2PDF(ByteArrayOutputStream pdfOut,
			String html,Rectangle pageSize) throws Exception {
		
		BaseFont bfCN = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H",
				false);
		// 中文字体定义
		Font chFont = new Font(bfCN, 12, Font.NORMAL, BaseColor.BLUE);
		Font secFont = new Font(bfCN, 12, Font.NORMAL, new BaseColor(0, 204,
				255));
		
		Document document = new Document(pageSize);
		
		PdfWriter pdfwriter = PdfWriter.getInstance(document,
				pdfOut);
		pdfwriter.setViewerPreferences(PdfWriter.HideToolbar);
		document.open();
		
		XMLWorkerHelper.getInstance().parseXHtml(pdfwriter, document, new StringReader(html));
		
		document.close();
		return pdfOut;
	}
	/**
	 * hyl<br/>
	 * 20160628<br/>
	 * 加页眉页脚
	 * */
	public static ByteArrayOutputStream parseHTML2PDFOfFootHeader(ByteArrayOutputStream pdfOut,
			String html,Rectangle pageSize,String headerStr) throws Exception {
		
		BaseFont bfCN = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H",
				false);
		// 中文字体定义
		Font chFont = new Font(bfCN, 12, Font.NORMAL, BaseColor.BLUE);
		Font secFont = new Font(bfCN, 12, Font.NORMAL, new BaseColor(0, 204,
				255));
		
		Document document = new Document(pageSize);
		
		PdfWriter pdfwriter = PdfWriter.getInstance(document,
				pdfOut);
		pdfwriter.setViewerPreferences(PdfWriter.HideToolbar);
		
		
		HeaderFooter header=new HeaderFooter(headerStr,8,pageSize);
		// 增加页眉页脚  
		pdfwriter.setPageEvent(header);
		document.open();
				
		XMLWorkerHelper.getInstance().parseXHtml(pdfwriter, document, new StringReader(html));
		
		document.close();
		return pdfOut;
	}
	/**
	 * hyl<br/>
	 * 20160801<br/>
	 * 加页眉页脚，同时可以控制页眉页脚的xy值
	 * */
	public static ByteArrayOutputStream parseHTML2PDFOfFootHeader(ByteArrayOutputStream pdfOut,
			String html,Rectangle pageSize,String headerStr,float headx,float heady,float footx,float footy) throws Exception {
		
		BaseFont bfCN = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H",
				false);
		// 中文字体定义
		Font chFont = new Font(bfCN, 12, Font.NORMAL, BaseColor.BLUE);
		Font secFont = new Font(bfCN, 12, Font.NORMAL, new BaseColor(0, 204,
				255));
		
		Document document = new Document(pageSize);
		
		PdfWriter pdfwriter = PdfWriter.getInstance(document,
				pdfOut);
		pdfwriter.setViewerPreferences(PdfWriter.HideToolbar);
		
		
		HeaderFooter header=new HeaderFooter(headerStr,8,pageSize,headx,heady,footx,footy);
		// 增加页眉页脚  
		pdfwriter.setPageEvent(header);
		document.open();
				
		XMLWorkerHelper.getInstance().parseXHtml(pdfwriter, document, new StringReader(html));
		
		document.close();
		return pdfOut;
	}
	
	
	/**
	 * map工具
	 * @param map
	 * @param key
	 * @param value
	 * @return
	 */
	public static Map<String, Double> setMapValues(Map<String, Double> map, String key, Double value) {
		if(map.containsKey(key)){
			value=value+map.get(key);
		}
		map.put(key, value);
		return map;
	}
      
    public static void main(String[] args) {  
        String str = "<div style='text-align:center;'> 整治“四风”   清弊除垢<br/><span style='font-size:14px;'> </span><span style='font-size:18px;'>公司召开党的群众路线教育实践活动动员大会</span><br/></div>";  
        System.out.println(getTextFromHtml(str));  
    }  
}
