package com.clientmonitoringclient.model;



public class Command {
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
