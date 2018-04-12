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

//元数据属性组
@Entity
@Table(name = "BASE_PROPERTIES_GROUP")
public class BasePropertiesGroup implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "GID", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//系统编码
	private java.lang.String id;
		
	@Column(name = "GROUP_CODE", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//属性组编码（如：001，001001）
	private java.lang.String groupCode;
		
	@Column(name = "PARENT_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 40)
	//父节点代码(根节点：-1)
	private java.lang.String parentCode;
		
	@Column(name = "GROUP_KEY", unique = true, nullable = false, insertable = true, updatable = true, length = 100)
	//属性组Key（如：teach.subject）
	private java.lang.String groupKey;
		
	@Column(name = "GROUP_NAME", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	//属性组名称
	private java.lang.String groupName;
		
	@Column(name = "NODE_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	//节点类型（1：非叶子节点；0：叶子节点）
	private java.lang.String nodeType;
		
	@Column(name = "ITEM_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//类型：ENUM('SYS', 'USR')
	private java.lang.String itemType;
		
	@Column(name = "SEQ_NO", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//排序
	private Integer seqNo;
		
	@Column(name = "CRE_USER", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//creUser
	private java.lang.String creUser;
		
	@Column(name = "MOD_USER", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//modUser
	private java.lang.String modUser;
		
	@Column(name = "CRE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//creTime
	private java.util.Date creTime;
		
	@Column(name = "MOD_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//modTime
	private java.util.Date modTime;

	
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
	

	public BasePropertiesGroup(){
	}

	
	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getGroupCode() {
		return this.groupCode;
	}
	
	public void setGroupCode(java.lang.String value) {
		this.groupCode = value;
	}
	
	public java.lang.String getParentCode() {
		return this.parentCode;
	}
	
	public void setParentCode(java.lang.String value) {
		this.parentCode = value;
	}
	
	public java.lang.String getGroupKey() {
		return this.groupKey;
	}
	
	public void setGroupKey(java.lang.String value) {
		this.groupKey = value;
	}
	
	public java.lang.String getGroupName() {
		return this.groupName;
	}
	
	public void setGroupName(java.lang.String value) {
		this.groupName = value;
	}
	
	public java.lang.String getNodeType() {
		return this.nodeType;
	}
	
	public void setNodeType(java.lang.String value) {
		this.nodeType = value;
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
	
	public java.lang.String getCreUser() {
		return this.creUser;
	}
	
	public void setCreUser(java.lang.String value) {
		this.creUser = value;
	}
	
	public java.lang.String getModUser() {
		return this.modUser;
	}
	
	public void setModUser(java.lang.String value) {
		this.modUser = value;
	}
	
	public java.util.Date getCreTime() {
		return this.creTime;
	}
	
	public void setCreTime(java.util.Date value) {
		this.creTime = value;
	}
	
	public java.util.Date getModTime() {
		return this.modTime;
	}
	
	public void setModTime(java.util.Date value) {
		this.modTime = value;
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
			.append("Gid",getId())
			.append("GroupCode",getGroupCode())
			.append("ParentCode",getParentCode())
			.append("GroupKey",getGroupKey())
			.append("GroupName",getGroupName())
			.append("NodeType",getNodeType())
			.append("ItemType",getItemType())
			.append("SeqNo",getSeqNo())
			.append("CreUser",getCreUser())
			.append("ModUser",getModUser())
			.append("CreTime",getCreTime())
			.append("ModTime",getModTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BasePropertiesGroup == false) return false;
		if(this == obj) return true;
		BasePropertiesGroup other = (BasePropertiesGroup)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

