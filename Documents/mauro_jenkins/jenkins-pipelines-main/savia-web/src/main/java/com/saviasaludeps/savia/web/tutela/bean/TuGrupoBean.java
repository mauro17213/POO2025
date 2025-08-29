/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.tutela.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.tutela.TuGrupo;
import com.saviasaludeps.savia.dominio.tutela.TuGrupoUsuario;
import com.saviasaludeps.savia.web.tutela.servicio.TuGrupoServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.ReorderEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author jorge eliecer perez
 */
@ManagedBean
@ViewScoped
public class TuGrupoBean extends Url {

    public static final char ACCION_LISTAR_GRUPOS = 'a';
    public static final char ACCION_CAMBIAR_ORDER = 'b';
    public static final char ACCION_MODIFICAR_GRUPO = 'c';
    public static final char ACCION_LISTAR_USUARIOS = 'd';
    public static final char ACCION_LISTAR_GRUPO_USUARIOS = 'e';
    public static final char ACCION_BORRAR_GRUPO = 'f';
    public static final char ACCION_BORRAR_USUARIO = 'g';

    @Autowired
    private TuGrupoServicioIface tuGrupoServicio;
    private TuGrupo objeto;
    private TuGrupoUsuario objetoGrupoUsuario;
    private List<TuGrupo> registros;
    private LazyDataModel<TuGrupo> lazyGrupos;

    private List<Usuario> registrosUsuarios;
    private LazyDataModel<Usuario> lazyUsuarios;

    private List<TuGrupoUsuario> registrosGrupoUsuarios;
    private List<TuGrupoUsuario> registrosGrupoUsuariosParaBorrarDB;
    private LazyDataModel<TuGrupoUsuario> lazyGrupoUsuario;

    private ParamConsulta paramConsultaUsuarios;
    private ParamConsulta paramConsultaGrupoUsuarios;

    private List<TuGrupoUsuario> listaGrupoUsuario;

    private List<Maestro> listaGrupoEstados;
    private HashMap<Integer, Maestro> hashGrupoEstados;

    public TuGrupo getObjeto() {
        if (objeto == null) {
            objeto = new TuGrupo();
        }
        return objeto;
    }

    public void setObjeto(TuGrupo objeto) {
        this.objeto = objeto;
    }

    public List<TuGrupo> getRegistros() {
        if (registros == null) {
            registros = new ArrayList<>();
        }
        return registros;
    }

    public void setRegistros(List<TuGrupo> registros) {
        this.registros = registros;
    }

    public TuGrupoServicioIface getTuGrupoServicio() {
        return tuGrupoServicio;
    }

    public void setTuGrupoServicio(TuGrupoServicioIface tuGrupoServicio) {
        this.tuGrupoServicio = tuGrupoServicio;
    }

    public LazyDataModel<TuGrupo> getLazyGrupos() {
        return lazyGrupos;
    }

    public void setLazyGrupos(LazyDataModel<TuGrupo> lazyGrupos) {
        this.lazyGrupos = lazyGrupos;
    }

    public LazyDataModel<Usuario> getLazyUsuarios() {
        return lazyUsuarios;
    }

    public void setLazyUsuarios(LazyDataModel<Usuario> lazyUsuarios) {
        this.lazyUsuarios = lazyUsuarios;
    }

    public List<Usuario> getRegistrosUsuarios() {
        if (registrosUsuarios == null) {
            registrosUsuarios = new ArrayList<>();
        }
        return registrosUsuarios;
    }

    public void setRegistrosUsuarios(List<Usuario> registrosUsuarios) {
        this.registrosUsuarios = registrosUsuarios;
    }

    public ParamConsulta getParamConsultaGrupoUsuarios() {
        if (paramConsultaGrupoUsuarios == null) {
            paramConsultaGrupoUsuarios = new ParamConsulta();
        }
        return paramConsultaGrupoUsuarios;
    }

    public void setParamConsultaGrupoUsuarios(ParamConsulta paramConsultaGrupoUsuarios) {
        this.paramConsultaGrupoUsuarios = paramConsultaGrupoUsuarios;
    }

    public ParamConsulta getParamConsultaUsuarios() {
        if (paramConsultaUsuarios == null) {
            paramConsultaUsuarios = new ParamConsulta();
        }
        return paramConsultaUsuarios;
    }

    public void setParamConsultaUsuarios(ParamConsulta paramConsultaUsuarios) {
        this.paramConsultaUsuarios = paramConsultaUsuarios;
    }

    public LazyDataModel<TuGrupoUsuario> getLazyGrupoUsuario() {
        return lazyGrupoUsuario;
    }

    public void setLazyGrupoUsuario(LazyDataModel<TuGrupoUsuario> lazyGrupoUsuario) {
        this.lazyGrupoUsuario = lazyGrupoUsuario;
    }

    public List<TuGrupoUsuario> getRegistrosGrupoUsuarios() {
        if (registrosGrupoUsuarios == null) {
            registrosGrupoUsuarios = new ArrayList<>();
        }
        return registrosGrupoUsuarios;
    }

    public void setRegistrosGrupoUsuarios(List<TuGrupoUsuario> registrosGrupoUsuarios) {
        this.registrosGrupoUsuarios = registrosGrupoUsuarios;
    }

    public List<TuGrupoUsuario> getListaGrupoUsuario() {
        if (listaGrupoUsuario == null) {
            listaGrupoUsuario = new ArrayList<>();
        }
        return listaGrupoUsuario;
    }

    public void setListaGrupoUsuario(List<TuGrupoUsuario> listaGrupoUsuario) {
        this.listaGrupoUsuario = listaGrupoUsuario;
    }

    public TuGrupoUsuario getObjetoGrupoUsuario() {
        if (objetoGrupoUsuario == null) {
            objetoGrupoUsuario = new TuGrupoUsuario();
        }
        return objetoGrupoUsuario;
    }

    public void setObjetoGrupoUsuario(TuGrupoUsuario objetoGrupoUsuario) {
        this.objetoGrupoUsuario = objetoGrupoUsuario;
    }

    public List<TuGrupoUsuario> getRegistrosGrupoUsuariosParaBorrarDB() {
        if (registrosGrupoUsuariosParaBorrarDB == null) {
            registrosGrupoUsuariosParaBorrarDB = new ArrayList<>();
        }
        return registrosGrupoUsuariosParaBorrarDB;
    }

    public void setRegistrosGrupoUsuariosParaBorrarDB(List<TuGrupoUsuario> registrosGrupoUsuariosParaBorrarDB) {
        this.registrosGrupoUsuariosParaBorrarDB = registrosGrupoUsuariosParaBorrarDB;
    }

    public List<Maestro> getListaGrupoEstados() {
        if (listaGrupoEstados == null) {
            listaGrupoEstados = new ArrayList<>();
        }
        return listaGrupoEstados;
    }

    public void setListaGrupoEstados(List<Maestro> listaGrupoEstados) {
        this.listaGrupoEstados = listaGrupoEstados;
    }

    public HashMap<Integer, Maestro> getHashGrupoEstados() {
        if (hashGrupoEstados == null) {
            hashGrupoEstados = new HashMap<>();
        }
        return hashGrupoEstados;
    }

    public void setHashGrupoEstados(HashMap<Integer, Maestro> hashGrupoEstados) {
        this.hashGrupoEstados = hashGrupoEstados;
    }

    public TuGrupoBean() {
        this.objeto = new TuGrupo();
        Modulo mod = super.validarModulo(Modulo.ID_TU_GRUPOS);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyGrupos = new LazyDataModel<TuGrupo>() {

                private List<TuGrupo> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<TuGrupo> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(TuGrupo objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public TuGrupo getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (TuGrupo objeto : lista) {
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
        getTuGrupoServicio().cargaInial(this);
        listar();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        setDoAccion(ACCION_LISTAR_GRUPOS);
        getTuGrupoServicio().Accion(this);
        //procesoFinal();
    }

    public void refrescarUsuarios() {
        super.setAccion(Url.ACCION_LISTAR);
        setDoAccion(ACCION_LISTAR_USUARIOS);
        getTuGrupoServicio().Accion(this);
    }

    public void refrescarGrupoUsuarios() {
        super.setAccion(Url.ACCION_LISTAR);
        setDoAccion(ACCION_LISTAR_GRUPO_USUARIOS);
        getTuGrupoServicio().Accion(this);
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        setDoAccion(ACCION_LISTAR_GRUPOS);
        getTuGrupoServicio().Accion(this);
        procesoFinal();
    }

    public void ver(int _id) {
        setObjeto(new TuGrupo());
        this.getObjeto().setId(_id);
        setRegistrosGrupoUsuarios(new ArrayList<>());
        inicializarLazyGrupoUsuario(_id);
        super.setAccion(Url.ACCION_VER);
        getTuGrupoServicio().Accion(this);
        procesoFinal();
    }

    public void crear() {
        setObjeto(new TuGrupo());
        getObjeto().setActivo(true);
        getObjeto().getGrupoEstado().setReparto(true);
        limpiarAtributosTemporales();
        super.setAccion(Url.ACCION_CREAR);
        getTuGrupoServicio().Accion(this);
        procesoFinal();
    }

    public void guardar() {
        if (validarGuardadoUsuarios()) {
            super.setAccion(Url.ACCION_GUARDAR);
            getTuGrupoServicio().Accion(this);
            if (!isError()) {
                addMensaje("Se creado un nuevo grupo :" + this.getObjeto().getNombre());
            }
        }
        procesoFinal();
    }

    public void editar(int _id) {
        this.getObjeto().setId(_id);
        getParamConsultaGrupoUsuarios().setParametroConsulta1(_id);
        limpiarAtributosTemporales();
        super.setAccion(Url.ACCION_EDITAR);
        getTuGrupoServicio().Accion(this);
        procesoFinal();
    }

    public void modificar() {
        if (validarGuardadoUsuarios()) {
            super.setAccion(Url.ACCION_MODIFICAR);
            setDoAccion(ACCION_MODIFICAR_GRUPO);
            getTuGrupoServicio().Accion(this);
            if (!isError()) {
                addMensaje("Se ha modificado el grupo:" + this.getObjeto().getNombre());
            }
        }
        procesoFinal();
    }

    public void borrar(int _id, String nombreGrupo) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        setDoAccion(ACCION_BORRAR_GRUPO);
        getTuGrupoServicio().Accion(this);
        if (!isError()) {
            addMensaje("Se ha realizado el borrado del grupo : " + nombreGrupo);
        }
        procesoFinal();
    }

    public void limpiarAtributosTemporales() {
        setRegistrosGrupoUsuariosParaBorrarDB(new ArrayList<>());
        setRegistrosGrupoUsuarios(new ArrayList<>());
    }

    public void verAuditores() {
        inicializarLazyUsuario();
        PrimeFaces.current().executeScript("PF('dialogoAuditoresLista').show()");
        PrimeFaces.current().ajax().update("frmAuditorLista");
    }

    public void onRowReorder(ReorderEvent event) {

        TuGrupo grupoMover = registros.get(event.getFromIndex());
        TuGrupo grupoMovido = registros.get(event.getToIndex());

        if (grupoMover != null && grupoMovido != null) {
            int ordenReglaMovida = grupoMover.getOrden();
            int ordenReglaCambiada = grupoMovido.getOrden();
            grupoMover.setOrden(ordenReglaCambiada);
            grupoMovido.setOrden(ordenReglaMovida);
            this.setObjeto(grupoMover);
            super.setAccion(ACCION_MODIFICAR);
            setDoAccion(ACCION_CAMBIAR_ORDER);
            getTuGrupoServicio().Accion(this);
            this.setObjeto(grupoMovido);
            super.setAccion(ACCION_MODIFICAR);
            setDoAccion(ACCION_CAMBIAR_ORDER);
            getTuGrupoServicio().Accion(this);
        }
        procesoFinal();
    }

    public void inicializarLazyUsuario() {

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

            @Override
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

    public void inicializarLazyGrupoUsuario(int _id) {
        lazyGrupoUsuario = new LazyDataModel<TuGrupoUsuario>() {

            private List<TuGrupoUsuario> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaGrupoUsuarios().getCantidadRegistros();
            }

            @Override
            public List<TuGrupoUsuario> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                setParamConsultaGrupoUsuarios(new ParamConsulta());
                getParamConsultaGrupoUsuarios().setParametroConsulta1(_id);
                getParamConsultaGrupoUsuarios().setPrimerRegistro(primerRegistro);
                getParamConsultaGrupoUsuarios().setRegistrosPagina(registrosPagina);
                getParamConsultaGrupoUsuarios().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaGrupoUsuarios().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarGrupoUsuarios();
                lista = getRegistrosGrupoUsuarios();
                setRowCount(getParamConsultaGrupoUsuarios().getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(TuGrupoUsuario objeto) {
                return objeto.getId().toString();
            }

            @Override
            public TuGrupoUsuario getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (TuGrupoUsuario objeto : lista) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };
    }

    public void onRowSelectAuditor(SelectEvent event) {
        Usuario usuario = (Usuario) event.getObject();
        boolean agregar = true;
        if (getObjeto().getId() != null) {
            //agregar = getAuGrupoServicio().validarAuditor(usuario, this);
        }
        if (agregar) {
            for (TuGrupoUsuario user : getListaGrupoUsuario()) {
                if (user.getUsuario().getId().equals(usuario.getId())) {
                    agregar = false;
                }
            }
        }
        if (agregar) {
            TuGrupoUsuario auditor = new TuGrupoUsuario();
            auditor.setActivo(true);
            auditor.setUsuario(usuario);
            //getListaGrupoUsuario().add(auditor);
            getRegistrosGrupoUsuarios().add(auditor);
            PrimeFaces.current().ajax().update("frmCrearGrupo:pAuditorCrear");
            PrimeFaces.current().ajax().update("frmEditarGrupo:pAuditorEditar");
            PrimeFaces.current().executeScript("PF('dialogoAuditoresLista').hide()");
        } else {
            addError("El auditor ya se encuentra agregado");
            generarMensajes();
        }
    }

    public void borrarAuditor(int idUsuario) {
        List<TuGrupoUsuario> nuevaLista = new ArrayList();
        boolean eliminado = false;
        for (TuGrupoUsuario auditor : getRegistrosGrupoUsuarios()) {
            if (auditor.getUsuario().getId().equals(idUsuario)) {
                if (auditor.getId() != null) {
                    getRegistrosGrupoUsuariosParaBorrarDB().add(auditor);
                }
                eliminado = true;
            } else {
                nuevaLista.add(auditor);
            }
        }
        setRegistrosGrupoUsuarios(nuevaLista);
        if (!eliminado) {
            for (TuGrupoUsuario auditor : getRegistrosGrupoUsuarios()) {
                if (auditor.getUsuario().getId().equals(idUsuario)) {
                    if (auditor.getId() != null) {
                        getRegistrosGrupoUsuariosParaBorrarDB().add(auditor);
                    }
                }
            }
        }
        PrimeFaces.current().ajax().update("frmCrearGrupo:pAuditorCrear");
        PrimeFaces.current().ajax().update("frmEditarGrupo:tablaAuditoresEditar");
        generarMensajes();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_CREAR:
                    PrimeFaces.current().ajax().update("frmCrearGrupo");
                    PrimeFaces.current().executeScript("PF('dialogoCrearGrupo').show()");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_EDITAR:
                    PrimeFaces.current().ajax().update("frmEditarGrupo");
                    PrimeFaces.current().executeScript("PF('dialogoEditarGrupo').show()");
                    crearLog("Editar", getObjeto().toString());
                    break;
                case Url.ACCION_GUARDAR:
                    PrimeFaces.current().executeScript("PF('dialogoCrearGrupo').hide()");
                    PrimeFaces.current().ajax().update("frmGrupos");
                    StringBuilder builderInsercion = new StringBuilder();
                    for (TuGrupoUsuario registrosGrupoUsuario : getRegistrosGrupoUsuarios()) {
                        builderInsercion.append(registrosGrupoUsuario.toString());
                        builderInsercion.append(",");
                    }
                    crearLog("Guardar", getObjeto().toString() + " usuarios: " + builderInsercion.toString());
                    limpiarAtributosTemporales();
                    break;
                case Url.ACCION_MODIFICAR:
                    switch (getDoAccion()) {
                        case ACCION_CAMBIAR_ORDER:
                            PrimeFaces.current().ajax().update("frmGrupos");
                            crearLog("Modificar Orden", getObjeto().toString());
                            break;
                        case ACCION_MODIFICAR_GRUPO:
                            PrimeFaces.current().executeScript("PF('dialogoEditarGrupo').hide()");
                            PrimeFaces.current().ajax().update("frmGrupos");
                            StringBuilder builderActualizacion = new StringBuilder();
                            for (TuGrupoUsuario registrosGrupoUsuario : getRegistrosGrupoUsuarios()) {
                                builderActualizacion.append(registrosGrupoUsuario.toString());
                                builderActualizacion.append(",");
                            }
                            crearLog("Modificar Grupo", getObjeto().toString() + ", usuarios: " + builderActualizacion.toString());
                            limpiarAtributosTemporales();
                            break;
                    }
                    break;
                case Url.ACCION_BORRAR:
                    switch (getDoAccion()) {
                        case ACCION_BORRAR_GRUPO:
                            crearLog("Borrar grupo", getObjeto().toString());
                            break;
                        case ACCION_BORRAR_USUARIO:
                            break;
                    }
                    PrimeFaces.current().ajax().update("frmGrupos");

                    break;
                case Url.ACCION_LISTAR:
                    switch (getDoAccion()) {
                        case ACCION_LISTAR_GRUPOS:
                            PrimeFaces.current().ajax().update("frmGrupos");
                            crearLog("Listar", getObjeto().toString());
                            break;
                        case ACCION_LISTAR_USUARIOS:
                            break;
                    }

                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            PrimeFaces.current().ajax().update("frmReasignarGrupo");
                            PrimeFaces.current().executeScript("PF('dialogoReasignarGrupo').show()");
                            crearLog("Ver Reasignar", getObjeto().toString());
                            break;
                        case Url.ACCION_MODIFICAR:
                            PrimeFaces.current().ajax().update("frmReasignarGrupo");
                            crearLog("Reasignar", getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                case Url.ACCION_ADICIONAL_3:
                    break;
                case Url.ACCION_VER:
                    PrimeFaces.current().ajax().update("frmVerGrupo");
                    PrimeFaces.current().executeScript("PF('dialogoVerGrupo').show()");
                    crearLog(getObjeto().toString());
                    break;
            }
        }
        generarMensajes();
    }

    private boolean validarExistenciaUsuarioRolGrupo() {
        boolean esValido = true;
        HashMap<String, String> validador = new HashMap<>();
        for (TuGrupoUsuario grupoUsuario : getRegistrosGrupoUsuarios()) {
            String llave = grupoUsuario.getUsuario().getId() + "-" + grupoUsuario.getTipo();
            if (validador.get(llave) == null) {
                validador.put(llave, llave);
            } else {
                esValido = false;
            }
        }
        return esValido;
    }

    private boolean validarExistenciaRolesNecesarios() {
        boolean hayAbogado = false;
        boolean hayGestor = false;
        boolean hayMedico = false;
        for (TuGrupoUsuario grupoUsuario : getRegistrosGrupoUsuarios()) {
            if (TuGrupoUsuario.TIPO_GESTOR == grupoUsuario.getTipo() && grupoUsuario.getActivo()) {
                hayGestor = true;
            }
            if (TuGrupoUsuario.TIPO_ABOGADO == grupoUsuario.getTipo() && grupoUsuario.getActivo()) {
                hayAbogado = true;
            }
            if (TuGrupoUsuario.TIPO_MEDICO == grupoUsuario.getTipo() && grupoUsuario.getActivo()) {
                hayMedico = true;
            }
        }

        return hayGestor && hayAbogado && hayMedico;
    }

    private boolean validarSeleccionUsuario() {
        return !getRegistrosGrupoUsuarios().isEmpty();
    }

    private boolean validarSeleccionTipoAudiorParaUsuarios() {
        boolean esValido = true;
        for (TuGrupoUsuario grupoUsuario : getRegistrosGrupoUsuarios()) {
            if (grupoUsuario.getTipo() == 0) {
                esValido = false;
            }
        }
        return esValido;
    }

    private boolean validarGuardadoUsuarios() {
        boolean esValido = true;

        if (!validarSeleccionUsuario()) {
            addError("Se deber agregar auditores para continuar el proceso.");
            esValido = false;
        }

        if (esValido && !validarExistenciaUsuarioRolGrupo()) {
            addError("Si el usuario se repite no debe tener el mismo tipo auditor");
            esValido = false;
        }

        if (esValido && !validarExistenciaRolesNecesarios()) {
            addError("Se requiere para la creación del grupo los perfiles (Abogado, Médico, Gestor) que esten activos, estos no deben estar repetidos.");
            esValido = false;
        }

        if (esValido && !validarSeleccionTipoAudiorParaUsuarios()) {
            addError("Se requiere que todos los auditores tenga tipo auditor para continuar el proceso.");
            esValido = false;
        }

        if (esValido && !validarEstadoPorRepartoAutomatico()) {
            addError("El reparto automático requiere solo la selección de un tipo de estado.");
            esValido = false;
        }

        return esValido;
    }

    private boolean validarEstadoPorRepartoAutomatico() {
        boolean esValido = true;
        if (this.getObjeto().getGrupoEstado().isReparto()
                && this.getObjeto().getGrupoEstado().getSelectedEstados().size() > 1) {
            esValido = false;
        }
        return esValido;
    }

}
