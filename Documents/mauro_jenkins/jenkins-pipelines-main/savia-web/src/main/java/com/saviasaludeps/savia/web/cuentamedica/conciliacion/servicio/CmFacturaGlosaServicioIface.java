/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.conciliacion.servicio;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.ReporteRespuestaGlosa;
import com.saviasaludeps.savia.web.cuentamedica.conciliacion.bean.CmFacturaGlosaBean;



/**
 *
 * @author raul-palacios
 */
public interface CmFacturaGlosaServicioIface {
    
    /**
     * 
     * @param bean 
     */
    void Accion(CmFacturaGlosaBean bean);
    
    /**
     * 
     * @param bean 
     */
    void cargaInial(CmFacturaGlosaBean bean);
    
    /**
     * Saca el reporte para respuesta glosa
     * @param id
     * @return 
     */
    void reporteRespuestaGlosa(CmFacturaGlosaBean id);
}
