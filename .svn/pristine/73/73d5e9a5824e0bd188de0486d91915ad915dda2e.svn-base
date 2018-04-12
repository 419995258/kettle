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

//学分项目
@Entity
@Table(name = "BASE_CREDIT_PROJECT")
public class BaseCreditProject implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "PRO_ID", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//项目ID
	private java.lang.String id;
		
	@Column(name = "ORG_3RD_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 40)
	//负责项目第三方机构ID
	private java.lang.String org3rdId;
		
	@Column(name = "PRO_REF_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//项目引用编码：国家教师系统用到的，先保留
	private java.lang.String proRefCode;
		
	@Column(name = "PRO_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//项目名称
	private java.lang.String proName;
		
	@Column(name = "PRO_LEVEL", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//项目级别:来自属性表
	private java.lang.String proLevel;
		
	@Column(name = "PRO_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//项目类型：来自属性表
	private java.lang.String proType;
		
	@Column(name = "T_YEAR", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//项目年度
	private java.lang.String tyear;
		
	@Column(name = "PRO_DEAL_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//项目实施方式
	private java.lang.String proDealType;
		
	@Column(name = "FACE_STUDY_SET", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//项目面对学段集合：国家教师系统用到的,先保留
	private java.lang.String faceStudySet;
		
	@Column(name = "FACE_POST_SET", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//项目面对岗位集合：国家教师系统用到的，先保留
	private java.lang.String facePostSet;
		
	@Column(name = "PRO_MONEY_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//项目经费类型：来自属性表
	private java.lang.String proMoneyType;
		
	@Column(name = "PRO_MONEY", unique = false, nullable = true, insertable = true, updatable = true, length = 126)
	//项目经费
	private java.lang.Double proMoney;
		
	@Column(name = "PRO_TOTAL_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//项目总人数
	private Integer proTotalNum;
		
	@Column(name = "PLAN_RESOURCE", unique = false, nullable = true, insertable = true, updatable = true, length = 126)
	//项目学时
	private java.lang.Integer planResource;
		
	@Column(name = "PLAN_RESOURCE_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//项目学分
	private Integer planResourceType;
		
	@Column(name = "RU_CREDIT_NUM", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//学分转换规则总数：<=5,从默认学分转换规则表获取
	private Integer ruCreditNum;
	
	@Column(name = "RU1_INPUT_AMOUNT", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//规则1-投入(学时、篇、部、项、年等）
	private java.lang.String ru1InputAmount;
		
	@Column(name = "RU1_OUTPUT_AMOUNT", unique = false, nullable = true, insertable = true, updatable = true, length = 126)
	//规则1-产出(学分）
	private java.lang.Integer ru1OutputAmount;
		
	@Column(name = "RU2_INPUT_AMOUNT", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//规则2-投入(学时、篇、部、项、年等）
	private java.lang.String ru2InputAmount;
		
	@Column(name = "RU2_OUTPUT_AMOUNT", unique = false, nullable = true, insertable = true, updatable = true, length = 126)
	//规则2-产出(学分）
	private java.lang.Integer ru2OutputAmount;
		
	@Column(name = "RU3_INPUT_AMOUNT", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//规则3-投入(学时、篇、部、项、年等）
	private java.lang.String ru3InputAmount;
		
	@Column(name = "RU3_OUTPUT_AMOUNT", unique = false, nullable = true, insertable = true, updatable = true, length = 126)
	//规则3-产出(学分）
	private java.lang.Integer ru3OutputAmount;
		
	@Column(name = "RU4_INPUT_AMOUNT", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//规则4-投入(学时、篇、部、项、年等）
	private java.lang.String ru4InputAmount;
		
	@Column(name = "RU4_OUTPUT_AMOUNT", unique = false, nullable = true, insertable = true, updatable = true, length = 126)
	//规则4-产出(学分）
	private java.lang.Integer ru4OutputAmount;
		
	@Column(name = "RU5_INPUT_AMOUNT", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//规则5-投入(学时、篇、部、项、年等）
	private java.lang.String ru5InputAmount;
		
	@Column(name = "RU5_OUTPUT_AMOUNT", unique = false, nullable = true, insertable = true, updatable = true, length = 126)
	//规则5-产出(学分）
	private java.lang.Integer ru5OutputAmount;
		
	@Column(name = "PRO_DETAIL", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	//项目内容描述
	private java.lang.String proDetail;
		
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
		
	@Column(name = "CRE_DEPART_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//创建部门ID
	private java.lang.String creDepartId;
		
	@Column(name = "CRE_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//创建用户ID
	private java.lang.String creUserId;
		
	@Column(name = "CRE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//创建时间
	private java.util.Date creTime;
		
	@Column(name = "AUDIT_STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//审核状态
	private Integer auditStatus;
		
	@Column(name = "AUDIT_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//审核时间
	private java.util.Date auditTime;
		
	@Column(name = "AUDIT_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//审核用户ID
	private java.lang.String auditUserId;
		
	@Column(name = "AUDIT_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//审核类型：新增／删除
	private java.lang.String auditType;
		
	@Column(name = "PROCESS_STAUS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//项目实施状态集合
	private Integer processStaus;
		
	@Column(name = "PROCESS_STAUS_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//项目实施状态时间
	private java.util.Date processStausTime;
		
	@Column(name = "DEL_FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//删除标识
	private Integer delFlag;
		
	@Column(name = "DEL_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//删除时间
	private java.util.Date delTime;
		
	@Column(name = "DEL_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//删除用户ID
	private java.lang.String delUserId;
		
	@Column(name = "SF_IT_ABILITY_FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//是否属于信息技术应用能力标识
	private Integer sfItAbilityFlag;

	@Column(name = "PRO_START_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//开始时间
	private java.util.Date proStartTime;
	
	@Column(name = "PRO_END_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//结束时间
	private java.util.Date proEndTime;

	@Column(name = "IS_BL", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//是否是补录项目。0：不是；是：1
	private Integer isBl;
	
	
	@Column(name = "RU1_TYPE_CUSTOM", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//学分转换规则自定义类型1
	private java.lang.String ru1TypeCustom;
	
	@Column(name = "RU2_TYPE_CUSTOM", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//学分转换规则自定义类型1
	private java.lang.String ru2TypeCustom;
	
	@Column(name = "RU3_TYPE_CUSTOM", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//学分转换规则自定义类型1
	private java.lang.String ru3TypeCustom;
	
	@Column(name = "RU4_TYPE_CUSTOM", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//学分转换规则自定义类型1
	private java.lang.String ru4TypeCustom;
	
	@Column(name = "RU5_TYPE_CUSTOM", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//学分转换规则自定义类型1
	private java.lang.String ru5TypeCustom;

	
	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getOrg3rdId() {
		return this.org3rdId;
	}
	
	public void setOrg3rdId(java.lang.String value) {
		this.org3rdId = value;
	}
	
	public java.lang.String getProRefCode() {
		return this.proRefCode;
	}
	
	public void setProRefCode(java.lang.String value) {
		this.proRefCode = value;
	}
	
	public java.lang.String getProName() {
		return this.proName;
	}
	
	public void setProName(java.lang.String value) {
		this.proName = value;
	}
	
	public java.lang.String getProLevel() {
		return this.proLevel;
	}
	
	public void setProLevel(java.lang.String value) {
		this.proLevel = value;
	}
	
	public java.lang.String getProType() {
		return this.proType;
	}
	
	public void setProType(java.lang.String value) {
		this.proType = value;
	}
	
	public java.lang.String getTyear() {
		return this.tyear;
	}
	
	public void setTyear(java.lang.String value) {
		this.tyear = value;
	}
	
	public java.lang.String getProDealType() {
		return this.proDealType;
	}
	
	public void setProDealType(java.lang.String value) {
		this.proDealType = value;
	}
	
	public java.lang.String getFaceStudySet() {
		return this.faceStudySet;
	}
	
	public void setFaceStudySet(java.lang.String value) {
		this.faceStudySet = value;
	}
	
	public java.lang.String getFacePostSet() {
		return this.facePostSet;
	}
	
	public void setFacePostSet(java.lang.String value) {
		this.facePostSet = value;
	}
	
	public java.lang.String getProMoneyType() {
		return this.proMoneyType;
	}
	
	public void setProMoneyType(java.lang.String value) {
		this.proMoneyType = value;
	}
	
	public java.lang.Double getProMoney() {
		return this.proMoney;
	}
	
	public void setProMoney(java.lang.Double value) {
		this.proMoney = value;
	}
	
	public Integer getProTotalNum() {
		return this.proTotalNum;
	}
	
	public void setProTotalNum(Integer value) {
		this.proTotalNum = value;
	}
	
	public java.lang.Integer getPlanResource() {
		return this.planResource;
	}
	
	public void setPlanResource(java.lang.Integer value) {
		this.planResource = value;
	}
	
	public Integer getPlanResourceType() {
		return this.planResourceType;
	}
	
	public void setPlanResourceType(Integer value) {
		this.planResourceType = value;
	}
	
	public Integer getRuCreditNum() {
		return this.ruCreditNum;
	}
	
	public void setRuCreditNum(Integer value) {
		this.ruCreditNum = value;
	}
	
	public java.lang.String getRu1InputAmount() {
		return this.ru1InputAmount;
	}
	
	public void setRu1InputAmount(java.lang.String value) {
		this.ru1InputAmount = value;
	}
	
	public java.lang.Integer getRu1OutputAmount() {
		return this.ru1OutputAmount;
	}
	
	public void setRu1OutputAmount(java.lang.Integer value) {
		this.ru1OutputAmount = value;
	}
	
	public java.lang.String getRu2InputAmount() {
		return this.ru2InputAmount;
	}
	
	public void setRu2InputAmount(java.lang.String value) {
		this.ru2InputAmount = value;
	}
	
	public java.lang.Integer getRu2OutputAmount() {
		return this.ru2OutputAmount;
	}
	
	public void setRu2OutputAmount(java.lang.Integer value) {
		this.ru2OutputAmount = value;
	}
	
	public java.lang.String getRu3InputAmount() {
		return this.ru3InputAmount;
	}
	
	public void setRu3InputAmount(java.lang.String value) {
		this.ru3InputAmount = value;
	}
	
	public java.lang.Integer getRu3OutputAmount() {
		return this.ru3OutputAmount;
	}
	
	public void setRu3OutputAmount(java.lang.Integer value) {
		this.ru3OutputAmount = value;
	}
	
	public java.lang.String getRu4InputAmount() {
		return this.ru4InputAmount;
	}
	
	public void setRu4InputAmount(java.lang.String value) {
		this.ru4InputAmount = value;
	}
	
	public java.lang.Integer getRu4OutputAmount() {
		return this.ru4OutputAmount;
	}
	
	public void setRu4OutputAmount(java.lang.Integer value) {
		this.ru4OutputAmount = value;
	}
	
	public java.lang.String getRu5InputAmount() {
		return this.ru5InputAmount;
	}
	
	public void setRu5InputAmount(java.lang.String value) {
		this.ru5InputAmount = value;
	}
	
	public java.lang.Integer getRu5OutputAmount() {
		return this.ru5OutputAmount;
	}
	
	public void setRu5OutputAmount(java.lang.Integer value) {
		this.ru5OutputAmount = value;
	}
	
	public java.lang.String getProDetail() {
		return this.proDetail;
	}
	
	public void setProDetail(java.lang.String value) {
		this.proDetail = value;
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
	
	public java.lang.String getCreDepartId() {
		return this.creDepartId;
	}
	
	public void setCreDepartId(java.lang.String value) {
		this.creDepartId = value;
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
	
	public Integer getAuditStatus() {
		return this.auditStatus;
	}
	
	public void setAuditStatus(Integer value) {
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
	
	public Integer getProcessStaus() {
		return this.processStaus;
	}
	
	public void setProcessStaus(Integer value) {
		this.processStaus = value;
	}
	
	public java.util.Date getProcessStausTime() {
		return this.processStausTime;
	}
	
	public void setProcessStausTime(java.util.Date value) {
		this.processStausTime = value;
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
	
	public Integer getSfItAbilityFlag() {
		return this.sfItAbilityFlag;
	}
	
	public void setSfItAbilityFlag(Integer value) {
		this.sfItAbilityFlag = value;
	}
	

	

	public java.util.Date getProStartTime() {
		return proStartTime;
	}

	public void setProStartTime(java.util.Date proStartTime) {
		this.proStartTime = proStartTime;
	}

	public java.util.Date getProEndTime() {
		return proEndTime;
	}

	public void setProEndTime(java.util.Date proEndTime) {
		this.proEndTime = proEndTime;
	}

	public Integer getIsBl() {
		return isBl;
	}

	public void setIsBl(Integer isBl) {
		this.isBl = isBl;
	}

	
	public java.lang.String getRu1TypeCustom() {
		return ru1TypeCustom;
	}

	public void setRu1TypeCustom(java.lang.String ru1TypeCustom) {
		this.ru1TypeCustom = ru1TypeCustom;
	}

	public java.lang.String getRu2TypeCustom() {
		return ru2TypeCustom;
	}

	public void setRu2TypeCustom(java.lang.String ru2TypeCustom) {
		this.ru2TypeCustom = ru2TypeCustom;
	}

	public java.lang.String getRu3TypeCustom() {
		return ru3TypeCustom;
	}

	public void setRu3TypeCustom(java.lang.String ru3TypeCustom) {
		this.ru3TypeCustom = ru3TypeCustom;
	}

	public java.lang.String getRu4TypeCustom() {
		return ru4TypeCustom;
	}

	public void setRu4TypeCustom(java.lang.String ru4TypeCustom) {
		this.ru4TypeCustom = ru4TypeCustom;
	}

	public java.lang.String getRu5TypeCustom() {
		return ru5TypeCustom;
	}

	public void setRu5TypeCustom(java.lang.String ru5TypeCustom) {
		this.ru5TypeCustom = ru5TypeCustom;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("ProId",getId())
			.append("Org3rdId",getOrg3rdId())
			.append("ProRefCode",getProRefCode())
			.append("ProName",getProName())
			.append("ProLevel",getProLevel())
			.append("ProType",getProType())
			.append("Tyear",getTyear())
			.append("ProDealType",getProDealType())
			.append("FaceStudySet",getFaceStudySet())
			.append("FacePostSet",getFacePostSet())
			.append("ProMoneyType",getProMoneyType())
			.append("ProMoney",getProMoney())
			.append("ProTotalNum",getProTotalNum())
			.append("PlanResource",getPlanResource())
			.append("PlanResourceType",getPlanResourceType())
			.append("RuCreditNum",getRuCreditNum())
			.append("Ru1InputAmount",getRu1InputAmount())
			.append("Ru1OutputAmount",getRu1OutputAmount())
			.append("Ru2InputAmount",getRu2InputAmount())
			.append("Ru2OutputAmount",getRu2OutputAmount())
			.append("Ru3InputAmount",getRu3InputAmount())
			.append("Ru3OutputAmount",getRu3OutputAmount())
			.append("Ru4InputAmount",getRu4InputAmount())
			.append("Ru4OutputAmount",getRu4OutputAmount())
			.append("Ru5InputAmount",getRu5InputAmount())
			.append("Ru5OutputAmount",getRu5OutputAmount())
			.append("ProDetail",getProDetail())
			.append("ProvinceId",getProvinceId())
			.append("CityId",getCityId())
			.append("CountyId",getCountyId())
			.append("SchoolId",getSchoolId())
			.append("CreDepartId",getCreDepartId())
			.append("CreUserId",getCreUserId())
			.append("CreTime",getCreTime())
			.append("AuditStatus",getAuditStatus())
			.append("AuditTime",getAuditTime())
			.append("AuditUserId",getAuditUserId())
			.append("AuditType",getAuditType())
			.append("ProcessStaus",getProcessStaus())
			.append("ProcessStausTime",getProcessStausTime())
			.append("DelFlag",getDelFlag())
			.append("DelTime",getDelTime())
			.append("DelUserId",getDelUserId())
			.append("SfItAbilityFlag",getSfItAbilityFlag())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseCreditProject == false) return false;
		if(this == obj) return true;
		BaseCreditProject other = (BaseCreditProject)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

