/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package org.my431.base.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.my431.base.json.JsonUtil;
import org.my431.base.model.BaseArea;
import org.my431.base.model.BaseAreaTree;
import org.my431.base.model.BaseProperties;
import org.my431.base.model.BaseToDo;
import org.my431.base.model.BaseUser;
import org.my431.base.services.BaseAreaManager;
import org.my431.base.services.BaseSchoolManager;
import org.my431.base.services.BaseToDoManager;
import org.my431.base.services.BaseUserManager;
import org.my431.base.services.CacheBaseAreaManager;
import org.my431.base.services.CacheBasePropertiesManager;
import org.my431.base.services.PageManager;
import org.my431.platform.dao.support.Page;
import org.my431.platform.web.StrutsTreeEntityAction;
import org.my431.taglib.My431Function;
import org.my431.util.MMap;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author wangzhen
 * @version 1.0
 * @since 1.0
 */


public class BaseToDoAction extends StrutsTreeEntityAction<BaseToDo,BaseToDoManager> implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "//base/BaseToDo/query.jsp";
	protected static final String LIST_JSP= "//base/BaseToDo/list.jsp";
	protected static final String CREATE_JSP = "//base/BaseToDo/create.jsp";
	protected static final String EDIT_JSP = "//base/BaseToDo/edit.jsp";
	protected static final String SHOW_JSP = "//base/BaseToDo/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!//base/BaseToDo/list.jspx";
	protected static final String INDEX_TODO_JSP = "//base/BaseToDo/index_todo.jsp";
	protected static final String STAT_BASETEACHERINFO_YEY_JSP = "/base/BaseToDo/yey/teacherinfo_yey.jsp";
	
	private BaseSchoolManager baseSchoolManager;
	private BaseAreaManager baseAreaManager;
	private BaseToDoManager baseToDoManager;
	@Autowired
	private BaseUserManager baseUserManager;
	
	private BaseToDo baseToDo;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			baseToDo = new BaseToDo();
		} else {
			baseToDo = (BaseToDo)baseToDoManager.get(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseToDoManager(BaseToDoManager manager){
		this.baseToDoManager = manager;
	}
	public void setBaseSchoolManager(BaseSchoolManager manager) {
		this.baseSchoolManager = manager;
	}
	
	public Object getModel() {
		return baseToDo;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	/**
	* 
	* @Description: 待办业务主页 
	* @author hyl     
	* @date 2017-6-20 下午5:31:07  
	* @version V1.0 
	* @author user
	*/
	public String indexTodo() {
		Object o=request.getSession().getAttribute("ssoUser");
		if(o!=null){
			BaseUser baseUser=(BaseUser)o;
			request.setAttribute("baseUser", baseUser);
		}
		return INDEX_TODO_JSP;
	}
	
	public String statBaseTeacherInfo(){
		String areaCode = "";// 区域code
		String areaId = request.getParameter("areaId");// 区域id
		Object o = request.getSession().getAttribute("ssoUser");
		Object wsAreaCode = request.getSession().getAttribute("wsAreaCode");
		if (o != null) {
			BaseUser user = (BaseUser) o;
			BaseArea baseArea = new BaseArea();
			if (user.getDefaultRoleCode().equals("role.provinceManager")) {
				if (areaId == null || "".equals(areaId)) {
					baseArea = CacheBaseAreaManager.getNodeById(user.getProvinceId());
				}
			}
			if (user.getDefaultRoleCode().equals("role.cityManager")) {
				if (areaId == null || "".equals(areaId)) {
					baseArea = CacheBaseAreaManager.getNodeById(user.getCityId());
				}
			}
			if (user.getDefaultRoleCode().equals("role.countryManager")) {
				if (areaId == null || "".equals(areaId)) {
					baseArea = CacheBaseAreaManager.getNodeById(user.getCountyId());
				}
			}
			if (StringUtils.isBlank(areaId)) {
				areaId = baseArea.getId();
				areaCode = baseArea.getAreaCode();
				request.setAttribute("areaName", baseArea.getAreaName());
			}else {
				areaCode = CacheBaseAreaManager.getNodeById(areaId).getAreaCode();
				request.setAttribute("areaName", CacheBaseAreaManager.getNodeById(areaId).getAreaName());
			}
			request.setAttribute("areaId", areaId);
			request.setAttribute("areaCode", areaCode);
			request.setAttribute("wsAreaCode", wsAreaCode);
			String shangJiAreaId = null;
			String shangJiAreaName = null;
			if ("role.provinceManager".equals(user.getDefaultRoleCode())) {
				if (areaCode.length() == 6) {
					shangJiAreaId = baseAreaManager.getAreaInfo(areaCode).getObj1().toString();
					shangJiAreaName = baseAreaManager.getAreaInfo(areaCode).getObj0().toString();
				}
				if (areaCode.length() == 9) {
					shangJiAreaId = baseAreaManager.getAreaInfo(areaCode).getObj4().toString();
					shangJiAreaName = baseAreaManager.getAreaInfo(areaCode).getObj3().toString();
				}
			}
			if ("role.cityManager".equals(user.getDefaultRoleCode())) {
				if (areaCode.length() == 9) {
					shangJiAreaId = baseAreaManager.getAreaInfo(areaCode).getObj4().toString();
					shangJiAreaName = baseAreaManager.getAreaInfo(areaCode).getObj3().toString();
				}
			}
			request.setAttribute("shangJiAreaId", shangJiAreaId);
			request.setAttribute("shangJiAreaName", shangJiAreaName);
           //根据属性的key从缓存中提取出所需要的属性的list列表
			List<BaseProperties> cxflList = CacheBasePropertiesManager.getPropertiesByGroupKey("cxflbm");//城乡分类
			List<BaseProperties> xbList = CacheBasePropertiesManager.getPropertiesByGroupKey("XB");//性别
			List<BaseProperties> sfzbList = CacheBasePropertiesManager.getPropertiesByGroupKey("SFZB");//是否在编
			List<BaseProperties> xlList = CacheBasePropertiesManager.getPropertiesByGroupKey("XL");//学历
			List<BaseProperties> gwlbList = CacheBasePropertiesManager.getPropertiesByGroupKey("GWLB");//岗位类别
			//查询未知的时候用到
			String cxflStr = "";
			String xlStr = "";//学历
			String sfzbStr = "";
			String gwlbStr = "";
			if (MMap.validateList(cxflList)) {
				for (BaseProperties baseProperties : cxflList) {
					cxflStr = cxflStr + "'"+baseProperties.getPropertyKey()+"',";
				}
				cxflStr = cxflStr.substring(0, cxflStr.length()-1);
			}
			if (MMap.validateList(xlList)) {
				for (BaseProperties baseProperties : xlList) {
					xlStr = xlStr + "'"+baseProperties.getPropertyKey()+"',";
				}
				xlStr = xlStr.substring(0, xlStr.length()-1);
			}
			if (MMap.validateList(sfzbList)) {
				for (BaseProperties baseProperties : sfzbList) {
					sfzbStr = sfzbStr + "'"+baseProperties.getPropertyKey()+"',";
				}
				sfzbStr = sfzbStr.substring(0, sfzbStr.length()-1);
			}
			if (MMap.validateList(gwlbList)) {
				for (BaseProperties baseProperties : gwlbList) {
					gwlbStr = gwlbStr + "'"+baseProperties.getPropertyKey()+"',";
				}
				gwlbStr = gwlbStr.substring(0, gwlbStr.length()-1);
			}
			request.setAttribute("cxflList", cxflList);
			request.setAttribute("xbList", xbList);
			request.setAttribute("sfzbList", sfzbList);
			request.setAttribute("gwlbList", gwlbList);
			/**
			 * 这是页面开头“筛选条件”进行的条件收集，并执方法，统计数据
			 */
			//筛选条件
			String[] cxfl = request.getParameterValues("cxfl");
			//System.out.println(Arrays.toString(cxfl));
			String[] xb = request.getParameterValues("xb");
			String[] sfzb = request.getParameterValues("sfzb");
			String[] xl = request.getParameterValues("xl");//学历
			String[] gwlb = request.getParameterValues("gwlb");
			String[] age = request.getParameterValues("age");
			
			request.setAttribute("cxflArray", Arrays.toString(cxfl));
			request.setAttribute("xbArray", Arrays.toString(xb));
			request.setAttribute("sfzbArray", Arrays.toString(sfzb));
			request.setAttribute("xlArray", Arrays.toString(xl));
			request.setAttribute("gwlbArray", Arrays.toString(gwlb));
			request.setAttribute("ageArray", Arrays.toString(age));
			//利用buffer拼接要执行的查询条件的sql
			StringBuffer buffer = new StringBuffer("");
			//城乡分类：
			if (cxfl != null && cxfl.length>0) {
				if (!MMap.loopArrays(cxfl, "all")) {//不包含all
					buffer.append(" and ( ");
					for (int i = 0; i < cxfl.length; i++) {
						if (i == 0) {
							if ("wtb".equals(cxfl[i])) {
								buffer.append(" t.cxfl is null ");
							}else if ("wz".equals(cxfl[i])) {
								buffer.append(" t.cxfl not in("+cxflStr+") ");
							}else {
								buffer.append(" t.cxfl= '"+cxfl[i]+"' ");
							}
						}else {
							if ("wtb".equals(cxfl[i])) {
								buffer.append(" or t.cxfl is null ");
							}else if ("wz".equals(cxfl[i])) {
								buffer.append(" or t.cxfl not in("+cxflStr+") ");
							}else {
								buffer.append(" or t.cxfl= '"+cxfl[i]+"' ");
							}
						}
					}
					buffer.append(" )");
				}
			}
			//性别：
			if (xb != null && xb.length>0) {
				if (!MMap.loopArrays(xb, "all")) {//不包含all
					buffer.append(" and ( ");
					for (int i = 0; i < xb.length; i++) {
						if (i == 0) {
							if ("wtb".equals(xb[i])) {
								buffer.append(" t.xb  is null ");
							}else {
								buffer.append(" t.xb = '"+xb[i]+"' ");
							}
						}else {
							if ("wtb".equals(xb[i])) {
								buffer.append(" or t.xb  is null ");
							}else {
								buffer.append(" or t.xb = '"+xb[i]+"' ");
							}
						}
					}
					buffer.append(" )");
				}
			}
			//是否在编：
			if (sfzb != null && sfzb.length>0) {
				if (!MMap.loopArrays(sfzb, "all")) {//不包含all
					buffer.append(" and ( ");
					for (int i = 0; i < sfzb.length; i++) {
						if (i == 0) {
							if ("wtb".equals(sfzb[i])) {
								buffer.append(" t.sfzb  is null ");
							}else if ("wz".equals(sfzb[i])) {
								buffer.append(" t.sfzb not in ("+sfzbStr+") ");
							}else {
								buffer.append(" t.sfzb = '"+sfzb[i]+"' ");
							}
						}else {
							if ("wtb".equals(sfzb[i])) {
								buffer.append(" or t.sfzb  is null ");
							}else if ("wz".equals(sfzb[i])) {
								buffer.append(" or t.sfzb not in ("+sfzbStr+") ");
							}else {
								buffer.append(" or t.sfzb = '"+sfzb[i]+"' ");
							}
						}
					}
					buffer.append(" )");
				}
			}
			//学历
			//初中（国家推行9年义务教育，所以小学不算学历，视同文盲），高中（包括：高中，职高，中专，技校），大专（大学学专科），大本（大学本科），硕士（硕士研究生），博士（博士研究生）
			if (xl != null && xl.length>0) {
				if (!MMap.loopArrays(xl, "all")) {//不包含all
					buffer.append(" and ( ");
					int count = 0;
					if (MMap.loopArrays(xl, "boshi")) {
						if (count == 0) {
							buffer.append(" t.zgxl in('XL@GJ@11','XL@GJ@12','XL@GJ@13') ");
						}else {
							buffer.append(" or t.zgxl in('XL@GJ@11','XL@GJ@12','XL@GJ@13') ");
						}
						count++;
					}
					if (MMap.loopArrays(xl, "shuoshi")) {
						if (count == 0) {
							buffer.append(" t.zgxl in('XL@GJ@14','XL@GJ@15','XL@GJ@16','XL@GJ@17','XL@GJ@18','XL@GJ@19') ");
						}else {
							buffer.append(" or t.zgxl in('XL@GJ@14','XL@GJ@15','XL@GJ@16','XL@GJ@17','XL@GJ@18','XL@GJ@19') ");
						}
						count++;
					}
					if (MMap.loopArrays(xl, "benke")) {//本科
						if (count == 0) {
							buffer.append(" t.zgxl in('XL@GJ@21','XL@GJ@22','XL@GJ@23','XL@GJ@28') ");
						}else {
							buffer.append(" or t.zgxl in('XL@GJ@21','XL@GJ@22','XL@GJ@23','XL@GJ@28') ");
						}
						count++;
					}
					if (MMap.loopArrays(xl, "gaozhong")) {//高中以下
						if (count == 0) {
							buffer.append(" t.zgxl in('XL@GJ@34','XL@GJ@35','XL@GJ@91','XL@GJ@92','XL@GJ@93','XL@GJ@41','XL@GJ@42','XL@GJ@43','XL@GJ@44','XL@GJ@45','XL@GJ@46','XL@GJ@47','XL@GJ@48','XL@GJ@49','XL@GJ@61','XL@GJ@62','XL@GJ@63','XL@GJ@71','XL@GJ@73','XL@GJ@81','XL@GJ@83','XL@GJ@99','XL@GJ@0') ");
						}else {
							buffer.append(" or t.zgxl in('XL@GJ@34','XL@GJ@35','XL@GJ@91','XL@GJ@92','XL@GJ@93','XL@GJ@41','XL@GJ@42','XL@GJ@43','XL@GJ@44','XL@GJ@45','XL@GJ@46','XL@GJ@47','XL@GJ@48','XL@GJ@49','XL@GJ@61','XL@GJ@62','XL@GJ@63','XL@GJ@71','XL@GJ@73','XL@GJ@81','XL@GJ@83','XL@GJ@99','XL@GJ@0') ");
						}
						count++;
					}
					if (MMap.loopArrays(xl, "wz")) {//未知
						if (count == 0) {
							buffer.append(" t.zgxl not in ("+xlStr+") ");
						}else {
							buffer.append(" or t.zgxl not in ("+xlStr+") ");
						}
						count++;
					}
					if (MMap.loopArrays(xl, "wtb")) {//未填报
						if (count == 0) {
							buffer.append(" t.zgxl is null ");
						}else {
							buffer.append(" or t.zgxl is null ");
						}
						count++;
					}
					buffer.append(" )");
				}
			}
			//岗位类别
			if (gwlb != null && gwlb.length>0) {
				if (!MMap.loopArrays(gwlb, "all")) {//不包含all
					buffer.append(" and ( ");
					for (int i = 0; i < gwlb.length; i++) {
						if (i == 0) {
							if ("wz".equals(gwlb[i])) {
								buffer.append(" t.xrgwlb   is null ");
							}else if ("wtb".equals(gwlb[i])) {
								buffer.append(" t.xrgwlb   not in ("+gwlbStr+")");
							}else {
								buffer.append(" t.xrgwlb  = '"+gwlb[i]+"' ");
							}
						}else {
							if ("wz".equals(gwlb[i])) {
								buffer.append(" or t.xrgwlb  is null ");
							}else if ("wtb".equals(gwlb[i])) {
								buffer.append(" or t.xrgwlb  not in ("+gwlbStr+")");
							}else {
								buffer.append(" or t.xrgwlb  = '"+gwlb[i]+"' ");
							}
						}
					}
					buffer.append(" )");
				}
			}
			//年龄
			if (age != null && age.length>0) {
				if (!MMap.loopArrays(age, "all")) {//不包含all
					buffer.append(" and ( ");
					int count = 0;
					if (MMap.loopArrays(age, "24")) {
						if (count == 0) {
							//buffer.append(" floor(MONTHS_BETWEEN(sysdate,to_date(t.csrq,'yyyy-MM-dd'))/12)<=24 ");
							buffer.append(" age<=24 ");
						}else {
							//buffer.append(" or floor(MONTHS_BETWEEN(sysdate,to_date(t.csrq,'yyyy-MM-dd'))/12)<=24");
							buffer.append(" or age<=24");
						}
						count++;
					}
					if (MMap.loopArrays(age, "25")) {
						if (count == 0) {
							//buffer.append(" floor(MONTHS_BETWEEN(sysdate,to_date(t.csrq,'yyyy-MM-dd'))/12) between  25 and 29 ");
							buffer.append(" age between  25 and 29 ");
						}else {
							//buffer.append(" or floor(MONTHS_BETWEEN(sysdate,to_date(t.csrq,'yyyy-MM-dd'))/12) between  25 and 29 ");
							buffer.append(" or age between  25 and 29 ");
						}
						count++;
					}
					if (MMap.loopArrays(age, "30")) {
						if (count == 0) {
							//buffer.append(" floor(MONTHS_BETWEEN(sysdate,to_date(t.csrq,'yyyy-MM-dd'))/12) between  30 and 34 ");
							buffer.append(" age between  30 and 34 ");
						}else {
							//buffer.append(" or floor(MONTHS_BETWEEN(sysdate,to_date(t.csrq,'yyyy-MM-dd'))/12) between  30 and 34 ");
							buffer.append(" or age between  30 and 34 ");
						}
						count++;
					}
					if (MMap.loopArrays(age, "35")) {
						if (count == 0) {
							//buffer.append(" floor(MONTHS_BETWEEN(sysdate,to_date(t.csrq,'yyyy-MM-dd'))/12) between  35 and 39 ");
							buffer.append(" age between  35 and 39 ");
						}else {
							//buffer.append(" or floor(MONTHS_BETWEEN(sysdate,to_date(t.csrq,'yyyy-MM-dd'))/12) between  35 and 39 ");
							buffer.append(" or age between  35 and 39 ");
						}
						count++;
					}
					if (MMap.loopArrays(age, "40")) {
						if (count == 0) {
							//buffer.append(" floor(MONTHS_BETWEEN(sysdate,to_date(t.csrq,'yyyy-MM-dd'))/12) between  40 and 44 ");
							buffer.append(" age between  40 and 44 ");
						}else {
							//buffer.append(" or floor(MONTHS_BETWEEN(sysdate,to_date(t.csrq,'yyyy-MM-dd'))/12) between  40 and 44 ");
							buffer.append(" or age between  40 and 44 ");
						}
						count++;
					}
					if (MMap.loopArrays(age, "45")) {
						if (count == 0) {
							//buffer.append(" floor(MONTHS_BETWEEN(sysdate,to_date(t.csrq,'yyyy-MM-dd'))/12) between  45 and 49 ");
							buffer.append(" age between  45 and 49 ");
						}else {
							//buffer.append(" or floor(MONTHS_BETWEEN(sysdate,to_date(t.csrq,'yyyy-MM-dd'))/12) between  45 and 49 ");
							buffer.append(" or age between  45 and 49 ");
						}
						count++;
					}
					if (MMap.loopArrays(age, "50")) {
						if (count == 0) {
							//buffer.append(" floor(MONTHS_BETWEEN(sysdate,to_date(t.csrq,'yyyy-MM-dd'))/12) between  50 and 54 ");
							buffer.append(" age between  50 and 54 ");
						}else {
							//buffer.append(" or floor(MONTHS_BETWEEN(sysdate,to_date(t.csrq,'yyyy-MM-dd'))/12) between  50 and 54 ");
							buffer.append(" or age between  50 and 54 ");
						}
						count++;
					}
					if (MMap.loopArrays(age, "55")) {
						if (count == 0) {
							//buffer.append(" floor(MONTHS_BETWEEN(sysdate,to_date(t.csrq,'yyyy-MM-dd'))/12) between  55 and 59 ");
							buffer.append(" age between  55 and 59 ");
						}else {
							//buffer.append(" or floor(MONTHS_BETWEEN(sysdate,to_date(t.csrq,'yyyy-MM-dd'))/12) between  55 and 59 ");
							buffer.append(" age between  55 and 59 ");
						}
						count++;
					}
					if (MMap.loopArrays(age, "60")) {
						if (count == 0) {
							//buffer.append(" floor(MONTHS_BETWEEN(sysdate,to_date(t.csrq,'yyyy-MM-dd'))/12)>=60 ");
							buffer.append(" age>=60 ");
						}else {
							//buffer.append(" or floor(MONTHS_BETWEEN(sysdate,to_date(t.csrq,'yyyy-MM-dd'))/12)>=60 ");
							buffer.append(" or age>=60 ");
						}
						count++;
					}
					if (MMap.loopArrays(age, "wtb")) {
						if (count == 0) {
							buffer.append(" t.csrq is null ");
						}else {
							buffer.append(" or t.csrq is null ");
						}
						count++;
					}
					buffer.append(" )");
				}
			}
			/**
			 *这是在分组指标选择不同的指标时显示的“ 图表” 和“ 跳转的页面 ”
			 */	
			String menu = request.getParameter("menu");//子菜单
			String fzzb = request.getParameter("fzzb");//分组指标
			String xsfs = request.getParameter("xsfs");//显示方式
			
			if (StringUtils.isBlank(menu)) {
				menu = "yey";//默认显示的menu是幼儿园
			}
			if (StringUtils.isBlank(fzzb)) {
				fzzb = "se_cxfl";//默认显示城乡分类指标
			}
			if (StringUtils.isBlank(xsfs)) {
				xsfs = "pie";//默认显示饼图
			}
			
			request.setAttribute("menu", menu);
			request.setAttribute("fzzb", fzzb);
			request.setAttribute("xsfs", xsfs);
			
			MMap mMap = baseToDoManager.getMapOfBaseTeacherInfo(menu, fzzb, buffer.toString(), areaCode, areaId);
			
			Map<String, Integer> mapChart = (Map<String, Integer>) mMap.getObj();
			Map<String, Integer> mapAreaZhibiao = (Map<String, Integer>) mMap.getObj1();
			Map<String, Integer> mapsArea = (Map<String, Integer>) mMap.getObj2();
			
			Integer allCnt = (Integer) mMap.getObj3();
			
			Map<String, Integer> mapChart1 = new HashMap<String, Integer>();
			Map<String, Map<String, Object>> mapChartJson = new HashMap<String, Map<String, Object>>();
			Map<String, Integer> mapChart2 = new HashMap<String, Integer>();
			
			List<Map<String, Object>> json_list = new ArrayList<Map<String,Object>>();
			
			if (!mapChart.isEmpty()) {
				
				for (Map.Entry<String, Integer> entryChart : mapChart.entrySet()) {
					
					Map<String, Object> json_map = new HashMap<String, Object>();
					
					String key = entryChart.getKey();
					Integer val = entryChart.getValue();
					
					if (!"nullkey".equals(key)) {
						if ("se_cxfl".equals(fzzb)) {
							String pValue = CacheBasePropertiesManager.getValueByPropertyKey(key);
							if (StringUtils.isNotBlank(pValue)) {
								json_map.put("label", pValue);
								mapChart1.put(key, val);
							}else {
								json_map.put("label", "未知");
								mapChart1.put("weizhi", val);
							}
						}else if ("se_xb".equals(fzzb)) {
							String pValue = CacheBasePropertiesManager.getValueByPropertyKey(key);
							if (StringUtils.isNotBlank(pValue)) {
								json_map.put("label", pValue);
								mapChart1.put(key, val);
							}else {
								json_map.put("label", "未知");
								mapChart1.put("weizhi", val);
							}
						}else if ("se_sfzb".equals(fzzb)) {
							//是否在编
							String pValue = CacheBasePropertiesManager.getValueByPropertyKey(key);
							if (StringUtils.isNotBlank(pValue)) {
								json_map.put("label", pValue);
								mapChart1.put(key, val);
							}else {
								json_map.put("label", "未知");
								mapChart1.put("weizhi", val);
							}
						}else if ("se_xl".equals(fzzb)) {//学历
							if ("weizhi".equals(key)) {
								json_map.put("label", "未知");
								mapChart1.put("weizhi", val);
							}
							if ("boshi".equals(key)) {
								json_map.put("label", "博士");
								mapChart1.put("boshi", val);
							}
							if ("shuoshi".equals(key)) {
								json_map.put("label", "硕士");
								mapChart1.put("shuoshi", val);
							}
							if ("benke".equals(key)) {
								json_map.put("label", "本科");
								mapChart1.put("benke", val);
							}
							if ("zhuanke".equals(key)) {
								json_map.put("label", "专科");
								mapChart1.put("zhuanke", val);
							}
							if ("gaozhong".equals(key)) {
								json_map.put("label", "高中以下");
								mapChart1.put("gaozhong", val);
							}
						}else if ("se_gwlb".equals(fzzb)) {
							String pValue = CacheBasePropertiesManager.getValueByPropertyKey(key);
							if (StringUtils.isNotBlank(pValue)) {
								json_map.put("label", pValue);
								mapChart1.put(key, val);
							}else {
								json_map.put("label", "未知");
								mapChart1.put("weizhi", val);
							}
						}else if ("se_age".equals(fzzb)) {
							if ("weizhi".equals(key)) {
								json_map.put("label", "未知");
								mapChart1.put("weizhi", val);
							}
							if ("24".equals(key)) {
								json_map.put("label", "24及以下");
								mapChart1.put("24", val);
							}
							if ("25".equals(key)) {
								json_map.put("label", "25-29");
								mapChart1.put("25", val);
							}
							if ("30".equals(key)) {
								json_map.put("label", "30-34");
								mapChart1.put("30", val);
							}
							if ("35".equals(key)) {
								json_map.put("label", "35-39");
								mapChart1.put("35", val);
							}
							if ("40".equals(key)) {
								json_map.put("label", "40-44");
								mapChart1.put("40", val);
							}
							if ("45".equals(key)) {
								json_map.put("label", "45-49");
								mapChart1.put("45", val);
							}
							if ("50".equals(key)) {
								json_map.put("label", "50-54");
								mapChart1.put("50", val);
							}
							if ("55".equals(key)) {
								json_map.put("label", "55-59");
								mapChart1.put("55", val);
							}
							if ("60".equals(key)) {
								json_map.put("label", "60及以上");
								mapChart1.put("60", val);
							}
						}else {
							json_map.put("label", "未知");
						}
					}
					if ("nullkey".equals(key)) {
						json_map.put("label", "未填报");
						mapChart1.put("nullkey", val);
					}
					json_map.put("value", val);
					mapChartJson.put(key, json_map);
					//json_list.add(json_map);
				}
			}
			//json数据排序封装
			if (!mapChartJson.isEmpty()) {
				if ("se_cxfl".equals(fzzb)){
					if (MMap.validateList(cxflList)) {
						for (BaseProperties p : cxflList) {
							if (mapChartJson.containsKey(p.getPropertyKey())) {
								json_list.add(mapChartJson.get(p.getPropertyKey()));
							}
						}
					}
					if (mapChartJson.containsKey("weizhi")) {
						json_list.add(mapChartJson.get("weizhi"));
					}
					if (mapChartJson.containsKey("nullkey")) {
						json_list.add(mapChartJson.get("nullkey"));
					}
				}
				if ("se_xb".equals(fzzb)){
					if (MMap.validateList(xbList)) {
						for (BaseProperties p : xbList) {
							if (mapChartJson.containsKey(p.getPropertyKey())) {
								json_list.add(mapChartJson.get(p.getPropertyKey()));
							}
						}
					}
					if (mapChartJson.containsKey("weizhi")) {
						json_list.add(mapChartJson.get("weizhi"));
					}
					if (mapChartJson.containsKey("nullkey")) {
						json_list.add(mapChartJson.get("nullkey"));
					}
				}
				if ("se_sfzb".equals(fzzb)){
					if (MMap.validateList(sfzbList)) {
						for (BaseProperties p : sfzbList) {
							if (mapChartJson.containsKey(p.getPropertyKey())) {
								json_list.add(mapChartJson.get(p.getPropertyKey()));
							}
						}
					}
					if (mapChartJson.containsKey("weizhi")) {
						json_list.add(mapChartJson.get("weizhi"));
					}
					if (mapChartJson.containsKey("nullkey")) {
						json_list.add(mapChartJson.get("nullkey"));
					}
				}
				if ("se_xl".equals(fzzb)){
					if (mapChartJson.containsKey("boshi")) {
						json_list.add(mapChartJson.get("boshi"));
					}
					if (mapChartJson.containsKey("shuoshi")) {
						json_list.add(mapChartJson.get("shuoshi"));
					}
					if (mapChartJson.containsKey("benke")) {
						json_list.add(mapChartJson.get("benke"));
					}
					if (mapChartJson.containsKey("zhuanke")) {
						json_list.add(mapChartJson.get("zhuanke"));
					}
					if (mapChartJson.containsKey("gaozhong")) {
						json_list.add(mapChartJson.get("gaozhong"));
					}
					if (mapChartJson.containsKey("weizhi")) {
						json_list.add(mapChartJson.get("weizhi"));
					}
					if (mapChartJson.containsKey("nullkey")) {
						json_list.add(mapChartJson.get("nullkey"));
					}
				}
				if ("se_gwlb".equals(fzzb)){
					if (MMap.validateList(gwlbList)) {
						for (BaseProperties p : gwlbList) {
							if (mapChartJson.containsKey(p.getPropertyKey())) {
								json_list.add(mapChartJson.get(p.getPropertyKey()));
							}
						}
					}
					if (mapChartJson.containsKey("weizhi")) {
						json_list.add(mapChartJson.get("weizhi"));
					}
					if (mapChartJson.containsKey("nullkey")) {
						json_list.add(mapChartJson.get("nullkey"));
					}
				}
				if ("se_age".equals(fzzb)){
					if (mapChartJson.containsKey("24")) {
						json_list.add(mapChartJson.get("24"));
					}
					if (mapChartJson.containsKey("25")) {
						json_list.add(mapChartJson.get("25"));
					}
					if (mapChartJson.containsKey("30")) {
						json_list.add(mapChartJson.get("30"));
					}
					if (mapChartJson.containsKey("35")) {
						json_list.add(mapChartJson.get("35"));
					}
					if (mapChartJson.containsKey("40")) {
						json_list.add(mapChartJson.get("40"));
					}
					if (mapChartJson.containsKey("45")) {
						json_list.add(mapChartJson.get("45"));
					}
					if (mapChartJson.containsKey("50")) {
						json_list.add(mapChartJson.get("50"));
					}
					if (mapChartJson.containsKey("55")) {
						json_list.add(mapChartJson.get("55"));
					}
					if (mapChartJson.containsKey("60")) {
						json_list.add(mapChartJson.get("60"));
					}
					if (mapChartJson.containsKey("weizhi")) {
						json_list.add(mapChartJson.get("weizhi"));
					}
					if (mapChartJson.containsKey("nullkey")) {
						json_list.add(mapChartJson.get("nullkey"));
					}
				}
			}
			System.out.println(JsonUtil.listToJSON(json_list));
			request.setAttribute("chartJson", JsonUtil.listToJSON(json_list).toString());
			if (!mapAreaZhibiao.isEmpty()) {
				for (Map.Entry<String, Integer> entryChart : mapAreaZhibiao.entrySet()) {
					String key = entryChart.getKey();
					Integer val = entryChart.getValue();
					String key_pre = key.split("_")[0];
					String key_suffix = key.split("_")[1];
					if (!"nullkey".equals(key_suffix)) {
						if ("se_cxfl".equals(fzzb)) {
							String pValue = CacheBasePropertiesManager.getValueByPropertyKey(key_suffix);
							if (StringUtils.isNotBlank(pValue)) {
								mapChart2.put(key, val);
							}else {
								mapChart2.put(key_pre+"_weizhi", val);
							}
						}else if ("se_xb".equals(fzzb)) {
							String pValue = CacheBasePropertiesManager.getValueByPropertyKey(key_suffix);
							if (StringUtils.isNotBlank(pValue)) {
								mapChart2.put(key, val);
							}else {
								mapChart2.put(key_pre+"_weizhi", val);
							}
						}else if ("se_sfzb".equals(fzzb)) {//是否在编
							String pValue = CacheBasePropertiesManager.getValueByPropertyKey(key_suffix);
							if (StringUtils.isNotBlank(pValue)) {
								mapChart2.put(key, val);
							}else {
								mapChart2.put(key_pre+"_weizhi", val);
							}
						}else if ("se_xl".equals(fzzb)) {//学历
							mapChart2.put(key, val);
						}else if ("se_gwlb".equals(fzzb)) {
							String pValue = CacheBasePropertiesManager.getValueByPropertyKey(key_suffix);
							if (StringUtils.isNotBlank(pValue)) {
								mapChart2.put(key, val);
							}else {
								mapChart2.put(key_pre+"_weizhi", val);
							}
						}else if ("se_age".equals(fzzb)) {
							mapChart2.put(key, val);
						}else {
							
						}
					}
					if ("nullkey".equals(key_suffix)) {
						mapChart2.put(key_pre+"_nullkey", val);
					}
				}
			}
			
			//地区显示
			// 查询地区
			List<BaseAreaTree> areaList = CacheBaseAreaManager.getTreeByParentCode(areaCode);
			request.setAttribute("areaList", areaList);
			//request.setAttribute("mapChart", mapChart);
			request.setAttribute("mapChart1", mapChart1);
			request.setAttribute("mapChart2", mapChart2);
			//request.setAttribute("mapAreaZhibiao", mapAreaZhibiao);
			request.setAttribute("mapsArea", mapsArea);
			request.setAttribute("allCnt", allCnt);
			
		}
		return STAT_BASETEACHERINFO_YEY_JSP;		
	}
	/** 执行搜索 */
	public String list() {
		Object o=request.getSession().getAttribute("ssoUser");
		if(o!=null){
			BaseUser baseUser=(BaseUser)o;
			String tabflag = request.getParameter("tabflag");
			request.setAttribute("tabflag", tabflag);
			BaseToDo query = new BaseToDo();
			//由我发起
			if ("tab1".equals(tabflag)) {
				query.setComeFromProvinceId(baseUser.getProvinceId());
				query.setComeFromCityId(baseUser.getCityId());
				query.setComeFromCountyId(baseUser.getCountyId());
				if ("role.schoolManager".equals(baseUser.getDefaultRoleCode())) {
					query.setComeFromSchoolId(baseUser.getSchoolId());
				}
				if ("role.institutionManager".equals(baseUser.getDefaultRoleCode())) {
					query.setComeFromInstitutionId(baseUser.getInstitutionId());
				}
				query.setCreUser(baseUser.getId());
				query.setDeleteFlag(0);
				query.setTodoDealStatus(0);
			}
			//需我处理
			if ("tab2".equals(tabflag)) {
				query.setBelongToUserId(baseUser.getId());
				query.setBelongToProvinceId(baseUser.getProvinceId());
				query.setBelongToCityId(baseUser.getCityId());
				query.setBelongToCountyId(baseUser.getCountyId());
				if ("role.schoolManager".equals(baseUser.getDefaultRoleCode())) {
					baseToDo.setBelongToSchoolId(baseUser.getSchoolId());
				}
				if ("role.institutionManager".equals(baseUser.getDefaultRoleCode())) {
					baseToDo.setBelongToInstitutionId(baseUser.getInstitutionId());
				}
				query.setDeleteFlag(0);
				query.setTodoDealStatus(0);
			}
			//需我处理
			if ("tab3".equals(tabflag)) {
				query.setBelongToUserId(baseUser.getId());
				query.setBelongToProvinceId(baseUser.getProvinceId());
				query.setBelongToCityId(baseUser.getCityId());
				query.setBelongToCountyId(baseUser.getCountyId());
				if ("role.schoolManager".equals(baseUser.getDefaultRoleCode())) {
					baseToDo.setBelongToSchoolId(baseUser.getSchoolId());
				}
				if ("role.institutionManager".equals(baseUser.getDefaultRoleCode())) {
					baseToDo.setBelongToInstitutionId(baseUser.getInstitutionId());
				}
				query.setDeleteFlag(0);
				query.setTodoDealStatus(1);
			}
			int pageNo=1;
			if(request.getParameter("pageNo")!=null&&!request.getParameter("pageNo").equals("")){
				pageNo=Integer.valueOf(request.getParameter("pageNo"));
			}
			int pageSize=10;
			if(request.getParameter("pageSize")!=null&&!request.getParameter("pageSize").equals("")){
				pageSize=Integer.valueOf(request.getParameter("pageSize"));
			}
			List<MMap> mMapList = new ArrayList<MMap>();
			Page page = baseToDoManager.findPage(query,pageSize,pageNo);
			if (page != null) {
				List<BaseToDo> list = (List<BaseToDo>) page.getResult();
				if (MMap.validateList(list)) {
					for (BaseToDo _baseToDo : list) {
						MMap mMap = new MMap();
						mMap.setObj(_baseToDo);
						mMap.setObj1(My431Function.getValueByCode(_baseToDo.getTodoType()));
						BaseUser sourceUser = baseUserManager.get(_baseToDo.getCreUser());
						mMap.setObj2(sourceUser.getRealName());
						mMapList.add(mMap);
					}
				}
			}
			request.setAttribute("mMapList", mMapList);
			PageManager pm = new PageManager((int)page.getTotalCount(), pageSize,pageSize);
			pm.goToPage(pageNo);
			request.setAttribute("dataList", page.getResult());   
			request.setAttribute("pageNo", pageNo);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("pageHtml", pm.getPageCode());
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
		baseToDoManager.save(baseToDo);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		request.setAttribute("baseToDo", baseToDo);
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		baseToDoManager.update(this.baseToDo);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		baseToDoManager.removeById(id);
		return LIST_ACTION;
	}

}
