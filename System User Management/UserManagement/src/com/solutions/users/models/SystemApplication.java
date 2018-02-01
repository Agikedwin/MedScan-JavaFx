/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.users.models;

import com.solutions.entorno.utilities.MessagesUtil;
import static com.solutions.entorno.utilities.SystemVariables.connection;
import static com.solutions.entorno.utilities.SystemVariables.preparedStatement;
import static com.solutions.entorno.utilities.SystemVariables.resultSet;
import static com.solutions.entorno.utilities.SystemVariables.statement;
import com.solutions.entorno.utilities.TableViewRenderer;
import static com.solutions.users.controllers.utilities.UserManagementVariables.APP_REGISTRATION;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.TableView;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class SystemApplication {

    private int applicationId;
    private String applicationName;
    private int state;
    private String doneBy;
    private String lastUpdated;

    private void SystemApplication() {

    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getDoneBy() {
        return doneBy;
    }

    public void setDoneBy(String doneBy) {
        this.doneBy = doneBy;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public static void addNewSystemApplication(String appName) {
        SystemApplication sysapp = new SystemApplication();
        sysapp.setApplicationName(appName);
        sysapp.setState(1);
        sysapp.addSystemApplication();
    }

    private void addSystemApplication() {
        String inserSQL = "INSERT INTO " + APP_REGISTRATION + "("
                + "             appname, app_state)"
                + "    VALUES ( ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(inserSQL);
            preparedStatement.setString(1, applicationName);
            preparedStatement.setInt(2, 1);
            preparedStatement.executeUpdate();
            MessagesUtil.displayMessage("Save Operation Successful", "Successfully Saved Application", 1, null);
        } catch (Exception e) {
            MessagesUtil.displayMessage("Save Operation failed", "Failed to Save Application", 3, e);
        }

    }

    public void updateSystemApplication() {
        String updateSQL = "UPDATE " + APP_REGISTRATION + ""
                + "   SET  appname=?, app_state=?"
                + " WHERE appid=?";
        try {
            preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, applicationName);
            preparedStatement.setInt(2, state);
            preparedStatement.setInt(3, applicationId);

            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully Updated", "SAVE MESSAGE", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed To Update", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private static ArrayList getSystemApplication() {
        SystemApplication systemApplication;
        ArrayList<SystemApplication> systemApp = new ArrayList<>();

        String selectSQL = "SELECT appid, appname, app_state "
                + "  FROM " + APP_REGISTRATION + " ";
        try {
            resultSet = statement.executeQuery(selectSQL);
            while (resultSet.next()) {
                systemApplication = new SystemApplication();
                systemApplication.setApplicationId(resultSet.getInt("appid"));
                systemApplication.setApplicationName(resultSet.getString("appname"));
                systemApplication.setState(resultSet.getInt("app_state"));
                systemApp.add(systemApplication);
            }
        } catch (Exception e) {
            MessagesUtil.displayMessage("Fetch Failed", "Faeiled to fetch System Applications", 3, e);
        }
        return systemApp;
    }

    public static HashMap getApplicationMap() {
        HashMap<Integer, String> appmap = new HashMap();
        ArrayList<SystemApplication> apps = getSystemApplication();
        for (SystemApplication app : apps) {
            appmap.put(app.getApplicationId(), app.getApplicationName());
        }
        return appmap;
    }

    public static TableView getApplications() {
        TableView tv;
        String[] headers = {"applicationId", "applicationName"};
        String[] property = {"applicationId", "applicationName"};
        ArrayList<Object> model = getSystemApplication();
        TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }

}
