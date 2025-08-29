/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucDiagnostico;
import java.util.List;

/**
 *
 * @author pavacca
 */
public interface AucDiagnosticoRemoto {
     
    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucDiagnostico consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AucDiagnostico obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AucDiagnostico obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucDiagnostico eliminar(int id) throws Exception;
    
    /**
     * Consulta todos los diagnosticos de ingreso
     *
     * @param id id del ingreso
     * @return
     * @throws Exception
     */
    List<AucDiagnostico> consultarPorIdIngreso(int id) throws Exception;
     
    /**
     * Método para consultar la lista de diagnosticos de egreso por ID
     * @param id id de egreso
     * @return (TuDiagnostico) cargado
     * @throws java.lang.Exception
     */
    List<AucDiagnostico> consultarPorIdEgreso(int id) throws Exception;
    
    /**
     * Método para consultar la lista de diagnosticos de hospitalizacion por ID
     * @param id id de egreso
     * @return (TuDiagnostico) cargado
     * @throws java.lang.Exception
     */
    List<AucDiagnostico> consultarPorIdHospitalizacion(int id) throws Exception;
}
