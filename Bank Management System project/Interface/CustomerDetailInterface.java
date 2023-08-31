package com.bank.management.system.Interface;

import java.sql.Connection;

public interface CustomerDetailInterface 
{
 void withdrawMoney(int userId, double amount, Connection conn);
 void depositeMoney(int userId, double amount,Connection conn);
 void transactionStatement(int userId, Connection conn);
}



