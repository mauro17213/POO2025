/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.generico;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucCarga;

/**
 *
 * @author sgiraldov
 */
public interface CargaMasivaAuditoriaConcurrenteRemoto {
    
    /**
     * Metodo para lanzar la carga masiva
     * @param carga
     * @throws Exception 
     */
    void cargaMasivaHospitalizacion(AucCarga carga) throws Exception;
    
}
