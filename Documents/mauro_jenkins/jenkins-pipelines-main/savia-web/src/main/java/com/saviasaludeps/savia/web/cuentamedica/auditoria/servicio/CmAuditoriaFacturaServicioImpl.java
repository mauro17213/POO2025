
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.auditoria.servicio;



import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegRegistroNovedad;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacionObjecion;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAdjunto;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAutorizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCapitaDescuento;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCierre;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaConceptoContable;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDevolucion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDiagnostico;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMasivaModulo;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMotivoGlosa;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmFacturaEstado;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupoUsuario;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmHistoricoFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmRespuestaGenerica;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuesta;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmRadicado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.NotificacionSapCmRadicadoCuentasMedicas;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeSoporte;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCarga;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsSuceso;
import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmTransaccion;
import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmTransaccionDetalle;
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
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionObjecionRemoto;
import com.saviasaludeps.savia.negocio.auditoria.concurrente.AucHospitalizacionRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.AuditoriaMasivaGenericoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaAutorizacionRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaCierreRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaConceptoContableRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaDescuentoCapitaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaDevolucionRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaDiagnosticoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaFacturaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaMotivosGlosaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmFacturaEstadoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmGrupoUsuarioRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmHistoricoFacturaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmDetalleRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmRadicadoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionDetalleRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionEncabezadoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmSincronizacionRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmFeSoporteRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmRipsCargaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.wstransaccion.WsCmTransaccionDetalleRemoto;
import com.saviasaludeps.savia.negocio.especial.PeAfiliadosProgramaRemoto;
import com.saviasaludeps.savia.negocio.especial.PeProgramaRemoto;
import com.saviasaludeps.savia.negocio.maestro.MaMedicamentoRemoto;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.cuentamedica.auditoria.bean.CmAuditoriaFacturaBean;
import com.saviasaludeps.savia.web.cuentamedica.conciliacion.servicio.CmEnviosGlosasServicioImpl;
import com.saviasaludeps.savia.web.cuentamedica.auditoria.utilidades.CalculoCierreAuditoriaHilo;
import com.saviasaludeps.savia.web.cuentamedica.auditoria.utilidades.CmAuditoriaFacturaBeanUtil;
import com.saviasaludeps.savia.web.cuentamedica.auditoria.utilidades.EnvioNotificacionAnexo4EntregaHilo;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.DTO.SincronizacionNotificacionFactura;
import com.saviasaludeps.savia.web.rest.NotificacionFactura.servicio.NotificacionFacturaServicio;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.io.IOUtils;


/**
 *
 * @author jeperez
 */
public class CmAuditoriaFacturaServicioImpl extends AccionesBO implements CmAuditoriaFacturaServicioIface {

    public final static int CONSULTA_TODOS_LOS_ITEMS = 1;
    public final static int CODIGO_LIBERACION_USUARIO_GESTIONA = 0;
      
    public String postFijoError;

    public String getPostFijoError() {
        return postFijoError;
    }
    
    public void setPostFijoError(String postFijoError) {
        this.postFijoError = postFijoError;
    }
     
    private CmAuditoriaFacturaRemoto getCmAuditoriaFacturaRemoto() throws Exception {
        return (CmAuditoriaFacturaRemoto) RemotoEJB.getEJBRemoto(("CmAuditoriaFacturaServicio"), CmAuditoriaFacturaRemoto.class.getName());
    }
    
    private CmDetalleRemoto getCmDetalleRemoto() throws Exception {
        return (CmDetalleRemoto) RemotoEJB.getEJBRemoto("CmDetalleServicio", CmDetalleRemoto.class.getName());
    }
    
    private CmFacturaRemoto getCmFacturaRemoto() throws Exception {
        return (CmFacturaRemoto) RemotoEJB.getEJBRemoto("CmFacturaServicio", CmFacturaRemoto.class.getName());
    }
    
    
    private CmAuditoriaDiagnosticoRemoto getCmAuditoriaDiagnosticoRemoto() throws Exception {
        return (CmAuditoriaDiagnosticoRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaDiagnosticoServicio", CmAuditoriaDiagnosticoRemoto.class.getName());
    }
    
    private MaestroRemoto getMaestroRemoto() throws Exception{
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
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
    
    private CmAuditoriaAdjuntoRemoto getCmAuditoriaAdjuntoRemoto() throws Exception {
        return (CmAuditoriaAdjuntoRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaAdjuntoServicio", CmAuditoriaAdjuntoRemoto.class.getName());
    }
    
    private CmAuditoriaDescuentoCapitaRemoto getCmAuditoriaDescuentoCapitaRemoto() throws Exception {
        return (CmAuditoriaDescuentoCapitaRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaDescuentoCapitaServicio", CmAuditoriaDescuentoCapitaRemoto.class.getName());
    }
    
    private CntContratoRemoto getCntContratoRemoto() throws Exception {
        return (CntContratoRemoto) RemotoEJB.getEJBRemoto("CntContratoServicio", CntContratoRemoto.class.getName());
    }

    private CmGrupoUsuarioRemoto getCmGrupoUsuarioRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("CmGrupoUsuarioServicio", CmGrupoUsuarioRemoto.class.getName());
        return (CmGrupoUsuarioRemoto) object;
    }
    
    private CmAuditoriaDevolucionRemoto getCmAuditoriaDevolucionRemoto() throws Exception {
        return (CmAuditoriaDevolucionRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaDevolucionServicio", CmAuditoriaDevolucionRemoto.class.getName());
    }
    
    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
        return (AfiliadoRemoto) object;
    }
    
    private CmHistoricoFacturaRemoto getCmHistoricoFacturaRemoto() throws Exception {
        return (CmHistoricoFacturaRemoto) RemotoEJB.getEJBRemoto("CmHistoricoFacturaServicio", CmHistoricoFacturaRemoto.class.getName());
    }
    
    private CmFacturaEstadoRemoto getCmFacturaEstadoRemoto() throws Exception {
        return (CmFacturaEstadoRemoto) RemotoEJB.getEJBRemoto("CmFacturaEstadoServicio", CmFacturaEstadoRemoto.class.getName());
    }
    
    private CmAuditoriaCierreRemoto getCmAuditoriaCierreRemoto() throws Exception {
        return (CmAuditoriaCierreRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaCierreServicio", CmAuditoriaCierreRemoto.class.getName());
    }
    
    private CmSincronizacionRemoto getCmSincronizacionRemoto() throws Exception {
        return (CmSincronizacionRemoto) RemotoEJB.getEJBRemoto("CmSincronizacionServicio", CmSincronizacionRemoto.class.getName());
    }
    
    private CmRadicadoRemoto getCmRadicadoServicioRemoto() throws Exception {
        return (CmRadicadoRemoto) RemotoEJB.getEJBRemoto(("CmRadicadoServicio"), CmRadicadoRemoto.class.getName());
    }
    
    private ConsultarAfiliadoRemoto getConsultarAfiliadoRemoto() throws Exception {
        return (ConsultarAfiliadoRemoto)RemotoEJB.getEJBRemoto("ConsultarAfiliadoServicio", ConsultarAfiliadoRemoto.class.getName());
    }

    private NotificacionFacturaServicio getNotificacionFacturaServicio() {
        return new NotificacionFacturaServicio();
    }
    
    private CmSincronizacionDetalleRemoto getCmSincronizacionDetalleRemoto() throws Exception {
        return (CmSincronizacionDetalleRemoto) RemotoEJB.getEJBRemoto("CmSincronizacionDetalleServicio", CmSincronizacionDetalleRemoto.class.getName());
    }
    
    private CmSincronizacionEncabezadoRemoto getCmSincronizacionEncabezadoRemoto() throws Exception {
        return (CmSincronizacionEncabezadoRemoto) RemotoEJB.getEJBRemoto("CmSincronizacionEncabezadoServicio", CmSincronizacionEncabezadoRemoto.class.getName());
    }
    
    private CmRipsCargaRemoto getCmRipsCargaRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("CmRipsCargaServicio", CmRipsCargaRemoto.class.getName());
        return (CmRipsCargaRemoto) object;
    }
    
    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
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
    
    private AuditoriaMasivaGenericoRemoto getAuditoriaMasivaGenericoRemoto() throws Exception {
        return (AuditoriaMasivaGenericoRemoto) RemotoEJB.getEJBRemotoGenerico("AuditoriaMasivaGenericoServicio", AuditoriaMasivaGenericoRemoto.class.getName());
    }
    
     private WsCmTransaccionDetalleRemoto getWsCmTransaccionDetalleRemoto() throws Exception {
        return (WsCmTransaccionDetalleRemoto) RemotoEJB.getEJBRemoto("WsCmTransaccionDetalleServicio", WsCmTransaccionDetalleRemoto.class.getName());
    }
     
    private AucHospitalizacionRemoto getAucHospitalizacionRemoto() throws Exception {
        return (AucHospitalizacionRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionServicio", AucHospitalizacionRemoto.class.getName());
    }
    
    private AucHospitalizacionObjecionRemoto getAucHospitalizacionObjecionRemoto() throws Exception {
        return (AucHospitalizacionObjecionRemoto) RemotoEJB.getEJBRemoto("AucHospitalizacionObjecionServicio", AucHospitalizacionObjecionRemoto.class.getName());
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
    public void Accion(CmAuditoriaFacturaBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_VER:
                    switch (bean.getDoAccion()) {
                        case CmAuditoriaFacturaBean.ACCION_VER_AUDITORIA_FACTURA:
                            ver(bean);
                            obtenerEstadosAuditoria(bean);
                            obtenerNumeroContrato(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_LISTAR_AUDITORIA_DETALLES:
                            listarAuditoriaDetalles(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_CONSULTAR_PROGRAMAS_ACTIVOS_POR_AFILIADO:
                            consultarProgramasActivosPorAfiliado(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_CONSULTAR_PROGRAMAS_ESPECIFICO_AFILIADO:
                            consultarProgramaEspecificoPorAfiliado(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_CONSULTAR_UBICACION_USUARIO:
                            consultarUbicacionUsuario(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_LISTAR_GLOSA_FACTURA_DETALLES:
                            listarGlosaRespuestaDetalles(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_VER_AUDITORIA_DETALLE:
                            verAuditoriaDetalle(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_LISTAR_ADJUNTOS:
                            listarAuditoriaAdjuntos(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_VER_VISTA_GETION_ITEM:
                            ver(bean);
                            verAuditoriaDetalle(bean);
                            obtenerNumeroContrato(bean);
                            consultarSucesosRips(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_LISTAR_DIAGNOSTICOS:
                            listarAuditoriaDiagnosticos(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_LISTAR_CONCEPTOS:
                            listarAuditoriaConceptosContables(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_LISTAR_MOTIVOS_GLOSA:
                            listarAuditoriaMotivosGlosa(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_LISTAR_AUTORIZACIONES:
                            listarAuditoriaAutorizacion(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_LISTAR_DETALLES_POR_GESTIONAR:
                             listarAuditoriaDetallesAgestionar(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_VALIDAR_FACTURA_MONO_USUARIO:
                             bean.getObjeto().setMultiUsuario(!verificarFacturaMonoUsuario(bean));
                            break;
                        case CmAuditoriaFacturaBean.ACCION_LISTAR_TODOS_DETALLES_POR_FACTUA:
                             bean.setRegistrosDetallesSeleccionadoMasivos(listarAuditoriaDetallesSinPaginar(bean));
                            break;  
                        case CmAuditoriaFacturaBean.ACCION_VER_MOTIVO_ESPECIFICO_HIJO:
                             bean.setListaMaeMotivosEspecificos(listarMotivosEspecificosHijos(bean));
                            break;  
                        case CmAuditoriaFacturaBean.ACCION_VER_MOTIVO_APLICACION_HIJO:
                             bean.setListaMaeMotivosAplicacion(listarMotivosAplicacionHijos(bean));
                            break;      
                         case CmAuditoriaFacturaBean.ACCION_VER_MOTIVO_GLOSA:
                             verMotivoGlosa(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_LISTAR_CENTRO_COSTOS_ASOCIADOS:         
                            listarCentroCostosAsociados(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_VER_MOTIVOS_MULTI_DETALLES:         
                            verMotivoEspecificosMultiDetalle(bean, obtenerIdsDetallesStr(bean));
                            break;
                         case CmAuditoriaFacturaBean.ACCION_VER_OBJECIONES_USUARIO:         
                             verObjecionesUsuario(bean);
                            break;
                         case CmAuditoriaFacturaBean.ACCION_VER_CONTRATO_SEDES:         
                             verContratoConSedes(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_LISTAR_ADJUNTOS_CMDETALLES:         
                             listarAuditoriaAdjuntosCmDetalles(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_LISTAR_MOTIVOS_ESPECIFICOS_DEVOLUCION:
                            bean.setListaMaeMotivoDevolucionEspecifico(listarMotivosEspecificosDevolucion(bean));
                            break;
                        case CmAuditoriaFacturaBean.DO_ACCION_VER_SOPORTES:         
                             verSoportes(bean);
                            break;
                    }
                    break;
          
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case CmAuditoriaFacturaBean.ACCION_GUARDAR_DEVOLUCION_FACTURA:
                            if (validarNoExistenciaDevolucion(bean.getObjeto().getId())) {
                                guardarDevolucionFactuar(bean);
                                guardarFechaAuditoria(bean);
                                guardarFechaDevolucion(bean);
                            }else{
                                bean.addError("La devolución no es posible por que la factura ya ha sido devuelta id factura:"+bean.getObjeto().getId());
                            }
                            break;
                    }
                    break;

                case Url.ACCION_ADICIONAL_3:
                    switch (bean.getDoAccion()) {
                        case CmAuditoriaFacturaBean.ACCION_LISTAR_AUDITORIA_NOVEDADES:
                            listarAuditoriaNovedades(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_VER_ASEGURAMIENTO_AFILIADO_ID:
                            verAseguramientoAfiliadoID(bean);
                            break;
                    }
                    break; 
                    
                case Url.ACCION_ADICIONAL_4:
                    switch (bean.getDoAccion()) {      
                        case CmAuditoriaFacturaBean.ACCION_LISTAR_ANEXOS_4:
                            listarAnexo4(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_VERIFICAR_EXISTENCIA_ANEXO4:
                            validarUsoAnexo4EnAuditoria(bean);
                            break;
                         case CmAuditoriaFacturaBean.ACCION_GUARDAR_GESTIONAR_ITEM:
                            guardarGestionarItemServicio(bean);
                             if (!bean.isError()) {
                                 guardarEstadosAplicaEnCmDetalles(bean, bean.getObjetoItemServicio());
                             }
                            break;
                         case CmAuditoriaFacturaBean.ACCION_GUARDAR_GESTIONAR_MASIVO:
                            guardarGestionarMasivoServicio(bean);
                            guardarEstadosAplicaEnCmDetallesMasivo(bean);
                            break;
                         case CmAuditoriaFacturaBean.ACCION_GUARDAR_ADJUNTOS:
                            guardarAdjunto(bean);
                            break;
                         case CmAuditoriaFacturaBean.ACCION_LISTAR_CNT_CONTRATOS:
                            listarCntContratos(bean);
                            break;  
                         case CmAuditoriaFacturaBean.ACCION_GUARDAR_ESTADO_AUDITORIA:
                             procesaEstadoAuditoria(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_LISTAR_ANEXOS4_ITEMS:
                            listarAnexo4Items(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_VERIFICAR_EXISTENCIA_ANEXO4_ITEM:
                            verificarExistenciaAnexo4Item(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_BORRAR_CONCEPTOS_POR_FACTURA:
                            borrarConceptosPorFactura(bean);
                            guardarEstadosAplicaEnCmDetallesMasivo(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_BORRAR_MOTIVOS_POR_FACTURA:
                            borrarMotivosPorFactura(bean);
                            guardarEstadosAplicaEnCmDetallesMasivo(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_BORRAR_MOTIVOS_ESPECIFICOS:
                            borrarMotivosEspecificosPorFactura(bean, obtenerIdsMotivosEspecificosStr(bean));
                            guardarEstadosAplicaEnCmDetallesMasivo(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_BORRAR_DIAGNOSTICOS_POR_FACTURA:
                            borrarDiagnosticosPorFactura(bean);
                            guardarEstadosAplicaEnCmDetallesMasivo(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_BORRAR_AUTORIZACIONES_POR_FACTURA:
                            borrarAutorizacionesPorFactura(bean);
                            guardarEstadosAplicaEnCmDetallesMasivo(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_BORRAR_DESCUENTO_CAPITA:
                            borrarDescuentoCapitaPorFactura(bean);
                            guardarEstadoDescuentoCapitaCmDetallesMasivo(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_OBTENER_VALOR_TOTAL_DETALLES_POR_FACTURA:
                            obtenerSumatoriaTotalDetallesPorFactura(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_OBTENER_VALOR_TEMPORALES_M2_FACTURA:
                            obtenerValoresTemporalesMomentoDosFactura(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_GUARDAR_EDICION_MOTIVO:
                            guardarEdicionMotivoGlosa(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_BLOQUEAR_FACTURAS_AUDITORIA:
                            bloquearFacturasPorAuditoria(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_DESBLOQUEAR_FACTURAS_AUDITORIA:
                            desbloquearFacturaAuditada(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_GUARDAR_MARCADO_GLOSA_IPS:
                            guardarMarcadoGlosaIps(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_GUARDAR_MARCACION_COPAGO_NO_EFECTIVO:
                            guardarMarcadoCopagoNoEfectivo(bean);
                            break;
                    }
                    break;
               case Url.ACCION_ADICIONAL_5:
                   switch (bean.getDoAccion()) {
                       case CmAuditoriaFacturaBean.ACCION_LISTAR_AUDITORIA_DESCUENTO_CAPITA:
                           listarAuditoriaCapitaDescuento(bean);
                           break;
                       case CmAuditoriaFacturaBean.ACCION_GUARDAR_CAPITA_DESCUENTO:
                           guardarCapitaDescuento(bean);
                           guardarEstadoCapitaApliacado(bean);
                           break;
                       case CmAuditoriaFacturaBean.ACCION_GUARDAR_CAPITA_DESCUENTO_MASIVO:
                           guardarCapitaDescuentoMasivo(bean);
                           guardarEstadoCapitaApliacadoMasivo(bean);
                           break;
                    }
                   break;
                   
                 case Url.ACCION_ADICIONAL_9:
                    switch (bean.getDoAccion()) {
                        case CmAuditoriaFacturaBean.ACCION_LISTAR_SINCRONIZACIONES_FACTURA:
                            listarSincronizaciones(bean);
                            break;
                        case CmAuditoriaFacturaBean.ACCION_VER_WS_TRANSACCIONES_DETALLE:
                            verWsTransaccionesDetalle(bean);
                            break;    
                        case CmAuditoriaFacturaBean.ACCION_GUARDAR_REINTENTO_FACTURA:
                            guardarReintentarSincronizacion(bean);
                            break; 
                    }
                    break;
                case Url.ACCION_ADICIONAL_10:
                    switch (bean.getDoAccion()) {
                        case CmAuditoriaFacturaBean.ACCION_GUARDAR:
                            guardarReversionFactura(bean);
                            break;
                    }
                    break;
            }
        }
    }


    @Override
    public void cargaInicial(CmAuditoriaFacturaBean bean) {
        try {
            

            List<CmGrupoUsuario> grupos =  getCmGrupoUsuarioRemoto().
               consultarPorUsuarioLista(bean.getConexion().getUsuario().getId());
            bean.getCmAuditoriaUsuarioActual().setGruposDeAccesoGrupos(grupos);
            
            //en cm_detalles
            bean.setListaMaeTipoDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
            bean.setHashMaeTipoDocumento( convertToHash(bean.getListaMaeTipoDocumento()) );
            
            //------------------------
            //en cm_auditoria_conceptos_contables  
            bean.setListaMaeConceptosTotales( getMaestroRemoto().consultarPorTipo(MaestroTipo.CM_CONCEPTO_CONTABLE));
            bean.setListaMaeConceptosContributivos( getMaestroRemoto().consultarListaPorPadre(MaestroTipo.CM_CONCEPTO_CONTABLE, CmFactura.CODIGO_REGIMEN_CONTRIBUTIVO));
            bean.setListaMaeConceptosSubsidiados( getMaestroRemoto().consultarListaPorPadre(MaestroTipo.CM_CONCEPTO_CONTABLE, CmFactura.CODIGO_REGIMEN_SUBSIDIADO));           
            bean.setHashMaeConceptos( convertToHash(bean.getListaMaeConceptosTotales()) );
            bean.setListaMaeCentrosCostos(getMaestroRemoto().consultarPorTipo(MaestroTipo.CM_CENTRO_COSTO));
            bean.setHashMaeCentrosCostos( convertToHash(bean.getListaMaeCentrosCostos()) );
                      
            //en cm_auditoria_devolucion
            bean.setListaMaeTipoContrato(getMaestroRemoto().consultarPorTipo(MaestroTipo.CNT_MODALIDAD));//tambien en cmfactura
            bean.setHashMaeTipoContrato(convertToHash(bean.getListaMaeTipoContrato()));//tambien en cmfactura
            //bean.setListaMaeMotivoDevolucion(getMaestroRemoto().consultarPorTipo(MaestroTipo.CM_DEVOLUCION_MOTIVO_GENERAL));  
            //bean.setHashMaeMotivoDevolucion(convertToHash( bean.getListaMaeMotivoDevolucion()));
            List<Maestro> maestroResolucion3047 =  consultarMaestrosPorAccionActivos(MaestroAccion.CM_DEVOLUCION_MOTIVO_RESOLUCION_3047);
            bean.getHashMaeMotivoDevolucionPadre().put(MaestroAccion.CM_DEVOLUCION_MOTIVO_RESOLUCION_3047, maestroResolucion3047); 
            List<Maestro> maestroResolucion2284 = consultarMaestrosPorAccionActivos(MaestroAccion.CM_DEVOLUCION_MOTIVO_RESOLUCION_2284);
            bean.getHashMaeMotivoDevolucionPadre().put(MaestroAccion.CM_DEVOLUCION_MOTIVO_RESOLUCION_2284, maestroResolucion2284);
                        
            bean.setListaMaeRegimen(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_REGIMEN));
            bean.setHashMaeRegimen(convertToHash(bean.getListaMaeRegimen()));

            // en cm_auditoria_motivos_glosa
            bean.setListaMaeMotivos(getMaestroRemoto().consultarPorTipo(MaestroTipo.CM_MOTIVO_GLOSA));
            bean.setHashMaeMotivos(convertToHash(bean.getListaMaeMotivos()));
            List<Maestro> maestroGlosaResolucion3047 = consultarMaestrosPorAccionActivos(MaestroAccion.CM_MOTIVO_GLOSA_RESOLUCION_3047);
            bean.getHashMaeMotivoGlosaPadre().put(MaestroAccion.CM_MOTIVO_GLOSA_RESOLUCION_3047, maestroGlosaResolucion3047); 
            List<Maestro> maestroGlosaResolucion2284 =  consultarMaestrosPorAccionActivos(MaestroAccion.CM_MOTIVO_GLOSA_RESOLUCION_2284); 
            bean.getHashMaeMotivoGlosaPadre().put(MaestroAccion.CM_MOTIVO_GLOSA_RESOLUCION_2284, maestroGlosaResolucion2284); 
            
            bean.setUbicacionesRecursiva(UbicacionSingle.getInstance().getHashMunicipios());
            bean.setUbicaciones(UbicacionSingle.getInstance().getListaMunicipios());
            
            bean.getHistoriaAfiliadoHash().setHashTiposGenero(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_SEXO));
            bean.getHistoriaAfiliadoHash().setHashTiposDocumento(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));                    
            bean.getHistoriaAfiliadoHash().setHashOrigenAfiliado(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.ASEG_ORIGEN_AFILIADO));
            bean.getHistoriaAfiliadoHash().setHashEstadosAfiliacion(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));
            bean.getHistoriaAfiliadoHash().setHashGrupoPoblacional(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.ASEG_GRUPO_POBLACIONAL)); 
            bean.setHashTipoSoporte(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.RIPS_TIPO_SOPORTE));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private List<Maestro> consultarMaestrosPorAccionActivos(int accionMaestro) throws Exception {
        List<Maestro> maestros =  getMaestroRemoto().consultarMaestrosPorAccion(accionMaestro);
                                  maestros = (maestros !=null && !maestros.isEmpty()) ?
        maestros.stream().filter(maestro-> maestro.isActivo() ).collect(Collectors.toList()): new ArrayList<>();
        return maestros;
    }
    
    public void obtenerNumeroContrato(CmAuditoriaFacturaBean bean) {
       try {
           String numeroContrato = "";

           if (bean.getObjeto().getNumeroContrato() == null || 
                   "".equals(bean.getObjeto().getNumeroContrato())) {
               
               CmRipsCarga ripcarga = bean.getObjeto().getCmRipCarga();
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
               bean.getObjeto().setNumeroContrato(numeroContrato);
           }
           
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    public void obtenerEstadosAuditoria(CmAuditoriaFacturaBean bean) {
        
        if (bean.getObjeto().getCmGrupo() != null && bean.getObjeto().getCmGrupo().getId() != null) {
            
            int idGrupoFactura = bean.getObjeto().getCmGrupo().getId();
            Map< String, Integer> tiposEstadoAuditoria = new HashMap<>();
            //se da privilegios de super administrador del sistema.
            bean.getCmAuditoriaUsuarioActual().setSuperAdministrador(bean.isAccionAdicional8());
            
            bean.getCmAuditoriaUsuarioActual().setGrupoAccesoFactura( bean.getObjeto(),
                                                                      bean.getConexion().getUsuario().getId());     
            if (bean.getCmAuditoriaUsuarioActual().isUsuarioLider(idGrupoFactura)) {
                tiposEstadoAuditoria.put(CmFactura.getTipoAuditoriaStr(CmFactura.TIPO_AUDITORIA_SIN_AUDITORIA), 
                                         CmFactura.TIPO_AUDITORIA_SIN_AUDITORIA);
                tiposEstadoAuditoria.put(CmFactura.getTipoAuditoriaStr(CmFactura.TIPO_AUDITORIA_PERTINENCIA_TECNICA), 
                                         CmFactura.TIPO_AUDITORIA_PERTINENCIA_TECNICA);
                tiposEstadoAuditoria.put(CmFactura.getTipoAuditoriaStr(CmFactura.TIPO_AUDITORIA_PERTINENCIA_MEDICA), 
                                         CmFactura.TIPO_AUDITORIA_PERTINENCIA_MEDICA);
                
            }
            
            if (bean.getCmAuditoriaUsuarioActual().isUsuarioTecnico(idGrupoFactura)
                    || bean.getCmAuditoriaUsuarioActual().isUsuarioLider(idGrupoFactura)) {
                tiposEstadoAuditoria.put(CmFactura.getTipoAuditoriaStr(CmFactura.TIPO_AUDITORIA_DEVOLUCION_TECNICA), 
                                         CmFactura.TIPO_AUDITORIA_DEVOLUCION_TECNICA);
                tiposEstadoAuditoria.put(CmFactura.getTipoAuditoriaStr(CmFactura.TIPO_AUDITORIA_PERTINENCIA_MEDICA), 
                                         CmFactura.TIPO_AUDITORIA_PERTINENCIA_MEDICA);
            }
            
            if (bean.getCmAuditoriaUsuarioActual().isUsuarioMedico(idGrupoFactura)
                    || bean.getCmAuditoriaUsuarioActual().isUsuarioLider(idGrupoFactura)) {
                tiposEstadoAuditoria.put(CmFactura.getTipoAuditoriaStr(CmFactura.TIPO_AUDITORIA_DEVOLUCION_MEDICA), 
                                         CmFactura.TIPO_AUDITORIA_DEVOLUCION_MEDICA);
            }
            
            if(!tiposEstadoAuditoria.isEmpty()){
                tiposEstadoAuditoria.put(CmFactura.getTipoAuditoriaStr(CmFactura.TIPO_AUDITORIA_CIERRE_AUDITORIA), 
                        CmFactura.TIPO_AUDITORIA_CIERRE_AUDITORIA);
            }
     
            bean.setListaTiposEstadoAuditoria(tiposEstadoAuditoria);
        } else {
            bean.addError("La factura no esta asociada a un grupo  para ser auditada.");
        }
        
    }

    private void procesaEstadoAuditoria(CmAuditoriaFacturaBean bean) {
        boolean esProcesoPermitido = true;
        try {   
                       
            CmFactura facturaConEstadosActuales = getCmFacturaRemoto().consultar(bean.getObjeto().getId());
            
            if (CmFactura.ESTADO_FACTURA_SIN_AUDITORIA != facturaConEstadosActuales.getEstadoFactura() && CmFactura.ESTADO_FACTURA_EN_AUDITORIA != facturaConEstadosActuales.getEstadoFactura() ) {
                esProcesoPermitido = false;
                bean.addError("La factura se encuentra en proceso " + facturaConEstadosActuales.getEstadoFacturaStr() +
                              ", este proceso requiere que la factura no este auditada o en procesos diferentes.");
            }
            
            if(esTipoAuditoriaCierre(bean.getObjeto().getTipoAuditoria()) && !validarCmDetallesSinEstadoAnulado(bean, bean.getObjeto().getId())){
               esProcesoPermitido = false;
            }
            
            if ( esTipoAuditoriaCierre(bean.getObjeto().getTipoAuditoria())  && esModalidadContratoCapita_o_PGP(bean) && esProcesoPermitido) {
                guardarEstadoAuditoriaFactura(bean,bean.getObjeto().getId(), bean.getObjeto().getTipoAuditoria(), CmFactura.ESTADO_FACTURA_AUDITADA_EXITOSA);
                if (!bean.isError()) {
                    guardarUsuarioGestiona(bean, bean.getObjeto().getId(), CODIGO_LIBERACION_USUARIO_GESTIONA);
                    guardarHistorialEstadoFactura(bean, bean.getObjeto().getId(), CmFactura.ESTADO_FACTURA_AUDITADA_EXITOSA);
                    guardarRegistroCmAuditoriaCierre(bean);
                    esProcesoPermitido = false;
                    enviarAutorizacionesAnexo4EntregaPorFactura(facturaConEstadosActuales.getId() ,facturaConEstadosActuales.getFechaPrestacion());
                    guardarFechaAuditoria(bean);
                    bean.addMensaje("El proceso de auditoría para la factura de tipo capita se ha realizado con exito.");
                }
            }
                 
            if (esTipoAuditoriaCierre(bean.getObjeto().getTipoAuditoria()) && esProcesoPermitido) {
                List<CmDetalle> lista = listarCantidadInsumosDetalleAuditar(bean);
                if (lista.isEmpty()) {
                    bean.addError("La factura debe tener detalles o servicios para cerrar la auditoría.");
                    esProcesoPermitido = false;
                } else {
                    for (CmDetalle cmDetalle : lista) {
                        if (cmDetalle.getCantidadConceptosContablesAsociados() == 0
                                || cmDetalle.getCantidadDiagnosticosAsociados() == 0) {
                            esProcesoPermitido = false;
                            bean.addError("Error en servicio id " + cmDetalle.getId() + ", Para realizar la operación todos los servicios deben "
                                    + "tener conceptos contables, diagnósticos.");
                            break;
                        }
                    }
                }
                if (esProcesoPermitido) {
                    if (CmFactura.ESTADO_FACTURA_SIN_AUDITORIA == facturaConEstadosActuales.getEstadoFactura()) {
                        bean.getObjeto().setEstadoFactura(CmFactura.ESTADO_FACTURA_EN_AUDITORIA);
                        guardarEstadoAuditoriaFactura(bean, bean.getObjeto().getId(), bean.getObjeto().getTipoAuditoria(), CmFactura.ESTADO_FACTURA_EN_AUDITORIA);
                        CmFactura factura = (CmFactura) bean.getObjeto().clone();
                        CalculoCierreAuditoriaHilo auditoriaHilo = new CalculoCierreAuditoriaHilo(factura, bean.getConexion());
                        auditoriaHilo.start();
                        enviarAutorizacionesAnexo4EntregaPorFactura(facturaConEstadosActuales.getId(), facturaConEstadosActuales.getFechaPrestacion());
                        guardarFechaAuditoria(bean);
                        bean.addMensaje("El proceso de auditoría se esta realizando.");
                    }
                }
            }else{          
                if (esProcesoPermitido) {
                    guardarEstadoAuditoriaFactura(bean, bean.getObjeto().getId(),bean.getObjeto().getTipoAuditoria(), null);
                    if (!bean.isError()) {
                        guardarUsuarioGestiona(bean, bean.getObjeto().getId(), bean.getObjeto().getUsuarioLider().getId());
                        bean.addMensaje("Se ha actualizado el estado de auditoría de la factura de número facturado :"
                                + bean.getObjeto().getNumeroFacturado() + " a estado de auditoría : "
                                + CmFactura.getTipoAuditoriaStr(bean.getObjeto().getTipoAuditoria()));
                    }
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private boolean esTipoAuditoriaCierre(int tipoAuditoria) {
        return CmFactura.TIPO_AUDITORIA_CIERRE_AUDITORIA == tipoAuditoria;
    }

    private boolean enviarAutorizacionesAnexo4EntregaPorFactura(int idFactura, Date fechaPrestacion) {

        boolean esNotificado = false;
        try {
            EnvioNotificacionAnexo4EntregaHilo hilo = new EnvioNotificacionAnexo4EntregaHilo(idFactura, fechaPrestacion);
            hilo.start();
        } catch (Exception e) {
            System.out.println("Error al llamar enviarAutorizacionesAnexo4EntregaPorFactura():" + e.toString());
        }
        return esNotificado;
    }
    
    private boolean evaluarExistenciaAccion(List<MaestroAccion> acciones, int idAccion){
        return acciones.stream().filter(predicate->predicate.getId() == idAccion).count()>0;
    }
    
    private void listar(CmAuditoriaFacturaBean bean) {
        try {
            
            boolean esConsultaGlobal = false;
            for (String key : bean.getParamConsulta().getFiltros().keySet()) {
                switch (key) {
                    case CmAuditoriaFacturaBeanUtil.CAMPO_FILTRO_RIP_ID:
                    case CmAuditoriaFacturaBeanUtil.CAMPO_FILTRO_NIT :
                    case CmAuditoriaFacturaBeanUtil.CAMPO_FILTRO_NUM_FACTURADO:
                    case CmAuditoriaFacturaBeanUtil.CAMPO_FILTRO_IPS:
                        esConsultaGlobal = true;
                     break;
                }
                if(esConsultaGlobal){
                    break;
                }
            }
            
            //busca en todo el sistema
            bean.getParamConsulta().setParametroConsulta2(null);
            if(esConsultaGlobal){
                 bean.getParamConsulta().setParametroConsulta2(esConsultaGlobal);
            }
            
            //busca en facturas donde sea el lider
            bean.getParamConsulta().setParametroConsulta3(null);
            if ( ( bean.getParamConsulta().getFiltros() != null && bean.getParamConsulta().getFiltros().isEmpty() ) ||
                 ( bean.getParamConsulta().getFiltros() != null && !esConsultaGlobal) 
                    ) {
                 bean.getParamConsulta().setParametroConsulta3(bean.getConexion().getUsuario().getId());
            }
            
            //Super usuario busca todo el sistema
            bean.getParamConsulta().setParametroConsulta4(null);
            if (bean.isAccionAdicional8()) {
                bean.getParamConsulta().setParametroConsulta4(bean.getConexion().getUsuario().getId());
            }

            bean.getParamConsulta().setCantidadRegistros(getCmAuditoriaFacturaRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getCmAuditoriaFacturaRemoto().consultarLista(bean.getParamConsulta()));
            
            asignarDiasRadicacionSegunEstado(bean.getRegistros());
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void ver(CmAuditoriaFacturaBean bean) {
        try {
            bean.setObjeto(getCmFacturaRemoto().consultar(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void verSoportes(CmAuditoriaFacturaBean bean) {
        try {
           bean.getParamConsultaSoportes().setCantidadRegistros(getCmFeSoporteRemoto().consultarCantidadLista(bean.getParamConsultaSoportes()));
           bean.setRegistrosSoportes((ArrayList<CmFeSoporte>) getCmFeSoporteRemoto().consultarLista(bean.getParamConsultaSoportes()));
        } catch (Exception ex) {
            bean.addError("Error versoportes: " + ex.getMessage());
        }
    }

 
    private boolean esModalidadContratoCapita_o_PGP(CmAuditoriaFacturaBean bean) {
        return bean.getObjeto().getMaeTipoContratoCodigo() != null
                && ( CmFactura.CODIGO_MODALIDAD_CONTRATO_CAPITA.equals(bean.getObjeto().getMaeTipoContratoCodigo())
                  || CmFactura.CODIGO_MODALIDAD_CONTRATO_PGP.equals(bean.getObjeto().getMaeTipoContratoCodigo()) );
    }
    
    private void verAseguramientoAfiliadoID(CmAuditoriaFacturaBean bean) {
        try {
            bean.setParamConsultaUtilitario(new ParamConsulta());
            bean.getParamConsultaUtilitario().setParametroConsulta2(bean.getObjetoItemServicio().getDocumento());
            bean.getParamConsultaUtilitario().setRegistrosPagina(10);
            List<AsegAfiliado> listAfiliados = getConsultarAfiliadoRemoto().consultarLista(bean.getParamConsultaUtilitario());
            Integer idAfiliado = listAfiliados != null && !listAfiliados.isEmpty() ? listAfiliados.get(0).getId() : 0;
            AsegRegistroNovedad aseguramientoNovedad = new AsegRegistroNovedad();
            aseguramientoNovedad.setIdAfiliado(idAfiliado);
            bean.setObjetoRegistroNovedad(aseguramientoNovedad);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void verAuditoriaDetalle(CmAuditoriaFacturaBean bean) {
        try {
            bean.setObjetoItemServicio(getCmDetalleRemoto().consultar(bean.getObjetoItemServicio().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void verMotivoGlosa(CmAuditoriaFacturaBean bean) {
        try {
            bean.setCmAuditoriaMotivoGlosa(getCmAuditoriaMotivosGlosaRemoto().consultar(bean.getCmAuditoriaMotivoGlosa().getId()));
            bean.setListaMaeMotivosEspecificos(listarMotivosEspecificosHijos(bean));
            bean.setListaMaeMotivosAplicacion(listarMotivosAplicacionHijos(bean));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void verMotivoEspecificosMultiDetalle(CmAuditoriaFacturaBean bean, String idsDetalles) {
        try {
            if (!"".equals(idsDetalles)) {
                bean.setListaMotivosEspecificosEncontrados(getCmAuditoriaMotivosGlosaRemoto().consultarPorMultiDetalles(idsDetalles));
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    
     private void listarAuditoriaDetalles(CmAuditoriaFacturaBean bean) {
        try {
            bean.getParamConsultaServiciosAuditoria().setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsultaServiciosAuditoria().setCantidadRegistros(getCmDetalleRemoto().consultarCantidadListaDetallesPorFactura(bean.getParamConsultaServiciosAuditoria()));
            bean.setRegistrosAuditoriaDetalles(getCmDetalleRemoto().consultarListaDetallesPorFactura(bean.getParamConsultaServiciosAuditoria()));
            
            asignarAtributosParaTipoMedicamento( bean.getRegistrosAuditoriaDetalles()) ;
            CmAuditoriaFacturaBeanUtil.asignarCodigoMipres(bean.getRegistrosAuditoriaDetalles());
          
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
  
    private boolean verificarFacturaMonoUsuario(CmAuditoriaFacturaBean bean) {
        boolean verificarFacturaMonoUsuario = false;
        try {
            bean.setParamConsultaUtilitario(new ParamConsulta());
            bean.getParamConsultaUtilitario().setParametroConsulta1(bean.getObjeto().getId());
            verificarFacturaMonoUsuario = getCmFacturaRemoto().verificarFacturaMonousurio(bean.getParamConsultaUtilitario());
            bean.setParamConsultaUtilitario(new ParamConsulta());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return verificarFacturaMonoUsuario;
    }
    
    private void verObjecionesUsuario(CmAuditoriaFacturaBean bean) {
        try {            
            List<AucHospitalizacionObjecion> objeciones = new ArrayList<>();
            asignarFiltroObjeciones(bean);
            List<AucHospitalizacion> autorizaciones = getAucHospitalizacionRemoto().consultarLista(bean.getParamConsultaUtilitario());
            for (AucHospitalizacion autorizacion : autorizaciones) {   
                List<AucHospitalizacionObjecion> objecionesHospitaliacion = getAucHospitalizacionObjecionRemoto().consultarPorIdHospitalizacion(autorizacion.getId());
                objecionesHospitaliacion = objecionesHospitaliacion.stream().peek(objecion -> {
                    objecion.getAucHospitalizacionId().setFechaInicioHospitalizacion(autorizacion.getFechaInicioHospitalizacion());
                    objecion.getAucHospitalizacionId().setFechaFinHospitalizacion(autorizacion.getFechaFinHospitalizacion());
                }).collect(Collectors.toList());
                objeciones.addAll(objecionesHospitaliacion);
            }
            bean.setRegistroHospitalizacionObjeciones(objeciones);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    
    private void verContratoConSedes(CmAuditoriaFacturaBean bean) {
        try {
            bean.setContratoConSedes(getContratoRemoto().consultarConSedes(bean.getContratoConSedes().getId()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void asignarFiltroObjeciones(CmAuditoriaFacturaBean bean) {
        bean.setParamConsultaUtilitario(new ParamConsulta());
        bean.getParamConsultaUtilitario().setRegistrosPagina(1000);
        HashMap<String, Object> filtros = new HashMap<>();
        filtros.put("aucAfiliadoId.maeTipoDocumentoId", bean.getObjetoItemServicio().getMaeTipoDocumentoId());
        filtros.put("aucAfiliadoId.numeroDocumento", bean.getObjetoItemServicio().getDocumento());
        filtros.put("cntPrestadorId.razonSocial", bean.getObjeto().getIps());
        bean.getParamConsultaUtilitario().setFiltros(filtros);
    }

    private void listarAuditoriaDetallesAgestionar(CmAuditoriaFacturaBean bean) {
        try {
            bean.getParamConsultaDetallesAgestionar().setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsultaDetallesAgestionar().setCantidadRegistros(getCmDetalleRemoto().consultarCantidadListaDetallesPorFactura(bean.getParamConsultaDetallesAgestionar()));
            bean.setRegistrosDetallesAgestionar(getCmDetalleRemoto().consultarListaDetallesPorFactura(bean.getParamConsultaDetallesAgestionar()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
    private List<CmDetalle> listarAuditoriaDetallesSinPaginar(CmAuditoriaFacturaBean bean) {
        List<CmDetalle> lista = new ArrayList<>();
        try {
            int CONSULTAR_SIN_PAGINAR = 1;
            bean.setParamConsultaUtilitario(new ParamConsulta());
            bean.getParamConsultaUtilitario().setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsultaUtilitario().setParametroConsulta2(CONSULTAR_SIN_PAGINAR);
            bean.getParamConsultaUtilitario().setCantidadRegistros(getCmDetalleRemoto().consultarCantidadListaDetallesPorFactura(bean.getParamConsultaUtilitario()));
            lista = (getCmDetalleRemoto().consultarListaDetallesPorFactura(bean.getParamConsultaUtilitario()));
            bean.setParamConsultaUtilitario(new ParamConsulta());  
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return lista;
    }
    
    private List<CmDetalle> listarCantidadInsumosDetalleAuditar(CmAuditoriaFacturaBean bean) {
        List<CmDetalle> lista = new ArrayList<>();
        try {
            int CONSULTAR_SIN_PAGINAR = 1;
            int CONSULTAR_VALORES_AUDITORIA = 1;
            bean.setParamConsultaUtilitario(new ParamConsulta());
            bean.getParamConsultaUtilitario().setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsultaUtilitario().setParametroConsulta2(CONSULTAR_SIN_PAGINAR);
            bean.getParamConsultaUtilitario().setParametroConsulta5(CONSULTAR_VALORES_AUDITORIA);
            lista = (getCmDetalleRemoto().consultarDetallesPorFacturaSoloValores(bean.getParamConsultaUtilitario()));
            bean.setParamConsultaUtilitario(new ParamConsulta());  
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return lista;
    }
    
    private List<Maestro> listarMotivosEspecificosHijos(CmAuditoriaFacturaBean bean) {
        List<Maestro> lista = new ArrayList<>();
        try {
            lista =  getMaestroRemoto().consultarListaPorPadre(MaestroTipo.CM_MOTIVO_GLOSA_ESPEFIFICO, bean.getCmAuditoriaMotivoGlosa().getMaestroMotivo().getId());       
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        
        lista = obtenerListaFiltradaPorAccionSegunResolucion(bean, lista);    
        bean.setHashMaeMotivosEspecificos( Util.convertToHash(lista));  
        return lista;
    }
    
     private List<Maestro> listarMotivosAplicacionHijos(CmAuditoriaFacturaBean bean) {
        List<Maestro> lista = new ArrayList<>();
        try {
            lista =  getMaestroRemoto().consultarListaPorPadre(MaestroTipo.CM_MOTIVO_GLOSA_APLICACION, bean.getCmAuditoriaMotivoGlosa().getMaestroMotivoEspecifico().getId());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        bean.setHashMaeMotivosAplicacion(Util.convertToHash(lista)); 
        return lista;
    }
       
    private List<Maestro> listarMotivosEspecificosDevolucion(CmAuditoriaFacturaBean bean) {
        List<Maestro> lista = new ArrayList<>();
        try {
            lista =  getMaestroRemoto().consultarListaPorPadre(MaestroTipo.CM_DEVOLUCION_MOTIVO, bean.getCmAuditoriaDevolucion().getMaestroMotivoDevolucion().getId());
            bean.setHashMaeMotivosEspecificos(Util.convertToHash(lista)); 
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
        return lista;
    }
    
    private void listarAuditoriaDiagnosticos(CmAuditoriaFacturaBean bean) {
        try {
            bean.getParamConsultaAuditoriaDiagnosticos().setParametroConsulta1(bean.getObjetoItemServicio().getId());
            bean.getParamConsultaAuditoriaDiagnosticos().setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            bean.getParamConsultaAuditoriaDiagnosticos().setCantidadRegistros(getCmAuditoriaDiagnosticoRemoto().consultarCantidadPorDetalle(bean.getParamConsultaAuditoriaDiagnosticos()));
            bean.setRegistrosAuditoriaDiagnostico(getCmAuditoriaDiagnosticoRemoto().consultarListaPorDetalle(bean.getParamConsultaAuditoriaDiagnosticos()));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarCentroCostosAsociados(CmAuditoriaFacturaBean bean) {
        List<Maestro> lista;
        HashMap<Integer, Maestro> hashCentroCostosAsociados = new HashMap();
        try {
            lista =  getMaestroRemoto().consultarListaPorPadre(MaestroTipo.CM_CENTRO_COSTO, bean.getCmAuditoriaConceptoContable().getMaestroConceptos().getId());          
            for (Maestro maestroHijo : lista) {
                hashCentroCostosAsociados.put(maestroHijo.getId(), maestroHijo);
            }
            bean.setHashMaeCentrosCostos(hashCentroCostosAsociados);         
            bean.setListaMaeCentrosCostos(lista); 
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void consultarUbicacionUsuario(CmAuditoriaFacturaBean bean) {
        try {
            String documento = bean.isEsGestionIndividual() ? bean.getObjetoItemServicio().getDocumento():
                              !bean.getRegistrosDetallesSeleccionadoMasivos().isEmpty() ?
                               bean.getRegistrosDetallesSeleccionadoMasivos().get(0).getDocumento() : "";
            
            List<AsegAfiliado> afiliados =  getAfiliadoRemoto().consultarPorNumeroDocumento(documento);    
            if(afiliados != null && !afiliados.isEmpty() ){
               AsegAfiliado afiliado = afiliados.get(0);
               
               Ubicacion ubicacion =  afiliado.getResidenciaUbicacion();
      
               if(ubicacion != null &&  ubicacion.getPrefijo()!=null){
                  bean.getCmAuditoriaConceptoContable().setUbicacionMunicipio(ubicacion);
               }else{
                  bean.addError("El usuario no tienen registrado ubicación de residencia");
               }   
            }
            
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
      private void consultarUbicacionPrestadorSede(CmAuditoriaFacturaBean bean) {
          try {
              if (bean.getObjeto().getCmRipCarga() != null && bean.getObjeto().getCmRipCarga().getId() > 0) {

                  CmRipsCarga ripCarga = getCmRipsCargaRemoto().consultar(bean.getObjeto().getCmRipCarga().getId());
                  int idSede = Optional.ofNullable(ripCarga.getGnPrestadorSede().getId()).orElse(0);
                  CntPrestadorSede prestador = getCntPrestadorSedeRemoto().consultar(idSede);
                  int idUbicacion = Optional.ofNullable(prestador.getUbicacionId()).orElse(0);
                  if (idUbicacion > 0) {
                      Ubicacion ubicacion = UbicacionSingle.getInstance().getHashMunicipios().get(idUbicacion);
                      if (ubicacion != null && ubicacion.getPrefijo() != null) {
                          bean.getCmAuditoriaConceptoContable().setUbicacionMunicipio(ubicacion);
                      }
                  }
              }

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
      
    private List<CmDetalle>consultarCmDetallesPorEstadoAutorizacion(int idCmFactura, String estadosAutorizacion) {
         List<CmDetalle> detalles;
        try {
            detalles = getCmDetalleRemoto().consultarPorEstadoAutorizacion(String.valueOf(idCmFactura), estadosAutorizacion);
        } catch (Exception e) {
            detalles = new ArrayList<>();
        }
        return detalles;
    }
    
    private boolean validarCmDetallesSinEstadoAnulado(CmAuditoriaFacturaBean bean, int idCmFactura) {
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

    private boolean validarNoExistenciaDevolucion(int idCmFactura) {
        boolean isValido;
        try {
            List<CmAuditoriaDevolucion> devoluciones = getCmAuditoriaDevolucionRemoto().consultarPorFacturaId(String.valueOf(idCmFactura));
            isValido = devoluciones.isEmpty();
        } catch (Exception e) {
           isValido = true;
        }
        return isValido;
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

     private void listarGlosaRespuestaDetalles(CmAuditoriaFacturaBean bean) {
        try { 
            bean.getParamConsultaGlosaFacturaDetalles().setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsultaGlosaFacturaDetalles().setCantidadRegistros(getCmDetalleRemoto().consultarCantidadListaDetallesPorFactura(bean.getParamConsultaGlosaFacturaDetalles()));
            bean.setRegistrosGlosaFacturaDetalle(getCmDetalleRemoto().consultarListaDetallesPorFactura(bean.getParamConsultaGlosaFacturaDetalles()));
        }  catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
    private void listarAuditoriaNovedades(CmAuditoriaFacturaBean bean) {
        try {            
            Date fechaBusqueda = new Date();
            bean.getHistoriaAfiliadoHash().setFechaConsulta(fechaBusqueda);
            bean.getParamConsultaAuditoriaNovedad().setParametroConsulta1(bean.getObjetoRegistroNovedad().getIdAfiliado());
            if (bean.getObjetoItemServicio().getFechaPrestacion() != null) {
                bean.getParamConsultaAuditoriaNovedad().setParametroConsulta2(bean.getObjetoItemServicio().getFechaPrestacion());
            }
            bean.getParamConsultaAuditoriaNovedad().setCantidadRegistros(getCmAuditoriaFacturaRemoto().consultarCantidadListaNovedades(bean.getParamConsultaAuditoriaNovedad()));
            bean.setRegistrosAseguramientoNovedad(getCmAuditoriaFacturaRemoto().consultarListaNovedades(bean.getParamConsultaAuditoriaNovedad()));
        }  catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarAuditoriaConceptosContables(CmAuditoriaFacturaBean bean) {
        try { 
            bean.getParamConsultaAuditoriaConceptoContable().setParametroConsulta1(bean.getObjetoItemServicio().getId());
            bean.getParamConsultaAuditoriaConceptoContable().setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            bean.getParamConsultaAuditoriaConceptoContable().setCantidadRegistros(getCmAuditoriaConceptoContableRemoto().consultarCantidadPorDetalle(bean.getParamConsultaAuditoriaConceptoContable()));
            bean.setRegistrosConceptoContable(getCmAuditoriaConceptoContableRemoto().consultarListaPorDetalle(bean.getParamConsultaAuditoriaConceptoContable()));
        }  catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
     private void listarAuditoriaMotivosGlosa(CmAuditoriaFacturaBean bean) {
        try { 
            bean.getParamConsultaAuditoriaMotivoGlosa().setParametroConsulta1(bean.getObjetoItemServicio().getId());
            bean.getParamConsultaAuditoriaMotivoGlosa().setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            bean.getParamConsultaAuditoriaMotivoGlosa().setCantidadRegistros(getCmAuditoriaMotivosGlosaRemoto().consultarCantidadPorDetalle(bean.getParamConsultaAuditoriaMotivoGlosa()));
            bean.setRegistrosAuditoriaMotivoGlosa(getCmAuditoriaMotivosGlosaRemoto().consultarListaPorDetalle(bean.getParamConsultaAuditoriaMotivoGlosa()));
        }  catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
    private void listarAuditoriaAutorizacion(CmAuditoriaFacturaBean bean) {
        try { 
            bean.getParamConsultaAuditoriaAutorizacion().setParametroConsulta1(bean.getObjetoItemServicio().getId());
            bean.getParamConsultaAuditoriaAutorizacion().setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            bean.getParamConsultaAuditoriaAutorizacion().setCantidadRegistros(getCmAuditoriaAutorizacionRemoto().consultarCantidadPorDetalle(bean.getParamConsultaAuditoriaAutorizacion()));
            bean.setRegistrosAuditoriaAutorizacion(getCmAuditoriaAutorizacionRemoto().consultarListaPorDetalle(bean.getParamConsultaAuditoriaAutorizacion()));
        }  catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarAuditoriaAdjuntos(CmAuditoriaFacturaBean bean) {
        try {
            bean.setParamConsultaAuditoriaAdjunto( new ParamConsulta());
            bean.getParamConsultaAuditoriaAdjunto().setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsultaAuditoriaAdjunto().setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            bean.getParamConsultaAuditoriaAdjunto().setParametroConsulta4(CmAuditoriaAdjunto.TIPO_FACTURA);
            bean.getParamConsultaAuditoriaAdjunto().setCantidadRegistros(getCmAuditoriaAdjuntoRemoto().consultarCantidadPorDetalle(bean.getParamConsultaAuditoriaAdjunto()));
            bean.setRegistrosAuditoriaAdjuto(getCmAuditoriaAdjuntoRemoto().consultarListaPorDetalle(bean.getParamConsultaAuditoriaAdjunto()));
        }  catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarAuditoriaAdjuntosCmDetalles(CmAuditoriaFacturaBean bean) {
        try { 
            bean.setParamConsultaAuditoriaAdjunto( new ParamConsulta());
            bean.getParamConsultaAuditoriaAdjunto().setParametroConsulta3(bean.getObjetoItemServicio().getId());
            bean.getParamConsultaAuditoriaAdjunto().setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            bean.getParamConsultaAuditoriaAdjunto().setParametroConsulta4(CmAuditoriaAdjunto.TIPO_DETALLE);
            bean.getParamConsultaAuditoriaAdjunto().setCantidadRegistros(getCmAuditoriaAdjuntoRemoto().consultarCantidadPorDetalle(bean.getParamConsultaAuditoriaAdjunto()));
            bean.setRegistrosAuditoriaAdjutoCmDetalle(getCmAuditoriaAdjuntoRemoto().consultarListaPorDetalle(bean.getParamConsultaAuditoriaAdjunto()));
        }  catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void validarUsoAnexo4EnAuditoria(CmAuditoriaFacturaBean bean) {
        try { 
            /*
            bean.getParamConsultaUtilitario().setFiltros(new HashMap<>());
            bean.getParamConsultaUtilitario().getFiltros().put("auAnexos4Id", bean.getCmAuditoriaAutorizacion().getAnexo4().getId());
            int anexosEncontrados = (getCmAuditoriaAutorizacionRemoto().consultarCantidadLista(bean.getParamConsultaUtilitario()));
            bean.getCmAuditoriaAutorizacion().setAnexo4Valido( anexosEncontrados == 0 );
            */
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
     
    private void verificarExistenciaAnexo4Item(CmAuditoriaFacturaBean bean) {
        try { 
            bean.getParamConsultaUtilitario().setFiltros(new HashMap<>());
            bean.getParamConsultaUtilitario().getFiltros().put("auAnexos4Id", bean.getCmAuditoriaAutorizacion().getAnexo4().getId());
            int anexosEncontrados = (getCmAuditoriaAutorizacionRemoto().consultarCantidadLista(bean.getParamConsultaUtilitario()));
            bean.getCmAuditoriaAutorizacion().setAnexo4Valido( anexosEncontrados == 0 );
        
        }  catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
      
    private void obtenerSumatoriaTotalDetallesPorFactura(CmAuditoriaFacturaBean bean) {
        try { 
            bean.setParamConsultaUtilitario(new ParamConsulta());
            bean.getParamConsultaUtilitario().setParametroConsulta1(bean.getObjeto().getId());
//            if(bean.getObjeto().getCmFeRipsCarga()!= null && bean.getObjeto().getCmFeRipsCarga().getId() != null){
//                    bean.getParamConsultaUtilitario().setParametroConsulta2(1);
//            }
            BigDecimal totalObtenido =  getCmDetalleRemoto().totalizarValorFacturadoDetallesPorFactura( bean.getParamConsultaUtilitario() );
            bean.setSumaValorFacturadoDetallesPorFactura(totalObtenido);
        }  catch (Exception e) {
            bean.addError("Error al obtener sumatoria detalles: " + e.getMessage());
        }
    }
    
    private void obtenerValoresTemporalesMomentoDosFactura(CmAuditoriaFacturaBean bean) {
        try {
            bean.setParamConsultaUtilitario(new ParamConsulta());
            bean.getParamConsultaUtilitario().setParametroConsulta1(bean.getObjeto().getId());
            BigDecimal valorGlosaTotalFactura = getCmDetalleRemoto().totalizarValorGlosadoDetallesPorFactura(bean.getParamConsultaUtilitario());
            valorGlosaTotalFactura = valorGlosaTotalFactura == null ?
                              new BigDecimal(BigInteger.ZERO) : valorGlosaTotalFactura;
            
            BigDecimal  valorFacturaTotal = bean.getObjeto().getValorFactura().setScale(2, RoundingMode.DOWN);
            
            BigDecimal valorParaPagarFactura = valorFacturaTotal.subtract(valorGlosaTotalFactura)
                                        .setScale(4, RoundingMode.HALF_UP);
            bean.getObjeto().setValorInicialGlosa(valorGlosaTotalFactura);
            bean.getObjeto().setValorPagadoFactura(valorParaPagarFactura);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private String obtenerIdsDetallesStr(CmAuditoriaFacturaBean bean) {
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
    
     private String obtenerIdsMotivosEspecificosStr(CmAuditoriaFacturaBean bean) {
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

    private void listarAuditoriaCapitaDescuento(CmAuditoriaFacturaBean bean) {
        try { 
            bean.getParamConsultaAuditoriaDescuentoCapita().setParametroConsulta1(bean.getObjetoItemServicio().getId());
            bean.getParamConsultaAuditoriaDescuentoCapita().setParametroConsulta2(CONSULTA_TODOS_LOS_ITEMS);
            bean.getParamConsultaAuditoriaDescuentoCapita().setCantidadRegistros(getCmAuditoriaDescuentoCapitaRemoto().consultarCantidadPorDetalle(bean.getParamConsultaAuditoriaDescuentoCapita()));
            bean.setRegistrosAuditoriaCapitaDescuento(getCmAuditoriaDescuentoCapitaRemoto().consultarListaPorDetalle(bean.getParamConsultaAuditoriaDescuentoCapita()));
        }  catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
    private void listarCntContratos(CmAuditoriaFacturaBean bean) {
        try { 
            bean.getParamConsultaCntContratos().setCantidadRegistros(getCntContratoRemoto().consultarCantidadLista(bean.getParamConsultaCntContratos()));
            bean.setRegistrosCntContratos(getCntContratoRemoto().consultarLista(bean.getParamConsultaCntContratos()));
        }  catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarAnexo4(CmAuditoriaFacturaBean bean) {
        try { 
            String numeroDocumento;
            if(bean.isEsGestionIndividual()){
               numeroDocumento = bean.getObjetoItemServicio().getDocumento() != null ?  
                                 bean.getObjetoItemServicio().getDocumento() : null;
            }else{
               numeroDocumento = bean.getRegistrosDetallesSeleccionadoMasivos().isEmpty() ? null:
                                 bean.getRegistrosDetallesSeleccionadoMasivos().get(0).getDocumento();
            }
            
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
    
    private void listarSincronizaciones(CmAuditoriaFacturaBean bean) {
        try {
            bean.setParamConsultaUtilitario(new ParamConsulta());
            bean.getParamConsultaUtilitario().setParametroConsulta1( bean.getObjetoReintento().getId() );
            bean.getParamConsultaUtilitario().setRegistrosPagina(30);
            bean.setListaSincronizaciones(getCmSincronizacionRemoto().consultarPorRadicado(bean.getParamConsultaUtilitario()));
            bean.getParamConsultaUtilitario().setParametroConsulta1(new ParamConsulta());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
     private void verRadicadoPendientePorFactura(CmAuditoriaFacturaBean bean) {
        try {
            bean.setParamConsultaUtilitario(new ParamConsulta());
            bean.getParamConsultaUtilitario().setParametroConsulta1(bean.getObjetoReintento().getIdFactura());
            bean.setObjetoReintento(getCmRadicadoServicioRemoto().consultarRadicadoPendientePorFactura(bean.getParamConsultaUtilitario()) );
            bean.setParamConsultaUtilitario(new ParamConsulta());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
    private void verWsTransaccionesDetalle(CmAuditoriaFacturaBean bean) {
        try {
           bean.setParamConsultaUtilitario(new ParamConsulta()); 
           bean.getParamConsultaUtilitario().setParametroConsulta1(bean.getObjetoReintento().getIdFactura());
           bean.getParamConsultaUtilitario().setParametroConsulta2(obtenerMomentoFactura(bean));
           List<WsCmTransaccionDetalle> transaccionDetalles =  getWsCmTransaccionDetalleRemoto().consultarPorIdCmFactura( bean.getParamConsultaUtilitario());
           bean.setListaWsCmTransaccionDetalle(transaccionDetalles);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private short obtenerMomentoFactura(CmAuditoriaFacturaBean bean) {
        short momento = 0;
        try {
            switch (bean.getObjetoReintento().getCmFactura().getEstadoFactura()) {
                case CmFactura.ESTADO_FACTURA_ESPERA_SAP_PARA_CIERRE_AUDITORIA:
                    momento = WsCmTransaccion.MOMENTO_PASO_CIERRE_AUDITORIA;
                    break;
                case CmFactura.ESTADO_FACTURA_ESPERA_SAP_PARA_DEVOLUCION:
                    momento = WsCmTransaccion.MOMENTO_PASO_DEVOLUCION;
                    break;
                 case CmFactura.ESTADO_FACTURA_SIN_PROCESAR:
                    momento = WsCmTransaccion.MOMENTO_PASO_AUDITORIA;
                    break;
            }
        } catch (Exception e) {
            momento = 0;
        }
        return momento;
    }

    public void guardarReintentarSincronizacion(CmAuditoriaFacturaBean bean) {
         try {
            int idRadicado = bean.getObjetoReintento().getRadicado();
            List<SincronizacionNotificacionFactura> listaReintentos = new ArrayList();
            SincronizacionNotificacionFactura reintento = new SincronizacionNotificacionFactura();
            reintento.setRadicadoId(idRadicado);
            listaReintentos.add(reintento);
            CmRadicado radicado = getCmRadicadoServicioRemoto().consultar(idRadicado);
            if (radicado.getCmConciliacion() != null || radicado.getCmGlosaRespuesta() != null) {
                if (radicado.getCmConciliacion() != null) {
                    Integer idConciliacion = radicado.getCmConciliacion().getId();
                    reintento.setConciliacionId(idConciliacion);
                    reintento.setTipoTransaccion(NotificacionSapCmRadicadoCuentasMedicas.TIPO_TRANSACCION_M4_CONCILIACION);
                }
                if (radicado.getCmGlosaRespuesta() != null) {
                    Integer idGlosa = radicado.getCmGlosaRespuesta().getId();
                    reintento.setGlosaRespuestaId(idGlosa);
                    if( CmGlosaRespuesta.TIPO_RESPUESTA_CONCILIACION == radicado.getCmGlosaRespuesta().getTipoRespuesta()){
                        reintento.setTipoTransaccion(NotificacionSapCmRadicadoCuentasMedicas.TIPO_TRANSACCION_M4_CONCILIACION);
                    }                
                    if( CmGlosaRespuesta.TIPO_RESPUESTA_RESPUESTA == radicado.getCmGlosaRespuesta().getTipoRespuesta()){
                        reintento.setTipoTransaccion(NotificacionSapCmRadicadoCuentasMedicas.TIPO_TRANSACCION_M3_RESPUESTA_GLOSA);
                    } 
                }
                getNotificacionFacturaServicio().consumoServicioNotificacionFactura(listaReintentos);
            }
            if (radicado.getCmAuditoriaDevolucion() != null) {
                Integer idDevolucion = radicado.getCmAuditoriaDevolucion().getId();
                reintento.setDevolucionId(idDevolucion);
                getNotificacionFacturaServicio().consumoServicioDevolucionFactua(listaReintentos);
            }
            if (radicado.getCmAuditoriaCierre() != null) {
                Integer idCierre = radicado.getCmAuditoriaCierre().getId();
                reintento.setAuditoriaCierreId(idCierre);
                reintento.setTipoTransaccion(NotificacionSapCmRadicadoCuentasMedicas.TIPO_TRANSACCION_M2_FACTURA_AUDITADA);
                getNotificacionFacturaServicio().consumoServicioCierreAuditoriaFactura(listaReintentos);
            }
            if (radicado.getCmFactura() != null) {
                Integer idFactura = radicado.getCmFactura().getId();
                reintento.setCmFacturaId(idFactura);
                reintento.setTipoTransaccion(NotificacionSapCmRadicadoCuentasMedicas.TIPO_TRANSACCION_M1_RADICACION_FACTURA);
                getNotificacionFacturaServicio().consumoServicioNotificacionFacturaM1(listaReintentos);
            }
            
            mostrarMensajeSiHayOperacionesMasivas(radicado, bean);
                      
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarAnexo4Items(CmAuditoriaFacturaBean bean) {
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
    
    private void guardarDevolucionFactuar(CmAuditoriaFacturaBean bean) {
        try { 
            
            boolean hayCambioEstado = false; 
            boolean esModalidadContratoNormal = !esModalidadContratoCapita_o_PGP(bean);
            bean.auditoriaGuardar(bean.getCmAuditoriaDevolucion());
            int idDevolucion =  getCmAuditoriaDevolucionRemoto().insertar(bean.getCmAuditoriaDevolucion());
            
            if( idDevolucion > 0 ){
                guardarEstadoAuditoriaFactura(bean, bean.getObjeto().getId(), null, CmFactura.ESTADO_FACTURA_DEVUELTA);
                hayCambioEstado = true;
            }
           
            if( idDevolucion > 0 && hayCambioEstado && esModalidadContratoNormal ){
                 CmEnviosGlosasServicioImpl envioGlosa = new CmEnviosGlosasServicioImpl();
                 envioGlosa.crearRadicadoXDevolucionFactuar(idDevolucion);
            }
            
            if( ! esModalidadContratoNormal ){
                guardarEstadoAuditoriaFactura(bean, bean.getObjeto().getId(),CmFactura.TIPO_AUDITORIA_CIERRE_AUDITORIA, null);
                guardarHistorialEstadoFactura(bean, bean.getObjeto().getId(), CmFactura.ESTADO_FACTURA_DEVUELTA);
            }
            
            borrarGlosadoDetallesDevolucion(bean.getObjeto().getId());
            
            bean.addMensaje("Se ha realizado la devolución de la factura id "+bean.getObjeto().getId());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarFechaAuditoria(CmAuditoriaFacturaBean bean) {
        try { 
           getCmFacturaRemoto().actualizarFechaAuditoriaCmFactura( String.valueOf(bean.getObjeto().getId()), new Date());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarFechaDevolucion(CmAuditoriaFacturaBean bean) {
        try { 
           getCmFacturaRemoto().actualizarFechaDevolucionCmFactura(String.valueOf(bean.getObjeto().getId()), new Date());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarGestionarMasivoServicio(CmAuditoriaFacturaBean bean){
       try {
                    
            StringBuilder errorCopiado =  new StringBuilder();
            if( ! copiarArchivosEnDisco(bean.getRegistrosAuditoriaAdjutoCmDetalle(), errorCopiado)){       
                 bean.addError(errorCopiado.toString());  
            }
           
            for (CmDetalle detalle : bean.getRegistrosDetallesSeleccionadoMasivos()) {

               detalle.setCantidadConceptosContablesAsociados(obtenerConceptosContablesActuales(detalle));

                for (CmAuditoriaConceptoContable concepto : bean.getRegistrosConceptoContable()) {
                    if (concepto.getId() == null && detalle.getCantidadConceptosContablesAsociados() == 0) {
                        concepto.setCmDetalle(new CmDetalle(detalle.getId()));
                        bean.auditoriaGuardar(concepto);
                        getCmAuditoriaConceptoContableRemoto().insertar(concepto);
                    }
                }
                for (CmAuditoriaDiagnostico diagnostico : bean.getRegistrosAuditoriaDiagnostico()) {
                    if (diagnostico.getId() == null) {
                        diagnostico.setCmDetalle(new CmDetalle(detalle.getId()));
                        bean.auditoriaGuardar(diagnostico);
                        getCmAuditoriaDiagnosticoRemoto().insertar(diagnostico);
                    }
                }
                for (CmAuditoriaMotivoGlosa motivo : bean.getRegistrosAuditoriaMotivoGlosa()) {
                    if (motivo.getId() == null) {
                        BigDecimal porcentaje = motivo.getPorcentaje();
                        if (porcentaje.compareTo(new BigDecimal("0.0")) > 0
                                && detalle.getValorFacturado().compareTo(new BigDecimal("0.0")) > 0) {
                            BigDecimal promedio = porcentaje.divide(new BigDecimal(100), 4, RoundingMode.CEILING);
                            BigDecimal valorMotivo = promedio.multiply(detalle.getValorFacturado()).setScale(4, RoundingMode.HALF_UP);
                            motivo.setValorMotivo(valorMotivo);
                        }
                        motivo.setCmDetalle(new CmDetalle(detalle.getId()));
                        bean.auditoriaGuardar(motivo);
                        getCmAuditoriaMotivosGlosaRemoto().insertar(motivo);
                    }
                }
                for (CmAuditoriaAutorizacion autorizacion : bean.getRegistrosAuditoriaAutorizacion()) {
                    if (autorizacion.getId() == null && detalle.getCantidadAutorizacionesAsociadas() == 0) {
                        autorizacion.setActiva(true);
                        autorizacion.setCmDetalle(new CmDetalle(detalle.getId()));
                        if (detalle.getCmFacturas() != null) {
                            autorizacion.setCmFactura(new CmFactura(detalle.getCmFacturas().getId()));
                        }
                        bean.auditoriaGuardar(autorizacion);
                        getCmAuditoriaAutorizacionRemoto().insertar(autorizacion);
                    }
                }
                
               for (CmAuditoriaAdjunto adjunto : bean.getRegistrosAuditoriaAdjutoCmDetalle()) {
                   if (adjunto.getId() == null) {
                       adjunto.setCmFactura(new CmFactura(detalle.getCmFacturas().getId()));
                       adjunto.setCmDetalle(new CmDetalle(detalle.getId()));
                       bean.auditoriaGuardar(adjunto);
                       if (adjunto.isGuardadoEnDisco()) {
                           getCmAuditoriaAdjuntoRemoto().insertar(adjunto);
                           adjunto.setGuardadoEnDB(true);
                       }
                   }
               }
             
               getCmDetalleRemoto().actualizarRecobro(String.valueOf(detalle.getId()) ,bean.getAplicaRecobro());
               
           }

            if (!bean.isError()) {
                bean.addMensaje("Se ha realizado una gestión masiva para la factura de id  " + bean.getObjeto().getId());
            }

        } catch (Exception e) {
            bean.addError("Se ha Producido error en gestionarMasivoServicio : "+e.getMessage());
            Logger.getLogger(CmAuditoriaFacturaServicioImpl.class.getName()).log(Level.SEVERE, null, e);
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

    private void guardarGestionarItemServicio(CmAuditoriaFacturaBean bean){
        try {
            CmDetalle detalle = bean.getObjetoItemServicio();
            CmHistoricoFactura historico = new CmHistoricoFactura();
            historico.setCmFactura(new CmFactura(bean.getObjeto().getId()));
            historico.setDescripcion("Eliminación de dato");
            bean.auditoriaGuardar(historico);
            
            for(Integer idConcepto : detalle.getListaConceptosEliminar() ){            
                CmAuditoriaConceptoContable concepto =  getCmAuditoriaConceptoContableRemoto().consultar(idConcepto);
                if(concepto != null){  
                  guardarHistorialBorrado(historico, concepto.toString(), CmHistoricoFactura.TIPO_CONCEPTO_CONTABLE);
                  bean.getHashInsumoAuditoriaEliminados().put(idConcepto, concepto.toString());
                }
                getCmAuditoriaConceptoContableRemoto().eliminar(idConcepto);
            }
            
           int cantidadConceptos = obtenerConceptosContablesActuales(detalle);
           detalle.setCantidadConceptosContablesAsociados(cantidadConceptos);
              
            for (CmAuditoriaConceptoContable concepto : detalle.getListaCmAuditoriaConceptoContable()) {
                concepto.castCentroCostoMaestro(concepto.getMaestroCentroCosto());
                concepto.castConceptoContableMaestro(concepto.getMaestroConceptos());
                if (concepto.getId() == null && cantidadConceptos == 0) {
                    concepto.setCmDetalle(new CmDetalle(bean.getObjetoItemServicio().getId()));
                    bean.auditoriaGuardar(concepto);
                    getCmAuditoriaConceptoContableRemoto().insertar(concepto);
                    bean.getHashInsumoAuditoriaInsertados().put(concepto.getId(), concepto.toString());
                }
            }     
                    
            for (CmAuditoriaDiagnostico diagnostico : detalle.getListaCmAuditoriaDiagnosticos()) {
                if (diagnostico.getId() == null) {
                    diagnostico.setCmDetalle(new CmDetalle(bean.getObjetoItemServicio().getId()));
                    bean.auditoriaGuardar(diagnostico);
                    getCmAuditoriaDiagnosticoRemoto().insertar(diagnostico);
                    bean.getHashInsumoAuditoriaInsertados().put(diagnostico.getId(), diagnostico.toString());
                }
            }
            for(Integer idDiagnostico : detalle.getListaDiagnosticosEliminar() ){ 
                CmAuditoriaDiagnostico diagnostico =  getCmAuditoriaDiagnosticoRemoto().consultar(idDiagnostico);
                if(diagnostico != null){  
                    guardarHistorialBorrado(historico, diagnostico.toString(), CmHistoricoFactura.TIPO_DIAGNOSTICO);
                    bean.getHashInsumoAuditoriaEliminados().put(idDiagnostico, diagnostico.toString());
                }
                getCmAuditoriaDiagnosticoRemoto().eliminar(idDiagnostico);
            }
            
            for (CmAuditoriaMotivoGlosa motivo : detalle.getListaCmAuditoriaMotivosGlosa()) {
                if (motivo.getId() == null) {
                    motivo.setCmDetalle(new CmDetalle(bean.getObjetoItemServicio().getId()));
                    bean.auditoriaGuardar(motivo);
                    getCmAuditoriaMotivosGlosaRemoto().insertar(motivo);
                    bean.getHashInsumoAuditoriaInsertados().put(motivo.getId(), motivo.toString());
                }
            }
            for(Integer idMotivo: detalle.getListaMotivosEliminar() ){              
               CmAuditoriaMotivoGlosa motivo =  getCmAuditoriaMotivosGlosaRemoto().consultar(idMotivo);
               if(motivo != null){  
                    guardarHistorialBorrado(historico, motivo.toString(), CmHistoricoFactura.TIPO_MOTIVO_GLOSA);
                    bean.getHashInsumoAuditoriaEliminados().put(idMotivo, motivo.toString());
               }   
               getCmAuditoriaMotivosGlosaRemoto().eliminar(idMotivo);
            }
            
            for (CmAuditoriaAutorizacion autorizacion : detalle.getListacmAuditoriaAutorizacion()) {
                if (autorizacion.getId() == null) {
                    autorizacion.setCmDetalle(new CmDetalle(bean.getObjetoItemServicio().getId()));
                    if(detalle.getCmFacturas() != null){
                       autorizacion.setCmFactura(new CmFactura(detalle.getCmFacturas().getId()));
                    }
                    bean.auditoriaGuardar(autorizacion);
                    getCmAuditoriaAutorizacionRemoto().insertar(autorizacion);
                    bean.getHashInsumoAuditoriaInsertados().put(autorizacion.getId(), autorizacion.toString());
                }
            } 
            for(Integer idAutorizacion: detalle.getListaAutorizacionEliminar() ){      
                CmAuditoriaAutorizacion autorizacion = getCmAuditoriaAutorizacionRemoto().consultar(idAutorizacion);
                if(autorizacion !=null){
                   guardarHistorialBorrado(historico, autorizacion.toString(), CmHistoricoFactura.TIPO_AUTORIZACION);
                   bean.getHashInsumoAuditoriaEliminados().put(idAutorizacion, autorizacion.toString());
                }
                getCmAuditoriaAutorizacionRemoto().eliminar(idAutorizacion);
            }
            
            StringBuilder errorCopiado =  new StringBuilder();
            if( ! copiarArchivosEnDisco(detalle.getListaCmAuditoriaAdjuntos(), errorCopiado)){       
                 bean.addError(errorCopiado.toString());  
            }
            
            for (CmAuditoriaAdjunto adjunto : detalle.getListaCmAuditoriaAdjuntos()) {
                if (adjunto.getId() == null) {
                    adjunto.setCmFactura(new CmFactura(detalle.getCmFacturas().getId()));
                    adjunto.setCmDetalle(detalle);
                    bean.auditoriaGuardar(adjunto);
                    if (adjunto.isGuardadoEnDisco()) {
                        getCmAuditoriaAdjuntoRemoto().insertar(adjunto);
                        adjunto.setGuardadoEnDB(true);
                    }
                }
            }
            
            for (Integer idAutorizacion : bean.getRegistrosAdjuntosEliminar()) {
                CmAuditoriaAdjunto adjuto = getCmAuditoriaAdjuntoRemoto().consultar(idAutorizacion);
                if (adjuto != null) {
                    getCmAuditoriaAdjuntoRemoto().eliminar(idAutorizacion);
                }
            }
            
            getCmDetalleRemoto().actualizarRecobro(String.valueOf(detalle.getId()) ,bean.getAplicaRecobro());
            
            if(!bean.isError()){
              bean.addMensaje("Se ha realizado la gestión de item de id de detalle " + bean.getObjetoItemServicio().getId());
            }
           

        } catch (Exception e) {
            bean.addError("Se ha Producido error en gestionarItemServicio : " + e.getMessage());
            Logger.getLogger(CmAuditoriaFacturaServicioImpl.class.getName()).log(Level.SEVERE, null, e);
        }

    }
    
    private void guardarHistorialBorrado(CmHistoricoFactura  historico, String contenido, int tipo ) {
        try {
            historico.setTipos(tipo);
            historico.setCmHistoricoFacturas(contenido);
            getCmHistoricoFacturaRemoto().insertar(historico);
        } catch (Exception e) {
            Logger.getLogger(CmAuditoriaFacturaServicioImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    private void guardarAdjunto(CmAuditoriaFacturaBean bean) {
        try {
            CmFactura factura = bean.getObjeto();
            
            StringBuilder errorCopiado =  new StringBuilder();
            if( ! copiarArchivosEnDisco( factura.getListaCmAuditoriaAdjuntos(), errorCopiado)){       
                 bean.addError(errorCopiado.toString());  
            }
           
            for (CmAuditoriaAdjunto adjunto : factura.getListaCmAuditoriaAdjuntos()) {
                if (adjunto.getId() == null) {
                    adjunto.setCmFactura(new CmFactura(factura.getId()));
                    bean.auditoriaGuardar(adjunto);
                    if(adjunto.isGuardadoEnDisco()){
                         getCmAuditoriaAdjuntoRemoto().insertar(adjunto);
                         adjunto.setGuardadoEnDB(true);
                    }else{
                      bean.addError("No se ha podido guardar el adjunto de nombre " + 
                              adjunto.getArchivoNombre() + " - " +getPostFijoError() );
                        setPostFijoError("");
                    }
                }
            }
            for (Integer idAdjunto : factura.getListaAdjuntosEliminar()) {
                getCmAuditoriaAdjuntoRemoto().eliminarSegunFactura(idAdjunto,factura.getId());
            }
            factura.getListaAdjuntosEliminar().clear();          
            
           if(!bean.isError()){
              bean.addMensaje("Se ha realizado la operación en adjuntos para la factura " + factura.getId());
           } 

        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarEstadoAuditoriaFactura(CmAuditoriaFacturaBean bean, Integer idFactura,
                                               Integer idTipoAuditoria, Integer idEstadoFactura) {
        try {
            bean.setParamConsultaUtilitario(new ParamConsulta());
            bean.getParamConsultaUtilitario().setParametroConsulta1(idFactura);
            if(idTipoAuditoria != null){
                   bean.getParamConsultaUtilitario().setParametroConsulta2(idTipoAuditoria); 
            }
            if(idEstadoFactura != null){
             bean.getParamConsultaUtilitario().setParametroConsulta3(idEstadoFactura);
            }
            getCmFacturaRemoto().actualizarEstadoAuditoria( bean.getParamConsultaUtilitario()); 
            bean.setParamConsultaUtilitario(new ParamConsulta());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarHistorialEstadoFactura(CmAuditoriaFacturaBean bean, int idFactura, int estadoFactura) {
      try {
          CmFacturaEstado facturaEstado = new CmFacturaEstado();
          facturaEstado.setCmFactura(new CmFactura(idFactura));
          facturaEstado.setEstadoFactura(estadoFactura);
          bean.auditoriaGuardar(facturaEstado);
          getCmFacturaEstadoRemoto().insertar(facturaEstado);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarUsuarioGestiona(CmAuditoriaFacturaBean bean, Integer idFactura , Integer idUsuarioParaAsignar ) {
        try {
            if (bean.getObjeto().getId() != null
                    && bean.getObjeto().getUsuarioLider().getId() != null) {
                bean.setParamConsultaUtilitario(new ParamConsulta());
                bean.getParamConsultaUtilitario().setParametroConsulta1(idFactura);
                bean.getParamConsultaUtilitario().setParametroConsulta2(idUsuarioParaAsignar);
                getCmFacturaRemoto().actualizarUsuarioGestiona(bean.getParamConsultaUtilitario());
                bean.setParamConsultaUtilitario(new ParamConsulta());
            }
      } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarCapitaDescuento(CmAuditoriaFacturaBean bean) {
        try {
            CmDetalle detalle = bean.getObjetoItemServicio();  
             for (CmAuditoriaCapitaDescuento capitaDescuento : bean.getRegistrosAuditoriaCapitaDescuento()) {
                if (capitaDescuento.getId() == null) {
                    capitaDescuento.setCmDetalle(new CmDetalle(detalle.getId()));
                    bean.auditoriaGuardar(capitaDescuento);
                    getCmAuditoriaDescuentoCapitaRemoto().insertar(capitaDescuento);
                }
            } 
            for(Integer idCapitaDescuento: detalle.getListaCapitaDescuentoEliminar()){
                getCmAuditoriaDescuentoCapitaRemoto().eliminar(idCapitaDescuento);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
     private void guardarCapitaDescuentoMasivo(CmAuditoriaFacturaBean bean) {
         try {
             for (CmDetalle detalle : bean.getRegistrosDetallesSeleccionadoMasivos()) {
                 for (CmAuditoriaCapitaDescuento capitaDescuento : bean.getRegistrosAuditoriaCapitaDescuento()) {
                     if (capitaDescuento.getId() == null && detalle.getCantidadCapitaDescuentosAsociados() == 0) {
                         capitaDescuento.setCmDetalle(new CmDetalle(detalle.getId()));
                         bean.auditoriaGuardar(capitaDescuento);
                         getCmAuditoriaDescuentoCapitaRemoto().insertar(capitaDescuento);
                     }
                 }
                 for (Integer idCapitaDescuento : detalle.getListaCapitaDescuentoEliminar()) {
                     getCmAuditoriaDescuentoCapitaRemoto().eliminar(idCapitaDescuento);
                 }
             }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarEstadoCapitaApliacado(CmAuditoriaFacturaBean bean){
     try {
            CmDetalle detalle = bean.getObjetoItemServicio(); 
            Integer idDetalle = detalle.getId();
            if(idDetalle != null){
                detalle =  getCmDetalleRemoto().consultar(idDetalle); 
                boolean  hayDescuentoCapita =  detalle.getCantidadCapitaDescuentosAsociados() > 0 ;
                detalle.setAplicaDc(hayDescuentoCapita);
                getCmDetalleRemoto().actualizar(detalle);
            }       
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarEstadosAplicaEnCmDetalles(CmAuditoriaFacturaBean bean, CmDetalle detalle) {
        try {
            if (detalle.getId() != null) {
                Integer idDetalle = detalle.getId();
                detalle = getCmDetalleRemoto().consultar(idDetalle);
                boolean hayAutorizaciones = detalle.getCantidadAutorizacionesAsociadas() > 0;
                boolean hayConceptos = detalle.getCantidadConceptosContablesAsociados() > 0;
                boolean hayDx = detalle.getCantidadDiagnosticosAsociados() > 0;
                boolean hayGlosa = detalle.getCantidadMotivosAsociadas() > 0;
                detalle.setAplicaAutorizacion(hayAutorizaciones);
                detalle.setAplicaConcepto(hayConceptos);
                detalle.setAplicaDx(hayDx);
                detalle.setAplicaGlosa(hayGlosa);
                
                String conceptosAcumulados = hayConceptos ? obtenerConceptosConcatenados( 
                                                  detalle.getListaCmAuditoriaConceptoContable() ): "";
                detalle.setConceptoContable(conceptosAcumulados);
                
                String diagnosticosAcumulados = hayDx ? obtenerDiagnosticosConcatenados(
                                                 detalle.getListaCmAuditoriaDiagnosticos()) : "";
                detalle.setNombreDx(diagnosticosAcumulados);
                
                String motivosAcumulados = hayGlosa ? obtenerMotivosConcatenados( 
                                                 detalle.getListaCmAuditoriaMotivosGlosa()) : "";
                detalle.setMotivoGlosa(motivosAcumulados );
                
                String observacionesAcumuladas = hayGlosa ? obtenerObservacionMotivosConcatenados( 
                                                 detalle.getListaCmAuditoriaMotivosGlosa()) : "";
                detalle.setObservacion( observacionesAcumuladas );
                
                detalle.setValorGlosa(calcularValorGlosaDetalle(bean, detalle));
                getCmDetalleRemoto().actualizar(detalle);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
     private void guardarEstadoDescuentoCapitaCmDetalles(CmAuditoriaFacturaBean bean, CmDetalle detalle) {
        try {
            if (detalle.getId() != null) {
                Integer idDetalle = detalle.getId();
                detalle = getCmDetalleRemoto().consultar(idDetalle);
                boolean hayDescuentoCapita = detalle.getCantidadCapitaDescuentosAsociados() > 0;
                detalle.setAplicaDc(hayDescuentoCapita);
                getCmDetalleRemoto().actualizar(detalle);
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarEstadosAplicaEnCmDetallesMasivo(CmAuditoriaFacturaBean bean) {
        try {
            for (CmDetalle detalle : bean.getRegistrosDetallesSeleccionadoMasivos()) {
               if (!bean.isError()) {
                   guardarEstadosAplicaEnCmDetalles(bean, detalle);
               }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarEstadoDescuentoCapitaCmDetallesMasivo(CmAuditoriaFacturaBean bean) {
        try {
            for (CmDetalle detalle : bean.getRegistrosDetallesSeleccionadoMasivos()) {
               if (!bean.isError()) {
                   guardarEstadoDescuentoCapitaCmDetalles(bean, detalle);
               }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarRegistroCmAuditoriaCierre(CmAuditoriaFacturaBean bean) {
        try { 
            bean.setParamConsultaUtilitario(new ParamConsulta());
            bean.getParamConsultaUtilitario().setParametroConsulta1(bean.getObjeto().getId());
            int cantidadDetalles = (getCmDetalleRemoto().consultarCantidadListaDetallesPorFactura(bean.getParamConsultaUtilitario()));
            bean.setParamConsultaUtilitario(new ParamConsulta());
            
            CmAuditoriaCierre cierre = new CmAuditoriaCierre();
            cierre.setCmFactura(new CmFactura(bean.getObjeto().getId()));  
            cierre.setCantidadDetalles(cantidadDetalles);
            cierre.setCantidadDetallesRegistradas(cantidadDetalles);
            cierre.setValorFacturado(bean.getObjeto().getValorFactura());
            cierre.setFechaHoraRegistroInicio(new Date());
            cierre.setFechaHoraRegistroFinalizacion(new Date());
            cierre.setFechaHoraCrea(new Date());
            cierre.setUsuarioCrea(bean.getConexion().getUsuario().getUsuarioNombre());
            cierre.setTerminalCrea(bean.getConexion().getIp());
            getCmAuditoriaCierreRemoto().insertar(cierre);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    
    private void guardarEstadoCapitaApliacadoMasivo(CmAuditoriaFacturaBean bean){
        try {
            for (CmDetalle detalle : bean.getRegistrosDetallesSeleccionadoMasivos()) {
                Integer idDetalle = detalle.getId();
                if ( idDetalle != null && detalle.getCantidadCapitaDescuentosAsociados() == 0 ) {
                    detalle = getCmDetalleRemoto().consultar(idDetalle);
                    boolean hayDescuentoCapita = detalle.getCantidadCapitaDescuentosAsociados() > 0;
                    detalle.setAplicaDc(hayDescuentoCapita);
                    getCmDetalleRemoto().actualizar(detalle);
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
          
    private void guardarEdicionMotivoGlosa(CmAuditoriaFacturaBean bean) {
        try {
             getCmAuditoriaMotivosGlosaRemoto().actualizar(bean.getCmAuditoriaMotivoGlosa());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarReversionFactura(CmAuditoriaFacturaBean bean){
        try {
           
            Integer idCierre = bean.getObjeto().getCmAuditoriaCierre().getId();
                    idCierre = idCierre == null ? 0 : idCierre;
            Integer idRadicado = bean.getObjeto().getCmAuditoriaCierre().getCmRadicado().getId();
                    idRadicado = idRadicado == null ? 0 : idRadicado;
            Integer idEncabezado =  bean.getObjeto().getCmAuditoriaCierre().getCmRadicado().getCmSincronizacionEncabezado().getId();
                    idEncabezado = idEncabezado == null ? 0 : idEncabezado ;
           
            deshabilitarEstadoActivoCmRadicado(idRadicado);     
                    
            bean.setParamConsultaUtilitario(new ParamConsulta());
            bean.getParamConsultaUtilitario().setParametroConsulta1(idEncabezado);
            bean.getParamConsultaUtilitario().setRegistrosPagina(20000);
            List<CmSincronizacionDetalle> sincronizacionesDetalles =  getCmSincronizacionDetalleRemoto().consultarLista(bean.getParamConsultaUtilitario()) ;
            for (CmSincronizacionDetalle sincronizacionesDetalle : sincronizacionesDetalles) {
                getCmSincronizacionDetalleRemoto().eliminar(sincronizacionesDetalle.getId());
            }
            
            getCmSincronizacionEncabezadoRemoto().eliminar(idEncabezado);
            
            bean.setParamConsultaUtilitario(new ParamConsulta());
            bean.getParamConsultaUtilitario().setParametroConsulta1(idRadicado);
            bean.getParamConsultaUtilitario().setRegistrosPagina(1000);
            List<CmSincronizacion> sincronizaciones = getCmSincronizacionRemoto().consultarLista(bean.getParamConsultaUtilitario()); 
            for (CmSincronizacion sincronizacion : sincronizaciones) {
                getCmSincronizacionRemoto().eliminar(sincronizacion.getId());
            }
              
            getCmRadicadoServicioRemoto().eliminar(idRadicado);
            
            getCmAuditoriaCierreRemoto().eliminar(idCierre);
                           
            bean.setParamConsultaUtilitario(new ParamConsulta());
            bean.getParamConsultaUtilitario().setParametroConsulta1(bean.getObjeto().getId());
            bean.getParamConsultaUtilitario().setParametroConsulta2(bean.getObjeto().getId());
            List<CmDetalle> detalles = getCmDetalleRemoto().consultarListaDetallesPorFactura(bean.getParamConsultaUtilitario());
            for (CmDetalle detalle : detalles) {
                detalle.setValorPagado(BigDecimal.ZERO);
                detalle.setValorPendiente(BigDecimal.ZERO);
                detalle.setValorPendienteActual(BigDecimal.ZERO);
                detalle.setEstado(CmDetalle.ESTADO_DETALLE_REGITRADO);
                getCmDetalleRemoto().actualizar(detalle);
            }
            
            String facturaHistoricaString = bean.getObjeto().toString();
            
            CmFactura factura = getCmFacturaRemoto().consultar(bean.getObjeto().getId());
            factura.setValorPagadoFactura(BigDecimal.ZERO);
            factura.setValorPendienteActual(BigDecimal.ZERO);
            factura.setValorInicialGlosa(BigDecimal.ZERO);
            factura.setTipoAuditoria(CmFactura.TIPO_AUDITORIA_SIN_AUDITORIA);
            factura.setEstadoFactura(CmFactura.ESTADO_FACTURA_SIN_AUDITORIA);
            factura.setUsuarioGestiona(new Usuario(bean.getConexion().getUsuario().getId()));
            getCmFacturaRemoto().actualizar(factura);
            
            CmHistoricoFactura historico = new CmHistoricoFactura();
            historico.setCmFactura(new CmFactura(bean.getObjeto().getId()));
            historico.setDescripcion("Revertir Factura");
            bean.auditoriaGuardar(historico);
            guardarHistorialBorrado(historico, facturaHistoricaString, CmHistoricoFactura.TIPO_REVERTIR_FACTURA_M2);
           
        } catch (Exception e) {
            bean.addError("Error guardarReversionFactura : "+e.toString());
        }
    }

    private boolean deshabilitarEstadoActivoCmRadicado(Integer idRadicado) throws Exception {
        boolean hayDeshabilitacion = false;
        try {
            if (idRadicado != null && idRadicado != 0) {
                CmRadicado obj = new CmRadicado(idRadicado);
                obj.setEstado_radicado(true);
                getCmRadicadoServicioRemoto().actualizarEstado(obj);
                hayDeshabilitacion = true;
            }
        } catch (Exception e) {
            hayDeshabilitacion = false;
        }
        return hayDeshabilitacion;
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
     
    private void borrarConceptosPorFactura(CmAuditoriaFacturaBean bean) {
        try {
            CmHistoricoFactura historico = new CmHistoricoFactura();
            historico.setCmFactura(new CmFactura(bean.getObjeto().getId()));
            historico.setDescripcion("Eliminación de dato");
            bean.auditoriaGuardar(historico);
            for (CmDetalle detalle : bean.getRegistrosDetallesSeleccionadoMasivos()) {
                for (CmAuditoriaConceptoContable concepto : detalle.getListaCmAuditoriaConceptoContable()) {
                    if (concepto.getId() != null) {
                        guardarHistorialBorrado(historico, concepto.toString(), CmHistoricoFactura.TIPO_CONCEPTO_CONTABLE);
                        getCmAuditoriaConceptoContableRemoto().eliminar(concepto.getId());
                    }
                }
                }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrarMotivosPorFactura(CmAuditoriaFacturaBean bean) {
        try {
            CmHistoricoFactura historico = new CmHistoricoFactura();
            historico.setCmFactura(new CmFactura(bean.getObjeto().getId()));
            historico.setDescripcion("Eliminación de dato");
            bean.auditoriaGuardar(historico);
            for (CmDetalle detalle : bean.getRegistrosDetallesSeleccionadoMasivos()) {
                for (CmAuditoriaMotivoGlosa motivo : detalle.getListaCmAuditoriaMotivosGlosa()) {
                    if (motivo.getId() != null) {
                        guardarHistorialBorrado(historico, motivo.toString(), CmHistoricoFactura.TIPO_MOTIVO_GLOSA);
                        getCmAuditoriaMotivosGlosaRemoto().eliminar(motivo.getId());
                    }
                }
                }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void borrarMotivosEspecificosPorFactura(CmAuditoriaFacturaBean bean, String codigosMotivosStr) {
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
    
     private void borrarDiagnosticosPorFactura(CmAuditoriaFacturaBean bean) {
        try {
            CmHistoricoFactura historico = new CmHistoricoFactura();
            historico.setCmFactura(new CmFactura(bean.getObjeto().getId()));
            historico.setDescripcion("Eliminación de dato");
            bean.auditoriaGuardar(historico);
            for (CmDetalle detalle : bean.getRegistrosDetallesSeleccionadoMasivos()) {
                for (CmAuditoriaDiagnostico diagnostico : detalle.getListaCmAuditoriaDiagnosticos()) {
                    if (diagnostico.getId() != null) {
                        guardarHistorialBorrado(historico, diagnostico.toString(), CmHistoricoFactura.TIPO_DIAGNOSTICO);
                        getCmAuditoriaDiagnosticoRemoto().eliminar(diagnostico.getId());
                    }
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
     private void borrarAutorizacionesPorFactura(CmAuditoriaFacturaBean bean) {
        try {
            CmHistoricoFactura historico = new CmHistoricoFactura();
            historico.setCmFactura(new CmFactura(bean.getObjeto().getId()));
            historico.setDescripcion("Eliminación de dato");
            bean.auditoriaGuardar(historico);
            for (CmDetalle detalle : bean.getRegistrosDetallesSeleccionadoMasivos()) {
                for (CmAuditoriaAutorizacion autorizacion : detalle.getListacmAuditoriaAutorizacion()) {
                    if (autorizacion.getId() != null) {
                        guardarHistorialBorrado(historico, autorizacion.toString(), CmHistoricoFactura.TIPO_AUTORIZACION);
                        getCmAuditoriaAutorizacionRemoto().eliminar(autorizacion.getId());
                    }
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
     
      private void borrarDescuentoCapitaPorFactura(CmAuditoriaFacturaBean bean) {
        try {
            CmHistoricoFactura historico = new CmHistoricoFactura();
            historico.setCmFactura(new CmFactura(bean.getObjeto().getId()));
            historico.setDescripcion("Eliminación de dato");
            bean.auditoriaGuardar(historico);
            for (CmDetalle detalle : bean.getRegistrosDetallesSeleccionadoMasivos()) {
                for (CmAuditoriaCapitaDescuento descuentoCapita : detalle.getListaCmAuditoriaDescuentoCapita()) {
                    if (descuentoCapita.getId() != null) {
                        guardarHistorialBorrado(historico, descuentoCapita.toString(), CmHistoricoFactura.TIPO_DESCUENTO_CAPITA);
                        getCmAuditoriaDescuentoCapitaRemoto().eliminar(descuentoCapita.getId());
                    }
                }
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
      
    private void borrarGlosadoDetallesDevolucion(int idFactura) {
        try {          
            CmAuditoriaMasivaModulo auditoriaModulo = new CmAuditoriaMasivaModulo();
            List<Integer> idFacturas = new ArrayList<>();
            idFacturas.add(idFactura);
            auditoriaModulo.setIdsFacturas(idFacturas);
            auditoriaModulo.setTipoOperacionMasiva(CmAuditoriaMasivaModulo.TIPO_OPERACION_BORRAR_GLOSADO_DEVOLUCION);
            getAuditoriaMasivaGenericoRemoto().ejecutarRegistroCierre(auditoriaModulo);
        } catch (Exception e) {}
    }
    
    public BigDecimal calcularValorGlosaDetalle(CmAuditoriaFacturaBean bean, CmDetalle detalle) {
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
    
    public  String obtenerConceptosConcatenados(List<CmAuditoriaConceptoContable> listaConceptos){
        String acumuladorConceptosStr = "";
        if(!listaConceptos.isEmpty()){
            for (CmAuditoriaConceptoContable conceptoContable : listaConceptos) {
                    acumuladorConceptosStr+= conceptoContable.getMaeConceptosValor() +" --";   
            }
        } 
       return acumuladorConceptosStr;
    }
    
    public  String obtenerDiagnosticosConcatenados(List<CmAuditoriaDiagnostico> listaDiagnostico){
        String acumuladorDiagnosticoStr = "";
        if(!listaDiagnostico.isEmpty()){
            for (CmAuditoriaDiagnostico diagnostico : listaDiagnostico) {
                    acumuladorDiagnosticoStr+= diagnostico.getMaDiagnosticoCodigo()+ "-" +
                                               diagnostico.getMaDiagnosticoValor() + " ;";   
            }
        }
       return acumuladorDiagnosticoStr;
    }
    
    public  String obtenerMotivosConcatenados(List<CmAuditoriaMotivoGlosa> listaMotivosGlosa){
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
    
    public  String obtenerObservacionMotivosConcatenados(List<CmAuditoriaMotivoGlosa> listaMotivosGlosa){
        String acumuladorObservacionMotivosStr = "";
        if(!listaMotivosGlosa.isEmpty()){
            for (CmAuditoriaMotivoGlosa motivo : listaMotivosGlosa) {
                acumuladorObservacionMotivosStr += motivo.getObservacion() + " -- ";
            }
        }
       return acumuladorObservacionMotivosStr;
    }
    
    private List<Maestro> obtenerListaFiltradaPorAccionSegunResolucion(CmAuditoriaFacturaBean bean, List<Maestro> lista) {       
        int accion = bean.getObjeto().getVersion() ? MaestroAccion.CM_MOTIVO_GLOSA_ESPEFIFICO_RESOLUCION_2284
                : MaestroAccion.CM_MOTIVO_GLOSA_ESPEFIFICO_RESOLUCION_3047;
        lista = lista.stream().filter(predicate -> (evaluarExistenciaAccion(predicate.getMaestroAcciones(), accion))).collect(Collectors.toList());
        return lista;
    }
    
    private void bloquearFacturasPorAuditoria(CmAuditoriaFacturaBean bean) {
        try {

            List<CmFacturaEstado> listaFacturasParaProcesar = obtenerListaFacturasParaProcesar(bean);
            bean.setCmRespuestaGenerica(new CmRespuestaGenerica());
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
                for (CmFacturaEstado facturasBloqueda : facturasBloquedasEncontradas) {
                    stringBuilder.append("La factura de número facturado : ");
                    stringBuilder.append(facturasBloqueda.getCmFactura().getNumeroFacturado());
                    stringBuilder.append(", esta siendo procesada por el usuario : ");
                    stringBuilder.append(facturasBloqueda.getUsuarioCrea());
                }
                bean.getCmRespuestaGenerica().setMensaje( stringBuilder.toString() );
            }           
        } catch (Exception e) {
            bean.addError("Ha ocurrido un error en bloquearFacturasPorAuditoria : "+e.getMessage());
        }
    }
    
    private List<CmFacturaEstado> filtrarFacturasPorRegistrar(List<CmFacturaEstado> listaFacturasParaProcesar, CmAuditoriaFacturaBean bean) {
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

    private void desbloquearFacturaAuditada(CmAuditoriaFacturaBean bean) {
        try {
            List<CmFacturaEstado> listFacturaEstados = obtenerListaFacturasParaProcesar(bean);
            getCmFacturaEstadoRemoto().borrarFacturasEstado(listFacturaEstados, CmFacturaEstado.TIPO_ESTADO_EN_PROCESO_AUDITORIA );
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarMarcadoGlosaIps(CmAuditoriaFacturaBean bean) {
        try {      
            getCmFacturaRemoto().actualizarMarcadoGlosaIPS( bean.getParamConsultaUtilitario());
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void guardarMarcadoCopagoNoEfectivo(CmAuditoriaFacturaBean bean) {
        try {
            String idsCmDetalles = "";
            boolean tipoMarcado = (boolean) bean.getParamConsultaUtilitario().getParametroConsulta1();
            int tipoGestion = (int) bean.getParamConsultaUtilitario().getParametroConsulta2();
    
            if (CmAuditoriaFacturaBean.GESTION_MASIVA_TOTAL == tipoGestion) {
                idsCmDetalles = obtenerIdCmDetallesContatenados(listarAuditoriaDetallesSinPaginar(bean));
            }
            if (CmAuditoriaFacturaBean.GESTION_MASIVA_PARCIAL == tipoGestion) {
                idsCmDetalles = obtenerIdCmDetallesContatenados(bean.getRegistrosDetallesSeleccionadoMasivos());
            }

            getCmDetalleRemoto().actualizarCopagoNoEfectivo(idsCmDetalles, tipoMarcado);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private String obtenerIdCmDetallesContatenados(List<CmDetalle> detalles) {
        String idsCmDetalles;
        idsCmDetalles = detalles.stream().map(mapper -> mapper.getId() + "").collect(Collectors.joining(","));
        return idsCmDetalles;
    }
    
    private List<CmFacturaEstado> obtenerListaFacturasParaProcesar(CmAuditoriaFacturaBean bean) {
        List<CmFacturaEstado> listFacturaEstados = new ArrayList<>();
        CmFacturaEstado facturaEstado = new CmFacturaEstado();
        facturaEstado.setCmFactura(new CmFactura(bean.getObjeto().getId()));
        facturaEstado.setEstadoFactura(CmFacturaEstado.TIPO_ESTADO_EN_PROCESO_AUDITORIA);
        bean.auditoriaGuardar(facturaEstado);
        listFacturaEstados.add(facturaEstado);
        return listFacturaEstados;
    }
    
    private void consultarProgramasActivosPorAfiliado(CmAuditoriaFacturaBean bean) {
        try {
            if (bean.getObjetoItemServicio().getId() != null) {
                AsegAfiliado afiliado = consultarAsegAfiliado(bean.getObjetoItemServicio().getMaeTipoDocumentoCodigo(),
                        bean.getObjetoItemServicio().getDocumento());
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
 
    private void asignarFiltroProgramaEspecialActivo(CmAuditoriaFacturaBean bean) {
        final String FILTRO = "activo";
        Map<String, Object> filtros = new HashMap<>();
        if (bean.getParamConsultaAfiliadoProgramas().getFiltros() == null) {
            bean.getParamConsultaAfiliadoProgramas().setFiltros(filtros);
        } else {
            filtros = bean.getParamConsultaAfiliadoProgramas().getFiltros();
        }
        filtros.put(FILTRO, "1");
    }
 
    private void consultarProgramaEspecificoPorAfiliado(CmAuditoriaFacturaBean bean) {
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

    private void asignarDiagnosticosPorAfiliado(CmAuditoriaFacturaBean bean) {
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

    private HashMap<Integer, Maestro> convertToHash(List<Maestro> list) {
        HashMap<Integer, Maestro> map = new HashMap<>();
        for (Maestro i : list) {
            map.put(i.getId(), i);
        }
        return map;
    }
    
    private void asignarFiltroBusquedaPrestadorFactura(CmAuditoriaFacturaBean bean) {
        final String FILTRO = "cntPrestadoresId.id";
        try {
            int idPrestador = Optional.ofNullable(bean.getObjeto().getCntPrestador().getId()).orElse(0);
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
    
    private void mostrarMensajeSiHayOperacionesMasivas(CmRadicado radicado, CmAuditoriaFacturaBean bean) {
        if (radicado.getCmAuditoriaMasivaN() != null) {
            bean.addError("La factura hace parte de un proceso de auditoría masiva, "
                    + " si desea reintentarla diríjase a la función 'Reintento envio SAP',"
                    + " cierre auditoria masiva id : " + radicado.getCmAuditoriaMasivaN().getId());
        }

        if (radicado.getCmRipsCarga() != null) {
            bean.addError("La factura hace parte de un proceso carga rips, "
                    + " si desea reintentarla diríjase a la función 'Reintento envio SAP',"
                    + " Radicado Rips : " + radicado.getCmRipsCarga().getId());
        }

        if (radicado.getCmDevolucionMasivaN() != null) {
            bean.addError("La factura hace parte de un proceso devolución masiva, "
                    + " si desea reintentarla diríjase a la función 'Reintento envio SAP',"
                    + " id devolución masiva : " + radicado.getCmDevolucionMasivaN().getId());
        }
    }
    
    private boolean copiarArchivosEnDisco(List<CmAuditoriaAdjunto> cmAuditoriaAdjuntos, StringBuilder errores) {
        boolean copiadoExitoso = true;
        errores = Optional.ofNullable(errores).orElse(new StringBuilder());
        for (CmAuditoriaAdjunto cmAuditoriaAdjunto : cmAuditoriaAdjuntos) {
            if (!cmAuditoriaAdjunto.isGuardadoEnDisco() &&  cmAuditoriaAdjunto.getId() == null ) {
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
    
    private void asignarDiasRadicacionSegunEstado(List<CmFactura> facturas){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateFechaActualString = sdf.format(new Date());
            Date fechaActualFormateada = sdf.parse(dateFechaActualString);
            HashMap<String, Integer> diasCache = new HashMap<>();
            Integer diaCache ;
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
        } catch (Exception e) {}
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
            String cobertura = Optional.ofNullable(medicamentoCache.getMaeCoberturaCodigo()).orElse("");
            cmDetalleMedicamento.setCondicionado(cobertura.equals(INDICE_COBERTURA_CODIGO_CONDICIONADO));
            cmDetalleMedicamento.setRegulado(Optional.ofNullable(medicamentoCache.getEsRegulado()).orElse(false));
        }
        
        medecamentosCache.clear();
    }
     
    private void consultarSucesosRips(CmAuditoriaFacturaBean bean) {
        try {
            CmRipsSuceso suceso       = new CmRipsSuceso() ;
            
            if (bean.isEsGestionIndividual() && !bean.getRegistrosAuditoriaAutorizacion().isEmpty()) {

                int tipoServicio = (int) bean.getParamConsultaConsultarSuceso().getParametroConsulta5();
                CmAuditoriaAutorizacion autorizacion =  bean.getRegistrosAuditoriaAutorizacion().get(0);   
                bean.getParamConsultaConsultarSuceso().setParametroConsulta6(autorizacion.getNumeroAutorizacion());
                
                switch (tipoServicio) {
                    case CmDetalle.TIPO_SERVICIO_CONSULTAS:
                        suceso = getCmRipsCargaRemoto().consultarSucesoRipsAC(bean.getParamConsultaConsultarSuceso());
                        break;
                    case CmDetalle.TIPO_SERVICIO_INSUMOS:
                        suceso = getCmRipsCargaRemoto().consultarSucesoRipsAT(bean.getParamConsultaConsultarSuceso());
                        break;
                    case CmDetalle.TIPO_SERVICIO_MEDICAMENTO:
                        suceso = getCmRipsCargaRemoto().consultarSucesoRipsAM(bean.getParamConsultaConsultarSuceso());
                        break;
                    case CmDetalle.TIPO_SERVICIO_PROCEDIMIENTO:
                        suceso = getCmRipsCargaRemoto().consultarSucesoRipsAP(bean.getParamConsultaConsultarSuceso());
                        break;
                }
                autorizacion.setSucesoRips(suceso.getDescripcionMensaje());
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
}
