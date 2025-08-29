/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.solicitud;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.solicitud.GsAfiliado;
import java.util.List;

/**
 *
 * @author jramirez
 */
public interface GsAfiliadoRemoto {
    //    /**
//     * Método para consultar todos los registros
//     * @return 
//     * @throws java.lang.Exception 
//     */
//    List<Persona> consultarTodos() throws Exception;

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
    List<GsAfiliado> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (Personas) cargado
     * @throws java.lang.Exception
     */
    GsAfiliado consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (Personas)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(GsAfiliado obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (Personas)
     * @throws java.lang.Exception
     */
    void actualizar(GsAfiliado obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (Personas) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    GsAfiliado eliminar(int id) throws Exception;

    /**
     * Metodo que permite consultar casos por una seria de filtros entre ellos
     * (tipo documento, documeto, idcaso)
     *
     * @param numeroDocumento
     * @param tipoDocumento
     * @return
     * @throws Exception
     */
    GsAfiliado consultarAfiliado(String numeroDocumento, String tipoDocumento) throws Exception;

    
    public List<GsAfiliado> consultarPorFiltros(ParamConsulta paramConsulta) throws Exception;
}
