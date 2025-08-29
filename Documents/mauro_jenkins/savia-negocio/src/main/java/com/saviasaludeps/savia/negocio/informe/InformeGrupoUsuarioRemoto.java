package com.saviasaludeps.savia.negocio.informe;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.informe.InfGrupo;
import com.saviasaludeps.savia.dominio.informe.InfGrupoUsuario;
import java.util.List;

public interface InformeGrupoUsuarioRemoto {

    /**
     * Metodo para contar listar informes
     *
     * @param paramConsulta
     * @return
     * @throws java.lang.Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Metodo para listar informes
     *
     * @param paramConsulta
     * @return
     * @throws java.lang.Exception
     */
    List<InfGrupoUsuario> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar un informe por id
     *
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    InfGrupoUsuario consultar(int id) throws Exception;

    /**
     * Guardar un informe
     *
     * @param informe
     * @return
     * @throws java.lang.Exception
     */
    int insertar(InfGrupoUsuario informe) throws Exception;

    /**
     * Borrar un informe por id
     *
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    InfGrupoUsuario eliminar(int id) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    List<InfGrupo> consultarListaDeGruposPorUsuario(Integer id) throws Exception;
    
    /**
     * Lista los usuarios dde un grupo
     * @param idGrupo
     * @return
     * @throws Exception 
     */
    List<InfGrupoUsuario> consultarPorIdGrupo(Integer idGrupo) throws Exception;

}
