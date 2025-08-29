/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.webservice.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.webservice.WsConexion;
import com.saviasaludeps.savia.dominio.webservice.WsServicio;
import com.saviasaludeps.savia.dominio.webservice.WsToken;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import com.saviasaludeps.savia.web.webservice.servicio.WsConexionServicioIface;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

/**
 *
 * @author rpalacic (SystemTech Integral)
 */
@ManagedBean
@ViewScoped
public class WsConexionBean extends Url {

    private WsConexionServicioIface wsConexionServicio;
    private WsConexion objeto;
    private List<WsConexion> registros;
    private LazyDataModel<WsConexion> lazyZona;
    private List<WsToken> listWsTokens;

    private List<WsServicio> listaWsServicios;

    private int cantidadToken = 20;

    public WsConexionBean() {
        this.objeto = new WsConexion();
        Modulo _mod = super.validarModulo(Modulo.ID_WEBSERVICES_CONEXIONES);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionar("/wicloud/home.faces");
        } else {
            super.setModulo(_mod);
            lazyZona = new LazyDataModel<WsConexion>() {

                private List<WsConexion> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<WsConexion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(WsConexion objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public WsConexion getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (WsConexion objeto : lista) {
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
        getWsConexionServicio().cargaInicial(this);
    }

    public WsConexion getObjeto() {
        return objeto;
    }

    public void setObjeto(WsConexion objeto) {
        this.objeto = objeto;
    }

    public List<WsConexion> getRegistros() {
        return registros;
    }

    public void setRegistros(List<WsConexion> registros) {
        this.registros = registros;
    }

    public LazyDataModel<WsConexion> getLazyZona() {
        return lazyZona;
    }

    public void setLazyZona(LazyDataModel<WsConexion> lazyZona) {
        this.lazyZona = lazyZona;
    }

    public List<WsServicio> getListaWsServicios() {
        return listaWsServicios;
    }

    public void setListaWsServicios(List<WsServicio> listaWsServicios) {
        this.listaWsServicios = listaWsServicios;
    }

    public WsConexionServicioIface getWsConexionServicio() {
        return wsConexionServicio;
    }

    public void setWsConexionServicio(WsConexionServicioIface wsConexionServicio) {
        this.wsConexionServicio = wsConexionServicio;
    }

    public List<WsToken> getListWsTokens() {
        return listWsTokens;
    }

    public void setListWsTokens(List<WsToken> listWsTokens) {
        this.listWsTokens = listWsTokens;
    }

    public int getCantidadToken() {
        return cantidadToken;
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void ver(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getWsConexionServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer:panelVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        getWsConexionServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmCrear:panelCrear");
        PrimeFaces.current().ajax().update("frmCrear:panelCrear");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        getWsConexionServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
        }
        procesoFinal();
    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_EDITAR);
        getWsConexionServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmEditar:panelEditar");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getWsConexionServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide()");
        }
        procesoFinal();
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        getWsConexionServicio().Accion(this);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getWsConexionServicio().Accion(this);
    }

    public void verToken(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_ADICIONAL_1);
        getWsConexionServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmVerToken");
        PrimeFaces.current().ajax().update("frmVerToken");
        PrimeFaces.current().executeScript("PF('dialogoVerToken').show()");
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
                    crearLog(getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmConexiones");
                    break;
            }
            refrescar();
        }
        generarMensajes();
    }
}
