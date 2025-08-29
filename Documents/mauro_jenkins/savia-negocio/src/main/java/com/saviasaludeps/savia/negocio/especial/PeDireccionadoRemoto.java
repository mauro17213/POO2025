/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.especial;

import com.saviasaludeps.savia.dominio.especial.PeDireccionado;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface PeDireccionadoRemoto {

    /**
     * Consulta de cantidad de registros en una lista de direccionados
     *
     * @author idbohorquez
     * @creado 29/08/2022
     * @param paramConsulta
     * @return int
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros de direccionados
     *
     * @author idbohorquez
     * @creado 29/08/2022
     * @param paramConsulta
     * @return List<PeDireccionado>
     * @throws Exception
     */
    List<PeDireccionado> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Funcion encargada de guardar un nuevo registro de direccionado en DB
     *
     * @author idbohorquez
     * @creado 30/08/2022
     * @param obj
     * @return int
     * @throws Exception
     */
    int insertar(PeDireccionado obj) throws Exception;

    /**
     * Funcion encargada de consultar registro de direccionado por su id
     *
     * @author idbohorquez
     * @creado 30/08/2022
     * @param id
     * @return PeDireccionado
     * @throws Exception
     */
    PeDireccionado consultar(int id) throws Exception;

    /**
     * Metodo encargada de anular un registro de direccionado
     *
     * @author idbohorquez
     * @creado 01/09/2022
     * @param obj
     * @throws Exception
     */
    public void anular(PeDireccionado obj) throws Exception;

    /**
     * Metodo encargada de cambiar el estado de un registro
     *
     * @author idbohorquez
     * @creado 14/09/2022
     * @param obj
     * @throws Exception
     */
    void actualizarEstado(PeDireccionado obj) throws Exception;

    /**
     * Consulta de cantidad de registros en una lista de direccionados
     *
     * @author idbohorquez
     * @creado 29/08/2022
     * @param paramConsulta
     * @return int
     * @throws Exception
     */
    int consultarCantidadListaAfiliado(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros de direccionados
     *
     * @author idbohorquez
     * @creado 29/08/2022
     * @param paramConsulta
     * @return List<PeDireccionado>
     * @throws Exception
     */
    List<PeDireccionado> consultarListaAfiliado(ParamConsulta paramConsulta) throws Exception;

    /**
     * Metodo para anular los registros direccionados de un afiliado retirado
     *
     * @author idbohorquez
     * @creado 21/04/2023
     * @param obj
     * @throws Exception
     */
    void anularAfiliadoRetirado(PeDireccionado obj) throws Exception;

}
