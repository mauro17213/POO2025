package com.saviasaludeps.savia.negocio.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.JrGestion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface JrGestionRemoto {
    /**
     * Método para retronar el listaod de registros
     *
     * @param paramConsulta: Contiene los listros de la consulta
     * @return (List<JrGestion>) lista de registros cargados
     * @throws Exception
     */
    List<JrGestion> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar la cantidad de registros de la lista
     *
     * @param paramConsulta: Contiene los listros de la consulta
     * @return (int) total de registros
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (JrGestion) cargado
     * @throws java.lang.Exception
     */
    JrGestion consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (JrGestion)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(JrGestion obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (JrGestion)
     * @throws java.lang.Exception
     */
    void actualizar(JrGestion obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (JrGestion) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    JrGestion eliminar(int id) throws Exception;

}
