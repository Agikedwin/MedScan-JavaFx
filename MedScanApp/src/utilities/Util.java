/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.rmi.RemoteException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author YoungGucha
 */
public class Util {
   private static String base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabsdefghijklmnopqrstuvwxyz";
    private static Random random = new Random();

    public static void printTable(JTable table, JInternalFrame parent) {
        try {
            /* print the table */
            /* determine the print mode */
            JTable.PrintMode mode = JTable.PrintMode.FIT_WIDTH;
            String title = new String(("List of " + parent.getTitle()).toUpperCase());
            boolean complete = table.print(mode, new MessageFormat(title), new MessageFormat("Page {0}"),
                    true, null,
                    true, null);

            /* if printing completes */
            if (complete) {
                /* show a success message */
                JOptionPane.showMessageDialog(table.getParent(),
                        "Printing Complete",
                        "Printing Result",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                /* show a message indicating that printing was cancelled */
                JOptionPane.showMessageDialog(table.getParent(),
                        "Printing Cancelled",
                        "Printing Result",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PrinterException pe) {
            /* Printing failed, report to the user */
            JOptionPane.showMessageDialog(table.getParent(),
                    "Printing Failed: " + pe.getMessage(),
                    "Printing Result",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static int getAge(Date dateOfBirth) {
        Calendar dob = Calendar.getInstance();
        dob.setTime(dateOfBirth);
        Calendar today = Calendar.getInstance();
        return today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

    }

    public static MediaPlayer getMediaPlayer(File audioFile) {
        JFXPanel fxPanel = new JFXPanel();
        String fileURI = audioFile.toURI().toString();
        Media audio = new Media(fileURI);
        return new MediaPlayer(audio);
    }

    public static File getDMILogo() {
        String path = Util.class.getResource("/resources/dmi_report_logo.png").getFile();
        File dmiLogo = new File(path);
        return dmiLogo;
    }

    public static void setLookAndFeel() {
        /* Use an appropriate Look and Feel */
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException | ClassNotFoundException ex) {
            ex.printStackTrace();
           
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        //Schedule a job for the event-dispatching thread:
        //adding TrayIcon.

    }

    public static void centerWindowOnScreen(Component c) {
        // get Dimension of user's screen
        Dimension screenDimension
                = Toolkit.getDefaultToolkit().getScreenSize();

        // use screen width and height and application width
        // and height to center application on user's screen
        int width = c.getSize().width;
        int height = c.getSize().height;
        int x = (screenDimension.width - width) / 2;
        int y = (screenDimension.height - height) / 2;

        // place application window at screen's center
        c.setBounds(x, y, width, height);
    }

    public static int[] getScreenSize() {
        int widthHeight[] = new int[2];
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        widthHeight[0] = screenSize.width;
        widthHeight[1] = screenSize.height;
        return widthHeight;
    }

    public static String DateMysqlFormat(Date date) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String DateTimeMysqlFormat(Date date) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(date);
    }

    public static Date dateFromMysqlFormat(String date) {
        Date inputDate;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            inputDate = dateFormat.parse(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
            inputDate = null;
            
        }
        return inputDate;
    }

    
   

    /**
     * Get a random String
     *
     * @param length type Integer. Length of the String
     * @return
     */
    public static String randomString(int length) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < length; i++) {
            b.append(base.charAt(random.nextInt(base.length())));
        }
        return b.toString();
    }

    /**
     * Open File Using a default Program
     *
     * @param file type File
     */
    public static void openFileUsingDefaultProgram(File file) {
        if (java.awt.Desktop.getDesktop().isSupported(java.awt.Desktop.Action.OPEN)) {
            try {
                java.awt.Desktop.getDesktop().open(file);
            } catch (IOException ex) {
               // Util.logError(Util.class.getName(), ex.getMessage());
            }
        }
    }

    /**
     * Get date from MYSQL format
     *
     * @param date type String
     * @param format type String
     * @return Date
     */
    public static Date dateFromMysqlFormat(String date, String format) {
        Date inputDate;
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            inputDate = dateFormat.parse(date);
        } catch (ParseException ex) {
            inputDate = null;
          //  Util.logError(Util.class.getName(), ex.getMessage());
        }
        return inputDate;
    }

    /**
     * Return a Boolean value for Email Sent
     *
     * @param email type Object of Email
     * @return Boolean True if the Email was sent and False otherwise
     */
   
    /**
     * Print a document in PDF format
     *
     * @param filename type name of the file
     */
    

    /**
     * Convert to SQL date format
     *
     * @param utilDate type Date
     * @return SQL date
     */
    public static java.sql.Date utilDateToSqlDate(Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }

    /**
     * Get the Boolean value for adult
     *
     * @param dateOfBirth type Date
     * @return Boolean True for adult above 18 years and false otherwise
     */
    public boolean isAdult(Date dateOfBirth) {

        Calendar dob = Calendar.getInstance();
        dob.setTime(dateOfBirth);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
            age--;
        } else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
                && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }

        return age >= 18;
    }

    /**
     * Check if the Selected index of a JComboBox Item is Zero
     *
     * @param jComboBox name of the JComboBox Component
     * @return Notifies the User if the selected index is zero
     */
    public static void validateComboBox(JComboBox jComboBox) {
        Border currentBorderColor = null;
        if (jComboBox.getSelectedIndex() == 0) {
            currentBorderColor = jComboBox.getBorder();
            jComboBox.setBackground(Color.PINK);
            jComboBox.setBorder(new LineBorder(Color.red));
        } else {
            jComboBox.setBorder(currentBorderColor);
            jComboBox.setBackground(Color.white);

        }

    }

    /**
     * Show a Success Message Dialog for an action performed
     *
     * @param message The Message that appears on the Dialog box
     * @param component The object of the Parent Component
     */
    public static void successDialogMessage(Component component, String message) {
        JOptionPane.showMessageDialog(component, message, "Success Message !", JOptionPane.INFORMATION_MESSAGE);
//        dialog.setVisible(true);

    }

    /**
     * Get the Host IP address
     *
     * @return String Host IP address
     */
    public static String getHostAddress() {
        String hostIp = null;
        try {
            hostIp = InetAddress.getLocalHost().getHostAddress();

        } catch (UnknownHostException ex) {
           // Util.logError(Util.class.getName(), ex.getMessage());
        }
        return hostIp;
    }

    /**
     * Create the System Tray Icon
     */
    public static void createSystemTrayIcon() {
        //Check the SystemTray support
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon
                = new TrayIcon(createImage("/resources/app_icon.png", "tray icon"));
        final SystemTray tray = SystemTray.getSystemTray();

        // Create a popup menu components
        MenuItem openItem = new MenuItem("Open");
        MenuItem exitItem = new MenuItem("Exit BIOAPP");

        //Add components to popup menu
        popup.add(openItem);
        popup.addSeparator();
        popup.add(exitItem);

        trayIcon.setPopupMenu(popup);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
          //  Util.logError(Util.class.getName(), e.getMessage());
            System.out.println("TrayIcon could not be added.");
            return;
        }

        trayIcon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "BIOAPP is running.");
            }
        });
        trayIcon.setToolTip("BIOAPP (Version 1.0)");

        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "You are using BIOAPP version 1.0");
            }
        });

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });
    }

    //Obtain the image URL
    protected static Image createImage(String path, String description) {
        URL imageURL = Util.class.getResource(path);

        if (imageURL == null) {
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }

    /**
     * Show a Error Message Dialog for an action performed
     *
     * @param message The Message that appears on the Dialog box
     * @return Notifies the User if the action performed was not successful
     */
    public static void validateMandatoryFields(JTextField field) {
        Border currentBorderColor = null;
        if (field.getText().isEmpty()) {
            currentBorderColor = field.getBorder();
            field.setBackground(Color.PINK);
            field.setBorder(new LineBorder(Color.RED));
        } else {
            field.setBorder(currentBorderColor);
            field.setBackground(Color.white);
        }
    }

    /**
     * Get the Error Message Dialog box
     *
     * @param message type String Message to be displayed on the dialog
     * @param component type Object for the parent Component
     */
    public static void errorMessage(Component component, String message) {
        JOptionPane.showMessageDialog(component, message, "ERROR MESSAGE !", JOptionPane.ERROR_MESSAGE);

    }

    /**
     * Show a Confirmation Message Dialog for an action performed
     *
     * @param message The Message that appears on the Dialog box
     * @return If a Yes Option is chosen, the action is performed. If No option
     * is chosen the dialog disappears and No action is Performed
     */
    public static void confirmationMessage(Component component, String message) {
        int answer = JOptionPane.showConfirmDialog(component, message, "CONFIRMATION MESSAGE !", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
        } else if (answer == JOptionPane.NO_OPTION) {
        }
    }

    /**
     * encrypt the System error logs file
     */
    

    /**
     * Decrypt the System error log file
     */
   
  /**
     * Logs System Error to the Specified log file
     *
     * @param className  name of the class where the error occured
     * @param message String the message to be written to the log file
     * @return void
     */
   

    /**
     * Logs all the user activities
     *
     * @param action type String
     */
   

    /**
     * Get the activity logs from the server
     * @return activity logs
     */
    

    /**
     * Get the Current date
     *
     * @return String Current date in "yyyy/MM/dd HH:mm:ss" format
     */
    public static String getCurrentDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * Encrypt a file
     * @param fil file path of the file to be encrypted
     * @return returns the file path of the encrypted file
     * @throws InvalidKeyException
     * @throws IOException 
     */
    
}
