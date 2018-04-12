/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package org.my431.base.services;

/**
 * @author wangzhen
 * @version 1.0
 * @since 1.0
 */


import java.util.HashMap;
import java.util.Map;

import org.my431.base.model.BaseCycleApproval;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.platform.dao.support.Page;
import org.springframework.stereotype.Repository;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

@Repository
public class BaseCycleApprovalManager extends HibernateXsqlBuilderQueryDao<BaseCycleApproval>{

	public Class getEntityClass() {
		return BaseCycleApproval.class;
	}
	
	/**
	 * 获取周期核定列表
	 * @author 90
	 * @date    2017-6-27
	 * @param map
	 * @param pageSize
	 * @param pageNo
	 * @param type  school   teacher 
	 * @return
	 */
	public Page getBaseCycleApprovalPage(Map<String, Object> map,int pageSize,int pageNo,String roleType){
		Map<String,Object> values=new HashMap<String,Object>();
		for (String key:map.keySet()) {
			Object thisValue=map.get(key);
			if(isNotEmpty(thisValue)){
				values.put(key, thisValue);
			}
		}
		if("school".equals(roleType)){//如果是学校
			values.put("roleTypeLimit", " and TEACHER_ID is null ");
		}else{//教师
			values.put("roleTypeLimit", " and TEACHER_ID is not null ");
		}
		return this.getPagedNamedQuery("misBase::BaseCycleApproval::getBaseCycleApprovalPage", pageNo, pageSize, values);
	}
	
	
	/**
	 * 删除教师的核定信息
	 * @author 90
	 * @date    2017-6-29
	 * @param hdId
	 * @param teacherId
	 * @param schoolId
	 * @return
	 */
	public String delHd(String hdId,String teacherId,String schoolId){
		String deleteHd="delete BASE_CYCLE_APPROVAL t where t.TEACHER_ID='"+teacherId+"' and t.SCHOOL_ID='"+schoolId+"'";
		String updateSum="update BASE_TEACHER_CREDITS_SUM t set t.CAID='' where t.CAID='"+hdId+"' and t.TEACHER_ID='"+teacherId+"'";
		this.updateSql(deleteHd);
		this.updateSql(updateSum);
		return "success";
	}

}
