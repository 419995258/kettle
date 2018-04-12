package org.my431.util;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

public class HTTPXMLUtil {
	private Document doc;
	private String regex="-";
	public HTTPXMLUtil(String _file) throws Exception
	{
			SAXBuilder sb = new SAXBuilder();
			URL url= new URL(_file);
			url.openConnection().setConnectTimeout(100000000);
			this.setDoc(sb.build(url));
			
	}
	
	public HTTPXMLUtil(InputStream _file) throws Exception
	{
			SAXBuilder sb = new SAXBuilder();
			this.setDoc(sb.build(_file));
			
	}
	
	public HTTPXMLUtil(String input,String type)
	{
		if (type.equals("xml")){
			try {				
				StringReader reader=new StringReader(input);
				InputSource source=new InputSource(reader);
				SAXBuilder sb = new SAXBuilder();
				this.setDoc(sb.build(source));
				
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (JDOMException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}	
		}else{
			try {
				SAXBuilder sb = new SAXBuilder();
				this.setDoc(sb.build(new FileInputStream(input)));
				
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (JDOMException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}	
		}
		

	}
	public String getValue(String _ElePath)
	{
		Element e=getElement(_ElePath);
		if(e==null)
			return null;
		return e.getValue();

	}
	public Element getElement(String _ElePath)
	{
		
		String[] strs=_ElePath.split(regex);
		Element root = doc.getRootElement();
		Element temp=root;
		
		
	  for(int i=0;i<strs.length;i++)
		{
		 // System.out.println(i+":"+strs[i]);
		  if(temp.getName().equals(strs[i]) )
		  {
			 
			  if(i!=strs.length-1)
			  {
				   
				  if (temp.getChildren(strs[i+1]).size()==0)
				  {
					  
					  return null;
					  
				  }
			  temp=temp.getChild(strs[i+1]);
			  
			  if (temp.equals(null)) return null;
			  }else
			  {
				  return temp;
			  }
			  
			 // System.out.println(temp.getName());
		  }
		  else
		  {
			  return null;
		  }
		
			
		}
	  return null;
	}
	public Document getDoc() {
		return doc;
	}
	public void setDoc(Document doc) {
		this.doc = doc;
	}
	public static void main(String[] args) {
		String xmlstring="<?xml version=\"1.0\" encoding=\"UTF-8\" ?> <license><productInfo><product> <productGroup>LEAP</productGroup>  <productName>LEAP 2.0</productName>  <productSerial>18w155k1124278535703</productSerial>  </product> </productInfo><clientInfo> <clientName>1111</clientName>  <serverIP>127.0.0.1</serverIP>  <macAddress>******</macAddress>  </clientInfo><licenseInfo> <licenseType>Demo</licenseType>  <validDate>2006-2-13</validDate>  <workDate>2005-8-17</workDate> <concurrentUsers>25</concurrentUsers> </licenseInfo> </license>";
		HTTPXMLUtil util=new HTTPXMLUtil(xmlstring,"xml");
		System.out.println(util.getValue("license-licenseInfo-validDate"));
	}
}
