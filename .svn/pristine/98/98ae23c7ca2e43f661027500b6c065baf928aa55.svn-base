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

//项目名额分配
@Entity
@Table(name = "BASE_PRO_AMOUNT_DISTRIBUTE")
public class BaseProAmountDistribute implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "PROAD_ID", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//proadId
	private java.lang.String id;
		
	@Column(name = "PRO_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 40)
	//名额来自项目ID
	private java.lang.String proId;
		
	@Column(name = "LOCK_STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//记录锁
	private Integer lockStatus;
		
	@Column(name = "DEPARTMENT_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//部门类型，国家、省、市、县、学校,用缓存[项目级别]
	private java.lang.String departmentType;
		
	@Column(name = "DEPARTMENT_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//部门ID
	private java.lang.String departmentId;
		
	@Column(name = "NODE_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//是否叶子(学校级是叶子)
	private Integer nodeType;
		
	@Column(name = "TOTAL_AMOUNT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//分配总名额
	private Integer totalAmount;
		
	@Column(name = "PARENT_PROAD_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//父账号
	private java.lang.String parentProadId;
		
	@Column(name = "ROOT_PROAD_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//根账户ID
	private java.lang.String rootProadId;
		
	@Column(name = "ROOT_PROAD_LEVEL", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//根名额分配记录级别-国家，省，市，县,学校
	private java.lang.String rootProadLevel;
		
	@Column(name = "TOTAL_AMOUNT_SIGNED_UP", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//教师报名人数合计(教师报名/取消报名时用)
	private Integer totalAmountSignedUp;
		
	@Column(name = "THIS_GRADE_AMOUNT_REMAINED", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//本级剩余名额
	private Integer thisGradeAmountRemained;
		
	@Column(name = "DEL_FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//0正常，1删除
	private Integer delFlag;
		
	@Column(name = "CRE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//创建时间
	private java.util.Date creTime;
		
	@Column(name = "CRE_USER", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//创建用户
	private java.lang.String creUser;
		
	@Column(name = "MOD_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//修改时间
	private java.util.Date modTime;
		
	@Column(name = "MOD_USER", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//修改用户
	private java.util.Date modUser;
		
	@Column(name = "PROVINCE_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//省ID
	private java.lang.String provinceId;
		
	@Column(name = "CITY_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//市ID
	private java.lang.String cityId;
		
	@Column(name = "COUNTY_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//县ID
	private java.lang.String countyId;
		
	@Column(name = "SCHOOL_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//学校ID
	private java.lang.String schoolId;
		
	@Column(name = "T_YEAR", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//年份
	private java.lang.String tyear;
		
	@Column(name = "PROAD_COMMENT", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	//备注
	private java.lang.String proadComment;



	

	
	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getProId() {
		return this.proId;
	}
	
	public void setProId(java.lang.String value) {
		this.proId = value;
	}
	
	public Integer getLockStatus() {
		return this.lockStatus;
	}
	
	public void setLockStatus(Integer value) {
		this.lockStatus = value;
	}
	
	public java.lang.String getDepartmentType() {
		return this.departmentType;
	}
	
	public void setDepartmentType(java.lang.String value) {
		this.departmentType = value;
	}
	
	public java.lang.String getDepartmentId() {
		return this.departmentId;
	}
	
	public void setDepartmentId(java.lang.String value) {
		this.departmentId = value;
	}
	
	public Integer getNodeType() {
		return this.nodeType;
	}
	
	public void setNodeType(Integer value) {
		this.nodeType = value;
	}
	
	public Integer getTotalAmount() {
		return this.totalAmount;
	}
	
	public void setTotalAmount(Integer value) {
		this.totalAmount = value;
	}
	
	public java.lang.String getParentProadId() {
		return this.parentProadId;
	}
	
	public void setParentProadId(java.lang.String value) {
		this.parentProadId = value;
	}
	
	public java.lang.String getRootProadId() {
		return this.rootProadId;
	}
	
	public void setRootProadId(java.lang.String value) {
		this.rootProadId = value;
	}
	
	public java.lang.String getRootProadLevel() {
		return this.rootProadLevel;
	}
	
	public void setRootProadLevel(java.lang.String value) {
		this.rootProadLevel = value;
	}
	
	public Integer getTotalAmountSignedUp() {
		return this.totalAmountSignedUp;
	}
	
	public void setTotalAmountSignedUp(Integer value) {
		this.totalAmountSignedUp = value;
	}
	
	public Integer getThisGradeAmountRemained() {
		return this.thisGradeAmountRemained;
	}
	
	public void setThisGradeAmountRemained(Integer value) {
		this.thisGradeAmountRemained = value;
	}
	
	public Integer getDelFlag() {
		return this.delFlag;
	}
	
	public void setDelFlag(Integer value) {
		this.delFlag = value;
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
	
	public java.util.Date getModUser() {
		return this.modUser;
	}
	
	public void setModUser(java.util.Date value) {
		this.modUser = value;
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
	
	public java.lang.String getTyear() {
		return this.tyear;
	}
	
	public void setTyear(java.lang.String value) {
		this.tyear = value;
	}
	
	public java.lang.String getProadComment() {
		return proadComment;
	}

	public void setProadComment(java.lang.String proadComment) {
		this.proadComment = proadComment;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ProadId",getId())
			.append("ProId",getProId())
			.append("LockStatus",getLockStatus())
			.append("DepartmentType",getDepartmentType())
			.append("DepartmentId",getDepartmentId())
			.append("NodeType",getNodeType())
			.append("TotalAmount",getTotalAmount())
			.append("ParentProadId",getParentProadId())
			.append("RootProadId",getRootProadId())
			.append("RootProadLevel",getRootProadLevel())
			.append("TotalAmountSignedUp",getTotalAmountSignedUp())
			.append("ThisGradeAmountRemained",getThisGradeAmountRemained())
			.append("DelFlag",getDelFlag())
			.append("CreTime",getCreTime())
			.append("CreUser",getCreUser())
			.append("ModTime",getModTime())
			.append("ModUser",getModUser())
			.append("ProvinceId",getProvinceId())
			.append("CityId",getCityId())
			.append("CountyId",getCountyId())
			.append("SchoolId",getSchoolId())
			.append("Tyear",getTyear())
			.append("ProadComment",getProadComment())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseProAmountDistribute == false) return false;
		if(this == obj) return true;
		BaseProAmountDistribute other = (BaseProAmountDistribute)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

