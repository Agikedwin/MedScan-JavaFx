/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.pwp.integrator.controllers;

import com.solutions.pwp.integrator.controllers.utilities.SystemImageIcon;
import com.solutions.pwp.integrator.models.InstitutionalDetails;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author shaddie
 */
public class DashBoard implements Initializable {

    @FXML
    private BorderPane borderPaneHomeScreen;
    @FXML
    private Button homeButton;
    @FXML
    private Button businessDetails;
    @FXML
    private Button businessLogoButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            AnchorPane contentpane = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/solutions/pwp/integrator/views/HomeScreen.fxml"));
            borderPaneHomeScreen.setCenter(contentpane);
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(DashBoard.class.getName()).log(Level.SEVERE, null, ex);
        }
        businessDetails.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
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
                    stage.initOwner(businessDetails.getScene().getWindow());
                    InstitutionalDetails details = new InstitutionalDetails();
                    details = details.readInstitution();
                    InstitutionDetailsController ic = fxmlLoader.<InstitutionDetailsController>getController();
                    ic.populateDetails(details);
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.setResizable(false);
                    stage.setTitle("Business Details");
                    // stage.initStyle(StageStyle.UNDECORATED);
                    stage.getIcons().add(new Image(SystemImageIcon.imagePath()));
                    stage.show();

                } catch (IOException ioe) {

                }
            }
        });

        businessLogoButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/solutions/pwp/integrator/views/Logo.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                     stage.initOwner(businessLogoButton.getScene().getWindow());
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.setResizable(false);
                    stage.setTitle("Business Logo");
                    LogoController lc = fxmlLoader.<LogoController>getController();
                    lc.populateLogo();
                    stage.getIcons().add(new Image(SystemImageIcon.imagePath()));
                    stage.show();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

}
