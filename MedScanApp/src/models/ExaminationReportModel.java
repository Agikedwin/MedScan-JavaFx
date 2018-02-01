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
import controllers.ExaminationReport;
import static database.MedscanVariables.TBL_EXAMS_REPORTS;
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
public class ExaminationReportModel {
    
    ExaminationReport examReport;
  
    public boolean insertExaminationReport(ExaminationReport examinationReport) {
        boolean result = false;
      String insertSQL = " INSERT INTO "+TBL_EXAMS_REPORTS+"( doctor_id,`patient_id`, `service`, `findings`, `conclusion`) "
                + "VALUES (?,?,?,?,?)";
        try {
            
             preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, examinationReport.getDoctorId());
            preparedStatement.setString(2, examinationReport.getPatientId());
            preparedStatement.setString(3, examinationReport.getService());
            preparedStatement.setString(4, examinationReport.getFindings());
             preparedStatement.setString(5, examinationReport.getConclusion());
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
 
    }
    
    public ArrayList<ExaminationReport> getAllExaminationReport() {
        ArrayList<ExaminationReport> examinationReport = new ArrayList<>();
       
        try {
             resultSet=statement.executeQuery("SELECT `examination_id`, `doctor_id`, `patient_id`, "
                     + "`service`, `findings`, `conclusion`, `reg_date`, `status`, `last_updated`, "
                     + "`deleted` FROM  "+TBL_EXAMS_REPORTS+" ");
            while (resultSet.next()) {
                examReport = new ExaminationReport();
                examReport.setPatientId(resultSet.getString("patient_id"));
                examReport.setDoctorId(resultSet.getString("doctor_id"));
                examReport.setService(resultSet.getString("service"));
                 examReport.setFindings(resultSet.getString("findings"));
                  examReport.setConclusion(resultSet.getString("conclusion"));
                examReport.setStatus(resultSet.getBoolean("status"));
                examReport.setLastUpdated(resultSet.getTimestamp("last_updated"));
                examReport.setDeleted(resultSet.getBoolean("deleted"));
                examinationReport.add(examReport);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return examinationReport;
    }
    public ExaminationReport getExaminationReport(long examinationid) {
       
        try {
             resultSet=statement.executeQuery("SELECT `examination_id`, `doctor_id`, `patient_id`, "
                     + "`service`, `findings`, `conclusion`, `reg_date`, `status`, `last_updated`, "
                     + "`deleted` FROM  "+TBL_EXAMS_REPORTS+"  WHERE examination_id =? ");
            while (resultSet.next()) {
                examReport = new ExaminationReport();
                examReport.setPatientId(resultSet.getString("patient_id"));
                examReport.setDoctorId(resultSet.getString("doctor_id"));
                examReport.setService(resultSet.getString("service"));
                 examReport.setFindings(resultSet.getString("findings"));
                  examReport.setConclusion(resultSet.getString("conclusion"));
                examReport.setStatus(resultSet.getBoolean("status"));
                examReport.setLastUpdated(resultSet.getTimestamp("last_updated"));
                examReport.setDeleted(resultSet.getBoolean("deleted"));
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return examReport;
    }
    
    public boolean updateExaminationReport(ExaminationReport examinationReport) {
        boolean result = false;
         
      String insertSQL = "UPDATE "+TBL_EXAMS_REPORTS+" SET `doctor_id`=?,`patient_id`=?,"
              + "`service`=?,`findings`=?,`conclusion`=?,`last_updated`=? WHERE examination_id=?";
        try {
             preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, examinationReport.getDoctorId());
            preparedStatement.setString(2, examinationReport.getPatientId());
            preparedStatement.setString(3, examinationReport.getService());
            preparedStatement.setString(4, examinationReport.getFindings());
             preparedStatement.setString(5, examinationReport.getConclusion());
              preparedStatement.setTimestamp(6, examinationReport.getLastUpdated());
              preparedStatement.setInt(7, examinationReport.getExaminationId());
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
    }
    
    public int deleteDoctors(ExaminationReport examinationReport) {
        String selectSQL = "UPDATE "+TBL_EXAMS_REPORTS+" SET `deleted`=1 WHERE examination_id=?";
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(selectSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setLong(1, examinationReport.getExaminationId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
