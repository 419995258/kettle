/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package org.my431.base.web;


import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.my431.base.model.BaseArea;
import org.my431.base.model.BaseAreaTree;
import org.my431.base.model.BaseKpiItem;
import org.my431.base.model.BaseProperties;
import org.my431.base.model.BaseSchool;
import org.my431.base.model.BaseSchoolFsb;
import org.my431.base.model.BaseUser;
import org.my431.base.services.BaseAreaManager;
import org.my431.base.services.BaseKpiItemManager;
import org.my431.base.services.BaseSchoolFsbManager;
import org.my431.base.services.BaseSchoolManager;
import org.my431.base.services.CacheBaseAreaManager;
import org.my431.base.services.CacheBasePropertiesManager;
import org.my431.base.services.PageManager;

import org.my431.platform.web.StrutsTreeEntityAction;
import org.my431.platform.dao.support.Page;
import org.my431.taglib.My431Function;
import org.my431.util.DateFormater;
import org.my431.util.HtmlUtil;
import org.my431.util.MMap;
import org.springframework.beans.factory.annotation.Autowired;


import com.alibaba.fastjson.JSONArray;
import com.itextpdf.text.PageSize;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ModelDriven;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangzhen
 * @version 1.0
 * @since 1.0
 */


@SuppressWarnings({ "serial", "rawtypes" })
public class BaseKpiItemAction extends StrutsTreeEntityAction<BaseKpiItem,BaseKpiItemManager> implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "//base/BaseKpiItem/query.jsp";
	protected static final String LIST_JSP= "//base/BaseKpiItem/list.jsp";
	protected static final String CREATE_JSP = "//base/BaseKpiItem/create.jsp";
	protected static final String EDIT_JSP = "//base/BaseKpiItem/edit.jsp";
	protected static final String SHOW_JSP = "//base/BaseKpiItem/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!//base/BaseKpiItem/list.jspx";
	
	// 乡村教师首页首页
	protected static final String INDEX = "//base/CountryTeacher/index.jsp";
	// 乡村教师首页首页内容
	protected static final String COUNTRY_TEACHER_INDEX = "//base/CountryTeacher/main.jsp";
	protected static final String COUNTRY_TEACHER_INDEX_EXCEL = "//base/CountryTeacher/export/main_excel.jsp";
	// 乡村教师薪资首页内容
	protected static final String COUNTRY_TEACHER_MONEY_INDEX = "//base/CountryTeacher/money_main.jsp";
	protected static final String COUNTRY_TEACHER_MONEY_EXCEL = "//base/CountryTeacher/export/money_excel.jsp";
	// 乡村教师荣誉首页内容
	protected static final String COUNTRY_TEACHER_HONOR_INDEX = "//base/CountryTeacher/honor_main.jsp";
	protected static final String COUNTRY_TEACHER_HONOR_EXCEL = "//base/CountryTeacher/export/honor_excel.jsp";
	// 教师荣誉首页内容
	protected static final String EXCELLENT_TEACHER_HONOR_INDEX = "//base/ExcellentTeacher/index.jsp";
	// 优秀教师荣誉首页内容
	protected static final String EXCELLENT_TEACHER_HONOR_MAIN = "//base/ExcellentTeacher/main.jsp";
	protected static final String EXCELLENT_TEACHER_HONOR_EXCEL = "//base/ExcellentTeacher/export/main_excel.jsp";
	// 优秀教师荣誉首页内容(学校)
	protected static final String EXCELLENT_TEACHER_HONOR_MAIN_SCHOOL = "//base/ExcellentTeacher/main_school.jsp";
	protected static final String EXCELLENT_TEACHER_HONOR_MAIN_SCHOOL_EXCEL = "//base/ExcellentTeacher/export/main_school_excel.jsp";
	
	private BaseKpiItemManager baseKpiItemManager;
	
	@Autowired
	private BaseAreaManager baseAreaManager;
	
	@Autowired
	private BaseSchoolFsbManager baseSchoolFsbManager;
	
	@Autowired
	private BaseSchoolManager baseSchoolManager;
	
	private BaseKpiItem baseKpiItem;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			baseKpiItem = new BaseKpiItem();
		} else {
			baseKpiItem = (BaseKpiItem)baseKpiItemManager.get(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseKpiItemManager(BaseKpiItemManager manager) {
		this.baseKpiItemManager = manager;
	}	
	
	public Object getModel() {
		return baseKpiItem;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		BaseKpiItem query = new BaseKpiItem();
		
		int pageNo=1;
		if(request.getParameter("pageNo")!=null&&!request.getParameter("pageNo").equals("")){
			pageNo=Integer.valueOf(request.getParameter("pageNo"));
		}
		int pageSize=10;
		if(request.getParameter("pageSize")!=null&&!request.getParameter("pageSize").equals("")){
			pageSize=Integer.valueOf(request.getParameter("pageSize"));
		}
		Page page = baseKpiItemManager.findPage(query,pageSize,pageNo);
		PageManager pm = new PageManager((int)page.getTotalCount(), pageSize,pageSize);
		pm.goToPage(pageNo);
		request.setAttribute("dataList", page.getResult());   
	    request.setAttribute("pageNo", pageNo);
	    request.setAttribute("pageSize", pageSize);
	    request.setAttribute("pageHtml", pm.getPageCode());
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
		baseKpiItemManager.save(baseKpiItem);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		request.setAttribute("baseKpiItem", baseKpiItem);
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		baseKpiItemManager.update(this.baseKpiItem);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		baseKpiItemManager.removeById(id);
		return LIST_ACTION;
	}
	
	/** 首页*/
	public String index() {
		String menu = request.getParameter("menu");
		if(StringUtils.isBlank(menu)){
			menu = "YEY";
		}
		request.setAttribute("menuFlag", "xcjs");
		request.setAttribute("menuFlag_1", menu);
		return INDEX;
	}

	/**
	 * 乡村教师内容首页
	 * @return
	 */
	public String countryTeacherIndex(){
		String menuFlag_1 = request.getParameter("menuFlag_1");
		String isExcel = request.getParameter("isExcel");
		if(StringUtils.isBlank(menuFlag_1)){
			menuFlag_1 = "YEY";
		}
		request.setAttribute("menuFlag_1", menuFlag_1);
		BaseUser baseUser = (BaseUser) request.getSession().getAttribute(
				"ssoUser");
		//查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		
		//是哪个学段的 幼儿园或者其他
		String menu = request.getParameter("menu");
		String SSXDstr = null;
		request.setAttribute("SSXD", menu);
		if(StringUtils.isBlank(menu)){
			menu = "YEY";
			SSXDstr = "'YEY'";
		}
		if("GZ".equals(menu)){
			SSXDstr = "'GZ','GX'";
		}else{
			SSXDstr = "'" + menu + "'";
		}
		String SSXD = "(" + SSXDstr +  ")";
		map.put("SSXD", SSXD);
		map.put("SSXDmenu", menu);
		//是否附设班教师
		String sffsbjs = request.getParameter("sffsbjs");
		if(StringUtils.isNotBlank(sffsbjs) && !"all".equals(sffsbjs)){
			map.put("SFFSBJS", sffsbjs);
			request.setAttribute("sffsbjs", sffsbjs);
		}
		/**==========================================表条件===================================**/
		String TJSJ = request.getParameter("TJSJ");
		request.setAttribute("TJSJ", TJSJ);
		String table = null;
		String DATE = null;
		if(StringUtils.isBlank(TJSJ)){
			table = "";
			DATE = "";
		}else{
			try {
				Date date = new SimpleDateFormat("yyyy-MM").parse(TJSJ);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy_MM");
			table = "_" + sf.format(date).substring(2);
			DATE = "_" + sf.format(date).substring(2);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		map.put("table", "STAT_BASE_TEACHER_INFO"+table);
		map.put("DATE", DATE);
		/**==========================================选中图表条件===================================**/
		String[] fxzbChar = request.getParameterValues("FXZB"); 
		String FXZBSelect = request.getParameter("FXZBSelect");
		if(fxzbChar == null && "all".equals(FXZBSelect)){
			fxzbChar = new String[]{"all","SFZB","SFXJJYSGGJS","XRGWDJ"};
		}
		if(StringUtils.isNotBlank(isExcel)){
			fxzbChar = new String[]{"all","SFZB","SFXJJYSGGJS","XRGWDJ"};
		}
		if(fxzbChar!=null){
			for (int i = 0; i < fxzbChar.length; i++) {
				request.setAttribute(fxzbChar[i],"checked=checked");
			}
		}
		/**==========================================地区条件===================================**/
		//地区id
		String areaId =request.getParameter("areaId");
		//地区code
		String areaCode =request.getParameter("areaCode");
		if(areaCode == null){
			String appAreaCode = CacheBasePropertiesManager.getValueByPropertyKey("app.areaCode");
			/*BaseArea baseArea = CacheBaseAreaManager.getNodeByCode(appAreaCode);
			//String provinceId="18A92CF8-5F66-ABDE-BB42-B1ACCC4180EE";
			String provinceId = baseArea.getId();*/
			areaCode = appAreaCode;
		}
		BaseArea baseArea = new BaseArea();
		String shangJiAreaId = null;
		String shangJiAreaName = null;
		map.put("provinceId", baseUser.getProvinceId());
		BaseUser baus = new BaseUser();
		if(StringUtils.isNotBlank(areaId)){
			areaCode = CacheBaseAreaManager.getNodeById(areaId).getAreaCode();
			request.setAttribute("areaName", CacheBaseAreaManager.getNodeById(areaId).getAreaName());
			if (areaCode.length() == 3) {
				//省
				map.put("areaId", "CITY_ID");
				baus.setProvinceId(areaId);
			}
			if (areaCode.length() == 6) {
				//市
				map.put("areaId", "COUNTY_ID");
				baus.setCityId(areaId);
			}
			if (areaCode.length() == 9) {
				//县
				map.put("areaId", "COUNTY_ID");
				baus.setCountyId(areaId);
			}
		}else {
			//没有地区id，判断登录身份是省市县哪个权限
			baus = baseUser;
			String roleCode = baseUser.getDefaultRoleCode();
			if("role.provinceManager".equals(roleCode)){
				map.put("areaId","CITY_ID");
				baseArea = CacheBaseAreaManager.getNodeById(baseUser.getProvinceId());
			}else if("role.cityManager".equals(roleCode)){
				map.put("areaId", "COUNTY_ID");
				baseArea = CacheBaseAreaManager.getNodeById(baseUser.getCityId());
			}
			else if("role.countryManager".equals(roleCode)){
				map.put("areaId", "COUNTY_ID");
				baseArea = CacheBaseAreaManager.getNodeById(baseUser.getCountyId());
			}
			areaId = baseArea.getId();
			areaCode = baseArea.getAreaCode();
			request.setAttribute("areaName", baseArea.getAreaName());
		}
		
		request.setAttribute("areaId", areaId);
		request.setAttribute("areaCode", areaCode);
		request.setAttribute("wsAreaCode", request.getSession().getAttribute("wsAreaCode"));
		
		if ("role.provinceManager".equals(baseUser.getDefaultRoleCode())) {
			if (areaCode.length() == 6) {
				shangJiAreaId = baseAreaManager.getAreaInfo(areaCode).getObj1().toString();
				shangJiAreaName = baseAreaManager.getAreaInfo(areaCode).getObj0().toString();
			}
			if (areaCode.length() == 9) {
				shangJiAreaId = baseAreaManager.getAreaInfo(areaCode).getObj4().toString();
				shangJiAreaName = baseAreaManager.getAreaInfo(areaCode).getObj3().toString();
			}
		}
		if ("role.cityManager".equals(baseUser.getDefaultRoleCode())) {
			if (areaCode.length() == 9) {
				shangJiAreaId = baseAreaManager.getAreaInfo(areaCode).getObj4().toString();
				shangJiAreaName = baseAreaManager.getAreaInfo(areaCode).getObj3().toString();
			}
		}
		request.setAttribute("shangJiAreaId", shangJiAreaId);
		request.setAttribute("shangJiAreaName", shangJiAreaName);
		
		map.put("baus", baus);
		//搜索结果
		Map<Object, Object> mapGet = null;
		mapGet = baseKpiItemManager.getCountryTeacher(map);
		//所有的地区数据
		List<BaseAreaTree> areaList = CacheBaseAreaManager.getTreeByParentCode(areaCode);
		request.setAttribute("areaList", areaList);
		//图表数据
		mapGet = baseKpiItemManager.getCountryTeacherCharts(mapGet);
		//content数据
		mapGet.put("content", baseKpiItemManager.getSchoolClassStudentContent(map));
		//所有的数据
		request.setAttribute("mapGet", mapGet);
		//乡村教师左侧
		request.setAttribute("menuFlag_2", "index");
		
		
		if(StringUtils.isNotBlank(isExcel)){//sflag=1则进行导出excel操作
			String excel_title = "乡村教师基本情况分析"+"_"+DateFormater.DateToString(new Date(), "yyyyMMddHHmmss");
			HttpServletResponse response = ServletActionContext.getResponse();
			String finalFileName="";
			try {
				String agent = request.getHeader("User-Agent");
				boolean isMSIE = (agent != null && agent.indexOf("MSIE") != -1);
				if( isMSIE ){
					finalFileName = java.net.URLEncoder.encode(excel_title,"UTF8");
				}else{
					finalFileName = new String(excel_title.getBytes("UTF-8"), "ISO-8859-1");
				}
				response.setHeader("Content-disposition","attachment; filename="+finalFileName+".xls");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return COUNTRY_TEACHER_INDEX_EXCEL;
		}else {
			return COUNTRY_TEACHER_INDEX;
		}
	}
	
	/**
	 * 乡村教师薪资首页
	 * @return
	 */
	public String countryTeacherMoney(){
		String menuFlag_1 = request.getParameter("menuFlag_1");
		String isExcel = request.getParameter("isExcel");
		if(StringUtils.isBlank(menuFlag_1)){
			menuFlag_1 = "YEY";
		}
		request.setAttribute("menuFlag_1", menuFlag_1);
		BaseUser baseUser = (BaseUser) request.getSession().getAttribute(
				"ssoUser");
		//查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		
		//是哪个学段的 幼儿园或者其他
		String menu = request.getParameter("menu");
		String SSXDstr = null;
		request.setAttribute("SSXD", menu);
		if(StringUtils.isBlank(menu)){
			menu = "YEY";
			SSXDstr = "'YEY'";
		}
		if("GZ".equals(menu)){
			SSXDstr = "'GZ','GX'";
		}else{
			SSXDstr = "'" + menu + "'";
		}
		String SSXD = "(" + SSXDstr +  ")";
		map.put("SSXD", SSXD);
		map.put("SSXDmenu", menu);
		//是否附设班教师
		String sffsbjs = request.getParameter("sffsbjs");
		if(StringUtils.isNotBlank(sffsbjs) && !"all".equals(sffsbjs)){
			map.put("SFFSBJS", sffsbjs);
			request.setAttribute("sffsbjs", sffsbjs);
		}
		//分析年度
		String nd = request.getParameter("nd");
		if(StringUtils.isBlank(nd)){
			List<String> list = My431Function.getDatesFromSomeDateYear();
			if(list.size()>0){
				nd=list.get(0);
			}
		}
		map.put("ND", nd);
		request.setAttribute("nd", nd);
		/**==========================================表条件===================================**/
		String TJSJ = request.getParameter("TJSJ");
		request.setAttribute("TJSJ", TJSJ);
		String table = null;
		String tableSUM = null;
		String DATE = null;
		if(StringUtils.isBlank(TJSJ)){
			table="";
			tableSUM="";
			DATE = "";
		}else{
			try {
				Date date = new SimpleDateFormat("yyyy-MM").parse(TJSJ);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy_MM");
			table = "_" + sf.format(date).substring(2);
			//sf = new SimpleDateFormat("yyyy");
			tableSUM ="_" + sf.format(date).substring(2); 
			DATE = "_" + sf.format(date).substring(2);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		map.put("table", "STAT_BASE_TEACHER_INFO"+table);
		map.put("tableSUM", "STAT_JSGDYXX_SUM"+tableSUM);
		map.put("DATE", DATE);
		/**==========================================地区条件===================================**/
		//地区id
		String areaId =request.getParameter("areaId");
		//地区code
		String areaCode =request.getParameter("areaCode");
		if(areaCode == null){
			String appAreaCode = CacheBasePropertiesManager.getValueByPropertyKey("app.areaCode");
			/*BaseArea baseArea = CacheBaseAreaManager.getNodeByCode(appAreaCode);
			//String provinceId="18A92CF8-5F66-ABDE-BB42-B1ACCC4180EE";
			String provinceId = baseArea.getId();*/
			areaCode = appAreaCode;
		}
		BaseArea baseArea = new BaseArea();
		String shangJiAreaId = null;
		String shangJiAreaName = null;
		map.put("provinceId", baseUser.getProvinceId());
		BaseUser baus = new BaseUser();
		if(StringUtils.isNotBlank(areaId)){
			areaCode = CacheBaseAreaManager.getNodeById(areaId).getAreaCode();
			request.setAttribute("areaName", CacheBaseAreaManager.getNodeById(areaId).getAreaName());
			if (areaCode.length() == 3) {
				//省
				map.put("areaId", "CITY_ID");
				baus.setProvinceId(areaId);
			}
			if (areaCode.length() == 6) {
				//市
				map.put("areaId", "COUNTY_ID");
				baus.setCityId(areaId);
			}
			if (areaCode.length() == 9) {
				//县
				map.put("areaId", "COUNTY_ID");
				baus.setCountyId(areaId);
			}
		}else {
			//没有地区id，判断登录身份是省市县哪个权限
			baus = baseUser;
			String roleCode = baseUser.getDefaultRoleCode();
			if("role.provinceManager".equals(roleCode)){
				map.put("areaId","CITY_ID");
				baseArea = CacheBaseAreaManager.getNodeById(baseUser.getProvinceId());
			}else if("role.cityManager".equals(roleCode)){
				map.put("areaId", "COUNTY_ID");
				baseArea = CacheBaseAreaManager.getNodeById(baseUser.getCityId());
			}
			else if("role.countryManager".equals(roleCode)){
				map.put("areaId", "COUNTY_ID");
				baseArea = CacheBaseAreaManager.getNodeById(baseUser.getCountyId());
			}
			areaId = baseArea.getId();
			areaCode = baseArea.getAreaCode();
			request.setAttribute("areaName", baseArea.getAreaName());
		}
		request.setAttribute("areaId", areaId);
		request.setAttribute("areaCode", areaCode);
		request.setAttribute("wsAreaCode", request.getSession().getAttribute("wsAreaCode"));
		
		if ("role.provinceManager".equals(baseUser.getDefaultRoleCode())) {
			if (areaCode.length() == 6) {
				shangJiAreaId = baseAreaManager.getAreaInfo(areaCode).getObj1().toString();
				shangJiAreaName = baseAreaManager.getAreaInfo(areaCode).getObj0().toString();
			}
			if (areaCode.length() == 9) {
				shangJiAreaId = baseAreaManager.getAreaInfo(areaCode).getObj4().toString();
				shangJiAreaName = baseAreaManager.getAreaInfo(areaCode).getObj3().toString();
			}
		}
		if ("role.cityManager".equals(baseUser.getDefaultRoleCode())) {
			if (areaCode.length() == 9) {
				shangJiAreaId = baseAreaManager.getAreaInfo(areaCode).getObj4().toString();
				shangJiAreaName = baseAreaManager.getAreaInfo(areaCode).getObj3().toString();
			}
		}
		request.setAttribute("shangJiAreaId", shangJiAreaId);
		request.setAttribute("shangJiAreaName", shangJiAreaName);
		
		/**==========================================选中图表条件===================================**/
		String[] fxzbChar = request.getParameterValues("FXZB"); 
		String FXZBSelect = request.getParameter("FXZBSelect");
		if(fxzbChar == null && "all".equals(FXZBSelect)){
			fxzbChar = new String[]{"all","JBGZ","JSBZ","WXYJ"};
		}
		if(StringUtils.isNotBlank(isExcel)){
			fxzbChar = new String[]{"all","JBGZ","JSBZ","WXYJ"};
		}
		map.put("baus", baus);
		//所有的地区数据
		List<BaseAreaTree> areaList = CacheBaseAreaManager.getTreeByParentCode(areaCode);
		request.setAttribute("areaList", areaList);
		//搜索结果
		Map<Object, Object> mapGet = null;
		//table数据
		mapGet = baseKpiItemManager.getTeacherMoneyTable(map);
		//图表的绘制
		if(fxzbChar!=null){
			for (int i = 0; i < fxzbChar.length; i++) {
				if("JSBZ".equals(fxzbChar[i])){
					mapGet = baseKpiItemManager.getTeacherMoneyXCJSSHBZCharts(mapGet);
				}
				if("JBGZ".equals(fxzbChar[i])){
					JSONArray array = baseKpiItemManager.getTeacherMoneyJBGZCharts(map);
					if(array != null){
						mapGet.put("JBGZChart", array);
					}
				}
				if("WXYJ".equals(fxzbChar[i])){
					mapGet = baseKpiItemManager.getTeacherMoneyWXYJCharts(mapGet);
				}
				request.setAttribute(fxzbChar[i],"checked=checked");
			}
		}
		
		//content数据
		mapGet.put("content", baseKpiItemManager.getSchoolClassStudentContent(map));
		//所有的数据
		request.setAttribute("mapGet", mapGet);
		
		//乡村教师左侧
		request.setAttribute("menuFlag_2", "money");
		
		if(StringUtils.isNotBlank(isExcel)){//sflag=1则进行导出excel操作
			String excel_title = "乡村教师基本待遇分析"+"_"+DateFormater.DateToString(new Date(), "yyyyMMddHHmmss");
			HttpServletResponse response = ServletActionContext.getResponse();
			String finalFileName="";
			try {
				String agent = request.getHeader("User-Agent");
				boolean isMSIE = (agent != null && agent.indexOf("MSIE") != -1);
				if( isMSIE ){
					finalFileName = java.net.URLEncoder.encode(excel_title,"UTF8");
				}else{
					finalFileName = new String(excel_title.getBytes("UTF-8"), "ISO-8859-1");
				}
				response.setHeader("Content-disposition","attachment; filename="+finalFileName+".xls");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return COUNTRY_TEACHER_MONEY_EXCEL;
		}else {
			return COUNTRY_TEACHER_MONEY_INDEX;
		}
		
	}
	
	/**
	 * 荣誉教师首页
	 * @return
	 */
	public String countryTeacherHonor(){
		String menuFlag_1 = request.getParameter("menuFlag_1");
		String isExcel = request.getParameter("isExcel");
		if(StringUtils.isBlank(menuFlag_1)){
			menuFlag_1 = "YEY";
		}
		request.setAttribute("menuFlag_1", menuFlag_1);
		BaseUser baseUser = (BaseUser) request.getSession().getAttribute(
				"ssoUser");
		//查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		
		//是哪个学段的 幼儿园或者其他
		String menu = request.getParameter("menu");
		String SSXDstr = null;
		request.setAttribute("SSXD", menu);
		if(StringUtils.isBlank(menu)){
			menu = "YEY";
			SSXDstr = "'YEY'";
		}
		if("GZ".equals(menu)){
			SSXDstr = "'GZ','GX'";
		}else{
			SSXDstr = "'" + menu + "'";
		}
		String SSXD = "(" + SSXDstr +  ")";
		map.put("SSXD", SSXD);
		map.put("SSXDmenu", menu);
		//是否附设班教师
		String sffsbjs = request.getParameter("sffsbjs");
		if(StringUtils.isNotBlank(sffsbjs) && !"all".equals(sffsbjs)){
			map.put("SFFSBJS", sffsbjs);
			request.setAttribute("sffsbjs", sffsbjs);
		}
		/**==========================================表条件===================================**/
		String TJSJ = request.getParameter("TJSJ");
		request.setAttribute("TJSJ", TJSJ);
		String table = null;
		String DATE = null;
		if(StringUtils.isBlank(TJSJ)){
			table = "";
			DATE = "";
		}else{
			try {
				Date date = new SimpleDateFormat("yyyy-MM").parse(TJSJ);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy_MM");
			table = "_" + sf.format(date).substring(2);
			DATE = "_"+sf.format(date).substring(2);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		map.put("table", "STAT_BASE_TEACHER_INFO"+table);
		map.put("tableJZG", "TB_BIZ_JZGSDRYXX"+table);
		map.put("DATE", DATE);
		/**==========================================地区条件===================================**/
		//地区id
		String areaId =request.getParameter("areaId");
		//地区code
		String areaCode =request.getParameter("areaCode");
		if(areaCode == null){
			String appAreaCode = CacheBasePropertiesManager.getValueByPropertyKey("app.areaCode");
			/*BaseArea baseArea = CacheBaseAreaManager.getNodeByCode(appAreaCode);
			//String provinceId="18A92CF8-5F66-ABDE-BB42-B1ACCC4180EE";
			String provinceId = baseArea.getId();*/
			areaCode = appAreaCode;
		}
		BaseArea baseArea = new BaseArea();
		String shangJiAreaId = null;
		String shangJiAreaName = null;
		map.put("provinceId", baseUser.getProvinceId());
		BaseUser baus = new BaseUser();
		if(StringUtils.isNotBlank(areaId)){
			areaCode = CacheBaseAreaManager.getNodeById(areaId).getAreaCode();
			request.setAttribute("areaName", CacheBaseAreaManager.getNodeById(areaId).getAreaName());
			if (areaCode.length() == 3) {
				//省
				map.put("areaId", "CITY_ID");
				baus.setProvinceId(areaId);
			}
			if (areaCode.length() == 6) {
				//市
				map.put("areaId", "COUNTY_ID");
				baus.setCityId(areaId);
			}
			if (areaCode.length() == 9) {
				//县
				map.put("areaId", "COUNTY_ID");
				baus.setCountyId(areaId);
			}
		}else {
			//没有地区id，判断登录身份是省市县哪个权限
			baus = baseUser;
			String roleCode = baseUser.getDefaultRoleCode();
			if("role.provinceManager".equals(roleCode)){
				map.put("areaId","CITY_ID");
				baseArea = CacheBaseAreaManager.getNodeById(baseUser.getProvinceId());
			}else if("role.cityManager".equals(roleCode)){
				map.put("areaId", "COUNTY_ID");
				baseArea = CacheBaseAreaManager.getNodeById(baseUser.getCityId());
			}
			else if("role.countryManager".equals(roleCode)){
				map.put("areaId", "COUNTY_ID");
				baseArea = CacheBaseAreaManager.getNodeById(baseUser.getCountyId());
			}
			areaId = baseArea.getId();
			areaCode = baseArea.getAreaCode();
			request.setAttribute("areaName", baseArea.getAreaName());
		}
		request.setAttribute("areaId", areaId);
		request.setAttribute("areaCode", areaCode);
		request.setAttribute("wsAreaCode", request.getSession().getAttribute("wsAreaCode"));
		
		if ("role.provinceManager".equals(baseUser.getDefaultRoleCode())) {
			if (areaCode.length() == 6) {
				shangJiAreaId = baseAreaManager.getAreaInfo(areaCode).getObj1().toString();
				shangJiAreaName = baseAreaManager.getAreaInfo(areaCode).getObj0().toString();
			}
			if (areaCode.length() == 9) {
				shangJiAreaId = baseAreaManager.getAreaInfo(areaCode).getObj4().toString();
				shangJiAreaName = baseAreaManager.getAreaInfo(areaCode).getObj3().toString();
			}
		}
		if ("role.cityManager".equals(baseUser.getDefaultRoleCode())) {
			if (areaCode.length() == 9) {
				shangJiAreaId = baseAreaManager.getAreaInfo(areaCode).getObj4().toString();
				shangJiAreaName = baseAreaManager.getAreaInfo(areaCode).getObj3().toString();
			}
		}
		request.setAttribute("shangJiAreaId", shangJiAreaId);
		request.setAttribute("shangJiAreaName", shangJiAreaName);
		
		/**==========================================选中图表条件===================================**/
		String fxzbCharStr = request.getParameter("FXZB"); 
		request.setAttribute("FXZB", fxzbCharStr);
		String[] fxzbChar = null;
		StringBuilder FXZB = new StringBuilder("and(1=2");
		StringBuilder FXZBSelect = new StringBuilder("");
		if(fxzbCharStr == null ){
			fxzbChar = new String[]{"RYCH@GJ@53"};
		}else{
			fxzbChar = fxzbCharStr.split(",");
		}
		for (int i = 0; i < fxzbChar.length; i++) {
			FXZB = FXZB.append(" or t.HDRYCH = '").append(fxzbChar[i]).append("'");
			FXZBSelect = FXZBSelect.append("'").append(fxzbChar[i]).append("'").append(",");
			//request.setAttribute(fxzbChar[i],"checked=checked");
		}
		request.setAttribute("FXZBSelect", FXZBSelect);
		FXZB = FXZB.append(")");
		map.put("baus", baus);
		//分析指标的条件
		map.put("FXZB", FXZB.toString());
		//所有的地区数据
		List<BaseAreaTree> areaList = CacheBaseAreaManager.getTreeByParentCode(areaCode);
		request.setAttribute("areaList", areaList);
		//搜索结果
		Map<Object, Object> mapGet = null;
		//table数据
		mapGet = baseKpiItemManager.getTeacherHonorTable(map);
		//获取图表数据
		mapGet = baseKpiItemManager.getTeacherRYCharts(mapGet);
		//content数据
		mapGet.put("content", baseKpiItemManager.getSchoolClassStudentContent(map));
		//所有的数据
		request.setAttribute("mapGet", mapGet);
		
		//乡村教师左侧
		request.setAttribute("menuFlag_2", "honor");
		
		if(StringUtils.isNotBlank(isExcel)){//sflag=1则进行导出excel操作
			String excel_title = "乡村教师荣誉情况分析"+"_"+DateFormater.DateToString(new Date(), "yyyyMMddHHmmss");
			HttpServletResponse response = ServletActionContext.getResponse();
			String finalFileName="";
			try {
				String agent = request.getHeader("User-Agent");
				boolean isMSIE = (agent != null && agent.indexOf("MSIE") != -1);
				if( isMSIE ){
					finalFileName = java.net.URLEncoder.encode(excel_title,"UTF8");
				}else{
					finalFileName = new String(excel_title.getBytes("UTF-8"), "ISO-8859-1");
				}
				response.setHeader("Content-disposition","attachment; filename="+finalFileName+".xls");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return COUNTRY_TEACHER_HONOR_EXCEL;
		}else {
			return COUNTRY_TEACHER_HONOR_INDEX;
		}
	}
	
	public String excellentIndex(){
		String menu = request.getParameter("menu");
		if(StringUtils.isBlank(menu)){
			menu = "YEY";
		}
		request.setAttribute("menuFlag_1", menu);
		request.setAttribute("menuFlag", "yxjs");
		return EXCELLENT_TEACHER_HONOR_INDEX;
		
	}
	
	public String excellentTeacherIndex(){
		String isExcel = request.getParameter("isExcel");
		BaseUser baseUser = (BaseUser) request.getSession().getAttribute(
				"ssoUser");
		//查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		
		//是哪个学段的 幼儿园或者其他
		String menu = request.getParameter("menu");
		String SSXDstr = null;
		request.setAttribute("SSXD", menu);
		if(StringUtils.isBlank(menu)){
			menu = "YEY";
			SSXDstr = "'YEY'";
		}
		if("GZ".equals(menu)){
			SSXDstr = "'GZ','GX'";
		}else{
			SSXDstr = "'" + menu + "'";
		}
		String SSXD = "(" + SSXDstr +  ")";
		map.put("SSXD", SSXD);
		map.put("SSXDmenu", menu);
		//是否附设班教师
		String sffsbjs = request.getParameter("sffsbjs");
		if(StringUtils.isNotBlank(sffsbjs) && !"all".equals(sffsbjs)){
			map.put("SFFSBJS", sffsbjs);
			request.setAttribute("sffsbjs", sffsbjs);
		}
		/**==========================================表条件===================================**/
		String TJSJ = request.getParameter("TJSJ");
		request.setAttribute("TJSJ", TJSJ);
		String table = null;
		String DATE = null;
		if(StringUtils.isBlank(TJSJ)){
			table = "";
			DATE = "";
		}else{
			try {
				Date date = new SimpleDateFormat("yyyy-MM").parse(TJSJ);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy_MM");
			table ="_" +  sf.format(date).substring(2);
			DATE = "_"+sf.format(date).substring(2);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		map.put("table", "STAT_BASE_TEACHER_INFO"+table);
		map.put("DATE", DATE);
		/**==========================================筛选荣誉日期条件===================================**/
		String RYDate = request.getParameter("RYDate");
		if(StringUtils.isBlank(RYDate)){
			RYDate = "2017";
		}
		map.put("RYDate", RYDate);
		request.setAttribute("RYDate", RYDate);
		/**==========================================结果条件===================================**/
		String CXFLRYJB = request.getParameter("CXFLRYJB");
		if(StringUtils.isBlank(CXFLRYJB)){
			CXFLRYJB = "CXFL";
		}
		/*
		if("CXFL".equals(CXFLRYJB)){
			map.put("CXFLRYJB", "tt."+ CXFLRYJB);
		}else{
			map.put("CXFLRYJB", "t."+ CXFLRYJB);
		}*/
		request.setAttribute("CXFLRYJB", CXFLRYJB);
		/**==========================================城乡分类条件===================================**/
		String[] CXFL = request.getParameterValues("CXFL");
		if(StringUtils.isNotBlank(isExcel)){
			String CXFLSTR =  request.getParameter("CXFLArray");
			if(StringUtils.isNotBlank(CXFLSTR)){
				if(!"null".equals(CXFLSTR)){
					CXFLSTR = CXFLSTR.substring(1,CXFLSTR.length()-1);
					if(StringUtils.isNotBlank(CXFLSTR)){
						CXFL = CXFLSTR.split(",");
					}
				}
			}
		}
		request.setAttribute("CXFLArray", Arrays.toString(CXFL));
		String CXFLStr = null;
		StringBuilder sbGet = new StringBuilder("( 1=2 ");
		if (null != CXFL && CXFL.length > 0) {
			List<BaseProperties> list = CacheBasePropertiesManager.getPropertiesByGroupKey("cxflbm");
			String str = new String();
			if (MMap.validateList(list)) {
				for (BaseProperties baseProperties : list) {
					str = str + "'"+baseProperties.getPropertyKey()+"',";
				}
				str = str.substring(0, str.length()-1);
			}
			for (int i = 0; i < CXFL.length; i++) {
				if ("all".equals(CXFL[i])) {
					break;
				}
				if ("wz".equals(CXFL[i])) {
					sbGet.append(" or TT.CXFL not in("+str+") ");
				} else {
					sbGet.append(" or ");
					sbGet.append("TT.CXFL = ");
					sbGet.append("'");
					sbGet.append(CXFL[i]);
					sbGet.append("'");
				}
			}
		}
		sbGet.append(")");
		CXFLStr = sbGet.toString();
		if(!"( 1=2 )".equals(CXFLStr)){
			map.put("CXFL", CXFLStr);
		}
		
		
		/**==========================================性别条件===================================**/
		String[] XB = request.getParameterValues("XB") ;
		if(StringUtils.isNotBlank(isExcel)){
			String XBSTR =  request.getParameter("XBArray");
			if(StringUtils.isNotBlank(XBSTR)){
				if(!"null".equals(XBSTR)){
					XBSTR = XBSTR.substring(1,XBSTR.length()-1);
					if(StringUtils.isNotBlank(XBSTR)){
						XB = XBSTR.split(",");
					}
				}
			}
		}
		request.setAttribute("XBArray", Arrays.toString(XB));
		String XBStr = null;
		sbGet = new StringBuilder("( 1=2 ");
		if (null != XB && XB.length > 0) {
			List<BaseProperties> list = CacheBasePropertiesManager.getPropertiesByGroupKey("XB");
			String str = new String();
			if (MMap.validateList(list)) {
				for (BaseProperties baseProperties : list) {
					str = str + "'"+baseProperties.getPropertyKey()+"',";
				}
				str = str.substring(0, str.length()-1);
			}
			for (int i = 0; i < XB.length; i++) {
				if ("all".equals(XB[i])) {
					break;
				}
				if ("wz".equals(XB[i])) {
					sbGet.append(" or TT.XB not in("+str+") ");
				}else if ("wtb".equals(XB[i])) {
					sbGet.append(" or TT.XB is null ");
				} else {
					sbGet.append(" or ");
					sbGet.append("TT.XB = ");
					sbGet.append("'");
					sbGet.append(XB[i]);
					sbGet.append("'");
				}
			}
		}
		sbGet.append(")");
		XBStr = sbGet.toString();
		if(!"( 1=2 )".equals(XBStr)){
			map.put("XB", XBStr);
		}
		
		/**==========================================是否在编条件===================================**/
		String[] SFZB = request.getParameterValues("SFZB");
		if(StringUtils.isNotBlank(isExcel)){
			String SFZBSTR =  request.getParameter("XBArray");
			if(StringUtils.isNotBlank(SFZBSTR)){
				if(!"null".equals(SFZBSTR)){
					SFZBSTR = SFZBSTR.substring(1,SFZBSTR.length()-1);
					if(StringUtils.isNotBlank(SFZBSTR)){
						SFZB = SFZBSTR.split(",");
					}
				}
			}
		}
		request.setAttribute("SFZBArray", Arrays.toString(SFZB));
		String SFZBStr = null;
		sbGet = new StringBuilder("( 1=2 ");
		if (null != SFZB  && SFZB.length > 0) {
			List<BaseProperties> list = CacheBasePropertiesManager.getPropertiesByGroupKey("SFZB");
			String str = new String();
			if (MMap.validateList(list)) {
				for (BaseProperties baseProperties : list) {
					str = str + "'"+baseProperties.getPropertyKey()+"',";
				}
				str = str.substring(0, str.length()-1);
			}
			for (int i = 0; i < SFZB.length; i++) {
				if ("all".equals(SFZB[i])) {
					break;
				}
				if ("wz".equals(SFZB[i])) {
					sbGet.append(" or TT.SFZB not in("+str+") ");							
				}else if ("wtb".equals(SFZB[i])) {
					sbGet.append(" or TT.SFZB is null ");							
				}else {
					sbGet.append(" or ");
					sbGet.append("TT.SFZB  = ");
					sbGet.append("'");
					sbGet.append(SFZB[i]);
					sbGet.append("'");
				}
			}
		}
		sbGet.append(")");
		SFZBStr = sbGet.toString();
		if(!"( 1=2 )".equals(SFZBStr)){
			map.put("SFZB", SFZBStr);
		}
		/**==========================================最高学历条件===================================**/
		//学历
		//初中（国家推行9年义务教育，所以小学不算学历，视同文盲），高中（包括：高中，职高，中专，技校），大专（大学学专科），大本（大学本科），硕士（硕士研究生），博士（博士研究生）
		String[] ZGXL = request.getParameterValues("ZGXL");
		if(StringUtils.isNotBlank(isExcel)){
			String ZGXLSTR =  request.getParameter("ZGXLArray");
			if(StringUtils.isNotBlank(ZGXLSTR)){
				if(!"null".equals(ZGXLSTR)){
					ZGXLSTR = ZGXLSTR.substring(1,ZGXLSTR.length()-1);
					if(StringUtils.isNotBlank(ZGXLSTR)){
						ZGXL = ZGXLSTR.split(",");
					}
				}
			}
		}
		request.setAttribute("ZGXLArray", Arrays.toString(ZGXL));
		String ZGXLStr = null; 
		sbGet = new StringBuilder("( 1=2 ");
		if (null != ZGXL   && ZGXL.length > 0) {
			List<BaseProperties> list = CacheBasePropertiesManager.getPropertiesByGroupKey("XL");
			String str = new String();
			if (MMap.validateList(list)) {
				for (BaseProperties baseProperties : list) {
					str = str + "'"+baseProperties.getPropertyKey()+"',";
				}
				str = str.substring(0, str.length()-1);
			}
			for (int i = 0; i < ZGXL.length; i++) {
				if ("all".equals(ZGXL[i])) {
					break;
				}
				if ("wz".equals(ZGXL[i])) {
					sbGet.append(" or TT.ZGXL not in("+str+") ");
				} else if("bs".equals(ZGXL[i])){
					sbGet.append(" or ");
					sbGet.append("TT.ZGXL in('XL@GJ@11','XL@GJ@12','XL@GJ@13') ");
				}else if("ss".equals(ZGXL[i])){
					sbGet.append(" or ");
					sbGet.append("TT.ZGXL in('XL@GJ@14','XL@GJ@15','XL@GJ@16','XL@GJ@17','XL@GJ@18','XL@GJ@19') ");
				}else if("bk".equals(ZGXL[i])){
					sbGet.append(" or ");
					sbGet.append("TT.ZGXL in('XL@GJ@21','XL@GJ@22','XL@GJ@23','XL@GJ@28') ");
				}else if("gz".equals(ZGXL[i])){
					sbGet.append(" or ");
					sbGet.append("TT.ZGXL in('XL@GJ@34','XL@GJ@35','XL@GJ@91','XL@GJ@92','XL@GJ@93','XL@GJ@41','XL@GJ@42','XL@GJ@43','XL@GJ@44','XL@GJ@45','XL@GJ@46','XL@GJ@47','XL@GJ@48','XL@GJ@49','XL@GJ@61','XL@GJ@62','XL@GJ@63','XL@GJ@71','XL@GJ@73','XL@GJ@81','XL@GJ@83','XL@GJ@99','XL@GJ@0') ");
				}
			}
		}
		sbGet.append(")");
		ZGXLStr = sbGet.toString();
		if(!"( 1=2 )".equals(ZGXLStr)){
			map.put("ZGXL", ZGXLStr);
		}
		/**==========================================岗位类别条件===================================**/
		String[] XRGWLB = request.getParameterValues("XRGWLB");
		if(StringUtils.isNotBlank(isExcel)){
			String XRGWLBSTR =  request.getParameter("XRGWLBArray");
			if(StringUtils.isNotBlank(XRGWLBSTR)){
				if(!"null".equals(XRGWLBSTR)){
					XRGWLBSTR = XRGWLBSTR.substring(1,XRGWLBSTR.length()-1);
					if(StringUtils.isNotBlank(XRGWLBSTR)){
						XRGWLB = XRGWLBSTR.split(",");
					}
				}
			}
		}
		request.setAttribute("XRGWLBArray", Arrays.toString(XRGWLB));
		String XRGWLBStr = null;
		sbGet = new StringBuilder("( 1=2 ");
		if (null != XRGWLB   && XRGWLB.length > 0) {
			List<BaseProperties> list = CacheBasePropertiesManager.getPropertiesByGroupKey("GWLB");
			String str = new String();
			if (MMap.validateList(list)) {
				for (BaseProperties baseProperties : list) {
					str = str + "'"+baseProperties.getPropertyKey()+"',";
				}
				str = str.substring(0, str.length()-1);
			}
			for (int i = 0; i < XRGWLB.length; i++) {
				if ("all".equals(XRGWLB[i])) {
					break;
				}
				if ("wz".equals(XRGWLB[i])) {
					sbGet.append(" or TT.XRGWLB not in("+str+") ");
				} else {
					sbGet.append(" or ");
					sbGet.append("TT.XRGWLB  = ");
					sbGet.append("'");
					sbGet.append(XRGWLB[i]);
					sbGet.append("'");
				}
			}
		}
		sbGet.append(")");
		XRGWLBStr = sbGet.toString();
		if(!"( 1=2 )".equals(XRGWLBStr)){
			map.put("XRGWLB", XRGWLBStr);
		}
		/**==========================================年龄条件===================================**/
		String[] AGE = request.getParameterValues("AGE");
		if(StringUtils.isNotBlank(isExcel)){
			String AGESTR =  request.getParameter("AGEArray");
			if(StringUtils.isNotBlank(AGESTR)){
				if(!"null".equals(AGESTR)){
					AGESTR = AGESTR.substring(1,AGESTR.length()-1);
					if(StringUtils.isNotBlank(AGESTR)){
						AGE = AGESTR.split(",");
					}
				}
			}
		}
		request.setAttribute("AGEArray", Arrays.toString(AGE));
		String AGEStr = null;
		sbGet = new StringBuilder("( 1=2 ");
		if (null != AGE   && AGE.length > 0) {
			for (int i = 0; i < AGE.length; i++) {
				if ("all".equals(AGE[i])) {
					break;
				}
				
				if("24".equals(AGE[i])){
					sbGet.append(" or TT.AGE  between 0 and 24 ");
				}else if("29".equals(AGE[i])){
					sbGet.append(" or TT.AGE between 25 and 29 ");
				}else if("34".equals(AGE[i])){
					sbGet.append(" or TT.AGE between 30 and 34 ");
				}else if("39".equals(AGE[i])){
					sbGet.append(" or TT.AGE  between 35 and 39 ");
				}else if("44".equals(AGE[i])){
					sbGet.append(" or TT.AGE  between 40 and 44 ");
				}else if("49".equals(AGE[i])){
					sbGet.append(" or TT.AGE  between 45 and 49 ");
				}else if("54".equals(AGE[i])){
					sbGet.append(" or TT.AGE  between 50 and 54 ");
				}else if("59".equals(AGE[i])){
					sbGet.append(" or TT.AGE  between 55 and 59 ");
				}else if("60".equals(AGE[i])){
					sbGet.append(" or TT.AGE  >= 60");
				}else if("wtb".equals(AGE[i])){
					sbGet.append(" or TT.AGE  is null");
				}
			}
		}
		sbGet.append(")");
		AGEStr = sbGet.toString();
		if(!"( 1=2 )".equals(AGEStr)){
			map.put("AGEStr", AGEStr);
		}
		/**==========================================地区条件===================================**/
		//地区id
		String areaId =request.getParameter("areaId");
		//地区code
		String areaCode =request.getParameter("areaCode");
		if(areaCode == null){
			String appAreaCode = CacheBasePropertiesManager.getValueByPropertyKey("app.areaCode");
			/*BaseArea baseArea = CacheBaseAreaManager.getNodeByCode(appAreaCode);
			//String provinceId="18A92CF8-5F66-ABDE-BB42-B1ACCC4180EE";
			String provinceId = baseArea.getId();*/
			areaCode = appAreaCode;
		}
		BaseArea baseArea = new BaseArea();
		String shangJiAreaId = null;
		String shangJiAreaName = null;
		map.put("provinceId", baseUser.getProvinceId());
		BaseUser baus = new BaseUser();
		if(StringUtils.isNotBlank(areaId)){
			areaCode = CacheBaseAreaManager.getNodeById(areaId).getAreaCode();
			request.setAttribute("areaName", CacheBaseAreaManager.getNodeById(areaId).getAreaName());
			if (areaCode.length() == 3) {
				//省
				map.put("areaId", "CITY_ID");
				baus.setProvinceId(areaId);
			}
			if (areaCode.length() == 6) {
				//市
				map.put("areaId", "COUNTY_ID");
				baus.setCityId(areaId);
			}
			if (areaCode.length() == 9) {
				//县
				map.put("areaId", "COUNTY_ID");
				baus.setCountyId(areaId);
			}
		}else {
			//没有地区id，判断登录身份是省市县哪个权限
			baus = baseUser;
			String roleCode = baseUser.getDefaultRoleCode();
			if("role.provinceManager".equals(roleCode)){
				map.put("areaId","CITY_ID");
				baseArea = CacheBaseAreaManager.getNodeById(baseUser.getProvinceId());
			}else if("role.cityManager".equals(roleCode)){
				map.put("areaId", "COUNTY_ID");
				baseArea = CacheBaseAreaManager.getNodeById(baseUser.getCityId());
			}
			else if("role.countryManager".equals(roleCode)){
				map.put("areaId", "COUNTY_ID");
				baseArea = CacheBaseAreaManager.getNodeById(baseUser.getCountyId());
			}
			areaId = baseArea.getId();
			areaCode = baseArea.getAreaCode();
			request.setAttribute("areaName", baseArea.getAreaName());
		}
		request.setAttribute("areaId", areaId);
		request.setAttribute("areaCode", areaCode);
		request.setAttribute("wsAreaCode", request.getSession().getAttribute("wsAreaCode"));
		
		if ("role.provinceManager".equals(baseUser.getDefaultRoleCode())) {
			if (areaCode.length() == 6) {
				shangJiAreaId = baseAreaManager.getAreaInfo(areaCode).getObj1().toString();
				shangJiAreaName = baseAreaManager.getAreaInfo(areaCode).getObj0().toString();
			}
			if (areaCode.length() == 9) {
				shangJiAreaId = baseAreaManager.getAreaInfo(areaCode).getObj4().toString();
				shangJiAreaName = baseAreaManager.getAreaInfo(areaCode).getObj3().toString();
			}
		}
		if ("role.cityManager".equals(baseUser.getDefaultRoleCode())) {
			if (areaCode.length() == 9) {
				shangJiAreaId = baseAreaManager.getAreaInfo(areaCode).getObj4().toString();
				shangJiAreaName = baseAreaManager.getAreaInfo(areaCode).getObj3().toString();
			}
		}
		request.setAttribute("shangJiAreaId", shangJiAreaId);
		request.setAttribute("shangJiAreaName", shangJiAreaName);
		
		/**==========================================选中图表条件===================================**/
		String fxzbCharStr = request.getParameter("FXZB");
		request.setAttribute("FXZB", fxzbCharStr);
		String[] fxzbChar = null;
		StringBuilder FXZB = new StringBuilder("and(1=2");
		StringBuilder FXZBSelect = new StringBuilder("");
		if(fxzbCharStr == null ){
			fxzbChar = new String[]{"RYCH@GJ@53"};
		}else{
			fxzbChar = fxzbCharStr.split(",");
		}
		for (int i = 0; i < fxzbChar.length; i++) {
			FXZB = FXZB.append(" or t.HDRYCH = '").append(fxzbChar[i]).append("'");
			FXZBSelect = FXZBSelect.append("'").append(fxzbChar[i]).append("'").append(",");
			//request.setAttribute(fxzbChar[i],"checked=checked");
		}
		request.setAttribute("FXZBSelect", FXZBSelect);
		FXZB = FXZB.append(")");
		map.put("baus", baus);
		//分析指标的条件
		map.put("FXZB", FXZB.toString());
		//所有的地区数据
		List<BaseAreaTree> areaList = CacheBaseAreaManager.getTreeByParentCode(areaCode);
		request.setAttribute("areaList", areaList);
		//搜索结果
		Map<Object, Object> mapGet = null;
		//table数据
		mapGet = baseKpiItemManager.getExcellentTeacherHonorTable(map);
		//获取图表数据
		mapGet.put("CXFLRYJB", CXFLRYJB);
		mapGet = baseKpiItemManager.getPyramidChart(mapGet);
		//content数据
		mapGet.put("content", baseKpiItemManager.getSchoolClassStudentContent(map));
		//所有的数据
		request.setAttribute("mapGet", mapGet);
		
		if(StringUtils.isNotBlank(isExcel)){//sflag=1则进行导出excel操作
			String excel_title = "优秀教师情况分析"+"_"+DateFormater.DateToString(new Date(), "yyyyMMddHHmmss");
			HttpServletResponse response = ServletActionContext.getResponse();
			String finalFileName="";
			try {
				String agent = request.getHeader("User-Agent");
				boolean isMSIE = (agent != null && agent.indexOf("MSIE") != -1);
				if( isMSIE ){
					finalFileName = java.net.URLEncoder.encode(excel_title,"UTF8");
				}else{
					finalFileName = new String(excel_title.getBytes("UTF-8"), "ISO-8859-1");
				}
				response.setHeader("Content-disposition","attachment; filename="+finalFileName+".xls");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return EXCELLENT_TEACHER_HONOR_EXCEL;
		}else {
			return EXCELLENT_TEACHER_HONOR_MAIN;
		}
		
	}
	
	public String excellentTeacherIndexSchool(){
		String isExcel = request.getParameter("isExcel");
		BaseUser baseUser = (BaseUser) request.getSession().getAttribute(
				"ssoUser");
		String schoolId = request.getParameter("schoolId");// 学校id
		if (baseUser.getDefaultRoleCode().equals("role.schoolManager")) {
			String isFSB = MMap.isnullStr(request.getSession().getAttribute("isFSB"));
			BaseSchool baseSchool = new BaseSchool();
			if ("1".equals(isFSB)) {
				BaseSchoolFsb baseSchoolFsb = baseSchoolFsbManager.get(baseUser.getSchoolId());
				baseSchool = baseSchoolFsb.cloneTo(BaseSchool.class);
			}else {
				baseSchool = baseSchoolManager.get(baseUser.getSchoolId());
			}
			if (baseSchool != null) {
				request.setAttribute("schoolName", baseSchool.getSchoolName());
				request.setAttribute("baseSchool", baseSchool);
				schoolId = baseSchool.getId();
				request.setAttribute("schoolId", schoolId);
			}
		}/*else {
			if (StringUtils.isBlank(areaId)) {
				areaId = baseArea.getId();
				areaCode = baseArea.getAreaCode();
				request.setAttribute("areaName", baseArea.getAreaName());
			}else {
				areaCode = CacheBaseAreaManager.getNodeById(areaId).getAreaCode();
				request.setAttribute("areaName", CacheBaseAreaManager.getNodeById(areaId).getAreaName());
			}
		}*/
		
		//查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		
		//是否附设班教师
		String sffsbjs = request.getParameter("sffsbjs");
		if(StringUtils.isNotBlank(sffsbjs) && !"all".equals(sffsbjs)){
			map.put("SFFSBJS", sffsbjs);
			request.setAttribute("sffsbjs", sffsbjs);
		}
		/**==========================================学校条件===================================**/
		/*if(baseUser != null){
			if(StringUtils.isNotBlank(baseUser.getSchoolId())){
				map.put("XXJGID",baseUser.getSchoolId());
			}
		}*/
		if(StringUtils.isNotBlank(schoolId)){
			map.put("XXJGID",schoolId);
		}
		
		/**==========================================表条件===================================**/
		String TJSJ = request.getParameter("TJSJ");
		request.setAttribute("TJSJ", TJSJ);
		String table = null;
		String DATE = null;
		if(StringUtils.isBlank(TJSJ)){
			table = "";
			DATE = "";
		}else{
			try {
				Date date = new SimpleDateFormat("yyyy-MM").parse(TJSJ);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy_MM");
			table ="_" + sf.format(date).substring(2);
			DATE = "_"+sf.format(date).substring(2);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		map.put("table", "STAT_BASE_TEACHER_INFO"+table);
		map.put("DATE", DATE);
		/**==========================================筛选荣誉日期条件===================================**/
		String RYDate = request.getParameter("RYDate");
		if(StringUtils.isBlank(RYDate)){
			RYDate = "2017";
		}
		map.put("RYDate", RYDate);
		request.setAttribute("RYDate", RYDate);
		/**==========================================结果条件===================================**/
		String CXFLRYJB = request.getParameter("CXFLRYJB");
		if(StringUtils.isBlank(CXFLRYJB)){
			CXFLRYJB = "CXFL";
		}
		
		/*
		if("CXFL".equals(CXFLRYJB)){
			map.put("CXFLRYJB", "tt."+ CXFLRYJB);
		}else{
			map.put("CXFLRYJB", "t."+ CXFLRYJB);
		}*/
		request.setAttribute("CXFLRYJB", CXFLRYJB);
		/**==========================================城乡分类条件===================================**/
		String[] CXFL = request.getParameterValues("CXFL");
		if(StringUtils.isNotBlank(isExcel)){
			String CXFLSTR =  request.getParameter("CXFLArray");
			if(StringUtils.isNotBlank(CXFLSTR)){
				if(!"null".equals(CXFLSTR)){
					CXFLSTR = CXFLSTR.substring(1,CXFLSTR.length()-1);
					if(StringUtils.isNotBlank(CXFLSTR)){
						CXFL = CXFLSTR.split(",");
					}
				}
			}
		}
		request.setAttribute("CXFLArray", Arrays.toString(CXFL));
		String CXFLStr = null;
		StringBuilder sbGet = new StringBuilder("( 1=2 ");
		if (null != CXFL && CXFL.length > 0) {
			List<BaseProperties> list = CacheBasePropertiesManager.getPropertiesByGroupKey("cxflbm");
			String str = new String();
			if (MMap.validateList(list)) {
				for (BaseProperties baseProperties : list) {
					str = str + "'"+baseProperties.getPropertyKey()+"',";
				}
				str = str.substring(0, str.length()-1);
			}
			for (int i = 0; i < CXFL.length; i++) {
				if ("all".equals(CXFL[i])) {
					break;
				}
				if ("wz".equals(CXFL[i])) {
					sbGet.append(" or TT.CXFL not in("+str+") ");
				} else {
					sbGet.append(" or ");
					sbGet.append("TT.CXFL = ");
					sbGet.append("'");
					sbGet.append(CXFL[i]);
					sbGet.append("'");
				}
			}
		}
		sbGet.append(")");
		CXFLStr = sbGet.toString();
		if(!"( 1=2 )".equals(CXFLStr)){
			map.put("CXFL", CXFLStr);
		}
		
		
		/**==========================================性别条件===================================**/
		String[] XB = request.getParameterValues("XB") ;
		if(StringUtils.isNotBlank(isExcel)){
			String XBSTR =  request.getParameter("XBArray");
			if(StringUtils.isNotBlank(XBSTR)){
				if(!"null".equals(XBSTR)){
					XBSTR = XBSTR.substring(1,XBSTR.length()-1);
					if(StringUtils.isNotBlank(XBSTR)){
						XB = XBSTR.split(",");
					}
					}
				}
			
		}
		request.setAttribute("XBArray", Arrays.toString(XB));
		String XBStr = null;
		sbGet = new StringBuilder("( 1=2 ");
		if (null != XB && XB.length > 0) {
			List<BaseProperties> list = CacheBasePropertiesManager.getPropertiesByGroupKey("XB");
			String str = new String();
			if (MMap.validateList(list)) {
				for (BaseProperties baseProperties : list) {
					str = str + "'"+baseProperties.getPropertyKey()+"',";
				}
				str = str.substring(0, str.length()-1);
			}
			for (int i = 0; i < XB.length; i++) {
				if ("all".equals(XB[i])) {
					break;
				}
				if ("wz".equals(XB[i])) {
					sbGet.append(" or TT.XB not in("+str+") ");
				}else if ("wtb".equals(XB[i])) {
					sbGet.append(" or TT.XB is null ");
				} else {
					sbGet.append(" or ");
					sbGet.append("TT.XB = ");
					sbGet.append("'");
					sbGet.append(XB[i]);
					sbGet.append("'");
				}
			}
		}
		sbGet.append(")");
		XBStr = sbGet.toString();
		if(!"( 1=2 )".equals(XBStr)){
			map.put("XB", XBStr);
		}
		
		/**==========================================是否在编条件===================================**/
		String[] SFZB = request.getParameterValues("SFZB");
		if(StringUtils.isNotBlank(isExcel)){
			String SFZBSTR =  request.getParameter("XBArray");
			if(StringUtils.isNotBlank(SFZBSTR)){
				if(!"null".equals(SFZBSTR)){
					SFZBSTR = SFZBSTR.substring(1,SFZBSTR.length()-1);
					if(StringUtils.isNotBlank(SFZBSTR)){
						SFZB = SFZBSTR.split(",");
					}
				}
			}
		}
		request.setAttribute("SFZBArray", Arrays.toString(SFZB));
		String SFZBStr = null;
		sbGet = new StringBuilder("( 1=2 ");
		if (null != SFZB  && SFZB.length > 0) {
			List<BaseProperties> list = CacheBasePropertiesManager.getPropertiesByGroupKey("SFZB");
			String str = new String();
			if (MMap.validateList(list)) {
				for (BaseProperties baseProperties : list) {
					str = str + "'"+baseProperties.getPropertyKey()+"',";
				}
				str = str.substring(0, str.length()-1);
			}
			for (int i = 0; i < SFZB.length; i++) {
				if ("all".equals(SFZB[i])) {
					break;
				}
				if ("wz".equals(SFZB[i])) {
					sbGet.append(" or TT.SFZB not in("+str+") ");							
				}else if ("wtb".equals(SFZB[i])) {
					sbGet.append(" or TT.SFZB is null ");							
				} else {
					sbGet.append(" or ");
					sbGet.append("TT.SFZB  = ");
					sbGet.append("'");
					sbGet.append(SFZB[i]);
					sbGet.append("'");
				}
			}
		}
		sbGet.append(")");
		SFZBStr = sbGet.toString();
		if(!"( 1=2 )".equals(SFZBStr)){
			map.put("SFZB", SFZBStr);
		}
		/**==========================================最高学历条件===================================**/
		//学历
		//初中（国家推行9年义务教育，所以小学不算学历，视同文盲），高中（包括：高中，职高，中专，技校），大专（大学学专科），大本（大学本科），硕士（硕士研究生），博士（博士研究生）
		String[] ZGXL = request.getParameterValues("ZGXL");
		if(StringUtils.isNotBlank(isExcel)){
			String ZGXLSTR =  request.getParameter("ZGXLArray");
			if(StringUtils.isNotBlank(ZGXLSTR)){
				if(!"null".equals(ZGXLSTR)){
					ZGXLSTR = ZGXLSTR.substring(1,ZGXLSTR.length()-1);
					if(StringUtils.isNotBlank(ZGXLSTR)){
						ZGXL = ZGXLSTR.split(",");
					}
				}
			}
		}
		request.setAttribute("ZGXLArray", Arrays.toString(ZGXL));
		String ZGXLStr = null; 
		sbGet = new StringBuilder("( 1=2 ");
		if (null != ZGXL   && ZGXL.length > 0) {
			List<BaseProperties> list = CacheBasePropertiesManager.getPropertiesByGroupKey("XL");
			String str = new String();
			if (MMap.validateList(list)) {
				for (BaseProperties baseProperties : list) {
					str = str + "'"+baseProperties.getPropertyKey()+"',";
				}
				str = str.substring(0, str.length()-1);
			}
			for (int i = 0; i < ZGXL.length; i++) {
				if ("all".equals(ZGXL[i])) {
					break;
				}
				if ("wz".equals(ZGXL[i])) {
					sbGet.append(" or TT.ZGXL not in("+str+") ");
				} else if("bs".equals(ZGXL[i])){
					sbGet.append(" or ");
					sbGet.append("TT.ZGXL in('XL@GJ@11','XL@GJ@12','XL@GJ@13') ");
				}else if("ss".equals(ZGXL[i])){
					sbGet.append(" or ");
					sbGet.append("TT.ZGXL in('XL@GJ@14','XL@GJ@15','XL@GJ@16','XL@GJ@17','XL@GJ@18','XL@GJ@19') ");
				}else if("bk".equals(ZGXL[i])){
					sbGet.append(" or ");
					sbGet.append("TT.ZGXL in('XL@GJ@21','XL@GJ@22','XL@GJ@23','XL@GJ@28') ");
				}else if("gz".equals(ZGXL[i])){
					sbGet.append(" or ");
					sbGet.append("TT.ZGXL in('XL@GJ@34','XL@GJ@35','XL@GJ@91','XL@GJ@92','XL@GJ@93','XL@GJ@41','XL@GJ@42','XL@GJ@43','XL@GJ@44','XL@GJ@45','XL@GJ@46','XL@GJ@47','XL@GJ@48','XL@GJ@49','XL@GJ@61','XL@GJ@62','XL@GJ@63','XL@GJ@71','XL@GJ@73','XL@GJ@81','XL@GJ@83','XL@GJ@99','XL@GJ@0') ");
				}
			}
		}
		sbGet.append(")");
		ZGXLStr = sbGet.toString();
		if(!"( 1=2 )".equals(ZGXLStr)){
			map.put("ZGXL", ZGXLStr);
		}
		/**==========================================岗位类别条件===================================**/
		String[] XRGWLB = request.getParameterValues("XRGWLB");
		if(StringUtils.isNotBlank(isExcel)){
			String XRGWLBSTR =  request.getParameter("XRGWLBArray");
			if(StringUtils.isNotBlank(XRGWLBSTR)){
				if(!"null".equals(XRGWLBSTR)){
					XRGWLBSTR = XRGWLBSTR.substring(1,XRGWLBSTR.length()-1);
					if(StringUtils.isNotBlank(XRGWLBSTR)){
						XRGWLB = XRGWLBSTR.split(",");
					}
				}
			}
		}
		request.setAttribute("XRGWLBArray", Arrays.toString(XRGWLB));
		String XRGWLBStr = null;
		sbGet = new StringBuilder("( 1=2 ");
		if (null != XRGWLB   && XRGWLB.length > 0) {
			List<BaseProperties> list = CacheBasePropertiesManager.getPropertiesByGroupKey("GWLB");
			String str = new String();
			if (MMap.validateList(list)) {
				for (BaseProperties baseProperties : list) {
					str = str + "'"+baseProperties.getPropertyKey()+"',";
				}
				str = str.substring(0, str.length()-1);
			}
			for (int i = 0; i < XRGWLB.length; i++) {
				if ("all".equals(XRGWLB[i])) {
					break;
				}
				if ("wz".equals(XRGWLB[i])) {
					sbGet.append(" or TT.XRGWLB not in("+str+") ");
				} else {
					sbGet.append(" or ");
					sbGet.append("TT.XRGWLB  = ");
					sbGet.append("'");
					sbGet.append(XRGWLB[i]);
					sbGet.append("'");
				}
			}
		}
		sbGet.append(")");
		XRGWLBStr = sbGet.toString();
		if(!"( 1=2 )".equals(XRGWLBStr)){
			map.put("XRGWLB", XRGWLBStr);
		}
		/**==========================================年龄条件===================================**/
		String[] AGE = request.getParameterValues("AGE");
		if(StringUtils.isNotBlank(isExcel)){
			String AGESTR =  request.getParameter("AGEArray");
			if(StringUtils.isNotBlank(AGESTR)){
				if(!"null".equals(AGESTR)){
					AGESTR = AGESTR.substring(1,AGESTR.length()-1);
					if(StringUtils.isNotBlank(AGESTR)){
						AGE = AGESTR.split(",");
					}
				}
			}
		}
		request.setAttribute("AGEArray", Arrays.toString(AGE));
		String AGEStr = null;
		sbGet = new StringBuilder("( 1=2 ");
		if (null != AGE   && AGE.length > 0) {
			for (int i = 0; i < AGE.length; i++) {
				if ("all".equals(AGE[i])) {
					break;
				}
				
				if("24".equals(AGE[i])){
					sbGet.append(" or TT.AGE  between 0 and 24 ");
				}else if("29".equals(AGE[i])){
					sbGet.append(" or TT.AGE between 25 and 29 ");
				}else if("34".equals(AGE[i])){
					sbGet.append(" or TT.AGE between 30 and 34 ");
				}else if("39".equals(AGE[i])){
					sbGet.append(" or TT.AGE  between 35 and 39 ");
				}else if("44".equals(AGE[i])){
					sbGet.append(" or TT.AGE  between 40 and 44 ");
				}else if("49".equals(AGE[i])){
					sbGet.append(" or TT.AGE  between 45 and 49 ");
				}else if("54".equals(AGE[i])){
					sbGet.append(" or TT.AGE  between 50 and 54 ");
				}else if("59".equals(AGE[i])){
					sbGet.append(" or TT.AGE  between 55 and 59 ");
				}else if("60".equals(AGE[i])){
					sbGet.append(" or TT.AGE  >= 60");
				}else if("wtb".equals(AGE[i])){
					sbGet.append(" or TT.AGE  is null");
				}
			}
		}
		sbGet.append(")");
		AGEStr = sbGet.toString();
		if(!"( 1=2 )".equals(AGEStr)){
			map.put("AGEStr", AGEStr);
		}
		/**==========================================地区条件===================================**/
		/*//地区id
		String areaId =request.getParameter("areaId");
		//地区code
		String areaCode =request.getParameter("areaCode");
		if(areaCode == null){
			String appAreaCode = CacheBasePropertiesManager.getValueByPropertyKey("app.areaCode");
			String provinceId = baseArea.getId();
			areaCode = appAreaCode;
		}
		BaseArea baseArea = new BaseArea();
		String shangJiAreaId = null;
		String shangJiAreaName = null;
		map.put("provinceId", baseUser.getProvinceId());
		BaseUser baus = new BaseUser();
		if(StringUtils.isNotBlank(areaId)){
			areaCode = CacheBaseAreaManager.getNodeById(areaId).getAreaCode();
			request.setAttribute("areaName", CacheBaseAreaManager.getNodeById(areaId).getAreaName());
			if (areaCode.length() == 3) {
				//省
				map.put("areaId", "CITY_ID");
				baus.setProvinceId(areaId);
			}
			if (areaCode.length() == 6) {
				//市
				map.put("areaId", "COUNTY_ID");
				baus.setCityId(areaId);
			}
			if (areaCode.length() == 9) {
				//县
				map.put("areaId", "COUNTY_ID");
				baus.setCountyId(areaId);
			}
		}else {
			//没有地区id，判断登录身份是省市县哪个权限
			baus = baseUser;
			String roleCode = baseUser.getDefaultRoleCode();
			if("role.provinceManager".equals(roleCode)){
				map.put("areaId","CITY_ID");
				baseArea = CacheBaseAreaManager.getNodeById(baseUser.getProvinceId());
			}else if("role.cityManager".equals(roleCode)){
				map.put("areaId", "COUNTY_ID");
				baseArea = CacheBaseAreaManager.getNodeById(baseUser.getCityId());
			}
			else if("role.countryManager".equals(roleCode)){
				map.put("areaId", "COUNTY_ID");
				baseArea = CacheBaseAreaManager.getNodeById(baseUser.getCountyId());
			}
			areaId = baseArea.getId();
			areaCode = baseArea.getAreaCode();
			request.setAttribute("areaName", baseArea.getAreaName());
		}
		request.setAttribute("areaId", areaId);
		request.setAttribute("areaCode", areaCode);
		request.setAttribute("wsAreaCode", request.getSession().getAttribute("wsAreaCode"));
		
		if ("role.provinceManager".equals(baseUser.getDefaultRoleCode())) {
			if (areaCode.length() == 6) {
				shangJiAreaId = baseAreaManager.getAreaInfo(areaCode).getObj1().toString();
				shangJiAreaName = baseAreaManager.getAreaInfo(areaCode).getObj0().toString();
			}
			if (areaCode.length() == 9) {
				shangJiAreaId = baseAreaManager.getAreaInfo(areaCode).getObj4().toString();
				shangJiAreaName = baseAreaManager.getAreaInfo(areaCode).getObj3().toString();
			}
		}
		if ("role.cityManager".equals(baseUser.getDefaultRoleCode())) {
			if (areaCode.length() == 9) {
				shangJiAreaId = baseAreaManager.getAreaInfo(areaCode).getObj4().toString();
				shangJiAreaName = baseAreaManager.getAreaInfo(areaCode).getObj3().toString();
			}
		}
		request.setAttribute("shangJiAreaId", shangJiAreaId);
		request.setAttribute("shangJiAreaName", shangJiAreaName);*/
		
		/**==========================================选中图表条件===================================**/
		String fxzbCharStr = request.getParameter("FXZB"); 
		request.setAttribute("FXZB", fxzbCharStr);
		String[] fxzbChar = null;
		StringBuilder FXZB = new StringBuilder("and(1=2");
		StringBuilder FXZBSelect = new StringBuilder("");
		if(fxzbCharStr == null ){
			fxzbChar = new String[]{"RYCH@GJ@53"};
		}else{
			fxzbChar = fxzbCharStr.split(",");
		}
		for (int i = 0; i < fxzbChar.length; i++) {
			FXZB = FXZB.append(" or t.HDRYCH = '").append(fxzbChar[i]).append("'");
			FXZBSelect = FXZBSelect.append("'").append(fxzbChar[i]).append("'").append(",");
			//request.setAttribute(fxzbChar[i],"checked=checked");
		}
		request.setAttribute("FXZBSelect", FXZBSelect);
		FXZB = FXZB.append(")");
		/*map.put("baus", baus);*/
		//分析指标的条件
		map.put("FXZB", FXZB.toString());
		/*//所有的地区数据
		List<BaseAreaTree> areaList = CacheBaseAreaManager.getTreeByParentCode(areaCode);
		request.setAttribute("areaList", areaList);*/
		//搜索结果
		Map<String, Object> mapGet = null;
		if(StringUtils.isNotBlank(isExcel)){
			List<Map<String, Object>>  teacherList = (List<Map<String, Object>>)baseKpiItemManager.getExcellentTeacherHonorTableSchoolNoPage(map);
			request.setAttribute("teacherList", teacherList);
		}else{
			//table数据，分页查询
			int pageNo=1;
			if(request.getParameter("pageNo")!=null&&!request.getParameter("pageNo").equals("")){
				pageNo=Integer.valueOf(request.getParameter("pageNo"));
			}
			int pageSize=10;
			if(request.getParameter("pageSize")!=null&&!request.getParameter("pageSize").equals("")){
				pageSize=Integer.valueOf(request.getParameter("pageSize"));
			}
			Page page = baseKpiItemManager.getExcellentTeacherHonorTableSchool(map, pageSize, pageNo);
			if(page != null){
				List<Map<String, Object>>  teacherList = (List<Map<String, Object>>) page.getResult();
				request.setAttribute("teacherList", teacherList);
			}
			PageManager pm = new PageManager((int)page.getTotalCount(), pageSize,pageSize);
			pm.goToPage(pageNo);
			request.setAttribute("dataList", page.getResult());
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageHtml", pm.getPageCode());
			mapGet = baseKpiItemManager.getPyramidChartSchool(map);
			mapGet.put("teacherGetCount", pm.getRecordCount());
			mapGet.put("content",baseKpiItemManager.getSchoolClassStudentContentSchool(map));
			request.setAttribute("mapGet", mapGet);
		}
		
		
		//mapGet = baseKpiItemManager.getExcellentTeacherHonorTableSchool(map);
		//获取图表数据
		//mapGet = baseKpiItemManager.getPyramidChart(mapGet);
		//content数据
		//mapGet.put("content", baseKpiItemManager.getSchoolClassStudentContent(map));
		//所有的数据
		//request.setAttribute("mapGet", mapGet);
		
		if(StringUtils.isNotBlank(isExcel)){//sflag=1则进行导出excel操作
			String excel_title = "优秀教师情况分析"+"_"+DateFormater.DateToString(new Date(), "yyyyMMddHHmmss");
			HttpServletResponse response = ServletActionContext.getResponse();
			String finalFileName="";
			try {
				String agent = request.getHeader("User-Agent");
				boolean isMSIE = (agent != null && agent.indexOf("MSIE") != -1);
				if( isMSIE ){
					finalFileName = java.net.URLEncoder.encode(excel_title,"UTF8");
				}else{
					finalFileName = new String(excel_title.getBytes("UTF-8"), "ISO-8859-1");
				}
				response.setHeader("Content-disposition","attachment; filename="+finalFileName+".xls");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return EXCELLENT_TEACHER_HONOR_MAIN_SCHOOL_EXCEL;
		}else {
			return EXCELLENT_TEACHER_HONOR_MAIN_SCHOOL;
		}
		
	}
	
	
	/**
	 * 导出pdf
	 * @return
	 */
	public String exportPDF() {

		String areaCode=request.getParameter("areaCode");//区域code
		String areaId=request.getParameter("areaId");//区域code
		String subareaId=request.getParameter("subareaId");//登录用户地区的子地区
		String leftmenu=request.getParameter("leftmenu");//左侧菜单编码
		String areaType =request.getParameter("areaType");//地区
		String isPdf = request.getParameter("isPdf");
		
		//乡村教师
		String menu = request.getParameter("menu");
		String sffsbjs = request.getParameter("sffsbjs");
		String TJSJ = request.getParameter("TJSJ");
		String nd = request.getParameter("nd");
		String FXZB = request.getParameter("FXZB");
		
		//优秀教师
		String RYDate = request.getParameter("RYDate");
		String CXFLRYJB = request.getParameter("CXFLRYJB");
		String CXFLArray = request.getParameter("CXFLArray");
		String XBArray = request.getParameter("XBArray");
		String SFZBArray = request.getParameter("SFZBArray");
		String ZGXLArray = request.getParameter("ZGXLArray");
		String XRGWLBArray = request.getParameter("XRGWLBArray");
		String AGEArray = request.getParameter("AGEArray");
		
		
		Object o=request.getSession().getAttribute("ssoUser");
		if(o!=null){
			BaseUser user=(BaseUser)o;
			if(StringUtils.isNotBlank(leftmenu)){
				try {
					//String baseUrl=CacheBasePropertiesManager.getValueByPropertyKey("gb.app.view.base");
					String downloadPdfUrl=CacheBasePropertiesManager.getValueByPropertyKey("downloadPdfUrl");
					//baseUrl="http://localhost:8080/tim_mis/";
					ByteArrayOutputStream buffer = new ByteArrayOutputStream(); 
					String html ="";
					String title="";
					String finalFileName="";
					if("countryMain".equals(leftmenu)){//乡村教师基本情况
						//System.out.println(baseUrl+"pdf/basicReportOfSchoolTypePdf.jsp?areaCode="+areaCode+"&year="+year+"&subareaId="+subareaId);
						//html = HtmlUtil.getHtmlFile(downloadPdfUrl+"base/CountryTeacher/export/main_pdf.jsp?menu="+menu+"&sffsbjs="+sffsbjs+"&TJSJ="+TJSJ+"&areaId="+areaId+"&areaCode="+areaCode+"&subareaId="+subareaId+"&subareaId="+subareaId+"&isPdf="+isPdf+"&userId="+user.getId());  
						html = HtmlUtil.getHtmlFile(downloadPdfUrl+"pdf/country_main_pdf.jsp?menu="+menu+"&sffsbjs="+sffsbjs+"&TJSJ="+TJSJ+"&areaId="+areaId+"&areaCode="+areaCode+"&subareaId="+subareaId+"&isPdf="+isPdf+"&userId="+user.getId());
						//html = HtmlUtil.getHtmlFile(baseUrl+"pdf/basicReportOfSchoolTypePdf.jsp?areaCode="+areaCode+"&year=year_2014");  
						//System.out.println(html+"---");
						HtmlUtil. parseHTML2PDF(buffer,html,PageSize.A4);
						response.setContentType("application/pdf");
						title="甘肃省乡村教师基本待遇分析_"+DateFormater.DateToString(new Date(), "yyyyMMddHHmmss");
						String agent = request.getHeader("User-Agent");
						boolean isMSIE = (agent != null && agent.indexOf("MSIE") != -1);
						if( isMSIE ){
							finalFileName = java.net.URLEncoder.encode(title,"UTF8");
						}else{
							finalFileName = new String(title.getBytes("UTF-8"), "ISO-8859-1");
						}
					}
					if("countryMoney".equals(leftmenu)){//乡村教师基本薪资
						//System.out.println(baseUrl+"pdf/basicReportOfSchoolTypePdf.jsp?areaCode="+areaCode+"&year="+year+"&subareaId="+subareaId);
						//html = HtmlUtil.getHtmlFile(downloadPdfUrl+"base/CountryTeacher/export/main_pdf.jsp?menu="+menu+"&sffsbjs="+sffsbjs+"&TJSJ="+TJSJ+"&areaId="+areaId+"&areaCode="+areaCode+"&subareaId="+subareaId+"&subareaId="+subareaId+"&isPdf="+isPdf+"&userId="+user.getId());  
						html = HtmlUtil.getHtmlFile(downloadPdfUrl+"pdf/country_money_pdf.jsp?menu="+menu+"&nd="+nd+"&sffsbjs="+sffsbjs+"&TJSJ="+TJSJ+"&areaId="+areaId+"&areaCode="+areaCode+"&subareaId="+subareaId+"&isPdf="+isPdf+"&userId="+user.getId());
						//html = HtmlUtil.getHtmlFile(baseUrl+"pdf/basicReportOfSchoolTypePdf.jsp?areaCode="+areaCode+"&year=year_2014");  
						//System.out.println(html+"---");
						HtmlUtil. parseHTML2PDF(buffer,html,PageSize.A4);
						response.setContentType("application/pdf");
						title="甘肃省乡村教师基本待遇分析_"+DateFormater.DateToString(new Date(), "yyyyMMddHHmmss");
						String agent = request.getHeader("User-Agent");
						boolean isMSIE = (agent != null && agent.indexOf("MSIE") != -1);
						if( isMSIE ){
							finalFileName = java.net.URLEncoder.encode(title,"UTF8");
						}else{
							finalFileName = new String(title.getBytes("UTF-8"), "ISO-8859-1");
						}
					}
					if("countryHonor".equals(leftmenu)){//乡村教师荣誉情况
						//System.out.println(baseUrl+"pdf/basicReportOfSchoolTypePdf.jsp?areaCode="+areaCode+"&year="+year+"&subareaId="+subareaId);
						html = HtmlUtil.getHtmlFile(downloadPdfUrl+"pdf/country_honor_pdf.jsp?menu="+menu+"&FXZB="+FXZB+"&sffsbjs="+sffsbjs+"&TJSJ="+TJSJ+"&areaId="+areaId+"&areaCode="+areaCode+"&subareaId="+subareaId+"&isPdf="+isPdf+"&userId="+user.getId());  
						//System.out.println(html+"---");
						HtmlUtil. parseHTML2PDF(buffer,html,PageSize.A4);
						response.setContentType("application/pdf");
						title="甘肃省乡村教师荣誉分析_"+DateFormater.DateToString(new Date(), "yyyyMMddHHmmss");
						String agent = request.getHeader("User-Agent");
						boolean isMSIE = (agent != null && agent.indexOf("MSIE") != -1);
						if( isMSIE ){
							finalFileName = java.net.URLEncoder.encode(title,"UTF8");
						}else{
							finalFileName = new String(title.getBytes("UTF-8"), "ISO-8859-1");
						}
					}
					if("excellentMain".equals(leftmenu)){//优秀教师情况
						//System.out.println(baseUrl+"pdf/basicReportOfSchoolTypePdf.jsp?areaCode="+areaCode+"&year="+year+"&subareaId="+subareaId);
						html = HtmlUtil.getHtmlFile(downloadPdfUrl+"pdf/excellent_main_pdf.jsp?menu="+menu+"&sffsbjs="+sffsbjs+"&TJSJ="+TJSJ+"&RYDate="+RYDate+"&CXFLRYJB="+CXFLRYJB+"&CXFLArray="+CXFLArray+"&XBArray="+XBArray+"&SFZBArray="+SFZBArray+"&ZGXLArray="+ZGXLArray+"&XRGWLBArray="+XRGWLBArray+"&AGEArray="+AGEArray+"&FXZB="+FXZB+"&areaId="+areaId+"&areaCode="+areaCode+"&subareaId="+subareaId+"&isPdf="+isPdf+"&userId="+user.getId());  
						//System.out.println(html+"---");
						HtmlUtil. parseHTML2PDF(buffer,html,PageSize.A4);
						response.setContentType("application/pdf");
						title="甘肃省优秀教师情况_"+DateFormater.DateToString(new Date(), "yyyyMMddHHmmss");
						String agent = request.getHeader("User-Agent");
						boolean isMSIE = (agent != null && agent.indexOf("MSIE") != -1);
						if( isMSIE ){
							finalFileName = java.net.URLEncoder.encode(title,"UTF8");
						}else{
							finalFileName = new String(title.getBytes("UTF-8"), "ISO-8859-1");
						}
					}
					if("excellentSchool".equals(leftmenu)){//优秀教师情况
						//System.out.println(baseUrl+"pdf/basicReportOfSchoolTypePdf.jsp?areaCode="+areaCode+"&year="+year+"&subareaId="+subareaId);
						html = HtmlUtil.getHtmlFile(downloadPdfUrl+"pdf/excellent_school_pdf.jsp?menu="+menu+"&sffsbjs="+sffsbjs+"&TJSJ="+TJSJ+"&RYDate="+RYDate+"&CXFLRYJB="+CXFLRYJB+"&CXFLArray="+CXFLArray+"&XBArray="+XBArray+"&SFZBArray="+SFZBArray+"&ZGXLArray="+ZGXLArray+"&XRGWLBArray="+XRGWLBArray+"&AGEArray="+AGEArray+"&FXZB="+FXZB+"&areaId="+areaId+"&areaCode="+areaCode+"&subareaId="+subareaId+"&isPdf="+isPdf+"&userId="+user.getId());  
						//System.out.println(html+"---");
						HtmlUtil. parseHTML2PDF(buffer,html,PageSize.A4);
						response.setContentType("application/pdf");
						title="甘肃省优秀教师情况_"+DateFormater.DateToString(new Date(), "yyyyMMddHHmmss");
						String agent = request.getHeader("User-Agent");
						boolean isMSIE = (agent != null && agent.indexOf("MSIE") != -1);
						if( isMSIE ){
							finalFileName = java.net.URLEncoder.encode(title,"UTF8");
						}else{
							finalFileName = new String(title.getBytes("UTF-8"), "ISO-8859-1");
						}
					}
					
					
					
					response.addHeader("Content-Disposition", "attachment;filename="+finalFileName+".pdf");
					response.setContentLength(buffer.size());  
					ServletOutputStream out = response.getOutputStream();  
					buffer.writeTo(out);  
					out.flush();  
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return null;
				
			}
			
			
		}
		
		return null;
	}
	
	
}
