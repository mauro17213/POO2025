/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionEstado;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public interface AucHospitalizacionEstadoRemoto {
    
    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucHospitalizacionEstado consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AucHospitalizacionEstado obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AucHospitalizacionEstado obj) throws Exception;
    
    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucHospitalizacionEstado eliminar(int id) throws Exception;
    
    
    /**
     * Consulta el objeto dado el id hospitalizacion, estado y estado de auditoria
     *
     * @param idHospitalizacion
     * @return
     * @throws Exception
     */
    AucHospitalizacionEstado consultarHospitalizacionYestados(Integer idHospitalizacion) throws Exception;
    
    
    /**
     * Consulta el objeto dado el id hospitalizacion, estado y estado de auditoria
     *
     * @param idHospitalizacion
     * @return
     * @throws Exception
     */
    AucHospitalizacionEstado consultarHospitalizacionYUltimoEstadoEgreso(Integer idHospitalizacion) throws Exception;
    
    /**
     * Consulta el objeto dado el id hospitalizacion, estado y estado de auditoria
     *
     * @param idHospitalizacion
     * @return
     * @throws Exception
     */
    AucHospitalizacionEstado consultarHospitalizacionYUltimoEstadoDevuelto(Integer idHospitalizacion) throws Exception;
       
}
