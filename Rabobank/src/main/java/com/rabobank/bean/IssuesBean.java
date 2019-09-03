package com.rabobank.bean;

import java.sql.Date;
import java.sql.Timestamp;

public class IssuesBean {
private String firstName;
private String surName;
private int issueCount;
private String dob;
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getSurName() {
	return surName;
}
public void setSurName(String surName) {
	this.surName = surName;
}
public int getIssueCount() {
	return issueCount;
}
public void setIssueCount(int issueCount) {
	this.issueCount = issueCount;
}
public String getDob() {
	return dob;
}
public void setDob(String dob) {
	this.dob = dob;
}
}
