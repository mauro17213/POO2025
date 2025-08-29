/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.converter;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.web.administracion.bean.GnSedeBean;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author sgiraldov
 */
@FacesConverter("ubicacionConverterSede")
public class UbicacionConverterSede implements Converter {
    
     @Override
    public Ubicacion getAsObject(FacesContext context, UIComponent component, String value) {
        Ubicacion obj = null;
        if(value != null && value.trim().length() > 0) {
            try {
                Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
                GnSedeBean service = (GnSedeBean) viewMap.get("gnSedeBean");
                for(Ubicacion _obj : service.getListaUbicaciones()){
                    if(Integer.parseInt(value) == _obj.getId()){
                        obj = _obj;
                        break;
                    }
                }
            } catch(NumberFormatException e) {
            }
        }else {
            return null;
        }
        return obj;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null) {
            return String.valueOf(((Ubicacion) value).getId());
        }else {
            return null;
        }
    }
    
}
