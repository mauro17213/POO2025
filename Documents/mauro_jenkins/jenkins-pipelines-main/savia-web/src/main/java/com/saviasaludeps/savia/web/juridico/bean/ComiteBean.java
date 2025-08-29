package com.saviasaludeps.savia.web.juridico.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjComite;
import com.saviasaludeps.savia.dominio.juridico.CntjComiteAsistente;
import com.saviasaludeps.savia.dominio.juridico.CntjDocumento;
import com.saviasaludeps.savia.dominio.juridico.CntjExpediente;
import com.saviasaludeps.savia.dominio.juridico.CntjLinea;
import com.saviasaludeps.savia.dominio.juridico.CntjLineaAdjunto;
import com.saviasaludeps.savia.web.juridico.servicio.ComiteServicioIface;
import com.saviasaludeps.savia.web.juridico.utilidades.CntjConstantes;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_CREAR;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_GUARDAR;
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
import java.util.Objects;
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
import org.primefaces.model.file.UploadedFile;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author idbohorquez
 */
@ManagedBean
@ViewScoped
public class ComiteBean extends Url {

    private ComiteServicioIface cntjComiteServicio;

    private CntjComite objeto;
    private CntjComiteAsistente objetoComiteAsistente;
    private Date fechaHoy;
    private CntjLinea objetoLinea;
    private Integer tipoSelectUsuario;
    private CntjLineaAdjunto lineaAdjunto;
    private UploadedFile archivoAdjunto;

    private LazyDataModel<CntjComite> lazyComite;
    private LazyDataModel<Usuario> lazyUsuarios;
    private LazyDataModel<CntjLinea> lazyLineas;
    private LazyDataModel<CntjDocumento> lazyDocumentos;

    private List<CntjComite> registros;
    private List<Usuario> listaUsuarios;
    private List<CntjComiteAsistente> listaComiteAsistente;
    private List<Maestro> listaEstadosComite;
    private List<CntjComiteAsistente> listaEliminarComiteAsistente;
    private List<CntjLinea> listaLineas;
    private List<Maestro> listaTipoLinea;
    private List<Maestro> listaTipoArchivo;
    private List<Maestro> listaEstadoLinea;
    private HashMap<Integer,Maestro> hashlistaTipoArchivo;
    private List<CntjLineaAdjunto> listaAdjuntosLinea;
    private List<CntjLineaAdjunto> listaAdjuntosLineaEliminar;
    private Object selectedRowUsuario;
    private List<CntjExpediente> listaExpedienteComite;
    private CntjDocumento objDocumento;
    private String contenidoPdf;
    private List<CntjDocumento> listaDocumentos;

    public ComiteBean() {
        this.objeto = new CntjComite();
        this.objetoComiteAsistente = new CntjComiteAsistente();
        this.objetoLinea = new CntjLinea();
        this.fechaHoy = new Date();
        this.lineaAdjunto = new CntjLineaAdjunto();
        this.objDocumento = new CntjDocumento();
        Modulo _mod = super.validarModulo(Modulo.ID_CNTJ_COMITES);
        if (_mod == null) {
            super.redireccionarHome();
        } else {
            super.setModulo(_mod);
            super.addListaParamConsultas(new ParamConsulta());
            super.addListaParamConsultas(new ParamConsulta());
            super.addListaParamConsultas(new ParamConsulta());
            super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyComite = new LazyDataModel<CntjComite>() {

                private List<CntjComite> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CntjComite> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(CntjComite objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public CntjComite getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CntjComite objeto : lista) {
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
        getCntjComiteServicio().cargaInicial(this);
    }
    
    
    
   
    // <editor-fold defaultstate="collapsed" desc="Gettes and Setters">
    
    public String getTipoDocumento(Integer id) {
        return CntjConstantes.getTipoDocumento(id);
    }
    
     public LazyDataModel<CntjDocumento> getLazyDocumentos() {    
        return lazyDocumentos;
    }

    public void setLazyDocumentos(LazyDataModel<CntjDocumento> lazyDocumentos) {
        this.lazyDocumentos = lazyDocumentos;
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

    public List<CntjDocumento> getListaDocumentos() {
        return listaDocumentos;
    }
    
    public void setListaDocumentos(List<CntjDocumento> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    public List<CntjExpediente> getListaExpedienteComite() {
        return listaExpedienteComite;
    }
    
    public void setListaExpedienteComite(List<CntjExpediente> listaExpedienteComite) {
        this.listaExpedienteComite = listaExpedienteComite;
    }

    public Object getSelectedRowUsuario() {
        return selectedRowUsuario;
    }

    public void setSelectedRowUsuario(Object selectedRowUsuario) {
        this.selectedRowUsuario = selectedRowUsuario;
    }
    
    public boolean isEstadoComiteFinalizado(Integer estado){
        return estado.equals(CntjConstantes.COMITE_FINALIZADO);
    }    

    public int getEstadoComiteEjecucion(){
        return CntjConstantes.COMITE_EN_EJECUCION;
    }    

    public int getEstadoComiteEnReceso(){
        return CntjConstantes.COMITE_EN_RECESO;
    }
    
    public List<Maestro> getListaEstadoLinea() {
        return listaEstadoLinea;
    }

    public void setListaEstadoLinea(List<Maestro> listaEstadoLinea) {
        this.listaEstadoLinea = listaEstadoLinea;
    }
    
    public List<CntjLineaAdjunto> getListaAdjuntosLineaEliminar() {
        return listaAdjuntosLineaEliminar;
    }

    public void setListaAdjuntosLineaEliminar(List<CntjLineaAdjunto> listaAdjuntosLineaEliminar) {
        this.listaAdjuntosLineaEliminar = listaAdjuntosLineaEliminar;
    }

    
    
    public List<Maestro> getListaTipoArchivo() {
        return listaTipoArchivo;
    }

    public void setListaTipoArchivo(List<Maestro> listaTipoArchivo) {
        this.listaTipoArchivo = listaTipoArchivo;
    }

    public HashMap<Integer, Maestro> getHashlistaTipoArchivo() {
        return hashlistaTipoArchivo;
    }

    public void setHashlistaTipoArchivo(HashMap<Integer, Maestro> hashlistaTipoArchivo) {
        this.hashlistaTipoArchivo = hashlistaTipoArchivo;
    }

    public List<CntjLineaAdjunto> getListaAdjuntosLinea() {
        return listaAdjuntosLinea;
    }

    public void setListaAdjuntosLinea(List<CntjLineaAdjunto> listaAdjuntosLinea) {
        this.listaAdjuntosLinea = listaAdjuntosLinea;
    }

    
    public UploadedFile getArchivoAdjunto() {
        return archivoAdjunto;
    }

    public void setArchivoAdjunto(UploadedFile archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
    }
    
    public CntjLineaAdjunto getLineaAdjunto() {
        return lineaAdjunto;
    }

    public void setLineaAdjunto(CntjLineaAdjunto lineaAdjunto) {
        this.lineaAdjunto = lineaAdjunto;
    }

    public Integer getTipoSelectUsuario() {
        return tipoSelectUsuario;
    }

    public void setTipoSelectUsuario(Integer tipoSelectUsuario) {
        this.tipoSelectUsuario = tipoSelectUsuario;
    }

    public List<Maestro> getListaTipoLinea() {
        return listaTipoLinea;
    }

    public void setListaTipoLinea(List<Maestro> listaTipoLinea) {
        this.listaTipoLinea = listaTipoLinea;
    }

    public CntjLinea getObjetoLinea() {
        return objetoLinea;
    }

    public void setObjetoLinea(CntjLinea objetoLinea) {
        this.objetoLinea = objetoLinea;
    }

    public LazyDataModel<CntjLinea> getLazyLineas() {
        return lazyLineas;
    }

    public void setLazyLineas(LazyDataModel<CntjLinea> lazyLineas) {
        this.lazyLineas = lazyLineas;
    }

    public List<CntjLinea> getListaLineas() {
        return listaLineas;
    }

    public void setListaLineas(List<CntjLinea> listaLineas) {
        this.listaLineas = listaLineas;
    }

    public List<CntjComiteAsistente> getListaEliminarComiteAsistente() {
        return listaEliminarComiteAsistente;
    }

    public void setListaEliminarComiteAsistente(List<CntjComiteAsistente> listaEliminarComiteAsistente) {
        this.listaEliminarComiteAsistente = listaEliminarComiteAsistente;
    }

    public Date getFechaHoy() {
        return fechaHoy;
    }

    public void setFechaHoy(Date fechaHoy) {
        this.fechaHoy = fechaHoy;
    }

    public List<Maestro> getListaEstadosComite() {
        return listaEstadosComite;
    }

    public void setListaEstadosComite(List<Maestro> listaEstadosComite) {
        this.listaEstadosComite = listaEstadosComite;
    }

    public List<CntjComite> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CntjComite> registros) {
        this.registros = registros;
    }

    public LazyDataModel<CntjComite> getLazyComite() {
        return lazyComite;
    }

    public void setLazyComite(LazyDataModel<CntjComite> lazyComite) {
        this.lazyComite = lazyComite;
    }

    public List<CntjComiteAsistente> getListaComiteAsistente() {
        return listaComiteAsistente;
    }

    public void setListaComiteAsistente(List<CntjComiteAsistente> listaComiteAsistente) {
        this.listaComiteAsistente = listaComiteAsistente;
    }

    public CntjComiteAsistente getObjetoComiteAsistente() {
        return objetoComiteAsistente;
    }

    public void setObjetoComiteAsistente(CntjComiteAsistente objetoComiteAsistente) {
        this.objetoComiteAsistente = objetoComiteAsistente;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public LazyDataModel<Usuario> getLazyUsuarios() {
        return lazyUsuarios;
    }

    public void setLazyUsuarios(LazyDataModel<Usuario> lazyUsuarios) {
        this.lazyUsuarios = lazyUsuarios;
    }

    public CntjComite getObjeto() {
        return objeto;
    }

    public void setObjeto(CntjComite objeto) {
        this.objeto = objeto;
    }

    public ComiteServicioIface getCntjComiteServicio() {
        return cntjComiteServicio;
    }

    public void setCntjComiteServicio(ComiteServicioIface cntjComiteServicio) {
        this.cntjComiteServicio = cntjComiteServicio;
    }

    // // </editor-fold>
    
    //Metodos
    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCntjComiteServicio().Accion(this);
    }
    
    public void refrescarDocumentos() {
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_VER);
        getCntjComiteServicio().Accion(this);
    }

    public void refrescarUsuarios() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_LISTAR);
        getCntjComiteServicio().Accion(this);
    }

    public void refrescarLineas() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_LISTAR);
        getCntjComiteServicio().Accion(this);
    }

    public void ver(int id) {
        this.objeto.setId(id);
        super.setAccion(ACCION_VER);
        getCntjComiteServicio().Accion(this);
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getCntjComiteServicio().Accion(this);
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getCntjComiteServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(Integer idComite) {
        this.objeto.setId(idComite);
        super.setAccion(ACCION_EDITAR);
        getCntjComiteServicio().Accion(this);
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        super.setDoAccion(ACCION_MODIFICAR);
        getCntjComiteServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void eliminarLocal(Integer idusuario) {
        this.listaComiteAsistente.removeIf(item -> item.getUsuarioId().getId().equals(idusuario));
    }

    public void eliminar(CntjComiteAsistente asistenteComite) {
        if (asistenteComite.getId().equals(-1)) {
            eliminarLocal(asistenteComite.getUsuarioId().getId());
        } else {
            this.listaEliminarComiteAsistente.add(asistenteComite);
            this.listaComiteAsistente.removeIf(item -> item.getId().equals(asistenteComite.getId()));
        }
        procesoFinal();
    }

    public void cerrarComite(int idcomite) {
        this.objeto.setId(idcomite);
        super.setAccion(ACCION_MODIFICAR);
        super.setDoAccion(ACCION_ADICIONAL_1);
        getCntjComiteServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void abrirComite(CntjComite comite) {
        setObjeto(objeto);
        super.setAccion(ACCION_MODIFICAR);
        super.setDoAccion(ACCION_ADICIONAL_2);
        getCntjComiteServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void listarUsuarios(Integer tipo) {
        setTipoSelectUsuario(tipo);
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_LISTAR);
        this.selectedRowUsuario = null;
        cargarLazyUsuarios();
        PrimeFaces.current().executeScript("PF('dialogoUsuariosLista').show()");
        procesoFinal();
    }

    private void cargarLazyUsuarios() {
        lazyUsuarios = new LazyDataModel<Usuario>() {
            private List<Usuario> listaUsuario = new ArrayList<>();

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(0).getCantidadRegistros();
            }

            @Override
            public List<Usuario> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(0).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(0).setPrimerRegistro(primerRegistro);
                getParamConsulta(0).setRegistrosPagina(registrosPagina);
                getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                HashMap<String, Object> filtrosHash = CompatibilidadPF.ConvertirFiltroMetaToHash(filtros);
                filtrosHash.put(CntjConstantes.ACTIVO_STR, CntjConstantes.ACTIVO.toString());
                getParamConsulta(0).setFiltros(filtrosHash);
                refrescarUsuarios();
                listaUsuario = getListaUsuarios();
                setRowCount(getParamConsulta(0).getCantidadRegistros());
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
        if (getTipoSelectUsuario() == null || getTipoSelectUsuario().equals(0)) {
            boolean existe = this.listaComiteAsistente.stream()
                    .anyMatch(elemento -> Objects.equals(elemento.getUsuarioId().getId(), usuario.getId()));
            if (existe) {
                this.addError("El usuario seleccionado ya fue agregado como asistente.");
            } else {
                this.objetoComiteAsistente = new CntjComiteAsistente(-1);
                this.objetoComiteAsistente.setUsuarioId(usuario);
                this.objetoComiteAsistente.setAprueba(false);
                this.listaComiteAsistente.add(objetoComiteAsistente);
                PrimeFaces.current().ajax().update("frmCrear:tablaAsistentesCt");
                PrimeFaces.current().ajax().update("frmEditar:tablaAsistentesCt");
                PrimeFaces.current().executeScript("PF('dialogoUsuariosLista').hide()");
            }
        } else if (getTipoSelectUsuario().equals(1)) {
            this.getObjetoLinea().setUsuariosId(usuario);
            this.getObjetoLinea().setArea(usuario.getMaeAreaValor());
            PrimeFaces.current().ajax().update("frmCrearLinea:cResponsable");
            PrimeFaces.current().ajax().update("frmEditarLinea:cResponsable");
            PrimeFaces.current().executeScript("PF('dialogoUsuariosLista').hide()");
        }
        procesoFinal();
    }

    public String getEstadoComiteStr(Integer id) {
        return CntjConstantes.getEstadoComiteStr(id);
    }

    public void lineas(CntjComite comite) {
        this.objeto = comite;
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_LISTAR);
        cargarLazyLineas();
        PrimeFaces.current().executeScript("PF('dialogoLinea').show()");
        procesoFinal();
    }

    private void cargarLazyLineas() {
        lazyLineas = new LazyDataModel<CntjLinea>() {
            private List<CntjLinea> listado;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(1).getCantidadRegistros();
            }

            @Override
            public List<CntjLinea> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(1).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(1).setPrimerRegistro(primerRegistro);
                getParamConsulta(1).setRegistrosPagina(registrosPagina);
                getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                HashMap<String, Object> filtrosHash = CompatibilidadPF.ConvertirFiltroMetaToHash(filtros);
                filtrosHash.put(CntjConstantes.ID_COMITE, getObjeto().getId());
                getParamConsulta(1).setFiltros(filtrosHash);
                refrescarLineas();
                listado = getListaLineas();
                setRowCount(getParamConsulta(1).getCantidadRegistros());
                return listado;
            }

            @Override
            public String getRowKey(CntjLinea linea) {
                return linea.getId().toString();
            }

            @Override
            public CntjLinea getRowData(String usuarioId) {
                Integer id = Integer.valueOf(usuarioId);
                for (CntjLinea linea : listado) {
                    if (id.equals(linea.getId())) {
                        return linea;
                    }
                }
                return null;
            }
        };
    }

    public void verLinea(CntjLinea linea) {
        setObjetoLinea(linea);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_VER);
        getCntjComiteServicio().Accion(this);
        procesoFinal();
    }

    public void crearLinea() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_CREAR);
        getCntjComiteServicio().Accion(this);
        procesoFinal();
    }

    public void guardarLinea() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_GUARDAR);
        getCntjComiteServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearLinea').hide();");
        }
        procesoFinal();
    }

    public String getTipoLineaStr(Integer id) {
        return CntjConstantes.getTipoLineaStr(id);
    }

    public String getEstadoLineaStr(Integer id) {
        return CntjConstantes.getEstadoLineaStr(id);
    }

    public void editarLinea(Integer idlinea) {
        setObjetoLinea(new CntjLinea(idlinea));
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_EDITAR);
        getCntjComiteServicio().Accion(this);
        procesoFinal();
    }

    public void modificarLinea() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_MODIFICAR);
        getCntjComiteServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditarLinea').hide();");
        }
        procesoFinal();
    }

    public void crearAdjunto() {
        this.lineaAdjunto = new CntjLineaAdjunto();
        PrimeFaces.current().ajax().update("frmCrearAdjunto");
        PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').show()");
    }

    public void cargarAdjunto(FileUploadEvent event) throws IOException {
        this.archivoAdjunto = event.getFile();
        try {
            if (this.lineaAdjunto.getMaeTipoArchivoId() == null) {
                this.addError("Debe seleccionar el tipo de archivo");
            } else {
                boolean existe = this.listaAdjuntosLinea.stream()
                        .anyMatch(elemento -> elemento.getNombre().equals(FilenameUtils.getName(this.archivoAdjunto.getFileName())));
                if (existe) {
                    this.addError("Ya existe un adjunto con el mismo nombre.");
                } else {
                    Optional<Maestro> maeTipoArchivo = getListaTipoArchivo().stream().filter(it -> it.getId().equals(getLineaAdjunto().getMaeTipoArchivoId())).findFirst();
                    this.lineaAdjunto.setId(-1);
                    this.lineaAdjunto.setAdjuntoStream(archivoAdjunto.getInputStream());
                    String nombreAdjunto = FilenameUtils.getName(this.archivoAdjunto.getFileName());
                    this.lineaAdjunto.setNombre(nombreAdjunto);
                    this.lineaAdjunto.setMaeTipoArchivoValor(maeTipoArchivo.get().getNombre());
                    this.listaAdjuntosLinea.add(lineaAdjunto);
                    this.lineaAdjunto = new CntjLineaAdjunto();
                    PrimeFaces.current().ajax().update("frmCrearLinea:tablaAdjuntoLineas");
                }
                PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').hide();");
            }
            
        } catch (IOException ex) {
            this.addError(ex.getMessage());
        }
        procesoFinal();
    }
    
    public void eliminarAdjunto(CntjLineaAdjunto adjunto){
        if(adjunto.getId().equals(-1)){
            this.listaAdjuntosLinea.removeIf(item -> item.getNombre().equals(adjunto.getNombre()) && item.getAdjuntoStream().equals(adjunto.getAdjuntoStream()));
        }else{
            this.listaAdjuntosLineaEliminar.add(adjunto);
            this.listaAdjuntosLinea.removeIf(item -> item.getNombre().equals(adjunto.getNombre())); 
        }
    }
    
    
    public void descargarAdjuntos(CntjLineaAdjunto adjunto) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        StringBuilder rutaCompleta = new StringBuilder(adjunto.getRuta()).append(adjunto.getArchivo());
        File file = new File(rutaCompleta.toString());
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
    
    public boolean verAccionLineas(Integer idestado){
        if(idestado == null){
            return false;
        }
        if(idestado >= CntjConstantes.COMITE_ABIERTO){
            return true;
        }
        return false;
    }
    
    
    public boolean verAccionAgregarLinea(Integer idestado){
        if(idestado == null){
            return false;
        }
        if(idestado.equals(CntjConstantes.COMITE_ABIERTO)){
            return true;
        }
        return false;
    }
    
    public boolean verAccionEvaluarLinea(CntjLinea linea){
        if(linea == null || linea.getEstado() == null){
            return false;
        }
        if((this.getObjeto().getEstado() >= CntjConstantes.COMITE_CERRADO && this.getObjeto().getEstado() <= CntjConstantes.COMITE_EN_EJECUCION)){
            return true;
        }
        return false;
    }
    
    public boolean verAccionEditarcomite(Integer idestado){
        if(idestado == null){
            return false;
        }
        if(idestado < CntjConstantes.COMITE_FINALIZADO){
            return true;
        }
        return false;
    }
    
    public void evaluacionLinea(CntjLinea linea) {
        setObjetoLinea(linea);
        if (this.getObjeto().getFechaProgramacion().after(new Date())) {
            this.addError("No puede evaluar las lineas, la fecha actual es menor a la fecha de programación del comité.");
        } else {
            super.setAccion(ACCION_ADICIONAL_2);
            super.setDoAccion(ACCION_ADICIONAL_1);
            getCntjComiteServicio().Accion(this);
        }
        procesoFinal();
    }
    
    public void evaluarLinea(){
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_ADICIONAL_2);
        getCntjComiteServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEvaluarLinea').hide();");
        }
        procesoFinal();
    }
    
    public boolean verAccionModificarComite(Integer idestado){
        if(idestado == null){
            return false;
        }
        if(idestado < CntjConstantes.COMITE_EN_EJECUCION){
            return true;
        }
        return false;
    }
    
    public void suspenderComite(CntjComite comite){
        setObjeto(comite);
        super.setAccion(ACCION_MODIFICAR);
        super.setDoAccion(ACCION_ADICIONAL_3);
        getCntjComiteServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }
    
    public void reanudarComite(CntjComite comite){
        setObjeto(comite);
        super.setAccion(ACCION_MODIFICAR);
        super.setDoAccion(ACCION_ADICIONAL_4);
        getCntjComiteServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }
    
    public void finalizarComite(CntjComite comite){
        setObjeto(comite);
        super.setAccion(ACCION_MODIFICAR);
        super.setDoAccion(ACCION_ADICIONAL_5);
        getCntjComiteServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }
    
    public void verdocumentos(Integer id) {
        getObjetoLinea().setId(id);
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_VER);
        cargarLazyDocumentos();
        PrimeFaces.current().executeScript("PF('dialogoDocumentos').show()");
        procesoFinal();
    }
    
    private void cargarLazyDocumentos() {
        lazyDocumentos = new LazyDataModel<CntjDocumento>() {

            private List<CntjDocumento> listaDocumentos;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(2).getCantidadRegistros();
            }

            @Override
            public List<CntjDocumento> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(2).setPrimerRegistro(primerRegistro);
                getParamConsulta(2).setRegistrosPagina(registrosPagina);
                getParamConsulta(2).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                HashMap<String, Object> filtrosHash = CompatibilidadPF.ConvertirFiltroMetaToHash(filtros);
                getParamConsulta(2).setFiltros(filtrosHash);
                refrescarDocumentos();
                listaDocumentos = getListaDocumentos();
                setRowCount(getParamConsulta(2).getCantidadRegistros());
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
    
    public void visualizardocumentos(Integer id) {
        getObjDocumento().setId(id);
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_ADICIONAL_1);
        getCntjComiteServicio().Accion(this);
        generarDocumentoPdfStream();
        procesoFinal();
    }
    
    public void generarDocumentoPdfStream() {
        try {
            File file = new File(getObjDocumento().getDocumentoRuta() + getObjDocumento().getDocumentoArchivo());
            if(!file.exists()){
               super.addError("El sistema no puede encontrar el archivo especificado.");
                setContenidoPdf(null);
                generarMensajes();
                return;
            }
            
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
            case Url.ACCION_ADICIONAL_1:
                switch (getDoAccion()) {
                    case Url.ACCION_ADICIONAL_1:
                        PrimeFaces.current().ajax().update("frmUsuariosLista");
                        break;
                }
                break;
            case Url.ACCION_ADICIONAL_2:
                switch (getDoAccion()) {
                    case Url.ACCION_LISTAR:
                        PrimeFaces.current().ajax().update("frmLineas");
                        PrimeFaces.current().ajax().update("frmOptCrearLinea");
                        break;
                    case Url.ACCION_VER:
                        PrimeFaces.current().ajax().update("frmVerLinea");
                        PrimeFaces.current().executeScript("PF('dialogoVerLinea').show()");
                        break;
                    case Url.ACCION_CREAR:
                        PrimeFaces.current().resetInputs("frmCrearLinea");
                        PrimeFaces.current().ajax().update("frmCrearLinea");
                        PrimeFaces.current().executeScript("PF('dialogoCrearLinea').show()");
                        CntjConstantes.desactivarInput("frmCrearLinea:cResponsable", FacesContext.getCurrentInstance());
                        break;
                    case Url.ACCION_GUARDAR:
                        PrimeFaces.current().ajax().update("frmLineas");
                        break;
                    case Url.ACCION_EDITAR:
                        PrimeFaces.current().executeScript("PF('dialogoEditarLinea').show()");
                        PrimeFaces.current().ajax().update("frmEditarLinea");
                        crearLog(getObjetoLinea().toString());
                        break;
                    case Url.ACCION_MODIFICAR:
                        PrimeFaces.current().ajax().update("frmLineas");
                        break;
                    case Url.ACCION_ADICIONAL_1:
                        PrimeFaces.current().executeScript("PF('dialogoEvaluarLinea').show()");
                        PrimeFaces.current().ajax().update("frmEvaluarLinea");
                        crearLog(getObjetoLinea().toString());
                        break;
                    case Url.ACCION_ADICIONAL_2:
                        PrimeFaces.current().ajax().update("frmLineas");
                        PrimeFaces.current().ajax().update("frmGestion");
                        break;
                }
                break;
            case Url.ACCION_ADICIONAL_4:
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            PrimeFaces.current().ajax().update("frmDocumentos");
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            PrimeFaces.current().ajax().update("frmVisualizaDoc");
                            PrimeFaces.current().executeScript("PF('dialogoVisualizarDoc').show()");
                            break;
                    }
                    break;
        }
        generarMensajes();
    }

}
