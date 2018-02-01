/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.solutions.pwp.integrator.controllers.utilities;

import com.solutions.entorno.utilities.SystemVariables;
import static com.solutions.entorno.utilities.SystemVariables.INSTITUTION_ETR;
import static com.solutions.entorno.utilities.SystemVariables.INSTITUTION_KRA;
import static com.solutions.entorno.utilities.SystemVariables.resultSet;
import static com.solutions.entorno.utilities.SystemVariables.statement;
import static com.solutions.pwp.integrator.controllers.utilities.InstitutionVariables.INSTITUTION;

/**
 *
 * @author shaddie
 */
public class SystemMandatorySettings {
    public static boolean isInstitutionAlphaSet(){
       boolean isSet = false;
        try {
            resultSet = statement.executeQuery("SELECT * FROM "+INSTITUTION+"");
            if(resultSet.next()){
                SystemVariables.INSTITUTION_NAME = FunctionGetInstitutionDetails.getInstitutionName();
                SystemVariables.INSTITUTION_ADDRESS = FunctionGetInstitutionDetails.getInstitutionAddress();
                SystemVariables.INSTITUTION_PHONE = FunctionGetInstitutionDetails.getInstitutionTelephone();
                SystemVariables.INSTITUTION_EMAIL = FunctionGetInstitutionDetails.getInstitutionEmail();
                SystemVariables.INSTITUTION_MOTTO = FunctionGetInstitutionDetails.getInstitutionMotto();
                SystemVariables.INSTITUTION_LOGO = FunctionGetInstitutionDetails.getInstitutionLogo();
                 SystemVariables.INSTITUTION_KRA = FunctionGetInstitutionDetails.getInstitutionPin();
                SystemVariables.INSTITUTION_ETR= FunctionGetInstitutionDetails.getInstitutionETR();
                if(INSTITUTION_KRA.equals(""))
                    INSTITUTION_KRA = null;
                 if(INSTITUTION_ETR.equals(""))
                    INSTITUTION_ETR = null;
                isSet=true;
            }
        } catch (Exception e) {
        }
        return isSet;
    }
    
    public static boolean isInstitutionLogoSet(){
       boolean isSet = false;
        try {
            resultSet = statement.executeQuery("SELECT institution_log FROM "+INSTITUTION+"");
            if(resultSet.next()){
                if(resultSet.getBinaryStream(1)!=null){
                isSet=true;
                }
            }
        } catch (Exception e) {
        }
        return isSet;
    }
    
    
}
