package com.bank.management.system.concreteClass;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.management.system.Interface.CustomerDetailInterface;

public class CustomerDetail implements CustomerDetailInterface 
{

	@Override
	public void withdrawMoney(int userId, double userAmout, Connection conn) {
String selectuserAccountBalance	= "select userAccountBalance from UserAccountDetail where userId = ?";	
String withdrawbalance = "update UserAccountDetail set userAccountBalance = userAccountBalance - ? where userId = ?";
		
		try {
			PreparedStatement pst = conn.prepareStatement(selectuserAccountBalance);
			
			pst.setInt(1, userId);
			
//			double updated = pst.executeUpdate();
			ResultSet rs = pst.executeQuery();
			int accountBalance=0;
			while(rs.next())
			{
				accountBalance = rs.getInt(1);
				System.out.println( "accountBalance" + accountBalance);
			}
			if(accountBalance > userAmout) {
				pst = conn.prepareStatement(withdrawbalance);
				pst.setDouble(1, userAmout);
				pst.setInt(2, userId);
				pst.executeUpdate();
				System.out.println("User amount withdraw");
			}else {
				System.out.println("You don't have enough balance to withdrawl money");							
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void depositeMoney(int userId, double amount,Connection conn) {
String depositbalance = "update UserAccountDetail set userAccountBalance = userAccountBalance + ? where userId = ?";
		
		try {
			PreparedStatement pst = conn.prepareStatement(depositbalance);
			pst.setDouble(1, amount);
			pst.setInt(2, userId);
			
			double updated = pst.executeUpdate();
			
			if(updated > 0) {
				System.out.println("User amount deposited");
			}else {
				System.out.println("User amount not deposited");
				return;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	


		
	

	@Override
	public void transactionStatement(int userId, Connection conn) {
		String selectTransactionStatement="select * from UserAccountDetail where userId=?";
		try {
			PreparedStatement pst=conn.prepareStatement(selectTransactionStatement);
			pst.setInt(1, userId);
			ResultSet rs=pst.executeQuery();
			
			String filepath ="D:\\deatabase\\file_user.text";
			FileWriter fw = new FileWriter(filepath);
			
			while(rs.next())
			{
				 userId=rs.getInt(1);
				 String userName=rs.getString(2);
				 int acNo=rs.getInt(3);
				 double bal=rs.getDouble(4);
				 int bid=rs.getInt(5);
System.out.println("UserId : "+userId+" \n"+"UserName : "+userName+" \n"+"AcoountNo : "+acNo+" \n"+"Balance : "+bal+"\n "+"BankId : "+bid);

fw.write("UserId : "+userId+" \n"+"UserName : "+userName+" \n"+"AcoountNo : "+acNo+" \n"+"Balance : "+bal+"\n "+"BankId : "+bid);
fw.write(System.lineSeparator());
			}
			fw.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
  
}
