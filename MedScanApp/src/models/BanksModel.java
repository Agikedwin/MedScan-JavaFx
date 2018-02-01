/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import static com.solutions.entorno.utilities.SystemVariables.SYSTEM_DATE;
import static com.solutions.entorno.utilities.SystemVariables.USER_PROFILE;
import static com.solutions.entorno.utilities.SystemVariables.connection;
import static com.solutions.entorno.utilities.SystemVariables.preparedStatement;
import static com.solutions.entorno.utilities.SystemVariables.resultSet;
import static com.solutions.entorno.utilities.SystemVariables.statement;
import controllers.Bank;
import database.DbConnection;
import database.MedscanVariables;
import static database.MedscanVariables.TBL_BANKS;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author YoungGucha
 */
public class BanksModel {
    
    
     Bank bank;
    
    
    public BanksModel (){
        
        
    }
   
    public boolean insertBanks(Bank banks) {
        boolean result = false;
        String insertSQL = "INSERT INTO "+TBL_BANKS+"(`bankid`, `account_no`, `bank_name`) VALUES (?,?,?)";
        try {
             preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, 1584);
            preparedStatement.setString(2, banks.getAccountNo());
            preparedStatement.setString(3, banks.getBankName());
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
    }
    
    public ArrayList<Bank> getAllBanks() {
        ArrayList<Bank> banks = new ArrayList<>();
        
        try {
             resultSet=statement.executeQuery("SELECT `bankid`, `account_no`, `bank_name`, `status`, "
                     + "`last_updated`, `deleted` FROM "+TBL_BANKS+"");
            while (resultSet.next()) {
                bank = new Bank();
                bank.setBankId(resultSet.getInt("bankid"));
                bank.setAccountNo(resultSet.getString("account_no"));
                bank.setBankName(resultSet.getString("bank_name"));
                bank.setStatus(resultSet.getBoolean("status"));
                bank.setLastUpdated(resultSet.getTimestamp("last_updated"));
                bank.setDeleted(resultSet.getBoolean("deleted"));
                banks.add(bank);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return banks;
    }
    public Bank getBank(long bankid) {
       
        try {
             resultSet=statement.executeQuery("SELECT `bankid`, `account_no`, `bank_name`, `status`, "
                     + "`last_updated`, `deleted` FROM "+TBL_BANKS+" WHERE bankid="+bankid+"");
            while (resultSet.next()) {
                bank = new Bank();
                bank.setBankId(resultSet.getInt("bankid"));
                bank.setAccountNo(resultSet.getString("account_no"));
                bank.setBankName(resultSet.getString("bank_name"));
                bank.setStatus(resultSet.getBoolean("status"));
                bank.setLastUpdated(resultSet.getTimestamp("last_updated"));
                bank.setDeleted(resultSet.getBoolean("deleted"));
               
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bank;
    }
    
    public boolean update(Bank banks) {
        boolean result = false;
        String insertSQL = "UPDATE "+TBL_BANKS+" SET `account_no`=?,"
                + "`bank_name`=?,`last_updated`=? WHERE `bankid`=?";
        try {
             preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, banks.getAccountNo());
            preparedStatement.setString(2, banks.getBankName());
              preparedStatement.setTimestamp(3, banks.getLastUpdated());
               preparedStatement.setInt(4, banks.getBankId());
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
    }
    
    public int deleteCounty(Bank bank) {
        String selectSQL = "UPDATE "+TBL_BANKS+" SET `deleted`=1 WHERE bankid=?";
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(selectSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setLong(1, bank.getBankId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
