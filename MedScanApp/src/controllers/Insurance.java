/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.solutions.entorno.utilities.TableViewRenderer;
import java.sql.Timestamp;
import java.util.ArrayList;
import javafx.scene.control.TableView;
import models.InsuranceModel;

/**
 *
 * @author YoungGucha
 */
public class Insurance {
    private int insuranceId;
    private String insuranceName;
    private boolean status;
    private Timestamp lastUpdated;
    private boolean deleted;
   public static InsuranceModel  insuranceModel;
    
    public Insurance(){
      insuranceModel=new InsuranceModel();
    }
    
    public boolean save(){
     return insuranceModel.insertInsurance(this);
    }
    public static ArrayList fetchInsurance(){
        return insuranceModel.getAllInsurance();
    }
    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public boolean isStatus() {
        return status;
    }

    public int getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(int insuranceId) {
        this.insuranceId = insuranceId;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
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
   public  static TableView InsuranceTV() {
 
        TableView tv;
        String[] headers = {"INSURANCE ID","INSURANCE NAME", };
        String[] property = {"insuranceId", "insuranceName",};
        ArrayList<Object> model = fetchInsurance();

        TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }
}
