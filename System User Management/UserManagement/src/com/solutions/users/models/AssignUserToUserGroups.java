/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.users.models;

import com.solutions.entorno.utilities.MessagesUtil;
import static com.solutions.entorno.utilities.SystemVariables.SYSTEM_DATE;
import static com.solutions.entorno.utilities.SystemVariables.USER_PROFILE;
import static com.solutions.entorno.utilities.SystemVariables.resultSet;
import static com.solutions.entorno.utilities.SystemVariables.statement;
import com.solutions.entorno.utilities.TableViewRenderer;
import static com.solutions.users.controllers.utilities.UserManagementVariables.ASSIGNUSER_TOGROUP;
import static com.solutions.users.models.User.readUsers;
import java.util.ArrayList;
import javafx.scene.control.TableView;

/**
 *
 * @author AND
 */
public class AssignUserToUserGroups {

    String g_userId;
    int g_groupId;

    //Mandatory fields
    private String date_reg;
    private String last_update;
    private String userId;
    private String applicationName;
    private String userName;
    private String password;

    public String getG_userId() {
        return g_userId;
    }

    public void setG_userId(String g_userId) {
        this.g_userId = g_userId;
    }

    public int getG_groupId() {
        return g_groupId;
    }

    public void setG_groupId(int g_groupId) {
        this.g_groupId = g_groupId;
    }

    public String getDate_reg() {
        return date_reg;
    }

    public void setDate_reg(String date_reg) {
        this.date_reg = date_reg;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //mandatory 
    public static void addNewAssignUserToUserGroups(String usrid, int groupid) {
        AssignUserToUserGroups prv = new AssignUserToUserGroups();
        prv.setG_userId(usrid);
        prv.setG_groupId(groupid);
        prv.addAssignUserToGroup();
    }
    //Assign users priviledges table properties

    public static TableView getAssignUserToGroupTb() {
        TableView tv;
        String[] headers = {"User ID", "First Name", "Middle Name", "Last Name", "Phone No", "User Name"};
        String[] property = {"userId", "firstName", "middleName", "lastName", "phoneNo", "username"};
        ArrayList<Object> model = readUsers();
        TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }

    //Persisting usergroups
    public void addAssignUserToGroup() {
        try {
            resultSet = statement.executeQuery("SELECT * from " + ASSIGNUSER_TOGROUP + " WHERE userid='" + getG_userId() + "' ");
            if (resultSet.next()) {
                statement.executeUpdate("UPDATE " + ASSIGNUSER_TOGROUP + " set groupid = " + getG_groupId() + "  WHERE userid='" + getG_userId() + "'  ");
            } else {
                statement.executeUpdate("INSERT INTO  " + ASSIGNUSER_TOGROUP + "  VALUES ('" + getG_userId() + "'," + getG_groupId() + ")");
            }
            MessagesUtil.displayMessage("Save Operation Successful", "Successfully saved AssignUserToGroup", 1, null);
        } catch (Exception e) {
            MessagesUtil.displayMessage("Save Operation failed", "Failed to Save AssignUserToGroup", 3, e);
        }
    }

    //Modifying the usergroups
    public void update(int g_groupId, String g_userId) {
        try {

            statement.executeUpdate("UPDATE " + ASSIGNUSER_TOGROUP + " SET userid='" + getG_userId() + "',groupid=" + getG_groupId() + ","
                    + " last_update = '" + SYSTEM_DATE + "', userid = '" + USER_PROFILE + "'"
                    + " WHERE groupid=" + g_groupId + " AND userid=" + g_userId);
            System.out.println("Successfully updated the record");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not update the the record for assign user to privileges " + e.getMessage());
        }
    }
}
