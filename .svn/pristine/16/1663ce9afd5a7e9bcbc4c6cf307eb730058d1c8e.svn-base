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

//需要记录修改字段
@Entity
@Table(name = "BASE_ND_MOD_FIELD")
public class BaseNdModField implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "NMF_ID", unique = true, nullable = false, insertable = true, updatable = true, length = 50)
	//需要记录修改字段ID
	private java.lang.String id;
		
	@Column(name = "SHEET_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//表名
	private java.lang.String sheetName;
		
	@Column(name = "SHEET_NAME_C", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	//表中文名
	private java.lang.String sheetNameC;
		
	@Column(name = "FIELD_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//字段名
	private java.lang.String fieldName;
		
	@Column(name = "FIELD_NAME_C", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	//字段中文名
	private java.lang.String fieldNameC;
		
	@Column(name = "IS_NEED_AUDIT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//字段修改是否需要审批
	private Integer isNeedAudit;
		
	@Column(name = "CRE_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//创建用户ID
	private java.lang.String creUserId;
		
	@Column(name = "CRE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//创建用户时间
	private java.util.Date creTime;
		
	@Column(name = "MOD_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//修改用户ID
	private java.lang.String modUserId;
		
	@Column(name = "MOD_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//修改用户时间
	private java.util.Date modTime;
		
	@Column(name = "DELETE_FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//删除标记
	private Integer deleteFlag;
		
	@Column(name = "DELETE_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//删除用户ID
	private java.lang.String deleteUserId;
		
	@Column(name = "DELETE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//删除时间
	private java.util.Date deleteTime;



	

	
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
	
	public java.lang.String getSheetNameC() {
		return this.sheetNameC;
	}
	
	public void setSheetNameC(java.lang.String value) {
		this.sheetNameC = value;
	}
	
	public java.lang.String getFieldName() {
		return this.fieldName;
	}
	
	public void setFieldName(java.lang.String value) {
		this.fieldName = value;
	}
	
	public java.lang.String getFieldNameC() {
		return this.fieldNameC;
	}
	
	public void setFieldNameC(java.lang.String value) {
		this.fieldNameC = value;
	}
	
	public Integer getIsNeedAudit() {
		return this.isNeedAudit;
	}
	
	public void setIsNeedAudit(Integer value) {
		this.isNeedAudit = value;
	}
	
	public java.lang.String getCreUserId() {
		return this.creUserId;
	}
	
	public void setCreUserId(java.lang.String value) {
		this.creUserId = value;
	}
	
	public java.util.Date getCreTime() {
		return this.creTime;
	}
	
	public void setCreTime(java.util.Date value) {
		this.creTime = value;
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
	
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}
	
	public void setDeleteFlag(Integer value) {
		this.deleteFlag = value;
	}
	
	public java.lang.String getDeleteUserId() {
		return this.deleteUserId;
	}
	
	public void setDeleteUserId(java.lang.String value) {
		this.deleteUserId = value;
	}
	
	public java.util.Date getDeleteTime() {
		return this.deleteTime;
	}
	
	public void setDeleteTime(java.util.Date value) {
		this.deleteTime = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("NmfId",getId())
			.append("SheetName",getSheetName())
			.append("SheetNameC",getSheetNameC())
			.append("FieldName",getFieldName())
			.append("FieldNameC",getFieldNameC())
			.append("IsNeedAudit",getIsNeedAudit())
			.append("CreUserId",getCreUserId())
			.append("CreTime",getCreTime())
			.append("ModUserId",getModUserId())
			.append("ModTime",getModTime())
			.append("DeleteFlag",getDeleteFlag())
			.append("DeleteUserId",getDeleteUserId())
			.append("DeleteTime",getDeleteTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseNdModField == false) return false;
		if(this == obj) return true;
		BaseNdModField other = (BaseNdModField)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

