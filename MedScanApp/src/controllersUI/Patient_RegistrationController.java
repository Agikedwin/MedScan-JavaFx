/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllersUI;

import static com.solutions.entorno.utilities.SystemVariables.DISPLAY_MESSAGE;
import com.solutions.entorno.utilities.dialogs.NotificationType;
import com.sun.corba.se.spi.presentation.rmi.StubAdapter;
import controllers.PatientsRequest;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utilities.Validation;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class Patient_RegistrationController implements Initializable {
    @FXML
    private TextField patientid;
    @FXML
    private TextField patientName;
    @FXML
    private TextField telephoneNo;
    @FXML
    private TextField age;
    @FXML
    private TextField address;
    @FXML
    private DatePicker lmp;
    @FXML
    private ComboBox sex;
    @FXML
    private DatePicker treatementDate;
    @FXML
    private Button save;
    @FXML
    private Button reset;
    private String dateTreated=null;
      private String lpmdate=null;
      
      PatientsRequest patientsReq;
    

    /**
     * Initializes the controller class.
     */
    OnActionListener onActionListener = new OnActionListener();
    MouseActionListener mouseActionListener = new MouseActionListener();
    OnSelectionListener onSelectionListener = new OnSelectionListener();
    
     PatientsRequest requests;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       requests=new PatientsRequest();
         sex.getItems().addAll("MALE", "FEMALE");
        sex.getSelectionModel().selectFirst();
        save.setOnAction(onActionListener);
        reset.setOnAction(onActionListener);
        save.setText(PatientsRequest.getAction());
        if(PatientsRequest.getAction()=="EDIT"){
          editPatients();  
        }else if(PatientsRequest.getAction()=="SAVE"){
          System.out.println("request "+requests.nextPatientId());
          patientid.setText("PNO"+requests.nextPatientId());
        }
        
        
        
        
        treatementDate.setOnAction(onActionListener);
          
    }   
    private void validateFields() {
    
  
    }
    
    private void loadPatients(){
       
    }
    public void reset(){
    patientName.setText(null);
   //  patientid.setText(null);
     treatementDate.setValue(null);
      address.setText(null);         
       lmp.setValue(null);
        age.setText(null);     
        telephoneNo.setText(null);       
         sex.setSelectionModel(null);
    }
    private void editPatients(){
        String datereg = PatientsRequest.getRegdateEdit();
        String datelmp = PatientsRequest.getLmpEdit();
        LocalDate localDatereg = LocalDate.parse(datereg);
         //LocalDate localDateLMP = LocalDate.parse(datelmp);
        patientName.setText(PatientsRequest.getNameEdit());
        patientid.setText(""+"PNO"+PatientsRequest.getPatientidEdit());
        treatementDate.setValue(localDatereg);
        address.setText(PatientsRequest.getAddressEdit());
      // lmp.setValue(localDateLMP);
        age.setText(PatientsRequest.getAgeEdit());
        telephoneNo.setText(PatientsRequest.getPhoneNoEdit());
        

    }
    class OnActionListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            if(treatementDate==e.getSource()|| lmp==e.getSource()){
              dateTreated= "" + (LocalDate) treatementDate.getValue();  
              lpmdate="" + (LocalDate) treatementDate.getValue(); 
            }
           if(save==e.getSource()){
                 requests =new PatientsRequest();
            
              requests.setName(patientName.getText());
                requests.setPatientid(patientid.getText());
                requests.setRegdate(dateTreated);
                requests.setAddress(address.getText());
                requests.setLmp(lpmdate);
                requests.setAge(age.getText());
                requests.setPhoneNo(telephoneNo.getText());
               requests.setGender(sex.getSelectionModel().getSelectedItem().toString()); 
               //validate fields
              
               if(patientName.getText().isEmpty()){
                DISPLAY_MESSAGE.showInfoDialog(save.getScene().getWindow(), "Please Enter  Patient's Name", NotificationType.ERROR);
                }
               else if(telephoneNo.getText().isEmpty()){
                   DISPLAY_MESSAGE.showInfoDialog(save.getScene().getWindow(), "Please Enter  Patient's Phone Number", NotificationType.ERROR);
                
               }
                
               else if(treatementDate.getValue()==null || dateTreated==null){
                  DISPLAY_MESSAGE.showInfoDialog(save.getScene().getWindow(), "Please Select Treatement Date  ", NotificationType.ERROR);
                  
               }
               else if(age.getText().isEmpty()){
                  DISPLAY_MESSAGE.showInfoDialog(save.getScene().getWindow(), "Please Enter  Patient's Age ", NotificationType.ERROR);
                  
               }
               else if(sex.getSelectionModel().getSelectedItem()==null){
                  DISPLAY_MESSAGE.showInfoDialog(save.getScene().getWindow(), "Please Select  Patient's  Gender ", NotificationType.ERROR);
                  
               }
               else if(address.getText().isEmpty()){
                  DISPLAY_MESSAGE.showInfoDialog(save.getScene().getWindow(), "Please Enter  Patient's Address ", NotificationType.ERROR);
                  
               }else{
                     if(PatientsRequest.getAction()=="SAVE"){
                         
                 
               if(requests.save()){
                   Stage stage = (Stage) save.getScene().getWindow();
                        stage.close();
                   DISPLAY_MESSAGE.showInfoDialog(save.getScene().getWindow(), "Successfully Saved Patients Details", NotificationType.INFORMATION);
              ((Node)(e.getSource())).getScene().getWindow().hide(); 
               }
               else{
                   DISPLAY_MESSAGE.showInfoDialog(save.getScene().getWindow(), "Failed to save Patient Details", NotificationType.ERROR);
               }  
              }
              else if(PatientsRequest.getAction()=="EDIT") {
                 requests.setPatientid(PatientsRequest.globalPatientId);
               if(requests.update()){
                   Stage stage = (Stage) save.getScene().getWindow();
                        stage.close();
                   DISPLAY_MESSAGE.showInfoDialog(save.getScene().getWindow(), "Successfully Edited Patient Details", NotificationType.INFORMATION);
              ((Node)(e.getSource())).getScene().getWindow().hide(); 
               }
               else{
                   DISPLAY_MESSAGE.showInfoDialog(save.getScene().getWindow(), "Failed to Edit Patient Details", NotificationType.ERROR);
               }   
              }
               }
            }
           if(reset==e.getSource()){
            reset();
            } 
            
            
        }
    }

    class MouseActionListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {
            
            
            
        }
    }

    class OnSelectionListener implements EventHandler<Event> {

        @Override
        public void handle(Event e) {
          
        }

    }

    private void loadTabs() {
        
    }

    
    
}
