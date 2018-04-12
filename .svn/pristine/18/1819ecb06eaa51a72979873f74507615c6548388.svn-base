/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package org.my431.base.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.*;

/**
 * @author wangzhen
 * @version 1.0
 * @since 1.0
 */

//培训机构
@Entity
@Table(name = "BASE_3RD_INSTITUTION")
public class Base3rdInstitution implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "ORG_3RD_ID", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//培训结构ID
	private java.lang.String id;
		
	@Column(name = "ORG_3RD_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//培训机构代码
	private java.lang.String org3rdCode;
		
	@Column(name = "ORG_3RD_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//培训机构名字
	private java.lang.String org3rdName;
		
	@Column(name = "CONTACT_PERSON", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//联系人姓名
	private java.lang.String contactPerson;
		
	@Column(name = "CONTACT_PHONE", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//联系人电话
	private java.lang.String contactPhone;
		
	@Column(name = "CREATE_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//创建人ID
	private java.lang.String createUserId;
		
	@Column(name = "CREATE_DEPART_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//创建部门
	private java.lang.String createDepartId;
		
	@Column(name = "CREATE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//创建时间
	private java.util.Date createTime;
		
	@Column(name = "DEL_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//删除用户
	private java.lang.String delUserId;
		
	@Column(name = "DEL_FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//删除标识：0正常，1删除
	private Integer delFlag;
		
	@Column(name = "DEL_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//删除时间
	private java.util.Date delTime;
		
	@Column(name = "AUDIT_STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//审核状态
	private Integer auditStatus;
		
	@Column(name = "AUDIT_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//审核时间
	private java.util.Date auditTime;
		
	@Column(name = "AUDIT_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//审核人
	private java.lang.String auditUserId;
		
	@Column(name = "ORG_3RD_LEVEL", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//培训机构级别:来自属性表
	private java.lang.String org3rdLevel;



	

	
	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getOrg3rdCode() {
		return this.org3rdCode;
	}
	
	public void setOrg3rdCode(java.lang.String value) {
		this.org3rdCode = value;
	}
	
	public java.lang.String getOrg3rdName() {
		return this.org3rdName;
	}
	
	public void setOrg3rdName(java.lang.String value) {
		this.org3rdName = value;
	}
	
	public java.lang.String getContactPerson() {
		return this.contactPerson;
	}
	
	public void setContactPerson(java.lang.String value) {
		this.contactPerson = value;
	}
	
	public java.lang.String getContactPhone() {
		return this.contactPhone;
	}
	
	public void setContactPhone(java.lang.String value) {
		this.contactPhone = value;
	}
	
	public java.lang.String getCreateUserId() {
		return this.createUserId;
	}
	
	public void setCreateUserId(java.lang.String value) {
		this.createUserId = value;
	}
	
	public java.lang.String getCreateDepartId() {
		return this.createDepartId;
	}
	
	public void setCreateDepartId(java.lang.String value) {
		this.createDepartId = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.lang.String getDelUserId() {
		return this.delUserId;
	}
	
	public void setDelUserId(java.lang.String value) {
		this.delUserId = value;
	}
	
	public Integer getDelFlag() {
		return this.delFlag;
	}
	
	public void setDelFlag(Integer value) {
		this.delFlag = value;
	}
	
	public java.util.Date getDelTime() {
		return this.delTime;
	}
	
	public void setDelTime(java.util.Date value) {
		this.delTime = value;
	}
	
	public Integer getAuditStatus() {
		return this.auditStatus;
	}
	
	public void setAuditStatus(Integer value) {
		this.auditStatus = value;
	}
	
	public java.util.Date getAuditTime() {
		return this.auditTime;
	}
	
	public void setAuditTime(java.util.Date value) {
		this.auditTime = value;
	}
	
	public java.lang.String getAuditUserId() {
		return this.auditUserId;
	}
	
	public void setAuditUserId(java.lang.String value) {
		this.auditUserId = value;
	}
	
	public java.lang.String getOrg3rdLevel() {
		return this.org3rdLevel;
	}
	
	public void setOrg3rdLevel(java.lang.String value) {
		this.org3rdLevel = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Org3rdId",getId())
			.append("Org3rdCode",getOrg3rdCode())
			.append("Org3rdName",getOrg3rdName())
			.append("ContactPerson",getContactPerson())
			.append("ContactPhone",getContactPhone())
			.append("CreateUserId",getCreateUserId())
			.append("CreateDepartId",getCreateDepartId())
			.append("CreateTime",getCreateTime())
			.append("DelUserId",getDelUserId())
			.append("DelFlag",getDelFlag())
			.append("DelTime",getDelTime())
			.append("AuditStatus",getAuditStatus())
			.append("AuditTime",getAuditTime())
			.append("AuditUserId",getAuditUserId())
			.append("Org3rdLevel",getOrg3rdLevel())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Base3rdInstitution == false) return false;
		if(this == obj) return true;
		Base3rdInstitution other = (Base3rdInstitution)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

