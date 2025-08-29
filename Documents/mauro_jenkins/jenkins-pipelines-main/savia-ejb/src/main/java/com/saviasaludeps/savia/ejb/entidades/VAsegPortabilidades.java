/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "v_aseg_portabilidades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VAsegPortabilidades.findAll", query = "SELECT v FROM VAsegPortabilidades v"),
    @NamedQuery(name = "VAsegPortabilidades.findByRadicadoPortabilidad", query = "SELECT v FROM VAsegPortabilidades v WHERE v.radicadoPortabilidad = :radicadoPortabilidad"),
    @NamedQuery(name = "VAsegPortabilidades.findByFechaRadicacion", query = "SELECT v FROM VAsegPortabilidades v WHERE v.fechaRadicacion = :fechaRadicacion"),
    @NamedQuery(name = "VAsegPortabilidades.findByContratoAfiliado", query = "SELECT v FROM VAsegPortabilidades v WHERE v.contratoAfiliado = :contratoAfiliado"),
    @NamedQuery(name = "VAsegPortabilidades.findByTipoDocumento", query = "SELECT v FROM VAsegPortabilidades v WHERE v.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "VAsegPortabilidades.findByNumeroDocumento", query = "SELECT v FROM VAsegPortabilidades v WHERE v.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "VAsegPortabilidades.findByPrimerNombre", query = "SELECT v FROM VAsegPortabilidades v WHERE v.primerNombre = :primerNombre"),
    @NamedQuery(name = "VAsegPortabilidades.findBySegundoNombre", query = "SELECT v FROM VAsegPortabilidades v WHERE v.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "VAsegPortabilidades.findByPrimerApellido", query = "SELECT v FROM VAsegPortabilidades v WHERE v.primerApellido = :primerApellido"),
    @NamedQuery(name = "VAsegPortabilidades.findBySegundoApellido", query = "SELECT v FROM VAsegPortabilidades v WHERE v.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "VAsegPortabilidades.findByFechaNacimiento", query = "SELECT v FROM VAsegPortabilidades v WHERE v.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "VAsegPortabilidades.findByCodigoDepartamentoAfiliacion", query = "SELECT v FROM VAsegPortabilidades v WHERE v.codigoDepartamentoAfiliacion = :codigoDepartamentoAfiliacion"),
    @NamedQuery(name = "VAsegPortabilidades.findByCodigoMunicipioAfiliacion", query = "SELECT v FROM VAsegPortabilidades v WHERE v.codigoMunicipioAfiliacion = :codigoMunicipioAfiliacion"),
    @NamedQuery(name = "VAsegPortabilidades.findByDepartamentoAfiliacion", query = "SELECT v FROM VAsegPortabilidades v WHERE v.departamentoAfiliacion = :departamentoAfiliacion"),
    @NamedQuery(name = "VAsegPortabilidades.findByMunicipioAfiliacion", query = "SELECT v FROM VAsegPortabilidades v WHERE v.municipioAfiliacion = :municipioAfiliacion"),
    @NamedQuery(name = "VAsegPortabilidades.findByDireccionResidencia", query = "SELECT v FROM VAsegPortabilidades v WHERE v.direccionResidencia = :direccionResidencia"),
    @NamedQuery(name = "VAsegPortabilidades.findByTelefono1Afiliado", query = "SELECT v FROM VAsegPortabilidades v WHERE v.telefono1Afiliado = :telefono1Afiliado"),
    @NamedQuery(name = "VAsegPortabilidades.findByTelefono2Afiliado", query = "SELECT v FROM VAsegPortabilidades v WHERE v.telefono2Afiliado = :telefono2Afiliado"),
    @NamedQuery(name = "VAsegPortabilidades.findByTelefono3Portabilidad", query = "SELECT v FROM VAsegPortabilidades v WHERE v.telefono3Portabilidad = :telefono3Portabilidad"),
    @NamedQuery(name = "VAsegPortabilidades.findByTelefono4Portabilidad", query = "SELECT v FROM VAsegPortabilidades v WHERE v.telefono4Portabilidad = :telefono4Portabilidad"),
    @NamedQuery(name = "VAsegPortabilidades.findByCorreoElectronicoPortabilidad", query = "SELECT v FROM VAsegPortabilidades v WHERE v.correoElectronicoPortabilidad = :correoElectronicoPortabilidad"),
    @NamedQuery(name = "VAsegPortabilidades.findByGrupoPoblacional", query = "SELECT v FROM VAsegPortabilidades v WHERE v.grupoPoblacional = :grupoPoblacional"),
    @NamedQuery(name = "VAsegPortabilidades.findByEstadoPortabilidad", query = "SELECT v FROM VAsegPortabilidades v WHERE v.estadoPortabilidad = :estadoPortabilidad"),
    @NamedQuery(name = "VAsegPortabilidades.findByIpsPrimariaSede", query = "SELECT v FROM VAsegPortabilidades v WHERE v.ipsPrimariaSede = :ipsPrimariaSede"),
    @NamedQuery(name = "VAsegPortabilidades.findByCodigoDepartamentoPortabilidad", query = "SELECT v FROM VAsegPortabilidades v WHERE v.codigoDepartamentoPortabilidad = :codigoDepartamentoPortabilidad"),
    @NamedQuery(name = "VAsegPortabilidades.findByCodigoMunicipioPortabilidad", query = "SELECT v FROM VAsegPortabilidades v WHERE v.codigoMunicipioPortabilidad = :codigoMunicipioPortabilidad"),
    @NamedQuery(name = "VAsegPortabilidades.findByDepartamentoPortabilidad", query = "SELECT v FROM VAsegPortabilidades v WHERE v.departamentoPortabilidad = :departamentoPortabilidad"),
    @NamedQuery(name = "VAsegPortabilidades.findByMunicipioPortabilidad", query = "SELECT v FROM VAsegPortabilidades v WHERE v.municipioPortabilidad = :municipioPortabilidad"),
    @NamedQuery(name = "VAsegPortabilidades.findByCodigoHabilitacionIpsPortabilidad", query = "SELECT v FROM VAsegPortabilidades v WHERE v.codigoHabilitacionIpsPortabilidad = :codigoHabilitacionIpsPortabilidad"),
    @NamedQuery(name = "VAsegPortabilidades.findByIpsPortabilidad", query = "SELECT v FROM VAsegPortabilidades v WHERE v.ipsPortabilidad = :ipsPortabilidad"),
    @NamedQuery(name = "VAsegPortabilidades.findByFichaSisben", query = "SELECT v FROM VAsegPortabilidades v WHERE v.fichaSisben = :fichaSisben"),
    @NamedQuery(name = "VAsegPortabilidades.findByDescMigracion", query = "SELECT v FROM VAsegPortabilidades v WHERE v.descMigracion = :descMigracion"),
    @NamedQuery(name = "VAsegPortabilidades.findByPeriodoInicial", query = "SELECT v FROM VAsegPortabilidades v WHERE v.periodoInicial = :periodoInicial"),
    @NamedQuery(name = "VAsegPortabilidades.findByPeriodoFinal", query = "SELECT v FROM VAsegPortabilidades v WHERE v.periodoFinal = :periodoFinal"),
    @NamedQuery(name = "VAsegPortabilidades.findByDuracionPortabilidad", query = "SELECT v FROM VAsegPortabilidades v WHERE v.duracionPortabilidad = :duracionPortabilidad"),
    @NamedQuery(name = "VAsegPortabilidades.findByOrigenSolicitudPortabilidad", query = "SELECT v FROM VAsegPortabilidades v WHERE v.origenSolicitudPortabilidad = :origenSolicitudPortabilidad"),
    @NamedQuery(name = "VAsegPortabilidades.findByCantidadProrrogas", query = "SELECT v FROM VAsegPortabilidades v WHERE v.cantidadProrrogas = :cantidadProrrogas"),
    @NamedQuery(name = "VAsegPortabilidades.findByFechaProrroga", query = "SELECT v FROM VAsegPortabilidades v WHERE v.fechaProrroga = :fechaProrroga"),
    @NamedQuery(name = "VAsegPortabilidades.findByMesesAdicionProrroga", query = "SELECT v FROM VAsegPortabilidades v WHERE v.mesesAdicionProrroga = :mesesAdicionProrroga"),
    @NamedQuery(name = "VAsegPortabilidades.findByFechaCancelacion", query = "SELECT v FROM VAsegPortabilidades v WHERE v.fechaCancelacion = :fechaCancelacion"),
    @NamedQuery(name = "VAsegPortabilidades.findByUsuarioCrea", query = "SELECT v FROM VAsegPortabilidades v WHERE v.usuarioCrea = :usuarioCrea")})
public class VAsegPortabilidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "radicado_portabilidad")
    @Id
    private int radicadoPortabilidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_radicacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRadicacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "contrato_afiliado")
    private String contratoAfiliado;
    @Size(max = 16)
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
    @Column(name = "primer_nombre")
    private String primerNombre;
    @Size(max = 64)
    @Column(name = "segundo_nombre")
    private String segundoNombre;
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
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Size(max = 5)
    @Column(name = "codigo_departamento_afiliacion")
    private String codigoDepartamentoAfiliacion;
    @Size(max = 5)
    @Column(name = "codigo_municipio_afiliacion")
    private String codigoMunicipioAfiliacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "departamento_afiliacion")
    private String departamentoAfiliacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "municipio_afiliacion")
    private String municipioAfiliacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "direccion_residencia")
    private String direccionResidencia;
    @Size(max = 32)
    @Column(name = "telefono1_afiliado")
    private String telefono1Afiliado;
    @Size(max = 32)
    @Column(name = "telefono2_afiliado")
    private String telefono2Afiliado;
    @Size(max = 10)
    @Column(name = "telefono3_portabilidad")
    private String telefono3Portabilidad;
    @Size(max = 10)
    @Column(name = "telefono4_portabilidad")
    private String telefono4Portabilidad;
    @Size(max = 512)
    @Column(name = "correo_electronico_portabilidad")
    private String correoElectronicoPortabilidad;
    @Size(max = 16)
    @Column(name = "grupo_poblacional")
    private String grupoPoblacional;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 39)
    @Column(name = "estado_portabilidad")
    private String estadoPortabilidad;
    @Size(max = 256)
    @Column(name = "ips_primaria_sede")
    private String ipsPrimariaSede;
    @Size(max = 5)
    @Column(name = "codigo_departamento_portabilidad")
    private String codigoDepartamentoPortabilidad;
    @Size(max = 5)
    @Column(name = "codigo_municipio_portabilidad")
    private String codigoMunicipioPortabilidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "departamento_portabilidad")
    private String departamentoPortabilidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "municipio_portabilidad")
    private String municipioPortabilidad;
    @Size(max = 16)
    @Column(name = "codigo_habilitacion_ips_portabilidad")
    private String codigoHabilitacionIpsPortabilidad;
    @Size(max = 256)
    @Column(name = "ips_portabilidad")
    private String ipsPortabilidad;
    @Size(max = 32)
    @Column(name = "ficha_sisben")
    private String fichaSisben;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 39)
    @Column(name = "desc_migracion")
    private String descMigracion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "periodo_inicial")
    @Temporal(TemporalType.DATE)
    private Date periodoInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "periodo_final")
    @Temporal(TemporalType.DATE)
    private Date periodoFinal;
    @Column(name = "duracion_portabilidad")
    private BigInteger duracionPortabilidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 39)
    @Column(name = "origen_solicitud_portabilidad")
    private String origenSolicitudPortabilidad;
    @Column(name = "cantidad_prorrogas")
    private Integer cantidadProrrogas;
    @Column(name = "fecha_prorroga")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaProrroga;
    @Column(name = "meses_adicion_prorroga")
    private Integer mesesAdicionProrroga;
    @Column(name = "fecha_cancelacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCancelacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Lob
    @Size(max = 65535)
    @Column(name = "observacion_aseguramiento")
    private String observacionAseguramiento;

    public VAsegPortabilidades() {
    }

    public int getRadicadoPortabilidad() {
        return radicadoPortabilidad;
    }

    public void setRadicadoPortabilidad(int radicadoPortabilidad) {
        this.radicadoPortabilidad = radicadoPortabilidad;
    }

    public Date getFechaRadicacion() {
        return fechaRadicacion;
    }

    public void setFechaRadicacion(Date fechaRadicacion) {
        this.fechaRadicacion = fechaRadicacion;
    }

    public String getContratoAfiliado() {
        return contratoAfiliado;
    }

    public void setContratoAfiliado(String contratoAfiliado) {
        this.contratoAfiliado = contratoAfiliado;
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

    public String getDepartamentoAfiliacion() {
        return departamentoAfiliacion;
    }

    public void setDepartamentoAfiliacion(String departamentoAfiliacion) {
        this.departamentoAfiliacion = departamentoAfiliacion;
    }

    public String getMunicipioAfiliacion() {
        return municipioAfiliacion;
    }

    public void setMunicipioAfiliacion(String municipioAfiliacion) {
        this.municipioAfiliacion = municipioAfiliacion;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public String getTelefono1Afiliado() {
        return telefono1Afiliado;
    }

    public void setTelefono1Afiliado(String telefono1Afiliado) {
        this.telefono1Afiliado = telefono1Afiliado;
    }

    public String getTelefono2Afiliado() {
        return telefono2Afiliado;
    }

    public void setTelefono2Afiliado(String telefono2Afiliado) {
        this.telefono2Afiliado = telefono2Afiliado;
    }

    public String getTelefono3Portabilidad() {
        return telefono3Portabilidad;
    }

    public void setTelefono3Portabilidad(String telefono3Portabilidad) {
        this.telefono3Portabilidad = telefono3Portabilidad;
    }

    public String getTelefono4Portabilidad() {
        return telefono4Portabilidad;
    }

    public void setTelefono4Portabilidad(String telefono4Portabilidad) {
        this.telefono4Portabilidad = telefono4Portabilidad;
    }

    public String getCorreoElectronicoPortabilidad() {
        return correoElectronicoPortabilidad;
    }

    public void setCorreoElectronicoPortabilidad(String correoElectronicoPortabilidad) {
        this.correoElectronicoPortabilidad = correoElectronicoPortabilidad;
    }

    public String getGrupoPoblacional() {
        return grupoPoblacional;
    }

    public void setGrupoPoblacional(String grupoPoblacional) {
        this.grupoPoblacional = grupoPoblacional;
    }

    public String getEstadoPortabilidad() {
        return estadoPortabilidad;
    }

    public void setEstadoPortabilidad(String estadoPortabilidad) {
        this.estadoPortabilidad = estadoPortabilidad;
    }

    public String getIpsPrimariaSede() {
        return ipsPrimariaSede;
    }

    public void setIpsPrimariaSede(String ipsPrimariaSede) {
        this.ipsPrimariaSede = ipsPrimariaSede;
    }

    public String getCodigoDepartamentoPortabilidad() {
        return codigoDepartamentoPortabilidad;
    }

    public void setCodigoDepartamentoPortabilidad(String codigoDepartamentoPortabilidad) {
        this.codigoDepartamentoPortabilidad = codigoDepartamentoPortabilidad;
    }

    public String getCodigoMunicipioPortabilidad() {
        return codigoMunicipioPortabilidad;
    }

    public void setCodigoMunicipioPortabilidad(String codigoMunicipioPortabilidad) {
        this.codigoMunicipioPortabilidad = codigoMunicipioPortabilidad;
    }

    public String getDepartamentoPortabilidad() {
        return departamentoPortabilidad;
    }

    public void setDepartamentoPortabilidad(String departamentoPortabilidad) {
        this.departamentoPortabilidad = departamentoPortabilidad;
    }

    public String getMunicipioPortabilidad() {
        return municipioPortabilidad;
    }

    public void setMunicipioPortabilidad(String municipioPortabilidad) {
        this.municipioPortabilidad = municipioPortabilidad;
    }

    public String getCodigoHabilitacionIpsPortabilidad() {
        return codigoHabilitacionIpsPortabilidad;
    }

    public void setCodigoHabilitacionIpsPortabilidad(String codigoHabilitacionIpsPortabilidad) {
        this.codigoHabilitacionIpsPortabilidad = codigoHabilitacionIpsPortabilidad;
    }

    public String getIpsPortabilidad() {
        return ipsPortabilidad;
    }

    public void setIpsPortabilidad(String ipsPortabilidad) {
        this.ipsPortabilidad = ipsPortabilidad;
    }

    public String getFichaSisben() {
        return fichaSisben;
    }

    public void setFichaSisben(String fichaSisben) {
        this.fichaSisben = fichaSisben;
    }

    public String getDescMigracion() {
        return descMigracion;
    }

    public void setDescMigracion(String descMigracion) {
        this.descMigracion = descMigracion;
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

    public BigInteger getDuracionPortabilidad() {
        return duracionPortabilidad;
    }

    public void setDuracionPortabilidad(BigInteger duracionPortabilidad) {
        this.duracionPortabilidad = duracionPortabilidad;
    }

    public String getOrigenSolicitudPortabilidad() {
        return origenSolicitudPortabilidad;
    }

    public void setOrigenSolicitudPortabilidad(String origenSolicitudPortabilidad) {
        this.origenSolicitudPortabilidad = origenSolicitudPortabilidad;
    }

    public Integer getCantidadProrrogas() {
        return cantidadProrrogas;
    }

    public void setCantidadProrrogas(Integer cantidadProrrogas) {
        this.cantidadProrrogas = cantidadProrrogas;
    }

    public Date getFechaProrroga() {
        return fechaProrroga;
    }

    public void setFechaProrroga(Date fechaProrroga) {
        this.fechaProrroga = fechaProrroga;
    }

    public Integer getMesesAdicionProrroga() {
        return mesesAdicionProrroga;
    }

    public void setMesesAdicionProrroga(Integer mesesAdicionProrroga) {
        this.mesesAdicionProrroga = mesesAdicionProrroga;
    }

    public Date getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(Date fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getObservacionAseguramiento() {
        return observacionAseguramiento;
    }

    public void setObservacionAseguramiento(String observacionAseguramiento) {
        this.observacionAseguramiento = observacionAseguramiento;
    }
    
}
