/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimientoAfiliado;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author iavenegas
 */
public interface AuSeguimientoAfiliadoRemoto {

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
    List<AuSeguimientoAfiliado> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    AuSeguimientoAfiliado consultar(int id) throws Exception;

    /**
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AuSeguimientoAfiliado obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AuSeguimientoAfiliado obj) throws Exception;

    /**
     * afiliado datos especificos  por tipo de tecnologia y afiliado
     * @param idTecnologia
     * @param tipoTecnologia
     * @param idAfiliado
     * @return
     * @throws Exception
     */
    AuSeguimientoAfiliado seguimientoPorTeconologiaPorAfiliado(int idTecnologia, int tipoTecnologia, int idAfiliado) throws Exception;

}
