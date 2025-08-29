/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.bean;

import com.saviasaludeps.savia.dominio.administracion.GnValidacionCampo;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.web.administracion.servicio.GnValidacionCampoIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
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
import org.primefaces.model.SortMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author sgiraldov
 */
@ManagedBean
@ViewScoped
public class GnValidacionCampoBean extends Url {

    @Autowired
    private GnValidacionCampoIface gnValidacionCampoServicio;
    private GnValidacionCampo objeto;
    private List<GnValidacionCampo> registros;
    private LazyDataModel<GnValidacionCampo> lazyValidacionCampo;

    //Listas auxiliares
    private List<MaestroTipo> listaMaestroTipos;

    public GnValidacionCampoBean() {
        this.objeto = new GnValidacionCampo();
        Modulo _mod = super.validarModulo(Modulo.ID_GN_VALIDACION_CAMPOS);
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyValidacionCampo = new LazyDataModel<GnValidacionCampo>() {

                private List<GnValidacionCampo> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<GnValidacionCampo> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(GnValidacionCampo objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public GnValidacionCampo getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (GnValidacionCampo objeto : lista) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }

            };
        }
    }

    public GnValidacionCampoIface getGnValidacionCampoServicio() {
        return gnValidacionCampoServicio;
    }

    public void setGnValidacionCampoServicio(GnValidacionCampoIface gnValidacionCampoServicio) {
        this.gnValidacionCampoServicio = gnValidacionCampoServicio;
    }

    public GnValidacionCampo getObjeto() {
        return objeto;
    }

    public void setObjeto(GnValidacionCampo objeto) {
        this.objeto = objeto;
    }

    public List<GnValidacionCampo> getRegistros() {
        return registros;
    }

    public void setRegistros(List<GnValidacionCampo> registros) {
        this.registros = registros;
    }

    public LazyDataModel<GnValidacionCampo> getLazyValidacionCampo() {
        return lazyValidacionCampo;
    }

    public void setLazyValidacionCampo(LazyDataModel<GnValidacionCampo> lazyValidacionCampo) {
        this.lazyValidacionCampo = lazyValidacionCampo;
    }

    public List<MaestroTipo> getListaMaestroTipos() {
        return listaMaestroTipos;
    }

    public void setListaMaestroTipos(List<MaestroTipo> listaMaestroTipos) {
        this.listaMaestroTipos = listaMaestroTipos;
    }

    @PostConstruct
    public void postConstruct() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        getGnValidacionCampoServicio().cargasInicial(this);
        listar();
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getGnValidacionCampoServicio().Accion(this);
    }

    public void crear() {
        super.setAccion(Url.ACCION_CREAR);
        getGnValidacionCampoServicio().Accion(this);
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(Url.ACCION_GUARDAR);
        getGnValidacionCampoServicio().Accion(this);
        procesoFinal();
    }

    public void ver(int id) {
        setObjeto(new GnValidacionCampo(id));
        super.setAccion(Url.ACCION_VER);
        getGnValidacionCampoServicio().Accion(this);
        procesoFinal();
    }

    public void editar(int id) {
        setObjeto(new GnValidacionCampo(id));
        super.setAccion(Url.ACCION_EDITAR);
        getGnValidacionCampoServicio().Accion(this);
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(Url.ACCION_MODIFICAR);
        getGnValidacionCampoServicio().Accion(this);
        procesoFinal();
    }

    public void borrar(int id) {
        super.setAccion(Url.ACCION_BORRAR);
        getGnValidacionCampoServicio().Accion(this);
        procesoFinal();
    }

    public void completarMaestro() {
        for (MaestroTipo tipo : getListaMaestroTipos()) {
            if (tipo.getTipo().equals(getObjeto().getGnMaestroTiposTipo().getTipo())) {
                getObjeto().setGnMaestroTiposTipo(tipo);
                break;
            }
        }
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmValidacionCampos");
                    break;
                case Url.ACCION_GUARDAR:
                    PrimeFaces.current().ajax().update("frmValidacionCampos");
                    PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
                    break;
                case Url.ACCION_MODIFICAR:
                    PrimeFaces.current().ajax().update("frmValidacionCampos");
                    PrimeFaces.current().executeScript("PF('dialogoEditar').hide()");
                    break;
                case Url.ACCION_VER:
                    PrimeFaces.current().ajax().update("frmVer");
                    PrimeFaces.current().executeScript("PF('dialogoVer').show()");
                    break;
                case Url.ACCION_CREAR:
                    PrimeFaces.current().ajax().update("frmCrear");
                    PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
                    break;
                case Url.ACCION_EDITAR:
                    PrimeFaces.current().ajax().update("frmEditar");
                    PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
                    crearLog(getObjeto().toString());
                    break;
            }
        }
        generarMensajes();
    }

}
