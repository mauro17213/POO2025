package com.saviasaludeps.savia.dominio.aseguramiento;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class AsegPortabilidad extends Auditoria {

    public static final String ESTADO_EN_TRAMITE = "1";
    public static final String ESTADO_APROBADA = "2";
    public static final String ESTADO_RECHAZADA = "3";
    public static final String ESTADO_CANCELADA_USUARIO = "4";
    public static final String ESTADO_FINALIZADA = "5";
    public static final String ESTADO_PRORROGA = "6";

    public static final String ESTADO_EN_TRAMITE_STRING = "En Trámite";
    public static final String ESTADO_APROBADA_STRING = "Aprobada";
    public static final String ESTADO_RECHAZADA_STRING = "Rechazada";
    public static final String ESTADO_CANCELADA_USUARIO_STRING = "Cancelada por Usuario";
    public static final String ESTADO_FINALIZADA_STRING = "Finalizada"; 
    public static final String ESTADO_PRORROGA_STRING = "Aprobado con Prórroga";

    public static final String TIPO_OCASIONAL = "1";
    public static final String TIPO_TEMPORAL = "2";
    public static final String TIPO_PERMANENTE = "3";

    public static final String TIPO_OCASIONAL_STRING = "OCASIONAL";
    public static final String TIPO_TEMPORAL_STRING = "TEMPORAL";
    public static final String TIPO_PERMANENTE_STRING = "PERMANENTE";

    public static final int ORIGEN_CORREO_ELECTRONICO = 1;
    public static final int ORIGEN_TELEFOO = 2;
    public static final int ORIGEN_PAGINA_WEB = 3;
    public static final int ORIGEN_SOLICITUD_PERSONAL = 4;

    public static final String ORIGEN_CORREO_ELECTRONICO_STRING = "CORREO ELECTRONICO";
    public static final String ORIGEN_TELEFONO_STRING = "TELEFONO";
    public static final String ORIGEN_PAGINA_WEB_STRING = "PAGINA WEB";
    public static final String ORIGEN_SOLICITUD_PERSONAL_STRING = "SOLICITUD PERSONAL";
    
    public static final String TIPO_SOLICITUD_PRORROGA = "Prórroga";
    public static final String TIPO_SOLICITUD_NUEVA = "Nueva";
    
    public static final Integer ENVIO_CORREO_FALLIDO = 0;
    public static final Integer ENVIO_CORREO_EXITOSO = 1;
    public static final Integer ENVIO_CORREO_NOAPLICA = 2;
    public static final String ENVIO_CORREO_FALLIDO_STR = "Fallido";
    public static final String ENVIO_CORREO_EXITOSO_STR = "Exitoso";
    public static final String ENVIO_CORREO_NOAPLICA_STR = "No aplica";

    private Integer id;
    private Date periodoInicial;
    private Date periodoFinal;
    private int origenSolicitud;
    private Integer maeOrigenSolicitudId;
    private String maeOrigenSolicitudCodigo;
    private String maeOrigenSolicitudValor;
    private int tipoPortabilidad;
    private Integer maeTipoPortabilidadId;
    private String maeTipoPortabilidadCodigo;
    private String maeTipoPortabilidadValor;
    private int estadoPortabilidad;
    private Integer maeEstadoPortabilidadId;
    private String maeEstadoPortabilidadCodigo;
    private String maeEstadoPortabilidadValor;
    private String estadoInicialPortab;
    private String direccion;
    private String telefonoContacto;
    private String telefonoContacto2;
    private String correoElectronico;
    private String observacionUsuario;
    private String observacionAseguramiento;
    private Date fechaSolicitudCancelacion;
    private Date fechaSolicitudProrroga;
    private Date fechaCancelacion;
    private String usuarioCancela;
    private String observacionCancelacion;
    private String observacionProrroga;
    private AsegAfiliado asegAfiliado;
    private CntPrestadorSede cntPrestadorSede;
    private Ubicacion ubicacion;
    private Integer numeroProrroga;
    private Integer mesesProrroga;
    private Date fechaSolicitudProrrogaAnterior;
    private Integer mesesProrrogaAnterior;
    private boolean hayProrrogaAnterior;
    private String observacionProrrogaAnterior;
    private Integer maeMotivoPortabilidadId;
    private String maeMotivoPortabilidadCodigo;
    private String maeMotivoPortabilidadValor;
    private Integer envioCorreo;
    // Campos para Manejo de Dirección
//    private String direccionVia;
//    private String direccionNro;
//    private String direccionOrd1;
//    private String direccionOrientacion;
//    private String direccionPlaca1;
//    private String direccionOrd2;
//    private String direccionPlaca2;
//    private String direccionDescripcion;

    public AsegPortabilidad() {
    }

    public AsegPortabilidad(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPeriodoInicial() {
        return periodoInicial;
    }

    public void setPeriodoInicial(Date periodoInicial) {
        this.periodoInicial = periodoInicial;
    }

    public Date getPeriodoFinal() {
        return periodoFinal;
    }

    public void setPeriodoFinal(Date periodoFinal) {
        this.periodoFinal = periodoFinal;
    }

    public int getOrigenSolicitud() {
        return origenSolicitud;
    }

    public String getOrigenSolicitudString() {
        switch (origenSolicitud) {
            case ORIGEN_CORREO_ELECTRONICO:
                return ORIGEN_CORREO_ELECTRONICO_STRING;
            case ORIGEN_PAGINA_WEB:
                return ORIGEN_PAGINA_WEB_STRING;
            case ORIGEN_SOLICITUD_PERSONAL:
                return ORIGEN_SOLICITUD_PERSONAL_STRING;
            case ORIGEN_TELEFOO:
                return ORIGEN_TELEFONO_STRING;
            default:
                return "";
        }
    }

    public void setOrigenSolicitud(int origenSolicitud) {
        this.origenSolicitud = origenSolicitud;
    }

    public int getTipoPortabilidad() {
        return tipoPortabilidad;
    }

    public String getTipoPortabilidadString() {
        switch (tipoPortabilidad+"") {
            case TIPO_OCASIONAL:
                return TIPO_OCASIONAL_STRING;
            case TIPO_PERMANENTE:
                return TIPO_PERMANENTE_STRING;
            case TIPO_TEMPORAL:
                return TIPO_TEMPORAL_STRING;
            default:
                return "";

        }
    }

    public void setTipoPortabilidad(int tipoPortabilidad) {
        this.tipoPortabilidad = tipoPortabilidad;
    }

    public int getEstadoPortabilidad() {
        return estadoPortabilidad;
    }

    public String getEstadoPortabilidadString() {
        switch (String.valueOf(estadoPortabilidad)) {
            case ESTADO_EN_TRAMITE:
                return ESTADO_EN_TRAMITE_STRING;
            case ESTADO_APROBADA:
                return ESTADO_APROBADA_STRING;
            case ESTADO_CANCELADA_USUARIO:
                return ESTADO_CANCELADA_USUARIO_STRING;
            case ESTADO_FINALIZADA:
                return ESTADO_FINALIZADA_STRING;
            case ESTADO_RECHAZADA:
                return ESTADO_RECHAZADA_STRING;
            case ESTADO_PRORROGA:
                return ESTADO_PRORROGA_STRING;
            default:
                return "";
        }
    }

    public void setEstadoPortabilidad(int estadoPortabilidad) {
        this.estadoPortabilidad = estadoPortabilidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getTelefonoContacto2() {
        return telefonoContacto2;
    }

    public void setTelefonoContacto2(String telefonoContacto2) {
        this.telefonoContacto2 = telefonoContacto2;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getObservacionUsuario() {
        return observacionUsuario;
    }

    public void setObservacionUsuario(String observacionUsuario) {
        this.observacionUsuario = observacionUsuario;
    }

    public String getObservacionAseguramiento() {
        return observacionAseguramiento;
    }

    public void setObservacionAseguramiento(String observacionAseguramiento) {
        this.observacionAseguramiento = observacionAseguramiento;
    }

    public Date getFechaSolicitudCancelacion() {
        return fechaSolicitudCancelacion;
    }

    public void setFechaSolicitudCancelacion(Date fechaSolicitudCancelacion) {
        this.fechaSolicitudCancelacion = fechaSolicitudCancelacion;
    }

    public Date getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(Date fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public String getUsuarioCancela() {
        return usuarioCancela;
    }

    public void setUsuarioCancela(String usuarioCancela) {
        this.usuarioCancela = usuarioCancela;
    }

    public String getObservacionCancelacion() {
        return observacionCancelacion;
    }

    public void setObservacionCancelacion(String observacionCancelacion) {
        this.observacionCancelacion = observacionCancelacion;
    }

    public AsegAfiliado getAsegAfiliado() {
        return asegAfiliado;
    }

    public void setAsegAfiliado(AsegAfiliado asegAfiliado) {
        this.asegAfiliado = asegAfiliado;
    }

    public CntPrestadorSede getCntPrestadorSede() {
        return cntPrestadorSede;
    }

    public void setCntPrestadorSede(CntPrestadorSede cntPrestadorSede) {
        this.cntPrestadorSede = cntPrestadorSede;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstadoInicialPortab() {
        return estadoInicialPortab;
    }

    public void setEstadoInicialPortab(String estadoInicialPortab) {
        this.estadoInicialPortab = estadoInicialPortab;
    }

    public Integer getNumeroProrroga() {
        return numeroProrroga;
    }

    public void setNumeroProrroga(Integer numeroProrroga) {
        this.numeroProrroga = numeroProrroga;
    }

    public Integer getMesesProrroga() {
        return mesesProrroga;
    }

    public void setMesesProrroga(Integer mesesProrroga) {
        this.mesesProrroga = mesesProrroga;
    } 

    public Date getFechaSolicitudProrrogaAnterior() {
        return fechaSolicitudProrrogaAnterior;
    }

    public void setFechaSolicitudProrrogaAnterior(Date fechaSolicitudProrrogaAnterior) {
        this.fechaSolicitudProrrogaAnterior = fechaSolicitudProrrogaAnterior;
    }

    public Integer getMesesProrrogaAnterior() {
        return mesesProrrogaAnterior;
    }

    public void setMesesProrrogaAnterior(Integer mesesProrrogaAnterior) {
        this.mesesProrrogaAnterior = mesesProrrogaAnterior;
    }

    public boolean isHayProrrogaAnterior() {
        return hayProrrogaAnterior;
    }

    public void setHayProrrogaAnterior(boolean hayProrrogaAnterior) {
        this.hayProrrogaAnterior = hayProrrogaAnterior;
    }
    
    public boolean getHayProrrogaAnterior() {
        return hayProrrogaAnterior;
    }

    public String getObservacionProrrogaAnterior() {
        return observacionProrrogaAnterior;
    }

    public void setObservacionProrrogaAnterior(String observacionProrrogaAnterior) {
        this.observacionProrrogaAnterior = observacionProrrogaAnterior;
    }

    public String getObservacionProrroga() {
        return observacionProrroga;
    }

    public void setObservacionProrroga(String observacionProrroga) {
        this.observacionProrroga = observacionProrroga;
    }

    public Date getFechaSolicitudProrroga() {
        return fechaSolicitudProrroga;
    }

    public void setFechaSolicitudProrroga(Date fechaSolicitudProrroga) {
        this.fechaSolicitudProrroga = fechaSolicitudProrroga;
    }
    
    public String getFechaStr(Date fecha) {
        String mensaje = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            mensaje = sdf.format(fecha);
        }catch (Exception ex) {
            mensaje= "";
        }
        return mensaje;
    }
    

    @Override
    public String toString() {
        return "AsegPortabilidad{" + "id=" + id + ", periodoInicial=" + getFechaStr(periodoInicial) + ", periodoFinal=" + getFechaStr(periodoFinal) + ", origenSolicitud=" + origenSolicitud + ", maeOrigenSolicitudId=" + maeOrigenSolicitudId + ", maeOrigenSolicitudCodigo=" + maeOrigenSolicitudCodigo + ", maeOrigenSolicitudValor=" + maeOrigenSolicitudValor + ", tipoPortabilidad=" + tipoPortabilidad + ", maeTipoPortabilidadId=" + maeTipoPortabilidadId + ", maeTipoPortabilidadCodigo=" + maeTipoPortabilidadCodigo + ", maeTipoPortabilidadValor=" + maeTipoPortabilidadValor + ", estadoPortabilidad=" + estadoPortabilidad + ", maeEstadoPortabilidadId=" + maeEstadoPortabilidadId + ", maeEstadoPortabilidadCodigo=" + maeEstadoPortabilidadCodigo  + ", maeEstadoPortabilidadValor=" + maeEstadoPortabilidadValor + ", direccion=" + direccion + ", telefonoContacto=" + telefonoContacto + ", telefonoContacto2=" + telefonoContacto2 + ", correoElectronico=" + correoElectronico + ", observacionUsuario=" + observacionUsuario + ", observacionAseguramiento=" + observacionAseguramiento + ", fechaSolicitudCancelacion=" + getFechaStr(fechaSolicitudCancelacion) + ", fechaCancelacion=" + getFechaStr(fechaCancelacion) + ", usuarioCancela=" + usuarioCancela + ", observacionCancelacion=" + observacionCancelacion + ", asegAfiliado=" + asegAfiliado + ", cntPrestadorSede=" + cntPrestadorSede + ", ubicacion=" + ubicacion + ", usuarioCrea=" + super.getUsuarioCrea() + ", terminalCrea=" + super.getTerminalCrea() + ", fechaHoraCrea=" + getFechaStr(super.getFechaHoraCrea()) + ", usuarioCrea=" + super.getUsuarioModifica() + ", terminalModifica=" + super.getTerminalModifica() + ", fechaHoraModifica=" + getFechaStr(super.getFechaHoraModifica()) +'}';
    }

    /**
     * @return the maeOrigenSolicitudId
     */
    public Integer getMaeOrigenSolicitudId() {
        return maeOrigenSolicitudId;
    }

    /**
     * @param maeOrigenSolicitudId the maeOrigenSolicitudId to set
     */
    public void setMaeOrigenSolicitudId(Integer maeOrigenSolicitudId) {
        this.maeOrigenSolicitudId = maeOrigenSolicitudId;
    }

    /**
     * @return the maeOrigenSolicitudCodigo
     */
    public String getMaeOrigenSolicitudCodigo() {
        return maeOrigenSolicitudCodigo;
    }

    /**
     * @param maeOrigenSolicitudCodigo the maeOrigenSolicitudCodigo to set
     */
    public void setMaeOrigenSolicitudCodigo(String maeOrigenSolicitudCodigo) {
        this.maeOrigenSolicitudCodigo = maeOrigenSolicitudCodigo;
    }

    /**
     * @return the maeOrigenSolicitudValor
     */
    public String getMaeOrigenSolicitudValor() {
        return maeOrigenSolicitudValor;
    }

    /**
     * @param maeOrigenSolicitudValor the maeOrigenSolicitudValor to set
     */
    public void setMaeOrigenSolicitudValor(String maeOrigenSolicitudValor) {
        this.maeOrigenSolicitudValor = maeOrigenSolicitudValor;
    }

    /**
     * @return the maeTipoPortabilidadId
     */
    public Integer getMaeTipoPortabilidadId() {
        return maeTipoPortabilidadId;
    }

    /**
     * @param maeTipoPortabilidadId the maeTipoPortabilidadId to set
     */
    public void setMaeTipoPortabilidadId(Integer maeTipoPortabilidadId) {
        this.maeTipoPortabilidadId = maeTipoPortabilidadId;
    }

    /**
     * @return the maeTipoPortabilidadCodigo
     */
    public String getMaeTipoPortabilidadCodigo() {
        return maeTipoPortabilidadCodigo;
    }

    /**
     * @param maeTipoPortabilidadCodigo the maeTipoPortabilidadCodigo to set
     */
    public void setMaeTipoPortabilidadCodigo(String maeTipoPortabilidadCodigo) {
        this.maeTipoPortabilidadCodigo = maeTipoPortabilidadCodigo;
    }

    /**
     * @return the maeTipoPortabilidadValor
     */
    public String getMaeTipoPortabilidadValor() {
        return maeTipoPortabilidadValor;
    }

    /**
     * @param maeTipoPortabilidadValor the maeTipoPortabilidadValor to set
     */
    public void setMaeTipoPortabilidadValor(String maeTipoPortabilidadValor) {
        this.maeTipoPortabilidadValor = maeTipoPortabilidadValor;
    }

    /**
     * @return the maeEstadoPortabilidadId
     */
    public Integer getMaeEstadoPortabilidadId() {
        return maeEstadoPortabilidadId;
    }

    /**
     * @param maeEstadoPortabilidadId the maeEstadoPortabilidadId to set
     */
    public void setMaeEstadoPortabilidadId(Integer maeEstadoPortabilidadId) {
        this.maeEstadoPortabilidadId = maeEstadoPortabilidadId;
    }

    /**
     * @return the maeEstadoPortabilidadCodigo
     */
    public String getMaeEstadoPortabilidadCodigo() {
        return maeEstadoPortabilidadCodigo;
    }

    /**
     * @param maeEstadoPortabilidadCodigo the maeEstadoPortabilidadCodigo to set
     */
    public void setMaeEstadoPortabilidadCodigo(String maeEstadoPortabilidadCodigo) {
        this.maeEstadoPortabilidadCodigo = maeEstadoPortabilidadCodigo;
    }

    /**
     * @return the maeEstadoPortabilidadValor
     */
    public String getMaeEstadoPortabilidadValor() {
        return maeEstadoPortabilidadValor;
    }

    /**
     * @param maeEstadoPortabilidadValor the maeEstadoPortabilidadValor to set
     */
    public void setMaeEstadoPortabilidadValor(String maeEstadoPortabilidadValor) {
        this.maeEstadoPortabilidadValor = maeEstadoPortabilidadValor;
    }

    public Integer getMaeMotivoPortabilidadId() {
        return maeMotivoPortabilidadId;
    }

    public void setMaeMotivoPortabilidadId(Integer maeMotivoPortabilidadId) {
        this.maeMotivoPortabilidadId = maeMotivoPortabilidadId;
    }

    public String getMaeMotivoPortabilidadCodigo() {
        return maeMotivoPortabilidadCodigo;
    }

    public void setMaeMotivoPortabilidadCodigo(String maeMotivoPortabilidadCodigo) {
        this.maeMotivoPortabilidadCodigo = maeMotivoPortabilidadCodigo;
    }

    public String getMaeMotivoPortabilidadValor() {
        return maeMotivoPortabilidadValor;
    }

    public void setMaeMotivoPortabilidadValor(String maeMotivoPortabilidadValor) {
        this.maeMotivoPortabilidadValor = maeMotivoPortabilidadValor;
    }

    public Integer getEnvioCorreo() {
        return envioCorreo;
    }

    public void setEnvioCorreo(Integer envioCorreo) {
        this.envioCorreo = envioCorreo;
    }
    
    public String getEnvioCorreoString(){        
        if(envioCorreo == null){
            return "";
        }
        if(Objects.equals(envioCorreo, ENVIO_CORREO_FALLIDO)){
            return ENVIO_CORREO_FALLIDO_STR;
        }
        if(Objects.equals(envioCorreo, ENVIO_CORREO_EXITOSO)){
            return ENVIO_CORREO_EXITOSO_STR;
        }
        if(Objects.equals(envioCorreo, ENVIO_CORREO_NOAPLICA)){
            return ENVIO_CORREO_NOAPLICA_STR;
        }
        return "";
    }

    
}
