/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.tutela;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuGrupoUsuario;
import java.util.List;

/**
 *
 * @author jorgePerez
 */
public interface TuGrupoUsuarioRemoto {
    
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
    List<TuGrupoUsuario> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (TuGrupo) cargado
     * @throws java.lang.Exception
     */
    TuGrupoUsuario consultar(int id) throws Exception;
    
      /**
     * Método para crear una nuevo Juzgado
     *
     * @param per (TuGrupo)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(TuGrupoUsuario per) throws Exception;

    
     /**
     * Permite actualizar la información de un registro
     * @param obj (TuGrupo)
     * @throws java.lang.Exception
     */
    void actualizar(TuGrupoUsuario obj) throws Exception;
      
    /**
     * Permite eliminar un usuario
     *
     * @param id
     * @return (TuGrupo) Objetop eliminado
     * @throws java.lang.Exception
     */
    TuGrupoUsuario eliminar(int id) throws Exception;

    /**
     * Permite contabilizar usuarios por grupo
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadListaPorGrupo(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite obtener usaurios por grupo
     * @param paramConsulta: paramConsulta.getParametroConsulta1() idgrupo, paramConsulta.getParametroConsulta2(): tipo usuario,
     *  paramConsulta.getParametroConsulta3(): activo, paramConsulta.getParametroConsulta4(): estado grupo,
     *  paramConsulta.getParametroConsulta5() idusuario
     * @return
     * @throws Exception 
     */
    List<TuGrupoUsuario> consultarListaPorGrupo(ParamConsulta paramConsulta) throws Exception;

}
