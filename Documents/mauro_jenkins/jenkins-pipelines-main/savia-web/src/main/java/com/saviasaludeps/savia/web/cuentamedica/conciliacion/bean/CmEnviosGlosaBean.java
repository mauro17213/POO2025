/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.conciliacion.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmRadicado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmReintento;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionEncabezado;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionEncabezadoResumen;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionPaquete;
import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmFacturaDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmTransaccion;
import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmTransaccionDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import org.primefaces.model.LazyDataModel;
import com.saviasaludeps.savia.web.cuentamedica.conciliacion.servicio.CmEnviosGlosasServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

@Named("cmEnviosGlosaBean")
@ViewScoped
public class CmEnviosGlosaBean extends Url {

    @Autowired
    private CmEnviosGlosasServicioIface cmReintentosGlosasServicio;
    private CmReintento objeto;
    private List<CmReintento> registros;
    private LazyDataModel<CmReintento> lazyReintentos;
    private List<CmSincronizacionEncabezado> listaDetalles;
    private LinkedHashMap<Integer, String> hashEstadoSincronizacion;
    private List<CmSincronizacion> listaSincronizaciones;
    private List<CmSincronizacionPaquete> listaSincronizacionPaquete;
    private List<CmSincronizacionEncabezadoResumen> listaCmSincronizaconEncabezadoResumen;
    private LazyDataModel<WsCmTransaccion> lazyWsTransacciones;
    private List<WsCmTransaccion> registrosWsTransacciones;
    private LazyDataModel<WsCmTransaccionDetalle> lazyWsTransaccionDetalles;
    private List<WsCmTransaccionDetalle> registrosWsTransaccionDetalles;
    private LazyDataModel<WsCmFactura> lazyWsCmFacturas;
    private List<WsCmFactura> registrosWsCmFacturas;
    private LazyDataModel<WsCmFacturaDetalle> lazyWsCmFacturaDetalles;
    private List<WsCmFacturaDetalle> registrosWsCmFacturaDetalles;

    public static final char ACCION_VER_ENCABEZADOS_FACTURAS = 'a';
    public static final char ACCION_VER_TRANSACCIONES = 'b';
    public static final char ACCION_VER_TRANSACCIONES_DETALLES = 'c';
    public static final char ACCION_VER_CM_FACTURAS = 'd';
    public static final char ACCION_VER_CM_FACTURAS_DETALLES = 'e';
    public static final char ACCION_VER_CM_RADICADO = 'f';

    public static final char ACCION_BUSCAR_REINTENTOS = 'O';
    public static final char ACCION_BUSCAR_PAQUETES_REINTENTO = 'P';
    public static final char ACCION_BUSCAR_RADICADO_PENDIENTE_POR_FACTURA = 'Q';

    private ParamConsulta paramConsultaUtilitario;

    private String jsonContenido;
    private String tituloJsonMensaje;
    private Date fechaEnvio;
    private CmRadicado cmRadicado;
    private String textoAmpliado;

    public CmEnviosGlosaBean() {
        this.objeto = new CmReintento();
        Modulo mod = super.validarModulo(Modulo.ID_REINTENTOS_RESPUESTAS_GLOSAS);
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyReintentos = new LazyDataModel<CmReintento>() {
                private List<CmReintento> respuestaReintentos;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CmReintento> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    respuestaReintentos = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return respuestaReintentos;
                }
                
                @Override
                public String getRowKey(CmReintento respuestaReintento) {
                    return ""+respuestaReintento.getId();
                }

                @Override
                public CmReintento getRowData(String respuestaReintentoId) {
                    Integer id = Integer.valueOf(respuestaReintentoId);
                    for (CmReintento glosa : respuestaReintentos) {
                        if (id.equals(glosa.getId())) {
                            return glosa;
                        }
                    }
                    return null;
                }

            };
        }
    }

    @PostConstruct
    public void postConstruct() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        listar();

    }

    public CmEnviosGlosasServicioIface getCmReintentosGlosasServicio() {
        return cmReintentosGlosasServicio;
    }

    public void setCmReintentosGlosasServicio(CmEnviosGlosasServicioIface cmReintentosGlosasServicio) {
        this.cmReintentosGlosasServicio = cmReintentosGlosasServicio;
    }

    public List<CmSincronizacion> getListaSincronizaciones() {
        if (listaSincronizaciones == null) {
            listaSincronizaciones = new ArrayList<>();
        }
        return listaSincronizaciones;
    }

    public void setListaSincronizaciones(List<CmSincronizacion> listaSincronizaciones) {
        this.listaSincronizaciones = listaSincronizaciones;
    }

    public List<CmSincronizacionPaquete> getListaSincronizacionPaquete() {
        if (listaSincronizacionPaquete == null) {
            listaSincronizacionPaquete = new ArrayList<>();
        }
        return listaSincronizacionPaquete;
    }

    public void setListaSincronizacionPaquete(List<CmSincronizacionPaquete> listaSincronizacionPaquete) {
        this.listaSincronizacionPaquete = listaSincronizacionPaquete;
    }

    public List<CmSincronizacionEncabezadoResumen> getListaCmSincronizaconEncabezadoResumen() {
        if (listaCmSincronizaconEncabezadoResumen == null) {
            listaCmSincronizaconEncabezadoResumen = new ArrayList<>();
        }
        return listaCmSincronizaconEncabezadoResumen;
    }

    public void setListaCmSincronizaconEncabezadoResumen(List<CmSincronizacionEncabezadoResumen> listaCmSincronizaconEncabezadoResumen) {
        this.listaCmSincronizaconEncabezadoResumen = listaCmSincronizaconEncabezadoResumen;
    }

    public ParamConsulta getParamConsultaUtilitario() {
        if (paramConsultaUtilitario == null) {
            paramConsultaUtilitario = new ParamConsulta();
        }
        return paramConsultaUtilitario;
    }

    public LazyDataModel<WsCmTransaccion> getLazyWsTransacciones() {
        return lazyWsTransacciones;
    }

    public void setLazyWsTransacciones(LazyDataModel<WsCmTransaccion> lazyWsTransacciones) {
        this.lazyWsTransacciones = lazyWsTransacciones;
    }

    public List<WsCmTransaccion> getRegistrosWsTransacciones() {
        return registrosWsTransacciones;
    }

    public void setRegistrosWsTransacciones(List<WsCmTransaccion> registrosWsTransacciones) {
        this.registrosWsTransacciones = registrosWsTransacciones;
    }

    public LazyDataModel<WsCmTransaccionDetalle> getLazyWsTransaccionDetalles() {
        return lazyWsTransaccionDetalles;
    }

    public void setLazyWsTransaccionDetalles(LazyDataModel<WsCmTransaccionDetalle> lazyWsTransaccionDetalles) {
        this.lazyWsTransaccionDetalles = lazyWsTransaccionDetalles;
    }

    public List<WsCmTransaccionDetalle> getRegistrosWsTransaccionDetalles() {
        return registrosWsTransaccionDetalles;
    }

    public void setRegistrosWsTransaccionDetalles(List<WsCmTransaccionDetalle> registrosWsTransaccionDetalles) {
        this.registrosWsTransaccionDetalles = registrosWsTransaccionDetalles;
    }

    public LazyDataModel<WsCmFactura> getLazyWsCmFacturas() {
        return lazyWsCmFacturas;
    }

    public void setLazyWsCmFacturas(LazyDataModel<WsCmFactura> lazyWsCmFacturas) {
        this.lazyWsCmFacturas = lazyWsCmFacturas;
    }

    public List<WsCmFactura> getRegistrosWsCmFacturas() {
        return registrosWsCmFacturas;
    }

    public void setRegistrosWsCmFacturas(List<WsCmFactura> registrosWsCmFacturas) {
        this.registrosWsCmFacturas = registrosWsCmFacturas;
    }

    public LazyDataModel<WsCmFacturaDetalle> getLazyWsCmFacturaDetalles() {
        return lazyWsCmFacturaDetalles;
    }

    public void setLazyWsCmFacturaDetalles(LazyDataModel<WsCmFacturaDetalle> lazyWsCmFacturaDetalles) {
        this.lazyWsCmFacturaDetalles = lazyWsCmFacturaDetalles;
    }

    public List<WsCmFacturaDetalle> getRegistrosWsCmFacturaDetalles() {
        return registrosWsCmFacturaDetalles;
    }

    public void setRegistrosWsCmFacturaDetalles(List<WsCmFacturaDetalle> registrosWsCmFacturaDetalles) {
        this.registrosWsCmFacturaDetalles = registrosWsCmFacturaDetalles;
    }

    public String getTextoAmpliado() {
        return textoAmpliado;
    }

    public void setTextoAmpliado(String textoAmpliado) {
        this.textoAmpliado = textoAmpliado;
    }

    public void setParamConsultaUtilitario(ParamConsulta paramConsultaUtilitario) {
        this.paramConsultaUtilitario = paramConsultaUtilitario;
    }

    public String getJsonContenido() {
        return jsonContenido;
    }

    public void setJsonContenido(String jsonContenido) {
        this.jsonContenido = jsonContenido;
    }

    public String getTituloJsonMensaje() {
        return tituloJsonMensaje;
    }

    public void setTituloJsonMensaje(String tituloJsonMensaje) {
        this.tituloJsonMensaje = tituloJsonMensaje;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public CmReintento getObjeto() {
        return objeto;
    }

    public void setObjeto(CmReintento objeto) {
        this.objeto = objeto;
    }

    public List<CmReintento> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CmReintento> registros) {
        this.registros = registros;
    }

    public LazyDataModel<CmReintento> getLazyReintentos() {
        return lazyReintentos;
    }

    public void setLazyReintentos(LazyDataModel<CmReintento> lazyReintentos) {
        this.lazyReintentos = lazyReintentos;
    }

    public LinkedHashMap<Integer, String> getHashEstadoSincronizacion() {
        return hashEstadoSincronizacion;
    }

    public void setHashEstadoSincronizacion(LinkedHashMap<Integer, String> hashEstadoSincronizacion) {
        this.hashEstadoSincronizacion = hashEstadoSincronizacion;
    }

    public String getEstadoSincronizacion(Integer id) {
        try {
            return hashEstadoSincronizacion.get(id);
        } catch (Exception e) {
            return "";
        }
    }

    public List<CmSincronizacionEncabezado> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(List<CmSincronizacionEncabezado> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }

    public CmRadicado getCmRadicado() {
        if (cmRadicado == null) {
            cmRadicado = new CmRadicado();
        }
        return cmRadicado;
    }

    public void setCmRadicado(CmRadicado cmRadicado) {
        this.cmRadicado = cmRadicado;
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void reiniciarPantalla() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(ACCION_LISTAR);
        getCmReintentosGlosasServicio().Accion(this);
    }

    public void refrescarWSTransacciones() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_TRANSACCIONES);
        getCmReintentosGlosasServicio().Accion(this);
    }

    public void refrescarWSTransaccionesDetalles() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_TRANSACCIONES_DETALLES);
        getCmReintentosGlosasServicio().Accion(this);
    }

    public void refrescarWSCmFacturaDetalles() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_CM_FACTURAS_DETALLES);
        getCmReintentosGlosasServicio().Accion(this);
    }

    public void refrescarWSFacturas() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_CM_FACTURAS);
        getCmReintentosGlosasServicio().Accion(this);
    }

    public void guardar() {
        procesoFinal();
    }

    public void guardarIncrementoMaximoIntentoPermitido(int idCmradicado) {
        getObjeto().setId(idCmradicado);
        super.setAccion(ACCION_ADICIONAL_3);
        getCmReintentosGlosasServicio().Accion(this);
        procesoFinal();
    }

    public void crear() {

    }

    public void reintentoGlosaServicio() {
        super.setAccion(ACCION_GUARDAR);
        getCmReintentosGlosasServicio().Accion(this);
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_VER:
                    switch (getDoAccion()) {
                        case ACCION_VER_TRANSACCIONES:
                            crearLog("Ver Transacciones ", getObjeto().toString());
                            break;
                        case ACCION_VER_TRANSACCIONES_DETALLES:
                            crearLog("Ver Transaccion Detalles ", getObjeto().toString());
                            break;
                        case ACCION_VER_CM_FACTURAS:
                            crearLog("Ver CmFacturas ", getObjeto().toString());
                            break;
                        case ACCION_VER_CM_FACTURAS_DETALLES:
                            crearLog("Ver CmFacturas Detalles ", getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                case Url.ACCION_EDITAR:
                case Url.ACCION_GUARDAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmReintentos");
                    crearLog(getObjeto().toString());
                    break;
                case ACCION_ADICIONAL_1:
                    crearLog("Reintentar Envio Sap", getObjeto().toString());
                    break;
            }
        }
        generarMensajes();
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_ENCABEZADOS_FACTURAS);
        getCmReintentosGlosasServicio().Accion(this);
        DataTable tablaVerDetalles = (DataTable) FacesContext.getCurrentInstance().
                getViewRoot().findComponent("frmVer:detalles");
        tablaVerDetalles.reset();
        PrimeFaces.current().resetInputs("frmVer");
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void verCmRadicado(int idcmRadicado) {
        getObjeto().setId(idcmRadicado);
        this.setCmRadicado(new CmRadicado());
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_CM_RADICADO);
        getCmReintentosGlosasServicio().Accion(this);
    }

    public void verWsTransacciones(int idCmRadicado) {
        verCmRadicado(idCmRadicado);
        getObjeto().setId(idCmRadicado);
        inicializarTablaTransacciones(idCmRadicado);
        limpiarFiltrosTablas("frmVerTransacciones:tablaTransacciones");
        PrimeFaces.current().resetInputs("frmVerTransacciones");
        PrimeFaces.current().ajax().update("frmVerTransacciones");
        PrimeFaces.current().executeScript("PF('dialogoVerTransacciones').show()");
        setDoAccion(ACCION_VER_TRANSACCIONES);
        procesoFinal();
    }

    public void verWsTransaccionDetalles(int idWsTransaccion) {
        inicializarTablaTransaccionDetalles(idWsTransaccion);
        limpiarFiltrosTablas("frmVerTransaccionDetalles:tablaTransaccionDetalles");
        PrimeFaces.current().resetInputs("frmVerTransaccionDetalles");
        PrimeFaces.current().ajax().update("frmVerTransaccionDetalles");
        PrimeFaces.current().executeScript("PF('dialogoVerTransaccionDetalles').show()");
        setDoAccion(ACCION_VER_TRANSACCIONES_DETALLES);
        procesoFinal();
    }

    public void verWsCmFacturas(int idCmRadicado) {
        verCmRadicado(idCmRadicado);
        inicializarTablaWsFacturas(idCmRadicado);
        limpiarFiltrosTablas("frmVerWsFacturas:tablaWsFacturas");
        PrimeFaces.current().resetInputs("frmVerWsFacturas");
        PrimeFaces.current().ajax().update("frmVerWsFacturas");
        PrimeFaces.current().executeScript("PF('dialogoVerWsFacturas').show()");
        super.setDoAccion(ACCION_VER_CM_FACTURAS);
        procesoFinal();
    }

    public void verWsCmFacturaDetalles(int idCmFactura) {
        inicializarTablaWsFacturaDetalles(idCmFactura);
        limpiarFiltrosTablas("frmVerWsFacturaDetalles:tablaWsFacturaDetalles");
        PrimeFaces.current().resetInputs("frmVerWsFacturaDetalles");
        PrimeFaces.current().ajax().update("frmVerWsFacturaDetalles");
        PrimeFaces.current().executeScript("PF('dialogoVerWsFacturaDetalles').show()");
        super.setDoAccion(ACCION_VER_CM_FACTURAS_DETALLES);
        procesoFinal();
    }

    public void inicializarTablaTransacciones(int idCmRadicado) {

        getParamConsulta(0).setParametroConsulta1(idCmRadicado);

        lazyWsTransacciones = new LazyDataModel<WsCmTransaccion>() {
            private List<WsCmTransaccion> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<WsCmTransaccion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(0).setPrimerRegistro(primerRegistro);
                getParamConsulta(0).setRegistrosPagina(registrosPagina);
                getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarWSTransacciones();
                lista = getRegistrosWsTransacciones();
                setRowCount(getParamConsulta(0).getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(WsCmTransaccion respuesta) {
                return respuesta.getId().toString();
            }

            @Override
            public WsCmTransaccion getRowData(String respuestaId) {
                Integer id = Integer.valueOf(respuestaId);
                for (WsCmTransaccion wsTransaccion : lista) {
                    if (id.equals(wsTransaccion.getId())) {
                        return wsTransaccion;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarTablaTransaccionDetalles(int idWsTransaccion) {

        getParamConsulta(1).setParametroConsulta1(idWsTransaccion);

        lazyWsTransaccionDetalles = new LazyDataModel<WsCmTransaccionDetalle>() {
            private List<WsCmTransaccionDetalle> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<WsCmTransaccionDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(1).setPrimerRegistro(primerRegistro);
                getParamConsulta(1).setRegistrosPagina(registrosPagina);
                getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(1).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarWSTransaccionesDetalles();
                lista = getRegistrosWsTransaccionDetalles();
                setRowCount(getParamConsulta(1).getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(WsCmTransaccionDetalle respuesta) {
                return respuesta.getId().toString();
            }

            @Override
            public WsCmTransaccionDetalle getRowData(String respuestaId) {
                Integer id = Integer.valueOf(respuestaId);
                for (WsCmTransaccionDetalle wsTransaccion : lista) {
                    if (id.equals(wsTransaccion.getId())) {
                        return wsTransaccion;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarTablaWsFacturas(int idCmRadicado) {

        getParamConsulta(2).setParametroConsulta1(idCmRadicado);

        lazyWsCmFacturas = new LazyDataModel<WsCmFactura>() {
            private List<WsCmFactura> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<WsCmFactura> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(2).setPrimerRegistro(primerRegistro);
                getParamConsulta(2).setRegistrosPagina(registrosPagina);
                getParamConsulta(2).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(2).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarWSFacturas();
                lista = getRegistrosWsCmFacturas();
                setRowCount(getParamConsulta(2).getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(WsCmFactura respuesta) {
                return respuesta.getId().toString();
            }

            @Override
            public WsCmFactura getRowData(String respuestaId) {
                Integer id = Integer.valueOf(respuestaId);
                for (WsCmFactura wsTransaccion : lista) {
                    if (id.equals(wsTransaccion.getId())) {
                        return wsTransaccion;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarTablaWsFacturaDetalles(int idWsCmFactura) {

        getParamConsulta(3).setParametroConsulta1(idWsCmFactura);

        lazyWsCmFacturaDetalles = new LazyDataModel<WsCmFacturaDetalle>() {
            private List<WsCmFacturaDetalle> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<WsCmFacturaDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(3).setPrimerRegistro(primerRegistro);
                getParamConsulta(3).setRegistrosPagina(registrosPagina);
                getParamConsulta(3).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(3).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarWSCmFacturaDetalles();
                lista = getRegistrosWsCmFacturaDetalles();
                setRowCount(getParamConsulta(3).getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(WsCmFacturaDetalle respuesta) {
                return respuesta.getId().toString();
            }

            @Override
            public WsCmFacturaDetalle getRowData(String respuestaId) {
                Integer id = Integer.valueOf(respuestaId);
                for (WsCmFacturaDetalle wsTransaccion : lista) {
                    if (id.equals(wsTransaccion.getId())) {
                        return wsTransaccion;
                    }
                }
                return null;
            }
        };
    }

    public String getEstado(String estado) {
        if (estado.equals("false")) {
            estado = "Pendiente";
        }
        return estado;
    }

    public void reintentarEnvioSap(Integer idCmRadicado) {
        getObjeto().setRadicado(idCmRadicado);
        super.setAccion(ACCION_ADICIONAL_1);
        getCmReintentosGlosasServicio().Accion(this);
        if (!isError()) {
            this.addMensaje("La solicitud de reintento se ha realizado");
        }
        PrimeFaces.current().ajax().update("frmReintentos");
        PrimeFaces.current().executeScript("PF('dialogoVer').hide()");
        procesoFinal();
    }

    public void verReintentos(Integer idCmRadicado) {
        getObjeto().setRadicado(idCmRadicado);
        getParamConsultaUtilitario().setParametroConsulta1(idCmRadicado);
        getParamConsultaUtilitario().setRegistrosPagina(30);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_BUSCAR_REINTENTOS);
        getCmReintentosGlosasServicio().Accion(this);
        if (!this.isError()) {
            PrimeFaces.current().ajax().update("frmVerReintento");
            PrimeFaces.current().executeScript("PF('dialogoVerReintento').show()");
        }
        procesoFinal();
    }

    public void verJson(String tituloJsonMensaje, String mensajeJson, Date fechaEnvio) {
        this.setTituloJsonMensaje(tituloJsonMensaje);
        this.setJsonContenido(mensajeJson);
        this.setFechaEnvio(fechaEnvio);
        PrimeFaces.current().resetInputs("frmVerJson");
        PrimeFaces.current().ajax().update("frmVerJson");
        PrimeFaces.current().executeScript("PF('dialogoVerJson').show()");
        crearLog("Ver " + tituloJsonMensaje, getObjeto().toString());
    }

    public void verPaquetesEnviados(Integer idSincronizacion) {
        getParamConsultaUtilitario().setParametroConsulta1(idSincronizacion);
        getParamConsultaUtilitario().setRegistrosPagina(30);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_BUSCAR_PAQUETES_REINTENTO);
        getCmReintentosGlosasServicio().Accion(this);
        if (!this.isError()) {
            PrimeFaces.current().ajax().update("frmVerPaquetes");
            PrimeFaces.current().executeScript("PF('dialogoVerPaquetes').show()");
        }
        procesoFinal();
    }

    public void clearViewScopedBeans() {
        // FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("cmEnviosGlosaBean");
    }

    private void limpiarFiltrosTablas(String nombrefrmTabla) {
        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(nombrefrmTabla);
        dataTable.reset();
    }

    public void verTextoAmpliado(String texto) {
        setTextoAmpliado(texto);
        PrimeFaces.current().resetInputs("frmTextoAmpliado");
        PrimeFaces.current().ajax().update("frmTextoAmpliado");
        PrimeFaces.current().executeScript("PF('dialogoTextoApliado').show();");
    }
}
