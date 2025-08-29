package com.saviasaludeps.savia.web.especial.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContacto;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegPortabilidad;
import com.saviasaludeps.savia.dominio.auditoria.concurrente.AucHospitalizacion;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadoDiagnostico;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadoSugerido;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.dominio.especial.PeIpsPrograma;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.especial.PeSugeridoAdjunto;
import com.saviasaludeps.savia.dominio.especial.PeTelefono;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.web.utilidades.Url;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.util.List;
import java.util.Map;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import com.saviasaludeps.savia.web.especial.servicio.PeAfiliadoSugeridoServicioIface;
import com.saviasaludeps.savia.web.especial.utilidades.PeConstantes;
import com.saviasaludeps.savia.web.maestro.seleccion.bean.SelDiagnosticosBean;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.SortMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author idbohorquez
 */
@ManagedBean
@ViewScoped
public class PeAfiliadoSugeridoBean extends Url {

    @Autowired
    private PeAfiliadoSugeridoServicioIface peAfiliadoSugeridoServicio;

    private List<PeAfiliadoSugerido> registros;
    private LazyDataModel<PeAfiliadoSugerido> lazySugeridos;
    private List<Ubicacion> listaUbicaciones;
    private HashMap<Integer, Ubicacion> hashUbicaciones;
    private List<AsegAfiliado> listaGrupoFamiliarAfiliado;
    private List<AsegPortabilidad> listaPortabilidadAfiliado;
    private List<AucHospitalizacion> listaHospitalizacion;
    private LazyDataModel<AucHospitalizacion> lazyHospitalizacion;
    private List<PeIpsPrograma> listaPrestadorSede;
    private List<Maestro> listaRegionCorporal;
    private HashMap<Integer, Maestro> hashRegionCorporal;
    private List<Maestro> listaMedioDiagnostico;
    private HashMap<Integer, Maestro> hashMedioDiagnostico;
    private List<Maestro> listCausasActivo;
    private HashMap<Integer, Maestro> hashCausasActivo;
    private SelDiagnosticosBean diagnosticosBean;
    private List<AsegAfiliadoContacto> listaContactos;
    private List<PeTelefono> listaContactosPrograma;
    private List<Maestro> listaEstadoSugerido;
    private HashMap<Integer, Maestro> hashEstadoSugerido;
    private HashMap<Integer, Maestro> hashTiposDocumento;
    private List<Maestro> listaTiposDocumento;
    private List<Maestro> listaTiposGenero;
    private HashMap<Integer, Maestro> hashTiposGenero;
    private List<PePrograma> listaPePrograma;
    private List<Maestro> listaTiposContacto;
    private HashMap<Integer, Maestro> hashTiposContacto;
    private List<PeTelefono> listaContactosBorrar;
    private List<Maestro> listaFuenteOrigen;
    private List<PeSugeridoAdjunto> listaSugeridoAdjuntos;

    private PeAfiliadoSugerido objeto;
    private PeAfiliadosPrograma objetoAfiliadosPrograma;
    private boolean afiliadoContribucionSolidaria;
    private String mensajeAfiliadoContribucionSolidaria;
    private boolean afiliadoSinEncuestaSisben4;
    private String mensajeAfiliadoSinEncuestaSisben4;
    private AucHospitalizacion objetoHospitalizacion;
    private AuAnexo3 objetoAnexo3;
    private ParamConsulta paramConsultaHospitalizacion;
    private Date fechaHoy;
    private int contadorIdDiagnosticos;
    private String causaActivo = "";
    private PeTelefono contacto;
    private int contadorIdContacto;
    private String descripcionGenerica;

    public PeAfiliadoSugeridoBean() {
        this.objeto = new PeAfiliadoSugerido();
        this.objetoAfiliadosPrograma = new PeAfiliadosPrograma();
        this.contadorIdDiagnosticos = -1;
        this.contadorIdContacto = -1;
        Modulo mod = super.validarModulo(Modulo.ID_PROGRAMA_ESPECIAL_SUGERIDOS);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazySugeridos = new LazyDataModel<PeAfiliadoSugerido>() {
                private List<PeAfiliadoSugerido> sugeridos;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<PeAfiliadoSugerido> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    sugeridos = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return sugeridos;
                }

                @Override
                public String getRowKey(PeAfiliadoSugerido sugerido) {
                    return sugerido.getId().toString();
                }

                @Override
                public PeAfiliadoSugerido getRowData(String sugeridoId) {
                    Integer id = Integer.valueOf(sugeridoId);
                    for (PeAfiliadoSugerido item : sugeridos) {
                        if (id.equals(item.getId())) {
                            return item;
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
        getPeAfiliadoSugeridoServicio().cargasInicial(this);
        listar();
    }

    public PeAfiliadoSugeridoServicioIface getPeAfiliadoSugeridoServicio() {
        return peAfiliadoSugeridoServicio;
    }

    public void setPeAfiliadoSugeridoServicio(PeAfiliadoSugeridoServicioIface peAfiliadoSugeridoServicio) {
        this.peAfiliadoSugeridoServicio = peAfiliadoSugeridoServicio;
    }

    public PeAfiliadoSugerido getObjeto() {
        return objeto;
    }

    public void setObjeto(PeAfiliadoSugerido objeto) {
        this.objeto = objeto;
    }

    public List<PeAfiliadoSugerido> getRegistros() {
        return registros;
    }

    public void setRegistros(List<PeAfiliadoSugerido> registros) {
        this.registros = registros;
    }

    public LazyDataModel<PeAfiliadoSugerido> getLazySugeridos() {
        return lazySugeridos;
    }

    public void setLazySugeridos(LazyDataModel<PeAfiliadoSugerido> lazySugeridos) {
        this.lazySugeridos = lazySugeridos;
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

    public boolean isAfiliadoContribucionSolidaria() {
        return afiliadoContribucionSolidaria;
    }

    public void setAfiliadoContribucionSolidaria(boolean afiliadoContribucionSolidaria) {
        this.afiliadoContribucionSolidaria = afiliadoContribucionSolidaria;
    }

    public String getMensajeAfiliadoContribucionSolidaria() {
        return mensajeAfiliadoContribucionSolidaria;
    }

    public void setMensajeAfiliadoContribucionSolidaria(String mensajeAfiliadoContribucionSolidaria) {
        this.mensajeAfiliadoContribucionSolidaria = mensajeAfiliadoContribucionSolidaria;
    }

    public boolean isAfiliadoSinEncuestaSisben4() {
        return afiliadoSinEncuestaSisben4;
    }

    public void setAfiliadoSinEncuestaSisben4(boolean afiliadoSinEncuestaSisben4) {
        this.afiliadoSinEncuestaSisben4 = afiliadoSinEncuestaSisben4;
    }

    public String getMensajeAfiliadoSinEncuestaSisben4() {
        return mensajeAfiliadoSinEncuestaSisben4;
    }

    public void setMensajeAfiliadoSinEncuestaSisben4(String mensajeAfiliadoSinEncuestaSisben4) {
        this.mensajeAfiliadoSinEncuestaSisben4 = mensajeAfiliadoSinEncuestaSisben4;
    }

    public List<AsegAfiliado> getListaGrupoFamiliarAfiliado() {
        return listaGrupoFamiliarAfiliado;
    }

    public void setListaGrupoFamiliarAfiliado(List<AsegAfiliado> listaGrupoFamiliarAfiliado) {
        this.listaGrupoFamiliarAfiliado = listaGrupoFamiliarAfiliado;
    }

    public List<AsegPortabilidad> getListaPortabilidadAfiliado() {
        return listaPortabilidadAfiliado;
    }

    public void setListaPortabilidadAfiliado(List<AsegPortabilidad> listaPortabilidadAfiliado) {
        this.listaPortabilidadAfiliado = listaPortabilidadAfiliado;
    }

    public AucHospitalizacion getObjetoHospitalizacion() {
        return objetoHospitalizacion;
    }

    public void setObjetoHospitalizacion(AucHospitalizacion objetoHospitalizacion) {
        this.objetoHospitalizacion = objetoHospitalizacion;
    }

    public LazyDataModel<AucHospitalizacion> getLazyHospitalizacion() {
        return lazyHospitalizacion;
    }

    public void setLazyHospitalizacion(LazyDataModel<AucHospitalizacion> lazyHospitalizacion) {
        this.lazyHospitalizacion = lazyHospitalizacion;
    }

    public ParamConsulta getParamConsultaHospitalizacion() {
        return paramConsultaHospitalizacion;
    }

    public void setParamConsultaHospitalizacion(ParamConsulta paramConsultaHospitalizacion) {
        this.paramConsultaHospitalizacion = paramConsultaHospitalizacion;
    }

    public List<AucHospitalizacion> getListaHospitalizacion() {
        return listaHospitalizacion;
    }

    public void setListaHospitalizacion(List<AucHospitalizacion> listaHospitalizacion) {
        this.listaHospitalizacion = listaHospitalizacion;
    }

    public List<PeIpsPrograma> getListaPrestadorSede() {
        return listaPrestadorSede;
    }

    public void setListaPrestadorSede(List<PeIpsPrograma> listaPrestadorSede) {
        this.listaPrestadorSede = listaPrestadorSede;
    }

    public PeAfiliadosPrograma getObjetoAfiliadosPrograma() {
        return objetoAfiliadosPrograma;
    }

    public void setObjetoAfiliadosPrograma(PeAfiliadosPrograma objetoAfiliadosPrograma) {
        this.objetoAfiliadosPrograma = objetoAfiliadosPrograma;
    }

    public Date getFechaHoy() {
        return fechaHoy;
    }

    public void setFechaHoy(Date fechaHoy) {
        this.fechaHoy = fechaHoy;
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

    public List<Maestro> getListCausasActivo() {
        return listCausasActivo;
    }

    public void setListCausasActivo(List<Maestro> listCausasActivo) {
        this.listCausasActivo = listCausasActivo;
    }

    public HashMap<Integer, Maestro> getHashCausasActivo() {
        return hashCausasActivo;
    }

    public void setHashCausasActivo(HashMap<Integer, Maestro> hashCausasActivo) {
        this.hashCausasActivo = hashCausasActivo;
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

    public String getCausaActivo() {
        return causaActivo;
    }

    public void setCausaActivo(String causaActivo) {
        this.causaActivo = causaActivo;
    }

    public List<Maestro> getListaEstadoSugerido() {
        return listaEstadoSugerido;
    }

    public void setListaEstadoSugerido(List<Maestro> listaEstadoSugerido) {
        this.listaEstadoSugerido = listaEstadoSugerido;
    }

    public HashMap<Integer, Maestro> getHashEstadoSugerido() {
        return hashEstadoSugerido;
    }

    public void setHashEstadoSugerido(HashMap<Integer, Maestro> hashEstadoSugerido) {
        this.hashEstadoSugerido = hashEstadoSugerido;
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

    public List<PePrograma> getListaPePrograma() {
        return listaPePrograma;
    }

    public void setListaPePrograma(List<PePrograma> listaPePrograma) {
        this.listaPePrograma = listaPePrograma;
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

    public List<PeTelefono> getListaContactosBorrar() {
        return listaContactosBorrar;
    }

    public void setListaContactosBorrar(List<PeTelefono> listaContactosBorrar) {
        this.listaContactosBorrar = listaContactosBorrar;
    }

    public String getDescripcionGenerica() {
        return descripcionGenerica;
    }

    public void setDescripcionGenerica(String descripcionGenerica) {
        this.descripcionGenerica = descripcionGenerica;
    }

    public List<Maestro> getListaFuenteOrigen() {
        return listaFuenteOrigen;
    }

    public void setListaFuenteOrigen(List<Maestro> listaFuenteOrigen) {
        this.listaFuenteOrigen = listaFuenteOrigen;
    }

    public AuAnexo3 getObjetoAnexo3() {
        return objetoAnexo3;
    }

    public void setObjetoAnexo3(AuAnexo3 objetoAnexo3) {
        this.objetoAnexo3 = objetoAnexo3;
    }

    public List<PeSugeridoAdjunto> getListaSugeridoAdjuntos() {
        return listaSugeridoAdjuntos;
    }

    public void setListaSugeridoAdjuntos(List<PeSugeridoAdjunto> listaSugeridoAdjuntos) {
        this.listaSugeridoAdjuntos = listaSugeridoAdjuntos;
    }

    //metodos
    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getPeAfiliadoSugeridoServicio().Accion(this);
    }

    public void refrescarHospitalizacion() {
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(Url.ACCION_LISTAR);
        getPeAfiliadoSugeridoServicio().Accion(this);
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getPeAfiliadoSugeridoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmVer");
            PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        }
        procesoFinal();
    }

    public void marcar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_CREAR);
        getPeAfiliadoSugeridoServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().ajax().update("frmAfiliado");
        PrimeFaces.current().executeScript("PF('dialogoMarcar').show()");
        procesoFinal();
    }

    public void guardarMarca() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_GUARDAR);
        boolean sexoAplica = validarSexoAplica();
        if (!sexoAplica) {
            generarMensajes();
        } else {
            getPeAfiliadoSugeridoServicio().Accion(this);
            if (!super.isError()) {
                PrimeFaces.current().executeScript("PF('dialogoMarcar').hide()");
            }
            procesoFinal();
        }
    }

    public void verListadoHospitalizacion(int id) {
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(Url.ACCION_LISTAR);
        this.objetoHospitalizacion = new AucHospitalizacion(id);
        inicializarTablaHospitalizacion(id);
        getPeAfiliadoSugeridoServicio().Accion(this);
        if (!super.isError()) {
            if (listaHospitalizacion.isEmpty()) {
                addMensaje("El afiliado no tiene registros de Hospitalización");
            } else {
                PrimeFaces.current().ajax().update("frmHospitalizacion");
                PrimeFaces.current().executeScript("PF('dialogoHospitalizacion').show()");
            }
        }
        procesoFinal();
    }

    public void inicializarTablaHospitalizacion(int id) {
        this.setParamConsultaHospitalizacion(new ParamConsulta());
        this.getParamConsultaHospitalizacion().setRegistrosPagina(30);
        this.getParamConsultaHospitalizacion().setParametroConsulta1(id);// este valor será nuestro diferenciador a nivel de afiliado
        lazyHospitalizacion = new LazyDataModel<AucHospitalizacion>() {

            private List<AucHospitalizacion> hospitalizacion;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaHospitalizacion().getCantidadRegistros();
            }

            @Override
            public List<AucHospitalizacion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                //usamos el mismo getParamConsulta pero validamos si debemos cambiarlo
                getParamConsultaHospitalizacion().setPrimerRegistro(primerRegistro);
                getParamConsultaHospitalizacion().setRegistrosPagina(registrosPagina);
                getParamConsultaHospitalizacion().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaHospitalizacion().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarHospitalizacion();
                hospitalizacion = getListaHospitalizacion();
                setRowCount(getParamConsultaHospitalizacion().getCantidadRegistros());
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

    public void verHospitalizacion(PeAfiliadoSugerido peAfiliadoSugerido) {
        setObjeto(peAfiliadoSugerido);
        if (getObjeto().getAucHospitalizaciones().getId() == null) {
            addError("No hay información de hospitalización.");
        } else {
            setObjetoHospitalizacion(new AucHospitalizacion(getObjeto().getAucHospitalizaciones().getId()));
            super.setAccion(Url.ACCION_ADICIONAL_3);
            super.setDoAccion(Url.ACCION_VER);
            getPeAfiliadoSugeridoServicio().Accion(this);
            if (!super.isError()) {
                PrimeFaces.current().ajax().update("frmVerHospitalizacion");
                PrimeFaces.current().ajax().update("frmAuditoriaVer");
                PrimeFaces.current().executeScript("PF('dialogoVerHospitalizacion').show()");
            }
        }
        procesoFinal();
    }

    public void verSolicitudAnexo3(PeAfiliadoSugerido peAfiliadoSugerido) {
        setObjeto(peAfiliadoSugerido);
        if (getObjeto().getAuAnexos3() == null || getObjeto().getAuAnexos3().getId() == null) {
            addError("No hay información de solicitud.");
        } else {
            setObjetoAnexo3(getObjeto().getAuAnexos3());
            super.setAccion(Url.ACCION_ADICIONAL_3);
            super.setDoAccion(Url.ACCION_ADICIONAL_1);
            getPeAfiliadoSugeridoServicio().Accion(this);
            if (!super.isError()) {
                PrimeFaces.current().ajax().update("frmVerSolicitud");
                PrimeFaces.current().ajax().update("frmAuditoriaVerSolicitud");
                PrimeFaces.current().executeScript("PF('dialogoVerSolicitud').show()");
            }
        }
        procesoFinal();
    }

    public void verAuditoria(PeAfiliadoSugerido peAfiliadoSugerido) {
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(Url.ACCION_VER);
        setObjeto(peAfiliadoSugerido);
        PrimeFaces.current().ajax().update("frmAuditoria");
        PrimeFaces.current().executeScript("PF('dialogoAuditoria').show()");
    }

    public void rechazar(int id) {
        getObjeto().setId(id);
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_EDITAR);
        getPeAfiliadoSugeridoServicio().Accion(this);
        PrimeFaces.current().executeScript("PF('dialogoRechazar').show()");
        PrimeFaces.current().ajax().update("frmRechazar");
        procesoFinal();
    }

    public void rechazarSugerido() {
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_MODIFICAR);
        getPeAfiliadoSugeridoServicio().Accion(this);
        PrimeFaces.current().executeScript("PF('dialogoRechazar').hide()");
        PrimeFaces.current().ajax().update("frmRechazar");
        procesoFinal();
    }

    public String getEstado(Integer id) {
        //1 - Pendiente | 2 - Marcado | 3 - Rechazado
        return PeConstantes.getEstadoSugerido(id);
    }

    public String obtenerDepartamento(int id) {
        try {
            int idPadre = getHashUbicaciones().get(id).getUbicacionPadre().getId();
            return getHashUbicaciones().get(idPadre).getNombre();
        } catch (Exception e) {
            return PeConstantes.TEXTO_VACIO;
        }
    }

    public String obtenerMunicipio(int id) {
        try {
            return getHashUbicaciones().get(id).getNombre();
        } catch (Exception e) {
            return PeConstantes.TEXTO_VACIO;
        }
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

    public List<Maestro> completarRegionCorporal(String query) {
        List<Maestro> regionCorporalFiltrados = getListaRegionCorporal().stream()
                .filter(c -> c.getDescripcion().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());

        if (regionCorporalFiltrados.size() == 1) {
            getObjetoAfiliadosPrograma().setMaeRegionCorporalId(regionCorporalFiltrados.get(0).getId());
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
            getObjetoAfiliadosPrograma().setMaeMedioDxId(medioDxFiltrados.get(0).getId());
        }
        return medioDxFiltrados;
    }

    public String getMedioDxRecursiva(int id) {
        String medioDxStr = "";
        if (hashMedioDiagnostico != null) {
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

    public void consultarDiagnostico() {
        PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').show()");
        PrimeFaces.current().executeScript("PF('frmDiagnosticoBusqueda:tablaRegistrosDiagnoticos').clearFilters()");
        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmDiagnosticoBusqueda:tablaRegistrosDiagnoticos");
        dataTable.reset();
        PrimeFaces.current().resetInputs("frmDiagnosticoBusqueda:tablaRegistrosDiagnoticos");
        PrimeFaces.current().ajax().update("frmDiagnosticoBusqueda:tablaRegistrosDiagnoticos");
    }

    public void cerrarDialogoDiagnostico() {
        boolean agregar = true;
        for (PeAfiliadoDiagnostico diag : getObjetoAfiliadosPrograma().getPeAfiliadoDiagnosticoList()) {
            if (Integer.parseInt(diag.getMaDiagnosticosId()) == getDiagnosticosBean().getObjeto().getId()) {
                agregar = false;
                addMensaje("El diagnostico " + getDiagnosticosBean().getObjeto().getNombre() + " ya fue agregado.");
            }
        }
        if (agregar) {
            PeAfiliadoDiagnostico diagnostico = new PeAfiliadoDiagnostico();
            diagnostico.setId(contadorIdDiagnosticos);
            diagnostico.setMaDiagnosticosId(getDiagnosticosBean().getObjeto().getId().toString());
            diagnostico.setMaDiagnosticosCodigo(getDiagnosticosBean().getObjeto().getCodigo());
            diagnostico.setMaDiagnosticosValor(getDiagnosticosBean().getObjeto().getNombre());
            if (getObjetoAfiliadosPrograma().getPeAfiliadoDiagnosticoList().isEmpty()) {
                diagnostico.setPrincipal(true);
            }
            getObjetoAfiliadosPrograma().getPeAfiliadoDiagnosticoList().add(diagnostico);
            getObjetoAfiliadosPrograma().setObjetoDiagnostico(diagnostico);
            contadorIdDiagnosticos--;
            PrimeFaces.current().executeScript("PF('dialogoDiagnosticoBusqueda').hide()");
            PrimeFaces.current().ajax().update("frmCrear:pDiagnosticoCrear");
            PrimeFaces.current().ajax().update("frmCrear:tablaDiagnosticosCrear");
            PrimeFaces.current().ajax().update("frmEditar:pDiagnosticoCrear");
            PrimeFaces.current().ajax().update("frmEditar:tablaDiagnosticosCrear");
        } else {
            generarMensajes();
        }
        getDiagnosticosBean().setObjeto(new MaDiagnostico());
    }

    public void cambiarPrincipalDiagnostico(int idDiagnostico) {
        for (PeAfiliadoDiagnostico diagnostico : getObjetoAfiliadosPrograma().getPeAfiliadoDiagnosticoList()) {
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
        if (getObjetoAfiliadosPrograma().getPeAfiliadoDiagnosticoList().size() > 1) {
            getObjetoAfiliadosPrograma().getPeAfiliadoDiagnosticoList().stream().filter(diagnostico -> (Integer.parseInt(diagnostico.getMaDiagnosticosId()) != id)).forEachOrdered(diagnostico -> {
                nuevaLista.add(diagnostico);
            });

            getObjetoAfiliadosPrograma().getPeAfiliadoDiagnosticoList().stream().filter(diagnostico -> (Integer.parseInt(diagnostico.getMaDiagnosticosId()) == id)).forEachOrdered(diagnostico -> {
                getObjetoAfiliadosPrograma().getPeAfiliadoDiagnosticoAuxList().add(diagnostico);
            });

            for (PeAfiliadoDiagnostico diagnostico : nuevaLista) {
                if (diagnostico.getPrincipal()) {
                    tiene_pricipal = true;
                }
            }
            if (!tiene_pricipal && nuevaLista.size() > 0) {
                nuevaLista.get(0).setPrincipal(true);
            }
            getObjetoAfiliadosPrograma().setPeAfiliadoDiagnosticoList(nuevaLista);
            PrimeFaces.current().ajax().update("frmCrear:pDiagnosticoCrear");
            PrimeFaces.current().ajax().update("frmCrear:tablaDiagnosticosCrear");
            PrimeFaces.current().ajax().update("frmEditar:pDiagnosticoCrear");
            PrimeFaces.current().ajax().update("frmEditar:tablaDiagnosticosCrear");

        } else {
            addError("Debe existir al menos un diagnostico");
            generarMensajes();
        }
    }

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
            PrimeFaces.current().executeScript("PF('dialogoCrearContacto').hide()");
        } else {
            generarMensajes();
        }
    }

    private String validarTelefonosFijosNoPermitidos(String fijo) {
        String mensaje = "";
        if (fijo != null && !fijo.equals("") && !fijo.isBlank()) {
            if (PeConstantes.getNumerosFijosNoPermitidos().contains(fijo)) {
                mensaje = "el telefono fijo no puede contener una secuencia de números repetitivos, lo cual lo hace inválido.";
            }
        }
        return mensaje;
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

    public void retirarContacto(Integer id) {
        for (int i = 0; i < listaContactosPrograma.size(); i++) {
            PeTelefono agrup = listaContactosPrograma.get(i);
            if (agrup.getId() != null && agrup.getId() == id) {
                listaContactosPrograma.remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmAfiliado:tablaContactosCrearPro");
    }

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
    }

    public void mostrarMensaje(String mensaje) {
        setDescripcionGenerica(mensaje);
        PrimeFaces.current().resetInputs("frmObservacion");
        PrimeFaces.current().ajax().update("frmObservacion");
        PrimeFaces.current().executeScript("PF('dlgMsg').show();");
    }

    public String getNombreFuenteOrigenSugeridos(Integer valor) {
        return PeConstantes.getNombreFuenteOrigenSugeridos(valor);
    }

    public void mostrarAfiliadoGeneral() {
        PrimeFaces.current().resetInputs("frmAfiliadoGeneral");
        PrimeFaces.current().ajax().update("frmAfiliadoGeneral");
        PrimeFaces.current().executeScript("PF('dlgInfoAfiliado').show();");
    }

    public String obtenerBoolean(boolean bool) {
        return bool == true ? "Si" : "No";
    }

    public String calcularEdad(Date fecha) {
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaNacimiento = PeConstantes.formato5.format(fecha);
            Period periodo = Period.between(LocalDate.parse(fechaNacimiento, fmt), LocalDate.now());
            String texto = periodo.getYears() + " años " + periodo.getMonths() + " meses " + periodo.getDays() + " dias";
            return texto;
        } catch (Exception e) {
            return "";
        }
    }

    public void verBitacoras(AuAnexo3Item item) {
        getObjetoAnexo3().setObjetoTecnologia(item);
        PrimeFaces.current().executeScript("PF('dialogoVerBitacorass').show()");
        PrimeFaces.current().ajax().update("frmVerBitacoras");
        generarMensajes();
    }

    public void visualizarAuditoriaPrescipcion(AuAnexo3Item item) {
        this.getAuditoria(item.getAuditoriaStrHTML());
    }

    public String getMotivoEstado(PeAfiliadoSugerido objeto) {
        if (objeto.getObservacion() == null && objeto.getObservacionRechazo() != null) {
            return objeto.getObservacionRechazo();
        }
        if (objeto.getObservacionRechazo() == null && objeto.getObservacion() != null) {
            return objeto.getObservacion();
        }
        if (objeto.getObservacionRechazo() != null && objeto.getObservacion() != null) {
            return objeto.getObservacionRechazo();
        }
        return "";
    }

    public void descargarAdjuntoHospitalizacion(PeSugeridoAdjunto adjunto) {
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
        } catch (FileNotFoundException ex) {
            this.addError("No se encontró el archivo a descargar.");
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
        }
        procesoFinal();
    }

    public String getSexoAplicaPrograma(Integer sexoAplica) {
        return PeConstantes.getListaSexoAplicaDescripcion(sexoAplica);
    }

    private boolean validarSexoAplica() {
        if (getObjetoAfiliadosPrograma().getPePrograma().getSexoAplica() == PeConstantes.APLICA_SEXO_AMBOS) {
            return true;
        }
        if (!PeConstantes.getCodigoSexoAplica(getObjetoAfiliadosPrograma().getPePrograma().getSexoAplica()).equalsIgnoreCase(getObjetoAfiliadosPrograma().getAsegAfiliado().getMaeGeneroCodigo())) {
            addMensaje("El género del afiliado no corresponde al permitido por el programa, el programa " + getObjetoAfiliadosPrograma().getPePrograma().getDescripcionPrograma() + " solo permite género " + PeConstantes.getListaSexoAplicaDescripcion(this.getObjetoAfiliadosPrograma().getPePrograma().getSexoAplica()));
            return false;
        }
        return true;
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                    crearLog(getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmSugeridos");
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmSugeridos");
                    break;
                case Url.ACCION_VER:
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    crearLog("Hospitalización", getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (getDoAccion()) {
                        case Url.ACCION_CREAR:
                            crearLog("Crear sugerido", getObjeto().toString());
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().ajax().update("frmSugeridos");
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            crearLog("Rechazar sugerido", getObjeto().toString());
                            break;
                        case Url.ACCION_MODIFICAR:
                            PrimeFaces.current().ajax().update("frmSugeridos");
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (getDoAccion()) {
                        case Url.ACCION_EDITAR:
                            crearLog("Ver Hospitalizacion", getObjeto().toString());
                            break;
                        case Url.ACCION_MODIFICAR:
                            crearLog("Ver SolicitudAnexo3", getObjeto().toString());
                            break;
                    }
                    break;
            }
        }
        generarMensajes();
    }

}
