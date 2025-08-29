/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.crue.converter;

import com.saviasaludeps.savia.web.crue.bean.DTO.RefAnexo9TriageDTO;
import com.saviasaludeps.savia.web.crue.bean.ReferenciaContraRefBean;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Jaime Andres Olarte
 */
@FacesConverter("triageConverterReferencia")
public class TriageConverterReferencia implements Converter {
    @Override
    public RefAnexo9TriageDTO getAsObject(FacesContext context, UIComponent component, String value) {
        RefAnexo9TriageDTO obj = null;
        if (value != null && value.trim().length() > 0) {
            try {
                Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
                ReferenciaContraRefBean service = (ReferenciaContraRefBean) viewMap.get("referenciaContraRefBean");

               for(RefAnexo9TriageDTO triageDTO : service.getListaTriage()){
                    if(Integer.parseInt(value) == triageDTO.getId()){
                        obj = triageDTO;
                        break;
                    }
                }
            } catch (NumberFormatException e) {
//                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Conversión", "Ubicación Inválida."));
            }
        } else {
            return null;
        }
        return obj;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return String.valueOf(((RefAnexo9TriageDTO) value).getId());
        } else {
            return null;
        }
    }
}
