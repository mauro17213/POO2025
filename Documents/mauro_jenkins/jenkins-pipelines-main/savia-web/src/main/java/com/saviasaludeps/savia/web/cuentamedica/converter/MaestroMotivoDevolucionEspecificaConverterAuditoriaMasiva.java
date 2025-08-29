/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.converter;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.web.cuentamedica.auditoriamasiva.bean.CmAuditoriaMasivaBean;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jperezn
 */
@FacesConverter("maestroMotivoDevolucionEspecificaConverterAuditoriaMasiva")
public class MaestroMotivoDevolucionEspecificaConverterAuditoriaMasiva implements Converter {

    @Override
    public Maestro getAsObject(FacesContext context, UIComponent component, String value) {
        Maestro obj = null;
        if(value != null && value.trim().length() > 0) {
            try {
                Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
                CmAuditoriaMasivaBean service = (CmAuditoriaMasivaBean) viewMap.get("cmAuditoriaMasivaBean");
                for(Maestro _obj : service.getListaMaeMotivoDevolucionEspecifico()){
                    if(Integer.parseInt(value) == _obj.getId()){
                        obj = _obj;
                        break;
                    }
                }
            } catch(NumberFormatException e) {  }
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
