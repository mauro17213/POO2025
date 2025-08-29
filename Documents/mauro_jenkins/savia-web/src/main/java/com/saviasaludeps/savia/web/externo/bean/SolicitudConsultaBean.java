/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.externo.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.solicitud.GsAfiliado;
import com.saviasaludeps.savia.dominio.solicitud.GsSolicitud;
import com.saviasaludeps.savia.web.externo.servicio.SolicitudConsultaServicioIface;
import com.saviasaludeps.savia.web.externo.servicio.SolicitudConsultaServicioImpl;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
 * @author admin
 */
@ManagedBean
@ViewScoped
public class SolicitudConsultaBean extends Url {

    private GsSolicitud objeto;
    private SolicitudConsultaServicioIface solicitudConsultaServicio;
    private List<GsSolicitud> registros;
    private LazyDataModel<GsSolicitud> lazySolicitud;

    private List<Maestro> listaTiposDocumento;
    private HashMap<Integer, Maestro> hashTiposDocumento;

    public final static char ACCION_BUSCAR_SOLICITUDES_SERVICE = '0';

    private boolean habilitarSeccionResultados;

    private List<Ubicacion> ubicaciones;
    private HashMap<Integer, Ubicacion> ubicacionesRecursiva;

    public boolean mostrarTablaSolicitudes;

    private Date fechaMaxima;

    public SolicitudConsultaBean() {
        this.objeto = new GsSolicitud();
        this.objeto.setGsAfiliado(new GsAfiliado());
        this.fechaMaxima = new Date();
        this.listaTiposDocumento = new ArrayList<>();
        this.hashTiposDocumento = new HashMap<>();
        this.solicitudConsultaServicio = new SolicitudConsultaServicioImpl();
    }

    @PostConstruct
    public void postConstruct() {
        getSolicitudConsultaServicio().cargaInicial(this);
        this.setTamanoPagina(10);
        lazySolicitud = new LazyDataModel<GsSolicitud>() {

            private List<GsSolicitud> vendedores;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<GsSolicitud> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta().setPrimerRegistro(primerRegistro);
                getParamConsulta().setRegistrosPagina(registrosPagina);
                getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescar();
                vendedores = getRegistros();
                setRowCount(getParamConsulta().getCantidadRegistros());
                return vendedores;
            }

            @Override
            public String getRowKey(GsSolicitud vendedor) {
                return vendedor.getId().toString();
            }

            @Override
            public GsSolicitud getRowData(String vendedorId) {
                Integer id = Integer.valueOf(vendedorId);
                for (GsSolicitud vendedor : vendedores) {
                    if (id.equals(vendedor.getId())) {
                        return vendedor;
                    }
                }
                return null;
            }
        };

        if (super.isError()) {
            PrimeFaces.current().ajax().update("frmBusquedaSolicitud");
        }
        procesoFinal();
    }

    public GsSolicitud getObjeto() {
        return objeto;
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getSolicitudConsultaServicio().Accion(this);
    }

    public void setObjeto(GsSolicitud objeto) {
        this.objeto = objeto;
    }

    public List<Maestro> getListaTiposDocumento() {
        return listaTiposDocumento;
    }

    public void setListaTiposDocumento(List<Maestro> listaTiposDocumento) {
        this.listaTiposDocumento = listaTiposDocumento;
    }

    public HashMap<Integer, Maestro> getHashTiposDocumento() {
        return hashTiposDocumento;
    }

    public void setHashTiposDocumento(HashMap<Integer, Maestro> hashTiposDocumento) {
        this.hashTiposDocumento = hashTiposDocumento;
    }

    public boolean isHabilitarSeccionResultados() {
        return habilitarSeccionResultados;
    }

    public void setHabilitarSeccionResultados(boolean habilitarSeccionResultados) {
        this.habilitarSeccionResultados = habilitarSeccionResultados;
    }

    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public SolicitudConsultaServicioIface getSolicitudConsultaServicio() {
        return solicitudConsultaServicio;
    }

    public void setSolicitudConsultaServicio(SolicitudConsultaServicioIface solicitudConsultaServicio) {
        this.solicitudConsultaServicio = solicitudConsultaServicio;
    }

    public boolean isMostrarTablaSolicitudes() {
        return mostrarTablaSolicitudes;
    }

    public void setMostrarTablaSolicitudes(boolean mostrarTablaSolicitudes) {
        this.mostrarTablaSolicitudes = mostrarTablaSolicitudes;
    }

    public LazyDataModel<GsSolicitud> getLazySolicitud() {
        return lazySolicitud;
    }

    public void setLazySolicitud(LazyDataModel<GsSolicitud> lazySolicitud) {
        this.lazySolicitud = lazySolicitud;
    }

    public List<GsSolicitud> getRegistros() {
        return registros;
    }

    public void setRegistros(List<GsSolicitud> registros) {
        this.registros = registros;
    }

    public Date getFechaMaxima() {
        return fechaMaxima;
    }

    public void setFechaMaxima(Date fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case ACCION_BUSCAR_SOLICITUDES_SERVICE:
                    PrimeFaces.current().ajax().update("frmBusquedaSolicitud");
//                    RequestContext.getCurrentInstance().reset("frmBusquedaSolicitud");
                    PrimeFaces.current().ajax().update("frmSolicitudConsultar");
//                    RequestContext.getCurrentInstance().reset("frmSolicitudConsultar");
                    break;
                case Url.ACCION_LISTAR:
                    break;
            }
        }
        generarMensajes();
    }

    public void buscarSolicitudesServicio() {
        this.setMostrarTablaSolicitudes(false);
        super.setAccion(ACCION_BUSCAR_SOLICITUDES_SERVICE);
        getSolicitudConsultaServicio().Accion(this);

        if (!super.isError()) {
            this.setMostrarTablaSolicitudes(true);
        }

        PrimeFaces.current().ajax().update("frmSolicitudConsultar");
//        RequestContext.getCurrentInstance().reset("frmSolicitudConsultar");
        procesoFinal();
    }

    public String getTipoDocumento(int id) {
        try {
            return hashTiposDocumento.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public void limpiarFormularioBusqueda() {
        this.getObjeto().getGsAfiliado().setDocumentoNumero(null);
        this.getObjeto().getGsAfiliado().setDocumentoTipo(null);
        this.getObjeto().getGsAfiliado().setFechaNacimiento(null);
        this.setHabilitarSeccionResultados(false);
        this.setMostrarTablaSolicitudes(false);
        PrimeFaces.current().ajax().update("frmBusquedaSolicitud");
//        RequestContext.getCurrentInstance().reset("frmBusquedaSolicitud");
        PrimeFaces.current().ajax().update("frmSolicitudConsultar");
//        RequestContext.getCurrentInstance().reset("frmSolicitudConsultar");
    }

    public HashMap<Integer, Ubicacion> getUbicacionesRecursiva() {
        return ubicacionesRecursiva;
    }

    public void setUbicacionesRecursiva(HashMap<Integer, Ubicacion> ubicacionesRecursiva) {
        this.ubicacionesRecursiva = ubicacionesRecursiva;
    }

}
