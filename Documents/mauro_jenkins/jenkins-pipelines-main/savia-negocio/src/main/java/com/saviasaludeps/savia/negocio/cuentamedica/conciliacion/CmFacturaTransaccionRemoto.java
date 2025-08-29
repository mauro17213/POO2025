/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFacturaTransaccion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author jeperez
 */
public interface CmFacturaTransaccionRemoto {

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CmFacturaTransaccion) cargado
     * @throws java.lang.Exception
     */
    CmFacturaTransaccion consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CmFacturaTransaccion)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmFacturaTransaccion obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CmFacturaTransaccion)
     * @throws java.lang.Exception
     */
    void actualizar(CmFacturaTransaccion obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CmFacturaTransaccion) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmFacturaTransaccion eliminar(int id) throws Exception;

    /**
     * Permite obtener cantidad de elementos de consulta
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite obtener lista de registros
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmFacturaTransaccion> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite insertar transaccion envio factura
     * @param idFactura
     * @param tipo
     * @param estado
     * @return 
     */
    CmFacturaTransaccion insertarTransaccionEnvioFactura(int idFactura, int tipo, int estado);


}
