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
import controllers.InternalRequisition;
import static database.MedscanVariables.TBL_INTERNAL_REQUISITION;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author YoungGucha
 */
public class InternalRequisitionModel {
    
    
   InternalRequisition internalRequisition;
  
    public boolean inserLocalOrderPurchase(InternalRequisition internalRequisition) {
        boolean result = false;
     String insertSQL = "INSERT INTO "+TBL_INTERNAL_REQUISITION+"( `expense_type`, `item_name`, `description`, `quantity`, `cost`)"
             + " VALUES (?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, internalRequisition.getExpenseType());
            preparedStatement.setString(2, internalRequisition.getItemName());
            preparedStatement.setString(3, internalRequisition.getDescription());
            preparedStatement.setDouble(4, internalRequisition.getQuantity());
            preparedStatement.setDouble(5, internalRequisition.getCost());
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
 
    }
    
     
    public ArrayList<InternalRequisition> getAllInternalRequisition() {
        ArrayList<InternalRequisition> internalReq = new ArrayList<>();
       
        try {
             resultSet=statement.executeQuery("SELECT `requisitionid`, `expense_type`, `item_name`, "
                     + "`description`, `quantity`, `cost`,  `status`, `lastupdated`, `deleted` "
                     + "FROM "+TBL_INTERNAL_REQUISITION+" ");
            while (resultSet.next()) {
                internalRequisition = new InternalRequisition();
                internalRequisition.setRequisitionid(resultSet.getInt("requisitionid"));
                internalRequisition.setExpenseType(resultSet.getString("expense_type"));
                internalRequisition.setItemName(resultSet.getString("item_name"));
                 internalRequisition.setDescription(resultSet.getString("description"));
                  internalRequisition.setQuantity(resultSet.getInt("quantity"));
                   internalRequisition.setCost(resultSet.getDouble("cost"));
                    internalRequisition.setDateOrdered(resultSet.getTimestamp("date_ordered"));
                internalRequisition.setStatus(resultSet.getBoolean("status"));
                internalRequisition.setLastupdated(resultSet.getTimestamp("lastupdated"));
                internalRequisition.setDeleted(resultSet.getBoolean("deleted"));
                internalReq.add(internalRequisition);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return internalReq;
    }
    public ArrayList<InternalRequisition> getAInternalRequisition() {
        ArrayList<InternalRequisition> internalReq = new ArrayList<>();
       
        try {
             resultSet=statement.executeQuery("SELECT `requisitionid`, `expense_type`, `item_name`, "
                     + "`description`, `quantity`, `cost`,  `status`, `lastupdated`, `deleted` "
                     + "FROM "+TBL_INTERNAL_REQUISITION+" ");
            while (resultSet.next()) {
                internalRequisition = new InternalRequisition();
                 internalRequisition.setRequisitionid(resultSet.getInt("requisitionid"));
                internalRequisition.setExpenseType(resultSet.getString("expense_type"));
                internalRequisition.setItemName(resultSet.getString("item_name"));
                 internalRequisition.setDescription(resultSet.getString("description"));
                  internalRequisition.setQuantity(resultSet.getInt("quantity"));
                   internalRequisition.setCost(resultSet.getDouble("cost"));
                    //internalRequisition.setDateOrdered(resultSet.getTimestamp("date_ordered"));
                internalRequisition.setStatus(resultSet.getBoolean("status"));
                internalRequisition.setLastupdated(resultSet.getTimestamp("lastupdated"));
                internalRequisition.setDeleted(resultSet.getBoolean("deleted"));
                internalReq.add(internalRequisition);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return internalReq;
    }
    public ArrayList<InternalRequisition> getAInternalRequisitionReport(String type,String year,String month) {
        ArrayList<InternalRequisition> internalReq = new ArrayList<>();
       String sqlPart;
        String sqlMonth;
        String sqlYear;
       
         double totalReq=0;
        if(type=="ALL" || type==""  ){
           sqlPart=""; 
        }else{
            sqlPart=" AND expense_type= '"+type+"'";  
       }
        if( month==""){
            sqlMonth="";
        }else{
            sqlMonth=" AND MONTH(lastupdated) ="+month+" ";
        }
        if(year==""){
          sqlYear="";  
        }else{
            sqlYear=" AND YEAR(lastupdated) ="+year+" ";
        }
        try {
             resultSet=statement.executeQuery("SELECT `requisitionid`, `expense_type`, `item_name`, "
                     + "`description`, `quantity`, `cost`,  `status`, `lastupdated`, `deleted` "
                     + "FROM "+TBL_INTERNAL_REQUISITION+" WHERE cost>0  "+sqlPart+"  "+sqlMonth+"  "+sqlYear+" ");
            while (resultSet.next()) {
                internalRequisition = new InternalRequisition();
                 internalRequisition.setRequisitionid(resultSet.getInt("requisitionid"));
                internalRequisition.setExpenseType(resultSet.getString("expense_type"));
                internalRequisition.setItemName(resultSet.getString("item_name"));
                 internalRequisition.setDescription(resultSet.getString("description"));
                  internalRequisition.setQuantity(resultSet.getInt("quantity"));
                   internalRequisition.setCost(resultSet.getDouble("cost"));
                    //internalRequisition.setDateOrdered(resultSet.getTimestamp("date_ordered"));
                internalRequisition.setStatus(resultSet.getBoolean("status"));
                internalRequisition.setLastupdated(resultSet.getTimestamp("lastupdated"));
                internalRequisition.setDeleted(resultSet.getBoolean("deleted"));
                 totalReq=totalReq+internalRequisition.getCost();
                internalReq.add(internalRequisition);
                internalRequisition.setTotalLPO(totalReq);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return internalReq;
    }
    
    
    public double getTotalReq(String type,String year,String month) {
       double totalReq=0;
       String sqlPart;
        String sqlMonth;
        String sqlYear;
        double totalPayment=0;
         double totalPaymentDiscount=0;
        if(type=="ALL" || type==""  ){
           sqlPart=""; 
        }else{
            sqlPart=" ";  
       }
        if( month==""){
            sqlMonth="";
        }else{
            sqlMonth=" AND MONTH(lastupdated) ="+month+" ";
        }
        if(year==""){
          sqlYear="";  
        }else{
            sqlYear=" AND YEAR(lastupdated) ="+year+" ";
        }
        try {
             resultSet=statement.executeQuery("SELECT `cost` FROM "+TBL_INTERNAL_REQUISITION+" "
                     + "WHERE cost>0 "+sqlPart+"  "+sqlMonth+"  "+sqlYear+" ");
            while (resultSet.next()) {
               totalReq=totalReq+resultSet.getDouble("cost");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return totalReq;
    }
    public boolean updateInternalRequisition(InternalRequisition internalRequisition) {
        boolean result = false;
         
       String insertSQL = "UPDATE "+TBL_INTERNAL_REQUISITION+" SET `expense_type`=?,`item_name`=?,"
               + "`description`=?,`quantity`=?,`cost`=? WHERE requisitionid= "+internalRequisition.getRequisitionid()+"";
        try {
             preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
             preparedStatement.setString(1, internalRequisition.getExpenseType());
            preparedStatement.setString(2, internalRequisition.getItemName());
            preparedStatement.setString(3, internalRequisition.getDescription());
            preparedStatement.setDouble(4, internalRequisition.getQuantity());
            preparedStatement.setDouble(5, internalRequisition.getCost());
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
    }
    
    
    public int deleteLocalOrderPurchase(InternalRequisition internalRequisition) {
        String updateSQL = "UPDATE "+TBL_INTERNAL_REQUISITION+" SET deleted =1 WHERE requisitionid=? ";
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(updateSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, internalRequisition.getRequisitionid());
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
}
