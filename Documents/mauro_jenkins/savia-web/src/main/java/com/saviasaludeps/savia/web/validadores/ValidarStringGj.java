/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author BsGomez
 */
@FacesValidator("validarStringGj")
public class ValidarStringGj implements Validator {

    private static final String STRING_PATTERN = "^[a-zA-Z0-9 /ñÑ]+$";

    private Pattern pattern;
    private Matcher matcher;

    public ValidarStringGj() {
        pattern = Pattern.compile(STRING_PATTERN);
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String label;
        HtmlInputText htmlInputText = (HtmlInputText) component;
        if(htmlInputText.getLabel()==null || htmlInputText.getLabel().trim().equals("")){
            label = htmlInputText.getId();
        }else{
            label = htmlInputText.getLabel();
        }
        
        if(value.toString().trim().equals("")){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", label+": "+label+" es requerido"));
        }else{
            matcher = pattern.matcher(value.toString());
            if (!matcher.matches()) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", label+": "+label+" no es una cadena de caracteres válida"));
            }
        }
        
//        if (value == null || value.toString().trim().equals("")) {
//            FacesMessage msg = new FacesMessage("String validation failed.", "Invalid String format.");
//            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
//            throw new ValidatorException(msg);
//        }else{
//            matcher = pattern.matcher(value.toString());
//            if (!matcher.matches()) {
//                FacesMessage msg = new FacesMessage("String validation failed.", "Invalid String format.");
//                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
//                throw new ValidatorException(msg);
//            }
//        }
    }
}
