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

//行政区划表
@Entity
@Table(name = "BASE_AREA")
public class BaseArea implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "AREA_ID", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//areaId
	private java.lang.String id;
		
	@Column(name = "AREA_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 200)
	//areaCode
	private java.lang.String areaCode;
		
	@Column(name = "PARENT_CODE", unique = false, nullable = false, insertable = true, updatable = true, length = 200)
	//parentCode
	private java.lang.String parentCode;
		
	@Column(name = "NODE_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	//nodeType 0叶子节点 1非叶子节点
	private java.lang.String nodeType;
		
	@Column(name = "AREA_NAME", unique = false, nullable = false, insertable = true, updatable = true, length = 200)
	//areaName
	private java.lang.String areaName;
		
	@Column(name = "AREA_DESC", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//areaDesc
	private java.lang.String areaDesc;
		
	@Column(name = "NODE_LEVEL", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//nodeLevel
	private Integer nodeLevel;
		
	@Column(name = "NATIONAL_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//国家行政区划编码
	private java.lang.String nationalCode;
		
	@Column(name = "SEQ_NO", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//seqNo
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
		
	@Column(name = "DELETE_FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//已删除：1 未删除：0
	private Integer deleteFlag;
		
	@Column(name = "IS_INI", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//IS_INI 是否初始化，1为初始化，0为未初始化 默认：0
	private Integer isIni;
		
	@Column(name = "INI_USER", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//iniUser
	private java.lang.String iniUser;
		
	@Column(name = "INI_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//iniTime
	private java.util.Date iniTime;
	
	@Column(name = "INFO_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//iniUser
	private java.lang.String infoId;
	
	@Column(name = "IS_POOR", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//iniUser
	private java.lang.Integer isPoor;
	
	@Column(name = "EXTEND_AREA_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//iniUser
	private java.lang.String extendAreaCode;
	
	@Column(name = "LAST_UPDATE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//iniTime
	private java.util.Date lastUpdateTime;
	
	@Column(name = "MAP_AREA_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//iniUser
	private java.lang.String mapAreaCode;
	
	@Column(name = "MAP_PARENT_CODE", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//iniUser
	private java.lang.String mapParentCode;
	
	
	@Column(name = "IS_MAP_FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//iniUser
	private java.lang.Integer isMapFlag;
	
	@Column(name = "JYGLBMDM", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	//教育管理部门代码
	private java.lang.String jyglbmdm;

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(java.lang.String areaCode) {
		this.areaCode = areaCode;
	}

	public java.lang.String getParentCode() {
		return parentCode;
	}

	public void setParentCode(java.lang.String parentCode) {
		this.parentCode = parentCode;
	}

	public java.lang.String getNodeType() {
		return nodeType;
	}

	public void setNodeType(java.lang.String nodeType) {
		this.nodeType = nodeType;
	}

	public java.lang.String getAreaName() {
		return areaName;
	}

	public void setAreaName(java.lang.String areaName) {
		this.areaName = areaName;
	}

	public java.lang.String getAreaDesc() {
		return areaDesc;
	}

	public void setAreaDesc(java.lang.String areaDesc) {
		this.areaDesc = areaDesc;
	}

	public Integer getNodeLevel() {
		return nodeLevel;
	}

	public void setNodeLevel(Integer nodeLevel) {
		this.nodeLevel = nodeLevel;
	}

	public java.lang.String getNationalCode() {
		return nationalCode;
	}

	public void setNationalCode(java.lang.String nationalCode) {
		this.nationalCode = nationalCode;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public java.lang.String getCreUser() {
		return creUser;
	}

	public void setCreUser(java.lang.String creUser) {
		this.creUser = creUser;
	}

	public java.lang.String getModUser() {
		return modUser;
	}

	public void setModUser(java.lang.String modUser) {
		this.modUser = modUser;
	}

	public java.util.Date getCreTime() {
		return creTime;
	}

	public void setCreTime(java.util.Date creTime) {
		this.creTime = creTime;
	}

	public java.util.Date getModTime() {
		return modTime;
	}

	public void setModTime(java.util.Date modTime) {
		this.modTime = modTime;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Integer getIsIni() {
		return isIni;
	}

	public void setIsIni(Integer isIni) {
		this.isIni = isIni;
	}

	public java.lang.String getIniUser() {
		return iniUser;
	}

	public void setIniUser(java.lang.String iniUser) {
		this.iniUser = iniUser;
	}

	public java.util.Date getIniTime() {
		return iniTime;
	}

	public void setIniTime(java.util.Date iniTime) {
		this.iniTime = iniTime;
	}

	public java.lang.String getInfoId() {
		return infoId;
	}

	public void setInfoId(java.lang.String infoId) {
		this.infoId = infoId;
	}

	public java.lang.Integer getIsPoor() {
		return isPoor;
	}

	public void setIsPoor(java.lang.Integer isPoor) {
		this.isPoor = isPoor;
	}

	public java.lang.String getExtendAreaCode() {
		return extendAreaCode;
	}

	public void setExtendAreaCode(java.lang.String extendAreaCode) {
		this.extendAreaCode = extendAreaCode;
	}

	public java.util.Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(java.util.Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public java.lang.String getMapAreaCode() {
		return mapAreaCode;
	}

	public void setMapAreaCode(java.lang.String mapAreaCode) {
		this.mapAreaCode = mapAreaCode;
	}

	public java.lang.String getMapParentCode() {
		return mapParentCode;
	}

	public void setMapParentCode(java.lang.String mapParentCode) {
		this.mapParentCode = mapParentCode;
	}

	public java.lang.Integer getIsMapFlag() {
		return isMapFlag;
	}

	public void setIsMapFlag(java.lang.Integer isMapFlag) {
		this.isMapFlag = isMapFlag;
	}

	public java.lang.String getJyglbmdm() {
		return jyglbmdm;
	}

	public void setJyglbmdm(java.lang.String jyglbmdm) {
		this.jyglbmdm = jyglbmdm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((areaCode == null) ? 0 : areaCode.hashCode());
		result = prime * result
				+ ((areaDesc == null) ? 0 : areaDesc.hashCode());
		result = prime * result
				+ ((areaName == null) ? 0 : areaName.hashCode());
		result = prime * result + ((creTime == null) ? 0 : creTime.hashCode());
		result = prime * result + ((creUser == null) ? 0 : creUser.hashCode());
		result = prime * result
				+ ((deleteFlag == null) ? 0 : deleteFlag.hashCode());
		result = prime * result
				+ ((extendAreaCode == null) ? 0 : extendAreaCode.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((infoId == null) ? 0 : infoId.hashCode());
		result = prime * result + ((iniTime == null) ? 0 : iniTime.hashCode());
		result = prime * result + ((iniUser == null) ? 0 : iniUser.hashCode());
		result = prime * result + ((isIni == null) ? 0 : isIni.hashCode());
		result = prime * result
				+ ((isMapFlag == null) ? 0 : isMapFlag.hashCode());
		result = prime * result + ((isPoor == null) ? 0 : isPoor.hashCode());
		result = prime * result
				+ ((jyglbmdm == null) ? 0 : jyglbmdm.hashCode());
		result = prime * result
				+ ((lastUpdateTime == null) ? 0 : lastUpdateTime.hashCode());
		result = prime * result
				+ ((mapAreaCode == null) ? 0 : mapAreaCode.hashCode());
		result = prime * result
				+ ((mapParentCode == null) ? 0 : mapParentCode.hashCode());
		result = prime * result + ((modTime == null) ? 0 : modTime.hashCode());
		result = prime * result + ((modUser == null) ? 0 : modUser.hashCode());
		result = prime * result
				+ ((nationalCode == null) ? 0 : nationalCode.hashCode());
		result = prime * result
				+ ((nodeLevel == null) ? 0 : nodeLevel.hashCode());
		result = prime * result
				+ ((nodeType == null) ? 0 : nodeType.hashCode());
		result = prime * result
				+ ((parentCode == null) ? 0 : parentCode.hashCode());
		result = prime * result + ((seqNo == null) ? 0 : seqNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseArea other = (BaseArea) obj;
		if (areaCode == null) {
			if (other.areaCode != null)
				return false;
		} else if (!areaCode.equals(other.areaCode))
			return false;
		if (areaDesc == null) {
			if (other.areaDesc != null)
				return false;
		} else if (!areaDesc.equals(other.areaDesc))
			return false;
		if (areaName == null) {
			if (other.areaName != null)
				return false;
		} else if (!areaName.equals(other.areaName))
			return false;
		if (creTime == null) {
			if (other.creTime != null)
				return false;
		} else if (!creTime.equals(other.creTime))
			return false;
		if (creUser == null) {
			if (other.creUser != null)
				return false;
		} else if (!creUser.equals(other.creUser))
			return false;
		if (deleteFlag == null) {
			if (other.deleteFlag != null)
				return false;
		} else if (!deleteFlag.equals(other.deleteFlag))
			return false;
		if (extendAreaCode == null) {
			if (other.extendAreaCode != null)
				return false;
		} else if (!extendAreaCode.equals(other.extendAreaCode))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (infoId == null) {
			if (other.infoId != null)
				return false;
		} else if (!infoId.equals(other.infoId))
			return false;
		if (iniTime == null) {
			if (other.iniTime != null)
				return false;
		} else if (!iniTime.equals(other.iniTime))
			return false;
		if (iniUser == null) {
			if (other.iniUser != null)
				return false;
		} else if (!iniUser.equals(other.iniUser))
			return false;
		if (isIni == null) {
			if (other.isIni != null)
				return false;
		} else if (!isIni.equals(other.isIni))
			return false;
		if (isMapFlag == null) {
			if (other.isMapFlag != null)
				return false;
		} else if (!isMapFlag.equals(other.isMapFlag))
			return false;
		if (isPoor == null) {
			if (other.isPoor != null)
				return false;
		} else if (!isPoor.equals(other.isPoor))
			return false;
		if (jyglbmdm == null) {
			if (other.jyglbmdm != null)
				return false;
		} else if (!jyglbmdm.equals(other.jyglbmdm))
			return false;
		if (lastUpdateTime == null) {
			if (other.lastUpdateTime != null)
				return false;
		} else if (!lastUpdateTime.equals(other.lastUpdateTime))
			return false;
		if (mapAreaCode == null) {
			if (other.mapAreaCode != null)
				return false;
		} else if (!mapAreaCode.equals(other.mapAreaCode))
			return false;
		if (mapParentCode == null) {
			if (other.mapParentCode != null)
				return false;
		} else if (!mapParentCode.equals(other.mapParentCode))
			return false;
		if (modTime == null) {
			if (other.modTime != null)
				return false;
		} else if (!modTime.equals(other.modTime))
			return false;
		if (modUser == null) {
			if (other.modUser != null)
				return false;
		} else if (!modUser.equals(other.modUser))
			return false;
		if (nationalCode == null) {
			if (other.nationalCode != null)
				return false;
		} else if (!nationalCode.equals(other.nationalCode))
			return false;
		if (nodeLevel == null) {
			if (other.nodeLevel != null)
				return false;
		} else if (!nodeLevel.equals(other.nodeLevel))
			return false;
		if (nodeType == null) {
			if (other.nodeType != null)
				return false;
		} else if (!nodeType.equals(other.nodeType))
			return false;
		if (parentCode == null) {
			if (other.parentCode != null)
				return false;
		} else if (!parentCode.equals(other.parentCode))
			return false;
		if (seqNo == null) {
			if (other.seqNo != null)
				return false;
		} else if (!seqNo.equals(other.seqNo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BaseArea [id=" + id + ", areaCode=" + areaCode
				+ ", parentCode=" + parentCode + ", nodeType=" + nodeType
				+ ", areaName=" + areaName + ", areaDesc=" + areaDesc
				+ ", nodeLevel=" + nodeLevel + ", nationalCode=" + nationalCode
				+ ", seqNo=" + seqNo + ", creUser=" + creUser + ", modUser="
				+ modUser + ", creTime=" + creTime + ", modTime=" + modTime
				+ ", deleteFlag=" + deleteFlag + ", isIni=" + isIni
				+ ", iniUser=" + iniUser + ", iniTime=" + iniTime + ", infoId="
				+ infoId + ", isPoor=" + isPoor + ", extendAreaCode="
				+ extendAreaCode + ", lastUpdateTime=" + lastUpdateTime
				+ ", mapAreaCode=" + mapAreaCode + ", mapParentCode="
				+ mapParentCode + ", isMapFlag=" + isMapFlag + ", jyglbmdm="
				+ jyglbmdm + "]";
	}
	
}

