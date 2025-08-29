/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.especial.converter;

import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
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
@FacesConverter("diagnosticosConverterPrograma")
public class DiagnosticosConverterPrograma implements Converter {
    @Override
    public MaDiagnostico getAsObject(FacesContext context, UIComponent component, String value) {
        MaDiagnostico obj = null;
        if (value != null && value.trim().length() > 0) {
            try {
                Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
                GestionAfiliadosPEBean service = (GestionAfiliadosPEBean) viewMap.get("gestionAfiliadosPEBean");
                for (MaDiagnostico maDiagnostico : service.getListaDiagnostico()) {
                    if (Integer.parseInt(value) == maDiagnostico.getId()) {
                        obj = maDiagnostico;
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
            return String.valueOf(((MaDiagnostico) value).getId());
        } else {
            return null;
        }
    }
}
