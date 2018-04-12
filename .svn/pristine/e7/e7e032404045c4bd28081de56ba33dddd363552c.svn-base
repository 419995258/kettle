package org.my431.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractUITag;
import org.my431.base.model.BaseProperties;
import org.my431.base.services.BaseAreaManager;
import org.my431.plugin.redis.services.RedisManager;

import com.opensymphony.xwork2.util.ValueStack;

public class My431Tag extends AbstractUITag {
	private BaseAreaManager baseAreaManager=(BaseAreaManager)org.my431.platform.utils.ContextUtils.getBean("baseAreaManager");
	/**
	 * 
	 */
	private static final long serialVersionUID = 1860015075932286266L;
	private String code; 
	private String value; 
	
	@Override
	public Component getBean(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return new My431(stack, request, response);
	}
	
	@Override  
	protected void populateParams() {  
		super.populateParams();  
	
		My431 mm = (My431)component;  
		mm.setValue(value);
	} 
	
	public void setCode(String code) {  
		RedisManager redisManager=(RedisManager)org.my431.platform.utils.ContextUtils.getBean("redisManager");
		this.code = code;  
		Object v=redisManager.getOValue("global.base.BaseProperties.key."+code);
		if(v!=null){
			this.setValue(((BaseProperties)v).getPropertyValue());
		}else{
			baseAreaManager.reload();
			v=redisManager.getOValue("global.base.BaseProperties.key."+code);
			if(v!=null){
				this.setValue(((BaseProperties)v).getPropertyValue());
			}else{
				this.setValue(code);
			}
		}
	}

	public void setValue(String value) {
		
		this.value = value;
	} 

}
