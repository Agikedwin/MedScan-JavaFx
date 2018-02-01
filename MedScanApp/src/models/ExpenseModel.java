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
import controllers.Expenses;
import static database.MedscanVariables.TBL_EXPENSES;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author YoungGucha
 */
public class ExpenseModel {
   
   Expenses expense;
  
    public boolean inserExpenses(Expenses expense) {
        boolean result = false;
     String insertSQL = "INSERT INTO "+TBL_EXPENSES+"( `expense_type`, `expense_name`, `description`, `amount` )"
             + " VALUES (?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, expense.getExpenseType());
            preparedStatement.setString(2, expense.getExpenseName());
            preparedStatement.setString(3, expense.getDescription());
            preparedStatement.setDouble(4, expense.getAmount());
           // preparedStatement.setString(5, expense.getDateIncurred());
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
 
    }
    
     
    public ArrayList<Expenses> getAllExpenses() {
        ArrayList<Expenses> exp = new ArrayList<>();
       
        try {
             resultSet=statement.executeQuery(" SELECT `expenseid`, `expense_type`, `expense_name`,"
                     + " `description`, `amount`, `dateincured`, `status`, `lastupdated`, `deleted` "
                     + " FROM "+TBL_EXPENSES+" ");
            while (resultSet.next()) {
                expense = new Expenses();
                expense.setExpenseid(resultSet.getInt("expenseid"));
                expense.setExpenseType(resultSet.getString("expense_type"));
                expense.setExpenseName(resultSet.getString("expense_name"));
                 expense.setDescription(resultSet.getString("description"));
                  expense.setAmount(resultSet.getDouble("amount"));
                   expense.setDateIncurred(resultSet.getString("dateincured"));
                expense.setStatus(resultSet.getBoolean("status"));
                expense.setLastupdated(resultSet.getTimestamp("lastupdated"));
                expense.setDeleted(resultSet.getBoolean("deleted"));
                exp.add(expense);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return exp;
    }
    public ArrayList<Expenses> getAllExpensesReport(String type,String year,String month) {
        ArrayList<Expenses> exp = new ArrayList<>();
       String sqlPart;
        String sqlMonth;
        String sqlYear;
       
         double totalExp=0;
        if(type=="ALL" || type==""  ){
           sqlPart=""; 
        }else{
            sqlPart=" AND expense_type= '"+type+"'";  
       }
        if( month==""){
            sqlMonth="";
        }else{
            sqlMonth=" AND MONTH(dateincured) ="+month+" ";
        }
        if(year==""){
          sqlYear="";  
        }else{
            sqlYear=" AND YEAR(dateincured) ="+year+" ";
        }
        try {
            String sql=" SELECT `expenseid`, `expense_type`, `expense_name`,"
                     + " `description`, `amount`, `dateincured`, `status`, `lastupdated`, `deleted` "
                     + " FROM "+TBL_EXPENSES+" WHERE amount>0  "+sqlPart+"  "+sqlMonth+"  "+sqlYear+" ";
            
             resultSet=statement.executeQuery(sql);
            while (resultSet.next()) {
                expense = new Expenses();
                expense.setExpenseid(resultSet.getInt("expenseid"));
                expense.setExpenseType(resultSet.getString("expense_type"));
                expense.setExpenseName(resultSet.getString("expense_name"));
                 expense.setDescription(resultSet.getString("description"));
                  expense.setAmount(resultSet.getDouble("amount"));
                   expense.setDateIncurred(resultSet.getString("dateincured"));
                expense.setStatus(resultSet.getBoolean("status"));
                expense.setLastupdated(resultSet.getTimestamp("lastupdated"));
                expense.setDeleted(resultSet.getBoolean("deleted"));
               totalExp=totalExp+expense.getAmount();
                exp.add(expense);
                expense.setTotalExpense(totalExp);
               
            }
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return exp;
    }
    
    
     public double getTotalExpense(String type,String year,String month) {
         double totalAmount=0;
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
            sqlMonth=" AND MONTH(dateincured) ="+month+" ";
        }
        if(year==""){
          sqlYear="";  
        }else{
            sqlYear=" AND YEAR(dateincured) ="+year+" ";
        }
        try {
             resultSet=statement.executeQuery("SELECT  `amount` FROM "+TBL_EXPENSES+" "
                     + "WHERE amount>0 "+sqlPart+"  "+sqlMonth+"  "+sqlYear+" ");
            while (resultSet.next()) {
               totalAmount=totalAmount+resultSet.getDouble("amount");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return totalAmount;
    }
    
    public boolean updateExpenses(Expenses expense) {
        boolean result = false;
         
        String insertSQL = "UPDATE   "+TBL_EXPENSES+" SET  `expense_type=?`, `expense_name=?`, `description=?`, `amount=?`, `dateincured=?` "
             + "WHERE expenseid="+expense.getExpenseid()+"";
        try {
            preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, expense.getExpenseType());
            preparedStatement.setString(2, expense.getExpenseName());
            preparedStatement.setString(3, expense.getDescription());
            preparedStatement.setDouble(4, expense.getAmount());
            preparedStatement.setString(5, expense.getDateIncurred());
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
    }
    
    public int deleteExpenses(Expenses expense) {
        String updateSQL = "UPDATE "+TBL_EXPENSES+" SET deleted =1 WHERE expenseid=? ";
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(updateSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, expense.getExpenseid());
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
       
}
