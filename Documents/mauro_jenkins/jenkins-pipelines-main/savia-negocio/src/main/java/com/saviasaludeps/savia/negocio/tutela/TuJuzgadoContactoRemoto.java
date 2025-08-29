/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.tutela;

import com.saviasaludeps.savia.dominio.tutela.TuJuzgadoContacto;
import java.util.List;

/**
 *
 * @author pavacca
 */
public interface TuJuzgadoContactoRemoto {
   /**
     * Método para consultar un registro por ID
     * @param id
     * @return (TuJuzgadoContacto) cargado
     * @throws java.lang.Exception
     */
    TuJuzgadoContacto consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo TuJuzgadoContacto
     *
     * @param per (TuJuzgadoContacto)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(TuJuzgadoContacto per) throws Exception;

    
    /**
     * Método para actualizar la información de un registro
     * @param obj (TuJuzgado)
     * @throws java.lang.Exception
     */
      void actualizar(TuJuzgadoContacto obj) throws Exception;
      
    /**
     * Método para eliminar un TuJuzgadoContacto
     *
     * @param id
     * @return (TuJuzgadoContacto) Objetop eliminado
     * @throws java.lang.Exception
     */
    TuJuzgadoContacto eliminar(int id) throws Exception; 
    
    /**
     * Metodo para consultar los contactos asociados a una juzgado
     * @param idTuJuzgado
     * @return
     * @throws Exception 
     */
    List<TuJuzgadoContacto> consultarListaContactos(int idTuJuzgado) throws Exception;
}
