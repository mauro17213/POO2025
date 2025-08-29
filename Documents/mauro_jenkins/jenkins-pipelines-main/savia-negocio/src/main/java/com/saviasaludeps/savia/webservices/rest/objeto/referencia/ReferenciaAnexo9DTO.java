package com.saviasaludeps.savia.webservices.rest.objeto.referencia;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ReferenciaAnexo9DTO implements Serializable {

    private String id;
    private String consecutivo;
    private String tipoAnexo;
    //Afiliado
    private String tipoDocumento;
    private String documento;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Bogota")
    private Date fechaNacimiento;
    private String sexo;
    private Double peso;
    private Integer talla;
    //Acompañante
    private String tipodocumentoAcompaniante;
    private String documentoAcompaniante;
    private String primerNombreAcompaniante;
    private String primerApellidoAcompaniante;
    private String segundoNombreAcompaniante;
    private String segundoApellidoAcompaniante;
    private String telefonoAcompaniante;
    private String direccionAcompaniante;
    private String codigoMunicipioAcompaniante;
    //Medico
    private String tipoDocumentoMedico;
    private String documentoMedico;
    private String primerApellidoMedico;
    private String segundoApellidoMedico;
    private String primerNombreMedico;
    private String segundoNombreMedico;
    private String registroMedico;
    private String codigoEspecialidadMedica;
    private String nombreEspecialidadMedica;
    //2024-05-23|lguerrero| eliminar campos cama y piso A9 HU 961
//    private String cama;
//    private String piso;
    private String codigoServicioSolicita;
    private String telefonoSolicita;
    private String codigoServicioRefiere;
    private String codigoHabilitacionIPSSolicitud;
    private String nombreInformante;
    private String cargoInformante;
    private String telefonoInformante;
    private String resumenHistoriaC;
    private String antecedentesHistoriaC;
    private BigDecimal signoVitalTemperatura;
    private BigDecimal signoVitalFrecuenciaC;
    private BigDecimal signoVitalTensionAS;
    private BigDecimal signoVitalTensionAD;
    private BigDecimal signoVitalSaturacionOx;
    private BigDecimal signoVitalFrecuenciaR;
    private Integer escalaGlasgow;
    private String hallazgoExamenFisico;
    //diagnosticos
    private String diagnosticoPrincipal;
    private String diagnosticoRelacionado1;
    private String diagnosticoRelacionado2;
    private Integer prioridadTriage;
    private String motivoRemision;
    private String causaExterna;
    private String condicionDestino;
    private int prioridad;
    private String tipoAtencion;
    private String ubicacion;
    private String modalidadTecnologia;
    private int tipoTecnologia;
    private String tecnologia;
    private int cantidadTecnologia;
    private String afiliadoDireccionAlternativa;
    private String nombreContactoEmergencia;
    private String telefonoContactoEmergencia;

    //adjunto
    private List<AdjuntoDTO> adjuntos;

    public String getTipoAnexo() {
        return tipoAnexo;
    }

    public void setTipoAnexo(String tipoAnexo) {
        this.tipoAnexo = tipoAnexo;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTipodocumentoAcompaniante() {
        return tipodocumentoAcompaniante;
    }

    public void setTipodocumentoAcompaniante(String tipodocumentoAcompaniante) {
        this.tipodocumentoAcompaniante = tipodocumentoAcompaniante;
    }

    public String getDocumentoAcompaniante() {
        return documentoAcompaniante;
    }

    public void setDocumentoAcompaniante(String documentoAcompaniante) {
        this.documentoAcompaniante = documentoAcompaniante;
    }

    public String getPrimerNombreAcompaniante() {
        return primerNombreAcompaniante;
    }

    public void setPrimerNombreAcompaniante(String primerNombreAcompaniante) {
        this.primerNombreAcompaniante = primerNombreAcompaniante;
    }

    public String getPrimerApellidoAcompaniante() {
        return primerApellidoAcompaniante;
    }

    public void setPrimerApellidoAcompaniante(String primerApellidoAcompaniante) {
        this.primerApellidoAcompaniante = primerApellidoAcompaniante;
    }

    public String getTelefonoAcompaniante() {
        return telefonoAcompaniante;
    }

    public void setTelefonoAcompaniante(String telefonoAcompaniante) {
        this.telefonoAcompaniante = telefonoAcompaniante;
    }

    public String getCodigoHabilitacionIPSSolicitud() {
        return codigoHabilitacionIPSSolicitud;
    }

    public void setCodigoHabilitacionIPSSolicitud(String codigoHabilitacionIPSSolicitud) {
        this.codigoHabilitacionIPSSolicitud = codigoHabilitacionIPSSolicitud;
    }

    public String getDiagnosticoPrincipal() {
        return diagnosticoPrincipal;
    }

    public void setDiagnosticoPrincipal(String diagnosticoPrincipal) {
        this.diagnosticoPrincipal = diagnosticoPrincipal;
    }

    public String getDiagnosticoRelacionado1() {
        return diagnosticoRelacionado1;
    }

    public void setDiagnosticoRelacionado1(String diagnosticoRelacionado1) {
        this.diagnosticoRelacionado1 = diagnosticoRelacionado1;
    }

    public String getDiagnosticoRelacionado2() {
        return diagnosticoRelacionado2;
    }

    public void setDiagnosticoRelacionado2(String diagnosticoRelacionado2) {
        this.diagnosticoRelacionado2 = diagnosticoRelacionado2;
    }

    public String getMotivoRemision() {
        return motivoRemision;
    }

    public void setMotivoRemision(String motivoRemision) {
        this.motivoRemision = motivoRemision;
    }

//    public String getCama() {
//        return cama;
//    }
//
//    public void setCama(String cama) {
//        this.cama = cama;
//    }
    public String getResumenHistoriaC() {
        return resumenHistoriaC;
    }

    public void setResumenHistoriaC(String resumenHistoriaC) {
        this.resumenHistoriaC = resumenHistoriaC;
    }

    public Integer getEscalaGlasgow() {
        return escalaGlasgow;
    }

    public void setEscalaGlasgow(Integer escalaGlasgow) {
        this.escalaGlasgow = escalaGlasgow;
    }

    public String getHallazgoExamenFisico() {
        return hallazgoExamenFisico;
    }

    public void setHallazgoExamenFisico(String hallazgoExamenFisico) {
        this.hallazgoExamenFisico = hallazgoExamenFisico;
    }

    public Integer getPrioridadTriage() {
        return prioridadTriage;
    }

    public void setPrioridadTriage(Integer prioridadTriage) {
        this.prioridadTriage = prioridadTriage;
    }

    public String getDocumentoMedico() {
        return documentoMedico;
    }

    public void setDocumentoMedico(String documentoMedico) {
        this.documentoMedico = documentoMedico;
    }

    public List<AdjuntoDTO> getAdjuntos() {
        return adjuntos;
    }

    public void setAdjuntos(List<AdjuntoDTO> adjuntos) {
        this.adjuntos = adjuntos;
    }

    public String getSegundoNombreAcompaniante() {
        return segundoNombreAcompaniante;
    }

    public void setSegundoNombreAcompaniante(String segundoNombreAcompaniante) {
        this.segundoNombreAcompaniante = segundoNombreAcompaniante;
    }

    public String getSegundoApellidoAcompaniante() {
        return segundoApellidoAcompaniante;
    }

    public void setSegundoApellidoAcompaniante(String segundoApellidoAcompaniante) {
        this.segundoApellidoAcompaniante = segundoApellidoAcompaniante;
    }

    public String getDireccionAcompaniante() {
        return direccionAcompaniante;
    }

    public void setDireccionAcompaniante(String direccionAcompaniante) {
        this.direccionAcompaniante = direccionAcompaniante;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Integer getTalla() {
        return talla;
    }

    public void setTalla(Integer talla) {
        this.talla = talla;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

//    public String getPiso() {
//        return piso;
//    }
//
//    public void setPiso(String piso) {
//        this.piso = piso;
//    }
    public String getTipoDocumentoMedico() {
        return tipoDocumentoMedico;
    }

    public void setTipoDocumentoMedico(String tipoDocumentoMedico) {
        this.tipoDocumentoMedico = tipoDocumentoMedico;
    }

    public String getPrimerApellidoMedico() {
        return primerApellidoMedico;
    }

    public void setPrimerApellidoMedico(String primerApellidoMedico) {
        this.primerApellidoMedico = primerApellidoMedico;
    }

    public String getSegundoApellidoMedico() {
        return segundoApellidoMedico;
    }

    public void setSegundoApellidoMedico(String segundoApellidoMedico) {
        this.segundoApellidoMedico = segundoApellidoMedico;
    }

    public String getPrimerNombreMedico() {
        return primerNombreMedico;
    }

    public void setPrimerNombreMedico(String primerNombreMedico) {
        this.primerNombreMedico = primerNombreMedico;
    }

    public String getSegundoNombreMedico() {
        return segundoNombreMedico;
    }

    public void setSegundoNombreMedico(String segundoNombreMedico) {
        this.segundoNombreMedico = segundoNombreMedico;
    }

    public String getRegistroMedico() {
        return registroMedico;
    }

    public void setRegistroMedico(String registroMedico) {
        this.registroMedico = registroMedico;
    }

    public String getCodigoEspecialidadMedica() {
        return codigoEspecialidadMedica;
    }

    public void setCodigoEspecialidadMedica(String codigoEspecialidadMedica) {
        this.codigoEspecialidadMedica = codigoEspecialidadMedica;
    }

    public String getNombreEspecialidadMedica() {
        return nombreEspecialidadMedica;
    }

    public void setNombreEspecialidadMedica(String nombreEspecialidadMedica) {
        this.nombreEspecialidadMedica = nombreEspecialidadMedica;
    }

    public String getCodigoServicioRefiere() {
        return codigoServicioRefiere;
    }

    public void setCodigoServicioRefiere(String codigoServicioRefiere) {
        this.codigoServicioRefiere = codigoServicioRefiere;
    }

    public String getCodigoServicioSolicita() {
        return codigoServicioSolicita;
    }

    public void setCodigoServicioSolicita(String codigoServicioSolicita) {
        this.codigoServicioSolicita = codigoServicioSolicita;
    }

    public String getNombreInformante() {
        return nombreInformante;
    }

    public void setNombreInformante(String nombreInformante) {
        this.nombreInformante = nombreInformante;
    }

    public String getCargoInformante() {
        return cargoInformante;
    }

    public void setCargoInformante(String cargoInformante) {
        this.cargoInformante = cargoInformante;
    }

    public String getTelefonoInformante() {
        return telefonoInformante;
    }

    public void setTelefonoInformante(String telefonoInformante) {
        this.telefonoInformante = telefonoInformante;
    }

    public String getAntecedentesHistoriaC() {
        return antecedentesHistoriaC;
    }

    public void setAntecedentesHistoriaC(String antecedentesHistoriaC) {
        this.antecedentesHistoriaC = antecedentesHistoriaC;
    }

    public String getTelefonoSolicita() {
        return telefonoSolicita;
    }

    public void setTelefonoSolicita(String telefonoSolicita) {
        this.telefonoSolicita = telefonoSolicita;
    }

    public String getCodigoMunicipioAcompaniante() {
        return codigoMunicipioAcompaniante;
    }

    public void setCodigoMunicipioAcompaniante(String codigoMunicipioAcompaniante) {
        this.codigoMunicipioAcompaniante = codigoMunicipioAcompaniante;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCausaExterna() {
        return causaExterna;
    }

    public void setCausaExterna(String causaExterna) {
        this.causaExterna = causaExterna;
    }

    public String getCondicionDestino() {
        return condicionDestino;
    }

    public void setCondicionDestino(String condicionDestino) {
        this.condicionDestino = condicionDestino;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getTipoAtencion() {
        return tipoAtencion;
    }

    public void setTipoAtencion(String tipoAtencion) {
        this.tipoAtencion = tipoAtencion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getModalidadTecnologia() {
        return modalidadTecnologia;
    }

    public void setModalidadTecnologia(String modalidadTecnologia) {
        this.modalidadTecnologia = modalidadTecnologia;
    }

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public int getCantidadTecnologia() {
        return cantidadTecnologia;
    }

    public void setCantidadTecnologia(int cantidadTecnologia) {
        this.cantidadTecnologia = cantidadTecnologia;
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

    public BigDecimal getSignoVitalTemperatura() {
        return signoVitalTemperatura;
    }

    public void setSignoVitalTemperatura(BigDecimal signoVitalTemperatura) {
        this.signoVitalTemperatura = signoVitalTemperatura;
    }

    public BigDecimal getSignoVitalFrecuenciaC() {
        return signoVitalFrecuenciaC;
    }

    public void setSignoVitalFrecuenciaC(BigDecimal signoVitalFrecuenciaC) {
        this.signoVitalFrecuenciaC = signoVitalFrecuenciaC;
    }

    public BigDecimal getSignoVitalTensionAS() {
        return signoVitalTensionAS;
    }

    public void setSignoVitalTensionAS(BigDecimal signoVitalTensionAS) {
        this.signoVitalTensionAS = signoVitalTensionAS;
    }

    public BigDecimal getSignoVitalTensionAD() {
        return signoVitalTensionAD;
    }

    public void setSignoVitalTensionAD(BigDecimal signoVitalTensionAD) {
        this.signoVitalTensionAD = signoVitalTensionAD;
    }

    public BigDecimal getSignoVitalSaturacionOx() {
        return signoVitalSaturacionOx;
    }

    public void setSignoVitalSaturacionOx(BigDecimal signoVitalSaturacionOx) {
        this.signoVitalSaturacionOx = signoVitalSaturacionOx;
    }

    public BigDecimal getSignoVitalFrecuenciaR() {
        return signoVitalFrecuenciaR;
    }

    public void setSignoVitalFrecuenciaR(BigDecimal signoVitalFrecuenciaR) {
        this.signoVitalFrecuenciaR = signoVitalFrecuenciaR;
    }
}
