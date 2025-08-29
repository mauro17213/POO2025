/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimiento;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author iavenegas
 */
public interface AuSeguimientoRemoto {

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
    List<AuSeguimiento> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    AuSeguimiento consultar(int id) throws Exception;

    /**
     * Consulta el objeto cast corto
     *
     * @param id
     * @return
     * @throws Exception
     */
    AuSeguimiento consultarCorto(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AuSeguimiento obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizarEstado(AuSeguimiento obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AuSeguimiento obj) throws Exception;

    /**
     * Seguimiento por id item anexo3
     *
     * @param idItem
     * @return
     * @throws Exception
     */
    AuSeguimiento seguimientoPorAnexo3Item(int idItem) throws Exception;

    /**
     * valida si existe seguimiento de item y solicitud diferentes a cancelado
     *
     * @param idItem
     * @param idAnexo3
     * @return
     * @throws Exception
     */
    boolean validarExisteSeguimiento(int idItem, int idAnexo3) throws Exception;
    
    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizarFechasSeguimiento(AuSeguimiento obj) throws Exception;

}
