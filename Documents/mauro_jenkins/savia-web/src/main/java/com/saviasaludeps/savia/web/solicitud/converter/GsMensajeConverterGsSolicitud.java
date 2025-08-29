/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.solicitud.converter;

import com.saviasaludeps.savia.dominio.solicitud.GsMensaje;
import com.saviasaludeps.savia.web.solicitud.bean.GsSolicitudBean;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jramirez
 */
@FacesConverter("gsMensajeConverterGsSolicitud")
public class GsMensajeConverterGsSolicitud implements Converter{
     @Override
    public GsMensaje getAsObject(FacesContext context, UIComponent component, String value) {
        GsMensaje obj = null;
        if(value != null && value.trim().length() > 0) {
            try {
                Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
                GsSolicitudBean service = (GsSolicitudBean) viewMap.get("gsSolicitudBean");
                for(GsMensaje mensaje : service.getListaMensajes()){
                    if(Integer.parseInt(value) == mensaje.getId()){
                        obj = mensaje;
                        break;
                    }
                }
            } catch(NumberFormatException e) {
//                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Conversión", "Ubicación Inválida."));
            }
        }else {
            return null;
        }
        return obj;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null) {
            return String.valueOf(((GsMensaje) value).getId());
        }else {
            return null;
        }
    }
}
