/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package org.my431.base.web;

import java.util.Map;

import oracle.net.aso.r;
import oracle.net.aso.s;

import org.my431.base.model.BaseBusinessStep;
import org.my431.base.model.BaseTeacherCreditsSum;
import org.my431.base.model.BaseUser;
import org.my431.base.services.BaseBusinessStepManager;
import org.my431.base.services.BaseTeacherCreditsSumManager;
import org.my431.base.services.BaseToDoManager;
import org.my431.base.services.CacheBaseSchoolManager;
import org.my431.base.services.CacheBaseUserManager;
import org.my431.base.services.PageManager;
import org.my431.platform.dao.support.Page;
import org.my431.platform.web.StrutsTreeEntityAction;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author wangzhen
 * @version 1.0
 * @since 1.0
 */


public class BaseBusinessStepAction extends StrutsTreeEntityAction<BaseBusinessStep,BaseBusinessStepManager> implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "//base/BaseBusinessStep/query.jsp";
	protected static final String LIST_JSP= "//base/BaseBusinessStep/list.jsp";
	protected static final String CREATE_JSP = "//base/BaseBusinessStep/create.jsp";
	protected static final String EDIT_JSP = "//base/BaseBusinessStep/edit.jsp";
	protected static final String SHOW_JSP = "//base/BaseBusinessStep/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!//base/BaseBusinessStep/list.jspx";
	
	private BaseBusinessStepManager baseBusinessStepManager;
	private BaseTeacherCreditsSumManager baseTeacherCreditsSumManager;
	
	private BaseBusinessStep baseBusinessStep;
	private BaseToDoManager baseToDoManager;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			baseBusinessStep = new BaseBusinessStep();
		} else {
			baseBusinessStep = (BaseBusinessStep)baseBusinessStepManager.get(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseBusinessStepManager(BaseBusinessStepManager manager) {
		this.baseBusinessStepManager = manager;
	}	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseTeacherCreditsSumManager(BaseTeacherCreditsSumManager manager) {
		this.baseTeacherCreditsSumManager = manager;
	}
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setBaseToDoManager(BaseToDoManager manager) {
		this.baseToDoManager = manager;
	}
	public Object getModel() {
		return baseBusinessStep;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	public String list() {
		BaseBusinessStep query = new BaseBusinessStep();
		
		int pageNo=1;
		if(request.getParameter("pageNo")!=null&&!request.getParameter("pageNo").equals("")){
			pageNo=Integer.valueOf(request.getParameter("pageNo"));
		}
		int pageSize=10;
		if(request.getParameter("pageSize")!=null&&!request.getParameter("pageSize").equals("")){
			pageSize=Integer.valueOf(request.getParameter("pageSize"));
		}
		/*Page page = baseBusinessStepManager.findPage(query,pageSize,pageNo);
		PageManager pm = new PageManager((int)page.getTotalCount(), pageSize,pageSize);
		pm.goToPage(pageNo);
		request.setAttribute("dataList", page.getResult());   
	    request.setAttribute("pageNo", pageNo);
	    request.setAttribute("pageSize", pageSize);
	    request.setAttribute("pageHtml", pm.getPageCode());*/
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
		baseBusinessStepManager.save(baseBusinessStep);
		return LIST_ACTION;
	}
	
	/**进入更新页面*/
	public String edit() {
		request.setAttribute("baseBusinessStep", baseBusinessStep);
		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	public String updateStep() {
		String userId=(String) request.getSession().getAttribute("wsuserId");
		String objectId=request.getParameter("objectId");
		String result=request.getParameter("result");
		String stepType=request.getParameter("stepType");
		String comment=request.getParameter("comment");
		baseBusinessStepManager.updateStep(objectId, stepType, result, comment, userId);
		this.renderText(response, "0");
		return null;
	}
	
	/**删除对象*/
	public String delete() {
		baseBusinessStepManager.removeById(id);
		return LIST_ACTION;
	}

}
