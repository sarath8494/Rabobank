package com.rabobank.bean;

import java.util.List;

/**
 * @author Sarath
 *
 */
public class ErrorResponse {
	private int errorCode;
	private String message;
	private List<Record> recordsList;
	private List<IssuesBean> issuesList;
	
	public List<IssuesBean> getIssuesList() {
		return issuesList;
	}

	public void setIssuesList(List<IssuesBean> issuesList) {
		this.issuesList = issuesList;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Record> getRecordsList() {
		return recordsList;
	}

	public void setRecordsList(List<Record> recordsList) {
		this.recordsList = recordsList;
	}

}
