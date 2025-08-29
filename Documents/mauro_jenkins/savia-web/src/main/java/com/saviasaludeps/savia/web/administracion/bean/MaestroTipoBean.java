/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.administracion.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipoRelacion;
import com.saviasaludeps.savia.dominio.administracion.Perfil;
import com.saviasaludeps.savia.web.administracion.servicio.MaestroTipoServicioIface;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author rpalacic (SystemTech Integral)
 */
@ManagedBean
@ViewScoped
public class MaestroTipoBean extends Url {

    private MaestroTipoServicioIface maestroTipoServicio;
    private MaestroTipo objeto;
    private List<MaestroTipo> registros;
    private LazyDataModel<MaestroTipo> lazyMaestroTipo;

    private List<MaestroTipo> listaMaestroTiposPadre;
    private List<Perfil> listaPerfiles;

    private List<String> selectedMaestrosPadres = new ArrayList();
    private List<MaestroTipoRelacion> listaMaestroTipoRelacion;

    public MaestroTipoBean() {
        this.objeto = new MaestroTipo();
        Modulo _mod = super.validarModulo(Modulo.ID_MAESTRO_TIPOS);
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyMaestroTipo = new LazyDataModel<MaestroTipo>() {

                private List<MaestroTipo> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<MaestroTipo> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(MaestroTipo objeto) {
                    return objeto.getTipo();
                }

                @Override
                public MaestroTipo getRowData(String objetoTipo) {
                    for (MaestroTipo objeto : lista) {
                        if (objetoTipo.equals(objeto.getTipo())) {
                            return objeto;
                        }
                    }
                    return null;
                }

            };
        }
    }

    @PostConstruct
    public void postConstruct() {
        getMaestroTipoServicio().cargasInicial(this);
        listar();
    }

    public MaestroTipo getObjeto() {
        return objeto;
    }

    public void setObjeto(MaestroTipo objeto) {
        this.objeto = objeto;
    }

    public List<MaestroTipo> getRegistros() {
        return registros;
    }

    public void setRegistros(List<MaestroTipo> registros) {
        this.registros = registros;
    }

    public LazyDataModel<MaestroTipo> getLazyMaestroTipo() {
        return lazyMaestroTipo;
    }

    public void setLazyMaestro(LazyDataModel<MaestroTipo> lazyMaestroTipo) {
        this.lazyMaestroTipo = lazyMaestroTipo;
    }

    public List<MaestroTipo> getListaMaestroTiposPadre() {
        return listaMaestroTiposPadre;
    }

    public void setListaMaestroTiposPadre(List<MaestroTipo> listaMaestroTiposPadre) {
        this.listaMaestroTiposPadre = listaMaestroTiposPadre;
    }

    public List<Perfil> getListaPerfiles() {
        return listaPerfiles;
    }

    public void setListaPerfiles(List<Perfil> listaPerfiles) {
        this.listaPerfiles = listaPerfiles;
    }

    public MaestroTipoServicioIface getMaestroTipoServicio() {
        return maestroTipoServicio;
    }

    public void setMaestroTipoServicio(MaestroTipoServicioIface maestroTipoServicio) {
        this.maestroTipoServicio = maestroTipoServicio;
    }

    public List<String> getSelectedMaestrosPadres() {
        return selectedMaestrosPadres;
    }

    public void setSelectedMaestrosPadres(List<String> selectedMaestrosPadres) {
        this.selectedMaestrosPadres = selectedMaestrosPadres;
    }

    public List<MaestroTipoRelacion> getListaMaestroTipoRelacion() {
        return listaMaestroTipoRelacion;
    }

    public void setListaMaestroTipoRelacion(List<MaestroTipoRelacion> listaMaestroTipoRelacion) {
        this.listaMaestroTipoRelacion = listaMaestroTipoRelacion;
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getMaestroTipoServicio().Accion(this);
    }

    public void ver(String tipo) {
        getObjeto().setTipo(tipo);
        super.setAccion(ACCION_VER);
        getMaestroTipoServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer:panelVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getMaestroTipoServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear:panelCrear");
        PrimeFaces.current().ajax().update("frmCrear:panelCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getMaestroTipoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(String tipo) {
        getObjeto().setTipo(tipo);
        setSelectedMaestrosPadres(new ArrayList());
        super.setAccion(ACCION_EDITAR);
        getMaestroTipoServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmEditar:panelEditar");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getMaestroTipoServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(String tipo) {
        getObjeto().setTipo(tipo);
        super.setAccion(ACCION_BORRAR);
        getMaestroTipoServicio().Accion(this);
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmMaestroTipos");
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;
            }
        }
        generarMensajes();
    }

}
