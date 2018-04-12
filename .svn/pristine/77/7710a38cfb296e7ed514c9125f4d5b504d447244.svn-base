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

//项目教师MAP
@Entity
@Table(name = "BASE_PRO_TEACHER_MAP")
public class BaseProTeacherMap implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "PRO_MAP_ID", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//项目教师MAP ID
	private java.lang.String id;
		
	@Column(name = "PROAD_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//培训名额ID
	private java.lang.String proadId;
		
	@Column(name = "PRO_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 40)
	//项目ID
	private java.lang.String proId;
		
	@Column(name = "T_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//教师ID
	private java.lang.String tid;
		
	@Column(name = "T_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//教师识别码
	private java.lang.String tcode;
		
	@Column(name = "T_PRO_ACTUAL_AMOUNT", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	//项目投入量
	private Double tproActualAmount;
		
	@Column(name = "T_PRO_RESULT", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//参加项目结果:优秀／及格／主持／主要／第三人
	private java.lang.String tproResult;
		
	@Column(name = "T_PRO_BASE_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//基础学分:如果是优秀：基础学分=ru.pass.
	private Double tproBaseCredits;
		
	@Column(name = "T_PRO_AWARD_CREDITS", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//奖励学分
	private Double tproAwardCredits;
		
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
		
	@Column(name = "AUDIT_STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//审核状态
	private Double auditStatus;
		
	@Column(name = "AUDIT_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//审核时间
	private java.util.Date auditTime;
		
	@Column(name = "AUDIT_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//审核用户ID
	private java.lang.String auditUserId;
		
	@Column(name = "AUDIT_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	//审核类型
	private java.lang.String auditType;
		
	@Column(name = "SIGN_UP_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//报名时间
	private java.util.Date signUpTime;
		
	@Column(name = "SIGN_UP_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//报名用户ID
	private java.lang.String signUpUserId;
		
	@Column(name = "RESULT_INPUT_FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//项目结果录入标识
	private Integer resultInputFlag;
		
	@Column(name = "RESULT_INPUT_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//项目结果录入用户ID
	private java.lang.String resultInputUserId;
		
	@Column(name = "RESULT_INPUT_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//项目结果录入时间
	private java.util.Date resultInputTime;
		
	@Column(name = "RESULT_CONF_FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//教师确认标识
	private Integer resultConfFlag;
		
	@Column(name = "RESULT_CONF_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//教师确认时间
	private java.util.Date resultConfTime;
		
	@Column(name = "RESULT_CONF_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//确认教师ID
	private java.lang.String resultConfUserId;
		
	@Column(name = "DEL_FALG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//删除标识
	private Integer delFalg;
		
	@Column(name = "DEL_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//删除时间
	private java.util.Date delTime;
		
	@Column(name = "DEL_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//删除用户ID
	private java.lang.String delUserId;
		
	@Column(name = "SRD_GRD_MARK", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//机构评分几星
	private Double srdGrdMark;
		
	@Column(name = "SRD_GRD_COMMETS", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//机构评论
	private java.lang.String srdGrdCommets;
		
	@Column(name = "SRD_GOOD_REPUT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//机构好评
	private Integer srdGoodReput;
		
	@Column(name = "SRD_BAD_REPUT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//机构差评
	private Integer srdBadReput;

	@Column(name = "IS_BL", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//是否是补录
	private java.lang.String isBl;

	@Column(name = "XFDJ_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//是否是补录
	private java.lang.String xfdjId;
	@Column(name = "T_YEAR", unique = false, nullable = true, insertable = true, updatable = true, length =10)
	//是否是补录
	private java.lang.String tyear;
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getProadId() {
		return this.proadId;
	}
	
	public void setProadId(java.lang.String value) {
		this.proadId = value;
	}
	
	public java.lang.String getProId() {
		return this.proId;
	}
	
	public void setProId(java.lang.String value) {
		this.proId = value;
	}
	
	public java.lang.String getTid() {
		return this.tid;
	}
	
	public void setTid(java.lang.String value) {
		this.tid = value;
	}
	
	public java.lang.String getTcode() {
		return this.tcode;
	}
	
	public void setTcode(java.lang.String value) {
		this.tcode = value;
	}
	
	public Double getTproActualAmount() {
		return this.tproActualAmount;
	}
	
	public void setTproActualAmount(Double value) {
		this.tproActualAmount = value;
	}
	
	public java.lang.String getTproResult() {
		return this.tproResult;
	}
	
	public void setTproResult(java.lang.String value) {
		this.tproResult = value;
	}
	
	public Double getTproBaseCredits() {
		return this.tproBaseCredits;
	}
	
	public void setTproBaseCredits(Double value) {
		this.tproBaseCredits = value;
	}
	
	public Double getTproAwardCredits() {
		return this.tproAwardCredits;
	}
	
	public void setTproAwardCredits(Double value) {
		this.tproAwardCredits = value;
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
	
	public java.util.Date getSignUpTime() {
		return this.signUpTime;
	}
	
	public void setSignUpTime(java.util.Date value) {
		this.signUpTime = value;
	}
	
	public java.lang.String getSignUpUserId() {
		return this.signUpUserId;
	}
	
	public void setSignUpUserId(java.lang.String value) {
		this.signUpUserId = value;
	}
	
	public Integer getResultInputFlag() {
		return this.resultInputFlag;
	}
	
	public void setResultInputFlag(Integer value) {
		this.resultInputFlag = value;
	}
	
	public java.lang.String getResultInputUserId() {
		return this.resultInputUserId;
	}
	
	public void setResultInputUserId(java.lang.String value) {
		this.resultInputUserId = value;
	}
	
	public java.util.Date getResultInputTime() {
		return this.resultInputTime;
	}
	
	public void setResultInputTime(java.util.Date value) {
		this.resultInputTime = value;
	}
	
	public Integer getResultConfFlag() {
		return this.resultConfFlag;
	}
	
	public void setResultConfFlag(Integer value) {
		this.resultConfFlag = value;
	}
	
	public java.util.Date getResultConfTime() {
		return this.resultConfTime;
	}
	
	public void setResultConfTime(java.util.Date value) {
		this.resultConfTime = value;
	}
	
	public java.lang.String getResultConfUserId() {
		return this.resultConfUserId;
	}
	
	public void setResultConfUserId(java.lang.String value) {
		this.resultConfUserId = value;
	}
	
	public Integer getDelFalg() {
		return this.delFalg;
	}
	
	public void setDelFalg(Integer value) {
		this.delFalg = value;
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
	

	public Double getSrdGrdMark() {
		return srdGrdMark;
	}

	public void setSrdGrdMark(Double srdGrdMark) {
		this.srdGrdMark = srdGrdMark;
	}

	public java.lang.String getSrdGrdCommets() {
		return srdGrdCommets;
	}

	public void setSrdGrdCommets(java.lang.String srdGrdCommets) {
		this.srdGrdCommets = srdGrdCommets;
	}

	public Integer getSrdGoodReput() {
		return srdGoodReput;
	}

	public void setSrdGoodReput(Integer srdGoodReput) {
		this.srdGoodReput = srdGoodReput;
	}

	public Integer getSrdBadReput() {
		return srdBadReput;
	}

	public void setSrdBadReput(Integer srdBadReput) {
		this.srdBadReput = srdBadReput;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ProMapId",getId())
			.append("ProadId",getProadId())
			.append("ProId",getProId())
			.append("Tid",getTid())
			.append("Tcode",getTcode())
			.append("TproActualAmount",getTproActualAmount())
			.append("TproResult",getTproResult())
			.append("TproBaseCredits",getTproBaseCredits())
			.append("TproAwardCredits",getTproAwardCredits())
			.append("ProvinceId",getProvinceId())
			.append("CityId",getCityId())
			.append("CountyId",getCountyId())
			.append("SchoolId",getSchoolId())
			.append("AuditStatus",getAuditStatus())
			.append("AuditTime",getAuditTime())
			.append("AuditUserId",getAuditUserId())
			.append("AuditType",getAuditType())
			.append("SignUpTime",getSignUpTime())
			.append("SignUpUserId",getSignUpUserId())
			.append("ResultInputFlag",getResultInputFlag())
			.append("ResultInputUserId",getResultInputUserId())
			.append("ResultInputTime",getResultInputTime())
			.append("ResultConfFlag",getResultConfFlag())
			.append("ResultConfTime",getResultConfTime())
			.append("ResultConfUserId",getResultConfUserId())
			.append("DelFalg",getDelFalg())
			.append("DelTime",getDelTime())
			.append("DelUserId",getDelUserId())
			.append("srdGrdMark",getSrdGrdMark())
			.append("srdGrdCommets",getSrdGrdCommets())
			.append("srdGoodReput",getSrdGoodReput())
			.append("srdBadReput",getSrdBadReput())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseProTeacherMap == false) return false;
		if(this == obj) return true;
		BaseProTeacherMap other = (BaseProTeacherMap)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	/**
	 * @return the isBl
	 */
	public java.lang.String getIsBl() {
		return isBl;
	}

	/**
	 * @param isBl the isBl to set
	 */
	public void setIsBl(java.lang.String isBl) {
		this.isBl = isBl;
	}

	/**
	 * @return the xfdjId
	 */
	public java.lang.String getXfdjId() {
		return xfdjId;
	}

	/**
	 * @param xfdjId the xfdjId to set
	 */
	public void setXfdjId(java.lang.String xfdjId) {
		this.xfdjId = xfdjId;
	}

	/**
	 * @return the tyear
	 */
	public java.lang.String getTyear() {
		return tyear;
	}

	/**
	 * @param tyear the tyear to set
	 */
	public void setTyear(java.lang.String tyear) {
		this.tyear = tyear;
	}
}

