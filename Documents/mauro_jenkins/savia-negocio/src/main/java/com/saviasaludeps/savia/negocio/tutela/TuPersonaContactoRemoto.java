/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.tutela;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContactoConsulta;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuPersonaContacto;
import java.util.List;

/**
 *
 * @author jperezn
 */
public interface TuPersonaContactoRemoto {
    
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
    List<TuPersonaContacto> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (TuJuzgado) cargado
     * @throws java.lang.Exception
     */
    TuPersonaContacto consultar(int id) throws Exception;
    
      /**
     * Crea un nuevo registro
     *
     * @param per (Usuarios)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(TuPersonaContacto per) throws Exception;

    
     /**
     * Método para actualizar la información de un registro
     * @param obj (TuJuzgado)
     * @throws java.lang.Exception
     */
      void actualizar(TuPersonaContacto obj) throws Exception;
      
         /**
     * Método para eliminar un usuario
     *
     * @param id
     * @return (Usuarios) Objetop eliminado
     * @throws java.lang.Exception
     */
    TuPersonaContacto eliminar(int id) throws Exception;

/**
     * Metodo para consultar los contactos asociados a una Persona
     * @param idPersona
     * @return
     * @throws Exception 
     */
    List<TuPersonaContacto> consultarListaContactos(int idPersona) throws Exception;
    
    /**
     * Consultar todas las Personas
     * @param idAsegAfilido id del aseg afiliado
     * @param numeroExcluidos para no tener los numero en cuenta
     * @return
     * @throws Exception 
     */
    List<AsegAfiliadoContactoConsulta> consultarListaContactosPorAsegAfiliado(int idAsegAfilido, String numeroExcluidos) throws Exception;
}
