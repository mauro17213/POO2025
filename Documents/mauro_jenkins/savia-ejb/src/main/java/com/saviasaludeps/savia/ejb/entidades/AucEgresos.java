/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "auc_egresos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AucEgresos.findAll", query = "SELECT a FROM AucEgresos a"),
    @NamedQuery(name = "AucEgresos.findById", query = "SELECT a FROM AucEgresos a WHERE a.id = :id"),
    @NamedQuery(name = "AucEgresos.findByNumCertificado", query = "SELECT a FROM AucEgresos a WHERE a.numCertificado = :numCertificado"),
    @NamedQuery(name = "AucEgresos.findByFechaEgreso", query = "SELECT a FROM AucEgresos a WHERE a.fechaEgreso = :fechaEgreso"),
    @NamedQuery(name = "AucEgresos.findByFallecido", query = "SELECT a FROM AucEgresos a WHERE a.fallecido = :fallecido"),
    @NamedQuery(name = "AucEgresos.findByMaeDestinoEgresoId", query = "SELECT a FROM AucEgresos a WHERE a.maeDestinoEgresoId = :maeDestinoEgresoId"),
    @NamedQuery(name = "AucEgresos.findByMaeDestinoEgresoCodigo", query = "SELECT a FROM AucEgresos a WHERE a.maeDestinoEgresoCodigo = :maeDestinoEgresoCodigo"),
    @NamedQuery(name = "AucEgresos.findByMaeDestinoEgresoValor", query = "SELECT a FROM AucEgresos a WHERE a.maeDestinoEgresoValor = :maeDestinoEgresoValor"),
    @NamedQuery(name = "AucEgresos.findByMaeConductaEgresoId", query = "SELECT a FROM AucEgresos a WHERE a.maeConductaEgresoId = :maeConductaEgresoId"),
    @NamedQuery(name = "AucEgresos.findByMaeConductaEgresoCodigo", query = "SELECT a FROM AucEgresos a WHERE a.maeConductaEgresoCodigo = :maeConductaEgresoCodigo"),
    @NamedQuery(name = "AucEgresos.findByMaeConductaEgresoValor", query = "SELECT a FROM AucEgresos a WHERE a.maeConductaEgresoValor = :maeConductaEgresoValor"),
    @NamedQuery(name = "AucEgresos.findByMaEspecialidadesId", query = "SELECT a FROM AucEgresos a WHERE a.maEspecialidadesId = :maEspecialidadesId"),
    @NamedQuery(name = "AucEgresos.findByMaEspecialidadesCodigo", query = "SELECT a FROM AucEgresos a WHERE a.maEspecialidadesCodigo = :maEspecialidadesCodigo"),
    @NamedQuery(name = "AucEgresos.findByMaEspecialidadesValor", query = "SELECT a FROM AucEgresos a WHERE a.maEspecialidadesValor = :maEspecialidadesValor"),
    @NamedQuery(name = "AucEgresos.findByIpsEntregaValor", query = "SELECT a FROM AucEgresos a WHERE a.ipsEntregaValor = :ipsEntregaValor"),
    @NamedQuery(name = "AucEgresos.findByValorEstancia", query = "SELECT a FROM AucEgresos a WHERE a.valorEstancia = :valorEstancia"),
    @NamedQuery(name = "AucEgresos.findByFacturado", query = "SELECT a FROM AucEgresos a WHERE a.facturado = :facturado"),
    @NamedQuery(name = "AucEgresos.findByFuenteOrigen", query = "SELECT a FROM AucEgresos a WHERE a.fuenteOrigen = :fuenteOrigen"),
    @NamedQuery(name = "AucEgresos.findByUsuarioCrea", query = "SELECT a FROM AucEgresos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AucEgresos.findByTerminalCrea", query = "SELECT a FROM AucEgresos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AucEgresos.findByFechaHoraCrea", query = "SELECT a FROM AucEgresos a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AucEgresos.findByUsuarioModifica", query = "SELECT a FROM AucEgresos a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AucEgresos.findByTerminalModifica", query = "SELECT a FROM AucEgresos a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AucEgresos.findByFechaHoraModifica", query = "SELECT a FROM AucEgresos a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AucEgresos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 32)
    @Column(name = "num_certificado")
    private String numCertificado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_egreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEgreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fallecido")
    private boolean fallecido;
    @Column(name = "mae_destino_egreso_id")
    private Integer maeDestinoEgresoId;
    @Size(max = 8)
    @Column(name = "mae_destino_egreso_codigo")
    private String maeDestinoEgresoCodigo;
    @Size(max = 128)
    @Column(name = "mae_destino_egreso_valor")
    private String maeDestinoEgresoValor;
    @Column(name = "mae_conducta_egreso_id")
    private Integer maeConductaEgresoId;
    @Size(max = 8)
    @Column(name = "mae_conducta_egreso_codigo")
    private String maeConductaEgresoCodigo;
    @Size(max = 128)
    @Column(name = "mae_conducta_egreso_valor")
    private String maeConductaEgresoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_especialidades_id")
    private int maEspecialidadesId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "ma_especialidades_codigo")
    private String maEspecialidadesCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ma_especialidades_valor")
    private String maEspecialidadesValor;
    @Column(name = "ips_entrega_valor")
    private Boolean ipsEntregaValor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_estancia")
    private BigDecimal valorEstancia;
    @Column(name = "facturado")
    private Integer facturado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fuente_origen")
    private int fuenteOrigen;
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
    @OneToMany(mappedBy = "aucEgresosId", fetch = FetchType.LAZY)
    private List<AucDiagnosticos> aucDiagnosticosList;
    @OneToMany(mappedBy = "aucEgresosId", fetch = FetchType.LAZY)
    private List<AucHospitalizaciones> aucHospitalizacionesList;

    public AucEgresos() {
    }

    public AucEgresos(Integer id) {
        this.id = id;
    }

    public AucEgresos(Integer id, Date fechaEgreso, boolean fallecido, int maEspecialidadesId, String maEspecialidadesCodigo, String maEspecialidadesValor, int fuenteOrigen, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fechaEgreso = fechaEgreso;
        this.fallecido = fallecido;
        this.maEspecialidadesId = maEspecialidadesId;
        this.maEspecialidadesCodigo = maEspecialidadesCodigo;
        this.maEspecialidadesValor = maEspecialidadesValor;
        this.fuenteOrigen = fuenteOrigen;
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

    public String getNumCertificado() {
        return numCertificado;
    }

    public void setNumCertificado(String numCertificado) {
        this.numCertificado = numCertificado;
    }

    public Date getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(Date fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public boolean getFallecido() {
        return fallecido;
    }

    public void setFallecido(boolean fallecido) {
        this.fallecido = fallecido;
    }

    public Integer getMaeDestinoEgresoId() {
        return maeDestinoEgresoId;
    }

    public void setMaeDestinoEgresoId(Integer maeDestinoEgresoId) {
        this.maeDestinoEgresoId = maeDestinoEgresoId;
    }

    public String getMaeDestinoEgresoCodigo() {
        return maeDestinoEgresoCodigo;
    }

    public void setMaeDestinoEgresoCodigo(String maeDestinoEgresoCodigo) {
        this.maeDestinoEgresoCodigo = maeDestinoEgresoCodigo;
    }

    public String getMaeDestinoEgresoValor() {
        return maeDestinoEgresoValor;
    }

    public void setMaeDestinoEgresoValor(String maeDestinoEgresoValor) {
        this.maeDestinoEgresoValor = maeDestinoEgresoValor;
    }

    public Integer getMaeConductaEgresoId() {
        return maeConductaEgresoId;
    }

    public void setMaeConductaEgresoId(Integer maeConductaEgresoId) {
        this.maeConductaEgresoId = maeConductaEgresoId;
    }

    public String getMaeConductaEgresoCodigo() {
        return maeConductaEgresoCodigo;
    }

    public void setMaeConductaEgresoCodigo(String maeConductaEgresoCodigo) {
        this.maeConductaEgresoCodigo = maeConductaEgresoCodigo;
    }

    public String getMaeConductaEgresoValor() {
        return maeConductaEgresoValor;
    }

    public void setMaeConductaEgresoValor(String maeConductaEgresoValor) {
        this.maeConductaEgresoValor = maeConductaEgresoValor;
    }

    public int getMaEspecialidadesId() {
        return maEspecialidadesId;
    }

    public void setMaEspecialidadesId(int maEspecialidadesId) {
        this.maEspecialidadesId = maEspecialidadesId;
    }

    public String getMaEspecialidadesCodigo() {
        return maEspecialidadesCodigo;
    }

    public void setMaEspecialidadesCodigo(String maEspecialidadesCodigo) {
        this.maEspecialidadesCodigo = maEspecialidadesCodigo;
    }

    public String getMaEspecialidadesValor() {
        return maEspecialidadesValor;
    }

    public void setMaEspecialidadesValor(String maEspecialidadesValor) {
        this.maEspecialidadesValor = maEspecialidadesValor;
    }

    public Boolean getIpsEntregaValor() {
        return ipsEntregaValor;
    }

    public void setIpsEntregaValor(Boolean ipsEntregaValor) {
        this.ipsEntregaValor = ipsEntregaValor;
    }

    public BigDecimal getValorEstancia() {
        return valorEstancia;
    }

    public void setValorEstancia(BigDecimal valorEstancia) {
        this.valorEstancia = valorEstancia;
    }

    public Integer getFacturado() {
        return facturado;
    }

    public void setFacturado(Integer facturado) {
        this.facturado = facturado;
    }

    public int getFuenteOrigen() {
        return fuenteOrigen;
    }

    public void setFuenteOrigen(int fuenteOrigen) {
        this.fuenteOrigen = fuenteOrigen;
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
    public List<AucDiagnosticos> getAucDiagnosticosList() {
        return aucDiagnosticosList;
    }

    public void setAucDiagnosticosList(List<AucDiagnosticos> aucDiagnosticosList) {
        this.aucDiagnosticosList = aucDiagnosticosList;
    }

    @XmlTransient
    public List<AucHospitalizaciones> getAucHospitalizacionesList() {
        return aucHospitalizacionesList;
    }

    public void setAucHospitalizacionesList(List<AucHospitalizaciones> aucHospitalizacionesList) {
        this.aucHospitalizacionesList = aucHospitalizacionesList;
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
        if (!(object instanceof AucEgresos)) {
            return false;
        }
        AucEgresos other = (AucEgresos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AucEgresos[ id=" + id + " ]";
    }
    
}
