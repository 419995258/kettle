/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package org.my431.base.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.my431.base.model.BaseAreaTree;
import org.my431.base.model.BaseCreditProject;
import org.my431.base.model.BaseProTeacherMap;
import org.my431.base.model.BaseProperties;
import org.my431.base.model.BaseSchool;
import org.my431.base.model.BaseUser;
import org.my431.base.services.BaseAreaManager;
import org.my431.base.services.BaseCreditProjectManager;
import org.my431.base.services.BaseModLogManager;
import org.my431.base.services.BaseProTeacherMapManager;
import org.my431.base.services.BaseSchoolManager;
import org.my431.base.services.BaseUserManager;
import org.my431.base.services.CacheBaseAreaManager;
import org.my431.base.services.CacheBasePropertiesManager;
import org.my431.base.services.PageManager;
import org.my431.platform.dao.support.Page;
import org.my431.platform.web.StrutsTreeEntityAction;
import org.my431.plugin.redis.services.RedisManager;
import org.my431.util.GeneralTreeNode;
import org.my431.util.HibernateToolsUtil;
import org.my431.util.MMap;
import org.my431.util.RandomGuid;
import org.my431.util.excel.ReadExcel;
import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author wangzhen
 * @version 1.0
 * @since 1.0
 */


public class BaseProTeacherMapAction extends StrutsTreeEntityAction<BaseProTeacherMap,BaseProTeacherMapManager> implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "//base/BaseProTeacherMap/query.jsp";
	protected static final String LIST_JSP= "//base/BaseProTeacherMap/list.jsp";
	protected static final String CREATE_JSP = "//base/BaseProTeacherMap/create.jsp";
	protected static final String EDIT_JSP = "//base/BaseProTeacherMap/edit.jsp";
	protected static final String SHOW_JSP = "//base/BaseProTeacherMap/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!//base/BaseProTeacherMap/list.jspx";
	protected static final String INDEX_JSP="//base/BaseProTeacherMap/index.jsp";
	protected static final String QRCJ_JSP="//base/BaseProTeacherMap/qrcj.jsp";
	protected static final String XZ_BL_JSP="//base/BaseProTeacherMap/xz_bl.jsp";
	protected static final String SEARCH_PROJECT_JSP="//base/BaseProTeacherMap/search_project.jsp";
	protected static final String XG_BL_JSP="//base/BaseProTeacherMap/xg_bl.jsp";
	//校本项目查看教师报名
	protected static final String PRO_TEACHER_DETAIL_JSP="//base/BaseProTeacherMap/pro_teacher_detail.jsp";
	protected static final String EDIT_SIGN_TEACHER_JSP="//base/BaseProTeacherMap/edit_sign_teacher.jsp";
	//修改日志查看
	protected static final String MOD_LOG_TEACHER_JSP="//base/BaseModLog/mod_log_teacher.jsp";
	//花名册导入成绩
	protected static final String EXPORT_TEACHER_SCORE_JSP="//base/BaseCreditProject/export_teacher_score.jsp";
	
	protected static final String TEACHER_CONFIRM_SCORE_JSP="//base/BaseCreditProject/teacher_confirm_score.jsp";
	//教师成绩证明img
	protected static final String UPLOAD_TEACHER_CONFIRM_SCORE_IMG_JSP="//base/BaseCreditProject/upload_teacher_confirm_score_img.jsp";
	//新增教师报名
	protected static final String ADD_SIGN_UP_TEACHER_JSP="//base/BaseCreditProject/add_sign_up_teacher.jsp";
	private BaseCreditProjectManager baseCreditProjectManager;
	private RedisManager redisManager;
	private BaseProTeacherMapManager baseProTeacherMapManager;
	@Autowired
	private BaseUserManager baseUserManager;
	@Autowired
	private BaseSchoolManager baseSchoolManager;
	@Autowired
	private BaseAreaManager baseAreaManager;
	@Autowired
	private BaseModLogManager baseModLogManager;
	
	private BaseProTeacherMap baseProTeacherMap;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			baseProTeacherMap = new BaseProTeacherMap();
		} else {
			baseProTeacherMap = (BaseProTeacherMap)baseProTeacherMapManager.get(id);
		}
	}
	public void setBaseCreditProjectManager(BaseCreditProjectManager manager) {
		this.baseCreditProjectManager = manager;
	}	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setRedisManager(RedisManager manager) {
		this.redisManager = manager;
	}
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseProTeacherMapManager(BaseProTeacherMapManager manager) {
		this.baseProTeacherMapManager = manager;
	}	
	
	public Object getModel() {
		return baseProTeacherMap;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		//String returnPage="//base/BaseProTeacherMap/yhq_list.jsp";
		String type=request.getParameter("type");
		if(isNullOrEmptyString(type)){
			type="yhq";
		}
		request.setAttribute("type", type);
		int pageNo=1;
		if(request.getParameter("pageNo")!=null&&!request.getParameter("pageNo").equals("")){
			pageNo=Integer.valueOf(request.getParameter("pageNo"));
		}
		int pageSize=10;
		if(request.getParameter("pageSize")!=null&&!request.getParameter("pageSize").equals("")){
			pageSize=Integer.valueOf(request.getParameter("pageSize"));
		}
		Map<String,Object> values=new HashMap<String,Object>();
		
		Page page = this.baseProTeacherMapManager.getBaseTeacherMapPage(values, pageSize, pageNo);//new Page();//baseProTeacherMapManager.findPage(query,pageSize,pageNo);
		PageManager pm = new PageManager((int)page.getTotalCount(), pageSize,pageSize);
		pm.goToPage(pageNo);
		request.setAttribute("dataList", page.getResult());   
	    request.setAttribute("pageNo", pageNo);
	    request.setAttribute("pageSize", pageSize);
	    request.setAttribute("pageHtml", pm.getPageCode());
		return LIST_JSP;
	}
	/**
	 * 
     *类的描述:teacherList<br/>
     *功能描述 ：花名册修改名字<br/>
     *作者:hyl<br/>
     *创建日期:2017-06-27<br/>
     *修改人：<br/>
     *修改日期：<br/>
     *修改原因描述:<br/>
	 * 
	 */
	public void teacherList() {
		Object o=request.getSession().getAttribute("ssoUser");
		if(o!=null){
			BaseUser user=(BaseUser)o;
			String keyword = request.getParameter("keyword");
			
		}
		
		
	}
	
	
	
	
	/** 查看对象*/
	public String index() {
		request.setAttribute("menuFlag", "xsxf");
		return INDEX_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	public String save() {
		baseProTeacherMapManager.save(baseProTeacherMap);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		request.setAttribute("baseProTeacherMap", baseProTeacherMap);
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		baseProTeacherMapManager.update(this.baseProTeacherMap);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		baseProTeacherMapManager.removeById(id);
		return LIST_ACTION;
	}

	/**
	 * 确认成绩
	 * @author 90
	 * @date    2017-6-13
	 * @return
	 */
	public String qrCj() {
		String xfId=request.getParameter("xfId");
		String type=request.getParameter("type");
		request.setAttribute("xfId", xfId);
		request.setAttribute("type", type);
		BaseProTeacherMap bptMap= baseProTeacherMapManager.get(xfId);
		request.setAttribute("bptMap", bptMap);
		BaseCreditProject bp=baseCreditProjectManager.get(bptMap.getProId());//项目
		request.setAttribute("bp", bp);
		return QRCJ_JSP;
	}
	
	/**
	 * 成绩确认保存
	 * @author 90
	 * @date    2017-6-13
	 * @return
	 */
	public String saveQrCj() {
		String userId=(String) request.getSession().getAttribute("wsuserId");
		String xfId=request.getParameter("xfId");
		String type=request.getParameter("type");
		String hp=request.getParameter("hp");//好评
		String cp=request.getParameter("cp");//差评
		String comments=request.getParameter("comments");
		request.setAttribute("xfId", xfId);
		request.setAttribute("type", type);
		BaseProTeacherMap bptMap= baseProTeacherMapManager.get(xfId);
		bptMap.setSrdGrdCommets(comments);
		bptMap.setSrdGoodReput(Integer.valueOf(hp));
		bptMap.setSrdBadReput(Integer.valueOf(cp));
		bptMap.setResultConfUserId(userId);
		bptMap.setResultConfTime(new Date());
		bptMap.setTid(userId);//教师id
		bptMap.setResultConfFlag(1);//1表示已确认
		baseProTeacherMapManager.save(bptMap);
		BaseCreditProject bp=baseCreditProjectManager.get(bptMap.getProId());//项目
		request.setAttribute("bp", bp);
		return null;
	}
	
	/**
	 * 新增补录
	 * @author 90
	 * @date    2017-6-14
	 * @return
	 */
	public String xzBl() {
		String userId=(String) request.getSession().getAttribute("wsuserId");
		
		String type=request.getParameter("type");
		request.setAttribute("type", type);
		
		/*baseProTeacherMapManager.save(bptMap);
		BaseCreditProject bp=baseCreditProjectManager.get(bptMap.getProId());//项目*/
		return XZ_BL_JSP;
	}
	
	/**
	 * 项目查询
	 * @author 90
	 * @date    2017-6-14
	 * @return
	 */
	public String searchProject(){
		String type=request.getParameter("type");
		request.setAttribute("type", type);
		String proType=request.getParameter("proType");
		request.setAttribute("proType", proType);
		String proLevel=request.getParameter("proLevel");
		request.setAttribute("proLevel", proLevel);
		String proName=request.getParameter("proName");
		request.setAttribute("proName", proName);
		Map<String,Object> values=new HashMap<String,Object>();
		values.put("proType", proType);
		values.put("proLevel", proLevel);
		values.put("proName", proName);
		
		String xfId=request.getParameter("xfId");
		request.setAttribute("xfId", xfId);
		if(!isNullOrEmptyString(xfId)){
			BaseProTeacherMap bptMap= baseProTeacherMapManager.get(xfId);
			request.setAttribute("bptMap", bptMap);
			BaseCreditProject bp=baseCreditProjectManager.get(bptMap.getProId());//项目
			request.setAttribute("bp", bp);
		}
		int pageNo=1;
		if(request.getParameter("pageNo")!=null&&!request.getParameter("pageNo").equals("")){
			pageNo=Integer.valueOf(request.getParameter("pageNo"));
		}
		int pageSize=100000;//十万各项目够了。
		if(request.getParameter("pageSize")!=null&&!request.getParameter("pageSize").equals("")){
			pageSize=Integer.valueOf(request.getParameter("pageSize"));
		}
		Page page = this.baseCreditProjectManager.getBaseCreProjectPage(values, pageSize, pageNo);//new Page();//baseProTeacherMapManager.findPage(query,pageSize,pageNo);
		PageManager pm = new PageManager((int)page.getTotalCount(), pageSize,pageSize);
		pm.goToPage(pageNo);
		request.setAttribute("dataList", page.getResult());   
	    request.setAttribute("pageNo", pageNo);
	    request.setAttribute("pageSize", pageSize);
	    request.setAttribute("pageHtml", pm.getPageCode());
	    
	    List<BaseProperties> fundsList=CacheBasePropertiesManager.getPropertiesByGroupKey("funds_type");
	    Map<String, String> fundsMap=new HashMap<String, String>();
	    for (int i = 0; i <fundsList.size(); i++) {
	    	BaseProperties bp=fundsList.get(i);
	    	fundsMap.put(bp.getPropertyKey(), bp.getPropertyValue());
		}
	    request.setAttribute("fundsJson",  JSONObject.fromObject(fundsMap).toString()); 
		return SEARCH_PROJECT_JSP;
	}
	
	/**
	 * 新增补录的保存
	 * @author 90
	 * @date    2017-6-14
	 * @return
	 */
	public String saveBl(){

		String userId=(String) request.getSession().getAttribute("wsuserId");
		String proId=request.getParameter("proId");
		String hp=request.getParameter("hp");//好评
		String cp=request.getParameter("cp");
		String jcxf=request.getParameter("jcxf");
		String jlxf=request.getParameter("jlxf");
		String xmtr=request.getParameter("xmtr");
		String comments=request.getParameter("comments");
		BaseCreditProject bp=baseCreditProjectManager.get(proId);//项目
		BaseUser user=redisManager.getRedisUser(userId);
		BaseProTeacherMap bptMap=new BaseProTeacherMap();
		bptMap.setProId(proId);
		bptMap.setProadId("");
		bptMap.setSrdGrdCommets(comments);
		bptMap.setSrdGoodReput(Integer.valueOf(hp));
		bptMap.setSrdBadReput(Integer.valueOf(cp));
		bptMap.setResultConfUserId(userId);
		bptMap.setResultConfTime(new Date());
		bptMap.setResultConfFlag(1);//1表示已确认
		bptMap.setTproBaseCredits(Double.valueOf(jcxf));
		bptMap.setTproAwardCredits(Double.valueOf(jlxf));
		bptMap.setTproActualAmount(Double.valueOf(xmtr));
		bptMap.setIsBl("1");
		bptMap.setTid(userId);
		bptMap.setProvinceId(user.getProvinceId());
		bptMap.setCityId(user.getCityId());
		bptMap.setCountyId(user.getCountyId());
		bptMap.setTyear(bp.getTyear());
		baseProTeacherMapManager.save(bptMap);
		return null;
	}
	
	/**
	 * 修改补录
	 * @author 90
	 * @date    2017-6-15
	 * @return
	 */
	public String xgBl() {
		String userId=(String) request.getSession().getAttribute("wsuserId");
		String xfId=request.getParameter("xfId");
		String type=request.getParameter("type");
		request.setAttribute("type", type);
		request.setAttribute("xfId", xfId);
		BaseProTeacherMap bptMap=baseProTeacherMapManager.get(xfId);
		request.setAttribute("bptMap", bptMap);
		//BaseCreditProject bp=baseCreditProjectManager.get(bptMap.getProId());//项目*/
		return XG_BL_JSP;
	}
	
	public String updateBl(){
		String xfId=request.getParameter("xfId");
		
		String userId=(String) request.getSession().getAttribute("wsuserId");
		String proId=request.getParameter("proId");
		String hp=request.getParameter("hp");//好评
		String cp=request.getParameter("cp");
		String jcxf=request.getParameter("jcxf");
		String jlxf=request.getParameter("jlxf");
		String xmtr=request.getParameter("xmtr");
		String comments=request.getParameter("comments");
		BaseProTeacherMap bptMap=baseProTeacherMapManager.get(xfId);
		bptMap.setProId(proId);
		bptMap.setProadId("");
		bptMap.setSrdGrdCommets(comments);
		bptMap.setSrdGoodReput(Integer.valueOf(hp));
		bptMap.setSrdBadReput(Integer.valueOf(cp));
		bptMap.setResultConfUserId(userId);
		bptMap.setResultConfTime(new Date());
		bptMap.setResultConfFlag(1);//1表示已确认
		bptMap.setTproBaseCredits(Double.valueOf(jcxf));
		bptMap.setTproAwardCredits(Double.valueOf(jlxf));
		bptMap.setTproActualAmount(Double.valueOf(xmtr));
		bptMap.setIsBl("1");
		baseProTeacherMapManager.save(bptMap);
		return null;
	}
	/**
	 * 
	* @Description: 校本项目花名册的操作
	* @author hyl     
	* @date 2017-6-27 下午2:55:04  
	* @version V1.0 
	* @author user
	 */
	public String dofuncTeac() {
		Object o=request.getSession().getAttribute("ssoUser");
		if(o!=null){
			BaseUser baseUser=(BaseUser)o;
			String doType = request.getParameter("doType");
			String pmId = request.getParameter("pmId");//BaseProTeacherMap id
			String proId = request.getParameter("proId");//项目id
			request.setAttribute("baseUser", baseUser);
			request.setAttribute("pmId", pmId);
			request.setAttribute("proId", proId);
			if ("view".equals(doType)) {
				if (StringUtils.isNotBlank(pmId)) {
					BaseProTeacherMap baseProTeacherMap = baseProTeacherMapManager.get(pmId);
					request.setAttribute("baseProTeacherMap", baseProTeacherMap);
					if (baseProTeacherMap != null) {
						String tId = baseProTeacherMap.getTid();
						BaseUser teacherUser = baseUserManager.get(tId);
						request.setAttribute("teacherUser", teacherUser);
						if (teacherUser != null && StringUtils.isNotBlank(teacherUser.getSchoolId())) {
							BaseSchool baseSchool = baseSchoolManager.get(teacherUser.getSchoolId());
							request.setAttribute("baseSchool", baseSchool);
							MMap mMap = baseAreaManager.getAreaInfo(CacheBaseAreaManager.getNodeById(baseSchool.getCountyId()).getAreaCode());
							request.setAttribute("areaName", mMap.getObj0()+"-"+mMap.getObj3()+"-"+mMap.getObj6());
						}
						
					}
					
					return PRO_TEACHER_DETAIL_JSP;
				}
			}
			//确认成绩
			if ("confirm.score".equals(doType)) {
				if (StringUtils.isNotBlank(pmId)) {
					BaseProTeacherMap baseProTeacherMap = baseProTeacherMapManager.get(pmId);
					request.setAttribute("baseProTeacherMap", baseProTeacherMap);
					if (baseProTeacherMap != null) {
						String tId = baseProTeacherMap.getTid();
						BaseUser teacherUser = baseUserManager.get(tId);
						request.setAttribute("teacherUser", teacherUser);
					}
					//项目级别
					List<BaseProperties> level_list = CacheBasePropertiesManager.getPropertiesByGroupKey("pro.level");
					request.setAttribute("level_list", level_list);
					//项目类型
					List<BaseProperties> type_list = CacheBasePropertiesManager.getPropertiesByGroupKey("pro.type");
					request.setAttribute("type_list", type_list);
					
					//资金类型
					List<BaseProperties> funds_list = CacheBasePropertiesManager.getPropertiesByGroupKey("funds_type");
					request.setAttribute("funds_list", funds_list);
					
					//String proId = request.getParameter("proId");
					if (StringUtils.isNotBlank(proId)) {
						BaseCreditProject baseCreditProject = baseCreditProjectManager.getCacheBaseCreditProject(proId);
						request.setAttribute("baseCreditProject", baseCreditProject);
					}else {
						BaseCreditProject baseCreditProject = baseCreditProjectManager.getCacheBaseCreditProject(baseProTeacherMap.getProId());
						request.setAttribute("baseCreditProject", baseCreditProject);
					}
					return TEACHER_CONFIRM_SCORE_JSP;
				}
			}
			if ("edit".equals(doType)) {
				if (StringUtils.isNotBlank(pmId)) {
					BaseProTeacherMap baseProTeacherMap = baseProTeacherMapManager.get(pmId);
					request.setAttribute("baseProTeacherMap", baseProTeacherMap);
					if (baseProTeacherMap != null) {
						String tId = baseProTeacherMap.getTid();
						BaseUser teacherUser = baseUserManager.get(tId);
						request.setAttribute("teacherUser", teacherUser);
					}
					
					return EDIT_SIGN_TEACHER_JSP;
				}
			}
			if ("modify.log".equals(doType)) {//修改报名人日志
				int pageNo = 1;
				if(request.getParameter("pageNo")!=null&&!request.getParameter("pageNo").equals("")){
					pageNo = Integer.valueOf(request.getParameter("pageNo"));
				}
				int pageSize = 5;
				if(request.getParameter("pageSize")!=null&&!request.getParameter("pageSize").equals("")){
					pageSize = Integer.valueOf(request.getParameter("pageSize"));
				}
				Map<String, Object> termValues = new HashMap<String, Object>();
				termValues.put("sheetName", "BASE_PRO_TEACHER_MAP");
				termValues.put("delFlag", "0");
				termValues.put("fieldName", "T_ID");
				String otherQuery = " and t.prim_key in (select b.pro_map_id from BASE_PRO_TEACHER_MAP b where  b.pro_id ='"+proId+"' and b.is_bl='0' and b.del_falg='0' )";
				String orderBy = " order by t.MOD_TIME desc ";
				//List<Map<String, Object>> list = baseModLogManager.getBaseModLogList(termValues, otherQuery, orderBy);
				Page page = baseModLogManager.getPageOfBaseModLog(termValues, otherQuery, orderBy, pageSize, pageNo);
				List<MMap> mMapsList = new ArrayList<MMap>();
				if (page != null) {
					List<Map<String, Object>>  list_page = (List<Map<String, Object>>) page.getResult();
					for (Map<String, Object> map : list_page) {
						MMap mMap = new MMap();
						mMap.setObj(map);
						MMap areaMMap = baseAreaManager.getAreaInfo(CacheBaseAreaManager.getNodeById(map.get("COUNTY_ID").toString()).getAreaCode());
						mMap.setObj1(areaMMap.getObj0()+"-"+areaMMap.getObj3()+"-"+areaMMap.getObj6());
						if (map.get("SCHOOL_ID") != null && !"".equals(map.get("SCHOOL_ID").toString())) {
							mMap.setObj2(baseSchoolManager.get(map.get("SCHOOL_ID").toString()).getSchoolName());
						}
						if (map.get("FIELD_OLD_VALUE") != null && !"".equals(map.get("FIELD_OLD_VALUE").toString())) {
							mMap.setObj3(baseUserManager.getBaseUserByUserId(map.get("FIELD_OLD_VALUE").toString()).getRealName());
						}
						if (map.get("FIELD_NEW_VALUE") != null && !"".equals(map.get("FIELD_NEW_VALUE").toString())) {
							mMap.setObj4(baseUserManager.getBaseUserByUserId(map.get("FIELD_NEW_VALUE").toString()).getRealName());
						}
						mMapsList.add(mMap);
					}
					PageManager pm = new PageManager((int)page.getTotalCount(), pageSize,pageSize);
					pm.goToPage(pageNo);
					request.setAttribute("dataList", page.getResult());
					request.setAttribute("pageNo", pageNo);
					request.setAttribute("pageSize", pageSize);
					request.setAttribute("pageHtml", pm.getPageCode());
				}
				request.setAttribute("mMapsList", mMapsList);
				return MOD_LOG_TEACHER_JSP;
			}
			//花名册导入成绩
			if ("export.score".equals(doType)) {
				BaseCreditProject baseCreditProject = baseCreditProjectManager.get(proId);
				request.setAttribute("baseCreditProject", baseCreditProject);
				
				
				
				
				return EXPORT_TEACHER_SCORE_JSP;
			}
			//file.type.schoolProject.img.teacherScore
			//教师成绩证明上传
			if ("file.type.schoolProject.img.teacherScore".equals(doType)) {
				//有pmId没有proId
				
				
				
				
				return UPLOAD_TEACHER_CONFIRM_SCORE_IMG_JSP;
			}
			//新增报名
			if ("sign.up".equals(doType)) {
				//所有的市
				List<BaseAreaTree>  tree=CacheBaseAreaManager.getTreeByParentCode("062");
				request.setAttribute("cityAreaList", tree);
				
				
				
				return ADD_SIGN_UP_TEACHER_JSP;
			}
			
			if ("audit".equals(doType)) {
				
				
				return "";
			}
		}
		return "";
	}
	/**
	 * 
     *类的描述:updateTeacherMapInfo<br/>
     *功能描述 ：化名车修改报名人<br/>
     *作者:hyl<br/>
     *创建日期:2017-06-27<br/>
     *修改人：<br/>
     *修改日期：<br/>
     *修改原因描述:<br/>
	 * 
	 */
	public void updateTeacherMapInfo() {
		Object o=request.getSession().getAttribute("ssoUser");
		if(o!=null){
			BaseUser baseUser=(BaseUser)o;
			String pmId = request.getParameter("pmId");
			String teacherId = request.getParameter("teacherId");
			String bcpId = request.getParameter("bcpId");
			String resourceUrl = "";
			if (StringUtils.isNotBlank(pmId)) {
				BaseProTeacherMap baseProTeacherMap = baseProTeacherMapManager.get(pmId);
				//baseProTeacherMap.setTid(teacherId);
				//baseProTeacherMap.setTcode("123456???654321");
				resourceUrl = "/base/BaseCreditProject/viewCreditPro.jspx?bcpId="+baseProTeacherMap.getProId()+"&sflag=school_view";
				Map<String, Object> termValues = new HashMap<String, Object>();
				Map<String, Object> newValueMap = new HashMap<String, Object>();
				Map<String, Object> oldValueMap = new HashMap<String, Object>();
				
				termValues.put("PRO_MAP_ID", pmId);
				
				newValueMap.put("T_ID", teacherId);
				newValueMap.put("T_CODE", "T_"+teacherId);
				
				oldValueMap.put("T_ID", baseProTeacherMap.getTid());
				oldValueMap.put("T_CODE", baseProTeacherMap.getTcode());
				
				//待办事项提交到上级管理员进行审核
				try {
					//baseProTeacherMapManager.update(baseProTeacherMap);
					List<BaseUser> userList = baseUserManager.getUserByAreaId(baseUser.getAreaId(), "role.countryManager");
					if (MMap.validateList(userList)) {
						Integer flag = baseModLogManager.saveBaseModLog("BASE_PRO_TEACHER_MAP", pmId, HibernateToolsUtil.getTableFieldsType(BaseProTeacherMap.class), termValues, newValueMap, oldValueMap, baseUser.getId(), "modify.audit.business.01", userList.get(0).getId(), resourceUrl);
						renderHtml(response, flag+"");
					}else {
						renderHtml(response, "-3");
					}
				} catch (Exception e) {
					// TODO: handle exception
					renderHtml(response, "0");
					e.printStackTrace();
				}
			}
			
		}
	}
	/**
	 * @author hyl
	 * @since 导入学校数据
	 * */
	public void importSchoolData(String[] mUpUrls,String importTable,Integer columnSize) {
		//String uploadLoginUrl=CacheBasePropertiesManager.getValueByPropertyKey("uploadLoginUrl");
		if(mUpUrls!=null&&mUpUrls.length>0){
			for(int i=0;i<mUpUrls.length;i++){
				String sourceUrl=mUpUrls[i];
				System.out.println("导入Excel的url："+sourceUrl);
				String filename="http://localhost:8080/upload"+sourceUrl;
				int count=0;
				String str="";
				List<Object[]> datalist = null;
				try {
					datalist=ReadExcel.read(filename, "data");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(datalist!=null && datalist.size()>1){
					if (datalist.size()>3000) {
						this.renderHtml(response, "每次导入数据量最大3000条，请分批导入！");
					}else {//aaaaa
						List<Map<String, Object>> mapValueList=new ArrayList<Map<String, Object>>();
						//循环从execl中读取的数据对象
						for (int k=0; k < datalist.size(); k++) {
							Object[] objs2 = datalist.get(k);//一行的所有数据
							Map<String, Object> mapValue=new HashMap<String, Object>();
							RandomGuid u=new RandomGuid();
							String uId=u.toString();
							mapValue.put("ID",uId);
							for(int j=0;j<columnSize;j++){
								Object ostr = objs2[j];//每一行中每一格的数据
								if (ostr!=null && !"".equals(ostr)) {
									if(ostr.toString().endsWith(".0")){
										mapValue.put("X"+(j+1), ostr.toString().substring(0, ostr.toString().lastIndexOf(".0")));
									}else {
										mapValue.put("X"+(j+1), ostr.toString());
									}
								}
							}
							//第一列没有数据直接跳过
							if(mapValue.get("X1")!=null&&StringUtils.isNotBlank(mapValue.get("X1").toString())){
								mapValueList.add(mapValue);
							}
						}
						//循环完成
						if(mapValueList.size()>0){
							try {
								for (Map<String, Object> map : mapValueList) {
									
								}
								//System.out.println("----------表："+tableName+"插入数据成功，共："+(mapValueList.size())+"条--------");
								//str=str+"表："+tableName+"插入数据成功，共："+(mapValueList.size())+"条;";
								count=count+(mapValueList.size());
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							}
						}
					}//aaaaa  end
				}
					this.renderHtml(response, "成功导入"+count+"条数据;"+str);
				}
			}
	}
	/**
	 * @author hyl
	 * @since 导入学校数据
	 * */
	public void confirmScore() {
		Object o=request.getSession().getAttribute("ssoUser");
		if(o!=null){
			BaseUser baseUser=(BaseUser)o;
			String pmId = request.getParameter("pmId");
			String comment = request.getParameter("comment");
			String result = request.getParameter("result");
			if (StringUtils.isNotBlank(pmId)) {
				BaseProTeacherMap baseProTeacherMap = baseProTeacherMapManager.get(pmId);
				baseProTeacherMap.setResultConfFlag(1);//教师确认标识
				baseProTeacherMap.setResultConfTime(new Date());
				baseProTeacherMap.setResultConfUserId(baseUser.getId());
				baseProTeacherMap.setSrdGrdCommets(comment);
				if (StringUtils.isNotBlank(result)) {
					if ("0".equals(result)) {
						baseProTeacherMap.setSrdBadReput(1);
					}else {
						baseProTeacherMap.setSrdGoodReput(1);
					}
				}
				try {
					baseProTeacherMapManager.update(baseProTeacherMap);
					this.renderHtml(response, "1");
				} catch (Exception e) {
					// TODO: handle exception
					this.renderHtml(response, "0");
					e.printStackTrace();
				}
				
			}
		}
	}
}
