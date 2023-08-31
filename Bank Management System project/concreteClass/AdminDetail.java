package com.bank.management.system.concreteClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.management.system.Interface.AdminDetailinterface;
import com.bank.management.system.utility.Utility;

public class AdminDetail implements AdminDetailinterface 
{

	@Override
	public void createAccount(String bankName, String location, String userName,
			double initialBalance, String role, Connection conn) {
	
		String insertBankDetails = "insert into BankDetail(bankName,location,ifscCode) values(?,?,generateifscCode())";
		String selectBankId = "select max(bankId) from BankDetail";
		int bankId;
		
		String insertUserDetails = "insert into UserAccountDetail(userName,userAccountNo,userAccountBalance,bankId) values(?,generateAccountNumber(),?,?)";
		String selectUserId = "select max(userId) from  UserAccountDetail";
		int userId=0;
		
		String insertLoginDetails = "insert into LogInDetail(customerName,userId,role) values(?,?,?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(insertBankDetails);
			pstmt.setString(1, bankName);
			pstmt.setString(2, location);
			
			int inserted = pstmt.executeUpdate();
			
			if(inserted > 0) {
				System.out.println("Bank delaits inserted");
			}else {
				System.out.println("Bank delaits not inserted");
				return;
			}
			
			
			ResultSet rs = pstmt.executeQuery(selectBankId);
			
			if(rs.next()) {
				bankId = rs.getInt(1);
			}else {
				System.out.println("Unable to find bank id");
				return;
			}
			
			pstmt = conn.prepareStatement(insertUserDetails);
			pstmt.setString(1, userName);
			pstmt.setDouble(2, initialBalance);
			pstmt.setInt(3, bankId);
			
			inserted = pstmt.executeUpdate();
			if(inserted > 0) {
				System.out.println("User delaits inserted");
			}else {
				System.out.println("User delaits not inserted");
				return;
			}
			
			rs = pstmt.executeQuery(selectUserId);
			
			if(rs.next()) {
				userId = rs.getInt(1);
			}else {
				System.out.println("Unable to find user id");
				return;
			}
			
			pstmt = conn.prepareStatement(insertLoginDetails);
			
			pstmt.setString(1, userName);
			pstmt.setInt(2, userId);
			pstmt.setString(3, role);
			
			inserted = pstmt.executeUpdate();
			if(inserted > 0) {
				System.out.println("User Login delaits inserted");
			}else {
				System.out.println("User login delaits not inserted");
				return;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	
	@Override
	public void deleteAccount(int userId, Connection conn) {
		String deleteFromLoginDetails = "delete from LogInDetail where userId = ?";
		
		String selectbankId = "select bankId from UserAccountDetail where userId = ?";
		int bankId;
		String deleteFromUserDetails = "delete from UserAccountDetail where userId = ?";

		String deleteFromBankDetails = "delete from BankDetail where bankId = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(deleteFromLoginDetails);
			pstmt.setInt(1, userId);
			
			int deleted = pstmt.executeUpdate();
			
			if(deleted > 0) {
				System.out.println("Login delaits deleted");
			}else {
				System.out.println("Login delaits not deleted");
				return;
			}
			
			pstmt = conn.prepareStatement(selectbankId);
			pstmt.setInt(1, userId);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bankId = rs.getInt(1);
			}else {
				System.out.println("Unable to find bank id");
				return;
			}
			
			
			pstmt = conn.prepareStatement(deleteFromUserDetails);
			pstmt.setInt(1, userId);
			
			deleted = pstmt.executeUpdate();
			
			if(deleted > 0) {
				System.out.println("User delaits deleted");
			}else {
				System.out.println("User delaits not deleted");
				return;
			}
			
			pstmt = conn.prepareStatement(deleteFromBankDetails);
			pstmt.setInt(1, bankId);
			
			deleted = pstmt.executeUpdate();
			
			if(deleted > 0) {
				System.out.println("Bank delaits deleted");
			}else {
				System.out.println("Bank delaits not deleted");
				return;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
	}

}
