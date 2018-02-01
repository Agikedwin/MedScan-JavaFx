/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.users.controllers;

import com.solutions.entorno.utilities.MessagesUtil;
import com.solutions.users.models.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ChangePasswordHomeController implements Initializable {

    @FXML
    private BorderPane borderpaneChangePasswordWrapper;
    @FXML
    private Button saveChangedPassword;
    @FXML
    private TextField lastName;
    @FXML
    private TextField userName;
    @FXML
    private TextField firstName;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField confirmNewPassword;
    @FXML
    private TableView passwordTable;

    User user = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        passwordTable = User.getUserTb();
        borderpaneChangePasswordWrapper.setCenter(passwordTable);
        passwordTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    user = (User) passwordTable.getSelectionModel().getSelectedItem();
                    firstName.setText(user.getFirstName());
                    lastName.setText(user.getLastName());
                    userName.setText(user.getUsername());
                } catch (Exception e) {

                }

            }
        });
        saveChangedPassword.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    String newpass = newPassword.getText();
                    String confirmpass = confirmNewPassword.getText();
                    if (newpass.equals("")) {
                        MessagesUtil.displayMessage("Missing Field", "New Password should be filled", 1, null);
                    } else if (confirmpass.equals("")) {
                        MessagesUtil.displayMessage("Missing Field", "Confirm New Password should be filled", 1, null);
                    } else if (!newpass.equals(confirmpass)) {
                        MessagesUtil.displayMessage("Error", "Passwords do not match", 1, null);
                    } else {
                        String usr = user.getUserId();
                        user.changePwd(newpass, usr);
                        firstName.setText(null);
                        lastName.setText(null);
                        userName.setText(null);
                        newPassword.setText(null);
                        confirmNewPassword.setText(null);
                    }

                } catch (Exception e) {

                }
            }
        });
    }

}
