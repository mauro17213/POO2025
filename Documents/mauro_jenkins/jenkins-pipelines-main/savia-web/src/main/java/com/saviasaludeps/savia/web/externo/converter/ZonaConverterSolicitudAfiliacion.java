/**
 * Clase que convierte la selección de un objeto tipo Proyectos
 * a areapara el caso del componente SelectOneMenu de PrimeFaces.
 *
 * @author ripalacios
 * @since 06-11-2018
 */
package com.saviasaludeps.savia.web.externo.converter;

import com.saviasaludeps.savia.dominio.solicitud.GsZona;
import com.saviasaludeps.savia.web.externo.bean.SolicitudAfiliacionBean;

import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("zonaConverteSolicitudAfiliacion")
public class ZonaConverterSolicitudAfiliacion implements Converter {

    @Override
    public GsZona getAsObject(FacesContext context, UIComponent component, String value) {
        GsZona obj = new GsZona();
        if (value != null && value.trim().length() > 0) {
            try {
                Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
                SolicitudAfiliacionBean service = (SolicitudAfiliacionBean) viewMap.get("solicitudAfiliacionBean");
                
                for (Map.Entry<String, String> entry : service.getListaZonas().entrySet()) {
                    Object key =  entry.getKey();
                    Object valor =  entry.getValue();
                
                    if(valor.equals(value)){
                       Integer id =   valor.equals("U") ? 1: 2;
                       obj.setId(id);
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
            String val = value.equals("U") ? "1": "2";
            return String.valueOf(value);
        } else {
            return null;
        }
    }

}
