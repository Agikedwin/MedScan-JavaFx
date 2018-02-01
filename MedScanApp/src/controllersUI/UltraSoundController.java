/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllersUI;

import static com.solutions.entorno.utilities.SystemVariables.DISPLAY_MESSAGE;
import static com.solutions.entorno.utilities.SystemVariables.UILoader;
import com.solutions.entorno.utilities.dialogs.NotificationType;
import controllers.AltraSoundServices;
import controllers.Services;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author YoungGucha
 */
public class UltraSoundController implements Initializable {
    @FXML
    private AnchorPane altraSoundTVWrapper;
    @FXML
    private TextField searchAltraSound;
    @FXML
    private TableView <AltraSoundServices>altraSoundTV;
    @FXML
    private Button cancelAltrasound;
    AltraSoundServices altrasound;
   OnActionListener onActionListener = new OnActionListener();
    MouseActionListener mouseActionListener = new MouseActionListener();
    OnSelectionListener onSelectionListener = new OnSelectionListener();
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
        
         altrasound= new AltraSoundServices();
         loadAltraSounds();
         altraSoundTV.setOnMouseClicked(new EventHandler<MouseEvent>() {

             @Override
             public void handle(MouseEvent event) {
               
                        altrasound = altraSoundTV.getSelectionModel().getSelectedItem();
                        Services service=new Services();
                        service.setServiceId(altrasound.getServiceid());
                        service.setPatientId("00021");
                        service.setServiceType(altrasound.getService_name());
                        service.setCost(altrasound.getCost());
                       
                        
                        
                         if(service!=null){
                             if(service.save()){
                                 System.out.println("reached at save");
                               DISPLAY_MESSAGE.showInfoDialog(altraSoundTV.getScene().getWindow(), "Successfully Added  Service", NotificationType.INFORMATION);
                               
                             }else{
                                 DISPLAY_MESSAGE.showInfoDialog(altraSoundTV.getScene().getWindow(), "Failed to Add Service", NotificationType.ERROR);
              
                             }
                             
                         
                         } 
             }
         });
    }  
    
    private void loadAltraSounds(){
//        //altraSoundTV.setOnMouseClicked(mouseActionListener);
//      altraSoundTV = altrasound.altraSound();  
//      UILoader.loadTable(altraSoundTVWrapper, altraSoundTV);
    }
    
    class OnActionListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            
            
           
            
        }
    }

    class MouseActionListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {
            if (altraSoundTV == e.getSource()) {
                try {
                    if (altraSoundTV.getSelectionModel().getSelectedCells().get(0).getColumn() ==2) {
                        System.out.println("clicked here.........");
                        altrasound = altraSoundTV.getSelectionModel().getSelectedItem();
                        Services service=new Services();
                        service.setServiceId(altrasound.getServiceid());
                        service.setServiceType(altrasound.getService_name());
                        service.setCost(altrasound.getCost());
                        
                         if(service!=null){
                             if(service.save()){
                                 
                               DISPLAY_MESSAGE.showInfoDialog(altraSoundTV.getScene().getWindow(), "Successfully Added  Service", NotificationType.INFORMATION);
                               
                             }else{
                                 DISPLAY_MESSAGE.showInfoDialog(altraSoundTV.getScene().getWindow(), "Failed to Add Service", NotificationType.ERROR);
              
                             }
                             
                         
                         }      

                    }  
                }catch (Exception ex) {
                    ex.printStackTrace();
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
