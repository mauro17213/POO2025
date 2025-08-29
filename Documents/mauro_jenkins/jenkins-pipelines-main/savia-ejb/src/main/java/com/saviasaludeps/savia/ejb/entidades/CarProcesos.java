/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "car_procesos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CarProcesos.findAll", query = "SELECT c FROM CarProcesos c"),
    @NamedQuery(name = "CarProcesos.findById", query = "SELECT c FROM CarProcesos c WHERE c.id = :id"),
    @NamedQuery(name = "CarProcesos.findByNombre", query = "SELECT c FROM CarProcesos c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CarProcesos.findByDescripcion", query = "SELECT c FROM CarProcesos c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CarProcesos.findByActivo", query = "SELECT c FROM CarProcesos c WHERE c.activo = :activo"),
    @NamedQuery(name = "CarProcesos.findByEditable", query = "SELECT c FROM CarProcesos c WHERE c.editable = :editable"),
    @NamedQuery(name = "CarProcesos.findByEstructuraJson", query = "SELECT c FROM CarProcesos c WHERE c.estructuraJson = :estructuraJson"),
    @NamedQuery(name = "CarProcesos.findByUsuarioCrea", query = "SELECT c FROM CarProcesos c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CarProcesos.findByTerminalCrea", query = "SELECT c FROM CarProcesos c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CarProcesos.findByFechaHoraCrea", query = "SELECT c FROM CarProcesos c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CarProcesos.findByUsuarioModifica", query = "SELECT c FROM CarProcesos c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CarProcesos.findByTerminalModifica", query = "SELECT c FROM CarProcesos c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CarProcesos.findByFechaHoraModifica", query = "SELECT c FROM CarProcesos c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CarProcesos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "editable")
    private boolean editable;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "estructura_json")
    private String estructuraJson;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carProcesosId", fetch = FetchType.LAZY)
    private List<CarProcesoPrestadores> carProcesoPrestadoresList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carProcesosId", fetch = FetchType.LAZY)
    private List<CarCargas> carCargasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carProcesosId", fetch = FetchType.LAZY)
    private List<CarPeriodos> carPeriodosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carProcesosId", fetch = FetchType.LAZY)
    private List<CarProcesoUsuarios> carProcesoUsuariosList;

    public CarProcesos() {
    }

    public CarProcesos(Integer id) {
        this.id = id;
    }

    public CarProcesos(Integer id, String nombre, String descripcion, boolean activo, boolean editable, String estructuraJson, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
        this.editable = editable;
        this.estructuraJson = estructuraJson;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean getEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getEstructuraJson() {
        return estructuraJson;
    }

    public void setEstructuraJson(String estructuraJson) {
        this.estructuraJson = estructuraJson;
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

    @XmlTransient
    public List<CarProcesoPrestadores> getCarProcesoPrestadoresList() {
        return carProcesoPrestadoresList;
    }

    public void setCarProcesoPrestadoresList(List<CarProcesoPrestadores> carProcesoPrestadoresList) {
        this.carProcesoPrestadoresList = carProcesoPrestadoresList;
    }

    @XmlTransient
    public List<CarCargas> getCarCargasList() {
        return carCargasList;
    }

    public void setCarCargasList(List<CarCargas> carCargasList) {
        this.carCargasList = carCargasList;
    }

    @XmlTransient
    public List<CarPeriodos> getCarPeriodosList() {
        return carPeriodosList;
    }

    public void setCarPeriodosList(List<CarPeriodos> carPeriodosList) {
        this.carPeriodosList = carPeriodosList;
    }

    @XmlTransient
    public List<CarProcesoUsuarios> getCarProcesoUsuariosList() {
        return carProcesoUsuariosList;
    }

    public void setCarProcesoUsuariosList(List<CarProcesoUsuarios> carProcesoUsuariosList) {
        this.carProcesoUsuariosList = carProcesoUsuariosList;
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
        if (!(object instanceof CarProcesos)) {
            return false;
        }
        CarProcesos other = (CarProcesos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CarProcesos[ id=" + id + " ]";
    }
    
}
