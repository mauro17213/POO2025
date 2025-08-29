/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.atencionusuario.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.atencionusuario.AusGrupo;
import com.saviasaludeps.savia.dominio.atencionusuario.AusGrupoUsuario;
import com.saviasaludeps.savia.web.atencionusuario.servicio.AusGrupoServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.el.ValueExpression;
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
 * @author jorge eliecer perez
 */
@ManagedBean
@ViewScoped
public class AusGrupoBean extends Url {

    public static final char ACCION_LISTAR_GRUPOS = 'a';
    public static final char ACCION_CAMBIAR_ORDER = 'b';
    public static final char ACCION_MODIFICAR_GRUPO = 'c';
    public static final char ACCION_LISTAR_USUARIOS = 'd';
    public static final char ACCION_LISTAR_GRUPO_USUARIOS = 'e';
    public static final char ACCION_BORRAR_GRUPO = 'f';
    public static final char ACCION_BORRAR_USUARIO = 'g';
    //2025-03-27 jyperez se manejará este parámetro con el id correspondiente a los gestores
    public static final String GN_ROL_AUS_GESTOR = "63";

    @Autowired
    private AusGrupoServicioIface ausGrupoServicio;
    private AusGrupo objeto;
    private AusGrupoUsuario objetoGrupoUsuario;
    private List<AusGrupo> registros;
    private LazyDataModel<AusGrupo> lazyGrupos;

    private List<Usuario> registrosUsuarios;
    private LazyDataModel<Usuario> lazyUsuarios;

    private List<AusGrupoUsuario> registrosGrupoUsuarios;
    private List<AusGrupoUsuario> registrosGrupoUsuariosParaBorrarDB;
    private LazyDataModel<AusGrupoUsuario> lazyGrupoUsuario;

    private ParamConsulta paramConsultaUsuarios;
    private ParamConsulta paramConsultaGrupoUsuarios;

    private List<Maestro> listaGrupoEstados;
    private HashMap<Integer, Maestro> hashGrupoEstados;

    public AusGrupo getObjeto() {
        if (objeto == null) {
            objeto = new AusGrupo();
        }
        return objeto;
    }

    public void setObjeto(AusGrupo objeto) {
        this.objeto = objeto;
    }

    public List<AusGrupo> getRegistros() {
        if (registros == null) {
            registros = new ArrayList<>();
        }
        return registros;
    }

    public void setRegistros(List<AusGrupo> registros) {
        this.registros = registros;
    }

    public AusGrupoServicioIface getAusGrupoServicio() {
        return ausGrupoServicio;
    }

    public void setAusGrupoServicio(AusGrupoServicioIface ausGrupoServicio) {
        this.ausGrupoServicio = ausGrupoServicio;
    }

    public LazyDataModel<AusGrupo> getLazyGrupos() {
        return lazyGrupos;
    }

    public void setLazyGrupos(LazyDataModel<AusGrupo> lazyGrupos) {
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

    public LazyDataModel<AusGrupoUsuario> getLazyGrupoUsuario() {
        return lazyGrupoUsuario;
    }

    public void setLazyGrupoUsuario(LazyDataModel<AusGrupoUsuario> lazyGrupoUsuario) {
        this.lazyGrupoUsuario = lazyGrupoUsuario;
    }

    public List<AusGrupoUsuario> getRegistrosGrupoUsuarios() {
        if (registrosGrupoUsuarios == null) {
            registrosGrupoUsuarios = new ArrayList<>();
        }
        return registrosGrupoUsuarios;
    }

    public void setRegistrosGrupoUsuarios(List<AusGrupoUsuario> registrosGrupoUsuarios) {
        this.registrosGrupoUsuarios = registrosGrupoUsuarios;
    }

    public AusGrupoUsuario getObjetoGrupoUsuario() {
        if (objetoGrupoUsuario == null) {
            objetoGrupoUsuario = new AusGrupoUsuario();
        }
        return objetoGrupoUsuario;
    }

    public void setObjetoGrupoUsuario(AusGrupoUsuario objetoGrupoUsuario) {
        this.objetoGrupoUsuario = objetoGrupoUsuario;
    }

    public List<AusGrupoUsuario> getRegistrosGrupoUsuariosParaBorrarDB() {
        if (registrosGrupoUsuariosParaBorrarDB == null) {
            registrosGrupoUsuariosParaBorrarDB = new ArrayList<>();
        }
        return registrosGrupoUsuariosParaBorrarDB;
    }

    public void setRegistrosGrupoUsuariosParaBorrarDB(List<AusGrupoUsuario> registrosGrupoUsuariosParaBorrarDB) {
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

    public AusGrupoBean() {
        this.objeto = new AusGrupo();
        Modulo mod = super.validarModulo(Modulo.ID_AUS_GRUPOS);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyGrupos = new LazyDataModel<AusGrupo>() {

                private List<AusGrupo> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<AusGrupo> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(AusGrupo objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public AusGrupo getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (AusGrupo objeto : lista) {
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
        getAusGrupoServicio().cargaInial(this);
        listar();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        setDoAccion(ACCION_LISTAR_GRUPOS);
        getAusGrupoServicio().Accion(this);
        //procesoFinal();
    }

    public void refrescarUsuarios() {
        super.setAccion(Url.ACCION_LISTAR);
        setDoAccion(ACCION_LISTAR_USUARIOS);
        getAusGrupoServicio().Accion(this);
    }

    public void refrescarGrupoUsuarios() {
        super.setAccion(Url.ACCION_LISTAR);
        setDoAccion(ACCION_LISTAR_GRUPO_USUARIOS);
        getAusGrupoServicio().Accion(this);
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        setDoAccion(ACCION_LISTAR_GRUPOS);
        getAusGrupoServicio().Accion(this);
        procesoFinal();
    }

    public void ver(int _id) {
        setObjeto(new AusGrupo());
        this.getObjeto().setId(_id);
        setRegistrosGrupoUsuarios(new ArrayList<>());
        inicializarLazyGrupoUsuario(_id);
        super.setAccion(Url.ACCION_VER);
        getAusGrupoServicio().Accion(this);
        procesoFinal();
    }

    public void crear() {
        setObjeto(new AusGrupo());
        limpiarAtributosTemporales();
        super.setAccion(Url.ACCION_CREAR);
        getAusGrupoServicio().Accion(this);
        procesoFinal();
    }

    public void guardar() {
        if (validarGuardadoUsuarios()) {
            super.setAccion(Url.ACCION_GUARDAR);
            getAusGrupoServicio().Accion(this);
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
        getAusGrupoServicio().Accion(this);
        procesoFinal();
    }

    public void modificar() {
        if (validarGuardadoUsuarios()) {
            super.setAccion(Url.ACCION_MODIFICAR);
            setDoAccion(ACCION_MODIFICAR_GRUPO);
            getAusGrupoServicio().Accion(this);
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
        getAusGrupoServicio().Accion(this);
        if (!isError()) {
            addMensaje("Se ha realizado el borrado del grupo : " + nombreGrupo);
        }
        procesoFinal();
    }

    public void limpiarAtributosTemporales() {
        setRegistrosGrupoUsuariosParaBorrarDB(new ArrayList<>());
        setRegistrosGrupoUsuarios(new ArrayList<>());
    }

    public void verGestores() {
        inicializarLazyUsuario();
        PrimeFaces.current().executeScript("PF('dialogoGestoresLista').show()");
        PrimeFaces.current().ajax().update("frmGestorLista");
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
                //filtros = new HashMap();
                FilterMeta valor = new FilterMeta();
                valor.setFilterValue(GN_ROL_AUS_GESTOR);
                filtros.put("listaRoles", valor);
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
        lazyGrupoUsuario = new LazyDataModel<AusGrupoUsuario>() {

            private List<AusGrupoUsuario> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaGrupoUsuarios().getCantidadRegistros();
            }

            @Override
            public List<AusGrupoUsuario> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
            public String getRowKey(AusGrupoUsuario objeto) {
                return objeto.getId().toString();
            }

            @Override
            public AusGrupoUsuario getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (AusGrupoUsuario objeto : lista) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };
    }

    public void onRowSelectGestor(SelectEvent event) {
        Usuario usuario = (Usuario) event.getObject();
        boolean agregar = true;
        for (AusGrupoUsuario user : getRegistrosGrupoUsuarios()) {
            if (user.getUsuario().getId().equals(usuario.getId())) {
                agregar = false;
            }
        }
        if (agregar) {
            AusGrupoUsuario auditor = new AusGrupoUsuario();
            auditor.setActivo(true);
            auditor.setUsuario(usuario);
            //getListaGrupoUsuario().add(auditor);
            getRegistrosGrupoUsuarios().add(auditor);
            PrimeFaces.current().ajax().update("frmCrearGrupo:pAuditorCrear");
            PrimeFaces.current().ajax().update("frmEditarGrupo:pAuditorEditar");
            PrimeFaces.current().executeScript("PF('dialogoGestoresLista').hide()");
        } else {
            addError("El gestor ya se encuentra agregado");
            generarMensajes();
        }
    }

    public void borrarGestor(int idUsuario) {
        List<AusGrupoUsuario> nuevaLista = new ArrayList();
        boolean eliminado = false;
        for (AusGrupoUsuario auditor : getRegistrosGrupoUsuarios()) {
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
            for (AusGrupoUsuario auditor : getRegistrosGrupoUsuarios()) {
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
                    for (AusGrupoUsuario registrosGrupoUsuario : getRegistrosGrupoUsuarios()) {
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
                            for (AusGrupoUsuario registrosGrupoUsuario : getRegistrosGrupoUsuarios()) {
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

    private boolean validarSeleccionUsuario() {
        return !getRegistrosGrupoUsuarios().isEmpty();
    }

    private boolean validarGuardadoUsuarios() {
        boolean esValido = true;

        if (!validarSeleccionUsuario()) {
            addError("Se deber agregar gestores para continuar el proceso.");
            esValido = false;
        }

        return esValido;
    }

}
