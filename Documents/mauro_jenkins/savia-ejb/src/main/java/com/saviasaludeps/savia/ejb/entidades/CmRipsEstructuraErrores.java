/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "cm_rips_estructura_errores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmRipsEstructuraErrores.findAll", query = "SELECT c FROM CmRipsEstructuraErrores c"),
    @NamedQuery(name = "CmRipsEstructuraErrores.findById", query = "SELECT c FROM CmRipsEstructuraErrores c WHERE c.id = :id"),
    @NamedQuery(name = "CmRipsEstructuraErrores.findByArchivoNombre", query = "SELECT c FROM CmRipsEstructuraErrores c WHERE c.archivoNombre = :archivoNombre"),
    @NamedQuery(name = "CmRipsEstructuraErrores.findByArchivoFila", query = "SELECT c FROM CmRipsEstructuraErrores c WHERE c.archivoFila = :archivoFila"),
    @NamedQuery(name = "CmRipsEstructuraErrores.findByDescripcionError", query = "SELECT c FROM CmRipsEstructuraErrores c WHERE c.descripcionError = :descripcionError")})
public class CmRipsEstructuraErrores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "archivo_nombre")
    private String archivoNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "archivo_fila")
    private String archivoFila;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "descripcion_error")
    private String descripcionError;
    @JoinColumn(name = "cm_rips_cargas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmRipsCargas cmRipsCargasId;

    public CmRipsEstructuraErrores() {
    }

    public CmRipsEstructuraErrores(Integer id) {
        this.id = id;
    }

    public CmRipsEstructuraErrores(Integer id, String archivoNombre, String archivoFila, String descripcionError) {
        this.id = id;
        this.archivoNombre = archivoNombre;
        this.archivoFila = archivoFila;
        this.descripcionError = descripcionError;
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

    public String getArchivoFila() {
        return archivoFila;
    }

    public void setArchivoFila(String archivoFila) {
        this.archivoFila = archivoFila;
    }

    public String getDescripcionError() {
        return descripcionError;
    }

    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
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
        if (!(object instanceof CmRipsEstructuraErrores)) {
            return false;
        }
        CmRipsEstructuraErrores other = (CmRipsEstructuraErrores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmRipsEstructuraErrores[ id=" + id + " ]";
    }
    
}
