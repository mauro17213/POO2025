/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.aseguramiento.bean;

import com.saviasaludeps.savia.dominio.administracion.GnUbicacionBarrio;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroAccion;
import com.saviasaludeps.savia.dominio.administracion.Modulo;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAdjunto;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliadoContacto;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegEncuestaPregunta;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegPortabilidad;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegRadicadoNovedad;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegRegistroNovedad;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegTabulacionEncuesta;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegTraslado;
import com.saviasaludeps.savia.dominio.aseguramiento.CertificadoAfiliacion;
import com.saviasaludeps.savia.dominio.aseguramiento.CertificadoContactoAfiliado;
import com.saviasaludeps.savia.dominio.aseguramiento.FormularioAfiliadoRes1823;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.aseguramiento.ReporteAfiliacion;
import com.saviasaludeps.savia.dominio.aseguramiento.ReporteEncuesta016;
import com.saviasaludeps.savia.dominio.especial.PeAfiliadosPrograma;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.web.aseguramiento.servicio.AfiliadosServicioIface;
import com.saviasaludeps.savia.web.aseguramiento.servicio.AfiliadosServicioImpl;
import com.saviasaludeps.savia.web.aseguramiento.utilidades.AfiliadoParametro;
import com.saviasaludeps.savia.web.utilidades.CompatibilidadPF;
import com.saviasaludeps.savia.web.utilidades.Url;
import static com.saviasaludeps.savia.web.utilidades.Url.ACCION_ADICIONAL_1;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.jsf.FacesContextUtils;

/**
 *
 * @author José Pérez Hernández
 */
@Named
@ViewScoped
public class AfiliadosBean extends Url {

//    public final static String ESTADO_AFILIACION_PENDIENTE_SOPORTE = "777";
//    public final static String ESTADO_AFILIACION_SUSPENDIDO = "103";
//    public final static String ESTADO_AFILIACION_RETIRADO = "104";
//    public final static String ESTADO_AFILIACION_FALLECIDO = "107";
//    //2021-09-27 jyperez nuevos estados REQ 140
//    public final static String ESTADO_AFILIACION_PROTECCION_LABORAL = "779";
//    public final static String ESTADO_AFILIACION_ACTIVO_POR_EMERGENCIA = "778";
//
//    //2021-05-20 jyperez se actualiza el código al valor que tiene el maestro de régimen
//    public static final String REGIMEN_SUBSIDIADO = "01";
//    public static final String REGIMEN_CONTRIBUTIVO = "02";
//    public static final String REGIMEN_SUBSIDIADO_ANTIGUO = "1";
//    public static final String REGIMEN_CONTRIBUTIVO_ANTIGUO = "2";
//
//    public static final String MODELO_LIQUIDACION_CAPITA = "0";
//    public static final String MODELO_LIQUIDACION_EVENTO = "1";
//
//    public final static int IDENTIFICADOR_DEPARTAMENTO_POR_DEFECTO = 2;
//    public final static int IDENTIFICADOR_MEDELLIN = 3;
//
//    public final static String NIVEL_1_SISBEN = "1";
//    public final static String NIVEL_2_SISBEN = "2";
//
//    public final static String ZONA_URBANA = "U";
//    public final static String ZONA_RURAL = "R";
//
//    //public final static String IDENTIFICADOR_DIVIPOLA_POR_DEFECTO = "05001";
//    public final static String IDENTIFICADOR_UBICACION_ID_POR_DEFECTO = "3";
//
//    public final static String TIPO_AFILIADO_BENEFICIARIO = "102";
//    public final static String TIPO_AFILIADO_ADICIONAL = "104";
//    public final static String ORIGEN_AFILIADO_NACIMIENTO = "NPNA";
//    public final static String ORIGEN_AFILIADO_TRASLADO_OTRA_EPS = "TSOE";
//    public final static String ORIGEN_AFILIADO_TRASLADO_EPS_LIQUIDADA = "296";
//    public final static String ORIGEN_AFILIADO_AFILIACION_TRANSACCIONAL_SAT = "ASAT";
//
//    public final static String TIPO_DOCUMENTO_CEDULA_DE_CIUDADANIA = "CC";
//    public final static String TIPO_DOCUMENTO_ADULTO_SIN_IDENTIFICACION = "AS";
//    public final static String TIPO_DOCUMENTO_CARNE_DIPLOMATICO = "CD";
//    public final static String TIPO_DOCUMENTO_CEDULA_EXTRANGERIA = "CE";
//    public final static String TIPO_DOCUMENTO_CERTIFICADO_NACIDO_VIVO = "CN";
//    public final static String TIPO_DOCUMENTO_MENOR_SIN_IDENTIFICACION = "MS";
//    public final static String TIPO_DOCUMENTO_NIT = "NI";
//    public final static String TIPO_DOCUMENTO_NIUP = "NU";
//    public final static String TIPO_DOCUMENTO_PASAPORTE = "PA";
//    public final static String TIPO_DOCUMENTO_PERMISO_ESPECIAL_PERMANENCIA = "PE";
//    public final static String TIPO_DOCUMENTO_REGISTRO_CIVIL = "RC";
//    public final static String TIPO_DOCUMENTO_RUT = "RUT";
//    public final static String TIPO_DOCUMENTO_SALVOCONDUCTO = "SC";
//    public final static String TIPO_DOCUMENTO_TARJETA_IDENTIDAD = "TI";
//    //2021-09-27 jyperez REQ 143 nuevo tipo documento
//    public final static String TIPO_DOCUMENTO_PERMISO_PROTECCION_TEMPORAL = "PT";
//    public final static String GRUPO_POBLACIONAL_POBLACION_SISBENIZADA = "5";
//    public final static String GRUPO_POBLACIONAL_POBLACION_31 = "31";
//    public final static String CONDICION_DISCAPACIDAD_TEMPORAL = "2";
//    public final static String CAUSA_NOVEDAD_MOVILIDAD_INGRESO_SUBSIDIADO = "IS-36";
//    
//    //2021-10-29 jyperez se definen valores para origen ultimo registro
//    public final static int ORIGEN_ULTIMO_REGISTRO_PANTALLA = 1;
//    
//    //2021-05-14 jyperez Se adicionan los tipos de certificados a gestionar
//    public final static int TIPO_CERTIFICADO_AFILIACION = 1;
//    public final static int TIPO_CERTIFICADO_PORTABILIDAD = 2;
//    public final static int TIPO_CERTIFICADO_FISCALIA = 3;
//    public final static int CERTIFICADO_DIAS_VIGENCIA = 30;
//    
//    //2022-01-13 jyperez se adiciona etnia indigena
//    public final static String ETNIA_INDIGENA = "1";
//    
//    public final static String TIPO_CONTACTO_TELEFONO = "1";
//    public final static String TIPO_CONTACTO_CELULAR = "2";
//    public final static String TIPO_CONTACTO_CORREO_ELECTRONICO = "3";
//    public final static int TIPO_ARCHIVO_NOVEDADES = 50;
//    
//    //2022-04-25 jyperez se adiciona los niveles de sisben
//    public final static String GRUPO_SISBEN_A = "A";
//    public final static String GRUPO_SISBEN_B = "B";
//    public final static String GRUPO_SISBEN_C = "C";
//    public final static String GRUPO_SISBEN_D = "D";
//    
//    //2022-07-21 jyperez adicionamos parámetro población no pobre no vulnerable
//    public final static String GRUPO_POBLACIONAL_POBLACION_NO_POBRE_NO_VULNERABLE = "34";
//    
//    //2022-09-15 jyperez adición parámetros para validación de afiliado sin encuesta sisben 4
//    public final static String METODOLOGIA_GRUPO_POBLACIONAL_SISBEN_3 = "1";
//    
//    //2022-12-22 jyperez parametro validación pais Venezuela (se configura codigo prefijo)
//    public final static String GN_UBICACIONES_PAIS_VENEZUELA = "862";
    
    @Autowired
    private AfiliadosServicioIface afiliadoServicio;
    private AsegAfiliado objeto;
    private AsegAfiliado objetoAnterior;
    private AsegTraslado objetoTraslado;

    private List<AsegRadicadoNovedad> listaRadicados = new ArrayList();
    private List<AsegAdjunto> listaAdjuntos = new ArrayList();
    private AsegAdjunto adjunto;

    private List<AsegTabulacionEncuesta> ListaEncuestaAfiliado;
    private List<AsegEncuestaPregunta> listaPreguntasEncuesta;
    private List<AsegAfiliado> registros;
    private LazyDataModel<AsegAfiliado> lazyAfiliado;
    private List<Maestro> listaTiposDocumento;
    private HashMap<Integer, Maestro> hashTiposDocumento;
    private List<Maestro> listaTiposGenero;
    private HashMap<Integer, Maestro> hashTiposGenero;
    //2023-06-20 jyperez N45 genero identificación
    private List<Maestro> listaTiposGeneroIdentificacion;
    private HashMap<Integer, Maestro> hashTiposGeneroIdentificacion;
    private List<Maestro> listaEstadosAfiliacion;
    private List<Maestro> listaEstadosAfiliacionListar;
    private HashMap<String, Maestro> hashValorEstadosAfiliacion;
    private HashMap<Integer, Maestro> hashEstadosAfiliacion;
    private HashMap<Integer, Maestro> hashEstadosAfiliacionCompleto;
    private List<Maestro> listaOrigenAfiliado;
    private HashMap<Integer, Maestro> hashOrigenAfiliado;
    private List<Maestro> listaGrupoPoblacional;
    private HashMap<Integer, Maestro> hashGrupoPoblacional;
    private List<Maestro> listaCausaNovedad;
    private HashMap<Integer, Maestro> hashCausaNovedad;
    private HashMap<String, Maestro> hashCausaNovedadValor;
    private List<Maestro> listaCajaCompensacion;
    private HashMap<Integer, Maestro> hashCajaCompensacion;
    private List<Maestro> listaARL;
    private HashMap<Integer, Maestro> hashARL;
    private List<Maestro> listaAFP;
    private HashMap<Integer, Maestro> hashAFP;
    private List<Maestro> listaActividadEconomica;
    private HashMap<Integer, Maestro> hashActividadEconomica;
    //2021-05-20 jyperez nuevos maestros Regimen y Nivel Sisben
    private List<Maestro> listaRegimen;
    private HashMap<Integer, Maestro> hashRegimen;
    private List<Maestro> listaNivelSisbenListar;
    private List<Maestro> listaNivelSisbenNuevo;
    private HashMap<Integer, Maestro> hashNivelSisbenNuevo;
    private HashMap<String, Maestro> hashValorNivelSisbenNuevo;
    //2021-10-28 jyperez nuevos maestros
    private List<Maestro> listaCategoriaIbc;
    private HashMap<Integer, Maestro> hashCategoriaIbc;
    private List<Maestro> listaModeloLiquidacion;
    private HashMap<String, Maestro> hashValorModeloLiquidacion;
    private HashMap<Integer, Maestro> hashModeloLiquidacion;
    private List<Maestro> listaEstadoCivil;
    private HashMap<Integer, Maestro> hashEstadoCivil;
    private List<Maestro> listaParentescoCotizante;
    private HashMap<Integer, Maestro> hashParentescoCotizante;
    private List<Maestro> listaZona;
    private HashMap<Integer, Maestro> hashZona;
    private List<Maestro> listaEtnia;
    private HashMap<Integer, Maestro> hashEtnia;
    private List<Maestro> listaTipoDiscapacidad;
    private HashMap<Integer, Maestro> hashTipoDiscapacidad;
    private List<Maestro> listaCondicionDiscapacidad;
    private HashMap<Integer, Maestro> hashCondicionDiscapacidad;
    private List<Maestro> listaComunidadEtnica;
    private HashMap<Integer, Maestro> hashComunidadEtnica;
    //2022-02-21 jyperez nuevo maestro
    private List<Maestro> listaMetodologiaGrupoPoblacional;
    private HashMap<Integer, Maestro> hashMetodologiaGrupoPoblacional;
    
    private boolean estaHabilitadoCampo = false;

    private List<Ubicacion> ubicaciones;
    private HashMap<Integer, Ubicacion> ubicacionesRecursiva;
    //2021-11-12 jyperez lista Paises
    private List<Ubicacion> listaPaises;
    private HashMap<Integer,Ubicacion> hashPaises;
    //2023-06-20 jyperez lista Paises
    private List<Ubicacion> listaLugarNacimiento;
    private HashMap<Integer,Ubicacion> hashLugarNacimiento;

    private List<Maestro> listadoEps;
    private HashMap<Integer, Maestro> listadoEpsRecursiva;

    private boolean beneficiario;

    private List<CntPrestadorSede> listaPrestadoresSedes;
    private HashMap<String, CntPrestadorSede> hashPrestadoresSedes;

    private List<AsegAfiliado> listaGrupoFamiliarAfiliado;

    private List<AsegPortabilidad> listaPortabilidadAfiliado;
    
    private List<PeAfiliadosPrograma> listaProgramaAfiliado;

    private Ubicacion ubicacionConsulta;
    //private CntPrestadorSede prestadorSedeConsulta; OK

    private boolean afiliadoExistente;
    private String mensajeAfiliadoExistente;

    private boolean afiliadoCFExistente;
    private String mensajeAfiliadoCFExistente;

    private Date fechaActual;

    private List<AsegRegistroNovedad> listaHistorialNovedad;
    private LazyDataModel<AsegRegistroNovedad> lazyHistorial;
    private ParamConsulta paramConsultaHistorial;

    // Objetos carga de archivos
    //private List<UploadedFile> listArchivo;
    private UploadedFile archivo;
    private List<AsegAdjunto> listaArchivos;
    private int tipoArchivo;
    private Date fechaNovedad;

    //variables edición
    //private int estadoAfiliado; ok
    //private String regimenAfiliado; ok
    private boolean habilitarCategoriaIBC;
    //private int municipioAfiliado; ok
    //private String primerNombre; ok
    //private String primerApellido; ok
    //private Date fechaNacimiento; ok
    //private int maeTipoDocumento; ok
    //private String numeroDocumento; ok
    private boolean consultarResidencia;
    private boolean trasladoExistente;
    //2020-08-24 jyperez INC 279
    private boolean trasladoEPS;

    // variables hash
    private HashMap<Integer, Maestro> hashTipoAfiliado;
    private HashMap<Integer, Maestro> hashTipoAfiliadoCompleto;
    //2021-1029 jyperez creamos la lista de tiposAfiliado
    private List<Maestro> listaTipoAfiliado;
    private List<Maestro> listaTipoAfiliadoCompleto;

    // variables hash
    private HashMap<Integer, Maestro> hashNivelSisben;

//    private List<AsegRadicadoNovedad> listaRadicadoAfiliado;
    private AsegRadicadoNovedad objetoRadicado;

    //Objetos reporte 016
    private ReporteEncuesta016 reporte;
    private List<ReporteEncuesta016> listaReporte016;

    //Objetos reporte afiliciacion
    private ReporteAfiliacion reporteAfiliacion;
    private List<ReporteAfiliacion> listaReporteAfiliacion;

    //2020-10-28 jyperez Ajustes Adición Homónimos
    private List<String> registrosHomonimos;
    //variable con la cual controlaremos la accion de la ventaja (acción crear = true - acción modificar = false)
    private boolean accionCreacion;

    // 2020-11-24 jyperez ajustes guardar afiliado histórico - necesitamos el prestador sede anterior.
    private CntPrestadorSede prestadorAnterior;

    //2021-04-07 jyperez INC 0757 creamos variable de mensaje.
    private String mensajeDatosBasicos;

    //2021-05-04 jyperez nuevos campos para el tema del grupo sisben
    private List<Maestro> listaGrupoSisben;
    private HashMap<Integer, Maestro> hashGrupoSisben;
    private List<Maestro> listaSubGrupoSisben;
    //2022-04-25 jyperez se comenta la lista debido a que no se usará
    //private List<Maestro> listaSubGrupoSisbenFiltro;
    private HashMap<Integer, Maestro> hashSubGrupoSisben;
    private boolean deshabilitaSubGrupoSisben;
    //2022-04-25 jyperez se comenta esta variable teniendo en cuenta que se incluyen nuevos campos en el objeto
    //private int grupoSeleccionado;
    
    //2021-05-12 jyperez campos certificado Afiliacion
    private CertificadoAfiliacion certificadoAfiliacion;
    //2024-12-06 jyperez campos formulario afiliación res 1823 de 2024
    private FormularioAfiliadoRes1823 formularioAfiliadoRes1823;

    private transient InputStream adjuntoStream;
    
    //2021-05-27 jyperez desarrollo contactos
    private List<AsegAfiliadoContacto> listaContactos;
    private List<AsegAfiliadoContacto> listaContactosBorrar;
    private int contadorIdContacto;
    private AsegAfiliadoContacto contacto;
    private List<Maestro> listaTiposContacto;
    private HashMap<Integer, Maestro> hashTiposContacto;
    //2021-07-08 jyperez INC 857 variable para solicitar obligatoriedad de SubGrupoSisben
    private boolean requeridoSubGrupoSisben;
    
    //2021-12-17 jyperez variable para administrar Afiliado Activo Contributivo
    private boolean afiliadoActivoContributivo;
    //2022-06-03 jyperez adición variables a usar en validación de contactos
    private List<String> valoresNoPermitidosTelefonoFijo = new ArrayList();
    //2022-06-08 jyperez adición variables contribución solidaria
    private boolean afiliadoContribucionSolidaria;
    private String mensajeAfiliadoContribucionSolidaria;
    private List<Maestro> listaPorcentajeDistribucion;
    private HashMap<Integer, Maestro> hashPorcentajeDistribucion;
    //2022-09-15 jyperez adición variables afiliado sin encuesta sisben 4
    private boolean afiliadoSinEncuestaSisben4;
    private String mensajeAfiliadoSinEncuestaSisben4;
    //2022-09-23 jyperez adición variables validación portabilidad vigente
    private boolean afiliadoPortabilidadVigente;
    private AsegPortabilidad portabilidadVigente;
    //2024-01-10 jyperez IS-58 retiro por duplicado variables auxiliares
    private boolean bloqueoDuplicado;
    //2024-03-27 jyperez se crea variable para controlar dialogo de Dirección y tipo de Dirección en el afiliado
    private int tipoDireccion;
    
    //2024-10-25 jyperez campos certificado Contactos Afiliado
    private CertificadoContactoAfiliado certificadoContactoAfiliado;
    private List<CertificadoContactoAfiliado> listaCertificadoContactoAfiliado;
    private String nombreSolicitante;
    private String cargoSolicitante;
    private String enteControlSolicitante;
    
    //2025-03-13 jyperez campos Ubicacion Barrios
    private List<GnUbicacionBarrio> listaUbicacionBarrios;
    private HashMap<Integer,GnUbicacionBarrio> hashUbicacionBarrios;
    private boolean habilitaListaBarrio;
    private Ubicacion ciudadMedellin;
    
    //2025-08-04 jyperez campos georeferenciación
    private MapModel<Long> registrosMapa;
    private double centroLatitud = AfiliadoParametro.CENTRO_LATITUD_INICIAL;
    private double centroLongitud = AfiliadoParametro.CENTRO_LONGITUD_INICIAL;
    private int profundidad = AfiliadoParametro.PROFUNDIDAD_INICIAL;
    private String rutaGoogleMapsEmpresarial;// = AfiliadoParametro.RUTA_GOOGLE_MAPS_KEY;
    private String rutaImagenes;
    private String direccionCompleta;
    private double campoLatitud;
    private double campoLongitud;

//    @Autowired
//    private AseguramientoSingle aseguramientoSingle;

    public AfiliadosBean() {
        contadorIdContacto = -1;
        accionCreacion = true;
        deshabilitaSubGrupoSisben = true;
        requeridoSubGrupoSisben = false;
        afiliadoActivoContributivo = false;
        registrosHomonimos = new ArrayList<>();
        fechaActual = new Date();
        this.objeto = new AsegAfiliado();
        this.objetoAnterior = new AsegAfiliado();
        this.objeto.setPrimariaPrestadorSede(new CntPrestadorSede());
        this.objetoTraslado = new AsegTraslado();
        //2025-03-13 jyperez jyperez inicializamos el objeto de ubicacionBarrio
        this.objeto.setUbicacionBarrio(new GnUbicacionBarrio());
        this.listaUbicacionBarrios = new ArrayList<>();
        this.hashUbicacionBarrios = new HashMap();
        this.habilitaListaBarrio = false;
        this.ciudadMedellin = new Ubicacion();
        //2022-06-03 jyperez se adiciona inicialización de lista de valores no permitidos
        valoresNoPermitidosTelefonoFijo = new ArrayList<>();
        valoresNoPermitidosTelefonoFijo.add("0000000000");
        valoresNoPermitidosTelefonoFijo.add("1111111111");
        valoresNoPermitidosTelefonoFijo.add("2222222222");
        valoresNoPermitidosTelefonoFijo.add("3333333333");
        valoresNoPermitidosTelefonoFijo.add("4444444444");
        valoresNoPermitidosTelefonoFijo.add("5555555555");
        valoresNoPermitidosTelefonoFijo.add("6666666666");
        valoresNoPermitidosTelefonoFijo.add("7777777777");
        valoresNoPermitidosTelefonoFijo.add("8888888888");
        valoresNoPermitidosTelefonoFijo.add("9999999999");
        //inicializarListaGrupoSisben();
        //this.prestadorSedeConsulta = new CntPrestadorSede(); OK
        ListaEncuestaAfiliado = new ArrayList<>();
        listaGrupoFamiliarAfiliado = new ArrayList<>();
        listaPortabilidadAfiliado = new ArrayList<>();
        this.objetoRadicado = new AsegRadicadoNovedad();
//        listaRadicadoAfiliado = new ArrayList<>();
        listaArchivos = new ArrayList<>();
        this.reporte = new ReporteEncuesta016();
        Modulo mod = super.validarModulo(Modulo.ID_ASEGURAMIENTO_GESTION_AFILIADOS);
        if (mod == null) {
            super.redireccionar("/savia/home.faces");
        } else {
            super.setModulo(mod);
            super.getParamConsulta().setEmpresaId(super.getConexion().getEmpresa().getId());
            lazyAfiliado = new LazyDataModel<AsegAfiliado>() {

                private List<AsegAfiliado> vendedores;
                
                @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }
			

                @Override
                public List<AsegAfiliado> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
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
                public String getRowKey(AsegAfiliado vendedor) {
                    return vendedor.getId().toString();
                }

                @Override
                public AsegAfiliado getRowData(String vendedorId) {
                    Integer id = Integer.valueOf(vendedorId);
                    for (AsegAfiliado vendedor : vendedores) {
                        if (id.equals(vendedor.getId())) {
                            return vendedor;
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
        //jyperez aca se llama la carga inicial
        getAfiliadosServicio().cargaInicial(this);
        super.setAccion(Url.ACCION_LISTAR);
        procesoFinal();
    }

    public AsegAfiliado getObjeto() {
        return objeto;
    }

    public void setObjeto(AsegAfiliado objeto) {
        this.objeto = objeto;
    }

    public List<AsegRadicadoNovedad> getListaRadicados() {
        return listaRadicados;
    }

    public void setListaRadicados(List<AsegRadicadoNovedad> listaRadicados) {
        this.listaRadicados = listaRadicados;
    }

    public List<AsegAdjunto> getListaAdjuntos() {
        return listaAdjuntos;
    }

    public void setListaAdjuntos(List<AsegAdjunto> listaAdjuntos) {
        this.listaAdjuntos = listaAdjuntos;
    }

    public AsegAdjunto getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(AsegAdjunto adjunto) {
        this.adjunto = adjunto;
    }

    public List<AsegAfiliado> getRegistros() {
        return registros;
    }

    public void setRegistros(List<AsegAfiliado> registros) {
        this.registros = registros;
    }

    public LazyDataModel<AsegAfiliado> getLazyAfiliado() {
        return lazyAfiliado;
    }

    public void setLazyAfiliado(LazyDataModel<AsegAfiliado> lazyAfiliado) {
        this.lazyAfiliado = lazyAfiliado;
    }

    public AfiliadosServicioIface getVendedorServicio() {
        return afiliadoServicio;
    }

    public void setVendedorServicio(AfiliadosServicioIface afiliadoServicio) {
        this.afiliadoServicio = afiliadoServicio;
    }

    public AfiliadosServicioIface getAfiliadosServicio() {
        if (afiliadoServicio == null) {
            afiliadoServicio = new AfiliadosServicioImpl();
        }
        return afiliadoServicio;
    }

    public void setAfiliadoServicio(AfiliadosServicioIface afiliadoServicio) {
        this.afiliadoServicio = afiliadoServicio;
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

    public List<Maestro> getListaTiposGenero() {
        return listaTiposGenero;
    }

    public void setListaTiposGenero(List<Maestro> listaTiposGenero) {
        this.listaTiposGenero = listaTiposGenero;
    }

    public HashMap<Integer, Maestro> getHashTiposGenero() {
        return hashTiposGenero;
    }

    public void setHashTiposGenero(HashMap<Integer, Maestro> hashTiposGenero) {
        this.hashTiposGenero = hashTiposGenero;
    }

    public String getTipoGenero(Integer id) {
        try {
            return hashTiposGenero.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public String getTipoDocumento(Integer id) {
        try {
            return hashTiposDocumento.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    public void refrescar() {
        super.setAccion(Url.ACCION_LISTAR);
        getAfiliadosServicio().Accion(this);
    }

    public void refrescarHistorial() {
        super.setAccion(Url.ACCION_ADICIONAL_1);
        getAfiliadosServicio().Accion(this);
    }

    public void ver(int _id) {
        objeto = new AsegAfiliado(_id);
        //getObjeto().setId(_id);
        super.setAccion(ACCION_VER);
        getAfiliadosServicio().Accion(this);
        validarContribucionSolidaria();
        //2022-09-15 jyperez llamamos a la función que evalua si se encuentra sin encuesta Sisben 4
        validarAfiliadoSinEncuestaSisbenVersion4();
        //2025-08-04 jyperez validamos campos de georreferenciación
        llenarRegistrosMapa(objeto);
        PrimeFaces.current().ajax().update("frmVer");
        PrimeFaces.current().executeScript("PF('dialogoVer').show()");
        procesoFinal();
    }

    public void verHistorialNovedades(int _id) {
        //getObjeto().setId(_id);
        super.setAccion(ACCION_ADICIONAL_1);
        inicializarTablaHistorialAfiliados(_id);
        getAfiliadosServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().ajax().update("frmHistorial");
            PrimeFaces.current().executeScript("PF('dialogoHistorial').show()");
        }
        procesoFinal();
    }

    public void inicializarTablaHistorialAfiliados(int id) {
        this.setParamConsultaHistorial(new ParamConsulta());
        this.getParamConsultaHistorial().setEmpresaId(id);// este valor será nuestro diferenciador a nivel de afiliado
        lazyHistorial = new LazyDataModel<AsegRegistroNovedad>() {

            private List<AsegRegistroNovedad> novedades;
            
            @Override
            public int count(Map<String, FilterMeta> filtros) {
                return getParamConsulta().getCantidadRegistros();
            }

            @Override
            public List<AsegRegistroNovedad> load(int primerRegistro, int registrosPagina, Map<String, SortMeta> listaOrdenes, Map<String, FilterMeta> filtros) {
                //usamos el mismo getParamConsulta pero validamos si debemos cambiarlo
                getParamConsultaHistorial().setPrimerRegistro(primerRegistro);
                getParamConsultaHistorial().setRegistrosPagina(registrosPagina);
                getParamConsultaHistorial().setListaOrden(CompatibilidadPF.convertirFiltrosOrdenToHash(listaOrdenes));
                getParamConsultaHistorial().setFiltros(CompatibilidadPF.ConvertirFiltroMetaToHash(filtros));
                refrescarHistorial();
                novedades = getListaHistorialNovedad();
                setRowCount(getParamConsultaHistorial().getCantidadRegistros());
                return novedades;
            }

            @Override
            public String getRowKey(AsegRegistroNovedad novedades) {
                return novedades.getId().toString();
            }

            @Override
            public AsegRegistroNovedad getRowData(String novedadesId) {
                Integer id = Integer.valueOf(novedadesId);
                for (AsegRegistroNovedad novedad : novedades) {
                    if (id.equals(novedad.getId())) {
                        return novedad;
                    }
                }
                return null;
            }

        };

    }

    public void crear() {
        //2021-05-27 jyperez se inicializa la variable para los contactos
        contadorIdContacto = -1;
        //2020-10-29 jyperez se inicializa variable para manejar ventana de homonimos
        accionCreacion = true;
        //2021-07-08 jyperez INC 857 se actualiza la variable a false
        requeridoSubGrupoSisben = false;
        super.setAccion(ACCION_CREAR);
        getAfiliadosServicio().Accion(this);
        marcarBeneficiario();
        cargarPreguntasEncuesta();
        this.setAfiliadoExistente(false);
        this.setAfiliadoCFExistente(false);
        this.setConsultarResidencia(false);
        //2020-08-20 jyperez Control de cambios población 31
        //2021-12-20 jyperez el llamado a esta función no carga las listas de Nivel Sisben, Etnia y Grupo Sisben
        cargarListaNivelSisbenInterna();
        //2020-10-29 jyperez debemos cargar el régimen
        this.objeto.setRegimen(AfiliadoParametro.REGIMEN_SUBSIDIADO_ANTIGUO);
        this.objeto.setMaeRegimenCodigo(AfiliadoParametro.REGIMEN_SUBSIDIADO);
        //usamos la lista para obtener el valor
        for (Maestro reg: listaRegimen) {
            if (reg.getValor().equals(AfiliadoParametro.REGIMEN_SUBSIDIADO)) {
                this.objeto.setMaeRegimenId(reg.getId());
                this.objeto.setMaeRegimenCodigo(reg.getValor());
                this.objeto.setMaeRegimenValor(reg.getNombre());
            }
        }
        //2021-11-11 jyperez cargamos el modelo de liquidación por defecto
        this.objeto.setModeloLiquidacion(AfiliadoParametro.MODELO_LIQUIDACION_EVENTO);
        Maestro mod = hashValorModeloLiquidacion.get(AfiliadoParametro.MODELO_LIQUIDACION_EVENTO);
        if (mod != null) {
            this.objeto.setMaeModeloLiquidacionId(mod.getId());
            this.objeto.setMaeModeloLiquidacionCodigo(mod.getValor());
            this.objeto.setMaeModeloLiquidacionValor(mod.getNombre());
        }
        //2021-11-11 jyperez cargamos el estado
        Maestro estado = hashValorEstadosAfiliacion.get(AfiliadoParametro.ESTADO_AFILIACION_PENDIENTE_SOPORTE);
        if (estado != null) {
            this.objeto.setMaeEstadoAfiliacion(estado.getId());
            this.objeto.setMaeEstadoAfiliacionCodigo(estado.getValor());
            this.objeto.setMaeEstadoAfiliacionValor(estado.getNombre());
        }
        //2020-10-29 jyperez adicionamos la carga de la lista de tipoAfiliado
        //2021-12-20 jyperez acá se carga la lista Tipo Afiliado, teniendo en cuenta los cambios realizados
        cargarListaTipoAfiliado();
        //2021-12-28 jyperez se incluye el llamado a la lista de Parentesco
        getAfiliadosServicio().consultarMaestroParentescoPorRegimen(this);
        //2021-12-28 jyperez cargamos la causa novedad.
        getAfiliadosServicio().consultarMaestroCausaNovedadPorEstado(this);
        //PrimeFaces.current().resetInputs("frmCrear:panelCrear");
        //resetearFormularioCrear();
        //PrimeFaces.current().ajax().update("frmCrear:panelCrear");
        actualizarFormularioCrear();
        PrimeFaces.current().executeScript("PF('dialogoCrear').show()");
        procesoFinal();
    }

    public void guardar() {
        PrimeFaces.current().executeScript("PF('dialogoHomonimo').hide();");
        super.setAccion(ACCION_GUARDAR);
        // valores establecidos por Defecto
        this.objeto.setIdAfiliado("0");
        //this.objeto.setResidenciaUbicacion(this.objeto.getAfiliacionUbicacion());
        // 2020-07-21 jyperez valor sincronizado inicializado en cero mantis 26X
        this.objeto.setSincronizado(0);
        //2020-08-19 jyperez se obtiene el registro de EPS si fué seleccionado y se almacena el id en el objeto traslado
        if (this.objetoTraslado != null) {
            if (this.objetoTraslado.getMaestroEps() != null) {
                this.objetoTraslado.setMaeEpsOrigenId(this.objetoTraslado.getMaestroEps().getId());
            }
        }
        getAfiliadosServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoCrear').hide();");
        }
        procesoFinal();
    }

    public void editar(int _id) {
        //2021-05-27 jyperez se inicializa la variable para los contactos
        contadorIdContacto = -1;
        //2021-02-26 jyperez inicializamos los objetos a null, teniendo en cuenta que los vamos a actualizar posteriormente
        objetoAnterior = null;
        objeto = new AsegAfiliado(_id);
        //2020-10-29 jyperez se inicializa variable para manejar ventana de homonimos
        accionCreacion = false;
        //2021-07-08 jyperez INC 857 se actualiza la variable a false
        requeridoSubGrupoSisben = false;
        //2021-12-17 jyperez actualizamos el valor
        afiliadoActivoContributivo = false;
        getObjeto().setId(_id);
        super.setAccion(ACCION_EDITAR);
        // adicionamos acá la condición de que consulte por residencia
        this.setConsultarResidencia(true);
        getAfiliadosServicio().Accion(this);
        marcarBeneficiario();
        //2024-01-10 jyperez si el afiliado ya se encuentra duplicado, no lo puede dejar editar a menos que tenga un permiso especial
        if (this.objeto.getDuplicado() != null && this.objeto.getDuplicado()) {
            bloqueoDuplicado = true;
        } else {
            bloqueoDuplicado = false;
        }
        try {
            //20202-06-04 copiamos el objeto de edición en un objeto anterior
            //this.objetoAnterior = this.objeto;
            objetoAnterior = (AsegAfiliado) objeto.clone();
        } catch (CloneNotSupportedException ex) {
            this.objetoAnterior = this.objeto;
        }
        try{
            //2021-12-17 jyperez validamos si el afiliado es Activo y Contributivo, con permiso adicional 5. Si es así se marca el usuario para
            // notificarle al final que está modificando este tipo de afiliado.
            if (hashRegimen.get(this.objeto.getMaeRegimenId()).getValor().equals(AfiliadoParametro.REGIMEN_CONTRIBUTIVO) &&
                        hashEstadosAfiliacion.get(this.objeto.getMaeEstadoAfiliacion()).contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO)) {
                afiliadoActivoContributivo = this.isAccionAdicional5();
            }
        } catch (Exception e) {
            afiliadoActivoContributivo = false;
        }
        
        //2020-11-24 jyperez guardamos el prestador anterior para luego poderlo almacenar en el histórico.
        this.setPrestadorAnterior(objeto.getPrimariaPrestadorSede());
        //this.prestadorSedeConsulta = objeto.getPrimariaPrestadorSede(); OK
        this.setAfiliadoExistente(false);
        this.setAfiliadoCFExistente(false);
        //this.setHabilitarCategoriaIBC(false);
        activarCategoriaIBC();
        //this.setEstadoAfiliado(objeto.getMaeEstadoAfiliacion()); OK
        //this.setRegimenAfiliado(objeto.getRegimen()); OK
        //2020-05-20 se cambia al municipio de residencia
        /*if (objeto.getResidenciaUbicacion() != null) {
            this.setMunicipioAfiliado(objeto.getResidenciaUbicacion().getId());
        } else {
            this.setMunicipioAfiliado(0);
        } OK */
        //this.setPrimerNombre(objeto.getPrimerNombre()); OK
        //this.setPrimerApellido(objeto.getPrimerApellido()); Ok
        //this.setFechaNacimiento(objeto.getFechaNacimiento()); OK
        //this.setMaeTipoDocumento(objeto.getMaeTipoDocumento()); OK
        //this.setNumeroDocumento(objeto.getNumeroDocumento()); Ok
        actualizarSubgrupoSisben();
        //cargarListaTipoAfiliado();
        //2020-08-20 jyperez Control de cambios población 31
        cargarListaNivelSisbenInterna();
        actualizarListaCausaNovedad();
        //2022-01-21 jyperez borramos el valor del campo observacion segun solicitud INC 1138
        this.objeto.setObservacionNovedad("");
        //2022-06-08 jyperez llamamos a la función que evalua si el afiliado es de contribución solidaria
        validarContribucionSolidaria();
        //2022-09-15 jyperez llamamos a la función que evalua si se encuentra sin encuesta Sisben 4
        validarAfiliadoSinEncuestaSisbenVersion4();
        //PrimeFaces.current().resetInputs("frmEditar:panelEditar");
        resetearFormularioEditar();
        //PrimeFaces.current().ajax().update("frmEditar:panelEditar");
        actualizarFormularioEditar();
        PrimeFaces.current().executeScript("PF('dialogoEditar').show()");

        procesoFinal();
    }

    public void modificar() {
        PrimeFaces.current().executeScript("PF('dialogoHomonimo').hide();");
        super.setAccion(ACCION_MODIFICAR);
        this.objeto.setFechaCambioEstadoEps(this.objeto.getFechaNovedad());
        //si el cambio es una reactivacion ( osea a estado Activo)
        if (this.objetoAnterior.getMaeEstadoAfiliacion() != this.objeto.getMaeEstadoAfiliacion()
                && this.hashEstadosAfiliacion.get(this.objeto.getMaeEstadoAfiliacion()).contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO)) {
            this.objeto.setFechaReactivacion(this.objeto.getFechaNovedad());
            //2022-03-02 jyperez borramos fecha de Egreso Eps debido a que es una reactivación.
            this.objeto.setFechaEgresoEps(null);
            //2022-11-09 jyperez borramos fecha de suspensión al reactivar al afiliado
            this.objeto.setFechaSuspension(null);

        } else if (this.objetoAnterior.getMaeEstadoAfiliacion() != this.objeto.getMaeEstadoAfiliacion()
                && this.hashEstadosAfiliacion.get(this.objeto.getMaeEstadoAfiliacion()).getValor().equals(AfiliadoParametro.ESTADO_AFILIACION_RETIRADO)) {
            this.objeto.setFechaEgresoEps(this.objeto.getFechaNovedad());
        } else if (this.objetoAnterior.getMaeEstadoAfiliacion() != this.objeto.getMaeEstadoAfiliacion()
                && this.hashEstadosAfiliacion.get(this.objeto.getMaeEstadoAfiliacion()).getValor().equals(AfiliadoParametro.ESTADO_AFILIACION_FALLECIDO)) {
            this.objeto.setFechaEgresoEps(this.objeto.getFechaNovedad());
        //2022-03-02 jyperez evaluamos el cambio a pendiente de Soporte para borrarla fecha de Egreso Eps
        } else if (this.objetoAnterior.getMaeEstadoAfiliacion() != this.objeto.getMaeEstadoAfiliacion()
                && this.hashEstadosAfiliacion.get(this.objeto.getMaeEstadoAfiliacion()).getValor().equals(AfiliadoParametro.ESTADO_AFILIACION_PENDIENTE_SOPORTE)) {
            this.objeto.setFechaEgresoEps(null);
        //2022-11-09 jyperez evaluamos el cambio a suspendido para actualizar la fecha
        } else if (this.objetoAnterior.getMaeEstadoAfiliacion() != this.objeto.getMaeEstadoAfiliacion()
                && this.hashEstadosAfiliacion.get(this.objeto.getMaeEstadoAfiliacion()).getValor().equals(AfiliadoParametro.ESTADO_AFILIACION_SUSPENDIDO)) {
            this.objeto.setFechaSuspension(this.objeto.getFechaNovedad());
        } else if (this.objetoAnterior.getMaeEstadoAfiliacion() != this.objeto.getMaeEstadoAfiliacion()
                && this.hashEstadosAfiliacion.get(this.objeto.getMaeEstadoAfiliacion()).getValor().equals(AfiliadoParametro.ESTADO_AFILIACION_DUPLICADO)) {
            //2024-01-17 jyperez se marcan como fecha Egreso o como cambio de estado
            this.objeto.setFechaEgresoEps(this.objeto.getFechaNovedad());
            this.objeto.setFechaCambioEstadoEps(this.objeto.getFechaNovedad());
        }
        // 2020-05-27 jyperez ajuste 211: actualizar valor sincronizado a 0
        this.objeto.setSincronizado(0);
        //2020-08-19 jyperez se obtiene el registro de EPS si fué seleccionado y se almacena el id en el objeto traslado
        if (this.objetoTraslado != null) {
            if (this.objetoTraslado.getMaestroEps() != null) {
                this.objetoTraslado.setMaeEpsOrigenId(this.objetoTraslado.getMaestroEps().getId());
            }
        }
        getAfiliadosServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().executeScript("PF('dialogoEditar').hide();");
        }
        procesoFinal();
    }

    public void borrar(int _id) {
        objeto = new AsegAfiliado(_id);
        getObjeto().setId(_id);
        super.setAccion(ACCION_BORRAR);
        getAfiliadosServicio().Accion(this);
        procesoFinal();
    }
    
    /**
     * Función para Ver información adicional del contacto
     * @param contacto 
     */
    public void verContacto(AsegAfiliadoContacto contacto) {
        this.contacto = contacto;
        PrimeFaces.current().resetInputs("frmVerContacto");
        PrimeFaces.current().ajax().update("frmVerContacto");
        PrimeFaces.current().executeScript("PF('dialogoVerContacto').show()");
    }

    /**
     * Función llamada desde Crear Afiliado y desde Editar Afiliado, para adicionar un nuevo contacto
     */
    public void adicionarContacto() {
        contacto = new AsegAfiliadoContacto();
        // el contacto siempre se creará activo
        contacto.setActivo(true);
        PrimeFaces.current().resetInputs("frmCrearContacto");
        PrimeFaces.current().ajax().update("frmCrearContacto");
        PrimeFaces.current().executeScript("PF('dialogoCrearContacto').show()");
    }

    /**
     * Función llamada desde Crear/Editar Afiliado, para guardar un contacto
     * -- Esto todavía no se refleja en la BD debido a que debe hacerse el proceso de creación de Afiliado
     */
    public void guardarContacto() {
        boolean guardar = true;
        String mensaje = "";
        //obtenemos los valores del maestro
        //validamos si el contacto ya se encuentra registrado
        for (AsegAfiliadoContacto cont: this.listaContactos) {
            if (cont.getNumeroContacto().equals(contacto.getNumeroContacto())) {
                addError("El contacto ya se encuentra registrado.");
                guardar = false;
            }
        }
        if (guardar) {
            try{
                Maestro tipo = hashTiposContacto.get(contacto.getMaeTipoContactoId());
                if (tipo != null) {
                    switch(tipo.getValor()) {
                        case AfiliadoParametro.TIPO_CONTACTO_CELULAR:
                            if(contacto.getNumeroContacto().length() != 10) {
                                addError("Si el Tipo seleccionado es " + tipo.getNombre() + " el Número de Contacto debe ser de 10 dígitos.");
                                guardar = false;
                            }
                            //2022-06-03 jyperez adicionamos validación
                            mensaje = validarRangoTelefonoMovil(contacto.getNumeroContacto());
                            if (!mensaje.isEmpty()) {
                                addError(mensaje);
                                guardar = false;
                            }
                        break;
                        case AfiliadoParametro.TIPO_CONTACTO_TELEFONO: 
                            if (contacto.getNumeroContacto().length() != 10) {
                                addError("Si el Tipo seleccionado es " + tipo.getNombre() + " el Número de Contacto debe ser de 10 dígitos.");
                                guardar = false;
                            }
                            //2022-06-03 jyperez adicionamos validación
                            mensaje = validarTelefonosFijosNoPermitidos(contacto.getNumeroContacto());
                            if (!mensaje.isEmpty()) {
                                addError(mensaje);
                                guardar = false;
                            }
                        break;
                    }
                    contacto.setMaeTipoContactoCodigo(tipo.getValor());
                    contacto.setMaeTipoContactoValor(tipo.getNombre());
                } else {
                    guardar = false;
                    addError("No se encontró el valor maestro del Tipo seleccionado.");
                }
            }catch (Exception e) {
                addError("No se encontró el valor maestro del Tipo seleccionado.");
                contacto.setMaeTipoContactoCodigo("");
                contacto.setMaeTipoContactoValor("");
                guardar = false;
            }
        }
        if (guardar) {
            contacto.setId(contadorIdContacto);
            contacto.setNuevoRegistro(true);
            //recordar que el contador va a la inversa con valores negativos
            contadorIdContacto--;
            listaContactos.add(contacto);
            PrimeFaces.current().ajax().update("frmCrear:tablaContactos");
            PrimeFaces.current().ajax().update("frmEditar:tablaContactos");
            PrimeFaces.current().executeScript("PF('dialogoCrearContacto').hide()");
        } else {
            generarMensajes();
        }
    }
    
    /**
     * Se valida que no se ingresen los valores no permitidos ( numeros repetitivos) para el campo
     * teléfono fijo.
     * @param objeto
     * @return 
     */
    private String validarTelefonosFijosNoPermitidos(String fijo) {
        String mensaje = "";
        if (fijo != null && !fijo.equals("") && !fijo.isBlank()) {
            if (valoresNoPermitidosTelefonoFijo.contains(fijo)) {
                mensaje = "el telefono fijo no puede contener una secuencia de números repetitivos, lo cual lo hace inválido.";
            }
        }
        return mensaje;
    }
    
    /**
     * Función para validar que el numero del telefono movil se encuentre entre un rango de 300 y 350 inicialmente.
     * @param objeto
     * @return 
     */
    private String validarRangoTelefonoMovil(String movil) {
        String mensaje = "";
        int prefijo = 0;
        //2020-07-27 jyperez se controla excepción debido a que hay datos erróneos ingresados por carga masiva - INC 261
        try{
            if(movil != null && !movil.equals("") && !movil.isBlank()) {
                if (movil.length() >= 3) {
                    prefijo = Integer.valueOf(movil.substring(0, 3));
                    if (prefijo >= 300 && prefijo <= 350) {
                        mensaje = "";
                    } else {
                        mensaje = "El teléfono móvil no inicia entre el rango permitido de valores (300 - 350) ";
                    }

                } else {
                    mensaje = "El teléfono móvil no inicia entre el rango permitido de valores (300 - 350) ";
                }

            }
        } catch (Exception e) {
            mensaje = "El teléfono móvil no contiene un valor numérico.";
        }
        return mensaje;
    }

    /**
     * Función usada desde Crear Afiliados con el objetivo de eliminar un contacto de la lista
     * @param id 
     */
    public void retirarContacto(int id) {
        for (int i = 0; i < listaContactos.size(); i++) {
            AsegAfiliadoContacto agrup = listaContactos.get(i);
            if (agrup.getId() != null && agrup.getId() == id) {
                //if (agrup.getIdInsertar() != null && agrup.getIdInsertar() == id) {
                listaContactos.remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmCrear:tablaContactos");
        PrimeFaces.current().ajax().update("frmEditar:tablaContactos");
    }

    public void editarContacto(int id) {
        contacto = new AsegAfiliadoContacto();
        for (AsegAfiliadoContacto cont : listaContactos) {
            if (cont.getId() != null && cont.getId() == id) {
                contacto = cont;
                break;
            }
        }
        PrimeFaces.current().resetInputs("frmEditarContacto");
        PrimeFaces.current().ajax().update("frmEditarContacto");
        PrimeFaces.current().executeScript("PF('dialogoEditarContacto').show()");
    }

    public void modificarContacto() {
        for (int i = 0; i < listaContactos.size(); i++) {
            AsegAfiliadoContacto agrup = listaContactos.get(i);
            if (agrup.getId() != null && contacto.getId().equals(agrup.getId())) {
                //obtenemos los valores del maestro
                try{
                    Maestro tipo = hashTiposContacto.get(contacto.getMaeTipoContactoId());
                    if (tipo != null) {
                        contacto.setMaeTipoContactoCodigo(tipo.getValor());
                        contacto.setMaeTipoContactoValor(tipo.getNombre());
                    }
                }catch (Exception e) {
                    contacto.setMaeTipoContactoCodigo("");
                    contacto.setMaeTipoContactoValor("");
                }
                // validamos que sede no sea un registro nuevo
                if (!contacto.isNuevoRegistro()) {
                    contacto.setEditado(true);
                }else {
                    contacto.setEditado(false);
                }
                listaContactos.set(i, contacto);
                break;
           }
        }
        PrimeFaces.current().ajax().update("frmEditar:tablaContactos");
        PrimeFaces.current().executeScript("PF('dialogoEditarContacto').hide()");
    }

    /**
     * Función que se llama desde Editar Afiliado - para borrar un contacto Existente
     * @param id 
     */
    public void borrarContacto(int id) {
        for (int i = 0; i < listaContactos.size(); i++) {
            AsegAfiliadoContacto agrup = listaContactos.get(i);
            if (agrup.getId() == id) {
                listaContactosBorrar.add(agrup);
                listaContactos.remove(i);
                break;
            }
        }
        PrimeFaces.current().ajax().update("frmEditar:tablaContactos");
    }

    private void procesoFinal() {
        switch (getAccion()) {
            case Url.ACCION_VER:
            case Url.ACCION_CREAR:
            case Url.ACCION_EDITAR:
                crearLog(getObjeto().toStringAlterno());
                break;
            case Url.ACCION_GUARDAR:
            case Url.ACCION_MODIFICAR:
            case Url.ACCION_BORRAR:
                crearLog(getObjeto().toStringAlterno());
                PrimeFaces.current().ajax().update("frmAfiliados");
                break;
            case Url.ACCION_LISTAR:
                crearLog(getObjeto().toStringAlterno());
                break;
            case Url.ACCION_ADICIONAL_1:
                if (getObjeto() != null) {
                    crearLog(getObjeto().toStringAlterno());
                }
                break;
            case Url.ACCION_ADICIONAL_2:
                switch (getDoAccion()) {
                    case Url.ACCION_LISTAR:
                        crearLog(getObjeto().toStringAlterno());
                        break;
                    case Url.ACCION_VER:
                        crearLog(getAdjunto().toString());
                        break;
                    case Url.ACCION_CREAR:
                        crearLog(getAdjunto().toString());
                        break;
                    case Url.ACCION_GUARDAR:
                        crearLog(getAdjunto().toString());
                        break;
                    case Url.ACCION_BORRAR:
                        break;
                }
                break;
            case Url.ACCION_ADICIONAL_3:
                crearLog("Entrega de Carta de deberes y derechos",getObjeto().toStringAlterno());
                break;
            case Url.ACCION_ADICIONAL_4:
                switch (getDoAccion()) {
                    case Url.ACCION_ADICIONAL_1:
                        crearLog("Formulario Único de Afiliación",getObjeto().toStringAlterno());
                    break;
                    case Url.ACCION_ADICIONAL_2:
                        crearLog("Formulario Único de AfiliaciónV2",getObjeto().toStringAlterno());
                    break;
                }
                break;
            case Url.ACCION_ADICIONAL_6:
                crearLog("Certificado de Afiliación",getObjeto().toStringAlterno());
                break;
            case Url.ACCION_ADICIONAL_7:
                crearLog("Desmarcar Duplicado",getObjeto().toStringAlterno());
                PrimeFaces.current().ajax().update("frmAfiliados");
                break;
            case Url.ACCION_ADICIONAL_8:
                crearLog("Generar Certificado de Contactos",getObjeto().toStringAlterno());
                PrimeFaces.current().ajax().update("frmAfiliados");
                //PrimeFaces.current().executeScript("PF('dialogoCrearCertificadoContacto').hide()");
                break;
        }
        generarMensajes();
    }

    /**
     * @return the listaEstadosAfiliacion
     */
    public List<Maestro> getListaEstadosAfiliacion() {
        return listaEstadosAfiliacion;
    }

    /**
     * @param listaEstadosAfiliacion the listaEstadosAfiliacion to set
     */
    public void setListaEstadosAfiliacion(List<Maestro> listaEstadosAfiliacion) {
        this.listaEstadosAfiliacion = listaEstadosAfiliacion;
    }

    /**
     * @return the hashEstadosAfiliacion
     */
    public HashMap<Integer, Maestro> getHashEstadosAfiliacion() {
        return hashEstadosAfiliacion;
    }

    /**
     * @param hashEstadosAfiliacion the hashEstadosAfiliacion to set
     */
    public void setHashEstadosAfiliacion(HashMap<Integer, Maestro> hashEstadosAfiliacion) {
        this.hashEstadosAfiliacion = hashEstadosAfiliacion;
    }

    public String getRegimen(int id) {
        String resultado = "";
        try{
            resultado = hashRegimen.get(id).getNombre();        
        }catch (Exception e) {
            
        }
        return resultado;
    }
    
    public String getActividadEconomica(int id) {
        String resultado = "";
        try{
            resultado = hashActividadEconomica.get(id).getNombre();        
        }catch (Exception e) {
            
        }
        return resultado;
    }
    public String getArl(int id) {
        String resultado = "";
        try{
            resultado = hashARL.get(id).getNombre();        
        }catch (Exception e) {
            
        }
        return resultado;
    }
    public String getAfp(int id) {
        String resultado = "";
        try{
            resultado = hashAFP.get(id).getNombre();        
        }catch (Exception e) {
            
        }
        return resultado;
    }
    public String getCcf(int id) {
        String resultado = "";
        try{
            resultado = hashCajaCompensacion.get(id).getNombre();        
        }catch (Exception e) {
            
        }
        return resultado;
    }

    /**
     * @return the listaOrigenAfiliado
     */
    public List<Maestro> getListaOrigenAfiliado() {
        return listaOrigenAfiliado;
    }

    /**
     * @param listaOrigenAfiliado the listaOrigenAfiliado to set
     */
    public void setListaOrigenAfiliado(List<Maestro> listaOrigenAfiliado) {
        this.listaOrigenAfiliado = listaOrigenAfiliado;
    }

    /**
     * @return the hashOrigenAfiliado
     */
    public HashMap<Integer, Maestro> getHashOrigenAfiliado() {
        return hashOrigenAfiliado;
    }

    /**
     * @param hashOrigenAfiliado the hashOrigenAfiliado to set
     */
    public void setHashOrigenAfiliado(HashMap<Integer, Maestro> hashOrigenAfiliado) {
        this.hashOrigenAfiliado = hashOrigenAfiliado;
    }

    /**
     * @return the objetoTraslado
     */
    public AsegTraslado getObjetoTraslado() {
        return objetoTraslado;
    }

    /**
     * @param objetoTraslado the objetoTraslado to set
     */
    public void setObjetoTraslado(AsegTraslado objetoTraslado) {
        this.objetoTraslado = objetoTraslado;
    }

    public void cambiarExigenciaDatosSegunVia() {
        String tipoVia = this.getObjeto().getDireccionVia();
        if (tipoVia != null && !"".equals(tipoVia)) {
            if (tipoVia.equalsIgnoreCase("SD")) {
                cambiarEstadoCamposSinDireccion(true);
            } else {
                cambiarEstadoCamposSinDireccion(false);
            }

        } else {
            cambiarEstadoCamposSinDireccion(true);
        }
        //2025-08-04 jyperez actualización campo oculto dirección
        actualizarUbicacion();
        PrimeFaces.current().ajax().update("frmDireccion");// validar
        PrimeFaces.current().resetInputs("frmDireccion");// validar
    }

    private void cambiarEstadoCamposSinDireccion(boolean tipoHabilitacion) {

        setEstaHabilitadoCampo(tipoHabilitacion);
    }

    public void verDireccion() {
        //2024-03-27 jyperez actualizamos variable para diferenciar tipo de dirección a actualizar
        this.setTipoDireccion(AfiliadoParametro.TIPO_DIRECCION_PRINCIPAL);
        if (objeto.getDireccionVia() == null || this.objeto.getDireccionVia().equals("SD")) {
            this.estaHabilitadoCampo = true;
        } else {
            this.estaHabilitadoCampo = false;
        }
        //2025-08-05 jyperez blankeamos los campos de georreferenciación
        if (this.objeto.getDireccionGeorefLatitud() != null && this.objeto.getDireccionGeorefLongitud() != null) {
            campoLatitud = this.objeto.getDireccionGeorefLatitud().doubleValue();
            campoLongitud = this.objeto.getDireccionGeorefLongitud().doubleValue();
        } else {
            campoLatitud = AfiliadoParametro.CENTRO_LATITUD_INICIAL;
            campoLongitud = AfiliadoParametro.CENTRO_LONGITUD_INICIAL;
        }
        PrimeFaces.current().ajax().update("frmDireccion");
        PrimeFaces.current().executeScript("PF('dialogoDireccion').show()");
    }
    
    public void verDireccionAlternaContacto() {
        //2024-03-27 jyperez actualizamos variable para diferenciar tipo de dirección a actualizar
        this.setTipoDireccion(AfiliadoParametro.TIPO_DIRECCION_ALTERNA_CONTACTO);
        if (objeto.getDireccionVia() == null || this.objeto.getDireccionVia().equals("SD")) {
            this.estaHabilitadoCampo = true;
        } else {
            this.estaHabilitadoCampo = false;
        }
        PrimeFaces.current().ajax().update("frmDireccion");
        PrimeFaces.current().executeScript("PF('dialogoDireccion').show()");
    }
    
    public boolean validarDireccionPrincipal(){
        return this.getTipoDireccion() == AfiliadoParametro.TIPO_DIRECCION_PRINCIPAL ? true: false;
    }

    public void actualizarDireccion() {
        // concatenamos los campos en la dirección del objeto afiliado
        String direccionCompleta;
        if (this.objeto.getDireccionVia() != null
                && !this.objeto.getDireccionVia().equals("")
                && !this.objeto.getDireccionVia().equals("SD")) {
            direccionCompleta = retornarCadena(this.objeto.getDireccionVia())
                    + retornarCadena(this.objeto.getDireccionNro())
                    + retornarCadena(this.objeto.getDireccionOrd1())
                    + retornarCadena(this.objeto.getDireccionOrientacion())
                    + "# "
                    + retornarCadena(this.objeto.getDireccionPlaca1())
                    + retornarCadena(this.objeto.getDireccionOrd2())
                    + retornarCadena(this.objeto.getDireccionOrientacion2())
                    + "- "
                    + retornarCadena(this.objeto.getDireccionPlaca2())
                    + retornarCadena(this.objeto.getDireccionDescripcion());
        } else {
            direccionCompleta = retornarCadena(this.objeto.getDireccionVia())
                    + retornarCadena(this.objeto.getDireccionDescripcion());
        }
        //2024-03-27 jyperez validamos variable tipoDireccion para diferenciar tipo de dirección a actualizar
       switch (this.getTipoDireccion()) {
           case AfiliadoParametro.TIPO_DIRECCION_PRINCIPAL:
                this.objeto.setDireccionResidencia(direccionCompleta);
                //2025-08-05 jyperez actualizamos los campos de georreferenciación
                this.objeto.setDireccionGeorefLatitud(BigDecimal.valueOf(campoLatitud));
                this.objeto.setDireccionGeorefLongitud(BigDecimal.valueOf(campoLongitud));
                // Se refresca unicamente el panel donde se encuentra el componente de la dirección
                PrimeFaces.current().ajax().update("frmCrear:panelCrear10");
                PrimeFaces.current().ajax().update("frmEditar:panelEditar9");
                PrimeFaces.current().executeScript("PF('dialogoDireccion').hide()");
            break;
           case AfiliadoParametro.TIPO_DIRECCION_ALTERNA_CONTACTO:
                this.objeto.setDireccionAlternaContacto(direccionCompleta);
                // Se refresca unicamente el panel donde se encuentra el componente de la dirección
                PrimeFaces.current().ajax().update("frmCrear:panelCrear19");
                PrimeFaces.current().ajax().update("frmEditar:panelEditar18");
                PrimeFaces.current().executeScript("PF('dialogoDireccion').hide()");
            break;
            default:
                PrimeFaces.current().executeScript("PF('dialogoDireccion').hide()");
            break;
        }
    }
    
    public void verBarrio() {
        //cargamos los barrios dependiendo del municipio de residencia cargado en el afiliado
        if (getObjeto().getResidenciaUbicacion() != null && getObjeto().getResidenciaUbicacion().getId() == getCiudadMedellin().getId()) {
            getAfiliadosServicio().consultarListaBarriosMedellin(this);
            setHabilitaListaBarrio(true);
        } else {
            setHabilitaListaBarrio(false);
        }
        //validamos si el objeto de barrio existe en el objeto seleccionado, sino agregamos uno vacio
        if (getObjeto().getUbicacionBarrio() == null) {
            getObjeto().setUbicacionBarrio(new GnUbicacionBarrio());
        }
        PrimeFaces.current().ajax().update("frmBarrio");
        PrimeFaces.current().executeScript("PF('dialogoBarrio').show()");
    }
    
    public void actualizarBarrio() {
        if (getObjeto().getResidenciaUbicacion() != null && getObjeto().getResidenciaUbicacion().getId() == getCiudadMedellin().getId()) {
            GnUbicacionBarrio barrio = getHashUbicacionBarrios().get(getObjeto().getUbicacionBarrio().getId());
            if (barrio != null ) {
                getObjeto().setUbicacionBarrio(barrio);
                getObjeto().setBarrio(barrio.getNombre());
            }
        }
        // Se refresca unicamente el panel donde se encuentra el componente del barrio
                PrimeFaces.current().ajax().update("frmCrear:panelCrear13");
                PrimeFaces.current().ajax().update("frmCrear:panelCrear4");
                PrimeFaces.current().ajax().update("frmEditar:panelEditar19");
                PrimeFaces.current().ajax().update("frmEditar:panelEditar6");
        PrimeFaces.current().executeScript("PF('dialogoBarrio').hide()");
    }

    /**
     * Utilidad para validar que un campo en nulo entregue una cadena vacia.
     *
     * @param campo
     * @return cadena vacia si el campo es nulo, de lo contrario el valor del
     * campo
     */
    public String retornarCadena(String campo) {
        if (campo == null || campo.trim().equals("")) {
            return "";
        } else {
            campo = campo + " ";
            return campo;
        }
    }

    public boolean isEstaHabilitadoCampo() {
        return estaHabilitadoCampo;
    }

    public void setEstaHabilitadoCampo(boolean estaHabilitadoCampo) {
        this.estaHabilitadoCampo = estaHabilitadoCampo;
    }

    public String getMaestroEpsRecursiva(int id) {
        String ubicacionStr = "";
        Maestro eps = null;
        if (getListadoEpsRecursiva() != null) {
            eps = getListadoEpsRecursiva().get(id);
        }
        if (eps != null) {
            ubicacionStr = eps.getNombre();
        }
        return ubicacionStr;
    }

    public String getUbicacionRecursiva(int id) {
        String ubicacionStr = "";
        Ubicacion _municipio = null;
        if (getUbicacionesRecursiva() != null) {
            _municipio = getUbicacionesRecursiva().get(id);
        }
        if (_municipio != null && _municipio.getUbicacionPadre() != null) {
            Ubicacion _departamento = _municipio.getUbicacionPadre();
            if (_departamento.getUbicacionPadre() != null) {
                Ubicacion _pais = _departamento.getUbicacionPadre();
                // ubicacionStr = _pais.getNombre();
            }
            ubicacionStr = _departamento.getNombre();
            ubicacionStr = _municipio.getNombre() + " - " + ubicacionStr;
        }
        return ubicacionStr;
    }

    public String getUbicacionResidenciaRecursiva(int id) {
        String ubicacionStr = "";
        Ubicacion _municipio = null;
        if (getUbicacionesRecursiva() != null) {
            _municipio = getUbicacionesRecursiva().get(id);
        }
        if (_municipio != null && _municipio.getUbicacionPadre() != null) {
            Ubicacion _departamento = _municipio.getUbicacionPadre();
            if (_departamento.getUbicacionPadre() != null) {
                Ubicacion _pais = _departamento.getUbicacionPadre();
                // ubicacionStr = _pais.getNombre();
            }
            ubicacionStr = _departamento.getNombre();
            ubicacionStr = _municipio.getNombre() + " - " + ubicacionStr;
        }
        return ubicacionStr;
    }

    public List<Maestro> completarMaestroEPS(String query) {
        List<Maestro> epsFiltradas = new ArrayList<>();
        for (Maestro maestro : getListadoEps()) {
            if (maestro.getNombre().toLowerCase().contains(query.toLowerCase())) {
                epsFiltradas.add(maestro);
            }
        }
        if (epsFiltradas.size() == 1) {
            getObjetoTraslado().setMaestroEps(epsFiltradas.get(0));
        }
        return epsFiltradas;
    }

    public List<Ubicacion> completarUbicacion(String query) {
        List<Ubicacion> ubicacionesFiltradas = new ArrayList<>();
        for (Ubicacion ubicacion : getUbicaciones()) {
            if (ubicacion.getNombre().toLowerCase().contains(query.toLowerCase())) {
                ubicacionesFiltradas.add(ubicacion);
            }
        }
        if (ubicacionesFiltradas.size() == 1) {
            getObjeto().setAfiliacionUbicacion(ubicacionesFiltradas.get(0));
            //actualizarSedes();
        }
        return ubicacionesFiltradas;
    }

    public List<Ubicacion> completarUbicacionResidencia(String query) {
        List<Ubicacion> ubicacionesFiltradas = new ArrayList<>();
        for (Ubicacion ubicacion : getUbicaciones()) {
            if (ubicacion.getNombre().toLowerCase().contains(query.toLowerCase())) {
                ubicacionesFiltradas.add(ubicacion);
            }
        }
        if (ubicacionesFiltradas.size() == 1) {
            getObjeto().setAfiliacionUbicacion(ubicacionesFiltradas.get(0));
            //actualizarSedes();
        }
        return ubicacionesFiltradas;
    }

    /**
     * funcion para actualizar las listas de PrestadoresSedes y el hash,
     * teniendo en cuenta el municipio seleccionado Para usarse en la
     * funcionalidad de Crear y Editar
     * @param estado
     */
    public void actualizarSedes(boolean estado) {
        this.consultarResidencia = estado;
        //2021-11-10 jyperez actualizamos el municipio de residencia desde pantalla.
        if (!estado) {
            this.objeto.setResidenciaUbicacion(this.objeto.getAfiliacionUbicacion());
        }
        getAfiliadosServicio().consultarSedesMunicipio(this);
        //PrimeFaces.current().ajax().update("frmCrear:panelCrear");
    }

    /**
     * funcion para consultar si un afiliado ya existe en la base de datos
     */
    public void validarAfiliadoExistente() {
        getAfiliadosServicio().consultarAfiliadoExistente(this);
        // solo se actualiza el panelGrid donde está incluido los componentes que llaman a la funcionalidad
        PrimeFaces.current().ajax().update("frmCrear:panelCrear1");
        //probamos si esto no afecta la estabilidad de la pantalla.. independiente de cual dialog esté abierto
        PrimeFaces.current().ajax().update("frmEditar:panelEditar1");
    }

    /**
     * funcion para consultar si un afiliado ya existe en la base de datos
     */
    public void validarAfiliadoCFExistente() {
        getAfiliadosServicio().consultarAfiliadoCabezaFamilia(this);
        // solo se actualiza el panelGrid donde está incluido los componentes que llaman a la funcionalidad
        PrimeFaces.current().ajax().update("frmCrear:panelCrear7");
        PrimeFaces.current().ajax().update("frmCrear:panelCrear3");// Georeferenciacion
        PrimeFaces.current().ajax().update("frmCrear:panelCrear10");//Dirección
        PrimeFaces.current().ajax().update("frmCrear:panelCrear4");// Zona y los otros campos
        PrimeFaces.current().ajax().update("frmCrear:panelCrear11");// Contactos
        PrimeFaces.current().ajax().update("frmCrear:panelCrear12");// IPS Atención Primaria
        PrimeFaces.current().ajax().update("frmCrear:panelCrear14");// Datos Socieconómicos
        PrimeFaces.current().ajax().update("frmCrear:panelCrear15");// Discapacidad
        PrimeFaces.current().ajax().update("frmCrear:panelCrear16");// Estado Afiliación
        PrimeFaces.current().ajax().update("frmCrear:panelCrear17");// Observación
        //PrimeFaces.current().ajax().update("frmCrear:panelCrear13");// Datos Movilidad
        //PrimeFaces.current().ajax().update("frmEditar:panelEditar3");
    }

    /**
     * funcion para consultar si un afiliado ya existe en la base de datos
     */
    public void validarAfiliadoCFExistenteEditar() {
        getAfiliadosServicio().consultarAfiliadoCabezaFamiliaEditar(this);
        // solo se actualiza el panelGrid donde está incluido los componentes que llaman a la funcionalidad
        PrimeFaces.current().ajax().update("frmEditar:panelEditar3");
    }

    /**
     * función para actualizar la lista de causas novedades una vez se cambie el
     * valor en la lista de estados afiliacion
     */
    public void actualizarListaCausaNovedad() {
        getAfiliadosServicio().consultarMaestroCausaNovedadPorEstado(this);
        // solo se actualiza el panelGrid donde está incluido los componentes que llaman a la funcionalidad
        //2021-04-28 jyperez INC 788 Se elimina selección del campo Origen del Afiliado para que el usuario vuelva a ingresarlo,
        // en caso de que este se está actualizando al estado PENDIENTE DE SOPORTE
        if (getValorEstadoAfiliacion(getObjetoAnterior().getMaeEstadoAfiliacion()).equals(AfiliadoParametro.ESTADO_AFILIACION_RETIRADO)
                && getValorEstadoAfiliacion(getObjeto().getMaeEstadoAfiliacion()).equals(AfiliadoParametro.ESTADO_AFILIACION_PENDIENTE_SOPORTE)) {
            getObjeto().setMaeOrigenAfiliado(0);
        }
        //2024-03-13 jyperez N00-42 traslado preaprobado: validamos si la acción del estado seleccionado es inactivo, entonces desmarcamos el campo
        if (getObjeto().isTrasladoPreaprobado()) {
            Maestro estadoAux = getEstadoAfiliacionCompleto(getObjeto().getMaeEstadoAfiliacion());
            if (estadoAux != null && estadoAux.contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_INACTIVO)) {
                getObjeto().setTrasladoPreaprobado(false);
        }
}
        //PrimeFaces.current().ajax().update("frmEditar:panelEditar2");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar7");
    }
    
    /**
     * función para actualizar la lista de comunidad etnica cada vez que se cambie el valor
     * en la lista de grupo etnico
     */
    public void actualizarListaComunidadEtnica() {
        getAfiliadosServicio().consultarMaestroComunidadEtnicaPorEtnia(this);
    }

    /**
     * función que realizara la actualización de todos los panelGrid que hacen
     * parte del formulario de Crear
     */
    public void actualizarFormularioCrear() {
        PrimeFaces.current().ajax().update("frmCrear");
        PrimeFaces.current().ajax().update("frmCrear:panelCrear1");
        PrimeFaces.current().ajax().update("frmCrear:panelCrear2");
        PrimeFaces.current().ajax().update("frmCrear:panelCrear3");
        PrimeFaces.current().ajax().update("frmCrear:panelCrear4");
        PrimeFaces.current().ajax().update("frmCrear:panelCrear5");
        PrimeFaces.current().ajax().update("frmCrear:tablaCrearEncuesta");
        PrimeFaces.current().ajax().update("frmCrear:panelCrear6");
        PrimeFaces.current().ajax().update("frmCrear:panelCrear7");
        PrimeFaces.current().ajax().update("frmCrear:accordionPanelCrear8");
        //PrimeFaces.current().ajax().update("frmCrear:accordionPanelCrear8:panelCrear8");
        PrimeFaces.current().ajax().update("frmCrear:panelCrear9");
        PrimeFaces.current().ajax().update("frmCrear:panelCrear11");// Contactos
        PrimeFaces.current().ajax().update("frmCrear:panelCrear12");// IPS Atención Primaria
        PrimeFaces.current().ajax().update("frmCrear:panelCrear14");// Datos Socieconómicos
        PrimeFaces.current().ajax().update("frmCrear:panelCrear15");// Discapacidad
        PrimeFaces.current().ajax().update("frmCrear:panelCrear16");// Estado Afiliación
        PrimeFaces.current().ajax().update("frmCrear:panelCrear17");// Observación
        PrimeFaces.current().ajax().update("frmCrear:panelCrear18");// Datos Adicionales de Emergencia
        PrimeFaces.current().ajax().update("frmCrear:panelCrear19");// Datos Adicionales de Emergencia - dirección
        //PrimeFaces.current().ajax().update("frmCrear:panelCrear13");// Datos Movilidad
    }

    /**
     * función que realizara el reseteo de todos los panelGrid que hacen parte
     * del formulario de Crear
     */
    public void resetearFormularioCrear() {
        PrimeFaces.current().resetInputs("frmCrear");
        PrimeFaces.current().resetInputs("frmCrear:panelCrear1");
        PrimeFaces.current().resetInputs("frmCrear:panelCrear2");
        PrimeFaces.current().resetInputs("frmCrear:panelCrear3");
        PrimeFaces.current().resetInputs("frmCrear:panelCrear4");
        PrimeFaces.current().resetInputs("frmCrear:panelCrear5");
        PrimeFaces.current().resetInputs("frmCrear:tablaCrearEncuesta");
        PrimeFaces.current().resetInputs("frmCrear:panelCrear6");
        PrimeFaces.current().resetInputs("frmCrear:panelCrear7");
        PrimeFaces.current().resetInputs("frmCrear:accordionPanelCrear8");
        //PrimeFaces.current().resetInputs("frmCrear:accordionPanelCrear8:panelCrear8");
        PrimeFaces.current().resetInputs("frmCrear:panelCrear9");
        PrimeFaces.current().resetInputs("frmCrear:panelCrear11");// Contactos
        PrimeFaces.current().resetInputs("frmCrear:panelCrear12");// IPS Atención Primaria
        PrimeFaces.current().resetInputs("frmCrear:panelCrear14");// Datos Socieconómicos
        PrimeFaces.current().resetInputs("frmCrear:panelCrear15");// Discapacidad
        PrimeFaces.current().resetInputs("frmCrear:panelCrear16");// Estado Afiliado
        PrimeFaces.current().resetInputs("frmCrear:panelCrear17");// Observación
        PrimeFaces.current().resetInputs("frmCrear:panelCrear18");// Datos Adicionales de Emergencia
        PrimeFaces.current().resetInputs("frmCrear:panelCrear19");// Datos Adicionales de Emergencia - dirección
        //PrimeFaces.current().resetInputs("frmCrear:panelCrear13");// Datos Movilidad
    }

    /**
     * función que realizara la actualización de todos los panelGrid que hacen
     * parte del formulario de Actualizar
     */
    public void actualizarFormularioEditar() {
        PrimeFaces.current().ajax().update("frmEditar:panelEditar1");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar2");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar3");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar4");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar5");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar6");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar7");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar8");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar9");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar10");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar11");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar12");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar13");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar14");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar15");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar16");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar17");
        PrimeFaces.current().ajax().update("frmEditar:panelEditar18");
        PrimeFaces.current().ajax().update("frmEditar:accordionPanelEditar1");
        PrimeFaces.current().ajax().update("frmEditar:pnlEditarEncuestaAfiliado");
        PrimeFaces.current().ajax().update("frmEditar:pnlEditarPortabilidad");
        PrimeFaces.current().ajax().update("frmEditar:pnlEditarGrupoFamiliarAfiliado");
        PrimeFaces.current().ajax().update("frmEditar:pnlEditarProgramasEspeciales");
        //PrimeFaces.current().ajax().update("frmEditar:accordionPanelEditar1:panelEditar10");
    }

    /**
     * función que realizara el reseteo de todos los panelGrid que hacen parte
     * del formulario de Crear
     */
    public void resetearFormularioEditar() {
        PrimeFaces.current().resetInputs("frmEditar:panelEditar1");
        PrimeFaces.current().resetInputs("frmEditar:panelEditar2");
        PrimeFaces.current().resetInputs("frmEditar:panelEditar3");
        PrimeFaces.current().resetInputs("frmEditar:panelEditar4");
        PrimeFaces.current().resetInputs("frmEditar:panelEditar5");
        PrimeFaces.current().resetInputs("frmEditar:panelEditar6");
        PrimeFaces.current().resetInputs("frmEditar:panelEditar7");
        PrimeFaces.current().resetInputs("frmEditar:panelEditar8");
        PrimeFaces.current().resetInputs("frmEditar:panelEditar9");
        PrimeFaces.current().resetInputs("frmEditar:panelEditar10");
        PrimeFaces.current().resetInputs("frmEditar:panelEditar11");
        PrimeFaces.current().resetInputs("frmEditar:panelEditar12");
        PrimeFaces.current().resetInputs("frmEditar:panelEditar13");
        PrimeFaces.current().resetInputs("frmEditar:panelEditar14");
        PrimeFaces.current().resetInputs("frmEditar:panelEditar15");
        PrimeFaces.current().resetInputs("frmEditar:panelEditar16");
        PrimeFaces.current().resetInputs("frmEditar:panelEditar17");
        PrimeFaces.current().resetInputs("frmEditar:panelEditar18");
        PrimeFaces.current().resetInputs("frmEditar:accordionPanelEditar1");
        PrimeFaces.current().resetInputs("frmEditar:pnlEditarEncuestaAfiliado");
        PrimeFaces.current().resetInputs("frmEditar:pnlEditarPortabilidad");
        PrimeFaces.current().resetInputs("frmEditar:pnlEditarGrupoFamiliarAfiliado");
        PrimeFaces.current().resetInputs("frmEditar:pnlEditarProgramasEspeciales");
        //PrimeFaces.current().resetInputs("frmEditar:accordionPanelEditar1:panelEditar10");
    }

    public List<Ubicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<Ubicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public HashMap<Integer, Ubicacion> getUbicacionesRecursiva() {
        return ubicacionesRecursiva;
    }

    public void setUbicacionesRecursiva(HashMap<Integer, Ubicacion> ubicacionesRecursiva) {
        this.ubicacionesRecursiva = ubicacionesRecursiva;
    }

    public List<Maestro> getListaGrupoPoblacional() {
        return listaGrupoPoblacional;
    }

    public void setListaGrupoPoblacional(List<Maestro> listaGrupoPoblacional) {
        this.listaGrupoPoblacional = listaGrupoPoblacional;
    }

    public HashMap<Integer, Maestro> getHashGrupoPoblacional() {
        return hashGrupoPoblacional;
    }

    public void setHashGrupoPoblacional(HashMap<Integer, Maestro> hashGrupoPoblacional) {
        this.hashGrupoPoblacional = hashGrupoPoblacional;
    }

    public List<Maestro> getListaCausaNovedad() {
        return listaCausaNovedad;
    }

    public void setListaCausaNovedad(List<Maestro> listaCausaNovedad) {
        this.listaCausaNovedad = listaCausaNovedad;
    }

    public HashMap<Integer, Maestro> getHashCausaNovedad() {
        return hashCausaNovedad;
    }

    public void setHashCausaNovedad(HashMap<Integer, Maestro> hashCausaNovedad) {
        this.hashCausaNovedad = hashCausaNovedad;
    }

    public List<Maestro> getListaCajaCompensacion() {
        return listaCajaCompensacion;
    }

    public void setListaCajaCompensacion(List<Maestro> listaCajaCompensacion) {
        this.listaCajaCompensacion = listaCajaCompensacion;
    }

    public HashMap<Integer, Maestro> getHashCajaCompensacion() {
        return hashCajaCompensacion;
    }

    public void setHashCajaCompensacion(HashMap<Integer, Maestro> hashCajaCompensacion) {
        this.hashCajaCompensacion = hashCajaCompensacion;
    }

    public List<Maestro> getListaARL() {
        return listaARL;
    }

    public void setListaARL(List<Maestro> listaARL) {
        this.listaARL = listaARL;
    }

    public HashMap<Integer, Maestro> getHashARL() {
        return hashARL;
    }

    public void setHashARL(HashMap<Integer, Maestro> hashARL) {
        this.hashARL = hashARL;
    }

    public List<Maestro> getListaAFP() {
        return listaAFP;
    }

    public void setListaAFP(List<Maestro> listaAFP) {
        this.listaAFP = listaAFP;
    }

    public HashMap<Integer, Maestro> getHashAFP() {
        return hashAFP;
    }

    public void setHashAFP(HashMap<Integer, Maestro> hashAFP) {
        this.hashAFP = hashAFP;
    }

    public List<Maestro> getListaActividadEconomica() {
        return listaActividadEconomica;
    }

    public void setListaActividadEconomica(List<Maestro> listaActividadEconomica) {
        this.listaActividadEconomica = listaActividadEconomica;
    }

    public HashMap<Integer, Maestro> getHashActividadEconomica() {
        return hashActividadEconomica;
    }

    public void setHashActividadEconomica(HashMap<Integer, Maestro> hashActividadEconomica) {
        this.hashActividadEconomica = hashActividadEconomica;
    }

    public List<CntPrestadorSede> getListaPrestadoresSedes() {
        return listaPrestadoresSedes;
    }

    public void setListaPrestadoresSedes(List<CntPrestadorSede> listaPrestadoresSedes) {
        this.listaPrestadoresSedes = listaPrestadoresSedes;
    }

    public HashMap<String, CntPrestadorSede> getHashPrestadoresSedes() {
        return hashPrestadoresSedes;
    }

    public void setHashPrestadoresSedes(HashMap<String, CntPrestadorSede> hashPrestadoresSedes) {
        this.hashPrestadoresSedes = hashPrestadoresSedes;
    }

    public String getEstadoCivil(String valor) {
        String equivalente;
        switch (valor) {
            case "1":
                equivalente = "Soltero";
                break;
            case "2":
                equivalente = "Casado";
                break;
            case "3":
                equivalente = "Viudo";
                break;
            case "4":
                equivalente = "Divorciado";
                break;
            case "5":
                equivalente = "Unión Libre";
                break;
            case "6":
                equivalente = "Divorciado";
                break;
            case "7":
                equivalente = "No reportado";
                break;
            case "8":
                equivalente = "Otro";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    public String getTipoAfiliado(String valor) {
        String equivalente;
        switch (valor) {
            case "101":
                equivalente = "Cotizante";
                break;
            case "102":
                equivalente = "Beneficiario";
                break;
            case "103":
                equivalente = "Cabeza de Hogar";
                break;
            case "104":
                equivalente = "Adicional";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    public String getZona(String valor) {
        String equivalente;
        switch (valor) {
            case "R":
                equivalente = "Rural";
                break;
            case "U":
                equivalente = "Urbana";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    public String getGrupoEtnico(String valor) {
        String equivalente;
        switch (valor) {
            case "1":
                equivalente = "Indígena";
                break;
            case "2":
                equivalente = "ROM (Gitanos)";
                break;
            case "3":
                equivalente = "Raizal";
                break;
            case "4":
                equivalente = "Palenquero san jacinto";
                break;
            case "5":
                equivalente = "Afrocolombiano";
                break;
            case "6":
                equivalente = "Otras";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    public String getNivelSisben(int id) {
        String equivalente = "";
        //2021-05-20 jyperez se actualiza al maestro
        try {
            equivalente = hashNivelSisbenNuevo.get(id).getNombre();
        }catch (Exception e) {
            
        }
        return equivalente;
    }

    public String getTipoDiscapacidad(String valor) {
        String equivalente;
        switch (valor) {
            case "1":
                equivalente = "Física";
                break;
            case "2":
                equivalente = "Neuro-Sensorial";
                break;
            case "3":
                equivalente = "Mental";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    public String getCategoriaIbc(String valor) {
        String equivalente;
        switch (valor) {
            case "101":
                equivalente = "Categoría A";
                break;
            case "102":
                equivalente = "Categoría B";
                break;
            case "103":
                equivalente = "Categoría C";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    public String getModeloLiquidacion(String valor) {
        String equivalente;
        switch (valor) {
            case "0":
                equivalente = "CAPITA";
                break;
            case "1":
                equivalente = "EVENTO";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    public String getParentescoCotizante(String valor) {
        String equivalente;
        switch (valor) {
            case "1":
                equivalente = "Cónyuge o compañero(a) permanente";
                break;
            case "2":
                equivalente = "Hijos del cotizante o del compañero(a) permanente, menores de veinticinco años que dependen económicamente del cotizante";
                break;
            case "3":
                equivalente = "Padre o madre del cotizante, cabeza de familia";
                break;
            case "4":
                equivalente = "Hijos de beneficiarios menores de 25 años o con incapacidad permanente";
                break;
            case "5":
                equivalente = "Hasta tercer grado de consanguinidad - Hijos de menores de 25 años o cualquier edad con incapacidad permanente";
                break;
            case "7":
                equivalente = "Padres que dependan económicamente de alguno de los cónyuges o compañero(a) permanente del cotizante, cuando ambos cotizan, cabeza de familia";
                break;
            case "8":
                equivalente = "Afiliado adicional";
                break;
            case "9":
                equivalente = "Hijos del cotizante o del compañero(a) permanente, de cualquier edad si tienen incapacidad permanente y depende económicamente del cotizante, cabeza de familia";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    public String getEstadoPortabilidad(int valor) {
        String equivalente;
        switch (valor) {
            case 1:
                equivalente = "En Trámite";
                break;
            case 2:
                equivalente = "Aprobada";
                break;
            case 3:
                equivalente = "Rechazada";
                break;
            case 4:
                equivalente = "Cancelada x Usuario";
                break;
            case 5:
                equivalente = "Finalizada";
                break;
            case 6:
                equivalente = "Aprobado con Prórroga";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    public String getTipoPortabilidad(int valor) {
        String equivalente;
        switch (valor) {
            case 1:
                equivalente = "Ocasional";
                break;
            case 2:
                equivalente = "Temporal";
                break;
            case 3:
                equivalente = "Permanente";
                break;
            default:
                equivalente = "";
                break;
        }
        return equivalente;
    }

    // obtener valores a partir de tablas maestras
    /**
     *
     * @param id
     * @return
     */
    public String getOrigenAfiliado(Integer id) {
        try {
            return hashOrigenAfiliado.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public String getGrupoPoblacional(Integer id) {
        try {
            return hashGrupoPoblacional.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     *
     * @param valor
     * @return
     */
    public Integer getIdEstadoAfiliacion(String valor) {
        try {
            return hashValorEstadosAfiliacion.get(valor).getId();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Función para obtener el identificador del registro de EstadoAfiliación
     *
     * @param id
     * @return
     */
    public String getEstadoAfiliacion(Integer id) {
        try {
            return hashEstadosAfiliacion.get(id).getNombre();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Función para obtener el valor del registro de EstadoAfiliación
     *
     * @param id
     * @return
     */
    public String getValorEstadoAfiliacion(Integer id) {
        try {
            return hashEstadosAfiliacion.get(id).getValor();
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * Función para obtener el valor del registro de EstadoAfiliación
     *
     * @param id
     * @return
     */
    public String getValorEstadoAfiliacionCompleto(Integer id) {
        try {
            return hashEstadosAfiliacionCompleto.get(id).getValor();
        } catch (Exception e) {
            return "";
        }
    }
    
    public Maestro getEstadoAfiliacionCompleto(Integer id) {
        try {
            return hashEstadosAfiliacionCompleto.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<AsegAfiliado> getListaGrupoFamiliarAfiliado() {
        return listaGrupoFamiliarAfiliado;
    }

    public void setListaGrupoFamiliarAfiliado(List<AsegAfiliado> listaGrupoFamiliarAfiliado) {
        this.listaGrupoFamiliarAfiliado = listaGrupoFamiliarAfiliado;
    }

    public Ubicacion getUbicacionConsulta() {
        return ubicacionConsulta;
    }

    public void setUbicacionConsulta(Ubicacion ubicacionConsulta) {
        this.ubicacionConsulta = ubicacionConsulta;
    }

    public List<AsegPortabilidad> getListaPortabilidadAfiliado() {
        return listaPortabilidadAfiliado;
    }

    public void setListaPortabilidadAfiliado(List<AsegPortabilidad> listaPortabilidadAfiliado) {
        this.listaPortabilidadAfiliado = listaPortabilidadAfiliado;
    }

    public boolean isAfiliadoExistente() {
        return afiliadoExistente;
    }

    public void setAfiliadoExistente(boolean afiliadoExistente) {
        this.afiliadoExistente = afiliadoExistente;
    }

    public HashMap<String, Maestro> getHashValorEstadosAfiliacion() {
        return hashValorEstadosAfiliacion;
    }

    public void setHashValorEstadosAfiliacion(HashMap<String, Maestro> hashValorEstadosAfiliacion) {
        this.hashValorEstadosAfiliacion = hashValorEstadosAfiliacion;
    }

    public String getMensajeAfiliadoExistente() {
        return mensajeAfiliadoExistente;
    }

    public void setMensajeAfiliadoExistente(String mensajeAfiliadoExistente) {
        this.mensajeAfiliadoExistente = mensajeAfiliadoExistente;
    }

    public List<AsegTabulacionEncuesta> getListaEncuestaAfiliado() {
        return ListaEncuestaAfiliado;
    }

    public void setListaEncuestaAfiliado(List<AsegTabulacionEncuesta> ListaEncuestaAfiliado) {
        this.ListaEncuestaAfiliado = ListaEncuestaAfiliado;
    }

    public List<AsegEncuestaPregunta> getListaPreguntasEncuesta() {
        return listaPreguntasEncuesta;
    }

    public void setListaPreguntasEncuesta(List<AsegEncuestaPregunta> listaPreguntasEncuesta) {
        this.listaPreguntasEncuesta = listaPreguntasEncuesta;
    }

    /**
     * Función utilizada para reiniciar la lista de la Encuesta que será
     * gestionada desde la pantalla Crear
     */
    private void cargarPreguntasEncuesta() {
        this.ListaEncuestaAfiliado = new ArrayList<>();
        for (AsegEncuestaPregunta pre : this.listaPreguntasEncuesta) {
            AsegTabulacionEncuesta obj = new AsegTabulacionEncuesta();
            obj.setEncuestaPregunta(pre);
            this.ListaEncuestaAfiliado.add(obj);
        }
    }

    /**
     * Función para validar el tipo afiliado Beneficiario y su relación no los
     * otros datos necesarios
     *
     * @return
     */
    public String validarTipoAfiliadoBeneficiario() {
        String mensaje;
        if (this.objeto.getTipoBeneficiario().equals(AfiliadoParametro.TIPO_AFILIADO_BENEFICIARIO)
                && (this.objeto.getMaeTipoDocumentoCf() != null && this.objeto.getMaeTipoDocumentoCf() != 0)
                && (this.objeto.getNumeroDocumentoCf() != null && !this.objeto.getNumeroDocumentoCf().trim().equals(""))) {
            mensaje = "";
        } else if (!this.objeto.getTipoBeneficiario().equals(AfiliadoParametro.TIPO_AFILIADO_BENEFICIARIO)) {
            mensaje = "";
        } else {
            mensaje = "Si Tipo de Afiliado es Beneficiario, deben ingresarse los campos Tipo Documento CF y Número Documento CF";
        }
        return mensaje;
    }

    /**
     * Funcion para validar que cuando se seleccione Nacimiento, los valores de
     * Tipo Documento CF y Numero Documento CF tengan valores
     *
     * @return
     */
    public String validarOrigenAfiliadoNacimiento() {
        String mensaje;
        if (hashOrigenAfiliado.get(this.objeto.getMaeOrigenAfiliado()).getValor().equals(AfiliadoParametro.ORIGEN_AFILIADO_NACIMIENTO)
                && (this.objeto.getMaeTipoDocumentoCf() != null && this.objeto.getMaeTipoDocumentoCf() != 0)
                && (this.objeto.getNumeroDocumentoCf() != null && !this.objeto.getNumeroDocumentoCf().trim().equals(""))) {
            mensaje = "";
        } else if (!hashOrigenAfiliado.get(this.objeto.getMaeOrigenAfiliado()).getValor().equals(AfiliadoParametro.ORIGEN_AFILIADO_NACIMIENTO)) {
            mensaje = "";
        } else {
            mensaje = "Si Origen Afiliado es "
                    + hashOrigenAfiliado.get(this.objeto.getMaeOrigenAfiliado()).getNombre()
                    + ", deben ingresarse los campos Tipo Documento CF y Número Documento CF";
        }
        return mensaje;
    }

    /**
     * Funcion para validar que cuando se seleccione Nacimiento, los valores de
     * Fecha de Nacimiento, Fecha Afiliacion SFSSS y Fecha afiliación EPS sean
     * iguales
     *
     * @return
     */
    public String validarOrigenAfiliadoFechaNacimiento() {
        String mensaje;
        if (hashOrigenAfiliado.get(this.objeto.getMaeOrigenAfiliado()).getValor().equals(AfiliadoParametro.ORIGEN_AFILIADO_NACIMIENTO)
                && (this.objeto.getFechaNacimiento().compareTo(this.objeto.getFechaAfiliacionSgsss()) == 0)
                && (this.objeto.getFechaNacimiento().compareTo(this.objeto.getFechaAfiliacionEps()) == 0)) {
            mensaje = "";
        } else if (!hashOrigenAfiliado.get(this.objeto.getMaeOrigenAfiliado()).getValor().equals(AfiliadoParametro.ORIGEN_AFILIADO_NACIMIENTO)) {
            mensaje = "";
        } else {
            mensaje = "Si Origen Afiliado es "
                    + hashOrigenAfiliado.get(this.objeto.getMaeOrigenAfiliado()).getNombre()
                    + ", los campos Fecha Nacimiento, Fecha Afiliacion SGSSS y Fecha Afiliación EPS deben ser iguales";
        }
        return mensaje;
    }

    /**
     * Función para validar el puntaje del sisben teniendo en cuenta: la Zona-
     * Rural o Urbana y el nivel seleccionado Si el municipio es Medellín La
     * validación aplica para Población Sisbenizada
     *
     * @return
     */
    public String validarPuntajeSisben() {
        String mensaje = "";
        double puntajeMinimo = 0.0;
        double puntajeNivel1Medellin = 47.99;
        double puntajeNivel2MedellinMin = 48;
        double puntajeNivel2MedellinMax = 54.86;
        double puntajeNivel1Urbano = 44.79;
        double puntajeNivel2UrbanoMin = 44.80;
        double puntajeNivel2UrbanoMax = 51.57;
        double puntajeNivel1Rural = 32.98;
        double puntajeNivel2RuralMin = 32.99;
        double puntajeNivel2RuralMax = 37.80;
        // validar inicialmente si el grupo poblacional seleccionado es Población Sisbenizada
        if (hashGrupoPoblacional.get(this.objeto.getMaeGrupoPoblacional()).getValor().equals(AfiliadoParametro.GRUPO_POBLACIONAL_POBLACION_SISBENIZADA)) {
            //validamos si el municipio de afiliacion es medellin
            if (this.objeto.getAfiliacionUbicacion().getId() == AfiliadoParametro.IDENTIFICADOR_MEDELLIN) {

                if (this.objeto.getNivelSisben().equals(AfiliadoParametro.NIVEL_1_SISBEN)
                        && (this.objeto.getPuntajeSisben() >= puntajeMinimo && this.objeto.getPuntajeSisben() <= puntajeNivel1Medellin)) {
                    mensaje = "";
                } else if (this.objeto.getNivelSisben().equals(AfiliadoParametro.NIVEL_1_SISBEN)) {
                    mensaje = "El puntaje del Sisben para Nivel 1 - Medellín debe estar entre " + puntajeMinimo + " y " + puntajeNivel1Medellin;
                } else if (this.objeto.getNivelSisben().equals(AfiliadoParametro.NIVEL_2_SISBEN)
                        && (this.objeto.getPuntajeSisben() >= puntajeNivel2MedellinMin && this.objeto.getPuntajeSisben() <= puntajeNivel2MedellinMax)) {
                    mensaje = "";
                } else if (this.objeto.getNivelSisben().equals(AfiliadoParametro.NIVEL_2_SISBEN)) {
                    mensaje = "El puntaje del Sisben para Nivel 2 - Medellín debe estar entre " + puntajeNivel2MedellinMin + " y " + puntajeNivel2MedellinMax;
                }
            } else {
                // sino es medelin, validamos la zona
                if (this.objeto.getZona().equals(AfiliadoParametro.ZONA_URBANA)) {
                    if (this.objeto.getNivelSisben().equals(AfiliadoParametro.NIVEL_1_SISBEN)
                            && (this.objeto.getPuntajeSisben() >= puntajeMinimo && this.objeto.getPuntajeSisben() <= puntajeNivel1Urbano)) {
                        mensaje = "";
                    } else if (this.objeto.getNivelSisben().equals(AfiliadoParametro.NIVEL_1_SISBEN)) {
                        mensaje = "El puntaje del Sisben para Nivel 1 - Otras Cabeceras debe estar entre " + puntajeMinimo + " y " + puntajeNivel1Urbano;
                    } else if (this.objeto.getNivelSisben().equals(AfiliadoParametro.NIVEL_2_SISBEN)
                            && (this.objeto.getPuntajeSisben() >= puntajeNivel2UrbanoMin && this.objeto.getPuntajeSisben() <= puntajeNivel2UrbanoMax)) {
                        mensaje = "";
                    } else if (this.objeto.getNivelSisben().equals(AfiliadoParametro.NIVEL_2_SISBEN)) {
                        mensaje = "El puntaje del Sisben para Nivel 2 - Otras Cabeceras debe estar entre " + puntajeNivel2UrbanoMin + " y " + puntajeNivel2UrbanoMax;
                    }
                } else if (this.objeto.getZona().equals(AfiliadoParametro.ZONA_RURAL)) {
                    if (this.objeto.getNivelSisben().equals(AfiliadoParametro.NIVEL_1_SISBEN)
                            && (this.objeto.getPuntajeSisben() >= puntajeMinimo && this.objeto.getPuntajeSisben() <= puntajeNivel1Rural)) {
                        mensaje = "";
                    } else if (this.objeto.getNivelSisben().equals(AfiliadoParametro.NIVEL_1_SISBEN)) {
                        mensaje = "El puntaje del Sisben para Nivel 1 - Rural debe estar entre " + puntajeMinimo + " y " + puntajeNivel1Rural;
                    } else if (this.objeto.getNivelSisben().equals(AfiliadoParametro.NIVEL_2_SISBEN)
                            && (this.objeto.getPuntajeSisben() >= puntajeNivel2RuralMin && this.objeto.getPuntajeSisben() <= puntajeNivel2RuralMax)) {
                        mensaje = "";
                    } else if (this.objeto.getNivelSisben().equals(AfiliadoParametro.NIVEL_2_SISBEN)) {
                        mensaje = "El puntaje del Sisben para Nivel 2 - Rural debe estar entre " + puntajeNivel2RuralMin + " y " + puntajeNivel2RuralMax;
                    }
                }
            }
        }
        return mensaje;
    }

    /**
     * Funcion para validar que cuando se seleccione Nacimiento, la fecha de
     * actual no sea mayor en un año que la fecha de nacimiento
     *
     * @return
     */
    public String validarFechaNacimientoNoMayorAnio() {
        String mensaje;
        if (hashOrigenAfiliado.get(this.objeto.getMaeOrigenAfiliado()).getValor().equals(AfiliadoParametro.ORIGEN_AFILIADO_NACIMIENTO)
                && this.getFechaActual().compareTo(calcularFechaCumpleanos(1, this.objeto.getFechaNacimiento())) < 0) {
            mensaje = "";
        } else if (!hashOrigenAfiliado.get(this.objeto.getMaeOrigenAfiliado()).getValor().equals(AfiliadoParametro.ORIGEN_AFILIADO_NACIMIENTO)) {
            mensaje = "";
        } else {
            mensaje = "Si Origen Afiliado es "
                    + hashOrigenAfiliado.get(this.objeto.getMaeOrigenAfiliado()).getNombre()
                    + ", la fecha de nacimiento no debe superar en 1 año a la fecha actual.";
        }
        return mensaje;
    }

    /**
     * Función para validar que se ingrese la Fecha de Expedición, si se
     * seleccionó Tipo de Documento "Cedula de Ciudadania"
     *
     * @return
     */
    public String validarFechaExpedicionCedulaCiudadania() {
        String mensaje;
        if (hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CEDULA_DE_CIUDADANIA)
                && objeto.getFechaExpedicionCedula() != null) {
            mensaje = "";
        } else if (!hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CEDULA_DE_CIUDADANIA)) {
            mensaje = "";
        } else {
            mensaje = "Si el Tipo Documento es "
                    + hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getNombre()
                    + " Fecha exp Documento es obligatorio";
        }
        return mensaje;
    }

    /**
     *
     * @return
     */
    public String validarRegistroBDUA() {
        String mensaje;
        if (this.objeto.isRegistroBdua()
                && (this.objetoTraslado.getTipoDocumentoBdua() != null && !this.objetoTraslado.getTipoDocumentoBdua().trim().equals(""))
                && (this.objetoTraslado.getNumeroDocumentoBdua() != null && !this.objetoTraslado.getNumeroDocumentoBdua().trim().equals(""))
                && (this.objetoTraslado.getPrimerApellidoBdua() != null && !this.objetoTraslado.getPrimerApellidoBdua().trim().equals(""))
                && (this.objetoTraslado.getPrimerNombreBdua() != null && !this.objetoTraslado.getPrimerNombreBdua().trim().equals(""))
                && (this.objetoTraslado.getFechaNacimientoBdua() != null)) {
            mensaje = "";
        } else if (!this.objeto.isRegistroBdua()) {
            mensaje = "";
        } else {
            mensaje = "Si seleccionaste 'Si' en Registra en BDUA, los campos Tipo Documento,Número Documento,"
                    + "Primer Apellido, Primer Nombre y Fecha Nacimiento es esa sección deben tener valores";
        }

        return mensaje;

    }

    /**
     * Función para validar que se ingrese por lo menos, uno de los siguientes
     * campos: Telefono o Celular
     *
     * @return
     */
    public String validarIngresoCelularTelefono() {
        String mensaje = "";
        if ((this.objeto.getTelefonoFijo() == null
                || this.objeto.getTelefonoFijo().trim().equals(""))
                && (this.objeto.getTelefonoMovil() == null
                || this.objeto.getTelefonoMovil().trim().equals(""))) {
            mensaje = "Se debe ingresar al menos uno de los dos campos: Teléfono Fijo o Teléfono Móvil";
        }

        return mensaje;
    }

    /**
     * Se debe validar que la fecha de afiliación de la EPS sea mayor o igual
     * que la fecha de afiliacion al SGSSS
     *
     * @return
     */
    public String validarFechaAfiliacionFechaIngreso() {
        String mensaje = "";
        if (this.objeto.getFechaAfiliacionEps().compareTo(this.objeto.getFechaAfiliacionSgsss()) < 0) {
            mensaje = "La Fecha Afiliación EPS debe ser mayor o igual a la Fecha Afiliación SGSSS";
        }
        return mensaje;
    }

    /**
     * Se debe validar que cuando se escoja el valor de grupo poblacional
     * diferente a población sisbenizada, el valor del nivel de sisben sea No
     * aplica
     *
     * @return
     */
    public String validarGrupoPoblacional() {
        String mensaje;
        if (!hashGrupoPoblacional.get(this.objeto.getMaeGrupoPoblacional()).getValor().equals(AfiliadoParametro.GRUPO_POBLACIONAL_POBLACION_SISBENIZADA)
                && this.objeto.getNivelSisben().equals("N")) {
            mensaje = "";
        } else if (!hashGrupoPoblacional.get(this.objeto.getMaeGrupoPoblacional()).getValor().equals(AfiliadoParametro.GRUPO_POBLACIONAL_POBLACION_SISBENIZADA)) {
            mensaje = "El valor del Nivel de Sisben debe ser No Aplica si el Grupo Poblacional es " + hashGrupoPoblacional.get(this.objeto.getMaeGrupoPoblacional()).getNombre();
        } else {
            mensaje = "";
        }
        return mensaje;
    }

    /**
     * Función para validar que si se ingresó la Fecha de Expedición y si se
     * seleccionó Tipo de Documento "Cedula de Ciudadania", la fecha de
     * expedición no sea inferior a la fecha del cumpleaños numero 18 del
     * usuario
     *
     * @return
     */
    public String validarFechaExpedicionCedulaCiudadFechaNac() {
        String mensaje;
        if (hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CEDULA_DE_CIUDADANIA)
                && objeto.getFechaExpedicionCedula() != null
                && objeto.getFechaExpedicionCedula().compareTo(calcularFechaCumpleanos(18, objeto.getFechaNacimiento())) > 0) {
            mensaje = "";
        } else if (!hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CEDULA_DE_CIUDADANIA)) {
            mensaje = "";
        } else {
            mensaje = "Si el Tipo Documento es "
                    + hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getNombre()
                    + "la Fecha exp Documento debe ser mayor a la fecha del cumpleaños 18 : " + calcularFechaCumpleanos(18, objeto.getFechaNacimiento()).toString();
        }
        return mensaje;
    }

    /**
     * Se debe validar que el tipo Documento coincida en Longitud y Edad a los
     * valores predefinidos para ello
     *
     * @return
     */
    public String validarTipoDocumentoEdad() {
        String mensaje = "";
        if (hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_REGISTRO_CIVIL)
                && (this.objeto.getNumeroDocumento().length() == 10 || this.objeto.getNumeroDocumento().length() == 11)
                && (calcularEdad(this.objeto.getFechaNacimiento()) < 7)) {
            mensaje = "";
        } else if (hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_REGISTRO_CIVIL)) {
            mensaje = "No se cumplen las  condiciones para Tipo Documento "
                    + hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getNombre()
                    + ": Numero Documento debe estar entre 10 y 11 dígitos, Edad del Afiliado debe ser menor a 7 años";
        }
        if (hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CERTIFICADO_NACIDO_VIVO)
                && (this.objeto.getNumeroDocumento().length() == 9)
                && (calcularEdad(this.objeto.getFechaNacimiento()) < 7)) {
            mensaje = "";
        } else if (hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CERTIFICADO_NACIDO_VIVO)) {
            mensaje = "No se cumplen las  condiciones para Tipo Documento "
                    + hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getNombre()
                    + ": Numero Documento debe ser de 9 dígitos, Edad del Afiliado debe ser 0 años";
        }
        if (hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_TARJETA_IDENTIDAD)
                && (this.objeto.getNumeroDocumento().length() == 10 || this.objeto.getNumeroDocumento().length() == 11)
                && (calcularEdad(this.objeto.getFechaNacimiento()) >= 7 && calcularEdad(this.objeto.getFechaNacimiento()) < 18)) {
            mensaje = "";
        } else if (hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_TARJETA_IDENTIDAD)) {
            mensaje = "No se cumplen las  condiciones para Tipo Documento "
                    + hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getNombre()
                    + ": Numero Documento debe estar entre 10 y 11 dígitos, Edad del Afiliado debe estar entre 7 y 17 años";
        }
        if (hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CEDULA_DE_CIUDADANIA)
                && (this.objeto.getNumeroDocumento().length() >= 6 && this.objeto.getNumeroDocumento().length() <= 10)
                && (this.objeto.getNumeroDocumento().length() != 9)
                && (calcularEdad(this.objeto.getFechaNacimiento()) >= 18)) {
            mensaje = "";
        } else if (hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CEDULA_DE_CIUDADANIA)) {
            mensaje = "No se cumplen las  condiciones para Tipo Documento "
                    + hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getNombre()
                    + ": Numero Documento debe estar entre 6 y 10 dígitos excluyendo 9 dígitos, Edad del Afiliado debe ser mayor o igual a 18 años";
        }
        if (hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_PERMISO_ESPECIAL_PERMANENCIA)
                && (this.objeto.getNumeroDocumento().length() == 15)) {
            mensaje = "";
        } else if (hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_PERMISO_ESPECIAL_PERMANENCIA)) {
            mensaje = "No se cumplen las  condiciones para Tipo Documento "
                    + hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getNombre()
                    + ": Numero Documento debe ser de 15 dígitos";
        }
        if (hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CEDULA_EXTRANGERIA)
                && (this.objeto.getNumeroDocumento().length() >= 3 && this.objeto.getNumeroDocumento().length() <= 6)) {
            mensaje = "";
        } else if (hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CEDULA_EXTRANGERIA)) {
            mensaje = "No se cumplen las  condiciones para Tipo Documento "
                    + hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getNombre()
                    + ": Numero Documento debe estar entre 3 y 6 dígitos";
        }
        if (hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_PASAPORTE)
                && (this.objeto.getNumeroDocumento().length() >= 3 && this.objeto.getNumeroDocumento().length() <= 16)) {
            mensaje = "";
        } else if (hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_PASAPORTE)) {
            mensaje = "No se cumplen las  condiciones para Tipo Documento "
                    + hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getNombre()
                    + ": Numero Documento debe estar entre 3 y 16 dígitos";
        }
        if (hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_MENOR_SIN_IDENTIFICACION)
                && (this.objeto.getNumeroDocumento().length() == 10 || this.objeto.getNumeroDocumento().length() == 12)) {
            mensaje = "";
        } else if (hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_MENOR_SIN_IDENTIFICACION)) {
            mensaje = "No se cumplen las  condiciones para Tipo Documento "
                    + hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getNombre()
                    + ": Numero Documento debe ser de 10 o 12 dígitos";
        }
        if (hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_SALVOCONDUCTO)
                && (this.objeto.getNumeroDocumento().length() == 9)) {
            mensaje = "";
        } else if (hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_SALVOCONDUCTO)) {
            mensaje = "No se cumplen las  condiciones para Tipo Documento "
                    + hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getNombre()
                    + ": Numero Documento debe ser de 9 dígitos";
        }
        if (hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_ADULTO_SIN_IDENTIFICACION)
                && (this.objeto.getNumeroDocumento().length() == 6 || this.objeto.getNumeroDocumento().length() == 10)) {
            mensaje = "";
        } else if (hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_ADULTO_SIN_IDENTIFICACION)) {
            mensaje = "No se cumplen las  condiciones para Tipo Documento "
                    + hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getNombre()
                    + ": Numero Documento debe ser de 6 o 10 dígitos";
        }
        if (hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CARNE_DIPLOMATICO)
                && (this.objeto.getNumeroDocumento().length() >= 3 && this.objeto.getNumeroDocumento().length() <= 11)) {
            mensaje = "";
        } else if (hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getValor().equals(AfiliadoParametro.TIPO_DOCUMENTO_CARNE_DIPLOMATICO)) {
            mensaje = "No se cumplen las  condiciones para Tipo Documento "
                    + hashTiposDocumento.get(this.objeto.getMaeTipoDocumento()).getNombre()
                    + ": Numero Documento debe estar 3 y 11 dígitos";
        }
        return mensaje;
    }

    public String validarMunicipioAfiliacion() {
        String mensaje = "";
        if (this.objeto.getAfiliacionUbicacion() == null) {
            mensaje = "Municipio Afiliación: Este campo es obligatorio";
        }

        return mensaje;
    }

    public String validarMunicipioResidencia() {
        String mensaje = "";
        if (this.objeto.getResidenciaUbicacion() == null) {
            mensaje = "Municipio Residencia: Este campo es obligatorio";
        }

        return mensaje;
    }

    public String validarDireccion() {
        String mensaje;
        if (!(this.objeto.getDireccionResidencia() == null)
                && !this.objeto.getDireccionResidencia().trim().equals("")) {
            mensaje = "";
        } else {
            mensaje = "Dirección: Este campo es obligatorio.";
        }

        return mensaje;
    }

    /**
     * Función que permite validar si la discapacidad de un afiliado es
     * temporal, para solicitar que se digiten los campos fecha de Inicio y
     * Fecha Fin de dicha discapacidad
     *
     * @return
     */
    public String validarDiscapacidadTemporal() {
        String mensaje;
        if (this.objeto.getCondicionDiscapacidad() != null
                && !this.objeto.getCondicionDiscapacidad().trim().equals("")
                && this.objeto.getCondicionDiscapacidad().equals(AfiliadoParametro.CONDICION_DISCAPACIDAD_TEMPORAL)
                && this.objeto.getFechaIniciodiscapacidad() != null
                && this.objeto.getFechaFinDiscapacidad() != null) {
            mensaje = "";
        } else if (this.objeto.getCondicionDiscapacidad() != null
                && !this.objeto.getCondicionDiscapacidad().trim().equals("")
                && this.objeto.getCondicionDiscapacidad().equals(AfiliadoParametro.CONDICION_DISCAPACIDAD_TEMPORAL)) {

            mensaje = "Si la condición de discapacidad es Temporal, debe digitarse una fecha de inicio y una fecha fin obligatoria. ";
        } else {
            mensaje = "";
        }

        return mensaje;
    }

    /**
     * Función para validar en caso de que se modifique el estado de un
     * afiliado, que se digiten valores de causa novedad y fecha novedad.
     *
     * @return
     */
    public String validarCambioEstadoAfiliado() {
        String mensaje = "";
        if (this.objeto.getMaeEstadoAfiliacion() != this.objetoAnterior.getMaeEstadoAfiliacion()
                && this.objeto.getFechaNovedad() != null
                && this.objeto.getMaeCausaNovedad() != 0) {
            mensaje = "";
        } else if (this.objeto.getMaeEstadoAfiliacion() != this.objetoAnterior.getMaeEstadoAfiliacion()) {
            mensaje = "Cuando se modifica el estado del afiliado, debe seleccionarse una causa de Novedad y una fecha de Novedad obligatoria.";
        } else {
            mensaje = "";
        }
        return mensaje;
    }

    /**
     * Función para validar en caso de que se modifique el estado de un
     * afiliado, que se digiten valores de causa novedad y fecha novedad.
     *
     * @return
     */
    /* 2021-05-20 jyperez se comenta funcionalidad no usada.
    public String validarCambioRegimenAfiliado() {
        String mensaje = "";
        if (this.objeto.getRegimen() != null
                && !this.objeto.getRegimen().trim().equals("")
                && !this.objeto.getRegimen().equals(this.objetoAnterior.getRegimen())
                && this.objeto.getFechaMovilidad() != null) {
            mensaje = "";
        } else if (this.objeto.getRegimen() != null
                && !this.objeto.getRegimen().trim().equals("")
                && !this.objeto.getRegimen().equals(this.objetoAnterior.getRegimen())) {
            mensaje = "Cuando se modifica el régimen del afiliado, la fecha de Movilidad es obligatoria.";
        } else {
            mensaje = "";
        }
        return mensaje;
    }*/

    /**
     * Función para validar que la escritura de un email sea correcta.
     *
     * @return
     */
    public String validarEmailCorrecto() {
        String mensaje = "";
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(this.objeto.getEmail());
        if (this.objeto.getEmail() != null
                && !this.objeto.getEmail().trim().equals("")
                && mather.find()) {
            mensaje = "";

        } else if (this.objeto.getEmail() != null
                && !this.objeto.getEmail().trim().equals("")) {
            mensaje = "Email: Debe ingresarse un email válido.";
        }

        return mensaje;
    }

    /**
     * Función que valida cuando se selecciona el valor de régimen contributivo
     * se digiten los valores de Categoría IBC y fecha de Movilidad
     *
     * @return
     */
    /* 2021-05-20 jyperez se comenta funcionalidad no usada.
    public String validarCategoriaIBC() {
        String mensaje = "";
        if (this.objeto.getRegimen().equals(REGIMEN_ CONTRIBUTIVO)
                && this.objeto.getCategoriaIbc() != null
                && !this.objeto.getCategoriaIbc().trim().equals("")
                && this.objeto.getFechaMovilidad() != null) {
            mensaje = "";
        } else if (this.objeto.getRegimen().equals(REGIMEN_ CONTRIBUTIVO)) {
            mensaje = "Si el régimen seleccionado es Contributivo, debe seleccionarse una categoría IBC y una fecha de Movilidad obligatoria";
        }
        return mensaje;
    }*/

    /**
     * Función que valida cuando se cambie el municipio de residencia del
     * afiliado, se realice tambien cambio en la ips a seleccionar.
     *
     * @return
     */
    public String validarCambioMunicipioAfiliado() {
        String mensaje = "";
        if (this.objeto.getResidenciaUbicacion() != null
                && this.objetoAnterior.getPrimariaPrestadorSede() != null
                && this.objetoAnterior.getResidenciaUbicacion().getId() != this.objeto.getResidenciaUbicacion().getId()) {
            mensaje = "";
        } else if (this.objeto.getResidenciaUbicacion() != null
                && this.objetoAnterior.getResidenciaUbicacion().getId() != this.objeto.getResidenciaUbicacion().getId()) {
            mensaje = "Si se realiza cambio de Municipio de Residencia, debe seleccionarse una nueva IPS Primaria";
        }
        return mensaje;
    }

    /**
     * Función que valida en la creación de afiliado, que no se permita el
     * registro de fechas de afiliacion EPS diferentes a las del mes actual
     * exceptuando si el afiliado es registrado con origen Nacimiento de Padres
     * Afiliados.
     *
     * @return
     */
    public String validarFechaAfiliacionEPS() {
        String mensaje = "";
        if (!this.hashOrigenAfiliado.get(this.objeto.getMaeOrigenAfiliado()).getValor().equals(AfiliadoParametro.ORIGEN_AFILIADO_NACIMIENTO)
                && validarMesFecha(this.objeto.getFechaAfiliacionEps(), this.fechaActual) == 0) {
            mensaje = "";

        } else if (!this.hashOrigenAfiliado.get(this.objeto.getMaeOrigenAfiliado()).getValor().equals(AfiliadoParametro.ORIGEN_AFILIADO_NACIMIENTO)) {
            mensaje = "Se debe ingresar una fecha de Afiliacion EPS correspondiente al mes actual";
        }
        return mensaje;
    }

    /**
     * función que valida que cuando la causa sea 'INGRESO CAMBIO MPIO VIENE DE
     * OTRA EPSS' el origen debe ser 'Traslado de otra EPS'
     *
     * @return
     */
    public String validarOrigenAfiliadoCausaNovedad() {
        String mensaje = "";
        if (hashOrigenAfiliado.get(this.objeto.getMaeOrigenAfiliado()).getValor().equals(AfiliadoParametro.ORIGEN_AFILIADO_TRASLADO_OTRA_EPS)
                && this.objeto.getMaeCausaNovedad() != 0
                && hashCausaNovedad.get(this.objeto.getMaeCausaNovedad()).getNombre().equals("INGRESO CAMBIO MPIO VIENE DE OTRA EPSS")) {
            mensaje = "";
        } else if (this.objeto.getMaeCausaNovedad() != 0
                && hashCausaNovedad.get(this.objeto.getMaeCausaNovedad()).getNombre().equals("INGRESO CAMBIO MPIO VIENE DE OTRA EPSS")) {
            mensaje = "Se debe seleccionar el origen Traslado de otra EPS para la Causa Novedad seleccionada";
        }
        return mensaje;
    }

    /**
     * Función para validar que si se seleccionó Autoriza Envio SMS, se haya
     * ingresado un celular
     *
     * @return
     */
    public String validarEnvioSMS() {
        String mensaje = "";
        if (this.objeto.getAutorizaEnvioSms()
                && this.objeto.getTelefonoMovil() != null
                && !this.objeto.getTelefonoMovil().trim().equals("")) {
            mensaje = "";
        } else if (this.objeto.getAutorizaEnvioSms()) {
            mensaje = "Si se selecciona Autorizo Envio SMS, debe ingresarse un Celular";
        }
        return mensaje;
    }

    /**
     * Función para validar que si se seleccionó Autoriza Envio Email, se haya
     * ingresado un email
     *
     * @return
     */
    public String validarEnvioEmail() {
        String mensaje = "";
        if (this.objeto.getAutorizaEnvioEmail()
                && this.objeto.getEmail() != null
                && !this.objeto.getEmail().trim().equals("")) {
            mensaje = "";
        } else if (this.objeto.getAutorizaEnvioEmail()) {
            mensaje = "Si se selecciona Autorizo Envio Email, debe ingresarse un Email";
        }
        return mensaje;
    }

    /**
     * Función para validar que cuando se edita un registro en estado
     * ‘Suspendido (Contributivo)’ solo debe registrar cuando el usuario se
     * encuentra en régimen contributivo.
     *
     * @return
     */
    /* 2021-05-20 jyperez se comenta funcionalidad no usada.
    public String validarRegimenEstadoAfiliacion() {
        String mensaje = "";
        if (this.objeto.getRegimen().equals(REGIMEN_ CONTRIBUTIVO)
                && this.objeto.getMaeEstadoAfiliacion() != 0
                && hashEstadosAfiliacion.get(this.objeto.getMaeEstadoAfiliacion()).getValor().equals(ESTADO_AFILIACION_SUSPENDIDO)) {
            mensaje = "";
        } else if (this.objeto.getMaeEstadoAfiliacion() != 0
                && hashEstadosAfiliacion.get(this.objeto.getMaeEstadoAfiliacion()).getValor().equals(ESTADO_AFILIACION_SUSPENDIDO)) {
            mensaje = "Para el estado de afiliación " + hashEstadosAfiliacion.get(this.objeto.getMaeEstadoAfiliacion()).getNombre()
                    + " el Regimen del afiliado debe ser Contributivo";
        }
        return mensaje;
    }*/

    public void activarCategoriaIBC() {
        //if (this.objeto.getRegimen().equals(REGIMEN_ CONTRIBUTIVO)) {
        //2021-05-20 jyperez actualizamos variable al maestro
        Maestro mae = hashRegimen.get(this.objeto.getMaeRegimenId());
        if (mae!= null && mae.getValor().equals(AfiliadoParametro.REGIMEN_CONTRIBUTIVO)) {
            this.setHabilitarCategoriaIBC(true);
        } else {
            this.setHabilitarCategoriaIBC(false);
            this.objeto.setCategoriaIbc(null);
            this.objeto.setMaeCategoriaIbcId(null);
            this.objeto.setMaeCategoriaIbcCodigo(null);
            this.objeto.setMaeCategoriaIbcValor(null);
            //2021-11-22 cuando se cambia de a regimen subsidiado, se deben eliminar los valores de los campos relacionados a contributivo
            this.objeto.setMaeActividadEconomica(null);
            //this.objeto.setMaeActividadEconomicaCodigo(null);
            //this.objeto.setMaeActividadEconomicaValor(null);
            this.objeto.setMaeTipoDocumentoAportante(null);
            this.objeto.setMaeTipoDocumentoAportanteCodigo(null);
            this.objeto.setMaeTipoDocumentoAportanteValor(null);
            this.objeto.setNumeroDocumentoAportante(null);
            this.objeto.setMaeArl(null);
            //this.objeto.setMaeArlCodigo(null);
            //this.objeto.setMaeArlValor(null);
            this.objeto.setMaeAfp(null);
            //this.objeto.setMaeAfpCodigo(null);
            //this.objeto.setMaeAfpValor(null);
            this.objeto.setMaeCajaCompensacion(null);
            //this.objeto.setMaeCajaCompensacionCodigo(null);
            //this.objeto.setMaeCajaCompensacionValor(null);
        }
        cargarListaTipoAfiliado();
        //2021-12-20 jyperez se incluye el llamado a la lista de Parentesco
        afiliadoServicio.consultarMaestroParentescoPorRegimen(this);
        //2021-12-20 jyperez se incluye el llamado a la lista de Estados, debido a que debe actualizarse según el régimen.
        afiliadoServicio.consultarMaestroEstadoPorEstadoYRegimen(this);
    }

    public boolean validarEstadoPermitido(int estado, int regimen) {
        boolean habilitar = false;
        try {
            //Caso estado fallecido: no se habilita
            if (hashEstadosAfiliacion.get(estado).getValor().equals(AfiliadoParametro.ESTADO_AFILIACION_FALLECIDO)) {
                //2020-09-09 jyperez INC 298 permitir al rol analista poder editar un afiliado fallecido
                habilitar = !this.isAccionAdicional5();
            }
            //2021-12-17 jyperez validación estado Accion Activo - Regimén Contributivo
            if (hashRegimen.get(regimen).getValor().equals(AfiliadoParametro.REGIMEN_CONTRIBUTIVO) &&
                    hashEstadosAfiliacion.get(estado).contieneAccion(MaestroAccion.ASEG_ESTADO_AFILIACION_AFILIADO_ACTIVO)) {
                //permitir al rol analista poder editar el afiliado con acción activo regimén contributivo
                habilitar = !this.isAccionAdicional5();
            }
            //2024-01-25 jyperez no debe permitirse si el estado es Duplicado
            if (hashEstadosAfiliacion.get(estado).getValor().equals(AfiliadoParametro.ESTADO_AFILIACION_DUPLICADO)) {
                habilitar = true;
            }
            //2025-03-03 jyperez Retiro por Resolución del Municipio
            if (hashEstadosAfiliacion.get(estado).getValor().equals(AfiliadoParametro.ESTADO_AFILIACION_RETIRADO_RES_MUNICIPIO)) {
                //2025-03-03 jyperez Retiro por Resolución del Municipio
                habilitar = !this.isAccionAdicional9();
            }
            
        } catch (Exception ex) {
            habilitar = false;
        }
        return habilitar;
    }
    
    public boolean validarEstadoDuplicado(int estado) {
        boolean habilitar = false;
        try {
            if (hashEstadosAfiliacion.get(estado).getValor().equals(AfiliadoParametro.ESTADO_AFILIACION_DUPLICADO)) {
                habilitar = true;
            }
        } catch (Exception ex) {
            habilitar = false;
        }
        return habilitar;
    }

    /**
     * Calcular fecha en la que se cumplio el cumpleaños 18
     *
     * @param anio
     * @param fecha
     * @return
     */
    public static Date calcularFechaCumpleanos(int anio, Date fecha) {
        Date cumpleanos18;
        Calendar fechaNac = Calendar.getInstance();
        Calendar fechaCumpleanos18 = Calendar.getInstance();
        //Calendar fechaActual = Calendar.getInstance();

        // cargamos la fecha a evaluar
        fechaNac.setTime(fecha);
        // Cálculo de las diferencias.
        int years = fechaNac.get(Calendar.YEAR) + anio;
        int months = fechaNac.get(Calendar.MONTH);
        int days = fechaNac.get(Calendar.DAY_OF_MONTH);

        fechaCumpleanos18.set(years, months, days);
        cumpleanos18 = fechaCumpleanos18.getTime();
        return cumpleanos18;
    }

    public int calcularEdad(Date fecha) {
        int years = 0;
        if (fecha != null) {
            Calendar fechaNac = Calendar.getInstance();
            Calendar fechaActual = Calendar.getInstance();
            // cargamos la fecha a evaluar
            fechaNac.setTime(fecha);
            // Cálculo de las diferencias.
            years = fechaActual.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
            int months = fechaActual.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH);
            int days = fechaActual.get(Calendar.DAY_OF_MONTH) - fechaNac.get(Calendar.DAY_OF_MONTH);
            // Hay que comprobar si el día de su cumpleaños es posterior
            // a la fecha actual, para restar 1 a la diferencia de años,
            // pues aún no ha sido su cumpleaños.
            if (months < 0 // Aún no es el mes de su cumpleaños
                    || (months == 0 && days < 0)) { // o es el mes pero no ha llegado el día.
                years--;
            }
        }
        return years;
    }
    
    public String calcularEdadEnDiasMesesAnios(Date fecha) {
        SimpleDateFormat formato5 = new SimpleDateFormat("dd/MM/yyyy");
        try {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaNacimiento = formato5.format(fecha);
            Period periodo = Period.between(LocalDate.parse(fechaNacimiento, fmt), LocalDate.now());
            String texto = periodo.getYears() + " años " + periodo.getMonths() + " meses " + periodo.getDays() + " dias";
            return texto;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Función para validar si dos fechas se encuentran en el mismo mes y el
     * mismo año
     *
     * @param fecha1
     * @param fecha2
     * @return si el valor es 0, las fechas son iguales. Si es 1 las fechas no
     * cumplen
     */
    public static int validarMesFecha(Date fecha1, Date fecha2) {
        int resultado = 0;
        Calendar fechaA = Calendar.getInstance();
        Calendar fechaB = Calendar.getInstance();

        // cargamos las fechas a evaluar
        fechaA.setTime(fecha1);
        fechaB.setTime(fecha2);

        if (fechaA.get(Calendar.YEAR) == fechaB.get(Calendar.YEAR)
                && fechaA.get(Calendar.MONTH) == fechaB.get(Calendar.MONTH)) {
            resultado = 0;
        } else {
            resultado = 1;
        }
        return resultado;
    }

    /**
     * Función para cargar dinámicamente la lista de TipoAfiliado en Editar
     */
    public void cargarListaTipoAfiliado() {
        //2021-05-20 jyperez actualizamos a la variable del maestro
        Maestro maestro = hashRegimen.get(this.objeto.getMaeRegimenId());
        if (maestro != null) {
            //2021-12-20 cargamos los valores del maestro tipoAfiliado desde la Bd, utilizando la relacion de maestros
            //cargamos los maestros pertenecientes al regimen contributivo
            this.hashTipoAfiliado = new HashMap();
            this.listaTipoAfiliado = new ArrayList<>();
            afiliadoServicio.consultarMaestroTipoAfiliadoPorRegimen(this);
        }
    }

    /**
     * Función para cargar dinámicamente la lista de Nivel Sisben , Etnia y Grupo Sisben
     */
    public void cargarListaNivelSisbenInterna() {
        //2021-05-20 jyperez se realiza cambio al maestro de Nivel
        afiliadoServicio.consultarMaestroNivelSisbenPorGrupoPoblacionalYGrupoSisben(this);
        //2021-05-04 jyperez 0797 habilitamos el campo de subgrupo sisben cuando sea seleccionado el grupo poblacional
        //2022-07-21 jyperez adicionamos a la validación el grupo población población no pobre no vulnerable. Aplicará las mismas reglas que población sisbenizada
        //2024-12-12 jyperez ajuste por error con codigo 4 - Creador o gestor cultural decreto 2283 de 2010
        // se valida que el hash tambien tenga valor
        if (this.objeto.getMaeGrupoPoblacional() != 0 && 
                hashGrupoPoblacional.get(this.objeto.getMaeGrupoPoblacional()) != null &&
                (hashGrupoPoblacional.get(this.objeto.getMaeGrupoPoblacional()).getValor().equals(AfiliadoParametro.GRUPO_POBLACIONAL_POBLACION_SISBENIZADA) ||
                hashGrupoPoblacional.get(this.objeto.getMaeGrupoPoblacional()).getValor().equals(AfiliadoParametro.GRUPO_POBLACIONAL_POBLACION_NO_POBRE_NO_VULNERABLE))) {
            deshabilitaSubGrupoSisben = false;
        } else {
            deshabilitaSubGrupoSisben = true;
            //reseteo campo suggrupo
            this.objeto.setMaeGrupoSisbenId(null);
            this.objeto.setMaeSubGrupoSisbenId(null);
            //this.grupoSeleccionado = 0;
        }
        //2021-12-20 jyperez adicionamos carga maestro Etnia
        afiliadoServicio.consultarMaestroEtniaPorGrupoPoblacional(this);        
        //2021-12-20 jyperez adicionamos carga maestro Grupo Sisben
        afiliadoServicio.consultarMaestroGrupoSisbenPorGrupoPoblacional(this);
        //2022-01-17 jyperez llamado para actualizar la comunidad etnica
        actualizarListaComunidadEtnica();
        //2022-02-21 jyperez cargar metodología grupo poblacional
        afiliadoServicio.consultarMaestroMetodGrupoPoblacionalPorGrupoPob(this);
    }
    /**
     * Función para cargar dinámicamente la lista de Nivel Sisben en Editar
     */
    public void cargarListaNivelSisben() {
        //2021-05-20 jyperez se realiza cambio al maestro de Nivel
        afiliadoServicio.consultarMaestroNivelSisbenPorGrupoPoblacionalYGrupoSisben(this);
        //2021-05-04 jyperez 0797 habilitamos el campo de subgrupo sisben cuando sea seleccionado el grupo poblacional
        //2022-07-21 jyperez adicionamos a la validación el grupo población población no pobre no vulnerable. Aplicará las mismas reglas que población sisbenizada
        if (this.objeto.getMaeGrupoPoblacional() != 0 && 
                (hashGrupoPoblacional.get(this.objeto.getMaeGrupoPoblacional()).getValor().equals(AfiliadoParametro.GRUPO_POBLACIONAL_POBLACION_SISBENIZADA)) ||
                hashGrupoPoblacional.get(this.objeto.getMaeGrupoPoblacional()).getValor().equals(AfiliadoParametro.GRUPO_POBLACIONAL_POBLACION_NO_POBRE_NO_VULNERABLE)) {
            deshabilitaSubGrupoSisben = false;
        } else {
            deshabilitaSubGrupoSisben = true;
            //reseteo campo subgrupo
            this.objeto.setMaeGrupoSisbenId(null);
            this.objeto.setMaeSubGrupoSisbenId(null);
            //this.grupoSeleccionado = 0;
        }
        //2022-01-17 jyperez reiniciamos valores de campo etnia y comunidad etnica
        this.objeto.setMaeEtniaId(null);
        this.objeto.setMaeEtniaCodigo("");
        this.objeto.setMaeEtniaValor("");
        this.objeto.setMaeComunidadEtniaId(null);
        this.objeto.setMaeComunidadEtniaCodigo("");
        this.objeto.setMaeComunidadEtniaValor("");
        //2021-12-20 jyperez adicionamos carga maestro Etnia
        afiliadoServicio.consultarMaestroEtniaPorGrupoPoblacional(this);        
        //2021-12-20 jyperez adicionamos carga maestro Grupo Sisben
        afiliadoServicio.consultarMaestroGrupoSisbenPorGrupoPoblacional(this);
        //2022-01-17 jyperez llamado para actualizar la comunidad etnica
        actualizarListaComunidadEtnica();
        //2022-02-21 jyperez cargar metodología grupo poblacional
        afiliadoServicio.consultarMaestroMetodGrupoPoblacionalPorGrupoPob(this);
        //2022-09-15 jyperez llamamos a la función que evalua si se encuentra sin encuesta Sisben 4
        validarAfiliadoSinEncuestaSisbenVersion4();
    }

    public void actualizarNivelSisben() {
        if (this.objeto.getMaeGrupoSisbenId() != null) {
        Maestro grupo = hashGrupoSisben.get(this.objeto.getMaeGrupoSisbenId());
            if (grupo != null) {
                if (grupo.getValor().equals(AfiliadoParametro.GRUPO_SISBEN_C)) {
                    this.objeto.setNivelSisben(AfiliadoParametro.NIVEL_2_SISBEN);
                    //2021-05-20 jyperez actualizamos al campo maestro
                    Maestro nivel = hashValorNivelSisbenNuevo.get(AfiliadoParametro.NIVEL_2_SISBEN);
                    if (nivel != null) {
                        this.objeto.setMaeNivelSisbenId(nivel.getId());
                        this.objeto.setMaeNivelSisbenCodigo(nivel.getValor());
                        this.objeto.setMaeNivelSisbenValor(nivel.getNombre());
                    }
                }
            }
            getAfiliadosServicio().consultarMaestroPorcentajeDistribucionPorSubGrupoSisben(this);
        }
    }

    /**
     * función para actualizar la lista de subgrupos
     */
    public void actualizarSubgrupoSisben() {
        try {
            // por cada item en la lista de subGrupo, validamos que tenga como padre el grupo seleccionado
            afiliadoServicio.consultarMaestroSubGrupoSisbenPorGrupoSisben(this);
            //2022-04-25 jyperez hay que consultar nuevamente el nivel de sisben
            afiliadoServicio.consultarMaestroNivelSisbenPorGrupoPoblacionalYGrupoSisben(this);
            //2021-07-08 jyperez INC 857 validamos si se seleccionó grupo. Si es así, el subgrupo es requerido
            if (listaSubGrupoSisben.isEmpty()) {
                requeridoSubGrupoSisben = false;
            }else {
                requeridoSubGrupoSisben = true;
            }
            //2022-06-08 jyperez actualizamos el campo de contribución solidaria
            validarContribucionSolidaria();
            //2022-09-15 jyperez llamamos a la función que evalua si se encuentra sin encuesta Sisben 4
            validarAfiliadoSinEncuestaSisbenVersion4();
        } catch (Exception e) {

        }
    }
    
    /* 2022-04-25 jyperez 
    public String getGrupoSisben(Integer subgrupo) { BORRAR
        String mensaje = "";
        try {
            //2022-04-22 PENDIENTE se comenta debido a que esto es un nuevo REQ del sprint 7
            Maestro ma = new Maestro();//hashSubGrupoSisben.get(subgrupo).getMaestro();
            if (ma != null) {
                mensaje = ma.getValor();
                grupoSeleccionado = ma.getId();
            }
        } catch (Exception e) {

        }
        return mensaje;
    }*/

    public String getSubGrupoSisben(Integer subgrupo) {
        String mensaje = "";
        try {
            Maestro ma = hashSubGrupoSisben.get(subgrupo);
            if (ma != null) {
                mensaje = ma.getValor();
            }
        } catch (Exception e) {

        }
        return mensaje;
    }

    public boolean isAfiliadoCFExistente() {
        return afiliadoCFExistente;
    }

    public void setAfiliadoCFExistente(boolean afiliadoCFExistente) {
        this.afiliadoCFExistente = afiliadoCFExistente;
    }

    public String getMensajeAfiliadoCFExistente() {
        return mensajeAfiliadoCFExistente;
    }

    public void setMensajeAfiliadoCFExistente(String mensajeAfiliadoCFExistente) {
        this.mensajeAfiliadoCFExistente = mensajeAfiliadoCFExistente;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public List<AsegRegistroNovedad> getListaHistorialNovedad() {
        return listaHistorialNovedad;
    }

    public void setListaHistorialNovedad(List<AsegRegistroNovedad> listaHistorialNovedad) {
        this.listaHistorialNovedad = listaHistorialNovedad;
    }

    public LazyDataModel<AsegRegistroNovedad> getLazyHistorial() {
        return lazyHistorial;
    }

    public void setLazyHistorial(LazyDataModel<AsegRegistroNovedad> lazyHistorial) {
        this.lazyHistorial = lazyHistorial;
    }
    
    /**
     * Función para resetear el campo discapacidad en caso de que se cambie el valor a false
     */
    public void validarDiscapacidad() {
        if (!this.objeto.isDiscapacidad()) {
            this.objeto.setMaeCondicionDiscapacidadId(null);
            this.objeto.setMaeCondicionDiscapacidadCodigo(null);
            this.objeto.setMaeCondicionDiscapacidadValor(null);
            this.objeto.setCondicionDiscapacidad(null);
            this.objeto.setMaeTipoDiscapacidadId(null);
            this.objeto.setMaeTipoDiscapacidadCodigo(null);
            this.objeto.setMaeTipoDiscapacidadValor(null);
            this.objeto.setTipoDiscapacidad(null);
            this.objeto.setFechaIniciodiscapacidad(null);
            this.objeto.setFechaFinDiscapacidad(null);
            
        }
    }
    
    /**
     * Función para resetear el campo acuerdo pago en caso de que se cambie el valor a false
     */
    public void validarAcuerdoPago() {
        if (this.objeto.getAcuerdoPago() != null && !this.objeto.getAcuerdoPago()) {
            this.objeto.setFechaAcuerdoPago(null);
        }
    }
    
    public void actualizarEstadoDuplicado() {
        if (this.objeto.getDuplicado()) {
            Maestro estadoAfiliacionDuplicado = this.hashValorEstadosAfiliacion.get(AfiliadoParametro.ESTADO_AFILIACION_DUPLICADO);
            if (estadoAfiliacionDuplicado != null) {
                this.objeto.setMaeEstadoAfiliacion(estadoAfiliacionDuplicado.getId());
                this.objeto.setMaeEstadoAfiliacionCodigo(estadoAfiliacionDuplicado.getValor());
                this.objeto.setMaeEstadoAfiliacionValor(estadoAfiliacionDuplicado.getNombre());
                //actualizamos la lista de Causa Novedad
                actualizarListaCausaNovedad();
                //configuramos el valor por defecto que será con código IS_58
                Maestro causaNovedadDuplicado = this.getHashCausaNovedadValor().get(AfiliadoParametro.CODIGO_NOVEDAD_IS_58);
                if (causaNovedadDuplicado != null) {
                    this.objeto.setMaeCausaNovedad(causaNovedadDuplicado.getId());
                    this.objeto.setMaeCausaNovedadCodigo(causaNovedadDuplicado.getValor());
                    this.objeto.setMaeCausaNovedadValor(causaNovedadDuplicado.getNombre());
                    //actualizamos la fecha de novedad
                    this.objeto.setFechaNovedad(new Date());
                } else {
                    //reseteamos los valores para indicar el error
                    this.objeto.setMaeCausaNovedad(0);
                    this.objeto.setMaeCausaNovedadCodigo("");
                    this.objeto.setMaeCausaNovedadValor("");
                    //actualizamos la fecha de novedad
                    this.objeto.setFechaNovedad(new Date());
                }
            } else {
                // no se realiza ningun ajuste. Validar cuando se seleccione Duplicado que el estado es correcto.
            }
            
        } else {
            //configuramos estado anterior
            this.objeto.setMaeEstadoAfiliacion(this.objetoAnterior.getMaeEstadoAfiliacion());
            this.objeto.setMaeEstadoAfiliacionCodigo(this.objetoAnterior.getMaeEstadoAfiliacionCodigo());
            this.objeto.setMaeEstadoAfiliacionValor(this.objetoAnterior.getMaeEstadoAfiliacionValor());
            //actualizamos la lista de Causa Novedad
            actualizarListaCausaNovedad();
            this.objeto.setMaeCausaNovedad(this.objetoAnterior.getMaeCausaNovedad());
            this.objeto.setMaeCausaNovedadCodigo(this.objetoAnterior.getMaeCausaNovedadCodigo());
            this.objeto.setMaeCausaNovedadValor(this.objetoAnterior.getMaeCausaNovedadValor());
            //reseteamos la fecha de novedad
            this.objeto.setFechaNovedad(null);
        }
    }

    public void marcarBeneficiario() {
        //beneficiario = !(this.objeto.getTipoBeneficiario() != null && this.objeto.getTipoBeneficiario().equals("102"));
        //obtener el maestro relacionado al id
        try{
            Maestro mae = hashTipoAfiliadoCompleto.get(this.objeto.getMaeTipoAfiliadoId());
            //2022-01-21 jyperez se adiciona afiliado adicional para permitirle la misma configuración que le beneficiario
            if (mae != null) {
                beneficiario = !(mae.getValor().equals(AfiliadoParametro.TIPO_AFILIADO_BENEFICIARIO) ||
                        mae.getValor().equals(AfiliadoParametro.TIPO_AFILIADO_ADICIONAL));
                if (beneficiario) {
                    //2022-01-24 jyperez INC 1144 adicionamos campo Parentesco para limpiar
                    this.objeto.setMaeParentescoCotizanteId(0);
                    this.objeto.setMaeParentescoCotizanteCodigo("");
                    this.objeto.setMaeParentescoCotizanteValor("");
                    this.objeto.setMaeTipoDocumentoCf(0);
                    this.objeto.setMaeTipoDocumentoCfCodigo("");
                    this.objeto.setMaeTipoDocumentoCfValor("");
                    this.objeto.setNumeroDocumentoCf("");
                    this.objeto.setNombresCF("");
                    this.objeto.setApellidosCF("");
                }
            }else {
                beneficiario = true;
                //2022-01-24 jyperez INC 1144 adicionamos campo Parentesco para limpiar
                this.objeto.setMaeParentescoCotizanteId(0);
                this.objeto.setMaeParentescoCotizanteCodigo("");
                this.objeto.setMaeParentescoCotizanteValor("");
                this.objeto.setMaeTipoDocumentoCf(0);
                this.objeto.setMaeTipoDocumentoCfCodigo("");
                this.objeto.setMaeTipoDocumentoCfValor("");
                this.objeto.setNumeroDocumentoCf("");
                this.objeto.setNombresCF("");
                this.objeto.setApellidosCF("");
            }
        } catch(Exception e) {
            //se actualiza a true teniendo en cuenta que si el valor es nulo, se niega la respuesta y esta sería true
            beneficiario = true;
            //2022-01-24 jyperez INC 1144 adicionamos campo Parentesco para limpiar
            this.objeto.setMaeParentescoCotizanteId(0);
            this.objeto.setMaeParentescoCotizanteCodigo("");
            this.objeto.setMaeParentescoCotizanteValor("");
            this.objeto.setMaeTipoDocumentoCf(0);
            this.objeto.setMaeTipoDocumentoCfCodigo("");
            this.objeto.setMaeTipoDocumentoCfValor("");
            this.objeto.setNumeroDocumentoCf("");
            this.objeto.setNombresCF("");
            this.objeto.setApellidosCF("");
        }
    }

    public boolean isBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(boolean beneficiario) {
        this.beneficiario = beneficiario;
    }

    public void listarAdjuntos(int id) {
        getObjeto().setId(id);
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_LISTAR);
        getAfiliadosServicio().Accion(this);
        if (!super.isError()) {
            PrimeFaces.current().resetInputs("frmAdjuntos");
            PrimeFaces.current().ajax().update("frmAdjuntos");
            PrimeFaces.current().executeScript("PF('dialogoAdjuntos').show()");
        }
        procesoFinal();
    }

    public void crearAdjunto() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_CREAR);
        getAfiliadosServicio().Accion(this);
        this.adjunto.setTipoArchivo(AfiliadoParametro.TIPO_ARCHIVO_NOVEDADES_AFILIADO);
        // 2020-10-11 jyperez ajuste INC XXX - error cuando no existe novedades (radicados)
        if (listaRadicados != null && listaRadicados.size() != 0) {
            this.adjunto.setRadicadoNovedad(listaRadicados.get(0));
        } else {
            this.adjunto.setRadicadoNovedad(new AsegRadicadoNovedad());
        }
        PrimeFaces.current().resetInputs("frmCrearAdjunto:panelCrearAdjunto");
        PrimeFaces.current().ajax().update("frmCrearAdjunto:panelCrearAdjunto");
        PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').show()");
        procesoFinal();
    }

    public void guardarAdjunto() {
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_GUARDAR);
        getAfiliadosServicio().Accion(this);
        if (!super.isError()) {
            // se adiciona esta opción para validar que actualice la lista de afiliados.
            PrimeFaces.current().ajax().update("frmAfiliados");
            PrimeFaces.current().executeScript("PF('dialogoCrearAdjunto').hide();");
        }
        procesoFinal();
    }

    public void cargarAdjunto(FileUploadEvent event) throws IOException {
        try {
            if (this.adjunto.getTipoArchivo() != 0 && this.adjunto.getRadicadoNovedad() != null) {
                AsegAdjunto adjuntoDocumento = new AsegAdjunto();
                UploadedFile archivo = event.getFile();
                adjuntoDocumento.setAdjuntoStream(archivo.getInputStream());
                String nombreAdjunto = FilenameUtils.getName(event.getFile().getFileName());
                int indiceExtension = nombreAdjunto.lastIndexOf(".");
                String extension = nombreAdjunto.substring(indiceExtension, nombreAdjunto.length());
                adjuntoDocumento.setObservacion(nombreAdjunto);
                adjuntoDocumento.setExtensionAdjunto(extension);
                adjuntoDocumento.setNombreArchivo(nombreAdjunto);
                //2020-08-12 jyperez se incluye adicionarle los campos de evento debido a que, como estaba actualmente se cargaban siempre sobre el nuevo objeto.
                adjuntoDocumento.setTipoArchivo(adjunto.getTipoArchivo());
                adjuntoDocumento.setRadicadoNovedad(adjunto.getRadicadoNovedad());
                setAdjunto(adjuntoDocumento);
                //2020-08-12 jperez se adiciona el llamado a la funcionalidad que guarda el adjuto, para volver automático el proceso
                // y eliminar el botón guardar de la pantalla
                this.guardarAdjunto();
            } else {
                if (this.adjunto.getRadicadoNovedad() == null) {
                    this.addError("Fecha de Novedad: Este campo es obligatorio.");
                }
                if (this.adjunto.getTipoArchivo() == 0) {
                    this.addError("Tipo Archivo: Este campo es obligatorio.");
                }
                //2021-03-18 jyperez se cambia por la funcionalidad de mostrar mensajes, debido a que sólo carga el archivo mas no lo guarda
                generarMensajes();
                //procesoFinal();
            }
        } catch (IOException ex) {
            Logger.getLogger(AfiliadosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void descargarAdjunto(AsegAdjunto adjunto) {
        String rutaCompleta = adjunto.getRuta() + adjunto.getArchivo();
        this.adjunto = adjunto;
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_VER);
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
            String attachmentName = "attachment; filename=\"" + adjunto.getNombreArchivo() + "\"";
            ec.setResponseHeader("Content-Disposition", attachmentName);
            if (ext.equalsIgnoreCase(".pdf")) {
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
            OutputStream output = ec.getResponseOutputStream();
            output.write(exportContent);
            output.close();
            fc.responseComplete();
        } catch (Exception e) {
            this.addError("Ocurrio un error al intentar descargar el archivo");
        }
        procesoFinal();
    }

    public StreamedContent descargarAdjunto(int id) throws FileNotFoundException {
        StreamedContent streamedContent = null;
        setAdjunto(new AsegAdjunto(id));
        super.setAccion(ACCION_ADICIONAL_2);
        super.setDoAccion(ACCION_VER);
        getAfiliadosServicio().Accion(this);
        if (!getAdjunto().getNombreArchivo().contains(".pdf")) {
            getAdjunto().setNombreArchivo(getAdjunto().getNombreArchivo() + ".pdf");
        }
        try {
            Path path = Paths.get(getAdjunto().getRuta() + getAdjunto().getArchivo());
            byte[] bytes = Files.readAllBytes(path);
            InputStream stream = new ByteArrayInputStream(bytes);
            stream.mark(0); //remember to this position!
            streamedContent =DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name(getAdjunto().getNombreArchivo()).build(); 
//            setArchivoPDF(streamedContent);
        } catch (IOException ex) {
            addError("Adjunto no encontrado");
        }
        return streamedContent;
    }

    public void setArchivo(UploadedFile archivo) {
        this.archivo = archivo;
    }

    public List<AsegAdjunto> getListaArchivos() {
        return listaArchivos;
    }

    public void setListaArchivos(List<AsegAdjunto> listaArchivos) {
        this.listaArchivos = listaArchivos;
    }

    public int getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(int tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public Date getFechaNovedad() {
        return fechaNovedad;
    }

    public void setFechaNovedad(Date fechaNovedad) {
        this.fechaNovedad = fechaNovedad;
    }

    public AsegRadicadoNovedad getObjetoRadicado() {
        return objetoRadicado;
    }

    public void setObjetoRadicado(AsegRadicadoNovedad objetoRadicado) {
        this.objetoRadicado = objetoRadicado;
    }

    public ReporteEncuesta016 getReporte() {
        return reporte;
    }

    public void setReporte(ReporteEncuesta016 reporte) {
        this.reporte = reporte;
    }

    public List<ReporteEncuesta016> getListaReporte016() {
        return listaReporte016;
    }

    public void setListaReporte016(List<ReporteEncuesta016> listaReporte016) {
        this.listaReporte016 = listaReporte016;
    }

    public StreamedContent generarReporte016() {
        StreamedContent streamedContent2 = null;
        try {
            getReporte().setId("" + this.getObjeto().getId());
            super.setAccion(ACCION_ADICIONAL_3);
            getAfiliadosServicio().Accion(this);
            if (getReporte().getId() != null && getReporte().getStrDocumento() != null) {
                listaReporte016 = new ArrayList();
                listaReporte016.add(getReporte());
                //Estructura de JasperReport
                InputStream is = getClass().getResourceAsStream("/reportes/encuesta_derechos_deberes.jasper");
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaReporte016);
                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));
                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0); //remember to this position!
                streamedContent2 = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name("Reporte.pdf").build();
            } else {
                this.addError("El usuario no cuenta con  Registro de Entrega de Carta de Deberes y Derechos");
                generarMensajes();
            }

        } catch (JRException ex) {
            streamedContent2 = null;
            this.addError("Error Reporte: " + ex.toString() + ex.getMessage());
            System.out.println("Error Stream: " + ex.toString() + ex.getMessage());
            generarMensajes();
        }
        //2021-03-18 jyperez se adiciona el llamado a la función de proceso final
        procesoFinal();
        return streamedContent2;
    }

    public ReporteAfiliacion getReporteAfiliacion() {
        return reporteAfiliacion;
    }

    public void setReporteAfiliacion(ReporteAfiliacion reporteAfiliacion) {
        this.reporteAfiliacion = reporteAfiliacion;
    }

    public List<ReporteAfiliacion> getListaReporteAfiliacion() {
        return listaReporteAfiliacion;
    }

    public void setListaReporteAfiliacion(List<ReporteAfiliacion> listaReporteAfiliacion) {
        this.listaReporteAfiliacion = listaReporteAfiliacion;
    }

    public StreamedContent generarReporteFormularioAfiliacion() {
        StreamedContent streamedContent2 = null;
        try {
            reporteAfiliacion = new ReporteAfiliacion();
            getReporteAfiliacion().setId("" + this.getObjeto().getId());
            listaReporteAfiliacion = new ArrayList();
            //2024-12-05 jyperez agregamos doAccion con acción adicional 1 para identificar el formulario antiguo
            super.setAccion(ACCION_ADICIONAL_4);
            super.setDoAccion(ACCION_ADICIONAL_1);
            getAfiliadosServicio().Accion(this);

            if (listaReporteAfiliacion.size() > 0) {
                //Estructura de JasperReport
                InputStream is = getClass().getResourceAsStream("/reportes/formulario_afiliacion.jasper");
                JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(listaReporteAfiliacion);
                Map parameters = new HashMap();
                parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "CO"));
                byte[] bytes = JasperRunManager.runReportToPdf(is, parameters, beanColDataSource);
                InputStream stream = new ByteArrayInputStream(bytes);
                stream.mark(0); //remember to this position!
                streamedContent2 =DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name("Reporte.pdf").build(); 
            } else {
                addError("No se encontraron datos para generer el reporte para afiliado");
                generarMensajes();
            }

        } catch (JRException ex) {
            streamedContent2 = null;
            System.out.println("Error Stream: " + ex.toString() + ex.getMessage());
        }
        //2021-03-18 jyperez se adiciona el llamado a la función de proceso final
        procesoFinal();
        return streamedContent2;
    }
    
    public StreamedContent generarReporteFormularioAfiliacionRes1823() {
        StreamedContent streamedContent2 = null;
        try {
            setAdjuntoStream(null);
            formularioAfiliadoRes1823 = new FormularioAfiliadoRes1823();
            //2024-12-05 jyperez agregamos doAccion con acción adicional 2 para identificar el formulario nuevo
            super.setAccion(ACCION_ADICIONAL_4);
            super.setDoAccion(ACCION_ADICIONAL_2);
            getAfiliadosServicio().Accion(this);

            if (getAdjuntoStream() != null) {
                InputStream stream = getAdjuntoStream();
                stream.mark(0); //remember to this position!
                streamedContent2 = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name(formularioAfiliadoRes1823.getNombreArchivo()).build();
            } else {
                addError("No se encontraron datos para generar el formulario de afiliación res. 1823.");
                generarMensajes();
            }

        } catch (Exception ex) {
            streamedContent2 = null;
            System.out.println("Error generarReporteFormularioAfiliacionRes1823: " + ex.toString() + ex.getMessage());
        }
        procesoFinal();
        return streamedContent2;
    }
    
    public StreamedContent generarReporteCertificadoAfiliacion() {
        StreamedContent streamedContent2 = null;
        try {
            setAdjuntoStream(null);
            certificadoAfiliacion = new CertificadoAfiliacion();
            certificadoAfiliacion.setIdAfiliado(this.getObjeto().getId());
            super.setAccion(ACCION_ADICIONAL_6);
            getAfiliadosServicio().Accion(this);

            if (getAdjuntoStream() != null) {
                InputStream stream = getAdjuntoStream();
                stream.mark(0); //remember to this position!
                streamedContent2 = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name(certificadoAfiliacion.getNombreArchivo()).build();
            } else {
                addError("No se encontraron datos para generer el certificado de afiliación.");
                generarMensajes();
            }

        } catch (Exception ex) {
            streamedContent2 = null;
            System.out.println("Error generarReporteCertificadoAfiliacion: " + ex.toString() + ex.getMessage());
        }
        procesoFinal();
        return streamedContent2;
    }

    /**
     * @return the habilitarCategoriaIBC
     */
    public boolean isHabilitarCategoriaIBC() {
        return habilitarCategoriaIBC;
    }

    /**
     * @param habilitarCategoriaIBC the habilitarCategoriaIBC to set
     */
    public void setHabilitarCategoriaIBC(boolean habilitarCategoriaIBC) {
        this.habilitarCategoriaIBC = habilitarCategoriaIBC;
    }

    /**
     * @return the listaEstadosAfiliacionListar
     */
    public List<Maestro> getListaEstadosAfiliacionListar() {
        return listaEstadosAfiliacionListar;
    }

    /**
     * @param listaEstadosAfiliacionListar the listaEstadosAfiliacionListar to
     * set
     */
    public void setListaEstadosAfiliacionListar(List<Maestro> listaEstadosAfiliacionListar) {
        this.listaEstadosAfiliacionListar = listaEstadosAfiliacionListar;
    }

    /**
     * @return the consultarResidencia
     */
    public boolean isConsultarResidencia() {
        return consultarResidencia;
    }

    /**
     * @param consultarResidencia the consultarResidencia to set
     */
    public void setConsultarResidencia(boolean consultarResidencia) {
        this.consultarResidencia = consultarResidencia;
    }

    /**
     * @return the hashTipoAfiliado
     */
    public HashMap<Integer, Maestro> getHashTipoAfiliado() {
        return hashTipoAfiliado;
    }

    /**
     * @param hashTipoAfiliado the hashTipoAfiliado to set
     */
    public void setHashTipoAfiliado(HashMap<Integer, Maestro> hashTipoAfiliado) {
        this.hashTipoAfiliado = hashTipoAfiliado;
    }

    /**
     * @return the paramConsultaHistorial
     */
    public ParamConsulta getParamConsultaHistorial() {
        if (paramConsultaHistorial == null) {
            paramConsultaHistorial = new ParamConsulta();
        }
        return paramConsultaHistorial;
    }

    /**
     * @param paramConsultaHistorial the paramConsultaHistorial to set
     */
    public void setParamConsultaHistorial(ParamConsulta paramConsultaHistorial) {
        this.paramConsultaHistorial = paramConsultaHistorial;
    }

    public void verReportes(int _id) {
        getObjeto().setId(_id);
        PrimeFaces.current().ajax().update("frmCrearReporte");
        PrimeFaces.current().executeScript("PF('dialogoCrearReporte').show()");
        //2021-03-18 jyperez este llamado se comenta porque no hace falta. es una función para desplegar pantalla.
        //procesoFinal();
    }

    /**
     * @return the objetoAnterior
     */
    public AsegAfiliado getObjetoAnterior() {
        return objetoAnterior;
    }

    /**
     * @param objetoAnterior the objetoAnterior to set
     */
    public void setObjetoAnterior(AsegAfiliado objetoAnterior) {
        this.objetoAnterior = objetoAnterior;
    }

    /**
     * @return the trasladoExistente
     */
    public boolean isTrasladoExistente() {
        return trasladoExistente;
    }

    /**
     * @param trasladoExistente the trasladoExistente to set
     */
    public void setTrasladoExistente(boolean trasladoExistente) {
        this.trasladoExistente = trasladoExistente;
    }

    public void cargarDato() {

    }

    /**
     * @return the listadoEps
     */
    public List<Maestro> getListadoEps() {
        return listadoEps;
    }

    /**
     * @param listadoEps the listadoEps to set
     */
    public void setListadoEps(List<Maestro> listadoEps) {
        this.listadoEps = listadoEps;
    }

    /**
     * @return the listadoEpsRecursiva
     */
    public HashMap<Integer, Maestro> getListadoEpsRecursiva() {
        return listadoEpsRecursiva;
    }

    /**
     * @param listadoEpsRecursiva the listadoEpsRecursiva to set
     */
    public void setListadoEpsRecursiva(HashMap<Integer, Maestro> listadoEpsRecursiva) {
        this.listadoEpsRecursiva = listadoEpsRecursiva;
    }

    /**
     * @return the hashNivelSisben
     */
    public HashMap<Integer, Maestro> getHashNivelSisben() {
        return hashNivelSisben;
    }

    /**
     * @param hashNivelSisben the hashNivelSisben to set
     */
    public void setHashNivelSisben(HashMap<Integer, Maestro> hashNivelSisben) {
        this.hashNivelSisben = hashNivelSisben;
    }

    /**
     * Función para bloquear y obligar a registrar la sección BDUA cuando el
     * origen de un afiliado sea TRaslado de Otra EPS o Transferido de EPS
     * liquidada
     *
     */
    public void actualizaRegistraBDUA() {
        if (this.objeto.getMaeOrigenAfiliado() != 0
                && (hashOrigenAfiliado.get(this.objeto.getMaeOrigenAfiliado()).getValor().equals(AfiliadoParametro.ORIGEN_AFILIADO_TRASLADO_OTRA_EPS)
                || hashOrigenAfiliado.get(this.objeto.getMaeOrigenAfiliado()).getValor().equals(AfiliadoParametro.ORIGEN_AFILIADO_TRASLADO_EPS_LIQUIDADA))) {
            this.objeto.setRegistroBdua(true);
            this.trasladoEPS = true;
        } else {
            //this.objeto.setRegistroBdua(false);
            this.trasladoEPS = false;
        }
    }
    
    public void verCertificadoContacto(AsegAfiliado obj) {
        objeto = obj;
        this.nombreSolicitante = "";
        this.cargoSolicitante = "";
        this.enteControlSolicitante = "";
        PrimeFaces.current().resetInputs("frmCrearCertificadoContacto");
        PrimeFaces.current().ajax().update("frmCrearCertificadoContacto");
        PrimeFaces.current().executeScript("PF('dialogoCrearCertificadoContacto').show()");
        //2021-03-18 jyperez este llamado se comenta porque no hace falta. es una función para desplegar pantalla.
        //procesoFinal();
    }

    public StreamedContent generarCertificadoContacto() {
        StreamedContent streamedContent2 = null;
        if (!this.nombreSolicitante.equals("") && !this.cargoSolicitante.equals("") && !this.enteControlSolicitante.equals("")) {
            try {
                setAdjuntoStream(null);
                certificadoContactoAfiliado = new CertificadoContactoAfiliado();
                certificadoContactoAfiliado.setIdAfiliado(this.getObjeto().getId());
                listaCertificadoContactoAfiliado = new ArrayList();
                super.setAccion(ACCION_ADICIONAL_8);
                getAfiliadosServicio().Accion(this);

                if (getAdjuntoStream() != null) {
                    InputStream stream = getAdjuntoStream();
                    stream.mark(0); //remember to this position!
                    streamedContent2 = DefaultStreamedContent.builder().contentType("application/pdf").stream(() -> stream).name(certificadoContactoAfiliado.getNombreArchivo()).build();
                    //PrimeFaces.current().executeScript("PF('dialogoCrearCertificadoContacto').hide()");
                } else {
                    addError("No se encontraron datos para generar el certificado de contactos del afiliado.");
                    generarMensajes();
                }

            } catch (Exception ex) {
                streamedContent2 = null;
                System.out.println("Error generarCertificadoContacto: " + ex.toString() + ex.getMessage());
            }
            procesoFinal();
        } else {
            generarMensajes();
        }
        return streamedContent2;
    }

    /**
     * función para validar si se debe deshabilitar o no el campo ingresoBdua,
     * dependiendo de si es trasladoExistente o si se encuentra activo el campo
     * trasladoEPS
     *
     * @return
     */
    public boolean validarTrasladoExistenteOrigenAfiliado() {
        return (this.trasladoExistente || this.trasladoEPS);
    }

    /**
     * @return the trasladoEPS
     */
    public boolean isTrasladoEPS() {
        return trasladoEPS;
    }

    /**
     * @param trasladoEPS the trasladoEPS to set
     */
    public void setTrasladoEPS(boolean trasladoEPS) {
        this.trasladoEPS = trasladoEPS;
    }
    
    public void validarMensajeAfiliadoActivoContributivo() {
        //validamos si el afiliado se encuentra estado activo y es régimen contributivo y lanzamos la ventana
        // la pantalla de este deben llamar a validarMensajeConfirmacion
        if (afiliadoActivoContributivo) {
            PrimeFaces.current().ajax().update("frmAfiliadoActivoContributivo");
            PrimeFaces.current().executeScript("PF('dialogoAfActivoContributivo').show()");
        } else {
            //seguimos con la validación normal de homónimos según sea el caso.
            validarMensajeConfirmacion();
        }
    }

    public void validarMensajeConfirmacion() {
        this.mensajeDatosBasicos = "";
        // validamos si existen homónimos para la configuración de valores del objeto
        //2021-12-17 jyperez validamos si se abrió la pantalla de Activo Contributivo
        if (afiliadoActivoContributivo) {
            PrimeFaces.current().executeScript("PF('dialogoAfActivoContributivo').hide();");
        }
        // 2021-05-11 jyperez se descomenta el código inicial del homónimo fonético
        getAfiliadosServicio().consultarAfiliadosHomonimos(this);
        //revisamos que se haya obtenido respuesta de la consulta de homónimos, dependiendo de eso
        // se muestra el dialogo, si no entonces se realiza la ejecución de la funcionalidad de guardar.
        if (this.registrosHomonimos != null && this.registrosHomonimos.size() > 0) {
            PrimeFaces.current().ajax().update("frmHomonimo");
            PrimeFaces.current().executeScript("PF('dialogoHomonimo').show()");
        /*
        //2021-05-11 jyperez comentamos código INC 757, ya no aplicará con las nuevas modificaciones
        //2021-04-07 jyperez INC 0757 Validamos adicional 5 para ejecutar validación alternativa de homónimos
        if (isAccionAdicional5()) {
            // consultamos registro existente
            getAfiliadosServicio().consultarAfiliadoExistenteDatosBasicos(this);
            // si se encuentra un registro con los datos básicos, se lanza la ventana. sino, se pasa normal.
            if (!this.mensajeDatosBasicos.equals("")) {
                PrimeFaces.current().ajax().update("frmHomonimo");
                PrimeFaces.current().executeScript("PF('dialogoHomonimo').show()");
            } else {
                if (accionCreacion) {
                    guardar();
                } else {
                    modificar();
                }
            }*/
        } else {
            if (accionCreacion) {
                guardar();
            } else {
                modificar();
            }
        }
    }

    public void validarAccion() {
        if (accionCreacion) {
            guardar();
        } else {
            modificar();
        }
    }
    
    /**
     * función para validar si el afiliado es grupo D, lo cual indica que es de contribución solidaria
     * Se implementa para ser usada en el Ver y el Editar
     */
    public void validarContribucionSolidaria() {
        //cargamos los valores del maestro teniendo en cuenta que debemos evaluar mediante código
        this.afiliadoContribucionSolidaria = false;
        this.mensajeAfiliadoContribucionSolidaria = "";
        try {
            Maestro grupo = hashGrupoSisben.get(this.objeto.getMaeGrupoSisbenId());
            if (grupo != null) {
                if (grupo.getValor().equals(AfiliadoParametro.GRUPO_SISBEN_D)) {
                    this.afiliadoContribucionSolidaria = true;
                    this.mensajeAfiliadoContribucionSolidaria = "Afiliado por Contribución Solidaria";
                }
            }
        } catch (Exception e) {
            this.afiliadoContribucionSolidaria = false;
            this.mensajeAfiliadoContribucionSolidaria = "";
        }
    }
    
    /**
     * función para validar si el afiliado se encuentra en grupo poblacional población sisbenizada
     * y tiene configurado en la metodología de grupo poblacional Sisben 3 ó no tiene valor. Esto con el objetivo
     * de informarle que debe realizar la encuentra de sisben versión 4 y debe ser actualizarse a esa metodología.
     * Se implementa para ser usada en el Ver y el Editar
     */
    public void validarAfiliadoSinEncuestaSisbenVersion4() {
        //cargamos los valores del maestro teniendo en cuenta que debemos evaluar mediante código
        this.afiliadoSinEncuestaSisben4 = false;
        this.mensajeAfiliadoSinEncuestaSisben4 = "";
        try {
            Maestro grupoPoblacional = hashGrupoPoblacional.get(this.objeto.getMaeGrupoPoblacional());
            if ( grupoPoblacional != null && grupoPoblacional.getValor().equals(AfiliadoParametro.GRUPO_POBLACIONAL_POBLACION_SISBENIZADA)) {
                    if (this.objeto.getMaeMetodologiaGrupoPoblacionalId() != null) { 
                        Maestro metodologia = hashMetodologiaGrupoPoblacional.get(this.objeto.getMaeMetodologiaGrupoPoblacionalId());
                        if(metodologia != null && metodologia.getValor().equals(AfiliadoParametro.METODOLOGIA_GRUPO_POBLACIONAL_SISBEN_3)) {
                            this.afiliadoSinEncuestaSisben4 = true;
                            this.mensajeAfiliadoSinEncuestaSisben4 = "Usuario sin encuesta Versión 4 del SISBEN";
                        } else if (metodologia == null) {
                            this.afiliadoSinEncuestaSisben4 = true;
                            this.mensajeAfiliadoSinEncuestaSisben4 = "Usuario sin encuesta Versión 4 del SISBEN";
                        }
                } else if (this.objeto.getMaeMetodologiaGrupoPoblacionalId() == null) {
                    this.afiliadoSinEncuestaSisben4 = true;
                    this.mensajeAfiliadoSinEncuestaSisben4 = "Usuario sin encuesta Versión 4 del SISBEN";
                }
            }
        } catch (Exception e) {
            this.afiliadoSinEncuestaSisben4 = false;
            this.mensajeAfiliadoSinEncuestaSisben4 = "";
        }
    }
    
    public void llenarRegistrosMapa(AsegAfiliado afiliado) {
//        genSimbolo01();
        registrosMapa = new DefaultMapModel<>();
        String ruta01 = rutaImagenes + AfiliadoParametro.ICONO_01;
        String ruta02 = rutaImagenes + AfiliadoParametro.ICONO_02;
        //for (MapaAfiliado obj : listaMapaAfiliados) {
        if (afiliado.getDireccionGeorefLatitud() != null && afiliado.getDireccionGeorefLongitud() != null) {
            LatLng coordenadas = new LatLng(afiliado.getDireccionGeorefLatitud().doubleValue(), afiliado.getDireccionGeorefLongitud().doubleValue());
            Marker marcador = new Marker(coordenadas, afiliado.getNombreCompleto(), afiliado.getIdAfiliado(), ruta01);
//            marcador.setIcon(getSymbol());
            registrosMapa.addOverlay(marcador);
            //actualizamos los datos de centrado del mapa
            this.setCentroLatitud(afiliado.getDireccionGeorefLatitud().doubleValue());
            this.setCentroLongitud(afiliado.getDireccionGeorefLongitud().doubleValue());
        }
        //}
        //Circulo
//        LatLng coord = new LatLng(centroLatitud, centroLongitud);
//        Circle<Long> circle1 = new Circle<>(coord, 10000);
//        circle1.setStrokeColor("#d93c3c");
//        circle1.setFillColor("#d93c3c");
//        circle1.setFillOpacity(0.1);
//        circle1.setData(1L);
//        registrosMapa.addOverlay(circle1);
    }

    public void onStateChange(StateChangeEvent event) {
        // Obtén el nuevo estado del mapa
        centroLatitud = event.getCenter().getLat();
        centroLongitud = event.getCenter().getLng();
        profundidad = event.getZoomLevel();
//        // Actualiza los marcadores
//        registrosMapa.addOverlay(new Marker(new LatLng(centroLatitud, centroLongitud), "Centro", 1001));
//        refrescar();
    }

    public List<Marker<Long>> getMarcadores() {
        return registrosMapa.getMarkers();
    }

    public void onMarkerSelect(OverlaySelectEvent<Long> event) {
        Marker<Long> marcador = (Marker) event.getOverlay();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        marcador.getData() + " ", marcador.getTitle()));
    }
    
    public void onPointSelectDireccion(PointSelectEvent event) {
        registrosMapa = new DefaultMapModel<>();
        LatLng marcador = event.getLatLng();
        centroLatitud = marcador.getLat();
        centroLongitud =  marcador.getLng();
        campoLatitud = centroLatitud;
        campoLongitud = centroLongitud;
        this.registrosMapa.addOverlay(new Marker(marcador, this.direccionCompleta));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Punto seleccionado",
                "Lat:" + marcador.getLat() + ", Lng:" + marcador.getLng()));
    }
    
    public void actualizarUbicacion() {
        //PENDIENTE inicializar los puntos donde debe cargar el mapa, calculando una dirección.
        // concatenamos los campos en la dirección del objeto afiliado
        this.setDireccionCompleta("");
        String municipio = "";
        String departamento = "";
        if (this.objeto.getDireccionVia() != null
                && !this.objeto.getDireccionVia().equals("")
                && !this.objeto.getDireccionVia().equals("SD")) {
            setDireccionCompleta(retornarCadena(this.objeto.getDireccionVia())
                    + retornarCadena(this.objeto.getDireccionNro())
                    + retornarCadena(this.objeto.getDireccionOrd1())
                    + retornarCadena(this.objeto.getDireccionOrientacion())
                    + "# "
                    + retornarCadena(this.objeto.getDireccionPlaca1())
                    + retornarCadena(this.objeto.getDireccionOrd2())
                    + retornarCadena(this.objeto.getDireccionOrientacion2())
                    + "- "
                    + retornarCadena(this.objeto.getDireccionPlaca2())
                    + retornarCadena(this.objeto.getDireccionDescripcion()));
        } else {
            setDireccionCompleta(retornarCadena(this.objeto.getDireccionVia())
                    + retornarCadena(this.objeto.getDireccionDescripcion()));
        }
        //validamos para poder contatenar el municipio y el departamento
        if (this.objeto.getResidenciaUbicacion() != null) {
            municipio = this.objeto.getResidenciaUbicacion().getNombre();
            setDireccionCompleta(getDireccionCompleta() + municipio);
            if (this.objeto.getResidenciaUbicacion().getUbicacionPadre() != null) {
                departamento = this.objeto.getResidenciaUbicacion().getUbicacionPadre().getNombre();
                setDireccionCompleta(getDireccionCompleta() + "," + departamento);
            }
            
        }
    }
    
    public void onGeocodeDireccion(GeocodeEvent event) {
        //String ruta01 = rutaImagenes + AfiliadoParametro.ICONO_01;
        List<GeocodeResult> results = event.getResults();
        registrosMapa = new DefaultMapModel<>();
        if (results != null && !results.isEmpty()) {
            LatLng center = results.get(0).getLatLng();
            this.centroLatitud = center.getLat();
            this.centroLongitud = center.getLng();
            
            campoLatitud = centroLatitud;
            campoLongitud = centroLongitud;
            
            //nos quedamos con la primera opción si de pronto se tiene mas de un resultado
            this.registrosMapa.addOverlay(new Marker(results.get(0).getLatLng(), results.get(0).getAddress()));

            /*for (int i = 0; i < results.size(); i++) {
                GeocodeResult result = results.get(i);
                this.registrosMapa.addOverlay(new Marker(result.getLatLng(), result.getAddress()));
                //new Marker(coordenadas, afiliado.getNombreCompleto(), afiliado.getIdAfiliado(), ruta01);
            }*/
        }
    }
    
    public void verMapaGeoreferencia() {
        PrimeFaces.current().ajax().update("frmVerGeoreferenciacion");
        PrimeFaces.current().executeScript("PF('dialogoVerGeoReferenciacion').show()");
    }
    
    /**
     * @return the regitrosHomonimos
     */
    public List<String> getRegistrosHomonimos() {
        return registrosHomonimos;
    }

    /**
     * @param registrosHomonimos the regitrosHomonimos to set
     */
    public void setRegistrosHomonimos(List<String> registrosHomonimos) {
        this.registrosHomonimos = registrosHomonimos;
    }

    /**
     * @return the accionCreacion
     */
    public boolean isAccionCreacion() {
        return accionCreacion;
    }

    /**
     * @param accionCreacion the accionCreacion to set
     */
    public void setAccionCreacion(boolean accionCreacion) {
        this.accionCreacion = accionCreacion;
    }

    /**
     * @return the prestadorAnterior
     */
    public CntPrestadorSede getPrestadorAnterior() {
        return prestadorAnterior;
    }

    /**
     * @param prestadorAnterior the prestadorAnterior to set
     */
    public void setPrestadorAnterior(CntPrestadorSede prestadorAnterior) {
        this.prestadorAnterior = prestadorAnterior;
    }

    /**
     * @return the mensajeDatosBasicos
     */
    public String getMensajeDatosBasicos() {
        return mensajeDatosBasicos;
    }

    /**
     * @param mensajeDatosBasicos the mensajeDatosBasicos to set
     */
    public void setMensajeDatosBasicos(String mensajeDatosBasicos) {
        this.mensajeDatosBasicos = mensajeDatosBasicos;
    }

    /**
     * @return the listaSubGrupoSisben
     */
    public List<Maestro> getListaSubGrupoSisben() {
        return listaSubGrupoSisben;
    }

    /**
     * @param listaSubGrupoSisben the listaSubGrupoSisben to set
     */
    public void setListaSubGrupoSisben(List<Maestro> listaSubGrupoSisben) {
        this.listaSubGrupoSisben = listaSubGrupoSisben;
    }

    /**
     * @return the deshabilitaSubGrupoSisben
     */
    public boolean isDeshabilitaSubGrupoSisben() {
        return deshabilitaSubGrupoSisben;
    }

    /**
     * @param deshabilitaSubGrupoSisben the deshabilitaSubGrupoSisben to set
     */
    public void setDeshabilitaSubGrupoSisben(boolean deshabilitaSubGrupoSisben) {
        this.deshabilitaSubGrupoSisben = deshabilitaSubGrupoSisben;
    }

    /**
     * @return the listaGrupoSisben
     */
    public List<Maestro> getListaGrupoSisben() {
        return listaGrupoSisben;
    }

    /**
     * @param listaGrupoSisben the listaGrupoSisben to set
     */
    public void setListaGrupoSisben(List<Maestro> listaGrupoSisben) {
        this.listaGrupoSisben = listaGrupoSisben;
    }

    /**
     * @return the hashGrupoSisben
     */
    public HashMap<Integer, Maestro> getHashGrupoSisben() {
        return hashGrupoSisben;
    }

    /**
     * @param hashGrupoSisben the hashGrupoSisben to set
     */
    public void setHashGrupoSisben(HashMap<Integer, Maestro> hashGrupoSisben) {
        this.hashGrupoSisben = hashGrupoSisben;
    }

    /**
     * @return the hashSubGrupoSisben
     */
    public HashMap<Integer, Maestro> getHashSubGrupoSisben() {
        return hashSubGrupoSisben;
    }

    /**
     * @param hashSubGrupoSisben the hashSubGrupoSisben to set
     */
    public void setHashSubGrupoSisben(HashMap<Integer, Maestro> hashSubGrupoSisben) {
        this.hashSubGrupoSisben = hashSubGrupoSisben;
    }

    /**
     * @return the certificadoAfiliacion
     */
    public CertificadoAfiliacion getCertificadoAfiliacion() {
        return certificadoAfiliacion;
    }

    /**
     * @param certificadoAfiliacion the certificadoAfiliacion to set
     */
    public void setCertificadoAfiliacion(CertificadoAfiliacion certificadoAfiliacion) {
        this.certificadoAfiliacion = certificadoAfiliacion;
    }

    /**
     * @return the adjuntoStream
     */
    public InputStream getAdjuntoStream() {
        return adjuntoStream;
    }

    /**
     * @param adjuntoStream the adjuntoStream to set
     */
    public void setAdjuntoStream(InputStream adjuntoStream) {
        this.adjuntoStream = adjuntoStream;
    }

    public void salir() {
        this.objeto = null;
        FacesContext context = javax.faces.context.FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.removeAttribute("com.sun.faces.renderkit.ServerSideStateHelper.LogicalViewMap");
        session.removeAttribute("com.sun.faces.application.view.activeViewMaps");
        session.removeAttribute("afiliadosBean");
        FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove("afiliadosBean");
    }
    
    public void desmarcarDuplicado(int id) {
        this.objeto = new AsegAfiliado(id);
        super.setAccion(ACCION_ADICIONAL_7);
        getAfiliadosServicio().Accion(this);
        procesoFinal();
    }
    
    /**
     * @return the listaRegimen
     */
    public List<Maestro> getListaRegimen() {
        return listaRegimen;
    }

    /**
     * @param listaRegimen the listaRegimen to set
     */
    public void setListaRegimen(List<Maestro> listaRegimen) {
        this.listaRegimen = listaRegimen;
    }

    /**
     * @return the hashRegimen
     */
    public HashMap<Integer, Maestro> getHashRegimen() {
        return hashRegimen;
    }

    /**
     * @param hashRegimen the hashRegimen to set
     */
    public void setHashRegimen(HashMap<Integer, Maestro> hashRegimen) {
        this.hashRegimen = hashRegimen;
    }

    /**
     * @return the listaNivelSisbenNuevo
     */
    public List<Maestro> getListaNivelSisbenNuevo() {
        return listaNivelSisbenNuevo;
    }

    /**
     * @param listaNivelSisbenNuevo the listaNivelSisbenNuevo to set
     */
    public void setListaNivelSisbenNuevo(List<Maestro> listaNivelSisbenNuevo) {
        this.listaNivelSisbenNuevo = listaNivelSisbenNuevo;
    }

    /**
     * @return the hashNivelSisbenNuevo
     */
    public HashMap<Integer, Maestro> getHashNivelSisbenNuevo() {
        return hashNivelSisbenNuevo;
    }

    /**
     * @param hashNivelSisbenNuevo the hashNivelSisbenNuevo to set
     */
    public void setHashNivelSisbenNuevo(HashMap<Integer, Maestro> hashNivelSisbenNuevo) {
        this.hashNivelSisbenNuevo = hashNivelSisbenNuevo;
    }

    /**
     * @return the listaContactos
     */
    public List<AsegAfiliadoContacto> getListaContactos() {
        return listaContactos;
    }

    /**
     * @param listaContactos the listaContactos to set
     */
    public void setListaContactos(List<AsegAfiliadoContacto> listaContactos) {
        this.listaContactos = listaContactos;
    }

    /**
     * @return the listaContactosBorrar
     */
    public List<AsegAfiliadoContacto> getListaContactosBorrar() {
        return listaContactosBorrar;
    }

    /**
     * @param listaContactosBorrar the listaContactosBorrar to set
     */
    public void setListaContactosBorrar(List<AsegAfiliadoContacto> listaContactosBorrar) {
        this.listaContactosBorrar = listaContactosBorrar;
    }

    /**
     * @return the contadorIdContacto
     */
    public int getContadorIdContacto() {
        return contadorIdContacto;
    }

    /**
     * @param contadorIdContacto the contadorIdContacto to set
     */
    public void setContadorIdContacto(int contadorIdContacto) {
        this.contadorIdContacto = contadorIdContacto;
    }

    /**
     * @return the contacto
     */
    public AsegAfiliadoContacto getContacto() {
        return contacto;
    }

    /**
     * @param contacto the contacto to set
     */
    public void setContacto(AsegAfiliadoContacto contacto) {
        this.contacto = contacto;
    }

    /**
     * @return the listaTiposContacto
     */
    public List<Maestro> getListaTiposContacto() {
        return listaTiposContacto;
    }

    /**
     * @param listaTiposContacto the listaTiposContacto to set
     */
    public void setListaTiposContacto(List<Maestro> listaTiposContacto) {
        this.listaTiposContacto = listaTiposContacto;
    }

    /**
     * @return the hashTiposContacto
     */
    public HashMap<Integer, Maestro> getHashTiposContacto() {
        return hashTiposContacto;
    }

    /**
     * @param hashTiposContacto the hashTiposContacto to set
     */
    public void setHashTiposContacto(HashMap<Integer, Maestro> hashTiposContacto) {
        this.hashTiposContacto = hashTiposContacto;
    }

    /**
     * @return the requeridoSubGrupoSisben
     */
    public boolean isRequeridoSubGrupoSisben() {
        return requeridoSubGrupoSisben;
    }

    /**
     * @param requeridoSubGrupoSisben the requeridoSubGrupoSisben to set
     */
    public void setRequeridoSubGrupoSisben(boolean requeridoSubGrupoSisben) {
        this.requeridoSubGrupoSisben = requeridoSubGrupoSisben;
    }

    /**
     * @return the listaCategoriaIbc
     */
    public List<Maestro> getListaCategoriaIbc() {
        return listaCategoriaIbc;
    }

    /**
     * @param listaCategoriaIbc the listaCategoriaIbc to set
     */
    public void setListaCategoriaIbc(List<Maestro> listaCategoriaIbc) {
        this.listaCategoriaIbc = listaCategoriaIbc;
    }

    /**
     * @return the hashCategoriaIbc
     */
    public HashMap<Integer, Maestro> getHashCategoriaIbc() {
        return hashCategoriaIbc;
    }

    /**
     * @param hashCategoriaIbc the hashCategoriaIbc to set
     */
    public void setHashCategoriaIbc(HashMap<Integer, Maestro> hashCategoriaIbc) {
        this.hashCategoriaIbc = hashCategoriaIbc;
    }

    /**
     * @return the listaModeloLiquidacion
     */
    public List<Maestro> getListaModeloLiquidacion() {
        return listaModeloLiquidacion;
    }

    /**
     * @param listaModeloLiquidacion the listaModeloLiquidacion to set
     */
    public void setListaModeloLiquidacion(List<Maestro> listaModeloLiquidacion) {
        this.listaModeloLiquidacion = listaModeloLiquidacion;
    }

    /**
     * @return the hashValorModeloLiquidacion
     */
    public HashMap<String, Maestro> getHashValorModeloLiquidacion() {
        return hashValorModeloLiquidacion;
    }

    /**
     * @param hashValorModeloLiquidacion the hashValorModeloLiquidacion to set
     */
    public void setHashValorModeloLiquidacion(HashMap<String, Maestro> hashValorModeloLiquidacion) {
        this.hashValorModeloLiquidacion = hashValorModeloLiquidacion;
    }

    /**
     * @return the hashModeloLiquidacion
     */
    public HashMap<Integer, Maestro> getHashModeloLiquidacion() {
        return hashModeloLiquidacion;
    }

    /**
     * @param hashModeloLiquidacion the hashModeloLiquidacion to set
     */
    public void setHashModeloLiquidacion(HashMap<Integer, Maestro> hashModeloLiquidacion) {
        this.hashModeloLiquidacion = hashModeloLiquidacion;
    }

    /**
     * @return the listaEstadoCivil
     */
    public List<Maestro> getListaEstadoCivil() {
        return listaEstadoCivil;
    }

    /**
     * @param listaEstadoCivil the listaEstadoCivil to set
     */
    public void setListaEstadoCivil(List<Maestro> listaEstadoCivil) {
        this.listaEstadoCivil = listaEstadoCivil;
    }

    /**
     * @return the hashEstadoCivil
     */
    public HashMap<Integer, Maestro> getHashEstadoCivil() {
        return hashEstadoCivil;
    }

    /**
     * @param hashEstadoCivil the hashEstadoCivil to set
     */
    public void setHashEstadoCivil(HashMap<Integer, Maestro> hashEstadoCivil) {
        this.hashEstadoCivil = hashEstadoCivil;
    }

    /**
     * @return the listaTipoAfiliado
     */
    public List<Maestro> getListaTipoAfiliado() {
        return listaTipoAfiliado;
    }

    /**
     * @param listaTipoAfiliado the listaTipoAfiliado to set
     */
    public void setListaTipoAfiliado(List<Maestro> listaTipoAfiliado) {
        this.listaTipoAfiliado = listaTipoAfiliado;
    }

    /**
     * @return the hashTipoAfiliadoCompleto
     */
    public HashMap<Integer, Maestro> getHashTipoAfiliadoCompleto() {
        return hashTipoAfiliadoCompleto;
    }

    /**
     * @param hashTipoAfiliadoCompleto the hashTipoAfiliadoCompleto to set
     */
    public void setHashTipoAfiliadoCompleto(HashMap<Integer, Maestro> hashTipoAfiliadoCompleto) {
        this.hashTipoAfiliadoCompleto = hashTipoAfiliadoCompleto;
    }

    /**
     * @return the listaTipoAfiliadoCompleto
     */
    public List<Maestro> getListaTipoAfiliadoCompleto() {
        return listaTipoAfiliadoCompleto;
    }

    /**
     * @param listaTipoAfiliadoCompleto the listaTipoAfiliadoCompleto to set
     */
    public void setListaTipoAfiliadoCompleto(List<Maestro> listaTipoAfiliadoCompleto) {
        this.listaTipoAfiliadoCompleto = listaTipoAfiliadoCompleto;
    }

    /**
     * @return the listaParentescoCotizante
     */
    public List<Maestro> getListaParentescoCotizante() {
        return listaParentescoCotizante;
    }

    /**
     * @param listaParentescoCotizante the listaParentescoCotizante to set
     */
    public void setListaParentescoCotizante(List<Maestro> listaParentescoCotizante) {
        this.listaParentescoCotizante = listaParentescoCotizante;
    }

    /**
     * @return the hashParentescoCotizante
     */
    public HashMap<Integer, Maestro> getHashParentescoCotizante() {
        return hashParentescoCotizante;
    }

    /**
     * @param hashParentescoCotizante the hashParentescoCotizante to set
     */
    public void setHashParentescoCotizante(HashMap<Integer, Maestro> hashParentescoCotizante) {
        this.hashParentescoCotizante = hashParentescoCotizante;
    }

    /**
     * @return the listaZona
     */
    public List<Maestro> getListaZona() {
        return listaZona;
    }

    /**
     * @param listaZona the listaZona to set
     */
    public void setListaZona(List<Maestro> listaZona) {
        this.listaZona = listaZona;
    }

    /**
     * @return the hashZona
     */
    public HashMap<Integer, Maestro> getHashZona() {
        return hashZona;
    }

    /**
     * @param hashZona the hashZona to set
     */
    public void setHashZona(HashMap<Integer, Maestro> hashZona) {
        this.hashZona = hashZona;
    }

    /**
     * @return the listaEtnia
     */
    public List<Maestro> getListaEtnia() {
        return listaEtnia;
    }

    /**
     * @param listaEtnia the listaEtnia to set
     */
    public void setListaEtnia(List<Maestro> listaEtnia) {
        this.listaEtnia = listaEtnia;
    }

    /**
     * @return the hashEtnia
     */
    public HashMap<Integer, Maestro> getHashEtnia() {
        return hashEtnia;
    }

    /**
     * @param hashEtnia the hashEtnia to set
     */
    public void setHashEtnia(HashMap<Integer, Maestro> hashEtnia) {
        this.hashEtnia = hashEtnia;
    }

    /**
     * @return the listaTipoDiscapacidad
     */
    public List<Maestro> getListaTipoDiscapacidad() {
        return listaTipoDiscapacidad;
    }

    /**
     * @param listaTipoDiscapacidad the listaTipoDiscapacidad to set
     */
    public void setListaTipoDiscapacidad(List<Maestro> listaTipoDiscapacidad) {
        this.listaTipoDiscapacidad = listaTipoDiscapacidad;
    }

    /**
     * @return the hashTipoDiscapacidad
     */
    public HashMap<Integer, Maestro> getHashTipoDiscapacidad() {
        return hashTipoDiscapacidad;
    }

    /**
     * @param hashTipoDiscapacidad the hashTipoDiscapacidad to set
     */
    public void setHashTipoDiscapacidad(HashMap<Integer, Maestro> hashTipoDiscapacidad) {
        this.hashTipoDiscapacidad = hashTipoDiscapacidad;
    }

    /**
     * @return the listaCondicionDiscapacidad
     */
    public List<Maestro> getListaCondicionDiscapacidad() {
        return listaCondicionDiscapacidad;
    }

    /**
     * @param listaCondicionDiscapacidad the listaCondicionDiscapacidad to set
     */
    public void setListaCondicionDiscapacidad(List<Maestro> listaCondicionDiscapacidad) {
        this.listaCondicionDiscapacidad = listaCondicionDiscapacidad;
    }

    /**
     * @return the hashCondicionDiscapacidad
     */
    public HashMap<Integer, Maestro> getHashCondicionDiscapacidad() {
        return hashCondicionDiscapacidad;
    }

    /**
     * @param hashCondicionDiscapacidad the hashCondicionDiscapacidad to set
     */
    public void setHashCondicionDiscapacidad(HashMap<Integer, Maestro> hashCondicionDiscapacidad) {
        this.hashCondicionDiscapacidad = hashCondicionDiscapacidad;
    }

    /**
     * @return the listaPaises
     */
    public List<Ubicacion> getListaPaises() {
        return listaPaises;
    }

    /**
     * @param listaPaises the listaPaises to set
     */
    public void setListaPaises(List<Ubicacion> listaPaises) {
        this.listaPaises = listaPaises;
    }

    /**
     * @return the listaComunidadEtnica
     */
    public List<Maestro> getListaComunidadEtnica() {
        return listaComunidadEtnica;
    }

    /**
     * @param listaComunidadEtnica the listaComunidadEtnica to set
     */
    public void setListaComunidadEtnica(List<Maestro> listaComunidadEtnica) {
        this.listaComunidadEtnica = listaComunidadEtnica;
    }

    /**
     * @return the hashComunidadEtnica
     */
    public HashMap<Integer, Maestro> getHashComunidadEtnica() {
        return hashComunidadEtnica;
    }

    /**
     * @param hashComunidadEtnica the hashComunidadEtnica to set
     */
    public void setHashComunidadEtnica(HashMap<Integer, Maestro> hashComunidadEtnica) {
        this.hashComunidadEtnica = hashComunidadEtnica;
    }

    /**
     * @return the hashEstadosAfiliacionCompleto
     */
    public HashMap<Integer, Maestro> getHashEstadosAfiliacionCompleto() {
        return hashEstadosAfiliacionCompleto;
    }

    /**
     * @param hashEstadosAfiliacionCompleto the hashEstadosAfiliacionCompleto to set
     */
    public void setHashEstadosAfiliacionCompleto(HashMap<Integer, Maestro> hashEstadosAfiliacionCompleto) {
        this.hashEstadosAfiliacionCompleto = hashEstadosAfiliacionCompleto;
    }

    /**
     * @return the listaMetodologiaGrupoPoblacional
     */
    public List<Maestro> getListaMetodologiaGrupoPoblacional() {
        return listaMetodologiaGrupoPoblacional;
    }

    /**
     * @param listaMetodologiaGrupoPoblacional the listaMetodologiaGrupoPoblacional to set
     */
    public void setListaMetodologiaGrupoPoblacional(List<Maestro> listaMetodologiaGrupoPoblacional) {
        this.listaMetodologiaGrupoPoblacional = listaMetodologiaGrupoPoblacional;
    }

    /**
     * @return the hashMetodologiaGrupoPoblacional
     */
    public HashMap<Integer, Maestro> getHashMetodologiaGrupoPoblacional() {
        return hashMetodologiaGrupoPoblacional;
    }

    /**
     * @param hashMetodologiaGrupoPoblacional the hashMetodologiaGrupoPoblacional to set
     */
    public void setHashMetodologiaGrupoPoblacional(HashMap<Integer, Maestro> hashMetodologiaGrupoPoblacional) {
        this.hashMetodologiaGrupoPoblacional = hashMetodologiaGrupoPoblacional;
    }

    /**
     * @return the hashValorNivelSisbenNuevo
     */
    public HashMap<String, Maestro> getHashValorNivelSisbenNuevo() {
        return hashValorNivelSisbenNuevo;
    }

    /**
     * @param hashValorNivelSisbenNuevo the hashValorNivelSisbenNuevo to set
     */
    public void setHashValorNivelSisbenNuevo(HashMap<String, Maestro> hashValorNivelSisbenNuevo) {
        this.hashValorNivelSisbenNuevo = hashValorNivelSisbenNuevo;
    }

    /**
     * @return the afiliadoContribucionSolidaria
     */
    public boolean isAfiliadoContribucionSolidaria() {
        return afiliadoContribucionSolidaria;
    }

    /**
     * @param afiliadoContribucionSolidaria the afiliadoContribucionSolidaria to set
     */
    public void setAfiliadoContribucionSolidaria(boolean afiliadoContribucionSolidaria) {
        this.afiliadoContribucionSolidaria = afiliadoContribucionSolidaria;
    }

    /**
     * @return the mensajeAfiliadoContribucionSolidaria
     */
    public String getMensajeAfiliadoContribucionSolidaria() {
        return mensajeAfiliadoContribucionSolidaria;
    }

    /**
     * @param mensajeAfiliadoContribucionSolidaria the mensajeAfiliadoContribucionSolidaria to set
     */
    public void setMensajeAfiliadoContribucionSolidaria(String mensajeAfiliadoContribucionSolidaria) {
        this.mensajeAfiliadoContribucionSolidaria = mensajeAfiliadoContribucionSolidaria;
    }

    /**
     * @return the hashPorcentajeDistribucion
     */
    public HashMap<Integer, Maestro> getHashPorcentajeDistribucion() {
        return hashPorcentajeDistribucion;
    }

    /**
     * @param hashPorcentajeDistribucion the hashPorcentajeDistribucion to set
     */
    public void setHashPorcentajeDistribucion(HashMap<Integer, Maestro> hashPorcentajeDistribucion) {
        this.hashPorcentajeDistribucion = hashPorcentajeDistribucion;
    }

    /**
     * @return the listaPorcentajeDistribucion
     */
    public List<Maestro> getListaPorcentajeDistribucion() {
        return listaPorcentajeDistribucion;
    }

    /**
     * @param listaPorcentajeDistribucion the listaPorcentajeDistribucion to set
     */
    public void setListaPorcentajeDistribucion(List<Maestro> listaPorcentajeDistribucion) {
        this.listaPorcentajeDistribucion = listaPorcentajeDistribucion;
    }

    /**
     * @return the afiliadoSinEncuestaSisben4
     */
    public boolean isAfiliadoSinEncuestaSisben4() {
        return afiliadoSinEncuestaSisben4;
    }

    /**
     * @param afiliadoSinEncuestaSisben4 the afiliadoSinEncuestaSisben4 to set
     */
    public void setAfiliadoSinEncuestaSisben4(boolean afiliadoSinEncuestaSisben4) {
        this.afiliadoSinEncuestaSisben4 = afiliadoSinEncuestaSisben4;
    }

    /**
     * @return the mensajeAfiliadoSinEncuestaSisben4
     */
    public String getMensajeAfiliadoSinEncuestaSisben4() {
        return mensajeAfiliadoSinEncuestaSisben4;
    }

    /**
     * @param mensajeAfiliadoSinEncuestaSisben4 the mensajeAfiliadoSinEncuestaSisben4 to set
     */
    public void setMensajeAfiliadoSinEncuestaSisben4(String mensajeAfiliadoSinEncuestaSisben4) {
        this.mensajeAfiliadoSinEncuestaSisben4 = mensajeAfiliadoSinEncuestaSisben4;
    }

    /**
     * @return the afiliadoPortabilidadVigente
     */
    public boolean isAfiliadoPortabilidadVigente() {
        return afiliadoPortabilidadVigente;
    }

    /**
     * @param afiliadoPortabilidadVigente the afiliadoPortabilidadVigente to set
     */
    public void setAfiliadoPortabilidadVigente(boolean afiliadoPortabilidadVigente) {
        this.afiliadoPortabilidadVigente = afiliadoPortabilidadVigente;
    }

    /**
     * @return the portabilidadVigente
     */
    public AsegPortabilidad getPortabilidadVigente() {
        return portabilidadVigente;
    }

    /**
     * @param portabilidadVigente the portabilidadVigente to set
     */
    public void setPortabilidadVigente(AsegPortabilidad portabilidadVigente) {
        this.portabilidadVigente = portabilidadVigente;
    }

    /**
     * @return the hashPaises
     */
    public HashMap<Integer,Ubicacion> getHashPaises() {
        return hashPaises;
    }

    /**
     * @param hashPaises the hashPaises to set
     */
    public void setHashPaises(HashMap<Integer,Ubicacion> hashPaises) {
        this.hashPaises = hashPaises;
    }

    /**
     * @return the listaNivelSisbenListar
     */
    public List<Maestro> getListaNivelSisbenListar() {
        return listaNivelSisbenListar;
    }

    /**
     * @param listaNivelSisbenListar the listaNivelSisbenListar to set
     */
    public void setListaNivelSisbenListar(List<Maestro> listaNivelSisbenListar) {
        this.listaNivelSisbenListar = listaNivelSisbenListar;
    }

    /**
     * @return the listaTiposGeneroIdentificacion
     */
    public List<Maestro> getListaTiposGeneroIdentificacion() {
        return listaTiposGeneroIdentificacion;
    }

    /**
     * @param listaTiposGeneroIdentificacion the listaTiposGeneroIdentificacion to set
     */
    public void setListaTiposGeneroIdentificacion(List<Maestro> listaTiposGeneroIdentificacion) {
        this.listaTiposGeneroIdentificacion = listaTiposGeneroIdentificacion;
    }

    /**
     * @return the hashTiposGeneroIdentificacion
     */
    public HashMap<Integer, Maestro> getHashTiposGeneroIdentificacion() {
        return hashTiposGeneroIdentificacion;
    }

    /**
     * @param hashTiposGeneroIdentificacion the hashTiposGeneroIdentificacion to set
     */
    public void setHashTiposGeneroIdentificacion(HashMap<Integer, Maestro> hashTiposGeneroIdentificacion) {
        this.hashTiposGeneroIdentificacion = hashTiposGeneroIdentificacion;
    }

    /**
     * @return the listaLugarNacimiento
     */
    public List<Ubicacion> getListaLugarNacimiento() {
        return listaLugarNacimiento;
    }

    /**
     * @param listaLugarNacimiento the listaLugarNacimiento to set
     */
    public void setListaLugarNacimiento(List<Ubicacion> listaLugarNacimiento) {
        this.listaLugarNacimiento = listaLugarNacimiento;
    }

    /**
     * @return the hashLugarNacimiento
     */
    public HashMap<Integer,Ubicacion> getHashLugarNacimiento() {
        return hashLugarNacimiento;
    }

    /**
     * @param hashLugarNacimiento the hashLugarNacimiento to set
     */
    public void setHashLugarNacimiento(HashMap<Integer,Ubicacion> hashLugarNacimiento) {
        this.hashLugarNacimiento = hashLugarNacimiento;
    }

//    /**
//     * @return the aseguramientoSingle
//     */
//    public AseguramientoSingle getAseguramientoSingle() {
//        return aseguramientoSingle;
//    }
//
//    /**
//     * @param aseguramientoSingle the aseguramientoSingle to set
//     */
//    public void setAseguramientoSingle(AseguramientoSingle aseguramientoSingle) {
//        this.aseguramientoSingle = aseguramientoSingle;
//    }

    /**
     * @return the hashCausaNovedadValor
     */
    public HashMap<String, Maestro> getHashCausaNovedadValor() {
        return hashCausaNovedadValor;
    }

    /**
     * @param hashCausaNovedadValor the hashCausaNovedadValor to set
     */
    public void setHashCausaNovedadValor(HashMap<String, Maestro> hashCausaNovedadValor) {
        this.hashCausaNovedadValor = hashCausaNovedadValor;
    }

    /**
     * @return the bloqueoDuplicado
     */
    public boolean isBloqueoDuplicado() {
        return bloqueoDuplicado;
    }

    /**
     * @param bloqueoDuplicado the bloqueoDuplicado to set
     */
    public void setBloqueoDuplicado(boolean bloqueoDuplicado) {
        this.bloqueoDuplicado = bloqueoDuplicado;
    }

    /**
     * @return the certificadoContactoAfiliado
     */
    public CertificadoContactoAfiliado getCertificadoContactoAfiliado() {
        return certificadoContactoAfiliado;
    }

    /**
     * @param certificadoContactoAfiliado the certificadoContactoAfiliado to set
     */
    public void setCertificadoContactoAfiliado(CertificadoContactoAfiliado certificadoContactoAfiliado) {
        this.certificadoContactoAfiliado = certificadoContactoAfiliado;
    }

    /**
     * @return the listaCertificadoContactoAfiliado
     */
    public List<CertificadoContactoAfiliado> getListaCertificadoContactoAfiliado() {
        return listaCertificadoContactoAfiliado;
    }

    /**
     * @param listaCertificadoContactoAfiliado the listaCertificadoContactoAfiliado to set
     */
    public void setListaCertificadoContactoAfiliado(List<CertificadoContactoAfiliado> listaCertificadoContactoAfiliado) {
        this.listaCertificadoContactoAfiliado = listaCertificadoContactoAfiliado;
    }

    /**
     * @return the nombreSolicitante
     */
    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    /**
     * @param nombreSolicitante the nombreSolicitante to set
     */
    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }

    /**
     * @return the cargoSolicitante
     */
    public String getCargoSolicitante() {
        return cargoSolicitante;
    }

    /**
     * @param cargoSolicitante the cargoSolicitante to set
     */
    public void setCargoSolicitante(String cargoSolicitante) {
        this.cargoSolicitante = cargoSolicitante;
    }

    /**
     * @return the enteControlSolicitante
     */
    public String getEnteControlSolicitante() {
        return enteControlSolicitante;
    }

    /**
     * @param enteControlSolicitante the enteControlSolicitante to set
     */
    public void setEnteControlSolicitante(String enteControlSolicitante) {
        this.enteControlSolicitante = enteControlSolicitante;
    }

    /**
     * @return the formularioAfiliadoRes1823
     */
    public FormularioAfiliadoRes1823 getFormularioAfiliadoRes1823() {
        return formularioAfiliadoRes1823;
    }

    /**
     * @param formularioAfiliadoRes1823 the formularioAfiliadoRes1823 to set
     */
    public void setFormularioAfiliadoRes1823(FormularioAfiliadoRes1823 formularioAfiliadoRes1823) {
        this.formularioAfiliadoRes1823 = formularioAfiliadoRes1823;
    }

    /**
     * @return the listaUbicacionBarrios
     */
    public List<GnUbicacionBarrio> getListaUbicacionBarrios() {
        return listaUbicacionBarrios;
    }

    /**
     * @param listaUbicacionBarrios the listaUbicacionBarrios to set
     */
    public void setListaUbicacionBarrios(List<GnUbicacionBarrio> listaUbicacionBarrios) {
        this.listaUbicacionBarrios = listaUbicacionBarrios;
    }

    /**
     * @return the habilitaListaBarrio
     */
    public boolean isHabilitaListaBarrio() {
        return habilitaListaBarrio;
    }

    /**
     * @param habilitaListaBarrio the habilitaListaBarrio to set
     */
    public void setHabilitaListaBarrio(boolean habilitaListaBarrio) {
        this.habilitaListaBarrio = habilitaListaBarrio;
    }

    /**
     * @return the ciudadMedellin
     */
    public Ubicacion getCiudadMedellin() {
        return ciudadMedellin;
    }

    /**
     * @param ciudadMedellin the ciudadMedellin to set
     */
    public void setCiudadMedellin(Ubicacion ciudadMedellin) {
        this.ciudadMedellin = ciudadMedellin;
    }

    /**
     * @return the hashUbicacionBarrios
     */
    public HashMap<Integer,GnUbicacionBarrio> getHashUbicacionBarrios() {
        return hashUbicacionBarrios;
    }

    /**
     * @param hashUbicacionBarrios the hashUbicacionBarrios to set
     */
    public void setHashUbicacionBarrios(HashMap<Integer,GnUbicacionBarrio> hashUbicacionBarrios) {
        this.hashUbicacionBarrios = hashUbicacionBarrios;
    }

    /**
     * @return the listaProgramaAfiliado
     */
    public List<PeAfiliadosPrograma> getListaProgramaAfiliado() {
        return listaProgramaAfiliado;
    }

    /**
     * @param listaProgramaAfiliado the listaProgramaAfiliado to set
     */
    public void setListaProgramaAfiliado(List<PeAfiliadosPrograma> listaProgramaAfiliado) {
        this.listaProgramaAfiliado = listaProgramaAfiliado;
    }

    /**
     * @return the registrosMapa
     */
    public MapModel<Long> getRegistrosMapa() {
        return registrosMapa;
    }

    /**
     * @param registrosMapa the registrosMapa to set
     */
    public void setRegistrosMapa(MapModel<Long> registrosMapa) {
        this.registrosMapa = registrosMapa;
    }

    /**
     * @return the centroLatitud
     */
    public double getCentroLatitud() {
        return centroLatitud;
    }

    /**
     * @param centroLatitud the centroLatitud to set
     */
    public void setCentroLatitud(double centroLatitud) {
        this.centroLatitud = centroLatitud;
    }

    /**
     * @return the centroLongitud
     */
    public double getCentroLongitud() {
        return centroLongitud;
    }

    /**
     * @param centroLongitud the centroLongitud to set
     */
    public void setCentroLongitud(double centroLongitud) {
        this.centroLongitud = centroLongitud;
    }

    /**
     * @return the profundidad
     */
    public int getProfundidad() {
        return profundidad;
    }

    /**
     * @param profundidad the profundidad to set
     */
    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    /**
     * @return the rutaGoogleMapsEmpresarial
     */
    public String getRutaGoogleMapsEmpresarial() {
        return rutaGoogleMapsEmpresarial;
    }

    /**
     * @param rutaGoogleMapsEmpresarial the rutaGoogleMapsEmpresarial to set
     */
    public void setRutaGoogleMapsEmpresarial(String rutaGoogleMapsEmpresarial) {
        this.rutaGoogleMapsEmpresarial = rutaGoogleMapsEmpresarial;
    }
    
    /**
     * @return the direccionCompleta
     */
    public String getDireccionCompleta() {
        return direccionCompleta;
    }

    /**
     * @param direccionCompleta the direccionCompleta to set
     */
    public void setDireccionCompleta(String direccionCompleta) {
        this.direccionCompleta = direccionCompleta;
    }

    /**
     * @return the campoLatitud
     */
    public double getCampoLatitud() {
        return campoLatitud;
    }

    /**
     * @param campoLatitud the campoLatitud to set
     */
    public void setCampoLatitud(double campoLatitud) {
        this.campoLatitud = campoLatitud;
    }

    /**
     * @return the campoLongitud
     */
    public double getCampoLongitud() {
        return campoLongitud;
    }

    /**
     * @param campoLongitud the campoLongitud to set
     */
    public void setCampoLongitud(double campoLongitud) {
        this.campoLongitud = campoLongitud;
    }

    /**
     * @return the tipoDireccion
     */
    public int getTipoDireccion() {
        return tipoDireccion;
    }

    /**
     * @param tipoDireccion the tipoDireccion to set
     */
    public void setTipoDireccion(int tipoDireccion) {
        this.tipoDireccion = tipoDireccion;
    }

    /**
     * @return the rutaImagenes
     */
    public String getRutaImagenes() {
        return rutaImagenes;
    }

    /**
     * @param rutaImagenes the rutaImagenes to set
     */
    public void setRutaImagenes(String rutaImagenes) {
        this.rutaImagenes = rutaImagenes;
    }
    

}
