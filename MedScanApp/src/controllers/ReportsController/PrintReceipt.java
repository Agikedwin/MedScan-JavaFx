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
import static com.solutions.entorno.utilities.SystemVariables.SYSTEM_TIME;
import com.solutions.entorno.utilities.WaterMarkGenerator;
import controllers.Services;
import java.awt.Font;
import java.io.FileOutputStream;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 *
 * @author YoungGucha
 */
public class PrintReceipt {

    public static void generateReceipt(String path, String PatrinetName, String services, String totalCost,
            String paymentMode, String discount, String amountReceived, double balance) {
        String FILE = SystemVariables.REPORT_FOLDER + path;
        String columnNames[] = {"SERVICE NAME ", "SERVICE COST"};

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

//            cell = new PdfPCell(new Phrase("MEDSCAN DIAGNOSTIC CENTRE", FontFactory.getFont("Helvetica", 35.5F, Font.BOLD)));
//            cell.setBorder(Rectangle.NO_BORDER);
//            cell.setColspan(columns);
//            cell.setPaddingLeft(80);
//            table.addCell(cell);

            table.setHeaderRows(1);

            cell = new PdfPCell(new Phrase("          PATIENT'S NAME:  " + PatrinetName, FontFactory.getFont("Helvetica", 20.5F, Font.ITALIC)));
            cell.setBorder(index);
            cell.setColspan(columns);
            cell.setPaddingLeft(80);
            table.addCell(cell);

            document.add(table);
            document.add(new Phrase("\n"));
            table = new PdfPTable(colsWidth);

            for (int i = 0; i < columns; i++) {
                table.addCell(new Phrase(columnNames[i], FontFactory.getFont("Helvetica", 25.0F, Font.PLAIN)));
            }
            cell = new PdfPCell(new Phrase(services, FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setFixedHeight(150f);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("" + totalCost, FontFactory.getFont("Helvetica", 13.5F, Font.BOLD)));
            cell.setFixedHeight(150f);
            table.addCell(cell);

            cell1 = new PdfPCell(new Phrase("TOTAL ", FontFactory.getFont("Helvetica", 15.4F, Font.ITALIC)));
            cell1.setFixedHeight(30f);
            table.addCell(cell1);
            cell1 = new PdfPCell(new Phrase("" + totalCost, FontFactory.getFont("Helvetica", 14.5F, Font.BOLD)));
            cell1.setFixedHeight(30f);
            table.addCell(cell1);

            cell1 = new PdfPCell(new Phrase("TOTAL DISCOUNT ", FontFactory.getFont("Helvetica", 15.4F, Font.ITALIC)));
            cell1.setFixedHeight(30f);
            table.addCell(cell1);
            cell1 = new PdfPCell(new Phrase("" +discount, FontFactory.getFont("Helvetica", 14.5F, Font.BOLD)));
            cell1.setFixedHeight(30f);
            table.addCell(cell1);

            cell1 = new PdfPCell(new Phrase("AMOUNT RECEIVED ", FontFactory.getFont("Helvetica", 15.4F, Font.ITALIC)));
            cell1.setFixedHeight(30f);
            table.addCell(cell1);
            cell1 = new PdfPCell(new Phrase("" +amountReceived, FontFactory.getFont("Helvetica", 14.5F, Font.BOLD)));
            cell1.setFixedHeight(30f);
            table.addCell(cell1);

            cell1 = new PdfPCell(new Phrase("BALANCE ", FontFactory.getFont("Helvetica", 15.4F, Font.ITALIC)));
            cell1.setFixedHeight(30f);
            table.addCell(cell1);
            cell1 = new PdfPCell(new Phrase("" + balance, FontFactory.getFont("Helvetica", 14.5F, Font.BOLD)));
           cell1.setFixedHeight(30f);
            table.addCell(cell1);

            cell = new PdfPCell(new Phrase("Your are Served by : MARK BET                  Date : " + SYSTEM_DATE, FontFactory.getFont("Helvetica", 12.5F, Font.ITALIC)));
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
