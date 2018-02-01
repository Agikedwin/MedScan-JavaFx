/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllersUI;

import static com.solutions.entorno.utilities.SystemVariables.DISPLAY_MESSAGE;
import com.solutions.entorno.utilities.dialogs.NotificationType;
import controllers.Bank;
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
public class AddBanksController implements Initializable {
    @FXML
    private AnchorPane anchorParent;
    @FXML
    private TextField bankName;
    @FXML
    private TextField accountNumber;
    @FXML
    private Button save;

    OnActionListener onActionListener = new OnActionListener();
    MouseActionListener mouseActionListener = new MouseActionListener();
    OnSelectionListener onSelectionListener = new OnSelectionListener();
    
    Bank banks;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        banks = new Bank();
        
        save.setOnAction(onActionListener);
    }  
    
    class MouseActionListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {

        }
    }

    class OnActionListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            if (save == e.getSource()) {
                if (bankName.getText().equals("")) {
                    DISPLAY_MESSAGE.showInfoDialog(save.getScene().getWindow(), "Enter Bank Name", NotificationType.ERROR);
                } else if (accountNumber.getText().isEmpty()) {
                    DISPLAY_MESSAGE.showInfoDialog(save.getScene().getWindow(), "Enter Account Number", NotificationType.ERROR);
                } else {
                    banks.setBankName(bankName.getText());
                    banks.setAccountNo(accountNumber.getText());
                    if (banks.save()) {

                        Stage stage = (Stage) save.getScene().getWindow();
                        stage.close();
                        DISPLAY_MESSAGE.showInfoDialog(save.getScene().getWindow(), "Successfully Saved Bank Details", NotificationType.INFORMATION);
                    } else {
                        DISPLAY_MESSAGE.showInfoDialog(save.getScene().getWindow(), "Failed to save Bank Details", NotificationType.ERROR);
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
