/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.atencionusuario;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.atencionusuario.AusGrupoUsuario;
import java.util.List;

/**
 *
 * @author jorgePerez
 */
public interface AusGrupoUsuarioRemoto {
    
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
    List<AusGrupoUsuario> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (AusGrupoUsuario) cargado
     * @throws java.lang.Exception
     */
    AusGrupoUsuario consultar(int id) throws Exception;
    
      /**
     * Método para crear una nuevo Juzgado
     *
     * @param per (AusGrupoUsuario)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AusGrupoUsuario per) throws Exception;

    
     /**
     * Permite actualizar la información de un registro
     * @param obj (AusGrupoUsuario)
     * @throws java.lang.Exception
     */
    void actualizar(AusGrupoUsuario obj) throws Exception;
      
    /**
     * Permite eliminar un usuario
     *
     * @param id
     * @return (TuGrupo) Objetop eliminado
     * @throws java.lang.Exception
     */
    AusGrupoUsuario eliminar(int id) throws Exception;

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
    List<AusGrupoUsuario> consultarListaPorGrupo(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consulta el usuario configurado para grupo tipo cierre. Se obtendrá el primer registro activo.
     * @return
     * @throws Exception 
     */
    AusGrupoUsuario consultarUsuarioCierreGrupo(int idGrupo) throws Exception;
    
    /**
     * Consulta la lista de usuarios activos registrados para el grupo ingresado
     * @param idGrupo
     * @return
     * @throws Exception 
     */
    List<AusGrupoUsuario> consultarListaPorGrupo(int idGrupo) throws Exception;
    
}
