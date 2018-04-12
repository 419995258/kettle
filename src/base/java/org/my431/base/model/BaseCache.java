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

//CACHE命名表
@Entity
@Table(name = "BASE_CACHE")
public class BaseCache implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//id
	private java.lang.String id;
		
	@Column(name = "CACHE_NAME", unique = true, nullable = true, insertable = true, updatable = true, length = 200)
	//CACHE命名串，例如： Area.key
	private java.lang.String cacheName;
		
	@Column(name = "CACHE_PRE_KEY", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
	//KEY的前缀
	private java.lang.String cachePreKey;
		
	@Column(name = "CACHE_AFT_KEY", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
	//KEY的后缀
	private java.lang.String cacheAftKey;
		
	@Column(name = "CACHE_TABLE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//cacheTable
	private java.lang.String cacheTable;
		
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


	public BaseCache(){
	}

	public BaseCache(
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
	
	public java.lang.String getCacheName() {
		return this.cacheName;
	}
	
	public void setCacheName(java.lang.String value) {
		this.cacheName = value;
	}
	
	public java.lang.String getCachePreKey() {
		return this.cachePreKey;
	}
	
	public void setCachePreKey(java.lang.String value) {
		this.cachePreKey = value;
	}
	
	public java.lang.String getCacheAftKey() {
		return this.cacheAftKey;
	}
	
	public void setCacheAftKey(java.lang.String value) {
		this.cacheAftKey = value;
	}
	
	public java.lang.String getCacheTable() {
		return this.cacheTable;
	}
	
	public void setCacheTable(java.lang.String value) {
		this.cacheTable = value;
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
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("CacheName",getCacheName())
			.append("CachePreKey",getCachePreKey())
			.append("CacheAftKey",getCacheAftKey())
			.append("CacheTable",getCacheTable())
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
		if(obj instanceof BaseCache == false) return false;
		if(this == obj) return true;
		BaseCache other = (BaseCache)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

