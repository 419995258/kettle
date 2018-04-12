/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package org.my431.base.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.my431.base.model.BaseArea;
import org.my431.base.model.BaseAreaTree;
import org.my431.base.model.BaseKpiGroup;
import org.my431.base.model.BaseKpiItem;
import org.my431.base.model.BaseProperties;
import org.my431.base.model.BaseSchool;
import org.my431.base.model.BaseSchoolFsb;
import org.my431.base.model.BaseUser;
import org.my431.base.services.BaseAreaManager;
import org.my431.base.services.BaseKpiGroupManager;
import org.my431.base.services.BaseSchoolFsbManager;
import org.my431.base.services.BaseSchoolManager;
import org.my431.base.services.CacheBaseAreaManager;
import org.my431.base.services.CacheBasePropertiesManager;
import org.my431.base.services.PageManager;

import org.my431.platform.web.StrutsTreeEntityAction;
import org.my431.platform.dao.support.Page;
import org.my431.plugin.redis.services.RedisManager;
import org.my431.util.MMap;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.ModelDriven;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author wangzhen
 * @version 1.0
 * @since 1.0
 */

public class BaseKpiGroupAction extends StrutsTreeEntityAction<BaseKpiGroup, BaseKpiGroupManager> implements
  Preparable, ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null;

	// forward paths
	protected static final String QUERY_JSP = "//base/BaseKpiGroup/query.jsp";
	protected static final String LIST_JSP = "//base/BaseKpiGroup/list.jsp";
	protected static final String CREATE_JSP = "//base/BaseKpiGroup/create.jsp";
	protected static final String EDIT_JSP = "//base/BaseKpiGroup/edit.jsp";
	protected static final String SHOW_JSP = "//base/BaseKpiGroup/show.jsp";
	// redirect paths,startWith: !
	protected static final String LIST_ACTION = "!//base/BaseKpiGroup/list.jspx";

	// 单一指标统计首页
	protected static final String INDEX = "//base/SystemManager/index.jsp";
	// 单一指标统计首页具体内容
	protected static final String MAIN = "//base/SystemManager/DYZBTJ/main.jsp";
	// 单一指标统计图表显示页面
	protected static final String CHARTS_SHOW = "//base/SystemManager/DYZBTJ/charts.jsp";
	// 单一指标统计图表显示页面
	protected static final String TABLE_SHOW = "//base/SystemManager/DYZBTJ/table.jsp";
	@Autowired
	private BaseKpiGroupManager baseKpiGroupManager;

	@Autowired
	private RedisManager redisManager;
	
	@Autowired
	private BaseAreaManager baseAreaManager;
	
	@Autowired
	private BaseSchoolFsbManager baseSchoolFsbManager;
	
	@Autowired
	private BaseSchoolManager baseSchoolManager;

	private BaseKpiGroup baseKpiGroup;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			baseKpiGroup = new BaseKpiGroup();
		} else {
			baseKpiGroup = (BaseKpiGroup) baseKpiGroupManager.get(id);
		}
	}

	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseKpiGroupManager(BaseKpiGroupManager manager) {
		this.baseKpiGroupManager = manager;
	}

	public Object getModel() {
		return baseKpiGroup;
	}

	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}

	/** 执行搜索 */
	public String list() {
		BaseKpiGroup query = new BaseKpiGroup();

		int pageNo = 1;
		if (request.getParameter("pageNo") != null
				&& !request.getParameter("pageNo").equals("")) {
			pageNo = Integer.valueOf(request.getParameter("pageNo"));
		}
		int pageSize = 10;
		if (request.getParameter("pageSize") != null
				&& !request.getParameter("pageSize").equals("")) {
			pageSize = Integer.valueOf(request.getParameter("pageSize"));
		}
		Page page = baseKpiGroupManager.findPage(query, pageSize, pageNo);
		PageManager pm = new PageManager((int) page.getTotalCount(), pageSize,
				pageSize);
		pm.goToPage(pageNo);
		request.setAttribute("dataList", page.getResult());
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageHtml", pm.getPageCode());
		return LIST_JSP;
	}

	/** 查看对象 */
	public String show() {
		return SHOW_JSP;
	}

	/** 进入新增页面 */
	public String create() {
		return CREATE_JSP;
	}

	/** 保存新增对象 */
	public String save() {
		baseKpiGroupManager.save(baseKpiGroup);
		return LIST_ACTION;
	}

	/** 进入更新页面 */
	public String edit() {
		request.setAttribute("baseKpiGroup", baseKpiGroup);
		return EDIT_JSP;
	}

	/** 保存更新对象 */
	public String update() {
		baseKpiGroupManager.update(this.baseKpiGroup);
		return LIST_ACTION;
	}

	/** 删除对象 */
	public String delete() {
		baseKpiGroupManager.removeById(id);
		return LIST_ACTION;
	}

	/**
	 * 显示首页
	 * 
	 * @return
	 */
	public String index() {
		request.setAttribute("menuFlag", "xfdj");
		request.setAttribute("menuFlag_1", "dyzbtj");
		return INDEX;
	}

	/**
	 * 显示内容页面
	 * 
	 * @return
	 */
	public String main() {
		
		return MAIN;
	}

	/**
	 * 显示图表的搜索页面
	 * 
	 * @return
	 */
	public String showGroup() {
		return CHARTS_SHOW;
	}
	
	public String showTable() {
		BaseUser baseUser = (BaseUser) request.getSession().getAttribute(
				"ssoUser");
		//地区id
		String areaId =request.getParameter("areaId");
		//地区code
		String areaCode =request.getParameter("areaCode");
		if(areaCode == null){
			areaCode = "062";
		}
		//所有的子项item
		String allItemChild = request.getParameter("allItemChild");
		//所有的子项item选择的
		String itemChildSelected = request.getParameter("itemChildSelected");
		// 筛选条件的处理
		String[] CXFL = request.getParameterValues("CXFL");
		String[] XB = request.getParameterValues("XB");
		String[] SFZB = request.getParameterValues("SFZB");
		String[] ZGXL = request.getParameterValues("ZGXL");
		String[] XRGWLB = request.getParameterValues("XRGWLB");
		String[] AGE = request.getParameterValues("AGE");
		String[] SSXD = request.getParameterValues("SSXD");
		String CXFLStr = null;
		String XBStr = null;
		String SFZBStr = null;
		String ZGXLStr = null;
		String XRGWLBStr = null;
		String AGEStr = null;
		String SSXDStr = null;

		// 表名
		String groupName = request.getParameter("groupName");
		// 列名
		String itemName = request.getParameter("itemName");
		// 查询表数据有效的条件
		String valideItem = request.getParameter("valideItem");
		// 判断教师id的查询条件是JSID还是ID
		String JSID = "JSID";
		//是否是枚举值
		String kpisfenum = request.getParameter("kpisfenum");
		// 获取得到的身份信息，是否查询下一级子集的依据,通过areaCOde的位数判断
		Map<Object, MMap> mapGet = null;
		if (StringUtils.isNotBlank(groupName)
				&& StringUtils.isNotBlank(itemName)
				 && StringUtils.isNotBlank(allItemChild)) {
			// 如果是教职工基本信息表，jsid需要改成id
			if ("TB_BIZ_JZGJBXX".equals(groupName)) {
				JSID = "ID";
			}
			// map 查询条件
			Map<String, Object> map = new HashMap<String, Object>();
			//TODO 上下文搜索条件
			// cxfl的数据处理
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
			// XB的数据处理
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
			// SFZB 的数据处理
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
			// ZGXL 的数据处理
			//学历
			//初中（国家推行9年义务教育，所以小学不算学历，视同文盲），高中（包括：高中，职高，中专，技校），大专（大学学专科），大本（大学本科），硕士（硕士研究生），博士（博士研究生）
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
			// XRGWLB 的数据处理
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
			// AGE 的数据处理
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
			//所属学段
			sbGet = new StringBuilder("( 1=2 ");
			if (null != SSXD && SSXD.length > 0) {
				for (int i = 0; i < SSXD.length; i++) {
					if ("all".equals(SSXD[i])) {
						break;
					}
					sbGet.append(" or ");
					sbGet.append("tt.SSXD = ");
					sbGet.append("'");
					sbGet.append(SSXD[i]);
					sbGet.append("'");
				}
			}
			sbGet.append(")");
			SSXDStr = sbGet.toString();
			if(!"( 1=2 )".equals(CXFLStr)){
				map.put("SSXD", SSXDStr);
			}
			map.put("groupName", groupName );
			map.put("itemName", itemName );
			map.put("valideItem", valideItem  );
			map.put("JSID", JSID  );
			map.put("provinceId", "b45ec3ec-19db-41f1-9656-d62a4a9b6a81");
			BaseArea baseArea = new BaseArea();
			String shangJiAreaId = null;
			String shangJiAreaName = null;
			if(StringUtils.isNotBlank(areaId)){
				areaCode = CacheBaseAreaManager.getNodeById(areaId).getAreaCode();
				request.setAttribute("areaName", CacheBaseAreaManager.getNodeById(areaId).getAreaName());
				if (areaCode.length() == 3) {
					//省
					map.put("areaId", "CITY_ID");
				}
				if (areaCode.length() == 6) {
					//市
					map.put("areaId", "COUNTY_ID");
					map.put("areaIdGet", "TT.CITY_ID='"+ areaId +"'");
				}
				if (areaCode.length() == 9) {
					//县
					map.put("areaId", "COUNTY_ID");
					map.put("areaIdGet", "TT.COUNTY_ID='"+ areaId +"'");
				}
			}else {
				//没有地区id，判断登录身份是省市县哪个权限
				String roleCode = baseUser.getDefaultRoleCode();
				if("role.provinceManager".equals(roleCode)){
					map.put("areaId","CITY_ID");
					baseArea = CacheBaseAreaManager.getNodeById(baseUser.getProvinceId());
				}else if("role.cityManager".equals(roleCode)){
					map.put("areaId", "COUNTY_ID");
					map.put("areaIdGet", "TT.CITY_ID='"+ baseUser.getCityId() +"'");
					baseArea = CacheBaseAreaManager.getNodeById(baseUser.getCityId());
				}
				else if("role.countryManager".equals(roleCode)){
					map.put("areaId", "COUNTY_ID");
					map.put("areaIdGet", "TT.COUNTY_ID='"+ baseUser.getCountyId() +"'");
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
			map.put("allItemChild",allItemChild);
			map.put("kpisfenum", kpisfenum);
			//map.put("itemChildSelected",itemChildSelected);
			mapGet = baseKpiGroupManager.getAreaTeacherCount(map);
		}
		//所有的数据
		request.setAttribute("mapGet", mapGet);
		
		//所有的地区数据
		List<BaseAreaTree> areaList = CacheBaseAreaManager.getTreeByParentCode(areaCode);
		if(areaList == null){
			areaList = new ArrayList<BaseAreaTree>();
			BaseArea baseArea = CacheBaseAreaManager.getNodeByCode(areaCode);
			BaseAreaTree baseAreaTree = new BaseAreaTree();
			baseAreaTree.setId(baseArea.getId());
			baseAreaTree.setNodeCode(baseArea.getAreaCode());
			baseAreaTree.setNodeName(baseArea.getAreaName());
			areaList.add(baseAreaTree);
		}
		request.setAttribute("areaList", areaList);
		//选择的item
		String[] itemChildSelectedlist = itemChildSelected.split(",");
		request.setAttribute("itemChildSelectedlist", itemChildSelectedlist);
		return TABLE_SHOW;
	}

	/**
	 * 缓存通过kpiGroup的name获取该所有的kpiItem
	 * 
	 * @param step
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getKpiItemListRedis() {
		String name = request.getParameter("name");
		List<BaseKpiItem> list = new ArrayList<BaseKpiItem>();
		list = (List<BaseKpiItem>) redisManager.getOValue("baseKpiItem" + name);
		if (null == list || list.size() < 1) {
			baseKpiGroupManager.init();
			list = (List<BaseKpiItem>) redisManager.getOValue("baseKpiItem"
					+ name);
		}
		JSONArray jsonArray = new JSONArray();
		jsonArray = JSONArray.parseArray(JSON.toJSONString(list));

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(jsonArray);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}

		return null;

	}
	
	
	/**
	 * 获取kpi的属性列表
	 * 
	 * @return
	 */
	public String getKpiItemChildList() {
		String groupName = request.getParameter("groupName");
		String itemName = request.getParameter("itemName");
		JSONObject json = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		if (StringUtils.isBlank(groupName) || StringUtils.isBlank(itemName)) {
			json.put("success", false);
			json.put("message", "传输的数据不能为空！");
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("groupName", groupName);
			map.put("itemName", itemName);
			List list = baseKpiGroupManager.getKpiItemChildList(map);
			jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
			System.out.println(list);
			json.put("list", jsonArray);
			json.put("success", true);
			json.put("message", "ok！");
		}

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(json);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取图表所需要产生的数据
	 * 
	 * @return
	 */
	public String getCharts() {
		BaseUser baseUser = (BaseUser) request.getSession().getAttribute(
				"ssoUser");
		// 筛选条件的处理
		String[] CXFL = request.getParameterValues("CXFL");
		String[] XB = request.getParameterValues("XB");
		String[] SFZB = request.getParameterValues("SFZB");
		String[] ZGXL = request.getParameterValues("ZGXL");
		String[] XRGWLB = request.getParameterValues("XRGWLB");
		String[] AGE = request.getParameterValues("AGE");
		String[] SSXD = request.getParameterValues("SSXD");
		String CXFLStr = null;
		String XBStr = null;
		String SFZBStr = null;
		String ZGXLStr = null;
		String XRGWLBStr = null;
		String AGEStr = null;
		String SSXDStr = null;
		String areaId = request.getParameter("areaId");
		String areaCode =request.getParameter("areaCode");
		if(areaCode == null){
			areaCode = "062";
		}
		
		// 表名,cn为中文名
		String groupName = request.getParameter("groupName");
		// 列名,cn为中文名
		String itemName = request.getParameter("itemName");
		// 查询表数据有效的条件
		String valideItem = request.getParameter("valideItem");
		// 选择的属性的list的str格式,cn为中文名
		String itemChildStr = request.getParameter("itemChildStr");
		String groupNameCn = request.getParameter("groupNameCn");
		String itemNameCn = request.getParameter("itemNameCn");
		String itemChildCnStr = request.getParameter("itemChildCnStr");
		// 判断教师id的查询条件是JSID还是ID
		String JSID = "JSID";
		

		// 获取得到的身份信息，是否查询下一级子集的依据
		/*String provinceId = request.getParameter("provinceId");
		String cityId = request.getParameter("cityId");
		String countyId = request.getParameter("countyId");
		if (StringUtils.isNotBlank(provinceId)
				&& StringUtils.isNotBlank(cityId)
				&& StringUtils.isNotBlank(countyId)) {
			baseUser.setProvinceId(provinceId);
			baseUser.setCityId(cityId);
			baseUser.setCountyId(countyId);
		}*/
		JSONObject json = new JSONObject();
		
			if (StringUtils.isNotBlank(groupName)
					&& StringUtils.isNotBlank(itemName)
					&& StringUtils.isNotBlank(itemChildStr)) {
				// 如果是教职工基本信息表，jsid需要改成id
				if ("TB_BIZ_JZGJBXX".equals(groupName)) {
					JSID = "ID";
				}

				String[] itemChild = itemChildStr.split(",");
				String[] itemChildCn = itemChildCnStr.split(",");
				JSONObject data = null;
				JSONArray dataList = new JSONArray();
				JSONArray categoryList = new JSONArray();
				JSONArray textList = new JSONArray();
				JSONArray categories = new JSONArray();
				JSONArray dataset = new JSONArray();

				// 三个统计的人数
				BigDecimal allCount = null;
				BigDecimal tPeopleCount = null;
				BigDecimal mPeopleCount = null;
				// 目标查询表的有效数据总数
				BigDecimal tableCount = null;
				
				// map 查询条件、maoObj查询到的结果
				Map<String, Object> map = new HashMap<String, Object>();
				Map<String, Object> mapObj = new HashMap<String, Object>();
				
				//地区搜索限制
				//先判断是不是学校
				if("role.schoolManager".equals(baseUser.getDefaultRoleCode())){
					map.put("schoolManager", true);
					json.put("schoolManager", true);
					String isFSB = MMap.isnullStr(request.getSession().getAttribute("isFSB"));
					BaseSchool baseSchool = new BaseSchool();
					String schoolId = null;
					if ("1".equals(isFSB)) {
						BaseSchoolFsb baseSchoolFsb = baseSchoolFsbManager.get(baseUser.getSchoolId());
						baseSchool = baseSchoolFsb.cloneTo(BaseSchool.class);
					}else {
						baseSchool = baseSchoolManager.get(baseUser.getSchoolId());
					}
					schoolId = baseSchool.getId();
					map.put("schoolId", schoolId);
				}else{
					map.put("schoolManager", false);
					json.put("schoolManager", false);
				}
				BaseUser baus = new BaseUser();
				if(StringUtils.isNotBlank(areaId)){
					areaCode = CacheBaseAreaManager.getNodeById(areaId).getAreaCode();
					if (areaCode.length() == 3) {
						//省
						baus.setProvinceId(areaId);
					}
					if (areaCode.length() == 6) {
						//市
						baus.setCityId(areaId);
					}
					if (areaCode.length() == 9) {
						//县
						baus.setCountyId(areaId);
					}
				}else {
					//没有地区id，判断登录身份是省市县哪个权限
					/*String roleCode = baseUser.getDefaultRoleCode();
					if("role.provinceManager".equals(roleCode)){
						map.put("provinceId", areaId);
					}else if("role.cityManager".equals(roleCode)){
						map.put("cityId", areaId);
					}
					else if("role.countryManager".equals(roleCode)){
						map.put("countyId", areaId);
					}*/
					baus = baseUser;	
				}
				
				//TODO 上下文搜索条件
				// cxfl的数据处理
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
							sbGet.append(" or CXFL not in("+str+") ");
						} else {
							sbGet.append(" or ");
							sbGet.append("CXFL = ");
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
				// XB的数据处理
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
							sbGet.append(" or XB not in("+str+") ");
						} else {
							sbGet.append(" or ");
							sbGet.append("XB = ");
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
				// SFZB 的数据处理
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
							sbGet.append(" or SFZB not in("+str+") ");							
						}else if ("wtb".equals(SFZB[i])) {
							sbGet.append(" or TT.SFZB is null ");							
						} else {
							sbGet.append(" or ");
							sbGet.append("SFZB  = ");
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
				// ZGXL 的数据处理
				//学历
				//初中（国家推行9年义务教育，所以小学不算学历，视同文盲），高中（包括：高中，职高，中专，技校），大专（大学学专科），大本（大学本科），硕士（硕士研究生），博士（博士研究生）
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
							sbGet.append(" or ZGXL not in("+str+") ");
						} else if("bs".equals(ZGXL[i])){
							sbGet.append(" or ");
							sbGet.append("ZGXL in('XL@GJ@11','XL@GJ@12','XL@GJ@13') ");
						}else if("ss".equals(ZGXL[i])){
							sbGet.append(" or ");
							sbGet.append("ZGXL in('XL@GJ@14','XL@GJ@15','XL@GJ@16','XL@GJ@17','XL@GJ@18','XL@GJ@19') ");
						}else if("bk".equals(ZGXL[i])){
							sbGet.append(" or ");
							sbGet.append("ZGXL in('XL@GJ@21','XL@GJ@22','XL@GJ@23','XL@GJ@28') ");
						}else if("gz".equals(ZGXL[i])){
							sbGet.append(" or ");
							sbGet.append("ZGXL in('XL@GJ@34','XL@GJ@35','XL@GJ@91','XL@GJ@92','XL@GJ@93','XL@GJ@41','XL@GJ@42','XL@GJ@43','XL@GJ@44','XL@GJ@45','XL@GJ@46','XL@GJ@47','XL@GJ@48','XL@GJ@49','XL@GJ@61','XL@GJ@62','XL@GJ@63','XL@GJ@71','XL@GJ@73','XL@GJ@81','XL@GJ@83','XL@GJ@99','XL@GJ@0') ");
						}
					}
				}
				sbGet.append(")");
				ZGXLStr = sbGet.toString();
				if(!"( 1=2 )".equals(ZGXLStr)){
					map.put("ZGXL", ZGXLStr);
				}
				// XRGWLB 的数据处理
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
							sbGet.append(" or XRGWLB not in("+str+") ");
						} else {
							sbGet.append(" or ");
							sbGet.append("XRGWLB  = ");
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
				// AGE 的数据处理
				sbGet = new StringBuilder("( 1=2 ");
				if (null != AGE   && AGE.length > 0) {
					for (int i = 0; i < AGE.length; i++) {
						if ("all".equals(AGE[i])) {
							break;
						}
						
						if("24".equals(AGE[i])){
							sbGet.append(" or AGE  between 0 and 24 ");
						}else if("29".equals(AGE[i])){
							sbGet.append(" or AGE between 25 and 29 ");
						}else if("34".equals(AGE[i])){
							sbGet.append(" or AGE between 30 and 34 ");
						}else if("39".equals(AGE[i])){
							sbGet.append(" or AGE  between 35 and 39 ");
						}else if("44".equals(AGE[i])){
							sbGet.append(" or AGE  between 40 and 44 ");
						}else if("49".equals(AGE[i])){
							sbGet.append(" or AGE  between 45 and 49 ");
						}else if("54".equals(AGE[i])){
							sbGet.append(" or AGE  between 50 and 54 ");
						}else if("59".equals(AGE[i])){
							sbGet.append(" or AGE  between 55 and 59 ");
						}else if("60".equals(AGE[i])){
							sbGet.append(" or AGE  >= 60");
						}
					}
				}
				sbGet.append(")");
				AGEStr = sbGet.toString();
				if(!"( 1=2 )".equals(AGEStr)){
					map.put("AGEStr", AGEStr);
				}
				//所属学段
				// ssxd的数据处理
				sbGet = new StringBuilder("( 1=2 ");
				if (null != SSXD && SSXD.length > 0) {
					for (int i = 0; i < SSXD.length; i++) {
						if ("all".equals(SSXD[i])) {
							break;
						}
						sbGet.append(" or ");
						sbGet.append("SSXD = ");
						sbGet.append("'");
						sbGet.append(SSXD[i]);
						sbGet.append("'");
					}
				}
				sbGet.append(")");
				SSXDStr = sbGet.toString();
				if(!"( 1=2 )".equals(CXFLStr)){
					map.put("SSXD", SSXDStr);
				}
				/*if(StringUtils.isNotBlank(SSXD)){
					map.put("SSXD", SSXD);
				}*/
				// 查询所有老师的数量
				//map.put("table", "TB_BIZ_JZGJBXX");
				map.put("baus", baus);
				mapObj = baseKpiGroupManager.getTeacherCount(map);
				allCount = (BigDecimal) mapObj.get("allCount");
				tPeopleCount = (BigDecimal) mapObj.get("tPeopleCount");
				mPeopleCount = (BigDecimal) mapObj.get("mPeopleCount");
				if(allCount == null){
					allCount = new BigDecimal("0");
				}
				if(tPeopleCount == null){
					tPeopleCount = new BigDecimal("0");
				}
				if(mPeopleCount == null){
					mPeopleCount = new BigDecimal("0");
				}

				// 查询该表所有的申请记录总数
				map = new HashMap<String, Object>();
				map.put("JSID", JSID);
				map.put("table", groupName);
				if (StringUtils.isNotBlank(valideItem)) {
					map.put("valideItem", valideItem);
				}
				if("role.schoolManager".equals(baseUser.getDefaultRoleCode())){
					map.put("schoolManager", true);
					json.put("schoolManager", true);
					String isFSB = MMap.isnullStr(request.getSession().getAttribute("isFSB"));
					BaseSchool baseSchool = new BaseSchool();
					String schoolId = null;
					if ("1".equals(isFSB)) {
						BaseSchoolFsb baseSchoolFsb = baseSchoolFsbManager.get(baseUser.getSchoolId());
						baseSchool = baseSchoolFsb.cloneTo(BaseSchool.class);
					}else {
						baseSchool = baseSchoolManager.get(baseUser.getSchoolId());
					}
					schoolId = baseSchool.getId();
					map.put("schoolId", schoolId);
				}else{
					map.put("schoolManager", false);
					json.put("schoolManager", false);
				}
				map.put("baus", baus);
				//TODO 上下文搜索条件
				if(!"( 1=2 )".equals(CXFLStr)){
					map.put("CXFL", CXFLStr);
				}
				if(!"( 1=2 )".equals(XBStr)){
					map.put("XB", XBStr);
				}
				if(!"( 1=2 )".equals(SFZBStr)){
					map.put("SFZB", SFZBStr);
				}
				if(!"( 1=2 )".equals(ZGXLStr)){
					map.put("ZGXL", ZGXLStr);
				}
				if(!"( 1=2 )".equals(XRGWLBStr)){
					map.put("XRGWLB", XRGWLBStr);
				}
				if(!"( 1=2 )".equals(AGEStr)){
					map.put("AGEStr", AGEStr);
				}
				if(!"( 1=2 )".equals(CXFLStr)){
					map.put("SSXD", SSXDStr);
				}
/*				if(StringUtils.isNotBlank(SSXD)){
					map.put("SSXD", SSXD);
				}*/
				tableCount = baseKpiGroupManager.getTeacherCountOne(map);
				json.put("tableCount", tableCount);
				// 设置json的数据
				data = new JSONObject();
				data.put("label", "教职工总数");
				categoryList = new JSONArray();
				categoryList.add(data);
				
				data = new JSONObject();
				data.put("value", allCount);
				data.put("color", "#66ccff");
				data.put("toolText",
						"<b>$label</b></br>$value 人</br>占总人数的：100 %");
				dataList.add(data);
				// 设置json的数据
				data = new JSONObject();
				data.put("label", "教师岗位");
				categoryList.add(data);
				
				data = new JSONObject();
				data.put("value", tPeopleCount);
				data.put("color", "#66ccff");
				if(!allCount.equals(new BigDecimal("0"))){
					data.put(
							"toolText",
							"<b>$label</b></br>$value 人</br>占总人数的："
									+ tPeopleCount
									.multiply(new BigDecimal("100"))
									.divide(allCount, 3,
											BigDecimal.ROUND_HALF_DOWN)
											.toString() + "%");
				}else{
					data.put(
							"toolText",
							"<b>$label</b></br>$value 人</br>占总人数的：100%");
				}
				dataList.add(data);
				// 设置json的数据
				data = new JSONObject();
				data.put("label", "管理岗位");
				categoryList.add(data);
				
				data = new JSONObject();
				data.put("value", mPeopleCount);
				data.put("color", "#66ccff");
				if(!allCount.equals(new BigDecimal("0"))){
					data.put(
							"toolText",
							"<b>$label</b></br>$value 人</br>占总人数的："
									+ mPeopleCount
									.multiply(new BigDecimal("100"))
									.divide(allCount, 3,
											BigDecimal.ROUND_HALF_DOWN)
											.toString() + "%");
				}else{
					data.put(
							"toolText",
							"<b>$label</b></br>$value 人</br>占总人数的：100%");
				}
				dataList.add(data);

				// TODO 用户自己选择的类型的数据
				BigDecimal qPeopleCount = new BigDecimal("0"); // 按条件查询的人数
				for (int i = 0; i < itemChild.length; i++) {
					/*if("未填报".equals(itemChild[i])){
						continue;
					}*/
					map = new HashMap<String, Object>();
					map.put("JSID", JSID);
					map.put("table", groupName);
					if (StringUtils.isNotBlank(valideItem)) {
						map.put("valideItem", valideItem);
					}
					if("role.schoolManager".equals(baseUser.getDefaultRoleCode())){
						map.put("schoolManager", true);
						json.put("schoolManager", true);
						String isFSB = MMap.isnullStr(request.getSession().getAttribute("isFSB"));
						BaseSchool baseSchool = new BaseSchool();
						String schoolId = null;
						if ("1".equals(isFSB)) {
							BaseSchoolFsb baseSchoolFsb = baseSchoolFsbManager.get(baseUser.getSchoolId());
							baseSchool = baseSchoolFsb.cloneTo(BaseSchool.class);
						}else {
							baseSchool = baseSchoolManager.get(baseUser.getSchoolId());
						}
						schoolId = baseSchool.getId();
						map.put("schoolId", schoolId);
					}else{
						map.put("schoolManager", false);
						json.put("schoolManager", false);
					}
					map.put("baus", baus);
					//TODO 上下文搜索条件
					if(!"( 1=2 )".equals(CXFLStr)){
						map.put("CXFL", CXFLStr);
					}
					if(!"( 1=2 )".equals(XBStr)){
						map.put("XB", XBStr);
					}
					if(!"( 1=2 )".equals(SFZBStr)){
						map.put("SFZB", SFZBStr);
					}
					if(!"( 1=2 )".equals(ZGXLStr)){
						map.put("ZGXL", ZGXLStr);
					}
					if(!"( 1=2 )".equals(XRGWLBStr)){
						map.put("XRGWLB", XRGWLBStr);
					}
					if(!"( 1=2 )".equals(AGEStr)){
						map.put("AGEStr", AGEStr);
					}
					if(!"( 1=2 )".equals(CXFLStr)){
						map.put("SSXD", SSXDStr);
					}
					/*if(StringUtils.isNotBlank(SSXD)){
						map.put("SSXD", SSXD);
					}*/
					StringBuilder sb = new StringBuilder(" and " + itemName);
					if (("未填报").equals(itemChild[i])) {
						//sb = sb.append(" is null");
					} 
					else if (("未知").equals(itemChild[i])) {
						sb = sb.append(" not in (SELECT DISTINCT ZDXBS from TB_CFG_ZDXB ) or " + itemName + " is null");
					}else if (("已填报").equals(itemChild[i])) {
						sb = sb.append(" is not null");
					}  else {
						sb = sb.append(" = '" + itemChild[i] + "'");
					}
					if("未填报".equals(itemChild[i])){
						qPeopleCount = baseKpiGroupManager.getTeacherCountOneWTB(map);
					}else if( "已填报".equals(itemChild[i])){
						//map.put("itemChild", sb.toString());
						qPeopleCount = baseKpiGroupManager.getTeacherCountOne(map);
					}else{
						map.put("itemChild", sb.toString());
						qPeopleCount = baseKpiGroupManager.getTeacherCountOne(map);
					}
					data = new JSONObject();
					JSONObject data2 = new JSONObject();
					data.put("label", itemChildCn[i]);
					categoryList.add(data);
					
					data = new JSONObject();
					data.put("value", qPeopleCount);
					// 占教职工比的百分比
					String countPer = null;
					if(!allCount.equals(new BigDecimal("0"))){
						countPer = qPeopleCount
								.multiply(new BigDecimal("100"))
								.divide(allCount, 3, BigDecimal.ROUND_HALF_DOWN)
								.toString();
					}else{
						countPer = "100";
					}
					data.put("countPer", countPer);
					// 占搜索数量的百分比
					String tableCountPer = null;
					if(!tableCount.equals(new BigDecimal("0"))){
						tableCountPer = qPeopleCount
								.multiply(new BigDecimal("100"))
								.divide(tableCount, 3, BigDecimal.ROUND_HALF_DOWN)
								.toString();
					}else{
						tableCountPer = "100";
					}
					data.put("countPer", tableCountPer);
					data.put("toolText",
							"<b>$label</b></br>$value 人</br>占总人数的： " + countPer
									+ "%");
					dataList.add(data);

					data2.put("label", itemChildCn[i]);
					data2.put("value", qPeopleCount);
					data2.put("countPer", countPer);
					data2.put("tableCountPer", tableCountPer);
					textList.add(data2);
					/*
					 * String str = JSON.toJSONString(data,SerializerFeature.
					 * DisableCircularReferenceDetect); JSONObject j =
					 * JSON.parseObject(str); textList.add(j);
					 */
				}

				data = new JSONObject();
				data.put("category", categoryList);
				categories.add(data);
				
				data = new JSONObject();
				data.put("data", dataList);
				dataset.add(data);
				
				json.put("success", true);
				json.put("message", "ok");
				json.put("groupName", groupNameCn);
				json.put("itemName", itemNameCn);
				json.put("categories", categories);
				json.put("dataset", dataset);
				json.put("textList", textList);
			} else {
				json.put("success", false);
				json.put("message", "数据为空");
			}

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(json);
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
