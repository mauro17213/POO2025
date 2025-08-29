/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.gestion.atencion.bean;

import com.saviasaludeps.savia.dominio.administracion.GnSede;
import com.saviasaludeps.savia.dominio.administracion.GnSedeHorario;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatReporteSede;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatReporteTaquilla;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeConfiguracion;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeFuncionario;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeTaquilla;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTaquillaFuncionario;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTaquillaServicio;
import com.saviasaludeps.savia.dominio.gestionAtencion.HistoricoSede;
import com.saviasaludeps.savia.web.gestion.atencion.servicio.GestionSedeServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_LISTAR;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.charts.bar.BarChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author sgiraldov
 */
@ManagedBean
@ViewScoped
public class GestionSedeBean extends Url {

    @Autowired
    private GestionSedeServicioIface gestionSedeServicio;
    private GnSede objeto;
    private List<GnSede> registros;
    private LazyDataModel<GnSede> lazySedes;

    private List<Ubicacion> listaUbicaciones;
    private List<Usuario> listaUsuarios;
    private List<Maestro> listaTiposSede;
    private HashMap<Integer, Maestro> hashTiposSede;
    private List<Maestro> listaServiciosTaquilla;
    private HashMap<Integer, Maestro> hashServiciosTaquilla;
    private List<Usuario> listaUsuariosHorarios;

    //Objetos necesarios
    private GatSedeConfiguracion objetoConfiguracion;
    private GatSedeTaquilla objetoTaquilla;
    private Maestro tipo;
    private GatTaquillaFuncionario objetoFuncionarioTaquilla;
    private GatSedeFuncionario objetoFuncionarioSede;
    private GnSedeHorario objetoHorario;

    //Listas para eliminar
    private List<GatSedeFuncionario> listaFuncionariosEliminar;
    private List<GatTaquillaServicio> listaTaquillaServiciosEliminar;
    private List<GatSedeTaquilla> listaTaquillasEliminar;
    private List<GnSedeHorario> listaHorariosEliminar;

    //Variables auxiliares
    private String listaFiltro;
    private int idTaquillaLiberar;
    private List<String> horarioSeleccionado;

    //Variables para reportes
    private GatReporteSede objetoReporteSede;
    private BarChartModel graficoBarraModelo;
    private List<GatReporteTaquilla> listaReportesTaquilla;

    //Variables para historicos
    private Date fechaInicio;
    private Date fechaFin;
    private List<HistoricoSede> listaHistorico;

    public GestionSedeBean() {
        this.objeto = new GnSede();
        Modulo _mod = super.validarModulo(Modulo.ID_GAT_SEDE);
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazySedes = new LazyDataModel<GnSede>() {

                private List<GnSede> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<GnSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(GnSede objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public GnSede getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (GnSede objeto : lista) {
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
        getGestionSedeServicio().cargasInicial(this);
        listar();
    }

    public GestionSedeServicioIface getGestionSedeServicio() {
        return gestionSedeServicio;
    }

    public void setGestionSedeServicio(GestionSedeServicioIface gestionSedeServicio) {
        this.gestionSedeServicio = gestionSedeServicio;
    }

    public GnSede getObjeto() {
        return objeto;
    }

    public void setObjeto(GnSede objeto) {
        this.objeto = objeto;
    }

    public List<GnSede> getRegistros() {
        return registros;
    }

    public void setRegistros(List<GnSede> registros) {
        this.registros = registros;
    }

    public LazyDataModel<GnSede> getLazySedes() {
        return lazySedes;
    }

    public void setLazySedes(LazyDataModel<GnSede> lazySedes) {
        this.lazySedes = lazySedes;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Maestro> getListaTiposSede() {
        return listaTiposSede;
    }

    public void setListaTiposSede(List<Maestro> listaTiposSede) {
        this.listaTiposSede = listaTiposSede;
    }

    public List<Ubicacion> getListaUbicaciones() {
        return listaUbicaciones;
    }

    public void setListaUbicaciones(List<Ubicacion> listaUbicaciones) {
        this.listaUbicaciones = listaUbicaciones;
    }

    public HashMap<Integer, Maestro> getHashTiposSede() {
        return hashTiposSede;
    }

    public void setHashTiposSede(HashMap<Integer, Maestro> hashTiposSede) {
        this.hashTiposSede = hashTiposSede;
    }

    public List<Maestro> getListaServiciosTaquilla() {
        return listaServiciosTaquilla;
    }

    public void setListaServiciosTaquilla(List<Maestro> listaServiciosTaquilla) {
        this.listaServiciosTaquilla = listaServiciosTaquilla;
    }

    public HashMap<Integer, Maestro> getHashServiciosTaquilla() {
        return hashServiciosTaquilla;
    }

    public void setHashServiciosTaquilla(HashMap<Integer, Maestro> hashServiciosTaquilla) {
        this.hashServiciosTaquilla = hashServiciosTaquilla;
    }

    public GatSedeConfiguracion getObjetoConfiguracion() {
        return objetoConfiguracion;
    }

    public void setObjetoConfiguracion(GatSedeConfiguracion objetoConfiguracion) {
        this.objetoConfiguracion = objetoConfiguracion;
    }

    public GatSedeTaquilla getObjetoTaquilla() {
        return objetoTaquilla;
    }

    public void setObjetoTaquilla(GatSedeTaquilla objetoTaquilla) {
        this.objetoTaquilla = objetoTaquilla;
    }

    public Maestro getTipo() {
        return tipo;
    }

    public void setTipo(Maestro tipo) {
        this.tipo = tipo;
    }

    public GatTaquillaFuncionario getObjetoFuncionarioTaquilla() {
        return objetoFuncionarioTaquilla;
    }

    public void setObjetoFuncionarioTaquilla(GatTaquillaFuncionario objetoFuncionarioTaquilla) {
        this.objetoFuncionarioTaquilla = objetoFuncionarioTaquilla;
    }

    public GatSedeFuncionario getObjetoFuncionarioSede() {
        return objetoFuncionarioSede;
    }

    public void setObjetoFuncionarioSede(GatSedeFuncionario objetoFuncionarioSede) {
        this.objetoFuncionarioSede = objetoFuncionarioSede;
    }

    public List<Usuario> getListaUsuariosHorarios() {
        return listaUsuariosHorarios;
    }

    public void setListaUsuariosHorarios(List<Usuario> listaUsuariosHorarios) {
        this.listaUsuariosHorarios = listaUsuariosHorarios;
    }

    public GnSedeHorario getObjetoHorario() {
        return objetoHorario;
    }

    public void setObjetoHorario(GnSedeHorario objetoHorario) {
        this.objetoHorario = objetoHorario;
    }

    public List<GatSedeFuncionario> getListaFuncionariosEliminar() {
        return listaFuncionariosEliminar;
    }

    public void setListaFuncionariosEliminar(List<GatSedeFuncionario> listaFuncionariosEliminar) {
        this.listaFuncionariosEliminar = listaFuncionariosEliminar;
    }

    public List<GatTaquillaServicio> getListaTaquillaServiciosEliminar() {
        return listaTaquillaServiciosEliminar;
    }

    public void setListaTaquillaServiciosEliminar(List<GatTaquillaServicio> listaTaquillaServiciosEliminar) {
        this.listaTaquillaServiciosEliminar = listaTaquillaServiciosEliminar;
    }

    public List<GatSedeTaquilla> getListaTaquillasEliminar() {
        return listaTaquillasEliminar;
    }

    public void setListaTaquillasEliminar(List<GatSedeTaquilla> listaTaquillasEliminar) {
        this.listaTaquillasEliminar = listaTaquillasEliminar;
    }

    public List<GnSedeHorario> getListaHorariosEliminar() {
        return listaHorariosEliminar;
    }

    public void setListaHorariosEliminar(List<GnSedeHorario> listaHorariosEliminar) {
        this.listaHorariosEliminar = listaHorariosEliminar;
    }

    public String getListaFiltro() {
        return listaFiltro;
    }

    public void setListaFiltro(String listaFiltro) {
        this.listaFiltro = listaFiltro;
    }

    public int getIdTaquillaLiberar() {
        return idTaquillaLiberar;
    }

    public void setIdTaquillaLiberar(int idTaquillaLiberar) {
        this.idTaquillaLiberar = idTaquillaLiberar;
    }

    public List<String> getHorarioSeleccionado() {
        return horarioSeleccionado;
    }

    public void setHorarioSeleccionado(List<String> horarioSeleccionado) {
        this.horarioSeleccionado = horarioSeleccionado;
    }

    public GatReporteSede getObjetoReporteSede() {
        return objetoReporteSede;
    }

    public void setObjetoReporteSede(GatReporteSede objetoReporteSede) {
        this.objetoReporteSede = objetoReporteSede;
    }

    public BarChartModel getGraficoBarraModelo() {
        return graficoBarraModelo;
    }

    public void setGraficoBarraModelo(BarChartModel graficoBarraModelo) {
        this.graficoBarraModelo = graficoBarraModelo;
    }

    public List<GatReporteTaquilla> getListaReportesTaquilla() {
        return listaReportesTaquilla;
    }

    public void setListaReportesTaquilla(List<GatReporteTaquilla> listaReportesTaquilla) {
        this.listaReportesTaquilla = listaReportesTaquilla;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<HistoricoSede> getListaHistorico() {
        return listaHistorico;
    }

    public void setListaHistorico(List<HistoricoSede> listaHistorico) {
        this.listaHistorico = listaHistorico;
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getGestionSedeServicio().Accion(this);
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(Url.ACCION_VER);
        getGestionSedeServicio().Accion(this);
        procesoFinal();
    }

    public void verConfigurar(int id) {
        getObjeto().setId(id);
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_VER);
        getGestionSedeServicio().Accion(this);
        procesoFinal();
    }

    public void guardarConfigurar() {
        super.setAccion(Url.ACCION_ADICIONAL_1);
        super.setDoAccion(Url.ACCION_GUARDAR);
        getGestionSedeServicio().Accion(this);
        procesoFinal();
    }

    public void crearFuncionarioSede() {
        setObjetoFuncionarioSede(new GatSedeFuncionario());
        getObjetoFuncionarioSede().setUsuarioId(new Usuario());
        getObjetoFuncionarioSede().setActivo(true);
        PrimeFaces.current().ajax().update("frmFuncionarioSede");
        PrimeFaces.current().executeScript("PF('dialogoFuncionarioSede').show()");
    }

    public void guardarFuncionarioSede() {
        if (getObjeto().getGatSedeFuncionarioList() != null) {
            if (getObjetoFuncionarioSede().isModificado()) {
                List<GatSedeFuncionario> nuevaLista = new ArrayList<>();
                getObjeto().getGatSedeFuncionarioList().stream().filter(funcionario -> (!funcionario.getUsuarioId().getId().equals(getObjetoFuncionarioSede().getUsuarioAnterior().getId()))).forEachOrdered(funcionario -> {
                    nuevaLista.add(funcionario);
                });
                getObjeto().setGatSedeFuncionarioList(nuevaLista);
            }
            if (getObjeto().getGatSedeFuncionarioList().stream().filter(funcionario -> funcionario.getUsuarioId().getId().equals(getObjetoFuncionarioSede().getUsuarioId().getId())).count() > 0) {
                this.addError("El usuario ya existe en la lista");
            }

        } else {
            getObjeto().setGatSedeFuncionarioList(new ArrayList<>());
        }
        if (this.getErrores().isEmpty()) {
            for (Usuario us : getListaUsuarios()) {
                if (us.getId().equals(getObjetoFuncionarioSede().getUsuarioId().getId())) {
                    getObjetoFuncionarioSede().getUsuarioId().setNombre(us.getNombre());
                    getObjetoFuncionarioSede().getUsuarioId().setUsuario(us.getUsuario());
                    break;
                }
            }
            getObjeto().getGatSedeFuncionarioList().add(getObjetoFuncionarioSede());
            PrimeFaces.current().ajax().update("frmConfigurar:pFuncionarioConfigurar");
            PrimeFaces.current().executeScript("PF('dialogoFuncionarioSede').hide()");
        } else {
            this.generarMensajes();
        }
    }

    public void editarFuncionarioSede(GatSedeFuncionario funcionarioEditar) {
        if (funcionarioEditar.getId() == null) {
            funcionarioEditar.setUsuarioAnterior(funcionarioEditar.getUsuarioId());
        }
        funcionarioEditar.setModificado(true);
        setObjetoFuncionarioSede(funcionarioEditar);
        PrimeFaces.current().ajax().update("frmFuncionarioSede");
        PrimeFaces.current().executeScript("PF('dialogoFuncionarioSede').show()");
    }

    public void modificarFuncionarioSede() {
        for (Usuario us : getListaUsuarios()) {
            if (us.getId().equals(getObjetoFuncionarioSede().getUsuarioId().getId())) {
                getObjetoFuncionarioSede().getUsuarioId().setNombre(us.getNombre());
                getObjetoFuncionarioSede().getUsuarioId().setUsuario(us.getUsuario());
                break;
            }
        }
        List<GatSedeFuncionario> nuevaLista = new ArrayList<>();
        getObjeto().getGatSedeFuncionarioList().forEach(funcionario -> {
            if (funcionario.getId() != null && funcionario.getId().equals(getObjetoFuncionarioSede().getId())) {
                nuevaLista.add(getObjetoFuncionarioSede());
            } else {
                nuevaLista.add(funcionario);
            }
        });
        getObjeto().setGatSedeFuncionarioList(nuevaLista);
        PrimeFaces.current().ajax().update("frmConfigurar:pFuncionarioConfigurar");
        PrimeFaces.current().executeScript("PF('dialogoFuncionarioSede').hide()");
    }

    public void borrarFuncionarioSede(GatSedeFuncionario funcionarioEliminar) {
        if (getListaFuncionariosEliminar() == null) {
            setListaFuncionariosEliminar(new ArrayList<>());
        }
        List<GatSedeFuncionario> nuevaLista = new ArrayList<>();
        for (GatSedeFuncionario funcionario : getObjeto().getGatSedeFuncionarioList()) {
            if (funcionario.getUsuarioId().getId().equals(funcionarioEliminar.getUsuarioId().getId())) {
                if (funcionario.getId() != null) {
                    getListaFuncionariosEliminar().add(funcionario);
                }
            } else {
                nuevaLista.add(funcionario);
            }
        }
        getObjeto().setGatSedeFuncionarioList(nuevaLista);
        PrimeFaces.current().ajax().update("frmConfigurar:pFuncionarioConfigurar");
        addMensaje("Pendiente de presionar el botón guardar");
        generarMensajes();
    }

    public void crearTaquilla() {
        setObjetoTaquilla(new GatSedeTaquilla());
        getObjetoTaquilla().setUsuarioId(new Usuario());
        getObjetoTaquilla().setGatTaquillaServicioList(new ArrayList<>());
        setTipo(new Maestro());
        PrimeFaces.current().ajax().update("frmTaquilla");
        PrimeFaces.current().executeScript("PF('dialogoTaquilla').show()");
    }

    public void editarTaquilla(GatSedeTaquilla taquilla) {
        setObjetoTaquilla(taquilla);
        getObjetoTaquilla().setModificado(true);
        setTipo(new Maestro());
        PrimeFaces.current().ajax().update("frmTaquilla");
        PrimeFaces.current().executeScript("PF('dialogoTaquilla').show()");
    }

    public void modificarTaquilla() {
        if (getObjeto().getGatSedeTaquillaList() == null) {
            getObjeto().setGatSedeTaquillaList(new ArrayList<>());
        }
        List<GatSedeTaquilla> nuevaLista = new ArrayList<>();
        String nombreEditada = getObjetoTaquilla().getNombre().replaceAll(" ", "");
        for (GatSedeTaquilla taquilla : getObjeto().getGatSedeTaquillaList()) {
            String nombre = taquilla.getNombre().replaceAll(" ", "");
            if (getObjetoTaquilla().getId() == null) {
                if (nombre.equalsIgnoreCase(nombreEditada)) {
                    nuevaLista.add(getObjetoTaquilla());
                    continue;
                }
            } else if (getObjetoTaquilla().getId().equals(taquilla.getId())) {
                nuevaLista.add(getObjetoTaquilla());
                continue;
            }
            nuevaLista.add(taquilla);
        }
        if (this.getErrores().isEmpty()) {
            getObjeto().setGatSedeTaquillaList(nuevaLista);
            PrimeFaces.current().ajax().update("frmConfigurar:pTaquillasConfigurar");
            PrimeFaces.current().executeScript("PF('dialogoTaquilla').hide()");
        } else {
            this.generarMensajes();
        }
    }

    public void guardarTaquilla() {
        if (getObjeto().getGatSedeTaquillaList() == null) {
            getObjeto().setGatSedeTaquillaList(new ArrayList<>());
        }
        String nombreTaquillaNueva = getObjetoTaquilla().getNombre().replaceAll(" ", "");
        for (GatSedeTaquilla taquilla : getObjeto().getGatSedeTaquillaList()) {
            String nombreTaquilla = taquilla.getNombre().replaceAll(" ", "");
            if (nombreTaquillaNueva.equalsIgnoreCase(nombreTaquilla)) {
                this.addError("La taquilla ya existe con ese nombre");
            }
        }
        if (getObjetoTaquilla().getGatTaquillaServicioList() != null && getObjetoTaquilla().getGatTaquillaServicioList().isEmpty()) {
            this.addError("La taquilla debe tener al menos un servicio");
        }
        if (this.getErrores().isEmpty()) {
            getObjeto().getGatSedeTaquillaList().add(getObjetoTaquilla());
            PrimeFaces.current().ajax().update("frmConfigurar:pTaquillasConfigurar");
            PrimeFaces.current().executeScript("PF('dialogoTaquilla').hide()");
        } else {
            this.generarMensajes();
        }
    }

    public void guardarServicioTaquilla() {
        if (getTipo().getId() != null) {
            GatTaquillaServicio servicio = new GatTaquillaServicio();
            Maestro tipoServicio = getHashServiciosTaquilla().get(getTipo().getId());
            servicio.setMaeTipoServicioId(tipoServicio.getId());
            servicio.setMaeTipoServicioCodigo(tipoServicio.getValor());
            servicio.setMaeTipoServicioValor(tipoServicio.getNombre());
            if (getObjetoTaquilla().getGatTaquillaServicioList() == null) {
                getObjetoTaquilla().setGatTaquillaServicioList(new ArrayList<>());
            }
            boolean valido = true;
            for (GatTaquillaServicio serv : getObjetoTaquilla().getGatTaquillaServicioList()) {
                if (serv.getMaeTipoServicioId() == tipoServicio.getId()) {
                    valido = false;
                }
            }
            if (valido) {
                getObjetoTaquilla().getGatTaquillaServicioList().add(servicio);
                PrimeFaces.current().ajax().update("frmTaquilla:pServicios");
            } else {
                this.addError("El servicio ya esta agregado");
                this.generarMensajes();
            }
        } else {
            this.addError("No se ha seleccionado un servicio");
            this.generarMensajes();
        }
    }

    public void borrarTaquillaServicio(GatTaquillaServicio servicio) {
        if (getListaTaquillaServiciosEliminar() == null) {
            setListaTaquillaServiciosEliminar(new ArrayList<>());
        }
        if (getObjetoTaquilla().getGatTaquillaServicioList() != null) {
            List<GatTaquillaServicio> nuevaLista = new ArrayList<>();
            for (GatTaquillaServicio serv : getObjetoTaquilla().getGatTaquillaServicioList()) {
                if (serv.getMaeTipoServicioId() != servicio.getMaeTipoServicioId()) {
                    nuevaLista.add(serv);
                } else {
                    if (servicio.getId() != null) {
                        getListaTaquillaServiciosEliminar().add(servicio);
                    }
                }
            }
            getObjetoTaquilla().setGatTaquillaServicioList(nuevaLista);
            PrimeFaces.current().ajax().update("frmTaquilla:pServicios");
        }
    }

    public void crearHorario() {
        setObjetoHorario(new GnSedeHorario());
        getObjetoHorario().setHoraDesde(new Date());
        getObjetoHorario().setHoraHasta(new Date());
        PrimeFaces.current().ajax().update("frmHorario");
        PrimeFaces.current().executeScript("PF('dialogoHorario').show()");
    }

    public void guardarHorario() {
        boolean cambio = false;
        if (getObjeto().getGnSedeHorarioList() == null) {
            getObjeto().setGnSedeHorarioList(new ArrayList<>());
        }
        /**
         * else {
         *
         * for (GnSedeHorario horario : getObjeto().getGnSedeHorarioList()) { if
         * (getHorarioSeleccionado().contains(horario.getDias())) {
         * horario.setActivo(false); horario.setModificado(true); cambio = true;
         * } }
         *
         * }
         */
        if (validarHorario() && validarHora()) {
            for (String dia : getHorarioSeleccionado()) {
                GnSedeHorario nuevoHorario = new GnSedeHorario();
                nuevoHorario.setGnSedeId(getObjeto());
                nuevoHorario.setDias(dia);
                nuevoHorario.setHoraDesde(getObjetoHorario().getHoraDesde());
                nuevoHorario.setHoraHasta(getObjetoHorario().getHoraHasta());
                nuevoHorario.setActivo(getObjetoHorario().isActivo());
                getObjeto().getGnSedeHorarioList().add(nuevoHorario);
            }
            /**
             * if (cambio) { addMensaje("Se cambió el estado de todos los
             * horarios que contienen esas fechas"); }
             */

            PrimeFaces.current().ajax().update("frmConfigurar:pHorarioConfigurar");
            PrimeFaces.current().executeScript("PF('dialogoHorario').hide()");
        } else {
            generarMensajes();
        }
    }

    public void verTaquilla(GatSedeTaquilla taquilla) {
        setObjetoTaquilla(taquilla);
        PrimeFaces.current().ajax().update("frmVerTaquilla");
        PrimeFaces.current().executeScript("PF('dialogoVerTaquilla').show()");
    }

    public void borrarTaquilla(GatSedeTaquilla taquilla) {
        if (getListaTaquillasEliminar() == null) {
            setListaTaquillasEliminar(new ArrayList<>());
        }
        List<GatSedeTaquilla> nuevaLista = new ArrayList<>();
        for (GatSedeTaquilla taqui : getObjeto().getGatSedeTaquillaList()) {
            if (taquilla.getId() != null) {
                if (taquilla.getId().equals(taqui.getId())) {
                    getListaTaquillasEliminar().add(taqui);
                    continue;
                }
            } else if (taqui.getNombre().equals(taquilla.getNombre())) {
                continue;
            }
            nuevaLista.add(taqui);
        }
        getObjeto().setGatSedeTaquillaList(nuevaLista);
        PrimeFaces.current().ajax().update("frmConfigurar:pTaquillasConfigurar");
    }

    public void editarHorario(GnSedeHorario horario) {
        List<String> lista = new ArrayList<>();
        lista.add(horario.getDias());

        setObjetoHorario(horario);
        getObjetoHorario().setModificado(true);
        Calendar calDesde = Calendar.getInstance();
        calDesde.setTime(horario.getHoraDesde());
        Calendar calHasta = Calendar.getInstance();
        calHasta.setTime(horario.getHoraHasta());
        Calendar actual = Calendar.getInstance();
        actual.set(Calendar.HOUR_OF_DAY, calDesde.get(Calendar.HOUR_OF_DAY));
        actual.set(Calendar.MINUTE, calDesde.get(Calendar.MINUTE));
        getObjetoHorario().setHoraDesde(actual.getTime());
        actual.set(Calendar.HOUR_OF_DAY, calHasta.get(Calendar.HOUR_OF_DAY));
        actual.set(Calendar.MINUTE, calHasta.get(Calendar.MINUTE));
        getObjetoHorario().setHoraHasta(actual.getTime());

        setHorarioSeleccionado(lista);

        PrimeFaces.current().ajax().update("frmHorario");
        PrimeFaces.current().executeScript("PF('dialogoHorario').show()");
    }

    public void borrarHorario(GnSedeHorario horario) {
        if (getListaHorariosEliminar() == null) {
            setListaHorariosEliminar(new ArrayList<>());
        }
        List<GnSedeHorario> nuevaLista = new ArrayList<>();
        for (GnSedeHorario horarioSede : getObjeto().getGnSedeHorarioList()) {
            if (horario.getId() != null) {
                if (horarioSede.getId().equals(horario.getId())) {
                    getListaHorariosEliminar().add(horario);
                    continue;
                }
            } else if (horarioSede.getDias().equals(horario.getDias())) {
                continue;
            }
            nuevaLista.add(horarioSede);
        }
        getObjeto().setGnSedeHorarioList(nuevaLista);
        PrimeFaces.current().ajax().update("frmConfigurar:pHorarioConfigurar");
        this.addMensaje("Pendiente de presionar el botón guardar");
        this.generarMensajes();
    }

    public void modificarHorario() {

        if (validarHorario()) {
            List<GnSedeHorario> nuevaLista = new ArrayList<>();

            for (GnSedeHorario horario : getObjeto().getGnSedeHorarioList()) {
                if (getObjetoHorario().getId() != null) {
                    if (horario.getId().equals(getObjetoHorario().getId())) {
                        if (horario.getDias().equals(getObjetoHorario().getDias())) {
                            nuevaLista.add(getObjetoHorario());
                            continue;
                        }
                    } else {
                        if (getObjetoHorario().getDias().contains(horario.getDias())) {
                            horario.setModificado(true);

                        }
                    }
                } else if (getObjetoHorario().getDias().equals(horario.getDias()) && horario.getId() == null) {
                    nuevaLista.add(getObjetoHorario());
                    continue;
                } else {
                    if (getObjetoHorario().getDias().contains(horario.getDias())) {
                        horario.setModificado(true);

                    }
                }
                nuevaLista.add(horario);
            }
            getObjeto().setGnSedeHorarioList(nuevaLista);
            PrimeFaces.current().ajax().update("frmConfigurar:pHorarioConfigurar");
            PrimeFaces.current().executeScript("PF('dialogoHorario').hide()");
            this.addMensaje("La modificación se hara al momento de gestionar");
        } else {
            addError("La hora desde esta despues de la hora hasta, favor corregir");
        }
        this.generarMensajes();
    }

    public void verLiberar(int id) {
        setObjeto(new GnSede(id));
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_VER);
        getGestionSedeServicio().Accion(this);
        procesoFinal();
    }

    public void liberar(int idTaquilla) {
        setIdTaquillaLiberar(idTaquilla);
        super.setAccion(Url.ACCION_ADICIONAL_2);
        super.setDoAccion(Url.ACCION_MODIFICAR);
        getGestionSedeServicio().Accion(this);
        procesoFinal();
    }

    public void inactivarPantallas(int idSede) {
        setObjeto(new GnSede(idSede));
        super.setAccion(Url.ACCION_ADICIONAL_3);
        getGestionSedeServicio().Accion(this);
        procesoFinal();
    }

    public void reportePorSede(int idSede) {
        setObjeto(new GnSede(idSede));
        super.setAccion(Url.ACCION_ADICIONAL_4);
        getGestionSedeServicio().Accion(this);
        procesoFinal();
    }

    public void historicoSede(GnSede objetoSede) {
        setObjeto(objetoSede);
        super.setAccion(Url.ACCION_ADICIONAL_5);
        setFechaInicio(null);
        setFechaFin(null);
        setListaHistorico(new ArrayList<>());
        procesoFinal();
    }

    public void generarHistorico() {
        boolean buscar = false;
        if (getFechaInicio() != null && getFechaFin()!= null) {
            if (getFechaFin().after(getFechaInicio())) {
                buscar = true;
            } else {
                addError("La fecha hora fin no puede estar antes de la fecha hora inicio");
            }
        }
        if (buscar) {
            super.setAccion(Url.ACCION_ADICIONAL_5);
            getGestionSedeServicio().Accion(this);
            PrimeFaces.current().ajax().update("frmHistoricoSede");
        }
        generarMensajes();
    }

    public void refrescarReporteSede() {
        super.setAccion(Url.ACCION_ADICIONAL_4);
        getGestionSedeServicio().Accion(this);
        PrimeFaces.current().executeScript("PF('dialogoReporteSede').hide()");
        PrimeFaces.current().ajax().update("frmReporteSede");
        PrimeFaces.current().executeScript("PF('dialogoReporteSede').show()");
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmSedes");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_VER:
                    PrimeFaces.current().ajax().update("frmVer");
                    PrimeFaces.current().executeScript("PF('dialogoVer').show()");
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            PrimeFaces.current().ajax().update("frmConfigurar");
                            PrimeFaces.current().executeScript("PF('dialogoConfigurar').show()");
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().ajax().update("frmSedes");
                            PrimeFaces.current().executeScript("PF('dialogoConfigurar').hide()");
                            crearLog(getObjeto().toString());
                            crearLog(getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            PrimeFaces.current().ajax().update("frmLiberar");
                            PrimeFaces.current().executeScript("PF('dialogoLiberar').show()");
                            break;
                        case Url.ACCION_MODIFICAR:
                            PrimeFaces.current().ajax().update("frmLiberar");
                            crearLog(getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    PrimeFaces.current().ajax().update("frmSedes");
                    break;
                case Url.ACCION_ADICIONAL_4:
                    PrimeFaces.current().ajax().update("frmReporteSede");
                    PrimeFaces.current().executeScript("PF('dialogoReporteSede').show()");
                    break;
                case Url.ACCION_ADICIONAL_5:
                    PrimeFaces.current().ajax().update("frmHistoricoSede");
                    PrimeFaces.current().executeScript("PF('dialogoHistoricoSede').show()");
                    break;
            }
        }
        generarMensajes();
    }

    private boolean validarHorario() {
        boolean valido = true;
        if (getObjetoHorario().getHoraDesde().after(getObjetoHorario().getHoraHasta())) {
            addError("La Hora Desde esta despues de la Hora Hasta");
            valido = false;

        } else if (getObjetoHorario().getHoraHasta().before(getObjetoHorario().getHoraDesde())) {
            addError("La Hora Hasta no puede estar antes de la Hora Desde");
            valido = false;
        }
        if (getObjetoHorario().getHoraDesde().equals(getObjetoHorario().getHoraHasta())) {
            addError("La hora desde y hasta no pueden ser iguales");
            valido = false;
        }

        return valido;
    }

    private boolean validarHora() {
        boolean valido = true;

        for (GnSedeHorario horario : getObjeto().getGnSedeHorarioList()) {
            for (String dia : getHorarioSeleccionado()) {
                if (dia.equals(horario.getDias())) {
                    if (getObjetoHorario().getHoraDesde().equals(horario.getHoraDesde()) || getObjetoHorario().getHoraDesde().after(horario.getHoraDesde()) && getObjetoHorario().getHoraDesde().before(horario.getHoraHasta())
                            || getObjetoHorario().getHoraHasta().equals(horario.getHoraHasta()) || getObjetoHorario().getHoraHasta().before(horario.getHoraHasta()) && getObjetoHorario().getHoraHasta().after(horario.getHoraDesde())) {
                        addError("La hora DESDE o HASTA ya se encuentra generada dentro de un horario del dia");
                        valido = false;
                    }

                }
            }

        }

        return valido;
    }

}
