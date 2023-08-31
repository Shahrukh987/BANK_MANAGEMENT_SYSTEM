	package com.bank.management.system.main;
	

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

	
	import com.bank.management.system.concreteClass.CustomerDetail;
	import com.bank.management.system.concreteClass.AdminDetail;
	import com.bank.management.system.utility.Utility;
	
	public class BankManagementSystem 
	{
	
		public static void main(String[] args) 
		{	
		Connection conn =Utility.getConnection();
			
		int userId, password;
		String role = null;
		int choice;
		
		String userName, location, bankName;
		
		double initialBalance;
		
		double userAmout;
        
		String selectRoleQuery = "select role from LoginDetail where userId = ? and customerPassword= ?";
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please login..");
		
		System.out.println("Enter User Id");
		userId = sc.nextInt();
		
		System.out.println("Enter Password");
		password = sc.nextInt();
        while(true) {
		try {
			PreparedStatement pstmt = conn.prepareStatement(selectRoleQuery);
			pstmt.setInt(1, userId);
			pstmt.setInt(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
				if(rs.next())
				{
					role = rs.getString(1);
				}
			} catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		

		if(role.equalsIgnoreCase("Admin"))
		{
			System.out.println("Welcome Admin");
			
			System.out.println("Would you like to\n Create account: 1\n Delete an account: 2 \n Logout 0");
			
			choice = sc.nextInt();
			
			switch(choice){
				case 1:
					
					System.out.println("Enter Bank Name");
					bankName = sc.next();
					
					System.out.println("Enter location");
					location = sc.next();
					
					
					System.out.println("Enter User Name");
					userName = sc.next();
					
					System.out.println("Enter Initial Balance");
					initialBalance = sc.nextDouble();
					
//					System.out.println("Enter Initial Role");
//					role = sc.next();
					
					System.out.println("Enter the role you want to give");
					 role = sc.next();
					if(role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("user"))
					{
						AdminDetail ad = new AdminDetail();
						
						ad.createAccount(bankName,location,userName,initialBalance,role,conn);
					}else
					{
						System.out.println("invalid role use only user and admin");
					}
				
									
					break;
					
                    case 2:
					
					System.out.println("Enter User Id");
					int userId1 = sc.nextInt();
					if(userId1==userId)
					{
						System.out.println("you are not authorized delete your own account");
						break;
					}
					
					AdminDetail add = new AdminDetail();
					
					add.deleteAccount(userId1, conn);
					
					
					break;
					
					  case 0:
							if(choice==0)
							{
								System.out.println("logout");
							}
							else
							{
								System.out.println("invalid key");
							}
							return;
						
						
						
						
					default:					
				}
			
			
			}else if(role.equalsIgnoreCase("User")){
				System.out.println("Welcome User");
				System.out.println("Would you like to\n Deposit: 1\n Widthdraw: 2 \n transactionStatement 3 \n Logout 0");
				choice = sc.nextInt();
				
				switch(choice){
					case 1:
						System.out.println("Enter user id");
    					 userId = sc.nextInt();
						
						System.out.println("Enter Amount to be depoited");
						userAmout = sc.nextDouble();
						
						CustomerDetail cd = new CustomerDetail();
						
						cd.depositeMoney(userId, userAmout, conn);
						
						break;
						
					case 2:
						System.out.println("Enter user id");
   					    userId = sc.nextInt();
						System.out.println("Enter Amount to be widhraw");
						userAmout = sc.nextInt();
						CustomerDetail cd1 = new CustomerDetail();
						cd1.withdrawMoney(userId, userAmout, conn);
						break;
						
					case 3:
						System.out.println("transation statement");
						System.out.println("Enter userid");
						userId = sc.nextInt();
						CustomerDetail ts = new CustomerDetail();
						ts.transactionStatement(userId, conn);
						
					
					break;
					    case 0:
						if(choice==0)
						{
							System.out.println("logout");
						}
						else
						{
							System.out.println("invalid key");
						}
						return;
						
					    
						
						
					default:
						
						
				}
				
			}else 
			{
				System.out.println("Invalid User Id and Password");
			}
        }
        
		

	    }
    }
