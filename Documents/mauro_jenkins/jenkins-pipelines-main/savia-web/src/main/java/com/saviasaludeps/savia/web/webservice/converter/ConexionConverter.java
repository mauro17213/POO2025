/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.webservice.converter;

import com.saviasaludeps.savia.dominio.webservice.WsServicio;
import com.saviasaludeps.savia.web.webservice.bean.WsConexionBean;
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
@FacesConverter("conexionConverter")
public class ConexionConverter implements Converter {

    @Override
    public WsServicio getAsObject(FacesContext context, UIComponent component, String value) {
        WsServicio obj = null;
        if (value != null && value.trim().length() > 0) {
            try {
                Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
                WsConexionBean service = (WsConexionBean) viewMap.get("wsConexionBean");
                for (WsServicio _obj : service.getListaWsServicios()) {
                    if (Integer.parseInt(value) == _obj.getId()) {
                        obj = _obj;
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Conversión", "Conexion Inválida."));
            }
        } else {
            return null;
        }
        return obj;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return String.valueOf(((WsServicio) value).getId());
        } else {
            return null;
        }
    }
}
