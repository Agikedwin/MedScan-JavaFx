/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllersUI;

import com.solutions.entorno.utilities.LoadUI;
import com.solutions.entorno.utilities.SystemVariables;
import static com.solutions.entorno.utilities.SystemVariables.DISPLAY_MESSAGE;
import static com.solutions.entorno.utilities.SystemVariables.UILoader;
import com.solutions.entorno.utilities.dialogs.LookupVar;
import com.solutions.entorno.utilities.dialogs.NotificationType;
import controllers.AltraSoundServices;
import controllers.CtScanServices;
import static controllers.CtScanServices.ctScanTV;
import controllers.Doctors;
import controllers.PatientsRequest;
import controllers.Payments;
import controllers.RefereeCommission;
import controllers.Services;
import controllers.XRayServices;
import static controllers.XRayServices.XRayTV;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.CTScanServicesModel;

/**
 * FXML Controller class
 *
 * @author YoungGucha
 */
public class Patients_RecordController implements Initializable {
    LoadUI view;
    @FXML
    private TableView<?> referralsTV;
    @FXML
    private Button addReferrals;
      OnActionListener onActionListener = new OnActionListener();
    MouseActionListener mouseActionListener = new MouseActionListener();
    OnSelectionListener onSelectionListener = new OnSelectionListener();
    @FXML
    private AnchorPane xRayTVWrapper;
    @FXML
    private TableView <Services> xRayTV;
    @FXML
    private Button addXRayService;
    @FXML
    private AnchorPane anchorParent;
    
   XRayServices  xRay;
   XRayServices lookUpService = null;
    @FXML
    private Button addAltrasound;
    @FXML
    private Button addctScan;
   AltraSoundServices altraSound;
    AltraSoundServices lookUpAltrasound = null;
    CtScanServices lookUpCtScan;
    Services services = null;
    CtScanServices ctScanservice;
    @FXML
    private TextField totalPrice;
    @FXML
    private Button saveServices;
    private double totaServiceCost=0;
    Services patientServices;
    
    PatientsRequest patientRequest;
    private String  fetchPatientId;
    @FXML
    private Tab paymentsTab;
    @FXML
    private AnchorPane anchorPayments;
    @FXML
    private TableView<?> PaymentsTV;
    @FXML
    private Button savePayments;
   RefereeCommission refcom;
    @FXML
    private Label selectedPatientName;
    private Payments payments;
   boolean saveServicesOffered=false;
    @FXML
    private Button viewReferals;
    @FXML
    private Button viewPayments;
    @FXML
    private Tab ExaminationReportTab;
    @FXML
    private AnchorPane anchorExaminationReport;
    @FXML
    private TableView<?> ExaminationReportTV;
    @FXML
    private Button saveExaminationReport;
    @FXML
    private Button viewExaminationReport;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    selectedPatientName.setStyle("-fx-text-fill: green;");
    saveServices.setStyle("-fx-text-fill:#33E3FF");
//    selectedPatientName.setStyle("-fx-fill: linear-gradient(from 0% 0% to 100% 200%, repeat, aqua 0%, red 50%)");
//    selectedPatientName.setStyle("-fx-stroke: black");
//    selectedPatientName.setStyle("-fx-stroke-width: 1");
        selectedPatientName.setText(" PATIENT'S NAME :   "+PatientsRequest.selectedPatientName.toUpperCase());
        refcom=new RefereeCommission();
       addReferrals.setOnAction(onActionListener);
       addXRayService.setOnAction(onActionListener);
       addAltrasound.setOnAction(onActionListener);
       addctScan.setOnAction(onActionListener);
        saveServices.setOnAction(onActionListener);
        savePayments.setOnAction(onActionListener);
        
        viewReferals.setOnAction(onActionListener);
       
          patientServices=new  Services ();
          
          altraSound=new AltraSoundServices();
          payments=new Payments();
          //loadxRayService();
//          xRayTV = xRay.loadXRayTV(0);
//          UILoader.loadTable(xRayTVWrapper, xRayTV);
         loadXrayTV();
         //loadDoctorsComTv(PatientsRequest.globalPatientId);
         loadPaymentTV(PatientsRequest.globalPatientId);
          altraSound=new AltraSoundServices();
           ctScanservice=new   CtScanServices() ;
           patientRequest=new PatientsRequest();
         fetchPatientId=patientServices.getPatientId();
         lookUpService=null;
         
         saveExaminationReport.setOnAction(onActionListener);
         
         
    }  
    
  public void loadDoctorsComTv(String patientid,int maxCount){
    
  referralsTV=refcom.loadDoctorComTV(patientid,maxCount);
  UILoader.loadTable(anchorParent, referralsTV);
    }
    private void loadXray(int id) {
        xRayTV=xRay.loadXRayTV(id);
        UILoader.loadTable(xRayTVWrapper, xRayTV);
    }
    private void loadXrayTV() {
        xRayTV=patientServices.loadServices(0);
        UILoader.loadTable(xRayTVWrapper, xRayTV);
       
    }
    
    private void loadAltraSoundTV() {
        xRayTV=altraSound.altraSoundTV();
        UILoader.loadTable(xRayTVWrapper, xRayTV);
    }
    private void loadsctScanTV(){
       xRayTV= ctScanservice.ctScanTV();
       UILoader.loadTable(xRayTVWrapper, xRayTV);
    }
    private void loadPaymentTV(String patientid){
       PaymentsTV= payments.loadPaymentsTV(patientid);
       UILoader.loadTable(anchorPayments, PaymentsTV);
    }
    
    
     class OnActionListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            
            if(addReferrals==e.getSource()){
               
                if((lookUpService==null || lookUpAltrasound ==null || lookUpCtScan==null) & (!saveServicesOffered) ){
                DISPLAY_MESSAGE.showInfoDialog(addReferrals.getScene().getWindow(), "Please Add Services before you add referral ", NotificationType.ERROR);
               
               }else {
                    
                 Stage stage = new Stage();
                Parent root = null;
                String css = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/views/AddReferrals.fxml"));
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
                stage.setTitle("Patient Referrals");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(
                ((Node) e.getSource()).getScene().getWindow());
                stage.show();
                
                
                
                }
                // loadDoctorsComTv(PatientsRequest.globalPatientId);
                }
            
            if(addXRayService==e.getSource()){
                
                 Stage utility = new Stage();
            LookupVar.SEARCH = LookupVar.LOOK_UP.LOOKUP_CONTR;

            utility.setTitle("SELECT XRAY SERVICE");
            LookupVar.SEARCH.setTbl_items(XRayTV());
            SystemVariables.UILoader.loadTable(LookupVar.SEARCH.getAnchor_tbl_items(), LookupVar.SEARCH.getTbl_items());

            LookupVar.SEARCH.getTxt_search().setOnKeyReleased((KeyEvent event) -> {
                LookupVar.SEARCH.getTbl_items().getItems().clear();
               //LookupVar.SEARCH.getTbl_items().getItems().addAll(clientTV(LookupVar.SEARCH.getTxt_search().getText()).getItems());
                SystemVariables.UILoader.loadTable(LookupVar.SEARCH.getAnchor_tbl_items(), LookupVar.SEARCH.getTbl_items());
            });
            LookupVar.SEARCH.getTbl_items().setOnMouseClicked((MouseEvent event) -> {
                
                if (event.getClickCount() == 2) {
                    lookUpService = (XRayServices) LookupVar.SEARCH.getTbl_items().getSelectionModel().getSelectedItem();  
                   
                   //loadXray(0);
                    System.out.println("patient id  "+fetchPatientId);
                    
                    Services serv = new Services();
                    serv.setServiceId(lookUpService.getServiceid());
                    serv.setServiceName(lookUpService.getService_name());
                    serv.setServiceType("XRAY");
                    serv.setCost(lookUpService.getCost());
                    totaServiceCost= totaServiceCost+lookUpService.getCost();
                    totalPrice.setText(""+totaServiceCost);
                    
                    xRayTV.getItems().add(serv);
                   // xRayTV.setItems();
                   // System.out.println("PRINT PBJECT"+serv.getService_name()+" "+serv.getServiceid()+" "+serv.getCost());
                    //txt_clientStatement.setText(lookupCustomer.getName());
                    //loadStatement(lookupCustomer.getClientid());
                    utility.close();
                }
            });

            utility.initModality(Modality.WINDOW_MODAL);
            utility.initOwner(addXRayService.getScene().getWindow());
            utility.setScene(LookupVar.LOOK_UP.lookupScene);
            utility.setIconified(false);
            utility.show();
            }
            
            if(addAltrasound==e.getSource()){
              
                 Stage utility = new Stage();
            LookupVar.SEARCH = LookupVar.LOOK_UP.LOOKUP_CONTR;

            utility.setTitle("SELECT AlTRA SOUND");
            LookupVar.SEARCH.setTbl_items(AltraSoundServices.altraTV());
            SystemVariables.UILoader.loadTable(LookupVar.SEARCH.getAnchor_tbl_items(), LookupVar.SEARCH.getTbl_items());

            LookupVar.SEARCH.getTxt_search().setOnKeyReleased((KeyEvent event) -> {
                LookupVar.SEARCH.getTbl_items().getItems().clear();
               //LookupVar.SEARCH.getTbl_items().getItems().addAll(clientTV(LookupVar.SEARCH.getTxt_search().getText()).getItems());
                SystemVariables.UILoader.loadTable(LookupVar.SEARCH.getAnchor_tbl_items(), LookupVar.SEARCH.getTbl_items());
            });
            LookupVar.SEARCH.getTbl_items().setOnMouseClicked((MouseEvent event) -> {
                
                if (event.getClickCount() == 2) {
                    lookUpAltrasound = (AltraSoundServices) LookupVar.SEARCH.getTbl_items().getSelectionModel().getSelectedItem();  
                   // System.out.println("SELCETED: "+lookUpAltrasound.getService_name());
                   //loadXray(0);
                    
                   Services serv = new Services();
                    serv.setServiceId(lookUpAltrasound.getServiceid());
                    serv.setServiceName(lookUpAltrasound.getService_name());
                    serv.setServiceType("ALTRA-SOUND");
                    serv.setCost(lookUpAltrasound.getCost());
                    totaServiceCost= totaServiceCost+lookUpAltrasound.getCost();
                    totalPrice.setText(""+totaServiceCost);
                    xRayTV.getItems().add(serv);
                   // xRayTV.setItems();
                   // System.out.println("PRINT PBJECT"+serv.getService_name()+" "+serv.getServiceid()+" "+serv.getCost());
                    //txt_clientStatement.setText(lookupCustomer.getName());
                    //loadStatement(lookupCustomer.getClientid());
                    utility.close();
                }
            });

            utility.initModality(Modality.WINDOW_MODAL);
            utility.initOwner(addAltrasound.getScene().getWindow());
            utility.setScene(LookupVar.LOOK_UP.lookupScene);
            utility.setIconified(false);
            utility.show();   
            }
            
            if(addctScan==e.getSource()){
                
                 Stage utility = new Stage();
            LookupVar.SEARCH = LookupVar.LOOK_UP.LOOKUP_CONTR;

            utility.setTitle("SELECT CT-SCAN");
            LookupVar.SEARCH.setTbl_items(ctScanTV());
            SystemVariables.UILoader.loadTable(LookupVar.SEARCH.getAnchor_tbl_items(), LookupVar.SEARCH.getTbl_items());

            LookupVar.SEARCH.getTxt_search().setOnKeyReleased((KeyEvent event) -> {
                LookupVar.SEARCH.getTbl_items().getItems().clear();
               //LookupVar.SEARCH.getTbl_items().getItems().addAll(clientTV(LookupVar.SEARCH.getTxt_search().getText()).getItems());
                SystemVariables.UILoader.loadTable(LookupVar.SEARCH.getAnchor_tbl_items(), LookupVar.SEARCH.getTbl_items());
            });
            LookupVar.SEARCH.getTbl_items().setOnMouseClicked((MouseEvent event) -> {
               
                if (event.getClickCount() == 2) {
                    lookUpCtScan = (CtScanServices) LookupVar.SEARCH.getTbl_items().getSelectionModel().getSelectedItem();  
                    System.out.println("SELCETED: "+lookUpCtScan.getService_name());
                  // loadXray(0);
                    
                    Services serv = new Services();
                    serv.setServiceId(lookUpCtScan.getServiceid());
                    serv.setServiceName(lookUpCtScan.getService_name());
                     serv.setServiceType("CT-SCAN");
                    serv.setCost(lookUpCtScan.getCost());
                    totaServiceCost=totaServiceCost +lookUpCtScan.getCost();
                    totalPrice.setText(""+totaServiceCost);
                    xRayTV.getItems().add(serv);
                   // xRayTV.setItems();
                   // System.out.println("PRINT PBJECT"+serv.getService_name()+" "+serv.getServiceid()+" "+serv.getCost());
                    //txt_clientStatement.setText(lookupCustomer.getName());
                    //loadStatement(lookupCustomer.getClientid());
                    utility.close();
                  
                }
            });

            utility.initModality(Modality.WINDOW_MODAL);
            utility.initOwner(addctScan.getScene().getWindow());
            utility.setScene(LookupVar.LOOK_UP.lookupScene);
            utility.setIconified(false);
            utility.show();  
                
            }
           
            
            if (saveServices == e.getSource()) {
                
                saveServicesOffered=false;
                Services ser=new Services();
               
                if(xRayTV.getItems().isEmpty()){
                DISPLAY_MESSAGE.showInfoDialog(saveServices.getScene().getWindow(), "Please Add Services ", NotificationType.ERROR);
               
               }else {
                    try {
                        
                      int servicecount=ser.maxServiceCount(PatientsRequest.globalPatientId);
                      
                   for (Services services   : xRayTV.getItems()) {
                        ser.setServiceId(services.getServiceId());
                         ser.setPatientId(PatientsRequest.globalPatientId);
                         ser.setServiceType(services.getServiceType());
                         ser.setServiceName(services.getServiceName());
                         ser.setCost(services.getCost());
                         ser.setCount(servicecount+1);//increament by one
                         ser.save();
                          }
                    saveServicesOffered=true;
                    DISPLAY_MESSAGE.showInfoDialog(saveServices.getScene().getWindow(), "Services Successfully Submited, Proceed and Add Referrals ", NotificationType.INFORMATION);
               
                    } catch (Exception ee) {
                        ee.printStackTrace();
                        DISPLAY_MESSAGE.showInfoDialog(saveServices.getScene().getWindow(), "Services Failled to  submit ", NotificationType.ERROR);
                    }
                     
                }
            }
            
            if(savePayments==e.getSource()){
                if(referralsTV.getItems().isEmpty()){
                  DISPLAY_MESSAGE.showInfoDialog(savePayments.getScene().getWindow(), "Please Add Referee Doctor Before You Proceed To Payments ", NotificationType.ERROR);
               
                }
                else {
                     Stage stage = new Stage();
                Parent root = null;
                String css = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/views/ServicePayments.fxml"));
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
                stage.setTitle("My modal window");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(
                ((Node) e.getSource()).getScene().getWindow());
                stage.show();
                }
                
                
                
                
            }
            
            if(viewReferals==e.getSource()){
            
            loadDoctorsComTv(PatientsRequest.globalPatientId,refcom.maxCount(PatientsRequest.globalPatientId));
            }
            
            if(savePayments==e.getSource()){
//                if(referralsTV.getItems().isEmpty()){
//                  DISPLAY_MESSAGE.showInfoDialog(savePayments.getScene().getWindow(), "Please Add Referee Doctor Before You Proceed To Payments ", NotificationType.ERROR);
//               
//                }
//                else {
//                     Stage stage = new Stage();
//                Parent root = null;
//                String css = null;
//                try {
//                    root = FXMLLoader.load(getClass().getResource("/views/ServicePayments.fxml"));
//                    URL url = this.getClass().getResource("/css/JMetroLightTheme.css");
//                    if (url == null) {
//                        System.out.println("Resource not found. Aborting.");
//                        System.exit(-1);
//                    }
//                    css = url.toExternalForm();
//                } catch (IOException ex) {
//                  //  Logger.getLogger(views.PatientsController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                Scene scene = new Scene(root);
//                scene.getStylesheets().add(css);
//                stage.setScene(scene);
//                stage.setTitle("Payments");
//                stage.initModality(Modality.WINDOW_MODAL);
//                stage.initOwner(
//                ((Node) e.getSource()).getScene().getWindow());
//                stage.show();
//                }
//                
//                
                
                
            }
            // load examination
           if(saveExaminationReport==e.getSource()){
                
                Stage stage = new Stage();
                Parent root = null;
                String css = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/views/ExaminationReport.fxml"));
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
                stage.setTitle("Examination Report");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(
                ((Node) e.getSource()).getScene().getWindow());
                stage.show();
                }
                
                
                
                
            
            
            }
        
        
        
        
           
            
        }
    }


    class MouseActionListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {
            
            
            
        }
    }

    class OnSelectionListener implements EventHandler<Event> {

        @Override
        public void handle(Event e) {
          
        }

    }

    
    

