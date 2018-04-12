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

//默认学分转换规则
@Entity
@Table(name = "BASE_CREDIT_TRANS_RULE")
public class BaseCreditTransRule implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "RID", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//规则ID
	private java.lang.String id;
		
	@Column(name = "PRO_LEVEL", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//项目级别：来自属性表组pro.level.
	private java.lang.String proLevel;
		
	@Column(name = "PRO_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//项目类型：来自属性表组pro.type
	private java.lang.String proType;
		
	@Column(name = "R_RESULT_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//结果转换类型：来自属性表
	private java.lang.String rresultType;
		
	@Column(name = "R_INPUT_UNIT", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//投入量单位
	private java.lang.String rinputUnit;
		
	@Column(name = "R_INPUT_AMOUNT", unique = false, nullable = true, insertable = true, updatable = true, length = 126)
	//投入数量
	private java.lang.Integer rinputAmount;
		
	@Column(name = "R_OUTPUT_UNIT", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//产出量单位
	private java.lang.String routputUnit;
		
	@Column(name = "R_OUTPUT_AMOUNT", unique = false, nullable = true, insertable = true, updatable = true, length = 126)
	//对应产出数量
	private java.lang.Integer routputAmount;
		
	@Column(name = "CRE_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//创建用户
	private java.lang.String creUserId;
		
	@Column(name = "CRE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//创建时间
	private java.util.Date creTime;
		
	@Column(name = "MOD_USER_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//修改用户
	private java.lang.String modUserId;
		
	@Column(name = "MOD_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//修改时间
	private java.util.Date modTime;
		
	@Column(name = "R_STATUS", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//最新状态:0:可用／1:暂停／2:作废
	private Integer rstatus;



	

	
	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
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
	
	public java.lang.String getRresultType() {
		return this.rresultType;
	}
	
	public void setRresultType(java.lang.String value) {
		this.rresultType = value;
	}
	
	public java.lang.String getRinputUnit() {
		return this.rinputUnit;
	}
	
	public void setRinputUnit(java.lang.String value) {
		this.rinputUnit = value;
	}
	
	public java.lang.Integer getRinputAmount() {
		return this.rinputAmount;
	}
	
	public void setRinputAmount(java.lang.Integer value) {
		this.rinputAmount = value;
	}
	
	public java.lang.String getRoutputUnit() {
		return this.routputUnit;
	}
	
	public void setRoutputUnit(java.lang.String value) {
		this.routputUnit = value;
	}
	
	public java.lang.Integer getRoutputAmount() {
		return this.routputAmount;
	}
	
	public void setRoutputAmount(java.lang.Integer value) {
		this.routputAmount = value;
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
	
	public Integer getRstatus() {
		return this.rstatus;
	}
	
	public void setRstatus(Integer value) {
		this.rstatus = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Rid",getId())
			.append("ProLevel",getProLevel())
			.append("ProType",getProType())
			.append("RresultType",getRresultType())
			.append("RinputUnit",getRinputUnit())
			.append("RinputAmount",getRinputAmount())
			.append("RoutputUnit",getRoutputUnit())
			.append("RoutputAmount",getRoutputAmount())
			.append("CreUserId",getCreUserId())
			.append("CreTime",getCreTime())
			.append("ModUserId",getModUserId())
			.append("ModTime",getModTime())
			.append("Rstatus",getRstatus())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseCreditTransRule == false) return false;
		if(this == obj) return true;
		BaseCreditTransRule other = (BaseCreditTransRule)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

