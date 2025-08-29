/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Carga;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public interface AuAnexo3CargaRemoto {

    /**
     * Consulta la cantidad de datos de parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta la lista dado los parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<AuAnexo3Carga> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    AuAnexo3Carga consultar(int id) throws Exception;

    /**
     * Consulta una carga por nombre de archivo
     *
     * @param nombre
     * @return
     * @throws Exception
     */
    List<AuAnexo3Carga> consultarArchivoNombre(String nombre) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AuAnexo3Carga obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AuAnexo3Carga obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    AuAnexo3Carga eliminar(int id) throws Exception;
    
    /**
     * Consulta la siguiente carga a procesar
     * @param estado
     * @return
     * @throws Exception 
     */
    AuAnexo3Carga consultarSiguienteCarga(int estado) throws Exception;
    
    /**
     * Consulta la siguiente carga a procesar
     * @param idEmbresa
     * @return
     * @throws Exception 
     */
    List<AuAnexo3Carga> consultarEstadoProceso(int idEmbresa) throws Exception;
    
    /**
     * Actualiza el estado de la carga
     * @param carga
     * @throws Exception 
     */
    void actualizarEstado(AuAnexo3Carga carga) throws Exception;
}
