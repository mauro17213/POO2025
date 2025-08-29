/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionAdverso;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public interface AucHospitalizacionAdversoRemoto {
    
    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucHospitalizacionAdverso consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AucHospitalizacionAdverso obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AucHospitalizacionAdverso obj) throws Exception;
    
    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void borradoLogico(AucHospitalizacionAdverso obj) throws Exception;
    
    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucHospitalizacionAdverso eliminar(int id) throws Exception;
    
    /**
     * Consulta los adversos de una hospitalizacion
     * @param idHospitalizacion
     * @return
     * @throws Exception 
     */
    List<AucHospitalizacionAdverso> consultarPorIdHospitalizacion(int idHospitalizacion) throws Exception;
    
    
}
