/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.gestionAtencion;

import com.saviasaludeps.savia.dominio.gestionAtencion.GatAtencionHistorico;
import java.util.List;

/**
 *
 * @author acuartas
 */
public interface GatAtencionHistoricoRemoto {
    
    /**
     * Consulta el historico de una atencion
     * @param idAtencion
     * @return
     * @throws Exception 
     */
    List<GatAtencionHistorico> consultarListaPorAtencion(int idAtencion) throws Exception;

    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    GatAtencionHistorico consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(GatAtencionHistorico obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(GatAtencionHistorico obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    GatAtencionHistorico eliminar(int id) throws Exception;
    
}
