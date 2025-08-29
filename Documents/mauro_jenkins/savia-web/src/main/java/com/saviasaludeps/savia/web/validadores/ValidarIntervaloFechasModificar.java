/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.validadores;

import java.util.Calendar;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


/**
 *
 * @author ripalacios
 */
@FacesValidator("validarIntervaloFechasModificar")
public class ValidarIntervaloFechasModificar implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }
        UIInput objetoFechaDesde = (UIInput) component.getAttributes().get("fechaDesde");
        if (objetoFechaDesde == null) {
            return;
        }
        Date fechaDesde = (Date) objetoFechaDesde.getValue();
        Date fechaHasta = (Date) value;
        if (fechaHasta.before(fechaDesde)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR: ", "La fecha inicial debe ser menor o igual a la fecha final"));
        }
    }

}
