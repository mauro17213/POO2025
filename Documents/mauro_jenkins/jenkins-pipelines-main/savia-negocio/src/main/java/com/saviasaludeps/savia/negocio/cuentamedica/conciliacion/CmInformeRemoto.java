/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmInforme;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author jperez
 */
public interface CmInformeRemoto {

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CmSincronizacionEncabezado) cargado
     * @throws java.lang.Exception
     */
    CmInforme consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CmSincronizacionEncabezado)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmInforme obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CmSincronizacionEncabezado)
     * @throws java.lang.Exception
     */
    void actualizar(CmInforme obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CmSincronizacionEncabezado) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmInforme eliminar(int id) throws Exception;


    /**
     * Metodo que permite consultar la contidad de informes
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    /**
     * Metodo que permite encontrar los informes dado filtros determinados
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmInforme> consultarLista(ParamConsulta paramConsulta) throws Exception;
}
