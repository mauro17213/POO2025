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
@Table(name = "cnt_prestador_contactos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntPrestadorContactos.findAll", query = "SELECT c FROM CntPrestadorContactos c"),
    @NamedQuery(name = "CntPrestadorContactos.findById", query = "SELECT c FROM CntPrestadorContactos c WHERE c.id = :id"),
    @NamedQuery(name = "CntPrestadorContactos.findByMaeTipoContactoId", query = "SELECT c FROM CntPrestadorContactos c WHERE c.maeTipoContactoId = :maeTipoContactoId"),
    @NamedQuery(name = "CntPrestadorContactos.findByMaeTipoContactoCodigo", query = "SELECT c FROM CntPrestadorContactos c WHERE c.maeTipoContactoCodigo = :maeTipoContactoCodigo"),
    @NamedQuery(name = "CntPrestadorContactos.findByMaeTipoContactoValor", query = "SELECT c FROM CntPrestadorContactos c WHERE c.maeTipoContactoValor = :maeTipoContactoValor"),
    @NamedQuery(name = "CntPrestadorContactos.findByMaeAreaContactoId", query = "SELECT c FROM CntPrestadorContactos c WHERE c.maeAreaContactoId = :maeAreaContactoId"),
    @NamedQuery(name = "CntPrestadorContactos.findByMaeAreaContactoCodigo", query = "SELECT c FROM CntPrestadorContactos c WHERE c.maeAreaContactoCodigo = :maeAreaContactoCodigo"),
    @NamedQuery(name = "CntPrestadorContactos.findByMaeAreaContactoValor", query = "SELECT c FROM CntPrestadorContactos c WHERE c.maeAreaContactoValor = :maeAreaContactoValor"),
    @NamedQuery(name = "CntPrestadorContactos.findByContacto", query = "SELECT c FROM CntPrestadorContactos c WHERE c.contacto = :contacto"),
    @NamedQuery(name = "CntPrestadorContactos.findByAutorizaEnvio", query = "SELECT c FROM CntPrestadorContactos c WHERE c.autorizaEnvio = :autorizaEnvio"),
    @NamedQuery(name = "CntPrestadorContactos.findByActivo", query = "SELECT c FROM CntPrestadorContactos c WHERE c.activo = :activo"),
    @NamedQuery(name = "CntPrestadorContactos.findByObservacion", query = "SELECT c FROM CntPrestadorContactos c WHERE c.observacion = :observacion"),
    @NamedQuery(name = "CntPrestadorContactos.findByUsuarioCrea", query = "SELECT c FROM CntPrestadorContactos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntPrestadorContactos.findByTerminalCrea", query = "SELECT c FROM CntPrestadorContactos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntPrestadorContactos.findByFechaHoraCrea", query = "SELECT c FROM CntPrestadorContactos c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CntPrestadorContactos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_tipo_contacto_id")
    private int maeTipoContactoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_contacto_codigo")
    private String maeTipoContactoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_tipo_contacto_valor")
    private String maeTipoContactoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_area_contacto_id")
    private int maeAreaContactoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_area_contacto_codigo")
    private String maeAreaContactoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_area_contacto_valor")
    private String maeAreaContactoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "contacto")
    private String contacto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "autoriza_envio")
    private boolean autorizaEnvio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Size(max = 128)
    @Column(name = "observacion")
    private String observacion;
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
    @JoinColumn(name = "cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesId;
    @JoinColumn(name = "cnt_prestadores_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadores cntPrestadoresId;

    public CntPrestadorContactos() {
    }

    public CntPrestadorContactos(Integer id) {
        this.id = id;
    }

    public CntPrestadorContactos(Integer id, int maeTipoContactoId, String maeTipoContactoCodigo, String maeTipoContactoValor, int maeAreaContactoId, String maeAreaContactoCodigo, String maeAreaContactoValor, String contacto, boolean autorizaEnvio, boolean activo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeTipoContactoId = maeTipoContactoId;
        this.maeTipoContactoCodigo = maeTipoContactoCodigo;
        this.maeTipoContactoValor = maeTipoContactoValor;
        this.maeAreaContactoId = maeAreaContactoId;
        this.maeAreaContactoCodigo = maeAreaContactoCodigo;
        this.maeAreaContactoValor = maeAreaContactoValor;
        this.contacto = contacto;
        this.autorizaEnvio = autorizaEnvio;
        this.activo = activo;
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

    public int getMaeTipoContactoId() {
        return maeTipoContactoId;
    }

    public void setMaeTipoContactoId(int maeTipoContactoId) {
        this.maeTipoContactoId = maeTipoContactoId;
    }

    public String getMaeTipoContactoCodigo() {
        return maeTipoContactoCodigo;
    }

    public void setMaeTipoContactoCodigo(String maeTipoContactoCodigo) {
        this.maeTipoContactoCodigo = maeTipoContactoCodigo;
    }

    public String getMaeTipoContactoValor() {
        return maeTipoContactoValor;
    }

    public void setMaeTipoContactoValor(String maeTipoContactoValor) {
        this.maeTipoContactoValor = maeTipoContactoValor;
    }

    public int getMaeAreaContactoId() {
        return maeAreaContactoId;
    }

    public void setMaeAreaContactoId(int maeAreaContactoId) {
        this.maeAreaContactoId = maeAreaContactoId;
    }

    public String getMaeAreaContactoCodigo() {
        return maeAreaContactoCodigo;
    }

    public void setMaeAreaContactoCodigo(String maeAreaContactoCodigo) {
        this.maeAreaContactoCodigo = maeAreaContactoCodigo;
    }

    public String getMaeAreaContactoValor() {
        return maeAreaContactoValor;
    }

    public void setMaeAreaContactoValor(String maeAreaContactoValor) {
        this.maeAreaContactoValor = maeAreaContactoValor;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public boolean getAutorizaEnvio() {
        return autorizaEnvio;
    }

    public void setAutorizaEnvio(boolean autorizaEnvio) {
        this.autorizaEnvio = autorizaEnvio;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    public CntPrestadorSedes getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSedes cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    public CntPrestadores getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    public void setCntPrestadoresId(CntPrestadores cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
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
        if (!(object instanceof CntPrestadorContactos)) {
            return false;
        }
        CntPrestadorContactos other = (CntPrestadorContactos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntPrestadorContactos[ id=" + id + " ]";
    }
    
}
