/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.wstransaccion;

import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmFacturaDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author jeperez
 */
public interface WsCmFacturaDetalleRemoto {

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
    List<WsCmFacturaDetalle> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (Personas) cargado
     * @throws java.lang.Exception
     */
    WsCmFacturaDetalle consultar(int id) throws Exception;

    /**
     *
     * @param per
     * @return (ControlCierre) cargado
     * @throws java.lang.Exception
     */

    int insertar(WsCmFacturaDetalle per) throws Exception;

    /**
     *
     * @param per (ControlCierre)
     * @throws java.lang.Exception
     */
    void actualizar(WsCmFacturaDetalle per) throws Exception;
    /**
     *
     * @param id
     * @return (ControlCierre) Objetop eliminado
     * @throws java.lang.Exception
     */
    WsCmFacturaDetalle eliminar(int id) throws Exception;

    /**
     * Permite consultar todos los detalles por idwscmfactura
     * @param idWsCmFactura
     * @return
     * @throws Exception 
     */
    List<WsCmFacturaDetalle> consultarPorIdWsCmFactura(int idWsCmFactura) throws Exception;

  
}
