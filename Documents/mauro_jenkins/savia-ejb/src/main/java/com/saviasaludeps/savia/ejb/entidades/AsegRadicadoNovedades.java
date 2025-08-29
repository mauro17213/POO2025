/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "aseg_radicado_novedades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsegRadicadoNovedades.findAll", query = "SELECT a FROM AsegRadicadoNovedades a"),
    @NamedQuery(name = "AsegRadicadoNovedades.findById", query = "SELECT a FROM AsegRadicadoNovedades a WHERE a.id = :id"),
    @NamedQuery(name = "AsegRadicadoNovedades.findBySoporteNovedad", query = "SELECT a FROM AsegRadicadoNovedades a WHERE a.soporteNovedad = :soporteNovedad"),
    @NamedQuery(name = "AsegRadicadoNovedades.findByUsuarioCrea", query = "SELECT a FROM AsegRadicadoNovedades a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AsegRadicadoNovedades.findByFechaHoraCrea", query = "SELECT a FROM AsegRadicadoNovedades a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AsegRadicadoNovedades.findByTerminalCrea", query = "SELECT a FROM AsegRadicadoNovedades a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AsegRadicadoNovedades.findByUsuarioModifica", query = "SELECT a FROM AsegRadicadoNovedades a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AsegRadicadoNovedades.findByFechaHoraModifica", query = "SELECT a FROM AsegRadicadoNovedades a WHERE a.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "AsegRadicadoNovedades.findByTerminalModifica", query = "SELECT a FROM AsegRadicadoNovedades a WHERE a.terminalModifica = :terminalModifica")})
public class AsegRadicadoNovedades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "soporte_novedad")
    private Boolean soporteNovedad;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "historico_afiliado")
    private String historicoAfiliado;
    @Size(max = 64)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Size(max = 64)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Size(max = 64)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @Size(max = 64)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "radicadoNovedadesId", fetch = FetchType.LAZY)
    private List<AsegAdjuntos> asegAdjuntosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "radicadoNovedadesId", fetch = FetchType.LAZY)
    private List<AsegRegistroNovedades> asegRegistroNovedadesList;
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;

    public AsegRadicadoNovedades() {
    }

    public AsegRadicadoNovedades(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getSoporteNovedad() {
        return soporteNovedad;
    }

    public void setSoporteNovedad(Boolean soporteNovedad) {
        this.soporteNovedad = soporteNovedad;
    }

    public String getHistoricoAfiliado() {
        return historicoAfiliado;
    }

    public void setHistoricoAfiliado(String historicoAfiliado) {
        this.historicoAfiliado = historicoAfiliado;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public String getTerminalCrea() {
        return terminalCrea;
    }

    public void setTerminalCrea(String terminalCrea) {
        this.terminalCrea = terminalCrea;
    }

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }

    public String getTerminalModifica() {
        return terminalModifica;
    }

    public void setTerminalModifica(String terminalModifica) {
        this.terminalModifica = terminalModifica;
    }

    @XmlTransient
    public List<AsegAdjuntos> getAsegAdjuntosList() {
        return asegAdjuntosList;
    }

    public void setAsegAdjuntosList(List<AsegAdjuntos> asegAdjuntosList) {
        this.asegAdjuntosList = asegAdjuntosList;
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
        if (!(object instanceof AsegRadicadoNovedades)) {
            return false;
        }
        AsegRadicadoNovedades other = (AsegRadicadoNovedades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AsegRadicadoNovedades[ id=" + id + " ]";
    }
    
}
