/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2015
 */

package org.my431.base.web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.my431.base.model.BaseArea;
import org.my431.base.model.StatLoginData;
import org.my431.base.services.CacheBaseAreaManager;
import org.my431.base.services.CacheBaseCacheManager;
import org.my431.base.services.PageManager;
import org.my431.base.services.StatLoginDataManager;
import org.my431.platform.dao.support.Page;
import org.my431.platform.web.StrutsTreeEntityAction;
import org.my431.plugin.redis.services.RedisManager;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author wangzhen
 * @version 1.0
 * @since 1.0
 */


public class StatLoginDataAction extends StrutsTreeEntityAction<StatLoginData,StatLoginDataManager> implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "//base/StatLoginData/query.jsp";
	protected static final String LIST_JSP= "//base/StatLoginData/list.jsp";
	protected static final String CREATE_JSP = "//base/StatLoginData/create.jsp";
	protected static final String EDIT_JSP = "//base/StatLoginData/edit.jsp";
	protected static final String SHOW_JSP = "//base/StatLoginData/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!//base/StatLoginData/list.jspx";
	
	private StatLoginDataManager statLoginDataManager;
	
	private StatLoginData statLoginData;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			statLoginData = new StatLoginData();
		} else {
			statLoginData = (StatLoginData)statLoginDataManager.get(id);
		}
	}
	private RedisManager redisManager;
	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setStatLoginDataManager(StatLoginDataManager manager) {
		this.statLoginDataManager = manager;
	}	
	
	public Object getModel() {
		return statLoginData;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		String areaId=request.getParameter("areaId");
		String userId=String.valueOf(request.getSession().getAttribute("wsuserId"));
		if(isNullOrEmptyString(areaId)){
			areaId=redisManager.getRedisUser(userId).getAreaId();
		}
		request.setAttribute("areaId", areaId);
		BaseArea area=CacheBaseAreaManager.getNodeById(areaId);
		List list = statLoginDataManager.loginUpdateTimeByParentCode(area.getAreaCode(), "t.area_code ");
		request.setAttribute("dataList", list);		
		List yue=new ArrayList();
		SimpleDateFormat matter=new SimpleDateFormat("MM");	   
	    //将calendar装换为Date类型
	    //将date类型转换为BigDecimal类型（该类型对应oracle中的number类型）
	    //获取当前时间的前6个月
	    for (int i =6; i >=0; i--) {
	    	 Calendar calendar = Calendar.getInstance();
	    	 calendar.add(Calendar.MONTH,-i);
		     Date date = calendar.getTime();
	    	 yue.add(new BigDecimal(matter.format(date)));
		}
	   request.setAttribute("yue", yue);
	   return LIST_JSP;
	}
	
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		statLoginDataManager.save(statLoginData);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		request.setAttribute("statLoginData", statLoginData);
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		statLoginDataManager.update(this.statLoginData);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		statLoginDataManager.removeById(id);
		return LIST_ACTION;
	}

}
