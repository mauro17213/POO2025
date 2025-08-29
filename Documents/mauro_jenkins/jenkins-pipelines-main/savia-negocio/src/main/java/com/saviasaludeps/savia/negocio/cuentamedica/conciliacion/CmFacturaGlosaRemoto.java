/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.conciliacion;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public interface CmFacturaGlosaRemoto {
    
    /**
     * Consulta de cantidad de registros en una lista
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Consultar lista de registros
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmFactura> consultarLista(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Método para consultar un registro por ID
     * @param id
     * @return (Personas) cargado
     * @throws java.lang.Exception
     */
    CmFactura consultar(int id) throws Exception;
    
    /**
     * Método para crear una nuevo registro
     * @param obj (Personas)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmFactura obj) throws Exception;
    
    /**
     * Método para actualizar la información de un registro
     * @param obj (Personas)
     * @throws java.lang.Exception
     */
    void actualizar(CmFactura obj) throws Exception;
    
    /**
     * Método para eliminar un registro
     * @param id
     * @return (Personas) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmFactura eliminar(int id) throws Exception;

    /**
     * Metodo que permite colocar una factura como bloqueda o no
     * @param paramConsulta:  getParametroConsulta1(): idfacturas , getParametroConsulta2(): fecha marcacion,
     * getParametroConsulta3(): fecha vencimiento,
     * getParametroConsulta4(): marcacion.
     * @throws Exception 
     */
    void actualizarBloqueoFactura(ParamConsulta paramConsulta) throws Exception;

    /**
     * Metodo que permite actualizar es estado de tipo de auditoria
     * @param paramConsulta
     * @throws Exception 
     */
    void actualizarTipoAuditoria(ParamConsulta paramConsulta) throws Exception;
    /**
     * Metodo que permite actualizar los valors de detalles para ser cargados y
     * aprobador por perfiles de auditoria
     * @param paramConsulta
     * @throws Exception 
     */
    void actualizarValoresAuditoria(ParamConsulta paramConsulta) throws Exception;
    
    /**
     * Metodo que permite borrar los valores temporales de auditoria
     * @param paramConsulta
     * @throws java.lang.Exception 
     */
    void borrarValoresAuditoria(ParamConsulta paramConsulta)throws java.lang.Exception;
    
}
