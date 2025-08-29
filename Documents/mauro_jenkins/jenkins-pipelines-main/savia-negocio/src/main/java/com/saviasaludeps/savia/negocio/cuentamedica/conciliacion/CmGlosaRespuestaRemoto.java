/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuesta;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.ReporteRespuestaGlosa;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface CmGlosaRespuestaRemoto {
    
    /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmGlosaRespuesta> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (Personas) cargado
     * @throws java.lang.Exception
     */
    CmGlosaRespuesta consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (Personas)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmGlosaRespuesta obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (Personas)
     * @throws java.lang.Exception
     */
    void actualizar(CmGlosaRespuesta obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (Personas) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmGlosaRespuesta eliminar(int id) throws Exception;
    
    /**
     * Saca el reporte de respuesta glosa
     * @param id
     * @return 
     */
    List<ReporteRespuestaGlosa> reporteRespuestaGlosa(int id);
    
    List<CmGlosaRespuesta> consultarXCmConciliacionesId(int cm_conciliaciones_id) throws Exception;
    
    List<CmGlosaRespuesta> consultarXCmFacturasId(int cm_facturas_id, int tipoRespuesta) throws Exception;
    
    List<CmGlosaRespuesta> consultarXCmGlosaRta(int cm_glosa_respuestas_id) throws Exception;
    /**
     * Metodo que permite consultar la cantidad de respuestas por conciliacion.
     * @param cm_conciliaciones_id
     * @return
     * @throws Exception 
     */
    int consultarCantidadPorConciliacion(int cm_conciliaciones_id) throws Exception;

    /**
     * Metodo que permite generar un reporte con los items enviados a sap
     * @param idGlosa
     * @return 
     */
    boolean verificacionEnvioSapExitoso(int idGlosa);

    /**
     * Permite consultar respuestas glosas por conciliaciones
     * @param idConciliacion
     * @return
     * @throws Exception 
     */
    int consultarCantidadRespuestasPorCanciliacion(int idConciliacion) throws Exception;

    /**
     * permite obtener las respusetas glosas de conciliaciones pero estas se consultan en bloque 
     * @param cm_conciliaciones_id
     * @return
     * @throws Exception 
     */
    List<CmGlosaRespuesta> consultarXCmConciliacionesIdPorBloque(int cm_conciliaciones_id) throws Exception;

    /**
     * Permite consultar glosas de una conciliacion de forma paginada
     * @param cm_conciliaciones_id
     * @param paginaInicio
     * @param paginaFinal
     * @return
     * @throws Exception 
     */
    List<CmGlosaRespuesta> consultarXCmConciliacionesIdPaginada(int cm_conciliaciones_id, int paginaInicio, int paginaFinal) throws Exception;

    /**
     * Metodo que permite actualizar el estado de sincronizacion de cmRespuestaGlosa
     * @param obj
     * @throws Exception 
     */
    void actualizaEstadoSincronizacion(CmGlosaRespuesta obj) throws Exception;

    /**
     * Permite consultar numero de respuestas que tiene una glosa masiva
     * @param idGlosaMasiva
     * @param tipoRespuesta
     * @param estadoSincronizacion
     * @return
     * @throws Exception 
     */
    int consultarCantidadRespuestasGlosaMasiva(int idGlosaMasiva, int tipoRespuesta, int estadoSincronizacion) throws Exception;

    /**
     * Permite consultar Respuesta glosas masivas
     * @param idGlosaMasiva
     * @return
     * @throws Exception 
     */
    List<CmGlosaRespuesta> consultarRespuestasGlosasMasivasEnBloque(int idGlosaMasiva) throws Exception;

    /**
     * Permite consutlar de forma paginasa las glosas relacionadas a un proceso masiva.
     * @param idGlosaMasiva
     * @param tipoRespuesta
     * @param estadoSincronizacion
     * @param paginaInicio
     * @param paginaFinal
     * @return
     * @throws Exception 
     */
    List<CmGlosaRespuesta> consultarXGlosaMasivaIdPaginada(int idGlosaMasiva, int tipoRespuesta, int estadoSincronizacion,int paginaInicio, int paginaFinal) throws Exception;

    /**
     * Permite actualizar estado sincronizacion
     * @param paramConsulta:paramConsulta.getParametroConsulta1(): idcmfactura, paramConsulta.getParametroConsulta2() : estado,
     * paramConsulta.getParametroConsulta3(): tipoRespuesta, paramConsulta.getParametroConsulta4():idglosamasiva, 
     * paramConsulta.getParametroConsulta5(): idconciliacionmasiva
     * @throws Exception 
     */
    void actualizaEstadoSincronizacion(ParamConsulta paramConsulta) throws Exception;
}
