/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2011
 */

package org.my431.base.web;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpUtils;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.my431.base.mobile.Client;
import org.my431.base.model.BaseArea;
import org.my431.base.model.BaseProperties;
import org.my431.base.model.BaseSchool;
import org.my431.base.model.BaseUser;
import org.my431.base.services.BaseAreaManager;
import org.my431.base.services.BaseLoginManager;
import org.my431.base.services.BaseNoteManager;
import org.my431.base.services.BasePropertiesManager;
import org.my431.base.services.BaseRoleManager;
import org.my431.base.services.BaseSchoolManager;
import org.my431.base.services.BaseUserManager;
import org.my431.base.services.BaseUserMisManager;
import org.my431.base.services.CacheBaseAreaManager;
import org.my431.base.services.CacheBasePropertiesManager;
import org.my431.base.services.CacheBaseSchoolManager;
import org.my431.base.services.CacheBaseUserManager;
import org.my431.base.services.PageManager;
import org.my431.platform.dao.support.Page;
import org.my431.platform.utils.SetValueUtil;
import org.my431.platform.web.StrutsTreeEntityAction;
import org.my431.plugin.redis.services.RedisManager;
import org.my431.taglib.My431Function;
import org.my431.util.DateFormater;
import org.my431.util.GeneralTreeNode;
import org.my431.util.MD5;
import org.my431.util.MMap;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author wangzhen
 * @version 1.0
 * @since 1.0
 */

public class BaseUserAction extends StrutsTreeEntityAction<BaseUser, BaseUserManager> implements Preparable, ModelDriven {
	// 默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null;

	// forward paths
	protected static final String QUERY_JSP = "/base/admin/BaseUser/user_query.jsp";
	protected static final String LIST_JSP = "/base/admin/BaseUser/user_list.jsp";
	protected static final String CREATE_JSP = "/base/admin/BaseUser/user_add.jsp";
	protected static final String EDIT_JSP = "/base/admin/BaseUser/user_edit.jsp";
	protected static final String EDIT_PWD_JSP = "/base/admin/BaseUser/user_edit_pwd.jsp";
	protected static final String AREAUSER_EDIT_JSP = "/base/admin/BaseUser/area_user_edit.jsp";

	protected static final String SHOW_JSP = "/base/admin/BaseUser/user_show.jsp";
	protected static final String INDEX_JSP = "/base/admin/BaseUser/user_index.jsp";
	protected static final String TREE_INDEX_JSP = "/base/admin/BaseUser/user_tree_index.jsp";
	protected static final String SCHOOL_USER_JSP = "/base/admin/BaseSchool/school_user_list.jsp";
	protected static final String SCHOOL_EDIT_JSP = "/base/admin/BaseSchool/school_user_edit.jsp";
	protected static final String SCHOOLUSER_ADD_JSP = "/base/admin/BaseSchool/school_user_add.jsp";

	protected static final String VIEW_USER_PWD_JSP = "/base/admin/BaseUser/view_user_pwd.jsp";

	protected static final String SCHOOL_USER_LIST_JSP = "/base/schoolManager/schoolUser/school_user_list.jsp";
	protected static final String SCHOOL_USER_CREATE_JSP = "/base/schoolManager/schoolUser/school_user_add.jsp";
	protected static final String SCHOOL_USER_EDIT_JSP = "/base/schoolManager/schoolUser/school_user_edit.jsp";

	protected static final String SCHOOL_USER_LIST_ACTION = "!/base/BaseUser/schoolUser.jspx";
	
	protected static final String EDIT_USER_PASSWORD = "/base/passward_edit.jsp";
	protected static final String EDIT_USER_PASSWORD_SUCCESS = "!/base/passward_edit_success.jsp";
	protected static final String BASE_USER_EDIT = "/base/user_edit.jsp";
	protected static final String BASE_USER_EDIT_SUCCESS = "/base/user_edit_success.jsp";
	// redirect paths,startWith: !
	protected static final String AREA_LIST_ACTION = "!/base/BaseArea/list.jspx";
	protected static final String SCHOOL_LIST_ACTION = "!/base/BaseUser/schoollist.jspx";
	protected static final String SCHOOL_USER_EDIT = "/base/schoolManager/schoolUser/user_edit_school.jsp";
	protected static final String BASE_USER_AREA = "/base/areaManager/area_user_edit.jsp";
	protected static final String BASE_USER_AREA_SUCCESS = "/base/areaManager/area_user_edit_success.jsp";
	protected static final String BASE_USER_AREA_PASSWORD = "/base/areaManager/passward_edit.jsp";
	protected static final String SAVE_AREA_USER_IMG = "/base/areaManager/upload_img/upload.jsp";
	protected static final String SAVE_SCHOOL_USER_IMG = "/base/schoolManager/upload_img/upload.jsp";
	protected static final String EDIT_SCHOOLUSER_PASSWORD_SUCCESS = "!/base/schoolManager/pwd_edit_success.jsp";
	
	protected static final String BASE_USER_ISINI="/base/user_is_ini.jsp";
	protected static final String USER_IS_INI_INDEX="/base/user_is_ini_index.jsp";
	
	protected static final String AREA_TREE_INDEX_JSP = "/base/BaseArea/area_tree_index.jsp";

	private BaseUserManager baseUserManager;

	private BaseRoleManager baseRoleManager;

	private BasePropertiesManager basePropertiesManager;
	
	private BaseSchoolManager baseSchoolManager;
	
	private BaseLoginManager baseLoginManager;
	
	private BaseUserMisManager baseUserMisManager;
	
	private BaseNoteManager baseNoteManager;
	public void setBaseLoginManager(BaseLoginManager baseLoginManager) {
		this.baseLoginManager = baseLoginManager;
	}

	public void setBaseNoteManager(BaseNoteManager baseNoteManager) {
		this.baseNoteManager = baseNoteManager;
	}

	private List<BaseProperties> dutyList;
	private List<BaseProperties> titleList;
	private List<BaseProperties> degreeList;
	private List<BaseProperties> subjectList;
	private List<BaseProperties> mzList;

	private BaseAreaManager baseAreaManager;

	private RedisManager redisManager;
	public void setRedisManager(RedisManager redisManager) {
		this.redisManager = redisManager;
	}

	private BaseUser baseUser;
	java.lang.String id = null;
	private String[] items;

	public String queryUser() {

		BaseUser base = (BaseUser) request.getSession().getAttribute("ssoUser");
		request.setAttribute("baseUser", base);

		return BASE_USER_EDIT;
	}

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			baseUser = new BaseUser();
		} else {
			baseUser = (BaseUser) baseUserManager.get(id);
		}
	}

	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseUserManager(BaseUserManager manager) {
		this.baseUserManager = manager;
	}
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseUserMisManager(BaseUserMisManager manager) {
		this.baseUserMisManager = manager;
	}
	public Object getModel() {
		return baseUser;
	}

	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}

	/** 执行搜索 */
	public String list() {
		BaseUser query = new BaseUser();
		String areaId = request.getParameter("areaId");
		String areaCode = request.getParameter("areaCode");

		query.setAreaId(areaId);
		query.setDeleteFlag("0");

		int pageSize = request.getParameter("pageSize") != null ? Integer
				.valueOf(request.getParameter("pageSize")) : 10;
		int pageNo = 1;
		if (request.getParameter("pageNo") != null
				&& !request.getParameter("pageNo").equals("")) {
			pageNo = Integer.valueOf(request.getParameter("pageNo"));
		}

		Page page = baseUserManager.findPage(request, query, pageSize, pageNo);
		request.setAttribute("dataList", page.getResult());
		PageManager pm = new PageManager(Long.valueOf(page.getTotalCount())
				.intValue(), pageSize, 8);
		pm.goToPage(pageNo);
		request.setAttribute("pageHtml", pm.getPageCode());
		request.setAttribute("areaCode", areaCode);
		request.setAttribute("areaId", areaId);
		
		
		request.getSession().setAttribute("session_areaId",areaId);
		request.getSession().setAttribute("session_areaCode", areaCode);
		
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNo", pageNo);
		// savePage(page,query);
		return LIST_JSP;
	}

	public String query() {
		BaseUser query = new BaseUser();
		String areaId = request.getParameter("areaId");
		//String userState=request.getParameter("userState");
		//request.setAttribute("userState", userState);

		//String roleCode = request.getParameter("roleCode");
		//request.setAttribute("roleCode", roleCode);

		String isZhiShu = request.getParameter("isZhiShu");
		request.setAttribute("isZhiShu", isZhiShu);
		String nodeLevel = request.getParameter("nodeLevel");
		request.setAttribute("nodeLevel", nodeLevel);

		String searchType = request.getParameter("searchType");
		request.setAttribute("searchType", searchType);

		String searchText = request.getParameter("searchText");
		request.setAttribute("searchText", searchText);
		

		/*query.setDefaultRoleCode(roleCode);
		if(userState!=null&&!userState.equals("")){
			query.setIsIni(Integer.valueOf(userState));
		}*/
		int pageSize = request.getParameter("pageSize") != null ? Integer
				.valueOf(request.getParameter("pageSize")) : 15;
		int pageNo = 1;
		if (request.getParameter("pageNo") != null
				&& !request.getParameter("pageNo").equals("")) {
			pageNo = Integer.valueOf(request.getParameter("pageNo"));
		}
		
		if(isZhiShu!=null&&isZhiShu.equals("1")){
			if(areaId!=null&&!areaId.equals("")){
				query.setAreaId(areaId);
			}else{
				query.setAreaId("-1");
			}
		}else{
			if(areaId!=null&&!areaId.equals("")){
				BaseArea a=CacheBaseAreaManager.getNodeById(areaId);
				
				if(a.getAreaCode().length()==3){
					query.setProvinceId(areaId);
				}
				if(a.getAreaCode().length()==6){
					query.setCityId(areaId);
				}
				if(a.getAreaCode().length()==9){
					query.setCountyId(areaId);
				}
			}
		}

		Page page = baseUserManager.findPage(request, query, pageSize, pageNo);
		request.setAttribute("dataList", page.getResult());
		PageManager pm = new PageManager(Long.valueOf(page.getTotalCount()).intValue(), pageSize, 8);
		pm.goToPage(pageNo);
		request.setAttribute("pageHtml", pm.getPageCode());
		request.setAttribute("areaId", areaId);
		request.setAttribute("totalCount", page.getTotalCount());
		if (areaId !=null ){
			request.getSession().setAttribute("session_areaId",areaId);
			request.getSession().setAttribute("session_areaCode", CacheBaseAreaManager.getNodeById(areaId).getAreaCode());
		}
		request.setAttribute("roleList", baseRoleManager.getAll());
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageNo", pageNo);
		// savePage(page,query);
		return QUERY_JSP;
	}

	/** 查看对象 */
	public String show() {
		return SHOW_JSP;
	}

	/** 进入新增页面 */
	public String create() {
		request.setAttribute("defaultRoleCode", request
				.getParameter("roleCode"));
		request.setAttribute("areaId", request.getParameter("areaId"));
		BaseArea baseArea=baseAreaManager.get(request.getParameter("areaId"));
		request.setAttribute("baseArea", baseArea);
		return CREATE_JSP;
	}

	/** 保存新增对象 */
	public String save() {
		String areaId = request.getParameter("areaId");
		BaseArea baseArea = baseAreaManager.get(areaId);

		String ln = "9";
		Integer accountSeq = baseUserManager.accountSeq();
		StringBuffer sf = new StringBuffer();
		if(!baseArea.getId().equals("1"))
			sf.append(baseArea.getNationalCode());
		else
			sf.append("000000");//全国的默认000000
		sf.append(accountSeq);
		ln = ln + sf.toString();
		baseUser.setLoginName(ln);
		baseUser.setPassword(MD5.getMd5(baseUserManager.getRandomPassword(8)));// by
		// lihaiqing
		// modify
		// 2011-02-22
		// 8位随机数字
		baseUser.setCreTime(new Date());
		baseUser.setModTime(new Date());
		baseUser.setAreaId(baseArea.getId());
		baseUser.setIsIni(0);
		baseUser.setStatus("1");
		baseUser.setDeleteFlag("0");
		if (baseArea.getAreaCode().length() == 3) {
			baseUser.setProvinceId(baseArea.getId());
		}
		if (baseArea.getAreaCode().length() == 6) {
			baseUser.setProvinceId(CacheBaseAreaManager.getNodeByCode(
					baseArea.getAreaCode().substring(0, 3)).getId());
			baseUser.setCityId(baseArea.getId());
		}
		if (baseArea.getAreaCode().length() == 9) {
			baseUser.setProvinceId(CacheBaseAreaManager.getNodeByCode(
					baseArea.getAreaCode().substring(0, 3)).getId());
			baseUser.setCityId(CacheBaseAreaManager.getNodeByCode(
					baseArea.getAreaCode().substring(0, 6)).getId());
			baseUser.setCountyId(baseArea.getId());
		}
		baseUserManager.save(baseUser);
		baseUserManager.addReload(baseUser);
		
		return AREA_LIST_ACTION + "?queryParentCode=" + baseArea.getAreaCode()
				+ "&areaId=" + areaId;
	}

	/**
	 * 用户查询 进入更新页面
	 */
	public String edit() {
		passParams(request);
		String nodeLevel = request.getParameter("nodeLevel");
		request.setAttribute("nodeLevel", nodeLevel);
		request.setAttribute("baseUser", baseUser);
		
		BaseArea area=baseAreaManager.get(baseUser.getAreaId());
		if(area.getAreaCode().length()>=3)
		request.setAttribute("province", area.getAreaCode().substring(0,3));
		if(area.getAreaCode().length()>3)
		request.setAttribute("city", area.getAreaCode().substring(0,6));
		if(area.getAreaCode().length()>6)
		request.setAttribute("country", area.getAreaCode().substring(0,9));
		request.setAttribute("queryAreaCode", request.getParameter("queryAreaCode"));
		request.setAttribute("queryAreaId", request.getParameter("queryAreaId"));
		return EDIT_JSP;
	}

	/**
	 * 地区管理-点击用户设置，进入用户修改页面
	 * 
	 * @return
	 */
	public String areaUserEdit() {
		passParams(request);
		String nodeLevel = request.getParameter("nodeLevel");
		request.setAttribute("nodeLevel", nodeLevel);
		request.setAttribute("baseUser", baseUserManager.get(request.getParameter("id")));
		request.setAttribute("areaCode", request.getParameter("areaCode"));
		request.setAttribute("areaId", request.getParameter("areaId"));
		request.setAttribute("queryParentCode", request.getParameter("queryParentCode"));

		BaseArea area=baseAreaManager.get(baseUser.getAreaId());
		if(area.getAreaCode().length()>=3)
		request.setAttribute("province", area.getAreaCode().substring(0,3));
		if(area.getAreaCode().length()>3)
		request.setAttribute("city", area.getAreaCode().substring(0,6));
		if(area.getAreaCode().length()>6)
		request.setAttribute("country", area.getAreaCode().substring(0,9));
		request.setAttribute("queryAreaCode", request.getParameter("queryAreaCode"));
		request.setAttribute("queryAreaId", request.getParameter("queryAreaId"));
		return AREAUSER_EDIT_JSP;
	}

	/**
	 * 学校管理-用户管理-用户列表
	 * 
	 * @return
	 */
	public String schoollist() {
		BaseUser query = new BaseUser();
		String schoolId = request.getParameter("schoolId");
		query.setSchoolId(schoolId);

		int pageSize = 10;
		int pageNo = 1;
		if (request.getParameter("pageNo") != null
				&& !request.getParameter("pageNo").equals("")) {
			pageNo = Integer.valueOf(request.getParameter("pageNo"));
		}

		Page page = baseUserManager.getListByObject(query, pageSize, pageNo);
		request.setAttribute("dataList", page.getResult());
		PageManager pm = new PageManager(Long.valueOf(page.getTotalCount())
				.intValue(), pageSize, 8);
		pm.goToPage(pageNo);
		request.setAttribute("pageHtml", pm.getPageCode());
		request.setAttribute("schoolId", schoolId);
		request.setAttribute("areaId", request.getParameter("areaId"));
		request.setAttribute("schoolpageNo", request
				.getParameter("schoolpageNo"));
		// savePage(page,query);
		return SCHOOL_USER_JSP;
	}

	/**
	 * 学校管理-用户管理：点击用户修改，进入修改页面
	 * 
	 * @return
	 */
	public String schooledit() {
		passParams(request);
		request.setAttribute("schoolId", request.getParameter("schoolId"));
		request.setAttribute("userId", baseUser.getId());
		request.setAttribute("baseUser", baseUser);

		BaseArea area=baseAreaManager.get(baseUser.getAreaId());
		if(area.getAreaCode().length()>=3)
		request.setAttribute("province", area.getAreaCode().substring(0,3));
		if(area.getAreaCode().length()>3)
		request.setAttribute("city", area.getAreaCode().substring(0,6));
		if(area.getAreaCode().length()>6)
		request.setAttribute("country", area.getAreaCode().substring(0,9));
		return SCHOOL_EDIT_JSP;
	}

	/**
	 * 学校管理-用户管理：点击添加校长，进入用户新增页面
	 * 
	 * @return
	 */
	public String schoolUseradd() {
		request.setAttribute("schoolId", request.getParameter("schoolId"));
		request.setAttribute("defaultRoleCode", request
				.getParameter("roleCode"));
		return SCHOOLUSER_ADD_JSP;
	}

	/**
	 * 学校管理-用户管理：保存用户
	 * 
	 * @return
	 */
	public String schoolUserSave() {
		String schoolId = request.getParameter("schoolId");
		String realname = request.getParameter("realname");
		BaseSchool school = baseSchoolManager.get(schoolId);
		BaseArea baseArea = baseAreaManager.get(school.getAreaId());

		String ln = "9";
		Integer accountSeq = baseUserManager.accountSeq();
		StringBuffer sf = new StringBuffer();
		if(!baseArea.getId().equals("1"))
			sf.append(baseArea.getNationalCode());
		else
			sf.append("000000");//全国的默认000000
		sf.append(accountSeq);
		ln = ln + sf.toString();
		baseUser.setLoginName(ln);
		baseUser.setRealName(realname);
		baseUser.setPassword(MD5.getMd5(baseUserManager.getRandomPassword(8)));
		baseUser.setCreTime(new Date());
		baseUser.setModTime(new Date());
		baseUser.setAreaId(baseArea.getId());
		baseUser.setIsIni(0);
		baseUser.setStatus("1");
		baseUser.setDeleteFlag("0");
		baseUser.setSchoolId(schoolId);
		if (baseArea.getAreaCode().length() == 3) {
			baseUser.setProvinceId(baseArea.getId());
		}
		if (baseArea.getAreaCode().length() == 6) {
			baseUser.setProvinceId(CacheBaseAreaManager.getNodeByCode(
					baseArea.getAreaCode().substring(0, 3)).getId());
			baseUser.setCityId(baseArea.getId());
		}
		if (baseArea.getAreaCode().length() == 9) {
			baseUser.setProvinceId(CacheBaseAreaManager.getNodeByCode(
					baseArea.getAreaCode().substring(0, 3)).getId());
			baseUser.setCityId(CacheBaseAreaManager.getNodeByCode(
					baseArea.getAreaCode().substring(0, 6)).getId());
			baseUser.setCountyId(baseArea.getId());
		}
		baseUserManager.save(baseUser);
		baseUserManager.addReload(baseUser);

		return SCHOOL_LIST_ACTION + "?schoolId=" + schoolId;
	}

	/**
	 * 用户查询 保存更新对象
	 */
	public String update() {
		String YYYY = request.getParameter("YYYY");// 年
		String MM = request.getParameter("MM");// 月
		String DD = request.getParameter("DD");// 日
		String birthday = YYYY + "-" + MM + "-" + DD;
		

		BaseUser baseUser = this.baseUser;// getBaseUser();
		request.setAttribute("areaId", baseUser.getAreaId());
		String[] hobbyCheck=request.getParameterValues("hobbyCheck");
		String hobbyStr="";
		if (hobbyCheck!=null){
		for(int i=0;i<hobbyCheck.length;i++)
		{
			hobbyStr=hobbyStr+hobbyCheck[i];
			if (i!=hobbyCheck.length-1)
			{
				hobbyStr=hobbyStr+",";
			}
		}
		}

		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String country=request.getParameter("country");
		
		String areaId=null;
		if(province!=null&&!province.trim().equals("")){
			areaId=CacheBaseAreaManager.getNodeByCode(province).getId();
			baseUser.setProvinceId(areaId);
		}
		if(city!=null&&!city.trim().equals("")){
			areaId=CacheBaseAreaManager.getNodeByCode(city).getId();
			baseUser.setCityId(areaId);
		}
		if(country!=null&&!country.trim().equals("")){
			areaId=CacheBaseAreaManager.getNodeByCode(country).getId();
			baseUser.setCountyId(areaId);
		}
		if(areaId!=null){
			baseUser.setAreaId(areaId);
		}
		baseUser.setModTime(new Date());
		baseUserManager.update(this.baseUser);
		redisManager.saveBaseUser(this.baseUser);
		baseUserManager.flush();
		baseUserManager.evit(this.baseUser);

		String nodeLevel = request.getParameter("nodeLevel");
		request.setAttribute("nodeLevel", nodeLevel);
		
		return query();
	}
	//地区管理员信息修改
	public String updateArea(){
		String YYYY = request.getParameter("YYYY");// 年
		String MM = request.getParameter("MM");// 月
		String DD = request.getParameter("DD");// 日
		String birthday = YYYY + "-" + MM + "-" + DD;

		BaseUser baseUser = this.baseUser;// getBaseUser();
		request.setAttribute("areaId", baseUser.getAreaId());
		baseUser.setModTime(new Date());
		baseUserManager.update(this.baseUser);
		baseUserManager.flush();
		baseUserManager.evit(this.baseUser);

		String nodeLevel = request.getParameter("nodeLevel");
		request.setAttribute("nodeLevel", nodeLevel);
		
		//转跳到地区修改成功也首页
		request.getSession().setAttribute("ssoUser", this.getBaseUser());
		return BASE_USER_AREA_SUCCESS;
	}
	/**
	 * 地区管理-用户设置-用户修改保存
	 * 
	 * @return
	 */
	public String areaUserUpdate() {
		//String YYYY = request.getParameter("YYYY");// 年
		//String MM = request.getParameter("MM");// 月
		//String DD = request.getParameter("DD");// 日
		//String birthday = YYYY + "-" + MM + "-" + DD;
		SetValueUtil.autoRequestSetAttribute(request);
		String uid=request.getParameter("id");
		if(isNullOrEmptyString(uid)){
			return "";
		}
		BaseUser baseUser =baseUserManager.get(uid);
		String realName=request.getParameter("realname");
		String mobil=request.getParameter("mobile");
		String email=request.getParameter("email");
		String officePhone=request.getParameter("officePhone");
		String address=request.getParameter("address");
		String qq=request.getParameter("qq");
		String zipcode=request.getParameter("zipcode");
		baseUser.setRealName(realName);
		baseUser.setMobile(mobil);
		baseUser.setEmail(email);
		baseUser.setOfficePhone(officePhone);
		baseUser.setAddress(address);
		baseUser.setQq(qq);
		baseUser.setZipcode(zipcode);
		request.setAttribute("baseUser", baseUser);
		
		
		request.setAttribute("areaId", baseUser.getAreaId());
		
		//String province=request.getParameter("province");
		//String city=request.getParameter("city");
		//String country=request.getParameter("country");
		String temp=baseUser.getDefaultRoleCode();
		//---hyl 注释，因为用户不可以修改地区  start
		/*String areaId=null;
		if(province!=null&&!province.trim().equals("")){
			areaId=CacheBaseAreaManager.getNodeByCode(province).getId();
			baseUser.setProvinceId(areaId);
			baseUser.setDefaultRoleCode("role.provinceManager");
		}
		if(city!=null&&!city.trim().equals("")){
			areaId=CacheBaseAreaManager.getNodeByCode(city).getId();
			baseUser.setCityId(areaId);
			baseUser.setDefaultRoleCode("role.cityManager");
		}else{
			baseUser.setCityId("");
		}
		if(country!=null&&!country.trim().equals("")){
			areaId=CacheBaseAreaManager.getNodeByCode(country).getId();
			baseUser.setCountyId(areaId);
			baseUser.setDefaultRoleCode("role.countryManager");
		}else{
			baseUser.setCountyId("");
		}
		if(areaId!=null){
			baseUser.setAreaId(areaId);
		}*/
		//---hyl 注释，因为用户不可以修改地区  end
		if(!isNullOrEmptyString(temp)&&temp.equals("role.areaObserve")){
			baseUser.setDefaultRoleCode("role.areaObserve");
		}
		baseUser.setModTime(new Date());
		baseUserManager.update(baseUser);
		baseUserManager.flush();
		baseUserManager.evit(baseUser);
		CacheBaseUserManager.delBaseUser(baseUser.getId());
		CacheBaseUserManager.setBaseUser(baseUser);
		String nodeLevel = request.getParameter("nodeLevel");
		request.setAttribute("nodeLevel", nodeLevel);
		request.setAttribute("queryParentCode", request.getParameter("queryParentCode"));
		/**********系统的真实姓名初始化是：地区+管理员，若发生了变化，则显示：修改后姓名(区域+角色（管理员|视察员）)，否则只显示：真是姓名****************************/
		Object o=request.getSession().getAttribute("ssoUser");
		if(o!=null){
			BaseUser user=(BaseUser)o;
			if(user!=null){
				if(user.getId().equals(uid)){
					String loginIniName="";
					BaseArea baseArea =CacheBaseAreaManager.getNodeById(baseUser.getAreaId());
					if(baseArea!=null){
						String inirealName="";
						if(baseUser.getDefaultRoleCode().equals("role.areaObserve")){
							inirealName=baseArea.getAreaName()+"视察员";
						}else if(baseUser.getDefaultRoleCode().equals("role.schoolManager")){
							BaseSchool baseSchool= CacheBaseSchoolManager.getSchool(baseUser.getSchoolId());
							if(baseSchool!=null){
								inirealName=baseSchool.getSchoolName()+"管理员";
							}
						}else if(baseUser.getDefaultRoleCode().equals("role.provinceManager")||baseUser.getDefaultRoleCode().equals("role.cityManager")||baseUser.getDefaultRoleCode().equals("role.countryManager")){
							inirealName=baseArea.getAreaName()+"管理员";
						}else{
							inirealName=baseUser.getRealName();
						}
						if(StringUtils.isNotBlank(baseUser.getRealName())){
							if(!baseUser.getRealName().trim().equals(inirealName)){
								loginIniName=baseUser.getRealName()+"("+inirealName+")";
							}else {
								loginIniName=baseUser.getRealName();
							}
						}else {
							loginIniName=inirealName;
						}
					}
					if(StringUtils.isNotBlank(loginIniName)){
						request.getSession().setAttribute("loginIniName", loginIniName);
					}else {
						if(StringUtils.isNotBlank(baseUser.getRealName())){
							request.getSession().setAttribute("loginIniName", baseUser.getRealName());
						}
					}
					
				}
			}
		}
		return areaUserEdit();
	}


	/**
	 * 学校管理-用户管理-保存修改用户信息
	 * 
	 * @return
	 */
	public String schoolupdate() {
		String schoolId = request.getParameter("schoolId");
		String YYYY = request.getParameter("YYYY");// 年
		String MM = request.getParameter("MM");// 月
		String DD = request.getParameter("DD");// 日
		String birthday = YYYY + "-" + MM + "-" + DD;
		
		BaseUser baseUser = getBaseUser();

		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String country=request.getParameter("country");
		
		String areaId=null;
		if(province!=null&&!province.trim().equals("")){
			areaId=CacheBaseAreaManager.getNodeByCode(province).getId();
			baseUser.setProvinceId(areaId);
		}
		if(city!=null&&!city.trim().equals("")){
			areaId=CacheBaseAreaManager.getNodeByCode(city).getId();
			baseUser.setCityId(areaId);
		}
		if(country!=null&&!country.trim().equals("")){
			areaId=CacheBaseAreaManager.getNodeByCode(country).getId();
			baseUser.setCountyId(areaId);
		}
		if(areaId!=null){
			baseUser.setAreaId(areaId);
		}

		baseUser.setModTime(new Date());
		baseUserManager.update(baseUser);
		
		return SCHOOL_LIST_ACTION + "?schoolId=" + schoolId;
	}

	/** 删除对象 */
	public String delete() {
		String areaCode = request.getParameter("areaCode");
		String areaId = request.getParameter("areaId");
		for (int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.String id = new java.lang.String((String) params
					.get("userId"));
			baseUserManager.removeById(id);
		}
		return AREA_LIST_ACTION + "?queryParentCode=" + areaCode + "&areaId="
				+ areaId;
	}

	public String index() {
		String queryParentCode=request.getParameter("queryParentCode");
		request.setAttribute("queryParentCode", queryParentCode);
		String roleCode = request.getParameter("roleCode");
		request.setAttribute("roleCode", roleCode);
		String userState=request.getParameter("userState");
		request.setAttribute("userState", userState);
		return INDEX_JSP;
	}
	
	public String treeIndex(){
		String roleCode = request.getParameter("roleCode");
		request.setAttribute("roleCode", roleCode);
		String userState=request.getParameter("userState");
		if(userState==null){
			userState="";
		}
		request.setAttribute("userState", userState);
		String queryParentCode=request.getParameter("queryParentCode");
		
		String wsAreaCode = String.valueOf(request.getSession().getAttribute("wsAreaCode"));
		if(queryParentCode==null||queryParentCode.trim().equals("")||queryParentCode.equals("-1")){
			if(request.getSession().getAttribute("session_areaCode")!=null){
				queryParentCode=request.getSession().getAttribute("session_areaCode").toString();
			}else{
				queryParentCode = wsAreaCode;
			}		
		}
		
		BaseArea area=new BaseArea ();
		if (wsAreaCode.equals("-1")) {
			area = CacheBaseAreaManager.getNodeByCode("-1");
			request.setAttribute("areaName", "全国");
			request.setAttribute("nodeLevel", "0");
			request.setAttribute("areaId",area.getId());
		} else {
			area = CacheBaseAreaManager.getNodeByCode(wsAreaCode);
			request.setAttribute("areaName", area.getAreaName());
			request.setAttribute("nodeLevel", area.getNodeLevel());
			request.setAttribute("areaId",area.getId());
		}
		
		if(area.getNodeType().equals("1")){
			Map<String,GeneralTreeNode> ht=CacheBaseAreaManager.getAllTreeByParentCode(wsAreaCode);
			request.setAttribute("dataList", ht);
		}
		request.setAttribute("queryParentCode", queryParentCode);
		
		request.getSession().setAttribute("session_areaId", CacheBaseAreaManager.getNodeByCode(queryParentCode).getId());
		request.getSession().setAttribute("session_areaCode", queryParentCode);
		
		request.setAttribute("menuFlag", "xfdj");
		request.setAttribute("menuFlag_1", "userList");
		
		return TREE_INDEX_JSP;
	}

	/**
	 * 学校用户查询
	 */
	
	public String schoolUser() {
		String roleCode = request.getParameter("roleCode") != null ? request
				.getParameter("roleCode") : "";
		BaseUser baseUser = (BaseUser) request.getSession().getAttribute(
				"ssoUser");
		String schoolId = baseUser.getSchoolId();

		List<BaseUser> dataList = baseUserManager
				.getUserListBySchoolIdAndRoleCode(schoolId, roleCode);

		request.setAttribute("dataList", dataList);
		request.setAttribute("schoolId", schoolId);
		request.setAttribute("roleCode", roleCode);
		return SCHOOL_USER_LIST_JSP;
	}

	/**
	 * 学校用户修改
	 * 
	 * @return
	 */
	public String schoolUserEdit() {
		passParams(request);
		String nodeLevel = request.getParameter("nodeLevel");
		request.setAttribute("nodeLevel", nodeLevel);
		request.setAttribute("baseUser", baseUserManager.get(request.getParameter("id")));
		request.setAttribute("areaCode", request.getParameter("areaCode"));
		request.setAttribute("areaId", request.getParameter("areaId"));
		return SCHOOL_USER_EDIT_JSP;
	}

	/** 保存更新对象 */
	public String schoolUserUpdate() {
		String YYYY = request.getParameter("YYYY");// 年
		String MM = request.getParameter("MM");// 月
		String DD = request.getParameter("DD");// 日
		String birthday = YYYY + "-" + MM + "-" + DD;

		BaseUser baseUser = this.baseUser;// getBaseUser();
		request.setAttribute("areaId", baseUser.getAreaId());
		baseUser.setModTime(new Date());
		baseUserManager.update(this.baseUser);
		baseUserManager.flush();
		baseUserManager.evit(this.baseUser);
		
		String nodeLevel = request.getParameter("nodeLevel");
		request.setAttribute("nodeLevel", nodeLevel);

		return schoolUser();
	}

	/** 进入学校用户新增页面 */
	public String schoolUserCreate() {
		// 跳转到新增页面
		passParams(request);
		String roleCode = request.getParameter("roleCode");
		request.setAttribute("roleCode", roleCode);
		return SCHOOL_USER_CREATE_JSP;
	}

	public String schoolAdminSave() {
		BaseUser loginUser = (BaseUser) request.getSession().getAttribute(
				"ssoUser");
		String schoolId = loginUser.getSchoolId(); // 当前登录用户所在学校的schoolid

		String roleCode = request.getParameter("roleCode");
		BaseSchool school = baseSchoolManager.get(schoolId);
		BaseArea baseArea = baseAreaManager.get(school.getAreaId());

		String ln = "9";
		Integer accountSeq = baseUserManager.accountSeq();
		StringBuffer sf = new StringBuffer();
		if(!baseArea.getId().equals("1"))
			sf.append(baseArea.getNationalCode());
		else
			sf.append("000000");//全国的默认000000
		sf.append(accountSeq);
		ln = ln + sf.toString();
		baseUser.setLoginName(ln);
		baseUser.setPassword(MD5.getMd5(baseUserManager.getRandomPassword(8)));
		baseUser.setCreTime(new Date());
		baseUser.setModTime(new Date());
		baseUser.setAreaId(baseArea.getId());
		baseUser.setIsIni(0);
		baseUser.setStatus("1");
		baseUser.setDeleteFlag("0");
		baseUser.setSchoolId(schoolId);
		baseUser.setDefaultRoleCode(roleCode); // 设置权限
		if (baseArea.getAreaCode().length() == 3) {
			baseUser.setProvinceId(baseArea.getId());
		}
		if (baseArea.getAreaCode().length() == 6) {
			baseUser.setProvinceId(CacheBaseAreaManager.getNodeByCode(
					baseArea.getAreaCode().substring(0, 3)).getId());
			baseUser.setCityId(baseArea.getId());
		}
		if (baseArea.getAreaCode().length() == 9) {
			baseUser.setProvinceId(CacheBaseAreaManager.getNodeByCode(
					baseArea.getAreaCode().substring(0, 3)).getId());
			baseUser.setCityId(CacheBaseAreaManager.getNodeByCode(
					baseArea.getAreaCode().substring(0, 6)).getId());
			baseUser.setCountyId(baseArea.getId());
		}
		baseUserManager.save(baseUser);
		baseUserManager.addReload(baseUser);
		
		return schoolUser();
	}


	/**
	 * 电话号码是否唯一
	 * 
	 */
	public String checkMobile(){
		String mobile=request.getParameter("mobile");
		String id=request.getParameter("userId");
		//check mobile begin
		String returnValue=baseUserManager.getCountByMobile(mobile,id);	
		//check mobile end	
		// 告诉页面返回处理结果 开始

		response.setContentType("text/html;charset=utf-8");
		java.io.PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(returnValue);
		} catch (java.io.IOException e) {
			returnValue = "1";//异常默认已经存在
			e.printStackTrace();
		}
		// 告诉页面返回处理结果结束
		return null;
	}

	/**
	 * 邮箱号码是否唯一
	 * 
	 */
	public String checkEmail(){
		String email=request.getParameter("email");
		String id=request.getParameter("userId");
		//check email begin
		String returnValue="1";
		if(null!=email&&!email.equals("")&&!email.equals("null")){
			returnValue=baseUserManager.getCountByEmail(email,id);
		}
		//check email end
		// 告诉页面返回处理结果 开始
		response.setContentType("text/html;charset=utf-8");
		java.io.PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(returnValue);
		} catch (java.io.IOException e) {
			returnValue = "1";//异常默认已经存在
			e.printStackTrace();
		}
		// 告诉页面返回处理结果结束
		
		
		return null;
	}
	/** 
	 * 用户查询-锁定、解锁用户
	 * 封杀用户 
	/**
	 * 用户查询-锁定、解锁用户 封杀用户
	 * 
	 */
	public String forbiddenUser() {
		// 处理封杀用户的程序开始
		String flag = request.getParameter("flag");
		String id = request.getParameter("id");
		BaseUser baseUser = baseUserManager.get(id);
		baseUser.setStatus(flag);// 锁定或者解锁 锁定：0 解锁：1
		baseUserManager.save(baseUser);
		baseUserManager.updateReload(baseUser);
		baseUserManager.flush();
		baseUserManager.evit(baseUser);
		
		// 告诉页面返回处理结果 开始
		response.setContentType("text/html;charset=utf-8");
		java.io.PrintWriter pw = null;
		String returnValue = "1";
		try {
			pw = response.getWriter();
			pw.write(returnValue);
		} catch (java.io.IOException e) {
			returnValue = "0";
			e.printStackTrace();
		}
		// 告诉页面返回处理结果结束
		return null;
	}

	/**
	 * 查看密码
	 * 
	 * @return
	 */
	public String viewPwd() {
		String userId = request.getParameter("userId");
		BaseUser user = baseUserManager.get(userId);
		request.setAttribute("user", user);
		return VIEW_USER_PWD_JSP;
	}
	
	public String rePwd() {
		String userId = request.getParameter("userId");
		BaseUser user = baseUserManager.get(userId);
		String pwd=baseUserManager.getRandomPasswordNum(6);
		user.setPassword(MD5.getMd5(pwd));
		baseUserManager.update(user);
		request.setAttribute("user", user);
		request.setAttribute("pwd", pwd);
		return VIEW_USER_PWD_JSP;
	}

	/**
	 * 公共参数传递.
	 */
	protected void passParams(HttpServletRequest request) {
		
	}

	/**
	 * 修改用户的密码(需要转跳页面)
	 * 
	 * @return
	 */
	public String editUserPassword() {

		return EDIT_USER_PASSWORD;
	}

	/**
	 * 保存密码修改
	 */
	public String saveEditUserPassword() {
		String isAjax = request.getParameter("isAjax");
		String newpassword=request.getParameter("newpassword");
		if(newpassword==null||newpassword.equals("")){
			response.setContentType("text/html;charset=utf-8");
			java.io.PrintWriter pw = null;
			try {
				pw = response.getWriter();
				pw.write("0");
			} catch (java.io.IOException e) {
				e.printStackTrace();
			}
			return null;
		}else{
			BaseUser user = (BaseUser) request.getSession().getAttribute("ssoUser");
			user.setPassword(MD5.getMd5(newpassword));
			baseUserManager.save(user);
			baseUserManager.updateReload(user);
			baseUserManager.flush();
			baseUserManager.evit(user);
			
			response.setContentType("text/html;charset=utf-8");
			java.io.PrintWriter pw = null;
			try {
				pw = response.getWriter();
				pw.write("1");
			} catch (java.io.IOException e) {
				e.printStackTrace();
			}
		}

	/*	if("true".equals(isAjax)){
			return null;
		}*/
		return EDIT_USER_PASSWORD_SUCCESS;
	}
	//保存学校管理员密码 
	public String saveSchoolPwd() {
		BaseUser user = (BaseUser) request.getSession().getAttribute("ssoUser");
		user.setPassword(MD5.getMd5(baseUser.getPassword()));
		baseUserManager.save(user);
		baseUserManager.updateReload(user);
		baseUserManager.flush();
		baseUserManager.evit(user);
		
		return EDIT_SCHOOLUSER_PASSWORD_SUCCESS;
	}
	//保存地区管理员信息
	public String saveAreaPassword() {
		BaseUser user = (BaseUser) request.getSession().getAttribute("ssoUser");
		user.setPassword(MD5.getMd5(baseUser.getPassword()));
		baseUserManager.save(user);
		baseUserManager.updateReload(user);
		baseUserManager.flush();
		baseUserManager.evit(user);
		//转跳到地区信息管理密码页
		request.setAttribute("loadparent", "load");
		
		return BASE_USER_AREA_PASSWORD;
	}

	/**
	 * 个人信息进入页面
	 */
	public String userEdit() {
		passParams(request);
		baseUserManager.get(id);
		request.setAttribute("baseUser", this.getBaseUser());
		return BASE_USER_EDIT;
	}
	/**
	 * 地区管理员信息修改
	 * @return
	 */
	public String userEditArea(){
		passParams(request);
		request.setAttribute("baseUser", this.getBaseUser());
		//转跳到个人信息显示页面
		return BASE_USER_AREA;
	}
	
	
	public String saveAreaUserImg(){
	 try {
		//prepare();
		String photoPath=request.getParameter("photoPath");
		BaseUser baseUser=this.getBaseUser();
		baseUser.setPhotoPath(photoPath);
		baseUserManager.save(baseUser);
		baseUserManager.updateReload(baseUser);
		setBaseUser(baseUser);			
		request.getSession().setAttribute("ssoUser", this.getBaseUser());
		request.setAttribute("loadparent", "load");

		
	} catch (Exception e) {
		e.printStackTrace();
	}
	 return SAVE_AREA_USER_IMG;
	}
	public String saveSchoolUserImg(){
		 try {
			//prepare();
			String photoPath=request.getParameter("photoPath");
			BaseUser baseUser=this.getBaseUser();
			baseUser.setPhotoPath(photoPath);
			baseUserManager.save(baseUser);
			baseUserManager.updateReload(baseUser);
			setBaseUser(baseUser);
			request.getSession().setAttribute("ssoUser", this.getBaseUser());
			request.setAttribute("loadparent", "load");

		} catch (Exception e) {
			e.printStackTrace();
		}
		 return SAVE_SCHOOL_USER_IMG;
		}
	public BaseUser getBaseUser() {
		return baseUser;
	}

	public void setBaseUser(BaseUser baseUser) {
		this.baseUser = baseUser;
	}

	public void setBaseRoleManager(BaseRoleManager baseRoleManager) {
		this.baseRoleManager = baseRoleManager;
	}

	public BasePropertiesManager getBasePropertiesManager() {
		return basePropertiesManager;
	}

	public void setBasePropertiesManager(
			BasePropertiesManager basePropertiesManager) {
		this.basePropertiesManager = basePropertiesManager;
	}

	public List<BaseProperties> getDutyList() {
		return dutyList;
	}

	public List<BaseProperties> getTitleList() {
		return titleList;
	}

	public List<BaseProperties> getDegreeList() {
		return degreeList;
	}

	public List<BaseProperties> getSubjectList() {
		return subjectList;
	}

	public List<BaseProperties> getMzList() {
		return mzList;
	}

	public void setMzList(List<BaseProperties> mzList) {
		this.mzList = mzList;
	}

	public void setDutyList(List<BaseProperties> dutyList) {
		this.dutyList = dutyList;
	}

	public void setTitleList(List<BaseProperties> titleList) {
		this.titleList = titleList;
	}

	public void setDegreeList(List<BaseProperties> degreeList) {
		this.degreeList = degreeList;
	}

	public void setSubjectList(List<BaseProperties> subjectList) {
		this.subjectList = subjectList;
	}

	public void setBaseAreaManager(BaseAreaManager baseAreaManager) {
		this.baseAreaManager = baseAreaManager;
	}

	public void setBaseSchoolManager(BaseSchoolManager baseSchoolManager) {
		this.baseSchoolManager = baseSchoolManager;
	}



	
	

	public String updateUserInfo(){
		String type=request.getParameter("type");
		request.setAttribute("type", type);
		String schoolId=request.getParameter("schoolId");
		request.setAttribute("schoolId", schoolId);
		String classId=request.getParameter("classId");
		request.setAttribute("classId", classId);
		String studentId=request.getParameter("studentId");
		request.setAttribute("studentId", studentId);
		String YYYY = request.getParameter("YYYY");// 年
		String MM = request.getParameter("MM");// 月
		String DD = request.getParameter("DD");// 日
		String birthday = YYYY + "-" + MM + "-" + DD;
		BaseUser baseUser= this.baseUser;
		request.setAttribute("areaId", baseUser.getAreaId());
		baseUser.setModTime(new Date());
 		baseUserManager.update(baseUser);
		baseUserManager.flush();
		baseUserManager.evit(baseUser);
		String nodeLevel = request.getParameter("nodeLevel");
		request.setAttribute("nodeLevel", nodeLevel);

		//更新session内存
		request.getSession().setAttribute("ssoUser", baseUser);

		return null;
	}
		
	public String resetPwd(){
		baseUser.setPassword(MD5.getMd5("123456"));
		baseUserManager.update(baseUser);
		return null;
	}




	/**
	 * 昵称是否唯一
	 * 
	 */
	public String checkNickName(){
		String nickname=request.getParameter("nickname");
		String userId = request.getParameter("userId");
		//check email begin
		String returnValue=baseUserManager.getCountByNickname(nickname,userId);
		//check email end
		// 告诉页面返回处理结果 开始
		response.setContentType("text/html;charset=utf-8");
		java.io.PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(returnValue);
		} catch (java.io.IOException e) {
			returnValue = "1";//异常默认已经存在
			e.printStackTrace();
		}
		// 告诉页面返回处理结果结束
		
		
		return null;
	}
	/**
	 * 保存用户信息
	 * @return
	 */
	public String addUser(){
		String YYYY = request.getParameter("YYYY");// 年
		String MM = request.getParameter("MM");// 月
		String DD = request.getParameter("DD");// 日
		String birthday = YYYY + "-" + MM + "-" + DD;
		String ln = "9";
		Integer accountSeq = baseUserManager.accountSeq();
		StringBuffer sf = new StringBuffer();
		sf.append("000000");//全国的默认000000
		sf.append(accountSeq);
		ln = ln + sf.toString();
		baseUser.setLoginName(ln);
		baseUser.setPassword(MD5.getMd5("123456"));// by	
		baseUser.setAreaId("1");
		baseUser.setIsIni(0);
		baseUser.setStatus("1");
		baseUser.setDeleteFlag("0");
		baseUser.setDefaultRoleCode("role.teacher");
		baseUser.setModTime(new Date());
		String[] hobbyCheck=request.getParameterValues("hobbyCheck");
		String hobbyStr="";
		if (hobbyCheck!=null){
		for(int i=0;i<hobbyCheck.length;i++)
		{
			hobbyStr=hobbyStr+hobbyCheck[i];
			if (i!=hobbyCheck.length-1)
			{
				hobbyStr=hobbyStr+",";
			}
		}
		}
		
		baseUserManager.save(baseUser);
		redisManager.saveBaseUser(baseUser);

		return query();
	}

		
		
		public void getMobileRand()
		{
			String mobile=request.getParameter("mobile");
			String sessionId=request.getSession().getId();
			String rands="";
//			if(redisManager.objectHasKey(sessionId+"_mobileRand")){
//				Object o=redisManager.getOValue(sessionId+"_mobileRand");
//				String rand="";
//				if(o!=null&&!o.equals("")){
//					rand=o.toString();
//				}else{
//					rand=baseUserManager.getRandomPasswordNum(6);
//					redisManager.setOValue(sessionId+"_mobileRand", rand);
//					//5秒后过期，单位参照
//					redisManager.expire(sessionId+"_mobileRand", 300l);
//				}
//				rands=rand;
//			}else{
				String rand=baseUserManager.getRandomPasswordNum(6);
				
				redisManager.setOValue(sessionId+"_mobileRand", rand);
				//5秒后过期，单位参照
				redisManager.expire(sessionId+"_mobileRand", 300l);
				
				rands=rand;
//			}
			
			try {
				//输入软件序列号和密码
				String sn=My431Function.getValueByCode("base.mobile.sn");//"SDK-BBX-010-19225";
				String pwd=My431Function.getValueByCode("base.mobile.pwd");//"d46fc4cB";
				String serviceUrl=My431Function.getValueByCode("base.mobile.serviceUrl");//"http://sdk.entinfo.cn:8060/webservice.asmx";
				String content=URLEncoder.encode("验证码为"+rands+"(切勿告知他人),请在页面中输入以完成验证，有问题请致电400-8980-910 "+"【信息化进展系统】", "utf8");	
				Client client=new Client(sn,pwd,serviceUrl);
				
				String result_mt = client.mdSmsSend_u(mobile, content, "", "", "");
				
				if(result_mt.startsWith("-")||result_mt.equals(""))//发送短信，如果是以负号开头就是发送失败。
				{
					System.out.print("发送失败！返回值为："+result_mt+"请查看webservice返回值对照表");
					response.setContentType("text/html;charset=utf-8");
					java.io.PrintWriter pw = null;
					try {
						pw = response.getWriter();
						pw.write("0");
					} catch (java.io.IOException e) {
						e.printStackTrace();
					}
				}
				//输出返回标识，为小于19位的正数，String类型的。记录您发送的批次。
				else
				{
					
					System.out.print("发送成功，返回值为："+result_mt+DateFormater.DateTimeToString(new Date()));
					BaseProperties sendCnt=CacheBasePropertiesManager.getPropertiesByPropertyKey("base.mobile.send.cnt");
					BaseProperties haveCnt=CacheBasePropertiesManager.getPropertiesByPropertyKey("base.mobile.have.cnt");
					if(sendCnt!=null){
						sendCnt.setPropertyValue(sendCnt.getPropertyValue()==null?"1":Integer.valueOf(sendCnt.getPropertyValue())+1+"");
						basePropertiesManager.update(sendCnt);
						basePropertiesManager.updateOne(sendCnt,request.getSession().getServletContext().getRealPath("WEB-INF/conf/"));
					}
					if(haveCnt!=null){
						String result=client.getBalance();
						if(result!=null){
							haveCnt.setPropertyValue(result);
						}
						basePropertiesManager.update(haveCnt);
						basePropertiesManager.updateOne(haveCnt,request.getSession().getServletContext().getRealPath("WEB-INF/conf/"));
					}
					
					
					response.setContentType("text/html;charset=utf-8");
					java.io.PrintWriter pw = null;
					try {
						pw = response.getWriter();
						pw.write("1");
					} catch (java.io.IOException e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		/**
		 * 编辑个人信息
		 * @return
		 * @author hmc
		 */
		public String saveUserEdit(){

			String userId=request.getParameter("id");
			BaseUser base=baseUserManager.reloadRedisUser(userId);
			
			String realName=(String)request.getParameter("urealName");
			base.setRealName(realName);
			String mobilPhone=(String) request.getAttribute("mobile");
			base.setMobile(mobilPhone);
			String tel=(String) request.getAttribute("officePhone");
			base.setOfficePhone(tel);
			String addr=(String) request.getAttribute("address");
			base.setAddress(addr);
			String zipCode=(String) request.getAttribute("zipcode");
			base.setZipcode(zipCode);
			String qq=(String) request.getAttribute("qq");
			base.setQq(qq);
			String email=(String) request.getAttribute("email");
			base.setEmail(email);
			baseUserManager.reEditByUserId(base);
			//request.setAttribute("baseUser1", base);
			request.getSession().removeAttribute("ssoUser");
			request.getSession().setAttribute("ssoUser", base);
			redisManager.removeOValue("global.base.getBaseUser.bySchoolId."+base.getSchoolId());
			redisManager.setOValue("global.base.getBaseUser.bySchoolId."+base.getSchoolId(), base);
			request.setAttribute("isedit", "1");
			
			/**********系统的真实姓名初始化是：地区+管理员，若发生了变化，则显示：修改后姓名(区域+角色（管理员|视察员）)，否则只显示：真是姓名****************************/
			String loginIniName="";
			BaseArea baseArea =CacheBaseAreaManager.getNodeById(base.getAreaId());
			if(baseArea!=null){
				String inirealName="";
				if(baseUser.getDefaultRoleCode().equals("role.areaObserve")){
					inirealName=baseArea.getAreaName()+"视察员";
				}else if(baseUser.getDefaultRoleCode().equals("role.schoolManager")){
					BaseSchool baseSchool= CacheBaseSchoolManager.getSchool(baseUser.getSchoolId());
					if(baseSchool!=null){
						inirealName=baseSchool.getSchoolName()+"管理员";
					}
				}else if(baseUser.getDefaultRoleCode().equals("role.provinceManager")||baseUser.getDefaultRoleCode().equals("role.cityManager")||baseUser.getDefaultRoleCode().equals("role.countryManager")){
					inirealName=baseArea.getAreaName()+"管理员";
				}else{
					inirealName=baseUser.getRealName();
				}
				if(StringUtils.isNotBlank(baseUser.getRealName())){
					if(!baseUser.getRealName().trim().equals(inirealName)){
						loginIniName=baseUser.getRealName()+"("+inirealName+")";
					}else {
						loginIniName=baseUser.getRealName();
					}
				}else {
					loginIniName=inirealName;
				}
			}
			if(StringUtils.isNotBlank(loginIniName)){
				request.getSession().setAttribute("loginIniName", loginIniName);
			}else {
				if(StringUtils.isNotBlank(baseUser.getRealName())){
					request.getSession().setAttribute("loginIniName", baseUser.getRealName());
				}
			}
			/**************************************/
			
			
			return "/base/user_edit.jsp";
		}
		
		/**
		 * 
		 * @author   hmc    2015年4月17日  下午6:08:10
		 * @param requestName   
		 * @param code
		 * @return
		 */
		public String changeCodeAndSetRequest(String requestName,String code){
			String temp[]=code.split(",");
			if(temp!=null&&temp.length==3){
				String requestCode=temp[2];
				request.setAttribute(""+requestName+"", requestCode);
				code=temp[0];
				return requestCode;
			}else{
				String requestCode=code.split(",")[1];
				request.setAttribute(""+requestName+"", requestCode);
				code=code.split(",")[0];
				return requestCode;
			}
		}
		
		/**
		 * 查询从缓存查询Area信息，如果不存在去数据库查询，并将对象返回且放入缓存
		 * @author    hmc    2015年4月17日  下午5:25:13
		 * @param areaCode 数字编码
		 * @param areaId	
		 * @return
		 */
		public BaseArea getBaseAreaAndSetRedis(String areaCode,String areaId){
			BaseArea baseAreaTemp=null;
			if(!isNullOrEmptyString(areaCode)){
				baseAreaTemp=CacheBaseAreaManager.getNodeByCode(areaCode);
				CacheBaseAreaManager.removeNodeByCode(baseAreaTemp.getAreaCode());
				CacheBaseAreaManager.setAreaCodeKeyMap(baseAreaTemp.getAreaCode(), baseAreaTemp);
			}
		   if(!isNullOrEmptyString(areaId)){
			   baseAreaTemp=CacheBaseAreaManager.getNodeById(areaId);
			   CacheBaseAreaManager.removeNodeById(baseAreaTemp.getId());
			   CacheBaseAreaManager.setAreaIdKeyMap(baseAreaTemp.getId(), baseAreaTemp);
		   }
			return baseAreaTemp;
		}
		/**
		 * 单独创建地区视察员
		 * @author    hmc    2015年4月29日  下午6:07:01
		 */
		public String  addInspector(){
			BaseUser locationUser=(BaseUser) request.getSession().getAttribute("ssoUser");//获取当前登录操作的用户
			String areaid=request.getParameter("areaId");
			String areacode=request.getParameter("areaCode");
			request.setAttribute("areaId", areaid);
			request.setAttribute("areaCode", areacode);
			
			BaseUser baseUser =new BaseUser();
			baseUser.setDefaultRoleCode(request.getParameter("defaultRoleCode")!=null&&!request.getParameter("defaultRoleCode").equals("")&&!request.getParameter("defaultRoleCode").equals("null")?request.getParameter("defaultRoleCode"):"role.areaObserve");//设置身份
			BaseArea baseArea =baseAreaManager.get(areaid);
			String ln="9";
			Integer accountSeq=baseUserManager.accountSeq();
			StringBuffer sf=new StringBuffer();
			if(!baseArea.getId().equals("1")){
				sf.append(baseArea.getNationalCode());
			}else{
				sf.append("000000");//全国的默认000000
			}
			sf.append(accountSeq);
			ln=ln+sf.toString();
			
			baseUser.setLoginName(ln); 
			baseUser.setPassword(MD5.getMd5(baseUserManager.getRandomPassword(8)));
			baseUser.setRealName(baseArea.getAreaName()+"视察员");
			baseUser.setAreaId(baseArea.getId());
			baseUser.setCreTime(new Date());
			baseUser.setModTime(new Date());
			//设置是由谁添加的
			baseUser.setModUser(locationUser.getId());
			baseUser.setCreUser(locationUser.getId());
			
			String tempCode=baseArea.getAreaCode();
			if(tempCode.equals("-2")){
				baseUser.setProvinceId("1");baseUser.setCityId("1");baseUser.setCountyId("1");
			}
			while(tempCode!=null&&!tempCode.equals("-1")&&!baseArea.getParentCode().equals("-2")){
				if(tempCode.length()==9){
					baseUser.setCountyId(baseArea.getId());
				}
				if(tempCode.length()==6){
					baseUser.setCityId(baseArea.getId());
				}
				if(tempCode.length()==3){
					baseUser.setProvinceId(baseArea.getId());
				}
				baseArea=baseAreaManager.getAreaByAreaId(null, tempCode);
				tempCode=baseArea.getParentCode();
			}
			baseUser.setIsIni(0);
			baseUser.setDeleteFlag("0");
			baseUser.setStatus("1");
		//	baseUser.setSchoolId(baseSchool.getId());
			baseUserManager.save(baseUser);
			baseUserManager.addReload(baseUser);
			baseUserManager.flush();
			
			return null;
		}
		/**系统应用情况*/
		public String user_is_ini_index(){
			SetValueUtil.autoRequestSetAttribute(request);
			
			String sflag=request.getParameter("sflag");//1代表是从饼图点过来的
			if(StringUtils.isNotBlank(sflag)){
				String needQueryMsg=request.getParameter("initype");//需要查询的信息 学校初始化还是管理员初始化
				String sname=request.getParameter("sname");//ycsh 已经初始化的数据，wcsh未初始化的数据
				String proCode=request.getParameter("proviceId");//省id--页面提交的
				String cityCode=request.getParameter("cityId");//市
				String countryCode=request.getParameter("countyId");//县
				
				request.setAttribute("myType", needQueryMsg);
				request.setAttribute("sname", sname);
				request.setAttribute("proviceId", proCode);
				request.setAttribute("cityId", cityCode);
				request.setAttribute("countyId", countryCode);
				request.setAttribute("sflag", sflag);
			}
			
			String firestMenu=request.getParameter("firestMenu");
			if(firestMenu==null||firestMenu.equals("")){
				setAttribute("menuFlag","sys");//上方主菜单
				setAttribute("menuFlag_1","sys");//上方主菜单
			}else if(firestMenu.equals("stat")){
				setAttribute("menuFlag","stat");//上方主菜单
				setAttribute("menuFlag_1","sys");//上方主菜单
			}else{
				setAttribute("menuFlag","sys");//上方主菜单
				setAttribute("menuFlag_1","sys");//上方主菜单
			}
			
			request.setAttribute("firestMenu", firestMenu);
			
			
			return USER_IS_INI_INDEX;
		}
		
		/**
		 * 用户编辑之前的方法，暂时没有使用
		 * @author    hmc    2015年6月12日  上午11:04:16
		 * @return
		 */
		public String beforeUserEdit(){
			String u_id=((BaseUser)request.getSession().getAttribute("ssoUser")).getId();
			BaseUser base=CacheBaseUserManager.getBaseUser(u_id);
			if(base==null||isNullOrEmptyString(base.getId())||isNullOrEmptyString(base.getLoginName())){
				base=baseUserManager.get(u_id);
				CacheBaseUserManager.setBaseUser(base);
			}
			request.setAttribute("baseUser", base);
			
			return "/base/user_edit.jsp";
			
		}
		
		public String editPwd(){
			String userId=request.getParameter("userId");
			BaseUser base=CacheBaseUserManager.getBaseUser(userId);
			if(base==null||isNullOrEmptyString(base.getId())||isNullOrEmptyString(base.getLoginName())){
				base=baseUserManager.get(userId);
				CacheBaseUserManager.setBaseUser(base);
			}
			request.setAttribute("baseUser", base);
			request.setAttribute("userId", userId);
			
			return EDIT_PWD_JSP;
		}
		
		public void upPwd(){
			String userId=request.getParameter("userId");
			String pwd=request.getParameter("pwd");
			BaseUser base=baseUserManager.get(userId);
			base.setPassword(MD5.getMd5(pwd));
			baseUserManager.update(base);		
			CacheBaseUserManager.setBaseUser(base);
		}
		
}
