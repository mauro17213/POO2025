/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.informe.converter;

import com.saviasaludeps.savia.dominio.informe.InfInforme;
import com.saviasaludeps.savia.web.informe.bean.InformeGeneradoBean;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ripalacios
 */
@FacesConverter("fechaConverter")
public class FechaConverter implements Converter {

    @Override
    public String getAsObject(FacesContext context, UIComponent component, String value) {
        String obj = null;
        if (value != null && value.trim().length() > 0) {
            obj = value;
        } else {
            return null;
        }
        return obj;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return String.valueOf(((String) value));
        } else {
            return null;
        }
    }

}
