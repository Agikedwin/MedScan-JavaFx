/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static com.solutions.entorno.utilities.SystemVariables.statement;
import static database.MedscanVariables.TBL_BANKS;
import java.sql.SQLException;

/**
 *
 * @author shaddie
 */
public class MedscanTablesCreator {

    public MedscanTablesCreator() {
       
    }

    

    //Material Registration table
    private void banks() {
        try {
            statement.executeQuery("SELECT * FROM " + TBL_BANKS + "");
        } catch (SQLException e) {
            String sql = "CREATE TABLE " + TBL_BANKS + "(bankid serial,"
                    + "account_no INT,"
                    + "bank_name varchar(24),"
                    + "status tinyint (1)"
                     + "deleted tinyint (1)"
                    + "regdate varchar(24),"
                    + "last_update varchar(15),"
                    + "userId varchar(15),"
                    + "PRIMARY KEY(fabricId))";
            try {
                statement.executeUpdate(sql);
            } catch (SQLException ex) {
                System.out.println("Could not create " + TBL_BANKS + " table");
            }
        }
    }

   
}