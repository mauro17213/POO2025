/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionHistorico;
import java.util.List;

/**
 *
 * @author pavacca
 */
public interface AucHospitalizacionHistoricoRemoto {
    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucHospitalizacionHistorico consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AucHospitalizacionHistorico obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AucHospitalizacionHistorico obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucHospitalizacionHistorico eliminar(int id) throws Exception;
    
    /**
     * Consultar historico egresos para un hospitalizacion
     *
     * @param obj
     * @return
     * @throws Exception
     */
    List<AucHospitalizacionHistorico> consultarHistoricoIdHopitalizacionSoloEgresos(AucHospitalizacionHistorico obj) throws Exception;
}
