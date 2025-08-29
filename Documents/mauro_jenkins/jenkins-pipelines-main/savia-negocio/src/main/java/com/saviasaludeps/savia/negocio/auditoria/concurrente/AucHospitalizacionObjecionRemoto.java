/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionObjecion;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public interface AucHospitalizacionObjecionRemoto {
    
    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucHospitalizacionObjecion consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AucHospitalizacionObjecion obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AucHospitalizacionObjecion obj) throws Exception;
    
    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void borradoLogico(AucHospitalizacionObjecion obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucHospitalizacionObjecion eliminar(int id) throws Exception;
    
    
    /**
     * Consulta las objeciones de una hospitalizacion
     * @param idHospitalizacion
     * @return
     * @throws Exception 
     */
    List<AucHospitalizacionObjecion> consultarPorIdHospitalizacion(int idHospitalizacion) throws Exception;
}
