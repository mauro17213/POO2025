package com.saviasaludeps.savia.web.juridico.servicio;

import com.saviasaludeps.savia.dominio.administracion.GnAlerta;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.juridico.CntjContrato;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoGarantia;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoIndicador;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoInforme;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoInformeAdjunto;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoObligacion;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoSeguimiento;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoSupervisor;
import com.saviasaludeps.savia.dominio.juridico.CntjEstado;
import com.saviasaludeps.savia.dominio.juridico.CntjEstadoEjecucion;
import com.saviasaludeps.savia.dominio.juridico.CntjExpediente;
import com.saviasaludeps.savia.dominio.juridico.CntjExpedienteResponsable;
import com.saviasaludeps.savia.dominio.juridico.CntjOtrosi;
import com.saviasaludeps.savia.dominio.juridico.CntjOtrosiAdjunto;
import com.saviasaludeps.savia.dominio.juridico.CntjProceso;
import com.saviasaludeps.savia.dominio.juridico.CntjUsuarioGrupo;
import com.saviasaludeps.savia.negocio.administracion.GnAlertaRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjContratoGarantiaRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjContratoIndicadorRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjContratoInformeAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjContratoInformeRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjContratoObligacionRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjContratoSeguimientoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjContratoSupervisorRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjDocumentoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjEstadoEjecucionRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjExpedienteRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjExpedienteResponsableRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjContratoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjEstadoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjOtrosiAdjuntoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjOtrosiRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjProcesoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjTerceroRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjUsuarioGrupoRemoto;
import com.saviasaludeps.savia.web.juridico.bean.ContratoBean;
import com.saviasaludeps.savia.web.juridico.utilidades.CntjConstantes;
import com.saviasaludeps.savia.web.singleton.MaestroSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.Fichero;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author idbohorquez
 */
public class ContratoServicioImpl extends AccionesBO implements ContratoServicioIface {

    private CtnjContratoRemoto getCtnjContratoRemoto() throws Exception {
        return (CtnjContratoRemoto) RemotoEJB.getEJBRemoto("CntjContratoServicio", CtnjContratoRemoto.class.getName());
    }

    private CtnjTerceroRemoto getCtnjTerceroRemoto() throws Exception {
        return (CtnjTerceroRemoto) RemotoEJB.getEJBRemoto("CntjTerceroServicio", CtnjTerceroRemoto.class.getName());
    }

    private CtnjOtrosiRemoto getCtnjOtrosiRemoto() throws Exception {
        return (CtnjOtrosiRemoto) RemotoEJB.getEJBRemoto("CntjOtrosiServicio", CtnjOtrosiRemoto.class.getName());
    }

    private CtnjOtrosiAdjuntoRemoto getCtnjOtrosiAdjuntoRemoto() throws Exception {
        return (CtnjOtrosiAdjuntoRemoto) RemotoEJB.getEJBRemoto("CntjOtrosiAdjuntoServicio", CtnjOtrosiAdjuntoRemoto.class.getName());
    }

    private CtnjProcesoRemoto getCtnjProcesoRemoto() throws Exception {
        return (CtnjProcesoRemoto) RemotoEJB.getEJBRemoto("CntjProcesoServicio", CtnjProcesoRemoto.class.getName());
    }

    private CntjContratoSupervisorRemoto getCntjContratoSupervisorRemoto() throws Exception {
        return (CntjContratoSupervisorRemoto) RemotoEJB.getEJBRemoto("CntjContratoSupervisorServicio", CntjContratoSupervisorRemoto.class.getName());
    }

    private CntjContratoGarantiaRemoto getCntjContratoGarantiaRemoto() throws Exception {
        return (CntjContratoGarantiaRemoto) RemotoEJB.getEJBRemoto("CntjContratoGarantiaServicio", CntjContratoGarantiaRemoto.class.getName());
    }

    private CntjContratoIndicadorRemoto getCntjContratoIndicadorRemoto() throws Exception {
        return (CntjContratoIndicadorRemoto) RemotoEJB.getEJBRemoto("CntjContratoIndicadorServicio", CntjContratoIndicadorRemoto.class.getName());
    }

    private CntjContratoObligacionRemoto getCntjContratoObligacionRemoto() throws Exception {
        return (CntjContratoObligacionRemoto) RemotoEJB.getEJBRemoto("CntjContratoObligacionServicio", CntjContratoObligacionRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        return (MaestroRemoto) RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
    }

    private CntjContratoSeguimientoRemoto getCntjContratoSeguimientoRemoto() throws Exception {
        return (CntjContratoSeguimientoRemoto) RemotoEJB.getEJBRemoto("CntjContratoSeguimientoServicio", CntjContratoSeguimientoRemoto.class.getName());
    }

    private CntjContratoInformeRemoto getCntjContratoInformeRemoto() throws Exception {
        return (CntjContratoInformeRemoto) RemotoEJB.getEJBRemoto("CntjContratoInformeServicio", CntjContratoInformeRemoto.class.getName());
    }

    private CntjContratoInformeAdjuntoRemoto getCntjContratoInformeAdjuntoRemoto() throws Exception {
        return (CntjContratoInformeAdjuntoRemoto) RemotoEJB.getEJBRemoto("CntjContratoInformeAdjuntoServicio", CntjContratoInformeAdjuntoRemoto.class.getName());
    }
    
    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }
    
    private CntjDocumentoRemoto getCntjDocumentoRemoto() throws Exception {
        return (CntjDocumentoRemoto) RemotoEJB.getEJBRemoto("CntjDocumentoServicio", CntjDocumentoRemoto.class.getName());
    }
    
    private CtnjEstadoRemoto getCtnjEstadoRemoto() throws Exception {
        return (CtnjEstadoRemoto) RemotoEJB.getEJBRemoto("CntjEstadoServicio", CtnjEstadoRemoto.class.getName());
    }
    
    private CntjExpedienteRemoto getCntjExpedienteRemoto() throws Exception {
        return (CntjExpedienteRemoto) RemotoEJB.getEJBRemoto("CntjExpedienteServicio", CntjExpedienteRemoto.class.getName());
    }
    
    private CntjEstadoEjecucionRemoto getCntjEstadoEjecucionRemoto() throws Exception {
        return (CntjEstadoEjecucionRemoto) RemotoEJB.getEJBRemoto("CntjEstadoEjecucionServicio", CntjEstadoEjecucionRemoto.class.getName());
    }
    
    private CntjExpedienteResponsableRemoto getCntjExpedienteResponsableRemoto() throws Exception {
        return (CntjExpedienteResponsableRemoto) RemotoEJB.getEJBRemoto("CntjExpedienteResponsableServicio", CntjExpedienteResponsableRemoto.class.getName());
    }
    
    private CtnjUsuarioGrupoRemoto getCtnjUsuarioGrupoRemoto() throws Exception {
        return (CtnjUsuarioGrupoRemoto) RemotoEJB.getEJBRemoto("CntjUsuarioGrupoServicio", CtnjUsuarioGrupoRemoto.class.getName());
    }
    
    private GnAlertaRemoto getGnAlertaRemoto() throws Exception {
        return (GnAlertaRemoto) RemotoEJB.getEJBRemoto("GnAlertaServicio", GnAlertaRemoto.class.getName());
    }
    

    @Override
    public void Accion(ContratoBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    ver(bean);
                    break;
                case Url.ACCION_CREAR:
                    crear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    guardar(bean);
                    break;
                case Url.ACCION_EDITAR:
                    editar(bean);
                    break;
                case Url.ACCION_MODIFICAR:
                    modificar(bean);
                    break;                
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarOtroSi(bean);
                            break;
                        case Url.ACCION_VER:
                            verOtroSi(bean);
                            break;
                        case Url.ACCION_CREAR:
                            crearOtroSi(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarOtroSi(bean);
                            break;
                        case Url.ACCION_EDITAR:
                            editarOtroSi(bean);
                            break;
                        case Url.ACCION_MODIFICAR:
                            modificarOtroSi(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            verSeguimiento(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            guardarSeguimiento(bean);
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            verInformes(bean);
                            break;
                        case Url.ACCION_ADICIONAL_4:
                            crearInforme(bean);
                            break;
                        case Url.ACCION_ADICIONAL_5:
                            guardarInforme(bean);
                            break;
                        case Url.ACCION_ADICIONAL_6:
                            editarInforme(bean);
                            break;
                        case Url.ACCION_ADICIONAL_7:
                            modificarInforme(bean);
                            break;
                        case Url.ACCION_ADICIONAL_8:
                            validarCreacionOtrosi(bean);
                            break;
                        case Url.ACCION_ADICIONAL_9:
                            crearEventoContractual(bean, CntjConstantes.TIPO_PROCESO_OTROSI);
                            break;
                        case Url.ACCION_ADICIONAL_10:
                            crearEventoContractual(bean, CntjConstantes.TIPO_PROCESO_INFORMES);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            verDetalleGarantia(bean);
                            break;
                        case Url.ACCION_CREAR:
                            crearGarantia(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            agregarGarantia(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_7:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            consultarDocumentos(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            consultarDocumento(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_8:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarTerceros(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            listarUsuarios(bean);
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            consultarResponsableSeguimiento(bean);
                            break;
                    }
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    @Override
    public void cargaInicial(ContratoBean bean) {
        try {
            bean.setListaTipoTercero(CntjConstantes.listaTipoTercero());
            bean.setListaTipoOtrosi(CntjConstantes.listaTipoOtrosi());
            bean.setListaEstadoOtrosi(CntjConstantes.listaEstadoOtrosi());
            bean.setListaTipoDocumentoTercero(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
            bean.getListaTipoDocumentoTercero().addAll(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
            bean.setListaEstadoContrato(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.CNTJ_CONTRATO_ESTADO));
            bean.setHashlistaEstadoContrato(Util.convertToHash(bean.getListaEstadoContrato()));
            bean.setListaTipoArchivo(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.CNTJ_OTROSIES_ADJUNTOS));
            bean.setHashlistaTipoArchivo(Util.convertToHash(bean.getListaTipoArchivo()));
            //Maestro tipo archivo informes
            bean.setListaTipoArchivoAdjuntoInforme(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.CNTJ_INFORMES_ARCHIVOS));
            bean.setHashTipoArchivoAdjuntoInforme(Util.convertToHash(bean.getListaTipoArchivoAdjuntoInforme()));
            //maestro clase contrato
            bean.setListaClaseContrato(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.CNTJ_CONTRATOS_CLASES));
            bean.setHashlistaClaseContrato(Util.convertToHash(bean.getListaClaseContrato()));
            //maestro garantia contrato
            bean.setListaGarantiaContrato(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.CNTJ_CONTRATOS_GARANTIAS));
            bean.setHashlistaGarantiaContrato(Util.convertToHash(bean.getListaGarantiaContrato()));
            //maestro indicadores contrato
            bean.setListaIndicadorContrato(CntjConstantes.maestroIndicadorContrato());
            bean.setHashlistaIndicadorContrato(Util.convertToHash(bean.getListaIndicadorContrato()));
            //procesos
            bean.setListaProcesos(getCtnjProcesoRemoto().getProcesos());
            bean.setHashlistaProceso(CntjConstantes.convertProcesoToHash(bean.getListaProcesos()));
            //Estado legalizacion
            bean.setListaEstadoLegalizacion(CntjConstantes.maestroEstadoLegalizacionContrato());
            //Modalidad
            bean.setListaModalidad(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.CNT_MODALIDAD));
            bean.setHashlistaModalidad(Util.convertToHash(bean.getListaModalidad()));
            //complejidad
            bean.setListaComplejidad(CntjConstantes.maestroComplejidadContrato());
            //regimen
            bean.setListaRegimen(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_REGIMEN));
            bean.setHashlistaRegimen(Util.convertToHash(bean.getListaRegimen()));
            //Forma de pago
            bean.setListaFormaDePago(CntjConstantes.maestroFormaDePagoContrato());
            //Tipo anticipo
            bean.setListaTipoAnticipo(CntjConstantes.maestroTipoAnticipoContrato());
            //Tipo gasto
            bean.setListaTipoGasto(CntjConstantes.getMaestroTipoGasto());
            //tipo de informe
            bean.setListaTipoInforme(CntjConstantes.getMaestroTipoInforme());
            //Lista estado informe
            bean.setListaEstadoInforme(CntjConstantes.getMaestroEstadoInforme());
            //Lista periodicidad seguimiento
            bean.setListaPeriodicidadSeguimiento(CntjConstantes.getMaestroperiodoSeguimiento());
            //estado legalizacion otrosi
            bean.setListaEstadoLegalizacionOtrosi(CntjConstantes.getListaEstadoLegalizacionOtrosi());
        } catch (Exception e) {
            Logger.getLogger(TerceroServicioImpl.class.getName()).log(Level.SEVERE, String.format("Se presento inconveniente al cargas información inicial. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al cargas información inicial.");
        }
    }

    private void listar(ContratoBean bean) {
        try {
            if(bean.isAccionAdicional1()){
                //Ver todos los registros
                bean.getParamConsulta().setParametroConsulta1(null);
            }else{
                //ver registros por usuario
                bean.getParamConsulta().setParametroConsulta1(bean.getConexion().getUsuario().getId());
            }
            bean.getParamConsulta().setCantidadRegistros(getCtnjContratoRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getCtnjContratoRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al listar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al listar información." + e.getMessage());
        }
    }

    private void ver(ContratoBean bean) {
        try {
            bean.setObjeto(getCtnjContratoRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setCntjContratoGarantiaList(getCntjContratoGarantiaRemoto().garantiasContrato(bean.getObjeto().getId()));
            bean.getObjeto().setCntjContratoIndicadorList(getCntjContratoIndicadorRemoto().indicadoresContrato(bean.getObjeto().getId()));
            bean.getObjeto().setCntjContratoObligacionList(getCntjContratoObligacionRemoto().obligacionesContrato(bean.getObjeto().getId()));
            bean.getObjeto().setCntjContratoSupervisorList(getCntjContratoSupervisorRemoto().supervisoresContrato(bean.getObjeto().getId()));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al consultar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al consultar información." + e.getMessage());
        }
    }

    private void crear(ContratoBean bean) {
        try {
            bean.setObjeto(new CntjContrato());
            bean.setMinimoPlazoDia(1);
            bean.getObjeto().setCntjContratoGarantiaList(new ArrayList<>());
            bean.getObjeto().setCntjContratoIndicadorList(new ArrayList<>());
            bean.getObjeto().setCntjContratoObligacionList(new ArrayList<>());
            bean.getObjeto().setCntjContratoSupervisorList(new ArrayList<>());
            bean.getObjeto().setCntjContratoSeguimientoList(new ArrayList<>());
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al realizar el proceso. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al realizar el proceso." + e.getMessage());
        }
    }

    private void guardar(ContratoBean bean) {
        try {

            if (bean.getObjeto().getMaeEstadoContratoId() == 0) {
                bean.addError("Debe selecionar un valor para el campo Estado de Contrato.");
            }
            if (bean.getObjeto().getFechaInicio() == null) {
                bean.addError("Debe indicar la fecha inicio del contrato");
            }
            if (bean.getObjeto().getFechaFin() == null) {
                bean.addError("Debe indicar la fecha fin del contrato");
            }
            if (bean.getObjeto().getValorInicial() == null) {
                bean.addError("Debe indicar el valor del contrato.");
            }
            if (bean.getObjeto().getValorPagadoTotal() == null) {
                bean.addError("Debe indicar el valor ejecutado.");
            }

            if (bean.getObjeto().getCntjContratoSupervisorList().isEmpty()) {
                bean.addError("Debe agregar por lo menos un responsable de seguimiento.");
            }

            boolean existe = bean.getRegistros().stream()
                    .anyMatch(elemento -> elemento.getContrato().equals(bean.getObjeto().getContrato()));
            if (existe) {
                bean.addError("Ya existe un registro con el mismo contrato, el valor del campo contrato es único");
            }

            if (bean.getObjeto().getTipoAnticipo() == CntjConstantes.TIPO_ANTICIPO_ANTICIPADO) {
                if (bean.getObjeto().getValorAnticipo() == null && bean.getObjeto().getValorAnticipo().compareTo(BigDecimal.valueOf(0)) > 0) {
                    bean.addError("Debe ingresar el valor anticipo.");
                }
            }
            
            if(bean.getObjeto().getFechaSuspension() != null){
                if(bean.getObjeto().getMotivoSuspension() == null || bean.getObjeto().getMotivoSuspension().isEmpty()){
                     bean.addError("Debe ingresar un motivo de suspensión porque selecciono una fecha de suspensión.");
                }               
            }

            if (!bean.getErrores().isEmpty()) {
                return;
            }

            boolean continuar = true;

            Maestro maemodalidad = bean.getHashlistaModalidad().get(bean.getObjeto().getMaeModalidadContratoId());
            if (maemodalidad != null) {
                bean.getObjeto().setMaeModalidadContratoCodigo(maemodalidad.getValor());
                bean.getObjeto().setMaeModalidadContratoValor(maemodalidad.getNombre());
            }
            Maestro maeclasecontrato = bean.getHashlistaClaseContrato().get(bean.getObjeto().getMaeClaseContratoId());
            if (maeclasecontrato != null) {
                bean.getObjeto().setMaeClaseContratoCodigo(maeclasecontrato.getValor());
                bean.getObjeto().setMaeClaseContratoValor(maeclasecontrato.getNombre());
            }
            Maestro maeregimen = bean.getHashlistaRegimen().get(bean.getObjeto().getMaeRegimenId());
            if (maeregimen != null) {
                bean.getObjeto().setMaeRegimenCodigo(maeregimen.getValor());
                bean.getObjeto().setMaeRegimenValor(maeregimen.getNombre());
            }

            Maestro estadocontrato = bean.getHashlistaEstadoContrato().get(bean.getObjeto().getMaeEstadoContratoId());
            bean.getObjeto().setMaeEstadoContratoCodigo(estadocontrato.getValor());
            bean.getObjeto().setMaeEstadoContratoValor(estadocontrato.getDescripcion());

            bean.auditoriaGuardar(bean.getObjeto());
            int idcontrato = getCtnjContratoRemoto().insertar(bean.getObjeto());

            //guardar supervisores
            if (!bean.getObjeto().getCntjContratoSupervisorList().isEmpty()) {
                for (CntjContratoSupervisor supervisor : bean.getObjeto().getCntjContratoSupervisorList()) {
                    supervisor.setCntjContratosId(new CntjContrato(idcontrato));
                    supervisor.setFechaInicio(bean.getObjeto().getFechaInicio());
                    supervisor.setFechaFin(bean.getObjeto().getFechaFin());
                    bean.auditoriaGuardar(supervisor);
                    try {
                        supervisor.setId(getCntjContratoSupervisorRemoto().insertar(supervisor));
                    } catch (Exception e) {
                        continuar = false;
                        bean.addError("Se presento inconveniente al guardar supervisores." + e.getMessage());
                        getCntjContratoSupervisorRemoto().eliminarPorcontrato(idcontrato);
                        getCtnjContratoRemoto().eliminar(idcontrato);
                        continue;
                    }

                }
            }

            //guardar garantia
            if (!bean.getObjeto().getCntjContratoGarantiaList().isEmpty() && continuar) {
                for (CntjContratoGarantia garantia : bean.getObjeto().getCntjContratoGarantiaList()) {
                    garantia.setCntjContratoId(new CntjContrato(idcontrato));
                    Maestro maegarantia = bean.getHashlistaGarantiaContrato().get(garantia.getMaeGarantiaContratoId());
                    garantia.setMaeGarantiaContratoCodigo(maegarantia.getValor());
                    garantia.setMaeGarantiaContratoValor(maegarantia.getNombre());
                    bean.auditoriaGuardar(garantia);
                    try {
                        getCntjContratoGarantiaRemoto().insertar(garantia);
                    } catch (Exception e) {
                        continuar = false;
                        bean.addError("Se presento inconveniente al guardar garantia." + e.getMessage());
                        getCntjContratoGarantiaRemoto().eliminarPorcontrato(idcontrato);
                        getCntjContratoSupervisorRemoto().eliminarPorcontrato(idcontrato);
                        getCtnjContratoRemoto().eliminar(idcontrato);
                        continue;
                    }
                }
            }

            //guardar indicadores
            if (!bean.getObjeto().getCntjContratoIndicadorList().isEmpty() && continuar) {
                for (CntjContratoIndicador indicador : bean.getObjeto().getCntjContratoIndicadorList()) {
                    indicador.setCntjContratosId(new CntjContrato(idcontrato));
                    bean.auditoriaGuardar(indicador);
                    try {
                        getCntjContratoIndicadorRemoto().insertar(indicador);
                    } catch (Exception e) {
                        continuar = false;
                        bean.addError("Se presento inconveniente al guardar indicadores." + e.getMessage());
                        getCntjContratoIndicadorRemoto().eliminarPorcontrato(idcontrato);
                        getCntjContratoSupervisorRemoto().eliminarPorcontrato(idcontrato);
                        getCntjContratoGarantiaRemoto().eliminarPorcontrato(idcontrato);
                        getCtnjContratoRemoto().eliminar(idcontrato);
                        continue;
                    }
                }
            }
            //guardar obligaciones
            if (!bean.getObjeto().getCntjContratoObligacionList().isEmpty() && continuar) {
                for (CntjContratoObligacion obligacion : bean.getObjeto().getCntjContratoObligacionList()) {
                    obligacion.setCntjContratosId(new CntjContrato(idcontrato));
                    bean.auditoriaGuardar(obligacion);
                    try {
                        getCntjContratoObligacionRemoto().insertar(obligacion);
                    } catch (Exception e) {
                        continuar = false;
                        bean.addError("Se presento inconveniente al guardar obligaciones." + e.getMessage());
                        getCntjContratoObligacionRemoto().eliminarPorcontrato(idcontrato);
                        getCntjContratoSupervisorRemoto().eliminarPorcontrato(idcontrato);
                        getCntjContratoGarantiaRemoto().eliminarPorcontrato(idcontrato);
                        getCntjContratoIndicadorRemoto().eliminarPorcontrato(idcontrato);
                        getCtnjContratoRemoto().eliminar(idcontrato);
                        continue;
                    }
                }
            }

            //guardar seguimientos
            if (continuar) {
                if (bean.getObjeto().getCntjContratoSeguimientoList().isEmpty()) {
                    CntjContratoSeguimiento seguimiento = new CntjContratoSeguimiento();
                    seguimiento.setCntjContratoId(new CntjContrato(idcontrato));
                    seguimiento.setCntjTerceroId(bean.getObjeto().getCntjTerceroId());
                    CntjProceso proceso = getCtnjProcesoRemoto().consultar(Integer.parseInt(bean.getObjeto().getProceso()));
                    if (proceso.getTipoProceso().equals(CntjConstantes.TIPO_PROCESO_ADMINISTRATIVO)) {
                        seguimiento.setPeriodicidad(CntjContratoSeguimiento.PERIODICIDAD_MENSUAL);
                    } else if (proceso.getTipoProceso().equals(CntjConstantes.TIPO_PROCESO_SALUD)
                            && bean.getObjeto().getFormaPago().equals(CntjConstantes.FORMA_PAGO_MENSUAL_VARIABLE)) {
                        seguimiento.setPeriodicidad(CntjContratoSeguimiento.PERIODICIDAD_BIMESTRAL);
                    } else {
                        seguimiento.setPeriodicidad(CntjContratoSeguimiento.PERIODICIDAD_SEMESTRAL);
                    }
                    seguimiento.setFechaInicioSeguimiento(bean.getObjeto().getFechaInicio());
                    seguimiento.setFechaFinSeguimiento(bean.getObjeto().getFechaFin());
                    seguimiento.setEstadoSeguimiento(CntjContratoSeguimiento.ESTADO_PENDIENTE);
                    seguimiento.setObservaciones("Inicial");
                    bean.auditoriaGuardar(seguimiento);
                    try {
                        getCntjContratoSeguimientoRemoto().insertar(seguimiento);
                    } catch (Exception e) {
                    }

                }
            }

            if (bean.getErrores().isEmpty()) {
                bean.addMensaje("Información guardada correctamente.");
            }

        } catch (Exception e) {
            bean.addError("Se presento inconveniente al guardar.");
        }
    }

    private void editar(ContratoBean bean) {
        try {
            bean.setObjeto(getCtnjContratoRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setCntjContratoGarantiaList(getCntjContratoGarantiaRemoto().garantiasContrato(bean.getObjeto().getId()));
            bean.getObjeto().setCntjContratoIndicadorList(getCntjContratoIndicadorRemoto().indicadoresContrato(bean.getObjeto().getId()));
            bean.getObjeto().setCntjContratoObligacionList(getCntjContratoObligacionRemoto().obligacionesContrato(bean.getObjeto().getId()));
            bean.getObjeto().setCntjContratoSupervisorList(getCntjContratoSupervisorRemoto().supervisoresContrato(bean.getObjeto().getId()));
            bean.setListaGarantiaeliminar(new ArrayList<>());
            bean.setListaIndicadorEliminar(new ArrayList<>());
            bean.setListaObligacionEliminar(new ArrayList<>());
            bean.setListaSupervisorEliminar(new ArrayList<>());
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al consultar información . %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al consultar información." + e.getMessage());
        }
    }

    private void listarTerceros(ContratoBean bean) {
        try {
            bean.getParamConsulta(0).setCantidadRegistros(getCtnjTerceroRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setRegistrosTerceros(getCtnjTerceroRemoto().consultarLista(bean.getParamConsulta(0)));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al listar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al listar información." + e.getMessage());
        }
    }
    
    private void listarUsuarios(ContratoBean bean) {
        try {
            if (!bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta(2).setEmpresaId(bean.getConexion().getEmpresa().getId());
            }
            bean.getParamConsulta(2).getFiltros().put("activo", "1");
            bean.getParamConsulta(2).setCantidadRegistros(getUsuarioRemoto().consultarCantidadLista(bean.getParamConsulta(2)));
            bean.setListaUsuario(getUsuarioRemoto().consultarLista(bean.getParamConsulta(2)));
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al realizar el proceso.");
        }
    }

    private void modificar(ContratoBean bean) {
        try {
            if (bean.getObjeto().getMaeEstadoContratoId() == 0) {
                bean.addError("Debe selecionar un valor para el campo Estado de Contrato.");
            }
            if (bean.getObjeto().getFechaInicio() == null) {
                bean.addError("Debe indicar la fecha inicio del contrato");
            }
            if (bean.getObjeto().getFechaFin() == null) {
                bean.addError("Debe indicar la fecha fin del contrato");
            }
            if (bean.getObjeto().getValorInicial() == null) {
                bean.addError("Debe indicar el valor del contrato.");
            }
            if (bean.getObjeto().getValorPagadoTotal() == null) {
                bean.addError("Debe indicar el valor ejecutado.");
            }

            if (bean.getObjeto().getCntjContratoSupervisorList().isEmpty()) {
                bean.addError("Debe agregar por lo menos un responsable de seguimiento.");
            }
            
            boolean existe = bean.getRegistros().stream()
                    .anyMatch(elemento -> (elemento.getContrato().equals(bean.getObjeto().getContrato()) && !elemento.getId().equals(bean.getObjeto().getId())));
            if (existe) {
                bean.addError("Ya existe un registro con el mismo contrato, el valor del campo contrato es único");
            }

            if (bean.getObjeto().getTipoAnticipo() == CntjConstantes.TIPO_ANTICIPO_ANTICIPADO) {
                if (bean.getObjeto().getValorAnticipo() == null && bean.getObjeto().getValorAnticipo().compareTo(BigDecimal.valueOf(0)) > 0) {
                    bean.addError("Debe ingresar el valor anticipo.");
                }
            }
            
            if(bean.getObjeto().getFechaSuspension() != null){
                if(bean.getObjeto().getMotivoSuspension() == null || bean.getObjeto().getMotivoSuspension().isEmpty()){
                     bean.addError("Debe ingresar un motivo de suspensión porque selecciono una fecha de suspensión.");
                }               
            }

            if (!bean.getErrores().isEmpty()) {
                return;
            }

            boolean continuar = true;

            Maestro maemodalidad = bean.getHashlistaModalidad().get(bean.getObjeto().getMaeModalidadContratoId());
            if (maemodalidad != null) {
                bean.getObjeto().setMaeModalidadContratoCodigo(maemodalidad.getValor());
                bean.getObjeto().setMaeModalidadContratoValor(maemodalidad.getNombre());
            }
            Maestro maeclasecontrato = bean.getHashlistaClaseContrato().get(bean.getObjeto().getMaeClaseContratoId());
            if (maeclasecontrato != null) {
                bean.getObjeto().setMaeClaseContratoCodigo(maeclasecontrato.getValor());
                bean.getObjeto().setMaeClaseContratoValor(maeclasecontrato.getNombre());
            }
            Maestro maeregimen = bean.getHashlistaRegimen().get(bean.getObjeto().getMaeRegimenId());
            if (maeregimen != null) {
                bean.getObjeto().setMaeRegimenCodigo(maeregimen.getValor());
                bean.getObjeto().setMaeRegimenValor(maeregimen.getNombre());
            }

            Maestro estadocontrato = bean.getHashlistaEstadoContrato().get(bean.getObjeto().getMaeEstadoContratoId());
            bean.getObjeto().setMaeEstadoContratoCodigo(estadocontrato.getValor());
            bean.getObjeto().setMaeEstadoContratoValor(estadocontrato.getDescripcion());

            bean.auditoriaModificar(bean.getObjeto());
            getCtnjContratoRemoto().actualizar(bean.getObjeto());

            //eliminar garantias
            if (!bean.getListaGarantiaeliminar().isEmpty()) {
                for (CntjContratoGarantia garantia : bean.getListaGarantiaeliminar()) {
                    getCntjContratoGarantiaRemoto().eliminar(garantia.getId());
                }
            }

            //eliminar indicadores
            if (!bean.getListaIndicadorEliminar().isEmpty()) {
                for (CntjContratoIndicador indicador : bean.getListaIndicadorEliminar()) {
                    getCntjContratoIndicadorRemoto().eliminar(indicador.getId());
                }
            }

            //eliminar obligaciones
            if (!bean.getListaObligacionEliminar().isEmpty()) {
                for (CntjContratoObligacion obligacion : bean.getListaObligacionEliminar()) {
                    getCntjContratoObligacionRemoto().eliminar(obligacion.getId());
                }
            }

            //eliminar supervisores
            if (!bean.getListaSupervisorEliminar().isEmpty()) {
                for (CntjContratoSupervisor supervisor : bean.getListaSupervisorEliminar()) {
                    getCntjContratoSupervisorRemoto().eliminar(supervisor.getId());
                }
            }

            //guardar supervisores
            List<CntjContratoSupervisor> auxSupervisor = bean.getObjeto().getCntjContratoSupervisorList().stream()
                    .filter(asistente -> asistente.getId() == null)
                    .collect(Collectors.toList());

            if (!auxSupervisor.isEmpty()) {
                for (CntjContratoSupervisor supervisor : auxSupervisor) {
                    supervisor.setCntjContratosId(new CntjContrato(bean.getObjeto().getId()));
                    supervisor.setFechaInicio(bean.getObjeto().getFechaInicio());
                    supervisor.setFechaFin(bean.getObjeto().getFechaFin());
                    bean.auditoriaGuardar(supervisor);
                    try {
                        supervisor.setId(getCntjContratoSupervisorRemoto().insertar(supervisor));
                    } catch (Exception e) {
                        continuar = false;
                        bean.addError("Se presento inconveniente al guardar supervisores.");
                        continue;
                    }

                }
            }

            //guardar garantia
            List<CntjContratoGarantia> auxGarantia = bean.getObjeto().getCntjContratoGarantiaList().stream()
                    .filter(asistente -> asistente.getId() == null)
                    .collect(Collectors.toList());

            if (!auxGarantia.isEmpty() && continuar) {
                for (CntjContratoGarantia garantia : auxGarantia) {
                    garantia.setCntjContratoId(new CntjContrato(bean.getObjeto().getId()));
                    Maestro maegarantia = bean.getHashlistaGarantiaContrato().get(garantia.getMaeGarantiaContratoId());
                    garantia.setMaeGarantiaContratoCodigo(maegarantia.getValor());
                    garantia.setMaeGarantiaContratoValor(maegarantia.getNombre());
                    bean.auditoriaGuardar(garantia);
                    try {
                        getCntjContratoGarantiaRemoto().insertar(garantia);
                    } catch (Exception e) {
                        continuar = false;
                        bean.addError("Se presento inconveniente al guardar garantia.");
                        continue;
                    }
                }
            }

            //guardar indicadores
            List<CntjContratoIndicador> auxIndicador = bean.getObjeto().getCntjContratoIndicadorList().stream()
                    .filter(asistente -> asistente.getId() == null)
                    .collect(Collectors.toList());

            if (!auxIndicador.isEmpty() && continuar) {
                for (CntjContratoIndicador indicador : auxIndicador) {
                    indicador.setCntjContratosId(new CntjContrato(bean.getObjeto().getId()));
                    bean.auditoriaGuardar(indicador);
                    try {
                        getCntjContratoIndicadorRemoto().insertar(indicador);
                    } catch (Exception e) {
                        continuar = false;
                        bean.addError("Se presento inconveniente al guardar indicadores.");
                        continue;
                    }
                }
            }
            //guardar obligaciones
            List<CntjContratoObligacion> auxObligacion = bean.getObjeto().getCntjContratoObligacionList().stream()
                    .filter(asistente -> asistente.getId() == null)
                    .collect(Collectors.toList());

            if (!auxObligacion.isEmpty() && continuar) {
                for (CntjContratoObligacion obligacion : auxObligacion) {
                    obligacion.setCntjContratosId(new CntjContrato(bean.getObjeto().getId()));
                    bean.auditoriaGuardar(obligacion);
                    try {
                        getCntjContratoObligacionRemoto().insertar(obligacion);
                    } catch (Exception e) {
                        continuar = false;
                        bean.addError("Se presento inconveniente al guardar obligaciones.");
                        continue;
                    }
                }
            }

            bean.addMensaje("Información modificada correctamente.");
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al modificar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al modificar información.");
        }
    }

    private void listarOtroSi(ContratoBean bean) {
        try {
            bean.setObjeto(getCtnjContratoRemoto().consultar(bean.getObjeto().getId()));            
            bean.getParamConsulta(1).setCantidadRegistros(getCtnjOtrosiRemoto().consultarCantidadLista(bean.getParamConsulta(1)));
            bean.setRegistrosOtrosi(getCtnjOtrosiRemoto().consultarLista(bean.getParamConsulta(1)));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al listar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al listar información." + e.getMessage());
        }
    }

    private void verOtroSi(ContratoBean bean) {
        try {
            bean.setObjetoOtrosi(getCtnjOtrosiRemoto().consultar(bean.getObjetoOtrosi().getId()));
            bean.setAdjuntosOtroSi(getCtnjOtrosiAdjuntoRemoto().adjuntosOtrosi(bean.getObjetoOtrosi().getId()));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al consultar información . %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al consultar información." + e.getMessage());
        }
    }

    private void crearOtroSi(ContratoBean bean) {
        try {
            bean.setObjetoOtrosi(new CntjOtrosi());
            bean.setAdjuntosOtroSi(new ArrayList<>());
            bean.setListaAdjuntoeliminar(new ArrayList<>());
            Integer vigentes = getCtnjOtrosiRemoto().otrosiVigentes(bean.getObjeto().getId());
            if(vigentes != null && vigentes > 0){
                bean.addError("No puede crear otro registro ya que existen otrosíes en estado vigente.");
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al realizar el proceso. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al realizar el proceso." + e.getMessage());
        }
    }

    private void guardarOtroSi(ContratoBean bean) {
        try {
            if (bean.getObjetoOtrosi().getTipo() == null) {
                bean.addError("Debe selecionar un valor para el campo Tipo Otrosí.");
            }
            if (bean.getObjetoOtrosi().getFechasuscripcion() == null) {
                bean.addError("Debe selecionar un valor para el campo Fecha Suscripción.");
            }

            if (bean.getObjetoOtrosi().getTipo() != null && !bean.getObjetoOtrosi().getTipo().equals(CntjConstantes.OTROSI_ACTA_INICIO)) {
                for (Maestro ma : bean.getListaTipoArchivo()) {
                    boolean existe = bean.getAdjuntosOtroSi().stream()
                            .anyMatch(elemento -> elemento.getMaetipoArchivoId().equals(ma.getId()));
                    if (!existe) {
                        bean.addError("Debe agregar un adjunto relacionado a " + ma.getNombre());
                    }
                }
            }
            
            if(bean.getObjetoOtrosi().getFechasuscripcion().after(bean.getObjetoOtrosi().getFechaInicio())){
                bean.addError("La fecha de suscripción no puede ser mayor a la fecha de inicio.");
            }
            
            if (bean.getObjetoOtrosi().getTipo() != null) {
                switch (bean.getObjetoOtrosi().getTipo()) {
                    case CntjConstantes.OTROSI_PRORROGA:
                    case CntjConstantes.OTROSI_PRORROGA_MODIFICACION:
                    case CntjConstantes.OTROSI_ACTA_INICIO:
                        if (bean.getObjetoOtrosi().getPlazoMeses() == null) {
                            bean.addError("Debe ingresar un valor para el campo Plazo Prorroga (Meses).");
                        }
                        if (bean.getObjetoOtrosi().getPlazoDias() == null) {
                            bean.addError("Debe ingresar un valor para el campo Plazo Prorroga (Días).");
                        }
                        break;
                    case CntjConstantes.OTROSI_ADICION:
                    case CntjConstantes.OTROSI_ADICION_MODIFICACION:
                        if (bean.getObjetoOtrosi().getValor() == null) {
                            bean.addError("Debe ingresar el Valor Otrosí");
                        }
                        break;
                    case CntjConstantes.OTROSI_PRORROGA_ADICION:
                    case CntjConstantes.OTROSI_PRORROGA_ADICION_MODIFICACION:
                        if (bean.getObjetoOtrosi().getPlazoMeses() == null) {
                            bean.addError("Debe ingresar un valor para el campo Plazo Prorroga (Meses).");
                        }
                        if (bean.getObjetoOtrosi().getPlazoDias() == null) {
                            bean.addError("Debe ingresar un valor para el campo Plazo Prorroga (Días).");
                        }
                        if (bean.getObjetoOtrosi().getValor() == null) {
                            bean.addError("Debe ingresar el Valor Otrosí");
                        }
                        break;
                    default:
                        break;
                }
            }
            
            CntjOtrosi actaInicio = getCtnjOtrosiRemoto().otrosiActaInicio(bean.getObjeto().getId());
            if(actaInicio != null && bean.getObjetoOtrosi().getTipo().equals(CntjConstantes.OTROSI_ACTA_INICIO)){
                bean.addError("Ya existe creada el acta de inicio.");
            }
            /*if(actaInicio == null && !bean.getObjetoOtrosi().getTipo().equals(CntjConstantes.OTROSI_ACTA_INICIO)){
                bean.addError("Debe crear el acta de inicio antes de registrar otrosies.");
            }*/

            String ruta = PropApl.getInstance().get(PropApl.CNTJ_RUTA_OTROSIES_ADJUNTOS);
            Files.createDirectories(Paths.get(ruta));
            if (!new File(ruta).exists()) {
                bean.addError("La ruta indicada para almacenar los adjuntos no existe. ");
            }

            CntjOtrosi maxNumero = getCtnjOtrosiRemoto().ultimoNumeroOtroSi(bean.getObjeto().getId());            
            if (maxNumero != null) {
                bean.getObjetoOtrosi().setNumero(maxNumero.getNumero() + 1);
                if (bean.getObjetoOtrosi().getFechasuscripcion().before(maxNumero.getFechasuscripcion())) {
                    bean.addError("La fecha de suscripción del actual registro no puede ser inferior a la fecha de suscripción del ultimo otrosí registrado.");
                }
            } else {
                bean.getObjetoOtrosi().setNumero(bean.getRegistrosOtrosi().size() + 1);
            }

            if (!bean.getErrores().isEmpty()) {
                return;
            }

            bean.getObjetoOtrosi().setContratoId(bean.getObjeto());
            bean.getObjetoOtrosi().setEstado(CntjConstantes.OTROSI_VIGENTE);
            bean.auditoriaGuardar(bean.getObjetoOtrosi());
            Integer idotrosi = getCtnjOtrosiRemoto().insertar(bean.getObjetoOtrosi());
            for (CntjOtrosiAdjunto adjunto : bean.getAdjuntosOtroSi()) {
                Date fecha = new Date();
                Maestro maestroTipoArchivo = bean.getHashlistaTipoArchivo().get(adjunto.getMaetipoArchivoId());
                adjunto.setMaetipoArchivoCodigo(maestroTipoArchivo.getValor());
                adjunto.setMaetipoArchivoValor(maestroTipoArchivo.getNombre());
                adjunto.setOtrosiId(new CntjOtrosi(idotrosi));
                adjunto.setRuta(ruta);
                adjunto.setExiste(true);
                int indiceExtension = adjunto.getNombre().lastIndexOf(".");
                String extension = adjunto.getNombre().substring(indiceExtension, adjunto.getNombre().length());
                adjunto.setArchivo(String.format("%s%s%s", CntjConstantes.NOMBRE_ADJUNTO_OTROSI, CntjConstantes.formato6.format(fecha), extension));
                bean.auditoriaGuardar(adjunto);
                adjunto.setId(getCtnjOtrosiAdjuntoRemoto().insertar(adjunto));
                generarArchivo(adjunto);
            }
            //modificar plazo prorroga en contrato
            if (CntjConstantes.isProrroga(bean.getObjetoOtrosi().getTipo())) {
                CntjContrato contrato = bean.getObjetoOtrosi().getContratoId();
                contrato.setPlazoProrrogas(bean.getObjetoOtrosi().getPlazoDias());
                contrato.setFechaFin(bean.getObjetoOtrosi().getFechaTerminacion());
                if(bean.getObjetoOtrosi().getTipo().equals(CntjConstantes.OTROSI_ACTA_INICIO)){
                    contrato.setFechaInicio(bean.getObjetoOtrosi().getFechaInicio());
                }
                bean.auditoriaModificar(contrato);
                getCtnjContratoRemoto().actualizarPlazoProrroga(contrato);
                if (CntjConstantes.isAdicion(bean.getObjetoOtrosi().getTipo())) {
                    contrato.setValorAdiciones(bean.getObjetoOtrosi().getValor());
                    getCtnjContratoRemoto().actualizarValorAdiciones(contrato);
                }
            }else if (CntjConstantes.isAdicion(bean.getObjetoOtrosi().getTipo())) {
                CntjContrato contrato = bean.getObjetoOtrosi().getContratoId();
                contrato.setValorAdiciones(bean.getObjetoOtrosi().getValor());
                bean.auditoriaModificar(contrato);
                getCtnjContratoRemoto().actualizarValorAdiciones(contrato);
            }      
            
            bean.addMensaje("Información guardada correctamente.");
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al guardar." + e.getMessage());
        }
    }

    private boolean generarArchivo(CntjOtrosiAdjunto objeto) throws Exception {
        boolean esArchivoGuardado = false;
        OutputStream destino = null;
        try {
            File archivo = new File(objeto.getRuta(), objeto.getArchivo());
            destino = new FileOutputStream(archivo);
            IOUtils.copy(objeto.getAdjuntoStream(), destino);
            IOUtils.closeQuietly(objeto.getAdjuntoStream());
            IOUtils.closeQuietly(destino);
            Fichero.permisos(archivo.toPath());
            esArchivoGuardado = true;
        } catch (FileNotFoundException ex) {
            throw new Exception("Error al subir un adjunto " + ex.getMessage());
        } catch (IOException ex) {
            throw new Exception("Error al subir un adjunto " + ex.getMessage());
        } finally {
            try {
                destino.close();
            } catch (IOException e) {
                esArchivoGuardado = false;
            }
        }

        return esArchivoGuardado;
    }

    private boolean generarArchivoInforme(CntjContratoInformeAdjunto objeto) throws Exception {
        boolean esArchivoGuardado = false;
        OutputStream destino = null;
        try {
            File archivo = new File(objeto.getRuta(), objeto.getArchivo());
            destino = new FileOutputStream(archivo);
            IOUtils.copy(objeto.getAdjuntoStream(), destino);
            IOUtils.closeQuietly(objeto.getAdjuntoStream());
            IOUtils.closeQuietly(destino);
            Fichero.permisos(archivo.toPath());
            esArchivoGuardado = true;
        } catch (FileNotFoundException ex) {
            esArchivoGuardado = false;
        } catch (IOException ex) {
            esArchivoGuardado = false;
        } finally {
            try {
                destino.close();
            } catch (IOException e) {
                esArchivoGuardado = false;
            }
        }

        return esArchivoGuardado;
    }

    private void editarOtroSi(ContratoBean bean) {
        try {
            bean.setObjetoOtrosi(getCtnjOtrosiRemoto().consultar(bean.getObjetoOtrosi().getId()));
            bean.setDiasProrrogaAnterior(bean.getObjetoOtrosi().getPlazoDias());
            bean.setFechaFinAnterior(bean.getObjetoOtrosi().getFechaTerminacion());
            bean.setValorAdicionAnterior(bean.getObjetoOtrosi().getValor());
            bean.setAdjuntosOtroSi(getCtnjOtrosiAdjuntoRemoto().adjuntosOtrosi(bean.getObjetoOtrosi().getId()));
            bean.setListaAdjuntoeliminar(new ArrayList<>());
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al consultar información . %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al consultar información." + e.getMessage());
        }
    }

    private void modificarOtroSi(ContratoBean bean) {
        try {
            if (bean.getObjetoOtrosi().getTipo() == null) {
                bean.addError("Debe selecionar un valor para el campo Tipo Otrosí.");
            }
            if (bean.getObjetoOtrosi().getFechasuscripcion() == null) {
                bean.addError("Debe selecionar un valor para el campo Fecha Suscripción.");
            }

            if (bean.getAdjuntosOtroSi().isEmpty()  && !bean.getObjetoOtrosi().getTipo().equals(CntjConstantes.OTROSI_ACTA_INICIO)) {
                bean.addError("Debe agregar por lo menos un adjunto.");
            }
            
            if(bean.getObjetoOtrosi().getFechasuscripcion().after(bean.getObjetoOtrosi().getFechaInicio())){
                bean.addError("La fecha de suscripción no puede ser mayor a la fecha de inicio.");
            }

            if (bean.getObjetoOtrosi().getTipo() != null) {
                switch (bean.getObjetoOtrosi().getTipo()) {
                    case CntjConstantes.OTROSI_PRORROGA:
                    case CntjConstantes.OTROSI_PRORROGA_MODIFICACION:
                    case CntjConstantes.OTROSI_ACTA_INICIO:
                        bean.getObjetoOtrosi().setValor(null);
                        if (bean.getObjetoOtrosi().getPlazoMeses() == null) {
                            bean.addError("Debe ingresar un valor para el campo Plazo Prorroga (Meses).");
                        }
                        if (bean.getObjetoOtrosi().getPlazoDias() == null) {
                            bean.addError("Debe ingresar un valor para el campo Plazo Prorroga (Días).");
                        }
                        if (bean.getObjetoOtrosi().getFechaInicio() == null) {
                            bean.addError("Debe ingresar un valor para la Fecha Inicio.");
                        }
                        if (bean.getObjetoOtrosi().getFechaTerminacion() == null) {
                            bean.addError("Debe ingresar un valor para la Fecha Terminación.");
                        }
                        break;
                    case CntjConstantes.OTROSI_ADICION:
                    case CntjConstantes.OTROSI_ADICION_MODIFICACION:
                        bean.getObjetoOtrosi().setPlazoMeses(null);
                        bean.getObjetoOtrosi().setPlazoDias(null);
                        bean.getObjetoOtrosi().setFechaInicio(null);
                        bean.getObjetoOtrosi().setFechaTerminacion(null);
                        if (bean.getObjetoOtrosi().getValor() == null) {
                            bean.addError("Debe ingresar el Valor Otrosí");
                        }
                        break;
                    case CntjConstantes.OTROSI_PRORROGA_ADICION:
                    case CntjConstantes.OTROSI_PRORROGA_ADICION_MODIFICACION:
                        if (bean.getObjetoOtrosi().getPlazoMeses() == null) {
                            bean.addError("Debe ingresar un valor para el campo Plazo Prorroga (Meses).");
                        }
                        if (bean.getObjetoOtrosi().getPlazoDias() == null) {
                            bean.addError("Debe ingresar un valor para el campo Plazo Prorroga (Días).");
                        }
                        if (bean.getObjetoOtrosi().getValor() == null) {
                            bean.addError("Debe ingresar el Valor Otrosí");
                        }
                        if (bean.getObjetoOtrosi().getFechaInicio() == null) {
                            bean.addError("Debe ingresar un valor para la Fecha Inicio.");
                        }
                        if (bean.getObjetoOtrosi().getFechaTerminacion() == null) {
                            bean.addError("Debe ingresar un valor para la Fecha Terminación.");
                        }
                        break;
                    case CntjConstantes.OTROSI_MODIFICACION:
                        bean.getObjetoOtrosi().setValor(null);
                        bean.getObjetoOtrosi().setPlazoMeses(null);
                        bean.getObjetoOtrosi().setPlazoDias(null);
                        bean.getObjetoOtrosi().setFechaInicio(null);
                        bean.getObjetoOtrosi().setFechaTerminacion(null);
                        break;
                    default:
                        break;
                }
            }
            
            CntjOtrosi actaInicio = getCtnjOtrosiRemoto().otrosiActaInicio(bean.getObjeto().getId());
            if((actaInicio != null && !actaInicio.getId().equals(bean.getObjetoOtrosi().getId()))
                    && bean.getObjetoOtrosi().getTipo().equals(CntjConstantes.OTROSI_ACTA_INICIO)){
                bean.addError("Ya existe el acta de inicio.");
            }
            
            if((actaInicio != null && actaInicio.getId().equals(bean.getObjetoOtrosi().getId()))
                    && !bean.getObjetoOtrosi().getTipo().equals(CntjConstantes.OTROSI_ACTA_INICIO)){
                bean.addError("No pude cambiar el tipo del acta de inicio.");
            }

            String ruta = PropApl.getInstance().get(PropApl.CNTJ_RUTA_OTROSIES_ADJUNTOS);
            if (bean.getAdjuntosOtroSi().size() > 0) {                
                Files.createDirectories(Paths.get(ruta));
                if (!new File(ruta).exists()) {
                    bean.addError("La ruta indicada para almacenar los adjuntos no existe. ");
                }
            }
            

            if (!bean.getErrores().isEmpty()) {
                return;
            }

            bean.auditoriaModificar(bean.getObjetoOtrosi());
            getCtnjOtrosiRemoto().actualizar(bean.getObjetoOtrosi());

            //Eliminar adjuntos
            if (!bean.getListaAdjuntoeliminar().isEmpty()) {
                for (CntjOtrosiAdjunto adjunto : bean.getListaAdjuntoeliminar()) {
                    getCtnjOtrosiAdjuntoRemoto().eliminar(adjunto.getId());
                    eliminarArchivodirectorio(adjunto);
                }
            }

            List<CntjOtrosiAdjunto> nuevosAdjuntos = bean.getAdjuntosOtroSi().stream()
                    .filter(asistente -> asistente.getId() < 0)
                    .collect(Collectors.toList());

            for (CntjOtrosiAdjunto adjunto : nuevosAdjuntos) {
                Date fecha = new Date();
                Maestro maestroTipoArchivo = bean.getHashlistaTipoArchivo().get(adjunto.getMaetipoArchivoId());
                adjunto.setMaetipoArchivoCodigo(maestroTipoArchivo.getValor());
                adjunto.setMaetipoArchivoValor(maestroTipoArchivo.getNombre());
                adjunto.setOtrosiId(bean.getObjetoOtrosi());
                adjunto.setRuta(ruta);
                adjunto.setExiste(true);
                int indiceExtension = adjunto.getNombre().lastIndexOf(".");
                String extension = adjunto.getNombre().substring(indiceExtension, adjunto.getNombre().length());
                adjunto.setArchivo(String.format("%s%s%s", CntjConstantes.NOMBRE_ADJUNTO_OTROSI, CntjConstantes.formato6.format(fecha), extension));
                bean.auditoriaGuardar(adjunto);
                adjunto.setId(getCtnjOtrosiAdjuntoRemoto().insertar(adjunto));
                generarArchivo(adjunto);
            }
            //modificar plazo prorroga en contrato
            
            if (CntjConstantes.isProrroga(bean.getObjetoOtrosi().getTipo())) {
                Integer diferenciaDiasProrroga = (bean.getObjetoOtrosi().getPlazoDias() - bean.getDiasProrrogaAnterior());
                if (diferenciaDiasProrroga != 0) {
                    CntjContrato contrato = bean.getObjetoOtrosi().getContratoId();
                    contrato.setPlazoProrrogas(diferenciaDiasProrroga);
                    contrato.setFechaFin(bean.getObjetoOtrosi().getFechaTerminacion());
                    bean.auditoriaModificar(contrato);
                    getCtnjContratoRemoto().actualizarPlazoProrroga(contrato);
                }else{
                    if (!bean.getObjetoOtrosi().getFechaTerminacion().equals(bean.getFechaFinAnterior())) {
                        CntjContrato contrato = bean.getObjetoOtrosi().getContratoId();
                        contrato.setFechaFin(bean.getObjetoOtrosi().getFechaTerminacion());
                        bean.auditoriaModificar(contrato);
                        getCtnjContratoRemoto().actualizarFechaFin(contrato);
                    }                    
                }
                
                if (CntjConstantes.isAdicion(bean.getObjetoOtrosi().getTipo())) {
                    CntjContrato contrato = bean.getObjetoOtrosi().getContratoId();
                    bean.auditoriaModificar(contrato);
                    BigDecimal diferenciaAdicion = bean.getObjetoOtrosi().getValor().subtract(bean.getValorAdicionAnterior());
                    if (diferenciaAdicion.compareTo(BigDecimal.ZERO) != 0) {
                        contrato.setValorAdiciones(diferenciaAdicion);
                        getCtnjContratoRemoto().actualizarValorAdiciones(contrato);
                    }
                }
            } else if (CntjConstantes.isAdicion(bean.getObjetoOtrosi().getTipo())) {
                CntjContrato contrato = bean.getObjetoOtrosi().getContratoId();
                bean.auditoriaModificar(contrato);
                BigDecimal diferenciaAdicion = bean.getObjetoOtrosi().getValor().subtract(bean.getValorAdicionAnterior());
                if (diferenciaAdicion.compareTo(BigDecimal.ZERO) != 0) {
                    contrato.setValorAdiciones(diferenciaAdicion);
                    getCtnjContratoRemoto().actualizarValorAdiciones(contrato);
                }
            }         
            
            bean.addMensaje("Información modificada correctamente.");
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al modificar. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al modificar." + e.getMessage());
        }
    }

    private void eliminarArchivodirectorio(CntjOtrosiAdjunto adjunto) {
        try {
            File archivo = new File(adjunto.getRuta(), adjunto.getArchivo());
            if (archivo.exists()) {
                archivo.delete();
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al eliminar el archivo del directorio. %s", e.getMessage()), e);
        }
    }

    private void crearGarantia(ContratoBean bean) {
        try {
            bean.setObjetoGarantia(new CntjContratoGarantia());
            if(bean.getObjeto().getTipoAnticipo() == null){
                bean.addError("Debe seleccionar el tipo de anticipo para continuar.");
            }
            
            if (!bean.getErrores().isEmpty()) {
                return;
            }
            
            if (bean.getObjeto().getTipoAnticipo() == 2) {
                bean.getObjetoGarantia().setPorcentajeValorAnticipo(new BigDecimal(0));
                bean.getObjetoGarantia().setBloquearPorcentaje(true);
            }
            bean.setHashlistaValidacionGarantia(new HashMap<>());
            bean.getHashlistaValidacionGarantia().put(MaestroAccion.CNTJ_GARANTIA_VIGENCIA_HASTA, getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.CNTJ_GARANTIA_VIGENCIA_HASTA));
            bean.getHashlistaValidacionGarantia().put(MaestroAccion.GARANTIA_VIG_HASTA_4_MES, getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.GARANTIA_VIG_HASTA_4_MES));
            bean.getHashlistaValidacionGarantia().put(MaestroAccion.GARANTIA_VIG_HASTA_36_MES, getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.GARANTIA_VIG_HASTA_36_MES));
            bean.getHashlistaValidacionGarantia().put(MaestroAccion.GARANTIA_VIG_HASTA_60_MES, getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.GARANTIA_VIG_HASTA_60_MES));
            bean.getHashlistaValidacionGarantia().put(MaestroAccion.GARANTIA_VIG_HASTA_12_MES, getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.GARANTIA_VIG_HASTA_12_MES));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al realizar el proceso. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al realizar el proceso." + e.getMessage());
        }
    }

    private void agregarGarantia(ContratoBean bean) {
        try {
            Maestro maegarantia = bean.getHashlistaGarantiaContrato().get(bean.getObjetoGarantia().getMaeGarantiaContratoId());
            bean.getObjetoGarantia().setMaeGarantiaContratoCodigo(maegarantia.getValor());
            bean.getObjetoGarantia().setMaeGarantiaContratoValor(maegarantia.getNombre());                
                
            List<Maestro> maeGarantia = getMaestroRemoto().consultarMaestrosPorAccion(MaestroAccion.CNTJ_GARANTIA_APLICA_ANTICIPO);
            Maestro garantiaMaestro = maeGarantia.stream().filter(valor -> valor.getId().equals(bean.getObjetoGarantia().getMaeGarantiaContratoId())).findFirst().orElse(new Maestro());            
            if (garantiaMaestro.getId() != null) {                
                if (bean.getObjeto().getTipoAnticipo() == 0) {
                    bean.addError("No se puede agregar la garantía porque el campo Tipo Anticipo del contrato no tiene un valor valido.");
                }
                if (bean.getObjeto().getValorAnticipo() == null || bean.getObjeto().getValorAnticipo().compareTo(BigDecimal.ZERO) <= 0) {
                    bean.addError("No se puede agregar la garantía porque el campo Valor Anticipo del contrato no tiene un valor valido mayor a cero.");
                }
            }
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al realizar el proceso." + e.getMessage());
        }
    }

    private void verSeguimiento(ContratoBean bean) {
        try {
            bean.getObjeto().setCntjContratoSeguimientoList(getCntjContratoSeguimientoRemoto().consultarTodoPorContrato(bean.getObjeto().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un fallo al cargar los seguimientos, favor contactar al administrador");
        }
    }

    private void guardarSeguimiento(ContratoBean bean) {
        try {
            bean.getObjetoSeguimiento().setCntjContratoId(bean.getObjeto());
            bean.getObjetoSeguimiento().setCntjTerceroId(bean.getObjeto().getCntjTerceroId());
            if (bean.getObjeto().getCntjContratoSeguimientoList().isEmpty()) {
                bean.getObjetoSeguimiento().setFechaInicioSeguimiento(bean.getObjeto().getFechaInicio());
                bean.getObjetoSeguimiento().setFechaFinSeguimiento(bean.getObjeto().getFechaFin());
                bean.auditoriaGuardar(bean.getObjetoSeguimiento());
                bean.getObjetoSeguimiento().setId(getCntjContratoSeguimientoRemoto().insertar(bean.getObjetoSeguimiento()));
            } else {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                bean.getObjetoSeguimiento().setFechaInicioSeguimiento(calendar.getTime());
                CntjContratoSeguimiento ultimoSeguimiento = bean.getObjeto().getCntjContratoSeguimientoList().get(bean.getObjeto().
                        getCntjContratoSeguimientoList().size() - 1);
                ultimoSeguimiento.setFechaFinSeguimiento(new Date());
                bean.auditoriaModificar(ultimoSeguimiento);
                getCntjContratoSeguimientoRemoto().actualizar(ultimoSeguimiento);
                bean.auditoriaGuardar(bean.getObjetoSeguimiento());
                bean.getObjetoSeguimiento().setId(getCntjContratoSeguimientoRemoto().insertar(bean.getObjetoSeguimiento()));
            }
        } catch (Exception e) {
            bean.addError("Hubo un fallo al gurdar seguimiento, favor contactar al administrador");
        }
    }

    private void verInformes(ContratoBean bean) {
        try {
            bean.setObjeto(getCtnjContratoRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeto().setCntjContratoSupervisorList(getCntjContratoSupervisorRemoto().supervisoresContrato(bean.getObjeto().getId()));
            bean.getObjeto().setCntjContratoInformeList(getCntjContratoInformeRemoto().consultarTodosPorIdContrato(bean.getObjeto().getId()));
            bean.setExpedienteContrato(bean.getObjeto().getCntjExpedienteId() != null);  
        } catch (Exception e) {
            bean.addError("Hubo un fallo cargando los informes, favor contactar al administrador");
        }
    }

    private void crearInforme(ContratoBean bean) {
        try {
            Calendar calendar = Calendar.getInstance();
            bean.getObjeto().setCntjContratoSupervisorList(getCntjContratoSupervisorRemoto().supervisoresContrato(bean.getObjeto().getId()));
            CntjContratoSupervisor supervisor = null;
            if(!bean.getObjeto().getCntjContratoSupervisorList().isEmpty()){
                supervisor = bean.getObjeto().getCntjContratoSupervisorList().get(bean.getObjeto().getCntjContratoSupervisorList().size() - 1);
            }
            bean.setObjetoInforme(new CntjContratoInforme());
            bean.getObjetoInforme().setCntjContratoId(bean.getObjeto());
            bean.getObjetoInforme().setCntjContratoSupervisorId(supervisor);
            bean.getObjetoInforme().setCntjContratoInformeAdjuntoList(new ArrayList<>());
            bean.getObjetoInforme().setEstado(CntjContratoInforme.ESTADO_GENERADO);
            /*if (bean.getObjeto().getCntjContratoInformeList().isEmpty()) {
                bean.getObjetoInforme().setFechaInicioPeriodo(bean.getObjeto().getFechaInicio());
            } else {
                CntjContratoInforme ultimoInforme = bean.getObjeto().getCntjContratoInformeList().get(bean.getObjeto().getCntjContratoInformeList().size() - 1);
                if (ultimoInforme != null) {
                    calendar.setTime(ultimoInforme.getFechaFinPeriodo());
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    bean.getObjetoInforme().setFechaInicioPeriodo(calendar.getTime());
                } else {
                    bean.getObjetoInforme().setFechaInicioPeriodo(bean.getObjeto().getFechaInicio());
                }
            }
            calendar.setTime(bean.getObjetoInforme().getFechaInicioPeriodo());
            if (bean.getObjeto().getProceso().equals("1") && bean.getObjeto().getFormaPago().equals(1)) {
                calendar.add(Calendar.MONTH, 1);
                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            } else if (bean.getObjeto().getProceso().equals("1") && bean.getObjeto().getFormaPago().equals(2)) {
                calendar.add(Calendar.MONTH, 5);
                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            } else {
                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            }
            bean.getObjetoInforme().setFechaFinPeriodo(calendar.getTime());*/
        } catch (Exception e) {
            bean.addError("Hubo un fallo al crear informe, favor contactar al administrador");
        }
    }

    private void guardarInforme(ContratoBean bean) {
        try {
            String ruta = PropApl.getInstance().get(PropApl.CNTJ_RUTA_SEGUIMIENTOS_ADJUNTOS);
            Files.createDirectories(Paths.get(ruta));
            if (!new File(ruta).exists()) {
                bean.addError("La ruta indicada para almacenar los adjuntos no existe. ");
            }
            if(bean.getObjetoInforme().getCntjContratoSupervisorId() == null){
                bean.addError("No se ha encontrado supervisor para el informe. ");
            }
            
            if (!bean.getErrores().isEmpty()) {
                return;
            }
            
            
            bean.auditoriaGuardar(bean.getObjetoInforme());
            bean.getObjetoInforme().setId(getCntjContratoInformeRemoto().insertar(bean.getObjetoInforme()));

            for (CntjContratoInformeAdjunto adjunto : bean.getObjetoInforme().getCntjContratoInformeAdjuntoList()) {
                if (adjunto.getId() == null) {
                    adjunto.setCntjContratoInformeId(bean.getObjetoInforme());
                    Maestro maestroTipoArchivo = bean.getHashTipoArchivoAdjuntoInforme().get(adjunto.getMaeTipoArchivoId());
                    adjunto.setMaeTipoArchivoCodigo(maestroTipoArchivo.getValor());
                    adjunto.setMaeTipoArchivoValor(maestroTipoArchivo.getNombre());
                    adjunto.setRuta(ruta);
                    adjunto.setExiste(true);
                    int indiceExtension = adjunto.getNombre().lastIndexOf(".");
                    String extension = adjunto.getNombre().substring(indiceExtension, adjunto.getNombre().length());
                    adjunto.setArchivo(String.format("%s%s%s", CntjConstantes.NOMBRE_ADJUNTO_INFORME, CntjConstantes.formato6.format(new Date()), extension));
                    bean.auditoriaGuardar(adjunto);
                    if (generarArchivoInforme(adjunto)) {
                        adjunto.setId(getCntjContratoInformeAdjuntoRemoto().insertar(adjunto));
                    } else {
                        bean.addError("No se pudo guardar el adjunto en el servidor, favor contactar al administrador");
                    }
                }
            }
            bean.getObjeto().getCntjContratoInformeList().add(0, bean.getObjetoInforme());
            bean.addMensaje("Se guardo el informe con exito");
        } catch (Exception e) {
            bean.addError("Hubo un error al guardar un informe, favor contactar al administrador");
        }
    }

    private void editarInforme(ContratoBean bean) {
        try {
            bean.setObjetoInforme(getCntjContratoInformeRemoto().consultar(bean.getObjetoInforme().getId()));
            bean.getObjetoInforme().setCntjContratoInformeAdjuntoList(getCntjContratoInformeAdjuntoRemoto().consultarTodosPorIdContrato(bean.getObjetoInforme().getId()));
        } catch (Exception e) {
            bean.addError("Hubo un error al editar un informe, favor contactar al administrador");
        }
    }

    private void modificarInforme(ContratoBean bean) {
        try {
            bean.auditoriaModificar(bean.getObjetoInforme());
            if (bean.getObjetoInforme().getEstado() == CntjContratoInforme.ESTADO_APROBADO) {
                bean.getObjetoInforme().setFechaAprobacion(new Date());
            } else if (bean.getObjetoInforme().getEstado() == CntjContratoInforme.ESTADO_DEVUELTO) {
                bean.getObjetoInforme().setFechaAprobacion(null);
                for (CntjContratoInformeAdjunto adjunto : bean.getObjetoInforme().getCntjContratoInformeAdjuntoList()) {
                    if (adjunto.getId() == null) {
                        bean.getObjetoInforme().setEstado(CntjContratoInforme.ESTADO_GENERADO);
                    }
                }
            }
            getCntjContratoInformeRemoto().actualizar(bean.getObjetoInforme());
            String ruta = PropApl.getInstance().get(PropApl.CNTJ_RUTA_SEGUIMIENTOS_ADJUNTOS);
            for (CntjContratoInformeAdjunto adjunto : bean.getObjetoInforme().getCntjContratoInformeAdjuntoList()) {
                if (adjunto.getId() == null) {
                    adjunto.setCntjContratoInformeId(bean.getObjetoInforme());
                    Maestro maestroTipoArchivo = bean.getHashTipoArchivoAdjuntoInforme().get(adjunto.getMaeTipoArchivoId());
                    adjunto.setMaeTipoArchivoCodigo(maestroTipoArchivo.getValor());
                    adjunto.setMaeTipoArchivoValor(maestroTipoArchivo.getNombre());
                    adjunto.setRuta(ruta);
                    adjunto.setExiste(true);
                    int indiceExtension = adjunto.getNombre().lastIndexOf(".");
                    String extension = adjunto.getNombre().substring(indiceExtension, adjunto.getNombre().length());
                    adjunto.setArchivo(String.format("%s%s%s", CntjConstantes.NOMBRE_ADJUNTO_INFORME, CntjConstantes.formato6.format(new Date()), extension));
                    bean.auditoriaGuardar(adjunto);
                    if (generarArchivoInforme(adjunto)) {
                        adjunto.setId(getCntjContratoInformeAdjuntoRemoto().insertar(adjunto));
                    } else {
                        bean.addError("No se pudo guardar el adjunto en el servidor, favor contactar al administrador");
                    }
                }

            }
            bean.getObjeto().getCntjContratoInformeList().stream()
                    .filter(obj -> obj.getId().equals(bean.getObjetoInforme().getId()))
                    .findFirst()
                    .ifPresent(obj->{
                        obj.setEstado(bean.getObjetoInforme().getEstado());
                        obj.setFechaAprobacion(bean.getObjetoInforme().getFechaAprobacion());
                        obj.setObservaciones(bean.getObjetoInforme().getObservaciones());
                    });
            bean.addMensaje("Se modifico con informe con exito");
        } catch (Exception e) {
            bean.addError("Hubo un error al modificar un informe, favor contactar al adminsitrador");
        }
    }
    
    private void validarCreacionOtrosi(ContratoBean bean) {
        try {
            bean.setObjeto(getCtnjContratoRemoto().consultar(bean.getObjeto().getId()));   
            Maestro maeEstadoContrato = MaestroSingle.getInstance().consultarMaestro(bean.getObjeto().getMaeEstadoContratoId());
            bean.setContratoVigente(maeEstadoContrato.contieneAccion(MaestroAccion.CNTJ_ESTADO_CONRATO_VIGENTE));
            bean.setExpedienteContrato(bean.getObjeto().getCntjExpedienteId() != null);            
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al consultar información.");
        }
    }

    private void consultarResponsableSeguimiento(ContratoBean bean) {
        try {            
            bean.setObjetoSupervisor(getCntjContratoSupervisorRemoto().consultar(bean.getObjetoSupervisor().getId()));
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al consultar responsable seguimiento.");
        }
    }

    private void verDetalleGarantia(ContratoBean bean) {
        try {            
            bean.setObjetoGarantia(getCntjContratoGarantiaRemoto().consultar(bean.getObjetoGarantia().getId()));
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al consultar detalle garantia.");
        }
    }

    private void consultarDocumentos(ContratoBean bean) {
        try {
            bean.setObjeto(getCtnjContratoRemoto().consultar(bean.getObjeto().getId()));
            if (bean.getObjeto().getCntjExpedienteId() != null && bean.getObjeto().getCntjExpedienteId().getId() != null) {
                bean.getParamConsulta(3).getFiltros().put(CntjConstantes.ID_EXPEDIENTE, bean.getObjeto().getCntjExpedienteId().getId());
                bean.getParamConsulta(3).setCantidadRegistros(getCntjDocumentoRemoto().consultarCantidadLista(bean.getParamConsulta(3)));
                bean.setListaDocumentos(getCntjDocumentoRemoto().consultarLista(bean.getParamConsulta(3)));
            }            
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al listar información %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al listar información.");
        }
    }
    
    private void consultarDocumento(ContratoBean bean) {
        try {
            bean.setObjDocumento(getCntjDocumentoRemoto().consultar(bean.getObjDocumento().getId()));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al consultar informacion. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al consultar informacion.");
        }
    }

    private void crearEventoContractual(ContratoBean bean, Integer tipoProceso) {
        try {
            CntjProceso proceso = getCtnjProcesoRemoto().getProcesoPorTipo(tipoProceso);
            if(proceso == null){
                bean.addError(String.format("No se encontró preceso de tipo %s para creación del expediente.", CntjConstantes.getTipoProceso(tipoProceso)));
            }
            
            CntjEstado estadoInicio = null;
            if (proceso != null) {
                estadoInicio = getCtnjEstadoRemoto().consultarEstadoInicio(proceso.getId(), bean.getConexion().getUsuario().getId());
                if (estadoInicio == null) {
                    bean.addError(String.format("No se encontró un estado de inicio activo para el preceso %s, valide que esté creado el estado de inicio , que se encuentre activo y que usted pertenezca al grupo asignado .", CntjConstantes.getTipoProceso(tipoProceso)));
                }
            }
            
                        
            if (!bean.getErrores().isEmpty()) {
                return;
            }
            
            CntjExpediente expedienteNuevo = new CntjExpediente();
            CntjExpediente expedientePadre = getCntjExpedienteRemoto().consultar(bean.getObjeto().getCntjExpedienteId().getId());
            
            //general el numero del expediente
            int anioActual = LocalDate.now().getYear();
            Integer ultimoNumeroExpediente = getCntjExpedienteRemoto().ultimoNumeroExpediente(anioActual);
            
            if (ultimoNumeroExpediente != null) {
                expedienteNuevo.setNumeroExpediente(String.format("%s-%s-%s", anioActual, CntjConstantes.getTipoProceso(proceso.getTipoProceso()).subSequence(0, 3).toString().toUpperCase(), String.format("%06d", (ultimoNumeroExpediente + 1))));
            } else {
                expedienteNuevo.setNumeroExpediente(String.format("%s-%s-%s", anioActual, CntjConstantes.getTipoProceso(proceso.getTipoProceso()).toString().toUpperCase(), String.format("%06d", 1)));
            }
            
            boolean continuar = true;
            try {
                expedienteNuevo.setProcesoId(proceso);
                expedienteNuevo.setContrato(bean.getObjeto().getContrato());
                expedienteNuevo.setExpedienteId(bean.getObjeto().getCntjExpedienteId());
                expedienteNuevo.setEstadoActual(estadoInicio);
                expedienteNuevo.setFechaEjecucionEstado(new Date());
                expedienteNuevo.setUsuarioPropietario(bean.getConexion().getUsuario());
                expedienteNuevo.setJsonData(expedientePadre.getJsonData());
                bean.auditoriaGuardar(expedienteNuevo);
                expedienteNuevo.setId(getCntjExpedienteRemoto().insertar(expedienteNuevo));
            } catch (Exception e) {
                continuar = false;
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente guardar expediente. %s", e.getMessage()), e);
                bean.addError("Se presento inconveniente guardar expediente.");
            }
            
            CntjEstadoEjecucion estadoEjecucion = null;
            if (continuar) {
                try {
                    //se establece el primer estado inicial en estado ejecucion 
                    estadoEjecucion = new CntjEstadoEjecucion();
                    estadoEjecucion.setCntjExpedienteId(expedienteNuevo);
                    estadoEjecucion.setCntjEstadoId(estadoInicio);
                    estadoEjecucion.setObservacion(CntjConstantes.OBSERVACION_CREACION_EXPEDIENTE);
                    estadoEjecucion.setFechaEjecucion(expedienteNuevo.getFechaEjecucionEstado());
                    estadoEjecucion.setGnUsuariosId(bean.getConexion().getUsuario());
                    bean.auditoriaGuardar(estadoEjecucion);
                    int idejecucion = getCntjEstadoEjecucionRemoto().insertar(estadoEjecucion);
                    estadoEjecucion.setId(idejecucion);
                } catch (Exception e) {
                    continuar = false;
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente guardar el estado actual del expediente. %s", e.getMessage()), e);
                    bean.addError("Se presento inconveniente guardar el estado actual del expediente.");
                    getCntjExpedienteRemoto().eliminar(expedienteNuevo.getId());
                }
            }
                    
            CntjExpedienteResponsable expedienteResponsable = null;
            if(continuar){
                try {
                    expedienteResponsable = new CntjExpedienteResponsable();
                    expedienteResponsable.setRol(CntjConstantes.ROL_PROPIETARIO);
                    expedienteResponsable.setUsuarioId(expedienteNuevo.getUsuarioPropietario());
                    expedienteResponsable.setFechaInicial(bean.getObjeto().getFechaHoraCrea());
                    expedienteResponsable.setExpedienteId(expedienteNuevo);
                    bean.auditoriaGuardar(expedienteResponsable);
                    int idResponsable = getCntjExpedienteResponsableRemoto().insertar(expedienteResponsable);
                    expedienteResponsable.setId(idResponsable);
                } catch (Exception e) {
                    continuar = false;
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente reistrar el propietario del expediente. %s", e.getMessage()), e);
                    bean.addError("Se presento inconveniente reistrar el propietario del expediente.");
                    getCntjEstadoEjecucionRemoto().eliminar(estadoEjecucion.getId());
                    getCntjExpedienteRemoto().eliminar(expedienteNuevo.getId());
                }
            }

            if (continuar) {
                List<Integer> enviadas = new ArrayList<>();
                try {
                    //Se establece notificacion al grupo del estado.
                    List<CntjUsuarioGrupo> listaUsuario = getCtnjUsuarioGrupoRemoto().listarUsuariosGrupoPermisos(estadoInicio.getId());
                    for (CntjUsuarioGrupo usuario : listaUsuario) {
                        GnAlerta alerta = CntjConstantes.getAlertaGestionExpediente(usuario.getGnUsuarioId(), estadoEjecucion.getFechaHoraCrea(), expedienteNuevo.getNumeroExpediente(), estadoInicio.getNombre(), bean.getConexion().getUsuario().getNombre(), estadoEjecucion.getObservacion());
                        int id = getGnAlertaRemoto().insertar(alerta);
                        enviadas.add(id);
                    }
                } catch (Exception e) {
                    continuar = false;
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente enviar notificacion a los usuarios del grupo. %s", e.getMessage()), e);
                    bean.addError("Se presento inconveniente enviar notificacion a los usuarios del grupo.");
                    getCntjEstadoEjecucionRemoto().eliminar(estadoEjecucion.getId());
                    getCntjExpedienteRemoto().eliminar(expedienteNuevo.getId());
                    getCntjExpedienteResponsableRemoto().eliminar(expedienteResponsable.getId());
                    for (Integer item : enviadas) {
                        getGnAlertaRemoto().eliminar(item);
                    }
                }
            }

            if (continuar) {
                bean.addMensaje("Información guardada correctamente.");
            }
            
            
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al realizar el proceso. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al realizar el proceso.");
        }
    }
    
}
