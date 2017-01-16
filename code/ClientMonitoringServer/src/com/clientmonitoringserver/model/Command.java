package com.clientmonitoringserver.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Command {
	
@Id@GeneratedValue	
private int commandId;
public int getCommandId() {
	return commandId;
}
public void setCommandId(int commandId) {
	this.commandId = commandId;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
private String content;
private String mobile;


}
