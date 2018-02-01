/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllersUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import utilities.Load;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class MainUIController implements Initializable {

    @FXML
    private Button patient_registration;
    @FXML
    private Button registered_patients;
    @FXML
    private AnchorPane anchorArea;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    private Label lbl;
    @FXML
    private Label lblUser;

    Load view = new Load();
    @FXML
    private Button RefereeDoctors;
    @FXML
    private Button lpoRequisitionbtn;
    @FXML
    private Button expenses;
    @FXML
    private Button reportPatients;
    @FXML
    private Button expensesReport;
    @FXML
    private Button services;
    @FXML
    private Button others;
    
    @FXML
    private ImageView  mainUiImage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //view.loadView(anchorArea, "/icons/medscreen.png");
        
        // mainUiImage.setImage(new Image("/icons/medscanicon1.jpg"));
       
        
       
        patient_registration.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                view.loadView(anchorArea, "/views/Patients.fxml");
            }
        });
        
        registered_patients.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                view.loadView(anchorArea, "/views/Registered_Patients.fxml");
            }
        });
        RefereeDoctors.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                view.loadView(anchorArea, "/views/setup.fxml");
                
            }
        });
        
        others.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                view.loadView(anchorArea, "/views/Others.fxml");
            }
        });
        
        lpoRequisitionbtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                view.loadView(anchorArea, "/views/OrdersAndExpenses.fxml");
                
            }
        });
        
        expenses.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                view.loadView(anchorArea, "/views/Expense.fxml");
                
            }
        });
       reportPatients.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                view.loadView(anchorArea, "/views/Report_Patients.fxml");
                
            }
        });
       expensesReport.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                view.loadView(anchorArea, "/views/Report_Expenses.fxml");
                
            }
        });
       services.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                view.loadView(anchorArea, "/views/Services.fxml");
            }
        });
//        banks.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                view.loadView(anchorArea, "/views/banks.fxml");
//            }
//        });
//        insurance.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent event) {
//                view.loadView(anchorArea, "/views/insurance.fxml");
//            }
//        });
    }

}
