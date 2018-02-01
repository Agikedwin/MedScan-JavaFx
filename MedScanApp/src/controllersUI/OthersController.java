/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllersUI;

import static com.solutions.entorno.utilities.SystemVariables.UILoader;
import controllers.Bank;
import controllers.Insurance;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class OthersController implements Initializable {

    @FXML
    private AnchorPane anchorParent;
    @FXML
    private AnchorPane anchorBanksWrapper;
    @FXML
    private TableView<?> banksTV;
    @FXML
    private Button addBank;

    OnActionListener onActionListener = new OnActionListener();
    MouseActionListener mouseActionListener = new MouseActionListener();
    OnSelectionListener onSelectionListener = new OnSelectionListener();
   
    Bank banks;
    Insurance insurance;
    @FXML
    private AnchorPane anchorInsuranceWrapper;
    @FXML
    private TableView<?> inssuranceTV;
    @FXML
    private Button addInsurance;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        banks = new Bank();
        insurance=new Insurance();
        addBank.setOnAction(onActionListener);  
        addInsurance.setOnAction(onActionListener);
        banksTV.setOnMouseClicked(mouseActionListener);
        loadBanks();
        loadInsurance();
       
    }
    
    public void loadBanks(){
     banksTV = banks.banksTV();
     UILoader.loadTable(anchorBanksWrapper, banksTV);    
    }
public void loadInsurance(){
     inssuranceTV = insurance.InsuranceTV();
     UILoader.loadTable(anchorInsuranceWrapper, inssuranceTV);    
    }
    class MouseActionListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {

        }
    }

    class OnActionListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            if (addBank == e.getSource()) {
                Stage stage = new Stage();
                Parent root = null;
                String css = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/views/addBanks.fxml"));
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
                stage.setTitle("Bank Registration");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(
                        ((Node) e.getSource()).getScene().getWindow());
                stage.show();

            }
            
            if (addInsurance == e.getSource()) {
                Stage stage = new Stage();
                Parent root = null;
                String css = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/views/addInsurance.fxml"));
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
                stage.setTitle("Insurance Registration");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(
                        ((Node) e.getSource()).getScene().getWindow());
                stage.show();

            }
        }

        

    }

    class OnSelectionListener implements EventHandler<Event> {

        @Override
        public void handle(Event e) {

        }

    }

}
