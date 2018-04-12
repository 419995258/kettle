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

//修改记录
@Entity
@Table(name = "BASE_MOD_LOG")
public class BaseModLog implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "MOD_LOG_ID", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//修改日志ID
	private java.lang.String id;
		
	@Column(name = "SHEET_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	//修改表名称
	private java.lang.String sheetName;
		
	@Column(name = "PRIM_KEY", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//修改行主键
	private java.lang.String primKey;
		
	@Column(name = "FIELDS_GROUP_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	//修改多个字段组ID
	private java.lang.String fieldsGroupId;
		
	@Column(name = "FIELD_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//修改表字段名
	private java.lang.String fieldName;
		
	@Column(name = "FIELD_DATA_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//修改表字段数据类型
	private java.lang.String fieldDataType;
		
	@Column(name = "FIELD_OLD_VALUE", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	//修改前字段值
	private java.lang.String fieldOldValue;
		
	@Column(name = "FIELD_NEW_VALUE", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	//修改后字段值
	private java.lang.String fieldNewValue;
		
	@Column(name = "MOD_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//修改人ID
	private java.lang.String modUserId;
		
	@Column(name = "MOD_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//修改时间
	private java.util.Date modTime;
		
	@Column(name = "AUDIT_STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//审核状态,未审核0，已审核1 不需要审核-1
	private Integer auditStatus;
		
	@Column(name = "AUDIT_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//审核时间
	private java.util.Date auditTime;
		
	@Column(name = "AUDIT_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//审核人ID
	private java.lang.String auditUserId;
		
	@Column(name = "AUDIT_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//审核类型:新增／修改／删除，此表中都是修改
	private java.lang.String auditType;
		
	@Column(name = "DEL_FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//删除标识
	private Integer delFlag;
		
	@Column(name = "DEL_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//删除时间
	private java.util.Date delTime;
		
	@Column(name = "DEL_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//删除人ID
	private java.lang.String delUserId;
		
	@Column(name = "PROVINCE_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//provinceId
	private java.lang.String provinceId;
		
	@Column(name = "CITY_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//cityId
	private java.lang.String cityId;
		
	@Column(name = "COUNTY_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//countyId
	private java.lang.String countyId;
		
	@Column(name = "SCHOOL_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//schoolId
	private java.lang.String schoolId;



	

	
	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getSheetName() {
		return this.sheetName;
	}
	
	public void setSheetName(java.lang.String value) {
		this.sheetName = value;
	}
	
	public java.lang.String getPrimKey() {
		return this.primKey;
	}
	
	public void setPrimKey(java.lang.String value) {
		this.primKey = value;
	}
	
	public java.lang.String getFieldsGroupId() {
		return this.fieldsGroupId;
	}
	
	public void setFieldsGroupId(java.lang.String value) {
		this.fieldsGroupId = value;
	}
	
	public java.lang.String getFieldName() {
		return this.fieldName;
	}
	
	public void setFieldName(java.lang.String value) {
		this.fieldName = value;
	}
	
	public java.lang.String getFieldDataType() {
		return this.fieldDataType;
	}
	
	public void setFieldDataType(java.lang.String value) {
		this.fieldDataType = value;
	}
	
	public java.lang.String getFieldOldValue() {
		return this.fieldOldValue;
	}
	
	public void setFieldOldValue(java.lang.String value) {
		this.fieldOldValue = value;
	}
	
	public java.lang.String getFieldNewValue() {
		return this.fieldNewValue;
	}
	
	public void setFieldNewValue(java.lang.String value) {
		this.fieldNewValue = value;
	}
	
	public java.lang.String getModUserId() {
		return this.modUserId;
	}
	
	public void setModUserId(java.lang.String value) {
		this.modUserId = value;
	}
	
	public java.util.Date getModTime() {
		return this.modTime;
	}
	
	public void setModTime(java.util.Date value) {
		this.modTime = value;
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
	
	public java.lang.String getAuditType() {
		return this.auditType;
	}
	
	public void setAuditType(java.lang.String value) {
		this.auditType = value;
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
	
	public java.lang.String getDelUserId() {
		return this.delUserId;
	}
	
	public void setDelUserId(java.lang.String value) {
		this.delUserId = value;
	}
	
	public java.lang.String getProvinceId() {
		return this.provinceId;
	}
	
	public void setProvinceId(java.lang.String value) {
		this.provinceId = value;
	}
	
	public java.lang.String getCityId() {
		return this.cityId;
	}
	
	public void setCityId(java.lang.String value) {
		this.cityId = value;
	}
	
	public java.lang.String getCountyId() {
		return this.countyId;
	}
	
	public void setCountyId(java.lang.String value) {
		this.countyId = value;
	}
	
	public java.lang.String getSchoolId() {
		return this.schoolId;
	}
	
	public void setSchoolId(java.lang.String value) {
		this.schoolId = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ModLogId",getId())
			.append("SheetName",getSheetName())
			.append("PrimKey",getPrimKey())
			.append("FieldsGroupId",getFieldsGroupId())
			.append("FieldName",getFieldName())
			.append("FieldDataType",getFieldDataType())
			.append("FieldOldValue",getFieldOldValue())
			.append("FieldNewValue",getFieldNewValue())
			.append("ModUserId",getModUserId())
			.append("ModTime",getModTime())
			.append("AuditStatus",getAuditStatus())
			.append("AuditTime",getAuditTime())
			.append("AuditUserId",getAuditUserId())
			.append("AuditType",getAuditType())
			.append("DelFlag",getDelFlag())
			.append("DelTime",getDelTime())
			.append("DelUserId",getDelUserId())
			.append("ProvinceId",getProvinceId())
			.append("CityId",getCityId())
			.append("CountyId",getCountyId())
			.append("SchoolId",getSchoolId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseModLog == false) return false;
		if(this == obj) return true;
		BaseModLog other = (BaseModLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

