/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.contratacion.seleccion.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.contratacion.seleccion.servicio.SelPrestadorIface;
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
 * @author José Pérez
 */
@ManagedBean
@SessionScoped
public class SelPrestadorBean extends Url {

    private SelPrestadorIface selPrestadorServicio;
    private CntPrestador objeto;
    private List<CntPrestador> registros;
    private LazyDataModel<CntPrestador> lazyRegistros;
    private List<Maestro> listaTiposDocumento;
    private HashMap<Integer, Maestro> hashTiposDocumento;

    @PostConstruct
    public void iniciar() {
        try {
            this.setParamConsulta(new ParamConsulta());
            this.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyRegistros = new LazyDataModel<CntPrestador>() {

                private List<CntPrestador> prestadores;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CntPrestador> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));

                    refrescar();
                    prestadores = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return prestadores;
                }

                @Override
                public String getRowKey(CntPrestador sede) {
                    return sede.getId().toString();
                }

                @Override
                public CntPrestador getRowData(String prestadoresId) {
                    Integer id = Integer.valueOf(prestadoresId);
                    for (CntPrestador pres : prestadores) {
                        if (id.equals(pres.getId())) {
                            return pres;
                        }
                    }
                    return null;
                }
            };
            PrimeFaces.current().executeScript("PF('dialogoPrestadorLista').show()");
            PrimeFaces.current().ajax().update("frmPrestadorLista");

        } catch (Exception ex) {
            setObjeto(new CntPrestador());
            addError(ex.getMessage());
        }
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(Url.ACCION_ADICIONAL_2);
        getSelPrestadorServicio().cargaInicial(this);
    }

    public String getTipoDocumento(int id) {
        String mensaje = "";
        try {
            mensaje = hashTiposDocumento.get(id).getNombre();
        } catch (Exception e) {

        }
        return mensaje;
    }

    public SelPrestadorIface getSelPrestadorServicio() {
        return selPrestadorServicio;
    }

    public void setSelPrestadorServicio(SelPrestadorIface selPrestadorServicio) {
        this.selPrestadorServicio = selPrestadorServicio;
    }

    public CntPrestador getObjeto() {
        return objeto;
    }

    public void setObjeto(CntPrestador objeto) {
        this.objeto = objeto;
    }

    public List<CntPrestador> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CntPrestador> registros) {
        this.registros = registros;
    }

    public LazyDataModel<CntPrestador> getLazyRegistros() {
        return lazyRegistros;
    }

    public void setLazyRegistros(LazyDataModel<CntPrestador> lazyRegistros) {
        this.lazyRegistros = lazyRegistros;
    }

    public void onRowSelectPrestador(SelectEvent event) {
        objeto = (CntPrestador) event.getObject();
    }

    public List<Maestro> getListaTiposDocumento() {
        return listaTiposDocumento;
    }

    public void setListaTiposDocumento(List<Maestro> listaTiposDocumento) {
        this.listaTiposDocumento = listaTiposDocumento;
    }

    /**
     * @return the hashTiposDocumento
     */
    public HashMap<Integer, Maestro> getHashTiposDocumento() {
        return hashTiposDocumento;
    }

    /**
     * @param hashTiposDocumento the hashTiposDocumento to set
     */
    public void setHashTiposDocumento(HashMap<Integer, Maestro> hashTiposDocumento) {
        this.hashTiposDocumento = hashTiposDocumento;
    }

}
