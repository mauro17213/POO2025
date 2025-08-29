/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.atencionusuario.converter;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.web.atencionusuario.bean.AusCasoBean;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author pavacca
 */
@FacesConverter("ipsPrescriptoraConverterAusCaso")
public class IpsPrescriptoraConverterAusCaso implements Converter{
    @Override
    public Maestro getAsObject(FacesContext context, UIComponent component, String value) {
        Maestro obj = null;
        if(value != null && value.trim().length() > 0) {
            try {
                Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
                AusCasoBean service = (AusCasoBean) viewMap.get("ausCasoBean");
                for(Maestro _obj : service.getListaTipoServicioIPS()){
                    if(Integer.parseInt(value) == _obj.getId()){
                        obj = _obj;
                        service.getServicioParaCrear().setObjetoIpsPrescriptora(new Maestro());
                        service.getServicioParaCrear().getObjetoIpsPrescriptora().setId(_obj.getId());
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
            return String.valueOf(((Maestro) value).getId());
        }else {
            return null;
        }
    }
}
