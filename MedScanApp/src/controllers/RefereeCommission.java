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
import models.RefereeCommisionModel;

/**
 *
 * @author young
 */
public class RefereeCommission {
private String  refId;
private String  patientId;
private String  doctorId;
private String  services,patientName,DoctorName;
private double totalCom,totalComAmount;
private Date  date;
private boolean  status;
private Timestamp  lastupdated;
private boolean deleted;
private int count;

private RefereeCommisionModel refereeCommisionModel;

public  RefereeCommission(){
  refereeCommisionModel =new RefereeCommisionModel();  
}

public boolean save(){
   return  refereeCommisionModel.insertRefereeCommision(this);
}

public RefereeCommission fetchService(String doctorid, String patientid){
    return refereeCommisionModel.getRefereeCommision(doctorid,patientid);
}
public ArrayList fetchDoctorCom( String patientid,int count){
   return refereeCommisionModel.getAllRefereeCommision(patientid,count);
}
public ArrayList fetchDoctorComReport(String type,String year,String month){
   return refereeCommisionModel.getAllRefereeCommisionReport(type, year, month);
}

public int maxCount(String patientid){
    return refereeCommisionModel.maxCount(patientid);
}
    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getServices() {
        return services;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public double getTotalCom() {
        return totalCom;
    }

    public void setTotalCom(double totalCom) {
        this.totalCom = totalCom;
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

    public Timestamp getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(Timestamp lastupdate) {
        this.lastupdated = lastupdate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String DoctorName) {
        this.DoctorName = DoctorName;
    }

    public double getTotalComAmount() {
        return totalComAmount;
    }

    public void setTotalComAmount(double totalComAmount) {
        this.totalComAmount = totalComAmount;
    }
    
    
    public   TableView loadDoctorComTV( String patientid,int count) {
        TableView tv;
        String[] headers = {"PATIENT'S NAME","DOCTOR'S NAME", "SERVICE NAME", "COST" };
        String[] property = {"patientName", "DoctorName", "services","totalCom"};
        ArrayList<Object> model =  fetchDoctorCom(patientid,count);

         TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }
    
     public   TableView loadServicesCommissionReport(String type,String year,String month) {
        TableView tv;
        String[] headers = {"DOCTOR'S NAME","PATIENT'S NAME", "SERVICE NAME", "COST", "DATE" };
        String[] property = {"DoctorName", "DoctorName", "services","totalCom","date"};
        ArrayList<Object> model =  fetchDoctorComReport(type,year,month);

         TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }
}
