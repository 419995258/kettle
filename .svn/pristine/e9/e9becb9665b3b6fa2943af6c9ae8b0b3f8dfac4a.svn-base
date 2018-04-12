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

//BaseKpiItem
@Entity
@Table(name = "BASE_KPI_ITEM")
public class BaseKpiItem implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "KPI_ID", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//kpiId
	private java.lang.String id;
		
	@Column(name = "TABLE_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//tableName 表名
	private java.lang.String tableName;
		
	@Column(name = "COLUMN_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	//columnName 字段名
	private java.lang.String columnName;
		
	@Column(name = "USE_FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//useFlag 是否查询出来该数据 ： 1 true
	private Integer useFlag;
		
	@Column(name = "KPI_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 500)
	//kpiName 字段名称
	private java.lang.String kpiName;
		
	@Column(name = "KPI_SF_ENUM", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//kpiSfEnum 是否枚举，用来属性表查询数据  1 ： true
	private java.lang.String kpiSfEnum;
		
	@Column(name = "DATA_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//dataType 数据类型
	private java.lang.String dataType;
		
	@Column(name = "DATA_LENGTH", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//dataLength 字段长度
	private Integer dataLength;
		
	@Column(name = "COLUMN_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//columnId id，第几个
	private Integer columnId;
		
	@Column(name = "COLUMN_COMMENTS", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//columnComments 备注
	private java.lang.String columnComments;
		
	@Column(name = "R_COLUMN_NAME_1", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//rcolumnName1
	private java.lang.String rcolumnName1;
		
	@Column(name = "R_COLUMN_TYPE_1", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//rcolumnType1
	private java.lang.String rcolumnType1;
		
	@Column(name = "R_COLUMN_NAME_2", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//rcolumnName2
	private java.lang.String rcolumnName2;
		
	@Column(name = "R_COLUMN_TYPE_2", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//rcolumnType2
	private java.lang.String rcolumnType2;
		
	@Column(name = "R_COLUMN_NAME_3", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//rcolumnName3
	private java.lang.String rcolumnName3;
		
	@Column(name = "R_COLUMN_TYPE_3", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	//rcolumnType3
	private java.lang.String rcolumnType3;



	

	
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
	
	public java.lang.String getColumnName() {
		return this.columnName;
	}
	
	public void setColumnName(java.lang.String value) {
		this.columnName = value;
	}
	
	public Integer getUseFlag() {
		return this.useFlag;
	}
	
	public void setUseFlag(Integer value) {
		this.useFlag = value;
	}
	
	public java.lang.String getKpiName() {
		return this.kpiName;
	}
	
	public void setKpiName(java.lang.String value) {
		this.kpiName = value;
	}
	
	public java.lang.String getKpiSfEnum() {
		return this.kpiSfEnum;
	}
	
	public void setKpiSfEnum(java.lang.String value) {
		this.kpiSfEnum = value;
	}
	
	public java.lang.String getDataType() {
		return this.dataType;
	}
	
	public void setDataType(java.lang.String value) {
		this.dataType = value;
	}
	
	public Integer getDataLength() {
		return this.dataLength;
	}
	
	public void setDataLength(Integer value) {
		this.dataLength = value;
	}
	
	public Integer getColumnId() {
		return this.columnId;
	}
	
	public void setColumnId(Integer value) {
		this.columnId = value;
	}
	
	public java.lang.String getColumnComments() {
		return this.columnComments;
	}
	
	public void setColumnComments(java.lang.String value) {
		this.columnComments = value;
	}
	
	public java.lang.String getRcolumnName1() {
		return this.rcolumnName1;
	}
	
	public void setRcolumnName1(java.lang.String value) {
		this.rcolumnName1 = value;
	}
	
	public java.lang.String getRcolumnType1() {
		return this.rcolumnType1;
	}
	
	public void setRcolumnType1(java.lang.String value) {
		this.rcolumnType1 = value;
	}
	
	public java.lang.String getRcolumnName2() {
		return this.rcolumnName2;
	}
	
	public void setRcolumnName2(java.lang.String value) {
		this.rcolumnName2 = value;
	}
	
	public java.lang.String getRcolumnType2() {
		return this.rcolumnType2;
	}
	
	public void setRcolumnType2(java.lang.String value) {
		this.rcolumnType2 = value;
	}
	
	public java.lang.String getRcolumnName3() {
		return this.rcolumnName3;
	}
	
	public void setRcolumnName3(java.lang.String value) {
		this.rcolumnName3 = value;
	}
	
	public java.lang.String getRcolumnType3() {
		return this.rcolumnType3;
	}
	
	public void setRcolumnType3(java.lang.String value) {
		this.rcolumnType3 = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("KpiId",getId())
			.append("TableName",getTableName())
			.append("ColumnName",getColumnName())
			.append("UseFlag",getUseFlag())
			.append("KpiName",getKpiName())
			.append("KpiSfEnum",getKpiSfEnum())
			.append("DataType",getDataType())
			.append("DataLength",getDataLength())
			.append("ColumnId",getColumnId())
			.append("ColumnComments",getColumnComments())
			.append("RcolumnName1",getRcolumnName1())
			.append("RcolumnType1",getRcolumnType1())
			.append("RcolumnName2",getRcolumnName2())
			.append("RcolumnType2",getRcolumnType2())
			.append("RcolumnName3",getRcolumnName3())
			.append("RcolumnType3",getRcolumnType3())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseKpiItem == false) return false;
		if(this == obj) return true;
		BaseKpiItem other = (BaseKpiItem)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

