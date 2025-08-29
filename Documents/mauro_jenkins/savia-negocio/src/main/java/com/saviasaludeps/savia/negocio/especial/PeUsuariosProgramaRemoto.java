/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.especial;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.especial.PeUsuariosPrograma;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public interface PeUsuariosProgramaRemoto {

    /**
     * Consulta los usuarios responsables(PeUsuariosPrograma) de un
     * Programa(PePrograma)
     *
     * @param idPrograma
     * @return
     * @throws Exception
     */
    List<PeUsuariosPrograma> consultarPorPrograma(int idPrograma) throws Exception;

    /**
     * Consulta los usuario responsables de un programa que esten activos
     *
     * @param idPrograma
     * @return
     * @throws java.lang.Exception
     */
    List<PeUsuariosPrograma> consultarPorProgramaActivo(int idPrograma) throws Exception;

    /**
     * Inserta un registro de tipo PeUsuariosProgramas
     *
     * @param obj
     * @return
     * @throws Exception
     */
    int insertar(PeUsuariosPrograma obj) throws Exception;

    /**
     * Actualiza un registro de tipo PeUsuariosProgramas
     *
     * @param obj
     * @throws Exception
     */
    void actualizar(PeUsuariosPrograma obj) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    PeUsuariosPrograma eliminar(int id) throws Exception;

    /**
     * Función encargada de consultar todos los usuarios que estan como
     * responsable en los programas especiales.
     *
     * @return
     * @throws Exception
     */
    List<PeUsuariosPrograma> consultarTodos() throws Exception;

    /**
     * Se valida si el usaurio esta asignado como usuario responsable del
     * programa
     *
     * @param idUsuario
     * @param idPrograma
     * @return
     * @throws Exception
     */
    boolean isUsuarioResponsable(Integer idUsuario, Integer idPrograma) throws Exception;

    /**
     * Metodo para cambiar el estado de un usuario en todos los programas en los
     * que está como responsable
     *
     * @param obj
     * @throws Exception
     */
    void cambiarEstadoUsuarioProgramas(PeUsuariosPrograma obj) throws Exception;
    
    /**
     * Metodo para cambiar el estado de un usuario en todos los programas en los
     * que está como responsable
     *
     * @param usuario
     * @throws Exception
     */
    public void cambiarEstadoUsuario(Usuario usuario) throws Exception;

}
