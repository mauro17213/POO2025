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
@Table(name = "au_anexo3_carga_anulada_sucesos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAnexo3CargaAnuladaSucesos.findAll", query = "SELECT a FROM AuAnexo3CargaAnuladaSucesos a"),
    @NamedQuery(name = "AuAnexo3CargaAnuladaSucesos.findById", query = "SELECT a FROM AuAnexo3CargaAnuladaSucesos a WHERE a.id = :id"),
    @NamedQuery(name = "AuAnexo3CargaAnuladaSucesos.findByEstado", query = "SELECT a FROM AuAnexo3CargaAnuladaSucesos a WHERE a.estado = :estado"),
    @NamedQuery(name = "AuAnexo3CargaAnuladaSucesos.findByFila", query = "SELECT a FROM AuAnexo3CargaAnuladaSucesos a WHERE a.fila = :fila"),
    @NamedQuery(name = "AuAnexo3CargaAnuladaSucesos.findByColumna", query = "SELECT a FROM AuAnexo3CargaAnuladaSucesos a WHERE a.columna = :columna"),
    @NamedQuery(name = "AuAnexo3CargaAnuladaSucesos.findByFechaHora", query = "SELECT a FROM AuAnexo3CargaAnuladaSucesos a WHERE a.fechaHora = :fechaHora")})
public class AuAnexo3CargaAnuladaSucesos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "estado")
    private Integer estado;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "data")
    private byte[] data;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fila")
    private int fila;
    @Basic(optional = false)
    @NotNull
    @Column(name = "columna")
    private int columna;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "detalle_fallo")
    private String detalleFallo;
    @Column(name = "fecha_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @JoinColumn(name = "au_anexos3_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuAnexos3 auAnexos3Id;
    @JoinColumn(name = "au_anexo3_carga_anuladas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuAnexo3CargaAnuladas auAnexo3CargaAnuladasId;

    public AuAnexo3CargaAnuladaSucesos() {
    }

    public AuAnexo3CargaAnuladaSucesos(Integer id) {
        this.id = id;
    }

    public AuAnexo3CargaAnuladaSucesos(Integer id, byte[] data, int fila, int columna) {
        this.id = id;
        this.data = data;
        this.fila = fila;
        this.columna = columna;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getDetalleFallo() {
        return detalleFallo;
    }

    public void setDetalleFallo(String detalleFallo) {
        this.detalleFallo = detalleFallo;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public AuAnexos3 getAuAnexos3Id() {
        return auAnexos3Id;
    }

    public void setAuAnexos3Id(AuAnexos3 auAnexos3Id) {
        this.auAnexos3Id = auAnexos3Id;
    }

    public AuAnexo3CargaAnuladas getAuAnexo3CargaAnuladasId() {
        return auAnexo3CargaAnuladasId;
    }

    public void setAuAnexo3CargaAnuladasId(AuAnexo3CargaAnuladas auAnexo3CargaAnuladasId) {
        this.auAnexo3CargaAnuladasId = auAnexo3CargaAnuladasId;
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
        if (!(object instanceof AuAnexo3CargaAnuladaSucesos)) {
            return false;
        }
        AuAnexo3CargaAnuladaSucesos other = (AuAnexo3CargaAnuladaSucesos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuAnexo3CargaAnuladaSucesos[ id=" + id + " ]";
    }
    
}
