/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.tutela;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuRepresentante;
import java.util.List;

/**
 *
 * @author pavacca
 */
public interface TuRepresentanteRemoto {
    
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
    List<TuRepresentante> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (TuRepresentante) cargado
     * @throws java.lang.Exception
     */
    TuRepresentante consultar(int id) throws Exception ;
    
    /**
     * Método para crear una nueva Representante
     *
     * @param obj (TuRepresentante)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(TuRepresentante obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (TuRepresentante)
     * @throws java.lang.Exception
     */
    void actualizar(TuRepresentante obj) throws Exception;
    
    /**
     * Método para eliminar un Representante
     *
     * @param id
     * @return (TuRepresentante) Objetop eliminado
     * @throws java.lang.Exception
     */
    TuRepresentante eliminar(int id) throws Exception;  
    
    /**
     * Consultar lista de registros activos
     * @return
     * @throws Exception 
     */
    List<TuRepresentante> consultarRepresentantesActivos() throws Exception;
}
