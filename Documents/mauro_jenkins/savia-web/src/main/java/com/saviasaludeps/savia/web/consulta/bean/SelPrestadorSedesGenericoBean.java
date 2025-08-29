/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.consulta.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.consulta.servicio.SelPrestadorSedesGenericoIface;
import com.saviasaludeps.savia.web.consulta.servicio.SelPrestadorSedesGenericoImpl;
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
 * @author Jose Pérez Hernández
 */
@ManagedBean
@SessionScoped
public class SelPrestadorSedesGenericoBean extends Url {

    private SelPrestadorSedesGenericoIface selPrestadorSedesGenericoServicio;
    private CntPrestadorSede objeto;
    private List<CntPrestadorSede> registros;
    private LazyDataModel<CntPrestadorSede> lazyRegistros;
    private List<Maestro> listaTiposDocumento;
    //2025-05-26 jyperez se crea objeto prestador para poder validar su información
    private CntPrestador cntPrestador;

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

        } catch (Exception ex) {
            setObjeto(new CntPrestadorSede());
            addError(ex.getMessage());
        }
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        //super.setDoAccion(Url.ACCION_ADICIONAL_2);
        getSelPrestadorSedesGenericoServicio().cargaInicial(this);
    }
    
    public void adicionarPrestador(CntPrestador cntPrestador){
        //actualmente se tiene configurado el parámetro 5 en las consultas del Servidor
        getParamConsulta().setParametroConsulta5(cntPrestador.getId());
        //2025-05-26 jyperez almacenamos el prestador
        this.cntPrestador = cntPrestador;
        //getSelPrestadorSedesGenericoServicio().cargaInicial(this);
    }

    public SelPrestadorSedesGenericoIface getSelPrestadorSedesGenericoServicio() {
        if (selPrestadorSedesGenericoServicio == null) {
            selPrestadorSedesGenericoServicio = new SelPrestadorSedesGenericoImpl();
        }
        return selPrestadorSedesGenericoServicio;
    }

    public void setSelPrestadorSedesGenericoServicio(SelPrestadorSedesGenericoIface selPrestadorSedesGenericoServicio) {
        this.selPrestadorSedesGenericoServicio = selPrestadorSedesGenericoServicio;
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

    /**
     * @return the cntPrestador
     */
    public CntPrestador getCntPrestador() {
        return cntPrestador;
    }

    /**
     * @param cntPrestador the cntPrestador to set
     */
    public void setCntPrestador(CntPrestador cntPrestador) {
        this.cntPrestador = cntPrestador;
    }

}
