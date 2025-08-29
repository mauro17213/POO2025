/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.gestionAtencion;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTiketeLlamado;
import java.util.List;

/**
 *
 * @author acuartas
 */
public interface GatTiketeLlamadoRemoto {
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
    List<GatTiketeLlamado> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    GatTiketeLlamado consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(GatTiketeLlamado obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(GatTiketeLlamado obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    GatTiketeLlamado eliminar(int id) throws Exception;
    
    /**
     * Consulta todos los llamados de un tiquete
     * @param idTiquete
     * @return
     * @throws Exception 
     */
    List<GatTiketeLlamado> consultarPorTiquete(int idTiquete) throws Exception;
    
    /**
     * Actualiza todos los llamados con estado llamado a cancelado
     * @param idTiquete
     * @throws Exception 
     */
    void actualizarRellamados(int idTiquete) throws Exception;
    
    /**
     * Lista todos los llamados activos del dia
     * @param idSede
     * @param maeTipoServicio
     * @return
     * @throws Exception 
     */
    List<GatTiketeLlamado> consultarLlamdosPorSede(int idSede, Integer maeTipoServicio) throws Exception;
    
    /**
     * Retorna un llamado vivo
     * @param idTiquete
     * @return
     * @throws Exception 
     */
    GatTiketeLlamado tieneLlamado(int idTiquete) throws Exception;
    
}
