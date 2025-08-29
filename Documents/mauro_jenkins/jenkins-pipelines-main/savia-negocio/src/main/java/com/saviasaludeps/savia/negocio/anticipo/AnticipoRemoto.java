/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.anticipo;

import com.saviasaludeps.savia.dominio.anticipo.AntAnticipo;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface AnticipoRemoto {

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
    List<AntAnticipo> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadContizacionesLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<AntAnticipo> consultarContizacionesLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un anticipo por ID
     *
     * @param id
     * @return (Anticipos) cargado
     * @throws java.lang.Exception
     */
    AntAnticipo consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo anticipo
     *
     * @param per (AntAnticipo)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AntAnticipo per) throws Exception;

    /**
     * Método para actualizar la información de una anticipo
     *
     * @param per (AntAnticipo)
     * @throws java.lang.Exception
     */
    void actualizar(AntAnticipo per) throws Exception;
    
    /**
     * Método para actualizar la información de una anticipo
     *
     * @param per (AntAnticipo)
     * @throws java.lang.Exception
     */
    void actualizarConCotizacion(AntAnticipo per) throws Exception;
    
    /**
     * Método para actualizar la información de una anticipo
     *
     * @param per (AntAnticipo)
     * @throws java.lang.Exception
     */
    void actualizarEstadoProceso(AntAnticipo per) throws Exception;

     /**
     * Método para actualizar la información del afiliado
     *
     * @param per (AntAnticipo)
     * @throws java.lang.Exception
     */
    void actualizarAfiliado(AntAnticipo per) throws Exception;
    
     /**
     * Método para actualizar la información del prestador
     *
     * @param per (AntAnticipo)
     * @throws java.lang.Exception
     */
    void actualizarPrestador(AntAnticipo per) throws Exception;
    
     /**
     * Método para actualizar la información del prestador
     *
     * @param per (AntAnticipo)
     * @throws java.lang.Exception
     */
    void actualizarPago(AntAnticipo per) throws Exception;
    
    /**
     * Método para actualizar la información de una anticipo
     *
     * @param per (AntAnticipo)
     * @throws java.lang.Exception
     */
    void actualizarValorDisponible(AntAnticipo per) throws Exception;
    
    /**
     * Método para consultar un anticipo por ID
     *
     * @param id
     * @param idTecnologia
     * @return (Anticipos) cargado
     * @throws java.lang.Exception
     */
    AntAnticipo consultarAnticipoAfiliadoYTecnologia(int id, int  idTecnologia) throws Exception;
    
    /**
     * Método para consultar un anticipo por ID
     *
     * @param idTecnologia
     * @return (Anticipos) cargado
     * @throws java.lang.Exception
     */
    AntAnticipo consultarAnticipoByTecnologia(int idTecnologia) throws Exception;
    
     /**
     * Método para consultar un anticipo por ID
     *
     * @param id
     * @param idTecnologia
     * @return (Anticipos) cargado
     * @throws java.lang.Exception
     */
    List<AntAnticipo> consultarAnticipoAfiliadoYTecnologiaList(int id, int  idTecnologia) throws Exception;
    
    /**
     * Método para consultar un anticipo por ID
     *
     * @param idTecnologia
     * @return (Anticipos) cargado
     * @throws java.lang.Exception
     */
    List<AntAnticipo> consultarAnticipoByTecnologiaList(int idTecnologia) throws Exception;
    
}
