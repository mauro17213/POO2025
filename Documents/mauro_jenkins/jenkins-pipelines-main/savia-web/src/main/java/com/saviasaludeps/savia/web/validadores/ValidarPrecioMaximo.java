/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.validadores;

import java.math.BigDecimal;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author ntaborda
 */
@FacesValidator("validarPrecioMaximo")
public class ValidarPrecioMaximo implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }
        //Leave the null handling of startDate to required="true"
        Object objetoPrecioRef = component.getAttributes().get("precioReferencia");
        Object objetoPrecioMin = component.getAttributes().get("precioMinimo");
        if (objetoPrecioMin == null) {
            return;
        }
        BigDecimal precioRef = (BigDecimal) objetoPrecioRef;
        BigDecimal precioMin = (BigDecimal) objetoPrecioMin;
        BigDecimal precioMax = (BigDecimal) value;
        
        if (precioMax.compareTo(precioMin) > 0) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "El precio Máximo debe ser mayor que el precio mínimo"));
        }
        if (objetoPrecioRef == null) {
            return;
        }
        if (precioMax.compareTo(precioRef) >= 0) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "El precio Máximo debe ser mayor que el precio referencia"));
        }
    }

}
