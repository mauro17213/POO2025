/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public interface AuAnexo3ItemRemoto {

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
    List<AuAnexo3Item> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta la cantidad de datos de parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadListaCotizacion(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta la lista dado los parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<AuAnexo3Item> consultarListaCotizacion(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    AuAnexo3Item consultar(int id) throws Exception;

    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    AuAnexo3Item consultarParaCotizacion(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AuAnexo3Item obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AuAnexo3Item obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizarEstado(AuAnexo3Item obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    AuAnexo3Item eliminar(int id) throws Exception;

    /**
     * Lista los items posfechados
     *
     * @param idAnexo3
     * @param idTecnologia
     * @return
     * @throws Exception
     */
    List<AuAnexo3Item> listarItemsPosfechados(int idAnexo3, int idTecnologia) throws Exception;

    /**
     * Funcion que actualiza un item por temas de posfechado
     *
     * @param obj
     * @throws Exception
     */
    void actualizarPosfechado(AuAnexo3Item obj) throws Exception;
    
    /**
     * Funcion que actualiza un item por temas de posfechado
     *
     * @param obj
     * @throws Exception
     */
    void actualizarPosfechados(AuAnexo3Item obj) throws Exception;
    
    /**
     * Funcion que actualiza un item por temas de posfechado
     *
     * @param obj
     * @throws Exception
     */
    void actualizarFechaPosfechado(AuAnexo3Item obj) throws Exception;

    /**
     * Consulta por Id Anexo3, y solo posfechados principales
     *
     * @param idAnexo3
     * @return
     * @throws Exception
     */
    List<AuAnexo3Item> listaItemsByAnexo3Id(int idAnexo3) throws Exception;
    
    /**
     * Todos los items incluyendo posfechados
     * @param idAnexo3
     * @return
     * @throws Exception 
     */
    List<AuAnexo3Item> listaTodosItemsAnexo3Id(int idAnexo3) throws Exception;

    /**
     * Actualiza grupo
     *
     * @param obj
     * @throws Exception
     */
    void actualizarGrupo(AuAnexo3Item obj) throws Exception;

    /**
     * Lista por id grupo
     *
     * @param idGrupo
     * @return
     * @throws Exception
     */
    List<AuAnexo3Item> listarPorIdGrupo(int idGrupo) throws Exception;

    /**
     * consulta si existen items para un afiliado en determinado estados
     *
     * @param afiliado
     * @param tecnologias
     * @param estados
     * @param fechaOrdenMedica
     * @param idSolicitud
     * @return
     * @throws Exception
     */
    List<AuAnexo3Item> itemsByAfiliadoByFechaOrdenMedica(int afiliado, String tecnologias, String estados, Date fechaOrdenMedica, int idSolicitud) throws Exception;
}
