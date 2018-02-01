/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.solutions.pwp.integrator.controllers.utilities;

import com.solutions.entorno.utilities.SystemVariables;
import static com.solutions.entorno.utilities.SystemVariables.statement;
import static com.solutions.pwp.integrator.controllers.utilities.InstitutionVariables.INSTITUTION;
import java.sql.SQLException;

/**
 *
 * @author shaddie
 */
public class InstitutionTableCreator {
    
    public InstitutionTableCreator(){
        institution_details();
    }
  
     private void institution_details(){
        try {
            statement.executeQuery("SELECT * FROM  "+INSTITUTION+"");
        } catch (SQLException e) {
            String query="CREATE TABLE  "+INSTITUTION+"("
                    + "name text NOT NULL ,"
                    + "town text NOT NULL," 
                    +"slogan VARCHAR(200) ,"                           
                    + "postal_address text,"
                    + "emails text ,"
                     + "telnos text,"
                     + "pin text,"
                    + "etr text ,"
                     + "regdate varchar(24),"
                    + "last_update varchar(24),"
                    + "userId varchar(20),"
                    + "expkey VARCHAR(200) NOT NULL,"
                    + "exptym VARCHAR(200) NOT NULL,"                   
                    + "status varchar(200) NOT NULL)";
            try {
                statement.executeUpdate(query);
                if(SystemVariables.CLASS_NAME.equalsIgnoreCase("org.postgresql.Driver")){
                statement.executeUpdate("ALTER TABLE "+INSTITUTION+" add institution_log bytea");
                }
                else if(SystemVariables.CLASS_NAME.equalsIgnoreCase("com.mysql.jdbc.Driver")){
                     statement.executeUpdate("ALTER TABLE "+INSTITUTION+" add institution_log longblob");
                }
            } catch (SQLException e1) {
               System.out.println("error creating "+INSTITUTION+" table"+e1);
            }
        }
    }

}
