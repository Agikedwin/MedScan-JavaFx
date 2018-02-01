/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.solutions.entorno.utilities.TableViewRenderer;
import java.sql.Timestamp;
import java.util.ArrayList;
import javafx.scene.control.TableView;
import models.PaymentsModel;

/**
 *
 * @author young
 */
public class Payments {
    
       
     private String  billingId;
     private String  patientId;
     private String  paymentMode,patientName;
     private double amount;
     private boolean  status;
     private Timestamp  lastUpdate;
      private Timestamp  datePaid;
     private boolean  deleted;
     //cash 
      private double cashAmount,cashDiscount;
     //banks
      private String  bankName,chequeNo,creditCardNo;
      private double bankAmount,bankDiscount;
      
      //insurance
      private String  policyNo,insuranceName;
      private double insuranceAmount, InsuranceDiscount;
      
      //report variables 
      public static double totalAllPayments=0;
      public static double totalAllDiscount=0;
    private static PaymentsModel paymentsModel;
    
    public Payments(){
     paymentsModel=new  PaymentsModel();
    }

public boolean saveCash(){
    return paymentsModel.inserCashPayments(this);
}
public boolean saveBank(){
    return paymentsModel.inserBankPayments(this);
}
public boolean saveInsurance(){
    return paymentsModel.inserInsurancePayments(this);
}
    public static ArrayList fetchPayments(String patientid){
       return paymentsModel.getPayments(patientid);
    }
    public static ArrayList fetchPaymentsReport(String type,String year,String month){
       return paymentsModel.getPaymentsReport(type,year, month);
    }
    public static ArrayList fetchPaymentsCash(String type,String year,String month){
       return paymentsModel.getPaymentsCash(type,year, month);
    }
    public static ArrayList fetchPaymentsBank(String type,String year,String month){
       return paymentsModel.getPaymentsBanks(type,year, month);
    }
    public static ArrayList fetchPaymentsInsurance(String type,String year,String month){
       return paymentsModel.getPaymentsInsurance(type,year, month);
    }
    // discounts 
//     public double fetchPaymentsAllDiscounts(String type,String year,String month){
//       return paymentsModel.getTotalPaymentDiscountReport(type,year, month);
//    }
//      public double fetchPaymentsCashDiscounts(String type,String year,String month){
//       return paymentsModel.getTotalPaymentDiscountCash(type,year, month);
//    }
//       public double fetchPaymentsBanksDiscounts(String type,String year,String month){
//       return paymentsModel.getTotalPaymentDiscountsBanks(type,year, month);
//    }
//        public double fetchPaymentsInsuranceDiscount(String type,String year,String month){
//       return paymentsModel.getTotalPaymentDiscountInsurance(type,year, month);
//    }
    public double totalPayemnt(String type,String year,String month){
        return paymentsModel.getTotalPayment( type, year, month);
    }
    public String getBillingId() {
        return billingId;
    }
    public void setBillingId(String billingId) {
        this.billingId = billingId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Timestamp datePaid) {
        this.datePaid = datePaid;
    }

    public double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(double cashAmount) {
        this.cashAmount = cashAmount;
    }

    public double getCashDiscount() {
        return cashDiscount;
    }

    public void setCashDiscount(double cashDiscount) {
        this.cashDiscount = cashDiscount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public double getBankAmount() {
        return bankAmount;
    }

    public void setBankAmount(double bankAmount) {
        this.bankAmount = bankAmount;
    }

    public double getBankDiscount() {
        return bankDiscount;
    }

    public void setBankDiscount(double BankDiscount) {
        this.bankDiscount = BankDiscount;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public double getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(double insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public double getInsuranceDiscount() {
        return InsuranceDiscount;
    }

    public void setInsuranceDiscount(double InsuranceDiscount) {
        this.InsuranceDiscount = InsuranceDiscount;
    }
   
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    // report 

    public static double getTotalAllPayments() {
        return totalAllPayments;
    }

    public static void setTotalAllPayments(double totalAllPayments) {
        Payments.totalAllPayments = totalAllPayments;
    }

    public static double getTotalAllDiscount() {
        return totalAllDiscount;
    }

    public static void setTotalAllDiscount(double totalAllDiscount) {
        Payments.totalAllDiscount = totalAllDiscount;
    }

    
    

    
     public   TableView loadPaymentsTV(String patientid) {
        TableView tv;
        String[] headers = {"PATIENT ID","PAYMENT MODE", "AMOUNT ", "DISCOUNT" };
        String[] property = {"patientId", "paymentMode", "amount","cashDiscount"};
        ArrayList<Object> model =  fetchPayments(patientid);

         TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }
     
      public   TableView loadPaymentsReport(String type,String year,String month) {
        TableView tv;
        String[] headers = {"PATIENT'S NAME","PAYMENT MODE", "AMOUNT ", "DISCOUNT", "DISCOUNT" };
        String[] property = {"patientName", "paymentMode", "amount","cashDiscount","datePaid"};
        ArrayList<Object> model =  fetchPaymentsReport(type, year, month);

         TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }
       public   TableView loadCashPayments(String type,String year,String month) {
        TableView tv;
        String[] headers = {"PATIENT'S NAME","PAYMENT MODE", "AMOUNT ", "DISCOUNT", "DISCOUNT" };
        String[] property = {"patientName", "paymentMode", "amount","cashDiscount","datePaid"};
        ArrayList<Object> model =  fetchPaymentsCash(type, year, month);

         TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }
        public   TableView loadPaymentBank(String type,String year,String month) {
        TableView tv;
        String[] headers = {"PATIENT'S NAME","BANK NAME","CHEQUE NO", "AMOUNT ", "DISCOUNT", "DISCOUNT" };
        String[] property = {"patientName", "bankName", "chequeNo","amount","cashDiscount","datePaid"};
        ArrayList<Object> model =  fetchPaymentsBank(type, year, month);

         TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }
         public   TableView loadCashPaymentsInsurance(String type,String year,String month) {
        TableView tv;
        String[] headers = {"PATIENT'S NAME","INSURANCE NAME", "INSURANCE NAME","AMOUNT ", "DISCOUNT", "DISCOUNT" };
        String[] property = {"patientName", "insuranceName", "policyNo","amount","cashDiscount","datePaid"};
        ArrayList<Object> model =  fetchPaymentsInsurance(type, year, month);

         TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }
}
