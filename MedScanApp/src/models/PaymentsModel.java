/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import static com.solutions.entorno.utilities.SystemVariables.connection;
import static com.solutions.entorno.utilities.SystemVariables.preparedStatement;
import static com.solutions.entorno.utilities.SystemVariables.resultSet;
import static com.solutions.entorno.utilities.SystemVariables.statement;
import controllers.ExaminationReport;
import controllers.InsurancePayments;
import controllers.Payments;
import database.DbConnection;
import static database.MedscanVariables.TBL_BANK_PAYMENTS;
import static database.MedscanVariables.TBL_CASH_PAYMENTS;
import static database.MedscanVariables.TBL_EXAMS_REPORTS;
import static database.MedscanVariables.TBL_INSURANCE_PAYMENTS;
import static database.MedscanVariables.TBL_PATIRNTS_REQUESTS;
import static database.MedscanVariables.TBL_PAYMENTS;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author YoungGucha
 */
public class PaymentsModel {
   
    Payments pay;  
    
     ExaminationReport examReport;
  
    public boolean inserCashPayments(Payments payment) {
        boolean result = false;
     String insertSQL = "INSERT INTO "+TBL_CASH_PAYMENTS+"(`patientid`, `amount`, `discount`)"
                + " VALUES (?,?,?)";
        try {
            
             preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, payment.getPatientId());
            preparedStatement.setDouble(2, payment.getCashAmount());
            preparedStatement.setDouble(3, payment.getCashDiscount());
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
 
    }
    
     public boolean inserBankPayments(Payments payment) {
        boolean result = false;
     String insertSQL = "INSERT INTO "+TBL_BANK_PAYMENTS+"(`patientid`, `bankname`, `chequeno`, `creditcardno`, `amount`, `discount`)"
                + " VALUES (?,?,?,?,?,?)";
        try {
            
             preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, payment.getPatientId());
             preparedStatement.setString(2, payment.getBankName());
              preparedStatement.setString(3, payment.getChequeNo());
               preparedStatement.setString(4, payment.getCreditCardNo());
            preparedStatement.setDouble(5, payment.getCashAmount());
            preparedStatement.setDouble(6, payment.getCashDiscount());
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
 
    }
     public boolean inserInsurancePayments(Payments payment) {
        boolean result = false;
     String insertSQL = "INSERT INTO "+TBL_INSURANCE_PAYMENTS+"(`patientid`, `insurancename`, `policyno`, `amount`, `discount`)"
                + " VALUES (?,?,?,?,?)";
        try {
            
             preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, payment.getPatientId());
             preparedStatement.setString(2, payment.getInsuranceName());
              preparedStatement.setString(3, payment.getPolicyNo());
            preparedStatement.setDouble(4, payment.getCashAmount());
            preparedStatement.setDouble(5, payment.getCashDiscount());
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
 
    }
    
    public ArrayList<Payments> getAllPayments() {
        ArrayList<Payments> payments = new ArrayList<>();
       
        try {
             resultSet=statement.executeQuery("SELECT `billing_id`, `patient_id`, `payment_mode`, `amount`, "
                     + "`status`, `last_updated`, `deleted` FROM  "+TBL_PAYMENTS+" ");
            while (resultSet.next()) {
                pay = new Payments();
                pay.setBillingId(resultSet.getString("billing_id"));
                pay.setPatientId(resultSet.getString("patient_id"));
                pay.setPaymentMode(resultSet.getString("payment_mode"));
                 pay.setAmount(resultSet.getDouble("amount"));
                pay.setStatus(resultSet.getBoolean("status"));
                pay.setLastUpdate(resultSet.getTimestamp("last_updated"));
                pay.setDeleted(resultSet.getBoolean("deleted"));
                payments.add(pay);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return payments;
    }
    public ArrayList<Payments> getPayments(String patientid) {
        ArrayList<Payments> payments = new ArrayList<>();
         try {
             //cash payments
             resultSet=statement.executeQuery("SELECT `paymentid`, `patientid`, `amount`, `discount`, `datepaid`, "
                     + "`status`, `lastupdated`, `deleted` FROM  "+TBL_CASH_PAYMENTS+" WHERE patientid='"+patientid+"' ");
            while (resultSet.next()) {
                pay = new Payments();
                pay.setBillingId(resultSet.getString("paymentid"));
                pay.setPatientId(resultSet.getString("patientid"));
                //pay.setPaymentMode(resultSet.getString("payment_mode"));
                pay.setAmount(resultSet.getDouble("amount"));
                pay.setCashDiscount(resultSet.getDouble("discount"));
                pay.setStatus(resultSet.getBoolean("status"));
                pay.setDatePaid(resultSet.getTimestamp("datepaid"));
                pay.setLastUpdate(resultSet.getTimestamp("lastupdated"));
                pay.setDeleted(resultSet.getBoolean("deleted"));
                payments.add(pay);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         //bank payments
         try {
              resultSet=statement.executeQuery("SELECT `paymentid`, `patientid`, `amount`, `discount`, `datepaid`, "
                     + "`status`, `lastupdated`, `deleted` FROM  "+TBL_BANK_PAYMENTS+" WHERE patientid='"+patientid+"' ");
            while (resultSet.next()) {
                pay = new Payments();
                pay.setBillingId(resultSet.getString("paymentid"));
                pay.setPatientId(resultSet.getString("patientid"));
             //   pay.setPaymentMode(resultSet.getString("payment_mode"));
                pay.setAmount(resultSet.getDouble("amount"));
                pay.setCashDiscount(resultSet.getDouble("discount"));
                pay.setStatus(resultSet.getBoolean("status"));
                pay.setDatePaid(resultSet.getTimestamp("datepaid"));
                pay.setLastUpdate(resultSet.getTimestamp("lastupdated"));
                pay.setDeleted(resultSet.getBoolean("deleted"));
                payments.add(pay);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
         // insurance payments
         try {
             
             resultSet=statement.executeQuery("SELECT `paymentid`, `patientid`, `amount`, `discount`, `datepaid`, "
                     + "`status`, `lastupdated`, `deleted` FROM  "+TBL_INSURANCE_PAYMENTS+" WHERE patientid='"+patientid+"' ");
            while (resultSet.next()) {
                pay = new Payments();
                pay.setBillingId(resultSet.getString("paymentid"));
                pay.setPatientId(resultSet.getString("patientid"));
              //  pay.setPaymentMode(resultSet.getString("payment_mode"));
                pay.setAmount(resultSet.getDouble("amount"));
                pay.setCashDiscount(resultSet.getDouble("discount"));
                pay.setStatus(resultSet.getBoolean("status"));
                pay.setDatePaid(resultSet.getTimestamp("datepaid"));
                pay.setLastUpdate(resultSet.getTimestamp("lastupdated"));
                pay.setDeleted(resultSet.getBoolean("deleted"));
                payments.add(pay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return payments;
    }
    public double getTotalPayment(String type,String year,String month) {
       double totalPayment=0;
       String sqlPart;
        String sqlMonth;
        String sqlYear;
       
         double totalPaymentDiscount=0;
        if(type=="ALL" || type==""  ){
           sqlPart=""; 
        }else{
            sqlPart=" ";  
       }
        if( month==""){
            sqlMonth="";
        }else{
            sqlMonth=" AND MONTH(datepaid) ="+month+" ";
        }
        if(year==""){
          sqlYear="";  
        }else{
            sqlYear=" AND YEAR(datepaid) ="+year+" ";
        }
         try {
             //cash payments
             resultSet=statement.executeQuery("SELECT  `amount` FROM  "+TBL_CASH_PAYMENTS+" "
                     + "WHERE amount>0 "+sqlPart+"  "+sqlMonth+"  "+sqlYear+"  ");
            while (resultSet.next()) {
             totalPayment=totalPayment+resultSet.getDouble("amount");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         //bank payments
         try {
              resultSet=statement.executeQuery("SELECT  `amount` FROM  "+TBL_BANK_PAYMENTS+" "
                      + "WHERE amount>0 "+sqlPart+"  "+sqlMonth+"  "+sqlYear+"  ");
            while (resultSet.next()) {
                totalPayment=totalPayment+resultSet.getDouble("amount");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
         // insurance payments
         try {
             
             resultSet=statement.executeQuery("SELECT  `amount` FROM  "+TBL_INSURANCE_PAYMENTS+" "
                     + "WHERE amount>0 "+sqlPart+"  "+sqlMonth+"  "+sqlYear+"  ");
            while (resultSet.next()) {
                totalPayment=totalPayment+resultSet.getDouble("amount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return totalPayment;
    }
    
     public ArrayList<Payments> getPaymentsReport(String serviceType,String year,String month) {
        ArrayList<Payments> payments = new ArrayList<>();
         String sqlPart;
        String sqlMonth;
        String sqlYear;
        double totalPayment=0;
         double totalPaymentDiscount=0;
        if(serviceType=="ALL" || serviceType==""  ){
           sqlPart=""; 
        }else{
            sqlPart=" ";  
       }
        if( month==""){
            sqlMonth="";
        }else{
            sqlMonth=" AND MONTH(datepaid) ="+month+" ";
        }
        if(year==""){
          sqlYear="";  
        }else{
            sqlYear=" AND YEAR(datepaid) ="+year+" ";
        }
         try {
             //cash payments
             resultSet=statement.executeQuery("SELECT p.name, pm.`paymentid`, pm.`patientid`, pm.`amount`, pm.`discount`, pm.`datepaid` "
                     + " FROM  "+TBL_CASH_PAYMENTS+" pm ,"+TBL_PATIRNTS_REQUESTS+" p "
                     + "WHERE p.patientid=pm.patientid   "+sqlPart+"  "+sqlMonth+"  "+sqlYear+"");
            while (resultSet.next()) {
                pay = new Payments();
                pay.setPatientName(resultSet.getString("name"));
                pay.setBillingId(resultSet.getString("paymentid"));
                pay.setPatientId(resultSet.getString("patientid"));
                pay.setPaymentMode("CASH");
                pay.setAmount(resultSet.getDouble("amount"));
                pay.setCashDiscount(resultSet.getDouble("discount"));
                pay.setDatePaid(resultSet.getTimestamp("datepaid"));
                totalPayment=totalPayment+pay.getAmount();
                totalPaymentDiscount=totalPaymentDiscount+pay.getCashDiscount();
                payments.add(pay);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         //bank payments
         try {
              resultSet=statement.executeQuery("SELECT p.name, pm.`paymentid`, pm.`patientid`, pm.`amount`, pm.`discount`, pm.`datepaid` "
                      + "FROM  "+TBL_BANK_PAYMENTS+" pm , "+TBL_PATIRNTS_REQUESTS+" p "
                      + "WHERE p.patientid=pm.patientid  "+sqlPart+"  "+sqlMonth+"  "+sqlYear+" ");
            while (resultSet.next()) {
                pay = new Payments();
                pay.setPatientName(resultSet.getString("name"));
                pay.setBillingId(resultSet.getString("paymentid"));
                pay.setPatientId(resultSet.getString("patientid"));
                pay.setPaymentMode("BANK");
                pay.setAmount(resultSet.getDouble("amount"));
                pay.setCashDiscount(resultSet.getDouble("discount"));
                pay.setDatePaid(resultSet.getTimestamp("datepaid"));
                 totalPayment=totalPayment+pay.getAmount();
                 totalPaymentDiscount=totalPaymentDiscount+pay.getCashDiscount();
                payments.add(pay);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
         // insurance payments
         try {
             
             resultSet=statement.executeQuery("SELECT p.name, pm.`paymentid`, pm.`patientid`, pm.`amount`, pm.`discount`, pm.`datepaid` "
                     + "FROM  "+TBL_INSURANCE_PAYMENTS+" pm, "+TBL_PATIRNTS_REQUESTS+" p "
                     + "WHERE p.patientid=pm.patientid  "+sqlPart+"  "+sqlMonth+"  "+sqlYear+" ");
            while (resultSet.next()) {
                pay = new Payments();
                pay.setPatientName(resultSet.getString("name"));
                pay.setBillingId(resultSet.getString("paymentid"));
                pay.setPatientId(resultSet.getString("patientid"));
               pay.setPaymentMode("INSURANCE");
                pay.setAmount(resultSet.getDouble("amount"));
                pay.setCashDiscount(resultSet.getDouble("discount"));
                pay.setDatePaid(resultSet.getTimestamp("datepaid"));
                 totalPayment=totalPayment+pay.getAmount();
                 totalPaymentDiscount=totalPaymentDiscount+pay.getCashDiscount();
                payments.add(pay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        pay.setTotalAllPayments(totalPayment);
        pay.setTotalAllDiscount(totalPaymentDiscount);
         
        return payments;
    }
    
    public ArrayList<Payments> getPaymentsCash(String serviceType,String year,String month) {
        ArrayList<Payments> payments = new ArrayList<>();
        String sqlPart;
        String sqlMonth;
        String sqlYear;
        double totalPayment=0;
         double totalPaymentDiscount=0;
        if(serviceType=="ALL" || serviceType==""  ){
           sqlPart=""; 
        }else{
            sqlPart="  ";  
       }
        if( month==""){
            sqlMonth="";
        }else{
            sqlMonth=" AND MONTH(datepaid) ="+month+" ";
        }
        if(year==""){
          sqlYear="";  
        }else{
            sqlYear=" AND YEAR(datepaid) ="+year+" ";
        }
         try {
             //cash payments
             resultSet=statement.executeQuery("SELECT p.name, pm.`paymentid`, pm.`patientid`, pm.`amount`, pm.`discount`, pm.`datepaid` "
                     + " FROM  "+TBL_CASH_PAYMENTS+" pm ,"+TBL_PATIRNTS_REQUESTS+" p "
                     + "WHERE p.patientid=pm.patientid   "+sqlPart+"  "+sqlMonth+"  "+sqlYear+" ");
            while (resultSet.next()) {
                pay = new Payments();
                pay.setBillingId(resultSet.getString("paymentid"));
                pay.setPatientId(resultSet.getString("patientid"));
                pay.setPaymentMode("CASH");
                pay.setAmount(resultSet.getDouble("amount"));
                pay.setCashDiscount(resultSet.getDouble("discount"));
                pay.setDatePaid(resultSet.getTimestamp("datepaid"));
                 totalPayment=totalPayment+pay.getAmount();
                 totalPaymentDiscount=totalPaymentDiscount+pay.getCashDiscount();
                payments.add(pay);
            }
           pay.setTotalAllPayments(totalPayment);
        pay.setTotalAllDiscount(totalPaymentDiscount);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         
        
        return payments;
    }
    public ArrayList<Payments> getPaymentsBanks(String serviceType,String year,String month) {
        ArrayList<Payments> payments = new ArrayList<>();
         String sqlPart;
        String sqlMonth;
        String sqlYear;
         double totalPayment=0;
         double totalPaymentDiscount=0;
        if(serviceType=="ALL" || serviceType==""  ){
           sqlPart=""; 
        }else{
            sqlPart="  ";  
       }
        if( month==""){
            sqlMonth="";
        }else{
            sqlMonth=" AND MONTH(datepaid) ="+month+" ";
        }
        if(year==""){
          sqlYear="";  
        }else{
            sqlYear=" AND YEAR(datepaid) ="+year+" ";
        }
         try {
            
       
              resultSet=statement.executeQuery("SELECT p.name, pm.`paymentid`, pm.`patientid`, pm.`amount`,"
                      + " pm.`bankname`, pm.`chequeno`, pm.`creditcardno`, pm.`discount`, pm.`datepaid` "
                      + "FROM  "+TBL_BANK_PAYMENTS+" pm , "+TBL_PATIRNTS_REQUESTS+" p "
                      + "WHERE p.patientid=pm.patientid  "+sqlPart+"  "+sqlMonth+"  "+sqlYear+" ");
            while (resultSet.next()) {
                pay = new Payments();
                pay.setBillingId(resultSet.getString("paymentid"));
                pay.setPatientId(resultSet.getString("patientid"));
              pay.setPaymentMode("BANK");
                pay.setAmount(resultSet.getDouble("amount"));
                pay.setCashDiscount(resultSet.getDouble("discount"));
                pay.setBankName(resultSet.getString("bankname"));
                 pay.setChequeNo(resultSet.getString("chequeno"));
                  pay.setCreditCardNo(resultSet.getString("creditcardno"));
                pay.setDatePaid(resultSet.getTimestamp("datepaid"));
                totalPayment=totalPayment+pay.getAmount();
                 totalPaymentDiscount=totalPaymentDiscount+pay.getCashDiscount();
                payments.add(pay);
            }
           pay.setTotalAllPayments(totalPayment);
        pay.setTotalAllDiscount(totalPaymentDiscount);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return payments;
    }
    
    public ArrayList<Payments> getPaymentsInsurance(String serviceType,String year,String month) {
        ArrayList<Payments> payments = new ArrayList<>();
        
              String sqlPart;
        String sqlMonth;
        String sqlYear;
         double totalPayment=0;
         double totalPaymentDiscount=0;
        if(serviceType=="ALL" || serviceType==""  ){
           sqlPart=""; 
        }else{
            sqlPart="  ";  
       }
        if( month==""){
            sqlMonth="";
        }else{
            sqlMonth=" AND MONTH(datepaid) ="+month+" ";
        }
        if(year==""){
          sqlYear="";  
        }else{
            sqlYear=" AND YEAR(datepaid) ="+year+" ";
        }
         // insurance payments
         try {
             
             resultSet=statement.executeQuery("SELECT p.name, pm.`paymentid`, pm.`patientid`, pm.`amount`,"
                     + " pm.`discount`, pm.`datepaid`, pm.`insurancename`, pm.`policyno` "
                     + "FROM  "+TBL_INSURANCE_PAYMENTS+" pm, "+TBL_PATIRNTS_REQUESTS+" p  "
                     + " WHERE p.patientid=pm.patientid  "+sqlPart+"  "+sqlMonth+"  "+sqlYear+"");
            while (resultSet.next()) {
                pay = new Payments();
                pay.setBillingId(resultSet.getString("paymentid"));
                pay.setPatientId(resultSet.getString("patientid"));
              pay.setPaymentMode("INSURANCE");
                pay.setAmount(resultSet.getDouble("amount"));
                pay.setCashDiscount(resultSet.getDouble("discount"));
                 pay.setInsuranceName(resultSet.getString("insurancename"));
                  pay.setPolicyNo(resultSet.getString("policyno"));
                pay.setDatePaid(resultSet.getTimestamp("datepaid"));
               totalPayment=totalPayment+pay.getAmount();
                 totalPaymentDiscount=totalPaymentDiscount+pay.getCashDiscount();
                payments.add(pay);
            }
           pay.setTotalAllPayments(totalPayment);
        pay.setTotalAllDiscount(totalPaymentDiscount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return payments;
    }
    public double getTotalPaymentReport(String serviceType,String year,String month) {
       double totalPayment=0;
       
              String sqlPart;
        String sqlMonth;
        String sqlYear;
        if(serviceType=="ALL" || serviceType==""  ){
           sqlPart=""; 
        }else{
            sqlPart="  ";  
       }
        if( month==""){
            sqlMonth="";
        }else{
            sqlMonth=" AND MONTH(datepaid) ="+month+" ";
        }
        if(year==""){
          sqlYear="";  
        }else{
            sqlYear=" AND YEAR(datepaid) ="+year+" ";
        }
         try {
             //cash payments
             pay=new Payments();
             resultSet=statement.executeQuery("SELECT  `amount` FROM  "+TBL_CASH_PAYMENTS+""
                     + " WHERE amount>0   "+sqlPart+"  "+sqlMonth+"  "+sqlYear+" ");
            while (resultSet.next()) {
             totalPayment=totalPayment+resultSet.getDouble("amount");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         //bank payments
         try {
              resultSet=statement.executeQuery("SELECT  `amount` FROM  "+TBL_BANK_PAYMENTS+"  "
                      + " amount>0   "+sqlPart+"  "+sqlMonth+"  "+sqlYear+" ");
            while (resultSet.next()) {
                totalPayment=totalPayment+resultSet.getDouble("amount");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
         // insurance payments
         try {
             
             resultSet=statement.executeQuery("SELECT  `amount` FROM  "+TBL_INSURANCE_PAYMENTS+" "
                     + " amount>0   "+sqlPart+"  "+sqlMonth+"  "+sqlYear+" ");
            while (resultSet.next()) {
                totalPayment=totalPayment+resultSet.getDouble("amount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        pay.setTotalAllPayments(totalPayment);
        System.out.println("pay at model "+pay.getTotalAllPayments());
        return totalPayment;
    }
     public double getTotalPaymentReportCash(String serviceType,String year,String month) {
       double totalPayment=0;
       
              String sqlPart;
        String sqlMonth;
        String sqlYear;
        if(serviceType=="ALL" || serviceType==""  ){
           sqlPart=""; 
        }else{
            sqlPart="  ";  
       }
        if( month==""){
            sqlMonth="";
        }else{
            sqlMonth=" AND MONTH(datepaid) ="+month+" ";
        }
        if(year==""){
          sqlYear="";  
        }else{
            sqlYear=" AND YEAR(datepaid) ="+year+" ";
        }
         try {
             //cash payments
             resultSet=statement.executeQuery("SELECT  `amount` FROM  "+TBL_CASH_PAYMENTS+""
                     + " WHERE amount>0   "+sqlPart+"  "+sqlMonth+"  "+sqlYear+" ");
            while (resultSet.next()) {
             totalPayment=totalPayment+resultSet.getDouble("amount");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         
        return totalPayment;
    }
     public double getTotalPaymentReportBanks(String serviceType,String year,String month) {
       double totalPayment=0;
       
              String sqlPart;
        String sqlMonth;
        String sqlYear;
        if(serviceType=="ALL" || serviceType==""  ){
           sqlPart=""; 
        }else{
            sqlPart="  ";  
       }
        if( month==""){
            sqlMonth="";
        }else{
            sqlMonth=" AND MONTH(datepaid) ="+month+" ";
        }
        if(year==""){
          sqlYear="";  
        }else{
            sqlYear=" AND YEAR(datepaid) ="+year+" ";
        }
        
         //bank payments
         try {
              resultSet=statement.executeQuery("SELECT  `amount` FROM  "+TBL_BANK_PAYMENTS+"  "
                      + " amount>0   "+sqlPart+"  "+sqlMonth+"  "+sqlYear+" ");
            while (resultSet.next()) {
                totalPayment=totalPayment+resultSet.getDouble("amount");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return totalPayment;
    }
      public double getTotalPaymentReportInsurance(String serviceType,String year,String month) {
       double totalPayment=0;
       
              String sqlPart;
        String sqlMonth;
        String sqlYear;
        if(serviceType=="ALL" || serviceType==""  ){
           sqlPart=""; 
        }else{
            sqlPart="  ";  
       }
        if( month==""){
            sqlMonth="";
        }else{
            sqlMonth=" AND MONTH(datepaid) ="+month+" ";
        }
        if(year==""){
          sqlYear="";  
        }else{
            sqlYear=" AND YEAR(datepaid) ="+year+" ";
        }
        
               // insurance payments
         try {
             
             resultSet=statement.executeQuery("SELECT  `amount` FROM  "+TBL_INSURANCE_PAYMENTS+" "
                     + " amount>0   "+sqlPart+"  "+sqlMonth+"  "+sqlYear+" ");
            while (resultSet.next()) {
                totalPayment=totalPayment+resultSet.getDouble("amount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return totalPayment;
    }
      
      // ========================discount ======================
      
      public double getTotalPaymentDiscountReport(String serviceType,String year,String month) {
       double totalPayment=0;
       
              String sqlPart;
        String sqlMonth;
        String sqlYear;
        if(serviceType=="ALL" || serviceType==""  ){
           sqlPart=""; 
        }else{
            sqlPart="  ";  
       }
        if( month==""){
            sqlMonth="";
        }else{
            sqlMonth=" AND MONTH(datepaid) ="+month+" ";
        }
        if(year==""){
          sqlYear="";  
        }else{
            sqlYear=" AND YEAR(datepaid) ="+year+" ";
        }
         try {
             //cash payments
             resultSet=statement.executeQuery("SELECT  `discount` FROM  "+TBL_CASH_PAYMENTS+""
                     + " WHERE amount>0   "+sqlPart+"  "+sqlMonth+"  "+sqlYear+" ");
            while (resultSet.next()) {
             totalPayment=totalPayment+resultSet.getDouble("discount");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         //bank payments
         try {
              resultSet=statement.executeQuery("SELECT  `discount` FROM  "+TBL_BANK_PAYMENTS+"  "
                      + " WHERE amount>0   "+sqlPart+"  "+sqlMonth+"  "+sqlYear+" ");
            while (resultSet.next()) {
                totalPayment=totalPayment+resultSet.getDouble("discount");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
         // insurance payments
         try {
             
             resultSet=statement.executeQuery("SELECT  `discount` FROM  "+TBL_INSURANCE_PAYMENTS+" "
                     + "WHERE amount>0   "+sqlPart+"  "+sqlMonth+"  "+sqlYear+" ");
            while (resultSet.next()) {
                totalPayment=totalPayment+resultSet.getDouble("discount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return totalPayment;
    }
    
         public double getTotalPaymentDiscountCash(String serviceType,String year,String month) {
       double totalPayment=0;
       
         String sqlPart;
        String sqlMonth;
        String sqlYear;
        if(serviceType=="ALL" || serviceType==""  ){
           sqlPart=""; 
        }else{
            sqlPart="  ";  
       }
        if( month==""){
            sqlMonth="";
        }else{
            sqlMonth=" AND MONTH(datepaid) ="+month+" ";
        }
        if(year==""){
          sqlYear="";  
        }else{
            sqlYear=" AND YEAR(datepaid) ="+year+" ";
        }
         try {
             //cash payments
             resultSet=statement.executeQuery("SELECT  `discount` FROM  "+TBL_CASH_PAYMENTS+""
                     + " WHERE amount>0   "+sqlPart+"  "+sqlMonth+"  "+sqlYear+" ");
            while (resultSet.next()) {
             totalPayment=totalPayment+resultSet.getDouble("discount");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         
        return totalPayment;
    }
     public double getTotalPaymentDiscountsBanks(String serviceType,String year,String month) {
       double totalPayment=0;
       
              String sqlPart;
        String sqlMonth;
        String sqlYear;
        if(serviceType=="ALL" || serviceType==""  ){
           sqlPart=""; 
        }else{
            sqlPart="  ";  
       }
        if( month==""){
            sqlMonth="";
        }else{
            sqlMonth=" AND MONTH(datepaid) ="+month+" ";
        }
        if(year==""){
          sqlYear="";  
        }else{
            sqlYear=" AND YEAR(datepaid) ="+year+" ";
        }
        
         //bank payments
         try {
              resultSet=statement.executeQuery("SELECT  `discount` FROM  "+TBL_BANK_PAYMENTS+"  "
                      + " WHERE amount >0   "+sqlPart+"  "+sqlMonth+"  "+sqlYear+" ");
            while (resultSet.next()) {
                totalPayment=totalPayment+resultSet.getDouble("discount");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return totalPayment;
    }
      public double getTotalPaymentDiscountInsurance(String serviceType,String year,String month) {
       double totalPayment=0;
       
              String sqlPart;
        String sqlMonth;
        String sqlYear;
        if(serviceType=="ALL" || serviceType==""  ){
           sqlPart=""; 
        }else{
            sqlPart="  ";  
       }
        if( month==""){
            sqlMonth="";
        }else{
            sqlMonth=" AND MONTH(datepaid) ="+month+" ";
        }
        if(year==""){
          sqlYear="";  
        }else{
            sqlYear=" AND YEAR(datepaid) ="+year+" ";
        }
        
               // insurance payments
         try {
             
             resultSet=statement.executeQuery("SELECT  `discount` FROM  "+TBL_INSURANCE_PAYMENTS+" "
                     + "WHERE  amount>0   "+sqlPart+"  "+sqlMonth+"  "+sqlYear+" ");
            while (resultSet.next()) {
                totalPayment=totalPayment+resultSet.getDouble("discount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return totalPayment;
    }
   // =============discount ends here================
    public boolean updatePayments(Payments payment) {
        boolean result = false;
         
       String insertSQL = "UPDATE "+TBL_PAYMENTS+" SET payment_mode=?, `amount` =? WHERE WHERE billing_id=?";
        try {
            
             preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
           
            preparedStatement.setString(1, payment.getPaymentMode());
            preparedStatement.setDouble(2, payment.getAmount());
             preparedStatement.setString(3, payment.getPatientId());
            preparedStatement.executeUpdate();
            result = true;
            }
            catch (SQLException e) {
                e.printStackTrace();
               
            }
 
        return result;
    }
    
    public int deleteDoctors(Payments payment) {
        String selectSQL = "UPDATE "+TBL_PAYMENTS+" SET `deleted`=1 WHERE billing_id=?";
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(selectSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setString(1, payment.getBillingId());
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
}
