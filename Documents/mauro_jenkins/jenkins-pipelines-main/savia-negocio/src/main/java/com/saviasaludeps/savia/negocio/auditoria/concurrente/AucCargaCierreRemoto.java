/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucCarga;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucCargaCierre;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public interface AucCargaCierreRemoto {
    
    /**
     * Consulta la cantidad de datos de parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta la lista dado los parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<AucCargaCierre> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Funcion que pemite obtener cargas que pertenecen a cola
     *
     * @param numeroMaximoRegistros
     * @return
     * @throws Exception
     */
    List<AucCargaCierre> consulltarPorEstadoCola(Integer numeroMaximoRegistros) throws Exception;
    
    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucCargaCierre consultar(int id) throws Exception;
    
    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AucCargaCierre obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AucCargaCierre obj) throws Exception;
    
    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizarEstadoProcesado(AucCargaCierre obj) throws Exception;
    
    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    AucCargaCierre eliminar(int id) throws Exception;
    
}
