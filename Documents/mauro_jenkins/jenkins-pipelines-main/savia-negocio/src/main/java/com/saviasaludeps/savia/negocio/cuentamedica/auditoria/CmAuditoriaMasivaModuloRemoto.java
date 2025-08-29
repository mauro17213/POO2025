/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegRegistroNovedad;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFacturasSinAutorizaciones;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author jperezn
 */
public interface CmAuditoriaMasivaModuloRemoto {


    
    /**
     * Consulta la cantidad de CmAuditoriaFactura 
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    int consultarCantidadRadicado(ParamConsulta paramConsulta) throws Exception;
    /**
     * Se consulta las factura
     * @param paramConsulta : param()1 y param()2 feacha radicacion, 
     * param3() si no hay filtros buscar por facturas asignadas o de lider.
     * param4(): habilitar super administracion.
     * @return
     * @throws Exception 
     */
    int consultarCantidadFacturasLista(ParamConsulta paramConsulta) throws Exception;
    /**
     * Se consulta las facturas
     * @param paramConsulta : param()1 y param()2 feacha radicacion, 
     * param3() si no hay filtros buscar por facturas asignadas o de lider.
     * param4(): habilitar super administracion.
     * @return
     * @throws Exception 
     */
    List<CmFactura> consultarFacturasLista(ParamConsulta paramConsulta) throws Exception;

    /**
     * permite obtener cantidad AsegRegistroNovedades
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<AsegRegistroNovedad> consultarListaNovedades(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite obtener novedades AsegRegistroNovedades
     * @param paramConsulta: getparam1(): idafiliado, getparam2(): fecha novedad.
     * @return
     * @throws Exception 
     */
    int consultarCantidadListaNovedades(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite obtener maestros de gnmaestro segun: getparam1(): tipo maestro, getParam2(): id maestro padre asociado 
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<Maestro> consultarMaestroPorParametro(ParamConsulta paramConsulta) throws Exception;

    /**
     * Permite obtener las factuas sin autorizacion
     * @param paramConsulta
     * @return 
     * @throws java.lang.Exception 
     */
    List<CmFacturasSinAutorizaciones> consultarFacturasSinAutorizacion(ParamConsulta paramConsulta)throws Exception;

    /**
     * Permite actualizar el estado y tipo de auditoria : getParametroConsulta2() tipo auditoria,
     * getParametroConsulta3(): estado factura, getParametroConsulta1() cadena con idsfactura.
     * @param paramConsulta
     * @throws Exception 
     */
    void actualizarEstadoAuditoriaMasivo(ParamConsulta paramConsulta) throws Exception;

}
