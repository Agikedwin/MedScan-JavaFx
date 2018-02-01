/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Timestamp;
import models.InsurancePaymentsModel;

/**
 *
 * @author young
 */
public class InsurancePayments {
 private String insurenceId;
  private String name;
   private String memberNo;
    private double amount;
     private boolean status;
      private Timestamp lastUpdated;
       private boolean deleted;
       private InsurancePaymentsModel insurancePayments;
       
       public InsurancePayments(){
           insurancePayments=new InsurancePaymentsModel();
       }
       
       public boolean save(){
           return insurancePayments.insertExaminationReport(this);
       }

    public String getInsurenceId() {
        return insurenceId;
    }

    public void setInsurenceId(String insurenceId) {
        this.insurenceId = insurenceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
       
    
}
