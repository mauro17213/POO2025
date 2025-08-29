package com.saviasaludeps.savia.negocio.informe;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.informe.InfGrupo;
import com.saviasaludeps.savia.dominio.informe.InfInforme;
import java.util.List;

public interface InformeGrupoRemoto {

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
    List<InfGrupo> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Metodo para listar informes
     *
     * @return
     * @throws java.lang.Exception
     */
    List<InfGrupo> consultarTodos() throws Exception;

    /**
     * Consultar un informe por id
     *
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    InfGrupo consultar(int id) throws Exception;

    /**
     * Guardar un informe
     *
     * @param informe
     * @return
     * @throws java.lang.Exception
     */
    int insertar(InfGrupo informe) throws Exception;

    /**
     * Modificar un informe
     *
     * @param informe
     * @throws java.lang.Exception
     */
    void actualizar(InfGrupo informe) throws Exception;

    /**
     * Borrar un informe por id
     *
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    InfGrupo eliminar(int id) throws Exception;

}
