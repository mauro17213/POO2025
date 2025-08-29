/*
 * To change this license header, choose License Headers in Project Proobjties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.servicio;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.GnSmsEnvio;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipo;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipoItem;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipoValor;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Entrega;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Historico;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Impresion;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Reporte;
import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacion;
import com.saviasaludeps.savia.dominio.autorizacion.AuCotizacionItem;
import com.saviasaludeps.savia.dominio.autorizacion.AuItemBitacora;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimiento;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimientoGestion;
import com.saviasaludeps.savia.dominio.autorizacion.AuSeguimientoPrestadorAsignado;
import com.saviasaludeps.savia.dominio.autorizacion.ReporteAnexo4;
import com.saviasaludeps.savia.dominio.configuracionSistema.CsCopagoModeradoraHistorico;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorContacto;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFacturaEnCmAuditoriaAutorizacion;
import com.saviasaludeps.savia.negocio.administracion.EmpresaRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.RolRemoto;
import com.saviasaludeps.savia.negocio.anticipo.AnticipoAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.anticipo.AnticipoItemRemoto;
import com.saviasaludeps.savia.negocio.anticipo.AnticipoRemoto;
import com.saviasaludeps.savia.negocio.anticipo.AnticipoValorRemoto;
import com.saviasaludeps.savia.negocio.aseguramiento.AfiliadoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo3ItemRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4EntregaRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4EstadoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4HistoricoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4ImpresionRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4ItemRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4Remoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuAnexo4ReporteRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuCotizacionItemRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuCotizacionRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuItemBitacoraRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuSeguimientoGestionRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuSeguimientoPrestadorAsignadoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuSeguimientoRemoto;
import com.saviasaludeps.savia.negocio.autorizacion.AuSolicitudAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.configuracionSistema.CsCopagoModeradoraHistoricoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorContactoRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorSedeRemoto;
import com.saviasaludeps.savia.negocio.crue.AuAnexo2Remoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmAuditoriaFacturaRemoto;
import com.saviasaludeps.savia.negocio.generico.ReporteSolicitudRemoto;
import com.saviasaludeps.savia.web.autorizacion.bean.AuAutorizacionBean;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.Mensaje;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @author Stiven Giraldo
 */
public class AuAutorizacionServicioImpl extends AccionesBO implements AuAutorizacionServicioIface {

    private final static int TOTAL = 1;
    private final static int PARCIAL = 2;
    private final static int SIN_ENTREGA = 3;
    private final static int ENTREGA_ANULADA = 4;
    private final static int NO_PRESTADO = 5;

    private AuAnexo4Remoto getAuAnexo4Remoto() throws Exception {
        return (AuAnexo4Remoto) RemotoEJB.getEJBRemoto("AuAnexo4Servicio", AuAnexo4Remoto.class.getName());
    }

    private CntPrestadorRemoto getPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private AuAnexo4ItemRemoto getAuAnexo4ItemRemoto() throws Exception {
        return (AuAnexo4ItemRemoto) RemotoEJB.getEJBRemoto("AuAnexo4ItemServicio", AuAnexo4ItemRemoto.class.getName());
    }

    private AuAnexo3ItemRemoto getAuAnexo3ItemRemoto() throws Exception {
        return (AuAnexo3ItemRemoto) RemotoEJB.getEJBRemoto("AuAnexo3ItemServicio", AuAnexo3ItemRemoto.class.getName());
    }

    private CntPrestadorSedeRemoto getCntPrestadorSedeRemoto() throws Exception {
        return (CntPrestadorSedeRemoto) RemotoEJB.getEJBRemoto("CntPrestadorSedeServicio", CntPrestadorSedeRemoto.class.getName());
    }

    private RolRemoto getRolRemoto() throws Exception {
        return (RolRemoto) RemotoEJB.getEJBRemoto("RolServicio", RolRemoto.class.getName());
    }

    private AfiliadoRemoto getAfiliadoRemoto() throws Exception {
        return (AfiliadoRemoto) RemotoEJB.getEJBRemoto("AfiliadoServicio", AfiliadoRemoto.class.getName());
    }

//    private UbicacionRemoto getUbicacionRemoto() throws Exception {
//        return (UbicacionRemoto) RemotoEJB.getEJBRemoto("UbicacionServicio", UbicacionRemoto.class.getName());
//    }
    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private AuAnexo4EntregaRemoto getAuAnexo4EntregaRemoto() throws Exception {
        return (AuAnexo4EntregaRemoto) RemotoEJB.getEJBRemoto("AuAnexo4EntregaServicio", AuAnexo4EntregaRemoto.class.getName());
    }

    private AuAnexo4HistoricoRemoto getAuAnexo4HistoricoRemoto() throws Exception {
        return (AuAnexo4HistoricoRemoto) RemotoEJB.getEJBRemoto("AuAnexo4HistoricoServicio", AuAnexo4HistoricoRemoto.class.getName());
    }

    private AuAnexo2Remoto getAuAnexo2Remoto() throws Exception {
        return (AuAnexo2Remoto) RemotoEJB.getEJBRemoto("AuAnexo2Servicio", AuAnexo2Remoto.class.getName());
    }

    private ReporteSolicitudRemoto getReporteSolicitudRemoto() throws Exception {
        return (ReporteSolicitudRemoto) RemotoEJB.getEJBRemotoGenerico("ReporteSolicitudServicio", ReporteSolicitudRemoto.class.getName());
    }

    private AuAnexo4ImpresionRemoto getAuAnexo4ImpresionRemoto() throws Exception {
        return (AuAnexo4ImpresionRemoto) RemotoEJB.getEJBRemoto("AuAnexo4ImpresionServicio", AuAnexo4ImpresionRemoto.class.getName());
    }

    private AuAnexo4ReporteRemoto getAuAnexo4ReporteRemoto() throws Exception {
        return (AuAnexo4ReporteRemoto) RemotoEJB.getEJBRemoto("AuAnexo4ReporteServicio", AuAnexo4ReporteRemoto.class.getName());
    }

    private CmAuditoriaFacturaRemoto getCmAuditoriaFacturaRemoto() throws Exception {
        return (CmAuditoriaFacturaRemoto) RemotoEJB.getEJBRemoto("CmAuditoriaFacturaServicio", CmAuditoriaFacturaRemoto.class.getName());
    }

    private CsCopagoModeradoraHistoricoRemoto getCsCopagoModeradoraHistoricoRemoto() throws Exception {
        return (CsCopagoModeradoraHistoricoRemoto) RemotoEJB.getEJBRemoto("CsCopagoModeradoraHistoricoServicio", CsCopagoModeradoraHistoricoRemoto.class.getName());
    }

    private AuItemBitacoraRemoto getAuItemBitacoraRemoto() throws Exception {
        return (AuItemBitacoraRemoto) RemotoEJB.getEJBRemoto("AuItemBitacoraServicio", AuItemBitacoraRemoto.class.getName());
    }

    private AuCotizacionItemRemoto getAuCotizacionItemRemoto() throws Exception {
        return (AuCotizacionItemRemoto) RemotoEJB.getEJBRemoto("AuCotizacionItemServicio", AuCotizacionItemRemoto.class.getName());
    }

    private AuSolicitudAdjuntoRemoto getAuSolicitudAdjuntoRemoto() throws Exception {
        return (AuSolicitudAdjuntoRemoto) RemotoEJB.getEJBRemoto("AuSolicitudAdjuntoServicio", AuSolicitudAdjuntoRemoto.class.getName());
    }

    private CntPrestadorContactoRemoto getCntPrestadorContactoRemoto() throws Exception {
        return (CntPrestadorContactoRemoto) RemotoEJB.getEJBRemoto("CntPrestadorContactoServicio", CntPrestadorContactoRemoto.class.getName());
    }

    private AuAnexo4EstadoRemoto getAuAnexo4EstadoRemoto() throws Exception {
        return (AuAnexo4EstadoRemoto) RemotoEJB.getEJBRemoto("AuAnexo4EstadoServicio", AuAnexo4EstadoRemoto.class.getName());
    }

    private AuSeguimientoRemoto getAuSeguimientoRemoto() throws Exception {
        return (AuSeguimientoRemoto) RemotoEJB.getEJBRemoto("AuSeguimientoServicio", AuSeguimientoRemoto.class.getName());
    }

    private AuSeguimientoGestionRemoto getAuSeguimientoGestionRemoto() throws Exception {
        return (AuSeguimientoGestionRemoto) RemotoEJB.getEJBRemoto("AuSeguimientoGestionServicio", AuSeguimientoGestionRemoto.class.getName());
    }

    private EmpresaRemoto getEmpresaRemoto() throws Exception {
        return (EmpresaRemoto) RemotoEJB.getEJBRemoto("EmpresaServicio", EmpresaRemoto.class.getName());
    }

    private AuSeguimientoPrestadorAsignadoRemoto getAuSeguimientoPrestadorAsignadoRemoto() throws Exception {
        return (AuSeguimientoPrestadorAsignadoRemoto) RemotoEJB.getEJBRemoto("AuSeguimientoPrestadorAsignadoServicio", AuSeguimientoPrestadorAsignadoRemoto.class.getName());
    }
    
    private AuCotizacionRemoto getAuCotizacionRemoto() throws Exception {
        return (AuCotizacionRemoto) RemotoEJB.getEJBRemoto("AuCotizacionServicio", AuCotizacionRemoto.class.getName());
    }
    
    private AnticipoRemoto getAnticipoRemoto() throws Exception {
        return (AnticipoRemoto) RemotoEJB.getEJBRemoto("AnticipoServicio", AnticipoRemoto.class.getName());
    }
    
    private AnticipoItemRemoto getAnticipoItemRemoto() throws Exception {
        return (AnticipoItemRemoto) RemotoEJB.getEJBRemoto("AnticipoItemServicio", AnticipoItemRemoto.class.getName());
    }
    
    private AnticipoAdjuntoRemoto getAnticipoAdjuntoRemoto() throws Exception {
        return (AnticipoAdjuntoRemoto) RemotoEJB.getEJBRemoto("AnticipoAdjuntoServicio", AnticipoAdjuntoRemoto.class.getName());
    }
    
    private AnticipoValorRemoto getAnticipoValorRemoto() throws Exception {
        return (AnticipoValorRemoto) RemotoEJB.getEJBRemoto("AnticipoValorServicio", AnticipoValorRemoto.class.getName());
    }
    
    private CntContratoRemoto getCntContratoRemoto() throws Exception {
        return (CntContratoRemoto) RemotoEJB.getEJBRemoto("CntContratoServicio", CntContratoRemoto.class.getName());
    }
    
    @Override
    public void Accion(AuAutorizacionBean bean) {
        if (super.ValidarSesion(bean)) {
            bean.getObjeto().setGnEmpresaId(bean.getConexion().getEmpresa());
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    ver(bean);
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
                    borrar(bean);
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            ver(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            anularAutorizacion(bean);
                            break;
                    }

                    break;
                case Url.ACCION_ADICIONAL_3:
                    generarReporte(bean);
                    break;
                case Url.ACCION_ADICIONAL_4:
                    incrementarImpresion(bean);
                    break;
                case Url.ACCION_ADICIONAL_5:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarEntregas(bean);
                            break;
                        case Url.ACCION_VER:
                            verEntrega(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            verGestionar(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_6:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarReportes(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            generarReporteExcelAnexos4(bean);
                            break;
                        case Url.ACCION_CREAR:
                            crearReporte(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            noEntregado(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_7:
                    break;    
                case Url.ACCION_ADICIONAL_8:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            break;
                        case Url.ACCION_GUARDAR:
                            entregar(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_9:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            verEntrega(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            anularEntrega(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_10:
                    autorizarPreautorizacion(bean);
                    break;
                case Url.ACCION_ADICIONAL_11://editar valor copago
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            verValorCopago(bean);
                            break;
                        case Url.ACCION_EDITAR:
                            editarValorCopago(bean);
                            break;
                    }
                    break;

            }
            cargas(bean);
        } else {
            System.err.println("Sesión inactiva");
        }
    }

    private void listar(AuAutorizacionBean bean) {
        try {
            bean.getParamConsulta(0).setParametroConsulta8(bean.getFechaInicio());
            bean.getParamConsulta(0).setParametroConsulta9(bean.getFechaFin());
            if (bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta(0).setEmpresaId(null);
            } else {
                bean.getParamConsulta(0).setEmpresaId(bean.getConexion().getEmpresa().getId());
            }
            //SI LLEVA TIPO ANEXO2 O TIPO ANEXO3
            for (Map.Entry e : bean.getParamConsulta(0).getFiltros().entrySet()) {
                if (e.getKey().equals(AuAnexo4.TIPO_ANEXO)) {
                    if (e.getValue().equals(AuAnexo4.ANEXO_2)) {
                        bean.getParamConsulta(0).setParametroConsulta3(null);
                        bean.getParamConsulta(0).setParametroConsulta2(AuAnexo4.ANEXO_2);
                        e.setValue(null);
                    } else if (e.getValue().equals(AuAnexo4.ANEXO_3)) {
                        bean.getParamConsulta(0).setParametroConsulta2(null);
                        bean.getParamConsulta(0).setParametroConsulta3(AuAnexo4.ANEXO_3);
                        e.setValue(null);
                    }
                }
            }
            //SI LLEVA NUMERO SOLICITUD
            for (Map.Entry e : bean.getParamConsulta(0).getFiltros().entrySet()) {
                if (e.getKey().equals(AuAnexo4.TIPO_SOLICITUD)) {
                    bean.getParamConsulta(0).setParametroConsulta4(e.getValue());
                    e.setValue(null);
                }
            }
            bean.getParamConsulta(0).setCantidadRegistros(getAuAnexo4Remoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setRegistros(getAuAnexo4Remoto().consultarLista(bean.getParamConsulta(0)));
            bean.getParamConsulta(0).setParametroConsulta2(null);
            bean.getParamConsulta(0).setParametroConsulta3(null);
            bean.getParamConsulta(0).setParametroConsulta4(null);
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void ver(AuAutorizacionBean bean) {
        try {
            bean.setObjeto(getAuAnexo4Remoto().consultar(bean.getObjeto().getId()));
            bean.setListaAuAnexo4Items(bean.getObjeto().getAuAnexo4ItemsList());
            consultarContactosPrestador(bean);
            if (bean.validarEsPreautorizacion(bean.getObjeto().getEstado())) {
                bean.setHeaderDialogVer("Ver Anexo 4 Preautorización de Servicios");
            } else {
                bean.setHeaderDialogVer("Ver Anexo 4 Autorización de Servicios");
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrar(AuAutorizacionBean bean) {
        try {
            bean.setObjeto(getAuAnexo4Remoto().eliminar(bean.getObjeto().getId()));
            bean.addMensaje("Se eliminó un registro de manera exitosa");
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void anularAutorizacion(AuAutorizacionBean bean) {
        try {//
            boolean anular = true;
            List<CmFacturaEnCmAuditoriaAutorizacion> lista = getCmAuditoriaFacturaRemoto().consultaFacturasPorAutorizacionEnAuditoria(bean.getObjeto().getId());
            if (!lista.isEmpty()) {
                anular = false;
                bean.addError("La autorización no se puede anular, debido a que ya tiene factura asociada");
            }
            AuAnexo4 anexoDB = getAuAnexo4Remoto().consultar(bean.getObjeto().getId());
            if (anexoDB.getEstado() != bean.getObjeto().getEstado()) {
                anular = false;
                bean.addError("La autorización no se puede anular, debido a que ya cambio de estado.");
            }
            if (anular) {
                Maestro maestroAnula;
                if (bean.getObjeto().getEstado() == AuAnexo4.ESTADO_PREAUTORIZADO) {
                    bean.getObjeto().setEstado(AuAnexo4.ESTADO_ANULADA_PREAUTORIZACION);
                    maestroAnula = getMaestroRemoto().consultarPorValorTipo(AuAnexo4Historico.VALOR_ANULADO_PREAUTORIZADO, AuAnexo4Historico.TIPO_ESTADO_MOTIVO_A3);
                //2023-09-29 jyperez se debe configurar para el estado ESTADO_ANULADO_PAGO_ANTICIPADO las mismas acciones que el ESTADO_ANULADA
                } if (bean.getObjeto().getEstado() == AuAnexo4.ESTADO_AUTORIZADO_PAGO_ANTICIPADO) {
                    bean.getObjeto().setEstado(AuAnexo4.ESTADO_ANULADO_PAGO_ANTICIPADO);
                    maestroAnula = getMaestroRemoto().consultarPorValorTipo(AuAnexo4Historico.VALOR_ANULADO, AuAnexo4Historico.TIPO_ESTADO_MOTIVO_A3);
                } else {
                    bean.getObjeto().setEstado(AuAnexo4.ESTADO_ANULADA);
                    maestroAnula = getMaestroRemoto().consultarPorValorTipo(AuAnexo4Historico.VALOR_ANULADO, AuAnexo4Historico.TIPO_ESTADO_MOTIVO_A3);
                }
                Maestro motivo = bean.obtenerMaestroMotivoA4(bean.getIdMotivo());
                if(motivo != null){
                    bean.getObjeto().setMaeEstadoMotivoId(motivo.getId());
                    bean.getObjeto().setMaeEstadoMotivoCodigo(motivo.getValor());
                    bean.getObjeto().setMaeEstadoMotivoValor(motivo.getNombre());
                }
                
                bean.getObjeto().setFuenteAnula(AuAnexo4.FUENTE_ANULADA_MANUAL);
                bean.auditoriaModificar(bean.getObjeto());
                getAuAnexo4Remoto().actualizarEstado(bean.getObjeto());
                for (AuAnexo4Item item : bean.getObjeto().getAuAnexo4ItemsList()) {
                    if(item.getCntContratoDetalle() != null){
                        if(item.getCntContratoDetalle().getCntContrato() != null){
                            item.getCntContratoDetalle().getCntContrato().setEjecucionContratoAutorizado((item.getCntContratoDetalle().getCntContrato().getEjecucionContratoAutorizado() == null) ? new BigDecimal(0) 
                            : item.getCntContratoDetalle().getCntContrato().getEjecucionContratoAutorizado().subtract(item.getCostoServicio()));
                            getCntContratoRemoto().actualizarEjecucionContratoAutorizado(item.getCntContratoDetalle().getCntContrato());
                        }
                    }
                    if (item.getAuAnexo3ItemId() != null) {
                        AuAnexo3Item itemAnexo3 = getAuAnexo3ItemRemoto().consultar(item.getAuAnexo3ItemId().getId());
                        if (itemAnexo3 != null) {
                            if (itemAnexo3.getEstado() == AuAnexo3Item.ESTADO_PREAUTORIZADO) {
                                itemAnexo3.setEstado(AuAnexo3Item.ESTADO_ANULADO_PREAUTORIZACION);
                            } else {
                                itemAnexo3.setEstado(AuAnexo3Item.ESTADO_ANULADO_AUTORIZACION);
                            }                            
                            itemAnexo3.setMaeEstadoMotivoItemId(motivo.getId());
                            itemAnexo3.setMaeEstadoMotivoItemCodigo(motivo.getValor());
                            itemAnexo3.setMaeEstadoMotivoItemValor(motivo.getNombre());
                            itemAnexo3.setEstadoJustificacion(bean.getObjeto().getEstadoJustificacion());
                            bean.auditoriaModificar(itemAnexo3);
                            getAuAnexo3ItemRemoto().actualizarEstado(itemAnexo3);
                            // funcionalidad para devolver anticipo
                            AuCotizacion cotizacion = getAuCotizacionRemoto().consultarPorIdItemAnexo3(itemAnexo3.getId());
                            if(cotizacion != null){
                                if(cotizacion.getAntAnticiposId() != null){
                                    AntAnticipo anticipo = getAnticipoRemoto().consultar(cotizacion.getAntAnticiposId().getId());
                                    AntAnticipoItem itemAnticipo = getAnticipoItemRemoto().consultar(cotizacion.getAntAnticipoItemsId().getId());
                                    AuAnexo3Item anexoItem3 = getAuAnexo3ItemRemoto().consultar(itemAnexo3.getId());
                                    BigDecimal valor = itemAnticipo.getValorTecnologiaCotizada().multiply(new BigDecimal(anexoItem3.getCantidadSolicitada()));
                                    anticipo.setValorDisponible(anticipo.getValorDisponible().add(valor));
                                    bean.auditoriaModificar(anticipo);
                                    getAnticipoRemoto().actualizarValorDisponible(anticipo);
                                    procesarAnticipoValor(bean, anticipo, valor, itemAnticipo, cotizacion, AntAnticipoValor.TIPO_ANULA_AUTORIZACION);
                                }
                            }
                            guardarItemBitacora(itemAnexo3, bean, AuItemBitacora.ID_CAMBIO_ESTADO, itemAnexo3.getEstadoStr().concat("-").concat(bean.getObjeto().getId().toString()));
                        }
                    }
                }

                if (bean.getObjeto().getAuAnexo2Id() != null && bean.getObjeto().getAuAnexo2Id().getId() != null) {
//                    Maestro estadoAnulado = getMaestroRemoto().consultarPorValorTipo("4", "89");                
                    bean.getObjeto().getAuAnexo2Id().setEstado(AuAnexo2.ESTADO_ANULADA);
                    bean.getObjeto().getAuAnexo2Id().setComentarioEstado(bean.getObjeto().getEstadoJustificacion());
                    bean.auditoriaModificar(bean.getObjeto().getAuAnexo2Id());
                    getAuAnexo2Remoto().actualizarEstado(bean.getObjeto().getAuAnexo2Id());
                }
                //Se actualiza el valor_proyectado en copago_moderadora_historico
                getCsCopagoModeradoraHistoricoRemoto().actualizarValorProyectado(bean.getObjeto());
                guardarHistorico(bean, motivo, bean.getObjeto().getEstadoJustificacion());
                //ya no se registra, ahora en historicos
//                AuAnexo4Estado anexo4Estado = new AuAnexo4Estado();
//                anexo4Estado.setAuAnexo4Id(bean.getObjeto());
//                anexo4Estado.setMaeEstadoId(maestroAnula.getId());
//                anexo4Estado.setMaeEstadoCodigo(maestroAnula.getValor());
//                anexo4Estado.setMaeEstadoValor(maestroAnula.getNombre());
//                anexo4Estado.setMaeMotivoEstadoId(motivo.getId());
//                anexo4Estado.setMaeMotivoEstadoCodigo(motivo.getValor());
//                anexo4Estado.setMaeMotivoEstadoValor(motivo.getNombre());
//                anexo4Estado.setObservacion(bean.getObjeto().getEstadoJustificacion());
//                bean.auditoriaGuardar(anexo4Estado);
//                getAuAnexo4EstadoRemoto().insertar(anexo4Estado);
                bean.addMensaje("Se anuló la autorización " + bean.getObjeto().getId() + " de manera exitosa. ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            bean.addError("Hubo un fallo anulando, por favor contactar al administrador");
        }
    }

    private void cargas(AuAutorizacionBean bean) {
        try {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    bean.setListaRoles(getRolRemoto().consultarTodos());
                    break;
                case Url.ACCION_VER:
                    break;
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    bean.setListaRoles(getRolRemoto().consultarTodos());
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void cargasInicial(AuAutorizacionBean bean) {
        try {
            bean.setHashTipoDocumento(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));

            bean.setListaEstadosItem(getMaestroRemoto().consultarPorTipo(MaestroTipo.ASEG_ESTADO_AFILIACION));

            bean.setListaMotivos(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_A3_ESTADO_MOTIVO));
            bean.setHashMotivos(AuConstantes.obtenerHashMaestro(bean.getListaMotivos()));

            bean.setListaMotivosA4(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_A4_ESTADO_MOTIVO));
            bean.setHashMotivosA4(AuConstantes.obtenerHashMaestro(bean.getListaMotivosA4()));

            bean.setListaCausaNoEntrega(getMaestroRemoto().consultarPorTipo(MaestroTipo.AU_ENTREGA_CAUSAS_NO_ENTREGA));
            bean.setHashCausaNoEntrega(AuConstantes.obtenerHashMaestro(bean.getListaCausaNoEntrega()));

            //bean.setRutaAnexo2(PropApl.getInstance().get(PropApl.REF_RUTA_ANEXOS));
            bean.setRutaAnexo4(PropApl.getInstance().get(PropApl.AU_A4_ANEXOS));
            //bean.setRutaAnexo2("C:\\Adjuntos\\");
            bean.setRutaAnexo3(PropApl.getInstance().get(PropApl.AU_A4_ANEXOS));
            //bean.setRutaAnexo3("C:\\Adjuntos\\");

            bean.setEstadoActivo(getMaestroRemoto().consultarPorValorTipo("101", MaestroTipo.ASEG_ESTADO_AFILIACION));

            bean.setListaCiudades(UbicacionSingle.getInstance().getListaMunicipios());
            bean.setHashUbicaciones(UbicacionSingle.getInstance().getHashUbicaciones());
            bean.setListaTipoDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_PERSONA));
        } catch (Exception e) {
            bean.addError("No fue posible cargar las listas de apoyo");
        }
    }

    @Override//por el momento no se usa
    public List<ReporteAnexo4> generarReporteAnexo4(int id, AuAutorizacionBean bean) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat horaFormat = new SimpleDateFormat("hh:mm");
        List<ReporteAnexo4> listaReportes = new ArrayList();
        try {
            ReporteAnexo4 reporte = new ReporteAnexo4();
            AuAnexo4 anexo4 = getAuAnexo4Remoto().consultar(id);
            anexo4.setAuAnexo4ItemsList(getAuAnexo4ItemRemoto().consultarListaByIdAnexo4(anexo4.getId()));
            CntPrestadorSede prestadorSede = getCntPrestadorSedeRemoto().consultar(anexo4.getCntPrestadorSedeId().getId());
//            Ubicacion ubicacionPrestador = getUbicacionRemoto().consultar(prestadorSede.getUbicacionId());
            Ubicacion ubicacionPrestador = UbicacionSingle.getInstance().getHashMunicipios().get(prestadorSede.getUbicacionId());
//            Ubicacion ubicacionPadre = getUbicacionRemoto().consultar(ubicacionPrestador.getUbicacionPadre().getId());
            Ubicacion ubicacionPadre = UbicacionSingle.getInstance().getHashMunicipios().get(ubicacionPrestador.getUbicacionPadre().getId());
            ubicacionPrestador.setUbicacionPadre(ubicacionPadre);
            prestadorSede.setUbicacion(ubicacionPrestador);
            anexo4.setCntPrestadorSedeId(prestadorSede);
            CntPrestador prestador = getPrestadorRemoto().consultar(prestadorSede.getCntPrestador().getId());
            anexo4.getCntPrestadorSedeId().setCntPrestador(prestador);
            AsegAfiliado afiliado = getAfiliadoRemoto().consultar(anexo4.getAsegAfiliadoId().getId());
//            Ubicacion ubicacionAfiliado = getUbicacionRemoto().consultar(afiliado.getResidenciaUbicacion().getId());
            Ubicacion ubicacionAfiliado = UbicacionSingle.getInstance().getHashMunicipios().get(afiliado.getResidenciaUbicacion().getId());
//            Ubicacion ubicacionAfiliadoPadre = getUbicacionRemoto().consultar(ubicacionAfiliado.getUbicacionPadre().getId());
            Ubicacion ubicacionAfiliadoPadre = UbicacionSingle.getInstance().getHashMunicipios().get(ubicacionAfiliado.getUbicacionPadre().getId());
            ubicacionAfiliado.setUbicacionPadre(ubicacionAfiliadoPadre);
            afiliado.setResidenciaUbicacion(ubicacionAfiliado);
            anexo4.setAsegAfiliadoId(afiliado);
            if (!anexo4.getAuAnexo4ItemsList().isEmpty()) {
                for (AuAnexo4Item item : anexo4.getAuAnexo4ItemsList()) {
                    //horaFormat.format(
                    reporte.setStrNumeroAutorizacion(anexo4.getId().toString());
                    reporte.setDateFechaAutorizacion(anexo4.getFechaAutorizacion());
                    reporte.setStrHoraAutorizacion(horaFormat.format(anexo4.getFechaAutorizacion()));
                    reporte.setStrEntidadResponsable(anexo4.getCntPrestadorSedeId().getNombreSede());
                    reporte.setStrCodigoEntidad(anexo4.getCntPrestadorSedeId().getCodigoPrestador());
                    reporte.setStrNombrePrestador(anexo4.getCntPrestadorSedeId().getCntPrestador().getRazonSocial());
                    reporte.setStrTipoDocPrestador(anexo4.getCntPrestadorSedeId().getCntPrestador().getMaeTipoDocumentoValor());
                    reporte.setStrNumDocPrestador(anexo4.getCntPrestadorSedeId().getCntPrestador().getNumeroDocumento());
                    reporte.setStrCodigoPrestador(anexo4.getCntPrestadorSedeId().getCntPrestador().getCodigoMinSalud());
                    reporte.setStrCorreoPrestador(anexo4.getCntPrestadorSedeId().getCorreoElectronico());
                    reporte.setStrTelefono1Prestador(anexo4.getCntPrestadorSedeId().getTelefonoCitas());
                    reporte.setStrTelefono2Prestador(anexo4.getCntPrestadorSedeId().getTelefonoAdministrativo());
                    reporte.setStrDireccionPrestador(anexo4.getCntPrestadorSedeId().getDireccion());
                    reporte.setStrDepartamentoPrestador(bean.obtenerDepartamento(anexo4.getCntPrestadorSedeId().getUbicacion().getId()));
                    reporte.setStrMunicipioPrestador(bean.obtenerMunicipio(anexo4.getCntPrestadorSedeId().getUbicacion().getId()));
                    reporte.setStrPrimerApellidoPaciente(anexo4.getAsegAfiliadoId().getPrimerApellido());
                    reporte.setStrSegundoApellidoPaciente(anexo4.getAsegAfiliadoId().getSegundoApellido());
                    reporte.setStrPrimerNombrePaciente(anexo4.getAsegAfiliadoId().getPrimerNombre());
                    reporte.setStrSegundoNombrePaciente(anexo4.getAsegAfiliadoId().getSegundoNombre());
                    reporte.setStrTipoDocPaciente(anexo4.getAsegAfiliadoId().getMaeTipoDocumentoValor());
                    reporte.setStrNumDocPaciente(anexo4.getAsegAfiliadoId().getNumeroDocumento());
                    reporte.setStrFechaNacimientoPaciente(dateFormat.format(anexo4.getAsegAfiliadoId().getFechaNacimiento()));
                    reporte.setStrHoraNacimientoPaciente(horaFormat.format(anexo4.getAsegAfiliadoId().getFechaNacimiento()));
                    reporte.setStrDireccionPaciente(anexo4.getAsegAfiliadoId().getDireccionResidencia());
                    reporte.setStrTelefonoFijoPaciente(anexo4.getAsegAfiliadoId().getTelefonoFijo());
                    reporte.setStrDepartementoPaciente(anexo4.getAsegAfiliadoId().getResidenciaUbicacion().getUbicacionPadre().getNombre() + "     " + anexo4.getAsegAfiliadoId().getResidenciaUbicacion().getUbicacionPadre().getPrefijo());
                    reporte.setStrMunicipioPaciente(anexo4.getAsegAfiliadoId().getResidenciaUbicacion().getNombre() + "     " + anexo4.getAsegAfiliadoId().getResidenciaUbicacion().getPrefijo());
                    reporte.setStrTelefonoCelularPaciente(anexo4.getAsegAfiliadoId().getTelefonoMovil());
                    reporte.setStrCorreoPaciente(anexo4.getAsegAfiliadoId().getEmail());
                    reporte.setStrUbicacionPaciente("");
                    reporte.setStrManejoIntegral(anexo4.getMaeGuiaManejoIntegralCodigo());
                    reporte.setStrCodigoCups(item.getMaTecnologiaCodigo());
                    reporte.setStrCantidad("" + item.getCantidadAutorizada());
                    reporte.setStrDescripcion(item.getMaTecnologiaValor());
                    reporte.setStrNumeroOrigen(anexo4.getNumeroPrescripcion());
                    reporte.setDateFechaOrigen(anexo4.getFechaHoraCrea());
                    reporte.setStrHoraOrigen(horaFormat.format(anexo4.getFechaHoraCrea()));
                    reporte.setStrPorcentajeAutorizado(""); //Falta preguntar
                    reporte.setStrSemanaPaciente("" + anexo4.getSemanasAfiliacion()); //Falta preguntar
                    reporte.setStrValor(anexo4.getValorCopago() != null ? anexo4.getValorCopago().toString() : "");
                    reporte.setStrPorcentaje(""); //Falta preguntar
                    if(Double.parseDouble(reporte.getStrValor()) != 0.0){
                        reporte.setStrAplicaCobro("SI");
                    }else{
                        reporte.setStrAplicaCobro("NO");
                    }
                    //reporte.setStrAplicaCobro(anexo4.getExcentoCopago() != null ? anexo4.getExcentoCopago() ? "NO" : "SI" : "");
                    //reporte.setStrTipoCuota(""); //Falta preguntar
                    reporte.setStrNombreAutoriza(""); //Falta preguntar
                    reporte.setStrCargoAutoriza(""); //Falta preguntar
                    reporte.setStrDias("" + anexo4.getDiasVigencia());
                    reporte.setStrCama(""); //Falta preguntar
                    reporte.setStrServicio(anexo4.getMaServicioHabilitadoCodigo());
                    reporte.setStrCuotaModeradora("NO");
                    reporte.setStrCopago(anexo4.getAplicaCopago() != null ? anexo4.getAplicaCopago() ? "SI" : "NO" : "");
                    reporte.setStrCuotaRecuperacion(anexo4.getAplicaCuotaRecuperacion() != null ? anexo4.getAplicaCuotaRecuperacion() ? "SI" : "NO" : "");
                    reporte.setStrExcentoCuota(anexo4.getExcentoCopago() != null ? anexo4.getExcentoCopago() ? "SI" : "NO" : "");
                    listaReportes.add(reporte);
                }
            }
        } catch (Exception e) {
            listaReportes = new ArrayList();
        }
        return listaReportes;
    }

    private void generarReporte(AuAutorizacionBean bean) {
        try {
            bean.setObjeto(getAuAnexo4Remoto().consultar(bean.getObjeto().getId()));
            /*String nombre = bean.getObjeto().getArchivo().substring(8, bean.getObjeto().getId().toString().length()+8);
            if(!nombre.equals(bean.getObjeto().getId().toString())){
                bean.getObjeto().setArchivo(null);
                bean.getObjeto().setRuta(null);
            }*/
            bean.getObjeto().setAuAnexo4ItemsList(getAuAnexo4ItemRemoto().consultarListaByIdAnexo4(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("No se encontro la autorización");
        }
    }

    @Override
    public void contarImpresion(AuAutorizacionBean bean) {
        try {
            AuAnexo4Impresion historico = new AuAnexo4Impresion();
            int cantidadActual = 1;
            if (bean.getObjeto().getImpresionesRealizadas() != null) {
                cantidadActual = bean.getObjeto().getImpresionesRealizadas();
                if (cantidadActual == 0) {
                    historico.setTipoImpresion(AuAnexo4Impresion.TIPO_ORIGINAL);
                } else {
                    historico.setTipoImpresion(AuAnexo4Impresion.TIPO_COPIA);
                }
                cantidadActual++;
            } else {
                historico.setTipoImpresion(AuAnexo4Impresion.TIPO_ORIGINAL);
            }
            historico.setOrigenImpresion(AuAnexo4Impresion.ORIGEN_APLICACION);
            historico.setAuAnexo4Id(bean.getObjeto());
            bean.auditoriaGuardar(historico);
            getAuAnexo4ImpresionRemoto().insertar(historico);
            bean.getObjeto().setImpresionesRealizadas(cantidadActual);
            getAuAnexo4Remoto().actualizarImpresion(bean.getObjeto());
        } catch (Exception e) {
            bean.addError("No se pudo actualizar la cantidad de impresiones");
        }
    }

    private void incrementarImpresion(AuAutorizacionBean bean) {
        try {
            ver(bean);
            bean.getObjeto().setImpresionesAutorizadas(bean.getObjeto().getImpresionesAutorizadas() + 1);
            getAuAnexo4Remoto().actualizarImpresion(bean.getObjeto());
            AuAnexo4Impresion historico = new AuAnexo4Impresion();
            historico.setAuAnexo4Id(bean.getObjeto());
            historico.setTipoImpresion(2);
            historico.setOrigenImpresion(0);
            bean.auditoriaGuardar(historico);
            getAuAnexo4ImpresionRemoto().insertar(historico);
            bean.addMensaje("Se realizó el incremento de manera exitosa");
        } catch (Exception e) {
            bean.addError("Hubo un error incrementando la impresión por favor comunicarse con el adminitrador");
        }
    }

    private void entregar(AuAutorizacionBean bean) {
        try {
            if (bean.getObjetoEntrega().isReclamaAfiliado()) {
                AsegAfiliado afiliado = bean.getObjeto().getAsegAfiliadoId();
                bean.getObjetoEntrega().setNombreReclama(afiliado.getNombreCompleto());
                bean.getObjetoEntrega().setTelefonoReclama(bean.getObjeto().getAfiliadoTelefono());
                bean.getObjetoEntrega().setCelularReclama(bean.getObjeto().getAfiliadoCelular());
            } else {
                if (bean.getObjetoEntrega().getAuAnexo4ItemId().getTipoTecnologia() == 1 || bean.getObjetoEntrega().getAuAnexo4ItemId().getTipoTecnologia() == 4) {
                    AsegAfiliado afiliado = bean.getObjeto().getAsegAfiliadoId();
                    bean.getObjetoEntrega().setNombreReclama(afiliado.getNombreCompleto());
                }
            }
            if (bean.getObjetoEntrega().getCantidadPendiente() == 0) {
                bean.getObjetoEntrega().setTipoEntrega(TOTAL); //Total
            } else {
                if (bean.getObjetoEntrega().getCantidadEntregada() == 0) {
                    bean.getObjetoEntrega().setTipoEntrega(SIN_ENTREGA); //Sin entrega
                } else {
                    bean.getObjetoEntrega().setTipoEntrega(PARCIAL); //Parcial
                }
            }
            SimpleDateFormat formatoBasico = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaFin = formatoBasico.parse(formatoBasico.format(bean.getObjeto().getFechaFin()));
            Date fechaInicio = formatoBasico.parse(formatoBasico.format(bean.getObjeto().getFechaInicio()));
            Date fechaEntrega = formatoBasico.parse(formatoBasico.format(bean.getObjetoEntrega().getFechaHoraEntrega()));

            if (fechaEntrega.after(fechaFin)) {
                bean.addError("La fecha de prestación es superior a la vigencia de la autorización");
            } else if (fechaEntrega.before(fechaInicio)) {
                bean.addError("La fecha de prestación es inferior a la vigencia de la autorización");
            } else {
                bean.auditoriaGuardar(bean.getObjetoEntrega());
                bean.getObjetoEntrega().setFuenteOrigen(AuAnexo4Entrega.ORIGEN_MANUAL);
                getAuAnexo4EntregaRemoto().insertar(bean.getObjetoEntrega());
                bean.addMensaje("Se realizó la entrega con éxito");
                bean.addMensaje("La autorización no se puede anular");
                bean.setListaAuAnexo4Items(getAuAnexo4ItemRemoto().consultarListaByIdAnexo4(bean.getObjeto().getId()));
                for (AuAnexo4Item item : bean.getListaAuAnexo4Items()) {
                    item.setAuAnexo4EntregasList(getAuAnexo4EntregaRemoto().consultarPorIdItemAnexo4(item.getId()));
                }
                verGestionar(bean);
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo en la entrega, favor contactar al administrador");
        }
    }
    
    private void noEntregado(AuAutorizacionBean bean) {
        try {
            bean.getObjetoEntrega().setReclamaAfiliado(false);
            bean.getObjetoEntrega().setNombreReclama(" ");
            Date fechaActual = new Date();
            bean.getObjetoEntrega().setFechaHoraEntrega(fechaActual);
            bean.getObjetoEntrega().setTipoEntrega(NO_PRESTADO);
            bean.getObjetoEntrega().setFuenteOrigen(AuAnexo4Entrega.ORIGEN_MANUAL);
            bean.auditoriaGuardar(bean.getObjetoEntrega());
            getAuAnexo4EntregaRemoto().insertar(bean.getObjetoEntrega());
            getAuAnexo4ItemRemoto().actualizar(bean.getObjetoItem());
            //bean.getObjetoItem().getAuAnexo3ItemId().setEstado(AuAnexo3Item.ESTADO_NO_PRESTADO);
            //getAuAnexo3ItemRemoto().actualizarEstado(bean.getObjetoItem().getAuAnexo3ItemId());
            if(bean.getObjetoItem().getAuAnexo3ItemId() != null){
                AuItemBitacora bitacora = new AuItemBitacora();
                bitacora.setTipo(AuItemBitacora.ID_CONTROL_REGISTROS_ENTREGAS);
                bitacora.setDescripcion(AuAnexo3Item.ESTADO_NO_PRESTADO_STR);
                bitacora.setAuAnexo3ItemId(bean.getObjetoItem().getAuAnexo3ItemId());
                bitacora.setEstado(bean.getObjetoItem().getAuAnexo3ItemId().getEstado());
                bean.auditoriaGuardar(bitacora);
                getAuItemBitacoraRemoto().insertar(bitacora);   
                // funcionalidad para devolver anticipo
                AuCotizacion cotizacion = getAuCotizacionRemoto().consultarPorIdItemAnexo3(bean.getObjetoItem().getAuAnexo3ItemId().getId());
                if(cotizacion != null){
                    if(cotizacion.getAntAnticiposId() != null){
                        AntAnticipo anticipo = getAnticipoRemoto().consultar(cotizacion.getAntAnticiposId().getId());
                        AntAnticipoItem anticipoItem = getAnticipoItemRemoto().consultar(cotizacion.getAntAnticipoItemsId().getId());
                        AuAnexo3Item anexoItem3 = getAuAnexo3ItemRemoto().consultar(bean.getObjetoItem().getAuAnexo3ItemId().getId());
                        BigDecimal valor = anticipoItem.getValorTecnologiaCotizada().multiply(new BigDecimal(anexoItem3.getCantidadSolicitada()));
                        anticipo.setValorDisponible(anticipo.getValorDisponible().add(valor));
                        bean.auditoriaModificar(anticipo);
                        getAnticipoRemoto().actualizarValorDisponible(anticipo);
                        procesarAnticipoValor(bean, anticipo, valor, anticipoItem, cotizacion, AntAnticipoValor.TIPO_NO_PRESTADO);
                    }
                }
            }
            for (AuAnexo4Item item : bean.getListaAuAnexo4Items()) {
                item.setAuAnexo4EntregasList(getAuAnexo4EntregaRemoto().consultarPorIdItemAnexo4(item.getId()));
            }
            verGestionar(bean);
            bean.addMensaje("Se realizó la modificacion a No Prestado con éxito");
        } catch (Exception e) {
            bean.addError("Hubo un fallo al marcar como No Entregado, favor contactar al administrador");
        }
    }
    
    public void procesarAnticipoValor(AuAutorizacionBean bean, AntAnticipo anticipo, BigDecimal valor, AntAnticipoItem item, AuCotizacion cotizacion, Integer tipoDevolucion){
        try{
            AntAnticipoValor antAnticipoValor = new AntAnticipoValor();
            bean.auditoriaGuardar(antAnticipoValor);
            antAnticipoValor.setAntAnticiposId(anticipo);
            antAnticipoValor.setAntAnticipoItemsId(item);
            antAnticipoValor.setAuCotizacionesId(cotizacion);
            antAnticipoValor.setDevolucion(Boolean.TRUE);
            antAnticipoValor.setTipoDevolucion(tipoDevolucion);
            antAnticipoValor.setValor(valor);
            antAnticipoValor.setId(getAnticipoValorRemoto().insertar(antAnticipoValor));
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }
    
    private void listarEntregas(AuAutorizacionBean bean) {
        try {
            bean.getObjetoItem().setAuAnexo4EntregasList(getAuAnexo4EntregaRemoto().consultarPorIdItemAnexo4(bean.getObjetoItem().getId()));
            bean.getObjetoItem().setAnularEntrega(true);
            for (AuAnexo4Entrega entrega : bean.getObjetoItem().getAuAnexo4EntregasList()) {
                if (entrega.isAnulada()) {
                    bean.getObjetoItem().setAnularEntrega(false);
                }
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo buscando las entregas, por favor contactar con el administrador");
        }
    }

    private void verEntrega(AuAutorizacionBean bean) {
        try {
            bean.setObjetoEntrega(getAuAnexo4EntregaRemoto().consultar(bean.getObjetoEntrega().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un error buscando la entrega, por favor contactar con el administrador");
        }
    }

    private void verGestionar(AuAutorizacionBean bean) {
        try {
            bean.setObjeto(getAuAnexo4Remoto().consultar(bean.getObjeto().getId()));
//            bean.setListaAuAnexo4Items(getAuAnexo4ItemRemoto().consultarListaByIdAnexo4(bean.getObjeto().getId()));
            bean.setListaAuAnexo4Items(bean.getObjeto().getAuAnexo4ItemsList());
            
            for (AuAnexo4Item item : bean.getListaAuAnexo4Items()) {
                AuAnexo4Entrega anexo4Entrega = new AuAnexo4Entrega();
                item.setAuAnexo4EntregasList(getAuAnexo4EntregaRemoto().consultarPorIdItemAnexo4(item.getId()));
                if (item.getAuAnexo4EntregasList().size() > 0){
                    anexo4Entrega = item.getAuAnexo4EntregasList().get(0);
                }
                item.setAuAnexo4EntregasList(getAuAnexo4EntregaRemoto().consultarPorIdItemAnexo4(item.getId()));
                int contador = 0;
                for (AuAnexo4Entrega entrega : item.getAuAnexo4EntregasList()) {
                    if (!entrega.isAnulada()) {
                        contador += entrega.getCantidadEntregada();
                    }
                }
                item.setCantidadPendiente(item.getCantidadAutorizada() - contador);
                item.setEntregada(item.getCantidadPendiente() <= 0);
                if (item.isEntregada()) {
                    item.setEstadoEntrega("Total");
                } else {
                    if (item.getCantidadAutorizada() == item.getCantidadPendiente()) {
                        switch (anexo4Entrega.getTipoEntrega()) {
                            case 0:
                                item.setEstadoEntrega("Sin Entrega");
                                break;
                            case 4:
                                item.setEstadoEntrega("Sin Entrega");
                                break;
                            case 5:
                                item.setEstadoEntrega("No Prestado");
                                break;
                            case 3:
                                item.setEstadoEntrega("Sin Entrega");
                                break;
                            default:
                                break;
                        }
                    } else {
                        item.setEstadoEntrega("Parcial");
                    }
                }
                
            }
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void listarReportes(AuAutorizacionBean bean) {
        try {
            bean.setListaReportes(getAuAnexo4ReporteRemoto().listarPorUsuario(bean.getConexion().getUsuario().getId()));
            bean.setPrestadorReporteExcel(bean.getConexion().getEmpresa().getRazonSocial());
        } catch (Exception e) {
            bean.addError("Hubo un fallo consultando los reportes, favor comunicarse con el administrador");
            bean.crearLog("Ver reportes Anexo 4", e.toString());
        }
    }

    private void generarReporteExcelAnexos4(AuAutorizacionBean bean) {
        try {
            bean.auditoriaGuardar(bean.getObjetoReporte());
            bean.getObjetoReporte().setId(getAuAnexo4ReporteRemoto().insertar(bean.getObjetoReporte()));
            SimpleDateFormat formato = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            Date fechaActual = new Date();
            String nombre = formato.format(fechaActual);
            bean.getObjetoReporte().setArchivo("Reporte_Autorizaciones_" + nombre);
            if (!bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getObjetoReporte().setPrestador(bean.getConexion().getEmpresa().getCntPrestador().getId());
            }
            getAuAnexo4ReporteRemoto().actualizar(bean.getObjetoReporte());
            getReporteSolicitudRemoto().exportarExcel(bean.getObjetoReporte());
            bean.setListaReportes(getAuAnexo4ReporteRemoto().listarPorUsuario(bean.getConexion().getUsuario().getId()));
            bean.addMensaje("El reporte esta en proceso favor revisar nuevamente el estado en unos minutos");
        } catch (Exception e) {
            bean.addError("Hubo un fallo generando el reporte, favor comunicarse con el adminsitrador");
            bean.crearLog("Generar Reporte Anexos 4", e.toString());
        }
    }

    private void crearReporte(AuAutorizacionBean bean) {
        try {
            bean.setObjetoReporte(new AuAnexo4Reporte());
            bean.getObjetoReporte().setCantidadProcesada(0);
            bean.getObjetoReporte().setCantidadTotal(0);
            bean.getObjetoReporte().setEstado(AuAnexo4Reporte.ESTADO_EN_PROCESO);
            bean.getObjetoReporte().setArchivo("Reporte");
            bean.getObjetoReporte().setUsuarioCreaId(bean.getConexion().getUsuario().getId());
            bean.getObjetoReporte().setRuta(PropApl.getInstance().get(PropApl.AU_A4_REPORTES));
        } catch (Exception e) {
            bean.addError("Hubo un fallo cargando el reporte, favor comunicarse con el administrador");
        }
    }

    @Override
    public void listarIps(AuAutorizacionBean bean) {
        try {
            if (!bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta(1).setParametroConsulta5(bean.getConexion().getEmpresa().getCntPrestador().getId());
            }
            bean.getParamConsulta(1).getFiltros().put("cntContratosId.activo", true);
            bean.getParamConsulta(1).getFiltros().put("fecha", Util.fechaDateAString(new Date()));
            bean.getParamConsulta(1).setCantidadRegistros(getPrestadorRemoto().consultarCantidadListaSede(bean.getParamConsulta(1)));
            bean.setListaIps(getPrestadorRemoto().consultarListaSede(bean.getParamConsulta(1)));
            bean.getListaIps().forEach((ips) -> {
                try {
                    ips.setCntPrestador(getPrestadorRemoto().consultar(ips.getCntPrestador().getId()));
                } catch (Exception e) {
                    bean.addError(e.getMessage());
                }
            });
        } catch (Exception e) {
            bean.addError(e.getMessage());
        }
    }

    private void borrarEntrega(AuAutorizacionBean bean) {
        try {
            getAuAnexo4EntregaRemoto().eliminar(bean.getObjetoEntrega().getId());
            bean.getObjetoItem().setAuAnexo4EntregasList(getAuAnexo4EntregaRemoto().consultarPorIdItemAnexo4(bean.getObjetoItem().getId()));
            verGestionar(bean);
            bean.addMensaje("Se borro la entrega de manera satisfactoria");
        } catch (Exception e) {
            bean.addError("Se genero un error borrando la entrega");
        }
    }

    private void anularEntrega(AuAutorizacionBean bean) {
        try {
            bean.getObjetoEntrega().setAnulada(true);
            bean.getObjetoEntrega().setTipoEntrega(AuAnexo4Entrega.TIPO_ANULADA);
            bean.getObjetoEntrega().setCantidadEntregada(0);
            bean.getObjetoEntrega().setCantidadPendiente(bean.getObjetoEntrega().getCantidadAutorizada());
            bean.auditoriaModificar(bean.getObjetoEntrega());
            getAuAnexo4EntregaRemoto().actualizar(bean.getObjetoEntrega());
            bean.getObjetoItem().setAuAnexo4EntregasList(getAuAnexo4EntregaRemoto().consultarPorIdItemAnexo4(bean.getObjetoItem().getId()));
            verGestionar(bean);
            bean.getObjetoItem().setAnularEntrega(false);
            bean.addMensaje("Se anuló la entrega con éxito");
        } catch (Exception e) {
            bean.addError("Hubo un fallo anulando la entrega, favor contactar al administrador");
        }
    }

    private void autorizarPreautorizacion(AuAutorizacionBean bean) {
        try {
            bean.setObjeto(getAuAnexo4Remoto().consultar(bean.getObjeto().getId()));
            if (bean.getObjeto().getEstado() == AuAnexo4.ESTADO_PREAUTORIZADO) {
                bean.getObjeto().setEstado(AuAnexo4.ESTADO_AUTORIZADA_PREAUTORIZACION);
                //reset info para guardar nuevo pdf
                bean.getObjeto().setFechaAutorizacion(new Date());
                bean.getObjeto().setImpresionesAutorizadas(2);
                bean.getObjeto().setImpresionesRealizadas(0);
                bean.getObjeto().setRuta(null);
                bean.getObjeto().setArchivo(null);
                bean.getObjeto().setArchivoNombre(null);
                bean.auditoriaModificar(bean.getObjeto());
                int actualizado = getAuAnexo4Remoto().autorizarPreautorizacion(bean.getObjeto());
                if (actualizado > 0) {
                    for (AuAnexo4Item item : bean.getObjeto().getAuAnexo4ItemsList()) {
                        //enviar mensaje SMS
                        if(bean.getObjeto().getAsegAfiliadoId().getAutorizaEnvioSms() != null){
                            if (bean.getObjeto().getAsegAfiliadoId().getAutorizaEnvioSms()
                                && bean.getObjeto().getAfiliadoCelular() != null) {
                                Maestro maeAmbito = getMaestroRemoto().consultar(bean.getObjeto().getMaeAmbitoAtencionId());
                                if (maeAmbito.contieneAccion(MaestroAccion.GN_AMBITO_APLICA_MENSAJE)) {
                                    item.setAuAnexo4Id(bean.getObjeto());
                                    String sms = AuConstantes.mensajeAutorizacionSMS(item);
                                    Mensaje mensaje = new Mensaje();
                                    mensaje.guardar(GnSmsEnvio.ORIGEN_AUTORIZACIONES, bean.getObjeto().getAfiliadoCelular(), sms);
                                }
                            }
                        }
                        
                        if (item.getAuAnexo3ItemId() != null) {
                            AuAnexo3Item itemAnexo3 = getAuAnexo3ItemRemoto().consultar(item.getAuAnexo3ItemId().getId());
                            if (itemAnexo3 != null) {
                                itemAnexo3.setEstado(AuAnexo3Item.ESTADO_AUTORIZADA_PREAUTORIZACION);
                                bean.auditoriaModificar(itemAnexo3);
                                getAuAnexo3ItemRemoto().actualizarEstado(itemAnexo3);

                                guardarItemBitacora(itemAnexo3, bean, AuItemBitacora.ID_CAMBIO_ESTADO, itemAnexo3.getEstadoStr());
                            }
                            //autoriza seguimiento
                            AuSeguimiento seguimiento = getAuSeguimientoRemoto().seguimientoPorAnexo3Item(itemAnexo3.getId());
                            if (seguimiento != null) {
                                Maestro maestroEstado = getMaestroRemoto().consultarPorValorTipo(AuSeguimiento.ESTADO_AUTORIZADO, MaestroTipo.AU_SEGUIMIENTO_ESTADO);
                                seguimiento.setMaeEstadoSeguimientoId(maestroEstado.getId());
                                seguimiento.setMaeEstadoSeguimientoCodigo(maestroEstado.getValor());
                                seguimiento.setMaeEstadoSeguimientoValor(maestroEstado.getNombre());
                                seguimiento.setEstadoTecnologia(itemAnexo3.getEstado());
                                seguimiento.setAuAnexos4Id(bean.getObjeto());
                                bean.auditoriaModificar(seguimiento);
                                //el usuario tiene la posibilidad de cambiar el prestador al seleccionar contrato
                                if (bean.getObjeto().getCntPrestadorSedeId() != null && seguimiento.getCntPrestadorSedeAsignadoId() != null) {
                                    if (!Objects.equals(bean.getObjeto().getCntPrestadorSedeId().getId(), seguimiento.getCntPrestadorSedeAsignadoId().getId())) {
                                        seguimiento.setCntPrestadorSedeAsignadoId(bean.getObjeto().getCntPrestadorSedeId());
                                        Empresa empresa = getEmpresaRemoto().consultarPorPrestador(
                                                seguimiento.getCntPrestadorSedeAsignadoId().getCntPrestador().getId()
                                        );
                                        seguimiento.setGnEmpresasId(empresa);
                                        guardarPrestadorSedeSeguimiento(bean, seguimiento);
                                        guardarGestionSeguimiento(bean, seguimiento, AuSeguimientoGestion.ESTADO_GESTION_NOTA, "Reasignación automatica de prestador aprobado " + seguimiento.getCntPrestadorSedeAsignadoId().getNombreSede());
                                    }
                                }
                                getAuSeguimientoRemoto().actualizarEstado(seguimiento);
                                guardarGestionSeguimiento(bean, seguimiento, AuSeguimientoGestion.ESTADO_AUTORIZADO, "Autorizado Preautorización");
                            }
                        }
                    }
                    Maestro maestro = getMaestroRemoto().consultarPorValorTipo(AuAnexo4Historico.VALOR_AUTORIZADO_PREAUTORIZADO, AuAnexo4Historico.TIPO_ESTADO_MOTIVO_A3);
                    guardarHistorico(bean, maestro, bean.getObjeto().getEstadoStr());
                    bean.addMensaje("Autorizada Preautorización	de manera exitosa");
                }
            } else {
                bean.addError("La Preautorización ya fue gestionada y se encuentra en estado ".concat(bean.getObjeto().getEstadoStr()));
            }
        } catch (Exception ex) {
            bean.addError("Hubo un fallo aprobando la preautorizacion, favor contactar al administrador");
        }
    }

    private void verValorCopago(AuAutorizacionBean bean) {
        ver(bean);
        try {
            //total copago
            LocalDate hoy = LocalDate.now();
            bean.getParamConsulta(2).setParametroConsulta1(hoy.getYear());
            bean.getParamConsulta(2).setParametroConsulta2(bean.getObjeto().getAsegAfiliadoId().getId());

            bean.getParamConsulta(2).setParametroConsulta3(CsCopagoModeradoraHistorico.COPAGO);

            bean.setValorTotalHistorico(
                    getCsCopagoModeradoraHistoricoRemoto().consultarTotalCopagoModeradora(bean.getParamConsulta(2))
            );

        } catch (Exception ex) {
            bean.addError("Error al consultar valor copago, refresque la página.");
        }
    }

    private void editarValorCopago(AuAutorizacionBean bean) {
        try {
            AuAnexo4 anexoDB = getAuAnexo4Remoto().consultar(bean.getObjeto().getId());
            if (bean.validarCopago(anexoDB.getEstado()) && anexoDB.getImpresionesRealizadas() == 0 && anexoDB.getAplicaCopago()) {
                //reset para volver a generar pdf
                bean.getObjeto().setArchivo(null);
                bean.getObjeto().setArchivoNombre(null);
                bean.getObjeto().setRuta(null);
                getAuAnexo4Remoto().actualizarValorCopago(bean.getObjeto());
                getCsCopagoModeradoraHistoricoRemoto().actualizarValorCopago(bean.getObjeto());
                //guardar bitacora
                String mensaje = "Valor anterior: " + anexoDB.getValorCopago() + " cambiado por: " + bean.getObjeto().getValorCopago();
                mensaje = bean.getObservacionGenerica().concat(" | ").concat(mensaje);
                Maestro maestro = getMaestroRemoto().consultarPorValorTipo(AuAnexo4Historico.VALOR_EDICION_COPAGO, AuAnexo4Historico.TIPO_ESTADO_MOTIVO_A4);
                guardarHistorico(bean, maestro, mensaje);
                bean.setObservacionGenerica(null);
                bean.addMensaje("Modificacion de valor copago exitosa");
            } else {
                bean.addError("El anexo no se puede actualizar debido a que fue modificado, refresque la página.");
            }

        } catch (Exception ex) {
            bean.addError("Hubo un fallo actualizando el valor copago, favor contactar al administrador");
        }
    }

    @Override
    public boolean validarFacturaAsociada(AuAutorizacionBean bean) {
        boolean factura = true;
        try {
            List<CmFacturaEnCmAuditoriaAutorizacion> lista = getCmAuditoriaFacturaRemoto().consultaFacturasPorAutorizacionEnAuditoria(bean.getObjeto().getId());
            if (lista.isEmpty()) {
                factura = false;
            }
        } catch (Exception e) {
            factura = true;
        }
        if (factura) {
            bean.addError("La autorización no se puede anular, debido a que ya tiene factura asociada");
        }
        return factura;
    }

    @Override
    public boolean validarEstadoAfiliado(int maeEstadoAfiliacion) {
        try {
            Maestro maeEstado = getMaestroRemoto().consultar(maeEstadoAfiliacion);
            return maeEstado.getNombre().equalsIgnoreCase("Activo");
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public void verHistoricosAnexo4(AuAutorizacionBean bean) {
        try {
            bean.getObjeto().setAuAnexo4HistoricosList(
                    getAuAnexo4HistoricoRemoto().historicosPorAnexo4(bean.getObjeto().getId())
            );
        } catch (Exception ex) {
            bean.addError("Hubo un fallo consultando los historicos");
        }
    }

    @Override
    public AuAnexo4 consultarAnexo4(int id) {
        AuAnexo4 respuesta = null;
        try {
            respuesta = getAuAnexo4Remoto().consultar(id);
        } catch (Exception ex) {
            respuesta = null;
        }
        return respuesta;
    }

    @Override
    public void verBitacoras(AuAutorizacionBean bean) {
        try {
            if (bean.getObjetoItem().getAuAnexo3ItemId() != null) {
                bean.setListaBitacoras(getAuItemBitacoraRemoto().listarPorIdItem(bean.getObjetoItem().getAuAnexo3ItemId().getId()));
            } else {
                bean.setListaBitacoras(new ArrayList());
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo consultando las bitacoras");
        }
    }

    @Override
    public void consultarAdjuntosCotizacion(AuAutorizacionBean bean) {
        try {
            AuCotizacionItem itemCotizacion = getAuCotizacionItemRemoto().consultarPorIdAnexo3(bean.getObjeto().getAuAnexo3Id().getObjetoTecnologia().getId());
            if (itemCotizacion != null) {
                bean.setListaAdjuntosCotizacion(getAuSolicitudAdjuntoRemoto().listarAdjuntosByIdCotizacion(itemCotizacion.getAuCotizacionId().getId()));
            }
            AuCotizacion cotizacion = getAuCotizacionRemoto().consultarPorIdItemAnexo3(bean.getObjeto().getAuAnexo3Id().getObjetoTecnologia().getId());
            if(cotizacion != null){
                if(cotizacion.getAntAnticiposId() != null){
                    bean.setAntAnticipoAdjuntosList(getAnticipoAdjuntoRemoto().consultarAdjuntoPorAnticipoId(cotizacion.getAntAnticiposId().getId()));
                }
            }
        } catch (Exception e) {
            bean.addError("No se encontro una cotizacion, favor comunicarse con el administrador");
        }
    }
    
    @Override
    public boolean consultarCantidadAnuladas(AuAutorizacionBean bean, int idAnexo4Item) {
        boolean factura = true;
        try {
            int respuesta = getAuAnexo4EntregaRemoto().consultarCantidadEstadoAnuladaloporAnexo4Item(idAnexo4Item);
            if (respuesta >= 4) {
                factura = false;
            }
        } catch (Exception e) {
            factura = true;
        }
        return factura;
    }
    
    private void guardarItemBitacora(AuAnexo3Item item, AuAutorizacionBean bean, int tipo, String descripcion) throws Exception {
        AuItemBitacora bitacora = new AuItemBitacora();
        bitacora.setDescripcion(descripcion);
        bitacora.setTipo(tipo);
        bitacora.setAuAnexo3ItemId(item);
        bitacora.setEstado(item.getEstado());
        bean.auditoriaGuardar(bitacora);
        getAuItemBitacoraRemoto().insertar(bitacora);
    }

    private void guardarHistorico(AuAutorizacionBean bean, Maestro causa, String observacion) throws Exception {
        AuAnexo4Historico historico = new AuAnexo4Historico();
        historico.setAuAnexos4Id(bean.getObjeto());
        historico.setEstado(bean.getObjeto().getEstado());
        historico.setMaeCausaId(causa.getId());
        historico.setMaeCausaCodigo(causa.getValor());
        historico.setMaeCausaValor(causa.getNombre());
        historico.setObservacionAutorizacion(observacion);
        bean.auditoriaGuardar(historico);
        getAuAnexo4HistoricoRemoto().insertar(historico);
    }

    public void guardarGestionSeguimiento(AuAutorizacionBean bean, AuSeguimiento seguimiento, String estado, String observacion) throws Exception {
        AuSeguimientoGestion objetoSeguimientoGestion = new AuSeguimientoGestion();
        objetoSeguimientoGestion.setAuSeguimiento(seguimiento);
        Maestro maestroEstadoGestion = getMaestroRemoto().consultarPorValorTipo(estado, MaestroTipo.AU_SEGUIMIENTO_ESTADO);
        if (maestroEstadoGestion != null) {
            objetoSeguimientoGestion.setMaeEstadoSeguimientoGestionId(maestroEstadoGestion.getId());
            objetoSeguimientoGestion.setMaeEstadoSeguimientoGestionCodigo(maestroEstadoGestion.getValor());
            objetoSeguimientoGestion.setMaeEstadoSeguimientoGestionValor(maestroEstadoGestion.getNombre());
        }

        Maestro maestroMotivoGestion = getMaestroRemoto().consultarPorValorTipo(AuSeguimientoGestion.MOTIVO_OTRO, MaestroTipo.AU_SEGUIMIENTO_MOTIVO);
        if (maestroMotivoGestion != null) {
            objetoSeguimientoGestion.setMaeMotivoSeguimientoId(maestroMotivoGestion.getId());
            objetoSeguimientoGestion.setMaeMotivoSeguimientoCodigo(maestroMotivoGestion.getValor());
            objetoSeguimientoGestion.setMaeMotivoSeguimientoValor(maestroMotivoGestion.getNombre());
        }

        objetoSeguimientoGestion.setDescripcion(observacion);
        objetoSeguimientoGestion.setTipo(AuSeguimientoGestion.TIPO_CAMBIO_ESTADO);
        bean.auditoriaGuardar(objetoSeguimientoGestion);
        getAuSeguimientoGestionRemoto().insertar(objetoSeguimientoGestion);
    }

    public void guardarPrestadorSedeSeguimiento(AuAutorizacionBean bean, AuSeguimiento seguimiento) throws Exception {
        AuSeguimientoPrestadorAsignado prestador = new AuSeguimientoPrestadorAsignado();
        prestador.setCntPrestadorSede(seguimiento.getCntPrestadorSedeAsignadoId());
        prestador.setEstado(AuSeguimientoPrestadorAsignado.ESTADO_APROBADO);
        prestador.setAuSeguimiento(seguimiento);
        bean.auditoriaGuardar(prestador);
        getAuSeguimientoPrestadorAsignadoRemoto().insertar(prestador);
    }

    private void consultarContactosPrestador(AuAutorizacionBean bean) throws Exception {
        List<CntPrestadorContacto> contactos = getCntPrestadorContactoRemoto().consultarPorCntContratoSedeYArea(bean.getObjeto().getCntPrestadorSedeId().getCntPrestador().getId(), bean.getObjeto().getCntPrestadorSedeId().getId(), AuConstantes.AREA_CONTACTO_AUTORIZACION);
        CntPrestadorContacto correo = contactos.stream()
                .filter(c -> c.isActivo() && c.getMaeTipoContactoCodigo().equals(AuConstantes.TIPO_CONTACTO_CORREO))
                .findFirst().orElse(null);
        if (correo != null) {
            bean.getObjeto().setCorreoElectronicoPrestador(correo.getContacto());
        }
        contactos = contactos.stream()
                .filter(c -> c.isActivo() && (c.getMaeTipoContactoCodigo().equals(AuConstantes.TIPO_CONTACTO_TELEFONO_FIJO)
                || c.getMaeTipoContactoCodigo().equals(AuConstantes.TIPO_CONTACTO_TELEFONO_MOVIL)
                || c.getMaeTipoContactoCodigo().equals(AuConstantes.TIPO_CONTACTO_TELEFONO_PBX)))
                .collect(Collectors.toList());
        if (!contactos.isEmpty()) {
            bean.getObjeto().setTelefono1Prestador(contactos.get(0).getContacto());
            if (contactos.size() > 1) {
                bean.getObjeto().setTelefono2Prestador(contactos.get(1).getContacto());
            }
        }
    }
}
