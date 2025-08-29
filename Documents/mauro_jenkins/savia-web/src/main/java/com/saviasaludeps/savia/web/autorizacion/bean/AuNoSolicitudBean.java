/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegRegistroNovedad;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitud;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudDiagnostico;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudEntrega;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudEntregaDetalle;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudHistorico;
import com.saviasaludeps.savia.dominio.autorizacion.AuNoSolicitudItem;
import com.saviasaludeps.savia.dominio.autorizacion.AuSolicitudAdjunto;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesionalPrestador;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.dominio.maestro.MaEspecialidad;
import com.saviasaludeps.savia.dominio.maestro.MaInsumo;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.dominio.maestro.MaServicioHabilitacion;
import com.saviasaludeps.savia.web.autorizacion.servicio.AuNoSolicitudIface;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelDiagnosticosBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelEspecialidadesBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelInsumosBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelMedicamentoBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelPaquetesBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelServiciosHabilitacionBean;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelTecnologiasBean;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author NEXOS
 */
@Named
@ViewScoped
public class AuNoSolicitudBean extends Url{
    
    @Autowired
    private AuNoSolicitudIface auNoSolicitudServicio;
    private AuNoSolicitud objeto;
    private List<AuNoSolicitud> registros;
    private LazyDataModel<AuNoSolicitud> lazyAuSinSolicitud;
    // lista maestros
    private List<Ubicacion> listaUbicaciones;
    private HashMap<Integer, Ubicacion> hashUbicaciones;
    private List<Maestro> listaTipoDocumentoPersona;
    private HashMap<Integer, Maestro> hashTipoDocumentoPersona;
    private List<Maestro> listaTipoDocumentoEmpresa;
    private List<Maestro> listaGeneroAfiliado;
    private List<Maestro> listaEstadosAfiliado;
    private List<Maestro> listaRegimenAfiliado;
    private List<Ubicacion> listaCiudades;
    private List<Maestro> listaTiposDocumentoProfesional;
    private HashMap<Integer, Maestro> hashTiposDocumentoProfesional;
    private List<Maestro> listaAmbitosAtencion;
    private HashMap<Integer, Maestro> hashAmbitosAtencion;
    private List<Maestro> listaViaAdmistracion;
    private HashMap<Integer, Maestro> hashViaAdmistracion;
    private List<Maestro> listaTiposAdjuntos;
    private HashMap<Integer, Maestro> hashTiposAdjuntos;
    private List<Maestro> listaCausaNoEntrega;
    private HashMap<Integer, Maestro>  hashCausaNoEntrega;
    private List<Maestro> listaMotivoSinAutorizacion;
    private HashMap<Integer, Maestro>  hashMotivoSinAutorizacion;
    
    // lazy adicionales
    private List<AsegAfiliado> listaAfiliados;
    private LazyDataModel<AsegAfiliado> lazyAfiliados;
    private List<CntPrestadorSede> listaPrestadores;
    private LazyDataModel<CntPrestadorSede> lazyPrestadores;
    private List<CntPrestadorSede> listaPrestadoresEntrega;
    private LazyDataModel<CntPrestadorSede> lazyPrestadoresEntrega;
    private List<CntContratoDetalle> listaProcedimientos;
    private LazyDataModel<CntContratoDetalle> lazyProcedimientos;
    private List<CntContratoDetalle> listaMedicamentos;
    private LazyDataModel<CntContratoDetalle> lazyMedicamentos;
    private List<CntContratoDetalle> listaInsumos;
    private LazyDataModel<CntContratoDetalle> lazyInsumos;
    private List<CntContratoDetalle> listaPaquetes;
    private LazyDataModel<CntContratoDetalle> lazyPaquetes;
    
    // variable adicionales
    private CntProfesionalPrestador objetoProfesionalPrestador;
    private AuNoSolicitudItem objetoItem;
    private AuSolicitudAdjunto objetoAdjunto;
    private AuNoSolicitudEntrega objetoEntrega;
    private AuNoSolicitudEntregaDetalle objetoEntregaDetalle;
    private int tipoFuncionServicioHabilitafion;
    private UploadedFile archivoAdjunto;
    private List<AuNoSolicitudItem> listaItemsBorrar;
    
    //listas para adjuntos
    private List<AuSolicitudAdjunto> listaAuSolicitudAdjunto;
    private List<AuNoSolicitudItem> listaAuSolicitudItemMemerio;
    
    //variables para tecnologias
    private SelServiciosHabilitacionBean selServiciosHabilitacionBean;
    private SelEspecialidadesBean selEspecialidadesBean;
    private SelDiagnosticosBean diagnosticosBean;
    private SelTecnologiasBean tecnologiasBean;
    private SelMedicamentoBean medicamentosBean;
    private SelInsumosBean insumosBean;
    private SelPaquetesBean paquetesBean;
    
    //Variables para mensaje
    private String tituloMensaje;
    private String subtituloMensaje;
    private String mensaje;
    
    public AuNoSolicitudBean() {
        this.objeto = new AuNoSolicitud();
        Modulo _mod = super.validarModulo(Modulo.ID_AUTORIZACIONES_SIN_AUTORIZACIONES);
        super.addListaParamConsultas(new ParamConsulta()); // lazy afiliado
        super.addListaParamConsultas(new ParamConsulta()); // lazy prestadores solicita
        super.addListaParamConsultas(new ParamConsulta()); // lazy prestadores entrega
        super.addListaParamConsultas(new ParamConsulta()); // lazy procedimientos
        super.addListaParamConsultas(new ParamConsulta()); // lazy medicamentos 
        super.addListaParamConsultas(new ParamConsulta()); // lazy insumos
        super.addListaParamConsultas(new ParamConsulta()); // lazy paquetes
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyAuSinSolicitud = new LazyDataModel<AuNoSolicitud>() {

                private List<AuNoSolicitud> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<AuNoSolicitud> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    lista = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(AuNoSolicitud objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public AuNoSolicitud getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (AuNoSolicitud objeto : lista) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }
            };
        }
    }
    
    @PostConstruct
    public void cargaInicial() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        getAuNoSolicitudServicio().cargaInicial(this);
        listar();
    }
    
    
    public AuNoSolicitudIface getAuNoSolicitudServicio() {
        return auNoSolicitudServicio;
    }

    public void setAuNoSolicitudServicio(AuNoSolicitudIface auNoSolicitudServicio) {
        this.auNoSolicitudServicio = auNoSolicitudServicio;
    }

    public AuNoSolicitud getObjeto() {
        return objeto;
    }

    public void setObjeto(AuNoSolicitud objeto) {
        this.objeto = objeto;
    }

    public List<AuNoSolicitud> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AuNoSolicitud> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AuNoSolicitud> getLazyAuSinSolicitud() {
        return lazyAuSinSolicitud;
    }

    public void setLazyAuSinSolicitud(LazyDataModel<AuNoSolicitud> lazyAuSinSolicitud) {
        this.lazyAuSinSolicitud = lazyAuSinSolicitud;
    }

    public List<Ubicacion> getListaUbicaciones() {
        return listaUbicaciones;
    }

    public void setListaUbicaciones(List<Ubicacion> listaUbicaciones) {
        this.listaUbicaciones = listaUbicaciones;
    }

    public HashMap<Integer, Ubicacion> getHashUbicaciones() {
        return hashUbicaciones;
    }

    public void setHashUbicaciones(HashMap<Integer, Ubicacion> hashUbicaciones) {
        this.hashUbicaciones = hashUbicaciones;
    }

    public List<Maestro> getListaTipoDocumentoPersona() {
        return listaTipoDocumentoPersona;
    }

    public void setListaTipoDocumentoPersona(List<Maestro> listaTipoDocumentoPersona) {
        this.listaTipoDocumentoPersona = listaTipoDocumentoPersona;
    }

    public HashMap<Integer, Maestro> getHashTipoDocumentoPersona() {
        return hashTipoDocumentoPersona;
    }

    public void setHashTipoDocumentoPersona(HashMap<Integer, Maestro> hashTipoDocumentoPersona) {
        this.hashTipoDocumentoPersona = hashTipoDocumentoPersona;
    }

    public List<Maestro> getListaTipoDocumentoEmpresa() {
        return listaTipoDocumentoEmpresa;
    }

    public void setListaTipoDocumentoEmpresa(List<Maestro> listaTipoDocumentoEmpresa) {
        this.listaTipoDocumentoEmpresa = listaTipoDocumentoEmpresa;
    }

    public List<Maestro> getListaGeneroAfiliado() {
        return listaGeneroAfiliado;
    }

    public void setListaGeneroAfiliado(List<Maestro> listaGeneroAfiliado) {
        this.listaGeneroAfiliado = listaGeneroAfiliado;
    }

    public List<Maestro> getListaEstadosAfiliado() {
        return listaEstadosAfiliado;
    }

    public void setListaEstadosAfiliado(List<Maestro> listaEstadosAfiliado) {
        this.listaEstadosAfiliado = listaEstadosAfiliado;
    }

    public List<Maestro> getListaRegimenAfiliado() {
        return listaRegimenAfiliado;
    }

    public void setListaRegimenAfiliado(List<Maestro> listaRegimenAfiliado) {
        this.listaRegimenAfiliado = listaRegimenAfiliado;
    }

    public List<Ubicacion> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(List<Ubicacion> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    public List<Maestro> getListaTiposDocumentoProfesional() {
        return listaTiposDocumentoProfesional;
    }

    public void setListaTiposDocumentoProfesional(List<Maestro> listaTiposDocumentoProfesional) {
        this.listaTiposDocumentoProfesional = listaTiposDocumentoProfesional;
    }

    public HashMap<Integer, Maestro> getHashTiposDocumentoProfesional() {
        return hashTiposDocumentoProfesional;
    }

    public void setHashTiposDocumentoProfesional(HashMap<Integer, Maestro> hashTiposDocumentoProfesional) {
        this.hashTiposDocumentoProfesional = hashTiposDocumentoProfesional;
    }

    public List<Maestro> getListaAmbitosAtencion() {
        return listaAmbitosAtencion;
    }

    public void setListaAmbitosAtencion(List<Maestro> listaAmbitosAtencion) {
        this.listaAmbitosAtencion = listaAmbitosAtencion;
    }

    public HashMap<Integer, Maestro> getHashAmbitosAtencion() {
        return hashAmbitosAtencion;
    }

    public void setHashAmbitosAtencion(HashMap<Integer, Maestro> hashAmbitosAtencion) {
        this.hashAmbitosAtencion = hashAmbitosAtencion;
    }

    public List<Maestro> getListaViaAdmistracion() {
        return listaViaAdmistracion;
    }

    public void setListaViaAdmistracion(List<Maestro> listaViaAdmistracion) {
        this.listaViaAdmistracion = listaViaAdmistracion;
    }

    public HashMap<Integer, Maestro> getHashViaAdmistracion() {
        return hashViaAdmistracion;
    }

    public void setHashViaAdmistracion(HashMap<Integer, Maestro> hashViaAdmistracion) {
        this.hashViaAdmistracion = hashViaAdmistracion;
    }

    public List<Maestro> getListaTiposAdjuntos() {
        return listaTiposAdjuntos;
    }

    public void setListaTiposAdjuntos(List<Maestro> listaTiposAdjuntos) {
        this.listaTiposAdjuntos = listaTiposAdjuntos;
    }

    public HashMap<Integer, Maestro> getHashTiposAdjuntos() {
        return hashTiposAdjuntos;
    }

    public void setHashTiposAdjuntos(HashMap<Integer, Maestro> hashTiposAdjuntos) {
        this.hashTiposAdjuntos = hashTiposAdjuntos;
    }

    public List<Maestro> getListaCausaNoEntrega() {
        return listaCausaNoEntrega;
    }

    public void setListaCausaNoEntrega(List<Maestro> listaCausaNoEntrega) {
        this.listaCausaNoEntrega = listaCausaNoEntrega;
    }

    public HashMap<Integer, Maestro> getHashCausaNoEntrega() {
        return hashCausaNoEntrega;
    }

    public void setHashCausaNoEntrega(HashMap<Integer, Maestro> hashCausaNoEntrega) {
        this.hashCausaNoEntrega = hashCausaNoEntrega;
    }

    public List<Maestro> getListaMotivoSinAutorizacion() {
        return listaMotivoSinAutorizacion;
    }

    public void setListaMotivoSinAutorizacion(List<Maestro> listaMotivoSinAutorizacion) {
        this.listaMotivoSinAutorizacion = listaMotivoSinAutorizacion;
    }

    public HashMap<Integer, Maestro> getHashMotivoSinAutorizacion() {
        return hashMotivoSinAutorizacion;
    }

    public void setHashMotivoSinAutorizacion(HashMap<Integer, Maestro> hashMotivoSinAutorizacion) {
        this.hashMotivoSinAutorizacion = hashMotivoSinAutorizacion;
    }

    public List<AsegAfiliado> getListaAfiliados() {
        return listaAfiliados;
    }

    public void setListaAfiliados(List<AsegAfiliado> listaAfiliados) {
        this.listaAfiliados = listaAfiliados;
    }

    public LazyDataModel<AsegAfiliado> getLazyAfiliados() {
        return lazyAfiliados;
    }

    public void setLazyAfiliados(LazyDataModel<AsegAfiliado> lazyAfiliados) {
        this.lazyAfiliados = lazyAfiliados;
    }

    public List<CntPrestadorSede> getListaPrestadores() {
        return listaPrestadores;
    }

    public void setListaPrestadores(List<CntPrestadorSede> listaPrestadores) {
        this.listaPrestadores = listaPrestadores;
    }

    public LazyDataModel<CntPrestadorSede> getLazyPrestadores() {
        return lazyPrestadores;
    }

    public void setLazyPrestadores(LazyDataModel<CntPrestadorSede> lazyPrestadores) {
        this.lazyPrestadores = lazyPrestadores;
    }

    public List<CntPrestadorSede> getListaPrestadoresEntrega() {
        return listaPrestadoresEntrega;
    }

    public void setListaPrestadoresEntrega(List<CntPrestadorSede> listaPrestadoresEntrega) {
        this.listaPrestadoresEntrega = listaPrestadoresEntrega;
    }

    public LazyDataModel<CntPrestadorSede> getLazyPrestadoresEntrega() {
        return lazyPrestadoresEntrega;
    }

    public void setLazyPrestadoresEntrega(LazyDataModel<CntPrestadorSede> lazyPrestadoresEntrega) {
        this.lazyPrestadoresEntrega = lazyPrestadoresEntrega;
    }

    public List<CntContratoDetalle> getListaProcedimientos() {
        return listaProcedimientos;
    }

    public void setListaProcedimientos(List<CntContratoDetalle> listaProcedimientos) {
        this.listaProcedimientos = listaProcedimientos;
    }

    public LazyDataModel<CntContratoDetalle> getLazyProcedimientos() {
        return lazyProcedimientos;
    }

    public void setLazyProcedimientos(LazyDataModel<CntContratoDetalle> lazyProcedimientos) {
        this.lazyProcedimientos = lazyProcedimientos;
    }

    public List<CntContratoDetalle> getListaMedicamentos() {
        return listaMedicamentos;
    }

    public void setListaMedicamentos(List<CntContratoDetalle> listaMedicamentos) {
        this.listaMedicamentos = listaMedicamentos;
    }

    public LazyDataModel<CntContratoDetalle> getLazyMedicamentos() {
        return lazyMedicamentos;
    }

    public void setLazyMedicamentos(LazyDataModel<CntContratoDetalle> lazyMedicamentos) {
        this.lazyMedicamentos = lazyMedicamentos;
    }

    public List<CntContratoDetalle> getListaInsumos() {
        return listaInsumos;
    }

    public void setListaInsumos(List<CntContratoDetalle> listaInsumos) {
        this.listaInsumos = listaInsumos;
    }

    public LazyDataModel<CntContratoDetalle> getLazyInsumos() {
        return lazyInsumos;
    }

    public void setLazyInsumos(LazyDataModel<CntContratoDetalle> lazyInsumos) {
        this.lazyInsumos = lazyInsumos;
    }

    public List<CntContratoDetalle> getListaPaquetes() {
        return listaPaquetes;
    }

    public void setListaPaquetes(List<CntContratoDetalle> listaPaquetes) {
        this.listaPaquetes = listaPaquetes;
    }

    public LazyDataModel<CntContratoDetalle> getLazyPaquetes() {
        return lazyPaquetes;
    }

    public void setLazyPaquetes(LazyDataModel<CntContratoDetalle> lazyPaquetes) {
        this.lazyPaquetes = lazyPaquetes;
    }

    public CntProfesionalPrestador getObjetoProfesionalPrestador() {
        return objetoProfesionalPrestador;
    }

    public void setObjetoProfesionalPrestador(CntProfesionalPrestador objetoProfesionalPrestador) {
        this.objetoProfesionalPrestador = objetoProfesionalPrestador;
    }

    public AuNoSolicitudItem getObjetoItem() {
        return objetoItem;
    }

    public void setObjetoItem(AuNoSolicitudItem objetoItem) {
        this.objetoItem = objetoItem;
    }

    public AuSolicitudAdjunto getObjetoAdjunto() {
        return objetoAdjunto;
    }

    public void setObjetoAdjunto(AuSolicitudAdjunto objetoAdjunto) {
        this.objetoAdjunto = objetoAdjunto;
    }

    public AuNoSolicitudEntrega getObjetoEntrega() {
        return objetoEntrega;
    }

    public void setObjetoEntrega(AuNoSolicitudEntrega objetoEntrega) {
        this.objetoEntrega = objetoEntrega;
    }

    public AuNoSolicitudEntregaDetalle getObjetoEntregaDetalle() {
        return objetoEntregaDetalle;
    }

    public void setObjetoEntregaDetalle(AuNoSolicitudEntregaDetalle objetoEntregaDetalle) {
        this.objetoEntregaDetalle = objetoEntregaDetalle;
    }
    
    public int getTipoFuncionServicioHabilitafion() {
        return tipoFuncionServicioHabilitafion;
    }

    public void setTipoFuncionServicioHabilitafion(int tipoFuncionServicioHabilitafion) {
        this.tipoFuncionServicioHabilitafion = tipoFuncionServicioHabilitafion;
    }

    public UploadedFile getArchivoAdjunto() {
        return archivoAdjunto;
    }

    public void setArchivoAdjunto(UploadedFile archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
    }

    public List<AuNoSolicitudItem> getListaItemsBorrar() {
        return listaItemsBorrar;
    }

    public void setListaItemsBorrar(List<AuNoSolicitudItem> listaItemsBorrar) {
        this.listaItemsBorrar = listaItemsBorrar;
    }

    public List<AuSolicitudAdjunto> getListaAuSolicitudAdjunto() {
        return listaAuSolicitudAdjunto;
    }

    public void setListaAuSolicitudAdjunto(List<AuSolicitudAdjunto> listaAuSolicitudAdjunto) {
        this.listaAuSolicitudAdjunto = listaAuSolicitudAdjunto;
    }

    public List<AuNoSolicitudItem> getListaAuSolicitudItemMemerio() {
        return listaAuSolicitudItemMemerio;
    }

    public void setListaAuSolicitudItemMemerio(List<AuNoSolicitudItem> listaAuSolicitudItemMemerio) {
        this.listaAuSolicitudItemMemerio = listaAuSolicitudItemMemerio;
    }
    
    public SelServiciosHabilitacionBean getSelServiciosHabilitacionBean() {
        selServiciosHabilitacionBean = (SelServiciosHabilitacionBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selServiciosHabilitacionBean");
        return selServiciosHabilitacionBean;
    }

    public void setSelServiciosHabilitacionBean(SelServiciosHabilitacionBean selServiciosHabilitacionBean) {
        this.selServiciosHabilitacionBean = selServiciosHabilitacionBean;
    }

    public SelEspecialidadesBean getSelEspecialidadesBean() {
        selEspecialidadesBean = (SelEspecialidadesBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selEspecialidadesBean");
        return selEspecialidadesBean;
    }

    public void setSelEspecialidadesBean(SelEspecialidadesBean selEspecialidadesBean) {
        this.selEspecialidadesBean = selEspecialidadesBean;
    }
    
    public SelDiagnosticosBean getDiagnosticosBean() {
        diagnosticosBean = (SelDiagnosticosBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selDiagnosticosBean");
        return diagnosticosBean;
    }

    public void setDiagnosticosBean(SelDiagnosticosBean diagnosticosBean) {
        this.diagnosticosBean = diagnosticosBean;
    }
    
    public SelTecnologiasBean getTecnologiasBean() {
        tecnologiasBean = (SelTecnologiasBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selTecnologiasBean");
        return tecnologiasBean;
    }

    public void setTecnologiasBean(SelTecnologiasBean tecnologiasBean) {
        this.tecnologiasBean = tecnologiasBean;
    }

    public SelMedicamentoBean getMedicamentosBean() {
        medicamentosBean = (SelMedicamentoBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selMedicamentosBean");
        return medicamentosBean;
    }

    public void setMedicamentosBean(SelMedicamentoBean medicamentosBean) {
        this.medicamentosBean = medicamentosBean;
    }

    public SelInsumosBean getInsumosBean() {
        insumosBean = (SelInsumosBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selInsumosBean");
        return insumosBean;
    }

    public void setInsumosBean(SelInsumosBean insumosBean) {
        this.insumosBean = insumosBean;
    }

    public SelPaquetesBean getPaquetesBean() {
        paquetesBean = (SelPaquetesBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selPaquetesBean");
        return paquetesBean;
    }

    public void setPaquetesBean(SelPaquetesBean paquetesBean) {
        this.paquetesBean = paquetesBean;
    }

    public String getTituloMensaje() {
        return tituloMensaje;
    }

    public void setTituloMensaje(String tituloMensaje) {
        this.tituloMensaje = tituloMensaje;
    }

    public String getSubtituloMensaje() {
        return subtituloMensaje;
    }

    public void setSubtituloMensaje(String subtituloMensaje) {
        this.subtituloMensaje = subtituloMensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getAuNoSolicitudServicio().Accion(this);
    }

    public void refrescarAfiliado() {
        getAuNoSolicitudServicio().listarAfiliado(this);
    }

    public void refrescarPrestador() {
        getAuNoSolicitudServicio().listarPrestadores(this);
    }
    
    public void refrescarPrestadorEntrega() {
        getAuNoSolicitudServicio().listarPrestadoresEntrega(this);
    }
    
    public void refrescarProcedimiento() {
        getAuNoSolicitudServicio().listarProcedimientos(this);
    }
    
    public void refrescarMedicamento() {
        getAuNoSolicitudServicio().listarMedicamentos(this);
    }
    
    public void refrescarInsumo() {
        getAuNoSolicitudServicio().listarInsumos(this);
    }
    
    public void refrescarPaquete() {
        getAuNoSolicitudServicio().listarPaquetes(this);
    }
    
    public void crear() {
        super.setAccion(Url.ACCION_CREAR);
        super.setDoAccion(Url.ACCION_CREAR);
        getAuNoSolicitudServicio().Accion(this);        
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        if(getObjeto().getAsegAfiliadosId() == null){
            addError("El afiliado es obligatorio");
        }
        if(getObjeto().getCntPrestadorId() == null){
            addError("El prestador solicita es obligatorio");
        }
        if(getObjeto().getCntPrestadorEntregaId() == null){
            addError("El prestador entrega es obligatorio");
        }
        if(getObjeto().getListAuNoSolicitudDiagnostico().isEmpty()){
            addError("El diagnostico es obligatorio");
        }
        if(getObjeto().getListAuNoSolicitudItem().isEmpty()){
            addError("El item es obligatorio");
        }
        if(getObjeto().getCntProfesionalesId() != null){
            if(!getObjeto().getCntProfesionalesId().getDocumento().isBlank() 
                    || getObjeto().getCntProfesionalesId().getMaeTipoCodumentoId() > 0){
                if(getObjetoProfesionalPrestador() != null){
                    if(getObjetoProfesionalPrestador().getMaEspecialidadId() == 0){
                        addError("La especialidad del profesional es obligatorio");
                    }
                }
            }
        }
        if (!super.isError()) {
            super.setAccion(Url.ACCION_GUARDAR);
            getAuNoSolicitudServicio().Accion(this);
        }
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }
    
    public void ver(int _id) throws IOException {
        getObjeto().setId(_id);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_VER);
        getAuNoSolicitudServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmVer");
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }
    
    public void ventanaAgregarItem() {
        super.setAccion(Url.ACCION_CREAR);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        super.setTakeAccion(Url.ACCION_CREAR);
        getAuNoSolicitudServicio().Accion(this);
        if(!super.isError()){
            PrimeFaces.current().ajax().update("frmItemCrear");
            PrimeFaces.current().executeScript("PF('dlgItemCrear').show()");
        }
        procesoFinal();
    }
    
    public void ventanaAgregarAdjuntos() {
        super.setAccion(Url.ACCION_CREAR);
        super.setDoAccion(Url.ACCION_ADICIONAL_2);
        super.setTakeAccion(Url.ACCION_CREAR);
        getAuNoSolicitudServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmAuditoriaAdjuntoProgramas");
            PrimeFaces.current().resetInputs("frmAdjunto");
            PrimeFaces.current().ajax().update("frmAdjunto");
            PrimeFaces.current().executeScript("PF('dialogoAdjuntos').show();");
        }
        procesoFinal();
    }
    
    public void ventanaAgregarEntregasAdjuntos() {
        super.setAccion(Url.ACCION_ADICIONAL_3);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        getAuNoSolicitudServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmAuditoriaAdjuntoEntregas");
            PrimeFaces.current().resetInputs("frmAdjuntoEntrega");
            PrimeFaces.current().ajax().update("frmAdjuntoEntrega");
            PrimeFaces.current().executeScript("PF('dialogoAdjuntosEntregas').show();");
        }
        procesoFinal();
    }
    
    public void editar(int _id, int estado) {
        getObjeto().setId(_id);
        AuNoSolicitud solicitudBD = getAuNoSolicitudServicio().consultarAuNoSolicitud(this, _id);
        if(estado != solicitudBD.getEstado()){
            addError("por favor refrecar la pantalla");
        }
        if(!super.isError()){
            super.setAccion(ACCION_EDITAR);
            super.setDoAccion(ACCION_EDITAR);
            getAuNoSolicitudServicio().Accion(this);
        }
        
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmEditar");
            PrimeFaces.current().ajax().update("frmEditar");
            PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        }
        procesoFinal();
    }
    
    public void modificar() {
        
        super.setAccion(ACCION_MODIFICAR);
        getAuNoSolicitudServicio().Accion(this);
        
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }
    
    public void ventanaGestionar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_ADICIONAL_1);
        getAuNoSolicitudServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmGestionar");
            PrimeFaces.current().ajax().update("frmGestionar");
            PrimeFaces.current().executeScript("PF('dialogoGestionar').show();");
        }
        procesoFinal();
    }
    
    public void abrirVentanaEntrega(AuNoSolicitudItem item) {
        setObjetoEntrega(new AuNoSolicitudEntrega());
        getObjetoEntrega().setAuNoSolicitudesId(getObjeto());
        getObjetoEntrega().setAuNoSolicitudItemsId(item);
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_CREAR);
        getAuNoSolicitudServicio().Accion(this);
        if(!super.isError()){
            PrimeFaces.current().ajax().update("frmEntrega");
            PrimeFaces.current().executeScript("PF('dialogoEntregar').show()");
        }
        procesoFinal();
    }
    
    public void guardarEntregar() {
        int sumatoriBlack = 0 ;
        for(AuNoSolicitudEntregaDetalle entregaDetalle: getObjetoEntrega().getListAuNoSolicitudEntregaDetalle()){
            if(entregaDetalle.getCantidadAEntregar() == null){
                sumatoriBlack = sumatoriBlack + 1;
            }else if(entregaDetalle.getCantidadAEntregar().isBlank()){
                sumatoriBlack = sumatoriBlack + 1;
            }
        }
        if(sumatoriBlack == getObjetoEntrega().getListAuNoSolicitudEntregaDetalle().size()){
            addError("Debe seleccionar por lo menos una entrega");
        }
        AsegRegistroNovedad ultimaNovedad = getAuNoSolicitudServicio().consultarUltinaNovedadInactivoAfiliado(this);
        if(ultimaNovedad != null){
            if(getObjetoEntrega().getFechaHoraEntrega().after(ultimaNovedad.getFechaNovedad())){ 
                addError("La fecha de la prestación no puede ser mayor a a fecha de novedad");
            }
        }
        if(!super.isError()){
            super.setAccion(ACCION_ADICIONAL_3);
            super.setDoAccion(ACCION_GUARDAR);
            getAuNoSolicitudServicio().Accion(this);
        }
        if(!super.isError()){
            PrimeFaces.current().ajax().update("frmGestionar:pItemGestionar");
            PrimeFaces.current().ajax().update("frmSinAutorizaciones");
            PrimeFaces.current().executeScript("PF('dialogoEntregar').hide()");
        }
        procesoFinal();
    }
    
    public void verEntregas(int id) {
        setObjetoItem(new AuNoSolicitudItem(id));
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_ADICIONAL_2);
        super.setTakeAccion(Url.ACCION_LISTAR);
        getAuNoSolicitudServicio().Accion(this);
        if(!super.isError()){
            PrimeFaces.current().ajax().update("frmVerEntregas");
            PrimeFaces.current().executeScript("PF('dialogoEntregas').show()");
        }
        procesoFinal();
    }
    
    public void verEntrega(int id) {
        setObjetoEntrega(new AuNoSolicitudEntrega(id));
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_ADICIONAL_2);
        super.setTakeAccion(Url.ACCION_VER);
        getAuNoSolicitudServicio().Accion(this);
        if(!super.isError()){
            PrimeFaces.current().ajax().update("frmVerEntrega");
            PrimeFaces.current().executeScript("PF('dialogoEntrega').show()");
        }
        procesoFinal();
    }
    
    public void ventanaEntregaVer(int id) {
        setObjetoEntrega(new AuNoSolicitudEntrega(id));
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_ADICIONAL_2);
        getAuNoSolicitudServicio().Accion(this);
        if(!super.isError()){
            PrimeFaces.current().ajax().update("frmVerEntrega");
            PrimeFaces.current().executeScript("PF('dialogoEntrega').show()");
        }
        procesoFinal();
    }
    
    public void verAnularEntrega(int idEntrega) {
        setObjetoEntrega(new AuNoSolicitudEntrega(idEntrega));
        super.setAccion(Url.ACCION_ADICIONAL_4);
        super.setDoAccion(Url.ACCION_CREAR);
        getAuNoSolicitudServicio().Accion(this);
        if(!super.isError()){
            PrimeFaces.current().ajax().update("frmAnularEntrega");
            PrimeFaces.current().executeScript("PF('dialogoAnularEntrega').show()");
        }
        procesoFinal();
    }
    
    public void anularEntrega() {
        super.setAccion(Url.ACCION_ADICIONAL_4);
        super.setDoAccion(Url.ACCION_GUARDAR);
        getAuNoSolicitudServicio().Accion(this);
        if(!super.isError()){
            PrimeFaces.current().executeScript("PF('dialogoAnularEntrega').hide()");
            PrimeFaces.current().ajax().update("frmVerEntregas");
            PrimeFaces.current().ajax().update("frmGestionar");
            PrimeFaces.current().ajax().update("frmSinAutorizaciones");
        }
        procesoFinal();
    }
    
    public void ventanaNoPrestado(AuNoSolicitudItem item) {
        setObjetoEntrega(new AuNoSolicitudEntrega());
        getObjetoEntrega().setAuNoSolicitudesId(getObjeto());
        getObjetoEntrega().setAuNoSolicitudItemsId(item);
        getObjetoEntrega().setCantidadPorEntregar(item.getCantidadSolicitada());
        getObjetoEntrega().setCantidadPendiente(item.getCantidadSolicitada());
        getObjetoEntrega().setCantidadEntregada(0);
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_CREAR);
        getAuNoSolicitudServicio().Accion(this);
        if(!super.isError()){
            PrimeFaces.current().resetInputs("frmNoPrestadoItem");
            PrimeFaces.current().ajax().update("frmNoPrestadoItem");
            PrimeFaces.current().executeScript("PF('dialogoNoPrestado').show()");
        }
        procesoFinal();
    }
    
    public void noPrestado() {
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_GUARDAR);
        getAuNoSolicitudServicio().Accion(this);
        if(!super.isError()){
            PrimeFaces.current().ajax().update("frmGestionar");
            PrimeFaces.current().ajax().update("frmSinAutorizaciones");
            PrimeFaces.current().executeScript("PF('dialogoNoPrestado').hide()");
        }
        procesoFinal();
    }
    
    public void ventanaAnularSinAutorizacion(int id) {
        setObjeto(new AuNoSolicitud(id));
        super.setAccion(Url.ACCION_ADICIONAL_5);
        super.setDoAccion(Url.ACCION_CREAR);
        getAuNoSolicitudServicio().Accion(this);
        if(!super.isError()){
            PrimeFaces.current().executeScript("PF('dialogoAnularSinEntrega').show()");
            PrimeFaces.current().ajax().update("frmAnularEntrega");
        }
        procesoFinal();
    }
    
    public void anularSinAutorizacion() {
        super.setAccion(Url.ACCION_ADICIONAL_5);
        super.setDoAccion(Url.ACCION_GUARDAR);
        getAuNoSolicitudServicio().Accion(this);
        if(!super.isError()){
            PrimeFaces.current().executeScript("PF('dialogoAnularSinEntrega').hide()");
            PrimeFaces.current().ajax().update("frmSinAutorizaciones");
        }
        procesoFinal();
    }
    
    public void verBitacorasSinAutorizaciones(int id) {
        getObjeto().setId(id);
        getAuNoSolicitudServicio().verBitacorasSinAutorizaciones(this);
        PrimeFaces.current().executeScript("PF('dialogoVerBitacorasSinSolicitud').show()");
        PrimeFaces.current().ajax().update("frmVerBitacorasSinSolicitud");
        generarMensajes();
    }
    
    public void editarTecnologia(int id) {
        boolean gestionar = false;
        setObjetoItem(new AuNoSolicitudItem());
        for (AuNoSolicitudItem tecnologia : getObjeto().getListAuNoSolicitudItem() ) {
            if (tecnologia.getMaTecnologiaId() == id) {
                setObjetoItem(tecnologia);
                gestionar = true;
            }
        }
        if (gestionar) {
            super.setAccion(Url.ACCION_EDITAR);
            super.setDoAccion(Url.ACCION_ADICIONAL_1);
            super.setTakeAccion(Url.ACCION_EDITAR);
            getAuNoSolicitudServicio().Accion(this);
            if(!super.isError()){
                switch (getObjetoItem().getTipoTecnologia()) {
                    case AuConstantes.ID_TECNOLOGIA:
                        PrimeFaces.current().ajax().update("frmItemTecnologiaEditar");
                        PrimeFaces.current().executeScript("PF('dlgItemTecnologiaEditar').show()");
                        break;
                    case AuConstantes.ID_MEDICAMENTO:
                        PrimeFaces.current().ajax().update("frmItemMedicamentoEditar");
                        PrimeFaces.current().executeScript("PF('dlgItemMedicamentoEditar').show()");
                        break;
                    case AuConstantes.ID_AGRUPADOR_MEDICAMENTO:
                        PrimeFaces.current().ajax().update("frmItemMedicamentoEditar");
                        PrimeFaces.current().executeScript("PF('dlgItemMedicamentoEditar').show()");
                        break;
                    case AuConstantes.ID_INSUMO:
                        PrimeFaces.current().ajax().update("frmItemInsumoEditar");
                        PrimeFaces.current().executeScript("PF('dlgItemInsumoEditar').show()");
                        break;
                    case AuConstantes.ID_PAQUETE:
                        PrimeFaces.current().ajax().update("frmItemPaqueteEditar");
                        PrimeFaces.current().executeScript("PF('dlgItemPaqueteEditar').show()");
                        break;
                }
            }
        } else {
            addError("No se encontro la tecnologia");
        }
        procesoFinal();
    }
    
    public void modificarItemTecnologia(){
        super.setAccion(Url.ACCION_EDITAR);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        super.setTakeAccion(Url.ACCION_MODIFICAR);
        getAuNoSolicitudServicio().Accion(this);
        if(!super.isError()){
            switch (getObjetoItem().getTipoTecnologia()) {
                case AuConstantes.ID_TECNOLOGIA:
                    PrimeFaces.current().ajax().update("frmEditar:pItemEditar");
                    PrimeFaces.current().executeScript("PF('dlgItemTecnologiaEditar').hide()");
                    break;
                case AuConstantes.ID_MEDICAMENTO:
                    PrimeFaces.current().ajax().update("frmEditar:pItemEditar");
                    PrimeFaces.current().executeScript("PF('dlgItemMedicamentoEditar').hide()");
                    break;
                case AuConstantes.ID_AGRUPADOR_MEDICAMENTO:
                    PrimeFaces.current().ajax().update("frmEditar:pItemEditar");
                    PrimeFaces.current().executeScript("PF('dlgItemMedicamentoEditar').hide()");
                    break;
                case AuConstantes.ID_INSUMO:
                    PrimeFaces.current().ajax().update("frmEditar:pItemEditar");
                    PrimeFaces.current().executeScript("PF('dlgItemInsumoEditar').hide()");
                    break;
                case AuConstantes.ID_PAQUETE:
                    PrimeFaces.current().ajax().update("frmEditar:pItemEditar");
                    PrimeFaces.current().executeScript("PF('dlgItemPaqueteEditar').hide()");
                    break;
            }
        }
        procesoFinal();
    }
    
    public void calcularCatidadesEntegas(RowEditEvent event) {
        setObjetoEntregaDetalle(new AuNoSolicitudEntregaDetalle());
        setObjetoEntregaDetalle((AuNoSolicitudEntregaDetalle) event.getObject());
         //int numero = cantidadTotal;
        boolean validate = true;
        // funcion para validar la cantidad a entregar no supero los faltante o la cantidad total
        if(getObjetoEntregaDetalle().getFaltantes() > 0){
            if(getObjetoEntregaDetalle().getCantidadAEntregar() != null 
                    && !getObjetoEntregaDetalle().getCantidadAEntregar().isBlank()){
                if(Integer.parseInt(getObjetoEntregaDetalle().getCantidadAEntregar()) > getObjetoEntregaDetalle().getFaltantes()){
                    getObjetoEntregaDetalle().setCantidadAEntregar("");
                    validate = false;
                }
            }
        }else if(getObjetoEntregaDetalle().getCatidadTotal() > 0){
            if(getObjetoEntregaDetalle().getCantidadAEntregar() != null 
                    && !getObjetoEntregaDetalle().getCantidadAEntregar().isBlank()){
                if(Integer.parseInt(getObjetoEntregaDetalle().getCantidadAEntregar()) > getObjetoEntregaDetalle().getCatidadTotal()){
                    getObjetoEntregaDetalle().setCantidadAEntregar("");
                    validate = false;
                }
            }
        }
        if(validate){
            int sumarCantidadEntregada = 0;
            int sumarCantidadAEntregar = 0;
            int pendientes = 0;
            for(AuNoSolicitudEntregaDetalle entregaDetalle: getObjetoEntrega().getListAuNoSolicitudEntregaDetalle()){
                if(entregaDetalle.getCantidadAEntregar() != null
                        && !entregaDetalle.getCantidadAEntregar().isBlank()){
                    sumarCantidadAEntregar = sumarCantidadAEntregar + Integer.parseInt(entregaDetalle.getCantidadAEntregar());
                }
            }
            getObjetoEntrega().setSumatoriaCantidadAEntregar(sumarCantidadAEntregar);
            for(AuNoSolicitudEntregaDetalle entregaDetalle: getObjetoEntrega().getListAuNoSolicitudEntregaDetalle()){
                if(entregaDetalle.getCantidadEntregada() > 0){
                    int cantidad = (entregaDetalle.getCantidadAEntregar() != null && !entregaDetalle.getCantidadAEntregar().isBlank()) ? Integer.parseInt(entregaDetalle.getCantidadAEntregar()) : 0;
                    sumarCantidadEntregada = sumarCantidadEntregada + cantidad + entregaDetalle.getCantidadEntregada();
                }else if(entregaDetalle.getCantidadAEntregar() != null 
                        && !entregaDetalle.getCantidadAEntregar().isBlank()){
                    sumarCantidadEntregada = sumarCantidadEntregada +Integer.parseInt(entregaDetalle.getCantidadAEntregar());
                }
            }
            //getObjetoEntrega().setCantidadPorEntregar(getObjetoEntrega().getAuNoSolicitudItemsId().getCantidadSolicitada());
            getObjetoEntrega().setCantidadEntregada(sumarCantidadEntregada);
            int sumarCantidadPediente = 0;
            for(AuNoSolicitudEntregaDetalle entregaDetalle: getObjetoEntrega().getListAuNoSolicitudEntregaDetalle()){
                sumarCantidadPediente = sumarCantidadPediente + entregaDetalle.getCantidadEntregada();
            }
            getObjetoEntrega().setHabilitarCausaNoEntrega(false);
            pendientes = getObjetoEntrega().getAuNoSolicitudItemsId().getCantidadSolicitada() - getObjetoEntrega().getCantidadEntregada();
            if(getObjetoEntrega().getCantidadEntregada() == getObjetoEntrega().getAuNoSolicitudItemsId().getCantidadSolicitada()){
                getObjetoEntrega().setHabilitarCausaNoEntrega(true);
                //pendientes = getObjetoEntrega().getAuNoSolicitudItemsId().getCantidadSolicitada() - sumarCantidadPediente;
            }

            getObjetoEntrega().setCantidadPendiente(pendientes);
            getObjetoEntrega().setCantidadPorEntregar(pendientes);
            if (getObjetoEntrega().getCantidadEntregada() > 0) {
                getObjetoEntrega().setMaeCausaMoEntregaId(null);
                getObjetoEntrega().setMaeCausaMoEntregaCodigo(null);
                getObjetoEntrega().setMaeCausaMoEntregaValor(null);
            }
        }else{
            addError("la cantidad a entregar supera a los faltantes o a la entrega total");
        }
        PrimeFaces.current().ajax().update("frmEntrega:cantidadPendiente");
        PrimeFaces.current().ajax().update("frmEntrega:sumatoriaCantidadEntregar");
        PrimeFaces.current().ajax().update("frmEntrega:cantidadPendienteGestionar");
        PrimeFaces.current().ajax().update("frmEntrega:cantidadEntregadaGestionar");
        PrimeFaces.current().ajax().update("frmEntrega:causaEntregaGestionar");
        generarMensajes();
    }
    
    public void cargarArchivoDocumentoEntrega(FileUploadEvent event) throws IOException {
        archivoAdjunto = event.getFile();
        try {
            if (getObjetoAdjunto().getMaeTipoArchivoId() == null) {
                addError("Tipo: Error de validación: se necesita un valor.");
                generarMensajes();
                return;
            }
            

            String ruta = PropApl.getInstance().get(PropApl.AU_A3_ADJUNTOS);
            if (ruta == null || ruta.isEmpty()) {
                addError("No esta configurada la ruta para los anticipos del sistema");
                return;
            }

            AuSolicitudAdjunto borrarObj = new AuSolicitudAdjunto();
            for (AuSolicitudAdjunto peAdjunto : listaAuSolicitudAdjunto) {
                if (peAdjunto.getMaeTipoArchivoId().equals(getObjetoAdjunto().getMaeTipoArchivoId())) {
                    borrarObj = peAdjunto;
                    break;
                }
            }
            listaAuSolicitudAdjunto.remove(borrarObj);
            getObjetoAdjunto().setAdjuntoStream(archivoAdjunto.getInputStream());
            String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
            int indiceExtension = nombreAdjunto.lastIndexOf(".");
            getObjetoAdjunto().setExtension(nombreAdjunto.substring(indiceExtension, nombreAdjunto.length()));
            getObjetoAdjunto().setNombre(nombreAdjunto);
            getObjetoAdjunto().setOrigen(AuSolicitudAdjunto.ORIGEN_NO_SOLICITUDES_ENTREGAS);
            getObjetoAdjunto().setRuta(ruta);
            getObjetoAdjunto().setExiste(Boolean.TRUE);
            Maestro tipoArchivo = hashTiposAdjuntos.get(getObjetoAdjunto().getMaeTipoArchivoId());
            if (tipoArchivo != null) {
                getObjetoAdjunto().setMaeTipoArchivoCodigo(tipoArchivo.getValor());
                getObjetoAdjunto().setMaeTipoArchivoValor(tipoArchivo.getNombre());
                //getObjetoAdjunto().setMaeTipoArchivoTipo(tipoArchivo.getTipo());
            }
            //generar nombre archivo
            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
            StringBuilder nombreArchivo = new StringBuilder();
            nombreArchivo.append("au_no_autorizacion_").append(getObjetoAdjunto().getMaeTipoArchivoCodigo())
                    .append("_").append(sdf.format(new Date()));
            nombreArchivo = new StringBuilder(Util.reemplazarCaracteresEspeciales(nombreArchivo.toString()));
            getObjetoAdjunto().setArchivo(nombreArchivo.append(getObjetoAdjunto().getExtension()).toString());
            listaAuSolicitudAdjunto.add(getObjetoAdjunto());
            setObjetoAdjunto(new AuSolicitudAdjunto());
            PrimeFaces.current().ajax().update("frmAdjuntoEntrega");
        } catch (IOException ex) {
            this.addError(ex.toString());
            generarMensajes();
        }
    }
    
    
    public void cargarArchivoDocumento(FileUploadEvent event) throws IOException {
        archivoAdjunto = event.getFile();
        try {
           
            if (getObjetoAdjunto().getMaeTipoArchivoId() == null) {
                addError("Tipo: Error de validación: se necesita un valor.");
                generarMensajes();
                return;
            }
           

            String ruta = PropApl.getInstance().get(PropApl.AU_A3_ADJUNTOS);
            if (ruta == null || ruta.isEmpty()) {
                addError("No esta configurada la ruta para los anticipos del sistema");
                return;
            }

            AuSolicitudAdjunto borrarObj = new AuSolicitudAdjunto();
            for (AuSolicitudAdjunto peAdjunto : listaAuSolicitudAdjunto) {
                if (peAdjunto.getMaeTipoArchivoId().equals(getObjetoAdjunto().getMaeTipoArchivoId())) {
                    borrarObj = peAdjunto;
                    break;
                }
            }
            listaAuSolicitudAdjunto.remove(borrarObj);
            getObjetoAdjunto().setAdjuntoStream(archivoAdjunto.getInputStream());
            String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
            int indiceExtension = nombreAdjunto.lastIndexOf(".");
            getObjetoAdjunto().setExtension(nombreAdjunto.substring(indiceExtension, nombreAdjunto.length()));
            getObjetoAdjunto().setNombre(nombreAdjunto);
            getObjetoAdjunto().setOrigen(AuSolicitudAdjunto.ORIGEN_NO_SOLICITUDES);
            getObjetoAdjunto().setRuta(ruta);
            getObjetoAdjunto().setExiste(Boolean.TRUE);
            Maestro tipoArchivo = hashTiposAdjuntos.get(getObjetoAdjunto().getMaeTipoArchivoId());
            if (tipoArchivo != null) {
                getObjetoAdjunto().setMaeTipoArchivoCodigo(tipoArchivo.getValor());
                getObjetoAdjunto().setMaeTipoArchivoValor(tipoArchivo.getNombre());
                //getObjetoAdjunto().setMaeTipoArchivoTipo(tipoArchivo.getTipo());
            }
            //generar nombre archivo
            SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
            StringBuilder nombreArchivo = new StringBuilder();
            nombreArchivo.append("au_no_autorizacion_").append(getObjetoAdjunto().getMaeTipoArchivoCodigo())
                    .append("_").append(sdf.format(new Date()));
            nombreArchivo = new StringBuilder(Util.reemplazarCaracteresEspeciales(nombreArchivo.toString()));
            getObjetoAdjunto().setArchivo(nombreArchivo.append(getObjetoAdjunto().getExtension()).toString());
            listaAuSolicitudAdjunto.add(getObjetoAdjunto());
            setObjetoAdjunto(new AuSolicitudAdjunto());
            PrimeFaces.current().ajax().update("frmAdjunto");
        } catch (IOException ex) {
            this.addError(ex.toString());
            generarMensajes();
        }
    }
    
    @SuppressWarnings("ConvertToTryWithResources")
    public void descargarAnticipoAdjunto(AuSolicitudAdjunto adjunto) {
        String rutaCompleta = adjunto.getRuta() + adjunto.getArchivo();
        try {
            File file = new File(rutaCompleta);
            byte[] exportContent = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(exportContent);
            int i = rutaCompleta.lastIndexOf(".");
            String ext = rutaCompleta.substring(i, rutaCompleta.length());
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.responseReset();
            ec.setResponseContentLength(exportContent.length);
            String attachmentName = "attachment; filename=\"" + adjunto.getNombre() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".pdf")) {
                ec.setResponseContentType("application/pdf");
            } else {
                throw new Exception();
            }
            OutputStream output = ec.getResponseOutputStream();
            output.write(exportContent);
            output.close();
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
        }
        procesoFinal();
    }
    
    public void guardarSinAutoriazacion() {
        if (getListaAuSolicitudAdjunto().isEmpty()) {
            addError("No tiene adjuntos para guardar");
        }
        for(AuSolicitudAdjunto adjunto: getListaAuSolicitudAdjunto()){
            getObjeto().getListAuSolicitudAdjunto().add(adjunto);
        }
        
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmAdjunto");
            PrimeFaces.current().ajax().update("frmCrear:pAdjuntoCrear");
            PrimeFaces.current().ajax().update("frmEditar:pAdjuntoEditar");
            PrimeFaces.current().executeScript("PF('dialogoAdjuntos').hide();");
        }
        procesoFinal();
    }
    
    public void guardarSinAutoriazacionEntregas() {
        if (getListaAuSolicitudAdjunto().isEmpty()) {
            addError("No tiene adjuntos para guardar");
        }
        getObjetoEntrega().setListAuSolicitudAdjunto(getListaAuSolicitudAdjunto());
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmAdjuntoEntrega");
            PrimeFaces.current().ajax().update("frmEntrega:pAdjuntoEntregaCrear");
            PrimeFaces.current().executeScript("PF('dialogoAdjuntosEntregas').hide();");
        }
        procesoFinal();
    }
    
    public void borrarAuSolicitidMemoriaAdjunto(String nombre) {
        AuSolicitudAdjunto borraObjecto = new AuSolicitudAdjunto();
        for (AuSolicitudAdjunto peAdjunto : listaAuSolicitudAdjunto) {
            if (peAdjunto.getNombre().equals(nombre)) {
                borraObjecto = peAdjunto;
                break;
            }
        }
        listaAuSolicitudAdjunto.remove(borraObjecto);
        PrimeFaces.current().ajax().update("frmAdjunto");
    }
    
    public void borrarAuSolicitidAdjunto(String nombre) {
        AuSolicitudAdjunto borraObjecto = new AuSolicitudAdjunto();
        for (AuSolicitudAdjunto peAdjunto : getObjeto().getListAuSolicitudAdjunto()) {
            if (peAdjunto.getNombre().equals(nombre)) {
                borraObjecto = peAdjunto;
                break;
            }
        }
        getObjeto().getListAuSolicitudAdjunto().remove(borraObjecto);
        PrimeFaces.current().ajax().update("frmCrear:pAdjuntoCrear");
        PrimeFaces.current().ajax().update("frmEditar:pAdjuntoEditar");
    }
    
    public void borrarAuSolicitidEntregaMemoriaAdjunto(String nombre) {
        AuSolicitudAdjunto borraObjecto = new AuSolicitudAdjunto();
        for (AuSolicitudAdjunto peAdjunto : listaAuSolicitudAdjunto) {
            if (peAdjunto.getNombre().equals(nombre)) {
                borraObjecto = peAdjunto;
                break;
            }
        }
        listaAuSolicitudAdjunto.remove(borraObjecto);
        PrimeFaces.current().ajax().update("frmAdjuntoEntrega");
    }
    
    public void borrarAuSolicitidEntregaAdjunto(String nombre) {
        AuSolicitudAdjunto borraObjecto = new AuSolicitudAdjunto();
        for (AuSolicitudAdjunto peAdjunto : getObjetoEntrega().getListAuSolicitudAdjunto()) {
            if (peAdjunto.getNombre().equals(nombre)) {
                borraObjecto = peAdjunto;
                break;
            }
        }
        getObjetoEntrega().getListAuSolicitudAdjunto().remove(borraObjecto);
        PrimeFaces.current().ajax().update("frmEntrega:pAdjuntoEntregaCrear");
    }
    
    public void guardarItemTecnologia() {
        boolean validate = true;
        if (getObjetoItem().getMaTecnologiaId() == 0) {
            addError("La tecnología es obligatoria");
            validate = false;
        }      
        if(getObjetoItem().getCantidadSolicitada() <= 0){
            addError("La cantidad tiene que ser mayor a 0");
            validate = false;
        }
        if(getObjetoItem().getDuracionTratamiento() < getObjetoItem().getNumEntregas()){
            addError("La duración de tratamiento No puede ser menor al número de entragas");
            validate = false;
        }
        if(getObjetoItem().getCantidadSolicitada() < getObjetoItem().getNumEntregas()){
            addError("La cantidad No puede ser menor al número entregas ");
            validate = false;
        }
        if(getObjetoItem().getNumEntregas() > 12){
            addError("El número de entregas no puede ser mayor a 12");
            validate = false;
        }
        String mensajeValidacion = getAuNoSolicitudServicio().validarCantidadTecnologia("" + getObjetoItem().getTipoTecnologia(), getObjetoItem().getCantidadSolicitada(),getObjetoItem().getMaTecnologiaCodigo());
        if(mensajeValidacion != null){
            addError(mensajeValidacion);
            validate = false;
        }
        if(validate){
            getObjetoItem().setEstado(AuNoSolicitudItem.ESTADO_PENDIENTE);
            getObjetoItem().setPosicion(getObjeto().getListAuNoSolicitudItem().size());
            getObjeto().getListAuNoSolicitudItem().add(getObjetoItem());
        }
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmCrear:pItemCrear");
            PrimeFaces.current().ajax().update("frmEditar:pItemEditar");
            PrimeFaces.current().executeScript("PF('dlgItemTecnologiaCrear').hide();");
            PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').hide()");
        }
        procesoFinal();
    }
     
    public void guardarItemMedicamento() {
        boolean validate = true;
        if (getObjetoItem().getMaTecnologiaId() == 0) {
            addError("La tecnología es obligatoria");
            validate = false;
        }      
        if(getObjetoItem().getCantidadSolicitada() <= 0){
            addError("La cantidad tiene que ser mayor a 0");
            validate = false;
        }
        if(getObjetoItem().getDuracionTratamiento() < getObjetoItem().getNumEntregas()){
            addError("La duración de tratamiento No puede ser menor al número de entragas");
            validate = false;
        }
        if(getObjetoItem().getCantidadSolicitada() < getObjetoItem().getNumEntregas()){
            addError("La cantidad No puede ser menor al número entregas ");
            validate = false;
        }
        if(getObjetoItem().getNumEntregas() > 12){
            addError("El número de entregas no puede ser mayor a 12");
            validate = false;
        }
        String mensajeValidacion = getAuNoSolicitudServicio().validarCantidadTecnologia("" + getObjetoItem().getTipoTecnologia(), getObjetoItem().getCantidadSolicitada(),getObjetoItem().getMaTecnologiaCodigo());
        if(mensajeValidacion != null){
            addError(mensajeValidacion);
            validate = false;
        }
        if(validate){
            Maestro maeViaAdmistracion = getHashViaAdmistracion().get(getObjetoItem().getMaeViaAdministracionId());
            if(maeViaAdmistracion != null){
                getObjetoItem().setMaeViaAdministracionCodigo(maeViaAdmistracion.getValor());
                getObjetoItem().setMaeViaAdministracionValor(maeViaAdmistracion.getNombre());
                getObjetoItem().setMaeViaAdministracionTipo(maeViaAdmistracion.getTipo());
            }
            getObjetoItem().setEstado(AuNoSolicitudItem.ESTADO_PENDIENTE);
            getObjetoItem().setPosicion(getObjeto().getListAuNoSolicitudItem().size());
            getObjeto().getListAuNoSolicitudItem().add(getObjetoItem());
        }
        
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmCrear:pItemCrear");
            PrimeFaces.current().ajax().update("frmEditar:pItemEditar");
            PrimeFaces.current().executeScript("PF('dlgItemMedicamentoCrear').hide();");
            PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').hide()");
        }
        procesoFinal();
    }
    
    public void guardarItemInsumo() {
        boolean validate = true;
        if (getObjetoItem().getMaTecnologiaId() == 0) {
            addError("La tecnología es obligatoria");
            validate = false;
        }      
        if(getObjetoItem().getCantidadSolicitada() <= 0){
            addError("La cantidad tiene que ser mayor a 0");
            validate = false;
        }
        if(getObjetoItem().getDuracionTratamiento() < getObjetoItem().getNumEntregas()){
            addError("La duración de tratamiento No puede ser menor al número de entragas");
            validate = false;
        }
        if(getObjetoItem().getCantidadSolicitada() < getObjetoItem().getNumEntregas()){
            addError("La cantidad No puede ser menor al número entregas ");
            validate = false;
        }
        if(getObjetoItem().getNumEntregas() > 12){
            addError("El número de entregas no puede ser mayor a 12");
            validate = false;
        }
        String mensajeValidacion = getAuNoSolicitudServicio().validarCantidadTecnologia("" + getObjetoItem().getTipoTecnologia(), getObjetoItem().getCantidadSolicitada(),getObjetoItem().getMaTecnologiaCodigo());
        if(mensajeValidacion != null){
            addError(mensajeValidacion);
            validate = false;
        }
        if(validate){
            getObjetoItem().setEstado(AuNoSolicitudItem.ESTADO_PENDIENTE);
            getObjetoItem().setPosicion(getObjeto().getListAuNoSolicitudItem().size());
            getObjeto().getListAuNoSolicitudItem().add(getObjetoItem());
        }
        
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmCrear:pItemCrear");
            PrimeFaces.current().ajax().update("frmEditar:pItemEditar");
            PrimeFaces.current().executeScript("PF('dlgItemInsumoCrear').hide();");
            PrimeFaces.current().executeScript("PF('dialogoCrearContratoInsumo').hide();");
            PrimeFaces.current().executeScript("PF('dialogoInsumoBusquedaPrestador').hide()");
            PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').hide()");
        }
        procesoFinal();
    }
    
    public void guardarItemPaquete() {
        boolean validate = true;
        if (getObjetoItem().getMaTecnologiaId() == 0) {
            addError("La tecnología es obligatoria");
            validate = false;
        }      
        if(getObjetoItem().getCantidadSolicitada() <= 0){
            addError("La cantidad tiene que ser mayor a 0");
            validate = false;
        }
        if(getObjetoItem().getDuracionTratamiento() < getObjetoItem().getNumEntregas()){
            addError("La duración de tratamiento No puede ser menor al número de entragas");
            validate = false;
        }
        if(getObjetoItem().getCantidadSolicitada() < getObjetoItem().getNumEntregas()){
            addError("La cantidad No puede ser menor al número entregas ");
            validate = false;
        }
        if(getObjetoItem().getNumEntregas() > 12){
            addError("El número de entregas no puede ser mayor a 12");
            validate = false;
        }
        String mensajeValidacion = getAuNoSolicitudServicio().validarCantidadTecnologia("" + getObjetoItem().getTipoTecnologia(), getObjetoItem().getCantidadSolicitada(),getObjetoItem().getMaTecnologiaCodigo());
        if(mensajeValidacion != null){
            addError(mensajeValidacion);
            validate = false;
        }
        if(validate){
            getObjetoItem().setEstado(AuNoSolicitudItem.ESTADO_PENDIENTE);
            getObjetoItem().setPosicion(getObjeto().getListAuNoSolicitudItem().size());
            getObjeto().getListAuNoSolicitudItem().add(getObjetoItem());
        }
        
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmCrear:pItemCrear");
            PrimeFaces.current().ajax().update("frmEditar:pItemEditar");
            PrimeFaces.current().executeScript("PF('dlgItemPaqueteCrear').hide();");
            PrimeFaces.current().executeScript("PF('dialogoPaqueteBusqueda').hide()");
        }
        procesoFinal();
    }
    
    public void verItemCrear(AuNoSolicitudItem obj) {
        setObjetoItem(obj);
        if (!super.isError()) {
            switch(getObjetoItem().getTipoTecnologia()){
                case AuConstantes.ID_TECNOLOGIA:
                    PrimeFaces.current().resetInputs("frmItemProcedimientoVer");
                    PrimeFaces.current().ajax().update("frmItemProcedimientoVer");
                    PrimeFaces.current().executeScript("PF('dlgItemProcedimientoVer').show();");
                    break;
                case AuConstantes.ID_MEDICAMENTO:
                    PrimeFaces.current().resetInputs("frmItemMedicamentoVer");
                    PrimeFaces.current().ajax().update("frmItemMedicamentoVer");
                    PrimeFaces.current().executeScript("PF('dlgItemMedicamentoVer').show();");
                    break;
                case AuConstantes.ID_INSUMO:
                    PrimeFaces.current().resetInputs("frmItemInsumoVer");
                    PrimeFaces.current().ajax().update("frmItemInsumoVer");
                    PrimeFaces.current().executeScript("PF('dlgItemInsumoVer').show();");
                    break;
                case AuConstantes.ID_PAQUETE: 
                    PrimeFaces.current().resetInputs("frmItemPaqueteVer");
                    PrimeFaces.current().ajax().update("frmItemPaqueteVer");
                    PrimeFaces.current().executeScript("PF('dlgItemPaqueteVer').show();");
                    break;
                case AuConstantes.ID_AGRUPADOR_MEDICAMENTO:
                    PrimeFaces.current().resetInputs("frmItemMedicamentoVer");
                    PrimeFaces.current().ajax().update("frmItemMedicamentoVer");
                    PrimeFaces.current().executeScript("PF('dlgItemMedicamentoVer').show();");
                    break;
            }
        }
    }
    
    public void verItem(int id) {
        setObjetoItem(new AuNoSolicitudItem());
        getObjetoItem().setId(id);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        getAuNoSolicitudServicio().Accion(this);
        if (!super.isError()) {
            switch(getObjetoItem().getTipoTecnologia()){
                case AuConstantes.ID_TECNOLOGIA:
                    PrimeFaces.current().resetInputs("frmItemProcedimientoVer");
                    PrimeFaces.current().ajax().update("frmItemProcedimientoVer");
                    PrimeFaces.current().executeScript("PF('dlgItemProcedimientoVer').show();");
                    break;
                case AuConstantes.ID_MEDICAMENTO:
                    PrimeFaces.current().resetInputs("frmItemMedicamentoVer");
                    PrimeFaces.current().ajax().update("frmItemMedicamentoVer");
                    PrimeFaces.current().executeScript("PF('dlgItemMedicamentoVer').show();");
                    break;
                case AuConstantes.ID_INSUMO:
                    PrimeFaces.current().resetInputs("frmItemInsumoVer");
                    PrimeFaces.current().ajax().update("frmItemInsumoVer");
                    PrimeFaces.current().executeScript("PF('dlgItemInsumoVer').show();");
                    break;
                case AuConstantes.ID_PAQUETE: 
                    PrimeFaces.current().resetInputs("frmItemPaqueteVer");
                    PrimeFaces.current().ajax().update("frmItemPaqueteVer");
                    PrimeFaces.current().executeScript("PF('dlgItemPaqueteVer').show();");
                    break;
                case AuConstantes.ID_AGRUPADOR_MEDICAMENTO:
                    PrimeFaces.current().resetInputs("frmItemMedicamentoVer");
                    PrimeFaces.current().ajax().update("frmItemMedicamentoVer");
                    PrimeFaces.current().executeScript("PF('dlgItemMedicamentoVer').show();");
                    break;
            }
        }
        procesoFinal();
    }
    
    public void consultarAfiliado() {
        if (!super.isError()) {
            cargaLazyAfiliado();
            PrimeFaces.current().ajax().update("frmAfiliadoLista");
            PrimeFaces.current().executeScript("PF('dialogoAfiliadoLista').show()");
        }
        procesoFinal();
    }

    public void cargaLazyAfiliado() {
        //Afiliado
        lazyAfiliados = new LazyDataModel<AsegAfiliado>() {
            private List<AsegAfiliado> afiliados;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(0).getCantidadRegistros();
            }

            @Override
            public List<AsegAfiliado> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(0).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(0).setPrimerRegistro(primerRegistro);
                getParamConsulta(0).setRegistrosPagina(registrosPagina);
                getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarAfiliado();
                afiliados = getListaAfiliados();
                setRowCount(getParamConsulta(0).getCantidadRegistros());
                return afiliados;
            }

            @Override
            public String getRowKey(AsegAfiliado afiliado) {
                return afiliado.getId().toString();
            }

            @Override
            public AsegAfiliado getRowData(String afiliadoId) {
                Integer id = Integer.valueOf(afiliadoId);
                for (AsegAfiliado afiliado : afiliados) {
                    if (id.equals(afiliado.getId())) {
                        return afiliado;
                    }
                }
                return null;
            }
        };
    }

    public void onRowSelectAfiliado(SelectEvent event) {
        AsegAfiliado afiliado = (AsegAfiliado) event.getObject();
        boolean isActivo = getAuNoSolicitudServicio().validarEstadoAfiliado(afiliado.getMaeEstadoAfiliacion(), afiliado.getId(), this);
        if (isActivo) {
            getObjeto().setAsegAfiliadosId(afiliado);
            getParamConsulta(0).setFiltros(new HashMap<>());
            PrimeFaces.current().ajax().update("frmCrear:pAfiliadoCrear");
            PrimeFaces.current().ajax().update("frmEditar:panelAfiliadoEditar");
            PrimeFaces.current().executeScript("PF('dialogoAfiliadoLista').hide()");
        }
        generarMensajes();
    }

    public void consultarPrestador() {
        if (!super.isError()) {
            cargaLazyPrestador();
            PrimeFaces.current().ajax().update("frmIpsLista");
            PrimeFaces.current().executeScript("PF('dialogoIpsLista').show()");
        }
        procesoFinal();
    }

    public void cargaLazyPrestador() {

        lazyPrestadores = new LazyDataModel<CntPrestadorSede>() {
            private List<CntPrestadorSede> listaPrestadores;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(1).getCantidadRegistros();
            }

            @Override
            public List<CntPrestadorSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(1).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(1).setPrimerRegistro(primerRegistro);
                getParamConsulta(1).setRegistrosPagina(registrosPagina);
                getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(1).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarPrestador();
                listaPrestadores = getListaPrestadores();
                setRowCount(getParamConsulta(1).getCantidadRegistros());
                return listaPrestadores;
            }

            @Override
            public String getRowKey(CntPrestadorSede ips) {
                return ips.getId().toString();
            }

            @Override
            public CntPrestadorSede getRowData(String ipsId) {
                Integer id = Integer.valueOf(ipsId);
                for (CntPrestadorSede ips : listaPrestadores) {
                    if (id.equals(ips.getId())) {
                        return ips;
                    }
                }
                return null;
            }
        };
    }

    public void onRowSelectPrestador(SelectEvent event) {
        CntPrestadorSede ips = (CntPrestadorSede) event.getObject();
        getObjeto().setCntPrestadorSedesId(ips);
        getObjeto().setCntPrestadorId(ips.getCntPrestador());
        getObjeto().setCntPrestadorEntregaId(new CntPrestador());
        getObjeto().setCntPrestadorSedeEntregaId(new CntPrestadorSede());
        setParamConsulta(new ParamConsulta());
        getParamConsulta(1).setFiltros(new HashMap<>());
        PrimeFaces.current().executeScript("PF('dialogoIpsLista').hide()");
        PrimeFaces.current().ajax().update("frmCrear:pPrestadorCrear");
        PrimeFaces.current().ajax().update("frmCrear:pPrestadorEntregaCrear");
        PrimeFaces.current().ajax().update("frmEditar:pPrestadorEditar");
        PrimeFaces.current().ajax().update("frmEditar:pPrestadorEntregaEditar");
        //PrimeFaces.current().ajax().update("frmGestionar:pPrestadorGestionar");
        //
    }
    
    public void consultarPrestadorEntrega() {
        if(getObjeto().getCntPrestadorSedesId() == null){
            addError("Se necesita el prestado solicita");
        }
        if (!super.isError()) {
            cargaLazyPrestadorEntega();
            PrimeFaces.current().ajax().update("frmIpsListaEntrega");
            PrimeFaces.current().executeScript("PF('dialogoIpsListaEntrega').show()");
        }
        generarMensajes();
    }
    
    public void cargaLazyPrestadorEntega() {

        lazyPrestadoresEntrega = new LazyDataModel<CntPrestadorSede>() {
            private List<CntPrestadorSede> listaPrestadoresEntrega;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(2).getCantidadRegistros();
            }

            @Override
            public List<CntPrestadorSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(2).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(2).setPrimerRegistro(primerRegistro);
                getParamConsulta(2).setRegistrosPagina(registrosPagina);
                getParamConsulta(2).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(2).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarPrestadorEntrega();
                listaPrestadoresEntrega = getListaPrestadoresEntrega();
                setRowCount(getParamConsulta(2).getCantidadRegistros());
                return listaPrestadoresEntrega;
            }

            @Override
            public String getRowKey(CntPrestadorSede ips) {
                return ips.getId().toString();
            }

            @Override
            public CntPrestadorSede getRowData(String ipsId) {
                Integer id = Integer.valueOf(ipsId);
                for (CntPrestadorSede ips : listaPrestadoresEntrega) {
                    if (id.equals(ips.getId())) {
                        return ips;
                    }
                }
                return null;
            }
        };
    }
    
    public void onRowSelectPrestadorEntrega(SelectEvent event) {
        CntPrestadorSede ips = (CntPrestadorSede) event.getObject();
        getObjeto().setCntPrestadorSedeEntregaId(ips);
        getObjeto().setCntPrestadorEntregaId(ips.getCntPrestador());
        setParamConsulta(new ParamConsulta());
        getParamConsulta(2).setFiltros(new HashMap<>());
        //cargaLazyInsumo();
        //PrimeFaces.current().ajax().update("frmInsumoBusquedaPrestador");
        PrimeFaces.current().executeScript("PF('dialogoIpsListaEntrega').hide()");
        PrimeFaces.current().ajax().update("frmCrear:pPrestadorEntregaCrear");
        PrimeFaces.current().ajax().update("frmEditar:pPrestadorEntregaEditar");
        //PrimeFaces.current().ajax().update("frmGestionar:pPrestadorGestionar");
        //
    }
    
    public void consultarTecnologia() {
        if(getObjeto().getCntPrestadorEntregaId() == null){
            addError("El prestado Entrega es obligatorio");
        }
        if(getObjeto().getAsegAfiliadosId() == null){
            addError("El afiliado es obligatorio");
        }
        if(!super.isError()){
            setObjetoItem(new AuNoSolicitudItem());
            getObjetoItem().setPbs(Boolean.TRUE);
            getObjetoItem().setCantidadSolicitada(1);
            cargaLazyProcedimiento();
            PrimeFaces.current().executeScript("PF('dialogoTecnologiaBusqueda').show()");
            PrimeFaces.current().ajax().update("frmTecnologiaBusqueda");
        }
        procesoFinal();
    }
    
    public void cargaLazyProcedimiento() {

        lazyProcedimientos = new LazyDataModel<CntContratoDetalle>() {
            private List<CntContratoDetalle> listaProcedimientos;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(3).getCantidadRegistros();
            }

            @Override
            public List<CntContratoDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(3).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(3).setPrimerRegistro(primerRegistro);
                getParamConsulta(3).setRegistrosPagina(registrosPagina);
                getParamConsulta(3).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(3).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarProcedimiento();
                listaProcedimientos = getListaProcedimientos();
                setRowCount(getParamConsulta(3).getCantidadRegistros());
                return listaProcedimientos;
            }

            @Override
            public String getRowKey(CntContratoDetalle ips) {
                return ips.getId().toString();
            }

            @Override
            public CntContratoDetalle getRowData(String ipsId) {
                Integer id = Integer.valueOf(ipsId);
                for (CntContratoDetalle ips : listaProcedimientos) {
                    if (id.equals(ips.getId())) {
                        return ips;
                    }
                }
                return null;
            }
        };
    }
    
    public void onRowSelectTecnologia(SelectEvent event) {
        CntContratoDetalle procedimiento = (CntContratoDetalle) event.getObject();
        //MaTecnologia tecnologia = getTecnologiasBean().getObjeto();
        getObjeto().getListAuNoSolicitudItem().stream().filter(item -> (item.getTipoTecnologia() == AuConstantes.ID_TECNOLOGIA
                && procedimiento.getMaTecnologiaId() == item.getMaTecnologiaId())).forEachOrdered(_item -> {
                    addError(String.format(AuConstantes.ERROR_ADICION_TECNOLOGIA, procedimiento.getMaTecnologiaValor()));
        });
        
        if (!super.isError()) {
            // tecnologia
            getObjetoItem().setTipoTecnologia(AuConstantes.ID_TECNOLOGIA);
            getObjetoItem().setMaTecnologiaId(procedimiento.getMaTecnologiaId());
            getObjetoItem().setMaTecnologiaCodigo(procedimiento.getMaTecnologiaCodigo());
            getObjetoItem().setMaTecnologiaValor(procedimiento.getMaTecnologiaValor());
            getObjetoItem().setValorUnitario(procedimiento.getValorContratado());
            getObjetoItem().setValorTotal(getObjetoItem().getValorUnitario().multiply(new BigDecimal(getObjetoItem().getCantidadSolicitada())));
            getObjetoItem().setAuNoSolicitudesId(getObjeto());
            getObjetoItem().setCntContratoDetallesId(procedimiento);
            getObjetoItem().setTipoEntrega(AuNoSolicitudEntrega.TIPO_NO_ENTREGADO);
            getObjetoItem().setBorrado(Boolean.FALSE);
            getObjetoItem().setPosicion(getObjeto().getListAuNoSolicitudItem().size());
            PrimeFaces.current().ajax().update("frmItemTecnologiaCrear");
            PrimeFaces.current().executeScript("PF('dlgItemTecnologiaCrear').show()");
           
        }
        procesoFinal();
    }

    public void consultarMedicamento() {
        setObjetoItem(new AuNoSolicitudItem());
        getObjetoItem().setPbs(Boolean.TRUE);
        getObjetoItem().setCantidadSolicitada(1);
        if(getObjeto().getCntPrestadorEntregaId() == null){
            addError("El prestado Entrega es obligatorio");
        }
        if(getObjeto().getAsegAfiliadosId() == null){
            addError("El afiliado es obligatorio");
        }
        if(!super.isError()){
            cargaLazyMedicamento();
            PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').show()");
            PrimeFaces.current().ajax().update("frmMedicamentoBusqueda");
        }
        procesoFinal();
    }
    
    public void cargaLazyMedicamento() {

        lazyMedicamentos = new LazyDataModel<CntContratoDetalle>() {
            private List<CntContratoDetalle> listaMedicamentos;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(4).getCantidadRegistros();
            }

            @Override
            public List<CntContratoDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(4).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(4).setPrimerRegistro(primerRegistro);
                getParamConsulta(4).setRegistrosPagina(registrosPagina);
                getParamConsulta(4).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(4).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarMedicamento();
                listaMedicamentos = getListaMedicamentos();
                setRowCount(getParamConsulta(4).getCantidadRegistros());
                return listaMedicamentos;
            }

            @Override
            public String getRowKey(CntContratoDetalle ips) {
                return ips.getId().toString();
            }

            @Override
            public CntContratoDetalle getRowData(String ipsId) {
                Integer id = Integer.valueOf(ipsId);
                for (CntContratoDetalle ips : listaMedicamentos) {
                    if (id.equals(ips.getId())) {
                        return ips;
                    }
                }
                return null;
            }
        };
    }
    
    public void onRowSelectMedicamento(SelectEvent event) {
        CntContratoDetalle medicamento = (CntContratoDetalle) event.getObject();
        MaMedicamento consultarMedicamento = getAuNoSolicitudServicio().consultarMedicamento(this, medicamento.getMaTecnologiaId());
     
        getObjeto().getListAuNoSolicitudItem().stream().filter(item -> (item.getTipoTecnologia() == AuConstantes.ID_AGRUPADOR_MEDICAMENTO
                && consultarMedicamento.getMaAgrupadorMedicamento().getId() == item.getMaTecnologiaId())).forEachOrdered(_item -> {
                    addError(String.format(AuConstantes.ERROR_ADICION_AGRUPADOR_MEDICAMENTO, consultarMedicamento.getMaAgrupadorMedicamento().getNombre()));
        });
        
        if (!super.isError()) {
            getObjetoItem().setTipoTecnologia(AuConstantes.ID_AGRUPADOR_MEDICAMENTO);
            // tecnologia   
            getObjetoItem().setMaTecnologiaId(consultarMedicamento.getMaAgrupadorMedicamento().getId());
            getObjetoItem().setMaTecnologiaCodigo(consultarMedicamento.getMaAgrupadorMedicamento().getCodigo());
            getObjetoItem().setMaTecnologiaValor(consultarMedicamento.getMaAgrupadorMedicamento().getNombre());
            //Medicamento
            getObjetoItem().setMaMedicamentosId(consultarMedicamento.getId());
            getObjetoItem().setMaMedicamentosCodigo(consultarMedicamento.getCum());
            getObjetoItem().setMaMedicamentosValor(consultarMedicamento.getDescripcionEstandarizada());
            getObjetoItem().setValorUnitario(medicamento.getValorContratado());
            getObjetoItem().setValorTotal(getObjetoItem().getValorUnitario().multiply(new BigDecimal(getObjetoItem().getCantidadSolicitada())));
            
            getObjetoItem().setMaeAtc1Id(consultarMedicamento.getMaeAtc1Id());
            getObjetoItem().setMaeAtc1Codigo(consultarMedicamento.getMaeAtc1Codigo());
            getObjetoItem().setMaeAtc1Valor(consultarMedicamento.getMaeAtc1Valor());
            
            getObjetoItem().setMaeGrupoTerapeuticoPpalId(consultarMedicamento.getMaeGrupoTerapeuticoPpalId());
            getObjetoItem().setMaeGrupoTerapeuticoPpalCodigo(consultarMedicamento.getMaeGrupoTerapeuticoPpalCodigo());
            getObjetoItem().setMaeGrupoTerapeuticoPpalValor(consultarMedicamento.getMaeGrupoTerapeuticoPpalValor());
            
            getObjetoItem().setRegistroSanitario(consultarMedicamento.getRegistroSanitario());
            //getObjetoItem().setDescripcionLargaEstandarizada(consultarMedicamento.getDescripcionLargaEstandarizada());

            getObjetoItem().setAuNoSolicitudesId(getObjeto());
            getObjetoItem().setCntContratoDetallesId(medicamento);
            getObjetoItem().setTipoEntrega(AuNoSolicitudEntrega.TIPO_NO_ENTREGADO);
            getObjetoItem().setBorrado(Boolean.FALSE);
            getObjetoItem().setPosicion(getObjeto().getListAuNoSolicitudItem().size());
            PrimeFaces.current().ajax().update("frmItemMedicamentoCrear");
            PrimeFaces.current().executeScript("PF('dlgItemMedicamentoCrear').show()");
            //PrimeFaces.current().executeScript("PF('dialogoMedicamentoBusqueda').hide()");
        }
        procesoFinal();
    }

    public void consultarInsumo() {
        if(getObjeto().getCntPrestadorEntregaId() == null){
            addError("El prestado Entrega es obligatorio");
        }
        if(getObjeto().getAsegAfiliadosId() == null){
            addError("El prestado Entrega es obligatorio");
        }
        if(!super.isError()){
            PrimeFaces.current().ajax().update("frmCrearContratoInsumo");
            PrimeFaces.current().executeScript("PF('dialogoCrearContratoInsumo').show()");
        }
        procesoFinal();
    }
    
    public void ventanaLazyContratoIsumo(){
        setObjetoItem(new AuNoSolicitudItem());
        getObjetoItem().setPbs(Boolean.TRUE);
        getObjetoItem().setCantidadSolicitada(1);
        getObjetoItem().setHabilitarValorUnitario(true);
        cargaLazyInsumo();
        PrimeFaces.current().ajax().update("frmInsumoBusquedaPrestador");
        PrimeFaces.current().executeScript("PF('dialogoInsumoBusquedaPrestador').show()");
    }
    
    public void ventanaLazyMaInsumoAnularSinAutorizacion(){
        setObjetoItem(new AuNoSolicitudItem());
        getObjetoItem().setPbs(Boolean.TRUE);
        getObjetoItem().setCantidadSolicitada(1);
        getObjetoItem().setHabilitarValorUnitario(false);
        PrimeFaces.current().ajax().update("frmInsumoBusqueda");
        PrimeFaces.current().executeScript("PF('dialogoInsumoBusqueda').show()");
    }
    
    public void cargaLazyInsumo() {

        lazyInsumos = new LazyDataModel<CntContratoDetalle>() {
            private List<CntContratoDetalle> listaInsumos;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(5).getCantidadRegistros();
            }

            @Override
            public List<CntContratoDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(5).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(5).setPrimerRegistro(primerRegistro);
                getParamConsulta(5).setRegistrosPagina(registrosPagina);
                getParamConsulta(5).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(5).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarInsumo();
                listaInsumos = getListaInsumos();
                setRowCount(getParamConsulta(5).getCantidadRegistros());
                return listaInsumos;
            }

            @Override
            public String getRowKey(CntContratoDetalle ips) {
                return ips.getId().toString();
            }

            @Override
            public CntContratoDetalle getRowData(String ipsId) {
                Integer id = Integer.valueOf(ipsId);
                for (CntContratoDetalle ips : listaInsumos) {
                    if (id.equals(ips.getId())) {
                        return ips;
                    }
                }
                return null;
            }
        };
    }
    
    public void onRowSelectInsumo(SelectEvent event) {
        CntContratoDetalle insumo = (CntContratoDetalle) event.getObject();
        getObjeto().getListAuNoSolicitudItem().stream().filter(item -> (item.getTipoTecnologia() == AuConstantes.ID_INSUMO
                && insumo.getMaTecnologiaId() == item.getMaTecnologiaId())).forEachOrdered(_item -> {
                    addError(String.format(AuConstantes.ERROR_ADICION_INSUMO, insumo.getMaTecnologiaValor()));
        });
        if (!super.isError()) {
            // tecnologia
            getObjetoItem().setTipoTecnologia(AuConstantes.ID_INSUMO);
            getObjetoItem().setMaTecnologiaId(insumo.getMaTecnologiaId());
            getObjetoItem().setMaTecnologiaCodigo(insumo.getMaTecnologiaCodigo());
            getObjetoItem().setMaTecnologiaValor(insumo.getMaTecnologiaValor());
            getObjetoItem().setValorUnitario(insumo.getValorContratado());
            getObjetoItem().setValorTotal(getObjetoItem().getValorUnitario().multiply(new BigDecimal(getObjetoItem().getCantidadSolicitada())));
            getObjetoItem().setAuNoSolicitudesId(getObjeto());
            getObjetoItem().setCntContratoDetallesId(insumo);
            getObjetoItem().setTipoEntrega(AuNoSolicitudEntrega.TIPO_NO_ENTREGADO);
            getObjetoItem().setBorrado(Boolean.FALSE);
            getObjetoItem().setPosicion(getObjeto().getListAuNoSolicitudItem().size());
            PrimeFaces.current().ajax().update("frmItemInsumoCrear");
            PrimeFaces.current().executeScript("PF('dlgItemInsumoCrear').show()");
        }
        procesoFinal();
    }
    
    public void cerrarDialogoInsumo() {
        MaInsumo insumo = getInsumosBean().getObjeto();
        getObjeto().getListAuNoSolicitudItem().stream().filter(item -> (item.getTipoTecnologia() == AuConstantes.ID_INSUMO
                && insumo.getId().equals(item.getMaTecnologiaId()))).forEachOrdered(_item -> {
                    addError(String.format(AuConstantes.ERROR_ADICION_INSUMO, insumo.getDescripcion()));
        });
        if (!super.isError()) {
            // tecnologia
            getObjetoItem().setTipoTecnologia(AuConstantes.ID_INSUMO);
            getObjetoItem().setMaTecnologiaId(insumo.getId());
            getObjetoItem().setMaTecnologiaCodigo(insumo.getCodigo());
            getObjetoItem().setMaTecnologiaValor(insumo.getDescripcion());
            //getObjetoItem().setValorUnitario(insumo.getValorContratado());
            //getObjetoItem().setValorTotal(getObjetoItem().getValorUnitario().multiply(new BigDecimal(getObjetoItem().getCantidadSolicitada())));
            getObjetoItem().setAuNoSolicitudesId(getObjeto());
            //getObjetoItem().setCntContratoDetallesId(new CntContratoDetalle(1));
            getObjetoItem().setTipoEntrega(AuNoSolicitudEntrega.TIPO_NO_ENTREGADO);
            getObjetoItem().setBorrado(Boolean.FALSE);
            getObjetoItem().setPosicion(getObjeto().getListAuNoSolicitudItem().size());
            PrimeFaces.current().ajax().update("frmItemInsumoCrear");
            PrimeFaces.current().executeScript("PF('dlgItemInsumoCrear').show()");
        }
        procesoFinal();
    }
    
    public void consultarPaquete() {
        setObjetoItem(new AuNoSolicitudItem());
        getObjetoItem().setPbs(Boolean.TRUE);
        getObjetoItem().setCantidadSolicitada(1);
        if(getObjeto().getCntPrestadorEntregaId() == null){
            addError("El prestado Entrega es obligatorio");
        }
        if(getObjeto().getAsegAfiliadosId() == null){
            addError("El afiliado es obligatorio");
        }
        if(!super.isError()){
            cargaLazyPaquete();
            PrimeFaces.current().executeScript("PF('dialogoPaqueteBusqueda').show()");
            PrimeFaces.current().ajax().update("frmPaqueteBusqueda");
        }
        procesoFinal();
    }
    
    public void cargaLazyPaquete() {

        lazyPaquetes = new LazyDataModel<CntContratoDetalle>() {
            private List<CntContratoDetalle> listaPaquetes;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(6).getCantidadRegistros();
            }

            @Override
            public List<CntContratoDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(6).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(6).setPrimerRegistro(primerRegistro);
                getParamConsulta(6).setRegistrosPagina(registrosPagina);
                getParamConsulta(6).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(6).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarPaquete();
                listaPaquetes = getListaPaquetes();
                setRowCount(getParamConsulta(6).getCantidadRegistros());
                return listaPaquetes;
            }

            @Override
            public String getRowKey(CntContratoDetalle ips) {
                return ips.getId().toString();
            }

            @Override
            public CntContratoDetalle getRowData(String ipsId) {
                Integer id = Integer.valueOf(ipsId);
                for (CntContratoDetalle ips : listaPaquetes) {
                    if (id.equals(ips.getId())) {
                        return ips;
                    }
                }
                return null;
            }
        };
    }
    
    public void onRowSelectPaquete(SelectEvent event) {
        CntContratoDetalle paquete = (CntContratoDetalle) event.getObject();
        getObjeto().getListAuNoSolicitudItem().stream().filter(item -> (item.getTipoTecnologia() == AuConstantes.ID_PAQUETE
                && paquete.getMaTecnologiaId() == item.getMaTecnologiaId())).forEachOrdered(_item -> {
                    addError(String.format(AuConstantes.ERROR_ADICION_INSUMO, paquete.getMaTecnologiaValor()));
        });
        if (!super.isError()) {
            // tecnologia
            getObjetoItem().setTipoTecnologia(AuConstantes.ID_PAQUETE);
            getObjetoItem().setMaTecnologiaId(paquete.getMaTecnologiaId());
            getObjetoItem().setMaTecnologiaCodigo(paquete.getMaTecnologiaCodigo());
            getObjetoItem().setMaTecnologiaValor(paquete.getMaTecnologiaValor());
            getObjetoItem().setValorUnitario(paquete.getValorContratado());
            getObjetoItem().setValorTotal(getObjetoItem().getValorUnitario().multiply(new BigDecimal(getObjetoItem().getCantidadSolicitada())));
            getObjetoItem().setAuNoSolicitudesId(getObjeto());
            getObjetoItem().setCntContratoDetallesId(paquete);
            getObjetoItem().setTipoEntrega(AuNoSolicitudEntrega.TIPO_NO_ENTREGADO);
            getObjetoItem().setBorrado(Boolean.FALSE);
            getObjetoItem().setPosicion(getObjeto().getListAuNoSolicitudItem().size());
            PrimeFaces.current().ajax().update("frmItemPaqueteCrear");
            PrimeFaces.current().executeScript("PF('dlgItemPaqueteCrear').show()");
        }
        procesoFinal();
    }
    
    public void consultarProfesional() {
        try {
            if (getObjeto().getCntPrestadorSedesId()== null
                    || getObjeto().getCntPrestadorSedesId().getId() == null) {
                addError("Se debe seleccionar una ips");
                generarMensajes();
                return;
            }

            if (getObjeto().getCntProfesionalesId().getMaeTipoCodumentoId() == 0) {
                return;
            }

            if (getObjeto().getCntProfesionalesId().getDocumento() == null || getObjeto().getCntProfesionalesId().getDocumento().isBlank()) {
                return;
            }

            getAuNoSolicitudServicio().consultarProfesional(this);
            PrimeFaces.current().resetInputs("frmCrear:pnlProfecionalCrear");
            PrimeFaces.current().ajax().update("frmCrear:pnlProfecionalCrear");
            PrimeFaces.current().resetInputs("frmEditar:pnlProfecionalEditar");
            PrimeFaces.current().ajax().update("frmEditar:pnlProfecionalEditar");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }
    
    public void consultarEspecialidad() {
        try {
            PrimeFaces.current().executeScript("PF('dialogoEspecialidadBusqueda').show()");
            PrimeFaces.current().ajax().update("frmDiagnosticoBusqueda");

        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void cerrarDialogoEspecialidad() {
        getObjetoProfesionalPrestador().setMaEspecialidadCodigo(getSelEspecialidadesBean().getObjeto().getCodigo());
        getObjetoProfesionalPrestador().setMaEspecialidadValor(getSelEspecialidadesBean().getObjeto().getNombre());
        getObjetoProfesionalPrestador().setMaEspecialidadId(getSelEspecialidadesBean().getObjeto().getId());
        getSelEspecialidadesBean().setObjeto(new MaEspecialidad());
        PrimeFaces.current().executeScript("PF('dialogoEspecialidadBusqueda').hide()");
        PrimeFaces.current().ajax().update("frmCrear:labelEspecialidad");
        PrimeFaces.current().ajax().update("frmEditar:labelEspecialidad");
    } 
     
    public void consultarServicioHabilitacion(int funcion) {
        try {
            setTipoFuncionServicioHabilitafion(funcion);
            PrimeFaces.current().executeScript("PF('dialogoServiciosHabilitacionBusqueda').show()");
            PrimeFaces.current().ajax().update("frmServiciosHabilitacionBusqueda");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void cerrarDialogoServicios() {
        if (getSelServiciosHabilitacionBean().getObjeto().isActivo()) {
            switch(getTipoFuncionServicioHabilitafion()){
                case 0:
                    getObjeto().setMaServicioHabilitacionId(getSelServiciosHabilitacionBean().getObjeto().getId());
                    getObjeto().setMaServicioHabilitacionCodigo(String.valueOf(getSelServiciosHabilitacionBean().getObjeto().getCodigo()));
                    getObjeto().setMaServicioHabilitacionValor(getSelServiciosHabilitacionBean().getObjeto().getNombre());
                    PrimeFaces.current().ajax().update("frmCrear:lblServicioSolicita");
                    PrimeFaces.current().ajax().update("frmCrear:lblServicioSolicitaCodigo");
                    PrimeFaces.current().ajax().update("frmEditar:lblServicioSolicita");
                    PrimeFaces.current().ajax().update("frmEditar:lblServicioSolicitaCodigo");
                    break;
                case 1:
                    getObjetoItem().setMaServicioHabilitacionId(getSelServiciosHabilitacionBean().getObjeto().getId());
                    getObjetoItem().setMaServicioHabilitacionCodigo(String.valueOf(getSelServiciosHabilitacionBean().getObjeto().getCodigo()));
                    getObjetoItem().setMaServicioHabilitacionValor(getSelServiciosHabilitacionBean().getObjeto().getNombre());
                    PrimeFaces.current().ajax().update("frmItemTecnologiaCrear:lblServicioSolicita");
                    PrimeFaces.current().ajax().update("frmItemTecnologiaCrear:lblServicioCodigo");
                    PrimeFaces.current().ajax().update("frmItemTecnologiaEditar:lblServicioSolicita");
                    PrimeFaces.current().ajax().update("frmItemTecnologiaEditar:lblServicioCodigo");
                    break;
            }
            getSelServiciosHabilitacionBean().setObjeto(new MaServicioHabilitacion());
            PrimeFaces.current().executeScript("PF('dialogoServiciosHabilitacionBusqueda').hide()");
        } else {
            this.addError("El servicio seleccionado no esta activo.");
            generarMensajes();
        }
    }
    
    public void consultarDiagnostico() {
        /*if (getObjeto().getId() != null) {
            AucHospitalizacion hopitalizacion = getAucHospitalizacionServicio().consultarhospitalizacionId(getObjeto().getId(), this);
            if (hopitalizacion.getEstado() == AucHospitalizacion.ESTADO_AFILIADO_ANULADO
                    || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_ANULADO
                    || hopitalizacion.getEstadoAuditoria() == AucHospitalizacion.ESTADO_AUDITORIA_CERRADO) {
                addError("No se pude aplicar, por favor refrescar la pantalla");
            }
        }*/
        if(getObjeto().getAsegAfiliadosId() == null){
            addError("El afiliado es obligatorio");
        }
        if(!super.isError()){
            PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').show()");
            PrimeFaces.current().ajax().update("frmDiagnosticoBusqueda");
        }
        generarMensajes();
    }
    
    public void cerrarDialogoDiagnostico() {
        boolean agregar = true;
        for (AuNoSolicitudDiagnostico diag : getObjeto().getListAuNoSolicitudDiagnostico()) {
            if (diag.getMaDiagnosticosId() == getDiagnosticosBean().getObjeto().getId()) {
                agregar = false;
                addMensaje("El diagnostico " + getDiagnosticosBean().getObjeto().getNombre() + " ya fue agregado.");
                break;
           }
        }   
        for (AuNoSolicitudDiagnostico diagnostico : getObjeto().getListAuNoSolicitudDiagnostico()) {
            if (diagnostico.isPrincipal() && getDiagnosticosBean().getObjeto().isPrincipal()) {
                agregar = false;
                addError("No puede haber más de un diagnóstico principal");
                break;
            }
        }
        /*String mensaje = getAuNoSolicitudServicio().validarDiagnostico(getDiagnosticosBean().getObjeto().getCodigo(), getObjeto().getAsegAfiliadosId().getMaeTipoDocumentoCodigo(), getObjeto().getAsegAfiliadosId().getNumeroDocumento());
        if(mensaje != null){
            addError(mensaje);
            agregar = false;
        }*/
        if (agregar) {
            AuNoSolicitudDiagnostico diagnostico = new AuNoSolicitudDiagnostico();
            diagnostico.setAltoCosto(getDiagnosticosBean().getObjeto().isAltoCosto());
            diagnostico.setMaDiagnosticosId(getDiagnosticosBean().getObjeto().getId());
            diagnostico.setMaDiagnosticosCodigo(getDiagnosticosBean().getObjeto().getCodigo());
            diagnostico.setMaDiagnosticosValor(getDiagnosticosBean().getObjeto().getNombre());
            if (getObjeto().getListAuNoSolicitudDiagnostico().isEmpty()) {
                diagnostico.setPrincipal(true);
            }
            diagnostico.setPosicion(getObjeto().getListAuNoSolicitudDiagnostico().size());
            getObjeto().getListAuNoSolicitudDiagnostico().add(diagnostico);
            
            PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').hide()");
            PrimeFaces.current().ajax().update("frmCrear:pDiagnosticoCrear");
            PrimeFaces.current().ajax().update("frmEditar:pDiagnosticoEditar");
            //PrimeFaces.current().ajax().update("frmGestionar:tablaDiagnosticosGestion");
        }
        getDiagnosticosBean().setObjeto(new MaDiagnostico());
        generarMensajes();
    }
    
    public void diagnosticoPrincipalCrear(int id) {
        for (AuNoSolicitudDiagnostico diagnostico : getObjeto().getListAuNoSolicitudDiagnostico()) {
            if (diagnostico.getMaDiagnosticosId() != id) {
                diagnostico.setPrincipal(false);
            } else {
                diagnostico.setPrincipal(true);
            }
        }
        PrimeFaces.current().ajax().update("frmCrear:pDiagnosticoCrear");
        PrimeFaces.current().ajax().update("frmEditar:pDiagnosticoEditar");
    }
    
    public void diagnosticoAltoCostoCrear(int id, boolean activar) {
        for (AuNoSolicitudDiagnostico diagnostico : getObjeto().getListAuNoSolicitudDiagnostico()) {
            if (diagnostico.getMaDiagnosticosId() == id && activar) {
                diagnostico.setAltoCosto(true);
            } 
        }
        PrimeFaces.current().ajax().update("frmCrear:pDiagnosticoCrear");
        PrimeFaces.current().ajax().update("frmEditar:pDiagnosticoEditar");
    }
    
    public void borrarDiagnostico(AuNoSolicitudDiagnostico diagnoticos) {
        List<AuNoSolicitudDiagnostico> listaContactos = new ArrayList<>();
        int posicionEliminar = diagnoticos.getPosicion();
        getObjeto().getListAuNoSolicitudDiagnostico().stream().filter(diagnostico -> (diagnostico.getPosicion() != posicionEliminar)).forEachOrdered(diagnostico -> {
            listaContactos.add(diagnostico);
        });

        if (diagnoticos.getId() != null) {
            //this.getAucAfiliadoContacto().setId(personaContacto.getId());
            //super.setAccion(Url.ACCION_BORRAR);
            //super.setDoAccion(ACCION_BORRAR_CONTACTOS_PERSONAS);
            //getAucHospitalizacionServicio().Accion(this);
        }
        getObjeto().setListAuNoSolicitudDiagnostico(listaContactos);

        addMensaje("Se ha realizado la eliminación del item");
        generarMensajes();
        PrimeFaces.current().ajax().update("frmCrear:pDiagnosticoCrear");
        PrimeFaces.current().ajax().update("frmEditar:pDiagnosticoEditar");
        //PrimeFaces.current().ajax().update("frmGestionar:tablaContactoPersona");
        //PrimeFaces.current().ajax().update("frmEditar:tablaContactoPersona");
    }
    
    public void borrarItem(AuNoSolicitudItem items) {
        boolean validaciones = false;
        if(getObjeto().getListAuNoSolicitudItem().size() == 1){
            validaciones = true;
        }
        if(validaciones){
            addError("Por lo menos debe tener un item");
            generarMensajes();
            return;
        }
        List<AuNoSolicitudItem> listaContactos = new ArrayList<>();
        int posicionEliminar = items.getPosicion();
        getObjeto().getListAuNoSolicitudItem().stream().filter(item -> (item.getPosicion() != posicionEliminar)).forEachOrdered(item -> {
            listaContactos.add(item);
        });
        getObjeto().setListAuNoSolicitudItem(listaContactos);
        addMensaje("Se ha realizado la eliminación del item");
        procesoFinal();
        PrimeFaces.current().ajax().update("frmCrear:pItemCrear");
        PrimeFaces.current().ajax().update("frmEditar:pItemEditar");
        //PrimeFaces.current().ajax().update("frmGestionar:tablaContactoPersona");
        //
    }
    
    public void borrarItemDb(AuNoSolicitudItem item) {
        boolean validaciones = false;
        if(getObjeto().getListAuNoSolicitudItem().size() == 1){
            validaciones = true;
        }
        if(validaciones){
            addError("Por lo menos debe tener un item");
            generarMensajes();
            return;
        }
        getListaItemsBorrar().add(item);
        getObjeto().getListAuNoSolicitudItem().remove(item);
        PrimeFaces.current().ajax().update("frmEditar:pItemEditar");
     
    }
    
    
    public String convertirFecha(Date fecha) {
        try {
            return AuConstantes.formato2.format(fecha);
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }
    
    public String calcularEdad(Date fecha) {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaNacimiento = AuConstantes.formato5.format(fecha);
            Period periodo = Period.between(LocalDate.parse(fechaNacimiento, fmt), LocalDate.now());
            String texto = periodo.getYears() + " años " + periodo.getMonths() + " meses " + periodo.getDays() + " dias";
            return texto;
        } catch (Exception e) {
            return "";
        }
    }

    public String obtenerDepartamento(int id) {
        try {
            int idPadre = hashUbicaciones.get(id).getUbicacionPadre().getId();
            return hashUbicaciones.get(idPadre).getNombre();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public String obtenerUbicacion(int id) {
        if (id == 0) {
            return "";
        }
        String ubi = getHashUbicaciones().get(id).getNombreDepartamentoCiudad();
        return ubi;
    }

    public String obtenerMunicipio(int id) {
        try {
            return hashUbicaciones.get(id).getNombre();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }
    
    public void calcularPendiente() {
        int pendientes = getObjetoEntrega().getAuNoSolicitudItemsId().getCantidadSolicitada() - getObjetoEntrega().getCantidadEntregada();
        //int pendientes = getObjetoEntrega().getCantidadPorEntregar() - getObjetoEntrega().getCantidadEntregada();
        getObjetoEntrega().setCantidadPendiente(pendientes);
        if (getObjetoEntrega().getCantidadEntregada() > 0) {
            getObjetoEntrega().setMaeCausaMoEntregaId(null);
            getObjetoEntrega().setMaeCausaMoEntregaCodigo(null);
            getObjetoEntrega().setMaeCausaMoEntregaValor(null);
        }
        PrimeFaces.current().ajax().update("frmEntrega:cantidadPendienteGestionar");
    }
    
    public boolean validarBloqueoCausaEntrega(){
        boolean validate = false;
        if(getObjetoEntrega().getCantidadEntregada() == getObjetoEntrega().getCantidadPorEntregar()){
            validate = true;
        }
        return validate;
    }
    
    public boolean validarBloqueoNombreEntrega() {
        boolean validar = false;
        if(getObjetoEntrega() != null){
            if (getObjetoEntrega().isReclamaAfiliado()) {
                validar = true;
            }
            if (getObjetoEntrega().getAuNoSolicitudItemsId().getTipoEntrega()== AuNoSolicitudEntrega.TIPO_ENTREGA_TOTAL 
                    || getObjetoEntrega().getAuNoSolicitudItemsId().getTipoEntrega() == AuNoSolicitudEntrega.TIPO_ENTREGA_ANULADO) {
                validar = true;
            }
        }
        return validar;
    }
    
    public Date obtenerFechaActual() throws ParseException {
//        SimpleDateFormat formatoBasico = new SimpleDateFormat("dd/MM/yyyy");
//        SimpleDateFormat formatoCompleto = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date fechaActual = new Date();
//        String nuevaFecha = formatoBasico.format(fechaActual) + " 00:00:00";
//        fechaActual = formatoCompleto.parse(nuevaFecha);
        return fechaActual;
    }
    
    public Date obtenerFechaMes() {
        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.set(2021, 0, 1);
        return calendar.getTime();
    }
    
    public boolean validarEstadoEntrega(int tipoEntrega){
        boolean valide = false;
        switch(tipoEntrega){
            case AuNoSolicitudEntrega.TIPO_NO_ENTREGADO:
                valide = true;
                break;
            case AuNoSolicitudEntrega.TIPO_ENTREGA_PARCIAL:
                valide = true;
                break;    
        }
        return valide;
    }
    
    public boolean validarEstadoNoEntrega(int tipoEntrega){
        boolean valide = false;
        switch(tipoEntrega){
            case AuNoSolicitudEntrega.TIPO_NO_ENTREGADO:
                valide = true;
                break;
        }
        return valide;
    }
    
    public boolean validarEstadoNoEntregaPrestado(int tipoEntrega){
        boolean valide = true;
        switch(tipoEntrega){
            case AuNoSolicitudEntrega.TIPO_ENTREGA_NO_PRESTADO:
                valide = false;
                break;
        }
        return valide;
    }
    
    public boolean habilitarEstadoEditar(int estado){
        boolean valide = true;
        switch(estado){
            case AuNoSolicitud.ESTADO_EN_GESTION:
                valide = false;
                break;
            case AuNoSolicitud.ESTADO_GESTIONADO:
                valide = false;
                break;
            case AuNoSolicitud.ESTADO_ANULADO:
                valide = false;
                break;
        }
        return valide;
    }
    
    public boolean habilitarEstadoAnularSinAutorizacion(int estado){
        boolean valide = true;
        switch(estado){
            case AuNoSolicitud.ESTADO_EN_GESTION:
                valide = false;
                break;
            case AuNoSolicitud.ESTADO_GESTIONADO:
                valide = false;
                break;
            case AuNoSolicitud.ESTADO_ANULADO:
                valide = false;
                break;
        }
        return valide;
    }
    
    public boolean consultarCantidadAnuladas(int id){
        boolean validate = true;
        if(!getAuNoSolicitudServicio().consultarCantidadAnuladas(this, id)){
            validate = false;
        }
        return validate;
    }
    
    public void calcularValorTotalTecnologias(){
        if(getObjetoItem().getValorUnitario() != null 
                && getObjetoItem().getCantidadSolicitada() > 0 ){
            getObjetoItem().setValorTotal(getObjetoItem().getValorUnitario().multiply(new BigDecimal(getObjetoItem().getCantidadSolicitada())));
        }
        
    }
    
    public void mostrarMensaje(AuNoSolicitudHistorico historico) {
        if (historico.getObservacion() != null) {
            setTituloMensaje("Bitacora");
            setSubtituloMensaje("Información");
            setMensaje(historico.getObservacion());
            PrimeFaces.current().ajax().update("frmMensaje");
            PrimeFaces.current().executeScript("PF('dialogoMensaje').show()");
        }
    }
    
    public boolean validarModificar(AuNoSolicitud objeto) {
        boolean validar = false;
        if (objeto.getEstado() != AuNoSolicitud.ESTADO_PENDIENTE) {
            validar = true;
            return validar;
        }

        for (AuNoSolicitudItem itemPagina : getObjeto().getListAuNoSolicitudItem()) {
            for (AuNoSolicitudItem itemDB : objeto.getListAuNoSolicitudItem()) {
                if (Objects.equals(itemPagina.getId(), itemDB.getId())) {
                    if (itemPagina.getEstado() != itemDB.getEstado()) {
                        validar = true;
                        break;
                    }
                }
            }
        }
        return validar;
    }
    
    public int maximoValorEntregaDetalle(int cantidadTotal, int faltantes){
        int numero = cantidadTotal;
        if(faltantes > 0){
           numero =  faltantes;
        }
        return numero;
    }
    
    public void validarItems() {
        boolean cerrarModalGestionarOeditar = false;
        AuNoSolicitud solicitudDB = getAuNoSolicitudServicio().consultarAuNoSolicitud(this, getObjeto().getId());
        if(solicitudDB.getListAuNoSolicitudItem().isEmpty()){
            addError("Debe de tener por lo menos un item");
            cerrarModalGestionarOeditar = true;
        }
        if (cerrarModalGestionarOeditar) {
            //PrimeFaces.current().resetInputs("frmGestionar");
            //PrimeFaces.current().ajax().update("frmGestionar");
            PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        }
        generarMensajes();
    }
    
    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmSinAutorizaciones");
                    crearLog("Listar");
                    break;
                case Url.ACCION_GUARDAR:
                    PrimeFaces.current().ajax().update("frmSinAutorizaciones");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_MODIFICAR:
                    PrimeFaces.current().ajax().update("frmSinAutorizaciones");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmSinAutorizaciones");
                    break;
                case Url.ACCION_VER:
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            crearLog("Ver Sin Autorización", getObjeto().toString());
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            crearLog("Ver Item", getObjetoItem().toString());
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            //crearLog("Ver Anticipo Gestión", getAntAnticipoGestion().toString());
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            //crearLog("Ver Anticipo Valor", getObjetoAnticipoValor().toString());
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                    switch (getDoAccion()) {
                        case Url.ACCION_CREAR:
                            crearLog("Crear");
                            break;
                    }
                    break;
                case Url.ACCION_EDITAR:
                    switch (getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            crearLog("Editar");
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            switch(getTakeAccion()){
                                case Url.ACCION_VER:
                                    //crearLog("Ver Item", getObjetoItem().toString());
                                    break;
                                case Url.ACCION_EDITAR:
                                    //crearLog("Editar Item", getObjetoItem().toString());
                                    break;
                                case Url.ACCION_MODIFICAR:
                                    //crearLog("modificar Item", getObjetoItem().toString());
                                    break;
                                case Url.ACCION_BORRAR:
                                    crearLog("Borrar Item", getObjetoItem().toString());
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            //crearLog("Ver gestión", getAntAnticipoGestion().toString());
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            //crearLog("Ver Anticipo Valor", getObjetoAnticipoValor().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (getDoAccion()) {
                        case Url.ACCION_ADICIONAL_1:
                            crearLog("Gestionar");
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            switch(getTakeAccion()){
                                case Url.ACCION_LISTAR:
                                    crearLog("listar entregas");
                                    break;
                                case Url.ACCION_VER:
                                    crearLog("ver entrega");
                                    break;
                            }
                            break;
                        case Url.ACCION_CREAR:
                            switch(getTakeAccion()){
                                case Url.ACCION_CREAR:
                                    crearLog("Crear gestión");
                                    break;
                                case Url.ACCION_GUARDAR:
                                    //crearLog("Guardar gestión", getAntAnticipoGestion().toString());
                                    break;
                                case Url.ACCION_VER:
                                    //crearLog("Ver gestión", getAntAnticipoGestion().toString());
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_5:
                            PrimeFaces.current().ajax().update("frmSinAutorizaciones");
                            crearLog("Guardar Gestionar", getObjeto().toString());
                            break;
                        case Url.ACCION_ADICIONAL_6:
                            switch(getTakeAccion()){
                                case Url.ACCION_EDITAR:
                                    //crearLog("Editar Item", getObjetoItem().toString());
                                    break;
                                case Url.ACCION_MODIFICAR:
                                    //crearLog("Modificar Item", getObjetoItem().toString());
                                    break;
                                case Url.ACCION_VER:
                                    //crearLog("Ver Item", getObjetoItem().toString());
                                    break;
                                case Url.ACCION_BORRAR:
                                    //crearLog("Borrar Item", getObjetoItem().toString());
                                    break;
                                case Url.ACCION_GUARDAR:
                                    //crearLog("guardar Item", getObjetoItem().toString());
                                    break;
                            }
                            break;
                        case Url.ACCION_ADICIONAL_9:
                            //crearLog("Ver Anticipo Valor", getObjetoAnticipoValor().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (getDoAccion()) {
                        case Url.ACCION_ADICIONAL_2:
                           switch(getTakeAccion()){
                                case Url.ACCION_CREAR:
                                    crearLog("Ventana No prestado");
                                    break;
                                case Url.ACCION_GUARDAR:
                                    crearLog("guardar no prestado", getObjetoEntrega().toString());
                                    break;   
                            }
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (getDoAccion()) {
                        case Url.ACCION_CREAR:
                            crearLog("Crear entrega");
                            break;
                        case Url.ACCION_GUARDAR:
                            crearLog("Guardar entrega", getObjetoEntrega().toString());
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            crearLog("Ventana Adjunto Entregas");
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_4:
                    switch (getDoAccion()) {       
                        case Url.ACCION_ADICIONAL_4:
                            crearLog("anular entrega", getObjetoEntrega().toString());
                            break;
                        case Url.ACCION_GUARDAR:
                            crearLog("guardar Anular Entrega", getObjetoEntrega().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_5:
                    switch (getDoAccion()) {
                        case Url.ACCION_ADICIONAL_5:
                            crearLog("Cancelar Anticipo");
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().ajax().update("frmSinAutorizaciones");
                            crearLog("Guardar Cancelar Anticipo", getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_6:
                    switch (getDoAccion()) {
                        case Url.ACCION_ADICIONAL_6:
                            crearLog("Pago Anticipo");
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().ajax().update("frmSinAutorizaciones");
                            crearLog("Guardar Pago Anticipo", getObjeto().toString());
                            break;
                    }
                    break;

            }
        }
        generarMensajes();
    }
}
