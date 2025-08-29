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
@Table(name = "cm_rips_regla_entradas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmRipsReglaEntradas.findAll", query = "SELECT c FROM CmRipsReglaEntradas c"),
    @NamedQuery(name = "CmRipsReglaEntradas.findById", query = "SELECT c FROM CmRipsReglaEntradas c WHERE c.id = :id"),
    @NamedQuery(name = "CmRipsReglaEntradas.findByTipo", query = "SELECT c FROM CmRipsReglaEntradas c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "CmRipsReglaEntradas.findByArchivo", query = "SELECT c FROM CmRipsReglaEntradas c WHERE c.archivo = :archivo"),
    @NamedQuery(name = "CmRipsReglaEntradas.findByPosicion", query = "SELECT c FROM CmRipsReglaEntradas c WHERE c.posicion = :posicion"),
    @NamedQuery(name = "CmRipsReglaEntradas.findByCampo", query = "SELECT c FROM CmRipsReglaEntradas c WHERE c.campo = :campo"),
    @NamedQuery(name = "CmRipsReglaEntradas.findByOrden", query = "SELECT c FROM CmRipsReglaEntradas c WHERE c.orden = :orden"),
    @NamedQuery(name = "CmRipsReglaEntradas.findByUsuarioCrea", query = "SELECT c FROM CmRipsReglaEntradas c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmRipsReglaEntradas.findByTerminalCrea", query = "SELECT c FROM CmRipsReglaEntradas c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmRipsReglaEntradas.findByFechaHoraCrea", query = "SELECT c FROM CmRipsReglaEntradas c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CmRipsReglaEntradas.findByUsuarioModifica", query = "SELECT c FROM CmRipsReglaEntradas c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CmRipsReglaEntradas.findByTerminalModifica", query = "SELECT c FROM CmRipsReglaEntradas c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CmRipsReglaEntradas.findByFechaHoraModifica", query = "SELECT c FROM CmRipsReglaEntradas c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CmRipsReglaEntradas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "archivo")
    private String archivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "posicion")
    private int posicion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "campo")
    private String campo;
    @Column(name = "orden")
    private Integer orden;
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
    @JoinColumn(name = "cm_rips_reglas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmRipsReglas cmRipsReglasId;

    public CmRipsReglaEntradas() {
    }

    public CmRipsReglaEntradas(Integer id) {
        this.id = id;
    }

    public CmRipsReglaEntradas(Integer id, int tipo, String archivo, int posicion, String campo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
        this.archivo = archivo;
        this.posicion = posicion;
        this.campo = campo;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
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

    public CmRipsReglas getCmRipsReglasId() {
        return cmRipsReglasId;
    }

    public void setCmRipsReglasId(CmRipsReglas cmRipsReglasId) {
        this.cmRipsReglasId = cmRipsReglasId;
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
        if (!(object instanceof CmRipsReglaEntradas)) {
            return false;
        }
        CmRipsReglaEntradas other = (CmRipsReglaEntradas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmRipsReglaEntradas[ id=" + id + " ]";
    }
    
}
