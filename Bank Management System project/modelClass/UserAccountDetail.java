package com.bank.management.system.modelClass;

public class UserAccountDetail 
{
	private int userId;
	private String userName;
	private int userAccountNo;
	private  double userAccountBalance;
	private int bankId;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserAccountNo() {
		return userAccountNo;
	}
	public void setUserAccountNo(int userAccountNo) {
		this.userAccountNo = userAccountNo;
	}
	public double getUserAccountBalance() {
		return userAccountBalance;
	}
	public void setUserAccountBalance(double userAccountBalance) {
		this.userAccountBalance = userAccountBalance;
	}
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	
	

}
