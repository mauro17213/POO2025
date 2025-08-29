/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.administracion;

import com.saviasaludeps.savia.dominio.administracion.GnUbicacionBarrio;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public interface GnUbicacionBarrioRemoto {
    
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
    List<GnUbicacionBarrio> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    GnUbicacionBarrio consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(GnUbicacionBarrio obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(GnUbicacionBarrio obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    GnUbicacionBarrio eliminar(int id) throws Exception;
    
    /**
     * Consulta todos los registros
     * @return
     * @throws Exception 
     */
    List<GnUbicacionBarrio> consultarTodos() throws Exception;
    
    /**
     * Consulta los registros de una ubicación
     * @param idUbicacion
     * @return
     * @throws Exception 
     */
    List<GnUbicacionBarrio> consultarPorUbicacion(int idUbicacion) throws Exception;
}
