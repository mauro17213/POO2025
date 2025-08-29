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
import com.saviasaludeps.savia.dominio.juridico.CntjPlantilla;
import com.saviasaludeps.savia.dominio.juridico.CntjTercero;
import com.saviasaludeps.savia.web.juridico.servicio.TareaServicioIface;
import com.saviasaludeps.savia.web.juridico.utilidades.CntjConstantes;
import com.saviasaludeps.savia.web.juridico.utilidades.CntjUtilidades;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_ADICIONAL_1;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_ADICIONAL_2;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_ADICIONAL_3;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_CREAR;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
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
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;
import org.primefaces.util.EscapeUtils;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author idbohorquez
 */
@ManagedBean
@ViewScoped
public class TareaBean extends Url {

    private TareaServicioIface cntjTareaServicio;

    private LazyDataModel<CntjExpediente> lazyExpediente;
    private LazyDataModel<CntjDocumento> lazyDocumentos;
    private LazyDataModel<CntjTercero> lazyTerceros;
    private LazyDataModel<Usuario> lazyUsuarios;

    private List<CntjExpediente> registros;
    private CntjExpediente objeto;
    private CntjEstadoEjecucion objeToEjecucion;
    private HashMap<Integer, CntjEstado> hashListaEstadoSiguiente;
    private boolean modificarFechaGestion;
    private List<CntjEstado> listaEstadoSiguiente;
    private List<CntjCampo> listaCamposTransicion;
    private List<CntjCampo> listaCamposGestionados;
    private List<CntjDocumento> listaAdjuntos;
    private CntjCampo objetoCampo;
    private List<CntjDocumento> listaDocumentos;
    private List<CntjCampo> listaCampos;
    private List<Maestro> listaMaestroCampo;
    private HashMap<Integer, Maestro> hashListaMaestroCampo;
    private HashMap<String, CntjCampo> hashListaCampos;
    private CntjTercero tercero;
    private CntjDocumento adjunto;
    private List<CntjEstadoProcesoDocumento> listaEstadoDocumento;
    private List<CntjTercero> registrosTerceros;
    private List<Maestro> listaTipoTercero;
    private List<Maestro> listaTipoDocumentoTercero;
    private Usuario usuario;
    private List<Usuario> listaUsuario;
    private CntjDocumento objDocumento;
    private String contenidoPdf;
    private CntjCampo objCampoAux;
    private List<CntjPlantilla> listaPrevisualizacion;
    private CntjPlantilla objPlantilla;
    //variable de datos para utilizar en insersion de contrato
    private HashMap<Integer, Maestro> hashlistaModalidad;
    private HashMap<Integer, Maestro> hashlistaClaseContrato;
    private HashMap<Integer, Maestro> hashlistaRegimen;
    private HashMap<Integer, Maestro> hashlistaEstadoContrato;
    private HashMap<Integer, Maestro> hashlistaGarantiaContrato;

    public TareaBean() {
        this.objeto = new CntjExpediente();
        this.objeToEjecucion = new CntjEstadoEjecucion();
        this.objetoCampo = new CntjCampo();
        this.objDocumento = new CntjDocumento();
        this.objCampoAux = new CntjCampo();
        Modulo _mod = super.validarModulo(Modulo.ID_CNTJ_TAREAS);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionarHome();
        } else {
            super.setModulo(_mod);
            super.addListaParamConsultas(new ParamConsulta()); //paramconsulta(0)
            super.addListaParamConsultas(new ParamConsulta()); //paramconsulta(1)
            super.addListaParamConsultas(new ParamConsulta()); //paramconsulta(2)
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
        getCntjTareaServicio().cargaInicial(this);
    }

    // <editor-fold defaultstate="collapsed" desc="Gettes and Setters">

   
    public String campoBorrador(boolean valor){
        if(valor){
            return "(Borrador)";
        }
        return "";
    }
      
    public CntjPlantilla getObjPlantilla() {
        return objPlantilla;
    }

    public void setObjPlantilla(CntjPlantilla objPlantilla) {
        this.objPlantilla = objPlantilla;
    }

    public List<CntjPlantilla> getListaPrevisualizacion() {
        return listaPrevisualizacion;
    }

    public void setListaPrevisualizacion(List<CntjPlantilla> listaPrevisualizacion) {
        this.listaPrevisualizacion = listaPrevisualizacion;
    }

    public boolean isPermitirModificarValoresGestionados() {
        if (getObjeToEjecucion() != null && getObjeToEjecucion().getCntjEstadoId() != null) {
            return !getObjeToEjecucion().getCntjEstadoId().isModificaDatos();
        }
        return false;
    }

    public List<CntjCampo> getListaCamposGestionados() {
        return listaCamposGestionados;
    }

    public void setListaCamposGestionados(List<CntjCampo> listaCamposGestionados) {
        this.listaCamposGestionados = listaCamposGestionados;
    }

    public CntjCampo getObjCampoAux() {
        return objCampoAux;
    }

    public void setObjCampoAux(CntjCampo objCampoAux) {
        this.objCampoAux = objCampoAux;
    }

    public String getContenidoPdf() {
        return contenidoPdf;
    }

    public void setContenidoPdf(String contenidoPdf) {
        this.contenidoPdf = contenidoPdf;
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

    public CntjDocumento getObjDocumento() {
        return objDocumento;
    }

    public void setObjDocumento(CntjDocumento objDocumento) {
        this.objDocumento = objDocumento;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTipoDocumento(Integer id) {
        return CntjConstantes.getTipoDocumento(id);
    }

    public String getTipoTerceroStr(Integer id) {
        return CntjConstantes.getTipoTercero(id);
    }

    public List<Maestro> getListaTipoTercero() {
        return listaTipoTercero;
    }

    public void setListaTipoTercero(List<Maestro> listaTipoTercero) {
        this.listaTipoTercero = listaTipoTercero;
    }

    public List<Maestro> getListaTipoDocumentoTercero() {
        return listaTipoDocumentoTercero;
    }

    public void setListaTipoDocumentoTercero(List<Maestro> listaTipoDocumentoTercero) {
        this.listaTipoDocumentoTercero = listaTipoDocumentoTercero;
    }

    public LazyDataModel<CntjTercero> getLazyTerceros() {
        return lazyTerceros;
    }

    public void setLazyTerceros(LazyDataModel<CntjTercero> lazyTerceros) {
        this.lazyTerceros = lazyTerceros;
    }

    public List<CntjTercero> getRegistrosTerceros() {
        return registrosTerceros;
    }

    public void setRegistrosTerceros(List<CntjTercero> registrosTerceros) {
        this.registrosTerceros = registrosTerceros;
    }

    public CntjDocumento getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(CntjDocumento adjunto) {
        this.adjunto = adjunto;
    }

    public List<CntjEstadoProcesoDocumento> getListaEstadoDocumento() {
        return listaEstadoDocumento;
    }

    public void setListaEstadoDocumento(List<CntjEstadoProcesoDocumento> listaEstadoDocumento) {
        this.listaEstadoDocumento = listaEstadoDocumento;
    }

    public CntjTercero getTercero() {
        return tercero;
    }

    public void setTercero(CntjTercero tercero) {
        this.tercero = tercero;
    }

    public HashMap<String, CntjCampo> getHashListaCampos() {
        return hashListaCampos;
    }

    public void setHashListaCampos(HashMap<String, CntjCampo> hashListaCampos) {
        this.hashListaCampos = hashListaCampos;
    }

    public List<CntjCampo> getListaCampos() {
        return listaCampos;
    }

    public void setListaCampos(List<CntjCampo> listaCampos) {
        this.listaCampos = listaCampos;
    }

    public List<Maestro> getListaMaestroCampo() {
        return listaMaestroCampo;
    }

    public void setListaMaestroCampo(List<Maestro> listaMaestroCampo) {
        this.listaMaestroCampo = listaMaestroCampo;
    }

    public HashMap<Integer, Maestro> getHashListaMaestroCampo() {
        return hashListaMaestroCampo;
    }

    public void setHashListaMaestroCampo(HashMap<Integer, Maestro> hashListaMaestroCampo) {
        this.hashListaMaestroCampo = hashListaMaestroCampo;
    }

    public LazyDataModel<CntjDocumento> getLazyDocumentos() {
        return lazyDocumentos;
    }

    public void setLazyDocumentos(LazyDataModel<CntjDocumento> lazyDocumentos) {
        this.lazyDocumentos = lazyDocumentos;
    }

    public List<CntjDocumento> getListaDocumentos() {
        return listaDocumentos;
    }

    public void setListaDocumentos(List<CntjDocumento> listaDocumentos) {
        this.listaDocumentos = listaDocumentos;
    }

    public CntjCampo getObjetoCampo() {
        return objetoCampo;
    }

    public void setObjetoCampo(CntjCampo objetoCampo) {
        this.objetoCampo = objetoCampo;
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

    public Date getFechaActual() {
        return new Date();
    }

    public List<CntjCampo> getListaCamposTransicion() {
        return listaCamposTransicion;
    }

    public void setListaCamposTransicion(List<CntjCampo> listaCamposTransicion) {
        this.listaCamposTransicion = listaCamposTransicion;
    }

    public List<CntjDocumento> getListaAdjuntos() {
        return listaAdjuntos;
    }

    public void setListaAdjuntos(List<CntjDocumento> listaAdjuntos) {
        this.listaAdjuntos = listaAdjuntos;
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

    public HashMap<Integer, CntjEstado> getHashListaEstadoSiguiente() {
        return hashListaEstadoSiguiente;
    }

    public void setHashListaEstadoSiguiente(HashMap<Integer, CntjEstado> hashListaEstadoSiguiente) {
        this.hashListaEstadoSiguiente = hashListaEstadoSiguiente;
    }

    public boolean isModificarFechaGestion() {
        return modificarFechaGestion;
    }

    public void setModificarFechaGestion(boolean modificarFechaGestion) {
        this.modificarFechaGestion = modificarFechaGestion;
    }

    public CntjExpediente getObjeto() {
        return objeto;
    }

    public void setObjeto(CntjExpediente objeto) {
        this.objeto = objeto;
    }

    public LazyDataModel<CntjExpediente> getLazyExpediente() {
        return lazyExpediente;
    }

    public void setLazyExpediente(LazyDataModel<CntjExpediente> lazyExpediente) {
        this.lazyExpediente = lazyExpediente;
    }

    public List<CntjExpediente> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CntjExpediente> registros) {
        this.registros = registros;
    }

    public TareaServicioIface getCntjTareaServicio() {
        return cntjTareaServicio;
    }

    public void setCntjTareaServicio(TareaServicioIface cntjTareaServicio) {
        this.cntjTareaServicio = cntjTareaServicio;
    }
    // </editor-fold>

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCntjTareaServicio().Accion(this);
    }

    public void refrescarTerceros() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_ADICIONAL_2);
        getCntjTareaServicio().Accion(this);
    }

    public void refrescarDocumentos() {
        super.setAccion(Url.ACCION_VER);
        getCntjTareaServicio().Accion(this);
    }

    public void refrescarUsuarios() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_ADICIONAL_3);
        getCntjTareaServicio().Accion(this);
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void crearGestion(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_CREAR);
        getCntjTareaServicio().Accion(this);
        procesoFinal();
        validarEstadoSiguiente();
    }

    public void guardarGestion() {
        super.setAccion(ACCION_GUARDAR);
        getCntjTareaServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoGestionar').hide();");
        }
        procesoFinal();
    }

    public void guardarBorrador() {
        super.setAccion(ACCION_ADICIONAL_4);
        getCntjTareaServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoGestionar').hide();");
        }
        procesoFinal();
    }

    public void validarEstadoSiguiente() {
        if (getObjeToEjecucion().getCntjEstadoId().getId() != null) {
            super.setAccion(ACCION_ADICIONAL_1);
            super.setDoAccion(ACCION_ADICIONAL_4);
            getCntjTareaServicio().Accion(this);
            if (getObjeToEjecucion().getCntjEstadoId().isModificaFecha()) {
                setModificarFechaGestion(false);
            } else {
                setModificarFechaGestion(true);
            }
            procesoFinal();
        }
    }

    public void eliminarCampo(Integer id) {
        getListaCamposTransicion().removeIf(f -> f.getId().equals(id));
    }

    public void eliminarAdjunto(int id) {
        getListaAdjuntos().removeIf(item -> item.getId().equals(id));
    }

    public void verdocumentos(Integer id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
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

    public void crearCampo() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_CREAR);
        getCntjTareaServicio().Accion(this);
        procesoFinal();
        validarTipoCampo();
    }

    public void guardarCampos() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_GUARDAR);
        getCntjTareaServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoAddCampo').hide();");
        }
        procesoFinal();
    }

    public void editarCampo(int idcampo) {
        setObjetoCampo(new CntjCampo(idcampo));
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_EDITAR);
        getCntjTareaServicio().Accion(this);
        validarTipoCampoEdicion();
        procesoFinal();
    }

    public void modificarCampos() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_MODIFICAR);
        getCntjTareaServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoModificarCampo').hide();");
        }
        procesoFinal();
    }

    public void validarTipoCampo() {
        if (getObjetoCampo().getNombre() != null) {
            Optional<CntjCampo> campo = getListaCampos().stream().filter(it -> it.getNombre().equals(getObjetoCampo().getNombre()))
                    .findFirst();
            getObjetoCampo().setTipoDato(campo.get().getTipoDato());
            getObjetoCampo().setMaestroTipo(campo.get().getMaestroTipo());
            if (campo.get().getTipoDato().equals(CntjConstantes.TIPO_DATO_LISTA) && campo.get().isAplicaMaestro()) {
                super.setAccion(ACCION_ADICIONAL_1);
                super.setDoAccion(ACCION_ADICIONAL_1);
                getCntjTareaServicio().Accion(this);
                procesoFinal();
            } else if (campo.get().getTipoDato().equals(CntjConstantes.TIPO_DATO_LISTA) && !campo.get().isAplicaMaestro()) {
                JSONArray arrayJson = new JSONArray(campo.get().getValoresLista());
                setListaMaestroCampo(new ArrayList<>());
                setHashListaMaestroCampo(new HashMap<>());
                for (int index = 0; index < arrayJson.length(); index++) {
                    JSONObject obj = arrayJson.getJSONObject(index);
                    Maestro maestro = new Maestro();
                    maestro.setId(obj.getInt(CntjConstantes.VALOR));
                    maestro.setNombre(obj.getString(CntjConstantes.OPCION));
                    getListaMaestroCampo().add(maestro);
                }
                setHashListaMaestroCampo(CntjConstantes.obtenerHashMaestro(getListaMaestroCampo()));
            }

            if (campo.get().getTipoDato().equals(CntjConstantes.TIPO_DATO_TEXTO_LARGO)) {
                PrimeFaces.current().executeScript("document.getElementById('modalAddCampo').classList.add('fullscreen-dialog');");
            } else {
                PrimeFaces.current().executeScript("document.getElementById('modalAddCampo').classList.remove('fullscreen-dialog');");
            }
        } else {
            getObjetoCampo().setTipoDato(null);
            PrimeFaces.current().executeScript("document.getElementById('modalAddCampo').classList.remove('fullscreen-dialog');");
        }
    }

    private void validarTipoCampoEdicion() {
        if (getObjetoCampo() != null && getObjetoCampo().getTipoDato() != null) {
            if (getObjetoCampo().getTipoDato().equals(CntjConstantes.TIPO_DATO_LISTA) && getObjetoCampo().isAplicaMaestro()) {
                super.setAccion(ACCION_ADICIONAL_1);
                super.setDoAccion(ACCION_ADICIONAL_1);
                getCntjTareaServicio().Accion(this);
                procesoFinal();
            } else if (getObjetoCampo().getTipoDato().equals(CntjConstantes.TIPO_DATO_LISTA) && !getObjetoCampo().isAplicaMaestro()) {
                JSONArray arrayJson = new JSONArray(getObjetoCampo().getValoresLista());
                setListaMaestroCampo(new ArrayList<>());
                setHashListaMaestroCampo(new HashMap<>());
                for (int index = 0; index < arrayJson.length(); index++) {
                    JSONObject obj = arrayJson.getJSONObject(index);
                    Maestro maestro = new Maestro();
                    maestro.setId(obj.getInt(CntjConstantes.VALOR));
                    maestro.setNombre(obj.getString(CntjConstantes.OPCION));
                    getListaMaestroCampo().add(maestro);
                }
                setHashListaMaestroCampo(CntjConstantes.obtenerHashMaestro(getListaMaestroCampo()));
            }
            super.setDoAccion(ACCION_EDITAR);
            if (getObjetoCampo().getTipoDato().equals(CntjConstantes.TIPO_DATO_TEXTO_LARGO)) {
                PrimeFaces.current().executeScript("document.getElementById('modalAddCampo').classList.add('fullscreen-dialog');");
            } else {
                PrimeFaces.current().executeScript("document.getElementById('modalAddCampo').classList.remove('fullscreen-dialog');");
            }
        }
    }

    public void agregarAdjunto() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_CREAR);
        getCntjTareaServicio().Accion(this);
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
                    
                    if(documento.get().getProcesodocumentoId().getTipoDocumento().equals(CntjConstantes.TIPO_DOCUMENTO_MIXTO) && !documento.get().getProcesodocumentoId().getNombre().equals(FilenameUtils.removeExtension(archivoAdjunto.getFileName()))){
                        super.addError("El documento adjunto no tiene el mismo nombre del documento solicitado, asegúrese que se llamen igual. ");
                    }else {
                        if (getListaAdjuntos().isEmpty()) {
                            getAdjunto().setId(-1);
                        } else {
                            Optional<CntjDocumento> maxItem = getListaAdjuntos().stream().max((a, b) -> Integer.compare(a.getId(), b.getId()));
                            int idadjunto = (maxItem.get().getId() + 1) * -1;
                            getAdjunto().setId(idadjunto);
                        }

                        if (documento.get().getProcesodocumentoId().getTipoDocumento().equals(CntjConstantes.TIPO_DOCUMENTO_MIXTO)) {
                            getAdjunto().setId(documento.get().getProcesodocumentoId().getId());
                        }
                        getAdjunto().setNombre(documento.get().getProcesodocumentoId().getNombre());
                        getAdjunto().setTipo(documento.get().getProcesodocumentoId().getTipoDocumento().shortValue());
                        getAdjunto().setEstadoDocumento(documento.get());
                        getAdjunto().setAdjuntoStream(archivoAdjunto.getInputStream());
                        getAdjunto().setEtapaContratacion(documento.get().getProcesodocumentoId().getEtapaContratacion());
                        String nombreAdjunto = FilenameUtils.getName(archivoAdjunto.getFileName());
                        getAdjunto().setDocumentoNombre(nombreAdjunto);
                        getListaAdjuntos().add(getAdjunto());
                        PrimeFaces.current().ajax().update("frmGestionar:tablaAdjuntos");
                        PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').hide();");
                    }                    
                }
            }

        } catch (IOException ex) {
            this.addError(ex.getMessage());
        }
        procesoFinal();
    }

    public void listarTerceros(CntjCampo itemCampo) {
        setObjCampoAux(itemCampo);
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_ADICIONAL_2);
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
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_GUARDAR);
        getCntjTareaServicio().Accion(this);
        //PrimeFaces.current().ajax().update("frmAddCampo");
        //PrimeFaces.current().ajax().update("frmModificarCampo");
        PrimeFaces.current().executeScript("PF('dialogoListaTercero').hide()");
        PrimeFaces.current().executeScript("actualizarCampoPorDataId(" + getObjCampoAux().getId() + ", '" + tercero.getRazonSocial() + "');");
        procesoFinal();
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

    public void listarUsuario(CntjCampo itemCampo) {
        setObjCampoAux(itemCampo);
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_ADICIONAL_3);
        cargarLazyUsuarios();
        PrimeFaces.current().executeScript("PF('dialogoUsuariosLista').show()");
        procesoFinal();
    }

    private void cargarLazyUsuarios() {
        lazyUsuarios = new LazyDataModel<Usuario>() {
            private List<Usuario> listaUsuario;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(2).getCantidadRegistros();
            }

            @Override
            public List<Usuario> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(2).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(2).setPrimerRegistro(primerRegistro);
                getParamConsulta(2).setRegistrosPagina(registrosPagina);
                getParamConsulta(2).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(2).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarUsuarios();
                listaUsuario = getListaUsuario();
                setRowCount(getParamConsulta(2).getCantidadRegistros());
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
        setUsuario(usuario);
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_GUARDAR);
        getCntjTareaServicio().Accion(this);
//        PrimeFaces.current().ajax().update("frmAddCampo");
//        PrimeFaces.current().ajax().update("frmModificarCampo");
        PrimeFaces.current().executeScript("PF('dialogoUsuariosLista').hide();");
        PrimeFaces.current().executeScript("actualizarCampoPorDataId(" + getObjCampoAux().getId() + ", '" + usuario.getNombre() + "');");
        procesoFinal();
    }

    public void visualizardocumentos(Integer id) {
        getObjDocumento().setId(id);
        super.setAccion(ACCION_ADICIONAL_3);
        getCntjTareaServicio().Accion(this);
        generarDocumentoPdfStream();
        procesoFinal();
    }

    public List<Maestro> consultarMaestroDinamico(CntjCampo campo) {
        try {
            setObjetoCampo(campo);
            if (campo.getTipoDato().equals(CntjConstantes.TIPO_DATO_LISTA) && campo.isAplicaMaestro()) {
                super.setAccion(ACCION_ADICIONAL_1);
                super.setDoAccion(ACCION_ADICIONAL_1);
                getCntjTareaServicio().Accion(this);
            } else if (campo.getTipoDato().equals(CntjConstantes.TIPO_DATO_LISTA) && !campo.isAplicaMaestro()) {
                JSONArray arrayJson = new JSONArray(campo.getValoresLista());
                setListaMaestroCampo(new ArrayList<>());
                setHashListaMaestroCampo(new HashMap<>());
                for (int index = 0; index < arrayJson.length(); index++) {
                    JSONObject obj = arrayJson.getJSONObject(index);
                    Maestro maestro = new Maestro();
                    maestro.setId(obj.getInt(CntjConstantes.VALOR));
                    maestro.setNombre(obj.getString(CntjConstantes.OPCION));
                    getListaMaestroCampo().add(maestro);
                }
            }

            if (campo.getExistente()) {
                getListaCamposGestionados().stream()
                        .filter(obj -> obj.getId().equals(campo.getId()))
                        .findFirst()
                        .ifPresent(obj -> {
                            obj.setListaValores(getListaMaestroCampo());
                            obj.setHashlistaValores(CntjConstantes.obtenerHashMaestro(getListaMaestroCampo()));
                        });
                procesoFinal();
            } else {
                getListaCamposTransicion().stream()
                        .filter(obj -> obj.getId().equals(campo.getId()))
                        .findFirst()
                        .ifPresent(obj -> {
                            obj.setListaValores(getListaMaestroCampo());
                            obj.setHashlistaValores(CntjConstantes.obtenerHashMaestro(getListaMaestroCampo()));
                        });
                procesoFinal();
            }

            return getListaMaestroCampo();
        } catch (Exception e) {
            super.addError("Se presento inconveniente al consultar lista de maestros para el campo: " + campo.getNombre());
            procesoFinal();
            return new ArrayList<>();
        }
    }

    public void establecerValorCampoDate(int idcampo, boolean existente) {
        if (existente) {
            if (!getListaCamposGestionados().isEmpty()) {
                Date valordt = getListaCamposGestionados().stream()
                        .filter(obj -> obj.getId().equals(idcampo))
                        .map(CntjCampo::getValorDt)
                        .findFirst()
                        .orElse(null);
                if (valordt != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String fechastr = sdf.format(valordt);
                    getListaCamposGestionados().stream()
                            .filter(obj -> obj.getId().equals(idcampo))
                            .findFirst()
                            .ifPresent(obj -> obj.setValor(fechastr));
                }
            }
        } else {
            if (!getListaCamposTransicion().isEmpty()) {
                Date valordt = getListaCamposTransicion().stream()
                        .filter(obj -> obj.getId().equals(idcampo))
                        .map(CntjCampo::getValorDt)
                        .findFirst()
                        .orElse(null);
                if (valordt != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String fechastr = sdf.format(valordt);
                    getListaCamposTransicion().stream()
                            .filter(obj -> obj.getId().equals(idcampo))
                            .findFirst()
                            .ifPresent(obj -> obj.setValor(fechastr));
                }
            }
        }
    }

    public void establecerValorCampoBoolean(int idcampo, boolean existente) {
        if (existente) {
            if (!getListaCamposGestionados().isEmpty()) {
                boolean valorBl = getListaCamposGestionados().stream()
                        .filter(obj -> obj.getId().equals(idcampo))
                        .map(CntjCampo::isValorBl)
                        .findFirst()
                        .orElse(false);

                String valorBolean = valorBl ? "Si" : "No";
                getListaCamposGestionados().stream()
                        .filter(obj -> obj.getId().equals(idcampo))
                        .findFirst()
                        .ifPresent(obj -> obj.setValor(valorBolean));
            }
        } else {
            if (!getListaCamposTransicion().isEmpty()) {
                boolean valorBl = getListaCamposTransicion().stream()
                        .filter(obj -> obj.getId().equals(idcampo))
                        .map(CntjCampo::isValorBl)
                        .findFirst()
                        .orElse(false);

                String valorBolean = valorBl ? "Si" : "No";
                getListaCamposTransicion().stream()
                        .filter(obj -> obj.getId().equals(idcampo))
                        .findFirst()
                        .ifPresent(obj -> obj.setValor(valorBolean));
            }
        }
    }

    public void establecerValorCampoSelect(CntjCampo campoItem) {
        if (campoItem.getExistente()) {
            if (!getListaCamposGestionados().isEmpty()) {
                String valorSt = campoItem.getHashlistaValores().get(Integer.parseInt(campoItem.getValor())).getNombre();
                getListaCamposGestionados().stream()
                        .filter(obj -> obj.getId().equals(campoItem.getId()))
                        .findFirst()
                        .ifPresent(obj -> {
                            obj.setValorStr(valorSt);
                        });
            }
        } else {
            if (!getListaCamposTransicion().isEmpty()) {
                String valorSt = campoItem.getHashlistaValores().get(Integer.parseInt(campoItem.getValor())).getNombre();
                getListaCamposTransicion().stream()
                        .filter(obj -> obj.getId().equals(campoItem.getId()))
                        .findFirst()
                        .ifPresent(obj -> {
                            obj.setValorStr(valorSt);
                        });
            }
        }
    }

    public void ingresarTextEditor(CntjCampo campoItem) {
        CntjCampo aux = campoItem;
        aux.setValorStr(campoItem.getValor());
        setObjCampoAux(aux);
        PrimeFaces.current().executeScript("PF('dialogoAddEditTest').show()");
        PrimeFaces.current().ajax().update("frmAddEditTest");
    }

    public void guardarTextEditor() {
        if (getObjCampoAux().getValor() != null) {
            if (getObjCampoAux().getExistente()) {
                getListaCamposGestionados().stream()
                        .filter(obj -> obj.getId().equals(getObjCampoAux().getId()))
                        .findFirst()
                        .ifPresent(obj -> {
                            obj.setValor(getObjCampoAux().getValor());
                        });
            } else {
                getListaCamposTransicion().stream()
                        .filter(obj -> obj.getId().equals(getObjCampoAux().getId()))
                        .findFirst()
                        .ifPresent(obj -> {
                            obj.setValor(getObjCampoAux().getValor());
                        });
            }

            //PrimeFaces.current().ajax().update("frmGestionar:panelCampos"); 
            String contenidoEscapado = EscapeUtils.forJavaScript(getObjCampoAux().getValor());
            PrimeFaces.current().executeScript("PF('dialogoAddEditTest').hide()");
            PrimeFaces.current().executeScript("actualizarCampoEditorPorDataId(" + getObjCampoAux().getId() + ", '" + contenidoEscapado + "');");
        }
    }

    public void actualizarComponentesFront(List<CntjCampo> listaid) {
        for (CntjCampo campo : listaid) {
            PrimeFaces.current().executeScript("actualizarCampoPorDataId(" + campo.getId() + ", '" + campo.getValor() + "');");
        }
    }

    public void previsualizarDocumentos() {
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_LISTAR);
        getCntjTareaServicio().Accion(this);
        if (super.isError()) {
            generarMensajes();
        }else{
            procesoFinal();
        }        
    }

    public void previsualizaDocumento() {
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_VER);
        if (getObjPlantilla().getId() == null) {
            setContenidoPdf(null);
        } else {
            getCntjTareaServicio().Accion(this);
        }
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
                crearLog(getObjeToEjecucion().toString());
                PrimeFaces.current().ajax().update("frmGestion");
                break;
            case Url.ACCION_VER:
                PrimeFaces.current().ajax().update("frmDocumentos");
                break;
            case Url.ACCION_CREAR:
                PrimeFaces.current().executeScript("PF('dialogoGestionar').show()");
                PrimeFaces.current().ajax().update("frmGestionar");
                PrimeFaces.current().ajax().update("frmGestion");
                break;
            case Url.ACCION_ADICIONAL_1:
                switch (getDoAccion()) {
                    case Url.ACCION_CREAR:
                        PrimeFaces.current().ajax().update("frmGestionar:panelCampos");
                        //PrimeFaces.current().executeScript("PF('dialogoAddCampo').show()");
                        break;
                    case Url.ACCION_GUARDAR:
                        //PrimeFaces.current().ajax().update("frmGestionar");
                        break;
                    case Url.ACCION_EDITAR:
                        PrimeFaces.current().ajax().update("frmModificarCampo");
                        PrimeFaces.current().executeScript("PF('dialogoModificarCampo').show()");
                        break;
                    case Url.ACCION_MODIFICAR:
                        PrimeFaces.current().ajax().update("frmGestionar");
                        break;
                    case Url.ACCION_ADICIONAL_2:
                        PrimeFaces.current().ajax().update("frmTerceroLista");
                        break;
                    case Url.ACCION_ADICIONAL_3:
                        PrimeFaces.current().ajax().update("frmUsuariosLista");
                        break;
                    case Url.ACCION_ADICIONAL_4:
                        PrimeFaces.current().ajax().update("frmGestionar:panelCampos");
                        PrimeFaces.current().ajax().update("frmGestionar:camposGestionados");
                        break;
                }
                break;
            case Url.ACCION_ADICIONAL_2:
                switch (getDoAccion()) {
                    case Url.ACCION_CREAR:
                        PrimeFaces.current().ajax().update("frmCrearAdjunto");
                        PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').show()");
                        break;
                }
                break;
            case Url.ACCION_ADICIONAL_3:
                PrimeFaces.current().ajax().update("frmVisualizaDoc");
                PrimeFaces.current().executeScript("PF('dialogoVisualizarDoc').show()");
                break;
            case Url.ACCION_ADICIONAL_5:
                switch (getDoAccion()) {
                    case Url.ACCION_LISTAR:
                        PrimeFaces.current().ajax().update("frmPreVisualizaDoc");
                        PrimeFaces.current().executeScript("PF('dialogoPreVisualizarDoc').show()");
                        break;
                    case Url.ACCION_VER:
                        PrimeFaces.current().ajax().update("frmPreVisualizaDoc");
                        PrimeFaces.current().executeScript("visualizarPdfFullPagina('frmPreVisualizaDoc:infoPdf', 'pdfViewer2');");
                        break;
                }
                break;

        }
        generarMensajes();
    }

}
