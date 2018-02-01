/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.users.controllers;

import com.solutions.entorno.utilities.MessagesUtil;
import com.solutions.users.models.AssignUserToUserGroups;
import com.solutions.users.models.SystemApplication;
import com.solutions.users.models.User;
import com.solutions.users.models.UserGroup;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import sun.misc.MessageUtils;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class UsersHomeController implements Initializable {

    //Manage User CONTROLS
    @FXML
    private BorderPane borderpaneManageUserWrapper;
    @FXML
    private Button saveUser;
    @FXML
    private TextField middleName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField userName;
    @FXML
    private TextField firstName;
    @FXML
    private PasswordField password;

    //User Group Assignment CONTROLS
    @FXML
    private BorderPane borderpaneGroupAssignedWrapper;
    @FXML
    private Button saveGroupAssignment;
    @FXML
    private TextField lastNameAssigned;
    @FXML
    private TextField userNameAssigned;
    @FXML
    private TextField firstNameAssigned;
    @FXML
    private ComboBox cbuserGroup;
    @FXML
    private TableView usersTable;
    @FXML
    private TableView groupAssignTable;

    User user = null;
    int groupid = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usersTable = User.getUserTb();
        borderpaneManageUserWrapper.setCenter(usersTable);
        saveUser.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {

                    String fname = firstName.getText();
                    String mname = middleName.getText();
                    String lname = lastName.getText();
                    String phoneno = phoneNumber.getText();
                    String usrname = userName.getText();
                    String pword = password.getText();

                    if (fname.equals("")) {
                        MessagesUtil.displayMessage("Missing Field", "First Name should be filled", 1, null);
                    } else if (lname.equals("")) {
                        MessagesUtil.displayMessage("Missing Field", "Last Name should be filled", 1, null);
                    } else if (usrname.equals("")) {
                        MessagesUtil.displayMessage("Missing Field", "Username should be filled", 1, null);
                    } else {
                        if (user != null) {
                            user.setFirstName(fname);
                            user.setMiddleName(mname);
                            user.setLastName(lname);
                            user.setUsername(usrname);
                            user.setPhoneNo(phoneno);
                            user.update(user.getUserId());
                        } else {
                            user = new User();
                            user.addNewUser(fname, mname, lname, phoneno, usrname, pword);
                        }
                        user = null;
                        firstName.setText(null);
                        middleName.setText(null);
                        lastName.setText(null);
                        phoneNumber.setText(null);
                        userName.setText(null);
                        password.setText(null);
                        usersTable = User.getUserTb();
                        borderpaneManageUserWrapper.setCenter(usersTable);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        usersTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    user = (User) usersTable.getSelectionModel().getSelectedItem();
                    firstName.setText(user.getFirstName());
                    middleName.setText(user.getMiddleName());
                    lastName.setText(user.getLastName());
                    phoneNumber.setText(user.getPhoneNo());
                    userName.setText(user.getUsername());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        ObservableList<String> groupName = FXCollections.observableArrayList(UserGroup.getUserGroupMap().values());
        cbuserGroup.setItems(groupName);
        groupAssignTable = AssignUserToUserGroups.getAssignUserToGroupTb();
        borderpaneGroupAssignedWrapper.setCenter(groupAssignTable);
        groupAssignTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    user = (User) groupAssignTable.getSelectionModel().getSelectedItem();
                    firstNameAssigned.setText(user.getFirstName());
                    lastNameAssigned.setText(user.getLastName());
                    userNameAssigned.setText(user.getUsername());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        cbuserGroup.setOnAction(new EventHandler() {

            @Override
            public void handle(Event event) {
                if (cbuserGroup.getItems().size() > 0) {
                    String groupname = cbuserGroup.getSelectionModel().getSelectedItem().toString();
                    HashMap<Integer, String> readgroups = UserGroup.getUserGroupMap();
                    Set<Integer> groupIds = readgroups.keySet();

                    for (int keys : groupIds) {
                        if (readgroups.get(keys).equals(groupname)) {
                            groupid = keys;
                            break;
                        }
                    }
                }
            }
        });
        saveGroupAssignment.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    if (user == null) {
                        MessagesUtil.displayMessage("Select user", "Please select user", 1, null);
                    } else if (groupid == 0) {
                        MessagesUtil.displayMessage("Select User Group", "Please select User Group", 1, null);
                    } else {
                        String usrid = user.getUserId();
                        AssignUserToUserGroups.addNewAssignUserToUserGroups(usrid, groupid);
                        groupid = 0;
                        user = null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
