package com.saviasaludeps.savia.dominio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.contratacion.CntContrato;
import com.saviasaludeps.savia.dominio.contratacion.CntContratoSede;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.cuentamedica.auditoria.CmGrupo;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class CmFeRipsCarga extends Auditoria implements Cloneable {

    public static final int TIPO_CARGA_FACTURA = 0;
    public static final int TIPO_CARGA_NOTA_CREDITO_PARCIAL = 1;
    public static final int TIPO_CARGA_NOTA_CREDITO_TOTAL  = 2;
    public static final int TIPO_CARGA_NOTA_AJUSTE = 3;
    public static final int TIPO_CARGA_NOTA_CREDITO_ACUERDO_VOLUNTAD = 4;
    public static final int TIPO_CARGA_NOTA_DEBITO = 5;
    public static final int TIPO_CARGA_CAPITA_INICIAL = 6;
    public static final int TIPO_CARGA_CAPITA_FINAL = 7;
    public static final int TIPO_CARGA_NOTA_CREDITO_CAPITA = 8;
    public static final int TIPO_CARGA_CAPITA_PERIODO = 9;
  
    public static final int ESTADO_EN_COLA = 0;
    public static final int ESTADO_EN_PROCESO = 1;
    public static final int ESTADO_VALIDACION_AUTOMATICA_ERROR = 2;
    public static final int ESTADO_VALIDACION_AUTOMATICA_OK = 3;
    public static final int ESTADO_VALIDACION_MINISTERIO_ERROR = 4;
    public static final int ESTADO_VALIDACION_PROCESO = 5;
    
    public static final int ESTADO_EN_COLA_AUDITORIA = 6;
    public static final int ESTADO_ENVIO_AUDITORIA_PROCESO = 7;
    public static final int ESTADO_ENVIO_AUDITORIA_ERROR = 8;
    public static final int ESTADO_ENVIO_AUDITORIA_OK = 9;
    
    public static final int ESTADO_SIN_RESPUESTA_SAP   = 10;
    public static final int ESTADO_RECHAZADO   = 11;
    public static final int ESTADO_DEVUELTO   = 12;
    public static final int ESTADO_REGISTRADA = 13;
    public static final int ESTADO_VALIDACION_NOTA_ERROR_MINISTERIO = 14;
    public static final int ESTADO_NOTA_MINISTERIO_OK = 15;
    public static final int ESTADO_VALIDACION_NOTA_ERROR_FE = 16;
    public static final int ESTADO_NOTA_REGISTRO_EXITOSO= 17;
    public static final int ESTADO_ERROR_CARGA = 18;
    
    public static final int ORIGEN_CONEXIONES = 0;
    public static final int ORIGEN_WEB_SERVICE = 1;
    
    public static final int ORIGEN_CARGA_MANUAL = 0;
    public static final int ORIGEN_CARGA_MASIVA = 1;
    
    public static final boolean COBERTURA_PBS = false;
    public static final boolean COBERTURA_NO_PBS = true;
    
    private Integer id;
    private Integer tipo;
    private String contrato;
    private int cntTipoContratoId;
    private int estado;
    private Integer maeRegimenId;
    private String maeRegimenCodigo;
    private String maeRegimenValor;
    private Integer maeContratoModalidadId;
    private String maeContratoModalidadCodigo;
    private String maeContratoModalidadValor;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private Integer tiempo;
    private Boolean cobertura;
    private String facturaNumero;
    private String facturaNumeroPeriodoAsociado;
    private String documentoNumero;
    private BigDecimal facturaValor;
    private int soportesNumero;
    private BigDecimal documentoValor;
    private BigDecimal valorNota;
    private BigDecimal valorCopago;
    private BigDecimal valorCuotaModeradora;
    private boolean rechazo;
    private Date fechaHoraRechazo;
    private String observacionRechazo;
    private boolean devolucion;
    private Date fechaHoraDevolucion;
    private Integer maeDevolucionId;
    private String maeDevolucionCodigo;
    private String maeDevolucionValor;
    private String observacionDevolucion;
    private String observacion;
    private String documentoPrestador;
    private String documentoPrestadorXml;
    private String cuv;
    private String cufe;
    private String cude;
    private Boolean de1601EpsErronea;
    private Boolean de4401ProfesionalRed;
    private Boolean de4402ProfesionalIndependiente;
    private Boolean de5001Pagada;
    private Boolean de5002Radicada;
    private Boolean de5601SoporteFe;
    private Boolean de5601Soportes;
    private Date fechaHoraEmision;
    private Date fechaHoraMinisterio;
    private CntContrato cntContrato;
    private CntPrestadorSede cntPrestadorSede;
    private Empresa empresa;
    private List<CmFeRipsCargaAdjunto> cmFeRipsCargaAdjuntos = new ArrayList<>();
    private List<CmFeRipsCargaContenido> cmFeRipsCargaContenidos = new ArrayList<>();
    private Boolean pbs;
    private CntPrestadorSede gnPrestadorSede;
    private CntContratoSede cntContratoSede;
    private Integer numeroCuenta;
    private String numeroNota;
    private String numeroFacturaAsociadoNota;
    private int origenCarga;
    private String urlXml;
    private String urlJson;
    private boolean tieneTransacciones;
    private String documentoPertinencia;
    private Integer idCmFacturaAsociadaNota;
    private Integer origen;
    private Integer tipoReglaCarga;
    private Integer capitaPeriodo;
    private CmFeRipsCarga cargaPeriodo;
    private CmFeRipsCarga cargaPeriodoAnterior;
    private Boolean multiusuario;
    private Usuario radicadorAsignado = new Usuario();
    private CmGrupo cmGrupoRadicacionRelacionado = new CmGrupo();
    
    public CmFeRipsCarga() {

    }

    public CmFeRipsCarga(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CntContrato getCntContrato() {
        if (cntContrato == null) {
            cntContrato = new CntContrato();
        }
        return cntContrato;
    }

    public void setCntContrato(CntContrato cntContrato) {
        this.cntContrato = cntContrato;
    }

    public int getCntTipoContratoId() {
        return cntTipoContratoId;
    }

    public void setCntTipoContratoId(int cntTipoContratoId) {
        this.cntTipoContratoId = cntTipoContratoId;
    }

 

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }


    public String getMaeRegimenCodigo() {
        return maeRegimenCodigo;
    }

    public void setMaeRegimenCodigo(String maeRegimenCodigo) {
        this.maeRegimenCodigo = maeRegimenCodigo;
    }

    public Integer getMaeRegimenId() {
        return maeRegimenId;
    }

    public void setMaeRegimenId(Integer maeRegimenId) {
        this.maeRegimenId = maeRegimenId;
    }

    public String getMaeRegimenValor() {
        return maeRegimenValor;
    }

    public void setMaeRegimenValor(String maeRegimenValor) {
        this.maeRegimenValor = maeRegimenValor;
    }

    public String getMaeContratoModalidadCodigo() {
        return maeContratoModalidadCodigo;
    }

    public void setMaeContratoModalidadCodigo(String maeContratoModalidadCodigo) {
        this.maeContratoModalidadCodigo = maeContratoModalidadCodigo;
    }

    public Integer getMaeContratoModalidadId() {
        return maeContratoModalidadId;
    }

    public void setMaeContratoModalidadId(Integer maeContratoModalidadId) {
        this.maeContratoModalidadId = maeContratoModalidadId;
    }

    public String getMaeContratoModalidadValor() {
        return maeContratoModalidadValor;
    }

    public void setMaeContratoModalidadValor(String maeContratoModalidadValor) {
        this.maeContratoModalidadValor = maeContratoModalidadValor;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

   

    public String getObservacionRechazo() {
        return observacionRechazo;
    }

    public void setObservacionRechazo(String observacionRechazo) {
        this.observacionRechazo = observacionRechazo;
    }


    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public int getOrigenCarga() {
        return origenCarga;
    }

    public void setOrigenCarga(int origen) {
        this.origenCarga = origen;
    }
    
    public String getOrigenCargaStr() {
        return CmFeRipsCarga.getOrigenCargaStr(getOrigenCarga());
    }
    
     public static String getOrigenCargaStr( int origen ) {
        String strOrigen;
         switch (origen) {
             case CmFeRipsCarga.ORIGEN_WEB_SERVICE:
                 strOrigen = "Web Service";
                 break;
                case CmFeRipsCarga.ORIGEN_CONEXIONES:
                 strOrigen = "Conexiones";
                 break;
         
             default:
                 strOrigen = "";
                 break;
         }
        return strOrigen;
    }

    
    public String getTipoStr() {
        return CmFeRipsCarga.getTipoStr(getTipo());
    }

    public static String getTipoStr( Integer tipo ) {
        String strEstado;
         tipo = Optional.ofNullable(tipo).orElse(-1);
         switch (tipo) {
             case CmFeRipsCarga.TIPO_CARGA_FACTURA:
                 strEstado = "Factura";
                 break;
                case CmFeRipsCarga.TIPO_CARGA_NOTA_CREDITO_CAPITA:
                 strEstado = "Nota Crédito Capita";
                 break;
             case CmFeRipsCarga.TIPO_CARGA_NOTA_CREDITO_TOTAL:
                 strEstado = "Nota Crédito Total";
                 break;
             case CmFeRipsCarga.TIPO_CARGA_NOTA_CREDITO_PARCIAL:
                 strEstado = "Nota Crédito Parcial";
                 break;
             case CmFeRipsCarga.TIPO_CARGA_NOTA_CREDITO_ACUERDO_VOLUNTAD:
                 strEstado = "Nota Crédito Acuerdo Voluntad";
                 break;
             case CmFeRipsCarga.TIPO_CARGA_NOTA_DEBITO:
                 strEstado = "Nota Débito";
                 break;
              case CmFeRipsCarga.TIPO_CARGA_NOTA_AJUSTE:
                 strEstado = "Nota Ajuste";
                 break;
               case CmFeRipsCarga.TIPO_CARGA_CAPITA_FINAL:
                 strEstado = "Capita Final";
                 break;
             case CmFeRipsCarga.TIPO_CARGA_CAPITA_INICIAL:
                 strEstado = "Capita Inicial";
                 break;
             case CmFeRipsCarga.TIPO_CARGA_CAPITA_PERIODO:
                 strEstado = "Capita por Período";
                 break;
             default:
                 strEstado = "";
                 break;
         }
        return strEstado;
    }
    
    public String getOrigenStr() {
        return CmFeRipsCarga.getOrigenStr(getOrigen());
    }
        
    public static String getOrigenStr( Integer origen ) {
        String strOrigen;
        origen = Optional.ofNullable(origen).orElse(-1);
        switch (origen) {
            case CmFeRipsCarga.ORIGEN_CARGA_MANUAL:
                strOrigen = "Manual";
                break;
            case CmFeRipsCarga.ORIGEN_CARGA_MASIVA:
                strOrigen = "Masivo";
                break;
            default:
                strOrigen = "";
                break;
        }
        return strOrigen;
    }

    public String getEstadoStr() {
        return CmFeRipsCarga.getEstadoStr(getEstado());
    }
    
    public static String getEstadoStr(int estado) {
        String strEstado;
        switch (estado) {
                     
            case CmFeRipsCarga.ESTADO_EN_COLA:
                strEstado = "En Cola";
                break;           
            case CmFeRipsCarga.ESTADO_EN_PROCESO:
                strEstado = "En Proceso";
                break;
            case CmFeRipsCarga.ESTADO_VALIDACION_AUTOMATICA_ERROR:
                strEstado = "Validación Automática Error";
                break;
            case CmFeRipsCarga.ESTADO_VALIDACION_AUTOMATICA_OK:
                strEstado = "Validación Automática OK";
                break;
            case CmFeRipsCarga.ESTADO_VALIDACION_MINISTERIO_ERROR:
                strEstado = "Validación Ministerio Error";
                break;    
            case CmFeRipsCarga.ESTADO_VALIDACION_PROCESO:
                strEstado = "Validación Proceso";
                break;    
            case CmFeRipsCarga.ESTADO_EN_COLA_AUDITORIA:
                strEstado = "En Cola Auditoría";
                break;
            case CmFeRipsCarga.ESTADO_ENVIO_AUDITORIA_PROCESO:
                strEstado = "Envío Auditoría Proceso";
                break; 
            case CmFeRipsCarga.ESTADO_ENVIO_AUDITORIA_ERROR:
                strEstado = "Envío Auditoría Error";
                break;  
            case CmFeRipsCarga.ESTADO_ENVIO_AUDITORIA_OK:
                strEstado = "Envío Auditoría OK";
                break;     
            case CmFeRipsCarga.ESTADO_SIN_RESPUESTA_SAP:
                strEstado = "Error sin respuesta sap";
                break;
            case CmFeRipsCarga.ESTADO_DEVUELTO:
                strEstado = "Devuelto";
                break;
            case CmFeRipsCarga.ESTADO_RECHAZADO:
                strEstado = "Rechazado";
                break;
            case CmFeRipsCarga.ESTADO_REGISTRADA:
                strEstado = "Registrada";
                break;
            case CmFeRipsCarga.ESTADO_VALIDACION_NOTA_ERROR_MINISTERIO:
                strEstado = "Validacion Nota Error Ministerio";
                break;    
            case CmFeRipsCarga.ESTADO_NOTA_MINISTERIO_OK:
                strEstado = "Nota Ministerio Ok";
                break; 
            case CmFeRipsCarga.ESTADO_VALIDACION_NOTA_ERROR_FE:
                strEstado = " Validación Nota Error FE";
                break; 
            case CmFeRipsCarga.ESTADO_NOTA_REGISTRO_EXITOSO:
                strEstado = "Nota Registro Exitoso";
                break;
            case CmFeRipsCarga.ESTADO_ERROR_CARGA:
                strEstado = "Error de carga";
                break; 
            default:
                strEstado = "";
                break;
        }
        return strEstado;
    }



    public Boolean getPbs() {
        return pbs;
    }

    public void setPbs(Boolean pbs) {
        this.pbs = pbs;
    }

    public Integer getCoberturaCierre() {
        return getPbs() ? CmRipsInhabilitado.COBERTURA_CIERRE_PBS : 
               CmRipsInhabilitado.COBERTURA_CIERRE_NO_PBS;
    }

    public String getPbsStr() {
        return pbs != null && pbs ? "Si" : "No";
    }
    
    public String getCoberturaStr() {
        return cobertura  != null && cobertura ? "NO PBS " : "PBS";
    }
    
    public String getMultiusuarioStr(){
      return multiusuario == null || !multiusuario ? "No": "Si";
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public CntPrestadorSede getCntPrestadorSede() {
        return cntPrestadorSede;
    }

    public void setCntPrestadorSede(CntPrestadorSede cntPrestadorSede) {
        this.cntPrestadorSede = cntPrestadorSede;
    }

    public List<CmFeRipsCargaAdjunto> getCmFeRipsCargaAdjuntos() {
        return cmFeRipsCargaAdjuntos;
    }

    public void setCmFeRipsCargaAdjuntos(List<CmFeRipsCargaAdjunto> cmFeRipsCargaAdjuntos) {
        this.cmFeRipsCargaAdjuntos = cmFeRipsCargaAdjuntos;
    }

    public List<CmFeRipsCargaContenido> getCmFeRipsCargaContenidos() {
        return cmFeRipsCargaContenidos;
    }

    public void setCmFeRipsCargaContenidos(List<CmFeRipsCargaContenido> cmFeRipsCargaContenidos) {
        this.cmFeRipsCargaContenidos = cmFeRipsCargaContenidos;
    }

    public Boolean getCobertura() {
        return cobertura;
    }

    public void setCobertura(Boolean cobertura) {
        this.cobertura = cobertura;
    }

    public String getFacturaNumero() {
        return facturaNumero;
    }

    public void setFacturaNumero(String facturaNumero) {
        this.facturaNumero = facturaNumero;
    }

    public String getFacturaNumeroPeriodoAsociado() {
        return facturaNumeroPeriodoAsociado;
    }

    public void setFacturaNumeroPeriodoAsociado(String facturaNumeroPeriodoAsociado) {
        this.facturaNumeroPeriodoAsociado = facturaNumeroPeriodoAsociado;
    }
    

    public BigDecimal getFacturaValor() {
        return facturaValor;
    }

    public void setFacturaValor(BigDecimal facturaValor) {
        this.facturaValor = facturaValor;
    }

    public int getSoportesNumero() {
        return soportesNumero;
    }

    public void setSoportesNumero(int soportesNumero) {
        this.soportesNumero = soportesNumero;
    }

    public Date getFechaHoraRechazo() {
        return fechaHoraRechazo;
    }

    public void setFechaHoraRechazo(Date fechaHoraRechazo) {
        this.fechaHoraRechazo = fechaHoraRechazo;
    }

    public boolean isDevolucion() {
        return devolucion;
    }
    
    public boolean getDevolucion() {
        return devolucion;
    }

    public void setDevolucion(boolean devolucion) {
        this.devolucion = devolucion;
    }

    public Date getFechaHoraDevolucion() {
        return fechaHoraDevolucion;
    }

    public void setFechaHoraDevolucion(Date fechaHoraDevolucion) {
        this.fechaHoraDevolucion = fechaHoraDevolucion;
    }

    public boolean isRechazo() {
        return rechazo;
    }
    
    public boolean hayObservacion() {
        return observacion != null && observacion.length()>5;
    }
    
    
     public boolean getRechazo() {
        return rechazo;
    }

    public void setRechazo(boolean rechazo) {
        this.rechazo = rechazo;
    }

    public Integer getMaeDevolucionId() {
        return maeDevolucionId;
    }

    public void setMaeDevolucionId(Integer maeDevolucionId) {
        this.maeDevolucionId = maeDevolucionId;
    }

    public String getMaeDevolucionCodigo() {
        return maeDevolucionCodigo;
    }

    public void setMaeDevolucionCodigo(String maeDevolucionCodigo) {
        this.maeDevolucionCodigo = maeDevolucionCodigo;
    }

    public String getMaeDevolucionValor() {
        return maeDevolucionValor;
    }

    public void setMaeDevolucionValor(String maeDevolucionValor) {
        this.maeDevolucionValor = maeDevolucionValor;
    }

    public String getObservacionDevolucion() {
        return observacionDevolucion;
    }

    public void setObservacionDevolucion(String observacionDevolucion) {
        this.observacionDevolucion = observacionDevolucion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public CntPrestadorSede getGnPrestadorSede() {
        return gnPrestadorSede;
    }

    public void setGnPrestadorSede(CntPrestadorSede gnPrestadorSede) {
        this.gnPrestadorSede = gnPrestadorSede;
    }

    public CntContratoSede getCntContratoSede() {
        return cntContratoSede;
    }

    public void setCntContratoSede(CntContratoSede cntContratoSede) {
        this.cntContratoSede = cntContratoSede;
    }
	
    public Integer getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(Integer numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getCuv() {
        return cuv;
    }

    public void setCuv(String cuv) {
        this.cuv = cuv;
    }

    public String getCufe() {
        return cufe;
    }

    public void setCufe(String cufe) {
        this.cufe = cufe;
    }

    public String getCude() {
        return cude;
    }

    public void setCude(String cude) {
        this.cude = cude;
    }
    
  
    public Boolean getDe1601EpsErronea() {
        return de1601EpsErronea;
    }
    
    public boolean esDe1601EpsErronea() {
        return de1601EpsErronea != null && de1601EpsErronea;
    }
    
    public String getDe1601EpsErroneaStr() {
        if (de1601EpsErronea == null) {
            return "";
        }
        return de1601EpsErronea ? "Si" : "No";
    }


    public void setDe1601EpsErronea(Boolean de1601EpsErronea) {
        this.de1601EpsErronea = de1601EpsErronea;
    }
 
    public Boolean getDe4401ProfesionalRed() {
        return de4401ProfesionalRed;
    }
    
    public String getDe4401ProfesionalRedStr() {
         if (de4401ProfesionalRed == null) {
            return "";
        }
        return de4401ProfesionalRed ? "Si" : "No";
    }

    public void setDe4401ProfesionalRed(Boolean de4401ProfesionalRed) {
        this.de4401ProfesionalRed = de4401ProfesionalRed;
    }
    
    public boolean esDe4401ProfesionalRed(){
      return de4401ProfesionalRed!= null && de4401ProfesionalRed;
    }
    
    public Boolean getDe4402ProfesionalIndependiente() {
        return de4402ProfesionalIndependiente;
    }
    
    public String getDe4402ProfesionalIndependienteStr() {
         if (de4402ProfesionalIndependiente == null) {
            return "";
        }
        return de4402ProfesionalIndependiente ? "Si" : "No";
    }

    public void setDe4402ProfesionalIndependiente(Boolean de4402ProfesionalIndependiente) {
        this.de4402ProfesionalIndependiente = de4402ProfesionalIndependiente;
    }
    
    public boolean esDe4402ProfesionalIndependiente(){
        return de4402ProfesionalIndependiente!= null && de4402ProfesionalIndependiente;
    }
    
    public Boolean getDe5001Pagada() {
        return de5001Pagada;
    }
    
    public String getDe5001PagadaStr() {
        if (de5001Pagada == null) {
            return "";
        }
        return de5001Pagada ? "Si" : "No";
    }
     
    public void setDe5001Pagada(Boolean de5001Pagada) {
        this.de5001Pagada = de5001Pagada;
    }
    
    public boolean esDe5001Pagada() {
        return de5001Pagada != null && de5001Pagada;
    }

    public Boolean getDe5002Radicada() {
        return de5002Radicada;
    }
    
    public boolean esDe5002Radicada() {
        return de5002Radicada !=null && de5002Radicada;
    }
    
    public String getDe5002RadicadaStr() {
        if (de5002Radicada == null) {
            return "";
        }
        return de5002Radicada ? "Si" : "No";
    }

    public void setDe5002Radicada(Boolean de5002Radicada) {
        this.de5002Radicada = de5002Radicada;
    }

    public Boolean getDe5601SoporteFe() {
        return de5601SoporteFe;
    }
    
     public String getDe5601SoporteFeStr() {
        if (de5601SoporteFe == null) {
            return "";
        }
        return de5601SoporteFe ? "Si" : "No";
    }

    public void setDe5601SoporteFe(Boolean de5601SoporteFe) {
        this.de5601SoporteFe = de5601SoporteFe;
    }
    
    public boolean esDe5601SoporteFe(){
       return de5601SoporteFe!= null && de5601SoporteFe;
    }

    public Boolean getDe5601Soportes() {
        return de5601Soportes;
    }
    
    public boolean esDe5601Soportes(){
        return de5601Soportes!= null && de5601Soportes;
    }
    
    public String getDe5601SoportesStr() {
        if (de5601Soportes == null) {
            return "";
        }
        return de5601Soportes ? "Si" : "No";
    }
   
    public void setDe5601Soportes(Boolean de5601Soportes) {
        this.de5601Soportes = de5601Soportes;
    }

    public String getDocumentoPrestador() {
        return documentoPrestador;
    }

    public void setDocumentoPrestador(String documentoPrestador) {
        this.documentoPrestador = documentoPrestador;
    }

    public String getDocumentoPrestadorXml() {
        return documentoPrestadorXml;
    }

    public void setDocumentoPrestadorXml(String documentoPrestadorXml) {
        this.documentoPrestadorXml = documentoPrestadorXml;
    }

    public Date getFechaHoraEmision() {
        return fechaHoraEmision;
    }

    public void setFechaHoraEmision(Date fechaHoraEmision) {
        this.fechaHoraEmision = fechaHoraEmision;
    }

    public Date getFechaHoraMinisterio() {
        return fechaHoraMinisterio;
    }

    public void setFechaHoraMinisterio(Date fechaHoraMinisterio) {
        this.fechaHoraMinisterio = fechaHoraMinisterio;
    }

    public String getUrlXml() {
        return urlXml;
    }

    public void setUrlXml(String urlXml) {
        this.urlXml = urlXml;
    }
    
    public boolean esUrlXml(){
        return (urlXml != null && !"".equals(urlXml));
    }

    public String getUrlJson() {
        return urlJson;
    }

    public void setUrlJson(String urlJson) {
        this.urlJson = urlJson;
    }
    
    public boolean esUrlJson(){
        return (urlJson != null && !"".equals(urlJson));
    }
    
    public boolean getHaySeleccionTipoValido(){
      return getTipo() != null;
    }

    public BigDecimal getValorNota() {
        return valorNota;
    }

    public void setValorNota(BigDecimal valorNota) {
        this.valorNota = valorNota;
    }
    
    public boolean getEsTipoCargaNota() {
        boolean variable = getTipo() != null &&
                 ( getTipo() == TIPO_CARGA_NOTA_DEBITO
                || getTipo() == TIPO_CARGA_NOTA_CREDITO_CAPITA
                || getTipo() == TIPO_CARGA_NOTA_CREDITO_PARCIAL
                || getTipo() == TIPO_CARGA_NOTA_CREDITO_TOTAL
                || getTipo() == TIPO_CARGA_NOTA_AJUSTE);
        return variable;
    }
    
      public boolean getEsTipoCapita() {
        boolean esCapita = getTipo() != null &&
                 ( getTipo() == TIPO_CARGA_CAPITA_INICIAL
                || getTipo() == TIPO_CARGA_CAPITA_PERIODO
                || getTipo() == TIPO_CARGA_CAPITA_FINAL);
        return esCapita;
    }
    
    public boolean getEsUnaNota(){
        return getEsTipoCargaNota();
    }
    
    public boolean getEsComportamientoFactura(){
        return !getEsTipoCargaNota();
    }
    
    public boolean getEsComportamientoNota(){
        return getEsTipoCargaNota();
    }
    
    public boolean getEsDevolucion(){
        return getEstado()== ESTADO_DEVUELTO;
    }
    
    public boolean getEsErrorValidacionAutomatica(){
        return getEstado()== ESTADO_VALIDACION_AUTOMATICA_ERROR;
    }
    
    public boolean getEsErrorValidacionMinisterio(){
        return getEstado()== ESTADO_VALIDACION_MINISTERIO_ERROR;
    }
    
    public boolean getEsErrorSinRespuestaSap(){
        return getEstado()== ESTADO_SIN_RESPUESTA_SAP;
    }
    
    public boolean getEsErrorEnvioAuditoria(){
        return getEstado()== ESTADO_ENVIO_AUDITORIA_ERROR;
    }
    
    public boolean getEsErrorValidacionNotaMinisterio(){
        return getEstado()== ESTADO_VALIDACION_NOTA_ERROR_MINISTERIO;
    }
    
     public boolean getEsErrorValidacionNotaFe(){
        return getEstado()== ESTADO_VALIDACION_NOTA_ERROR_FE;
    }
    
    public boolean getEsEstadoRecuperable(){
      return   getEsErrorValidacionAutomatica() || 
               getEsErrorValidacionMinisterio() || 
               getEsErrorEnvioAuditoria()       || 
               getEsErrorValidacionNotaMinisterio() ||
               getEsErrorValidacionNotaFe();
    }
    
    public boolean getEsInicioCarga(){
        return getEstado()== ESTADO_EN_COLA;
    }
    
    public boolean getEsValidacionProceso(){
        return getEstado()== ESTADO_VALIDACION_PROCESO;
    }
    
    public boolean getEsValidacionAutomaticoOk(){
        return getEstado()== ESTADO_VALIDACION_AUTOMATICA_OK;
    }
    
    public boolean esCausalDevolucionProcesoAutomatico(){
        return  (this.esDe5001Pagada() || this.esDe5002Radicada()
                || this.esDe1601EpsErronea());
    }
    
    public boolean esCausalDevolucionProcesoManual(){
        return  ( !this.esDe4401ProfesionalRed()|| !this.esDe4402ProfesionalIndependiente()
                || !this.esDe5601Soportes()|| !this.esDe5601SoporteFe() );
    }
    
    public boolean esTipoCapitaInicial() {
        return getTipo() == TIPO_CARGA_CAPITA_INICIAL;
    }
    
    public boolean esTipoCapitaPeriodo() {
        return getTipo() == TIPO_CARGA_CAPITA_PERIODO;
    }
    
    public boolean esTipoCapitaFinal() {
        return getTipo() == TIPO_CARGA_CAPITA_FINAL;
    }
    
    public boolean esTipoFactura() {
        return getTipo() == TIPO_CARGA_FACTURA;
    }

    public String getNumeroNota() {
        return numeroNota;
    }
    
    public boolean esTipoNotaAjuste() {
        return getTipo() == TIPO_CARGA_NOTA_AJUSTE;
    }

    public void setNumeroNota(String numeroNota) {
        this.numeroNota = numeroNota;
    }

    public BigDecimal getValorCopago() {
        return valorCopago;
    }

    public void setValorCopago(BigDecimal valorCopago) {
        this.valorCopago = valorCopago;
    }

    public BigDecimal getValorCuotaModeradora() {
        return valorCuotaModeradora;
    }

    public void setValorCuotaModeradora(BigDecimal valorCuotaModeradora) {
        this.valorCuotaModeradora = valorCuotaModeradora;
    }

    public boolean isTieneTransacciones() {
        return tieneTransacciones;
    }
    
    public boolean isEstadoMayorValidacionProceso() {
        return getEstado() >= CmFeRipsCarga.ESTADO_VALIDACION_PROCESO;
    }

    public void setTieneTransacciones(boolean tieneTransacciones) {
        this.tieneTransacciones = tieneTransacciones;
    }

    public String getDocumentoNumero() {
        return documentoNumero;
    }

    public void setDocumentoNumero(String documentoNumero) {
        this.documentoNumero = documentoNumero;
    }

    public BigDecimal getDocumentoValor() {
        return documentoValor;
    }

    public void setDocumentoValor(BigDecimal documentoValor) {
        this.documentoValor = documentoValor;
    }

    public String getDocumentoPertinencia() {
        return documentoPertinencia;
    }

    public void setDocumentoPertinencia(String documentoPertinencia) {
        this.documentoPertinencia = documentoPertinencia;
    }

    public Integer getIdCmFacturaAsociadaNota() {
        return idCmFacturaAsociadaNota;
    }

    public void setIdCmFacturaAsociadaNota(Integer idCmFacturaAsociadaNota) {
        this.idCmFacturaAsociadaNota = idCmFacturaAsociadaNota;
    }

    public Integer getOrigen() {
        return origen;
    }

    public void setOrigen(Integer origen) {
        this.origen = origen;
    }   

    public Integer getTipoReglaCarga() {
        return tipoReglaCarga;
    }

    public void setTipoReglaCarga(Integer tipoReglaCarga) {
        this.tipoReglaCarga = tipoReglaCarga;
    }

    public Integer getCapitaPeriodo() {
        return capitaPeriodo;
    }

    public void setCapitaPeriodo(Integer capitaPeriodo) {
        this.capitaPeriodo = capitaPeriodo;
    }

    public CmFeRipsCarga getCargaPeriodo() {
        return cargaPeriodo;
    }

    public void setCargaPeriodo(CmFeRipsCarga cargaPeriodo) {
        this.cargaPeriodo = cargaPeriodo;
    }

    public CmFeRipsCarga getCargaPeriodoAnterior() {
        return cargaPeriodoAnterior;
    }

    public void setCargaPeriodoAnterior(CmFeRipsCarga cargaPeriodoAnterior) {
        this.cargaPeriodoAnterior = cargaPeriodoAnterior;
    }
    
    public static String getCapitaPeriodoStr(Integer periodo) {
        if (periodo == null  || periodo < 1 || periodo > 12) {
            return "";
        }
        Month mes = Month.of(periodo);
        return mes.getDisplayName(TextStyle.FULL, new Locale("es", "CO")).toUpperCase();
    }
    
    public String getCapitaPeriodoStr() {
        return CmFeRipsCarga.getCapitaPeriodoStr(getCapitaPeriodo());
    }

    public String getNumeroFacturaAsociadoNota() {
        return numeroFacturaAsociadoNota;
    }

    public void setNumeroFacturaAsociadoNota(String numeroFacturaAsociadoNota) {
        this.numeroFacturaAsociadoNota = numeroFacturaAsociadoNota;
    }

    public Boolean getMultiusuario() {
        return multiusuario;
    }

    public void setMultiusuario(Boolean multiusuario) {
        this.multiusuario = multiusuario;
    }  

    public Usuario getRadicadorAsignado() {
        return radicadorAsignado;
    }

    public void setRadicadorAsignado(Usuario radicadorAsignado) {
        this.radicadorAsignado = radicadorAsignado;
    }

    public CmGrupo getCmGrupoRadicacionRelacionado() {
        return cmGrupoRadicacionRelacionado;
    }

    public void setCmGrupoRadicacionRelacionado(CmGrupo cmGrupoRadicacionRelacionado) {
        this.cmGrupoRadicacionRelacionado = cmGrupoRadicacionRelacionado;
    }

    @Override
    public CmFeRipsCarga clone() throws CloneNotSupportedException {
        try {
             CmFeRipsCarga clon = (CmFeRipsCarga) super.clone();
             clon.setCmFeRipsCargaAdjuntos( new ArrayList<>());
             clon.setCmFeRipsCargaContenidos( new ArrayList<>());
            return clon;
        } catch (CloneNotSupportedException ex) {      
            throw new RuntimeException("No se pudo clonar el objeto", ex);
        }
    }

    @Override
    public String toString() {
        return "CmFeRipsCarga{" + "id=" + id + ", tipo=" + tipo + ", contrato=" + contrato + ", cntTipoContratoId=" + cntTipoContratoId + ", estado=" + estado + ", maeRegimenId=" + maeRegimenId + ", maeRegimenCodigo=" + maeRegimenCodigo + ", maeRegimenValor=" + maeRegimenValor + ", maeContratoModalidadId=" + maeContratoModalidadId + ", maeContratoModalidadCodigo=" + maeContratoModalidadCodigo + ", maeContratoModalidadValor=" + maeContratoModalidadValor + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin + ", tiempo=" + tiempo + ", cobertura=" + cobertura + ", facturaNumero=" + facturaNumero + ", facturaValor=" + facturaValor + ", rechazo=" + rechazo + ", fechaHoraRechazo=" + fechaHoraRechazo + ", observacionRechazo=" + observacionRechazo + ", devolucion=" + devolucion + ", fechaHoraDevolucion=" + fechaHoraDevolucion + ", maeDevolucionId=" + maeDevolucionId + ", maeDevolucionCodigo=" + maeDevolucionCodigo + ", maeDevolucionValor=" + maeDevolucionValor + ", observacionDevolucion=" + observacionDevolucion + ", observacion=" + observacion + ", pbs=" + pbs + '}';
    }
    
}
