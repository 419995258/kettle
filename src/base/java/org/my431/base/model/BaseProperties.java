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

//元数据属性表
@Entity
@Table(name = "BASE_PROPERTIES")
public class BaseProperties implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "PID", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//系统编码
	private java.lang.String id;
		
	@Column(name = "PROPERTY_KEY", unique = true, nullable = false, insertable = true, updatable = true, length = 100)
	//属性代码
	private java.lang.String propertyKey;
		
	@Column(name = "PROPERTY_VALUE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//属性值
	private java.lang.String propertyValue;
		
	@Column(name = "PROPERTY_DESC", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//属性值描述
	private java.lang.String propertyDesc;
		
	@Column(name = "GROUP_KEY", unique = false, nullable = false, insertable = true, updatable = true, length = 40)
	//属性组代码[FK](如：teach.subject)
	private java.lang.String groupKey;
		
	@Column(name = "ITEM_TYPE", unique = false, nullable = false, insertable = true, updatable = true, length = 40)
	//类型：ENUM('SYS', 'USR')
	private java.lang.String itemType;
		
	@Column(name = "SEQ_NO", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//排序
	private Integer seqNo;
		
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
		
	@Column(name = "EXTRA1", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//extra1
	private java.lang.String extra1;
		
	@Column(name = "EXTRA2", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//extra2
	private java.lang.String extra2;
		
	@Column(name = "USE_FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//启用：0 停用：1
	private Integer useFlag;

	@Column(name = "EXTRA3", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//extra3
	private java.lang.String extra3;
	
	@Column(name = "EXTRA4", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//extra3
	private java.lang.String extra4;
	
	@Column(name = "EXTRA5", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//extra3
	private java.lang.String extra5;
	
	@Column(name = "YEY", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	//幼儿园(0否,1是)
	private java.lang.String yey;
	
	@Column(name = "ZXX", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	//中小学(0否,1是)
	private java.lang.String zxx;
	
	@Column(name = "TJ", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	//特教(0否,1是)
	private java.lang.String tj;
	
	@Column(name = "ZZ", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	//中职(0否,1是)
	private java.lang.String zz;
	
	@Column(name = "GZ", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	//高职(0否,1是)
	private java.lang.String gz;
	
	@Column(name = "GX", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	//高校(0否,1是)
	private java.lang.String gx;
	

	public BaseProperties(){
		
	}

	
	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getPropertyKey() {
		return this.propertyKey;
	}
	
	public void setPropertyKey(java.lang.String value) {
		this.propertyKey = value;
	}
	
	public java.lang.String getPropertyValue() {
		return this.propertyValue;
	}
	
	public void setPropertyValue(java.lang.String value) {
		this.propertyValue = value;
	}
	
	public java.lang.String getPropertyDesc() {
		return this.propertyDesc;
	}
	
	public void setPropertyDesc(java.lang.String value) {
		this.propertyDesc = value;
	}
	
	public java.lang.String getGroupKey() {
		return this.groupKey;
	}
	
	public void setGroupKey(java.lang.String value) {
		this.groupKey = value;
	}
	
	public java.lang.String getItemType() {
		return this.itemType;
	}
	
	public void setItemType(java.lang.String value) {
		this.itemType = value;
	}
	
	public Integer getSeqNo() {
		return this.seqNo;
	}
	
	public void setSeqNo(Integer value) {
		this.seqNo = value;
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
	
	public java.lang.String getExtra1() {
		return this.extra1;
	}
	
	public void setExtra1(java.lang.String value) {
		this.extra1 = value;
	}
	
	public java.lang.String getExtra2() {
		return this.extra2;
	}
	
	public void setExtra2(java.lang.String value) {
		this.extra2 = value;
	}
	
	public Integer getUseFlag() {
		return this.useFlag;
	}
	
	public void setUseFlag(Integer value) {
		this.useFlag = value;
	}
	

	public java.lang.String getExtra3() {
		return extra3;
	}


	public void setExtra3(java.lang.String extra3) {
		this.extra3 = extra3;
	}


	public java.lang.String getExtra4() {
		return extra4;
	}


	public void setExtra4(java.lang.String extra4) {
		this.extra4 = extra4;
	}


	public java.lang.String getExtra5() {
		return extra5;
	}


	public void setExtra5(java.lang.String extra5) {
		this.extra5 = extra5;
	}

	public java.lang.String getYey() {
		return yey;
	}


	public void setYey(java.lang.String yey) {
		this.yey = yey;
	}


	public java.lang.String getZxx() {
		return zxx;
	}


	public void setZxx(java.lang.String zxx) {
		this.zxx = zxx;
	}


	public java.lang.String getTj() {
		return tj;
	}


	public void setTj(java.lang.String tj) {
		this.tj = tj;
	}


	public java.lang.String getZz() {
		return zz;
	}


	public void setZz(java.lang.String zz) {
		this.zz = zz;
	}


	public java.lang.String getGz() {
		return gz;
	}


	public void setGz(java.lang.String gz) {
		this.gz = gz;
	}


	public java.lang.String getGx() {
		return gx;
	}


	public void setGx(java.lang.String gx) {
		this.gx = gx;
	}


	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Pid",getId())
			.append("PropertyKey",getPropertyKey())
			.append("PropertyValue",getPropertyValue())
			.append("PropertyDesc",getPropertyDesc())
			.append("GroupKey",getGroupKey())
			.append("ItemType",getItemType())
			.append("SeqNo",getSeqNo())
			.append("CreTime",getCreTime())
			.append("CreUser",getCreUser())
			.append("ModTime",getModTime())
			.append("ModUser",getModUser())
			.append("Extra1",getExtra1())
			.append("Extra2",getExtra2())
			.append("UseFlag",getUseFlag())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseProperties == false) return false;
		if(this == obj) return true;
		BaseProperties other = (BaseProperties)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

