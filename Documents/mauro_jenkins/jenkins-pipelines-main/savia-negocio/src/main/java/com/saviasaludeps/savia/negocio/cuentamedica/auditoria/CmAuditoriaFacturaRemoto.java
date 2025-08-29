/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.negocio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegRegistroNovedad;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFacturaEnCmAuditoriaAutorizacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import java.util.List;

/**
 *
 * @author jperezn
 */
public interface CmAuditoriaFacturaRemoto {


    
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
    int consultarCantidadLista(ParamConsulta paramConsulta) throws Exception;
    /**
     * Se consulta las facturas
     * @param paramConsulta : param()1 y param()2 feacha radicacion, 
     * param3() si no hay filtros buscar por facturas asignadas o de lider.
     * param4(): habilitar super administracion.
     * @return
     * @throws Exception 
     */
    List<CmFactura> consultarLista(ParamConsulta paramConsulta) throws Exception;

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
     * Permite consultar las autorizaciones asociadas a una factura al momento
     * de realizar el cierre auditoria
     * @param paramConsulta
     * @return
     * @throws Exception 
     */
    List<String> consultarAutorizacionesPorFactura(ParamConsulta paramConsulta) throws Exception;

    /**
     * Metodo que permite obtener todas las facturas registradas  en cmauditoriaAutorizaciones.
     * @param numeroAutorizacion
     * @return
     * @throws Exception 
     */
    List<CmFacturaEnCmAuditoriaAutorizacion> consultaFacturasPorAutorizacionEnAuditoria(int numeroAutorizacion) throws Exception;

    /**
     * Permite consultar usuarios presentes en factura que estan activos.
     * @param tipoUsuario
     * @return
     * @throws Exception 
     */
    List<Usuario> consultaUsuariosCmFacturaSegunPeril(int tipoUsuario) throws Exception;

}
