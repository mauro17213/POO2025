/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.bean;

import com.saviasaludeps.savia.dominio.administracion.GnValidacionCampo;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.MaestroRelacion;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipoRelacion;
import com.saviasaludeps.savia.web.administracion.servicio.MaestroServicioImpl;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import com.saviasaludeps.savia.web.administracion.servicio.MaestroServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.SortMeta;

/**
 *
 * @author rpalacic (SystemTech Integral)
 */
@ManagedBean
@ViewScoped
public class MaestroBean extends Url {

    private MaestroServicioIface maestroServicio;
    private List<MaestroAccion> listaAcciones;
    private Maestro objeto;
    private List<Maestro> registros;
    private LazyDataModel<Maestro> lazyMaestro;

    private List<MaestroTipo> listaTipoMaestros;
    private List<Maestro> listaDependencias;
    private HashMap<Integer, Maestro> hashListaDependencias;

    private List<MaestroAccion> listaTipoAcciones;
    private HashMap<Integer, MaestroAccion> hashListaTipoAcciones;
    private List<Integer> selectedMaestrosHijos = new ArrayList();
    private List<String> selectedMaestrosPadres = new ArrayList();
    private List<MaestroTipoRelacion> listaMaestroTipoRelacion;
    private List<Maestro> listaMaestrosConsultados;
    private List<MaestroRelacion> selectedMaestros;
    private List<MaestroTipo> listaMaestroTiposPadre;  
    private List<Integer> selectMaestroAcciones;
    private List<GnValidacionCampo> listaValidacionesCampo;

    public MaestroBean() {
        this.objeto = new Maestro();
        listaMaestrosConsultados = new ArrayList<>();
        Modulo _mod = super.validarModulo(Modulo.ID_MAESTROS);
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyMaestro = new LazyDataModel<Maestro>() {

                private List<Maestro> lista;
                
                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<Maestro> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(Maestro objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public Maestro getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (Maestro objeto : lista) {
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
        getMaestroServicio().cargasInicial(this);
        listar();
    }

    public Maestro getObjeto() {
        return objeto;
    }

    public void setObjeto(Maestro objeto) {
        this.objeto = objeto;
    }

    public List<Maestro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Maestro> registros) {
        this.registros = registros;
    }

    public LazyDataModel<Maestro> getLazyMaestro() {
        return lazyMaestro;
    }

    public void setLazyMaestro(LazyDataModel<Maestro> lazyMaestro) {
        this.lazyMaestro = lazyMaestro;
    }

    public List<MaestroTipo> getListaTipoMaestros() {
        return listaTipoMaestros;
    }

    public void setListaTipoMaestros(List<MaestroTipo> listaTipoMaestros) {
        this.listaTipoMaestros = listaTipoMaestros;
    }

    public List<Maestro> getListaDependencias() {
        return listaDependencias;
    }

    public void setListaDependencias(List<Maestro> listaDependencias) {
        this.listaDependencias = listaDependencias;
    }

    public HashMap<Integer, Maestro> getHashListaDependencias() {
        return hashListaDependencias;
    }

    public void setHashListaDependencias(HashMap<Integer, Maestro> hashListaDependencias) {
        this.hashListaDependencias = hashListaDependencias;
    }

    public List<MaestroAccion> getListaTipoAcciones() {
        return listaTipoAcciones;
    }

    public void setListaTipoAcciones(List<MaestroAccion> listaTipoAcciones) {
        this.listaTipoAcciones = listaTipoAcciones;
    }

    public HashMap<Integer, MaestroAccion> getHashListaTipoAcciones() {
        return hashListaTipoAcciones;
    }

    public void setHashListaTipoAcciones(HashMap<Integer, MaestroAccion> hashListaTipoAcciones) {
        this.hashListaTipoAcciones = hashListaTipoAcciones;
    }

    public List<MaestroAccion> getListaAcciones() {
        return listaAcciones;
    }

    public void setListaAcciones(List<MaestroAccion> listaAcciones) {
        this.listaAcciones = listaAcciones;
    }

    public List<Integer> getSelectedMaestrosHijos() {
        return selectedMaestrosHijos;
    }

    public void setSelectedMaestrosHijos(List<Integer> selectedMaestrosHijos) {
        this.selectedMaestrosHijos = selectedMaestrosHijos;
    }

    public List<MaestroTipoRelacion> getListaMaestroTipoRelacion() {
        return listaMaestroTipoRelacion;
    }

    public void setListaMaestroTipoRelacion(List<MaestroTipoRelacion> listaMaestroTipoRelacion) {
        this.listaMaestroTipoRelacion = listaMaestroTipoRelacion;
    }

    public List<Maestro> getListaMaestrosConsultados() {
        return listaMaestrosConsultados;
    }

    public void setListaMaestrosConsultados(List<Maestro> listaMaestrosConsultados) {
        this.listaMaestrosConsultados = listaMaestrosConsultados;
    }

    public List<MaestroRelacion> getSelectedMaestros() {
        return selectedMaestros;
    }

    public void setSelectedMaestros(List<MaestroRelacion> selectedMaestros) {
        this.selectedMaestros = selectedMaestros;
    }

    public List<MaestroTipo> getListaMaestroTiposPadre() {
        return listaMaestroTiposPadre;
    }

    public void setListaMaestroTiposPadre(List<MaestroTipo> listaMaestroTiposPadre) {
        this.listaMaestroTiposPadre = listaMaestroTiposPadre;
    }

    public List<String> getSelectedMaestrosPadres() {
        return selectedMaestrosPadres;
    }

    public void setSelectedMaestrosPadres(List<String> selectedMaestrosPadres) {
        this.selectedMaestrosPadres = selectedMaestrosPadres;
    }

    public List<Integer> getSelectMaestroAcciones() {
        return selectMaestroAcciones;
    }

    public void setSelectMaestroAcciones(List<Integer> selectMaestroAcciones) {
        this.selectMaestroAcciones = selectMaestroAcciones;
    }

    public List<GnValidacionCampo> getListaValidacionesCampo() {
        return listaValidacionesCampo;
    }

    public void setListaValidacionesCampo(List<GnValidacionCampo> listaValidacionesCampo) {
        this.listaValidacionesCampo = listaValidacionesCampo;
    }

    public void cargarAcciones() {
    }

    public MaestroServicioIface getMaestroServicio() {
        if (maestroServicio == null) {
            maestroServicio = new MaestroServicioImpl();
        }
        return maestroServicio;
    }

    public void setMaestroServicio(MaestroServicioIface maestroServicio) {
        this.maestroServicio = maestroServicio;
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        setSelectedMaestrosHijos(new ArrayList());
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getMaestroServicio().Accion(this);
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getMaestroServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer:panelVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        setListaMaestroTipoRelacion(new ArrayList<>());
        setListaMaestrosConsultados(new ArrayList());
        getMaestroServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear:panelCrear");
        PrimeFaces.current().ajax().update("frmCrear:panelCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getMaestroServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        setSelectedMaestrosHijos(new ArrayList());
        super.setAccion(ACCION_EDITAR);
        getMaestroServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmEditar:panelEditar");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getMaestroServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        getMaestroServicio().Accion(this);
        procesoFinal();
    }

    public void listarDependencias() {
        getObjeto().getMaestroTipo().setMaestroTipo(getMaestroServicio().consultarDependenciaPadre(getObjeto().getMaestroTipo().getTipo()));
        if (getObjeto().getMaestroTipo().getTipo() != null) {
            setListaDependencias(getMaestroServicio().listaDependencias(getObjeto().getMaestroTipo().getTipo()));
            setListaAcciones(getMaestroServicio().listaAcciones(getObjeto().getMaestroTipo().getTipo()));
            setListaValidacionesCampo(getMaestroServicio().listarValidacionesDelTipo(getObjeto().getMaestroTipo().getTipo()));
            getMaestroServicio().listaMaestroTiposPadre(this);
        } else {
            setListaDependencias(new ArrayList());
            setListaAcciones(new ArrayList());
            setListaValidacionesCampo(new ArrayList<GnValidacionCampo>());
        }
    }
    
    public List<Maestro> maestroPorTipo(String maestroTipo){
        List<Maestro> filtro = listaMaestrosConsultados;
        for(Maestro maestroFiltro: filtro){
            if(!maestroFiltro.getTipo().equals(maestroTipo)){
                filtro.remove(maestroFiltro);
            }
        }
        return filtro;
    }

    public void modificarRelacionHijos(RowEditEvent event) {
        setObjeto((Maestro) event.getObject());
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(ACCION_MODIFICAR);
        getMaestroServicio().Accion(this);
        generarMensajes();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmMaestros");
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;
            }
        }
        generarMensajes();
    }

}
