package com.saviasaludeps.savia.web.judicial.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.judicial.GjTercero;
import com.saviasaludeps.savia.dominio.judicial.GjTerceroContacto;
import com.saviasaludeps.savia.web.atencionusuario.bean.AusCasoBean;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import com.saviasaludeps.savia.web.judicial.servicio.GjTerceroServicioIface;
//import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import org.primefaces.model.SortMeta;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author bsgomez
 *
 */
@ManagedBean
@ViewScoped
public class GjTerceroBean extends Url {

    private GjTerceroServicioIface gjTerceroServicio;
    private GjTercero objeto;
    private List<GjTercero> registros;
    private LazyDataModel<GjTercero> lazyTercero;
    private LazyDataModel<GjTerceroContacto> lazyContactos;
//    @Autowired
//    private UbicacionSingle ubicacionSingle;

    private List<Maestro> maestros;

    private GjTerceroContacto afiliadoContacto;
    private List<GjTerceroContacto> listaafiliadoContacto;

    //disabled
    private boolean deshabilitarCampoRazonSocialTercero = false;
    private boolean deshabilitarCampoTipo = false;
    private boolean deshabilitarCampoTipoDocumento = false;
    private boolean deshabilitarCampoDocumento = false;
    private boolean deshabilitarCampoNombres = false;
    private boolean deshabilitarCampoApellidos = false;
    private boolean deshabilitarCampo = false;
    private boolean deshabilitarCampoApellido = false;

    private boolean terceroRegistradoEnSistema = false;

    //required
    private boolean requieredCampoRazonSocialTercero = true;
    private boolean requieredCampoApellido;

    //Listas Auxiliares
    private List<Maestro> listaTipoDocumento;
    private HashMap<Integer, Maestro> hashTipoDocumento;
    private List<Ubicacion> ubicaciones;
    private HashMap<Integer, Ubicacion> ubicacionesRecursiva;
    private List<Maestro> listaTipoContacto;
    private HashMap<Integer, Maestro> hashTipoContacto;

    //Constantes Auxiliares
    private static final String VALOR_TIPO_CONTACTO_CORREO = "3";
    public static final char BORRAR_TELEFONO_CONTACTO = 'a';
    public static final char LISTAR_TELEFONO_SELECCIONADO = 'o';
    public static final char EDITAR_CONTACTO = 's';

    public GjTerceroBean() {
        this.objeto = new GjTercero();
        Modulo _mod = super.validarModulo(Modulo.ID_GJ_TERCEROS);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyTercero = new LazyDataModel<GjTercero>() {

                private List<GjTercero> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<GjTercero> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    lista = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    //agrega registros hash datos

                    return lista;
                }

                @Override
                public String getRowKey(GjTercero objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public GjTercero getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (GjTercero objeto : lista) {
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
        getTerceroServicio().cargasInicial(this);
        listar();
    }

    public GjTerceroServicioIface getTerceroServicio() {
        return gjTerceroServicio;
    }

    public LazyDataModel<GjTercero> getLazyTercero() {
        return lazyTercero;
    }

    public void setLazyTercero(LazyDataModel<GjTercero> lazyTercero) {
        this.lazyTercero = lazyTercero;
    }

    public GjTercero getObjeto() {
        return objeto;
    }

    public void setObjeto(GjTercero objeto) {
        this.objeto = objeto;
    }

    public List<GjTercero> getRegistros() {

        return registros;
    }

    public LazyDataModel<GjTerceroContacto> getLazyContactos() {
        return lazyContactos;
    }

    public void setLazyContactos(LazyDataModel<GjTerceroContacto> lazyContactos) {
        this.lazyContactos = lazyContactos;
    }

    public void setRegistros(List<GjTercero> registros) {
        this.registros = registros;
    }

    public LazyDataModel<GjTercero> getLazyUsuario() {
        return lazyTercero;
    }

    public void setLazyUsuario(LazyDataModel<GjTercero> lazyTercero) {
        this.lazyTercero = lazyTercero;
    }

//    public UbicacionSingle getUbicacionSingle() {
//        return ubicacionSingle;
//    }
//
//    public void setUbicacionSingle(UbicacionSingle ubicacionSingle) {
//        this.ubicacionSingle = ubicacionSingle;
//    }
    public List<Maestro> getMaestros() {
        return maestros;
    }

    public void setMaestros(List<Maestro> maestros) {
        this.maestros = maestros;
    }

    public GjTerceroContacto getAfiliadoContacto() {
        if (afiliadoContacto == null) {
            afiliadoContacto = new GjTerceroContacto();
        }
        return afiliadoContacto;
    }

    public void setAfiliadoContacto(GjTerceroContacto afiliadoContacto) {
        this.afiliadoContacto = afiliadoContacto;
    }

    public List<GjTerceroContacto> getListaafiliadoContacto() {
        if (listaafiliadoContacto == null) {
            listaafiliadoContacto = new ArrayList<>();
        }
        return listaafiliadoContacto;
    }

    public void setListaafiliadoContacto(List<GjTerceroContacto> listaafiliadoContacto) {
        this.listaafiliadoContacto = listaafiliadoContacto;
    }

    public boolean isDeshabilitarCampoRazonSocialTercero() {
        return deshabilitarCampoRazonSocialTercero;
    }

    public void setDeshabilitarCampoRazonSocialTercero(boolean deshabilitarCampoRazonSocialTercero) {
        this.deshabilitarCampoRazonSocialTercero = deshabilitarCampoRazonSocialTercero;
    }

    public boolean isDeshabilitarCampoTipo() {
        return deshabilitarCampoTipo;
    }

    public void setDeshabilitarCampoTipo(boolean deshabilitarCampoTipo) {
        this.deshabilitarCampoTipo = deshabilitarCampoTipo;
    }

    public boolean isDeshabilitarCampoTipoDocumento() {
        return deshabilitarCampoTipoDocumento;
    }

    public void setDeshabilitarCampoTipoDocumento(boolean deshabilitarCampoTipoDocumento) {
        this.deshabilitarCampoTipoDocumento = deshabilitarCampoTipoDocumento;
    }

    public boolean isDeshabilitarCampoDocumento() {
        return deshabilitarCampoDocumento;
    }

    public void setDeshabilitarCampoDocumento(boolean deshabilitarCampoDocumento) {
        this.deshabilitarCampoDocumento = deshabilitarCampoDocumento;
    }

    public boolean isDeshabilitarCampoNombres() {
        return deshabilitarCampoNombres;
    }

    public void setDeshabilitarCampoNombres(boolean deshabilitarCampoNombres) {
        this.deshabilitarCampoNombres = deshabilitarCampoNombres;
    }

    public boolean isDeshabilitarCampoApellidos() {
        return deshabilitarCampoApellidos;
    }

    public void setDeshabilitarCampoApellidos(boolean deshabilitarCampoApellidos) {
        this.deshabilitarCampoApellidos = deshabilitarCampoApellidos;
    }

    public boolean isDeshabilitarCampo() {
        return deshabilitarCampo;
    }

    public void setDeshabilitarCampo(boolean deshabilitarCampo) {
        this.deshabilitarCampo = deshabilitarCampo;
    }

    public boolean isDeshabilitarCampoApellido() {
        return deshabilitarCampoApellido;
    }

    public void setDeshabilitarCampoApellido(boolean deshabilitarCampoApellido) {
        this.deshabilitarCampoApellido = deshabilitarCampoApellido;
    }

    public boolean isTerceroRegistradoEnSistema() {
        return terceroRegistradoEnSistema;
    }

    public void setTerceroRegistradoEnSistema(boolean terceroRegistradoEnSistema) {
        this.terceroRegistradoEnSistema = terceroRegistradoEnSistema;
    }

    public boolean isRequieredCampoRazonSocialTercero() {
        return requieredCampoRazonSocialTercero;
    }

    public void setRequieredCampoRazonSocialTercero(boolean requieredCampoRazonSocialTercero) {
        this.requieredCampoRazonSocialTercero = requieredCampoRazonSocialTercero;
    }

    public boolean isRequieredCampoApellido() {
        return requieredCampoApellido;
    }

    public void setRequieredCampoApellido(boolean requieredCampoApellido) {
        this.requieredCampoApellido = requieredCampoApellido;
    }

    public List<Maestro> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    public void setListaTipoDocumento(List<Maestro> listaTipoDocumento) {
        this.listaTipoDocumento = listaTipoDocumento;
    }

    public HashMap<Integer, Maestro> getHashTipoDocumento() {
        return hashTipoDocumento;
    }

    public void setHashTipoDocumento(HashMap<Integer, Maestro> hashTipoDocumento) {
        this.hashTipoDocumento = hashTipoDocumento;
    }

    public GjTerceroServicioIface getGjTerceroServicio() {
        return gjTerceroServicio;
    }

    public void setGjTerceroServicio(GjTerceroServicioIface gjTerceroServicio) {
        this.gjTerceroServicio = gjTerceroServicio;
    }

    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public HashMap<Integer, Ubicacion> getUbicacionesRecursiva() {
        return ubicacionesRecursiva;
    }

    public void setUbicacionesRecursiva(HashMap<Integer, Ubicacion> ubicacionesRecursiva) {
        this.ubicacionesRecursiva = ubicacionesRecursiva;
    }

    public List<Maestro> getListaTipoContacto() {
        return listaTipoContacto;
    }

    public void setListaTipoContacto(List<Maestro> listaTipoContacto) {
        this.listaTipoContacto = listaTipoContacto;
    }

    public HashMap<Integer, Maestro> getHashTipoContacto() {
        return hashTipoContacto;
    }

    public void setHashTipoContacto(HashMap<Integer, Maestro> hashTipoContacto) {
        this.hashTipoContacto = hashTipoContacto;
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getGjTerceroServicio().Accion(this);
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        setListaafiliadoContacto(new ArrayList<>());
        super.setAccion(ACCION_VER);
        getTerceroServicio().consultarUbicaciones(this);
        getTerceroServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().ajax().update("frmVer:panelVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        setListaafiliadoContacto(new ArrayList<>());
        getTerceroServicio().Accion(this);
        getTerceroServicio().consultarUbicaciones(this);
         crearLog(getObjeto().toString());
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getTerceroServicio().consultarTerCero(this);
        if (isTerceroRegistradoEnSistema() != true) {
            getTerceroServicio().Accion(this);
        }
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        inicializarTablaContacto(_id);
        super.setAccion(ACCION_EDITAR);
        getTerceroServicio().Accion(this);
        getTerceroServicio().consultarUbicaciones(this);
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getTerceroServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void modificarContacto() {
        super.setAccion(ACCION_BORRAR);
        super.setDoAccion(GjTerceroBean.EDITAR_CONTACTO);
        getTerceroServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmEditar:tablaContactoEditar");
            PrimeFaces.current().ajax().update("frmEditar");
            PrimeFaces.current().executeScript("PF('dialogoEditarContactoT').hide();");
        }
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        super.setDoAccion(ACCION_BORRAR);
        getTerceroServicio().Accion(this);
         crearLog("Borrar", getObjeto().toString());
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                    crearLog(getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmTerceros");
                    break;
                case Url.ACCION_GUARDAR:
                     crearLog(getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmTerceros");
                    break;
                case Url.ACCION_MODIFICAR:
                     crearLog(getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmTerceros");
                    break;
                case Url.ACCION_BORRAR:
                    switch (getDoAccion()) {
                        case Url.ACCION_BORRAR:
                           
                            PrimeFaces.current().ajax().update("frmTerceros");
                            break;
                        case GjTerceroBean.EDITAR_CONTACTO:
                            PrimeFaces.current().ajax().update("tablaContactoEditar");
                            break;
                        case GjTerceroBean.LISTAR_TELEFONO_SELECCIONADO:
                            PrimeFaces.current().executeScript("PF('dialogoEditarContactoT').show()");
                            PrimeFaces.current().ajax().update("frmEditarContacto");
                            break;
                    }
                    break;
                case Url.ACCION_VER:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_CREAR:
                    PrimeFaces.current().resetInputs("frmCrear");
                    requieredCampoApellido = true;
                    deshabilitarCampoApellidos = false;
                    deshabilitarCampoRazonSocialTercero = false;
                    PrimeFaces.current().ajax().update("frmCrear");
                    PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
                    break;
                case Url.ACCION_EDITAR:
                    PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
                    requieredCampoApellido = !(" ".equals(objeto.getApellidos()) || objeto.getApellidos() == null);
                    if (objeto.getApellidos().trim().isEmpty()) {
                        objeto.setApellidos("N/A");
                    }
                    PrimeFaces.current().ajax().update("frmEditar");
                    crearLog(getObjeto().toString());
                    break;

            }
        }
        generarMensajes();
    }

    public void inicializarTablaContacto(int id) {
        this.setParamConsulta(new ParamConsulta());
        this.getParamConsulta().setParametroConsulta2(id);
        lazyContactos = new LazyDataModel<GjTerceroContacto>() {
            private List<GjTerceroContacto> contacto;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<GjTerceroContacto> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta().setPrimerRegistro(primerRegistro);
                getParamConsulta().setRegistrosPagina(registrosPagina);
                getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarContactos();
                contacto = getListaafiliadoContacto();
                setRowCount(getParamConsulta().getCantidadRegistros());
                return contacto;
            }

            @Override
            public String getRowKey(GjTerceroContacto persona) {
                return persona.getId().toString();
            }

            @Override
            public GjTerceroContacto getRowData(String personaId
            ) {
                Integer id = Integer.valueOf(personaId);
                for (GjTerceroContacto persona : contacto) {
                    if (id.equals(persona.getId())) {
                        return persona;
                    }
                }
                return null;
            }
        };
    }

    public void refrescarContactos() {
        super.setAccion(Url.ACCION_BORRAR);
        super.setDoAccion(GjTerceroBean.BORRAR_TELEFONO_CONTACTO);
        getGjTerceroServicio().Accion(this);
    }

    public String obtenerDepartamento(int id) {
        try {
            int idPadre = ubicacionesRecursiva.get(id).getUbicacionPadre().getId();
            return ubicacionesRecursiva.get(idPadre).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public String obtenerMunicipio(int id) {
        try {
            return ubicacionesRecursiva.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public void validarTipoDoc() {
        if (this.getObjeto().getMaeTipoDocumentoId() != 10) {
            this.getObjeto().setRazonSocial("N/A");
            deshabilitarCampoRazonSocialTercero = true;
            requieredCampoRazonSocialTercero = false;
        } else {
            deshabilitarCampoRazonSocialTercero = false;
            requieredCampoRazonSocialTercero = true;
            this.getObjeto().setRazonSocial("");
        }
        PrimeFaces.current().ajax().update("frmCrear:panelCrear");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar");
    }

    public void validartipo() {
        short tip = getObjeto().getTipo();
        if (tip == 1 || tip == 2) {
            deshabilitarCampoTipo = true;
            deshabilitarCampoTipoDocumento = true;
            deshabilitarCampoNombres = true;
            deshabilitarCampoApellidos = true;
            deshabilitarCampoRazonSocialTercero = true;
            deshabilitarCampoDocumento = true;
        } else {
            deshabilitarCampoTipo = false;
            deshabilitarCampoTipoDocumento = false;
            deshabilitarCampoNombres = false;
            deshabilitarCampoApellidos = false;
            deshabilitarCampoRazonSocialTercero = false;
            deshabilitarCampoDocumento = false;
        }
    }

    public void buscarPersonaEnSistema() {
        if (this.getObjeto().getTipo() != 0) {
            switch (this.getObjeto().getTipo()) {
                case 1:
                    buscarAfiliadoEnSistema();
                    break;
                case 2:
                    buscarPrestadorEnSistema();

                    break;
            }
        }
    }

    public void validarEnteros(java.awt.event.KeyEvent telefono) {
        char c = telefono.getKeyChar();
        if (c >= '0' || c <= '9') {
            telefono.consume();
        }

    }

    public void validarTercero(int tipo) {
        if (tipo != 10) {
            deshabilitarCampoRazonSocialTercero = true;
            requieredCampoRazonSocialTercero = false;
        } else {
            deshabilitarCampoRazonSocialTercero = false;
            requieredCampoRazonSocialTercero = true;
        }

        PrimeFaces.current().ajax().update("frmEditar:panelEditar");
    }

    public void buscarAfiliadoEnSistema() {
        if (this.getObjeto().getMaeTipoDocumentoId() > 0
                && this.getObjeto().getDocumento() != null) {
            try {
                //REFRESCAR BÚSQUEDA DE PERSONA
//debe estar inicializado asegafiliado  * por el momento para que deje registrar esta No inicializado
                this.getObjeto().getAsegAfiliado().setId(0);
                if (!getHashTipoDocumento().isEmpty() && getHashTipoDocumento() != null) {
                    Maestro tipoDocumento = getHashTipoDocumento().get(this.getObjeto().getMaeTipoDocumentoId());
                    if (tipoDocumento != null) {
                        this.getObjeto().setMaeTipoDocumentoCodigo(tipoDocumento.getValor());
                        this.getObjeto().setMaeTipoDocumentoValor(tipoDocumento.getNombre());
                    }
                }
                //Consultar afiliado en BD
                getGjTerceroServicio().consultarPersonaAfiliada(this);
                //getPersonaServicio().consultarPersona(personaBean);
                //desde aca inicia la excepcion
                asignacionResultadosConsultaParaBeanCaso();
                if (!this.getObjeto().exitePersona()) {
                    //Consultar persona en BD
                    getGjTerceroServicio().consultarPersona(this);
                    if (this.getErrores().size() > 0) {
                        this.addError(this.getErrores().get(0));
                        this.getErrores().clear();
                    }
                    asignacionResultadosConsultaParaBeanCaso();
                }
                if (!this.getObjeto().getGjPersona().exitePersona() && !existePersonaAfiliada()) {//aca esta saltando a la excepcion
                    String documento = this.getObjeto().getGjPersona().getDocumento();
                    int tipoDoc = this.getObjeto().getGjPersona().getMaeTipoDocumentoId();
                    this.getObjeto().setGjPersona(new GjTercero());
                    this.getObjeto().getGjPersona().setMaeTipoDocumentoId(tipoDoc);
                    this.getObjeto().getGjPersona().setDocumento(documento);
                    //this.getObjeto().getGjPersona().setMaeEstadoAfiliadoId(Integer.parseInt(PropTutelasUsuario.getInstance().get(PropTutelasUsuario.ESTADO_PERSONA_DEFECTO_CREAR)));
                    //this.listaPersonaTelefono = new ArrayList<>();
                }
                PrimeFaces.current().ajax().update("frmCrear:panelCrear");
                PrimeFaces.current().ajax().update("frmEditar:panelEditar");
                //PrimeFaces.current().ajax().update("frmCrear:telefonos");
                //PrimeFaces.current().ajax().update("frmEditar");
                generarMensajes();
            } catch (Exception ex) {
                Logger.getLogger(AusCasoBean.class.getName()).log(Level.SEVERE, null, ex);
                this.addError(ex.getMessage());
                generarMensajes();
            }
        }
    }

    public void buscarPrestadorEnSistema() {
        if (this.getObjeto().getMaeTipoDocumentoId() > 0
                && this.getObjeto().getDocumento() != null) {
            try {
                //REFRESCAR BÚSQUEDA DE PERSONA
                this.getObjeto().getCntPrestador().setId(0);
                if (!getHashTipoDocumento().isEmpty() && getHashTipoDocumento() != null) {
                    Maestro tipoDocumento = getHashTipoDocumento().get(this.getObjeto().getMaeTipoDocumentoId());
                    if (tipoDocumento != null) {
                        this.getObjeto().setMaeTipoDocumentoCodigo(tipoDocumento.getValor());
                        this.getObjeto().setMaeTipoDocumentoValor(tipoDocumento.getNombre());
                    }
                }
                //Consultar prestador en BD
                getGjTerceroServicio().consultarPrestadorAfiliado(this);
                asignacionResultadosConsulta();
                if (!this.getObjeto().exitePersona()) {
                    //Consultar persona en BD
                    getGjTerceroServicio().consultarPersona(this);
                    if (this.getErrores().size() > 0) {
                        this.addError(this.getErrores().get(0));
                        this.getErrores().clear();
                    }
                    asignacionResultadosConsulta();
                }
                if (!this.getObjeto().getGjPersona().exitePersona() && !existePersonaAfiliada()) {//aca esta saltando a la excepcion
                    String documento = this.getObjeto().getGjPersona().getDocumento();
                    int tipoDoc = this.getObjeto().getGjPersona().getMaeTipoDocumentoId();
                    this.getObjeto().setGjPersona(new GjTercero());
                    this.getObjeto().getGjPersona().setMaeTipoDocumentoId(tipoDoc);
                    this.getObjeto().getGjPersona().setDocumento(documento);
                }
                objeto.setApellidos("N/A");

                requieredCampoApellido = false;
                deshabilitarCampoApellidos = true;
                deshabilitarCampoRazonSocialTercero = true;
                PrimeFaces.current().ajax().update("frmCrear");
                PrimeFaces.current().ajax().update("frmEditar:");
                //PrimeFaces.current().ajax().update("frmCrear:telefonos");
                //PrimeFaces.current().ajax().update("frmEditar");
                generarMensajes();
            } catch (Exception ex) {
                Logger.getLogger(AusCasoBean.class.getName()).log(Level.SEVERE, null, ex);
                this.addError(ex.getMessage());
                generarMensajes();
            }
        }
    }

    public List<Ubicacion> completarUbicacion(String query) {
        List<Ubicacion> ubicacionesFiltradas = new ArrayList<>();
        for (Ubicacion ubicacion : this.getUbicaciones()) {
            if (ubicacion.getNombre().toLowerCase().contains(query.toLowerCase())) {
                ubicacionesFiltradas.add(ubicacion);
            }
        }
//        if (ubicacionesFiltradas.size() == 1) {
//            getObjeto().setPersonaUbicacion(ubicacionesFiltradas.get(0));
//        }
        return ubicacionesFiltradas;
    }

    private boolean existePersonaAfiliada() {
        return this.getObjeto().getEsAfiliado();
    }

    private void asignacionResultadosConsultaParaBeanCaso() {
        this.getObjeto().setAsegAfiliado(this.getObjeto().getAsegAfiliado());//aca debe traerme toda la informacion de la persona consultada
        //this.listaPersonaTelefono = personaBean.get ListaausPersonaTelefono();
        //this.listaPersonaTelefono = personaBean.getObjeto().getListaTelefonos();
    }

    private void asignacionResultadosConsulta() {
        this.getObjeto().setCntPrestador(this.getObjeto().getCntPrestador());//aca debe traerme toda la informacion de la persona consultada
        //this.listaPersonaTelefono = personaBean.get ListaausPersonaTelefono();
        //this.listaPersonaTelefono = personaBean.getObjeto().getListaTelefonos();
    }

    public String getUbicacionRecursiva(int id) {
        String ubicacionStr = "";
        if (ubicacionesRecursiva != null && !ubicacionesRecursiva.isEmpty()) {
            Ubicacion _municipio = ubicacionesRecursiva.get(id);
            if (_municipio != null && _municipio.getUbicacionPadre() != null) {
                Ubicacion _departamento = _municipio.getUbicacionPadre();
                if (_departamento.getUbicacionPadre() != null) {
                    Ubicacion _pais = _departamento.getUbicacionPadre();
                    ubicacionStr = _pais.getNombre();
                }
                ubicacionStr = _departamento.getNombre() + " - " + ubicacionStr;
                ubicacionStr = _municipio.getNombre() + " - " + ubicacionStr;
            }
        }
        return ubicacionStr;
    }

    public String obtenerMunicipioDepartamento(int id) {
        try {
            String municipio = obtenerMunicipio(id);
            String departamento = obtenerDepartamento(id);
            return municipio + " - " + departamento;
        } catch (Exception e) {
            return "";
        }
    }

    public void crearContacto() {
        setAfiliadoContacto(new GjTerceroContacto());
        PrimeFaces.current().resetInputs("frmCrearContacto:panelCrearContacto");
        PrimeFaces.current().ajax().update("frmCrearContacto:panelCrearContacto");
        PrimeFaces.current().executeScript("PF('dialogoCrearContacto').show()");
    }

    public void borrarContacto(GjTerceroContacto personaContacto) {
        List<GjTerceroContacto> listaContactos = new ArrayList<>();
        int posicionEliminar = personaContacto.getPosicion();
        int posicionNueva = 0;
        for (GjTerceroContacto contacto : getListaafiliadoContacto()) {
            if (contacto.getPosicion() != posicionEliminar) {
                contacto.setPosicion(posicionNueva++);
                listaContactos.add(contacto);
            }
        }
        if (personaContacto.getId() != null) {
            this.getAfiliadoContacto().setId(personaContacto.getId());
            super.setAccion(Url.ACCION_BORRAR);
            super.setDoAccion(BORRAR_TELEFONO_CONTACTO);
            getGjTerceroServicio().Accion(this);
        }
        setListaafiliadoContacto(listaContactos);
        addMensaje("Se ha realizado la eliminación del contacto");
        generarMensajes();
        PrimeFaces.current().ajax().update("frmCrear:tablaContactoCrear");
    }

    public void adicionarContacto() {
        int longitudMaximaNumero = 10;
        boolean esContactoValido = true;
        GjTerceroContacto personaContacto = getAfiliadoContacto();
        Maestro tipoContacto = getHashTipoContacto().get(personaContacto.getMaeTipoContactoId());
        if (tipoContacto != null) {
            personaContacto.setMaeTipoContactoCodigo(tipoContacto.getValor());
            personaContacto.setMaeTipoContactoValor(tipoContacto.getNombre());
        }
        for (GjTerceroContacto contacto : getListaafiliadoContacto()) {
            if (contacto.getContacto().equals(personaContacto.getContacto())) {
                esContactoValido = false;
                addError("El contacto ya existe");
                break;
            }
        }
        if (personaContacto.getMaeTipoContactoCodigo().equals(VALOR_TIPO_CONTACTO_CORREO)) {
            if (!personaContacto.getContacto().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-xZa-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
                addError("El correo no es valido");
                esContactoValido = false;
            }
        } else {
            if (esContactoValido && personaContacto.getContacto().length() > longitudMaximaNumero) {
                addError("El número de contacto excede la longitud de caracteres : " + longitudMaximaNumero);
                esContactoValido = false;
            }
            if (!personaContacto.getContacto().matches("\\d*")) {
                addError("El número de contacto tiene letras");
                esContactoValido = false;
            }
        }
        if (esContactoValido) {
            personaContacto.setPosicion(getListaafiliadoContacto().size());
            getListaafiliadoContacto().add(personaContacto);
        }
        if (!isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrearContacto').hide();");
            PrimeFaces.current().ajax().update("frmCrear:tablaContactoCrear");
            PrimeFaces.current().ajax().update("frmEditar:tablaContactoEditar");
        }
        generarMensajes();
    }

    public void borrarContactoEditar(int contacto) {
        getObjeto().getGjTerceroContacto().setId(contacto);
        super.setAccion(Url.ACCION_BORRAR);
        super.setDoAccion(LISTAR_TELEFONO_SELECCIONADO);
        getGjTerceroServicio().Accion(this);
        procesoFinal();
    }
}
