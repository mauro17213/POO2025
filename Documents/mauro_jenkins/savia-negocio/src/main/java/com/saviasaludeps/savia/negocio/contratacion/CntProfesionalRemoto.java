/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.contratacion;

import com.saviasaludeps.savia.dominio.contratacion.CntProfesional;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface CntProfesionalRemoto {

    /**
     * Consulta un registro por el tipodocumento y el numero de documento
     *
     * @param tipodocumento
     * @param numeroDocumento
     * @return
     * @throws Exception
     */
    CntProfesional consultarNumDocumento(Integer tipodocumento, String numeroDocumento) throws Exception;
    
    public CntProfesional consultar(String tipodocumento, String numeroDocumento) throws Exception;

    int insertar(CntProfesional obj) throws Exception;
    
    void actualizar(CntProfesional obj) throws Exception;
    
    CntProfesional eliminar(int id) throws Exception;

    /**
     * Consulta un profesional dependiendo de los parametros
     *
     * @param paramConsulta
     * @return
     * @throws Exception
     */
    CntProfesional consultarProfesional(ParamConsulta paramConsulta) throws Exception;

    CntProfesional consultar(int id) throws Exception;

    /**
     * Consulta lista completa con datos minimos
     *
     * @return
     * @throws Exception
     */
    List<CntProfesional> consultarTodos() throws Exception;
    
    /**
     * Actualiza unicamente el registro medico
     * @param obj
     * @throws Exception 
     */
    void actualizarRegistroMedico(CntProfesional obj) throws Exception;
    
}
