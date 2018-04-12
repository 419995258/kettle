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

//学分登记
@Entity
@Table(name = "BASE_TEACHER_CREDITS_SUM")
public class BaseTeacherCreditsSum implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "SUMCID", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//学分汇总ID
	private java.lang.String id;
		
	@Column(name = "TEACHER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//教师ID
	private java.lang.String teacherId;
		
	@Column(name = "TEACHER_ID_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//教师识别码
	private java.lang.String teacherIdCode;
		
	@Column(name = "T_YEAR", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//学分所属年度
	private java.lang.String tyear;
		
	@Column(name = "SUM_HOURS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//年度学时累计
	private Double sumHours;
		
	@Column(name = "SUM_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//年度学分累计
	private Double sumCredits;
		
	@Column(name = "SUM_QZ_IT_ABILITY_HOURS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//其中年度信息能力学时
	private Double sumQzItAbilityHours;
		
	@Column(name = "SUM_QZ_IT_ABILITY_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//其中年度信息能力学分
	private Double sumQzItAbilityCredits;
		
	@Column(name = "XB_HOURS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//学校级学时
	private Double xbHours;
		
	@Column(name = "XB_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//学校级学分
	private Double xbCredits;
		
	@Column(name = "XB_QZ_IT_ABILITY_HOURS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//学校级其中信息能力学时
	private Double xbQzItAbilityHours;
		
	@Column(name = "XB_QZ_IT_ABILITY_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//学校级其中信息能力学分
	private Double xbQzItAbilityCredits;
		
	@Column(name = "JG_HOURS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//机构学时数
	private Double jgHours;
		
	@Column(name = "JG_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//机构学分
	private Double jgCredits;
		
	@Column(name = "JG_QZ_IT_ABILITY_HOURS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//机构其中信息能力学时
	private Double jgQzItAbilityHours;
		
	@Column(name = "JG_QZ_IT_ABILITY_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//机构其中信息能力学分
	private Double jgQzItAbilityCredits;
		
	@Column(name = "JG_QZ_NATL_HOURS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//机构国培学时数
	private Double jgQzNatlHours;
		
	@Column(name = "JG_QZ_NATL_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//机构国培学分
	private Double jgQzNatlCredits;
		
	@Column(name = "JG_QZ_NATL_IT_ABILITY_HOURS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//机构国培其中信息能力学时
	private Double jgQzNatlItAbilityHours;
		
	@Column(name = "JG_QZ_NATL_IT_ABILITY_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//机构国培其中信息能力学分
	private Double jgQzNatlItAbilityCredits;
		
	@Column(name = "JG_QZ_PRCE_HOURS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//机构省培学时数
	private Double jgQzPrceHours;
		
	@Column(name = "JG_QZ_RRCE_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//机构省培学分
	private Double jgQzRrceCredits;
		
	@Column(name = "JG_QZ_PRCE_IT_ABILITY_HOURS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//机构省培其中信息能力学时
	private Double jgQzPrceItAbilityHours;
		
	@Column(name = "JG_QZ_PRCE_IT_ABILITY_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//机构省培其中信息能力学分
	private Double jgQzPrceItAbilityCredits;
		
	@Column(name = "JG_QZ_CITY_HOURS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//机构市培学时数
	private Double jgQzCityHours;
		
	@Column(name = "JG_QZ_CITY_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//机构市培学分
	private Double jgQzCityCredits;
		
	@Column(name = "JG_QZ_CITY_IT_ABILITY_HOURS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//机构市培其中信息能力学时
	private Double jgQzCityItAbilityHours;
		
	@Column(name = "JG_QZ_CITY_IT_ABILITY_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//机构市培其中信息能力学分
	private Double jgQzCityItAbilityCredits;
		
	@Column(name = "JG_QZ_CNTY_HOURS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//机构县培学时数
	private Double jgQzCntyHours;
		
	@Column(name = "JG_QZ_CNTY_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//机构县培学分
	private Double jgQzCntyCredits;
		
	@Column(name = "JG_QZ_CNTY_IT_ABILITY_HOURS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//机构县培其中信息能力学时
	private Double jgQzCntyItAbilityHours;
		
	@Column(name = "JG_QZ_CNTY_IT_ABILITY_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//机构县培其中信息能力学分
	private Double jgQzCntyItAbilityCredits;
		
	@Column(name = "XL_YEARS", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//学历提高学年
	private java.lang.String xlYears;
		
	@Column(name = "XL_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//学历提高学分
	private Double xlCredits;
		
	@Column(name = "RSB_YEARS", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//人社部学历提高学年
	private java.lang.String rsbYears;
		
	@Column(name = "RSB_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//人社部学分
	private Double rsbCredits;
		
	@Column(name = "NET_HOURS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//网络项目学时
	private Double netHours;
		
	@Column(name = "NET_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//网络项目学分
	private Double netCredits;
		
	@Column(name = "NET_QZ_IT_ABILITY_HOURS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//网络其中信息能力学时
	private Double netQzItAbilityHours;
		
	@Column(name = "NET_QZ_IT_ABILITY_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//网络其中信息能力学分
	private Double netQzItAbilityCredits;
		
	@Column(name = "NET_QZ_NATL_HOURS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//网络国家项目学时
	private Double netQzNatlHours;
		
	@Column(name = "NET_QZ_NATL_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//网络国家项目学分
	private Double netQzNatlCredits;
		
	@Column(name = "NET_QZ_NATL_IT_ABILITY_HOURS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//网络国家其中信息能力学时
	private Double netQzNatlItAbilityHours;
		
	@Column(name = "NET_QZ_NATL_IT_ABILITY_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//网络国家其中信息能力学分
	private Double netQzNatlItAbilityCredits;
		
	@Column(name = "NET_QZ_PRCE_HOURS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//网络省级项目学时
	private Double netQzPrceHours;
		
	@Column(name = "NET_QZ_RRCE_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//网络省级项目学分
	private Double netQzRrceCredits;
		
	@Column(name = "NET_QZ_PRCE_IT_ABILITY_HOURS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//网络省级其中信息能力学时
	private Double netQzPrceItAbilityHours;
		
	@Column(name = "NET_QZ_PRCE_IT_ABILITY_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//网络省级其中信息能力学分
	private Double netQzPrceItAbilityCredits;
		
	@Column(name = "NET_QZ_CITY_HOURS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//网络市级项目学时
	private Double netQzCityHours;
		
	@Column(name = "NET_QZ_CITY_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//网络市级项目学分
	private Double netQzCityCredits;
		
	@Column(name = "NET_QZ_CITY_IT_ABILITY_HOURS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//网络市级其中信息能力学时
	private Double netQzCityItAbilityHours;
		
	@Column(name = "NET_QZ_CITY_IT_ABILITY_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//网络市级其中信息能力学分
	private Double netQzCityItAbilityCredits;
		
	@Column(name = "BK_PAPERS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//报刊发表篇数
	private Double bkPapers;
		
	@Column(name = "BK_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//报刊发表学分
	private Double bkCredits;
		
	@Column(name = "JCZZ_BOOKS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//教材专著部数
	private Double jczzBooks;
		
	@Column(name = "JCZZ_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//教材专著学分
	private Double jczzCredits;
		
	@Column(name = "KTJG_WORKS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//课题研究或教改项目数
	private Double ktjgWorks;
		
	@Column(name = "KTJG_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//课题研究或教改学分
	private Double ktjgCredits;
		
	@Column(name = "KYCG_WORKS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//科研成果项目数
	private Double kycgWorks;
		
	@Column(name = "KYCG_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//科研陈果学分
	private Double kycgCredits;
		
	@Column(name = "ZJYX_TERMS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//城区支教或农村研修学期
	private Double zjyxTerms;
		
	@Column(name = "ZJYX_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//城区支教或农村研修学分
	private Double zjyxCredits;
		
	@Column(name = "AUDIT_STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//审核状态
	private Double auditStatus;
		
	@Column(name = "AUDIT_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//审核时间
	private java.util.Date auditTime;
		
	@Column(name = "AUDIT_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//审核人ID
	private java.lang.String auditUserId;
		
	@Column(name = "AUDIT_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	//审核类型
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
		
	@Column(name = "LAST_STAT_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//截止统计时间
	private java.util.Date lastStatTime;

	@Column(name = "STEP_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//schoolId
	private java.lang.String stepType;

	
	@Column(name = "CAID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//核定Id
	private java.lang.String caId;
	@Column(name = "CRE_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	private java.lang.String creUserId;
	@Column(name = "MOD_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	private java.lang.String modUserId;
	@Column(name = "CRE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//创建时间
	private java.util.Date creTime;
	@Column(name = "MOD_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//创建时间
	private java.util.Date modTime;
	public java.lang.String getCaId() {
		return caId;
	}

	public void setCaId(java.lang.String caId) {
		this.caId = caId;
	}

	public java.lang.String getCreUserId() {
		return creUserId;
	}

	public void setCreUserId(java.lang.String creUserId) {
		this.creUserId = creUserId;
	}

	public java.lang.String getModUserId() {
		return modUserId;
	}

	public void setModUserId(java.lang.String modUserId) {
		this.modUserId = modUserId;
	}

	public java.util.Date getCreTime() {
		return creTime;
	}

	public void setCreTime(java.util.Date creTime) {
		this.creTime = creTime;
	}

	public java.util.Date getModTime() {
		return modTime;
	}

	public void setModTime(java.util.Date modTime) {
		this.modTime = modTime;
	}

	
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
	
	public java.lang.String getTyear() {
		return this.tyear;
	}
	
	public void setTyear(java.lang.String value) {
		this.tyear = value;
	}
	
	public Double getSumHours() {
		return this.sumHours;
	}
	
	public void setSumHours(Double value) {
		this.sumHours = value;
	}
	
	public Double getSumCredits() {
		return this.sumCredits;
	}
	
	public void setSumCredits(Double value) {
		this.sumCredits = value;
	}
	
	public Double getSumQzItAbilityHours() {
		return this.sumQzItAbilityHours;
	}
	
	public void setSumQzItAbilityHours(Double value) {
		this.sumQzItAbilityHours = value;
	}
	
	public Double getSumQzItAbilityCredits() {
		return this.sumQzItAbilityCredits;
	}
	
	public void setSumQzItAbilityCredits(Double value) {
		this.sumQzItAbilityCredits = value;
	}
	
	public Double getXbHours() {
		return this.xbHours;
	}
	
	public void setXbHours(Double value) {
		this.xbHours = value;
	}
	
	public Double getXbCredits() {
		return this.xbCredits;
	}
	
	public void setXbCredits(Double value) {
		this.xbCredits = value;
	}
	
	public Double getXbQzItAbilityHours() {
		return this.xbQzItAbilityHours;
	}
	
	public void setXbQzItAbilityHours(Double value) {
		this.xbQzItAbilityHours = value;
	}
	
	public Double getXbQzItAbilityCredits() {
		return this.xbQzItAbilityCredits;
	}
	
	public void setXbQzItAbilityCredits(Double value) {
		this.xbQzItAbilityCredits = value;
	}
	
	public Double getJgHours() {
		return this.jgHours;
	}
	
	public void setJgHours(Double value) {
		this.jgHours = value;
	}
	
	public Double getJgCredits() {
		return this.jgCredits;
	}
	
	public void setJgCredits(Double value) {
		this.jgCredits = value;
	}
	
	public Double getJgQzItAbilityHours() {
		return this.jgQzItAbilityHours;
	}
	
	public void setJgQzItAbilityHours(Double value) {
		this.jgQzItAbilityHours = value;
	}
	
	public Double getJgQzItAbilityCredits() {
		return this.jgQzItAbilityCredits;
	}
	
	public void setJgQzItAbilityCredits(Double value) {
		this.jgQzItAbilityCredits = value;
	}
	
	public Double getJgQzNatlHours() {
		return this.jgQzNatlHours;
	}
	
	public void setJgQzNatlHours(Double value) {
		this.jgQzNatlHours = value;
	}
	
	public Double getJgQzNatlCredits() {
		return this.jgQzNatlCredits;
	}
	
	public void setJgQzNatlCredits(Double value) {
		this.jgQzNatlCredits = value;
	}
	
	public Double getJgQzNatlItAbilityHours() {
		return this.jgQzNatlItAbilityHours;
	}
	
	public void setJgQzNatlItAbilityHours(Double value) {
		this.jgQzNatlItAbilityHours = value;
	}
	
	public Double getJgQzNatlItAbilityCredits() {
		return this.jgQzNatlItAbilityCredits;
	}
	
	public void setJgQzNatlItAbilityCredits(Double value) {
		this.jgQzNatlItAbilityCredits = value;
	}
	
	public Double getJgQzPrceHours() {
		return this.jgQzPrceHours;
	}
	
	public void setJgQzPrceHours(Double value) {
		this.jgQzPrceHours = value;
	}
	
	public Double getJgQzRrceCredits() {
		return this.jgQzRrceCredits;
	}
	
	public void setJgQzRrceCredits(Double value) {
		this.jgQzRrceCredits = value;
	}
	
	public Double getJgQzPrceItAbilityHours() {
		return this.jgQzPrceItAbilityHours;
	}
	
	public void setJgQzPrceItAbilityHours(Double value) {
		this.jgQzPrceItAbilityHours = value;
	}
	
	public Double getJgQzPrceItAbilityCredits() {
		return this.jgQzPrceItAbilityCredits;
	}
	
	public void setJgQzPrceItAbilityCredits(Double value) {
		this.jgQzPrceItAbilityCredits = value;
	}
	
	public Double getJgQzCityHours() {
		return this.jgQzCityHours;
	}
	
	public void setJgQzCityHours(Double value) {
		this.jgQzCityHours = value;
	}
	
	public Double getJgQzCityCredits() {
		return this.jgQzCityCredits;
	}
	
	public void setJgQzCityCredits(Double value) {
		this.jgQzCityCredits = value;
	}
	
	public Double getJgQzCityItAbilityHours() {
		return this.jgQzCityItAbilityHours;
	}
	
	public void setJgQzCityItAbilityHours(Double value) {
		this.jgQzCityItAbilityHours = value;
	}
	
	public Double getJgQzCityItAbilityCredits() {
		return this.jgQzCityItAbilityCredits;
	}
	
	public void setJgQzCityItAbilityCredits(Double value) {
		this.jgQzCityItAbilityCredits = value;
	}
	
	public Double getJgQzCntyHours() {
		return this.jgQzCntyHours;
	}
	
	public void setJgQzCntyHours(Double value) {
		this.jgQzCntyHours = value;
	}
	
	public Double getJgQzCntyCredits() {
		return this.jgQzCntyCredits;
	}
	
	public void setJgQzCntyCredits(Double value) {
		this.jgQzCntyCredits = value;
	}
	
	public Double getJgQzCntyItAbilityHours() {
		return this.jgQzCntyItAbilityHours;
	}
	
	public void setJgQzCntyItAbilityHours(Double value) {
		this.jgQzCntyItAbilityHours = value;
	}
	
	public Double getJgQzCntyItAbilityCredits() {
		return this.jgQzCntyItAbilityCredits;
	}
	
	public void setJgQzCntyItAbilityCredits(Double value) {
		this.jgQzCntyItAbilityCredits = value;
	}
	
	public java.lang.String getXlYears() {
		return this.xlYears;
	}
	
	public void setXlYears(java.lang.String value) {
		this.xlYears = value;
	}
	
	public Double getXlCredits() {
		return this.xlCredits;
	}
	
	public void setXlCredits(Double value) {
		this.xlCredits = value;
	}
	
	public java.lang.String getRsbYears() {
		return this.rsbYears;
	}
	
	public void setRsbYears(java.lang.String value) {
		this.rsbYears = value;
	}
	
	public Double getRsbCredits() {
		return this.rsbCredits;
	}
	
	public void setRsbCredits(Double value) {
		this.rsbCredits = value;
	}
	
	public Double getNetHours() {
		return this.netHours;
	}
	
	public void setNetHours(Double value) {
		this.netHours = value;
	}
	
	public Double getNetCredits() {
		return this.netCredits;
	}
	
	public void setNetCredits(Double value) {
		this.netCredits = value;
	}
	
	public Double getNetQzItAbilityHours() {
		return this.netQzItAbilityHours;
	}
	
	public void setNetQzItAbilityHours(Double value) {
		this.netQzItAbilityHours = value;
	}
	
	public Double getNetQzItAbilityCredits() {
		return this.netQzItAbilityCredits;
	}
	
	public void setNetQzItAbilityCredits(Double value) {
		this.netQzItAbilityCredits = value;
	}
	
	public Double getNetQzNatlHours() {
		return this.netQzNatlHours;
	}
	
	public void setNetQzNatlHours(Double value) {
		this.netQzNatlHours = value;
	}
	
	public Double getNetQzNatlCredits() {
		return this.netQzNatlCredits;
	}
	
	public void setNetQzNatlCredits(Double value) {
		this.netQzNatlCredits = value;
	}
	
	public Double getNetQzNatlItAbilityHours() {
		return this.netQzNatlItAbilityHours;
	}
	
	public void setNetQzNatlItAbilityHours(Double value) {
		this.netQzNatlItAbilityHours = value;
	}
	
	public Double getNetQzNatlItAbilityCredits() {
		return this.netQzNatlItAbilityCredits;
	}
	
	public void setNetQzNatlItAbilityCredits(Double value) {
		this.netQzNatlItAbilityCredits = value;
	}
	
	public Double getNetQzPrceHours() {
		return this.netQzPrceHours;
	}
	
	public void setNetQzPrceHours(Double value) {
		this.netQzPrceHours = value;
	}
	
	public Double getNetQzRrceCredits() {
		return this.netQzRrceCredits;
	}
	
	public void setNetQzRrceCredits(Double value) {
		this.netQzRrceCredits = value;
	}
	
	public Double getNetQzPrceItAbilityHours() {
		return this.netQzPrceItAbilityHours;
	}
	
	public void setNetQzPrceItAbilityHours(Double value) {
		this.netQzPrceItAbilityHours = value;
	}
	
	public Double getNetQzPrceItAbilityCredits() {
		return this.netQzPrceItAbilityCredits;
	}
	
	public void setNetQzPrceItAbilityCredits(Double value) {
		this.netQzPrceItAbilityCredits = value;
	}
	
	public Double getNetQzCityHours() {
		return this.netQzCityHours;
	}
	
	public void setNetQzCityHours(Double value) {
		this.netQzCityHours = value;
	}
	
	public Double getNetQzCityCredits() {
		return this.netQzCityCredits;
	}
	
	public void setNetQzCityCredits(Double value) {
		this.netQzCityCredits = value;
	}
	
	public Double getNetQzCityItAbilityHours() {
		return this.netQzCityItAbilityHours;
	}
	
	public void setNetQzCityItAbilityHours(Double value) {
		this.netQzCityItAbilityHours = value;
	}
	
	public Double getNetQzCityItAbilityCredits() {
		return this.netQzCityItAbilityCredits;
	}
	
	public void setNetQzCityItAbilityCredits(Double value) {
		this.netQzCityItAbilityCredits = value;
	}
	
	public Double getBkPapers() {
		return this.bkPapers;
	}
	
	public void setBkPapers(Double value) {
		this.bkPapers = value;
	}
	
	public Double getBkCredits() {
		return this.bkCredits;
	}
	
	public void setBkCredits(Double value) {
		this.bkCredits = value;
	}
	
	public Double getJczzBooks() {
		return this.jczzBooks;
	}
	
	public void setJczzBooks(Double value) {
		this.jczzBooks = value;
	}
	
	public Double getJczzCredits() {
		return this.jczzCredits;
	}
	
	public void setJczzCredits(Double value) {
		this.jczzCredits = value;
	}
	
	public Double getKtjgWorks() {
		return this.ktjgWorks;
	}
	
	public void setKtjgWorks(Double value) {
		this.ktjgWorks = value;
	}
	
	public Double getKtjgCredits() {
		return this.ktjgCredits;
	}
	
	public void setKtjgCredits(Double value) {
		this.ktjgCredits = value;
	}
	
	public Double getKycgWorks() {
		return this.kycgWorks;
	}
	
	public void setKycgWorks(Double value) {
		this.kycgWorks = value;
	}
	
	public Double getKycgCredits() {
		return this.kycgCredits;
	}
	
	public void setKycgCredits(Double value) {
		this.kycgCredits = value;
	}
	
	public Double getZjyxTerms() {
		return this.zjyxTerms;
	}
	
	public void setZjyxTerms(Double value) {
		this.zjyxTerms = value;
	}
	
	public Double getZjyxCredits() {
		return this.zjyxCredits;
	}
	
	public void setZjyxCredits(Double value) {
		this.zjyxCredits = value;
	}
	
	public Double getAuditStatus() {
		return this.auditStatus;
	}
	
	public void setAuditStatus(Double value) {
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
	
	public java.util.Date getLastStatTime() {
		return this.lastStatTime;
	}
	
	public void setLastStatTime(java.util.Date value) {
		this.lastStatTime = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Sumcid",getId())
			.append("TeacherId",getTeacherId())
			.append("TeacherIdCode",getTeacherIdCode())
			.append("Tyear",getTyear())
			.append("SumHours",getSumHours())
			.append("SumCredits",getSumCredits())
			.append("SumQzItAbilityHours",getSumQzItAbilityHours())
			.append("SumQzItAbilityCredits",getSumQzItAbilityCredits())
			.append("XbHours",getXbHours())
			.append("XbCredits",getXbCredits())
			.append("XbQzItAbilityHours",getXbQzItAbilityHours())
			.append("XbQzItAbilityCredits",getXbQzItAbilityCredits())
			.append("JgHours",getJgHours())
			.append("JgCredits",getJgCredits())
			.append("JgQzItAbilityHours",getJgQzItAbilityHours())
			.append("JgQzItAbilityCredits",getJgQzItAbilityCredits())
			.append("JgQzNatlHours",getJgQzNatlHours())
			.append("JgQzNatlCredits",getJgQzNatlCredits())
			.append("JgQzNatlItAbilityHours",getJgQzNatlItAbilityHours())
			.append("JgQzNatlItAbilityCredits",getJgQzNatlItAbilityCredits())
			.append("JgQzPrceHours",getJgQzPrceHours())
			.append("JgQzRrceCredits",getJgQzRrceCredits())
			.append("JgQzPrceItAbilityHours",getJgQzPrceItAbilityHours())
			.append("JgQzPrceItAbilityCredits",getJgQzPrceItAbilityCredits())
			.append("JgQzCityHours",getJgQzCityHours())
			.append("JgQzCityCredits",getJgQzCityCredits())
			.append("JgQzCityItAbilityHours",getJgQzCityItAbilityHours())
			.append("JgQzCityItAbilityCredits",getJgQzCityItAbilityCredits())
			.append("JgQzCntyHours",getJgQzCntyHours())
			.append("JgQzCntyCredits",getJgQzCntyCredits())
			.append("JgQzCntyItAbilityHours",getJgQzCntyItAbilityHours())
			.append("JgQzCntyItAbilityCredits",getJgQzCntyItAbilityCredits())
			.append("XlYears",getXlYears())
			.append("XlCredits",getXlCredits())
			.append("RsbYears",getRsbYears())
			.append("RsbCredits",getRsbCredits())
			.append("NetHours",getNetHours())
			.append("NetCredits",getNetCredits())
			.append("NetQzItAbilityHours",getNetQzItAbilityHours())
			.append("NetQzItAbilityCredits",getNetQzItAbilityCredits())
			.append("NetQzNatlHours",getNetQzNatlHours())
			.append("NetQzNatlCredits",getNetQzNatlCredits())
			.append("NetQzNatlItAbilityHours",getNetQzNatlItAbilityHours())
			.append("NetQzNatlItAbilityCredits",getNetQzNatlItAbilityCredits())
			.append("NetQzPrceHours",getNetQzPrceHours())
			.append("NetQzRrceCredits",getNetQzRrceCredits())
			.append("NetQzPrceItAbilityHours",getNetQzPrceItAbilityHours())
			.append("NetQzPrceItAbilityCredits",getNetQzPrceItAbilityCredits())
			.append("NetQzCityHours",getNetQzCityHours())
			.append("NetQzCityCredits",getNetQzCityCredits())
			.append("NetQzCityItAbilityHours",getNetQzCityItAbilityHours())
			.append("NetQzCityItAbilityCredits",getNetQzCityItAbilityCredits())
			.append("BkPapers",getBkPapers())
			.append("BkCredits",getBkCredits())
			.append("JczzBooks",getJczzBooks())
			.append("JczzCredits",getJczzCredits())
			.append("KtjgWorks",getKtjgWorks())
			.append("KtjgCredits",getKtjgCredits())
			.append("KycgWorks",getKycgWorks())
			.append("KycgCredits",getKycgCredits())
			.append("ZjyxTerms",getZjyxTerms())
			.append("ZjyxCredits",getZjyxCredits())
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
			.append("LastStatTime",getLastStatTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseTeacherCreditsSum == false) return false;
		if(this == obj) return true;
		BaseTeacherCreditsSum other = (BaseTeacherCreditsSum)obj;
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
}

