/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoCertificado;
import com.saviasaludeps.savia.dominio.aseguramiento.CertificadoAfiliacion;
import java.util.List;

public interface AfiliadoCertificadoRemoto {
    
    /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta Parametros de la consulta de registros
     * @return Lista de registros consultados
     * @throws Exception 
     */
    List<AsegAfiliadoCertificado> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (AsegAfiliadoCertificado) objeto consultadp
     * @throws java.lang.Exception
     */
    AsegAfiliadoCertificado consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (AsegAfiliadoCertificado) registro a crear
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(AsegAfiliadoCertificado obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (AsegAfiliadoCertificado)
     * @throws java.lang.Exception
     */
    void actualizar(AsegAfiliadoCertificado obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (AsegAfiliadoCertificado) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    AsegAfiliadoCertificado eliminar(int id) throws Exception;
    
    /**
     * Consultar todas las AsegAfiliadoCertificado
     * @return
     * @throws Exception 
     */
    List<AsegAfiliadoCertificado> consultarTodos() throws Exception;
    
    /**
     * 
     * @param idAfiliado
     * @param ruta
     * @return
     * @throws Exception 
     */
    List<CertificadoAfiliacion> consultarCertificadoAfiliacion(String idAfiliado,String ruta, String nombreArchivo) throws Exception;

}
