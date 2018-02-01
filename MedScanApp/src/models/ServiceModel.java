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
import controllers.Services;
import database.DbConnection;
import static database.MedscanVariables.TBL_PATIRNTS_REQUESTS;
import static database.MedscanVariables.TBL_SERVICES;
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
public class ServiceModel {

    Services serv;

    public boolean insertServices(Services service) {

        boolean result = false;
        String insertSQL = " INSERT INTO " + TBL_SERVICES + "(serviceid,`patient_id`,  `service_type`, `service_name`,`cost`,service_count)"
                + " VALUES (?,?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, service.getServiceId());
            preparedStatement.setString(2, service.getPatientId());
            preparedStatement.setString(3, service.getServiceType());
            preparedStatement.setString(4, service.getServiceName());
            preparedStatement.setDouble(5, service.getCost());
            preparedStatement.setInt(6, service.getCount());
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return result;

    }

    public ArrayList<Services> getAllServices() {
        ArrayList<Services> services = new ArrayList<>();

        try {
            resultSet = statement.executeQuery("SELECT `serviceid`, `patient_id`, `service_type`, "
                    + "`service_name`, `cost`, `dateoffered`,`status`, `last_updated`, "
                    + "`deleted` FROM  " + TBL_SERVICES + " WHERE  serviceid=0");
            while (resultSet.next()) {
                serv = new Services();
                serv.setServiceId(resultSet.getInt("serviceid"));
                serv.setPatientId(resultSet.getString("patient_id"));
                serv.setServiceType(resultSet.getString("service_type"));
                serv.setClinicalInfo(resultSet.getString("service_name"));
                serv.setCost(resultSet.getDouble("cost"));
                serv.setDate(resultSet.getDate("dateoffered"));
                serv.setStatus(resultSet.getBoolean("status"));
                serv.setLastUpdated(resultSet.getTimestamp("last_updated"));
                serv.setDeleted(resultSet.getBoolean("deleted"));
                services.add(serv);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return services;
    }

    public ArrayList<Services> getAllServicesReport(String serviceType,String year,String month) {
        ArrayList<Services> services = new ArrayList<>();
        String sqlPart;
        String sqlMonth;
        String sqlYear;
        if(serviceType=="ALL" || serviceType==""  ){
           sqlPart=""; 
        }else{
            sqlPart=" AND s.service_type =  '"+serviceType+"' ";  
       }
        if( month==""){
            sqlMonth="";
        }else{
            sqlMonth=" AND MONTH(dateoffered) ="+month+" ";
        }
        if(year==""){
          sqlYear="";  
        }else{
            sqlYear=" AND YEAR(dateoffered) ="+year+" ";
        }
       try {
           String sql="SELECT p.name, s.serviceid, s.patient_id, s.service_type, "
                    + "s.service_name, s.cost, s.dateoffered FROM  " + TBL_SERVICES + " s ,"
                    + "" + TBL_PATIRNTS_REQUESTS + " p  WHERE  s.patient_id=p.patientid  "+sqlPart+"  "+sqlMonth+"  "+sqlYear+" " ;
          
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                serv = new Services();
                serv.setPatientName(resultSet.getString("name"));
                serv.setServiceId(resultSet.getInt("serviceid"));
                serv.setPatientId(resultSet.getString("patient_id"));
                serv.setServiceType(resultSet.getString("service_type"));
                serv.setServiceName(resultSet.getString("service_name"));
                serv.setCost(resultSet.getDouble("cost"));
                serv.setDate(resultSet.getDate("dateoffered"));
                services.add(serv);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return services;
    }

    public double getTotalSevicesCost(String serviceType,String year,String month) {
         String sqlPart;
        String sqlMonth;
        String sqlYear;
        if(serviceType=="ALL" || serviceType==""  ){
           sqlPart=""; 
        }else{
            sqlPart=" AND service_type =  '"+serviceType+"' ";  
       }
        if( month==""){
            sqlMonth="";
        }else{
            sqlMonth=" AND MONTH(dateoffered) ="+month+" ";
        }
        if(year==""){
          sqlYear="";  
        }else{
            sqlYear=" AND YEAR(dateoffered) ="+year+" ";
        }

        double totalserviceCost = 0;

        try {
            String sql="SELECT cost FROM  " + TBL_SERVICES + "  WHERE cost > 0 "
                    + "  "+sqlPart+" "+sqlMonth+"  "+sqlYear+" ";
            
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                totalserviceCost = totalserviceCost + resultSet.getDouble("cost");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return totalserviceCost;
    }

    public Services getService(String patientid) {

        try {
            resultSet = statement.executeQuery("SELECT `service_id`, `patient_id`, `service_type`, "
                    + "`service_name`, `cost`, `dateoffered`,`status`, `last_updated`, "
                    + "`deleted` FROM  " + TBL_SERVICES + "  WHERE patient_id='" + patientid + "'");
            while (resultSet.next()) {

                serv = new Services();
                serv.setServiceId(resultSet.getInt("service_id"));
                serv.setPatientId(resultSet.getString("patient_id"));
                serv.setServiceType(resultSet.getString("service_type"));
                serv.setClinicalInfo(resultSet.getString("service_name"));
                serv.setCost(resultSet.getDouble("cost"));
                serv.setDate(resultSet.getDate("dateoffered"));
                serv.setStatus(resultSet.getBoolean("status"));
                serv.setLastUpdated(resultSet.getTimestamp("last_updated"));
                serv.setDeleted(resultSet.getBoolean("deleted"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return serv;
    }

    public int getServiceCount(String patientid) {
        int servicecount = 0;
        try {
            resultSet = statement.executeQuery("SELECT MAX(service_count) as maxcount FROM  " + TBL_SERVICES + "  WHERE patient_id='" + patientid + "'");
            if (resultSet.next()) {
                servicecount = resultSet.getInt("maxcount");
            } else {
                servicecount = 0;
            }
            System.out.println("service maxcount " + servicecount);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return servicecount;
    }

    public double getServiceCom(String patientid,int maxcount) {
        double totalCom = 0, commission = 0;
        try {

            String serviceType = null;
            resultSet = statement.executeQuery("SELECT `serviceid`,  `patient_id`, `service_type`, `cost`, `last_updated`"
                    + " FROM  " + TBL_SERVICES + " WHERE  patient_id='" + patientid + "' AND   service_count=" + maxcount + " ");
            while (resultSet.next()) {
                serviceType = (resultSet.getString("service_type"));
                if (serviceType.equalsIgnoreCase("XRAY")) {
                    commission = (resultSet.getDouble("cost") * 0.01);
                } else if (serviceType.equalsIgnoreCase("ALTRA-SOUND")) {
                    commission = 500;
                } else if (serviceType.equalsIgnoreCase("CT-SCAN")) {
                    commission = (resultSet.getDouble("cost") * 0.02);
                }
                totalCom = totalCom + commission;

                System.out.println("the total commission 1 " + totalCom);
            }
            System.out.println("the total commission  " + totalCom);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return totalCom;
    }

    public double getServiceCost(String patientid) {
        double totalCost = 0, commission = 0;
        try {

            String serviceType = null;
            resultSet = statement.executeQuery("SELECT `serviceid`,  `patient_id`, `cost`"
                    + " FROM  " + TBL_SERVICES + " WHERE  patient_id='" + patientid + "' AND DATE(dateoffered)=CURDATE()");
            while (resultSet.next()) {
                commission = (resultSet.getDouble("cost"));

                totalCost = totalCost + commission;
            }
            System.out.println("the total commission  " + totalCost);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return totalCost;
    }

    public ArrayList getServiceComOffered(String patientid, int maxCount) {
        ArrayList<String> servicesOffered = new ArrayList<>();

        try {

            resultSet = statement.executeQuery("SELECT `serviceid`, `patient_id`, `service_type`, `service_name`, `cost`, `last_updated`"
                    + " FROM  " + TBL_SERVICES + " WHERE patient_id='" + patientid + "' AND service_count=" + maxCount + " ");
            while (resultSet.next()) {

                servicesOffered.add(resultSet.getString("service_name") + " \t");

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return servicesOffered;
    }

    public boolean updateServices(Services service) {
        boolean result = false;

        String insertSQL = "UPDATE " + TBL_SERVICES + " SET `patient_id`=?,"
                + "`service_type`=?,`service_name`=?,`cost`=?,`last_updated`=? WHERE `service_id`=?";
        try {
            preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, service.getPatientId());
            preparedStatement.setString(2, service.getServiceType());
            preparedStatement.setString(3, service.getClinicalInfo());
            preparedStatement.setDouble(4, service.getCost());
            preparedStatement.setTimestamp(5, service.getLastUpdated());
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return result;
    }

    public int deleteServices(Services service) {
        String selectSQL = "UPDATE " + TBL_SERVICES + " SET `deleted`=1 WHERE service_id=?";
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(selectSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, service.getServiceId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
