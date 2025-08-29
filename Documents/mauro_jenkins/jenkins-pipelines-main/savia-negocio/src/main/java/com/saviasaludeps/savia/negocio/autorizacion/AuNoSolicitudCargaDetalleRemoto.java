/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitud;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudCargaDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.solicitud.dominio.ValidaRespuestaDTO;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface AuNoSolicitudCargaDetalleRemoto {

    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<AuNoSolicitudCargaDetalle> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    
    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadDetalleLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<AuNoSolicitudCargaDetalle> consultarDetalleLista(ParamConsulta paramConsulta) throws Exception;
  
    /**
     * Método para consultar un anticipo por ID
     *
     * @param id
     * @return (Anticipos) cargado
     * @throws java.lang.Exception
     */
    AuNoSolicitudCargaDetalle consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo anticipo
     *
     * @param per (AntAnticipo)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AuNoSolicitudCargaDetalle per) throws Exception;   
    
     /**
     * Método para consultar un anticipo por ID
     *
     * @param id
     * @return (Anticipos) cargado
     * @throws java.lang.Exception
     */
    List<AuNoSolicitudCargaDetalle> consultarDetallesByuNoSolicitudCargasId(int id) throws Exception;
    
    
    /**
     * Método para consultar un anticipo por ID
     *
     * @param listaQuery
     * @return (Anticipos) cargado
     * @throws java.sql.SQLException
     * @throws java.lang.Exception
     */
    List<ValidaRespuestaDTO> ejecutarSelFuncionesSinAutorizacion(List<String> listaQuery) throws SQLException, Exception;
    
    /**
     * Método para consultar un anticipo por ID
     *
     * @param afiliado
     * @param tecnologias
     * @param estados
     * @param fechaOrdenMedica
     * @return (Anticipos) cargado
     * @throws java.sql.SQLException
     * @throws java.lang.Exception
     */
    List<String> itemsByAfiliadoByFechaOrdenMedica(int afiliado, String tecnologias, String estados, Date fechaOrdenMedica) throws SQLException, Exception;
    
    /**
     * Método para consultar un anticipo por ID
     *
     * @param listaNegocio
     * @return (Anticipos) cargado
     * @throws java.sql.SQLException
     * @throws java.lang.Exception
     */
    boolean llenarSinAutorizacionesIdCargaDetalle(List<AuNoSolicitud> listaNegocio) throws SQLException, Exception;
    
    /**
     * Método para consultar un anticipo por ID
     *
     * @param listaNegocio
     * @return (Anticipos) cargado
     * @throws java.sql.SQLException
     * @throws java.lang.Exception
     */
    boolean insertarSinAutorizacionesCargaDetalles(List<AuNoSolicitudCargaDetalle> listaNegocio) throws SQLException, Exception;
}
