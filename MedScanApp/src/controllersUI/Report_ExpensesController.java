/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllersUI;

import static com.solutions.entorno.utilities.SystemVariables.SYSTEM_DATE;
import static com.solutions.entorno.utilities.SystemVariables.UILoader;
import controllers.Expenses;
import controllers.InternalRequisition;
import controllers.LocalOrderPurchase;
import controllers.ReportsController.ExependitureReport;
import static controllers.ReportsController.ExependitureReport.generateExpensesReport;
import static controllers.ReportsController.ExependitureReport.generateInternalReqReport;
import static controllers.ReportsController.ExependitureReport.generateLPOReqReport;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author YoungGucha
 */
public class Report_ExpensesController implements Initializable {
    OnActionListener onActionListener = new OnActionListener();
    MouseActionListener mouseActionListener = new MouseActionListener();
    OnSelectionListener onSelectionListener = new OnSelectionListener();
    @FXML
    private AnchorPane anchorParent;
    @FXML
    private Tab expensesTab;
    @FXML
    private AnchorPane wrapperExpenses;
    @FXML
    private TableView<?> expensesTV;
    @FXML
    private ComboBox expensesSortByType;
    @FXML
    private ComboBox expensesSortByYear;
    @FXML
    private Tab inernalRequisitionTab;
    @FXML
    private AnchorPane wrapperInernalRequisition;
    @FXML
    private TableView inernalRequisitionTV;
    @FXML
    private ComboBox inernalRequisitionSortByType;
    @FXML
    private ComboBox inernalRequisitionSortByYear;
    @FXML
    private ComboBox inernalRequisitionSortByMonth;
    @FXML
    private TextField inernalRequisitionTotal;
    @FXML
    private Tab lpoTab;
    @FXML
    private AnchorPane wrapperlpo;
    @FXML
    private TableView lpoTV;
    @FXML
    private ComboBox  lpoSortByType;
    @FXML
    private ComboBox lpoSortByYear;
    @FXML
    private ComboBox lpoSortByMonth;
    Expenses expense;
    LocalOrderPurchase localOrderPurchase;
    InternalRequisition requisition;
    @FXML
    private ComboBox expensesSortByMonth;
    String type="";
    String year="";
    String month="";
    @FXML
    private TextField totalExpenseAmount;
    @FXML
    private TextField totalLopAmount;
    @FXML
    private Button expensesPDF;
    @FXML
    private Button internalReqPDF;
    @FXML
    private Button lopPDF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     expensesSortByType.getItems().addAll("ALL","Salary");
        expensesSortByYear.getItems().addAll("2016","2017");
        expensesSortByMonth.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12");
        
        inernalRequisitionSortByType.getItems().addAll("ALL","Stationery");
        inernalRequisitionSortByYear.getItems().addAll("2016","2017");
        inernalRequisitionSortByMonth.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12");
        
        lpoSortByType.getItems().addAll("ALL","Stationery");
        lpoSortByYear.getItems().addAll("2016","2017");
        lpoSortByMonth.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12");
        
        expensesSortByType.setOnAction(onActionListener);
        expensesSortByYear.setOnAction(onActionListener);
        expensesSortByMonth.setOnAction(onActionListener);
        
        inernalRequisitionSortByType.setOnAction(onActionListener);
        inernalRequisitionSortByMonth.setOnAction(onActionListener);
        inernalRequisitionSortByYear.setOnAction(onActionListener);
        
        lpoSortByType.setOnAction(onActionListener);
        expensesSortByYear.setOnAction(onActionListener);
        expensesSortByMonth.setOnAction(onActionListener);
        
        expensesPDF.setOnAction(onActionListener);
        internalReqPDF.setOnAction(onActionListener);
        lopPDF.setOnAction(onActionListener);
        
         expense=new Expenses();
        localOrderPurchase =new LocalOrderPurchase();
        requisition=new InternalRequisition();
        loadExpenseTV("","","");
       loadLPOTV("","","");
        loadInternalReq("","","");
    }    
     public void loadExpenseTV(String type,String year,String month){
       expensesTV = expense.loadExpensesReport( type, year, month);
        UILoader.loadTable(wrapperExpenses, expensesTV);
        totalExpenseAmount.setText(""+Expenses.getTotalExpense());
         
    }
     public void loadLPOTV(String type,String year,String month){
       lpoTV = localOrderPurchase.loadLocalOrderPurchaseReport( type, year, month);
        UILoader.loadTable(wrapperlpo, lpoTV);
    }
     public void loadInternalReq(String type,String year,String month){
       inernalRequisitionTV = requisition.loadInternalReport( type, year, month);
        UILoader.loadTable(wrapperInernalRequisition, inernalRequisitionTV);
    }
     
     
     class MouseActionListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {


        }
    }

    class OnActionListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
        if(expensesSortByType==e.getSource()){
           type= expensesSortByType.getSelectionModel().getSelectedItem().toString();
            loadExpenseTV(type, year, month);
           totalExpenseAmount.setText(""+Expenses.getTotalExpense());
        }
          if(expensesSortByYear==e.getSource()){
           year= expensesSortByYear.getSelectionModel().getSelectedItem().toString();
            loadExpenseTV(type, year, month);
            totalExpenseAmount.setText(""+expense.getTotalExpense());
        }  
          if(expensesSortByMonth==e.getSource()){
           month= expensesSortByMonth.getSelectionModel().getSelectedItem().toString();
            loadExpenseTV(type, year, month);
            totalExpenseAmount.setText(""+Expenses.getTotalExpense());
        }  
       
// internal requisition
        if(inernalRequisitionSortByType==e.getSource()){
           type= inernalRequisitionSortByType.getSelectionModel().getSelectedItem().toString();
            loadInternalReq(type, year, month);
           inernalRequisitionTotal.setText(""+InternalRequisition.getTotalLPO());
        }
          if(inernalRequisitionSortByYear==e.getSource()){
           year= inernalRequisitionSortByYear.getSelectionModel().getSelectedItem().toString();
            loadInternalReq(type, year, month);
            inernalRequisitionTotal.setText(""+InternalRequisition.getTotalLPO());
        }  
          if(inernalRequisitionSortByMonth==e.getSource()){
           month= inernalRequisitionSortByMonth.getSelectionModel().getSelectedItem().toString();
            loadInternalReq(type, year, month);
            inernalRequisitionTotal.setText(""+InternalRequisition.getTotalLPO());
        }  
        
           if(lpoSortByType==e.getSource()){
           type= lpoSortByType.getSelectionModel().getSelectedItem().toString();
            loadLPOTV(type, year, month);
           totalLopAmount.setText(""+LocalOrderPurchase.getTotalRequisition());
        }
          if(lpoSortByYear==e.getSource()){
           year= lpoSortByYear.getSelectionModel().getSelectedItem().toString();
            loadLPOTV(type, year, month);
            totalLopAmount.setText(""+LocalOrderPurchase.getTotalRequisition());
        }  
          if(lpoSortByMonth==e.getSource()){
           month= lpoSortByMonth.getSelectionModel().getSelectedItem().toString();
            loadLPOTV(type, year, month);
            totalLopAmount.setText(""+LocalOrderPurchase.getTotalRequisition());
        }  
          
          
          //==========================Generate Reports expenses====================
                
              if(expensesPDF==e.getSource()){
                 generateExpensesReport(expensesTV ,"Payments" + SYSTEM_DATE + ".pdf","TOATAL PAYMENTS AS AT " + SYSTEM_DATE,
                         totalExpenseAmount.getText());
                
             }  
               
             if(internalReqPDF==e.getSource()){
                 generateInternalReqReport(inernalRequisitionTV ,"Payments" + SYSTEM_DATE + ".pdf","TOATAL PAYMENTS AS AT " + SYSTEM_DATE,
                         inernalRequisitionTotal.getText());  
              }
              if(lopPDF==e.getSource()){
                generateLPOReqReport(lpoTV ,"Payments" + SYSTEM_DATE + ".pdf","TOATAL PAYMENTS AS AT " + SYSTEM_DATE,
                         totalLopAmount.getText());  
              }
         }
    }

    class OnSelectionListener implements EventHandler<Event> {

        @Override
        public void handle(Event e) {
            
        }

    }
}
