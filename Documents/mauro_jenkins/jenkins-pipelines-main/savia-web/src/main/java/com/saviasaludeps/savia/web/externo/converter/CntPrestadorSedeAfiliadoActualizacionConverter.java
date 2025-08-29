/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.externo.converter;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.web.externo.bean.AfiliadoActualizacionBean;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ripalacios
 */
@FacesConverter("cntPrestadorSedeAfiliadoActualizacionConverter")
public class CntPrestadorSedeAfiliadoActualizacionConverter implements Converter {

    @Override
    public CntPrestadorSede getAsObject(FacesContext context, UIComponent component, String value) {
        CntPrestadorSede obj = null;
        if(value != null && value.trim().length() > 0) {
            try {
                Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
                AfiliadoActualizacionBean service = (AfiliadoActualizacionBean) viewMap.get("afiliadoActualizacionBean");
                for(CntPrestadorSede _obj : service.getListaCntPrestadorSedes()){
                    if(Integer.parseInt(value) == _obj.getId()){
                        obj = _obj;
                        break;
                    }
                }
            } catch(Exception e) {
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
            return String.valueOf(((CntPrestadorSede) value).getId());
        }else {
            return null;
        }
    }
    
}
