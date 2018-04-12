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

import org.my431.base.model.BaseImportRecord;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.platform.dao.support.Page;
import org.springframework.stereotype.Repository;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

@Repository
public class BaseImportRecordManager extends HibernateXsqlBuilderQueryDao<BaseImportRecord>{

	public Class getEntityClass() {
		return BaseImportRecord.class;
	}
	
	public Page findPage(BaseImportRecord query, int pageSize, int pageNo) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from BaseImportRecord t where 1=1 "
			  	+ "/~ and t.projectFileId = {projectFileId} ~/"
			  	+ "/~ and t.importType = {importType} ~/"
			  	+ "/~ and t.importStatus = {importStatus} ~/"
			  	+ "/~ and t.importResult = {importResult} ~/"
			  	+ "/~ and t.uploadCount = {uploadCount} ~/"
			  	+ "/~ and t.importCount = {importCount} ~/"
			  	+ "/~ and t.creUserId = {creUserId} ~/"
				+ "/~ and t.creTime >= {creTimeBegin} ~/"
				+ "/~ and t.creTime <= {creTimeEnd} ~/"
				+ "/~ order by [sortColumns] ~/";

		Map filters = new HashMap();  
				
		XsqlBuilder xsqlBuilder=new XsqlBuilder();
        if(isNotEmpty(query.getProjectFileId())) {
            filters.put("projectFileId", query.getProjectFileId()); 
        }
        if(isNotEmpty(query.getImportType())) {
            filters.put("importType", query.getImportType()); 
        }
        if(isNotEmpty(query.getImportStatus())) {
            filters.put("importStatus", query.getImportStatus()); 
        }
        if(isNotEmpty(query.getImportResult())) {
            filters.put("importResult", query.getImportResult()); 
        }
        if(isNotEmpty(query.getUploadCount())) {
            filters.put("uploadCount", query.getUploadCount()); 
        }
        if(isNotEmpty(query.getImportCount())) {
            filters.put("importCount", query.getImportCount()); 
        }
        if(isNotEmpty(query.getCreUserId())) {
            filters.put("creUserId", query.getCreUserId()); 
        }
        if(isNotEmpty(query.getCreTime())) {
            filters.put("creTime", query.getCreTime()); 
        }
        
        XsqlFilterResult result = xsqlBuilder.generateHql(sql,filters); 
        
		return pageQuery(result.getXsql(), pageNo, pageSize,result.getAcceptedFilters());
	}
	

}
