/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package org.my431.base.services;

import java.util.*;

/**
 * @author wangzhen
 * @version 1.0
 * @since 1.0
 */


import java.util.HashMap;
import java.util.Map;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

import org.my431.platform.dao.extend.HibernateEntityExtendDao;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.springframework.stereotype.Repository;
import org.my431.base.model.BaseTableUpdateLog;
import org.my431.platform.dao.support.Page;

@Repository
public class BaseTableUpdateLogManager extends HibernateXsqlBuilderQueryDao<BaseTableUpdateLog>{

	public Class getEntityClass() {
		return BaseTableUpdateLog.class;
	}
	
	public Page findPage(BaseTableUpdateLog query, int pageSize, int pageNo) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from BaseTableUpdateLog t where 1=1 "
			  	+ "/~ and t.tableName = {tableName} ~/"
				+ "/~ and t.lastUpdateTime >= {lastUpdateTimeBegin} ~/"
				+ "/~ and t.lastUpdateTime <= {lastUpdateTimeEnd} ~/"
				+ "/~ and t.lastAddTime >= {lastAddTimeBegin} ~/"
				+ "/~ and t.lastAddTime <= {lastAddTimeEnd} ~/"
			  	+ "/~ and t.dataCnt = {dataCnt} ~/"
				+ "/~ order by [sortColumns] ~/";

		Map filters = new HashMap();  
				
		XsqlBuilder xsqlBuilder=new XsqlBuilder();
		
        if(isNotEmpty(query.getId())) {
            filters.put("id", query.getId()); 
        }
        if(isNotEmpty(query.getTableName())) {
            filters.put("tableName", query.getTableName()); 
        }
        if(isNotEmpty(query.getLastUpdateTime())) {
            filters.put("lastUpdateTime", query.getLastUpdateTime()); 
        }
        if(isNotEmpty(query.getLastAddTime())) {
            filters.put("lastAddTime", query.getLastAddTime()); 
        }
        if(isNotEmpty(query.getDataCnt())) {
            filters.put("dataCnt", query.getDataCnt()); 
        }
        
        XsqlFilterResult result = xsqlBuilder.generateHql(sql,filters); 
        
		return pageQuery(result.getXsql(), pageNo, pageSize,result.getAcceptedFilters());
	}
	
	

}
