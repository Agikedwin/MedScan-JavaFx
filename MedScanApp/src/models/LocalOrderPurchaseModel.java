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
import controllers.LocalOrderPurchase;
import static database.MedscanVariables.TBL_LOCAL_PURCHASE_ORDER;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author YoungGucha
 */
public class LocalOrderPurchaseModel {
    
   LocalOrderPurchase purchaseOrder;
  
    public boolean inserLocalOrderPurchase(LocalOrderPurchase purchaseOrder) {
        boolean result = false;
     String insertSQL = "INSERT INTO "+TBL_LOCAL_PURCHASE_ORDER+"(`expense_type`,"
             + " `item_name`, `description`, `quantity`, `cost`)"
             + " VALUES (?,?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, purchaseOrder.getExpenseType());
            preparedStatement.setString(2, purchaseOrder.getItemName());
            preparedStatement.setString(3, purchaseOrder.getDescription());
            preparedStatement.setDouble(4, purchaseOrder.getQuantity());
            preparedStatement.setDouble(5, purchaseOrder.getCost());
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
 
    }
    
     
    public ArrayList<LocalOrderPurchase> getAllLocalOrderPurchase() { 
        System.out.println("called at model");
        ArrayList<LocalOrderPurchase> localPurchaseOrder = new ArrayList<>();
       
        try {
             resultSet=statement.executeQuery("SELECT `orderid`, `expense_type`, `item_name`, "
                     + "`description`, `quantity`, `cost`, `date_ordered`, `status`, `lastupdated`, `deleted` "
                     + "FROM "+TBL_LOCAL_PURCHASE_ORDER+" ");
            while (resultSet.next()) {
                purchaseOrder = new LocalOrderPurchase();
                purchaseOrder.setOrderid(resultSet.getInt("orderid"));
                purchaseOrder.setExpenseType(resultSet.getString("expense_type"));
                purchaseOrder.setItemName(resultSet.getString("item_name"));
                 purchaseOrder.setDescription(resultSet.getString("description"));
                  purchaseOrder.setQuantity(resultSet.getInt("quantity"));
                   purchaseOrder.setCost(resultSet.getDouble("cost"));
                    purchaseOrder.setDateOrdered(resultSet.getTimestamp("date_ordered"));
                purchaseOrder.setStatus(resultSet.getBoolean("status"));
                purchaseOrder.setLastupdated(resultSet.getTimestamp("lastupdated"));
                purchaseOrder.setDeleted(resultSet.getBoolean("deleted"));
                localPurchaseOrder.add(purchaseOrder);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return localPurchaseOrder;
    }
    public ArrayList<LocalOrderPurchase> getAllLocalOrderPurchaseReport(String type,String year,String month) { 
       
        ArrayList<LocalOrderPurchase> localPurchaseOrder = new ArrayList<>();
       String sqlPart;
        String sqlMonth;
        String sqlYear;
       
         double totalLOP=0;
        if(type=="ALL" || type==""  ){
           sqlPart=""; 
        }else{
            sqlPart=" AND expense_type= '"+type+"'";  
       }
        if( month==""){
            sqlMonth="";
        }else{
            sqlMonth=" AND MONTH(date_ordered) ="+month+" ";
        }
        if(year==""){
          sqlYear="";  
        }else{
            sqlYear=" AND YEAR(date_ordered) ="+year+" ";
        }
        try {
             resultSet=statement.executeQuery("SELECT `orderid`, `expense_type`, `item_name`, "
                     + "`description`, `quantity`, `cost`, `date_ordered`, `status`, `lastupdated`, `deleted` "
                     + "FROM "+TBL_LOCAL_PURCHASE_ORDER+"  WHERE cost>0  "+sqlPart+"  "+sqlMonth+"  "+sqlYear+" ");
            while (resultSet.next()) {
                purchaseOrder = new LocalOrderPurchase();
                purchaseOrder.setOrderid(resultSet.getInt("orderid"));
                purchaseOrder.setExpenseType(resultSet.getString("expense_type"));
                purchaseOrder.setItemName(resultSet.getString("item_name"));
                 purchaseOrder.setDescription(resultSet.getString("description"));
                  purchaseOrder.setQuantity(resultSet.getInt("quantity"));
                   purchaseOrder.setCost(resultSet.getDouble("cost"));
                    purchaseOrder.setDateOrdered(resultSet.getTimestamp("date_ordered"));
                purchaseOrder.setStatus(resultSet.getBoolean("status"));
                purchaseOrder.setLastupdated(resultSet.getTimestamp("lastupdated"));
                purchaseOrder.setDeleted(resultSet.getBoolean("deleted"));
                totalLOP=totalLOP+purchaseOrder.getCost();
                localPurchaseOrder.add(purchaseOrder);
                purchaseOrder.setTotalRequisition(totalLOP);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return localPurchaseOrder;
    }
    public double getTotalLOP(String type,String year,String month) { 
       double totalLOP=0;
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
            sqlMonth=" AND MONTH(date_ordered) ="+month+" ";
        }
        if(year==""){
          sqlYear="";  
        }else{
            sqlYear=" AND YEAR(date_ordered) ="+year+" ";
        }
        try {
             resultSet=statement.executeQuery("SELECT `cost` FROM "+TBL_LOCAL_PURCHASE_ORDER+"  "
                     + "WHERE cost>0 "+sqlPart+"  "+sqlMonth+"  "+sqlYear+" ");
            while (resultSet.next()) {
              totalLOP=totalLOP+resultSet.getDouble("cost");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return totalLOP;
    }
    public ArrayList<LocalOrderPurchase> getLocalOrderPurchase() {
        ArrayList<LocalOrderPurchase> localPurchaseOrder = new ArrayList<>();
       
        try {
             resultSet=statement.executeQuery("SELECT `orderid`, `expense_type`, `item_name`, "
                     + "`description`, `quantity`, `cost`, `date_ordered`, `status`, `lastupdated`, `deleted` "
                     + "FROM "+TBL_LOCAL_PURCHASE_ORDER+" WHERE orderid=? ");
            while (resultSet.next()) {
                purchaseOrder = new LocalOrderPurchase();
                purchaseOrder.setOrderid(resultSet.getInt("orderid"));
                purchaseOrder.setExpenseType(resultSet.getString("expense_type"));
                purchaseOrder.setItemName(resultSet.getString("item_name"));
                 purchaseOrder.setDescription(resultSet.getString("description"));
                  purchaseOrder.setQuantity(resultSet.getInt("quantity"));
                   purchaseOrder.setCost(resultSet.getDouble("cost"));
                    purchaseOrder.setDateOrdered(resultSet.getTimestamp("date_ordered"));
                purchaseOrder.setStatus(resultSet.getBoolean("status"));
                purchaseOrder.setLastupdated(resultSet.getTimestamp("lastupdated"));
                purchaseOrder.setDeleted(resultSet.getBoolean("deleted"));
                localPurchaseOrder.add(purchaseOrder);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return localPurchaseOrder;
    }
    
    public boolean updateLocalOrderPurchase(LocalOrderPurchase purchaseOrder) {
        boolean result = false;
         
       String insertSQL = "UPDATE "+TBL_LOCAL_PURCHASE_ORDER+" SET `expense_type`=?,`item_name`=?,"
               + "`description`=?,`quantity`=?,`cost`=? WHERE orderid="+10+" ";
        try {
            
             preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
             preparedStatement.setString(1, purchaseOrder.getExpenseType());
            preparedStatement.setString(2, purchaseOrder.getItemName());
            preparedStatement.setString(3, purchaseOrder.getDescription());
            preparedStatement.setDouble(4, purchaseOrder.getQuantity());
            preparedStatement.setDouble(5, purchaseOrder.getCost());
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
    }
    
    public int deleteLocalOrderPurchase(LocalOrderPurchase purchaseOrder) {
        String updateSQL = "UPDATE "+TBL_LOCAL_PURCHASE_ORDER+" SET deleted =1 WHERE orderid=? ";
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(updateSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, purchaseOrder.getOrderid());
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    
}
