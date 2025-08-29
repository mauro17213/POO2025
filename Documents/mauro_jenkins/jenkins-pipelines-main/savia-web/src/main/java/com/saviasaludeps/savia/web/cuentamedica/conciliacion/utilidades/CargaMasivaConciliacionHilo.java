/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.conciliacion.utilidades;

import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmConciliacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuesta;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuestaDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmDetalleRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmGlosaRespuestaDetalleRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmGlosaRespuestaRemoto;
import com.saviasaludeps.savia.web.cuentamedica.conciliacion.bean.CargaMasivaConciliacionBean;
import com.saviasaludeps.savia.web.cuentamedica.conciliacion.servicio.CmEnviosGlosasServicioImpl;
import com.saviasaludeps.savia.web.utilidades.Conexion;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
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
 * @author admin
 */
public class CargaMasivaConciliacionHilo extends Thread {
    
    public static final int TIPO_AJUSTE_EPS = 1;
    public static final int TIPO_AJUSTE_IPS = 2;
    public static final int TIPO_AJUSTE_EPS_MENOR = 3;
    public static final int TIPO_AJUSTE_IPS_MENOR = 4;
    public static final int TIPO_CONCILIACION_MASIVA = 2;
    private Map<Integer, BigDecimal> mapAjuste;
    CargaMasivaConciliacionBean bean;
    int idConciliacion;
    private Conexion conexion = null;

    public Map<Integer, BigDecimal> getMapAjuste() {
         if (mapAjuste == null) {
            mapAjuste = new HashMap<>();
        }
        return mapAjuste;
    }

    public void setMapAjuste(Map<Integer, BigDecimal> mapAjuste) {
        this.mapAjuste = mapAjuste;
    } 

    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }
    
      
    public CargaMasivaConciliacionHilo(CargaMasivaConciliacionBean bean, int idConciliacion, Conexion conexion ) {
      this.bean = bean;
      this.idConciliacion = idConciliacion;
      this.conexion = conexion;
    }
    
    private CmGlosaRespuestaRemoto getCmGlosaRespuestaRemoto() throws Exception {
        return (CmGlosaRespuestaRemoto) RemotoEJB.getEJBRemoto("CmGlosaRespuestaServicio", CmGlosaRespuestaRemoto.class.getName());
    }
    
    private CmGlosaRespuestaDetalleRemoto getCmGlosaRespuestaDetalleRemoto() throws Exception {
        return (CmGlosaRespuestaDetalleRemoto) RemotoEJB.getEJBRemoto("CmGlosaRespuestaDetalleServicio", CmGlosaRespuestaDetalleRemoto.class.getName());
    }
    
    private CmDetalleRemoto getCmDetalleRemoto() throws Exception {
        return (CmDetalleRemoto) RemotoEJB.getEJBRemoto("CmDetalleServicio", CmDetalleRemoto.class.getName());
    }
    
    private CmFacturaRemoto getCmFacturaRemoto() throws Exception {
        return (CmFacturaRemoto) RemotoEJB.getEJBRemoto("CmFacturaServicio", CmFacturaRemoto.class.getName());
    } 
    
    @Override
    public void run() {
        try {          
            
            String observacionConciliacion = bean.getCmConciliacion().getObservacion() == null
                        || bean.getCmConciliacion().getObservacion().equals("") ? " "
                        : bean.getCmConciliacion().getObservacion();
            
            String representanteEPS = bean.getCmConciliacion().getRepresentanteEps();
            String representanteIPS = bean.getCmConciliacion().getRepresentanteIps();
            
            BigDecimal valorTotalEpsDeseadoConciliar = bean.getCmConciliacion().getValorPagadoEps()== null ?
                                          new BigDecimal("0") : bean.getCmConciliacion().getValorPagadoEps();
            BigDecimal valorTotalIpsDeseadoConciliar = bean.getCmConciliacion().getValorAceptadoIps()== null ? 
                                          new BigDecimal("0") : bean.getCmConciliacion().getValorAceptadoIps();
            
            for (CmFactura cmFactura : bean.getFacturasEncontradas()) {

                if (cmFactura.getValorPendienteActual() != null
                        && cmFactura.getValorPendienteActual().compareTo(BigDecimal.ZERO) > 0) {

                    List<CmDetalle> detallesPorFactura = listarDetallesSoloValoresSinPaginar(bean, cmFactura.getId());

                    if (!detallesPorFactura.isEmpty()) {
                        setMapAjuste(new HashMap<>());
                        ajusteValores(detallesPorFactura, cmFactura);

                        BigDecimal valorPendienteTotalRespuestaDetalle = new BigDecimal("0");
                        BigDecimal valorPagadoTotalRespuestaDetalle = new BigDecimal("0");
                        BigDecimal valorFacturadoTotalRespuestaDetalle = new BigDecimal("0");
                        BigDecimal valorTotalPagadoEPS = new BigDecimal("0");
                        BigDecimal valorTotalAceptadoIPS = new BigDecimal("0");
                        String observacionFactura = cmFactura.getObservacion() == null
                                || cmFactura.getObservacion().equals("") ? " "
                                : cmFactura.getObservacion();

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
                        
                        if( valorTotalPagadoEPS.compareTo(new BigDecimal("0.0")) > 0 && 
                            valorTotalEpsDeseadoConciliar.compareTo(new BigDecimal("0.0")) > 0){
                            if(valorTotalPagadoEPS.compareTo(valorTotalEpsDeseadoConciliar)> 0){
                                BigDecimal diferencia = valorTotalPagadoEPS.subtract(valorTotalEpsDeseadoConciliar);
                                BigDecimal diferenciaRedondeoImpar = new BigDecimal("0.0001");
                                if(diferencia.compareTo(diferenciaRedondeoImpar) == 0){
                                 respuesta.setValorPagadoEps(valorTotalPagadoEPS.subtract(diferenciaRedondeoImpar));
                                }
                            }
                        }
                        
                        if( valorTotalAceptadoIPS.compareTo(new BigDecimal("0.0")) > 0 && 
                            valorTotalIpsDeseadoConciliar.compareTo(new BigDecimal("0.0")) > 0){
                            if(valorTotalAceptadoIPS.compareTo(valorTotalIpsDeseadoConciliar)> 0){
                                BigDecimal diferencia = valorTotalAceptadoIPS.subtract(valorTotalIpsDeseadoConciliar);
                                BigDecimal diferenciaRedondeoImpar = new BigDecimal("0.0001");
                                if(diferencia.compareTo(diferenciaRedondeoImpar) == 0){
                                 respuesta.setValorAceptadoIps(valorTotalAceptadoIPS.subtract(diferenciaRedondeoImpar));
                                }
                            }
                        }
                                              
                        getCmGlosaRespuestaRemoto().actualizar(respuesta);
                        //actualizo pendiente actual CMFACTURAS
                        actualizarValorPendienteActualFactura(cmFactura, respuesta, bean);
                    }
                }
            }
            
            desbloquearFacturas(bean);
            CmEnviosGlosasServicioImpl cmEnviosGlosasServicioImpl = new CmEnviosGlosasServicioImpl();
            cmEnviosGlosasServicioImpl.setConexion(this.conexion);
            cmEnviosGlosasServicioImpl.crearRadicadoXConciliaciones(idConciliacion);
            bean.setFacturasEncontradas(null);
            bean.setCmConciliacion(null);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private List<CmDetalle> listarDetallesSoloValoresSinPaginar(CargaMasivaConciliacionBean bean, int idFactura) {
        List<CmDetalle> lista = new ArrayList<>();
        try {
            int CONSULTAR_SIN_PAGINAR = 1;
            bean.getParamConsulta().setParametroConsulta1(idFactura);
            bean.getParamConsulta().setParametroConsulta2(CONSULTAR_SIN_PAGINAR);
            lista = getCmDetalleRemoto().consultarDetallesPorFacturaSoloValores(bean.getParamConsulta());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return lista;
    }
    
    private void actualizarValorPendienteActualFactura(CmFactura factura,
                                                       CmGlosaRespuesta respuesta,
            CargaMasivaConciliacionBean bean) throws Exception {
        
        BigDecimal valorPendienteActual = factura.getValorPendienteActual() == null
                ? new BigDecimal("0") : factura.getValorPendienteActual();

        BigDecimal nuevoPendientaActual = valorPendienteActual.subtract(respuesta.getValorPagado())
                .setScale(4, RoundingMode.HALF_UP);
        
        if( nuevoPendientaActual.compareTo(new BigDecimal("0.1")) <= 0 && 
            nuevoPendientaActual.compareTo(new BigDecimal("0.0001")) >= 0){
            nuevoPendientaActual = new BigDecimal(BigInteger.ZERO);
        }
        if( nuevoPendientaActual.compareTo(new BigDecimal("0.1")) <= 0 && 
            nuevoPendientaActual.compareTo(new BigDecimal("-0.0001")) >= 0){
            nuevoPendientaActual = new BigDecimal(BigInteger.ZERO);
        }
        
        factura.setValorPendienteActual(nuevoPendientaActual);

        bean.auditoriaModificar(factura);
        getCmFacturaRemoto().actualizar(factura);
     }
    
    private void actualizarValorPendienteActualDetalles(CmDetalle detalle,
               CmGlosaRespuestaDetalle respuestaDetalle, CargaMasivaConciliacionBean bean) throws Exception{ 
            //MathContext mc  = new MathContext(4);
            BigDecimal valorPendienteActual = detalle.getValorPendienteActual() == null ? 
                                              new BigDecimal("0") : detalle.getValorPendienteActual();
            BigDecimal nuevoPendientaActual = valorPendienteActual.subtract(respuestaDetalle.getValorPagado())
                  .setScale(4, RoundingMode.HALF_UP);
            
            BigDecimal ajusteEPS = new BigDecimal("0.0");
            BigDecimal ajusteEPS_menor = new BigDecimal("0.0");
            BigDecimal ajusteIPS = new BigDecimal("0.0");
            BigDecimal ajusteIPS_menor = new BigDecimal("0.0");
            
            if (!getMapAjuste().isEmpty()) {
               if(getMapAjuste().get(TIPO_AJUSTE_EPS) != null){
                  ajusteEPS = getMapAjuste().get(TIPO_AJUSTE_EPS);
               }
               if (getMapAjuste().get(TIPO_AJUSTE_IPS) != null) {
                  ajusteIPS = getMapAjuste().get(TIPO_AJUSTE_IPS);
               }
               if (getMapAjuste().get(TIPO_AJUSTE_EPS_MENOR) != null) {
                  ajusteEPS_menor = getMapAjuste().get(TIPO_AJUSTE_EPS_MENOR);
               }
               if (getMapAjuste().get(TIPO_AJUSTE_IPS_MENOR) != null) {
                  ajusteIPS_menor = getMapAjuste().get(TIPO_AJUSTE_IPS_MENOR);
               }
            }

            if (nuevoPendientaActual.compareTo(new BigDecimal("0.0")) < 0) {
                BigDecimal deuda = nuevoPendientaActual.abs().setScale(3, RoundingMode.HALF_UP);
                BigDecimal ajusteTotal = ajusteEPS.add(ajusteIPS).add(ajusteEPS_menor).
                                         add(ajusteIPS_menor).setScale(3, RoundingMode.HALF_UP);
                if (ajusteTotal.compareTo(new BigDecimal("0.0")) > 0 && ajusteTotal.compareTo(deuda) >= 0) {
                    nuevoPendientaActual = new BigDecimal("0.0");
                }
            }
            
            if(nuevoPendientaActual.equals(new BigDecimal("-0.0001"))){
              nuevoPendientaActual = new BigDecimal(BigInteger.ZERO);
            }
            detalle.setValorPendienteActual(nuevoPendientaActual);
            bean.auditoriaModificar(detalle);
            getCmDetalleRemoto().actualizarValorPendienteActual(detalle);
     }
    
    public void ajusteValores(List<CmDetalle> detallesPorFactura, CmFactura cmFactura ) {
        BigDecimal valorTotalPagadoEPS = new BigDecimal("0");
        BigDecimal valorTotalAceptadoIPS = new BigDecimal("0");
        CmGlosaRespuestaDetalle respuestaDetalle = new CmGlosaRespuestaDetalle();
        respuestaDetalle.setPorcentajePagadoEps(cmFactura.getPorcentajePagadoEPS());
        respuestaDetalle.setPorcentajeAceptadoIps(cmFactura.getPorcentajeAceptadoIPS());
        int numeroDetallesCalculados = 0;
        for (CmDetalle detalle : detallesPorFactura) {
            BigDecimal valorPendienteActualDetalle = Optional.ofNullable(detalle.getValorPendienteActual()).orElse(new BigDecimal(BigInteger.ZERO));
            if (valorPendienteActualDetalle.compareTo(new BigDecimal(BigInteger.ZERO)) > 0) {
                calcularPorcentajesRespuestaDetalle(detalle, respuestaDetalle, TIPO_CONCILIACION_MASIVA);

                valorTotalPagadoEPS = valorTotalPagadoEPS.
                        add(respuestaDetalle.getValorPagadoEps()).
                        setScale(4, RoundingMode.HALF_UP);

                valorTotalAceptadoIPS = valorTotalAceptadoIPS.
                        add(respuestaDetalle.getValorAceptadoIps()).
                        setScale(4, RoundingMode.HALF_UP);
                numeroDetallesCalculados++;
            }
        }

        if ( valorTotalPagadoEPS.compareTo(new BigDecimal("0.0")) > 0 ) {
            if ( valorTotalPagadoEPS.compareTo(cmFactura.getValorPagadoEPS()) > 0) {
                BigDecimal camparacion = valorTotalPagadoEPS.subtract(cmFactura.getValorPagadoEPS());
                BigDecimal agrearIpsPorRegistro = camparacion.divide(new BigDecimal(numeroDetallesCalculados),5, RoundingMode.CEILING);
                getMapAjuste().put(TIPO_AJUSTE_EPS, agrearIpsPorRegistro);
            }else{
                if ( cmFactura.getValorPagadoEPS()!= null && cmFactura.getValorPagadoEPS().compareTo(valorTotalPagadoEPS) > 0) {
                     BigDecimal camparacion = cmFactura.getValorPagadoEPS().subtract(valorTotalPagadoEPS);
                     BigDecimal agrearIpsPorRegistro = camparacion.divide(new BigDecimal(numeroDetallesCalculados),5, RoundingMode.CEILING);
                     getMapAjuste().put(TIPO_AJUSTE_EPS_MENOR, agrearIpsPorRegistro);
                }
            }
        }

        if (valorTotalAceptadoIPS.compareTo(new BigDecimal("0.0")) > 0) {
            if (valorTotalAceptadoIPS.compareTo(cmFactura.getValorAceptadoIPS()) > 0) {
                BigDecimal camparacion = valorTotalAceptadoIPS.subtract(cmFactura.getValorAceptadoIPS());
                BigDecimal agrearEpsPorRegistro = camparacion.divide(new BigDecimal(numeroDetallesCalculados),5, RoundingMode.CEILING);             
                getMapAjuste().put(TIPO_AJUSTE_IPS, agrearEpsPorRegistro);
            }else{
                if (cmFactura.getValorAceptadoIPS() != null && cmFactura.getValorAceptadoIPS().compareTo(valorTotalAceptadoIPS) > 0) {
                    BigDecimal camparacion = cmFactura.getValorAceptadoIPS().subtract(valorTotalAceptadoIPS);
                    BigDecimal agrearEpsPorRegistro = camparacion.divide(new BigDecimal(numeroDetallesCalculados),5, RoundingMode.CEILING);
                    getMapAjuste().put(TIPO_AJUSTE_IPS_MENOR, agrearEpsPorRegistro);
                }
            }
        }
    }
    
    private void calcularPorcentajesRespuestaDetalle(CmDetalle detalle, CmGlosaRespuestaDetalle respuestaDetalle, int tipoConciliacion){

        BigDecimal valorPendientePagoDetalle = detalle.getValorPendienteActual() == null ? 
                                         new BigDecimal("0") : detalle.getValorPendienteActual() ;
        
        BigDecimal porcentajePagadoEPS = new BigDecimal(BigInteger.ZERO);
        BigDecimal porcentajeAceptadoIPS = new BigDecimal(BigInteger.ZERO);
        respuestaDetalle.setValorPagadoEps(new BigDecimal(BigInteger.ZERO));
        respuestaDetalle.setValorAceptadoIps(new BigDecimal(BigInteger.ZERO));

        if (TIPO_CONCILIACION_MASIVA == tipoConciliacion) {
            porcentajePagadoEPS = respuestaDetalle.getPorcentajePagadoEps();
            porcentajeAceptadoIPS = respuestaDetalle.getPorcentajeAceptadoIps();
        }
        
        respuestaDetalle.setPorcentajeAceptadoIps(porcentajeAceptadoIPS);
        respuestaDetalle.setPorcentajePagadoEps(porcentajePagadoEPS);
        
         BigDecimal ajuste ;
         BigDecimal valorAjusteEPS = new BigDecimal("0.0");
         BigDecimal valorAjusteIPS = new BigDecimal("0.0");
         BigDecimal valorAjusteEPSmenor = new BigDecimal("0.0");
         BigDecimal valorAjusteIPSmenor = new BigDecimal("0.0");
        //se calcula porcentaje(ips,eps)  con base al valor pendiente de cada detalle
        if( porcentajePagadoEPS != null ){
            BigDecimal promedio = porcentajePagadoEPS.divide(new  BigDecimal(100), 4, RoundingMode.CEILING);
            respuestaDetalle.setValorPagadoEps(promedio.multiply(valorPendientePagoDetalle).setScale(4, RoundingMode.HALF_UP));
            
            if( porcentajeAceptadoIPS != null ){
              BigDecimal promedioIps = porcentajeAceptadoIPS.divide(new  BigDecimal(100), 4, RoundingMode.CEILING);
              respuestaDetalle.setValorAceptadoIps( promedioIps.multiply(valorPendientePagoDetalle).setScale(4, RoundingMode.HALF_UP));
            } 
             
            if(!getMapAjuste().isEmpty()){
                //eps detalles eps mayor que sugerido
                if (getMapAjuste().get(TIPO_AJUSTE_EPS) != null) {
                    ajuste = respuestaDetalle.getValorPagadoEps().subtract(getMapAjuste().get(TIPO_AJUSTE_EPS));
                    respuestaDetalle.setValorPagadoEps(ajuste);
                    valorAjusteEPS = getMapAjuste().get(TIPO_AJUSTE_EPS);
                }
                //es mayor el ips de detalles que el sugerido y el eps sugerido es mayor o igual a sugerido
                if ( getMapAjuste().get(TIPO_AJUSTE_EPS_MENOR) != null) {
                    ajuste = respuestaDetalle.getValorPagadoEps().add(getMapAjuste().get(TIPO_AJUSTE_EPS_MENOR));
                    respuestaDetalle.setValorPagadoEps(ajuste);
                    valorAjusteEPSmenor = getMapAjuste().get(TIPO_AJUSTE_EPS_MENOR);
                }      
            }  
        }
        if (porcentajeAceptadoIPS != null) {
            BigDecimal promedio = porcentajeAceptadoIPS.divide(new BigDecimal(100), 4, RoundingMode.CEILING);
            respuestaDetalle.setValorAceptadoIps(promedio.multiply(valorPendientePagoDetalle).setScale(4, RoundingMode.HALF_UP));

            if (!getMapAjuste().isEmpty()) {
                //la eps es sugerida es mayor que la detales
                if (getMapAjuste().get(TIPO_AJUSTE_IPS) != null ) {
                    ajuste = respuestaDetalle.getValorAceptadoIps().subtract(getMapAjuste().get(TIPO_AJUSTE_IPS));
                    respuestaDetalle.setValorAceptadoIps(ajuste);
                    valorAjusteIPS = getMapAjuste().get(TIPO_AJUSTE_IPS);
                }
                
                if (getMapAjuste().get(TIPO_AJUSTE_IPS_MENOR) != null) {
                    ajuste = respuestaDetalle.getValorAceptadoIps().add(getMapAjuste().get(TIPO_AJUSTE_IPS_MENOR));    
                    respuestaDetalle.setValorAceptadoIps(ajuste);
                    valorAjusteIPSmenor = getMapAjuste().get(TIPO_AJUSTE_IPS_MENOR);
                }             
            }
        }
        
        // Valor pagado = valor aceptado ips + valor pagado eps
         BigDecimal valorPagado = respuestaDetalle.getValorAceptadoIps().
                    add(respuestaDetalle.getValorPagadoEps()).setScale(4, RoundingMode.HALF_UP);
         respuestaDetalle.setValorPagado(valorPagado);
        
         //Valor pendiente = valor pendiente en detalle -  valor pagado(eps+ips)
         BigDecimal valorPendiente = valorPendientePagoDetalle.subtract(respuestaDetalle.getValorPagado())
                                     .setScale(4, RoundingMode.HALF_UP);
         
         
         if(valorPendiente.compareTo( new BigDecimal("0.0") ) < 0){
             BigDecimal deuda = valorPendiente.abs().setScale(3, RoundingMode.HALF_UP);
             BigDecimal valorAjusteTotal = valorAjusteEPS.add(valorAjusteIPS).
                            add(valorAjusteEPSmenor).add(valorAjusteIPSmenor).
                            setScale(3, RoundingMode.HALF_UP);
             if(valorAjusteTotal.compareTo(new BigDecimal("0.0")) > 0 &&  valorAjusteTotal.compareTo(deuda) >= 0 ){
                 valorPendiente = new BigDecimal("0.0");
             }
         }
         
         //para distribucion de valores 50% con decimales impares se equilibra a cero
         if(valorPendiente.equals(new BigDecimal("-0.0001"))){
            valorPendiente = new BigDecimal(BigInteger.ZERO);
         }
         respuestaDetalle.setValorPendiente(valorPendiente);
              
     }
    
    private void desbloquearFacturas(CargaMasivaConciliacionBean bean) {
        try {     
            ParamConsulta paramConsultaUtilitario = new ParamConsulta();
            String idFacturas ;
            StringBuilder sb = new StringBuilder();
            for (CmFactura cmFactura : bean.getFacturasEncontradas()) {
                sb.append(cmFactura.getId()).append(",");
            }
            if (sb.length() > 0) {
                idFacturas = sb.deleteCharAt(sb.length() - 1).toString();
                Integer idUsuarioSolicitante = this.getConexion().getUsuario().getId();
                Integer idAsignacionUsuario = 0;
                paramConsultaUtilitario.setParametroConsulta1(idAsignacionUsuario);
                paramConsultaUtilitario.setParametroConsulta2(idUsuarioSolicitante);
                paramConsultaUtilitario.setParametroConsulta3(idFacturas);
            }
            getCmFacturaRemoto().actualizarEstadoGestion(paramConsultaUtilitario);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
      
}
