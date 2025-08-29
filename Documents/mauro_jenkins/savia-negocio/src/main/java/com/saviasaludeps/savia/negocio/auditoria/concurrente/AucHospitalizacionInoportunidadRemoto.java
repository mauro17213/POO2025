/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionInoportunidad;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public interface AucHospitalizacionInoportunidadRemoto {
    
    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucHospitalizacionInoportunidad consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AucHospitalizacionInoportunidad obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AucHospitalizacionInoportunidad obj) throws Exception;
    
    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void borradoLogico(AucHospitalizacionInoportunidad obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucHospitalizacionInoportunidad eliminar(int id) throws Exception;
    
    /**
     * Consulta las inoportunidades de una hospitalizacion
     * @param idHospitalizacion
     * @return
     * @throws Exception 
     */
    List<AucHospitalizacionInoportunidad> consultarPorIdHospitalizacion(int idHospitalizacion) throws Exception;
    
}
