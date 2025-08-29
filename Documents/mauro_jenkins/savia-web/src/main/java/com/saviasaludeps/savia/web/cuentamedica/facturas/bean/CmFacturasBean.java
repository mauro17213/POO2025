/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.cuentamedica.facturas.bean;

import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAdjunto;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaAutorizacion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaCapitaDescuento;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaConceptoContable;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDiagnostico;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaMotivoGlosa;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFacturaTransaccion;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuesta;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmGlosaRespuestaDetalle;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_EDITAR;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import com.saviasaludeps.savia.web.cuentamedica.facturas.servicio.CmFacturasServicioIface;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Optional;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.SortMeta;

@ManagedBean
@ViewScoped
public class CmFacturasBean extends Url {

    public static final char ACCION_LISTAR_FACTURAS = 'a';
    public static final char ACCION_LISTAR_GLOSA_RESPUESTA = 'b';
    public static final char ACCION_LISTAR_DETALLES = 'c';
    public static final char ACCION_LISTAR_RESPUESTA_DETALLES = 'd';

    public static final char ACCION_LISTAR_DIAGNOSTICOS = 'e';
    public static final char ACCION_LISTAR_CONCEPTOS = 'f';
    public static final char ACCION_LISTAR_MOTIVOS_GLOSA = 'g';
    public static final char ACCION_LISTAR_AUTORIZACIONES = 'h';
    public static final char ACCION_LISTAR_FACTURA_TRANSACCIONES = 'i';

    public static final char ACCION_VER_FACTURA = 'j';
    public static final char ACCION_VER_DETALLE = 'k';
    public static final char ACCION_VER_AUDITORIA_SERVICIOS = 'l';
    public static final char ACCION_VER_TRANSACCIONES = 'm';
    public static final char ACCION_LISTAR_ADJUNTOS_CMDETALLES = 'n';
    public static final char ACCION_LISTAR_ADJUNTOS_FACTURA = 'o';
    public static final char ACCION_LISTAR_DESCUENTO_CAPITA= 'p';

    private CmFacturasServicioIface cmFacturasServicio;
    private CmFactura objeto;
    private LazyDataModel<CmFactura> lazyFacturas;
    private LazyDataModel<CmDetalle> lazyDetalles;
    private LazyDataModel<CmGlosaRespuesta> lazyGlosaRespuesta;
    private LazyDataModel<CmGlosaRespuestaDetalle> lazyGlosaRespuestaDetalle;
    private LazyDataModel<CmFacturaTransaccion> lazyFacturasTransaccion;
    private List<CmFactura> registros;
    private List<CmDetalle> registrosDetalles;
    private List<CmFacturaTransaccion> registrosFacturasTransaccion;
    private List<CmGlosaRespuesta> registrosGlosaRespuesta;
    private List<CmGlosaRespuestaDetalle> registrosGlosaRespuestaDetalle;
    private List<CmAuditoriaMotivoGlosa> registrosAuditoriaMotivoGlosa = new ArrayList<>();
   
    private List<CmAuditoriaConceptoContable> registrosConceptoContable = new ArrayList<>();
    private List<CmAuditoriaCapitaDescuento> registrosDescuentoCapita = new ArrayList<>();
    private List<CmAuditoriaDiagnostico> registrosAuditoriaDiagnostico = new ArrayList<>();
  
    private List<CmAuditoriaAutorizacion> registrosAuditoriaAutorizacion = new ArrayList<>();
    private List<CmAuditoriaAdjunto> registrosAuditoriaAdjutoCmDetalle = new ArrayList<>();
    private List<CmAuditoriaAdjunto> registrosAdjuntosFactura = new ArrayList<>();
   
    private String textoAmpliado;
    private CmDetalle detalleServicioActual = new CmDetalle();
    private ParamConsulta paramConsultaServicios;
    private ParamConsulta paramConsultaRespuestaGlosa;
    private ParamConsulta paramConsultaTransacciones;

    public CmFacturasBean() {
        this.objeto = new CmFactura();
        Modulo mod = super.validarModulo(Modulo.ID_CM_FACTURA_HISTORICO);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);

            lazyFacturas = new LazyDataModel<CmFactura>() {
                private List<CmFactura> listaFacturas;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CmFactura> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    listaFacturas = getRegistros();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return listaFacturas;
                }

                @Override
                public String getRowKey(CmFactura factura) {
                    return factura.getId().toString();
                }

                @Override
                public CmFactura getRowData(String facturaId) {
                    Integer id = Integer.valueOf(facturaId);
                    for (CmFactura factura : listaFacturas) {
                        if (id.equals(factura.getId())) {
                            return factura;
                        }
                    }
                    return null;
                }

            };
        }
    }

    @PostConstruct
    public void postConstruct() {
        listar();
    }

    public CmFactura getObjeto() {
        return objeto;
    }

    public void setObjeto(CmFactura objeto) {
        this.objeto = objeto;
    }

    public List<CmFactura> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CmFactura> registros) {
        this.registros = registros;
    }

    public LazyDataModel<CmFactura> getLazyFacturas() {
        return lazyFacturas;
    }

    public void setLazyFacturas(LazyDataModel<CmFactura> lazyFacturas) {
        this.lazyFacturas = lazyFacturas;
    }

    public CmFacturasServicioIface getCmFacturasServicio() {
        return cmFacturasServicio;
    }

    public void setCmFacturasServicio(CmFacturasServicioIface cmFacturasServicio) {
        this.cmFacturasServicio = cmFacturasServicio;
    }

    public LazyDataModel<CmDetalle> getLazyDetalles() {
        return lazyDetalles;
    }

    public void setLazyDetalles(LazyDataModel<CmDetalle> lazyDetalles) {
        this.lazyDetalles = lazyDetalles;
    }

    public LazyDataModel<CmGlosaRespuesta> getLazyGlosaRespuesta() {
        return lazyGlosaRespuesta;
    }

    public void setLazyGlosaRespuesta(LazyDataModel<CmGlosaRespuesta> lazyGlosaRespuesta) {
        this.lazyGlosaRespuesta = lazyGlosaRespuesta;
    }

    public LazyDataModel<CmGlosaRespuestaDetalle> getLazyGlosaRespuestaDetalle() {
        return lazyGlosaRespuestaDetalle;
    }

    public void setLazyGlosaRespuestaDetalle(LazyDataModel<CmGlosaRespuestaDetalle> lazyGlosaRespuestaDetalle) {
        this.lazyGlosaRespuestaDetalle = lazyGlosaRespuestaDetalle;
    }

    public List<CmDetalle> getRegistrosDetalles() {
        return registrosDetalles;
    }

    public void setRegistrosDetalles(List<CmDetalle> registrosDetalles) {
        this.registrosDetalles = registrosDetalles;
    }

    public List<CmGlosaRespuesta> getRegistrosGlosaRespuesta() {
        return registrosGlosaRespuesta;
    }

    public void setRegistrosGlosaRespuesta(List<CmGlosaRespuesta> registrosGlosaRespuesta) {
        this.registrosGlosaRespuesta = registrosGlosaRespuesta;
    }

    public List<CmGlosaRespuestaDetalle> getRegistrosGlosaRespuestaDetalle() {
        return registrosGlosaRespuestaDetalle;
    }

    public void setRegistrosGlosaRespuestaDetalle(List<CmGlosaRespuestaDetalle> registrosGlosaRespuestaDetalle) {
        this.registrosGlosaRespuestaDetalle = registrosGlosaRespuestaDetalle;
    }

    public String getTextoAmpliado() {
        return textoAmpliado;
    }

    public void setTextoAmpliado(String textoAmpliado) {
        this.textoAmpliado = textoAmpliado;
    }

    public CmDetalle getDetalleServicioActual() {
        if (detalleServicioActual == null) {
            detalleServicioActual = new CmDetalle();
        }
        return detalleServicioActual;
    }

    public void setDetalleServicioActual(CmDetalle detalleServicioActual) {
        this.detalleServicioActual = detalleServicioActual;
    }

    public ParamConsulta getParamConsultaServicios() {
        if (paramConsultaServicios == null) {
            paramConsultaServicios = new ParamConsulta();
        }
        return paramConsultaServicios;
    }

    public void setParamConsultaServicios(ParamConsulta paramConsultaServicios) {
        this.paramConsultaServicios = paramConsultaServicios;
    }

    public List<CmAuditoriaMotivoGlosa> getRegistrosAuditoriaMotivoGlosa() {
        return registrosAuditoriaMotivoGlosa;
    }

    public ParamConsulta getParamConsultaRespuestaGlosa() {
        if (paramConsultaRespuestaGlosa == null) {
            paramConsultaRespuestaGlosa = new ParamConsulta();
        }
        return paramConsultaRespuestaGlosa;
    }

    public void setParamConsultaRespuestaGlosa(ParamConsulta paramConsultaRespuestaGlosa) {
        this.paramConsultaRespuestaGlosa = paramConsultaRespuestaGlosa;
    }

    public ParamConsulta getParamConsultaTransacciones() {
        if (paramConsultaTransacciones == null) {
            paramConsultaTransacciones = new ParamConsulta();
        }
        return paramConsultaTransacciones;
    }

    public void setParamConsultaTransacciones(ParamConsulta paramConsultaTransacciones) {
        this.paramConsultaTransacciones = paramConsultaTransacciones;
    }

    public void setRegistrosAuditoriaMotivoGlosa(List<CmAuditoriaMotivoGlosa> registrosAuditoriaMotivoGlosa) {
        this.registrosAuditoriaMotivoGlosa = registrosAuditoriaMotivoGlosa;
    }

    public List<CmAuditoriaConceptoContable> getRegistrosConceptoContable() {
        return registrosConceptoContable;
    }

    public void setRegistrosConceptoContable(List<CmAuditoriaConceptoContable> registrosConceptoContable) {
        this.registrosConceptoContable = registrosConceptoContable;
    }

    public List<CmAuditoriaAdjunto> getRegistrosAdjuntosFactura() {
        return registrosAdjuntosFactura;
    }

    public void setRegistrosAdjuntosFactura(List<CmAuditoriaAdjunto> registrosAdjuntosFactura) {
        this.registrosAdjuntosFactura = registrosAdjuntosFactura;
    }

    public List<CmAuditoriaDiagnostico> getRegistrosAuditoriaDiagnostico() {
        return registrosAuditoriaDiagnostico;
    }

    public void setRegistrosAuditoriaDiagnostico(List<CmAuditoriaDiagnostico> registrosAuditoriaDiagnostico) {
        this.registrosAuditoriaDiagnostico = registrosAuditoriaDiagnostico;
    }

    public List<CmAuditoriaCapitaDescuento> getRegistrosDescuentoCapita() {
        return registrosDescuentoCapita;
    }

    public void setRegistrosDescuentoCapita(List<CmAuditoriaCapitaDescuento> registrosDescuentoCapita) {
        this.registrosDescuentoCapita = registrosDescuentoCapita;
    }

    public List<CmAuditoriaAutorizacion> getRegistrosAuditoriaAutorizacion() {
        return registrosAuditoriaAutorizacion;
    }

    public void setRegistrosAuditoriaAutorizacion(List<CmAuditoriaAutorizacion> registrosAuditoriaAutorizacion) {
        this.registrosAuditoriaAutorizacion = registrosAuditoriaAutorizacion;
    }

    public List<CmAuditoriaAdjunto> getRegistrosAuditoriaAdjutoCmDetalle() {
        return registrosAuditoriaAdjutoCmDetalle;
    }

    public void setRegistrosAuditoriaAdjutoCmDetalle(List<CmAuditoriaAdjunto> registrosAuditoriaAdjutoCmDetalle) {
        this.registrosAuditoriaAdjutoCmDetalle = registrosAuditoriaAdjutoCmDetalle;
    }

    public LazyDataModel<CmFacturaTransaccion> getLazyFacturasTransaccion() {
        return lazyFacturasTransaccion;
    }

    public void setLazyFacturasTransaccion(LazyDataModel<CmFacturaTransaccion> lazyFacturasTransaccion) {
        this.lazyFacturasTransaccion = lazyFacturasTransaccion;
    }

    public List<CmFacturaTransaccion> getRegistrosFacturasTransaccion() {
        return registrosFacturasTransaccion;
    }

    public void setRegistrosFacturasTransaccion(List<CmFacturaTransaccion> registrosFacturasTransaccion) {
        this.registrosFacturasTransaccion = registrosFacturasTransaccion;
    }

    public void listar() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR_FACTURAS);
        getCmFacturasServicio().Accion(this);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR_FACTURAS);
        getCmFacturasServicio().Accion(this);
    }

    public void refrescarGlosaRespuesta() {
        super.setAccion(ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR_GLOSA_RESPUESTA);
        getCmFacturasServicio().Accion(this);
        procesoFinal();
    }

    public void refrescarDetalles() {
        super.setAccion(ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR_DETALLES);
        getCmFacturasServicio().Accion(this);
    }

    public void refrescarGlosaRespuestaDetalle() {
        super.setAccion(ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR_RESPUESTA_DETALLES);
        getCmFacturasServicio().Accion(this);
        procesoFinal();
    }

    public void refrescarFacturaTransacciones() {
        super.setAccion(ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR_FACTURA_TRANSACCIONES);
        getCmFacturasServicio().Accion(this);
    }

    public void listarDiagnosticos() {
        super.setAccion(ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR_DIAGNOSTICOS);
        getCmFacturasServicio().Accion(this);
    }

    public void listarConceptosContables() {
        super.setAccion(ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR_CONCEPTOS);
        getCmFacturasServicio().Accion(this);
    }

    public void listarMotivosGlosa() {
        super.setAccion(ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR_MOTIVOS_GLOSA);
        getCmFacturasServicio().Accion(this);
    }

    public void listarAutorizaciones() {
        super.setAccion(ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR_AUTORIZACIONES);
        getCmFacturasServicio().Accion(this);
    }

    public void listarAdjuntosCmDetalles() {
        super.setAccion(ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR_ADJUNTOS_CMDETALLES);
        getCmFacturasServicio().Accion(this);
    }

    public void listarAdjuntosFactura() {
        super.setAccion(ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR_ADJUNTOS_FACTURA);
        getCmFacturasServicio().Accion(this);
    }
    
    public void listarDescuentoCapita() {
        super.setAccion(ACCION_LISTAR);
        super.setDoAccion(ACCION_LISTAR_DESCUENTO_CAPITA);
        getCmFacturasServicio().Accion(this);
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER_FACTURA);
        getCmFacturasServicio().Accion(this);
        listarAdjuntosFactura();
        inicializarTableDetalles(id);
        inicializarTablaGlosaRespuesta(id);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().ajax().update("scrollPanelVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void verServicioAuditado(int idServicio) {
        getDetalleServicioActual().setId(idServicio);
        verDetalle();
        listarConceptosContables();
        listarDiagnosticos();
        listarMotivosGlosa();
        listarAutorizaciones();
        listarAdjuntosCmDetalles();
        listarDescuentoCapita();
        PrimeFaces.current().resetInputs("frmVerAuditoriaServicio");
        PrimeFaces.current().ajax().update("frmVerAuditoriaServicio");
        PrimeFaces.current().executeScript("PF('dialogoVerAuditoriaServicio').show()");
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_AUDITORIA_SERVICIOS);
        procesoFinal();
    }

    public void verDetalle() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_DETALLE);
        getCmFacturasServicio().Accion(this);
    }

    public void verFacturaTransacciones(int idFactura) {
        getObjeto().setId(idFactura);
        inicializarTablaFacturaTransacciones(idFactura);
        PrimeFaces.current().ajax().update("frmVerTransacciones");
        PrimeFaces.current().executeScript("PF('dialogoVerTransacciones').show()");
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(ACCION_VER_TRANSACCIONES);
        procesoFinal();
    }
    
    public void verNota(CmFactura factura) {
       this.setObjeto(factura);
        PrimeFaces.current().ajax().update("frmVerNota");
        PrimeFaces.current().executeScript("PF('dialogoVerNota').show()");
    }

    public void inicializarTableDetalles(Integer idFactura) {
        this.setParamConsultaServicios(new ParamConsulta());
        lazyDetalles = new LazyDataModel<CmDetalle>() {
            private List<CmDetalle> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaServicios().getCantidadRegistros();
            }

            @Override
            public List<CmDetalle> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaServicios().setPrimerRegistro(primerRegistro);
                getParamConsultaServicios().setRegistrosPagina(registrosPagina);
                getParamConsultaServicios().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaServicios().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarDetalles();
                lista = getRegistrosDetalles();
                setRowCount(getParamConsultaServicios().getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(CmDetalle caso) {
                return caso.getId().toString();
            }

            @Override
            public CmDetalle getRowData(String personaId) {
                Integer id = Integer.valueOf(personaId);
                for (CmDetalle cmDetalle : lista) {
                    if (id.equals(cmDetalle.getId())) {
                        return cmDetalle;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarTablaGlosaRespuesta(int idGlosa) {
        this.setParamConsultaRespuestaGlosa(new ParamConsulta());
        this.getParamConsultaRespuestaGlosa().setParametroConsulta1(idGlosa);
        lazyGlosaRespuesta = new LazyDataModel<CmGlosaRespuesta>() {
            private List<CmGlosaRespuesta> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaRespuestaGlosa().getCantidadRegistros();
            }

            @Override
            public List<CmGlosaRespuesta> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaRespuestaGlosa().setPrimerRegistro(primerRegistro);
                getParamConsultaRespuestaGlosa().setRegistrosPagina(registrosPagina);
                getParamConsultaRespuestaGlosa().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaRespuestaGlosa().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarGlosaRespuesta();
                lista = getRegistrosGlosaRespuesta();
                setRowCount(getParamConsultaRespuestaGlosa().getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(CmGlosaRespuesta respuesta) {
                return respuesta.getId().toString();
            }

            @Override
            public CmGlosaRespuesta getRowData(String respuestaId) {
                Integer id = Integer.valueOf(respuestaId);
                for (CmGlosaRespuesta cmRespuesta : lista) {
                    if (id.equals(cmRespuesta.getId())) {
                        return cmRespuesta;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarTablaFacturaTransacciones(Integer idFactura) {
        this.setParamConsultaTransacciones(new ParamConsulta());
        this.getParamConsultaTransacciones().setParametroConsulta1(idFactura);
        lazyFacturasTransaccion = new LazyDataModel<CmFacturaTransaccion>() {
            private List<CmFacturaTransaccion> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaTransacciones().getCantidadRegistros();
            }

            @Override
            public List<CmFacturaTransaccion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaTransacciones().setPrimerRegistro(primerRegistro);
                getParamConsultaTransacciones().setRegistrosPagina(registrosPagina);
                getParamConsultaTransacciones().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaTransacciones().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarFacturaTransacciones();
                lista = getRegistrosFacturasTransaccion();
                setRowCount(getParamConsultaTransacciones().getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(CmFacturaTransaccion facturaTransaccion) {
                return facturaTransaccion.getId().toString();
            }

            @Override
            public CmFacturaTransaccion getRowData(String facturaTransaccionId) {
                Integer id = Integer.valueOf(facturaTransaccionId);
                for (CmFacturaTransaccion facturaTransaccion : lista) {
                    if (id.equals(facturaTransaccion.getId())) {
                        return facturaTransaccion;
                    }
                }
                return null;
            }
        };
    }

    public void verTextoAmpliado(String texto) {
        setTextoAmpliado(texto);
        PrimeFaces.current().resetInputs("frmTextoAmpliado");
        PrimeFaces.current().ajax().update("frmTextoAmpliado");
        PrimeFaces.current().executeScript("PF('dialogoTextoApliado').show();");
    }

    public void crear() {
        super.setAccion(ACCION_CREAR);
        procesoFinal();
    }

    public void guardar() {
        super.setAccion(ACCION_GUARDAR);
        procesoFinal();
    }

    public void editar(int id) {
        super.setAccion(ACCION_EDITAR);
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        procesoFinal();
    }

    public void borrar(int id) {
        super.setAccion(ACCION_BORRAR);
        procesoFinal();
    }

    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_VER:
                switch (getDoAccion()) {
                    case ACCION_VER_FACTURA:
                        crearLog(getObjeto().toString());
                        break;
                    case ACCION_VER_AUDITORIA_SERVICIOS:
                        crearLog("Ver Auditoria Servicio", getObjeto().toString());
                        break;
                    case ACCION_VER_TRANSACCIONES:
                        crearLog("Ver Transacciones", getObjeto().toString());
                        break;
                }
                break;
            case Url.ACCION_CREAR:
            case Url.ACCION_EDITAR:
                crearLog(getObjeto().toString());
                break;
            case Url.ACCION_GUARDAR:
            case Url.ACCION_MODIFICAR:
            case Url.ACCION_BORRAR:
                crearLog(getObjeto().toString());
                PrimeFaces.current().ajax().update("frmDiagnosticos");
                break;
            case Url.ACCION_LISTAR:
                switch (getDoAccion()) {
                    case ACCION_LISTAR_FACTURAS:
                        crearLog(getObjeto().toString());
                        break;
                }
                break;
            case Url.ACCION_ADICIONAL_2:
                PrimeFaces.current().ajax().update("frmDiagnosticos");
                break;
        }
        generarMensajes();
    }

    public boolean validarUsuarioMomento(String nombreUsuario) {
        String usuario = Optional.ofNullable(nombreUsuario).orElse("");
        return usuario.length() > 1;
    }

    public void descargarAdjunto(CmAuditoriaAdjunto adj) {
        String rutaCompleta = adj.getArchivoRuta() + adj.getArchivoNombre();
        try {
            File file = new File(rutaCompleta);
            byte[] exportContent = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            fis.read(exportContent);
            int i = rutaCompleta.lastIndexOf(".");
            String ext = rutaCompleta.substring(i, rutaCompleta.length());
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.responseReset();
            ec.setResponseContentLength(exportContent.length);
            String attachmentName = "attachment; filename=\"" + adj.getArchivoNombre() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".doc")) {
                ec.setResponseContentType("application/doc");
            } else if (ext.equalsIgnoreCase(".docx")) {
                ec.setResponseContentType("application/docx");
            } else if (ext.equalsIgnoreCase(".xls")) {
                ec.setResponseContentType("application/xls");
            } else if (ext.equalsIgnoreCase(".xlsx")) {
                ec.setResponseContentType("application/xlsx");
            } else if (ext.equalsIgnoreCase(".pdf")) {
                ec.setResponseContentType("application/pdf");
            } else if (ext.equalsIgnoreCase(".jpg")) {
                ec.setResponseContentType("image/jpg");
            } else if (ext.equalsIgnoreCase(".jpeg")) {
                ec.setResponseContentType("image/jpeg");
            } else if (ext.equalsIgnoreCase(".png")) {
                ec.setResponseContentType("image/png");
            } else {
                throw new Exception();
            }
            try ( OutputStream output = ec.getResponseOutputStream()) {
                output.write(exportContent);
            }
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo:" + e.getMessage());
            generarMensajes();
        }
    }

}
