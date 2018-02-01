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
import controllers.RefereeCommission;
import controllers.PatientsRequest;
import database.DbConnection;
import static database.MedscanVariables.TBL_PATIRNTS_REQUESTS;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author YoungGucha
 */
public class PatientsRequetModel {
    
   
    PatientsRequest patientsReq;
    
    public boolean insertPatientsRequet(PatientsRequest request) {
        boolean result = false;
     String insertSQL = "INSERT INTO "+TBL_PATIRNTS_REQUESTS+"( `name`, `gender`,phone_no,age,`address`, lmp,regdate )"
                + " VALUES (?,?,?,?,?,?,?)";
        try {
           
            preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
           preparedStatement.setString(1, request.getName());
            preparedStatement.setString(2, request.getGender());
            preparedStatement.setString(3, request.getPhoneNo());
            preparedStatement.setString(4, request.getAge());
            preparedStatement.setString(5, request.getAddress());
              preparedStatement.setString(6, request.getLmp());
               preparedStatement.setString(7, request.getRegdate()); 
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
 
    }
    
    public ArrayList<PatientsRequest> getAllPatientsRequest() {
      
        ArrayList<PatientsRequest> patientsRequest = new ArrayList<>();
       
        try {
             resultSet=statement.executeQuery("SELECT  `patientid`, `name`, `gender`,phone_no,age,`dob`, `address`, `dob`, `regdate`,`status`,"
                     + " `lastupdated`, `deleted` FROM  "+TBL_PATIRNTS_REQUESTS+" WHERE deleted=0");
            while (resultSet.next()) {
                patientsReq = new PatientsRequest();
                patientsReq.setPatientid(resultSet.getString("patientid"));
                patientsReq.setName(resultSet.getString("name"));
                patientsReq.setGender(resultSet.getString("gender"));
                 patientsReq.setPhoneNo(resultSet.getString("phone_no"));
                  patientsReq.setAge(resultSet.getString("age"));
                 patientsReq.setAddress(resultSet.getString("address"));
                 patientsReq.setDob(resultSet.getString("dob"));
                 patientsReq.setRegdate(resultSet.getString("regdate"));
                patientsReq.setStatus(resultSet.getBoolean("status"));
                patientsReq.setLastUpdated(resultSet.getTimestamp("lastupdated"));
                patientsReq.setDeleted(resultSet.getBoolean("deleted"));
                patientsRequest.add(patientsReq);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return patientsRequest;
    }
    
    public PatientsRequest getPatientsRequest(int patientid) {
       
         try {
             resultSet=statement.executeQuery("SELECT  `patientid`, `name`, `gender`,phone_no,age, `address`, `dob`, `regdate`,`status`,"
                     + " `lastupdate`, `deleted` FROM  "+TBL_PATIRNTS_REQUESTS+" WHERE patientid=?");
            while (resultSet.next()) {
                patientsReq = new PatientsRequest();
                patientsReq.setPatientid(resultSet.getString("patientid"));
                patientsReq.setName(resultSet.getString("name"));
                patientsReq.setGender(resultSet.getString("gender"));                
                 patientsReq.setPhoneNo(resultSet.getString("phone_no"));
                  patientsReq.setAge(resultSet.getString("age"));
                 patientsReq.setAddress(resultSet.getString("address"));
                 patientsReq.setDob(resultSet.getString("dob"));
                 patientsReq.setRegdate(resultSet.getString("regdate"));
                patientsReq.setStatus(resultSet.getBoolean("status"));
                patientsReq.setLastUpdated(resultSet.getTimestamp("lastupdate"));
                patientsReq.setDeleted(resultSet.getBoolean("deleted"));
               
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return patientsReq;
    }
     public int nextPatientid() {
       int nextid=0;
         try {
             resultSet=statement.executeQuery("SELECT  MAX(patientid) AS patientid FROM  "+TBL_PATIRNTS_REQUESTS+" ");
            if (resultSet.next()) {
              nextid=resultSet.getInt("patientid");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         // next id is max id plus 1
        return nextid+1;
    }
    
    public boolean updatePatientsRequest(PatientsRequest request) {
        boolean result = false;
        
       String insertSQL = "UPDATE "+TBL_PATIRNTS_REQUESTS+" SET `name` =? , `gender` =? "
               + ",phone_no=?,age=?, address =? , `dob` =? , `lmp`=?, `regdate` = ?  WHERE patientid=?";
       
       try {
           
             preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
           
             preparedStatement.setString(1, request.getName());
            preparedStatement.setString(2, request.getGender());
            preparedStatement.setString(3, request.getPhoneNo());
            preparedStatement.setString(4, request.getAge());
             preparedStatement.setString(6, request.getAge());
            preparedStatement.setString(5, request.getAddress());
              preparedStatement.setString(7, request.getLmp());
               preparedStatement.setString(8, request.getRegdate()); 
                preparedStatement.setString(9, request.getPatientid());
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
    }
    
    public boolean deletePatientsRequest(PatientsRequest request) {
        System.out.println("id  "+request.getPatientidEdit());
        String selectSQL = "UPDATE  "+TBL_PATIRNTS_REQUESTS+"  SET `deleted`=1 WHERE patientid=?";
        boolean result = false;
        try {
            preparedStatement = connection.prepareStatement(selectSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, request.getPatientidEdit());
            preparedStatement.executeUpdate();
             result =true;            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
