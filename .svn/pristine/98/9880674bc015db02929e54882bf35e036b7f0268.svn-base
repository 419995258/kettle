/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2011
 */

package org.my431.base.web;
//
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Border;
import jxl.write.BorderLineStyle;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.my431.base.json.JsonUtil;
import org.my431.base.model.BaseArea;
import org.my431.base.model.BaseAreaTree;
import org.my431.base.model.BaseProperties;
import org.my431.base.model.BaseUser;
import org.my431.base.services.BaseAreaManager;
import org.my431.base.services.BaseUserManager;
import org.my431.base.services.CacheBaseAreaManager;
import org.my431.base.services.CacheBasePropertiesManager;
import org.my431.platform.web.StrutsTreeEntityAction;
import org.my431.plugin.redis.services.RedisManager;
import org.my431.util.GeneralTreeNode;
import org.my431.util.MD5;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author wangzhen
 * @version 1.0
 * @since 1.0
 */


public class BaseAreaAction extends StrutsTreeEntityAction<BaseArea,BaseAreaManager> implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	//forward paths
	protected static final String QUERY_JSP = "/base/BaseArea/area_query.jsp";
	protected static final String LIST_JSP= "/base/BaseArea/area_list.jsp";
	protected static final String CREATE_JSP = "/base/BaseArea/area_add.jsp";
	protected static final String EDIT_JSP = "/base/BaseArea/area_edit.jsp";
	protected static final String SHOW_JSP = "/base/BaseArea/area_show.jsp";
	protected static final String INDEX_JSP = "/base/BaseArea/area_index.jsp";
	protected static final String TREE_INDEX_JSP = "/base/BaseArea/area_tree_index.jsp";
	protected static final String ORDER_JSP = "/base/BaseArea/area_order.jsp";
	protected static final String FLUSH_JSP = "/base/BaseArea/flush_tree.jsp";
	protected static final String SEARCH_JSP = "/base/BaseArea/search.jsp";
	protected static final String VIEW_USER_PWD_JSP = "/base/BaseArea/view_user_pwd.jsp";
	
	
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/base/BaseArea/list.jspx";
	protected static final String LIST_ACTION_RETURN = "!/base/BaseArea/treeIndex.jspx";
	
	private BaseAreaManager baseAreaManager;
	
	private BaseUserManager baseUserManager;
	
	private RedisManager redisManager;
	public RedisManager getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}
	
	private BaseArea baseArea;
	java.lang.String id = null;
	java.lang.String queryParentCode = null;
	
	private String[] items;

	public void prepare() throws Exception {
		
		if (isNullOrEmptyString(id)) {
				baseArea = new BaseArea();
			} else {
				if(!id.contains(",")){
					baseArea = (BaseArea)baseAreaManager.get(id);
				}
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseAreaManager(BaseAreaManager manager) {
		this.baseAreaManager = manager;
	}	
	
	public Object getModel() {
		return baseArea;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	public String index() {
		return INDEX_JSP;
	}
	public String treeIndex() {
		String queryParentCode=request.getParameter("queryParentCode");
		if(queryParentCode==null){
			queryParentCode="-1";
		}
		  BaseArea area=new BaseArea ();
		  String wsAreaCode=String.valueOf(request.getSession().getAttribute("wsAreaCode"));
		if(wsAreaCode.equals("-1")){
			area = CacheBaseAreaManager.getNodeByCode("-1");
			request.setAttribute("areaName", "全国");
		}else{
			area = CacheBaseAreaManager.getNodeByCode(wsAreaCode);
			request.setAttribute("areaName", area.getAreaName());
		}
		if(queryParentCode.equals("-1")){
		   queryParentCode=wsAreaCode;
		}
		if(area.getNodeType().equals("1")){
			Map<String,GeneralTreeNode> ht=CacheBaseAreaManager.getAllTreeByParentCode(wsAreaCode);
			request.setAttribute("dataList", ht);
		}
		request.setAttribute("queryParentCode", queryParentCode);
		request.setAttribute("errorMsg", request.getParameter("errorMsg"));
		request.setAttribute("menuFlag", "xfdj");
		request.setAttribute("menuFlag_1", "dqgl");
		return TREE_INDEX_JSP;
	}
	
	/** 执行搜索 */
	public String list(){
		if(queryParentCode!=null){
			List<BaseAreaTree> list=CacheBaseAreaManager.getTreeByParentCode(queryParentCode);
			request.setAttribute("dataList", list);
		}
		BaseArea node= null;	
		node= CacheBaseAreaManager.getNodeByCode(queryParentCode);
		request.setAttribute("node", node);
		if(node!=null){
			String queryRole="role";
			if(queryParentCode.length()<3){
				queryRole="role.provinceManager";
			}
			if(queryParentCode.length()==3){
				queryRole="role.provinceManager";
			}
			if(queryParentCode.length()==6){
				queryRole="role.cityManager";
			}
			if(queryParentCode.length()==9){
				queryRole="role.countryManager";
			}
			request.setAttribute("areaObserves", baseUserManager.getUserByAreaId(node.getId(), "role.areaObserve"));
			request.setAttribute("areaManagers", baseUserManager.getUserByAreaId(node.getId(), queryRole));
//			request.setAttribute("areaAppMngs", baseUserManager.getUserByAreaId(node.getId(), "role.areaAppMng"));
		}else{
			request.setAttribute("areaObserves", baseUserManager.getUserByAreaId("1", "role.areaObserve"));
			request.setAttribute("areaManagers", baseUserManager.getUserByAreaId("1", "role.areaManager"));
//			request.setAttribute("areaAppMngs", baseUserManager.getUserByAreaId("1", "role.areaAppMng"));
		}
		request.setAttribute("queryParentCode", queryParentCode);
		request.setAttribute("menuFlag", "sys");
		request.setAttribute("menuFlag_1", "dqgl");
		return LIST_JSP;
	}
	
	
	
	/** 更改状态*/
	public String upStatus() {
		String status=request.getParameter("status");
		String userId=request.getParameter("userId");
		BaseUser user=baseUserManager.get(userId);
		user.setStatus(status);
		baseUserManager.save(user);
		baseUserManager.updateReload(user);
		
		return treeIndex();// LIST_ACTION+"?queryParentCode="+request.getParameter("queryParentCode");
	}
	
	public String viewPwd(){
		String userId=request.getParameter("userId");
		BaseUser user=baseUserManager.get(userId);
		request.setAttribute("user", user);
	    return VIEW_USER_PWD_JSP;
	}
	
	/** 查看对象*/
	public String show() {
		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		List<BaseProperties> bps = CacheBasePropertiesManager.getPropertiesByGroupKey("lianpianCode");
		request.setAttribute("menuFlag", "sys");
		request.setAttribute("menuFlag_1", "dqgl");
		
		request.setAttribute("queryParentCode", queryParentCode);
		request.setAttribute("bps", bps);
		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		
		baseArea.setDeleteFlag(0);
		//baseArea.setTbFlag("1");
		baseAreaManager.save(baseArea);
		baseAreaManager.reloadSave(baseArea);
		//新建地区更新地区缓存 hmc
		reLoadAreaRedis();
		
		//baseAreaManager.init();
		
		//新增地区时，默认生成一个地区管理员 begin
		BaseUser baseUser =new BaseUser();
		
//		String ln="9";
//		Integer accountSeq=baseUserManager.accountSeq();
//		StringBuffer sf=new StringBuffer();
//		if(!baseArea.getId().equals("1"))
//			sf.append(baseArea.getNationalCode());
//		else
//			sf.append("000000");//全国的默认000000
//		sf.append(accountSeq);
//		ln=ln+sf.toString();
		
		baseUser.setLoginName(baseArea.getNationalCode());
		baseUser.setPassword(MD5.getMd5(baseUserManager.getRandomPassword(8)));
		baseUser.setRealName(baseArea.getAreaName()+"管理员");
		if(baseArea.getAreaCode().length()==3){
			baseUser.setProvinceId(baseArea.getId());
			baseUser.setDefaultRoleCode("role.provinceManager");
		}
		if(baseArea.getAreaCode().length()==6){
			baseUser.setProvinceId(CacheBaseAreaManager.getNodeByCode(baseArea.getAreaCode().substring(0, 3)).getId());
			baseUser.setCityId(baseArea.getId());
			baseUser.setDefaultRoleCode("role.cityManager");
		}
		if(baseArea.getAreaCode().length()==9){
			baseUser.setProvinceId(CacheBaseAreaManager.getNodeByCode(baseArea.getAreaCode().substring(0, 3)).getId());
			baseUser.setCityId(CacheBaseAreaManager.getNodeByCode(baseArea.getAreaCode().substring(0, 6)).getId());
			baseUser.setCountyId(baseArea.getId());
			baseUser.setDefaultRoleCode("role.countryManager");
		}		
		baseUser.setAreaId(baseArea.getId());
		baseUser.setCreTime(new Date());
		baseUser.setModTime(new Date());
		baseUser.setIsIni(0);
		baseUser.setStatus("1");
		baseUser.setDeleteFlag("0");
		baseUserManager.save(baseUser);
		//////end
		
//		//新增地区时，默认生成一个地区视察员 begin
//		BaseUser areaObserve =new BaseUser();
//		
//		String lna="9";
//		Integer areaObserveAccountSeq=baseUserManager.accountSeq();
//		StringBuffer asf=new StringBuffer();
//		if(!baseArea.getId().equals("1"))
//			asf.append(baseArea.getNationalCode());
//		else
//			asf.append("000000");//全国的默认000000
//		asf.append(areaObserveAccountSeq);
//		lna=lna+asf.toString();
//		
//		areaObserve.setLoginName(lna);
//		areaObserve.setPassword(MD5.getMd5(baseUserManager.getRandomPassword(8)));
//		areaObserve.setRealname(baseArea.getAreaName()+"视察员");
//		if(baseArea.getAreaCode().length()==3){
//			areaObserve.setProvinceId(baseArea.getId());
//		}
//		if(baseArea.getAreaCode().length()==6){
//			areaObserve.setProvinceId(CacheBaseAreaManager.getNodeByCode(baseArea.getAreaCode().substring(0, 3)).getId());
//			areaObserve.setCityId(baseArea.getId());
//		}
//		if(baseArea.getAreaCode().length()==9){
//			areaObserve.setProvinceId(CacheBaseAreaManager.getNodeByCode(baseArea.getAreaCode().substring(0, 3)).getId());
//			areaObserve.setCityId(CacheBaseAreaManager.getNodeByCode(baseArea.getAreaCode().substring(0, 6)).getId());
//			areaObserve.setCountyId(baseArea.getId());
//		}		
//		areaObserve.setAreaId(baseArea.getId());
//		areaObserve.setCreTime(new Date());
//		areaObserve.setModTime(new Date());
//		areaObserve.setDefaultRoleCode("role.areaObserve");
//		areaObserve.setIsIni(0);
//		areaObserve.setStatus("1");
//		areaObserve.setDeleteFlag("0");
//		baseUserManager.save(areaObserve);
//		//////end
		
		
		request.setAttribute("queryParentCode", baseArea.getParentCode());
		return LIST_ACTION_RETURN;
		//return "/base/BaseArea/treeIndex.jspx?queryParentCode="+baseArea.getParentCode()+"&errorMsg="+request.getParameter("errorMsg");
		//return FLUSH_JSP;
	}
	
	/**进入更新页面*/
	public String edit() {
		List<BaseProperties> bps = CacheBasePropertiesManager.getPropertiesByGroupKey("lianpianCode");
		request.setAttribute("bps", bps);
		request.setAttribute("baseArea", this.baseArea);
		request.setAttribute("queryParentCode", request.getParameter("queryParentCode"));
		return EDIT_JSP;
	}
	
	/**模糊搜索视察员，管理员	 */
	public String search() {
		String searchAreaName=request.getParameter("searchAreaName");
		request.setAttribute("searchAreaName", searchAreaName);
		List<BaseArea> list=baseAreaManager.searchArea1(searchAreaName);
		request.setAttribute("dataList",list );
		return SEARCH_JSP;
	}
	
	
	/**保存更新对象*/
	public String update() {
		String areaName=request.getParameter("areaName");
		String nationalCode=request.getParameter("nationalCode");
		String areaType=request.getParameter("areaType");
		String nodeType=request.getParameter("nodeType");
		baseArea.setAreaName(areaName);
		baseArea.setNationalCode(nationalCode);
		baseArea.setNodeType(nodeType);
		baseAreaManager.update(this.baseArea);
		baseAreaManager.reloadSave(baseArea);
		
		//baseAreaManager.init();
		request.setAttribute("queryParentCode", request.getParameter("queryParentCode"));
		request.setAttribute("isEdit_1", "1");
		return treeIndex();//LIST_ACTION+"?queryParentCode="+request.getParameter("queryParentCode");
	}

	public String order() {
		String queryParentCode=request.getParameter("queryParentCode");
		List<BaseAreaTree> list=CacheBaseAreaManager.getTreeByParentCode(queryParentCode);
		request.setAttribute("list", list);
		request.setAttribute("queryParentCode", queryParentCode);
		request.setAttribute("menuFlag", "sys");
		request.setAttribute("menuFlag_1", "dqgl");
		return ORDER_JSP;
	}
	
	public String orderSave() {
		Serializable[] ids = getEntityIds(request);
		if (ids != null) {
			doSortEntity(ids);
		}
		
		String queryParentCode=request.getParameter("queryParentCode");
		request.setAttribute("queryParentCode",queryParentCode);
		
		return LIST_ACTION_RETURN;
		//return LIST_ACTION+"?queryParentCode="+queryParentCode;
	}	
	
	/**
	 * 排序业务对象的函
	 */
	@Override
	protected void doSortEntity(Serializable[] ids) {
		String userId=request.getSession().getAttribute("wsuserId").toString();
		if (ids != null) {
			String[] idStr=new String[ids.length];
			for (int i=0;i<ids.length;i++){
				idStr[i]=(String) ids[i];
			}
			baseAreaManager.updateOrder(idStr,userId);
		}		
	}
	
	/**删除对象*/
	public String delete() {
		Map<String,GeneralTreeNode>	ht=(Map<String, GeneralTreeNode>) redisManager.getOValue("lockInfoTb");
		String[] ids=request.getParameterValues("id");
		String errorMsg="";
		for(int i = 0; i < ids.length; i++) {
			java.lang.String id = ids[i];
			BaseArea  area=baseAreaManager.get(id);
			if(baseAreaManager.isDel(area)){
				area.setDeleteFlag(1);
				baseAreaManager.update(area);
				baseAreaManager.reloadDel(area);
				ht.remove(area.getAreaCode());
			}
			request.setAttribute("queryParentCode", area.getParentCode());
		}
		redisManager.removeOValue("lockInfoTb");
		redisManager.setOValue("lockInfoTb", ht);
		request.setAttribute("errorMsg", errorMsg);
		return FLUSH_JSP;
	}
	
		
	public java.lang.String getQueryParentCode() {
		return queryParentCode;
	}

	public void setQueryParentCode(java.lang.String queryParentCode) {
		this.queryParentCode = queryParentCode;
	}

	public BaseArea getBaseArea() {
		return baseArea;
	}

	public void setBaseArea(BaseArea baseArea) {
		this.baseArea = baseArea;
	}

	public void setBaseUserManager(BaseUserManager baseUserManager) {
		this.baseUserManager = baseUserManager;
	}
	
	public String deleteAreaObserve(){
		String wsuserId=request.getSession().getAttribute("wsuserId").toString();
		String userId=request.getParameter("userId");
		BaseUser user=baseUserManager.get(userId);
		user.setDeleteFlag("1");
		user.setMobile(user.getLoginName()+"#"+user.getMobile());
		user.setEmail(user.getLoginName()+"#"+user.getEmail());
		user.setDeleteTime(new Date());
		user.setDeleteUser(wsuserId);
		baseUserManager.update(user);
		baseUserManager.updateReload(user);
		
		BaseArea baseArea=baseAreaManager.get(user.getAreaId());
		
		request.setAttribute("queryParentCode", baseArea.getAreaCode());
		return FLUSH_JSP;
	}
	
	/**
	 * 通过地区，创建管理员
	 * @return
	 */
	public String insertAreaManager()
	{
	   List<BaseArea> list = baseAreaManager.getNoManagerArea();
	   for(BaseArea baseArea : list)
		{
			//新增地区时，默认生成一个地区管理员 begin
			BaseUser baseUser =new BaseUser();
//			String ln="9";
//			Integer accountSeq=baseUserManager.accountSeq();
//			StringBuffer sf=new StringBuffer();
//			if(!baseArea.getId().equals("1"))
//				sf.append(baseArea.getNationalCode());
//			else
//				sf.append("000000");//全国的默认000000
//			sf.append(accountSeq);
//			ln=ln+sf.toString();
			
			baseUser.setLoginName(baseArea.getNationalCode());
			baseUser.setPassword(MD5.getMd5(baseUserManager.getRandomPassword(8)));
			baseUser.setRealName(baseArea.getAreaName()+"管理员");
			if(baseArea.getAreaCode().length()==3){
				baseUser.setProvinceId(baseArea.getId());
			}
			if(baseArea.getAreaCode().length()==6){
				baseUser.setProvinceId(CacheBaseAreaManager.getNodeByCode(baseArea.getAreaCode().substring(0, 3)).getId());
				baseUser.setCityId(baseArea.getId());
			}
			if(baseArea.getAreaCode().length()==9){
				baseUser.setProvinceId(CacheBaseAreaManager.getNodeByCode(baseArea.getAreaCode().substring(0, 3)).getId());
				baseUser.setCityId(CacheBaseAreaManager.getNodeByCode(baseArea.getAreaCode().substring(0, 6)).getId());
				baseUser.setCountyId(baseArea.getId());
			}		
			baseUser.setAreaId(baseArea.getId());
			baseUser.setCreTime(new Date());
			baseUser.setModTime(new Date());
			baseUser.setDefaultRoleCode("role.areaManager");
			baseUser.setIsIni(0);
			baseUser.setStatus("1");
			baseUser.setDeleteFlag("0");
			baseUserManager.save(baseUser);
			//////end
			/**
			//新增地区时，默认生成一个地区视察员 begin
			BaseUser areaObserve =new BaseUser();
			
			String lna="9";
			Integer areaObserveAccountSeq=baseUserManager.accountSeq();
			StringBuffer asf=new StringBuffer();
			if(!baseArea.getId().equals("1"))
				asf.append(baseArea.getNationalCode());
			else
				asf.append("000000");//全国的默认000000
			asf.append(areaObserveAccountSeq);
			lna=lna+asf.toString();
			
			areaObserve.setLoginName(lna);
			areaObserve.setPassword(MD5.getMd5(baseUserManager.getRandomPassword(8)));
			areaObserve.setRealname(baseArea.getAreaName()+"视察员");
			if(baseArea.getAreaCode().length()==3){
				areaObserve.setProvinceId(baseArea.getId());
			}
			if(baseArea.getAreaCode().length()==6){
				areaObserve.setProvinceId(CacheBaseAreaManager.getNodeByCode(baseArea.getAreaCode().substring(0, 3)).getId());
				areaObserve.setCityId(baseArea.getId());
			}
			if(baseArea.getAreaCode().length()==9){
				areaObserve.setProvinceId(CacheBaseAreaManager.getNodeByCode(baseArea.getAreaCode().substring(0, 3)).getId());
				areaObserve.setCityId(CacheBaseAreaManager.getNodeByCode(baseArea.getAreaCode().substring(0, 6)).getId());
				areaObserve.setCountyId(baseArea.getId());
			}		
			areaObserve.setAreaId(baseArea.getId());
			areaObserve.setCreTime(new Date());
			areaObserve.setModTime(new Date());
			areaObserve.setDefaultRoleCode("role.areaObserve");
			areaObserve.setIsIni(0);
			areaObserve.setStatus("1");
			areaObserve.setDeleteFlag("0");
			baseUserManager.save(areaObserve);
			*/
		}
		return null;
	}
	
	/**
	 * 变省直管 未完成，该功能已被取消
	 * @author    hmc    2015年5月6日  下午3:27:51
	 * @return
	 */
	public String transferArea0(){
		String areaId=request.getParameter("areaId");
		if(!isEmpty(areaId)){
			BaseArea temparea=getBaseAreaAndSetRedis(areaId, null);
			String tempCode=temparea.getAreaCode();
			while(tempCode!=null&&!tempCode.equals("-1")&&!baseArea.getParentCode().equals("-2")){
				baseArea=baseAreaManager.getAreaByAreaId(null, tempCode);
				tempCode=baseArea.getParentCode();
			}
		}
			
		return null;
	}
	/**
	 * 判断传入的对象是否为空
	 * @author    hmc    2015年5月6日  下午3:35:57
	 * @param obj
	 * @return
	 */
	private boolean isEmpty(Object obj){
		if(obj!=null&&!obj.equals("")&&!obj.equals("null")){
			return true;
		}
		return false;
	}
	
	/**
	 * 通过areaid或者areaCode从缓存查询area对象
	 * @author    hmc    2015年5月6日  下午3:43:49
	 * @param areaId
	 * @param areaCode
	 * @return
	 */
	private BaseArea getBaseAreaAndSetRedis(String areaId,String areaCode){
		BaseArea tempArea=null;
		if(!isEmpty(areaId)){
			tempArea=CacheBaseAreaManager.getNodeById(areaId);
			if(tempArea==null&&!isEmpty(areaCode)){
				tempArea=CacheBaseAreaManager.getNodeByCode(areaCode);
				if(tempArea==null){
					tempArea=baseAreaManager.getAreaByAreaId(areaId, areaCode);
					CacheBaseAreaManager.setAreaIdKeyMap(tempArea.getId(), tempArea);
					CacheBaseAreaManager.setAreaCodeKeyMap(tempArea.getAreaCode(), tempArea);
				}
			}
		}
		
		return tempArea;
	}
	
	/**
	 * 检查地区名字是否重复
	 * @author    hmc    2015年6月12日  下午1:38:26
	 * @return
	 */
	public String checkAreaName(){
		String areaName=request.getParameter("areaName");
		int x=baseAreaManager.getAreaByAreaName(areaName);
		response.setContentType("text/html;charset=utf-8");
		java.io.PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		if(x==0){
			pw.write("0");
		}else{
			pw.write("1");
		}
		return null;
	}
	
	/**
	 * 更新地区的redis缓存
	 * @author    hmc    2015年6月30日  上午11:43:23
	 */
	public void reLoadAreaRedis(){
		Map<String,GeneralTreeNode>	ht=(Map<String, GeneralTreeNode>) redisManager.getOValue("lockInfoTb");
		BaseArea node=baseArea;
		if(node.getDeleteFlag()==null||node.getDeleteFlag().equals(0)){
			GeneralTreeNode treeNode = new GeneralTreeNode();
			treeNode.setId(node.getId());
			treeNode.setNodeCode(node.getAreaCode());
			treeNode.setParentCode(node.getParentCode());
			treeNode.setNodeName(node.getAreaName());
			treeNode.setNodeLink(node.getNationalCode());
			treeNode.setNodeType(node.getNodeType());
			treeNode.setNodeLevel(node.getNodeLevel());
			treeNode.setSeqNo(node.getSeqNo());
			/*treeNode.setShangBaoStatus2014(node.getShangBaoStatus2014());
			treeNode.setShangBaoStatus2015(node.getShangBaoStatus2015());
			treeNode.setShangBaoStatus2016(node.getShangBaoStatus2016());
			treeNode.setShangBaoStatus2017(node.getShangBaoStatus2017());
			treeNode.setShangBaoStatus2018(node.getShangBaoStatus2018());
			treeNode.setTbFlag(node.getTbFlag());*/
			ht.put(node.getAreaCode(), treeNode);
			CacheBaseAreaManager.setAreaIdKeyMap(node.getId(), node);
			CacheBaseAreaManager.setAreaCodeKeyMap(node.getAreaCode(), node);
		}
			redisManager.removeOValue("lockInfoTb");
			redisManager.setOValue("lockInfoTb", ht);
	}
	/**
	 * 
	* @Description:由父code得到下级地区 
	* @author hyl     
	* @date 2017-7-3 下午3:22:48  
	* @version V1.0 
	* @author user
	 */
	public void getAreaListByParentCode() {
		String parentCode = request.getParameter("areaCode");
		List<BaseAreaTree>  tree=CacheBaseAreaManager.getTreeByParentCode(parentCode);
		System.out.println(JsonUtil.listToJSON(tree));
		this.renderHtml(response, JsonUtil.listToJSON(tree));
	}
	
	public String expUser() {
		String areaId = request.getParameter("areaId");
		System.out.println();
		List users = new ArrayList<BaseUser>();
		// String areaName =
		// McBaseAreaManager.getNodeById(areaId).getAreaName();
		String areaName = baseAreaManager.getLabelByCode(CacheBaseAreaManager
				.getNodeById(areaId).getAreaCode());
		if (CacheBaseAreaManager.getNodeById(areaId) != null) {
			users = baseAreaManager.getUserByAreaCode(CacheBaseAreaManager
					.getNodeById(areaId).getAreaCode());
		} else {
			users = baseAreaManager.getUserByAreaCode("-1");
		}
		String downloadUrl = "";
		String title = "";
		try {
			// String excelTitle=areaName+"下级地区管理员列表";
			// response.setHeader("Content-Disposition", "attachment; filename="
			// + new String((excelTitle+".xls").getBytes("GBK"),"ISO8859-1"));
			// ExcelUtil.WriteExcel(request.getSession().getServletContext().getRealPath("/excel/users.xls"),
			// excelTitle, 2, users, response.getOutputStream());
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			title = areaName + "-地区管理员列表";
			String url = request.getSession().getServletContext()
					.getRealPath("/");
			String templatePath = url + "excel/" + title + ".xls";
			downloadUrl = "/excel/" + title + ".xls";
			File ff = new File(templatePath);
			if (!ff.exists()) {
				ff.createNewFile();
			}
			WorkbookSettings settings = new WorkbookSettings();
			settings.setWriteAccess(null);
			WritableWorkbook wwb = Workbook.createWorkbook(ff, settings);
			WritableSheet wws = wwb.createSheet(title, 0);
			WritableFont font = new WritableFont(WritableFont.createFont("宋体"),
					12, WritableFont.NO_BOLD);
			WritableCellFormat cellFormat = new WritableCellFormat(font,
					NumberFormats.TEXT);
			WritableFont font1 = new WritableFont(
					WritableFont.createFont("宋体"), 14, WritableFont.NO_BOLD);
			WritableCellFormat cellFormat1 = new WritableCellFormat(font1,
					NumberFormats.TEXT);
			cellFormat.setWrap(true);
			cellFormat
					.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
			cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			cellFormat1.setWrap(true);
			cellFormat1
					.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			cellFormat1.setAlignment(jxl.format.Alignment.CENTRE);
			cellFormat1.setBorder(Border.ALL, BorderLineStyle.THIN);
			Label label = new Label(0, 0, title, cellFormat1);
			wws.addCell(label);
			// 设置Excel表头
			//String[] titles = new String[] { "地区名称", "姓名", "账号", "密码" };
			String[] titles = new String[] { "地区名称", "姓名", "账号" };
			for (int i = 0; i < titles.length; i++) {
				Label excelTitle = new Label(i, 1, titles[i], cellFormat1);
				wws.addCell(excelTitle);
			}
			for (int i = 0; i < users.size(); i++) {
				Object[] baseUser = (Object[]) users.get(i);
				/*if (isNullOrEmptyString(baseUser[3])) {
					baseUser[3] = "123456";
				}*/
				wws.setRowView(i + 2, 270);
				wws.addCell(new Label(0, i + 2, baseUser[0].toString(),
						cellFormat));
				wws.setColumnView(0, 18);
				wws.addCell(new Label(1, i + 2, baseUser[1].toString(),
						cellFormat));
				wws.setColumnView(1, 18);
				wws.addCell(new Label(2, i + 2, baseUser[2].toString(),
						cellFormat));
				wws.setColumnView(2, 18);
				/*wws.addCell(new Label(3, i + 2, baseUser[3].toString(),
						cellFormat));
				wws.setColumnView(2, 18);*/
			}
			wws.mergeCells(0, 0, 2, 0);
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=utf-8");
		java.io.PrintWriter pw = null;
		String returnValue = downloadUrl + "#" + title;
		try {
			pw = response.getWriter();
			pw.write(returnValue);
		} catch (java.io.IOException e) {
			returnValue = "0";
			e.printStackTrace();
		}
		return null;
	}

}
