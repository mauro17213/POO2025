/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmPagoTransaccion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.HashMap;
import java.util.List;

public interface CmPagoTransaccionRemoto {

    /**
     * Método para consultar un usuario por ID
     *
     * @param id
     * @return (Usuarios) cargado
     * @throws java.lang.Exception
     */
    CmPagoTransaccion consultar(int id) throws Exception;

    /**
     * Método para crear una nueva Empresa
     *
     * @param per (Usuarios)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmPagoTransaccion per) throws Exception;

    /**
     * Método para actualizar la información de una Empresa
     *
     * @param per (Usuarios)
     * @throws java.lang.Exception
     */
    void actualizar(CmPagoTransaccion per) throws Exception;

    /**
     * Método para eliminar un usuario
     *
     * @param id
     * @return (Usuarios) Objetop eliminado
     * @throws java.lang.Exception
     */
    CmPagoTransaccion eliminar(int id) throws Exception;

}
