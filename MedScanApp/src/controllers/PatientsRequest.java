/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.solutions.entorno.utilities.InternalTableViewRenderer;
import com.solutions.entorno.utilities.TableViewRenderer;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.scene.control.TableView;
import models.PatientsRequetModel;

/**
 *
 * @author young
 */
public class PatientsRequest {

    private String patientid;
    private String name, age, phoneNo;
    private String gender;
    private String address;
    private String dob;
    private String regdate, lmp;//to be changed to date type
    private boolean status;
    private Timestamp lastUpdated;
    private boolean deleted;
    private String edit = "Edit";
    private String delete = "Delete";
    private String view = "View";
    private String selectedPatientId;

    public static String globalPatientId;
    public static String selectedPatientName;

    public PatientsRequetModel requetModel;

    private static String patientidEdit;
    private static String nameEdit, ageEdit, phoneNoEdit;
    private static String genderEdit;
    private static String addressEdit;
    private static String dobEdit;
    private static String regdateEdit, lmpEdit;//to be changed to date type

    private static String action;

    public PatientsRequest() {
        requetModel = new PatientsRequetModel();

    }

    public   ArrayList readPatients() {
        return requetModel.getAllPatientsRequest();
    }

    public boolean save() {
        return requetModel.insertPatientsRequet(this);
    }

    public boolean update() {
        return requetModel.updatePatientsRequest(this);
    }

    public boolean delete() {
        return requetModel.deletePatientsRequest(this);
    }
 public int nextPatientId() {
        return requetModel.nextPatientid();
    }
    public static String getGlobalPatientId() {
        return globalPatientId;
    }

    public static void setGlobalPatientId(String globalPatientId) {
        PatientsRequest.globalPatientId = globalPatientId;
    }

    public static String getSelectedPatientName() {
        return selectedPatientName;
    }

    public static void setSelectedPatientName(String selectedPatientName) {
        PatientsRequest.selectedPatientName = selectedPatientName;
    }

    public String getPatientid() {
        return patientid;
    }

    public void setPatientid(String patientid) {
        this.patientid = patientid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getLmp() {
        return lmp;
    }

    public void setLmp(String lmp) {
        this.lmp = lmp;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
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

    public String getEdit() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit = edit;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    //edit getters and setters
    public static String getPatientidEdit() {
        return patientidEdit;
    }

    public static void setPatientidEdit(String patientidEdit) {
        PatientsRequest.patientidEdit = patientidEdit;
    }

    public static String getNameEdit() {
        return nameEdit;
    }

    public static void setNameEdit(String nameEdit) {
        PatientsRequest.nameEdit = nameEdit;
    }

    public static String getAgeEdit() {
        return ageEdit;
    }

    public static void setAgeEdit(String ageEdit) {
        PatientsRequest.ageEdit = ageEdit;
    }

    public static String getPhoneNoEdit() {
        return phoneNoEdit;
    }

    public static void setPhoneNoEdit(String phoneNoEdit) {
        PatientsRequest.phoneNoEdit = phoneNoEdit;
    }

    public static String getGenderEdit() {
        return genderEdit;
    }

    public static void setGenderEdit(String genderEdit) {
        PatientsRequest.genderEdit = genderEdit;
    }

    public static String getAddressEdit() {
        return addressEdit;
    }

    public static void setAddressEdit(String addressEdit) {
        PatientsRequest.addressEdit = addressEdit;
    }

    public static String getDobEdit() {
        return dobEdit;
    }

    public static void setDobEdit(String dobEdit) {
        PatientsRequest.dobEdit = dobEdit;
    }

    public static String getRegdateEdit() {
        return regdateEdit;
    }

    public static void setRegdateEdit(String regdateEdit) {
        PatientsRequest.regdateEdit = regdateEdit;
    }

    public static String getLmpEdit() {
        return lmpEdit;
    }

    public static void setLmpEdit(String lmpEdit) {
        PatientsRequest.lmpEdit = lmpEdit;
    }

    public static String getAction() {
        return action;
    }

    public static void setAction(String action) {
        PatientsRequest.action = action;
    }

    public TableView patientDetails() {

        TableView tv;
        String[] headers = {"PATIENT ID", "NAME", "GENDER", "AGE", "PHOEN NO", "DATE TREATED", "ADDRESS", "EDIT", "DELETE", "VIEW"};
        String[] property = {"patientid", "name", "gender", "age", "phoneNo", "regdate", "address", "edit", "delete", "view"};
        ArrayList<Object> model = readPatients();

        InternalTableViewRenderer tbl = new InternalTableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }

}
