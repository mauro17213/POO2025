/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.auditoria;




/**
 *
 * @author jeperezn
 */
public interface SincronizacionCuentasMedicasRemoto {

    /**
     * permite crear una sincronizacion de cierre auditoria
     * @param idCierreAuditoria
     * @param esOperacionMasiva
     * @return
     * @throws Exception
     */
    public Integer crearSincronizacionCierreAuditoria(int idCierreAuditoria, boolean esOperacionMasiva) throws Exception;

    /**
     *permite crear una sincronizacion de devolucion
     * @param idDevolucion
     * @param esOperacionMasiva
     * @return
     * @throws Exception
     */
    public Integer crearSincronizacionDevolucionAuditoria(int idDevolucion, boolean esOperacionMasiva) throws Exception;

}
