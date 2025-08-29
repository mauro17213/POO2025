/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.autorizacion.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Rol;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.anticipo.AntAnticipoAdjunto;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo3Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Entrega;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Impresion;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Item;
import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Reporte;
import com.saviasaludeps.savia.dominio.autorizacion.AuItemBitacora;
import com.saviasaludeps.savia.dominio.autorizacion.AuSolicitudAdjunto;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import com.saviasaludeps.savia.web.autorizacion.servicio.AuAutorizacionServicioIface;
import com.saviasaludeps.savia.web.autorizacion.servicio.AuAutorizacionServicioImpl;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuConstantes;
import com.saviasaludeps.savia.web.autorizacion.utilidades.AuReporte;
import com.saviasaludeps.savia.web.gestion.atencion.utilidades.Utilidad;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author Stiven Giraldo
 */
@Named
@ViewScoped
public class AuAutorizacionBean extends Url {

    @Autowired
    private AuAutorizacionServicioIface auAutorizacionServicio;
    private AuAnexo4 objeto;
    private List<AuAnexo4> registros;
    private LazyDataModel<AuAnexo4> lazyAuAnexo4;
    private List<AuAnexo4Item> listaAuAnexo4Items;
    private HashMap<Integer, Maestro> hashTipoDocumento;
    private HashMap<Integer, Ubicacion> hashUbicaciones;
    private List<Maestro> listaEstadosItem;
    private List<Maestro> listaCausaNoEntrega;
    private HashMap<Integer, Maestro> hashCausaNoEntrega;
    private List<AuSolicitudAdjunto> listaAdjuntosCotizacion;
    private List<AntAnticipoAdjunto> antAnticipoAdjuntosList;

    private List<Rol> listaRoles;
    private List<Integer> seleccionRoles = new ArrayList();

    private List<Maestro> maestros;
    private List<AuItemBitacora> listaBitacoras;

    private List<Maestro> listaMotivos;
    private HashMap<Integer, Maestro> hashMotivos;
    private List<Maestro> listaMotivosA4;
    private HashMap<Integer, Maestro> hashMotivosA4;
    private int idMotivo;

    //Auxiliares
    private Date fechaMaximaCalendario;
    //private String rutaAnexo2;
    private String rutaAnexo4;
    private String rutaAnexo3;
    private String observacionGenerica;
    private BigDecimal valorTotalHistorico;

    private AuAnexo4Entrega objetoEntrega;
    private boolean tipoEntrega;
    private AuAnexo4Item objetoItem;
    private Maestro estadoActivo;
    private Date fechaInicio;
    private Date fechaFin;

    private List<AuAnexo4Impresion> listaImpresiones;
    private AuAnexo4Reporte objetoReporte;
    private List<AuAnexo4Reporte> listaReportes;
    private List<CntPrestadorSede> listaIps;
    private LazyDataModel<CntPrestadorSede> lazyIps;
    private List<Ubicacion> listaCiudades;
    private List<Maestro> listaTipoDocumento;
    private String prestadorReporteExcel;
    private String headerDialogVer;
    private String reporteTicketString;

    public AuAutorizacionBean() {
        this.objeto = new AuAnexo4();
        Modulo _mod = super.validarModulo(Modulo.ID_AUTORIZACIONES_AUTORIZACIONES);
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        super.addListaParamConsultas(new ParamConsulta());
        if (super.getConexion().getEmpresa().isAdministradora()) {
            super.getParamConsulta(0).setEmpresaId(null);
        } else {
            super.getParamConsulta(0).setEmpresaId(super.getConexion().getEmpresa().getId());
        }
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyAuAnexo4 = new LazyDataModel<AuAnexo4>() {

                private List<AuAnexo4> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<AuAnexo4> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(0).setPrimerRegistro(primerRegistro);
                    getParamConsulta(0).setRegistrosPagina(registrosPagina);
                    getParamConsulta(0).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(0).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    lista = getRegistros();
                    setRowCount(getParamConsulta(0).getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(AuAnexo4 objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public AuAnexo4 getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (AuAnexo4 objeto : lista) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }
            };
            //Lazy para carga ips
            lazyIps = new LazyDataModel<CntPrestadorSede>() {
                private List<CntPrestadorSede> listaIps;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CntPrestadorSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta(1).setEmpresaId(getConexion().getEmpresa().getId());
                    getParamConsulta(1).setPrimerRegistro(primerRegistro);
                    getParamConsulta(1).setRegistrosPagina(registrosPagina);
                    getParamConsulta(1).setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta(1).setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarIps();
                    listaIps = getListaIps();
                    setRowCount(getParamConsulta(1).getCantidadRegistros());
                    return listaIps;
                }

                @Override
                public String getRowKey(CntPrestadorSede ips) {
                    return ips.getId().toString();
                }

                @Override
                public CntPrestadorSede getRowData(String ipsId) {
                    Integer id = Integer.valueOf(ipsId);
                    for (CntPrestadorSede ips : listaIps) {
                        if (id.equals(ips.getId())) {
                            return ips;
                        }
                    }
                    return null;
                }
            };
        }
    }

    public void ver(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        getAuAutorizacionServicio().Accion(this);
        procesoFinal();
    }

    @PostConstruct
    public void postConstruct() {
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        getAuAutorizacionServicio().cargasInicial(this);
        listar();
    }

    public AuAutorizacionServicioIface getAuAutorizacionServicio() {
        if (auAutorizacionServicio == null) {
            auAutorizacionServicio = new AuAutorizacionServicioImpl();
        }
        return auAutorizacionServicio;
    }

    public void setAuAutorizacionServicio(AuAutorizacionServicioIface auAutorizacionServicio) {
        this.auAutorizacionServicio = auAutorizacionServicio;
    }

    public AuAnexo4 getObjeto() {
        return objeto;
    }

    public void setObjeto(AuAnexo4 objeto) {
        this.objeto = objeto;
    }

    public List<AuAnexo4> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AuAnexo4> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AuAnexo4> getLazyAuAnexo4() {
        return lazyAuAnexo4;
    }

    public void setLazyAuAnexo4(LazyDataModel<AuAnexo4> lazyAuAnexo4) {
        this.lazyAuAnexo4 = lazyAuAnexo4;
    }

    public List<Rol> getListaRoles() {
        return listaRoles;
    }

    public void setListaRoles(List<Rol> listaRoles) {
        this.listaRoles = listaRoles;
    }

    public List<Integer> getSeleccionRoles() {
        return seleccionRoles;
    }

    public void setSeleccionRoles(List<Integer> seleccionRoles) {
        this.seleccionRoles = seleccionRoles;
    }

    public List<Maestro> getMaestros() {
        return maestros;
    }

    public void setMaestros(List<Maestro> maestros) {
        this.maestros = maestros;
    }

    public Date getFechaMaximaCalendario() {
        return fechaMaximaCalendario;
    }

    public void setFechaMaximaCalendario(Date fechaMaximaCalendario) {
        this.fechaMaximaCalendario = fechaMaximaCalendario;
    }

    /*public String getRutaAnexo2() {
    return rutaAnexo2;
    }
    public void setRutaAnexo2(String rutaAnexo2) {
    this.rutaAnexo2 = rutaAnexo2;
    }*/
    public String getRutaAnexo4() {
        return rutaAnexo4;
    }

    public void setRutaAnexo4(String rutaAnexo4) {
        this.rutaAnexo4 = rutaAnexo4;
    }

    public String getRutaAnexo3() {
        return rutaAnexo3;
    }

    public void setRutaAnexo3(String rutaAnexo3) {
        this.rutaAnexo3 = rutaAnexo3;
    }

    public AuAnexo4Entrega getObjetoEntrega() {
        return objetoEntrega;
    }

    public void setObjetoEntrega(AuAnexo4Entrega objetoEntrega) {
        this.objetoEntrega = objetoEntrega;
    }

    public boolean isTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(boolean tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public AuAnexo4Item getObjetoItem() {
        return objetoItem;
    }

    public void setObjetoItem(AuAnexo4Item objetoItem) {
        this.objetoItem = objetoItem;
    }

    public Maestro getEstadoActivo() {
        return estadoActivo;
    }

    public void setEstadoActivo(Maestro estadoActivo) {
        this.estadoActivo = estadoActivo;
    }

    public List<AuAnexo4Impresion> getListaImpresiones() {
        return listaImpresiones;
    }

    public void setListaImpresiones(List<AuAnexo4Impresion> listaImpresiones) {
        this.listaImpresiones = listaImpresiones;
    }

    public AuAnexo4Reporte getObjetoReporte() {
        return objetoReporte;
    }

    public void setObjetoReporte(AuAnexo4Reporte objetoReporte) {
        this.objetoReporte = objetoReporte;
    }

    public List<AuAnexo4Reporte> getListaReportes() {
        return listaReportes;
    }

    public void setListaReportes(List<AuAnexo4Reporte> listaReportes) {
        this.listaReportes = listaReportes;
    }

    public List<CntPrestadorSede> getListaIps() {
        return listaIps;
    }

    public void setListaIps(List<CntPrestadorSede> listaIps) {
        this.listaIps = listaIps;
    }

    public LazyDataModel<CntPrestadorSede> getLazyIps() {
        return lazyIps;
    }

    public void setLazyIps(LazyDataModel<CntPrestadorSede> lazyIps) {
        this.lazyIps = lazyIps;
    }

    public List<Ubicacion> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(List<Ubicacion> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    public BigDecimal getValorTotalHistorico() {
        return valorTotalHistorico;
    }

    public void setValorTotalHistorico(BigDecimal valorTotalHistorico) {
        this.valorTotalHistorico = valorTotalHistorico;
    }

    public List<Maestro> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    public void setListaTipoDocumento(List<Maestro> listaTipoDocumento) {
        this.listaTipoDocumento = listaTipoDocumento;
    }

    public String getPrestadorReporteExcel() {
        return prestadorReporteExcel;
    }

    public void setPrestadorReporteExcel(String prestadorReporteExcel) {
        this.prestadorReporteExcel = prestadorReporteExcel;
    }

    public List<AuItemBitacora> getListaBitacoras() {
        return listaBitacoras;
    }

    public void setListaBitacoras(List<AuItemBitacora> listaBitacoras) {
        this.listaBitacoras = listaBitacoras;
    }

    public List<AuSolicitudAdjunto> getListaAdjuntosCotizacion() {
        return listaAdjuntosCotizacion;
    }

    public void setListaAdjuntosCotizacion(List<AuSolicitudAdjunto> listaAdjuntosCotizacion) {
        this.listaAdjuntosCotizacion = listaAdjuntosCotizacion;
    }

    public List<AntAnticipoAdjunto> getAntAnticipoAdjuntosList() {
        return antAnticipoAdjuntosList;
    }

    public void setAntAnticipoAdjuntosList(List<AntAnticipoAdjunto> antAnticipoAdjuntosList) {
        this.antAnticipoAdjuntosList = antAnticipoAdjuntosList;
    }

    public String getHeaderDialogVer() {
        return headerDialogVer;
    }

    public void setHeaderDialogVer(String headerDialogVer) {
        this.headerDialogVer = headerDialogVer;
    }

    public String getReporteTicketString() {
        return reporteTicketString;
    }

    public void setReporteTicketString(String reporteTicketString) {
        this.reporteTicketString = reporteTicketString;
    }

    public String getObservacionGenerica() {
        return observacionGenerica;
    }

    public void setObservacionGenerica(String observacionGenerica) {
        this.observacionGenerica = observacionGenerica;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getAuAutorizacionServicio().Accion(this);
    }

    public void refrescarIps() {
        getAuAutorizacionServicio().listarIps(this);
    }

    public void borrar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        getAuAutorizacionServicio().Accion(this);
        procesoFinal();
    }

    public void verAnular(int _id) {
        getObjeto().setId(_id);
        boolean factura = getAuAutorizacionServicio().validarFacturaAsociada(this);
        if (factura) {
            generarMensajes();
        } else {
            super.setAccion(ACCION_ADICIONAL_2);
            super.setDoAccion(ACCION_VER);
            getAuAutorizacionServicio().Accion(this);
            setIdMotivo(0);
            getObjeto().setEstadoJustificacion("");
            procesoFinal();
        }
    }

    public void anular() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_MODIFICAR);
        getAuAutorizacionServicio().Accion(this);
        procesoFinal();
    }

    public void autorizarPreautorizacion(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_10);
        getAuAutorizacionServicio().Accion(this);
        procesoFinal();
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_LISTAR:
                    PrimeFaces.current().ajax().update("frmAutorizaciones");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_GUARDAR:
                case Url.ACCION_MODIFICAR:
                case Url.ACCION_BORRAR:
                    PrimeFaces.current().ajax().update("frmAutorizaciones");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_VER:
                    PrimeFaces.current().ajax().update("frmVer");
                    PrimeFaces.current().ajax().update("frmVer:pVerServicios");
                    PrimeFaces.current().ajax().update("frmVer:tablaTecnologiasVer");
                    PrimeFaces.current().ajax().update("frmAuditoriaVer:labelDatosAuditoria");
                    PrimeFaces.current().executeScript("PF('dialogoVer').show()");
                    PrimeFaces.current().ajax().update("dialogoVerId");
                    crearLog(getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1://Ver todas las empresas (Deshabilitado)
                    break;
                case Url.ACCION_ADICIONAL_2://Anular
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            PrimeFaces.current().ajax().update("frmAnularAutorizacion");
                            PrimeFaces.current().executeScript("PF('dialogoAnularAutorizacion').show()");
                            break;
                        case Url.ACCION_MODIFICAR:
                            crearLog("Anular Autorización", getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmAutorizaciones");
                            PrimeFaces.current().executeScript("PF('dialogoAnularAutorizacion').hide()");
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3://Imprimir
                    crearLog("Imprimir Autorizacion", "Impresión de Autorización: " + getObjeto().getId());
                    break;
                case Url.ACCION_ADICIONAL_4://Incrementar impresiones
                    crearLog("Incremento Impresion Autorizacion", "Incremento Impresion Autorizacion: " + getObjeto().getId() + " - Autorizadas: " + getObjeto().getImpresionesAutorizadas());
                    PrimeFaces.current().ajax().update("frmAutorizaciones");
                    break;
                case Url.ACCION_ADICIONAL_5://Entregas
                    switch (getDoAccion()) {
                        case Url.ACCION_LISTAR:
                            PrimeFaces.current().ajax().update("frmVerEntregas");
                            PrimeFaces.current().executeScript("PF('dialogoEntregas').show()");
                            break;
                        case Url.ACCION_VER:
                            PrimeFaces.current().ajax().update("frmVerEntrega");
                            PrimeFaces.current().executeScript("PF('dialogoEntrega').show()");
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            PrimeFaces.current().ajax().update("frmGestionar");
                            PrimeFaces.current().executeScript("PF('dialogoGestionar').show()");
                            crearLog("Gestionar Entrega", getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_6://Reportes
                    switch (getDoAccion()) {
                        case Url.ACCION_CREAR:
                            PrimeFaces.current().ajax().update("frmSolicitarReporte");
                            PrimeFaces.current().executeScript("PF('dialogoSolicitarReporte').show()");
//                            PrimeFaces.current().ajax().update("frmReportes");
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().ajax().update("frmReportes");
                            PrimeFaces.current().executeScript("PF('dialogoSolicitarReporte').hide()");
                            crearLog("Generar Informe", getObjetoReporte().toString());
                            break;
                        case Url.ACCION_ADICIONAL_1:
                            PrimeFaces.current().resetInputs("frmNoPrestadoItem");
                            PrimeFaces.current().ajax().update("frmNoPrestadoItem");
                            PrimeFaces.current().executeScript("PF('dialogoNoPrestado').show()");
//                            PrimeFaces.current().ajax().update("frmReportes");
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            //rimeFaces.current().ajax().update("frmGestionar");
                            PrimeFaces.current().ajax().update("frmGestionar:tablaTecnologiasGestionar");
                            PrimeFaces.current().executeScript("PF('dialogoNoPrestado').hide()");
                            crearLog("No prestado", getObjetoEntrega().toString());
                            //setObjetoEntrega(null);
                            PrimeFaces.current().ajax().update("frmGestionar");
                            PrimeFaces.current().ajax().update("frmAutorizaciones");
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_7:
                    break;
                case Url.ACCION_ADICIONAL_8://Entregar
                    switch (getDoAccion()) {
                        case Url.ACCION_CREAR:
                            PrimeFaces.current().ajax().update("frmEntrega");
                            PrimeFaces.current().executeScript("PF('dialogoEntregar').show()");
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().executeScript("PF('dialogoEntregar').hide()");
                            crearLog("Guardar Entrega", getObjetoEntrega().toString());
                            PrimeFaces.current().ajax().update("frmGestionar");
                            PrimeFaces.current().ajax().update("frmAutorizaciones");
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_9://Anular entrega
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            PrimeFaces.current().ajax().update("frmAnularEntrega");
                            PrimeFaces.current().executeScript("PF('dialogoAnularEntrega').show()");
                            break;
                        case Url.ACCION_GUARDAR:
                            PrimeFaces.current().executeScript("PF('dialogoAnularEntrega').hide()");
                            PrimeFaces.current().ajax().update("frmVerEntregas");
                            PrimeFaces.current().ajax().update("frmGestionar");
                            PrimeFaces.current().ajax().update("frmAutorizaciones");
                            crearLog("Anular Entrega", getObjetoEntrega().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_10://autorizar preautorizacion
                    PrimeFaces.current().ajax().update("frmAutorizaciones");
                    crearLog("Autorizar preautorizacion", getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_11://Editar valor copago
                    switch (getDoAccion()) {
                        case Url.ACCION_VER:
                            PrimeFaces.current().ajax().update("frmEditarCopago");
                            PrimeFaces.current().executeScript("PF('dialogoEditarCopago').show()");
                            break;
                        case Url.ACCION_EDITAR:
                            PrimeFaces.current().executeScript("PF('dialogoEditarCopago').hide()");
                            crearLog("Editar copago", getObjeto().toString());
                            break;
                    }
                    break;
            }
        }
        generarMensajes();
    }

    public List<AuAnexo4Item> getListaAuAnexo4Items() {
        return listaAuAnexo4Items;
    }

    public void setListaAuAnexo4Items(List<AuAnexo4Item> listaAuAnexo4Items) {
        this.listaAuAnexo4Items = listaAuAnexo4Items;
    }

    public String obtenerTipoTencnologia(int id) {
        return AuConstantes.obtenerTipoTecnologia(id);
    }

    public StreamedContent generarReporteAnexo4(int id) {
        getObjeto().setId(id);
        StreamedContent streamContent = null;
        super.setAccion(ACCION_ADICIONAL_3);
        getAuAutorizacionServicio().Accion(this);
//        AuAnexo4 anexo4 = getAuAutorizacionServicio().consultarAnexo4(getObjeto().getId());
        crearLog("Imprimir Autorizacion", getObjeto().toString());
        boolean isActivo = true; //getAuAutorizacionServicio().validarEstadoAfiliado(getObjeto().getAsegAfiliadoId().getMaeEstadoAfiliacion());        
        if (isActivo) {
            String marca;
            if (getObjeto().getImpresionesRealizadas() == null) {
                getObjeto().setImpresionesRealizadas(0);
            }
            int ejeX = 20;
            int ejeY = 20;
            if (getObjeto().getImpresionesRealizadas() == 0) {
                marca = "Original";
            } else {
                int impresiones = getObjeto().getImpresionesRealizadas();
                marca = "Copia " + impresiones;
            }
            try {
                if (getObjeto().getImpresionesRealizadas() < getObjeto().getImpresionesAutorizadas()) {
                    //si no concuerda vuelve a generar el pdf
                    AuReporte reporte = new AuReporte(getHashUbicaciones());
                    if ((getObjeto().getArchivo() != null && !getObjeto().getArchivo().contains(getObjeto().getId() + ""))
                            || (getObjeto().getArchivoNombre() != null && !getObjeto().getArchivoNombre().contains(getObjeto().getId() + ""))) {
                        getObjeto().setRuta(getRutaAnexo4());
                        generarPDFAutorizacion(reporte);
                    }else if(getObjeto().getArchivoNombre() == null){
                        getObjeto().setRuta(getRutaAnexo4());
                        generarPDFAutorizacion(reporte);
                    }
                    String ruta = getObjeto().getRuta();
                    String nombre = getObjeto().getArchivo();
                    ruta += nombre;
                    byte[] bytes = Files.readAllBytes(Paths.get(ruta));
                    byte[] newBytes = reporte.generarMarcaAgua(bytes, marca, ejeX, ejeY);
                    if (newBytes != null) {
                        if (validarEstadoAnulado(getObjeto().getEstado())) {
                            marca = "ANULADA";
                            ejeX = 310;
                            ejeY = 565;
                            newBytes = reporte.generarMarcaAgua(newBytes, marca, ejeX, ejeY);
                        }
                        bytes = newBytes;
                    }
                    InputStream stream = new ByteArrayInputStream(bytes);
                    stream.mark(0);
                    streamContent = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name(getObjeto().getArchivoNombre()).build();
                    getAuAutorizacionServicio().contarImpresion(this);
                } else {
                    addError("La autorización número " + getObjeto().getId() + " ya alcanzo el número de impresiones permitidas (" + getObjeto().getImpresionesRealizadas() + ").");
                }
            } catch (IOException ex) {
                if (getObjeto() != null) {
                    AuReporte reporte = new AuReporte(getHashUbicaciones());
                    String ruta = getRutaAnexo4();
                    getObjeto().setRuta(ruta);
                    generarPDFAutorizacion(reporte);
                    if (getObjeto().getImpresionesRealizadas() < getObjeto().getImpresionesAutorizadas()) {
                        String nombre = getObjeto().getArchivo();
                        ruta += nombre;
                        try {
                            byte[] bytes = Files.readAllBytes(Paths.get(ruta));
                            byte[] newBytes = reporte.generarMarcaAgua(bytes, marca, ejeX, ejeY);
                            if (newBytes != null) {
                                if (validarEstadoAnulado(getObjeto().getEstado())) {
                                    marca = "ANULADA";
                                    ejeX = 310;
                                    ejeY = 565;
                                    newBytes = reporte.generarMarcaAgua(newBytes, marca, ejeX, ejeY);
                                }
                                bytes = newBytes;
                            }
                            InputStream stream = new ByteArrayInputStream(bytes);
                            stream.mark(0);
                            streamContent = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name(getObjeto().getArchivoNombre()).build();
                            getAuAutorizacionServicio().contarImpresion(this);
                        } catch (IOException e) {
                            crearLog("Fallo Imprimir Autorizacion", ex.getMessage() + " " + ex.toString());
                            addError("Hubo un fallo al descargar el pdf por favor comunicarse con el administrador");
                        }
                    } else {
                        addError("La autorización número " + getObjeto().getId() + " ya alcanzo el número de impresiones permitidas (" + getObjeto().getImpresionesRealizadas() + ").");
                    }
                }
                addMensaje("Hubo un error buscando el pdf, se genero nuevamente");
            }
        } else {
            addMensaje("El Afiliado no esta activo");
        }
        generarMensajes();
        return streamContent;
    }

    /*public void generarReporteAnexo4(int id) throws Exception {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_3);
        getAuAutorizacionServicio().Accion(this);
        crearLog("Imprimir Autorizacion", getObjeto().toString());
        boolean isActivo = true;
        if(isActivo){
            String marca;
            if (getObjeto().getImpresionesRealizadas() == null) {
                getObjeto().setImpresionesRealizadas(0);
            }
            int ejeX = 20;
            int ejeY = 20;
            if (getObjeto().getImpresionesRealizadas() == 0) {
                marca = "Original";
            } else {
                int impresiones = getObjeto().getImpresionesRealizadas();
                marca = "Copia " + impresiones;
            }
            try {
                if (getObjeto().getImpresionesRealizadas() < getObjeto().getImpresionesAutorizadas()) {
                    //si no concuerda vuelve a generar el pdf
                    AuReporte reporte = new AuReporte(getHashUbicaciones());
                    if ((getObjeto().getArchivo() != null && !getObjeto().getArchivo().contains(getObjeto().getId() + ""))
                            || (getObjeto().getArchivoNombre() != null && !getObjeto().getArchivoNombre().contains(getObjeto().getId() + ""))) {
                        getObjeto().setRuta(getRutaAnexo4());
                        generarPDFAutorizacion(reporte);
                    }
                    String ruta = getObjeto().getRuta();
                    String nombre = getObjeto().getArchivo();
                    ruta += nombre;
                    byte[] bytes = Files.readAllBytes(Paths.get(ruta));
                    byte[] newBytes = reporte.generarMarcaAgua(bytes, marca, ejeX, ejeY);
                    if (newBytes != null) {
                        if (validarEstadoAnulado(getObjeto().getEstado())) {
                            marca = "ANULADA";
                            ejeX = 310;
                            ejeY = 565;
                            newBytes = reporte.generarMarcaAgua(newBytes, marca, ejeX, ejeY);
                        }
                        bytes = newBytes;
                    }
                    setReporteTicketString(Utilidad.reporteByte(bytes));
                    try {
                        PrimeFaces.current().ajax().update("frmBar:intReportePdf");
                        PrimeFaces.current().executeScript("imprimirContenido();");
                    } catch (Exception e) {
                        addError("No fue posible visualizar la vista de impresión. (" + e.getMessage() + ")");
                    }
                    getAuAutorizacionServicio().contarImpresion(this);
                }else {
                    addError("La autorización número " + getObjeto().getId() + " ya alcanzo el número de impresiones permitidas (" + getObjeto().getImpresionesRealizadas() + ").");
                }
            }catch (IOException ex){
                if (getObjeto() != null) {
                    AuReporte reporte = new AuReporte(getHashUbicaciones());
                    String ruta = getRutaAnexo4();
                    getObjeto().setRuta(ruta);
                    generarPDFAutorizacion(reporte);
                    if (getObjeto().getImpresionesRealizadas() < getObjeto().getImpresionesAutorizadas()) {
                        String nombre = getObjeto().getArchivo();
                        ruta += nombre;
                        try {
                            byte[] bytes = Files.readAllBytes(Paths.get(ruta));
                            byte[] newBytes = reporte.generarMarcaAgua(bytes, marca, ejeX, ejeY);
                            if (newBytes != null) {
                                if (validarEstadoAnulado(getObjeto().getEstado())) {
                                    marca = "ANULADA";
                                    ejeX = 310;
                                    ejeY = 565;
                                    newBytes = reporte.generarMarcaAgua(newBytes, marca, ejeX, ejeY);
                                }
                                bytes = newBytes;
                            }
                            setReporteTicketString(Utilidad.reporteByte(bytes));
                            try {
                                PrimeFaces.current().ajax().update("frmBar:intReportePdf");
                                PrimeFaces.current().executeScript("imprimirContenido();");
                            } catch (Exception e) {
                                addError("No fue posible visualizar la vista de impresión. (" + e.getMessage() + ")");
                            }
                            getAuAutorizacionServicio().contarImpresion(this);
                        } catch (IOException e) {
                            crearLog("Fallo Imprimir Autorizacion", ex.getMessage() + " " + ex.toString());
                            addError("Hubo un fallo al descargar el pdf por favor comunicarse con el administrador");
                        }
                    } else {
                        addError("La autorización número " + getObjeto().getId() + " ya alcanzo el número de impresiones permitidas (" + getObjeto().getImpresionesRealizadas() + ").");
                    }
                }
                addMensaje("Hubo un error buscando el pdf, se genero nuevamente");
            }
        }else {
            addMensaje("El Afiliado no esta activo");
        }
    }*/
    public void generarPDFAutorizacion(AuReporte reporte) {
//        if (getObjeto().getArchivo() == null || getObjeto().getArchivo().equals("")) {
        getObjeto().setArchivo(AuConstantes.nombreReporteAnexo4(getObjeto()));
//        }
//        if (getObjeto().getArchivoNombre() == null || getObjeto().getArchivoNombre().equals("")) {
        getObjeto().setArchivoNombre(AuConstantes.nombreArchivoReporteAnexo4(getObjeto()));
//        }
        if (validarEsPreautorizacion(getObjeto().getEstado())) {
            reporte.generarReportePreAutorizacionAnexo4(getObjeto().getRuta(), getObjeto());
        } else {
            reporte.generarReporteAnexo4(getObjeto().getRuta(), getObjeto());
        }
    }

    public String obtenerDepartamento(int id) {
        try {
            int idPadre = hashUbicaciones.get(id).getUbicacionPadre().getId();
            return hashUbicaciones.get(idPadre).getNombre();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public String obtenerMunicipio(int id) {
        try {
            return hashUbicaciones.get(id).getNombre();
        } catch (Exception e) {
            return AuConstantes.TEXTO_VACIO;
        }
    }

    public HashMap<Integer, Maestro> getHashTipoDocumento() {
        return hashTipoDocumento;
    }

    public void setHashTipoDocumento(HashMap<Integer, Maestro> hashTipoDocumento) {
        this.hashTipoDocumento = hashTipoDocumento;
    }

    public boolean validarEstadoImpresionAnexo4(int estado) {
        int estadoAprobado = obtenerEstadoItem(AuConstantes.APROBADO_AUDITORIA);
        return estado == estadoAprobado;
    }

    public boolean validarEstadoPreautorizacion(int estado) {
        return estado == AuAnexo4.ESTADO_PREAUTORIZADO;
    }

    public boolean validarEsPreautorizacion(int estado) {
        return estado == AuAnexo4.ESTADO_PREAUTORIZADO || estado == AuAnexo4.ESTADO_ANULADA_PREAUTORIZACION;
    }

    public boolean validarEstadoAnulado(int estado) {
        //2023-09-29 jyperez se debe configurar para el estado ESTADO_ANULADO_PAGO_ANTICIPADO las mismas acciones que el ESTADO_ANULADA
        return estado == AuAnexo4.ESTADO_ANULADA || estado == AuAnexo4.ESTADO_ANULADA_PREAUTORIZACION || estado == AuAnexo4.ESTADO_ANULADO_PAGO_ANTICIPADO;
    }

    public int obtenerEstadoItem(String valor) {
        for (Maestro maestro : getListaEstadosItem()) {
            if (maestro.getValor().equals(valor)) {
                return maestro.getId();
            }
        }
        return 0;
    }

    public List<Maestro> getListaCausaNoEntrega() {
        return listaCausaNoEntrega;
    }

    public void setListaCausaNoEntrega(List<Maestro> listaCausaNoEntrega) {
        this.listaCausaNoEntrega = listaCausaNoEntrega;
    }

    public HashMap<Integer, Maestro> getHashCausaNoEntrega() {
        return hashCausaNoEntrega;
    }

    public void setHashCausaNoEntrega(HashMap<Integer, Maestro> hashCausaNoEntrega) {
        this.hashCausaNoEntrega = hashCausaNoEntrega;
    }

    public HashMap<Integer, Ubicacion> getHashUbicaciones() {
        return hashUbicaciones;
    }

    public void setHashUbicaciones(HashMap<Integer, Ubicacion> hashUbicaciones) {
        this.hashUbicaciones = hashUbicaciones;
    }

    public List<Maestro> getListaEstadosItem() {
        return listaEstadosItem;
    }

    public void setListaEstadosItem(List<Maestro> listaEstadosItem) {
        this.listaEstadosItem = listaEstadosItem;
    }

    public static Maestro getMapMaestroValue(Map map, Object key) {
        Maestro maestro = (Maestro) map.get(key);
        return maestro;
    }

    public List<Maestro> getListaMotivos() {
        return listaMotivos;
    }

    public void setListaMotivos(List<Maestro> listaMotivos) {
        this.listaMotivos = listaMotivos;
    }

    public HashMap<Integer, Maestro> getHashMotivos() {
        return hashMotivos;
    }

    public void setHashMotivos(HashMap<Integer, Maestro> hashMotivos) {
        this.hashMotivos = hashMotivos;
    }

    public List<Maestro> getListaMotivosA4() {
        return listaMotivosA4;
    }

    public void setListaMotivosA4(List<Maestro> listaMotivosA4) {
        this.listaMotivosA4 = listaMotivosA4;
    }

    public HashMap<Integer, Maestro> getHashMotivosA4() {
        return hashMotivosA4;
    }

    public void setHashMotivosA4(HashMap<Integer, Maestro> hashMotivosA4) {
        this.hashMotivosA4 = hashMotivosA4;
    }

    public int getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(int idMotivo) {
        this.idMotivo = idMotivo;
    }

    public Maestro obtenerMaestroMotivo(int idMotivo) {
        try {
            return hashMotivos.get(idMotivo);
        } catch (Exception e) {
            return null;
        }
    }

    public Maestro obtenerMaestroMotivoA4(int idMotivo) {
        try {
            return hashMotivosA4.get(idMotivo);
        } catch (Exception e) {
            return null;
        }
    }

    public void incrementarImpresion(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_4);
        getAuAutorizacionServicio().Accion(this);
        procesoFinal();
    }

    public String obtenerTextoAnulado() {
        try {
            return "Anulada - " + getObjeto().getMaeEstadoMotivoValor();
        } catch (Exception e) {
            return "";
        }
    }

    public void gestionarEntrega(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_ADICIONAL_1);
        setObjetoEntrega(null);
        getAuAutorizacionServicio().Accion(this);
        procesoFinal();
    }

    public void abrirVentanaEntrega(AuAnexo4Item item) {
        setObjetoEntrega(new AuAnexo4Entrega());
        getObjetoEntrega().setAuAnexo4Id(getObjeto());
        getObjetoEntrega().setAuAnexo4ItemId(item);
        getObjetoEntrega().setCantidadAutorizada(item.getCantidadPendiente());
        getObjetoEntrega().setCantidadPendiente(item.getCantidadPendiente());
        if (item.getTipoTecnologia() == AuAnexo3Item.TIPO_TECNOLOGIA_CUP || item.getTipoTecnologia() == AuAnexo3Item.TIPO_TECNOLOGIA_PAQUETE) {
            setTipoEntrega(false);
            getObjetoEntrega().setReclamaAfiliado(true);
            getObjetoEntrega().setTipoEntrega(AuAnexo4Entrega.TIPO_TOTAL);
        } else {
            setTipoEntrega(true);
        }
        super.setAccion(ACCION_ADICIONAL_8);
        super.setDoAccion(ACCION_CREAR);
        getAuAutorizacionServicio().Accion(this);
        procesoFinal();
    }

    public void entregar() {
        super.setAccion(ACCION_ADICIONAL_8);
        super.setDoAccion(ACCION_GUARDAR);
        getAuAutorizacionServicio().Accion(this);
        procesoFinal();
    }

    public void noPrestado(AuAnexo4Item item) {
        item.setEntrega(AuAnexo4Item.TIPO_ENTREGA_NO_PRESTADO);
        setObjetoEntrega(new AuAnexo4Entrega());
        getObjetoEntrega().setAuAnexo4Id(getObjeto());
        getObjetoEntrega().setAuAnexo4ItemId(item);
        getObjetoEntrega().setNoPrestadoObservacion(item.getEntregaObservacion());
        getObjetoEntrega().setTipoEntrega(AuAnexo4Entrega.TIPO_NO_PRESTADO);
        getObjetoEntrega().setCantidadAutorizada(item.getCantidadPendiente());
        getObjetoEntrega().setCantidadPendiente(item.getCantidadPendiente());

        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(ACCION_ADICIONAL_2);
        getAuAutorizacionServicio().Accion(this);
        procesoFinal();
    }

    public void ventanaNoPrestado(AuAnexo4Item item) {
        setObjetoItem(new AuAnexo4Item());
        setObjetoItem(item);
        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(ACCION_ADICIONAL_1);
        getAuAutorizacionServicio().Accion(this);
        procesoFinal();
    }

    public void verEntregas(int id) {
        setObjetoItem(new AuAnexo4Item(id));
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_LISTAR);
        getAuAutorizacionServicio().Accion(this);
        procesoFinal();
    }

    public void verEntrega(int id) {
        setObjetoEntrega(new AuAnexo4Entrega(id));
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(ACCION_VER);
        getAuAutorizacionServicio().Accion(this);
        procesoFinal();
    }

    public void calcularPendiente() {
        int pendientes = getObjetoEntrega().getCantidadAutorizada() - getObjetoEntrega().getCantidadEntregada();
        getObjetoEntrega().setCantidadPendiente(pendientes);
        if (getObjetoEntrega().getCantidadEntregada() > 0) {
            getObjetoEntrega().setMaeCausaNoEntregaId(null);
            getObjetoEntrega().setMaeCausaNoEntregaCodigo(null);
            getObjetoEntrega().setMaeCausaNoEntregaValor(null);
        }
        PrimeFaces.current().ajax().update("frmEntrega:cantidadPendienteGestionar");
    }

    public void agregarHistoricoCausaNoEntrega() {
        Maestro causa = getHashCausaNoEntrega().get(getObjetoEntrega().getMaeCausaNoEntregaId());
        if (causa != null) {
            getObjetoEntrega().setMaeCausaNoEntregaId(causa.getId());
            getObjetoEntrega().setMaeCausaNoEntregaCodigo(causa.getValor());
            getObjetoEntrega().setMaeCausaNoEntregaValor(causa.getNombre());
        }
    }

    public void abrirModificarCopago(int id) {
        getObjeto().setId(id);
        setObservacionGenerica(null);
        super.setAccion(ACCION_ADICIONAL_11);
        super.setDoAccion(ACCION_VER);
        getAuAutorizacionServicio().Accion(this);
        procesoFinal();
    }

    public void modificarCopago() {
        super.setAccion(ACCION_ADICIONAL_11);
        super.setDoAccion(ACCION_EDITAR);
        getAuAutorizacionServicio().Accion(this);
        procesoFinal();
    }

    public String obtenerBoolean(boolean valor) {
        if (valor) {
            return "Si";
        } else {
            return "No";
        }
    }

    public boolean validarBloqueoNombreEntrega() {
        boolean validar = false;
        if (getObjetoEntrega().isReclamaAfiliado()) {
            validar = true;
        }
        if (getObjetoEntrega().getAuAnexo4ItemId().getTipoTecnologia() == 1 || getObjetoEntrega().getAuAnexo4ItemId().getTipoTecnologia() == 4) {
            validar = true;
        }
        return validar;
    }

    public boolean validarImprimir(int estado) {
        return estado != AuAnexo4.ESTADO_AUTORIZADA
                || estado != AuAnexo4.ESTADO_AUTORIZADA_AUTOMATICO
                || estado != AuAnexo4.ESTADO_PREAUTORIZADO;
    }

    public boolean validarCopago(int estado) {
        return estado == AuAnexo4.ESTADO_AUTORIZADA
                || estado == AuAnexo4.ESTADO_AUTORIZADA_AUTOMATICO
                || estado == AuAnexo4.ESTADO_AUTORIZADA_PREAUTORIZACION
                || estado == AuAnexo4.ESTADO_PREAUTORIZADO;
    }

    public boolean validarContrato(AuAnexo4 obj) {
        boolean validate = true;
        try {
            if (obj.isPosfechada()) {
                if (obj.getImpresionesRealizadas().equals(0)) {
                    if (obj.getCntContratoId() != null) {
                        if (obj.getFechaAutorizacion().after(obj.getCntContratoId().getFechaFin())) {
                            validate = false;
                        }
                    }
                }
            }
        } catch (Exception e) {
            validate = true;
        }
        return validate;
    }

    public Date obtenerFechaActual() throws ParseException {
//        SimpleDateFormat formatoBasico = new SimpleDateFormat("dd/MM/yyyy");
//        SimpleDateFormat formatoCompleto = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date fechaActual = new Date();
//        String nuevaFecha = formatoBasico.format(fechaActual) + " 00:00:00";
//        fechaActual = formatoCompleto.parse(nuevaFecha);
        return fechaActual;
    }

    public boolean validarFecha(AuAnexo4 objeto) throws ParseException {
        boolean validar = true;
        SimpleDateFormat formatoBasico = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fecha_autorizacion = formatoBasico.format(objeto.getFechaAutorizacion()).substring(0, 10);
        Date date_autorizacion = formatoBasico.parse(fecha_autorizacion + " 00:00:00");
        Date fechaActual = obtenerFechaActual();
        if (fechaActual.before(date_autorizacion)) {
            validar = false;
        }
        return validar;
    }

    public String obtenerColorImpresion(AuAnexo4 objeto) throws ParseException {
        SimpleDateFormat formatoBasico = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fecha_autorizacion = formatoBasico.format(objeto.getFechaAutorizacion()).substring(0, 10);
        Date date_autorizacion = formatoBasico.parse(fecha_autorizacion + " 00:00:00");
        String color = "green";
        Date fechaActual = obtenerFechaActual();
        //2023-09-29 jyperez se debe configurar para el estado ESTADO_ANULADO_PAGO_ANTICIPADO las mismas acciones que el ESTADO_ANULADA
        if (objeto.getEstado() == AuAnexo4.ESTADO_ANULADA
                || objeto.getEstado() == AuAnexo4.ESTADO_ANULADO_PAGO_ANTICIPADO
                || objeto.getEstado() == AuAnexo4.ESTADO_ANULADA_PREAUTORIZACION
                || objeto.getFechaFin().before(fechaActual)) {
            return "red";
        } else {
            if (fechaActual.before(date_autorizacion)) {
                if (objeto.getCntContratoId() != null) {
                    if (objeto.getFechaAutorizacion().after(objeto.getCntContratoId().getFechaFin())) {
                        return "purple";
                    }
                }
                return "blue";
            }
            if (objeto.getImpresionesRealizadas() != null && objeto.getImpresionesRealizadas() > 0) {
                return "lawngreen";
            }
        }
        return color;
    }

    public String obtenerNombreImpresion(AuAnexo4 objeto) throws ParseException {
        SimpleDateFormat formatoBasico = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fecha_autorizacion = formatoBasico.format(objeto.getFechaAutorizacion()).substring(0, 10);
        Date date_autorizacion = formatoBasico.parse(fecha_autorizacion + " 00:00:00");
        String color = "";
        Date fechaActual = obtenerFechaActual();
        //2023-09-29 jyperez se debe configurar para el estado ESTADO_ANULADO_PAGO_ANTICIPADO las mismas acciones que el ESTADO_ANULADA
        if (objeto.getEstado() == AuAnexo4.ESTADO_ANULADA
                || objeto.getEstado() == AuAnexo4.ESTADO_ANULADO_PAGO_ANTICIPADO
                || objeto.getEstado() == AuAnexo4.ESTADO_ANULADA_PREAUTORIZACION
                || objeto.getFechaFin().before(fechaActual)) {
            return "";
        } else {
            if (fechaActual.before(date_autorizacion)) {
                if (objeto.getCntContratoId() != null) {
                    if (objeto.getFechaAutorizacion().after(objeto.getCntContratoId().getFechaFin())) {
                        return "Posfechado Inactivo";
                    }
                }
                return "Posfechado";
            }
            if (objeto.getImpresionesRealizadas() != null && objeto.getImpresionesRealizadas() > 0) {
                return "";
            }
        }
        return color;
    }

    public void descargarAdjuntoSolicitudAnexo(AuSolicitudAdjunto adjunto) {
        String rutaCompleta = adjunto.getRuta() + adjunto.getArchivo();
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
            String attachmentName = "attachment; filename=\"" + adjunto.getNombre() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".pdf")) {
                ec.setResponseContentType("application/pdf");
            } else if (ext.equalsIgnoreCase(".jpg")) {
                ec.setResponseContentType("image/jpg");
            } else if (ext.equalsIgnoreCase(".jpeg")) {
                ec.setResponseContentType("image/jpeg");
            } else if (ext.equalsIgnoreCase(".png")) {
                ec.setResponseContentType("image/png");
            } else if (ext.equalsIgnoreCase(".doc")) {
                ec.setResponseContentType("application/msword");
            } else {
                throw new Exception();
            }
            OutputStream output = ec.getResponseOutputStream();
            output.write(exportContent);
            output.close();
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
        }
        procesoFinal();
    }

    public void descargarAdjuntoSolicitudAnticipo(AntAnticipoAdjunto adjunto) {
        String rutaCompleta = adjunto.getRuta() + adjunto.getArchivo();
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
            String attachmentName = "attachment; filename=\"" + adjunto.getNombre() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".pdf")) {
                ec.setResponseContentType("application/pdf");
            } else if (ext.equalsIgnoreCase(".jpg")) {
                ec.setResponseContentType("image/jpg");
            } else if (ext.equalsIgnoreCase(".jpeg")) {
                ec.setResponseContentType("image/jpeg");
            } else if (ext.equalsIgnoreCase(".png")) {
                ec.setResponseContentType("image/png");
            } else if (ext.equalsIgnoreCase(".doc")) {
                ec.setResponseContentType("application/msword");
            } else {
                throw new Exception();
            }
            OutputStream output = ec.getResponseOutputStream();
            output.write(exportContent);
            output.close();
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
        }
        procesoFinal();
    }

    public BigDecimal obtenerValorPago() {
        return getObjeto().getValorCopago().add(getObjeto().getValorCuotaModeradora());
    }

    public void refrescarReportes() {
        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(ACCION_LISTAR);
        getAuAutorizacionServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmReportes:pReportes");
    }

    public void ventanaCrearReporte() {
        super.setAccion(ACCION_ADICIONAL_6);
        super.setDoAccion(ACCION_CREAR);
        getAuAutorizacionServicio().Accion(this);
        procesoFinal();
    }

    public void generarReporte() {
        int dias = contarDiasEntreFechas(getObjetoReporte().getFechaInicio(), getObjetoReporte().getFechaFin());
        if (dias > 31) {
            addError("Hay mas de un mes entre las fechas");
        }
        if (dias < 0) {
            addError("La fecha de inicio debe ser mayor a la final");
        }
        if (getErrores().isEmpty()) {
            super.setAccion(ACCION_ADICIONAL_6);
            super.setDoAccion(ACCION_GUARDAR);
            getAuAutorizacionServicio().Accion(this);
            procesoFinal();
        } else {
            generarMensajes();
        }
    }

    public int contarDiasEntreFechas(Date fechaInicio, Date fechaFin) {
        int milisecondsByDay = 86400000;
        int dias = (int) ((fechaFin.getTime() - fechaInicio.getTime()) / milisecondsByDay);
        return dias;
    }

    public void verImpresiones(AuAnexo4 anexo4) {
        setListaImpresiones(anexo4.getAuAnexo4ImpresionesList());
        PrimeFaces.current().ajax().update("frmHistorialImpresiones");
        PrimeFaces.current().executeScript("PF('dialogoHistorialImpresiones').show()");
    }

    public String obtenerTipoImpresion(int tipo) {
        String texto = "";
        switch (tipo) {
            case 0:
                texto = "Original";
                break;
            case 1:
                texto = "Copia";
                break;
            case 2:
                texto = "Incremento";
                break;
        }
        return texto;
    }

    public void descargarReporteAnexo4(AuAnexo4Reporte reporte) {
        String rutaCompleta = reporte.getRuta() + reporte.getArchivo() + ".xlsx";
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
            String attachmentName = "attachment; filename=\"" + reporte.getArchivo() + ".xlsx" + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".xlsx")) {
                ec.setResponseContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            } else {
                throw new Exception();
            }
            OutputStream output = ec.getResponseOutputStream();
            output.write(exportContent);
            output.close();
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
        }
        procesoFinal();
    }

    public void salir() {
        this.objeto = null;
        FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.removeAttribute("com.sun.faces.renderkit.ServerSideStateHelper.LogicalViewMap");
        session.removeAttribute("com.sun.faces.application.view.activeViewMaps");
        session.removeAttribute("auAutorizacionBean");
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("auAutorizacionBean");
    }

    public void onRowSelectIps(SelectEvent event) {
        CntPrestadorSede ips = (CntPrestadorSede) event.getObject();
        getObjetoReporte().setSede(ips);
        getParamConsulta(1).setFiltros(new HashMap<>());
        PrimeFaces.current().ajax().update("frmSolicitarReporte:ips");
        PrimeFaces.current().executeScript("PF('dialogoIpsLista').hide()");
    }

    public void consultarIps() {
        PrimeFaces.current().executeScript("PF('dialogoIpsLista').show()");
        PrimeFaces.current().ajax().update("frmIpsLista");
    }

    public void verAnularEntrega(int idEntrega) {
        setObjetoEntrega(new AuAnexo4Entrega(idEntrega));
        super.setAccion(ACCION_ADICIONAL_9);
        super.setDoAccion(ACCION_VER);
        getAuAutorizacionServicio().Accion(this);
        procesoFinal();
    }

    public void anularEntrega() {
        super.setAccion(ACCION_ADICIONAL_9);
        super.setDoAccion(ACCION_GUARDAR);
        getAuAutorizacionServicio().Accion(this);
        procesoFinal();
    }

//    public void borrarEntrega(int idEntrega) {
//        setObjetoEntrega(new AuAnexo4Entrega(idEntrega));
//        super.setAccion(ACCION_ADICIONAL_10);
//        getAuAutorizacionServicio().Accion(this);
//        procesoFinal();
//    }
    public Date obtenerFechaMes() {
        Date fechaActual = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaActual);
        calendar.set(2021, 0, 1);
        return calendar.getTime();
    }

    public void verBitacoras(AuAnexo4Item item4) {
        setObjetoItem(item4);
        getAuAutorizacionServicio().verBitacoras(this);
        PrimeFaces.current().executeScript("PF('dialogoVerBitacoras').show()");
        PrimeFaces.current().ajax().update("frmVerBitacoras");
        generarMensajes();
    }

    public void verHistoricos(int idAnexo4) {
        getObjeto().setId(idAnexo4);
        getAuAutorizacionServicio().verHistoricosAnexo4(this);
        PrimeFaces.current().executeScript("PF('dialogoVerHistoricos').show()");
        PrimeFaces.current().ajax().update("frmVerHistoricos");
        generarMensajes();
    }

    public void consultarAdjuntos(int id) {
        if (getObjeto().getAuAnexo3Id() != null) {
            getObjeto().getAuAnexo3Id().setObjetoTecnologia(
                    new AuAnexo3Item(id)
            );
            this.listaAdjuntosCotizacion = new ArrayList();
            this.antAnticipoAdjuntosList = new ArrayList<>();
            getAuAutorizacionServicio().consultarAdjuntosCotizacion(this);

            PrimeFaces.current().executeScript("PF('dialogoVerAdjuntosItem').show()");
            PrimeFaces.current().ajax().update("frmVerAdjuntosItem");

            generarMensajes();
        }

    }

    public void mostrarAfiliadoGeneral() {
        PrimeFaces.current().resetInputs("frmAfiliadoGeneral");
        PrimeFaces.current().ajax().update("frmAfiliadoGeneral");
        PrimeFaces.current().executeScript("PF('dlgInfoAfiliado').show();");
    }

    public String obtenerString(boolean bool) {
        return bool == true ? AuConstantes.TEXTO_SI : AuConstantes.TEXTO_NO;
    }

    public void mostrarMensaje(String desc) {
        setObservacionGenerica(desc);
        PrimeFaces.current().resetInputs("frmObservacion");
        PrimeFaces.current().ajax().update("frmObservacion");
        PrimeFaces.current().executeScript("PF('dlgMsg').show();");
    }

    public boolean esAdministradora() {
        return super.getConexion().getEmpresa().isAdministradora();
    }

    public String estiloAdminnistradora() {
        if (esAdministradora()) {
            return "display:block;";
        } else {
            return "display:none;";
        }
    }

    public boolean consultarCantidadAnuladas(int id) {
        boolean validate = true;
        if (!getAuAutorizacionServicio().consultarCantidadAnuladas(this, id)) {
            validate = false;
        }
        return validate;
    }
}
