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

//字段映射
@Entity
@Table(name = "BASE_USER_SHEET_MAP")
public class BaseUserSheetMap implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "SH_COL_ID", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//shColId
	private java.lang.String id;
		
	@Column(name = "SHEET_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	//数据库表名
	private java.lang.String sheetName;
		
	@Column(name = "FIELD_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	//数据库表用字段名
	private java.lang.String fieldName;
		
	@Column(name = "FIELD_NAME_C", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	//字段中文
	private java.lang.String fieldNameC;
		
	@Column(name = "FIELD_NAME_DISP", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//字段页面显示
	private java.lang.String fieldNameDisp;
		
	@Column(name = "LAST_MOD_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//最后一次修改时间
	private java.util.Date lastModTime;
		
	@Column(name = "LAST_MOD_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//最后一次修改人ID
	private java.lang.String lastModUserId;
		
	@Column(name = "VALID_STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//字段有效状态:0 有效；1 删除；2 禁用
	private Double validStatus;
		
	@Column(name = "IS_MONITOR_FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//监控修改标志
	private Double isMonitorFlag;
		
	@Column(name = "IS_MOD_NEED_AUDIT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//字段修改是否需要审核 如果需要则修改时只增加修改记录，审核时才修改，如果不需要，则修改时既记录又修改"
	private Double isModNeedAudit;



	

	
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
	
	public java.lang.String getFieldNameDisp() {
		return this.fieldNameDisp;
	}
	
	public void setFieldNameDisp(java.lang.String value) {
		this.fieldNameDisp = value;
	}
	
	public java.util.Date getLastModTime() {
		return this.lastModTime;
	}
	
	public void setLastModTime(java.util.Date value) {
		this.lastModTime = value;
	}
	
	public java.lang.String getLastModUserId() {
		return this.lastModUserId;
	}
	
	public void setLastModUserId(java.lang.String value) {
		this.lastModUserId = value;
	}
	
	public Double getValidStatus() {
		return this.validStatus;
	}
	
	public void setValidStatus(Double value) {
		this.validStatus = value;
	}
	
	public Double getIsMonitorFlag() {
		return this.isMonitorFlag;
	}
	
	public void setIsMonitorFlag(Double value) {
		this.isMonitorFlag = value;
	}
	
	public Double getIsModNeedAudit() {
		return this.isModNeedAudit;
	}
	
	public void setIsModNeedAudit(Double value) {
		this.isModNeedAudit = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ShColId",getId())
			.append("SheetName",getSheetName())
			.append("FieldName",getFieldName())
			.append("FieldNameC",getFieldNameC())
			.append("FieldNameDisp",getFieldNameDisp())
			.append("LastModTime",getLastModTime())
			.append("LastModUserId",getLastModUserId())
			.append("ValidStatus",getValidStatus())
			.append("IsMonitorFlag",getIsMonitorFlag())
			.append("IsModNeedAudit",getIsModNeedAudit())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseUserSheetMap == false) return false;
		if(this == obj) return true;
		BaseUserSheetMap other = (BaseUserSheetMap)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

