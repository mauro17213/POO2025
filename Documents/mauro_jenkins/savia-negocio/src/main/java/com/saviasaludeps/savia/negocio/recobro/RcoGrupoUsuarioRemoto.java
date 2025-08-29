package com.saviasaludeps.savia.negocio.recobro;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.recobro.RcoGrupoUsuario;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public interface RcoGrupoUsuarioRemoto {
    
/**
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<RcoGrupoUsuario> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un maestro por ID
     *
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    RcoGrupoUsuario consultar(int id) throws Exception;

    /**
     * Método para crear un nuev maestro
     *
     * @param obj
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(RcoGrupoUsuario obj) throws Exception;

    /**
     * Método para actualizar la información de un maestro
     *
     * @param obj
     * @throws java.lang.Exception
     */
    void actualizar(RcoGrupoUsuario obj) throws Exception;

    /**
     * Eliminar un maestro
     *
     * @param id
     * @return objeto eliminado
     * @throws java.lang.Exception
     */
    RcoGrupoUsuario eliminar(int id) throws Exception;
    
    /**
     * Lista por id de grupo
     * @param idGrupo
     * @return
     * @throws Exception 
     */
    List<RcoGrupoUsuario> consultarListaPorIdGrupo(int idGrupo) throws Exception;
     
}

