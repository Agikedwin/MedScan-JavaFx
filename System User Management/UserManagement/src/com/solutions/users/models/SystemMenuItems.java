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
import static com.solutions.users.controllers.utilities.UserManagementVariables.GROUP_PRIVILEGES;
import static com.solutions.users.controllers.utilities.UserManagementVariables.SUBMENUS_REGISTRATION;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.TableView;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class SystemMenuItems {

    private int itemId;
    private int menuId;
    private String itemName;

    private void SystemMenuItems() {

    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public static void addNewSystemMenuItem(String appName) {
        SystemMenuItems sysMenuitems = new SystemMenuItems();
        sysMenuitems.setItemId(2);
        sysMenuitems.setMenuId(2);
        sysMenuitems.setItemName(appName);
        sysMenuitems.insertSystemMenuItems();
    }

    public static TableView getSystemMenusItemsTb() {
        TableView tv;
        String[] headers = {"Item ID", "Menu ID", "Item Name"};
        String[] property = {"itemId", "menuId", "itemName"};
        ArrayList<Object> model = getSystemMenuItems();
        TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }

    public static TableView getSystemPossiblePrevItemsTb(int usrgrp) {
        TableView tv;
        String[] headers = {"Item ID", "Item Name"};
        String[] property = {"itemId", "itemName"};
        ArrayList<Object> model = getUserGroupMenuItems(usrgrp);
        TableViewRenderer tbl = new TableViewRenderer(headers, model, property);

        tv = tbl.getTable();
        return tv;
    }

    public void insertSystemMenuItems() {
        String inserSQL = "INSERT INTO " + SUBMENUS_REGISTRATION + "("
                + "            itemid, menuid, itemname)"
                + "    VALUES (?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(inserSQL);
            preparedStatement.setInt(1, itemId);
            preparedStatement.setInt(2, menuId);
            preparedStatement.setString(3, itemName);
            preparedStatement.executeUpdate();
            MessagesUtil.displayMessage("Save Operation Successful", "successfully saved System Menu Items", 1, null);
        } catch (Exception e) {
            MessagesUtil.displayMessage("Save Operation failed", "Failed to Save System Menu Items", 3, e);
        }

    }

    public void updateSystemMenuItems() {
        String updateSQL = "UPDATE " + SUBMENUS_REGISTRATION + ""
                + "   SET  itemname=?"
                + " WHERE itemid=? AND menuid=?";
        try {
            preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, itemName);
            preparedStatement.setInt(2, itemId);
            preparedStatement.setInt(3, menuId);

            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Successfully Updated", "SAVE MESSAGE", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed To Update", "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private static ArrayList getSystemMenuItems() {
        SystemMenuItems systemMenuItems;
        ArrayList<SystemMenuItems> MenuItems = new ArrayList<>();

        String selectSQL = "SELECT itemid, menuid, itemname"
                + "  FROM " + SUBMENUS_REGISTRATION + "";
        try {
            resultSet = statement.executeQuery(selectSQL);
            while (resultSet.next()) {
                systemMenuItems = new SystemMenuItems();
                systemMenuItems.setItemId(resultSet.getInt("itemid"));
                systemMenuItems.setMenuId(resultSet.getInt("menuid"));
                systemMenuItems.setItemName(resultSet.getString("itemname"));
                MenuItems.add(systemMenuItems);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return MenuItems;
    }

    private static ArrayList getUserGroupMenuItems(int usergroup) {
        SystemMenuItems systemMenuItems;
        ArrayList<SystemMenuItems> MenuItems = new ArrayList<>();

        String selectSQL = "SELECT itemid, menuid, itemname"
                + "  FROM " + SUBMENUS_REGISTRATION + " WHERE itemid not in"
                + "(select  privilege from " + GROUP_PRIVILEGES + " where usergroup = " + usergroup + ")";
        try {
            resultSet = statement.executeQuery(selectSQL);
            while (resultSet.next()) {
                systemMenuItems = new SystemMenuItems();
                systemMenuItems.setItemId(resultSet.getInt("itemid"));
                systemMenuItems.setMenuId(resultSet.getInt("menuid"));
                systemMenuItems.setItemName(resultSet.getString("itemname"));
                MenuItems.add(systemMenuItems);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return MenuItems;
    }
    public static HashMap getUserGroupMenuItemsMap() {
        int usergroup = 0;
        HashMap<Integer, String> menumap = new HashMap();
        ArrayList<SystemMenuItems> menus = getUserGroupMenuItems(usergroup);
        for (SystemMenuItems menu : menus) {
            menumap.put(menu.getItemId(), menu.getItemName());
        }
        return menumap;
    }
}
