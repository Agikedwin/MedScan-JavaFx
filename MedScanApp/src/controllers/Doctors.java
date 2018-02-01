/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static com.solutions.entorno.utilities.SystemVariables.resultSet;
import static com.solutions.entorno.utilities.SystemVariables.statement;
import com.solutions.entorno.utilities.TableViewRenderer;
import static database.MedscanVariables.TBL_DOCTORS;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javafx.scene.control.TableView;
import models.DoctorsModel;
import static models.DoctorsModel.doctor;

/**
 *
 * @author young
 */
public class Doctors {
   private String name;
   private int doctorid;
   private String  service;
   private double commission;
   private boolean status,deleted;
   private Timestamp lastUpdated;
   public static String globalDoctorName;
   
   public static  DoctorsModel doctorsModel;
   
     public static String selectedDoctorId;
   public  Doctors(){
     doctorsModel= new  DoctorsModel(); 
   }
public boolean save(){
    return doctorsModel.insertDoctors(this);
}
public static ArrayList fetchDoctors(){
   
   return doctorsModel.getAllDoctor();
}
    public int getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(int doctorid) {
        this.doctorid = doctorid;
    }

    public static String getSelectedDoctorId() {
        return selectedDoctorId;
    }

    public static void setSelectedDoctorId(String selectedDoctorId) {
        Doctors.selectedDoctorId = selectedDoctorId;
    }

    public static String getGlobalDoctorName() {
        return globalDoctorName;
    }

    public static void setGlobalDoctorName(String globalDoctorName) {
        Doctors.globalDoctorName = globalDoctorName;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public double getCommission() {
        return commission;
    }

   

    public void setCommission(double commission) {
        this.commission = commission;
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
    public  static ArrayList getAllDoctor1() {
        
        ArrayList<Doctors> doctors = new ArrayList<>();
       
        try {
             resultSet=statement.executeQuery("SELECT `doctor_id`, `name`, `hosiptal`,"
                     + " `contact`, `status`, `lastupdated`, `deleted` FROM "+TBL_DOCTORS+" ");
            while (resultSet.next()) {
                
                doctor = new Doctors();
              
                doctor.setDoctorid(resultSet.getInt("doctor_id"));
                doctor.setName(resultSet.getString("name"));
                doctor.setService(resultSet.getString("hosiptal"));
                 doctor.setCommission(resultSet.getDouble("contact"));
                doctor.setStatus(resultSet.getBoolean("status"));
                doctor.setLastUpdated(resultSet.getTimestamp("lastupdated"));
                doctor.setDeleted(resultSet.getBoolean("deleted"));
                doctors.add(doctor);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return doctors;
    }

    public  static  TableView loadDoctorTV() {
        System.out.println(" at tv");
        TableView tv;
        String[] headers = {"DOCTOR ID", "DOCTOR'S NAME", "HOSIPTAL" };
        String[] property = {"doctorid", "name", "service"};
        ArrayList<Object> model =  getAllDoctor1();

         TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }
   
   
    
}
