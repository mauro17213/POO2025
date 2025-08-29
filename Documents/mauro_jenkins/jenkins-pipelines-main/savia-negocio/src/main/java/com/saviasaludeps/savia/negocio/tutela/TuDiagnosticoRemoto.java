/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.tutela;

import com.saviasaludeps.savia.dominio.tutela.TuDiagnostico;

/**
 *
 * @author pavacca
 */
public interface TuDiagnosticoRemoto {
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (TuDiagnostico) cargado
     * @throws java.lang.Exception
     */
    TuDiagnostico consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (TuDiagnostico)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(TuDiagnostico obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (TuDiagnostico)
     * @throws java.lang.Exception
     */
    void actualizar(TuDiagnostico obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (TuDiagnostico) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    TuDiagnostico eliminar(int id) throws Exception;
    
}
