/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionSeguimiento;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public interface AucHospitalizacionSeguimientoRemoto {

    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucHospitalizacionSeguimiento consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AucHospitalizacionSeguimiento obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AucHospitalizacionSeguimiento obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizarGestorasRegionales(AucHospitalizacionSeguimiento obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizarBorradoLogico(AucHospitalizacionSeguimiento obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucHospitalizacionSeguimiento eliminar(int id) throws Exception;

    /**
     * Consulta los seguimientos de una hospitalizacion
     *
     * @param idHospitalizacion
     * @return
     * @throws Exception
     */
    List<AucHospitalizacionSeguimiento> consultarPorIdHospitalizacion(int idHospitalizacion) throws Exception;
    
    /**
     * Consulta los gestoras regionales de una hospitalizacion
     *
     * @param idHospitalizacion
     * @return
     * @throws Exception
     */
    List<AucHospitalizacionSeguimiento> consultarPorIdHospitalizacionGestorasRegionales(int idHospitalizacion) throws Exception;

}
