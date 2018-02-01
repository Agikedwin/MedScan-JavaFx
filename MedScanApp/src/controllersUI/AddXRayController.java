/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllersUI;

import static com.solutions.entorno.utilities.SystemVariables.DISPLAY_MESSAGE;
import com.solutions.entorno.utilities.dialogs.NotificationType;
import controllers.XRayServices;
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
public class AddXRayController implements Initializable {

    @FXML
    private AnchorPane anchorParent;
    @FXML
    private TextField name;
    @FXML
    private TextField cost;
    @FXML
    private Button addXRay;

    OnActionListener onActionListener = new OnActionListener();
    MouseActionListener mouseActionListener = new MouseActionListener();
    OnSelectionListener onSelectionListener = new OnSelectionListener();
    XRayServices xrayService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        xrayService = new XRayServices();
        addXRay.setOnAction(onActionListener);
    }

    class MouseActionListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {

        }
    }

    class OnActionListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            if (addXRay == e.getSource()) {
                if (name.getText().equals("")) {
                    DISPLAY_MESSAGE.showInfoDialog(addXRay.getScene().getWindow(), "Input Service Name", NotificationType.ERROR);
                } else if (cost.getText().isEmpty()) {
                    DISPLAY_MESSAGE.showInfoDialog(addXRay.getScene().getWindow(), "Input the Cost", NotificationType.ERROR);
                } else {
                    xrayService.setService_name(name.getText());
                    xrayService.setCost(Double.parseDouble(cost.getText()));
                    if (xrayService.save()) {

                        Stage stage = (Stage) addXRay.getScene().getWindow();
                        stage.close();
                        DISPLAY_MESSAGE.showInfoDialog(addXRay.getScene().getWindow(), "Successfully Saved XRay Details", NotificationType.INFORMATION);
                    } else {
                        DISPLAY_MESSAGE.showInfoDialog(addXRay.getScene().getWindow(), "Failed to save XRay Details", NotificationType.ERROR);
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
