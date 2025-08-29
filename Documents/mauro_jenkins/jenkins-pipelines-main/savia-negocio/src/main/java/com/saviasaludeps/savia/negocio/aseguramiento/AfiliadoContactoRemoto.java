package com.saviasaludeps.savia.negocio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContacto;
import java.util.List;

public interface AfiliadoContactoRemoto {

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
     * @param paramConsulta Parametros de la consulta de registros
     * @return Lista de registros consultados
     * @throws Exception
     */
    List<AsegAfiliadoContacto> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (AsegAfiliadoContacto) objeto consultadp
     * @throws java.lang.Exception
     */
    AsegAfiliadoContacto consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (AsegAfiliadoContacto) registro a crear
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AsegAfiliadoContacto obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (AsegAfiliadoContacto)
     * @throws java.lang.Exception
     */
    void actualizar(AsegAfiliadoContacto obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (AsegAfiliadoContacto) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    AsegAfiliadoContacto eliminar(int id) throws Exception;

    /**
     * Consultar todas las AsegAfiliadoContacto
     *
     * @return
     * @throws Exception
     */
    List<AsegAfiliadoContacto> consultarTodos() throws Exception;

    /**
     * Consulta el último contacto registrado para un afiliado específico y un
     * tipo de contacto.
     *
     * @param idAfiliado ID del afiliado
     * @param tipoContacto Tipo de contacto
     * @return Último AsegAfiliadoContacto que coincide con el afiliado y tipo,
     * o null si no existe
     * @throws Exception
     */
    AsegAfiliadoContacto consultarUltimoPorAfiliadoYTipo(int idAfiliado, String tipoContacto) throws Exception;
}
