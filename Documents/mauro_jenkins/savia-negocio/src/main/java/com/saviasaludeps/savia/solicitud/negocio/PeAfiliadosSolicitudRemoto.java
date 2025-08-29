/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.solicitud.negocio;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;

/**
 *
 * @author idbohorquez
 */
public interface PeAfiliadosSolicitudRemoto {
    
    /**
     * Metodo encargado de realizar marcación automatica de afilaidos en programas
     * 
     * @author Isaac Bohorquez
     * @param auAnexo3
     * @throws java.lang.Exception
     * @fechaCreación 24/06/2022
     */
    void solicitudMarcacionAutomatica(AuAnexo3 auAnexo3) throws Exception;
    
}
