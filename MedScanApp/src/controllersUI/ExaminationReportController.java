/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllersUI;

import static com.solutions.entorno.utilities.SystemVariables.DISPLAY_MESSAGE;
import com.solutions.entorno.utilities.dialogs.NotificationType;
import controllers.Doctors;
import controllers.ExaminationReport;
import controllers.PatientsRequest;
import controllers.Services;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author YoungGucha
 */
public class ExaminationReportController implements Initializable {
    OnActionListener onActionListener = new OnActionListener();
    MouseActionListener mouseActionListener = new MouseActionListener();
    OnSelectionListener onSelectionListener = new OnSelectionListener();
    @FXML
    private HBox refereeDoctorid;
    @FXML
    private Button addexamination;
    @FXML
    private Label examinationPatientName;
    @FXML
    private Label examinationDoctor;
    @FXML
    private Label examinationExamination;
    @FXML
    private TextField examinationClinicalInfo;
    @FXML
    private TextArea examinationFindings;
    ExaminationReport examinationReport;
    @FXML
    private TextArea examinationConclusion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       examinationPatientName.setText(PatientsRequest.selectedPatientName);
       examinationDoctor.setText(Doctors.globalDoctorName);
       
       examinationReport=new ExaminationReport();
        
       addexamination.setOnAction(onActionListener);
       examinationPatientName.setText(PatientsRequest.selectedPatientName);
       examinationDoctor.setText(Doctors.getGlobalDoctorName());
        Services serv = new Services();
        examinationExamination.setText(null);
        ArrayList<String> servOffered = serv.fetchServicesOffered(PatientsRequest.globalPatientId, serv.maxServiceCount(PatientsRequest.globalPatientId));
        for (String servce : servOffered) {
            examinationExamination.setText(servce + "\n");
        }

    }    
    
class MouseActionListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {

        }
    }

    class OnActionListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            
            if(addexamination==e.getSource()){
              
             if(examinationPatientName.getText().isEmpty()){
                DISPLAY_MESSAGE.showInfoDialog(addexamination.getScene().getWindow(), "Please select the patient before you add the examination report ", NotificationType.ERROR);
                  
             }
             else if(examinationDoctor.getText()==null){
               DISPLAY_MESSAGE.showInfoDialog(addexamination.getScene().getWindow(), "Please select the doctor before you add the examination report ", NotificationType.ERROR);
                    
             }
             else if(examinationExamination.getText().isEmpty()){
              DISPLAY_MESSAGE.showInfoDialog(addexamination.getScene().getWindow(), "Added service  before you add the examination report ", NotificationType.ERROR);
                  
             }
             else if(examinationClinicalInfo.getText().isEmpty()){
              DISPLAY_MESSAGE.showInfoDialog(addexamination.getScene().getWindow(), "Please provide clinical information ", NotificationType.ERROR);
              
             }
             else if(examinationFindings.getText().isEmpty()){
              DISPLAY_MESSAGE.showInfoDialog(addexamination.getScene().getWindow(), "Please provide  findings ", NotificationType.ERROR);
              
             }
             else if(examinationConclusion.getText().isEmpty()){
              DISPLAY_MESSAGE.showInfoDialog(addexamination.getScene().getWindow(), "Please provide  conclusion ", NotificationType.ERROR);
              
             }else{
                   examinationReport.setPatientId(PatientsRequest.globalPatientId);
               examinationReport.setDoctorId(Doctors.selectedDoctorId);
                examinationReport.setService(examinationExamination.getText());
             examinationReport.setClinicalInformation(examinationClinicalInfo.getText());
             examinationReport.setFindings(examinationFindings.getText());
             examinationReport.setConclusion(examinationConclusion.getText());
                if(examinationReport.save()){
                  DISPLAY_MESSAGE.showInfoDialog(addexamination.getScene().getWindow(), "Examination Report Successfully Saved ", NotificationType.INFORMATION);
               
             }else{
                 DISPLAY_MESSAGE.showInfoDialog(addexamination.getScene().getWindow(), "Failled to save Examination Report ", NotificationType.ERROR);
                
             } 
             }
             
            }
           

    }
    }
    class OnSelectionListener implements EventHandler<Event> {

        @Override
        public void handle(Event e) {

        }

    }

}

