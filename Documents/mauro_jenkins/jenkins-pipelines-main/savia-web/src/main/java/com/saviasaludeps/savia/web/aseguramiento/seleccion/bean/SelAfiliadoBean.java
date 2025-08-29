/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.seleccion.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.aseguramiento.seleccion.servicio.SelAfiliadoIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author Jaime Andres Olarte
 */
@ManagedBean
@SessionScoped
public class SelAfiliadoBean extends Url {

    private SelAfiliadoIface selAfiliadoServicio;
    private AsegAfiliado objeto;
    private List<AsegAfiliado> registros;
    private LazyDataModel<AsegAfiliado> lazyRegistros;
    private List<Maestro> listaTiposDocumento;
    private HashMap<Integer, Maestro> hashTiposDocumentoPersona;
    private List<Maestro> listaTiposDocumentoPersona;
    private List<Maestro> listaEstadosAfiliacion;
    private HashMap<Integer, Maestro> hashEstadosAfiliacion;
    private List<Maestro> listaRegimen;
    private HashMap<Integer, Maestro> hashRegimen;

    //@PostConstruct
    public SelAfiliadoBean() {
        try {
            this.setParamConsulta(new ParamConsulta());
            this.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyRegistros = new LazyDataModel<AsegAfiliado>() {

                private List<AsegAfiliado> sedes;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<AsegAfiliado> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    sedes = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return sedes;
                }

                @Override
                public String getRowKey(AsegAfiliado sede) {
                    return sede.getId().toString();
                }

                @Override
                public AsegAfiliado getRowData(String sedesId) {
                    Integer id = Integer.valueOf(sedesId);
                    for (AsegAfiliado sede : sedes) {
                        if (id.equals(sede.getId())) {
                            return sede;
                        }
                    }
                    return null;
                }
            };
            PrimeFaces.current().executeScript("PF('dialogoAfiliadoBusqueda').show()");
            PrimeFaces.current().ajax().update("frmAfiliadoBusqueda");

        } catch (Exception ex) {
            setObjeto(new AsegAfiliado());
            addError(ex.getMessage());
        }
    }

    @PostConstruct
    public void postConstruct() {
        getSelAfiliadoServicio().cargaInicial(this);
    }

    public void refrescar() {
        //getSelAfiliadoServicio().cargaInicial(this);
        getSelAfiliadoServicio().listar(this);
    }

    public String obtenerEstadoAfiliacion(int id) {
        try {
            return getHashEstadosAfiliacion().get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public String obtenerTipoDocumentoPersona(Integer id) {
        try {
            return hashTiposDocumentoPersona.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public SelAfiliadoIface getSelAfiliadoServicio() {
        return selAfiliadoServicio;
    }

    public void setSelAfiliadoServicio(SelAfiliadoIface selAfiliadoServicio) {
        this.selAfiliadoServicio = selAfiliadoServicio;
    }

    public AsegAfiliado getObjeto() {
        return objeto;
    }

    public void setObjeto(AsegAfiliado objeto) {
        this.objeto = objeto;
    }

    public List<AsegAfiliado> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AsegAfiliado> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AsegAfiliado> getLazyRegistros() {
        return lazyRegistros;
    }

    public void setLazyRegistros(LazyDataModel<AsegAfiliado> lazyRegistros) {
        this.lazyRegistros = lazyRegistros;
    }

    public void onRowSelectAfiliado(SelectEvent event) {
        objeto = (AsegAfiliado) event.getObject();
    }

    public List<Maestro> getListaTiposDocumento() {
        return listaTiposDocumento;
    }

    public void setListaTiposDocumento(List<Maestro> listaTiposDocumento) {
        this.listaTiposDocumento = listaTiposDocumento;
    }

    /**
     * @return the hashTiposDocumentoPersona
     */
    public HashMap<Integer, Maestro> getHashTiposDocumentoPersona() {
        return hashTiposDocumentoPersona;
    }

    /**
     * @param hashTiposDocumentoPersona the hashTiposDocumentoPersona to set
     */
    public void setHashTiposDocumentoPersona(HashMap<Integer, Maestro> hashTiposDocumentoPersona) {
        this.hashTiposDocumentoPersona = hashTiposDocumentoPersona;
    }

    /**
     * @return the listaTiposDocumentoPersona
     */
    public List<Maestro> getListaTiposDocumentoPersona() {
        return listaTiposDocumentoPersona;
    }

    /**
     * @param listaTiposDocumentoPersona the listaTiposDocumentoPersona to set
     */
    public void setListaTiposDocumentoPersona(List<Maestro> listaTiposDocumentoPersona) {
        this.listaTiposDocumentoPersona = listaTiposDocumentoPersona;
    }

    /**
     * @return the listaEstadosAfiliacion
     */
    public List<Maestro> getListaEstadosAfiliacion() {
        return listaEstadosAfiliacion;
    }

    /**
     * @param listaEstadosAfiliacion the listaEstadosAfiliacion to set
     */
    public void setListaEstadosAfiliacion(List<Maestro> listaEstadosAfiliacion) {
        this.listaEstadosAfiliacion = listaEstadosAfiliacion;
    }

    /**
     * @return the hashEstadosAfiliacion
     */
    public HashMap<Integer, Maestro> getHashEstadosAfiliacion() {
        return hashEstadosAfiliacion;
    }

    /**
     * @param hashEstadosAfiliacion the hashEstadosAfiliacion to set
     */
    public void setHashEstadosAfiliacion(HashMap<Integer, Maestro> hashEstadosAfiliacion) {
        this.hashEstadosAfiliacion = hashEstadosAfiliacion;
    }

    /**
     * @return the listaRegimen
     */
    public List<Maestro> getListaRegimen() {
        return listaRegimen;
    }

    /**
     * @param listaRegimen the listaRegimen to set
     */
    public void setListaRegimen(List<Maestro> listaRegimen) {
        this.listaRegimen = listaRegimen;
    }

    /**
     * @return the hashRegimen
     */
    public HashMap<Integer, Maestro> getHashRegimen() {
        return hashRegimen;
    }

    /**
     * @param hashRegimen the hashRegimen to set
     */
    public void setHashRegimen(HashMap<Integer, Maestro> hashRegimen) {
        this.hashRegimen = hashRegimen;
    }

}
