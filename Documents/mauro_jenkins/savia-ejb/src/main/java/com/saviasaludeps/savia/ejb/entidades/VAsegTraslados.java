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
@Table(name = "v_aseg_traslados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VAsegTraslados.findAll", query = "SELECT v FROM VAsegTraslados v"),
    @NamedQuery(name = "VAsegTraslados.findById", query = "SELECT v FROM VAsegTraslados v WHERE v.id = :id"),
    @NamedQuery(name = "VAsegTraslados.findByCodigoEntidad", query = "SELECT v FROM VAsegTraslados v WHERE v.codigoEntidad = :codigoEntidad"),
    @NamedQuery(name = "VAsegTraslados.findByTipoDocumentoBdua", query = "SELECT v FROM VAsegTraslados v WHERE v.tipoDocumentoBdua = :tipoDocumentoBdua"),
    @NamedQuery(name = "VAsegTraslados.findByNumeroDocumentoBdua", query = "SELECT v FROM VAsegTraslados v WHERE v.numeroDocumentoBdua = :numeroDocumentoBdua"),
    @NamedQuery(name = "VAsegTraslados.findByPrimerApellidoBdua", query = "SELECT v FROM VAsegTraslados v WHERE v.primerApellidoBdua = :primerApellidoBdua"),
    @NamedQuery(name = "VAsegTraslados.findBySegundoApellidoBdua", query = "SELECT v FROM VAsegTraslados v WHERE v.segundoApellidoBdua = :segundoApellidoBdua"),
    @NamedQuery(name = "VAsegTraslados.findByPrimerNombreBdua", query = "SELECT v FROM VAsegTraslados v WHERE v.primerNombreBdua = :primerNombreBdua"),
    @NamedQuery(name = "VAsegTraslados.findBySegundoNombreBdua", query = "SELECT v FROM VAsegTraslados v WHERE v.segundoNombreBdua = :segundoNombreBdua"),
    @NamedQuery(name = "VAsegTraslados.findByFechaNacimientoBdua", query = "SELECT v FROM VAsegTraslados v WHERE v.fechaNacimientoBdua = :fechaNacimientoBdua"),
    @NamedQuery(name = "VAsegTraslados.findByGeneroBdua", query = "SELECT v FROM VAsegTraslados v WHERE v.generoBdua = :generoBdua"),
    @NamedQuery(name = "VAsegTraslados.findByTipoDocumento", query = "SELECT v FROM VAsegTraslados v WHERE v.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "VAsegTraslados.findByNumeroDocumento", query = "SELECT v FROM VAsegTraslados v WHERE v.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "VAsegTraslados.findByPrimerApellido", query = "SELECT v FROM VAsegTraslados v WHERE v.primerApellido = :primerApellido"),
    @NamedQuery(name = "VAsegTraslados.findBySegundoApellido", query = "SELECT v FROM VAsegTraslados v WHERE v.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "VAsegTraslados.findByPrimerNombre", query = "SELECT v FROM VAsegTraslados v WHERE v.primerNombre = :primerNombre"),
    @NamedQuery(name = "VAsegTraslados.findBySegundoNombre", query = "SELECT v FROM VAsegTraslados v WHERE v.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "VAsegTraslados.findByFechaNacimiento", query = "SELECT v FROM VAsegTraslados v WHERE v.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "VAsegTraslados.findByGenero", query = "SELECT v FROM VAsegTraslados v WHERE v.genero = :genero"),
    @NamedQuery(name = "VAsegTraslados.findByCodigoDepartamentoAfiliacion", query = "SELECT v FROM VAsegTraslados v WHERE v.codigoDepartamentoAfiliacion = :codigoDepartamentoAfiliacion"),
    @NamedQuery(name = "VAsegTraslados.findByCodigoMunicipioAfiliacion", query = "SELECT v FROM VAsegTraslados v WHERE v.codigoMunicipioAfiliacion = :codigoMunicipioAfiliacion"),
    @NamedQuery(name = "VAsegTraslados.findByZona", query = "SELECT v FROM VAsegTraslados v WHERE v.zona = :zona"),
    @NamedQuery(name = "VAsegTraslados.findByFechaAfiliacionEps", query = "SELECT v FROM VAsegTraslados v WHERE v.fechaAfiliacionEps = :fechaAfiliacionEps"),
    @NamedQuery(name = "VAsegTraslados.findByCodigoGrupoPoblacional", query = "SELECT v FROM VAsegTraslados v WHERE v.codigoGrupoPoblacional = :codigoGrupoPoblacional"),
    @NamedQuery(name = "VAsegTraslados.findByNivelSisben", query = "SELECT v FROM VAsegTraslados v WHERE v.nivelSisben = :nivelSisben"),
    @NamedQuery(name = "VAsegTraslados.findByTipoTraslado", query = "SELECT v FROM VAsegTraslados v WHERE v.tipoTraslado = :tipoTraslado"),
    @NamedQuery(name = "VAsegTraslados.findByEstadoAfiliado", query = "SELECT v FROM VAsegTraslados v WHERE v.estadoAfiliado = :estadoAfiliado"),
    @NamedQuery(name = "VAsegTraslados.findByContratoAfiliado", query = "SELECT v FROM VAsegTraslados v WHERE v.contratoAfiliado = :contratoAfiliado"),
    @NamedQuery(name = "VAsegTraslados.findByOrigenAfiliado", query = "SELECT v FROM VAsegTraslados v WHERE v.origenAfiliado = :origenAfiliado"),
    @NamedQuery(name = "VAsegTraslados.findByEpsAnterior", query = "SELECT v FROM VAsegTraslados v WHERE v.epsAnterior = :epsAnterior"),
    @NamedQuery(name = "VAsegTraslados.findByCodigoEpsAnterior", query = "SELECT v FROM VAsegTraslados v WHERE v.codigoEpsAnterior = :codigoEpsAnterior"),
    @NamedQuery(name = "VAsegTraslados.findByFechaHoraCrea", query = "SELECT v FROM VAsegTraslados v WHERE v.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "VAsegTraslados.findByUsuarioCrea", query = "SELECT v FROM VAsegTraslados v WHERE v.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "VAsegTraslados.findByFechaMarcacion", query = "SELECT v FROM VAsegTraslados v WHERE v.fechaMarcacion = :fechaMarcacion"),
    @NamedQuery(name = "VAsegTraslados.findByIdReporte", query = "SELECT v FROM VAsegTraslados v WHERE v.idReporte = :idReporte"),
    @NamedQuery(name = "VAsegTraslados.findByMetodologiaGrupoPoblacional", query = "SELECT v FROM VAsegTraslados v WHERE v.metodologiaGrupoPoblacional = :metodologiaGrupoPoblacional"),
    @NamedQuery(name = "VAsegTraslados.findBySubgrupoSisbenIv", query = "SELECT v FROM VAsegTraslados v WHERE v.subgrupoSisbenIv = :subgrupoSisbenIv"),
    @NamedQuery(name = "VAsegTraslados.findByCondicionBeneficiario", query = "SELECT v FROM VAsegTraslados v WHERE v.condicionBeneficiario = :condicionBeneficiario"),
    @NamedQuery(name = "VAsegTraslados.findByTipoDocumentoCabeza", query = "SELECT v FROM VAsegTraslados v WHERE v.tipoDocumentoCabeza = :tipoDocumentoCabeza"),
    @NamedQuery(name = "VAsegTraslados.findByNumeroIdentificacionCabeza", query = "SELECT v FROM VAsegTraslados v WHERE v.numeroIdentificacionCabeza = :numeroIdentificacionCabeza"),
    @NamedQuery(name = "VAsegTraslados.findByParentescoCabeza", query = "SELECT v FROM VAsegTraslados v WHERE v.parentescoCabeza = :parentescoCabeza"),
    @NamedQuery(name = "VAsegTraslados.findByTipoAfiliado", query = "SELECT v FROM VAsegTraslados v WHERE v.tipoAfiliado = :tipoAfiliado")})
public class VAsegTraslados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "id")
    @Id
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "codigo_entidad")
    private String codigoEntidad;
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
    @Size(min = 1, max = 64)
    @Column(name = "primer_apellido_bdua")
    private String primerApellidoBdua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "segundo_apellido_bdua")
    private String segundoApellidoBdua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "primer_nombre_bdua")
    private String primerNombreBdua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "segundo_nombre_bdua")
    private String segundoNombreBdua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "fecha_nacimiento_bdua")
    private String fechaNacimientoBdua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "genero_bdua")
    private String generoBdua;
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
    @Size(min = 1, max = 2)
    @Column(name = "genero")
    private String genero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "codigo_departamento_afiliacion")
    private String codigoDepartamentoAfiliacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "codigo_municipio_afiliacion")
    private String codigoMunicipioAfiliacion;
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
    @Column(name = "codigo_grupo_poblacional")
    private String codigoGrupoPoblacional;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "nivel_sisben")
    private String nivelSisben;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "tipo_traslado")
    private String tipoTraslado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "estado_afiliado")
    private String estadoAfiliado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "contrato_afiliado")
    private String contratoAfiliado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "origen_afiliado")
    private String origenAfiliado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "eps_anterior")
    private String epsAnterior;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "codigo_eps_anterior")
    private String codigoEpsAnterior;
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

    public VAsegTraslados() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigoEntidad() {
        return codigoEntidad;
    }

    public void setCodigoEntidad(String codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
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

    public String getPrimerApellidoBdua() {
        return primerApellidoBdua;
    }

    public void setPrimerApellidoBdua(String primerApellidoBdua) {
        this.primerApellidoBdua = primerApellidoBdua;
    }

    public String getSegundoApellidoBdua() {
        return segundoApellidoBdua;
    }

    public void setSegundoApellidoBdua(String segundoApellidoBdua) {
        this.segundoApellidoBdua = segundoApellidoBdua;
    }

    public String getPrimerNombreBdua() {
        return primerNombreBdua;
    }

    public void setPrimerNombreBdua(String primerNombreBdua) {
        this.primerNombreBdua = primerNombreBdua;
    }

    public String getSegundoNombreBdua() {
        return segundoNombreBdua;
    }

    public void setSegundoNombreBdua(String segundoNombreBdua) {
        this.segundoNombreBdua = segundoNombreBdua;
    }

    public String getFechaNacimientoBdua() {
        return fechaNacimientoBdua;
    }

    public void setFechaNacimientoBdua(String fechaNacimientoBdua) {
        this.fechaNacimientoBdua = fechaNacimientoBdua;
    }

    public String getGeneroBdua() {
        return generoBdua;
    }

    public void setGeneroBdua(String generoBdua) {
        this.generoBdua = generoBdua;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCodigoDepartamentoAfiliacion() {
        return codigoDepartamentoAfiliacion;
    }

    public void setCodigoDepartamentoAfiliacion(String codigoDepartamentoAfiliacion) {
        this.codigoDepartamentoAfiliacion = codigoDepartamentoAfiliacion;
    }

    public String getCodigoMunicipioAfiliacion() {
        return codigoMunicipioAfiliacion;
    }

    public void setCodigoMunicipioAfiliacion(String codigoMunicipioAfiliacion) {
        this.codigoMunicipioAfiliacion = codigoMunicipioAfiliacion;
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

    public String getCodigoGrupoPoblacional() {
        return codigoGrupoPoblacional;
    }

    public void setCodigoGrupoPoblacional(String codigoGrupoPoblacional) {
        this.codigoGrupoPoblacional = codigoGrupoPoblacional;
    }

    public String getNivelSisben() {
        return nivelSisben;
    }

    public void setNivelSisben(String nivelSisben) {
        this.nivelSisben = nivelSisben;
    }

    public String getTipoTraslado() {
        return tipoTraslado;
    }

    public void setTipoTraslado(String tipoTraslado) {
        this.tipoTraslado = tipoTraslado;
    }

    public String getEstadoAfiliado() {
        return estadoAfiliado;
    }

    public void setEstadoAfiliado(String estadoAfiliado) {
        this.estadoAfiliado = estadoAfiliado;
    }

    public String getContratoAfiliado() {
        return contratoAfiliado;
    }

    public void setContratoAfiliado(String contratoAfiliado) {
        this.contratoAfiliado = contratoAfiliado;
    }

    public String getOrigenAfiliado() {
        return origenAfiliado;
    }

    public void setOrigenAfiliado(String origenAfiliado) {
        this.origenAfiliado = origenAfiliado;
    }

    public String getEpsAnterior() {
        return epsAnterior;
    }

    public void setEpsAnterior(String epsAnterior) {
        this.epsAnterior = epsAnterior;
    }

    public String getCodigoEpsAnterior() {
        return codigoEpsAnterior;
    }

    public void setCodigoEpsAnterior(String codigoEpsAnterior) {
        this.codigoEpsAnterior = codigoEpsAnterior;
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
