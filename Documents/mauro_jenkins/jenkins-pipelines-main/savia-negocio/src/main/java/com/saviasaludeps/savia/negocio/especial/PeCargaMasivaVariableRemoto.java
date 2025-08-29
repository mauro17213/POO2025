/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.especial;

import com.saviasaludeps.savia.dominio.especial.PeCargaVariable;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author Jhohan Lopez
 */
public interface PeCargaMasivaVariableRemoto {
    /**
     * Consulta un registro por id
     * @param id
     * @return
     * @throws Exception
     */
    PeCargaVariable consultar(int id) throws Exception;

    /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     * @param paramConsulta Parametros de la consulta de registros
     * @return Lista de registros consultados
     * @throws Exception
     */
    List<PeCargaVariable> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Inserta un registro PeCargaVariables
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(PeCargaVariable obj) throws Exception;

    /**
     * Actualiza un registro de tipo PeCargaVariable
     * @param obj
     * @throws Exception
     */
    void actualizar(PeCargaVariable obj) throws Exception;
    /**
     * Actualiza el detalle de un registro PeCargaVariables
     * @param obj
     * @throws Exception
     */
    void actualizarDetalle(PeCargaVariable obj) throws Exception;
    /**
     * Consulta una carga masiva para ser procesada
     * @return PeCargaVariable
     * @throws Exception
     */
    PeCargaVariable consultarCargasSiguientes() throws Exception;
    /**
     * Verifica si hay una carga por prestador con estado 0 (en cola) o 1 (en proceso).
     * @param idPrestador
     * @return boolean
     * @throws Exception
     */
    boolean existeCargaEnColaOProcesoPorIdPrestador(int idPrestador) throws Exception;

    /**
     * Verifica si hay una carga por prestador con estado 0 (en cola) o 1 (en
     * proceso).
     *
     * @param idPeriodo
     * @param idEmpresa
     * @return Lista de cargas por periodo
     * @throws Exception
     */
    List<PeCargaVariable> consultarCargasPorPeriodo(int idPeriodo, int idEmpresa) throws Exception;
    
    
    /**
     * Verifica si hay una carga por prestador con estado 0 (en cola) o 1 (en
     * proceso).
     *
     * @param idPeriodo
     * @param idPrestador
     * @return Lista de cargas por periodo
     * @throws Exception
     */
    List<PeCargaVariable> consultarCargasPorPeriodoSede(int idPeriodo, int idPrestador) throws Exception;
}
