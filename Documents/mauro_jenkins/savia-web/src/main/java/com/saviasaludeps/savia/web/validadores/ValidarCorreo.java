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
 * @author raul-palacios
 */
@FacesValidator("validarCorreo")
public class ValidarCorreo implements Validator{
    
    private Pattern pattern;
    private Matcher matcher;
    
    public ValidarCorreo() {
        pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-xZa-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
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
        matcher = pattern.matcher((CharSequence)value);
        if(!value.equals("") && !matcher.matches()){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", label+": "+label+" es inválido"));
        }
    }
    
}
