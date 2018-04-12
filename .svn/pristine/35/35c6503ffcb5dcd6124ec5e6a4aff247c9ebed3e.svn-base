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

import org.my431.base.model.BaseRoleUrlMap;
import org.my431.base.services.BaseRoleUrlMapManager;
import org.my431.platform.web.StrutsTreeEntityAction;
import org.my431.platform.dao.support.Page;


import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ModelDriven;

import java.util.*;

/**
 * @author ice
 * @version 1.0
 * @since 1.0
 */


public class BaseRoleUrlMapAction extends StrutsTreeEntityAction<BaseRoleUrlMap,BaseRoleUrlMapManager> implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/MIS_BASE/BaseRoleUrlMap/query.jsp";
	protected static final String LIST_JSP= "/MIS_BASE/BaseRoleUrlMap/list.jsp";
	protected static final String ADD_JSP = "/MIS_BASE/BaseRoleUrlMap/create.jsp";
	protected static final String EDIT_JSP = "/MIS_BASE/BaseRoleUrlMap/edit.jsp";
	protected static final String SHOW_JSP = "/MIS_BASE/BaseRoleUrlMap/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/MIS_BASE/BaseRoleUrlMap/list.jspx";
	
	private BaseRoleUrlMapManager baseRoleUrlMapManager;
	
	private BaseRoleUrlMap baseRoleUrlMap;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			baseRoleUrlMap = new BaseRoleUrlMap();
		} else {
			baseRoleUrlMap = (BaseRoleUrlMap)baseRoleUrlMapManager.get(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseRoleUrlMapManager(BaseRoleUrlMapManager manager) {
		this.baseRoleUrlMapManager = manager;
	}	
	
	public Object getModel() {
		return baseRoleUrlMap;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		BaseRoleUrlMap query = new BaseRoleUrlMap();
		int pageSize=10;
		int pageNo=1;
		Page page = baseRoleUrlMapManager.findPage(query,pageSize,pageNo);
		//savePage(page,query);
		return LIST_JSP;
	}
	
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String add() {
		return ADD_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		String checked=request.getParameter("checked");
		String unchecked=request.getParameter("unchecked");
		String roleCode=request.getParameter("roleCode");
		String roleId=request.getParameter("roleId");
		if(checked!=null&&!checked.trim().equals("")){
			String[] checkedIds=checked.split(",");
			for(String urlId:checkedIds){
				BaseRoleUrlMap baseRoleUrlMap=new BaseRoleUrlMap();
				baseRoleUrlMap.setRoleCode(roleCode);
				baseRoleUrlMap.setRoleId(roleId);
				baseRoleUrlMap.setUrlId(urlId);
				baseRoleUrlMapManager.save(baseRoleUrlMap);
			}
		}
		if(unchecked!=null&&!unchecked.trim().equals("")){
			List<BaseRoleUrlMap> list=baseRoleUrlMapManager.findBy("roleId", roleId);
			for(BaseRoleUrlMap o:list){
				if(unchecked.contains(o.getUrlId())){
					baseRoleUrlMapManager.remove(o);
				}
			}
		}
		
		return null;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		baseRoleUrlMapManager.update(this.baseRoleUrlMap);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.String id = new java.lang.String((String)params.get("mapId"));
			baseRoleUrlMapManager.removeById(id);
		}
		return LIST_ACTION;
	}

}
