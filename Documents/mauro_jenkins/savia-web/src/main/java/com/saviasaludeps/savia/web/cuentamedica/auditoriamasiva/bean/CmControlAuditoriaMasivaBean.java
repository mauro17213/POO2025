/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.auditoriamasiva.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMasivaN;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmSincronizacionEncabezado;
import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.wstransaccion.WsCmFacturaDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.cuentamedica.auditoriamasiva.servicio.CmControlAuditoriaMasivaServicioIface;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;

import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author jperezn
 */
@ManagedBean
@ViewScoped
public class CmControlAuditoriaMasivaBean extends Url {

    public final static char ACCION_VER_SINCRONIZACION_ENCABEZADOS = 'a';
    public final static char ACCION_VER_SINCRONIZACION_DETALLES = 'b';
    public final static char ACCION_VER_REPORTE_ERRORES = 'c';
    public static final char ACCION_VER_WS_CM_FACTURAS = 'd';
    public static final char ACCION_VER_CM_FACTURAS_DETALLES = 'e';
    public static final char ACCION_VER_TIPO_FUENTE_DATOS = 'f';

    public final static int TIPO_FUENTE_DATOS_SINCRONIZACION_ENCABEZADO = 1;
    public final static int TIPO_FUENTE_DATOS_WS_FACTURAS = 2;

    private CmControlAuditoriaMasivaServicioIface cmControlAuditoriaMasivaServicio;
    private CmAuditoriaMasivaN objeto;
    private LazyDataModel<CmAuditoriaMasivaN> lazyCmAuditoriaMasiva;
    private List<CmAuditoriaMasivaN> registros;
    private List<CmSincronizacionEncabezado> registrosSincEncabezado;
    private List<CmSincronizacionDetalle> registrosSincDetalle;
    private ParamConsulta paramConsultaSincEncabezado;
    private ParamConsulta paramConsultaSincDetalle;
    private ParamConsulta paramConsultaUtilitario;
    private LazyDataModel<CmSincronizacionEncabezado> lazySincEncabezado;
    private LazyDataModel<CmSincronizacionDetalle> lazySincDetalle;
    private LazyDataModel<WsCmFactura> lazyWsCmFacturas;
    private List<WsCmFactura> registrosWsCmFacturas;
    private LazyDataModel<WsCmFacturaDetalle> lazyWsCmFacturaDetalles;
    private List<WsCmFacturaDetalle> registrosWsCmFacturaDetalles;
    private int tipoFuenteDatos;

    public CmControlAuditoriaMasivaBean() {
        this.objeto = new CmAuditoriaMasivaN();
        Modulo mod = super.validarModulo(Modulo.ID_CM_CONTROL_AUDITORIA_MASIVA);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            lazyCmAuditoriaMasiva = new LazyDataModel<CmAuditoriaMasivaN>() {
                private List<CmAuditoriaMasivaN> auditoriaFacturas;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CmAuditoriaMasivaN> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    auditoriaFacturas = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return auditoriaFacturas;
                }

                @Override
                public String getRowKey(CmAuditoriaMasivaN auditoriaFactura) {
                    return auditoriaFactura.getId().toString();
                }

                @Override
                public CmAuditoriaMasivaN getRowData(String auditoriaFacturaId) {
                    Integer id = Integer.valueOf(auditoriaFacturaId);
                    for (CmAuditoriaMasivaN auditoriaFactura : auditoriaFacturas) {
                        if (id.equals(auditoriaFactura.getId())) {
                            return auditoriaFactura;
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
        getCmControlAuditoriaMasivaServicio().cargaInicial(this);
        listar();
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCmControlAuditoriaMasivaServicio().Accion(this);
    }

    public void refrescarWSFacturas() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_WS_CM_FACTURAS);
        getCmControlAuditoriaMasivaServicio().Accion(this);
        procesoFinal();
    }

    public void refrescarWSCmFacturaDetalles() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_CM_FACTURAS_DETALLES);
        getCmControlAuditoriaMasivaServicio().Accion(this);
        procesoFinal();
    }

    private void limpiarFiltrosTablas(String nombrefrmTabla) {
        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(nombrefrmTabla);
        dataTable.reset();
    }

    public CmAuditoriaMasivaN getObjeto() {
        return objeto;
    }

    public void setObjeto(CmAuditoriaMasivaN objeto) {
        this.objeto = objeto;
    }

    public LazyDataModel<CmAuditoriaMasivaN> getLazyCmAuditoriaMasiva() {
        return lazyCmAuditoriaMasiva;
    }

    public void setLazyCmAuditoriaMasiva(LazyDataModel<CmAuditoriaMasivaN> lazyCmAuditoriaMasiva) {
        this.lazyCmAuditoriaMasiva = lazyCmAuditoriaMasiva;
    }

    public List<CmAuditoriaMasivaN> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CmAuditoriaMasivaN> registros) {
        this.registros = registros;
    }

    public CmControlAuditoriaMasivaServicioIface getCmControlAuditoriaMasivaServicio() {
        return cmControlAuditoriaMasivaServicio;
    }

    public void setCmControlAuditoriaMasivaServicio(CmControlAuditoriaMasivaServicioIface cmControlAuditoriaMasivaServicio) {
        this.cmControlAuditoriaMasivaServicio = cmControlAuditoriaMasivaServicio;
    }

    public ParamConsulta getParamConsultaSincEncabezado() {
        if (paramConsultaSincEncabezado == null) {
            paramConsultaSincEncabezado = new ParamConsulta();
        }
        return paramConsultaSincEncabezado;
    }

    public void setParamConsultaSincEncabezado(ParamConsulta paramConsultaSincEncabezado) {
        this.paramConsultaSincEncabezado = paramConsultaSincEncabezado;
    }

    public ParamConsulta getParamConsultaUtilitario() {
        if (paramConsultaUtilitario == null) {
            paramConsultaUtilitario = new ParamConsulta();
        }
        return paramConsultaUtilitario;
    }

    public void setParamConsultaUtilitario(ParamConsulta paramConsultaUtilitario) {
        this.paramConsultaUtilitario = paramConsultaUtilitario;
    }

    public List<CmSincronizacionEncabezado> getRegistrosSincEncabezado() {
        return registrosSincEncabezado;
    }

    public void setRegistrosSincEncabezado(List<CmSincronizacionEncabezado> registrosSincEncabezado) {
        this.registrosSincEncabezado = registrosSincEncabezado;
    }

    public List<CmSincronizacionDetalle> getRegistrosSincDetalle() {
        return registrosSincDetalle;
    }

    public void setRegistrosSincDetalle(List<CmSincronizacionDetalle> registrosSincDetalle) {
        this.registrosSincDetalle = registrosSincDetalle;
    }

    public LazyDataModel<CmSincronizacionEncabezado> getLazySincEncabezado() {
        return lazySincEncabezado;
    }

    public void setLazySincEncabezado(LazyDataModel<CmSincronizacionEncabezado> lazySincEncabezado) {
        this.lazySincEncabezado = lazySincEncabezado;
    }

    public LazyDataModel<CmSincronizacionDetalle> getLazySincDetalle() {
        return lazySincDetalle;
    }

    public void setLazySincDetalle(LazyDataModel<CmSincronizacionDetalle> lazySincDetalle) {
        this.lazySincDetalle = lazySincDetalle;
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

    public int getTipoFuenteDatos() {
        return tipoFuenteDatos;
    }

    public void setTipoFuenteDatos(int tipoFuenteDatos) {
        this.tipoFuenteDatos = tipoFuenteDatos;
    }

    public ParamConsulta getParamConsultaSincDetalle() {
        if (paramConsultaSincDetalle == null) {
            paramConsultaSincDetalle = new ParamConsulta();
        }
        return paramConsultaSincDetalle;
    }

    public void setParamConsultaSincDetalle(ParamConsulta paramConsultaSincDetalle) {
        this.paramConsultaSincDetalle = paramConsultaSincDetalle;
    }

    public void refrescarSincDetalle() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_SINCRONIZACION_DETALLES);
        getCmControlAuditoriaMasivaServicio().Accion(this);
    }

    public void refrescarSincEncabezado() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_SINCRONIZACION_ENCABEZADOS);
        getCmControlAuditoriaMasivaServicio().Accion(this);
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_CREAR:
                    break;
                case Url.ACCION_GUARDAR:
                    break;
                case Url.ACCION_MODIFICAR:
                    break;
                case Url.ACCION_BORRAR:
                    break;
                case Url.ACCION_LISTAR:
                    crearLog("Listar Cargas Masivas", getObjeto().toString());
                    break;
                case Url.ACCION_VER:
                    switch (this.getDoAccion()) {
                        case ACCION_VER_SINCRONIZACION_ENCABEZADOS:
                            crearLog("Ver Encabezados", getObjeto().toString());
                            break;
                        case ACCION_VER_SINCRONIZACION_DETALLES:
                            crearLog("Ver Encabezados Detalles", getObjeto().toString());
                            break;
                        case ACCION_VER_REPORTE_ERRORES:
                            crearLog("Generar Reporte Errores", getObjeto().toString());
                            break;
                        case ACCION_VER_WS_CM_FACTURAS:
                            crearLog("Ver Ws Facturas", getObjeto().toString());
                            break;
                        case ACCION_VER_CM_FACTURAS_DETALLES:
                            crearLog("Ver Ws Facturas Detalles", getObjeto().toString());
                            break;

                    }
                    break;
                case Url.ACCION_EDITAR:
                    break;
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_ADICIONAL_2:
                    break;
                case Url.ACCION_ADICIONAL_3:
                    break;
            }
        }
        generarMensajes();
    }

    public void inicializarTablaSincronizacionEncabezado() {
        lazySincEncabezado = new LazyDataModel<CmSincronizacionEncabezado>() {
            private List<CmSincronizacionEncabezado> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaSincEncabezado().getCantidadRegistros();
            }

            @Override
            public List<CmSincronizacionEncabezado> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaSincEncabezado().setPrimerRegistro(primerRegistro);
                getParamConsultaSincEncabezado().setRegistrosPagina(registrosPagina);
                getParamConsultaSincEncabezado().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaSincEncabezado().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarSincEncabezado();
                lista = getRegistrosSincEncabezado();
                setRowCount(getParamConsultaSincEncabezado().getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(CmSincronizacionEncabezado sincronizacionEncabezado) {
                return sincronizacionEncabezado.getId().toString();
            }

            @Override
            public CmSincronizacionEncabezado getRowData(String encabezadoId) {
                Integer id = Integer.valueOf(encabezadoId);
                for (CmSincronizacionEncabezado cmsincronizacionEn : lista) {
                    if (id.equals(cmsincronizacionEn.getId())) {
                        return cmsincronizacionEn;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarTablaSincronizacionDetalles() {
        lazySincDetalle = new LazyDataModel<CmSincronizacionDetalle>() {
            private List<CmSincronizacionDetalle> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaSincDetalle().getCantidadRegistros();
            }

            @Override
            public List<CmSincronizacionDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaSincDetalle().setPrimerRegistro(primerRegistro);
                getParamConsultaSincDetalle().setRegistrosPagina(registrosPagina);
                getParamConsultaSincDetalle().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaSincDetalle().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarSincDetalle();
                lista = getRegistrosSincDetalle();
                setRowCount(getParamConsultaSincDetalle().getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(CmSincronizacionDetalle sincronizacionD) {
                return sincronizacionD.getId().toString();
            }

            @Override
            public CmSincronizacionDetalle getRowData(String personaId) {
                Integer id = Integer.valueOf(personaId);
                for (CmSincronizacionDetalle sincronizacionD : lista) {
                    if (id.equals(sincronizacionD.getId())) {
                        return sincronizacionD;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarTablaWsFacturas(int idCmRadicado) {

        getParamConsulta(0).setParametroConsulta1(idCmRadicado);

        lazyWsCmFacturas = new LazyDataModel<WsCmFactura>() {
            private List<WsCmFactura> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<WsCmFactura> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(0).setPrimerRegistro(primerRegistro);
                getParamConsulta(0).setRegistrosPagina(registrosPagina);
                getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarWSFacturas();
                lista = getRegistrosWsCmFacturas();
                setRowCount(getParamConsulta(0).getCantidadRegistros());
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

        getParamConsulta(1).setParametroConsulta1(idWsCmFactura);

        lazyWsCmFacturaDetalles = new LazyDataModel<WsCmFacturaDetalle>() {
            private List<WsCmFacturaDetalle> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<WsCmFacturaDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsulta(1).setPrimerRegistro(primerRegistro);
                getParamConsulta(1).setRegistrosPagina(registrosPagina);
                getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsulta(1).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarWSCmFacturaDetalles();
                lista = getRegistrosWsCmFacturaDetalles();
                setRowCount(getParamConsulta(1).getCantidadRegistros());
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

    public String asignarColorFila(CmAuditoriaMasivaN auditoria) {
        String color = "";
        if (auditoria.isTieneFactuasPendientesProcesarEnSap()) {
            color = "red";
        }
        return color;
    }
    
    public String asignarStyleSegunEstado(CmAuditoriaMasivaN auditoria) {
        String style = "";
        if (auditoria.isTieneFactuasPendientesProcesarEnSap() ) {
            style = "pi pi-circle-on red_circle";
        }
        return style;
    }
       
    public void verSincronizaciones(CmAuditoriaMasivaN obj) {
        String idCmRadicadoStr = String.valueOf(obj.getCmRadicado());
        int idCmRadicadoInt = Integer.parseInt(idCmRadicadoStr);
        getObjeto().setCmRadicado(idCmRadicadoStr);
        setTipoFuenteDatos(0);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_TIPO_FUENTE_DATOS);
        getCmControlAuditoriaMasivaServicio().Accion(this);

        switch (getTipoFuenteDatos()) {
            case TIPO_FUENTE_DATOS_SINCRONIZACION_ENCABEZADO:
                verSincronizacionEncabezados(idCmRadicadoStr);
                break;
            case TIPO_FUENTE_DATOS_WS_FACTURAS:
                verWsCmFacturas(idCmRadicadoInt);
                break;
            default:
                addError("No se han creado registros para envio a sap de la carga de id : "+obj.getId());
                procesoFinal();
                break;
        }

    }

    public void verSincronizacionEncabezados(String idRadicado) {
        getObjeto().setCmRadicado(idRadicado);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_SINCRONIZACION_ENCABEZADOS);
        inicializarTablaSincronizacionEncabezado();
        getCmControlAuditoriaMasivaServicio().Accion(this);
        if (!this.isError()) {
            PrimeFaces.current().resetInputs("frmVerSincronizacionEncabezado:tablaRegistrosEncabezado");
            PrimeFaces.current().ajax().update("frmVerSincronizacionEncabezado");
            PrimeFaces.current().executeScript("PF('dialogoVerSincronizacionEncabezado').show()");
        }
        procesoFinal();
    }

    public void verSincronizacionDetalles(int idSincronizazionE) {
        ParamConsulta param = new ParamConsulta();
        param.setParametroConsulta1(idSincronizazionE);
        setParamConsultaSincDetalle(param);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_SINCRONIZACION_DETALLES);
        inicializarTablaSincronizacionDetalles();
        getCmControlAuditoriaMasivaServicio().Accion(this);
        if (!this.isError()) {
            PrimeFaces.current().ajax().update("frmVerCuentasContables");
            PrimeFaces.current().executeScript("PF('dialogoVerCuentasContables').show()");
        }
        procesoFinal();
    }

    public void verWsCmFacturas(int idCmRadicado) {
        inicializarTablaWsFacturas(idCmRadicado);
        limpiarFiltrosTablas("frmVerWsFacturas:tablaWsFacturas");
        PrimeFaces.current().resetInputs("frmVerWsFacturas");
        PrimeFaces.current().ajax().update("frmVerWsFacturas");
        PrimeFaces.current().executeScript("PF('dialogoVerWsFacturas').show()");
        procesoFinal();
    }

    public void verWsCmFacturaDetalles(int idCmFactura) {
        inicializarTablaWsFacturaDetalles(idCmFactura);
        limpiarFiltrosTablas("frmVerWsFacturaDetalles:tablaWsFacturaDetalles");
        PrimeFaces.current().resetInputs("frmVerWsFacturaDetalles");
        PrimeFaces.current().ajax().update("frmVerWsFacturaDetalles");
        PrimeFaces.current().executeScript("PF('dialogoVerWsFacturaDetalles').show()");
        procesoFinal();
    }

    public boolean hayFacturasPendientes(CmAuditoriaMasivaN auditoriaMasiva) {
        boolean habilitarGeneracion = false;
        if (auditoriaMasiva.isTieneFactuasPendientesProcesarEnSap()) {
            habilitarGeneracion = true;
        }
        return habilitarGeneracion;
    }

    public void generarReporteErrores(CmAuditoriaMasivaN auditoriaMasiva) {
        this.getObjeto().setId(auditoriaMasiva.getId());
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_REPORTE_ERRORES);
        getCmControlAuditoriaMasivaServicio().Accion(this);
        procesoFinal();
    }

}
