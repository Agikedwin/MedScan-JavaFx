package com.solutions.users.controllers.utilities;

import static com.solutions.entorno.utilities.SystemVariables.statement;
import static com.solutions.users.controllers.utilities.UserManagementVariables.APP_REGISTRATION;
import static com.solutions.users.controllers.utilities.UserManagementVariables.ASSIGNUSER_TOGROUP;
import static com.solutions.users.controllers.utilities.UserManagementVariables.GROUP_PRIVILEGES;
import static com.solutions.users.controllers.utilities.UserManagementVariables.MENUS_REGISTRATION;
import static com.solutions.users.controllers.utilities.UserManagementVariables.SUBMENUS_REGISTRATION;
import static com.solutions.users.controllers.utilities.UserManagementVariables.USERGROUPS;
import static com.solutions.users.controllers.utilities.UserManagementVariables.USER_REGISTRATION;

import java.sql.SQLException;

/**
 *
 * @author shaddie
 */
public class UserManagementTablesCreator {

    public UserManagementTablesCreator() {
        SystemApplication();
        SystemMenus();
        SystemMenuItems();

        users();
        user_groups();
        group_privileges();
        assignUserToGroups();
    }

   
    //system users

    private void SystemApplication() {
        try {
            statement.executeQuery("SELECT * FROM " + APP_REGISTRATION + "");
        } catch (SQLException e) {

            String query = "CREATE TABLE " + APP_REGISTRATION + "(appid serial,appname varchar(150) NOT NULL,app_state varchar(150) NOT NULL ,"
                    + "PRIMARY KEY(appid))";
            try {
                statement.executeUpdate(query);
            } catch (SQLException e1) {
                System.out.println("error  " + APP_REGISTRATION + " failed " + e1);
                e1.printStackTrace();
            }
        }

    }

    private void SystemMenus() {
        try {
            statement.executeQuery("SELECT * FROM " + MENUS_REGISTRATION + "");
        } catch (SQLException e) {

            String query = "CREATE TABLE " + MENUS_REGISTRATION + "(appid int,menuid serial,menuname varchar(150) NOT NULL unique,menu_state varchar(150) NOT NULL ,"
                    + "PRIMARY KEY(appid,menuid))";
            try {
                statement.executeUpdate(query);
            } catch (SQLException e1) {
                System.out.println("error  " + MENUS_REGISTRATION + " " + e1);
                e1.printStackTrace();
            }
        }
    }

    private void SystemMenuItems() {
        try {
            statement.executeQuery("SELECT * FROM " + SUBMENUS_REGISTRATION + "");
        } catch (SQLException e) {

            String query = "CREATE TABLE " + SUBMENUS_REGISTRATION + "(itemid serial,menuid int not null,itemname varchar(150) NOT NULL ,"
                    + "PRIMARY KEY(itemid))";
            try {
                statement.executeUpdate(query);
            } catch (SQLException e1) {
                System.out.println("error  " + SUBMENUS_REGISTRATION + " " + e1);

            }
        }
    }

    //Users table

    private void users() {
        try {
            statement.executeQuery("SELECT * FROM " + USER_REGISTRATION + "");
        } catch (SQLException e) {
            String query = "CREATE TABLE " + USER_REGISTRATION + "("
                    + "userid varchar(50),"
                    + "firstname VARCHAR(50),"
                    + " middlename VARCHAR(50),"
                    + "lastname VARCHAR(50),"
                    + "phoneno VARCHAR(50) ,"
                    + "datereg VARCHAR(50)  ,"
                    + "doneby varchar(50)  ,"
                    + "username VARCHAR(30) ,"
                    + "password VARCHAR(150) ,"
                    + "additional VARCHAR(50),"
                    + "PRIMARY KEY(userid))";
            try {
                statement.executeUpdate(query);
            } catch (SQLException e1) {
                System.out.println("error " + USER_REGISTRATION + " " + e1.getMessage());
            }
        }
    }

    //Usergroup tables

    private void user_groups() {
        try {
            statement.executeQuery("SELECT * FROM " + USERGROUPS + "");
        } catch (SQLException e) {
            String query = "CREATE TABLE " + USERGROUPS + "("
                    + "groupid serial,"
                    + "groupname varchar(200) NOT NULL,"
                    + "date_reg varchar(15),"
                    + "lastupdate varchar(15),"
                    + "userid varchar(30),"
                    + " PRIMARY KEY(groupid))";
            try {
                statement.executeUpdate(query);
            } catch (SQLException e1) {
                System.out.println("error  " + USERGROUPS + " " + e1);
            }
        }
    }

    //Group privileges table

    private void group_privileges() {
        try {
            statement.executeQuery("SELECT * FROM " + GROUP_PRIVILEGES + "");
        } catch (SQLException e) {
            String query = "CREATE TABLE " + GROUP_PRIVILEGES + " (usergroup int,privilege int,"
                    + " PRIMARY KEY(usergroup,privilege))";
            try {
                statement.executeUpdate(query);
            } catch (SQLException e1) {
                System.out.println("" + GROUP_PRIVILEGES + " " + e1);
            }
        }
    }
     //Mapping of users to usergroups table

    private void assignUserToGroups() {
        try {
            statement.executeQuery("SELECT * FROM " + ASSIGNUSER_TOGROUP + "");
        } catch (SQLException e) {
            String query = "CREATE TABLE " + ASSIGNUSER_TOGROUP + "(userid varchar(50) NOT NULL,groupid int not null,"
                    + " PRIMARY KEY(userid,groupid))";
            try {
                statement.executeUpdate(query);
            } catch (SQLException e1) {
                System.out.println("" + ASSIGNUSER_TOGROUP + " table creation failed " + e1);
            }
        }
    }
}
