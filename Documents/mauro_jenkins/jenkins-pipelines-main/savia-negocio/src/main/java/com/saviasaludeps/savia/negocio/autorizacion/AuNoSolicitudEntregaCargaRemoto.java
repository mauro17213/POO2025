/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudEntregaCarga;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface AuNoSolicitudEntregaCargaRemoto {

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
    List<AuNoSolicitudEntregaCarga> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
  
    /**
     * Método para consultar un anticipo por ID
     *
     * @param id
     * @return (Anticipos) cargado
     * @throws java.lang.Exception
     */
    AuNoSolicitudEntregaCarga consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo anticipo
     *
     * @param per (AntAnticipo)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AuNoSolicitudEntregaCarga per) throws Exception;
    
    /**
     * Método para actualizar la información de una anticipo
     *
     * @param per (AntAnticipo)
     * @throws java.lang.Exception
     */
    void actualizar(AuNoSolicitudEntregaCarga per) throws Exception;
    
    /**
     * Método para actualizar la información de una anticipo
     *
     * @param per (AntAnticipo)
     * @throws java.lang.Exception
     */
    void actualizarEstado(AuNoSolicitudEntregaCarga per) throws Exception;
    
    /**
     * Método para actualizar la información de una anticipo
     *
     * @param per (AntAnticipo)
     * @throws java.lang.Exception
     */
    void actualizarProceso(AuNoSolicitudEntregaCarga per) throws Exception;
    
    /**
     * Consulta una carga por nombre de archivo
     *
     * @param nombre
     * @return
     * @throws Exception
     */
    List<AuNoSolicitudEntregaCarga> consultarArchivoNombre(String nombre) throws Exception;
    
    /**
     * Consulta la siguiente carga a procesar
     * @param idEmbresa
     * @return
     * @throws Exception 
     */
    List<AuNoSolicitudEntregaCarga> consultarEstadoProceso(int idEmbresa) throws Exception;
    
    /**
     * Consulta la siguiente carga a procesar
     * @param estado
     * @return
     * @throws Exception 
     */
    AuNoSolicitudEntregaCarga consultarSiguienteCarga(int estado) throws Exception;
}
