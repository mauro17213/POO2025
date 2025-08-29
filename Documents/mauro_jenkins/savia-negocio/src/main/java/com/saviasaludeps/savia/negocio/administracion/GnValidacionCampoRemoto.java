/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.administracion;

import com.saviasaludeps.savia.dominio.administracion.GnValidacionCampo;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public interface GnValidacionCampoRemoto {
    
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
    List<GnValidacionCampo> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    GnValidacionCampo consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(GnValidacionCampo obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(GnValidacionCampo obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    GnValidacionCampo eliminar(int id) throws Exception;
    
    /**
     * Lista por tipo
     * @param tipo
     * @return
     * @throws Exception 
     */
    List<GnValidacionCampo> consultarPorTipo(String tipo) throws Exception;
    
    /**
     * Lista todas las validaciones
     * @return
     * @throws Exception 
     */
    List<GnValidacionCampo> listarTodas() throws Exception;
    
}
