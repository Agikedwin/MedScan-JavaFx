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
import controllers.Payments;
import controllers.RefereeCommission;
import database.DbConnection;
import static database.MedscanVariables.TBL_DOCTORS;
import static database.MedscanVariables.TBL_PATIRNTS_REQUESTS;
import static database.MedscanVariables.TBL_PAYMENTS;
import static database.MedscanVariables.TBL_REFEREE_COMMISSION;
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
public class RefereeCommisionModel {
    RefereeCommission refCommission;
    
   
    public boolean insertRefereeCommision(RefereeCommission refereeCommision) {
        boolean result = false;
     String insertSQL = "INSERT INTO "+TBL_REFEREE_COMMISSION+" (`patient_id`, `doctorid`, `services`, `amount`,maxCount)"
                + " VALUES (?,?,?,?,?)";
        try {
            
            preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, refereeCommision.getPatientId());
            preparedStatement.setString(2, refereeCommision.getDoctorId());
            preparedStatement.setString(3, refereeCommision.getServices());
            preparedStatement.setDouble(4, refereeCommision.getTotalCom());
            preparedStatement.setDouble(5, refereeCommision.getCount());
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
 
    }
    
    public ArrayList<RefereeCommission> getAllRefereeCommision(String patientid,int count) {
        ArrayList<RefereeCommission> refereeCommision = new ArrayList<>();
       System.out.println(" dr "+count);
        try {
             resultSet=statement.executeQuery("SELECT rc.`ref_id`, d.name as dName,p.name,rc.`patient_id`, rc.`doctorid`, rc.`services`, rc.`amount`,"
                     + " rc.`date`, rc.`status`, rc.`lastupdate`, rc.`deleted`"
                     + " FROM  "+TBL_REFEREE_COMMISSION+" rc, "+TBL_DOCTORS+" d , "+TBL_PATIRNTS_REQUESTS+" p"
                     + " WHERE  rc.patient_id=p.patientid  AND d.doctor_id=rc.doctorid  AND  rc.patient_id='"+patientid+"' AND rc.maxCount="+count+" ");
            while (resultSet.next()) {
                refCommission = new RefereeCommission();
                refCommission.setRefId(resultSet.getString("ref_id"));
                refCommission.setPatientId(resultSet.getString("patient_id"));
                refCommission.setPatientName(resultSet.getString("name"));
                refCommission.setDoctorName(resultSet.getString("dName"));
                refCommission.setDoctorId(resultSet.getString("doctorid"));
                 refCommission.setServices(resultSet.getString("services"));
                 refCommission.setTotalCom(resultSet.getDouble("amount"));
                 refCommission.setDate(resultSet.getDate("date"));
                //refCommission.setStatus(resultSet.getBoolean("status"));
                refCommission.setLastupdated(resultSet.getTimestamp("lastupdate"));
                refCommission.setDeleted(resultSet.getBoolean("deleted"));
                refereeCommision.add(refCommission);
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return refereeCommision;
    }
     public ArrayList<RefereeCommission> getAllRefereeCommisionReport(String type,String year,String month) {
        
        ArrayList<RefereeCommission> refereeCommision = new ArrayList<>();
        String sqlPart;
        String sqlMonth;
        String sqlYear;
        double totalCom=0;
       if(type=="ALL" || type==""  ){
           sqlPart=""; 
        }else{
            sqlPart=" AND p.name =  '"+type+"' ";  
       }
        if( month==""){
            sqlMonth="";
        }else{
            sqlMonth=" AND MONTH(rc.date) ="+month+" ";
        }
        if(year==""){
          sqlYear="";  
        }else{
            sqlYear=" AND YEAR(rc.date) ="+year+" ";
        }
        try {
             resultSet=statement.executeQuery("SELECT d.name as dName, p.name,rc.`ref_id`, rc.`patient_id`, rc.`doctorid`, rc.`services`, rc.`amount`,"
                     + " rc.`date` FROM  "+TBL_REFEREE_COMMISSION+" rc, "+TBL_PATIRNTS_REQUESTS+" p,"+TBL_DOCTORS+"  d "
                     + "WHERE  rc.patient_id=p.patientid  AND d.doctor_id=rc.doctorid  "+sqlPart+"  "+sqlMonth+"  "+sqlYear+" ");
            while (resultSet.next()) {
                
                refCommission = new RefereeCommission();
                refCommission.setPatientName(resultSet.getString("name"));
                refCommission.setDoctorName(resultSet.getString("dName"));
                refCommission.setRefId(resultSet.getString("ref_id"));
                refCommission.setPatientId(resultSet.getString("patient_id"));
                refCommission.setDoctorId(resultSet.getString("doctorid"));
                 refCommission.setServices(resultSet.getString("services"));
                 refCommission.setTotalCom(resultSet.getDouble("amount"));
                 refCommission.setDate(resultSet.getDate("date"));
                 totalCom=totalCom+refCommission.getTotalCom();
                refereeCommision.add(refCommission);
                refCommission.setTotalComAmount(totalCom);
                System.out.println("com at model "+refCommission.getTotalComAmount());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return refereeCommision;
    }
    public RefereeCommission getRefereeCommision(String doctorid,String patientid) {
       
         try {
             resultSet=statement.executeQuery("SELECT `ref_id`, `patient_id`, `doctorid`, `services`, "
                     + "`amount`, `date`, `status`, `lastupdate`, `deleted`"
                     + " FROM  "+TBL_REFEREE_COMMISSION+" WHERE doctorid='"+doctorid+"' AND  patient_id='"+patientid+"'");
            while (resultSet.next()) {
                refCommission = new RefereeCommission();
                refCommission.setRefId(resultSet.getString("ref_id"));
                refCommission.setPatientId(resultSet.getString("patient_id"));
                refCommission.setDoctorId(resultSet.getString("doctorid"));
                 refCommission.setServices(resultSet.getString("services"));
                 refCommission.setTotalCom(resultSet.getDouble("amount"));
                 refCommission.setDate(resultSet.getDate("date"));
                refCommission.setStatus(resultSet.getBoolean("status"));
                refCommission.setLastupdated(resultSet.getTimestamp("lastupdate"));
                refCommission.setDeleted(resultSet.getBoolean("deleted"));
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return refCommission;
    }
    
     public int maxCount(String  patientid) {
       int count=0;
         try {
             resultSet=statement.executeQuery("SELECT MAX(maxCount) as maxcount FROM  "+TBL_REFEREE_COMMISSION+" WHERE  patient_id='"+patientid+"'");
             if(resultSet.next()){
                count=resultSet.getInt("maxcount");  
             } 
            
                
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return count;
    }
    
    public boolean updateRefereeCommision(RefereeCommission refereeCommision) {
        boolean result = false;
         
       String insertSQL = "UPDATE "+TBL_REFEREE_COMMISSION+" SET patient_id=?, `hosiptal_id` =? , `service_id` =? "
               + ", `amount` =? , `date` =?, `lastupdate`=? WHERE WHERE refereeCommisionf_id=?";
        try {
           
             preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
           
            preparedStatement.setString(1, refereeCommision.getPatientId());
            preparedStatement.setString(2, refereeCommision.getDoctorId());
             preparedStatement.setString(3, refereeCommision.getServices());
              preparedStatement.setDouble(4, refereeCommision.getTotalCom());
               preparedStatement.setDate(5, refereeCommision.getDate());
                preparedStatement.setTimestamp(7, refereeCommision.getLastupdated());
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
    }
    
    public int deleteRefereeCommision(RefereeCommission refereeCommision) {
        String selectSQL = "UPDATE  "+TBL_REFEREE_COMMISSION+"  SET `deleted`=1 WHERE refereeCommisionf_id=?";
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(selectSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, refereeCommision.getRefId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
