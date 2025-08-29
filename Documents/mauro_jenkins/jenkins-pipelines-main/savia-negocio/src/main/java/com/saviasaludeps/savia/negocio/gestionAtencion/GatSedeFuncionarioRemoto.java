/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.gestionAtencion;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeFuncionario;
import java.util.List;

/**
 *
 * @author acuartas
 */
public interface GatSedeFuncionarioRemoto {

    /**
     * Consulta el objeto dado el id
     *
     * @param id
     * @return
     * @throws Exception
     */
    GatSedeFuncionario consultar(int id) throws Exception;

    /**
     * Inserta el objeto
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(GatSedeFuncionario obj) throws Exception;

    /**
     * Actualiza el objeto
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(GatSedeFuncionario obj) throws Exception;

    /**
     * Elimina el objeto
     *
     * @param id
     * @return
     * @throws Exception
     */
    GatSedeFuncionario eliminar(int id) throws Exception;
    
    /**
     * Lista todos los funcionarios por el id de la sede
     * @param idSede
     * @return
     * @throws Exception 
     */
    List<GatSedeFuncionario> listarPorIdSede(int idSede) throws Exception;
    
    /**
     * Consulta el funcioario de la sede
     * @param idSede
     * @param idUsuario
     * @return
     * @throws Exception 
     */
    GatSedeFuncionario consultarPorSedeYUsuario(int idSede, int idUsuario) throws Exception;
    
    /**
     * Consulta los usuarios en una sede
     * @param idSede
     * @return
     * @throws Exception 
     */
    List<Usuario> consultarIdUsuarios(int idSede) throws Exception;
    
}
