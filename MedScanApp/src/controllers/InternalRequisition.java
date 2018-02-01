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
import models.InternalRequisitionModel;

/**
 *
 * @author YoungGucha
 */
public class InternalRequisition {
  private int requisitionid,quantity;
  private String expenseType,itemName,description,edit="Edit";
  private double cost;
   private static  double totalLPO;
  private Timestamp dateOrdered , lastupdated;
  private boolean status,deleted;
   public static String action;
    InternalRequisitionModel internalRequisitionModel;
    
    public InternalRequisition(){
      internalRequisitionModel=new InternalRequisitionModel();  
    }
    public boolean save(){
        return  internalRequisitionModel.inserLocalOrderPurchase(this);
    }
public boolean update(){
        return  internalRequisitionModel.updateInternalRequisition(this);
    }
public ArrayList fetchRequisition(){
        return  internalRequisitionModel.getAInternalRequisition();
    }
public ArrayList fetchRequisitionReport(String type,String year,String month){
        return  internalRequisitionModel.getAInternalRequisitionReport( type, year, month);
    }
public double totalRequisition(String type,String year,String month){
    return internalRequisitionModel.getTotalReq( type, year, month);
}
    public int getRequisitionid() {
        return requisitionid;
    }

    public void setRequisitionid(int requisitionid) {
        this.requisitionid = requisitionid;
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

    public static double getTotalLPO() {
        return totalLPO;
    }

    public static void setTotalLPO(double totalLPO) {
        InternalRequisition.totalLPO = totalLPO;
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
        InternalRequisition.action = action;
    }
  
  public   TableView loadInternalReqTV() {
      
        TableView tv;
        String[] headers = {"ORDER ID","ITEM TYPE","ITEM NAME","ITEM DESCRIPTIN ", "QUANTYTY ", "COST", "ACTION" };
        String[] property = {"requisitionid", "expenseType", "itemName","description","quantity","cost","edit"};
        ArrayList<Object> model =  fetchRequisition();

         InternalTableViewRenderer tbl = new InternalTableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }
  public   TableView loadInternalReport(String type,String year,String month) {
      
        TableView tv;
        String[] headers = {"ORDER ID","ITEM TYPE","ITEM NAME","ITEM DESCRIPTIN ", "QUANTYTY ", "COST" };
        String[] property = {"requisitionid", "expenseType", "itemName","description","quantity","cost"};
        ArrayList<Object> model =  fetchRequisitionReport( type, year, month);

         TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }
}
