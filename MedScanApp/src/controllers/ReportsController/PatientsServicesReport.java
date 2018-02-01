/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.ReportsController;

import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.solutions.entorno.utilities.ReportGeneratorJtables;
import static com.solutions.entorno.utilities.SystemFunctions.openfile;
import com.solutions.entorno.utilities.SystemVariables;
import static com.solutions.entorno.utilities.SystemVariables.SYSTEM_DATE;
import com.solutions.entorno.utilities.WaterMarkGenerator;
import controllers.Payments;
import controllers.RefereeCommission;
import controllers.Services;
import java.awt.Font;
import java.io.FileOutputStream;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 *
 * @author YoungGucha
 */
public class PatientsServicesReport {
    
    public static void generateServicesReport(TableView table_to_export, String path, String title, String total) {
        String FILE = SystemVariables.REPORT_FOLDER + path;
        String columnNames[] = {"PATIENT'S NAME ", "SERVICE TYPE", "SERVICE NAME", "COST"};

        int index = 2;
        try {
            Rectangle rect;
            if (index == 1) {
                rect = new Rectangle(700.0F, 1024.0F);
            } else {
                rect = new Rectangle(1024.0F, 700.0F);
            }
            Document document = new Document();
            document.setPageSize(rect);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(FILE));
           writer.setPageEvent(new WaterMarkGenerator());
            document.open();
            int columns = columnNames.length;

            float[] colsWidth = new float[columns];
            for (int i = 0; i < columns; i++) {

                colsWidth[i] = 3.0F;

            }

            float[] headerWidth = new float[2];
            headerWidth[0] = 20.0F;
            headerWidth[1] = 60.F;
            PdfPTable table = new PdfPTable(headerWidth);
            if (index == 1) {
                table.setWidthPercentage(100.0F);
            } else {
                table.setWidthPercentage(75.0F);
            }

            PdfPCell cell;

            document.add(ReportGeneratorJtables.reportHeader(headerWidth, index));
            document.add(new Phrase("\n"));

            table = new PdfPTable(colsWidth);
            
            cell = new PdfPCell(new Phrase("MEDSCAN DIAGNOSTIC CENTRE", FontFactory.getFont("Helvetica", 35.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(80);
            table.addCell(cell);

            table.setHeaderRows(1);

            cell = new PdfPCell(new Phrase(title, FontFactory.getFont("Helvetica", 20.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(120);
            table.addCell(cell);

            document.add(table);
            //document.add(new Phrase("\n"));
            table = new PdfPTable(colsWidth);

            table.setHeaderRows(1);
            for (int i = 0; i < columns; i++) {
                table.addCell(new Phrase(columnNames[i], FontFactory.getFont("Helvetica", 12.0F, Font.BOLD)));
            }
            ObservableList<Services> list = table_to_export.getItems();
            Services patientServ;
            for (Services rec : list) {
                patientServ = rec;
                table.addCell(new Phrase("" + patientServ.getPatientName(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase(patientServ.getServiceType(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase("" + patientServ.getServiceName(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase("" + patientServ.getCost(), FontFactory.getFont("Helvetica", 11.0F)));
            }
            document.add(table);
            
            document.add(new Phrase("\n"));
            table = new PdfPTable(headerWidth);

            cell = new PdfPCell(new Phrase(total, FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("" , FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            // cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);

            document.add(table);
           
            
            cell = new PdfPCell(new Phrase(title, FontFactory.getFont("Helvetica", 13.5F, com.itextpdf.text.Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);
            
            
            document.close();
            openfile(FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
    public static void generateRefCommissionReport(TableView table_to_export, String path, String title, String total) {
        String FILE = SystemVariables.REPORT_FOLDER + path;
       String columnNames[] = {"DOCTOR'S NAME ","PATIENT'S NAME ", "SERVICE NAME", "SERVICES COST", "DATE OFFERED"};


        int index = 2;
        try {
            Rectangle rect;
            if (index == 1) {
                rect = new Rectangle(700.0F, 1024.0F);
            } else {
                rect = new Rectangle(1024.0F, 700.0F);
            }
            Document document = new Document();
            document.setPageSize(rect);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(FILE));
           writer.setPageEvent(new WaterMarkGenerator());
            document.open();
            int columns = columnNames.length;

            float[] colsWidth = new float[columns];
            for (int i = 0; i < columns; i++) {

                colsWidth[i] = 3.0F;

            }

            float[] headerWidth = new float[2];
            headerWidth[0] = 20.0F;
            headerWidth[1] = 60.F;
            PdfPTable table = new PdfPTable(headerWidth);
            if (index == 1) {
                table.setWidthPercentage(100.0F);
            } else {
                table.setWidthPercentage(75.0F);
            }

            PdfPCell cell;

            document.add(ReportGeneratorJtables.reportHeader(headerWidth, index));
            document.add(new Phrase("\n"));

            table = new PdfPTable(colsWidth);

            cell = new PdfPCell(new Phrase(title, FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);

            document.add(table);
            document.add(new Phrase("\n"));
            
            table = new PdfPTable(colsWidth);
            
//            cell = new PdfPCell(new Phrase("MEDSCAN DIAGNOSTIC CENTRE", FontFactory.getFont("Helvetica", 35.5F, Font.BOLD)));
//            cell.setBorder(Rectangle.NO_BORDER);
//            cell.setColspan(columns);
//            cell.setPaddingLeft(80);
//            table.addCell(cell);

           table.setHeaderRows(1);
           
            for (int i = 0; i < columns; i++) {
                table.addCell(new Phrase(columnNames[i], FontFactory.getFont("Helvetica", 12.0F, Font.BOLD)));
            }
            ObservableList<RefereeCommission> list = table_to_export.getItems();
            RefereeCommission refCom;
            for (RefereeCommission rec : list) {
                refCom = rec;
                table.addCell(new Phrase("" + refCom.getDoctorName(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase(refCom.getPatientName(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase("" + refCom.getServices(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase("" + refCom.getTotalCom(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase("" + refCom.getDate(), FontFactory.getFont("Helvetica", 11.0F)));
            }
            document.add(table);
            
            document.add(new Phrase("\n"));
            table = new PdfPTable(headerWidth);

            cell = new PdfPCell(new Phrase(total, FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("" , FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            // cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);

            document.add(table);
           
            
            cell = new PdfPCell(new Phrase(title, FontFactory.getFont("Helvetica", 13.5F, com.itextpdf.text.Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);
            
            
            document.close();
            openfile(FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
    public static void generatePaymentsReport(TableView table_to_export, String path, String title, String total,String type) {
        String FILE = SystemVariables.REPORT_FOLDER + path;
        switch (type) {
            case "ALL":
       
        String columnNames[] = {"PATIENT'S NAME ","PAYMENT MODE ", "AMOUNT", "DISCOUNT", "DATE PAID"};

        int index = 2;
        try {
            Rectangle rect;
            if (index == 1) {
                rect = new Rectangle(700.0F, 1024.0F);
            } else {
                rect = new Rectangle(1024.0F, 700.0F);
            }
            Document document = new Document();
            document.setPageSize(rect);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(FILE));
           writer.setPageEvent(new WaterMarkGenerator());
            document.open();
            int columns = columnNames.length;

            float[] colsWidth = new float[columns];
            for (int i = 0; i < columns; i++) {

                colsWidth[i] = 3.0F;

            }

            float[] headerWidth = new float[2];
            headerWidth[0] = 20.0F;
            headerWidth[1] = 60.F;
            PdfPTable table = new PdfPTable(headerWidth);
            if (index == 1) {
                table.setWidthPercentage(100.0F);
            } else {
                table.setWidthPercentage(75.0F);
            }

            PdfPCell cell;

            document.add(ReportGeneratorJtables.reportHeader(headerWidth, index));
            document.add(new Phrase("\n"));

            table = new PdfPTable(colsWidth);
            cell = new PdfPCell(new Phrase("MEDSCAN DIAGNOSTIC CENTRE", FontFactory.getFont("Helvetica", 35.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(80);
            table.addCell(cell);

            table.setHeaderRows(1);

            cell = new PdfPCell(new Phrase(title, FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);

            document.add(table);
            document.add(new Phrase("\n"));
            table = new PdfPTable(colsWidth);

            table.setHeaderRows(1);
            for (int i = 0; i < columns; i++) {
                table.addCell(new Phrase(columnNames[i], FontFactory.getFont("Helvetica", 12.0F, Font.BOLD)));
            }
            ObservableList<Payments> list = table_to_export.getItems();
            Payments patientsPay;
            for (Payments rec : list) {
                patientsPay = rec;
                table.addCell(new Phrase("" + patientsPay.getPatientName(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase(patientsPay.getPaymentMode(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase("" + patientsPay.getAmount(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase("" + patientsPay.getCashDiscount(), FontFactory.getFont("Helvetica", 11.0F)));
                 table.addCell(new Phrase("" + patientsPay.getDatePaid(), FontFactory.getFont("Helvetica", 11.0F)));
           
            }
            document.add(table);
            
            document.add(new Phrase("\n"));
            table = new PdfPTable(headerWidth);

            cell = new PdfPCell(new Phrase(total, FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("" , FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            // cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);

            document.add(table);
           
            
            cell = new PdfPCell(new Phrase(title, FontFactory.getFont("Helvetica", 13.5F, com.itextpdf.text.Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);
            
            
            document.close();
            openfile(FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
                break;

            case "CASH":
       
        String columnNamesCash[] = {"PATIENT'S NAME ","PAYMENT MODE ", "AMOUNT", "DISCOUNT", "DATE PAID"};

        int index1 = 2;
        try {
            Rectangle rect;
            if (index1 == 1) {
                rect = new Rectangle(700.0F, 1024.0F);
            } else {
                rect = new Rectangle(1024.0F, 700.0F);
            }
            Document document = new Document();
            document.setPageSize(rect);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(FILE));
           writer.setPageEvent(new WaterMarkGenerator());
            document.open();
            int columns = columnNamesCash.length;

            float[] colsWidth = new float[columns];
            for (int i = 0; i < columns; i++) {

                colsWidth[i] = 3.0F;

            }

            float[] headerWidth = new float[2];
            headerWidth[0] = 20.0F;
            headerWidth[1] = 60.F;
            PdfPTable table = new PdfPTable(headerWidth);
            if (index1 == 1) {
                table.setWidthPercentage(100.0F);
            } else {
                table.setWidthPercentage(75.0F);
            }

            PdfPCell cell;

            document.add(ReportGeneratorJtables.reportHeader(headerWidth, index1));
            document.add(new Phrase("\n"));

            table = new PdfPTable(colsWidth);
            
            cell = new PdfPCell(new Phrase("MEDSCAN DIAGNOSTIC CENTRE", FontFactory.getFont("Helvetica", 35.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(80);
            table.addCell(cell);

            table.setHeaderRows(1);

            cell = new PdfPCell(new Phrase(title, FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);

            document.add(table);
            document.add(new Phrase("\n"));
            table = new PdfPTable(colsWidth);

            table.setHeaderRows(1);
            for (int i = 0; i < columns; i++) {
                table.addCell(new Phrase(columnNamesCash[i], FontFactory.getFont("Helvetica", 12.0F, Font.BOLD)));
            }
            ObservableList<Payments> list = table_to_export.getItems();
            Payments patientsPay;
            for (Payments rec : list) {
                patientsPay = rec;
                table.addCell(new Phrase("" + patientsPay.getPatientName(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase(patientsPay.getPaymentMode(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase("" + patientsPay.getAmount(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase("" + patientsPay.getCashDiscount(), FontFactory.getFont("Helvetica", 11.0F)));
                 table.addCell(new Phrase("" + patientsPay.getDatePaid(), FontFactory.getFont("Helvetica", 11.0F)));
           
            }
            document.add(table);
            
            document.add(new Phrase("\n"));
            table = new PdfPTable(headerWidth);

            cell = new PdfPCell(new Phrase(total, FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("" , FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            // cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);

            document.add(table);
           
            
            cell = new PdfPCell(new Phrase(title, FontFactory.getFont("Helvetica", 13.5F, com.itextpdf.text.Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);
            
            
            document.close();
            openfile(FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
                break;

            case "BANK":
                
           String columnNamesBank[] = {"PATIENT'S NAME ","BANK NAME ","ACCOUNT NUMBER ",  "AMOUNT", "DISCOUNT", "DATE PAID"};

        int index2 = 2;
        try {
            Rectangle rect;
            if (index2 == 1) {
                rect = new Rectangle(700.0F, 1024.0F);
            } else {
                rect = new Rectangle(1024.0F, 700.0F);
            }
            Document document = new Document();
            document.setPageSize(rect);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(FILE));
           writer.setPageEvent(new WaterMarkGenerator());
            document.open();
            int columns = columnNamesBank.length;

            float[] colsWidth = new float[columns];
            for (int i = 0; i < columns; i++) {

                colsWidth[i] = 3.0F;

            }

            float[] headerWidth = new float[2];
            headerWidth[0] = 20.0F;
            headerWidth[1] = 60.F;
            PdfPTable table = new PdfPTable(headerWidth);
            if (index2 == 1) {
                table.setWidthPercentage(100.0F);
            } else {
                table.setWidthPercentage(75.0F);
            }

            PdfPCell cell;

            document.add(ReportGeneratorJtables.reportHeader(headerWidth, index2));
            document.add(new Phrase("\n"));

            table = new PdfPTable(colsWidth);
            
            cell = new PdfPCell(new Phrase("MEDSCAN DIAGNOSTIC CENTRE", FontFactory.getFont("Helvetica", 35.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(80);
            table.addCell(cell);

            table.setHeaderRows(1);

            cell = new PdfPCell(new Phrase(title, FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);

            document.add(table);
            document.add(new Phrase("\n"));
            table = new PdfPTable(colsWidth);

            table.setHeaderRows(1);
            for (int i = 0; i < columns; i++) {
                table.addCell(new Phrase(columnNamesBank[i], FontFactory.getFont("Helvetica", 12.0F, Font.BOLD)));
            }
            ObservableList<Payments> list = table_to_export.getItems();
            Payments patientsPay;
            for (Payments rec : list) {
                patientsPay = rec;
                table.addCell(new Phrase("" + patientsPay.getPatientName(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase(patientsPay.getBankName(), FontFactory.getFont("Helvetica", 11.0F)));
                 table.addCell(new Phrase(patientsPay.getChequeNo(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase("" + patientsPay.getAmount(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase("" + patientsPay.getCashDiscount(), FontFactory.getFont("Helvetica", 11.0F)));
                 table.addCell(new Phrase("" + patientsPay.getDatePaid(), FontFactory.getFont("Helvetica", 11.0F)));
           
            }
            document.add(table);
            
            document.add(new Phrase("\n"));
            table = new PdfPTable(headerWidth);

            cell = new PdfPCell(new Phrase(total, FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("" , FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            // cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);

            document.add(table);
           
            
            cell = new PdfPCell(new Phrase(title, FontFactory.getFont("Helvetica", 13.5F, com.itextpdf.text.Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);
            
            
            document.close();
            openfile(FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }

                break;

            case "INSURANCE":

                 String columnNamesIinsurance[] = {"PATIENT'S NAME ","INSURANCE NAME ","INSURANCE NUMBER ",  "AMOUNT", "DISCOUNT", "DATE PAID"};

        int index3 = 2;
        try {
            Rectangle rect;
            if (index3 == 1) {
                rect = new Rectangle(700.0F, 1024.0F);
            } else {
                rect = new Rectangle(1024.0F, 700.0F);
            }
            Document document = new Document();
            document.setPageSize(rect);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(FILE));
           writer.setPageEvent(new WaterMarkGenerator());
            document.open();
            int columns = columnNamesIinsurance.length;

            float[] colsWidth = new float[columns];
            for (int i = 0; i < columns; i++) {

                colsWidth[i] = 3.0F;

            }

            float[] headerWidth = new float[2];
            headerWidth[0] = 20.0F;
            headerWidth[1] = 60.F;
            PdfPTable table = new PdfPTable(headerWidth);
            if (index3 == 1) {
                table.setWidthPercentage(100.0F);
            } else {
                table.setWidthPercentage(75.0F);
            }

            PdfPCell cell;

            document.add(ReportGeneratorJtables.reportHeader(headerWidth, index3));
            document.add(new Phrase("\n"));

            table = new PdfPTable(colsWidth);
            
            cell = new PdfPCell(new Phrase("MEDSCAN DIAGNOSTIC CENTRE", FontFactory.getFont("Helvetica", 35.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(80);
            table.addCell(cell);

            table.setHeaderRows(1);

            cell = new PdfPCell(new Phrase(title, FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);

            document.add(table);
            document.add(new Phrase("\n"));
            table = new PdfPTable(colsWidth);

            table.setHeaderRows(1);
            for (int i = 0; i < columns; i++) {
                table.addCell(new Phrase(columnNamesIinsurance[i], FontFactory.getFont("Helvetica", 12.0F, Font.BOLD)));
            }
            ObservableList<Payments> list = table_to_export.getItems();
            Payments patientsPay;
            for (Payments rec : list) {
                patientsPay = rec;
                table.addCell(new Phrase("" + patientsPay.getPatientName(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase(patientsPay.getInsuranceName(), FontFactory.getFont("Helvetica", 11.0F)));
                 table.addCell(new Phrase(patientsPay.getPolicyNo(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase("" + patientsPay.getAmount(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase("" + patientsPay.getCashDiscount(), FontFactory.getFont("Helvetica", 11.0F)));
                 table.addCell(new Phrase("" + patientsPay.getDatePaid(), FontFactory.getFont("Helvetica", 11.0F)));
           
            }
            document.add(table);
            
            document.add(new Phrase("\n"));
            table = new PdfPTable(headerWidth);

            cell = new PdfPCell(new Phrase(total, FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("" , FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            // cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);

            document.add(table);
           
            
            cell = new PdfPCell(new Phrase(title, FontFactory.getFont("Helvetica", 13.5F, com.itextpdf.text.Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);
            
            
            document.close();
            openfile(FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }

                
                break;

            default:
                break;
        }
        
    } 
    
    public static void generateFinanceReport(TableView table_to_export, String path, String title, 
            String totalExpress,String totalInternalReq,String totalLPO,String totalExpenditure,
            String totalIncome,String totalDiscount,String totalNetIncome) {
        String FILE = SystemVariables.REPORT_FOLDER + path;
        String columnNames[] = {"PATIENT'S NAME ", "SERVICE TYPE", "SERVICE NAME", "COST"};

        int index = 2;
        try {
            Rectangle rect;
            if (index == 1) {
                rect = new Rectangle(700.0F, 1024.0F);
            } else {
                rect = new Rectangle(1024.0F, 700.0F);
            }
            Document document = new Document();
            document.setPageSize(rect);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(FILE));
           writer.setPageEvent(new WaterMarkGenerator());
            document.open();
            int columns = columnNames.length;

            float[] colsWidth = new float[columns];
            for (int i = 0; i < columns; i++) {

                colsWidth[i] = 3.0F;

            }

            float[] headerWidth = new float[2];
            headerWidth[0] = 20.0F;
            headerWidth[1] = 60.F;
            PdfPTable table = new PdfPTable(headerWidth);
            if (index == 1) {
                table.setWidthPercentage(100.0F);
            } else {
                table.setWidthPercentage(75.0F);
            }

            PdfPCell cell;

            document.add(ReportGeneratorJtables.reportHeader(headerWidth, index));
            document.add(new Phrase("\n"));

            table = new PdfPTable(colsWidth);
            
            cell = new PdfPCell(new Phrase("MEDSCAN DIAGNOSTIC CENTRE", FontFactory.getFont("Helvetica", 35.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(80);
            table.addCell(cell);

            table.setHeaderRows(1);

            cell = new PdfPCell(new Phrase(title, FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);

            document.add(table);
            document.add(new Phrase("\n"));
            table = new PdfPTable(colsWidth);

            document.add(new Phrase("\n"));
            table = new PdfPTable(headerWidth);
            //start rec
            cell = new PdfPCell(new Phrase("TOTAL EXPENSES :      "+totalExpress+"", FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("TOTAL EXPENSES :      "+totalExpress+"", FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n"));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("TOTAL INTERNAL REQUISITION : "+totalInternalReq+"", FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("\n"));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("TOTAL LPO : "+totalLPO+"", FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n"));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("TOTAL EXPENDITURE  : "+totalExpenditure+"", FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n"));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("TOTAL INCOME : "+totalIncome+"", FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("\n"));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("TOTAL DISCOUNT : "+totalDiscount+"", FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("TOTAL NET INCOME : "+totalNetIncome+"" , FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(60);
            table.addCell(cell);

            document.add(table);
           
            
            
            
            document.close();
            openfile(FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
     public static void generateCustomeFinanceReport( String path, String title, 
            String totalExpress,String totalInternalReq,String totalLPO,String totalExpenditure,
            String totalIncome,String totalDiscount,String totalNetIncome) {
        String FILE = SystemVariables.REPORT_FOLDER + path;
        String columnNames[] = {"INCOME ", "AMOUNT"};

        int index = 2;
        try {
            Rectangle rect;
            if (index == 1) {
                rect = new Rectangle(700.0F, 1024.0F);
            } else {
                rect = new Rectangle(1024.0F, 700.0F);
            }
            Document document = new Document();
            document.setPageSize(rect);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(FILE));
            writer.setPageEvent(new WaterMarkGenerator());
            document.open();
            int columns = columnNames.length;

            float[] colsWidth = new float[columns];
            for (int i = 0; i < columns; i++) {

                colsWidth[i] = 3.0F;

            }

            float[] headerWidth = new float[2];
            headerWidth[0] = 20.0F;
            headerWidth[1] = 60.F;
            PdfPTable table = new PdfPTable(headerWidth);
            if (index == 1) {
                table.setWidthPercentage(100.0F);
            } else {
                table.setWidthPercentage(75.0F);
            }

            PdfPCell cell;
            PdfPCell cell1;

            document.add(ReportGeneratorJtables.reportHeader(headerWidth, index));

            table = new PdfPTable(colsWidth);

            cell = new PdfPCell(new Phrase("MEDSCAN DIAGNOSTIC CENTRE", FontFactory.getFont("Helvetica", 35.5F, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(80);
            table.addCell(cell);
             
             
             cell = new PdfPCell(new Phrase("FINANCIAL SUMMARY", FontFactory.getFont("Helvetica", 35.5F, Font.ITALIC)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(150);
            table.addCell(cell);
             document.add(table);
             
            table.setHeaderRows(1);

           

           
            document.add(new Phrase("\n"));
            table = new PdfPTable(colsWidth);

            for (int i = 0; i < columns; i++) {
                table.addCell(new Phrase(columnNames[i], FontFactory.getFont("Helvetica", 25.0F, Font.PLAIN)));
            }
            cell = new PdfPCell(new Phrase(" Income ", FontFactory.getFont("Helvetica", 13.5F, Font.PLAIN)));
           cell.setFixedHeight(30f);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("" + totalIncome, FontFactory.getFont("Helvetica", 13.5F, Font.PLAIN)));
             cell.setFixedHeight(30f);
             table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Discount   ", FontFactory.getFont("Helvetica", 13.5F, Font.PLAIN)));
            cell.setFixedHeight(30f);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("- " + totalDiscount, FontFactory.getFont("Helvetica", 13.5F, Font.PLAIN)));
           cell.setFixedHeight(30f);
             table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("TOTAL INCOME   ", FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
           cell.setFixedHeight(30f);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("" + totalDiscount, FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setFixedHeight(30f);
            table.addCell(cell);
            

            cell1 = new PdfPCell(new Phrase("EXPENDITURES   ", FontFactory.getFont("Helvetica", 15.4F, Font.BOLD)));
            cell1.setFixedHeight(40f);
            table.addCell(cell1);
            cell1 = new PdfPCell(new Phrase("AMOUNT    " , FontFactory.getFont("Helvetica", 14.5F, Font.BOLD)));
            cell1.setFixedHeight(40f);
            table.addCell(cell1);

            cell1 = new PdfPCell(new Phrase("Total Expenses ", FontFactory.getFont("Helvetica", 15.4F, Font.PLAIN)));
           cell1.setFixedHeight(40f);
            table.addCell(cell1);
            cell1 = new PdfPCell(new Phrase("-" + totalExpress, FontFactory.getFont("Helvetica", 14.5F, Font.PLAIN)));
            cell1.setFixedHeight(40f);
            table.addCell(cell1);

            cell1 = new PdfPCell(new Phrase("Total Internal Requisition ", FontFactory.getFont("Helvetica", 15.4F, Font.ITALIC)));
            cell1.setFixedHeight(40f);
            table.addCell(cell1);
            cell1 = new PdfPCell(new Phrase("-" + totalInternalReq, FontFactory.getFont("Helvetica", 14.5F, Font.PLAIN)));
            cell1.setFixedHeight(40f);
            table.addCell(cell1);

            cell1 = new PdfPCell(new Phrase("Total LPO ", FontFactory.getFont("Helvetica", 15.4F, Font.PLAIN)));
            cell1.setFixedHeight(40f);
            table.addCell(cell1);
            cell1 = new PdfPCell(new Phrase("-" + totalLPO, FontFactory.getFont("Helvetica", 14.5F, Font.PLAIN)));
            cell1.setFixedHeight(40f);
            table.addCell(cell1);
            
            cell1 = new PdfPCell(new Phrase("TOTAL EXPENDITURE ", FontFactory.getFont("Helvetica", 15.4F, Font.BOLD)));
           cell1.setFixedHeight(40f);
            table.addCell(cell1);
            cell1 = new PdfPCell(new Phrase("-" +totalExpenditure,  FontFactory.getFont("Helvetica", 14.5F, Font.BOLD)));
           cell1.setFixedHeight(40f);
            table.addCell(cell1);
            
            cell1 = new PdfPCell(new Phrase("NET INCOME ", FontFactory.getFont("Helvetica", 15.4F, Font.BOLD)));
            cell1.setFixedHeight(40f);
            table.addCell(cell1);
            cell1 = new PdfPCell(new Phrase("" +totalNetIncome,  FontFactory.getFont("Helvetica", 14.5F, Font.BOLD)));
            cell1.setFixedHeight(40f);
            table.addCell(cell1);
            

            cell = new PdfPCell(new Phrase("Generated by : MARK BET                  Date : " + SYSTEM_DATE, FontFactory.getFont("Helvetica", 12.5F, Font.ITALIC)));
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setColspan(columns);
            cell.setPaddingLeft(80);
            table.addCell(cell);
            document.add(table);

            document.close();
            openfile(FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
