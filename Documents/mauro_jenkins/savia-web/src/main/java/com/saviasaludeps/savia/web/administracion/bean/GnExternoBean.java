/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author sgiraldov
 */
@ManagedBean
@ViewScoped
public class GnExternoBean extends Url {
    
    private String urlExterno;
    
    @PostConstruct
    public void postConstruct() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        
        Map<String, String> params =FacesContext.getCurrentInstance().
                   getExternalContext().getRequestParameterMap();
        setUrlExterno(params.get("url"));
        setModulo(new Modulo());
        getModulo().setNombre("Vinculo");
        crearLog("Listar", getUrlExterno());
    }

    public String getUrlExterno() {
        return urlExterno;
    }

    public void setUrlExterno(String urlExterno) {
        this.urlExterno = urlExterno;
    }
    
}
