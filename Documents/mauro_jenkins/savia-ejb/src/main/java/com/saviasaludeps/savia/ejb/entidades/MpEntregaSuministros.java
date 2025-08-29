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
@Table(name = "mp_entrega_suministros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpEntregaSuministros.findAll", query = "SELECT m FROM MpEntregaSuministros m"),
    @NamedQuery(name = "MpEntregaSuministros.findById", query = "SELECT m FROM MpEntregaSuministros m WHERE m.id = :id"),
    @NamedQuery(name = "MpEntregaSuministros.findByIdSuministro", query = "SELECT m FROM MpEntregaSuministros m WHERE m.idSuministro = :idSuministro"),
    @NamedQuery(name = "MpEntregaSuministros.findByFechaHoraSuminisro", query = "SELECT m FROM MpEntregaSuministros m WHERE m.fechaHoraSuminisro = :fechaHoraSuminisro"),
    @NamedQuery(name = "MpEntregaSuministros.findByUltimaEntrega", query = "SELECT m FROM MpEntregaSuministros m WHERE m.ultimaEntrega = :ultimaEntrega"),
    @NamedQuery(name = "MpEntregaSuministros.findByAnulado", query = "SELECT m FROM MpEntregaSuministros m WHERE m.anulado = :anulado"),
    @NamedQuery(name = "MpEntregaSuministros.findByFechaHoraAnula", query = "SELECT m FROM MpEntregaSuministros m WHERE m.fechaHoraAnula = :fechaHoraAnula"),
    @NamedQuery(name = "MpEntregaSuministros.findByNumeroPrescripcionAsociada", query = "SELECT m FROM MpEntregaSuministros m WHERE m.numeroPrescripcionAsociada = :numeroPrescripcionAsociada"),
    @NamedQuery(name = "MpEntregaSuministros.findByFechaConsumo", query = "SELECT m FROM MpEntregaSuministros m WHERE m.fechaConsumo = :fechaConsumo"),
    @NamedQuery(name = "MpEntregaSuministros.findByEstadoMipres", query = "SELECT m FROM MpEntregaSuministros m WHERE m.estadoMipres = :estadoMipres"),
    @NamedQuery(name = "MpEntregaSuministros.findByRespuestaSuministro", query = "SELECT m FROM MpEntregaSuministros m WHERE m.respuestaSuministro = :respuestaSuministro"),
    @NamedQuery(name = "MpEntregaSuministros.findByUsuarioCrea", query = "SELECT m FROM MpEntregaSuministros m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpEntregaSuministros.findByTerminalCrea", query = "SELECT m FROM MpEntregaSuministros m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpEntregaSuministros.findByFechaHoraCrea", query = "SELECT m FROM MpEntregaSuministros m WHERE m.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "MpEntregaSuministros.findByUsuarioModifica", query = "SELECT m FROM MpEntregaSuministros m WHERE m.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "MpEntregaSuministros.findByTerminalModifica", query = "SELECT m FROM MpEntregaSuministros m WHERE m.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "MpEntregaSuministros.findByFechaHoraModifica", query = "SELECT m FROM MpEntregaSuministros m WHERE m.fechaHoraModifica = :fechaHoraModifica")})
public class MpEntregaSuministros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 16)
    @Column(name = "id_suministro")
    private String idSuministro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_suminisro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraSuminisro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ultima_entrega")
    private boolean ultimaEntrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anulado")
    private boolean anulado;
    @Column(name = "fecha_hora_anula")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraAnula;
    @Size(max = 32)
    @Column(name = "numero_prescripcion_asociada")
    private String numeroPrescripcionAsociada;
    @Column(name = "fecha_consumo")
    @Temporal(TemporalType.DATE)
    private Date fechaConsumo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_mipres")
    private int estadoMipres;
    @Size(max = 512)
    @Column(name = "respuesta_suministro")
    private String respuestaSuministro;
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
    @JoinColumn(name = "mp_direccionamiento_entregados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MpDireccionamientoEntregados mpDireccionamientoEntregadosId;
    @OneToMany(mappedBy = "mpEntregaSuministrosId", fetch = FetchType.LAZY)
    private List<MpEntregaFacturas> mpEntregaFacturasList;

    public MpEntregaSuministros() {
    }

    public MpEntregaSuministros(Integer id) {
        this.id = id;
    }

    public MpEntregaSuministros(Integer id, Date fechaHoraSuminisro, boolean ultimaEntrega, boolean anulado, int estadoMipres, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fechaHoraSuminisro = fechaHoraSuminisro;
        this.ultimaEntrega = ultimaEntrega;
        this.anulado = anulado;
        this.estadoMipres = estadoMipres;
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

    public String getIdSuministro() {
        return idSuministro;
    }

    public void setIdSuministro(String idSuministro) {
        this.idSuministro = idSuministro;
    }

    public Date getFechaHoraSuminisro() {
        return fechaHoraSuminisro;
    }

    public void setFechaHoraSuminisro(Date fechaHoraSuminisro) {
        this.fechaHoraSuminisro = fechaHoraSuminisro;
    }

    public boolean getUltimaEntrega() {
        return ultimaEntrega;
    }

    public void setUltimaEntrega(boolean ultimaEntrega) {
        this.ultimaEntrega = ultimaEntrega;
    }

    public boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }

    public Date getFechaHoraAnula() {
        return fechaHoraAnula;
    }

    public void setFechaHoraAnula(Date fechaHoraAnula) {
        this.fechaHoraAnula = fechaHoraAnula;
    }

    public String getNumeroPrescripcionAsociada() {
        return numeroPrescripcionAsociada;
    }

    public void setNumeroPrescripcionAsociada(String numeroPrescripcionAsociada) {
        this.numeroPrescripcionAsociada = numeroPrescripcionAsociada;
    }

    public Date getFechaConsumo() {
        return fechaConsumo;
    }

    public void setFechaConsumo(Date fechaConsumo) {
        this.fechaConsumo = fechaConsumo;
    }

    public int getEstadoMipres() {
        return estadoMipres;
    }

    public void setEstadoMipres(int estadoMipres) {
        this.estadoMipres = estadoMipres;
    }

    public String getRespuestaSuministro() {
        return respuestaSuministro;
    }

    public void setRespuestaSuministro(String respuestaSuministro) {
        this.respuestaSuministro = respuestaSuministro;
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

    public MpDireccionamientoEntregados getMpDireccionamientoEntregadosId() {
        return mpDireccionamientoEntregadosId;
    }

    public void setMpDireccionamientoEntregadosId(MpDireccionamientoEntregados mpDireccionamientoEntregadosId) {
        this.mpDireccionamientoEntregadosId = mpDireccionamientoEntregadosId;
    }

    @XmlTransient
    public List<MpEntregaFacturas> getMpEntregaFacturasList() {
        return mpEntregaFacturasList;
    }

    public void setMpEntregaFacturasList(List<MpEntregaFacturas> mpEntregaFacturasList) {
        this.mpEntregaFacturasList = mpEntregaFacturasList;
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
        if (!(object instanceof MpEntregaSuministros)) {
            return false;
        }
        MpEntregaSuministros other = (MpEntregaSuministros) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpEntregaSuministros[ id=" + id + " ]";
    }
    
}
