/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegRegistroNovedad;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitud;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.web.autorizacion.bean.AuNoSolicitudBean;



/**
 *
 * @author raul-palacios
 */
public interface AuNoSolicitudIface {

    void Accion(AuNoSolicitudBean bean);
    
    void cargaInicial(AuNoSolicitudBean bean);
    
    void listarAfiliado(AuNoSolicitudBean bean);
    
    void listarPrestadores(AuNoSolicitudBean bean);
    
    void listarPrestadoresEntrega(AuNoSolicitudBean bean);
    
    void listarProcedimientos(AuNoSolicitudBean bean);
    
    void listarMedicamentos(AuNoSolicitudBean bean);
    
    void listarInsumos(AuNoSolicitudBean bean);
     
    void listarPaquetes(AuNoSolicitudBean bean);
    
    boolean validarEstadoAfiliado(int maeEstadoAfiliacion, int idAsegAfiliado, AuNoSolicitudBean bean);
    
    void consultarProfesional(AuNoSolicitudBean bean);
    
    boolean consultarCantidadAnuladas(AuNoSolicitudBean bean, int idAnexo4Item);
    
    MaMedicamento consultarMedicamento(AuNoSolicitudBean bean, int idMedicamento);
    
    void verBitacorasSinAutorizaciones(AuNoSolicitudBean bean);
    
    String validarTecnologia(String tipoTecnologia, String codigoTecnologia, String tipoDocumento, String numeroDocumento);

    String validarDiagnostico(String codigoDiagnostico, String tipoDocumento, String numeroDocumento);
    
    String validarCantidadTecnologia(String tipoTecnologia, int cantidad, String codigoTecnologia);
    
    AuNoSolicitud consultarAuNoSolicitud(AuNoSolicitudBean bean, int idSolicitud);
    
    AsegRegistroNovedad consultarUltinaNovedadInactivoAfiliado(AuNoSolicitudBean bean);
}
