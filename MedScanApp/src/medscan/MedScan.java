/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medscan; 

import com.solutions.entorno.classes.MainClass;
import com.solutions.entorno.utilities.SystemFunctions;
import static com.solutions.entorno.utilities.SystemFunctions.pathname;
import com.solutions.entorno.utilities.SystemVariables;
import com.solutions.pwp.utilities.FXMLLoaderUtil;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.CTScanServicesModel;

/**
 *
 * @author dell
 */
public class MedScan extends Application {

//    @Override
//    public void start(Stage stage) throws Exception {
//        System.out.println("reached here");
//        Parent root = FXMLLoader.load(getClass().getResource("/views/MainUI.fxml"));
//        URL url = this.getClass().getResource("/css/JMetroLightTheme.css");
//        if (url == null) {
//            System.out.println("Resource not found. Aborting.");
//            System.exit(-1);
//        } 
//        String css = url.toExternalForm();  
//        
//        Scene scene = new Scene(root); 
//        scene.getStylesheets().add(css); 
//        stage.setScene(scene);
//        stage.setMaximized(true);
//        stage.show(); 
//    }
// 
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        MainClass.getInstance();
//        launch(args);
//    }
 public MedScan() {
 

        

        try {
            FXMLLoaderUtil.MAIN_WINDOW = FXMLLoader.load(getClass().getResource("/views/MainUI.fxml"));
//
//           
//            FXMLLoaderUtil.MATERIAL_REGISTRATION = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/solutions/pwp/views/MaterialRegistration.fxml"));
//
//            FXMLLoaderUtil.ITEMS_SETUP = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/solutions/pwp/views/ItemSetup.fxml"));
//
//            FXMLLoaderUtil.EMPLOYEE_SETUP = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/solutions/pwp/views/EmployeeSetup.fxml"));
//
//            FXMLLoaderUtil.SUPPLIERS_CLIENTS_REGISTRATION = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/solutions/pwp/views/SuppliersClientsSetup.fxml"));
//
//            FXMLLoaderUtil.SEWING_MATRICES = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/solutions/pwp/views/SewingMatrices.fxml"));
//
//            FXMLLoaderUtil.ITEM_SUPPLIES = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/solutions/pwp/views/ItemSupplies.fxml"));
//
//            FXMLLoaderUtil.ITEMS_STOCKING = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/solutions/pwp/views/ItemStocking.fxml"));
//
//            FXMLLoaderUtil.MATERIAL_STOCKING = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/solutions/pwp/views/MaterialStocking.fxml"));
//
//            FXMLLoaderUtil.EMPLOYEE = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/solutions/pwp/views/Employee.fxml"));
//
//            FXMLLoaderUtil.STOCK = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/solutions/pwp/views/Stock.fxml"));
//
//            FXMLLoaderUtil.REPORTS = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/solutions/pwp/views/ReportsHome_1.fxml"));
//
//            FXMLLoaderUtil.JOBS = (AnchorPane) FXMLLoader.load(getClass().getResource("/com/solutions/pwp/views/Jobs.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/MainUI.fxml"));
        URL url = this.getClass().getResource("/com/solutions/pwp/views/css/JMetroLightTheme.css");
        //JMetroLightTheme
        if (url == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        String css = url.toExternalForm();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainClass.getInstance();
        new MedScan();
        SystemVariables.USER_PROFILE = "USER001";
        File file = pathname();
        if (!file.exists()) {
            file.mkdirs();
        }
        file = SystemFunctions.reportspath("PWP/REPORTS");
        if (!file.exists()) {
            file.mkdirs();
        }
        SystemVariables.REPORT_FOLDER = SystemFunctions.reportspath("PWP/REPORTS").getAbsolutePath() + "/";
        launch(args);
    }

}
