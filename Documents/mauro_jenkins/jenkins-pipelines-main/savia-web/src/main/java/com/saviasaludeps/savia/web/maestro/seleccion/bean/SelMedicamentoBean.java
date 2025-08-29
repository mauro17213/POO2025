/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.seleccion.bean;

import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.web.maestro.seleccion.servicio.MedicamentoServicioIface;
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
import org.primefaces.model.SortMeta;

/**
 *
 * @author AlexanderDiaz
 */
@ManagedBean
@SessionScoped
public class SelMedicamentoBean extends Url {

    private MedicamentoServicioIface selMedicamentoServicio;
    private MaMedicamento objeto;
    private List<MaMedicamento> registros;
    private LazyDataModel<MaMedicamento> lazyRegistros;

    @PostConstruct
    public void iniciar() {
        //parametro activo
        getParamConsulta().setParametroConsulta1("1");
        lazyRegistros = new LazyDataModel<MaMedicamento>() {
            private List<MaMedicamento> listaMaMedicamento;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<MaMedicamento> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta().setPrimerRegistro(primerRegistro);
                getParamConsulta().setRegistrosPagina(registrosPagina);
                getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescar();
                listaMaMedicamento = getRegistros();
                setRowCount(getParamConsulta().getCantidadRegistros());
                return listaMaMedicamento;
            }

            @Override
            public String getRowKey(MaMedicamento maMedicamento) {
                return maMedicamento.getId().toString();
            }

            @Override
            public MaMedicamento getRowData(String maMedicamentoId) {
                Integer id = Integer.valueOf(maMedicamentoId);
                for (MaMedicamento maMedicamento : listaMaMedicamento) {
                    if (id.equals(maMedicamento.getId())) {
                        return maMedicamento;
                    }
                }
                return null;
            }
        };

    }

    public void refrescar() {
        getSelMedicamentoServicio().cargaInicial(this);
    }

    public MedicamentoServicioIface getSelMedicamentoServicio() {
        return selMedicamentoServicio;
    }

    public void setSelMedicamentoServicio(MedicamentoServicioIface selMedicamentoServicio) {
        this.selMedicamentoServicio = selMedicamentoServicio;
    }

    public MaMedicamento getObjeto() {
        return objeto;
    }

    public void setObjeto(MaMedicamento objeto) {
        this.objeto = objeto;
    }

    public List<MaMedicamento> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MaMedicamento> registros) {
        this.registros = registros;
    }

    public LazyDataModel<MaMedicamento> getLazyRegistros() {
        return lazyRegistros;
    }

    public void setLazyRegistros(LazyDataModel<MaMedicamento> lazyRegistros) {
        this.lazyRegistros = lazyRegistros;
    }

    public void onRowSelectMedicamento(SelectEvent event) {
        objeto = (MaMedicamento) event.getObject();
    }
}
