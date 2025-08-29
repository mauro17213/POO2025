/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.conciliacion.utilidades;


import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmInforme;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.Reporte;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmInformeRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.ReporteCuentasMedicasRemoto;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author jperezn
 */
public class ReportesCargaMasivaHilo extends Thread {
    
    ParamConsulta parametroConsulta;
    CmInforme informe;
           
    private ReporteCuentasMedicasRemoto getReporteCuentasMedicasRemoto() throws Exception {
        return (ReporteCuentasMedicasRemoto) RemotoEJB.getEJBRemoto(("ReporteCuentasMedicasServicio"), ReporteCuentasMedicasRemoto.class.getName());
    }

    public ReportesCargaMasivaHilo(CmInforme informe, ParamConsulta parametroConsulta) {
        this.informe = informe;
        this.parametroConsulta = parametroConsulta;
    }
   
    private CmInformeRemoto getCmInformeRemoto() throws Exception {
        return (CmInformeRemoto) RemotoEJB.getEJBRemoto(("CmInformeServicio"), CmInformeRemoto.class.getName());
    }
    
    @Override
    public void run() {

        Reporte reporte;
   
        try {
            switch (informe.getTipo()) {
                case CmInforme.TIPO_INFORME_MOTIVO:
                    reporte = getReporteCuentasMedicasRemoto().generarReporteMotivos(this.parametroConsulta);
                    break;
                case CmInforme.TIPO_INFORME_RESPUESTA_POR_DETALLES:
                    reporte = getReporteCuentasMedicasRemoto().generarReporteRespuestasPorDetalle(this.parametroConsulta);
                    break;
                case CmInforme.TIPO_INFORME_DETALLES_NO_RESP:
                    reporte = getReporteCuentasMedicasRemoto().generarReporteDetallesSinRespuestas(this.parametroConsulta);
                    break;
                case CmInforme.TIPO_INFORME_RESPUESTA_DETALLES:
                    reporte = getReporteCuentasMedicasRemoto().generarReporteRespuestaDetalles(this.parametroConsulta);
                    break;
                case CmInforme.TIPO_INFORME_AUDITORIA:
                    reporte = getReporteCuentasMedicasRemoto().generarReporteAuditoria(this.parametroConsulta);
                    break;
                case CmInforme.TIPO_INFORME_GENERAL_CUENTAS_MEDICAS:
                    reporte = getReporteCuentasMedicasRemoto().generarReporteGeneralCuentaMedica(this.parametroConsulta);
                    break;
                case CmInforme.TIPO_INFORME_GENERAL_CUENTAS_MEDICAS_DETALLES:
                    reporte = getReporteCuentasMedicasRemoto().generarReporteGeneralCuentaMedicaDetalles(this.parametroConsulta);
                    break;
                case CmInforme.TIPO_INFORME_DEVOLUCION_FACTURAS:
                    reporte = getReporteCuentasMedicasRemoto().generarReporteDevolucionFacturas(this.parametroConsulta);
                    break;
                case CmInforme.TIPO_INFORME_FACTURAS_CONCILIADAS:
                    reporte = getReporteCuentasMedicasRemoto().generarReporteFacturasConciliadas(this.parametroConsulta);
                    break;
                case CmInforme.TIPO_INFORME_FACTURAS_RECHAZADAS:
                    reporte = getReporteCuentasMedicasRemoto().generarReporteFacturasRechazadas(this.parametroConsulta);
                    break;
                case CmInforme.TIPO_INFORME_DETALES_DESCUENTO_CAPITA:
                    reporte = getReporteCuentasMedicasRemoto().generarReporteDetallesDescuentoCapita(this.parametroConsulta);
                    break;
                default:
                    reporte = new Reporte();
                    break;
            }
            
            if(!"".equals(reporte.getContenidoEnString())){
                reporte.setRuta(informe.getRuta());
                reporte.setNombreReporte(informe.getArchivo());
                generarArchivoReporte(reporte);
                informe.setRegistros(reporte.getCantidadRegistros());      
            }
             informe.setObservacion(reporte.getObservacion());
             int estado = reporte.getObservacion().length() > 0 ? CmInforme.ESTADO_ERROR : CmInforme.ESTADO_FINALIZADO;
             informe.setEstado(estado);
             getCmInformeRemoto().actualizar(informe);
             reporte.setContenidoEnString(null);
             reporte.setContenidoEnBytes(null);
             reporte = null;
                    
        } catch (Exception e) {
            try {
                String mensaje ;
                if (e.getCause() != null && e.getCause().getMessage().equals("Java heap space")) {
                    mensaje = ("Ha ocurrido un error por la cantidad de información a procesar,"
                            + " por favor intente segmentación de búsqueda más especifica.");
                } else {
                    mensaje = (e.getMessage());
                }
                informe.setObservacion(mensaje);
                informe.setEstado(CmInforme.ESTADO_ERROR );
                getCmInformeRemoto().actualizar(informe);
            } catch (Exception ex) {
                Logger.getLogger(ReportesCargaMasivaHilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
     private int generarArchivoReporte(Reporte reporte) {
        int cant = 0;
        File archivo;
        BufferedWriter bw = null;
        try {
            archivo = new File(reporte.getRuta(), reporte.getNombreReporte());
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            bw = new BufferedWriter (new FileWriter(archivo)); 
            bw.write(reporte.getContenidoEnString());           
            bw.close();
        } catch (IOException ex) {
           reporte.setObservacion("Error creando el archivo " + reporte.getRuta()+ ex.getMessage());
           if ( bw != null ) {
               try {
                   bw.close();
               } catch (IOException ex1) { }
           }
        } catch (Exception e) {
            reporte.setObservacion("Error creando el archivo " + reporte.getRuta()+ e.getMessage());
            if ( bw != null ) {
               try {
                   bw.close();
               } catch (IOException ex1) { }
           }
        }
        return cant;
    }

}
