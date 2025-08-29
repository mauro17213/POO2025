/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "aseg_reporte_novedades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsegReporteNovedades.findAll", query = "SELECT a FROM AsegReporteNovedades a"),
    @NamedQuery(name = "AsegReporteNovedades.findById", query = "SELECT a FROM AsegReporteNovedades a WHERE a.id = :id"),
    @NamedQuery(name = "AsegReporteNovedades.findByCodigoEps", query = "SELECT a FROM AsegReporteNovedades a WHERE a.codigoEps = :codigoEps"),
    @NamedQuery(name = "AsegReporteNovedades.findByTipoDocumento", query = "SELECT a FROM AsegReporteNovedades a WHERE a.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "AsegReporteNovedades.findByNumeroDocumento", query = "SELECT a FROM AsegReporteNovedades a WHERE a.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "AsegReporteNovedades.findByPrimerApellido", query = "SELECT a FROM AsegReporteNovedades a WHERE a.primerApellido = :primerApellido"),
    @NamedQuery(name = "AsegReporteNovedades.findBySegundoApellido", query = "SELECT a FROM AsegReporteNovedades a WHERE a.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "AsegReporteNovedades.findByPrimerNombre", query = "SELECT a FROM AsegReporteNovedades a WHERE a.primerNombre = :primerNombre"),
    @NamedQuery(name = "AsegReporteNovedades.findBySegundoNombre", query = "SELECT a FROM AsegReporteNovedades a WHERE a.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "AsegReporteNovedades.findByFechaNacimiento", query = "SELECT a FROM AsegReporteNovedades a WHERE a.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "AsegReporteNovedades.findByCodigoDepartamento", query = "SELECT a FROM AsegReporteNovedades a WHERE a.codigoDepartamento = :codigoDepartamento"),
    @NamedQuery(name = "AsegReporteNovedades.findByCodigoMunicipio", query = "SELECT a FROM AsegReporteNovedades a WHERE a.codigoMunicipio = :codigoMunicipio"),
    @NamedQuery(name = "AsegReporteNovedades.findByCodigoNovedad", query = "SELECT a FROM AsegReporteNovedades a WHERE a.codigoNovedad = :codigoNovedad"),
    @NamedQuery(name = "AsegReporteNovedades.findByFechaNovedad", query = "SELECT a FROM AsegReporteNovedades a WHERE a.fechaNovedad = :fechaNovedad"),
    @NamedQuery(name = "AsegReporteNovedades.findByValor1", query = "SELECT a FROM AsegReporteNovedades a WHERE a.valor1 = :valor1"),
    @NamedQuery(name = "AsegReporteNovedades.findByValor2", query = "SELECT a FROM AsegReporteNovedades a WHERE a.valor2 = :valor2"),
    @NamedQuery(name = "AsegReporteNovedades.findByValor3", query = "SELECT a FROM AsegReporteNovedades a WHERE a.valor3 = :valor3"),
    @NamedQuery(name = "AsegReporteNovedades.findByValor4", query = "SELECT a FROM AsegReporteNovedades a WHERE a.valor4 = :valor4"),
    @NamedQuery(name = "AsegReporteNovedades.findByValor5", query = "SELECT a FROM AsegReporteNovedades a WHERE a.valor5 = :valor5"),
    @NamedQuery(name = "AsegReporteNovedades.findByValor6", query = "SELECT a FROM AsegReporteNovedades a WHERE a.valor6 = :valor6"),
    @NamedQuery(name = "AsegReporteNovedades.findByFechaReporte", query = "SELECT a FROM AsegReporteNovedades a WHERE a.fechaReporte = :fechaReporte"),
    @NamedQuery(name = "AsegReporteNovedades.findByUsuarioCrea", query = "SELECT a FROM AsegReporteNovedades a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AsegReporteNovedades.findByTerminalCrea", query = "SELECT a FROM AsegReporteNovedades a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AsegReporteNovedades.findByFechaHoraCrea", query = "SELECT a FROM AsegReporteNovedades a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AsegReporteNovedades.findByUsuarioModifica", query = "SELECT a FROM AsegReporteNovedades a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AsegReporteNovedades.findByTerminalModifica", query = "SELECT a FROM AsegReporteNovedades a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AsegReporteNovedades.findByFechaHoraModifica", query = "SELECT a FROM AsegReporteNovedades a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AsegReporteNovedades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
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
    @Size(max = 256)
    @Column(name = "valor_1")
    private String valor1;
    @Size(max = 256)
    @Column(name = "valor_2")
    private String valor2;
    @Size(max = 256)
    @Column(name = "valor_3")
    private String valor3;
    @Size(max = 256)
    @Column(name = "valor_4")
    private String valor4;
    @Size(max = 256)
    @Column(name = "valor_5")
    private String valor5;
    @Size(max = 256)
    @Column(name = "valor_6")
    private String valor6;
    @Column(name = "fecha_reporte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReporte;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Size(max = 128)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Size(max = 16)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @OneToMany(mappedBy = "asegReporteNovedadesId", fetch = FetchType.LAZY)
    private List<AsegRegistroNovedades> asegRegistroNovedadesList;
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;

    public AsegReporteNovedades() {
    }

    public AsegReporteNovedades(Integer id) {
        this.id = id;
    }

    public AsegReporteNovedades(Integer id, String codigoEps, String tipoDocumento, String numeroDocumento, String primerApellido, String primerNombre, Date fechaNacimiento, String codigoDepartamento, String codigoMunicipio, String codigoNovedad, Date fechaNovedad, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.codigoEps = codigoEps;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.primerApellido = primerApellido;
        this.primerNombre = primerNombre;
        this.fechaNacimiento = fechaNacimiento;
        this.codigoDepartamento = codigoDepartamento;
        this.codigoMunicipio = codigoMunicipio;
        this.codigoNovedad = codigoNovedad;
        this.fechaNovedad = fechaNovedad;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getTerminalCrea() {
        return terminalCrea;
    }

    public void setTerminalCrea(String terminalCrea) {
        this.terminalCrea = terminalCrea;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public String getTerminalModifica() {
        return terminalModifica;
    }

    public void setTerminalModifica(String terminalModifica) {
        this.terminalModifica = terminalModifica;
    }

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }

    @XmlTransient
    public List<AsegRegistroNovedades> getAsegRegistroNovedadesList() {
        return asegRegistroNovedadesList;
    }

    public void setAsegRegistroNovedadesList(List<AsegRegistroNovedades> asegRegistroNovedadesList) {
        this.asegRegistroNovedadesList = asegRegistroNovedadesList;
    }

    public AsegAfiliados getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliados asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsegReporteNovedades)) {
            return false;
        }
        AsegReporteNovedades other = (AsegReporteNovedades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AsegReporteNovedades[ id=" + id + " ]";
    }
    
}
