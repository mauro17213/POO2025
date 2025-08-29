package com.saviasaludeps.savia.web.juridico.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.juridico.CntjGrupo;
import com.saviasaludeps.savia.dominio.juridico.CntjProceso;
import com.saviasaludeps.savia.dominio.juridico.CntjUsuarioGrupo;
import com.saviasaludeps.savia.web.juridico.servicio.GrupoServicioIface;
import com.saviasaludeps.savia.web.juridico.utilidades.CntjConstantes;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author idbohorquez
 */
@ManagedBean
@ViewScoped
public class GrupoBean extends Url {

    private GrupoServicioIface grupoServicio;
    private CntjGrupo objeto;
    private CntjUsuarioGrupo objetoUsuarioGrupo;

    private LazyDataModel<CntjGrupo> lazyGrupos;
    private LazyDataModel<Usuario> lazyUsuarios;
    private List<CntjGrupo> registros;
    private List<Usuario> listaUsuario;
    private List<CntjUsuarioGrupo> listaUsuarioGrupo;

    private Integer tipoSelectUsuario;

    public GrupoBean() {
        this.objeto = new CntjGrupo();
        this.objetoUsuarioGrupo = new CntjUsuarioGrupo();
        Modulo _mod = super.validarModulo(Modulo.ID_CNTJ_GRUPOS);
        if (_mod == null) {
            super.redireccionarHome();
        } else {
            super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            super.setModulo(_mod);
            super.addListaParamConsultas(new ParamConsulta());
            super.addListaParamConsultas(new ParamConsulta());
            lazyGrupos = new LazyDataModel<CntjGrupo>() {

                private List<CntjGrupo> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CntjGrupo> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(CntjGrupo objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public CntjGrupo getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CntjGrupo objeto : lista) {
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
    }

    //getter and setter
    public GrupoServicioIface getGrupoServicio() {
        return grupoServicio;
    }

    public void setGrupoServicio(GrupoServicioIface grupoServicio) {
        this.grupoServicio = grupoServicio;
    }

    public CntjGrupo getObjeto() {
        return objeto;
    }

    public void setObjeto(CntjGrupo objeto) {
        this.objeto = objeto;
    }

    public LazyDataModel<CntjGrupo> getLazyGrupos() {
        return lazyGrupos;
    }

    public void setLazyGrupos(LazyDataModel<CntjGrupo> lazyGrupos) {
        this.lazyGrupos = lazyGrupos;
    }

    public List<CntjGrupo> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CntjGrupo> registros) {
        this.registros = registros;
    }

    public LazyDataModel<Usuario> getLazyUsuarios() {
        return lazyUsuarios;
    }

    public void setLazyUsuarios(LazyDataModel<Usuario> lazyUsuarios) {
        this.lazyUsuarios = lazyUsuarios;
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public List<CntjUsuarioGrupo> getListaUsuarioGrupo() {
        return listaUsuarioGrupo;
    }

    public void setListaUsuarioGrupo(List<CntjUsuarioGrupo> listaUsuarioGrupo) {
        this.listaUsuarioGrupo = listaUsuarioGrupo;
    }

    public Integer getTipoSelectUsuario() {
        return tipoSelectUsuario;
    }

    public void setTipoSelectUsuario(Integer tipoSelectUsuario) {
        this.tipoSelectUsuario = tipoSelectUsuario;
    }

    public CntjUsuarioGrupo getObjetoUsuarioGrupo() {
        return objetoUsuarioGrupo;
    }

    public void setObjetoUsuarioGrupo(CntjUsuarioGrupo objetoUsuarioGrupo) {
        this.objetoUsuarioGrupo = objetoUsuarioGrupo;
    }

    //metodos
    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getGrupoServicio().Accion(this);
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getGrupoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getGrupoServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getGrupoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_EDITAR);
        getGrupoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getGrupoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void verListaUsuario(int tipoSeleccion) {
        this.tipoSelectUsuario = tipoSeleccion;
        cargarLazyUsuario();
        PrimeFaces.current().executeScript("PF('dialogoUsuariosLista').show()");
        PrimeFaces.current().ajax().update("frmUsuariosLista:tablaRegistrosUsuarios");
    }

    public void refrescarUsuarios() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_LISTAR);
        getGrupoServicio().Accion(this);
    }

    private void cargarLazyUsuario() {
        lazyUsuarios = new LazyDataModel<Usuario>() {
            private List<Usuario> listaUsuario;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(1).getCantidadRegistros();
            }

            @Override
            public List<Usuario> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(1).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(1).setPrimerRegistro(primerRegistro);
                getParamConsulta(1).setRegistrosPagina(registrosPagina);
                getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(1).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarUsuarios();
                listaUsuario = getListaUsuario();
                setRowCount(getParamConsulta(1).getCantidadRegistros());
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
        CntjUsuarioGrupo usuarioGrupo = new CntjUsuarioGrupo(-1);
        usuarioGrupo.setGnUsuarioId(usuario);

        boolean existe = objeto.getListaUsuarioGrupo().stream()
                .anyMatch(elemento -> Objects.equals(elemento.getGnUsuarioId().getId(), usuario.getId()));
        if (existe) {
            this.addError("El usuario que seleccionó ya se encuentra asociado al grupo.");
        } else {
            if (tipoSelectUsuario == CntjConstantes.SELECCION_CREAR) {
                this.objeto.getListaUsuarioGrupo().add(usuarioGrupo);
                PrimeFaces.current().ajax().update("frmCrear:tblUsuGrupo");
            } else {
                usuarioGrupo.setCntjGruposId(objeto);
                this.objeto.getListaUsuarioGrupo().add(usuarioGrupo);
                super.setAccion(ACCION_ADICIONAL_1);
                super.setDoAccion(Url.ACCION_GUARDAR);
                getGrupoServicio().Accion(this);
                PrimeFaces.current().ajax().update("frmEditar:tblEditUsuGrupo");
            }
            PrimeFaces.current().executeScript("PF('dialogoUsuariosLista').hide()");
        }
        procesoFinal();
    }

    public void quitarUsuario(CntjUsuarioGrupo cntjUsuarioGrupo) {
        this.objeto.getListaUsuarioGrupo().removeIf(elemento -> elemento.getGnUsuarioId().getId() == cntjUsuarioGrupo.getGnUsuarioId().getId());
    }

    public void eliminarUsuarioGrupo(int id) {
        this.objetoUsuarioGrupo.setId(id);
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_BORRAR);
        getGrupoServicio().Accion(this);
        if (!super.isError()) {
            this.getObjeto().getListaUsuarioGrupo().removeIf(elemento -> elemento.getId() == id);
            PrimeFaces.current().ajax().update("frmEditar:tblEditUsuGrupo");
        }
        procesoFinal();
    }

    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_LISTAR:
                //crearLog(getObjetoGestion().toString());
                PrimeFaces.current().ajax().update("frmGestion");
                break;
            case Url.ACCION_GUARDAR:
            case Url.ACCION_MODIFICAR:
            case Url.ACCION_BORRAR:
                PrimeFaces.current().ajax().update("frmGestion");
                break;
            case Url.ACCION_VER:
            case Url.ACCION_CREAR:
            case Url.ACCION_EDITAR:
                //crearLog(getObjeto().toString());
                break;
        }
        generarMensajes();
    }

}
