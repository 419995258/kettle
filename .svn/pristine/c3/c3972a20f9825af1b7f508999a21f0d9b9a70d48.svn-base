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

//BaseUrl
@Entity
@Table(name = "BASE_URL")
public class BaseUrl implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "URL_ID", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//urlId
	private java.lang.String id;
		
	@Column(name = "URL_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//链接名称
	private java.lang.String urlName;
		
	@Column(name = "URL", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	//链接
	private java.lang.String url;
		
	@Column(name = "URL_DESC", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	//链接说明
	private java.lang.String urlDesc;
		
	@Column(name = "CLICK_COUNT", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//点击次数
	private Integer clickCount;
		
	@Column(name = "CRE_USER", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//creUser
	private java.lang.String creUser;
		
	@Column(name = "CRE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//creTime
	private java.util.Date creTime;
		
	@Column(name = "MOD_USER", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//modUser
	private java.lang.String modUser;
		
	@Column(name = "MOD_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//modTime
	private java.util.Date modTime;
		
	@Column(name = "MODULE_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 40)
	//moduleId
	private java.lang.String moduleId;

	@Column(name = "ISADDLOG", unique = false, nullable = true, insertable = true, updatable = true, length = 2)
	//moduleId
	private java.lang.String isAddLog;//是否加入日志 0否1是
	
	public BaseUrl(){
	}

	
	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getUrlName() {
		return this.urlName;
	}
	
	public void setUrlName(java.lang.String value) {
		this.urlName = value;
	}
	
	public java.lang.String getUrl() {
		return this.url;
	}
	
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	
	public java.lang.String getUrlDesc() {
		return this.urlDesc;
	}
	
	public void setUrlDesc(java.lang.String value) {
		this.urlDesc = value;
	}
	
	public Integer getClickCount() {
		return this.clickCount;
	}
	
	public void setClickCount(Integer value) {
		this.clickCount = value;
	}
	
	public java.lang.String getCreUser() {
		return this.creUser;
	}
	
	public void setCreUser(java.lang.String value) {
		this.creUser = value;
	}
	
	public java.util.Date getCreTime() {
		return this.creTime;
	}
	
	public void setCreTime(java.util.Date value) {
		this.creTime = value;
	}
	
	public java.lang.String getModUser() {
		return this.modUser;
	}
	
	public void setModUser(java.lang.String value) {
		this.modUser = value;
	}
	
	public java.util.Date getModTime() {
		return this.modTime;
	}
	
	public void setModTime(java.util.Date value) {
		this.modTime = value;
	}
	
	public java.lang.String getModuleId() {
		return this.moduleId;
	}
	
	public void setModuleId(java.lang.String value) {
		this.moduleId = value;
	}
	

	public java.lang.String getIsAddLog() {
		return isAddLog;
	}


	public void setIsAddLog(java.lang.String isAddLog) {
		this.isAddLog = isAddLog;
	}


	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("UrlId",getId())
			.append("UrlName",getUrlName())
			.append("Url",getUrl())
			.append("UrlDesc",getUrlDesc())
			.append("ClickCount",getClickCount())
			.append("CreUser",getCreUser())
			.append("CreTime",getCreTime())
			.append("ModUser",getModUser())
			.append("ModTime",getModTime())
			.append("ModuleId",getModuleId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseUrl == false) return false;
		if(this == obj) return true;
		BaseUrl other = (BaseUrl)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

