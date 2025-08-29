/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.informe.converter;

import com.saviasaludeps.savia.dominio.informe.InfGrupo;
import com.saviasaludeps.savia.web.informe.bean.InformeBean;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ripalacios
 */
@FacesConverter("grupoInformeConverter")
public class GrupoInformeConverter implements Converter {

    @Override
    public InfGrupo getAsObject(FacesContext context, UIComponent component, String value) {
        InfGrupo obj = null;
        if(value != null && value.trim().length() > 0) {
            try {
                Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
                InformeBean service = (InformeBean) viewMap.get("informeBean");
                for(InfGrupo _obj : service.getListaGrupos()){
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
            return String.valueOf(((InfGrupo) value).getId());
        }else {
            return null;
        }
    }
    
}
