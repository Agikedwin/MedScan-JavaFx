/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.solutions.pwp.integrator.controllers.utilities;

import com.solutions.entorno.utilities.FunctionFieldFetcher;
import com.solutions.entorno.utilities.ImageResizerClass;
import java.awt.Image;
import java.io.InputStream;

/**
 *
 * @author shaddie
 */
public class FunctionGetInstitutionDetails {
    public static  Image getInstitutionLogo(){
       Image logo = null;
        InputStream input = new FunctionFieldFetcher().getBlobs(InstitutionVariables.INSTITUTION, "institution_log");
        if(input!=null){
        logo = ImageResizerClass.getImage(input);
        logo = logo.getScaledInstance(300, 140, Image.SCALE_SMOOTH);
        }
        return logo;
    }
    public static InputStream getInstitutionLogoStream(){
       return new FunctionFieldFetcher().getBlobs(InstitutionVariables.INSTITUTION, "institution_log"); 
    }
    public static String getInstitutionName(){
        return (String)FunctionFieldFetcher.getField(InstitutionVariables.INSTITUTION, "name");
    }
    public static String getInstitutionAddress(){
        return (String)FunctionFieldFetcher.getField(InstitutionVariables.INSTITUTION, "postal_address");
    }
    public static String getInstitutionEmail(){
        return (String)FunctionFieldFetcher.getField(InstitutionVariables.INSTITUTION, "emails");
    }
    public static String getInstitutionTelephone(){
        return (String)FunctionFieldFetcher.getField(InstitutionVariables.INSTITUTION, "telnos");
    }
    public static String getInstitutionMotto(){
        return (String)FunctionFieldFetcher.getField(InstitutionVariables.INSTITUTION, "slogan");
    }
    
    public static String getInstitutionPin(){
        return (String)FunctionFieldFetcher.getField(InstitutionVariables.INSTITUTION, "pin");
    }
    
    public static String getInstitutionETR(){
        return (String)FunctionFieldFetcher.getField(InstitutionVariables.INSTITUTION, "etr");
    }
}
