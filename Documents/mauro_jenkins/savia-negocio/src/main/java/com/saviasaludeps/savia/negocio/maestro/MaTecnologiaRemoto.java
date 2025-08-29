package com.saviasaludeps.savia.negocio.maestro;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author AlexanderDiaz
 */
public interface MaTecnologiaRemoto {

    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<MaTecnologia> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<MaTecnologia> consultarListaTodos(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (ReConciliacionRecobros) cargado
     * @throws java.lang.Exception
     */
    MaTecnologia consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (ReConciliacionRecobros)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(MaTecnologia obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (AfAfiliados)
     * @throws java.lang.Exception
     */
    void actualizar(MaTecnologia obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (AfAfiliados) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    MaTecnologia eliminar(int id) throws Exception;

    /**
     * Consultar todos los registros
     *
     * @return
     * @throws Exception
     */
    List<MaTecnologia> consultarTodos() throws Exception;

    /**
     * Consultar todos los registros, pero solo se cargan algunos valores en los
     * objetos
     *
     * @return
     * @throws Exception
     */
    List<MaTecnologia> consultarTodosCorto() throws Exception;

    /**
     * Consultar un registro por su código
     *
     * @param codigo
     * @return
     * @throws Exception
     */
    MaTecnologia consultarPorCodigo(String codigo) throws Exception;

    /**
     * Consulta de cantidad de registros en una lista
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    int consultarCantidadListaBuscador(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consultar lista de registros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    List<MaTecnologia> consultarListaBuscador(ParamConsulta paramConsulta) throws Exception;

    /**
     * Obtener las tecnologias en un hash identificados por código propio
     *
     * @return
     * @throws Exception
     */
    HashMap<String, MaTecnologia> consultarHash() throws Exception;

    /**
     * Consulta todos solo con la informacion necesaria del singleton
     *
     * @return
     * @throws Exception
     */
    List<MaTecnologia> consultarTodoSingleton() throws Exception;

}
