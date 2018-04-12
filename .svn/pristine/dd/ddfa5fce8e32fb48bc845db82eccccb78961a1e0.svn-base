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

//StatLoginData
@Entity
@Table(name = "STAT_LOGIN_DATA")
public class StatLoginData implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//id
	private java.lang.String id;
		
	@Column(name = "AREA_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//areaId
	private java.lang.String areaId;
		
	@Column(name = "AREA_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//areaName
	private java.lang.String areaName;
		
	@Column(name = "AREA_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//areaCode
	private java.lang.String areaCode;
		
	@Column(name = "PARENT_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//parentCode
	private java.lang.String parentCode;
		
	@Column(name = "SUMTYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//sumtype
	private Integer sumtype;
		
	@Column(name = "MLAST1_CNT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//mlast1Cnt
	private Integer mlast1Cnt;
		
	@Column(name = "MLAST2_CNT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//mlast2Cnt
	private Integer mlast2Cnt;
		
	@Column(name = "MLAST3_CNT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//mlast3Cnt
	private Integer mlast3Cnt;
		
	@Column(name = "MLAST4_CNT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//mlast4Cnt
	private Integer mlast4Cnt;
		
	@Column(name = "MLAST5_CNT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//mlast5Cnt
	private Integer mlast5Cnt;
		
	@Column(name = "MLAST6_CNT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//mlast6Cnt
	private Integer mlast6Cnt;
		
	@Column(name = "MLAST6PLUS_CNT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//mlast6plusCnt
	private Integer mlast6plusCnt;
	
	
	@Column(name = "USER_CNT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//mlast6plusCnt
	private Integer userCnt;
	public Integer getUserCnt() {
		return userCnt;
	}

	public void setUserCnt(Integer userCnt) {
		this.userCnt = userCnt;
	}

	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getAreaId() {
		return this.areaId;
	}
	
	public void setAreaId(java.lang.String value) {
		this.areaId = value;
	}
	
	public java.lang.String getAreaName() {
		return this.areaName;
	}
	
	public void setAreaName(java.lang.String value) {
		this.areaName = value;
	}
	
	public java.lang.String getAreaCode() {
		return this.areaCode;
	}
	
	public void setAreaCode(java.lang.String value) {
		this.areaCode = value;
	}
	
	public java.lang.String getParentCode() {
		return this.parentCode;
	}
	
	public void setParentCode(java.lang.String value) {
		this.parentCode = value;
	}
	
	public Integer getSumtype() {
		return this.sumtype;
	}
	
	public void setSumtype(Integer value) {
		this.sumtype = value;
	}
	
	public Integer getMlast1Cnt() {
		return this.mlast1Cnt;
	}
	
	public void setMlast1Cnt(Integer value) {
		this.mlast1Cnt = value;
	}
	
	public Integer getMlast2Cnt() {
		return this.mlast2Cnt;
	}
	
	public void setMlast2Cnt(Integer value) {
		this.mlast2Cnt = value;
	}
	
	public Integer getMlast3Cnt() {
		return this.mlast3Cnt;
	}
	
	public void setMlast3Cnt(Integer value) {
		this.mlast3Cnt = value;
	}
	
	public Integer getMlast4Cnt() {
		return this.mlast4Cnt;
	}
	
	public void setMlast4Cnt(Integer value) {
		this.mlast4Cnt = value;
	}
	
	public Integer getMlast5Cnt() {
		return this.mlast5Cnt;
	}
	
	public void setMlast5Cnt(Integer value) {
		this.mlast5Cnt = value;
	}
	
	public Integer getMlast6Cnt() {
		return this.mlast6Cnt;
	}
	
	public void setMlast6Cnt(Integer value) {
		this.mlast6Cnt = value;
	}
	
	public Integer getMlast6plusCnt() {
		return this.mlast6plusCnt;
	}
	
	public void setMlast6plusCnt(Integer value) {
		this.mlast6plusCnt = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("AreaId",getAreaId())
			.append("AreaName",getAreaName())
			.append("AreaCode",getAreaCode())
			.append("ParentCode",getParentCode())
			.append("Sumtype",getSumtype())
			.append("Mlast1Cnt",getMlast1Cnt())
			.append("Mlast2Cnt",getMlast2Cnt())
			.append("Mlast3Cnt",getMlast3Cnt())
			.append("Mlast4Cnt",getMlast4Cnt())
			.append("Mlast5Cnt",getMlast5Cnt())
			.append("Mlast6Cnt",getMlast6Cnt())
			.append("Mlast6plusCnt",getMlast6plusCnt())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof StatLoginData == false) return false;
		if(this == obj) return true;
		StatLoginData other = (StatLoginData)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

