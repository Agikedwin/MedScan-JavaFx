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
import controllers.AltraSoundServices;
import static database.MedscanVariables.TBL_ALTRASOUND;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author YoungGucha
 */
public class AltraSoundServicesModel {
    AltraSoundServices altra;
     public boolean insertAltraSoundServices(AltraSoundServices altraSound) {
        boolean result = false;
     String insertSQL = "INSERT INTO "+TBL_ALTRASOUND+"(`service_name`, `cost`)"
                + " VALUES (?,?)";
        try {
            
            preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, altraSound.getService_name());
            preparedStatement.setDouble(2, altraSound.getCost());
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
 
    }
    
    public ArrayList<AltraSoundServices> getAllAltraSoundServices() {
        ArrayList<AltraSoundServices> altraSoundServices = new ArrayList<>();
        System.out.println("at model");
        try {
             resultSet=statement.executeQuery("SELECT  `id`, `servicename`, `commission`  FROM  test_Sevices ");
            while (resultSet.next()) {
                System.out.println("record found");
                altra = new AltraSoundServices();
                altra.setServiceid(resultSet.getInt("id"));
                altra.setService_name(resultSet.getString("servicename"));
               altra.setCost(resultSet.getDouble("commission"));
//                 altra.setDatereg(resultSet.getTimestamp("datereg"));
//                altra.setStatus(resultSet.getInt("status"));
//                altra.setLastupdated(resultSet.getTimestamp("last_updated"));
//                altra.setDeleted(resultSet.getInt("deleted"));
                altraSoundServices.add(altra);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return altraSoundServices;
    }
    
    public AltraSoundServices getAltraSoundServices(int id) {
       
          try {
             resultSet=statement.executeQuery("`serviceid`, `service_name`, `cost`, `datereg`, "
                     + "`lastupdated`, `deleted`, `status` FROM  "+TBL_ALTRASOUND+" WHERE serviceid=?");
            while (resultSet.next()) {
                altra = new AltraSoundServices();
                altra.setServiceid(resultSet.getInt("serviceid"));
                altra.setService_name(resultSet.getString("service_name"));
                altra.setCost(resultSet.getDouble("cost"));
                 altra.setDatereg(resultSet.getTimestamp("datereg"));
                altra.setStatus(resultSet.getInt("status"));
                altra.setLastupdated(resultSet.getTimestamp("last_updated"));
                altra.setDeleted(resultSet.getInt("deleted"));
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return altra;
    }
    
    public boolean updatePayments(AltraSoundServices altraSoundServices) {
        boolean result = false;
         
       String insertSQL = "UPDATE "+TBL_ALTRASOUND+" SET service_name=?, `cost` =? WHERE WHERE serviceid=?";
        try { 
            
             preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
           
            preparedStatement.setString(1, altraSoundServices.getService_name());
            preparedStatement.setDouble(2, altraSoundServices.getCost());
            
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
    }
    
    public int deleteDoctors(AltraSoundServices altraSoundServices) {
        String selectSQL = "UPDATE "+TBL_ALTRASOUND+" SET `deleted`=1 WHERE serviceid=?";
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(selectSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, altraSoundServices.getServiceid());
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
