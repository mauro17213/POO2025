/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.solicitud.negocio;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;

/**
 *
 * @author Stiven Giraldo
 */
public interface AuAnexo4AutorizacionRemoto {
    
    /**
     * Se realiza la aprobacion automatica
     * @param anexo3
     * @return
     * @throws Exception 
     */
    AuAnexo3 aprobacionAutomatica(AuAnexo3 anexo3) throws Exception;
    
}
