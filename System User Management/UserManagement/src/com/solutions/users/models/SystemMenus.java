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
import static com.solutions.users.controllers.utilities.UserManagementVariables.MENUS_REGISTRATION;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.TableView;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class SystemMenus {

    private int applicationId;
    private int menuId;
    private String menuName;
    private String menuState;

    private void SystemMenus() {

    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuState() {
        return menuState;
    }

    public void setMenuState(String menuState) {
        this.menuState = menuState;
    }

    public static void addNewSystemApplication(int appid, String appName) {
        SystemMenus sysMenu = new SystemMenus();
        sysMenu.setApplicationId(appid);
        //sysMenu.setMenuId(1);
        sysMenu.setMenuState("1");
        sysMenu.setMenuName(appName);
        sysMenu.insertSystemMenus();
    }

    public static TableView getSystemMenusTb() {
        TableView tv;
        String[] headers = {"applicationId", "MenuId", "Menu Name"};
        String[] property = {"applicationId", "menuId", "menuName"};
        ArrayList<Object> model = getSystemMenus();
        TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }

    public void insertSystemMenus() {
        String inserSQL = "INSERT INTO " + MENUS_REGISTRATION + " ("
                + "            appid, menuname, menu_state)"
                + "    VALUES (?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(inserSQL);
            preparedStatement.setInt(1, applicationId);
            preparedStatement.setString(2, menuName);
            preparedStatement.setString(3, menuState);
            preparedStatement.executeUpdate();

            MessagesUtil.displayMessage("Save Operation Successful", "successfully saved Application", 1, null);
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtil.displayMessage("Save Operation failed", "Failed to Save Application", 3, e);
        }

    }

    public void updateSystemMenus() {
        String updateSQL = "UPDATE  " + MENUS_REGISTRATION + "  "
                + "   SET  menuname=?, menu_state=?"
                + " WHERE  appid=? AND  menuid=?";
        try {
            preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, menuName);
            preparedStatement.setString(2, menuState);
            preparedStatement.setInt(3, applicationId);
            preparedStatement.setInt(4, menuId);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully Updated", "SAVE MESSAGE", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed To Update", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private static ArrayList getSystemMenus() {
        SystemMenus systemMenus;
        ArrayList<SystemMenus> menus = new ArrayList<>();

        String selectSQL = "SELECT appid, menuid, menuname "
                + "  FROM  " + MENUS_REGISTRATION + " ";
        try {
            resultSet = statement.executeQuery(selectSQL);
            while (resultSet.next()) {
                systemMenus = new SystemMenus();
                systemMenus.setApplicationId(resultSet.getInt("appid"));
                systemMenus.setMenuId(resultSet.getInt("menuid"));
                systemMenus.setMenuName(resultSet.getString("menuname"));
                menus.add(systemMenus);
            }
        } catch (Exception e) {
            MessagesUtil.displayMessage("Fetch Failed", "Faeiled to fetch System menus", 3, e);
        }

        return menus;
    }

    private static ArrayList getSystemMenus2(int appid) {
        SystemMenus systemMenus;
        ArrayList<SystemMenus> menus = new ArrayList<>();

        String selectSQL = "SELECT appid, menuid, menuname "
                + "  FROM  " + MENUS_REGISTRATION + " where appid='"+appid+"' ";
        try {
            resultSet = statement.executeQuery(selectSQL);
            while (resultSet.next()) {
                systemMenus = new SystemMenus();
                systemMenus.setApplicationId(resultSet.getInt("appid"));
                systemMenus.setMenuId(resultSet.getInt("menuid"));
                systemMenus.setMenuName(resultSet.getString("menuname"));
                menus.add(systemMenus);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtil.displayMessage("Fetch Failed", "Faeiled to fetch System menus", 3, e);
        }

        return menus;
    }

    public static HashMap getSystemMenusMap(int appid) {
        HashMap<Integer, String> menumap = new HashMap();
        ArrayList<SystemMenus> menus = getSystemMenus2(appid);
        for (SystemMenus men : menus) {
            menumap.put(men.getMenuId(), men.getMenuName());
        }
        return menumap;
    }

}
