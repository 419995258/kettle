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

//BaseNote
@Entity
@Table(name = "BASE_NOTE")
public class BaseNote implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "NOTE_ID", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//noteId
	private java.lang.String id;
		
	@Column(name = "TITLE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//标题
	private java.lang.String title;
		
	@Column(name = "CONTENT_VALUE", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
	//内容
	private String content;
		
	@Column(name = "CRE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//creTime
	private java.util.Date creTime;
		
	@Column(name = "CRE_USER", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//creUser
	private java.lang.String creUser;
		
	@Column(name = "CRE_USER_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//creUserName
	private java.lang.String creUserName;
		
	@Column(name = "NOTE_LEVEL", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//紧急程度，由1-3，3最紧急
	private Integer noteLevel;
		
	@Column(name = "COLOR", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//存标题颜色，支持几种常见颜色
	private java.lang.String color;
		
	@Column(name = "TO_WHO", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	//note.towho.sys
	private java.lang.String toWho;
		
	@Column(name = "NOTE_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//通知类型 系统通知：note.sys 课程通知：note.course 会议通知：note.meet 教案通知：note.idesign 教研组通知：note.studio
	private java.lang.String noteType;
		
	@Column(name = "NEWS_PIC", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//新闻图片
	private java.lang.String newsPic;
		
	@Column(name = "INTRO", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	//内容简介
	private java.lang.String intro;
		
	@Column(name = "IS_TOP", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//1:置顶 0:非置顶
	private Integer isTop;
		
	@Column(name = "SET_TOP_USER", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//置顶人
	private java.lang.String setTopUser;
		
	@Column(name = "SET_TOP_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//置顶时间
	private java.util.Date setTopTime;
		
	@Column(name = "CONTENT_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//文本：       content.text word文档：content.word pdf文档：   content.pdf ppt文档：   content.ppt excel文档：content.excel
	private java.lang.String contentType;
		
	@Column(name = "MOE_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//教育部管理员发通知时，本字段写入国家区域代码
	private java.lang.String moeCode;
		
	@Column(name = "PROVINCE_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//省管理员发通知时，本字段写入该省区域代码
	private java.lang.String provinceCode;
		
	@Column(name = "CITY_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//市管理员发通知时，本字段写入本市区域代码
	private java.lang.String cityCode;
		
	@Column(name = "COUNTY_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//县管理员发通知时，本字段写入该县区域代码
	private java.lang.String countyCode;
		
	@Column(name = "MOE_ADMIN", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	//教育部管理员 是：1 不是：0
	private java.lang.String moeAdmin;
		
	@Column(name = "PROVINCE_ADMIN", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	//省管理员
	private java.lang.String provinceAdmin;
		
	@Column(name = "CITY_ADMIN", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	//市管理员
	private java.lang.String cityAdmin;
		
	@Column(name = "COUNTY_ADMIN", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	//县管理员
	private java.lang.String countyAdmin;
		
	@Column(name = "JXD", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	//教学点
	private java.lang.String jxd;
		
	@Column(name = "XX", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	//小学
	private java.lang.String xx;
		
	@Column(name = "CZ", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	//初中
	private java.lang.String cz;
		
	@Column(name = "GZ", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	//高中
	private java.lang.String gz;
		
	@Column(name = "JNYGZ", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	//九年一贯制
	private java.lang.String jnygz;
		
	@Column(name = "WZ", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	//完中
	private java.lang.String wz;
		
	@Column(name = "SENYGZ", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	//十二年一贯制
	private java.lang.String senygz;
		
	@Column(name = "ZZHI", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	//中职
	private java.lang.String zzhi;
		
	@Column(name = "GZHI", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	//高职
	private java.lang.String gzhi;
		
	@Column(name = "IS_STRONG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//加粗：1 不加粗：0 默认0
	private Integer isStrong;
	
	@Column(name = "AREA_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//地区id
	private String areaId;


	public BaseNote(){
	}

	
	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String value) {
		this.content = value;
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
	
	public java.lang.String getCreUserName() {
		return this.creUserName;
	}
	
	public void setCreUserName(java.lang.String value) {
		this.creUserName = value;
	}
	
	public Integer getNoteLevel() {
		return this.noteLevel;
	}
	
	public void setNoteLevel(Integer value) {
		this.noteLevel = value;
	}
	
	public java.lang.String getColor() {
		return this.color;
	}
	
	public void setColor(java.lang.String value) {
		this.color = value;
	}
	
	public java.lang.String getToWho() {
		return this.toWho;
	}
	
	public void setToWho(java.lang.String value) {
		this.toWho = value;
	}
	
	public java.lang.String getNoteType() {
		return this.noteType;
	}
	
	public void setNoteType(java.lang.String value) {
		this.noteType = value;
	}
	
	public java.lang.String getNewsPic() {
		return this.newsPic;
	}
	
	public void setNewsPic(java.lang.String value) {
		this.newsPic = value;
	}
	
	public java.lang.String getIntro() {
		return this.intro;
	}
	
	public void setIntro(java.lang.String value) {
		this.intro = value;
	}
	
	public Integer getIsTop() {
		return this.isTop;
	}
	
	public void setIsTop(Integer value) {
		this.isTop = value;
	}
	
	public java.lang.String getSetTopUser() {
		return this.setTopUser;
	}
	
	public void setSetTopUser(java.lang.String value) {
		this.setTopUser = value;
	}
	
	public java.util.Date getSetTopTime() {
		return this.setTopTime;
	}
	
	public void setSetTopTime(java.util.Date value) {
		this.setTopTime = value;
	}
	
	public java.lang.String getContentType() {
		return this.contentType;
	}
	
	public void setContentType(java.lang.String value) {
		this.contentType = value;
	}
	
	public java.lang.String getMoeCode() {
		return this.moeCode;
	}
	
	public void setMoeCode(java.lang.String value) {
		this.moeCode = value;
	}
	
	public java.lang.String getProvinceCode() {
		return this.provinceCode;
	}
	
	public void setProvinceCode(java.lang.String value) {
		this.provinceCode = value;
	}
	
	public java.lang.String getCityCode() {
		return this.cityCode;
	}
	
	public void setCityCode(java.lang.String value) {
		this.cityCode = value;
	}
	
	public java.lang.String getCountyCode() {
		return this.countyCode;
	}
	
	public void setCountyCode(java.lang.String value) {
		this.countyCode = value;
	}
	
	public java.lang.String getMoeAdmin() {
		return this.moeAdmin;
	}
	
	public void setMoeAdmin(java.lang.String value) {
		this.moeAdmin = value;
	}
	
	public java.lang.String getProvinceAdmin() {
		return this.provinceAdmin;
	}
	
	public void setProvinceAdmin(java.lang.String value) {
		this.provinceAdmin = value;
	}
	
	public java.lang.String getCityAdmin() {
		return this.cityAdmin;
	}
	
	public void setCityAdmin(java.lang.String value) {
		this.cityAdmin = value;
	}
	
	public java.lang.String getCountyAdmin() {
		return this.countyAdmin;
	}
	
	public void setCountyAdmin(java.lang.String value) {
		this.countyAdmin = value;
	}
	
	public java.lang.String getJxd() {
		return this.jxd;
	}
	
	public void setJxd(java.lang.String value) {
		this.jxd = value;
	}
	
	public java.lang.String getXx() {
		return this.xx;
	}
	
	public void setXx(java.lang.String value) {
		this.xx = value;
	}
	
	public java.lang.String getCz() {
		return this.cz;
	}
	
	public void setCz(java.lang.String value) {
		this.cz = value;
	}
	
	public java.lang.String getGz() {
		return this.gz;
	}
	
	public void setGz(java.lang.String value) {
		this.gz = value;
	}
	
	public java.lang.String getJnygz() {
		return this.jnygz;
	}
	
	public void setJnygz(java.lang.String value) {
		this.jnygz = value;
	}
	
	public java.lang.String getWz() {
		return this.wz;
	}
	
	public void setWz(java.lang.String value) {
		this.wz = value;
	}
	
	public java.lang.String getSenygz() {
		return this.senygz;
	}
	
	public void setSenygz(java.lang.String value) {
		this.senygz = value;
	}
	
	public java.lang.String getZzhi() {
		return this.zzhi;
	}
	
	public void setZzhi(java.lang.String value) {
		this.zzhi = value;
	}
	
	public java.lang.String getGzhi() {
		return this.gzhi;
	}
	
	public void setGzhi(java.lang.String value) {
		this.gzhi = value;
	}
	
	public Integer getIsStrong() {
		return this.isStrong;
	}
	
	public void setIsStrong(Integer value) {
		this.isStrong = value;
	}
	

	public String getAreaId() {
		return areaId;
	}


	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}


	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("NoteId",getId())
			.append("Title",getTitle())
			.append("Content",getContent())
			.append("CreTime",getCreTime())
			.append("CreUser",getCreUser())
			.append("CreUserName",getCreUserName())
			.append("NoteLevel",getNoteLevel())
			.append("Color",getColor())
			.append("ToWho",getToWho())
			.append("NoteType",getNoteType())
			.append("NewsPic",getNewsPic())
			.append("Intro",getIntro())
			.append("IsTop",getIsTop())
			.append("SetTopUser",getSetTopUser())
			.append("SetTopTime",getSetTopTime())
			.append("ContentType",getContentType())
			.append("MoeCode",getMoeCode())
			.append("ProvinceCode",getProvinceCode())
			.append("CityCode",getCityCode())
			.append("CountyCode",getCountyCode())
			.append("MoeAdmin",getMoeAdmin())
			.append("ProvinceAdmin",getProvinceAdmin())
			.append("CityAdmin",getCityAdmin())
			.append("CountyAdmin",getCountyAdmin())
			.append("Jxd",getJxd())
			.append("Xx",getXx())
			.append("Cz",getCz())
			.append("Gz",getGz())
			.append("Jnygz",getJnygz())
			.append("Wz",getWz())
			.append("Senygz",getSenygz())
			.append("Zzhi",getZzhi())
			.append("Gzhi",getGzhi())
			.append("IsStrong",getIsStrong())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseNote == false) return false;
		if(this == obj) return true;
		BaseNote other = (BaseNote)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

