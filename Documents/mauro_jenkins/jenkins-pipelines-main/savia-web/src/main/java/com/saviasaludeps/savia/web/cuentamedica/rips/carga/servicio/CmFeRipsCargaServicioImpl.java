package com.saviasaludeps.savia.web.cuentamedica.rips.carga.servicio;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.administracion.MaestroTipo;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoSede;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupo;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupoUsuario;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmFactura;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCarga;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCargaAdjunto;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmFeRipsCargaContenido;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCarga;
import com.saviasaludeps.savia.dominio.facturacionelectronica.CmFeNota;
import com.saviasaludeps.savia.dominio.facturacionelectronica.CmFeRipsFactura;
import com.saviasaludeps.savia.dominio.facturacionelectronica.FeDocumento;
import com.saviasaludeps.savia.dominio.generico.ParamConsulta;
import com.saviasaludeps.savia.negocio.administracion.CalendarioRemoto;
import com.saviasaludeps.savia.negocio.administracion.MaestroRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntContratoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmRipsCargaRemoto;
import com.saviasaludeps.savia.negocio.contratacion.CntPrestadorRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmGrupoRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.auditoria.CmGrupoUsuarioRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.conciliacion.CmFacturaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmFeRipsCargaAdjuntoRemoto;
import com.saviasaludeps.savia.web.cuentamedica.rips.carga.bean.CmFeRipsCargaBean;
import com.saviasaludeps.savia.web.singleton.UbicacionSingle;
import com.saviasaludeps.savia.web.utilidades.AccionesBO;
import com.saviasaludeps.savia.web.utilidades.RemotoEJB;
import com.saviasaludeps.savia.web.utilidades.Url;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmFeRipsCargaRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmFeSoporteRemoto;
import com.saviasaludeps.savia.negocio.cuentamedica.rips.CmFeTransaccionRemoto;
import com.saviasaludeps.savia.negocio.facturacionelectronica.CmFeNotasRemoto;
import com.saviasaludeps.savia.negocio.facturacionelectronica.FeDocumentoRemoto;
import com.saviasaludeps.savia.web.cuentamedica.utilidades.CmFeAdjuntoErrores;
import com.saviasaludeps.savia.web.cuentamedica.utilidades.CmUtilidades;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.xml.sax.InputSource;

public class CmFeRipsCargaServicioImpl extends AccionesBO implements CmFeRipsCargaServicioIface {
    
    public static final String NIT_SAVIA_SALUD  = "900604350";
    public static final String FECHA_INICIO_FACTURACION_ELECTRONICA  = "2024-10-01";
    public static final String TAG_NUMERO_DOCUMENTO_XML  = "cbc:ParentDocumentID";
    public static final String TAG_FACTURA_NUMERO_NO_NOTAS_XML  = "cbc:ParentDocumentID";
    public static final String TAG_NUMERO_NOTAS_XML  = "cbc:ParentDocumentID";
    public static final String TAG_FACTURA_NUMERO_NOTAS_XML  = "ReferenceID";
    public static final String TAG_VALOR_FACTURA_LEGAL_XML  = "PayableAmount";
    public static final String TAG_VALOR_NOTA_LEGAL_XML  = "PayableAmount";
    public static final String TAG_VALOR_COPAGO_XML  = "COPAGO";
    public static final String TAG_CODIGO_COPAGO_XML  = "01";
    public static final String TAG_NUMERO_CONTRATO_XML  = "NUMERO_CONTRATO";
    public static final String TAG_MODALIDAD_XML  = "MODALIDAD_PAGO";
    public static final String TAG_COBERTURA_XML  = "COBERTURA_PLAN_BENEFICIOS";
    public static final String TAG_CODIGO_MODERADORA_XML  = "02";
    public static final String TAG_CUFE_XML  = "UUID";
    public static final String TAG_PADRE  = "cbc:Description";    
    public static final String TAG_FECHA_EXPEDICION_XML  = "cbc:IssueDate";
    public static final String TAG_DOCUMENTO_PERTINENCIA_XML  = "cac:ReceiverParty - cac:PartyTaxScheme - cbc:CompanyID";
    public static final String TAG_NIT_PRESTADOR_XML  = "cac:SenderParty - cac:PartyTaxScheme - cbc:CompanyID";
    public static final String TAG_HORA_EXPEDICION_XML  = "cbc:IssueTime";
    public static final String LINEA_CUV_TXT  = "(CUV):";
    public static final String LINEA_FECHA_MINISTERIO_TXT  = "Fecha:";
    public static final String LINEA_HORA_MINISTERIO_TXT  = "Hora:";
    public static final String ATRIBUTO_FECHA_MINISTERIO_JSON = "fechaRadicacion";
    public static final String ATRIBUTO_CUV_JSON  = "codigoUnicoValidacion";
    public static final int DIAS_HABILES_ESPERA_FACTURA_ELECTRONICA  = 22;
    public static final int ID_PRESTADOR_SAVIA = 1;
    public static final String CODIGO_SCHEME_ID_NO_ENCONTRADO  = "99";
    public static final int ID_MODALIDAD_CONTRATO_PGP = 9119;
    public static final int ID_MODALIDAD_CONTRATO_EVENTO = 9118;
    public static final int COBERTURA_PBS_CONBINADA = -1;
    
    private static final Map<String, String> codigosModalidadMap = new HashMap<>();
    private static final Map<String, Boolean> codigosCoberturaMap = new HashMap<>();
    private HashMap<Integer,Maestro> hashMapModalidadContratos = new HashMap<>();
    private HashMap<String, String> facturasVerificadas = new HashMap<>();

    static {
        codigosModalidadMap.put("03", "01"); //capita
        codigosModalidadMap.put("02", "03"); //pgp
        codigosModalidadMap.put("01", "04"); // paquetes
        codigosModalidadMap.put("04", "02"); //evento
        codigosModalidadMap.put("05", "05"); //covid
        
        codigosCoberturaMap.put("01", false);//pbs
        codigosCoberturaMap.put("02", true);//no pbs
    }


    private CmRipsCargaRemoto getCmRipsCargaRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("CmRipsCargaServicio", CmRipsCargaRemoto.class.getName());
        return (CmRipsCargaRemoto) object;
    }
    
    private CmFeRipsCargaAdjuntoRemoto getCmFeRipsCargaAdjuntoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("CmFeRipsCargaAdjuntoServicio", CmFeRipsCargaAdjuntoRemoto.class.getName());
        return (CmFeRipsCargaAdjuntoRemoto) object;
    }
    
    private CmFeRipsCargaRemoto getCmFeRipsCargaServicioRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("CmFeRipsCargaServicio", CmFeRipsCargaRemoto.class.getName());
        return (CmFeRipsCargaRemoto) object;
    }

    private CntPrestadorRemoto getPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

    private MaestroRemoto getMaestroRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("MaestroServicio", MaestroRemoto.class.getName());
        return (MaestroRemoto) object;
    }

    private CntPrestadorRemoto getCntPrestadorRemoto() throws Exception {
        return (CntPrestadorRemoto) RemotoEJB.getEJBRemoto("CntPrestadorServicio", CntPrestadorRemoto.class.getName());
    }

     private CntContratoRemoto getContratoRemoto() throws Exception {
        return (CntContratoRemoto) RemotoEJB.getEJBRemoto(("CntContratoServicio"), CntContratoRemoto.class.getName());
    }
     
    private CmFacturaRemoto getCmFacturaRemoto() throws Exception {
        return (CmFacturaRemoto) RemotoEJB.getEJBRemoto("CmFacturaServicio", CmFacturaRemoto.class.getName());
    }
    
     private FeDocumentoRemoto getFeDocumentoServicioRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("FeDocumentoServicio", FeDocumentoRemoto.class.getName());
        return (FeDocumentoRemoto) object;
    }
     
    private CalendarioRemoto getCalendarioRemoto() throws Exception {
        return (CalendarioRemoto) RemotoEJB.getEJBRemoto("CalendarioServicio", CalendarioRemoto.class.getName());
    }
    
    private CmFeNotasRemoto getCmFeNotasRemoto() throws Exception {
        return (CmFeNotasRemoto) RemotoEJB.getEJBRemoto("CmFeNotasServicio", CmFeNotasRemoto.class.getName());
    }
    
     private CmFeTransaccionRemoto getCmFeTransaccionRemoto() throws Exception {
        return (CmFeTransaccionRemoto) RemotoEJB.getEJBRemoto("CmFeTransaccionServicio", CmFeTransaccionRemoto.class.getName());
    }
     
    private CmFeSoporteRemoto getCmFeSoporteRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("CmFeSoporteServicio", CmFeSoporteRemoto.class.getName());
        return (CmFeSoporteRemoto) object;
    }
    
    private CmGrupoRemoto getCmGrupoRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("CmGrupoServicio", CmGrupoRemoto.class.getName());
        return (CmGrupoRemoto) object;
    }
    
    private CmGrupoUsuarioRemoto getCmGrupoUsuarioRemoto() throws Exception {
        Object object = RemotoEJB.getEJBRemoto("CmGrupoUsuarioServicio", CmGrupoUsuarioRemoto.class.getName());
        return (CmGrupoUsuarioRemoto) object;
    }


    public static Map<String, String> getCodigosModalidadMap() {
        return codigosModalidadMap;
    }

    public static Map<String, Boolean> getCodigosCoberturaMap() {
        return codigosCoberturaMap;
    }  
    
    public HashMap<Integer, Maestro> getHashMapModalidadContratos() {
        return hashMapModalidadContratos;
    }

    public void setHashMapModalidadContratos(HashMap<Integer, Maestro> hashMapModalidadContratos) {
        this.hashMapModalidadContratos = hashMapModalidadContratos;
    }

    public HashMap<String, String> getFacturasVerificadas() {
        return facturasVerificadas;
    }
  
    @Override
    public void Accion(CmFeRipsCargaBean bean) {
        if (super.ValidarSesion(bean)) {
            switch (bean.getAccion()) {
                case Url.ACCION_LISTAR:
                    listar(bean);
                    break;
                case Url.ACCION_VER:
                    switch (bean.getDoAccion()) {
                        case CmFeRipsCargaBean.DO_ACCION_VER_CARGA:
                            ver(bean);
                            break;
                        case CmFeRipsCargaBean.DO_ACCION_VER_ADJUNTOS:
                            verAdjuntos(bean);
                            break;
                        case CmFeRipsCargaBean.DO_ACCION_VER_CM_FE_TRANSACCIONES:
                            verCmFeTransacciones(bean);
                            break;
                        case CmFeRipsCargaBean.DO_ACCION_VER_CARGA_PERIODO_ASOCIADA:
                            bean.setMansajeGeneral(validarExistenciaFacturaPeriodoAsociada(bean.getObjeto()));
                            break;
                        case CmFeRipsCargaBean.DO_ACCION_VER_SOPORTES:
                            verSoportes(bean);
                            break;
                        case CmFeRipsCargaBean.DO_ACCION_VER_RADICADORES:
                            verRadicadores(bean);
                            break;
                    }
                    break;
                case Url.ACCION_CREAR:
                    crear(bean);
                    break;
                case Url.ACCION_GUARDAR:
                    switch (bean.getDoAccion()) {
                        case CmFeRipsCargaBean.DO_ACCION_GUARDAR_INTENSIDAD_INDIVIDUAL:
                            guardar(bean);
                            break;
                    }
                    break;
                case Url.ACCION_EDITAR:
                    editar(bean);
                    break;
                case Url.ACCION_MODIFICAR:
                    modificar(bean);
                    break;
                case Url.ACCION_BORRAR:
                    break;
                case Url.ACCION_ADICIONAL_1:
                    switch (bean.getDoAccion()) {
                        case CmFeRipsCargaBean.DO_ACCION_VER_GESTIONAR:
                            verGestionar(bean);
                            break;
                        case CmFeRipsCargaBean.DO_ACCION_GUARDAR_ENVIAR:
                            guardarEnviar(bean);
                            break;  
                        case CmFeRipsCargaBean.DO_ACCION_GUARDAR_REQUISITOS_MANUALES:
                            guardarRequisitosManuales(bean);
                            break;
                        case CmFeRipsCargaBean.DO_ACCION_GUARDAR_ESTADO_RECUPERACION_CARGA:
                            guardarEstadoRecuperacionCarga(bean);
                            break;  
                         case CmFeRipsCargaBean.DO_ACCION_ACTUALIZAR_ATRIBUTO_FE_DOCUMENTO:
                            actualizarAtributoFeDocumento(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_2:
                    switch (bean.getDoAccion()) {
                        case CmFeRipsCargaBean.DO_ACCION_VER_CM_FE_TRANSACCIONES:
                            verCmFeTransacciones(bean);
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_3:
                    switch (bean.getDoAccion()) {
                        case Url.ACCION_CREAR:
                            crear(bean);
                            break;
                        case Url.ACCION_GUARDAR:
                             guardarFacturasMasivas(bean);   
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_4:
                     switch (bean.getDoAccion()) {
                        case Url.ACCION_GUARDAR:
                             guardarReintentoPorValidacionCUV(bean);   
                            break;
                    }
                    break;
                case Url.ACCION_ADICIONAL_5:
                    switch (bean.getDoAccion()) {
                        case CmFeRipsCargaBean.DO_ACCION_GUARDAR_ASIGNACION_RADICADOR:
                            guardarAsignacionRadicador(bean); 
                            break;
                        case CmFeRipsCargaBean.DO_ACCION_LISTAR_CARGAS_ASIGNAR:
                            listarCargasParaAsignarRadicador(bean);
                            break;
                    }
                    break;
                default:
                    break;
            }
        } else {
            System.err.println("Sesion inactiva");
        }
    }

    @Override
    public void cargaInicial(CmFeRipsCargaBean bean) {
        try {
            bean.setListaTiposDocumento(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO));
            bean.setListaTiposDocumentoEmpresa(getMaestroRemoto().consultarPorTipo(MaestroTipo.GN_TIPO_DOCUMENTO_EMPRESA));
            bean.setListaUbicacion(UbicacionSingle.getInstance().getListaMunicipios());
            bean.setHashRegimenes(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_REGIMEN));
            setHashMapModalidadContratos(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.CNT_MODALIDAD));
            bean.setHashRegionales(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.GN_REGION));
            bean.setHashUbicacion(UbicacionSingle.getInstance().getHashMunicipios());
            bean.setHashUbicacionDepartamento( UbicacionSingle.getInstance().getHashDepartamentos());
            bean.setListaMaeMotivoDevolucion(consultarMotivosDevolucionManual(MaestroTipo.CM_DEVOLUCION_MOTIVO));
            bean.setListaPeriodoCarga(CmUtilidades.listarCapitaPeriodo());
            bean.setListaMeses(CmUtilidades.listarMeses());
            bean.setHashTipoSoporte(getMaestroRemoto().consultarHashPorTipo(MaestroTipo.RIPS_TIPO_SOPORTE));
            
        } catch (Exception ex) {
            bean.addError(obtenerErrorStrFormateado(ex));
        }
    }

    @Override
    public void listar(CmFeRipsCargaBean bean) {
        try {
            // no es un usuario savia busco las de su nit
            if (ID_PRESTADOR_SAVIA != bean.getConexion().getEmpresa().getId()) {
                int idPrestador = bean.getConexion().getEmpresa().getCntPrestador().getId();
                if (idPrestador > 0) {
                    bean.getParamConsulta().setParametroConsulta3(idPrestador);
                } else {
                    bean.addError("Error su usuario no tiene asociado un prestador");
                }
            } else { // usuario savia no es lider o auxiliar radicacion busco las que tengo asignado
                bean.getParamConsulta().setParametroConsulta4(bean.getConexion().getUsuario().getId());
                if (bean.isAccionAdicional5() || bean.isAccionAdicional6()) {
                    bean.getParamConsulta().setParametroConsulta4(null);
                }
            }
                                   
            if (bean.isError() == false) {
                bean.getParamConsulta().setCantidadRegistros(getCmFeRipsCargaServicioRemoto().consultarCantidadLista(bean.getParamConsulta()));
                bean.setRegistros(getCmFeRipsCargaServicioRemoto().consultarLista(bean.getParamConsulta()));
            }
        } catch (Exception ex) {
            bean.addError("Error: " + obtenerErrorStrFormateado(ex));
        }
    }
    
    private void listarCargasParaAsignarRadicador(CmFeRipsCargaBean bean) {
        try {
            // no es un usuario savia busco las de su nit
            if (ID_PRESTADOR_SAVIA != bean.getConexion().getEmpresa().getId()) {
                int idPrestador = bean.getConexion().getEmpresa().getCntPrestador().getId();
                if (idPrestador > 0) {
                    bean.getParamConsultaCargasAsignar().setParametroConsulta3(idPrestador);
                } else {
                    bean.addError("Error su usuario no tiene asociado un prestador");
                }
            } else { // usuario savia no es lider radicacion busco las que tengo asignado
                if (!bean.isAccionAdicional5()) {
                    bean.getParamConsultaCargasAsignar().setParametroConsulta4(bean.getConexion().getUsuario().getId());
                }
            }

            if (bean.isError() == false) {

                if (bean.getParamConsultaCargasAsignar().getFiltros() == null) {
                    bean.getParamConsultaCargasAsignar().setFiltros(new HashMap());
                }

                bean.getParamConsultaCargasAsignar().getFiltros().put("idModalidadContratoAsignar", ID_MODALIDAD_CONTRATO_EVENTO+","+ID_MODALIDAD_CONTRATO_PGP);
                bean.getParamConsultaCargasAsignar().getFiltros().put("estadoCargaAsignar", CmFeRipsCarga.ESTADO_VALIDACION_PROCESO);
                bean.getParamConsultaCargasAsignar().getFiltros().put("tipo", CmFeRipsCarga.TIPO_CARGA_FACTURA);

                bean.getParamConsultaCargasAsignar().setCantidadRegistros(getCmFeRipsCargaServicioRemoto().consultarCantidadLista(bean.getParamConsultaCargasAsignar()));
                bean.setRegistrosCargasAsignar(getCmFeRipsCargaServicioRemoto().consultarLista(bean.getParamConsultaCargasAsignar()));
            }
        } catch (Exception ex) {
            bean.addError("Error: " + obtenerErrorStrFormateado(ex));
        }
    }

    @Override
    public void cargar(CmFeRipsCargaBean bean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ver(CmFeRipsCargaBean bean) {
        try {
            CmFeRipsCarga objeto = getCmFeRipsCargaServicioRemoto().consultar(bean.getObjeto().getId());
            bean.setObjeto(objeto);
            asignarCntContratoCompleto(bean);
        } catch (Exception ex) {
            bean.addError("Error :" +obtenerErrorStrFormateado(ex));
        }
    }

    @Override
    public void verListaErrores(CmFeRipsCargaBean bean) {
        try {
            CmFeRipsCarga objeto = new CmFeRipsCarga();
            getCmRipsCargaRemoto().consultarErroresEstructura(bean.getObjeto().getId());
            bean.setObjeto(objeto);
        } catch (Exception ex) {
            bean.addError("Error :" + obtenerErrorStrFormateado(ex));
        }
    }
    private void verCmFeTransacciones(CmFeRipsCargaBean bean) {
        try {
                bean.getParamConsultaCmCfeTransacciones().setCantidadRegistros(getCmFeTransaccionRemoto().consultarCantidadLista(bean.getParamConsultaCmCfeTransacciones()));
                bean.setListaCmFeTransacciones(getCmFeTransaccionRemoto().consultarLista(bean.getParamConsultaCmCfeTransacciones()));
        } catch (Exception ex) {
            bean.addError("Error :" + obtenerErrorStrFormateado(ex));
        }
    }

    @Override
    public void verGestionar(CmFeRipsCargaBean bean) {
        verFeRipsCarga(bean);
        //se asigna por defecto valor de true para estas documentos en caso de ser vacios
        if (bean.getObjeto().getId() != null) {
            if (bean.getObjeto().getDe4402ProfesionalIndependiente() == null) {
                bean.getObjeto().setDe4402ProfesionalIndependiente(true);
            }
            if (bean.getObjeto().getDe4401ProfesionalRed() == null) {
                bean.getObjeto().setDe4401ProfesionalRed(true);
            }
        }
    }
    
    private void verFeRipsCarga(CmFeRipsCargaBean bean){
     try {
            CmFeRipsCarga carga = getCmFeRipsCargaServicioRemoto().consultar(bean.getObjeto().getId());
            bean.setObjeto(carga);
        } catch (Exception ex) {
            bean.addError(obtenerErrorStrFormateado(ex));
        }
    }
    
    private FeDocumento obtenerFacuraEnFeDocumento(CmFeRipsCargaBean bean){
         FeDocumento feDocumento = new FeDocumento();
     try {
         
         ParamConsulta paramConsulta = new ParamConsulta();
         paramConsulta.setParametroConsulta1(  bean.getObjeto().getDocumentoPrestador());
         paramConsulta.setParametroConsulta2(  bean.getObjeto().getDocumentoNumero());
         paramConsulta.setParametroConsulta3( CmUtilidades.obtenerTipoFeDocumento(bean.getObjeto().getTipo()) );
         return getFeDocumentoServicioRemoto().consultarFacturaPorPrestador(paramConsulta);

        } catch (Exception ex) {
            bean.addError(obtenerErrorStrFormateado(ex));
        }
     return feDocumento;
    }
    
    private void guardarEnviar(CmFeRipsCargaBean bean) {
        try {
     
            CmFeRipsCarga carga = bean.getObjeto();

            CmFeRipsCarga cargaVerificar = getCmFeRipsCargaServicioRemoto().consultar(carga.getId());
            if (!cargaVerificar.getEsValidacionProceso()) {
                bean.addError(String.format("La carga está en estado %s, el cual no está permitido para esta acción.", 
                      cargaVerificar.getEstadoStr()));
                return;
            }

            bean.auditoriaModificar(carga);
            if (validarDiasHabilesEsperaFacturaElectronica(bean)) {
                carga.setDe5601Soportes(bean.getObjeto().esDe5601Soportes());
                carga.setDe5601SoporteFe(bean.getObjeto().esDe5601SoporteFe());
                carga.setDe4402ProfesionalIndependiente(bean.getObjeto().esDe4402ProfesionalIndependiente());
                carga.setDe4401ProfesionalRed(bean.getObjeto().esDe4401ProfesionalRed());
                bean.getObjeto().setEstado(CmFeRipsCarga.ESTADO_EN_COLA_AUDITORIA);

                if (procesarCausalesDevolucion(carga, bean)) {
                    carga.setEstado(CmFeRipsCarga.ESTADO_DEVUELTO);
                }
                getCmFeRipsCargaServicioRemoto().actualizarAtributosDevolucion(carga);
                getCmFeRipsCargaServicioRemoto().actualizarEstado(carga);
            }
        } catch (Exception ex) {
            bean.addError(obtenerErrorStrFormateado(ex));
        }
    }
    
    private void guardarReintentoPorValidacionCUV(CmFeRipsCargaBean bean) {
        try {
            CmFeRipsCarga carga = bean.getObjeto();
            carga.setRechazo(false);
            carga.setObservacionRechazo(null);
            carga.setFechaHoraRechazo(null);
            getCmFeRipsCargaServicioRemoto().actualizarRechazo(carga);
            carga.setEstado(CmFeRipsCarga.ESTADO_EN_COLA);            
            getCmFeRipsCargaServicioRemoto().actualizarEstado(carga);
        } catch (Exception ex) {
            bean.addError(obtenerErrorStrFormateado(ex));
        }
    }

    private boolean procesarCausalesDevolucion(CmFeRipsCarga carga, CmFeRipsCargaBean bean) throws Exception {
       
        if (carga.esCausalDevolucionProcesoManual()) {
            
            String codigoMotivo = "";
            String observacionMotivo = "";
            
            if (!carga.getDe4401ProfesionalRed()) {
                codigoMotivo = "DE4401";
                observacionMotivo = "Devolucion IPS Que No Hace Parte De La Red  De Prestadores.";
            }
            
            if (!carga.getDe4402ProfesionalIndependiente()) {
                codigoMotivo = "DE4402";
                observacionMotivo = "Devolución Profesional Independiente No Hace Parte De La Red";
            }
            
            if (!carga.getDe5601Soportes()) {
                codigoMotivo = "DE5601";
                observacionMotivo = "Devolución Faltan Soportes";
            }
            
            if (!carga.getDe5601SoporteFe()) {
                codigoMotivo = "DE5601";
                observacionMotivo = "Devolución la factura no esta en sistema de facturación electrónica y no se presento en ("+DIAS_HABILES_ESPERA_FACTURA_ELECTRONICA+") días hábiles";
            }
            
            for (Maestro motivo : bean.getListaMaeMotivoDevolucion()) {
                if (motivo.getValor().equals(codigoMotivo)) {
                    bean.getObjeto().setMaeDevolucionCodigo(motivo.getValor());
                    bean.getObjeto().setMaeDevolucionId(motivo.getId());
                    bean.getObjeto().setMaeDevolucionValor(motivo.getNombre());
                    bean.getObjeto().setFechaHoraDevolucion(new Date());
                    bean.getObjeto().setDevolucion(true);
                    bean.getObjeto().setObservacionDevolucion(observacionMotivo);
                }
            }
            
            getCmFeRipsCargaServicioRemoto().actualizarDescripcionDevolucion( bean.getObjeto() );
            return true;
        }
        return false;
    }
    
      
    private void guardarRequisitosManuales(CmFeRipsCargaBean bean) {
        try {  
          bean.auditoriaModificar(bean.getObjeto());
          getCmFeRipsCargaServicioRemoto().actualizarRequisitosManuales(bean.getObjeto());
        } catch (Exception ex) {
            bean.addError(obtenerErrorStrFormateado(ex));
        }
    }
    
    private void guardarEstadoRecuperacionCarga(CmFeRipsCargaBean bean) {
        try {
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(new Date());
            calendario.add(Calendar.HOUR_OF_DAY, -24);
            bean.getObjeto().setFechaHoraInicio(calendario.getTime());
            getCmFeRipsCargaServicioRemoto().actualizarFechaInicio(bean.getObjeto());
        } catch (Exception ex) {
            bean.addError(obtenerErrorStrFormateado(ex));
        }
    }

    private void guardarAsignacionRadicador(CmFeRipsCargaBean bean) {
        try {
            
            int categoriaActual  =  bean.getObjeto().getCmGrupoRadicacionRelacionado().getCategoria();

            if (CmFeRipsCargaServicioImpl.COBERTURA_PBS_CONBINADA != categoriaActual ) {
                actualizarCargasSegunRadicador(bean);
                return;
            } 
            
            boolean coberturaBuscada = CmGrupo.CATEGORIA_PBS == 
                    bean.getRadicador().getCmGrupo().getCategoria() ?
                    CmFeRipsCarga.COBERTURA_PBS : CmFeRipsCarga.COBERTURA_NO_PBS;

            List<CmFeRipsCarga> inconsistentes = bean.getListaCargasSeleccionadaAsignar().stream()
                    .filter(Objects::nonNull)
                    .filter(registro -> {
                        Boolean cobertura = registro.getCobertura();
                        return cobertura == null || cobertura != coberturaBuscada;
                    })
                    .collect(Collectors.toList());

            if (!inconsistentes.isEmpty()) {
                String mesnaje = String.format("Hay %s carga que pertenece a un grupo diferente "
                        + " a %s donde esta asociado el radicador seleccionado."
                        + " ej: carga ( %s ) perteneciente a un grupo diferente.", inconsistentes.size(),
                        bean.getRadicador().getCmGrupo().getCategoriaStr(), inconsistentes.get(0).getId());
                bean.addError(mesnaje);
                return;
            }

            actualizarCargasSegunRadicador(bean);
          

        } catch (Exception ex) {
            bean.addError("Error guardarAsignacionRadicador: " + obtenerErrorStrFormateado(ex));
        }
    }

    private void actualizarCargasSegunRadicador(CmFeRipsCargaBean bean) throws Exception {
        try {
            String idsConcatenados = bean.getListaCargasSeleccionadaAsignar().stream()
                    .map(registro -> registro.getId().toString())
                    .collect(Collectors.joining(", "));

            getCmFeRipsCargaServicioRemoto().actualizarRadicadorAsignado(idsConcatenados,
                    bean.getRadicador().getGnUsuario().getId());
        } catch (Exception ex) {
            throw new Exception("Error actualizarCargasSegunRadicador: " + obtenerErrorStrFormateado(ex));
        }
    }

    @Override
    public void enviarAuditoria(CmFeRipsCargaBean bean) {
        try {
            //VALIDAR ESTADO ANTES DE ENVIAR A AUDITORIA
            CmRipsCarga carga = getCmRipsCargaRemoto().consultar(bean.getObjeto().getId());
            if (carga.getEstado() != CmRipsCarga.ESTADO_VALIDADO) {
                bean.addError("Ha ocurrido un error la carga ya no se encuentra en estado VALIDADO");
            }
        } catch (Exception ex) {
            bean.addError(obtenerErrorStrFormateado(ex));
        }
    }

    private void crear(CmFeRipsCargaBean bean) {
        try {
            bean.setObjetoPrestador(new CntPrestador());
            // NO (VER TODAS LAS IPS o GESTIONAR) 
            if (ID_PRESTADOR_SAVIA !=  bean.getConexion().getEmpresa().getId()) {
                //CARGAR IPS
                if (bean.getConexion().getEmpresa().getCntPrestador() != null
                        && bean.getConexion().getEmpresa().getCntPrestador().getId() != null) {
                    bean.setObjetoPrestador(getPrestadorRemoto()
                            .consultar(
                                    bean.getConexion().getEmpresa().getCntPrestador().getId())
                    );
                }
                if (bean.getConexion().getEmpresa().getCntPrestador() == null) {
                    bean.addError("Error no se encontraron sedes o prestador para la empresa : "
                            + bean.getConexion().getEmpresa().getRazonSocial());
                }
            }
            if (bean.getErrores().isEmpty()) {    
                bean.setListaAdjuntos(new ArrayList<>());
                bean.setListaCmFeRipsCarga(new HashMap<>());
                bean.setListaErroresProcesamiento(new ArrayList<>());
                bean.setObjetoPrestadorSede(new CntPrestadorSede());
                bean.setObjeto(new CmFeRipsCarga());
                bean.setCntContratoSede(new CntContratoSede());
                bean.setObjetoPrestador(new CntPrestador());
                bean.setObjetoPrestadorSede(new CntPrestadorSede());
                bean.setNitPrestador("");
            }
        } catch (Exception ex) {
            bean.addError("Error: " + obtenerErrorStrFormateado(ex));
        }
    }

    private void guardar(CmFeRipsCargaBean bean) {
        try {

            CmFeRipsCarga cmFeCarga = bean.getObjeto();
            cmFeCarga.setCmFeRipsCargaAdjuntos(bean.getListaAdjuntos());

            asignarValoresObjetoCarga(bean);

            boolean esCargaValida = validarCargaInsertar(bean);
            
            List<CmFeAdjuntoErrores> erroresTotalesArchivoZip = new ArrayList<>();

            if (!bean.isError() && esCargaValida) {
                
              
               String llave = cmFeCarga.getDocumentoPrestador();
                
                procesarAnexo(cmFeCarga, "XML", this::asignarValoresDesdeArchivoXML, llave, erroresTotalesArchivoZip);
                procesarAnexoJSONCuv(cmFeCarga, "JSON", this::asignarValoresDesdeArchivoJSON, llave, erroresTotalesArchivoZip);

                agregarErroresSiPresente(validarCargaProcesar(cmFeCarga), llave, erroresTotalesArchivoZip);
                agregarErroresSiPresente(asignarValoresDesdeDB(cmFeCarga), llave, erroresTotalesArchivoZip);
                
                if (!erroresTotalesArchivoZip.isEmpty()) {
                    for (CmFeAdjuntoErrores cmFeAdjuntoErrores : erroresTotalesArchivoZip) {
                        manejarErrorIndividual(bean, cmFeAdjuntoErrores.getDescripcion());
                    }
                    return;
                }

                List<CmFeAdjuntoErrores> erroresInsertarCarga = insertarDatosCarga(cmFeCarga, cmFeCarga.getFacturaNumero());
                if (!erroresInsertarCarga.isEmpty()) {
                    for (CmFeAdjuntoErrores cmFeAdjuntoErrores : erroresInsertarCarga) {
                        manejarErrorIndividual(bean, cmFeAdjuntoErrores.getDescripcion());
                    }
                } else {
                    bean.addMensaje("Cargue iniciado por favor consulte de nuevo en un momento para validar el estado");
                }

            }
        } catch (Exception ex) {
            manejarErrorIndividual(bean, obtenerErrorStrFormateado(ex));
        } finally {
            getFacturasVerificadas().clear();
        }
    }

    private void guardarFacturasMasivas(CmFeRipsCargaBean bean) {

        List<CmFeAdjuntoErrores> erroresTotalesArchivoZip = new ArrayList<>();
        
        try {

            Map<String, CmFeRipsCarga> listaCargas = bean.getListaCmFeRipsCarga();
            
            listaCargas.forEach((llave, cmFeCarga) -> {

                procesarAnexo(cmFeCarga, "XML", this::asignarValoresDesdeArchivoXML, llave, erroresTotalesArchivoZip);
                procesarAnexoJSONCuv(cmFeCarga, "JSON", this::asignarValoresDesdeArchivoJSON, llave, erroresTotalesArchivoZip);

                agregarErroresSiPresente(validarCargaProcesar(cmFeCarga), llave, erroresTotalesArchivoZip);
                agregarErroresSiPresente(asignarValoresDesdeDB(cmFeCarga), llave, erroresTotalesArchivoZip);
            });

            if (!erroresTotalesArchivoZip.isEmpty()) {
                manejarMensajeErrorMasivo(bean, erroresTotalesArchivoZip);
                return;
            }
            
            List<CmFeAdjuntoErrores> erroresInsertarCarga = new ArrayList<>();
            listaCargas = bean.getListaCmFeRipsCarga();
            listaCargas.forEach((llave, cmFeCarga) -> {
                List<CmFeAdjuntoErrores> errores = insertarDatosCarga(cmFeCarga, llave);
                erroresInsertarCarga.addAll(errores);
            });

            if (!erroresInsertarCarga.isEmpty()) {
                manejarMensajeErrorMasivo(bean, erroresInsertarCarga);
            } else {
                bean.addMensaje("proceso de carga exitoso");
            }

        } catch (Exception ex) {
            erroresTotalesArchivoZip.add(new CmFeAdjuntoErrores("Error General", obtenerErrorStrFormateado(ex)));
            manejarMensajeErrorMasivo(bean, erroresTotalesArchivoZip);
        } finally {
            getFacturasVerificadas().clear();
        }
    }
    
    public String insertarSoportesNotas( CmFeRipsCarga carga) {
        try {
            if (carga.getEsTipoCargaNota()) {
                int idfactura = insertarFeCargaFactura(carga);
                insertarFeCargaNota(carga, idfactura);
            }
            return "";
        } catch (Exception ex) {
            return String.format("Error en insertarSoportesNotas carga (FeCargaFactura-FeCargaNota) : %s el carga : %s  ", obtenerErrorStrFormateado(ex), carga.getFacturaNumero());
        }
    }

    private int insertarFeCargaFactura(CmFeRipsCarga carga) throws Exception {
        int id = 0;
        try {
            if (carga.getEsTipoCargaNota()) {
                CmFeRipsFactura factura = new CmFeRipsFactura();
                factura.setCmFactura(new CmFactura(carga.getIdCmFacturaAsociadaNota()));
                factura.setCmFeRipsCarga(new CmFeRipsCarga(carga.getId()));
                factura.setUsuarioCrea(carga.getUsuarioCrea());
                factura.setTerminalCrea(carga.getTerminalCrea());
                factura.setFechaHoraCrea(carga.getFechaHoraCrea());
                id = getCmFeRipsCargaServicioRemoto().insertarFeRipsCmFacturas(factura);
                factura.setId(id);
            }
        } catch (Exception ex) {
            throw new Exception(" insertarFeCargaFactura: " + obtenerErrorStrFormateado(ex));
        }
        return id;
    }
    
    private int insertarFeCargaNota(CmFeRipsCarga carga, int idFeCargaFactura) throws Exception {
        int id = 0;
        try{
            
            int tipo = 0;
            switch (carga.getTipo()) {
                case CmFeRipsCarga.TIPO_CARGA_NOTA_CREDITO_CAPITA:
                case CmFeRipsCarga.TIPO_CARGA_NOTA_CREDITO_ACUERDO_VOLUNTAD:
                case CmFeRipsCarga.TIPO_CARGA_NOTA_CREDITO_TOTAL:
                case CmFeRipsCarga.TIPO_CARGA_NOTA_CREDITO_PARCIAL:
                    tipo = CmFeNota.TIPO_NOTA_CREDITO;
                    break;
                case CmFeRipsCarga.TIPO_CARGA_NOTA_DEBITO:
                    tipo = CmFeNota.TIPO_NOTA_DEBIDO;
                    break;
                case CmFeRipsCarga.TIPO_CARGA_NOTA_AJUSTE:
                    tipo = CmFeNota.TIPO_AJUSTE;
                    break;

            }
          
            if(idFeCargaFactura>0 && carga.getEsTipoCargaNota()){
                CmFeNota nota = new CmFeNota();
                nota.setCmFeRipsFacturasId(idFeCargaFactura);
                nota.setCmFacturasId(carga.getIdCmFacturaAsociadaNota());
                nota.setTipo(tipo);
                nota.setNumeroNota(carga.getNumeroNota());
                nota.setValorNota(carga.getValorNota());
                nota.setCude(carga.getCude());
                nota.setFechaHoraEmision(carga.getFechaHoraEmision());
                nota.setUsuarioCrea(carga.getUsuarioCrea());
                nota.setTerminalCrea(carga.getTerminalCrea());
                nota.setFechaHoraCrea(carga.getFechaHoraCrea());
                id = getCmFeNotasRemoto().insertar(nota);
                nota.setId(id);
            }
            
        } catch (Exception ex) {
            throw new Exception(" insertarFeCargaNota: " + obtenerErrorStrFormateado(ex));
        }
        return id;
    }
    
    private String insertarCmFeCarga(String nombreArchivo, CmFeRipsCarga carga) {
        try {
            carga.setId(getCmFeRipsCargaServicioRemoto().insertar(carga));
            return "";
        } catch (Exception ex) {
            return String.format("Error insertarCmFeCarga : %s el item : %s  ", obtenerErrorStrFormateado(ex), nombreArchivo);
        }
    }

    private String actualizarEstadoCargaExitoso(CmFeRipsCarga carga) {
        try {
            carga.setEstado(CmFeRipsCarga.ESTADO_EN_COLA);
            if (carga.getEsTipoCargaNota()) {
                carga.setEstado(CmFeRipsCarga.ESTADO_REGISTRADA);
            }
            getCmFeRipsCargaServicioRemoto().actualizarEstado(carga);
            return "";
        } catch (Exception ex) {
            return String.format("Error actualizarEstadoCargaExitoso al insertar  : %s el item : %s  ", obtenerErrorStrFormateado(ex), carga.getFacturaNumero());
        }
    }
    
    private String actualizarCapitaPeriodo(CmFeRipsCarga carga) {
        try {
            if (!carga.esTipoCapitaPeriodo() && !carga.esTipoCapitaFinal() && carga.getId() == null) {
                return "";
            }

            if (carga.getCargaPeriodoAnterior() != null && carga.getCargaPeriodoAnterior().getId() != null) {
                getCmFeRipsCargaServicioRemoto().actualizarCargaPeriodo(carga.getCargaPeriodoAnterior().getId(), carga.getId());
            }

            return "";
        } catch (Exception ex) {
            return String.format("Error actualizarCapitaPeriodo al insertar  : %s el item : %s  ", obtenerErrorStrFormateado(ex), carga.getFacturaNumero());
        }
    }

    private String actualizarIdAsignacionCmGrupo(CmFeRipsCarga carga) {
        try {
            if (CmFeRipsCarga.TIPO_CARGA_FACTURA != carga.getTipo()) {
                return "";
            }
            
            if(carga.getCmGrupoRadicacionRelacionado() == null ||carga.getCmGrupoRadicacionRelacionado().getId() == null){
                return "";
            }
            
            getCmGrupoRemoto().actualizarIdAsignacion(carga.getCmGrupoRadicacionRelacionado());
            return "";
        } catch (Exception ex) {
             return "";
        }
    }
    
    private String actualizarEstadoCargaPorFallo(CmFeRipsCarga carga) {
        try {

            if (carga.getId() == null) {
                return "Error no se ha registrado la carga en el sistema";
            }            
            carga.setEstado(CmFeRipsCarga.ESTADO_ERROR_CARGA);
            getCmFeRipsCargaServicioRemoto().actualizarEstado(carga);
            
            return "";
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Error fatal FACTURACION_ELECTRONICA WEB al procesar la excepción : actualizarEstadoCargaPorFallo" , ex);
            return String.format("Error actualizar Estado Por falla : %s el item : %s  ", obtenerErrorStrFormateado(ex), carga.getFacturaNumero());
             
        }
    }
    
    private String actualizarObservacionCargaPorFallo(CmFeRipsCarga carga,  List<CmFeAdjuntoErrores> erroresInsertarDatosCarga) {
        try {

            if (carga.getId() == null) {
                return "Error no se ha registrado la observacion de la carga en el sistema";
            }
            
            carga.setObservacion(erroresInsertarDatosCarga.get(0).getDescripcion());
            getCmFeRipsCargaServicioRemoto().actualizarObservacion(carga);

            return "";
        } catch (Exception ex) {
            return String.format("Error actualizar Estado Por falla : %s el item : %s  ", obtenerErrorStrFormateado(ex), carga.getFacturaNumero());
        }
    }


    public Optional<CmFeAdjuntoErrores> asignarValoresDesdeDB(CmFeRipsCarga carga) {

        CmFeAdjuntoErrores adjuntoError = new CmFeAdjuntoErrores();

        try {
            List<String> errores = Arrays.asList(asignarDatosFacturaParaNota(carga), 
                    asignarCargaPeriodoAnteriorParaCapita(carga));

            for (String error : errores) {
                if (!error.isEmpty()) {
                    adjuntoError.setDescripcion(error);
                    return Optional.of(adjuntoError);
                }
            }
        } catch (Exception ex) {
            adjuntoError.setDescripcion("Error al procesar asignarValoresDesdeDB: " + obtenerErrorStrFormateado(ex)+ ", archivo: " + carga.getFacturaNumero());
            return Optional.of(adjuntoError);
        }

        return Optional.empty();
    }
    
    private String asignarDatosFacturaParaNota(CmFeRipsCarga carga) {

        if (carga.getEsComportamientoFactura()) {
            return "";
        }

        String numeroFactura = Optional.ofNullable(carga.getFacturaNumero()).orElse("");
        String documentoPrestador = Optional.ofNullable(carga.getDocumentoPrestador()).orElse("");

        if (numeroFactura.isEmpty() || documentoPrestador.isEmpty()) {
            return String.format("Error asignar Datos de factura a Nota : factura (%s) , documento prestador(%s) no tiene valores validos. ", numeroFactura, documentoPrestador);
        }

        try {

            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(carga.getFacturaNumero());
            paramConsulta.setParametroConsulta2(carga.getDocumentoPrestador());
            List<CmFactura> facturas = getCmFacturaRemoto().consultarPorAtributos(paramConsulta);

            if (facturas.isEmpty()) {
                return "";
            }

            CmFactura facturaAsociadaNota = facturas.get(0);

            return asignarValoresDesdeCmFacturaParaNotas(carga, facturaAsociadaNota);

        } catch (Exception ex) {
            return String.format("Error al procesar en asignarDatosFacturaParaNota : %s para factura : %s  ", obtenerErrorStrFormateado(ex), carga.getFacturaNumero());
        }

    }

    private String asignarCargaPeriodoAnteriorParaCapita(CmFeRipsCarga carga) {

        try {

            if (!carga.esTipoCapitaPeriodo() && !carga.esTipoCapitaFinal()) {
                return "";
            }

            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(carga.getFacturaNumeroPeriodoAsociado());
            paramConsulta.setParametroConsulta2(carga.getDocumentoPrestador());
            paramConsulta.setParametroConsulta4(carga.getMaeRegimenId());
            paramConsulta.setParametroConsulta5(carga.getCapitaPeriodo()-1);

            List<CmFeRipsCarga> cargas = getCmFeRipsCargaServicioRemoto().consultarExistenciaCargaFactura(paramConsulta);

            if (cargas.isEmpty()) {
                return "";
            }

            carga.setCargaPeriodoAnterior( cargas.get(0) );      
            
        } catch (Exception ex) {
            return String.format("Error al procesar en asignarCargaPeriodoParaCapita : %s para factura : %s  ", obtenerErrorStrFormateado(ex), carga.getFacturaNumero());
        }
        return "";
    }
        
    public Optional<CmFeAdjuntoErrores> validarCargaProcesar(CmFeRipsCarga carga) {

        CmFeAdjuntoErrores adjuntoError = new CmFeAdjuntoErrores();

        try {
            List<String> errores = Arrays.asList(validarFechaExpedicion(carga), 
                    validarDocumentoPertinenciaFactura(carga), 
                    validarFacturaNoRegistradaEnCmFeCarga(carga), 
                    validarFacturaNoRegistradaEnCmFactura(carga),
                    validarNitPrestador(carga), 
                    validarFacturaRadicadaAsociadaConNota(carga), 
                    validarFacturaPrestadorUnicaPorCarga(carga),
                    validarExistenciaNotaAjuste(carga));

            for (String error : errores) {
                if (!error.isEmpty()) {
                    adjuntoError.setDescripcion(error);
                    return Optional.of(adjuntoError);
                }
            }
        } catch (Exception ex) {
            adjuntoError.setDescripcion("Error al realizar validaciones " + obtenerErrorStrFormateado(ex) + ", factura : " +carga.getFacturaNumero());
            return Optional.of(adjuntoError);
        }

        return Optional.empty();
    }

    private String validarDocumentoPertinenciaFactura(CmFeRipsCarga carga) {
        
        if ( validarCargaSinXML(carga)) {
            return "";
        }
         
        String numeroDocumentoPertinencia = Optional.ofNullable(carga.getDocumentoPertinencia()).orElse("");
        String numeroDocumento = Optional.ofNullable(carga.getFacturaNumero()).orElse("");
        if (numeroDocumentoPertinencia.isEmpty()) {
            return String.format("Error Documento Pertinencia: No se pudo leer el valor del campo %s del archivo.", 
                    TAG_DOCUMENTO_PERTINENCIA_XML);
        }
        if (!numeroDocumentoPertinencia.equals(NIT_SAVIA_SALUD)) {
            return String.format(
                    "El documento (%s) no pertenece a Savia (%s). Número al que pertenece encontrado: %s.",
                    numeroDocumento, NIT_SAVIA_SALUD, numeroDocumentoPertinencia
            );
        }
        return "";
    }

    private String validarFechaExpedicion(CmFeRipsCarga carga) {

        if ( validarCargaSinXML(carga)) {
            return "";
        }

        try {
            Date fechaFacturaActual = carga.getFechaHoraEmision();
            if (fechaFacturaActual == null) {
                return String.format("Error Fecha Expedicion: La fecha de expedición (%s) de la factura no ha podido ser obtenida.", TAG_FECHA_EXPEDICION_XML);
            }
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", new Locale("es", "ES"));
            Date fechaAplicacionNuevaResolucion = dateFormat.parse(FECHA_INICIO_FACTURACION_ELECTRONICA);
            if (fechaFacturaActual.before(fechaAplicacionNuevaResolucion)) {
                return String.format(
                        "Error validarFechaExpedicion: La fecha de expedición de la factura (%s) es inferior a la fecha (%s).",
                        dateFormat.format(fechaFacturaActual), FECHA_INICIO_FACTURACION_ELECTRONICA
                );
            }

            return "";
        } catch (ParseException ex) {
            return "Error validarFechaExpedicion: Error al procesar la fecha de expedición de la factura: " + obtenerErrorStrFormateado(ex);
        }

    }
   
    private String validarFacturaNoRegistradaEnCmFeCarga(CmFeRipsCarga carga) {
        try {

            String numeroFactura = Optional.ofNullable(carga.getFacturaNumero()).orElse("");
            String documentoPrestador = Optional.ofNullable(carga.getDocumentoPrestador()).orElse("");
            CntPrestadorSede prestadorSede = Optional.ofNullable(carga.getGnPrestadorSede()).orElse(new CntPrestadorSede());

            if (carga.getEsTipoCargaNota()) {
                return "";
            }

            if (numeroFactura.isEmpty() || prestadorSede.getId() == null || documentoPrestador.isEmpty()) {
                return "";
            }

            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(numeroFactura);
            paramConsulta.setParametroConsulta2(documentoPrestador);

            List<CmFeRipsCarga> cargas = getCmFeRipsCargaServicioRemoto().consultarExistenciaCargaFactura(paramConsulta);
            if (!cargas.isEmpty()) {

                if ( CmFeRipsCarga.ESTADO_ERROR_CARGA == cargas.get(0).getEstado() ||
                     CmFeRipsCarga.ESTADO_DEVUELTO == cargas.get(0).getEstado() ) {
                    return "";
                }
                
                
                if (CmFeRipsCarga.ESTADO_ENVIO_AUDITORIA_OK == cargas.get(0).getEstado()) {
                    List<CmFactura> facturas = getCmFacturaRemoto().consultarPorAtributos(paramConsulta);
                    if (!facturas.isEmpty() && CmFactura.ESTADO_FACTURA_DEVUELTA == facturas.get(0).getEstadoFactura()) {
                        return "";
                    }
                }           

                return ("La factura numero: " + numeroFactura
                        + " ya esta registrada en el sistema para el prestador: "
                        + documentoPrestador + ", con consecutivo: " + cargas.get(0).getId());

            }
            return "";
        } catch (Exception ex) {
            return String.format("Error al consultar CmFeRipsCarga : %s para la carga : %s  ", obtenerErrorStrFormateado(ex), carga.getFacturaNumero());
        }
    }
    
    private String validarFacturaNoRegistradaEnCmFactura(CmFeRipsCarga carga) {
        try {

            String numeroFactura = Optional.ofNullable(carga.getFacturaNumero()).orElse("");
            String documentoPrestador = Optional.ofNullable(carga.getDocumentoPrestador()).orElse("");
            
            if (carga.getEsTipoCargaNota()) {
                return "";
            }

            if (numeroFactura.isEmpty() || documentoPrestador .isEmpty()) {
                return "";
            }

            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(numeroFactura);
            paramConsulta.setParametroConsulta2(documentoPrestador);
            List<CmFactura> facturas = getCmFacturaRemoto().consultarPorAtributos(paramConsulta);

            if (facturas.isEmpty() || CmFactura.ESTADO_FACTURA_DEVUELTA == facturas.get(0).getEstadoFactura() ) {
                return "";
            }

            CmFactura facturaEncontrada = facturas.get(0);

            return ("La factura numero : " + numeroFactura
                            + " ya esta registrada en el sistema para el prestador  :" + documentoPrestador + ". Id radicado factura : "+facturaEncontrada.getId());
        } catch (Exception ex) {
            return String.format("Error al consultar CmFactura : %s para la carga : %s  ", obtenerErrorStrFormateado(ex), carga.getFacturaNumero());
        }
    }
    
    
    private String validarNitPrestador(CmFeRipsCarga carga) {
        try {
            
            if (validarCargaSinXML(carga)) {
                return "";
            }

            String nitPrestadorXML = Optional.ofNullable(carga.getDocumentoPrestadorXml()).orElse("");
            String nitPrestador = Optional.ofNullable(carga.getDocumentoPrestador()).orElse("");

            if (nitPrestadorXML.isEmpty() || nitPrestador.isEmpty()) {
                return "No se ha encontrado el numero documento prestador.";
            }

            if (!nitPrestadorXML.equals(nitPrestador)) {
                return String.format("El nit prestador encontrado en archivo xml : %s no corresponde con el seleccionado en la carga : %s  ", nitPrestadorXML, nitPrestador);
            }

            return "";
        } catch (Exception ex) {
            return String.format("Error al consultar validarNitPrestador : %s para la carga : %s  ",obtenerErrorStrFormateado(ex), carga.getFacturaNumero());
        }
    }
    
    private String validarFacturaRadicadaAsociadaConNota(CmFeRipsCarga carga) {

        String MODALIDAD_CONTRATO_CAPITA = "capita";
        String MODALIDAD_CONTRATO_EVENTO = "evento";

        if (carga.getEsComportamientoFactura()) {
            return "";
        }

        String numeroFactura = Optional.ofNullable(carga.getFacturaNumero()).orElse("");
        String documentoPrestador = Optional.ofNullable(carga.getDocumentoPrestador()).orElse("");

        if (numeroFactura.isEmpty() || documentoPrestador.isEmpty()) {
            return String.format("Error validar factura radicada asociada a Nota : factura (%s) , documento prestador(%s) no tiene valores validos. ", numeroFactura, documentoPrestador);
        }

        try {

            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(carga.getFacturaNumero());
            paramConsulta.setParametroConsulta2(carga.getDocumentoPrestador());
            List<CmFactura> facturas = getCmFacturaRemoto().consultarPorAtributos(paramConsulta);

            if (facturas.isEmpty()) {
                return String.format(" Error validar factura para asociar a Nota : La factura con número %s y número de prestador %s no se encuentra radicada en el sistema.", numeroFactura, documentoPrestador);
            }

            CmFactura facturaAsociadaNota = facturas.get(0);

            if (CmFactura.ESTADO_FACTURA_DEVUELTA == facturaAsociadaNota.getEstadoFactura()) {
                return String.format(
                        "Error validar factura para asociar a Nota : La factura con número %s se encuentra registrada en el sistema con estado: %s.",
                        numeroFactura, facturaAsociadaNota.getEstadoFacturaStr());
            }

            DateFormat fechaFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaPrestacionFormateada = fechaFormat.parse(fechaFormat.format(facturaAsociadaNota.getFechaPrestacion()));
            Date fechaAplicacionFe = fechaFormat.parse(FECHA_INICIO_FACTURACION_ELECTRONICA);

            if (fechaPrestacionFormateada.before(fechaAplicacionFe)) {
                return String.format(
                        "Error validar factura para asociar a Nota a Nota : La factura con número %s posee una fecha inferior a %s.",
                        numeroFactura, FECHA_INICIO_FACTURACION_ELECTRONICA);
            }

            String modalidadFacturaAsociada = Optional.ofNullable(facturaAsociadaNota.getMaeTipoContratoValor()).orElse("").toLowerCase();

            if (MODALIDAD_CONTRATO_EVENTO.equals(modalidadFacturaAsociada)
                    && carga.getTipo() == CmFeRipsCarga.TIPO_CARGA_NOTA_CREDITO_CAPITA) {
                return String.format(
                        "Error la modalidad de factura asociada a la nota es :  %s esta no admite las cargas de tipo %s.",
                        facturaAsociadaNota.getMaeTipoContratoValor(), carga.getTipoStr());
            }

            if (MODALIDAD_CONTRATO_CAPITA.equals(modalidadFacturaAsociada)
                    && (carga.getTipo() == CmFeRipsCarga.TIPO_CARGA_NOTA_CREDITO_PARCIAL
                    || carga.getTipo() == CmFeRipsCarga.TIPO_CARGA_NOTA_CREDITO_TOTAL)) {
                return String.format(
                        "Error la modalidad de factura asociada a la nota es :  %s esta no admite las cargas de tipo %s.",
                        facturaAsociadaNota.getMaeTipoContratoValor(), " Nota Crédito Total o Nota Crédito Parcial");
            }

            return "";

        } catch (Exception ex) {
            return String.format("Error en validarFacturaRadicadaAsociadaConNota : %s para factura : %s  ", obtenerErrorStrFormateado(ex), carga.getFacturaNumero());
        }

    }
    
    private String validarFacturaPrestadorUnicaPorCarga(CmFeRipsCarga carga) {

        try {
            if (carga.getFacturaNumero() == null || carga.getDocumentoPrestador() == null) {
                return "";
            }
             
            String clave = String.format("%s-%s", carga.getDocumentoPrestador().trim(), 
                            carga.getFacturaNumero().trim());
            if(getFacturasVerificadas().putIfAbsent(clave, clave) != null ){
              return String.format("Error la carga ya tiene un número de factura del mismo prestador : Nit (%s) , Factura(%s). ",  carga.getDocumentoPrestador(), carga.getFacturaNumero());
            }

        } catch (Exception ex) {
            return String.format("Error en validarFacturaPrestadorUnica : %s para factura : %s  ", obtenerErrorStrFormateado(ex), carga.getFacturaNumero());
        }
        return "";

    }
   
    private String validarExistenciaFacturaPeriodoAsociada(CmFeRipsCarga carga) {

        try {

            if (!carga.esTipoCapitaPeriodo() && !carga.esTipoCapitaFinal()) {
                return "";
            }

            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(carga.getFacturaNumeroPeriodoAsociado());
            paramConsulta.setParametroConsulta2(carga.getDocumentoPrestador());
            paramConsulta.setParametroConsulta4(carga.getMaeRegimenId());

            List<CmFeRipsCarga> cargas = getCmFeRipsCargaServicioRemoto().consultarExistenciaCargaFactura(paramConsulta);

            if (cargas.isEmpty()) {
                return String.format("Error en el sistema no tiene  una carga de nit (%s) , "
                        + " factura (%s) e igual régimen. Esto es necesario para hacer la asociación de"
                        + " capita periódica.", carga.getDocumentoPrestador(), carga.getFacturaNumeroPeriodoAsociado());
            }

            CmFeRipsCarga cargaEncontrada = cargas.get(0);

            if (cargaEncontrada.getEstado() != CmFeRipsCarga.ESTADO_VALIDACION_PROCESO) {
                return String.format("La factura encontrada de id carga (%s) de estado (%s) no "
                        + "tiene estado adecuado (%s) para realizar la carga de capita.", cargaEncontrada.getId(),
                        cargaEncontrada.getEstadoStr(), CmFeRipsCarga.getEstadoStr(CmFeRipsCarga.ESTADO_VALIDACION_PROCESO));
            }

            if (!Objects.equals(cargaEncontrada.getCapitaPeriodo(), carga.getCapitaPeriodo() - 1)) {
                return String.format("La factura encontrada de id carga (%s)  no "
                        + "corresponde a un período anterior al seleccionado, "
                        + "período encontrado (%s), período requerido (%s) ", cargaEncontrada.getId(), cargaEncontrada.getCapitaPeriodo(), carga.getCapitaPeriodo() - 1);
            }

        } catch (Exception ex) {
            return String.format("Error en validarExistenciaFacturaPeriodoAsociada : %s para factura : %s  ", obtenerErrorStrFormateado(ex), carga.getFacturaNumero());
        }
        return "";
    }
    
     private String validarExistenciaNotaAjuste(CmFeRipsCarga carga) {
        try {
       
            if (!carga.esTipoNotaAjuste()) {
                return "";
            }

            String numeroNota= Optional.ofNullable(carga.getNumeroNota()).orElse("");
            String documentoPrestador = Optional.ofNullable(carga.getDocumentoPrestador()).orElse("");
            String numeroFactura = Optional.ofNullable(carga.getFacturaNumero()).orElse("");

 
            if (numeroNota.isEmpty() || documentoPrestador.isEmpty()  || numeroFactura.isEmpty() ) {
                return "";
            }

            ParamConsulta paramConsulta = new ParamConsulta();
            paramConsulta.setParametroConsulta1(numeroFactura);
            paramConsulta.setParametroConsulta2(documentoPrestador);
            paramConsulta.setParametroConsulta7(numeroNota);
            
            List<CmFeRipsCarga> cargas = getCmFeRipsCargaServicioRemoto().consultarExistenciaCargaFactura(paramConsulta);
            if (!cargas.isEmpty()) {
                return ("La nota de numero: " + numeroNota
                        + " ya esta registrada en el sistema para el prestador: "
                        + documentoPrestador + ", con consecutivo: " + cargas.get(0).getId());
            }
            return "";
        } catch (Exception ex) {
            return String.format("Error al consultar validarExistenciaNotaAjuste : %s para la carga : %s  ", obtenerErrorStrFormateado(ex), carga.getFacturaNumero());
        }
    }

    public Optional<CmFeAdjuntoErrores> asignarValoresDesdeArchivoTXT(CmFeRipsCarga carga, CmFeRipsCargaAdjunto anexoTXT) {

        CmFeAdjuntoErrores adjuntoError = new CmFeAdjuntoErrores();

        try {
            List<String> errores = Arrays.asList(asignarCuvDeTxt(carga, anexoTXT), asignarFechaMinisterioDeTxt(carga, anexoTXT));

            for (String error : errores) {
                if (!error.isEmpty()) {
                    adjuntoError.setDescripcion(error);
                    return Optional.of(adjuntoError);
                }
            }
        } catch (Exception ex) {
            adjuntoError.setDescripcion("Error al procesar el archivo txt: " + obtenerErrorStrFormateado(ex) + ", archivo: " + anexoTXT.getArchivoNombre());
            return Optional.of(adjuntoError);
        }

        return Optional.empty();
    }
  
    private String asignarCuvDeTxt(CmFeRipsCarga carga, CmFeRipsCargaAdjunto anexoTXT) {

        try (InputStream inputUtilizar = obtenerCopiaInputStream(anexoTXT)) {
            carga.setCuv(obtenerValorArchivoCUV(inputUtilizar, LINEA_CUV_TXT));
            if (carga.getCuv().isEmpty()) {
                return String.format("No se puedo leer la linea %s del archivo : %s", LINEA_CUV_TXT, anexoTXT.getArchivoNombre());
            }
        } catch (IOException ex) {
            return String.format("Error al leer el texto %s en asignarCuvDeTxt : %s en el archivo txt : %s  ", LINEA_CUV_TXT, obtenerErrorStrFormateado(ex), anexoTXT.getArchivoNombre());
        }

        return "";
    }
    
    private String asignarFechaMinisterioDeTxt(CmFeRipsCarga carga, CmFeRipsCargaAdjunto anexoTXT) {
        try {

            String fecha;
            try (InputStream inputFecha = obtenerCopiaInputStream(anexoTXT)) {
                fecha = obtenerValorArchivoCUV(inputFecha, LINEA_FECHA_MINISTERIO_TXT);
                if (fecha.isEmpty()) {
                    return String.format("No se pudo leer el campo Fecha (%s) del archivo: %s", LINEA_FECHA_MINISTERIO_TXT, anexoTXT.getArchivoNombre());
                }
            }

            String hora;
            try (InputStream inputHora = obtenerCopiaInputStream(anexoTXT)) {
                hora = obtenerValorArchivoCUV(inputHora, LINEA_HORA_MINISTERIO_TXT);
                if (hora.isEmpty()) {
                    return String.format("No se pudo leer el campo Hora(%s) del archivo: %s", LINEA_HORA_MINISTERIO_TXT, anexoTXT.getArchivoNombre());
                }
            }

            return asignarFormatoFechaMinisterios(fecha + " " + hora, anexoTXT.getArchivoNombre(), carga);

        } catch (IOException ex) {
            return String.format("Error al leer el texto %s en asignarFechaMinisterioDeTxt : %s en el archivo txt : %s  ", LINEA_FECHA_MINISTERIO_TXT, obtenerErrorStrFormateado(ex), anexoTXT.getArchivoNombre());
        }
    }
    
   public Optional<CmFeAdjuntoErrores> asignarValoresDesdeArchivoJSON(CmFeRipsCarga carga, CmFeRipsCargaAdjunto anexoJSON) {

        CmFeAdjuntoErrores adjuntoError = new CmFeAdjuntoErrores();

        try {
            List<String> errores = Arrays.asList(asignarCuvDeJson(carga, anexoJSON), asignarFechaMinisterioDeJson(carga, anexoJSON));

            for (String error : errores) {
                if (!error.isEmpty()) {
                    adjuntoError.setDescripcion(error);
                    return Optional.of(adjuntoError);
                }
            }
        } catch (Exception ex) {
            adjuntoError.setDescripcion("Error al procesar el archivo Json: " + obtenerErrorStrFormateado(ex) + ", archivo: " + anexoJSON.getArchivoNombre());
            return Optional.of(adjuntoError);
        }

        return Optional.empty();
    }
   
    private String asignarCuvDeJson(CmFeRipsCarga carga, CmFeRipsCargaAdjunto anexoJSON) {
        

        try (InputStream inputUtilizar = obtenerCopiaInputStream(anexoJSON)) {
            carga.setCuv(obtenerValorArchivoJsonCUV(inputUtilizar, ATRIBUTO_CUV_JSON));
            if (carga.getCuv()==null || carga.getCuv().isEmpty()) {
                return String.format("No se puedo leer la linea %s del archivo : %s", ATRIBUTO_CUV_JSON, anexoJSON.getArchivoNombre());
            }
        } catch (IOException ex) {
            return String.format("Error al leer el texto %s en asignarCuvDeJson : %s en el archivo Json : %s  ", ATRIBUTO_CUV_JSON, obtenerErrorStrFormateado(ex), anexoJSON.getArchivoNombre());
        } catch (Exception ex) {
             return String.format("Error al leer el texto %s en asignarCuvDeJson : %s en el archivo Json : %s  ", ATRIBUTO_CUV_JSON, obtenerErrorStrFormateado(ex), anexoJSON.getArchivoNombre());
        }

        return "";
    }
   
    private String asignarFechaMinisterioDeJson(CmFeRipsCarga carga, CmFeRipsCargaAdjunto anexoJSON) {

        String fecha = "";
        try (InputStream inputFecha = obtenerCopiaInputStream(anexoJSON)) {
            fecha = obtenerValorArchivoJsonCUV(inputFecha, ATRIBUTO_FECHA_MINISTERIO_JSON);
            if (fecha==null || fecha.isEmpty()) {
                return String.format("No se pudo leer el campo Fecha (%s) del archivo: %s", ATRIBUTO_FECHA_MINISTERIO_JSON, anexoJSON.getArchivoNombre());
            }
            return asignarFormatoFechaMinisteriosJSON(fecha, anexoJSON.getArchivoNombre(), carga);

        } catch (IOException ex) {
            return String.format("Error al leer el texto %s en asignarFechaMinisterioDeJson : %s en el archivo Json : %s  ", ATRIBUTO_FECHA_MINISTERIO_JSON, obtenerErrorStrFormateado(ex), anexoJSON.getArchivoNombre());
        } catch (Exception ex) {
             return String.format("Error al leer el texto %s en asignarFechaMinisterioDeJson : %s en el archivo Json : %s  ", ATRIBUTO_FECHA_MINISTERIO_JSON, obtenerErrorStrFormateado(ex), anexoJSON.getArchivoNombre());
        }

    }
    
    public Optional<CmFeAdjuntoErrores> asignarValoresDesdeArchivoXML(CmFeRipsCarga carga, CmFeRipsCargaAdjunto anexoXML){

        CmFeAdjuntoErrores adjuntoError = new CmFeAdjuntoErrores();

        try {
            List<String> errores = Arrays.asList(
                    asignarNumeroFacturaDeXml(carga, anexoXML),
                    asignarCoberturaXml(carga, anexoXML),
                    asignarModalidadXml(carga, anexoXML),
                    asignarValorFacturaDeXml(carga, anexoXML),
                    asignarCufeDeXml(carga, anexoXML),
                    asignarFechaEmisionDeXml(carga, anexoXML),
                    asignarNumeroContratoXml(carga, anexoXML),
                    asignarNumeroNotaDeXml(carga, anexoXML),
                    asignarValorNotaDeXml(carga, anexoXML),
                    asignarCudeDeXml(carga, anexoXML),
                    asignarValorCopagoDeXml(carga, anexoXML),
                    asignarCuotaModeradoraXml(carga, anexoXML),
                    asignarDocumentoPertinenciaXml(carga, anexoXML),
                    asignarNitPrestadorXml(carga, anexoXML)
            );

            for (String error : errores) {
                if (!error.isEmpty()) {
                    adjuntoError.setDescripcion(error);
                    return Optional.of(adjuntoError);
                }
            }
        } catch (Exception ex) {
            adjuntoError.setDescripcion("Error al procesar el archivo xml: " + obtenerErrorStrFormateado(ex)+ ", archivo: " + anexoXML.getArchivoNombre());
            return Optional.of(adjuntoError);
        }
        return Optional.empty();
    }

    private String asignarCufeDeXml(CmFeRipsCarga carga, CmFeRipsCargaAdjunto anexoXML) throws IOException {

        String mensajeError = "";
        if (carga.getEsComportamientoNota()) {
            return mensajeError;
        }

        try (InputStream inputUtilizar = obtenerCopiaInputStream(anexoXML)) {
            String cufe = obtenerValorEnTagDescripcion(inputUtilizar, TAG_CUFE_XML);
            if (cufe.isEmpty()) {
                return String.format("No se puedo leer el campo cbc: %s - valor cufe del archivo : %s", TAG_CUFE_XML, anexoXML.getArchivoNombre());
            }
            carga.setCufe(cufe);
            return "";
        } catch (UnsupportedEncodingException | ParserConfigurationException | SAXException | XMLStreamException ex) {
            mensajeError = String.format("Error al procesar el tag %s en asignarCufeDeXml : %s el archivo XML : %s  ", TAG_CUFE_XML, obtenerErrorStrFormateado(ex), anexoXML.getArchivoNombre());
        }
        return mensajeError;
    }
     
    private String asignarCudeDeXml(CmFeRipsCarga carga, CmFeRipsCargaAdjunto anexoXML) throws IOException {
        String mensajeError = "";

        if (carga.getEsComportamientoFactura()) {
            return mensajeError;
        }

        try (InputStream inputUtilizar = obtenerCopiaInputStream(anexoXML)) {
            String cude = obtenerValorEnTagDescripcion(inputUtilizar, TAG_CUFE_XML);
            if (cude.isEmpty()) {
                return String.format("No se puedo leer el campo cbc:%s - valor Cude del archivo : %s", TAG_CUFE_XML, anexoXML.getArchivoNombre());
            }
            carga.setCude(cude);

        } catch (UnsupportedEncodingException | ParserConfigurationException | SAXException | XMLStreamException ex) {
            mensajeError = String.format("Error al procesar el tag %s en asignarCudeDeXml : %s el archivo XML : %s  ", TAG_CUFE_XML, obtenerErrorStrFormateado(ex), anexoXML.getArchivoNombre());
        }

        return mensajeError;
    }
    
    private String asignarValorFacturaDeXml(CmFeRipsCarga carga, CmFeRipsCargaAdjunto anexoXML){
        String mensajeError = "";
        try (InputStream inputUtilizar = obtenerCopiaInputStream(anexoXML)) {
            String valoFactura = obtenerValorFacturaLegalMonetary(inputUtilizar, TAG_VALOR_FACTURA_LEGAL_XML);
            if (valoFactura.isEmpty()) {
                return String.format("No se puedo leer el campo %s - valor factura del archivo : %s", TAG_VALOR_FACTURA_LEGAL_XML, anexoXML.getArchivoNombre());
            }
            carga.setFacturaValor(new BigDecimal(valoFactura));

        } catch (UnsupportedEncodingException | ParserConfigurationException | SAXException | XMLStreamException ex) {
            mensajeError = String.format("Error al procesar el tag %s en asignarValorFacturaDeXml : %s el archivo XML : %s  ", TAG_VALOR_FACTURA_LEGAL_XML, obtenerErrorStrFormateado(ex), anexoXML.getArchivoNombre());
        } catch (Exception ex) {
            mensajeError = String.format("Error al procesar el tag %s en asignarValorFacturaDeXml : %s el archivo XML : %s  ", TAG_VALOR_FACTURA_LEGAL_XML, obtenerErrorStrFormateado(ex), anexoXML.getArchivoNombre());
        }
        return mensajeError;
    }
    
    private String asignarValorNotaDeXml(CmFeRipsCarga carga, CmFeRipsCargaAdjunto anexoXML) throws IOException {
        String mensajeError = "";
        if (carga.getEsComportamientoFactura()) {
            return mensajeError;
        }

        try (InputStream inputUtilizar = obtenerCopiaInputStream(anexoXML)) {
            String valoNota = obtenerValorFacturaLegalMonetary(inputUtilizar, TAG_VALOR_NOTA_LEGAL_XML);
            if (valoNota.isEmpty()) {
                return String.format("No se puedo leer el campo %s  - valor nota del archivo : %s ", TAG_VALOR_NOTA_LEGAL_XML, anexoXML.getArchivoNombre());
            }
            carga.setValorNota(new BigDecimal(valoNota));
        } catch (UnsupportedEncodingException | ParserConfigurationException | SAXException | XMLStreamException | XPathExpressionException ex) {
            mensajeError = String.format("Error al procesar el tag %s en asignarValorNotaDeXml : %s el archivo XML : %s  ", TAG_VALOR_NOTA_LEGAL_XML, obtenerErrorStrFormateado(ex), anexoXML.getArchivoNombre());
        }

        return mensajeError;
    }

    private String asignarNumeroFacturaDeXml(CmFeRipsCarga carga, CmFeRipsCargaAdjunto anexoXML) {
        String mensajeError = "";

        try (InputStream inputUtilizar = obtenerCopiaInputStream(anexoXML)) {
            String facturaNumero = "";

            if (carga.getEsComportamientoFactura()) {
                facturaNumero = obtenerValorCampXmlBase(inputUtilizar, TAG_FACTURA_NUMERO_NO_NOTAS_XML);
                if (facturaNumero.isEmpty()) {
                    return String.format("No se pudo leer el campo %s - número factura del archivo: %s", TAG_FACTURA_NUMERO_NO_NOTAS_XML, anexoXML.getArchivoNombre());
                }
            } else if (carga.getEsComportamientoNota()) {
                facturaNumero = obtenerValorEnTagDescripcion(inputUtilizar, TAG_FACTURA_NUMERO_NOTAS_XML);
                if (facturaNumero.isEmpty()) {
                    return String.format("No se pudo leer el campo %s - número factura  del archivo: %s", TAG_FACTURA_NUMERO_NOTAS_XML, anexoXML.getArchivoNombre());
                }
                if (!facturaNumero.equals(carga.getFacturaNumero())) {
                    return String.format("El número de factura ingresado (%s) no corresponde al encontrado (%s) en el archivo xml (%s).", carga.getFacturaNumero(), facturaNumero, anexoXML.getArchivoNombre());
                }
            }
            carga.setFacturaNumero(facturaNumero);
        } catch (IOException | ParserConfigurationException | XMLStreamException | SAXException ex) {
            mensajeError = String.format("Error al procesar el tag %s en asignarNumeroFacturaDeXml : %s el archivo XML : %s  ", "Numero ", obtenerErrorStrFormateado(ex), anexoXML.getArchivoNombre());
        }
        return mensajeError;
    }
    
    private String asignarNumeroNotaDeXml(CmFeRipsCarga carga, CmFeRipsCargaAdjunto anexoXML) throws IOException {
        String mensajeError = "";
        if (carga.getEsComportamientoFactura()) {
            return mensajeError;
        }
        
        String numeroNota = obtenerValorCampoXMLBase(anexoXML, TAG_NUMERO_NOTAS_XML, anexoXML.getArchivoNombre());
        if (numeroNota.isEmpty()) {
            return String.format("No se pudo leer el campo %s - número de nota del archivo: %s", TAG_NUMERO_NOTAS_XML, anexoXML.getArchivoNombre());
        }
        carga.setNumeroNota(numeroNota);
        return mensajeError;
    }
    
    private String asignarFechaEmisionDeXml(CmFeRipsCarga carga, CmFeRipsCargaAdjunto anexoXML) throws IOException {

        String fecha = obtenerValorCampoXMLBase(anexoXML, TAG_FECHA_EXPEDICION_XML, anexoXML.getArchivoNombre());
        if (fecha.isEmpty()) {
            return "No se pudo leer el campo " + TAG_FECHA_EXPEDICION_XML + " que representa fecha de emision del archivo: " + anexoXML.getArchivoNombre();
        }

        String hora = obtenerValorCampoXMLBase(anexoXML, TAG_HORA_EXPEDICION_XML, anexoXML.getArchivoNombre());
        if (hora.isEmpty()) {
            return "No se pudo leer el campo " + TAG_HORA_EXPEDICION_XML + " que representa hora de emision del archivo: " + anexoXML.getArchivoNombre();
        }
        return asignarFormatoFechaEmision(fecha + " " + hora, anexoXML.getArchivoNombre(), carga);
       
    }
     
    private String asignarNumeroContratoXml(CmFeRipsCarga carga, CmFeRipsCargaAdjunto anexoXML) throws IOException {
        String mensajeError;
        try (InputStream inputUtilizar = obtenerCopiaInputStream(anexoXML)) {
            String contranto = obtenerValorSobreAreaAdicionalXml(inputUtilizar, TAG_NUMERO_CONTRATO_XML);
            carga.setContrato(contranto);
            return "";
        } catch (UnsupportedEncodingException | ParserConfigurationException | SAXException | XPathExpressionException ex) {
              mensajeError = String.format("Error al procesar el tag %s en asignarNumeroContratoXml : %s el archivo XML : %s  ", TAG_NUMERO_CONTRATO_XML, obtenerErrorStrFormateado(ex), anexoXML.getArchivoNombre());
        }
        return mensajeError;
    }    
     
    private String asignarCoberturaXml(CmFeRipsCarga carga, CmFeRipsCargaAdjunto anexoXML) throws IOException {

        String mensajeError = "";

        if (carga.getEsComportamientoNota()) {
            return mensajeError;
        }

        try (InputStream inputUtilizar = obtenerCopiaInputStream(anexoXML)) {
            String codigoCobertura = obtenerValorSchemeIDSobreAreaAdicionalXml(inputUtilizar, TAG_COBERTURA_XML);
            if (codigoCobertura.isEmpty()) {
                return String.format("No se pudo leer el campo %s en el archivo: %s", TAG_COBERTURA_XML, anexoXML.getArchivoNombre());
            }
            
            return asignarCobertura(codigoCobertura, carga, anexoXML);
         
        } catch (UnsupportedEncodingException | ParserConfigurationException | SAXException | XPathExpressionException ex) {
            mensajeError = String.format("Error al procesar el tag %s en asignarCoberturaXml : %s el archivo XML : %s  ", TAG_COBERTURA_XML, obtenerErrorStrFormateado(ex), anexoXML.getArchivoNombre());
        }

        return mensajeError;
    }
        
    private String asignarModalidadXml(CmFeRipsCarga carga, CmFeRipsCargaAdjunto anexoXML) throws IOException {
        String mensajeError = "";
        
        if (carga.getEsComportamientoNota()) {
            return mensajeError;
        }

        try (InputStream inputUtilizar = obtenerCopiaInputStream(anexoXML)) {

            String modalidad = obtenerValorSchemeIDSobreAreaAdicionalXml(inputUtilizar, TAG_MODALIDAD_XML);
            if (modalidad.isEmpty()) {
                return String.format("No se puedo leer el campo  %s :  %s", TAG_MODALIDAD_XML, anexoXML.getArchivoNombre());
            }

            String codigoModalidad = getCodigosModalidadMap().getOrDefault(modalidad, CODIGO_SCHEME_ID_NO_ENCONTRADO);
            if (CODIGO_SCHEME_ID_NO_ENCONTRADO.equals(codigoModalidad)) {
                return String.format("El codigo encontrado ( %s ) del tag %s no esta definido en el sistema : %s", modalidad, TAG_MODALIDAD_XML, anexoXML.getArchivoNombre());
            }

            return asignarModalidadContrato(codigoModalidad, carga);

        } catch (UnsupportedEncodingException | ParserConfigurationException | SAXException | XPathExpressionException ex) {
            mensajeError = String.format("Error al procesar el tag %s en asignarModalidadXml : %s el archivo XML : %s  ", TAG_MODALIDAD_XML, obtenerErrorStrFormateado(ex), anexoXML.getArchivoNombre());
        }

        return mensajeError;
    }
    
    private String asignarCuotaModeradoraXml(CmFeRipsCarga carga, CmFeRipsCargaAdjunto anexoXML) throws Exception {

        String mensajeError;

        try (InputStream inputUtilizar = obtenerCopiaInputStream(anexoXML)) {
            String cuota = obtenerValorSobreAreaPagosXml(inputUtilizar, TAG_CODIGO_MODERADORA_XML);
            cuota = Optional.ofNullable(cuota).orElse("0");
            cuota = cuota.equals("") ? "0" : cuota;
            carga.setValorCuotaModeradora(new BigDecimal(cuota));
            return "";
        } catch (Exception ex) {
            mensajeError = String.format("Error al procesar el tag %s en asignarCuotaModeradoraXml : %s el archivo XML : %s  ", TAG_CODIGO_MODERADORA_XML, obtenerErrorStrFormateado(ex), anexoXML.getArchivoNombre());
        }
        return mensajeError;
    }
    
    private String asignarDocumentoPertinenciaXml(CmFeRipsCarga carga, CmFeRipsCargaAdjunto anexoXML) throws Exception {

        String mensajeError;

        try (InputStream inputUtilizar = obtenerCopiaInputStream(anexoXML)) {
             String numeroDocumentoPertinencia = obtenerDocumentoPertenencia(inputUtilizar);
             if (numeroDocumentoPertinencia.isEmpty()) {
               return("No se puedo leer el campo " + TAG_DOCUMENTO_PERTINENCIA_XML + " que contiene la pertinencia de la factura en el archivo : " + anexoXML.getArchivoNombre());
           }
            carga.setDocumentoPertinencia(numeroDocumentoPertinencia);
            return "";
        } catch (Exception ex) {
            mensajeError = String.format("Error al procesar el tag %s en asignarDocumentePertinenciaXml : %s el archivo XML : %s  ", TAG_DOCUMENTO_PERTINENCIA_XML, obtenerErrorStrFormateado(ex), anexoXML.getArchivoNombre());
        }
        return mensajeError;
    }

    private String asignarValorCopagoDeXml(CmFeRipsCarga carga, CmFeRipsCargaAdjunto anexoXML) throws Exception {
          String mensajeError = "";
          try (InputStream inputUtilizar = obtenerCopiaInputStream(anexoXML)) {
            String valoNota = obtenerValorSobreAreaPagosXml(inputUtilizar, TAG_CODIGO_COPAGO_XML);
            valoNota = Optional.ofNullable(valoNota).orElse("0");
            valoNota = valoNota.equals("") ? "0": valoNota;
            carga.setValorCopago(new BigDecimal(valoNota));
         }catch(Exception ex){
           mensajeError = String.format("Error al procesar el tag %s en asignarValorCopagoDeXml : %s el archivo XML : %s  ", TAG_CODIGO_COPAGO_XML, obtenerErrorStrFormateado(ex), anexoXML.getArchivoNombre());
        }
        return mensajeError;
    }
    
    private String asignarNitPrestadorXml(CmFeRipsCarga carga, CmFeRipsCargaAdjunto anexoXML) throws Exception {
        String mensajeError;

        try (InputStream inputUtilizar = obtenerCopiaInputStream(anexoXML)) {
             String nitPrestadorXml = obtenerValorNitPrestadorXml(inputUtilizar);
             if (nitPrestadorXml.isEmpty()) {
               return("No se puedo leer el campo "+TAG_NIT_PRESTADOR_XML+"  que contiene el nit prestador de factura en el archivo : " + anexoXML.getArchivoNombre());
           }
            carga.setDocumentoPrestadorXml(nitPrestadorXml);
            return "";
        } catch (Exception ex) {
            mensajeError = String.format("Error al procesar el tag %s en asignarNitPrestadorXml : %s el archivo XML : %s  ", TAG_NIT_PRESTADOR_XML, obtenerErrorStrFormateado(ex), anexoXML.getArchivoNombre());
        }
        return mensajeError;
    }
           
    private String asignarValoresDesdeCmFacturaParaNotas(CmFeRipsCarga carga, CmFactura factura)  {
        carga.setMaeContratoModalidadCodigo(factura.getMaeTipoContratoCodigo());
        carga.setMaeContratoModalidadId(factura.getMaeTipoContratoId());
        carga.setMaeContratoModalidadValor(factura.getMaeTipoContratoValor());
        carga.setMaeRegimenId(factura.getMaeRegimenId());
        carga.setMaeRegimenCodigo(factura.getMaeRegimenCodigo());
        carga.setMaeRegimenValor(factura.getMaeRegimenValor());
        carga.setCobertura(!factura.isPbs());
        carga.setIdCmFacturaAsociadaNota(factura.getId());
        return "";
    }
    
    private InputStream obtenerCopiaInputStream(CmFeRipsCargaAdjunto anexoXML) throws IOException {
        InputStream inputEntrada = anexoXML.getInputStream();
        byte[] buffer = inputEntrada.readAllBytes();
        InputStream inputUtilizar = new ByteArrayInputStream(buffer);
        InputStream inputRemplazar = new ByteArrayInputStream(buffer);
        anexoXML.setInputStream(inputRemplazar);
        return inputUtilizar;
    }
    
    private List<CmFeAdjuntoErrores> insertarDatosCarga(CmFeRipsCarga cmFeCarga, String nombreError) {

        List<CmFeAdjuntoErrores> erroresInsertarDatosCarga = new ArrayList<>();
      
        procesarInsercion(() -> asignarTecnicoRadicador(cmFeCarga), nombreError, erroresInsertarDatosCarga);
        procesarInsercion(() -> insertarCmFeCarga(nombreError, cmFeCarga), nombreError, erroresInsertarDatosCarga);
        procesarInsercion(() -> actualizarIdAsignacionCmGrupo(cmFeCarga), nombreError, erroresInsertarDatosCarga);
        procesarInsercionLista(() -> insertarArchivosEnContenido(nombreError, cmFeCarga), erroresInsertarDatosCarga);
        procesarInsercion(() -> insertarAdjuntos(cmFeCarga), nombreError, erroresInsertarDatosCarga);
        procesarInsercion(() -> insertarSoportesNotas(cmFeCarga), nombreError, erroresInsertarDatosCarga);
        procesarInsercion(() -> actualizarCapitaPeriodo(cmFeCarga), nombreError, erroresInsertarDatosCarga);
        procesarInsercion(() -> actualizarEstadoCargaExitoso(cmFeCarga), nombreError, erroresInsertarDatosCarga);
        procesarInsercionFallo(() -> actualizarEstadoCargaPorFallo(cmFeCarga), nombreError, erroresInsertarDatosCarga);
        procesarInsercionFallo(() -> actualizarObservacionCargaPorFallo(cmFeCarga,erroresInsertarDatosCarga), nombreError, erroresInsertarDatosCarga);
        
        modificarErrorInsercionSiExiste(erroresInsertarDatosCarga);

        return erroresInsertarDatosCarga;
    }

    private List<CmFeAdjuntoErrores> insertarArchivosEnContenido(String nombreError, CmFeRipsCarga carga) {
        List<CmFeAdjuntoErrores> listaErrores = new ArrayList<>();

        for (CmFeRipsCargaAdjunto anexo : carga.getCmFeRipsCargaAdjuntos()) {

            try (InputStream inputUtilizar = obtenerCopiaInputStream(anexo)) {
                String mensajeError = insertaraArchivoContenidoSegunExtension( anexo.getArchivoNombre(), inputUtilizar, carga);
                if (!mensajeError.isEmpty()) {
                    listaErrores.add(new CmFeAdjuntoErrores(nombreError, mensajeError));
                }
            } catch (IOException ex) {
                CmFeAdjuntoErrores errorGeneral = new CmFeAdjuntoErrores();
                errorGeneral.setDescripcion(String.format("Error en insertarArchivosEnContenido al procesar el archivo : %s",obtenerErrorStrFormateado(ex)));
                listaErrores.add(errorGeneral);
            }
        }

        return listaErrores;
    }
    
    private String insertaraArchivoContenidoSegunExtension(String nombreArchivo, InputStream inputUtilizar, CmFeRipsCarga carga) {

        String fileExtension = nombreArchivo.substring(nombreArchivo.lastIndexOf(".") + 1);

        switch (fileExtension) {
            case "json":
                return insertarArchivoJsonContenido(inputUtilizar, carga, nombreArchivo);
            case "xml":
                return insertarArchivoXmlContenido(inputUtilizar, carga);
            case "txt":
                return insertarArchivoTxtContenido(inputUtilizar, carga);
            default:
                return String.format("Extensión de archivo no soportada: %s", fileExtension);
        }
    }
  
    private String insertarAdjuntos(CmFeRipsCarga carga) {

        try {
            for (CmFeRipsCargaAdjunto adjuntoNuevo : carga.getCmFeRipsCargaAdjuntos()) {
                if (adjuntoNuevo.getIdInsertar() != null && adjuntoNuevo.getIdInsertar() != 0) {
                    CmFeRipsCargaAdjunto adjuntoInsertar = new CmFeRipsCargaAdjunto();
                    adjuntoInsertar.setCmFeRipsCarga(new CmFeRipsCarga(carga.getId()));
                    adjuntoInsertar.setArchivoNombre(adjuntoNuevo.getArchivoNombre());
                    adjuntoInsertar.setArchivoNombreOriginal(adjuntoNuevo.getArchivoNombreOriginal());
                    adjuntoInsertar.setArchivoRuta(adjuntoNuevo.getArchivoRuta());
                    adjuntoInsertar.setInputStream(adjuntoNuevo.getInputStream());
                    adjuntoInsertar.setIdInsertar(adjuntoNuevo.getIdInsertar());
                    adjuntoInsertar.setFechaHoraCrea(adjuntoNuevo.getFechaHoraCrea());
                    adjuntoInsertar.setArchivo(adjuntoNuevo.getArchivo());
                    String postFijo =  CmUtilidades.esArchivoJsonCuv(adjuntoInsertar.getArchivoNombre()) ? "_CUV":"";
                    adjuntoInsertar.getGenerarNuevoNombreArchivo(postFijo);
                    adjuntoInsertar.setTipo(adjuntoNuevo.getTipo());
                    //GUARDAR ARCHIVO
                    String mensajeCreacionArchivo = crearArchivo(adjuntoInsertar);
                    if (mensajeCreacionArchivo.startsWith("Error") == false) {
                        adjuntoInsertar.setArchivoExiste(true);
                        adjuntoInsertar.setUsuarioCrea(adjuntoNuevo.getUsuarioCrea());
                        adjuntoInsertar.setTerminalCrea(adjuntoNuevo.getTerminalCrea());
                        adjuntoInsertar.setFechaHoraCrea(adjuntoNuevo.getFechaHoraCrea());
                        getCmFeRipsCargaAdjuntoRemoto().insertar(adjuntoInsertar);
                    }else{
                       return mensajeCreacionArchivo;
                    }
                }
            }
            return "";
        } catch (Exception ex) {
            return String.format("Error en insertarAdjuntos : %s", obtenerErrorStrFormateado(ex));
        }
    }
    
    private void actualizarAtributoFeDocumento(CmFeRipsCargaBean bean) {
        try {
            
            bean.getObjeto().setDocumentoValor(bean.getObjeto().getFacturaValor());
            bean.getObjeto().setDocumentoNumero(bean.getObjeto().getFacturaNumero());
            if (bean.getObjeto().getEsComportamientoNota()) {
                CmFeNota nota = getCmFeNotasRemoto().consultarPorCmFeCargaId(bean.getObjeto().getId());
                if (nota.getId() != null) {
                    bean.getObjeto().setDocumentoValor(nota.getValorNota());
                    bean.getObjeto().setDocumentoNumero(nota.getNumeroNota());
                }
            }
            
            FeDocumento documento = obtenerFacuraEnFeDocumento(bean);
            if (documento == null || documento.getId() == null) {
                bean.addError("El documento asociado a la carga no esta en el sistema de facturación electrónica");
            } else {
                if (!bean.getObjeto().esDe5601SoporteFe()) {
                   if (validarValorDocumento(documento, bean)) {
                        bean.getObjeto().setDe5601SoporteFe(true);
                        getCmFeRipsCargaServicioRemoto().actualizarAtributoSoporteFe(bean.getObjeto());
                        bean.addMensaje("El documento ya ha sido registrada en el sistema de facturación electónica.");
                    }
                }
            }
        } catch (Exception ex) {
            bean.addError("Error :" + obtenerErrorStrFormateado(ex));
        }
    }

    private boolean validarCargaInsertar(CmFeRipsCargaBean bean) throws Exception {


        //REGIMEN
        if (CmFeRipsCarga .TIPO_CARGA_FACTURA == bean.getObjeto().getTipo() && 
            bean.getObjeto().getMaeRegimenId() == null )  {
            bean.addError(("Ocurrio un error el regimen se encuentra vacio"));
            return false;
        }

        //VALIDAR IPS Y LIMITAR A 1 CARGA POR IPS
        if (bean.getObjeto().getGnPrestadorSede() == null) {
            bean.addError("Por favor seleccione la sede del Prestador");
            return false;
        }
        
        return true;
    }

    private void asignarValoresObjetoCarga(CmFeRipsCargaBean bean) {
        
        bean.auditoriaGuardar(bean.getObjeto());
        
        if (bean.getObjeto().getEsTipoCapita()) {
            for (Map.Entry<Integer, Maestro> mae : bean.getHashRegimenes().entrySet()) {
                if (mae.getValue().getId().equals(bean.getObjeto().getMaeRegimenId())) {
                    bean.getObjeto().setMaeRegimenCodigo(mae.getValue().getValor());
                    bean.getObjeto().setMaeRegimenValor(mae.getValue().getNombre());
                }
            }
        }
 
        bean.getObjeto().setEmpresa(new Empresa(bean.getConexion().getEmpresa().getId()));
        bean.getObjeto().setEstado(CmFeRipsCarga.ESTADO_EN_COLA);
        bean.getObjeto().setFechaHoraInicio(new Date());
        asignarTipoContrato(bean);
        bean.getObjeto().setTiempo(0);
        bean.getObjeto().setOrigen( CmFeRipsCarga.ORIGEN_CARGA_MANUAL);
        
        bean.getObjeto().setFechaHoraMinisterio(null);
        bean.getObjeto().setFechaHoraEmision(validarCargaSinXML(bean.getObjeto()) ? 
                                             bean.getObjeto().getFechaHoraInicio(): null);
        bean.getObjeto().setValorCopago(BigDecimal.ZERO);
        bean.getObjeto().setValorCuotaModeradora(BigDecimal.ZERO);
        bean.getObjeto().setFacturaValor(BigDecimal.ZERO);
        bean.getObjeto().setContrato("");
        bean.getObjeto().setCobertura(null);
        bean.getObjeto().setCude(validarCargaSinXML(bean.getObjeto()) ? "Este tipo de nota no genera cude.":"");
        bean.getObjeto().setCufe("");
        bean.getObjeto().setValorNota(BigDecimal.ZERO);
        bean.getObjeto().setNumeroNota(validarCargaSinXML(bean.getObjeto()) ?
                          obtenerNumeroNotaDeNombreArchivo(bean.getObjeto(), "JSON"):"");
    }
      
    private boolean validarDiasHabilesEsperaFacturaElectronica(CmFeRipsCargaBean bean){
        try {
            if( ! bean.getObjeto().esDe5601SoporteFe() ){
         
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateFechaActualString = sdf.format(new Date());
                Date fechaActualFormateada = sdf.parse(dateFechaActualString);

                String fechaEmisionString = sdf.format(bean.getObjeto().getFechaHoraEmision());
                Date fechaEmisionCarga = sdf.parse(fechaEmisionString);

                int rangoDiasHabiles = getCalendarioRemoto().consultarHabilies(fechaEmisionCarga, fechaActualFormateada);
                if (DIAS_HABILES_ESPERA_FACTURA_ELECTRONICA > rangoDiasHabiles) {
                    bean.addError("No se puede enviar la factura por que no se encuentra en el sistema de facturación electrónica (Soportes Fe) dentro de los ( "+DIAS_HABILES_ESPERA_FACTURA_ELECTRONICA+" ) días hábiles después de emisión de la factura.");
                    return false;
                }
             
                return true;
         }
        } catch (Exception ex) {
            bean.addError(obtenerErrorStrFormateado(ex));
        }
     
        return true;
    }

    private void asignarTipoContrato(CmFeRipsCargaBean bean) {
        //TIPO CONTRATO
        if (bean.getObjeto().getCntContratoSede() != null) {
            bean.getObjeto().setCntContrato(bean.getObjeto().getCntContratoSede().getCntContrato());
            if (bean.getObjeto().getCntContratoSede().getAplicaContribuitivo() != null) {
                if (bean.getObjeto().getCntContratoSede().getAplicaContribuitivo()) {
                    bean.getObjeto().setCntTipoContratoId(CntContratoSede.TIPO_CONTRATO_CONTRIBUTIVO);
                    bean.getObjeto().setContrato(bean.getObjeto().getCntContratoSede().getCntContrato().getContrato());
                }
            }
            if (bean.getObjeto().getCntContratoSede().getAplicaSubsidiado() != null) {
                if (bean.getObjeto().getCntContratoSede().getAplicaSubsidiado()) {
                    bean.getObjeto().setCntTipoContratoId(CntContratoSede.TIPO_CONTRATO_SUBSIDIADO);
                    bean.getObjeto().setContrato(bean.getObjeto().getCntContratoSede().getCntContrato().getContrato());
                }
            }
        }
    }

    @Override
    public void rechazar(CmFeRipsCargaBean bean) {
        try {
            bean.getObjeto().setEstado(CmRipsCarga.ESTADO_RECHAZADO);
        } catch (Exception ex) {
            bean.addError(obtenerErrorStrFormateado(ex));
        }
    }

    @Override
    public void listarPrestadoresYSedes(CmFeRipsCargaBean bean) {
        try {
            if (ID_PRESTADOR_SAVIA !=  bean.getConexion().getEmpresa().getId() ) {
                if (bean.getConexion().getEmpresa().getCntPrestador() == null) {
                    bean.addError("Su usuario no se encuentra asociado a un prestador");
                } else {
                    bean.getParamConsultaPrestador().setParametroConsulta5(bean.getConexion().getEmpresa().getCntPrestador().getId());
                }
            }
            if (bean.isError() == false) {
                bean.getParamConsultaPrestador().setCantidadRegistros(getCntPrestadorRemoto().consultarCantidadListaSede(bean.getParamConsultaPrestador()));
                bean.setListaPrestadorSedes(getCntPrestadorRemoto().consultarListaSede(bean.getParamConsultaPrestador()));
            }
        } catch (Exception ex) {
            bean.addError(obtenerErrorStrFormateado(ex));
        }
    }

    private String crearArchivo(CmFeRipsCargaAdjunto adjunto) {
        String respuesta;
        try {
            copyInputStreamToFile(adjunto.getInputStream(), new File(adjunto.getArchivoRuta(), adjunto.getArchivo()));
            respuesta = "Rips guardados";
        } catch (IOException ex) {
            respuesta = "Error: " + obtenerErrorStrFormateado(ex);
        } finally {
            adjunto.setInputStream(null);
        }
        return respuesta;
    }

    private static void copyInputStreamToFile(InputStream inputStream, File file)
            throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }
    }

    private void editar(CmFeRipsCargaBean bean) {
        try {
            CmRipsCarga carga = getCmRipsCargaRemoto().consultar(bean.getObjeto().getId());
            if (carga.getEstado() == CmRipsCarga.ESTADO_VALIDADO) {
                //bean.setObjeto(carga);
                bean.setObjetoPrestador(carga.getGnPrestadorSede().getCntPrestador());
                bean.setObjetoPrestadorSede(carga.getGnPrestadorSede());
            } else {
                bean.addError("Esta carga no se encuentra en estado VALIDADO, por favor refresque el listado");
            }
        } catch (Exception ex) {
            bean.addError(obtenerErrorStrFormateado(ex));
        }
    }
    



    private void modificar(CmFeRipsCargaBean bean) {
        try {
            //getCmRipsCargaRemoto().actualizarPbsCamaFija(bean.getObjeto());
            bean.addMensaje("Datos de carga actualizados");
        } catch (Exception ex) {
            bean.addError(obtenerErrorStrFormateado(ex));
        }
    }

    private void verAdjuntos(CmFeRipsCargaBean bean) {
        try {
            List<CmFeRipsCargaAdjunto> listaAdjuntos = getCmFeRipsCargaAdjuntoRemoto().consultarTodos(bean.getObjeto().getId());
            if (listaAdjuntos.size() > 0) {
                bean.getObjeto().setCmFeRipsCargaAdjuntos(listaAdjuntos);
            }
        } catch (Exception ex) {
            bean.addError("Error verAdjuntos: " + obtenerErrorStrFormateado(ex));
        }
    }
    
    private void verSoportes(CmFeRipsCargaBean bean) {
        try {
           bean.getParamConsultaSoportes().setCantidadRegistros(getCmFeSoporteRemoto().consultarCantidadLista(bean.getParamConsultaSoportes()));
           bean.setListaSoportes(getCmFeSoporteRemoto().consultarLista(bean.getParamConsultaSoportes()));
        } catch (Exception ex) {
            bean.addError("Error versoportes: " + obtenerErrorStrFormateado(ex));
        }
    }

    private void verRadicadores(CmFeRipsCargaBean bean) {
        try {
            List<CmGrupoUsuario> usuariosFinales = new ArrayList<>();
            int categoria = bean.getObjeto().getCmGrupoRadicacionRelacionado().getCategoria();

            switch (categoria) {
                case CmGrupo.CATEGORIA_PBS:
                case CmGrupo.CATEGORIA_NO_PBS:
                case CmGrupo.CATEGORIA_PGP:
                    CmGrupo grupo = consultarCmGrupo(CmGrupo.TIPO_GRUPO_RADICACION, categoria);

                    if (grupo.getId() == null) {
                        bean.addError(String.format("No se ha encontrado grupo para buscar radicadores de la categoria : %s", CmGrupo.getCategoriaStr(categoria)));
                        return;
                    }
                    usuariosFinales = consultarCmGrupoUsuarios(grupo.getId());
                    if (usuariosFinales.isEmpty()) {
                        bean.addError(String.format("No se ha encontrado radicadores de la categoria %s para hacer la asignacion.", CmGrupo.getCategoriaStr(categoria)));
                        return;
                    }

                    break;

                case COBERTURA_PBS_CONBINADA:
                    List<CmGrupoUsuario> usuariosCombinados = new ArrayList<>();
                    grupo = consultarCmGrupo(CmGrupo.TIPO_GRUPO_RADICACION, CmGrupo.CATEGORIA_PBS);
                    if (grupo.getId() != null) {
                        List<CmGrupoUsuario> usuariosPbs = consultarCmGrupoUsuarios(grupo.getId());
                        if (!usuariosPbs.isEmpty()) {
                            usuariosPbs = asignarPostfijoUsuarioSegunGrupo(usuariosPbs, CmGrupo.CATEGORIA_PBS);
                            usuariosCombinados.addAll(usuariosPbs);
                        }
                    }

                    grupo = consultarCmGrupo(CmGrupo.TIPO_GRUPO_RADICACION, CmGrupo.CATEGORIA_NO_PBS);
                    if (grupo.getId() != null) {
                        List<CmGrupoUsuario> usuariosNoPbs = consultarCmGrupoUsuarios(grupo.getId());
                        if (!usuariosNoPbs.isEmpty()) {
                            usuariosNoPbs = asignarPostfijoUsuarioSegunGrupo(usuariosNoPbs, CmGrupo.CATEGORIA_NO_PBS);
                            usuariosCombinados.addAll(usuariosNoPbs);
                        }
                        
                    }
               
                    if (usuariosCombinados.isEmpty()) {
                        bean.addError("No se ha encontrado usuarios Pbs y No pbs");
                        return;
                    }
                    
                    usuariosFinales = usuariosCombinados;
                    
                    break;
            }

           
            bean.setRadicadores(usuariosFinales);
        } catch (Exception ex) {
            bean.addError("Error verRadicadores: " + obtenerErrorStrFormateado(ex));
        }
    }

    private void asignarCntContratoCompleto(CmFeRipsCargaBean bean) throws Exception {
        CntContrato contrato = Optional.ofNullable(bean.getObjeto().getCntContrato()).orElse(new CntContrato());
        if (contrato.getId() != null && contrato.getId() > 0) {
            contrato = getContratoRemoto().consultar(bean.getObjeto().getCntContrato().getId());
            bean.getObjeto().setCntContrato(contrato);
        }
    }
       
    private String insertarArchivoJsonContenido(InputStream anexoStream, CmFeRipsCarga carga, String nombreArchivo) { 
  
        try {
            
            byte[] buffer = anexoStream.readAllBytes();
            try (InputStream inputStreamGuardar = new ByteArrayInputStream(buffer);
                 InputStream inputStreamDetectar = new ByteArrayInputStream(buffer)) {

                String valorGuardarJsonToString = new BufferedReader(new InputStreamReader(inputStreamGuardar,
                        CmUtilidades.getCharset(inputStreamDetectar))).lines().collect(Collectors.joining(System.lineSeparator()));
                valorGuardarJsonToString = CmUtilidades.filtroContenidoJson(valorGuardarJsonToString);
                CmFeRipsCargaContenido obj = new CmFeRipsCargaContenido();
                obj.setCmFeRipsCarga(new CmFeRipsCarga(carga.getId()));
                obj.setTipo(CmFeRipsCargaContenido.TIPO_JSON);
                if (CmUtilidades.esArchivoJsonCuv(nombreArchivo)) {
                    valorGuardarJsonToString = filtrarProcesoIdNumerosIzquierda(valorGuardarJsonToString);
                    obj.setCuvJson(valorGuardarJsonToString);
                } else {
                    obj.setJson(valorGuardarJsonToString);
                }
                obj.setUsuarioCrea(carga.getUsuarioCrea());
                obj.setTerminalCrea(carga.getTerminalCrea());
                obj.setFechaHoraCrea(carga.getFechaHoraCrea());
                getCmFeRipsCargaServicioRemoto().insertarCmFeCargaContenido(obj);
                return "";
            }
        } catch (Exception ex) {
            return String.format("Error en insertarArchivoJsonContenido carga : %s el item : %s  ", obtenerErrorStrFormateado(ex), carga.getFacturaNumero());
        }
    }

    private String insertarArchivoXmlContenido(InputStream anexoStream, CmFeRipsCarga carga) {
        try {

            byte[] buffer = anexoStream.readAllBytes();
            try (InputStream inputStreamGuardar = new ByteArrayInputStream(buffer);
                    InputStream inputStreamDetectar = new ByteArrayInputStream(buffer)) {

                String valorGuardarXmlToString = new BufferedReader(new InputStreamReader(inputStreamGuardar,
                        CmUtilidades.getCharset(inputStreamDetectar))).lines().collect(Collectors.joining(System.lineSeparator()));
                valorGuardarXmlToString = CmUtilidades.filtroContenidoXml(valorGuardarXmlToString,buffer);
                CmFeRipsCargaContenido obj = new CmFeRipsCargaContenido();
                obj.setCmFeRipsCarga(new CmFeRipsCarga(carga.getId()));
                obj.setTipo(CmFeRipsCargaContenido.TIPO_XML);
                obj.setXml(valorGuardarXmlToString);
                obj.setUsuarioCrea(carga.getUsuarioCrea());
                obj.setTerminalCrea(carga.getTerminalCrea());
                obj.setFechaHoraCrea(carga.getFechaHoraCrea());
                getCmFeRipsCargaServicioRemoto().insertarCmFeCargaContenido(obj);
                return "";
            }
        } catch (Exception ex) {
            return String.format("Error insertarArchivoXmlContenido carga : %s el item : %s  ", obtenerErrorStrFormateado(ex), carga.getFacturaNumero());
        }
    }
     
    private String insertarArchivoTxtContenido(InputStream anexoStream, CmFeRipsCarga carga) {
        try {
            CmFeRipsCargaContenido obj = new CmFeRipsCargaContenido();
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(anexoStream, StandardCharsets.UTF_8));
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append(System.lineSeparator());
                }
            } catch (Exception ex) {
                return String.format("Error al leer txt para insertar en CmFeCargaContenido : %s el item : %s  ", obtenerErrorStrFormateado(ex), carga.getFacturaNumero());
            }
            obj.setCmFeRipsCarga(new CmFeRipsCarga(carga.getId()));
            obj.setTipo(CmFeRipsCargaContenido.TIPO_TXT);
            obj.setCuv(stringBuilder.toString());
            obj.setUsuarioCrea(carga.getUsuarioCrea());
            obj.setTerminalCrea(carga.getTerminalCrea());
            obj.setFechaHoraCrea(carga.getFechaHoraCrea());
            getCmFeRipsCargaServicioRemoto().insertarCmFeCargaContenido(obj);
            return "";
        } catch (Exception ex) {
            return String.format("Error insertarArchivoTxtContenido carga : %s el item : %s  ", obtenerErrorStrFormateado(ex), carga.getFacturaNumero());
        }
    }

    private String obtenerValorEnTagDescripcion(InputStream anexoStream, String subtag) throws UnsupportedEncodingException,
            ParserConfigurationException, SAXException, IOException, XMLStreamException {

        String valorTag = "";
        Document documento = CmUtilidades.obtenerDocumentoFiltradoXML(anexoStream);  
        Element root = documento.getDocumentElement();
        NodeList descriptionList = root.getElementsByTagName(TAG_PADRE);

        if (descriptionList.getLength() > 0) {
            Node descriptionNode = descriptionList.item(0);
            String descriptionContent = descriptionNode.getTextContent();
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(descriptionContent));
            String elementName = "";
            while (reader.hasNext()) {
                int event = reader.next();

                if (event == XMLStreamConstants.START_ELEMENT) {
                    elementName = reader.getLocalName();
                } else if (event == XMLStreamConstants.CHARACTERS && subtag.equals(elementName)) {
                    valorTag = reader.getText();
                    break;
                }
            }
        }

        return valorTag;
    }
    
    private String obtenerValorFacturaLegalMonetary(InputStream anexoStream, String subtag) throws UnsupportedEncodingException,
            ParserConfigurationException, SAXException, IOException, XMLStreamException, XPathExpressionException {
        // Configurar el parser con soporte para namespaces
        byte[] buffer = anexoStream.readAllBytes();
        InputStream inputStreamcharset = new ByteArrayInputStream(buffer);
        InputStream inputUtilizar = new ByteArrayInputStream(buffer);
        InputStreamReader isr = new InputStreamReader(inputUtilizar, CmUtilidades.getCharset(inputStreamcharset));
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        dbFactory.setNamespaceAware(true);
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        String jsonContent = new BufferedReader(isr).lines().collect(Collectors.joining("\n"));
        jsonContent = CmUtilidades.filtroReconocimientoArchivoXml(jsonContent);
        Document documento = dBuilder.parse(new org.xml.sax.InputSource(new StringReader(jsonContent)));

        // Configurar XPath con manejo de namespaces
        XPath xPath = XPathFactory.newInstance().newXPath();

        // NamespaceContext personalizado
        xPath.setNamespaceContext(new NamespaceContext() {
            @Override
            public String getNamespaceURI(String prefix) {
                switch (prefix) {
                    case "cac":
                        return "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2";
                    case "cbc":
                        return "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2";
                    default:
                        return null;
                }
            }

            @Override
            public String getPrefix(String namespaceURI) {
                return null;
            }

            @Override
            public Iterator<String> getPrefixes(String namespaceURI) {
                return null;
            }
        });

        // Obtener el XML embebido
        String embeddedXml = (String) xPath.evaluate("//cac:Attachment/cac:ExternalReference/cbc:Description/text()",
                documento,
                XPathConstants.STRING
        );

        if (embeddedXml == null || embeddedXml.trim().isEmpty()) {
            return "";
        }

        // Parsear el XML embebido
        Document embeddedDoc = dBuilder.parse(
                new org.xml.sax.InputSource(new StringReader(embeddedXml))
        );

        // Configurar XPath para el documento embebido
        XPath embeddedXPath = XPathFactory.newInstance().newXPath();
        embeddedXPath.setNamespaceContext(new NamespaceContext() {
            @Override
            public String getNamespaceURI(String prefix) {
                switch (prefix) {
                    case "cac":
                        return "urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2";
                    case "cbc":
                        return "urn:oasis:names:specification:ubl:schema:xsd:CommonBasicComponents-2";
                    default:
                        return null;
                }
            }

            @Override
            public String getPrefix(String namespaceURI) {
                return null;
            }

            @Override
            public Iterator<String> getPrefixes(String namespaceURI) {
                return null;
            }
        });

        // Buscar el valor específico
        String expression = "//cac:LegalMonetaryTotal/cbc:" + subtag + "[@currencyID='COP']";
        Node amountNode = (Node) embeddedXPath.evaluate(
                expression,
                embeddedDoc,
                XPathConstants.NODE
        );

        if (amountNode != null) {
            return amountNode.getTextContent();
        }

        return "";  // Valor por defecto si no se encuentra
    }


    private String obtenerValorArchivoCUV(InputStream anexoStream, String campo) throws IOException {

        String valorBuscado = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(anexoStream));
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.toUpperCase().contains(campo.toUpperCase())) {
                valorBuscado = linea.substring(linea.indexOf(":") + 1).trim();
                break;
            }
        }
        return valorBuscado;

    }

    private String obtenerValorArchivoJsonCUV(InputStream anexoStream, String campo) throws IOException, Exception {
        return CmUtilidades.leerDatoArchivoJsonCuv(anexoStream, campo);
    }

    private String obtenerValorCampXmlBase(InputStream anexoStream, String tagName) throws UnsupportedEncodingException,
            ParserConfigurationException, SAXException,
            IOException {
        String valor = "";
        Document documento =  CmUtilidades.obtenerDocumentoFiltradoXML(anexoStream);
        Element root = documento.getDocumentElement();
        NodeList descriptionList = root.getElementsByTagName(tagName);
        if (descriptionList.getLength() > 0) {
            Node descriptionNode = descriptionList.item(0);
            valor = descriptionNode.getTextContent();
        }
        return valor;
    }
    
    private String obtenerDocumentoPertenencia(InputStream anexoStream) throws UnsupportedEncodingException,
            ParserConfigurationException, SAXException, IOException {
        String valor = "";
        Document documento = CmUtilidades.obtenerDocumentoFiltradoXML(anexoStream);  
        documento.getDocumentElement().normalize();

        NodeList receiverPartyList = documento.getElementsByTagName("cac:ReceiverParty");
        for (int i = 0; i < receiverPartyList.getLength(); i++) {

            Element receiverParty = (Element) receiverPartyList.item(i);
            NodeList partyTaxSchemeList = receiverParty.getElementsByTagName("cac:PartyTaxScheme");

            for (int j = 0; j < partyTaxSchemeList.getLength(); j++) {

                Element partyTaxScheme = (Element) partyTaxSchemeList.item(j);
                NodeList companyIDList = partyTaxScheme.getElementsByTagName("cbc:CompanyID");

                if (companyIDList.getLength() > 0) {
                    valor = companyIDList.item(0).getTextContent();
                    return valor;
                }
            }
        }

        return valor;
    }
    
    private String obtenerValorSobreAreaAdicionalXml(InputStream anexoStream, String tagName) throws UnsupportedEncodingException,
            ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        String valor = "";
        
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document documento = CmUtilidades.obtenerDocumentoFiltradoXML(anexoStream);    
        documento.getDocumentElement().normalize();

        NodeList descList = documento.getElementsByTagName(TAG_PADRE);
        for (int i = 0; i < descList.getLength(); i++) {
            Node descNode = descList.item(i);
            if (descNode.getNodeType() == Node.ELEMENT_NODE) {
                Element descElement = (Element) descNode;
                String nestedXML = descElement.getTextContent();

                Document nestedDoc = dBuilder.parse(new InputSource(new StringReader(nestedXML)));
                nestedDoc.getDocumentElement().normalize();

                XPath xPath = XPathFactory.newInstance().newXPath();
                XPathExpression expr = xPath.compile("//*[local-name()='AdditionalInformation'][*[local-name()='Name']='" + tagName + "']/*[local-name()='Value']");
                NodeList valueNodes = (NodeList) expr.evaluate(nestedDoc, XPathConstants.NODESET);

                if (valueNodes.getLength() > 0) {
                    Node valueNode = valueNodes.item(0);
                    valor = valueNode.getTextContent();
                    return valor;
                }
            }
        }

        return valor;
    }   

    private String obtenerValorSchemeIDSobreAreaAdicionalXml(InputStream anexoStream, String TAG_NAME) throws UnsupportedEncodingException,
            ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        String valor = "";
        
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document documento = CmUtilidades.obtenerDocumentoFiltradoXML(anexoStream); 

        documento.getDocumentElement().normalize();

        NodeList descList = documento.getElementsByTagName(TAG_PADRE);
        for (int i = 0; i < descList.getLength(); i++) {
            Node descNode = descList.item(i);
            if (descNode.getNodeType() == Node.ELEMENT_NODE) {
                Element descElement = (Element) descNode;
                String nestedXML = descElement.getTextContent();

                Document nestedDoc = dBuilder.parse(new InputSource(new StringReader(nestedXML)));
                nestedDoc.getDocumentElement().normalize();

                XPath xPath = XPathFactory.newInstance().newXPath();
                XPathExpression expr = xPath.compile("//*[local-name()='AdditionalInformation'][*[local-name()='Name']='" + TAG_NAME + "']/*[local-name()='Value']/@schemeID");
                Node schemeIDNode = (Node) expr.evaluate(nestedDoc, XPathConstants.NODE);
                if (schemeIDNode != null) {
                    valor = schemeIDNode.getNodeValue();
                    return valor;
                }
            }
        }

        return valor;
    }
       
    private String obtenerValorSobreAreaPagosXml(InputStream anexoStream, String codigoTagName) throws UnsupportedEncodingException, 
            ParserConfigurationException, SAXException, IOException, XMLStreamException {
        String valor = "";
        Document documento = CmUtilidades.obtenerDocumentoFiltradoXML(anexoStream);  
        Element root = documento.getDocumentElement();

        NodeList descriptionList = root.getElementsByTagName("cbc:Description");

        if (descriptionList.getLength() > 0) {
            Node descriptionNode = descriptionList.item(0);
            String descriptionContent = descriptionNode.getTextContent();
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(new StringReader(descriptionContent));
            String elementName = "";
            String schemeID = null;
            while (reader.hasNext()) {
                int event = reader.next();

                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        elementName = reader.getLocalName();
                        if ("ID".equals(elementName)) {
                            schemeID = reader.getAttributeValue(null, "schemeID");
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if ("PaidAmount".equals(elementName) && codigoTagName.equals(schemeID)) {
                            valor = reader.getText();
                            return valor;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        elementName = reader.getLocalName();
                        if ("ID".equals(elementName) && !codigoTagName.equals(schemeID)) {
                            schemeID = null;
                        }
                        break;
                }
            }
        }

        return valor;
    }
    
    private String obtenerValorNitPrestadorXml(InputStream anexoStream) throws UnsupportedEncodingException,
            ParserConfigurationException, SAXException, IOException, XMLStreamException {
        
        Document documento = CmUtilidades.obtenerDocumentoFiltradoXML(anexoStream);  
        Element root = documento.getDocumentElement();
        NodeList senderPartyList = root.getElementsByTagName("cac:SenderParty");

        for (int i = 0; i < senderPartyList.getLength(); i++) {
            Node senderPartyNode = senderPartyList.item(i);

            if (senderPartyNode.getNodeType() == Node.ELEMENT_NODE) {
                Element senderPartyElement = (Element) senderPartyNode;

                NodeList partyTaxSchemeList = senderPartyElement.getElementsByTagName("cac:PartyTaxScheme");

                for (int j = 0; j < partyTaxSchemeList.getLength(); j++) {
                    Node partyTaxSchemeNode = partyTaxSchemeList.item(j);

                    if (partyTaxSchemeNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element partyTaxSchemeElement = (Element) partyTaxSchemeNode;
                        Element companyIDElement = (Element) partyTaxSchemeElement.getElementsByTagName("cbc:CompanyID").item(0);
                        return  companyIDElement.getTextContent();

                    }
                }
            }
        }
        return "";
    }
     
     
    private List<Maestro> consultarMotivosDevolucionManual(String accionMaestro) throws Exception {
        List<String> motivosDevolucionManual = Arrays.asList("DE4401", "DE4402", "DE5601");
        List<Maestro> maestros =  getMaestroRemoto().consultarPorTipo(accionMaestro);
                                  maestros = (maestros !=null && !maestros.isEmpty()) ?
        maestros.stream().filter(maestro-> maestro.isActivo() && motivosDevolucionManual.contains( maestro.getValor()) ).collect(Collectors.toList()): new ArrayList<>();
        return maestros;
    }
    
    private String asignarTecnicoRadicador(CmFeRipsCarga carga) {
        try {
            
            if(CmFeRipsCarga.TIPO_CARGA_FACTURA != carga.getTipo()){
               return ""; 
            }
            
            int categoria;
            if (ID_MODALIDAD_CONTRATO_PGP == carga.getMaeContratoModalidadId()) {
                categoria = CmGrupo.CATEGORIA_PGP;
            } else {
                categoria = carga.getCobertura() ? CmGrupo.CATEGORIA_NO_PBS : CmGrupo.CATEGORIA_PBS;
            }

            CmGrupo grupo = consultarCmGrupo(CmGrupo.TIPO_GRUPO_RADICACION, categoria);      
            if(grupo.getId() == null){
                return "";
            }
             
            List<CmGrupoUsuario> usuarios = consultarCmGrupoUsuarios(grupo.getId());           
            if (usuarios.isEmpty()) {
                return "";
            }
            
            int ultimoTecnicoAsignado = Optional.ofNullable(grupo.getUsuariosIdAsignacion()).orElse(-1);
            ultimoTecnicoAsignado = (ultimoTecnicoAsignado + 1) % usuarios.size();

            CmGrupoUsuario usuarioAsignar = usuarios.get(ultimoTecnicoAsignado);
            if (usuarioAsignar == null) {
                  return "";
            }
            carga.setRadicadorAsignado(new Usuario(usuarioAsignar.getGnUsuario().getId()));
            grupo.setUsuariosIdAsignacion(ultimoTecnicoAsignado);
            carga.setCmGrupoRadicacionRelacionado(grupo);
            
        } catch (Exception ex) {
            return "";
        }

        return "";
    }

    private List<CmGrupoUsuario> consultarCmGrupoUsuarios(Integer idCmGrupo) throws Exception {
        List<CmGrupoUsuario> usuarios = null;
        try {
            ParamConsulta paramConsultaUsuarios = new ParamConsulta();
            paramConsultaUsuarios.setParametroConsulta1(true);
            paramConsultaUsuarios.setParametroConsulta2(idCmGrupo);
            usuarios = getCmGrupoUsuarioRemoto().consultarListaParametrizada(paramConsultaUsuarios);
        } catch (Exception ex) {
            throw new Exception("Error consultarCmGrupoUsuarios: " + obtenerErrorStrFormateado(ex));
        }
        return usuarios;
    }

    private CmGrupo consultarCmGrupo(boolean tipoGrupo, int categoria) throws Exception {
        CmGrupo grupo = null;
        try {
            ParamConsulta paramConsultaGrupo = new ParamConsulta();
            paramConsultaGrupo.setParametroConsulta1(true);
            paramConsultaGrupo.setParametroConsulta2(tipoGrupo);
            paramConsultaGrupo.setParametroConsulta3(categoria);
            grupo = getCmGrupoRemoto().consultarConParametrizacion(paramConsultaGrupo);
        } catch (Exception ex) {
            throw new Exception("Error consultarCmGrupo: " + obtenerErrorStrFormateado(ex));
        }
        return grupo;
    }
    
    private boolean validarValorDocumento(FeDocumento feDocumento,  CmFeRipsCargaBean bean) throws Exception {
        boolean esValido= false;
        CmFeRipsCarga carga = bean.getObjeto();
        
        if (feDocumento.getId() != null) {
            BigDecimal valorFeDocumento = feDocumento.getDocumentoValor();
            BigDecimal valorCarga = carga.getDocumentoValor();
            int scale = Math.min(valorFeDocumento.scale(), valorCarga.scale());
            valorFeDocumento = valorFeDocumento.setScale(scale, RoundingMode.HALF_UP);
            valorCarga = valorCarga.setScale(scale, RoundingMode.HALF_UP);
            esValido = true;
            
            if (valorFeDocumento.compareTo(valorCarga) != 0) {               
                bean.addError("Para el número de documento : " + carga.getDocumentoNumero()
                        + " de valor :" + valorCarga + ", no corresponde con el valor encontrado:"
                        + valorFeDocumento);
                esValido = false;
            }
        }
        
        return esValido;
   }
    
    private String asignarModalidadContrato(String codigoModalidad, CmFeRipsCarga carga) {
        Optional<Maestro> modalidadContrato = getHashMapModalidadContratos().entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(maestro -> maestro.getValor().equals(codigoModalidad))
                .findFirst();

        if (modalidadContrato.isPresent()) {
            Maestro mae = modalidadContrato.get();
            carga.setMaeContratoModalidadCodigo(mae.getValor());
            carga.setMaeContratoModalidadValor(mae.getNombre());
            carga.setMaeContratoModalidadId(mae.getId());
            return "";
        } else {
            return String.format("No se encontró la modalidad código (%s) en el maestro de contratos", codigoModalidad);
        }
    }
   
    private String asignarCobertura(String codigoCobertura, CmFeRipsCarga carga,CmFeRipsCargaAdjunto anexoXML) {
        Boolean cobertura = getCodigosCoberturaMap().get(codigoCobertura);
        if (cobertura == null) {
            return String.format("El código de cobertura %s no está definido en el sistema: %s", codigoCobertura, anexoXML.getArchivoNombre());
        }
        carga.setCobertura(cobertura);
        return "";
    }
    
    private String obtenerValorCampoXMLBase(CmFeRipsCargaAdjunto anexoXML, String tag, String nombreArchivo) {
        try (InputStream inputUtilizar = obtenerCopiaInputStream(anexoXML)) {
            return obtenerValorCampXmlBase(inputUtilizar, tag);
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            return String.format("Error tratar de obtener valor del tag ( %s ) : error : %s , archivo : ", tag, obtenerErrorStrFormateado(ex), nombreArchivo);
        }
    }
   
    private String asignarFormatoFechaEmision(String fechaHora, String nombreArchivo, CmFeRipsCarga carga) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            carga.setFechaHoraEmision(formatter.parse(fechaHora));
            return "";
        } catch (ParseException ex) {
             return String.format("Error tratar de formatear la fecha emision encontrada ( %s ) : error : %s  , archivo : %s", fechaHora, obtenerErrorStrFormateado(ex), nombreArchivo);
        }
    }
    
    private String asignarFormatoFechaMinisterios(String fechaHora, String nombreArchivo, CmFeRipsCarga carga) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd 'de' MMMM 'de' yyyy HH:mm:ss", new Locale("es", "ES"));
            Date fechaHoraMinisterio = formatter.parse(fechaHora);
            carga.setFechaHoraMinisterio(fechaHoraMinisterio);
            return "";
        } catch (ParseException ex) {
            return String.format("Error tratar de formatear la fecha  ministerio encontrada ( %s ) : error : %s  , archivo : %s ", fechaHora, obtenerErrorStrFormateado(ex), nombreArchivo);
        }
    }
    
    private String asignarFormatoFechaMinisteriosJSON(String fechaHora, String nombreArchivo, CmFeRipsCarga carga) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date fechaHoraMinisterio = formatter.parse(fechaHora);
            carga.setFechaHoraMinisterio(fechaHoraMinisterio);
            return "";
        } catch (ParseException ex) {
            return String.format("Error tratar de formatear la fecha  ministerio encontrada ( %s ) : error : %s  , archivo : %s ", fechaHora, obtenerErrorStrFormateado(ex), nombreArchivo);
        }
    }
    
     private void procesarAnexo(CmFeRipsCarga carga, String extension, 
                              BiFunction<CmFeRipsCarga, CmFeRipsCargaAdjunto, Optional<CmFeAdjuntoErrores>> funcionAsignacion, 
                              String llave, List<CmFeAdjuntoErrores> erroresTotalesArchivoZip) {
        carga.getCmFeRipsCargaAdjuntos().stream()
                .filter(obj -> extension.equals(obj.getExtension()))
                .findFirst()
                .ifPresent(anexo -> agregarErroresSiPresente(funcionAsignacion.apply(carga, anexo), llave, erroresTotalesArchivoZip));
    }
     
    private void procesarAnexoJSONCuv(CmFeRipsCarga carga, String extension, 
                              BiFunction<CmFeRipsCarga, CmFeRipsCargaAdjunto, Optional<CmFeAdjuntoErrores>> funcionAsignacion, 
                              String llave, List<CmFeAdjuntoErrores> erroresTotalesArchivoZip) {
     carga.getCmFeRipsCargaAdjuntos().stream()
                .filter(obj -> extension.equals(obj.getExtension()))
                .filter(obj -> CmUtilidades.esArchivoJsonCuv(obj.getArchivoNombre())) 
                .findFirst()
                .ifPresent(anexo -> agregarErroresSiPresente(funcionAsignacion.apply(carga, anexo), llave, erroresTotalesArchivoZip));
    }
        
     private void agregarErroresSiPresente(Optional<CmFeAdjuntoErrores> errores, String llave, List<CmFeAdjuntoErrores> erroresTotalesArchivoZip) {
        errores.ifPresent(error -> {
            error.setNombre(llave);
            erroresTotalesArchivoZip.add(error);
        });
    }
     
    private void manejarMensajeErrorMasivo(CmFeRipsCargaBean bean, List<CmFeAdjuntoErrores> erroresTotalesArchivoZip) {
        bean.setListaErroresProcesamiento(erroresTotalesArchivoZip);
        bean.addError("Se han producido errores al procesar la carga  zip");
    }
    
     private void manejarErrorIndividual(CmFeRipsCargaBean bean, String errorDescripcion) {
        bean.addError(errorDescripcion);
    }
     
    private void procesarReglasPreInsercion(CmFeRipsCarga cmFeCarga) {
        if (cmFeCarga.getEsTipoCargaNota()) {
            cmFeCarga.setFacturaNumero("");
        }
    }
   
    private void procesarInsercion(Supplier<String> funcionInsercion, String nombreError, List<CmFeAdjuntoErrores> errores) {

        if (!errores.isEmpty()) {
            return; // Salir si ya hay errores
        }

        String mensajeError = funcionInsercion.get(); // Ejecuta la operación
        if (!mensajeError.isEmpty()) {
            errores.add(new CmFeAdjuntoErrores(nombreError, mensajeError));
        }
    }
    
     private void procesarInsercionFallo(Supplier<String> funcionInsercion, String nombreError, List<CmFeAdjuntoErrores> errores) {

        if (errores.isEmpty()) {
            return; // Salir si no hay errores
        }

        String mensajeError = funcionInsercion.get(); // Ejecuta la operación
        if (!mensajeError.isEmpty()) {
            errores.add(new CmFeAdjuntoErrores(nombreError, mensajeError));
        }
    }

    private void procesarInsercionLista(Supplier<List<CmFeAdjuntoErrores>> funcionInsercion, List<CmFeAdjuntoErrores> errores) {
    
        if (!errores.isEmpty()) {
            return; // Salir si ya hay errores
        }
        
        List<CmFeAdjuntoErrores> erroresInsercion = funcionInsercion.get(); // Ejecuta la operación
        if (!erroresInsercion.isEmpty()) {
            errores.addAll(erroresInsercion);
        }
    }
    
    private void modificarErrorInsercionSiExiste(List<CmFeAdjuntoErrores> errores) {
        if (!errores.isEmpty()) {
            CmFeAdjuntoErrores primerError = errores.get(0);
            primerError.setDescripcion("Error al momento de insertar datos de carga : " + primerError.getDescripcion());
        }
    }
    
    private String obtenerErrorStrFormateado(Exception excepcion) {
        return Optional.ofNullable(excepcion.getMessage()).orElse(excepcion.toString());
    }
    
    private String filtrarProcesoIdNumerosIzquierda(String valorGuardarJsonToString) {
        String regex = "\"procesoId\":\\s*(\\d+)";
        valorGuardarJsonToString = valorGuardarJsonToString.replaceAll(regex, "\"procesoId\": \"$1\"");
        regex = "\"ProcesoId\":\\s*(\\d+)";
        valorGuardarJsonToString = valorGuardarJsonToString.replaceAll(regex, "\"ProcesoId\": \"$1\"");
        return valorGuardarJsonToString;
    } 
    
    private boolean validarCargaSinXML(CmFeRipsCarga carga) {
        return (CmFeRipsCarga.TIPO_CARGA_NOTA_AJUSTE == carga.getTipo() || 
                CmFeRipsCarga.TIPO_CARGA_CAPITA_FINAL == carga.getTipo());
    }
    
    private String obtenerNumeroNotaDeNombreArchivo(CmFeRipsCarga carga, String extension) {
        CmFeRipsCargaAdjunto adjunto = carga.getCmFeRipsCargaAdjuntos().stream()
                .filter(obj -> extension.equals(obj.getExtension()))
                .filter(obj -> CmUtilidades.esArchivoJsonRips(obj.getArchivoNombre()))
                .findAny().orElse(new CmFeRipsCargaAdjunto());
        String numeroFactura = Optional.ofNullable(adjunto.getArchivoNombre()).orElse("");
        return CmUtilidades.obtenerNumeroNotaDeNombreArchivo(numeroFactura);
    }
    
    private List<CmGrupoUsuario> asignarPostfijoUsuarioSegunGrupo(List<CmGrupoUsuario> usuarios, int categoria) {
        usuarios = usuarios.stream()
                .peek(usuario -> {
                    String nombreOriginal = usuario.getGnUsuario().getNombre();
                    usuario.getGnUsuario().setNombre(nombreOriginal + " - " + CmGrupo.getCategoriaStr(categoria));
                })
                .collect(Collectors.toList());
        return usuarios;
    }
}