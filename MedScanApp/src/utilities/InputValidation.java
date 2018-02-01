/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author YoungGucha
 */
public class InputValidation {
    Pattern pattern;
    Matcher matcher;
    private static Pattern emailNamePtrn = Pattern.compile(
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@mod.go.ke");

    private final String EMPTY_MESSAGE;
    private final String INVALID_CHARACTER_MESSAGE;
    private final String INVALID_EMAIL_MESSAGE;
    private final String INVALID_SELECTED_ITEM;
    private String errorMessage;
    
    

    /**
     *
     */
    public InputValidation() {
        EMPTY_MESSAGE = "Field cannot be empty";
        INVALID_CHARACTER_MESSAGE = "Field contains invalid characters";
        INVALID_EMAIL_MESSAGE = "Invalid email address. Try again !";
        INVALID_SELECTED_ITEM = "Please Select the Item";
        errorMessage = "";
    }

    public boolean isEmpty(String input) {
        return input.equalsIgnoreCase("");
    }

    public boolean isEmpty(Object obj) {
        return obj == null;
    }

    /**
     *
     * @param name
     * @return
     */
    public boolean isValidName(String name) {
        String expression = "^[a-zA-Z'-]*$";
        pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(name);
        return matcher.matches();
    }

    /**
     *
     * @param email
     * @return
     */
//    public boolean isValidEmailAddress(String email) {
//        boolean result = true;
//        try {
//            InternetAddress emailAddr = new InternetAddress(email);
//            emailAddr.validate();
//
//        } catch (AddressException ex) {
//            result = false;
//        }
//        return result;
//    }
    
    public static boolean validateEmailAddress(String userName) {
        Matcher mtch = emailNamePtrn.matcher(userName);
        if (mtch.matches()) {
            return true;
        }
        return false;
    }

    public static void verifyEmail(JTextField t) {
        String input;
        InputValidation valid = new InputValidation();
        input = t.getText();
        t.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                displayMessage("Focus gained", e);
            }

            public void focusLost(FocusEvent e) {
                displayMessage("Focus lost", e);
            }

            void displayMessage(String prefix, FocusEvent e) {
                System.out.println(prefix
                        + (e.isTemporary() ? " (temporary):" : ":")
                        + e.getComponent().getClass().getName()
                        + "; Opposite component: "
                        + (e.getOppositeComponent() != null ? e.getOppositeComponent().getClass().getName()
                        : "null"));
            }
        });
//        if (valid.isValidEmailAddress(input) == true) {
//            System.out.println("True Ok");
//        } else {
//            Util.validateMandatoryFields(t);
//        }

    }
    
    

//    public static void validateTextField(final JTextField tfField) {
//        tfField.addKeyListener(new KeyAdapter() {
//
//            public void keyTyped(KeyEvent e) {
//                Border currentBorderColor = null;
//                currentBorderColor = tfField.getBorder();
//                
//
//                char c = e.getKeyChar();
//                if (!((c >= '0') && (c <= '9')
//                        || (c == KeyEvent.VK_BACK_SPACE)
//                        || (c == KeyEvent.VK_DELETE))) {
//                    getToolkit().beep();
//                    e.consume();
//                    tfField.setBorder(new LineBorder(color.red));
//                    JDialog popup = new JDialog();
//                    popup.setSize(0,0);
//                    popup.setUndecorated(true);
//                    popup.getContentPane().setBackground(Color.pink);
//                    popup.getContentPane().add(messageLabel);
//                    popup.setLocationRelativeTo(tfField);
//                    point = popup.getLocation();
//                   cDim = tfField.getSize();
//                    popup.setLocation(point.x - (int) cDim.getWidth() / 2,
//                    point.y + (int) cDim.getHeight() / 2);
//                   popup.pack();
//                    
//                    popup.setVisible(true);
//                } else {
//                    tfField.setBorder(new LineBorder(Color.gray));
//                    tfField.setBackground(Color.white);
//                }
//            }
//        });
//
//    }

    public boolean isValidSelectedItem(String item) {
        boolean result = true;
        JComboBox comboBox = new JComboBox();
        item = comboBox.getSelectedItem().toString();
        if (item.equalsIgnoreCase("--Select--")) {
            result = false;
        } else {
            result = true;
        }

        return result;

    }

    /**
     *
     * @param dateOfBirth
     * @return
     */
    public boolean isValidDateOfBirth(Date dateOfBirth) {

        boolean valid;
        //check that dateOfBirth is not in the future
        valid = dateOfBirth.before(new Date());
        if (!valid) {
            errorMessage = "Invalid DoB";
        } else {
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
            //max age allowed is 120
            if (age > 120) {
                errorMessage = "Age is greater than 120";
                valid = false;
            }

        }
        return valid;
    }

    /**
     *
     * @param input
     * @return
     */
    public boolean isAlphanumeric(String input) {
        String expression = "^[a-zA-Z0-9]*$";
        pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(input);
        return matcher.matches();
    }

    /**
     *
     * @param input
     * @return
     */
    public boolean isNumeric(String input) {
        String expression = "^[0-9]*$";
        pattern = Pattern.compile(expression);
        matcher = pattern.matcher(input);
        return matcher.matches();

    }

//    public  boolean verifyDate(Date tfDate){
//        boolean result;
//        result = tfDate.before(new Date());
//        return result;
//        
//    }
    /**
     *
     * @return
     */
    public String getEmptyMessage() {
        return EMPTY_MESSAGE;
    }

    /**
     *
     * @return
     */
    public String getInvalidCharacterMessage() {
        return INVALID_CHARACTER_MESSAGE;
    }

    /**
     *
     * @return
     */
    public String getInvalidEmailMessage() {
        return INVALID_EMAIL_MESSAGE;
    }

    public String getInvalidSelectedItemMessage() {
        return INVALID_SELECTED_ITEM;
    }

    /**
     *
     * @return
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    public static void main(String[]args){
     InputValidation inputValidation=new InputValidation();
     System.out.println("This Address is :"+inputValidation.validateEmailAddress("walterbuyu@mod.go.ke"));
     System.out.println(inputValidation.isNumeric("3241324"));
        System.out.println("Name :"+inputValidation.isValidName("Robert"));
    }
}
