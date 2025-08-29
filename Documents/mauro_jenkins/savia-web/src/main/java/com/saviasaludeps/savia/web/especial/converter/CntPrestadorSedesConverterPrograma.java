/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.especial.converter;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.web.especial.bean.GestionProgramaBean;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Jaime Andres Olarte
 */
@FacesConverter("cntPrestadorSedesConverterPrograma")
public class CntPrestadorSedesConverterPrograma implements Converter {
    @Override
    public CntPrestadorSede getAsObject(FacesContext context, UIComponent component, String value) {
        CntPrestadorSede obj = null;
        if(value != null && value.trim().length() > 0) {
            try {
                Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
                GestionProgramaBean service = (GestionProgramaBean) viewMap.get("gestionProgramaBean");
                for(CntPrestadorSede sede : service.getListaIps()){
                    if(Integer.parseInt(value) == sede.getId()){
                        obj = sede;
                        break;
                    }
                }
            } catch(Exception e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Conversión", "CntPrestadorSede Inválida."));
            }
        }else {
            return null;
        }
        return obj;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null) {
            return String.valueOf(((CntPrestadorSede) value).getId());
        }else {
            return null;
        }
    }
}
