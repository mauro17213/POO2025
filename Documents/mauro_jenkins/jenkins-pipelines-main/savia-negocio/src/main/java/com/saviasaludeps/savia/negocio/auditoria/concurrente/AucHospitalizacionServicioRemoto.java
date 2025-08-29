/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionServicio;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public interface AucHospitalizacionServicioRemoto {
    
    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucHospitalizacionServicio consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AucHospitalizacionServicio obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AucHospitalizacionServicio obj) throws Exception;
    
    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void borradoLogico(AucHospitalizacionServicio obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucHospitalizacionServicio eliminar(int id) throws Exception;
    
    /**
     * Consulta los servicios de una hospitalizacion
     * @param idHospitalizacion
     * @return
     * @throws Exception 
     */
    List<AucHospitalizacionServicio> consultarPorIdHospitalizacion(int idHospitalizacion) throws Exception;
    
}
