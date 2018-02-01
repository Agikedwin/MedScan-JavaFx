/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllersUI;

import com.solutions.entorno.utilities.SystemVariables;
import static com.solutions.entorno.utilities.SystemVariables.DISPLAY_MESSAGE;
import static com.solutions.entorno.utilities.SystemVariables.SYSTEM_DATE;
import com.solutions.entorno.utilities.dialogs.LookupVar;
import com.solutions.entorno.utilities.dialogs.NotificationType;
import controllers.Bank;
import static controllers.Bank.banksTV;
import controllers.Insurance;
import static controllers.Insurance.InsuranceTV;
import controllers.PatientsRequest;
import controllers.Payments;
import controllers.ReportsController.PrintReceipt;
import controllers.Services;
import java.awt.Color;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.BorderFactory;

/**
 * FXML Controller class
 *
 * @author YoungGucha
 */
public class ServicePaymentsController implements Initializable {

    @FXML
    private HBox refereeDoctorid;
    @FXML
    private TextField bankName;
    @FXML
    private ComboBox paymentMode;
    @FXML
    private TextField chequeNo;
    @FXML
    private TextField insuranceNo;
    @FXML
    private TextField creditCardNo;
    @FXML
    private TextField insuranceName;
    @FXML
    private TextArea servicesOffered;
    @FXML
    private TextField commissionDue;
    @FXML
    private Button savePayments;
    @FXML
    private TextField discount;
    Payments payments;
    Services service;
    OnActionListener onActionListener = new OnActionListener();
    MouseActionListener mouseActionListener = new MouseActionListener();
    @FXML
    private Button selectBank;
    @FXML
    private Button selectInsurance;
    Bank lookupBanks;
    Insurance lookupInsurance;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        payments = new Payments();
        service = new Services();
        lookupBanks = new Bank();
        lookupInsurance = new Insurance();
        paymentMode.getItems().addAll("CASH ", "BANK", "INSURANCE");;
        loadServicesAndCost();
        paymentMode.setOnAction(onActionListener);
        savePayments.setOnAction(onActionListener);

        selectBank.setOnAction(onActionListener);
        selectInsurance.setOnAction(onActionListener);

    }

    private void loadServicesAndCost() {
        ArrayList<String> services = service.fetchServicesOffered(PatientsRequest.globalPatientId, service.maxServiceCount(PatientsRequest.globalPatientId));
        commissionDue.setText("" + service.getTotalCost(PatientsRequest.globalPatientId));

        for (String servce : services) {
            servicesOffered.appendText(servce + "\n");
        }

    }

    class OnActionListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {
            // set editeble
            if (paymentMode == e.getSource() && paymentMode.getSelectionModel().getSelectedIndex() == 0) {

                bankName.setEditable(false);
                paymentMode.setEditable(false);
                chequeNo.setEditable(false);
                insuranceNo.setEditable(false);
                creditCardNo.setEditable(false);
                insuranceName.setEditable(false);
                servicesOffered.setEditable(false);
                discount.setStyle("-fx-border-color:green;-fx-border-width:1px");

                insuranceNo.setStyle(null);
                insuranceName.setStyle(null);
                bankName.setStyle(null);
                creditCardNo.setStyle(null);
                chequeNo.setStyle(null);

            }
            if (paymentMode == e.getSource() && paymentMode.getSelectionModel().getSelectedIndex() == 1) {

                insuranceName.setEditable(false);
                insuranceNo.setEditable(false);
                bankName.setEditable(true);
                chequeNo.setEditable(true);
                creditCardNo.setEditable(true);
                bankName.setStyle("-fx-border-color:green;-fx-border-width:1px");
                creditCardNo.setStyle("-fx-border-color:green;-fx-border-width:1px");
                chequeNo.setStyle("-fx-border-color:green;-fx-border-width:1px");
                discount.setStyle("-fx-border-color:green;-fx-border-width:1px");
                insuranceNo.setStyle(null);
                insuranceName.setStyle(null);

            }
            if (paymentMode == e.getSource() && paymentMode.getSelectionModel().getSelectedIndex() == 2) {

                insuranceName.setEditable(true);
                insuranceNo.setEditable(true);
                bankName.setEditable(false);
                chequeNo.setEditable(false);
                creditCardNo.setEditable(false);
                insuranceNo.setStyle("-fx-border-color:green;-fx-border-width:1px");
                insuranceName.setStyle("-fx-border-color:green;-fx-border-width:1px");
                discount.setStyle("-fx-border-color:green;-fx-border-width:1px");

                bankName.setStyle(null);
                creditCardNo.setStyle(null);
                chequeNo.setStyle(null);

            }
          //ends editable

            //start saving
            double totalDue = Double.parseDouble(commissionDue.getText());

            if (savePayments == e.getSource() && paymentMode.getSelectionModel().getSelectedItem() == null) {

                DISPLAY_MESSAGE.showInfoDialog(savePayments.getScene().getWindow(), "Please Select the Payment Mode", NotificationType.ERROR);
            } else {

                if (savePayments == e.getSource() && paymentMode.getSelectionModel().getSelectedIndex() == 0) {

                    if (totalDue <= 0) {
                        DISPLAY_MESSAGE.showInfoDialog(savePayments.getScene().getWindow(), "Please Add Services Requested By the Patient before you proceed", NotificationType.ERROR);

                    } else {

                        if (paymentMode.getSelectionModel().getSelectedIndex() < 0 || paymentMode.getSelectionModel().getSelectedItem() == null) {
                            DISPLAY_MESSAGE.showInfoDialog(savePayments.getScene().getWindow(), "Please select payment mode", NotificationType.ERROR);
                        } else {

                            String inputAmount = DISPLAY_MESSAGE.showInputDialog(savePayments.getScene().getWindow(), "Enter Amount Received", "0");
                            double amount = Double.parseDouble(inputAmount);
                            double discountAmount = 0;

                            payments.setPatientId(PatientsRequest.globalPatientId);
                            payments.setCashAmount(Double.parseDouble(commissionDue.getText()));
                            payments.setCashAmount(amount);
                            if (discount.getText().isEmpty()) {
                                discountAmount = 0;
                            } else {
                                discountAmount = Double.parseDouble(discount.getText());
                            }
                            payments.setCashDiscount(discountAmount);
                            double totalSserviceCost = Double.parseDouble(commissionDue.getText());
                            double total = (totalSserviceCost - discountAmount);
                            double balance = amount - total;

                            if (amount >= total) {
                                if (payments.saveCash()) {
                               Stage stage = (Stage) savePayments.getScene().getWindow();
                                   stage.close();
                                    DISPLAY_MESSAGE.showInfoDialog(savePayments.getScene().getWindow(), "Payment Successfully Completed", NotificationType.INFORMATION);
                                          ((Node)(e.getSource())).getScene().getWindow().hide(); 
                                    PrintReceipt.generateReceipt("Payments" + SYSTEM_DATE + ".pdf", "AGIK EDWIN", servicesOffered.getText(),
                                            commissionDue.getText(), paymentMode.getSelectionModel().getSelectedItem().toString(),
                                            discount.getText(), inputAmount, balance);

                                } else {
                                    DISPLAY_MESSAGE.showInfoDialog(savePayments.getScene().getWindow(), "Failed to Complete Cash Payment", NotificationType.ERROR);

                                }
                            } else {
                                DISPLAY_MESSAGE.showInfoDialog(savePayments.getScene().getWindow(), "The amount being received is not sufficient to pay for the services requested", NotificationType.INFORMATION);
                            }
                        }

                    }
                }

                if (savePayments == e.getSource() && paymentMode.getSelectionModel().getSelectedIndex() == 1) {
                    if (totalDue <= 0) {
                        DISPLAY_MESSAGE.showInfoDialog(savePayments.getScene().getWindow(), "Please Add Services Requested By the Patient before you proceed", NotificationType.ERROR);

                    } else {

                        if (paymentMode.getSelectionModel().getSelectedIndex() < 0) {

                            DISPLAY_MESSAGE.showInfoDialog(savePayments.getScene().getWindow(), "Please select payment mode", NotificationType.ERROR);
                        } else {

                            if (bankName.getText().isEmpty()) {

                                DISPLAY_MESSAGE.showInfoDialog(savePayments.getScene().getWindow(), "Please Click the button to select the bank", NotificationType.ERROR);

                            } else if (creditCardNo.getText().isEmpty() && chequeNo.getText().isEmpty()) {

                                DISPLAY_MESSAGE.showInfoDialog(savePayments.getScene().getWindow(), "Please enter cheque Number or Credit card number", NotificationType.ERROR);

                            } else {
                                String inputAmount = DISPLAY_MESSAGE.showInputDialog(savePayments.getScene().getWindow(), "Enter Amount Received", "0");

                                double amount = Double.parseDouble(inputAmount);
                                double discountAmount = 0;
                                payments.setPatientId(PatientsRequest.globalPatientId);
                                payments.setCashAmount(amount);
                                if (discount.getText().isEmpty()) {
                                    discountAmount = 0;
                                } else {
                                    discountAmount = Double.parseDouble(discount.getText());
                                }
                                payments.setCashDiscount(discountAmount);
                                payments.setBankName(lookupBanks.getBankName());
                                payments.setChequeNo(chequeNo.getText());
                                payments.setCreditCardNo(creditCardNo.getText());
//                               

                                double totalSserviceCost = Double.parseDouble(commissionDue.getText());
                                double total = (totalSserviceCost - discountAmount);
                                double balance = amount - total;
                                if (amount >= (totalSserviceCost + payments.getCashDiscount())) {
                                    if (payments.saveBank()) {
                                         Stage stage = (Stage) savePayments.getScene().getWindow();
                                   stage.close();
                                        DISPLAY_MESSAGE.showInfoDialog(savePayments.getScene().getWindow(), "Payment Successfully Completed", NotificationType.INFORMATION);
                                        ((Node)(e.getSource())).getScene().getWindow().hide(); 
                                        PrintReceipt.generateReceipt("Payments" + SYSTEM_DATE + ".pdf", "AGIK EDWIN", servicesOffered.getText(),
                                                commissionDue.getText(), paymentMode.getSelectionModel().getSelectedItem().toString(),
                                                discount.getText(), inputAmount, balance);

                                    } else {
                                        DISPLAY_MESSAGE.showInfoDialog(savePayments.getScene().getWindow(), "Failed to Complete Cash Payment", NotificationType.ERROR);

                                    }

                                } else {
                                    DISPLAY_MESSAGE.showInfoDialog(savePayments.getScene().getWindow(), "The amount being received is not sufficient to pay for the services requested", NotificationType.INFORMATION);
                                }
                            }

                        }
                    }

                }

                if (savePayments == e.getSource() && paymentMode.getSelectionModel().getSelectedIndex() == 2) {
                    if (totalDue <= 0) {
                        DISPLAY_MESSAGE.showInfoDialog(savePayments.getScene().getWindow(), "Please Add Services Requested By the Patient before you proceed", NotificationType.ERROR);

                    } else {

                        if (paymentMode.getSelectionModel().getSelectedIndex() < 0) {
                            DISPLAY_MESSAGE.showInfoDialog(savePayments.getScene().getWindow(), "Please select payment mode", NotificationType.ERROR);
                        } else {

                            if (insuranceName.getText().isEmpty()) {
                                DISPLAY_MESSAGE.showInfoDialog(savePayments.getScene().getWindow(), "Please click the button to select insurance", NotificationType.ERROR);

                            } else if (insuranceNo.getText().isEmpty()) {
                                DISPLAY_MESSAGE.showInfoDialog(savePayments.getScene().getWindow(), "Please enter the insurance number", NotificationType.ERROR);

                            } else {

                                String inputAmount = DISPLAY_MESSAGE.showInputDialog(savePayments.getScene().getWindow(), "Enter Amount Received", "0");

                                double amount = Double.parseDouble(inputAmount);
                                double discountAmount = 0;
                                payments.setPatientId(PatientsRequest.globalPatientId);
                                payments.setCashAmount(amount);
                                if (discount.getText().isEmpty()) {
                                    discountAmount = 0;
                                } else {
                                    discountAmount = Double.parseDouble(discount.getText());
                                }
                                payments.setCashDiscount(discountAmount);
                                payments.setInsuranceName(insuranceName.getText());
                                payments.setPolicyNo(insuranceNo.getText());
                                double totalSserviceCost = Double.parseDouble(commissionDue.getText());
                                double total = (totalSserviceCost - discountAmount);
                                double balance = amount - total;

                                if (amount >= (totalSserviceCost + payments.getCashDiscount())) {
                                    if (payments.saveInsurance()) {
                                         Stage stage = (Stage) savePayments.getScene().getWindow();
                                   stage.close();
                                        DISPLAY_MESSAGE.showInfoDialog(savePayments.getScene().getWindow(), "Payment Successfully Completed", NotificationType.INFORMATION);
                                         ((Node)(e.getSource())).getScene().getWindow().hide(); 
                                        PrintReceipt.generateReceipt("Payments" + SYSTEM_DATE + ".pdf", "AGIK EDWIN", servicesOffered.getText(),
                                                commissionDue.getText(), paymentMode.getSelectionModel().getSelectedItem().toString(),
                                                discount.getText(), inputAmount, balance);
                                    } else {
                                        DISPLAY_MESSAGE.showInfoDialog(savePayments.getScene().getWindow(), "Failed to Complete Cash Payment", NotificationType.ERROR);

                                    }
                                } else {
                                    DISPLAY_MESSAGE.showInfoDialog(savePayments.getScene().getWindow(), "The amount being received is not sufficient to pay for the services requested", NotificationType.INFORMATION);
                                }

                            }

                        }
                    }
                }

                if (selectBank == e.getSource()) {

                    Stage utility = new Stage();
                    LookupVar.SEARCH = LookupVar.LOOK_UP.LOOKUP_CONTR;

                    utility.setTitle("SELECT CT-SCAN");
                    LookupVar.SEARCH.setTbl_items(banksTV());
                    SystemVariables.UILoader.loadTable(LookupVar.SEARCH.getAnchor_tbl_items(), LookupVar.SEARCH.getTbl_items());

                    LookupVar.SEARCH.getTxt_search().setOnKeyReleased((KeyEvent event) -> {
                        LookupVar.SEARCH.getTbl_items().getItems().clear();
                        //LookupVar.SEARCH.getTbl_items().getItems().addAll(clientTV(LookupVar.SEARCH.getTxt_search().getText()).getItems());
                        SystemVariables.UILoader.loadTable(LookupVar.SEARCH.getAnchor_tbl_items(), LookupVar.SEARCH.getTbl_items());
                    });
                    LookupVar.SEARCH.getTbl_items().setOnMouseClicked((MouseEvent event) -> {

                        if (event.getClickCount() == 2) {
                            lookupBanks = (Bank) LookupVar.SEARCH.getTbl_items().getSelectionModel().getSelectedItem();

                            bankName.setText(lookupBanks.getBankName());

                            utility.close();

                        }
                    });

                    utility.initModality(Modality.WINDOW_MODAL);
                    utility.initOwner(selectBank.getScene().getWindow());
                    utility.setScene(LookupVar.LOOK_UP.lookupScene);
                    utility.setIconified(false);
                    utility.show();

                }
                if (selectInsurance == e.getSource()) {

                    Stage utility = new Stage();
                    LookupVar.SEARCH = LookupVar.LOOK_UP.LOOKUP_CONTR;

                    utility.setTitle("SELECT CT-SCAN");
                    LookupVar.SEARCH.setTbl_items(InsuranceTV());
                    SystemVariables.UILoader.loadTable(LookupVar.SEARCH.getAnchor_tbl_items(), LookupVar.SEARCH.getTbl_items());

                    LookupVar.SEARCH.getTxt_search().setOnKeyReleased((KeyEvent event) -> {
                        LookupVar.SEARCH.getTbl_items().getItems().clear();
                        //LookupVar.SEARCH.getTbl_items().getItems().addAll(clientTV(LookupVar.SEARCH.getTxt_search().getText()).getItems());
                        SystemVariables.UILoader.loadTable(LookupVar.SEARCH.getAnchor_tbl_items(), LookupVar.SEARCH.getTbl_items());
                    });
                    LookupVar.SEARCH.getTbl_items().setOnMouseClicked((MouseEvent event) -> {

                        if (event.getClickCount() == 2) {
                            lookupInsurance = (Insurance) LookupVar.SEARCH.getTbl_items().getSelectionModel().getSelectedItem();

                            insuranceName.setText(lookupInsurance.getInsuranceName());

                            utility.close();

                        }
                    });

                    utility.initModality(Modality.WINDOW_MODAL);
                    utility.initOwner(selectBank.getScene().getWindow());
                    utility.setScene(LookupVar.LOOK_UP.lookupScene);
                    utility.setIconified(false);
                    utility.show();
                }
            }
        }
    }

    class MouseActionListener implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent e) {

        }

    }
}
