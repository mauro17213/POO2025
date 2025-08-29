/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMasivaModulo;

/**
 *
 * @author jeperezn
 */
public interface AuditoriaMasivaGenericoRemoto {
    
    /**
     * 
     * @param auditoria
     * @return
     * @throws Exception 
     */
    boolean validarLevantamientos(CmAuditoriaMasivaModulo auditoria) throws Exception;
   /**
    * 
    * @param auditoria
    * @return
    * @throws Exception 
    */
    boolean ejecutarLevantamientos(CmAuditoriaMasivaModulo auditoria) throws Exception;
    /**
     * Permite realizar la ejecucion de cierre de auditoria masivo
     * @param auditoria
     * @return
     * @throws Exception 
     */
    boolean ejecutarRegistroCierre(CmAuditoriaMasivaModulo auditoria) throws Exception;

    
    
}
