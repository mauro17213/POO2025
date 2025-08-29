package com.saviasaludeps.savia.web.juridico.servicio;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.saviasaludeps.savia.dominio.administracion.GnAlerta;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.juridico.CntjCampo;
import com.saviasaludeps.savia.dominio.juridico.CntjCampoGestion;
import com.saviasaludeps.savia.dominio.juridico.CntjContrato;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoGarantia;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoIndicador;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoObligacion;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoSeguimiento;
import com.saviasaludeps.savia.dominio.juridico.CntjContratoSupervisor;
import com.saviasaludeps.savia.dominio.juridico.CntjDocumento;
import com.saviasaludeps.savia.dominio.juridico.CntjEstado;
import com.saviasaludeps.savia.dominio.juridico.CntjEstadoEjecucion;
import com.saviasaludeps.savia.dominio.juridico.CntjEstadoProcesoDocumento;
import com.saviasaludeps.savia.dominio.juridico.CntjExpediente;
import com.saviasaludeps.savia.dominio.juridico.CntjExpedienteResponsable;
import com.saviasaludeps.savia.dominio.juridico.CntjPlantilla;
import com.saviasaludeps.savia.dominio.juridico.CntjProceso;
import com.saviasaludeps.savia.dominio.juridico.CntjTercero;
import com.saviasaludeps.savia.dominio.juridico.CntjUsuarioGrupo;
import com.saviasaludeps.savia.negocio.administracion.GnAlertaRemoto;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjCampoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjContratoGarantiaRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjContratoIndicadorRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjContratoObligacionRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjContratoSeguimientoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjContratoSupervisorRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjDocumentoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjEstadoEjecucionRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjEstadoGrupoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjEstadoPlantillaRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjEstadoProcesoDocumentoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjExpedienteRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjExpedienteResponsableRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjPlantillaCampoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CntjPlantillaRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjContratoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjEstadoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjProcesoRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjTerceroRemoto;
import com.saviasaludeps.savia.negocio.juridico.CtnjUsuarioGrupoRemoto;
import com.saviasaludeps.savia.web.juridico.bean.ExpedienteBean;
import com.saviasaludeps.savia.web.juridico.utilidades.CntjConstantes;
import com.saviasaludeps.savia.web.juridico.utilidades.CntjUtilidades;
import com.saviasaludeps.savia.web.singleton.MaestroSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.Fichero;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;

/**
 *
 * @author idbohorquez
 */
public class ExpedienteServicioImpl extends AccionesBO implements ExpedienteServicioIface {

    private CtnjProcesoRemoto getCtnjProcesoRemoto() throws Exception {
        return (CtnjProcesoRemoto) RemotoEJB.getEJBRemoto("CntjProcesoServicio", CtnjProcesoRemoto.class.getName());
    }

    private CntjExpedienteRemoto getCntjExpedienteRemoto() throws Exception {
        return (CntjExpedienteRemoto) RemotoEJB.getEJBRemoto("CntjExpedienteServicio", CntjExpedienteRemoto.class.getName());
    }

    private CtnjEstadoRemoto getCtnjEstadoRemoto() throws Exception {
        return (CtnjEstadoRemoto) RemotoEJB.getEJBRemoto("CntjEstadoServicio", CtnjEstadoRemoto.class.getName());
    }

    private CntjEstadoEjecucionRemoto getCntjEstadoEjecucionRemoto() throws Exception {
        return (CntjEstadoEjecucionRemoto) RemotoEJB.getEJBRemoto("CntjEstadoEjecucionServicio", CntjEstadoEjecucionRemoto.class.getName());
    }

    private GnAlertaRemoto getGnAlertaRemoto() throws Exception {
        return (GnAlertaRemoto) RemotoEJB.getEJBRemoto("GnAlertaServicio", GnAlertaRemoto.class.getName());
    }

    private CtnjUsuarioGrupoRemoto getCtnjUsuarioGrupoRemoto() throws Exception {
        return (CtnjUsuarioGrupoRemoto) RemotoEJB.getEJBRemoto("CntjUsuarioGrupoServicio", CtnjUsuarioGrupoRemoto.class.getName());
    }

    private CntjEstadoGrupoRemoto getCntjEstadoGrupoRemoto() throws Exception {
        return (CntjEstadoGrupoRemoto) RemotoEJB.getEJBRemoto("CntjEstadoGrupoServicio", CntjEstadoGrupoRemoto.class.getName());
    }

    private CntjCampoRemoto getCntjCampoRemoto() throws Exception {
        return (CntjCampoRemoto) RemotoEJB.getEJBRemoto("CntjCampoServicio", CntjCampoRemoto.class.getName());
    }

    private CntjEstadoPlantillaRemoto getCntjEstadoPlantillaRemoto() throws Exception {
        return (CntjEstadoPlantillaRemoto) RemotoEJB.getEJBRemoto("CntjEstadoPlantillaServicio", CntjEstadoPlantillaRemoto.class.getName());
    }

    private CntjDocumentoRemoto getCntjDocumentoRemoto() throws Exception {
        return (CntjDocumentoRemoto) RemotoEJB.getEJBRemoto("CntjDocumentoServicio", CntjDocumentoRemoto.class.getName());
    }

    private CntjEstadoProcesoDocumentoRemoto getCntjEstadoProcesoDocumentoRemoto() throws Exception {
        return (CntjEstadoProcesoDocumentoRemoto) RemotoEJB.getEJBRemoto("CntjEstadoProcesoDocumentoServicio", CntjEstadoProcesoDocumentoRemoto.class.getName());
    }

    private CntjPlantillaRemoto getCntjPlantillaRemoto() throws Exception {
        return (CntjPlantillaRemoto) RemotoEJB.getEJBRemoto("CntjPlantillaServicio", CntjPlantillaRemoto.class.getName());
    }

    private CntjPlantillaCampoRemoto getCntjPlantillaCampoRemoto() throws Exception {
        return (CntjPlantillaCampoRemoto) RemotoEJB.getEJBRemoto("CntjPlantillaCampoServicio", CntjPlantillaCampoRemoto.class.getName());
    }

    private CtnjContratoRemoto getCtnjContratoRemoto() throws Exception {
        return (CtnjContratoRemoto) RemotoEJB.getEJBRemoto("CntjContratoServicio", CtnjContratoRemoto.class.getName());
    }

    private CtnjTerceroRemoto getCtnjTerceroRemoto() throws Exception {
        return (CtnjTerceroRemoto) RemotoEJB.getEJBRemoto("CntjTerceroServicio", CtnjTerceroRemoto.class.getName());
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

    private CntjContratoSeguimientoRemoto getCntjContratoSeguimientoRemoto() throws Exception {
        return (CntjContratoSeguimientoRemoto) RemotoEJB.getEJBRemoto("CntjContratoSeguimientoServicio", CntjContratoSeguimientoRemoto.class.getName());
    }

    private CntjExpedienteResponsableRemoto getCntjExpedienteResponsableRemoto() throws Exception {
        return (CntjExpedienteResponsableRemoto) RemotoEJB.getEJBRemoto("CntjExpedienteResponsableServicio", CntjExpedienteResponsableRemoto.class.getName());
    }

    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    @Override
    public void Accion(ExpedienteBean bean) {
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
                    break;                
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_VER:
                            gestionar(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarGestion(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            listarTerceros(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            crearCampo(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            agregarCampo(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            listarMaestro(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_4:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarDocumentos(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            descargaAgrupada(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_5:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            crearAdjunto(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_6:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarResponsablesExpediente(bean);
                            break;
                        case Url.ACCION_CREAR:
                            crearResponsablesExpediente(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                            guardarResponsable(bean);
                            break;
                        case Url.ACCION_BORRAR:
                            borrarResponsable(bean);
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            consultarUsuarios(bean);
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            consultarUltimoResponsable(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_7:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarTrazabilidad(bean);
                            break;
                    }
                    break;                
                case Url.ACCION_ADICIONAL_8:
                    consultarDocumento(bean);
                    break;
                case Url.ACCION_ADICIONAL_9:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            listarEventoContractual(bean);
                            break;
                    }
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    @Override
    public void cargaInicial(ExpedienteBean bean) {
        try {
            bean.setListaProceso(getCtnjProcesoRemoto().getProcesos());
            bean.setHashlistaModalidad(MaestroSingle.getInstance().getHashPorTipo(MaestroTipo.CNT_MODALIDAD));
            bean.setHashlistaClaseContrato(MaestroSingle.getInstance().getHashPorTipo(MaestroTipo.CNTJ_CONTRATOS_CLASES));
            bean.setHashlistaRegimen(MaestroSingle.getInstance().getHashPorTipo(MaestroTipo.GN_REGIMEN));
            bean.setHashlistaEstadoContrato(MaestroSingle.getInstance().getHashPorTipo(MaestroTipo.CNTJ_CONTRATO_ESTADO));
            bean.setHashlistaGarantiaContrato(MaestroSingle.getInstance().getHashPorTipo(MaestroTipo.CNTJ_CONTRATOS_GARANTIAS));
            bean.setListaRol(CntjConstantes.getListaRol());
        } catch (Exception e) {
            Logger.getLogger(TerceroServicioImpl.class.getName()).log(Level.SEVERE, String.format("Se presento inconveniente al cargas información inicial. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al cargas información inicial.");
        }
    }

    private void listar(ExpedienteBean bean) {
        try {
            if(bean.isAccionAdicional1()){
                //Ver todos los registros
                bean.getParamConsulta().setParametroConsulta1(null);
            }else{
                //ver registros por usuario
                bean.getParamConsulta().setParametroConsulta1(bean.getConexion().getUsuario().getId());
            }
            bean.getParamConsulta().setParametroConsulta2(null);
            bean.getParamConsulta().setCantidadRegistros(getCntjExpedienteRemoto().consultarCantidadLista(bean.getParamConsulta()));
            bean.setRegistros(getCntjExpedienteRemoto().consultarLista(bean.getParamConsulta()));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al listar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al listar información." + e.getMessage());
        }
    }

    private void ver(ExpedienteBean bean) {
        try {
            bean.setObjeto(getCntjExpedienteRemoto().consultar(bean.getObjeto().getId()));
            bean.setListaCamposTransicion(new ArrayList<>());
            JSONObject objeto = new JSONObject(bean.getObjeto().getJsonData() != null ? bean.getObjeto().getJsonData() : "{}");
            JSONArray arrayObjeto = new JSONArray();
            if (objeto.has(CntjConstantes.CAMPOS)) {
                arrayObjeto = objeto.getJSONArray(CntjConstantes.CAMPOS);
            }
            for (int index = 0; index < arrayObjeto.length(); index++) {
                JSONObject obj = arrayObjeto.getJSONObject(index);
                CntjCampo campo = new CntjCampo();
                campo.setNombre(obj.getString(CntjConstantes.NOMBRE));
                campo.setTipoDato(obj.getInt(CntjConstantes.TIPO));                
                if (obj.has(CntjConstantes.VALOR_STR)) {                    
                    campo.setValor(obj.getString(CntjConstantes.VALOR_STR));                   
                }else if(obj.has(CntjConstantes.VALOR)){
                    campo.setValor(obj.getString(CntjConstantes.VALOR)); 
                } 
                bean.getListaCamposTransicion().add(campo);
            }
        } catch (Exception e) {
            bean.addError("Se presento inconveniente al consultar información.");
        }
    }

    private void crear(ExpedienteBean bean) {
        try {
            bean.setObjeto(new CntjExpediente());
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al realizar proceso. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al realizar proceso.");
        }
    }

    private void guardar(ExpedienteBean bean) {
        try {
            if (bean.getObjeto().getProcesoId() == null) {
                bean.addError("Debe seleccionar el proceso para guardar el expediente.");
            }

            if (!bean.getErrores().isEmpty()) {
                return;
            }

            Optional<CntjProceso> procesoId = bean.getListaProceso().stream()
                    .filter(v -> v.getId().equals(bean.getObjeto().getProcesoId().getId()))
                    .findFirst();

            //general el numero del expediente
            int anioActual = LocalDate.now().getYear();
            Integer ultimoNumeroExpediente = getCntjExpedienteRemoto().ultimoNumeroExpediente(anioActual);
            
            if (ultimoNumeroExpediente != null) {
                bean.getObjeto().setNumeroExpediente(String.format("%s-%s-%s", anioActual, CntjConstantes.getTipoProceso(procesoId.get().getTipoProceso()).subSequence(0, 3).toString().toUpperCase(), String.format("%06d", (ultimoNumeroExpediente + 1))));
            } else {
                bean.getObjeto().setNumeroExpediente(String.format("%s-%s-%s", anioActual, CntjConstantes.getTipoProceso(procesoId.get().getTipoProceso()).subSequence(0, 3).toString().toUpperCase(), String.format("%06d", 1)));
            }

            boolean continuar = true;

            CntjEstado estadoInicio = getCtnjEstadoRemoto().consultarEstadoInicio(bean.getObjeto().getProcesoId().getId(), bean.getConexion().getUsuario().getId());
            if (estadoInicio == null) {
                bean.addError("No se encontro un estado de inicio activo, valide que este creado el estado de inicio y que se encuentre activo.");
                continuar = false;
            }

            if (continuar) {
                try {
                    bean.getObjeto().setEstado(estadoInicio.getNombre());
                    bean.getObjeto().setEstadoActual(estadoInicio);
                    bean.getObjeto().setFechaEjecucionEstado(new Date());
                    bean.getObjeto().setUsuarioPropietario(bean.getConexion().getUsuario());
                    bean.auditoriaGuardar(bean.getObjeto());
                    bean.getObjeto().setId(getCntjExpedienteRemoto().insertar(bean.getObjeto()));
                } catch (Exception e) {
                    continuar = false;
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente guardar expediente. %s", e.getMessage()), e);
                    bean.addError("Se presento inconveniente guardar expediente.");
                }
            }

            if (continuar) {
                try {
                    //se establece el primer estado inicial en estado ejecucion 
                    bean.setObjeToEjecucion(new CntjEstadoEjecucion());
                    bean.getObjeToEjecucion().setCntjExpedienteId(bean.getObjeto());
                    bean.getObjeToEjecucion().setCntjEstadoId(estadoInicio);
                    bean.getObjeToEjecucion().setObservacion(CntjConstantes.OBSERVACION_CREACION_EXPEDIENTE);
                    bean.getObjeToEjecucion().setFechaEjecucion(bean.getObjeto().getFechaEjecucionEstado());
                    bean.getObjeToEjecucion().setGnUsuariosId(bean.getConexion().getUsuario());
                    bean.auditoriaGuardar(bean.getObjeToEjecucion());
                    int idejecucion = getCntjEstadoEjecucionRemoto().insertar(bean.getObjeToEjecucion());
                    bean.getObjeToEjecucion().setId(idejecucion);
                } catch (Exception e) {
                    continuar = false;
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente guardar el estado actual del expediente. %s", e.getMessage()), e);
                    bean.addError("Se presento inconveniente guardar el estado actual del expediente.");
                    getCntjExpedienteRemoto().eliminar(bean.getObjeto().getId());
                }
            }
                        
            if(continuar){
                try {
                    bean.setObjetoResponsable(new CntjExpedienteResponsable());
                    bean.getObjetoResponsable().setRol(CntjConstantes.ROL_PROPIETARIO);
                    bean.getObjetoResponsable().setUsuarioId(bean.getObjeto().getUsuarioPropietario());
                    bean.getObjetoResponsable().setFechaInicial(bean.getObjeto().getFechaHoraCrea());
                    bean.getObjetoResponsable().setExpedienteId(bean.getObjeto());
                    bean.auditoriaGuardar(bean.getObjetoResponsable());
                    int idResponsable = getCntjExpedienteResponsableRemoto().insertar(bean.getObjetoResponsable());
                    bean.getObjetoResponsable().setId(idResponsable);
                } catch (Exception e) {
                    continuar = false;
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente reistrar el propietario del expediente. %s", e.getMessage()), e);
                    bean.addError("Se presento inconveniente reistrar el propietario del expediente.");
                    getCntjEstadoEjecucionRemoto().eliminar(bean.getObjeToEjecucion().getId());
                    getCntjExpedienteRemoto().eliminar(bean.getObjeto().getId());
                }
            }

            if (continuar) {
                List<Integer> enviadas = new ArrayList<>();
                try {
                    //Se establece notificacion al grupo del estado.
                    List<CntjUsuarioGrupo> listaUsuario = getCtnjUsuarioGrupoRemoto().listarUsuariosGrupoPermisos(estadoInicio.getId());
                    for (CntjUsuarioGrupo usuario : listaUsuario) {
                        GnAlerta alerta = CntjConstantes.getAlertaGestionExpediente(usuario.getGnUsuarioId(), bean.getObjeToEjecucion().getFechaHoraCrea(), bean.getObjeto().getNumeroExpediente(), estadoInicio.getNombre(), bean.getConexion().getUsuario().getNombre(), bean.getObjeToEjecucion().getObservacion());
                        int id = getGnAlertaRemoto().insertar(alerta);
                        enviadas.add(id);
                    }
                } catch (Exception e) {
                    continuar = false;
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente enviar notificacion a los usuarios del grupo. %s", e.getMessage()), e);
                    bean.addError("Se presento inconveniente enviar notificacion a los usuarios del grupo.");
                    getCntjEstadoEjecucionRemoto().eliminar(bean.getObjeToEjecucion().getId());
                    getCntjExpedienteRemoto().eliminar(bean.getObjeto().getId());
                    getCntjExpedienteResponsableRemoto().eliminar(bean.getObjetoResponsable().getId());
                    for (Integer item : enviadas) {
                        getGnAlertaRemoto().eliminar(item);
                    }
                }
            }

            if (continuar) {
                bean.addMensaje("Información guardada correctamente.");
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al guardar.");
        }
    }

    private void listarTrazabilidad(ExpedienteBean bean) {
        try {
            bean.setListaEstadoEjecucion(getCntjEstadoEjecucionRemoto().listaEjecucionExpediente(bean.getObjeto().getId()));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al listar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al listar información." + e.getMessage());
        }
    }

    private void gestionar(ExpedienteBean bean) {
        try {
            bean.setObjeToEjecucion(new CntjEstadoEjecucion());
            bean.setObjeto(getCntjExpedienteRemoto().consultar(bean.getObjeto().getId()));
            bean.getObjeToEjecucion().setCntjExpedienteId(bean.getObjeto());
            bean.getObjeToEjecucion().setFechaEjecucion(new Date());
            bean.setListaEstadoSiguiente(getCtnjEstadoRemoto().consultarEstadoSiguientes(bean.getObjeto().getId(), bean.getConexion().getUsuario().getId()));
            bean.setHashListaEstadoSiguiente(CntjConstantes.convertEstadosToHash(bean.getListaEstadoSiguiente()));
            bean.setListaCamposTransicion(new ArrayList<>());
            bean.setListaAdjuntos(new ArrayList<>());
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al consultar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al consultar información.");
        }
    }

    private void guardarGestion(ExpedienteBean bean) {
        try {
            if (bean.getObjeToEjecucion().getCntjEstadoId().getId() == null) {
                bean.addError("Debe seleccionar el Paso Permitido.");
            } else {
                List<CntjEstadoProcesoDocumento> docEstado = getCntjEstadoProcesoDocumentoRemoto().listaDocumentoEstadoAdjuntos(bean.getObjeToEjecucion().getCntjEstadoId().getId());
                for (CntjEstadoProcesoDocumento item : docEstado) {
                    boolean existe = bean.getListaAdjuntos().stream()
                            .anyMatch(elemento -> elemento.getEstadoDocumento().getId().equals(item.getId()));
                    if (!existe) {
                        bean.addError("No se han adjuntado todos los documentos requeridos.");
                        break;
                    }
                }

                List<CntjCampo> camposRequeridos = getCntjCampoRemoto().listaCamposDocumentoEstadoGenerados(bean.getObjeToEjecucion().getCntjEstadoId().getId());
                List<CntjCampo> camposExistentes = CntjUtilidades.getArrayCampos(bean.getObjeto().getJsonData());
                //Se filtran solo los campos que no esten ya agregados al expediente
                List<CntjCampo> camposValidar = camposRequeridos.stream()
                        .filter(item -> !camposExistentes.contains(item))
                        .collect(Collectors.toList());
                
                for (CntjCampo item : camposValidar) {
                    boolean existe = bean.getListaCamposTransicion().stream()
                            .anyMatch(elemento -> elemento.getEtiqueta().equals(item.getEtiqueta()));
                    if (!existe) {
                        bean.addError("No se han diligenciado todos los campos requeridos.");
                        break;
                    }
                }

            }

            if (bean.getObjeToEjecucion().getFechaEjecucion() == null) {
                bean.addError("Debe elegir la Fecha de Ejecución");
            }

            if (!bean.getErrores().isEmpty()) {
                return;
            }

            CntjEstado estadoSiguiente= getCtnjEstadoRemoto().consultar(bean.getObjeToEjecucion().getCntjEstadoId().getId());

            //Se arma el jsondata para almacenar
            JSONObject objeto = new JSONObject(bean.getObjeto().getJsonData() != null ? bean.getObjeto().getJsonData() : "{}");
            JSONArray arrayObjeto = new JSONArray();
            if (objeto.has(CntjConstantes.CAMPOS)) {
                arrayObjeto = objeto.getJSONArray(CntjConstantes.CAMPOS);
            }
            for (CntjCampo campo : bean.getListaCamposTransicion()) {
                JSONObject objCampo = new JSONObject();
                objCampo.put(CntjConstantes.ETIQUETA, campo.getEtiqueta());
                objCampo.put(CntjConstantes.NOMBRE, campo.getNombre());
                objCampo.put(CntjConstantes.VALOR, campo.getValor());
                objCampo.put(CntjConstantes.VALOR_STR, campo.getValorStr());
                objCampo.put(CntjConstantes.TABLA, campo.getTabla());
                objCampo.put(CntjConstantes.CAMPO, campo.getCampo());
                objCampo.put(CntjConstantes.TIPO, campo.getTipoDato());
                objCampo.put(CntjConstantes.IDCAMPO, campo.getId());
                Integer existeCampo = CntjUtilidades.getIndiceCampoExistente(objeto.toString(), campo.getId());
                if(existeCampo != null){
                    arrayObjeto.put(existeCampo, objCampo);
                }else{
                    arrayObjeto.put(objCampo);
                }                
            }
            objeto.put(CntjConstantes.CAMPOS, arrayObjeto);
            bean.getObjeto().setJsonData(objeto.toString());

            //se valida si se debe crear un contrato
            if (estadoSiguiente.getTipo().equals(CntjConstantes.TIPO_CONTRATO)) {
                CntjContrato contrato = CntjUtilidades.parsearJsonContrato(objeto.toString());
                try {
                    validarDatosContratos(bean, contrato);
                } catch (Exception e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al validar información para creación de contrato. %s", e.getMessage()), e);
                    bean.addError("Se presento inconveniente al validar información para creación de contrato.");
                }
            }

            if (!bean.getErrores().isEmpty()) {
                return;
            }

            //switch en caso de excepciones en el proceso normal
            boolean continuar = true;

            bean.getObjeToEjecucion().setCntjExpedienteId(bean.getObjeto());
            bean.getObjeToEjecucion().setGnUsuariosId(bean.getConexion().getUsuario());
            bean.auditoriaGuardar(bean.getObjeToEjecucion());
            try {
                //Se guarda la ejecucion 
                bean.getObjeToEjecucion().setId(getCntjEstadoEjecucionRemoto().insertar(bean.getObjeToEjecucion()));
            } catch (Exception e) {
                continuar = false;
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar el nuevo estado del expediente. %s", e.getMessage()), e);
                bean.addError("Se presento inconveniente al guardar el nuevo estado del expediente." + e.getMessage());
            }

            CntjExpediente expActual = getCntjExpedienteRemoto().consultar(bean.getObjeto().getId());

            if (continuar) {
                try {
                    bean.getObjeto().setEstado(estadoSiguiente.getNombre());
                    bean.getObjeto().setEstadoActual(estadoSiguiente);
                    bean.getObjeto().setFechaEjecucionEstado(bean.getObjeToEjecucion().getFechaEjecucion());
                    bean.auditoriaModificar(bean.getObjeto());
                    //Se actualiza el campo jsonData del expediente
                    getCntjExpedienteRemoto().actualizar(bean.getObjeto());
                } catch (Exception e) {
                    continuar = false;
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al actualizar información del expediente. %s", e.getMessage()), e);
                    bean.addError("Se presento inconveniente al actualizar información del expediente." + e.getMessage());
                    getCntjEstadoEjecucionRemoto().eliminar(bean.getObjeToEjecucion().getId());
                }
            }

            if (continuar) {
                try {
                    //se consultan las plantillas´para generar documentos
                    List<CntjPlantilla> plantillas = getCntjPlantillaRemoto().listaDocumentoEstadoGenerados(estadoSiguiente.getId());
                    if (!plantillas.isEmpty()) {
                        for (CntjPlantilla item : plantillas) {
                            String plantilla = CntjConstantes.ESTILO_DOCUMENTO + item.getEstructura();
                            JSONObject objetoData = new JSONObject(bean.getObjeto().getJsonData() != null ? bean.getObjeto().getJsonData() : "{}");
                            JSONArray arrayObj = new JSONArray();
                            if (objetoData.has(CntjConstantes.CAMPOS)) {
                                arrayObj = objetoData.getJSONArray(CntjConstantes.CAMPOS);
                            }
                            for (int index = 0; index < arrayObj.length(); index++) {
                                JSONObject objItem = arrayObj.getJSONObject(index);
                                plantilla = plantilla.replace("<!-- -->", "");
                                if (objItem.has(CntjConstantes.VALOR_STR)) {
                                    plantilla = plantilla.replace(objItem.getString(CntjConstantes.ETIQUETA), objItem.getString(CntjConstantes.VALOR_STR));
                                } else {
                                    plantilla = plantilla.replace(objItem.getString(CntjConstantes.ETIQUETA), objItem.getString(CntjConstantes.VALOR));
                                }
                            }

                            String ruta = PropApl.getInstance().get(PropApl.CNTJ_RUTA_EXPEDIENTES_ADJUNTOS);
                            Files.createDirectories(Paths.get(ruta));
                            File archivo = new File(ruta, CntjUtilidades.getNombreDocumento(bean.getObjeto().getNumeroExpediente(), item.getProcesodocumentoId().getNombre()));
                            ConverterProperties properties = new ConverterProperties();
                            HtmlConverter.convertToPdf(plantilla, new FileOutputStream(archivo), properties);

                            CntjDocumento documento = new CntjDocumento();
                            documento.setCntjExpedienteId(bean.getObjeto());
                            documento.setCntjPlantillaId(item);
                            documento.setDocumentoNombre(item.getProcesodocumentoId().getNombre());
                            documento.setDocumentoRuta(ruta);
                            documento.setDocumentoArchivo(archivo.getName());
                            documento.setDocumentoExiste(true);
                            documento.setNombre(item.getProcesodocumentoId().getNombre());
                            documento.setTipo(item.getProcesodocumentoId().getTipoDocumento().shortValue());
                            Integer docExistente = getCntjDocumentoRemoto().existeDocumentoExpediente(bean.getObjeto().getId(), item.getProcesodocumentoId().getNombre());
                            if(docExistente == null){
                                bean.auditoriaGuardar(documento);
                                getCntjDocumentoRemoto().insertar(documento);
                            }else{
                                documento.setId(docExistente);
                                bean.auditoriaModificar(documento);
                                getCntjDocumentoRemoto().actualizar(documento);
                            }
                            
                        }
                    }
                } catch (Exception e) {
                    continuar = false;
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente en la generacion de documentos del estado. %s", e.getMessage()), e);
                    bean.addError("Inconveninete en la generacion de documentos del estado.");
                    getCntjDocumentoRemoto().eliminarPorExpediente(bean.getObjeto().getId());
                    getCntjExpedienteRemoto().actualizar(expActual);
                    getCntjEstadoEjecucionRemoto().eliminar(bean.getObjeToEjecucion().getId());
                }
            }

            if (continuar) {
                //guardar adjuntos
                if (!bean.getListaAdjuntos().isEmpty()) {
                    try {
                        String ruta = PropApl.getInstance().get(PropApl.CNTJ_RUTA_EXPEDIENTES_ADJUNTOS);
                        Files.createDirectories(Paths.get(ruta));
                        for (CntjDocumento doc : bean.getListaAdjuntos()) {
                            int indiceExtension = doc.getDocumentoNombre().lastIndexOf(".");
                            String extension = doc.getDocumentoNombre().substring(indiceExtension, doc.getDocumentoNombre().length());
                            doc.setDocumentoArchivo(String.format("%s%s%s", CntjConstantes.NOMBRE_ADJUNTO_EXPEDIENTE, CntjConstantes.formato6.format(new Date()), extension));
                            doc.setCntjExpedienteId(bean.getObjeto());
                            doc.setDocumentoRuta(ruta);
                            doc.setDocumentoExiste(true);
                            bean.auditoriaGuardar(doc);
                            getCntjDocumentoRemoto().insertar(doc);
                            boolean generado = generarArchivo(doc);
                            if (!generado) {
                                throw new Exception("No se pudo almacenar el archivo del adjunto.");
                            }
                        }
                    } catch (Exception e) {
                        continuar = false;
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente guardar adjuntos. %s", e.getMessage()), e);
                        bean.addError("Inconveniente al guardar adjuntos.");
                        getCntjDocumentoRemoto().eliminarPorExpediente(bean.getObjeto().getId());
                        getCntjExpedienteRemoto().actualizar(expActual);
                        getCntjEstadoEjecucionRemoto().eliminar(bean.getObjeToEjecucion().getId());
                    }
                }
            }

            if (continuar) {
                //se valida si se debe crear un contrato
                if (estadoSiguiente.getTipo().equals(CntjConstantes.TIPO_CONTRATO)) {
                    try {
                        JSONObject json = new JSONObject(bean.getObjeto().getJsonData() != null ? bean.getObjeto().getJsonData() : "{}");
                        CntjContrato contrato = CntjUtilidades.parsearJsonContrato(json.toString());
                        contrato.setCntjExpedienteId(bean.getObjeto());
                        contrato.setProceso(bean.getObjeto().getProcesoId().getId().toString());
                        String ultimoNumcontrato = getCtnjContratoRemoto().ultimoNumeroContrato(String.valueOf(LocalDate.now().getYear()));
                        if (ultimoNumcontrato != null && !ultimoNumcontrato.isEmpty()) {
                            int ultimoValor = Integer.parseInt(ultimoNumcontrato.split(CntjConstantes.SEPARADOR)[0]);
                            contrato.setContrato(String.format("%s-%s", String.format("%04d", (ultimoValor + 1)), LocalDate.now().getYear()));
                        } else {
                            contrato.setContrato(String.format("%s-%s", String.format("%04d", 1), LocalDate.now().getYear()));
                        }
                        contrato.setValorPagadoTotal(contrato.getValorTotal());
                        guardarContrato(bean, contrato);
                        bean.getObjeto().setContrato(contrato.getContrato());
                        getCntjExpedienteRemoto().actualizar(bean.getObjeto());
                        if (bean.isError()) {
                            throw new Exception("No fue posible guardar el contrato.");
                        }

                    } catch (Exception e) {
                        continuar = false;
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente en la creación del contarto. %s", e.getMessage()), e);
                        bean.addError("Inconveniente en la creación del contarto.");
                        getCntjDocumentoRemoto().eliminarPorExpediente(bean.getObjeto().getId());
                        getCntjExpedienteRemoto().actualizar(expActual);
                        getCntjEstadoEjecucionRemoto().eliminar(bean.getObjeToEjecucion().getId());
                    }
                }
            }

            if (continuar) {
                //Se establece notificacion al grupo del estado.
                List<CntjUsuarioGrupo> listaUsuario = getCtnjUsuarioGrupoRemoto().listarUsuariosGrupoPermisos(bean.getObjeToEjecucion().getCntjEstadoId().getId());
                for (CntjUsuarioGrupo usuario : listaUsuario) {
                    try {
                        GnAlerta alerta = CntjConstantes.getAlertaGestionExpediente(usuario.getGnUsuarioId(), bean.getObjeToEjecucion().getFechaEjecucion(), bean.getObjeto().getNumeroExpediente(), estadoSiguiente.getNombre(), bean.getConexion().getUsuario().getNombre(), bean.getObjeToEjecucion().getObservacion());
                        getGnAlertaRemoto().insertar(alerta);
                    } catch (Exception e) {
                        bean.addError("No fue posible enviar notificaciones para algunos usuarios.");
                        continue;
                    }
                }
            }

            if (continuar) {
                bean.addMensaje("Información guardada correctamente.");
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al guardar.");
        }
    }

    private void crearCampo(ExpedienteBean bean) {
        try {
            if (bean.getObjeToEjecucion().getCntjEstadoId() == null) {
                bean.addError("No se ha establecido un estado siguiente para cargar los campos.");
                return;
            }
            List<CntjCampo> listadoCampos = getCntjCampoRemoto().listaCamposDocumentoEstadoGenerados(bean.getObjeToEjecucion().getCntjEstadoId().getId());
            if (!listadoCampos.isEmpty()) {
                List<CntjCampo> campos = listadoCampos.stream()
                        .filter(item -> !bean.getListaCamposTransicion().contains(item))
                        .collect(Collectors.toList());
                bean.setListaCampos(campos);
            } else {
                bean.setListaCampos(new ArrayList<>());
            }
            bean.setHashListaCampos(CntjConstantes.obtenerHashCampos(bean.getListaCampos()));
            bean.setObjetoCampo(new CntjCampo());
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al consultar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al consultar información.");
        }
    }

    private void agregarCampo(ExpedienteBean bean) {
        try {
            CntjCampoGestion campoGestion = new CntjCampoGestion();
            campoGestion.setValor(bean.getObjetoCampo().getValor());

            if (bean.getObjetoCampo().getNombre() == null) {
                bean.addError("Debe seleccionar el Campo que desea agregar.");
            }

            if (bean.getObjetoCampo().getTipoDato().equals(CntjConstantes.TIPO_DATO_FECHA)) {
                if (bean.getObjetoCampo().getValorDt() == null) {
                    bean.addError("Debe ingresar un valor para el campo seleccionado. ");
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    bean.getObjetoCampo().setValor(sdf.format(bean.getObjetoCampo().getValorDt()));
                }
            }

            if (!bean.getObjetoCampo().getTipoDato().equals(CntjConstantes.TIPO_DATO_TERCERO) && bean.getObjetoCampo().getValor() == null) {
                bean.addError("Debe ingresar un valor para el campo seleccionado. ");
            }

            if (!bean.getErrores().isEmpty()) {
                return;
            }

            if (bean.getObjetoCampo().getTipoDato().equals(CntjConstantes.TIPO_DATO_TERCERO)) {
                bean.getObjetoCampo().setValor(bean.getTercero().getId().toString());
                List<CntjCampo> campoReferentes = getCntjCampoRemoto().camposPorReferencia(bean.getObjeToEjecucion().getCntjEstadoId().getId(), bean.getObjetoCampo().getNombre());
                for (CntjCampo item : campoReferentes) {
                    item.setValor(CntjUtilidades.getValorTercero(bean.getTercero(), item.getValorReferencia()));
                    bean.getListaCamposTransicion().add(item);
                }
                bean.setTercero(new CntjTercero());
            }

            if (bean.getObjetoCampo().getTipoDato().equals(CntjConstantes.TIPO_DATO_LISTA)) {
                Maestro maestro = bean.getHashListaMaestroCampo().get(Integer.valueOf(bean.getObjetoCampo().getValor()));
                bean.getObjetoCampo().setValorStr(maestro.getNombre());
            }

            if (bean.getListaCamposTransicion().isEmpty()) {
                bean.getObjetoCampo().setId(1);
            } else {
                Optional<CntjCampo> maxNumero = bean.getListaCamposTransicion().stream()
                        .max(Comparator.comparingInt(CntjCampo::getId));
                if (maxNumero.isPresent()) {
                    bean.getObjetoCampo().setId(maxNumero.get().getId() + 1);
                }
            }
            bean.getObjetoCampo().setEtiqueta(bean.getHashListaCampos().get(bean.getObjetoCampo().getNombre()).getEtiqueta());
            bean.getObjetoCampo().setTabla(bean.getHashListaCampos().get(bean.getObjetoCampo().getNombre()).getTabla());
            bean.getObjetoCampo().setCampo(bean.getHashListaCampos().get(bean.getObjetoCampo().getNombre()).getCampo());
            bean.getListaCamposTransicion().add(bean.getObjetoCampo());

        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al agregar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al agregar información.");
        }
    }

    private void listarMaestro(ExpedienteBean bean) {
        try {
            bean.setListaMaestroCampo(MaestroSingle.getInstance().listarPorTipo(bean.getObjetoCampo().getMaestroTipo()));
            bean.setHashListaMaestroCampo(CntjConstantes.obtenerHashMaestro(bean.getListaMaestroCampo()));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al listar información %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al listar información.");
        }
    }

    private void listarDocumentos(ExpedienteBean bean) {
        try {
            bean.getParamConsulta(0).setCantidadRegistros(getCntjDocumentoRemoto().consultarCantidadLista(bean.getParamConsulta(0)));
            bean.setListaDocumentos(getCntjDocumentoRemoto().consultarLista(bean.getParamConsulta(0)));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al listar información %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al listar información.");
        }
    }

    private void crearAdjunto(ExpedienteBean bean) {
        try {
            bean.setAdjunto(new CntjDocumento());
            bean.setListaEstadoDocumento(getCntjEstadoProcesoDocumentoRemoto().listaDocumentoEstadoAdjuntos(bean.getObjeToEjecucion().getCntjEstadoId().getId()));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al realizar la acción %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al realizar la acción.");
        }
    }

    private boolean generarArchivo(CntjDocumento objeto) throws Exception {
        boolean esArchivoGuardado = false;
        OutputStream destino = null;
        try {
            File archivo = new File(objeto.getDocumentoRuta(), objeto.getDocumentoArchivo());
            destino = new FileOutputStream(archivo);
            IOUtils.copy(objeto.getAdjuntoStream(), destino);
            IOUtils.closeQuietly(objeto.getAdjuntoStream());
            IOUtils.closeQuietly(destino);
            Fichero.permisos(archivo.toPath());
            esArchivoGuardado = true;
        } catch (FileNotFoundException ex) {
            esArchivoGuardado = false;
            throw new Exception("Error al subir un adjunto " + ex.getMessage());
        } catch (IOException ex) {
            esArchivoGuardado = false;
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

    private void validarDatosContratos(ExpedienteBean bean, CntjContrato contrato) throws Exception {
        if (contrato.getCntjTerceroId() == null) {
            bean.addError("Creación contrato: Debe indicar el supervisor.");
        }
        if (contrato.getMaeEstadoContratoId() == 0) {
            bean.addError("Creación contrato: Debe selecionar un valor para el campo Estado de Contrato.");
        }
        if (contrato.getFechaInicio() == null) {
            bean.addError("Creación contrato: Debe indicar la fecha inicio del contrato");
        }
        if (contrato.getFechaFin() == null) {
            bean.addError("Creación contrato: Debe indicar la fecha fin del contrato");
        }
        if (contrato.getValorInicial() == null) {
            bean.addError("Creación contrato: Debe indicar el valor del contrato.");
        }
        if (contrato.getMaeClaseContratoId() == null) {
            bean.addError("Creación contrato: Debe indicar la clase de contrato.");
        }
        if (contrato.getPlazoInicialMeses() == null) {
            bean.addError("Creación contrato: Debe indicar el plazo inicial en meses.");
        }
        if (contrato.getPlazoInicialDias() == null) {
            bean.addError("Creación contrato: Debe indicar el plazo inicial en días.");
        }
        if (contrato.getPlazoTotalDias() == null) {
            bean.addError("Creación contrato: Debe indicar el plazo total en días.");
        }
        if (contrato.getValorTotal() == null) {
            bean.addError("Creación contrato: Debe indicar el valor total.");
        }
        if (contrato.getObjeto() == null) {
            bean.addError("Creación contrato: Debe indicar el objeto del contrato.");
        }

        boolean existe = getCtnjContratoRemoto().existeContratoCodigo(contrato.getContrato());

        if (existe) {
            bean.addError("Creación contrato: Ya existe un registro con el mismo contrato, el valor del campo contrato es único");
        }

        if (contrato.getTipoAnticipo() != null && contrato.getTipoAnticipo() == CntjConstantes.TIPO_ANTICIPO_ANTICIPADO) {
            if (contrato.getValorAnticipo() == null) {
                bean.addError("Creación contrato: Debe ingresar el valor anticipo.");
            }
        }

        if (contrato.getFechaSuspension() != null) {
            if (contrato.getMotivoSuspension() == null) {
                bean.addError("Creación contrato: Debe ingresar un motivo de suspensión porque selecciono una fecha de suspensión.");
            }
        }

    }

    private void listarTerceros(ExpedienteBean bean) {
        try {
            bean.setListaTipoTercero(CntjConstantes.listaTipoTercero());
            bean.setListaTipoDocumentoTercero(MaestroSingle.getInstance().listarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
            bean.getParamConsulta(1).setCantidadRegistros(getCtnjTerceroRemoto().consultarCantidadLista(bean.getParamConsulta(1)));
            bean.setRegistrosTerceros(getCtnjTerceroRemoto().consultarLista(bean.getParamConsulta(1)));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al listar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al listar información." + e.getMessage());
        }
    }

    private void guardarContrato(ExpedienteBean bean, CntjContrato contrato) {
        try {
            boolean continuar = true;
            Maestro maemodalidad = bean.getHashlistaModalidad().get(contrato.getMaeModalidadContratoId());
            if (maemodalidad != null) {
                contrato.setMaeModalidadContratoCodigo(maemodalidad.getValor());
                contrato.setMaeModalidadContratoValor(maemodalidad.getNombre());
            }
            Maestro maeclasecontrato = bean.getHashlistaClaseContrato().get(contrato.getMaeClaseContratoId());
            if (maeclasecontrato != null) {
                contrato.setMaeClaseContratoCodigo(maeclasecontrato.getValor());
                contrato.setMaeClaseContratoValor(maeclasecontrato.getNombre());
            }
            Maestro maeregimen = bean.getHashlistaRegimen().get(contrato.getMaeRegimenId());
            if (maeregimen != null) {
                contrato.setMaeRegimenCodigo(maeregimen.getValor());
                contrato.setMaeRegimenValor(maeregimen.getNombre());
            }

            Maestro estadocontrato = bean.getHashlistaEstadoContrato().get(contrato.getMaeEstadoContratoId());
            contrato.setMaeEstadoContratoCodigo(estadocontrato.getValor());
            contrato.setMaeEstadoContratoValor(estadocontrato.getDescripcion());

            bean.auditoriaGuardar(contrato);
            int idcontrato = getCtnjContratoRemoto().insertar(contrato);

            //guardar supervisores
            if (!contrato.getCntjContratoSupervisorList().isEmpty()) {
                for (CntjContratoSupervisor supervisor : contrato.getCntjContratoSupervisorList()) {
                    supervisor.setCntjContratosId(new CntjContrato(idcontrato));
                    supervisor.setFechaInicio(contrato.getFechaInicio());
                    supervisor.setFechaFin(contrato.getFechaFin());
                    bean.auditoriaGuardar(supervisor);
                    try {
                        supervisor.setId(getCntjContratoSupervisorRemoto().insertar(supervisor));
                    } catch (Exception e) {
                        continuar = false;
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar supervisores del contrato. %s", e.getMessage()), e);
                        bean.addError("Se presento inconveniente al guardar supervisores del contrato." + e.getMessage());
                        getCntjContratoSupervisorRemoto().eliminarPorcontrato(idcontrato);
                        getCtnjContratoRemoto().eliminar(idcontrato);
                        continue;
                    }
                }
            }

            //guardar garantia
            if (!contrato.getCntjContratoGarantiaList().isEmpty() && continuar) {
                for (CntjContratoGarantia garantia : contrato.getCntjContratoGarantiaList()) {
                    try {
                        garantia.setCntjContratoId(new CntjContrato(idcontrato));

                        if (garantia.getMaeGarantiaContratoId() == null) {
                            bean.addError("Garantia del contrato: Falta definir la garantia.");
                        }
                        if (garantia.getEstado() == null) {
                            bean.addError("Garantia del contrato: Falta definir el estado de la garantia.");
                        }
                        if (bean.isError()) {
                            throw new Exception();
                        }

                        Maestro maegarantia = bean.getHashlistaGarantiaContrato().get(garantia.getMaeGarantiaContratoId());
                        garantia.setMaeGarantiaContratoCodigo(maegarantia.getValor());
                        garantia.setMaeGarantiaContratoValor(maegarantia.getNombre());
                        bean.auditoriaGuardar(garantia);

                        getCntjContratoGarantiaRemoto().insertar(garantia);
                    } catch (Exception e) {
                        continuar = false;
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar garantias del contrato. %s", e.getMessage()), e);
                        if (!bean.isError()) {
                            bean.addError("Se presento inconveniente al guardar garantias del contrato.");
                        }
                        getCntjContratoGarantiaRemoto().eliminarPorcontrato(idcontrato);
                        getCntjContratoSupervisorRemoto().eliminarPorcontrato(idcontrato);
                        getCtnjContratoRemoto().eliminar(idcontrato);
                        break;
                    }
                }
            }

            //guardar indicadores
            if (!contrato.getCntjContratoIndicadorList().isEmpty() && continuar) {
                for (CntjContratoIndicador indicador : contrato.getCntjContratoIndicadorList()) {
                    try {
                        if (indicador.getTipoIndicador() == null) {
                            bean.addError("Indicador del contrato: Falta definir el tipo de indicador.");
                        }
                        if (indicador.getDescripcion() == null) {
                            bean.addError("Indicador del contrato: Falta definir la descripción.");
                        }
                        if (indicador.getMeta() == null) {
                            bean.addError("Indicador del contrato: Falta definir la meta.");
                        }
                        if (indicador.getAplicaDescuento() == null) {
                            bean.addError("Indicador del contrato: Falta definir si aplica descuento.");
                        }

                        if (bean.isError()) {
                            throw new Exception();
                        }

                        indicador.setCntjContratosId(new CntjContrato(idcontrato));
                        bean.auditoriaGuardar(indicador);
                        getCntjContratoIndicadorRemoto().insertar(indicador);
                    } catch (Exception e) {
                        continuar = false;
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar indicadores del contrato. %s", e.getMessage()), e);
                        if (!bean.isError()) {
                            bean.addError("Se presento inconveniente al guardar indicadores del contrato." + e.getMessage());
                        }
                        getCntjContratoIndicadorRemoto().eliminarPorcontrato(idcontrato);
                        getCntjContratoSupervisorRemoto().eliminarPorcontrato(idcontrato);
                        getCntjContratoGarantiaRemoto().eliminarPorcontrato(idcontrato);
                        getCtnjContratoRemoto().eliminar(idcontrato);
                        continue;
                    }
                }
            }

            //guardar obligaciones
            if (!contrato.getCntjContratoObligacionList().isEmpty() && continuar) {
                for (CntjContratoObligacion obligacion : contrato.getCntjContratoObligacionList()) {
                    try {
                        if (obligacion.getNumeroObligacion() == null) {
                            bean.addError("Obligación del contrato: Falta definir el numero de la obligacion.");
                        }
                        if (obligacion.getDescripcion() == null) {
                            bean.addError("Obligación del contrato: Falta definir el la descripción.");
                        }
                        if (bean.isError()) {
                            throw new Exception();
                        }

                        obligacion.setCntjContratosId(new CntjContrato(idcontrato));
                        bean.auditoriaGuardar(obligacion);
                        getCntjContratoObligacionRemoto().insertar(obligacion);
                    } catch (Exception e) {
                        continuar = false;
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar obligaciones del contrato. %s", e.getMessage()), e);
                        if (!bean.isError()) {
                            bean.addError("Se presento inconveniente al guardar obligaciones del contrato." + e.getMessage());
                        }
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
                if (contrato.getCntjContratoSeguimientoList().isEmpty()) {
                    CntjContratoSeguimiento seguimiento = new CntjContratoSeguimiento();
                    seguimiento.setCntjContratoId(new CntjContrato(idcontrato));
                    seguimiento.setCntjTerceroId(contrato.getCntjTerceroId());
                    CntjProceso proceso = getCtnjProcesoRemoto().consultar(Integer.parseInt(contrato.getProceso()));
                    if (proceso.getTipoProceso().equals(CntjConstantes.TIPO_PROCESO_ADMINISTRATIVO)) {
                        seguimiento.setPeriodicidad(CntjContratoSeguimiento.PERIODICIDAD_MENSUAL);
                    } else if (proceso.getTipoProceso().equals(CntjConstantes.TIPO_PROCESO_SALUD)
                            && contrato.getFormaPago().equals(CntjConstantes.FORMA_PAGO_MENSUAL_VARIABLE)) {
                        seguimiento.setPeriodicidad(CntjContratoSeguimiento.PERIODICIDAD_BIMESTRAL);
                    } else {
                        seguimiento.setPeriodicidad(CntjContratoSeguimiento.PERIODICIDAD_SEMESTRAL);
                    }
                    seguimiento.setFechaInicioSeguimiento(contrato.getFechaInicio());
                    seguimiento.setFechaFinSeguimiento(contrato.getFechaFin());
                    seguimiento.setEstadoSeguimiento(CntjContratoSeguimiento.ESTADO_PENDIENTE);
                    seguimiento.setObservaciones("Inicial");
                    bean.auditoriaGuardar(seguimiento);
                    try {
                        getCntjContratoSeguimientoRemoto().insertar(seguimiento);
                    } catch (Exception e) {
                        Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar seguimientos del contrato. %s", e.getMessage()), e);
                    }

                }
            }

            if (bean.getErrores().isEmpty()) {
                bean.addMensaje("Contrato guardado correctamente.");
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar contrato. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al guardar contrato.");
        }
    }

    private void listarResponsablesExpediente(ExpedienteBean bean) {
        try {
            bean.setObjeto(getCntjExpedienteRemoto().consultar(bean.getObjeto().getId()));
            bean.getParamConsulta(2).setCantidadRegistros(getCntjExpedienteResponsableRemoto().consultarCantidadLista(bean.getParamConsulta(2)));
            bean.setRegistrosResponsables(getCntjExpedienteResponsableRemoto().consultarLista(bean.getParamConsulta(2)));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al consultar responsables. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al consultar responsables.");
        }
    }

    private void crearResponsablesExpediente(ExpedienteBean bean) {
        try {
            bean.setObjetoResponsable(new CntjExpedienteResponsable());
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al realizar la acción. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al realizar la acción.");
        }
    }

    private void consultarUsuarios(ExpedienteBean bean) {
        try {
            if (!bean.getConexion().getEmpresa().isAdministradora()) {
                bean.getParamConsulta(3).setEmpresaId(bean.getConexion().getEmpresa().getId());
            }
            bean.getParamConsulta(3).getFiltros().put(CntjConstantes.ACTIVO_STR, String.valueOf(CntjConstantes.ACTIVO));
            bean.getParamConsulta(3).setCantidadRegistros(getUsuarioRemoto().consultarCantidadLista(bean.getParamConsulta(3)));
            bean.setListaUsuario(getUsuarioRemoto().consultarLista(bean.getParamConsulta(3)));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al consultar listado de usuarios. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al consultar listado de usuarios.");
        }
    }

    private void guardarResponsable(ExpedienteBean bean) {
        try {
            if (bean.getObjetoResponsable().getUsuarioId().getId() == null) {
                bean.addError("Debe seleccioanr un valor para el campo usuario.");
            }
            if (bean.getObjetoResponsable().getFechaInicial() == null) {
                bean.addError("Debe seleccionar la fecha inicial.");
            }
            if (bean.getObjetoResponsable().getFechaFinal() == null) {
                bean.addError("Debe seleccionar la fecha final.");
            }

            if (bean.getObjeto().getUsuarioPropietario() != null
                    && bean.getObjetoResponsable().getRol().equals(CntjConstantes.ROL_RESPONSABLE)
                    && bean.getObjetoResponsable().getUsuarioId().getId().equals(bean.getObjeto().getUsuarioPropietario().getId())) {
                bean.addError("Este usuario es el propietario del expediente y no puede ser agregado como responsable.");
            }

            if (bean.isError()) {
                return;
            }

            boolean continuar = true;

            if (bean.getObjetoResponsable().getRol().equals(CntjConstantes.ROL_PROPIETARIO)) {
                bean.getObjeto().setUsuarioPropietario(bean.getObjetoResponsable().getUsuarioId());
            } else if (bean.getObjetoResponsable().getRol().equals(CntjConstantes.ROL_RESPONSABLE)) {
                bean.getObjeto().setUsuarioResponsable(bean.getObjetoResponsable().getUsuarioId());
            }

            try {
                bean.getObjetoResponsable().setExpedienteId(bean.getObjeto());
                bean.auditoriaGuardar(bean.getObjetoResponsable());
                int idResponsable = getCntjExpedienteResponsableRemoto().insertar(bean.getObjetoResponsable());
                bean.getObjetoResponsable().setId(idResponsable);
            } catch (Exception e) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar responsable. %s", e.getMessage()), e);
                bean.addError("Se presento inconveniente al guardar responsable.");
                continuar = false;
                getCntjExpedienteResponsableRemoto().eliminar(bean.getObjetoResponsable().getId());
            }

            if (continuar) {
                try {
                    //se actualizara el responsable en el expediente
                    bean.auditoriaModificar(bean.getObjeto());
                    getCntjExpedienteRemoto().actualizar(bean.getObjeto());
                } catch (Exception e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al actualizar el expediente. %s", e.getMessage()), e);
                    bean.addError("Se presento inconveniente al actualizar el expediente.");
                    continuar = false;
                    getCntjExpedienteResponsableRemoto().eliminar(bean.getObjetoResponsable().getId());
                }
            }

            if (continuar) {
                bean.addMensaje("Datos guardados correctamente.");
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al guardar responsable. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al guardar responsable.");
        }
    }

    private void borrarResponsable(ExpedienteBean bean) {
        try {
            if (bean.getObjetoResponsable().getId() == null) {
                bean.addError("Debe indicar el registro que desea eliminar.");
            }

            if (bean.isError()) {
                return;
            }

            getCntjExpedienteResponsableRemoto().eliminar(bean.getObjetoResponsable().getId());
            bean.addMensaje("Registro eliminado correctamente.");
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al borrar el responsable. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al borrar el responsable.");
        }
    }

    private void consultarUltimoResponsable(ExpedienteBean bean) {
        try {
            CntjExpedienteResponsable ultimoResponsable = getCntjExpedienteResponsableRemoto().ultimoResponsable(bean.getObjeto().getId(), bean.getObjetoResponsable().getRol());
            if (ultimoResponsable != null) {
                bean.setFechaMinimaReponsable(ultimoResponsable.getFechaInicial());
                if (bean.getObjetoResponsable().getFechaInicial() != null
                        && ultimoResponsable.getFechaInicial().after(bean.getObjetoResponsable().getFechaInicial())) {
                    bean.getObjetoResponsable().setFechaInicial(null);
                    bean.getObjetoResponsable().setFechaFinal(null);
                }
            } else {
                bean.setFechaMinimaReponsable(null);
            }
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al realizar la acción. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al realizar la acción.");
        }
    }

    private void consultarDocumento(ExpedienteBean bean) {
        try {
            bean.setObjDocumento(getCntjDocumentoRemoto().consultar(bean.getObjDocumento().getId()));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al consultar informacion. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al consultar informacion.");
        }
    }

    private void listarEventoContractual(ExpedienteBean bean) {
        try {
            if(bean.isAccionAdicional1()){
                //Ver todos los registros
                bean.getParamConsulta(4).setParametroConsulta1(null);
            }else{
                //ver registros por usuario
                bean.getParamConsulta(4).setParametroConsulta1(bean.getConexion().getUsuario().getId());
            }
            bean.getParamConsulta(4).setParametroConsulta2(bean.getObjeto().getId());
            bean.getParamConsulta(4).setCantidadRegistros(getCntjExpedienteRemoto().consultarCantidadLista(bean.getParamConsulta(4)));
            bean.setRegistrosContractuales(getCntjExpedienteRemoto().consultarLista(bean.getParamConsulta(4)));
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al listar información. %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al listar información." + e.getMessage());
        }
    }

    private void descargaAgrupada(ExpedienteBean bean) {
        try {
            bean.setObjeto(getCntjExpedienteRemoto().consultar(bean.getObjeto().getId()));
            List<CntjDocumento> documentos = getCntjDocumentoRemoto().documentosExportar(bean.getObjeto().getId());
            if (!documentos.isEmpty()) {
                Map<Integer, List<CntjDocumento>> agrupados = documentos.stream()
                        .collect(Collectors.groupingBy(CntjDocumento::getEtapaContratacion));
                Map<String, byte[]> archivosPDF = new HashMap<>();

                for (Map.Entry<Integer, List<CntjDocumento>> entry : agrupados.entrySet()) {
                    Integer tipo = entry.getKey();
                    String tipoEtapa = CntjConstantes.getEtapaDeDesignacion(tipo);
                    List<CntjDocumento> grupo = entry.getValue();

                    byte[] pdfUnido = CntjUtilidades.unirPDFsEnMemoria(grupo);
                    archivosPDF.put(tipoEtapa+ ".pdf", pdfUnido);
                }
                
                byte[] zipBytes = CntjUtilidades.crearZipEnMemoria(archivosPDF);
                StreamedContent zipParaDescarga = DefaultStreamedContent.builder()
                        .name(bean.getObjeto().getNumeroExpediente() +".zip")
                        .contentType("application/zip")
                        .stream(() -> new ByteArrayInputStream(zipBytes))
                        .build();
                bean.setZipParaDescarga(zipParaDescarga);
            }else {
                bean.addError("No se encontraron documentos que cumplan el criterio para descargar.");
            }   
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, String.format("Se presento inconveniente al generar archivo de documentos %s", e.getMessage()), e);
            bean.addError("Se presento inconveniente al generar archivo de documentos.");
        }
    }

}
