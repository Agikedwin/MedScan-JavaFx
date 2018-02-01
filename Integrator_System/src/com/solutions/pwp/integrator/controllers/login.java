/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.pwp.integrator.controllers;

import com.solutions.entorno.utilities.MessagesUtil;
import com.solutions.entorno.utilities.SystemFunctions;
import com.solutions.entorno.utilities.SystemVariables;
import static com.solutions.entorno.utilities.SystemVariables.resultSet;
import static com.solutions.entorno.utilities.SystemVariables.statement;
import com.solutions.pwp.integrator.controllers.utilities.SystemImageIcon;
import com.solutions.pwp.integrator.controllers.utilities.SystemMandatorySettings;
import static com.solutions.users.controllers.utilities.UserManagementVariables.USER_REGISTRATION;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 * FXML Controller class
 *
 * @author shaddie
 */
public class login implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private Button login;
    @FXML
    private PasswordField password;
    @FXML
    private Label close;
    @FXML
    private Label miniz;
    @FXML
    private Label imageLabel;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label passLabel;

    @FXML
    private void minimizeProgram() {
        Stage stage = (Stage) miniz.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void exitProgramAction() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        Image closeIcon = new Image(getClass().getResourceAsStream("Entypo_2715(0)_32.png"));
//        Image minimizeIcon = new Image(getClass().getResourceAsStream("Entypo_2d(0)_48.png"));
//        
//        Image userIcon = new Image(getClass().getResourceAsStream("Entypo_d83d(0)_32.png"));
//        Image passIcon = new Image(getClass().getResourceAsStream("Entypo_d83d(1)_32.png"));
//        close.setGraphic(new ImageView(new Image("/com/solutions/pwp/integrator/controllers/utilities/icons/Entypo_2715(0)_32.png")));
//        miniz.setGraphic(new ImageView(new Image("/com/solutions/pwp/integrator/controllers/utilities/icons/Entypo_2d(0)_48.png")));
//        
////        userNameLabel.setGraphic(new ImageView(userIcon));
////        passLabel.setGraphic(new ImageView(passIcon));
//        userNameLabel.setGraphic(new ImageView(new Image("/com/solutions/pwp/integrator/controllers/utilities/icons/Entypo_d83d(0)_32.png")));
//        passLabel.setGraphic(new ImageView(new Image("/com/solutions/pwp/integrator/controllers/utilities/icons/Entypo_d83d(1)_32.png")));
        login.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if ("".equals(username.getText())) {
                    MessagesUtil.displayMessage("User Name required", "Please enter user Name", 1, null);
                } else if ("".equals(password.getText())) {
                    MessagesUtil.displayMessage("Password required", "Please enter Password", 1, null);
                } else {
                    try {
                        System.out.println("trying to log in");
                        /////////////////admin login///////////////////////////////
                        String admid = username.getText().toLowerCase();
                        String admpwd = SystemFunctions.OneWayDataSecurity(password.getText());
                        // String admpwd= (password.getText());
                        String query = "SELECT userid FROM " + USER_REGISTRATION + " WHERE username='" + admid + "' AND password='" + admpwd + "'";
                        resultSet = statement.executeQuery(query);
                        if (resultSet.next()) {
                            SystemVariables.USER_PROFILE = resultSet.getString(1);

                            mandatorySettings();

                        } else {
                            MessagesUtil.displayMessage("Incorrect Credentials", "User name or password incoreect. please try again", 1, null);
                        }
                    } catch (SQLException e) {
                        System.out.println("at the error");
                        e.printStackTrace();
                        MessagesUtil.displayMessage("Error encountered while logging in", "Error encountered log in ", 3, e);
                    }
                }

            }
        });
    }

    public void mandatorySettings() {

        if (!SystemMandatorySettings.isInstitutionAlphaSet()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/solutions/pwp/integrator/views/InstitutionDetails.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                URL url = this.getClass().getResource("/com/solutions/pwp/integrator/views/css/JMetroLightTheme.css");
                if (url == null) {
                    System.out.println("Resource not found. Aborting.");
                    System.exit(-1);
                }
                String css = url.toExternalForm();
                Scene scene = new Scene(root1);
                scene.getStylesheets().add(css);
                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.getIcons().add(new Image(SystemImageIcon.imagePath()));
                stage.show();
                Stage oldstage = (Stage) login.getScene().getWindow();
                oldstage.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } //     if (!SystemMandatorySettings.isInstitutionLogoSet()) {
        //                    try {
        //                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/solutions/pos/integrator/views/Logo.fxml"));
        //                        Parent root1 = (Parent) fxmlLoader.load();
        //                        Stage stage = new Stage();
        //                        stage.setScene(new Scene(root1));
        //                        stage.initStyle(StageStyle.UNDECORATED);
        //                        stage.getIcons().add(new Image(SystemImageIcon.imagePath()));
        //                        stage.show();
        //                        Stage oldstage = (Stage) login.getScene().getWindow();
        //                        oldstage.close();
        //                    } catch (IOException ex) {
        //                        ex.printStackTrace();
        //                    }
        //                } 
        else {
            dashBoardPage();
        }

    }

    public void dashBoardPage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/solutions/pwp/integrator/views/Dashboard_1.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            URL url = this.getClass().getResource("/com/solutions/pwp/integrator/views/css/JMetroLightTheme.css");
            if (url == null) {
                System.out.println("Resource not found. Aborting.");
                System.exit(-1);
            }
            String css = url.toExternalForm();
            //stage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(root1);
            stage.setScene(scene);
            scene.getStylesheets().add(css);
            stage.getIcons().add(new Image(SystemImageIcon.imagePath()));
            stage.setTitle("PIECE WISE DASHBOARD");
            stage.show();
            Stage oldstage = (Stage) login.getScene().getWindow();
            oldstage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
