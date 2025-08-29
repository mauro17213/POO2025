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
@FacesValidator("validarPrecioMinimo")
public class ValidarPrecioMinimo implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }
        //Leave the null handling of startDate to required="true"
        Object objetoPrecioRef = component.getAttributes().get("precioReferencia");
        Object objetoPrecioMax = component.getAttributes().get("precioMaximo");
        if (objetoPrecioRef == null) {
            return;
        }
        BigDecimal precioRef = (BigDecimal) objetoPrecioRef;
        BigDecimal precioMax = (BigDecimal) objetoPrecioMax;
        BigDecimal precioMin = (BigDecimal) value;
        if (precioMin.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "El precio mínimo debe ser mayor que cero"));
        }
        if (precioMin.compareTo(precioRef) > 0) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "El precio Mínimo debe ser menor o igual que el precio de referencia"));
        }
        if (objetoPrecioMax == null) {
            return;
        }
        if (precioMin.compareTo(precioMax) >= 0) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "El precio Mínimo debe ser menor que el precio máximo"));
        }
    }

}
