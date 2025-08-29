/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitud;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudEntregaDetalle;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface AuNoSolicitudEntregaDetalleRemoto {

    /**
     * Método para consultar un AuNoSolicitudDiagnostico por ID
     *
     * @param id
     * @return (AuNoSolicitudDiagnostico) cargado
     * @throws java.lang.Exception
     */
    AuNoSolicitudEntregaDetalle consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo AuNoSolicitudDiagnostico
     *
     * @param per (AuNoSolicitudDiagnostico)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AuNoSolicitudEntregaDetalle per) throws Exception;
    
    /**
     * Método para actualizar la información de una AuNoSolicitudItems
     *
     * @param listaNegocio
     * @throws java.lang.Exception
     */
    void insertarEntregaDetalles(List<AuNoSolicitud> listaNegocio) throws Exception;

    /**
     * Método para actualizar la información de una AuNoSolicitudDiagnostico
     *
     * @param per (AuNoSolicitudDiagnostico)
     * @throws java.lang.Exception
     */
    void actualizar(AuNoSolicitudEntregaDetalle per) throws Exception;
    
    /**
     * Método para actualizar la información de una AuNoSolicitudDiagnostico
     *
     * @param per (AuNoSolicitudDiagnostico)
     * @throws java.lang.Exception
     */
    void actualizarBorradoLogico(AuNoSolicitudEntregaDetalle per) throws Exception;
   
    /**
     * Consultar lista de registros
     *
     * @param idAuNoSolicitudItem
     * @return
     * @throws Exception
     */
    List<AuNoSolicitudEntregaDetalle> consultarEntregasPorAuNosolicitudesItemId(int idAuNoSolicitudItem) throws Exception;
    
    /**
     * consulta AuNoSolicitudEntregaDetalle por idAuNoSolicitudItem item y numero entrega
     *
     * @param idAuNoSolicitudItem
     * @param numeroEntrega
     * @return
     * @throws Exception
     */
    AuNoSolicitudEntregaDetalle consultarPorIdAuNoSolicitudItemPorNumeroEntrega(int idAuNoSolicitudItem, Integer numeroEntrega) throws Exception;

}
