/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Date;
import java.sql.Timestamp;
import models.RefereeCommisionModel;

/**
 *
 * @author young
 */
public class ServicesCommision {
private String commissionId;
private String doctorId;

private String servicesName;
private double commisssionAmount;
private Date date;
private boolean status;
private Timestamp lastUpdated;
private boolean deleted;
private RefereeCommisionModel refereeCommisionModel;

public  ServicesCommision(){
    refereeCommisionModel=new RefereeCommisionModel();
}

//public boolean save(){
//   // return refereeCommisionModel.insertRefereeCommision(this);
//}
    public String getCommissionId() {
        return commissionId;
    }

    public void setCommissionId(String commissionId) {
        this.commissionId = commissionId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getServicesName() {
        return servicesName;
    }

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }

    public double getCommisssionAmount() {
        return commisssionAmount;
    }

    public void setCommisssionAmount(double commisssionAmount) {
        this.commisssionAmount = commisssionAmount;
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
    
}
