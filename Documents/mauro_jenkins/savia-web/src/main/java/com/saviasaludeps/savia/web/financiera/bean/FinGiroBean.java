/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.financiera.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.financiera.FinGiro;
import com.saviasaludeps.savia.web.financiera.servicio.FinGiroServicioIface;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.util.ArrayList;
import org.primefaces.PrimeFaces;
import org.primefaces.model.SortMeta;

/**
 *
 * @author jeperez
 */
@ManagedBean
@ViewScoped
public class FinGiroBean extends Url {

    @Autowired
    private FinGiroServicioIface finGiroServicio;
    private FinGiro objeto;
    private List<FinGiro> registros = new ArrayList<>();
    private LazyDataModel<FinGiro> lazyFinGiros;


    public FinGiroBean() {
        try {
            this.objeto = new FinGiro();
            Modulo _mod = super.validarModulo(Modulo.ID_FIN_GIROS);
            if (_mod == null) {
                super.redireccionar("/savia/home.faces");
            } else {
                super.setModulo(_mod);
                inicializarTablaGiros();
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

    

    public FinGiro getObjeto() {
        return objeto;
    }

    public void setObjeto(FinGiro objeto) {
        this.objeto = objeto;
    }

  

    public void listar() {
        refrescar();
        procesoFinal();
    }



    public void refrescar() {
            super.setAccion(Url.ACCION_LISTAR);
            getFinGiroServicio().Accion(this);
    }

   

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;    
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmFinGiros");
            }
        }
        generarMensajes();
    }
    
    public void crear() {
        super.setAccion(ACCION_CREAR);
        getFinGiroServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmCrear:panelCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }
    
    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getFinGiroServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_EDITAR);
        getFinGiroServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmEditar:panelEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getFinGiroServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    
    public void inicializarTablaGiros() {
        lazyFinGiros = new LazyDataModel<FinGiro>() {

            private List<FinGiro> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<FinGiro> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
            public String getRowKey(FinGiro objeto) {
                return objeto.getId().toString();
            }

            @Override
            public FinGiro getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (FinGiro objeto : lista) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };

    }

    public List<FinGiro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<FinGiro> registros) {
        this.registros = registros;
    }

    public FinGiroServicioIface getFinGiroServicio() {
        return finGiroServicio;
    }

    public void setFinGiroServicio(FinGiroServicioIface finGiroServicio) {
        this.finGiroServicio = finGiroServicio;
    }

    public LazyDataModel<FinGiro> getLazyFinGiros() {
        return lazyFinGiros;
    }

    public void setLazyFinGiros(LazyDataModel<FinGiro> lazyFinGiros) {
        this.lazyFinGiros = lazyFinGiros;
    }


}
