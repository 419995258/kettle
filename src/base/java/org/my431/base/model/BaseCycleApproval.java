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

//周期核定
@Entity
@Table(name = "BASE_CYCLE_APPROVAL")
public class BaseCycleApproval implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "CAID", unique = true, nullable = false, insertable = true, updatable = true, length = 50)
	//周期核定ID
	private java.lang.String id;
		
	@Column(name = "TEACHER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//教师ID
	private java.lang.String teacherId;
		
	@Column(name = "TEACHER_ID_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//教师识别码
	private java.lang.String teacherIdCode;
		
	@Column(name = "START_YEAR", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//周期核定起始年份
	private java.lang.String startYear;
		
	@Column(name = "END_YEAR", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//周期核定结束年份
	private java.lang.String endYear;
		
	@Column(name = "CRE_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//creUserId
	private java.lang.String creUserId;
		
	@Column(name = "CRE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//creTime
	private java.util.Date creTime;
		
	@Column(name = "MOD_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//modUserId
	private java.lang.String modUserId;
		
	@Column(name = "MOD_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//modTime
	private java.util.Date modTime;
		
	@Column(name = "DEL_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//delUserId
	private java.lang.String delUserId;
		
	@Column(name = "DEL_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//delTime
	private java.util.Date delTime;
		
	@Column(name = "DEL_FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//delFlag
	private Integer delFlag;
		
	@Column(name = "PROVINCE_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//provinceId
	private java.lang.String provinceId;
		
	@Column(name = "CITY_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//cityId
	private java.lang.String cityId;
		
	@Column(name = "COUNTY_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//countyId
	private java.lang.String countyId;
		
	@Column(name = "SCHOOL_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//schoolId
	private java.lang.String schoolId;
		
	@Column(name = "COMMENTS", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//备注
	private java.lang.String comments;


	@Column(name = "TEACHER_CAID", unique = true, nullable = false, insertable = true, updatable = true, length = 50)
	//老师所在学校的周期核定ID
	private java.lang.String teacherSchoolHdId;
	@Column(name = "STEP_TYPE", unique = true, nullable = false, insertable = true, updatable = true, length = 50)
	//周期核定ID
	private java.lang.String stepType;

	
	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getTeacherId() {
		return this.teacherId;
	}
	
	public void setTeacherId(java.lang.String value) {
		this.teacherId = value;
	}
	
	public java.lang.String getTeacherIdCode() {
		return this.teacherIdCode;
	}
	
	public void setTeacherIdCode(java.lang.String value) {
		this.teacherIdCode = value;
	}
	
	public java.lang.String getStartYear() {
		return this.startYear;
	}
	
	public void setStartYear(java.lang.String value) {
		this.startYear = value;
	}
	
	public java.lang.String getEndYear() {
		return this.endYear;
	}
	
	public void setEndYear(java.lang.String value) {
		this.endYear = value;
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
	
	public java.lang.String getDelUserId() {
		return this.delUserId;
	}
	
	public void setDelUserId(java.lang.String value) {
		this.delUserId = value;
	}
	
	public java.util.Date getDelTime() {
		return this.delTime;
	}
	
	public void setDelTime(java.util.Date value) {
		this.delTime = value;
	}
	
	public Integer getDelFlag() {
		return this.delFlag;
	}
	
	public void setDelFlag(Integer value) {
		this.delFlag = value;
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
	
	public java.lang.String getComments() {
		return this.comments;
	}
	
	public void setComments(java.lang.String value) {
		this.comments = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Caid",getId())
			.append("TeacherId",getTeacherId())
			.append("TeacherIdCode",getTeacherIdCode())
			.append("StartYear",getStartYear())
			.append("EndYear",getEndYear())
			.append("CreUserId",getCreUserId())
			.append("CreTime",getCreTime())
			.append("ModUserId",getModUserId())
			.append("ModTime",getModTime())
			.append("DelUserId",getDelUserId())
			.append("DelTime",getDelTime())
			.append("DelFlag",getDelFlag())
			.append("ProvinceId",getProvinceId())
			.append("CityId",getCityId())
			.append("CountyId",getCountyId())
			.append("SchoolId",getSchoolId())
			.append("Comments",getComments())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseCycleApproval == false) return false;
		if(this == obj) return true;
		BaseCycleApproval other = (BaseCycleApproval)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	

	/**
	 * @return the stepType
	 */
	public java.lang.String getStepType() {
		return stepType;
	}

	/**
	 * @param stepType the stepType to set
	 */
	public void setStepType(java.lang.String stepType) {
		this.stepType = stepType;
	}

	/**
	 * @return the teacherSchoolHdId
	 */
	public java.lang.String getTeacherSchoolHdId() {
		return teacherSchoolHdId;
	}

	/**
	 * @param teacherSchoolHdId the teacherSchoolHdId to set
	 */
	public void setTeacherSchoolHdId(java.lang.String teacherSchoolHdId) {
		this.teacherSchoolHdId = teacherSchoolHdId;
	}

	
}

