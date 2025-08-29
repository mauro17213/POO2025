package com.saviasaludeps.savia.web.cuentamedica.converter;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.web.cuentamedica.auditoria.bean.CmGrupoBean;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("cntPrestadorGrupoConverter")
public class CntPrestadorGrupoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        CntPrestador obj = null;
        if (value != null && value.trim().length() > 0) {
            try {
                Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
                CmGrupoBean service = (CmGrupoBean) viewMap.get("cmGrupoBean");
                for (CntPrestador objRec : service.getListaCntPrestadores()) {
                    if (Integer.parseInt(value) == objRec.getId()) {
                        obj = objRec;
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Conversión", "IPS Sede invalida."));
            }
        } else {
            return null;
        }
        return obj;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value != null) {
            return String.valueOf(((CntPrestador) value).getId());
        } else {
            return null;
        }
    }

}
