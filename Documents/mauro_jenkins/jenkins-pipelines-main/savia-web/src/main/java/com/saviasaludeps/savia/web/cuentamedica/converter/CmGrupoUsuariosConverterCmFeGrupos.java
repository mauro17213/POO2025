/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.converter;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupoUsuario;
import com.saviasaludeps.savia.web.cuentamedica.rips.carga.bean.CmFeRipsCargaBean;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author jperezn
 */
@FacesConverter("cmGrupoUsuariosConverterCmFeGrupos")
public class CmGrupoUsuariosConverterCmFeGrupos implements Converter {

    @Override
    public CmGrupoUsuario getAsObject(FacesContext context, UIComponent component, String value) {
        CmGrupoUsuario obj = null;
        if(value != null && value.trim().length() > 0) {
            try {
                Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
                CmFeRipsCargaBean service = (CmFeRipsCargaBean) viewMap.get("cmFeRipsCargaBean");
                for(CmGrupoUsuario _obj : service.getRadicadores()){
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
            return String.valueOf(((CmGrupoUsuario) value).getId());
        }else {
            return null;
        }
    }
    
}
