/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author dell
 */
public class Load {

    public void loadView(AnchorPane parent, String path) {
        try {
            AnchorPane p = (AnchorPane) FXMLLoader.load(getClass().getResource(path));
            AnchorPane.setTopAnchor(p, Double.valueOf(0));
            AnchorPane.setLeftAnchor(p, Double.valueOf(0));
            AnchorPane.setRightAnchor(p, Double.valueOf(0));
            AnchorPane.setBottomAnchor(p, Double.valueOf(0));
            parent.getChildren().setAll(p);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTable(AnchorPane parent, TableView table) {
        AnchorPane.setTopAnchor(table, Double.valueOf(0));
        AnchorPane.setLeftAnchor(table, Double.valueOf(0));
        AnchorPane.setRightAnchor(table, Double.valueOf(0));
        AnchorPane.setBottomAnchor(table, Double.valueOf(0));
        parent.getChildren().setAll(table);
    }

    public AnchorPane LoadView2(AnchorPane parent, String path) {
        AnchorPane p = null;
        try {
            p = (AnchorPane) FXMLLoader.load(getClass().getResource(path));
            parent.getChildren().setAll(p);
        } catch (IOException e) {
        }
        return p;
    }

}
