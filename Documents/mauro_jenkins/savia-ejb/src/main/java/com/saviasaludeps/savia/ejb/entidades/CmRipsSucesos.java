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
@Table(name = "cm_rips_sucesos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmRipsSucesos.findAll", query = "SELECT c FROM CmRipsSucesos c"),
    @NamedQuery(name = "CmRipsSucesos.findById", query = "SELECT c FROM CmRipsSucesos c WHERE c.id = :id"),
    @NamedQuery(name = "CmRipsSucesos.findByArchivoNombre", query = "SELECT c FROM CmRipsSucesos c WHERE c.archivoNombre = :archivoNombre"),
    @NamedQuery(name = "CmRipsSucesos.findByArchivoFila", query = "SELECT c FROM CmRipsSucesos c WHERE c.archivoFila = :archivoFila"),
    @NamedQuery(name = "CmRipsSucesos.findByCmRipsReglasId", query = "SELECT c FROM CmRipsSucesos c WHERE c.cmRipsReglasId = :cmRipsReglasId"),
    @NamedQuery(name = "CmRipsSucesos.findByCmRipsReglasMensajesId", query = "SELECT c FROM CmRipsSucesos c WHERE c.cmRipsReglasMensajesId = :cmRipsReglasMensajesId"),
    @NamedQuery(name = "CmRipsSucesos.findByNombreRegla", query = "SELECT c FROM CmRipsSucesos c WHERE c.nombreRegla = :nombreRegla"),
    @NamedQuery(name = "CmRipsSucesos.findByTipoRegla", query = "SELECT c FROM CmRipsSucesos c WHERE c.tipoRegla = :tipoRegla"),
    @NamedQuery(name = "CmRipsSucesos.findByAlerta", query = "SELECT c FROM CmRipsSucesos c WHERE c.alerta = :alerta"),
    @NamedQuery(name = "CmRipsSucesos.findByDescripcionMensaje", query = "SELECT c FROM CmRipsSucesos c WHERE c.descripcionMensaje = :descripcionMensaje"),
    @NamedQuery(name = "CmRipsSucesos.findByUsuarioCrea", query = "SELECT c FROM CmRipsSucesos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmRipsSucesos.findByTerminalCrea", query = "SELECT c FROM CmRipsSucesos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmRipsSucesos.findByFechaHoraCrea", query = "SELECT c FROM CmRipsSucesos c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmRipsSucesos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "archivo_nombre")
    private String archivoNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "archivo_fila")
    private int archivoFila;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cm_rips_reglas_id")
    private int cmRipsReglasId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cm_rips_reglas_mensajes_id")
    private int cmRipsReglasMensajesId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "nombre_regla")
    private String nombreRegla;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_regla")
    private int tipoRegla;
    @Basic(optional = false)
    @NotNull
    @Column(name = "alerta")
    private int alerta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "descripcion_mensaje")
    private String descripcionMensaje;
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

    public CmRipsSucesos() {
    }

    public CmRipsSucesos(Integer id) {
        this.id = id;
    }

    public CmRipsSucesos(Integer id, String archivoNombre, int archivoFila, int cmRipsReglasId, int cmRipsReglasMensajesId, String nombreRegla, int tipoRegla, int alerta, String descripcionMensaje, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.archivoNombre = archivoNombre;
        this.archivoFila = archivoFila;
        this.cmRipsReglasId = cmRipsReglasId;
        this.cmRipsReglasMensajesId = cmRipsReglasMensajesId;
        this.nombreRegla = nombreRegla;
        this.tipoRegla = tipoRegla;
        this.alerta = alerta;
        this.descripcionMensaje = descripcionMensaje;
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

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public int getArchivoFila() {
        return archivoFila;
    }

    public void setArchivoFila(int archivoFila) {
        this.archivoFila = archivoFila;
    }

    public int getCmRipsReglasId() {
        return cmRipsReglasId;
    }

    public void setCmRipsReglasId(int cmRipsReglasId) {
        this.cmRipsReglasId = cmRipsReglasId;
    }

    public int getCmRipsReglasMensajesId() {
        return cmRipsReglasMensajesId;
    }

    public void setCmRipsReglasMensajesId(int cmRipsReglasMensajesId) {
        this.cmRipsReglasMensajesId = cmRipsReglasMensajesId;
    }

    public String getNombreRegla() {
        return nombreRegla;
    }

    public void setNombreRegla(String nombreRegla) {
        this.nombreRegla = nombreRegla;
    }

    public int getTipoRegla() {
        return tipoRegla;
    }

    public void setTipoRegla(int tipoRegla) {
        this.tipoRegla = tipoRegla;
    }

    public int getAlerta() {
        return alerta;
    }

    public void setAlerta(int alerta) {
        this.alerta = alerta;
    }

    public String getDescripcionMensaje() {
        return descripcionMensaje;
    }

    public void setDescripcionMensaje(String descripcionMensaje) {
        this.descripcionMensaje = descripcionMensaje;
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
        if (!(object instanceof CmRipsSucesos)) {
            return false;
        }
        CmRipsSucesos other = (CmRipsSucesos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmRipsSucesos[ id=" + id + " ]";
    }
    
}
