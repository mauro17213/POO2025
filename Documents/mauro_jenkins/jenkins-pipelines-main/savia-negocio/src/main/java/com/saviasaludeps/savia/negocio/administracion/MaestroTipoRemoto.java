/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.administracion;

import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Perfil;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface MaestroTipoRemoto {

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
    List<MaestroTipo> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un maestro por ID
     *
     * @param tipo
     * @return
     * @throws java.lang.Exception
     */
    MaestroTipo consultar(String tipo) throws Exception;

    /**
     * Método para actualizar la información de un Tipo Maestro (Perfil)
     *
     * @param obj (Maestros)
     * @throws java.lang.Exception
     */
    void actualizar(MaestroTipo obj) throws Exception;

    /**
     * Consultar MestroTipo padre por tipo hijo
     * @param tipo
     * @return
     * @throws Exception 
     */
    MaestroTipo consultarPadrePorTipo(String tipo) throws Exception;
            
    /**
     * Método para consultar todos los registros
     * @return
     * @throws Exception
     */
    List<MaestroTipo> consultarTodos()throws Exception;
    
    /**
     * Método para consultar todos los registros activos
     * @return
     * @throws Exception
     */
    List<MaestroTipo> consultarActivos()throws Exception;
    
    /**
     * Consultar listado de activos por perfil
     * @param listaPerfiles
     * @return
     * @throws Exception 
     */
    List<MaestroTipo> consultarActivosPorPerfil(List<Perfil> listaPerfiles) throws Exception;

}
