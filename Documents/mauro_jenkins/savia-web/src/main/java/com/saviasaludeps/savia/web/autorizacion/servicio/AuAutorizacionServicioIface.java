/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.ReporteAnexo4;
import com.saviasaludeps.savia.web.autorizacion.bean.AuAutorizacionBean;
import java.util.List;

/**
 *
 * @author raul-palacios
 */
public interface AuAutorizacionServicioIface {

    void Accion(AuAutorizacionBean bean);

    void cargasInicial(AuAutorizacionBean bean);

    public List<ReporteAnexo4> generarReporteAnexo4(int id, AuAutorizacionBean bean);

    public void contarImpresion(AuAutorizacionBean bean);

    void listarIps(AuAutorizacionBean bean);

    boolean validarFacturaAsociada(AuAutorizacionBean bean);

    boolean validarEstadoAfiliado(int maeEstadoAfiliacion);

    void verHistoricosAnexo4(AuAutorizacionBean bean);

    AuAnexo4 consultarAnexo4(int id);

    void verBitacoras(AuAutorizacionBean bean);

    void consultarAdjuntosCotizacion(AuAutorizacionBean bean);
    
    boolean consultarCantidadAnuladas(AuAutorizacionBean bean, int idAnexo4Item);
}
