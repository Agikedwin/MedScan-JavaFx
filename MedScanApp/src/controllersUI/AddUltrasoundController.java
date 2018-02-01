/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllersUI;

import static com.solutions.entorno.utilities.SystemVariables.DISPLAY_MESSAGE;
import com.solutions.entorno.utilities.dialogs.NotificationType;
import controllers.AltraSoundServices;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AddUltrasoundController implements Initializable {
    @FXML
    private AnchorPane anchorParent;
    @FXML
    private TextField name;
    @FXML
    private TextField cost;
    @FXML
    private Button addUltra;

    OnActionListener onActionListener = new OnActionListener();
    MouseActionListener mouseActionListener = new MouseActionListener();
    OnSelectionListener onSelectionListener = new OnSelectionListener();
    AltraSoundServices ultraServices;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ultraServices = new AltraSoundServices();
        addUltra.setOnAction(onActionListener);
    }    
    
    class MouseActionListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {

        }
    }

    class OnActionListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            if (addUltra == e.getSource()) {
                if (name.getText().equals("")) {
                    DISPLAY_MESSAGE.showInfoDialog(addUltra.getScene().getWindow(), "Enter Service Name", NotificationType.ERROR);
                } else if (cost.getText().isEmpty()) {
                    DISPLAY_MESSAGE.showInfoDialog(addUltra.getScene().getWindow(), "Enter the Cost", NotificationType.ERROR);
                } else {
                    ultraServices.setService_name(name.getText());
                    ultraServices.setCost(Double.parseDouble(cost.getText()));
                    if (ultraServices.save()) {

                        Stage stage = (Stage) addUltra.getScene().getWindow();
                        stage.close();
                        DISPLAY_MESSAGE.showInfoDialog(addUltra.getScene().getWindow(), "Successfully Saved Ultra Sound Details", NotificationType.INFORMATION);
                    } else {
                        DISPLAY_MESSAGE.showInfoDialog(addUltra.getScene().getWindow(), "Failed to save Ultra Sound Details", NotificationType.ERROR);
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
