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
@Table(name = "gs_zona_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GsZonaUsuarios.findAll", query = "SELECT g FROM GsZonaUsuarios g"),
    @NamedQuery(name = "GsZonaUsuarios.findById", query = "SELECT g FROM GsZonaUsuarios g WHERE g.id = :id"),
    @NamedQuery(name = "GsZonaUsuarios.findByMaeSala", query = "SELECT g FROM GsZonaUsuarios g WHERE g.maeSala = :maeSala"),
    @NamedQuery(name = "GsZonaUsuarios.findByActivo", query = "SELECT g FROM GsZonaUsuarios g WHERE g.activo = :activo"),
    @NamedQuery(name = "GsZonaUsuarios.findByGestiones", query = "SELECT g FROM GsZonaUsuarios g WHERE g.gestiones = :gestiones"),
    @NamedQuery(name = "GsZonaUsuarios.findByUsuarioCrea", query = "SELECT g FROM GsZonaUsuarios g WHERE g.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "GsZonaUsuarios.findByTerminalCrea", query = "SELECT g FROM GsZonaUsuarios g WHERE g.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "GsZonaUsuarios.findByFechaHoraCrea", query = "SELECT g FROM GsZonaUsuarios g WHERE g.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "GsZonaUsuarios.findByUsuarioModifica", query = "SELECT g FROM GsZonaUsuarios g WHERE g.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "GsZonaUsuarios.findByTerminalModifica", query = "SELECT g FROM GsZonaUsuarios g WHERE g.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "GsZonaUsuarios.findByFechaHoraModifica", query = "SELECT g FROM GsZonaUsuarios g WHERE g.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "GsZonaUsuarios.findByGestion01", query = "SELECT g FROM GsZonaUsuarios g WHERE g.gestion01 = :gestion01"),
    @NamedQuery(name = "GsZonaUsuarios.findByGestion02", query = "SELECT g FROM GsZonaUsuarios g WHERE g.gestion02 = :gestion02"),
    @NamedQuery(name = "GsZonaUsuarios.findByGestion03", query = "SELECT g FROM GsZonaUsuarios g WHERE g.gestion03 = :gestion03"),
    @NamedQuery(name = "GsZonaUsuarios.findByGestion04", query = "SELECT g FROM GsZonaUsuarios g WHERE g.gestion04 = :gestion04"),
    @NamedQuery(name = "GsZonaUsuarios.findByGestion05", query = "SELECT g FROM GsZonaUsuarios g WHERE g.gestion05 = :gestion05"),
    @NamedQuery(name = "GsZonaUsuarios.findByGestion06", query = "SELECT g FROM GsZonaUsuarios g WHERE g.gestion06 = :gestion06"),
    @NamedQuery(name = "GsZonaUsuarios.findByGestion07", query = "SELECT g FROM GsZonaUsuarios g WHERE g.gestion07 = :gestion07"),
    @NamedQuery(name = "GsZonaUsuarios.findByGestion08", query = "SELECT g FROM GsZonaUsuarios g WHERE g.gestion08 = :gestion08"),
    @NamedQuery(name = "GsZonaUsuarios.findByGestion09", query = "SELECT g FROM GsZonaUsuarios g WHERE g.gestion09 = :gestion09"),
    @NamedQuery(name = "GsZonaUsuarios.findByGestion10", query = "SELECT g FROM GsZonaUsuarios g WHERE g.gestion10 = :gestion10")})
public class GsZonaUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_sala")
    private int maeSala;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "gestiones")
    private String gestiones;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "gestion01")
    private boolean gestion01;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gestion02")
    private boolean gestion02;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gestion03")
    private boolean gestion03;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gestion04")
    private boolean gestion04;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gestion05")
    private boolean gestion05;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gestion06")
    private boolean gestion06;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gestion07")
    private boolean gestion07;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gestion08")
    private boolean gestion08;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gestion09")
    private boolean gestion09;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gestion10")
    private boolean gestion10;
    @JoinColumn(name = "gs_zonas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GsZonas gsZonasId;
    @JoinColumn(name = "gn_usuarios_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUsuarios gnUsuariosId;

    public GsZonaUsuarios() {
    }

    public GsZonaUsuarios(Integer id) {
        this.id = id;
    }

    public GsZonaUsuarios(Integer id, int maeSala, boolean activo, String gestiones, String usuarioCrea, String terminalCrea, Date fechaHoraCrea, boolean gestion01, boolean gestion02, boolean gestion03, boolean gestion04, boolean gestion05, boolean gestion06, boolean gestion07, boolean gestion08, boolean gestion09, boolean gestion10) {
        this.id = id;
        this.maeSala = maeSala;
        this.activo = activo;
        this.gestiones = gestiones;
        this.usuarioCrea = usuarioCrea;
        this.terminalCrea = terminalCrea;
        this.fechaHoraCrea = fechaHoraCrea;
        this.gestion01 = gestion01;
        this.gestion02 = gestion02;
        this.gestion03 = gestion03;
        this.gestion04 = gestion04;
        this.gestion05 = gestion05;
        this.gestion06 = gestion06;
        this.gestion07 = gestion07;
        this.gestion08 = gestion08;
        this.gestion09 = gestion09;
        this.gestion10 = gestion10;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeSala() {
        return maeSala;
    }

    public void setMaeSala(int maeSala) {
        this.maeSala = maeSala;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getGestiones() {
        return gestiones;
    }

    public void setGestiones(String gestiones) {
        this.gestiones = gestiones;
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

    public boolean getGestion01() {
        return gestion01;
    }

    public void setGestion01(boolean gestion01) {
        this.gestion01 = gestion01;
    }

    public boolean getGestion02() {
        return gestion02;
    }

    public void setGestion02(boolean gestion02) {
        this.gestion02 = gestion02;
    }

    public boolean getGestion03() {
        return gestion03;
    }

    public void setGestion03(boolean gestion03) {
        this.gestion03 = gestion03;
    }

    public boolean getGestion04() {
        return gestion04;
    }

    public void setGestion04(boolean gestion04) {
        this.gestion04 = gestion04;
    }

    public boolean getGestion05() {
        return gestion05;
    }

    public void setGestion05(boolean gestion05) {
        this.gestion05 = gestion05;
    }

    public boolean getGestion06() {
        return gestion06;
    }

    public void setGestion06(boolean gestion06) {
        this.gestion06 = gestion06;
    }

    public boolean getGestion07() {
        return gestion07;
    }

    public void setGestion07(boolean gestion07) {
        this.gestion07 = gestion07;
    }

    public boolean getGestion08() {
        return gestion08;
    }

    public void setGestion08(boolean gestion08) {
        this.gestion08 = gestion08;
    }

    public boolean getGestion09() {
        return gestion09;
    }

    public void setGestion09(boolean gestion09) {
        this.gestion09 = gestion09;
    }

    public boolean getGestion10() {
        return gestion10;
    }

    public void setGestion10(boolean gestion10) {
        this.gestion10 = gestion10;
    }

    public GsZonas getGsZonasId() {
        return gsZonasId;
    }

    public void setGsZonasId(GsZonas gsZonasId) {
        this.gsZonasId = gsZonasId;
    }

    public GnUsuarios getGnUsuariosId() {
        return gnUsuariosId;
    }

    public void setGnUsuariosId(GnUsuarios gnUsuariosId) {
        this.gnUsuariosId = gnUsuariosId;
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
        if (!(object instanceof GsZonaUsuarios)) {
            return false;
        }
        GsZonaUsuarios other = (GsZonaUsuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GsZonaUsuarios[ id=" + id + " ]";
    }
    
}
