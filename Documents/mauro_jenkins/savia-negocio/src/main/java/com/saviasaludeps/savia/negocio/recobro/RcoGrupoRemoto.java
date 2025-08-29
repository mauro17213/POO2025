package com.saviasaludeps.savia.negocio.recobro;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.recobro.RcoGrupo;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public interface RcoGrupoRemoto {
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
    List<RcoGrupo> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un maestro por ID
     *
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    RcoGrupo consultar(int id) throws Exception;

    /**
     * Método para crear un nuev maestro
     *
     * @param obj
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(RcoGrupo obj) throws Exception;

    /**
     * Método para actualizar la información de un maestro
     *
     * @param obj
     * @throws java.lang.Exception
     */
    void actualizar(RcoGrupo obj) throws Exception;

    /**
     * Eliminar un maestro
     *
     * @param id
     * @return objeto eliminado
     * @throws java.lang.Exception
     */
    RcoGrupo eliminar(int id) throws Exception;

    
}

    

