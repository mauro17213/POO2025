package com.saviasaludeps.savia.web.supervisioncontrato.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.dominio.supervisioncontrato.ScIndicador;
import com.saviasaludeps.savia.web.supervisioncontrato.servicio.IndicadoresServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author aguevara
 */
@ManagedBean
@ViewScoped
public class IndicadoresBean extends Url {

    private IndicadoresServicioIface indicadoresServicio;
    private LazyDataModel<ScIndicador> lazyIndicadores;
    private List<ScIndicador> registros;
    private ScIndicador objeto;

    //Listas Auxiliares
    private List<Maestro> listaAreas;
    private HashMap<Integer, Maestro> hashAreas;

    private List<Maestro> listaIndicadorClase;
    private HashMap<Integer, Maestro> hashIndicadorClase;

    private List<Maestro> listaIndicadorMacroproceso;
    private HashMap<Integer, Maestro> hashIndicadorMacroproceso;

    private List<Maestro> listaIndicadorProceso;
    private HashMap<Integer, Maestro> hashIndicadorProceso;
    
    //Variable auxiliar
    private int idBorrador;

    public IndicadoresBean() {
        this.objeto = new ScIndicador();
        Modulo mod = super.validarModulo(Modulo.ID_SC_SUPERVISION_CONTRATOS);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            super.addListaParamConsultas(new ParamConsulta());
            lazyIndicadores = new LazyDataModel<ScIndicador>() {
                private List<ScIndicador> indicadores;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<ScIndicador> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    indicadores = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return indicadores;
                }

                @Override
                public String getRowKey(ScIndicador reserva) {
                    return reserva.getId().toString();
                }

                @Override
                public ScIndicador getRowData(String indicadoresId) {
                    Integer id = Integer.valueOf(indicadoresId);
                    for (ScIndicador item : indicadores) {
                        if (id.equals(item.getId())) {
                            return item;
                        }
                    }
                    return null;
                }

            };
        }
    }

    @PostConstruct
    public void postConstruct() {
        getIndicadoresServicio().cargasInicial(this);
        listar();
    }

    public IndicadoresServicioIface getIndicadoresServicio() {
        return indicadoresServicio;
    }

    public void setIndicadoresServicio(IndicadoresServicioIface indicadoresServicio) {
        this.indicadoresServicio = indicadoresServicio;
    }

    public LazyDataModel<ScIndicador> getLazyIndicadores() {
        return lazyIndicadores;
    }

    public void setLazyIndicadores(LazyDataModel<ScIndicador> lazyIndicadores) {
        this.lazyIndicadores = lazyIndicadores;
    }

    public List<ScIndicador> getRegistros() {
        return registros;
    }

    public void setRegistros(List<ScIndicador> registros) {
        this.registros = registros;
    }

    public ScIndicador getObjeto() {
        return objeto;
    }

    public void setObjeto(ScIndicador objeto) {
        this.objeto = objeto;
    }

    public List<Maestro> getListaAreas() {
        return listaAreas;
    }

    public void setListaAreas(List<Maestro> listaAreas) {
        this.listaAreas = listaAreas;
    }

    public HashMap<Integer, Maestro> getHashAreas() {
        return hashAreas;
    }

    public void setHashAreas(HashMap<Integer, Maestro> hashAreas) {
        this.hashAreas = hashAreas;
    }

    public List<Maestro> getListaIndicadorClase() {
        return listaIndicadorClase;
    }

    public void setListaIndicadorClase(List<Maestro> listaIndicadorClase) {
        this.listaIndicadorClase = listaIndicadorClase;
    }

    public HashMap<Integer, Maestro> getHashIndicadorClase() {
        return hashIndicadorClase;
    }

    public void setHashIndicadorClase(HashMap<Integer, Maestro> hashIndicadorClase) {
        this.hashIndicadorClase = hashIndicadorClase;
    }

    public List<Maestro> getListaIndicadorMacroproceso() {
        return listaIndicadorMacroproceso;
    }

    public void setListaIndicadorMacroproceso(List<Maestro> listaIndicadorMacroproceso) {
        this.listaIndicadorMacroproceso = listaIndicadorMacroproceso;
    }

    public HashMap<Integer, Maestro> getHashIndicadorMacroproceso() {
        return hashIndicadorMacroproceso;
    }

    public void setHashIndicadorMacroproceso(HashMap<Integer, Maestro> hashIndicadorMacroproceso) {
        this.hashIndicadorMacroproceso = hashIndicadorMacroproceso;
    }

    public List<Maestro> getListaIndicadorProceso() {
        return listaIndicadorProceso;
    }

    public void setListaIndicadorProceso(List<Maestro> listaIndicadorProceso) {
        this.listaIndicadorProceso = listaIndicadorProceso;
    }

    public HashMap<Integer, Maestro> getHashIndicadorProceso() {
        return hashIndicadorProceso;
    }

    public void setHashIndicadorProceso(HashMap<Integer, Maestro> hashIndicadorProceso) {
        this.hashIndicadorProceso = hashIndicadorProceso;
    }

    public int getIdBorrador() {
        return idBorrador;
    }

    public void setIdBorrador(int idBorrador) {
        this.idBorrador = idBorrador;
    }
    
    

    //metodos
    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getIndicadoresServicio().Accion(this);
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getIndicadoresServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer:panelVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getIndicadoresServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmCrear:panelCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getIndicadoresServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_EDITAR);
        getIndicadoresServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmEditar:panelEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getIndicadoresServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }
    
        public void borrarIndicador(ScIndicador obj) {
        this.setObjeto(obj);
        this.setIdBorrador(obj.getId());
        this.objeto.setBorradoObservacion("");
        PrimeFaces.current().ajax().update("frmBorrarIndicador");
        PrimeFaces.current().executeScript("PF('dialogoBorrarIndicador').show()");
    }

    public void borrar() {
        getObjeto().setId(this.getIdBorrador());
        super.setAccion(ACCION_BORRAR);
        getIndicadoresServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoBorrarIndicador').hide();");
        }
        procesoFinal();
    }
    
    
    public void mostrarMensaje(String mensaje) {
        getObjeto().setNombre(mensaje);
        PrimeFaces.current().resetInputs("frmDescripcionNombre");
        PrimeFaces.current().ajax().update("frmDescripcionNombre");
        PrimeFaces.current().executeScript("PF('dlgMsg').show();");
    }
    
     public void mostrarDescripcion(String mensaje) {
        getObjeto().setDescripcion(mensaje);
        PrimeFaces.current().resetInputs("frmDescripcion");
        PrimeFaces.current().ajax().update("frmDescripcion");
        PrimeFaces.current().executeScript("PF('dlgMsg2').show();");
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmIndicador");
                case Url.ACCION_VER:
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1:
                    PrimeFaces.current().ajax().update("frmIndicador");
                    break;
                case Url.ACCION_ADICIONAL_2:
                    PrimeFaces.current().ajax().update("frmIndicador");
                    break;
            }
        }
        generarMensajes();
    }

}
