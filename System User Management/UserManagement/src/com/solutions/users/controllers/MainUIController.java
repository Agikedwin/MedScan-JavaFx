/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.users.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class MainUIController implements Initializable {

    @FXML
    private BorderPane borderpaneWrapper;
    @FXML
    private Hyperlink linkApplications;
    @FXML
    private Hyperlink linkGroups;
    @FXML
    private Hyperlink linkUsers;
    @FXML
    private Hyperlink linkUtilities;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        linkApplications.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    borderpaneWrapper.setCenter(null);
                    AnchorPane contentpane = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/solutions/users/views/ApplicationsHome.fxml"));
                    borderpaneWrapper.setCenter(contentpane);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        linkGroups.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    borderpaneWrapper.setCenter(null);
                    AnchorPane contentpane = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/solutions/users/views/GroupsHome.fxml"));
                    borderpaneWrapper.setCenter(contentpane);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        linkUsers.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    borderpaneWrapper.setCenter(null);
                    AnchorPane contentpane = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/solutions/users/views/UsersHome.fxml"));
                    borderpaneWrapper.setCenter(contentpane);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        linkUtilities.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    borderpaneWrapper.setCenter(null);
                    AnchorPane contentpane = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/solutions/users/views/ChangePasswordHome.fxml"));
                    borderpaneWrapper.setCenter(contentpane);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
