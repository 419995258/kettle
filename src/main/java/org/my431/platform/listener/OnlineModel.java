package org.my431.platform.listener;

import org.my431.base.model.BaseUser;

public class OnlineModel implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8766000951392439261L;

	private String id;

	private String loginName;

	private String realName;

	private String loginTime;

	private String lastAccessTime;

	private String ipAddress;

	private String address;

    private String department;
    
    private String identify;
    
    private String clientIpAddress;
    
    private String clientIpAddress138;
    
    private BaseUser baseUser;
    
    private String roleName;
    
    private String fullAreaName;
    
    private Long loginCount;

	public BaseUser getBaseUser() {
		return baseUser;
	}

	public void setBaseUser(BaseUser baseUser) {
		this.baseUser = baseUser;
	}

	public String getClientIpAddress138() {
		return clientIpAddress138;
	}

	public void setClientIpAddress138(String clientIpAddress138) {
		this.clientIpAddress138 = clientIpAddress138;
	}

	public String getClientIpAddress() {
		return clientIpAddress;
	}

	public void setClientIpAddress(String clientIpAddress) {
		this.clientIpAddress = clientIpAddress;
	}

	/**
	 * OnlineModel
	 */
	public OnlineModel() {
	}

	public OnlineModel(String id, String loginName, String realName, String ip,
			String loginTime, String lastAcessTime, String address) {
		this.id = id;
		this.loginName = loginName;
		this.realName = realName;
		this.loginTime = loginTime;
		this.lastAccessTime = lastAcessTime;
		this.ipAddress = ip;
		this.address = address;
	}
	
	@Deprecated
	public OnlineModel(String id, String loginName, String realName, String ip,
			String loginTime, String lastAcessTime, String address ,String department , String identify,String clientIpAddress,String clientIpAddress138,BaseUser baseUser) {
		this.id = id;
		this.loginName = loginName;
		this.realName = realName;
		this.loginTime = loginTime;
		this.lastAccessTime = lastAcessTime;
		this.ipAddress = ip;
		this.address = address;
		this.department = department ;
		this.identify = identify ;
		this.clientIpAddress = clientIpAddress ;
		this.clientIpAddress138=clientIpAddress138;
		this.baseUser=baseUser;
	}
	
	public OnlineModel(String id, String loginName, String realName, String ip,
			String loginTime, String lastAcessTime, String address ,String department , String identify,String clientIpAddress,String clientIpAddress138,BaseUser baseUser,String roleName,String fullAreaName) {
		this.id = id;
		this.loginName = loginName;
		this.realName = realName;
		this.loginTime = loginTime;
		this.lastAccessTime = lastAcessTime;
		this.ipAddress = ip;
		this.address = address;
		this.department = department ;
		this.identify = identify ;
		this.clientIpAddress = clientIpAddress ;
		this.clientIpAddress138=clientIpAddress138;
		this.roleName = roleName;
		this.fullAreaName = fullAreaName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getAddress() {
		return address;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginName() {
		return loginName;
	}

	public String toString() {
		return loginName + " " + realName + " " + ipAddress + " " + loginTime
				+ " " + lastAccessTime;
	}

	public boolean equals(Object o) {
		if ((o instanceof OnlineModel) && ((OnlineModel) o).getId().equals(id)) {
			return true;
		}
		return false;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(String lastAcessTime) {
		this.lastAccessTime = lastAcessTime;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getIdentify() {
		return identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getFullAreaName() {
		return fullAreaName;
	}

	public void setFullAreaName(String fullAreaName) {
		this.fullAreaName = fullAreaName;
	}

	public Long getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Long loginCount) {
		this.loginCount = loginCount;
	}
}