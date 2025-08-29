/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.auditoria.utilidades;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFacturaTransaccion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionEncabezado;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaTransaccionRemoto;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.RespuestaDevolucionFactura;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.RespuestaNotificacionFactura;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.util.Date;

/**
 *
 * @author Jorge Eliecer Perez
 */
public class NotificacionUtilidades {
    
    private CmFacturaTransaccionRemoto getCmFacturaTransaccionServicioRemoto() throws Exception {
        return (CmFacturaTransaccionRemoto) RemotoEJB.getEJBRemoto("CmFacturaTransaccionServicio", CmFacturaTransaccionRemoto.class.getName());
    }
    
    private CmFacturaRemoto getCmFacturaRemoto() throws Exception {
        return (CmFacturaRemoto) RemotoEJB.getEJBRemoto("CmFacturaServicio", CmFacturaRemoto.class.getName());
    }

    
    public void actualizarHistoricoTransaccionFacturaM3M4(RespuestaNotificacionFactura respuesta, int tipoTransaccion){
         try {
             CmFacturaTransaccion transaccion = new CmFacturaTransaccion();
             transaccion.setCmFactura(new CmFactura(Integer.parseInt(respuesta.getConsecutivo())));
             transaccion.setTipo(tipoTransaccion);
             transaccion.setRespuestaCodigo(Integer.parseInt(respuesta.getCodigoResultado()));
             transaccion.setRespuestaDescripcion(respuesta.getResultado());
             insercionHistoricoFactura(transaccion);
        } catch (Exception e) {
        }
    }
    
    public void actualizarHistoricoTransaccionFacturaAuditoria(RespuestaNotificacionFactura respuesta){
         try {
             CmFacturaTransaccion transaccion = new CmFacturaTransaccion();
             transaccion.setCmFactura(new CmFactura(Integer.parseInt(respuesta.getConsecutivo())));
             transaccion.setTipo(CmFacturaTransaccion.TIPO_MOMENTO2);
             transaccion.setRespuestaCodigo(Integer.parseInt(respuesta.getCodigoResultado()));
             transaccion.setRespuestaDescripcion(respuesta.getResultado());
             insercionHistoricoFactura(transaccion);
        } catch (Exception e) {
        }
    }
    
    public void actualizarHistoricoTransaccionFacturaDevolucion(RespuestaDevolucionFactura respuesta){
         try {
             CmFacturaTransaccion transaccion = new CmFacturaTransaccion();
             transaccion.setCmFactura(new CmFactura(Integer.parseInt(respuesta.getConsecutivo())));
             transaccion.setTipo(CmFacturaTransaccion.TIPO_MOMENTO0);
             transaccion.setRespuestaCodigo(Integer.parseInt(respuesta.getCodigoRespuesta()));
             transaccion.setRespuestaDescripcion(respuesta.getDescripcionRespuesta());
             insercionHistoricoFactura(transaccion);
        } catch (NumberFormatException e) {
        }
    }
    
    private void insercionHistoricoFactura(CmFacturaTransaccion transaccion){
        try {   
             transaccion.setEstado(CmFacturaTransaccion.ESTADO_RESPUESTA);
             transaccion.setFechaHoraFin(new Date());
             transaccion.setEstadoAnterior(CmFacturaTransaccion.ESTADO_ENVIADA);
             getCmFacturaTransaccionServicioRemoto().actualizar(transaccion);
        } catch (Exception e) {
        }
    }
    
    public void actualizarUsuarioPorMomento(CmSincronizacionEncabezado enca, int tipoEstado){
        ParamConsulta paramConsulta = new ParamConsulta();
        try {
            paramConsulta.setParametroConsulta1(tipoEstado);
            paramConsulta.setParametroConsulta2(enca.getFactura());
            paramConsulta.setParametroConsulta3(enca.getUsuarioCrea());
            paramConsulta.setParametroConsulta4(enca.getTerminalCrea());
            paramConsulta.setParametroConsulta5(new Date());
            getCmFacturaRemoto().actualizarUsuarioSegunMomento(paramConsulta);
        } catch (Exception ex) {
        }
    }

}
