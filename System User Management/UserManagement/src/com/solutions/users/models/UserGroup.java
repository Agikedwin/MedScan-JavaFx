/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.users.models;

import com.solutions.entorno.utilities.MessagesUtil;
import static com.solutions.entorno.utilities.SystemVariables.SYSTEM_DATE;
import static com.solutions.entorno.utilities.SystemVariables.USER_PROFILE;
import static com.solutions.entorno.utilities.SystemVariables.connection;
import static com.solutions.entorno.utilities.SystemVariables.preparedStatement;
import static com.solutions.entorno.utilities.SystemVariables.resultSet;
import static com.solutions.entorno.utilities.SystemVariables.statement;
import com.solutions.entorno.utilities.TableViewRenderer;
import static com.solutions.users.controllers.utilities.UserManagementVariables.USERGROUPS;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TableView;

/**
 *
 * @author AND
 */
public class UserGroup {

    String groupname;
    int groupId;

    //Mandatory fields
    private String date_reg;
    private String last_update;
    private String userId;

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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

    //set mandatory fileds
    public static void addNewreadUserGroups(String groupName) {
        UserGroup userGroup = new UserGroup();
        userGroup.setGroupname(groupName);
        userGroup.addUserGroups();
    }

    //user group table properties
    public static TableView getUserGroupTb() {
        TableView tv;
        String[] headers = {"Group ID", "Group Name"};
        String[] property = {"groupId", "groupname"};
        ArrayList<Object> model = readUserGroups();
        TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }

    //Persisting usergroups
    public void addUserGroups() {
        try {
            String query = "INSERT INTO " + USERGROUPS + "(groupname,date_reg,lastupdate,userid) VALUES (?, ?, ?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, getGroupname());
            preparedStatement.setString(2, SYSTEM_DATE);
            preparedStatement.setString(3, SYSTEM_DATE);
            preparedStatement.setString(4, USER_PROFILE);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            MessagesUtil.displayMessage("Save Operation Successful", "Successfully Saved User Group", 1, null);
        } catch (Exception e) {
            MessagesUtil.displayMessage("Save Operation failed", "Failed to Save User Group", 3, e);
        }

    }

    //Modifying the usergroups
    public void update(String groupId) {
        try {
            statement.executeUpdate("UPDATE " + USERGROUPS + " SET groupname='" + getGroupname() + "',"
                    + " last_update = '" + SYSTEM_DATE + "', userid = '" + USER_PROFILE + "'"
                    + " WHERE skuId=" + groupId + "");
            System.out.println("Successfully updated the record");
        } catch (Exception e) {
            System.out.println("Could not update the the record for usergroup " + e.getMessage());
        }
    }

    public static ArrayList readUserGroups() {
        ArrayList<UserGroup> readAll = new ArrayList<>();
        UserGroup usergroup;
        try {
            resultSet = statement.executeQuery("SELECT * FROM " + USERGROUPS + " ");
            while (resultSet.next()) {
                usergroup = new UserGroup();
                usergroup.setGroupId(resultSet.getInt(1));
                usergroup.setGroupname(resultSet.getString(2));
                readAll.add(usergroup);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserGroup.class.getName()).log(Level.SEVERE, null, ex);
        }
        return readAll;
    }

    public static HashMap getUserGroupMap() {
        HashMap<Integer, String> groupmap = new HashMap();
        ArrayList<UserGroup> maps = readUserGroups();
        for (UserGroup map : maps) {
            groupmap.put(map.getGroupId(), map.getGroupname());
        }
        return groupmap;
    }
}
