/**
 * 
 */
package org.my431.platform.filter;


import java.util.Iterator;
import java.util.Map;

 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringEscapeUtils;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;



/**
 * @author 90
 * Date:2017-7-4下午7:38:04 
 */
public class XssHttpServletRequestWrapper  extends HttpServletRequestWrapper{
	/**
	 * @param request
	 */
	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}


	private static Policy policy = null;  
    
    static{  
    	String path =XssHttpServletRequestWrapper.class.getClassLoader().getResource("antisamy-anythinggoes.xml").getFile();  
		 try {
			policy = Policy.getInstance(path);
		} catch (PolicyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }  
    @SuppressWarnings("rawtypes")  
    public Map<String,String[]> getParameterMap(){  
        Map<String,String[]> request_map = super.getParameterMap();  
        Iterator iterator = request_map.entrySet().iterator();  
        System.out.println("request_map"+request_map.size());  
        while(iterator.hasNext()){  
            Map.Entry me = (Map.Entry)iterator.next();  
            //System.out.println(me.getKey()+":");  
            String[] values = (String[])me.getValue();  
            for(int i = 0 ; i < values.length ; i++){  
                //System.out.println(values[i]);  
                values[i] = xssClean(values[i]);  
            }  
        }  
        return request_map;  
    }  
     public String[] getParameterValues(String paramString)  
      {  
        String[] arrayOfString1 = super.getParameterValues(paramString);  
        if (arrayOfString1 == null)  
          return null;  
        int i = arrayOfString1.length;  
        String[] arrayOfString2 = new String[i];  
        for (int j = 0; j < i; j++)  
          arrayOfString2[j] = xssClean(arrayOfString1[j]);  
        return arrayOfString2;  
      }  
  
      public String getParameter(String paramString)  
      {  
        String str = super.getParameter(paramString);  
        if (str == null)  
          return null;  
        return xssClean(str);  
      }  
  
      public String getHeader(String paramString)  
      {  
        String str = super.getHeader(paramString);  
        if (str == null)  
          return null;  
        return xssClean(str);  
      }  
        
        
    private String xssClean(String value) {  
        AntiSamy antiSamy = new AntiSamy(); 
        try {  
        	final CleanResults cr = antiSamy.scan(value, policy);  
            //安全的HTML输出  
        	/*String str=cr.getCleanHTML();
        	//去掉a标签
        	str=str.replaceAll("<a", "");
        	str=str.replaceAll("</a>", "");
            return str;  */
        	return cr.getCleanHTML();
        } catch (ScanException e) {  
            e.printStackTrace();  
        } catch (PolicyException e) {  
            e.printStackTrace();  
        }  
        return value;  
    }   
    
    public static void main(String[] args) {
    	try {
    		 String path =XssHttpServletRequestWrapper.class.getClassLoader().getResource("antisamy-anythinggoes.xml").getFile();  
			 Policy policy = Policy.getInstance(path);
    	} catch (PolicyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}
