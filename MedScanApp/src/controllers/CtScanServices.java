/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.solutions.entorno.utilities.TableViewRenderer;
import static controllers.AltraSoundServices.fetchAltraSound;
import java.sql.Timestamp;
import java.util.ArrayList;
import javafx.scene.control.TableView;
import models.CTScanServicesModel;

/**
 *
 * @author YoungGucha
 */
public class CtScanServices {
    private int serviceid,deleted,status;
    private String serviceType,service_name;
    private double cost;
    private Timestamp datereg;
    private Timestamp lastupdated;
    
    private static CTScanServicesModel CtScanServices;
    
    public CtScanServices(){
        CtScanServices=new CTScanServicesModel();
    }
    public boolean save(){
        return CtScanServices.insertCtScanServices(this);
    }
    private static ArrayList fetchCtscan(){
        return CtScanServices.getAllCtScanServices();
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
    
    public static  TableView ctScanTV() {

        TableView tv;
        String[] headers = {"SERVICE ID", "SERVICE NAME", "COST" };
        String[] property = {"serviceid", "service_name", "cost"};
        ArrayList<Object> model = fetchCtscan();

         TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }
    public TableView loadctScanTV() {

        TableView tv;
        String[] headers = {"SERVICE ID", "SERVICE NAME", "COST" };
        String[] property = {"serviceid", "service_name", "cost"};
        ArrayList<Object> model = fetchCtscan();

         TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }
}
