/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.atencionusuario;

import com.saviasaludeps.savia.dominio.atencionusuario.AusSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public interface AusSedeRemoto {
    
    /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<AusSede> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (AusSede) cargado
     * @throws java.lang.Exception
     */
    AusSede consultar(int id) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param idUbicacion
     * @param idempresa
     * @return
     * @throws Exception 
     */
    List<AusSede> consultarListaPorUbicacion(Integer idUbicacion, Integer idempresa) throws Exception;
    
}
