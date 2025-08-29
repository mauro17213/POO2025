/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Item;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAutorizacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author jperezn
 */
public interface CmAuditoriaAutorizacionRemoto {

    /**
     * Método para consultar un registro por ID
     *
     * @param id
     * @return (CmAuditoriaAutorizacion) cargado
     * @throws java.lang.Exception
     */
    CmAuditoriaAutorizacion consultar(int id) throws Exception;

    /**
     * Método para crear una nuevo registro
     *
     * @param obj (CmAuditoriaAutorizacion)
     * @return (int) id del registro generado
     * @throws java.lang.Exception
     */
    int insertar(CmAuditoriaAutorizacion obj) throws Exception;

    /**
     * Método para actualizar la información de un registro
     *
     * @param obj (CmAuditoriaAutorizacion)
     * @throws java.lang.Exception
     */
    void actualizar(CmAuditoriaAutorizacion obj) throws Exception;

    /**
     * Método para eliminar un registro
     *
     * @param id
     * @return (CmAuditoriaAutorizacion) La persistencia del objeto eliminado
     * @throws java.lang.Exception
     */
    CmAuditoriaAutorizacion eliminar(int id) throws Exception;
    
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
    List<CmAuditoriaAutorizacion> consultarLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * 
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadPorFactura(ParamConsulta paramConsulta) throws Exception;
    /**
     * 
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmAuditoriaAutorizacion> consultarListaPorFactura(ParamConsulta paramConsulta) throws Exception;

    /**
     * Consutar anexos4 por documento
     * @param paramConsulta: getparam2: consulta todos sin paginacion., getParame3():consulta anexo 4 autorizado o autorizado automatico
     * @return
     * @throws Exception 
     */
    List<AuAnexo4> consultarListaAnexo4PorDocumento(ParamConsulta paramConsulta) throws Exception;
    /**
     * Consutar anexos4 por documento
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadAnexo4PorDocumento(ParamConsulta paramConsulta) throws Exception;
    /**
     * Metodo que permite consultar autorizaciones por detalle
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<CmAuditoriaAutorizacion> consultarListaPorDetalle(ParamConsulta paramConsulta) throws Exception;
    /**
     * Metodo que permite contar autorizaciones por detalle
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadPorDetalle(ParamConsulta paramConsulta) throws Exception;
    /**
     * permite buscar items anexo4 por documento afiliado
     * @param paramConsulta: getParam1: numero documento - opcional, getparam2: id anexo4 - opcional
     * @return
     * @throws Exception 
     */
    int consultarCantidadAnexo4ItemsPorAtributos(ParamConsulta paramConsulta) throws Exception;

    /**
     * permite consutar caontidad items anexo4 por documento afiliado
     * @param paramConsulta: getParam1: numero documento - opcional, getparam2: id anexo4 - opcional
     * getparam3: consulta todos sin paginacion- opcional
     * @return
     * @throws Exception 
     */
    List<AuAnexo4Item> consultarListaAnexo4ItemsPorAtributos(ParamConsulta paramConsulta) throws Exception;


}
