/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2012
 */

package org.my431.base.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUtils;

import org.my431.base.model.BaseNoteAttachment;
import org.my431.base.services.BaseNoteAttachmentManager;
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


public class BaseNoteAttachmentAction extends StrutsTreeEntityAction<BaseNoteAttachment,BaseNoteAttachmentManager> implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/base/BaseNoteAttachment/query.jsp";
	protected static final String LIST_JSP= "/base/BaseNoteAttachment/list.jsp";
	protected static final String ADD_JSP = "/base/BaseNoteAttachment/create.jsp";
	protected static final String EDIT_JSP = "/base/BaseNoteAttachment/edit.jsp";
	protected static final String SHOW_JSP = "/base/BaseNoteAttachment/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/base/BaseNoteAttachment/list.jspx";
	
	private BaseNoteAttachmentManager baseNoteAttachmentManager;
	
	private BaseNoteAttachment baseNoteAttachment;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			baseNoteAttachment = new BaseNoteAttachment();
		} else {
			baseNoteAttachment = (BaseNoteAttachment)baseNoteAttachmentManager.get(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseNoteAttachmentManager(BaseNoteAttachmentManager manager) {
		this.baseNoteAttachmentManager = manager;
	}	
	
	public Object getModel() {
		return baseNoteAttachment;
	}
	
	public void setFileId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		BaseNoteAttachment query = new BaseNoteAttachment();
		int pageSize=10;
		int pageNo=1;
		Page page = baseNoteAttachmentManager.findPage(query,pageSize,pageNo);
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
		baseNoteAttachmentManager.save(baseNoteAttachment);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		baseNoteAttachmentManager.update(this.baseNoteAttachment);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.String id = new java.lang.String((String)params.get("fileId"));
			baseNoteAttachmentManager.removeById(id);
		}
		return LIST_ACTION;
	}
	public String delAttachement(){
		String id = request.getParameter("attachmentid");
		baseNoteAttachmentManager.removeById(id);
		return null;
	}
	//action
}
