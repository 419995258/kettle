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


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.my431.base.model.BaseModLog;
import org.my431.base.model.BaseUser;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.platform.dao.support.Page;
import org.my431.util.DateFormater;
import org.my431.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

@Repository
public class BaseModLogManager extends HibernateXsqlBuilderQueryDao<BaseModLog>{

	@Autowired
	private BaseNdModFieldManager baseNdModFieldManager;
	@Autowired
	private BaseUserManager baseUserManager;
	@Autowired
	private BaseToDoManager baseToDoManager;
	
	public Class getEntityClass() {
		return BaseModLog.class;
	}
	
	public Page findPage(BaseModLog query, int pageSize, int pageNo) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from BaseModLog t where 1=1 "
			  	+ "/~ and t.sheetName = {sheetName} ~/"
			  	+ "/~ and t.primKey = {primKey} ~/"
			  	+ "/~ and t.fieldsGroupId = {fieldsGroupId} ~/"
			  	+ "/~ and t.fieldName = {fieldName} ~/"
			  	+ "/~ and t.fieldDataType = {fieldDataType} ~/"
			  	+ "/~ and t.fieldOldValue = {fieldOldValue} ~/"
			  	+ "/~ and t.fieldNewValue = {fieldNewValue} ~/"
			  	+ "/~ and t.modUserId = {modUserId} ~/"
				+ "/~ and t.modTime >= {modTimeBegin} ~/"
				+ "/~ and t.modTime <= {modTimeEnd} ~/"
			  	+ "/~ and t.auditStatus = {auditStatus} ~/"
				+ "/~ and t.auditTime >= {auditTimeBegin} ~/"
				+ "/~ and t.auditTime <= {auditTimeEnd} ~/"
			  	+ "/~ and t.auditUserId = {auditUserId} ~/"
			  	+ "/~ and t.auditType = {auditType} ~/"
			  	+ "/~ and t.delFlag = {delFlag} ~/"
				+ "/~ and t.delTime >= {delTimeBegin} ~/"
				+ "/~ and t.delTime <= {delTimeEnd} ~/"
			  	+ "/~ and t.delUserId = {delUserId} ~/"
			  	+ "/~ and t.provinceId = {provinceId} ~/"
			  	+ "/~ and t.cityId = {cityId} ~/"
			  	+ "/~ and t.countyId = {countyId} ~/"
			  	+ "/~ and t.schoolId = {schoolId} ~/"
				+ "/~ order by [sortColumns] ~/";

		Map filters = new HashMap();  
				
		XsqlBuilder xsqlBuilder=new XsqlBuilder();
		
        if(isNotEmpty(query.getSheetName())) {
            filters.put("sheetName", query.getSheetName()); 
        }
        if(isNotEmpty(query.getPrimKey())) {
            filters.put("primKey", query.getPrimKey()); 
        }
        if(isNotEmpty(query.getFieldsGroupId())) {
            filters.put("fieldsGroupId", query.getFieldsGroupId()); 
        }
        if(isNotEmpty(query.getFieldName())) {
            filters.put("fieldName", query.getFieldName()); 
        }
        if(isNotEmpty(query.getFieldDataType())) {
            filters.put("fieldDataType", query.getFieldDataType()); 
        }
        if(isNotEmpty(query.getFieldOldValue())) {
            filters.put("fieldOldValue", query.getFieldOldValue()); 
        }
        if(isNotEmpty(query.getFieldNewValue())) {
            filters.put("fieldNewValue", query.getFieldNewValue()); 
        }
        if(isNotEmpty(query.getModUserId())) {
            filters.put("modUserId", query.getModUserId()); 
        }
        if(isNotEmpty(query.getModTime())) {
            filters.put("modTime", query.getModTime()); 
        }
        if(isNotEmpty(query.getAuditStatus())) {
            filters.put("auditStatus", query.getAuditStatus()); 
        }
        if(isNotEmpty(query.getAuditTime())) {
            filters.put("auditTime", query.getAuditTime()); 
        }
        if(isNotEmpty(query.getAuditUserId())) {
            filters.put("auditUserId", query.getAuditUserId()); 
        }
        if(isNotEmpty(query.getAuditType())) {
            filters.put("auditType", query.getAuditType()); 
        }
        if(isNotEmpty(query.getDelFlag())) {
            filters.put("delFlag", query.getDelFlag()); 
        }
        if(isNotEmpty(query.getDelTime())) {
            filters.put("delTime", query.getDelTime()); 
        }
        if(isNotEmpty(query.getDelUserId())) {
            filters.put("delUserId", query.getDelUserId()); 
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
        if(isNotEmpty(query.getSchoolId())) {
            filters.put("schoolId", query.getSchoolId()); 
        }
        
        XsqlFilterResult result = xsqlBuilder.generateHql(sql,filters); 
        
		return pageQuery(result.getXsql(), pageNo, pageSize,result.getAcceptedFilters());
	}
	/**
	 * 
	 * hyl<br/>
	 * 参数说明： tableName：表名，pId ：主键值，    tableFieldsType ：HibernateToolsUtil.getTableFieldsType（clazz）<br/>
	 * termValues :做更新时where后面的条件；newValueMap：需要更新的值；oldValueMap：更新之前的值；currentUserId当前用户id<br/>
	 * toDoType 业务步骤类型，toUserId 待办事项交给谁，url跳转的url<br/>
	 * termValues，newValueMap，oldValueMap:key为数据库字段值 <br/>
	* 新旧值有变化，则保存到BASE_MOD_LOG，如果不需要审批则直接修改，如果需要审批，则暂不修改且发待处理。 <br/>
	*2017-6-26 下午2:17:11  
	*  
	 */
	public Integer saveBaseModLog(String tableName,String pId,Map<String, String> tableFieldsType,Map<String, Object> termValues,
			Map<String, Object> newValueMap,Map<String, Object> oldValueMap,String currentUserId,String toDoType,
			String toUserId,String url) {
		Map<String, Object> modLogValues = new HashMap<String, Object>();//
		Map<String, Object> values_0 = new HashMap<String, Object>();//不需要审核的值
		Map<String, Object> values_1 = new HashMap<String, Object>();//需要审核字段
		Integer flag = 0;//-1 新值为空，-2需要修改字段未查询到，1成功
		if (!newValueMap.isEmpty()) {
			//需要记录的修改字段
			Map<String, Object>  modFieldMaps = baseNdModFieldManager.getMapOfModField(tableName);
			if (!modFieldMaps.isEmpty()) {//有需要记录的修改字段
				for (Map.Entry<String, Object> entry : newValueMap.entrySet()) {//新字段
					String keyStr = entry.getKey();
					String newVal = entry.getValue().toString();
					if (modFieldMaps.containsKey(keyStr)) {
						if (!newVal.equals(oldValueMap.get(keyStr)+"")) {
							if ("1".equals(modFieldMaps.get(keyStr)+"")) {//需要审核
								values_1.put(keyStr, newVal);
							}else {
								values_0.put(keyStr, newVal);
							}
						}
					}
				}
				//直接更新不需要审核的字段
				if (!values_0.isEmpty()) {
					updateData(values_0, termValues, tableName);
				}
				//需要审核的字段，发待办事项
				baseToDoManager.toDoList(pId, toDoType, currentUserId, toUserId, url);
				
				BaseUser  baseUser = baseUserManager.getBaseUserByUserId(currentUserId);
				String groupUUID = UUID.randomUUID().toString();
				//modLogValues.put("MOD_LOG_ID", UUID.randomUUID().toString());
				modLogValues.put("SHEET_NAME", tableName.toUpperCase());
				modLogValues.put("PRIM_KEY", pId);
				modLogValues.put("FIELDS_GROUP_ID", groupUUID);
				modLogValues.put("MOD_USER_ID", currentUserId);
				modLogValues.put("MOD_TIME", "to_date('"+DateFormater.DateToString(new Date(), "yyyy-MM-dd HH:mm:ss")+"','yyyy-MM-dd:hh24:mi:ss')");
				modLogValues.put("DEL_FLAG", 0);
				modLogValues.put("PROVINCE_ID", baseUser.getProvinceId());
				if (StringUtils.isNotBlank(baseUser.getCityId())) {
					modLogValues.put("CITY_ID", baseUser.getCityId());
				}
				if (StringUtils.isNotBlank(baseUser.getCountyId())) {
					modLogValues.put("COUNTY_ID", baseUser.getCountyId());
				}
				if (StringUtils.isNotBlank(baseUser.getSchoolId())) {
					modLogValues.put("SCHOOL_ID", baseUser.getSchoolId());
				}
				//需要审核的
				if (!values_1.isEmpty()) {
					for (Map.Entry<String, Object> entry : values_1.entrySet()) {
						String key = entry.getKey();
						modLogValues.put("MOD_LOG_ID", UUID.randomUUID().toString());
						modLogValues.put("FIELD_NAME", entry.getKey());
						modLogValues.put("FIELD_DATA_TYPE", tableFieldsType.get(key));
						modLogValues.put("FIELD_OLD_VALUE", oldValueMap.get(key));
						modLogValues.put("FIELD_NEW_VALUE", newValueMap.get(key));
						modLogValues.put("AUDIT_STATUS", 0);
						modLogValues.put("AUDIT_TYPE", "edit");
						insertDataV2(modLogValues, "BASE_MOD_LOG");
						
					}
				}
				//不需要审核的
				if (!values_0.isEmpty()) {
					for (Map.Entry<String, Object> entry : values_0.entrySet()) {
						String key = entry.getKey();
						modLogValues.put("MOD_LOG_ID", UUID.randomUUID().toString());
						modLogValues.put("FIELD_NAME", entry.getKey());
						modLogValues.put("FIELD_DATA_TYPE", tableFieldsType.get(key));
						modLogValues.put("FIELD_OLD_VALUE", oldValueMap.get(key));
						modLogValues.put("FIELD_NEW_VALUE", newValueMap.get(key));
						modLogValues.put("AUDIT_STATUS", -1);
						modLogValues.put("AUDIT_TYPE", "edit");
						insertDataV2(modLogValues, "BASE_MOD_LOG");
					}
				}
				flag = 1;
			}else {
				updateData(newValueMap, termValues, tableName);
				flag = -2;
			}
		}else {
			flag = -1;
		}
		return flag;
	}
	/**
	 * 
     *类的描述:updateModLog<br/>
     *功能描述 ：审核通过修改信息<br/>
     *作者:hyl<br/>
     *创建日期:2017-06-28<br/>
     *修改人：<br/>
     *修改日期：<br/>
     *修改原因描述:<br/>
	 * 
	 */

	public void updateModLogAfterAudit(Map<String, Object> newValueMap,Map<String, Object> termValues,String tableNames,String objectId,String toDoType,String CurrentUserId) {
		updateData(newValueMap, termValues,tableNames);
		baseToDoManager.updateBaseToDoStatus(objectId, toDoType, CurrentUserId);
		//return 0;
	}
	
	
	/**
	 * 
     *类的描述:getBaseModLogList<br/>
     *功能描述 ：修改日志的获得<br/>
     *作者:hyl<br/>
     *创建日期:2017-06-28<br/>
     *修改人：<br/>
     *修改日期：<br/>
     *修改原因描述:<br/>
	 * 
	 */
	public List<Map<String, Object>> getBaseModLogList(Map<String, Object> termValues,String otherQuery,String orderBy) {
		Map<String, Object> values = new HashMap<String, Object>();
		if (!termValues.isEmpty()) {
			for (Map.Entry<String, Object> entry : termValues.entrySet()) {
				values.put(entry.getKey(),entry.getValue());
			}
		}
		if (StringUtils.isNotBlank(otherQuery)) {
			values.put("otherQuery", otherQuery);
		}
		if (StringUtils.isNotBlank(orderBy)) {
			values.put("orderBy", orderBy);
		}
		return getNamedQuery("misBase::getBaseModLogList::query", values);
	}
	/**
	 * 
     *类的描述:getPageOfBaseModLog<br/>
     *功能描述 ：修改日志的获得<br/>
     *作者:hyl<br/>
     *创建日期:2017-06-28<br/>
     *修改人：<br/>
     *修改日期：<br/>
     *修改原因描述:<br/>
	 * 
	 */
	public Page getPageOfBaseModLog(Map<String, Object> termValues,String otherQuery,String orderBy,int pageSize, int pageNo) {
		Map<String, Object> values = new HashMap<String, Object>();
		if (!termValues.isEmpty()) {
			for (Map.Entry<String, Object> entry : termValues.entrySet()) {
				values.put(entry.getKey(),entry.getValue());
			}
		}
		if (StringUtils.isNotBlank(otherQuery)) {
			values.put("otherQuery", otherQuery);
		}
		if (StringUtils.isNotBlank(orderBy)) {
			values.put("orderBy", orderBy);
		}
		return getPagedNamedQuery("misBase::getBaseModLogList::query", pageNo, pageSize, values);
	}
	
	
	
	
}
