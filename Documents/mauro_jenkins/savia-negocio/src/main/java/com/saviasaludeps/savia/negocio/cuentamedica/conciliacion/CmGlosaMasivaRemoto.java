/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaMasivaN;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author jeperez
 */
public interface CmGlosaMasivaRemoto {

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
    List<CmGlosaMasivaN> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (Personas) cargado
     * @throws java.lang.Exception
     */
    CmGlosaMasivaN consultar(int id) throws Exception;


    /**
     * Permite insertar una glosa
     * @param obj
     * @return
     * @throws Exception 
     */
    public int insertar(CmGlosaMasivaN obj) throws Exception;
    
     
    /**
     * Permite actualizar campos de glosa masiva
     * @param obj
     * @throws Exception 
     */
    public void actualizar(CmGlosaMasivaN obj) throws Exception ;
   
    
}
