/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.atencionusuario;

import com.saviasaludeps.savia.dominio.atencionusuario.AusGrupo;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author jorgePerez
 */
public interface AusGrupoRemoto {
    
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
    List<AusGrupo> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<AusGrupo> consultarTodos() throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (AusGrupo) cargado
     * @throws java.lang.Exception
     */
    AusGrupo consultar(int id) throws Exception;
    
      /**
     * Método para crear una nuevo Juzgado
     *
     * @param per (AusGrupo)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AusGrupo per) throws Exception;

    
     /**
     * Permite actualizar la información de un registro
     * @param obj (AusGrupo)
     * @throws java.lang.Exception
     */
      void actualizar(AusGrupo obj) throws Exception;
      
    /**
     * Permite eliminar un usuario
     *
     * @param id
     * @return (AusGrupo) Objetop eliminado
     * @throws java.lang.Exception
     */
    AusGrupo eliminar(int id) throws Exception;

}
