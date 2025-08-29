/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.NotificacionSapCmRadicadoCuentasMedicas;
import java.util.List;



/**
 *
 * @author jeperezn
 */
public interface NotificacionSAPCuentasMedicasRemoto {
    
    /**
     * Permite realizar una notificacion a sap sobre ciereres de autitoria
     *
     * @param listCmRadicados
     * @throws Exception
     */
    public void crearNotificacionSapCierreAuditoria(List<NotificacionSapCmRadicadoCuentasMedicas> listCmRadicados) throws Exception;

    /**
     * Permite realizar una notificacion a sap
     *
     * @param listCmRadicados
     * @param tipoNotificacion
     * @throws Exception
     */
    public void crearNotificacionSapCuentasMedicas(List<NotificacionSapCmRadicadoCuentasMedicas> listCmRadicados, int tipoNotificacion) throws Exception;
 
    
}
