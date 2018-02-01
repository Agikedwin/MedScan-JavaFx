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
import controllers.Payments;
import controllers.RefereeCommission;
import controllers.ReportsController.PatientsServicesReport;
import static controllers.ReportsController.PatientsServicesReport.generateCustomeFinanceReport;
import static controllers.ReportsController.PatientsServicesReport.generateFinanceReport;
import static controllers.ReportsController.PatientsServicesReport.generatePaymentsReport;
import static controllers.ReportsController.PatientsServicesReport.generateRefCommissionReport;
import static controllers.ReportsController.PatientsServicesReport.generateServicesReport;
import controllers.Services;
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
import models.RefereeCommisionModel;

/**
 * FXML Controller class
 *
 * @author YoungGucha
 */
public class Report_PatientsController implements Initializable {
    OnActionListener onActionListener = new OnActionListener();
    MouseActionListener mouseActionListener = new MouseActionListener();
    OnSelectionListener onSelectionListener = new OnSelectionListener();
    @FXML
    private AnchorPane anchorParent;
    @FXML
    private Tab servicesOfferedTab;
    @FXML
    private AnchorPane wrapperServicesOffered;
    @FXML
    private TableView<?> servicesOfferedTV;
    @FXML
    private ComboBox serviceType;
    @FXML
    private ComboBox serviceYear;
    @FXML
    private ComboBox serviceMonth;

    /**
     * Initializes the controller class.
     */
    Services serv;
    RefereeCommission refereeCommission;
    @FXML
    private Tab refereeCommissionTab;
    Payments payment;
    @FXML
    private AnchorPane wrapperReferees;
    @FXML
    private TableView<?> RefereesTv;
    @FXML
    private Tab servicesPaymentTab;
    @FXML
    private AnchorPane wrapperservicesPayment;
    @FXML
    private TableView servicesPaymentTV;
    @FXML
    private ComboBox servicesPaymentType;
    @FXML
    private ComboBox servicesPaymentYear;
    @FXML
    private ComboBox servicesPaymentMonth;
    @FXML
    private TextField financeTotalExpense;
    @FXML
    private TextField financeTotalReq;
    @FXML
    private TextField financeTotalLPO;
    @FXML
    private TextField financeTotalIncome;
    @FXML
    private TextField financeTotalNetIncome;
    @FXML
    private TextField financeTotalExpenditure;
    @FXML
    private ComboBox financeSortByMonth;
    @FXML
    private ComboBox financeSortByYear;
    Expenses exp;
    InternalRequisition internalRequisition;
    LocalOrderPurchase localOrderPurchase;
    @FXML
    private TextField totalServiceCost;
    @FXML
    private TextField totalCommission;
    @FXML
    private TextField totalPayment;
    String type="";
            String year="";
            String month="";
    @FXML
    private ComboBox refereeSortBy;
    @FXML
    private ComboBox refereeSortByYear;
    @FXML
    private ComboBox refereeSortByMonth;
    @FXML
    private TextField totalDiscount;
    @FXML
    private TextField netAmount;
    @FXML
    private TextField totalDiscountAmount;
    @FXML
    private Button sevicePDF;
    @FXML
    private Button refcommissionPDF;
    @FXML
    private Button paymentsPDF;
    @FXML
    private Tab financeTab;
    @FXML
    private Button financePDF;
   
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
         
        serviceType.getItems().addAll("ALL","XRAY","ALTRA-SOUND","CT-SCAN");
        serviceYear.getItems().addAll("2016","2017");
        serviceMonth.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12");
        
        servicesPaymentType.getItems().addAll("ALL","CASH","BANK","INSURANCE");
        servicesPaymentYear.getItems().addAll("2016","2017");
        servicesPaymentMonth.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12");
        
        refereeSortBy.getItems().addAll("ALL","MARK BET","SIMON KARANJA");
        refereeSortByYear.getItems().addAll("2016","2017");
        refereeSortByMonth.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12");
        
        financeSortByYear.getItems().addAll("2016","2017");
        financeSortByMonth.getItems().addAll("1","2","3","4","5","6","7","8","9","10","11","12");
        
       serv=new Services();
       refereeCommission=new RefereeCommission();
       internalRequisition =new InternalRequisition();
       localOrderPurchase=new LocalOrderPurchase();
       payment=new Payments();
       exp=new  Expenses();
       loadServicesOffered("","","");
       loadRefereeCommission("","","");
       loadPayments("","","");
       finance("","","");
       
       serviceType.setOnAction(onActionListener);
       serviceYear.setOnAction(onActionListener);
       serviceMonth.setOnAction(onActionListener);
       
       servicesPaymentType.setOnAction(onActionListener);
        servicesPaymentYear.setOnAction(onActionListener);
        servicesPaymentMonth.setOnAction(onActionListener);
        
        financeSortByYear.setOnAction(onActionListener);
        financeSortByMonth.setOnAction(onActionListener);
        
        sevicePDF.setOnAction(onActionListener);
        refcommissionPDF.setOnAction(onActionListener);
        financePDF.setOnAction(onActionListener);
        paymentsPDF.setOnAction(onActionListener);
        
        
    } 
    
    public void loadServicesOffered(String serviceType,String year,String month){
    
  servicesOfferedTV=serv.loadServicesReport(serviceType,year,month);
  UILoader.loadTable(wrapperServicesOffered, servicesOfferedTV);
  totalServiceCost.setText(""+serv.getTotalServiceCost("","",""));
  
    }
    public void loadRefereeCommission(String serviceType,String year,String month){
        refereeSortBy.setOnAction(onActionListener);
        refereeSortByYear.setOnAction(onActionListener);
        refereeSortByMonth.setOnAction(onActionListener);
      RefereesTv=refereeCommission.loadServicesCommissionReport(serviceType,year,month);
      UILoader.loadTable(wrapperReferees, RefereesTv);  
    }
     public void loadPayments(String serviceType,String year,String month){
      servicesPaymentTV=payment.loadPaymentsReport(serviceType,year,month);
  UILoader.loadTable(wrapperservicesPayment, servicesPaymentTV); 
  
    }
     public void loadPaymentsCash(String serviceType,String year,String month){
      servicesPaymentTV=payment.loadCashPayments(serviceType,year,month);
  UILoader.loadTable(wrapperservicesPayment, servicesPaymentTV);  
    
    }
     public void loadPaymentsBank(String serviceType,String year,String month){
      servicesPaymentTV=payment.loadPaymentBank(serviceType,year,month);
  UILoader.loadTable(wrapperservicesPayment, servicesPaymentTV);  
    }
     public void loadPaymentsInsurance(String serviceType,String year,String month){
      servicesPaymentTV=payment.loadCashPaymentsInsurance(serviceType,year,month);
  UILoader.loadTable(wrapperservicesPayment, servicesPaymentTV);  
    }
     public void finance(String type,String year,String month){
         double discount=0;
         double totalExpense=exp.totalExpense( type, year, month);
         double totalReq=internalRequisition.totalRequisition(type, year, month);
         double totalLOP=localOrderPurchase.totalLOP(type, year, month);
         double totalAmount=payment.totalPayemnt(type, year, month);
          double totalExpediture=totalExpense+totalReq+totalLOP;
         double netIncome=totalAmount-(totalExpense+totalReq+totalLOP);
         
         loadPayments(type,year,month);
          discount=payment.getTotalAllDiscount();
          totalDiscountAmount.setText(""+discount);
          System.out.println("discount "+discount);
         
         financeTotalExpense.setText(""+totalExpense);
         financeTotalReq.setText(""+totalReq);
         financeTotalLPO.setText(""+totalLOP);
         financeTotalExpenditure.setText(""+totalExpediture);
         financeTotalIncome.setText(""+totalAmount);
         financeTotalNetIncome.setText(""+netIncome);
         
         
     }
      class MouseActionListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {

        }
    }

    class OnActionListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            
            if(serviceType==e.getSource()){
                type=serviceType.getSelectionModel().getSelectedItem().toString();
                loadServicesOffered(type,year,month);
                totalServiceCost.setText(""+serv.getTotalServiceCost(type,year,month));
            }
            if(serviceYear==e.getSource()){
             year=serviceYear.getSelectionModel().getSelectedItem().toString();
             loadServicesOffered(type,year,month);
                totalServiceCost.setText(""+serv.getTotalServiceCost(type,year,month));
         
            } 
            if(serviceMonth==e.getSource()){
              month=serviceMonth.getSelectionModel().getSelectedItem().toString();
              loadServicesOffered(type,year,month);
              totalServiceCost.setText(""+serv.getTotalServiceCost(type,year,month));
         
            }
            //Payments
             
            if(servicesPaymentType==e.getSource()){
               double totalDis=0,discount=0,totapPay=0, netIncome=0;
                type=servicesPaymentType.getSelectionModel().getSelectedItem().toString();
                 if(type=="ALL"){
                   loadPayments(type,year,month);
                   totalDis=payment.getTotalAllDiscount();
                   totalDiscount.setText(""+totalDis);
                   //payment.fetchPaymentsReport(type,year,month);
                   totalPayment.setText(""+Payments.getTotalAllPayments());
                   netIncome=Payments.getTotalAllPayments()-totalDis;
                    netAmount.setText(""+netIncome);
                 }
                 if(type=="CASH"){
                     loadPaymentsCash(type,year,month);
                      totalDis=payment.getTotalAllDiscount();
                   totalDiscount.setText(""+totalDis);
                   //payment.fetchPaymentsReport(type,year,month);
                   totalPayment.setText(""+Payments.getTotalAllPayments());
                   netIncome=Payments.getTotalAllPayments()-totalDis;
                    netAmount.setText(""+netIncome); 
                 }
                 if(type=="BANK"){
                    loadPaymentsBank(type,year,month);
                     totalDis=payment.getTotalAllDiscount();
                   totalDiscount.setText(""+totalDis);
                   //payment.fetchPaymentsReport(type,year,month);
                   totalPayment.setText(""+Payments.getTotalAllPayments());
                   netIncome=Payments.getTotalAllPayments()-totalDis;
                    netAmount.setText(""+netIncome);    
                 }
                 if(type=="INSURANCE"){
                    loadPaymentsInsurance(type,year,month);
                     totalDis=payment.getTotalAllDiscount();
                   totalDiscount.setText(""+totalDis);
                   //payment.fetchPaymentsReport(type,year,month);
                   totalPayment.setText(""+Payments.getTotalAllPayments());
                   netIncome=Payments.getTotalAllPayments()-totalDis;
                    netAmount.setText(""+netIncome); 
                 }
                
            }
            if(servicesPaymentYear==e.getSource()){
                 double totalDis=0,discount=0,totapPay=0, netIncome=0;
               type=servicesPaymentType.getSelectionModel().getSelectedItem().toString();
                 if(type=="ALL"){
                   loadPayments(type,year,month);
                   totalDis=payment.getTotalAllDiscount();
                   totalDiscount.setText(""+totalDis);
                   //payment.fetchPaymentsReport(type,year,month);
                   totalPayment.setText(""+Payments.getTotalAllPayments());
                   netIncome=Payments.getTotalAllPayments()-totalDis;
                    netAmount.setText(""+netIncome);
                 }
                 if(type=="CASH"){
                     loadPaymentsCash(type,year,month);
                      totalDis=payment.getTotalAllDiscount();
                   totalDiscount.setText(""+totalDis);
                   //payment.fetchPaymentsReport(type,year,month);
                   totalPayment.setText(""+Payments.getTotalAllPayments());
                   netIncome=Payments.getTotalAllPayments()-totalDis;
                    netAmount.setText(""+netIncome); 
                 }
                 if(type=="BANK"){
                    loadPaymentsBank(type,year,month);
                     totalDis=payment.getTotalAllDiscount();
                   totalDiscount.setText(""+totalDis);
                   //payment.fetchPaymentsReport(type,year,month);
                   totalPayment.setText(""+Payments.getTotalAllPayments());
                   netIncome=Payments.getTotalAllPayments()-totalDis;
                    netAmount.setText(""+netIncome);    
                 }
                 if(type=="INSURANCE"){
                    loadPaymentsInsurance(type,year,month);
                     totalDis=payment.getTotalAllDiscount();
                   totalDiscount.setText(""+totalDis);
                   //payment.fetchPaymentsReport(type,year,month);
                   totalPayment.setText(""+Payments.getTotalAllPayments());
                   netIncome=Payments.getTotalAllPayments()-totalDis;
                    netAmount.setText(""+netIncome); 
                 }
                
            } 
            if(servicesPaymentMonth==e.getSource()){
                 double totalDis=0,discount=0,totapPay=0, netIncome=0;
              month=servicesPaymentMonth.getSelectionModel().getSelectedItem().toString();
              if(type=="ALL"){
                   loadPayments(type,year,month);
                   totalDis=payment.getTotalAllDiscount();
                   totalDiscount.setText(""+totalDis);
                   //payment.fetchPaymentsReport(type,year,month);
                   totalPayment.setText(""+Payments.getTotalAllPayments());
                   netIncome=Payments.getTotalAllPayments()-totalDis;
                    netAmount.setText(""+netIncome);
                 }
                 if(type=="CASH"){
                     loadPaymentsCash(type,year,month);
                      totalDis=payment.getTotalAllDiscount();
                   totalDiscount.setText(""+totalDis);
                   //payment.fetchPaymentsReport(type,year,month);
                   totalPayment.setText(""+Payments.getTotalAllPayments());
                   netIncome=Payments.getTotalAllPayments()-totalDis;
                    netAmount.setText(""+netIncome); 
                 }
                 if(type=="BANK"){
                    loadPaymentsBank(type,year,month);
                     totalDis=payment.getTotalAllDiscount();
                   totalDiscount.setText(""+totalDis);
                   //payment.fetchPaymentsReport(type,year,month);
                   totalPayment.setText(""+Payments.getTotalAllPayments());
                   netIncome=Payments.getTotalAllPayments()-totalDis;
                    netAmount.setText(""+netIncome);    
                 }
                 if(type=="INSURANCE"){
                    loadPaymentsInsurance(type,year,month);
                     totalDis=payment.getTotalAllDiscount();
                   totalDiscount.setText(""+totalDis);
                   //payment.fetchPaymentsReport(type,year,month);
                   totalPayment.setText(""+Payments.getTotalAllPayments());
                   netIncome=Payments.getTotalAllPayments()-totalDis;
                    netAmount.setText(""+netIncome); 
                 }
                  }
                 // referee commission starts 
                 
                
                 if(refereeSortBy==e.getSource()){
                type=refereeSortBy.getSelectionModel().getSelectedItem().toString();
                loadRefereeCommission(type,year,month);
                totalCommission.setText(""+refereeCommission.getTotalComAmount());
            }
            if(refereeSortByYear==e.getSource()){
             year=refereeSortByYear.getSelectionModel().getSelectedItem().toString();
              loadRefereeCommission(type,year,month);
                totalCommission.setText(""+refereeCommission.getTotalComAmount());
         
            } 
            if(refereeSortByMonth==e.getSource()){
                
              month=refereeSortByMonth.getSelectionModel().getSelectedItem().toString();
               loadRefereeCommission(type,year,month);
              totalCommission.setText(""+refereeCommission.getTotalComAmount());
                System.out.println("the amount "+refereeCommission.getTotalComAmount());
            }
            
            //======finance report==========
             if(financeSortByYear==e.getSource()){
                 year=financeSortByYear.getSelectionModel().getSelectedItem().toString();
                 finance(type,year,month);
             }  
             if(financeSortByMonth==e.getSource()){
                 year=financeSortByMonth.getSelectionModel().getSelectedItem().toString();
                 finance(type,year,month);
             } 
             
             
             
             
             
             //==========================Generate Reports services====================
             
             if(sevicePDF==e.getSource()){
                 
                 generateServicesReport(servicesOfferedTV,"SERVICES OFFERED" + SYSTEM_DATE + ".pdf", 
                         "SERVICES OFFERED AS AT " + SYSTEM_DATE," TOTAL COST AS AT "  +SYSTEM_DATE+ " : "+totalServiceCost.getText()+"");
            
             }
             
              if(refcommissionPDF==e.getSource()){
                 
                  generateRefCommissionReport(RefereesTv ,"Referee Commission" + SYSTEM_DATE + ".pdf", 
                         "TOATAL COMMISSION AS AT " + SYSTEM_DATE," TOTAL COMMISSION "  +SYSTEM_DATE+ " : "+totalCommission.getText()+"");
                
             }   
              
              if(paymentsPDF==e.getSource()){
                
                 generatePaymentsReport( servicesPaymentTV,"Payments" + SYSTEM_DATE + ".pdf", "TOATAL PAYMENTS AS AT " + SYSTEM_DATE+" "," TOTAL NET INCOME "  +SYSTEM_DATE+ " : "+netAmount.getText()+"",servicesPaymentType.getSelectionModel().getSelectedItem().toString());
                
             }  
              
              if(financePDF==e.getSource()){
                
                         
                
//                 generateFinanceReport(RefereesTv ,"Payments" + SYSTEM_DATE + ".pdf","TOATAL PAYMENTS AS AT " + SYSTEM_DATE,
//                         financeTotalExpense.getText(),financeTotalReq.getText(),financeTotalLPO.getText(),
//                         financeTotalExpenditure.getText(), financeTotalIncome.getText(),totalDiscountAmount.getText(),
//                         financeTotalNetIncome.getText());
                 
                 generateCustomeFinanceReport( "Payments" + SYSTEM_DATE + ".pdf","TOATAL PAYMENTS AS AT " + SYSTEM_DATE,
                         financeTotalExpense.getText(),financeTotalReq.getText(),financeTotalLPO.getText(),
                         financeTotalExpenditure.getText(), financeTotalIncome.getText(),totalDiscountAmount.getText(),
                         financeTotalNetIncome.getText());
                
             }  
              
               
        }    

    }

    class OnSelectionListener implements EventHandler<Event> {

        @Override
        public void handle(Event e) {

        }

    }
    
}
