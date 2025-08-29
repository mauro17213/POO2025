/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.mipres.contingencia;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.mipres.contingencia.MpcPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.contingencia.MpcProgramacionEntrega;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface EntregaRemoto {

    /**
     * Consultar lista de registros
     *
     * @param idPrescripcion
     * @return
     * @throws Exception
     */
    List<MpcProgramacionEntrega> consultarLista(int idPrescripcion) throws Exception;

    /**
     * Método para consultar un usuario por ID
     *
     * @param id
     * @return (Usuarios) cargado
     * @throws java.lang.Exception
     */
    MpcProgramacionEntrega consultar(int id) throws Exception;

    /**
     * Método para crear una nueva Empresa
     *
     * @param per (Usuarios)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(MpcProgramacionEntrega per) throws Exception;

    /**
     * Método para eliminar un usuario
     *
     * @param id
     * @return (Usuarios) Objetop eliminado
     * @throws java.lang.Exception
     */
    MpcProgramacionEntrega eliminar(int id) throws Exception;
    
}
