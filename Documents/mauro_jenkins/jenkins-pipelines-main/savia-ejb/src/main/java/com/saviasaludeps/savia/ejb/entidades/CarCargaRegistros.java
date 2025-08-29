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
@Table(name = "car_carga_registros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CarCargaRegistros.findAll", query = "SELECT c FROM CarCargaRegistros c"),
    @NamedQuery(name = "CarCargaRegistros.findById", query = "SELECT c FROM CarCargaRegistros c WHERE c.id = :id"),
    @NamedQuery(name = "CarCargaRegistros.findByTipo", query = "SELECT c FROM CarCargaRegistros c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "CarCargaRegistros.findByFila", query = "SELECT c FROM CarCargaRegistros c WHERE c.fila = :fila"),
    @NamedQuery(name = "CarCargaRegistros.findByJsonDatos", query = "SELECT c FROM CarCargaRegistros c WHERE c.jsonDatos = :jsonDatos"),
    @NamedQuery(name = "CarCargaRegistros.findByFallido", query = "SELECT c FROM CarCargaRegistros c WHERE c.fallido = :fallido"),
    @NamedQuery(name = "CarCargaRegistros.findByFallos", query = "SELECT c FROM CarCargaRegistros c WHERE c.fallos = :fallos"),
    @NamedQuery(name = "CarCargaRegistros.findByUsuarioCrea", query = "SELECT c FROM CarCargaRegistros c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CarCargaRegistros.findByTerminalCrea", query = "SELECT c FROM CarCargaRegistros c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CarCargaRegistros.findByFechaHoraCrea", query = "SELECT c FROM CarCargaRegistros c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CarCargaRegistros.findByUsuarioModifica", query = "SELECT c FROM CarCargaRegistros c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CarCargaRegistros.findByTerminalModifica", query = "SELECT c FROM CarCargaRegistros c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CarCargaRegistros.findByFechaHoraModifica", query = "SELECT c FROM CarCargaRegistros c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CarCargaRegistros implements Serializable {

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
    @Column(name = "fila")
    private int fila;
    @Size(max = 2147483647)
    @Column(name = "json_datos")
    private String jsonDatos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fallido")
    private boolean fallido;
    @Size(max = 1024)
    @Column(name = "fallos")
    private String fallos;
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
    @JoinColumn(name = "car_cargas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CarCargas carCargasId;

    public CarCargaRegistros() {
    }

    public CarCargaRegistros(Integer id) {
        this.id = id;
    }

    public CarCargaRegistros(Integer id, int tipo, int fila, boolean fallido, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
        this.fila = fila;
        this.fallido = fallido;
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

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public String getJsonDatos() {
        return jsonDatos;
    }

    public void setJsonDatos(String jsonDatos) {
        this.jsonDatos = jsonDatos;
    }

    public boolean getFallido() {
        return fallido;
    }

    public void setFallido(boolean fallido) {
        this.fallido = fallido;
    }

    public String getFallos() {
        return fallos;
    }

    public void setFallos(String fallos) {
        this.fallos = fallos;
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

    public CarCargas getCarCargasId() {
        return carCargasId;
    }

    public void setCarCargasId(CarCargas carCargasId) {
        this.carCargasId = carCargasId;
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
        if (!(object instanceof CarCargaRegistros)) {
            return false;
        }
        CarCargaRegistros other = (CarCargaRegistros) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CarCargaRegistros[ id=" + id + " ]";
    }
    
}
