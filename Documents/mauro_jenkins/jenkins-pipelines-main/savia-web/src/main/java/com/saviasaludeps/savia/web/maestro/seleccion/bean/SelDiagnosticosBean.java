/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.seleccion.bean;

import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import com.saviasaludeps.savia.web.maestro.seleccion.servicio.DiagnosticoServicioIface;
import org.primefaces.model.SortMeta;

/**
 *
 * @author Jaime Andres Olarte
 */
@ManagedBean
@SessionScoped
public class SelDiagnosticosBean extends Url {

    private DiagnosticoServicioIface selDiagnosticoServicio;
    private MaDiagnostico objeto;
    private List<MaDiagnostico> registros;
    private LazyDataModel<MaDiagnostico> lazyRegistros;

    @PostConstruct
    public void iniciar() {
        lazyRegistros = new LazyDataModel<MaDiagnostico>() {
            private List<MaDiagnostico> listaMaDiagnostico;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<MaDiagnostico> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta().setPrimerRegistro(primerRegistro);
                getParamConsulta().setRegistrosPagina(registrosPagina);
                getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescar();
                listaMaDiagnostico = getRegistros();
                setRowCount(getParamConsulta().getCantidadRegistros());
                return listaMaDiagnostico;
            }

            @Override
            public String getRowKey(MaDiagnostico maDiagnostico) {
                return maDiagnostico.getId().toString();
            }

            @Override
            public MaDiagnostico getRowData(String maDiagnosticoId) {
                Integer id = Integer.valueOf(maDiagnosticoId);
                for (MaDiagnostico maDiagnostico : listaMaDiagnostico) {
                    if (id.equals(maDiagnostico.getId())) {
                        return maDiagnostico;
                    }
                }
                return null;
            }
        };

    }

    public void refrescar() {
        getSelDiagnosticoServicio().cargaInicial(this);
    }

    public DiagnosticoServicioIface getSelDiagnosticoServicio() {
        return selDiagnosticoServicio;
    }

    public void setSelDiagnosticoServicio(DiagnosticoServicioIface selDiagnosticoServicio) {
        this.selDiagnosticoServicio = selDiagnosticoServicio;
    }

    public MaDiagnostico getObjeto() {
        return objeto;
    }

    public void setObjeto(MaDiagnostico objeto) {
        this.objeto = objeto;
    }

    public List<MaDiagnostico> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MaDiagnostico> registros) {
        this.registros = registros;
    }

    public LazyDataModel<MaDiagnostico> getLazyRegistros() {
        return lazyRegistros;
    }

    public void setLazyRegistros(LazyDataModel<MaDiagnostico> lazyRegistros) {
        this.lazyRegistros = lazyRegistros;
    }

    public void onRowSelectDiagnostico(SelectEvent event) {
        objeto = (MaDiagnostico) event.getObject();
    }

}
