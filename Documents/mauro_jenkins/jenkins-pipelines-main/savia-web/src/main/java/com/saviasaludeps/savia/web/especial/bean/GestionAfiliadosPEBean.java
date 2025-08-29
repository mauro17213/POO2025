/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.especial.bean;

import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContacto;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.especial.PeAdjunto;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadoDiagnostico;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.dominio.especial.PeGestion;
import com.saviasaludeps.savia.dominio.especial.PeGestionHistorico;
import com.saviasaludeps.savia.dominio.especial.PeIpsPrograma;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.especial.PeProgramaTraslado;
import com.saviasaludeps.savia.dominio.especial.PeTelefono;
import com.saviasaludeps.savia.dominio.especial.PeTipoVariable;
import com.saviasaludeps.savia.dominio.especial.PeUsuariosPrograma;
import com.saviasaludeps.savia.dominio.especial.PeValidacion;
import com.saviasaludeps.savia.dominio.especial.PeVariable;
import com.saviasaludeps.savia.dominio.especial.PeVariableValor;
import com.saviasaludeps.savia.dominio.especial.PeVariableValorHistorico;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.especial.servicio.GestionAfiliadosPEServicioIface;
import com.saviasaludeps.savia.web.especial.utilidades.PeConstantes;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelDiagnosticosBean;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author Jaime Andres Olarte
 */
@ManagedBean
@ViewScoped
public class GestionAfiliadosPEBean extends Url {

    @Autowired
    private GestionAfiliadosPEServicioIface afiliadosPEServicio;
    private PeAfiliadosPrograma objeto;
    private PeAdjunto objectoAdjunto;
    private List<PeAfiliadosPrograma> registros;
    private LazyDataModel<PeAfiliadosPrograma> lazyAfiliadosPE;
    private PeGestion objetoGestion;
    private LazyDataModel<AsegAfiliado> lazyAfiliados;
    private LazyDataModel<AucHospitalizacion> lazyHospitalizacion;
    private LazyDataModel<PeProgramaTraslado> lazyTrasladoPrograma;

    private UploadedFile archivoAdjunto;
    private SelDiagnosticosBean diagnosticosBean;

    private HashMap<Integer, Maestro> hashTiposDocumento;
    private List<Maestro> listaTiposDocumento;
    private List<Maestro> listaTiposGenero;
    private HashMap<Integer, Maestro> hashTiposGenero;
    private List<Maestro> listaEstadosAfiliacion;
    private HashMap<Integer, Maestro> hashEstadosAfiliacion;
    private List<Maestro> listaEstadosAfiliadoPrograma;
    private HashMap<Integer, Maestro> hashEstadosAfiliadoPrograma;
    private List<MaDiagnostico> listaDiagnostico;
    private HashMap<Integer, MaDiagnostico> hashDiagnostico;
    private List<Maestro> listaEstadoDiagnostico;
    private List<Maestro> listaRegionCorporal;
    private HashMap<Integer, Maestro> hashRegionCorporal;
    private List<Maestro> listaMedioDiagnostico;
    private HashMap<Integer, Maestro> hashMedioDiagnostico;
    private List<PePrograma> listaPePrograma;
    private List<PePrograma> listaProgramasAgrupador;
    private Integer idProgramaDestino; //usado para cambio de programa
    private PeProgramaTraslado programaTraslado;
    private List<Maestro> listaSentencias;

    private List<Ubicacion> listaUbicacion;
    private HashMap<Integer, Ubicacion> hashUbicacion;
    private List<PeAdjunto> peAdjuntos;
    private List<PeUsuariosPrograma> listResponsablesProg;
    private List<PeAfiliadosPrograma> listAfiliadoProgramas;
    private List<PeIpsPrograma> listPrestadorSede;
    private List<Maestro> listCausasActivo;
    private HashMap<Integer, Maestro> hashCausasActivo;
    private List<Maestro> listCausasInactivo;
    private HashMap<Integer, Maestro> hashCausasInActivo;
    private List<Maestro> listaTipoAdjunto;
    private HashMap<Integer, Maestro> hashMaeTipoAdjunto;
    private List<Maestro> listaGestionTipo;
    private List<AsegAfiliado> listaAfiliados;
    private List<Maestro> listaRegimen;
    private HashMap<Integer, Maestro> hashListaRegimen;
    private List<AsegAfiliadoContacto> listaContactos;
    private List<PeTelefono> listaContactosPrograma;
    private List<Maestro> listaFuenteOrigen;
    private List<Maestro> listaTiposContacto;
    private HashMap<Integer, Maestro> hashTiposContacto;
    private List<PeTelefono> listaContactosBorrar;
    private List<AucHospitalizacion> listaHospitalizacion;
    private List<PeProgramaTraslado> listaTrasladoProgramas;
    private List<PeGestionHistorico> listaHistoricoGestion;
    private List<Maestro> listaEstadoSivigila;
    private List<Maestro> listaCausaDescarte;
    private HashMap<Integer, Maestro> hashEstadosSivigila;
    private HashMap<Integer, Maestro> hashCausaDescarte;
    private List<Maestro> listaNotificacionSivigila;
    private HashMap<Integer, Maestro> hashNotificacionSivigila;
    private HashMap<Integer, Maestro> hashSentencias;

    private String estadoAfiliado;
    private String mensajeConfirma;
    private String dialogoAbrir;
    private Date fechaHoy;
    private String causaActivo = "";
    private String causaInActivo = "";
    private boolean afiliadoActivo;
    private PeTelefono contacto;
    private int contadorIdContacto;
    private int contadorIdDiagnosticos;
    private AucHospitalizacion objetoHospitalizacion;
    private String descripcionGenerica;
    private boolean activoCausaDescarte = true;
    private boolean activoEstadoSivigila = true;

    //Variables especificas
    private List<PeVariable> listadoVariablesEspecificas;
    private List<PeVariableValor> listadoValoresVariables;
    private List<PeVariableValorHistorico> listadoValoresVariablesHistorico;
    private String nombreVariable;//usado en historicos de valores de variable

    public final static int IDENTIFICADOR_ESTADO_ACTIVO_AFILIACION = 134;

    public GestionAfiliadosPEBean() {
        contadorIdContacto = -1;
        contadorIdDiagnosticos = -1;
        listaHospitalizacion = new ArrayList<>();
        listaPePrograma = new ArrayList<>();
        listaProgramasAgrupador = new ArrayList<>();
        listaTrasladoProgramas = new ArrayList<>();
        fechaHoy = new Date();
        this.objeto = new PeAfiliadosPrograma();
        this.objeto.setAsegAfiliado(new AsegAfiliado());
        this.objeto.setCntPrestadorSede(new CntPrestadorSede());
        this.objectoAdjunto = new PeAdjunto();
        this.objetoGestion = new PeGestion();
        this.objeto.setGnUsuario(new Usuario());
        peAdjuntos = new ArrayList<>();
        Modulo mod = super.validarModulo(Modulo.ID_PROGRAMA_ESPECIAL_GESTION_AFILIADOS);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            super.addListaParamConsultas(new ParamConsulta());
            super.addListaParamConsultas(new ParamConsulta());
            super.addListaParamConsultas(new ParamConsulta());
            super.addListaParamConsultas(new ParamConsulta());
            super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyAfiliadosPE = new LazyDataModel<PeAfiliadosPrograma>() {
                private List<PeAfiliadosPrograma> listAfiliadosProgramas;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta(0).getCantidadRegistros();
                }

                @Override
                public List<PeAfiliadosPrograma> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(0).setPrimerRegistro(primerRegistro);
                    getParamConsulta(0).setRegistrosPagina(registrosPagina);
                    getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    listAfiliadosProgramas = getRegistros();
                    setRowCount(getParamConsulta(0).getCantidadRegistros());
                    return listAfiliadosProgramas;
                }

                @Override
                public String getRowKey(PeAfiliadosPrograma afiliadosPrograma) {
                    return afiliadosPrograma.getId().toString();
                }

                @Override
                public PeAfiliadosPrograma getRowData(String programaId) {
                    Integer id = Integer.valueOf(programaId);
                    for (PeAfiliadosPrograma programa : listAfiliadosProgramas) {
                        if (id.equals(programa.getId())) {
                            return programa;
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
        getAfiliadosPEServicio().cargaInicial(this);
        listar();
    }

    // <editor-fold defaultstate="collapsed" desc="Gettes and Setters">
    public PeAfiliadosPrograma getObjeto() {
        return objeto;
    }

    public void setObjeto(PeAfiliadosPrograma objeto) {
        this.objeto = objeto;
    }

    public List<PeAfiliadosPrograma> getRegistros() {
        return registros;
    }

    public void setRegistros(List<PeAfiliadosPrograma> registros) {
        this.registros = registros;
    }

    public LazyDataModel<PeAfiliadosPrograma> getLazyAfiliadosPE() {
        return lazyAfiliadosPE;
    }

    public void setLazyAfiliadosPE(LazyDataModel<PeAfiliadosPrograma> lazyAfiliadosPE) {
        this.lazyAfiliadosPE = lazyAfiliadosPE;
    }

    public GestionAfiliadosPEServicioIface getAfiliadosPEServicio() {
        return afiliadosPEServicio;
    }

    public void setAfiliadosPEServicio(GestionAfiliadosPEServicioIface afiliadosPEServicio) {
        this.afiliadosPEServicio = afiliadosPEServicio;
    }

    public HashMap<Integer, Maestro> getHashTiposDocumento() {
        return hashTiposDocumento;
    }

    public void setHashTiposDocumento(HashMap<Integer, Maestro> hashTiposDocumento) {
        this.hashTiposDocumento = hashTiposDocumento;
    }

    public List<Maestro> getListaTiposDocumento() {
        return listaTiposDocumento;
    }

    public void setListaTiposDocumento(List<Maestro> listaTiposDocumento) {
        this.listaTiposDocumento = listaTiposDocumento;
    }

    public List<Maestro> getListaTiposGenero() {
        return listaTiposGenero;
    }

    public void setListaTiposGenero(List<Maestro> listaTiposGenero) {
        this.listaTiposGenero = listaTiposGenero;
    }

    public HashMap<Integer, Maestro> getHashTiposGenero() {
        return hashTiposGenero;
    }

    public void setHashTiposGenero(HashMap<Integer, Maestro> hashTiposGenero) {
        this.hashTiposGenero = hashTiposGenero;
    }

    public List<Maestro> getListaEstadosAfiliacion() {
        return listaEstadosAfiliacion;
    }

    public void setListaEstadosAfiliacion(List<Maestro> listaEstadosAfiliacion) {
        this.listaEstadosAfiliacion = listaEstadosAfiliacion;
    }

    public HashMap<Integer, Maestro> getHashEstadosAfiliacion() {
        return hashEstadosAfiliacion;
    }

    public void setHashEstadosAfiliacion(HashMap<Integer, Maestro> hashEstadosAfiliacion) {
        this.hashEstadosAfiliacion = hashEstadosAfiliacion;
    }

    public List<Maestro> getListaEstadosAfiliadoPrograma() {
        return listaEstadosAfiliadoPrograma;
    }

    public void setListaEstadosAfiliadoPrograma(List<Maestro> listaEstadosAfiliadoPrograma) {
        this.listaEstadosAfiliadoPrograma = listaEstadosAfiliadoPrograma;
    }

    public HashMap<Integer, Maestro> getHashEstadosAfiliadoPrograma() {
        return hashEstadosAfiliadoPrograma;
    }

    public void setHashEstadosAfiliadoPrograma(HashMap<Integer, Maestro> hashEstadosAfiliadoPrograma) {
        this.hashEstadosAfiliadoPrograma = hashEstadosAfiliadoPrograma;
    }

    public List<MaDiagnostico> getListaDiagnostico() {
        return listaDiagnostico;
    }

    public void setListaDiagnostico(List<MaDiagnostico> listaDiagnostico) {
        this.listaDiagnostico = listaDiagnostico;
    }

    public HashMap<Integer, MaDiagnostico> getHashDiagnostico() {
        return hashDiagnostico;
    }

    public void setHashDiagnostico(HashMap<Integer, MaDiagnostico> hashDiagnostico) {
        this.hashDiagnostico = hashDiagnostico;
    }

    public List<Maestro> getListaEstadoDiagnostico() {
        return listaEstadoDiagnostico;
    }

    public void setListaEstadoDiagnostico(List<Maestro> listaEstadoDiagnostico) {
        this.listaEstadoDiagnostico = listaEstadoDiagnostico;
    }

    public List<Maestro> getListaRegionCorporal() {
        return listaRegionCorporal;
    }

    public void setListaRegionCorporal(List<Maestro> listaRegionCorporal) {
        this.listaRegionCorporal = listaRegionCorporal;
    }

    public HashMap<Integer, Maestro> getHashRegionCorporal() {
        return hashRegionCorporal;
    }

    public void setHashRegionCorporal(HashMap<Integer, Maestro> hashRegionCorporal) {
        this.hashRegionCorporal = hashRegionCorporal;
    }

    public List<Maestro> getListaMedioDiagnostico() {
        return listaMedioDiagnostico;
    }

    public void setListaMedioDiagnostico(List<Maestro> listaMedioDiagnostico) {
        this.listaMedioDiagnostico = listaMedioDiagnostico;
    }

    public HashMap<Integer, Maestro> getHashMedioDiagnostico() {
        return hashMedioDiagnostico;
    }

    public void setHashMedioDiagnostico(HashMap<Integer, Maestro> hashMedioDiagnostico) {
        this.hashMedioDiagnostico = hashMedioDiagnostico;
    }

    public String getEstadoAfiliado() {
        return estadoAfiliado;
    }

    public void setEstadoAfiliado(String estadoAfiliado) {
        this.estadoAfiliado = estadoAfiliado;
    }

    public List<PePrograma> getListaPePrograma() {
        return listaPePrograma;
    }

    public void setListaPePrograma(List<PePrograma> listaPePrograma) {
        this.listaPePrograma = listaPePrograma;
    }

    public List<Ubicacion> getListaUbicacion() {
        return listaUbicacion;
    }

    public void setListaUbicacion(List<Ubicacion> listaUbicacion) {
        this.listaUbicacion = listaUbicacion;
    }

    public HashMap<Integer, Ubicacion> getHashUbicacion() {
        return hashUbicacion;
    }

    public void setHashUbicacion(HashMap<Integer, Ubicacion> hashUbicacion) {
        this.hashUbicacion = hashUbicacion;
    }

    public UploadedFile getArchivoAdjunto() {
        return archivoAdjunto;
    }

    public void setArchivoAdjunto(UploadedFile archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
    }

    public List<PeAdjunto> getPeAdjuntos() {
        return peAdjuntos;
    }

    public void setPeAdjuntos(List<PeAdjunto> peAdjuntos) {
        this.peAdjuntos = peAdjuntos;
    }

    public PeAdjunto getObjectoAdjunto() {
        return objectoAdjunto;
    }

    public void setObjectoAdjunto(PeAdjunto objectoAdjunto) {
        this.objectoAdjunto = objectoAdjunto;
    }

    public PeGestion getObjetoGestion() {
        return objetoGestion;
    }

    public void setObjetoGestion(PeGestion objetoGestion) {
        this.objetoGestion = objetoGestion;
    }

    public List<PeUsuariosPrograma> getListResponsablesProg() {
        return listResponsablesProg;
    }

    public void setListResponsablesProg(List<PeUsuariosPrograma> listResponsablesProg) {
        this.listResponsablesProg = listResponsablesProg;
    }

    public List<PeAfiliadosPrograma> getListAfiliadoProgramas() {
        return listAfiliadoProgramas;
    }

    public void setListAfiliadoProgramas(List<PeAfiliadosPrograma> listAfiliadoProgramas) {
        this.listAfiliadoProgramas = listAfiliadoProgramas;
    }

    public String getMensajeConfirma() {
        return mensajeConfirma;
    }

    public void setMensajeConfirma(String mensajeConfirma) {
        this.mensajeConfirma = mensajeConfirma;
    }

    public Date getFechaHoy() {
        return fechaHoy;
    }

    public void setFechaHoy(Date fechaHoy) {
        this.fechaHoy = fechaHoy;
    }

    public List<Maestro> getListCausasActivo() {
        return listCausasActivo;
    }

    public void setListCausasActivo(List<Maestro> listCausasActivo) {
        this.listCausasActivo = listCausasActivo;
    }

    public List<Maestro> getListCausasInactivo() {
        return listCausasInactivo;
    }

    public void setListCausasInactivo(List<Maestro> listCausasInactivo) {
        this.listCausasInactivo = listCausasInactivo;
    }

    public HashMap<Integer, Maestro> getHashCausasActivo() {
        return hashCausasActivo;
    }

    public void setHashCausasActivo(HashMap<Integer, Maestro> hashCausasActivo) {
        this.hashCausasActivo = hashCausasActivo;
    }

    public HashMap<Integer, Maestro> getHashCausasInActivo() {
        return hashCausasInActivo;
    }

    public void setHashCausasInActivo(HashMap<Integer, Maestro> hashCausasInActivo) {
        this.hashCausasInActivo = hashCausasInActivo;
    }

    public String getCausaActivo() {
        return causaActivo;
    }

    public void setCausaActivo(String causaActivo) {
        this.causaActivo = causaActivo;
    }

    public String getCausaInActivo() {
        return causaInActivo;
    }

    public void setCausaInActivo(String causaInActivo) {
        this.causaInActivo = causaInActivo;
    }

    public String getDialogoAbrir() {
        return dialogoAbrir;
    }

    public void setDialogoAbrir(String dialogoAbrir) {
        this.dialogoAbrir = dialogoAbrir;
    }

    public PeTelefono getContacto() {
        return contacto;
    }

    public void setContacto(PeTelefono contacto) {
        this.contacto = contacto;
    }

    public List<Maestro> getListaTiposContacto() {
        return listaTiposContacto;
    }

    public void setListaTiposContacto(List<Maestro> listaTiposContacto) {
        this.listaTiposContacto = listaTiposContacto;
    }

    public HashMap<Integer, Maestro> getHashTiposContacto() {
        return hashTiposContacto;
    }

    public void setHashTiposContacto(HashMap<Integer, Maestro> hashTiposContacto) {
        this.hashTiposContacto = hashTiposContacto;
    }

    public List<PeIpsPrograma> getListPrestadorSede() {
        return listPrestadorSede;
    }

    public void setListPrestadorSede(List<PeIpsPrograma> listPrestadorSede) {
        this.listPrestadorSede = listPrestadorSede;
    }

    public List<Maestro> getListaTipoAdjunto() {
        return listaTipoAdjunto;
    }

    public void setListaTipoAdjunto(List<Maestro> listaTipoAdjunto) {
        this.listaTipoAdjunto = listaTipoAdjunto;
    }

    public HashMap<Integer, Maestro> getHashMaeTipoAdjunto() {
        return hashMaeTipoAdjunto;
    }

    public void setHashMaeTipoAdjunto(HashMap<Integer, Maestro> hashMaeTipoAdjunto) {
        this.hashMaeTipoAdjunto = hashMaeTipoAdjunto;
    }

    public List<Maestro> getListaGestionTipo() {
        return listaGestionTipo;
    }

    public void setListaGestionTipo(List<Maestro> listaGestionTipo) {
        this.listaGestionTipo = listaGestionTipo;
    }

    public List<Maestro> getListaRegimen() {
        return listaRegimen;
    }

    public void setListaRegimen(List<Maestro> listaRegimen) {
        this.listaRegimen = listaRegimen;
    }

    public boolean isAfiliadoActivo() {
        return afiliadoActivo;
    }

    public void setAfiliadoActivo(boolean afiliadoActivo) {
        this.afiliadoActivo = afiliadoActivo;
    }

    public LazyDataModel<AsegAfiliado> getLazyAfiliados() {
        return lazyAfiliados;
    }

    public void setLazyAfiliados(LazyDataModel<AsegAfiliado> lazyAfiliados) {
        this.lazyAfiliados = lazyAfiliados;
    }

    public List<AsegAfiliado> getListaAfiliados() {
        return listaAfiliados;
    }

    public void setListaAfiliados(List<AsegAfiliado> listaAfiliados) {
        this.listaAfiliados = listaAfiliados;
    }

    public SelDiagnosticosBean getDiagnosticosBean() {
        diagnosticosBean = (SelDiagnosticosBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selDiagnosticosBean");
        return diagnosticosBean;
    }

    public List<AsegAfiliadoContacto> getListaContactos() {
        return listaContactos;
    }

    public void setListaContactos(List<AsegAfiliadoContacto> listaContactos) {
        this.listaContactos = listaContactos;
    }

    public List<PeTelefono> getListaContactosPrograma() {
        return listaContactosPrograma;
    }

    public void setListaContactosPrograma(List<PeTelefono> listaContactosPrograma) {
        this.listaContactosPrograma = listaContactosPrograma;
    }

    public List<Maestro> getListaFuenteOrigen() {
        return listaFuenteOrigen;
    }

    public void setListaFuenteOrigen(List<Maestro> listaFuenteOrigen) {
        this.listaFuenteOrigen = listaFuenteOrigen;
    }

    public int getContadorIdContacto() {
        return contadorIdContacto;
    }

    public void setContadorIdContacto(int contadorIdContacto) {
        this.contadorIdContacto = contadorIdContacto;
    }

    public List<PeTelefono> getListaContactosBorrar() {
        return listaContactosBorrar;
    }

    public void setListaContactosBorrar(List<PeTelefono> listaContactosBorrar) {
        this.listaContactosBorrar = listaContactosBorrar;
    }

    public AucHospitalizacion getObjetoHospitalizacion() {
        return objetoHospitalizacion;
    }

    public void setObjetoHospitalizacion(AucHospitalizacion objetoHospitalizacion) {
        this.objetoHospitalizacion = objetoHospitalizacion;
    }

    public String getDescripcionGenerica() {
        return descripcionGenerica;
    }

    public void setDescripcionGenerica(String descripcionGenerica) {
        this.descripcionGenerica = descripcionGenerica;
    }

    public LazyDataModel<AucHospitalizacion> getLazyHospitalizacion() {
        return lazyHospitalizacion;
    }

    public void setLazyHospitalizacion(LazyDataModel<AucHospitalizacion> lazyHospitalizacion) {
        this.lazyHospitalizacion = lazyHospitalizacion;
    }

    public LazyDataModel<PeProgramaTraslado> getLazyTrasladoPrograma() {
        return lazyTrasladoPrograma;
    }

    public void setLazyTrasladoPrograma(LazyDataModel<PeProgramaTraslado> lazyTrasladoPrograma) {
        this.lazyTrasladoPrograma = lazyTrasladoPrograma;
    }

    public List<AucHospitalizacion> getListaHospitalizacion() {
        return listaHospitalizacion;
    }

    public void setListaHospitalizacion(List<AucHospitalizacion> listaHospitalizacion) {
        this.listaHospitalizacion = listaHospitalizacion;
    }

    public List<PeProgramaTraslado> getListaTrasladoProgramas() {
        return listaTrasladoProgramas;
    }

    public void setListaTrasladoProgramas(List<PeProgramaTraslado> listaTrasladoProgramas) {
        this.listaTrasladoProgramas = listaTrasladoProgramas;
    }

    public List<PeGestionHistorico> getListaHistoricoGestion() {
        return listaHistoricoGestion;
    }

    public void setListaHistoricoGestion(List<PeGestionHistorico> listaHistoricoGestion) {
        this.listaHistoricoGestion = listaHistoricoGestion;
    }

    public List<Maestro> getListaEstadoSivigila() {
        return listaEstadoSivigila;
    }

    public void setListaEstadoSivigila(List<Maestro> listaEstadoSivigila) {
        this.listaEstadoSivigila = listaEstadoSivigila;
    }

    public List<Maestro> getListaCausaDescarte() {
        return listaCausaDescarte;
    }

    public void setListaCausaDescarte(List<Maestro> listaCausaDescarte) {
        this.listaCausaDescarte = listaCausaDescarte;
    }

    public HashMap<Integer, Maestro> getHashEstadosSivigila() {
        return hashEstadosSivigila;
    }

    public void setHashEstadosSivigila(HashMap<Integer, Maestro> hashEstadosSivigila) {
        this.hashEstadosSivigila = hashEstadosSivigila;
    }

    public boolean isActivoCausaDescarte() {
        return activoCausaDescarte;
    }

    public void setActivoCausaDescarte(boolean activoCausaDescarte) {
        this.activoCausaDescarte = activoCausaDescarte;
    }

    public HashMap<Integer, Maestro> getHashCausaDescarte() {
        return hashCausaDescarte;
    }

    public void setHashCausaDescarte(HashMap<Integer, Maestro> hashCausaDescarte) {
        this.hashCausaDescarte = hashCausaDescarte;
    }

    public List<Maestro> getListaNotificacionSivigila() {
        return listaNotificacionSivigila;
    }

    public void setListaNotificacionSivigila(List<Maestro> listaNotificacionSivigila) {
        this.listaNotificacionSivigila = listaNotificacionSivigila;
    }

    public HashMap<Integer, Maestro> getHashNotificacionSivigila() {
        return hashNotificacionSivigila;
    }

    public void setHashNotificacionSivigila(HashMap<Integer, Maestro> hashNotificacionSivigila) {
        this.hashNotificacionSivigila = hashNotificacionSivigila;
    }

    public boolean isActivoEstadoSivigila() {
        return activoEstadoSivigila;
    }

    public void setActivoEstadoSivigila(boolean activoEstadoSivigila) {
        this.activoEstadoSivigila = activoEstadoSivigila;
    }

    public HashMap<Integer, Maestro> getHashListaRegimen() {
        return hashListaRegimen;
    }

    public void setHashListaRegimen(HashMap<Integer, Maestro> hashListaRegimen) {
        this.hashListaRegimen = hashListaRegimen;
    }

    public List<PeVariable> getListadoVariablesEspecificas() {
        return listadoVariablesEspecificas;
    }

    public void setListadoVariablesEspecificas(List<PeVariable> listadoVariablesEspecificas) {
        this.listadoVariablesEspecificas = listadoVariablesEspecificas;
    }

    public List<PeVariableValor> getListadoValoresVariables() {
        return listadoValoresVariables;
    }

    public void setListadoValoresVariables(List<PeVariableValor> listadoValoresVariables) {
        this.listadoValoresVariables = listadoValoresVariables;
    }

    public List<PeVariableValorHistorico> getListadoValoresVariablesHistorico() {
        return listadoValoresVariablesHistorico;
    }

    public void setListadoValoresVariablesHistorico(List<PeVariableValorHistorico> listadoValoresVariablesHistorico) {
        this.listadoValoresVariablesHistorico = listadoValoresVariablesHistorico;
    }

    public String getNombreVariable() {
        return nombreVariable;
    }

    public void setNombreVariable(String nombreVariable) {
        this.nombreVariable = nombreVariable;
    }

    public List<Maestro> getListaSentencias() {
        return listaSentencias;
    }

    public void setListaSentencias(List<Maestro> listaSentencias) {
        this.listaSentencias = listaSentencias;
    }

    public HashMap<Integer, Maestro> getHashSentencias() {
        return hashSentencias;
    }

    public void setHashSentencias(HashMap<Integer, Maestro> hashSentencias) {
        this.hashSentencias = hashSentencias;
    }

    public List<PePrograma> getListaProgramasAgrupador() {
        return listaProgramasAgrupador;
    }

    public void setListaProgramasAgrupador(List<PePrograma> listaProgramasAgrupador) {
        this.listaProgramasAgrupador = listaProgramasAgrupador;
    }

    public Integer getIdProgramaDestino() {
        return idProgramaDestino;
    }

    public void setIdProgramaDestino(Integer idProgramaDestino) {
        this.idProgramaDestino = idProgramaDestino;
    }

    public PeProgramaTraslado getProgramaTraslado() {
        return programaTraslado;
    }

    public void setProgramaTraslado(PeProgramaTraslado programaTraslado) {
        this.programaTraslado = programaTraslado;
    }

    // </editor-fold>
    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getAfiliadosPEServicio().Accion(this);
    }

    public void refrescarAfiliado() {
        getAfiliadosPEServicio().consultarAfiliado(this);
    }

    public void refrescarHospitalizacion() {
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(Url.ACCION_LISTAR);
        getAfiliadosPEServicio().Accion(this);
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getAfiliadosPEServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmVerAfiliado");
        PrimeFaces.current().ajax().update("frmVerAfiliado");
        PrimeFaces.current().resetInputs("frmVer");
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().ajax().update("frmVer:tablaDiagnosticosVer");
        PrimeFaces.current().executeScript("PF('tablaValoresVariablesVer').clearFilters()");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        super.setDoAccion(ACCION_CREAR);
        getAfiliadosPEServicio().Accion(this);
        contadorIdContacto = -1;
        contadorIdDiagnosticos = -1;
        cambiarCausas();
        validarTipoPaciente();
        validarEstadoSivigila();
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().resetInputs("frmAfiliado");
        PrimeFaces.current().ajax().update("frmAfiliado");
        PrimeFaces.current().ajax().update("frmCrear:pnlMatricula");
        PrimeFaces.current().ajax().update("frmCrear:pDiagnosticoCrear");
        PrimeFaces.current().ajax().update("frmCrear:pnlSiVigila");
        PrimeFaces.current().ajax().update("frmCrear:pnlVariable");
        PrimeFaces.current().executeScript("PF('tablaValoresVariablesCrear').clearFilters()");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getAfiliadosPEServicio().Accion(this);
        if (!mensajeConfirma.isEmpty()) {
            PrimeFaces.current().ajax().update("frmConfirmaPA");
            PrimeFaces.current().ajax().update("frmConfirmaPAA");
            PrimeFaces.current().executeScript("PF('" + dialogoAbrir + "').show()");
        } else {
            if (!super.isError()) {
                PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
            }
            procesoFinal();
        }
    }

    public void editar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_EDITAR);
        getAfiliadosPEServicio().Accion(this);
        validarCausaDecarte();
        contadorIdContacto = -1;
        cambiarCausas();
        this.agregarValoresVariables();
        PrimeFaces.current().ajax().update("frmEditarAfiliado");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('tablaValoresVariablesEditar').clearFilters()");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getAfiliadosPEServicio().Accion(this);
        contadorIdContacto = -1;
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_BORRAR);
        getAfiliadosPEServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmGestion");
        procesoFinal();
    }

    public void verHistoricoEdiciones(Integer idGestion) {
        getObjetoGestion().setId(idGestion);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_VER);
        getAfiliadosPEServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmHistoEdiGestion:tablaHistoricoGestiones");
            PrimeFaces.current().executeScript("PF('dialogoHistoricoEdicion').show()");
        }
        procesoFinal();
    }

    public String getTipoGenero(String id) {
        try {
            return hashTiposGenero.get(Integer.parseInt(id)).getNombre();
        } catch (NumberFormatException e) {
            return id;
        }
    }

    public String getTipoDocumento(Integer id) {
        try {
            return hashTiposDocumento.get(id).getNombre();
        } catch (Exception e) {
            return String.valueOf(id);
        }
    }

    public String getEstadoAfiliacion(Integer id) {
        try {
            if (id == null) {
                return "";
            }
            return hashEstadosAfiliacion.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public String getEstadoAfiliadoPrograma(int id) {
        try {
            return hashEstadosAfiliadoPrograma.get(id).getNombre();
        } catch (Exception e) {
            return String.valueOf(id);
        }
    }

    public void consultarAfiliado() {
        iniciarLazyAfiliados();
        PrimeFaces.current().ajax().update("frmAfiliadoLista");
        PrimeFaces.current().executeScript("PF('dialogoAfiliadoLista').show()");
    }

    private void iniciarLazyAfiliados() {
        lazyAfiliados = new LazyDataModel<AsegAfiliado>() {
            private List<AsegAfiliado> afiliados;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(1).getCantidadRegistros();
            }

            @Override
            public List<AsegAfiliado> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(1).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(1).setPrimerRegistro(primerRegistro);
                getParamConsulta(1).setRegistrosPagina(registrosPagina);
                getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(1).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarAfiliado();
                afiliados = getListaAfiliados();
                setRowCount(getParamConsulta(1).getCantidadRegistros());
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
        getObjeto().setAsegAfiliado(afiliado);
        boolean afiliado_activo = getAfiliadosPEServicio().validarEstadoAfiliado(afiliado.getMaeEstadoAfiliacion());
        if (getObjeto().getAsegAfiliado().getId() != null && !afiliado_activo) {
            setEstadoAfiliado(hashEstadosAfiliacion.get(getObjeto().getAsegAfiliado().getMaeEstadoAfiliacion()).getNombre());
            PrimeFaces.current().ajax().update("frmConfirma");
            PrimeFaces.current().executeScript("PF('dialogoConfirma').show()");
        } else {
            getAfiliadosPEServicio().consultarAfiliadoContactos(this);
            PrimeFaces.current().ajax().update("frmAfiliado:pnlAfiliado");
            PrimeFaces.current().ajax().update("frmAfiliado:tablaContactos");
            PrimeFaces.current().ajax().update("frmAfiliado:tablaContactosCrearPro");
            PrimeFaces.current().ajax().update("frmEditarAfiliado:tablaContactos");
            PrimeFaces.current().executeScript("PF('dialogoAfiliadoLista').hide()");
            validarSexoAplicaPrograma();
        }
        generarMensajes();
    }

    public void consultarResponsablesProg(int idPrograma) {
        getAfiliadosPEServicio().consultarResponsablesPrestadorPrograma(this);
        PrimeFaces.current().ajax().update("frmCrear:SomResponsable");
        PrimeFaces.current().ajax().update("frmCrear:SomIps");
        validarSexoAplicaPrograma();
        this.establecerVariablesEspecificas(idPrograma);
    }

    private void establecerVariablesEspecificas(int idPrograma) {
        this.getAfiliadosPEServicio().consultarVariablesEspecificas(idPrograma, this);
        this.agregarValoresVariables();
    }

    private void actualizarPanelVariables() {

        if (getAccion() == ACCION_CREAR || getAccion() == ACCION_GUARDAR) {
            PrimeFaces.current().ajax().update("frmCrear:pnlVariable");
        } else {//ACCION_EDITAR
            PrimeFaces.current().ajax().update("frmEditar:pnlVariable");
        }
    }

    public void agregarValoresVariables() {
        if (this.getAccion() == ACCION_CREAR) {

            this.getListadoValoresVariables().clear();
            this.getListadoVariablesEspecificas().forEach(
                    v -> {
                        if (v.isActiva()) {
                            PeVariableValor peVariableValor = new PeVariableValor(v);
                            this.getListadoValoresVariables().add(peVariableValor);
                        }
                    }
            );
        } else {//ACCION_EDITAR
            this.getListadoVariablesEspecificas().forEach(
                    v -> {

                        this.getListadoValoresVariables().stream()
                                .forEach(vv -> {
                                    //en caso de que la variable ya tenga un valor
                                    if (v.getId().equals(vv.getIdVariable())) {
                                        //se establece el valor descripcion, activa, obligatoria, recurrente (para que sea dinamico si se actualiza) de la variable al valor
                                        vv.setDescripcion(v.getDescripcion());
                                        vv.setActiva(v.isActiva());
                                        vv.setObligatoria(v.isObligatoria());
                                        vv.setRecurrente(v.isRecurrente());
                                        vv.setEditable(v.isEditable());
                                        vv.setValidaciones(v.getValidaciones());
                                    }
                                });

                        boolean noExiste = this.getListadoValoresVariables().stream()
                                .noneMatch(vv -> v.getId().equals(vv.getIdVariable()));

                        if (noExiste) {
                            this.getListadoValoresVariables().add(new PeVariableValor(v));
                        }
                    }
            );
            List<PeVariableValor> listadoValoresActivos = this.getListadoValoresVariables().stream().filter(vv -> vv.isActiva()).collect(Collectors.toList());
            this.setListadoValoresVariables(listadoValoresActivos);
        }
        this.actualizarPanelVariables();
    }

    public int asignarRecurrencia(List<PeVariableValor> valoresRecurrentes) {
        int recurrencia = 1;
        for (PeVariableValor valor : valoresRecurrentes) {
            valor.setRecurrencia((short) recurrencia++);
        }
        return recurrencia;
    }

    public void agregarRecurrencia(PeVariableValor variableValor) {
        List<PeVariableValor> valoresRecurrentes = this.getListadoValoresVariables().stream()
                .filter(vv -> vv.getIdVariable().equals(variableValor.getIdVariable()))
                .collect(Collectors.toList());

        int recurrencia = this.asignarRecurrencia(valoresRecurrentes);

        PeVariableValor peVariableValor = new PeVariableValor(variableValor);
        peVariableValor.setRecurrencia((short) recurrencia);
        this.getListadoValoresVariables().add(peVariableValor);
    }

    public boolean mostrarBtnDisminuir(PeVariableValor variableValor) {
        List<PeVariableValor> variablesRecurrentes = this.getListadoValoresVariables().stream()
                .filter(vv -> vv.getIdVariable().equals(variableValor.getIdVariable()))
                .collect(Collectors.toList());

        long conteo = variablesRecurrentes.size();

        if (conteo <= 1) {
            return false;
        }
        //n elementos
        int posicion = variablesRecurrentes.indexOf(variableValor);

        if (getAccion() == ACCION_CREAR) {
            return posicion != 0;
        }
        return posicion != 0 && variableValor.getFechaHoraCrea() == null;//si la variable ya esta creada no se podra habilitar la opcion
    }

    public void disminuirRecurrencia(PeVariableValor variableValor) {
        this.getListadoValoresVariables().remove(variableValor);//borrado

        List<PeVariableValor> valoresRecurrentes = this.getListadoValoresVariables().stream()
                .filter(vv -> vv.getIdVariable().equals(variableValor.getIdVariable()))//filtramos las variables iguales
                .collect(Collectors.toList());

        this.asignarRecurrencia(valoresRecurrentes); //restablecemos la recurrencia, una vez suprimido la variable.
    }

    public void mostrarHistorialValoresVariables(PeVariableValor variableValor) {
        this.getAfiliadosPEServicio().consultarValoresVariablesHistorico(variableValor, this);
        if (!super.isError()) {
            this.setNombreVariable(variableValor.getDescripcion());
            PrimeFaces.current().ajax().update("frmHistoricoValoresVariable:tablaHistoricoValoresVariables");
            PrimeFaces.current().executeScript("PF('dialogoHistoricoValoresVariable').show()");
        }
    }

    public void continuar() {
        getAfiliadosPEServicio().consultarAfiliadoContactos(this);
        PrimeFaces.current().executeScript("PF('dialogoConfirma').hide()");
        PrimeFaces.current().executeScript("PF('dialogoAfiliadoLista').hide()");
        PrimeFaces.current().ajax().update("frmAfiliado:panelAfiliado");
        PrimeFaces.current().ajax().update("frmAfiliado:tablaContactos");
    }

    public void cancelar() {
        getObjeto().setAsegAfiliado(new AsegAfiliado());
        PrimeFaces.current().executeScript("PF('dialogoConfirma').hide()");
        PrimeFaces.current().ajax().update("frmAfiliado:panelAfiliado");
    }

    public void agregarAfiliado() {
        PrimeFaces.current().ajax().update("frmAfiliado:panelAfiliado");
    }

    public void quitarAfiliado() {
        getObjeto().setAsegAfiliado(new AsegAfiliado());
        PrimeFaces.current().ajax().update("frmAfiliado:panelAfiliado");
    }

    public List<Maestro> completarRegionCorporal(String query) {
        List<Maestro> regionCorporalFiltrados = new ArrayList<>();
        for (Maestro region : getListaRegionCorporal()) {
            if (region.getNombre().toLowerCase().contains(query.toLowerCase())) {
                regionCorporalFiltrados.add(region);
            }
        }

        if (regionCorporalFiltrados.size() == 1) {
            getObjeto().setMaeRegionCorporalId(regionCorporalFiltrados.get(0).getId());
        }
        return regionCorporalFiltrados;
    }

    public String getRegionCorporalRecursiva(int id) {
        String regionStr = "";
        if (hashRegionCorporal != null) {
            Maestro maestroRegionCor = hashRegionCorporal.get(id);
            if (maestroRegionCor != null && maestroRegionCor.getId() != null) {
                regionStr = maestroRegionCor.getNombre();
            }
        }
        return regionStr;
    }

    public List<Maestro> completarMedioDx(String query) {
        List<Maestro> medioDxFiltrados = new ArrayList<>();
        for (Maestro region : getListaMedioDiagnostico()) {
            if (region.getNombre().toLowerCase().contains(query.toLowerCase())) {
                medioDxFiltrados.add(region);
            }
        }

        if (medioDxFiltrados.size() == 1) {
            getObjeto().setMaeMedioDxId(medioDxFiltrados.get(0).getId());
        }
        return medioDxFiltrados;
    }

    public List<PePrograma> completarPePrograma(String query) {
        List<PePrograma> programasFiltrados = new ArrayList<>();
        for (PePrograma programa : getListaPePrograma()) {
            if (programa.getDescripcionPrograma().toLowerCase().contains(query.toLowerCase())) {
                programasFiltrados.add(programa);
            }
        }
        return programasFiltrados;
    }

    public String getMedioDxRecursiva(Integer id) {
        String medioDxStr = "";
        if (id != null) {
            Maestro maestroMedioDx = hashMedioDiagnostico.get(id);
            if (maestroMedioDx != null && maestroMedioDx.getId() != null) {
                medioDxStr = maestroMedioDx.getNombre();
            }
        }
        return medioDxStr;
    }

    public String getEstadoRecursiva(Boolean estado) {
        if (estado != null && estado) {
            return "Si";
        } else {
            return "No";
        }
    }

    public String getTipoAdjuntoRecursiva(int id) {
        return hashMaeTipoAdjunto.get(id).getNombre();
    }

    public String obtenerTipoDocumentoAfiliado(int id) {
        try {
            return hashTiposDocumento.get(id).getNombre();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public String obtenerEstadoAfiliado(int id) {
        try {
            return hashEstadosAfiliacion.get(id).getNombre();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public String obtenerBoolean(boolean bool) {
        return bool == true ? AuConstantes.TEXTO_SI : AuConstantes.TEXTO_NO;
    }

    /**
     * Metodo que retona el nombre completo mde la ubicacion a partir de un ID
     *
     * @param id
     * @return
     */
    public String getUbicacionRecursiva(int id) {
        String ubicacionStr = "";
        Ubicacion municipio = null;
        if (getHashUbicacion() != null) {
            municipio = getHashUbicacion().get(id);
        }
        if (municipio != null && municipio.getUbicacionPadre() != null) {
            Ubicacion departamento = municipio.getUbicacionPadre();
            ubicacionStr = departamento.getNombre();
            ubicacionStr = municipio.getNombre() + " - " + ubicacionStr;
        }
        return ubicacionStr;
    }

    public String getProgramaRecursiva(int id) {
        String programaStr = "";

        for (PePrograma pePrograma : listaPePrograma) {
            if (pePrograma.getId() == id) {
                programaStr = pePrograma.getDescripcionPrograma();
                break;
            }
        }

        return programaStr;
    }

    public String getCausasRecursiva(Integer id) {
        String causasStr = "";
        if (id != null) {
            for (Maestro maestro : listCausasActivo) {
                if (maestro.getId().intValue() == id) {
                    causasStr = maestro.getNombre();
                    break;
                }
            }

            for (Maestro maestro : listCausasInactivo) {
                if (maestro.getId().intValue() == id) {
                    causasStr = maestro.getNombre();
                    break;
                }
            }
        }
        return causasStr;
    }

    public void cargarArchivoDocumento(FileUploadEvent event) throws IOException {
        archivoAdjunto = event.getFile();
        try {
            if (objectoAdjunto.getMaeTipoArchivoId() == 0) {
                addError("Tipo: Error de validación: se necesita un valor.");
                generarMensajes();
                return;
            }
            PeAdjunto borrarObj = new PeAdjunto();
            for (PeAdjunto peAdjunto : peAdjuntos) {
                if (peAdjunto.getMaeTipoArchivoId() == objectoAdjunto.getMaeTipoArchivoId()) {
                    borrarObj = peAdjunto;
                    break;
                }
            }
            peAdjuntos.remove(borrarObj);
            objectoAdjunto.setAdjuntoStream(archivoAdjunto.getInputStream());
            String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
            int indiceExtension = nombreAdjunto.lastIndexOf(".");
            objectoAdjunto.setExtensión(nombreAdjunto.substring(indiceExtension, nombreAdjunto.length()));
            objectoAdjunto.setNombre(nombreAdjunto.substring(0, indiceExtension));
            peAdjuntos.add(objectoAdjunto);
            objectoAdjunto = new PeAdjunto();
            PrimeFaces.current().ajax().update("frmAdjunto");
        } catch (IOException ex) {
            this.addError(ex.toString());
            generarMensajes();
        }
    }

    public void borrarAdjunto(String nombre) {
        PeAdjunto borraObjecto = new PeAdjunto();
        for (PeAdjunto peAdjunto : peAdjuntos) {
            if (peAdjunto.getNombre().equals(nombre)) {
                borraObjecto = peAdjunto;
                break;
            }
        }

        peAdjuntos.remove(borraObjecto);
        PrimeFaces.current().ajax().update("frmAdjunto");
    }

    public void abrirAdjuntos() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_CREAR);
        getAfiliadosPEServicio().Accion(this);
        setPeAdjuntos(new ArrayList<>());
        PrimeFaces.current().resetInputs("frmAdjunto");
        PrimeFaces.current().ajax().update("frmAdjunto");
        PrimeFaces.current().ajax().update("frmAuditoriaVer");
        PrimeFaces.current().executeScript("PF('dialogoAdjuntos').show()");
        procesoFinal();
    }

    public void guardarAdjunto() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_GUARDAR);
        getAfiliadosPEServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmAdjunto");
        procesoFinal();
    }

    public void descargarAdjunto(PeAdjunto adjunto) {
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
            String attachmentName = "attachment; filename=\"" + adjunto.getArchivo() + "\"";
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

    public void abrirGestion() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_LISTAR);
        getAfiliadosPEServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmGestionAfiliado");
        PrimeFaces.current().ajax().update("frmGestionAfiliado");
        //PrimeFaces.current().resetInputs("frmGestionPrograma");
        //PrimeFaces.current().ajax().update("frmGestionPrograma");
        PrimeFaces.current().executeScript("PF('dialogoGestiones').show()");
        procesoFinal();
    }

    public void crearGestion() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_CREAR);
        setObjetoGestion(new PeGestion());
        PrimeFaces.current().resetInputs("frmGestiones");
        PrimeFaces.current().ajax().update("frmGestiones");
        PrimeFaces.current().executeScript("PF('dialogoCrearGestion').show()");
        procesoFinal();
    }

    public void guardarGestiones() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_GUARDAR);
        getAfiliadosPEServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmGestionAfiliado");
//        PrimeFaces.current().ajax().update("frmGestionPrograma");
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearGestion').hide();");
        }
        procesoFinal();
    }

    public void changeActivo() {
        cambiarCausas();
    }

    public void cambiarCausas() {
        if (getObjeto().getActivo()) {
            causaActivo = "visible";
            causaInActivo = "noVisible";
            PrimeFaces.current().ajax().update("frmEditar:fechaFin");
        } else {
            causaActivo = "noVisible";
            causaInActivo = "visible";
        }
    }

    public void verHospitalizacion(AucHospitalizacion aucHospitalizacion) {
        if (aucHospitalizacion == null) {
            addError("No hay información de hospitalización.");
        } else {
            setObjetoHospitalizacion(aucHospitalizacion);
            super.setAccion(Url.ACCION_ADICIONAL_3);
            super.setDoAccion(Url.ACCION_VER);
            getAfiliadosPEServicio().Accion(this);
            if (!super.isError()) {
                PrimeFaces.current().ajax().update("frmVerHospitalizacion");
                PrimeFaces.current().executeScript("PF('dialogoVerHospitalizacion').show()");
            }
        }
        procesoFinal();
    }

    public void verListadoHospitalizacion(PeAfiliadosPrograma peAfiliadosPrograma) {
        setObjeto(peAfiliadosPrograma);
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(Url.ACCION_LISTAR);
        inicializarTablaHospitalizacion(peAfiliadosPrograma.getAsegAfiliado().getId());
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmHospitalizacion");
            PrimeFaces.current().executeScript("PF('dialogoHospitalizacion').show()");
        }
        procesoFinal();
    }

    public void inicializarTablaHospitalizacion(Integer numeroDocumento) {
        lazyHospitalizacion = new LazyDataModel<AucHospitalizacion>() {

            private List<AucHospitalizacion> hospitalizacion;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(2).getCantidadRegistros();
            }

            @Override
            public List<AucHospitalizacion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(2).setPrimerRegistro(primerRegistro);
                getParamConsulta(2).setRegistrosPagina(registrosPagina);
                getParamConsulta(2).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                HashMap<String, Object> filtrosHash = CompatibilidadPF.ConvertirFiltroMetaToHash(filtros);
                //filtrosHash.put("id", numeroDocumento);
                getParamConsulta(2).setFiltros(filtrosHash);
                getParamConsulta(2).setParametroConsulta1(numeroDocumento);
                refrescarHospitalizacion();
                hospitalizacion = getListaHospitalizacion();
                setRowCount(getParamConsulta(2).getCantidadRegistros());
                return hospitalizacion;
            }

            @Override
            public String getRowKey(AucHospitalizacion afiliadoHospitalizacion) {
                return afiliadoHospitalizacion.getId().toString();
            }

            @Override
            public AucHospitalizacion getRowData(String afiliadoHospitalizacionId) {
                Integer id = Integer.valueOf(afiliadoHospitalizacionId);
                for (AucHospitalizacion afiliadoHospitalizacion : hospitalizacion) {
                    if (id.equals(afiliadoHospitalizacion.getId())) {
                        return afiliadoHospitalizacion;
                    }
                }
                return null;
            }

        };
    }

    public void clearViewScopedBeans() {
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("gestionAfiliadosPEBean");
    }

    public void consultarDiagnostico() {
        /*
        PrimeFaces.current().executeScript("PF('frmDiagnosticoBusqueda:tablaRegistrosDiagnoticos').clearFilters()");
        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmDiagnosticoBusqueda:tablaRegistrosDiagnoticos");
        dataTable.reset();
        PrimeFaces.current().resetInputs("frmDiagnosticoBusqueda:tablaRegistrosDiagnoticos");*/
        //PrimeFaces.current().ajax().update("frmDiagnosticoBusqueda:tablaRegistrosDiagnoticos");
        PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').show()");
    }

    public void cerrarDialogoDiagnostico() {
        boolean agregar = true, sexoAplica = true;
        for (PeAfiliadoDiagnostico diag : getObjeto().getPeAfiliadoDiagnosticoList()) {
            if (Integer.parseInt(diag.getMaDiagnosticosId()) == getDiagnosticosBean().getObjeto().getId()) {
                agregar = false;
                addMensaje("El diagnostico " + getDiagnosticosBean().getObjeto().getNombre() + " ya fue agregado.");
            }
        }
        if (getDiagnosticosBean().getObjeto().getSexoAplica() != PeConstantes.SEXO_APLICA_AMBOS && !PeConstantes.getCodigoSexoAplicaDiagnostico(getDiagnosticosBean().getObjeto().getSexoAplica()).equalsIgnoreCase(objeto.getAsegAfiliado().getMaeGeneroCodigo())) {
            addError("El género del diagnostico " + getDiagnosticosBean().getObjeto().getNombre() + " no corresponde al género del afiliado.");
            agregar = false;
        }
        if (agregar && sexoAplica) {
            PeAfiliadoDiagnostico diagnostico = new PeAfiliadoDiagnostico();
            diagnostico.setId(contadorIdDiagnosticos);
            diagnostico.setMaDiagnosticosId(getDiagnosticosBean().getObjeto().getId().toString());
            diagnostico.setMaDiagnosticosCodigo(getDiagnosticosBean().getObjeto().getCodigo());
            diagnostico.setMaDiagnosticosValor(getDiagnosticosBean().getObjeto().getNombre());
            diagnostico.setPeAfiliadosProgramasId(new PeAfiliadosPrograma());
            diagnostico.getPeAfiliadosProgramasId().setMaDiagnosticoPrincipal(new MaDiagnostico());
            if (getObjeto().getPeAfiliadoDiagnosticoList().isEmpty()) {
                diagnostico.setPrincipal(true);
            }
            
            getObjeto().getPeAfiliadoDiagnosticoList().add(diagnostico);
            getObjeto().setObjetoDiagnostico(diagnostico);
            for (PeAfiliadoDiagnostico afiliadoDiagnostico : getObjeto().getPeAfiliadoDiagnosticoList()) {

                // Validamos que afiliadoDiagnostico y su estructura no sea null
                if (afiliadoDiagnostico != null) {

                    MaDiagnostico diagBean = getDiagnosticosBean().getObjeto();

                    if (diagBean != null) {
                        afiliadoDiagnostico.getPeAfiliadosProgramasId().getMaDiagnosticoPrincipal().setMaCacCodigo(
                                diagBean.getMaCacCodigo() != null ? diagBean.getMaCacCodigo() : ""
                        );
                        afiliadoDiagnostico.getPeAfiliadosProgramasId().getMaDiagnosticoPrincipal().setMaCacId(
                                diagBean.getMaCacId() != null ? diagBean.getMaCacId() : null
                        );
                        afiliadoDiagnostico.getPeAfiliadosProgramasId().getMaDiagnosticoPrincipal().setMaCacValor(
                                diagBean.getMaCacValor() != null ? diagBean.getMaCacValor() : ""
                        );
                    } else {
                        afiliadoDiagnostico.getPeAfiliadosProgramasId().getMaDiagnosticoPrincipal().setMaCacCodigo("");
                        afiliadoDiagnostico.getPeAfiliadosProgramasId().getMaDiagnosticoPrincipal().setMaCacId(null);
                        afiliadoDiagnostico.getPeAfiliadosProgramasId().getMaDiagnosticoPrincipal().setMaCacValor("");
                    }
                }
            }
            contadorIdDiagnosticos--;
            PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').hide()");
            PrimeFaces.current().ajax().update("frmCrear:pDiagnosticoCrear");
            PrimeFaces.current().ajax().update("frmCrear:tablaDiagnosticosCrear");
            PrimeFaces.current().ajax().update("frmEditar:pDiagnosticoCrear");
            PrimeFaces.current().ajax().update("frmEditar:tablaDiagnosticosCrear");
            PrimeFaces.current().ajax().update("frmCambioPrograma:tablaDiagnosticosCrear");
        } else {
            generarMensajes();
        }
        getDiagnosticosBean().setObjeto(new MaDiagnostico());
    }

    public void cambiarPrincipalDiagnostico(int idDiagnostico) {
        for (PeAfiliadoDiagnostico diagnostico : getObjeto().getPeAfiliadoDiagnosticoList()) {
            if (Integer.parseInt(diagnostico.getMaDiagnosticosId()) != idDiagnostico) {
                diagnostico.setPrincipal(false);
            } else {
                diagnostico.setPrincipal(true);
            }
        }
    }

    public void borrarDiagnostico(int id) {
        List<PeAfiliadoDiagnostico> nuevaLista = new ArrayList();
        boolean tiene_pricipal = false;
        if (getObjeto().getPeAfiliadoDiagnosticoList().size() > 1) {
            getObjeto().getPeAfiliadoDiagnosticoList().stream().filter(diagnostico -> (Integer.parseInt(diagnostico.getMaDiagnosticosId()) != id)).forEachOrdered(diagnostico -> {
                nuevaLista.add(diagnostico);
            });

            getObjeto().getPeAfiliadoDiagnosticoList().stream().filter(diagnostico -> (Integer.parseInt(diagnostico.getMaDiagnosticosId()) == id)).forEachOrdered(diagnostico -> {
                getObjeto().getPeAfiliadoDiagnosticoAuxList().add(diagnostico);
            });

            for (PeAfiliadoDiagnostico diagnostico : nuevaLista) {
                if (diagnostico.getPrincipal()) {
                    tiene_pricipal = true;
                }
            }
            if (!tiene_pricipal && nuevaLista.size() > 0) {
                nuevaLista.get(0).setPrincipal(true);
            }
            getObjeto().setPeAfiliadoDiagnosticoList(nuevaLista);
            PrimeFaces.current().ajax().update("frmCrear:pDiagnosticoCrear");
            PrimeFaces.current().ajax().update("frmCrear:tablaDiagnosticosCrear");
            PrimeFaces.current().ajax().update("frmEditar:pDiagnosticoCrear");
            PrimeFaces.current().ajax().update("frmEditar:tablaDiagnosticosCrear");

        } else {
            addError("Debe existir al menos un diagnostico");
            generarMensajes();
        }
    }

    /*public boolean validarOpcionAgregardiagnostico(){
        return this.getObjeto().getPeAfiliadoDiagnosticoList().size() < 3 ? true : false;
    }*/
    public String obtenerNombreFuenteOrigen(int id) {
        return PeConstantes.obtenerNombreFuenteOrigen(id);
    }

    /**
     * Función que se llama desde Editar Afiliado - para borrar un contacto
     * Existente
     *
     * @param id
     */
    public void borrarContacto(Integer id) {
        for (int i = 0; i < listaContactosPrograma.size(); i++) {
            PeTelefono agrup = listaContactosPrograma.get(i);
            if (agrup.getId() == id) {
                listaContactosBorrar.add(agrup);
                listaContactosPrograma.remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmAfiliado:tablaContactosCrearPro");
        PrimeFaces.current().ajax().update("frmEditarAfiliado:tablaContactos");
    }

    /**
     * Función usada desde Crear Afiliados con el objetivo de eliminar un
     * contacto de la lista
     *
     * @param id
     */
    public void retirarContacto(Integer id) {
        for (int i = 0; i < listaContactosPrograma.size(); i++) {
            PeTelefono agrup = listaContactosPrograma.get(i);
            if (agrup.getId() != null && agrup.getId() == id) {
                listaContactosPrograma.remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmEditarAfiliado:tablaContactos");
        PrimeFaces.current().ajax().update("frmAfiliado:tablaContactosCrearPro");
    }

    /**
     * Función llamada desde Crear Afiliado y desde Editar Afiliado, para
     * adicionar un nuevo contacto
     */
    public void adicionarContacto() {
        contacto = new PeTelefono();
        PrimeFaces.current().resetInputs("frmCrearContacto");
        PrimeFaces.current().ajax().update("frmCrearContacto");
        PrimeFaces.current().executeScript("PF('dialogoCrearContacto').show()");
    }

    public void guardarContacto() {
        boolean guardar = true;
        String mensaje = "";
        //obtenemos los valores del maestro
        try {
            Maestro tipo = hashTiposContacto.get(contacto.getMaeTipoContactoId());
            if (tipo != null) {
                switch (Integer.parseInt(tipo.getValor())) {
                    case PeConstantes.PE_TELEFONO_TIPO_MOVIL:
                        contacto.setMaeTipoContactoValor(PeTelefono.PE_TELEFONO_TIPO_MOVIL);
                        contacto.setTipo(PeConstantes.PE_TELEFONO_TIPO_MOVIL);
                        if (contacto.getNumero().length() != 10) {
                            addError("Si el Tipo seleccionado es " + tipo.getNombre() + " el Número de Contacto debe ser de 10 dígitos.");
                            guardar = false;
                        }
                        //2022-06-03 jyperez adicionamos validación
                        mensaje = validarRangoTelefonoMovil(contacto.getNumero());
                        if (!mensaje.isEmpty()) {
                            addError(mensaje);
                            guardar = false;
                        }
                        break;
                    case PeConstantes.PE_TELEFONO_TIPO_FIJO:
                        contacto.setMaeTipoContactoValor(PeTelefono.PE_TELEFONO_TIPO_FIJO);
                        contacto.setTipo(PeConstantes.PE_TELEFONO_TIPO_FIJO);
                        if (contacto.getNumero().length() != 10) {
                            addError("Si el Tipo seleccionado es " + tipo.getNombre() + " el Número de Contacto debe ser de 10 dígitos.");
                            guardar = false;
                        }
                        //2022-06-03 jyperez adicionamos validación
                        mensaje = validarTelefonosFijosNoPermitidos(contacto.getNumero());
                        if (!mensaje.isEmpty()) {
                            addError(mensaje);
                            guardar = false;
                        }
                        break;
                }
                contacto.setMaeTipoContactoCodigo(tipo.getValor());
            } else {
                guardar = false;
                addError("No se encontró el valor maestro del Tipo seleccionado.");
            }
        } catch (Exception e) {
            addError("No se encontró el valor maestro del Tipo seleccionado.");
            contacto.setMaeTipoContactoCodigo("");
            contacto.setMaeTipoContactoValor("");
            guardar = false;
        }

        for (PeTelefono item : listaContactosPrograma) {
            if (item.getNumero().equalsIgnoreCase(contacto.getNumero())) {
                guardar = false;
                addError("El número ya se encuentra en la lista de contactos.");
            }
        }

        if (guardar) {
            contacto.setId(contadorIdContacto);
            contacto.setNuevo(true);
            contacto.setAsegAfiliadosId(new AsegAfiliado(getObjeto().getAsegAfiliado().getId()));
            listaContactosPrograma.add(contacto);
            contadorIdContacto--;
            //PrimeFaces.current().ajax().update("frmCrear:tablaContactos");
            PrimeFaces.current().ajax().update("frmAfiliado:tablaContactosCrearPro");
            PrimeFaces.current().ajax().update("frmEditarAfiliado:tablaContactos");
            PrimeFaces.current().executeScript("PF('dialogoCrearContacto').hide()");
        } else {
            generarMensajes();
        }
    }

    private String validarRangoTelefonoMovil(String movil) {
        String mensaje = "";
        int prefijo = 0;
        //2020-07-27 jyperez se controla excepción debido a que hay datos erróneos ingresados por carga masiva - INC 261
        try {
            if (movil != null && !movil.equals("") && !movil.isBlank()) {
                if (movil.length() >= 3) {
                    prefijo = Integer.valueOf(movil.substring(0, 3));
                    if (prefijo >= 300 && prefijo <= 350) {
                        mensaje = "";
                    } else {
                        mensaje = "El teléfono móvil no inicia entre el rango permitido de valores (300 - 350) ";
                    }

                } else {
                    mensaje = "El teléfono móvil no inicia entre el rango permitido de valores (300 - 350) ";
                }

            }
        } catch (Exception e) {
            mensaje = "El teléfono móvil no contiene un valor numérico.";
        }
        return mensaje;
    }

    /**
     * Se valida que no se ingresen los valores no permitidos ( numeros
     * repetitivos) para el campo teléfono fijo.
     *
     * @param objeto
     * @return
     */
    private String validarTelefonosFijosNoPermitidos(String fijo) {
        String mensaje = "";
        if (fijo != null && !fijo.equals("") && !fijo.isBlank()) {
            if (PeConstantes.getNumerosFijosNoPermitidos().contains(fijo)) {
                mensaje = "el telefono fijo no puede contener una secuencia de números repetitivos, lo cual lo hace inválido.";
            }
        }
        return mensaje;
    }

    public String obtenerDepartamento(int id) {
        try {
            int idPadre = getHashUbicacion().get(id).getUbicacionPadre().getId();
            return getHashUbicacion().get(idPadre).getNombre();
        } catch (Exception e) {
            return PeConstantes.TEXTO_VACIO;
        }
    }

    public String obtenerMunicipio(int id) {
        try {
            return getHashUbicacion().get(id).getNombre();
        } catch (Exception e) {
            return PeConstantes.TEXTO_VACIO;
        }
    }

    public void mostrarMensaje(String mensaje) {
        setDescripcionGenerica(mensaje);
        PrimeFaces.current().resetInputs("frmObservacion");
        PrimeFaces.current().ajax().update("frmObservacion");
        PrimeFaces.current().executeScript("PF('dlgMsg').show();");
    }

    public String validarHospitalizado(AucHospitalizacion aucHospitalizacion) {
        if (aucHospitalizacion == null) {
            return "No";
        }
        return "Si";
    }

    public void mostrarAfiliadoGeneral() {
        PrimeFaces.current().resetInputs("frmAfiliadoGeneral");
        PrimeFaces.current().ajax().update("frmAfiliadoGeneral");
        PrimeFaces.current().executeScript("PF('dlgInfoAfiliado').show();");
    }

    public String obtenerMunicipioDepartamento(int id) {
        try {
            String municipio = obtenerMunicipio(id);
            String departamento = obtenerDepartamento(id);
            return municipio + " - " + departamento;
        } catch (Exception e) {
            return PeConstantes.TEXTO_VACIO;
        }
    }

    public void validarTipoPaciente() {
        try {
            if (objeto.getFechaInicioPrograma() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                String strDiaActual = sdf.format(new Date());
                String strDiaInicio = sdf.format(objeto.getFechaInicioPrograma());
                Long dia = ChronoUnit.DAYS.between(LocalDate.parse(strDiaInicio), LocalDate.parse(strDiaActual));
                //System.err.println("Dias diferencia : " + dia);
                if (dia < 90) {
                    objeto.setTipoPaciente(PeConstantes.PE_TIPO_PACIENTE_NUEVO);
                    return;
                }
                if (dia > 90) {
                    objeto.setTipoPaciente(PeConstantes.PE_TIPO_PACIENTE_PREVALENTE);
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTipoPaciente(Integer tipoPaciente) {
        return PeConstantes.getTipoAfiliado(tipoPaciente);
    }

    private void validarSexoAplicaPrograma() {
        if (this.getObjeto().getAsegAfiliado().getId() == null || this.getObjeto().getPePrograma().getId() == null) {
            return;
        }
        if (this.getObjeto().getPePrograma().getSexoAplica() == PeConstantes.APLICA_SEXO_AMBOS) {
            return;
        }
        if (!PeConstantes.getCodigoSexoAplica(this.getObjeto().getPePrograma().getSexoAplica()).equalsIgnoreCase(this.getObjeto().getAsegAfiliado().getMaeGeneroCodigo())) {
            setMensajeConfirma("El género del afiliado no corresponde al permitido por el programa, el programa " + getObjeto().getPePrograma().getDescripcionPrograma() + " solo permite género " + PeConstantes.getListaSexoAplicaDescripcion(this.getObjeto().getPePrograma().getSexoAplica()));
            PrimeFaces.current().ajax().update("frmConfirmaPAA");
            PrimeFaces.current().executeScript("PF('dialogoProgAfilActivo').show()");
        }
    }

    public String getSexoAplicaPrograma(Integer sexoAplica) {
        return PeConstantes.getListaSexoAplicaDescripcion(sexoAplica);
    }

    public String getFuenteOrigenGestion(Integer id) {
        return PeConstantes.getFuenteOrigenGestionStr(id);
    }

    public String calcularEdadEnDiasMesesAnios(Date fecha) {
        SimpleDateFormat formato5 = new SimpleDateFormat("dd/MM/yyyy");
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaNacimiento = formato5.format(fecha);
            Period periodo = Period.between(LocalDate.parse(fechaNacimiento, fmt), LocalDate.now());
            String texto = periodo.getYears() + " años " + periodo.getMonths() + " meses " + periodo.getDays() + " dias";
            return texto;
        } catch (Exception e) {
            return "";
        }
    }

    public void editarGestion(Integer id) {
        getObjetoGestion().setId(id);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_EDITAR);
        getAfiliadosPEServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmEditGestiones");
            PrimeFaces.current().executeScript("PF('dialogoEditarGestion').show()");
        }
        procesoFinal();
    }

    public void modificarGestion() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_MODIFICAR);
        getAfiliadosPEServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmGestionAfiliado");
            PrimeFaces.current().executeScript("PF('dialogoEditarGestion').hide()");
        }
        procesoFinal();
    }

    public void observacionBorrarGestion(Integer id) {
        getObjetoGestion().setId(id);
        PrimeFaces.current().executeScript("PF('confEliGestion').show()");
        PrimeFaces.current().resetInputs("frmEliminaGestion");
        PrimeFaces.current().ajax().update("frmEliminaGestion");
    }

    public void borrarGestion() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_BORRAR);
        if (getObjetoGestion().getBorradoObservacion() == null || getObjetoGestion().getBorradoObservacion().isEmpty()) {
            this.addMensaje("Debe ingresar el valor para el campo observación.");
        } else {
            getAfiliadosPEServicio().Accion(this);
            PrimeFaces.current().ajax().update("frmGestionAfiliado");
            PrimeFaces.current().executeScript("PF('confEliGestion').hide()");
        }
        procesoFinal();
    }

    public void auditoriaGestion(PeGestion obj) {
        super.getAuditoria(obj.getAuditoriaStrHTML());
    }

    public boolean puedeGestionar(PeGestion gestion) {
        if (gestion.getUsuarioCrea().contains(this.getConexion().getUsuario().getUsuario())) {
            return true;
        }
        return false;
    }

    public String getEstadoSivigilaStr(Integer id) {
        if (id == null || hashEstadosSivigila == null) {
            return PeConstantes.TEXTO_VACIO;
        }
        return hashEstadosSivigila.get(id).getNombre();
    }

    public void validarEstadoSivigila() {
        boolean result = true;
        if (this.objeto.getNotificadoSivigila() != null) {
            if (this.objeto.getNotificadoSivigila() == PeConstantes.NOTIFICACION_SIVIGILA_SI) {
                result = false;
            }
        }
        if (result) {
            this.objeto.setEstadoSivigila(null);
        }
        setActivoEstadoSivigila(result);
        validarCausaDecarte();
    }

    public void validarCausaDecarte() {
        boolean result = true;
        if (this.objeto.getEstadoSivigila() != null) {
            if (this.objeto.getEstadoSivigila() == PeConstantes.ESTADO_CASO_DESCARTADO) {
                result = false;
            }
        }
        if (result) {
            this.objeto.setMaeCausaDescarteId(null);
        }
        setActivoCausaDescarte(result);
    }

    public String getEstadoDiagnosticoStr() {
        if (Objects.isNull(this.getObjeto().getEstadoDiagnostico())) {
            return "";
        }
        return PeConstantes.estadoDiagnosticoStr(this.getObjeto().getEstadoDiagnostico());
    }

    public String getSentenciasRecursiva(Integer id) {
        String sentencia = "";
        if (id != null) {
            for (Maestro maestro : listaSentencias) {
                if (maestro.getId().intValue() == id) {
                    sentencia = maestro.getNombre();
                    break;
                }
            }

        }
        return sentencia;
    }

    public String getValorEnteroStr(PeVariableValor valor) {
        String valorPredeterminado = (valor.getEntero() != null) ? String.valueOf(valor.getEntero()) : "";

        if (valor.getValidaciones() == null) {
            return valorPredeterminado;
        }

        return valor.getValidaciones().stream()
                .filter(v -> v.getTipo().equals(2))
                .filter(v -> valor.getEntero() == ((Number) v.getValor()).intValue())
                .map(PeValidacion::getNombre)
                .findFirst()
                .orElse(valorPredeterminado);

    }

    public String getValorDecimalStr(PeVariableValor valor) {
        String valorPredeterminado = (valor.getDecimal() != null) ? String.valueOf(valor.getDecimal()) : "";

        if (valor.getValidaciones() == null) {
            return valorPredeterminado;
        }

        return valor.getValidaciones().stream()
                .filter(v -> v.getTipo().equals(2))
                .filter(v -> valor.getDecimal().doubleValue() == (double) v.getValor())
                .map(PeValidacion::getNombre)
                .findFirst()
                .orElse(valorPredeterminado);

    }

    public String getValorFechaStr(PeVariableValor valor) {
        String valorPredeterminado = (valor.getFecha() != null) ? String.valueOf(valor.getFecha()) : "";

        if (valor.getValidaciones() == null) {
            return valorPredeterminado;
        }

        return valor.getValidaciones().stream()
                .filter(v -> v.getTipo().equals(2))
                .filter(v -> String.valueOf(valor.getFecha()).equals(v.getValor().toString()))
                .map(PeValidacion::getNombre)
                .findFirst()
                .orElse(valorPredeterminado);

    }

    public String getTituloFechaStr(PeVariableValor valor) {
        String valorPredeterminado = "";

        if (valor.getValidaciones() == null) {
            return valorPredeterminado;
        }

        Optional<PeValidacion> existeValidacion = valor.getValidaciones().stream()
                .filter(v -> v.getTipo().equals(2))
                .filter(v -> String.valueOf(valor.getFecha()).equals(v.getValor().toString()))
                .findFirst();

        return existeValidacion.isPresent() ? String.valueOf(valor.getFecha()) : valorPredeterminado;
    }

    /*
    * Determina si una variable es apta para ser editada (disabled true o false)
     */
    public boolean validarEdicionVariable(PeVariableValor valor) {

        if (!valor.isActiva()) {//si la variable no esta activa -> disabled = true
            return true;
        }

        if (valor.getTipo().equals(PeTipoVariable.TEXTO.getId()) || valor.getTipo().equals(PeTipoVariable.TEXTO_LARGO.getId())) {//si la variable es texto o texto largo 
            if (!valor.isEditable() && valor.getValorObject() != null) {
                return !valor.getValor().isBlank();//si no es nula se verifica que no sea blank
            }
        }

        return !valor.isEditable() && valor.getValorObject() != null;//si la variable no es editable y no es nula
    }

    public void crearCambioPrograma(int id) {
        this.getObjeto().setId(id);
        this.setIdProgramaDestino(null);
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(Url.ACCION_CREAR);
        this.getAfiliadosPEServicio().Accion(this);
        this.procesoFinal();
    }

    private boolean validacionSeleccionProgramaDestino() {
        if (this.getIdProgramaDestino() == null) {
            addError("Debe seleccionar el programa destino");
            this.procesoFinal();
            return false;
        }
        return true;
    }

    public void seleccionarProgramaDestino() {
        if (!this.validacionSeleccionProgramaDestino()) {
            return;
        }

        PePrograma programaEncontrado = this.getListaProgramasAgrupador().stream()
                .filter(p -> p.getId().equals(this.getIdProgramaDestino()))
                .findFirst().orElse(null);

        if (programaEncontrado != null) {
            this.getProgramaTraslado().setProgramaDestino(programaEncontrado);
        }
    }

    public String getRegistrosAfiliados(int id) {
        return PeConstantes.getRegistrosAfiliados(id);
    }

    public String getSexoAplicaDescripcion(Integer id) {
        return PeConstantes.getListaSexoAplicaDescripcion(id);
    }

    public String getCantidadRegistrosStr(Integer id) {
        return PeConstantes.getCantidadRegistrosStr(id);
    }

    public void guardarCambioPrograma() {
        if (!this.validacionSeleccionProgramaDestino()) {
            return;
        }

        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(Url.ACCION_GUARDAR);
        this.getAfiliadosPEServicio().Accion(this);
        this.procesoFinal();
    }

    public void refrescarTrasladoProgramas() {
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(Url.ACCION_LISTAR);
        getAfiliadosPEServicio().Accion(this);
    }

    public void inicializarTablaTrasladoProgramas() {
        lazyTrasladoPrograma = new LazyDataModel<PeProgramaTraslado>() {

            private List<PeProgramaTraslado> traslados;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(3).getCantidadRegistros();
            }

            @Override
            public List<PeProgramaTraslado> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(3).setPrimerRegistro(primerRegistro);
                getParamConsulta(3).setRegistrosPagina(registrosPagina);
                getParamConsulta(3).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));

                HashMap<String, Object> filtrosHash = CompatibilidadPF.ConvertirFiltroMetaToHash(filtros);
                //filtros por defecto
//                filtrosHash.put("afiliadoPrograma", getObjeto().getId());
                filtrosHash.put("afiliadoAseg", getObjeto().getAsegAfiliado().getId());

                getParamConsulta(3).setFiltros(filtrosHash);
                refrescarTrasladoProgramas();
                traslados = getListaTrasladoProgramas();
                setRowCount(getParamConsulta(3).getCantidadRegistros());
                return traslados;
            }

            @Override
            public String getRowKey(PeProgramaTraslado trasladoPrograma) {
                return trasladoPrograma.getId().toString();
            }

            @Override
            public PeProgramaTraslado getRowData(String idTrasladoPrograma) {
                Integer id = Integer.valueOf(idTrasladoPrograma);
                for (PeProgramaTraslado afiliadoHospitalizacion : traslados) {
                    if (id.equals(afiliadoHospitalizacion.getId())) {
                        return afiliadoHospitalizacion;
                    }
                }
                return null;
            }
        };
    }

    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_LISTAR:
                crearLog(getObjetoGestion().toString());
                PrimeFaces.current().ajax().update("frmGestion");
                break;
            case Url.ACCION_GUARDAR:
                break;
            case Url.ACCION_MODIFICAR:
            case Url.ACCION_BORRAR:
                PrimeFaces.current().ajax().update("frmGestion");
                break;
            case Url.ACCION_VER:
                this.inicializarTablaTrasladoProgramas();
                break;
            case Url.ACCION_CREAR:
                break;
            case Url.ACCION_EDITAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_ADICIONAL_1:
                switch (getDoAccion()) {
                    case Url.ACCION_LISTAR:
                        crearLog("Listar adjuntos", getObjectoAdjunto().toString());
                        break;
                    case Url.ACCION_GUARDAR:
                        crearLog("Crear adjuntos", getObjectoAdjunto().toString());
                        break;
                }
                break;
            case Url.ACCION_ADICIONAL_2:
                switch (getDoAccion()) {
                    case Url.ACCION_LISTAR:
                        crearLog("Listar gestiones", getObjetoGestion().toString());
                        break;
                    case Url.ACCION_CREAR:
                        crearLog("Crear gestion", getObjetoGestion().toString());
                        break;
                    case Url.ACCION_GUARDAR:
                        crearLog("Guardar gestion", getObjetoGestion().toString());
                        break;
                    case Url.ACCION_BORRAR:
                        crearLog("Borrar gestion", getObjetoGestion().toString());
                        break;
                }
                break;
            case Url.ACCION_ADICIONAL_3:
                switch (getDoAccion()) {
                    case Url.ACCION_LISTAR:
                        PrimeFaces.current().ajax().update("frmHospitalizacion");
                        break;
                }
            case Url.ACCION_ADICIONAL_5:
                switch (getDoAccion()) {
                    case Url.ACCION_CREAR:
                        PrimeFaces.current().ajax().update("frmVerAfiliadoCambioPrograma");
                        PrimeFaces.current().ajax().update("frmCambioPrograma");
                        PrimeFaces.current().resetInputs("frmCambioPrograma");
                        PrimeFaces.current().executeScript("PF('dialogoCambioPrograma').show()");
                        break;
                    case Url.ACCION_GUARDAR:
                        if (!super.isError()) {
                            PrimeFaces.current().ajax().update("frmGestion");
                            PrimeFaces.current().executeScript("PF('dialogoCambioPrograma').hide()");
                        }
                        break;
                }
        }
        generarMensajes();
    }
}
