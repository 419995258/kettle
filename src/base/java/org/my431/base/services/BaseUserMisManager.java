/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2011
 */

package org.my431.base.services;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;
import org.my431.base.model.BaseUser;
import org.my431.platform.dao.extend.HibernateNamedQueryDao;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.platform.dao.support.Page;
import org.my431.plugin.redis.services.RedisManager;
import org.springframework.stereotype.Repository;

@Repository
public class BaseUserMisManager extends HibernateXsqlBuilderQueryDao<BaseUser>{
	/**
	 * 
	 * 通过省市县查询初始化情况
	 * @author    hmc    2015年4月10日  下午6:34:33
	 * @param proCode	省code
	 * @param cityCode	市code
	 * @param countryCode 区,县code
	 * @return
	 */
	public List getAllIniState(String proCode, String cityCode,String countryCode) {
		Map<String,String> m=new HashMap<String, String>();
		if(null!=proCode&&!"".equals(proCode)&&!"null".equals(proCode)){
			m.put("mywhere", "and (t.parent_code='"+proCode+"'  and t.area_code not in('"+proCode+"') ) ");
		}else{
			m.put("mywhere", "and (t.parent_code='-1'  and t.area_code not in('-1') ) ");
		}
		if(null!=cityCode&&!"".equals(cityCode)&&!"null".equals(cityCode)){
			m.put("mywhere", "and (t.parent_code='"+cityCode+"'  and t.area_code not in('"+cityCode+"') ) ");
		}
		if(null!=countryCode&&!"".equals(countryCode)&&!"null".equals(countryCode)){
			m.put("mywhere", "and t.area_code='"+countryCode+"' ");
		}
		//HibernateXsqlBuilderQueryDao<BaseUser> xsql=new HibernateXsqlBuilderQueryDao<BaseUser>();
		
		
		List list=this.getNamedQuery("misBase::getAllManagerIniState::query",m);
		return list;
	}
	
	/**
	 * 获得地区管理员
	 * @author 90
	 * @date    2017-6-21
	 * @param areaId
	 * @param role role.cityManager role.countryManager role.provinceManager 
	 * @return
	 */
	public Map<String,Object> getManagerByAreaIdAndRole(String areaId,String role){
		Map<String,Object> userMap=null;
		if(isNotEmpty(role)){
			Map<String,Object> values=new HashMap<String, Object>();
			values.put("role", role);
			if("role.provinceManager".equals(role)){
				values.put("provinceId", areaId);
			}else if("role.cityManager".equals(role)){
				values.put("cityId", areaId);
			}else if("role.countryManager".equals(role)){
				values.put("countryId", areaId);
			}
			List<Map<String,Object>> list=this.getNamedQuery("misbase::BaseUser::getManagerByAreaIdAndRole", values);
			if(list!=null&&list.size()>0){
				userMap=list.get(0);
			}
		} 
		return userMap;
	}
	
	/**
	 * 获取学校管理员
	 * @author 90
	 * @date    2017-6-21
	 * @param schoolId
	 * @param role
	 * @return
	 */
	public Map<String,Object> getSchoolManagerByAreaIdAndRole(String schoolId,String role){
		Map<String,Object> userMap=null;
		if(isNotEmpty(role)){
			Map<String,Object> values=new HashMap<String, Object>();
			values.put("role", role);
			values.put("schoolId", schoolId);
			List<Map<String,Object>> list=this.getNamedQuery("misbase::BaseUser::getManagerByAreaIdAndRole", values);
			if(list!=null&&list.size()>0){
				userMap=list.get(0);
			}
		} 
		return userMap;
	}
	
	/**
	 * 获得用户列表
	 * @author 90
	 * @date    2017-6-28
	 * @param schoolId
	 * @param role
	 * @return
	 */
	public Page getUserList(Map<String,Object> map,int pageNo,int pageSize,String teacherName){
		Map<String,Object> values=new HashMap<String,Object>();
		for (String key:map.keySet()) {
			Object thisValue=map.get(key);
			if(isNotEmpty(thisValue)){
				values.put(key, thisValue);
			}
		}
		return this.getPagedNamedQuery("misbase::BaseUser::getUserList", pageNo, pageSize, values);
	}
}

