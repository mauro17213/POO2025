/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.especial.converter;

import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.web.especial.bean.GestionAfiliadosPEBean;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Jaime Andres Olarte
 */
@FacesConverter("peProgramaConverterPrograma")
public class PeProgramaConverterPrograma implements Converter {
    
    @Override
    public PePrograma getAsObject(FacesContext context, UIComponent component, String value) {
        PePrograma obj = null;
        if (value != null && value.trim().length() > 0) {
            try {
                Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
                GestionAfiliadosPEBean service = (GestionAfiliadosPEBean) viewMap.get("gestionAfiliadosPEBean");
                for (PePrograma objPePrograma : service.getListaPePrograma()) {
                    if (Integer.parseInt(value) == objPePrograma.getId()) {
                        obj = objPePrograma;
                        break;
                    }
                }
            } catch (NumberFormatException e) {
//                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITYERROR, "Error de Conversión", "Ubicación Inválida."));
            }
        } else {
            return null;
        }
        return obj;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return String.valueOf(((PePrograma) value).getId());
        } else {
            return null;
        }
    }
}
