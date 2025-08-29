package com.saviasaludeps.savia.web.juridico.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjCampo;
import com.saviasaludeps.savia.dominio.juridico.CntjDocumento;
import com.saviasaludeps.savia.dominio.juridico.CntjEstado;
import com.saviasaludeps.savia.dominio.juridico.CntjEstadoEjecucion;
import com.saviasaludeps.savia.dominio.juridico.CntjEstadoProcesoDocumento;
import com.saviasaludeps.savia.dominio.juridico.CntjExpediente;
import com.saviasaludeps.savia.dominio.juridico.CntjExpedienteResponsable;
import com.saviasaludeps.savia.dominio.juridico.CntjProceso;
import com.saviasaludeps.savia.dominio.juridico.CntjTercero;
import com.saviasaludeps.savia.web.juridico.servicio.ExpedienteServicioIface;
import com.saviasaludeps.savia.web.juridico.utilidades.CntjConstantes;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author idbohorquez
 */
@ManagedBean
@ViewScoped
public class ExpedienteBean extends Url {

    private ExpedienteServicioIface cntjExpedienteServicio;

    private LazyDataModel<CntjExpediente> lazyExpediente;
    private LazyDataModel<CntjDocumento> lazyDocumentos;
    private LazyDataModel<CntjTercero> lazyTerceros;
    private LazyDataModel<CntjExpedienteResponsable> lazyResponsables;
    private LazyDataModel<Usuario> lazyUsuarios;
    private LazyDataModel<CntjExpediente> lazyEventoContractual;

    private CntjExpediente objeto;
    private List<CntjProceso> listaProceso;
    private LazyDataModel<CntjCampo> lazyCampos;
    private List<CntjCampo> registrosCampos;
    private List<CntjExpediente> registros;
    private List<CntjDocumento> listaDocumentos;
    private List<CntjEstadoEjecucion> listaEstadoEjecucion;
    private CntjEstadoEjecucion objeToEjecucion;
    private List<CntjEstado> listaEstadoSiguiente;
    private HashMap<Integer, CntjEstado> hashListaEstadoSiguiente;
    private List<CntjCampo> listaCampos;
    private HashMap<String, CntjCampo> hashListaCampos;
    private CntjCampo objetoCampo;
    private List<CntjCampo> listaCamposTransicion;
    private List<Maestro> listaMaestroCampo;
    private HashMap<Integer, Maestro> hashListaMaestroCampo;
    private List<CntjDocumento> listaAdjuntos;
    private List<CntjEstadoProcesoDocumento> listaEstadoDocumento;
    private CntjDocumento adjunto;
    private CntjTercero tercero;
    private List<CntjTercero> registrosTerceros;
    private List<Maestro> listaTipoDocumentoTercero;
    private List<Maestro> listaTipoTercero;
    private List<CntjExpediente> registrosContractuales;
    //variable de datos para utilizar en insersion de contrato
    private HashMap<Integer, Maestro> hashlistaModalidad;
    private HashMap<Integer, Maestro> hashlistaClaseContrato;
    private HashMap<Integer, Maestro> hashlistaRegimen;
    private HashMap<Integer, Maestro> hashlistaEstadoContrato;
    private HashMap<Integer, Maestro> hashlistaGarantiaContrato;
    private List<CntjExpedienteResponsable> registrosResponsables;
    private CntjExpedienteResponsable objetoResponsable;
    private List<Maestro> listaRol;
    private List<Usuario> listaUsuario;
    private Date fechaMinimaReponsable;
    private boolean modificarFechaGestion;
    private CntjDocumento objDocumento;
    private String contenidoPdf;
    private StreamedContent zipParaDescarga;
    
    public ExpedienteBean() {
        this.objeto = new CntjExpediente();
        this.objeToEjecucion = new CntjEstadoEjecucion();
        this.objetoCampo = new CntjCampo();
        this.objetoResponsable = new CntjExpedienteResponsable();
        this.objDocumento = new CntjDocumento();
        
        Modulo _mod = super.validarModulo(Modulo.ID_CNTJ_EXPEDIENTES);
        if (_mod == null) {
            super.redireccionarHome();
        } else {
            super.setModulo(_mod);
            super.addListaParamConsultas(new ParamConsulta());
            super.addListaParamConsultas(new ParamConsulta());
            super.addListaParamConsultas(new ParamConsulta());
            super.addListaParamConsultas(new ParamConsulta());
            super.addListaParamConsultas(new ParamConsulta());
            super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyExpediente = new LazyDataModel<CntjExpediente>() {

                private List<CntjExpediente> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CntjExpediente> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    HashMap<String, Object> filtrosHash = CompatibilidadPF.ConvertirFiltroMetaToHash(filtros);
                    getParamConsulta().setFiltros(filtrosHash);
                    refrescar();
                    lista = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(CntjExpediente objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public CntjExpediente getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CntjExpediente objeto : lista) {
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
    public void postConstruct() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        getCntjExpedienteServicio().cargaInicial(this);
    }

    // <editor-fold defaultstate="collapsed" desc="Gettes and Setters">

    public StreamedContent getZipParaDescarga() {
        return zipParaDescarga;
    }

    public void setZipParaDescarga(StreamedContent zipParaDescarga) {
        this.zipParaDescarga = zipParaDescarga;
    }    
    
    public LazyDataModel<CntjExpediente> getLazyEventoContractual() {
        return lazyEventoContractual;
    }

    public void setLazyEventoContractual(LazyDataModel<CntjExpediente> lazyEventoContractual) {
        this.lazyEventoContractual = lazyEventoContractual;
    }

    public List<CntjExpediente> getRegistrosContractuales() {
        return registrosContractuales;
    }

    public void setRegistrosContractuales(List<CntjExpediente> registrosContractuales) {
        this.registrosContractuales = registrosContractuales;
    }

    public CntjDocumento getObjDocumento() {
        return objDocumento;
    }

    public void setObjDocumento(CntjDocumento objDocumento) {
        this.objDocumento = objDocumento;
    }

    public String getContenidoPdf() {
        return contenidoPdf;
    }

    public void setContenidoPdf(String contenidoPdf) {
        this.contenidoPdf = contenidoPdf;
    }

    
    public boolean isModificarFechaGestion() {
        return modificarFechaGestion;
    }

    public void setModificarFechaGestion(boolean modificarFechaGestion) {
        this.modificarFechaGestion = modificarFechaGestion;
    }
    
    public String getRolStr(Integer rol){
        return CntjConstantes.getRolStr(rol);
    }
    
    public Date getFechaMinimaReponsable() {
        return fechaMinimaReponsable;
    }
    
    public void setFechaMinimaReponsable(Date fechaMinimaReponsable) {
        this.fechaMinimaReponsable = fechaMinimaReponsable;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }
    
    public LazyDataModel<Usuario> getLazyUsuarios() {
        return lazyUsuarios;
    }

    public void setLazyUsuarios(LazyDataModel<Usuario> lazyUsuarios) {
        this.lazyUsuarios = lazyUsuarios;
    }    
    
    public List<Maestro> getListaRol() {
        return listaRol;
    }

    public void setListaRol(List<Maestro> listaRol) {
        this.listaRol = listaRol;
    }
    
    public CntjExpedienteResponsable getObjetoResponsable() {
        return objetoResponsable;
    }

    public void setObjetoResponsable(CntjExpedienteResponsable objetoResponsable) {
        this.objetoResponsable = objetoResponsable;
    }
    
    public List<CntjExpedienteResponsable> getRegistrosResponsables() {
        return registrosResponsables;
    }

    public void setRegistrosResponsables(List<CntjExpedienteResponsable> registrosResponsables) {
        this.registrosResponsables = registrosResponsables;
    }
    
    public LazyDataModel<CntjExpedienteResponsable> getLazyResponsables() {
        return lazyResponsables;
    }

    public void setLazyResponsables(LazyDataModel<CntjExpedienteResponsable> lazyResponsables) {
        this.lazyResponsables = lazyResponsables;
    }
    
    public HashMap<Integer, Maestro> getHashlistaModalidad() {
        return hashlistaModalidad;
    }

    public void setHashlistaModalidad(HashMap<Integer, Maestro> hashlistaModalidad) {
        this.hashlistaModalidad = hashlistaModalidad;
    }

    public HashMap<Integer, Maestro> getHashlistaClaseContrato() {
        return hashlistaClaseContrato;
    }

    public void setHashlistaClaseContrato(HashMap<Integer, Maestro> hashlistaClaseContrato) {
        this.hashlistaClaseContrato = hashlistaClaseContrato;
    }

    public HashMap<Integer, Maestro> getHashlistaRegimen() {
        return hashlistaRegimen;
    }

    public void setHashlistaRegimen(HashMap<Integer, Maestro> hashlistaRegimen) {
        this.hashlistaRegimen = hashlistaRegimen;
    }

    public HashMap<Integer, Maestro> getHashlistaEstadoContrato() {
        return hashlistaEstadoContrato;
    }

    public void setHashlistaEstadoContrato(HashMap<Integer, Maestro> hashlistaEstadoContrato) {
        this.hashlistaEstadoContrato = hashlistaEstadoContrato;
    }

    public HashMap<Integer, Maestro> getHashlistaGarantiaContrato() {
        return hashlistaGarantiaContrato;
    }

    public void setHashlistaGarantiaContrato(HashMap<Integer, Maestro> hashlistaGarantiaContrato) {
        this.hashlistaGarantiaContrato = hashlistaGarantiaContrato;
    }

    public HashMap<Integer, Maestro> getHashListaMaestroCampo() {
        return hashListaMaestroCampo;
    }

    public void setHashListaMaestroCampo(HashMap<Integer, Maestro> hashListaMaestroCampo) {
        this.hashListaMaestroCampo = hashListaMaestroCampo;
    }
    
    public String getTipoTerceroStr(Integer id) {
        return CntjConstantes.getTipoTercero(id);
    }
    
    public List<Maestro> getListaTipoDocumentoTercero() {
        return listaTipoDocumentoTercero;
    }

    public void setListaTipoDocumentoTercero(List<Maestro> listaTipoDocumentoTercero) {
        this.listaTipoDocumentoTercero = listaTipoDocumentoTercero;
    }

    public List<Maestro> getListaTipoTercero() {
        return listaTipoTercero;
    }

    public void setListaTipoTercero(List<Maestro> listaTipoTercero) {
        this.listaTipoTercero = listaTipoTercero;
    }
    
    public List<CntjTercero> getRegistrosTerceros() {
        return registrosTerceros;
    }

    public void setRegistrosTerceros(List<CntjTercero> registrosTerceros) {
        this.registrosTerceros = registrosTerceros;
    }
    
    public LazyDataModel<CntjTercero> getLazyTerceros() {
        return lazyTerceros;
    }

    public void setLazyTerceros(LazyDataModel<CntjTercero> lazyTerceros) {
        this.lazyTerceros = lazyTerceros;
    }

    public CntjTercero getTercero() {
        return tercero;
    }

    public void setTercero(CntjTercero tercero) {
        this.tercero = tercero;
    }
    
    public HashMap<Integer, CntjEstado> getHashListaEstadoSiguiente() {
        return hashListaEstadoSiguiente;
    }

    public void setHashListaEstadoSiguiente(HashMap<Integer, CntjEstado> hashListaEstadoSiguiente) {
        this.hashListaEstadoSiguiente = hashListaEstadoSiguiente;
    }
        
    public List<CntjEstadoProcesoDocumento> getListaEstadoDocumento() {
        return listaEstadoDocumento;
    }

    public void setListaEstadoDocumento(List<CntjEstadoProcesoDocumento> listaEstadoDocumento) {
        this.listaEstadoDocumento = listaEstadoDocumento;
    }

    public List<CntjDocumento> getListaAdjuntos() {
        return listaAdjuntos;
    }

    public void setListaAdjuntos(List<CntjDocumento> listaAdjuntos) {
        this.listaAdjuntos = listaAdjuntos;
    }

    public CntjDocumento getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(CntjDocumento adjunto) {
        this.adjunto = adjunto;
    }

    public String getTipoDocumento(Integer id) {
        return CntjConstantes.getTipoDocumento(id);
    }

    public List<CntjDocumento> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaDocumentos(List<CntjDocumento> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    public LazyDataModel<CntjDocumento> getLazyDocumentos() {
        return lazyDocumentos;
    }

    public void setLazyDocumentos(LazyDataModel<CntjDocumento> lazyDocumentos) {
        this.lazyDocumentos = lazyDocumentos;
    }

    public List<Maestro> getListaMaestroCampo() {
        return listaMaestroCampo;
    }

    public void setListaMaestroCampo(List<Maestro> listaMaestroCampo) {
        this.listaMaestroCampo = listaMaestroCampo;
    }

    public HashMap<String, CntjCampo> getHashListaCampos() {
        return hashListaCampos;
    }

    public void setHashListaCampos(HashMap<String, CntjCampo> hashListaCampos) {
        this.hashListaCampos = hashListaCampos;
    }

    public List<CntjCampo> getListaCamposTransicion() {
        return listaCamposTransicion;
    }

    public void setListaCamposTransicion(List<CntjCampo> listaCamposTransicion) {
        this.listaCamposTransicion = listaCamposTransicion;
    }

    public CntjCampo getObjetoCampo() {
        return objetoCampo;
    }

    public void setObjetoCampo(CntjCampo objetoCampo) {
        this.objetoCampo = objetoCampo;
    }

    public List<CntjCampo> getListaCampos() {
        return listaCampos;
    }

    public void setListaCampos(List<CntjCampo> listaCampos) {
        this.listaCampos = listaCampos;
    }

    public Date getFechaActual() {
        return new Date();
    }

    public List<CntjEstado> getListaEstadoSiguiente() {
        return listaEstadoSiguiente;
    }

    public void setListaEstadoSiguiente(List<CntjEstado> listaEstadoSiguiente) {
        this.listaEstadoSiguiente = listaEstadoSiguiente;
    }

    public CntjEstadoEjecucion getObjeToEjecucion() {
        return objeToEjecucion;
    }

    public void setObjeToEjecucion(CntjEstadoEjecucion objeToEjecucion) {
        this.objeToEjecucion = objeToEjecucion;
    }

    public List<CntjEstadoEjecucion> getListaEstadoEjecucion() {
        return listaEstadoEjecucion;
    }

    public void setListaEstadoEjecucion(List<CntjEstadoEjecucion> listaEstadoEjecucion) {
        this.listaEstadoEjecucion = listaEstadoEjecucion;
    }

    public List<CntjExpediente> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CntjExpediente> registros) {
        this.registros = registros;
    }

    public LazyDataModel<CntjExpediente> getLazyExpediente() {
        return lazyExpediente;
    }

    public void setLazyExpediente(LazyDataModel<CntjExpediente> lazyExpediente) {
        this.lazyExpediente = lazyExpediente;
    }

    public LazyDataModel<CntjCampo> getLazyCampos() {
        return lazyCampos;
    }

    public void setLazyCampos(LazyDataModel<CntjCampo> lazyCampos) {
        this.lazyCampos = lazyCampos;
    }

    public List<CntjCampo> getRegistrosCampos() {
        return registrosCampos;
    }

    public void setRegistrosCampos(List<CntjCampo> registrosCampos) {
        this.registrosCampos = registrosCampos;
    }

    public List<CntjProceso> getListaProceso() {
        return listaProceso;
    }

    public void setListaProceso(List<CntjProceso> listaProceso) {
        this.listaProceso = listaProceso;
    }

    public CntjExpediente getObjeto() {
        return objeto;
    }

    public void setObjeto(CntjExpediente objeto) {
        this.objeto = objeto;
    }

    public ExpedienteServicioIface getCntjExpedienteServicio() {
        return cntjExpedienteServicio;
    }

    public void setCntjExpedienteServicio(ExpedienteServicioIface cntjExpedienteServicio) {
        this.cntjExpedienteServicio = cntjExpedienteServicio;
    }

    // </editor-fold>
    
    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCntjExpedienteServicio().Accion(this);
    }

    public void refrescarDocumentos() {
        super.setAccion(Url.ACCION_ADICIONAL_4);
        super.setDoAccion(Url.ACCION_LISTAR);
        getCntjExpedienteServicio().Accion(this);
    }
    
    public void refrescarTerceros() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        getCntjExpedienteServicio().Accion(this);
    }
    
    public void refrescarResponsables() {
        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(Url.ACCION_LISTAR);
        getCntjExpedienteServicio().Accion(this);
    } 
    
    public void refrescarUsuarios() {
        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(Url.ACCION_ADICIONAL_1);
        getCntjExpedienteServicio().Accion(this);
    }
    
    public void refrescarEventoConstractual() {
        super.setAccion(ACCION_ADICIONAL_9);
        super.setDoAccion(Url.ACCION_LISTAR);
        getCntjExpedienteServicio().Accion(this);
    }
    
    public void listarEventoConstractual() {
        super.setAccion(Url.ACCION_ADICIONAL_9);
        super.setDoAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void listarDocumentos() {
        super.setAccion(Url.ACCION_ADICIONAL_4);
        super.setDoAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void ver(int id) {
        this.objeto.setId(id);
        super.setAccion(ACCION_VER);
        getCntjExpedienteServicio().Accion(this);
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getCntjExpedienteServicio().Accion(this);
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getCntjExpedienteServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(Integer idComite) {
        this.objeto.setId(idComite);
        super.setAccion(ACCION_EDITAR);
        getCntjExpedienteServicio().Accion(this);
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getCntjExpedienteServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void trazabilidad(Integer id) {
        this.objeto.setId(id);
        super.setAccion(ACCION_ADICIONAL_7);
        super.setDoAccion(ACCION_LISTAR);
        getCntjExpedienteServicio().Accion(this);
        procesoFinal();
    }

    public void gestionar(Integer id) {
        this.objeto.setId(id);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_VER);
        getCntjExpedienteServicio().Accion(this);
        validarEstadoSiguiente();
        procesoFinal();
    }

    public void crearCampo() {
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_CREAR);
        getCntjExpedienteServicio().Accion(this);
        procesoFinal();
        validarTipoCampo();
    }

    public void agregarAdjunto() {
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_CREAR);
        getCntjExpedienteServicio().Accion(this);
        procesoFinal();
    }

    public void adjuntarArchivo(FileUploadEvent event) throws IOException {
        super.setAccion(CntjConstantes.ACCION_NA);
        UploadedFile archivoAdjunto = event.getFile();
        try {
            if (getAdjunto().getEstadoDocumento().getProcesodocumentoId().getId() == null) {
                this.addError("Debe seleccionar el valor del campo documento");
            } else {
                boolean existe = getListaAdjuntos().stream()
                        .anyMatch(elemento -> elemento.getEstadoDocumento().getProcesodocumentoId().getId() == getAdjunto().getEstadoDocumento().getProcesodocumentoId().getId());
                if (existe) {
                    this.addError("Ya existe un adjunto agregado para el documento seleccionado.");
                    PrimeFaces.current().ajax().update("frmCrearAdjunto");
                } else {
                    Optional<CntjEstadoProcesoDocumento> documento = getListaEstadoDocumento().stream()
                            .filter(item -> item.getProcesodocumentoId().getId().equals(getAdjunto().getEstadoDocumento().getProcesodocumentoId().getId()))
                            .findFirst();
                    
                      
                    if(getListaAdjuntos().isEmpty()){
                        getAdjunto().setId(-1);
                    }else{
                        Optional<CntjDocumento> maxItem = getListaAdjuntos().stream().max((a, b) -> Integer.compare(a.getId(), b.getId())); 
                        int idadjunto = (maxItem.get().getId() + 1) * -1;
                        getAdjunto().setId(idadjunto);
                    }
                    getAdjunto().setNombre(documento.get().getProcesodocumentoId().getNombre());
                    getAdjunto().setTipo(documento.get().getProcesodocumentoId().getTipoDocumento().shortValue());
                    getAdjunto().setEstadoDocumento(documento.get());
                    getAdjunto().setAdjuntoStream(archivoAdjunto.getInputStream());
                    String nombreAdjunto = FilenameUtils.getName(archivoAdjunto.getFileName());
                    getAdjunto().setDocumentoNombre(nombreAdjunto);
                    getListaAdjuntos().add(getAdjunto());                    
                    PrimeFaces.current().ajax().update("frmGestionar:tablaAdjuntos");
                    PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').hide();");
                }
            }

        } catch (IOException ex) {
            this.addError(ex.getMessage());
        }
        procesoFinal();
    }
    
    public void eliminarAdjunto(int id){
        getListaAdjuntos().removeIf(item -> item.getId().equals(id));
    }

    public void avanzar() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_GUARDAR);
        getCntjExpedienteServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoGestionar').hide();");
        }
        procesoFinal();
    }

    public void validarEstadoSiguiente() {
        if (getObjeToEjecucion().getCntjEstadoId().getId() != null) {
            CntjEstado estado = getHashListaEstadoSiguiente().get(getObjeToEjecucion().getCntjEstadoId().getId());
            getObjeToEjecucion().setCntjEstadoId(new CntjEstado(estado.getId()));
            getObjeToEjecucion().setEstadoSiguiente(estado.getNombre());
            if(estado.isModificaFecha()){
                setModificarFechaGestion(false);
            }else{
                setModificarFechaGestion(true);
            }            
        } else if (!getListaEstadoSiguiente().isEmpty()) {
            CntjEstado estado = getHashListaEstadoSiguiente().get(getListaEstadoSiguiente().get(0).getId());
            getObjeToEjecucion().setCntjEstadoId(new CntjEstado(estado.getId()));
            getObjeToEjecucion().setEstadoSiguiente(estado.getNombre());
            if(estado.isModificaFecha()){
                setModificarFechaGestion(false);
            }else{
                setModificarFechaGestion(true);
            } 
        }
    }

    public void validarTipoCampo() {
        if (getObjetoCampo().getNombre() != null) {
            Optional<CntjCampo> campo = getListaCampos().stream().filter(it -> it.getNombre().equals(getObjetoCampo().getNombre()))
                    .findFirst();
            getObjetoCampo().setTipoDato(campo.get().getTipoDato());
            getObjetoCampo().setMaestroTipo(campo.get().getMaestroTipo());
            if (campo.get().getTipoDato().equals(CntjConstantes.TIPO_DATO_LISTA) && campo.get().isAplicaMaestro()) {
                super.setAccion(ACCION_ADICIONAL_3);
                super.setDoAccion(ACCION_ADICIONAL_1);
                getCntjExpedienteServicio().Accion(this);
                procesoFinal();
            }else if(campo.get().getTipoDato().equals(CntjConstantes.TIPO_DATO_LISTA) && !campo.get().isAplicaMaestro()){
                JSONArray arrayJson = new JSONArray(campo.get().getValoresLista());
                setListaMaestroCampo(new ArrayList<>());
                setHashListaMaestroCampo(new HashMap<>());
                for(int index=0; index < arrayJson.length(); index++){
                    JSONObject obj = arrayJson.getJSONObject(index);
                    Maestro maestro = new Maestro();
                    maestro.setId(obj.getInt(CntjConstantes.VALOR));
                    maestro.setNombre(obj.getString(CntjConstantes.OPCION));
                    getListaMaestroCampo().add(maestro);
                }
                setHashListaMaestroCampo(CntjConstantes.obtenerHashMaestro(getListaMaestroCampo()));
            }
            
            if(campo.get().getTipoDato().equals(CntjConstantes.TIPO_DATO_TEXTO_LARGO)){
                PrimeFaces.current().executeScript("document.getElementById('modalAddCampo').classList.add('fullscreen-dialog');");
            }else{
                PrimeFaces.current().executeScript("document.getElementById('modalAddCampo').classList.remove('fullscreen-dialog');");
            }
        } else {
            getObjetoCampo().setTipoDato(null);
            PrimeFaces.current().executeScript("document.getElementById('modalAddCampo').classList.remove('fullscreen-dialog');");
        }
    }

    public void agregarCampo() {
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_GUARDAR);
        getCntjExpedienteServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoAddCampo').hide();");
        }
        procesoFinal();
    }

    public void eliminarCampo(Integer id) {
        getListaCamposTransicion().removeIf(f -> f.getId().equals(id));
    }

    public void verdocumentos(Integer id) {
        this.objeto.setId(id);
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_LISTAR);
        cargarLazyDocumentos();
        PrimeFaces.current().executeScript("PF('dialogoDocumentos').show()");
        procesoFinal();
    }

    private void cargarLazyDocumentos() {
        lazyDocumentos = new LazyDataModel<CntjDocumento>() {

            private List<CntjDocumento> listaDocumentos;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(0).getCantidadRegistros();
            }

            @Override
            public List<CntjDocumento> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(0).setPrimerRegistro(primerRegistro);
                getParamConsulta(0).setRegistrosPagina(registrosPagina);
                getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                HashMap<String, Object> filtrosHash = CompatibilidadPF.ConvertirFiltroMetaToHash(filtros);
                filtrosHash.put(CntjConstantes.ID_EXPEDIENTE, getObjeto().getId());
                getParamConsulta(0).setFiltros(filtrosHash);
                refrescarDocumentos();
                listaDocumentos = getListaDocumentos();
                setRowCount(getParamConsulta(0).getCantidadRegistros());
                return listaDocumentos;
            }

            @Override
            public String getRowKey(CntjDocumento objeto) {
                return objeto.getId().toString();
            }

            @Override
            public CntjDocumento getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (CntjDocumento objeto : listaDocumentos) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };
    }

    public void descargarDocumento(CntjDocumento documento) {
        super.setAccion(CntjConstantes.ACCION_NA);
        if (documento.getDocumentoExiste()) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

            //String filePath = facesContext.getExternalContext().getRequestParameterMap().get("filePath");
            StringBuilder rutaCompleta = new StringBuilder(documento.getDocumentoRuta()).append(documento.getDocumentoArchivo());
            File file = new File(rutaCompleta.toString());
            if (!file.exists()) {
                super.addError("No se encontro el archivo que desea descargar.");
                response.reset();
                response.setStatus(HttpServletResponse.SC_NO_CONTENT); // Responde con 204 No Content
                facesContext.responseComplete();
            } else {

                response.reset(); // Resetea la respuesta para evitar conflictos
                response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
                response.setContentLength((int) file.length());
                response.setContentType(FacesContext.getCurrentInstance().getExternalContext().getMimeType(file.getName()));

                facesContext.responseComplete(); // Completa la respuesta antes de enviar el archivo

                try (FileInputStream fileInputStream = new FileInputStream(file); OutputStream responseOutputStream = response.getOutputStream()) {

                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        responseOutputStream.write(buffer, 0, bytesRead);
                    }

                    responseOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else {
            this.addError("El archivo no existe en la ruta de descarga.");
        }
        procesoFinal();

    }
    
    public void listarTerceros() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_ADICIONAL_1);
        cargarLazyTerceros();
        PrimeFaces.current().executeScript("PF('dialogoListaTercero').show()");
        procesoFinal();
    }

    private void cargarLazyTerceros() {
        lazyTerceros = new LazyDataModel<CntjTercero>() {
            private List<CntjTercero> listaTerceros;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(1).getCantidadRegistros();
            }

            @Override
            public List<CntjTercero> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(1).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(1).setPrimerRegistro(primerRegistro);
                getParamConsulta(1).setRegistrosPagina(registrosPagina);
                getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                HashMap<String, Object> filtrosHash = CompatibilidadPF.ConvertirFiltroMetaToHash(filtros);
                //filtrosHash.put(CntjConstantes.ACTIVO_STR, CntjConstantes.ACTIVO.toString());
                getParamConsulta(1).setFiltros(filtrosHash);
                refrescarTerceros();
                listaTerceros = getRegistrosTerceros();
                setRowCount(getParamConsulta(1).getCantidadRegistros());
                return listaTerceros;
            }

            @Override
            public String getRowKey(CntjTercero objeto) {
                return objeto.getId().toString();
            }

            @Override
            public CntjTercero getRowData(String usuarioId) {
                Integer id = Integer.valueOf(usuarioId);
                for (CntjTercero user : listaTerceros) {
                    if (id.equals(user.getId())) {
                        return user;
                    }
                }
                return null;
            }
        };
    }
    
    public void onRowSelectTercero(SelectEvent event) {
        CntjTercero tercero = (CntjTercero) event.getObject();
        setTercero(tercero);
        PrimeFaces.current().ajax().update("frmAddCampo");
        PrimeFaces.current().executeScript("PF('dialogoListaTercero').hide()");        
    }

    public void verResponsables(Integer id) {
        this.objeto.setId(id);
        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(ACCION_LISTAR);
        cargarLazyResponsables();
        PrimeFaces.current().executeScript("PF('dialogoListaResponsables').show()");
        procesoFinal();
    }
    
    private void cargarLazyResponsables() {
        lazyResponsables = new LazyDataModel<CntjExpedienteResponsable>() {
            private List<CntjExpedienteResponsable> listaResponsables;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(2).getCantidadRegistros();
            }

            @Override
            public List<CntjExpedienteResponsable> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(2).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(2).setPrimerRegistro(primerRegistro);
                getParamConsulta(2).setRegistrosPagina(registrosPagina);
                getParamConsulta(2).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                HashMap<String, Object> filtrosHash = CompatibilidadPF.ConvertirFiltroMetaToHash(filtros);
                filtrosHash.put(CntjConstantes.CAMPO_EXPEDIENTE, getObjeto().getId());
                getParamConsulta(2).setFiltros(filtrosHash);
                refrescarResponsables();
                listaResponsables = getRegistrosResponsables();
                setRowCount(getParamConsulta(2).getCantidadRegistros());
                return listaResponsables;
            }

            @Override
            public String getRowKey(CntjExpedienteResponsable objeto) {
                return objeto.getId().toString();
            }

            @Override
            public CntjExpedienteResponsable getRowData(String usuarioId) {
                Integer id = Integer.valueOf(usuarioId);
                for (CntjExpedienteResponsable item : listaResponsables) {
                    if (id.equals(item.getId())) {
                        return item;
                    }
                }
                return null;
            }
        };
    }
    
    public void agregarResponsable(){
        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(ACCION_CREAR);
        getCntjExpedienteServicio().Accion(this);
        procesoFinal();
    }
    
    public void cargarUsuarios() {
        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(ACCION_ADICIONAL_1);
        cargarLazyUsuario();
        PrimeFaces.current().executeScript("PF('dialogoUsuariosLista').show()");
        procesoFinal();
    }
    
    private void cargarLazyUsuario() {
        lazyUsuarios = new LazyDataModel<Usuario>() {
            private List<Usuario> listaUsuario;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(3).getCantidadRegistros();
            }

            @Override
            public List<Usuario> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(3).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(3).setPrimerRegistro(primerRegistro);
                getParamConsulta(3).setRegistrosPagina(registrosPagina);
                getParamConsulta(3).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(3).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarUsuarios();
                listaUsuario = getListaUsuario();
                setRowCount(getParamConsulta(3).getCantidadRegistros());
                return listaUsuario;
            }

            @Override
            public String getRowKey(Usuario usuario) {
                return usuario.getId().toString();
            }

            @Override
            public Usuario getRowData(String usuarioId) {
                Integer id = Integer.valueOf(usuarioId);
                for (Usuario user : listaUsuario) {
                    if (id.equals(user.getId())) {
                        return user;
                    }
                }
                return null;
            }
        };
    }
    
    public void onRowSelectUsuario(SelectEvent event) {
        Usuario usuario = (Usuario) event.getObject();
        getObjetoResponsable().setUsuarioId(usuario);
        PrimeFaces.current().ajax().update("frmCrearResponsable:cusuario");        
        PrimeFaces.current().ajax().update("frmCrearResponsable:hcusuario");        
        PrimeFaces.current().executeScript("PF('dialogoUsuariosLista').hide()");
        
    }
    
    public void guardarResponsable(){
        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(ACCION_GUARDAR);
        getCntjExpedienteServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoAgregarReponsable').hide();");
        }
        procesoFinal();
    }
    
    public void eliminrSupervisor(Integer id){
        getObjetoResponsable().setId(id);
        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(ACCION_BORRAR);
        getCntjExpedienteServicio().Accion(this);
        procesoFinal();
    }
    
    public void validarUltimoResponsable(){
        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(ACCION_ADICIONAL_2);
        getCntjExpedienteServicio().Accion(this);
        procesoFinal();
    }
    
    public void visualizardocumentos(Integer id){
        getObjDocumento().setId(id);
        super.setAccion(ACCION_ADICIONAL_8);
        getCntjExpedienteServicio().Accion(this);
        generarDocumentoPdfStream();
        procesoFinal();
    }
    
    public void generarDocumentoPdfStream() {
        try {
            File file = new File(getObjDocumento().getDocumentoRuta() + getObjDocumento().getDocumentoArchivo());
            byte[] fileContent = new byte[(int) file.length()];
            try (FileInputStream fis = new FileInputStream(file)) {
                fis.read(fileContent);
            }
            setContenidoPdf(Base64.getEncoder().encodeToString(fileContent));
        } catch (IOException e) {
            e.printStackTrace();
            setContenidoPdf(null);
        }
    }
    
     public void verEventosContractuales(Integer id) {
        this.objeto.setId(id);
        super.setAccion(ACCION_ADICIONAL_9);
        super.setDoAccion(ACCION_LISTAR);
        cargarLazyEventosContractuales();
        PrimeFaces.current().executeScript("PF('dialogoEventoContractual').show()");
        procesoFinal();
    }
     
    private void cargarLazyEventosContractuales() {
        lazyEventoContractual = new LazyDataModel<CntjExpediente>() {
            private List<CntjExpediente> listaContractuales;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(4).getCantidadRegistros();
            }

            @Override
            public List<CntjExpediente> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(4).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(4).setPrimerRegistro(primerRegistro);
                getParamConsulta(4).setRegistrosPagina(registrosPagina);
                getParamConsulta(4).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                HashMap<String, Object> filtrosHash = CompatibilidadPF.ConvertirFiltroMetaToHash(filtros);
                filtrosHash.put(CntjConstantes.CAMPO_EXPEDIENTE_ID, getObjeto().getId());
                getParamConsulta(4).setFiltros(filtrosHash);
                refrescarEventoConstractual();
                listaContractuales = getRegistrosContractuales();
                setRowCount(getParamConsulta(4).getCantidadRegistros());
                return listaContractuales;
            }

            @Override
            public String getRowKey(CntjExpediente objeto) {
                return objeto.getId().toString();
            }

            @Override
            public CntjExpediente getRowData(String usuarioId) {
                Integer id = Integer.valueOf(usuarioId);
                for (CntjExpediente item : listaContractuales) {
                    if (id.equals(item.getId())) {
                        return item;
                    }
                }
                return null;
            }
        };
    }
    
    public void descargarDocumentosExpediente(){
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_ADICIONAL_1);
        getCntjExpedienteServicio().Accion(this);
        procesoFinal();
    }
    
    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_LISTAR:
                PrimeFaces.current().ajax().update("frmGestion");
                break;
            case Url.ACCION_GUARDAR:
            case Url.ACCION_MODIFICAR:
            case Url.ACCION_BORRAR:
                crearLog(getObjeto().toString());
                PrimeFaces.current().ajax().update("frmGestion");
                break;
            case Url.ACCION_VER:
                PrimeFaces.current().ajax().update("frmVer");
                PrimeFaces.current().executeScript("PF('dialogoVer').show()");
                break;
            case Url.ACCION_CREAR:
                PrimeFaces.current().resetInputs("frmCrear");
                PrimeFaces.current().ajax().update("frmCrear");
                PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
                break;
            case Url.ACCION_EDITAR:
                PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
                PrimeFaces.current().ajax().update("frmEditar");
                crearLog(getObjeto().toString());
                break;            
            case Url.ACCION_ADICIONAL_2:
                switch (getDoAccion()) {
                    case Url.ACCION_VER:
                        PrimeFaces.current().executeScript("PF('dialogoGestionar').show()");
                        PrimeFaces.current().ajax().update("frmGestionar");
                        PrimeFaces.current().ajax().update("frmGestion");
                        break;
                    case Url.ACCION_GUARDAR:
                        PrimeFaces.current().ajax().update("frmGestion");
                        break;
                    case Url.ACCION_ADICIONAL_1:
                            PrimeFaces.current().ajax().update("frmTerceroLista");
                            break;
                }
                break;
            case Url.ACCION_ADICIONAL_3:
                switch (getDoAccion()) {
                    case Url.ACCION_CREAR:
                        PrimeFaces.current().ajax().update("frmAddCampo");
                        PrimeFaces.current().executeScript("PF('dialogoAddCampo').show()");
                        break;
                    case Url.ACCION_GUARDAR:
                        PrimeFaces.current().ajax().update("frmGestionar:tablaCampos");
                        break;
                }
                break;
            case Url.ACCION_ADICIONAL_4:
                switch (getDoAccion()) {
                    case Url.ACCION_LISTAR:
                        PrimeFaces.current().ajax().update("frmDocumentos");
                        break;
                }
                break;
            case Url.ACCION_ADICIONAL_5:
                switch (getDoAccion()) {
                    case Url.ACCION_CREAR:
                        PrimeFaces.current().ajax().update("frmCrearAdjunto");
                        PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').show()");
                        break;
                }
                break;
            case Url.ACCION_ADICIONAL_6:
                switch (getDoAccion()) {
                    case Url.ACCION_LISTAR:
                        PrimeFaces.current().ajax().update("frmResponsableLista");
                        break;
                    case Url.ACCION_CREAR:
                        PrimeFaces.current().ajax().update("frmCrearResponsable");
                        PrimeFaces.current().executeScript("PF('dialogoAgregarReponsable').show()");
                        break;
                    case Url.ACCION_GUARDAR:
                        PrimeFaces.current().ajax().update("frmResponsableLista");
                        break;
                    case Url.ACCION_ADICIONAL_1:
                        PrimeFaces.current().ajax().update("frmUsuariosLista");
                        break;
                }
                break;
            case Url.ACCION_ADICIONAL_7:
                switch (getDoAccion()) {
                    case Url.ACCION_LISTAR:
                        PrimeFaces.current().executeScript("PF('dialogoTrazabilidad').show()");
                        PrimeFaces.current().ajax().update("frmTrazabilidad");
                        break;                    
                }
                break;
            case Url.ACCION_ADICIONAL_8:
                 PrimeFaces.current().ajax().update("frmVisualizaDoc");
                 PrimeFaces.current().executeScript("PF('dialogoVisualizarDoc').show()");
                break;
            case Url.ACCION_ADICIONAL_9:
                switch (getDoAccion()) {
                    case Url.ACCION_LISTAR:
                        PrimeFaces.current().ajax().update("frmEventoContractual");
                        break;
                }
                break;

        }
        generarMensajes();
    }

}
