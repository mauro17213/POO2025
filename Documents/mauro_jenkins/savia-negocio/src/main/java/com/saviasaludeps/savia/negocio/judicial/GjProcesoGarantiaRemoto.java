/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.judicial;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.judicial.GjProcesoGarantia;
import java.util.List;

/**
 *
 * @author jperezn
 */
public interface GjProcesoGarantiaRemoto {

    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<GjProcesoGarantia> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (GjProcesoGarantia) cargado
     * @throws java.lang.Exception
     */
    GjProcesoGarantia consultar(int id) throws Exception;

    /**
     * Método para crear una nueva Empresa
     *
     * @param per (GjProcesoGarantia)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(GjProcesoGarantia per) throws Exception;

    /**
     * Permite consultar por id grupo
     *
     * @param idProceso
     * @return
     * @throws Exception
     */
    List<GjProcesoGarantia> consultarListaPorIdProceso(int idProceso) throws Exception;

    
}
