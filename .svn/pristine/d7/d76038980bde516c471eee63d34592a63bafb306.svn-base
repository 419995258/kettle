/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package org.my431.base.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.my431.base.model.BaseTableUpdateLog;
import org.my431.base.services.BaseTableUpdateLogManager;

import org.my431.base.services.PageManager;
import org.my431.platform.web.StrutsTreeEntityAction;
import org.my431.platform.dao.support.Page;


import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ModelDriven;

import java.util.*;

/**
 * @author wangzhen
 * @version 1.0
 * @since 1.0
 */


public class BaseTableUpdateLogAction extends StrutsTreeEntityAction<BaseTableUpdateLog,BaseTableUpdateLogManager> implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "//base/BaseTableUpdateLog/query.jsp";
	protected static final String LIST_JSP= "//base/BaseTableUpdateLog/list.jsp";
	protected static final String CREATE_JSP = "//base/BaseTableUpdateLog/create.jsp";
	protected static final String EDIT_JSP = "//base/BaseTableUpdateLog/edit.jsp";
	protected static final String SHOW_JSP = "//base/BaseTableUpdateLog/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!//base/BaseTableUpdateLog/list.jspx";
	
	private BaseTableUpdateLogManager baseTableUpdateLogManager;
	
	private BaseTableUpdateLog baseTableUpdateLog;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			baseTableUpdateLog = new BaseTableUpdateLog();
		} else {
			baseTableUpdateLog = (BaseTableUpdateLog)baseTableUpdateLogManager.get(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseTableUpdateLogManager(BaseTableUpdateLogManager manager) {
		this.baseTableUpdateLogManager = manager;
	}	
	
	public Object getModel() {
		return baseTableUpdateLog;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		BaseTableUpdateLog query = new BaseTableUpdateLog();
		
		int pageNo=1;
		if(request.getParameter("pageNo")!=null&&!request.getParameter("pageNo").equals("")){
			pageNo=Integer.valueOf(request.getParameter("pageNo"));
		}
		int pageSize=10;
		if(request.getParameter("pageSize")!=null&&!request.getParameter("pageSize").equals("")){
			pageSize=Integer.valueOf(request.getParameter("pageSize"));
		}
		Page page = baseTableUpdateLogManager.findPage(query,pageSize,pageNo);
		PageManager pm = new PageManager((int)page.getTotalCount(), pageSize,pageSize);
		pm.goToPage(pageNo);
		request.setAttribute("dataList", page.getResult());   
	    request.setAttribute("pageNo", pageNo);
	    request.setAttribute("pageSize", pageSize);
	    request.setAttribute("pageHtml", pm.getPageCode());
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
		baseTableUpdateLogManager.save(baseTableUpdateLog);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		request.setAttribute("baseTableUpdateLog", baseTableUpdateLog);
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		baseTableUpdateLogManager.update(this.baseTableUpdateLog);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		baseTableUpdateLogManager.removeById(id);
		return LIST_ACTION;
	}

}
