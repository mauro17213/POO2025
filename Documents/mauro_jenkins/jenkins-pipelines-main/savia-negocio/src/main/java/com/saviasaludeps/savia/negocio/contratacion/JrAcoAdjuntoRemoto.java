package com.saviasaludeps.savia.negocio.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.JrAcoAdjunto;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface JrAcoAdjuntoRemoto {

    /**
     * Método para retronar el listaod de registros
     *
     * @param paramConsulta: Contiene los listros de la consulta
     * @return (List<JrAcoAdjunto>) lista de registros cargados
     * @throws Exception
     */
    List<JrAcoAdjunto> consultarLista(ParamConsulta paramConsulta) throws Exception;

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
     * @return (JrAcoAdjunto) cargado
     * @throws java.lang.Exception
     */
    JrAcoAdjunto consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (JrAcoAdjunto)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(JrAcoAdjunto obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (JrAcoAdjunto)
     * @throws java.lang.Exception
     */
    void actualizar(JrAcoAdjunto obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (JrAcoAdjunto) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    JrAcoAdjunto eliminar(int id) throws Exception;

}
