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

//表更新信息表
@Entity
@Table(name = "BASE_TABLE_UPDATE_LOG")
public class BaseTableUpdateLog implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 50)
	//ID
	private java.lang.String id;
		
	@Column(name = "TABLE_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//表名
	private java.lang.String tableName;
		
	@Column(name = "LAST_UPDATE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	//表最后更新时间
	private java.util.Date lastUpdateTime;
		
	@Column(name = "LAST_ADD_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 11)
	//表最后新增时间
	private java.util.Date lastAddTime;
		
	@Column(name = "DATA_CNT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//表数据量
	private Integer dataCnt;



	

	
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
	
	public java.util.Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}
	
	public void setLastUpdateTime(java.util.Date value) {
		this.lastUpdateTime = value;
	}
	
	public java.util.Date getLastAddTime() {
		return this.lastAddTime;
	}
	
	public void setLastAddTime(java.util.Date value) {
		this.lastAddTime = value;
	}
	
	public Integer getDataCnt() {
		return this.dataCnt;
	}
	
	public void setDataCnt(Integer value) {
		this.dataCnt = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("TableName",getTableName())
			.append("LastUpdateTime",getLastUpdateTime())
			.append("LastAddTime",getLastAddTime())
			.append("DataCnt",getDataCnt())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseTableUpdateLog == false) return false;
		if(this == obj) return true;
		BaseTableUpdateLog other = (BaseTableUpdateLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

