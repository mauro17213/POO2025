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
@Table(name = "au_anexo4_carga_anulada_sucesos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAnexo4CargaAnuladaSucesos.findAll", query = "SELECT a FROM AuAnexo4CargaAnuladaSucesos a"),
    @NamedQuery(name = "AuAnexo4CargaAnuladaSucesos.findById", query = "SELECT a FROM AuAnexo4CargaAnuladaSucesos a WHERE a.id = :id"),
    @NamedQuery(name = "AuAnexo4CargaAnuladaSucesos.findByEstado", query = "SELECT a FROM AuAnexo4CargaAnuladaSucesos a WHERE a.estado = :estado"),
    @NamedQuery(name = "AuAnexo4CargaAnuladaSucesos.findByFila", query = "SELECT a FROM AuAnexo4CargaAnuladaSucesos a WHERE a.fila = :fila"),
    @NamedQuery(name = "AuAnexo4CargaAnuladaSucesos.findByColumna", query = "SELECT a FROM AuAnexo4CargaAnuladaSucesos a WHERE a.columna = :columna"),
    @NamedQuery(name = "AuAnexo4CargaAnuladaSucesos.findByFechaHora", query = "SELECT a FROM AuAnexo4CargaAnuladaSucesos a WHERE a.fechaHora = :fechaHora")})
public class AuAnexo4CargaAnuladaSucesos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
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
    @JoinColumn(name = "au_anexos4_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuAnexos4 auAnexos4Id;
    @JoinColumn(name = "au_anexo4_carga_anuladas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuAnexo4CargaAnuladas auAnexo4CargaAnuladasId;

    public AuAnexo4CargaAnuladaSucesos() {
    }

    public AuAnexo4CargaAnuladaSucesos(Integer id) {
        this.id = id;
    }

    public AuAnexo4CargaAnuladaSucesos(Integer id, int estado, byte[] data, int fila, int columna) {
        this.id = id;
        this.estado = estado;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
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

    public AuAnexos4 getAuAnexos4Id() {
        return auAnexos4Id;
    }

    public void setAuAnexos4Id(AuAnexos4 auAnexos4Id) {
        this.auAnexos4Id = auAnexos4Id;
    }

    public AuAnexo4CargaAnuladas getAuAnexo4CargaAnuladasId() {
        return auAnexo4CargaAnuladasId;
    }

    public void setAuAnexo4CargaAnuladasId(AuAnexo4CargaAnuladas auAnexo4CargaAnuladasId) {
        this.auAnexo4CargaAnuladasId = auAnexo4CargaAnuladasId;
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
        if (!(object instanceof AuAnexo4CargaAnuladaSucesos)) {
            return false;
        }
        AuAnexo4CargaAnuladaSucesos other = (AuAnexo4CargaAnuladaSucesos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuAnexo4CargaAnuladaSucesos[ id=" + id + " ]";
    }
    
}
