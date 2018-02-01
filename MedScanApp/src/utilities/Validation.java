/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author YoungGucha
 */
public class Validation {
     public static void validateNumericalFields(final JTextField jTextField) {
        final JDialog popup = new JDialog();
        Object parent;
        final JLabel messageLabel = new JLabel("Invalid Characters");
        JLabel image;
        final Point point;
        Dimension cDim;
        Color color;

        Border currentBorderColor = null;
        currentBorderColor = jTextField.getBorder();
        jTextField.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {

                char c = e.getKeyChar();
                int x= e.getKeyCode();
                if (!jTextField.getText().isEmpty() && !((c >= '0') && (c <= '9') || c=='-' ||c=='/' || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE)|| (c == KeyEvent.VK_CONTROL) )) {
                    Toolkit.getDefaultToolkit().beep();

                    e.consume();
                    jTextField.setBorder(new LineBorder(Color.red));

                    popup.setSize(0, 0);
                    popup.getContentPane().setBackground(Color.pink);
                    popup.getContentPane().add(messageLabel);
                    popup.setLocationRelativeTo(jTextField);
                    Point point = popup.getLocation();
                    Dimension cDim = jTextField.getSize();
                    popup.setLocation(point.x - (int) cDim.getWidth() / 2,
                            point.y + (int) cDim.getHeight() / 2);
                    popup.pack();
                } else {
                    jTextField.setBorder(new LineBorder(Color.gray));
                    jTextField.setBackground(Color.white);
                }
            }
        });
    }

    /**
     * validate the Email Address fields of a JTextField Component
     *
     * @param emailAddressField email Address Field of the JTextField Component
     *
     */
    public static void validateEmailAdressField(final JTextField emailAddressField) {
        final JDialog popup = new JDialog();
        Object parent;
        final JLabel messageLabel = new JLabel("Invalid Email Address");
        JLabel image;
        final Point point;
        Dimension cDim;
        Color color;

        Border currentBorderColor = null;
        currentBorderColor = emailAddressField.getBorder();
        emailAddressField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                popup.setVisible(false);
                emailAddressField.setBorder(new LineBorder(Color.gray));
                emailAddressField.setBackground(Color.white);
            }

            @Override
            public void focusLost(FocusEvent e) {
                InputValidation valid = new InputValidation();

                String c = emailAddressField.getText();
                
                if (!emailAddressField.getText().isEmpty()  ) {
                    Toolkit.getDefaultToolkit().beep();

                    emailAddressField.setBorder(new LineBorder(Color.red));

                    popup.setSize(0, 0);
                    popup.getContentPane().setBackground(Color.pink);
                    popup.getContentPane().add(messageLabel);
                    popup.setLocationRelativeTo(emailAddressField);
                    Point point = popup.getLocation();
                    Dimension cDim = emailAddressField.getSize();
                    popup.setLocation(point.x - (int) cDim.getWidth() / 2,
                            point.y + (int) cDim.getHeight() / 2);
                    popup.pack();
                } else {
                    emailAddressField.setBorder(new LineBorder(Color.gray));
                    emailAddressField.setBackground(Color.white);
                }
            }

        });
    }

    public static void validateMODEmailAdressField(final JTextField emailAddressField) {
        final JDialog popup = new JDialog();
        Object parent;
        final JLabel messageLabel = new JLabel("Invalid Email Address");
        JLabel image;
        final Point point;
        Dimension cDim;
        Color color;
        Border currentBorderColor = null;
        currentBorderColor = emailAddressField.getBorder();
        emailAddressField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                popup.setVisible(false);
                emailAddressField.setBorder(new LineBorder(Color.gray));
                emailAddressField.setBackground(Color.white);
            }

            @Override
            public void focusLost(FocusEvent e) {
                InputValidation valid = new InputValidation();
                String c = emailAddressField.getText();
                if (!emailAddressField.getText().isEmpty() && (valid.validateEmailAddress(c) == false)) {
                    Toolkit.getDefaultToolkit().beep();
                    emailAddressField.setBorder(new LineBorder(Color.red));
                    popup.setSize(0, 0);
                    popup.getContentPane().setBackground(Color.pink);
                    popup.getContentPane().add(messageLabel);
                    popup.setLocationRelativeTo(emailAddressField);
                    Point point = popup.getLocation();
                    Dimension cDim = emailAddressField.getSize();
                    popup.setLocation(point.x - (int) cDim.getWidth() / 2,
                            point.y + (int) cDim.getHeight() / 2);
                    popup.pack();
                } else {
                    emailAddressField.setBorder(new LineBorder(Color.gray));
                    emailAddressField.setBackground(Color.white);
                }
            }
        });
    }

    /**
     * validate the Date fields of a JDateChooser Component
     *
     * @param date date Field of the JDateChooser Component
     *
     */
//    public static void validateDate(final JDateChooser date) {
//        final JDialog popup = new JDialog();
//        Object parent;
//        final JLabel messageLabel = new JLabel("Invalid Date");
//        JLabel image;
//        final Point point;
//        Dimension cDim;
//        Color color;
//        Border currentBorderColor = null;
//        currentBorderColor = date.getBorder();
//        date.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                popup.setVisible(false);
//                date.setBorder(new LineBorder(Color.gray));
//                date.setBackground(Color.white);
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                if (date.getDate() == null) {
//                    Toolkit.getDefaultToolkit().beep();
//                    date.setBorder(new LineBorder(Color.red));
//                    popup.setSize(0, 0);
//                    popup.getContentPane().setBackground(Color.pink);
//                    popup.getContentPane().add(messageLabel);
//                    popup.setLocationRelativeTo(date);
//                    Point point = popup.getLocation();
//                    Dimension cDim = date.getSize();
//                    popup.setLocation(point.x - (int) cDim.getWidth() / 2,
//                            point.y + (int) cDim.getHeight() / 2);
//                    popup.pack();
//                } else {
//                    date.setBorder(new LineBorder(Color.gray));
//                    date.setBackground(Color.white);
//                }
//            }
//
//        });
//    }

    /**
     * validate the Numerical characters typed into a JTextArea Component
     *
     * @param textArea name of the JTextArea Component
     *
     */
    public static void validateNumericalTextArea(final JTextArea textArea) {
        final JDialog popup = new JDialog();
        Object parent;
        final JLabel messageLabel = new JLabel("Invalid Characters");
        JLabel image;
        final Point point;
        Dimension cDim;
        Color color;

        Border currentBorderColor = null;
        currentBorderColor = textArea.getBorder();
        textArea.addKeyListener(new KeyAdapter() {

            public void keyTyped(KeyEvent e) {

                char c = e.getKeyChar();
                int x= e.getKeyCode();
                if (!textArea.getText().isEmpty() && !((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_ENTER) || (c == KeyEvent.VK_SHIFT)|| (c == KeyEvent.VK_CONTROL) )) {
                    Toolkit.getDefaultToolkit().beep();

                    e.consume();
                    textArea.setBorder(new LineBorder(Color.red));
                    popup.setSize(0, 0);
                    popup.getContentPane().setBackground(Color.pink);
                    popup.getContentPane().add(messageLabel);
                    popup.setLocationRelativeTo(textArea);
                    Point point = popup.getLocation();
                    Dimension cDim = textArea.getSize();
                    popup.setLocation(point.x - (int) cDim.getWidth() / 2,
                            point.y + (int) cDim.getHeight() / 2);
                    popup.pack();
                } else {
                    textArea.setBorder(new LineBorder(Color.gray));
                    textArea.setBackground(Color.white);
                }
            }
        });
    }
    
    
    /**
     * validate the alphabetic characters typed in a JTextField
     *
     * @param textField name of the JTextField Component
     *
     */
    public static void validateAlphaNumericFields(final JTextField textField) {
        final JDialog popup = new JDialog();
        Object parent;
        final JLabel messageLabel = new JLabel("Field Contains Invalid Characters");
        JLabel image;
        final Point point;
        Dimension cDim;
        Color color;
        Border currentBorderColor = null;
        currentBorderColor = textField.getBorder();
        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                int x= e.getKeyCode();
                if (!textField.getText().isEmpty() && !((c >= '0') && (c <= '9')||(c >= 'a') && (c <= 'z') || (c >= 'A') && (c <= 'Z') || (c == '&')
                        || (c == '_') || (c == '-')  || (c == '/')|| (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_SHIFT)|| (c == KeyEvent.VK_CONTROL) )) {
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                    textField.setBorder(new LineBorder(Color.red));
                    popup.setSize(0, 0);
                    popup.getContentPane().setBackground(Color.pink);
                    popup.getContentPane().add(messageLabel);
                    popup.setLocationRelativeTo(textField);
                    Point point = popup.getLocation();
                    Dimension cDim = textField.getSize();
                    popup.setLocation(point.x - (int) cDim.getWidth() / 2,
                            point.y + (int) cDim.getHeight() / 2);
                    popup.pack();
                } else {
                    textField.setBorder(new LineBorder(Color.gray));
                    textField.setBackground(Color.white);
                }
            }
        });
    }

    

    /**
     * validate the alphabetic characters typed in a JTextField
     *
     * @param textField name of the JTextField Component
     *
     */
    public static void validateTextFields(final JTextField textField) {
        
        final JDialog popup = new JDialog();
        Object parent;
        final JLabel messageLabel = new JLabel("Field Contains Invalid Characters");
        JLabel image;
        final Point point;
        Dimension cDim;
        Color color;
        Border currentBorderColor = null;
        currentBorderColor = textField.getBorder();
        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                int x= e.getKeyCode();
                if (!textField.getText().isEmpty() && !((c >= 'a') && (c <= 'z') || (c >= 'A') && (c <= 'Z') ||
                        (c == '&') || (c == '_') || (c == KeyEvent.VK_BACK_SPACE)|| (c == KeyEvent.VK_CONTROL) 
                        || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_SHIFT))) {
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                    textField.setBorder(new LineBorder(Color.red));
                    popup.setSize(0, 0);
                    popup.getContentPane().setBackground(Color.pink);
                    popup.getContentPane().add(messageLabel);
                    popup.setLocationRelativeTo(textField);
                    Point point = popup.getLocation();
                    Dimension cDim = textField.getSize();
                    popup.setLocation(point.x - (int) cDim.getWidth() / 2,
                            point.y + (int) cDim.getHeight() / 2);
                    popup.pack();
                } else {
                    textField.setBorder(new LineBorder(Color.gray));
                    textField.setBackground(Color.white);
                }
            }
        });
    }
 //validate degree to allow special characters
   public static void validateDegreeTextFields(final JTextField textField) {
        
        final JDialog popup = new JDialog();
        Object parent;
        final JLabel messageLabel = new JLabel("Field Contains Invalid Characters");
        JLabel image;
        final Point point;
        Dimension cDim;
        Color color;
        Border currentBorderColor = null;
        currentBorderColor = textField.getBorder();
        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                int x= e.getKeyCode();
                if (!textField.getText().isEmpty() && !((c >= 'a') && (c <= 'z') || (c >= 'A') && (c <= 'Z') ||
                        (c == '&') || (c == '_') || (c == '(') ||(c == ')') || (c == '-')|| (c == ':') || (c == '/') 
                        ||(c == KeyEvent.VK_BACK_SPACE)|| (c == KeyEvent.VK_CONTROL) 
                        || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_SHIFT))) {
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                    textField.setBorder(new LineBorder(Color.red));
                    popup.setSize(0, 0);
                    popup.getContentPane().setBackground(Color.pink);
                    popup.getContentPane().add(messageLabel);
                    popup.setLocationRelativeTo(textField);
                    Point point = popup.getLocation();
                    Dimension cDim = textField.getSize();
                    popup.setLocation(point.x - (int) cDim.getWidth() / 2,
                            point.y + (int) cDim.getHeight() / 2);
                    popup.pack();
                } else {
                    textField.setBorder(new LineBorder(Color.gray));
                    textField.setBackground(Color.white);
                }
            }
        });
    }
    
    /**
     * validate the Alphabetic characters entered in a JTextArea
     *
     * @param jTextArea name of the JTextArea Component
     *
     */
    public static void validateTextArea(final JTextArea jTextArea) {

        Object parent;
        final JLabel messageLabel = new JLabel("Field Contains Invalid Characters");
        JLabel image;
        final Point point;
        Dimension cDim;
        Color color;
        Border currentBorderColor = null;
        currentBorderColor = jTextArea.getBorder();
        final JDialog popup = new JDialog();
        jTextArea.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                int x= e.getKeyCode();
                if (!jTextArea.getText().isEmpty() && !((c >= '0') && (c <= '9')||(c >= 'a') && (c <= 'z') || (c >= 'A') && (c <= 'Z') ||
                        (c == '&')|| (c == '-') || (c == ',') || (c == '.')|| (c == '/') || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_SHIFT) || 
                        (c == KeyEvent.VK_CAPS_LOCK) || (c == KeyEvent.VK_ENTER)
                        || (c == KeyEvent.VK_CONTROL) )) {
                    Toolkit.getDefaultToolkit().beep();
                    e.consume();
                    jTextArea.setBorder(new LineBorder(Color.red));
                    popup.setSize(0, 0);
                    popup.getContentPane().setBackground(Color.pink);
                    popup.getContentPane().add(messageLabel);
                    popup.setLocationRelativeTo(jTextArea);
                    Point point = popup.getLocation();
                    Dimension cDim = jTextArea.getSize();
                    popup.setLocation(point.x - (int) cDim.getWidth() / 2,
                            point.y + (int) cDim.getHeight() / 2);
                    popup.pack();
                } else {
                    jTextArea.setBorder(new LineBorder(Color.gray));
                    jTextArea.setBackground(Color.white);
                }
            }
        });
    }

    /**
     * validate the Selected Item in a JComboBox
     *
     * @param jcomboBox name of the JComboBox Component
     *
     */
    public static void validateComboBox(final JComboBox jcomboBox) {
        final JDialog popup = new JDialog();
        Object parent;
        final JLabel messageLabel = new JLabel("Invalid Selected Item");
        JLabel image;
        final Point point;
        Dimension cDim;
        Color color;
        Border currentBorderColor = null;
        currentBorderColor = jcomboBox.getBorder();
        jcomboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jcomboBox.getSelectedIndex() == 0) {
                    Toolkit.getDefaultToolkit().beep();
                    jcomboBox.setBorder(new LineBorder(Color.red));
                    popup.setSize(0, 0);
                    popup.getContentPane().setBackground(Color.pink);
                    popup.getContentPane().add(messageLabel);
                    popup.setLocationRelativeTo(jcomboBox);
                    Point point = popup.getLocation();
                    Dimension cDim = jcomboBox.getSize();
                    popup.setLocation(point.x - (int) cDim.getWidth() / 2,
                            point.y + (int) cDim.getHeight() / 2);
                    popup.pack();
                } else {
                    jcomboBox.setBorder(new LineBorder(Color.gray));
                    jcomboBox.setBackground(Color.white);
                }
            }
        });
    }

    /**
     * Check if a mandatory item is not Selected in a JComboBox
     *
     * @param jcomboBox name of the JComboBox Component
     *
     */
    public static void validateMandatoryComboBox(final JComboBox jcomboBox) {
        final JDialog popup = new JDialog();
        Object parent;
        final JLabel messageLabel = new JLabel("Invalid Selected Item");
        JLabel image;
        final Point point;
        Dimension cDim;
        Color color;
        Border currentBorderColor = null;
        currentBorderColor = jcomboBox.getBorder();
        if (jcomboBox.getSelectedIndex() == 0) {
            Toolkit.getDefaultToolkit().beep();
            jcomboBox.setBorder(new LineBorder(Color.red));
            popup.setSize(0, 0);
            popup.getContentPane().setBackground(Color.pink);
            popup.getContentPane().add(messageLabel);
            popup.setLocationRelativeTo(jcomboBox);
            point = popup.getLocation();
            cDim = jcomboBox.getSize();
            popup.setLocation(point.x - (int) cDim.getWidth() / 2,
                    point.y + (int) cDim.getHeight() / 2);
            popup.pack();
        } else {
            jcomboBox.setBorder(new LineBorder(Color.gray));
            jcomboBox.setBackground(Color.white);
        }
    }

    /**
     * Check if a mandatory Field has no values
     *
     * @param jTextFieldName name of the JTextField Component
     *
     */
    public static void mandatoryTextField(final JTextField jTextFieldName) {
        final JDialog popup = new JDialog();
        Object parent;
        final JLabel messageLabel = new JLabel("This Field Is required");
        JLabel image;
        Color color;
        Border currentBorderColor = null;
        currentBorderColor = jTextFieldName.getBorder();
//        InputValidation valid = new InputValidation();
        if (jTextFieldName.getText().isEmpty()) {
            Toolkit.getDefaultToolkit().beep();
            jTextFieldName.setBorder(new LineBorder(Color.red));
            popup.setSize(0, 0);
            popup.getContentPane().setBackground(Color.pink);
            popup.getContentPane().add(messageLabel);
            popup.setLocationRelativeTo(jTextFieldName);
            final Point point = popup.getLocation();
            Dimension cDim = jTextFieldName.getSize();
            popup.setLocation(point.x - (int) cDim.getWidth() / 2,
                    point.y + (int) cDim.getHeight() / 2);
            popup.pack();
        } else {
            jTextFieldName.setBorder(new LineBorder(Color.gray));
            jTextFieldName.setBackground(Color.white);
        }
    }

    /**
     * Check if a mandatory Field has no values
     *
     * @param jTextAreaName name of the JTextArea Component
     *
     */
    public static void mandatoryTextArea(final JTextArea jTextAreaName) {
        final JDialog popup = new JDialog();
        Object parent;
        final JLabel messageLabel = new JLabel("This Field Is required");
        JLabel image;
        Color color;
        Border currentBorderColor = null;
        currentBorderColor = jTextAreaName.getBorder();
//        InputValidation valid = new InputValidation();
        if (jTextAreaName.getText().isEmpty()) {
            Toolkit.getDefaultToolkit().beep();
            jTextAreaName.setBorder(new LineBorder(Color.red));
            popup.setSize(0, 0);
            popup.getContentPane().setBackground(Color.pink);
            popup.getContentPane().add(messageLabel);
            popup.setLocationRelativeTo(jTextAreaName);
            final Point point = popup.getLocation();
            Dimension cDim = jTextAreaName.getSize();
            popup.setLocation(point.x - (int) cDim.getWidth() / 2,
                    point.y + (int) cDim.getHeight() / 2);
            popup.pack();
        } else {
            jTextAreaName.setBorder(new LineBorder(Color.gray));
            jTextAreaName.setBackground(Color.white);
        }
    }

    /**
     * Check if a mandatory date field is null
     *
     * @param jDateChooserName name of the JTDateChooser Component
     *
     */
//    public static void mandatoryDate(final JDateChooser jDateChooserName) {
//        final JDialog popup = new JDialog();
//        Object parent;
//        final JLabel messageLabel = new JLabel("This Field Is required");
//        JLabel image;
//        Color color;
//        if (jDateChooserName.getDate() == null) {
//            Toolkit.getDefaultToolkit().beep();
//            jDateChooserName.setBorder(new LineBorder(Color.red));
//            popup.setSize(0, 0);
//            popup.getContentPane().setBackground(Color.pink);
//            popup.getContentPane().add(messageLabel);
//            popup.setLocationRelativeTo(jDateChooserName);
//            final Point point = popup.getLocation();
//            Dimension cDim = jDateChooserName.getSize();
//            popup.setLocation(point.x - (int) cDim.getWidth() / 2,
//                    point.y + (int) cDim.getHeight() / 2);
//            popup.pack();
//        } else if (jDateChooserName.getDate() != null) {
//            jDateChooserName.setBorder(new LineBorder(Color.GRAY));
//            jDateChooserName.setBackground(Color.white);
//        }
//    }
    
public static void validateCheckBox(final JCheckBox jCheckBoxName) {
        final JDialog popup = new JDialog();
        Object parent;
        final JLabel messageLabel = new JLabel("This Field Is required");
        JLabel image;
        Color color;
        Border currentBorderColor = null;
        currentBorderColor = jCheckBoxName.getBorder();
//        InputValidation valid = new InputValidation();
        
        if (!jCheckBoxName.isSelected()) {
            Toolkit.getDefaultToolkit().beep();
            jCheckBoxName.setBorder(new LineBorder(Color.red));
            popup.setSize(0, 0);
            popup.getContentPane().setBackground(Color.pink);
            popup.getContentPane().add(messageLabel);
            popup.setLocationRelativeTo(jCheckBoxName);
            final Point point = popup.getLocation();
            Dimension cDim = jCheckBoxName.getSize();
            popup.setLocation(point.x - (int) cDim.getWidth() / 2,
                    point.y + (int) cDim.getHeight() / 2);
            popup.pack();
        } else {
            jCheckBoxName.setBorder(new LineBorder(Color.gray));
            jCheckBoxName.setBackground(Color.white);
        }
    }
public static void validateMultipleCheckBoxes(final JCheckBox jCheckBoxName,final JCheckBox jCheckBoxName2) {
        final JDialog popup = new JDialog();
        Object parent;
        final JLabel messageLabel = new JLabel("This Field Is required");
        JLabel image;
        Color color;
        Border currentBorderColor = null;
        currentBorderColor = jCheckBoxName.getBorder();
//        InputValidation valid = new InputValidation();
        
        if (!jCheckBoxName.isSelected() || !jCheckBoxName2.isSelected()) {
            Toolkit.getDefaultToolkit().beep();
            jCheckBoxName.setBorder(new LineBorder(Color.red));
            popup.setSize(0, 0);
            popup.getContentPane().setBackground(Color.pink);
            popup.getContentPane().add(messageLabel);
            popup.setLocationRelativeTo(jCheckBoxName);
            final Point point = popup.getLocation();
            Dimension cDim = jCheckBoxName.getSize();
            popup.setLocation(point.x - (int) cDim.getWidth() / 2,
                    point.y + (int) cDim.getHeight() / 2);
            popup.pack();
        } else {
            jCheckBoxName.setBorder(new LineBorder(Color.gray));
            jCheckBoxName.setBackground(Color.white);
        }
    }

}
