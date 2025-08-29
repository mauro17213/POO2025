/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.contratacion.seleccion.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.contratacion.seleccion.servicio.PrestadorSedeIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.Util;
import java.util.Date;
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
public class SelPrestadorSedesBean extends Url {

    private PrestadorSedeIface prestadorSedeServicio;
    private CntPrestadorSede objeto;
    private List<CntPrestadorSede> registros;
    private LazyDataModel<CntPrestadorSede> lazyRegistros;
    private List<Maestro> listaTiposDocumento;

    @PostConstruct
    public void iniciar() {
        try {
            this.setParamConsulta(new ParamConsulta());
            this.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyRegistros = new LazyDataModel<CntPrestadorSede>() {

                private List<CntPrestadorSede> sedes;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CntPrestadorSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    getParamConsulta().getFiltros().put("cntContratosId.activo", true);
                    getParamConsulta().getFiltros().put("fecha", Util.fechaDateAString(new Date()));

                    refrescar();
                    sedes = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return sedes;
                }

                @Override
                public String getRowKey(CntPrestadorSede sede) {
                    return sede.getId().toString();
                }

                @Override
                public CntPrestadorSede getRowData(String sedesId) {
                    Integer id = Integer.valueOf(sedesId);
                    for (CntPrestadorSede sede : sedes) {
                        if (id.equals(sede.getId())) {
                            return sede;
                        }
                    }
                    return null;
                }
            };
            PrimeFaces.current().executeScript("PF('dialogoSedeLista').show()");
            PrimeFaces.current().ajax().update("frmSedeLista");

        } catch (Exception ex) {
            setObjeto(new CntPrestadorSede());
            addError(ex.getMessage());
        }
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(Url.ACCION_ADICIONAL_2);
        getPrestadorSedeServicio().cargaInicial(this);
    }

    public PrestadorSedeIface getPrestadorSedeServicio() {
        return prestadorSedeServicio;
    }

    public void setPrestadorSedeServicio(PrestadorSedeIface prestadorSedeServicio) {
        this.prestadorSedeServicio = prestadorSedeServicio;
    }

    public CntPrestadorSede getObjeto() {
        return objeto;
    }

    public void setObjeto(CntPrestadorSede objeto) {
        this.objeto = objeto;
    }

    public List<CntPrestadorSede> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CntPrestadorSede> registros) {
        this.registros = registros;
    }

    public LazyDataModel<CntPrestadorSede> getLazyRegistros() {
        return lazyRegistros;
    }

    public void setLazyRegistros(LazyDataModel<CntPrestadorSede> lazyRegistros) {
        this.lazyRegistros = lazyRegistros;
    }

    public void onRowSelectSede(SelectEvent event) {
        objeto = (CntPrestadorSede) event.getObject();
    }

    public List<Maestro> getListaTiposDocumento() {
        return listaTiposDocumento;
    }

    public void setListaTiposDocumento(List<Maestro> listaTiposDocumento) {
        this.listaTiposDocumento = listaTiposDocumento;
    }

}
