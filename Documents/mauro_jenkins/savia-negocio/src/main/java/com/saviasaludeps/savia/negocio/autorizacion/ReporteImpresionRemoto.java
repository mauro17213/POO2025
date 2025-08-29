/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.ReporteImpresion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public interface ReporteImpresionRemoto {
    
    /**
     * Consulta las impresiones de anexo 4 entre dos fechas
     * @param fechaInicio
     * @param fechaFin
     * @return 
     */
    List<ReporteImpresion> consultarImpresionesAnexo4PorFecha(Date fechaInicio, Date fechaFin) throws Exception;
    
    
}
