/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2012
 */

package org.my431.base.web;

import java.util.Date;
import java.util.List;

import org.my431.base.model.BaseQuery;
import org.my431.base.model.BaseUser;
import org.my431.base.services.BaseQueryManager;
import org.my431.base.services.CacheBaseQueryManager;
import org.my431.base.services.PageManager;
import org.my431.platform.dao.support.Page;
import org.my431.platform.web.StrutsTreeEntityAction;
import org.my431.plugin.redis.services.RedisManager;
import org.my431.util.MMap;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author ice
 * @version 1.0
 * @since 1.0
 */


public class BaseQueryAction extends StrutsTreeEntityAction<BaseQuery,BaseQueryManager> implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/base/baseQuery/query.jsp";
	protected static final String LIST_JSP= "/base/baseQuery/list.jsp";
	protected static final String ADD_JSP = "/base/baseQuery/create.jsp";
	protected static final String EDIT_JSP = "/base/baseQuery/edit.jsp";
	protected static final String SHOW_JSP = "/base/baseQuery/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/base/BaseQuery/list.jspx";
	
	private BaseQueryManager baseQueryManager;
	private CacheBaseQueryManager cacheBaseQueryManager;
	private RedisManager redisManager;
	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}
	private BaseQuery baseQuery;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			baseQuery = new BaseQuery();
		} else {
			baseQuery = (BaseQuery)baseQueryManager.get(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseQueryManager(BaseQueryManager manager) {
		this.baseQueryManager = manager;
	}	
	
	public void setCacheBaseQueryManager(CacheBaseQueryManager manager) {
		this.cacheBaseQueryManager = manager;
	}
	
	public Object getModel() {
		return baseQuery;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		int pageSize=15;
		int pageNo=1;
		if(request.getParameter("pageNo")!=null&&!request.getParameter("pageNo").equals("")){
			pageNo=Integer.valueOf(request.getParameter("pageNo"));
		}
		String searchValue=request.getParameter("searchValue");
		if (searchValue==null)
		{
			searchValue="";
		}
		BaseQuery query=new BaseQuery();
		query.setQueryName(searchValue);
		Page page = baseQueryManager.findPage(query,pageSize,pageNo);
		request.setAttribute("dataList", page.getResult());
		PageManager pm = new PageManager(Long.valueOf(page.getTotalCount()).intValue(), pageSize,pageSize);
		pm.goToPage(pageNo);
		request.setAttribute("pageHtml", pm.getPageCode());
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("searchValue", searchValue);
		request.setAttribute("menuFlag", "query");
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
	public void save() {
		String queryName = request.getParameter("queryName");
		List<BaseQuery> list = baseQueryManager.getBaseQueryByQueryName(queryName);
		if (MMap.validateList(list)) {
			this.renderHtml(response, "0");
		}else {
			baseQuery.setCreTime(new Date());
			baseQuery.setCreUser(((BaseUser)request.getSession().getAttribute("ssoUser")).getId());
			baseQueryManager.save(baseQuery);
			baseQueryManager.init();
			this.renderHtml(response, "1");
		}
		//return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		request.setAttribute("baseQuery", baseQuery);
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public void update() {
		boolean flag = true;
		String queryName1 = request.getParameter("queryName1");//原先的queryName
		String queryName = request.getParameter("queryName");//现在的queryName
		if (!queryName.equals(queryName1)) {
			List<BaseQuery> list = baseQueryManager.getBaseQueryByQueryName(queryName);
			if (MMap.validateList(list)) {
				flag = false;
			}
		}
		if (flag) {
			baseQuery.setModTime(new Date());
			baseQuery.setModUser(((BaseUser)request.getSession().getAttribute("ssoUser")).getId());
			baseQueryManager.update(this.baseQuery);
			BaseQuery temp = cacheBaseQueryManager.getValue(cacheBaseQueryManager.idKey+baseQuery.getId());
			if(temp!=null&&!temp.getQueryName().equals(baseQuery.getQueryName())){
				cacheBaseQueryManager.removeValue(temp.getQueryName(),redisManager);
			}
			baseQueryManager.init();
			this.renderHtml(response, "1");
		}else {
			this.renderHtml(response, "0");
		}
		//return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		baseQueryManager.removeById(id);
		baseQueryManager.init();
		String pageNo=request.getParameter("pageNo");
		return LIST_ACTION+"?pageNo="+pageNo;
	}
}
