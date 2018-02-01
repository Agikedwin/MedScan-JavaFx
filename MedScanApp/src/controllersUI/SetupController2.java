/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllersUI;

import static com.solutions.entorno.utilities.SystemVariables.UILoader;
import controllers.Referees;
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
public class SetupController2 implements Initializable {
    OnActionListener onActionListener = new OnActionListener();
    MouseActionListener mouseActionListener = new MouseActionListener();
    OnSelectionListener onSelectionListener = new OnSelectionListener();

     @FXML
    private AnchorPane anchorParent;
    @FXML
    private AnchorPane anchorRefereeWrapper;
    @FXML
    private TableView<?> refereeTV;
    @FXML
    private Button addReferee;
    Referees referees;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        referees = new Referees();
        
        addReferee.setOnAction(onActionListener);
        //refereeTV.setOnMouseClicked(mouseActionListener);
        
       // refereeTV = referees.refereeTV();
        UILoader.loadTable(anchorRefereeWrapper, refereeTV);
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
                Stage stage = new Stage();
                Parent root = null;
                String css = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/views/addReferee.fxml"));
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
                stage.setTitle("Referee Registration");
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
