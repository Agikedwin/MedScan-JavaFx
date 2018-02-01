/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllersUI;

import com.solutions.entorno.utilities.LoadUI;
import com.solutions.entorno.utilities.SystemVariables;
import static com.solutions.entorno.utilities.SystemVariables.UILoader;
import controllers.PatientsRequest;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class Registered_PatientsController implements Initializable {

    MouseActionListener mouseActionListener = new MouseActionListener();
    OnSelectionListener onSelectionListener = new OnSelectionListener();
    @FXML
    private TextField searchPatient;
    @FXML
    private AnchorPane anchorPatientsWrapper;
    @FXML
    private TableView<PatientsRequest> patientsTV;
    
    PatientsRequest patientsRequests;
    LoadUI view;
    @FXML
    private AnchorPane anchorParent;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        patientsRequests = new PatientsRequest();
        
        patientsTV = patientsRequests.patientDetails();
        patientsTV.setOnMouseClicked(mouseActionListener);
        patientsTV.getSelectionModel().setCellSelectionEnabled(true);
        UILoader.loadTable(anchorPatientsWrapper, patientsTV);
    } 
    
    class MouseActionListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {
            if(patientsTV == e.getSource()){
                if (patientsTV.getSelectionModel().getSelectedCells().get(0).getColumn() == 9) {
                   
                    PatientsRequest.setGlobalPatientId(patientsTV.getSelectionModel().getSelectedItem().getPatientid());
                    PatientsRequest.setSelectedPatientName(patientsTV.getSelectionModel().getSelectedItem().getName());

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
            }

        }
    }
    
    class OnSelectionListener implements EventHandler<Event>{

        @Override
        public void handle(Event e) {
        
        }
        
        
    }
    
}
