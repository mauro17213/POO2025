/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.anticipo;

import com.saviasaludeps.savia.dominio.anticipo.AntAnticipoItem;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface AnticipoItemRemoto {

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
    List<AntAnticipoItem> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un anticipo por ID
     *
     * @param id
     * @return (Anticipos) cargado
     * @throws java.lang.Exception
     */
    AntAnticipoItem consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo anticipo
     *
     * @param per (AntAnticipo)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AntAnticipoItem per) throws Exception;
    
    /**
     * Método para actualizar la información de una anticipo
     *
     * @param per (AntAnticipo)
     * @throws java.lang.Exception
     */
    void actualizar(AntAnticipoItem per) throws Exception;
    
    /**
     * Método para actualizar la información de una anticipo
     *
     * @param per (AntAnticipo)
     * @throws java.lang.Exception
     */
    void actualizarBorradoLogico(AntAnticipoItem per) throws Exception;
    
    /**
     * Método para actualizar la información de una anticipo
     *
     * @param per (AntAnticipo)
     * @throws java.lang.Exception
     */
    void actualizarValorTecnologiaPagada(AntAnticipoItem per) throws Exception;
    
    /**
     * Consultar lista de registros
     *
     * @param idAnticipo
     * @return
     * @throws Exception
     */
    List<AntAnticipoItem> consultarItemPorAnticipoId(int idAnticipo) throws Exception;
    
    /**
     * Consultar lista de registros
     *
     * @param idAnticipo
     * @param idTecnologia
     * @return
     * @throws Exception
     */
    AntAnticipoItem consultarItemAnticipoYTecnologia(int idAnticipo, int idTecnologia) throws Exception;
    
     
}

