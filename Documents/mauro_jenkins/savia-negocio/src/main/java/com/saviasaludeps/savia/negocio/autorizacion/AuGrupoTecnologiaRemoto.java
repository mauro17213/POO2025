/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuGrupoTecnologia;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public interface AuGrupoTecnologiaRemoto {

    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    AuGrupoTecnologia consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(AuGrupoTecnologia obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(AuGrupoTecnologia obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    AuGrupoTecnologia eliminar(int id) throws Exception;

    /**
     * Listar por Id grupo
     *
     * @param idGrupo
     * @return
     * @throws Exception
     */
    List<AuGrupoTecnologia> consultarListaPorIdGrupo(int idGrupo) throws Exception;

    /**
     * Cuenta la cantidad de lista dado unos parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Lista dados unos parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<AuGrupoTecnologia> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Valida la existencia de una tecnologia
     *
     * @param idTecnologia
     * @param idGrupo
     * @return
     * @throws Exception
     */
    boolean validarTecnologia(int idTecnologia, int tipoTecnologia, int idGrupo) throws Exception;

    /**
     * Valida si la tecnoliga se encuentra en grupos aprueba por ambito
     *
     * @param idTecnologia
     * @param tipoTecnologia
     * @param grupos
     * @param ambito
     * @return
     * @throws Exception
     */
    boolean validarTecnologiaGrupoAprueba(int idTecnologia, int tipoTecnologia, String grupos, String ambito) throws Exception;

    /**
     *
     * @param idTecnologia
     * @param tipoTecnologia
     * @param idGrupo
     * @param idUsuario
     * @return
     * @throws Exception
     */
    boolean validarTecnologiaGrupoTipoUsuario(int idTecnologia, int tipoTecnologia, int idGrupo, int idUsuario) throws Exception;

    /**
     * Valida
     *
     * @param idTecnologia
     * @param tipoAuditor
     * @return
     * @throws Exception
     */
    boolean validarAuditor(int idTecnologia, String tipoAuditor) throws Exception;

    /**
     * valida si la tecnologia estan marcados como aplica seguimiento, en grupo diferente
     *
     * @param idTecnologia
     * @param tipoTecnologia
     * @param idGrupo
     * @return
     * @throws Exception
     */
    boolean validarAplicaSeguimiento(int idTecnologia, int tipoTecnologia, int idGrupo) throws Exception;

    /**
     * tecnologia que aplica para seguimiento
     *
     * @param idTecnologia
     * @param tipoTecnologia
     * @return
     * @throws Exception
     */
    AuGrupoTecnologia tecnologiaAplicaSeguimiento(int idTecnologia, int tipoTecnologia) throws Exception;

}
