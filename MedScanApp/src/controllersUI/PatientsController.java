/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllersUI;

import com.solutions.entorno.utilities.LoadUI;
import static com.solutions.entorno.utilities.SystemVariables.DISPLAY_MESSAGE;
import static com.solutions.entorno.utilities.SystemVariables.UILoader;
import com.solutions.entorno.utilities.dialogs.NotificationType;
import controllers.PatientsRequest;
import controllers.Services;
//import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class PatientsController implements Initializable {

    LoadUI view;
    @FXML
    private Button addPatient;
    @FXML
    private AnchorPane anchorPatientDetails;
    @FXML
    private TableView<PatientsRequest> tblPatients;
    PatientsRequest patientsRequests;
    public static String patientLoggedId;

    MouseActionListener mouseActionListener = new MouseActionListener();
    OnSelectionListener onSelectionListener = new OnSelectionListener();
    @FXML
    private AnchorPane anchorParent;
    
     private ObservableList<PatientsRequest> masterDataPatientsReg;
    @FXML
    private TextField searchPatients;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        addPatient.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                PatientsRequest.setAction("SAVE");
                Stage stage = new Stage();
                Parent root = null;
                String css = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/views/Patient_Registration.fxml"));
                    URL url = this.getClass().getResource("/css/JMetroLightTheme.css");
                    if (url == null) {
                        System.out.println("Resource not found. Aborting.");
                        System.exit(-1);
                    }
                    css = url.toExternalForm();
                } catch (IOException ex) {
                    //  Logger.getLogger(views.PatientsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(root);
                scene.getStylesheets().add(css);
                stage.setScene(scene);
                stage.setTitle("Patients Reqistration");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(
                        ((Node) event.getSource()).getScene().getWindow());
                stage.show();
            }
        });
        patientsRequests = new PatientsRequest();

        loadPatients();
    }

    private void loadPatients() {
        this.masterDataPatientsReg = FXCollections.observableArrayList(patientsRequests.readPatients());
        tblPatients = patientsRequests.patientDetails();
        tblPatients.setOnMouseClicked(mouseActionListener);
        tblPatients.getSelectionModel().setCellSelectionEnabled(true);
        UILoader.loadTable(anchorPatientDetails, tblPatients);
        
        //search begins here 
         FilteredList<PatientsRequest> filteredPatientsData = new FilteredList<>(masterDataPatientsReg, p -> true);
        searchPatients.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredPatientsData.setPredicate(searchModel -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (searchModel.getPatientid().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (searchModel.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                return false;
            });
        });
        SortedList<PatientsRequest> sortedPatientsData = new SortedList<>(filteredPatientsData);
        sortedPatientsData.comparatorProperty().bind(tblPatients.comparatorProperty());
        tblPatients.setItems(sortedPatientsData);
    }

    class MouseActionListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {

            if (tblPatients == e.getSource()) {
                if (tblPatients.getSelectionModel().getSelectedCells().get(0).getColumn() == 9) {

                    PatientsRequest.setGlobalPatientId(tblPatients.getSelectionModel().getSelectedItem().getPatientid());
                    PatientsRequest.setSelectedPatientName(tblPatients.getSelectionModel().getSelectedItem().getName());

                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/views/Patients_Record.fxml"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        Logger.getLogger(PatientsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    view = new LoadUI();
                    view.loadView((AnchorPane) anchorParent.getParent(), (AnchorPane) root);
                }
                
                if(tblPatients.getSelectionModel().getSelectedCells().get(0).getColumn() == 7){
                    patientsRequests = new PatientsRequest();
                    patientsRequests = tblPatients.getSelectionModel().getSelectedItem();
                    patientsRequests.setPatientidEdit(patientsRequests.getPatientid());
                    patientsRequests.setNameEdit(patientsRequests.getName());
                    patientsRequests.setLmpEdit(patientsRequests.getLmp());
                    patientsRequests.setPhoneNoEdit(patientsRequests.getPhoneNo());
                    patientsRequests.setRegdateEdit(patientsRequests.getRegdate());
                    patientsRequests.setAgeEdit(patientsRequests.getAge());
                    patientsRequests.setGenderEdit(patientsRequests.getGender());
                    patientsRequests.setAddressEdit(patientsRequests.getAddress());
                    PatientsRequest.setGlobalPatientId(patientsRequests.getPatientid());
                   PatientsRequest.setAction("EDIT");
                    
                   
                  Stage stage = new Stage();
                Parent root = null;
                String css = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/views/Patient_Registration.fxml"));
                    URL url = this.getClass().getResource("/css/JMetroLightTheme.css");
                    if (url == null) {
                        System.out.println("Resource not found. Aborting.");
                        System.exit(-1);
                    }
                    css = url.toExternalForm();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    //  Logger.getLogger(views.PatientsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(root);
                scene.getStylesheets().add(css);
                stage.setScene(scene);
                stage.setTitle("Edit Patients ");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(
                 ((Node) e.getSource()).getScene().getWindow());
                stage.show();
                }
                
                if(tblPatients.getSelectionModel().getSelectedCells().get(0).getColumn() == 8){
                    patientsRequests = tblPatients.getSelectionModel().getSelectedItem();
                    patientsRequests.setPatientidEdit(patientsRequests.getPatientid());
                 boolean con= DISPLAY_MESSAGE.showConfirmDialog(tblPatients.getScene().getWindow(), "Are you sure want to delete this record ??");
                  
                 if(con){
                     patientsRequests = new PatientsRequest();
                     if(patientsRequests.delete()){
                     DISPLAY_MESSAGE.showInfoDialog(tblPatients.getScene().getWindow(), "Record Successfully deleted  ", NotificationType.INFORMATION);
             
                     }
                   }
                }

        }
    }
    }

    class OnSelectionListener implements EventHandler<Event> {

        @Override
        public void handle(Event e) {
            if (tblPatients == e.getSource()) {

                if (tblPatients.getSelectionModel().getSelectedCells().get(0).getColumn() == 9) {
                    Services ser = new Services();
                    ser.setPatientId(tblPatients.getSelectionModel().getSelectedItem().getPatientid());
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/views/Registered_Patients.fxml"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        Logger.getLogger(PatientsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    view = new LoadUI();
                    view.loadView((AnchorPane) anchorParent.getParent(), (AnchorPane) root);
                }

            }
        }

    }

}
