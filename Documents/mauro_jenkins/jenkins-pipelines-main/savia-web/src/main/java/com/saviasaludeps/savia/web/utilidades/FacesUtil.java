/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.utilidades;

import javax.faces.event.ActionEvent;

/**
 *
 * @author raul-palacios
 */
public class FacesUtil {
    public static char getActionAttributeChar(ActionEvent event, String name) {
        try{
            return ((String) event.getComponent().getAttributes().get(name)).charAt(0);
        }catch(Exception e){
            return 'L';
        }
    }
    public static String getActionAttributeStr(ActionEvent event, String name) {
        try{
            return (String) event.getComponent().getAttributes().get(name);
        }catch(Exception e){
            return "";
        }
    }
    public static int getActionAttributeInt(ActionEvent event, String name) {
        try{
            return (int) event.getComponent().getAttributes().get(name);
        }catch(Exception e){
            return 0;
        }
    }
}
