/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.judicial.converter;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.web.judicial.bean.GjAbogadoBean;

import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("usuarioConverterAbogado")
public class UsuarioConverterAbogado implements Converter {

    @Override
    public Usuario getAsObject(FacesContext context, UIComponent component, String value) {
        Usuario obj = new Usuario();
        if (value != null && value.trim().length() > 0) {
            try {
                Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
                GjAbogadoBean service = (GjAbogadoBean) viewMap.get("gjAbogadoBean");
                for (Usuario _obj : service.getListaUsuarios()) {
                    if (Integer.parseInt(value) == _obj.getId()) {
                        obj = _obj;
//                       service.getObjeto().setNombre(_obj.getNombre());
//                       service.getObjeto().setGnUsuarioId(_obj.getId());
                        //service.getServicioParaCrear().set setMaDiagnostico(new MaDiagnostico());
                        //service.getServicioParaCrear().setIdUsuarioResponsable(_obj.getId());
                        break;
                    }
                }
            } catch (NumberFormatException e) {
            }
        } else {
            return null;
        }
        return obj;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return String.valueOf(((Usuario) value).getId());
        } else {
            return null;
        }
    }
}
