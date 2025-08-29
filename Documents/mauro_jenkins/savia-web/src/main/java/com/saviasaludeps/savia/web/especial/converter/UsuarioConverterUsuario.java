/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.especial.converter;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.web.especial.bean.GestionProgramaBean;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Jaime Andres Olarte
 */
@FacesConverter("usuarioConverterUsuario")
public class UsuarioConverterUsuario implements Converter {
    @Override
    public Usuario getAsObject(FacesContext context, UIComponent component, String value) {
        Usuario obj = null;
        if (value != null && value.trim().length() > 0) {
            try {
                Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
                GestionProgramaBean service = (GestionProgramaBean) viewMap.get("gestionProgramaBean");
                for (Usuario objRec : service.getListaUsuario()) {
                    if (Integer.parseInt(value) == objRec.getId()) {
                        obj = objRec;
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
            return String.valueOf(((Usuario) value).getId());
        } else {
            return null;
        }
    }
}
