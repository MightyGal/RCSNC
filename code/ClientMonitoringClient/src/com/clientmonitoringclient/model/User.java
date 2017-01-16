package com.clientmonitoringclient.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class User {
	
@Id	
private int userId;
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
@OneToOne(cascade = CascadeType.ALL)
private Leader leader;
public Leader getLeader() {
	return leader;
}
public void setLeader(Leader leader) {
	this.leader = leader;
}
private String name;
private String mobile;
private String email;



}