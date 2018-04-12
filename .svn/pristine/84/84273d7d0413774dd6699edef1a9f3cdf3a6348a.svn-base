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

//BaseImportRecord
@Entity
@Table(name = "BASE_IMPORT_RECORD")
public class BaseImportRecord implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "R_ID", unique = true, nullable = false, insertable = true, updatable = true, length = 50)
	//rid
	private java.lang.String id;
		
	@Column(name = "PROJECT_FILE_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//projectFileId
	private java.lang.String projectFileId;
		
	@Column(name = "IMPORT_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//导入类型1增量，2替换
	private Integer importType;
		
	@Column(name = "IMPORT_STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//导入状态1成功0失败-1正在处理
	private Integer importStatus;
		
	@Column(name = "IMPORT_RESULT", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//导入结果
	private java.lang.String importResult;
		
	@Column(name = "UPLOAD_COUNT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//报表中数据条数
	private Integer uploadCount;
		
	@Column(name = "IMPORT_COUNT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//报表成功导入的数据条数
	private Integer importCount;
		
	@Column(name = "CRE_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//creUserId
	private java.lang.String creUserId;
		
	@Column(name = "CRE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//creTime
	private java.util.Date creTime;



	

	
	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getProjectFileId() {
		return this.projectFileId;
	}
	
	public void setProjectFileId(java.lang.String value) {
		this.projectFileId = value;
	}
	
	public Integer getImportType() {
		return this.importType;
	}
	
	public void setImportType(Integer value) {
		this.importType = value;
	}
	
	public Integer getImportStatus() {
		return this.importStatus;
	}
	
	public void setImportStatus(Integer value) {
		this.importStatus = value;
	}
	
	public java.lang.String getImportResult() {
		return this.importResult;
	}
	
	public void setImportResult(java.lang.String value) {
		this.importResult = value;
	}
	
	public Integer getUploadCount() {
		return this.uploadCount;
	}
	
	public void setUploadCount(Integer value) {
		this.uploadCount = value;
	}
	
	public Integer getImportCount() {
		return this.importCount;
	}
	
	public void setImportCount(Integer value) {
		this.importCount = value;
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
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Rid",getId())
			.append("ProjectFileId",getProjectFileId())
			.append("ImportType",getImportType())
			.append("ImportStatus",getImportStatus())
			.append("ImportResult",getImportResult())
			.append("UploadCount",getUploadCount())
			.append("ImportCount",getImportCount())
			.append("CreUserId",getCreUserId())
			.append("CreTime",getCreTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseImportRecord == false) return false;
		if(this == obj) return true;
		BaseImportRecord other = (BaseImportRecord)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

