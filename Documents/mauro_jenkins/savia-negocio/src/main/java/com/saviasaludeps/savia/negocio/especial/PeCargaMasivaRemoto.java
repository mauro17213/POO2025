/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.especial;

import com.saviasaludeps.savia.dominio.especial.PeCarga;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface PeCargaMasivaRemoto {
    
    /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta Parametros de la consulta de registros
     * @return Lista de registros consultados
     * @throws Exception 
     */
    List<PeCarga> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consulta cantidad de registros para cargas de diagnosticos y tecnologias
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int cantidadListaCargaDiagnosticoTecnologia(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consulta lista de registros para cargas de diagnosticos y tecnologias
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<PeCarga> consultarListaCargaDiagnosticoTecnologia(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Inserta un registro de tipo PeCarga
     * @param obj
     * @return
     * @throws Exception 
     */
    int insertar(PeCarga obj) throws Exception;
    
    /**
     * Actualiza un registro de tipo PeCarga
     * @param obj
     * @throws Exception 
     */
    void actualizar(PeCarga obj) throws Exception;
    
    /**
     * Consulta un registro por id
     * @param id
     * @return
     * @throws Exception 
     */
    PeCarga consultar(int id) throws Exception;
    
    /**
     * Actualizar nombre de archivo almacenado
     * @param obj
     * @throws Exception 
     */
    void actualizarArchivo(PeCarga obj) throws Exception;

    List<PeCarga> consultarCargasSiguientes(int numRegistros);
    
    PeCarga consultarCargasSiguientes();
    
    void actualizarDetalle(PeCarga obj) throws Exception;
}
