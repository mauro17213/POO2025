/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.mipres.contingencia;

import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.mipres.contingencia.MpcPrescripcion;
import com.saviasaludeps.savia.dominio.mipres.contingencia.MpcPrescripcionHistorico;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface PrescripcionRemoto {

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
    List<MpcPrescripcion> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * Método para consultar un usuario por ID
     *
     * @param id
     * @return (Usuarios) cargado
     * @throws java.lang.Exception
     */
    MpcPrescripcion consultar(int id) throws Exception;

    /**
     * Método para consultar un usuario por ID
     *
     * @param id
     * @return (Usuarios) cargado
     * @throws java.lang.Exception
     */
    MpcPrescripcionHistorico consultarH(int id) throws Exception;

    MpcPrescripcionHistorico consultarHistorico(int id) throws Exception;

    boolean consultarHis(int id) throws Exception;

    /**
     * Método para crear una nueva Empresa
     *
     * @param per (Usuarios)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(MpcPrescripcion per) throws Exception;

    /**
     * Método para actualizar la información de una Empresa
     *
     * @param per (Usuarios)
     * @throws java.lang.Exception
     */
    void actualizar(MpcPrescripcion per) throws Exception;

    void insertarHistorico(MpcPrescripcionHistorico per) throws Exception;

    /**
     * Médodo para matricular direccionamiento
     *
     * @param per
     * @throws Exception
     */
    void direccionar(MpcPrescripcion per) throws Exception;

    /**
     * Método para eliminar un usuario
     *
     * @param id
     * @return (Usuarios) Objetop eliminado
     * @throws java.lang.Exception
     */
    MpcPrescripcion eliminar(int id) throws Exception;

}
