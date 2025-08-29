/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.gestionAtencion;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTaquillaFuncionario;
import java.util.List;

/**
 *
 * @author acuartas
 */
public interface GatTaquillaFuncionarioRemoto {
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
    List<GatTaquillaFuncionario> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    GatTaquillaFuncionario consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(GatTaquillaFuncionario obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(GatTaquillaFuncionario obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    GatTaquillaFuncionario eliminar(int id) throws Exception;
}
