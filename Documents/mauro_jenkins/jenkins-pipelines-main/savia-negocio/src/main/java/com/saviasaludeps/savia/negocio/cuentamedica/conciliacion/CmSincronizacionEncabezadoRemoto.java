/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionEncabezado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionEncabezadoResumen;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author yjimenez
 */
public interface CmSincronizacionEncabezadoRemoto {

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CmSincronizacionEncabezado) cargado
     * @throws java.lang.Exception
     */
    CmSincronizacionEncabezado consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CmSincronizacionEncabezado)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmSincronizacionEncabezado obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CmSincronizacionEncabezado)
     * @throws java.lang.Exception
     */
    void actualizar(CmSincronizacionEncabezado obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CmSincronizacionEncabezado) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmSincronizacionEncabezado eliminar(int id) throws Exception;

    /**
     * Método para consultar CmSincronizacionEncabezado por id
     *
     * @param id
     * @return
     * @throws Exception
     */
    List<CmSincronizacionEncabezado> consultarDetalles(int id) throws Exception;

    /**
     * Método para consultar CmSincronizacionEncabezado por id
     *
     * @param nitProveedor
     * @param numeroDocumento
     * @return
     * @throws Exception
     */
    CmSincronizacionEncabezado consultarEnacabezadoNitNumeroDocumento(String nitProveedor, String numeroDocumento) throws Exception;

    /**
     * Metodo que consulta cantidad de items encontrados en parametro de consulta.
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Metodo que permite consultar encabezados segun criterio busqueda.
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmSincronizacionEncabezado> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
    * permite buscar factura por por radicado y nit proveedor
    * @param nitProveedor
    * @param numeroRadicado
    * @return
    * @throws Exception 
    */
    CmSincronizacionEncabezado consultarEnacabezadoNitNumeroRadicado(String nitProveedor, String numeroRadicado) throws Exception;

    /**
     * permite buscar encabezado por nit y concecutivo
     * @param nitProveedor
     * @param concecutivo
     * @return
     * @throws Exception 
     */
    CmSincronizacionEncabezado consultarEnacabezadoPorNitConcecutivo(String nitProveedor, int concecutivo) throws Exception;

    /**
     * permite obtener los encabezados presentes cuando se proporciona un idcmradicado y un idcmfactura
     * @param idCmRadicado
     * @param idCmFactura
     * @return
     * @throws Exception 
     */
    CmSincronizacionEncabezado consultarPorRadicadoFactura(int idCmRadicado, int idCmFactura) throws Exception;

    /**
     * permite consultar todos los encabezados pertenecientes a un radicado
     * @param id
     * @return
     * @throws Exception 
     */
    List<CmSincronizacionEncabezado> consultarTodos(int id) throws Exception;
    /**
     * Permite consultar resumen encabezado
     * @param idRadicado
     * @return 
     */
    List<CmSincronizacionEncabezadoResumen> consultarResumenEncabezado(int idRadicado);

    /**
     * Permite actualizar estado factura a exitoso
     * @param idCmRadicado
     * @param idFactura
     * @throws Exception 
     */
    void actualizarEstadoExitoso(int idCmRadicado, int idFactura) throws Exception;

    /**
     * Permite conslultar encabezados sin procesar que correspondan a un radicado y idCmfactura
     * @param idCmRadicado
     * @param idCmFactura
     * @return
     * @throws Exception 
     */
     CmSincronizacionEncabezado consultarPorRadicadoFacturaSinProcesar(int idCmRadicado, int idCmFactura) throws Exception;
    
}
