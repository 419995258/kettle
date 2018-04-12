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


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.my431.base.model.BaseProTeacherMap;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.platform.dao.support.Page;
import org.my431.util.MMap;
import org.my431.util.RandomGuid;
import org.my431.util.excel.ReadExcel;
import org.springframework.stereotype.Repository;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

@Repository
public class BaseProTeacherMapManager extends HibernateXsqlBuilderQueryDao<BaseProTeacherMap>{

	public Class getEntityClass() {
		return BaseProTeacherMap.class;
	}
	
	
	/**
	 * 获取学分学时
	 * @author 90
	 * @date    2017-6-7
	 * @param map
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public Page getBaseTeacherMapPage(Map<String, Object> map,int pageSize,int pageNo){
		Map<String,Object> values=new HashMap<String,Object>();
		for (String key:map.keySet()) {
			Object thisValue=map.get(key);
			if(isNotEmpty(thisValue)){
				values.put(key, thisValue);
			}
		}
		return this.getPagedNamedQuery("misBase::BaseProTeacherMap::getBaseTeacherMapPage", pageNo, pageSize, values);
	}
	
	
	/**
	 * 获取学时学分
	 * @author 90
	 * @date    2017-6-16
	 * @param map
	 * @return
	 */
	public Map<String,Object> getBaseTeacherMap(Map<String, Object> map){
		Map<String, Object> btMap=new HashMap<String, Object>();
		Map<String,Object> values=new HashMap<String,Object>();
		for (String key:map.keySet()) {
			Object thisValue=map.get(key);
			if(isNotEmpty(thisValue)){
				values.put(key, thisValue);
			}
		}
		List list=this.getNamedQuery("misBase::BaseProTeacherMap::getBaseTeacherMap", values);
		if(list!=null&&list.size()>0){
			btMap=(Map<String, Object>) list.get(0);
		}
		return btMap;
	}
}
