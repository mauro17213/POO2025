/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.generico;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Reporte;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;

/**
 *
 * @author StivenGV
 */
public interface ReporteSolicitudRemoto {
    
    /**
     * Funcion que crea el archivo de excel con la informacion de las autorizaciones 
     * @param reporte
     */
    void exportarExcel(AuAnexo4Reporte reporte);
    
}
