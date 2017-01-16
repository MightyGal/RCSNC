package com.clientmonitoringserver.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Host {
	@Id@GeneratedValue
	private int hostId;
	@OneToOne(cascade=CascadeType.ALL)

	private User user;
	
	private String hostName;
	private String osinfo;
	private String hardWareInfo;
	private String systemInfo;
	public int getHostId() {
		return hostId;
	}
	public void setHostId(int hostId) {
		this.hostId = hostId;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getOsinfo() {
		return osinfo;
	}
	public void setOsinfo(String osinfo) {
		this.osinfo = osinfo;
	}
	public String getHardWareInfo() {
		return hardWareInfo;
	}
	public void setHardWareInfo(String hardWareInfo) {
		this.hardWareInfo = hardWareInfo;
	}
	public String getSystemInfo() {
		return systemInfo;
	}
	public void setSystemInfo(String systemInfo) {
		this.systemInfo = systemInfo;
	}

	
	

}

