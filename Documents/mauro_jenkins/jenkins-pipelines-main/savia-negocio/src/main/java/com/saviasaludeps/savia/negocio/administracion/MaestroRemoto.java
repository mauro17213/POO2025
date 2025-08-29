/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.administracion;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface MaestroRemoto {

    /**
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<Maestro> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un maestro por ID
     *
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    Maestro consultar(int id) throws Exception;

    /**
     * Método para crear un nuev maestro
     *
     * @param obj (Maestros)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(Maestro obj) throws Exception;

    /**
     * Método para actualizar la información de un maestro
     *
     * @param obj (Maestros)
     * @throws java.lang.Exception
     */
    void actualizar(Maestro obj) throws Exception;

    /**
     * Eliminar un maestro
     *
     * @param id
     * @return (Maestro) objeto eliminado
     * @throws java.lang.Exception
     */
    Maestro eliminar(int id) throws Exception;

    /**
     * Consultar los maestros activos por tipo
     *
     * @param _tipo
     * @return
     * @throws Exception
     */
    List<Maestro> consultarPorTipo(String _tipo) throws Exception;

    /**
     * Consulta lista de maestros segun el tipo de su Hijo
     *
     * @param tipo
     * @return
     * @throws Exception
     */
    List<Maestro> consultarPorTipoHijo(String tipo) throws Exception;

    /**
     * Consultar los maestros activos por tipo
     *
     * @param _tipo
     * @return (HashMap<Integer, Maestro>) Hash con los maestros cargados
     * @throws Exception
     */
    HashMap<Integer, Maestro> consultarHashPorTipo(String _tipo) throws Exception;

    /**
     * Consultar todos maestros de un tipo en Hash
     *
     * @param tipo
     * @return
     * @throws Exception
     */
    HashMap<String, Maestro> consultarHashPorTipoValor(String tipo) throws Exception;

    /**
     * Consultar todos los Maestros
     *
     * @return (HashMap<Integer, Maestro>) Hash con los maestros cargados
     * @throws Exception
     */
    HashMap<Integer, Maestro> consultarHash() throws Exception;

    /**
     * Consultar todos los maetsros
     *
     * @return
     * @throws Exception
     */
    List<Maestro> consultarLista() throws Exception;

    /**
     * Consultar de maestro por valor y tipo
     *
     * @param valor
     * @param tipo
     * @return
     * @throws Exception
     */
    public Maestro consultarPorValorTipo(String valor, String tipo) throws Exception;

    /**
     * Consultar de maestro por nombre y tipo
     *
     * @param nombre
     * @param tipo
     * @return
     * @throws Exception
     */
    public Maestro consultarPorNombreTipo(String nombre, String tipo) throws Exception;

    /**
     * Consultar los maestros activos por tipo, ordenandolos por su id.
     *
     * @param tipo
     * @return
     * @throws Exception
     */
    List<Maestro> consultarPorTipoYOrdenPorId(String tipo) throws Exception;

    /**
     * Consultar lista de Acciones de Maestros por Tipo de maestro
     *
     * @param tipo
     * @return
     * @throws Exception
     */
    List<MaestroAccion> consultarAccionesPorTipo(String tipo) throws Exception;

    /**
     * Consultar accion por ID
     *
     * @param id
     * @return
     * @throws Exception
     */
    MaestroAccion consultarAcciones(int id) throws Exception;

    /**
     * Consultar lista de maestros a partir del ID de ina acción
     *
     * @param idAccion
     * @return
     * @throws Exception
     */
    List<Maestro> consultarMaestrosPorAccion(int idAccion) throws Exception;

    /**
     * Consultar todos los Maestros
     *
     * @param idPadre
     * @return (HashMap<Integer, Maestro>) Hash con los maestros cargados
     * @throws Exception
     */
    HashMap<Integer, Maestro> consultarHashPorPadre(int idPadre) throws Exception;

    /**
     * Consultar todos los maetros hijo de un tipo partiendo del id del padre
     *
     * @param tipo tipo de maestro hijo
     * @param idPadre
     * @return
     * @throws Exception
     */
    List<Maestro> consultarListaPorPadre(String tipo, int idPadre) throws Exception;

    /**
     * Consultar todos los maetros partiendo de dos padres
     *
     * @param tipo tipo de maestro hijo
     * @param idPadre1
     * @param idPadre2
     * @return
     * @throws java.lang.Exception
     */
    List<Maestro> consultarListaPorPadre(String tipo, int idPadre1, int idPadre2) throws Exception;

    /**
     * Consultar todos los maetros partiendo de tres padres
     *
     * @param tipo tipo de maestro hijo
     * @param idPadre1
     * @param idPadre2
     * @param idPadre3
     * @return
     * @throws java.lang.Exception
     */
    List<Maestro> consultarListaPorPadre(String tipo, int idPadre1, int idPadre2, int idPadre3) throws Exception;

    /**
     * Consultar todos los maetros partiendo del valor de un padre
     *
     * @param tipo
     * @param valorPadre
     * @return
     * @throws java.lang.Exception
     */
    List<Maestro> consultarListaPorPadre(String tipo, String valorPadre) throws Exception;

    /**
     * Consultar todos los maetros padre de un tipo partiendo del id del hijo
     *
     * @param tipo tipo de maestro hijo
     * @param idHijo
     * @return
     * @throws Exception
     */
    List<Maestro> consultarListaPorHijo(String tipo, int idHijo) throws Exception;

    /**
     * Consultar todos los maetros padre de un tipo partiendo del valor del hijo
     *
     * @param tipo tipo de maestro hijo
     * @param valorHijo
     * @return
     * @throws Exception
     */
    List<Maestro> consultarListaPorHijo(String tipo, String valorHijo) throws Exception;

    /**
     * Validar si existe relación entre padres e hijos
     *
     * @param idHijo
     * @param idPadre
     * @return
     * @throws Exception
     */
    boolean isRelacionPadreHijo(int idHijo, int idPadre) throws Exception;

    /**
     * Validar si existe relación entre padres e hijos
     *
     * @param idHijo
     * @param idPadre1
     * @param idPadre2
     * @return
     * @throws Exception
     */
    boolean isRelacionPadreHijo(int idHijo, int idPadre1, int idPadre2) throws Exception;

    /**
     * Validar si existe relación entre padres e hijos
     *
     * @param idHijo
     * @param idPadre1
     * @param idPadre2
     * @param idPadre3
     * @return
     * @throws Exception
     */
    boolean isRelacionPadreHijo(int idHijo, int idPadre1, int idPadre2, int idPadre3) throws Exception;

    /**
     * Listar los maestros por validacion
     *
     * @param idValidacion
     * @return
     * @throws Exception
     */
    List<Maestro> listarPorIdValidacion(int idValidacion) throws Exception;

    /**
     * Lista los maestros por tipos
     *
     * @param tipos
     * @return
     * @throws Exception
     */
    List<Maestro> listarPorTipos(List<String> tipos) throws Exception;

    /**
     * Lista los maestros recientes por tipos
     *
     * @param tipos
     * @param time
     * @return
     * @throws Exception
     */
    List<Maestro> listarPorTiposNuevos(List<String> tipos, Long time) throws Exception;

    /**
     * Lista por tipos sin acciones
     *
     * @param tipo
     * @return
     * @throws Exception
     */
    List<Maestro> listarPorTiposSinAcciones(String tipo) throws Exception;
}
