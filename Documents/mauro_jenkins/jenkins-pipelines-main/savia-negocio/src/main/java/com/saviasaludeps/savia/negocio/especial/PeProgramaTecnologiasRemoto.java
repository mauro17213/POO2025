/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.especial;

import com.saviasaludeps.savia.dominio.especial.PeProgramaTecnologia;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author idbohorquez
 */
public interface PeProgramaTecnologiasRemoto {
    
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    List<PeProgramaTecnologia> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Guarda un registro de PeProgramaTecnologias
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(PeProgramaTecnologia obj) throws Exception;

    /**
     *
     * @param idPrograma
     * @return
     * @throws Exception
     */
    List<PeProgramaTecnologia> consultarTecnologiasPrograma(int idPrograma) throws Exception;

    /**
     * Permite establecer un registro como eliminado
     *
     * @param obj
     * @throws Exception
     */
    void eliminar(PeProgramaTecnologia obj) throws Exception;

    /**
     *
     * @param objeto
     * @return
     * @throws Exception
     */
    PeProgramaTecnologia consultarTecnologiaPrograma(PeProgramaTecnologia objeto) throws Exception;

    /**
     *
     * @param obj
     * @throws Exception
     */
    void actualizarMarcaAutomatica(PeProgramaTecnologia obj) throws Exception;

    /**
     * @authot Isaac Bohorquez
     * @fechaCreacion 23/06/2022
     * @param idTecnologias
     * @param idProgramas
     * @return
     * @throws Exception
     */
    List<PeProgramaTecnologia> consultarTecnologiasProgramaMarcacionAutomatica(String idTecnologias, String idProgramas) throws java.lang.Exception;

    /**
     * @authot idbohorquez
     * @fechaCreacion 18/08/2022
     * @param idTecnologias
     * @param idProgramas
     * @return List<PeProgramaTecnologia>
     * @throws java.lang.Exception
     */
    List<PeProgramaTecnologia> consultarTecnologiasProgramaDirecciona(String idTecnologias, String idProgramas) throws java.lang.Exception;
    
    /**
     * @authot idbohorquez
     * @fechaCreacion 18/08/2022
     * @param idTecnologias
     * @param tipoTecnologia
     * @param idProgramas
     * @return PeProgramaTecnologia
     * @throws java.lang.Exception
     */
    PeProgramaTecnologia consultarTecnologiasProgramaDirecciona(Integer idTecnologias, int tipoTecnologia, Integer idProgramas) throws java.lang.Exception;
}
