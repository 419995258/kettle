package org.my431.base.web;

import java.util.Date;
import java.util.List;

import org.my431.base.model.BaseRole;
import org.my431.base.model.BaseRoleUrlMap;
import org.my431.base.services.BaseModuleManager;
import org.my431.base.services.BaseRoleManager;
import org.my431.base.services.BaseRoleUrlMapManager;
import org.my431.base.services.BaseUrlManager;
import org.my431.base.services.PageManager;
import org.my431.platform.dao.support.Page;
import org.my431.platform.web.StrutsTreeEntityAction;

/**
 * @author wangzhen
 * @version 1.0
 * @since 1.0
 */

public class BaseRoleAction extends StrutsTreeEntityAction<BaseRole,BaseRoleManager>{
	private static final long serialVersionUID = 9185459445837615472L;

	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/base/BaseRole/role_query.jsp";
	protected static final String LIST_JSP= "/base/BaseRole/role_list.jsp";
	protected static final String CREATE_JSP = "/base/BaseRole/role_add.jsp";
	protected static final String EDIT_JSP = "/base/BaseRole/role_edit.jsp";
	protected static final String SHOW_JSP = "/base/BaseRole/role_show.jsp";
	protected static final String OPRN_URL_LIST_JSP = "/base/BaseRole/url_list.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/base/BaseRole/list.jspx";
	
	private BaseRoleManager baseRoleManager;
	
	private BaseRoleUrlMapManager baseRoleUrlMapManager;
	
	public void setBaseRoleUrlMapManager(BaseRoleUrlMapManager baseRoleUrlMapManager) {
		this.baseRoleUrlMapManager = baseRoleUrlMapManager;
	}

	private BaseUrlManager baseUrlManager;
	
	public void setBaseUrlManager(BaseUrlManager baseUrlManager) {
		this.baseUrlManager = baseUrlManager;
	}
	
	private BaseModuleManager baseModuleManager;

	public void setBaseModuleManager(BaseModuleManager baseModuleManager) {
		this.baseModuleManager = baseModuleManager;
	}

	private BaseRole baseRole;
	java.lang.String id = null;
	private String[] items;
	private String[] transform;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			baseRole = new BaseRole();
		} else {
			if(!id.contains(",")){
				baseRole = (BaseRole)baseRoleManager.get(id);
			}
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseRoleManager(BaseRoleManager manager) {
		this.baseRoleManager = manager;
	}	
	
	public Object getModel() {
		return baseRole;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		BaseRole query = new BaseRole();
		int pageSize=request.getParameter("pageSize")!=null?Integer.valueOf(request.getParameter("pageSize")):15;
		int pageNo=1;
		if(request.getParameter("pageNo")!=null&&!request.getParameter("pageNo").equals("")){
			pageNo=Integer.valueOf(request.getParameter("pageNo"));
		}
		
		Page page = baseRoleManager.findPage(query,pageSize,pageNo);
		request.setAttribute("dataList", page.getResult());
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNo", pageNo);
		PageManager pm = new PageManager(Long.valueOf(page.getTotalCount()).intValue(), pageSize,10);
		pm.goToPage(pageNo);
		request.setAttribute("pageHtml", pm.getPageCode());
		//savePage(page,query);
		
		request.setAttribute("menuFlag", "role");
		return LIST_JSP;
	}
	
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		String roleId="";
		request.setAttribute("otherRoleList", baseRoleManager.getBaseRoleList("add", roleId));
		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		baseRole.setCreTime(new Date());
		baseRole.setModTime(new Date());
		String[] transformArrays = request.getParameterValues("transform");

		if(transformArrays!=null){
			String transformValue="";
			for(int i=0; i<transformArrays.length; i++)
			{
				if(i==0)
					transformValue=transformValue+transformArrays[i];
				else
					transformValue=transformValue+","+transformArrays[i];
			}
			baseRole.setTransformRole(transformValue);
		}
		baseRoleManager.save(baseRole);
		baseRoleManager.updateOne(baseRole);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		String roleId=id;
		request.setAttribute("otherRoleList", baseRoleManager.getBaseRoleList("edit", roleId));
		String transform=baseRole.getTransformRole()!=null?baseRole.getTransformRole():"";
		String[] transformArrays=transform.split(",");
		request.setAttribute("transformArrays", transformArrays);
		
		//以下代码对应到role_edit.jsp 也可以实现复选框选中s:checkboxlist 中的value是#request.transformList
/*		List transformList = new ArrayList();
		for(int i=0; i<transformArrays.length; i++){
			transformList.add(transformArrays[i]);
		}
		request.setAttribute("transformList", transformList);*/
		
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		baseRole.setModTime(new Date());
		String[] transformArrays = request.getParameterValues("transform");
		String transformValue="";
		if(transformArrays!=null){
			for(int i=0; i<transformArrays.length; i++)
			{
				if(i==0)
					transformValue=transformValue+transformArrays[i];
				else
					transformValue=transformValue+","+transformArrays[i];
			}
			baseRole.setTransformRole(transformValue);
		}else{
			baseRole.setTransformRole("");
		}
		
		baseRoleManager.update(this.baseRole);
		baseRoleManager.updateOne(baseRole);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		String[] ids=request.getParameterValues("id");
		for(int i = 0; i < ids.length; i++) {
			java.lang.String id = ids[i];
			baseRoleManager.delOne(baseRoleManager.get(id));
			baseRoleManager.removeById(id);
		}
		return LIST_ACTION;
	}

	public BaseRole getBaseRole() {
		return baseRole;
	}

	public void setBaseRole(BaseRole baseRole) {
		this.baseRole = baseRole;
	}

	public String[] getTransform() {
		return transform;
	}

	public void setTransform(String[] transform) {
		this.transform = transform;
	}
	
	
	public String openUrlList() {
		request.setAttribute("dataList", baseModuleManager.tree);
		request.setAttribute("id", id);
		request.setAttribute("baseRole", baseRole);
		List<BaseRoleUrlMap> rum=baseRoleUrlMapManager.findBy("roleId", baseRole.getId());
		String ids="";
		for(BaseRoleUrlMap o:rum){
			if(ids.equals("")){
				ids=o.getUrlId();
			}else{
				ids=ids+","+o.getUrlId();
			}
		}
		request.setAttribute("ids", ids);
		return OPRN_URL_LIST_JSP;
	}

}
