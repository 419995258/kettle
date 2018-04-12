/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2012
 */

package org.my431.base.web;

import java.net.MalformedURLException;
import java.util.Date;

import org.apache.xmlrpc.XmlRpcException;
import org.my431.base.model.BaseCache;
import org.my431.base.services.BaseCacheManager;
import org.my431.base.services.PageManager;
import org.my431.platform.dao.support.Page;
import org.my431.platform.web.StrutsTreeEntityAction;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author ice
 * @version 1.0
 * @since 1.0
 */


public class BaseCacheAction extends StrutsTreeEntityAction<BaseCache,BaseCacheManager> implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/base/baseCache/query.jsp";
	protected static final String LIST_JSP= "/base/baseCache/list.jsp";
	protected static final String ADD_JSP = "/base/baseCache/create.jsp";
	protected static final String EDIT_JSP = "/base/baseCache/edit.jsp";
	protected static final String SHOW_JSP = "/base/baseCache/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/base/BaseCache/list.jspx";
	
	private BaseCacheManager baseCacheManager;
	
	private BaseCache baseCache;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			baseCache = new BaseCache();
		} else {
			baseCache = (BaseCache)baseCacheManager.get(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseCacheManager(BaseCacheManager manager) {
		this.baseCacheManager = manager;
	}	
	
	public Object getModel() {
		return baseCache;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		BaseCache query = new BaseCache();
		int pageSize=15;
		int pageNo=1;
		if(request.getParameter("pageNo")!=null&&!request.getParameter("pageNo").equals("")){
			pageNo=Integer.valueOf(request.getParameter("pageNo"));
		}
		Page page = baseCacheManager.findPage(query,pageSize,pageNo);
		request.setAttribute("dataList", page.getResult());
		PageManager pm = new PageManager(Long.valueOf(page.getTotalCount()).intValue(), pageSize,pageSize);
		pm.goToPage(pageNo);
		request.setAttribute("pageHtml", pm.getPageCode());
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
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
		baseCache.setCreTime(new Date());
		baseCacheManager.save(baseCache);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		request.setAttribute("baseCache", baseCache);
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		baseCache.setModTime(new Date());
		baseCacheManager.update(this.baseCache);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		baseCacheManager.removeById(id);
		return LIST_ACTION;
	}
	
	public String reloadRedisByObject()
	{
		String keyValue=baseCache.getCachePreKey();
		String[] objectKey=keyValue.split("\\.");
		String objectValue=objectKey[2];
	    String appName=objectKey[1];
//	    BaseXmlRpcClient baseXmlRpcClient=new BaseXmlRpcClient();
//	    try {
//			baseXmlRpcClient.reloadRedis(objectValue,appName);
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (XmlRpcException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	    return null;
	}

}
