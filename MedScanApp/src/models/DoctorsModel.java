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
import controllers.ChequePayments;
import controllers.Doctors;
import database.DbConnection;
import static database.MedscanVariables.TBL_DOCTORS;
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
public class DoctorsModel {
   
   public static Doctors doctor;
   
    public boolean insertDoctors(Doctors doctors) {
        boolean result = false;
      String insertSQL = "INSERT INTO "+TBL_DOCTORS+" ( `name`, `service`, `commission`)"
                + " VALUES (?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, doctors.getName());
            preparedStatement.setString(2, doctors.getService());
            preparedStatement.setDouble(3, doctors.getCommission());
                         
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
    }
    
    /**
     *
     * @return
     */
    public static ArrayList<Doctors> getAllDoctor() {
         System.out.println("doctor found ");
        ArrayList<Doctors> doctors = new ArrayList<>();
       
        try {
             resultSet=statement.executeQuery("SELECT `doctor_id`, `name`, `service`, `commission`,"
                     + " `status`, `last_updated`, `deleted`  FROM "+TBL_DOCTORS+" ");
            while (resultSet.next()) {
                
                doctor = new Doctors();
                System.out.println("doctor found "+resultSet.getInt("doctor_id"));
                doctor.setDoctorid(resultSet.getInt("doctor_id"));
                doctor.setName(resultSet.getString("name"));
                doctor.setService(resultSet.getString("name"));
                 doctor.setCommission(resultSet.getDouble("commission"));
                doctor.setStatus(resultSet.getBoolean("status"));
                doctor.setLastUpdated(resultSet.getTimestamp("last_updated"));
                doctor.setDeleted(resultSet.getBoolean("deleted"));
                doctors.add(doctor);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return doctors;
    }
    public Doctors getDoctors(long doctorid) {
       
        try {
             resultSet=statement.executeQuery("SELECT `doctor_id`, `name`, `service`, `commission`,"
                     + " `status`, `last_updated`, `deleted`  FROM "+TBL_DOCTORS+"  WHERE doctor_id=?");
            while (resultSet.next()) {
                doctor = new Doctors();
                doctor.setDoctorid(resultSet.getInt("doctor_id"));
                doctor.setName(resultSet.getString("name"));
                doctor.setService(resultSet.getString("service"));
                 doctor.setCommission(resultSet.getDouble("commission"));
                doctor.setStatus(resultSet.getBoolean("status"));
                doctor.setLastUpdated(resultSet.getTimestamp("last_updated"));
                doctor.setDeleted(resultSet.getBoolean("deleted"));
               
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return doctor;
    }
    
    public boolean updateDoctors(Doctors doctors) {
        boolean result = false;
         
      String insertSQL = "UPDATE   "+TBL_DOCTORS+" SET `name=?`, `service=?`, `commission=? WHERE doctor_id=?`)"
                + " VALUES (?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, doctors.getName());
            preparedStatement.setString(2, doctors.getService());
            preparedStatement.setDouble(3, doctors.getCommission());
             preparedStatement.setDouble(4, doctors.getDoctorid());
                         
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
    }
    
    public int deleteDoctors(Doctors doctors) {
        String selectSQL = "UPDATE "+TBL_DOCTORS+" SET `deleted`=1 WHERE cheque_id=?";
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(selectSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setLong(1, doctors.getDoctorid());
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
