/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.webservice.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.webservice.WsServicio;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.webservice.servicio.WsServicioIface;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author Jaime Andres Olarte
 */
@ManagedBean
@ViewScoped
public class WsServicioBean extends Url {

    private WsServicioIface wsServicio;
    private WsServicio objeto;
    private List<WsServicio> registros;
    private LazyDataModel<WsServicio> lazyServicio;

    public WsServicioBean() {
        this.objeto = new WsServicio();
        Modulo _mod = super.validarModulo(Modulo.ID_WEBSERVICES_SERVICIOS);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionar("/wicloud/home.faces");
        } else {
            super.setModulo(_mod);
            lazyServicio = new LazyDataModel<WsServicio>() {

                private List<WsServicio> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<WsServicio> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(WsServicio objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public WsServicio getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (WsServicio objeto : lista) {
                        if (id.equals(objeto.getId())) {
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
        getWsServicio().cargaInicial(this);
    }

    public WsServicioIface getWsServicio() {
        return wsServicio;
    }

    public void setWsServicio(WsServicioIface wsServicio) {
        this.wsServicio = wsServicio;
    }

    public WsServicio getObjeto() {
        return objeto;
    }

    public void setObjeto(WsServicio objeto) {
        this.objeto = objeto;
    }

    public List<WsServicio> getRegistros() {
        return registros;
    }

    public void setRegistros(List<WsServicio> registros) {
        this.registros = registros;
    }

    public LazyDataModel<WsServicio> getLazyServicio() {
        return lazyServicio;
    }

    public void setLazyServicio(LazyDataModel<WsServicio> lazyServicio) {
        this.lazyServicio = lazyServicio;
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getWsServicio().Accion(this);
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getWsServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getWsServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getWsServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
        }
        procesoFinal();
    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_EDITAR);
        getWsServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getWsServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide()");
        }
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        getWsServicio().Accion(this);
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_GUARDAR:
                    crearLog(getObjeto().toString());
                case Url.ACCION_MODIFICAR:
                    crearLog(getObjeto().toString());
                case Url.ACCION_BORRAR:
                    crearLog(getObjeto().toString());
                case Url.ACCION_LISTAR:
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    PrimeFaces.current().ajax().update("frmServicios");
                    break;
            }
            refrescar();
        }
        generarMensajes();
    }

}
