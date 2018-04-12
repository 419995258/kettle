/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2015
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

//BaseModule
@Entity
@Table(name = "BASE_MODULE")
public class BaseModule implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "MODULE_ID", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//moduleId
	private java.lang.String id;
		
	@Column(name = "CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//code
	private java.lang.String code;
		
	@Column(name = "PARENT_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//parentCode
	private java.lang.String parentCode;
		
	@Column(name = "MODULE_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//模块名
	private java.lang.String moduleName;
		
	@Column(name = "MODULE_DESC", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	//说明
	private java.lang.String moduleDesc;
		
	@Column(name = "NODE_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	//nodeType
	private java.lang.String nodeType;
		
	@Column(name = "CRE_USER", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//creUser
	private java.lang.String creUser;
		
	@Column(name = "CRE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//creTime
	private java.util.Date creTime;
		
	@Column(name = "MOD_USER", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//modUser
	private java.lang.String modUser;
		
	@Column(name = "MOD_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//modTime
	private java.util.Date modTime;


	public BaseModule(){
	}

	
	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getCode() {
		return this.code;
	}
	
	public void setCode(java.lang.String value) {
		this.code = value;
	}
	
	public java.lang.String getParentCode() {
		return this.parentCode;
	}
	
	public void setParentCode(java.lang.String value) {
		this.parentCode = value;
	}
	
	public java.lang.String getModuleName() {
		return this.moduleName;
	}
	
	public void setModuleName(java.lang.String value) {
		this.moduleName = value;
	}
	
	public java.lang.String getModuleDesc() {
		return this.moduleDesc;
	}
	
	public void setModuleDesc(java.lang.String value) {
		this.moduleDesc = value;
	}
	
	public java.lang.String getNodeType() {
		return this.nodeType;
	}
	
	public void setNodeType(java.lang.String value) {
		this.nodeType = value;
	}
	
	public java.lang.String getCreUser() {
		return this.creUser;
	}
	
	public void setCreUser(java.lang.String value) {
		this.creUser = value;
	}
	
	public java.util.Date getCreTime() {
		return this.creTime;
	}
	
	public void setCreTime(java.util.Date value) {
		this.creTime = value;
	}
	
	public java.lang.String getModUser() {
		return this.modUser;
	}
	
	public void setModUser(java.lang.String value) {
		this.modUser = value;
	}
	
	public java.util.Date getModTime() {
		return this.modTime;
	}
	
	public void setModTime(java.util.Date value) {
		this.modTime = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ModuleId",getId())
			.append("Code",getCode())
			.append("ParentCode",getParentCode())
			.append("ModuleName",getModuleName())
			.append("ModuleDesc",getModuleDesc())
			.append("NodeType",getNodeType())
			.append("CreUser",getCreUser())
			.append("CreTime",getCreTime())
			.append("ModUser",getModUser())
			.append("ModTime",getModTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseModule == false) return false;
		if(this == obj) return true;
		BaseModule other = (BaseModule)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

