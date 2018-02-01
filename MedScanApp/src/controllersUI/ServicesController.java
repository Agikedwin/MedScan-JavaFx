/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllersUI;

import static com.solutions.entorno.utilities.SystemVariables.UILoader;
import controllers.AltraSoundServices;
import controllers.CtScanServices;
import controllers.XRayServices;
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
import javafx.scene.control.Tab;
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
public class ServicesController implements Initializable {

    @FXML
    private AnchorPane anchorParent;
    @FXML
    private AnchorPane anchorXRayWrapper;
    @FXML
    private TableView<?> xrayTV;
    @FXML
    private Button addXRay;
    @FXML
    private AnchorPane anchorUltrasoundWrapper;
    @FXML
    private TableView<?> ultrasoundTV;
    @FXML
    private Button addUltrasound;
    @FXML
    private AnchorPane anchorCtscanWrapper;
    @FXML
    private TableView<?> ctscanTV;
    @FXML
    private Button addCtscan;
    @FXML
    private Tab xrayTab;
    @FXML
    private Tab ultrasoundTab;
    @FXML
    private Tab ctscanTab;

    OnActionListener onActionListener = new OnActionListener();
    MouseActionListener mouseActionListener = new MouseActionListener();
    OnSelectionListener onSelectionListener = new OnSelectionListener();
    XRayServices xrayServices;
    AltraSoundServices ultrasounServices;
    CtScanServices ctscanServices;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        xrayServices = new XRayServices();
        ultrasounServices = new AltraSoundServices();
        ctscanServices = new CtScanServices();

        xrayTab.setOnSelectionChanged(onSelectionListener);
        ultrasoundTab.setOnSelectionChanged(onSelectionListener);
        ctscanTab.setOnSelectionChanged(onSelectionListener);

        addXRay.setOnAction(onActionListener);
        addUltrasound.setOnAction(onActionListener);
        addCtscan.setOnAction(onActionListener);
        xrayTV = xrayServices.XRayTV();
        UILoader.loadTable(anchorXRayWrapper, xrayTV);
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
                Stage stage = new Stage();
                Parent root = null;
                String css = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/views/addXRay.fxml"));
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
                stage.setTitle("XRay Registration");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(
                        ((Node) e.getSource()).getScene().getWindow());
                stage.show();

            } else if (addUltrasound == e.getSource()) {
                Stage stage = new Stage();
                Parent root = null;
                String css = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/views/addUltrasound.fxml"));
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
                stage.setTitle("Ultra Sound Registration");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(
                        ((Node) e.getSource()).getScene().getWindow());
                stage.show();
            }else if(addCtscan == e.getSource()){
                Stage stage = new Stage();
                Parent root = null;
                String css = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/views/addCtscan.fxml"));
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
                stage.setTitle("CTScan Registration");
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
            if (ultrasoundTab == e.getSource()) {
                ultrasoundTV = ultrasounServices.altraSoundTV();
                UILoader.loadTable(anchorUltrasoundWrapper, ultrasoundTV);
            }else if(ctscanTab == e.getSource()){
                ctscanTV = ctscanServices.loadctScanTV();
                UILoader.loadTable(anchorCtscanWrapper, ctscanTV);
            }
        }

    }

}
