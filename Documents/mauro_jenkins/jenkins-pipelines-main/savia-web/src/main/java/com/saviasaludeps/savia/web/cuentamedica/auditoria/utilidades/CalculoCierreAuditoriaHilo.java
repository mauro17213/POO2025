/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.auditoria.utilidades;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCierre;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMotivoGlosa;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaCierreRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmDetalleRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaRemoto;
import com.saviasaludeps.savia.web.cuentamedica.conciliacion.servicio.CmEnviosGlosasServicioImpl;
import com.saviasaludeps.savia.web.utilidades.Conexion;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jperezn
 */
 public class CalculoCierreAuditoriaHilo extends Thread {

    CmFactura factura;
    private Conexion conexion = null;

  
    public Conexion getConexion() {
        return conexion;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }
    
      
    public CalculoCierreAuditoriaHilo( CmFactura factura, Conexion conexion ) {
      this.factura = factura;
      this.conexion = conexion;
    }

    private CmDetalleRemoto getCmDetalleRemoto() throws Exception {
        return (CmDetalleRemoto) RemotoEJB.getEJBRemoto("CmDetalleServicio", CmDetalleRemoto.class.getName());
    }
    
    private CmFacturaRemoto getCmFacturaRemoto() throws Exception {
        return (CmFacturaRemoto) RemotoEJB.getEJBRemoto("CmFacturaServicio", CmFacturaRemoto.class.getName());
    } 
    
    private CmAuditoriaCierreRemoto getCmAuditoriaCierreRemoto() throws Exception {
        return (CmAuditoriaCierreRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaCierreServicio", CmAuditoriaCierreRemoto.class.getName());
    }
    
    @Override
    public void run() {
        try {          
               
            List<CmDetalle> lista = listarDetallesSinPaginar(factura.getId());

            BigDecimal valorAcumuladoPendienteActual = new BigDecimal("0");
            BigDecimal valorAcumuladoPagoActual = new BigDecimal("0");
            BigDecimal pocentajeMaximoReconocer = new BigDecimal("0");
            BigDecimal porcentajeCienPorCiento = new BigDecimal("100");
            BigDecimal valorGlosar ;
            BigDecimal valorFacturadoPorDetalle;
            boolean hayMotivosParaGlosar;
                    
            int cantidadDetalles = 0;
            
            for (CmDetalle cmDetalle : lista) {
                valorFacturadoPorDetalle = cmDetalle.getValorFacturado();
                if (valorFacturadoPorDetalle.compareTo(new BigDecimal(BigInteger.ZERO)) > 0) {
                    hayMotivosParaGlosar = false;
                    if (cmDetalle.getCantidadMotivosAsociadas() > 0) {
                        pocentajeMaximoReconocer = new BigDecimal("0");
                        for (CmAuditoriaMotivoGlosa motivo : cmDetalle.getListaCmAuditoriaMotivosGlosa()) {
                            pocentajeMaximoReconocer = pocentajeMaximoReconocer.add(motivo.getPorcentaje());
                            hayMotivosParaGlosar = true;
                        }

                        if (hayMotivosParaGlosar) {
                            if (pocentajeMaximoReconocer.compareTo(porcentajeCienPorCiento) < 0) {
                                BigDecimal promedio = pocentajeMaximoReconocer.divide(new BigDecimal(100), 4, RoundingMode.CEILING);
                                valorGlosar = promedio.multiply(valorFacturadoPorDetalle).setScale(4, RoundingMode.HALF_UP);

                                valorAcumuladoPendienteActual = valorAcumuladoPendienteActual.add(valorGlosar).
                                        setScale(4, RoundingMode.HALF_UP);

                                BigDecimal valorParaPagar = valorFacturadoPorDetalle.subtract(valorGlosar)
                                        .setScale(4, RoundingMode.HALF_UP);
                                valorAcumuladoPagoActual = valorAcumuladoPagoActual.add(valorParaPagar).
                                        setScale(4, RoundingMode.HALF_UP);
                                cmDetalle.setValorPendiente(valorGlosar);
                                cmDetalle.setValorPendienteActual(valorGlosar);
                                cmDetalle.setValorPagado(valorParaPagar);
                                cmDetalle.setEstado(CmDetalle.ESTADO_DETALLE_GLOSADO_PARCIAL);
                            }
                            
                            if (pocentajeMaximoReconocer.compareTo(porcentajeCienPorCiento) >= 0) {
                                cmDetalle.setValorPendiente(valorFacturadoPorDetalle);
                                cmDetalle.setValorPendienteActual(valorFacturadoPorDetalle);
                                valorAcumuladoPendienteActual = valorAcumuladoPendienteActual.add(valorFacturadoPorDetalle).
                                        setScale(4, RoundingMode.HALF_UP);
                                cmDetalle.setEstado(CmDetalle.ESTADO_DETALLE_GLOSADO_TOTAL);
                            }
                        }                                            
                    }
                    if (!hayMotivosParaGlosar) {
                            BigDecimal valorParaPagar = valorFacturadoPorDetalle;
                            valorAcumuladoPagoActual = valorAcumuladoPagoActual.add(valorParaPagar).
                                        setScale(4, RoundingMode.HALF_UP);
                            BigDecimal valorPendiente = valorFacturadoPorDetalle.subtract(valorParaPagar)
                                        .setScale(4, RoundingMode.HALF_UP);
                            cmDetalle.setValorPagado(valorParaPagar);
                            cmDetalle.setValorPendiente(valorPendiente);
                            cmDetalle.setValorPendienteActual(valorPendiente);
                            cmDetalle.setEstado(CmDetalle.ESTADO_DETALLE_LISTO_PARA_PAGO);
                    }
                      //actualzar detalle
                      cmDetalle.setUsuarioModifica(getConexion().getUsuario().getUsuarioNombre());
                      cmDetalle.setTerminalModifica(getConexion().getIp());
                      cmDetalle.setFechaHoraModifica(new Date());
                      getCmDetalleRemoto().actualizar(cmDetalle);
                }
                cantidadDetalles++;
            }

            factura.setUsuarioModifica(getConexion().getUsuario().getUsuarioNombre());
            factura.setTerminalModifica(getConexion().getIp());
            factura.setFechaHoraModifica(new Date());
            factura.setValorPagadoFactura(valorAcumuladoPagoActual);
            factura.setValorInicialGlosa(valorAcumuladoPendienteActual);
            factura.setValorPendienteActual(valorAcumuladoPendienteActual);
            //Se quita la asignacion de usurio gestiona para iniciar momento 3
            factura.setUsuarioGestiona(new Usuario(0));
            getCmFacturaRemoto().actualizar(factura);
           
           CmAuditoriaCierre cierre = new CmAuditoriaCierre();
           cierre.setCmFactura(new CmFactura(factura.getId()));
           cierre.setCantidadDetalles(cantidadDetalles);
           cierre.setValorFacturado(factura.getValorFactura());
           cierre.setValorPagado(valorAcumuladoPagoActual);
           cierre.setValorGlosado(valorAcumuladoPendienteActual);
           cierre.setFechaHoraRegistroInicio(new Date());
           cierre.setFechaHoraCrea(new Date());
           cierre.setUsuarioCrea(getConexion().getUsuario().getUsuarioNombre());
           cierre.setTerminalCrea(getConexion().getIp());
           int idCierre = getCmAuditoriaCierreRemoto().insertar(cierre);

           CmEnviosGlosasServicioImpl cmEnviosGlosasServicioImpl = new CmEnviosGlosasServicioImpl();
           cmEnviosGlosasServicioImpl.setConexion(this.conexion);
           cmEnviosGlosasServicioImpl.crearRadicadoXCierreAuditoria(idCierre);
        } catch (Exception e) {
           Logger.getLogger(CmEnviosGlosasServicioImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private List<CmDetalle> listarDetallesSinPaginar( int idFactura ) {
        List<CmDetalle> lista = new ArrayList<>();
        try {
            int CONSULTAR_SIN_PAGINAR = 1;
            ParamConsulta parametroConsulta = new ParamConsulta();
            parametroConsulta.setParametroConsulta1(idFactura);
            parametroConsulta.setParametroConsulta2(CONSULTAR_SIN_PAGINAR);
            lista = getCmDetalleRemoto().consultarListaDetallesPorFactura(parametroConsulta);
        } catch (Exception e) {
            Logger.getLogger(CmEnviosGlosasServicioImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return lista;
    }
      
}
