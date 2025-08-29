/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.webservice.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.webservice.WsConexion;
import com.saviasaludeps.savia.dominio.webservice.WsServicio;
import com.saviasaludeps.savia.dominio.webservice.WsTransaccion;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import com.saviasaludeps.savia.web.webservice.servicio.WsTransaccionServicioIface;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

/**
 *
 * @author rpalacic (SystemTech Integral)
 */
@ManagedBean
@ViewScoped
public class WsTransaccionBean extends Url {

    private WsTransaccionServicioIface wsTransaccionServicio;
    private WsTransaccion objeto;
    private List<WsTransaccion> registros;
    private LazyDataModel<WsTransaccion> lazyTransacciones;
    private HashMap<Integer, Maestro> hashMaestros;

    private List<WsServicio> listaServicios;
    private List<WsConexion> listaConexiones;

    public WsTransaccionBean() {
        this.objeto = new WsTransaccion();
        Modulo mod = super.validarModulo(Modulo.ID_WEBSERVICES_TRANSACCIONES);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (mod == null) {
            super.redireccionar("/wicloud/home.faces");
        } else {
            super.setModulo(mod);
            lazyTransacciones = new LazyDataModel<WsTransaccion>() {

                private List<WsTransaccion> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<WsTransaccion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(WsTransaccion objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public WsTransaccion getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (WsTransaccion objeto : lista) {
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
        getWsTransaccionServicio().cargaInicial(this);
        listar();
    }

    public WsTransaccion getObjeto() {
        return objeto;
    }

    public void setObjeto(WsTransaccion objeto) {
        this.objeto = objeto;
    }

    public List<WsTransaccion> getRegistros() {
        return registros;
    }

    public void setRegistros(List<WsTransaccion> registros) {
        this.registros = registros;
    }

    public LazyDataModel<WsTransaccion> getLazyTransacciones() {
        return lazyTransacciones;
    }

    public void setLazyTransacciones(LazyDataModel<WsTransaccion> lazyTransacciones) {
        this.lazyTransacciones = lazyTransacciones;
    }

    public HashMap<Integer, Maestro> getHashMaestros() {
        return hashMaestros;
    }

    public String genMaestroNombre(int id) {
        try {
            return hashMaestros.get(id).getNombre();
        } catch (Exception e) {
            return " -- ";
        }
    }

    public String genMaestroValor(int id) {
        try {
            return hashMaestros.get(id).getValor();
        } catch (Exception e) {
            return " -- ";
        }
    }

    public void setHashMaestros(HashMap<Integer, Maestro> hashMaestros) {
        this.hashMaestros = hashMaestros;
    }

    public List<WsServicio> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(List<WsServicio> listaServicios) {
        this.listaServicios = listaServicios;
    }

    public List<WsConexion> getListaConexiones() {
        return listaConexiones;
    }

    public void setListaConexiones(List<WsConexion> listaConexiones) {
        this.listaConexiones = listaConexiones;
    }

    public WsTransaccionServicioIface getWsTransaccionServicio() {
        return wsTransaccionServicio;
    }

    public void setWsTransaccionServicio(WsTransaccionServicioIface wsTransaccionServicio) {
        this.wsTransaccionServicio = wsTransaccionServicio;
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getWsTransaccionServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer:panelVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    //Desbloquea una transacción en contigencia
    public void desbloquearFactura(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_2);
        getWsTransaccionServicio().Accion(this);
        this.addMensaje("Transaccion: " + id + " desbloqueada");
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getWsTransaccionServicio().Accion(this);
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_VER:
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                case Url.ACCION_LISTAR:
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_2:
                    crearLog(getObjeto().toString());
                    super.setAccion(Url.ACCION_LISTAR);
                    getWsTransaccionServicio().Accion(this);
                    PrimeFaces.current().ajax().update("frmTransacciones:tablaRegistros");
                    break;
            }
        }
        generarMensajes();
    }

}
