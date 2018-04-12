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

//教师资格注册表
@Entity
@Table(name = "BASE_TEACHER_CERIFIY")
public class BaseTeacherCerifiy implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "TCEID", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//tceid
	private java.lang.String id;
		
	@Column(name = "TEACHER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//教师ID
	private java.lang.String teacherId;
		
	@Column(name = "TEACHER_ID_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	//教师识别码
	private java.lang.String teacherIdCode;
		
	@Column(name = "CERIFIY_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	//注册类型(首次注册,定期注册）
	private java.lang.String cerifiyType;
		
	@Column(name = "BUSINESS_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//业务名称 名称系统自动生成
	private java.lang.String businessName;
		
	@Column(name = "ANNUAL_CREDIT_YEAR", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//学分登记年份
	private java.lang.String annualCreditYear;
		
	@Column(name = "ANNUAL_START_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//学分统计开始时间
	private java.util.Date annualStartTime;
		
	@Column(name = "ANNUAL_END_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//学分统计结束时间
	private java.util.Date annualEndTime;
		
	@Column(name = "SCHOOL_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//教师所属学校ID
	private java.lang.String schoolId;
		
	@Column(name = "PROVINCE_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//学校所属省ID
	private java.lang.String provinceId;
		
	@Column(name = "CITY_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//学校所属市ID
	private java.lang.String cityId;
		
	@Column(name = "COUNTY_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//学校所属县ID
	private java.lang.String countyId;
		
	@Column(name = "AREA_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//学校被管ID
	private java.lang.String areaId;
		
	@Column(name = "DELETE_FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//删除标记
	private Integer deleteFlag;
		
	@Column(name = "DELETE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//删除时间
	private java.util.Date deleteTime;
		
	@Column(name = "DELETE_USER", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//删除用户
	private java.lang.String deleteUser;

	//申请科目
	@Column(name = "APPLY_SUBJECTS", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	private java.lang.String applySubjects;
	
	//证书编号
	@Column(name = "CERTIFICATE_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	private java.lang.String certificateNum;

	@Column(name = "DEAL_RESULT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//解决标记
	private Integer dealResult;

	@Column(name = "CREATE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//删除时间
	private java.util.Date createTime;

	//解决状态
	@Column(name = "DEAL_STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	private java.lang.String dealStatus;
	
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
	
	public java.lang.String getCerifiyType() {
		return this.cerifiyType;
	}
	
	public void setCerifiyType(java.lang.String value) {
		this.cerifiyType = value;
	}
	
	public java.lang.String getBusinessName() {
		return this.businessName;
	}
	
	public void setBusinessName(java.lang.String value) {
		this.businessName = value;
	}
	
	public java.lang.String getAnnualCreditYear() {
		return this.annualCreditYear;
	}
	
	public void setAnnualCreditYear(java.lang.String value) {
		this.annualCreditYear = value;
	}
	
	public java.util.Date getAnnualStartTime() {
		return this.annualStartTime;
	}
	
	public void setAnnualStartTime(java.util.Date value) {
		this.annualStartTime = value;
	}
	
	public java.util.Date getAnnualEndTime() {
		return this.annualEndTime;
	}
	
	public void setAnnualEndTime(java.util.Date value) {
		this.annualEndTime = value;
	}
	
	public java.lang.String getSchoolId() {
		return this.schoolId;
	}
	
	public void setSchoolId(java.lang.String value) {
		this.schoolId = value;
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
	
	public java.lang.String getAreaId() {
		return this.areaId;
	}
	
	public void setAreaId(java.lang.String value) {
		this.areaId = value;
	}
	
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}
	
	public void setDeleteFlag(Integer value) {
		this.deleteFlag = value;
	}
	
	public java.util.Date getDeleteTime() {
		return this.deleteTime;
	}
	
	public void setDeleteTime(java.util.Date value) {
		this.deleteTime = value;
	}
	
	public java.lang.String getDeleteUser() {
		return this.deleteUser;
	}
	
	public void setDeleteUser(java.lang.String value) {
		this.deleteUser = value;
	}
	
	public java.lang.String getApplySubjects() {
		return applySubjects;
	}

	public void setApplySubjects(java.lang.String applySubjects) {
		this.applySubjects = applySubjects;
	}

	public java.lang.String getCertificateNum() {
		return certificateNum;
	}

	public void setCertificateNum(java.lang.String certificateNum) {
		this.certificateNum = certificateNum;
	}
	
	

	public Integer getDealResult() {
		return dealResult;
	}

	public void setDealResult(Integer dealResult) {
		this.dealResult = dealResult;
	}
	
	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.lang.String getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(java.lang.String dealStatus) {
		this.dealStatus = dealStatus;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Tceid",getId())
			.append("TeacherId",getTeacherId())
			.append("TeacherIdCode",getTeacherIdCode())
			.append("CerifiyType",getCerifiyType())
			.append("BusinessName",getBusinessName())
			.append("AnnualCreditYear",getAnnualCreditYear())
			.append("AnnualStartTime",getAnnualStartTime())
			.append("AnnualEndTime",getAnnualEndTime())
			.append("SchoolId",getSchoolId())
			.append("ProvinceId",getProvinceId())
			.append("CityId",getCityId())
			.append("CountyId",getCountyId())
			.append("AreaId",getAreaId())
			.append("DeleteFlag",getDeleteFlag())
			.append("DeleteTime",getDeleteTime())
			.append("DeleteUser",getDeleteUser())
			.append("ApplySubjects",getApplySubjects())
			.append("CertificateNum",getCertificateNum())
			.append("DealResult",getDealResult())
			.append("CreateTime",getCreateTime())
			.append("DealStatus",getDealStatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseTeacherCerifiy == false) return false;
		if(this == obj) return true;
		BaseTeacherCerifiy other = (BaseTeacherCerifiy)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

