/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.servicio;

import com.saviasaludeps.savia.dominio.administracion.DiaHabil;
import com.saviasaludeps.savia.dominio.autorizacion.AuRechazo;
import com.saviasaludeps.savia.dominio.autorizacion.ReporteAnexo3;
import com.saviasaludeps.savia.web.aseguramiento.bean.ConsultarAfiliadoBean;
import java.util.Date;
import java.util.List;


/**
 *
 * @author jose perez
 */
public interface ConsultarAfiliadoServicioIface {
    
    void Accion(ConsultarAfiliadoBean bean);
    
    /**
     * Cargar lista de Zonas
     * @param bean 
     */
    void cargaInial(ConsultarAfiliadoBean bean);
    
    void contarImpresion(ConsultarAfiliadoBean bean);
    
    void verBitacoras(ConsultarAfiliadoBean bean);
    
    void consultarAdjuntosCotizacion(ConsultarAfiliadoBean bean);
    
    List<ReporteAnexo3> generarReporteAnexo3(int id, ConsultarAfiliadoBean bean);
    
    DiaHabil validarFechaHabil(Date fecha);
    
    AuRechazo buscarRechazo(int idItem);
    
    void consultarPosfechados(ConsultarAfiliadoBean bean);
    
    void consultarAdjuntosCotizacionSol(ConsultarAfiliadoBean bean);
    
}
