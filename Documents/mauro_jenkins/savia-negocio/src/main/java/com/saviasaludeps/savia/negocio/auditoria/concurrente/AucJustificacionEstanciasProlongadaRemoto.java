/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucJustificacionEstanciasProlongada;
import java.util.List;

/**
 *
 * @author pavacca
 */
public interface AucJustificacionEstanciasProlongadaRemoto {
    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucJustificacionEstanciasProlongada consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AucJustificacionEstanciasProlongada obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AucJustificacionEstanciasProlongada obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucJustificacionEstanciasProlongada eliminar(int id) throws Exception;
    
    /**
     * Consulta el objeto dado el id
     *
     * @param id id de la hospitalizacion
     * @return
     * @throws Exception
     */
    List<AucJustificacionEstanciasProlongada> consultarPorIdHospitalizacion(int id) throws Exception;
}
