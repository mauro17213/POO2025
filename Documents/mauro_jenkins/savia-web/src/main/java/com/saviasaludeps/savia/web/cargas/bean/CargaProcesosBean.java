/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.web.cargas.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.cargas.CarPeriodo;
import com.saviasaludeps.savia.dominio.cargas.CarProceso;
import com.saviasaludeps.savia.dominio.cargas.CarProcesoPrestador;
import com.saviasaludeps.savia.dominio.cargas.CarProcesoUsuario;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.cargas.servicio.CargaProcesosServicioIface;
import com.saviasaludeps.savia.web.contratacion.seleccion.bean.SelPrestadorBean;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author aguevara
 */
@ManagedBean
@ViewScoped
public class CargaProcesosBean extends Url {

    @Autowired
    private CargaProcesosServicioIface cargaProcesosServicio;
    private CarProceso objeto;
    private CarProcesoPrestador objetoPrestador;
    private CarPeriodo objetoPeriodo;
    private LazyDataModel<CarProceso> lazyProcesos;
    private LazyDataModel<CarProcesoPrestador> lazyPrestadores;
    private LazyDataModel<CarPeriodo> lazyPeriodos;
    private List<CarProceso> registros;
    private List<CarProcesoPrestador> registrosPrestadores;
    private List<CarPeriodo> registrosPeriodos;

    private List<Maestro> listaTipoDocumento;
    private HashMap<Integer, Maestro> hashTipoDocumento;
    private List<Maestro> listaClasePrestador;
    private HashMap<Integer, Maestro> hashClasePrestador;

    private ParamConsulta paramConsultaPrestadores;

    //listas Agregar
    private SelPrestadorBean selPrestadorBean;
    private List<CarProcesoPrestador> listaProcesoPrestadores;

    //Listas
    private List<CarPeriodo> listaProcesoPeriodo;
    private Integer idRolUsuario;
    private CarPeriodo objetoPeriodoOriginal;

    //Usuarios
    private CarProcesoUsuario objetoUsuarioProceso;
    private List<CarProcesoUsuario> listaProcesoUsuario;
    private List<Usuario> listaUsuarios;
    private List<CarProcesoUsuario> listaUsuarioEliminar;

    public CargaProcesosBean() {
        this.objeto = new CarProceso();
        this.objetoUsuarioProceso = new CarProcesoUsuario();
        this.objetoPrestador = new CarProcesoPrestador();
        this.objetoPeriodo = new CarPeriodo();
        Modulo _mod = super.validarModulo(Modulo.ID_CAR_PROCESOS);
        super.addListaParamConsultas(new ParamConsulta());
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        //super.getParamConsulta().setUsuarioId(super.getConexion().);
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyProcesos = new LazyDataModel<CarProceso>() {

                private List<CarProceso> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CarProceso> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(CarProceso objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public CarProceso getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CarProceso objeto : lista) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }

            };

            lazyPrestadores = new LazyDataModel<CarProcesoPrestador>() {

                private List<CarProcesoPrestador> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta(0).getCantidadRegistros();
                }

                @Override
                public List<CarProcesoPrestador> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(0).setPrimerRegistro(primerRegistro);
                    getParamConsulta(0).setRegistrosPagina(registrosPagina);
                    getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    //refrescarPrestadores();
                    lista = getRegistrosPrestadores();
                    setRowCount(getParamConsulta(0).getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(CarProcesoPrestador objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public CarProcesoPrestador getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CarProcesoPrestador objeto : lista) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }

            };

            super.addListaParamConsultas(new ParamConsulta());
            lazyPeriodos = new LazyDataModel<CarPeriodo>() {

                private List<CarPeriodo> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta(1).getCantidadRegistros();
                }

                @Override
                public List<CarPeriodo> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(1).setPrimerRegistro(primerRegistro);
                    getParamConsulta(1).setRegistrosPagina(registrosPagina);
                    getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(1).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    //refrescar();
                    lista = getRegistrosPeriodos();
                    setRowCount(getParamConsulta(1).getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(CarPeriodo objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public CarPeriodo getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CarPeriodo objeto : lista) {
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
        getCargaProcesosServicio().cargasInicial(this);
        listar();
    }

    public List<CarProceso> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CarProceso> registros) {
        this.registros = registros;
    }

    public List<CarProcesoPrestador> getRegistrosPrestadores() {
        return registrosPrestadores;
    }

    public void setRegistrosPrestadores(List<CarProcesoPrestador> registrosPrestadores) {
        this.registrosPrestadores = registrosPrestadores;
    }

    public CarProceso getObjeto() {
        return objeto;
    }

    public void setObjeto(CarProceso objetoProcesos) {
        this.objeto = objetoProcesos;
    }

    public LazyDataModel<CarProceso> getLazyProcesos() {
        return lazyProcesos;
    }

    public void setLazyProcesos(LazyDataModel<CarProceso> lazyProcesos) {
        this.lazyProcesos = lazyProcesos;
    }

    public LazyDataModel<CarProcesoPrestador> getLazyPrestadores() {
        return lazyPrestadores;
    }

    public void setLazyPrestadores(LazyDataModel<CarProcesoPrestador> lazyPrestadores) {
        this.lazyPrestadores = lazyPrestadores;
    }

    public LazyDataModel<CarPeriodo> getLazyPeriodos() {
        return lazyPeriodos;
    }

    public void setLazyPeriodos(LazyDataModel<CarPeriodo> lazyPeriodos) {
        this.lazyPeriodos = lazyPeriodos;
    }

    public List<CarPeriodo> getRegistrosPeriodos() {
        return registrosPeriodos;
    }

    public void setRegistrosPeriodos(List<CarPeriodo> registrosPeriodos) {
        this.registrosPeriodos = registrosPeriodos;
    }

    public CargaProcesosServicioIface getCargaProcesosServicio() {
        return cargaProcesosServicio;
    }

    public ParamConsulta getParamConsultaPrestadores() {
        if (paramConsultaPrestadores == null) {
            paramConsultaPrestadores = new ParamConsulta();
        }
        return paramConsultaPrestadores;
    }

    public void setParamConsultaPrestadores(ParamConsulta paramConsultaPrestadores) {
        this.paramConsultaPrestadores = paramConsultaPrestadores;
    }

    public void setCargaProcesosServicio(CargaProcesosServicioIface cargaProcesosServicio) {
        this.cargaProcesosServicio = cargaProcesosServicio;
    }

    public List<Maestro> getListaClasePrestador() {
        return listaClasePrestador;
    }

    public void setListaClasePrestador(List<Maestro> listaClasePrestador) {
        this.listaClasePrestador = listaClasePrestador;
    }

    public HashMap<Integer, Maestro> getHashClasePrestador() {
        return hashClasePrestador;
    }

    public void setHashClasePrestador(HashMap<Integer, Maestro> hashClasePrestador) {
        this.hashClasePrestador = hashClasePrestador;
    }

    /**
     * @return the selPrestadorBean
     */
    public SelPrestadorBean getSelPrestadorBean() {
        selPrestadorBean = (SelPrestadorBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("selPrestadorBean");
        return selPrestadorBean;
    }

    public void setSelPrestadorBean(SelPrestadorBean selPrestadorBean) {
        this.selPrestadorBean = selPrestadorBean;
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

    public CarProcesoPrestador getObjetoPrestador() {
        return objetoPrestador;
    }

    public void setObjetoPrestador(CarProcesoPrestador objetoPrestador) {
        this.objetoPrestador = objetoPrestador;
    }

    public CarPeriodo getObjetoPeriodo() {
        return objetoPeriodo;
    }

    public void setObjetoPeriodo(CarPeriodo objetoPeriodo) {
        this.objetoPeriodo = objetoPeriodo;
    }

    public List<CarProcesoPrestador> getListaProcesoPrestadores() {
        return listaProcesoPrestadores;
    }

    public void setListaProcesoPrestadores(List<CarProcesoPrestador> listaProcesoPrestadores) {
        this.listaProcesoPrestadores = listaProcesoPrestadores;
    }

    public CarProcesoUsuario getObjetoUsuarioProceso() {
        return objetoUsuarioProceso;
    }

    public void setObjetoUsuarioProceso(CarProcesoUsuario objetoUsuarioProceso) {
        this.objetoUsuarioProceso = objetoUsuarioProceso;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<CarProcesoUsuario> getListaProcesoUsuario() {
        return listaProcesoUsuario;
    }

    public void setListaProcesoUsuario(List<CarProcesoUsuario> listaProcesoUsuario) {
        this.listaProcesoUsuario = listaProcesoUsuario;
    }

    public List<CarProcesoUsuario> getListaUsuarioEliminar() {
        return listaUsuarioEliminar;
    }

    public void setListaUsuarioEliminar(List<CarProcesoUsuario> listaUsuarioEliminar) {
        this.listaUsuarioEliminar = listaUsuarioEliminar;
    }

    public List<CarPeriodo> getListaProcesoPeriodo() {
        return listaProcesoPeriodo;
    }

    public void setListaProcesoPeriodo(List<CarPeriodo> listaProcesoPeriodo) {
        this.listaProcesoPeriodo = listaProcesoPeriodo;
    }

    public Integer getIdRolUsuario() {
        return idRolUsuario;
    }

    public void setIdRolUsuario(Integer idRolUsuario) {
        this.idRolUsuario = idRolUsuario;
    }

    public CarPeriodo getObjetoPeriodoOriginal() {
        return objetoPeriodoOriginal;
    }

    public void setObjetoPeriodoOriginal(CarPeriodo objetoPeriodoOriginal) {
        this.objetoPeriodoOriginal = objetoPeriodoOriginal;
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCargaProcesosServicio().Accion(this);
    }

    public void ver(CarProceso obj) {
        getObjeto().setId(obj.getId());
        super.setAccion(ACCION_VER);
        getCargaProcesosServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        listaProcesoUsuario = new ArrayList<>();
        super.setAccion(ACCION_CREAR);
        getCargaProcesosServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getCargaProcesosServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(CarProceso obj) {
        setObjeto(obj);
        super.setAccion(ACCION_EDITAR);
        getCargaProcesosServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getCargaProcesosServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        objeto.setCarProcesoUsuariosList(new ArrayList<>());
        procesoFinal();
    }

    public void borrarr(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        getCargaProcesosServicio().Accion(this);
        procesoFinal();
    }

    public void consultarCntPrestador() {
        try {
            PrimeFaces.current().executeScript("PF('dialogoPrestadorLista').show()");
            PrimeFaces.current().ajax().update("frmPrestadorLista");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public void listarPrestadores(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_LISTAR);
        getCargaProcesosServicio().Accion(this);
        PrimeFaces.current().executeScript("PF('dialogoVerPrestadores').show()");
        PrimeFaces.current().ajax().update("frmVerPrestador:tablaPrestadores");
        procesoFinal();
    }

    public void refrescarPrestador() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_LISTAR);
        getCargaProcesosServicio().Accion(this);
        PrimeFaces.current().executeScript("PF('dialogoVerPrestadores').show()");
        PrimeFaces.current().ajax().update("frmVerPrestador:tablaPrestadores");

    }

    public void verPrestador(CarProcesoPrestador obj) {
        getObjeto().setId(obj.getId());
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_VER);
        getCargaProcesosServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerPrestador");
        PrimeFaces.current().executeScript("PF('dialogoVerPrestadores').show()");
        procesoFinal();
    }

    public void crearPrestador() {
        objetoPrestador = new CarProcesoPrestador();
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_CREAR);
        getCargaProcesosServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmCrearPrestador:tablaPrestadores");
        PrimeFaces.current().executeScript("PF('dialogoCrearPrestador').show()");
        procesoFinal();
    }

    public void guardarPrestador() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_GUARDAR);
        getCargaProcesosServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerPrestador:tablaPrestadores");
        PrimeFaces.current().executeScript("PF('dialogoCrearPrestador').hide()");
        procesoFinal();
    }

    public void editarPrestador(CarProcesoPrestador obj) {
        setObjetoPrestador(obj);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_EDITAR);
        getCargaProcesosServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmEditarPrestador");
        PrimeFaces.current().ajax().update("frmEditarPrestador");
        PrimeFaces.current().executeScript("PF('dialogoEditarPrestador').show()");
        procesoFinal();
    }

    public void modificarPrestador() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_MODIFICAR);
        getCargaProcesosServicio().Accion(this);
        if (!super.isError()) {
            refrescarPrestador();
            PrimeFaces.current().ajax().update("frmPrestador:tablaPrestadores");
            PrimeFaces.current().executeScript("PF('dialogoEditarPrestador').hide();");
        }
        procesoFinal();
    }

    public void borrarPrestador(CarProcesoPrestador obj) {
        setObjetoPrestador(obj);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_BORRAR);
        getCargaProcesosServicio().Accion(this);
        refrescarPrestador();
        PrimeFaces.current().resetInputs("frmVerPrestador");
        PrimeFaces.current().ajax().update("frmVerPrestador:tablaPrestadores");
        procesoFinal();
    }

    //Metodo para usar panelPrestadores
    public void cerrarDialogoCntPrestador() {

        // Obtener el prestador seleccionado
        CntPrestador prestadorSeleccionado = getSelPrestadorBean().getObjeto();
        if (prestadorSeleccionado != null) {
            // Verificar si el prestador ya está en la lista seleccionada o en la lista de CarProcesoPrestadores
            boolean prestadorYaExiste = false;

            // Verificar en listaSeleccionadaPrestadores
            if (getRegistrosPrestadores().contains(prestadorSeleccionado)) {
                prestadorYaExiste = true;
            }

            // Verificar en CarProcesoPrestadoresList
            for (CarProcesoPrestador procesoPrestador : getRegistrosPrestadores()) {
                if (procesoPrestador.getPrestador().getCodigoMinSalud().equals(prestadorSeleccionado.getCodigoMinSalud())) {
                    prestadorYaExiste = true;
                    break;
                }
            }
            // Solo agregar si el prestador no existe en ninguna lista
            if (!prestadorYaExiste) {

                objetoPrestador.setPrestador(prestadorSeleccionado);
                objetoPrestador.setProceso(this.getObjeto());// Asociar con el objeto actual
                objetoPrestador.setActivo(true);
            } else {
                // Mensaje de advertencia si el prestador ya existe
                this.addError("El prestador ya existe en la lista");
                PrimeFaces.current().ajax().update("frmCrearPrestador");
                PrimeFaces.current().executeScript("PF('dialogoPrestadorLista').hide()");

                return;
            }
        }
        // Ocultar el diálogo
        PrimeFaces.current().executeScript("PF('dialogoPrestadorLista').hide()");
        getSelPrestadorBean().setObjeto(new CntPrestador());

        // Actualizar la tabla de prestadores
        PrimeFaces.current().ajax().update("frmCrearPrestador");

    }

    public void crearUsuario() {

        setObjetoUsuarioProceso(new CarProcesoUsuario());
        getObjetoUsuarioProceso().setUsuario(new Usuario());
        PrimeFaces.current().ajax().update("frmCrearUsuario");
        PrimeFaces.current().executeScript("PF('dialogoCrearUsuario').show()");
    }

    public void guardarUsuario() {
        if (getObjeto().getCarProcesoUsuariosList() != null) {
            if (getObjetoUsuarioProceso().isModificado()) {
                List<CarProcesoUsuario> nuevaLista = new ArrayList<>();
                getObjeto().getCarProcesoUsuariosList().stream().filter(usuario -> (!usuario.getUsuario().getId().equals(getObjetoUsuarioProceso().getUsuarioAnterior().getId()))).forEachOrdered(funcionario -> {
                    nuevaLista.add(funcionario);
                });
                getObjeto().setCarProcesoUsuariosList(nuevaLista);
            }
            if (getObjeto().getCarProcesoUsuariosList().stream().filter(usuario -> usuario.getUsuario().getId().equals(getObjetoUsuarioProceso().getUsuario().getId())).count() > 0) {
                this.addError("El usuario ya existe en la lista");
            }

        } else {
            getObjeto().setCarProcesoUsuariosList(new ArrayList<>());
        }
        if (this.getErrores().isEmpty()) {
            for (Usuario us : getListaUsuarios()) {
                if (us.getId().equals(getObjetoUsuarioProceso().getUsuario().getId())) {
                    getObjetoUsuarioProceso().getUsuario().setNombre(us.getNombre());
                    getObjetoUsuarioProceso().getUsuario().setUsuario(us.getUsuario());
                    break;
                }
            }
            getObjeto().getCarProcesoUsuariosList().add(getObjetoUsuarioProceso());
            PrimeFaces.current().ajax().update("frmCrear:pUsuarios"); // Actualiza la tabla principal
            PrimeFaces.current().ajax().update("frmCrearUsuario");
            PrimeFaces.current().ajax().update("frmEditar:pUsuarios");
            PrimeFaces.current().executeScript("PF('dialogoCrearUsuario').hide()"); // Cierra el diálogo
        } else {
            this.generarMensajes();
        }

    }

    public void editarUsuario(CarProcesoUsuario usuarioEditar) {
        // Establecer el objeto que se va a editar
        setObjetoUsuarioProceso(usuarioEditar);

        // Asegúrate de que el usuario tiene un ID
        if (usuarioEditar.getId() == null) {
            usuarioEditar.setUsuarioAnterior(usuarioEditar.getUsuario());
        }

        // Actualizar la vista y mostrar el diálogo
        PrimeFaces.current().ajax().update("frmCrear:pUsuarios"); // Actualiza el formulario
        PrimeFaces.current().ajax().update("frmEditar:pUsuarios");
        PrimeFaces.current().executeScript("PF('dialogoCrearUsuario').show()"); // Muestra el diálogo
    }

    public void modificarUsuario() {

        for (Usuario us : getListaUsuarios()) {
            if (us.getId().equals(getObjetoUsuarioProceso().getUsuario().getId())) {
                getObjetoUsuarioProceso().getUsuario().setNombre(us.getNombre());
                getObjetoUsuarioProceso().getUsuario().setUsuario(us.getUsuario());
                getObjetoUsuarioProceso().getUsuario().setActivo(us.isActivo());
                break;
            }
        }
        List<CarProcesoUsuario> nuevaLista = new ArrayList<>();
        getObjeto().getCarProcesoUsuariosList().forEach(usuario -> {
            if (usuario.getId() != null && usuario.getId().equals(getObjetoUsuarioProceso().getId())) {
                nuevaLista.add(getObjetoUsuarioProceso());
            } else {
                nuevaLista.add(usuario);
            }
        });
        // Actualiza la vista
        PrimeFaces.current().ajax().update("frmCrear:pUsuarios");
        PrimeFaces.current().ajax().update("frmEditar:pUsuarios");
        PrimeFaces.current().executeScript("PF('dialogoCrearUsuario').hide()");

    }

    public void borrarUsuario(CarProcesoUsuario usuarioEliminar) {
        if (getListaUsuarioEliminar() == null) {
            setListaUsuarioEliminar(new ArrayList<>());
        }
        List<CarProcesoUsuario> nuevaLista = new ArrayList<>();
        for (CarProcesoUsuario usuario : getObjeto().getCarProcesoUsuariosList()) {
            if (usuario.getUsuario().getId().equals(usuarioEliminar.getUsuario().getId())) {
                if (usuario.getId() != null) {
                    getListaUsuarioEliminar().add(usuario);
                }
            } else {
                nuevaLista.add(usuario);
            }
        }
        getObjeto().setCarProcesoUsuariosList(nuevaLista);
        // Actualiza vistas
        PrimeFaces.current().ajax().update("frmCrear:pUsuarios");
        PrimeFaces.current().ajax().update("frmEditar:pUsuarios");
        addMensaje("Pendiente de presionar el botón guardar");
        generarMensajes();
    }

    public String getTipoDocumento(int id) {
        String mensaje = "";
        try {
            mensaje = hashTipoDocumento.get(id).getNombre();
        } catch (Exception e) {

        }
        return mensaje;
    }

    public void listarPeriodos(int id) {
        getObjeto().setId(id);        
        setIdRolUsuario(super.getConexion().getUsuario().getEmpresa().getId());
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_LISTAR);
        getCargaProcesosServicio().Accion(this);
        setObjetoPeriodoOriginal(clonarObjeto(getObjetoPeriodo() ));       
        PrimeFaces.current().executeScript("PF('dialogoVerPeriodos').show()");
        PrimeFaces.current().ajax().update("frmVerPeriodo");
        procesoFinal();
    }

    public void crearPeriodo() {
        objetoPeriodo = new CarPeriodo();
        getObjetoPeriodo().setCarProceso(objeto);
        PrimeFaces.current().ajax().update("frmCrearPeriodo");
        PrimeFaces.current().executeScript("PF('dialogoCrearPeriodos').show()");
        procesoFinal();
    }

    public void guardarPeriodos() {
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_GUARDAR);
        // Si las validaciones fallan, se detiene el proceso
        if (!validarPeriodo()) {
            return;
        }
        getCargaProcesosServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerPeriodo");
        PrimeFaces.current().executeScript("PF('dialogoCrearPeriodos').hide()");

        procesoFinal();
    }

    public boolean validarPeriodo() {
        // Obtener las fechas del objeto actual (editado)
        Date fechaInicio = getObjetoPeriodo().getFechaInicio();
        Date fechaFin = getObjetoPeriodo().getFechaFin();

        // Obtener las fechas del período original (almacenadas antes de la edición)
        Date fechaInicioOriginal = getObjetoPeriodoOriginal().getFechaInicio();
        Date fechaFinOriginal = getObjetoPeriodoOriginal().getFechaFin();

        // Verificar si las fechas fueron modificadas
        boolean fechasModificadas = (fechaInicioOriginal == null || fechaFinOriginal == null)
                || !fechaInicioOriginal.equals(fechaInicio)
                || !fechaFinOriginal.equals(fechaFin);

        // Solo validar fechas si estas fueron modificadas
        if (fechasModificadas) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            Date fechaActual = cal.getTime();

            // Validar que la fecha de fin no sea anterior al día actual
            if (fechaFin != null && fechaFin.before(fechaActual)) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación",
                                "La fecha de fin no puede ser anterior al día actual."));
                return false;
            }

            // Validar que la fecha de inicio no sea posterior a la fecha de fin
            if (fechaInicio != null && fechaFin != null && fechaInicio.after(fechaFin)) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación",
                                "La fecha de inicio no puede ser posterior a la fecha de fin."));
                return false;
            }

            // Validar solapamiento con otros periodos
            for (CarPeriodo periodoExistente : getRegistrosPeriodos()) {
                if (!periodoExistente.getId().equals(getObjetoPeriodo().getId())
                        && periodosSolapados(periodoExistente.getFechaInicio(), periodoExistente.getFechaFin(), fechaInicio, fechaFin)) {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de validación",
                                    "Ya existe un periodo registrado dentro del rango de fechas especificado."));
                    return false;
                }
            }
        }

        return true;
    }

// Método para verificar si dos rangos de fechas se solapan, incluyendo coincidencias exactas en las fechas de inicio y fin
    private boolean periodosSolapados(Date inicio1, Date fin1, Date inicio2, Date fin2) {
        // Condición para detectar solapamiento o fechas de inicio/fin coincidentes
        return (inicio1.before(fin2) && fin1.after(inicio2))
                || // solapamiento entre rangos
                inicio1.equals(fin2)
                || // inicio del nuevo periodo coincide con fin del existente
                inicio2.equals(fin1);   // inicio del existente coincide con fin del nuevo periodo
    }

    public void editarPeriodo(CarPeriodo periodo) {
        setObjetoPeriodo(periodo);
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_EDITAR);

        getCargaProcesosServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmEditarPeriodo");
        PrimeFaces.current().executeScript("PF('dialogoEditarPeriodo').show()");
        procesoFinal();
    }

    public void modificarPeriodo() {
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_MODIFICAR);
        // Si las validaciones fallan, se detiene el proceso
        if (!validarPeriodo()) {
            return;
        }
        getCargaProcesosServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerPeriodo");
        PrimeFaces.current().executeScript("PF('dialogoEditarPeriodo').hide();");

        procesoFinal();
    }

    public void borrarPeriodos(CarPeriodo periodo) {
        objetoPeriodo.setId(periodo.getId());
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_BORRAR);
        getCargaProcesosServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerPeriodo");
        procesoFinal();

    }

    private CarPeriodo clonarObjeto(CarPeriodo periodo) {
        CarPeriodo copia = new CarPeriodo();
        copia.setId(periodo.getId());
        copia.setFechaInicio(periodo.getFechaInicio());
        copia.setFechaFin(periodo.getFechaFin());
        copia.setNombre(periodo.getNombre());
        return copia;
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmProcesos");
                case Url.ACCION_VER:
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1:

                    break;
                case Url.ACCION_ADICIONAL_2: // Prestadores
                    switch (getDoAccion()) {
                        case Url.ACCION_LISTAR:
                        case Url.ACCION_GUARDAR:
                        case Url.ACCION_MODIFICAR:
                        case Url.ACCION_BORRAR:
                            PrimeFaces.current().ajax().update("frmVerPrestadores");
                        case Url.ACCION_VER:
                        case Url.ACCION_CREAR:
                        case Url.ACCION_EDITAR:
                            crearLog("Prestadores", getObjetoPrestador().toString());
                            break;
                    }
                    crearLog("Agregar prestador", getObjetoPrestador().toString());
                    PrimeFaces.current().ajax().update("frmVerPrestadores");
                    break;
                case Url.ACCION_ADICIONAL_3: // Periodos
                    switch (getDoAccion()) {
                        case Url.ACCION_LISTAR:
                        case Url.ACCION_GUARDAR:
                        case Url.ACCION_MODIFICAR:
                        case Url.ACCION_BORRAR:
                            PrimeFaces.current().ajax().update("frmVerPeriodo");
                        case Url.ACCION_VER:
                        case Url.ACCION_CREAR:
                        case Url.ACCION_EDITAR:
                            crearLog("Periodos", getObjetoPeriodo().toString());
                            break;
                    }
                    crearLog("Agregar periodo", getObjetoPeriodo().toString());
                    PrimeFaces.current().ajax().update("frmVerPeriodo");
                    break;
            }
        }
        generarMensajes();
    }

}
