package org.my431.util;

import java.security.MessageDigest;

public class MD5 {

	public MD5() {
	}
	
	public static String getMd5(String source){
		String s = null;
		  char hexDigits[] = {
		     '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'p', 'o', 'i', 'u',  'y', 't'};
		   try
		   {
		    MessageDigest md = MessageDigest.getInstance( "MD5" );
		    md.update(source.getBytes());
		    byte tmp[] = md.digest();          
		                                            
		    char str[] = new char[16 * 2];
		                                    
		    int k = 0;                         
		    for (int i = 0; i < 16; i++) {    
		                                
		     byte byte0 = tmp[i];             
		     str[k++] = hexDigits[byte0 >>> 4 & 0xf]; 
		                                                   
		     str[k++] = hexDigits[byte0 & 0xf];        
		    }
		    s = new String(str);

		   }catch( Exception e )
		   {
		    e.printStackTrace();
		   }
		   return s;
	}

	 /** 
     * 加密解密算法 执行一次加密，两次解密 
     */   
    public static String convertMD5(String inStr){  
  
        char[] a = inStr.toCharArray();  
        for (int i = 0; i < a.length; i++){  
            a[i] = (char) (a[i] ^ 's');  
        }  
        String s = new String(a);  
        return s;  
  
    }  
	public static void main(String[] args) {
		System.out.println(getMd5("123456"));
		System.out.println(convertMD5(getMd5(getMd5("123456"))));
	}
}