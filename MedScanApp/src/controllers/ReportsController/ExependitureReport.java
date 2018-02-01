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
import com.solutions.entorno.utilities.WaterMarkGenerator;
import controllers.Expenses;
import controllers.InternalRequisition;
import controllers.LocalOrderPurchase;
import controllers.RefereeCommission;
import java.awt.Font;
import java.io.FileOutputStream;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 *
 * @author YoungGucha
 */
public class ExependitureReport {
   
    public static void generateExpensesReport(TableView table_to_export, String path, String title, String total) {
        String FILE = SystemVariables.REPORT_FOLDER + path;
       String columnNames[] = {"EXPENSE  NAME ","EXPENSE TYPE ", "DESCRIPTION", "TOTAL AMOUNT", "DATE INCURRED"};
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

            table.setHeaderRows(1);
            for (int i = 0; i < columns; i++) {
                table.addCell(new Phrase(columnNames[i], FontFactory.getFont("Helvetica", 12.0F, Font.BOLD)));
            }
            ObservableList<Expenses> list = table_to_export.getItems();
            Expenses expenses;
            for (Expenses rec : list) {
                expenses = rec;
                table.addCell(new Phrase("" + expenses.getExpenseType(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase(expenses.getExpenseName(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase("" + expenses.getDescription(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase("" + expenses.getAmount(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase("" + expenses.getDateIncurred(), FontFactory.getFont("Helvetica", 11.0F)));
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
     
    public static void generateInternalReqReport(TableView table_to_export, String path, String title, String total) {
        String FILE = SystemVariables.REPORT_FOLDER + path;
       String columnNames[] = {" TYPE ","NAME ", "DESCRIPTION", "QUANTITY", "TOTAL COST" , "DATE"};
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

            table.setHeaderRows(1);
            for (int i = 0; i < columns; i++) {
                table.addCell(new Phrase(columnNames[i], FontFactory.getFont("Helvetica", 12.0F, Font.BOLD)));
            }
            ObservableList<InternalRequisition> list = table_to_export.getItems();
            InternalRequisition internalRequisition;
            for (InternalRequisition rec : list) {
                internalRequisition = rec;
                table.addCell(new Phrase("" + internalRequisition.getExpenseType(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase(internalRequisition.getItemName(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase("" + internalRequisition.getDescription(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase("" + internalRequisition.getQuantity(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase("" + internalRequisition.getCost(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase("" + internalRequisition.getDateOrdered(), FontFactory.getFont("Helvetica", 11.0F)));
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
    
     
    public static void generateLPOReqReport(TableView table_to_export, String path, String title, String total) {
        String FILE = SystemVariables.REPORT_FOLDER + path;
       String columnNames[] = {" TYPE ","NAME ", "DESCRIPTION", "QUANTITY", "TOTAL COST", "DATE"};
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

            table.setHeaderRows(1);
            for (int i = 0; i < columns; i++) {
                table.addCell(new Phrase(columnNames[i], FontFactory.getFont("Helvetica", 12.0F, Font.BOLD)));
            }
            ObservableList<LocalOrderPurchase> list = table_to_export.getItems();
            LocalOrderPurchase localOrderPurchase;
            for (LocalOrderPurchase rec : list) {
                localOrderPurchase = rec;
                table.addCell(new Phrase("" + localOrderPurchase.getExpenseType(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase(localOrderPurchase.getItemName(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase("" + localOrderPurchase.getDescription(), FontFactory.getFont("Helvetica", 11.0F)));
                 table.addCell(new Phrase("" + localOrderPurchase.getQuantity(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase("" + localOrderPurchase.getCost(), FontFactory.getFont("Helvetica", 11.0F)));
                table.addCell(new Phrase("" + localOrderPurchase.getDateOrdered(), FontFactory.getFont("Helvetica", 11.0F)));
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
    
}
