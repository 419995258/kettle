package org.my431.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.UIBean;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name="my431", tldTagClass="limitstudy.corestruts2.tag.MMTag", description="My431")
public class My431 extends UIBean  {
	
	private String value; 
	private String code; 
	
	
	public My431(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getDefaultTemplate() {
		// TODO Auto-generated method stub
		return "my431";
	}
	
	@StrutsTagAttribute(description="code", type="String")  
	public void setCode(String code) {
		this.code = code;  
	}
	
	@StrutsTagAttribute(description="value", type="String") 
	public void setValue(String value) {
		this.value = value;
	} 
	
	@Override  
	protected void evaluateExtraParams() {  
	super.evaluateExtraParams();  
	
		if (null != value) {  
		     addParameter("value", findString(value));  
		} 
	}



}
