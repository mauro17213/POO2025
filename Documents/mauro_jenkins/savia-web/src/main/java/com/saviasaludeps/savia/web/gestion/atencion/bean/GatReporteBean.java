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
import com.saviasaludeps.savia.dominio.gestionAtencion.GatAtencion;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatReporteSede;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatReporteServicioActual;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatReporteTaquilla;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeConfiguracion;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeFuncionario;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatSedeTaquilla;
import com.saviasaludeps.savia.dominio.gestionAtencion.GatTaquillaFuncionario;
import com.saviasaludeps.savia.dominio.gestionAtencion.HistoricoSede;
import com.saviasaludeps.savia.web.gestion.atencion.servicio.GatReporteServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_LISTAR;
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
public class GatReporteBean extends Url {

    @Autowired
    private GatReporteServicioIface gatReporteServicio;
    private GnSede objeto;
    private List<GnSede> registros;
    private LazyDataModel<GnSede> lazySedes;

    private List<Ubicacion> listaUbicaciones;
//    private List<Usuario> listaUsuarios;
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

    //Variables auxiliares
    private String listaFiltro;
    private int idTaquillaLiberar;
    private List<String> horarioSeleccionado;

    //Variables para reportes
    private GatReporteSede objetoReporteSede;
    private BarChartModel graficoBarraModelo;
    private List<GatReporteTaquilla> listaReportesTaquilla;
    private List<GatReporteServicioActual> listaReporteServicioActual;

    //Informe Taquillas Actual
    List<GatSedeTaquilla> listaTaquillas;
//    List<GatTiquete> listaTiquetes;
    List<GatAtencion> listaAtenciones;

    //Variables para historicos
    private Date fecha;
    private Date fechaInicio;
    private Date fechaFin;
    private List<HistoricoSede> listaHistorico;

    public GatReporteBean() {
        this.objeto = new GnSede();
        Modulo _mod = super.validarModulo(Modulo.ID_GAT_REPORTES);
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
        FacesContextUtils.getRequiredWebApplicationContext(FacesContext.getCurrentInstance()).getAutowireCapableBeanFactory().autowireBean(this);
        getGatReporteServicio().cargaInicial(this);
        listar();
    }

    public GatReporteServicioIface getGatReporteServicio() {
        return gatReporteServicio;
    }

    public void setGatReporteServicio(GatReporteServicioIface gatReporteServicio) {
        this.gatReporteServicio = gatReporteServicio;
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

//    public List<Usuario> getListaUsuarios() {
//        return listaUsuarios;
//    }
//
//    public void setListaUsuarios(List<Usuario> listaUsuarios) {
//        this.listaUsuarios = listaUsuarios;
//    }
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

    public List<GatReporteServicioActual> getListaReporteServicioActual() {
        return listaReporteServicioActual;
    }

    public void setListaReporteServicioActual(List<GatReporteServicioActual> listaReporteServicioActual) {
        this.listaReporteServicioActual = listaReporteServicioActual;
    }

    public List<GatSedeTaquilla> getListaTaquillas() {
        return listaTaquillas;
    }

    public void setListaTaquillas(List<GatSedeTaquilla> listaTaquillas) {
        this.listaTaquillas = listaTaquillas;
    }

    public List<GatAtencion> getListaAtenciones() {
        return listaAtenciones;
    }

//    public List<GatTiquete> getListaTiquetes() {
//        return listaTiquetes;
//    }
//
//    public void setListaTiquetes(List<GatTiquete> listaTiquetes) {
//        this.listaTiquetes = listaTiquetes;
    public void setListaAtenciones(List<GatAtencion> listaAtenciones) {
        this.listaAtenciones = listaAtenciones;
    }

//    }
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        getGatReporteServicio().Accion(this);
    }

    public void taquillasActualListar(int idSede) {
        setObjeto(new GnSede(idSede));
        super.setAccion(Url.ACCION_ADICIONAL_1);
        getGatReporteServicio().Accion(this);
        procesoFinal();
    }

    public void turnosListar(int idSede) {
        setObjeto(new GnSede(idSede));
        super.setAccion(Url.ACCION_ADICIONAL_2);
        getGatReporteServicio().Accion(this);        
        procesoFinal();
    }

    public void serviciosActualListar(int idSede) {
        setObjeto(new GnSede(idSede));
        super.setAccion(Url.ACCION_ADICIONAL_3);
        getGatReporteServicio().Accion(this);
        procesoFinal();
    }

    public void sedeActualListar(int idSede) {
        setObjeto(new GnSede(idSede));
        super.setAccion(Url.ACCION_ADICIONAL_4);
        getGatReporteServicio().Accion(this);
        procesoFinal();
    }

    public void fechasListar(int idSede) {
        setObjeto(new GnSede(idSede));
        super.setAccion(Url.ACCION_ADICIONAL_5);
        getGatReporteServicio().Accion(this);        
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmSedes");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1:
                    PrimeFaces.current().ajax().update("frmTaquillasActual");
                    PrimeFaces.current().executeScript("PF('dialogoTaquillasActual').show()");
                    break;
                case Url.ACCION_ADICIONAL_2:
                    PrimeFaces.current().ajax().update("frmTurnosHistorico");
                    PrimeFaces.current().executeScript("PF('dialogoTurnosHistorico').show()");
                    break;
                case Url.ACCION_ADICIONAL_3:
                    PrimeFaces.current().ajax().update("frmServiciosActual");
                    PrimeFaces.current().executeScript("PF('dialogoServiciosActual').show()");
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

}
