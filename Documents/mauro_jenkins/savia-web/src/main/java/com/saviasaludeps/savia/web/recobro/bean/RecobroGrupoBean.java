package com.saviasaludeps.savia.web.recobro.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.especial.PePrograma;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.recobro.RcoGrupo;
import com.saviasaludeps.savia.dominio.recobro.RcoGrupoUsuario;
import com.saviasaludeps.savia.web.recobro.servicio.RecobroGrupoServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author Stiven Giraldo
 */
@ManagedBean
@ViewScoped
public class RecobroGrupoBean extends Url {

    @Autowired
    private RecobroGrupoServicioIface rcoGrupoServicio;
    private RcoGrupo objeto;
    private List<RcoGrupo> registros;
    private LazyDataModel<RcoGrupo> lazyRcoGrupos;

    private LazyDataModel<Usuario> lazyUsuarios;
    private ParamConsulta paramConsultaUsuarios;
    private List<Usuario> registrosUsuarios;
    private Usuario objetoUsuario;

    private List<RcoGrupoUsuario> RegistrosUsuariosborrar;

    //Auxiliares
    private List<PePrograma> listaProgramas;

    public RecobroGrupoBean() {
        this.objeto = new RcoGrupo();
        Modulo _mod = super.validarModulo(Modulo.ID_RCO_GRUPOS);
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyRcoGrupos = new LazyDataModel<RcoGrupo>() {

                private List<RcoGrupo> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<RcoGrupo> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(RcoGrupo objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public RcoGrupo getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (RcoGrupo objeto : lista) {
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
        getRcoGrupoServicio().cargaInicial(this);
        listar();
    }

    public RecobroGrupoServicioIface getRcoGrupoServicio() {
        return rcoGrupoServicio;
    }

    public void setRcoGrupoServicio(RecobroGrupoServicioIface rcoGrupoServicio) {
        this.rcoGrupoServicio = rcoGrupoServicio;
    }

    public RcoGrupo getObjeto() {
        return objeto;
    }

    public void refrescarUsuarios() {
        getRcoGrupoServicio().listarUsuarios(this);
    }

    public List<Usuario> getRegistrosUsuarios() {
        if (registrosUsuarios == null) {
            registrosUsuarios = new ArrayList<>();
        }
        return registrosUsuarios;
    }

    public void setRegistrosUsuarios(List<Usuario> RegistrosUsuarios) {
        this.registrosUsuarios = RegistrosUsuarios;
    }

    public void setObjeto(RcoGrupo objeto) {
        this.objeto = objeto;
    }

    public List<RcoGrupo> getRegistros() {
        return registros;
    }

    public void setRegistros(List<RcoGrupo> registros) {
        this.registros = registros;
    }

    public LazyDataModel<RcoGrupo> getLazyRcoGrupos() {
        return lazyRcoGrupos;
    }

    public void setLazyRcoGrupos(LazyDataModel<RcoGrupo> lazyRcoGrupos) {
        this.lazyRcoGrupos = lazyRcoGrupos;
    }

    public List<PePrograma> getListaProgramas() {
        return listaProgramas;
    }

    public void setListaProgramas(List<PePrograma> listaProgramas) {
        this.listaProgramas = listaProgramas;
    }

    public LazyDataModel<Usuario> getLazyUsuarios() {
        return lazyUsuarios;
    }

    public void setLazyUsuarios(LazyDataModel<Usuario> lazyUsuarios) {
        this.lazyUsuarios = lazyUsuarios;
    }

    public ParamConsulta getParamConsultaUsuarios() {
        return paramConsultaUsuarios;
    }

    public void setParamConsultaUsuarios(ParamConsulta paramConsultaUsuarios) {
        this.paramConsultaUsuarios = paramConsultaUsuarios;
    }

    public Usuario getObjetoUsuario() {
        return objetoUsuario;
    }

    public void setObjetoUsuario(Usuario objetoUsuario) {
        this.objetoUsuario = objetoUsuario;
    }

    public List<RcoGrupoUsuario> getRegistrosUsuariosborrar() {
        if (RegistrosUsuariosborrar == null) {
            RegistrosUsuariosborrar = new ArrayList<>();
        }
        return RegistrosUsuariosborrar;
    }

    public void setRegistrosUsuariosborrar(List<RcoGrupoUsuario> RegistrosUsuariosborrar) {
        this.RegistrosUsuariosborrar = RegistrosUsuariosborrar;
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getRcoGrupoServicio().Accion(this);
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getRcoGrupoServicio().Accion(this);
        procesoFinal();
    }

    public void verUsuarios() {
        inicializarLazyUsuario();
        PrimeFaces.current().executeScript("PF('dialogoUsuariosLista').show()");
        PrimeFaces.current().ajax().update("frmUsuariosLista");
    }

    public void guardar() {
        super.setAccion(Url.ACCION_GUARDAR);
        getRcoGrupoServicio().Accion(this);
        if (!isError()) {
            addMensaje("Se ha creado un nuevo grupo " + this.getObjeto().getNombre());
        }
        procesoFinal();
    }

    public void ver(int id) {
        setObjeto(new RcoGrupo(id));
        super.setAccion(ACCION_VER);
        getRcoGrupoServicio().Accion(this);
        procesoFinal();
    }

    public void editar(int id) {
        setObjeto(new RcoGrupo(id));
        super.setAccion(ACCION_EDITAR);
        getRcoGrupoServicio().Accion(this);
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getRcoGrupoServicio().Accion(this);
        procesoFinal();
    }

    public void borrar(int id) {
        setObjeto(new RcoGrupo(id));
        super.setAccion(ACCION_BORRAR);
        getRcoGrupoServicio().Accion(this);
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_GUARDAR:
                    PrimeFaces.current().ajax().update("frmGrupos");
                    PrimeFaces.current().executeScript("PF('dialogoCrearGrupo').hide()");
                    break;
                case Url.ACCION_MODIFICAR:
                    PrimeFaces.current().ajax().update("frmGrupos");
                    PrimeFaces.current().executeScript("PF('dialogoEditarGrupo').hide()");
                    break;
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmGrupos");
                    break;
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmGrupos");
                    break;
                case Url.ACCION_CREAR:
                    PrimeFaces.current().ajax().update("frmCrearGrupo");
                    PrimeFaces.current().executeScript("PF('dialogoCrearGrupo').show()");
                    break;
                case Url.ACCION_EDITAR:
                    PrimeFaces.current().ajax().update("frmEditarGrupo");
                    PrimeFaces.current().executeScript("PF('dialogoEditarGrupo').show()");
                    break;
                case Url.ACCION_VER:
                    PrimeFaces.current().ajax().update("frmverGrupo");
                    PrimeFaces.current().executeScript("PF('dialogoVerGrupo').show()");
                    break;
            }
        }
        generarMensajes();
    }

    public void inicializarLazyUsuario() {
        setParamConsultaUsuarios(new ParamConsulta());

        lazyUsuarios = new LazyDataModel<Usuario>() {

            private List<Usuario> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaUsuarios().getCantidadRegistros();
            }

            @Override
            public List<Usuario> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                setParamConsultaUsuarios(new ParamConsulta());
                getParamConsultaUsuarios().setPrimerRegistro(primerRegistro);
                getParamConsultaUsuarios().setRegistrosPagina(registrosPagina);
                getParamConsultaUsuarios().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaUsuarios().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarUsuarios();
                lista = getRegistrosUsuarios();
                setRowCount(getParamConsultaUsuarios().getCantidadRegistros());
                return lista;
            }

            public String getRowKey(Usuario objeto) {
                return objeto.getId().toString();
            }

            @Override
            public Usuario getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (Usuario objeto : lista) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };
    }

    public void onRowSelectUsuarios(SelectEvent event) {
        Usuario usuario = (Usuario) event.getObject();
        boolean agregar = true;
        for (RcoGrupoUsuario user : getObjeto().getListaRcoGrupoUsuario()) {
            if (user.getGnUsuarioId().getId().equals(usuario.getId())) {
                agregar = false;
            }
        }
        if (agregar) {
            RcoGrupoUsuario grupoUsuario = new RcoGrupoUsuario();
            grupoUsuario.setActivo(true);
            grupoUsuario.setGnUsuarioId(usuario);
            getObjeto().getListaRcoGrupoUsuario().add(grupoUsuario);
            PrimeFaces.current().ajax().update("frmCrearGrupo:pUsuariosCrear");
            PrimeFaces.current().ajax().update("frmEditarGrupo:pUsuariosEditar");
            PrimeFaces.current().executeScript("PF('dialogoUsuariosLista').hide()");
        } else {
            addError("El usuario ya se encuentra agregado");
            generarMensajes();
        }
    }

    public void borrarUsuario(RcoGrupoUsuario usuarioEliminar) {
        List<RcoGrupoUsuario> nuevaLista = new ArrayList();
        for (RcoGrupoUsuario usuario : getObjeto().getListaRcoGrupoUsuario()) {
            if (usuario.getGnUsuarioId().getId().equals(usuarioEliminar.getGnUsuarioId().getId())) {
                if (usuario.getId() != null) {
                    getRegistrosUsuariosborrar().add(usuario);
                    addMensaje("El borrado se hara el modificar");
                }
            } else {
                nuevaLista.add(usuario);
            }
        }
        getObjeto().setlistaRcoGrupoUsuario(nuevaLista);
        PrimeFaces.current().ajax().update("frmCrearGrupo:tablaAuditoresCrear");
        PrimeFaces.current().ajax().update("frmEditarGrupo:tablaAuditoresEditar");
        generarMensajes();
    }

}
