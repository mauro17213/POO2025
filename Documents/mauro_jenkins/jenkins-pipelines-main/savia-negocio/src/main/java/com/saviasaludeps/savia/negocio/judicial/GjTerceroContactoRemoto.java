/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.judicial;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContactoConsulta;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.judicial.GjTerceroContacto;
import java.util.List;

/**
 *
 * @author jperezn
 */
public interface GjTerceroContactoRemoto {
    
    /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta: paramConsulta1:numeroDocumento, paramConsulta2:idpersona
     * @return
     * @throws Exception 
     */
    List<GjTerceroContacto> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (TuJuzgado) cargado
     * @throws java.lang.Exception
     */
    GjTerceroContacto consultar(int id) throws Exception;
    
      /**
     * Crea un nuevo registro
     *
     * @param per (Usuarios)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(GjTerceroContacto per) throws Exception;

    
     /**
     * Método para actualizar la información de un registro
     * @param obj (TuJuzgado)
     * @throws java.lang.Exception
     */
      void actualizar(GjTerceroContacto obj) throws Exception;
      
         /**
     * Método para eliminar un usuario
     *
     * @param id
     * @return (Usuarios) Objetop eliminado
     * @throws java.lang.Exception
     */
    GjTerceroContacto eliminar(int id) throws Exception;

/**
     * Metodo para consultar los contactos asociados a una Persona
     * @param idTercero
     * @return
     * @throws Exception 
     */
    List<GjTerceroContacto> consultarListaContactos(int idTercero) throws Exception;
    
    
    /**
     * Metodo para consultar los contactos asociados a una Persona
     * @param idAfiliado
     * @param numerosExcluidos
     * @return
     * @throws Exception 
     */
    List<AsegAfiliadoContactoConsulta> consultarListaContactosAfiliado(int idAfiliado, String numerosExcluidos) throws Exception;
}
