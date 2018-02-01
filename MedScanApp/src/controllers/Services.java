/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.solutions.entorno.utilities.TableViewRenderer;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import javafx.scene.control.TableView;
import models.ServiceModel;

/**
 *
 * @author young
 */
public class Services {
private  int serviceId;
private  String patientId;
private  String serviceName;
private  String serviceType;
private  String clinicalInfo;
private  double cost,totalServiceCost;
private  Date date;
private  boolean status;
private  Timestamp lastUpdated;
private  boolean deleted;
private int count;

  private String patientName;

private ServiceModel serviceModel;

public  Services(){
   serviceModel = new ServiceModel();
}
  public  boolean save(){
      return serviceModel.insertServices(this);
  }
  
  public ArrayList fetchServices(int id){
      return serviceModel.getAllServices();
  }
   public ArrayList fetchServicesReport(String serviceType,String year,String month){
      return serviceModel.getAllServicesReport(serviceType,year,month);
  }
    
  public int maxServiceCount(String Patientid){
      return serviceModel.getServiceCount(Patientid);
  }
  
    /**
     *
     * @param id
     * @return
     */
    public ArrayList fetchServicesOffered( String patientid,int count){
      return serviceModel.getServiceComOffered(patientid,count);
  }
  
  public double getTotalCom(String patientid,int maxCount){
     
    return serviceModel.getServiceCom(patientid,maxCount);
  }
  public double getTotalCost(String patientid){
     
    return serviceModel.getServiceCost(patientid);
  }
  public double getTotalServiceCost(String serviceType,String year,String month){
      return serviceModel.getTotalSevicesCost(serviceType,year,month);
  }
    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

   

    public String getClinicalInfo() {
        return clinicalInfo;
    }

    public void setClinicalInfo(String clinicalInfo) {
        this.clinicalInfo = clinicalInfo;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    //report getters and setters

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    
  
     public   TableView loadServices(int id) {

        TableView tv;
        String[] headers = {"SERVICE ID","SERVICE TYPE", "SERVICE NAME", "COST" };
        String[] property = {"serviceId", "serviceType", "serviceName","cost"};
        ArrayList<Object> model =  fetchServices(id);

         TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }
     
      public   TableView loadServicesReport(String serviceType,String year,String month) {

        TableView tv;
        String[] headers = {"PATIENT NAME","SERVICE TYPE", "SERVICE NAME", "COST" };
        String[] property = {"patientName", "serviceType", "serviceName","cost"};
        ArrayList<Object> model =  fetchServicesReport(serviceType,year,month);

         TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }
}
