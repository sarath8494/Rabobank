package com.rabobank.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Sarath
 *
 */
public class Record {
	private int referenceNum;
	private String accountNum;
	private double startBalance;
	private double mutation;
	private String description;
	private double endBalance;

	@XmlAttribute(name = "reference")
	public int getReferenceNum() {
		return referenceNum;
	}

	public void setReferenceNum(int referenceNum) {
		this.referenceNum = referenceNum;
	}

	@XmlElement(name = "accountNumber")
	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	@XmlElement(name = "startBalance")
	public double getStartBalance() {
		return startBalance;
	}

	public void setStartBalance(double startBalance) {
		this.startBalance = startBalance;
	}

	@XmlElement(name = "mutation")
	public double getMutation() {
		return mutation;
	}

	public void setMutation(double mutation) {
		this.mutation = mutation;
	}

	@XmlElement(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement(name = "endBalance")
	public double getEndBalance() {
		return endBalance;
	}

	public void setEndBalance(double endBalance) {
		this.endBalance = endBalance;
	}

	@Override
	public String toString() {
		return "Record [referenceNum=" + referenceNum + ", accountNum=" + accountNum + ", startBalance=" + startBalance
				+ ", mutation=" + mutation + ", description=" + description + ", endBalance=" + endBalance + "]";
	}

	public Record(int referenceNum, String accountNum, double startBalance, double mutation, String description,
			double endBalance) {
		super();
		this.referenceNum = referenceNum;
		this.accountNum = accountNum;
		this.startBalance = startBalance;
		this.mutation = mutation;
		this.description = description;
		this.endBalance = endBalance;
	}

	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}

}
