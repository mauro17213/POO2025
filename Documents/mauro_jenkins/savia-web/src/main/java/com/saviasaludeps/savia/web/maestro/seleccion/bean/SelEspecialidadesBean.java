/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.maestro.seleccion.bean;

import com.saviasaludeps.savia.dominio.maestro.MaEspecialidad;
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
import com.saviasaludeps.savia.web.maestro.seleccion.servicio.EspecialidadServicioIface;
import org.primefaces.model.SortMeta;

/**
 *
 * @author AlexanderDiaz
 */
@ManagedBean
@SessionScoped
public class SelEspecialidadesBean extends Url {

    private EspecialidadServicioIface selEspecialidadServicio;
    private MaEspecialidad objeto;
    private List<MaEspecialidad> registros;
    private LazyDataModel<MaEspecialidad> lazyRegistros;

    @PostConstruct
    public void iniciar() {
        lazyRegistros = new LazyDataModel<MaEspecialidad>() {
            private List<MaEspecialidad> listaMaEspecialidad;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<MaEspecialidad> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta().setPrimerRegistro(primerRegistro);
                getParamConsulta().setRegistrosPagina(registrosPagina);
                getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescar();
                listaMaEspecialidad = getRegistros();
                setRowCount(getParamConsulta().getCantidadRegistros());
                return listaMaEspecialidad;
            }

            @Override
            public String getRowKey(MaEspecialidad maEspecialidad) {
                return maEspecialidad.getId().toString();
            }

            @Override
            public MaEspecialidad getRowData(String maEspecialidadId) {
                Integer id = Integer.valueOf(maEspecialidadId);
                for (MaEspecialidad maEspecialidad : listaMaEspecialidad) {
                    if (id.equals(maEspecialidad.getId())) {
                        return maEspecialidad;
                    }
                }
                return null;
            }
        };

    }

    public void refrescar() {
        getSelEspecialidadServicio().cargaInicial(this);
    }

    public EspecialidadServicioIface getSelEspecialidadServicio() {
        return selEspecialidadServicio;
    }

    public void setSelEspecialidadServicio(EspecialidadServicioIface selEspecialidadServicio) {
        this.selEspecialidadServicio = selEspecialidadServicio;
    }

    public MaEspecialidad getObjeto() {
        return objeto;
    }

    public void setObjeto(MaEspecialidad objeto) {
        this.objeto = objeto;
    }

    public List<MaEspecialidad> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MaEspecialidad> registros) {
        this.registros = registros;
    }

    public LazyDataModel<MaEspecialidad> getLazyRegistros() {
        return lazyRegistros;
    }

    public void setLazyRegistros(LazyDataModel<MaEspecialidad> lazyRegistros) {
        this.lazyRegistros = lazyRegistros;
    }

    public void onRowSelectEspecialidad(SelectEvent event) {
        objeto = (MaEspecialidad) event.getObject();
    }
}
