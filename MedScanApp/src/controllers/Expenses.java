/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.solutions.entorno.utilities.InternalTableViewRenderer;
import com.solutions.entorno.utilities.TableViewRenderer;
import java.sql.Timestamp;
import java.util.ArrayList;
import javafx.scene.control.TableView;
import models.ExpenseModel;

/**
 *
 * @author YoungGucha
 */
public class Expenses {

  private int expenseid,quantity;
  private String expenseType,expenseName,description,dateIncurred,edit="Edit";
  private double amount;
  private static double totalAmount=0;
  private  static double totalExpense=0;
  private Timestamp  lastupdated;
  private boolean status,deleted;
   public static String action;
    ExpenseModel expenseModel;
    
    public Expenses(){
      expenseModel=new ExpenseModel();  
    }
    public boolean save(){
        return  expenseModel.inserExpenses(this);
    }
public boolean update(){
        return  expenseModel.updateExpenses(this);
    }
public ArrayList fetchExpenses(){
        return  expenseModel.getAllExpenses();
    }
public ArrayList fetchExpensesReport(String type,String year,String month){
        return  expenseModel.getAllExpensesReport( type, year, month);
    }
public double totalExpense(String type,String year,String month){
        return  expenseModel.getTotalExpense( type, year, month);
    }
    public int getExpenseid() {
        return expenseid;
    }

    public void setExpenseid(int expenseid) {
        this.expenseid = expenseid;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    

    
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getDateIncurred() {
        return dateIncurred;
    }

    public void setDateIncurred(String dateIncurred) {
        this.dateIncurred = dateIncurred;
    }

    

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static double getTotalExpense() {
        return totalExpense;
    }

    public static void setTotalExpense(double totalExpense) {
        Expenses.totalExpense = totalExpense;
    }

    

    

    public Timestamp getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(Timestamp lastupdated) {
        this.lastupdated = lastupdated;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public static String getAction() {
        return action;
    }

    public static void setAction(String action) {
        InternalRequisition.action = action;
    }
  
  public   TableView loadInternalReqTV() {
 
        TableView tv;
        String[] headers = {"EXPENSE ID","EXPENSE TYPE","EXPENSE NAME","EXPENSE DESCRIPTIN ", "COST ", "DATE INCURRED ", "ACTION" };
        String[] property = {"expenseid", "expenseType", "expenseName","description","amount","dateIncurred","edit"};
        ArrayList<Object> model =  fetchExpenses();

         InternalTableViewRenderer tbl = new InternalTableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }
     public   TableView loadExpensesReport(String type,String year,String month) {
 
        TableView tv;
        String[] headers = {"EXPENSE TYPE","EXPENSE NAME","EXPENSE DESCRIPTIN ", "COST ", "DATE INCURRED "};
        String[] property = { "expenseType", "expenseName","description","amount","dateIncurred"};
        ArrayList<Object> model =  fetchExpensesReport(type, year, month);

         TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }
}
