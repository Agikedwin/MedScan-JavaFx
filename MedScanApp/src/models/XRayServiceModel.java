/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import static com.solutions.entorno.utilities.SystemVariables.connection;
import static com.solutions.entorno.utilities.SystemVariables.preparedStatement;
import static com.solutions.entorno.utilities.SystemVariables.resultSet;
import static com.solutions.entorno.utilities.SystemVariables.statement;
import controllers.XRayServices;
import database.MedscanVariables;
import static database.MedscanVariables.TBL_SERVICES;
import static database.MedscanVariables.TBL_XRAY;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author YoungGucha
 */
public class XRayServiceModel {
    XRayServices xRay;
    

    public boolean insertXRayServices(XRayServices xRayServices) {
        boolean result = false;
        String insertSQL = "INSERT INTO " + TBL_XRAY + "(`service_name`, `cost`)"
                + " VALUES (?,?)";
        try {

            preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, xRayServices.getService_name());
            preparedStatement.setDouble(2, xRayServices.getCost());
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return result;

    }

    public ArrayList<XRayServices> getAllXRayServices(int id) {
        ArrayList<XRayServices> xRayServices = new ArrayList<>();

        try {
            resultSet = statement.executeQuery("SELECT ``serviceid`, `service_name`, `cost`, `datereg`, `lastupdated`,"
                    + "`deleted`, `status`"
                    + " FROM  " + TBL_XRAY + ""
                    + " ");
            while (resultSet.next()) {
                xRay = new XRayServices();
                xRay.setServiceid(resultSet.getInt("serviceid"));
                xRay.setService_name(resultSet.getString("service_name"));
                xRay.setCost(resultSet.getDouble("cost"));
                xRay.setDatereg(resultSet.getTimestamp("datereg"));
                xRay.setStatus(resultSet.getInt("status"));
                xRay.setLastupdated(resultSet.getTimestamp("last_updated"));
                xRay.setDeleted(resultSet.getInt("deleted"));
                xRayServices.add(xRay);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return xRayServices;
    }

    public ArrayList getXRayServices(int id) {
        ArrayList<XRayServices> array = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("SELECT `serviceid`, `service_name`, `cost`, `datereg`, "
                    + "`lastupdated`, `deleted`, `status` FROM  " + TBL_XRAY + " WHERE serviceid=" + id + " ");
            while (resultSet.next()) {
                xRay = new XRayServices();
                xRay.setServiceid(resultSet.getInt("serviceid"));
                xRay.setService_name(resultSet.getString("service_name"));
                xRay.setCost(resultSet.getDouble("cost"));
                xRay.setDatereg(resultSet.getTimestamp("datereg"));
                xRay.setStatus(resultSet.getInt("status"));
                xRay.setLastupdated(resultSet.getTimestamp("last_updated"));
                xRay.setDeleted(resultSet.getInt("deleted"));
                array.add(xRay);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return array;
    }

    public boolean updateXRayServices(XRayServices xRayServices) {
        boolean result = false;

        String insertSQL = "UPDATE " + TBL_XRAY + " SET service_name=?, `cost` =? WHERE WHERE serviceid=?";
        try {

            preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, xRayServices.getService_name());
            preparedStatement.setDouble(2, xRayServices.getCost());

            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return result;
    }

    public int deleteXRayServices(XRayServices xRayServices) {
        String selectSQL = "UPDATE " + TBL_XRAY + " SET `deleted`=1 WHERE serviceid=?";
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(selectSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            preparedStatement.setInt(1, xRayServices.getServiceid());
            result = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
