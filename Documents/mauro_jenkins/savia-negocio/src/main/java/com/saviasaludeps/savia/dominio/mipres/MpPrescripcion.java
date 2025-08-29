package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.contratacion.CntProfesional;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author bsgomez
 */
public class MpPrescripcion extends Auditoria {

    public static final String ID_AMBITO_AMBULATORIO_PRIORIZADO = "11";
    public static final String ID_AMBITO_AMBULATORIO_NO_PRIORIZADO = "12";
    public static final String ID_AMBITO_HOSPITALARIO_DOMICILIARIO = "21";
    public static final String ID_AMBITO_HOSPITALARIO_INTERNACION = "22";
    public static final String ID_AMBITO_URGENCIAS = "30";
    //estados
    public final static int ESTADO_DIRECCIONADA = 1;
    public final static int ESTADO_NO_DIRECCIONADA = 2;
    public final static int ESTADO_PENDIENTE = 3;

    public static final int ESTADO_PRESCRIPCION_ANULADO = 2;
    //tecnologias tipo int
    public final static int TIPO_MEDICAMENTO = 1;
    public final static int TIPO_TECNOLOGIA = 2;
    public final static int TIPO_DISPOSITIVO_MEDICO = 3;
    public final static int TIPO_PRODUCTO_NUTRICIONAL = 4;
    public final static int TIPO_SERVICIO_COMPLEMENTARIO = 5;
    //tecnologias tipo Char
    public final static String STR_TIPO_MEDICAMENTO = "M";
    public final static String STR_TIPO_TECNOLOGIA = "P";
    public final static String STR_TIPO_DISPOSITIVO_MEDICO = "D";
    public final static String STR_TIPO_PRODUCTO_NUTRICIONAL = "N";
    public final static String STR_TIPO_SERVICIO_COMPLEMENTARIO = "S";

    public static final String FECHA_INICIO_SERVICIO_SUB_POR_DEFECTO = "2024-07-01";
    public static final String FECHA_INICIO_SERVICIO_CONT_POR_DEFECTO = "2024-07-01";
//    public static final String FECHA_INICIO_SERVICIO_SUB_POR_DEFECTO = "2016-12-02";
//    public static final String FECHA_INICIO_SERVICIO_CONT_POR_DEFECTO = "2016-12-02";
    //Regimen
    public static final String REGIMEN_SUBSIDIADO = "01";
    public static final String REGIMEN_CONTRIBUTIVO = "02";

    private Integer id;
    private boolean recobrante;
    private Integer maTipoDocumentoPrestadorId;
    private String maTipoDocumentoPrestadorCodigo;
    private String maTipoDocumentoPrestadorValor;
    private String prestadorNumeroDocumento;
    private String prestadorRazonSocial;
    private String sedeCodigoHabilitacion;
    private String numeroPrescripcion;
    private String consecutivoMipres;
    private Date fechaPrescripcion;
    private Date horaPrescripcion;
    private Boolean tipoPrescripcion;
    private String codAmbAte;
    private Boolean referenciaAmbitoAtencion;
    private Boolean pacienteCovid19;
    private boolean enfermedadHuerfana;
    private String codigoEnfermedadHuerfana;
    private boolean diagnosticoEnfermedadHuerfana;
    private Integer maDiagnosticoPrincipalId;
    private String maDiagnosticoPrincipalCodigo;
    private String maDiagnosticoPrincipalValor;
    private Integer maDiagnosticoRelacionado1Id;
    private String maDiagnosticoRelacionado1Codigo;
    private String maDiagnosticoRelacionado1Valor;
    private Integer maDiagnosticoRelacionado2Id;
    private String maDiagnosticoRelacionado2Codigo;
    private String maDiagnosticoRelacionado2Valor;
    private String sopNutricional;
    private String codigoEps;
    private String asegAfiliadoMadreTipoDocumento;
    private String asegAfiliadoMadreDocumento;
    private Integer tipoTransaccion;
    private String numeroDocumentoDonanteVivo;
    private String tipoDocumentoDonanteVivo;
    private Integer estado;
    private Boolean afectaPresMax;
    private String compradorHomologo;
    private String transcripcion;
    private String direccionIpsPrescriptora;
    private String telefonoIpsPrescriptora;
    private String codHabilitacionIpsPrescriptora;
    private Integer municipioIpsPrescriptora;
    private Boolean derechosVerificados;
    private Boolean portabilidad;
    private Integer municipioPortabilidad;
    private String transferidaPor;
    private String actualizadaPor;
    private Boolean requiereAnulacion;
    private String notaAuditoria;
    private MpAfiliado mpAfiliado;
    private AsegAfiliado asegAfiliado;
    private CntProfesional cntProfesional;
    private CntPrestadorSede cntPrestadorSede;
    private Empresa empresa = null;

    private Integer medicamento;//1
    private Integer complementario;//5
    private Integer insumo;//3
    private Integer tecnologia;//4
    private Integer procedimiento;//2

    private Integer idItem;
    private Integer tipoTecnologiaItem;

    public MpPrescripcion() {
    }

    public MpPrescripcion(Integer id) {
        this.id = id;
    }

    public MpPrescripcion(Integer id, boolean recobrante, Integer maTipoDocumentoPrestadorId, String maTipoDocumentoPrestadorCodigo, String maTipoDocumentoPrestadorValor, String prestadorNumeroDocumento, String prestadorRazonSocial, String sedeCodigoHabilitacion, String numeroPrescripcion, String consecutivoMipres, Date fechaPrescripcion, Date horaPrescripcion, Boolean tipoPrescripcion, String codAmbAte, Boolean referenciaAmbitoAtencion, Boolean pacienteCovid19, boolean enfermedadHuerfana, String codigoEnfermedadHuerfana, boolean diagnosticoEnfermedadHuerfana, Integer maDiagnosticoPrincipalId, String maDiagnosticoPrincipalCodigo, String maDiagnosticoPrincipalValor, Integer maDiagnosticoRelacionado1Id, String maDiagnosticoRelacionado1Codigo, String maDiagnosticoRelacionado1Valor, Integer maDiagnosticoRelacionado2Id, String maDiagnosticoRelacionado2Codigo, String maDiagnosticoRelacionado2Valor, String sopNutricional, String codigoEps, String asegAfiliadoMadreTipoDocumento, String asegAfiliadoMadreDocumento, Integer tipoTransaccion, String numeroDocumentoDonanteVivo, String tipoDocumentoDonanteVivo, Integer estado, Boolean afectaPresMax, String compradorHomologo, String transcripcion, String direccionIpsPrescriptora, String telefonoIpsPrescriptora, String codHabilitacionIpsPrescriptora, Integer municipioIpsPrescriptora, Boolean derechosVerificados, Boolean portabilidad, Integer municipioPortabilidad, String transferidaPor, String actualizadaPor, Boolean requiereAnulacion, String notaAuditoria, AsegAfiliado asegAfiliado, CntProfesional cntProfesional, CntPrestadorSede cntPrestadorSede) {
        this.id = id;
        this.recobrante = recobrante;
        this.maTipoDocumentoPrestadorId = maTipoDocumentoPrestadorId;
        this.maTipoDocumentoPrestadorCodigo = maTipoDocumentoPrestadorCodigo;
        this.maTipoDocumentoPrestadorValor = maTipoDocumentoPrestadorValor;
        this.prestadorNumeroDocumento = prestadorNumeroDocumento;
        this.prestadorRazonSocial = prestadorRazonSocial;
        this.sedeCodigoHabilitacion = sedeCodigoHabilitacion;
        this.numeroPrescripcion = numeroPrescripcion;
        this.consecutivoMipres = consecutivoMipres;
        this.fechaPrescripcion = fechaPrescripcion;
        this.horaPrescripcion = horaPrescripcion;
        this.tipoPrescripcion = tipoPrescripcion;
        this.codAmbAte = codAmbAte;
        this.referenciaAmbitoAtencion = referenciaAmbitoAtencion;
        this.pacienteCovid19 = pacienteCovid19;
        this.enfermedadHuerfana = enfermedadHuerfana;
        this.codigoEnfermedadHuerfana = codigoEnfermedadHuerfana;
        this.diagnosticoEnfermedadHuerfana = diagnosticoEnfermedadHuerfana;
        this.maDiagnosticoPrincipalId = maDiagnosticoPrincipalId;
        this.maDiagnosticoPrincipalCodigo = maDiagnosticoPrincipalCodigo;
        this.maDiagnosticoPrincipalValor = maDiagnosticoPrincipalValor;
        this.maDiagnosticoRelacionado1Id = maDiagnosticoRelacionado1Id;
        this.maDiagnosticoRelacionado1Codigo = maDiagnosticoRelacionado1Codigo;
        this.maDiagnosticoRelacionado1Valor = maDiagnosticoRelacionado1Valor;
        this.maDiagnosticoRelacionado2Id = maDiagnosticoRelacionado2Id;
        this.maDiagnosticoRelacionado2Codigo = maDiagnosticoRelacionado2Codigo;
        this.maDiagnosticoRelacionado2Valor = maDiagnosticoRelacionado2Valor;
        this.sopNutricional = sopNutricional;
        this.codigoEps = codigoEps;
        this.asegAfiliadoMadreTipoDocumento = asegAfiliadoMadreTipoDocumento;
        this.asegAfiliadoMadreDocumento = asegAfiliadoMadreDocumento;
        this.tipoTransaccion = tipoTransaccion;
        this.numeroDocumentoDonanteVivo = numeroDocumentoDonanteVivo;
        this.tipoDocumentoDonanteVivo = tipoDocumentoDonanteVivo;
        this.estado = estado;
        this.afectaPresMax = afectaPresMax;
        this.compradorHomologo = compradorHomologo;
        this.transcripcion = transcripcion;
        this.direccionIpsPrescriptora = direccionIpsPrescriptora;
        this.telefonoIpsPrescriptora = telefonoIpsPrescriptora;
        this.codHabilitacionIpsPrescriptora = codHabilitacionIpsPrescriptora;
        this.municipioIpsPrescriptora = municipioIpsPrescriptora;
        this.derechosVerificados = derechosVerificados;
        this.portabilidad = portabilidad;
        this.municipioPortabilidad = municipioPortabilidad;
        this.transferidaPor = transferidaPor;
        this.actualizadaPor = actualizadaPor;
        this.requiereAnulacion = requiereAnulacion;
        this.notaAuditoria = notaAuditoria;
        this.asegAfiliado = asegAfiliado;
        this.cntProfesional = cntProfesional;
        this.cntPrestadorSede = cntPrestadorSede;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaTipoDocumentoPrestadorId() {
        return maTipoDocumentoPrestadorId;
    }

    public void setMaTipoDocumentoPrestadorId(Integer maTipoDocumentoPrestadorId) {
        this.maTipoDocumentoPrestadorId = maTipoDocumentoPrestadorId;
    }

    public String getMaTipoDocumentoPrestadorCodigo() {
        return maTipoDocumentoPrestadorCodigo;
    }

    public void setMaTipoDocumentoPrestadorCodigo(String maTipoDocumentoPrestadorCodigo) {
        this.maTipoDocumentoPrestadorCodigo = maTipoDocumentoPrestadorCodigo;
    }

    public String getMaTipoDocumentoPrestadorValor() {
        return maTipoDocumentoPrestadorValor;
    }

    public void setMaTipoDocumentoPrestadorValor(String maTipoDocumentoPrestadorValor) {
        this.maTipoDocumentoPrestadorValor = maTipoDocumentoPrestadorValor;
    }

    public String getPrestadorNumeroDocumento() {
        return prestadorNumeroDocumento;
    }

    public void setPrestadorNumeroDocumento(String prestadorNumeroDocumento) {
        this.prestadorNumeroDocumento = prestadorNumeroDocumento;
    }

    public String getPrestadorRazonSocial() {
        return prestadorRazonSocial;
    }

    public void setPrestadorRazonSocial(String prestadorRazonSocial) {
        this.prestadorRazonSocial = prestadorRazonSocial;
    }

    public String getSedeCodigoHabilitacion() {
        return sedeCodigoHabilitacion;
    }

    public void setSedeCodigoHabilitacion(String sedeCodigoHabilitacion) {
        this.sedeCodigoHabilitacion = sedeCodigoHabilitacion;
    }

    public String getNumeroPrescripcion() {
        return numeroPrescripcion;
    }

    public void setNumeroPrescripcion(String numeroPrescripcion) {
        this.numeroPrescripcion = numeroPrescripcion;
    }

    public String getConsecutivoMipres() {
        return consecutivoMipres;
    }

    public void setConsecutivoMipres(String consecutivoMipres) {
        this.consecutivoMipres = consecutivoMipres;
    }

    public Date getFechaPrescripcion() {
        return fechaPrescripcion;
    }

    public void setFechaPrescripcion(Date fechaPrescripcion) {
        this.fechaPrescripcion = fechaPrescripcion;
    }

    public Date getHoraPrescripcion() {
        return horaPrescripcion;
    }

    public void setHoraPrescripcion(Date horaPrescripcion) {
        this.horaPrescripcion = horaPrescripcion;
    }

    public Boolean getTipoPrescripcion() {
        return tipoPrescripcion;
    }

    public void setTipoPrescripcion(Boolean tipoPrescripcion) {
        this.tipoPrescripcion = tipoPrescripcion;
    }

    public String getCodAmbAte() {
        return codAmbAte;
    }

    public void setCodAmbAte(String codAmbAte) {
        this.codAmbAte = codAmbAte;
    }

    public boolean isReferenciaAmbitoAtencion() {
        return referenciaAmbitoAtencion;
    }

    public void setReferenciaAmbitoAtencion(boolean referenciaAmbitoAtencion) {
        this.referenciaAmbitoAtencion = referenciaAmbitoAtencion;
    }

    public boolean isPacienteCovid19() {
        return pacienteCovid19;
    }

    public void setPacienteCovid19(boolean pacienteCovid19) {
        this.pacienteCovid19 = pacienteCovid19;
    }

    public boolean isEnfermedadHuerfana() {
        return enfermedadHuerfana;
    }

    public void setEnfermedadHuerfana(boolean enfermedadHuerfana) {
        this.enfermedadHuerfana = enfermedadHuerfana;
    }

    public String getCodigoEnfermedadHuerfana() {
        return codigoEnfermedadHuerfana;
    }

    public void setCodigoEnfermedadHuerfana(String codigoEnfermedadHuerfana) {
        this.codigoEnfermedadHuerfana = codigoEnfermedadHuerfana;
    }

    public boolean isDiagnosticoEnfermedadHuerfana() {
        return diagnosticoEnfermedadHuerfana;
    }

    public void setDiagnosticoEnfermedadHuerfana(boolean diagnosticoEnfermedadHuerfana) {
        this.diagnosticoEnfermedadHuerfana = diagnosticoEnfermedadHuerfana;
    }

    public Integer getMaDiagnosticoPrincipalId() {
        return maDiagnosticoPrincipalId;
    }

    public void setMaDiagnosticoPrincipalId(Integer maDiagnosticoPrincipalId) {
        this.maDiagnosticoPrincipalId = maDiagnosticoPrincipalId;
    }

    public String getMaDiagnosticoPrincipalCodigo() {
        return maDiagnosticoPrincipalCodigo;
    }

    public void setMaDiagnosticoPrincipalCodigo(String maDiagnosticoPrincipalCodigo) {
        this.maDiagnosticoPrincipalCodigo = maDiagnosticoPrincipalCodigo;
    }

    public String getMaDiagnosticoPrincipalValor() {
        return maDiagnosticoPrincipalValor;
    }

    public void setMaDiagnosticoPrincipalValor(String maDiagnosticoPrincipalValor) {
        this.maDiagnosticoPrincipalValor = maDiagnosticoPrincipalValor;
    }

    public Integer getMaDiagnosticoRelacionado1Id() {
        return maDiagnosticoRelacionado1Id;
    }

    public void setMaDiagnosticoRelacionado1Id(Integer maDiagnosticoRelacionado1Id) {
        this.maDiagnosticoRelacionado1Id = maDiagnosticoRelacionado1Id;
    }

    public String getMaDiagnosticoRelacionado1Codigo() {
        return maDiagnosticoRelacionado1Codigo;
    }

    public void setMaDiagnosticoRelacionado1Codigo(String maDiagnosticoRelacionado1Codigo) {
        this.maDiagnosticoRelacionado1Codigo = maDiagnosticoRelacionado1Codigo;
    }

    public String getMaDiagnosticoRelacionado1Valor() {
        return maDiagnosticoRelacionado1Valor;
    }

    public void setMaDiagnosticoRelacionado1Valor(String maDiagnosticoRelacionado1Valor) {
        this.maDiagnosticoRelacionado1Valor = maDiagnosticoRelacionado1Valor;
    }

    public Integer getMaDiagnosticoRelacionado2Id() {
        return maDiagnosticoRelacionado2Id;
    }

    public void setMaDiagnosticoRelacionado2Id(Integer maDiagnosticoRelacionado2Id) {
        this.maDiagnosticoRelacionado2Id = maDiagnosticoRelacionado2Id;
    }

    public String getMaDiagnosticoRelacionado2Codigo() {
        return maDiagnosticoRelacionado2Codigo;
    }

    public void setMaDiagnosticoRelacionado2Codigo(String maDiagnosticoRelacionado2Codigo) {
        this.maDiagnosticoRelacionado2Codigo = maDiagnosticoRelacionado2Codigo;
    }

    public String getMaDiagnosticoRelacionado2Valor() {
        return maDiagnosticoRelacionado2Valor;
    }

    public void setMaDiagnosticoRelacionado2Valor(String maDiagnosticoRelacionado2Valor) {
        this.maDiagnosticoRelacionado2Valor = maDiagnosticoRelacionado2Valor;
    }

    public String getSopNutricional() {
        return sopNutricional;
    }

    public void setSopNutricional(String sopNutricional) {
        this.sopNutricional = sopNutricional;
    }

    public String getCodigoEps() {
        return codigoEps;
    }

    public void setCodigoEps(String codigoEps) {
        this.codigoEps = codigoEps;
    }

    public String getAsegAfiliadoMadreTipoDocumento() {
        return asegAfiliadoMadreTipoDocumento;
    }

    public void setAsegAfiliadoMadreTipoDocumento(String asegAfiliadoMadreTipoDocumento) {
        this.asegAfiliadoMadreTipoDocumento = asegAfiliadoMadreTipoDocumento;
    }

    public String getAsegAfiliadoMadreDocumento() {
        return asegAfiliadoMadreDocumento;
    }

    public void setAsegAfiliadoMadreDocumento(String asegAfiliadoMadreDocumento) {
        this.asegAfiliadoMadreDocumento = asegAfiliadoMadreDocumento;
    }

    public Integer getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(Integer tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public String getNumeroDocumentoDonanteVivo() {
        return numeroDocumentoDonanteVivo;
    }

    public void setNumeroDocumentoDonanteVivo(String numeroDocumentoDonanteVivo) {
        this.numeroDocumentoDonanteVivo = numeroDocumentoDonanteVivo;
    }

    public String getTipoDocumentoDonanteVivo() {
        return tipoDocumentoDonanteVivo;
    }

    public void setTipoDocumentoDonanteVivo(String tipoDocumentoDonanteVivo) {
        this.tipoDocumentoDonanteVivo = tipoDocumentoDonanteVivo;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getEstadoStr() {
        String val = "";

        switch (estado) {
            case 1: {
                val = "Modificado";
            }
            break;
            case 2: {
                val = "Anulado";
            }
            break;
            case 4: {
                val = "Activo";
            }
            break;
            case 5: {
                val = "Solicitud Anulación";
            }
            break;

        }
        return val;
    }

    public boolean isRecobrante() {
        return recobrante;
    }

    public void setRecobrante(boolean recobrante) {
        this.recobrante = recobrante;
    }

    public Boolean getReferenciaAmbitoAtencion() {
        return referenciaAmbitoAtencion;
    }

    public void setReferenciaAmbitoAtencion(Boolean referenciaAmbitoAtencion) {
        this.referenciaAmbitoAtencion = referenciaAmbitoAtencion;
    }

    public Boolean getPacienteCovid19() {
        return pacienteCovid19;
    }

    public void setPacienteCovid19(Boolean pacienteCovid19) {
        this.pacienteCovid19 = pacienteCovid19;
    }

    public Boolean getAfectaPresMax() {
        return afectaPresMax;
    }

    public void setAfectaPresMax(Boolean afectaPresMax) {
        this.afectaPresMax = afectaPresMax;
    }

    public String getCompradorHomologo() {
        return compradorHomologo;
    }

    public void setCompradorHomologo(String compradorHomologo) {
        this.compradorHomologo = compradorHomologo;
    }

    public String getTranscripcion() {
        return transcripcion;
    }

    public void setTranscripcion(String transcripcion) {
        this.transcripcion = transcripcion;
    }

    public String getDireccionIpsPrescriptora() {
        return direccionIpsPrescriptora;
    }

    public void setDireccionIpsPrescriptora(String direccionIpsPrescriptora) {
        this.direccionIpsPrescriptora = direccionIpsPrescriptora;
    }

    public String getTelefonoIpsPrescriptora() {
        return telefonoIpsPrescriptora;
    }

    public void setTelefonoIpsPrescriptora(String telefonoIpsPrescriptora) {
        this.telefonoIpsPrescriptora = telefonoIpsPrescriptora;
    }

    public String getCodHabilitacionIpsPrescriptora() {
        return codHabilitacionIpsPrescriptora;
    }

    public void setCodHabilitacionIpsPrescriptora(String codHabilitacionIpsPrescriptora) {
        this.codHabilitacionIpsPrescriptora = codHabilitacionIpsPrescriptora;
    }

    public Integer getMunicipioIpsPrescriptora() {
        return municipioIpsPrescriptora;
    }

    public void setMunicipioIpsPrescriptora(Integer municipioIpsPrescriptora) {
        this.municipioIpsPrescriptora = municipioIpsPrescriptora;
    }

    public Boolean getDerechosVerificados() {
        return derechosVerificados;
    }

    public void setDerechosVerificados(Boolean derechosVerificados) {
        this.derechosVerificados = derechosVerificados;
    }

    public Boolean getPortabilidad() {
        return portabilidad;
    }

    public void setPortabilidad(Boolean portabilidad) {
        this.portabilidad = portabilidad;
    }

    public Integer getMunicipioPortabilidad() {
        return municipioPortabilidad;
    }

    public void setMunicipioPortabilidad(Integer municipioPortabilidad) {
        this.municipioPortabilidad = municipioPortabilidad;
    }

    public String getTransferidaPor() {
        return transferidaPor;
    }

    public void setTransferidaPor(String transferidaPor) {
        this.transferidaPor = transferidaPor;
    }

    public String getActualizadaPor() {
        return actualizadaPor;
    }

    public void setActualizadaPor(String actualizadaPor) {
        this.actualizadaPor = actualizadaPor;
    }

    public Boolean getRequiereAnulacion() {
        return requiereAnulacion;
    }

    public void setRequiereAnulacion(Boolean requiereAnulacion) {
        this.requiereAnulacion = requiereAnulacion;
    }

    public String getNotaAuditoria() {
        return notaAuditoria;
    }

    public void setNotaAuditoria(String notaAuditoria) {
        this.notaAuditoria = notaAuditoria;
    }

    public AsegAfiliado getAsegAfiliado() {
        return asegAfiliado;
    }

    public void setAsegAfiliado(AsegAfiliado asegAfiliado) {
        this.asegAfiliado = asegAfiliado;
    }

    public CntProfesional getCntProfesional() {
        return cntProfesional;
    }

    public void setCntProfesional(CntProfesional cntProfesional) {
        this.cntProfesional = cntProfesional;
    }

    public CntPrestadorSede getCntPrestadorSede() {
        return cntPrestadorSede;
    }

    public void setCntPrestadorSede(CntPrestadorSede cntPrestadorSede) {
        this.cntPrestadorSede = cntPrestadorSede;
    }

    public Integer getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Integer medicamento) {
        this.medicamento = medicamento;
    }

    public Integer getComplementario() {
        return complementario;
    }

    public void setComplementario(Integer complementario) {
        this.complementario = complementario;
    }

    public Integer getInsumo() {
        return insumo;
    }

    public void setInsumo(Integer insumo) {
        this.insumo = insumo;
    }

    public Integer getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(Integer tecnologia) {
        this.tecnologia = tecnologia;
    }

    public Integer getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(Integer procedimiento) {
        this.procedimiento = procedimiento;
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Integer getTipoTecnologiaItem() {
        return tipoTecnologiaItem;
    }

    public void setTipoTecnologiaItem(Integer tipoTecnologiaItem) {
        this.tipoTecnologiaItem = tipoTecnologiaItem;
    }

    public Boolean getRecobrante() {
        return recobrante;
    }

    public void setRecobrante(Boolean recobrante) {
        this.recobrante = recobrante;
    }

    public MpAfiliado getMpAfiliado() {
        return mpAfiliado;
    }

    public void setMpAfiliado(MpAfiliado mpAfiliado) {
        this.mpAfiliado = mpAfiliado;
    }

    public String getRecobranteStr() {
        String resultado = "NO";
        if (recobrante) {
            resultado = "SI";
        }
        return resultado;
    }

    public String getStrTipoTecnologia(Integer nTipoTecnologia) {
        String chTipoTecnologia = "";
        switch (nTipoTecnologia) {
            case TIPO_MEDICAMENTO:
                chTipoTecnologia = STR_TIPO_MEDICAMENTO;
                break;
            case TIPO_TECNOLOGIA:
                chTipoTecnologia = STR_TIPO_TECNOLOGIA;
                break;
            case TIPO_DISPOSITIVO_MEDICO:
                chTipoTecnologia = STR_TIPO_DISPOSITIVO_MEDICO;
                break;
            case TIPO_PRODUCTO_NUTRICIONAL:
                chTipoTecnologia = STR_TIPO_PRODUCTO_NUTRICIONAL;
                break;
            case TIPO_SERVICIO_COMPLEMENTARIO:
                chTipoTecnologia = STR_TIPO_SERVICIO_COMPLEMENTARIO;
                break;
        }
        return chTipoTecnologia;
    }

    @Override
    public String toString() {
        return "MpPrescripcion{" + "id=" + id + ", recobrante=" + recobrante + ", maTipoDocumentoPrestadorId=" + maTipoDocumentoPrestadorId + ", maTipoDocumentoPrestadorCodigo=" + maTipoDocumentoPrestadorCodigo + ", maTipoDocumentoPrestadorValor=" + maTipoDocumentoPrestadorValor + ", prestadorNumeroDocumento=" + prestadorNumeroDocumento + ", prestadorRazonSocial=" + prestadorRazonSocial + ", sedeCodigoHabilitacion=" + sedeCodigoHabilitacion + ", numeroPrescripcion=" + numeroPrescripcion + ", consecutivoMipres=" + consecutivoMipres + ", fechaPrescripcion=" + fechaPrescripcion + ", horaPrescripcion=" + horaPrescripcion + ", tipoPrescripcion=" + tipoPrescripcion + ", codAmbAte=" + codAmbAte + ", referenciaAmbitoAtencion=" + referenciaAmbitoAtencion + ", pacienteCovid19=" + pacienteCovid19 + ", enfermedadHuerfana=" + enfermedadHuerfana + ", codigoEnfermedadHuerfana=" + codigoEnfermedadHuerfana + ", diagnosticoEnfermedadHuerfana=" + diagnosticoEnfermedadHuerfana + ", maDiagnosticoPrincipalId=" + maDiagnosticoPrincipalId + ", maDiagnosticoPrincipalCodigo=" + maDiagnosticoPrincipalCodigo + ", maDiagnosticoPrincipalValor=" + maDiagnosticoPrincipalValor + ", maDiagnosticoRelacionado1Id=" + maDiagnosticoRelacionado1Id + ", maDiagnosticoRelacionado1Codigo=" + maDiagnosticoRelacionado1Codigo + ", maDiagnosticoRelacionado1Valor=" + maDiagnosticoRelacionado1Valor + ", maDiagnosticoRelacionado2Id=" + maDiagnosticoRelacionado2Id + ", maDiagnosticoRelacionado2Codigo=" + maDiagnosticoRelacionado2Codigo + ", maDiagnosticoRelacionado2Valor=" + maDiagnosticoRelacionado2Valor + ", sopNutricional=" + sopNutricional + ", codigoEps=" + codigoEps + ", asegAfiliadoMadreTipoDocumento=" + asegAfiliadoMadreTipoDocumento + ", asegAfiliadoMadreDocumento=" + asegAfiliadoMadreDocumento + ", tipoTransaccion=" + tipoTransaccion + ", numeroDocumentoDonanteVivo=" + numeroDocumentoDonanteVivo + ", tipoDocumentoDonanteVivo=" + tipoDocumentoDonanteVivo + ", estado=" + estado + ", afectaPresMax=" + afectaPresMax + ", compradorHomologo=" + compradorHomologo + ", transcripcion=" + transcripcion + ", direccionIpsPrescriptora=" + direccionIpsPrescriptora + ", telefonoIpsPrescriptora=" + telefonoIpsPrescriptora + ", codHabilitacionIpsPrescriptora=" + codHabilitacionIpsPrescriptora + ", municipioIpsPrescriptora=" + municipioIpsPrescriptora + ", derechosVerificados=" + derechosVerificados + ", portabilidad=" + portabilidad + ", municipioPortabilidad=" + municipioPortabilidad + ", transferidaPor=" + transferidaPor + ", actualizadaPor=" + actualizadaPor + ", requiereAnulacion=" + requiereAnulacion + ", notaAuditoria=" + notaAuditoria + ", asegAfiliado=" + asegAfiliado + ", cntProfesional=" + cntProfesional + ", cntPrestadorSede=" + cntPrestadorSede + '}';
    }

}
