/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.solicitud;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.solicitud.GsZona;
import com.saviasaludeps.savia.dominio.solicitud.GsZonaUsuario;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author jramirez
 */
public interface GsZonaRemoto {

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
    List<GsZona> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (Sedes) cargado
     * @throws java.lang.Exception
     */
    GsZona consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (Sedes)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(GsZona obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (Sedes)
     * @throws java.lang.Exception
     */
    void actualizar(GsZona obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (Sedes) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    GsZona eliminar(int id) throws Exception;

    /**
     * Consultar todas las Sedes de una empresa y retornarlas en List
     *
     * @return
     * @throws Exception
     */
    List<GsZona> consultarTodas() throws Exception;

    /**
     * Consultar todas las Sedes de una empresa y retornarlas en Hash
     *
     * @return
     * @throws Exception
     */
    HashMap<Integer, GsZona> consultarTodasHash() throws Exception;

    /**
     * Consulta Usuarios por zonas
     *
     * @param zonaId
     * @return
     * @throws Exception
     */
    List<GsZonaUsuario> consultarUsuarios(Integer zonaId) throws Exception;

    /**
     * Consultar ZonaUsuario
     *
     * @param id
     * @return
     * @throws Exception
     */
    GsZonaUsuario consultarUsuario(int id) throws Exception;

    /**
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertarUsuario(GsZonaUsuario obj) throws Exception;

    /**
     *
     * @param obj
     * @throws Exception
     */
    void actualizarUsuario(GsZonaUsuario obj) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    GsZonaUsuario eliminarUsuario(int id) throws Exception;
}
