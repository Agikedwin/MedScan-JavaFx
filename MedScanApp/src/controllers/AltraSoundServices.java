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
import models.AltraSoundServicesModel;

/**
 *
 * @author YoungGucha
 */
public class AltraSoundServices {
  private int serviceid,deleted,status;
    private String serviceType, service_name;
    private double cost;
    private Timestamp datereg;
    private Timestamp lastupdated;
    private static AltraSoundServicesModel altraSound;
    
   public AltraSoundServices() {
        altraSound = new AltraSoundServicesModel();
    }
    public boolean save(){
        return altraSound.insertAltraSoundServices(this);
    }

    public static ArrayList fetchAltraSound() {
        return altraSound.getAllAltraSoundServices();
    }

    public int getServiceid() {
        return serviceid;
    }

    public void setServiceid(int serviceid) {
        this.serviceid = serviceid;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Timestamp getDatereg() {
        return datereg;
    }

    public void setDatereg(Timestamp datereg) {
        this.datereg = datereg;
    }

    public Timestamp getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(Timestamp lastupdated) {
        this.lastupdated = lastupdated;
    }

    public TableView altraSoundTV() {

        TableView tv;
        String[] headers = {"SERVICE ID", "SERVICE NAME", "COST"};
        String[] property = {"serviceid", "service_name", "cost"};
        ArrayList<Object> model = fetchAltraSound();

        TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }

    public static TableView altraTV() {

        TableView tv;
        String[] headers = {"SERVICE ID", "SERVICE NAME", "COST"};
        String[] property = {"serviceid", "service_name", "cost"};
        ArrayList<Object> model = fetchAltraSound();

        TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }

}
