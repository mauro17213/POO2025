/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Reporte;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public interface AuAnexo4ReporteRemoto {
    
    /**
     * Inserta en BD un reporte de anexo 4
     * @param obj
     * @return 
     * @throws Exception 
     */
    int insertar(AuAnexo4Reporte obj) throws Exception;
    
    /**
     * Lista los  reportes creados por un usuarios
     * @param idUsuario
     * @return
     * @throws Exception 
     */
    List<AuAnexo4Reporte> listarPorUsuario(int idUsuario) throws Exception;
    
    /**
     * Actualizar el reporte
     * @param obj
     * @throws Exception 
     */
    void actualizar(AuAnexo4Reporte obj) throws Exception;
    
}
