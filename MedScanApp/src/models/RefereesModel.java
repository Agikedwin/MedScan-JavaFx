/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.solutions.entorno.utilities.MessagesUtil;
import static com.solutions.entorno.utilities.SystemVariables.connection;
import static com.solutions.entorno.utilities.SystemVariables.preparedStatement;
import static com.solutions.entorno.utilities.SystemVariables.resultSet;
import static com.solutions.entorno.utilities.SystemVariables.statement;
import controllers.RefereeCommission;
import controllers.Referees;
import database.DbConnection;
import static database.MedscanVariables.TBL_DOCTORS;
import static database.MedscanVariables.TBL_REFEREES;
import static database.MedscanVariables.TBL_REFEREE_COMMISSION;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author YoungGucha
 */
public class RefereesModel {
  
   public static Referees  ref; 
    public boolean insertReferees(Referees referees) {
        boolean result = false;
    String insertSQL = "INSERT INTO "+TBL_DOCTORS+"( `name`, `hosiptal`,contact)"
                + " VALUES (?,?,?)";
        try {
            
            preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, referees.getName());
            preparedStatement.setString(2, referees.getHosiptal());
            preparedStatement.setString(3, referees.getContact());
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
 
    }
    
    public static ArrayList<Referees> getAllReferees() {
        ArrayList<Referees> referee = new ArrayList<>();
       
        try {
             resultSet=statement.executeQuery(" SELECT `doctor_id`, `name`, `hosiptal`, `contact`,status,  `lastupdated`, `deleted` FROM  "+TBL_DOCTORS+" ");
            while (resultSet.next()) {
                ref = new Referees();
                ref.setRefereeId(resultSet.getString("doctor_id"));
                ref.setName(resultSet.getString("name"));
                ref.setHosiptal(resultSet.getString("hosiptal"));
                ref.setContact(resultSet.getString("contact"));
                ref.setStatus(resultSet.getBoolean("status"));
                ref.setLastUpdated(resultSet.getTimestamp("lastupdated"));
                ref.setDeleted(resultSet.getBoolean("deleted"));
                referee.add(ref);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return referee;
    }
    public Referees getReferees(int refereeid) {
       
         try {
             resultSet=statement.executeQuery("`referee_id`, `name`, `hosiptal`, `date`,`lastupdated`, `deleted`"
                     + " FROM  "+TBL_DOCTORS+" WHERE referee_id=?");
            while (resultSet.next()) {
                ref = new Referees();
                ref.setRefereeId(resultSet.getString("referee_id"));
                ref.setName(resultSet.getString("name"));
                ref.setHosiptal(resultSet.getString("hosiptal"));
                ref.setStatus(resultSet.getBoolean("status"));
                ref.setLastUpdated(resultSet.getTimestamp("lastupdated"));
                ref.setDeleted(resultSet.getBoolean("deleted"));
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return ref;
    }
    
    public boolean updateRefereeCommision(Referees referees) {
        boolean result = false;
       String insertSQL = "UPDATE "+TBL_DOCTORS+" SET name=?, `hosiptal` =? , `date` =? `lastupdate`=?  WHERE referee_id=?";
        try {
           
             preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
           
            preparedStatement.setString(1, referees.getName());
            preparedStatement.setString(2, referees.getHosiptal());
             preparedStatement.setString(3, referees.getDate());
             preparedStatement.setTimestamp(4, referees.getLastUpdated());
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
    }
    
    public int deleteReferees(Referees referees) {
        String selectSQL = "UPDATE  "+TBL_DOCTORS+"  SET `deleted`=1 WHERE referee_id=?";
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(selectSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, referees.getRefereeId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    public static HashMap Fetchemployees() {
        HashMap<String, String> readAll = new HashMap<>();
        try {
            resultSet = statement.executeQuery("SELECT `doctor_id`, `name` from " + TBL_DOCTORS + "  ");

            while (resultSet.next()) {
                readAll.put(resultSet.getString("doctor_id"), resultSet.getString("name"));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            //MessagesUtil.displayMessage("Records Retrieval Failed", "Failed to fetch SKU records", 3, ex);
        }
        return readAll;
    }
}
