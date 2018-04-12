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

//SQL命名表
@Entity
@Table(name = "BASE_QUERY")
public class BaseQuery implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//id
	private java.lang.String id;
		
	@Column(name = "QUERY_NAME", unique = true, nullable = true, insertable = true, updatable = true, length = 200)
	//sql命名串，例如： training.news.query
	private java.lang.String queryName;
		
	@Column(name = "QUERY_TITLE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//sql命名名称
	private java.lang.String queryTitle;
		
	@Column(name = "DB_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//数据库类型 mysql oracle sqlserver
	private java.lang.String dbType;
		
	@Column(name = "QUERY_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//sql或者hql
	private java.lang.String queryType;
		
	@Column(name = "QUERY_STRING", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
	//sql语句，查询语句字符串
	private java.lang.String queryString;
		
	@Column(name = "IS_CACHE", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//0：不缓存 1：缓存
	private Integer isCache;
		
	@Column(name = "CACHE_GROUP", unique = false, nullable = true, insertable = true, updatable = true, length = 400)
	//cacheGroup
	private java.lang.String cacheGroup;
		
	@Column(name = "NOTE", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
	//描述
	private java.lang.String note;
		
	@Column(name = "CRE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//creTime
	private java.util.Date creTime;
		
	@Column(name = "CRE_USER", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//creUser
	private java.lang.String creUser;
		
	@Column(name = "MOD_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//modTime
	private java.util.Date modTime;
		
	@Column(name = "MOD_USER", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//modUser
	private java.lang.String modUser;
	
	@Column(name = "ACTIVE_TIME", unique = false, nullable = true, insertable = true, updatable = true)
	//modUser
	private java.lang.Integer activeTime;


	public BaseQuery(){
	}

	public BaseQuery(
		java.lang.String id
	){
		this.id = id;
	}

	

	
	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getQueryName() {
		return this.queryName;
	}
	
	public void setQueryName(java.lang.String value) {
		this.queryName = value;
	}
	
	public java.lang.String getQueryTitle() {
		return this.queryTitle;
	}
	
	public void setQueryTitle(java.lang.String value) {
		this.queryTitle = value;
	}
	
	public java.lang.String getDbType() {
		return this.dbType;
	}
	
	public void setDbType(java.lang.String value) {
		this.dbType = value;
	}
	
	public java.lang.String getQueryType() {
		return this.queryType;
	}
	
	public void setQueryType(java.lang.String value) {
		this.queryType = value;
	}
	
	public java.lang.String getQueryString() {
		return this.queryString;
	}
	
	public void setQueryString(java.lang.String value) {
		this.queryString = value;
	}
	
	public Integer getIsCache() {
		return this.isCache;
	}
	
	public void setIsCache(Integer value) {
		this.isCache = value;
	}
	
	public java.lang.String getCacheGroup() {
		return this.cacheGroup;
	}
	
	public void setCacheGroup(java.lang.String value) {
		this.cacheGroup = value;
	}
	
	public java.lang.String getNote() {
		return this.note;
	}
	
	public void setNote(java.lang.String value) {
		this.note = value;
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
	
	public java.util.Date getModTime() {
		return this.modTime;
	}
	
	public void setModTime(java.util.Date value) {
		this.modTime = value;
	}
	
	public java.lang.String getModUser() {
		return this.modUser;
	}
	
	public void setModUser(java.lang.String value) {
		this.modUser = value;
	}
	

	public java.lang.Integer getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(java.lang.Integer activeTime) {
		this.activeTime = activeTime;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("QueryName",getQueryName())
			.append("QueryTitle",getQueryTitle())
			.append("DbType",getDbType())
			.append("QueryType",getQueryType())
			.append("QueryString",getQueryString())
			.append("IsCache",getIsCache())
			.append("CacheGroup",getCacheGroup())
			.append("Note",getNote())
			.append("CreTime",getCreTime())
			.append("CreUser",getCreUser())
			.append("ModTime",getModTime())
			.append("ModUser",getModUser())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseQuery == false) return false;
		if(this == obj) return true;
		BaseQuery other = (BaseQuery)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

