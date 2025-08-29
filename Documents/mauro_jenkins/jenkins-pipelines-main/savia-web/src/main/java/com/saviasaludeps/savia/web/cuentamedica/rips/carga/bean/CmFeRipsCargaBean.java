package com.saviasaludeps.savia.web.cuentamedica.rips.carga.bean;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoSede;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmAuditoriaDevolucion;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupo;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupoUsuario;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCarga;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCargaAdjunto;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeSoporte;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeTransaccion;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsAfFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCargaDetalleDTO;
import com.saviasaludeps.savia.web.cuentamedica.rips.carga.servicio.CmFeRipsCargaServicioIface;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsEstructuraError;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsSuceso;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmRipsCargaRemoto;
import com.saviasaludeps.savia.web.cuentamedica.rips.DTO.CmFeRipsCargaFacturaDTO;
import com.saviasaludeps.savia.web.cuentamedica.rips.DTO.CmFeRipsCargaUsuarioDTO;
import com.saviasaludeps.savia.web.cuentamedica.rips.carga.servicio.CmFeRipsCargaServicioImpl;
import com.saviasaludeps.savia.web.cuentamedica.utilidades.CmFeAdjuntoErrores;
import com.saviasaludeps.savia.web.cuentamedica.utilidades.CmUtilidades;
import com.saviasaludeps.savia.web.utilidades.Url;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.primefaces.PrimeFaces;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.FilterMeta;
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
import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.util.Base64;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.primefaces.event.ToggleSelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean
@ViewScoped
public class CmFeRipsCargaBean extends Url {

    public static final char DO_ACCION_VER_CARGA = 'b';
    public static final char DO_ACCION_VER_ADJUNTOS = 'c';
    public static final char DO_ACCION_VER_GESTIONAR = 'd';
    public static final char DO_ACCION_GUARDAR_ENVIAR = 'e';
    public static final char DO_ACCION_GUARDAR_REQUISITOS_MANUALES = 'k';
    public static final char DO_ACCION_GUARDAR_ESTADO_RECUPERACION_CARGA = 'l';
    public static final char DO_ACCION_ACTUALIZAR_ATRIBUTO_FE_DOCUMENTO = 'm';
    public static final char DO_ACCION_VER_CM_FE_TRANSACCIONES = 'n';
    public static final char DO_ACCION_GUARDAR_INTENSIDAD_INDIVIDUAL = 'o';
    public static final char DO_ACCION_VER_CARGA_PERIODO_ASOCIADA = 'p';
    public static final char DO_ACCION_VER_SOPORTES = 'q';
    public static final char DO_ACCION_VER_PDF = 'r';
    public static final char DO_ACCION_VER_RADICADORES = 's';
    public static final char DO_ACCION_VER_CARGAS_ASIGNAR = 't';
    public static final char DO_ACCION_GUARDAR_ASIGNACION_RADICADOR = 'u';
    public static final char DO_ACCION_LISTAR_CARGAS_ASIGNAR = 'v';
    
    public static final int RANGO_FECHA_PRESTACION = 10;
    public static final int TIPO_CREACION_FACTURA = 1;
    public static final int TIPO_CREACION_NOTA = 2;
    
    public static final int INTENSIDAD_CARGA_INDIVIDUAL = 1;
    public static final int INTENSIDAD_CARGA_MASIVA = 2;
         
    DateFormat fechaFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public final static String FECHA_APLICACION_FE = "2024-10-01";
    
    
    public final static int TIPO_REGLA_CARGA_XML_CUV = 1;
    public final static int TIPO_REGLA_CARGA_XML_JSON_CUV = 2;
    public final static int TIPO_REGLA_CARGA_JSON_CUV = 3;
    
    public static final int INDICADOR_PENDIENTE_FE = 1;
    public static final int INDICADOR_PENDIENTE_SOPORTES = 2;
        
    private int tamanoPagina = 30;
    private CmFeRipsCargaServicioIface cmFeRipsCargaServicio;
    private List<CmFeRipsCarga> registros;
    private List<CmFeRipsCarga> registrosCargasAsignar;
    private List<CmRipsEstructuraError> registrosEstructuraError;
    private CmFeRipsCarga objeto;
    private CntPrestadorSede objetoPrestadorSede;
    private CntPrestador objetoPrestador;
    private LazyDataModel<CmFeRipsCarga> lazyCmRips;
    private LazyDataModel<CmFeRipsCarga> lazyCmCargasAsignar;
    private LazyDataModel<CmRipsEstructuraError> lazyCmRipsEstructuraError;
    private List<CmFeRipsCargaAdjunto> listaAdjuntos = new ArrayList<>();
    private List<CmFeRipsCargaAdjunto> listaAnexos;
    private List<CmFeTransaccion> listaCmFeTransacciones = new ArrayList<>();;
    private List<CmRipsEstructuraError> listaErroresEstructura;
    private List<CntPrestadorSede> listaPrestadorSedes;
    private LazyDataModel<CntPrestador> lazyPrestadores;
    private LazyDataModel<CntPrestadorSede> lazySedes;
    private LazyDataModel<CmFeTransaccion> lazyCmFeTransacciones;
    private LazyDataModel<CmFeSoporte> lazySoportes;
    private List<CmFeSoporte> listaSoportes;
    private List<Ubicacion> listaUbicacion;
    private String cntBuscadorContrato;
    private Exporter<DataTable> textExporter;
    private ParamConsulta paramConsultaCmCfeTransacciones;
    private ParamConsulta paramConsultaDetalles;
    private ParamConsulta paramConsultaPrestador;
    private ParamConsulta paramConsultaSoportes  = new ParamConsulta();
    private ParamConsulta paramConsultaCargasAsignar  = new ParamConsulta();
    private CntContratoSede cntContratoSede;
    private CmAuditoriaDevolucion devolucion;
    private CmRipsAfFactura factura;
    private List<CmRipsCargaDetalleDTO> listaCmRipsCargaFacturaDetalle;
    private List<CmRipsSuceso> listaCmRipsSucesos;
    private HashMap<Integer, Maestro> hashRegimenes;
    private HashMap<Integer, Maestro> hashRegionales;
    private HashMap<Integer, Ubicacion> hashUbicacion;
    private HashMap<Integer, Maestro> hashTipoSoporte = new HashMap<>();
    private List<Maestro> listaPeriodoCarga;
    private List<Maestro> listaMeses;
    private HashMap<Integer, Ubicacion> hashUbicacionDepartamento;
    private List<Maestro> listaTiposDocumento;
    private List<Maestro> listaMaeMotivoDevolucion;
    private int totalDevoluciones = 0;
    private String nitPrestador;
    private List<Maestro> listaTiposDocumentoEmpresa;
    private String rangoFechaPrestacion;
    private String mansajeGeneral;
    
    private String jsonContenido;
    private String tituloJsonMensaje;
    private Date fechaEnvio;
    
    private String adjuntoExtensionValida = "xml|json";
    private int adjuntoCantidadValida = 3;
    private String adjuntoExpresionValida = "/(\\.|\\/)(xml|json)$/";
    private CmFactura cmFacturaAsociadaNotas = new CmFactura();
    private boolean habilitarBotonAdjuntos = false;
    private int intensidadCarga;
    List<CmFeAdjuntoErrores> listaErroresProcesamiento = new ArrayList<>();
    Map<String, CmFeRipsCarga> listaCmFeRipsCarga = new HashMap<>();
    private int tipoReglaCarga;
    private String contenidoPDF;
    private List<CmFeRipsCarga> listaCargasSeleccionadaAsignar = new ArrayList<>();
    private List<CmGrupoUsuario> radicadores =   new ArrayList<>();
    
    private CmGrupoUsuario radicador = new CmGrupoUsuario();
    
    private HashMap<Integer, CmFeRipsCarga> hashCargasSelecionadasRadicador = new HashMap<>();
    
    private boolean todaCargasSelecionadasRadicar = false;
                            
    private CmRipsCargaRemoto getCmRipsCargaRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("CmRipsCargaFacturacionElectronicaServicio", CmRipsCargaRemoto.class.getName());
        return (CmRipsCargaRemoto) object;
    }


    @PreDestroy
    public void preDestroy() {
        this.hashUbicacion = null;
        this.objeto = null;
    }

    public CmFeRipsCargaBean() {
        this.objeto = new CmFeRipsCarga();
        Modulo _mod = super.validarModulo(Modulo.ID_CM_FE_RIPS_CARGA);
        super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
        if (_mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(_mod);
            lazyCmRips = new LazyDataModel<CmFeRipsCarga>() {

                private List<CmFeRipsCarga> lista;

                @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }
			
                @Override
                public List<CmFeRipsCarga> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(CmFeRipsCarga objeto) {
                    return objeto.getId().toString();
                }

                @Override
                public CmFeRipsCarga getRowData(String objetoId) {
                    Integer id = Integer.valueOf(objetoId);
                    for (CmFeRipsCarga objeto : lista) {
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
        getCmFeRipsCargaServicio().cargaInicial(this);
        listar();
    }

    public void listar() {
        super.setAccion(ACCION_LISTAR);
        procesoFinal();
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getCmFeRipsCargaServicio().Accion(this);
    }
    
     public void refrescarCargasAsignar() {
        super.setAccion(Url.ACCION_ADICIONAL_5);
        super.setDoAccion(DO_ACCION_LISTAR_CARGAS_ASIGNAR);
        getCmFeRipsCargaServicio().Accion(this);
    }

    public void refrescarContrato() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(DO_ACCION_VER_CM_FE_TRANSACCIONES);
        getCmFeRipsCargaServicio().Accion(this);
    }

    public void refrescarCmFeTransacciones() {
       super.setAccion(Url.ACCION_ADICIONAL_2);
       super.setDoAccion(DO_ACCION_VER_CM_FE_TRANSACCIONES);
       getCmFeRipsCargaServicio().Accion(this);
    }
    
    public void refrescarCmSoportes() {
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(DO_ACCION_VER_SOPORTES);
        getCmFeRipsCargaServicio().Accion(this);
    }

    public void crearFactura() {

        super.setAccion(ACCION_CREAR);
        setIntensidadCarga(INTENSIDAD_CARGA_INDIVIDUAL);
        getCmFeRipsCargaServicio().Accion(this);
        setRangoFechaPrestacion(asignarRangoPrestacion());

        limpiarPantallaCrearFactura();
        procesoFinal();
    }
    
    public void crearFacturasMasivas() {

        
        super.setAccion(ACCION_ADICIONAL_3);
        super.setDoAccion(ACCION_CREAR);
        setIntensidadCarga(INTENSIDAD_CARGA_MASIVA);
        getCmFeRipsCargaServicio().Accion(this);
        setRangoFechaPrestacion(asignarRangoPrestacion());

        limpiarPantallaCrearFacturaMasiva();
        procesoFinal();
    }

    public void guardar(int tipoCreacion) {

        boolean validacionCorrecta = validarPrestadorSede(getObjeto().getGnPrestadorSede());

        if (validacionCorrecta) {
             validacionCorrecta = validarSeleccionTipoCarga();
        }   
        
        if (validacionCorrecta) {
             validacionCorrecta = validarExisternciaArchivosCarga(getListaAdjuntos());
        }

        if (validacionCorrecta) {
            validarCalidadArchivos(getListaAdjuntos());
        }

        if (validacionCorrecta && this.getErrores().isEmpty()) {
            String mensaje = validarNombresCapitaPeriodoXmlJsonCuv(getListaAdjuntos());
            if (!mensaje.isEmpty()) {
                addError(mensaje);
            }
            validacionCorrecta = mensaje.isEmpty();
        }

        if (validacionCorrecta && this.getErrores().isEmpty()) {
            super.setAccion(ACCION_GUARDAR);
            super.setDoAccion(DO_ACCION_GUARDAR_INTENSIDAD_INDIVIDUAL);
            getCmFeRipsCargaServicio().Accion(this);
            if (!super.isError()) {
                PrimeFaces.current().executeScript("PF('dialogoCrearFactura').hide()");
            }
        }

        procesoFinal();
    }
    
    public void guardarFacturaMasiva() {

        setListaCmFeRipsCarga(new HashMap<>());

        boolean validacionCorrecta = validarPrestadorSede(getObjeto().getGnPrestadorSede())
                && validarSeleccionTipoCarga() && validarExistenciaAdjuntosCarga();

        if (validacionCorrecta) {
            validacionCorrecta = validarCalidadDatoZip(getListaAdjuntos());
            if (!validacionCorrecta && !getListaErroresProcesamiento().isEmpty()) {
                mostrarErroresInterfaz();
            }
        }

        if (validacionCorrecta && this.getErrores().isEmpty()) {
            super.setAccion(ACCION_ADICIONAL_3);
            super.setDoAccion(ACCION_GUARDAR);
            getCmFeRipsCargaServicio().Accion(this);
            if (super.isError() && !getListaErroresProcesamiento().isEmpty()) {
                mostrarErroresInterfaz();
            } else {
               PrimeFaces.current().executeScript("PF('dialogoCrearFacturaMasiva').hide()"); 
            }
        }

        procesoFinal();
    }
    
    public void guardarEnviar() {
        
        boolean esValidoEnvio =  validarRequisitosManualesParaEnviar();
        esValidoEnvio = esValidoEnvio ? validarRequisitosCapitaEnviar(): false ; 
//        esValidoEnvio = esValidoEnvio ? validarSoportesEnviar(): false ; 
        if ( esValidoEnvio ){
            super.setAccion(ACCION_ADICIONAL_1);
            super.setDoAccion(DO_ACCION_GUARDAR_ENVIAR);
            getCmFeRipsCargaServicio().Accion(this);
            if (!this.isError()) {       
                PrimeFaces.current().executeScript("PF('dialogoGestionar').hide()");
                this.addMensaje("El proceso de gestion se esta ejecutando. para la carga de id : "+this.getObjeto().getId());
            }
        }
        procesoFinal();
    }
    
     public void guardarRequisitosManuales() {     
         if(validarRequisitosManualesParaGuardar() ){
            super.setAccion(ACCION_ADICIONAL_1);
            super.setDoAccion(DO_ACCION_GUARDAR_REQUISITOS_MANUALES);
            getCmFeRipsCargaServicio().Accion(this);
            if (!this.isError()) {       
                PrimeFaces.current().executeScript("PF('dialogoGestionar').hide()");
                this.addMensaje("Se ha modificado la carga de  : "+this.getObjeto().getId());
            }
        }
        procesoFinal();
    }
     
    public void guardarAsigancionRadicador() {
        super.setAccion(ACCION_ADICIONAL_5);
        super.setDoAccion(DO_ACCION_GUARDAR_ASIGNACION_RADICADOR);
        getCmFeRipsCargaServicio().Accion(this);

        if (!isError()) {
            limpiarSeleccionCargasAsignar();
            PrimeFaces.current().executeScript("PF('dialogoVisualizarRadicadores').hide()");
            PrimeFaces.current().ajax().update("frmVerAsignarRadicador");
//            PrimeFaces.current().executeScript("PF('dialogoAsignarRadicador').hide()");
            this.addMensaje("Se ha realizado la asignación de manera exitosa");
        }
        
        procesoFinal();

    }

    private boolean validarRequisitosManualesParaEnviar() {
        String mensaje =  this.getObjeto().getDe4401ProfesionalRed()== null ? " Profesional Red":"";
        mensaje +=  this.getObjeto().getDe4402ProfesionalIndependiente()== null ? " , Profesional Independiente":"";
        mensaje +=  this.getObjeto().getDe5601Soportes()== null ? " , Soporte":"";
        
        if (mensaje.length()>5){
            this.addError("Para realizar el envío se debe tener seleccionado una opcion de : " +mensaje);
            return false;
        }
        return true;
    }
    
    private boolean validarRequisitosCapitaEnviar() {

        if (!this.getObjeto().esTipoCapitaPeriodo() && !this.getObjeto().esTipoCapitaInicial()) {
            return true;
        }
        if (this.getObjeto().getCargaPeriodo() == null || this.getObjeto().getCargaPeriodo().getId() == null) {
            this.addError("Para realizar el envío de este tipo de capita debe tener una carga de período asociada." );
            return false;
        }
        return true;
    }

    private boolean validarRequisitosManualesParaGuardar() {
        if (this.getObjeto().getDe4401ProfesionalRed() == null
                && this.getObjeto().getDe4402ProfesionalIndependiente() == null
                && this.getObjeto().getDe5601Soportes() == null) {
            this.addError("Para realizar el guardado debe tener seleccionado al menos una opción.");
            return false;
        }
        return true;
    }
 
    private boolean validarSoportesEnviar() {

        if (!this.getObjeto().esTipoFactura()) {
            return true;
        }
        if (this.getObjeto().getSoportesNumero() <= 0) {
            this.addError("Para realiza el envío la factura debe tener soportes");
            return false;
        }
        return true;
    }
    
    public void actualizarRequisitoFeDocumento(int idcarga) {
        getObjeto().setId(idcarga);
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(DO_ACCION_ACTUALIZAR_ATRIBUTO_FE_DOCUMENTO);
        getCmFeRipsCargaServicio().Accion(this);
        procesoFinal();
    }
    
    private boolean validarPrestadorSede(CntPrestadorSede prestadorSede) {
        boolean hayPrestador = true;
        if (prestadorSede == null) {
            addError("Seleccione un prestador para continuar con la carga.");
            hayPrestador = false;
        }
        return hayPrestador;
    }
    
    private void validarCalidadArchivos(List<CmFeRipsCargaAdjunto> anexos) {
        for (CmFeRipsCargaAdjunto anexo : anexos) {
            String fileExtension = anexo.getArchivoNombre().substring(anexo.getArchivoNombre().lastIndexOf(".") + 1);
            validarCalidadArchivoJson(fileExtension, anexo);
            validarCalidadArchivoXML(fileExtension, anexo);
            validarCalidadArchivoTxt(fileExtension, anexo);
        }
    }

    private boolean validarCalidadArchivoJson(String fileExtension, CmFeRipsCargaAdjunto anexo) {
        boolean archivoValido = true;
     
        if (!"json".equals(fileExtension)) {
            return false;
        }
        
        try {
            InputStream inputEntrada = anexo.getInputStream();
            byte[] buffer = inputEntrada.readAllBytes();
            InputStream inputUtilizar = new ByteArrayInputStream(buffer);
            InputStream inputRemplazar = new ByteArrayInputStream(buffer);
            InputStream inputStreamValidationDTO = new ByteArrayInputStream(buffer);
            anexo.setInputStream(inputRemplazar);

            String mensajeValidacion = CmUtilidades.validarJsonExtructuraBase(inputUtilizar, anexo);
            if (mensajeValidacion.isEmpty()) {
                if (CmUtilidades.esArchivoJsonRips(anexo.getArchivoNombre())) {
                    CmFeRipsCargaFacturaDTO feCargaFacturaDTO = CmUtilidades.obtenerCmFeRipsCargaFacturaDTO(inputStreamValidationDTO, anexo);
                    mensajeValidacion = validarTipoUsuarioUnicoPorFactura(feCargaFacturaDTO);
                    if (!mensajeValidacion.isEmpty()) {
                        this.addError("El archivo json (" + anexo.getArchivoNombre() + ") : " + mensajeValidacion);
                        return false;
                    }
                    mensajeValidacion = validarExistenciaCargaCapitaPeriodo(feCargaFacturaDTO);
                    if (!mensajeValidacion.isEmpty()) {
                        this.addError("El archivo json (" + anexo.getArchivoNombre() + ") : " + mensajeValidacion);
                        return false;
                    }                    
                    if (mensajeValidacion.isEmpty()) {
                        asignarMaestroRegimenCarga(this.getObjeto(), feCargaFacturaDTO);
                        asignarMultiusuario(this.getObjeto(), feCargaFacturaDTO);
                    }

                }
                return true;
            }

            this.addError("El archivo json (" + anexo.getArchivoNombre() + ") : " + mensajeValidacion);
            return false;

        } catch (JsonIOException | JsonSyntaxException | IOException ex) {
            this.addError("El archivo json (" + anexo.getArchivoNombre() + ") tiene problemas en su estructura de datos : " +obtenerErrorStrFormateado(ex));
            archivoValido = false;
        } catch (Exception ex) {
            this.addError("El archivo json (" + anexo.getArchivoNombre() + ") tiene problemas en su estructura de datos : " +obtenerErrorStrFormateado(ex));
            archivoValido = false;
        }

        return archivoValido;
    }

    private void asignarMaestroRegimenCarga(CmFeRipsCarga carga, CmFeRipsCargaFacturaDTO feCargaFacturaDTO) {
        Maestro regimen = buscarMaestroRegimenPorNombre(getHashRegimenes(), feCargaFacturaDTO.tipoRegimen);
        if (regimen != null) {
            carga.setMaeRegimenId(regimen.getId());
            carga.setMaeRegimenCodigo(regimen.getValor());
            carga.setMaeRegimenValor(regimen.getNombre());
        }
    }
    
    private void asignarMultiusuario(CmFeRipsCarga carga, CmFeRipsCargaFacturaDTO feCargaFacturaDTO) {
        carga.setMultiusuario(feCargaFacturaDTO.multiusuario);
    }
    
    private boolean validarCalidadArchivoTxt(String fileExtension, CmFeRipsCargaAdjunto anexo) {
        boolean archivoValido = true;
        if (fileExtension.equals("txt")) {
            try {
                InputStream inputEntrada = anexo.getInputStream();
                byte[] buffer = inputEntrada.readAllBytes();
                InputStream inputUtilizar = new ByteArrayInputStream(buffer);
                InputStream inputRemplazar = new ByteArrayInputStream(buffer);
                anexo.setInputStream(inputRemplazar);

                if (inputUtilizar.available() <= 0) {
                    this.addError("El archivo txt (" + anexo.getArchivoNombre() + ") esta vacio.");
                    archivoValido = false;
                }
            } catch (IOException ex) {
                this.addError("El archivo txt (" + anexo.getArchivoNombre() + ") tiene problemas en su estructura de datos : " +obtenerErrorStrFormateado(ex));
                archivoValido = false;
            }
        }
        return archivoValido;
    }
    
    private boolean validarCalidadArchivoXML(String fileExtension, CmFeRipsCargaAdjunto anexo) {
        boolean archivoValido = true;
        if (fileExtension.equals("xml")) {
            try {
                InputStream inputEntrada = anexo.getInputStream();
                byte[] buffer = inputEntrada.readAllBytes();
                InputStream inputUtilizar = new ByteArrayInputStream(buffer);
                InputStream inputRemplazar = new ByteArrayInputStream(buffer);
                anexo.setInputStream(inputRemplazar);
                
                if (!CmUtilidades.esXmlValido(inputUtilizar)) {
                    this.addError("El archivo xml (" + anexo.getArchivoNombre() + ") esta vacio o no tiene formato válido");
                    archivoValido = false;
                }

            } catch (Exception ex) {
                this.addError("El archivo xml (" + anexo.getArchivoNombre() + ") tiene problemas en su estructura o esta vacio : " + obtenerErrorStrFormateado(ex));
                archivoValido = false;
            }
        }
        return archivoValido;
    }
    
    private boolean validarSeleccionTipoCarga() {
      
        if(this.getObjeto().getTipo() == null){
         addError("Debe seleccionar un tipo de carga.");
          return false;
        }
        return true;
      }
     
    private boolean validarExistenciaAdjuntosCarga() {
        if(getListaAdjuntos().isEmpty()){
          addError("Debe agregar archivos para realizar la carga.");
          return false;
        }
        return true;
    }
    
    private boolean validarExisternciaArchivosCarga(List<CmFeRipsCargaAdjunto> anexos) {

        if (!validarExistenciaAdjuntosCarga()) {
            return false;
        }
         
        if (!validarNombresBaseIgual(anexos)) {
            return false;
        } 
           
        if (!validarReglasCargasAdjuntos(anexos, getTipoReglaCarga())) {
            return false;
        } 
        
        return true;
    }
 
    private boolean validarNombresBaseIgual(List<CmFeRipsCargaAdjunto> anexos) {
        
        if(getObjeto().esTipoCapitaPeriodo()){
            return true;
        }
        
        Set<String> nombresBase = obtenerNombreBase(anexos);
        if (nombresBase.size() != 1) {
            addError("Todos los archivos deben tener el mismo nombre base, favor validar información, encontrados:" + nombresBase);
            return false;
        }
        return true;
    }

    public boolean validarCalidadDatoZip(List<CmFeRipsCargaAdjunto> adjuntos) {

        try {
            Optional<CmFeRipsCargaAdjunto> anexoXML = adjuntos.stream()
                    .filter(obj -> "ZIP".equals(obj.getExtension())).findFirst();
            if (anexoXML.isEmpty()) {
                return false;
            }

            InputStream inputStreamZip = CmUtilidades.obtenerCopiaInputStream(anexoXML.get());

            List<CmFeAdjuntoErrores> erroresDeValidacion = procesarValidacionBasicasZip(inputStreamZip, getListaCmFeRipsCarga());
           
            if (!erroresDeValidacion.isEmpty()) {
                setListaErroresProcesamiento(erroresDeValidacion);
                getListaCmFeRipsCarga().clear();
            }

            return erroresDeValidacion.isEmpty();

        } catch (IOException ex) {
           this.addError("Error validarCalidadDatoZip : "+obtenerErrorStrFormateado(ex));
           return false;
        }
    }
    
     private String validarTipoUsuarioUnicoPorFactura(CmFeRipsCargaFacturaDTO facturaDTO) {

        if (facturaDTO == null || facturaDTO.usuarios == null || facturaDTO.usuarios.isEmpty()) {
            return "El archivo json que contiene informacion rips no comple o tiene la estructura requerida.";
        }

        String identificacionContributivo = null;
        String tipoUsuarioContributivo = null;
        String identificacionSubsidiado = null;
        String tipoUsuarioSubsidiado = null;
        Set<String> documentosUnicos = new HashSet<>();

        for (CmFeRipsCargaUsuarioDTO usuarioDTO : facturaDTO.usuarios) {
            
            String tipoUsuario = Optional.ofNullable(usuarioDTO.tipoUsuario).orElse("");

            if (tipoUsuario.equals("")) {
                return String.format("El tipo de usuario no ha sido declarado , Doc : %s , Cons : %s" ,
                       usuarioDTO.numDocumentoIdentificacion ,(int) Math.floor(usuarioDTO.consecutivo));
            }

            if (tipoUsuario.equals("01") || tipoUsuario.equals("02") || tipoUsuario.equals("03")) {
                if (identificacionContributivo == null) {
                    identificacionContributivo = usuarioDTO.numDocumentoIdentificacion;
                    tipoUsuarioContributivo = tipoUsuario;
                    facturaDTO.tipoRegimen = "contributivo";
                }
            } else if (tipoUsuario.equals("04") ) {
                if (identificacionSubsidiado == null) {
                    identificacionSubsidiado = usuarioDTO.numDocumentoIdentificacion;
                    tipoUsuarioSubsidiado = tipoUsuario;
                    facturaDTO.tipoRegimen ="subsidiado";
                }
            }else{
                facturaDTO.tipoRegimen ="N/A";
                return "El tipo de usuario no puede ser reconocido por Savia Salud EPS ( "+ 
                        tipoUsuario+" - " + usuarioDTO.numDocumentoIdentificacion + "); " ;
           }

            if (identificacionContributivo != null && identificacionSubsidiado != null) {
                facturaDTO.tipoRegimen ="N/A";
                return "La carga tiene tipos de usuario mezclados o incompatibles: contributivo ( "+ 
                        tipoUsuarioContributivo+" - " + identificacionContributivo + " ) y subsidiado u otros( " +
                        tipoUsuarioSubsidiado+" - " + identificacionSubsidiado + " ).";
            }
            documentosUnicos.add( usuarioDTO.tipoDocumentoIdentificacion + "|" + usuarioDTO.numDocumentoIdentificacion );
        }
        
        facturaDTO.multiusuario =  documentosUnicos.size() > 1;
        
        return "";
    }
     
     private String validarExistenciaCargaCapitaPeriodo(CmFeRipsCargaFacturaDTO facturaDTO) {

        if (!this.getObjeto().esTipoCapitaPeriodo() && !this.getObjeto().esTipoCapitaFinal()) {
            return "";
        }

        if (facturaDTO == null || facturaDTO.usuarios == null || facturaDTO.usuarios.isEmpty()) {
            return "El archivo json que contiene informacion rips no cumple o tiene la estructura requerida.";
        }
        
         if (facturaDTO.numFactura == null || facturaDTO.numFactura.isEmpty()) {
             return "El campo numFactura debe tener un numero válido para realizar la carga tipo capita.";
         }
        
        this.getObjeto().setFacturaNumeroPeriodoAsociado(facturaDTO.numFactura);
        super.setAccion(ACCION_VER);
        super.setDoAccion(DO_ACCION_VER_CARGA_PERIODO_ASOCIADA);
        getCmFeRipsCargaServicio().Accion(this);
        
        if(getMansajeGeneral().isEmpty()){
            return "";
        }

        return getMansajeGeneral();
    }
     
    private List<CmFeAdjuntoErrores> procesarValidacionBasicasZip(InputStream inputStreamIn,  Map<String,CmFeRipsCarga> cargasMap) {

        List<CmFeAdjuntoErrores> erroresContenidoBasicoPorAdjunto = new ArrayList<>();
        List<CmFeAdjuntoErrores> erroresTotalesArchivoZip = new ArrayList<>();

        try {

            if (!CmUtilidades.zipTieneContenido(inputStreamIn)) {
                agregarError(erroresTotalesArchivoZip, "Error General", "El archivo ZIP está vacío o no tiene contenido.");
                return erroresTotalesArchivoZip;
            }

            CmUtilidades.reiniciarInputStream(inputStreamIn);

            try (InputStream inputStream = inputStreamIn; ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {

                Set<String> nombreBaseSet = new HashSet<>();
                Set<String> nombresArchivosSet = new HashSet<>();
                ZipEntry entry;

                while ((entry = zipInputStream.getNextEntry()) != null) {
                    String fileName = entry.getName();
                    nombresArchivosSet.add(fileName);
                    String nombreBase = fileName.replaceFirst("\\"+CmUtilidades.POSTFIJO_ARCHIVO_UNICO_VALIDACION+"$|\\.json$|\\.xml$", "");
                    nombreBaseSet.add(nombreBase);
                    
                    CmFeRipsCarga carga = cargasMap.computeIfAbsent(nombreBase, k -> crearCarga(getObjeto()));

                    try (InputStream fileStream = CmUtilidades.cloneInputStream(zipInputStream)) {
                        validarContenidoBasicoArchivo(fileName, fileStream, carga)
                                .ifPresent(erroresContenidoBasicoPorAdjunto::add);
                        CmUtilidades.reiniciarInputStream(fileStream);
                        carga.getCmFeRipsCargaAdjuntos().add(crearAdjunto(fileName, fileStream));      
                    }

                    zipInputStream.closeEntry();
                }
                erroresTotalesArchivoZip.addAll(validarConjuntosArchivos(nombreBaseSet, nombresArchivosSet, cargasMap));
                erroresTotalesArchivoZip.addAll(erroresContenidoBasicoPorAdjunto);
            }
        } catch (Exception e) {
            CmFeAdjuntoErrores errorGeneral = new CmFeAdjuntoErrores();
            errorGeneral.setNombre("Error General");
            errorGeneral.setDescripcion("Error al procesar el archivo ZIP: " +obtenerErrorStrFormateado(e));
            erroresTotalesArchivoZip.add(errorGeneral);
        }

        return erroresTotalesArchivoZip;
    }

    private List<CmFeAdjuntoErrores> validarConjuntosArchivos(Set<String> nombresBase, Set<String> nombresArchivos,   Map<String, CmFeRipsCarga> cargasMap) {
        List<CmFeAdjuntoErrores> errores = new ArrayList<>();

        for (String nombreBase : nombresBase) {
            if (!(nombresArchivos.contains(nombreBase + CmUtilidades.POSTFIJO_ARCHIVO_UNICO_VALIDACION)
                    && nombresArchivos.contains(nombreBase + ".json")
                    && nombresArchivos.contains(nombreBase + ".xml"))) {
                
                String extensionFaltante = nombresArchivos.contains(nombreBase + CmUtilidades.POSTFIJO_ARCHIVO_UNICO_VALIDACION) ? 
                        "" : CmUtilidades.POSTFIJO_ARCHIVO_UNICO_VALIDACION;
                extensionFaltante += nombresArchivos.contains(nombreBase + ".json") ? "" : " .json";
                extensionFaltante += nombresArchivos.contains(nombreBase + ".xml") ? "" : " .xml";

                CmFeAdjuntoErrores adjuntoError = new CmFeAdjuntoErrores();
                adjuntoError.setNombre(nombreBase);
                adjuntoError.setDescripcion("Faltan archivos para el conjunto base: " + nombreBase + " extensiones faltantes ("+extensionFaltante+").");
                errores.add(adjuntoError);
                cargasMap.remove(nombreBase);
            }
        }

        return errores;
    }

    private Optional<CmFeAdjuntoErrores> validarContenidoBasicoArchivo(String fileName, InputStream inputStream, CmFeRipsCarga carga) {

        CmFeAdjuntoErrores adjuntoError = new CmFeAdjuntoErrores();
        adjuntoError.setNombre(fileName);

        try {
            
            byte[] buffer = inputStream.readAllBytes();
            InputStream inputStreamValidationDTO = new ByteArrayInputStream(buffer);
            inputStream = new ByteArrayInputStream(buffer);
            
            if (!CmUtilidades.inputStreamTieneContenido(inputStream)) {
                adjuntoError.setDescripcion("El archivo " + fileName + " está vacío.");
                return Optional.of(adjuntoError);
            }
       
            if (fileName.endsWith(".json")) {
                CmFeRipsCargaAdjunto anexo = new CmFeRipsCargaAdjunto();
                anexo.setArchivoNombre(fileName);
                String mensajeValidacion = CmUtilidades.validarJsonExtructuraBase(inputStream, anexo);
                if (!mensajeValidacion.isEmpty()) {
                    adjuntoError.setDescripcion("El archivo " + fileName + " : " + mensajeValidacion);
                    return Optional.of(adjuntoError);
                }
                
                if (CmUtilidades.esArchivoJsonRips(fileName)) {
                    CmFeRipsCargaFacturaDTO feCargaFacturaDTO = CmUtilidades.obtenerCmFeRipsCargaFacturaDTO(inputStreamValidationDTO, anexo);
                    mensajeValidacion = validarTipoUsuarioUnicoPorFactura(feCargaFacturaDTO);
                    if (!mensajeValidacion.isEmpty()) {
                        adjuntoError.setDescripcion("El archivo " + fileName + " : " + mensajeValidacion);
                        return Optional.of(adjuntoError);
                    }
                     if (mensajeValidacion.isEmpty()) {
                        asignarMaestroRegimenCarga(carga, feCargaFacturaDTO);
                        asignarMultiusuario(carga, feCargaFacturaDTO);
                    }
                }              
            }

            if (fileName.endsWith(".xml") && !CmUtilidades.esXmlValido(inputStream)) {
                adjuntoError.setDescripcion("El archivo " + fileName + " no contiene XML válido.");
                return Optional.of(adjuntoError);
            }

            return Optional.empty();
        } catch (Exception ex) {
            adjuntoError.setDescripcion("Error al validar el archivo ZIP: " + obtenerErrorStrFormateado(ex));
        }

        return Optional.of(adjuntoError);
    }
    
    public boolean esReintentPorCUVesCandidatoValido(CmFeRipsCarga objeto) {
        
        if (objeto == null || objeto.getEstado() != CmFeRipsCarga.ESTADO_RECHAZADO) {
            return false;
        }
        
        final String mensajeComparacion  = "CUV no es válido ante el MSPS".toLowerCase();
        String descripcionRechazo = Optional.ofNullable(objeto.getObservacionRechazo()).orElse("").toLowerCase();
   

        if (!descripcionRechazo.isEmpty() && descripcionRechazo.contains(mensajeComparacion)) {
            return true;
        }
        return false;
    }
    
    private void agregarError(List<CmFeAdjuntoErrores> erroresTotalesArchivoZip,
            String nombreError, String descripcionError) {
        CmFeAdjuntoErrores error = new CmFeAdjuntoErrores();
        error.setNombre(nombreError);
        error.setDescripcion(descripcionError);
        erroresTotalesArchivoZip.add(error);
    }

    public void guardarEstadorRecuperacionCarga(CmFeRipsCarga objeto) {
        setObjeto(objeto);
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(DO_ACCION_GUARDAR_ESTADO_RECUPERACION_CARGA);
        getCmFeRipsCargaServicio().Accion(this);
        if (!this.isError()) {
            this.addMensaje("Se realizará el reintento de la carga favor validar en unos minutos. Carga id(" + objeto.getId() + ")");
        }
        procesoFinal();
    }
    
    public void guardarReintentoPorValidacionCUV(CmFeRipsCarga objeto) {
        setObjeto(objeto);
        super.setAccion(ACCION_ADICIONAL_4);
        super.setDoAccion(ACCION_GUARDAR);
        getCmFeRipsCargaServicio().Accion(this);
        if (!this.isError()) {
            this.addMensaje("Se realizará el reintento de la carga favor validar en unos minutos. Carga id ( " + objeto.getId() + " )");
        }
        procesoFinal();
    }
    

    public void editar(int _id) {
        getObjeto().setId(_id);
        super.setAccion(ACCION_EDITAR);
        getCmFeRipsCargaServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmEditar");
        PrimeFaces.current().ajax().update("frmEditar");
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");
        procesoFinal();
    }

    public void ver(int id) {
        setObjeto(new CmFeRipsCarga(id));
        super.setAccion(ACCION_VER);
        super.setDoAccion(DO_ACCION_VER_CARGA);
        getCmFeRipsCargaServicio().Accion(this);
        super.setAccion(ACCION_VER);
        super.setDoAccion(DO_ACCION_VER_ADJUNTOS);
        getCmFeRipsCargaServicio().Accion(this);
        PrimeFaces.current().resetInputs("frmVer");
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void verGestionar(int id) {
        getObjeto().setId(id);
        setHabilitarBotonAdjuntos(false);
        super.setAccion(ACCION_ADICIONAL_1);
        super.setDoAccion(DO_ACCION_VER_GESTIONAR);
        getCmFeRipsCargaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmGestionar");
        PrimeFaces.current().executeScript("PF('dialogoGestionar').show()");
        procesoFinal();
    }

    public void verAdjuntos(int id) {
        setObjeto(new CmFeRipsCarga(id));
        super.setAccion(ACCION_VER);
        super.setDoAccion(DO_ACCION_VER_ADJUNTOS);
        getCmFeRipsCargaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmVerAdjuntos:pAdjuntos");
        PrimeFaces.current().executeScript("PF('dialogoVerAdjuntos').show()");
        procesoFinal();
    }
    
     public void verSoportes(int id) {
        setObjeto(new CmFeRipsCarga(id));
        inicializarTablaSoportes(id);
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(DO_ACCION_VER_SOPORTES);
        limpiarSelectorSoportes();
        PrimeFaces.current().ajax().update("frmVerSoportes:panelSoportes");
        PrimeFaces.current().executeScript("PF('dialogoSoportes').show()");
        procesoFinal();
    }
     
    public void verCargasAsignar() {
      
        limpiarListaCargasAsignar();
        inicializarTablaCargasAsignar();
        super.setAccion(Url.ACCION_VER);
        super.setDoAccion(DO_ACCION_VER_CARGAS_ASIGNAR);
        PrimeFaces.current().resetInputs("frmVerAsignarRadicador");
        PrimeFaces.current().ajax().update("frmVerAsignarRadicador");
        PrimeFaces.current().executeScript("PF('dialogoAsignarRadicador').show()");
        procesoFinal();
    }
     
    public void verJson(String tituloJsonMensaje, String mensajeJson, Date fechaEnvio) {
        this.setTituloJsonMensaje(tituloJsonMensaje);
        this.setJsonContenido(mensajeJson);
        this.setFechaEnvio(fechaEnvio);
        PrimeFaces.current().resetInputs("frmVerJson");
        PrimeFaces.current().ajax().update("frmVerJson");
        PrimeFaces.current().executeScript("PF('dialogoVerJson').show()");
    }
    
    public void verWsTransaccion(int idWsTransaccion) {
        inicializarTablaTransacciones(idWsTransaccion);
        PrimeFaces.current().resetInputs("frmVerTransaccion");
        PrimeFaces.current().ajax().update("frmVerTransaccion");
        PrimeFaces.current().executeScript("PF('dialogoVerTransaccion').show()");
        procesoFinal();
    }
    
    public void verPdf(CmFeSoporte soporte) {
      
        super.setAccion(ACCION_VER);
        super.setDoAccion(DO_ACCION_VER_PDF);
        File file = new File(soporte.getArchivoRuta() + soporte.getArchivo());
        byte[] fileContent = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(fileContent);
            String titulo = soporte.getArchivoNombre();
            String base64Pdf = Base64.getEncoder().encodeToString(fileContent);

            String script = "var pdfBlob = b64toBlob('" + base64Pdf + "', 'application/pdf');"
                    + "var blobUrl = URL.createObjectURL(pdfBlob);"
                    + "var newWindow = window.open('', '_blank');"
                    + "newWindow.document.write(`"
                    + "<html><head><title>" + titulo + "</title></head>"
                    + "<body style='margin:0'>"
                    + "<iframe width='100%' height='100%' style='border:none' src='${blobUrl}'></iframe>"
                    + "</body></html>`);"
                    + "newWindow.document.close();"
                    + "function b64toBlob(b64Data, contentType) {"
                    + "  contentType = contentType || '';"
                    + "  var sliceSize = 512;"
                    + "  var byteCharacters = atob(b64Data);"
                    + "  var byteArrays = [];"
                    + "  for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {"
                    + "    var slice = byteCharacters.slice(offset, offset + sliceSize);"
                    + "    var byteNumbers = new Array(slice.length);"
                    + "    for (var i = 0; i < slice.length; i++) {"
                    + "      byteNumbers[i] = slice.charCodeAt(i);"
                    + "    }"
                    + "    var byteArray = new Uint8Array(byteNumbers);"
                    + "    byteArrays.push(byteArray);"
                    + "  }"
                    + "  return new Blob(byteArrays, {type: contentType});"
                    + "}";

            PrimeFaces.current().executeScript(script);


        } catch (IOException ex) {
            addError("Ocurrio un error verPdf  :" + obtenerErrorStrFormateado(ex));
        }
        procesoFinal();
    }
    
    
    public void verAsignarRadicadores(){
      
        setRadicador(new  CmGrupoUsuario());
        super.setAccion(ACCION_VER);
        super.setDoAccion(DO_ACCION_VER_RADICADORES);

        boolean esValidaAsignacion = validarExistenciaSeleccionCargasAsignables();
        
        if(!getHashCargasSelecionadasRadicador().isEmpty()){
            setListaCargasSeleccionadaAsignar( new ArrayList<>(hashCargasSelecionadasRadicador.values()));
        }

        esValidaAsignacion = esValidaAsignacion ? validarSeleccionUnicaModalidadContrato(getListaCargasSeleccionadaAsignar()) :
                             esValidaAsignacion;

        if (esValidaAsignacion) {

            int idMaeContratoModalidadReferencia = getListaCargasSeleccionadaAsignar().get(0).getMaeContratoModalidadId();

            int categoria = CmFeRipsCargaServicioImpl.ID_MODALIDAD_CONTRATO_PGP == idMaeContratoModalidadReferencia
                    ? CmGrupo.CATEGORIA_PGP
                    : determinarCategoriaPorCobertura(getListaCargasSeleccionadaAsignar());

            this.getObjeto().getCmGrupoRadicacionRelacionado().setCategoria(categoria);

            getCmFeRipsCargaServicio().Accion(this);
            if (!isError()) {
                PrimeFaces.current().resetInputs("frmVerRadicadores");
                PrimeFaces.current().ajax().update("frmVerRadicadores");
                PrimeFaces.current().executeScript("PF('dialogoVisualizarRadicadores').show()");
            }
        }
        procesoFinal();
    }



    public void gestionar(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_ADICIONAL_1);
        getCmFeRipsCargaServicio().Accion(this);
        PrimeFaces.current().ajax().update("frmGestionar:tablaFacturas");
        PrimeFaces.current().ajax().update("frmGestionar:pGestionar");
        PrimeFaces.current().executeScript("PF('dialogoGestionar').show()");
        procesoFinal();
    }
    
    public void modificar() {
        super.setAccion(ACCION_MODIFICAR);
        getCmFeRipsCargaServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide()");
        }
        procesoFinal();
    }

    public void borrar(int _id) {
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

    private void procesoFinal() {
        if (!super.isError()) {
            switch (getAccion()) {
                case Url.ACCION_GUARDAR:
                    crearLog("Guardar", getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmRipsCargas");
                    break;
                case Url.ACCION_MODIFICAR:
                    crearLog("Modificar", getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmRipsCargas");
                    break;
                case Url.ACCION_BORRAR:
                    crearLog("Borrar", getObjeto().toString());
                    break;
                case Url.ACCION_LISTAR:
                    crearLog("Listar", getObjeto().toString());
                    PrimeFaces.current().ajax().update("frmRipsCargas");
                    break;
                case Url.ACCION_VER:                
                    switch (getDoAccion()) {
                        case Url.ACCION_ADICIONAL_1:
                            crearLog("Ver Errores de Estructura", getObjeto().toString());
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            crearLog("Ver Sucesos", getObjeto().toString());
                            break;
                        case DO_ACCION_VER_ADJUNTOS:
                            crearLog("Ver Adjunto", getObjeto().toString());
                            break;
                        case DO_ACCION_VER_SOPORTES:
                            crearLog("Ver Soportes", getObjeto().toString());
                            break;
                        case DO_ACCION_VER_PDF:
                            crearLog("Ver Pdf", getObjeto().toString());
                            break;
                        case DO_ACCION_VER_RADICADORES:
                            crearLog("Ver Radicadores", getObjeto().toString());
                            break;
                        case DO_ACCION_VER_CARGAS_ASIGNAR:
                            crearLog("Ver Cargas Asignar", getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                    crearLog("Crear", getObjeto().toString());
                    break;
                case Url.ACCION_EDITAR:
                    crearLog("Editar", getObjeto().toString());
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (getDoAccion()) {
                        case DO_ACCION_VER_GESTIONAR:
                            crearLog("Ver Gestionar", getObjeto().toString());
                            break;
                        case DO_ACCION_GUARDAR_ENVIAR:
                            crearLog("Enviar Gestionar", getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmRipsCargas");
                            break;
                        case DO_ACCION_GUARDAR_ESTADO_RECUPERACION_CARGA:
                            crearLog("Guardar Estado Inicio", getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmRipsCargas");
                            break;
                        case DO_ACCION_ACTUALIZAR_ATRIBUTO_FE_DOCUMENTO:
                            PrimeFaces.current().ajax().update("frmGestionar");
                            if (this.getObjeto().getDe5601SoporteFe()) {
                                PrimeFaces.current().ajax().update("frmRipsCargas");
                            }
                            crearLog("Guardar Atributos", getObjeto().toString());
                            break;
                        case DO_ACCION_GUARDAR_REQUISITOS_MANUALES:
                            crearLog("Guardar Gestionar", getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmRipsCargas");
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (getDoAccion()) {
                        case DO_ACCION_VER_CM_FE_TRANSACCIONES:
                            crearLog("Ver Transacciones", getObjeto().toString());
                            break;
                        case Url.ACCION_ADICIONAL_2:
                            crearLog("Enviar Auditoria", getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (getDoAccion()) {
                        case Url.ACCION_CREAR:
                            crearLog("Crear Masiva", getObjeto().toString());
                            break;
                        case Url.ACCION_GUARDAR:
                            crearLog("Guardar Masivo", getObjeto().toString());
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_4:
                    switch (getDoAccion()) {
                        case Url.ACCION_GUARDAR:
                            crearLog("Guardar Reintento CUV", getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmRipsCargas");
                            break;
                    }
                    break;   
                case Url.ACCION_ADICIONAL_5:
                    switch (getDoAccion()) {
                        case DO_ACCION_GUARDAR_ASIGNACION_RADICADOR:
                            crearLog("Guardar Asignacion Radicador", getObjeto().toString());
                            PrimeFaces.current().ajax().update("frmRipsCargas");
                            break;
                    }
                    break;   
            }
        }
        generarMensajes();
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
        getCmFeRipsCargaServicio().Accion(this);
        PrimeFaces.current().executeScript("PF('dialogoGestionar').hide()");
        procesoFinal();
    }
    
    public void inicializarTablaTransacciones(int idCarga) {
        
           getParamConsultaCmCfeTransacciones().setParametroConsulta1(idCarga);
        
        lazyCmFeTransacciones = new LazyDataModel<CmFeTransaccion>() {

            private List<CmFeTransaccion> lista;
            
            @Override                      
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaCmCfeTransacciones().getCantidadRegistros();
            }

            @Override
            public List<CmFeTransaccion> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaCmCfeTransacciones().setPrimerRegistro(primerRegistro);
                getParamConsultaCmCfeTransacciones().setRegistrosPagina(registrosPagina);
                getParamConsultaCmCfeTransacciones().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaCmCfeTransacciones().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarCmFeTransacciones();
                lista = getListaCmFeTransacciones();
                setRowCount(getParamConsultaCmCfeTransacciones().getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(CmFeTransaccion objeto) {
                return objeto.getId().toString();
            }

            @Override
            public CmFeTransaccion getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (CmFeTransaccion objeto : lista) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };
    }

    public void inicializarTablaSoportes(int idCarga) {

        getParamConsultaSoportes().setParametroConsulta1(idCarga);

        lazySoportes = new LazyDataModel<CmFeSoporte>() {

            private List<CmFeSoporte> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaSoportes().getCantidadRegistros();
            }

            @Override
            public List<CmFeSoporte> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaSoportes, Map<String, FilterMeta> filtros) {
                getParamConsultaSoportes().setPrimerRegistro(primerRegistro);
                getParamConsultaSoportes().setRegistrosPagina(registrosPagina);
                getParamConsultaSoportes().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaSoportes));
                getParamConsultaSoportes().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarCmSoportes();
                lista = getListaSoportes();
                setRowCount(getParamConsultaSoportes().getCantidadRegistros());
                return lista;
            }

            @Override
            public String getRowKey(CmFeSoporte objeto) {
                return objeto.getId().toString();
            }

            @Override
            public CmFeSoporte getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (CmFeSoporte objeto : lista) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };
    }
    
    public void inicializarTablaCargasAsignar() {

        lazyCmCargasAsignar = new LazyDataModel<CmFeRipsCarga>() {

            private List<CmFeRipsCarga> lista;

            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsultaCargasAsignar().getCantidadRegistros();
            }

            @Override
            public List<CmFeRipsCarga> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                getParamConsultaCargasAsignar().setPrimerRegistro(primerRegistro);
                getParamConsultaCargasAsignar().setRegistrosPagina(registrosPagina);
                getParamConsultaCargasAsignar().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaCargasAsignar().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarCargasAsignar();
                lista = getRegistrosCargasAsignar();
                setRowCount(getParamConsultaCargasAsignar().getCantidadRegistros());
                if (getRegistrosCargasAsignar() != null) {
                        for (CmFeRipsCarga carga : getRegistrosCargasAsignar()) {
                            if (getHashCargasSelecionadasRadicador().get(carga.getId()) != null) {
                                getListaCargasSeleccionadaAsignar().add(carga);
                            }
                        }
                    }
                return lista;
            }

            @Override
            public String getRowKey(CmFeRipsCarga objeto) {
                return objeto.getId().toString();
            }

            @Override
            public CmFeRipsCarga getRowData(String objetoId) {
                Integer id = Integer.valueOf(objetoId);
                for (CmFeRipsCarga objeto : lista) {
                    if (id.equals(objeto.getId())) {
                        return objeto;
                    }
                }
                return null;
            }
        };
    }

    public void verDetalleFactura(CmRipsAfFactura cmRipsAfFactura) {
        try {
            setFactura(cmRipsAfFactura);
            getParamConsultaDetalles().setParametroConsulta1(cmRipsAfFactura.getCmRipsCarga().getId());
            getParamConsultaDetalles().setParametroConsulta2(cmRipsAfFactura.getNumeroFactura());
            getParamConsultaDetalles().setCantidadRegistros(getCmRipsCargaRemoto().consultarCantidadFacturaDetalles(getParamConsultaDetalles()));
            setListaCmRipsCargaFacturaDetalle(getCmRipsCargaRemoto().consultarFacturaDetalles(getParamConsultaDetalles()));
        } catch (Exception ex) {
            addError("Error: " + obtenerErrorStrFormateado(ex));
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
            addError(obtenerErrorStrFormateado(ex));
        }
        generarMensajes();
    }

    public void adicionarAdjunto(FileUploadEvent event) throws IOException {
     
        try {
            UploadedFile archivoCarga = event.getFile();
            String nombre = archivoCarga.getFileName();
            InputStream inputStream = archivoCarga.getInputStream();
           
            
            
            if (this.getObjeto().getTipo() == null) {
                addError("Para agregar adjuntos deben seleccionar tipo de carga");
                generarMensajes();
                return;
            }
            
            if (!validarArchivoYaAdjunto(nombre)) {
                generarMensajes();
                return;
            }
            
            CmFeRipsCargaAdjunto adjNuevo = crearAdjunto(nombre,  inputStream);
            
            getListaAdjuntos().add(adjNuevo);
            
            generarMensajes();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar agregar un anexo: "+obtenerErrorStrFormateado(e));
            generarMensajes();
        }
    }

    public void retirarAdjunto(String nombreArchivo) {
        if (!"".equals(nombreArchivo)) {
            for (int i = 0; i < getListaAdjuntos().size(); i++) {
                CmFeRipsCargaAdjunto adj = getListaAdjuntos().get(i);
                if (adj.getIdInsertar() != null && adj.getArchivoNombre() == nombreArchivo) {
                    getListaAdjuntos().remove(i);
                    break;
                }
            }
        }
        PrimeFaces.current().ajax().update("frmCrearFactura:tablaAdjuntos");
    }

    public void borrarAdjunto(int id) {
        for (int i = 0; i < getListaAdjuntos().size(); i++) {
            CmFeRipsCargaAdjunto adj = getListaAdjuntos().get(i);
            if (adj.getId() != null && adj.getId() == id) {
                getListaAdjuntos().remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmCrearFactura:tablaAdjuntos");
    }

    public void descargarAdjunto( CmFeRipsCargaAdjunto adj) throws IOException {
        String rutaCompleta = adj.getArchivoRuta() + adj.getArchivo();
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
            String attachmentName = "attachment; filename=\"" + adj.getArchivoNombre() + "\"";
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
            } else if (ext.equalsIgnoreCase(".txt")) {
                ec.setResponseContentType("application/txt");
            } else if (ext.equalsIgnoreCase(".json")) {
                ec.setResponseContentType("application/json");
            } else  if (ext.equalsIgnoreCase(".xml")) {
                ec.setResponseContentType("application/xml");
            }else  {
                throw new Exception();
            }
            output = ec.getResponseOutputStream();
            output.write(exportContent);
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo  :" + obtenerErrorStrFormateado(e));
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (output != null) {
                output.close();
            }
        }
        crearLog("Descargar Adjuntos", getObjeto().toString());
        procesoFinal();
    }
    
    public void descargarSoporte( CmFeSoporte soporte) throws IOException {
        String rutaCompleta = soporte.getArchivoRuta() + soporte.getArchivo();
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
            String attachmentName = "attachment; filename=\"" + soporte.getArchivoNombre() + "\"";
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
            } else if (ext.equalsIgnoreCase(".txt")) {
                ec.setResponseContentType("application/txt");
            } else if (ext.equalsIgnoreCase(".json")) {
                ec.setResponseContentType("application/json");
            } else  if (ext.equalsIgnoreCase(".xml")) {
                ec.setResponseContentType("application/xml");
            }else  {
                throw new Exception();
            }
            output = ec.getResponseOutputStream();
            output.write(exportContent);
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo  :" + obtenerErrorStrFormateado(e));
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (output != null) {
                output.close();
            }
        }
        crearLog("Descargar Adjuntos", getObjeto().toString());
        procesoFinal();
    }
    /*----------------------
    -----GETTERS SETTERS------
    ------------------------*/

    public CmFeRipsCargaServicioIface getCmFeRipsCargaServicio() {
        return cmFeRipsCargaServicio;
    }

    public void setCmFeRipsCargaServicio(CmFeRipsCargaServicioIface cmFeRipsCargaServicio) {
        this.cmFeRipsCargaServicio = cmFeRipsCargaServicio;
    }


    public List<CmFeRipsCarga> getRegistros() {
        return registros;
    }

    public void setRegistros(List<CmFeRipsCarga> registros) {
        this.registros = registros;
    }

    public List<CmFeRipsCarga> getRegistrosCargasAsignar() {
        return registrosCargasAsignar;
    }

    public void setRegistrosCargasAsignar(List<CmFeRipsCarga> registrosCargasAsignar) {
        this.registrosCargasAsignar = registrosCargasAsignar;
    }
    

    public CmFeRipsCarga getObjeto() {
        return objeto;
    }

    public void setObjeto(CmFeRipsCarga objeto) {
        this.objeto = objeto;
    }

    public LazyDataModel<CmFeRipsCarga> getLazyCmRips() {
        return lazyCmRips;
    }

    public void setLazyCmRips(LazyDataModel<CmFeRipsCarga> lazyCmRips) {
        this.lazyCmRips = lazyCmRips;
    }

    public LazyDataModel<CmFeRipsCarga> getLazyCmCargasAsignar() {
        return lazyCmCargasAsignar;
    }

    public void setLazyCmCargasAsignar(LazyDataModel<CmFeRipsCarga> lazyCmCargasAsignar) {
        this.lazyCmCargasAsignar = lazyCmCargasAsignar;
    }

    @Override
    public int getTamanoPagina() {
        return tamanoPagina;
    }

    @Override
    public void setTamanoPagina(int tamanoPagina) {
        this.tamanoPagina = tamanoPagina;
    }

    public List<CmFeRipsCargaAdjunto> getListaAdjuntos() {
        if (listaAdjuntos == null) {
            listaAdjuntos = new ArrayList<>();
        }
        return listaAdjuntos;
    }

    public void setListaAdjuntos(List<CmFeRipsCargaAdjunto> listaAdjuntos) {
        this.listaAdjuntos = listaAdjuntos;
    }

    public int getTipoCreacionFactura() {
        return TIPO_CREACION_FACTURA;
    }
    
    public int getTipoCreacionNota() {
        return TIPO_CREACION_NOTA;
    }

    public String getMansajeGeneral() {
        return mansajeGeneral;
    }

    public void setMansajeGeneral(String mansajeGeneral) {
        this.mansajeGeneral = mansajeGeneral;
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

    public ParamConsulta getParamConsultaCmCfeTransacciones() {
        if (paramConsultaCmCfeTransacciones == null) {
            paramConsultaCmCfeTransacciones = new ParamConsulta();
        }
        return paramConsultaCmCfeTransacciones;
    }

    public void setParamConsultaCmCfeTransacciones(ParamConsulta paramConsultaCmCfeTransacciones) {
        this.paramConsultaCmCfeTransacciones = paramConsultaCmCfeTransacciones;
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

    public ParamConsulta getParamConsultaDetalles() {
        if (paramConsultaDetalles == null) {
            paramConsultaDetalles = new ParamConsulta();
        }
        return paramConsultaDetalles;
    }

    public void setParamConsultaDetalles(ParamConsulta paramConsultaDetalles) {
        this.paramConsultaDetalles = paramConsultaDetalles;
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

    public ParamConsulta getParamConsultaSoportes() {
        return paramConsultaSoportes;
    }

    public void setParamConsultaSoportes(ParamConsulta paramConsultaSoportes) {
        this.paramConsultaSoportes = paramConsultaSoportes;
    }

    public ParamConsulta getParamConsultaCargasAsignar() {
        return paramConsultaCargasAsignar;
    }

    public void setParamConsultaCargasAsignar(ParamConsulta paramConsultaCargasAsignar) {
        this.paramConsultaCargasAsignar = paramConsultaCargasAsignar;
    }
    
    
    public String getAdjuntoExtensionValida() {
        return adjuntoExtensionValida;
    }

    public void setAdjuntoExtensionValida(String adjuntoExtensionValida) {
        this.adjuntoExtensionValida = adjuntoExtensionValida;
    }

    public int getAdjuntoCantidadValida() {
        return adjuntoCantidadValida;
    }

    public void setAdjuntoCantidadValida(int adjuntoCantidadValida) {
        this.adjuntoCantidadValida = adjuntoCantidadValida;
    }

    public String getAdjuntoExpresionValida() {
        return adjuntoExpresionValida;
    }

    public void setAdjuntoExpresionValida(String adjuntoExpresionValida) {
        this.adjuntoExpresionValida = adjuntoExpresionValida;
    }

    public List<CmFeTransaccion> getListaCmFeTransacciones() {
        return listaCmFeTransacciones;
    }

    public void setListaCmFeTransacciones(List<CmFeTransaccion> listaCmFeTransacciones) {
        this.listaCmFeTransacciones = listaCmFeTransacciones;
    }

    public LazyDataModel<CmFeTransaccion> getLazyCmFeTransacciones() {
        return lazyCmFeTransacciones;
    }

    public void setLazyCmFeTransacciones(LazyDataModel<CmFeTransaccion> lazyCmFeTransacciones) {
        this.lazyCmFeTransacciones = lazyCmFeTransacciones;
    }

    public DateFormat getFechaFormat() {
        return fechaFormat;
    }

    public void setFechaFormat(DateFormat fechaFormat) {
        this.fechaFormat = fechaFormat;
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

    public int getIntensidadCarga() {
        return intensidadCarga;
    }

    public void setIntensidadCarga(int intensidadCarga) {
        this.intensidadCarga = intensidadCarga;
    }

    public List<CmFeAdjuntoErrores> getListaErroresProcesamiento() {
        if (listaErroresProcesamiento == null) {
            listaErroresProcesamiento = new ArrayList<>();
        }
        return listaErroresProcesamiento;
    }

    public void setListaErroresProcesamiento(List<CmFeAdjuntoErrores> listaErroresProcesamiento) {
        this.listaErroresProcesamiento = listaErroresProcesamiento;
    }

    public Map<String, CmFeRipsCarga> getListaCmFeRipsCarga() {
        if (listaCmFeRipsCarga == null) {
            listaCmFeRipsCarga = new HashMap<>();
        }
        return listaCmFeRipsCarga;
    }

    public void setListaCmFeRipsCarga(Map<String, CmFeRipsCarga> listaCmFeRipsCarga) {
        this.listaCmFeRipsCarga = listaCmFeRipsCarga;
    }

    public List<CmGrupoUsuario> getRadicadores() {
        return radicadores;
    }

    public void setRadicadores(List<CmGrupoUsuario> radicadores) {
        this.radicadores = radicadores;
    }

    public CmGrupoUsuario getRadicador() {
        return radicador;
    }

    public void setRadicador(CmGrupoUsuario radicador) {
        this.radicador = radicador;
    }

    public boolean isTodaCargasSelecionadasRadicar() {
        return todaCargasSelecionadasRadicar;
    }

    public void setTodaCargasSelecionadasRadicar(boolean todaCargasSelecionadasRadicar) {
        this.todaCargasSelecionadasRadicar = todaCargasSelecionadasRadicar;
    }
    
    public int getINDICADOR_PENDIENTE_FE() {
        return INDICADOR_PENDIENTE_FE;
    }
    
    public int getINDICADOR_PENDIENTE_SOPORTES() {
        return INDICADOR_PENDIENTE_SOPORTES;
    }

    public List<CmFeRipsCarga> getListaCargasSeleccionadaAsignar() {
        return listaCargasSeleccionadaAsignar;
    }

    public void setListaCargasSeleccionadaAsignar(List<CmFeRipsCarga> listaCargasSeleccionadaAsignar) {
        this.listaCargasSeleccionadaAsignar = listaCargasSeleccionadaAsignar;
    }
  
    public void refrescarSedes() {
        getCmFeRipsCargaServicio().listarPrestadoresYSedes(this);
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

    public HashMap<Integer, Maestro> getHashTipoSoporte() {
        return hashTipoSoporte;
    }

    public void setHashTipoSoporte(HashMap<Integer, Maestro> hashTipoSoporte) {
        this.hashTipoSoporte = hashTipoSoporte;
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
    
    public List<Maestro> getListaMaeMotivoDevolucion() {
        if(listaMaeMotivoDevolucion == null){
            listaMaeMotivoDevolucion = new ArrayList<>();
        }
        return listaMaeMotivoDevolucion;
    }

    public void setListaMaeMotivoDevolucion(List<Maestro> listaMaeMotivoDevolucion) {
        this.listaMaeMotivoDevolucion = listaMaeMotivoDevolucion;
    }

    public void adicionarSedePrestadorPorEvento(SelectEvent event) {
        try {
            setObjetoPrestadorSede((CntPrestadorSede) event.getObject());
            getObjeto().setGnPrestadorSede(getObjetoPrestadorSede());
            setObjetoPrestador(getObjetoPrestadorSede().getCntPrestador());
            adicionarNitPrestador();
            limpiarPantallaDespuesAgregarSedeCargaFactura();
        } catch (Exception ex) {
            addError("Ocurrio un error al seleccionar la IPS: " + obtenerErrorStrFormateado(ex));
        }
    }

    private void adicionarNitPrestador() {
        if (getObjeto().getGnPrestadorSede() != null && 
            getObjeto().getGnPrestadorSede().getCntPrestador() != null) {
            getObjeto().setDocumentoPrestador(getObjeto().getGnPrestadorSede().getCntPrestador().getNumeroDocumento());
        }
    }

    public String obtenerMunicipio(int id) {
        try {
            return hashUbicacion.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public List<CmFeRipsCargaAdjunto> getListaAnexos() {
        return listaAnexos;
    }

    public void setListaAnexos(List<CmFeRipsCargaAdjunto> listaAnexos) {
        this.listaAnexos = listaAnexos;
    }

    public String getRangoFechaPrestacion() {
        return rangoFechaPrestacion;
    }

    public void setRangoFechaPrestacion(String rangoFechaPrestacion) {
        this.rangoFechaPrestacion = rangoFechaPrestacion;
    }  

    public CmFactura getCmFacturaAsociadaNotas() {
        return cmFacturaAsociadaNotas;
    }

    public void setCmFacturaAsociadaNotas(CmFactura cmFacturaAsociadaNotas) {
        this.cmFacturaAsociadaNotas = cmFacturaAsociadaNotas;
    }

    public boolean isHabilitarBotonAdjuntos() {
        return habilitarBotonAdjuntos;
    }

    public void setHabilitarBotonAdjuntos(boolean habilitarBotonAdjuntos) {
        this.habilitarBotonAdjuntos = habilitarBotonAdjuntos;
    }

    public int getTipoReglaCarga() {
        return tipoReglaCarga;
    }

    public void setTipoReglaCarga(int tipoReglaCarga) {
        this.tipoReglaCarga = tipoReglaCarga;
    }

    public List<Maestro> getListaPeriodoCarga() {
        return listaPeriodoCarga;
    }

    public void setListaPeriodoCarga(List<Maestro> listaPeriodoCarga) {
        this.listaPeriodoCarga = listaPeriodoCarga;
    }

    public List<Maestro> getListaMeses() {
        return listaMeses;
    }

    public void setListaMeses(List<Maestro> listaMeses) {
        this.listaMeses = listaMeses;
    }

    public List<CmFeSoporte> getListaSoportes() {
        return listaSoportes;
    }

    public void setListaSoportes(List<CmFeSoporte> listaSoportes) {
        this.listaSoportes = listaSoportes;
    } 

    public LazyDataModel<CmFeSoporte> getLazySoportes() {
        return lazySoportes;
    }

    public void setLazySoportes(LazyDataModel<CmFeSoporte> lazySoportes) {
        this.lazySoportes = lazySoportes;
    }

    public String getContenidoPDF() {
        return contenidoPDF;
    }

    public void setContenidoPDF(String contenidoPDF) {
        this.contenidoPDF = contenidoPDF;
    }

    public HashMap<Integer, CmFeRipsCarga> getHashCargasSelecionadasRadicador() {
        return hashCargasSelecionadasRadicador;
    }

    public void setHashCargasSelecionadasRadicador(HashMap<Integer, CmFeRipsCarga> hashCargasSelecionadasRadicador) {
        this.hashCargasSelecionadasRadicador = hashCargasSelecionadasRadicador;
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
        PrimeFaces.current().ajax().update("frmCrearFactura:contrato");
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
     
    private void limpiarPantallaCrearFactura() {
        getObjeto().setTipo(null);
        setHabilitarBotonAdjuntos(false);
        PrimeFaces.current().resetInputs("frmCrearFactura");
        PrimeFaces.current().ajax().update("frmCrearFactura");
        PrimeFaces.current().executeScript("PF('dialogoCrearFactura').show()");
    }
    
    private void limpiarPantallaCrearFacturaMasiva() {
        getObjeto().setTipo(null);
        setHabilitarBotonAdjuntos(false);
        PrimeFaces.current().resetInputs("frmCrearFacturaMasiva");
        PrimeFaces.current().ajax().update("frmCrearFacturaMasiva");
        PrimeFaces.current().executeScript("PF('dialogoCrearFacturaMasiva').show()");
    }
    

    private void limpiarPantallaDespuesAgregarSedeCargaFactura() {

        if (esIntensidadCargaIndividual()) {
            PrimeFaces.current().ajax().update("frmCrearFactura:prestador");
            PrimeFaces.current().ajax().update("frmCrearFactura:sede");
        }
        if (esIntensidadCargaMasiva()) {
            PrimeFaces.current().ajax().update("frmCrearFacturaMasiva:prestador");
            PrimeFaces.current().ajax().update("frmCrearFacturaMasiva:sede");
        }
        PrimeFaces.current().executeScript("PF('dialogoIpsLista').hide()");
        PrimeFaces.current().executeScript("PF('tablaWidgetIPS').clearFilters()");
        PrimeFaces.current().executeScript("PF('tablaWidgetIPS').unselectAllRows()");
        PrimeFaces.current().resetInputs("frmPrestadorLista:tablaRegistrosIps");
        PrimeFaces.current().resetInputs("frmPrestadorLista:panelIpsLista");
        PrimeFaces.current().ajax().update("frmPrestadorLista:tablaRegistrosIps");
        PrimeFaces.current().ajax().update("frmPrestadorLista");
        PrimeFaces.current().ajax().update("frmPrestadorLista:panelIpsLista");
    }

    public void ejecutarAccionCambioTipoCarga() {
        setHabilitarBotonAdjuntos(false);
        if (getObjeto().getHaySeleccionTipoValido()) {
            asignarValoresValidacionAnexos();
            setHabilitarBotonAdjuntos(true);
        }
        limpiarAnexos();
        PrimeFaces.current().ajax().update("frmCrearFactura");
    }
    
    public void ejecutarAccionCambioTipoCargaMasiva() {
        setHabilitarBotonAdjuntos(false);
        if (getObjeto().getHaySeleccionTipoValido()) {
            setHabilitarBotonAdjuntos(true);
        }
        limpiarAnexosMasivos();
        PrimeFaces.current().ajax().update("frmCrearFacturaMasiva");
    }

     public void asignarValoresValidacionAnexos() {
        switch (getObjeto().getTipo()) {
            case CmFeRipsCarga.TIPO_CARGA_NOTA_CREDITO_CAPITA:
            case CmFeRipsCarga.TIPO_CARGA_NOTA_CREDITO_TOTAL:
            case CmFeRipsCarga.TIPO_CARGA_NOTA_CREDITO_ACUERDO_VOLUNTAD:  //TODO: incluir getEsTipoCargaNota final: nota falta a que carga se la cargo
            case CmFeRipsCarga.TIPO_CARGA_CAPITA_INICIAL:// tipo: carga
                setAdjuntoCantidadValida(2);
                setAdjuntoExtensionValida("xml|json");
                setAdjuntoExpresionValida("/(\\.|\\/)(xml|json)$/");
                setTipoReglaCarga(TIPO_REGLA_CARGA_XML_CUV);
                break;
            case CmFeRipsCarga.TIPO_CARGA_NOTA_CREDITO_PARCIAL:
            case CmFeRipsCarga.TIPO_CARGA_NOTA_DEBITO:
            case CmFeRipsCarga.TIPO_CARGA_FACTURA:
            case CmFeRipsCarga.TIPO_CARGA_CAPITA_PERIODO: //tipo: carga
                setAdjuntoCantidadValida(3);
                setAdjuntoExtensionValida("xml|json");
                setAdjuntoExpresionValida("/(\\.|\\/)(xml|json)$/");  
                setTipoReglaCarga(TIPO_REGLA_CARGA_XML_JSON_CUV);
                break;
            case CmFeRipsCarga.TIPO_CARGA_CAPITA_FINAL://TODO: probar: nota : asociar a numero carga cargado inicialmente
            case CmFeRipsCarga.TIPO_CARGA_NOTA_AJUSTE://TODO: probar: nota: datos
                setAdjuntoCantidadValida(2);
                setAdjuntoExtensionValida("json");
                setAdjuntoExpresionValida("/(\\.|\\/)(json)$/");
                setTipoReglaCarga(TIPO_REGLA_CARGA_JSON_CUV);
                break;
            default:
                 setAdjuntoCantidadValida(2);
                 setAdjuntoExtensionValida("xml|json");
                 setAdjuntoExpresionValida("/(\\.|\\/)(xml|json)$/");
                 setTipoReglaCarga(TIPO_REGLA_CARGA_XML_CUV);
                 break;
         }
        
    }

    public void limpiarAnexos() {
        this.getListaAdjuntos().clear();
        this.getListaCmFeRipsCarga().clear();
        PrimeFaces.current().ajax().update("frmCrearFactura:tablaAdjuntos");
    }
    
    public void limpiarAnexosMasivos() {
        this.getListaAdjuntos().clear();
        this.getListaCmFeRipsCarga().clear();
        PrimeFaces.current().ajax().update("frmCrearFacturaMasiva:tablaAdjuntos");
    }
    



    public boolean esIntensidadCargaMasiva() {
        return getIntensidadCarga() == INTENSIDAD_CARGA_MASIVA;
    }

    public boolean esIntensidadCargaIndividual() {
        return getIntensidadCarga() == INTENSIDAD_CARGA_INDIVIDUAL;
    }

    private void mostrarErroresInterfaz() {
        PrimeFaces.current().resetInputs("frmErroresFacturaMasiva");
        PrimeFaces.current().ajax().update("frmErroresFacturaMasiva");
        PrimeFaces.current().executeScript("PF('dialogoErroresFacturaMasiva').show()");
    }
 
    private boolean validarArchivoYaAdjunto(String nombreArchivo) {
      
        String extensionArchivo = CmUtilidades.obtenerExtensionArchivo(nombreArchivo);
        
        for (CmFeRipsCargaAdjunto adjunto : getListaAdjuntos()) {
            if (adjunto.getArchivoNombre().equals(nombreArchivo)) {
                addError("Error: El archivo " + nombreArchivo + " está repetido.");
                return false;
            }
        }
        
        if(getListaAdjuntos().size()==getAdjuntoCantidadValida()){
            addError("Error: se sobrepaso la cantidad de archivos para este tipo de carga, cantidad valida : " + getAdjuntoCantidadValida() + ".");
            return false;
        }

        if (  CmUtilidades.esArchivoJsonCuv(nombreArchivo) ) {
            long countCuvJson = getListaAdjuntos().stream()
                    .filter(a -> CmUtilidades.esArchivoJsonCuv(a.getArchivoNombre()))
                    .count();
            if (countCuvJson == 0) {
                return true;
            }
            addError("Error: El archivo que contienen el codigo unico de validacion ya ha sido adjuntado.");
            return false;
        }

        // Validar si ya existe un archivo con la misma extensión
        boolean extensionRepetida = getListaAdjuntos().stream()
                .anyMatch(adjunto -> adjunto.getExtension().equalsIgnoreCase(extensionArchivo) && 
                        ! CmUtilidades.esArchivoJsonCuv(adjunto.getArchivoNombre()));
        
        if (extensionRepetida) {
            addError("Error: Ya existe un archivo con la extensión " + extensionArchivo + ".");
            return false;
        }        

        return true;
    }
    
    private CmFeRipsCargaAdjunto crearAdjunto(String nombreArchivo,  InputStream inputStream) throws Exception {
        
        String ruta = PropApl.getInstance().get(PropApl.CM_FE_RUTA_RIPS_CARGA);
        if (ruta == null || "".equals(ruta) ) {
            throw new Exception("Error: La ruta para realizar el almacenamiento de adjuntos no está definida.");
        }

        
        String extensionArchivo = CmUtilidades.obtenerExtensionArchivo(nombreArchivo);
        
        CmFeRipsCargaAdjunto adjNuevo = new CmFeRipsCargaAdjunto(nombreArchivo, ruta, inputStream);
        adjNuevo.setUsuarioCrea(getConexion().getUsuario().getUsuarioNombre());
        adjNuevo.setTerminalCrea(getConexion().getIp());
        adjNuevo.setFechaHoraCrea(new Date());
        adjNuevo.setArchivoNombreOriginal(nombreArchivo);
        adjNuevo.setIdInsertar(1);
        adjNuevo.setExtension(extensionArchivo.toUpperCase());
        adjNuevo.setTipo((short) CmFeRipsCargaAdjunto.getTipoSegunExtension(extensionArchivo.toUpperCase()));
        return adjNuevo;
    }

    private CmFeRipsCarga crearCarga(CmFeRipsCarga cargaBase)  {
        try {
            CmFeRipsCarga cargaClone = cargaBase.clone();
//            if (cargaBase.getMaeRegimenId() != null
//                    && this.getHashRegimenes().get(cargaBase.getMaeRegimenId()) != null) {
//                Maestro regimenMaestro = this.getHashRegimenes().get(cargaBase.getMaeRegimenId());
//                cargaClone.setMaeRegimenCodigo(regimenMaestro.getValor());
//                cargaClone.setMaeRegimenValor(regimenMaestro.getNombre());
//            }
            cargaClone.setEmpresa(new Empresa(this.getConexion().getEmpresa().getId()));
            cargaClone.setEstado(CmFeRipsCarga.ESTADO_EN_COLA);
            cargaClone.setFechaHoraInicio(new Date());
            cargaClone.setTiempo(0);
            cargaClone.setOrigen(CmFeRipsCarga.ORIGEN_CARGA_MASIVA);
            this.auditoriaGuardar(cargaClone);
            return cargaClone;
        } catch (CloneNotSupportedException ex) {
            CmFeRipsCarga cargaClone = new CmFeRipsCarga();
            return cargaClone;
        }
    }

    
    private String obtenerErrorStrFormateado(Exception excepcion) {
        return Optional.ofNullable(excepcion.getMessage()).orElse(excepcion.toString());
    }
    
    private String validarReglaXmlJsonCuv(List<CmFeRipsCargaAdjunto> archivos) {
        int cantidadXml = 0;
        int cantidadJson = 0;
        boolean tieneCUV = false;
        
        for (CmFeRipsCargaAdjunto archivo : archivos) {
            String extensionArchivo = archivo.getExtension().toLowerCase();
            if (extensionArchivo.equals("xml") ) {
                cantidadXml++;
            } else if (extensionArchivo.equals("json")) {
                if (archivo.getArchivoNombre().endsWith(CmUtilidades.POSTFIJO_ARCHIVO_UNICO_VALIDACION)) {
                    tieneCUV = true;
                } else {
                    cantidadJson++;
                }
            }
        }
      
        if( cantidadXml == 1 && tieneCUV && cantidadJson == 1){
          return "";
        }
        
        String mensaje = "Hace falta los archivos : " ;
        mensaje += cantidadXml == 0 ? ".xml " : "";
        mensaje += cantidadJson == 0 ? ".json " : "";
        mensaje += !tieneCUV ? CmUtilidades.POSTFIJO_ARCHIVO_UNICO_VALIDACION : "";
        return mensaje;
    }
    
    private String validarReglaJsonCuv(List<CmFeRipsCargaAdjunto> archivos) {
        int cantidadJson = 0;
        boolean tieneCUV = false;
        
        for (CmFeRipsCargaAdjunto archivo : archivos) {
            String extensionArchivo = archivo.getExtension().toLowerCase();
            if (extensionArchivo.equals("json")) {
                if (archivo.getArchivoNombre().endsWith(CmUtilidades.POSTFIJO_ARCHIVO_UNICO_VALIDACION)) {
                    tieneCUV = true;
                } else{
                    cantidadJson++;
                }
            }
        }

       if(cantidadJson == 1 && tieneCUV){
            return "";
       }
       
        String mensaje =  "Hace falta los archivos : ";
        mensaje += cantidadJson == 0 ? ".json" : "";
        mensaje += !tieneCUV ? CmUtilidades.POSTFIJO_ARCHIVO_UNICO_VALIDACION : "";
        return mensaje;
       
    }
    
    private  String validarReglaXmlCuv(List<CmFeRipsCargaAdjunto> archivos) {
        int cantidadXml = 0;
        boolean tieneCUV = false;
        
        for (CmFeRipsCargaAdjunto archivo : archivos) {
            String extensionArchivo = archivo.getExtension().toLowerCase();
            if (extensionArchivo.equals("xml") ) {
                cantidadXml++;
            } else if ( extensionArchivo.equals("json") && 
                        archivo.getArchivoNombre().endsWith(CmUtilidades.POSTFIJO_ARCHIVO_UNICO_VALIDACION) ) {
                tieneCUV = true;
            }
        }

        if(cantidadXml == 1 && tieneCUV){
          return "";
        }
        
        String mensaje = "Hace falta los archivos : " ;
        mensaje += cantidadXml == 0 ? ".xml " : "";
        mensaje += !tieneCUV ? CmUtilidades.POSTFIJO_ARCHIVO_UNICO_VALIDACION : "";
        return mensaje;
       
    }
    
    public boolean validarReglasCargasAdjuntos(List<CmFeRipsCargaAdjunto> archivos, int tipoRegla){
    
        String mensajeRegla;
        
        switch (tipoRegla) {

            case TIPO_REGLA_CARGA_XML_CUV:
                mensajeRegla = validarReglaXmlCuv(archivos);
                if(mensajeRegla.isEmpty()){
                    return true;
                }
                addError(mensajeRegla);
                break;
            case TIPO_REGLA_CARGA_XML_JSON_CUV:
                mensajeRegla = validarReglaXmlJsonCuv(archivos);
                if (mensajeRegla.isEmpty()) {
                    return true;
                }
                addError(mensajeRegla);
                break;
            case TIPO_REGLA_CARGA_JSON_CUV:
                mensajeRegla = validarReglaJsonCuv(archivos);
                if (mensajeRegla.isEmpty()) {
                    return true;
                }
                addError(mensajeRegla);
                break;

        }
        return false;
         
    }
    
    public String validarNombresCapitaPeriodoXmlJsonCuv(List<CmFeRipsCargaAdjunto> archivos) {

        String nombreBaseComun = null;
        String cadigoFactura = this.getObjeto().getFacturaNumeroPeriodoAsociado();
        
        if(this.getObjeto().getTipo() !=  CmFeRipsCarga.TIPO_CARGA_CAPITA_PERIODO){
            return "";
        }

        for (CmFeRipsCargaAdjunto archivo : archivos) {
            String nombreArchivo = archivo.getArchivoNombre();
            String extensionArchivo = archivo.getExtension().toLowerCase();
            if (extensionArchivo.equals("xml")) {
                String nombreBase = nombreArchivo.replaceAll("\\.[a-zA-Z]+$", "");
                if (nombreBaseComun == null) {
                    nombreBaseComun = nombreBase;
                } else {
                    if (!nombreBaseComun.equals(nombreBase)) {
                        return String.format("El nombre del archivo xml %s , no es igual al de CUV %s" 
                                ,nombreBase, nombreBaseComun);
                    }
                }
            } else if (extensionArchivo.equals("json")) {
                String nombreBase = archivo.getArchivoNombre().replaceAll("(_CUV)?\\.[a-zA-Z]+$", "");
                if (nombreArchivo.endsWith(CmUtilidades.POSTFIJO_ARCHIVO_UNICO_VALIDACION)) {
                    if (nombreBaseComun == null) {
                        nombreBaseComun = nombreBase;
                    } else {
                        if (!nombreBaseComun.equals(nombreBase)) {
                            return String.format("El nombre del archivo CUV  %s , no es igual al xml %s",
                                     nombreBase, nombreBaseComun);
                        }
                    }
                } else {
                    if (!nombreArchivo.toLowerCase().contains(cadigoFactura.toLowerCase())) {
                        return String.format("El nombre del archivo json %s , no contiene en su nombre la factura de referencia %s",
                                 nombreArchivo, cadigoFactura);
                    }
                }
            }
        }

        return "";
    }
    
    
    private Set<String> obtenerNombreBase(List<CmFeRipsCargaAdjunto> archivos) {
        Set<String> nombresBase = new HashSet<>();
        for (CmFeRipsCargaAdjunto archivo : archivos) {
            String nombreBase = archivo.getArchivoNombre().replaceAll("(_CUV)?\\.[a-zA-Z]+$", "");
            if (!nombreBase.isEmpty()) {
                nombresBase.add(nombreBase);
            }
        }
        return nombresBase;
    }
    
    public  Maestro buscarMaestroRegimenPorNombre(HashMap<Integer, Maestro> getHashRegimenes, String nombreBuscado) {
        for (Map.Entry<Integer, Maestro> entry : getHashRegimenes.entrySet()) {
            Maestro maestro = entry.getValue(); 
            if (maestro.getNombre().equalsIgnoreCase(nombreBuscado)) {
                return maestro; 
            }
        }
        return null;
    }

    public void limpiarSelectorSoportes() {
        DataTable dataTable2 = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmVerSoportes:tablaSoportes");
        dataTable2.reset();
        PrimeFaces.current().resetInputs("frmVerSoportes");
    }
    
    public void limpiarListaCargasAsignar() {
        DataTable dataTable2 = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("frmVerAsignarRadicador:tablaRegistrosRadicador");
        dataTable2.reset();
        PrimeFaces.current().resetInputs("frmVerAsignarRadicador");
        getHashCargasSelecionadasRadicador().clear();
        getListaCargasSeleccionadaAsignar().clear();
    }
    
    public void limpiarSeleccionCargasAsignar() {
        getListaCargasSeleccionadaAsignar().clear();
        getHashCargasSelecionadasRadicador().clear();
        PrimeFaces.current().executeScript("PF('tablaWidgetRadicador').unselectAllRows();");
    }
    
    private int determinarCategoriaPorCobertura(List<CmFeRipsCarga> registros) {
        Map<Boolean, Long> conteoCobertura = registros.stream()
                .collect(Collectors.partitioningBy(
                        CmFeRipsCarga::getCobertura,
                        Collectors.counting()
                ));

        long coberturaPbs = conteoCobertura.getOrDefault(CmFeRipsCarga.COBERTURA_PBS, 0L);
        long coberturaNoPbs = conteoCobertura.getOrDefault(CmFeRipsCarga.COBERTURA_NO_PBS, 0L);

        if (coberturaPbs > 0 && coberturaNoPbs > 0) {
            return CmFeRipsCargaServicioImpl.COBERTURA_PBS_CONBINADA;
        } else if (coberturaPbs > 0) {
            return CmGrupo.CATEGORIA_PBS;
        } else if (coberturaNoPbs > 0) {
            return CmGrupo.CATEGORIA_NO_PBS;
        }
        return 0; 
    }
    
    private boolean validarExistenciaSeleccionCargasAsignables() {
        if (getListaCargasSeleccionadaAsignar().isEmpty()) {
            this.addError("Para realizar la asignación debe seleccionar cargas.");
            return false;
        }
        return true;
    }

    private boolean validarSeleccionUnicaModalidadContrato(List<CmFeRipsCarga> registrosCargasAsignables) {

        String mensajeError = "";
        String valorReferencia = registrosCargasAsignables.get(0).getMaeContratoModalidadValor();

        Optional<CmFeRipsCarga> registroDiferente = registrosCargasAsignables.stream()
                .skip(1)
                .filter(registro -> !Objects.equals(valorReferencia, registro.getMaeContratoModalidadValor()))
                .findFirst();

        if (registroDiferente.isPresent()) {
            CmFeRipsCarga registro = registroDiferente.get();
            mensajeError = String.format(
                    "Error en asignación: La carga con ID %d tiene modalidad '%s' (se esperaba '%s'). "
                    + "Todas las cargas deben tener la misma modalidad de contrato.",
                    registro.getId(),
                    registro.getMaeContratoModalidadValor(),
                    valorReferencia
            );
        }

        if (!mensajeError.isEmpty()) {
            this.addError(mensajeError);
            return false;
        }

        return true;
    }
    
    public boolean validarReglaAuxiliarRadicacion(CmFeRipsCarga carga) {
        if (this.isAccionAdicional6()) {
            if (carga.getRadicadorAsignado().getId() == null) {
                return true;
            }
            return carga.getRadicadorAsignado().getId().equals(this.getConexion().getUsuario().getId());
        }
        return true;
    }
     
    public void selectCheckbox(SelectEvent event) {
        CmFeRipsCarga carga = (CmFeRipsCarga) event.getObject();
        if (getHashCargasSelecionadasRadicador().get(carga.getId()) == null) {
            getHashCargasSelecionadasRadicador().put(carga.getId(), carga);
        }
    }

    public void unSelectCheckbox(UnselectEvent event) {
        CmFeRipsCarga carga = (CmFeRipsCarga) event.getObject();
        if (getHashCargasSelecionadasRadicador().get(carga.getId()) != null) {
            getHashCargasSelecionadasRadicador().remove(carga.getId());
        }
    }
    
      public void procesarFacturasSeleccionadas(ToggleSelectEvent event) {
        if (event.isSelected()) {
            for (CmFeRipsCarga carga : getListaCargasSeleccionadaAsignar()) {
                if (getHashCargasSelecionadasRadicador().get(carga.getId()) == null) {
                    getHashCargasSelecionadasRadicador().put(carga.getId(), carga);
                }
            }

            setTodaCargasSelecionadasRadicar(true);

        } else {
            for (CmFeRipsCarga carga : getRegistrosCargasAsignar()) {
                if (getHashCargasSelecionadasRadicador().get(carga.getId()) != null) {
                    getHashCargasSelecionadasRadicador().remove(carga.getId());
                }
            }
            setTodaCargasSelecionadasRadicar(false);
        }
    }
      
    public void onRowSelect(SelectEvent event) {
        CmFeRipsCarga cargaIn = (CmFeRipsCarga) event.getObject();

        if (isTodaCargasSelecionadasRadicar()) {
            for (CmFeRipsCarga carga : getListaCargasSeleccionadaAsignar()) {
                if (getHashCargasSelecionadasRadicador().get(carga.getId()) != null) {
                    getHashCargasSelecionadasRadicador().remove(carga.getId());
                }
            }
            setTodaCargasSelecionadasRadicar(false);
        }
        if (getHashCargasSelecionadasRadicador().get(cargaIn.getId()) == null) {
            getHashCargasSelecionadasRadicador().put(cargaIn.getId(), cargaIn);
        }
    }

    public void onRowUnselect(SelectEvent event) {
        CmFeRipsCarga carga = (CmFeRipsCarga) event.getObject();
        if (getHashCargasSelecionadasRadicador().get(carga.getId()) != null) {
            getHashCargasSelecionadasRadicador().remove(carga.getId());
        }
    }
 
    public String getRowStyleClass(CmFeRipsCarga item) {
        return getHashCargasSelecionadasRadicador().containsKey(item.getId()) ? "ui-state-highlight" : "";
    }
    
    public String asignarIndicadorSinEnvioRadicacion(CmFeRipsCarga carga, int indicador) {
        String estilo = "display:none;";

        //No  es factura ni esta estado auditoria
        if (!(carga.getEstado() < CmFeRipsCarga.ESTADO_EN_COLA_AUDITORIA &&
            CmFeRipsCarga.TIPO_CARGA_FACTURA == carga.getTipo())) {
            return "display:none;";
        }

        switch (indicador) {
            case INDICADOR_PENDIENTE_FE:
                if (carga.getDe5601SoporteFe() == null || carga.getDe5601SoporteFe() == false) {
                    estilo = "color: red; cursor: pointer;";
                    return estilo;
                }

                break;
                
            case INDICADOR_PENDIENTE_SOPORTES: 
                if (Boolean.TRUE.equals(carga.getDe4401ProfesionalRed())
                        && Boolean.TRUE.equals(carga.getDe4402ProfesionalIndependiente())
                        && Boolean.TRUE.equals(carga.getDe5601Soportes())) {
                    return "display:none;";
                }

                if ( carga.getDe5601Soportes() != null &&  
                     Boolean.FALSE.equals(carga.getDe5601Soportes()) ) {
                     return "color: #ff9e00; cursor: pointer;";
                }
                break;
        }

        return estilo;
    }


}
