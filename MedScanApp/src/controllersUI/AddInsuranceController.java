/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllersUI;

import static com.solutions.entorno.utilities.SystemVariables.DISPLAY_MESSAGE;
import com.solutions.entorno.utilities.dialogs.NotificationType;
import controllers.Insurance;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author YoungGucha
 */
public class AddInsuranceController implements Initializable {
     OnActionListener onActionListener = new OnActionListener();
    MouseActionListener mouseActionListener = new MouseActionListener();
    OnSelectionListener onSelectionListener = new OnSelectionListener();
    
    @FXML
    private AnchorPane anchorParent;
    
    @FXML
    private TextField insuranceName;
    @FXML
    private Button addInsurance;
    
    Insurance insurance;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       insurance=new Insurance();
       
       addInsurance.setOnAction(onActionListener);
    }    
    
    public void loadInsurance(){
        
    }
  class MouseActionListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {

        }
    }

    class OnActionListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            if(addInsurance==e.getSource()){
                insurance.setInsuranceName(insuranceName.getText());
                if(insuranceName.getText().isEmpty()){
                  DISPLAY_MESSAGE.showInfoDialog(addInsurance.getScene().getWindow(), "Please enter insurance name ", NotificationType.ERROR);
               }else{
                    if(insurance.save()){
                    Stage stage = (Stage) addInsurance.getScene().getWindow();
                        stage.close();
                     DISPLAY_MESSAGE.showInfoDialog(addInsurance.getScene().getWindow(), "Successfully Saved Bank Details", NotificationType.INFORMATION);
                    ((Node)(e.getSource())).getScene().getWindow().hide();
                }else{
                    DISPLAY_MESSAGE.showInfoDialog(addInsurance.getScene().getWindow(), "Failed to Save Insurance ", NotificationType.ERROR);
                     
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
