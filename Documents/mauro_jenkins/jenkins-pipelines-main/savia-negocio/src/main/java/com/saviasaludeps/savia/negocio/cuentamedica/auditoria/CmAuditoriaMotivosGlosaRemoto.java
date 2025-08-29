/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMotivoGlosa;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author jperezn
 */
public interface CmAuditoriaMotivosGlosaRemoto {

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CmAuditoriaMotivoGlosa) cargado
     * @throws java.lang.Exception
     */
    CmAuditoriaMotivoGlosa consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CmAuditoriaMotivoGlosa)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmAuditoriaMotivoGlosa obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CmAuditoriaMotivoGlosa)
     * @throws java.lang.Exception
     */
    void actualizar(CmAuditoriaMotivoGlosa obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CmAuditoriaMotivoGlosa) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmAuditoriaMotivoGlosa eliminar(int id) throws Exception;
    
    /**
     * Consulta la cantidad de CmRadicados 
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadRadicado(ParamConsulta paramConsulta) throws Exception;
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
    List<CmAuditoriaMotivoGlosa> consultarLista(ParamConsulta paramConsulta) throws Exception;
    /**
     * Metodo que permite consultar los motivos por detalle
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    public List<CmAuditoriaMotivoGlosa> consultarListaPorDetalle(ParamConsulta paramConsulta) throws Exception;
    /**
     * Metodo que permite consultar los motivos por detalle
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    public int consultarCantidadPorDetalle(ParamConsulta paramConsulta) throws Exception;

    /**
     * permite consular los motivos segun varios idDetalles consultados
     * @param idsDetalles: list
     * @return 
     * @throws Exception 
     */
    public List<CmAuditoriaMotivoGlosa> consultarPorMultiDetalles(String idsDetalles) throws Exception;

    /**
     * Permite eliminar un motivo por codigo motivo especifico e id detalle.
     * @param idsMotivos motivo especifico: list
     * @param idDetalle
     * @throws Exception 
     */
    public void eliminarPorCodigoEspecifico(String idsMotivos, int idDetalle) throws Exception;

    /**
     * Permite consultar motivos relacionados con facturas 
     * @param idsCmFacturas
     * @return
     * @throws Exception 
     */
    public List<CmAuditoriaMotivoGlosa> consultarPorMultiFacturas(String idsCmFacturas) throws Exception;

}
