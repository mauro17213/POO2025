/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.Reporte;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;

/**
 *
 * @author rpalacic
 */
public interface ReporteCuentasMedicasRemoto {
    
    /**
     * Generar reporte motivos
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    Reporte generarReporteMotivos(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Generar reporte eps
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    Reporte generarReporteRespuestaDetalles(ParamConsulta paramConsulta) throws Exception;
  
    /**
    * Metodo que permite consultar detalles que no tengan respuesta asociada
    * @param paramConsulta
    * @return
    * @throws Exception 
    */ 
    Reporte generarReporteDetallesSinRespuestas(ParamConsulta paramConsulta) throws Exception;
    /**
     * Metodo que permite consultar todas las 2 respuestas de una factura
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    Reporte generarReporteRespuestasPorDetalle(ParamConsulta paramConsulta) throws Exception;

    /**
     * Metodo que permite consutar factuas y atributos de auditoria
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    Reporte generarReporteAuditoria(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite generar un informe de proposito general de cuentas medicas
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    Reporte generarReporteGeneralCuentaMedica(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite generar reporte cuentas medicas con detalles
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    Reporte generarReporteGeneralCuentaMedicaDetalles(ParamConsulta paramConsulta) throws Exception;
    /**
     * Permite realizar la devolucion de facturas
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    Reporte generarReporteDevolucionFacturas(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Permite listas las factuas conciladas
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    Reporte generarReporteFacturasConciliadas(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Permite listas las facturas rechazadas 
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    Reporte generarReporteFacturasRechazadas(ParamConsulta paramConsulta) throws Exception;
    
     /**
     * Permite listas detalles Descuento Capita
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    Reporte generarReporteDetallesDescuentoCapita(ParamConsulta paramConsulta) throws Exception;
    
    
}
