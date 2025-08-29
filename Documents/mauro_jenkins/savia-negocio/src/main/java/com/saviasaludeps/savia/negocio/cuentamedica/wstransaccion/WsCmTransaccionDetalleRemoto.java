/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.wstransaccion;

import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmTransaccionDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jeperez
 */
public interface WsCmTransaccionDetalleRemoto {

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
    List<WsCmTransaccionDetalle> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (Personas) cargado
     * @throws java.lang.Exception
     */
    WsCmTransaccionDetalle consultar(int id) throws Exception;

    /**
     *
     * @param per
     * @return (ControlCierre) cargado
     * @throws java.lang.Exception
     */

    int insertar(WsCmTransaccionDetalle per) throws Exception;

    /**
     *
     * @param per (ControlCierre)
     * @throws java.lang.Exception
     */
    void actualizar(WsCmTransaccionDetalle per) throws Exception;
    /**
     *
     * @param id
     * @return (ControlCierre) Objetop eliminado
     * @throws java.lang.Exception
     */
    WsCmTransaccionDetalle eliminar(int id) throws Exception;

    /**
     * Permite actualizar datos respuesta
     * @param neg
     * @throws Exception 
     */
    void actualizarDatosRespuesta(WsCmTransaccionDetalle neg) throws Exception;

    /**
     * Permite actualizar fecha de envio de paquete a sap
     * @param fechaHoraEnvio
     * @param idWsCmTransaccionDetalle
     * @throws Exception 
     */
    void actualizarFechaEnvio(Date fechaHoraEnvio, int idWsCmTransaccionDetalle) throws Exception;

    /**
     * Permite actualizar json envio 
     * @param jsonEnvio
     * @param idWsCmTransaccionDetalle
     * @throws Exception 
     */
    void actualizarJsonEnvio(byte[] jsonEnvio, int idWsCmTransaccionDetalle) throws Exception;

    /**
     * Permite consulta de detalles dado idwscmtransaccion
     * @param idWsCmTransaccion
     * @return
     * @throws Exception 
     */
    List<WsCmTransaccionDetalle> consultarPorIdWsCmTransaccion(int idWsCmTransaccion) throws Exception;

    /**
     * Permite consultar transaccion por estado e idtransaccion detalle
     * @param idWsCmTransaccion
     * @param estado
     * @return
     * @throws Exception 
     */
    List<WsCmTransaccionDetalle> consultarPorIdWsCmTransaccionEstado(int idWsCmTransaccion, short estado) throws Exception;

    /**
     * actualiza estado de registro
     * @param estado
     * @param idWsCmTransaccionDetalle
     * @throws Exception 
     */
    void actualizarEstado(short estado, int idWsCmTransaccionDetalle) throws Exception;

    /**
     * Permite realizar la busqueda de detalles multiEstados
     * @param idWsCmTransaccion
     * @param estado1
     * @param estado2
     * @param estado3
     * @return
     * @throws Exception 
     */
    List<WsCmTransaccionDetalle> consultarPorIdWsCmTransaccionMultiEstado(int idWsCmTransaccion, short estado1, short estado2, short estado3) throws Exception;

    /**
     * Permite consultar transaccion detalles
     * @param paramConsulta(getParam1: idcmfactura -obligatorio, getParam2: momento -opcional)
     * @return
     * @throws Exception 
     */
    List<WsCmTransaccionDetalle> consultarPorIdCmFactura(ParamConsulta paramConsulta) throws Exception;

  
}
