/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.pwp.integrator.controllers;

import com.solutions.entorno.database.Database2Connection;
import com.solutions.entorno.utilities.MessagesUtil;
import com.solutions.entorno.utilities.SystemFunctions;
import static com.solutions.entorno.utilities.SystemFunctions.OneWayDataSecurity;
import com.solutions.entorno.utilities.SystemVariables;
import com.solutions.entorno.utilities.dialogs.NotificationType;
import com.solutions.pwp.integrator.controllers.utilities.SystemImageIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author shaddie
 */
public class SoftwareRegistration implements Initializable {

    @FXML
    private Button continueTrial;
    @FXML
    private Button submitKey;
    @FXML
    private Button exit;
    @FXML
    private TextField prodkey;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        submitKey.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String keyz = SystemFunctions.encrypt(prodkey.getText());
                System.out.println(keyz);
                Connection con = new Database2Connection().getConnectionvalue();
                Statement st = new Database2Connection().getStatementvalue(con);
                try {

                    ResultSet rs = st.executeQuery("SELECT * FROM studentstable");
                    if (rs.next()) {
                        System.out.println(rs.getString(3));
                        if (keyz.equals(rs.getString(3))) {
                            st.executeUpdate("UPDATE studentstable SET middlename='c0d83f0b82a6b30de8811e69e6d95c61'");
                            MessagesUtil.displayMessage("Registration Successful", "Thank You for Using Genuine Product.", 1, null);

                            loginPage();
                        } else {
                            MessagesUtil.displayMessage("Registration Failed!", "Invalid Product Key! Try Again", 1, null);
                        }
                    }

                } catch (SQLException e) {
                    MessagesUtil.displayMessage("Error Encountered", "SQL Error ", 3, e);
                } finally {
                    try {
                        con.close();
                        st.close();
                    } catch (Exception e) {
                    }
                }
            }
        });
        exit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.exit(1);
            }
        });

        continueTrial.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(SystemFunctions.isStaleCopy()){
                    SystemVariables.DISPLAY_MESSAGE.showInfoDialog(continueTrial.getScene().getWindow(), "TRIAL PERIOD IS OVER", NotificationType.INFORMATION);
                }
                 else
                {
                loginPage();
                }
            }
        });
    }

    public void welcomePage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/solutions/pwp/integrator/views/Welcome.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.initStyle(StageStyle.UNDECORATED);
            // stage.getIcons().add(new Image(SystemImageIcon.imagePath()));
            stage.show();
            Stage oldstage = (Stage) continueTrial.getScene().getWindow();
            oldstage.close();
        } catch (IOException ex) {
            Logger.getLogger(SoftwareRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loginPage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/solutions/pwp/integrator/views/login.fxml"));
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
            Stage oldstage = (Stage) continueTrial.getScene().getWindow();
            oldstage.close();
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
