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

import org.my431.base.model.BaseUserSheetMap;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.platform.dao.support.Page;
import org.springframework.stereotype.Repository;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

@Repository
public class BaseUserSheetMapManager extends HibernateXsqlBuilderQueryDao<BaseUserSheetMap>{

	public Class getEntityClass() {
		return BaseUserSheetMap.class;
	}
	
	public Page findPage(BaseUserSheetMap query, int pageSize, int pageNo) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from BaseUserSheetMap t where 1=1 "
			  	+ "/~ and t.sheetName = {sheetName} ~/"
			  	+ "/~ and t.fieldName = {fieldName} ~/"
			  	+ "/~ and t.fieldNameC = {fieldNameC} ~/"
			  	+ "/~ and t.fieldNameDisp = {fieldNameDisp} ~/"
				+ "/~ and t.lastModTime >= {lastModTimeBegin} ~/"
				+ "/~ and t.lastModTime <= {lastModTimeEnd} ~/"
			  	+ "/~ and t.lastModUserId = {lastModUserId} ~/"
			  	+ "/~ and t.validStatus = {validStatus} ~/"
			  	+ "/~ and t.isMonitorFlag = {isMonitorFlag} ~/"
			  	+ "/~ and t.isModNeedAudit = {isModNeedAudit} ~/"
				+ "/~ order by [sortColumns] ~/";

		Map filters = new HashMap();  
				
		XsqlBuilder xsqlBuilder=new XsqlBuilder();
		
        if(isNotEmpty(query.getSheetName())) {
            filters.put("sheetName", query.getSheetName()); 
        }
        if(isNotEmpty(query.getFieldName())) {
            filters.put("fieldName", query.getFieldName()); 
        }
        if(isNotEmpty(query.getFieldNameC())) {
            filters.put("fieldNameC", query.getFieldNameC()); 
        }
        if(isNotEmpty(query.getFieldNameDisp())) {
            filters.put("fieldNameDisp", query.getFieldNameDisp()); 
        }
        if(isNotEmpty(query.getLastModTime())) {
            filters.put("lastModTime", query.getLastModTime()); 
        }
        if(isNotEmpty(query.getLastModUserId())) {
            filters.put("lastModUserId", query.getLastModUserId()); 
        }
        if(isNotEmpty(query.getValidStatus())) {
            filters.put("validStatus", query.getValidStatus()); 
        }
        if(isNotEmpty(query.getIsMonitorFlag())) {
            filters.put("isMonitorFlag", query.getIsMonitorFlag()); 
        }
        if(isNotEmpty(query.getIsModNeedAudit())) {
            filters.put("isModNeedAudit", query.getIsModNeedAudit()); 
        }
        
        XsqlFilterResult result = xsqlBuilder.generateHql(sql,filters); 
        
		return pageQuery(result.getXsql(), pageNo, pageSize,result.getAcceptedFilters());
	}
	

}
