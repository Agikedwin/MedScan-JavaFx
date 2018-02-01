/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.pwp.integrator.controllers;

import com.solutions.entorno.utilities.MessagesUtil;
import com.solutions.pwp.integrator.models.InstitutionalDetails;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author shaddie
 */
public class InstitutionDetailsController implements Initializable {

    @FXML
    private TextField krapin;
    @FXML
    private TextField etrNumber;
    @FXML
    private TextField name;
    @FXML
    private TextField town;
    @FXML
    private TextField address;
    @FXML
    private TextField phoneno;
    @FXML
    private TextField email;
    @FXML
    private TextField slogan;
    @FXML
    private Button saveBusinessDetails;
    @FXML
    private Button clearBusinessFields;

    InstitutionalDetails institutionDetails = null; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        saveBusinessDetails.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String bname =name.getText();
                String btown = town.getText();
                String postalAddress = address.getText();
                String bslogan  = slogan.getText();
                String phoneNo = phoneno.getText();
                String bemail =email.getText();
                String karPin = krapin.getText();
                String betrNumber = etrNumber.getText();
                if(bname.equals("")){
                MessagesUtil.displayMessage("Empty Field Business Name", "Please enter Business Name", 1, null);
                }
                else  if(btown.equals("")){
                MessagesUtil.displayMessage("Empty Field Town", "Please enter Town", 1, null);
                }
                 else  if(phoneNo.equals("")){
                MessagesUtil.displayMessage("Empty Field Phone", "Please enter Town", 1, null);
                }
                 else{
                     if(institutionDetails == null){
                     InstitutionalDetails institution =  new InstitutionalDetails();
                     institution.setName(bname);
                     institution.setTown(btown);
                     institution.setPostalAddress(postalAddress);
                     institution.setSlogan(bslogan);
                     institution.setPhoneNo(phoneNo);
                     institution.setEmail(bemail);
                     institution.setKarPin(karPin);
                     institution.setEtrNumber(betrNumber);
                     institution.setStatus("946003f97ccc52d5d3b54ac0ec31bbfc");
                     institution.setExpkey("946003f97ccc52d5d3b54ac0ec31bbfc");
                     institution.setExptym("946003f97ccc52d5d3b54ac0ec31bbfc");
                     institution.insertInstitutionDetails();
                     }
                     else{
                     institutionDetails.setName(bname);
                     institutionDetails.setTown(btown);
                     institutionDetails.setPostalAddress(postalAddress);
                     institutionDetails.setSlogan(bslogan);
                     institutionDetails.setPhoneNo(phoneNo);
                     institutionDetails.setEmail(bemail);
                     institutionDetails.setKarPin(karPin);
                     institutionDetails.setEtrNumber(betrNumber);
                     institutionDetails.updateInstitutionDetails();
                     }
                     reset();
                 }
            }
        });
    }
    public void populateDetails(InstitutionalDetails inst){
        institutionDetails = inst;
         name.setText(institutionDetails.getName());
        town.setText(institutionDetails.getTown());
        address.setText(institutionDetails.getPostalAddress());
        slogan.setText(institutionDetails.getSlogan());
        phoneno.setText(institutionDetails.getPhoneNo());
        email.setText(institutionDetails.getEmail());
        krapin.setText(institutionDetails.getKarPin());
        etrNumber.setText(institutionDetails.getEtrNumber());
    }
    private void reset() {
        name.setText(null);
        town.setText(null);
        address.setText(null);
        slogan.setText(null);
        phoneno.setText(null);
        email.setText(null);
        krapin.setText(null);
        etrNumber.setText(null);
    }
  
}
