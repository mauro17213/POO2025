/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmPago;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface CmPagoRemoto {

    /**
     * Método para consultar un usuario por ID
     *
     * @param id
     * @return (Usuarios) cargado
     * @throws java.lang.Exception
     */
    CmPago consultar(int id) throws Exception;

    /**
     * Método para crear una nueva Empresa
     *
     * @param per (Usuarios)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmPago per) throws Exception;

    /**
     * Método para actualizar la información de una Empresa
     *
     * @param per (Usuarios)
     * @throws java.lang.Exception
     */
    void actualizar(CmPago per) throws Exception;

    /**
     * Método para eliminar un usuario
     *
     * @param id
     * @return (Usuarios) Objetop eliminado
     * @throws java.lang.Exception
     */
    CmPago eliminar(int id) throws Exception;
    
    /**
     * Pemite consultar pagos  por medio de parametros
     * @param paramConsulta: getParametroConsulta1(): documento,  
     * getParametroConsulta2(): numero factura
     * @return
     * @throws Exception 
     */
    List<CmPago> consultarPorAtributos(ParamConsulta paramConsulta) throws Exception ;

}
