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

import org.hibernate.Query;
import org.hibernate.Session;
import org.my431.base.model.BaseNdModField;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.platform.dao.support.Page;
import org.my431.util.MMap;
import org.springframework.stereotype.Repository;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

@Repository
public class BaseNdModFieldManager extends HibernateXsqlBuilderQueryDao<BaseNdModField>{

	public Class getEntityClass() {
		return BaseNdModField.class;
	}
	
	public Page findPage(BaseNdModField query, int pageSize, int pageNo) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from BaseNdModField t where 1=1 "
			  	+ "/~ and t.sheetName = {sheetName} ~/"
			  	+ "/~ and t.sheetNameC = {sheetNameC} ~/"
			  	+ "/~ and t.fieldName = {fieldName} ~/"
			  	+ "/~ and t.fieldNameC = {fieldNameC} ~/"
			  	+ "/~ and t.isNeedAudit = {isNeedAudit} ~/"
			  	+ "/~ and t.creUserId = {creUserId} ~/"
				+ "/~ and t.creTime >= {creTimeBegin} ~/"
				+ "/~ and t.creTime <= {creTimeEnd} ~/"
			  	+ "/~ and t.modUserId = {modUserId} ~/"
				+ "/~ and t.modTime >= {modTimeBegin} ~/"
				+ "/~ and t.modTime <= {modTimeEnd} ~/"
			  	+ "/~ and t.deleteFlag = {deleteFlag} ~/"
			  	+ "/~ and t.deleteUserId = {deleteUserId} ~/"
				+ "/~ and t.deleteTime >= {deleteTimeBegin} ~/"
				+ "/~ and t.deleteTime <= {deleteTimeEnd} ~/"
				+ "/~ order by [sortColumns] ~/";

		Map filters = new HashMap();  
				
		XsqlBuilder xsqlBuilder=new XsqlBuilder();
		
        if(isNotEmpty(query.getSheetName())) {
            filters.put("sheetName", query.getSheetName()); 
        }
        if(isNotEmpty(query.getSheetNameC())) {
            filters.put("sheetNameC", query.getSheetNameC()); 
        }
        if(isNotEmpty(query.getFieldName())) {
            filters.put("fieldName", query.getFieldName()); 
        }
        if(isNotEmpty(query.getFieldNameC())) {
            filters.put("fieldNameC", query.getFieldNameC()); 
        }
        if(isNotEmpty(query.getIsNeedAudit())) {
            filters.put("isNeedAudit", query.getIsNeedAudit()); 
        }
        if(isNotEmpty(query.getCreUserId())) {
            filters.put("creUserId", query.getCreUserId()); 
        }
        if(isNotEmpty(query.getCreTime())) {
            filters.put("creTime", query.getCreTime()); 
        }
        if(isNotEmpty(query.getModUserId())) {
            filters.put("modUserId", query.getModUserId()); 
        }
        if(isNotEmpty(query.getModTime())) {
            filters.put("modTime", query.getModTime()); 
        }
        if(isNotEmpty(query.getDeleteFlag())) {
            filters.put("deleteFlag", query.getDeleteFlag()); 
        }
        if(isNotEmpty(query.getDeleteUserId())) {
            filters.put("deleteUserId", query.getDeleteUserId()); 
        }
        if(isNotEmpty(query.getDeleteTime())) {
            filters.put("deleteTime", query.getDeleteTime()); 
        }
        
        XsqlFilterResult result = xsqlBuilder.generateHql(sql,filters); 
        
		return pageQuery(result.getXsql(), pageNo, pageSize,result.getAcceptedFilters());
	}
	/**
	 * 查询列表
	 * */
	public List<Map<String, Object>> getBaseNdModFieldMapList(Map<String, Object> map) {
		Map<String, Object> values = new HashMap<String, Object>();
		if (!map.isEmpty()) {
			for(Map.Entry<String, Object> entry : map.entrySet()) {
				values.put(entry.getKey(), entry.getValue());
			}
			return getNamedQuery("misBase::getBaseNdModFieldMapList::query", values);
		}
		return null;
	}
	/**
	 * 
	* @Description: 传入表名，得到需要修改字段的map,修改字段-是否审核
	* @author hyl     
	* @date 2017-6-26 下午4:55:43  
	* @version V1.0 
	* @author user
	 */
	public Map<String, Object> getMapOfModField(String tableNames) {
		Map<String, Object> maps = new HashMap<String, Object>();
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("deleteFlag", 0);
		values.put("sheetName", tableNames.toUpperCase());
		List<Map<String, Object>> list = getBaseNdModFieldMapList(values);
		if (MMap.validateList(list)) {
			for (Map<String, Object> map : list) {
				maps.put(map.get("FIELD_NAME").toString(), map.get("IS_NEED_AUDIT"));
			}
		}
		return maps;
	}
}
