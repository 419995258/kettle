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

//BaseToDo
@Entity
@Table(name = "BASE_TO_DO")
public class BaseToDo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "TODO_ID", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//待办业务ID
	private java.lang.String id;
		
	@Column(name = "TODO_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	//待办业务类型，来自属性表
	private java.lang.String todoType;
		
	@Column(name = "RESOURCE_PARAMETER", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//待办业务来源ID(参数)
	private java.lang.String resourceParameter;
		
	@Column(name = "REOURCE_URL", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	//待办业务来源URL
	private java.lang.String reourceUrl;
		
	@Column(name = "BELONG_TO_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//待办业务主人
	private java.lang.String belongToUserId;
		
	@Column(name = "BELONG_TO_PROVINCE_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//待办业务发到省
	private java.lang.String belongToProvinceId;
		
	@Column(name = "BELONG_TO_CITY_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//待办业务发到市
	private java.lang.String belongToCityId;
		
	@Column(name = "BELONG_TO_COUNTY_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//待办业务发到县
	private java.lang.String belongToCountyId;
		
	@Column(name = "BELONG_TO_SCHOOL_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//待办业务发到学校
	private java.lang.String belongToSchoolId;
		
	@Column(name = "BELONG_TO_INSTITUTION_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//待办业务发到培训机构
	private java.lang.String belongToInstitutionId;
		
	@Column(name = "BELONG_TO_DEPARTMENT_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	//待办业务达到部门类型
	private java.lang.String belongToDepartmentType;
		
	@Column(name = "CRE_USER", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//创建用户ID
	private java.lang.String creUser;
		
	@Column(name = "CRE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//创建时间
	private java.util.Date creTime;
		
	@Column(name = "EXPIRED_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//待办业务过期时间
	private java.util.Date expiredTime;
		
	@Column(name = "TODO_DEAL_STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//待办业务处理状态
	private Integer todoDealStatus;
		
	@Column(name = "COME_FROM_PROVINCE_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//待办业务来自省
	private java.lang.String comeFromProvinceId;
		
	@Column(name = "COME_FROM_CITY_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//待办业务来自市
	private java.lang.String comeFromCityId;
		
	@Column(name = "COME_FROM_COUNTY_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//待办业务来自县
	private java.lang.String comeFromCountyId;
		
	@Column(name = "COME_FROM_SCHOOL_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//待办业务来自学校
	private java.lang.String comeFromSchoolId;
		
	@Column(name = "COME_FROM_INSTITUTION_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//待办业务来自培训机构
	private java.lang.String comeFromInstitutionId;
		
	@Column(name = "COME_DEPARTMENT_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	//待办业务来自部门类型
	private java.lang.String comeDepartmentType;
		
	@Column(name = "DELETE_FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//删除标记
	private Integer deleteFlag;
		
	@Column(name = "DELETE_USER", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//删除用户
	private java.lang.String deleteUser;
		
	@Column(name = "DELETE_COMMENT", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	//删除备注
	private java.lang.String deleteComment;
		
	@Column(name = "DEAL_LAST_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//最后处理时间
	private java.util.Date dealLastTime;
		
	@Column(name = "DEAL_LAST_USER", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//最后处理人
	private java.lang.String dealLastUser;



	

	
	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getTodoType() {
		return this.todoType;
	}
	
	public void setTodoType(java.lang.String value) {
		this.todoType = value;
	}
	
	public java.lang.String getResourceParameter() {
		return this.resourceParameter;
	}
	
	public void setResourceParameter(java.lang.String value) {
		this.resourceParameter = value;
	}
	
	public java.lang.String getReourceUrl() {
		return this.reourceUrl;
	}
	
	public void setReourceUrl(java.lang.String value) {
		this.reourceUrl = value;
	}
	
	public java.lang.String getBelongToUserId() {
		return this.belongToUserId;
	}
	
	public void setBelongToUserId(java.lang.String value) {
		this.belongToUserId = value;
	}
	
	public java.lang.String getBelongToProvinceId() {
		return this.belongToProvinceId;
	}
	
	public void setBelongToProvinceId(java.lang.String value) {
		this.belongToProvinceId = value;
	}
	
	public java.lang.String getBelongToCityId() {
		return this.belongToCityId;
	}
	
	public void setBelongToCityId(java.lang.String value) {
		this.belongToCityId = value;
	}
	
	public java.lang.String getBelongToCountyId() {
		return this.belongToCountyId;
	}
	
	public void setBelongToCountyId(java.lang.String value) {
		this.belongToCountyId = value;
	}
	
	public java.lang.String getBelongToSchoolId() {
		return this.belongToSchoolId;
	}
	
	public void setBelongToSchoolId(java.lang.String value) {
		this.belongToSchoolId = value;
	}
	
	public java.lang.String getBelongToInstitutionId() {
		return this.belongToInstitutionId;
	}
	
	public void setBelongToInstitutionId(java.lang.String value) {
		this.belongToInstitutionId = value;
	}
	
	public java.lang.String getBelongToDepartmentType() {
		return this.belongToDepartmentType;
	}
	
	public void setBelongToDepartmentType(java.lang.String value) {
		this.belongToDepartmentType = value;
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
	
	public java.util.Date getExpiredTime() {
		return this.expiredTime;
	}
	
	public void setExpiredTime(java.util.Date value) {
		this.expiredTime = value;
	}
	
	public Integer getTodoDealStatus() {
		return this.todoDealStatus;
	}
	
	public void setTodoDealStatus(Integer value) {
		this.todoDealStatus = value;
	}
	
	public java.lang.String getComeFromProvinceId() {
		return this.comeFromProvinceId;
	}
	
	public void setComeFromProvinceId(java.lang.String value) {
		this.comeFromProvinceId = value;
	}
	
	public java.lang.String getComeFromCityId() {
		return this.comeFromCityId;
	}
	
	public void setComeFromCityId(java.lang.String value) {
		this.comeFromCityId = value;
	}
	
	public java.lang.String getComeFromCountyId() {
		return this.comeFromCountyId;
	}
	
	public void setComeFromCountyId(java.lang.String value) {
		this.comeFromCountyId = value;
	}
	
	public java.lang.String getComeFromSchoolId() {
		return this.comeFromSchoolId;
	}
	
	public void setComeFromSchoolId(java.lang.String value) {
		this.comeFromSchoolId = value;
	}
	
	public java.lang.String getComeFromInstitutionId() {
		return this.comeFromInstitutionId;
	}
	
	public void setComeFromInstitutionId(java.lang.String value) {
		this.comeFromInstitutionId = value;
	}
	
	public java.lang.String getComeDepartmentType() {
		return this.comeDepartmentType;
	}
	
	public void setComeDepartmentType(java.lang.String value) {
		this.comeDepartmentType = value;
	}
	
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}
	
	public void setDeleteFlag(Integer value) {
		this.deleteFlag = value;
	}
	
	public java.lang.String getDeleteUser() {
		return this.deleteUser;
	}
	
	public void setDeleteUser(java.lang.String value) {
		this.deleteUser = value;
	}
	
	public java.lang.String getDeleteComment() {
		return this.deleteComment;
	}
	
	public void setDeleteComment(java.lang.String value) {
		this.deleteComment = value;
	}
	
	public java.util.Date getDealLastTime() {
		return this.dealLastTime;
	}
	
	public void setDealLastTime(java.util.Date value) {
		this.dealLastTime = value;
	}
	
	public java.lang.String getDealLastUser() {
		return this.dealLastUser;
	}
	
	public void setDealLastUser(java.lang.String value) {
		this.dealLastUser = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("TodoId",getId())
			.append("TodoType",getTodoType())
			.append("ResourceParameter",getResourceParameter())
			.append("ReourceUrl",getReourceUrl())
			.append("BelongToUserId",getBelongToUserId())
			.append("BelongToProvinceId",getBelongToProvinceId())
			.append("BelongToCityId",getBelongToCityId())
			.append("BelongToCountyId",getBelongToCountyId())
			.append("BelongToSchoolId",getBelongToSchoolId())
			.append("BelongToInstitutionId",getBelongToInstitutionId())
			.append("BelongToDepartmentType",getBelongToDepartmentType())
			.append("CreUser",getCreUser())
			.append("CreTime",getCreTime())
			.append("ExpiredTime",getExpiredTime())
			.append("TodoDealStatus",getTodoDealStatus())
			.append("ComeFromProvinceId",getComeFromProvinceId())
			.append("ComeFromCityId",getComeFromCityId())
			.append("ComeFromCountyId",getComeFromCountyId())
			.append("ComeFromSchoolId",getComeFromSchoolId())
			.append("ComeFromInstitutionId",getComeFromInstitutionId())
			.append("ComeDepartmentType",getComeDepartmentType())
			.append("DeleteFlag",getDeleteFlag())
			.append("DeleteUser",getDeleteUser())
			.append("DeleteComment",getDeleteComment())
			.append("DealLastTime",getDealLastTime())
			.append("DealLastUser",getDealLastUser())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseToDo == false) return false;
		if(this == obj) return true;
		BaseToDo other = (BaseToDo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

