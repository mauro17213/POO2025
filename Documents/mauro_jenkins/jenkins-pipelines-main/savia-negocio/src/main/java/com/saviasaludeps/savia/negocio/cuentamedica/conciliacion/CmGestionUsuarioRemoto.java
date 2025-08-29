/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGestionUsuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author jperez
 */
public interface CmGestionUsuarioRemoto {

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CmGestionUsuario) cargado
     * @throws java.lang.Exception
     */
    CmGestionUsuario consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CmGestionUsuario)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmGestionUsuario obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CmGestionUsuario)
     * @throws java.lang.Exception
     */
    void actualizar(CmGestionUsuario obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CmGestionUsuario) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmGestionUsuario eliminar(int id) throws Exception;


    /**
     * Metodo que permite consultar la contidad de informes
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    /**
     * Metodo que permite encontrar los informes dado filtros determinados
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmGestionUsuario> consultarLista(ParamConsulta paramConsulta) throws Exception;
}
