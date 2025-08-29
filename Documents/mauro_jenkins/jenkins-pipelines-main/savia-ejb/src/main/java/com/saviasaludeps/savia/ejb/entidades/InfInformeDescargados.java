/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
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
@Table(name = "inf_informe_descargados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InfInformeDescargados.findAll", query = "SELECT i FROM InfInformeDescargados i"),
    @NamedQuery(name = "InfInformeDescargados.findById", query = "SELECT i FROM InfInformeDescargados i WHERE i.id = :id"),
    @NamedQuery(name = "InfInformeDescargados.findByInfUsuarioId", query = "SELECT i FROM InfInformeDescargados i WHERE i.infUsuarioId = :infUsuarioId"),
    @NamedQuery(name = "InfInformeDescargados.findByFechaDescarga", query = "SELECT i FROM InfInformeDescargados i WHERE i.fechaDescarga = :fechaDescarga"),
    @NamedQuery(name = "InfInformeDescargados.findByEmpresaNombre", query = "SELECT i FROM InfInformeDescargados i WHERE i.empresaNombre = :empresaNombre"),
    @NamedQuery(name = "InfInformeDescargados.findByUsuarioCrea", query = "SELECT i FROM InfInformeDescargados i WHERE i.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "InfInformeDescargados.findByFechaHoraCrea", query = "SELECT i FROM InfInformeDescargados i WHERE i.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "InfInformeDescargados.findByTerminalCrea", query = "SELECT i FROM InfInformeDescargados i WHERE i.terminalCrea = :terminalCrea")})
public class InfInformeDescargados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "inf_usuario_id")
    private int infUsuarioId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_descarga")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDescarga;
    @Size(max = 256)
    @Column(name = "empresa_nombre")
    private String empresaNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @JoinColumn(name = "inf_informe_generados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InfInformeGenerados infInformeGeneradosId;

    public InfInformeDescargados() {
    }

    public InfInformeDescargados(Integer id) {
        this.id = id;
    }

    public InfInformeDescargados(Integer id, int infUsuarioId, Date fechaDescarga, String usuarioCrea, Date fechaHoraCrea, String terminalCrea) {
        this.id = id;
        this.infUsuarioId = infUsuarioId;
        this.fechaDescarga = fechaDescarga;
        this.usuarioCrea = usuarioCrea;
        this.fechaHoraCrea = fechaHoraCrea;
        this.terminalCrea = terminalCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getInfUsuarioId() {
        return infUsuarioId;
    }

    public void setInfUsuarioId(int infUsuarioId) {
        this.infUsuarioId = infUsuarioId;
    }

    public Date getFechaDescarga() {
        return fechaDescarga;
    }

    public void setFechaDescarga(Date fechaDescarga) {
        this.fechaDescarga = fechaDescarga;
    }

    public String getEmpresaNombre() {
        return empresaNombre;
    }

    public void setEmpresaNombre(String empresaNombre) {
        this.empresaNombre = empresaNombre;
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

    public InfInformeGenerados getInfInformeGeneradosId() {
        return infInformeGeneradosId;
    }

    public void setInfInformeGeneradosId(InfInformeGenerados infInformeGeneradosId) {
        this.infInformeGeneradosId = infInformeGeneradosId;
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
        if (!(object instanceof InfInformeDescargados)) {
            return false;
        }
        InfInformeDescargados other = (InfInformeDescargados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.InfInformeDescargados[ id=" + id + " ]";
    }
    
}
