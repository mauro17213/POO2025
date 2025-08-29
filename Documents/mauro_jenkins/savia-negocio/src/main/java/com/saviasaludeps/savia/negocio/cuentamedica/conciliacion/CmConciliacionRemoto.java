/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmConciliacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.ReporteConciliacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rpalacic
 */
public interface CmConciliacionRemoto {

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
    List<CmConciliacion> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (Personas) cargado
     * @throws java.lang.Exception
     */
    CmConciliacion consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (Personas)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmConciliacion obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (Personas)
     * @throws java.lang.Exception
     */
    void actualizar(CmConciliacion obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (Personas) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmConciliacion eliminar(int id) throws Exception;

    /**
     * Metodo que busca los datos para el reporte de conciliacion
     *
     * @param id
     * @return Lista de datos para generar el reporte
     * @throws Exception
     */
    List<ReporteConciliacion> reporteConciliacion(int id) throws Exception;

    /**
     * Metodo que permite obtener datos de conciliaciones con envio a sap
     * exitoso
     *
     * @param id
     * @param wsFacturasExitosas
     * @return
     * @throws Exception
     */
    List<ReporteConciliacion> reporteConciliacionEnvioSapExitoso(int id,  Map<String,Integer> wsFacturasExitosas) throws Exception;

    
    List<CmConciliacion> consultarAnexos(int id) throws Exception;
    /**
     * Consultar detalles de una factura en carga
     *
     * @param paramConsulta de carga paramConsulta1 Id de Carga
     * @return detalles factura DTO
     * @throws Exception
     */
    
}
