/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.conciliacion.servicio;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmConciliacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuesta;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuestaDetalle;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAdjunto;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.ReporteConciliacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.ReporteRespuestaGlosa;
import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmFactura;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaAutorizacionRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaConceptoContableRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaDescuentoCapitaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaDiagnosticoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaMotivosGlosaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmConciliacionRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmDetalleRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaConciliacionRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmGlosaRespuestaDetalleRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmGlosaRespuestaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.wstransaccion.WsCmFacturaRemoto;
//import com.saviasaludeps.savia.web.cuentamedica.conciliacion.bean.CargaMasivaConciliacionBean;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.cuentamedica.conciliacion.bean.CmFacturaConciliacionBean;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author raul-palacios
 */
public class CmFacturaConciliacionServicioImpl extends AccionesBO implements CmFacturaConciliacionServicioIface {
      
    public static final int TIPO_CONCILIACION_INDIVIDUAL = 1;
    
    public static final int TIPO_CONCILIACION_MASIVA = 2;
    
    public final static int CONSULTA_TODOS_LOS_ITEMS = 1;
     
    private CmGlosaRespuestaDetalleRemoto getCmGlosaRespuestaDetalleRemoto() throws Exception {
        return (CmGlosaRespuestaDetalleRemoto) RemotoEJB.getEJBRemoto("CmGlosaRespuestaDetalleServicio", CmGlosaRespuestaDetalleRemoto.class.getName());
    }
    
    private CmFacturaConciliacionRemoto getCmFacturaConciliacionRemoto() throws Exception {
        return (CmFacturaConciliacionRemoto) RemotoEJB.getEJBRemoto("CmFacturaConciliacionServicio", CmFacturaConciliacionRemoto.class.getName());
    }
    
    private CmGlosaRespuestaRemoto getCmGlosaRespuestaRemoto() throws Exception {
        return (CmGlosaRespuestaRemoto) RemotoEJB.getEJBRemoto("CmGlosaRespuestaServicio", CmGlosaRespuestaRemoto.class.getName());
    }
    
    private CmDetalleRemoto getCmDetalleRemoto() throws Exception {
        return (CmDetalleRemoto) RemotoEJB.getEJBRemoto("CmDetalleServicio", CmDetalleRemoto.class.getName());
    }
    
    private CmFacturaRemoto getCmFacturaRemoto() throws Exception {
        return (CmFacturaRemoto) RemotoEJB.getEJBRemoto("CmFacturaServicio", CmFacturaRemoto.class.getName());
    }
    
    private CmConciliacionRemoto getCmConciliacionRemoto() throws Exception {
        return (CmConciliacionRemoto) RemotoEJB.getEJBRemoto("CmConciliacionServicio", CmConciliacionRemoto.class.getName());
    }
    
    private CmAuditoriaDiagnosticoRemoto getCmAuditoriaDiagnosticoRemoto() throws Exception {
        return (CmAuditoriaDiagnosticoRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaDiagnosticoServicio", CmAuditoriaDiagnosticoRemoto.class.getName());
    }
    
    private CmAuditoriaConceptoContableRemoto getCmAuditoriaConceptoContableRemoto() throws Exception {
        return (CmAuditoriaConceptoContableRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaConceptoContableServicio", CmAuditoriaConceptoContableRemoto.class.getName());
    }
    
    private CmAuditoriaMotivosGlosaRemoto getCmAuditoriaMotivosGlosaRemoto() throws Exception {
        return (CmAuditoriaMotivosGlosaRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaMotivosGlosaServicio", CmAuditoriaMotivosGlosaRemoto.class.getName());
    }
    
    private CmAuditoriaAutorizacionRemoto getCmAuditoriaAutorizacionRemoto() throws Exception {
        return (CmAuditoriaAutorizacionRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaAutorizacionServicio", CmAuditoriaAutorizacionRemoto.class.getName());
    }
    
    private WsCmFacturaRemoto getWsCmFacturaRemoto() throws Exception {
        return (WsCmFacturaRemoto) RemotoEJB.getEJBRemoto("WsCmFacturaServicio", WsCmFacturaRemoto.class.getName());
    }
    
    private CmAuditoriaAdjuntoRemoto getCmAuditoriaAdjuntoRemoto() throws Exception {
        return (CmAuditoriaAdjuntoRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaAdjuntoServicio", CmAuditoriaAdjuntoRemoto.class.getName());
    }
     
    private CmAuditoriaDescuentoCapitaRemoto getCmAuditoriaDescuentoCapitaRemoto() throws Exception {
        return (CmAuditoriaDescuentoCapitaRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaDescuentoCapitaServicio", CmAuditoriaDescuentoCapitaRemoto.class.getName());
    }
    
    @Override
    public void Accion(CmFacturaConciliacionBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    switch (bean.getDoAccion()) {
                        case CmFacturaConciliacionBean.ACCION_VER_FACTURA:
                            verFactura(bean);
                            break;
                        case CmFacturaConciliacionBean.ACCION_LISTAR_DETALLES:
                            listarDetalles(bean);
                            break;
                        case CmFacturaConciliacionBean.ACCION_LISTAR_GLOSA_RESPUESTA:
                            listarGlosaRespuesta(bean);
                            break;
                        case CmFacturaConciliacionBean.ACCION_LISTAR_RESPUESTA_DETALLES:
                            listarGlosaRespuestaDetalles(bean);
                            break;
                        case CmFacturaConciliacionBean.ACCION_LISTAR_DETALLES_SIN_PAGINAR:
                            listarDetallesSinPaginar(bean);
                            break;
                        case CmFacturaConciliacionBean.ACCION_VER_RESPUESTA_GLOSA:
                            verRespuestaGlosa(bean);
                            break;
                        case CmFacturaConciliacionBean.ACCION_VER_DETALLE:
                            verDetalle(bean);
                            break;
                        case CmFacturaConciliacionBean.ACCION_LISTAR_AUTORIZACIONES:
                            listarAuditoriaAutorizacion(bean);
                            break;
                        case CmFacturaConciliacionBean.ACCION_LISTAR_CONCEPTOS:
                            listarAuditoriaConceptosContables(bean);
                            break;
                        case CmFacturaConciliacionBean.ACCION_LISTAR_DIAGNOSTICOS:
                            listarAuditoriaDiagnosticos(bean);
                            break;
                        case CmFacturaConciliacionBean.ACCION_LISTAR_MOTIVOS_GLOSA:
                            listarAuditoriaMotivosGlosa(bean);
                            break;
                         case CmFacturaConciliacionBean.ACCION_LISTAR_ADJUNTOS_CMDETALLES:
                            listarAuditoriaAdjuntosCmDetalles(bean);
                            break;
                        case CmFacturaConciliacionBean.ACCION_LISTAR_ADJUNTOS_FACTURA:
                            listarAdjuntosFactura(bean);
                            break;
                        case CmFacturaConciliacionBean.ACCION_LISTAR_DESCUENTO_CAPITA:
                            listarDescuentoCapita(bean);
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:          
                    break;
                case Url.ACCION_GUARDAR:
                    break;
                case Url.ACCION_EDITAR:
                    break;
                case Url.ACCION_MODIFICAR:
                    break;
                case Url.ACCION_BORRAR:
                    break;
                 case Url.ACCION_ADICIONAL_1:   
                     switch (bean.getDoAccion()) {    
                           case CmFacturaConciliacionBean.ACCION_BUSCAR_FACTURAS_BLOQUEDAS:
                             consultarFacturasBloquedas(bean);
                             break;
                         case Url.ACCION_GUARDAR:
                             guardarConciliacionMasiva(bean);
                             break;
                         case Url.ACCION_MODIFICAR:
                             actualizarBloqueoFacturas(bean);
                             break;
                     }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                      case CmFacturaConciliacionBean.ACCION_VER_FACTURA:
                          verFactura(bean);
                        break;
                       case CmFacturaConciliacionBean.ACCION_LISTAR_DETALLES:
                          listarDetalles(bean);
                        break;
                      case CmFacturaConciliacionBean.ACCION_LISTAR_GLOSA_RESPUESTA:
                          listarGlosaRespuesta(bean);
                          break;
                      case CmFacturaConciliacionBean.ACCION_LISTAR_RESPUESTA_DETALLES:
                          listarGlosaRespuestaDetalles(bean);
                          break;
                       case CmFacturaConciliacionBean.ACCION_GUARDAR_CONCILIACION_IN:
                          guardarConciliacionIndividual(bean);
                          break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (bean.getDoAccion()) {
                      case Url.ACCION_ADICIONAL_1:
                          reporteRespuestaGlosa(bean);
                        break;
                       case Url.ACCION_ADICIONAL_2:
                          reporteConciliacionMasiva(bean);
                        break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_4:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_GUARDAR:
                            actualizarDesbloqueoMasivo(bean);
                            break;
                    }
                    break;  
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }
    
    private void guardarConciliacionMasiva(CmFacturaConciliacionBean bean) {
        try {
            
            if( verificarBloqueoFacturas(bean) ){
     
                CmConciliacion cmConciliacion = new CmConciliacion();
                //calculo valor ips y eps para tabla conciliacion
                calcularValoresConciliacion( bean.getCmConciliacion(), cmConciliacion );
                cmConciliacion.setValorPagadoEps(bean.getCmConciliacion().getValorPagadoEps());
                cmConciliacion.setValorAceptadoIps(bean.getCmConciliacion().getValorAceptadoIps());
                //Tomamos el primer prestador y asignamos a conciliacion
                int idPrestador = (bean.getRegistrosFacturaSelected() != null
                        && !bean.getRegistrosFacturaSelected().isEmpty())
                        ? bean.getRegistrosFacturaSelected().get(0).getCntPrestador().getId() : 0;
                cmConciliacion.setCntPrestadores(new CntPrestador(idPrestador));         
                bean.auditoriaGuardar(cmConciliacion);
                
                String observacionConciliacion = bean.getCmConciliacion().getObservacion() == null ||
                          bean.getCmConciliacion().getObservacion().equals("") ? " "
                        : bean.getCmConciliacion().getObservacion();
                cmConciliacion.setObservacion(observacionConciliacion);
                
                int totalFacturasPorProcesar = bean.getRegistrosFacturaSelected() != null ? 
                                    bean.getRegistrosFacturaSelected().size() : 0 ;
                cmConciliacion.setCantidadFacturas(totalFacturasPorProcesar);
                cmConciliacion.setEstadoProceso(CmConciliacion.ESTADO_EN_PROCESO);
                
                String representanteEPS = bean.getCmConciliacion().getRepresentanteEps() ;
                String representanteIPS = bean.getCmConciliacion().getRepresentanteIps();
             
                int idConciliacion = getCmConciliacionRemoto().insertar(cmConciliacion);

                for (CmFactura cmFactura : bean.getRegistrosFacturaSelected()) {
        
                    if (cmFactura.getValorPendienteActual() != null && 
                            cmFactura.getValorPendienteActual().compareTo(BigDecimal.ZERO) > 0) {

                        List<CmDetalle> detallesPorFactura = listarDetallesSinPaginar(bean, cmFactura.getId());

                        if ( !detallesPorFactura.isEmpty() ) {
                            BigDecimal valorPendienteTotalRespuestaDetalle = new BigDecimal("0");
                            BigDecimal valorPagadoTotalRespuestaDetalle = new BigDecimal("0");
                            BigDecimal valorFacturadoTotalRespuestaDetalle = new BigDecimal("0");
                            BigDecimal valorTotalPagadoEPS   = new BigDecimal("0");
                            BigDecimal valorTotalAceptadoIPS = new BigDecimal("0");
                            String observacionFactura = cmFactura.getObservacion() == null ||
                                                  cmFactura.getObservacion().equals("") ? " " :
                                                  cmFactura.getObservacion();

                            CmGlosaRespuesta respuesta = new CmGlosaRespuesta();
                            respuesta.setCmFactura(new CmFactura(cmFactura.getId()));
                            respuesta.setCmConciliacion(new CmConciliacion(idConciliacion));
                            respuesta.setTipoRespuesta(bean.getCmConciliacion().getTipoEstadoRespuesta());
                            respuesta.setValorCobroDetalle(new BigDecimal("0"));
                            respuesta.setValorPagado(new BigDecimal("0"));
                            respuesta.setValorPendiente(new BigDecimal("0"));
                            respuesta.setValorFacturado(cmFactura.getValorFactura());
                            respuesta.setValorPagadoEps(new BigDecimal("0"));
                            respuesta.setValorAceptadoIps(new BigDecimal("0"));
                            respuesta.setObservacion(observacionConciliacion);
                            respuesta.setRepresentanteEps(representanteEPS);
                            respuesta.setRepresentanteIps(representanteIPS);
                            respuesta.setEstadoSincronizacion(CmGlosaRespuesta.ESTADO_SINCRONIZACION_CREADA);
                            bean.auditoriaGuardar(respuesta);
                            int idRespuestaGlosa = getCmGlosaRespuestaRemoto().insertar(respuesta);
                            respuesta.setId(idRespuestaGlosa);

                            CmGlosaRespuestaDetalle respuestaDetalle = new CmGlosaRespuestaDetalle();
                            respuestaDetalle.setPorcentajePagadoEps(cmFactura.getPorcentajePagadoEPS());
                            respuestaDetalle.setPorcentajeAceptadoIps(cmFactura.getPorcentajeAceptadoIPS());
                            respuestaDetalle.setObservacion(observacionFactura);

                            for (CmDetalle detalle : detallesPorFactura) {                              
                                BigDecimal valorPendienteActualDetalle = Optional.ofNullable(detalle.getValorPendienteActual()).orElse(new BigDecimal(BigInteger.ZERO));
                                if (valorPendienteActualDetalle.compareTo(new BigDecimal(BigInteger.ZERO)) > 0) {
                                    respuestaDetalle.setDocumento(detalle.getDocumento());
                                    respuestaDetalle.setCmGlosaRespuesta(new CmGlosaRespuesta(idRespuestaGlosa));
                                    respuestaDetalle.setCmDetalle(new CmDetalle(detalle.getId()));
                                    // se toman estos valores de detalle
                                    respuestaDetalle.setValorCobroDetalle(detalle.getValorFacturado());
                                    respuestaDetalle.setValorFacturado(detalle.getValorFacturado());
                                    
                                    //se calculan y asignan valores para CMGLOSA_RESPUESTA_DETALLE
                                    //(valor pagadoEps, valor aceptado Ips, valorPagado, valor pendiente)
                                    calcularPorcentajesRespuestaDetalle(detalle, respuestaDetalle, TIPO_CONCILIACION_MASIVA);

                                    valorPagadoTotalRespuestaDetalle = valorPagadoTotalRespuestaDetalle.
                                            add(respuestaDetalle.getValorPagado()).
                                            setScale(4, RoundingMode.HALF_UP);

                                    valorPendienteTotalRespuestaDetalle = valorPendienteTotalRespuestaDetalle.
                                            add(respuestaDetalle.getValorPendiente()).
                                            setScale(4, RoundingMode.HALF_UP);

                                    valorFacturadoTotalRespuestaDetalle = valorFacturadoTotalRespuestaDetalle.
                                            add(respuestaDetalle.getValorFacturado()).
                                            setScale(4, RoundingMode.HALF_UP);
                                    
                                    valorTotalPagadoEPS = valorTotalPagadoEPS.
                                            add(respuestaDetalle.getValorPagadoEps()).
                                            setScale(4, RoundingMode.HALF_UP);
                                            
                                    valorTotalAceptadoIPS = valorTotalAceptadoIPS.
                                            add(respuestaDetalle.getValorAceptadoIps()).
                                            setScale(4, RoundingMode.HALF_UP);

                                    bean.auditoriaGuardar(respuestaDetalle);
                                    getCmGlosaRespuestaDetalleRemoto().insertar(respuestaDetalle);

                                    //actualiza: valor pendiente actual detalle - valor pagado respuesta detalle
                                    actualizarValorPendienteActualDetalles(detalle, respuestaDetalle, bean);
                                }
                            }
                            //actualiza : respuesta con acumulados de sumas de detalles
                            respuesta.setValorCobroDetalle(valorFacturadoTotalRespuestaDetalle);
                            respuesta.setValorFacturado(valorFacturadoTotalRespuestaDetalle);
                            respuesta.setValorPagado(valorPagadoTotalRespuestaDetalle);
                            respuesta.setValorPendiente(valorPendienteTotalRespuestaDetalle);
                            respuesta.setValorPagadoEps(valorTotalPagadoEPS);
                            respuesta.setValorAceptadoIps(valorTotalAceptadoIPS);
                            getCmGlosaRespuestaRemoto().actualizar(respuesta);
                            //actualizo pendiente actual CMFACTURAS
                            actualizarValorPendienteActualFactura(cmFactura, respuesta, bean);
                        }
                    }
                }
                CmEnviosGlosasServicioImpl enviosGlosasServicioImpl = new CmEnviosGlosasServicioImpl();
                enviosGlosasServicioImpl.crearRadicadoXConciliaciones(idConciliacion);
                bean.addMensaje(" Se ha creado la respuesta conciliación de manera exitosa de id " + idConciliacion);      
            } 
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarConciliacionIndividual(CmFacturaConciliacionBean bean) {

        try {
            
            CmFactura factura = bean.getObjeto();
            List<CmFactura> registroVerificar = new ArrayList<>();
            registroVerificar.add(factura);
            bean.setRegistrosFacturaSelected(registroVerificar);
            
            if ( verificarBloqueoFacturas(bean) ) {
                
                int idRespuesta = 0;
                
                CmGlosaRespuestaDetalle respuestaDetalle = factura.getCmGlosaRespuestaDetalle();
                CmGlosaRespuesta respuesta = new CmGlosaRespuesta();
                respuesta.setCmFactura(new CmFactura(factura.getId()));
                respuesta.setTipoRespuesta(respuestaDetalle.getTipoEstadoRespuesta());
                String observacionRespuesta = respuestaDetalle.getObservacion() == null
                        || respuestaDetalle.getObservacion().equals("") ? " "
                        : respuestaDetalle.getObservacion();
                respuesta.setObservacion(observacionRespuesta);
                respuesta.setEstadoSincronizacion(CmGlosaRespuesta.ESTADO_SINCRONIZACION_CREADA);
                respuesta.setValorPendiente(new BigDecimal("0"));
                respuesta.setValorPagado(new BigDecimal("0"));
                respuesta.setValorCobroDetalle(new BigDecimal("0"));
                respuesta.setValorFacturado(new BigDecimal("0"));
                respuesta.setValorPagadoEps(new BigDecimal("0"));
                respuesta.setValorAceptadoIps(new BigDecimal("0"));
                respuesta.setRepresentanteEps(respuestaDetalle.getRepresentanteEps());
                respuesta.setRepresentanteIps(respuestaDetalle.getRepresentanteIps());
                bean.auditoriaGuardar(respuesta);
                
                BigDecimal valorPendienteTotalRespuestaDetalle = new BigDecimal("0");
                BigDecimal valorPagadoTotalRespuestaDetalle = new BigDecimal("0");
                BigDecimal valorFacturadoTotalRespuestaDetalle = new BigDecimal("0");
                respuesta.setValorPagadoEps(respuestaDetalle.getValorPagadoEpsPrecalculado());
                respuesta.setValorAceptadoIps(respuestaDetalle.getValorAceptadoIpsPrecalculado());
                idRespuesta = getCmGlosaRespuestaRemoto().insertar(respuesta);
                respuesta.setId(idRespuesta);
                
                for (CmDetalle detalle : bean.getRegistrosDetalles()) {
                    
                    BigDecimal valorEPS = detalle.getValorPagadoEPS() == null
                            ? new BigDecimal("0") : detalle.getValorPagadoEPS();
                    BigDecimal valorIPS = detalle.getValorAceptadoIPS() == null
                            ? new BigDecimal("0") : detalle.getValorAceptadoIPS();
                    
                    String observacionGlosa = detalle.getObservacionGlosa() == null
                            || detalle.getObservacionGlosa().equals("") ? " "
                            : detalle.getObservacionGlosa();
                    
                    detalle.setValorAceptadoIPS(valorIPS);
                    detalle.setValorPagadoEPS(valorEPS);
                    
                    BigDecimal valorPendienteActualDetalle = Optional.ofNullable(detalle.getValorPendienteActual()).orElse(new BigDecimal(BigInteger.ZERO));
                    if (valorPendienteActualDetalle.compareTo(new BigDecimal(BigInteger.ZERO)) > 0
                            && (detalle.getValorPagadoEPS().compareTo(new BigDecimal(BigInteger.ZERO)) > 0
                            || detalle.getValorAceptadoIPS().compareTo(new BigDecimal(BigInteger.ZERO)) > 0)) {
                        respuestaDetalle.setDocumento(detalle.getDocumento());
                        respuestaDetalle.setCmGlosaRespuesta(new CmGlosaRespuesta(idRespuesta));
                        respuestaDetalle.setCmDetalle(new CmDetalle(detalle.getId()));
                        // se cobro detalle y facturado se toman de facturado detalle
                        respuestaDetalle.setValorCobroDetalle(detalle.getValorFacturado());
                        respuestaDetalle.setValorFacturado(detalle.getValorFacturado());
                        respuestaDetalle.setObservacion(observacionGlosa);
                        //se calculan y asignan valores para CMGLOSA_RESPUESTA_DETALLE
                        //(valorPagado, valor pendiente)
                        calcularPorcentajesRespuestaDetalle(detalle, respuestaDetalle, TIPO_CONCILIACION_INDIVIDUAL);
                        
                        valorPagadoTotalRespuestaDetalle = valorPagadoTotalRespuestaDetalle.
                                add(respuestaDetalle.getValorPagado()).
                                setScale(4, RoundingMode.HALF_UP);
                        
                        valorPendienteTotalRespuestaDetalle = valorPendienteTotalRespuestaDetalle.
                                add(respuestaDetalle.getValorPendiente()).
                                setScale(4, RoundingMode.HALF_UP);
                        
                        valorFacturadoTotalRespuestaDetalle = valorFacturadoTotalRespuestaDetalle.
                                add(respuestaDetalle.getValorFacturado()).
                                setScale(4, RoundingMode.HALF_UP);
                        
                        bean.auditoriaGuardar(respuestaDetalle);
                        getCmGlosaRespuestaDetalleRemoto().insertar(respuestaDetalle);

                        //actualiza: valor pendiente actual detalle - valor pagado respuesta detalle
                        actualizarValorPendienteActualDetalles(detalle, respuestaDetalle, bean);
                    }
                }
                //actualiza : respuesta con acumulados de sumas de detalles
                respuesta.setValorCobroDetalle(valorFacturadoTotalRespuestaDetalle);
                respuesta.setValorFacturado(valorFacturadoTotalRespuestaDetalle);
                respuesta.setValorPagado(valorPagadoTotalRespuestaDetalle);
                respuesta.setValorPendiente(valorPendienteTotalRespuestaDetalle);
                getCmGlosaRespuestaRemoto().actualizar(respuesta);

                //actualiza: valor pendente actual factura - valor pagado respuesta
                actualizarValorPendienteActualFactura(factura, respuesta, bean);
                CmEnviosGlosasServicioImpl enviosGlosasServicioImpl = new CmEnviosGlosasServicioImpl();
                enviosGlosasServicioImpl.crearRadicadoXFactura(factura.getId(), CmGlosaRespuesta.TIPO_RESPUESTA_CONCILIACION, idRespuesta);
                bean.addMensaje(" Se creo la respuesta conciliación de manera exitosa con id " + idRespuesta);
            }
            
            bean.setRegistrosFacturaSelected(new ArrayList<>());  
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
      
    private void listar(CmFacturaConciliacionBean bean) {
        try {
            bean.getParamConsulta().setParametroConsulta4(bean.isHabilitarBusquedaMuliUsuario());
            bean.getParamConsulta().setCantidadRegistros(getCmFacturaConciliacionRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getCmFacturaConciliacionRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarDetallesSinPaginar(CmFacturaConciliacionBean bean) {
        try {
            int CONSULTAR_SIN_PAGINAR = 1;
            int CONSULTAR_DETALLES_MAYORES_CERO = 1;
            bean.getParamConsultaServicios().setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsultaServicios().setParametroConsulta2(CONSULTAR_SIN_PAGINAR);
            bean.getParamConsultaServicios().setParametroConsulta3(CONSULTAR_DETALLES_MAYORES_CERO);
            bean.getParamConsultaServicios().setCantidadRegistros(getCmDetalleRemoto().consultarCantidadListaDetallesPorFactura(bean.getParamConsultaServicios()));
            bean.setRegistrosDetalles(getCmDetalleRemoto().consultarListaDetallesPorFactura(bean.getParamConsultaServicios()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarDetalles(CmFacturaConciliacionBean bean) {
        try {
            bean.getParamConsultaServicios().setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsultaServicios().setCantidadRegistros(getCmDetalleRemoto().consultarCantidadListaDetallesPorFactura(bean.getParamConsultaServicios()));
            bean.setRegistrosDetalles(getCmDetalleRemoto().consultarListaDetallesPorFactura(bean.getParamConsultaServicios()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private List<CmDetalle> listarDetallesSinPaginar(CmFacturaConciliacionBean bean, int idFactura) {
        List<CmDetalle> lista = new ArrayList<>();
        try {
            int CONSULTAR_SIN_PAGINAR = 1;
            bean.getParamConsultaServicios().setParametroConsulta1(idFactura);
            bean.getParamConsultaServicios().setParametroConsulta2(CONSULTAR_SIN_PAGINAR);
            lista = getCmDetalleRemoto().consultarListaDetallesPorFactura(bean.getParamConsultaServicios());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return lista;
    }
    
    private void verFactura(CmFacturaConciliacionBean bean) {
        try {
          bean.setObjeto( getCmFacturaRemoto().consultar(bean.getObjeto().getId()) ) ;
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void verRespuestaGlosa(CmFacturaConciliacionBean bean) {
        try {
             bean.getParamConsultaUtilitario().setParametroConsulta1(bean.getCmGlosaRespuesta().getId());
             bean.setCmGlosaRespuesta(getCmGlosaRespuestaRemoto().consultar((int) bean.getParamConsultaUtilitario().getParametroConsulta1()));
             bean.getParamConsultaUtilitario().setParametroConsulta1(null);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void verDetalle(CmFacturaConciliacionBean bean) {
        try {
            bean.setDetalleServicioActual(getCmDetalleRemoto().consultar(bean.getDetalleServicioActual().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
 
    @Override
    public void cargaInial(CmFacturaConciliacionBean bean) {
        try {
            //bean.setUbicacionesRecursiva(getUbicacionRemoto().consultarMunicipios());
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
    }
    
    private void actualizarValorPendienteActualFactura(CmFactura factura,
                                                       CmGlosaRespuesta respuesta,
            CmFacturaConciliacionBean bean) throws Exception {
        
        BigDecimal valorPendienteActual = factura.getValorPendienteActual() == null
                ? new BigDecimal("0") : factura.getValorPendienteActual();

        BigDecimal nuevoPendientaActual = valorPendienteActual.subtract(respuesta.getValorPagado())
                .setScale(4, RoundingMode.HALF_UP);
        
        if(nuevoPendientaActual.compareTo(new BigDecimal("0.1")) <= 0 &&  
            nuevoPendientaActual.compareTo(new BigDecimal("0.0001")) >= 0 ){
            nuevoPendientaActual = new BigDecimal(BigInteger.ZERO);
        }
        factura.setValorPendienteActual(nuevoPendientaActual);

        bean.auditoriaModificar(factura);
        getCmFacturaRemoto().actualizar(factura);
     }
     
    private void actualizarValorPendienteActualDetalles(CmDetalle detalle,
               CmGlosaRespuestaDetalle respuestaDetalle, CmFacturaConciliacionBean bean) throws Exception{ 
            //MathContext mc  = new MathContext(4);
            BigDecimal valorPendienteActual = detalle.getValorPendienteActual() == null ? 
                                              new BigDecimal("0") : detalle.getValorPendienteActual();
            BigDecimal nuevoPendientaActual = valorPendienteActual.subtract(respuestaDetalle.getValorPagado())
                                             .setScale(4, RoundingMode.HALF_UP);
            if(nuevoPendientaActual.equals(new BigDecimal("-0.0001"))){
                nuevoPendientaActual = new BigDecimal(BigInteger.ZERO);
            }
            detalle.setValorPendienteActual(nuevoPendientaActual);
            bean.auditoriaModificar(detalle);
            getCmDetalleRemoto().actualizar(detalle);
     }

    private void calcularPorcentajesRespuestaDetalle(CmDetalle detalle, CmGlosaRespuestaDetalle respuestaDetalle, int tipoConciliacion){

        //MathContext mc  = new MathContext(4);
        BigDecimal valorPendientePagoDetalle = detalle.getValorPendienteActual() == null ? 
                                         new BigDecimal("0") : detalle.getValorPendienteActual() ;
        
        BigDecimal porcentajePagadoEPS = new BigDecimal(BigInteger.ZERO);
        BigDecimal porcentajeAceptadoIPS = new BigDecimal(BigInteger.ZERO);
        respuestaDetalle.setValorPagadoEps(new BigDecimal(BigInteger.ZERO));
        respuestaDetalle.setValorAceptadoIps(new BigDecimal(BigInteger.ZERO));

        if (TIPO_CONCILIACION_INDIVIDUAL == tipoConciliacion) {
            porcentajeAceptadoIPS = detalle.getPorcentajeAceptadoIPS();
            porcentajePagadoEPS  = detalle.getPorcentajePagadoEPS();
        }

        if (TIPO_CONCILIACION_MASIVA == tipoConciliacion) {
            porcentajePagadoEPS = respuestaDetalle.getPorcentajePagadoEps();
            porcentajeAceptadoIPS = respuestaDetalle.getPorcentajeAceptadoIps();
        }
        
        respuestaDetalle.setPorcentajeAceptadoIps(porcentajeAceptadoIPS);
        respuestaDetalle.setPorcentajePagadoEps(porcentajePagadoEPS);
        
        //se calcula porcentaje(ips,eps)  con base al valor pendiente de cada detalle
        if( porcentajePagadoEPS != null ){
            BigDecimal promedio = porcentajePagadoEPS.divide(new  BigDecimal(100), 4, RoundingMode.CEILING);
            respuestaDetalle.setValorPagadoEps(promedio.multiply(valorPendientePagoDetalle).setScale(4, RoundingMode.HALF_UP));
        }
        if( porcentajeAceptadoIPS != null ){
            BigDecimal promedio = porcentajeAceptadoIPS.divide(new  BigDecimal(100), 4, RoundingMode.CEILING);
            respuestaDetalle.setValorAceptadoIps( promedio.multiply(valorPendientePagoDetalle).setScale(4, RoundingMode.HALF_UP));
        }
        
        // Valor pagado = valor aceptado ips + valor pagado eps
         BigDecimal valorPagado = respuestaDetalle.getValorAceptadoIps().
                    add(respuestaDetalle.getValorPagadoEps()).setScale(4, RoundingMode.HALF_UP);
         respuestaDetalle.setValorPagado(valorPagado);
        
         //Valor pendiente = valor pendiente en detalle -  valor pagado(eps+ips)
         BigDecimal valorPendiente = valorPendientePagoDetalle.subtract(respuestaDetalle.getValorPagado())
                                     .setScale(4, RoundingMode.HALF_UP);
         if(valorPendiente.equals(new BigDecimal("-0.0001"))){
            valorPendiente = new BigDecimal(BigInteger.ZERO);
         }
         respuestaDetalle.setValorPendiente(valorPendiente);
              
     }

    private void actualizarBloqueoFacturas(CmFacturaConciliacionBean bean) {
        try {
            getCmFacturaRemoto().actualizarEstadoGestion(bean.getParamConsultaUtilitario());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void actualizarDesbloqueoMasivo(CmFacturaConciliacionBean bean) {
        try {
            bean.setParamConsultaUtilitario(new ParamConsulta());
            StringBuilder sb = new StringBuilder();
            for (CmFactura cmFactura : bean.getRegistrosFacturaSelected()) {
                sb.append(cmFactura.getId()).append(",");
            }
            String idFacturas = "";
            if (sb.length() > 0) {
                idFacturas = sb.deleteCharAt(sb.length() - 1).toString();
            }
           bean.setMensajeGeneral(idFacturas);
           bean.getParamConsultaUtilitario().setParametroConsulta1(idFacturas);
           bean.getParamConsultaUtilitario().setParametroConsulta2(0);
           getCmFacturaRemoto().actualizarUsuarioGestionFacturasMasiva(bean.getParamConsultaUtilitario());
           bean.setParamConsultaUtilitario(new ParamConsulta());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarGlosaRespuesta(CmFacturaConciliacionBean bean) {
        try {
            bean.getParamConsultaRespuestaGlosa().setCantidadRegistros(getCmGlosaRespuestaRemoto().consultarCantidadLista(bean.getParamConsultaRespuestaGlosa()));
            bean.setRegistrosGlosaRespuesta(getCmGlosaRespuestaRemoto().consultarLista(bean.getParamConsultaRespuestaGlosa()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarGlosaRespuestaDetalles(CmFacturaConciliacionBean bean) {
        try {
            bean.getParamConsultaDestallesGlosa().setCantidadRegistros(getCmGlosaRespuestaDetalleRemoto().consultarCantidadLista(bean.getParamConsultaDestallesGlosa()));
            bean.setRegistrosGlosaRespuestaDetalle(getCmGlosaRespuestaDetalleRemoto().consultarLista(bean.getParamConsultaDestallesGlosa()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
     private void listarAuditoriaMotivosGlosa(CmFacturaConciliacionBean bean) {
        try { 
            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(bean.getDetalleServicioActual().getId());
            paramConsulta.setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            paramConsulta.setCantidadRegistros(getCmAuditoriaMotivosGlosaRemoto().consultarCantidadPorDetalle(paramConsulta));
            bean.setRegistrosAuditoriaMotivoGlosa(getCmAuditoriaMotivosGlosaRemoto().consultarListaPorDetalle(paramConsulta));
        }  catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
     private void listarAuditoriaAutorizacion(CmFacturaConciliacionBean bean) {
        try { 
            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(bean.getDetalleServicioActual().getId());
            paramConsulta.setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            paramConsulta.setCantidadRegistros(getCmAuditoriaAutorizacionRemoto().consultarCantidadPorDetalle(paramConsulta));
            bean.setRegistrosAuditoriaAutorizacion(getCmAuditoriaAutorizacionRemoto().consultarListaPorDetalle(paramConsulta));
        }  catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
     private void listarAuditoriaConceptosContables(CmFacturaConciliacionBean bean) {
        try { 
            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(bean.getDetalleServicioActual().getId());
            paramConsulta.setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            paramConsulta.setCantidadRegistros(getCmAuditoriaConceptoContableRemoto().consultarCantidadPorDetalle(paramConsulta));
            bean.setRegistrosConceptoContable(getCmAuditoriaConceptoContableRemoto().consultarListaPorDetalle(paramConsulta));
        }  catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
     private void listarAuditoriaDiagnosticos(CmFacturaConciliacionBean bean) {
        try {
            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(bean.getDetalleServicioActual().getId());
            paramConsulta.setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            paramConsulta.setCantidadRegistros(getCmAuditoriaDiagnosticoRemoto().consultarCantidadPorDetalle(paramConsulta));
            bean.setRegistrosAuditoriaDiagnostico(getCmAuditoriaDiagnosticoRemoto().consultarListaPorDetalle(paramConsulta));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
    private void listarAuditoriaAdjuntosCmDetalles(CmFacturaConciliacionBean bean) {
        try { 
            bean.setParamConsultaUtilitario( new ParamConsulta());
            bean.getParamConsultaUtilitario().setParametroConsulta3(bean.getDetalleServicioActual().getId());
            bean.getParamConsultaUtilitario().setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            bean.getParamConsultaUtilitario().setParametroConsulta4(CmAuditoriaAdjunto.TIPO_DETALLE);
            bean.getParamConsultaUtilitario().setCantidadRegistros(getCmAuditoriaAdjuntoRemoto().consultarCantidadPorDetalle(bean.getParamConsultaUtilitario()));
            bean.setRegistrosAuditoriaAdjutoCmDetalle(getCmAuditoriaAdjuntoRemoto().consultarListaPorDetalle(bean.getParamConsultaUtilitario()));
        }  catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarAdjuntosFactura(CmFacturaConciliacionBean bean) {
        try {
            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(bean.getObjeto().getId());
            paramConsulta.setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            paramConsulta.setParametroConsulta4(CmAuditoriaAdjunto.TIPO_FACTURA);
            paramConsulta.setCantidadRegistros(getCmAuditoriaAdjuntoRemoto().consultarCantidadPorDetalle(paramConsulta));
            bean.setRegistrosAdjuntosFactura(getCmAuditoriaAdjuntoRemoto().consultarListaPorDetalle(paramConsulta));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarDescuentoCapita(CmFacturaConciliacionBean bean) {
        try {
            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(bean.getDetalleServicioActual().getId());
            paramConsulta.setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            paramConsulta.setCantidadRegistros(getCmAuditoriaDescuentoCapitaRemoto().consultarCantidadPorDetalle(paramConsulta));
            bean.setRegistrosDescuentoCapita(getCmAuditoriaDescuentoCapitaRemoto().consultarListaPorDetalle(paramConsulta));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
            
    private void consultarFacturasBloquedas(CmFacturaConciliacionBean bean) {
        try {
            bean.setFacturasBloquedas(getCmFacturaRemoto().consultarFacturasBloquedas(bean.getParamConsultaUtilitario()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void calcularValoresConciliacion(CmConciliacion conciliacionIn, CmConciliacion conciliacionOut ){
    
        BigDecimal procentajeTotal = new BigDecimal("0");
        BigDecimal procentajeCalculadoIPS = new BigDecimal("0");
        BigDecimal procentajeCalculadoEPS = new BigDecimal("0");
        BigDecimal promedioIPS;
        BigDecimal promedioEPS;
        BigDecimal valorLimite = conciliacionIn.getValorFacturasPendienteActualPrecalculado();
        BigDecimal valorPropuestoIPS = conciliacionIn.getValorAceptadoIps();
        BigDecimal valorPropuestoEPS = conciliacionIn.getValorPagadoEps();
        BigDecimal valorTotal ;
        
        if (valorPropuestoIPS.compareTo(new BigDecimal("0")) > 0) {
            promedioIPS = valorPropuestoIPS.divide(valorLimite, 5, RoundingMode.CEILING);
            procentajeCalculadoIPS = promedioIPS.multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_DOWN);
        }
        conciliacionOut.setPorcentajeAceptadoIps(procentajeCalculadoIPS);
         
        if (valorPropuestoEPS.compareTo(new BigDecimal("0")) > 0) {
            promedioEPS = valorPropuestoEPS.divide(valorLimite, 5, RoundingMode.CEILING);
            procentajeCalculadoEPS = promedioEPS.multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_DOWN);
        }  
        conciliacionOut.setPorcentajePagadoEps(procentajeCalculadoEPS);
        
        valorTotal = valorPropuestoIPS.
                add(valorPropuestoEPS).setScale(4, RoundingMode.HALF_UP);
        conciliacionOut.setValor(valorTotal);

        if (valorTotal.compareTo(new BigDecimal("0")) > 0) {
            promedioEPS = valorTotal.divide(valorLimite, 5, RoundingMode.CEILING);
            procentajeTotal = promedioEPS.multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_DOWN);
        }
        conciliacionOut.setPorcentaje(procentajeTotal);

    }
    
    private void reporteRespuestaGlosa(CmFacturaConciliacionBean bean){
        List<ReporteRespuestaGlosa> lista = new ArrayList<>();
        try {
            int idGlosaRespuesta = bean.getReporteRespuestaGlosa().getId();
            boolean repuesta =  getCmGlosaRespuestaRemoto().verificacionEnvioSapExitoso(idGlosaRespuesta);
            if( repuesta ){
               lista = getCmGlosaRespuestaRemoto().reporteRespuestaGlosa(idGlosaRespuesta);
            }else{
                String mensaje = "La respuesta de id "+ idGlosaRespuesta +" no ha sido enviada a Sap, o esta en un estado donde no se permite generar pdf.";
               bean.addError(mensaje);
            }  
        
            bean.setListaReporteRespuestaGlosa(lista);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void reporteConciliacionMasiva(CmFacturaConciliacionBean bean){
        try {
            Map<String,Integer> wsFacturasExitosas  = obtenerWSFacturasExitosasEnviadasSap(bean.getReporteConciliacionMasiva().getId());  
            List<ReporteConciliacion> lista = getCmConciliacionRemoto().reporteConciliacionEnvioSapExitoso(bean.getReporteConciliacionMasiva().getId(),wsFacturasExitosas);
            bean.setListaReporteConsiliacionMasiva(lista);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
      private boolean verificarBloqueoFacturas(CmFacturaConciliacionBean bean) {
        boolean facturaPermitida = true;
        try {
            int usuarioGestiona = bean.getConexion().getUsuario().getId();
            List<String> listIdFacturasEncontradas = new ArrayList<>();
            for (CmFactura cmFactura : bean.getRegistrosFacturaSelected()) {
                listIdFacturasEncontradas.add(String.valueOf(cmFactura.getId()));
            }
            String idsFacturas = String.join(",", listIdFacturasEncontradas);
            bean.getParamConsultaUtilitario().setParametroConsulta1(idsFacturas);
            bean.getParamConsultaUtilitario().setParametroConsulta2(usuarioGestiona);
            String facturasEncontradasBloqueadas = getCmFacturaRemoto().consultarFacturasBloquedas(bean.getParamConsultaUtilitario());
            bean.getParamConsultaUtilitario().setParametroConsulta1(null);
            bean.getParamConsultaUtilitario().setParametroConsulta2(null);
            if (facturasEncontradasBloqueadas != null && facturasEncontradasBloqueadas.length() > 0) {
                    bean.addError("Las siguentes facturas estan bloquedas por favor contacte administrador, "
                            + facturasEncontradasBloqueadas);
                    facturaPermitida = false;
            }
            
        } catch (Exception ex) {
            bean.addError(ex.getMessage());
        }
        return facturaPermitida;
    }
      
      private Map<String, Integer> obtenerWSFacturasExitosasEnviadasSap(int idConciliacion) {
        Map<String, Integer> facturasEnviadasExitosas = new HashMap<>();
          try {
              CmConciliacion conciliacion = getCmConciliacionRemoto().consultar(idConciliacion);
              if (conciliacion.getCmRadicado() != null && conciliacion.getCmRadicado().getId() != null) {
                  List<WsCmFactura> listaCmFacturas = getWsCmFacturaRemoto().consultarTodasPorIdCmRadicado(conciliacion.getCmRadicado().getId());
                  for (WsCmFactura wsfacturas : listaCmFacturas) {
                      if (WsCmFactura.ESTADO_EXITOSO == wsfacturas.getEstado()
                              || WsCmFactura.ESTADO_SIN_VALORES_EPS == wsfacturas.getEstado()) {
                          String llave = wsfacturas.getNumeroDocumento() + "-" + wsfacturas.getNumeroRadicado();
                          if (facturasEnviadasExitosas.get(llave) == null) {
                              facturasEnviadasExitosas.put(llave, 1);
                          }
                      }
                  }
              }
        } catch (Exception e) {
            facturasEnviadasExitosas = new HashMap<>();
        }

        return facturasEnviadasExitosas;
    }

}
