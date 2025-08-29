/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionPatologia;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public interface AucHospitalizacionPatologiaRemoto {
    
    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucHospitalizacionPatologia consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AucHospitalizacionPatologia obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AucHospitalizacionPatologia obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucHospitalizacionPatologia eliminar(int id) throws Exception;
    
    /**
     * Consulta las patologias de una hospitalizacion
     * @param idHospitalizacion
     * @return
     * @throws Exception 
     */
    List<AucHospitalizacionPatologia> consultarPorIdHospitalizacion(int idHospitalizacion) throws Exception;
    
}
