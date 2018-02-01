/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.pwp.integrator.controllers;

import com.solutions.entorno.utilities.MessagesUtil;
import com.solutions.entorno.utilities.SystemVariables;
import static com.solutions.entorno.utilities.SystemVariables.USER_PROFILE;
import static com.solutions.entorno.utilities.SystemVariables.resultSet;
import static com.solutions.entorno.utilities.SystemVariables.statement;
import static com.solutions.pwp.integrator.controllers.utilities.FunctionGetInstitutionDetails.getInstitutionLogoStream;
import com.solutions.pwp.integrator.controllers.utilities.SystemImageIcon;
import com.solutions.users.controllers.utilities.FunctionGetUserDetails;
import static com.solutions.users.controllers.utilities.ProfileVariables.POS_MANAGEMENT_MENU;
import static com.solutions.users.controllers.utilities.ProfileVariables.POS_MANAGEMENT_SUBMENUS;
import static com.solutions.users.controllers.utilities.ProfileVariables.USER_MANAGEMENT_MENU;
import static com.solutions.users.controllers.utilities.ProfileVariables.USER_MANAGEMENT_SUBMENUS;
import com.solutions.users.controllers.utilities.UserManagementTablesCreator;
import static com.solutions.users.controllers.utilities.UserManagementVariables.APP_REGISTRATION;
import static com.solutions.users.controllers.utilities.UserManagementVariables.ASSIGNUSER_TOGROUP;
import static com.solutions.users.controllers.utilities.UserManagementVariables.GROUP_PRIVILEGES;
import static com.solutions.users.controllers.utilities.UserManagementVariables.MENUS_REGISTRATION;
import static com.solutions.users.controllers.utilities.UserManagementVariables.SUBMENUS_REGISTRATION;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import medscan.MedScan;

/**
 * FXML Controller class
 *
 * @author shaddie
 */
public class Homescreen implements Initializable {

    @FXML
    private Pane POS_app;
    @FXML
    private Pane logo;
    @FXML
    private Label businessName;
    @FXML
    private Label postalAdress;
    @FXML
    private Label email;
    @FXML
    private Label telephone;
    @FXML
    private Pane userManagementApp;
    @FXML
    private ImageView logoImage;
    @FXML
    private Label loggedIn;
    @FXML
    private ImageView posImg;
    @FXML
    private ImageView accessControlImg;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // init();
        //appListInit();
         new MedScan();
        POS_app.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
               // init();
               // appListInit();
                   new MedScan();
               //mainPOSapp();
            }
        });
        userManagementApp.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                init();
                appListInit();
                  new MedScan();
                userManagemenentApp();
            }
        });
    }

    private void init() {
        if (getInstitutionLogoStream() != null) {
            logoImage.setImage(new Image(getInstitutionLogoStream()));
        }

        businessName.setText("Business Name: " + SystemVariables.INSTITUTION_NAME);
        postalAdress.setText("Postal Address:  " + SystemVariables.INSTITUTION_ADDRESS);
        telephone.setText("Telephone: " + SystemVariables.INSTITUTION_PHONE);
        email.setText("Email:  " + SystemVariables.INSTITUTION_EMAIL);
        //loggedIn.setText(FunctionGetUserDetails.getFullName(USER_PROFILE) + "  Logged in as  " + FunctionGetUserDetails.getUserGroupName(Integer.parseInt(FunctionGetUserDetails.getUserGroup(USER_PROFILE))));
        //posImg.setImage(new Image("/com/solutions/pwp/integrator/controllers/utilities/icons/Cash-register-icon-32.png"));
        //accessControlImg.setImage(new Image("/com/solutions/pwp/integrator/controllers/utilities/icons/Settings-icon2.png"));
    }

    public void mainPOSapp() {
//new PWPTablesCreator();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/solutions/pwp/views/MainUI_1.fxml"));
            URL url = this.getClass().getResource("/com/solutions/pwp/integrator/views/css/JMetroLightTheme.css");
            if (url == null) {
                System.out.println("Resource not found. Aborting.");
                System.exit(-1);
            }
            String css = url.toExternalForm();
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root1);
            stage.setScene(scene);
             stage.setTitle("MEDSCAN DIAGNOSTIC CENTRE MIS");
            stage.setMaximized(true);
            scene.getStylesheets().add(css);
            //  stage.initStyle(StageStyle.UNDECORATED);
            stage.getIcons().add(new Image(SystemImageIcon.imagePath()));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void userManagemenentApp() {
     new UserManagementTablesCreator();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/solutions/users/views/MainUI.fxml"));
            URL url = this.getClass().getResource("/com/solutions/pwp/integrator/views/css/JMetroLightTheme.css");
            if (url == null) {
                System.out.println("Resource not found. Aborting.");
                System.exit(-1);
            }
            String css = url.toExternalForm();
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root1);
            stage.setScene(scene);
            stage.setMaximized(true);
             stage.setTitle("ACCESS CONTROL");
            scene.getStylesheets().add(css);
            //  stage.initStyle(StageStyle.UNDECORATED);
            stage.getIcons().add(new Image(SystemImageIcon.imagePath()));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void appListInit() {
        try {
            POS_MANAGEMENT_MENU.clear();
            POS_MANAGEMENT_SUBMENUS.clear();
            USER_MANAGEMENT_MENU.clear();
            USER_MANAGEMENT_SUBMENUS.clear();
//            JPanel jpanelList[]= {jPanelApp1,jPanelApp2,jPanelApp3,jPanelApp4};
            Pane panes[] = {POS_app, userManagementApp};
            for (Pane pane : panes) {
                pane.setVisible(false);
            }
            String theAppList[] = {"MEDSCAN DIAGNOSTIC CENTRE MIS", "ACCESS CONTROL"};
            ArrayList<String> systemApp = new ArrayList();

            int usergroup;
            resultSet = statement.executeQuery("select usergroup from " + ASSIGNUSER_TOGROUP + " where userid='" + USER_PROFILE + "' ");
            if (resultSet.next()) {
                usergroup = resultSet.getInt(1);

                resultSet = statement.executeQuery("SELECT privilege from " + GROUP_PRIVILEGES + " where usergroup = " + usergroup + "");
                if (resultSet.next()) {
                    resultSet = statement.executeQuery("SELECT privilege from " + GROUP_PRIVILEGES + " where usergroup = " + usergroup + "");
                    String privs = null;
                    int priv = 0;
                    while (resultSet.next()) {
                        if (priv != 0) {
                            privs = privs.concat(",");
                            privs = privs.concat(resultSet.getString(1));
                        } else {
                            privs = (resultSet.getString(1));
                        }
                        priv++;
                    }
                    resultSet = statement.executeQuery("select distinct menuid from " + SUBMENUS_REGISTRATION + " where itemid in(" + privs + ") ");
                    if (resultSet.next()) {
                        resultSet = statement.executeQuery("select distinct menuid from " + SUBMENUS_REGISTRATION + " where itemid in(" + privs + ") ");
                        String menus = null;
                        int menu = 0;
                        while (resultSet.next()) {
                            if (menu != 0) {
                                menus = menus.concat(",");
                                menus = menus.concat(resultSet.getString(1));
                            } else {
                                menus = (resultSet.getString(1));
                            }
                            menu++;
                        }
                        String apps = null;
                        int app = 0;
                        resultSet = statement.executeQuery("select distinct appid from " + MENUS_REGISTRATION + " where menuid in(" + menus + ") ");
                        while (resultSet.next()) {
                            if (app != 0) {
                                apps = apps.concat(",");
                                apps = apps.concat(resultSet.getString(1));
                            } else {
                                apps = (resultSet.getString(1));
                            }
                            app++;
                        }

                        String appz[] = apps.split(",");
                        for (String appp : appz) {
                            resultSet = statement.executeQuery("select appname from " + APP_REGISTRATION + " where appid=" + Integer.parseInt(appp) + " and app_state = '1' ");
                            if (resultSet.next()) {
                                // System.out.println(resultSet.getString(1));
                                systemApp.add(resultSet.getString(1));
                                //populate menus
                                resultSet = statement.executeQuery("select menuname from " + MENUS_REGISTRATION + ""
                                        + " where appid = " + Integer.parseInt(appp) + " and menuid in(" + menus + ")");
                                while (resultSet.next()) {
                                    switch (appp) {
                                        case "1":
                                            USER_MANAGEMENT_MENU.add(resultSet.getString(1));
                                            break;
                                        case "4":
                                            POS_MANAGEMENT_MENU.add(resultSet.getString(1));
                                            break;

                                    }
                                }
                                //populate submenus
                                resultSet = statement.executeQuery("select sm.itemname from " + SUBMENUS_REGISTRATION + " sm,"
                                        + " " + MENUS_REGISTRATION + " mr  "
                                        + " where sm.itemid in (" + privs + ") "
                                        + " and sm.menuid in(" + menus + ") "
                                        + " and sm.menuid = mr.menuid "
                                        + " and mr.appid= " + Integer.parseInt(appp) + " ");
                                while (resultSet.next()) {
                                    switch (appp) {
                                        case "1":
                                            USER_MANAGEMENT_SUBMENUS.add(resultSet.getString(1));
                                            break;

                                        case "4":
                                            POS_MANAGEMENT_SUBMENUS.add(resultSet.getString(1));
                                            break;

                                    }
                                }

                            }
                        }
                        for (int i = 0; i < theAppList.length; i++) {
                            //System.out.println(theAppList[i]);
                            if (systemApp.contains(theAppList[i])) {
                                panes[i].setVisible(true);
                            }
                        }
                    } else {
                        MessagesUtil.displayMessage("No Usergroup Privileges", "Usergroup does not have any assigned Menu privileges"
                                + "\n Contact the admin to solve the problem", 1, null);
                        System.exit(0);
                    }
                } else {
                    MessagesUtil.displayMessage("No Usergroup Privileges", "Usergroup does not have any assigned access privileges"
                            + "\n Contact the admin to solve the problem", 1, null);
                    System.exit(0);
                }
            } else {
                MessagesUtil.displayMessage("No User group found", "User does not belong to any usergroup"
                        + "\n please assign user to at least one of the groups", 1, null);
                System.exit(0);
            }
        } catch (SQLException ex) {
            MessagesUtil.displayMessage("Error Encountered", "Error Encountered", 3, ex);
        }
    }
}
