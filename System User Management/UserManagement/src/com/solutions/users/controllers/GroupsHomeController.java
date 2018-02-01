/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.users.controllers;

import com.solutions.users.models.SystemApplication;
import com.solutions.users.models.SystemMenuItems;
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
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class GroupsHomeController implements Initializable {

    //Manage User Groups CONTROLS
    @FXML
    private BorderPane borderpaneUserGroupsWrapper;
    @FXML
    private TextField txtUserGroupName;
    @FXML
    private Button saveUserGroup;

    //Manage Group Previleges CONTROLS
    @FXML
    private BorderPane borderpanePossiblePrevWrapper;
    @FXML
    private ComboBox cbUserGroup;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonRemove;
    @FXML
    private BorderPane borderpaneAssignedPrevWrapper;
    @FXML
    private TabPane tabpane;
    @FXML
    private TableView groupsTable;
    @FXML
    private TableView possibleGroupsTable;
    @FXML
    private TableView groupPrevTable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//      groupTab.getTabPane()
        //tabpane.getTabs().remove(0);
        // tabpane.getTabs().get(1).getContent().setVisible(true);
        ObservableList<String> groupName = FXCollections.observableArrayList(UserGroup.getUserGroupMap().values());
        cbUserGroup.setItems(groupName);
        borderpaneUserGroupsWrapper.setCenter(UserGroup.getUserGroupTb());
        cbUserGroup.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                int grpid = 0;
                String groupname = cbUserGroup.getSelectionModel().getSelectedItem().toString();
                HashMap<Integer, String> readgroups = SystemMenuItems.getUserGroupMenuItemsMap();
                Set<Integer> catIds = readgroups.keySet();
                for (int keys : catIds) {
                    if (readgroups.get(keys).equals(groupname)) {
                        grpid = keys;
                        break;
                    }
                }
                //int grpid = (int) cbUserGroup.getSelectionModel().getSelectedItem();
                possibleGroupsTable = SystemMenuItems.getSystemPossiblePrevItemsTb(grpid);
                borderpanePossiblePrevWrapper.setCenter(possibleGroupsTable);
            }
        });

        saveUserGroup.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    String groupname = txtUserGroupName.getText();
                    if (groupname.equals("")) {

                    } else {
                        UserGroup.addNewreadUserGroups(groupname);
                        borderpaneUserGroupsWrapper.setCenter(UserGroup.getUserGroupTb());
                        txtUserGroupName.setText(null);
                    }

                } catch (Exception e) {

                }
            }
        });
    }

}
