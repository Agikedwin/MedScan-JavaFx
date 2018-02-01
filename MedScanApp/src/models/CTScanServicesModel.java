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
import controllers.CtScanServices;
import static database.MedscanVariables.TBL_CTSCAN;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author YoungGucha
 */
public class CTScanServicesModel {
    CtScanServices ctScan;
     
    public boolean insertCtScanServices(CtScanServices ctScanServices) {
        boolean result = false;
     String insertSQL = "INSERT INTO "+TBL_CTSCAN+"(`service_name`, `cost`)"
                + " VALUES (?,?)";
        try {
            
            preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, ctScanServices.getService_name());
            preparedStatement.setDouble(2, ctScanServices.getCost());
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
 
    }
    
    public ArrayList<CtScanServices> getAllCtScanServices() {
        ArrayList<CtScanServices> ctScanServices = new ArrayList<>();
       
        try {
             resultSet=statement.executeQuery("SELECT `serviceid`, `service_name`, `cost`, `datereg`, "
                     + " `lastupdated`, `deleted`, `status` FROM  "+TBL_CTSCAN+" ");
            while (resultSet.next()) {
                ctScan = new CtScanServices();
                ctScan.setServiceid(resultSet.getInt("serviceid"));
                ctScan.setService_name(resultSet.getString("service_name"));
                ctScan.setCost(resultSet.getDouble("cost"));
                 ctScan.setDatereg(resultSet.getTimestamp("datereg"));
                ctScan.setStatus(resultSet.getInt("status"));
                ctScan.setLastupdated(resultSet.getTimestamp("lastupdated"));
                ctScan.setDeleted(resultSet.getInt("deleted"));
                ctScanServices.add(ctScan);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return ctScanServices;
    }
    
    public CtScanServices getCtScanServices(int id) {
       
          try {
             resultSet=statement.executeQuery("`serviceid`, `service_name`, `cost`, `datereg`, "
                     + "`lastupdated`, `deleted`, `status` FROM  "+TBL_CTSCAN+" WHERE serviceid=?");
            while (resultSet.next()) {
                ctScan = new CtScanServices();
                ctScan.setServiceid(resultSet.getInt("serviceid"));
                ctScan.setService_name(resultSet.getString("service_name"));
                ctScan.setCost(resultSet.getDouble("cost"));
                 ctScan.setDatereg(resultSet.getTimestamp("datereg"));
                ctScan.setStatus(resultSet.getInt("status"));
                ctScan.setLastupdated(resultSet.getTimestamp("last_updated"));
                ctScan.setDeleted(resultSet.getInt("deleted"));
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return ctScan;
    }
    
    public boolean updateXRayServices(CtScanServices xtScanServices) {
        boolean result = false;
         
       String insertSQL = "UPDATE "+TBL_CTSCAN+" SET service_name=?, `cost` =? WHERE WHERE serviceid=?";
        try { 
            
             preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
           
            preparedStatement.setString(1, xtScanServices.getService_name());
            preparedStatement.setDouble(2, xtScanServices.getCost());
            
            
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
    }
    
    public int deleteXRayServices(CtScanServices xtScanServices) {
        String selectSQL = "UPDATE "+TBL_CTSCAN+" SET `deleted`=1 WHERE serviceid=?";
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(selectSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, xtScanServices.getServiceid());
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    public static void main(String[] args) {
        
                
    }
           
            
            
    
}
