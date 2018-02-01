/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.pwp.integrator.controllers.main.main;

import com.solutions.entorno.classes.MainClass;
import com.solutions.entorno.utilities.ProductKeyUtil;
import com.solutions.entorno.utilities.SystemFunctions;
import static com.solutions.entorno.utilities.SystemFunctions.isStaleCopy;
import static com.solutions.entorno.utilities.SystemFunctions.pathname;
import static com.solutions.entorno.utilities.SystemFunctions.recordCountPopulator;
import static com.solutions.entorno.utilities.SystemFunctions.registered;
import static com.solutions.entorno.utilities.SystemFunctions.returnRecordCount;
import com.solutions.entorno.utilities.SystemVariables;
import static com.solutions.entorno.utilities.SystemVariables.PRODUCT_KEY;
import com.solutions.pwp.integrator.controllers.utilities.InstitutionTableCreator;
import com.solutions.pwp.integrator.controllers.utilities.SystemImageIcon;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 *
 * @author shaddie
 */
public class POSAppStart extends Application {

    private static int load = -1;

    public static void main(String[] args) {
        SystemVariables.SETTINGS_FILE = System.getProperty("CONF", "settings.xml");
        MainClass.getInstance();
        SystemVariables.DATABASE2_NAME = "pwp";
        PRODUCT_KEY = ProductKeyUtil.DEFAULT_KEY;
        new InstitutionTableCreator();
        File file = pathname();
        if (!file.exists()) {
            file.mkdirs();
        }
        file = SystemFunctions.reportspath("PWP/REPORTS");
        if(!file.exists()){
             file.mkdirs();
        }
        SystemVariables.REPORT_FOLDER = SystemFunctions.reportspath("PWP/REPORTS").getAbsolutePath()+"/";
        if (returnRecordCount() < 1) {
            if (!recordCountPopulator()) {

                JOptionPane.showMessageDialog(null, "Error encountered Updating System", "FAILED", JOptionPane.ERROR_MESSAGE);
            } else {
                System.exit(1);
            }
        }
        
        if(registered()){
             load = 2;
            launch(args);
        }
       else {
            load = 1;
            launch(args);
        }

    }

    public void registration() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/solutions/pwp/integrator/views/SoftwareRegistration.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
             URL url = this.getClass().getResource("/com/solutions/pwp/integrator/views/css/mycss.css");
            if (url == null) {
                System.out.println("Resource not found. Aborting.");
                System.exit(-1);
            }
            String css = url.toExternalForm();
            stage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(root1);
            stage.setScene(scene);
            scene.getStylesheets().add(css);
            stage.getIcons().add(new Image(SystemImageIcon.imagePath()));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(com.solutions.pwp.integrator.controllers.SoftwareRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void welcome() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/solutions/pwp/integrator/views/login.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image(SystemImageIcon.imagePath()));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(com.solutions.pwp.integrator.controllers.SoftwareRegistration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        if (load == 1) {
            registration();
        } else if (load == 2) {
            welcome();
        }
    }
}
