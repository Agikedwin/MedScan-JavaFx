/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.solutions.pwp.integrator.controllers.utilities;

import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author shaddie
 */
public class SystemImageIcon {
    
    public SystemImageIcon(){
        
    }
    public  Image getImage(){
       return Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/solutions/pwp/integrator/controllers/main/startup/currency_black_dollar.png"));
    }
    public static String imagePath(){
        return "/com/solutions/pwp/integrator/controllers/main/startup/currency_black_dollar.png";
    }
}
