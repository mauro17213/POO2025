/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4CargaAnulada;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface AuAnexo4CargaAnuladaRemoto {

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
    List<AuAnexo4CargaAnulada> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
  
    /**
     * Método para consultar un anticipo por ID
     *
     * @param id
     * @return (Anticipos) cargado
     * @throws java.lang.Exception
     */
    AuAnexo4CargaAnulada consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo anticipo
     *
     * @param per (AntAnticipo)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AuAnexo4CargaAnulada per) throws Exception;
    
    /**
     * Método para actualizar la información de una anticipo
     *
     * @param per (AntAnticipo)
     * @throws java.lang.Exception
     */
    void actualizar(AuAnexo4CargaAnulada per) throws Exception;
    
    /**
     * Método para actualizar la información de una anticipo
     *
     * @param per (AntAnticipo)
     * @throws java.lang.Exception
     */
    void actualizarEstado(AuAnexo4CargaAnulada per) throws Exception;
    
    /**
     * Método para actualizar la información de una anticipo
     *
     * @param per (AntAnticipo)
     * @throws java.lang.Exception
     */
    void actualizarProceso(AuAnexo4CargaAnulada per) throws Exception;
    
    /**
     * Consulta una carga por nombre de archivo
     *
     * @param nombre
     * @return
     * @throws Exception
     */
    List<AuAnexo4CargaAnulada> consultarArchivoNombre(String nombre) throws Exception;
    
    /**
     * Consulta la siguiente carga a procesar
     * @param idEmbresa
     * @return
     * @throws Exception 
     */
    List<AuAnexo4CargaAnulada> consultarEstadoProceso(int idEmbresa) throws Exception;
    
    /**
     * Consulta la siguiente carga a procesar
     * @param estado
     * @return
     * @throws Exception 
     */
    AuAnexo4CargaAnulada consultarSiguienteCarga(int estado) throws Exception;
}
