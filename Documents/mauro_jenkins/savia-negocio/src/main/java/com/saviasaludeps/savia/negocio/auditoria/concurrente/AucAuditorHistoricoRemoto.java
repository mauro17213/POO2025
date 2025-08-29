/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucAuditorHistorico;
import java.util.Date;

/**
 *
 * @author sgiraldov
 */
public interface AucAuditorHistoricoRemoto {
    
    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucAuditorHistorico consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AucAuditorHistorico obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AucAuditorHistorico obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucAuditorHistorico eliminar(int id) throws Exception;
    
}
