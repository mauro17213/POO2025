/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.crue;

import com.saviasaludeps.savia.dominio.contratacion.CntProfesional;
import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public class RefAnexo9 extends Auditoria {

    public static final int TIPO_SOLICITUD_REFERENCIA = 9;
    public static final int TIPO_SOLICITUD_CONTRAREFERENCIA = 10;

    public static final int FUENTE_ORIGEN_MANUAL = 1;
    public static final int FUENTE_ORIGEN_CARGA_MASIVA = 2;
    public static final int FUENTE_ORIGEN_INTEROPERABILIDAD = 3;
    
    //Parametros para variables Acta de hospitalización
    public static final String LISTA_DIAGNOSTICOS = "LISTA_DIAGNOSTICOS";
    public static final String LISTA_GESTION = "LISTA_GESTION";
    //
    public static final String SEMAFORO_HORA_MANANA = "07:00:00";
    public static final String SEMAFORO_HORA_TARDE = "19:00:00";
    public static final String SEMAFORO_HORA_CAMBIO = "06:59:59";
    
    //para validar fecha de agreso en las gestiones
    public static final Integer ID_SERVICIO_HABILIDADO = 5;
     
    // 
    public static final int VERSION_0 = 0;
    public static final int VERSION_1 = 1;
    
    //semaforo de maeMaternoPerinatalCodigo
    public static final String ESTADO_CERRADA = "9";
    public static final String ESTADO_CANCELADA = "6";
    public static final String ESTADO_ANAULADA = "5";
    public static final String MAESTRO_CODIGO_MATERNO_PERINATAL = "3";
    
    private Integer id;
    private int tipo;
    private String numeroSolicitud;
    private Date fechaHoraSolicitud;
    private Date fechaHoraRegistro;
    private boolean aplicaNoIpsContrato;
    private boolean aplicaNoAfiliado;
    private Integer maEspecialidadesId;
    private String maEspecialidadCodigo;
    private String maEspecialidadValor;
    private int maServicioSolicitaId;
    private String maServicioSolicitaCodigo;
    private String maServicioSolicitaValor;
    private String motivo;
    private Integer maServicioRemiteId;
    private String maServicioRemiteCodigo;
    private String maServicioRemiteValor;
    private String ubicacion;
    private String cama;
    private String numeroTiket;
    private Boolean aplicaLdf;
    private Boolean aplicaMaterna;
    private Boolean aplicaNeonato;
    private Integer maeCanalComunicacionId;
    private String maeCanalComunicacionCodigo;
    private String maeCanalComunicacionValor;
    private Integer estado;
    private Integer maeEstadoId;
    private Integer fuenteOrigen;
    private String maeEstadoCodigo;
    private String maeEstadoValor;
    private Integer maeAcompananteTipoDocumentoId;
    private String maeAcompananteTipoDocumentoCodigo;
    private String maeAcompananteTipoDocumentoValor;
    private String acompananteDocumento;
    private String acompanantePrimerNombre;
    private String acompananteSegundoNombre;
    private String acompanantePrimerApellido;
    private String acompananteSegundoApellido;
    private String acompananteTelefono;
    private String acompananteDireccion;
    private String acompananteMunicipio;
    private String acompananteDepartamento;
    private String informanteNombre;
    private String informanteTelefono;
    private String informanteCargo;
    private String profesionalSolicitaNombre;
    private String profesionalSolicitaTelefono;
    private List<RefAnexo9Gestion> listaRefAnexo9Gestion;
    private List<RefAnexo9Diagnostico> listaRefAnexo9Diagnostico;
    private List<RefAnexo9Estado> listaRefAnexo9Estado;
    private List<RefAnexo9DatoClinico> listaRefAnexo9DatosClinico;
    private List<RefTransporte> listaRefTransporte;
    private List<RefAnexo9Adjunto> listaRefAnexo9Adjunto;
    private AsegAfiliado asegAfiliado;
    private CntPrestadorSede cntPrestadorSede;
    private CntPrestadorSede cntPrestadorSedesUbicacion;
    private CntProfesional cntProfesionales;
    private boolean newProfesional;
    private boolean diagnosticoEmergente;
    private Empresa gnEmpresa;
    private Empresa gnEmpresaUbicacion;
    
    private Integer version;
    private String consecutivo;
    private Integer maeCausaExternaId;
    private String maeCausaExternaCodigo;
    private String maeCausaExternaValor;
    private Integer maeCondicionDestinoId;
    private String maeCondicionDestinoCodigo;
    private String maeCondicionDestinoValor;
    private Boolean prioridad;
    private Integer maeTipoAtencionId;
    private String maeTipoAtencionCodigo;
    private String maeTipoAtencionValor;
    private Integer maeUbicacionId;
    private String maeUbicacionCodigo;
    private String maeUbicacionValor;
    private Integer maeModalidadTecnologiaId;
    private String maeModalidadTecnologiaCodigo;
    private String maeModalidadTecnologiaValor;
    private Integer tipoTecnologia;
    private Integer maTecnologiaId;
    private String maTecnologiaCodigo;
    private String maTecnologiaValor;
    private Integer cantidadTecnologiaSolicitada;
    private Integer autorizacion;
    private String afiliadoDireccionAlternativa;
    private String nombreContactoEmergencia;
    private String telefonoContactoEmergencia;
    private Date fechaHoraFinGestion;
    private Date fechaHoraInicioGestion;
    private Integer diasGestion;
    private Date fechaHoraUltimaGestion;
    private Date fechaHoraAdjuntoEvolucion;
    //campo nuevo 23-10-2024
    private boolean requiereContraste;
    private boolean requiereSedacion;
    private boolean examenBag; 
    private Integer maeTipoAislamientoId;
    private String maeTipoAislamientoCodigo;
    private String maeTipoAislamientoValor;
    private String maeTipoAislamientoTipo;
    private Integer maeMaternoPerinatalId;
    private String maeMaternoPerinatalCodigo;
    private String maeMaternoPerinatalValor;
    private String maeMaternoPerinatalTipo;
    
    private RefAnexo9DatoClinico refAnexo9DatoClinico;
    private RefAnexo9Estado refAnexo9Estado;
    private RefAnexo9Gestion refAnexo9Gestion;
    
    private boolean habilitarCodigoCUPSprocedimientoRequerido;
    //semaforos agendamiento y efectividad
    private int semaforoAgendamiento;
    private int semaforoEfectividad;
    // resoluccion 2335
    private boolean habilitarCups;
    private boolean habilitarDireccionAlternativa;
    private boolean habilitarNombreContactoEmergencia;
    private boolean habilitarTelefonoContactoEmergencia;
    private boolean habilitarMaeCausaExternaId;
    private boolean habilitarPrioridad;
    private boolean habilitarMaeTipoAtencionId;
    private boolean habilitarGrupoServicio;
    private boolean habilitarMaeModalidadTecnologiaId;
    private boolean habilitarMaeCondicionDestinoId;
    private boolean habilitarMaeMaternoPerinatalId;
    
    public RefAnexo9() {
    }

    public RefAnexo9(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public String getTipoStr() {
        String tipoStr = "";

        if (RefAnexo9.TIPO_SOLICITUD_REFERENCIA == tipo) {
            tipoStr = "Referencia";
        } else {
            tipoStr = "Contrareferencia";
        }

        return tipoStr;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(String numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }

    public Date getFechaHoraSolicitud() {
        return fechaHoraSolicitud;
    }

    public void setFechaHoraSolicitud(Date fechaHoraSolicitud) {
        this.fechaHoraSolicitud = fechaHoraSolicitud;
    }

    public Date getFechaHoraRegistro() {
        return fechaHoraRegistro;
    }

    public void setFechaHoraRegistro(Date fechaHoraRegistro) {
        this.fechaHoraRegistro = fechaHoraRegistro;
    }

    public boolean isAplicaNoIpsContrato() {
        return aplicaNoIpsContrato;
    }

    public void setAplicaNoIpsContrato(boolean aplicaNoIpsContrato) {
        this.aplicaNoIpsContrato = aplicaNoIpsContrato;
    }

    public boolean isAplicaNoAfiliado() {
        return aplicaNoAfiliado;
    }

    public void setAplicaNoAfiliado(boolean aplicaNoAfiliado) {
        this.aplicaNoAfiliado = aplicaNoAfiliado;
    }

    public Integer getMaEspecialidadesId() {
        return maEspecialidadesId;
    }

    public void setMaEspecialidadesId(Integer maEspecialidadesId) {
        this.maEspecialidadesId = maEspecialidadesId;
    }

    public String getMaEspecialidadCodigo() {
        return maEspecialidadCodigo;
    }

    public void setMaEspecialidadCodigo(String maEspecialidadCodigo) {
        this.maEspecialidadCodigo = maEspecialidadCodigo;
    }

    public String getMaEspecialidadValor() {
        return maEspecialidadValor;
    }

    public void setMaEspecialidadValor(String maEspecialidadValor) {
        this.maEspecialidadValor = maEspecialidadValor;
    }

    public int getMaServicioSolicitaId() {
        return maServicioSolicitaId;
    }

    public void setMaServicioSolicitaId(int maServicioSolicitaId) {
        this.maServicioSolicitaId = maServicioSolicitaId;
    }

    public String getMaServicioSolicitaCodigo() {
        return maServicioSolicitaCodigo;
    }

    public void setMaServicioSolicitaCodigo(String maServicioSolicitaCodigo) {
        this.maServicioSolicitaCodigo = maServicioSolicitaCodigo;
    }

    public String getMaServicioSolicitaValor() {
        return maServicioSolicitaValor;
    }

    public void setMaServicioSolicitaValor(String maServicioSolicitaValor) {
        this.maServicioSolicitaValor = maServicioSolicitaValor;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Integer getMaServicioRemiteId() {
        return maServicioRemiteId;
    }

    public void setMaServicioRemiteId(Integer maServicioRemiteId) {
        this.maServicioRemiteId = maServicioRemiteId;
    }

    public String getMaServicioRemiteCodigo() {
        return maServicioRemiteCodigo;
    }

    public void setMaServicioRemiteCodigo(String maServicioRemiteCodigo) {
        this.maServicioRemiteCodigo = maServicioRemiteCodigo;
    }

    public String getMaServicioRemiteValor() {
        return maServicioRemiteValor;
    }

    public void setMaServicioRemiteValor(String maServicioRemiteValor) {
        this.maServicioRemiteValor = maServicioRemiteValor;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCama() {
        return cama;
    }

    public void setCama(String cama) {
        this.cama = cama;
    }

    public String getNumeroTiket() {
        return numeroTiket;
    }

    public void setNumeroTiket(String numeroTiket) {
        this.numeroTiket = numeroTiket;
    }

    public Boolean getAplicaLdf() {
        return aplicaLdf;
    }

    public void setAplicaLdf(Boolean aplicaLdf) {
        this.aplicaLdf = aplicaLdf;
    }

    public Boolean getAplicaMaterna() {
        return aplicaMaterna;
    }

    public void setAplicaMaterna(Boolean aplicaMaterna) {
        this.aplicaMaterna = aplicaMaterna;
    }

    public Boolean getAplicaNeonato() {
        return aplicaNeonato;
    }

    public void setAplicaNeonato(Boolean aplicaNeonato) {
        this.aplicaNeonato = aplicaNeonato;
    }

    public Integer getMaeCanalComunicacionId() {
        return maeCanalComunicacionId;
    }

    public void setMaeCanalComunicacionId(Integer maeCanalComunicacionId) {
        this.maeCanalComunicacionId = maeCanalComunicacionId;
    }

    public String getMaeCanalComunicacionCodigo() {
        return maeCanalComunicacionCodigo;
    }

    public void setMaeCanalComunicacionCodigo(String maeCanalComunicacionCodigo) {
        this.maeCanalComunicacionCodigo = maeCanalComunicacionCodigo;
    }

    public String getMaeCanalComunicacionValor() {
        return maeCanalComunicacionValor;
    }

    public void setMaeCanalComunicacionValor(String maeCanalComunicacionValor) {
        this.maeCanalComunicacionValor = maeCanalComunicacionValor;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getMaeAcompananteTipoDocumentoId() {
        return maeAcompananteTipoDocumentoId;
    }

    public void setMaeAcompananteTipoDocumentoId(Integer maeAcompananteTipoDocumentoId) {
        this.maeAcompananteTipoDocumentoId = maeAcompananteTipoDocumentoId;
    }

    public String getMaeAcompananteTipoDocumentoCodigo() {
        return maeAcompananteTipoDocumentoCodigo;
    }

    public void setMaeAcompananteTipoDocumentoCodigo(String maeAcompananteTipoDocumentoCodigo) {
        this.maeAcompananteTipoDocumentoCodigo = maeAcompananteTipoDocumentoCodigo;
    }

    public String getMaeAcompananteTipoDocumentoValor() {
        return maeAcompananteTipoDocumentoValor;
    }

    public void setMaeAcompananteTipoDocumentoValor(String maeAcompananteTipoDocumentoValor) {
        this.maeAcompananteTipoDocumentoValor = maeAcompananteTipoDocumentoValor;
    }

    public String getAcompananteDocumento() {
        return acompananteDocumento;
    }

    public void setAcompananteDocumento(String acompananteDocumento) {
        this.acompananteDocumento = acompananteDocumento;
    }

    public String getAcompanantePrimerNombre() {
        return acompanantePrimerNombre;
    }

    public void setAcompanantePrimerNombre(String acompanantePrimerNombre) {
        this.acompanantePrimerNombre = acompanantePrimerNombre;
    }

    public String getAcompananteSegundoNombre() {
        return acompananteSegundoNombre;
    }

    public void setAcompananteSegundoNombre(String acompananteSegundoNombre) {
        this.acompananteSegundoNombre = acompananteSegundoNombre;
    }

    public String getAcompanantePrimerApellido() {
        return acompanantePrimerApellido;
    }

    public void setAcompanantePrimerApellido(String acompanantePrimerApellido) {
        this.acompanantePrimerApellido = acompanantePrimerApellido;
    }

    public String getAcompananteSegundoApellido() {
        return acompananteSegundoApellido;
    }

    public void setAcompananteSegundoApellido(String acompananteSegundoApellido) {
        this.acompananteSegundoApellido = acompananteSegundoApellido;
    }

    public String getAcompananteTelefono() {
        return acompananteTelefono;
    }

    public void setAcompananteTelefono(String acompananteTelefono) {
        this.acompananteTelefono = acompananteTelefono;
    }

    public String getAcompananteDireccion() {
        return acompananteDireccion;
    }

    public void setAcompananteDireccion(String acompananteDireccion) {
        this.acompananteDireccion = acompananteDireccion;
    }

    public String getAcompananteMunicipio() {
        return acompananteMunicipio;
    }

    public void setAcompananteMunicipio(String acompananteMunicipio) {
        this.acompananteMunicipio = acompananteMunicipio;
    }

    public String getAcompananteDepartamento() {
        return acompananteDepartamento;
    }

    public void setAcompananteDepartamento(String acompananteDepartamento) {
        this.acompananteDepartamento = acompananteDepartamento;
    }

    public String getInformanteNombre() {
        return informanteNombre;
    }

    public void setInformanteNombre(String informanteNombre) {
        this.informanteNombre = informanteNombre;
    }

    public String getInformanteTelefono() {
        return informanteTelefono;
    }

    public void setInformanteTelefono(String informanteTelefono) {
        this.informanteTelefono = informanteTelefono;
    }

    public String getInformanteCargo() {
        return informanteCargo;
    }

    public void setInformanteCargo(String informanteCargo) {
        this.informanteCargo = informanteCargo;
    }

    public String getProfesionalSolicitaNombre() {
        return profesionalSolicitaNombre;
    }

    public void setProfesionalSolicitaNombre(String profesionalSolicitaNombre) {
        this.profesionalSolicitaNombre = profesionalSolicitaNombre;
    }

    public String getProfesionalSolicitaTelefono() {
        return profesionalSolicitaTelefono;
    }

    public void setProfesionalSolicitaTelefono(String profesionalSolicitaTelefono) {
        this.profesionalSolicitaTelefono = profesionalSolicitaTelefono;
    }

    public List<RefAnexo9Gestion> getListaRefAnexo9Gestion() {
        return listaRefAnexo9Gestion;
    }

    public void setListaRefAnexo9Gestion(List<RefAnexo9Gestion> listaRefAnexo9Gestion) {
        this.listaRefAnexo9Gestion = listaRefAnexo9Gestion;
    }

    public List<RefAnexo9Estado> getListaRefAnexo9Estado() {
        return listaRefAnexo9Estado;
    }

    public void setListaRefAnexo9Estado(List<RefAnexo9Estado> listaRefAnexo9Estado) {
        this.listaRefAnexo9Estado = listaRefAnexo9Estado;
    }

    public List<RefAnexo9DatoClinico> getListaRefAnexo9DatosClinico() {
        return listaRefAnexo9DatosClinico;
    }

    public void setListaRefAnexo9DatosClinico(List<RefAnexo9DatoClinico> listaRefAnexo9DatosClinico) {
        this.listaRefAnexo9DatosClinico = listaRefAnexo9DatosClinico;
    }

    public List<RefTransporte> getListaRefTransporte() {
        return listaRefTransporte;
    }

    public void setListaRefTransporte(List<RefTransporte> listaRefTransporte) {
        this.listaRefTransporte = listaRefTransporte;
    }

    public List<RefAnexo9Adjunto> getListaRefAnexo9Adjunto() {
        return listaRefAnexo9Adjunto;
    }

    public void setListaRefAnexo9Adjunto(List<RefAnexo9Adjunto> listaRefAnexo9Adjunto) {
        this.listaRefAnexo9Adjunto = listaRefAnexo9Adjunto;
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

    public CntPrestadorSede getCntPrestadorSedesUbicacion() {
        return cntPrestadorSedesUbicacion;
    }

    public void setCntPrestadorSedesUbicacion(CntPrestadorSede cntPrestadorSedesUbicacion) {
        this.cntPrestadorSedesUbicacion = cntPrestadorSedesUbicacion;
    }

    public CntProfesional getCntProfesionales() {
        return cntProfesionales;
    }

    public void setCntProfesionales(CntProfesional cntProfesionales) {
        this.cntProfesionales = cntProfesionales;
    }

    public Empresa getGnEmpresa() {
        return gnEmpresa;
    }

    public void setGnEmpresa(Empresa gnEmpresa) {
        this.gnEmpresa = gnEmpresa;
    }

    public Empresa getGnEmpresaUbicacion() {
        return gnEmpresaUbicacion;
    }

    public void setGnEmpresaUbicacion(Empresa gnEmpresaUbicacion) {
        this.gnEmpresaUbicacion = gnEmpresaUbicacion;
    }

    public RefAnexo9DatoClinico getRefAnexo9DatoClinico() {
        return refAnexo9DatoClinico;
    }

    public void setRefAnexo9DatoClinico(RefAnexo9DatoClinico refAnexo9DatoClinico) {
        this.refAnexo9DatoClinico = refAnexo9DatoClinico;
    }

    public List<RefAnexo9Diagnostico> getListaRefAnexo9Diagnostico() {
        return listaRefAnexo9Diagnostico;
    }

    public void setListaRefAnexo9Diagnostico(List<RefAnexo9Diagnostico> listaRefAnexo9Diagnostico) {
        this.listaRefAnexo9Diagnostico = listaRefAnexo9Diagnostico;
    }

    public RefAnexo9Estado getRefAnexo9Estado() {
        return refAnexo9Estado;
    }

    public void setRefAnexo9Estado(RefAnexo9Estado refAnexo9Estado) {
        this.refAnexo9Estado = refAnexo9Estado;
    }

    public RefAnexo9Gestion getRefAnexo9Gestion() {
        return refAnexo9Gestion;
    }

    public void setRefAnexo9Gestion(RefAnexo9Gestion refAnexo9Gestion) {
        this.refAnexo9Gestion = refAnexo9Gestion;
    }

    public Integer getMaeEstadoId() {
        return maeEstadoId;
    }

    public void setMaeEstadoId(Integer maeEstadoId) {
        this.maeEstadoId = maeEstadoId;
    }

    public String getMaeEstadoCodigo() {
        return maeEstadoCodigo;
    }

    public void setMaeEstadoCodigo(String maeEstadoCodigo) {
        this.maeEstadoCodigo = maeEstadoCodigo;
    }

    public String getMaeEstadoValor() {
        return maeEstadoValor;
    }

    public void setMaeEstadoValor(String maeEstadoValor) {
        this.maeEstadoValor = maeEstadoValor;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Integer getMaeCausaExternaId() {
        return maeCausaExternaId;
    }

    public void setMaeCausaExternaId(Integer meaCausaExternaId) {
        this.maeCausaExternaId = meaCausaExternaId;
    }

    public String getMaeCausaExternaCodigo() {
        return maeCausaExternaCodigo;
    }

    public void setMaeCausaExternaCodigo(String maeCausaExternaCodigo) {
        this.maeCausaExternaCodigo = maeCausaExternaCodigo;
    }

    public String getMaeCausaExternaValor() {
        return maeCausaExternaValor;
    }

    public void setMaeCausaExternaValor(String maeCausaExternaValor) {
        this.maeCausaExternaValor = maeCausaExternaValor;
    }

    public Integer getMaeCondicionDestinoId() {
        return maeCondicionDestinoId;
    }

    public void setMaeCondicionDestinoId(Integer maeCondicionDestinoId) {
        this.maeCondicionDestinoId = maeCondicionDestinoId;
    }

    public String getMaeCondicionDestinoCodigo() {
        return maeCondicionDestinoCodigo;
    }

    public void setMaeCondicionDestinoCodigo(String maeCondicionDestinoCodigo) {
        this.maeCondicionDestinoCodigo = maeCondicionDestinoCodigo;
    }

    public String getMaeCondicionDestinoValor() {
        return maeCondicionDestinoValor;
    }

    public void setMaeCondicionDestinoValor(String maeCondicionDestinoValor) {
        this.maeCondicionDestinoValor = maeCondicionDestinoValor;
    }

    public Boolean getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Boolean prioridad) {
        this.prioridad = prioridad;
    }

    public Integer getMaeTipoAtencionId() {
        return maeTipoAtencionId;
    }

    public void setMaeTipoAtencionId(Integer maeTipoAtencionId) {
        this.maeTipoAtencionId = maeTipoAtencionId;
    }

    public String getMaeTipoAtencionCodigo() {
        return maeTipoAtencionCodigo;
    }

    public void setMaeTipoAtencionCodigo(String maeTipoAtencionCodigo) {
        this.maeTipoAtencionCodigo = maeTipoAtencionCodigo;
    }

    public String getMaeTipoAtencionValor() {
        return maeTipoAtencionValor;
    }

    public void setMaeTipoAtencionValor(String maeTipoAtencionValor) {
        this.maeTipoAtencionValor = maeTipoAtencionValor;
    }

    public Integer getMaeUbicacionId() {
        return maeUbicacionId;
    }

    public void setMaeUbicacionId(Integer maeUbicacionId) {
        this.maeUbicacionId = maeUbicacionId;
    }

    public String getMaeUbicacionCodigo() {
        return maeUbicacionCodigo;
    }

    public void setMaeUbicacionCodigo(String maeUbicacionCodigo) {
        this.maeUbicacionCodigo = maeUbicacionCodigo;
    }

    public String getMaeUbicacionValor() {
        return maeUbicacionValor;
    }

    public void setMaeUbicacionValor(String maeUbicacionValor) {
        this.maeUbicacionValor = maeUbicacionValor;
    }

    public Integer getMaeModalidadTecnologiaId() {
        return maeModalidadTecnologiaId;
    }

    public void setMaeModalidadTecnologiaId(Integer maeModalidadTecnologiaId) {
        this.maeModalidadTecnologiaId = maeModalidadTecnologiaId;
    }

    public String getMaeModalidadTecnologiaCodigo() {
        return maeModalidadTecnologiaCodigo;
    }

    public void setMaeModalidadTecnologiaCodigo(String maeModalidadTecnologiaCodigo) {
        this.maeModalidadTecnologiaCodigo = maeModalidadTecnologiaCodigo;
    }

    public String getMaeModalidadTecnologiaValor() {
        return maeModalidadTecnologiaValor;
    }

    public void setMaeModalidadTecnologiaValor(String maeModalidadTecnologiaValor) {
        this.maeModalidadTecnologiaValor = maeModalidadTecnologiaValor;
    }

    public Integer getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(Integer tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public Integer getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(Integer maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
    }

    public Integer getCantidadTecnologiaSolicitada() {
        return cantidadTecnologiaSolicitada;
    }

    public void setCantidadTecnologiaSolicitada(Integer cantidadTecnologiaSolicitada) {
        this.cantidadTecnologiaSolicitada = cantidadTecnologiaSolicitada;
    }

    public Integer getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(Integer autorizacion) {
        this.autorizacion = autorizacion;
    }

    public String getAfiliadoDireccionAlternativa() {
        return afiliadoDireccionAlternativa;
    }

    public void setAfiliadoDireccionAlternativa(String afiliadoDireccionAlternativa) {
        this.afiliadoDireccionAlternativa = afiliadoDireccionAlternativa;
    }

    public String getNombreContactoEmergencia() {
        return nombreContactoEmergencia;
    }

    public void setNombreContactoEmergencia(String nombreContactoEmergencia) {
        this.nombreContactoEmergencia = nombreContactoEmergencia;
    }

    public String getTelefonoContactoEmergencia() {
        return telefonoContactoEmergencia;
    }

    public void setTelefonoContactoEmergencia(String telefonoContactoEmergencia) {
        this.telefonoContactoEmergencia = telefonoContactoEmergencia;
    }

    public Date getFechaHoraFinGestion() {
        return fechaHoraFinGestion;
    }

    public void setFechaHoraFinGestion(Date fechaHoraFinGestion) {
        this.fechaHoraFinGestion = fechaHoraFinGestion;
    }

    public Date getFechaHoraInicioGestion() {
        return fechaHoraInicioGestion;
    }

    public void setFechaHoraInicioGestion(Date fechaHoraInicioGestion) {
        this.fechaHoraInicioGestion = fechaHoraInicioGestion;
    }

    public Integer getDiasGestion() {
        return diasGestion;
    }

    public void setDiasGestion(Integer diasGestion) {
        this.diasGestion = diasGestion;
    }

    public Date getFechaHoraUltimaGestion() {
        return fechaHoraUltimaGestion;
    }

    public void setFechaHoraUltimaGestion(Date fechaHoraUltimaGestion) {
        this.fechaHoraUltimaGestion = fechaHoraUltimaGestion;
    }

    public Date getFechaHoraAdjuntoEvolucion() {
        return fechaHoraAdjuntoEvolucion;
    }

    public void setFechaHoraAdjuntoEvolucion(Date fechaHoraAdjuntoEvolucion) {
        this.fechaHoraAdjuntoEvolucion = fechaHoraAdjuntoEvolucion;
    }

    public boolean isRequiereContraste() {
        return requiereContraste;
    }

    public void setRequiereContraste(boolean requiereContraste) {
        this.requiereContraste = requiereContraste;
    }

    public boolean isRequiereSedacion() {
        return requiereSedacion;
    }

    public void setRequiereSedacion(boolean requiereSedacion) {
        this.requiereSedacion = requiereSedacion;
    }

    public boolean isExamenBag() {
        return examenBag;
    }

    public void setExamenBag(boolean examenBag) {
        this.examenBag = examenBag;
    }

    public Integer getMaeTipoAislamientoId() {
        return maeTipoAislamientoId;
    }

    public void setMaeTipoAislamientoId(Integer maeTipoAislamientoId) {
        this.maeTipoAislamientoId = maeTipoAislamientoId;
    }

    public String getMaeTipoAislamientoCodigo() {
        return maeTipoAislamientoCodigo;
    }

    public void setMaeTipoAislamientoCodigo(String maeTipoAislamientoCodigo) {
        this.maeTipoAislamientoCodigo = maeTipoAislamientoCodigo;
    }

    public String getMaeTipoAislamientoValor() {
        return maeTipoAislamientoValor;
    }

    public void setMaeTipoAislamientoValor(String maeTipoAislamientoValor) {
        this.maeTipoAislamientoValor = maeTipoAislamientoValor;
    }

    public String getMaeTipoAislamientoTipo() {
        return maeTipoAislamientoTipo;
    }

    public void setMaeTipoAislamientoTipo(String maeTipoAislamientoTipo) {
        this.maeTipoAislamientoTipo = maeTipoAislamientoTipo;
    }

    public Integer getMaeMaternoPerinatalId() {
        return maeMaternoPerinatalId;
    }

    public void setMaeMaternoPerinatalId(Integer maeMaternoPerinatalId) {
        this.maeMaternoPerinatalId = maeMaternoPerinatalId;
    }

    public String getMaeMaternoPerinatalCodigo() {
        return maeMaternoPerinatalCodigo;
    }

    public void setMaeMaternoPerinatalCodigo(String maeMaternoPerinatalCodigo) {
        this.maeMaternoPerinatalCodigo = maeMaternoPerinatalCodigo;
    }

    public String getMaeMaternoPerinatalValor() {
        return maeMaternoPerinatalValor;
    }

    public void setMaeMaternoPerinatalValor(String maeMaternoPerinatalValor) {
        this.maeMaternoPerinatalValor = maeMaternoPerinatalValor;
    }

    public String getMaeMaternoPerinatalTipo() {
        return maeMaternoPerinatalTipo;
    }

    public void setMaeMaternoPerinatalTipo(String maeMaternoPerinatalTipo) {
        this.maeMaternoPerinatalTipo = maeMaternoPerinatalTipo;
    }

    public boolean isHabilitarCodigoCUPSprocedimientoRequerido() {
        return habilitarCodigoCUPSprocedimientoRequerido;
    }

    public void setHabilitarCodigoCUPSprocedimientoRequerido(boolean habilitarCodigoCUPSprocedimientoRequerido) {
        this.habilitarCodigoCUPSprocedimientoRequerido = habilitarCodigoCUPSprocedimientoRequerido;
    }
    
    public boolean isNewProfesional() {
        return newProfesional;
    }

    public void setNewProfesional(boolean newProfesional) {
        this.newProfesional = newProfesional;
    }

    public Integer getFuenteOrigen() {
        return fuenteOrigen;
    }

    public void setFuenteOrigen(Integer fuenteOrigen) {
        this.fuenteOrigen = fuenteOrigen;
    }

    public boolean isDiagnosticoEmergente() {
        return diagnosticoEmergente;
    }

    public void setDiagnosticoEmergente(boolean diagnosticoEmergente) {
        this.diagnosticoEmergente = diagnosticoEmergente;
    }

    public int getSemaforoAgendamiento() {
        return semaforoAgendamiento;
    }

    public void setSemaforoAgendamiento(int semaforoAgendamiento) {
        this.semaforoAgendamiento = semaforoAgendamiento;
    }

    public int getSemaforoEfectividad() {
        return semaforoEfectividad;
    }

    public void setSemaforoEfectividad(int semaforoEfectividad) {
        this.semaforoEfectividad = semaforoEfectividad;
    }

    public boolean isHabilitarCups() {
        return habilitarCups;
    }

    public void setHabilitarCups(boolean habilitarCups) {
        this.habilitarCups = habilitarCups;
    }

    public boolean isHabilitarDireccionAlternativa() {
        return habilitarDireccionAlternativa;
    }

    public void setHabilitarDireccionAlternativa(boolean habilitarDireccionAlternativa) {
        this.habilitarDireccionAlternativa = habilitarDireccionAlternativa;
    }

    public boolean isHabilitarNombreContactoEmergencia() {
        return habilitarNombreContactoEmergencia;
    }

    public void setHabilitarNombreContactoEmergencia(boolean habilitarNombreContactoEmergencia) {
        this.habilitarNombreContactoEmergencia = habilitarNombreContactoEmergencia;
    }

    public boolean isHabilitarTelefonoContactoEmergencia() {
        return habilitarTelefonoContactoEmergencia;
    }

    public void setHabilitarTelefonoContactoEmergencia(boolean habilitarTelefonoContactoEmergencia) {
        this.habilitarTelefonoContactoEmergencia = habilitarTelefonoContactoEmergencia;
    }

    public boolean isHabilitarMaeCausaExternaId() {
        return habilitarMaeCausaExternaId;
    }

    public void setHabilitarMaeCausaExternaId(boolean habilitarMaeCausaExternaId) {
        this.habilitarMaeCausaExternaId = habilitarMaeCausaExternaId;
    }

    public boolean isHabilitarPrioridad() {
        return habilitarPrioridad;
    }

    public void setHabilitarPrioridad(boolean habilitarPrioridad) {
        this.habilitarPrioridad = habilitarPrioridad;
    }

    public boolean isHabilitarMaeTipoAtencionId() {
        return habilitarMaeTipoAtencionId;
    }

    public void setHabilitarMaeTipoAtencionId(boolean habilitarMaeTipoAtencionId) {
        this.habilitarMaeTipoAtencionId = habilitarMaeTipoAtencionId;
    }

    public boolean isHabilitarGrupoServicio() {
        return habilitarGrupoServicio;
    }

    public void setHabilitarGrupoServicio(boolean habilitarGrupoServicio) {
        this.habilitarGrupoServicio = habilitarGrupoServicio;
    }

    public boolean isHabilitarMaeModalidadTecnologiaId() {
        return habilitarMaeModalidadTecnologiaId;
    }

    public void setHabilitarMaeModalidadTecnologiaId(boolean habilitarMaeModalidadTecnologiaId) {
        this.habilitarMaeModalidadTecnologiaId = habilitarMaeModalidadTecnologiaId;
    }

    public boolean isHabilitarMaeCondicionDestinoId() {
        return habilitarMaeCondicionDestinoId;
    }

    public void setHabilitarMaeCondicionDestinoId(boolean habilitarMaeCondicionDestinoId) {
        this.habilitarMaeCondicionDestinoId = habilitarMaeCondicionDestinoId;
    }

    public boolean isHabilitarMaeMaternoPerinatalId() {
        return habilitarMaeMaternoPerinatalId;
    }

    public void setHabilitarMaeMaternoPerinatalId(boolean habilitarMaeMaternoPerinatalId) {
        this.habilitarMaeMaternoPerinatalId = habilitarMaeMaternoPerinatalId;
    }
    
    public int getTiempoCrea() {
        long ini = getFechaHoraCrea().getTime();
        long fin = (new Date()).getTime();
        return (int) ((fin - ini) / 60000);
    }

    public String getTipoSolicitudStr() {
        String tipoStr = "";

        switch (this.tipo) {
            case TIPO_SOLICITUD_REFERENCIA:
                tipoStr = "Referencia";
                break;
            case TIPO_SOLICITUD_CONTRAREFERENCIA:
                tipoStr = "Contra-Referencia";
                break;
            default:
                tipoStr = "";
                break;
        }
        return tipoStr;
    }
    
    public String getPrioridadStr() {
        String prioridadStr = "";
        if(this.prioridad != null){
            if(this.prioridad){
                prioridadStr = "Prioridad";
            }else{
                prioridadStr = "No Prioridad";
            }
        }
        
        return prioridadStr;
    }
    
    public String getFuenteOrigenStr() {
        String srt = "";
        if (this.fuenteOrigen != null) {
            switch (this.fuenteOrigen) {
                case FUENTE_ORIGEN_MANUAL:
                    srt = "Manual";
                    break;
                case FUENTE_ORIGEN_CARGA_MASIVA:
                    srt = "Carga Masiva";
                    break;
                case FUENTE_ORIGEN_INTEROPERABILIDAD:
                    srt = "Interoperabilidad";
                    break;
                default:
                    srt = "";
                    break;
            }
        }
        return srt;
    }
    
    public int calcularDiasGestion() {
        Long fechaInicio = null;
        Long fechaFin = null;
        if (getFechaHoraInicioGestion() != null) {
            fechaInicio = getFechaHoraInicioGestion().getTime();
        } else {
            return 0;
        }
        if (getFechaHoraFinGestion() != null) {
            fechaFin = getFechaHoraFinGestion().getTime();
        } else {
            fechaFin = new Date().getTime();
        }
        int milisecondsByDay = 86400000;
        return (int) ((fechaFin - fechaInicio) / milisecondsByDay);
    }
    
    public long calcularHorasFechasGestion(){
        Long fechaInicio = null;
        Long fechaFin = null;
        if (getFechaHoraInicioGestion() != null) {
            fechaInicio = getFechaHoraInicioGestion().getTime();
        } else {
            return 0;
        }
        if (getFechaHoraFinGestion() != null) {
            fechaFin = getFechaHoraFinGestion().getTime();
        } else {
            fechaFin = new Date().getTime();
        }
        int diferencia = (int) ((fechaFin - fechaInicio) / 1000);
        return (int) Math.floor(diferencia/3600);
    }
    
    public long calcularHorasGestion(){
   
        int dias = calcularDiasGestion();
        Long hora = calcularHorasFechasGestion();
        long horas = (int) (hora -(dias * 24));
        return horas;
    }
    
    public String getRequiereContrasteStr() {
        String requiereContrasteStr = "No";
        if(requiereContraste){
            requiereContrasteStr = "Si";
        }
        return requiereContrasteStr;
    }
    
    public String getRequiereSedacionStr() {
        String requiereSedacionStr = "No";
        if(requiereSedacion){
            requiereSedacionStr = "Si";
        }
        return requiereSedacionStr;
    }
    
    public String getExamenBagStr() {
        String examenBagStr = "No";
        if(examenBag){
            examenBagStr = "Si";
        }
        return examenBagStr;
    }
    
    public String getConsecutivoGen() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyyMMdd");
        StringBuilder consecutivoStr = new StringBuilder();
        formato.format(fechaHoraCrea);
        consecutivoStr.append(formato.format(fechaHoraCrea).replace("-", "")).append(id);
        return consecutivoStr.toString();
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "RefAnexo9{" + "id=" + id + ", tipo=" + tipo + ", numeroSolicitud=" + numeroSolicitud + ", fechaHoraSolicitud=" + (fechaHoraSolicitud != null ? sdf.format(fechaHoraSolicitud) : fechaHoraSolicitud)
                + ", fechaHoraRegistro=" + (fechaHoraRegistro != null ? sdf.format(fechaHoraRegistro) : fechaHoraRegistro) + ", aplicaNoIpsContrato=" + aplicaNoIpsContrato + ", aplicaNoAfiliado=" + aplicaNoAfiliado
                + ", maEspecialidadesId=" + maEspecialidadesId + ", maEspecialidadCodigo=" + maEspecialidadCodigo + ", maEspecialidadValor=" + maEspecialidadValor
                + ", maServicioSolicitaId=" + maServicioSolicitaId + ", maServicioSolicitaCodigo=" + maServicioSolicitaCodigo + ", maServicioSolicitaValor=" + maServicioSolicitaValor
                + ", motivo=" + motivo + ", maServicioRemiteId=" + maServicioRemiteId + ", maServicioRemiteCodigo=" + maServicioRemiteCodigo
                + ", maServicioRemiteValor=" + maServicioRemiteValor + ", ubicacion=" + ubicacion + ", cama=" + cama + ", numeroTiket=" + numeroTiket + ", aplicaLdf=" + aplicaLdf
                + ", aplicaMaterna=" + aplicaMaterna + ", aplicaNeonato=" + aplicaNeonato + ", maeCanalComunicacionId=" + maeCanalComunicacionId
                + ", maeCanalComunicacionCodigo=" + maeCanalComunicacionCodigo + ", maeCanalComunicacionValor=" + maeCanalComunicacionValor + ", maeEstadoId=" + maeEstadoId
                + ", maeEstadoCodigo=" + maeEstadoCodigo + ", maeEstadoValor=" + maeEstadoValor + ", maeAcompananteTipoDocumentoId=" + maeAcompananteTipoDocumentoId
                + ", maeAcompananteTipoDocumentoCodigo=" + maeAcompananteTipoDocumentoCodigo + ", maeAcompananteTipoDocumentoValor=" + maeAcompananteTipoDocumentoValor
                + ", acompananteDocumento=" + acompananteDocumento + ", acompanantePrimerNombre=" + acompanantePrimerNombre + ", acompananteSegundoNombre=" + acompananteSegundoNombre
                + ", acompanantePrimerApellido=" + acompanantePrimerApellido + ", acompananteSegundoApellido=" + acompananteSegundoApellido
                + ", acompananteTelefono=" + acompananteTelefono + ", acompananteDireccion=" + acompananteDireccion + ", acompananteMunicipio=" + acompananteMunicipio
                + ", acompananteDepartamento=" + acompananteDepartamento + ", informanteNombre=" + informanteNombre + ", informanteTelefono=" + informanteTelefono
                + ", informanteCargo=" + informanteCargo + ", profesionalSolicitaNombre=" + profesionalSolicitaNombre + ", profesionalSolicitaTelefono=" + profesionalSolicitaTelefono
                + ", listaRefAnexo9Gestion=" + listaRefAnexo9Gestion + ", listaRefAnexo9Diagnostico=" + listaRefAnexo9Diagnostico + ", listaRefAnexo9Estado=" + listaRefAnexo9Estado
                + ", listaRefAnexo9DatosClinico=" + listaRefAnexo9DatosClinico + ", listaRefTransporte=" + listaRefTransporte + ", listaRefAnexo9Adjunto=" + listaRefAnexo9Adjunto
                + ", asegAfiliado=" + (asegAfiliado != null ? asegAfiliado.getId() : asegAfiliado) + ", cntPrestadorSede=" + (cntPrestadorSede != null ? cntPrestadorSede.getId() : cntPrestadorSede) + ", cntPrestadorSedesUbicacion=" + (cntPrestadorSedesUbicacion != null ? cntPrestadorSedesUbicacion.getId() : cntPrestadorSedesUbicacion)
                + ", cntProfesionales=" + (cntProfesionales != null ? cntProfesionales.getId() : cntProfesionales) + ", gnEmpresa=" + (gnEmpresa != null ? gnEmpresa.getId() : gnEmpresa) + ", gnEmpresaUbicacion=" + (gnEmpresaUbicacion != null ? gnEmpresaUbicacion.getId() : gnEmpresaUbicacion) + ", refAnexo9DatoClinico=" + refAnexo9DatoClinico
                + ", refAnexo9Estado=" + refAnexo9Estado + ", refAnexo9Gestion=" + refAnexo9Gestion 
                + ", semaforoAgendamiento=" + semaforoAgendamiento + ", semaforoEfectividad=" + semaforoEfectividad +'}';
    }
}
