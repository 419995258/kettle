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

//用户表（包括各种类型的用户）
@Entity
@Table(name = "BASE_USER")
public class BaseUser implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "USER_ID", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//userId
	private java.lang.String id;
	@Column(name = "USER_NO", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//个人标识
	private java.lang.String userNo;
	@Column(name = "LOGIN_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 200)
	//登录名
	private java.lang.String loginName;
	@Column(name = "PASSWORD", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//密码
	
	private java.lang.String password;
	
	@Column(name = "STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//审核状态  0:待审核  1:审核通过 2::审核未通过
	private java.lang.String status;
		
	@Column(name = "REAL_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//真实姓名
	private java.lang.String realName;
		
	@Column(name = "FORMER_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//曾用名
	private java.lang.String formerName;
		
	@Column(name = "PHOTO_PATH", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//头像路径
	private java.lang.String photoPath;
		
	@Column(name = "OFFICE_PHONE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//办公电话
	private java.lang.String officePhone;
		
	@Column(name = "MOBILE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//移动电话
	private java.lang.String mobile;
		
	@Column(name = "EMAIL", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//email
	private java.lang.String email;
		
	@Column(name = "ADDRESS", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//地址
	private java.lang.String address;
		
	@Column(name = "ZIPCODE", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	//邮编
	private java.lang.String zipcode;
		
	@Column(name = "QQ", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//QQ
	private java.lang.String qq;
		
	@Column(name = "LOGIN_CNT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//登录次数
	private Integer loginCnt;
		
	@Column(name = "LAST_LOGIN_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//最后一次登录时间 到秒
	private java.util.Date lastLoginTime;
		
	@Column(name = "LAST_LOGIN_IP", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//最后一次登录ip
	private java.lang.String lastLoginIp;
		
	@Column(name = "CRE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//创建时间
	private java.util.Date creTime;
		
	@Column(name = "MOD_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//修改时间
	private java.util.Date modTime;
		
	@Column(name = "PROVINCE_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//省id
	private java.lang.String provinceId;
		
	@Column(name = "CITY_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//市id
	private java.lang.String cityId;
		
	@Column(name = "COUNTY_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//县id
	private java.lang.String countyId;
		
	@Column(name = "SCHOOL_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//学校id
	private java.lang.String schoolId;
	
	@Column(name = "INSTITUTION_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//学校id
	private java.lang.String institutionId;
		
	@Column(name = "DEFAULT_ROLE_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//defaultRoleCode
	private java.lang.String defaultRoleCode;
		
	@Column(name = "IS_INI", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//是否初始化，1为初始化，0为未初始化
	private Integer isIni;
		
	@Column(name = "AREA_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//所属地区id
	private java.lang.String areaId;
		
	@Column(name = "INI_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//初始化时间
	private java.util.Date iniTime;
		
	@Column(name = "DELETE_FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	//删除标记 0：未删除 1：已删除
	private java.lang.String deleteFlag;
		
	@Column(name = "DELETE_USER", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//删除操作人
	private java.lang.String deleteUser;
		
	@Column(name = "DELETE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//删除操作时间
	private java.util.Date deleteTime;
		
	@Column(name = "CRE_USER", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//creUser
	private java.lang.String creUser;
		
	@Column(name = "MOD_USER", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//modUser
	private java.lang.String modUser;
		
	@Column(name = "IS_PASSWORD_VALIDATE", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//弱口令是否已验证 默认0 已验证：1 未验证：0
	private Integer isPasswordValidate;
		
	@Column(name = "VIEW_NOTE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//最后一次查看通知的时间
	private java.util.Date viewNoteTime;
		
	@Column(name = "ID_CARD_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	//身份证类型
	private java.lang.String idCardType;
		
	@Column(name = "ID_CARD", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//身份证号
	private java.lang.String idCard;
		
	@Column(name = "SEX", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//性别
	private java.lang.String sex;
		
	@Column(name = "TEACHER_NO", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	//教职工号
	private java.lang.String teacherNo;
		
	@Column(name = "BIRTHDAY", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//生日
	private java.util.Date birthday;
		
	@Column(name = "NATIONALITY", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//国籍
	private java.lang.String nationality;
		
	@Column(name = "NATIVE_PLACE", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//籍贯
	private java.lang.String nativePlace;
		
	@Column(name = "BIRTHLAND", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//出生地
	private java.lang.String birthland;
		
	@Column(name = "NATION", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//民族
	private java.lang.String nation;
		
	@Column(name = "POLITICS_STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//政治面貌
	private java.lang.String politicsStatus;
		
	@Column(name = "IS_MARRY", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//婚姻状况
	private Integer isMarry;
		
	@Column(name = "HEALTH_STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//健康状况
	private java.lang.String healthStatus;
		
	@Column(name = "FIRST_WORK_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//参加工作年月
	private java.util.Date firstWorkDate;
		
	@Column(name = "ENTER_SCHOOL_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//进入本校年月
	private java.util.Date enterSchoolDate;
		
	@Column(name = "TEACHER_SOURCE", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//教职工来源：例子：调入，应届毕业生
	private java.lang.String teacherSource;
		
	@Column(name = "TEACHER_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//教职工类别：专任教师
	private java.lang.String teacherType;
		
	@Column(name = "IS_IN_ESTABLISHMENT", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//是否在编
	private java.lang.String isInEstablishment;
		
	@Column(name = "USER_TEACHER_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//用人形式
	private java.lang.String userTeacherType;
		
	@Column(name = "CONTRACT_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//签订合同情况
	private java.lang.String contractType;
		
	@Column(name = "IS_GRADUATE", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//是否全日制师范类专业毕业
	private java.lang.String isGraduate;
		
	@Column(name = "IS_TJ_PX", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//是否受过特教专业培养培训
	private java.lang.String isTjPx;
		
	@Column(name = "IS_TJ_ZS", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//是否有特殊教育从业证书
	private java.lang.String isTjZs;
		
	@Column(name = "XX_LEVEL", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//信息技术应用能力
	private java.lang.String xxLevel;
		
	@Column(name = "IS_MF_SFS", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//是否属于免费（公费）师范生
	private java.lang.String isMfSfs;



	

	
	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getUserNo() {
		return this.userNo;
	}
	
	public void setUserNo(java.lang.String value) {
		this.userNo = value;
	}
	
	public java.lang.String getLoginName() {
		return this.loginName;
	}
	
	public void setLoginName(java.lang.String value) {
		this.loginName = value;
	}
	
	public java.lang.String getPassword() {
		return this.password;
	}
	
	public void setPassword(java.lang.String value) {
		this.password = value;
	}
	
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String value) {
		this.status = value;
	}
	
	public java.lang.String getRealName() {
		return this.realName;
	}
	
	public void setRealName(java.lang.String value) {
		this.realName = value;
	}
	
	public java.lang.String getFormerName() {
		return this.formerName;
	}
	
	public void setFormerName(java.lang.String value) {
		this.formerName = value;
	}
	
	public java.lang.String getPhotoPath() {
		return this.photoPath;
	}
	
	public void setPhotoPath(java.lang.String value) {
		this.photoPath = value;
	}
	
	public java.lang.String getOfficePhone() {
		return this.officePhone;
	}
	
	public void setOfficePhone(java.lang.String value) {
		this.officePhone = value;
	}
	
	public java.lang.String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(java.lang.String value) {
		this.mobile = value;
	}
	
	public java.lang.String getEmail() {
		return this.email;
	}
	
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
	public java.lang.String getZipcode() {
		return this.zipcode;
	}
	
	public void setZipcode(java.lang.String value) {
		this.zipcode = value;
	}
	
	public java.lang.String getQq() {
		return this.qq;
	}
	
	public void setQq(java.lang.String value) {
		this.qq = value;
	}
	
	public Integer getLoginCnt() {
		return this.loginCnt;
	}
	
	public void setLoginCnt(Integer value) {
		this.loginCnt = value;
	}
	
	public java.util.Date getLastLoginTime() {
		return this.lastLoginTime;
	}
	
	public void setLastLoginTime(java.util.Date value) {
		this.lastLoginTime = value;
	}
	
	public java.lang.String getLastLoginIp() {
		return this.lastLoginIp;
	}
	
	public void setLastLoginIp(java.lang.String value) {
		this.lastLoginIp = value;
	}
	
	public java.util.Date getCreTime() {
		return this.creTime;
	}
	
	public void setCreTime(java.util.Date value) {
		this.creTime = value;
	}
	
	public java.util.Date getModTime() {
		return this.modTime;
	}
	
	public void setModTime(java.util.Date value) {
		this.modTime = value;
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
	
	public java.lang.String getDefaultRoleCode() {
		return this.defaultRoleCode;
	}
	
	public void setDefaultRoleCode(java.lang.String value) {
		this.defaultRoleCode = value;
	}
	
	public Integer getIsIni() {
		return this.isIni;
	}
	
	public void setIsIni(Integer value) {
		this.isIni = value;
	}
	
	public java.lang.String getAreaId() {
		return this.areaId;
	}
	
	public void setAreaId(java.lang.String value) {
		this.areaId = value;
	}
	
	public java.util.Date getIniTime() {
		return this.iniTime;
	}
	
	public void setIniTime(java.util.Date value) {
		this.iniTime = value;
	}
	
	public java.lang.String getDeleteFlag() {
		return this.deleteFlag;
	}
	
	public void setDeleteFlag(java.lang.String value) {
		this.deleteFlag = value;
	}
	
	public java.lang.String getDeleteUser() {
		return this.deleteUser;
	}
	
	public void setDeleteUser(java.lang.String value) {
		this.deleteUser = value;
	}
	
	public java.util.Date getDeleteTime() {
		return this.deleteTime;
	}
	
	public void setDeleteTime(java.util.Date value) {
		this.deleteTime = value;
	}
	
	public java.lang.String getCreUser() {
		return this.creUser;
	}
	
	public void setCreUser(java.lang.String value) {
		this.creUser = value;
	}
	
	public java.lang.String getModUser() {
		return this.modUser;
	}
	
	public void setModUser(java.lang.String value) {
		this.modUser = value;
	}
	
	public Integer getIsPasswordValidate() {
		return this.isPasswordValidate;
	}
	
	public void setIsPasswordValidate(Integer value) {
		this.isPasswordValidate = value;
	}
	
	public java.util.Date getViewNoteTime() {
		return this.viewNoteTime;
	}
	
	public void setViewNoteTime(java.util.Date value) {
		this.viewNoteTime = value;
	}
	
	public java.lang.String getIdCardType() {
		return this.idCardType;
	}
	
	public void setIdCardType(java.lang.String value) {
		this.idCardType = value;
	}
	
	public java.lang.String getIdCard() {
		return this.idCard;
	}
	
	public void setIdCard(java.lang.String value) {
		this.idCard = value;
	}
	
	public java.lang.String getSex() {
		return this.sex;
	}
	
	public void setSex(java.lang.String value) {
		this.sex = value;
	}
	
	public java.lang.String getTeacherNo() {
		return this.teacherNo;
	}
	
	public void setTeacherNo(java.lang.String value) {
		this.teacherNo = value;
	}
	
	public java.util.Date getBirthday() {
		return this.birthday;
	}
	
	public void setBirthday(java.util.Date value) {
		this.birthday = value;
	}
	
	public java.lang.String getNationality() {
		return this.nationality;
	}
	
	public void setNationality(java.lang.String value) {
		this.nationality = value;
	}
	
	public java.lang.String getNativePlace() {
		return this.nativePlace;
	}
	
	public void setNativePlace(java.lang.String value) {
		this.nativePlace = value;
	}
	
	public java.lang.String getBirthland() {
		return this.birthland;
	}
	
	public void setBirthland(java.lang.String value) {
		this.birthland = value;
	}
	
	public java.lang.String getNation() {
		return this.nation;
	}
	
	public void setNation(java.lang.String value) {
		this.nation = value;
	}
	
	public java.lang.String getPoliticsStatus() {
		return this.politicsStatus;
	}
	
	public void setPoliticsStatus(java.lang.String value) {
		this.politicsStatus = value;
	}
	
	public Integer getIsMarry() {
		return this.isMarry;
	}
	
	public void setIsMarry(Integer value) {
		this.isMarry = value;
	}
	
	public java.lang.String getHealthStatus() {
		return this.healthStatus;
	}
	
	public void setHealthStatus(java.lang.String value) {
		this.healthStatus = value;
	}
	
	public java.util.Date getFirstWorkDate() {
		return this.firstWorkDate;
	}
	
	public void setFirstWorkDate(java.util.Date value) {
		this.firstWorkDate = value;
	}
	
	public java.util.Date getEnterSchoolDate() {
		return this.enterSchoolDate;
	}
	
	public void setEnterSchoolDate(java.util.Date value) {
		this.enterSchoolDate = value;
	}
	
	public java.lang.String getTeacherSource() {
		return this.teacherSource;
	}
	
	public void setTeacherSource(java.lang.String value) {
		this.teacherSource = value;
	}
	
	public java.lang.String getTeacherType() {
		return this.teacherType;
	}
	
	public void setTeacherType(java.lang.String value) {
		this.teacherType = value;
	}
	
	public java.lang.String getIsInEstablishment() {
		return this.isInEstablishment;
	}
	
	public void setIsInEstablishment(java.lang.String value) {
		this.isInEstablishment = value;
	}
	
	public java.lang.String getUserTeacherType() {
		return this.userTeacherType;
	}
	
	public void setUserTeacherType(java.lang.String value) {
		this.userTeacherType = value;
	}
	
	public java.lang.String getContractType() {
		return this.contractType;
	}
	
	public void setContractType(java.lang.String value) {
		this.contractType = value;
	}
	
	public java.lang.String getIsGraduate() {
		return this.isGraduate;
	}
	
	public void setIsGraduate(java.lang.String value) {
		this.isGraduate = value;
	}
	
	public java.lang.String getIsTjPx() {
		return this.isTjPx;
	}
	
	public void setIsTjPx(java.lang.String value) {
		this.isTjPx = value;
	}
	
	public java.lang.String getIsTjZs() {
		return this.isTjZs;
	}
	
	public void setIsTjZs(java.lang.String value) {
		this.isTjZs = value;
	}
	
	public java.lang.String getXxLevel() {
		return this.xxLevel;
	}
	
	public void setXxLevel(java.lang.String value) {
		this.xxLevel = value;
	}
	
	public java.lang.String getIsMfSfs() {
		return this.isMfSfs;
	}
	
	public void setIsMfSfs(java.lang.String value) {
		this.isMfSfs = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("UserId",getId())
			.append("UserNo",getUserNo())
			.append("LoginName",getLoginName())
			.append("Password",getPassword())
			.append("Status",getStatus())
			.append("RealName",getRealName())
			.append("FormerName",getFormerName())
			.append("PhotoPath",getPhotoPath())
			.append("OfficePhone",getOfficePhone())
			.append("Mobile",getMobile())
			.append("Email",getEmail())
			.append("Address",getAddress())
			.append("Zipcode",getZipcode())
			.append("Qq",getQq())
			.append("LoginCnt",getLoginCnt())
			.append("LastLoginTime",getLastLoginTime())
			.append("LastLoginIp",getLastLoginIp())
			.append("CreTime",getCreTime())
			.append("ModTime",getModTime())
			.append("ProviinceId",getProvinceId())
			.append("CityId",getCityId())
			.append("CountyId",getCountyId())
			.append("SchoolId",getSchoolId())
			.append("DefaultRoleCode",getDefaultRoleCode())
			.append("IsIni",getIsIni())
			.append("AreaId",getAreaId())
			.append("IniTime",getIniTime())
			.append("DeleteFlag",getDeleteFlag())
			.append("DeleteUser",getDeleteUser())
			.append("DeleteTime",getDeleteTime())
			.append("CreUser",getCreUser())
			.append("ModUser",getModUser())
			.append("IsPasswordValidate",getIsPasswordValidate())
			.append("ViewNoteTime",getViewNoteTime())
			.append("IdCardType",getIdCardType())
			.append("IdCard",getIdCard())
			.append("Sex",getSex())
			.append("TeacherNo",getTeacherNo())
			.append("Birthday",getBirthday())
			.append("Nationality",getNationality())
			.append("NativePlace",getNativePlace())
			.append("Birthland",getBirthland())
			.append("Nation",getNation())
			.append("PoliticsStatus",getPoliticsStatus())
			.append("IsMarry",getIsMarry())
			.append("HealthStatus",getHealthStatus())
			.append("FirstWorkDate",getFirstWorkDate())
			.append("EnterSchoolDate",getEnterSchoolDate())
			.append("TeacherSource",getTeacherSource())
			.append("TeacherType",getTeacherType())
			.append("IsInEstablishment",getIsInEstablishment())
			.append("UserTeacherType",getUserTeacherType())
			.append("ContractType",getContractType())
			.append("IsGraduate",getIsGraduate())
			.append("IsTjPx",getIsTjPx())
			.append("IsTjZs",getIsTjZs())
			.append("XxLevel",getXxLevel())
			.append("IsMfSfs",getIsMfSfs())
			.toString();
	}
	
	public java.lang.String getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(java.lang.String institutionId) {
		this.institutionId = institutionId;
	}

	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseUser == false) return false;
		if(this == obj) return true;
		BaseUser other = (BaseUser)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

