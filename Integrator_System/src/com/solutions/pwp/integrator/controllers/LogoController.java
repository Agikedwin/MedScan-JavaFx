/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.pwp.integrator.controllers;

import com.solutions.entorno.utilities.ImageResizerClass;
import com.solutions.entorno.utilities.MessagesUtil;
import com.solutions.entorno.utilities.SystemVariables;
import static com.solutions.entorno.utilities.SystemVariables.CONFIG_FOLDER;
import static com.solutions.entorno.utilities.SystemVariables.connection;
import static com.solutions.entorno.utilities.SystemVariables.preparedStatement;
import com.solutions.pwp.integrator.controllers.utilities.FunctionGetInstitutionDetails;
import static com.solutions.pwp.integrator.controllers.utilities.FunctionGetInstitutionDetails.getInstitutionLogoStream;
import static com.solutions.pwp.integrator.controllers.utilities.InstitutionVariables.INSTITUTION;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author shaddie
 */
public class LogoController implements Initializable {
    @FXML
    private Pane logo;
    @FXML
    private Button btnSetlogo;
    @FXML
    private ImageView logoImage;
    @FXML
    private Button saveBtn;
     String input = null;  
    File file = null;
    
    FileInputStream fs = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnSetlogo.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
         try {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Select Business Logo");
                    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
                    File selectedFile = fileChooser.showOpenDialog(null);
                     file = new File(selectedFile.getAbsolutePath());
                   fs = new FileInputStream(file);
                    input = selectedFile.getAbsolutePath();
                   Image inImg = new Image(fs);
                    logoImage.setImage(inImg);
                } catch (IOException ex) {
            ex.printStackTrace();
                }
            }
        });
        saveBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
        saveLogo();
            }
        });
        
    }    
    private void saveLogo(){
         try{
            if(input!=null) {
       String output = SystemVariables.REPORT_FOLDER+"/new_logo.jpg";
           new ImageResizerClass().getInstance(input, output, 300, 300);
            File ufile= new File(output);
                    FileInputStream fs = new FileInputStream(ufile);
               preparedStatement = connection.prepareStatement("UPDATE "+INSTITUTION+" SET institution_log=?");
               preparedStatement.setBinaryStream(1, fs, ufile.length()); 
                preparedStatement.executeUpdate();
                 SystemVariables.INSTITUTION_LOGO = FunctionGetInstitutionDetails.getInstitutionLogo();
                MessagesUtil.displayMessage("Operation Successful", "Successfully saved Logo", 1, null);
            }
    }catch (SQLException ex) {
               ex.printStackTrace();
            }
       catch (FileNotFoundException ex) {
               ex.printStackTrace();
            }

    }
    public void populateLogo(){
          if (getInstitutionLogoStream() != null) {
            logoImage.setImage(new Image(getInstitutionLogoStream()));
        }
    }
}
