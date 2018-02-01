/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Timestamp;
import models.ChequePaymentModel;

/**
 *
 * @author young
 */
public class ChequePayments {
private int chequeNo,chequeid,bankid;
private double amount;
private boolean status;
private Timestamp lastUpdated;
private boolean  deleted;
private ChequePaymentModel chequeModel;

public  ChequePayments(){
    chequeModel =new ChequePaymentModel();
}
public boolean save(){
    return chequeModel.insertChequePayment(this);
}

    public int getChequeid() {
        return chequeid;
    }

    public void setChequeid(int chequeid) {
        this.chequeid = chequeid;
    }


    public int getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(int chequeNo) {
        this.chequeNo = chequeNo;
    }

    public int getBankid() {
        return bankid;
    }

    public void setBankid(int bankid) {
        this.bankid = bankid;
    }

   


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean getStatus() {
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
