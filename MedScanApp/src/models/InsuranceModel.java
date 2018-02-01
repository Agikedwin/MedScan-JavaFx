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
import controllers.Insurance;
import static database.MedscanVariables.TBL_INSURANCE;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author YoungGucha
 */
public class InsuranceModel {
  
     Insurance insurance;
    
    
    public InsuranceModel (){
        
        
    }
   
    public boolean insertInsurance(Insurance insurance) {
        boolean result = false;
        String insertSQL = "INSERT INTO "+TBL_INSURANCE+"(`insurance_name` ) VALUES (?)";
        try {
            System.out.println("tbl  "+TBL_INSURANCE);
             preparedStatement = connection.prepareStatement(insertSQL);
           
            preparedStatement.setString(1, insurance.getInsuranceName());
            
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
    }
    
    public  ArrayList<Insurance> getAllInsurance() {
       
        ArrayList<Insurance> ins = new ArrayList<>();
        
        try {
             resultSet=statement.executeQuery("SELECT `insuranceid`, `insurance_name`, "
                     + " `lastupdated`, `deleted` FROM "+TBL_INSURANCE+"");
             System.out.println("called at model 2");
            while (resultSet.next()) {
                System.out.println("records found");
                insurance = new Insurance();
                insurance.setInsuranceId(resultSet.getInt("insuranceid"));
                insurance.setInsuranceName(resultSet.getString("insurance_name"));
                insurance.setLastUpdated(resultSet.getTimestamp("lastupdated"));
                insurance.setDeleted(resultSet.getBoolean("deleted"));
                ins.add(insurance);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return ins;
    }
     public Insurance getInsurance() {
       
        try {
             resultSet=statement.executeQuery("SELECT `insuranceid`, `insurance_name`, `staus`,"
                     + " `lastupdated`, `deleted` FROM "+TBL_INSURANCE+"");
            while (resultSet.next()) {
                insurance = new Insurance();
                insurance.setInsuranceId(resultSet.getInt("insuranceid"));
                insurance.setInsuranceName(resultSet.getString("insurance_name"));
                insurance.setStatus(resultSet.getBoolean("status"));
                insurance.setLastUpdated(resultSet.getTimestamp("lastupdated"));
                insurance.setDeleted(resultSet.getBoolean("deleted"));
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return insurance;
    }
    
    public boolean update(Insurance insurance) {
        boolean result = false;
        String insertSQL = "UPDATE "+TBL_INSURANCE+" SET `insurance_name`=? WHERE `insuranceid`=?";
        try {
             preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, insurance.getInsuranceName());
               preparedStatement.setInt(4, insurance.getInsuranceId());
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
    }
    
    public int deleteCounty(Insurance insurance) {
        String selectSQL = "UPDATE "+TBL_INSURANCE+" SET `deleted`=1 WHERE bankid=?";
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(selectSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setLong(1, insurance.getInsuranceId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
