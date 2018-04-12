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

//BaseBusinessStep
@Entity
@Table(name = "BASE_BUSINESS_STEP")
public class BaseBusinessStep implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	

	
	@Id 
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(name = "BSID", unique = true, nullable = false, insertable = true, updatable = true, length = 40)
	//bsid
	private java.lang.String id;
		
	@Column(name = "BUSINESS_STEP_KEY", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	//业务步骤关键字 来自属性表
	private java.lang.String businessStepKey;
		
	@Column(name = "BUSINESS_FLOW_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//业务流程ID 如学分项目ID/资格注册ID／学分登记ID等
	private java.lang.String businessFlowId;
		
	@Column(name = "BUSINESS_STEP_ROUND", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//流程回合次数 如果被迫从新来，此字段加1
	private Integer businessStepRound;
		
	@Column(name = "STEP_ONOFF", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//业务步骤激活状态
	private Integer stepOnoff;
		
	@Column(name = "STEP_DEAL_REULST", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//业务步骤处理结果
	private Integer stepDealReulst;
		
	@Column(name = "STEP_DEAL_COMMENT_JASON", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	//业务步骤处理备注
	private java.lang.String stepDealCommentJason;
		
	@Column(name = "STEP_DEAL_USER_JASON", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//业务步骤处理用户
	private java.lang.String stepDealUserJason;
		
	@Column(name = "STEP_DEAL_TIME_JASON", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//业务步骤处理时间
	private java.util.Date stepDealTimeJason;
		
	@Column(name = "DELETE_FLAG", unique = false, nullable = true, insertable = true, updatable = true, length = 22)
	//删除标记
	private Integer deleteFlag;
		
	@Column(name = "DELETE_TIME", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	//删除时间
	private java.util.Date deleteTime;
		
	@Column(name = "DELETE_USER", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//删除用户
	private java.lang.String deleteUser;


	@Column(name = "OBJECT_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 40)
	//备用字段
	private java.lang.String objectId;
	

	
	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getBusinessStepKey() {
		return this.businessStepKey;
	}
	
	public void setBusinessStepKey(java.lang.String value) {
		this.businessStepKey = value;
	}
	
	public java.lang.String getBusinessFlowId() {
		return this.businessFlowId;
	}
	
	public void setBusinessFlowId(java.lang.String value) {
		this.businessFlowId = value;
	}
	
	public Integer getBusinessStepRound() {
		return this.businessStepRound;
	}
	
	public void setBusinessStepRound(Integer value) {
		this.businessStepRound = value;
	}
	
	public Integer getStepOnoff() {
		return this.stepOnoff;
	}
	
	public void setStepOnoff(Integer value) {
		this.stepOnoff = value;
	}
	
	public Integer getStepDealReulst() {
		return this.stepDealReulst;
	}
	
	public void setStepDealReulst(Integer value) {
		this.stepDealReulst = value;
	}
	
	public java.lang.String getStepDealCommentJason() {
		return this.stepDealCommentJason;
	}
	
	public void setStepDealCommentJason(java.lang.String value) {
		this.stepDealCommentJason = value;
	}
	
	public java.lang.String getStepDealUserJason() {
		return this.stepDealUserJason;
	}
	
	public void setStepDealUserJason(java.lang.String value) {
		this.stepDealUserJason = value;
	}
	
	public java.util.Date getStepDealTimeJason() {
		return this.stepDealTimeJason;
	}
	
	public void setStepDealTimeJason(java.util.Date value) {
		this.stepDealTimeJason = value;
	}
	
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}
	
	public void setDeleteFlag(Integer value) {
		this.deleteFlag = value;
	}
	
	public java.util.Date getDeleteTime() {
		return this.deleteTime;
	}
	
	public void setDeleteTime(java.util.Date value) {
		this.deleteTime = value;
	}
	
	public java.lang.String getDeleteUser() {
		return this.deleteUser;
	}
	
	public void setDeleteUser(java.lang.String value) {
		this.deleteUser = value;
	}
	

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Bsid",getId())
			.append("BusinessStepKey",getBusinessStepKey())
			.append("BusinessFlowId",getBusinessFlowId())
			.append("BusinessStepRound",getBusinessStepRound())
			.append("StepOnoff",getStepOnoff())
			.append("StepDealReulst",getStepDealReulst())
			.append("StepDealCommentJason",getStepDealCommentJason())
			.append("StepDealUserJason",getStepDealUserJason())
			.append("StepDealTimeJason",getStepDealTimeJason())
			.append("DeleteFlag",getDeleteFlag())
			.append("DeleteTime",getDeleteTime())
			.append("DeleteUser",getDeleteUser())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof BaseBusinessStep == false) return false;
		if(this == obj) return true;
		BaseBusinessStep other = (BaseBusinessStep)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	/**
	 * @return the objectId
	 */
	public java.lang.String getObjectId() {
		return objectId;
	}

	/**
	 * @param objectId the objectId to set
	 */
	public void setObjectId(java.lang.String objectId) {
		this.objectId = objectId;
	}
}

