/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllersUI;

import com.solutions.entorno.utilities.SystemVariables;
import static com.solutions.entorno.utilities.SystemVariables.DISPLAY_MESSAGE;
import com.solutions.entorno.utilities.dialogs.LookupVar;
import com.solutions.entorno.utilities.dialogs.NotificationType;
import controllers.Referees;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AddRefereeController implements Initializable {

    OnActionListener onActionListener = new OnActionListener();
    MouseActionListener mouseActionListener = new MouseActionListener();
    OnSelectionListener onSelectionListener = new OnSelectionListener();
    @FXML
    private TextField refereeName;
    @FXML
    private TextField hospital;
    @FXML
    private Button addReferee;
    Referees referee;
    @FXML
    private AnchorPane anchorParent;
    @FXML
    private TextField contact;
    @FXML
    private Button selectDoctor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        referee = new Referees();
        addReferee.setOnAction(onActionListener);
    }
    
    public void loadDoctors(){
       
    }

    class MouseActionListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {

        }
    }

    class OnActionListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            if (addReferee == e.getSource()) {
                referee.setName(refereeName.getText());
                referee.setHosiptal(hospital.getText());
                referee.setContact(contact.getText());
                if (refereeName.getText().equals("")) {
                    DISPLAY_MESSAGE.showInfoDialog(addReferee.getScene().getWindow(), "Enter  Referee Name", NotificationType.ERROR);
                } else if (hospital.getText().equals("")) {
                    DISPLAY_MESSAGE.showInfoDialog(addReferee.getScene().getWindow(), "Enter Hospital Name", NotificationType.ERROR);
                }
                else if(contact.getText().isEmpty()){
                    DISPLAY_MESSAGE.showInfoDialog(addReferee.getScene().getWindow(), "Please enter contact", NotificationType.ERROR);
                   
                }
                else {
                    if (referee.save()) {    
                        
                        Stage stage = (Stage) addReferee.getScene().getWindow();
                        stage.close();
                        DISPLAY_MESSAGE.showInfoDialog(addReferee.getScene().getWindow(), "Successfully Saved Referee Details", NotificationType.INFORMATION);
                       ((Node)(e.getSource())).getScene().getWindow().hide(); 
                    } else {
                        DISPLAY_MESSAGE.showInfoDialog(addReferee.getScene().getWindow(), "Failed to save Referee Details", NotificationType.ERROR);
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
