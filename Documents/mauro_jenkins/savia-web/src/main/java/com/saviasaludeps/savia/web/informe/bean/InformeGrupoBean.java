package com.saviasaludeps.savia.web.informe.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.informe.InfGrupo;
import com.saviasaludeps.savia.dominio.informe.InfGrupoUsuario;
import com.saviasaludeps.savia.web.informe.servicio.InformeGrupoServicioIface;
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
import org.primefaces.model.SortMeta;

@ManagedBean
@ViewScoped
public class InformeGrupoBean extends Url {

    private InformeGrupoServicioIface informeGrupoServicio;
    private InfGrupo objeto;
    private InfGrupoUsuario objetoUsuario;
    private List<InfGrupo> registros;
    private LazyDataModel<InfGrupo> lazyGrupo;

    private List<Usuario> listaGnUsuarios;
    private ParamConsulta paramConsultaGrupo;
    private HashMap<Integer, Usuario> usuarioRecursiva;
    private List<InfGrupo> listaGrupos;
    private List<InfGrupoUsuario> listaFiltroUsuario;
    private String valorFiltro;

    public InformeGrupoBean() {
        this.objeto = new InfGrupo();
        this.objetoUsuario = new InfGrupoUsuario();
        Modulo _mod = super.validarModulo(Modulo.ID_INFORMES_GRUPOS);
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            paramConsultaGrupo = new ParamConsulta();
            lazyGrupo = new LazyDataModel<InfGrupo>() {

                private List<InfGrupo> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsultaGrupo().getCantidadRegistros();
                }

                @Override
                public List<InfGrupo> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsultaGrupo().setPrimerRegistro(primerRegistro);
                    getParamConsultaGrupo().setRegistrosPagina(registrosPagina);
                    getParamConsultaGrupo().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsultaGrupo().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    lista = getRegistros();
                    setRowCount(getParamConsultaGrupo().getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(InfGrupo objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public InfGrupo getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (InfGrupo objeto : lista) {
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
        getInformeGrupoServicio().cargaInicial(this);
        listar();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getInformeGrupoServicio().Accion(this);
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getInformeGrupoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getInformeGrupoServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getInformeGrupoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(int id) {
        setObjeto(new InfGrupo(id));
        super.setAccion(ACCION_EDITAR);
        getInformeGrupoServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar:pEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getInformeGrupoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        getInformeGrupoServicio().Accion(this);
        procesoFinal();
    }

    public void listarUsuarios(int id) {
        setObjeto(new InfGrupo(id));
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_LISTAR);
        getInformeGrupoServicio().Accion(this);
        PrimeFaces.current().executeScript("PF('dialogoUsuarios').show()");
        procesoFinal();
    }

    public void crearUsuario() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_CREAR);
        getInformeGrupoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmUsuarios");
        PrimeFaces.current().executeScript("PF('dialogoCrearUsuario').show()");
        procesoFinal();
    }

    public void guardarUsuario() {
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_GUARDAR);
        getInformeGrupoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmUsuarios");
        PrimeFaces.current().executeScript("PF('dialogoCrearUsuario').hide()");
        procesoFinal();
    }

    public void borrarUsuario(int _id) {
        setObjetoUsuario(new InfGrupoUsuario(_id));
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_BORRAR);
        getInformeGrupoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmUsuarios");
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmGrupos");
                case Url.ACCION_VER:
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            PrimeFaces.current().ajax().update("frmUsuarios");
                            crearLog("Usuarios - Listar", "Listar Registro");
                            break;
                        case Url.ACCION_CREAR:
                            PrimeFaces.current().ajax().update("frmUsuarios:pVerUsuarios");
                            crearLog("Usuarios - Crear", "Creación de Registro");
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().ajax().update("frmUsuarios");
                            crearLog("Usuarios - Guardar", getObjetoUsuario().toString());
                            break;
                        case Url.ACCION_BORRAR:
                            PrimeFaces.current().ajax().update("frmUsuarios");
                            crearLog("Usuarios - Borrar", getObjetoUsuario().toString());
                            break;
                    }
                    break;
            }
        }
        generarMensajes();
    }

    //GETTERS SETTER
    public InformeGrupoServicioIface getInformeGrupoServicio() {
        return informeGrupoServicio;
    }

    public void setInformeGrupoServicio(InformeGrupoServicioIface informeGrupoServicio) {
        this.informeGrupoServicio = informeGrupoServicio;
    }

    public InfGrupo getObjeto() {
        return objeto;
    }

    public void setObjeto(InfGrupo objeto) {
        this.objeto = objeto;
    }

    public List<InfGrupo> getRegistros() {
        return registros;
    }

    public void setRegistros(List<InfGrupo> registros) {
        this.registros = registros;
    }

    public ParamConsulta getParamConsultaGrupo() {
        return paramConsultaGrupo;
    }

    public void setParamConsultaGrupo(ParamConsulta paramConsultaGrupo) {
        this.paramConsultaGrupo = paramConsultaGrupo;
    }

    public InfGrupoUsuario getObjetoUsuario() {
        return objetoUsuario;
    }

    public void setObjetoUsuario(InfGrupoUsuario objetoUsuario) {
        this.objetoUsuario = objetoUsuario;
    }

    public LazyDataModel<InfGrupo> getLazyGrupo() {
        return lazyGrupo;
    }

    public void setLazyGrupo(LazyDataModel<InfGrupo> lazyGrupo) {
        this.lazyGrupo = lazyGrupo;
    }

    public List<Usuario> getListaGnUsuarios() {
        return listaGnUsuarios;
    }

    public void setListaGnUsuarios(List<Usuario> listaGnUsuarios) {
        this.listaGnUsuarios = listaGnUsuarios;
    }

    public HashMap<Integer, Usuario> getUsuarioRecursiva() {
        return usuarioRecursiva;
    }

    public void setUsuarioRecursiva(HashMap<Integer, Usuario> usuarioRecursiva) {
        this.usuarioRecursiva = usuarioRecursiva;
    }

    public String getUsuarioPorId(int id) {
        String usuarioStr = "";
        Usuario usuario = getUsuarioRecursiva().get(id);
        if (usuario != null) {
            usuarioStr = usuario.getNombre();
        }
        return usuarioStr;
    }

    public List<InfGrupo> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List<InfGrupo> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public void seleccionarUsuario(int id) {
        getObjetoUsuario().setUsuario(new Usuario(id));
    }

    public List<InfGrupoUsuario> getListaFiltroUsuario() {
        return listaFiltroUsuario;
    }

    public void setListaFiltroUsuario(List<InfGrupoUsuario> listaFiltroUsuario) {
        this.listaFiltroUsuario = listaFiltroUsuario;
    }

    public String getValorFiltro() {
        return valorFiltro;
    }

    public void setValorFiltro(String valorFiltro) {
        this.valorFiltro = valorFiltro;
    }

    public void filtrarUsuarios() {
        if (getValorFiltro() == null || getValorFiltro().equals("")) {
            setListaFiltroUsuario(getObjeto().getListaGrupoUsuarios());
        } else {
            setListaFiltroUsuario(new ArrayList<>());
            for (InfGrupoUsuario usuario : getObjeto().getListaGrupoUsuarios()) {
                if (usuario.getUsuario().getNombre().contains(getValorFiltro())) {
                    getListaFiltroUsuario().add(usuario);
                }
            }
        }
        PrimeFaces.current().ajax().update("frmVer:pVerUsuarios");
        PrimeFaces.current().ajax().update("frmUsuarios:pVerUsuarios");
    }

    public List<Usuario> completarUsuario(String query) {
        List<Usuario> usuariosFiltrados = new ArrayList<>();
        for (Usuario usuario : getUsuarioRecursiva().values()) {
            if (usuario.getNombre().toLowerCase().contains(query.toLowerCase())) {
                usuariosFiltrados.add(usuario);
            }
        }
        return usuariosFiltrados;
    }

}
