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
public interface AuAnexo3SolicitudTutelaRemoto {
    
    /**
     * Inserta las tutelas de anexo3
     * @param auAnexo3
     * @return
     * @throws Exception 
     */
    AuAnexo3 insertar(AuAnexo3 auAnexo3) throws Exception;
    
}
