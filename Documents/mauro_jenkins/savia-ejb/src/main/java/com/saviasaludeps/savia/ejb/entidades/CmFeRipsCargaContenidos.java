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
import javax.persistence.Lob;
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
@Table(name = "cm_fe_rips_carga_contenidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmFeRipsCargaContenidos.findAll", query = "SELECT c FROM CmFeRipsCargaContenidos c"),
    @NamedQuery(name = "CmFeRipsCargaContenidos.findById", query = "SELECT c FROM CmFeRipsCargaContenidos c WHERE c.id = :id"),
    @NamedQuery(name = "CmFeRipsCargaContenidos.findByTipo", query = "SELECT c FROM CmFeRipsCargaContenidos c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "CmFeRipsCargaContenidos.findByJson", query = "SELECT c FROM CmFeRipsCargaContenidos c WHERE c.json = :json"),
    @NamedQuery(name = "CmFeRipsCargaContenidos.findByCuvJson", query = "SELECT c FROM CmFeRipsCargaContenidos c WHERE c.cuvJson = :cuvJson"),
    @NamedQuery(name = "CmFeRipsCargaContenidos.findByUsuarioCrea", query = "SELECT c FROM CmFeRipsCargaContenidos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmFeRipsCargaContenidos.findByTerminalCrea", query = "SELECT c FROM CmFeRipsCargaContenidos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmFeRipsCargaContenidos.findByFechaHoraCrea", query = "SELECT c FROM CmFeRipsCargaContenidos c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmFeRipsCargaContenidos implements Serializable {

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
    @Size(max = 2147483647)
    @Column(name = "json")
    private String json;
    @Lob
    @Size(max = 16777215)
    @Column(name = "xml")
    private String xml;
    @Lob
    @Size(max = 65535)
    @Column(name = "cuv")
    private String cuv;
    @Size(max = 2147483647)
    @Column(name = "cuv_json")
    private String cuvJson;
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
    @JoinColumn(name = "cm_fe_rips_cargas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmFeRipsCargas cmFeRipsCargasId;

    public CmFeRipsCargaContenidos() {
    }

    public CmFeRipsCargaContenidos(Integer id) {
        this.id = id;
    }

    public CmFeRipsCargaContenidos(Integer id, int tipo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
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

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getCuv() {
        return cuv;
    }

    public void setCuv(String cuv) {
        this.cuv = cuv;
    }

    public String getCuvJson() {
        return cuvJson;
    }

    public void setCuvJson(String cuvJson) {
        this.cuvJson = cuvJson;
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

    public CmFeRipsCargas getCmFeRipsCargasId() {
        return cmFeRipsCargasId;
    }

    public void setCmFeRipsCargasId(CmFeRipsCargas cmFeRipsCargasId) {
        this.cmFeRipsCargasId = cmFeRipsCargasId;
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
        if (!(object instanceof CmFeRipsCargaContenidos)) {
            return false;
        }
        CmFeRipsCargaContenidos other = (CmFeRipsCargaContenidos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmFeRipsCargaContenidos[ id=" + id + " ]";
    }
    
}
