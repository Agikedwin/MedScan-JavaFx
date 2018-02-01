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
import controllers.ExaminationReport;
import controllers.InsurancePayments;
import database.DbConnection;
import static database.MedscanVariables.TBL_EXAMS_REPORTS;
import static database.MedscanVariables.TBL_INSURANCE_PAYMENTS;
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
public class InsurancePaymentsModel {
   
   InsurancePayments insurancePmt;
    public boolean  insertExaminationReport(InsurancePayments insurancePayments){
        boolean result = false;
       String insertSQL = "INSERT INTO "+TBL_INSURANCE_PAYMENTS+" (`insurence_id`, `name`, `member_no`, `amount`)"
                + " VALUES (?,?,?,?)";
        try {
             preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, insurancePayments.getInsurenceId());
            preparedStatement.setString(2, insurancePayments.getName());
            preparedStatement.setString(3, insurancePayments.getMemberNo());
            preparedStatement.setDouble(4, insurancePayments.getAmount());
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
               
            }
 
        return result;
 
    }
    
    public ArrayList<InsurancePayments> getAllInsurancePayments() {
        ArrayList<InsurancePayments> insurancePayments = new ArrayList<>();
       
        try {
             resultSet=statement.executeQuery("`insurence_id`, `name`, `member_no`, `amount`, `status`, `last_updated`, "
                     + "`deleted` FROM  "+TBL_INSURANCE_PAYMENTS+" ");
            while (resultSet.next()) {
                insurancePmt = new InsurancePayments();
                insurancePmt.setInsurenceId(resultSet.getString("insurence_id"));
                insurancePmt.setName(resultSet.getString("name"));
                insurancePmt.setAmount(resultSet.getDouble("amount"));
                insurancePmt.setStatus(resultSet.getBoolean("status"));
                insurancePmt.setLastUpdated(resultSet.getTimestamp("last_updated"));
                insurancePmt.setDeleted(resultSet.getBoolean("deleted"));
                insurancePayments.add(insurancePmt);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return insurancePayments;
    }
    public InsurancePayments getInsurancePayments(long insurancePaymentsid) {
       
         try {
             resultSet=statement.executeQuery("`insurence_id`, `name`, `member_no`, `amount`, `status`, `last_updated`, "
                     + "`deleted` FROM  "+TBL_INSURANCE_PAYMENTS+"  WHERE insurence_id` =?");
            while (resultSet.next()) {
                insurancePmt = new InsurancePayments();
                insurancePmt.setInsurenceId(resultSet.getString("insurence_id"));
                insurancePmt.setName(resultSet.getString("name"));
                insurancePmt.setAmount(resultSet.getDouble("amount"));
                insurancePmt.setStatus(resultSet.getBoolean("status"));
                insurancePmt.setLastUpdated(resultSet.getTimestamp("last_updated"));
                insurancePmt.setDeleted(resultSet.getBoolean("deleted"));
               
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return insurancePmt;
    }
    
    public boolean updateExaminationReport(InsurancePayments insurancePayments) {
        boolean result = false;
         
      String insertSQL = "UPDATE  "+TBL_INSURANCE_PAYMENTS+" SET ` `name=?`, `member_no=?`, `amount=?` WHERE insurence_id=?";
        try {
             preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, insurancePayments.getName());
            preparedStatement.setString(2, insurancePayments.getMemberNo());
            preparedStatement.setDouble(3, insurancePayments.getAmount());
             preparedStatement.setString(4, insurancePayments.getInsurenceId());
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
               
            }
 
        return result;
    }
    
    public int deleteDoctors(InsurancePayments insurancePayments) {
        String selectSQL = "UPDATE "+TBL_INSURANCE_PAYMENTS+" SET `deleted`=1 WHERE insurence_id=?";
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(selectSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, insurancePayments.getInsurenceId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
