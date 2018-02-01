/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.users.models;

import com.solutions.entorno.utilities.MessagesUtil;
import com.solutions.entorno.utilities.SystemFunctions;
import static com.solutions.entorno.utilities.SystemVariables.SYSTEM_DATE;
import static com.solutions.entorno.utilities.SystemVariables.USER_PROFILE;
import static com.solutions.entorno.utilities.SystemVariables.connection;
import static com.solutions.entorno.utilities.SystemVariables.preparedStatement;
import static com.solutions.entorno.utilities.SystemVariables.resultSet;
import static com.solutions.entorno.utilities.SystemVariables.statement;
import com.solutions.entorno.utilities.TableViewRenderer;
import static com.solutions.users.controllers.utilities.UserManagementVariables.USER_REGISTRATION;
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
public class User {

    String userId, firstName, middleName, lastName, gender, dob, residence, address, phoneNo, date_reg, time_done, doneby;
    String username, password, additional;
    int idNo, status;
    String applicationName;

 //Mandatory fields 
    //The db for the system does not support the mandatory fields
    String createdate, lastupdate, modifiedBy;
    
    public String getApplicationName() {
        return applicationName;
    }
    
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
    
    public String getCreatedate() {
        return createdate;
    }
    
    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }
    
    public String getLastupdate() {
        return lastupdate;
    }
    
    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }
    
    public String getModifiedBy() {
        return modifiedBy;
    }
    
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getMiddleName() {
        return middleName;
    }
    
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getDob() {
        return dob;
    }
    
    public void setDob(String dob) {
        this.dob = dob;
    }
    
    public String getResidence() {
        return residence;
    }
    
    public void setResidence(String residence) {
        this.residence = residence;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPhoneNo() {
        return phoneNo;
    }
    
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    
    public String getDate_reg() {
        return date_reg;
    }
    
    public void setDate_reg(String date_reg) {
        this.date_reg = date_reg;
    }
    
    public String getTime_done() {
        return time_done;
    }
    
    public void setTime_done(String time_done) {
        this.time_done = time_done;
    }
    
    public String getDoneby() {
        return doneby;
    }
    
    public void setDoneby(String doneby) {
        this.doneby = doneby;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getAdditional() {
        return additional;
    }
    
    public void setAdditional(String additional) {
        this.additional = additional;
    }
    
    public int getIdNo() {
        return idNo;
    }
    
    public void setIdNo(int idNo) {
        this.idNo = idNo;
    }
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }

    //change other people pwd

    public static void changePwd(String newspass,String usrid) {
        User user = new User();
        user.setUserId(usrid);
        user.setPassword(newspass);
        user.updateUsername(usrid);
    }

    //mandatory 

    public static void addNewUser(String fname, String mname, String lname, String phoneno, String usrname, String pword) {
        User user = new User();
        String userId = generateUserId();
        user.setUserId(userId);
        user.setFirstName(fname);
        user.setMiddleName(mname);
        user.setLastName(lname);
        user.setPhoneNo(phoneno);
        user.setUsername(usrname);
        user.setPassword(pword);
        user.addUser();
    }

    //users table properties

    public static TableView getUserTb() {
        TableView tv;
        String[] headers = {"User ID", "First Name", "Middle Name", "Last Name", "Phone No","User Name"};
        String[] property = {"userId", "firstName", "middleName", "lastName", "phoneNo","username"};
        ArrayList<Object> model = readUsers();
        TableViewRenderer tbl = new TableViewRenderer(headers, model, property);
        
        tv = tbl.getTable();
        return tv;
    }

    //Adding user to the system

    public void addUser() {
        setUserId(generateUserId());
        try {
            String query = "INSERT INTO " + USER_REGISTRATION + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, getUserId());
            preparedStatement.setString(2, getFirstName());
            preparedStatement.setString(3, getMiddleName());
            preparedStatement.setString(4, getLastName());    
            preparedStatement.setString(5, getPhoneNo()); 
            preparedStatement.setString(6, SYSTEM_DATE);
            preparedStatement.setString(7, USER_PROFILE);
            preparedStatement.setString(8, getUsername());
            preparedStatement.setString(9, SystemFunctions.OneWayDataSecurity(getPassword()));
            preparedStatement.setString(10, "");
            preparedStatement.executeUpdate();
            MessagesUtil.displayMessage("Save Operation Successful", "Successfully saved User", 1, null);
        } catch (Exception e) {
            MessagesUtil.displayMessage("Save Operation failed", "Failed to Save User", 3, e);
        }
    }

    // Updating records for customers

    public void update(String userid) {
        try {
            statement.executeUpdate("UPDATE " + USER_REGISTRATION + " SET firstname='" + getFirstName() + "',"
                    + " middlename='" + getMiddleName() + "', lastname= '" + getLastName() + "',"
                    + " phoneno= '" + getPhoneNo() + "',"
                    + " doneby='" + getDoneby() + "', username= '" + getUsername() + "'"
                    + " WHERE userid='" + userid + "'");
            MessagesUtil.displayMessage("Update Operation Successful", "Successfully Updated User", 1, null);
        } catch (Exception e) { 
            MessagesUtil.displayMessage("Update Operation failed", "Failed to Update User", 3, e);
        }
    }

    //update user name

    public void updateUsername(String userid) {
        try {
            String pass = SystemFunctions.OneWayDataSecurity(getPassword());
            statement.executeUpdate("UPDATE " + USER_REGISTRATION + " SET "
                    + " password='" + pass + "'"
                    + " WHERE userid='" + userid + "'");
            MessagesUtil.displayMessage("Save Operation Successful", "Successfully Changed User Password", 1, null);
        } catch (Exception e) {
            e.printStackTrace();
            MessagesUtil.displayMessage("Save Operation failed", "Failed to change user password", 3, e);
        }
    }

    //Read i.e. fetch all employees

    public static ArrayList readUsers() {
        ArrayList<User> readAll = new ArrayList<>();
        User user;
        try {
            resultSet = statement.executeQuery("SELECT userid,firstname,middlename,lastname,phoneno,datereg,doneby,username,password FROM " + USER_REGISTRATION + " ");
            while (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getString("userid"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setMiddleName(resultSet.getString("middlename"));
                user.setLastName(resultSet.getString("lastname"));
                user.setPhoneNo(resultSet.getString("phoneno"));
                user.setUsername(resultSet.getString("username"));
                readAll.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return readAll;
    }

    //Dynamically generate the customerid i.e system generated

    private static String generateUserId() {
        String userNo = null;
        try {
            resultSet = statement.executeQuery("SELECT count(*) FROM " + USER_REGISTRATION + " ");
            resultSet.next();
            int no = 1 + resultSet.getInt(1);
            userNo = "USR0" + no;
        } catch (SQLException e) {
            System.out.println("Could not generate userId dynamically");
        } finally {
            
        }
        return userNo;
    }
     public static HashMap FetchUsers(){
        HashMap<String,String> readAll = new HashMap<>();  
         try {
             readAll.put("All","ALL USERS");
             resultSet = statement.executeQuery("SELECT userid,firstname,lastname from "+USER_REGISTRATION+"  ");
            while(resultSet.next()){               
                readAll.put(resultSet.getString("userid"),resultSet.getString("firstname")+" "+resultSet.getString("lastname"));
               }
         } catch (SQLException ex) {
             MessagesUtil.displayMessage("Records Retrieval Failed", "Failed to fetch User records", 3, ex);
         }
          return readAll;
   }
}
