/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "cm_rips_an_recien_nacidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmRipsAnRecienNacidos.findAll", query = "SELECT c FROM CmRipsAnRecienNacidos c"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findById", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.id = :id"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByFila", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.fila = :fila"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByNumeroFactura", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.numeroFactura = :numeroFactura"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByCodigoReps", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.codigoReps = :codigoReps"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByMaeTipoDocumentoMadreCodigo", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.maeTipoDocumentoMadreCodigo = :maeTipoDocumentoMadreCodigo"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByMaeTipoDocumentoMadreId", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.maeTipoDocumentoMadreId = :maeTipoDocumentoMadreId"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByMaeTipoDocumentoMadreValor", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.maeTipoDocumentoMadreValor = :maeTipoDocumentoMadreValor"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByDocumentoAfiliadoMadre", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.documentoAfiliadoMadre = :documentoAfiliadoMadre"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByFechaNacimiento", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByHoraNacimiento", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.horaNacimiento = :horaNacimiento"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByEdadGestacion", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.edadGestacion = :edadGestacion"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByMaeControlPenatalCodigo", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.maeControlPenatalCodigo = :maeControlPenatalCodigo"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByMaeControlPenatalId", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.maeControlPenatalId = :maeControlPenatalId"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByMaeControlPenatalValor", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.maeControlPenatalValor = :maeControlPenatalValor"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByMaeSexoCodigo", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.maeSexoCodigo = :maeSexoCodigo"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByMaeSexoId", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.maeSexoId = :maeSexoId"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByMaeSexoValor", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.maeSexoValor = :maeSexoValor"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByPeso", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.peso = :peso"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByMaDiagnosticoPrincipalCodigo", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.maDiagnosticoPrincipalCodigo = :maDiagnosticoPrincipalCodigo"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByMaDiagnosticoPrincipalId", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.maDiagnosticoPrincipalId = :maDiagnosticoPrincipalId"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByMaDiagnosticoPrincipalValor", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.maDiagnosticoPrincipalValor = :maDiagnosticoPrincipalValor"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByMaCausaMuerteDiagnosticoCodigo", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.maCausaMuerteDiagnosticoCodigo = :maCausaMuerteDiagnosticoCodigo"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByMaCausaMuerteDiagnosticoId", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.maCausaMuerteDiagnosticoId = :maCausaMuerteDiagnosticoId"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByMaCausaMuerteDiagnosticoValor", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.maCausaMuerteDiagnosticoValor = :maCausaMuerteDiagnosticoValor"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByFechaMuerte", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.fechaMuerte = :fechaMuerte"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByHoraMuerte", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.horaMuerte = :horaMuerte"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByArchivoNombreOriginal", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.archivoNombreOriginal = :archivoNombreOriginal"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByArchivoRuta", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.archivoRuta = :archivoRuta"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByArchivoNombre", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.archivoNombre = :archivoNombre"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByUsuarioCrea", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByTerminalCrea", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmRipsAnRecienNacidos.findByFechaHoraCrea", query = "SELECT c FROM CmRipsAnRecienNacidos c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmRipsAnRecienNacidos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fila")
    private int fila;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "numero_factura")
    private String numeroFactura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "codigo_reps")
    private String codigoReps;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_documento_madre_codigo")
    private String maeTipoDocumentoMadreCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_documento_madre_id")
    private int maeTipoDocumentoMadreId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_tipo_documento_madre_valor")
    private String maeTipoDocumentoMadreValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "documento_afiliado_madre")
    private String documentoAfiliadoMadre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_nacimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaNacimiento;
    @Column(name = "edad_gestacion")
    private Integer edadGestacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_control_penatal_codigo")
    private String maeControlPenatalCodigo;
    @Column(name = "mae_control_penatal_id")
    private Integer maeControlPenatalId;
    @Size(max = 128)
    @Column(name = "mae_control_penatal_valor")
    private String maeControlPenatalValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_sexo_codigo")
    private String maeSexoCodigo;
    @Column(name = "mae_sexo_id")
    private Integer maeSexoId;
    @Size(max = 128)
    @Column(name = "mae_sexo_valor")
    private String maeSexoValor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "peso")
    private BigDecimal peso;
    @Size(max = 32)
    @Column(name = "ma_diagnostico_principal_codigo")
    private String maDiagnosticoPrincipalCodigo;
    @Column(name = "ma_diagnostico_principal_id")
    private Integer maDiagnosticoPrincipalId;
    @Size(max = 512)
    @Column(name = "ma_diagnostico_principal_valor")
    private String maDiagnosticoPrincipalValor;
    @Size(max = 32)
    @Column(name = "ma_causa_muerte_diagnostico_codigo")
    private String maCausaMuerteDiagnosticoCodigo;
    @Column(name = "ma_causa_muerte_diagnostico_id")
    private Integer maCausaMuerteDiagnosticoId;
    @Size(max = 512)
    @Column(name = "ma_causa_muerte_diagnostico_valor")
    private String maCausaMuerteDiagnosticoValor;
    @Column(name = "fecha_muerte")
    @Temporal(TemporalType.DATE)
    private Date fechaMuerte;
    @Column(name = "hora_muerte")
    @Temporal(TemporalType.TIME)
    private Date horaMuerte;
    @Size(max = 256)
    @Column(name = "archivo_nombre_original")
    private String archivoNombreOriginal;
    @Size(max = 512)
    @Column(name = "archivo_ruta")
    private String archivoRuta;
    @Size(max = 128)
    @Column(name = "archivo_nombre")
    private String archivoNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @JoinColumn(name = "cm_rips_cargas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmRipsCargas cmRipsCargasId;

    public CmRipsAnRecienNacidos() {
    }

    public CmRipsAnRecienNacidos(Integer id) {
        this.id = id;
    }

    public CmRipsAnRecienNacidos(Integer id, int fila, String numeroFactura, String codigoReps, String maeTipoDocumentoMadreCodigo, int maeTipoDocumentoMadreId, String maeTipoDocumentoMadreValor, String documentoAfiliadoMadre, Date fechaNacimiento, Date horaNacimiento, String maeControlPenatalCodigo, String maeSexoCodigo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fila = fila;
        this.numeroFactura = numeroFactura;
        this.codigoReps = codigoReps;
        this.maeTipoDocumentoMadreCodigo = maeTipoDocumentoMadreCodigo;
        this.maeTipoDocumentoMadreId = maeTipoDocumentoMadreId;
        this.maeTipoDocumentoMadreValor = maeTipoDocumentoMadreValor;
        this.documentoAfiliadoMadre = documentoAfiliadoMadre;
        this.fechaNacimiento = fechaNacimiento;
        this.horaNacimiento = horaNacimiento;
        this.maeControlPenatalCodigo = maeControlPenatalCodigo;
        this.maeSexoCodigo = maeSexoCodigo;
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

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getCodigoReps() {
        return codigoReps;
    }

    public void setCodigoReps(String codigoReps) {
        this.codigoReps = codigoReps;
    }

    public String getMaeTipoDocumentoMadreCodigo() {
        return maeTipoDocumentoMadreCodigo;
    }

    public void setMaeTipoDocumentoMadreCodigo(String maeTipoDocumentoMadreCodigo) {
        this.maeTipoDocumentoMadreCodigo = maeTipoDocumentoMadreCodigo;
    }

    public int getMaeTipoDocumentoMadreId() {
        return maeTipoDocumentoMadreId;
    }

    public void setMaeTipoDocumentoMadreId(int maeTipoDocumentoMadreId) {
        this.maeTipoDocumentoMadreId = maeTipoDocumentoMadreId;
    }

    public String getMaeTipoDocumentoMadreValor() {
        return maeTipoDocumentoMadreValor;
    }

    public void setMaeTipoDocumentoMadreValor(String maeTipoDocumentoMadreValor) {
        this.maeTipoDocumentoMadreValor = maeTipoDocumentoMadreValor;
    }

    public String getDocumentoAfiliadoMadre() {
        return documentoAfiliadoMadre;
    }

    public void setDocumentoAfiliadoMadre(String documentoAfiliadoMadre) {
        this.documentoAfiliadoMadre = documentoAfiliadoMadre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getHoraNacimiento() {
        return horaNacimiento;
    }

    public void setHoraNacimiento(Date horaNacimiento) {
        this.horaNacimiento = horaNacimiento;
    }

    public Integer getEdadGestacion() {
        return edadGestacion;
    }

    public void setEdadGestacion(Integer edadGestacion) {
        this.edadGestacion = edadGestacion;
    }

    public String getMaeControlPenatalCodigo() {
        return maeControlPenatalCodigo;
    }

    public void setMaeControlPenatalCodigo(String maeControlPenatalCodigo) {
        this.maeControlPenatalCodigo = maeControlPenatalCodigo;
    }

    public Integer getMaeControlPenatalId() {
        return maeControlPenatalId;
    }

    public void setMaeControlPenatalId(Integer maeControlPenatalId) {
        this.maeControlPenatalId = maeControlPenatalId;
    }

    public String getMaeControlPenatalValor() {
        return maeControlPenatalValor;
    }

    public void setMaeControlPenatalValor(String maeControlPenatalValor) {
        this.maeControlPenatalValor = maeControlPenatalValor;
    }

    public String getMaeSexoCodigo() {
        return maeSexoCodigo;
    }

    public void setMaeSexoCodigo(String maeSexoCodigo) {
        this.maeSexoCodigo = maeSexoCodigo;
    }

    public Integer getMaeSexoId() {
        return maeSexoId;
    }

    public void setMaeSexoId(Integer maeSexoId) {
        this.maeSexoId = maeSexoId;
    }

    public String getMaeSexoValor() {
        return maeSexoValor;
    }

    public void setMaeSexoValor(String maeSexoValor) {
        this.maeSexoValor = maeSexoValor;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public String getMaDiagnosticoPrincipalCodigo() {
        return maDiagnosticoPrincipalCodigo;
    }

    public void setMaDiagnosticoPrincipalCodigo(String maDiagnosticoPrincipalCodigo) {
        this.maDiagnosticoPrincipalCodigo = maDiagnosticoPrincipalCodigo;
    }

    public Integer getMaDiagnosticoPrincipalId() {
        return maDiagnosticoPrincipalId;
    }

    public void setMaDiagnosticoPrincipalId(Integer maDiagnosticoPrincipalId) {
        this.maDiagnosticoPrincipalId = maDiagnosticoPrincipalId;
    }

    public String getMaDiagnosticoPrincipalValor() {
        return maDiagnosticoPrincipalValor;
    }

    public void setMaDiagnosticoPrincipalValor(String maDiagnosticoPrincipalValor) {
        this.maDiagnosticoPrincipalValor = maDiagnosticoPrincipalValor;
    }

    public String getMaCausaMuerteDiagnosticoCodigo() {
        return maCausaMuerteDiagnosticoCodigo;
    }

    public void setMaCausaMuerteDiagnosticoCodigo(String maCausaMuerteDiagnosticoCodigo) {
        this.maCausaMuerteDiagnosticoCodigo = maCausaMuerteDiagnosticoCodigo;
    }

    public Integer getMaCausaMuerteDiagnosticoId() {
        return maCausaMuerteDiagnosticoId;
    }

    public void setMaCausaMuerteDiagnosticoId(Integer maCausaMuerteDiagnosticoId) {
        this.maCausaMuerteDiagnosticoId = maCausaMuerteDiagnosticoId;
    }

    public String getMaCausaMuerteDiagnosticoValor() {
        return maCausaMuerteDiagnosticoValor;
    }

    public void setMaCausaMuerteDiagnosticoValor(String maCausaMuerteDiagnosticoValor) {
        this.maCausaMuerteDiagnosticoValor = maCausaMuerteDiagnosticoValor;
    }

    public Date getFechaMuerte() {
        return fechaMuerte;
    }

    public void setFechaMuerte(Date fechaMuerte) {
        this.fechaMuerte = fechaMuerte;
    }

    public Date getHoraMuerte() {
        return horaMuerte;
    }

    public void setHoraMuerte(Date horaMuerte) {
        this.horaMuerte = horaMuerte;
    }

    public String getArchivoNombreOriginal() {
        return archivoNombreOriginal;
    }

    public void setArchivoNombreOriginal(String archivoNombreOriginal) {
        this.archivoNombreOriginal = archivoNombreOriginal;
    }

    public String getArchivoRuta() {
        return archivoRuta;
    }

    public void setArchivoRuta(String archivoRuta) {
        this.archivoRuta = archivoRuta;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
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

    public CmRipsCargas getCmRipsCargasId() {
        return cmRipsCargasId;
    }

    public void setCmRipsCargasId(CmRipsCargas cmRipsCargasId) {
        this.cmRipsCargasId = cmRipsCargasId;
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
        if (!(object instanceof CmRipsAnRecienNacidos)) {
            return false;
        }
        CmRipsAnRecienNacidos other = (CmRipsAnRecienNacidos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmRipsAnRecienNacidos[ id=" + id + " ]";
    }
    
}
