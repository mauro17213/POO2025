/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegTraslado;
import java.util.List;

public interface TrasladoRemoto {
    
    /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<AsegTraslado> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (AsegTraslados) cargado
     * @throws java.lang.Exception
     */
    AsegTraslado consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (AsegTraslados)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AsegTraslado obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (AsegTraslados)
     * @throws java.lang.Exception
     */
    void actualizar(AsegTraslado obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (AsegTraslados) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    AsegTraslado eliminar(int id) throws Exception;
    
    /**
     * Consultar todos las Traslados
     * @return
     * @throws Exception 
     */
    List<AsegTraslado> consultarTodos() throws Exception;

    /**
     * Método que permite consultar el traslado de un afiliado por su tipo de documento y numero de documento
     * @param tipodocumento
     * @param numeroDocumento
     * @return
     * @throws Exception 
     */
    AsegTraslado consultar(Integer tipodocumento,String numeroDocumento) throws Exception;
    
    /**
     * Método para consultar un registro por el Id de Afiliado
     * @param idAfiliado
     * @return (AsegTraslados) cargado
     * @throws java.lang.Exception
     */
    AsegTraslado consultarPorAfiliado(int idAfiliado) throws Exception;
    
}
