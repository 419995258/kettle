package org.my431.base.web;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.my431.base.model.BaseProperties;
import org.my431.base.model.BasePropertiesGroup;
import org.my431.base.services.BasePropertiesGroupManager;
import org.my431.base.services.BasePropertiesManager;
import org.my431.platform.web.StrutsEntityAction;
import org.my431.platform.web.StrutsTreeEntityAction;
import org.my431.util.ParamUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wangzhen
 * @version 1.0
 * @since 1.0
 */

public class BasePropertiesAction extends StrutsTreeEntityAction<BaseProperties, BasePropertiesManager> {
	private static final long serialVersionUID = 8694683272716549963L;

	// 默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null;

	// forward paths
	protected static final String LIST_JSP = "/base/BaseProperties/property_list.jsp";
	protected static final String EDIT_JSP = "/base/BaseProperties/property_form.jsp";
	protected static final String VIEW_JSP = "/base/BaseProperties/property_view.jsp";
	protected static final String ORDER_JSP = "/base/BaseProperties/property_order.jsp";
	protected static final String INDEX_JSP = "/base/BaseProperties/index.jsp";
	protected static final String TREE_INDEX_JSP = "/base/BaseProperties/tree_index.jsp";
	protected static final String SUCCESS_JSP = "/base/BaseProperties/flush_tree.jsp";
	
	protected static final String ADD_GROUP_JSP = "/base/BaseProperties/property_group_add_form.jsp";
	protected static final String EDIT_GROUP_JSP = "/base/BaseProperties/property_group_edit_form.jsp";
	protected static final String FLUSH_GROUP_JSP = "/base/BaseProperties/property_group_flush.jsp";
	protected static final String FLUSH_DELETE_GROUP_JSP = "/base/BaseProperties/property_group_delete_flush.jsp";
	protected static final String FLUSH_DELETE_GROUP_JSP1 = "/base/BaseProperties/property_group_delete_flush1.jsp";
	
	// redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/base/BaseProperties/list.jspx";

	private BasePropertiesManager basePropertiesManager;

	private BasePropertiesGroupManager basePropertiesGroupManager;
	
	public void setBasePropertiesManager(BasePropertiesManager basePropertiesManager) {
		this.basePropertiesManager = basePropertiesManager;
	}

	public void setBasePropertiesGroupManager(BasePropertiesGroupManager basePropertiesGroupManager) {
		this.basePropertiesGroupManager = basePropertiesGroupManager;
	}

	private BaseProperties baseProperties;
	private BasePropertiesGroup basePropertiesGroup;
	private List<BaseProperties> propertiesDataList;
	
	java.lang.String id = null;
	
	private String groupId;
	private String operater;
	
	/**
	 * 显示Welcome页的Action函数.
	 * @return String
	 */
	public String index() {
		passParams(request);//公共参数传递
		return INDEX_JSP;
	}
	
	public String treeIndex() {
		passParams(request);//公共参数传递
		request.setAttribute("nodeList", basePropertiesGroupManager.getTreeNodeList());
		request.setAttribute("code1", request.getParameter("code"));
		request.setAttribute("menuFlag","properties");
		return TREE_INDEX_JSP;
	}
	
	/**
	 * 列出所有对象的Action函数.
	 * @return String
	 */
	public String list() {
		passParams(request);
		String groupKey = request.getParameter("groupKey")!=null?request.getParameter("groupKey"):(String)request.getAttribute("groupKey");
		List<BaseProperties>  list = basePropertiesManager.getPropertiesList(groupKey,request.getSession().getServletContext().getRealPath("WEB-INF/conf/"));
		request.setAttribute("dataList", list);
		return LIST_JSP;
	}
	
	/**
	 * 显示新建对象Form的Action函数. 默认跳到
	 * {@link #edit(ActionMapping,ActionForm,HttpServletRequest,HttpServletResponse)}
	 */
	public String add() {
		refrenceData(request);
		return edit();
	}

	/**
	 * 显示修改对象Form的Action函数.
	 */
	public String edit() {

		String mysubject=request.getParameter("msubject");
		BaseProperties object = null;
		// 如果是修改操作，id!=null
		if (request.getParameter(idName) != null) {
			object = basePropertiesManager.get(request.getParameter(idName));
			if (object == null) {
				addFieldError("edit", "entity.missing");
				return LIST_JSP;
			}
			//request.setAttribute(getEntityName(), object);
			request.setAttribute("baseProperties", object);
		} else {
			object = new BaseProperties();
		}
        request.setAttribute("mysubject", mysubject);
		refrenceData(request);
		passParams(request);
		return EDIT_JSP;
	}

	/**
	 * 保存对象的Action函数.
	 */
	public String save() {

		BaseProperties object;
		// 如果是修改操作，id is not blank
		
		Boolean isAdd=true;

		if (StringUtils.isNotBlank(request.getParameter(idName))) {
			//object = doGetEntity(request);
			object = basePropertiesManager.get(request.getParameter(idName));
			if (object == null) {
				addFieldError("edit", "entity.missing");
				return LIST_JSP;
			}
			isAdd=false;
		} else { // 否则为新增操作
			object = new BaseProperties();
		}
		try {
			onInitEntity(request, object);
			//getEntityManager().save(object);
			basePropertiesManager.save(object);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			addFieldError("edit", e.getMessage());
			return EDIT_JSP;
		}
		doReload();//updateOne(object,request.getSession().getServletContext().getRealPath("WEB-INF/conf/"));

		refrenceData(request);
		passParams(request);
		
		return SUCCESS_JSP;
	}

	/**
	 * 删除单个对象的Action函数.
	 */
	public String delete() {
		refrenceData(request);

		try {
			doDeleteEntity(request);
		} catch (Exception e) {
			addFieldError("delete", e.getMessage());
		}

		passParams(request);
		return list();
	}

	/**
	 * 排序对象的Action函数.
	 */
	public String order() {
		passParams(request);
		List<BaseProperties> list = basePropertiesManager
				.getPropertiesList((String) request.getAttribute("groupKey"),request.getSession().getServletContext().getRealPath("WEB-INF/conf/"));
		setPropertiesDataList(list);
		// request.setAttribute("dataList", list); 使用这个也可以！
		return ORDER_JSP;
	}

	/**
	 * 排序业务对象的函数.
	 */
	protected void doSortEntity(Serializable[] ids,String userId) {
		if (ids != null) {
			String[] idStr = new String[ids.length];
			for (int i = 0; i < ids.length; i++) {
				idStr[i] = (String) ids[i];
			}
			basePropertiesManager.updateOrder(idStr,userId);
		}
	}

	/**
	 * 重载静态业务对象树的函数.
	 */
	@Override
	protected void doReload() {
		basePropertiesManager.reload();
	}

	/**
	 * 删除业务对象的函数.
	 */
	protected void doDeleteEntity(HttpServletRequest request) {
		Serializable[] ids = getEntityIds(request);

		if (ids != null) {
			for (Serializable id : ids) {
				basePropertiesManager.removeById(id);
			}
			doReload();
			return;
		}

		Serializable id = getEntityId(request);
		doReload();
		//basePropertiesManager.delOne(basePropertiesManager.get(id),request.getSession().getServletContext().getRealPath("WEB-INF/conf/"));
		basePropertiesManager.removeById(id);
	}

	/**
	 * 执行对象排序的Action函数.
	 */
	public String orderSave() {
		Serializable[] ids = getEntityIds(request);
		String userId=request.getSession().getAttribute("wsuserId").toString();
		if (ids != null) {
			doSortEntity(ids,userId);
		}
		passParams(request);
		doReload();
		return list();
	}
	
	/**
	 * 新增属性组
	 * 
	 */
	public String addGroup() {
		setBasePropertiesGroup(basePropertiesGroupManager.get(getGroupId()));
		request.setAttribute("basePropertiesGroup", basePropertiesGroupManager.get(getGroupId()));
		setOperater("add");
		return ADD_GROUP_JSP;
	}
	
	/**
	 * 修改属性组
	 * 
	 */
	public String editGroup() {
		setBasePropertiesGroup(basePropertiesGroupManager.get(getGroupId()));
		request.setAttribute("basePropertiesGroup", basePropertiesGroupManager.get(getGroupId()));
		setOperater("edit");
		return EDIT_GROUP_JSP;
	}
	
	/**
	 * 删除属性组
	 * 
	 */
	public String deleteGroup() {
		setBasePropertiesGroup(basePropertiesGroupManager.get(getGroupId()));
		setOperater("delete");
		return groupUpdate();
	}
	
	/**
	 * 保存Group对象的Action函数.
	 */
	public String groupUpdate() {
		BasePropertiesGroup object;
		//修改：edit 新增：add  删除：delete
		String operater=getOperater();
		String nodeName=request.getParameter("nodeName1");
		String nodeType=request.getParameter("nodeType");
		String nodeKey=request.getParameter("nodeKey");
		if (operater.equals("edit")){//修改
			object = basePropertiesGroup;
			object.setModTime(new Date());
			object.setGroupName(nodeName);
			object.setGroupKey(nodeKey);
			object.setNodeType(nodeType);
			basePropertiesGroupManager.save(object);
			basePropertiesGroupManager.flush();
			basePropertiesGroupManager.evit(object);
			basePropertiesGroupManager.reload();
			if (object == null) {
				addFieldError("add_group_jsp", "entity.missing");
				return ADD_GROUP_JSP;
			}
			request.setAttribute("groupCode1", basePropertiesGroup.getGroupCode());
			return FLUSH_GROUP_JSP;
		} else if (operater.equals("add")){//新增
			object = new BasePropertiesGroup();
			if (basePropertiesGroup.getGroupCode().equals("")){
			    object.setParentCode("-1");
			//}else if (type=="0")
//			{
//				object.setParentCode(basePropertiesGroup.getParentCode());
			}else
			{
				object.setParentCode(basePropertiesGroup.getGroupCode());
			}
			object.setGroupKey(nodeKey);
			object.setGroupName(nodeName);
			object.setNodeType(nodeType);
			object.setItemType("USR");
			object.setCreTime(new Date());
			object.setModTime(new Date());
			basePropertiesGroupManager.save(object);
			basePropertiesGroupManager.flush();
			basePropertiesGroupManager.evit(object);
			basePropertiesGroupManager.reload();
			request.setAttribute("groupCode1", basePropertiesGroup.getGroupCode());
			return FLUSH_GROUP_JSP;
		}else if (operater.equals("delete")){//删除
			try {
				basePropertiesGroupManager.removeById(getGroupId());
				basePropertiesGroupManager.reload();
			} catch (Exception e) {
				addFieldError("deleteGroup", e.getMessage());
			}
			return FLUSH_DELETE_GROUP_JSP;
		}

		return FLUSH_GROUP_JSP;
	}
	
	/**
	 * 保存Form表单时的回调函数
	 * 
	 * @see StrutsEntityAction#initEntity(org.apache.struts.action.ActionForm,javax.servlet.http.HttpServletRequest,Object)
	 */
	@Override
	protected void onInitEntity(HttpServletRequest request,
			BaseProperties baseProperties) {
		bindEntity(this.getBaseProperties(), baseProperties);
		if (baseProperties.getId() != null) {
			baseProperties.setModTime(new Date());
		} else {
			baseProperties.setModTime(new Date());
			baseProperties.setCreTime(new Date());
		}
	}

	/*
	 * 公共参数传递
	 */
	protected void passParams(HttpServletRequest request) {
		String groupCode = ParamUtils.getParameter(request, "groupCode", "-1");
		String groupName = "";
		if(groupCode!=null&&!groupCode.equals("-1")){
			groupName = BasePropertiesManager.getValue(groupCode);
		}
		String groupKey = request.getParameter("groupKey");
		String nodeType = request.getParameter("nodeType");

		request.setAttribute("groupCode", groupCode);
		request.setAttribute("groupKey", groupKey);
		request.setAttribute("groupName", groupName);
		request.setAttribute("nodeType", nodeType);
	}
	
	public String checkDelete()
	{
		String flag="0";
		setBasePropertiesGroup(basePropertiesGroupManager.get(getGroupId()));
		flag=basePropertiesGroupManager.getCountByGroupId(basePropertiesGroup.getGroupCode());
		response.setContentType("text/html;charset=utf-8");
		java.io.PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(flag);
		} catch (java.io.IOException e) {
			flag = "1";//异常默认已经存在
			e.printStackTrace();
		}
		// 告诉页面返回处理结果结束
		return null;
	}

	public List<BaseProperties> getPropertiesDataList() {
		return propertiesDataList;
	}

	public void setPropertiesDataList(List<BaseProperties> propertiesDataList) {
		this.propertiesDataList = propertiesDataList;
	}
	
	public BaseProperties getBaseProperties() {
		return baseProperties;
	}

	public void setBaseProperties(BaseProperties baseProperties) {
		this.baseProperties = baseProperties;
	}
	
	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			baseProperties = new BaseProperties();
			baseProperties.setUseFlag(0);
		} else {
			baseProperties = (BaseProperties) basePropertiesManager.get(id);
		}
	}

	public Object getModel() {
		return baseProperties;
	}
	

	public void setPid(java.lang.String val) {
		this.id = val;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public BasePropertiesGroup getBasePropertiesGroup() {
		return basePropertiesGroup;
	}

	public void setBasePropertiesGroup(BasePropertiesGroup basePropertiesGroup) {
		this.basePropertiesGroup = basePropertiesGroup;
	}

	public String getOperater() {
		return operater;
	}

	public void setOperater(String operater) {
		this.operater = operater;
	}
}
