package com.bank.management.system.Interface;

import java.sql.Connection;

public interface AdminDetailinterface 
{
  void createAccount(String bankName, String location, String userName,
		  double initialBalance, String role, Connection conn);
  void deleteAccount(int userId, Connection conn);
}
