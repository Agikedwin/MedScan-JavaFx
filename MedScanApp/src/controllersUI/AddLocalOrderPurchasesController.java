/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllersUI;

import static com.solutions.entorno.utilities.SystemVariables.DISPLAY_MESSAGE;
import com.solutions.entorno.utilities.dialogs.NotificationType;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author YoungGucha
 */
public class AddLocalOrderPurchasesController implements Initializable {
     OnActionListener onActionListener = new OnActionListener();
    MouseActionListener mouseActionListener = new MouseActionListener();
    OnSelectionListener onSelectionListener = new OnSelectionListener();
    @FXML
    private HBox refereeDoctorid;
    @FXML
    private TextField quantity;
    @FXML
    private ComboBox expenseType;
    @FXML
    private TextField itemName;
    @FXML
    private TextField cost;
    @FXML
    private TextArea itemDescription;
    @FXML
    private Button saveLpo;
    
    LocalOrderPurchase localOrderPurchase;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        expenseType.getItems().addAll("Stationery","Funiture","Medical","Electronic","Others");
        saveLpo.setOnAction(onActionListener);
      //the object must not be null
       if(LocalOrderPurchase.getAction()=="Edit"){
         fillLPO(); 
           
        }else{
             
        }
       
    }    

    
    
    public void fillLPO(){
       
        expenseType.getSelectionModel().select(LocalOrderPurchase.getExpenseTypeEdit());
        itemName.setText(LocalOrderPurchase.getExpenseTypeEdit());
        itemDescription.setText(LocalOrderPurchase.getDescriptionEdit());
        quantity.setText("" + LocalOrderPurchase.getQuantityEdit());
        cost.setText("" + LocalOrderPurchase.getCostEdit());


    }
    private String actionPem(){
       
        return LocalOrderPurchase.getAction();
    }
    
      class MouseActionListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {

        }
    }

    class OnActionListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            
             if(saveLpo==e.getSource()){
                 if(expenseType.getSelectionModel().getSelectedItem().toString()==null){
                      DISPLAY_MESSAGE.showInfoDialog(saveLpo.getScene().getWindow(), "Please select expense type", NotificationType.ERROR);
                     
                 }
                 else if(itemName.getText().isEmpty()){
                    DISPLAY_MESSAGE.showInfoDialog(saveLpo.getScene().getWindow(), "Please enter item name", NotificationType.ERROR);
                 }
                 else if(itemDescription.getText().isEmpty()){
                     DISPLAY_MESSAGE.showInfoDialog(saveLpo.getScene().getWindow(), "Please enter item description", NotificationType.ERROR);
                   
                 }
                 else if(quantity.getText().isEmpty() || quantity.getText().matches("[0-9]*")){
                     DISPLAY_MESSAGE.showInfoDialog(saveLpo.getScene().getWindow(), "Please quantity must not be empty and must be numeric value", NotificationType.ERROR);
                     
                 }
                 else if(cost.getText().isEmpty() || quantity.getText().matches("[0-9]*")){
                     DISPLAY_MESSAGE.showInfoDialog(saveLpo.getScene().getWindow(), "Please cost must not be empty and must be numeric value", NotificationType.ERROR);
                     
                 }
                 else{
                   localOrderPurchase = new LocalOrderPurchase();
                       localOrderPurchase.setExpenseType(expenseType.getSelectionModel().getSelectedItem().toString());
                       localOrderPurchase.setItemName(itemName.getText());
                       localOrderPurchase.setDescription(itemDescription.getText());
                       localOrderPurchase.setQuantity(Integer.parseInt(quantity.getText()));
                       localOrderPurchase.setCost(Double.parseDouble(cost.getText()));  
                 
                if(LocalOrderPurchase.getAction().equalsIgnoreCase("Edit")){
                    
                  
                      if(localOrderPurchase.update()){
                       Stage stage = (Stage) saveLpo.getScene().getWindow();
                        stage.close();
                         
                        DISPLAY_MESSAGE.showInfoDialog(saveLpo.getScene().getWindow(), "Successfully Update LPO", NotificationType.INFORMATION);
                       ((Node)(e.getSource())).getScene().getWindow().hide(); 
                    } else {
                        DISPLAY_MESSAGE.showInfoDialog(saveLpo.getScene().getWindow(), "Failed to Update LPO", NotificationType.ERROR);
                    }
                      
                  }
                  else{
                    localOrderPurchase = new LocalOrderPurchase();
                       localOrderPurchase.setExpenseType(expenseType.getSelectionModel().getSelectedItem().toString());
                       localOrderPurchase.setItemName(itemName.getText());
                       localOrderPurchase.setDescription(itemDescription.getText());
                       localOrderPurchase.setQuantity(Integer.parseInt(quantity.getText()));
                       localOrderPurchase.setCost(Double.parseDouble(cost.getText()));
                   if(localOrderPurchase.save()){       
                       
                       
                      Stage stage = (Stage) saveLpo.getScene().getWindow();
                        stage.close();
                         
                        DISPLAY_MESSAGE.showInfoDialog(saveLpo.getScene().getWindow(), "Successfully Saved LPO", NotificationType.INFORMATION);
                       ((Node)(e.getSource())).getScene().getWindow().hide(); 
                    } else {
                        DISPLAY_MESSAGE.showInfoDialog(saveLpo.getScene().getWindow(), "Failed to save LPO", NotificationType.ERROR);
                    }   
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
