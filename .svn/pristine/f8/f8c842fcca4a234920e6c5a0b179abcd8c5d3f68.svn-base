/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2011
 */

package org.my431.base.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpUtils;

import org.my431.base.model.BaseProperties;
import org.my431.base.model.BasePropertiesGroup;
import org.my431.base.services.BasePropertiesGroupManager;
import org.my431.base.services.CacheBasePropertiesManager;
import org.my431.platform.web.StrutsTreeEntityAction;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author wangzhen
 * @version 1.0
 * @since 1.0
 */


public class BasePropertiesGroupAction extends StrutsTreeEntityAction<BasePropertiesGroup,BasePropertiesGroupManager> implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/pages/BasePropertiesGroup/query.jsp";
	protected static final String LIST_JSP= "/pages/BasePropertiesGroup/list.jsp";
	protected static final String CREATE_JSP = "/pages/BasePropertiesGroup/create.jsp";
	protected static final String EDIT_JSP = "/pages/BasePropertiesGroup/edit.jsp";
	protected static final String SHOW_JSP = "/pages/BasePropertiesGroup/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/pages/BasePropertiesGroup/list.jspx";
	
	private BasePropertiesGroupManager basePropertiesGroupManager;
	
	private BasePropertiesGroup basePropertiesGroup;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			basePropertiesGroup = new BasePropertiesGroup();
		} else {
			basePropertiesGroup = (BasePropertiesGroup)basePropertiesGroupManager.get(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBasePropertiesGroupManager(BasePropertiesGroupManager manager) {
		this.basePropertiesGroupManager = manager;
	}	
	
	public Object getModel() {
		return basePropertiesGroup;
	}
	
	public void setGid(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		BasePropertiesGroup query = new BasePropertiesGroup();
		int pageSize=10;
		int pageNo=1;
		//Page page = basePropertiesGroupManager.findPage(query,pageSize,pageNo);
		//savePage(page,query);
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
		basePropertiesGroupManager.save(basePropertiesGroup);
		Map<String, List<BaseProperties>> map=new HashMap<String, List<BaseProperties>>();
		map.put(basePropertiesGroup.getGroupKey(), new ArrayList<BaseProperties>());
		CacheBasePropertiesManager.setAllPropertiesMap(map);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		BasePropertiesGroup old=basePropertiesGroupManager.get(basePropertiesGroup.getId());
		CacheBasePropertiesManager.removeByGroupKey(old.getGroupKey());
		
		basePropertiesGroupManager.update(this.basePropertiesGroup);
		
		LinkedHashMap<String, List<BaseProperties>> map=new LinkedHashMap<String, List<BaseProperties>>();
		List<BaseProperties> list=map.get(old.getGroupKey());
		map.put(basePropertiesGroup.getGroupKey(), list);
		CacheBasePropertiesManager.setAllPropertiesMap(map);
		
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.String id = new java.lang.String((String)params.get("gid"));
			basePropertiesGroupManager.removeById(id);
		}
		return LIST_ACTION;
	}

}
