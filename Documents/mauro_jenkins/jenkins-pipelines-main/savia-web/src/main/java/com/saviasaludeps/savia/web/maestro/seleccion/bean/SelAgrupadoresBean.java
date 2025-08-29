/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.seleccion.bean;

import com.saviasaludeps.savia.dominio.maestro.MaAgrupadoresMedicamentos;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import com.saviasaludeps.savia.web.maestro.seleccion.servicio.AgrupadoresServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.util.Map;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author jarodriguez
 */
@ManagedBean
@SessionScoped
public class SelAgrupadoresBean extends Url {

    private AgrupadoresServicioIface selAgrupadoresServicio;
    private MaAgrupadoresMedicamentos objeto;
    private List<MaAgrupadoresMedicamentos> registros;
    private LazyDataModel<MaAgrupadoresMedicamentos> lazyRegistros;

    @PostConstruct
    public void Iniciar() {
        lazyRegistros = new LazyDataModel<MaAgrupadoresMedicamentos>() {
            private List<MaAgrupadoresMedicamentos> listaMaAgrupadoresMedicamento;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<MaAgrupadoresMedicamentos> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta().setPrimerRegistro(primerRegistro);
                getParamConsulta().setRegistrosPagina(registrosPagina);
                getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                Refrescar();
                listaMaAgrupadoresMedicamento = getRegistros();
                setRowCount(getParamConsulta().getCantidadRegistros());
                return listaMaAgrupadoresMedicamento;
            }

            @Override
            public String getRowKey(MaAgrupadoresMedicamentos maAgrupadoresMedicamento) {
                return maAgrupadoresMedicamento.getId().toString();
            }

            @Override
            public MaAgrupadoresMedicamentos getRowData(String maAgrupadoresMedicamentoId) {
                Integer id = Integer.valueOf(maAgrupadoresMedicamentoId);
                for (MaAgrupadoresMedicamentos maAgrupadoresMedicamento : listaMaAgrupadoresMedicamento) {
                    if (id.equals(maAgrupadoresMedicamento.getId())) {
                        return maAgrupadoresMedicamento;
                    }
                }
                return null;
            }

        };
    }

    public void Refrescar() {
        getSelAgrupadoresServicio().cargaInicial(this);
    }

    public AgrupadoresServicioIface getSelAgrupadoresServicio() {
        return selAgrupadoresServicio;
    }

    public void setSelAgrupadoresServicio(AgrupadoresServicioIface selAgrupadoresServicio) {
        this.selAgrupadoresServicio = selAgrupadoresServicio;
    }

    public MaAgrupadoresMedicamentos getObjet() {
        return objeto;
    }

    public void setObjeto(MaAgrupadoresMedicamentos objeto) {
        this.objeto = objeto;
    }

    public List<MaAgrupadoresMedicamentos> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MaAgrupadoresMedicamentos> registros) {
        this.registros = registros;
    }

    public LazyDataModel<MaAgrupadoresMedicamentos> getLazyRegistros() {
        return lazyRegistros;
    }

    public void setLazyRegistros(LazyDataModel<MaAgrupadoresMedicamentos> lazyRegistros) {
        this.lazyRegistros = lazyRegistros;
    }

    public void onRowSelectEspecialidad(SelectEvent event) {
        objeto = (MaAgrupadoresMedicamentos) event.getObject();
    }

}
