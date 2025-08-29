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
@Table(name = "cnt_profesional_prestadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntProfesionalPrestadores.findAll", query = "SELECT c FROM CntProfesionalPrestadores c"),
    @NamedQuery(name = "CntProfesionalPrestadores.findById", query = "SELECT c FROM CntProfesionalPrestadores c WHERE c.id = :id"),
    @NamedQuery(name = "CntProfesionalPrestadores.findByActivo", query = "SELECT c FROM CntProfesionalPrestadores c WHERE c.activo = :activo"),
    @NamedQuery(name = "CntProfesionalPrestadores.findByMaEspecialidadId", query = "SELECT c FROM CntProfesionalPrestadores c WHERE c.maEspecialidadId = :maEspecialidadId"),
    @NamedQuery(name = "CntProfesionalPrestadores.findByMaEspecialidadCodigo", query = "SELECT c FROM CntProfesionalPrestadores c WHERE c.maEspecialidadCodigo = :maEspecialidadCodigo"),
    @NamedQuery(name = "CntProfesionalPrestadores.findByMaEspecialidadValor", query = "SELECT c FROM CntProfesionalPrestadores c WHERE c.maEspecialidadValor = :maEspecialidadValor"),
    @NamedQuery(name = "CntProfesionalPrestadores.findByUsuarioCrea", query = "SELECT c FROM CntProfesionalPrestadores c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntProfesionalPrestadores.findByTerminalCrea", query = "SELECT c FROM CntProfesionalPrestadores c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntProfesionalPrestadores.findByFechaHoraCrea", query = "SELECT c FROM CntProfesionalPrestadores c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntProfesionalPrestadores.findByUsuarioModifica", query = "SELECT c FROM CntProfesionalPrestadores c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntProfesionalPrestadores.findByTerminalModifica", query = "SELECT c FROM CntProfesionalPrestadores c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntProfesionalPrestadores.findByFechaHoraModifica", query = "SELECT c FROM CntProfesionalPrestadores c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntProfesionalPrestadores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_especialidad_id")
    private int maEspecialidadId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "ma_especialidad_codigo")
    private String maEspecialidadCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ma_especialidad_valor")
    private String maEspecialidadValor;
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
    @JoinColumn(name = "cnt_prestadores_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadores cntPrestadoresId;
    @JoinColumn(name = "cnt_profesionales_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntProfesionales cntProfesionalesId;

    public CntProfesionalPrestadores() {
    }

    public CntProfesionalPrestadores(Integer id) {
        this.id = id;
    }

    public CntProfesionalPrestadores(Integer id, boolean activo, int maEspecialidadId, String maEspecialidadCodigo, String maEspecialidadValor, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.activo = activo;
        this.maEspecialidadId = maEspecialidadId;
        this.maEspecialidadCodigo = maEspecialidadCodigo;
        this.maEspecialidadValor = maEspecialidadValor;
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

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getMaEspecialidadId() {
        return maEspecialidadId;
    }

    public void setMaEspecialidadId(int maEspecialidadId) {
        this.maEspecialidadId = maEspecialidadId;
    }

    public String getMaEspecialidadCodigo() {
        return maEspecialidadCodigo;
    }

    public void setMaEspecialidadCodigo(String maEspecialidadCodigo) {
        this.maEspecialidadCodigo = maEspecialidadCodigo;
    }

    public String getMaEspecialidadValor() {
        return maEspecialidadValor;
    }

    public void setMaEspecialidadValor(String maEspecialidadValor) {
        this.maEspecialidadValor = maEspecialidadValor;
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

    public CntPrestadores getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    public void setCntPrestadoresId(CntPrestadores cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
    }

    public CntProfesionales getCntProfesionalesId() {
        return cntProfesionalesId;
    }

    public void setCntProfesionalesId(CntProfesionales cntProfesionalesId) {
        this.cntProfesionalesId = cntProfesionalesId;
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
        if (!(object instanceof CntProfesionalPrestadores)) {
            return false;
        }
        CntProfesionalPrestadores other = (CntProfesionalPrestadores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntProfesionalPrestadores[ id=" + id + " ]";
    }
    
}
