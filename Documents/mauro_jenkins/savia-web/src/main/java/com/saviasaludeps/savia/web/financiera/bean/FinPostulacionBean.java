/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.financiera.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.financiera.FinPostulacion;
import com.saviasaludeps.savia.web.financiera.servicio.FinPostulacionServicioIface;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.util.ArrayList;
import org.primefaces.model.SortMeta;

/**
 *
 * @author jeperez
 */
@ManagedBean
@ViewScoped
public class FinPostulacionBean extends Url {

    @Autowired
    private FinPostulacionServicioIface finPostulacionServicio;
    private FinPostulacion objeto;
    private List<FinPostulacion> registros = new ArrayList<>();
    private LazyDataModel<FinPostulacion> lazyFinPostulaciones;


    public FinPostulacionBean() {
        try {
            this.objeto = new FinPostulacion();
            Modulo _mod = super.validarModulo(Modulo.ID_FIN_POSTULACIONES);
            if (_mod == null) {
                super.redireccionar("/savia/home.faces");
            } else {
                super.setModulo(_mod);
                inicializarTablaPostulaciones();
            }
        } catch (Exception e) {
            super.redireccionar("/savia/home.faces");
        }

    }

    @PostConstruct
    public void postConstruct() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public FinPostulacionServicioIface getFinPostulacionServicio() {
        return finPostulacionServicio;
    }

    public void setFinPostulacionServicio(FinPostulacionServicioIface finPostulacionServicio) {
        this.finPostulacionServicio = finPostulacionServicio;
    }

    public FinPostulacion getObjeto() {
        return objeto;
    }

    public void setObjeto(FinPostulacion objeto) {
        this.objeto = objeto;
    }

  

    public void listar() {
        refrescar();
        procesoFinal();
    }



    public void refrescar() {
            super.setAccion(Url.ACCION_LISTAR);
            getFinPostulacionServicio().Accion(this);
    }

   

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                    crearLog(getObjeto().toString());
                    break;
            }
        }
        generarMensajes();
    }
    
    public void inicializarTablaPostulaciones() {
        lazyFinPostulaciones = new LazyDataModel<FinPostulacion>() {

            private List<FinPostulacion> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<FinPostulacion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
            public String getRowKey(FinPostulacion objeto) {
                return objeto.getId().toString();
            }

            @Override
            public FinPostulacion getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (FinPostulacion objeto : lista) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };

    }

    public List<FinPostulacion> getRegistros() {
        return registros;
    }

    public void setRegistros(List<FinPostulacion> registros) {
        this.registros = registros;
    }

    public LazyDataModel<FinPostulacion> getLazyFinPostulaciones() {
        return lazyFinPostulaciones;
    }

    public void setLazyFinPostulaciones(LazyDataModel<FinPostulacion> lazyFinPostulaciones) {
        this.lazyFinPostulaciones = lazyFinPostulaciones;
    }



}
