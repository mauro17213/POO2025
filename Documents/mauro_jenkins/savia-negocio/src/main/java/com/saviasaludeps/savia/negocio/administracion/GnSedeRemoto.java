/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.administracion;

import com.saviasaludeps.savia.dominio.administracion.GnSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author acuartas
 */
public interface GnSedeRemoto {
    /**
     * Consulta la cantidad de datos de parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    
    /**
     * Consulta la cantidad de datos de sedes activas de parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadListaActiva(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta la lista dado los parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<GnSede> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consulta la lista de sedes activas dado los parametros 
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<GnSede> consultarListaActiva(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    GnSede consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(GnSede obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(GnSede obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    GnSede eliminar(int id) throws Exception;
    
    
    /**
     * Valida si existe una sede ya creada con el nombre
     * @param nombre
     * @return
     * @throws Exception 
     */
    boolean validarExiste(String nombre) throws Exception;
    
    /**
     * Lista todas las sedes donde esta el funcionario
     * @param idFuncionario
     * @return
     * @throws Exception 
     */
    List<GnSede> listarSedesPorFuncionario(int idFuncionario) throws Exception;
    
    /**
     * Valida si existe ese nombre en otra sede
     * @param nombre
     * @param id
     * @return
     * @throws Exception 
     */
    boolean validarExisteConSede(String nombre, int id) throws Exception;
    
    /**
     * Lista las sedes que tienen turnero
     * @return
     * @throws Exception 
     */
    List<GnSede> listarSedesTurnero() throws Exception;
    
    /**
     * Lista las sedes que tienen turnero por usuario
     * @return
     * @throws Exception 
     */
    List<GnSede> listarSedesTurno() throws Exception;
    
    /**
     * Lista por ubicacion
     * @param idUbicacion
     * @return
     * @throws Exception 
     */
    List<GnSede> listarPorUbicacion(int idUbicacion) throws Exception;
}
