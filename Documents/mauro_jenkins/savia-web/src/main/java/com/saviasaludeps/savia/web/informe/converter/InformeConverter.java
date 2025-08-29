/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.informe.converter;
import com.saviasaludeps.savia.dominio.informe.InfInforme;
import com.saviasaludeps.savia.web.informe.bean.InformeGeneradoBean;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ripalacios
 */
@FacesConverter("informeConverter")
public class InformeConverter implements Converter {

    @Override
    public InfInforme getAsObject(FacesContext context, UIComponent component, String value) {
        InfInforme obj = null;
        if(value != null && value.trim().length() > 0) {
            try {
                Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
                InformeGeneradoBean service = (InformeGeneradoBean) viewMap.get("informeGeneradoBean");
                for(InfInforme _obj : service.getListaInformes()){
                    if(Integer.parseInt(value) == _obj.getId()){
                        obj = _obj;
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
            return String.valueOf(((InfInforme) value).getId());
        }else {
            return null;
        }
    }
    
}
