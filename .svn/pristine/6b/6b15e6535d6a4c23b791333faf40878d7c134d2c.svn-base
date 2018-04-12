/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package org.my431.base.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.my431.util.CloneEntity;
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

//BaseSchoolFsb
@Entity
@Table(name = "BASE_SCHOOL_FSB")
public class BaseSchoolFsb extends CloneEntity implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "SCHOOL_ID", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//schoolId
	private java.lang.String id;
		
	@Column(name = "SCHOOL_NO", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	//学校标识码
	private java.lang.String schoolNo;
		
	@Column(name = "SCHOOL_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//学校名称
	private java.lang.String schoolName;
		
	@Column(name = "SCHOOL_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//学校类型：国标
	private java.lang.String schoolType;
		
	@Column(name = "XXJGJBZM", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//学校(机构)举办者码
	private java.lang.String xxjgjbzm;
		
	@Column(name = "XXJGXZLBM", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//"学校(机构)性质类别码， 非普通高校的性质类别码均为00；"
	private java.lang.String xxjgxzlbm;
		
	@Column(name = "XXJGSZDCXFLM", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//学校(机构)所在地城乡分类码
	private java.lang.String xxjgszdcxflm;
		
	@Column(name = "DLSZSSMZXXJG", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//独立设置少数民族学校(机构)
	private java.lang.String dlszssmzxxjg;
		
	@Column(name = "SFXSLXX", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//是否新设立学校(机构)
	private java.lang.String sfxslxx;
		
	@Column(name = "SFCX", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//是否撤销(在用)，增加自定义状态[撤并中]
	private java.lang.String sfcx;
		
	@Column(name = "CBDXXJGBSM", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	//撤并多个单位时用逗号分开
	private java.lang.String cbdxxjgbsm;
		
	@Column(name = "UUID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//国家教师表UUID
	private java.lang.String uuid;
		
	@Column(name = "SSXD", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//所属学段
	private java.lang.String ssxd;
		
	@Column(name = "GXXZ", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//高校性质(01综合大学,02理工院校,03农业院校,04林业院校,05医药院校,06师范院校,07语文院校,08财经院校,09政法院校,10体育院校,11艺术院校,12民族院校)
	private java.lang.String gxxz;
		
	@Column(name = "SFBSXX", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//是否部属学校(0否,1是)
	private java.lang.String sfbsxx;
		
	@Column(name = "SFSSMZSYJX", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//是否少数民族双语教学(0否,1一类模式,2二类模式,3三类模式)
	private java.lang.String sfssmzsyjx;
		
	@Column(name = "CJR", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//创建人
	private java.lang.String cjr;
		
	@Column(name = "CJSJ", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//创建时间
	private java.util.Date cjsj;
		
	@Column(name = "XGR", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//修改人
	private java.lang.String xgr;
		
	@Column(name = "XGSJ", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//修改时间
	private java.util.Date xgsj;
		
	@Column(name = "PROVINCE_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//provinceId
	private java.lang.String provinceId;
		
	@Column(name = "CITY_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//cityId
	private java.lang.String cityId;
		
	@Column(name = "COUNTY_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//countyId
	private java.lang.String countyId;
		
	@Column(name = "AREA_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//areaId
	private java.lang.String areaId;
		
	@Column(name = "XXJGDZDM", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//学校机构所在地代码
	private java.lang.String xxjgdzdm;
		
	@Column(name = "ADDRESS", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	//学校地址
	private java.lang.String address;
		
	@Column(name = "ZIP_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//邮编
	private java.lang.String zipCode;
		
	@Column(name = "TEL", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//电话号码
	private java.lang.String tel;
		
	@Column(name = "CONTACTER", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//联系人
	private java.lang.String contacter;
		
	@Column(name = "EMAIL", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//邮箱
	private java.lang.String email;
		
	@Column(name = "STAFF_CNT_NB", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//教职工数量_年报
	private Double staffCntNb;
		
	@Column(name = "TEACHER_CNT_NB", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//专任教师数量_年报
	private Double teacherCntNb;
		
	@Column(name = "STUDENT_NUM_YEY", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//幼儿园学生数量
	private Double studentNumYey;
		
	@Column(name = "STUDENT_NUM_XX", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//小学学生数量
	private Double studentNumXx;
		
	@Column(name = "STUDENT_NUM_CZ", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//初中学生数量
	private Double studentNumCz;
		
	@Column(name = "STUDENT_NUM_GZ", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//高中学生数量
	private Double studentNumGz;
		
	@Column(name = "STUDENT_NUM_ZZ", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//中职学生数量
	private Double studentNumZz;
		
	@Column(name = "STUDENT_NUM_GX", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//高职/专科/本科学生数量
	private Double studentNumGx;
		
	@Column(name = "STUDENT_NUM_QT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//不能归属以上学生字段的学生数
	private Double studentNumQt;
		
	@Column(name = "DELETE_FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//删除标志
	private Integer deleteFlag;
		
	@Column(name = "DELETE_USER", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//删除用户
	private java.lang.String deleteUser;
		
	@Column(name = "DELETE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//删除时间
	private java.util.Date deleteTime;
		
	@Column(name = "COMMENTS", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	//备注
	private java.lang.String comments;
	
	@Column(name = "FSBBXLXM", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//附设班办学类型码，附设班表有值
	private java.lang.String fsbbxlxm;


	

	
	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getSchoolNo() {
		return this.schoolNo;
	}
	
	public void setSchoolNo(java.lang.String value) {
		this.schoolNo = value;
	}
	
	public java.lang.String getSchoolName() {
		return this.schoolName;
	}
	
	public void setSchoolName(java.lang.String value) {
		this.schoolName = value;
	}
	
	public java.lang.String getSchoolType() {
		return this.schoolType;
	}
	
	public void setSchoolType(java.lang.String value) {
		this.schoolType = value;
	}
	
	public java.lang.String getXxjgjbzm() {
		return this.xxjgjbzm;
	}
	
	public void setXxjgjbzm(java.lang.String value) {
		this.xxjgjbzm = value;
	}
	
	public java.lang.String getXxjgxzlbm() {
		return this.xxjgxzlbm;
	}
	
	public void setXxjgxzlbm(java.lang.String value) {
		this.xxjgxzlbm = value;
	}
	
	public java.lang.String getXxjgszdcxflm() {
		return this.xxjgszdcxflm;
	}
	
	public void setXxjgszdcxflm(java.lang.String value) {
		this.xxjgszdcxflm = value;
	}
	
	public java.lang.String getDlszssmzxxjg() {
		return this.dlszssmzxxjg;
	}
	
	public void setDlszssmzxxjg(java.lang.String value) {
		this.dlszssmzxxjg = value;
	}
	
	public java.lang.String getSfxslxx() {
		return this.sfxslxx;
	}
	
	public void setSfxslxx(java.lang.String value) {
		this.sfxslxx = value;
	}
	
	public java.lang.String getSfcx() {
		return this.sfcx;
	}
	
	public void setSfcx(java.lang.String value) {
		this.sfcx = value;
	}
	
	public java.lang.String getCbdxxjgbsm() {
		return this.cbdxxjgbsm;
	}
	
	public void setCbdxxjgbsm(java.lang.String value) {
		this.cbdxxjgbsm = value;
	}
	
	public java.lang.String getUuid() {
		return this.uuid;
	}
	
	public void setUuid(java.lang.String value) {
		this.uuid = value;
	}
	
	public java.lang.String getSsxd() {
		return this.ssxd;
	}
	
	public void setSsxd(java.lang.String value) {
		this.ssxd = value;
	}
	
	public java.lang.String getGxxz() {
		return this.gxxz;
	}
	
	public void setGxxz(java.lang.String value) {
		this.gxxz = value;
	}
	
	public java.lang.String getSfbsxx() {
		return this.sfbsxx;
	}
	
	public void setSfbsxx(java.lang.String value) {
		this.sfbsxx = value;
	}
	
	public java.lang.String getSfssmzsyjx() {
		return this.sfssmzsyjx;
	}
	
	public void setSfssmzsyjx(java.lang.String value) {
		this.sfssmzsyjx = value;
	}
	
	public java.lang.String getCjr() {
		return this.cjr;
	}
	
	public void setCjr(java.lang.String value) {
		this.cjr = value;
	}
	
	public java.util.Date getCjsj() {
		return this.cjsj;
	}
	
	public void setCjsj(java.util.Date value) {
		this.cjsj = value;
	}
	
	public java.lang.String getXgr() {
		return this.xgr;
	}
	
	public void setXgr(java.lang.String value) {
		this.xgr = value;
	}
	
	public java.util.Date getXgsj() {
		return this.xgsj;
	}
	
	public void setXgsj(java.util.Date value) {
		this.xgsj = value;
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
	
	public java.lang.String getXxjgdzdm() {
		return this.xxjgdzdm;
	}
	
	public void setXxjgdzdm(java.lang.String value) {
		this.xxjgdzdm = value;
	}
	
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String value) {
		this.address = value;
	}
	
	public java.lang.String getZipCode() {
		return this.zipCode;
	}
	
	public void setZipCode(java.lang.String value) {
		this.zipCode = value;
	}
	
	public java.lang.String getTel() {
		return this.tel;
	}
	
	public void setTel(java.lang.String value) {
		this.tel = value;
	}
	
	public java.lang.String getContacter() {
		return this.contacter;
	}
	
	public void setContacter(java.lang.String value) {
		this.contacter = value;
	}
	
	public java.lang.String getEmail() {
		return this.email;
	}
	
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	
	public Double getStaffCntNb() {
		return this.staffCntNb;
	}
	
	public void setStaffCntNb(Double value) {
		this.staffCntNb = value;
	}
	
	public Double getTeacherCntNb() {
		return this.teacherCntNb;
	}
	
	public void setTeacherCntNb(Double value) {
		this.teacherCntNb = value;
	}
	
	public Double getStudentNumYey() {
		return this.studentNumYey;
	}
	
	public void setStudentNumYey(Double value) {
		this.studentNumYey = value;
	}
	
	public Double getStudentNumXx() {
		return this.studentNumXx;
	}
	
	public void setStudentNumXx(Double value) {
		this.studentNumXx = value;
	}
	
	public Double getStudentNumCz() {
		return this.studentNumCz;
	}
	
	public void setStudentNumCz(Double value) {
		this.studentNumCz = value;
	}
	
	public Double getStudentNumGz() {
		return this.studentNumGz;
	}
	
	public void setStudentNumGz(Double value) {
		this.studentNumGz = value;
	}
	
	public Double getStudentNumZz() {
		return this.studentNumZz;
	}
	
	public void setStudentNumZz(Double value) {
		this.studentNumZz = value;
	}
	
	public Double getStudentNumGx() {
		return this.studentNumGx;
	}
	
	public void setStudentNumGx(Double value) {
		this.studentNumGx = value;
	}
	
	public Double getStudentNumQt() {
		return this.studentNumQt;
	}
	
	public void setStudentNumQt(Double value) {
		this.studentNumQt = value;
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
	
	public java.util.Date getDeleteTime() {
		return this.deleteTime;
	}
	
	public void setDeleteTime(java.util.Date value) {
		this.deleteTime = value;
	}
	
	public java.lang.String getComments() {
		return this.comments;
	}
	
	public void setComments(java.lang.String value) {
		this.comments = value;
	}

	public java.lang.String getFsbbxlxm() {
		return fsbbxlxm;
	}

	public void setFsbbxlxm(java.lang.String fsbbxlxm) {
		this.fsbbxlxm = fsbbxlxm;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("SchoolId",getId())
			.append("SchoolNo",getSchoolNo())
			.append("SchoolName",getSchoolName())
			.append("SchoolType",getSchoolType())
			.append("Xxjgjbzm",getXxjgjbzm())
			.append("Xxjgxzlbm",getXxjgxzlbm())
			.append("Xxjgszdcxflm",getXxjgszdcxflm())
			.append("Dlszssmzxxjg",getDlszssmzxxjg())
			.append("Sfxslxx",getSfxslxx())
			.append("Sfcx",getSfcx())
			.append("Cbdxxjgbsm",getCbdxxjgbsm())
			.append("Uuid",getUuid())
			.append("Ssxd",getSsxd())
			.append("Gxxz",getGxxz())
			.append("Sfbsxx",getSfbsxx())
			.append("Sfssmzsyjx",getSfssmzsyjx())
			.append("Cjr",getCjr())
			.append("Cjsj",getCjsj())
			.append("Xgr",getXgr())
			.append("Xgsj",getXgsj())
			.append("ProvinceId",getProvinceId())
			.append("CityId",getCityId())
			.append("CountyId",getCountyId())
			.append("AreaId",getAreaId())
			.append("Xxjgdzdm",getXxjgdzdm())
			.append("Address",getAddress())
			.append("ZipCode",getZipCode())
			.append("Tel",getTel())
			.append("Contacter",getContacter())
			.append("Email",getEmail())
			.append("StaffCntNb",getStaffCntNb())
			.append("TeacherCntNb",getTeacherCntNb())
			.append("StudentNumYey",getStudentNumYey())
			.append("StudentNumXx",getStudentNumXx())
			.append("StudentNumCz",getStudentNumCz())
			.append("StudentNumGz",getStudentNumGz())
			.append("StudentNumZz",getStudentNumZz())
			.append("StudentNumGx",getStudentNumGx())
			.append("StudentNumQt",getStudentNumQt())
			.append("DeleteFlag",getDeleteFlag())
			.append("DeleteUser",getDeleteUser())
			.append("DeleteTime",getDeleteTime())
			.append("Comment",getComments())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseSchoolFsb == false) return false;
		if(this == obj) return true;
		BaseSchoolFsb other = (BaseSchoolFsb)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

