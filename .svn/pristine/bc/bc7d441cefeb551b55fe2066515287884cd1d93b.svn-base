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
import org.my431.base.model.Base3rdInstitution;
import org.my431.platform.dao.extend.HibernateXsqlBuilderQueryDao;
import org.my431.platform.dao.support.Page;
import org.springframework.stereotype.Repository;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import javacommon.xsqlbuilder.XsqlBuilder;
import javacommon.xsqlbuilder.XsqlBuilder.XsqlFilterResult;

@Repository
public class Base3rdInstitutionManager extends HibernateXsqlBuilderQueryDao<Base3rdInstitution>{

	public Class getEntityClass() {
		return Base3rdInstitution.class;
	}
	
	public Page findPage(Base3rdInstitution query, int pageSize, int pageNo) {
        //XsqlBuilder syntax,please see http://code.google.com/p/rapid-xsqlbuilder
        // [column]为字符串拼接, {column}为使用占位符. [column]为使用字符串拼接,如username='[username]',偷懒时可以使用字符串拼接 
        // [column] 为PageRequest的属性
		String sql = "select t from Base3rdInstitution t where 1=1 "
			  	+ "/~ and t.org3rdCode = {org3rdCode} ~/"
			  	+ "/~ and t.org3rdName = {org3rdName} ~/"
			  	+ "/~ and t.contactPerson = {contactPerson} ~/"
			  	+ "/~ and t.contactPhone = {contactPhone} ~/"
			  	+ "/~ and t.createUserId = {createUserId} ~/"
			  	+ "/~ and t.createDepartId = {createDepartId} ~/"
				+ "/~ and t.createTime >= {createTimeBegin} ~/"
				+ "/~ and t.createTime <= {createTimeEnd} ~/"
			  	+ "/~ and t.delUserId = {delUserId} ~/"
			  	+ "/~ and t.delFlag = {delFlag} ~/"
				+ "/~ and t.delTime >= {delTimeBegin} ~/"
				+ "/~ and t.delTime <= {delTimeEnd} ~/"
			  	+ "/~ and t.auditStatus = {auditStatus} ~/"
				+ "/~ and t.auditTime >= {auditTimeBegin} ~/"
				+ "/~ and t.auditTime <= {auditTimeEnd} ~/"
			  	+ "/~ and t.auditUserId = {auditUserId} ~/"
			  	+ "/~ and t.org3rdLevel = {org3rdLevel} ~/"
				+ "/~ order by [sortColumns] ~/";

		Map filters = new HashMap();  
				
		XsqlBuilder xsqlBuilder=new XsqlBuilder();
        if(isNotEmpty(query.getOrg3rdCode())) {
            filters.put("org3rdCode", query.getOrg3rdCode()); 
        }
        if(isNotEmpty(query.getOrg3rdName())) {
            filters.put("org3rdName", query.getOrg3rdName()); 
        }
        if(isNotEmpty(query.getContactPerson())) {
            filters.put("contactPerson", query.getContactPerson()); 
        }
        if(isNotEmpty(query.getContactPhone())) {
            filters.put("contactPhone", query.getContactPhone()); 
        }
        if(isNotEmpty(query.getCreateUserId())) {
            filters.put("createUserId", query.getCreateUserId()); 
        }
        if(isNotEmpty(query.getCreateDepartId())) {
            filters.put("createDepartId", query.getCreateDepartId()); 
        }
        if(isNotEmpty(query.getCreateTime())) {
            filters.put("createTime", query.getCreateTime()); 
        }
        if(isNotEmpty(query.getDelUserId())) {
            filters.put("delUserId", query.getDelUserId()); 
        }
        if(isNotEmpty(query.getDelFlag())) {
            filters.put("delFlag", query.getDelFlag()); 
        }
        if(isNotEmpty(query.getDelTime())) {
            filters.put("delTime", query.getDelTime()); 
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
        if(isNotEmpty(query.getOrg3rdLevel())) {
            filters.put("org3rdLevel", query.getOrg3rdLevel()); 
        }
        
        XsqlFilterResult result = xsqlBuilder.generateHql(sql,filters); 
        
		return pageQuery(result.getXsql(), pageNo, pageSize,result.getAcceptedFilters());
	}
	/**
	 * 
	* @Description: 查询未删除的培训机构  
	* @author hyl     
	* @date 2017-6-7 上午11:02:36  
	* @version V1.0 
	* @author user
	 */
	public List<Base3rdInstitution> getList() {
		String hql = " from Base3rdInstitution t where t.delFlag =:delFlag";
		Session session = getSession();
		Query query = session.createQuery(hql);
		query.setParameter("delFlag", 0);
		this.releaseSession(session);
		return query.list();
	}

}
