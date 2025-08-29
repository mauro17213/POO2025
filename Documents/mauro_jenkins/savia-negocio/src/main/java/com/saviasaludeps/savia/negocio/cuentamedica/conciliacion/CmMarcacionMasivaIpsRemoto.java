/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmMarcacionMasivaIps;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

public interface CmMarcacionMasivaIpsRemoto {

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
    List<CmMarcacionMasivaIps> consultarLista(ParamConsulta paramConsulta) throws Exception;
     /**
     * Permite consultar facturas
     * 
     * @param numerosFacturados
     * @param numerosRadicados
     * @return
     * @throws Exception 
     */
    List<CmFactura> consultarParaMarcacionIpsMasiva(String numerosFacturados, String numerosRadicados) throws Exception;
    
    /**
     * Permite consultar un registro por ID
     * @param id
     * @return (Personas) cargado
     * @throws java.lang.Exception
     */
    CmMarcacionMasivaIps consultar(int id) throws Exception;
    
    /**
     * Permite crear una nuevo registro
     * @param obj (Personas)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmMarcacionMasivaIps obj) throws Exception;
    
    /**
     * Permite actualizar la información de un registro
     * @param obj (Personas)
     * @throws java.lang.Exception
     */
    void actualizar(CmMarcacionMasivaIps obj) throws Exception;
   
    /**
     * Permite eliminar registro
     * @param id
     * @return
     * @throws Exception 
     */
    CmMarcacionMasivaIps eliminar(int id) throws Exception;

    /**
     * Permite actualizar datos de fin de proceso.
     * @param obj[id:estado:feachaHorafin:exitosos]
     * @throws Exception 
     */
    void actualizarFinProceso(CmMarcacionMasivaIps obj) throws Exception;

}
