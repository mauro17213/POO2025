package com.saviasaludeps.savia.webservices.rest.objeto.aseguramiento;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yjimenez
 */
public class RespuestaAfiliadoInformacionSystemSaviaDTO implements Serializable {

    private String consecutivoBDUA;
    private String codigoEntidad;
    private String tipoDocumentoCabezaFamilia;
    private String documentoCabezaFamilia;
    private String tipoDocumentoAfiliado;
    private String documentoAfiliado;
    private String primerApellidoAfiliado;
    private String segundoApellidoAfiliado;
    private String primerNombreAfiliado;
    private String segundoNombreAfiliado;
    private String fechaNacimientoAfiliado;
    private String sexoAfiliado;
    private String tipoAfiliado;
    private String parentescoCabezaFamilia;
    private String grupoPoblacional;
    private String codGrupoPoblacional;
    private String codEtnia;
    private String nivelSisben;
    private String categoriaIBC;
    private String grupoSisben;
    private String subGrupoSisben;
    private String zonaAfiliacion;
    private String fechaAfiliacionSGSSS;
    private String fechaAfiliacionEntidad;
    private String fechaMovilidadEntidad;
    private String modalidadSubsidio;
    private String estadoAfiliacion;
    private String fechaSuspension;
    private String causaEstado;
    private String codCausaEstado;
    private String fechaNovedad;
    private String estadoCivil;
    private String contratoInternoAfilado;
    private String direccion;
    private String origenAfiliado;
    private String puntajeSisben;
    private String telefono;
    private String telefonoMovil;
    private String email;
    private String discapacidad;
    private String regimen;
    private String fechaReactivacion;
    private String fechaRetiro;
    private String victimaLey1448;
    private String tipoDiscapacidad;
    private String codDepartamentoResidencia;
    private String codCiudadResidencia;
    private String codBarrioResidencia;
    private String descripcionCiudadResidencia;
    private String municipioAfiliacion;
    private String departamentoAfiliacion;
    private String codDepartamentoAfiliacion;
    private String codMunicipioAfiliacion;
    private String ciudadAfiliacion;
    private String codOrigenAfiliado;
    private String tipoPortabilidad;
    private String codMunicipioPortabilidad;
    private String codDepartamentoPortabilidad;
    private String ciudadPortabilidad;
    private String departamentoPortabilidad;
    private String descrLiquidacion;
    private String fechaPasoSubsidiadoContributivo;
    private String fechaPasoContributivoSubsidiado;
    private String codigoIPS;
    private String codigoSede;
    private String ipsprimaria;
    private String sedeIPSPrimaria;
    private String ipsportabilidad;
    private String sedeIPSPortabilidad;
    private String observacion;
    private String paisNacionalidad;
    private String paisNacimiento;
    private String departamentoNacimiento;
    private String municipioNacimiento;
    private String generoIdentificacion;
    private String comuna;
    private String barrio;
    private List<AfiliadoProgramaDTO> programas;

    public String getConsecutivoBDUA() {
        return consecutivoBDUA;
    }

    public void setConsecutivoBDUA(String consecutivoBDUA) {
        this.consecutivoBDUA = consecutivoBDUA;
    }

    public String getCodigoEntidad() {
        return codigoEntidad;
    }

    public void setCodigoEntidad(String codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
    }

    public String getTipoDocumentoCabezaFamilia() {
        return tipoDocumentoCabezaFamilia;
    }

    public void setTipoDocumentoCabezaFamilia(String tipoDocumentoCabezaFamilia) {
        this.tipoDocumentoCabezaFamilia = tipoDocumentoCabezaFamilia;
    }

    public String getDocumentoCabezaFamilia() {
        return documentoCabezaFamilia;
    }

    public void setDocumentoCabezaFamilia(String documentoCabezaFamilia) {
        this.documentoCabezaFamilia = documentoCabezaFamilia;
    }

    public String getTipoDocumentoAfiliado() {
        return tipoDocumentoAfiliado;
    }

    public void setTipoDocumentoAfiliado(String tipoDocumentoAfiliado) {
        this.tipoDocumentoAfiliado = tipoDocumentoAfiliado;
    }

    public String getDocumentoAfiliado() {
        return documentoAfiliado;
    }

    public void setDocumentoAfiliado(String documentoAfiliado) {
        this.documentoAfiliado = documentoAfiliado;
    }

    public String getPrimerApellidoAfiliado() {
        return primerApellidoAfiliado;
    }

    public void setPrimerApellidoAfiliado(String primerApellidoAfiliado) {
        this.primerApellidoAfiliado = primerApellidoAfiliado;
    }

    public String getSegundoApellidoAfiliado() {
        return segundoApellidoAfiliado;
    }

    public void setSegundoApellidoAfiliado(String segundoApellidoAfiliado) {
        this.segundoApellidoAfiliado = segundoApellidoAfiliado;
    }

    public String getPrimerNombreAfiliado() {
        return primerNombreAfiliado;
    }

    public void setPrimerNombreAfiliado(String primerNombreAfiliado) {
        this.primerNombreAfiliado = primerNombreAfiliado;
    }

    public String getSegundoNombreAfiliado() {
        return segundoNombreAfiliado;
    }

    public void setSegundoNombreAfiliado(String segundoNombreAfiliado) {
        this.segundoNombreAfiliado = segundoNombreAfiliado;
    }

    public String getFechaNacimientoAfiliado() {
        return fechaNacimientoAfiliado;
    }

    public void setFechaNacimientoAfiliado(String fechaNacimientoAfiliado) {
        this.fechaNacimientoAfiliado = fechaNacimientoAfiliado;
    }

    public String getSexoAfiliado() {
        return sexoAfiliado;
    }

    public void setSexoAfiliado(String sexoAfiliado) {
        this.sexoAfiliado = sexoAfiliado;
    }

    public String getTipoAfiliado() {
        return tipoAfiliado;
    }

    public void setTipoAfiliado(String tipoAfiliado) {
        this.tipoAfiliado = tipoAfiliado;
    }

    public String getParentescoCabezaFamilia() {
        return parentescoCabezaFamilia;
    }

    public void setParentescoCabezaFamilia(String parentescoCabezaFamilia) {
        this.parentescoCabezaFamilia = parentescoCabezaFamilia;
    }

    public String getGrupoPoblacional() {
        return grupoPoblacional;
    }

    public void setGrupoPoblacional(String grupoPoblacional) {
        this.grupoPoblacional = grupoPoblacional;
    }

    public String getCodGrupoPoblacional() {
        return codGrupoPoblacional;
    }

    public void setCodGrupoPoblacional(String codGrupoPoblacional) {
        this.codGrupoPoblacional = codGrupoPoblacional;
    }

    public String getCodEtnia() {
        return codEtnia;
    }

    public void setCodEtnia(String codEtnia) {
        this.codEtnia = codEtnia;
    }

    public String getNivelSisben() {
        return nivelSisben;
    }

    public void setNivelSisben(String nivelSisben) {
        this.nivelSisben = nivelSisben;
    }

    public String getCodDepartamentoAfiliacion() {
        return codDepartamentoAfiliacion;
    }

    public void setCodDepartamentoAfiliacion(String codDepartamentoAfiliacion) {
        this.codDepartamentoAfiliacion = codDepartamentoAfiliacion;
    }

    public String getCodMunicipioAfiliacion() {
        return codMunicipioAfiliacion;
    }

    public void setCodMunicipioAfiliacion(String codMunicipioAfiliacion) {
        this.codMunicipioAfiliacion = codMunicipioAfiliacion;
    }

    public String getZonaAfiliacion() {
        return zonaAfiliacion;
    }

    public void setZonaAfiliacion(String zonaAfiliacion) {
        this.zonaAfiliacion = zonaAfiliacion;
    }

    public String getFechaAfiliacionSGSSS() {
        return fechaAfiliacionSGSSS;
    }

    public void setFechaAfiliacionSGSSS(String fechaAfiliacionSGSSS) {
        this.fechaAfiliacionSGSSS = fechaAfiliacionSGSSS;
    }

    public String getFechaAfiliacionEntidad() {
        return fechaAfiliacionEntidad;
    }

    public void setFechaAfiliacionEntidad(String fechaAfiliacionEntidad) {
        this.fechaAfiliacionEntidad = fechaAfiliacionEntidad;
    }

    public String getModalidadSubsidio() {
        return modalidadSubsidio;
    }

    public void setModalidadSubsidio(String modalidadSubsidio) {
        this.modalidadSubsidio = modalidadSubsidio;
    }

    public String getEstadoAfiliacion() {
        return estadoAfiliacion;
    }

    public void setEstadoAfiliacion(String estadoAfiliacion) {
        this.estadoAfiliacion = estadoAfiliacion;
    }

    public String getCausaEstado() {
        return causaEstado;
    }

    public void setCausaEstado(String causaEstado) {
        this.causaEstado = causaEstado;
    }

    public String getFechaNovedad() {
        return fechaNovedad;
    }

    public void setFechaNovedad(String fechaNovedad) {
        this.fechaNovedad = fechaNovedad;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getContratoInternoAfilado() {
        return contratoInternoAfilado;
    }

    public void setContratoInternoAfilado(String contratoInternoAfilado) {
        this.contratoInternoAfilado = contratoInternoAfilado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getOrigenAfiliado() {
        return origenAfiliado;
    }

    public void setOrigenAfiliado(String origenAfiliado) {
        this.origenAfiliado = origenAfiliado;
    }

    public String getPuntajeSisben() {
        return puntajeSisben;
    }

    public void setPuntajeSisben(String puntajeSisben) {
        this.puntajeSisben = puntajeSisben;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCodigoIPS() {
        return codigoIPS;
    }

    public void setCodigoIPS(String codigoIPS) {
        this.codigoIPS = codigoIPS;
    }

    public String getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(String codigoSede) {
        this.codigoSede = codigoSede;
    }

    public String getSedeIPSPortabilidad() {
        return sedeIPSPortabilidad;
    }

    public void setSedeIPSPortabilidad(String sedeIPSPortabilidad) {
        this.sedeIPSPortabilidad = sedeIPSPortabilidad;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public String getFechaReactivacion() {
        return fechaReactivacion;
    }

    public void setFechaReactivacion(String fechaReactivacion) {
        this.fechaReactivacion = fechaReactivacion;
    }

    public String getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(String fechaRetiro) {
        this.fechaRetiro = fechaRetiro;
    }

    public String getVictimaLey1448() {
        return victimaLey1448;
    }

    public void setVictimaLey1448(String victimaLey1448) {
        this.victimaLey1448 = victimaLey1448;
    }

    public String getTipoDiscapacidad() {
        return tipoDiscapacidad;
    }

    public void setTipoDiscapacidad(String tipoDiscapacidad) {
        this.tipoDiscapacidad = tipoDiscapacidad;
    }

    public String getCodDepartamentoResidencia() {
        return codDepartamentoResidencia;
    }

    public void setCodDepartamentoResidencia(String codDepartamentoResidencia) {
        this.codDepartamentoResidencia = codDepartamentoResidencia;
    }

    public String getCodCiudadResidencia() {
        return codCiudadResidencia;
    }

    public void setCodCiudadResidencia(String codCiudadResidencia) {
        this.codCiudadResidencia = codCiudadResidencia;
    }

    public String getCodBarrioResidencia() {
        return codBarrioResidencia;
    }

    public void setCodBarrioResidencia(String codBarrioResidencia) {
        this.codBarrioResidencia = codBarrioResidencia;
    }

    public String getCiudadAfiliacion() {
        return ciudadAfiliacion;
    }

    public void setCiudadAfiliacion(String ciudadAfiliacion) {
        this.ciudadAfiliacion = ciudadAfiliacion;
    }

    public String getDepartamentoAfiliacion() {
        return departamentoAfiliacion;
    }

    public void setDepartamentoAfiliacion(String departamentoAfiliacion) {
        this.departamentoAfiliacion = departamentoAfiliacion;
    }

    public String getCodOrigenAfiliado() {
        return codOrigenAfiliado;
    }

    public void setCodOrigenAfiliado(String codOrigenAfiliado) {
        this.codOrigenAfiliado = codOrigenAfiliado;
    }

    public String getTipoPortabilidad() {
        return tipoPortabilidad;
    }

    public void setTipoPortabilidad(String tipoPortabilidad) {
        this.tipoPortabilidad = tipoPortabilidad;
    }

    public String getCodMunicipioPortabilidad() {
        return codMunicipioPortabilidad;
    }

    public void setCodMunicipioPortabilidad(String codMunicipioPortabilidad) {
        this.codMunicipioPortabilidad = codMunicipioPortabilidad;
    }

    public String getCodDepartamentoPortabilidad() {
        return codDepartamentoPortabilidad;
    }

    public void setCodDepartamentoPortabilidad(String codDepartamentoPortabilidad) {
        this.codDepartamentoPortabilidad = codDepartamentoPortabilidad;
    }

    public String getCiudadPortabilidad() {
        return ciudadPortabilidad;
    }

    public void setCiudadPortabilidad(String ciudadPortabilidad) {
        this.ciudadPortabilidad = ciudadPortabilidad;
    }

    public String getDepartamentoPortabilidad() {
        return departamentoPortabilidad;
    }

    public void setDepartamentoPortabilidad(String departamentoPortabilidad) {
        this.departamentoPortabilidad = departamentoPortabilidad;
    }

    public String getDescrLiquidacion() {
        return descrLiquidacion;
    }

    public void setDescrLiquidacion(String descrLiquidacion) {
        this.descrLiquidacion = descrLiquidacion;
    }

    public String getDescripcionCiudadResidencia() {
        return descripcionCiudadResidencia;
    }

    public void setDescripcionCiudadResidencia(String descripcionCiudadResidencia) {
        this.descripcionCiudadResidencia = descripcionCiudadResidencia;
    }

    public String getFechaPasoSubsidiadoContributivo() {
        return fechaPasoSubsidiadoContributivo;
    }

    public void setFechaPasoSubsidiadoContributivo(String fechaPasoSubsidiadoContributivo) {
        this.fechaPasoSubsidiadoContributivo = fechaPasoSubsidiadoContributivo;
    }

    public String getFechaPasoContributivoSubsidiado() {
        return fechaPasoContributivoSubsidiado;
    }

    public void setFechaPasoContributivoSubsidiado(String fechaPasoContributivoSubsidiado) {
        this.fechaPasoContributivoSubsidiado = fechaPasoContributivoSubsidiado;
    }

    public String getMunicipioAfiliacion() {
        return municipioAfiliacion;
    }

    public void setMunicipioAfiliacion(String municipioAfiliacion) {
        this.municipioAfiliacion = municipioAfiliacion;
    }

    public String getSedeIPSPrimaria() {
        return sedeIPSPrimaria;
    }

    public void setSedeIPSPrimaria(String sedeIPSPrimaria) {
        this.sedeIPSPrimaria = sedeIPSPrimaria;
    }

    public String getIpsportabilidad() {
        return ipsportabilidad;
    }

    public void setIpsportabilidad(String ipsportabilidad) {
        this.ipsportabilidad = ipsportabilidad;
    }

    public String getIpsprimaria() {
        return ipsprimaria;
    }

    public void setIpsprimaria(String ipsprimaria) {
        this.ipsprimaria = ipsprimaria;
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

    public String getGrupoSisben() {
        return grupoSisben;
    }

    public void setGrupoSisben(String grupoSisben) {
        this.grupoSisben = grupoSisben;
    }

    public String getSubGrupoSisben() {
        return subGrupoSisben;
    }

    public void setSubGrupoSisben(String subGrupoSisben) {
        this.subGrupoSisben = subGrupoSisben;
    }

    public String getEtnia() {
        return codEtnia;
    }

    public void setEtnia(String etnia) {
        this.codEtnia = etnia;
    }

    public String getCodCausaEstado() {
        return codCausaEstado;
    }

    public void setCodCausaEstado(String codCausaEstado) {
        this.codCausaEstado = codCausaEstado;
    }

    public String getCategoriaIBC() {
        return categoriaIBC;
    }

    public void setCategoriaIBC(String categoriaIBC) {
        this.categoriaIBC = categoriaIBC;
    }

    public String getFechaMovilidadEntidad() {
        return fechaMovilidadEntidad;
    }

    public void setFechaMovilidadEntidad(String fechaMovilidadEntidad) {
        this.fechaMovilidadEntidad = fechaMovilidadEntidad;
    }

    public String getFechaSuspension() {
        return fechaSuspension;
    }

    public void setFechaSuspension(String fechaSuspension) {
        this.fechaSuspension = fechaSuspension;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getPaisNacionalidad() {
        return paisNacionalidad;
    }

    public void setPaisNacionalidad(String paisNacionalidad) {
        this.paisNacionalidad = paisNacionalidad;
    }

    public String getPaisNacimiento() {
        return paisNacimiento;
    }

    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }

    public String getDepartamentoNacimiento() {
        return departamentoNacimiento;
    }

    public void setDepartamentoNacimiento(String departamentoNacimiento) {
        this.departamentoNacimiento = departamentoNacimiento;
    }

    public String getMunicipioNacimiento() {
        return municipioNacimiento;
    }

    public void setMunicipioNacimiento(String municipioNacimiento) {
        this.municipioNacimiento = municipioNacimiento;
    }

    public String getGeneroIdentificacion() {
        return generoIdentificacion;
    }

    public void setGeneroIdentificacion(String generoIdentificacion) {
        this.generoIdentificacion = generoIdentificacion;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public List<AfiliadoProgramaDTO> getProgramas() {
        return programas;
    }

    public void setProgramas(List<AfiliadoProgramaDTO> programas) {
        this.programas = programas;
    }
}
