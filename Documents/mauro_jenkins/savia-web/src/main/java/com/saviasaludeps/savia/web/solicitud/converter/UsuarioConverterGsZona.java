/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.solicitud.converter;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.web.solicitud.bean.GsZonaBean;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jramirez
 */
@FacesConverter("usuarioConverterGsZona")
public class UsuarioConverterGsZona implements Converter{
    @Override
    public Usuario getAsObject(FacesContext context, UIComponent component, String value) {
        Usuario obj = null;
        if(value != null && value.trim().length() > 0) {
            try {
                Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
                GsZonaBean service = (GsZonaBean) viewMap.get("gsZonaBean");
                for(Usuario usu : service.getUsuarios()){
                    if(Integer.parseInt(value) == usu.getId()){
                        obj = usu;
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
            return String.valueOf(((Usuario) value).getId());
        }else {
            return null;
        }
    }
}
