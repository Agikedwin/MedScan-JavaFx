/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllersUI;

import static com.solutions.entorno.utilities.SystemVariables.DISPLAY_MESSAGE;
import static com.solutions.entorno.utilities.SystemVariables.UILoader;
import com.solutions.entorno.utilities.dialogs.NotificationType;
import controllers.Expenses;
import controllers.LocalOrderPurchase;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
public class AddExpensesController implements Initializable {
    OnActionListener onActionListener = new OnActionListener();
    MouseActionListener mouseActionListener = new MouseActionListener();
    OnSelectionListener onSelectionListener = new OnSelectionListener();
    @FXML
    private HBox refereeDoctorid;
    @FXML
    private Button saveExpense;
    @FXML
    private TextField expenseCost;
    @FXML
    private ComboBox expenseType;
    @FXML
    private TextField itemName;
    @FXML
    private Label Amount;
    @FXML
    private TextArea expenseDescription;
    @FXML
    private DatePicker expenseDate;
Expenses expense;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        expenseType.getItems().addAll("Rent","Salary","Electricity","Others");
        saveExpense.setOnAction(onActionListener);
       
    }    
    
    
     class MouseActionListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {


        }
    }

    class OnActionListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            String dateIncurred=null;
            if(expenseDate==e.getSource()){
                 dateIncurred=""+(LocalDate) expenseDate.getValue();
            }

            if (saveExpense==e.getSource()) {
              
               if(expenseType.getSelectionModel().getSelectedItem()==null){
                 DISPLAY_MESSAGE.showInfoDialog(saveExpense.getScene().getWindow(), "Please select expense type", NotificationType.ERROR);
               }
               else if(itemName.getText().isEmpty()){
                 DISPLAY_MESSAGE.showInfoDialog(saveExpense.getScene().getWindow(), "Please Enter Expense name", NotificationType.ERROR);
                   
               }
               else if(expenseDescription.getText().isEmpty()){
                 DISPLAY_MESSAGE.showInfoDialog(saveExpense.getScene().getWindow(), "Please Enter Expense description", NotificationType.ERROR);
              }
               else if(expenseDate.getValue()==null || dateIncurred==null ){
                    DISPLAY_MESSAGE.showInfoDialog(saveExpense.getScene().getWindow(), "Please select the date expense was incurred", NotificationType.ERROR);
                
               }
               
               else{
               expense=new Expenses();
               expense.setExpenseType(expenseType.getSelectionModel().getSelectedItem().toString());
               expense.setExpenseName(itemName.getText());
               expense.setDescription(expenseDescription.getText());
               expense.setAmount(Double.parseDouble(expenseCost.getText()));
               expense.setDateIncurred(dateIncurred);
               if(expense.save()){
                   Stage stage = (Stage) saveExpense.getScene().getWindow();
                        stage.close();
                         
                        DISPLAY_MESSAGE.showInfoDialog(saveExpense.getScene().getWindow(), "Successfully Saved Expense", NotificationType.INFORMATION);
                       ((Node)(e.getSource())).getScene().getWindow().hide(); 
                    } else {
                        DISPLAY_MESSAGE.showInfoDialog(saveExpense.getScene().getWindow(), "Failed to save Expense", NotificationType.ERROR);
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
