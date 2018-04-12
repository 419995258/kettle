/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package org.my431.base.web;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.my431.base.model.BaseCycleApproval;
import org.my431.base.model.BaseTeacherCreditsSum;
import org.my431.base.model.BaseUser;
import org.my431.base.services.BaseCycleApprovalManager;
import org.my431.base.services.BaseTeacherCreditsSumManager;
import org.my431.base.services.BaseUserMisManager;
import org.my431.base.services.PageManager;
import org.my431.platform.dao.support.Page;
import org.my431.platform.web.StrutsTreeEntityAction;
import org.my431.plugin.redis.services.RedisManager;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author wangzhen
 * @version 1.0
 * @since 1.0
 */


public class BaseCycleApprovalAction extends StrutsTreeEntityAction<BaseCycleApproval,BaseCycleApprovalManager> implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "//base/BaseCycleApproval/query.jsp";
	protected static final String LIST_JSP= "//base/BaseCycleApproval/list.jsp";
	protected static final String CREATE_JSP = "//base/BaseCycleApproval/create.jsp";
	protected static final String EDIT_JSP = "//base/BaseCycleApproval/edit.jsp";
	protected static final String SHOW_JSP = "//base/BaseCycleApproval/show.jsp";
	protected static final String GET_TEACHER_LIST_JSP="//base/BaseCycleApproval/get_teacher_list.jsp";
	protected static final String VIEW_JSP="//base/BaseCycleApproval/view.jsp";
	protected static final String SECOND_STEP_JSP="//base/BaseCycleApproval/second_step.jsp";
	protected static final String FIRST_STEP_JSP="//base/BaseCycleApproval/first_step.jsp";
	
	protected static final String GET_YSQ_LIST_JSP="//base/BaseCycleApproval/get_ysq_list.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!//base/BaseCycleApproval/list.jspx";
	private BaseUserMisManager baseUserMisManager;
	private BaseCycleApprovalManager baseCycleApprovalManager;
	private RedisManager redisManager;
	private BaseCycleApproval baseCycleApproval;
	private BaseTeacherCreditsSumManager baseTeacherCreditsSumManager;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			baseCycleApproval = new BaseCycleApproval();
		} else {
			baseCycleApproval = (BaseCycleApproval)baseCycleApprovalManager.get(id);
		}
	}
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseUserMisManager(BaseUserMisManager manager) {
		this.baseUserMisManager = manager;
	}
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseTeacherCreditsSumManager(BaseTeacherCreditsSumManager manager) {
		this.baseTeacherCreditsSumManager = manager;
	}
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseCycleApprovalManager(BaseCycleApprovalManager manager) {
		this.baseCycleApprovalManager = manager;
	}	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setRedisManager(RedisManager manager) {
		this.redisManager = manager;
	}
	public Object getModel() {
		return baseCycleApproval;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		String userId=(String) request.getSession().getAttribute("wsuserId");
		BaseUser baseUser=redisManager.getRedisUser(userId);
		int pageNo=1;
		if(request.getParameter("pageNo")!=null&&!request.getParameter("pageNo").equals("")){
			pageNo=Integer.valueOf(request.getParameter("pageNo"));
		}
		int pageSize=10;
		if(request.getParameter("pageSize")!=null&&!request.getParameter("pageSize").equals("")){
			pageSize=Integer.valueOf(request.getParameter("pageSize"));
		}
		Map<String,Object> values=new HashMap<String,Object>();
		values.put("schoolId", baseUser.getSchoolId());
		values.put("delFlag", 0);
		Page page=this.baseCycleApprovalManager.getBaseCycleApprovalPage(values, pageSize, pageNo,"school");
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
		baseCycleApprovalManager.save(baseCycleApproval);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		request.setAttribute("baseCycleApproval", baseCycleApproval);
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String update() {
		baseCycleApprovalManager.update(this.baseCycleApproval);
		return LIST_ACTION;
	}
	
	/**删除对象*/
	public String delete() {
		baseCycleApprovalManager.removeById(id);
		return LIST_ACTION;
	}
	
	/**
	 * 第一步
	 * @author 90
	 * @date    2017-6-27
	 * @return
	 */
	public String firstStep(){
		
		
		
		return FIRST_STEP_JSP;
	}
	
    public String saveFirstStep(){
    	String startYear=request.getParameter("startYear");
		String endYear=request.getParameter("endYear");
		String userId=(String) request.getSession().getAttribute("wsuserId");
		BaseCycleApproval approval=new BaseCycleApproval();
		BaseUser baseUser=redisManager.getRedisUser(userId);
		approval.setCityId(baseUser.getCityId());
		approval.setCountyId(baseUser.getCountyId());
		approval.setProvinceId(baseUser.getProvinceId());
		approval.setSchoolId(baseUser.getSchoolId());
		approval.setCreTime(new Date());
		approval.setDelFlag(0);
		approval.setStartYear(startYear);
		approval.setEndYear(endYear);
		baseCycleApprovalManager.save(approval);
		this.renderText(response, approval.getId());
		return null;
	}
    
    /**
     * 第二步
     * @author 90
     * @date    2017-6-28
     * @return
     */
    public String secondStep(){
		String hdId=request.getParameter("hdId");
		request.setAttribute("hdId", hdId);
		BaseCycleApproval approval=this.baseCycleApprovalManager.get(hdId);
		request.setAttribute("approval", approval);
		return SECOND_STEP_JSP;
	}
	/**
	 * 第二步
	 * @author 90
	 * @date    2017-6-27
	 * @return
	 */
    public String saveSecondStep(){
    	String schoolId=request.getParameter("schoolId");
    	String hdId=request.getParameter("hdId");
    	BaseCycleApproval approvalSchool=baseCycleApprovalManager.get(hdId);
    	String[] itemIds=request.getParameterValues("itemIds"); 
    	
    	if(itemIds!=null&&itemIds.length>0){
    		String teacherStr="";
    		for (int i = 0; i < itemIds.length; i++) {
				String uId=itemIds[i];
				BaseUser thisUser=redisManager.getRedisUser(uId);
				BaseCycleApproval approval=new BaseCycleApproval();
				approval.setCityId(thisUser.getCityId());
				approval.setCountyId(thisUser.getCountyId());
				approval.setProvinceId(thisUser.getProvinceId());
				approval.setSchoolId(schoolId);
				approval.setCreTime(new Date());
				approval.setDelFlag(0);
				approval.setStartYear(approvalSchool.getStartYear());
				approval.setEndYear(approvalSchool.getEndYear());
				approval.setTeacherId(uId);
				approval.setTeacherIdCode(thisUser.getTeacherNo());
				baseCycleApprovalManager.save(approval);
				if(i==0){
					teacherStr=" t.TEACHER_ID='"+uId+"'";
				}else{
					teacherStr=" or  t.TEACHER_ID='"+uId+"'";
				}
			}
    		
    		//需要更新教师的在该年份内的学分登记的核定，置上核定Id
    		
    		String sql=" update BASE_TEACHER_CREDITS_SUM t set t.CAID='"+hdId+
    				"' where t.T_YEAR>='"+approvalSchool.getStartYear()+"' AND t.T_YEAR<='"+approvalSchool.getEndYear()+
    				"' AND t.CAID IS NULL AND ("+teacherStr+")";
    		
    		this.baseCycleApprovalManager.updateSql(sql);
    	}
    	this.renderText(response, "0");
    	return null;
	}

    public String view(){
    	
    	return VIEW_JSP;
    }
    
    
    /**
     * 获得学校的老师列表
     * @author 90
     * @date    2017-6-28
     * @return
     */
    public String getTeacherList(){
    	String hdId=request.getParameter("hdId");
		request.setAttribute("hdId", hdId);
		BaseCycleApproval approvalSchool=baseCycleApprovalManager.get(hdId);
    	String schoolId=request.getParameter("schoolId");
    	String teacherName=request.getParameter("teacherName");
    	String userId=(String) request.getSession().getAttribute("wsuserId");
		request.setAttribute("schoolId", schoolId);
		int pageNo=1;
		if(request.getParameter("pageNo")!=null&&!request.getParameter("pageNo").equals("")){
			pageNo=Integer.valueOf(request.getParameter("pageNo"));
		}
		int pageSize=10;
		if(request.getParameter("pageSize")!=null&&!request.getParameter("pageSize").equals("")){
			pageSize=Integer.valueOf(request.getParameter("pageSize"));
		}
		Map<String,Object> values=new HashMap<String,Object>();
		values.put("schoolId", schoolId);
		Page page=this.baseTeacherCreditsSumManager.getHdTeacherPage(values, pageSize, pageNo, approvalSchool.getStartYear(), approvalSchool.getEndYear(),"  and t.CAID is null ");//this.baseUserMisManager.getUserList(values, pageNo, pageSize, teacherName);
		PageManager pm = new PageManager((int)page.getTotalCount(), pageSize,pageSize);
		pm.goToPage(pageNo);
		request.setAttribute("dataList", page.getResult());   
	    request.setAttribute("pageNo", pageNo);
	    request.setAttribute("pageSize", pageSize);
	    request.setAttribute("pageHtml", pm.getPageCode());
    	return GET_TEACHER_LIST_JSP;
    }
    
    /**
     * 已申请教师列表
     * @author 90
     * @date    2017-6-28
     * @return
     */
    public String getYsqList(){
    	String hdId=request.getParameter("hdId");
		request.setAttribute("hdId", hdId);
    	String schoolId=request.getParameter("schoolId");
    	String userId=(String) request.getSession().getAttribute("wsuserId");
		request.setAttribute("schoolId", schoolId);
		int pageNo=1;
		if(request.getParameter("pageNo")!=null&&!request.getParameter("pageNo").equals("")){
			pageNo=Integer.valueOf(request.getParameter("pageNo"));
		}
		int pageSize=10;
		if(request.getParameter("pageSize")!=null&&!request.getParameter("pageSize").equals("")){
			pageSize=Integer.valueOf(request.getParameter("pageSize"));
		}
		Map<String,Object> values=new HashMap<String,Object>();
		values.put("schoolId", schoolId);
		values.put("delFlag", 0);
		Page page=this.baseCycleApprovalManager.getBaseCycleApprovalPage(values, pageSize, pageNo,"teacher");
		PageManager pm = new PageManager((int)page.getTotalCount(), pageSize,pageSize);
		pm.goToPage(pageNo);
		request.setAttribute("dataList", page.getResult());   
	    request.setAttribute("pageNo", pageNo);
	    request.setAttribute("pageSize", pageSize);
	    request.setAttribute("pageHtml", pm.getPageCode());
    	return GET_YSQ_LIST_JSP;
    }
    /**
     * 删除核定教师
     * @author 90
     * @date    2017-6-29
     * @return
     */
    public String delHd(){
    	String hdId=request.getParameter("hdId");
    	String schoolId=request.getParameter("schoolId");
    	String teacherId=request.getParameter("teacherId");
    	//两步操作，第一步，删除教师的核定信息，第二步，更新学分登记，将为核定字段重置为空
    	this.baseCycleApprovalManager.delHd(hdId, teacherId, schoolId);
    	this.renderText(response, "0");
    	return null;
    }
}
