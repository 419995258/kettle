/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2014
 */

package org.my431.base.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUtils;

import org.my431.base.model.BaseArea;
import org.my431.base.model.BaseModule;
import org.my431.base.services.BaseModuleManager;
import org.my431.base.services.CacheBaseAreaManager;
import org.my431.base.services.PageManager;
import org.my431.platform.web.StrutsTreeEntityAction;
import org.my431.platform.dao.support.Page;
import org.my431.util.GeneralTreeNode;


import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ModelDriven;

import java.util.*;

/**
 * @author ice
 * @version 1.0
 * @since 1.0
 */


public class BaseModuleAction extends StrutsTreeEntityAction<BaseModule,BaseModuleManager> implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/base/BaseModule/query.jsp";
	protected static final String LIST_JSP= "/base/BaseModule/list.jsp";
	protected static final String ADD_JSP = "/base/BaseModule/create.jsp";
	protected static final String EDIT_JSP = "/base/BaseModule/edit.jsp";
	protected static final String SHOW_JSP = "/base/BaseModule/show.jsp";
	protected static final String INDEX_JSP = "/base/BaseModule/index.jsp";
	protected static final String TREE_JSP = "/base/BaseModule/tree.jsp";
	protected static final String FLUSH_JSP = "/base/BaseModule/flush.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/base/BaseModule/list.jspx";
	
	private BaseModuleManager baseModuleManager;
	
	private BaseModule baseModule;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			baseModule = new BaseModule();
		} else {
			baseModule = (BaseModule)baseModuleManager.get(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseModuleManager(BaseModuleManager manager) {
		this.baseModuleManager = manager;
	}	
	
	public Object getModel() {
		return baseModule;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		String queryParentCode=request.getParameter("queryParentCode");
		request.setAttribute("queryParentCode", queryParentCode);
		BaseModule query = new BaseModule();
		query.setParentCode(queryParentCode);
		int pageSize=30;
		int pageNo=1;
		if(request.getParameter("pageNo")!=null&&!request.getParameter("pageNo").equals("")){
			pageNo=Integer.valueOf(request.getParameter("pageNo"));
		}
		Page page = baseModuleManager.findPage(query,pageSize,pageNo);
		request.setAttribute("dataList", page.getResult());
		PageManager pm = new PageManager(Long.valueOf(page.getTotalCount()).intValue(), pageSize,pageSize);
		pm.goToPage(pageNo);
		request.setAttribute("pageHtml", pm.getPageCode());
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		return LIST_JSP;
	}
	
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String add() {
		request.setAttribute("queryParentCode", request.getParameter("queryParentCode"));
		return EDIT_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		baseModuleManager.save(baseModule);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		String queryParentCode=request.getParameter("queryParentCode");
		if(queryParentCode==null){
			queryParentCode="-1";
		}
		request.setAttribute("queryParentCode", queryParentCode);
		String select_id=request.getParameter("select_id");
		request.setAttribute("select_id", select_id);
		request.setAttribute("baseModule", baseModule);
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		String userId=request.getSession().getAttribute("wsuserId").toString();
		if(baseModule.getId()!=null&&baseModule.getId().trim().equals("")){
			baseModule.setId(null);
		}
		if(baseModule.getId()==null){
			baseModule.setCreTime(new Date());
			baseModule.setCreUser(userId);
		}else{
			baseModule.setModTime(new Date());
			baseModule.setModUser(userId);
		}
		baseModuleManager.save(baseModule);
		request.setAttribute("baseModule", baseModule);
		request.setAttribute("queryParentCode", baseModule.getParentCode());
		request.setAttribute("select_id", baseModule.getParentCode());
		return FLUSH_JSP;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			java.lang.String id = items[i];
			baseModuleManager.removeById(id);
		}
		request.setAttribute("queryParentCode", request.getParameter("queryParentCode"));
		request.setAttribute("select_id", request.getParameter("queryParentCode"));
		return FLUSH_JSP;
	}

	public String index() {
		String queryParentCode=request.getParameter("queryParentCode");
		if(queryParentCode==null){
			queryParentCode="-1";
		}
		request.setAttribute("queryParentCode", queryParentCode);
		String select_id=request.getParameter("select_id");
		request.setAttribute("select_id", select_id);
		request.setAttribute("menuFlag", "module");
		return INDEX_JSP;
	}
	public String tree() {
		String select_id=request.getParameter("select_id");
		request.setAttribute("select_id", select_id);
		String queryParentCode=request.getParameter("queryParentCode");
		if(queryParentCode==null){
			queryParentCode="-1";
		}
		BaseModule module=new BaseModule ();
		if(queryParentCode.equals("-1")){
			request.setAttribute("areaName", "模块");
		}else{
			module = baseModuleManager.getBaseModuleCode(queryParentCode);
			request.setAttribute("areaName", module.getModuleName());
		}
		request.setAttribute("dataList", baseModuleManager.tree);
		request.setAttribute("queryParentCode", queryParentCode);
		request.setAttribute("errorMsg", request.getParameter("errorMsg"));
		return TREE_JSP;
	}
}
