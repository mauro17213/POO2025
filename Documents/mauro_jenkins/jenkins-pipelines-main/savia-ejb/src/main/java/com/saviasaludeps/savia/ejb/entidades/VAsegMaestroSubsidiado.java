/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jyperez
 */
@Entity
@Table(name = "v_aseg_maestro_subsidiado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VAsegMaestroSubsidiado.findAll", query = "SELECT v FROM VAsegMaestroSubsidiado v"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByCodigoEps", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.codigoEps = :codigoEps"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByTipoDocumento", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByNumeroDocumento", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByPrimerApellido", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.primerApellido = :primerApellido"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findBySegundoApellido", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByPrimerNombre", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.primerNombre = :primerNombre"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findBySegundoNombre", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByFechaNacimiento", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findBySexo", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.sexo = :sexo"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByCodigoDepartamento", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.codigoDepartamento = :codigoDepartamento"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByCodigoMunicipio", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.codigoMunicipio = :codigoMunicipio"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByZona", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.zona = :zona"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByFechaAfiliacionEps", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.fechaAfiliacionEps = :fechaAfiliacionEps"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByGrupoPoblacional", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.grupoPoblacional = :grupoPoblacional"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByNivelSisben", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.nivelSisben = :nivelSisben"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByCodigoHabilitacionSede", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.codigoHabilitacionSede = :codigoHabilitacionSede"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByContratoAfiliado", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.contratoAfiliado = :contratoAfiliado"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByEstadoAfiliacion", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.estadoAfiliacion = :estadoAfiliacion"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByTipoDocumentoBdua", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.tipoDocumentoBdua = :tipoDocumentoBdua"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByNumeroDocumentoBdua", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.numeroDocumentoBdua = :numeroDocumentoBdua"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByOrigenAfiliado", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.origenAfiliado = :origenAfiliado"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByFechaHoraCrea", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByUsuarioCrea", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByFechaMarcacion", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.fechaMarcacion = :fechaMarcacion"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByIdReporte", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.idReporte = :idReporte"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByMetodologiaGrupoPoblacional", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.metodologiaGrupoPoblacional = :metodologiaGrupoPoblacional"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findBySubgrupoSisbenIv", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.subgrupoSisbenIv = :subgrupoSisbenIv"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByCondicionBeneficiario", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.condicionBeneficiario = :condicionBeneficiario"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByTipoDocumentoCabeza", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.tipoDocumentoCabeza = :tipoDocumentoCabeza"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByNumeroIdentificacionCabeza", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.numeroIdentificacionCabeza = :numeroIdentificacionCabeza"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByParentescoCabeza", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.parentescoCabeza = :parentescoCabeza"),
    @NamedQuery(name = "VAsegMaestroSubsidiado.findByTipoAfiliado", query = "SELECT v FROM VAsegMaestroSubsidiado v WHERE v.tipoAfiliado = :tipoAfiliado")})
public class VAsegMaestroSubsidiado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "codigo_eps")
    private String codigoEps;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "tipo_documento")
    private String tipoDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "primer_apellido")
    private String primerApellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "primer_nombre")
    private String primerNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "sexo")
    private String sexo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "codigo_departamento")
    private String codigoDepartamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "codigo_municipio")
    private String codigoMunicipio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "zona")
    private String zona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "fecha_afiliacion_eps")
    private String fechaAfiliacionEps;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "grupo_poblacional")
    private String grupoPoblacional;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "nivel_sisben")
    private String nivelSisben;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "codigo_habilitacion_sede")
    private String codigoHabilitacionSede;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "contrato_afiliado")
    @Id
    private String contratoAfiliado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "estado_afiliacion")
    private String estadoAfiliacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "tipo_documento_bdua")
    private String tipoDocumentoBdua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "numero_documento_bdua")
    private String numeroDocumentoBdua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "origen_afiliado")
    private String origenAfiliado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 19)
    @Column(name = "fecha_hora_crea")
    private String fechaHoraCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 19)
    @Column(name = "fecha_marcacion")
    private String fechaMarcacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "id_reporte")
    private String idReporte;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "metodologia_grupo_poblacional")
    private String metodologiaGrupoPoblacional;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "subgrupo_sisben_iv")
    private String subgrupoSisbenIv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "condicion_beneficiario")
    private String condicionBeneficiario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "tipo_documento_cabeza")
    private String tipoDocumentoCabeza;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "numero_identificacion_cabeza")
    private String numeroIdentificacionCabeza;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "parentesco_cabeza")
    private String parentescoCabeza;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "tipo_afiliado")
    private String tipoAfiliado;

    public VAsegMaestroSubsidiado() {
    }

    public String getCodigoEps() {
        return codigoEps;
    }

    public void setCodigoEps(String codigoEps) {
        this.codigoEps = codigoEps;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getFechaAfiliacionEps() {
        return fechaAfiliacionEps;
    }

    public void setFechaAfiliacionEps(String fechaAfiliacionEps) {
        this.fechaAfiliacionEps = fechaAfiliacionEps;
    }

    public String getGrupoPoblacional() {
        return grupoPoblacional;
    }

    public void setGrupoPoblacional(String grupoPoblacional) {
        this.grupoPoblacional = grupoPoblacional;
    }

    public String getNivelSisben() {
        return nivelSisben;
    }

    public void setNivelSisben(String nivelSisben) {
        this.nivelSisben = nivelSisben;
    }

    public String getCodigoHabilitacionSede() {
        return codigoHabilitacionSede;
    }

    public void setCodigoHabilitacionSede(String codigoHabilitacionSede) {
        this.codigoHabilitacionSede = codigoHabilitacionSede;
    }

    public String getContratoAfiliado() {
        return contratoAfiliado;
    }

    public void setContratoAfiliado(String contratoAfiliado) {
        this.contratoAfiliado = contratoAfiliado;
    }

    public String getEstadoAfiliacion() {
        return estadoAfiliacion;
    }

    public void setEstadoAfiliacion(String estadoAfiliacion) {
        this.estadoAfiliacion = estadoAfiliacion;
    }

    public String getTipoDocumentoBdua() {
        return tipoDocumentoBdua;
    }

    public void setTipoDocumentoBdua(String tipoDocumentoBdua) {
        this.tipoDocumentoBdua = tipoDocumentoBdua;
    }

    public String getNumeroDocumentoBdua() {
        return numeroDocumentoBdua;
    }

    public void setNumeroDocumentoBdua(String numeroDocumentoBdua) {
        this.numeroDocumentoBdua = numeroDocumentoBdua;
    }

    public String getOrigenAfiliado() {
        return origenAfiliado;
    }

    public void setOrigenAfiliado(String origenAfiliado) {
        this.origenAfiliado = origenAfiliado;
    }

    public String getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(String fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getFechaMarcacion() {
        return fechaMarcacion;
    }

    public void setFechaMarcacion(String fechaMarcacion) {
        this.fechaMarcacion = fechaMarcacion;
    }

    public String getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(String idReporte) {
        this.idReporte = idReporte;
    }
    
    public String getMetodologiaGrupoPoblacional() {
        return metodologiaGrupoPoblacional;
    }
    
    public void setMetodologiaGrupoPoblacional(String metodologiaGrupoPoblacional) {
        this.metodologiaGrupoPoblacional = metodologiaGrupoPoblacional;
    }
    
    public String getSubgrupoSisbenIv() {
        return subgrupoSisbenIv;
    }
    
    public void setSubgrupoSisbenIv(String subgrupoSisbenIv) {
        this.subgrupoSisbenIv = subgrupoSisbenIv;
    }
    
    public String getCondicionBeneficiario() {
        return condicionBeneficiario;
    }
    
    public void setCondicionBeneficiario(String condicionBeneficiario) {
        this.condicionBeneficiario = condicionBeneficiario;
    }
    
    public String getTipoDocumentoCabeza() {
        return tipoDocumentoCabeza;
    }
    
    public void setTipoDocumentoCabeza(String tipoDocumentoCabeza) {
        this.tipoDocumentoCabeza = tipoDocumentoCabeza;
    }
    
    public String getNumeroIdentificacionCabeza() {
        return numeroIdentificacionCabeza;
    }
    
    public void setNumeroIdentificacionCabeza(String numeroIdentificacionCabeza) {
        this.numeroIdentificacionCabeza = numeroIdentificacionCabeza;
    }
    
    public String getParentescoCabeza() {
        return parentescoCabeza;
    }
    
    public void setParentescoCabeza(String parentescoCabeza) {
        this.parentescoCabeza = parentescoCabeza;
    }
    
    public String getTipoAfiliado() {
        return tipoAfiliado;
    }
    
    public void setTipoAfiliado(String tipoAfiliado) {
        this.tipoAfiliado = tipoAfiliado;
    }
    
}
