/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jyperez
 */
@Entity
@Table(name = "v_aseg_novedades_subsidiado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findAll", query = "SELECT v FROM VAsegNovedadesSubsidiado v"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findById", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.id = :id"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByCodigoEps", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.codigoEps = :codigoEps"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByTipoDocumento", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByNumeroDocumento", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByPrimerApellido", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.primerApellido = :primerApellido"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findBySegundoApellido", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByPrimerNombre", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.primerNombre = :primerNombre"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findBySegundoNombre", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByFechaNacimiento", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByCodigoDepartamento", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.codigoDepartamento = :codigoDepartamento"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByCodigoMunicipio", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.codigoMunicipio = :codigoMunicipio"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByCodigoNovedad", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.codigoNovedad = :codigoNovedad"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByFechaNovedad", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.fechaNovedad = :fechaNovedad"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByValor1", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.valor1 = :valor1"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByValor2", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.valor2 = :valor2"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByValor3", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.valor3 = :valor3"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByValor4", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.valor4 = :valor4"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByValor5", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.valor5 = :valor5"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByValor6", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.valor6 = :valor6"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByValor7", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.valor7 = :valor7"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByContratoAfiliado", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.contratoAfiliado = :contratoAfiliado"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByEstadoAfiliacion", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.estadoAfiliacion = :estadoAfiliacion"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByCausaNovedad", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.causaNovedad = :causaNovedad"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByCodigoCausaNovedad", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.codigoCausaNovedad = :codigoCausaNovedad"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByFechaHoraCrea", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByUsuarioCrea", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByFechaHoraReporte", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.fechaHoraReporte = :fechaHoraReporte"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByIdReporte", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.idReporte = :idReporte"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByMetodologiaGrupoPoblacional", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.metodologiaGrupoPoblacional = :metodologiaGrupoPoblacional"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findBySubgrupoSisbenIv", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.subgrupoSisbenIv = :subgrupoSisbenIv"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByCondicionBeneficiario", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.condicionBeneficiario = :condicionBeneficiario"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByTipoDocumentoCabeza", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.tipoDocumentoCabeza = :tipoDocumentoCabeza"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByNumeroIdentificacionCabeza", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.numeroIdentificacionCabeza = :numeroIdentificacionCabeza"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByParentescoCabeza", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.parentescoCabeza = :parentescoCabeza"),
    @NamedQuery(name = "VAsegNovedadesSubsidiado.findByTipoAfiliado", query = "SELECT v FROM VAsegNovedadesSubsidiado v WHERE v.tipoAfiliado = :tipoAfiliado")})
public class VAsegNovedadesSubsidiado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    @Id
    private int id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "codigo_eps")
    private String codigoEps;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
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
    @Size(max = 64)
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "primer_nombre")
    private String primerNombre;
    @Size(max = 64)
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "codigo_departamento")
    private String codigoDepartamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "codigo_municipio")
    private String codigoMunicipio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "codigo_novedad")
    private String codigoNovedad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_novedad")
    @Temporal(TemporalType.DATE)
    private Date fechaNovedad;
    @Size(max = 64)
    @Column(name = "valor_1")
    private String valor1;
    @Size(max = 64)
    @Column(name = "valor_2")
    private String valor2;
    @Size(max = 64)
    @Column(name = "valor_3")
    private String valor3;
    @Size(max = 64)
    @Column(name = "valor_4")
    private String valor4;
    @Size(max = 64)
    @Column(name = "valor_5")
    private String valor5;
    @Size(max = 64)
    @Column(name = "valor_6")
    private String valor6;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 0)
    @Column(name = "valor_7")
    private String valor7;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "contrato_afiliado")
    private String contratoAfiliado;
    @Size(max = 512)
    @Column(name = "estado_afiliacion")
    private String estadoAfiliacion;
    @Size(max = 512)
    @Column(name = "causa_novedad")
    private String causaNovedad;
    @Column(name = "codigo_causa_novedad")
    private Integer codigoCausaNovedad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Column(name = "fecha_hora_reporte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraReporte;
    @Column(name = "id_reporte")
    private Integer idReporte;
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

    public VAsegNovedadesSubsidiado() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public String getCodigoNovedad() {
        return codigoNovedad;
    }

    public void setCodigoNovedad(String codigoNovedad) {
        this.codigoNovedad = codigoNovedad;
    }

    public Date getFechaNovedad() {
        return fechaNovedad;
    }

    public void setFechaNovedad(Date fechaNovedad) {
        this.fechaNovedad = fechaNovedad;
    }

    public String getValor1() {
        return valor1;
    }

    public void setValor1(String valor1) {
        this.valor1 = valor1;
    }

    public String getValor2() {
        return valor2;
    }

    public void setValor2(String valor2) {
        this.valor2 = valor2;
    }

    public String getValor3() {
        return valor3;
    }

    public void setValor3(String valor3) {
        this.valor3 = valor3;
    }

    public String getValor4() {
        return valor4;
    }

    public void setValor4(String valor4) {
        this.valor4 = valor4;
    }

    public String getValor5() {
        return valor5;
    }

    public void setValor5(String valor5) {
        this.valor5 = valor5;
    }

    public String getValor6() {
        return valor6;
    }

    public void setValor6(String valor6) {
        this.valor6 = valor6;
    }

    public String getValor7() {
        return valor7;
    }

    public void setValor7(String valor7) {
        this.valor7 = valor7;
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

    public String getCausaNovedad() {
        return causaNovedad;
    }

    public void setCausaNovedad(String causaNovedad) {
        this.causaNovedad = causaNovedad;
    }

    public Integer getCodigoCausaNovedad() {
        return codigoCausaNovedad;
    }

    public void setCodigoCausaNovedad(Integer codigoCausaNovedad) {
        this.codigoCausaNovedad = codigoCausaNovedad;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public Date getFechaHoraReporte() {
        return fechaHoraReporte;
    }

    public void setFechaHoraReporte(Date fechaHoraReporte) {
        this.fechaHoraReporte = fechaHoraReporte;
    }

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
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
