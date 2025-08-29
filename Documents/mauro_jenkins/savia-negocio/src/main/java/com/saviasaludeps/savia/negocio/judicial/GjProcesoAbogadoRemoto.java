/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.judicial;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.judicial.GjProcesoAbogado;
import java.util.List;

/**
 *
 * @author bsgomez
 */
public interface GjProcesoAbogadoRemoto {
    
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
    List<GjProcesoAbogado> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (GjProcesoAbogado) cargado
     * @throws java.lang.Exception
     */
    GjProcesoAbogado consultar(int id) throws Exception;
    
      /**
     * Método para crear una nueva Empresa
     *
     * @param per (GjProcesoAbogado)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(GjProcesoAbogado per) throws Exception;

    
     /**
     * Método para actualizar la información de un registro
     * @param obj (GjProcesoAbogado)
     * @throws java.lang.Exception
     */
      void actualizar(GjProcesoAbogado obj) throws Exception;
      

     /**
     * Permite eliminar un usuario
     *
     * @param id
     * @return (TuGrupo) Objetop eliminado
     * @throws java.lang.Exception
     */
    GjProcesoAbogado eliminar(int id) throws Exception;

    /**
     * Permite consultar por id grupo
     * @param idProceso
     * @return
     * @throws Exception 
     */
    List<GjProcesoAbogado> consultarListaPorIdProceso(int idProceso) throws Exception;
    
    /**
     * Permite consultar por id grupo
     * @param idAbogado
     * @return
     * @throws Exception 
     */
    List<GjProcesoAbogado> consultarListaPorIdAbogado(int idAbogado) throws Exception;
}
