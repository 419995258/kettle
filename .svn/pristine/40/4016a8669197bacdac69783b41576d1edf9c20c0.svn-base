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

//【用户角色】 区域管理员 区域视察员 学校管理员 设备管理员 教务管理员 校长 学科教师 学生 家长 系统管理员
@Entity
@Table(name = "BASE_ROLE")
public class BaseRole implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "ROLE_ID", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//roleId
	private java.lang.String id;
		
	@Column(name = "ROLE_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//角色名称
	private java.lang.String roleName;
		
	@Column(name = "ROLE_CODE", unique = true, nullable = true, insertable = true, updatable = true, length = 200)
	//角色代码
	private java.lang.String roleCode;
		
	@Column(name = "ROLE_DESC", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
	//角色描述
	private String roleDesc;
		
	@Column(name = "ROLE_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//角色类型，以后也许用得上
	private java.lang.String roleType;
		
	@Column(name = "DEFAULT_URL", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	//该身份登陆后的默认url
	private java.lang.String defaultUrl;
		
	@Column(name = "TRANSFORM_ROLE", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	//登陆后可以互相穿梭的几种角色，除了本角色外，其他角色代码用逗号分开
	private java.lang.String transformRole;
		
	@Column(name = "CRE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//creTime
	private java.util.Date creTime;
		
	@Column(name = "CRE_USER", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	//creUser
	private java.lang.String creUser;
		
	@Column(name = "MOD_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//modTime
	private java.util.Date modTime;
		
	@Column(name = "MOD_USER", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	//modUser
	private java.lang.String modUser;


	public BaseRole(){
	}

	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getRoleName() {
		return this.roleName;
	}
	
	public void setRoleName(java.lang.String value) {
		this.roleName = value;
	}
	
	public java.lang.String getRoleCode() {
		return this.roleCode;
	}
	
	public void setRoleCode(java.lang.String value) {
		this.roleCode = value;
	}
	
	public String getRoleDesc() {
		return this.roleDesc;
	}
	
	public void setRoleDesc(String value) {
		this.roleDesc = value;
	}
	
	public java.lang.String getRoleType() {
		return this.roleType;
	}
	
	public void setRoleType(java.lang.String value) {
		this.roleType = value;
	}
	
	public java.lang.String getDefaultUrl() {
		return this.defaultUrl;
	}
	
	public void setDefaultUrl(java.lang.String value) {
		this.defaultUrl = value;
	}
	
	public java.lang.String getTransformRole() {
		return this.transformRole;
	}
	
	public void setTransformRole(java.lang.String value) {
		this.transformRole = value;
	}
	
	public java.util.Date getCreTime() {
		return this.creTime;
	}
	
	public void setCreTime(java.util.Date value) {
		this.creTime = value;
	}
	
	public java.lang.String getCreUser() {
		return this.creUser;
	}
	
	public void setCreUser(java.lang.String value) {
		this.creUser = value;
	}
	
	public java.util.Date getModTime() {
		return this.modTime;
	}
	
	public void setModTime(java.util.Date value) {
		this.modTime = value;
	}
	
	public java.lang.String getModUser() {
		return this.modUser;
	}
	
	public void setModUser(java.lang.String value) {
		this.modUser = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("RoleId",getId())
			.append("RoleName",getRoleName())
			.append("RoleCode",getRoleCode())
			.append("RoleDesc",getRoleDesc())
			.append("RoleType",getRoleType())
			.append("DefaultUrl",getDefaultUrl())
			.append("TransformRole",getTransformRole())
			.append("CreTime",getCreTime())
			.append("CreUser",getCreUser())
			.append("ModTime",getModTime())
			.append("ModUser",getModUser())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseRole == false) return false;
		if(this == obj) return true;
		BaseRole other = (BaseRole)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

