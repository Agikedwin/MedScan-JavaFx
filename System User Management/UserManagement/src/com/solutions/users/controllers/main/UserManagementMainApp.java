package com.solutions.users.controllers.main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.solutions.entorno.classes.MainClass;
import com.solutions.users.controllers.utilities.UserManagementTablesCreator;
import java.net.URL;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author dell
 */
public class UserManagementMainApp extends Application {

    public UserManagementMainApp() {
         new UserManagementTablesCreator();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/solutions/users/views/MainUI.fxml"));
        URL url = this.getClass().getResource("/com/solutions/users/views/css/mycss.css");
        if (url == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        String css = url.toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene); 
        stage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainClass.getInstance();
       new UserManagementMainApp();
        launch(args);
    }

}
