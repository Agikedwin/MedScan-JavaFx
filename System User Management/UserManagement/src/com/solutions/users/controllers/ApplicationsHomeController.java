/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.users.controllers;

import com.solutions.entorno.utilities.MessagesUtil;
import com.solutions.users.models.SystemApplication;
import com.solutions.users.models.SystemMenuItems;
import com.solutions.users.models.SystemMenus;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ApplicationsHomeController implements Initializable {

    //Application Registration Controls
    @FXML
    private TextField txtApplicationName;
    @FXML
    private Button saveApplicationName;
    @FXML
    private BorderPane borderpaneApplicationWrapper;

    //Register Menus Controls
    @FXML
    private ComboBox cbApplicationID;
    @FXML
    private TextField txtMenuName;
    @FXML
    private Button saveMenus;
    @FXML
    private BorderPane borderpaneMenusWrapper;

    //Register Menus Items Controls
    @FXML
    private ComboBox cbMenuName;
    @FXML
    private TextField txtMenuItemName;
    @FXML
    private Button saveMenuItem;
    @FXML
    private BorderPane borderpaneMenuItemWrapper;
    @FXML
    private TableView appRegTable;
    @FXML
    private TableView menusTable;
    @FXML
    private TableView menuItemsTable;
    @FXML
    private ComboBox cbApplicationNameItems;

    int menuid = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        borderpaneApplicationWrapper.setCenter(SystemApplication.getApplications());
        saveApplicationName.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    String appname = txtApplicationName.getText();
                    if (appname.equals("")) {

                    } else {
                        SystemApplication.addNewSystemApplication(appname);
                        borderpaneApplicationWrapper.setCenter(SystemApplication.getApplications());
                        txtApplicationName.setText(null);
                    }

                } catch (Exception e) {

                }
            }
        });
        ObservableList<String> skuCat = FXCollections.observableArrayList(SystemApplication.getApplicationMap().values());
        cbApplicationID.setItems(skuCat);
        borderpaneMenusWrapper.setCenter(SystemMenus.getSystemMenusTb());
        saveMenus.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    int appid = 0;
                    String appname = cbApplicationID.getSelectionModel().getSelectedItem().toString();
                    HashMap<Integer, String> readapps = SystemApplication.getApplicationMap();
                    Set<Integer> catIds = readapps.keySet();

                    for (int keys : catIds) {
                        if (readapps.get(keys).equals(appname)) {
                            appid = keys;
                            break;
                        }
                    }
                    String menuname = txtMenuName.getText();

                    if (menuname.equals("")) {
                        MessagesUtil.displayMessage("Empty Field", "Menu Name cannot be null", 3, null);
                    } else {
                        SystemMenus.addNewSystemApplication(appid, menuname);
                        borderpaneMenusWrapper.setCenter(SystemMenus.getSystemMenusTb());
                        txtMenuName.setText(null);
                    }

                } catch (Exception e) {

                }
            }
        });
        //MENU ITEMS
        borderpaneMenuItemWrapper.setCenter(SystemMenuItems.getSystemMenusItemsTb());
        ObservableList<String> skuApps = FXCollections.observableArrayList(SystemApplication.getApplicationMap().values());
        cbApplicationNameItems.setItems(skuApps);
        cbApplicationNameItems.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                int appsid = 0;
                String appname = cbApplicationNameItems.getSelectionModel().getSelectedItem().toString();
                HashMap<Integer, String> readapps = SystemApplication.getApplicationMap();
                Set<Integer> catIds = readapps.keySet();
                for (int keys : catIds) {
                    if (readapps.get(keys).equals(appname)) {
                        appsid = keys;
                        break;
                    }
                }
                System.out.println("appid: " + appsid);
                ObservableList<String> skuMenus = FXCollections.observableArrayList(SystemMenus.getSystemMenusMap(appsid).values());
                cbMenuName.setItems(skuMenus);
            }
        });

        saveMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String menuitem = txtMenuItemName.getText();
                    if (menuitem.equals("")) {
                        MessagesUtil.displayMessage("Missing Field", "Item Name should be filled", 1, null);
                    } 
//                    else if (menuid == 0) {
//                        MessagesUtil.displayMessage("Select Menu Name", "Please select Menu Name", 1, null);
//                    }
                    else {
                        SystemMenuItems.addNewSystemMenuItem(txtMenuItemName.getText());
                        borderpaneMenuItemWrapper.setCenter(SystemMenuItems.getSystemMenusItemsTb());
                        txtMenuItemName.setText(null);
                    }

                } catch (Exception e) {

                }
            }
        });

    }

}
