package com.saviasaludeps.savia.web.cuentamedica.auditoria.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.negocio.administracion.UsuarioRemoto;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupo;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupoPrestador;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupoUsuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.web.utilidades.Url;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import com.saviasaludeps.savia.web.cuentamedica.auditoria.servicio.CmGrupoServicioIface;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.util.ArrayList;
import java.util.Objects;
import javax.faces.context.FacesContext;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.SortMeta;

@ManagedBean
@ViewScoped
public class CmGrupoBean extends Url {

    public static final char ACCION_LISTAR_GRUPOS = 'a';
    public static final char ACCION_LISTAR_USUARIOS = 'b';
    public static final char ACCION_LISTAR_PRESTADORES = 'c';
    public static final char ACCION_LISTAR_PRESTADORES_POR_GRUPO = 'd';
    public static final char ACCION_LISTAR_USUARIOS_POR_GRUPO = 'e';
    public static final char ACCION_BORRAR_USUARIO_POR_GRUPO = 'f';
    public static final char ACCION_BORRAR_PRESTADOR_POR_GRUPO = 'g';
    public static final char ACCION_BORRAR_GRUPO = 'h';
    public static final char ACCION_VER_GRUPO = 'i';
    public static final char ACCION_VER_PRESTADOR_POR_GRUPO = 'j';
    public static final char ACCION_VER_USUARIO_POR_GRUPO = 'k';
    public static final char ACCION_GUARDAR_USUARIO_POR_GRUPO = 'l';
    public static final char ACCION_GUARDAR_PRESTADOR_POR_GRUPO = 'm';
    public static final char ACCION_GUARDAR_GRUPO = 'n';
    public static final char ACCION_LISTAR_USUARIOS_LIDER = 'o';
    
    public static final int TIPO_OPERACION_INSERCION = 1;
    public static final int TIPO_OPERACION_EDICION   = 2;

    private CmGrupoServicioIface cmGrupoServicio;
    private List<CmGrupo> registros;
    private List<CmGrupoUsuario> listaGrupoUsuarios;
    private List<CmGrupoUsuario> listaGrupoUsuariosLider;
    private List<CmGrupoPrestador> listaGrupoPrestadores;
    private CmGrupo objeto;
    private CmGrupoPrestador prestador;
    private CmGrupoPrestador prestadorEncontrado;
    private CmGrupoUsuario usuario;
    private CmGrupoUsuario usuarioEncontrado;
    private LazyDataModel<CmGrupo> lazyCmGrupo;
    private Usuario gnUsuario;
    private CntPrestador cntPrestador;
    private List<Usuario> listaGnUsuarios;
    private LazyDataModel<Usuario> lazyUsuarios;
    private LazyDataModel<CmGrupoUsuario> lazyCmGrupoUsuarios;
    private List<Usuario> registroUsuarios;
    private List<CntPrestador> listaCntPrestadores;
    private List<CntPrestador> registroCntPrestadores;
    private LazyDataModel<CntPrestador> lazyCntPrestadores;
    private LazyDataModel<CmGrupoPrestador> lazyCmGrupoPrestadores;
    private int tamanoPagina = 30;
    private int tipoOperacion;
    private boolean habilitarCamposRadicacion;

    private UsuarioRemoto getUsuarioRemoto() throws Exception {
        return (UsuarioRemoto) RemotoEJB.getEJBRemoto("UsuarioServicio", UsuarioRemoto.class.getName());
    }

    private CntPrestadorRemoto getCntPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    public CmGrupoBean() {
        this.objeto = new CmGrupo();
        this.usuario = new CmGrupoUsuario();
        this.prestador = new CmGrupoPrestador();
        this.prestadorEncontrado = new CmGrupoPrestador();
        this.listaGrupoPrestadores = new ArrayList<>();
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        Modulo _mod = super.validarModulo(Modulo.ID_CM_ASIGNACION_USUARIOS);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyCmGrupo = new LazyDataModel<CmGrupo>() {

                private List<CmGrupo> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CmGrupo> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(CmGrupo objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public CmGrupo getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CmGrupo objeto : lista) {
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
        //getCmRipsReglaServicio().cargasInicial(this);
        listar();
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR_GRUPOS);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR_GRUPOS);
        getCmGrupoServicio().Accion(this);
    }

    public void refrescarUsuarios() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR_USUARIOS);
        getCmGrupoServicio().Accion(this);
    }

    public void refrescarPrestadores() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR_PRESTADORES);
        getCmGrupoServicio().Accion(this);
    }
    
    public void refrescarGrupoPrestadores() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR_PRESTADORES_POR_GRUPO);
        getCmGrupoServicio().Accion(this);
    }
   
     public void refrescarGrupoUsuarios() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR_USUARIOS_POR_GRUPO);
        getCmGrupoServicio().Accion(this);
    }

    public void crear() {
        limpiarVariables();
        super.setAccion(ACCION_CREAR);
        getCmGrupoServicio().Accion(this);
        setTipoOperacion(TIPO_OPERACION_INSERCION);
        PrimeFaces.current().ajax().update("frmCrear:panelCrear");
        PrimeFaces.current().ajax().update("frmCrear:tablaUsuariosCrear");
        PrimeFaces.current().ajax().update("frmCrear:tablaPrestadoresCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {

        if (validarDatoParaGuardadoGrupo()) {
            super.setAccion(ACCION_GUARDAR);
            super.setDoAccion(ACCION_GUARDAR_GRUPO);
            getCmGrupoServicio().Accion(this);
            if (!super.isError()) {
                PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
            }
        }
        procesoFinal();
    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        limpiarVariables();
        super.setAccion(ACCION_EDITAR);
        getCmGrupoServicio().Accion(this);
        setTipoOperacion(TIPO_OPERACION_EDICION);
          
        inicializarTablaGrupoUsuarios();
        if (!isHabilitarCamposRadicacion()) {
            inicializarTablaGrupoPrestadores();
        }

        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        
        DataTable tablaUsuarios = (DataTable) FacesContext.getCurrentInstance().
                getViewRoot().findComponent("frmEditar:tablaUsuarioEditar");
        tablaUsuarios.reset();
        if (!isHabilitarCamposRadicacion()) {
            PrimeFaces.current().ajax().update("frmEditar:tablaPrestadorEditar");
            limpiarFiltrosTablas("frmEditar:tablaPrestadorEditar");
        }
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_GRUPO);
        getCmGrupoServicio().Accion(this); 
        inicializarTablaGrupoPrestadores();
        inicializarTablaGrupoUsuarios();
        PrimeFaces.current().resetInputs("frmVer");
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().ajax().update("frmVer:tablaUsuarioVer");
        PrimeFaces.current().ajax().update("frmVer:tablaPrestadorVer");
        DataTable tablaUsuarios= (DataTable) FacesContext.getCurrentInstance().
                getViewRoot().findComponent("frmVer:tablaUsuarioVer");
        tablaUsuarios.reset();		
        limpiarFiltrosTablas("frmVer:tablaPrestadorVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void verListUsuarios() {
        limpiarFiltrosTablas("frmUsuariosLista:tablaRegistrosUsuarios");
        inicializarTablaUsuarios();
        PrimeFaces.current().resetInputs("frmUsuariosLista");
        PrimeFaces.current().ajax().update("frmUsuariosLista");
        PrimeFaces.current().executeScript("PF('dialogoUsuariosLista').show()");
    }

    public void verListPrestador() {
        limpiarFiltrosTablas("frmPrestadoresLista:tablaRegistrosPrestadores");
        inicializarTablaPrestadores();
        PrimeFaces.current().resetInputs("frmPrestadoresLista");
        PrimeFaces.current().ajax().update("frmPrestadoresLista");
        PrimeFaces.current().executeScript("PF('dialogoPrestadoresLista').show()");
    }

    public void verAgregarUsuario() {
        setUsuario(new CmGrupoUsuario());
        PrimeFaces.current().resetInputs("frmCrearUsuario");
        PrimeFaces.current().ajax().update("frmCrearUsuario");
        PrimeFaces.current().executeScript("PF('dialogoCrearUsuario').show()");
        procesoFinal();
    }

    public void verAgregarPrestador() {
        setPrestador(new CmGrupoPrestador());
        PrimeFaces.current().resetInputs("frmCrearPrestador");
        PrimeFaces.current().ajax().update("frmCrearPrestador");
        PrimeFaces.current().executeScript("PF('dialogoCrearPrestador').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getCmGrupoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide()");
        }
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        super.setDoAccion(ACCION_BORRAR_GRUPO);
        getCmGrupoServicio().Accion(this);
        procesoFinal();
    }
    
    public void borraFisicoUsuarioGrupo(int _id) {
        getUsuario().setId(_id);
        super.setAccion(ACCION_BORRAR);
        super.setDoAccion(ACCION_BORRAR_USUARIO_POR_GRUPO);
        getCmGrupoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmEditar:tablaUsuarioEditar");
        procesoFinal();
    }
    
    public void borrarFisicoPrestadorGrupo(int _id) {
        getPrestador().setId(_id);
        super.setAccion(ACCION_BORRAR);
        super.setDoAccion(ACCION_BORRAR_PRESTADOR_POR_GRUPO);
        getCmGrupoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmEditar:tablaPrestadorEditar");
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_GUARDAR:
                    crearLog(getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmGrupos");
                    break;
                case Url.ACCION_MODIFICAR:
                    PrimeFaces.current().ajax().update("frmGrupos");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmGrupos");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_LISTAR:
                    switch (this.getDoAccion()) {
                        case ACCION_LISTAR_GRUPOS:
                            PrimeFaces.current().ajax().update("frmGrupos");
                            crearLog(getObjeto().toString());
                            break;
                    }

                    break;
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());

                    break;
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_VER:
                    crearLog(getObjeto().toString());
                    break;
            }
            //refrescar();
        }
        generarMensajes();
    }

    /*-------------------------------------
    ------USUARIOS Y PRESTADORES DEL GRUPO---
    ---------------------------------------*/
    public void agregarUsuario() {
        boolean hayInsercion = !insertarUsuarioMemoria() ? insertarUsuarioDB() : true;
        if (hayInsercion) {
            PrimeFaces.current().executeScript("PF('dialogoCrearUsuario').hide()");
            PrimeFaces.current().ajax().update("frmCrear:tablaUsuariosCrear");
            PrimeFaces.current().ajax().update("frmEditar:tablaUsuarioEditar");
        }
        procesoFinal();
    }

    private boolean insertarUsuarioMemoria() {
       int idInsertar = 1;
        boolean hayInsercion = false;
        if (TIPO_OPERACION_INSERCION == getTipoOperacion()) {
            for (CmGrupoUsuario cmGrupoUsuario : listaGrupoUsuarios) {
                if (cmGrupoUsuario.getIdInsertar() != null) {
                    idInsertar = cmGrupoUsuario.getIdInsertar() + 1;
                }
            }
            if (validarUsuarioTipoUnicoMemoria(listaGrupoUsuarios, getUsuario()) && 
                validarUsuarioPuedeSerLiderMemoria(listaGrupoUsuarios, getUsuario().getTipo())) {
                listaGrupoUsuarios.add(setearUsuarioGrupo(idInsertar));
                hayInsercion = true;
            }

        }
        return hayInsercion;
    }

  
    private boolean insertarUsuarioDB() {
        int idInsertar = 1;
        boolean hayInsercion = false;
        if (TIPO_OPERACION_EDICION == getTipoOperacion()) {

            if (validarUsuarioTipoUnicoDB() && validarUsuarioPuedeSerLiderDB(getUsuario().getTipo()) ) {
                setUsuarioEncontrado(setearUsuarioGrupo(idInsertar));
                setAccion(ACCION_GUARDAR);
                setDoAccion(ACCION_GUARDAR_USUARIO_POR_GRUPO);
                getCmGrupoServicio().Accion(this);
                hayInsercion = true;
            }
        }
        return hayInsercion;
    }

    public boolean validarUsuarioTipoUnicoMemoria(List<CmGrupoUsuario> listaGrupoUsuarios, CmGrupoUsuario usuario) {
        boolean usuarioValido = true;
        for (CmGrupoUsuario cmGrupoUsuario : listaGrupoUsuarios) {
            if (cmGrupoUsuario.getTipo() == usuario.getTipo()
                    && Objects.equals(cmGrupoUsuario.getGnUsuario().getId(), usuario.getGnUsuario().getId())) {
                usuarioValido = false;
                break;
            }
        }

        if (!usuarioValido) {
             this.addError("El usuario ( "+usuario.getGnUsuario().getNombre()+
                     " ) y tipo ( "+usuario.getTipoStr()+" ) ya esta asociado al grupo.");
        }
        return usuarioValido;
    }
   
    public boolean validarUsuarioTipoUnicoDB( ) {
        boolean usuarioValido = true;
        setAccion(ACCION_VER);
        setDoAccion(ACCION_VER_USUARIO_POR_GRUPO);
        getCmGrupoServicio().Accion(this);
        if (getUsuarioEncontrado().getId() != null) {
            usuarioValido = false;
            this.addError("El usuario ( " + getUsuarioEncontrado().getGnUsuario().getNombre() +
                    " ) y tipo ( " + getUsuarioEncontrado().getTipoStr() + " ) ya esta asociado al grupo.");
        }
        return usuarioValido;
    }

    public boolean validarPrestadorUnico(List<CmGrupoPrestador> listaPrestadores, CmGrupoPrestador prestador) {
        boolean prestadorValido = true;
        for (CmGrupoPrestador cmGrupoPrestador : listaPrestadores) {
            if (Objects.equals(cmGrupoPrestador.getCntPrestador().getId(), prestador.getCntPrestador().getId())) {
                prestadorValido = false;
                break;
            }
        }

        if (!prestadorValido) {
            addError("El prestador ( "+prestador.getRazonSocial()+" ) ya existe asociado al grupo.");
        }
        return prestadorValido;
    }

    public boolean validarDatoParaGuardadoGrupo() {
        boolean datosGrupoValido = true;
        if (this.getObjeto().getNombre() == null || "".equals(this.getObjeto().getNombre())) {
            addError("El grupo debe tener un nombre");
            datosGrupoValido = false;
        }
        return datosGrupoValido;
    }
    
    public boolean validarUsuarioPuedeSerLiderMemoria(List<CmGrupoUsuario> listaGrupoUsuarios, int tipoUsuario) {
        boolean usuarioValido = true;
        CmGrupoUsuario usuarioLider = new CmGrupoUsuario();
        
        if ( CmGrupoUsuario.TIPO_LIDER  != tipoUsuario){
            return usuarioValido;
        }
        
        for (CmGrupoUsuario cmGrupoUsuario : listaGrupoUsuarios) {
            if (CmGrupoUsuario.TIPO_LIDER  == cmGrupoUsuario.getTipo()) {
                usuarioLider = cmGrupoUsuario;
                usuarioValido = false;
                break;
            }
        }

        if (!usuarioValido) {
             this.addError("El grupo ya tiene asociado un líder ( "+usuarioLider.getGnUsuario().getNombre()+" )");
        }
        return usuarioValido;
    }
    
    public boolean validarUsuarioPuedeSerLiderDB(int tipoUsuario) {
        
        boolean usuarioValido = true;
        CmGrupoUsuario usuarioLider = new CmGrupoUsuario();
        setAccion(ACCION_LISTAR);
        setDoAccion(ACCION_LISTAR_USUARIOS_LIDER);
        if (CmGrupoUsuario.TIPO_LIDER != tipoUsuario) {
            return usuarioValido;
        }

        getCmGrupoServicio().Accion(this);
    
        if (getListaGrupoUsuariosLider() != null && getListaGrupoUsuariosLider().size() > 0) {
            usuarioLider = getListaGrupoUsuariosLider().get(0);
            usuarioValido = false;
        }
        if (!usuarioValido) {
            this.addError("El grupo ya tiene asociado un líder( " + usuarioLider.getGnUsuario().getNombre() + " )");
        }
        return usuarioValido;
    }

    public void agregarPrestador() {
        
        boolean hayInsercion = !insertarPrestadorMemoria() ? insertarPrestadorDB(): true;
 
        if (hayInsercion) {
            PrimeFaces.current().executeScript("PF('dialogoCrearPrestador').hide()");
            PrimeFaces.current().ajax().update("frmCrear:tablaPrestadoresCrear");
            PrimeFaces.current().ajax().update("frmEditar:tablaPrestadorEditar");
        }
        procesoFinal();
    }

    private boolean insertarPrestadorDB() {
        int idInsertar = 1;
        boolean hayInsercion = false;
        if (TIPO_OPERACION_EDICION == getTipoOperacion()) {
            setAccion(ACCION_VER);
            setDoAccion(ACCION_VER_PRESTADOR_POR_GRUPO);
            getCmGrupoServicio().Accion(this);

            if (getPrestadorEncontrado().getId() == null) {
                setPrestadorEncontrado(setearPrestadorGrupo(idInsertar));
                setAccion(ACCION_GUARDAR);
                setDoAccion(ACCION_GUARDAR_PRESTADOR_POR_GRUPO);
                getCmGrupoServicio().Accion(this);
                hayInsercion = true;
            }else{
                this.addError("El prestador ( "+getPrestadorEncontrado().getRazonSocial()+" ) ya esta asociado al grupo.");
            }
        }
        return hayInsercion;
    }

    private boolean insertarPrestadorMemoria() {
        int idInsertar = 1;
        boolean hayInsercion = false;
        if (TIPO_OPERACION_INSERCION == getTipoOperacion()) {
            for (CmGrupoPrestador cmGrupoPrestador : listaGrupoPrestadores) {
                if (cmGrupoPrestador.getIdInsertar() != null) {
                    idInsertar = cmGrupoPrestador.getIdInsertar() + 1;
                }
            }

            if (validarPrestadorUnico(listaGrupoPrestadores, getPrestador())) {
                listaGrupoPrestadores.add(setearPrestadorGrupo(idInsertar));
                hayInsercion = true;
            }
        }
        return hayInsercion;
    }

    public void retirarUsuario(int id) {
        for (int i = 0; i < listaGrupoUsuarios.size(); i++) {
            CmGrupoUsuario cmGrupoUsuario = listaGrupoUsuarios.get(i);
            if (cmGrupoUsuario.getIdInsertar() != null && cmGrupoUsuario.getIdInsertar() == id) {
                listaGrupoUsuarios.remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmCrear:tablaUsuariosCrear");
        PrimeFaces.current().ajax().update("frmEditar:tablaUsuarioEditar");
    }

    public void retirarPrestador(int id) {
        for (int i = 0; i < listaGrupoPrestadores.size(); i++) {
            CmGrupoPrestador cmGrupoPrestador = listaGrupoPrestadores.get(i);
            if (cmGrupoPrestador.getIdInsertar() != null && cmGrupoPrestador.getIdInsertar() == id) {
                listaGrupoPrestadores.remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmCrear:tablaPrestadoresCrear");
        PrimeFaces.current().ajax().update("frmEditar:tablaPrestadorEditar");
    }

    public void borrarUsuario(int id) {
        for (int i = 0; i < listaGrupoUsuarios.size(); i++) {
            CmGrupoUsuario cmGrupoUsuario = listaGrupoUsuarios.get(i);
            if (cmGrupoUsuario.getId() == id) {
                listaGrupoUsuarios.remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmCrear:tablaUsuariosCrear");
        PrimeFaces.current().ajax().update("frmEditar:tablaUsuarioEditar");
    }


    public List<Usuario> autoCompletarUsuario(String nombreUsuario) {
        try {
            List<String> results = new ArrayList<>();
            setListaGnUsuarios(getUsuarioRemoto().consultarPorNombre(nombreUsuario));
        } catch (Exception ex) {
            addError(ex.toString());
            generarMensajes();
        }
        return listaGnUsuarios;
    }

    public List<CntPrestador> autoCompletarIPS(String nombreIPS) {
        try {
            List<String> results = new ArrayList<>();
            setListaCntPrestadores(getCntPrestadorRemoto().consultarPorNombre(nombreIPS));
        } catch (Exception ex) {
            addError(ex.toString());
            generarMensajes();
        }
        return listaCntPrestadores;
    }

    public void seleccionarUsuario(SelectEvent event) {
        Object item = event.getObject();
        setGnUsuario((Usuario) item);
    }

    public void seleccionarIPS(SelectEvent event) {
        Object item = event.getObject();
        setCntPrestador((CntPrestador) item);
    }

    public void inicializarTablaUsuarios() {
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
                listaUsuario = getRegistroUsuarios();
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

    public void inicializarTablaPrestadores() {
        lazyCntPrestadores = new LazyDataModel<CntPrestador>() {
            private List<CntPrestador> listaPrestadores;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(0).getCantidadRegistros();
            }

            @Override
            public List<CntPrestador> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(0).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(0).setPrimerRegistro(primerRegistro);
                getParamConsulta(0).setRegistrosPagina(registrosPagina);
                getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarPrestadores();
                listaPrestadores = getRegistroCntPrestadores();
                setRowCount(getParamConsulta(0).getCantidadRegistros());
                return listaPrestadores;
            }

            @Override
            public String getRowKey(CntPrestador cntPrestador) {
                return cntPrestador.getId().toString();
            }

            @Override
            public CntPrestador getRowData(String cntPrestador) {
                Integer id = Integer.valueOf(cntPrestador);
                for (CntPrestador prestador : listaPrestadores) {
                    if (id.equals(prestador.getId())) {
                        return prestador;
                    }
                }
                return null;
            }
        };
    }
    
    public void inicializarTablaGrupoPrestadores() {
        lazyCmGrupoPrestadores = new LazyDataModel<CmGrupoPrestador>() {
            private List<CmGrupoPrestador> listaPrestadores;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(3).getCantidadRegistros();
            }

            @Override
            public List<CmGrupoPrestador> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(3).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(3).setPrimerRegistro(primerRegistro);
                getParamConsulta(3).setRegistrosPagina(registrosPagina);
                getParamConsulta(3).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(3).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarGrupoPrestadores();
                listaPrestadores = getListaGrupoPrestadores();
                setRowCount(getParamConsulta(3).getCantidadRegistros());
                return listaPrestadores;
            }

            @Override
            public String getRowKey(CmGrupoPrestador cntPrestador) {
                return cntPrestador.getId().toString();
            }

            @Override
            public CmGrupoPrestador getRowData(String cntPrestador) {
                Integer id = Integer.valueOf(cntPrestador);
                for (CmGrupoPrestador prestador : listaPrestadores) {
                    if (id.equals(prestador.getId())) {
                        return prestador;
                    }
                }
                return null;
            }
        };
    }
    
    public void inicializarTablaGrupoUsuarios() {
        lazyCmGrupoUsuarios = new LazyDataModel<CmGrupoUsuario>() {
            private List<CmGrupoUsuario> listaUsuarios;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta(2).getCantidadRegistros();
            }

            @Override
            public List<CmGrupoUsuario> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(2).setEmpresaId(getConexion().getEmpresa().getId());
                getParamConsulta(2).setPrimerRegistro(primerRegistro);
                getParamConsulta(2).setRegistrosPagina(registrosPagina);
                getParamConsulta(2).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(2).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarGrupoUsuarios();
                listaUsuarios = getListaGrupoUsuarios();
                setRowCount(getParamConsulta(2).getCantidadRegistros());
                return listaUsuarios;
            }

            @Override
            public String getRowKey(CmGrupoUsuario usuario) {
                return usuario.getId().toString();
            }

            @Override
            public CmGrupoUsuario getRowData(String cntUsuario) {
                Integer id = Integer.valueOf(cntUsuario);
                for (CmGrupoUsuario usuario : listaUsuarios) {
                    if (id.equals(usuario.getId())) {
                        return usuario;
                    }
                }
                return null;
            }
        };
    }


    public void onRowSelecionUsuario(SelectEvent event) {
        Usuario usuario = (Usuario) event.getObject();
        getUsuario().setGnUsuario(usuario);
        getUsuario().setNombreUsuario(usuario.getNombre());
        PrimeFaces.current().executeScript("PF('dialogoUsuariosLista').hide()");
        PrimeFaces.current().ajax().update("frmCrearUsuario:autoUsuario");
    }

    public void onRowSelecionPrestador(SelectEvent event) {
        setCntPrestador(new CntPrestador());
        CntPrestador cmPrestador = (CntPrestador) event.getObject();
        getPrestador().setCntPrestador(cmPrestador);
        getPrestador().setRazonSocial(cmPrestador.getRazonSocial());
        PrimeFaces.current().executeScript("PF('dialogoPrestadoresLista').hide()");
        PrimeFaces.current().ajax().update("frmCrearPrestador:textPrestador");
    }
    
    public void habilitarCamposSegunTipoGrupo() {

        
        setHabilitarCamposRadicacion(false);
        this.getObjeto().setCategoria(null);
        if (CmGrupo.TIPO_GRUPO_RADICACION == this.getObjeto().getTipoGrupo()) {
            setHabilitarCamposRadicacion(true);
        }

    }

    public boolean limpiarVariables() {
        boolean hayLimpiado = false;
        if (getListaGrupoUsuarios() != null) {
            getListaGrupoUsuarios().clear();
        }
        if (getListaGrupoPrestadores() != null) {
            getListaGrupoPrestadores().clear();
        }
        hayLimpiado = true;
        return hayLimpiado;
    }

    private void limpiarFiltrosTablas(String nombreTablaUsuario, String nombrePrestacion) {
        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(nombreTablaUsuario);
        dataTable.reset();
        DataTable dataTable1 = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(nombrePrestacion);
        dataTable1.reset();
    }

    public List<CmGrupo> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CmGrupo> registros) {
        this.registros = registros;
    }

    public List<CmGrupoUsuario> getListaGrupoUsuarios() {
        return listaGrupoUsuarios;
    }

    public void setListaGrupoUsuarios(List<CmGrupoUsuario> listaGrupoUsuarios) {
        this.listaGrupoUsuarios = listaGrupoUsuarios;
    }

    public List<CmGrupoUsuario> getListaGrupoUsuariosLider() {
        return listaGrupoUsuariosLider;
    }

    public void setListaGrupoUsuariosLider(List<CmGrupoUsuario> listaGrupoUsuariosLider) {
        this.listaGrupoUsuariosLider = listaGrupoUsuariosLider;
    }
    

    public List<CmGrupoPrestador> getListaGrupoPrestadores() {
        return listaGrupoPrestadores;
    }

    public void setListaGrupoPrestadores(List<CmGrupoPrestador> listaGrupoPrestadores) {
        this.listaGrupoPrestadores = listaGrupoPrestadores;
    }

    public CmGrupo getObjeto() {
        return objeto;
    }

    public void setObjeto(CmGrupo objeto) {
        this.objeto = objeto;
    }

    public LazyDataModel<CmGrupo> getLazyCmGrupo() {
        return lazyCmGrupo;
    }

    public void setLazyCmGrupo(LazyDataModel<CmGrupo> lazyCmGrupo) {
        this.lazyCmGrupo = lazyCmGrupo;
    }

    public CmGrupoServicioIface getCmGrupoServicio() {
        return cmGrupoServicio;
    }

    public void setCmGrupoServicio(CmGrupoServicioIface cmGrupoServicio) {
        this.cmGrupoServicio = cmGrupoServicio;
    }

    public CmGrupoPrestador getPrestador() {
        return prestador;
    }

    public void setPrestador(CmGrupoPrestador prestador) {
        this.prestador = prestador;
    }

    public CmGrupoPrestador getPrestadorEncontrado() {
        return prestadorEncontrado;
    }

    public void setPrestadorEncontrado(CmGrupoPrestador prestadorEncontrado) {
        this.prestadorEncontrado = prestadorEncontrado;
    }

    public CmGrupoUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(CmGrupoUsuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getGnUsuario() {
        return gnUsuario;
    }

    public CmGrupoUsuario getUsuarioEncontrado() {
        return usuarioEncontrado;
    }

    public void setUsuarioEncontrado(CmGrupoUsuario usuarioEncontrado) {
        this.usuarioEncontrado = usuarioEncontrado;
    }

    public void setGnUsuario(Usuario gnUsuario) {
        this.gnUsuario = gnUsuario;
    }

    public List<Usuario> getListaGnUsuarios() {
        return listaGnUsuarios;
    }

    public void setListaGnUsuarios(List<Usuario> listaGnUsuarios) {
        this.listaGnUsuarios = listaGnUsuarios;
    }

    public List<CntPrestador> getListaCntPrestadores() {
        return listaCntPrestadores;
    }

    public void setListaCntPrestadores(List<CntPrestador> listaCntPrestadores) {
        this.listaCntPrestadores = listaCntPrestadores;
    }

    public CntPrestador getCntPrestador() {
        return cntPrestador;
    }

    public void setCntPrestador(CntPrestador cntPrestador) {
        this.cntPrestador = cntPrestador;
    }

    public LazyDataModel<Usuario> getLazyUsuarios() {
        return lazyUsuarios;
    }

    public void setLazyUsuarios(LazyDataModel<Usuario> lazyUsuarios) {
        this.lazyUsuarios = lazyUsuarios;
    }

    public List<Usuario> getRegistroUsuarios() {
        return registroUsuarios;
    }

    public void setRegistroUsuarios(List<Usuario> registroUsuarios) {
        this.registroUsuarios = registroUsuarios;
    }

    public LazyDataModel<CntPrestador> getLazyCntPrestadores() {
        return lazyCntPrestadores;
    }

    public void setLazyCntPrestadores(LazyDataModel<CntPrestador> lazyCntPrestadores) {
        this.lazyCntPrestadores = lazyCntPrestadores;
    }

    public LazyDataModel<CmGrupoPrestador> getLazyCmGrupoPrestadores() {
        return lazyCmGrupoPrestadores;
    }

    public void setLazyCmGrupoPrestadores(LazyDataModel<CmGrupoPrestador> lazyCmGrupoPrestadores) {
        this.lazyCmGrupoPrestadores = lazyCmGrupoPrestadores;
    }

    public LazyDataModel<CmGrupoUsuario> getLazyCmGrupoUsuarios() {
        return lazyCmGrupoUsuarios;
    }

    public void setLazyCmGrupoUsuarios(LazyDataModel<CmGrupoUsuario> lazyCmGrupoUsuarios) {
        this.lazyCmGrupoUsuarios = lazyCmGrupoUsuarios;
    }
    
    public List<CntPrestador> getRegistroCntPrestadores() {
        return registroCntPrestadores;
    }

    public void setRegistroCntPrestadores(List<CntPrestador> registroCntPrestadores) {
        this.registroCntPrestadores = registroCntPrestadores;
    }

    public int getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(int tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public boolean isHabilitarCamposRadicacion() {
        return habilitarCamposRadicacion;
    }

    public void setHabilitarCamposRadicacion(boolean habilitarCamposRadicacion) {
        this.habilitarCamposRadicacion = habilitarCamposRadicacion;
    }    

    private void limpiarFiltrosTablas(String nombrefrmTabla) {
        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(nombrefrmTabla);
        dataTable.reset();
    }
    
    private CmGrupoPrestador setearPrestadorGrupo(int idInsertar) {
        CmGrupoPrestador cmGrupoPrestador = new CmGrupoPrestador();
        cmGrupoPrestador.setCmGrupo(new CmGrupo(getObjeto().getId()));
        cmGrupoPrestador.setActivo(true);
        cmGrupoPrestador.setRazonSocial(getPrestador().getRazonSocial());
        cmGrupoPrestador.setCntPrestador(getPrestador().getCntPrestador());
        cmGrupoPrestador.setIdInsertar(idInsertar);
        return cmGrupoPrestador;
    }
    
     private CmGrupoUsuario setearUsuarioGrupo(int idInsertar) {
        CmGrupoUsuario nuevoUsuario = new CmGrupoUsuario();
        nuevoUsuario.setIdInsertar(idInsertar);
        nuevoUsuario.setActivo(true);
        nuevoUsuario.setCmGrupo(new CmGrupo(this.getObjeto().getId()));
        nuevoUsuario.setTipo(getUsuario().getTipo());
        nuevoUsuario.setIdInsertar(idInsertar);
        nuevoUsuario.setGnUsuario(getUsuario().getGnUsuario());
        return nuevoUsuario;
    }
    
}
