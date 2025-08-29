/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.auditoria.utilidades;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Entrega;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4EntregaRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4Remoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaFacturaRemoto;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jperezn
 */
 public class EnvioNotificacionAnexo4EntregaHilo extends Thread {

    int  idFactura;
    Date fechaPrestacion;
    private CmAuditoriaFacturaRemoto getCmAuditoriaFacturaRemoto() throws Exception {
        return (CmAuditoriaFacturaRemoto) RemotoEJB.getEJBRemoto(("CmAuditoriaFacturaServicio"), CmAuditoriaFacturaRemoto.class.getName());
    }
     
    private AuAnexo4EntregaRemoto getAuAnexo4EntregaRemoto() throws Exception {
         return (AuAnexo4EntregaRemoto) RemotoEJB.getEJBRemoto("AuAnexo4EntregaServicio", AuAnexo4EntregaRemoto.class.getName());
    }

    private AuAnexo4Remoto getAuAnexo4Remoto() throws Exception {
         return (AuAnexo4Remoto) RemotoEJB.getEJBRemoto("AuAnexo4Servicio", AuAnexo4Remoto.class.getName());
    }
      
    public EnvioNotificacionAnexo4EntregaHilo( int idFactura , Date fechaPrestacion) {
      this.idFactura = idFactura;
      this.fechaPrestacion = fechaPrestacion;
    }

    
     @Override
     public void run() {

         ParamConsulta param = new ParamConsulta();
         List<AuAnexo4> listAnexo4 = new ArrayList<>();
         try {
           
             param.setParametroConsulta1(idFactura);
             List<String> autorizaciones = getCmAuditoriaFacturaRemoto().consultarAutorizacionesPorFactura(param);

             if (!autorizaciones.isEmpty()) {
                 ParamConsulta param2 = new ParamConsulta();
                 for (String autorizacion : autorizaciones) {
                     Map filtro = new HashMap<String, Object>();
                     filtro.put("id", autorizacion);
                     param2.setFiltros(filtro);
                     param2.setPrimerRegistro(0);
                     param2.setRegistrosPagina(100);
                     for (AuAnexo4 auAnexo4 : getAuAnexo4Remoto().consultarLista(param2)) {
                         auAnexo4.setFechaPrestacion(fechaPrestacion);
                         auAnexo4.setOrigenEntrega(AuAnexo4Entrega.ORIGEN_RIPS);
                         listAnexo4.add(auAnexo4);
                     }
                 }
                 
                 if( ! listAnexo4.isEmpty()){
                   getAuAnexo4EntregaRemoto().entregarTodo(listAnexo4);
                 }
             }
         } catch (Exception e) {
             Logger.getLogger(EnvioNotificacionAnexo4EntregaHilo.class.getName()).log(Level.SEVERE, null, e);
         }
     }
}
