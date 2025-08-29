/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.administracion;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author rpalacic
 */
@Remote
public interface EmpresaRemoto {
    
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
    List<Empresa> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar una empresa por ID
     * @param id
     * @return (Empresas) objeto consultado
     * @throws java.lang.Exception 
     */
    Empresa consultar(int id)throws Exception;
    
    /**
     * Método para crear una nueva Empresa
     * @param obj (Empresas)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(Empresa obj)throws Exception;
    
    /**
     * Método para actualizar la información de una Empresa
     * @param obj (Empresas) Objeto actualizado
     * @throws java.lang.Exception
     */
    void actualizar(Empresa obj)throws Exception;
    
    /**
     * Método para eliminar una Empresa
     * @param id
     * @return (Empresa) objeto eliminado
     * @throws java.lang.Exception
     */
    Empresa eliminar(int id)throws Exception;
    
    /**
     * Método para consultar todas las Empresas activas.
     * @return (List) objetos consultados
     * @throws java.lang.Exception 
     */
    List<Empresa> consultarActivas()throws Exception;
    
    /**
     * Método para consultar una empresa por ID del Prestador
     * @param idPrestador
     * @return (Empresas) objeto consultado
     * @throws java.lang.Exception 
     */
    Empresa consultarPorPrestador(int idPrestador)throws Exception;
    
}
