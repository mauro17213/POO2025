/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudEntrega;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface AuNoSolicitudEntregaRemoto {

    /**
     * Método para consultar un AuNoSolicitudDiagnostico por ID
     *
     * @param id
     * @return (AuNoSolicitudDiagnostico) cargado
     * @throws java.lang.Exception
     */
    AuNoSolicitudEntrega consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo AuNoSolicitudDiagnostico
     *
     * @param per (AuNoSolicitudDiagnostico)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AuNoSolicitudEntrega per) throws Exception;

    /**
     * Método para actualizar la información de una AuNoSolicitudDiagnostico
     *
     * @param per (AuNoSolicitudDiagnostico)
     * @throws java.lang.Exception
     */
    void actualizarAnular(AuNoSolicitudEntrega per) throws Exception;
    
    /**
     * Consultar lista de registros
     *
     * @param idAuNoSolicitudes
     * @return
     * @throws Exception
     */
    List<AuNoSolicitudEntrega> consultarEntregasPorAuNosolicitudesId(int idAuNoSolicitudes) throws Exception;
    
    /**
     * Consultar lista de registros
     *
     * @param idAuNoSolicitudItem
     * @return
     * @throws Exception
     */
    List<AuNoSolicitudEntrega> consultarEntregasPorAuNosolicitudesItemId(int idAuNoSolicitudItem) throws Exception;
    
    /**
     * Consultar lista de registros
     *
     * @param idAuNoSolicitudItem
     * @param numeroEntregas
     * @return
     * @throws Exception
     */
    List<AuNoSolicitudEntrega> consultarEntregasPorAuNosolicitudesItemIdWithNumeroEntregas(int idAuNoSolicitudItem, int numeroEntregas) throws Exception;
    
    /**
     * Consultar lista de registros
     *
     * @param idAuNoSolicitudItem
     * @return
     * @throws Exception
     */
    List<AuNoSolicitudEntrega> consultarEntregasPorAuNosolicitudesItemIdWithNoAnuladas(int idAuNoSolicitudItem) throws Exception;
    
    /**
     * Consultar lista de registros
     *
     * @param idAuNoSolicitudItem
     * @param numeroEntrega
     * @return
     * @throws Exception
     */
    List<AuNoSolicitudEntrega> consultarEntregasPorAuNosolicitudesItemIdWithNoAnuladasYnumeroEntregas(int idAuNoSolicitudItem, int numeroEntrega) throws Exception;
    
    /**
     * 
     * @param idAuNoSolicitudItem
     * @return
     * @throws Exception 
     */
    int consultarCantidadEstadoAnuladaloporAuNoSolicitudItem(int idAuNoSolicitudItem) throws Exception;
}
