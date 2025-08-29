/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmRadicado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmReintento;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.Date;

/**
 *
 * @author yjimenez
 */
public interface CmRadicadoRemoto {

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CmRadicado) cargado
     * @throws java.lang.Exception
     */
    CmRadicado consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CmRadicado)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmRadicado obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CmRadicado)
     * @throws java.lang.Exception
     */
    void actualizar(CmRadicado obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CmRadicado) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmRadicado eliminar(int id) throws Exception;

    /**
     * Consulta la cantidad de CmRadicados
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadRadicado(ParamConsulta paramConsulta) throws Exception;

    /**
     * Actualizar el estado del radicado : {false : activo, true : cerrado} para procesar de momentos
     *
     * @param obj radicado
     * @throws Exception
     */
    void actualizarEstado(CmRadicado obj) throws Exception;

    /**
     * Permite consultar un radicado pendiente asociado a una factura si esta pendiente por 
     * envio sap momento1, envio sap devolucion, envio sap momento3
     * @param paramConsulta : paramConsulta.getParametroConsulta1() = idfactura obligatorio.
     * @return
     * @throws Exception 
     */
    CmReintento consultarRadicadoPendientePorFactura(ParamConsulta paramConsulta) throws Exception;
    /**
     * Permite consultar radicados activos conforme 
     * @param paramConsulta: getparam1: numero sincronizaciones, getparam2 : horas diferencia
     * @return
     * @throws Exception 
     */
    CmRadicado consultarSiguienteRadicadoActivo(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite modificar la cantidad de intentos permitidos
     * @param cantidadIntento
     * @param idRadicado
     * @throws Exception 
     */
    void actualizarIntentosPermitidos(int cantidadIntento, int idRadicado) throws Exception;

    /**
     * Permite modificar la cantidad de los intentos ejecutados
     * @param cantidadIntento
     * @param idRadicado
     * @throws Exception 
     */
    void actualizarIntentosEjecutados(int cantidadIntento, int idRadicado) throws Exception;
   
    /**
     * Permite actualizar el estado de proceso de un radicado segun el momento
     * ('0 - Creado | 1 - Facturas Inicio | 2 - Facturas Fin | 3 - Transacciones Inicio | 4 - Transacciones Fin | 5 - Envío SAP).
     * @param idCmRadicado
     * @param estado
     * @throws Exception 
     */
    void actualizarEstadoProceso(int idCmRadicado, short estado) throws Exception ;
    
    /**
     * Permite consultar radicados activos  fase creacion facturas
     * @param idRadicadoExcluir
     * @param horasDifencia
     * @return
     * @throws Exception 
     */
    CmRadicado consultarRadicadoActivoWsCreacionFacturas(int idRadicadoExcluir, int horasDifencia) throws Exception ;

    /**
     * Permite consultar radicados activos  fase creacion transacciones
     * @param idRadicadoExcluir
     * @param horasDifencia
     * @return
     * @throws Exception 
     */
    CmRadicado consultarRadicadoActivoWsCreacionTransacciones(int idRadicadoExcluir, int horasDifencia) throws Exception;

    /**
     * Permite realizar actualizacion de fecha registro factura
     * @param idCmRadicado
     * @param fecha
     * @throws Exception 
     */
    void actualizarFechaRegistroFacturas(int idCmRadicado, Date fecha) throws Exception;

    /**
     * Permite realizar actualizacion de fecha registro transaccion
     * @param idCmRadicado
     * @param fecha
     * @throws Exception 
     */
    void actualizarFechaRegistroTransaccion(int idCmRadicado, Date fecha) throws Exception;
    /**
     * Permite realizar actualizacion de fecha registro envio sap
     * @param idCmRadicado
     * @param fecha
     * @throws Exception 
     */
    void actualizarFechaRegistroEnvioSap(int idCmRadicado, Date fecha) throws Exception;

    /**
     * Permite consultar cmradicados que estan en disposicin envio sap
     * @param idRadicadoExcluir
     * @param horasDifencia
     * @return
     * @throws Exception 
     */
    CmRadicado consultarRadicadoActivoWsEnvioSap(int idRadicadoExcluir, int horasDifencia) throws Exception;

    /**
     * Permiteconcultar un radicado dado idconciliacionmasiva
     * @param idConciliacion
     * @return
     * @throws Exception 
     */
    CmRadicado consultarRadicadoSegunConciliacionMasiva(int idConciliacion) throws Exception;

}
