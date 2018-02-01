/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.users.models;

import static com.solutions.entorno.utilities.SystemVariables.SYSTEM_DATE;
import static com.solutions.entorno.utilities.SystemVariables.USER_PROFILE;
import static com.solutions.entorno.utilities.SystemVariables.connection;
import static com.solutions.entorno.utilities.SystemVariables.preparedStatement;
import static com.solutions.entorno.utilities.SystemVariables.resultSet;
import static com.solutions.entorno.utilities.SystemVariables.statement;
import com.solutions.entorno.utilities.TableViewRenderer;
import static com.solutions.users.controllers.utilities.UserManagementVariables.GROUP_PRIVILEGES;
import static com.solutions.users.controllers.utilities.UserManagementVariables.USERGROUPS;
import static com.solutions.users.models.User.readUsers;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TableView;

/**
 *
 * @author AND
 */
public class GroupPrivilege {

    int usergroupId;
    int privilegeId;

    //Mandatory fields
    private String date_reg;
    private String last_update;
    private String userId;

    public int getUsergroupId() {
        return usergroupId;
    }

    public void setUsergroup(int usergroupId) {
        this.usergroupId = usergroupId;
    }

    public int getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(int privilegeId) {
        this.privilegeId = privilegeId;
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
    public static TableView getUserTb() {
        TableView tv;
        String[] headers = {"Group ID", "Group Privilege"};
        String[] property = {"userId", "firstName", "middleName", "lastName", "phoneNo","username"};
        ArrayList<Object> model = readUsers();
        TableViewRenderer tbl = new TableViewRenderer(headers, model, property);
        
        tv = tbl.getTable();
        return tv;
    }

    //Persisting usergroups

    public void addUserGroups() {
        try {
            String query = "INSERT INTO " + GROUP_PRIVILEGES + " VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            //  preparedStatement.setString(1, getSkucategoryId());
            preparedStatement.setInt(1, getUsergroupId());
            preparedStatement.setInt(2, getPrivilegeId());
            preparedStatement.setString(3, SYSTEM_DATE);
            preparedStatement.setString(4, SYSTEM_DATE);
            preparedStatement.setString(5, USER_PROFILE);
            preparedStatement.executeUpdate();

            System.out.println("Group privileges successfully  saved ");
            // JOptionPane.showMessageDialog(null, "Successfully saved the record", "Error Encountered", JOptionPane.ERROR_MESSAGE);
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println("Group privileges " + e.getMessage());
            //JOptionPane.showMessageDialog(null, e.getMessage(), "Error Encountered", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Modifying the usergroups

    public void update(String usergrouId, String privilegeId) {
        try {
            statement.executeUpdate("UPDATE " + GROUP_PRIVILEGES + " SET usergroup=" + getUsergroupId() + ",privilege=" + getPrivilegeId() + ","
                    + " last_update = '" + SYSTEM_DATE + "', userid = '" + USER_PROFILE + "'"
                    + " WHERE skuId=" + usergroupId + " AND privilege=" + privilegeId);
            System.out.println("Successfully updated the record");
        } catch (Exception e) {
            System.out.println("Could not update the the record for group privileges " + e.getMessage());
        }
    }

    //Fetching all the group privileges records

    public ArrayList readGroupPrivleges() {
        ArrayList<GroupPrivilege> readAll = new ArrayList<>();
        GroupPrivilege grouprivilege;
        try {
            resultSet = statement.executeQuery("SELECT * FROM " + GROUP_PRIVILEGES + " ");
            while (resultSet.next()) {
                grouprivilege = new GroupPrivilege();
                grouprivilege.setUsergroup(resultSet.getInt(1));
                grouprivilege.setPrivilegeId(resultSet.getInt(2));
                readAll.add(grouprivilege);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserGroup.class.getName()).log(Level.SEVERE, null, ex);
        }
        return readAll;
    }
}
