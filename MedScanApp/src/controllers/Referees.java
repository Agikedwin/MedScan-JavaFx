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
import models.RefereesModel;

/**
 *
 * @author young
 */
public class Referees {
 private String refereeId;
 private String  name;
 private String hosiptal;
  private String contact;
 private String date;
 private boolean status;
 private Timestamp lastUpdated;
 private boolean deleted;
 private static RefereesModel refereesModel;
 
 public  Referees(){
     refereesModel =new RefereesModel();
 }
 public boolean save(){
     return refereesModel.insertReferees(this);
 }
public  static  ArrayList fetchDoctors(){
 return refereesModel.getAllReferees();
}
    public String getRefereeId() {
        return refereeId;
    }

    public void setRefereeId(String refereeId) {
        this.refereeId = refereeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHosiptal() {
        return hosiptal;
    }

    public void setHosiptal(String hosiptal) {
        this.hosiptal = hosiptal;
    }

    public String getDate() {
        return date;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setDate(String date) {
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
 
 public  static TableView loadDoctorsTV() {

        TableView tv;
         String[] headers = {"DOCTOR'S ID", "DOCTOR'S NAME", "CONTACT" };
        String[] property = {"refereeId",  "name","contact"};
        ArrayList<Object> model = fetchDoctors();

        TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }
  public TableView refereeTV() {

        TableView tv;
        String[] headers = {"REFEREE", "HOSPITAL NAME" };
        String[] property = {"name", "hospital"};
        ArrayList<Object> model = fetchDoctors();

         TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }
}
