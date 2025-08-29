/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.gestionAtencion;

import com.saviasaludeps.savia.dominio.gestionAtencion.GatAtencionComentario;
import java.util.List;

/**
 *
 * @author acuartas
 */
public interface GatAtencionComentarioRemoto {
    
    /**
     * Consulta el historico de una atencion
     * @param idAtencion
     * @return
     * @throws Exception 
     */
    List<GatAtencionComentario> consultarListaPorAtencion(int idAtencion) throws Exception;

    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    GatAtencionComentario consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(GatAtencionComentario obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    GatAtencionComentario eliminar(int id) throws Exception;
    
}
