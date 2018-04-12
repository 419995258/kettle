/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package org.my431.base.web;

import org.my431.base.model.BaseCreditTransRule;
import org.my431.base.services.BaseCreditTransRuleManager;
import org.my431.base.services.PageManager;
import org.my431.platform.dao.support.Page;
import org.my431.platform.web.StrutsTreeEntityAction;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author wangzhen
 * @version 1.0
 * @since 1.0
 */


public class BaseCreditTransRuleAction extends StrutsTreeEntityAction<BaseCreditTransRule,BaseCreditTransRuleManager> implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "//base/BaseCreditTransRule/query.jsp";
	protected static final String LIST_JSP= "//base/BaseCreditTransRule/list.jsp";
	protected static final String CREATE_JSP = "//base/BaseCreditTransRule/create.jsp";
	protected static final String EDIT_JSP = "//base/BaseCreditTransRule/edit.jsp";
	protected static final String SHOW_JSP = "//base/BaseCreditTransRule/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!//base/BaseCreditTransRule/list.jspx";
	
	private BaseCreditTransRuleManager baseCreditTransRuleManager;
	
	private BaseCreditTransRule baseCreditTransRule;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			baseCreditTransRule = new BaseCreditTransRule();
		} else {
			baseCreditTransRule = (BaseCreditTransRule)baseCreditTransRuleManager.get(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseCreditTransRuleManager(BaseCreditTransRuleManager manager) {
		this.baseCreditTransRuleManager = manager;
	}	
	
	public Object getModel() {
		return baseCreditTransRule;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		BaseCreditTransRule query = new BaseCreditTransRule();
		
		int pageNo=1;
		if(request.getParameter("pageNo")!=null&&!request.getParameter("pageNo").equals("")){
			pageNo=Integer.valueOf(request.getParameter("pageNo"));
		}
		int pageSize=10;
		if(request.getParameter("pageSize")!=null&&!request.getParameter("pageSize").equals("")){
			pageSize=Integer.valueOf(request.getParameter("pageSize"));
		}
		Page page = baseCreditTransRuleManager.findPage(query,pageSize,pageNo);
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
		baseCreditTransRuleManager.save(baseCreditTransRule);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		request.setAttribute("baseCreditTransRule", baseCreditTransRule);
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		baseCreditTransRuleManager.update(this.baseCreditTransRule);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		baseCreditTransRuleManager.removeById(id);
		return LIST_ACTION;
	}

}
