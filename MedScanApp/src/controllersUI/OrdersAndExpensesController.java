/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllersUI;

import com.solutions.entorno.utilities.LoadUI;
import static com.solutions.entorno.utilities.SystemVariables.UILoader;
import controllers.InternalRequisition;
import controllers.LocalOrderPurchase;
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
 * @author YoungGucha
 */
public class OrdersAndExpensesController implements Initializable {

    LoadUI view;
    OnActionListener onActionListener = new OnActionListener();
    MouseActionListener mouseActionListener = new MouseActionListener();
    OnSelectionListener onSelectionListener = new OnSelectionListener();
    @FXML
    private AnchorPane anchorParent;
    @FXML
    private Tab ocalPurchaseOrderTab;
    @FXML
    private AnchorPane anchorLPOWrapper;
    @FXML
    private TableView<LocalOrderPurchase> localPurchaseOrderTV;
    @FXML
    private TableView<?> lpoItemsTV;
    @FXML
    private Button addLOP;
    @FXML
    private Tab internalRequisition;
    @FXML
    private AnchorPane anchorRequisitionWrapper;
    @FXML
    private TableView<InternalRequisition> InternalRequisitionTV;
    @FXML
    private TableView orderItemTV;
    @FXML
    private Button addRequisition;
    LocalOrderPurchase localOrderPurchase;
    public static LocalOrderPurchase localOrderPurchaseGlobal;
    @FXML
    private AnchorPane anchorLPOOrdersWrapper;
    @FXML
    private AnchorPane anchorLPOItemsWrapper;

    InternalRequisition internalReq;

    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        localOrderPurchase = new LocalOrderPurchase();
        internalReq = new InternalRequisition();
        loadLPOTV();
        addLOP.setOnAction(onActionListener);
        addRequisition.setOnAction(onActionListener);
        //localPurchaseOrderTV.setOnMouseClicked(onSelectionListener);
        loadRequisitionTV();

    }

    public void loadLPOTV() {

        localPurchaseOrderTV = localOrderPurchase.loadLocalOrderPurchaseTV();
        UILoader.loadTable(anchorLPOWrapper, localPurchaseOrderTV);
        localPurchaseOrderTV.setOnMouseClicked(mouseActionListener);
        localPurchaseOrderTV.getSelectionModel().setCellSelectionEnabled(true);

    }

    public void loadRequisitionTV() {

        InternalRequisitionTV = internalReq.loadInternalReqTV();
        UILoader.loadTable(anchorRequisitionWrapper, InternalRequisitionTV);
        InternalRequisitionTV.setOnMouseClicked(mouseActionListener);
        InternalRequisitionTV.getSelectionModel().setCellSelectionEnabled(true);

    }

    class OnActionListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            if (addLOP == e.getSource()) {

                LocalOrderPurchase.setAction("Save");
                Stage stage = new Stage();
                Parent root = null;
                String css = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/views/AddLocalOrderPurchases.fxml"));
                    URL url = this.getClass().getResource("/css/JMetroLightTheme.css");
                    if (url == null) {
                        System.out.println("Resource not found. Aborting.");
                        System.exit(-1);
                    }
                    css = url.toExternalForm();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    //  Logger.getLogger(views.PatientsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(root);
                scene.getStylesheets().add(css);
                stage.setScene(scene);
                stage.setTitle("Add L.P.O");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(
                        ((Node) e.getSource()).getScene().getWindow());
                stage.show();
                LocalOrderPurchase.setAction("Save");

            }
            if (addRequisition == e.getSource()) {
                InternalRequisition.setAction("Save");
                Stage stage = new Stage();
                Parent root = null;
                String css = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/views/AddInternalRequisition.fxml"));
                    URL url = this.getClass().getResource("/css/JMetroLightTheme.css");
                    if (url == null) {
                        System.out.println("Resource not found. Aborting.");
                        System.exit(-1);
                    }
                    css = url.toExternalForm();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    //  Logger.getLogger(views.PatientsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Scene scene = new Scene(root);
                scene.getStylesheets().add(css);
                stage.setScene(scene);
                stage.setTitle("Add L.P.O");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(
                        ((Node) e.getSource()).getScene().getWindow());
                stage.show();

            }
        }

    }

    class MouseActionListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {
            if (localPurchaseOrderTV == e.getSource()) {

                if (localPurchaseOrderTV.getSelectionModel().getSelectedCells().get(0).getColumn() == 6) {
                    localOrderPurchase = new LocalOrderPurchase();
                    localOrderPurchase = localPurchaseOrderTV.getSelectionModel().getSelectedItem();
                    localOrderPurchase.setExpenseTypeEdit(localOrderPurchase.getExpenseType());
                    localOrderPurchase.setItemNameEdit(localOrderPurchase.getItemName());
                    localOrderPurchase.setDescriptionEdit(localOrderPurchase.getDescription());
                    localOrderPurchase.setQuantityEdit(localOrderPurchase.getQuantity());
                    localOrderPurchase.setCostEdit(localOrderPurchase.getCost());
                    LocalOrderPurchase.setAction("Edit");

                    Stage stage = new Stage();
                    Parent root = null;
                    String css = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/views/AddLocalOrderPurchases.fxml"));
                        URL url = this.getClass().getResource("/css/JMetroLightTheme.css");
                        if (url == null) {
                            System.out.println("Resource not found. Aborting.");
                            System.exit(-1);
                        }
                        css = url.toExternalForm();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(css);
                    stage.setScene(scene);
                    stage.setTitle("Edit LPO");
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(
                            ((Node) e.getSource()).getScene().getWindow());
                    stage.show();

                }

                if (localPurchaseOrderTV.getSelectionModel().getSelectedCells().get(0).getColumn() == 5) {
                    loadLPOTV();
                }

            }

            if (InternalRequisitionTV == e.getSource()) {

                if (InternalRequisitionTV.getSelectionModel().getSelectedCells().get(0).getColumn() == 6) {
                    internalReq = new InternalRequisition();
                    internalReq = InternalRequisitionTV.getSelectionModel().getSelectedItem();
                    internalReq.setRequisitionid(internalReq.getRequisitionid());
                    internalReq.setExpenseType(internalReq.getExpenseType());
                    internalReq.setItemName(internalReq.getItemName());
                    internalReq.setDescription(internalReq.getDescription());
                    internalReq.setQuantity(internalReq.getQuantity());
                    internalReq.setCost(internalReq.getCost());
                    InternalRequisition.setAction("Edit");

                    Stage stage = new Stage();
                    Parent root = null;
                    String css = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/views/AddInternalRequisition.fxml"));
                        URL url = this.getClass().getResource("/css/JMetroLightTheme.css");
                        if (url == null) {
                            System.out.println("Resource not found. Aborting.");
                            System.exit(-1);
                        }
                        css = url.toExternalForm();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(css);
                    stage.setScene(scene);
                    stage.setTitle("Edit Interenal Requisition");
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(
                            ((Node) e.getSource()).getScene().getWindow());
                    stage.show();

                }

                if (InternalRequisitionTV.getSelectionModel().getSelectedCells().get(0).getColumn() == 5) {
                    loadRequisitionTV();
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
