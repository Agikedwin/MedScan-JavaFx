/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static com.solutions.entorno.utilities.SystemVariables.resultSet;
import static com.solutions.entorno.utilities.SystemVariables.statement;
import com.solutions.entorno.utilities.TableViewRenderer;
import static database.MedscanVariables.TBL_XRAY;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javafx.scene.control.TableView;
import models.XRayServiceModel;

/**
 *
 * @author YoungGucha
 */

public class XRayServices {
    private int serviceid,deleted,status;
    private String serviceType,service_name;
    private double cost;
    private Timestamp datereg;
    private Timestamp lastupdated;
    
    private static XRayServiceModel xRay;
    
    public XRayServices(){
      xRay =new  XRayServiceModel(); 
    }
    
//    public static ArrayList fetchXrays(int id){
//        return xRay.getXRayServices(id);
//    }
    public boolean save(){
        return xRay.insertXRayServices(this);
    }
    public ArrayList getXRay(int id) {

        return xRay.getXRayServices(id);
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

    public TableView loadXRayTV(int id) {

        TableView tv;
        String[] headers = {"SERVICE ID", "SERVICE NAME", "COMMISSION"};
        String[] property = {"serviceid", "service_name", "cost"};
        ArrayList<Object> model = getXRay(id);

        TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }

    public static TableView XRayTV() {

        TableView tv;
        String[] headers = {"SERVICE ID", "SERVICE NAME", "COMMISSION"};
        String[] property = {"serviceid", "service_name", "cost"};
        ArrayList<Object> model = getAllXRay();

        TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }

    public static ArrayList getAllXRay() {
        ArrayList<XRayServices> xRayServices = new ArrayList<>();
        XRayServices xRayz;
        try {
            resultSet = statement.executeQuery(" select serviceid, `service_name`, `cost`, `datereg`, `lastupdated`, `deleted`, `status` "
                    + " FROM  " + TBL_XRAY + " ");
            while (resultSet.next()) {
                xRayz = new XRayServices();
                xRayz.setServiceid(resultSet.getInt("serviceid"));
                xRayz.setService_name(resultSet.getString("service_name"));
                xRayz.setCost(resultSet.getDouble("cost"));
                xRayz.setDatereg(resultSet.getTimestamp("datereg"));
                xRayz.setStatus(resultSet.getInt("status"));
                xRayz.setLastupdated(resultSet.getTimestamp("lastupdated"));
                xRayz.setDeleted(resultSet.getInt("deleted"));
                xRayServices.add(xRayz);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return xRayServices;
    }
}
