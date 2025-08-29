/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.wstransaccion;

import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmFactura;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jeperez
 */
public interface WsCmFacturaRemoto {

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
    List<WsCmFactura> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (Personas) cargado
     * @throws java.lang.Exception
     */
    WsCmFactura consultar(int id) throws Exception;

    /**
     *
     * @param per
     * @return (ControlCierre) cargado
     * @throws java.lang.Exception
     */

    int insertar(WsCmFactura per) throws Exception;

    /**
     *
     * @param per (ControlCierre)
     * @throws java.lang.Exception
     */
    void actualizar(WsCmFactura per) throws Exception;
    /**
     *
     * @param id
     * @return (ControlCierre) Objetop eliminado
     * @throws java.lang.Exception
     */
    WsCmFactura eliminar(int id) throws Exception;

    /**
     * Permite actualizar el estado del registro
     * @param idWsCmFactura
     * @param estado
     * @throws Exception 
     */
    void actualizarEstado(int idWsCmFactura, short estado) throws Exception;

    /**
     * Permite actualizar valores de valores proceso segun momento
     * @param idWsCmFactura
     * @param valorPagado
     * @param valorGlosado
     * @throws Exception 
     */
    void actualizarValoresProceso(int idWsCmFactura, BigDecimal valorPagado, BigDecimal valorGlosado) throws Exception;

    /**
     * Permite consuta todas segun idcmradicado
     * @param idCmRadicado
     * @return
     * @throws Exception 
     */
    List<WsCmFactura> consultarTodasPorIdCmRadicado(int idCmRadicado) throws Exception;

    /**
     * permite consultar facturas por estado
     * @param idCmRadicado
     * @param estado
     * @return
     * @throws Exception 
     */
    List<WsCmFactura> consultarPorEstadoIdCmRadicado(int idCmRadicado, int estado) throws Exception;

    /**
     * Permite consultar ultima factura
     * @param idCmRadicado
     * @return
     * @throws Exception 
     */
    WsCmFactura consultarUltimaWsCmFacturaPorCmRadicado(int idCmRadicado) throws Exception;

    /**
     * permite consultar wscmfacturas donde estado sea diferente a exitoso
     * @param idCmRadicado
     * @return
     * @throws Exception 
     */
    List<WsCmFactura> consultarPorIdCmRadicadoEstadoPendientes(int idCmRadicado) throws Exception;

    /**
     * Permite actualizacion de estado wsfacturas teniendo encuenta radicados , icmfactura
     * @param idCmRadicado
     * @param idCmFactura
     * @param estado
     * @throws Exception 
     */
    void actualizarEstado(int idCmRadicado, int idCmFactura, short estado) throws Exception;

}
