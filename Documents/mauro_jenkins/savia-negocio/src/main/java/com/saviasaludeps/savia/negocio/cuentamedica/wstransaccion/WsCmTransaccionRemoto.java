/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.wstransaccion;

import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmTransaccion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jeperez
 */
public interface WsCmTransaccionRemoto {

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
    List<WsCmTransaccion> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (Personas) cargado
     * @throws java.lang.Exception
     */
    WsCmTransaccion consultar(int id) throws Exception;

    /**
     *
     * @param per
     * @return (ControlCierre) cargado
     * @throws java.lang.Exception
     */

    int insertar(WsCmTransaccion per) throws Exception;

    /**
     *
     * @param per (ControlCierre)
     * @throws java.lang.Exception
     */
    void actualizar(WsCmTransaccion per) throws Exception;
    /**
     *
     * @param id
     * @return (ControlCierre) Objetop eliminado
     * @throws java.lang.Exception
     */
    WsCmTransaccion eliminar(int id) throws Exception;

    /**
     * Permite consultar transacciones por estado e idCmRadicado
     * @param idCmRadicado
     * @param estado
     * @return
     * @throws Exception 
     */
    WsCmTransaccion consultarPorEstadoIdRadicado(int idCmRadicado, int estado) throws Exception;

    /**
     * permite actualizacion datos iniciales transaccion
     * @param idWstrandaccion
     * @param estado
     * @param fechaEnvio
     * @throws Exception 
     */
    void actualizarDatosInicioTransaccion(int idWstrandaccion, short estado, Date fechaEnvio) throws Exception;

    /**
     * Permite actualizar datos recibidos de una transaccion
     * @param wsCmTransaccion
     * @throws Exception 
     */
    void actualizarDatosRecibidosTransaccion(WsCmTransaccion wsCmTransaccion) throws Exception;

    /**
     * Permite actualizar la cantidad de paquetes exitosos realizados
     * @param idWsTransaccion
     * @param paquetesExitosos
     * @throws Exception 
     */
    void actualizarPaquetesExitosos(int idWsTransaccion, short paquetesExitosos) throws Exception;

    /**
     * Permite actualizar estado de transaccion
     * @param idWsCmFactura
     * @param estado
     * @throws Exception 
     */
    void actualizarEstado(int idWsCmFactura, short estado) throws Exception;

    /**
     * Permite consultar ultima transaccion 
     * @param idCmConciliacion
     * @return
     * @throws Exception 
     */
    WsCmTransaccion consultarUltimaWsTransaccionPorCmConciliacion(int idCmConciliacion) throws Exception;

    /**
     * Permite consultar ultima transaccion por auditoria masiva
     * @param idCmAuditoria
     * @return
     * @throws Exception 
     */
    WsCmTransaccion consultarUltimaWsTransaccionPorCmAuditoriaMasiva(int idCmAuditoria) throws Exception;

    /**
     * Permite consultar la lista de transacciones de un cmradicado y con un estado especifico
     * @param idCmRadicado
     * @param estado
     * @return
     * @throws Exception 
     */
    List<WsCmTransaccion> consultarListaPorEstadoIdRadicado(int idCmRadicado, int estado) throws Exception;
 
}
