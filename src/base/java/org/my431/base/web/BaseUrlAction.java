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

import org.apache.commons.lang.StringUtils;
import org.my431.base.model.BaseUrl;
import org.my431.base.services.BaseModuleManager;
import org.my431.base.services.BaseUrlManager;
import org.my431.base.services.PageManager;
import org.my431.platform.web.StrutsTreeEntityAction;
import org.my431.platform.dao.support.Page;
import org.my431.util.MMap;


import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ModelDriven;

import java.util.*;

/**
 * @author ice
 * @version 1.0
 * @since 1.0
 */


public class BaseUrlAction extends StrutsTreeEntityAction<BaseUrl,BaseUrlManager> implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/base/BaseUrl/query.jsp";
	protected static final String LIST_JSP= "/base/BaseUrl/list.jsp";
	protected static final String ADD_JSP = "/base/BaseUrl/create.jsp";
	protected static final String EDIT_JSP = "/base/BaseUrl/edit.jsp";
	protected static final String SHOW_JSP = "/base/BaseUrl/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/base/BaseUrl/list.jspx";
	//加日志的url列表
	protected static final String LIST_LOGURL_YES_JSP= "/base/BaseUrl/listoflogurl.jsp";
	//未加日志的url列表
	protected static final String LIST_LOGURL_NO_JSP= "/base/BaseUrl/listOfNoLogUrl.jsp";
	//系统日志首页
	protected static final String SYS_LOGINDEX_JSP= "/base/BaseUrl/xtrz_index.jsp";
	
	private BaseUrlManager baseUrlManager;
	private BaseModuleManager baseModuleManager;
	
	private BaseUrl baseUrl;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			baseUrl = new BaseUrl();
		} else {
			baseUrl = (BaseUrl)baseUrlManager.get(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseUrlManager(BaseUrlManager manager) {
		this.baseUrlManager = manager;
	}	
	
	public void setBaseModuleManager(BaseModuleManager baseModuleManager) {
		this.baseModuleManager = baseModuleManager;
	}

	public Object getModel() {
		return baseUrl;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		String logflag=request.getParameter("logflag");
		String muId=request.getParameter("muId");
		request.setAttribute("moduleId", muId);
		int pageSize=10;
		int pageNo=1;
		if(request.getParameter("pageNo")!=null&&!request.getParameter("pageNo").equals("")){
			pageNo=Integer.valueOf(request.getParameter("pageNo"));
		}
		BaseUrl query = new BaseUrl();
		if(StringUtils.isNotBlank(logflag)){
			if("1".equals(logflag)){
				query.setIsAddLog("1");
			}
			if("0".equals(logflag)){
				query.setIsAddLog("0");
				if(StringUtils.isNotBlank(muId)){
					query.setModuleId(muId);
				}
				pageSize=100000;
			}
		}else {
			query.setModuleId(muId);
		}
		Page page = baseUrlManager.findPage(query,pageSize,pageNo);
		request.setAttribute("dataList", page.getResult());
		PageManager pm = new PageManager(Long.valueOf(page.getTotalCount()).intValue(), pageSize,pageSize);
		pm.goToPage(pageNo);
		request.setAttribute("pageHtml", pm.getPageCode());
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		String redirectUrl="";
		if(StringUtils.isNotBlank(logflag)){
			if("1".equals(logflag)){
				redirectUrl=LIST_LOGURL_YES_JSP;
			}
			if("0".equals(logflag)){
				//List<MMap> urlList=baseModuleManager.getBaseModuleTreeList();
				//request.setAttribute("urlList", urlList);
				request.setAttribute("urlList", baseModuleManager.tree);
				redirectUrl=LIST_LOGURL_NO_JSP;
			}
			return redirectUrl;
		}else {
			return LIST_JSP;
		}
		
	}
	
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String add() {
		request.setAttribute("moduleId", request.getParameter("moduleId"));
		return EDIT_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		baseUrlManager.save(baseUrl);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		request.setAttribute("baseUrl", baseUrl);
		request.setAttribute("moduleId", baseUrl.getModuleId());
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		String userId=request.getSession().getAttribute("wsuserId").toString();
		if(baseUrl.getId()!=null&&baseUrl.getId().trim().equals("")){
			baseUrl.setId(null);
		}
		if(baseUrl.getId()==null){
			baseUrl.setCreTime(new Date());
			baseUrl.setCreUser(userId);
			baseUrl.setClickCount(0);
			baseUrl.setIsAddLog("0");
		}else{
			baseUrl.setModTime(new Date());
			baseUrl.setModUser(userId);
		}
		baseUrlManager.save(baseUrl);
		return LIST_ACTION+"?muId="+baseUrl.getModuleId();
	}
	
	/**删除对象*/
	public String delete() {
		String moduleId=request.getParameter("moduleId");
		for(int i = 0; i < items.length; i++) {
			java.lang.String id = items[i];
			baseUrlManager.removeById(id);
		}
		return LIST_ACTION+"?muId="+moduleId;
	}
	/**系统日志首页*/
	public String sysLogIndex() {
		
		return SYS_LOGINDEX_JSP;
	}
	/**操作方法*/
	public void doOperate(){
		String flag=request.getParameter("flag");
		if("delAddLog".equals(flag)){//日志功能移除
			String urlId=request.getParameter("urlId");
			if(StringUtils.isNotBlank(urlId)){
				BaseUrl baseUrl = (BaseUrl)baseUrlManager.get(urlId);
				baseUrl.setIsAddLog("0");
				try {
					baseUrlManager.update(baseUrl);
					renderHtml(response, "0");
				} catch (Exception e) {
					// TODO: handle exception
					renderHtml(response, "1");
					e.printStackTrace();
				}
			}
		}
		if("addLog".equals(flag)){//日志功能增加
			String urlstr=request.getParameter("urlstr");
			if(StringUtils.isNotBlank(urlstr)){
				urlstr=urlstr.substring(0, urlstr.length()-1);
				String[] urlstrv=urlstr.split(",");
				for (int i = 0; i < urlstrv.length; i++) {
					String urlId = urlstrv[i];
					BaseUrl baseUrl = (BaseUrl)baseUrlManager.get(urlId);
					baseUrl.setIsAddLog("1");
					baseUrlManager.update(baseUrl);
				}
			}
		}
		
		
	}
}
