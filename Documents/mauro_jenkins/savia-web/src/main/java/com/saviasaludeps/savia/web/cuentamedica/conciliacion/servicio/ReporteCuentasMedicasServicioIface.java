/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.conciliacion.servicio;

import com.saviasaludeps.savia.web.cuentamedica.conciliacion.bean.CmEnviosGlosaBean;
import com.saviasaludeps.savia.web.cuentamedica.conciliacion.bean.ReporteCuentasMedicasBean;

/**
 *
 * @author admin
 */
public interface ReporteCuentasMedicasServicioIface {
    
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(ReporteCuentasMedicasBean bean);
    
    /**
     * Métodopara carhas inicial de variables
     * @param bean 
     */
    void cargaIncial(ReporteCuentasMedicasBean bean);
   
    
}
