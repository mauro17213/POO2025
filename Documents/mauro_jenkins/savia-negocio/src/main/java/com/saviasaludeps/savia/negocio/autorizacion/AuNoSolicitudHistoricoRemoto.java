/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudHistorico;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface AuNoSolicitudHistoricoRemoto {

    /**
     * Método para consultar un AuNoSolicitudDiagnostico por ID
     *
     * @param id
     * @return (AuNoSolicitudDiagnostico) cargado
     * @throws java.lang.Exception
     */
    AuNoSolicitudHistorico consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo AuNoSolicitudDiagnostico
     *
     * @param per (AuNoSolicitudDiagnostico)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AuNoSolicitudHistorico per) throws Exception;

    /**
     * Método para actualizar la información de una AuNoSolicitudDiagnostico
     *
     * @param per (AuNoSolicitudDiagnostico)
     * @throws java.lang.Exception
     */
    void actualizar(AuNoSolicitudHistorico per) throws Exception;
    
    /**
     * Consultar lista de registros
     *
     * @param idAuNoSolicitudes
     * @return
     * @throws Exception
     */
    List<AuNoSolicitudHistorico> consultarHistoricoPorAuNosolicitudesId(int idAuNoSolicitudes) throws Exception;
}
