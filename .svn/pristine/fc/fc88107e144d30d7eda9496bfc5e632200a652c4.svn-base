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
import java.util.List;
import java.util.Map;


import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.my431.base.model.BaseArea;
import org.my431.base.model.BaseCreditProject;
import org.my431.base.model.BaseTeacherCerifiy;
import org.my431.base.model.BaseUser;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.platform.dao.support.Page;
import org.my431.taglib.My431Function;
import org.springframework.stereotype.Repository;


import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

@Repository
public class BaseTeacherCerifiyManager extends HibernateXsqlBuilderQueryDao<BaseTeacherCerifiy>{

	private My431Function my431Function;
	
	public Class getEntityClass() {
		return BaseTeacherCerifiy.class;
	}
	
	public Page findPage(BaseTeacherCerifiy query, int pageSize, int pageNo) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from BaseTeacherCerifiy t where 1=1 "
			  	+ "/~ and t.teacherId = {teacherId} ~/"
			  	+ "/~ and t.teacherIdCode = {teacherIdCode} ~/"
			  	+ "/~ and t.cerifiyType = {cerifiyType} ~/"
			  	+ "/~ and t.businessName = {businessName} ~/"
			  	+ "/~ and t.annualCreditYear = {annualCreditYear} ~/"
				+ "/~ and t.annualStartTime >= {annualStartTimeBegin} ~/"
				+ "/~ and t.annualStartTime <= {annualStartTimeEnd} ~/"
				+ "/~ and t.annualEndTime >= {annualEndTimeBegin} ~/"
				+ "/~ and t.annualEndTime <= {annualEndTimeEnd} ~/"
			  	+ "/~ and t.schoolId = {schoolId} ~/"
			  	+ "/~ and t.provinceId = {provinceId} ~/"
			  	+ "/~ and t.cityId = {cityId} ~/"
			  	+ "/~ and t.countyId = {countyId} ~/"
			  	+ "/~ and t.areaId = {areaId} ~/"
			  	+ "/~ and t.deleteFlag = {deleteFlag} ~/"
				+ "/~ and t.deleteTime >= {deleteTimeBegin} ~/"
				+ "/~ and t.deleteTime <= {deleteTimeEnd} ~/"
			  	+ "/~ and t.deleteUser = {deleteUser} ~/"
				+ "/~ order by [sortColumns] ~/";

		Map filters = new HashMap();  
				
		XsqlBuilder xsqlBuilder=new XsqlBuilder();
		
        if(isNotEmpty(query.getTeacherId())) {
            filters.put("teacherId", query.getTeacherId()); 
        }
        if(isNotEmpty(query.getTeacherIdCode())) {
            filters.put("teacherIdCode", query.getTeacherIdCode()); 
        }
        if(isNotEmpty(query.getCerifiyType())) {
            filters.put("cerifiyType", query.getCerifiyType()); 
        }
        if(isNotEmpty(query.getBusinessName())) {
            filters.put("businessName", query.getBusinessName()); 
        }
        if(isNotEmpty(query.getAnnualCreditYear())) {
            filters.put("annualCreditYear", query.getAnnualCreditYear()); 
        }
        if(isNotEmpty(query.getAnnualStartTime())) {
            filters.put("annualStartTime", query.getAnnualStartTime()); 
        }
        if(isNotEmpty(query.getAnnualEndTime())) {
            filters.put("annualEndTime", query.getAnnualEndTime()); 
        }
        if(isNotEmpty(query.getSchoolId())) {
            filters.put("schoolId", query.getSchoolId()); 
        }
        if(isNotEmpty(query.getProvinceId())) {
            filters.put("provinceId", query.getProvinceId()); 
        }
        if(isNotEmpty(query.getCityId())) {
            filters.put("cityId", query.getCityId()); 
        }
        if(isNotEmpty(query.getCountyId())) {
            filters.put("countyId", query.getCountyId()); 
        }
        if(isNotEmpty(query.getAreaId())) {
            filters.put("areaId", query.getAreaId()); 
        }
        if(isNotEmpty(query.getDeleteFlag())) {
            filters.put("deleteFlag", query.getDeleteFlag()); 
        }
        if(isNotEmpty(query.getDeleteTime())) {
            filters.put("deleteTime", query.getDeleteTime()); 
        }
        if(isNotEmpty(query.getDeleteUser())) {
            filters.put("deleteUser", query.getDeleteUser()); 
        }
        
        XsqlFilterResult result = xsqlBuilder.generateHql(sql,filters); 
        
		return pageQuery(result.getXsql(), pageNo, pageSize,result.getAcceptedFilters());
	}
	
	public List getTeacherCerifiyTeacher(String userId) {
		Map<String, String>  map = new HashMap<String, String>();
		map.put("userId", userId);
		List list = (List) getNamedQuery("misBase:getTeacherCerifiyTeacher:query", map);
		return list;
		
	}
	
	public List getTeacherRegLog(String id,String key) {
		Map<String, Object>  map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("key", key);
		List list = (List) getNamedQuery("misbase::BaseTeacherCerifiy::getTeacherRegLog", map);
		for (Object object : list) {
			Map<String, Object> mapget = (Map<String, Object>) object;
			//修改结束步骤的值
			String code = (String) mapget.get("BUSINESS_STEP_KEY");
			mapget.put("BUSINESS_STEP_KEY", my431Function.getValueByCode(code));
		}
		return list;
		
	}
	
	public List<BaseTeacherCerifiy> getBaseTeacherCerifiyByTeacherIDAndCerifiyType(String teacherId,String cerifiyType){
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(BaseTeacherCerifiy.class);
		criteria.add(Restrictions.eq("teacherId", teacherId));  
		criteria.add(Restrictions.eq("cerifiyType", cerifiyType));  
		//未删除
		criteria.add(Restrictions.eq("deleteFlag", 0));  
		List<BaseTeacherCerifiy> list = criteria.list();
		return list;
		
	}
	
	
	public Page getTeacherCerifiyPage(Map<String, Object>  mapValues, int pageSize, int pageNo) {
		return getPagedNamedQuery("misbase::BaseTeacherCerifiy::getTeacherCerifiy", pageNo, pageSize, mapValues);
	}
	
	/**
	 * 根据id以及角色查找用户
	 * @param id
	 * @param role
	 * @return
	 */
	public List<BaseUser> getUserByIdAndRole(String type,String id,String role){
		List<BaseUser> baseUserList = null;
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(BaseUser.class);
		criteria.add(Restrictions.eq(type, id));
		criteria.add(Restrictions.eq("defaultRoleCode", role));
		List<BaseUser> list = criteria.list();
		return list;
		
	}
	
	/**
	 * 获取教师的信息
	 * @author 90
	 * @date    2017-6-26
	 * @param map
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public Page getBaseTeacherCerifiyPage(Map<String, Object> map,int pageSize,int pageNo){
		Map<String,Object> values=new HashMap<String,Object>();
		for (String key:map.keySet()) {
			Object thisValue=map.get(key);
			if(isNotEmpty(thisValue)){
				values.put(key, thisValue);
			}
		}
		return this.getPagedNamedQuery("misBase::BaseTeacherCerifiy::getBaseTeacherCerifiyPage", pageNo, pageSize, values);
	}
	
}
