/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.converter;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegRadicadoNovedad;
import com.saviasaludeps.savia.web.aseguramiento.bean.AfiliadosBean;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ripalacios
 */
@FacesConverter("asegRadicadoNovedadAfiliadoConverter")
public class AsegRadicadoNovedadAfiliadoConverter implements Converter {

    @Override
    public AsegRadicadoNovedad getAsObject(FacesContext context, UIComponent component, String value) {
        AsegRadicadoNovedad obj = null;
        if(value != null && value.trim().length() > 0) {
            try {
                Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
                AfiliadosBean service = (AfiliadosBean) viewMap.get("afiliadosBean");
                for(AsegRadicadoNovedad _obj : service.getListaRadicados()){
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
            return String.valueOf(((AsegRadicadoNovedad) value).getId());
        }else {
            return null;
        }
    }
    
}
