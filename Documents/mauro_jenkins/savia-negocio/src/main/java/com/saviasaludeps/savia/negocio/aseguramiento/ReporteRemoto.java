/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegInforme;
import com.saviasaludeps.savia.dominio.aseguramiento.VAsegCartillaDerechoDeber;
import com.saviasaludeps.savia.dominio.aseguramiento.VAsegDigitacionesNovedad;
import com.saviasaludeps.savia.dominio.aseguramiento.VAsegMaestrosSubsidiado;
//import com.saviasaludeps.savia.dominio.aseguramiento.VAsegNovedad;
import com.saviasaludeps.savia.dominio.aseguramiento.VAsegNovedadSubsidiado;
import com.saviasaludeps.savia.dominio.aseguramiento.VAsegPortabilidad;
import com.saviasaludeps.savia.dominio.aseguramiento.VAsegTraslado;
import java.util.Date;
import java.util.List;

public interface ReporteRemoto {
    
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
    List<AsegInforme> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (AsegInforme) objeto consultadp
     * @throws java.lang.Exception
     */
    AsegInforme consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (AsegInforme) registro a crear
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AsegInforme obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (AsegInforme)
     * @throws java.lang.Exception
     */
    void actualizar(AsegInforme obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (AsegInforme) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    AsegInforme eliminar(int id) throws Exception;
    
    /**
     * Consultar todas las AsegInforme
     * @return
     * @throws Exception 
     */
    List<AsegInforme> consultarTodos() throws Exception;
    
   /**
    * Consultar cantidad de reportes por tipo y estado de reporte.
    * 
    * @param tipo Tipo de Reporte a consultar
    * @param estado Estado del Reporte a consultar
    * @return
    * @throws Exception 
    */
    int consultarCantidadPorTipoYEstado(int tipo, int estado) throws Exception;
    
    /**
     * Consulta un Reporte por tipo, fecha Desde y fecha Hasta que no se encuentre en estado Rechazado = 3
     * @param tipo
     * @param fechaDesde
     * @param fechaHasta
     * @return
     * @throws Exception 
     */
    int consultarCantidadPorTipoYRangoFechas(int tipo, Date fechaDesde, Date fechaHasta) throws Exception;
    
    /**
     * Consultar registros de la Vista VAsegMaestrosSubsidiado que 
 esten entre la fechaInicio y la fechaFin
     * @param idReporte
     * @return
     * @throws Exception 
     */
    List<VAsegMaestrosSubsidiado> consultarReporteAfiliadosNuevosMS (int idReporte) throws Exception;
    
    /**
     * Consultar registros de la Vista VAsegNovedadesAfiliado que 
     * esten entre la fechaInicio y la fechaFin
     * @param idReporte
     * @return
     * @throws Exception 
     */
    List<VAsegNovedadSubsidiado> consultarReporteNovedadesAfiliadoNS(int idReporte) throws Exception;
    
    /**
     * Consultar registros de la Vista VAsegTraslados que 
     * esten entre la fechaInicio y la fechaFin
     * @param idReporte
     * @return
     * @throws Exception 
     */
    List<VAsegTraslado> consultarReporteTrasladoS1(int idReporte) throws Exception;
    
    /**
     * Consultar registros de la Vista VAsegPortabilidades que 
     * esten entre la fechaInicio y la fechaFin
     * @param fechaInicio
     * @param fechaFin
     * @return
     * @throws Exception 
     */
    List<VAsegPortabilidad> consultarReportePortabilidad(Date fechaInicio, Date fechaFin) throws Exception;
    
    /**
     * Consultar registros de la Vista VAsegDigitacionNovedad que 
     * esten entre la fechaInicio y la fechaFin
     * @param fechaInicio
     * @param fechaFin
     * @return
     * @throws Exception 
     */
    List<VAsegDigitacionesNovedad> consultarReporteDigitacionUsuarios(Date fechaInicio, Date fechaFin) throws Exception;
    
    /**
     * Consultar registros de la Vista VAsegCartillaDerechoDeber que 
     * esten entre la fechaInicio y la fechaFin
     * @param fechaInicio
     * @param fechaFin
     * @return
     * @throws Exception 
     */
    List<VAsegCartillaDerechoDeber> consultarReporteEncuestasAfiliados(Date fechaInicio, Date fechaFin) throws Exception;
    
//    /**
//     * Consultar registros de la Vista VAsegNovedad que tengan la fechaHoraNotificacion nula
//     * @return
//     * @throws Exception 
//     */
//    List<VAsegNovedad> consultarReporteNovedadesAseguramiento() throws Exception;
    
}
