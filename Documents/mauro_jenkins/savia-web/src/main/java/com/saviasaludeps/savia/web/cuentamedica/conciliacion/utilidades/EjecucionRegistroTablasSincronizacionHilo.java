/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.conciliacion.utilidades;

import com.saviasaludeps.savia.web.cuentamedica.conciliacion.bean.CargaMasivaConciliacionBean;
import com.saviasaludeps.savia.web.cuentamedica.conciliacion.servicio.CmEnviosGlosasServicioImpl;

/**
 *
 * @author admin
 */
public class EjecucionRegistroTablasSincronizacionHilo extends Thread {
    
    CargaMasivaConciliacionBean bean;

    public CargaMasivaConciliacionBean getBean() {
        return bean;
    }

    public void setBean(CargaMasivaConciliacionBean bean) {
        this.bean = bean;
    }
    
      
    public EjecucionRegistroTablasSincronizacionHilo(CargaMasivaConciliacionBean bean) {
       this.bean = bean;
    }
    
    @Override
    public void run() {
        try {   
            CmEnviosGlosasServicioImpl cmEnviosGlosasServicioImpl = new CmEnviosGlosasServicioImpl();
            int idConciliacionMasiva =  bean.getObjeto().getIdConciliacionMasiva();
            cmEnviosGlosasServicioImpl.setConexion(bean.getConexion());
            cmEnviosGlosasServicioImpl.crearRadicadoXConciliaciones(idConciliacionMasiva);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
  
      
}
