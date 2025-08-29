package com.saviasaludeps.savia.web.cuentamedica.rips.carga.bean;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoSede;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDevolucion;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAfFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCarga;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCargaAnexo;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCargaDetalleDTO;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsEstructuraError;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsSuceso;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmRipsCargaRemoto;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.primefaces.PrimeFaces;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.FilterMeta;
import com.saviasaludeps.savia.web.cuentamedica.rips.carga.servicio.CmRipsCargaServicioIface;
import com.saviasaludeps.savia.web.utilidades.PropApl;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Pattern;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.export.Exporter;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.SortMeta;
import org.primefaces.model.file.UploadedFile;
import org.springframework.web.jsf.FacesContextUtils;

@ManagedBean
@ViewScoped
public class CmRipsCargaBean extends Url {

    public static final char ACCION_VALIDAR_CONTRATOS_VIGENTES = 'a';
    public static final int RANGO_FECHA_PRESTACION = 10;

    private int tamanoPagina = 30;
    private CmRipsCargaServicioIface cmRipsCargaServicio;
    private List<CmRipsCarga> registros;
    private List<CmRipsEstructuraError> registrosEstructuraError;
    private CmRipsCarga objeto;
    private CntPrestadorSede objetoPrestadorSede;
    private CntPrestador objetoPrestador;
    private LazyDataModel<CmRipsCarga> lazyCmRips;
    private LazyDataModel<CntContratoSede> lazyContratos;
    private LazyDataModel<CmRipsAfFactura> lazyFacturas;
    private LazyDataModel<CmRipsCargaDetalleDTO> lazyDetalles;
    private LazyDataModel<CmAuditoriaDevolucion> lazyDevoluciones;
    private LazyDataModel<CmRipsEstructuraError> lazyCmRipsEstructuraError;
    private LazyDataModel<CmRipsSuceso> lazyCmRipsSucesos;
    private List<CmRipsCargaAnexo> listaAdjuntos;
    private List<com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCargaAnexo> listaAnexos;
    private List<CmRipsEstructuraError> listaErroresEstructura;
    private List<CntPrestadorSede> listaPrestadorSedes;
    private List<CntContratoSede> listaContratos;
    private LazyDataModel<CntPrestador> lazyPrestadores;
    private LazyDataModel<CntPrestadorSede> lazySedes;
    private List<Ubicacion> listaUbicacion;
    private String cntBuscadorContrato;
    private Exporter<DataTable> textExporter;
    private ParamConsulta paramConsultaContratos;
    private ParamConsulta paramConsultaDevoluciones;
    private ParamConsulta paramConsultaDetalles;
    private ParamConsulta paramConsultaSucesos;
    private ParamConsulta paramConsultaPrestador;
    private CntContratoSede cntContratoSede;
    private CmAuditoriaDevolucion devolucion;
    private CmRipsAfFactura factura;
    private List<CmRipsCargaDetalleDTO> listaCmRipsCargaFacturaDetalle;
    private List<CmRipsSuceso> listaCmRipsSucesos;
    private HashMap<Integer, Maestro> hashRegimenes;
    private HashMap<Integer, Maestro> hashRegionales;
    private HashMap<Integer, Ubicacion> hashUbicacion;
    private HashMap<Integer, Ubicacion> hashUbicacionDepartamento;
    private HashMap<Integer, Maestro> hashModalidadContratos;
    private HashMap<Integer, Maestro> hashRechazos;
    private List<CmAuditoriaDevolucion> listaAuditoriaDevoluciones;
    private List<Maestro> listaTiposDocumento;
    private int totalDevoluciones = 0;
    private String nitPrestador;
    private List<Maestro> listaTiposDocumentoEmpresa;
    private String rangoFechaPrestacion;
    private String mansajeGeneral;
    private boolean deshabilitarNoPbs;

    private CmRipsCargaRemoto getCmRipsCargaRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("CmRipsCargaServicio", CmRipsCargaRemoto.class.getName());
        return (CmRipsCargaRemoto) object;
    }

    @PreDestroy
    public void preDestroy() {
        this.hashUbicacion = null;
        this.hashUbicacionDepartamento = null;
        this.objeto = null;
    }

    public CmRipsCargaBean() {
        this.objeto = new CmRipsCarga();
        Modulo _mod = super.validarModulo(Modulo.ID_CM_RIPS);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyCmRips = new LazyDataModel<CmRipsCarga>() {

                private List<CmRipsCarga> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CmRipsCarga> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(CmRipsCarga objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public CmRipsCarga getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CmRipsCarga objeto : lista) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }
            };
            lazyCmRipsEstructuraError = new LazyDataModel<CmRipsEstructuraError>() {

                private List<CmRipsEstructuraError> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsulta().getCantidadRegistros();
                }

                @Override
                public List<CmRipsEstructuraError> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsulta().setPrimerRegistro(primerRegistro);
                    getParamConsulta().setRegistrosPagina(registrosPagina);
                    getParamConsulta().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsulta().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescar();
                    lista = getRegistrosEstructuraError();
                    setRowCount(getParamConsulta().getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(CmRipsEstructuraError objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public CmRipsEstructuraError getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CmRipsEstructuraError objeto : lista) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }
            };
            lazyContratos = new LazyDataModel<CntContratoSede>() {

                private List<CntContratoSede> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsultaContratos().getCantidadRegistros();
                }

                @Override
                public List<CntContratoSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsultaContratos().setPrimerRegistro(primerRegistro);
                    getParamConsultaContratos().setRegistrosPagina(registrosPagina);
                    getParamConsultaContratos().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsultaContratos().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarContrato();
                    lista = getListaContratos();
                    setRowCount(getParamConsultaContratos().getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(CntContratoSede objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public CntContratoSede getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CntContratoSede objeto : lista) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }
            };

            lazyDetalles = new LazyDataModel<CmRipsCargaDetalleDTO>() {

                private List<CmRipsCargaDetalleDTO> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsultaDetalles().getCantidadRegistros();
                }

                @Override
                public List<CmRipsCargaDetalleDTO> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsultaDetalles().setPrimerRegistro(primerRegistro);
                    getParamConsultaDetalles().setRegistrosPagina(registrosPagina);
                    getParamConsultaDetalles().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsultaDetalles().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarFacturaDetalle();
                    lista = getListaCmRipsCargaFacturaDetalle();
                    setRowCount(getParamConsultaDetalles().getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(CmRipsCargaDetalleDTO objeto) {
                    return ""+objeto.getId();
                }

                @Override
                public CmRipsCargaDetalleDTO getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CmRipsCargaDetalleDTO objeto : lista) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }
            };

            lazyCmRipsSucesos = new LazyDataModel<CmRipsSuceso>() {

                private List<CmRipsSuceso> lista;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsultaSucesos().getCantidadRegistros();
                }

                @Override
                public List<CmRipsSuceso> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsultaSucesos().setPrimerRegistro(primerRegistro);
                    getParamConsultaSucesos().setRegistrosPagina(registrosPagina);
                    getParamConsultaSucesos().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsultaSucesos().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarSuceso();
                    lista = getListaCmRipsSucesos();
                    setRowCount(getParamConsultaSucesos().getCantidadRegistros());
                    return lista;
                }

                @Override
                public String getRowKey(CmRipsSuceso objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public CmRipsSuceso getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CmRipsSuceso objeto : lista) {
                        if (id.equals(objeto.getId())) {
                            return objeto;
                        }
                    }
                    return null;
                }
            };

            lazySedes = new LazyDataModel<CntPrestadorSede>() {

                private List<CntPrestadorSede> listaSedes;

                @Override
                public int count(Map<String, FilterMeta> filtros) {
                    return getParamConsultaPrestador().getCantidadRegistros();
                }

                @Override
                public List<CntPrestadorSede> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                    getParamConsultaPrestador().setPrimerRegistro(primerRegistro);
                    getParamConsultaPrestador().setRegistrosPagina(registrosPagina);
                    getParamConsultaPrestador().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                    getParamConsultaPrestador().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                    refrescarSedes();
                    listaSedes = getListaPrestadorSedes();
                    setRowCount(getParamConsultaPrestador().getCantidadRegistros());
                    return listaSedes;
                }

                @Override
                public String getRowKey(CntPrestadorSede ips) {
                    return ips.getId().toString();
                }

                @Override
                public CntPrestadorSede getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CntPrestadorSede objeto : listaSedes) {
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
        FacesContextUtils
                .getRequiredWebApplicationContext(FacesContext.getCurrentInstance())
                .getAutowireCapableBeanFactory().autowireBean(this);
        getCmRipsCargaServicio().cargaInicial(this);
        listar();
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    private void limpiarTablaDevoluciones() {
        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().
                findComponent("frmVerDevoluciones:tablaDevoluciones");
        dataTable.reset();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCmRipsCargaServicio().Accion(this);
    }

    public void refrescarContrato() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(Url.ACCION_ADICIONAL_3);
        getCmRipsCargaServicio().Accion(this);
    }

    public void refrescarDevolucion() {
        super.setAccion(Url.ACCION_ADICIONAL_4);
        getCmRipsCargaServicio().Accion(this);
    }

    public void refrescarSuceso() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_ADICIONAL_2);
        getCmRipsCargaServicio().Accion(this);
    }

    public void crear() {
        if (this.listaAdjuntos != null) {
            this.listaAdjuntos.clear();
        }
        setListaPrestadorSedes(new ArrayList<>());
        setObjeto(new CmRipsCarga());
        setObjetoPrestador(new CntPrestador());
        setObjetoPrestadorSede(new CntPrestadorSede());
        setNitPrestador("");
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().ajax().update("frmCrear:pCrearDatos");
        super.setAccion(ACCION_CREAR);
        getCmRipsCargaServicio().Accion(this);

        setRangoFechaPrestacion( asignarRangoPrestacion() );

        PrimeFaces.current().ajax().update("frmCrear:pCrearDatos");
        PrimeFaces.current().ajax().update("frmCrear:botonCargarRips");
        PrimeFaces.current().ajax().update("frmCrear:prestador");
        PrimeFaces.current().ajax().update("frmCrear:tablaAdjuntos");
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        //Validar que esten los 3 archivos necesarios
        boolean ct = false;
        boolean af = false;
        boolean us = false;

        //boolean esFechaPrestacionValida = validarFechaPrestacion(getObjeto().getFechaPrestacion());
        for (CmRipsCargaAnexo adj : listaAdjuntos) {
            if (adj.getArchivoNombre().toUpperCase().startsWith("AF")) {
                af = true;
            }
            if (adj.getArchivoNombre().toUpperCase().startsWith("US")) {
                us = true;
            }
            if (adj.getArchivoNombre().toUpperCase().startsWith("CT")) {
                ct = true;
            }
        }
        if (ct == false || af == false || us == false) {
            addError("Debe agregar el archivo US - AF - CT");
        } else {
            super.setAccion(ACCION_GUARDAR);
            getCmRipsCargaServicio().Accion(this);
            if (!super.isError()) {
                PrimeFaces.current().executeScript("PF('dialogoCrear').hide()");
            }
        }
        procesoFinal();
    }

    public void guardarEstadoEnColaRips(int idCmCarga) {
        getObjeto().setId(idCmCarga);
        super.setAccion(ACCION_ADICIONAL_5);
        getCmRipsCargaServicio().Accion(this);
        if(!this.isError()){
            this.addMensaje("Se ha realizado el proceso de cambio de estado para la carga de id("+idCmCarga+")");
        }
        procesoFinal();
    }

    public void editar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_EDITAR);
        getCmRipsCargaServicio().Accion(this);

        desHabilitarCoberturaParaMotivoCapita(getObjeto().getMaeContratoModalidadValor());

        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void ver(int id) {
        DataTable dt = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmVer:tablaFacturas");
        dt.setFilterBy(null);
        dt.reset();
        PrimeFaces.current().resetInputs("frmVer:tablaFacturas");
        PrimeFaces.current().ajax().update("frmVer:hPanelFacturas");
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VER);
        getCmRipsCargaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVer:pVer");
        PrimeFaces.current().ajax().update("frmVer:tablaFacturas");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void verAdjuntos(int id) {
        setObjeto(new CmRipsCarga(id));
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_ADICIONAL_4);
        getCmRipsCargaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerAdjuntos:pAdjuntos");
        PrimeFaces.current().executeScript("PF('dialogoVerAdjuntos').show()");
        procesoFinal();
    }

    public void gestionar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_ADICIONAL_1);
        getCmRipsCargaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmGestionar:tablaFacturas");
        PrimeFaces.current().ajax().update("frmGestionar:pGestionar");
        PrimeFaces.current().executeScript("PF('dialogoGestionar').show()");
        procesoFinal();
    }

    public void rechazar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_3);
        getCmRipsCargaServicio().Accion(this);
        PrimeFaces.current().executeScript("PF('dialogoRechazoRips').hide()");
        PrimeFaces.current().executeScript("PF('dialogoGestionar').hide()");
        procesoFinal();
    }

    public void verErroresEstructura(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_ADICIONAL_1);
        getCmRipsCargaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerErroresEstructura:pVerErroresEstructuraTabla");
        PrimeFaces.current().ajax().update("frmVerErroresEstructura:tablaErroresEstructura");
        PrimeFaces.current().executeScript("PF('dialogoVerErroresEstructura').show()");
        procesoFinal();
    }

    public void verContratos() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_ADICIONAL_3);
        getCmRipsCargaServicio().Accion(this);
        crearLog(getObjeto().toString());
        if (!isError()) {
            PrimeFaces.current().resetInputs("frmVerContratos");
            PrimeFaces.current().ajax().update("frmVerContratos");
            PrimeFaces.current().ajax().update("frmVerContratos:pVerContratos");
            PrimeFaces.current().ajax().update("frmVerContratos:tablaContratos");
            PrimeFaces.current().executeScript("PF('dialogoVerContratos').show()");
        }
        procesoFinal();
    }

    public void verDevoluciones() {
        crearLog(getObjeto().toString());
        inicializarTablaDevoluciones();
        limpiarTablaDevoluciones();
        PrimeFaces.current().resetInputs("frmVerDevoluciones");
        PrimeFaces.current().ajax().update("frmVerDevoluciones");
        PrimeFaces.current().ajax().update("frmVerDevoluciones:pVerDevolucionesTabla");
        PrimeFaces.current().ajax().update("frmVerDevoluciones:tablaDevoluciones");
        PrimeFaces.current().executeScript("PF('dialogoVerDevoluciones').show()");
        super.setAccion(Url.ACCION_ADICIONAL_4);
        procesoFinal();
    }

    public void verConfirmarRechazar(int id) {
        getObjeto().setId(id);
        PrimeFaces.current().ajax().update("frmRechazoRips");
        PrimeFaces.current().resetInputs("frmRechazoRips");
        PrimeFaces.current().executeScript("PF('dialogoRechazoRips').show()");
    }

    public void buscarContratos() {
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_ADICIONAL_3);
        getCmRipsCargaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerContratos:pVerContratos");
        PrimeFaces.current().ajax().update("frmVerContratos:tablaContratos");
        procesoFinal();
    }

    public void buscarDevoluciones() {
        super.setAccion(ACCION_ADICIONAL_4);
        getCmRipsCargaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerDevoluciones:tablaDevoluciones");
        procesoFinal();
    }

    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getCmRipsCargaServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide()");
        }
        procesoFinal();
    }

    public void borrar(int _id) {
//        getObjeto().setId(_id);
//        super.setAccion(ACCION_BORRAR);
//        getCmRipsCargaServicio().Accion(this);
//        procesoFinal();
    }

    private String asignarRangoPrestacion() {
        String rangoFinal;
        int rangoDescuento = RANGO_FECHA_PRESTACION;
        String rangoConfigurado = PropApl.getInstance().get(PropApl.CM_RIPS_RANGO_FECHA_CARGA);
        try {
            if (rangoConfigurado != null) {
                rangoDescuento = Integer.parseInt(rangoConfigurado);
            }
        } catch (NumberFormatException e) {
            rangoDescuento = RANGO_FECHA_PRESTACION;
        }
        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        int rangoInicial = anioActual - rangoDescuento;
        rangoFinal = "" + rangoInicial + ":" + anioActual;

        return rangoFinal;
    }

    private boolean validarFechaPrestacion(Date fechaPrestacion) {
        boolean esValido = true;
        Calendar calendar = Calendar.getInstance();
        try {
            if (fechaPrestacion != null) {
                calendar.setTime(fechaPrestacion);

                int annioActual = Calendar.getInstance().get(Calendar.YEAR);
                int annioPrestacion = calendar.get(Calendar.YEAR);

                int mesActual = Calendar.getInstance().get(Calendar.MONTH);
                int mesPrestacion = calendar.get(Calendar.MONTH);

                if (annioActual == annioPrestacion && mesPrestacion > mesActual) {
                    addError("El mes de fecha prestación no debe ser mayor al mes actual del año en curso.");
                    esValido = false;
                }
            }
        } catch (Exception e) {
            esValido = true;
        }
        return esValido;
    }

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_GUARDAR:
                    crearLog("Rips - Crear Carga", getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmRipsCargas");
                    break;
                case Url.ACCION_MODIFICAR:
                    crearLog("Rips - Modificar Carga", getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmRipsCargas");
                    break;
                case Url.ACCION_BORRAR:
                    crearLog("Rips - Borrar Carga", getObjeto().toString());
                    break;
                case Url.ACCION_LISTAR:
                    crearLog("Rips - Listar Carga", getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmRipsCargas");
                    break;
                case Url.ACCION_VER:
                    switch (getDoAccion()) {
                        case Url.ACCION_ADICIONAL_1:
                            crearLog("Rips - Ver Errores de Estructura", getObjeto().toString());
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            crearLog("Rips - Ver Sucesos", getObjeto().toString());
                            break;
                        case Url.ACCION_ADICIONAL_3:
                            crearLog("Rips - Ver Contratos", getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmVerContratos");
                            PrimeFaces.current().ajax().update("frmVerContratos:pVerContratos");
                            PrimeFaces.current().ajax().update("frmVerContratos:tablaContratos");
                            PrimeFaces.current().executeScript("PF('dialogoVerContratos').show()");
                            break;
                        case Url.ACCION_ADICIONAL_4:
                            crearLog("Rips - Ver Adjuntos Carga", getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                    crearLog("Rips - Crear", getObjeto().toString());
                    break;
                case Url.ACCION_EDITAR:
                    crearLog("Rips - Editar Carga", getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1:
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (getDoAccion()) {
                        case Url.ACCION_ADICIONAL_1:
                            crearLog("Rips - Gestionar", getObjeto().toString());
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            crearLog("Rips - Enviar Auditoria", getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    crearLog("Rips - Rechazar Rips", getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmRipsCargas");
                    break;
                case Url.ACCION_ADICIONAL_4:
                    crearLog("Rips - Ver Devoluciones", getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_5:
                    crearLog("Rips - Cambiar Estado Carga", getObjeto().toString());
                    break;
            }
        }
        generarMensajes();
    }

    public void seleccionarContrato(SelectEvent<CntContratoSede> obj) {

        getObjeto().setCntContratoSede(obj.getObject());
        getObjeto().setMaeContratoModalidadId(obj.getObject().getMaeModalidadContratoId());

        if (obj.getObject().getAplicaContribuitivo() != null) {
            //TODO BUSCAR MAE
            if (obj.getObject().getAplicaContribuitivo()) {
                getObjeto().setMaeRegimenId(9305);
            }
        }
        if (obj.getObject().getAplicaSubsidiado() != null) {
            //TODO BUSCAR MAE
            if (obj.getObject().getAplicaSubsidiado()) {
                getObjeto().setMaeRegimenId(9304);
            }
        }
        PrimeFaces.current().ajax().update("frmCrear:contrato");
        PrimeFaces.current().ajax().update("frmCrear:regimen");
        PrimeFaces.current().ajax().update("frmCrear:modalidad");
        PrimeFaces.current().executeScript("PF('dialogoVerContratos').hide()");

    }

    private boolean validarFechaEnVigenciaContrato(Date fechaInicio, Date fechaFin, Date fechaPrestacion) {

        boolean fechaValida = false;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateFechaFinString = sdf.format(fechaFin);
            fechaFin = sdf.parse(dateFechaFinString);

            String dateFechaInicioString = sdf.format(fechaInicio);
            fechaInicio = sdf.parse(dateFechaInicioString);

            String dateFechaPrestacionString = sdf.format(fechaPrestacion);
            fechaPrestacion = sdf.parse(dateFechaPrestacionString);

            if (fechaInicio.compareTo(fechaPrestacion) <= 0 && fechaFin.compareTo(fechaPrestacion) >= 0) {
                fechaValida = true;
            }
        } catch (Exception e) {
        }
        return fechaValida;

    }

    private boolean validarExistenciaFechaPrestacion(Date obj) {
        boolean fechaValida = true;
        if (obj == null) {
            fechaValida = false;
        }
        return fechaValida;
    }

    public void enviarAuditoria() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_ADICIONAL_2);
        getCmRipsCargaServicio().Accion(this);
        PrimeFaces.current().executeScript("PF('dialogoGestionar').hide()");
        procesoFinal();
    }

    public void inicializarTablaDevoluciones() {
        lazyDevoluciones = new LazyDataModel<CmAuditoriaDevolucion>() {

            private List<CmAuditoriaDevolucion> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaDevoluciones().getCantidadRegistros();
            }

            @Override
            public List<CmAuditoriaDevolucion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaDevoluciones().setPrimerRegistro(primerRegistro);
                getParamConsultaDevoluciones().setRegistrosPagina(registrosPagina);
                getParamConsultaDevoluciones().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaDevoluciones().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarDevolucion();
                lista = getListaAuditoriaDevoluciones();
                setRowCount(getParamConsultaDevoluciones().getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(CmAuditoriaDevolucion objeto) {
                return objeto.getId().toString();
            }

            @Override
            public CmAuditoriaDevolucion getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (CmAuditoriaDevolucion objeto : lista) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };
    }

    public void verSucesos(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_ADICIONAL_2);
        getCmRipsCargaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerSucesos");
        PrimeFaces.current().ajax().update("frmVerSucesos:tablaSucesos");
        PrimeFaces.current().executeScript("PF('dialogoVerSucesos').show()");
        procesoFinal();
    }

    public void verDetalleFactura(CmRipsAfFactura cmRipsAfFactura) {
        try {
            setFactura(cmRipsAfFactura);
            getParamConsultaDetalles().setParametroConsulta1(cmRipsAfFactura.getCmRipsCarga().getId());
            getParamConsultaDetalles().setParametroConsulta2(cmRipsAfFactura.getNumeroFactura());
            getParamConsultaDetalles().setCantidadRegistros(getCmRipsCargaRemoto().consultarCantidadFacturaDetalles(getParamConsultaDetalles()));
            setListaCmRipsCargaFacturaDetalle(getCmRipsCargaRemoto().consultarFacturaDetalles(getParamConsultaDetalles()));
        } catch (Exception ex) {
            addError("Error: " + ex.toString());
        }
        PrimeFaces.current().ajax().update("frmVerDetalleFactura:pDetalleFactura");
        PrimeFaces.current().ajax().update("frmVerDetalleFactura:tablaDetalleFactura");
        PrimeFaces.current().executeScript("PF('dialogoVerDetalleFactura').show()");
    }

    public void refrescarFacturaDetalle() {
        try {
            getParamConsultaDetalles().setCantidadRegistros(getCmRipsCargaRemoto().consultarCantidadFacturaDetalles(getParamConsultaDetalles()));
            setListaCmRipsCargaFacturaDetalle(getCmRipsCargaRemoto().consultarFacturaDetalles(getParamConsultaDetalles()));
        } catch (Exception ex) {
            addError(ex.toString());
        }
        generarMensajes();
    }

    /*----------------------
    --------ADJUNTOS--------
    ------------------------*/
    public void adicionarAdjunto(FileUploadEvent event) throws IOException {
        String ruta = PropApl.getInstance().get(PropApl.CM_RUTA_RIPS_CARGA);
        UploadedFile archivoCarga = event.getFile();
        String nombre = archivoCarga.getFileName();
        InputStream inputStream = archivoCarga.getInputStream();
        int idInsertar = 0;
        boolean insertarAdjunto = true;
        //Validar que no este repetido
        for (CmRipsCargaAnexo adj : listaAdjuntos) {
            if (adj.getIdInsertar() != null && adj.getIdInsertar() >= idInsertar) {
                if (adj.getArchivoNombre().equals(nombre)) {
                    addError("Error archivo " + nombre + " repetido");
                    insertarAdjunto = false;
                }
            }
        }
        if (insertarAdjunto) {
            CmRipsCargaAnexo adjNuevo = new CmRipsCargaAnexo(getAdjuntoTipo(nombre), nombre, ruta, inputStream);
            //Validar que sea un tipo valido de archivo

            if (adjNuevo.getMaeTipoArchivoId() == -1) {
                addError("Error: Archivo " + nombre + " no inicia con un prefijo válido.");
                generarMensajes();
                return;
            }

            if (!Pattern.matches("^[a-zA-Z]{2}[0-9]{6,6}(?!\\d)", nombre.substring(0, nombre.length() - 4))) {
                addError("Error el archivo debe iniciar por dos letras seguido por 6 números: " + nombre + "");
                generarMensajes();
                return;
            }

            String prefijoArchivoAgregar = nombre.substring(0, 2).toLowerCase();
            if (validarArchivoConPrefijo(listaAdjuntos, prefijoArchivoAgregar)) {
                addError("Error: El archivo con prefijo " + prefijoArchivoAgregar + " ya ha sido agregado.");
                generarMensajes();
                return; 
            }

            adjNuevo.setUsuarioCrea(getConexion().getUsuario().getUsuarioNombre());
            adjNuevo.setTerminalCrea(getConexion().getIp());
            adjNuevo.setFechaHoraCrea(new Date());
            adjNuevo.setArchivoNombreOriginal(nombre);
            adjNuevo.setIdInsertar(++idInsertar);
            listaAdjuntos.add(adjNuevo);
        }
        generarMensajes();
    }

    public void retirarAdjunto(String nombreArchivo) {
        if (!"".equals(nombreArchivo)) {
            for (int i = 0; i < listaAdjuntos.size(); i++) {
                CmRipsCargaAnexo adj = listaAdjuntos.get(i);
                if (adj.getIdInsertar() != null && adj.getArchivoNombre() == nombreArchivo) {
                    listaAdjuntos.remove(i);
                    break;
                }
            }
        }
        PrimeFaces.current().ajax().update("frmCrear:tablaAdjuntos");
    }

    public void borrarAdjunto(int id) {
        for (int i = 0; i < listaAdjuntos.size(); i++) {
            CmRipsCargaAnexo adj = listaAdjuntos.get(i);
            if (adj.getId() != null && adj.getId() == id) {
                listaAdjuntos.remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmCrear:tablaAdjuntos");
    }

    public void descargarAdjunto(com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCargaAnexo adj) throws IOException {
        String rutaCompleta = adj.getArchivoRuta() + adj.getArchivoNombre();
        FileInputStream fis = null;
        OutputStream output = null;
        try {
            File file = new File(rutaCompleta);
            byte[] exportContent = new byte[(int) file.length()];
            fis = new FileInputStream(file);
            fis.read(exportContent);
            int i = rutaCompleta.lastIndexOf(".");
            String ext = rutaCompleta.substring(i, rutaCompleta.length());
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.responseReset();
            ec.setResponseContentLength(exportContent.length);
            String attachmentName = "attachment; filename=\"" + adj.getArchivoNombreOriginal() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".txt")) {
                ec.setResponseContentType("application/txt");
            } else {
                throw new Exception();
            }
            output = ec.getResponseOutputStream();
            output.write(exportContent);
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (output != null) {
                output.close();
            }
        }
        crearLog("Rips - Descargar Adjuntos", getObjeto().toString());
        procesoFinal();
    }

    /*----------------------
    -----GETTERS SETTERS------
    ------------------------*/
    public CmRipsCargaServicioIface getCmRipsCargaServicio() {
        return cmRipsCargaServicio;
    }

    public void setCmRipsCargaServicio(CmRipsCargaServicioIface cmRipsCargaServicio) {
        this.cmRipsCargaServicio = cmRipsCargaServicio;
    }

    public List<CmRipsCarga> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CmRipsCarga> registros) {
        this.registros = registros;
    }

    public CmRipsCarga getObjeto() {
        return objeto;
    }

    public void setObjeto(CmRipsCarga objeto) {
        this.objeto = objeto;
    }

    public LazyDataModel<CmRipsCarga> getLazyCmRips() {
        return lazyCmRips;
    }

    public void setLazyCmRips(LazyDataModel<CmRipsCarga> lazyCmRips) {
        this.lazyCmRips = lazyCmRips;
    }

    @Override
    public int getTamanoPagina() {
        return tamanoPagina;
    }

    @Override
    public void setTamanoPagina(int tamanoPagina) {
        this.tamanoPagina = tamanoPagina;
    }

    public List<CmRipsCargaAnexo> getListaAdjuntos() {
        return listaAdjuntos;
    }

    public void setListaAdjuntos(List<CmRipsCargaAnexo> listaAdjuntos) {
        this.listaAdjuntos = listaAdjuntos;
    }

    public String getMansajeGeneral() {
        return mansajeGeneral;
    }

    public void setMansajeGeneral(String mansajeGeneral) {
        this.mansajeGeneral = mansajeGeneral;
    }

    private int getAdjuntoTipo(String nombre) {
        String prefijo = nombre.substring(0, 2);
        int tipoId = 0;
        switch (prefijo) {
            case (CmRipsCargaAnexo.AC):
                tipoId = CmRipsCargaAnexo.TIPO_AC;
                break;
            case (CmRipsCargaAnexo.AF):
                tipoId = CmRipsCargaAnexo.TIPO_AF;
                break;
            case (CmRipsCargaAnexo.AD):
                tipoId = CmRipsCargaAnexo.TIPO_AD;
                break;
            case (CmRipsCargaAnexo.AM):
                tipoId = CmRipsCargaAnexo.TIPO_AM;
                break;
            case (CmRipsCargaAnexo.AP):
                tipoId = CmRipsCargaAnexo.TIPO_AP;
                break;
            case (CmRipsCargaAnexo.AT):
                tipoId = CmRipsCargaAnexo.TIPO_AT;
                break;
            case (CmRipsCargaAnexo.AU):
                tipoId = CmRipsCargaAnexo.TIPO_AU;
                break;
            case (CmRipsCargaAnexo.AN):
                tipoId = CmRipsCargaAnexo.TIPO_AN;
                break;
            case (CmRipsCargaAnexo.AH):
                tipoId = CmRipsCargaAnexo.TIPO_AH;
                break;
            case (CmRipsCargaAnexo.CT):
                tipoId = CmRipsCargaAnexo.TIPO_CT;
                break;
            case (CmRipsCargaAnexo.US):
                tipoId = CmRipsCargaAnexo.TIPO_US;
                break;
            default:
                break;
        }
        return tipoId;
    }

    public List<CmRipsEstructuraError> getRegistrosEstructuraError() {
        return registrosEstructuraError;
    }

    public void setRegistrosEstructuraError(List<CmRipsEstructuraError> registrosEstructuraError) {
        this.registrosEstructuraError = registrosEstructuraError;
    }

    public LazyDataModel<CmRipsEstructuraError> getLazyCmRipsEstructuraError() {
        return lazyCmRipsEstructuraError;
    }

    public void setLazyCmRipsEstructuraError(LazyDataModel<CmRipsEstructuraError> lazyCmRipsEstructuraError) {
        this.lazyCmRipsEstructuraError = lazyCmRipsEstructuraError;
    }

    public List<CmRipsEstructuraError> getListaErroresEstructura() {
        return listaErroresEstructura;
    }

    public void setListaErroresEstructura(List<CmRipsEstructuraError> listaErroresEstructura) {
        this.listaErroresEstructura = listaErroresEstructura;
    }

    public Exporter<DataTable> getTextExporter() {
        return textExporter;
    }

    public void setTextExporter(Exporter<DataTable> textExporter) {
        this.textExporter = textExporter;
    }

    public CntPrestadorSede getObjetoPrestadorSede() {
        return objetoPrestadorSede;
    }

    public void setObjetoPrestadorSede(CntPrestadorSede objetoPrestadorSede) {
        this.objetoPrestadorSede = objetoPrestadorSede;
    }

    public String getCntBuscadorContrato() {
        return cntBuscadorContrato;
    }

    public void setCntBuscadorContrato(String cntBuscadorContrato) {
        this.cntBuscadorContrato = cntBuscadorContrato;
    }

    public List<CntContratoSede> getListaContratos() {
        if(listaContratos==null){
            listaContratos = new ArrayList<>();
        }
        return listaContratos;
    }

    public void setListaContratos(List<CntContratoSede> listaContratos) {
        this.listaContratos = listaContratos;
    }

    public ParamConsulta getParamConsultaContratos() {
        if (paramConsultaContratos == null) {
            paramConsultaContratos = new ParamConsulta();
        }
        return paramConsultaContratos;
    }

    public void setParamConsultaContratos(ParamConsulta paramConsultaContratos) {
        this.paramConsultaContratos = paramConsultaContratos;
    }

    public LazyDataModel<CntContratoSede> getLazyContratos() {
        return lazyContratos;
    }

    public void setLazyContratos(LazyDataModel<CntContratoSede> lazyContratos) {
        this.lazyContratos = lazyContratos;
    }

    public CntContratoSede getCntContratoSede() {
        return cntContratoSede;
    }

    public void setCntContratoSede(CntContratoSede cntContratoSede) {
        this.cntContratoSede = cntContratoSede;
    }

    public CmRipsAfFactura getFactura() {
        return factura;
    }

    public void setFactura(CmRipsAfFactura factura) {
        this.factura = factura;
    }

    public List<CmRipsCargaDetalleDTO> getListaCmRipsCargaFacturaDetalle() {
        return listaCmRipsCargaFacturaDetalle;
    }

    public void setListaCmRipsCargaFacturaDetalle(List<CmRipsCargaDetalleDTO> listaCmRipsCargaFacturaDetalle) {
        this.listaCmRipsCargaFacturaDetalle = listaCmRipsCargaFacturaDetalle;
    }

    public List<CmRipsSuceso> getListaCmRipsSucesos() {
        return listaCmRipsSucesos;
    }

    public void setListaCmRipsSucesos(List<CmRipsSuceso> listaCmRipsSucesos) {
        this.listaCmRipsSucesos = listaCmRipsSucesos;
    }

    public LazyDataModel<CmRipsAfFactura> getLazyFacturas() {
        return lazyFacturas;
    }

    public void setLazyFacturas(LazyDataModel<CmRipsAfFactura> lazyFacturas) {
        this.lazyFacturas = lazyFacturas;
    }

    public HashMap<Integer, Maestro> getHashRegimenes() {
        return hashRegimenes;
    }

    public void setHashRegimenes(HashMap<Integer, Maestro> hashRegimenes) {
        this.hashRegimenes = hashRegimenes;
    }

    public HashMap<Integer, Maestro> getHashRegionales() {
        return hashRegionales;
    }

    public void setHashRegionales(HashMap<Integer, Maestro> hashRegionales) {
        this.hashRegionales = hashRegionales;
    }

    public HashMap<Integer, Maestro> getHashModalidadContratos() {
        return hashModalidadContratos;
    }

    public void setHashModalidadContratos(HashMap<Integer, Maestro> hashModalidadContratos) {
        this.hashModalidadContratos = hashModalidadContratos;
    }

    public HashMap<Integer, Maestro> getHashRechazos() {
        return hashRechazos;
    }

    public void setHashRechazos(HashMap<Integer, Maestro> hashRechazos) {
        this.hashRechazos = hashRechazos;
    }

    public static Object getMapMaestroValue(Map map, Object key) {
        Maestro maestro = (Maestro) map.get(key);
        return maestro;
    }

    public CntPrestador getObjetoPrestador() {
        return objetoPrestador;
    }

    public void setObjetoPrestador(CntPrestador objetoPrestador) {
        this.objetoPrestador = objetoPrestador;
    }

    public LazyDataModel<CmAuditoriaDevolucion> getLazyDevoluciones() {
        return lazyDevoluciones;
    }

    public void setLazyDevoluciones(LazyDataModel<CmAuditoriaDevolucion> lazyDevoluciones) {
        this.lazyDevoluciones = lazyDevoluciones;
    }

    public List<CmAuditoriaDevolucion> getListaAuditoriaDevoluciones() {
        return listaAuditoriaDevoluciones;
    }

    public void setListaAuditoriaDevoluciones(List<CmAuditoriaDevolucion> listaAuditoriaDevoluciones) {
        this.listaAuditoriaDevoluciones = listaAuditoriaDevoluciones;
    }

    public ParamConsulta getParamConsultaDevoluciones() {
        if (paramConsultaDevoluciones == null) {
            paramConsultaDevoluciones = new ParamConsulta();
        }
        return paramConsultaDevoluciones;
    }

    public void setParamConsultaDevoluciones(ParamConsulta paramConsultaDevoluciones) {
        this.paramConsultaDevoluciones = paramConsultaDevoluciones;
    }

    public CmAuditoriaDevolucion getDevolucion() {
        return devolucion;
    }

    public void setDevolucion(CmAuditoriaDevolucion devolucion) {
        this.devolucion = devolucion;
    }

    public int getTotalDevoluciones() {
        return totalDevoluciones;
    }

    public void setTotalDevoluciones(int totalDevoluciones) {
        this.totalDevoluciones = totalDevoluciones;
    }

    public LazyDataModel<CmRipsCargaDetalleDTO> getLazyDetalles() {
        return lazyDetalles;
    }

    public void setLazyDetalles(LazyDataModel<CmRipsCargaDetalleDTO> lazyDetalles) {
        this.lazyDetalles = lazyDetalles;
    }

    public ParamConsulta getParamConsultaDetalles() {
        if (paramConsultaDetalles == null) {
            paramConsultaDetalles = new ParamConsulta();
        }
        return paramConsultaDetalles;
    }

    public void setParamConsultaDetalles(ParamConsulta paramConsultaDetalles) {
        this.paramConsultaDetalles = paramConsultaDetalles;
    }

    public LazyDataModel<CmRipsSuceso> getLazyCmRipsSucesos() {
        return lazyCmRipsSucesos;
    }

    public void setLazyCmRipsSucesos(LazyDataModel<CmRipsSuceso> lazyCmRipsSucesos) {
        this.lazyCmRipsSucesos = lazyCmRipsSucesos;
    }

    public ParamConsulta getParamConsultaSucesos() {
        if (paramConsultaSucesos == null) {
            paramConsultaSucesos = new ParamConsulta();
        }
        return paramConsultaSucesos;
    }

    public void setParamConsultaSucesos(ParamConsulta paramConsultaSucesos) {
        this.paramConsultaSucesos = paramConsultaSucesos;
    }

    public String getNitPrestador() {
        return nitPrestador;
    }

    public void setNitPrestador(String nitPrestador) {
        this.nitPrestador = nitPrestador;
    }

    public ParamConsulta getParamConsultaPrestador() {
        return paramConsultaPrestador;
    }

    public void setParamConsultaPrestador(ParamConsulta paramConsultaPrestador) {
        this.paramConsultaPrestador = paramConsultaPrestador;
    }

    public void refrescarSedes() {
        getCmRipsCargaServicio().listarPrestadoresYSedes(this);
    }

    public void consultarSedes() {
        try {
            DataTable dataTable2 = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmPrestadorLista:tablaRegistrosIps");
            dataTable2.setFilterBy(null);
            dataTable2.reset();
            PrimeFaces.current().ajax().update("frmPrestadorLista:hPanelIps");
            this.setParamConsultaPrestador(new ParamConsulta());
            this.getParamConsultaPrestador().setEmpresaId(super.getConexion().getEmpresa().getId());
            PrimeFaces.current().ajax().update("frmPrestadorLista");
            PrimeFaces.current().executeScript("PF('dialogoIpsLista').show()");
        } catch (Exception ex) {
            addError(ex.getMessage());
        }
    }

    public List<CntPrestadorSede> getListaPrestadorSedes() {
        return listaPrestadorSedes;
    }

    public void setListaPrestadorSedes(List<CntPrestadorSede> listaPrestadorSedes) {
        this.listaPrestadorSedes = listaPrestadorSedes;
    }

    public LazyDataModel<CntPrestador> getLazyPrestadores() {
        return lazyPrestadores;
    }

    public void setLazyPrestadores(LazyDataModel<CntPrestador> lazyPrestadores) {
        this.lazyPrestadores = lazyPrestadores;
    }

    public LazyDataModel<CntPrestadorSede> getLazySedes() {
        return lazySedes;
    }

    public void setLazySedes(LazyDataModel<CntPrestadorSede> lazySedes) {
        this.lazySedes = lazySedes;
    }

    public List<Maestro> getListaTiposDocumento() {
        return listaTiposDocumento;
    }

    public void setListaTiposDocumento(List<Maestro> listaTiposDocumento) {
        this.listaTiposDocumento = listaTiposDocumento;
    }

    public String obtenerDepartamento(int id) {
        try {
            int idPadre = hashUbicacion.get(id).getUbicacionPadre().getId();
            return hashUbicacionDepartamento.get(idPadre).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public HashMap<Integer, Ubicacion> getHashUbicacion() {
        return hashUbicacion;
    }

    public void setHashUbicacion(HashMap<Integer, Ubicacion> hashUbicacion) {
        this.hashUbicacion = hashUbicacion;
    }

    public HashMap<Integer, Ubicacion> getHashUbicacionDepartamento() {
        return hashUbicacionDepartamento;
    }

    public void setHashUbicacionDepartamento(HashMap<Integer, Ubicacion> hashUbicacionDepartamento) {
        this.hashUbicacionDepartamento = hashUbicacionDepartamento;
    }

    public List<Maestro> getListaTiposDocumentoEmpresa() {
        return listaTiposDocumentoEmpresa;
    }

    public void setListaTiposDocumentoEmpresa(List<Maestro> listaTiposDocumentoEmpresa) {
        this.listaTiposDocumentoEmpresa = listaTiposDocumentoEmpresa;
    }

    public List<Ubicacion> getListaUbicacion() throws Exception {
        return listaUbicacion;
    }

    public void setListaUbicacion(List<Ubicacion> listaUbicacion) {
        this.listaUbicacion = listaUbicacion;
    }

    public boolean isDeshabilitarNoPbs() {
        return deshabilitarNoPbs;
    }

    public void setDeshabilitarNoPbs(boolean deshabilitarNoPbs) {
        this.deshabilitarNoPbs = deshabilitarNoPbs;
    }

    public void onRowSelectIps(SelectEvent event) {
        try {
            setObjetoPrestadorSede((CntPrestadorSede) event.getObject());

//            if (getObjetoPrestadorSede().getFechaFacturaElectronica() != null) {
//                addError("La sede del prestador ya cuenta con registros de facturación electrónica");
//                PrimeFaces.current().ajax().update("frmPrestadorLista");
//                PrimeFaces.current().ajax().update("frmPrestadorLista:panelIpsLista");
//                generarMensajes();
//                return ;
//            }
            getObjeto().setGnPrestadorSede(getObjetoPrestadorSede());
            setObjetoPrestador(getObjetoPrestadorSede().getCntPrestador());
            PrimeFaces.current().ajax().update("frmCrear:prestador");
            PrimeFaces.current().ajax().update("frmCrear:sede");
            PrimeFaces.current().executeScript("PF('dialogoIpsLista').hide()");
            PrimeFaces.current().executeScript("PF('tablaWidgetIPS').clearFilters()");
            PrimeFaces.current().executeScript("PF('tablaWidgetIPS').unselectAllRows()");
            PrimeFaces.current().resetInputs("frmPrestadorLista:tablaRegistrosIps");
            PrimeFaces.current().resetInputs("frmPrestadorLista:panelIpsLista");
            PrimeFaces.current().ajax().update("frmPrestadorLista:tablaRegistrosIps");
            PrimeFaces.current().ajax().update("frmPrestadorLista");
            PrimeFaces.current().ajax().update("frmPrestadorLista:panelIpsLista");
        } catch (Exception ex) {
            addError("Ocurrio un error al seleccionar la IPS: " + ex.toString());
        }
    }

    public String obtenerMunicipio(int id) {
        try {
            return hashUbicacion.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public List<CmRipsCargaAnexo> getListaAnexos() {
        return listaAnexos;
    }

    public void setListaAnexos(List<CmRipsCargaAnexo> listaAnexos) {
        this.listaAnexos = listaAnexos;
    }

    public String getRangoFechaPrestacion() {
        return rangoFechaPrestacion;
    }

    public void setRangoFechaPrestacion(String rangoFechaPrestacion) {
        this.rangoFechaPrestacion = rangoFechaPrestacion;
    }

    public void salir() {
        this.objeto = null;
        FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.removeAttribute("com.sun.faces.renderkit.ServerSideStateHelper.LogicalViewMap");
        session.removeAttribute("com.sun.faces.application.view.activeViewMaps");
        session.removeAttribute("cmRipsCargaBean");
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("cmRipsCargaBean");
    }

    public String recortarMensaje(String mensaje) {
        int TAMANIO_MAXIMO_MENSAJE = 20;
        if (mensaje != null && mensaje.length() >= TAMANIO_MAXIMO_MENSAJE) {
            return mensaje.substring(0, TAMANIO_MAXIMO_MENSAJE) + "... ";
        } else {
            return mensaje;
        }
    }

    public void mostrarMensaje(String mensaje) {
        setMansajeGeneral(mensaje);
        PrimeFaces.current().resetInputs("frmMensaje");
        PrimeFaces.current().ajax().update("frmMensaje");
        PrimeFaces.current().executeScript("PF('dlgMsg').show();");
    }

    public void actualizarValoresCargaRips() {
        getObjeto().setCntContratoSede(new CntContratoSede ());
        getObjeto().setMaeContratoModalidadId(null);
        getObjeto().setMaeRegimenId(null);
        PrimeFaces.current().ajax().update("frmCrear:contrato");
    }

     public boolean validarExistenciaContratosVigentes(){
        super.setAccion(ACCION_VER);
        super.setDoAccion(ACCION_VALIDAR_CONTRATOS_VIGENTES);
        getCmRipsCargaServicio().Accion(this);
        return getObjeto().getTieneContratosActivos();
    }

     public boolean validarNoHayContratoSeleccionado(){ 
        CntContratoSede contratoSede =  Optional.ofNullable(getObjeto().getCntContratoSede()).orElse(new CntContratoSede());
        CntContrato contrato =  Optional.ofNullable(contratoSede.
                getCntContrato()).orElse(new CntContrato());
        return contrato.getId() == null;
    }

    public boolean validarFechaPrestacionRips(SelectEvent<CntContratoSede> obj, Date fechaPrestacion) {
        boolean fechaValida = true;
        String mensaje = "";
        if (!validarExistenciaFechaPrestacion(fechaPrestacion)) {
            mensaje = ("Para seleccionar el contrato debe ingresar la fecha de prestación.");
            fechaValida = false;
        }

         CntContrato contrato =  Optional.ofNullable(obj.getObject().getCntContrato()).orElse(new CntContrato());
         Date fechaFin = contrato .getFechaFin();
         Date fechaInicio = contrato .getFechaInicio();

        if (fechaValida && !validarFechaEnVigenciaContrato(fechaInicio, fechaFin, fechaPrestacion)) {
             mensaje = ("Fecha prestación no esta en la fecha de vigencia del contrato. Esta debe estar entre ( "+fechaInicio.toString()+" hasta "+ fechaFin.toString()+" )");
            fechaValida = false;
        }

        if (!fechaValida) {
            addError(mensaje);
            generarMensajes();
        }

        return fechaValida;
    }

    public void deshabilitarCoberturaSegunModalidad() {

        this.setDeshabilitarNoPbs(false);
        Maestro meatro = getHashModalidadContratos().get(getObjeto().getMaeContratoModalidadId());

        if (meatro != null && meatro.getNombre() != null) {
            desHabilitarCoberturaParaMotivoCapita(meatro.getNombre());
        }
        PrimeFaces.current().ajax().update("frmCrear:pbs");

    }

    public void desHabilitarCoberturaParaMotivoCapita(String nombreModalidadContrato){
        this.setDeshabilitarNoPbs(false);
        if (nombreModalidadContrato != null && nombreModalidadContrato.toUpperCase().equals("CAPITA")) {
            this.setDeshabilitarNoPbs(true);
        }
    }
    
    private boolean validarArchivoConPrefijo(List<CmRipsCargaAnexo> listaAdjuntos, String prefijo) {
        return listaAdjuntos.stream()
                .map(adj -> adj.getArchivoNombre().substring(0, 2).toLowerCase())
                .anyMatch(prefijoObtenido -> prefijoObtenido.equals(prefijo));
    }
}
