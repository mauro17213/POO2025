/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.auditoriamasiva.servicio;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegRegistroNovedad;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAdjunto;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAutorizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCapitaDescuento;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaConceptoContable;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDiagnostico;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMasivaModulo;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMotivoGlosa;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaSelectorOperacionMasiva;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmFacturaEstado;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmHistoricoFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmRespuestaGenerica;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeSoporte;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCarga;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadoDiagnostico;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.negocio.administracion.CalendarioRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.ConsultarAfiliadoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.AuditoriaMasivaGenericoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaAutorizacionRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaConceptoContableRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaDescuentoCapitaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaDiagnosticoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaFacturaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaMotivosGlosaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmDetalleRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaRemoto;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.cuentamedica.auditoriamasiva.bean.CmAuditoriaMasivaBean;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.io.IOUtils;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaMasivaModuloRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmFacturaEstadoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmHistoricoFacturaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmFeSoporteRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmRipsCargaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadosProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaMedicamentoRemoto;
import com.saviasaludeps.savia.web.cuentamedica.auditoria.utilidades.CmAuditoriaFacturaBeanUtil;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jperezn
 */
public class CmAuditoriaMasivaServicioImpl extends AccionesBO implements CmAuditoriaMasivaServicioIface {

    public final static int CONSULTA_TODOS_LOS_ITEMS = 1;

    public String postFijoError;

    public String getPostFijoError() {
        return postFijoError;
    }

    public void setPostFijoError(String postFijoError) {
        this.postFijoError = postFijoError;
    }

    private CmAuditoriaMasivaModuloRemoto getCmAuditoriaMasivaModuloRemoto() throws Exception {
        return (CmAuditoriaMasivaModuloRemoto) RemotoEJB.getEJBRemoto(("CmAuditoriaMasivaModuloServicio"), CmAuditoriaMasivaModuloRemoto.class.getName());
    }

    private CmDetalleRemoto getCmDetalleRemoto() throws Exception {
        return (CmDetalleRemoto) RemotoEJB.getEJBRemoto("CmDetalleServicio", CmDetalleRemoto.class.getName());
    }

    private CmFacturaRemoto getCmFacturaRemoto() throws Exception {
        return (CmFacturaRemoto) RemotoEJB.getEJBRemoto("CmFacturaServicio", CmFacturaRemoto.class.getName());
    }

    private CmAuditoriaAdjuntoRemoto getCmAuditoriaAdjuntoRemoto() throws Exception {
        return (CmAuditoriaAdjuntoRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaAdjuntoServicio", CmAuditoriaAdjuntoRemoto.class.getName());
    }

    private CntContratoRemoto getCntContratoRemoto() throws Exception {
        return (CntContratoRemoto) RemotoEJB.getEJBRemoto("CntContratoServicio", CntContratoRemoto.class.getName());
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

    private CmAuditoriaDiagnosticoRemoto getCmAuditoriaDiagnosticoRemoto() throws Exception {
        return (CmAuditoriaDiagnosticoRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaDiagnosticoServicio", CmAuditoriaDiagnosticoRemoto.class.getName());
    }
    
    private MaestroRemoto getMaestroRemoto() throws Exception{
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }
    
    private AuditoriaMasivaGenericoRemoto getAuditoriaMasivaGenericoRemoto() throws Exception {
        return (AuditoriaMasivaGenericoRemoto) RemotoEJB.getEJBRemotoGenerico("AuditoriaMasivaGenericoServicio", AuditoriaMasivaGenericoRemoto.class.getName());
    }
    
    private CmRipsCargaRemoto getCmRipsCargaRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("CmRipsCargaServicio", CmRipsCargaRemoto.class.getName());
        return (CmRipsCargaRemoto) object;
    }
    
    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }
    
    private CmHistoricoFacturaRemoto getCmHistoricoFacturaRemoto() throws Exception {
        return (CmHistoricoFacturaRemoto) RemotoEJB.getEJBRemoto("CmHistoricoFacturaServicio", CmHistoricoFacturaRemoto.class.getName());
    }
    
     private CmFacturaEstadoRemoto getCmFacturaEstadoRemoto() throws Exception {
        return (CmFacturaEstadoRemoto) RemotoEJB.getEJBRemoto("CmFacturaEstadoServicio", CmFacturaEstadoRemoto.class.getName());
    }
     
    private CmAuditoriaDescuentoCapitaRemoto getCmAuditoriaDescuentoCapitaRemoto() throws Exception {
        return (CmAuditoriaDescuentoCapitaRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaDescuentoCapitaServicio", CmAuditoriaDescuentoCapitaRemoto.class.getName());
    }
    
    private PeAfiliadosProgramaRemoto getPeAfiliadosProgramaRemoto() throws Exception {
        return (PeAfiliadosProgramaRemoto) RemotoEJB.getEJBRemoto("PeAfiliadosProgramaServicio", PeAfiliadosProgramaRemoto.class.getName());
    }

    private PeProgramaRemoto getPeProgramaRemoto() throws Exception {
        return (PeProgramaRemoto) RemotoEJB.getEJBRemoto("PeProgramaServicio", PeProgramaRemoto.class.getName());
    }
    
    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }
    
    private CntPrestadorRemoto getPrestadoresRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }
    
    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
        return (AfiliadoRemoto) object;
    }
    
    private ConsultarAfiliadoRemoto getConsultarAfiliadoRemoto() throws Exception {
        return (ConsultarAfiliadoRemoto)RemotoEJB.getEJBRemoto("ConsultarAfiliadoServicio", ConsultarAfiliadoRemoto.class.getName());
    }
    
    private CmAuditoriaFacturaRemoto getCmAuditoriaFacturaRemoto() throws Exception {
        return (CmAuditoriaFacturaRemoto) RemotoEJB.getEJBRemoto(("CmAuditoriaFacturaServicio"), CmAuditoriaFacturaRemoto.class.getName());
    }
    
    private CntContratoRemoto getContratoRemoto() throws Exception {
        return (CntContratoRemoto) RemotoEJB.getEJBRemoto(("CntContratoServicio"), CntContratoRemoto.class.getName());
    }
    
    private CalendarioRemoto getCalendarioRemoto() throws Exception {
        return (CalendarioRemoto) RemotoEJB.getEJBRemoto("CalendarioServicio", CalendarioRemoto.class.getName());
    }
    
    private MaMedicamentoRemoto getMedicamentoRemoto() throws Exception {
        return (MaMedicamentoRemoto) RemotoEJB.getEJBRemoto(("MaMedicamentoServicio"), MaMedicamentoRemoto.class.getName());
    } 
    
     private CmFeSoporteRemoto getCmFeSoporteRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("CmFeSoporteServicio", CmFeSoporteRemoto.class.getName());
        return (CmFeSoporteRemoto) object;
    }
    
    @Override
    public void Accion(CmAuditoriaMasivaBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {

                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;

                case Url.ACCION_CREAR:
                    switch (bean.getDoAccion()) {
                        case CmAuditoriaMasivaBean.ACCION_GUARDAR_ADJUNTOS:
                            guardarAdjunto(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_GUARDAR_AUDITORIA_MASIVA:
                            if( ! bean.isError()){
                                guardarAuditoriaMasiva(bean);
                            }             
                            break;
                        case CmAuditoriaMasivaBean.ACCION_VALIDAR_LEVANTAMIENTOS:
                            validarLevantamientos(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_GUARDAR_LEVANTAMIENTOS:
                            guardarLevantamientos(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_GUARDAR_GESTION_AUDITORIA_MASIVA_DETALLES:
                            guardarAuditoriaMasivaDetalles(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_GUARDAR_LEVANTAMIENTOS_DETALLES:
                            guardarLevantamientosDetalles(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_BLOQUEAR_FACTURAS_POR_USUARIO:
                            bloquearFacturasPorUsuarioEnProcesamiento(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_DESBLOQUEAR_FACTURAS_POR_USUARIO:
                            desbloquearFacturasPorUsuarioEnProcesamiento(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_GUARDAR_AUTORIZACION_AUDITORIA_MASIVA:
                            guardarAuditoriaMasivaAutorizaciones(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_REVERSAR_ESTADO_AUDITORIA:
                            reversarEstadoFacturaParaSinAuditoria(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_BORRAR_MOTIVOS_ESPECIFICOS:
                            guardarLevantamietnoMotivosEspecificos(bean, obtenerIdsMotivosEspecificosStr(bean));
                            guardarEstadosAplicaEnCmDetalles(bean);
                            break;                           
                        case CmAuditoriaMasivaBean.ACCION_GUARDAR_MARCADO_GLOSA_IPS:
                            guardarMarcadoGlosaIps(bean);
                            break;
                         case CmAuditoriaMasivaBean.ACCION_GUARDAR_MARCACION_COPAGO_NO_EFECTIVO:
                            guardarMarcadoCopagoNoEfectivo(bean);
                            break;    
                    }
                    break;

                case Url.ACCION_VER:
                    switch (bean.getDoAccion()) {
                        case CmAuditoriaMasivaBean.ACCION_VER_FACTURA:
                            verFactura(bean);
                            obtenerNumeroContrato(bean);
                            break;
                         case CmAuditoriaMasivaBean.ACCION_CONSULTAR_PROGRAMAS_POR_AFILIADO:
                            consultarProgramasActivosPorAfiliado(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_CONSULTAR_PROGRAMAS_ESPECIFICO_AFILIADO:
                            consultarProgramaEspecificoPorAfiliado(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_LISTAR_DETALLES_POR_FACTURA:
                            listarDetallesPorFactura(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_LISTAR_DETALLES_MULTI_FACTURA:
                            listarDetallesMultiFactura(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_LISTAR_ADJUNTOS_POR_FACTURA:
                            listarAdjuntosPorFactura(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_LISTAR_DIAGNOSTICOS_POR_SERVICIO:
                            listarDiagnosticosPorServicio(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_LISTAR_CONCEPTOS_POR_SERVICIO:
                            listarConceptosContablesPorServicio(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_LISTAR_MOTIVOS_GLOSA_POR_SERVICIO:
                            listarMotivosGlosaPorServicio(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_LISTAR_AUTORIZACIONES_POR_SERVICIO:
                            listarAutorizacionPorServicio(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_VER_DETALLE_SERVICIO:
                            verDetalleServicio(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_VER_MOTIVO_ESPECIFICO_HIJO:
                            bean.setListaMaeMotivosEspecificos(listarMotivosEspecificosHijos(bean));
                            break;
                        case CmAuditoriaMasivaBean.ACCION_VER_MOTIVO_APLICACION_HIJO:
                            bean.setListaMaeMotivosAplicacion(listarMotivosAplicacionHijos(bean));
                            break; 
                        case CmAuditoriaMasivaBean.ACCION_LISTAR_CNT_CONTRATOS:
                            listarCntContratos(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_VER_FACTURAS_SIN_AUTORIZACION:
                            verFacturasSinAutorizacion(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_CONSULTAR_UBICACION_PRESTADOR_SEDE:
                            consultarUbicacionPrestadorSede(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_LISTAR_DETALLES_FALTOS_DE_INSUMOS:
                            listarDetallesFaltoInsumosMultifactura(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_LISTAR_CENTRO_COSTOS_ASOCIADOS:
                            listarCentroCostosAsociados(bean);
                            break; 
                        case CmAuditoriaMasivaBean.ACCION_LISTAR_ANEXOS_4:
                           listarAnexo4(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_LISTAR_ANEXOS4_ITEMS:
                           listarAnexo4Items(bean);
                           break;
                        case CmAuditoriaMasivaBean.ACCION_VERIFICAR_EXISTENCIA_ANEXO4:
                           validarUsoAnexo4EnAuditoria(bean);
                           break;
                        case CmAuditoriaMasivaBean.ACCION_VER_ASEGURAMIENTO_AFILIADO_ID:
                            verAseguramientoAfiliadoID(bean);
                            break;
                         case CmAuditoriaMasivaBean.ACCION_LISTAR_AUDITORIA_NOVEDADES:
                            listarAuditoriaNovedades(bean);
                            break;
                         case CmAuditoriaMasivaBean.ACCION_CONSULTAR_CANTIDAD_DETALLES_PROCESO:
                             consultarNumeroDetallesProceso(bean);
                             break;
                         case CmAuditoriaMasivaBean.ACCION_VER_MOTIVOS_MULTI_DETALLES:         
                            verMotivoEspecificosMultiDetalle(bean, obtenerIdsDetallesStr(bean));
                            break;
                         case CmAuditoriaMasivaBean.ACCION_LISTAR_TODOS_DETALLES_MULTI_FAC:         
                              listarTodosDetallesMultiFactura(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_VER_CONTRATO_SEDES:
                            verContratoConSedes(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_LISTAR_ADJUNTOS_CMDETALLES:
                            listarAdjuntosPorCmDetalle(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_LISTAR_DESCUENTO_CAPITA:
                            listarDescuentoCapita(bean);
                            break;
                        case CmAuditoriaMasivaBean.ACCION_LISTAR_MOTIVOS_ESPECIFICOS_DEVOLUCION:
                            bean.setListaMaeMotivoDevolucionEspecifico(listarMotivosEspecificosDevolucion(bean));
                            break;
                        case CmAuditoriaMasivaBean.DO_ACCION_VER_SOPORTES:
                            verSoportes(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case CmAuditoriaMasivaBean.ACCION_GUARDAR:
                            bloquearFacturasParaAuditar(bean);
                            if (!bean.isError()) {
                                guardarDevolucionMasiva(bean);
                            }
                            break;
                    }
                    break;
            }
        }
    }

    @Override
    public void cargaInicial(CmAuditoriaMasivaBean bean) {
        try {
             
            bean.setUbicacionesRecursiva(UbicacionSingle.getInstance().getHashMunicipios());
            bean.setUbicaciones(UbicacionSingle.getInstance().getListaMunicipios());
            //------------------------
            //en cm_auditoria_conceptos_contables  
            bean.setListaMaeConceptosTotales( getMaestroRemoto().consultarPorTipo(MaestroTipo.CM_CONCEPTO_CONTABLE));
            bean.setListaMaeConceptosContributivos(getMaestroRemoto().consultarListaPorPadre(MaestroTipo.CM_CONCEPTO_CONTABLE, CmFactura.CODIGO_REGIMEN_CONTRIBUTIVO));
            bean.setListaMaeConceptosSubsidiados( getMaestroRemoto().consultarListaPorPadre(MaestroTipo.CM_CONCEPTO_CONTABLE, CmFactura.CODIGO_REGIMEN_SUBSIDIADO));           
            
            bean.setListaMaeCentrosCostos(getMaestroRemoto().consultarPorTipo(MaestroTipo.CM_CENTRO_COSTO));
            // en cm_auditoria_motivos_glosa
            bean.setListaMaeMotivos(getMaestroRemoto().consultarPorTipo(MaestroTipo.CM_MOTIVO_GLOSA));
            List<Maestro> maestroGlosaResolucion3047 = consultarMaestrosPorAccionActivos(MaestroAccion.CM_MOTIVO_GLOSA_RESOLUCION_3047);
            bean.getHashMaeMotivoGlosaPadre().put(MaestroAccion.CM_MOTIVO_GLOSA_RESOLUCION_3047, maestroGlosaResolucion3047); 
            List<Maestro> maestroGlosaResolucion2284 =  consultarMaestrosPorAccionActivos(MaestroAccion.CM_MOTIVO_GLOSA_RESOLUCION_2284); 
            bean.getHashMaeMotivoGlosaPadre().put(MaestroAccion.CM_MOTIVO_GLOSA_RESOLUCION_2284, maestroGlosaResolucion2284);
                    
            // en cm_auditoria_devolucion
            List<Maestro> maestroResolucion3047 =  consultarMaestrosPorAccionActivos(MaestroAccion.CM_DEVOLUCION_MOTIVO_RESOLUCION_3047);
            bean.getHashMaeMotivoDevolucionPadre().put(MaestroAccion.CM_DEVOLUCION_MOTIVO_RESOLUCION_3047, maestroResolucion3047);               
            List<Maestro> maestroResolucion2284 =  consultarMaestrosPorAccionActivos(MaestroAccion.CM_DEVOLUCION_MOTIVO_RESOLUCION_2284);
            bean.getHashMaeMotivoDevolucionPadre().put(MaestroAccion.CM_DEVOLUCION_MOTIVO_RESOLUCION_2284, maestroResolucion2284);
                       
            listarUsuariosGestiona(bean);
            bean.setHashTipoSoporte(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.RIPS_TIPO_SOPORTE));
     
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void verFactura(CmAuditoriaMasivaBean bean) {
        try {
            bean.setObjetoFactura(getCmFacturaRemoto().consultar(bean.getObjetoFactura().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void verDetalleServicio(CmAuditoriaMasivaBean bean) {
        try {
            bean.setObjetoDetalleServicio(getCmDetalleRemoto().consultar(bean.getObjetoDetalleServicio().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void verFacturasSinAutorizacion(CmAuditoriaMasivaBean bean) {
        try {
            ParamConsulta parametro = new ParamConsulta();
            String idFacturas =  Stream.of(bean.getObjeto().getIdsFacturas().toArray()).map(String::valueOf).collect(Collectors.joining(","));
            parametro.setParametroConsulta1(idFacturas);
            bean.setRegistrosFacturasSinAutorizaciones(getCmAuditoriaMasivaModuloRemoto().consultarFacturasSinAutorizacion(parametro));  
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
     private void verMotivoEspecificosMultiDetalle(CmAuditoriaMasivaBean bean, String idsDetalles) {
        try {
            if (!"".equals(idsDetalles)) {
                bean.setListaMotivosEspecificosEncontrados(getCmAuditoriaMotivosGlosaRemoto().consultarPorMultiDetalles(idsDetalles));
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }  
     
    private void verContratoConSedes(CmAuditoriaMasivaBean bean) {
        try {
            bean.setContratoConSedes(getContratoRemoto().consultarConSedes(bean.getContratoConSedes().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void validarUsoAnexo4EnAuditoria(CmAuditoriaMasivaBean bean) {
        try { 
            ParamConsulta parametroConsulta = new ParamConsulta();
            parametroConsulta.setRegistrosPagina(1);
            parametroConsulta.setFiltros(new HashMap<>());
            parametroConsulta.getFiltros().put("auAnexos4Id", bean.getCmAuditoriaAutorizacion().getAnexo4().getId());
            List<CmAuditoriaAutorizacion> lista =  getCmAuditoriaAutorizacionRemoto().consultarLista(parametroConsulta);
            StringBuilder str = new StringBuilder();
            for (CmAuditoriaAutorizacion autorizacion : lista) {
               str.append("La autorización esta siendo utilizada por una factura : ");
               if(autorizacion.getCmFactura().getNumeroFacturado() !=null){
                  str.append("De número de factura: ");
                  str.append(autorizacion.getCmFactura().getNumeroFacturado());
               }
               str.append(" De detalle de id : ");
               str.append(autorizacion.getCmDetalle().getId());
            }
            bean.setCmRespuestaGenerica( new CmRespuestaGenerica());
            bean.getCmRespuestaGenerica().setEstadoRespuesta(!lista.isEmpty());
            bean.getCmRespuestaGenerica().setMensaje(str.toString());
        
        }  catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void validarLevantamientos(CmAuditoriaMasivaBean bean) {
        try {
             CmAuditoriaMasivaModulo copiaAuditoriaMasiva = (CmAuditoriaMasivaModulo) bean.getObjeto().clone();
             bean.setEjecucionExitosaOperacion(getAuditoriaMasivaGenericoRemoto().validarLevantamientos(copiaAuditoriaMasiva));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private boolean validarCmDetallesSinEstadoAnulado(CmAuditoriaMasivaBean bean, String idCmFactura) {
        boolean isValido = true;
        String ESTADO_ANULADO_AUTORIZACION = "2,5";
        int CANTIDAD_DETALLES_MOSTRAR = 5;
        try {
            List<CmDetalle> detallesConAutorizacionAnulada = consultarCmDetallesPorEstadoAutorizacion(idCmFactura, ESTADO_ANULADO_AUTORIZACION);

            if (!detallesConAutorizacionAnulada.isEmpty()) {
                isValido = false;
                String mensaje = "Los detalles de id : ( ";
                int cantidadDetallesMostras = 0;
                for (CmDetalle cmDetalle : detallesConAutorizacionAnulada) {
                    mensaje += cmDetalle.getId() + " ";
                    cantidadDetallesMostras++;
                    if (cantidadDetallesMostras > CANTIDAD_DETALLES_MOSTRAR) {
                        break;
                    }
                }
                mensaje += ") están asociados con autorizaciones anuladas,"
                        + " no es posible cerrar la auditoría.";
                bean.addError(mensaje);
            }

        } catch (Exception e) {
            isValido = true;
        }
        return isValido;
    }
    
     private void verSoportes(CmAuditoriaMasivaBean bean) {
        try {
            bean.getParamConsultaSoportes().setCantidadRegistros(getCmFeSoporteRemoto().consultarCantidadLista(bean.getParamConsultaSoportes()));
            bean.setRegistrosSoportes((ArrayList<CmFeSoporte>) getCmFeSoporteRemoto().consultarLista(bean.getParamConsultaSoportes()));
        } catch (Exception ex) {
            bean.addError("Error versoportes: " + ex.getMessage());
        }
    }
    
    private void bloquearFacturasParaAuditar(CmAuditoriaMasivaBean bean) {
        try {
            ParamConsulta parametro = new ParamConsulta();
            String idFacturas = Stream.of(bean.getObjeto().getIdsFacturas().toArray()).map(String::valueOf).collect(Collectors.joining(","));
            parametro.setParametroConsulta1(idFacturas);
            parametro.setParametroConsulta3(CmFactura.ESTADO_FACTURA_EN_AUDITORIA);
            getCmAuditoriaMasivaModuloRemoto().actualizarEstadoAuditoriaMasivo(parametro);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listar(CmAuditoriaMasivaBean bean) {
        try {
            bean.getParamConsulta().setCantidadRegistros(getCmAuditoriaMasivaModuloRemoto().consultarCantidadFacturasLista(bean.getParamConsulta()));
            bean.setRegistros(getCmAuditoriaMasivaModuloRemoto().consultarFacturasLista(bean.getParamConsulta()));
            asignarDiasRadicacionSegunEstado(bean.getRegistros());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
 
    private void listarUsuariosGestiona(CmAuditoriaMasivaBean bean) {
        try {
            bean.setUsuariosGestiona(getCmAuditoriaFacturaRemoto().consultaUsuariosCmFacturaSegunPeril(CmFactura.TIPO_USUARIO_GESTIONA));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listarDetallesPorFactura(CmAuditoriaMasivaBean bean) {
        try {
            bean.getParamConsultaDetallesPorFactura().setCantidadRegistros(getCmDetalleRemoto().consultarCantidadListaDetallesPorFactura(bean.getParamConsultaDetallesPorFactura()));
            bean.setRegistrosAuditoriaDetalles(getCmDetalleRemoto().consultarListaDetallesPorFactura(bean.getParamConsultaDetallesPorFactura()));
            
            asignarAtributosParaTipoMedicamento(bean.getRegistrosAuditoriaDetalles());  
            CmAuditoriaFacturaBeanUtil.asignarCodigoMipres(bean.getRegistrosAuditoriaDetalles());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarDetallesMultiFactura(CmAuditoriaMasivaBean bean) {
        try {
            bean.getParamConsultaDetallesPorFactura().setCantidadRegistros(getCmDetalleRemoto().consultarCantidadListaDetallesMultiFactura(bean.getParamConsultaDetallesPorFactura()));
            bean.setRegistrosAuditoriaDetalles(getCmDetalleRemoto().consultarListaDetallesMultiFactura(bean.getParamConsultaDetallesPorFactura()));
            
            asignarAtributosParaTipoMedicamento(bean.getRegistrosAuditoriaDetalles());
            
            CmAuditoriaFacturaBeanUtil.asignarCodigoMipres(bean.getRegistrosAuditoriaDetalles());
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarTodosDetallesMultiFactura(CmAuditoriaMasivaBean bean) {
        try {
            String idsFacturas = Stream.of(bean.getObjeto().getIdsFacturas().toArray()).map(String::valueOf).collect(Collectors.joining(","));
            bean.setRegistrosDetallesSeleccionadoMasivos(getCmDetalleRemoto().consultarTodosDetallesMultiFactura(idsFacturas));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarDetallesFaltoInsumosMultifactura(CmAuditoriaMasivaBean bean) {
        try {
            ParamConsulta parametro = new ParamConsulta();
            String idFacturas = Stream.of(bean.getObjeto().getIdsFacturas().toArray()).map(String::valueOf).collect(Collectors.joining(","));
            parametro.setParametroConsulta1(  idFacturas );
            bean.setRegistrosTodosAuditoriaDetallesMultifactura(getCmDetalleRemoto().consultarListaDetallesMultiFacturaFaltoInsumosParaAuditar(parametro));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listarAdjuntosPorFactura(CmAuditoriaMasivaBean bean) {
        try {
            bean.getParamConsultaUtilitario().setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            bean.getParamConsultaUtilitario().setParametroConsulta4(CmAuditoriaAdjunto.TIPO_FACTURA);
            bean.getParamConsultaUtilitario().setCantidadRegistros(getCmAuditoriaAdjuntoRemoto().consultarCantidadPorDetalle(bean.getParamConsultaUtilitario()));
            bean.setRegistrosAuditoriaAdjuto(getCmAuditoriaAdjuntoRemoto().consultarListaPorDetalle(bean.getParamConsultaUtilitario()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarAdjuntosPorCmDetalle(CmAuditoriaMasivaBean bean) {
        try {
            bean.getParamConsultaUtilitario().setParametroConsulta4(CmAuditoriaAdjunto.TIPO_DETALLE);
            bean.getParamConsultaUtilitario().setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            bean.getParamConsultaUtilitario().setCantidadRegistros(getCmAuditoriaAdjuntoRemoto().consultarCantidadPorDetalle(bean.getParamConsultaUtilitario()));
            bean.setRegistrosAuditoriaAdjutoCmDetalle(getCmAuditoriaAdjuntoRemoto().consultarListaPorDetalle(bean.getParamConsultaUtilitario()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listarConceptosContablesPorServicio(CmAuditoriaMasivaBean bean) {
        try {
            bean.getParamConsultaUtilitario().setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            bean.getParamConsultaUtilitario().setCantidadRegistros(getCmAuditoriaConceptoContableRemoto().consultarCantidadPorDetalle(bean.getParamConsultaUtilitario()));
            bean.setRegistrosConceptoContable(getCmAuditoriaConceptoContableRemoto().consultarListaPorDetalle(bean.getParamConsultaUtilitario()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listarMotivosGlosaPorServicio(CmAuditoriaMasivaBean bean) {
        try {
            bean.getParamConsultaUtilitario().setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            bean.getParamConsultaUtilitario().setCantidadRegistros(getCmAuditoriaMotivosGlosaRemoto().consultarCantidadPorDetalle(bean.getParamConsultaUtilitario()));
            bean.setRegistrosAuditoriaMotivoGlosa(getCmAuditoriaMotivosGlosaRemoto().consultarListaPorDetalle(bean.getParamConsultaUtilitario()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listarAutorizacionPorServicio(CmAuditoriaMasivaBean bean) {
        try {
            bean.getParamConsultaUtilitario().setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            bean.getParamConsultaUtilitario().setCantidadRegistros(getCmAuditoriaAutorizacionRemoto().consultarCantidadPorDetalle(bean.getParamConsultaUtilitario()));
            bean.setRegistrosAuditoriaAutorizacion(getCmAuditoriaAutorizacionRemoto().consultarListaPorDetalle(bean.getParamConsultaUtilitario()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listarDiagnosticosPorServicio(CmAuditoriaMasivaBean bean) {
        try {
            bean.getParamConsultaUtilitario().setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            bean.getParamConsultaUtilitario().setCantidadRegistros(getCmAuditoriaDiagnosticoRemoto().consultarCantidadPorDetalle(bean.getParamConsultaUtilitario()));
            bean.setRegistrosAuditoriaDiagnostico(getCmAuditoriaDiagnosticoRemoto().consultarListaPorDetalle(bean.getParamConsultaUtilitario()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
 
    private List<Maestro> listarMotivosEspecificosHijos(CmAuditoriaMasivaBean bean) {
        List<Maestro> lista = new ArrayList<>();
        try {
            lista = getMaestroRemoto().consultarListaPorPadre(MaestroTipo.CM_MOTIVO_GLOSA_ESPEFIFICO, bean.getCmAuditoriaMotivoGlosa().getMaestroMotivo().getId());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        lista = obtenerListaFiltradaPorAccionSegunResolucion(bean, lista);    
        return lista;
    }
    
    private List<Maestro> listarMotivosAplicacionHijos(CmAuditoriaMasivaBean bean) {
        List<Maestro> lista = new ArrayList<>();
        try {
            lista = getMaestroRemoto().consultarListaPorPadre(MaestroTipo.CM_MOTIVO_GLOSA_APLICACION, bean.getCmAuditoriaMotivoGlosa().getMaestroMotivoEspecifico().getId());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return lista;
    }
    
    private void listarCntContratos(CmAuditoriaMasivaBean bean) {
        try { 
            bean.getParamConsultaCntContratos().setCantidadRegistros(getCntContratoRemoto().consultarCantidadLista(bean.getParamConsultaCntContratos()));
            bean.setRegistrosCntContratos(getCntContratoRemoto().consultarLista(bean.getParamConsultaCntContratos()));
        }  catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarCentroCostosAsociados(CmAuditoriaMasivaBean bean) {
        List<Maestro> lista;
        try {
            lista =  getMaestroRemoto().consultarListaPorPadre(MaestroTipo.CM_CENTRO_COSTO, bean.getCmAuditoriaConceptoContable().getMaestroConceptos().getId());             
            bean.setListaMaeCentrosCostos(lista); 
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarAnexo4(CmAuditoriaMasivaBean bean) {
        try {          
            String  numeroDocumento = bean.getRegistrosDetallesSeleccionadoMasivos().isEmpty() ? null:
                                 bean.getRegistrosDetallesSeleccionadoMasivos().get(0).getDocumento();
            
            if (numeroDocumento != null) {
                asignarFiltroBusquedaPrestadorFactura(bean);
                bean.getParamConsultaAnexo4().setParametroConsulta1(numeroDocumento);
                bean.getParamConsultaAnexo4().setParametroConsulta3(true);
                bean.getParamConsultaAnexo4().setCantidadRegistros(getCmAuditoriaAutorizacionRemoto().consultarCantidadAnexo4PorDocumento(bean.getParamConsultaAnexo4()));
                bean.setRegistrosAnexo4(getCmAuditoriaAutorizacionRemoto().consultarListaAnexo4PorDocumento(bean.getParamConsultaAnexo4()));
            }else{
                 bean.addError("Se han presentado problemas para obtener el numero de documento para consultar anexo4");
            }
          
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
      
    private void listarAnexo4Items(CmAuditoriaMasivaBean bean) {
        try {
            if (bean.getAuAnexo4() != null && bean.getAuAnexo4().getId() != null ) {
                bean.getParamConsultaAnexo4Item().setParametroConsulta2(bean.getAuAnexo4().getId());
                bean.getParamConsultaAnexo4Item().setCantidadRegistros(getCmAuditoriaAutorizacionRemoto().consultarCantidadAnexo4ItemsPorAtributos(bean.getParamConsultaAnexo4Item()));
                bean.setRegistrosAnexo4Item(getCmAuditoriaAutorizacionRemoto().consultarListaAnexo4ItemsPorAtributos(bean.getParamConsultaAnexo4Item()));
            } else {
                bean.addError("Se han presentado problemas para obtener el id anexo4 para consultar anexo4 Items");
            }    
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listarDescuentoCapita(CmAuditoriaMasivaBean bean) {
        try {
            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(bean.getObjetoDetalleServicio().getId());
            paramConsulta.setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            paramConsulta.setCantidadRegistros(getCmAuditoriaDescuentoCapitaRemoto().consultarCantidadPorDetalle(paramConsulta));
            bean.setRegistrosAuditoriaCapitaDescuento(getCmAuditoriaDescuentoCapitaRemoto().consultarListaPorDetalle(paramConsulta));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private List<Maestro> listarMotivosEspecificosDevolucion(CmAuditoriaMasivaBean bean) {
        List<Maestro> lista = new ArrayList<>();
        try {
            lista =  getMaestroRemoto().consultarListaPorPadre(MaestroTipo.CM_DEVOLUCION_MOTIVO, bean.getCmAuditoriaDevolucion().getMaestroMotivoDevolucion().getId()); 
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return lista;
    }
     
    private void guardarAdjunto(CmAuditoriaMasivaBean bean) {
        try {
             StringBuilder errorCopiado =  new StringBuilder();
             if( ! copiarArchivosEnDisco(bean.getAdjuntosParaGuardar(), errorCopiado)){       
                 bean.addError(errorCopiado.toString());  
             }

            for (CmFactura factura : bean.getRegistrosFacturasSeleccionadas()) {
                for (CmAuditoriaAdjunto adjunto : bean.getAdjuntosParaGuardar()) {
                    if (adjunto.isGuardadoEnDisco() && !adjunto.isGuardadoEnDB()) {
                        adjunto.setCmFactura(new CmFactura(factura.getId()));
                        bean.auditoriaGuardar(adjunto);
                        Integer idAdjunto = getCmAuditoriaAdjuntoRemoto().insertar(adjunto);
                        if (bean.getObjeto().getListaFacturasPorAdjunto().get(adjunto.getPosInsertar()) == null) {
                            bean.getObjeto().getListaFacturasPorAdjunto().
                                    put(adjunto.getPosInsertar(), new ArrayList<>());
                            bean.getObjeto().getListaFacturasPorAdjunto().get(adjunto.getPosInsertar()).add(idAdjunto);
                        } else {
                            bean.getObjeto().getListaFacturasPorAdjunto().get(adjunto.getPosInsertar()).add(idAdjunto);
                        }
                    }
                }
            }
       
            for (CmAuditoriaAdjunto cmAuditoriaAdjunto : bean.getAdjuntosParaGuardar()) {
                if (cmAuditoriaAdjunto.isGuardadoEnDisco()) {
                    cmAuditoriaAdjunto.setGuardadoEnDB(true);
                }
            }
            
            for (Integer idAdjuntoPadre : bean.getObjeto().getListaAdjuntosEliminar()) {
                List<Integer> listaAdjuntosBorrar = bean.getObjeto().getListaFacturasPorAdjunto().get(idAdjuntoPadre);                   
                listaAdjuntosBorrar = listaAdjuntosBorrar == null ? new ArrayList<>() : listaAdjuntosBorrar;      
                for (Integer idAdjuntoFactura : listaAdjuntosBorrar) {
                    for (CmFactura factura : bean.getRegistrosFacturasSeleccionadas()) {
                        getCmAuditoriaAdjuntoRemoto().eliminarSegunFactura(idAdjuntoFactura, factura.getId());
                    }
                }
                if( ! listaAdjuntosBorrar.isEmpty() ){
                 bean.getObjeto().getListaFacturasPorAdjunto().get(idAdjuntoPadre).clear();
                } 
            }
            bean.getObjeto().getListaAdjuntosEliminar().clear();         
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private boolean copiarArchivosEnDisco(List<CmAuditoriaAdjunto> cmAuditoriaAdjuntos, StringBuilder errores) {
        boolean copiadoExitoso = true;
        errores = Optional.ofNullable(errores).orElse(new StringBuilder());
        for (CmAuditoriaAdjunto cmAuditoriaAdjunto : cmAuditoriaAdjuntos) {
            if (!cmAuditoriaAdjunto.isGuardadoEnDisco()) {
                cmAuditoriaAdjunto.setGuardadoEnDisco(crearArchivo(cmAuditoriaAdjunto));
                if (!cmAuditoriaAdjunto.isGuardadoEnDisco()) {
                    errores.append("No se ha podido guardar el adjunto de nombre ").
                            append(cmAuditoriaAdjunto.getArchivoNombre()).
                            append(" - ");
                    copiadoExitoso = false;
                }
            }
        }
        return copiadoExitoso;
    }
    
    private void guardarLevantamientos(CmAuditoriaMasivaBean bean) {
        try {
            CmAuditoriaMasivaModulo copiaAuditoriaMasiva = (CmAuditoriaMasivaModulo) bean.getObjeto().clone();
            bean.auditoriaGuardar(copiaAuditoriaMasiva);
            bean.setEjecucionExitosaOperacion(getAuditoriaMasivaGenericoRemoto().ejecutarLevantamientos(copiaAuditoriaMasiva));
        } catch (Exception e) {
            bean.addError("Se ha presentado error al realizar levantamiento:"+e.getMessage());
        }
    }
    
     private void guardarLevantamietnoMotivosEspecificos(CmAuditoriaMasivaBean bean, String codigosMotivosStr) {
        try {
            boolean hayEliminacion = false;
            for (CmDetalle detalle : bean.getRegistrosDetallesSeleccionadoMasivos()) {
                getCmAuditoriaMotivosGlosaRemoto().eliminarPorCodigoEspecifico(codigosMotivosStr, detalle.getId());
                hayEliminacion = true;
            }

            if (hayEliminacion) {
                bean.addMensaje("Se ha realizado la eliminación de los motivos seleccionados.");
            }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarLevantamientosDetalles(CmAuditoriaMasivaBean bean) {
        try {
            for (CmDetalle detalle : bean.getRegistrosDetallesSeleccionadoMasivos()) {
                CmHistoricoFactura historico = new CmHistoricoFactura();
                historico.setCmFactura(new CmFactura(detalle.getCmFacturas().getId()));
                historico.setDescripcion("Eliminación de dato");
                bean.auditoriaGuardar(historico);
                boolean hayBorradoConceptos = false;
                boolean hayBorradoDiagnosticos = false;
                boolean hayBorradoMotivos = false;
                boolean hayBorradoAutorizacion = false;
                boolean hayBorradoDescuentoCapita = false;
                switch (bean.getCmSelectorOperacionaMasivaDetalles().getTipoOperacion()) {
                    case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_CONCEPTOS_CONTABLES:
                        for (CmAuditoriaConceptoContable concepto : detalle.getListaCmAuditoriaConceptoContable()) {
                            if (concepto.getId() != null) {
                                guardarHistorialBorrado(historico, concepto.toString(), CmHistoricoFactura.TIPO_CONCEPTO_CONTABLE);
                                getCmAuditoriaConceptoContableRemoto().eliminar(concepto.getId());
                                hayBorradoConceptos = true;
                            }
                        }

                        break;
                    case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_DIAGNOSTICOS:
                        for (CmAuditoriaDiagnostico diagnostico : detalle.getListaCmAuditoriaDiagnosticos()) {
                            getCmAuditoriaDiagnosticoRemoto().eliminar(diagnostico.getId());
                            historico.setTipos(CmHistoricoFactura.TIPO_DIAGNOSTICO);
                            historico.setCmHistoricoFacturas(diagnostico.toString());
                            getCmHistoricoFacturaRemoto().insertar(historico);
                            hayBorradoDiagnosticos = true;
                        }

                        break;
                    case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_MOTIVOS:
                        for (CmAuditoriaMotivoGlosa motivo : detalle.getListaCmAuditoriaMotivosGlosa()) {
                            getCmAuditoriaMotivosGlosaRemoto().eliminar(motivo.getId());
                            historico.setTipos(CmHistoricoFactura.TIPO_MOTIVO_GLOSA);
                            historico.setCmHistoricoFacturas(motivo.toString());
                            getCmHistoricoFacturaRemoto().insertar(historico);
                            hayBorradoMotivos = true;
                        }
                        break;
                    case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_AUTORIZACIONES:
                        for (CmAuditoriaAutorizacion autorizacion : detalle.getListacmAuditoriaAutorizacion()) {
                            getCmAuditoriaAutorizacionRemoto().eliminar(autorizacion.getId());
                            historico.setTipos(CmHistoricoFactura.TIPO_AUTORIZACION);
                            historico.setCmHistoricoFacturas(autorizacion.toString());
                            getCmHistoricoFacturaRemoto().insertar(historico);
                            hayBorradoAutorizacion = true;
                        }
                        break;
                     case CmAuditoriaSelectorOperacionMasiva.TIPO_OPERACION_LEVANTAMIENTO_DESCUENTO_CAPITA:
                        for (CmAuditoriaCapitaDescuento descuentoCapita : detalle.getListaCmAuditoriaDescuentoCapita()) {
                            getCmAuditoriaDescuentoCapitaRemoto().eliminar(descuentoCapita.getId());
                            historico.setTipos(CmHistoricoFactura.TIPO_DESCUENTO_CAPITA);
                            historico.setCmHistoricoFacturas(descuentoCapita.toString());
                            getCmHistoricoFacturaRemoto().insertar(historico);
                            hayBorradoDescuentoCapita = true;
                        }
                        break;
                }

                if (hayBorradoConceptos) {
                    detalle.setAplicaConcepto(false);
                    detalle.setConceptoContable("");
                    getCmDetalleRemoto().actualizar(detalle);
                }

                if (hayBorradoDiagnosticos) {
                    detalle.setAplicaDx(false);
                    detalle.setNombreDx("");
                    getCmDetalleRemoto().actualizar(detalle);
                }
                if (hayBorradoMotivos) {
                    detalle.setAplicaGlosa(false);
                    detalle.setMotivoGlosa("");
                    detalle.setObservacion("");
                    detalle.setValorGlosa(null);
                    getCmDetalleRemoto().actualizar(detalle);
                }
                if (hayBorradoAutorizacion) {
                    detalle.setAplicaAutorizacion(false);
                    getCmDetalleRemoto().actualizar(detalle);
                }
                 if (hayBorradoDescuentoCapita) {
                    detalle.setAplicaDc(false);
                    getCmDetalleRemoto().actualizar(detalle);
                }
            }
        } catch (Exception e) {
            bean.addError("Se ha presentado error al realizar levantamiento detalles:" + e.getMessage());
        }
    }
    
    private void guardarAuditoriaMasiva(CmAuditoriaMasivaBean bean) {
        try {
            StringBuilder errorCopiado =  new StringBuilder();
            if( ! copiarArchivosEnDisco(bean.getObjeto().getRegistrosAuditoriaAdjutoCmDetalle(),  errorCopiado)){
                 bean.addError(errorCopiado.toString()); 
            }
            
            CmAuditoriaMasivaModulo copiaAuditoriaMasiva = (CmAuditoriaMasivaModulo) bean.getObjeto().clone();
            bean.auditoriaGuardar(copiaAuditoriaMasiva);
            String idsCmFactura = Stream.of(copiaAuditoriaMasiva.getIdsFacturas().toArray()).map(String::valueOf).collect(Collectors.joining(","));
            if(validarCmDetallesSinEstadoAnulado(bean,idsCmFactura )){
                bean.setEjecucionExitosaOperacion(getAuditoriaMasivaGenericoRemoto().ejecutarRegistroCierre(copiaAuditoriaMasiva));
            }                    
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarAuditoriaMasivaDetalles(CmAuditoriaMasivaBean bean) {
        try {
            
            StringBuilder errorCopiado =  new StringBuilder();
            String acumuladorTotalConceptosStr = obtenerConceptosConcatenados(bean.getRegistrosConceptoContable());
            String acumuladorTotalDiagnosticoStr = obtenerDiagnosticosConcatenados(bean.getRegistrosAuditoriaDiagnostico());
            
            if(! copiarArchivosEnDisco(bean.getRegistrosAuditoriaAdjutoCmDetalle(), errorCopiado)){
                 bean.addError(errorCopiado.toString());  
            } 
         
            for (CmDetalle cmDetalle : bean.getRegistrosDetallesSeleccionadoMasivos()) {
                
                cmDetalle.setCantidadConceptosContablesAsociados(obtenerConceptosContablesActuales(cmDetalle));
 
                for (CmAuditoriaConceptoContable concepto : bean.getRegistrosConceptoContable()) {
                    concepto.castCentroCostoMaestro(concepto.getMaestroCentroCosto());
                    concepto.castConceptoContableMaestro(concepto.getMaestroConceptos());
                    if (concepto.getId() == null && cmDetalle.getCantidadConceptosContablesAsociados() == 0) {
                        concepto.setCmDetalle(new CmDetalle(cmDetalle.getId()));
                        bean.auditoriaGuardar(concepto);
                        getCmAuditoriaConceptoContableRemoto().insertar(concepto);
                        bean.getHashInsumoAuditoriaInsertados().put(1, concepto.toString());
                    }
                }

                for (CmAuditoriaDiagnostico diagnostico : bean.getRegistrosAuditoriaDiagnostico()) {
                    if (diagnostico.getId() == null && cmDetalle.getCantidadDiagnosticosAsociados() == 0) {
                        diagnostico.setCmDetalle(new CmDetalle(cmDetalle.getId()));
                        bean.auditoriaGuardar(diagnostico);
                        getCmAuditoriaDiagnosticoRemoto().insertar(diagnostico);
                        bean.getHashInsumoAuditoriaInsertados().put(2, diagnostico.toString());
                    }
                }

                List<CmAuditoriaMotivoGlosa> motivosAgregados = new ArrayList<>();
                Map<Integer, String> motivosEspecificosExistentePorDetalle = obtenerMotivosEspecificosExistentes(cmDetalle.getListaCmAuditoriaMotivosGlosa());
                for (CmAuditoriaMotivoGlosa motivo : bean.getRegistrosAuditoriaMotivoGlosa()) {
                    if (motivo.getId() == null && motivosEspecificosExistentePorDetalle.get(motivo.getMaestroMotivoEspecifico().getId()) == null) {
                        motivosAgregados.add(motivo);
                        motivo.setCmDetalle(new CmDetalle(cmDetalle.getId()));
                        BigDecimal porcentaje = motivo.getPorcentaje();
                        if (porcentaje.compareTo(new BigDecimal("0.0")) > 0
                                && cmDetalle.getValorFacturado().compareTo(new BigDecimal("0.0")) > 0) {
                            BigDecimal promedio = porcentaje.divide(new BigDecimal(100), 4, RoundingMode.CEILING);
                            BigDecimal valorMotivo = promedio.multiply(cmDetalle.getValorFacturado()).setScale(4, RoundingMode.HALF_UP);
                            motivo.setValorMotivo(valorMotivo);
                        }
                        bean.auditoriaGuardar(motivo);
                        getCmAuditoriaMotivosGlosaRemoto().insertar(motivo);
                        cmDetalle.getListaCmAuditoriaMotivosGlosa().add(motivo);
                        bean.getHashInsumoAuditoriaInsertados().put(3, motivo.toString());
                    }
                }
                
                for (CmAuditoriaAdjunto adjunto : bean.getRegistrosAuditoriaAdjutoCmDetalle()) {
                    if (adjunto.getId() == null) {
                        adjunto.setCmFactura(new CmFactura(cmDetalle.getCmFacturas().getId()));
                        adjunto.setCmDetalle(new CmDetalle(cmDetalle.getId()));
                        bean.auditoriaGuardar(adjunto);
                        if (adjunto.isGuardadoEnDisco()) {
                            getCmAuditoriaAdjuntoRemoto().insertar(adjunto);
                            adjunto.setGuardadoEnDB(true);
                            bean.getHashInsumoAuditoriaInsertados().put(4, adjunto.toString());
                        }
                    }
                }
                
                String acumuladorMotivosStr = obtenerMotivosConcatenados(motivosAgregados);
                String acumuladorObservacionStr = obtenerObservacionMotivosConcatenados(motivosAgregados);

                boolean hayConceptos = acumuladorTotalConceptosStr.length() > 0
                        || (cmDetalle.getAplicaConcepto() != null && cmDetalle.getAplicaConcepto());
                cmDetalle.setAplicaConcepto(hayConceptos);
                boolean hayDiagnosticos = acumuladorTotalDiagnosticoStr.length() > 0
                        || (cmDetalle.getAplicaDx() != null && cmDetalle.getAplicaDx());
                cmDetalle.setAplicaDx(hayDiagnosticos);
                boolean hayGlosa = acumuladorMotivosStr.length() > 0
                        || (cmDetalle.getAplicaGlosa() != null && cmDetalle.getAplicaGlosa());
                cmDetalle.setAplicaGlosa(hayGlosa);

                String acumuladorDetalleConceptosStr = cmDetalle.getCantidadConceptosContablesAsociados() > 0
                        ? cmDetalle.getConceptoContable() : acumuladorTotalConceptosStr;
                cmDetalle.setConceptoContable(acumuladorDetalleConceptosStr);
                String acumuladorDetalleDiagnosticoStr = cmDetalle.getCantidadDiagnosticosAsociados() > 0
                        ? cmDetalle.getNombreDx() : acumuladorTotalDiagnosticoStr;
                cmDetalle.setNombreDx(acumuladorDetalleDiagnosticoStr);
                String acumuladorDetalleMotivosStr = cmDetalle.getCantidadMotivosAsociadas() > 0
                        ? cmDetalle.getMotivoGlosa() + acumuladorMotivosStr : acumuladorMotivosStr;
                cmDetalle.setMotivoGlosa(acumuladorDetalleMotivosStr);
                String acumuladorDetalleObservacionStr = cmDetalle.getCantidadMotivosAsociadas() > 0
                        ? cmDetalle.getObservacion() + acumuladorObservacionStr : acumuladorObservacionStr;
                cmDetalle.setObservacion(acumuladorDetalleObservacionStr);
                cmDetalle.setCantidadConceptosContablesAsociados( hayConceptos ? 1 : 0 );
                cmDetalle.setCantidadDiagnosticosAsociados( hayDiagnosticos ? 1 : 0 );
                cmDetalle.setCantidadMotivosAsociadas( hayGlosa ? 1 : 0 );
                cmDetalle.setValorGlosa(calcularValorGlosaDetalle(bean, cmDetalle));
               
                getCmDetalleRemoto().actualizar(cmDetalle);   
                
                getCmDetalleRemoto().actualizarRecobro(String.valueOf(cmDetalle.getId()) ,bean.getAplicaRecobro());
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarAuditoriaMasivaAutorizaciones(CmAuditoriaMasivaBean bean) {
        try {
            for (CmDetalle detalle : bean.getRegistrosDetallesSeleccionadoMasivos()) {
                for (CmAuditoriaAutorizacion autorizacion : bean.getRegistrosAuditoriaAutorizacion()) {
                    if (autorizacion.getId() == null && detalle.getCantidadAutorizacionesAsociadas() == 0) {
                        if (detalle.getCmFacturas() != null) {
                            autorizacion.setCmFactura(new CmFactura(detalle.getCmFacturas().getId()));
                        }
                        autorizacion.setCmDetalle(new CmDetalle(detalle.getId()));
                        bean.auditoriaGuardar(autorizacion);
                        getCmAuditoriaAutorizacionRemoto().insertar(autorizacion);
                    }
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarDevolucionMasiva(CmAuditoriaMasivaBean bean) {
        try {
            CmAuditoriaMasivaModulo copiaAuditoriaMasiva = (CmAuditoriaMasivaModulo) bean.getObjeto().clone();
            bean.auditoriaGuardar(copiaAuditoriaMasiva);
            bean.setEjecucionExitosaOperacion(getAuditoriaMasivaGenericoRemoto().ejecutarRegistroCierre(copiaAuditoriaMasiva));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void guardarMarcadoGlosaIps(CmAuditoriaMasivaBean bean) {
        try {      
            getCmFacturaRemoto().actualizarMarcadoGlosaIPS( bean.getParamConsultaUtilitario());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarMarcadoCopagoNoEfectivo(CmAuditoriaMasivaBean bean) {
        try {
            String idsCmDetalles = "";
            boolean tipoMarcado = (boolean) bean.getParamConsultaUtilitario().getParametroConsulta1();
            idsCmDetalles = obtenerIdCmDetallesContatenados(bean.getRegistrosDetallesSeleccionadoMasivos());

            getCmDetalleRemoto().actualizarCopagoNoEfectivo(idsCmDetalles, tipoMarcado);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    public void obtenerNumeroContrato(CmAuditoriaMasivaBean bean) {
        try {
            String numeroContrato = "";

            if (bean.getObjetoFactura().getNumeroContrato() == null
                    || "".equals(bean.getObjetoFactura().getNumeroContrato())) {

                CmRipsCarga ripcarga = bean.getObjetoFactura().getCmRipCarga();
                if (ripcarga != null
                        && ripcarga.getCntContrato() != null
                        && ripcarga.getCntContrato().getId() != null
                        && ripcarga.getCntContrato().getId() > 0) {
                    CntContrato contrato;
                    try {
                        contrato = getCntContratoRemoto().consultarDatosBasicos(ripcarga.getCntContrato().getId());
                    } catch (Exception e) {
                        contrato = null;
                    }
                    numeroContrato = contrato != null ? contrato.getContrato() : "";
                }
                bean.getObjetoFactura().setNumeroContrato(numeroContrato);
            }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
  
    private void guardarEstadosAplicaEnCmDetalles(CmAuditoriaMasivaBean bean) {
        try {
            for (CmDetalle detalle : bean.getRegistrosDetallesSeleccionadoMasivos()) {
                    Integer idDetalle = detalle.getId();
                    detalle = getCmDetalleRemoto().consultar(idDetalle);

                    boolean hayGlosa = detalle.getCantidadMotivosAsociadas() > 0;;
                    detalle.setAplicaGlosa(hayGlosa);

                    String motivosAcumulados = hayGlosa ? obtenerMotivosConcatenados(
                            detalle.getListaCmAuditoriaMotivosGlosa()) : "";
                    detalle.setMotivoGlosa(motivosAcumulados);

                    String observacionesAcumuladas = hayGlosa ? obtenerObservacionMotivosConcatenados(
                            detalle.getListaCmAuditoriaMotivosGlosa()) : "";
                    detalle.setObservacion(observacionesAcumuladas);

                    detalle.setValorGlosa(calcularValorGlosaDetalle(bean, detalle));
                    getCmDetalleRemoto().actualizar(detalle);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private boolean crearArchivo(CmAuditoriaAdjunto adjunto) {
        boolean guardado = true;
        OutputStream destino = null;
        try {
            File archivo = new File(adjunto.getArchivoRuta(), adjunto.getArchivoNombre());
            destino = new FileOutputStream(archivo);
            IOUtils.copy(adjunto.getInputStream(), destino);
            IOUtils.closeQuietly(adjunto.getInputStream());
            IOUtils.closeQuietly(destino);
            return guardado;
        } catch (IOException e) {
            setPostFijoError(e.getMessage());
            guardado = false;
        } finally {
            IOUtils.closeQuietly(adjunto.getInputStream());
            IOUtils.closeQuietly(destino);
        }
        return guardado;
    }

    private void consultarUbicacionPrestadorSede(CmAuditoriaMasivaBean bean) {
        try {
            if (!bean.getRegistrosFacturasSeleccionadas().isEmpty()) {
                CmFactura factura = bean.getRegistrosFacturasSeleccionadas().get(0);
                if (factura.getCmRipCarga() != null && factura.getCmRipCarga().getId() > 0) {

                    CmRipsCarga ripCarga = getCmRipsCargaRemoto().consultar(factura.getCmRipCarga().getId());
                    int idSede = Optional.ofNullable(ripCarga.getGnPrestadorSede().getId()).orElse(0);
                    CntPrestadorSede prestador = getCntPrestadorSedeRemoto().consultar(idSede);
                    int idUbicacion = Optional.ofNullable(prestador.getUbicacionId()).orElse(0);
                    if (idUbicacion > 0) {
                        Ubicacion ubicacion =  UbicacionSingle.getInstance().getHashMunicipios().get(idUbicacion);
                        if (ubicacion != null && ubicacion.getPrefijo() != null) {
                            bean.getCmAuditoriaConceptoContable().setUbicacionMunicipio(ubicacion);
                        }
                    }
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void consultarProgramasActivosPorAfiliado(CmAuditoriaMasivaBean bean) {
        try {
            if (bean.getObjetoDetalleServicio().getId() != null) {
                AsegAfiliado afiliado = consultarAsegAfiliado(bean.getObjetoDetalleServicio().getMaeTipoDocumentoCodigo(),
                        bean.getObjetoDetalleServicio().getDocumento());
                if (afiliado.getId() != null && afiliado.getId() > 0) {
                    bean.setAfiliadoCompleto(afiliado);
                }
                
                asignarFiltroProgramaEspecialActivo(bean);

                bean.getParamConsultaAfiliadoProgramas().setCantidadRegistros(getPeAfiliadosProgramaRemoto().consultarCantidadLista(bean.getParamConsultaAfiliadoProgramas()));
                bean.setRegistrosAfiliadosPrograma(getPeAfiliadosProgramaRemoto().consultarLista(bean.getParamConsultaAfiliadoProgramas()));  
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void consultarProgramaEspecificoPorAfiliado(CmAuditoriaMasivaBean bean) {
        try {
            PePrograma pePrograma = Optional.ofNullable(bean.getPeAfiliadosPrograma().getPePrograma()).orElse(new PePrograma());
            if (pePrograma.getId() != null) {
                bean.getPeAfiliadosPrograma().setPePrograma(getPeProgramaRemoto().consultar(pePrograma.getId()));
                bean.getPeAfiliadosPrograma().setGnUsuario(getUsuarioRemoto().consultar(bean.getPeAfiliadosPrograma().getGnUsuario().getId()));
                bean.getPeAfiliadosPrograma().setCntPrestadorSede(getPrestadoresRemoto().consultarSede(bean.getPeAfiliadosPrograma().getCntPrestadorSede().getId()));
                asignarDiagnosticosPorAfiliado(bean);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private List<Maestro> consultarMaestrosPorAccionActivos(int accionMaestro) throws Exception {
        List<Maestro> maestros = getMaestroRemoto().consultarMaestrosPorAccion(accionMaestro);
        maestros = (maestros != null && !maestros.isEmpty())
                ? maestros.stream().filter(maestro -> maestro.isActivo()).collect(Collectors.toList()) : new ArrayList<>();
        return maestros;
    }
     
     private void asignarDiagnosticosPorAfiliado(CmAuditoriaMasivaBean bean) {
        try {
            if (bean.getPeAfiliadosPrograma() != null) {
                MaDiagnostico maDiagnostico = new MaDiagnostico();
                bean.getPeAfiliadosPrograma().setMaDiagnosticoPrincipal(maDiagnostico);
                String diagnosticos = "";
                for (PeAfiliadoDiagnostico peAfiliadoDiagnostico : bean.getPeAfiliadosPrograma().getPeAfiliadoDiagnosticoList()) {
                    diagnosticos += peAfiliadoDiagnostico.getMaDiagnosticosCodigo() + " - " + peAfiliadoDiagnostico.getMaDiagnosticosValor() + "; ";
                }
                maDiagnostico.setMaeDiagnosticoCategoriaValor(diagnosticos);
            }
        } catch (Exception e) {
        }
    } 
    
    private AsegAfiliado consultarAsegAfiliado(String tipo, String documento) {
        AsegAfiliado afiliado = new AsegAfiliado();
        try {
            List<AsegAfiliado> afiliados = getAfiliadoRemoto().consultarPorNumeroDocumento(documento);
            if (afiliados != null && !afiliados.isEmpty()) {
                for (int i = afiliados.size() - 1; i >= 0; i--) {
                    AsegAfiliado afiliadoIn = afiliados.get(i);
                    if (tipo.equals(afiliadoIn.getMaeTipoDocumentoCodigo())
                            && documento.equals(afiliadoIn.getNumeroDocumento())) {
                        afiliado = afiliadoIn;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            afiliado = new AsegAfiliado();
        }
        return afiliado;
    }
    
    private List<CmDetalle>consultarCmDetallesPorEstadoAutorizacion(String idCmFacturas, String estadosAutorizacion) {
         List<CmDetalle> detalles;
        try {
            detalles = getCmDetalleRemoto().consultarPorEstadoAutorizacion(idCmFacturas, estadosAutorizacion);
        } catch (Exception e) {
            detalles = new ArrayList<>();
        }
        return detalles;
    }
     
    private HashMap obtenerMotivosEspecificosExistentes(List<CmAuditoriaMotivoGlosa> listaMotivosGlosa) {
        HashMap<Object, Object> motivosEspecificosExistentes = new HashMap<>();
        if (!listaMotivosGlosa.isEmpty()) {
            for (CmAuditoriaMotivoGlosa motivo : listaMotivosGlosa) {
                if (motivosEspecificosExistentes.get(motivo.getMaestroMotivoEspecifico().getId()) == null) {
                    motivosEspecificosExistentes.put(motivo.getMaestroMotivoEspecifico().getId(), motivo.getMaestroMotivoEspecifico().getValor());
                }
            }
        }
        return motivosEspecificosExistentes;
    }
    
    private String obtenerConceptosConcatenados(List<CmAuditoriaConceptoContable> listaConceptos){
        String acumuladorConceptosStr = "";
        if(!listaConceptos.isEmpty()){
            for (CmAuditoriaConceptoContable conceptoContable : listaConceptos) {
                    acumuladorConceptosStr+= conceptoContable.getMaeConceptosValor() +" --";   
            }
        } 
       return acumuladorConceptosStr;
    }
    
    private  String obtenerDiagnosticosConcatenados(List<CmAuditoriaDiagnostico> listaDiagnostico){
        String acumuladorDiagnosticoStr = "";
        if(!listaDiagnostico.isEmpty()){
            for (CmAuditoriaDiagnostico diagnostico : listaDiagnostico) {
                    acumuladorDiagnosticoStr+= diagnostico.getMaDiagnosticoCodigo()+ "-" +
                                               diagnostico.getMaDiagnosticoValor() + " ;";   
            }
        }
       return acumuladorDiagnosticoStr;
    }
    
    private  String obtenerMotivosConcatenados(List<CmAuditoriaMotivoGlosa> listaMotivosGlosa){
        String acumuladorMotivosStr = "";
        if(!listaMotivosGlosa.isEmpty()){
            for (CmAuditoriaMotivoGlosa motivo : listaMotivosGlosa) {
               if(motivo.getMaeMotivoId()>0){
                    acumuladorMotivosStr+= "M(codigo:"+motivo.getMaeMotivoCodigo()+")-"+motivo.getMaeMotivoValor()+" ";
                }
                if(motivo.getMaeMotivoEspecificoId()>0){
                    acumuladorMotivosStr+= "ME(codigo:"+motivo.getMaeMotivoEspecificoCodigo()+")-"+motivo.getMaeMotivoEspecificoValor()+" --";
                }
                if(motivo.getMaeMotivoGlosaAplicacionId() != null && motivo.getMaeMotivoGlosaAplicacionId() >0 ){
                    acumuladorMotivosStr+= "MA(codigo:"+motivo.getMaeMotivoGlosaAplicacionCodigo()+")-"+motivo.getMaeMotivoGlosaAplicacionValor()+" --";
                }
            }
        }
       return acumuladorMotivosStr;
    }
    
    private  String obtenerObservacionMotivosConcatenados(List<CmAuditoriaMotivoGlosa> listaMotivosGlosa){
        String acumuladorObservacionMotivosStr = "";
        if(!listaMotivosGlosa.isEmpty()){
            for (CmAuditoriaMotivoGlosa motivo : listaMotivosGlosa) {
                acumuladorObservacionMotivosStr += motivo.getObservacion() + " -- ";
            }
        }
       return acumuladorObservacionMotivosStr;
    }
    
     private String obtenerIdsDetallesStr(CmAuditoriaMasivaBean bean) {
        String ids = "";
        try {
            List<String> listIdsDetalle = new ArrayList<>();
            bean.getRegistrosDetallesSeleccionadoMasivos().stream().forEach((p)-> {listIdsDetalle.add(String.valueOf(p.getId()));});
            ids = String.join(",",listIdsDetalle);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return ids;
    }
    
    private String obtenerIdsMotivosEspecificosStr(CmAuditoriaMasivaBean bean) {
        String codigosMotivosStr = "";
        try {
            if( ! bean.getMotivosEspecificosSeleccionados().isEmpty()){
                codigosMotivosStr = Stream.of(bean.getMotivosEspecificosSeleccionados().toArray()).map(String::valueOf).collect(Collectors.joining(","));
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return codigosMotivosStr;
    }
    
     private List<Maestro> obtenerListaFiltradaPorAccionSegunResolucion(CmAuditoriaMasivaBean bean, List<Maestro> lista) {
        int accion = bean.getObjeto().getVersion() ? MaestroAccion.CM_MOTIVO_GLOSA_ESPEFIFICO_RESOLUCION_2284
                : MaestroAccion.CM_MOTIVO_GLOSA_ESPEFIFICO_RESOLUCION_3047;
        lista = lista.stream().filter(predicate -> (evaluarExistenciaAccion(predicate.getMaestroAcciones(), accion))).collect(Collectors.toList());
        return lista;
    }
       
    public void consultarNumeroDetallesProceso(CmAuditoriaMasivaBean bean) {
        try {
            bean.setNumeroDetallesEnProceso(getCmDetalleRemoto().consultarCantidadListaDetallesMultiFactura(bean.getParamConsultaDetallesPorFactura()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    public BigDecimal calcularValorGlosaDetalle(CmAuditoriaMasivaBean bean, CmDetalle detalle) {
        BigDecimal valorGlosar = null;
        try {
            BigDecimal porcentajeCienPorCiento = new BigDecimal("100");   
            Integer idDetalle = detalle.getId();
            
            if (idDetalle != null) {
                boolean hayGlosa = detalle.getCantidadMotivosAsociadas() > 0;
                BigDecimal valorFacturadoPorDetalle = detalle.getValorFacturado();
                BigDecimal pocentajeMaximoReconocer = new BigDecimal("0");
                if(hayGlosa){
                     for (CmAuditoriaMotivoGlosa motivo : detalle.getListaCmAuditoriaMotivosGlosa()) {
                            pocentajeMaximoReconocer = pocentajeMaximoReconocer.add(motivo.getPorcentaje());
                     }
                     if (pocentajeMaximoReconocer.compareTo(porcentajeCienPorCiento) < 0) {
                         BigDecimal promedio = pocentajeMaximoReconocer.divide(new BigDecimal(100), 4, RoundingMode.CEILING);
                         valorGlosar = promedio.multiply(valorFacturadoPorDetalle).setScale(4, RoundingMode.HALF_UP);
                     }
                     
                     if (pocentajeMaximoReconocer.compareTo(porcentajeCienPorCiento) >= 0) {
                         valorGlosar =  valorFacturadoPorDetalle;
                     }
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }      
        return valorGlosar;
    }

    private void guardarHistorialBorrado(CmHistoricoFactura  historico, String contenido, int tipo ) {
        try {
            historico.setTipos(tipo);
            historico.setCmHistoricoFacturas(contenido);
            getCmHistoricoFacturaRemoto().insertar(historico);
        } catch (Exception e) {
            Logger.getLogger(CmAuditoriaMasivaServicioImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }
     
    private void  desbloquearFacturasPorUsuarioEnProcesamiento(CmAuditoriaMasivaBean bean) {
        try {
            List<CmFacturaEstado> listFacturaEstados = obtenerListaFacturasParaProcesar(bean);
            getCmFacturaEstadoRemoto().borrarFacturasEstado(listFacturaEstados, CmFacturaEstado.TIPO_ESTADO_EN_PROCESO_AUDITORIA );
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private boolean evaluarExistenciaAccion(List<MaestroAccion> acciones, int idAccion){
        return acciones.stream().filter(predicate->predicate.getId() == idAccion).count()>0;
    }
    
    private List<CmFacturaEstado> obtenerListaFacturasParaProcesar(CmAuditoriaMasivaBean bean) {
        List<CmFacturaEstado> listFacturaEstados = new ArrayList<>();

        for (Integer idFactura : bean.getObjeto().getIdsFacturas()) {
            CmFacturaEstado facturaEstado = new CmFacturaEstado();
            facturaEstado.setCmFactura(new CmFactura(idFactura));
            facturaEstado.setEstadoFactura(CmFacturaEstado.TIPO_ESTADO_EN_PROCESO_AUDITORIA);
            bean.auditoriaGuardar(facturaEstado);
            listFacturaEstados.add(facturaEstado);
        }
        return listFacturaEstados;
    }
  
    private List<CmFacturaEstado> filtrarFacturasPorRegistrar(List<CmFacturaEstado> listaFacturasParaProcesar, CmAuditoriaMasivaBean bean) {
        List<CmFacturaEstado> listaFacturasParaProcesarFinal = new ArrayList<>();
        HashMap<Integer, Integer> facturasRegistradas = new HashMap<>();
        try {
            List<CmFacturaEstado> facturasEncontradas = getCmFacturaEstadoRemoto().buscarFacturasUsadasPorUsuario(CmFacturaEstado.TIPO_ESTADO_EN_PROCESO_AUDITORIA,
                    bean.getConexion().getUsuario().getUsuarioNombre(), listaFacturasParaProcesar);

            for (CmFacturaEstado facturaEncontrada : facturasEncontradas) {
                facturasRegistradas.put(facturaEncontrada.getCmFactura().getId(), facturaEncontrada.getCmFactura().getId());
            }

            for (CmFacturaEstado cmFacturaParaProcesar : listaFacturasParaProcesar) {
                if (facturasRegistradas.get(cmFacturaParaProcesar.getCmFactura().getId()) == null) {
                    listaFacturasParaProcesarFinal.add(cmFacturaParaProcesar);
                }
            } 
        } catch (Exception e) {
            listaFacturasParaProcesarFinal = listaFacturasParaProcesar;
            bean.addError("Ha ocurrido un error en filtrarFacturasPorRegistrar : " + e.getMessage());
        }
        return listaFacturasParaProcesarFinal;
    }
    
    private void verAseguramientoAfiliadoID(CmAuditoriaMasivaBean bean) {
        try {
            bean.setParamConsultaUtilitario(new ParamConsulta());
            bean.getParamConsultaUtilitario().setParametroConsulta2(bean.getObjetoDetalleServicio().getDocumento());
            bean.getParamConsultaUtilitario().setRegistrosPagina(10);
            List<AsegAfiliado> listAfiliados = getConsultarAfiliadoRemoto().consultarLista(bean.getParamConsultaUtilitario());
            Integer idAfiliado = listAfiliados != null && listAfiliados.size() > 0 ? listAfiliados.get(0).getId() : 0;
            AsegRegistroNovedad aseguramientoNovedad = new AsegRegistroNovedad();
            aseguramientoNovedad.setIdAfiliado(idAfiliado);
            bean.setObjetoRegistroNovedad(aseguramientoNovedad);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void bloquearFacturasPorUsuarioEnProcesamiento(CmAuditoriaMasivaBean bean) {
        try {

            bean.setCmRespuestaGenerica( new CmRespuestaGenerica());
            List<CmFacturaEstado> listaFacturasParaProcesar = obtenerListaFacturasParaProcesar(bean);

            List<CmFacturaEstado> facturasBloquedasEncontradas = getCmFacturaEstadoRemoto().
                    buscarFacturasNoPermitidasPorUsuario(CmFacturaEstado.TIPO_ESTADO_EN_PROCESO_AUDITORIA,
                             bean.getConexion().getUsuario().getUsuarioNombre(), listaFacturasParaProcesar);
         
            if (facturasBloquedasEncontradas.isEmpty()) {
                listaFacturasParaProcesar = filtrarFacturasPorRegistrar(listaFacturasParaProcesar, bean);
                getCmFacturaEstadoRemoto().insertarEstadoMasivo(listaFacturasParaProcesar);
                bean.getCmRespuestaGenerica().setEstadoRespuesta(true);
            } else {
                bean.getCmRespuestaGenerica().setEstadoRespuesta(false);
                StringBuilder stringBuilder = new StringBuilder(10);
                stringBuilder.append("Las facturas estan siendo usadas por otros usuarios : ");
                for (CmFacturaEstado facturasBloqueda : facturasBloquedasEncontradas) {
                    stringBuilder.append("Número radicado : ");
                    stringBuilder.append(facturasBloqueda.getCmFactura().getNumeroRadicado());
                    stringBuilder.append(", es usada por el usuario : ");
                    stringBuilder.append(facturasBloqueda.getUsuarioCrea());
                    stringBuilder.append(" ,");
                    break;
                }
                bean.getCmRespuestaGenerica().setMensaje( stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1) );
            }           
        } catch (Exception e) {
            bean.addError("Ha ocurrido un error en bloquearFacturasPorAuditoria : "+e.getMessage());
        }
    }
    
    private void asignarFiltroBusquedaPrestadorFactura(CmAuditoriaMasivaBean bean) {
        final String FILTRO = "cntPrestadoresId.id";
        try {
           
            int idPrestador = 0;
            if(!bean.getHashFacturasSelecionadas().isEmpty()){
                  int idFactura = bean.getRegistrosDetallesSeleccionadoMasivos().get(0).getCmFacturas().getId();
                  CmFactura factura = bean.getHashFacturasSelecionadas().get(idFactura);
                  idPrestador = Optional.ofNullable(factura.getCntPrestador().getId()).orElse(0);
            }
                  
            if (idPrestador > 0) {
                Map<String, Object> filtros = new HashMap<>();
                if (bean.getParamConsultaAnexo4().getFiltros() == null) {
                    filtros.put(FILTRO, idPrestador);
                    bean.getParamConsultaAnexo4().setFiltros(filtros);
                } else {
                    if (bean.getParamConsultaAnexo4().getFiltros().get(FILTRO) == null) {
                        bean.getParamConsultaAnexo4().getFiltros().put(FILTRO, idPrestador);
                    }
                }
            }
        } catch (Exception e) {
        }
    }
    
    private void reversarEstadoFacturaParaSinAuditoria(CmAuditoriaMasivaBean bean) {
        try {
            getCmFacturaRemoto().cambiarEstadoFacturas(bean.getRegistrosFacturasSeleccionadas(), CmFactura.ESTADO_FACTURA_SIN_AUDITORIA);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarAuditoriaNovedades(CmAuditoriaMasivaBean bean) {
        try {            
            bean.getParamConsultaAuditoriaNovedad().setParametroConsulta1(bean.getObjetoRegistroNovedad().getIdAfiliado());
            if (bean.getObjetoDetalleServicio().getFechaPrestacion() != null) {
                bean.getParamConsultaAuditoriaNovedad().setParametroConsulta2(bean.getObjetoDetalleServicio().getFechaPrestacion());
            }
            bean.getParamConsultaAuditoriaNovedad().setCantidadRegistros(getCmAuditoriaFacturaRemoto().consultarCantidadListaNovedades(bean.getParamConsultaAuditoriaNovedad()));
            bean.setRegistrosAseguramientoNovedad(getCmAuditoriaFacturaRemoto().consultarListaNovedades(bean.getParamConsultaAuditoriaNovedad()));
        }  catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private int obtenerConceptosContablesActuales(CmDetalle detalle) throws Exception {
       int cantidadConceptos;
        try {
            cantidadConceptos = getCmAuditoriaConceptoContableRemoto().consultarCantidadPorIdDetalle(detalle.getId());
        } catch (Exception e) {
            cantidadConceptos = 0;
        }
        return cantidadConceptos;
    }
    
     private void asignarFiltroProgramaEspecialActivo(CmAuditoriaMasivaBean bean) {
        final String FILTRO = "activo";
        Map<String, Object> filtros = new HashMap<>();
        if (bean.getParamConsultaAfiliadoProgramas().getFiltros() == null) {
            bean.getParamConsultaAfiliadoProgramas().setFiltros(filtros);
        } else {
            filtros = bean.getParamConsultaAfiliadoProgramas().getFiltros();
        }
        filtros.put(FILTRO, "1");
    }
     
     private void asignarDiasRadicacionSegunEstado(List<CmFactura> facturas){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateFechaActualString = sdf.format(new Date());
            Date fechaActualFormateada = sdf.parse(dateFechaActualString);
            HashMap<String, Integer> diasCache = new HashMap<>();
            Integer diaCache;
                    
            if (!facturas.isEmpty()) {
                for (CmFactura factura : facturas) {
                    if (CmFactura.ESTADO_FACTURA_SIN_AUDITORIA == factura.getEstadoFactura()) {
                        String fechaRadicacionString = sdf.format(factura.getFechaRadicacion());
                        Date fechaRadicacionFormateada = sdf.parse(fechaRadicacionString);
                        diaCache = diasCache.get(fechaRadicacionString);
                         if( diaCache == null){
                            diaCache = getCalendarioRemoto().consultarHabilies(fechaRadicacionFormateada, fechaActualFormateada);
                            diaCache = diaCache < 0  ? 0 : diaCache ;
                            diasCache.put(fechaRadicacionString, diaCache);
                         }                
                        factura.setNumeroDiasPasados(Optional.ofNullable(diaCache).orElse(0));
                    }
                }
            }
        } catch (Exception e) {
        }
    }
     
    private void asignarAtributosParaTipoMedicamento( List<CmDetalle> cmDetalles) throws Exception {
       
        int INDICE_PRIMER_REGISTRO = 0;
        String INDICE_COBERTURA_CODIGO_CONDICIONADO = "3";
        String llaveMedicamento;
        MaMedicamento medicamentoCache;
        HashMap<String, MaMedicamento> medecamentosCache = new HashMap<>();
        ParamConsulta parametroConsulta = new ParamConsulta();
        parametroConsulta.setRegistrosPagina(1);

        List<CmDetalle> detallesTipoMedicamento = cmDetalles.stream().filter(item -> item.getTipoServicio() == CmDetalle.TIPO_SERVICIO_MEDICAMENTO).collect(Collectors.toList());

        for (CmDetalle cmDetalleMedicamento : detallesTipoMedicamento) {
            llaveMedicamento = cmDetalleMedicamento.getMaServicioCodigo().trim();
            medicamentoCache = medecamentosCache.get(llaveMedicamento);
            if (medicamentoCache == null) {
                parametroConsulta.setFiltros(new HashMap<>());
                parametroConsulta.getFiltros().put("cum", llaveMedicamento);
                List<MaMedicamento> listaMedicamentos = getMedicamentoRemoto().consultarListaBuscador(parametroConsulta);
                medicamentoCache = listaMedicamentos.isEmpty() ? new MaMedicamento() : listaMedicamentos.get(INDICE_PRIMER_REGISTRO);
                medecamentosCache.put(llaveMedicamento, medicamentoCache);
            }
            cmDetalleMedicamento.setCondicionado(medicamentoCache.getMaeCoberturaCodigo().equals(INDICE_COBERTURA_CODIGO_CONDICIONADO));
            cmDetalleMedicamento.setRegulado(Optional.ofNullable(medicamentoCache.getEsRegulado()).orElse(false));
        }
        
        medecamentosCache.clear();
    }
     
    private String obtenerIdCmDetallesContatenados(List<CmDetalle> detalles) {
        String idsCmDetalles;
        idsCmDetalles = detalles.stream().map(mapper -> mapper.getId() + "").collect(Collectors.joining(","));
        return idsCmDetalles;
    }
   
}
