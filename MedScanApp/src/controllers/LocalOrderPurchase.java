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
import models.LocalOrderPurchaseModel;

/**
 *
 * @author YoungGucha
 */
public class LocalOrderPurchase {
 
private int orderid,quantity;
  private String expenseType,itemName,description;
  private double cost;
  private Timestamp dateOrdered , lastupdated;
  private boolean status,deleted;
   private String edit="Edit";
   
   //variables for editing
   public static int  orderidEdit,quantityEdit;
 public static String expenseTypeEdit,itemNameEdit,descriptionEdit;
  public static double costEdit;
  public static double totalRequisition;
  //end 
   public static String action;
    LocalOrderPurchaseModel localOrderPurchaseModel;
    public LocalOrderPurchase(){
        localOrderPurchaseModel=new LocalOrderPurchaseModel();
    }
    
    public boolean save(){
        return  localOrderPurchaseModel.inserLocalOrderPurchase(this);
    }
    private ArrayList fetchLOP(){
        return localOrderPurchaseModel.getAllLocalOrderPurchase();
    }
    private ArrayList fetchLOPReport(String type,String year,String month){
        return localOrderPurchaseModel.getAllLocalOrderPurchaseReport( type, year, month);
    }
    public double totalLOP(String type,String year,String month){
        return localOrderPurchaseModel.getTotalLOP( type, year, month);
    }
    public boolean update(){
        return localOrderPurchaseModel.updateLocalOrderPurchase(this);
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Timestamp getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(Timestamp dateOrdered) {
        this.dateOrdered = dateOrdered;
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
        LocalOrderPurchase.action = action;
    } 

    public static double getTotalRequisition() {
        return totalRequisition;
    }

    public static void setTotalRequisition(double totalRequisition) {
        LocalOrderPurchase.totalRequisition = totalRequisition;
    }
    
    
//editing getter and setteres

    public static int getOrderidEdit() {
        return orderidEdit;
    }

    public static void setOrderidEdit(int orderidEdit) {
        LocalOrderPurchase.orderidEdit = orderidEdit;
    }

    public static int getQuantityEdit() {
        return quantityEdit;
    }

    public static void setQuantityEdit(int quantityEdit) {
        LocalOrderPurchase.quantityEdit = quantityEdit;
    }

    public static String getExpenseTypeEdit() {
        return expenseTypeEdit;
    }

    public static void setExpenseTypeEdit(String expenseTypeEdit) {
        LocalOrderPurchase.expenseTypeEdit = expenseTypeEdit;
    }

    public static String getItemNameEdit() {
        return itemNameEdit;
    }

    public static void setItemNameEdit(String itemNameEdit) {
        LocalOrderPurchase.itemNameEdit = itemNameEdit;
    }

    public static String getDescriptionEdit() {
        return descriptionEdit;
    }

    public static void setDescriptionEdit(String descriptionEdit) {
        LocalOrderPurchase.descriptionEdit = descriptionEdit;
    }

    public static double getCostEdit() {
        return costEdit;
    }

    public static void setCostEdit(double costEdit) {
        LocalOrderPurchase.costEdit = costEdit;
    }
    
   

   
    
  

    public   TableView loadLocalOrderPurchaseTV() {
      
        TableView tv;
        String[] headers = {"ORDER ID","ITEM TYPE","ITEM NAME","ITEM DESCRIPTIN ", "QUANTYTY ", "COST", "ACTION" };
        String[] property = {"orderid", "expenseType", "itemName","description","quantity","cost","edit"};
        ArrayList<Object> model =  fetchLOP();

         InternalTableViewRenderer tbl = new InternalTableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }
   public   TableView loadLocalOrderPurchaseReport(String type,String year,String month) {
      
        TableView tv;
        String[] headers = {"ORDER ID","ITEM TYPE","ITEM NAME","ITEM DESCRIPTIN ", "QUANTYTY ", "COST", "ACTION" };
        String[] property = {"orderid", "expenseType", "itemName","description","quantity","cost","edit"};
        ArrayList<Object> model =  fetchLOPReport( type, year, month);

         TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }
  
}
