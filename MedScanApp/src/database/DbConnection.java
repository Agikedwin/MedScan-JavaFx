/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




/**
 *
 * @author young
 */
public final class DbConnection {
    public  static Connection conn;
    private static Statement statement;
    public static DbConnection db;
    
   private String url= "jdbc:mysql://localhost:3306/";
    private    String dbName = "medscan_database";
    private    String driver = "com.mysql.jdbc.Driver";
     private   String userName = "root";
     private   String password = "";
        
     
   
    public  synchronized Connection getDbCon() {
        
       
        try {
            System.out.println("tried to connect  "+conn);
        if (conn == null ) {
             Class.forName(driver).newInstance();
            conn = (Connection)DriverManager.getConnection(url+dbName,userName,password);
            System.out.println("tried to connect , got new intance  "+conn);
        }else{
        System.out.println(" nconnection failed "+conn);
        
        }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
         return conn;
    }
    
     public Connection getConnection() {
        return this.conn;
    }
    /**
     *
     * @param query String The query to be executed
     * @return a ResultSet object containing the results or null if not available
     * @throws SQLException
     */
    public ResultSet query(String query) throws SQLException{
        statement = db.conn.createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;
    }
    /**
     * @desc Method to insert data to a table
     * @param insertQuery String The Insert query
     * @return boolean
     * @throws SQLException
     */
    private void records(){
        try {
           statement= db.conn.createStatement();
           System.out.println("tried to connect here ===  "+conn);
           System.out.println("fetching records .........");
            ResultSet res = statement.executeQuery("SELECT * FROM `tblbank`  ");
            while(res.next()){
                System.out.println("results........."+res.getString(2));
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
//     public boolean save() {
//         System.out.println("reached here   "+conn);
//        boolean success = false;
//        try {
//             System.out.println("reached here 2222");
//            String query = "INSERT INTO `tblbank`(`bankid`, `account_no`, `bank_name`, `status`, `last_updated`, `deleted`)"
//                    + " VALUES(?,?,?)";
//           
//             insertPrepStmt = conn.prepareStatement(query);
//                insertPrepStmt.executeUpdate();
//            
//
//            
//        } catch (SQLException e) {
//            success = false;
//            e.printStackTrace();
//        }
//        return success;
//    }
    public int insert(String insertQuery) throws SQLException {
        statement = db.conn.createStatement();
        int result = statement.executeUpdate(insertQuery);
        return result;
 
    }
    public static void main(String[] args) throws SQLException {
//       DbConnection connect=new DbConnection();
//       connect.getDbCon();
//       connect.getConnection();
//       connect.records();
    
       
     //connect.records();
      //connect.query("SELECT `id`, `name`, `gender`, `age` FROM `records`");
        
    }

    
    
}
