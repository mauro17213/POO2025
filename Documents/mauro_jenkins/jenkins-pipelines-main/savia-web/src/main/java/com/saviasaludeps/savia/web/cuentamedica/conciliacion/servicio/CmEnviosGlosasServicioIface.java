/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.conciliacion.servicio;

import com.saviasaludeps.savia.web.cuentamedica.conciliacion.bean.CmEnviosGlosaBean;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface CmEnviosGlosasServicioIface {
    
    /**
     * Método de acciones central
     * @param bean 
     */
    void Accion(CmEnviosGlosaBean bean);
    
    /**
     * Métodopara carhas inicial de variables
     * @param bean 
     */
    void cargaInial(CmEnviosGlosaBean bean);
    
    /**
     * Crea el radicado a partir de una conciliacion
     * @param cm_conciliaciones_id 
     */
    void crearRadicadoXConciliaciones(int cm_conciliaciones_id);
    
    /**
     * Crea el radicado a partir de la factura y el tipo de respuesta
     * @param cm_facturas_id 
     * @param tipoRespuesta 
     * @param cm_glosa_respuestas_id 
     */
    void crearRadicadoXFactura(int cm_facturas_id, int tipoRespuesta, int cm_glosa_respuestas_id);
    
    /**
     * Crea el radicado a partir de la glosa respuesta
     * @param cm_glosa_respuestas_id 
     */
    void crearRadicadoXCmGlosaRta(int cm_glosa_respuestas_id);
   
    /**
     * Metodo que permite radicar una devolucion
     * @param idDevolucion 
     */
    void crearRadicadoXDevolucionFactuar(int idDevolucion) ;
    
    /**
     * Metodo que permite radicar cierre auditoria
     * @param idCierreAuditoria 
     */
    void crearRadicadoXCierreAuditoria(int idCierreAuditoria) ;
   
    /**
     * Metodo que permite radicar glosa masiva
     * @param idGlosaMasiva 
     */
    void crearRadicadoCmGlosaMasiva(int idGlosaMasiva) ;
    
}
