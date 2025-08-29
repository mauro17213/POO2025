/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.judicial;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.judicial.GjProcesoTercero;
import java.util.List;

/**
 *
 * @author jperezn
 */
public interface GjProcesoTerceroRemoto {
    
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
    List<GjProcesoTercero> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (GjProcesoTercero) cargado
     * @throws java.lang.Exception
     */
    GjProcesoTercero consultar(int id) throws Exception;
    
      /**
     * Método para crear una nueva Empresa
     *
     * @param per (GjProcesoTercero)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(GjProcesoTercero per) throws Exception;

    
     /**
     * Método para actualizar la información de un registro
     * @param obj (GjProcesoTercero)
     * @throws java.lang.Exception
     */
      void actualizar(GjProcesoTercero obj) throws Exception;
      

     /**
     * Permite eliminar un usuario
     *
     * @param id
     * @return (TuGrupo) Objetop eliminado
     * @throws java.lang.Exception
     */
    GjProcesoTercero eliminar(int id) throws Exception;

    /**
     * Permite consultar por id grupo
     * @param idProceso
     * @return
     * @throws Exception 
     */
    List<GjProcesoTercero> consultarListaPorIdProceso(int idProceso) throws Exception;
}
