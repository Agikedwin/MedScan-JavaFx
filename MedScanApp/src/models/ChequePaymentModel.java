/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import static com.solutions.entorno.utilities.SystemVariables.connection;
import static com.solutions.entorno.utilities.SystemVariables.preparedStatement;
import static com.solutions.entorno.utilities.SystemVariables.resultSet;
import static com.solutions.entorno.utilities.SystemVariables.statement;
import controllers.Bank;
import controllers.ChequePayments;
import controllers.PatientsRequest;
import database.DbConnection;
import database.MedscanVariables;
import static database.MedscanVariables.TBL_CHEQUE_PAYMENTS;
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
public class ChequePaymentModel {
   
   ChequePayments chequePayment;
    
    public boolean insertChequePayment(ChequePayments chequePayments) {
        boolean result = false;
       String insertSQL = "INSERT INTO "+TBL_CHEQUE_PAYMENTS+""
                + "( `cheque_no`, `bank_id`, `amount`)"
                + " VALUES (?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, chequePayments.getChequeNo());
            preparedStatement.setInt(2, chequePayments.getBankid());
            preparedStatement.setDouble(3, chequePayments.getAmount());
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
    }
    
    public ArrayList<ChequePayments> getAllChequePayments() {
        ArrayList<ChequePayments> chequePayments = new ArrayList<>();
       
        try {
             resultSet=statement.executeQuery("SELECT `cheque_id`, `cheque_no`, `bank_id`, `amount`, `status`, "
                     + "`last_updated`, `deleted` FROM "+TBL_CHEQUE_PAYMENTS+"");
            while (resultSet.next()) {
                chequePayment = new ChequePayments();
                chequePayment.setChequeid(resultSet.getInt("cheque_id"));
                chequePayment.setChequeNo(resultSet.getInt("cheque_no"));
                chequePayment.setBankid(resultSet.getInt("bank_id"));
                 chequePayment.setAmount(resultSet.getDouble("amount"));
                chequePayment.setStatus(resultSet.getBoolean("status"));
                chequePayment.setLastUpdated(resultSet.getTimestamp("last_updated"));
                chequePayment.setDeleted(resultSet.getBoolean("deleted"));
                chequePayments.add(chequePayment);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return chequePayments;
    }
    public ChequePayments getChequePayment(long chequeid) {
       
        try {
             resultSet=statement.executeQuery("SELECT `cheque_id`, `cheque_no`, `bank_id`, `amount`, `status`, "
                     + "`last_updated`, `deleted` FROM "+TBL_CHEQUE_PAYMENTS+" WHERE chequeid="+chequeid+"");
            while (resultSet.next()) {
                chequePayment.setChequeid(resultSet.getInt("cheque_id"));
                chequePayment.setChequeNo(resultSet.getInt("cheque_no"));
                chequePayment.setBankid(resultSet.getInt("bank_id"));
                 chequePayment.setAmount(resultSet.getDouble("amount"));
                chequePayment.setStatus(resultSet.getBoolean("status"));
                chequePayment.setLastUpdated(resultSet.getTimestamp("last_updated"));
                chequePayment.setDeleted(resultSet.getBoolean("deleted"));
               
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return chequePayment;
    }
    
    public boolean updateChequePayment(ChequePayments chequePayment) {
        boolean result = false;
        String insertSQL = "UPDATE "+TBL_CHEQUE_PAYMENTS+" SET `cheque_no`=?,`bank_id`=?,`amount`=?,status =?,"
                + "`last_updated`=? WHERE `cheque_id`=?";
        try {
             preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, chequePayment.getChequeNo());
            preparedStatement.setInt(2, chequePayment.getBankid());
             preparedStatement.setDouble(3, chequePayment.getAmount());
             preparedStatement.setBoolean(4, chequePayment.getStatus());
              preparedStatement.setTimestamp(5, chequePayment.getLastUpdated());
               
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
    }
    
    public int deleteChequePayment(ChequePayments chequePayments) {
        String selectSQL = "UPDATE "+TBL_CHEQUE_PAYMENTS+" SET `deleted`=1 WHERE cheque_id=?";
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(selectSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setLong(1, chequePayments.getChequeid());
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
