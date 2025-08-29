/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.crue.servicio;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.ReporteAnexo4;
import com.saviasaludeps.savia.dominio.crue.ReporteAnexo2;
import com.saviasaludeps.savia.web.crue.bean.SolicitudesUrgenciasBean;
import java.util.List;

/**
 *
 * @author AlexanderDiaz
 */
public interface SolicitudesUrgenciasIface {

    /**
     * Método de acciones central
     *
     * @param bean
     */
    void Accion(SolicitudesUrgenciasBean bean);

    /**
     * Método para cargar inicial del Bean
     *
     * @param bean
     */
    void cargaInicial(SolicitudesUrgenciasBean bean);

    /**
     *
     * @param bean
     */
    void consultarProfesional(SolicitudesUrgenciasBean bean);

    /**
     *
     * @param id
     * @param bean
     * @return
     */
    List<ReporteAnexo2> generarReporteAnexo2(int id, SolicitudesUrgenciasBean bean);

    /**
     *
     * @param id
     * @param bean
     * @return
     */
    List<ReporteAnexo4> generarReporteAnexo4(int id, SolicitudesUrgenciasBean bean);

    /**
     *
     * @param idAnexo2
     * @return
     */
    AuAnexo4 consultarByIdAnexo2(int idAnexo2);

    /**
     * Consultar lista de IPSs
     *
     * @param bean
     */
    void listarIPS(SolicitudesUrgenciasBean bean);

    /**
     * consultar lista de Afiliados
     *
     * @param bean
     */
    void listarAfiliado(SolicitudesUrgenciasBean bean);

    /**
     * asigna afiliado seleccionado con datos completos
     *
     * @param idAfiliacion
     * @param bean
     */
    void asignarAfiliado(int idAfiliacion, SolicitudesUrgenciasBean bean);

    /**
     * ver rescates por anexo2
     *
     * @param bean
     */
    void verRescatesAnexo2(SolicitudesUrgenciasBean bean);

    /**
     * consulta rescate por id
     *
     * @param bean
     */
    void verRescate(SolicitudesUrgenciasBean bean);
    
     
    /**
     * Consultar maestro origen , tipo servicio atenciom y ubicacion del paciente
     *
     * @param bean
     * */
    void completarMaestro(SolicitudesUrgenciasBean bean);
    
    /**
     * Consultar maestro origen , tipo servicio atenciom y ubicacion del paciente
     *
     * @param bean
     * */
    void completarMaestroVersion2335(SolicitudesUrgenciasBean bean);
}
