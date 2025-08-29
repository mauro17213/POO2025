/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.validadores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.primefaces.component.selectonemenu.SelectOneMenu;

/**
 *
 * @author raul-palacios
 */
@FacesValidator("validarSeleccionMenu")
public class ValidarSeleccionMenu implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String label;
        SelectOneMenu htmlSelectOneMenu=(SelectOneMenu) component;
        if(htmlSelectOneMenu.getLabel()==null || htmlSelectOneMenu.getLabel().trim().equals("")){
            label=htmlSelectOneMenu.getId();
        }else{
            label=htmlSelectOneMenu.getLabel();
        }
//        if (value == null) {
        if(value==null || value.toString().trim().equals("")){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", label+": "+label+" es requerido"));
//            FacesMessage msg = new FacesMessage("The Payer field is required.", "The Payer field is required..");
//            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
//            throw new ValidatorException(msg);
        } 
    }
}