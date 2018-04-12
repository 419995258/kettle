/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package org.my431.base.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.my431.base.model.BaseModLog;
import org.my431.base.model.BaseUser;
import org.my431.base.services.BaseModLogManager;
import org.my431.base.services.PageManager;
import org.my431.platform.dao.support.Page;
import org.my431.platform.web.StrutsTreeEntityAction;
import org.my431.util.DateFormater;
import org.my431.util.MMap;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author wangzhen
 * @version 1.0
 * @since 1.0
 */


public class BaseModLogAction extends StrutsTreeEntityAction<BaseModLog,BaseModLogManager> implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "//base/BaseModLog/query.jsp";
	protected static final String LIST_JSP= "//base/BaseModLog/list.jsp";
	//学分项目修改日志
	protected static final String SCHOOL_PRO_LIST_JSP= "//base/BaseModLog/school_pro_list.jsp";
	
	protected static final String CREATE_JSP = "//base/BaseModLog/create.jsp";
	protected static final String EDIT_JSP = "//base/BaseModLog/edit.jsp";
	protected static final String SHOW_JSP = "//base/BaseModLog/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!//base/BaseModLog/list.jspx";
	
	private BaseModLogManager baseModLogManager;
	
	private BaseModLog baseModLog;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			baseModLog = new BaseModLog();
		} else {
			baseModLog = (BaseModLog)baseModLogManager.get(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseModLogManager(BaseModLogManager manager) {
		this.baseModLogManager = manager;
	}	
	
	public Object getModel() {
		return baseModLog;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		Object o=request.getSession().getAttribute("ssoUser");
		String sflag = request.getParameter("sflag");
		if(o!=null){
			BaseUser baseUser=(BaseUser)o;
			request.setAttribute("baseUser", baseUser);
			BaseModLog query = new BaseModLog();
			if ("schoolPro".equals(sflag)) {
				String pid = request.getParameter("pid");
				query.setSheetName("BASE_CREDIT_PROJECT");
				query.setPrimKey(pid);
				query.setDelFlag(0);
			}
			
			int pageNo=1;
			if(request.getParameter("pageNo")!=null&&!request.getParameter("pageNo").equals("")){
				pageNo=Integer.valueOf(request.getParameter("pageNo"));
			}
			int pageSize=10;
			if(request.getParameter("pageSize")!=null&&!request.getParameter("pageSize").equals("")){
				pageSize=Integer.valueOf(request.getParameter("pageSize"));
			}
			Page page = baseModLogManager.findPage(query,pageSize,pageNo);
			PageManager pm = new PageManager((int)page.getTotalCount(), pageSize,pageSize);
			pm.goToPage(pageNo);
			request.setAttribute("dataList", page.getResult());   
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageHtml", pm.getPageCode());
		}
	    if ("schoolPro".equals(sflag)) {
			return SCHOOL_PRO_LIST_JSP;
		}
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
		baseModLogManager.save(baseModLog);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		request.setAttribute("baseModLog", baseModLog);
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		baseModLogManager.update(this.baseModLog);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		baseModLogManager.removeById(id);
		return LIST_ACTION;
	}
	/**
	 * 
     *类的描述:updateModLogInfo<br/>
     *功能描述 ：花名册教师修改审核<br/>
     *作者:hyl<br/>
     *创建日期:2017-06-28<br/>
     *修改人：<br/>
     *修改日期：<br/>
     *修改原因描述:<br/>
	 * 
	 */
	public void updateModLogInfo() {
		Object o=request.getSession().getAttribute("ssoUser");
		if(o!=null){
			BaseUser baseUser=(BaseUser)o;
			String modLogId = request.getParameter("modLogId");
			String groupId = request.getParameter("groupId");
			if (StringUtils.isNotBlank(modLogId)) {
				//BaseModLog baseModLog = baseModLogManager.get(modLogId);
				Map<String, Object> newValueMap = new HashMap<String, Object>();
				Map<String, Object> termValues_v1 = new HashMap<String, Object>();
				Map<String, Object> termValues = new HashMap<String, Object>();
				termValues.put("sheetName", "BASE_PRO_TEACHER_MAP");
				termValues.put("delFlag", "0");
				termValues.put("fieldsGroupId", groupId);
				List<Map<String, Object>> list = baseModLogManager.getBaseModLogList(termValues, "", "");
				if (MMap.validateList(list)) {
					String pkey = "";
					for (Map<String, Object> map : list) {
						pkey = map.get("PRIM_KEY").toString();
						newValueMap.put(map.get("FIELD_NAME").toString(), map.get("FIELD_NEW_VALUE"));
					}
					termValues_v1.put("PRO_MAP_ID", pkey);
					
					//更新修改记录审核状态
					Map<String, Object> termValues_1 = new HashMap<String, Object>();
					Map<String, Object> columValues_1 = new HashMap<String, Object>();
					termValues_1.put("SHEET_NAME", "BASE_PRO_TEACHER_MAP");
					termValues_1.put("FIELDS_GROUP_ID", groupId);
					termValues_1.put("DEL_FLAG", "0");
					
					columValues_1.put("AUDIT_STATUS", 1);
					columValues_1.put("AUDIT_TIME", "to_date('"+DateFormater.DateToString(new Date(), "yyyy-MM-dd HH:mm:ss")+"','yyyy-MM-dd:hh24:mi:ss')");
					columValues_1.put("AUDIT_USER_ID", baseUser.getId());
					try {
						//更新值
						baseModLogManager.updateModLogAfterAudit(newValueMap, termValues_v1, "BASE_PRO_TEACHER_MAP",pkey,"modify.audit.business.01",baseUser.getId());
						//更新修改记录审核状态
						baseModLogManager.updateData(columValues_1, termValues_1, "BASE_MOD_LOG");
						renderHtml(response, "1");
					} catch (Exception e) {
						// TODO: handle exception
						renderHtml(response, "0");
						e.printStackTrace();
					}
				}
				
			}
		}
	}
}
