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

//BaseKpiGroup
@Entity
@Table(name = "BASE_KPI_GROUP")
public class BaseKpiGroup implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "KPI_GRP_ID", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//kpiGrpId
	private java.lang.String id;
		
	@Column(name = "TABLE_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//tableName 表名
	private java.lang.String tableName;
		
	@Column(name = "KPI_GROUP_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//kpiGroupName 表名称
	private java.lang.String kpiGroupName;
		
	@Column(name = "VALIDE_ITEM_CONDITION", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	//valideItemCondition 有效数据条件
	private java.lang.String valideItemCondition;
		
	@Column(name = "USE_FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//useFlag 是否查询出来该数据 ： 1 true
	private Integer useFlag;
		
	@Column(name = "POINT_YEAR_COL_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//pointYearColName
	private java.lang.String pointYearColName;
		
	@Column(name = "POINT_TERM_COL_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//pointTermColName
	private java.lang.String pointTermColName;



	

	
	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getTableName() {
		return this.tableName;
	}
	
	public void setTableName(java.lang.String value) {
		this.tableName = value;
	}
	
	public java.lang.String getKpiGroupName() {
		return this.kpiGroupName;
	}
	
	public void setKpiGroupName(java.lang.String value) {
		this.kpiGroupName = value;
	}
	
	public java.lang.String getValideItemCondition() {
		return this.valideItemCondition;
	}
	
	public void setValideItemCondition(java.lang.String value) {
		this.valideItemCondition = value;
	}
	
	public Integer getUseFlag() {
		return this.useFlag;
	}
	
	public void setUseFlag(Integer value) {
		this.useFlag = value;
	}
	
	public java.lang.String getPointYearColName() {
		return this.pointYearColName;
	}
	
	public void setPointYearColName(java.lang.String value) {
		this.pointYearColName = value;
	}
	
	public java.lang.String getPointTermColName() {
		return this.pointTermColName;
	}
	
	public void setPointTermColName(java.lang.String value) {
		this.pointTermColName = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("KpiGrpId",getId())
			.append("TableName",getTableName())
			.append("KpiGroupName",getKpiGroupName())
			.append("ValideItemCondition",getValideItemCondition())
			.append("UseFlag",getUseFlag())
			.append("PointYearColName",getPointYearColName())
			.append("PointTermColName",getPointTermColName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseKpiGroup == false) return false;
		if(this == obj) return true;
		BaseKpiGroup other = (BaseKpiGroup)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

