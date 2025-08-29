/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jyperez
 */
public class AsegAfiliadoDTO extends Auditoria implements  Cloneable {

    public static final int SERVICIOWEB_INTEROPERABILIDAD = 3;
    private Integer id;
    private String idAfiliado;
    private BigInteger serialBdua;
    private boolean registroBdua;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private Date fechaNacimiento;
    private String genero;
    private Integer maeGeneroId;
    private String maeGeneroCodigo;
    private String maeGeneroValor;
    private Date fechaExpedicionCedula;
    private int maeTipoDocumento;
    private String maeTipoDocumentoCodigo;
    private String maeTipoDocumentoValor;
    private String numeroDocumento;
    private Integer maeTipoDocumentoCf;
    private String maeTipoDocumentoCfCodigo;
    private String maeTipoDocumentoCfValor;
    private String numeroDocumentoCf;
    private Date fechaAfiliacionSgsss;
    private Date fechaAfiliacionEps;
    private Date fechaEgresoEps;
    private Date fechaCambioEstadoEps;
    private String tipoBeneficiario;
    private Integer maeTipoAfiliadoId;
    private String maeTipoAfiliadoCodigo;
    private String maeTipoAfiliadoValor;
    private int maeEstadoAfiliacion;
    private String maeEstadoAfiliacionCodigo;
    private String maeEstadoAfiliacionValor;
    private int maeOrigenAfiliado;
    private String maeOrigenAfiliadoCodigo;
    private String maeOrigenAfiliadoValor;
    private String parentescoCotizante;
    private Integer maeParentescoCotizanteId;
    private String maeParentescoCotizanteCodigo;
    private String maeParentescoCotizanteValor;
    private Boolean autorizaEnvioSms;
    private Boolean autorizaEnvioEmail;
    private String telefonoFijo;
    private String telefonoMovil;
    private String email;
    private String zona;
    private Integer maeZonaId;
    private String maeZonaCodigo;
    private String maeZonaValor;
    private String direccionResidencia;
    private String barrio;
    private String regimen;
    private String nivelSisben;
    private Double puntajeSisben;
    private String fichaSisben;
    private Date fechaSisben;
    private String categoriaIbc;
    private Integer maeCategoriaIbcId;
    private String maeCategoriaIbcCodigo;
    private String maeCategoriaIbcValor;
    private Boolean tienePortabilidad;
    private Date fechaPortabilidad;
    private String tipoCotizante;
    private Integer maeTipoCotizanteId;
    private String maeTipoCotizanteCodigo;
    private String maeTipoCotizanteValor;
    private boolean discapacidad;
    private String tipoDiscapacidad;
    private Integer maeTipoDiscapacidadId;
    private String maeTipoDiscapacidadCodigo;
    private String maeTipoDiscapacidadValor;
    private String condicionDiscapacidad;
    private Integer maeCondicionDiscapacidadId;
    private String maeCondicionDiscapacidadCodigo;
    private String maeCondicionDiscapacidadValor;
    private Date fechaIniciodiscapacidad;
    private Date fechaFinDiscapacidad;
    private int maeGrupoPoblacional;
    private String maeGrupoPoblacionalCodigo;
    private String maeGrupoPoblacionalValor;
    private Boolean victima;
    private String etnia;
    private Integer maeEtniaId;
    private String maeEtniaCodigo;
    private String maeEtniaValor;
    private Integer maeComunidadEtniaId;
    private String maeComunidadEtniaCodigo;
    private String maeComunidadEtniaValor;
    private int maeCausaNovedad;
    private String maeCausaNovedadCodigo;
    private String maeCausaNovedadValor;
    private Date fechaReactivacion;
    private String estadoCivil;
    private Integer maeEstadoCivilId;
    private String maeEstadoCivilCodigo;
    private String maeEstadoCivilValor;
    private Date fechaMovilidad;
    private String modeloLiquidacion;
    private Integer maeModeloLiquidacionId;
    private String maeModeloLiquidacionCodigo;
    private String maeModeloLiquidacionValor;
    private Integer maeTipoDocumentoAportante;
    private String maeTipoDocumentoAportanteCodigo;
    private String maeTipoDocumentoAportanteValor;
    private String numeroDocumentoAportante;
    private Integer maeActividadEconomica;
    private String maeActividadEconomicaCodigo;
    private String maeActividadEconomicaValor;
    private Integer maeArl;
    private String maeArlCodigo;
    private String maeArlValor;
    private Integer maeAfp;
    private String maeAfpCodigo;
    private String maeAfpValor;
    private Integer maeCajaCompensacion;
    private String maeCajaCompensacionCodigo;
    private String maeCajaCompensacionValor;
    private Integer sincronizado;
    //2021-05-03 jyperez nuevos campos
    private Integer maeGrupoSisbenId;
    private String maeGrupoSisbenCodigo;
    private String maeGrupoSisbenValor;
    //2021-05-11 jyperez nuevos campos
    private String codigoFonetico;
    //2021-05-20 jyperez nuevos campos
    private Integer maeRegimenId;
    private String maeRegimenCodigo;
    private String maeRegimenValor;
    private Integer maeNivelSisbenId;
    private String maeNivelSisbenCodigo;
    private String maeNivelSisbenValor;
    private Integer origenUltimoRegistro;
    //2022-02-21 jyperez nuevos campos
    private Integer maeMetodologiaGrupoPoblacionalId;
    private String maeMetodologiaGrupoPoblacionalCodigo;
    private String maeMetodologiaGrupoPoblacionalValor;
    //2022-04-25 jyperez nuevos campos
    private Integer maeSubGrupoSisbenId;
    private String maeSubGrupoSisbenCodigo;
    private String maeSubGrupoSisbenValor;
    //2022-06-09 jyperez nuevos campos
    private Integer maeSolidariaPorcentajeId;
    private String maeSolidariaPorcentajeCodigo;
    private String maeSolidariaPorcentajeValor;
    
    private CntPrestadorSede primariaPrestadorSede;
    private CntPrestadorSede portabilidadPrestadorSede;
    private Ubicacion nacimientoUbicacion;
    private Ubicacion nacionalidadUbicacion;
    private Ubicacion afiliacionUbicacion;
    private Ubicacion residenciaUbicacion;
    private List<AsegPortabilidad> listaAsegPortabilidades;
    private List<AsegReporteNovedad> listaAsegReporteNovedades;
    private List<AsegRadicadoNovedad> listaAsegRadicadoNovedades;
    private List<AsegTabulacionEncuesta> listaAsegTabulacionEncuestas;
    private List<AsegTraslado> listaAsegTraslados;
    private List<AsegAfiliadoHistorico> listaAsegAfiliadoHistorico;
    private List<AsegAfiliadoContacto> listaAsegAfiliadoContacto;

    // CAMPOS ADICIONALES
    // Campos para Manejo de Dirección
    private String direccionVia;
    private String direccionNro;
    private String direccionOrd1;
    private String direccionOrientacion;
    private String direccionPlaca1;
    private String direccionOrd2;
    private String direccionOrientacion2;
    private String direccionPlaca2;
    private String direccionDescripcion;
    private String observacionNovedad;
    private Date fechaNovedad;
    //2020-08-18 jyperez campos adicionales Control de Cambios - Adicion Campos para el Cabeza de Familia
    private String nombresCF;
    private String apellidosCF;
    //campos para carga masiva
    private AsegTraslado asegTraslado;
    private String errorCarga;
    private String registroArchivo;
    //campos para consulta afiliado
    private Date fechaUltimaNovedad;
    
    //campo edad afiliado
    public transient String edad;
    //2021-11-12 jyperez campo para el manejo del pais de Ubicación
    public Integer paisNacimiento;
    private Integer paisNacionalidad;
    //faltantes

    public AsegAfiliadoDTO() {
    }

    public AsegAfiliadoDTO(Integer id) {
        this.id = id;
    }

    public AsegAfiliadoDTO(Integer id, String idAfiliado, boolean registroBdua, String primerNombre, String primerApellido, Date fechaNacimiento, String genero, int maeTipoDocumento, String numeroDocumento, Date fechaAfiliacionSgsss, Date fechaAfiliacionEps, String tipoBeneficiario, int maeEstadoAfiliacion, int maeOrigenAfiliado, String zona, String direccionResidencia, String regimen, String nivelSisben, boolean discapacidad, int maeGrupoPoblacional, String etnia, int maeCausaNovedad, String estadoCivil, String modeloLiquidacion) {
        this.id = id;
        this.idAfiliado = idAfiliado;
        this.registroBdua = registroBdua;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.maeTipoDocumento = maeTipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.fechaAfiliacionSgsss = fechaAfiliacionSgsss;
        this.fechaAfiliacionEps = fechaAfiliacionEps;
        this.tipoBeneficiario = tipoBeneficiario;
        this.maeEstadoAfiliacion = maeEstadoAfiliacion;
        this.maeOrigenAfiliado = maeOrigenAfiliado;
        this.zona = zona;
        this.direccionResidencia = direccionResidencia;
        this.regimen = regimen;
        this.nivelSisben = nivelSisben;
        this.discapacidad = discapacidad;
        this.maeGrupoPoblacional = maeGrupoPoblacional;
        this.etnia = etnia;
        this.maeCausaNovedad = maeCausaNovedad;
        this.estadoCivil = estadoCivil;
        this.modeloLiquidacion = modeloLiquidacion;
    }

    public AsegAfiliadoDTO(Integer id, String idAfiliado, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String genero, int maeTipoDocumento, String numeroDocumento, int maeEstadoAfiliacion) {
        this.id = id;
        this.idAfiliado = idAfiliado;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.genero = genero;
        this.maeTipoDocumento = maeTipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.segundoApellido = segundoApellido;
        this.segundoNombre = segundoNombre;
        this.maeEstadoAfiliacion = maeEstadoAfiliacion;
    }
    
    public AsegAfiliadoDTO(Integer id, String idAfiliado, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido) {
        this.id = id;
        this.idAfiliado = idAfiliado;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.segundoNombre = segundoNombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdAfiliado() {
        return idAfiliado;
    }

    public void setIdAfiliado(String idAfiliado) {
        this.idAfiliado = idAfiliado;
    }

    public BigInteger getSerialBdua() {
        return serialBdua;
    }

    public void setSerialBdua(BigInteger serialBdua) {
        this.serialBdua = serialBdua;
    }

    public String getRegistroBduaStr() {
        if (registroBdua) {
            return "SI";
        } else {
            return "NO";
        }
    }
    
    public boolean isRegistroBdua() {
        return registroBdua;
    }

    public void setRegistroBdua(boolean registroBdua) {
        this.registroBdua = registroBdua;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getNombres() {
        return ((primerNombre == null || primerNombre.equals("")) ? "" : primerNombre) + ((segundoNombre == null || segundoNombre.equals("")) ? "" : " " + segundoNombre);
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getApellidos() {
        return ((primerApellido == null || primerApellido.equals("")) ? "" : primerApellido) + ((segundoApellido == null || segundoApellido.equals("")) ? "" : " " + segundoApellido);
    }

    public String getNombreCompleto() {
        String str = "";
        str += (primerNombre == null) ? "" : primerNombre;
        str += (segundoNombre == null) ? "" : " " + segundoNombre;
        str += (primerApellido == null) ? "" : " " + primerApellido;
        str += (segundoApellido == null) ? "" : " " + segundoApellido;
        return str;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getFechaExpedicionCedula() {
        return fechaExpedicionCedula;
    }

    public void setFechaExpedicionCedula(Date fechaExpedicionCedula) {
        this.fechaExpedicionCedula = fechaExpedicionCedula;
    }

    public int getMaeTipoDocumento() {
        return maeTipoDocumento;
    }

    public void setMaeTipoDocumento(int maeTipoDocumento) {
        this.maeTipoDocumento = maeTipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Integer getMaeTipoDocumentoCf() {
        return maeTipoDocumentoCf;
    }

    public void setMaeTipoDocumentoCf(Integer maeTipoDocumentoCf) {
        this.maeTipoDocumentoCf = maeTipoDocumentoCf;
    }

    public String getNumeroDocumentoCf() {
        return numeroDocumentoCf;
    }

    public void setNumeroDocumentoCf(String numeroDocumentoCf) {
        this.numeroDocumentoCf = numeroDocumentoCf;
    }

    public Date getFechaAfiliacionSgsss() {
        return fechaAfiliacionSgsss;
    }

    public void setFechaAfiliacionSgsss(Date fechaAfiliacionSgsss) {
        this.fechaAfiliacionSgsss = fechaAfiliacionSgsss;
    }

    public Date getFechaAfiliacionEps() {
        return fechaAfiliacionEps;
    }

    public void setFechaAfiliacionEps(Date fechaAfiliacionEps) {
        this.fechaAfiliacionEps = fechaAfiliacionEps;
    }

    public Date getFechaEgresoEps() {
        return fechaEgresoEps;
    }

    public void setFechaEgresoEps(Date fechaEgresoEps) {
        this.fechaEgresoEps = fechaEgresoEps;
    }

    public Date getFechaCambioEstadoEps() {
        return fechaCambioEstadoEps;
    }

    public void setFechaCambioEstadoEps(Date fechaCambioEstadoEps) {
        this.fechaCambioEstadoEps = fechaCambioEstadoEps;
    }

    public String getTipoBeneficiario() {
        return tipoBeneficiario;
    }

    public void setTipoBeneficiario(String tipoBeneficiario) {
        this.tipoBeneficiario = tipoBeneficiario;
    }

    public int getMaeEstadoAfiliacion() {
        return maeEstadoAfiliacion;
    }

    public void setMaeEstadoAfiliacion(int maeEstadoAfiliacion) {
        this.maeEstadoAfiliacion = maeEstadoAfiliacion;
    }

    public int getMaeOrigenAfiliado() {
        return maeOrigenAfiliado;
    }

    public void setMaeOrigenAfiliado(int maeOrigenAfiliado) {
        this.maeOrigenAfiliado = maeOrigenAfiliado;
    }

    public String getParentescoCotizante() {
        return parentescoCotizante;
    }

    public void setParentescoCotizante(String parentescoCotizante) {
        this.parentescoCotizante = parentescoCotizante;
    }

    public String getAutorizaEnvioSmsStr() {
        if (autorizaEnvioSms != null && autorizaEnvioSms) {
            return "SI";
        } else {
            return "NO";
        }
    }
    
    public Boolean getAutorizaEnvioSms() {
        return autorizaEnvioSms;
    }

    public void setAutorizaEnvioSms(Boolean autorizaEnvioSms) {
        this.autorizaEnvioSms = autorizaEnvioSms;
    }

    public String getAutorizaEnvioEmailStr() {
        if (autorizaEnvioEmail != null && autorizaEnvioEmail) {
            return "SI";
        } else {
            return "NO";
        }
    }
    
    public Boolean getAutorizaEnvioEmail() {
        return autorizaEnvioEmail;
    }

    public void setAutorizaEnvioEmail(Boolean autorizaEnvioEmail) {
        this.autorizaEnvioEmail = autorizaEnvioEmail;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public String getNivelSisben() {
        return nivelSisben;
    }

    public void setNivelSisben(String nivelSisben) {
        this.nivelSisben = nivelSisben;
    }

    public Double getPuntajeSisben() {
        return puntajeSisben;
    }

    public void setPuntajeSisben(Double puntajeSisben) {
        this.puntajeSisben = puntajeSisben;
    }

    public String getFichaSisben() {
        return fichaSisben;
    }

    public void setFichaSisben(String fichaSisben) {
        this.fichaSisben = fichaSisben;
    }

    public Date getFechaSisben() {
        return fechaSisben;
    }

    public void setFechaSisben(Date fechaSisben) {
        this.fechaSisben = fechaSisben;
    }

    public String getCategoriaIbc() {
        return categoriaIbc;
    }

    public void setCategoriaIbc(String categoriaIbc) {
        this.categoriaIbc = categoriaIbc;
    }

    public String getTienePortabilidadStr() {
        if (tienePortabilidad != null && tienePortabilidad) {
            return "SI";
        } else {
            return "NO";
        }
    }
    
    public Boolean getTienePortabilidad() {
        return tienePortabilidad;
    }

    public void setTienePortabilidad(Boolean tienePortabilidad) {
        this.tienePortabilidad = tienePortabilidad;
    }

    public Date getFechaPortabilidad() {
        return fechaPortabilidad;
    }

    public void setFechaPortabilidad(Date fechaPortabilidad) {
        this.fechaPortabilidad = fechaPortabilidad;
    }

    public String getTipoCotizante() {
        return tipoCotizante;
    }

    public void setTipoCotizante(String tipoCotizante) {
        this.tipoCotizante = tipoCotizante;
    }

    public String getDiscapacidadStr() {
        if (discapacidad) {
        return "SI";
        } else {
            return "NO";
        }
    }
    
    public boolean isDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(boolean discapacidad) {
        this.discapacidad = discapacidad;
    }

    public String getTipoDiscapacidad() {
        return tipoDiscapacidad;
    }

    public void setTipoDiscapacidad(String tipoDiscapacidad) {
        this.tipoDiscapacidad = tipoDiscapacidad;
    }

    public String getCondicionDiscapacidad() {
        return condicionDiscapacidad;
    }

    public void setCondicionDiscapacidad(String condicionDiscapacidad) {
        this.condicionDiscapacidad = condicionDiscapacidad;
    }

    public Date getFechaIniciodiscapacidad() {
        return fechaIniciodiscapacidad;
    }

    public void setFechaIniciodiscapacidad(Date fechaIniciodiscapacidad) {
        this.fechaIniciodiscapacidad = fechaIniciodiscapacidad;
    }

    public Date getFechaFinDiscapacidad() {
        return fechaFinDiscapacidad;
    }

    public void setFechaFinDiscapacidad(Date fechaFinDiscapacidad) {
        this.fechaFinDiscapacidad = fechaFinDiscapacidad;
    }

    public int getMaeGrupoPoblacional() {
        return maeGrupoPoblacional;
    }

    public void setMaeGrupoPoblacional(int maeGrupoPoblacional) {
        this.maeGrupoPoblacional = maeGrupoPoblacional;
    }

    public String getVictimaStr() {
        if (victima != null && victima) {
            return "SI";
        } else {
            return "NO";
        }
    }
    
    public Boolean getVictima() {
        return victima;
    }

    public void setVictima(Boolean victima) {
        this.victima = victima;
    }

    public String getEtnia() {
        return etnia;
    }

    public void setEtnia(String etnia) {
        this.etnia = etnia;
    }

    public int getMaeCausaNovedad() {
        return maeCausaNovedad;
    }

    public void setMaeCausaNovedad(int maeCausaNovedad) {
        this.maeCausaNovedad = maeCausaNovedad;
    }

    public Date getFechaReactivacion() {
        return fechaReactivacion;
    }

    public void setFechaReactivacion(Date fechaReactivacion) {
        this.fechaReactivacion = fechaReactivacion;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Date getFechaMovilidad() {
        return fechaMovilidad;
    }

    public void setFechaMovilidad(Date fechaMovilidad) {
        this.fechaMovilidad = fechaMovilidad;
    }

    public String getModeloLiquidacion() {
        return modeloLiquidacion;
    }

    public void setModeloLiquidacion(String modeloLiquidacion) {
        this.modeloLiquidacion = modeloLiquidacion;
    }

    public Integer getMaeTipoDocumentoAportante() {
        return maeTipoDocumentoAportante;
    }

    public void setMaeTipoDocumentoAportante(Integer maeTipoDocumentoAportante) {
        this.maeTipoDocumentoAportante = maeTipoDocumentoAportante;
    }

    public String getNumeroDocumentoAportante() {
        return numeroDocumentoAportante;
    }

    public void setNumeroDocumentoAportante(String numeroDocumentoAportante) {
        this.numeroDocumentoAportante = numeroDocumentoAportante;
    }

    public Integer getMaeActividadEconomica() {
        return maeActividadEconomica;
    }

    public void setMaeActividadEconomica(Integer maeActividadEconomica) {
        this.maeActividadEconomica = maeActividadEconomica;
    }

    public Integer getMaeArl() {
        return maeArl;
    }

    public void setMaeArl(Integer maeArl) {
        this.maeArl = maeArl;
    }

    public Integer getMaeAfp() {
        return maeAfp;
    }

    public void setMaeAfp(Integer maeAfp) {
        this.maeAfp = maeAfp;
    }

    public Integer getMaeCajaCompensacion() {
        return maeCajaCompensacion;
    }

    public void setMaeCajaCompensacion(Integer maeCajaCompensacion) {
        this.maeCajaCompensacion = maeCajaCompensacion;
    }

    public CntPrestadorSede getPrimariaPrestadorSede() {
        return primariaPrestadorSede;
    }

    public void setPrimariaPrestadorSede(CntPrestadorSede primariaPrestadorSede) {
        this.primariaPrestadorSede = primariaPrestadorSede;
    }

    public CntPrestadorSede getPortabilidadPrestadorSede() {
        return portabilidadPrestadorSede;
    }

    public void setPortabilidadPrestadorSede(CntPrestadorSede portabilidadPrestadorSede) {
        this.portabilidadPrestadorSede = portabilidadPrestadorSede;
    }

    public Ubicacion getNacimientoUbicacion() {
        return nacimientoUbicacion;
    }

    public void setNacimientoUbicacion(Ubicacion nacimientoUbicacion) {
        this.nacimientoUbicacion = nacimientoUbicacion;
    }

    public Ubicacion getAfiliacionUbicacion() {
        return afiliacionUbicacion;
    }

    public void setAfiliacionUbicacion(Ubicacion afiliacionUbicacion) {
        this.afiliacionUbicacion = afiliacionUbicacion;
    }

    public List<AsegPortabilidad> getListaAsegPortabilidades() {
        return listaAsegPortabilidades;
    }

    public void setListaAsegPortabilidades(List<AsegPortabilidad> listaAsegPortabilidades) {
        this.listaAsegPortabilidades = listaAsegPortabilidades;
    }

    public List<AsegReporteNovedad> getListaAsegReporteNovedades() {
        return listaAsegReporteNovedades;
    }

    public void setListaAsegReporteNovedades(List<AsegReporteNovedad> listaAsegReporteNovedades) {
        this.listaAsegReporteNovedades = listaAsegReporteNovedades;
    }

    public List<AsegRadicadoNovedad> getListaAsegRadicadoNovedades() {
        return listaAsegRadicadoNovedades;
    }

    public void setListaAsegRadicadoNovedades(List<AsegRadicadoNovedad> listaAsegRadicadoNovedades) {
        this.listaAsegRadicadoNovedades = listaAsegRadicadoNovedades;
    }

    public List<AsegTabulacionEncuesta> getListaAsegTabulacionEncuestas() {
        return listaAsegTabulacionEncuestas;
    }

    public void setListaAsegTabulacionEncuestas(List<AsegTabulacionEncuesta> listaAsegTabulacionEncuestas) {
        this.listaAsegTabulacionEncuestas = listaAsegTabulacionEncuestas;
    }

    public List<AsegTraslado> getListaAsegTraslados() {
        return listaAsegTraslados;
    }

    public void setListaAsegTraslados(List<AsegTraslado> listaAsegTraslados) {
        this.listaAsegTraslados = listaAsegTraslados;
    }

    public String getDireccionVia() {
        return direccionVia;
    }

    public void setDireccionVia(String direccionVia) {
        this.direccionVia = direccionVia;
    }

    public String getDireccionNro() {
        return direccionNro;
    }

    public void setDireccionNro(String direccionNro) {
        this.direccionNro = direccionNro;
    }

    public String getDireccionOrd1() {
        return direccionOrd1;
    }

    public void setDireccionOrd1(String direccionOrd1) {
        this.direccionOrd1 = direccionOrd1;
    }

    public String getDireccionOrientacion() {
        return direccionOrientacion;
    }

    public void setDireccionOrientacion(String direccionOrientacion) {
        this.direccionOrientacion = direccionOrientacion;
    }

    public String getDireccionPlaca1() {
        return direccionPlaca1;
    }

    public void setDireccionPlaca1(String direccionPlaca1) {
        this.direccionPlaca1 = direccionPlaca1;
    }

    public String getDireccionOrd2() {
        return direccionOrd2;
    }

    public void setDireccionOrd2(String direccionOrd2) {
        this.direccionOrd2 = direccionOrd2;
    }

    public String getDireccionPlaca2() {
        return direccionPlaca2;
    }

    public void setDireccionPlaca2(String direccionPlaca2) {
        this.direccionPlaca2 = direccionPlaca2;
    }

    public String getDireccionDescripcion() {
        return direccionDescripcion;
    }

    public void setDireccionDescripcion(String direccionDescripcion) {
        this.direccionDescripcion = direccionDescripcion;
    }

    public String getObservacionNovedad() {
        return observacionNovedad;
    }

    public void setObservacionNovedad(String observacionNovedad) {
        this.observacionNovedad = observacionNovedad;
    }

    public Date getFechaNovedad() {
        return fechaNovedad;
    }

    public void setFechaNovedad(Date fechaNovedad) {
        this.fechaNovedad = fechaNovedad;
    }

    /**
     * @return the direccionOrientacion2
     */
    public String getDireccionOrientacion2() {
        return direccionOrientacion2;
    }

    /**
     * @param direccionOrientacion2 the direccionOrientacion2 to set
     */
    public void setDireccionOrientacion2(String direccionOrientacion2) {
        this.direccionOrientacion2 = direccionOrientacion2;
    }

    /**
     * @return the residenciaUbicacion
     */
    public Ubicacion getResidenciaUbicacion() {
        return residenciaUbicacion;
    }

    /**
     * @param residenciaUbicacion the residenciaUbicacion to set
     */
    public void setResidenciaUbicacion(Ubicacion residenciaUbicacion) {
        this.residenciaUbicacion = residenciaUbicacion;
    }

    /**
     * @return the sincronizado
     */
    public Integer getSincronizado() {
        return sincronizado;
    }

    /**
     * @param sincronizado the sincronizado to set
     */
    public void setSincronizado(Integer sincronizado) {
        this.sincronizado = sincronizado;
    }

    /**
     * @return the asegTraslado
     */
    public AsegTraslado getAsegTraslado() {
        return asegTraslado;
    }

    /**
     * @param asegTraslado the asegTraslado to set
     */
    public void setAsegTraslado(AsegTraslado asegTraslado) {
        this.asegTraslado = asegTraslado;
    }

    /**
     * @return the errorCarga
     */
    public String getErrorCarga() {
        return errorCarga;
    }

    /**
     * @param errorCarga the errorCarga to set
     */
    public void setErrorCarga(String errorCarga) {
        this.errorCarga = errorCarga;
    }

    /**
     * @return the registroArchivo
     */
    public String getRegistroArchivo() {
        return registroArchivo;
    }

    /**
     * @param registroArchivo the registroArchivo to set
     */
    public void setRegistroArchivo(String registroArchivo) {
        this.registroArchivo = registroArchivo;
    }
    
    @Override
    public Object clone()throws CloneNotSupportedException{  
	return (AsegAfiliadoDTO)super.clone();  
   }

    /**
     * @return the nombresCF
     */
    public String getNombresCF() {
        return nombresCF;
    }

    /**
     * @param nombresCF the nombresCF to set
     */
    public void setNombresCF(String nombresCF) {
        this.nombresCF = nombresCF;
    }

    /**
     * @return the apellidosCF
     */
    public String getApellidosCF() {
        return apellidosCF;
    }

    /**
     * @param apellidosCF the apellidosCF to set
     */
    public void setApellidosCF(String apellidosCF) {
        this.apellidosCF = apellidosCF;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }    

    @Override
    public String toString() {
        //return "AsegAfiliado{" + "id=" + id + ", idAfiliado=" + idAfiliado + ", serialBdua=" + serialBdua + ", registroBdua=" + registroBdua + ", primerNombre=" + primerNombre + ", segundoNombre=" + segundoNombre + ", primerApellido=" + primerApellido + ", segundoApellido=" + segundoApellido + ", fechaNacimiento=" + fechaNacimiento + ", genero=" + genero + ", fechaExpedicionCedula=" + fechaExpedicionCedula + ", maeTipoDocumento=" + maeTipoDocumento + ", numeroDocumento=" + numeroDocumento + ", maeTipoDocumentoCf=" + maeTipoDocumentoCf + ", numeroDocumentoCf=" + numeroDocumentoCf + ", fechaAfiliacionSgsss=" + fechaAfiliacionSgsss + ", fechaAfiliacionEps=" + fechaAfiliacionEps + ", fechaEgresoEps=" + fechaEgresoEps + ", fechaCambioEstadoEps=" + fechaCambioEstadoEps + ", tipoBeneficiario=" + tipoBeneficiario + ", maeEstadoAfiliacion=" + maeEstadoAfiliacion + ", maeOrigenAfiliado=" + maeOrigenAfiliado + ", parentescoCotizante=" + parentescoCotizante + ", autorizaEnvioSms=" + autorizaEnvioSms + ", autorizaEnvioEmail=" + autorizaEnvioEmail + ", telefonoFijo=" + telefonoFijo + ", telefonoMovil=" + telefonoMovil + ", email=" + email + ", zona=" + zona + ", direccionResidencia=" + direccionResidencia + ", barrio=" + barrio + ", regimen=" + regimen + ", nivelSisben=" + nivelSisben + ", puntajeSisben=" + puntajeSisben + ", fichaSisben=" + fichaSisben + ", fechaSisben=" + fechaSisben + ", categoriaIbc=" + categoriaIbc + ", tienePortabilidad=" + tienePortabilidad + ", fechaPortabilidad=" + fechaPortabilidad + ", tipoCotizante=" + tipoCotizante + ", discapacidad=" + discapacidad + ", tipoDiscapacidad=" + tipoDiscapacidad + ", condicionDiscapacidad=" + condicionDiscapacidad + ", fechaIniciodiscapacidad=" + fechaIniciodiscapacidad + ", fechaFinDiscapacidad=" + fechaFinDiscapacidad + ", maeGrupoPoblacional=" + maeGrupoPoblacional + ", victima=" + victima + ", etnia=" + etnia + ", maeCausaNovedad=" + maeCausaNovedad + ", fechaReactivacion=" + fechaReactivacion + ", estadoCivil=" + estadoCivil + ", fechaMovilidad=" + fechaMovilidad + ", modeloLiquidacion=" + modeloLiquidacion + ", maeTipoDocumentoAportante=" + maeTipoDocumentoAportante + ", numeroDocumentoAportante=" + numeroDocumentoAportante + ", maeActividadEconomica=" + maeActividadEconomica + ", maeArl=" + maeArl + ", maeAfp=" + maeAfp + ", maeCajaCompensacion=" + maeCajaCompensacion + ", sincronizado=" + sincronizado + ", primariaPrestadorSede=" + primariaPrestadorSede + ", portabilidadPrestadorSede=" + portabilidadPrestadorSede + ", nacimientoUbicacion=" + nacimientoUbicacion + ", afiliacionUbicacion=" + afiliacionUbicacion + ", residenciaUbicacion=" + residenciaUbicacion + ", observacionNovedad=" + observacionNovedad + ", fechaNovedad=" + fechaNovedad + '}';
        //Gson gson = new Gson();
        //Declaracion lectura JSON
        GsonBuilder gsonBuilderRespuesta = new GsonBuilder();
        gsonBuilderRespuesta.setDateFormat("yyyy-MM-dd HH:mm:ss"); //Formato fecha 
        gsonBuilderRespuesta.serializeNulls();
        Gson gson = gsonBuilderRespuesta.create();
        return gson.toJson(this);
    }

    public String toStringAlterno() {
        return "AsegAfiliado{" + "id=" + id + ", idAfiliado=" + idAfiliado + ", serialBdua=" + serialBdua + ", registroBdua=" + registroBdua + ", primerNombre=" + primerNombre + ", segundoNombre=" + segundoNombre + ", primerApellido=" + primerApellido + ", segundoApellido=" + segundoApellido + ", fechaNacimiento=" + fechaNacimiento + ", genero=" + genero + ", fechaExpedicionCedula=" + fechaExpedicionCedula + ", maeTipoDocumento=" + maeTipoDocumento + ", numeroDocumento=" + numeroDocumento + ", maeTipoDocumentoCf=" + maeTipoDocumentoCf + ", numeroDocumentoCf=" + numeroDocumentoCf + ", fechaAfiliacionSgsss=" + fechaAfiliacionSgsss + ", fechaAfiliacionEps=" + fechaAfiliacionEps + ", fechaEgresoEps=" + fechaEgresoEps + ", fechaCambioEstadoEps=" + fechaCambioEstadoEps + ", tipoBeneficiario=" + tipoBeneficiario + ", maeEstadoAfiliacion=" + maeEstadoAfiliacion + ", maeOrigenAfiliado=" + maeOrigenAfiliado + ", parentescoCotizante=" + parentescoCotizante + ", autorizaEnvioSms=" + autorizaEnvioSms + ", autorizaEnvioEmail=" + autorizaEnvioEmail + ", telefonoFijo=" + telefonoFijo + ", telefonoMovil=" + telefonoMovil + ", email=" + email + ", zona=" + zona + ", direccionResidencia=" + direccionResidencia + ", barrio=" + barrio + ", regimen=" + regimen + ", nivelSisben=" + nivelSisben + ", puntajeSisben=" + puntajeSisben + ", fichaSisben=" + fichaSisben + ", fechaSisben=" + fechaSisben + ", categoriaIbc=" + categoriaIbc + ", tienePortabilidad=" + tienePortabilidad + ", fechaPortabilidad=" + fechaPortabilidad + ", tipoCotizante=" + tipoCotizante + ", discapacidad=" + discapacidad + ", tipoDiscapacidad=" + tipoDiscapacidad + ", condicionDiscapacidad=" + condicionDiscapacidad + ", fechaIniciodiscapacidad=" + fechaIniciodiscapacidad + ", fechaFinDiscapacidad=" + fechaFinDiscapacidad + ", maeGrupoPoblacional=" + maeGrupoPoblacional + ", victima=" + victima + ", etnia=" + etnia + ", maeCausaNovedad=" + maeCausaNovedad + ", fechaReactivacion=" + fechaReactivacion + ", estadoCivil=" + estadoCivil + ", fechaMovilidad=" + fechaMovilidad + ", modeloLiquidacion=" + modeloLiquidacion + ", maeTipoDocumentoAportante=" + maeTipoDocumentoAportante + ", numeroDocumentoAportante=" + numeroDocumentoAportante + ", maeActividadEconomica=" + maeActividadEconomica + ", maeArl=" + maeArl + ", maeAfp=" + maeAfp + ", maeCajaCompensacion=" + maeCajaCompensacion + ", sincronizado=" + sincronizado + ", maeGrupoSisbenId=" + maeGrupoSisbenId + ", codigoFonetico=" + codigoFonetico + ", maeRegimenId=" + maeRegimenId + ", maeRegimenCodigo=" + maeRegimenCodigo + ", maeRegimenValor=" + maeRegimenValor + ", maeNivelSisbenId=" + maeNivelSisbenId + ", maeNivelSisbenCodigo=" + maeNivelSisbenCodigo + ", maeNivelSisbenValor=" + maeNivelSisbenValor + ", primariaPrestadorSede=" + primariaPrestadorSede + ", portabilidadPrestadorSede=" + portabilidadPrestadorSede + ", nacimientoUbicacion=" + nacimientoUbicacion + ", afiliacionUbicacion=" + afiliacionUbicacion + ", residenciaUbicacion=" + residenciaUbicacion + ", listaAsegAfiliadoContacto=" + listaAsegAfiliadoContacto + '}';
    }

    /**
     * @return the listaAsegAfiliadoHistorico
     */
    public List<AsegAfiliadoHistorico> getListaAsegAfiliadoHistorico() {
        return listaAsegAfiliadoHistorico;
    }

    /**
     * @param listaAsegAfiliadoHistorico the listaAsegAfiliadoHistorico to set
     */
    public void setListaAsegAfiliadoHistorico(List<AsegAfiliadoHistorico> listaAsegAfiliadoHistorico) {
        this.listaAsegAfiliadoHistorico = listaAsegAfiliadoHistorico;
    }

    /**
     * @return the fechaUltimaNovedad
     */
    public Date getFechaUltimaNovedad() {
        return fechaUltimaNovedad;
    }

    /**
     * @param fechaUltimaNovedad the fechaUltimaNovedad to set
     */
    public void setFechaUltimaNovedad(Date fechaUltimaNovedad) {
        this.fechaUltimaNovedad = fechaUltimaNovedad;
    }

    /**
     * @return the maeGrupoSisbenId
     */
    public Integer getMaeGrupoSisbenId() {
        return maeGrupoSisbenId;
    }

    /**
     * @param maeGrupoSisbenId the maeGrupoSisbenId to set
     */
    public void setMaeGrupoSisbenId(Integer maeGrupoSisbenId) {
        this.maeGrupoSisbenId = maeGrupoSisbenId;
    }

    /**
     * @return the codigoFonetico
     */
    public String getCodigoFonetico() {
        return codigoFonetico;
    }

    /**
     * @param codigoFonetico the codigoFonetico to set
     */
    public void setCodigoFonetico(String codigoFonetico) {
        this.codigoFonetico = codigoFonetico;
    }

    /**
     * @return the maeRegimenId
     */
    public Integer getMaeRegimenId() {
        return maeRegimenId;
    }

    /**
     * @param maeRegimenId the maeRegimenId to set
     */
    public void setMaeRegimenId(Integer maeRegimenId) {
        this.maeRegimenId = maeRegimenId;
    }

    /**
     * @return the maeRegimenCodigo
     */
    public String getMaeRegimenCodigo() {
        return maeRegimenCodigo;
    }

    /**
     * @param maeRegimenCodigo the maeRegimenCodigo to set
     */
    public void setMaeRegimenCodigo(String maeRegimenCodigo) {
        this.maeRegimenCodigo = maeRegimenCodigo;
    }

    /**
     * @return the maeRegimenValor
     */
    public String getMaeRegimenValor() {
        return maeRegimenValor;
    }

    /**
     * @param maeRegimenValor the maeRegimenValor to set
     */
    public void setMaeRegimenValor(String maeRegimenValor) {
        this.maeRegimenValor = maeRegimenValor;
    }

    /**
     * @return the maeNivelSisbenId
     */
    public Integer getMaeNivelSisbenId() {
        return maeNivelSisbenId;
    }

    /**
     * @param maeNivelSisbenId the maeNivelSisbenId to set
     */
    public void setMaeNivelSisbenId(Integer maeNivelSisbenId) {
        this.maeNivelSisbenId = maeNivelSisbenId;
    }

    /**
     * @return the maeNivelSisbenCodigo
     */
    public String getMaeNivelSisbenCodigo() {
        return maeNivelSisbenCodigo;
    }

    /**
     * @param maeNivelSisbenCodigo the maeNivelSisbenCodigo to set
     */
    public void setMaeNivelSisbenCodigo(String maeNivelSisbenCodigo) {
        this.maeNivelSisbenCodigo = maeNivelSisbenCodigo;
    }

    /**
     * @return the maeNivelSisbenValor
     */
    public String getMaeNivelSisbenValor() {
        return maeNivelSisbenValor;
    }

    /**
     * @param maeNivelSisbenValor the maeNivelSisbenValor to set
     */
    public void setMaeNivelSisbenValor(String maeNivelSisbenValor) {
        this.maeNivelSisbenValor = maeNivelSisbenValor;
    }

    /**
     * @return the listaAsegAfiliadoContacto
     */
    public List<AsegAfiliadoContacto> getListaAsegAfiliadoContacto() {
        return listaAsegAfiliadoContacto;
    }

    /**
     * @param listaAsegAfiliadoContacto the listaAsegAfiliadoContacto to set
     */
    public void setListaAsegAfiliadoContacto(List<AsegAfiliadoContacto> listaAsegAfiliadoContacto) {
        this.listaAsegAfiliadoContacto = listaAsegAfiliadoContacto;
    }

    /**
     * @return the maeGeneroId
     */
    public Integer getMaeGeneroId() {
        return maeGeneroId;
    }

    /**
     * @param maeGeneroId the maeGeneroId to set
     */
    public void setMaeGeneroId(Integer maeGeneroId) {
        this.maeGeneroId = maeGeneroId;
    }

    /**
     * @return the maeGeneroCodigo
     */
    public String getMaeGeneroCodigo() {
        return maeGeneroCodigo;
    }

    /**
     * @param maeGeneroCodigo the maeGeneroCodigo to set
     */
    public void setMaeGeneroCodigo(String maeGeneroCodigo) {
        this.maeGeneroCodigo = maeGeneroCodigo;
    }

    /**
     * @return the maeGeneroValor
     */
    public String getMaeGeneroValor() {
        return maeGeneroValor;
    }

    /**
     * @param maeGeneroValor the maeGeneroValor to set
     */
    public void setMaeGeneroValor(String maeGeneroValor) {
        this.maeGeneroValor = maeGeneroValor;
    }

    /**
     * @return the maeTipoDocumentoCodigo
     */
    public String getMaeTipoDocumentoCodigo() {
        return maeTipoDocumentoCodigo;
    }

    /**
     * @param maeTipoDocumentoCodigo the maeTipoDocumentoCodigo to set
     */
    public void setMaeTipoDocumentoCodigo(String maeTipoDocumentoCodigo) {
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
    }

    /**
     * @return the maeTipoDocumentoValor
     */
    public String getMaeTipoDocumentoValor() {
        return maeTipoDocumentoValor;
    }

    /**
     * @param maeTipoDocumentoValor the maeTipoDocumentoValor to set
     */
    public void setMaeTipoDocumentoValor(String maeTipoDocumentoValor) {
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
    }

    /**
     * @return the maeTipoDocumentoCfCodigo
     */
    public String getMaeTipoDocumentoCfCodigo() {
        return maeTipoDocumentoCfCodigo;
    }

    /**
     * @param maeTipoDocumentoCfCodigo the maeTipoDocumentoCfCodigo to set
     */
    public void setMaeTipoDocumentoCfCodigo(String maeTipoDocumentoCfCodigo) {
        this.maeTipoDocumentoCfCodigo = maeTipoDocumentoCfCodigo;
    }

    /**
     * @return the maeTipoDocumentoCfValor
     */
    public String getMaeTipoDocumentoCfValor() {
        return maeTipoDocumentoCfValor;
    }

    /**
     * @param maeTipoDocumentoCfValor the maeTipoDocumentoCfValor to set
     */
    public void setMaeTipoDocumentoCfValor(String maeTipoDocumentoCfValor) {
        this.maeTipoDocumentoCfValor = maeTipoDocumentoCfValor;
    }

    /**
     * @return the maeTipoAfiliadoId
     */
    public Integer getMaeTipoAfiliadoId() {
        return maeTipoAfiliadoId;
    }

    /**
     * @param maeTipoAfiliadoId the maeTipoAfiliadoId to set
     */
    public void setMaeTipoAfiliadoId(Integer maeTipoAfiliadoId) {
        this.maeTipoAfiliadoId = maeTipoAfiliadoId;
    }

    /**
     * @return the maeTipoAfiliadoCodigo
     */
    public String getMaeTipoAfiliadoCodigo() {
        return maeTipoAfiliadoCodigo;
    }

    /**
     * @param maeTipoAfiliadoCodigo the maeTipoAfiliadoCodigo to set
     */
    public void setMaeTipoAfiliadoCodigo(String maeTipoAfiliadoCodigo) {
        this.maeTipoAfiliadoCodigo = maeTipoAfiliadoCodigo;
    }

    /**
     * @return the maeTipoAfiliadoValor
     */
    public String getMaeTipoAfiliadoValor() {
        return maeTipoAfiliadoValor;
    }

    /**
     * @param maeTipoAfiliadoValor the maeTipoAfiliadoValor to set
     */
    public void setMaeTipoAfiliadoValor(String maeTipoAfiliadoValor) {
        this.maeTipoAfiliadoValor = maeTipoAfiliadoValor;
    }

    /**
     * @return the maeEstadoAfiliacionCodigo
     */
    public String getMaeEstadoAfiliacionCodigo() {
        return maeEstadoAfiliacionCodigo;
    }

    /**
     * @param maeEstadoAfiliacionCodigo the maeEstadoAfiliacionCodigo to set
     */
    public void setMaeEstadoAfiliacionCodigo(String maeEstadoAfiliacionCodigo) {
        this.maeEstadoAfiliacionCodigo = maeEstadoAfiliacionCodigo;
    }

    /**
     * @return the maeEstadoAfiliacionValor
     */
    public String getMaeEstadoAfiliacionValor() {
        return maeEstadoAfiliacionValor;
    }

    /**
     * @param maeEstadoAfiliacionValor the maeEstadoAfiliacionValor to set
     */
    public void setMaeEstadoAfiliacionValor(String maeEstadoAfiliacionValor) {
        this.maeEstadoAfiliacionValor = maeEstadoAfiliacionValor;
    }

    /**
     * @return the maeOrigenAfiliadoCodigo
     */
    public String getMaeOrigenAfiliadoCodigo() {
        return maeOrigenAfiliadoCodigo;
    }

    /**
     * @param maeOrigenAfiliadoCodigo the maeOrigenAfiliadoCodigo to set
     */
    public void setMaeOrigenAfiliadoCodigo(String maeOrigenAfiliadoCodigo) {
        this.maeOrigenAfiliadoCodigo = maeOrigenAfiliadoCodigo;
    }

    /**
     * @return the maeOrigenAfiliadoValor
     */
    public String getMaeOrigenAfiliadoValor() {
        return maeOrigenAfiliadoValor;
    }

    /**
     * @param maeOrigenAfiliadoValor the maeOrigenAfiliadoValor to set
     */
    public void setMaeOrigenAfiliadoValor(String maeOrigenAfiliadoValor) {
        this.maeOrigenAfiliadoValor = maeOrigenAfiliadoValor;
    }

    /**
     * @return the maeParentescoCotizanteId
     */
    public Integer getMaeParentescoCotizanteId() {
        return maeParentescoCotizanteId;
    }

    /**
     * @param maeParentescoCotizanteId the maeParentescoCotizanteId to set
     */
    public void setMaeParentescoCotizanteId(Integer maeParentescoCotizanteId) {
        this.maeParentescoCotizanteId = maeParentescoCotizanteId;
    }

    /**
     * @return the maeParentescoCotizanteCodigo
     */
    public String getMaeParentescoCotizanteCodigo() {
        return maeParentescoCotizanteCodigo;
    }

    /**
     * @param maeParentescoCotizanteCodigo the maeParentescoCotizanteCodigo to set
     */
    public void setMaeParentescoCotizanteCodigo(String maeParentescoCotizanteCodigo) {
        this.maeParentescoCotizanteCodigo = maeParentescoCotizanteCodigo;
    }

    /**
     * @return the maeParentescoCotizanteValor
     */
    public String getMaeParentescoCotizanteValor() {
        return maeParentescoCotizanteValor;
    }

    /**
     * @param maeParentescoCotizanteValor the maeParentescoCotizanteValor to set
     */
    public void setMaeParentescoCotizanteValor(String maeParentescoCotizanteValor) {
        this.maeParentescoCotizanteValor = maeParentescoCotizanteValor;
    }

    /**
     * @return the maeZonaId
     */
    public Integer getMaeZonaId() {
        return maeZonaId;
    }

    /**
     * @param maeZonaId the maeZonaId to set
     */
    public void setMaeZonaId(Integer maeZonaId) {
        this.maeZonaId = maeZonaId;
    }

    /**
     * @return the maeZonaCodigo
     */
    public String getMaeZonaCodigo() {
        return maeZonaCodigo;
    }

    /**
     * @param maeZonaCodigo the maeZonaCodigo to set
     */
    public void setMaeZonaCodigo(String maeZonaCodigo) {
        this.maeZonaCodigo = maeZonaCodigo;
    }

    /**
     * @return the maeZonaValor
     */
    public String getMaeZonaValor() {
        return maeZonaValor;
    }

    /**
     * @param maeZonaValor the maeZonaValor to set
     */
    public void setMaeZonaValor(String maeZonaValor) {
        this.maeZonaValor = maeZonaValor;
    }

    /**
     * @return the maeCategoriaIbcId
     */
    public Integer getMaeCategoriaIbcId() {
        return maeCategoriaIbcId;
    }

    /**
     * @param maeCategoriaIbcId the maeCategoriaIbcId to set
     */
    public void setMaeCategoriaIbcId(Integer maeCategoriaIbcId) {
        this.maeCategoriaIbcId = maeCategoriaIbcId;
    }

    /**
     * @return the maeCategoriaIbcCodigo
     */
    public String getMaeCategoriaIbcCodigo() {
        return maeCategoriaIbcCodigo;
    }

    /**
     * @param maeCategoriaIbcCodigo the maeCategoriaIbcCodigo to set
     */
    public void setMaeCategoriaIbcCodigo(String maeCategoriaIbcCodigo) {
        this.maeCategoriaIbcCodigo = maeCategoriaIbcCodigo;
    }

    /**
     * @return the maeCategoriaIbcValor
     */
    public String getMaeCategoriaIbcValor() {
        return maeCategoriaIbcValor;
    }

    /**
     * @param maeCategoriaIbcValor the maeCategoriaIbcValor to set
     */
    public void setMaeCategoriaIbcValor(String maeCategoriaIbcValor) {
        this.maeCategoriaIbcValor = maeCategoriaIbcValor;
    }

    /**
     * @return the maeTipoCotizanteId
     */
    public Integer getMaeTipoCotizanteId() {
        return maeTipoCotizanteId;
    }

    /**
     * @param maeTipoCotizanteId the maeTipoCotizanteId to set
     */
    public void setMaeTipoCotizanteId(Integer maeTipoCotizanteId) {
        this.maeTipoCotizanteId = maeTipoCotizanteId;
    }

    /**
     * @return the maeTipoCotizanteCodigo
     */
    public String getMaeTipoCotizanteCodigo() {
        return maeTipoCotizanteCodigo;
    }

    /**
     * @param maeTipoCotizanteCodigo the maeTipoCotizanteCodigo to set
     */
    public void setMaeTipoCotizanteCodigo(String maeTipoCotizanteCodigo) {
        this.maeTipoCotizanteCodigo = maeTipoCotizanteCodigo;
    }

    /**
     * @return the maeTipoCotizanteValor
     */
    public String getMaeTipoCotizanteValor() {
        return maeTipoCotizanteValor;
    }

    /**
     * @param maeTipoCotizanteValor the maeTipoCotizanteValor to set
     */
    public void setMaeTipoCotizanteValor(String maeTipoCotizanteValor) {
        this.maeTipoCotizanteValor = maeTipoCotizanteValor;
    }

    /**
     * @return the maeTipoDiscapacidadId
     */
    public Integer getMaeTipoDiscapacidadId() {
        return maeTipoDiscapacidadId;
    }

    /**
     * @param maeTipoDiscapacidadId the maeTipoDiscapacidadId to set
     */
    public void setMaeTipoDiscapacidadId(Integer maeTipoDiscapacidadId) {
        this.maeTipoDiscapacidadId = maeTipoDiscapacidadId;
    }

    /**
     * @return the maeTipoDiscapacidadCodigo
     */
    public String getMaeTipoDiscapacidadCodigo() {
        return maeTipoDiscapacidadCodigo;
    }

    /**
     * @param maeTipoDiscapacidadCodigo the maeTipoDiscapacidadCodigo to set
     */
    public void setMaeTipoDiscapacidadCodigo(String maeTipoDiscapacidadCodigo) {
        this.maeTipoDiscapacidadCodigo = maeTipoDiscapacidadCodigo;
    }

    /**
     * @return the maeTipoDiscapacidadValor
     */
    public String getMaeTipoDiscapacidadValor() {
        return maeTipoDiscapacidadValor;
    }

    /**
     * @param maeTipoDiscapacidadValor the maeTipoDiscapacidadValor to set
     */
    public void setMaeTipoDiscapacidadValor(String maeTipoDiscapacidadValor) {
        this.maeTipoDiscapacidadValor = maeTipoDiscapacidadValor;
    }

    /**
     * @return the maeGrupoPoblacionalCodigo
     */
    public String getMaeGrupoPoblacionalCodigo() {
        return maeGrupoPoblacionalCodigo;
    }

    /**
     * @param maeGrupoPoblacionalCodigo the maeGrupoPoblacionalCodigo to set
     */
    public void setMaeGrupoPoblacionalCodigo(String maeGrupoPoblacionalCodigo) {
        this.maeGrupoPoblacionalCodigo = maeGrupoPoblacionalCodigo;
    }

    /**
     * @return the maeGrupoPoblacionalValor
     */
    public String getMaeGrupoPoblacionalValor() {
        return maeGrupoPoblacionalValor;
    }

    /**
     * @param maeGrupoPoblacionalValor the maeGrupoPoblacionalValor to set
     */
    public void setMaeGrupoPoblacionalValor(String maeGrupoPoblacionalValor) {
        this.maeGrupoPoblacionalValor = maeGrupoPoblacionalValor;
    }

    /**
     * @return the maeEtniaId
     */
    public Integer getMaeEtniaId() {
        return maeEtniaId;
    }

    /**
     * @param maeEtniaId the maeEtniaId to set
     */
    public void setMaeEtniaId(Integer maeEtniaId) {
        this.maeEtniaId = maeEtniaId;
    }

    /**
     * @return the maeEtniaCodigo
     */
    public String getMaeEtniaCodigo() {
        return maeEtniaCodigo;
    }

    /**
     * @param maeEtniaCodigo the maeEtniaCodigo to set
     */
    public void setMaeEtniaCodigo(String maeEtniaCodigo) {
        this.maeEtniaCodigo = maeEtniaCodigo;
    }

    /**
     * @return the maeEtniaValor
     */
    public String getMaeEtniaValor() {
        return maeEtniaValor;
    }

    /**
     * @param maeEtniaValor the maeEtniaValor to set
     */
    public void setMaeEtniaValor(String maeEtniaValor) {
        this.maeEtniaValor = maeEtniaValor;
    }

    /**
     * @return the maeCausaNovedadCodigo
     */
    public String getMaeCausaNovedadCodigo() {
        return maeCausaNovedadCodigo;
    }

    /**
     * @param maeCausaNovedadCodigo the maeCausaNovedadCodigo to set
     */
    public void setMaeCausaNovedadCodigo(String maeCausaNovedadCodigo) {
        this.maeCausaNovedadCodigo = maeCausaNovedadCodigo;
    }

    /**
     * @return the maeCausaNovedadValor
     */
    public String getMaeCausaNovedadValor() {
        return maeCausaNovedadValor;
    }

    /**
     * @param maeCausaNovedadValor the maeCausaNovedadValor to set
     */
    public void setMaeCausaNovedadValor(String maeCausaNovedadValor) {
        this.maeCausaNovedadValor = maeCausaNovedadValor;
    }

    /**
     * @return the maeEstadoCivilId
     */
    public Integer getMaeEstadoCivilId() {
        return maeEstadoCivilId;
    }

    /**
     * @param maeEstadoCivilId the maeEstadoCivilId to set
     */
    public void setMaeEstadoCivilId(Integer maeEstadoCivilId) {
        this.maeEstadoCivilId = maeEstadoCivilId;
    }

    /**
     * @return the maeEstadoCivilCodigo
     */
    public String getMaeEstadoCivilCodigo() {
        return maeEstadoCivilCodigo;
    }

    /**
     * @param maeEstadoCivilCodigo the maeEstadoCivilCodigo to set
     */
    public void setMaeEstadoCivilCodigo(String maeEstadoCivilCodigo) {
        this.maeEstadoCivilCodigo = maeEstadoCivilCodigo;
    }

    /**
     * @return the maeEstadoCivilValor
     */
    public String getMaeEstadoCivilValor() {
        return maeEstadoCivilValor;
    }

    /**
     * @param maeEstadoCivilValor the maeEstadoCivilValor to set
     */
    public void setMaeEstadoCivilValor(String maeEstadoCivilValor) {
        this.maeEstadoCivilValor = maeEstadoCivilValor;
    }

    /**
     * @return the maeModeloLiquidacionId
     */
    public Integer getMaeModeloLiquidacionId() {
        return maeModeloLiquidacionId;
    }

    /**
     * @param maeModeloLiquidacionId the maeModeloLiquidacionId to set
     */
    public void setMaeModeloLiquidacionId(Integer maeModeloLiquidacionId) {
        this.maeModeloLiquidacionId = maeModeloLiquidacionId;
    }

    /**
     * @return the maeModeloLiquidacionCodigo
     */
    public String getMaeModeloLiquidacionCodigo() {
        return maeModeloLiquidacionCodigo;
    }

    /**
     * @param maeModeloLiquidacionCodigo the maeModeloLiquidacionCodigo to set
     */
    public void setMaeModeloLiquidacionCodigo(String maeModeloLiquidacionCodigo) {
        this.maeModeloLiquidacionCodigo = maeModeloLiquidacionCodigo;
    }

    /**
     * @return the maeModeloLiquidacionValor
     */
    public String getMaeModeloLiquidacionValor() {
        return maeModeloLiquidacionValor;
    }

    /**
     * @param maeModeloLiquidacionValor the maeModeloLiquidacionValor to set
     */
    public void setMaeModeloLiquidacionValor(String maeModeloLiquidacionValor) {
        this.maeModeloLiquidacionValor = maeModeloLiquidacionValor;
    }

    /**
     * @return the maeTipoDocumentoAportanteCodigo
     */
    public String getMaeTipoDocumentoAportanteCodigo() {
        return maeTipoDocumentoAportanteCodigo;
    }

    /**
     * @param maeTipoDocumentoAportanteCodigo the maeTipoDocumentoAportanteCodigo to set
     */
    public void setMaeTipoDocumentoAportanteCodigo(String maeTipoDocumentoAportanteCodigo) {
        this.maeTipoDocumentoAportanteCodigo = maeTipoDocumentoAportanteCodigo;
    }

    /**
     * @return the maeTipoDocumentoAportanteValor
     */
    public String getMaeTipoDocumentoAportanteValor() {
        return maeTipoDocumentoAportanteValor;
    }

    /**
     * @param maeTipoDocumentoAportanteValor the maeTipoDocumentoAportanteValor to set
     */
    public void setMaeTipoDocumentoAportanteValor(String maeTipoDocumentoAportanteValor) {
        this.maeTipoDocumentoAportanteValor = maeTipoDocumentoAportanteValor;
    }

    /**
     * @return the maeGrupoSisbenCodigo
     */
    public String getMaeGrupoSisbenCodigo() {
        return maeGrupoSisbenCodigo;
    }

    /**
     * @param maeGrupoSisbenCodigo the maeGrupoSisbenCodigo to set
     */
    public void setMaeGrupoSisbenCodigo(String maeGrupoSisbenCodigo) {
        this.maeGrupoSisbenCodigo = maeGrupoSisbenCodigo;
    }

    /**
     * @return the maeGrupoSisbenValor
     */
    public String getMaeGrupoSisbenValor() {
        return maeGrupoSisbenValor;
    }

    /**
     * @param maeGrupoSisbenValor the maeGrupoSisbenValor to set
     */
    public void setMaeGrupoSisbenValor(String maeGrupoSisbenValor) {
        this.maeGrupoSisbenValor = maeGrupoSisbenValor;
    }

    /**
     * @return the origenUltimoRegistro
     */
    public Integer getOrigenUltimoRegistro() {
        return origenUltimoRegistro;
    }

    /**
     * @param origenUltimoRegistro the origenUltimoRegistro to set
     */
    public void setOrigenUltimoRegistro(Integer origenUltimoRegistro) {
        this.origenUltimoRegistro = origenUltimoRegistro;
    }

    /**
     * @return the maeCondicionDiscapacidadCodigo
     */
    public String getMaeCondicionDiscapacidadCodigo() {
        return maeCondicionDiscapacidadCodigo;
    }

    /**
     * @param maeCondicionDiscapacidadCodigo the maeCondicionDiscapacidadCodigo to set
     */
    public void setMaeCondicionDiscapacidadCodigo(String maeCondicionDiscapacidadCodigo) {
        this.maeCondicionDiscapacidadCodigo = maeCondicionDiscapacidadCodigo;
    }

    /**
     * @return the maeCondicionDiscapacidadValor
     */
    public String getMaeCondicionDiscapacidadValor() {
        return maeCondicionDiscapacidadValor;
    }

    /**
     * @param maeCondicionDiscapacidadValor the maeCondicionDiscapacidadValor to set
     */
    public void setMaeCondicionDiscapacidadValor(String maeCondicionDiscapacidadValor) {
        this.maeCondicionDiscapacidadValor = maeCondicionDiscapacidadValor;
    }

    /**
     * @return the maeCondicionDiscapacidadId
     */
    public Integer getMaeCondicionDiscapacidadId() {
        return maeCondicionDiscapacidadId;
    }

    /**
     * @param maeCondicionDiscapacidadId the maeCondicionDiscapacidadId to set
     */
    public void setMaeCondicionDiscapacidadId(Integer maeCondicionDiscapacidadId) {
        this.maeCondicionDiscapacidadId = maeCondicionDiscapacidadId;
    }
    
    /**
     * Función para obtener el país de Nacimiento a partir de la ubicación de nacimiento.
     * @return 
     */
    public String getPaisNacimientoStr() {
        String mensaje = "";
        if (nacimientoUbicacion != null) {
            mensaje = nacimientoUbicacion.getNombre();
        }
        return mensaje;
    }
    
    /**
     * Función para obtener el país de Nacionalidad a partir de la ubicación de Nacionalidad.
     * @return 
     */
    public String getPaisNacionalidadStr() {
        String mensaje = "";
        if (nacionalidadUbicacion != null) {
            mensaje = nacionalidadUbicacion.getNombre();
        }
        return mensaje;
    }
    
    public Integer getPaisNacimiento() {
        return paisNacimiento;
    }
    
    public void setPaisNacimiento(Integer paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }

    /**
     * @return the maeActividadEconomicaCodigo
     */
    public String getMaeActividadEconomicaCodigo() {
        return maeActividadEconomicaCodigo;
    }

    /**
     * @param maeActividadEconomicaCodigo the maeActividadEconomicaCodigo to set
     */
    public void setMaeActividadEconomicaCodigo(String maeActividadEconomicaCodigo) {
        this.maeActividadEconomicaCodigo = maeActividadEconomicaCodigo;
    }

    /**
     * @return the maeActividadEconomicaValor
     */
    public String getMaeActividadEconomicaValor() {
        return maeActividadEconomicaValor;
    }

    /**
     * @param maeActividadEconomicaValor the maeActividadEconomicaValor to set
     */
    public void setMaeActividadEconomicaValor(String maeActividadEconomicaValor) {
        this.maeActividadEconomicaValor = maeActividadEconomicaValor;
    }

    /**
     * @return the maeArlCodigo
     */
    public String getMaeArlCodigo() {
        return maeArlCodigo;
    }

    /**
     * @param maeArlCodigo the maeArlCodigo to set
     */
    public void setMaeArlCodigo(String maeArlCodigo) {
        this.maeArlCodigo = maeArlCodigo;
    }

    /**
     * @return the maeArlValor
     */
    public String getMaeArlValor() {
        return maeArlValor;
    }

    /**
     * @param maeArlValor the maeArlValor to set
     */
    public void setMaeArlValor(String maeArlValor) {
        this.maeArlValor = maeArlValor;
    }

    /**
     * @return the maeAfpCodigo
     */
    public String getMaeAfpCodigo() {
        return maeAfpCodigo;
    }

    /**
     * @param maeAfpCodigo the maeAfpCodigo to set
     */
    public void setMaeAfpCodigo(String maeAfpCodigo) {
        this.maeAfpCodigo = maeAfpCodigo;
    }

    /**
     * @return the maeAfpValor
     */
    public String getMaeAfpValor() {
        return maeAfpValor;
    }

    /**
     * @param maeAfpValor the maeAfpValor to set
     */
    public void setMaeAfpValor(String maeAfpValor) {
        this.maeAfpValor = maeAfpValor;
    }

    /**
     * @return the maeCajaCompensacionCodigo
     */
    public String getMaeCajaCompensacionCodigo() {
        return maeCajaCompensacionCodigo;
    }

    /**
     * @param maeCajaCompensacionCodigo the maeCajaCompensacionCodigo to set
     */
    public void setMaeCajaCompensacionCodigo(String maeCajaCompensacionCodigo) {
        this.maeCajaCompensacionCodigo = maeCajaCompensacionCodigo;
    }

    /**
     * @return the maeCajaCompensacionValor
     */
    public String getMaeCajaCompensacionValor() {
        return maeCajaCompensacionValor;
    }

    /**
     * @param maeCajaCompensacionValor the maeCajaCompensacionValor to set
     */
    public void setMaeCajaCompensacionValor(String maeCajaCompensacionValor) {
        this.maeCajaCompensacionValor = maeCajaCompensacionValor;
    }

    /**
     * @return the nacionalidadUbicacion
     */
    public Ubicacion getNacionalidadUbicacion() {
        return nacionalidadUbicacion;
    }

    /**
     * @param nacionalidadUbicacion the nacionalidadUbicacion to set
     */
    public void setNacionalidadUbicacion(Ubicacion nacionalidadUbicacion) {
        this.nacionalidadUbicacion = nacionalidadUbicacion;
    }

    /**
     * @return the paisNacionalidad
     */
    public Integer getPaisNacionalidad() {
        return paisNacionalidad;
    }

    /**
     * @param paisNacionalidad the paisNacionalidad to set
     */
    public void setPaisNacionalidad(Integer paisNacionalidad) {
        this.paisNacionalidad = paisNacionalidad;
    }

    /**
     * @return the maeComunidadEtniaId
     */
    public Integer getMaeComunidadEtniaId() {
        return maeComunidadEtniaId;
    }

    /**
     * @param maeComunidadEtniaId the maeComunidadEtniaId to set
     */
    public void setMaeComunidadEtniaId(Integer maeComunidadEtniaId) {
        this.maeComunidadEtniaId = maeComunidadEtniaId;
    }

    /**
     * @return the maeComunidadEtniaCodigo
     */
    public String getMaeComunidadEtniaCodigo() {
        return maeComunidadEtniaCodigo;
    }

    /**
     * @param maeComunidadEtniaCodigo the maeComunidadEtniaCodigo to set
     */
    public void setMaeComunidadEtniaCodigo(String maeComunidadEtniaCodigo) {
        this.maeComunidadEtniaCodigo = maeComunidadEtniaCodigo;
    }

    /**
     * @return the maeComunidadEtniaValor
     */
    public String getMaeComunidadEtniaValor() {
        return maeComunidadEtniaValor;
    }

    /**
     * @param maeComunidadEtniaValor the maeComunidadEtniaValor to set
     */
    public void setMaeComunidadEtniaValor(String maeComunidadEtniaValor) {
        this.maeComunidadEtniaValor = maeComunidadEtniaValor;
    }

    /**
     * @return the maeMetodologiaGrupoPoblacionalId
     */
    public Integer getMaeMetodologiaGrupoPoblacionalId() {
        return maeMetodologiaGrupoPoblacionalId;
    }

    /**
     * @param maeMetodologiaGrupoPoblacionalId the maeMetodologiaGrupoPoblacionalId to set
     */
    public void setMaeMetodologiaGrupoPoblacionalId(Integer maeMetodologiaGrupoPoblacionalId) {
        this.maeMetodologiaGrupoPoblacionalId = maeMetodologiaGrupoPoblacionalId;
    }

    /**
     * @return the maeMetodologiaGrupoPoblacionalCodigo
     */
    public String getMaeMetodologiaGrupoPoblacionalCodigo() {
        return maeMetodologiaGrupoPoblacionalCodigo;
    }

    /**
     * @param maeMetodologiaGrupoPoblacionalCodigo the maeMetodologiaGrupoPoblacionalCodigo to set
     */
    public void setMaeMetodologiaGrupoPoblacionalCodigo(String maeMetodologiaGrupoPoblacionalCodigo) {
        this.maeMetodologiaGrupoPoblacionalCodigo = maeMetodologiaGrupoPoblacionalCodigo;
    }

    /**
     * @return the maeMetodologiaGrupoPoblacionalValor
     */
    public String getMaeMetodologiaGrupoPoblacionalValor() {
        return maeMetodologiaGrupoPoblacionalValor;
    }

    /**
     * @param maeMetodologiaGrupoPoblacionalValor the maeMetodologiaGrupoPoblacionalValor to set
     */
    public void setMaeMetodologiaGrupoPoblacionalValor(String maeMetodologiaGrupoPoblacionalValor) {
        this.maeMetodologiaGrupoPoblacionalValor = maeMetodologiaGrupoPoblacionalValor;
    }

    /**
     * @return the maeSubGrupoSisbenId
     */
    public Integer getMaeSubGrupoSisbenId() {
        return maeSubGrupoSisbenId;
    }

    /**
     * @param maeSubGrupoSisbenId the maeSubGrupoSisbenId to set
     */
    public void setMaeSubGrupoSisbenId(Integer maeSubGrupoSisbenId) {
        this.maeSubGrupoSisbenId = maeSubGrupoSisbenId;
    }

    /**
     * @return the maeSubGrupoSisbenCodigo
     */
    public String getMaeSubGrupoSisbenCodigo() {
        return maeSubGrupoSisbenCodigo;
    }

    /**
     * @param maeSubGrupoSisbenCodigo the maeSubGrupoSisbenCodigo to set
     */
    public void setMaeSubGrupoSisbenCodigo(String maeSubGrupoSisbenCodigo) {
        this.maeSubGrupoSisbenCodigo = maeSubGrupoSisbenCodigo;
    }

    /**
     * @return the maeSubGrupoSisbenValor
     */
    public String getMaeSubGrupoSisbenValor() {
        return maeSubGrupoSisbenValor;
    }

    /**
     * @param maeSubGrupoSisbenValor the maeSubGrupoSisbenValor to set
     */
    public void setMaeSubGrupoSisbenValor(String maeSubGrupoSisbenValor) {
        this.maeSubGrupoSisbenValor = maeSubGrupoSisbenValor;
    }

    /**
     * @return the maeSolidariaPorcentajeId
     */
    public Integer getMaeSolidariaPorcentajeId() {
        return maeSolidariaPorcentajeId;
    }

    /**
     * @param maeSolidariaPorcentajeId the maeSolidariaPorcentajeId to set
     */
    public void setMaeSolidariaPorcentajeId(Integer maeSolidariaPorcentajeId) {
        this.maeSolidariaPorcentajeId = maeSolidariaPorcentajeId;
    }

    /**
     * @return the maeSolidariaPorcentajeCodigo
     */
    public String getMaeSolidariaPorcentajeCodigo() {
        return maeSolidariaPorcentajeCodigo;
    }

    /**
     * @param maeSolidariaPorcentajeCodigo the maeSolidariaPorcentajeCodigo to set
     */
    public void setMaeSolidariaPorcentajeCodigo(String maeSolidariaPorcentajeCodigo) {
        this.maeSolidariaPorcentajeCodigo = maeSolidariaPorcentajeCodigo;
    }

    /**
     * @return the maeSolidariaPorcentajeValor
     */
    public String getMaeSolidariaPorcentajeValor() {
        return maeSolidariaPorcentajeValor;
    }

    /**
     * @param maeSolidariaPorcentajeValor the maeSolidariaPorcentajeValor to set
     */
    public void setMaeSolidariaPorcentajeValor(String maeSolidariaPorcentajeValor) {
        this.maeSolidariaPorcentajeValor = maeSolidariaPorcentajeValor;
    }

}
