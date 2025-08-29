/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuSolicitud;

/**
 *
 * @author Stiven Giraldo
 */
public interface AuAsignacionRemoto {
    
    /**
     * Asignar Solicitudes por Items
     * @param anexo3
     * @return
     * @throws Exception 
     */
    boolean asignar(AuAnexo3 anexo3) throws Exception;
        
}
