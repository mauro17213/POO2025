/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.autorizacion;

import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudCargaSuceso;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface AuNoSolicitudCargaSucesoRemoto {

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
    List<AuNoSolicitudCargaSuceso> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadSucesoLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<AuNoSolicitudCargaSuceso> consultarSucesoLista(ParamConsulta paramConsulta) throws Exception;
    
    
    /**
     * Método para consultar un anticipo por ID
     *
     * @param id
     * @return (Anticipos) cargado
     * @throws java.lang.Exception
     */
    AuNoSolicitudCargaSuceso consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo anticipo
     *
     * @param per (AntAnticipo)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AuNoSolicitudCargaSuceso per) throws Exception; 
    
    /**
     * Método para crear una nuevo anticipo
     *
     * @param per (AntAnticipo)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    boolean insertarAuNoSolicitudCargaSucesos(List<AuNoSolicitudCargaSuceso> per) throws Exception; 

}
