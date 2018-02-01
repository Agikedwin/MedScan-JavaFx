/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.pwp.integrator.controllers;

import static com.solutions.entorno.utilities.SystemFunctions.isStaleCopy;
import static com.solutions.entorno.utilities.SystemFunctions.registered;
import com.solutions.pwp.integrator.controllers.utilities.SystemImageIcon;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author shaddie
 */
public class welcome implements Initializable {
    @FXML
    private Label gifLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //gifLabel.
            if(isStaleCopy() && !registered()){
            System.exit(1);
        }
        else{
           //loginPage();   
        }
    } 
    public void loginPage(){
         try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/solutions/pwp/integrator/views/login.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                     stage.initStyle(StageStyle.UNDECORATED);
                      stage.getIcons().add(new Image(SystemImageIcon.imagePath()));
                    stage.show();
                    Stage oldstage = (Stage) gifLabel.getScene().getWindow();
                    oldstage.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
    }
   
}
