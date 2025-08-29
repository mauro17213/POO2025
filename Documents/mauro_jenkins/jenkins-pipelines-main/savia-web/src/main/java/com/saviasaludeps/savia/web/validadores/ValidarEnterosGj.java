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
@FacesValidator("validarEnterosGj")
public class ValidarEnterosGj implements Validator {

    private static final String NUMBER_PATTERN = "^[1-9][0-9]{0,99999999999}$";

    private Pattern pattern;
    private Matcher matcher;

    public ValidarEnterosGj() {
        pattern = Pattern.compile(NUMBER_PATTERN);
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String label;
        HtmlInputText htmlInputText=(HtmlInputText) component;
        if(htmlInputText.getLabel()==null || htmlInputText.getLabel().trim().equals("")){
            label=htmlInputText.getId();
        }else{
            label=htmlInputText.getLabel();
        }
        if(value.toString().trim().equals("")){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", label+": "+label+" es inválido"));
        }else{
            matcher = pattern.matcher(value.toString());
            if (!matcher.matches()) {
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", label+": "+label+" no es un número válido"));
            }
        }
    }
}