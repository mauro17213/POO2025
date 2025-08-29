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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "car_cargas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CarCargas.findAll", query = "SELECT c FROM CarCargas c"),
    @NamedQuery(name = "CarCargas.findById", query = "SELECT c FROM CarCargas c WHERE c.id = :id"),
    @NamedQuery(name = "CarCargas.findByEstado", query = "SELECT c FROM CarCargas c WHERE c.estado = :estado"),
    @NamedQuery(name = "CarCargas.findByNombreArchivo", query = "SELECT c FROM CarCargas c WHERE c.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "CarCargas.findByRuta", query = "SELECT c FROM CarCargas c WHERE c.ruta = :ruta"),
    @NamedQuery(name = "CarCargas.findByArchivo", query = "SELECT c FROM CarCargas c WHERE c.archivo = :archivo"),
    @NamedQuery(name = "CarCargas.findByExiste", query = "SELECT c FROM CarCargas c WHERE c.existe = :existe"),
    @NamedQuery(name = "CarCargas.findByFechaHoraInicio", query = "SELECT c FROM CarCargas c WHERE c.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "CarCargas.findByFechaHoraFin", query = "SELECT c FROM CarCargas c WHERE c.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "CarCargas.findByRegistros", query = "SELECT c FROM CarCargas c WHERE c.registros = :registros"),
    @NamedQuery(name = "CarCargas.findByExitosos", query = "SELECT c FROM CarCargas c WHERE c.exitosos = :exitosos"),
    @NamedQuery(name = "CarCargas.findByFallidos", query = "SELECT c FROM CarCargas c WHERE c.fallidos = :fallidos"),
    @NamedQuery(name = "CarCargas.findByUsuarioCrea", query = "SELECT c FROM CarCargas c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CarCargas.findByTerminalCrea", query = "SELECT c FROM CarCargas c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CarCargas.findByFechaHoraCrea", query = "SELECT c FROM CarCargas c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CarCargas.findByUsuarioModifica", query = "SELECT c FROM CarCargas c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CarCargas.findByTerminalModifica", query = "SELECT c FROM CarCargas c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CarCargas.findByFechaHoraModifica", query = "SELECT c FROM CarCargas c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CarCargas implements Serializable {

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
    @Size(min = 1, max = 256)
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "ruta")
    private String ruta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "archivo")
    private String archivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "existe")
    private boolean existe;
    @Column(name = "fecha_hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;
    @Column(name = "fecha_hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;
    @Column(name = "registros")
    private Integer registros;
    @Column(name = "exitosos")
    private Integer exitosos;
    @Column(name = "fallidos")
    private Integer fallidos;
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
    @Size(max = 26)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @JoinColumn(name = "car_periodos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CarPeriodos carPeriodosId;
    @JoinColumn(name = "car_procesos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CarProcesos carProcesosId;
    @JoinColumn(name = "cnt_prestadores_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntPrestadores cntPrestadoresId;
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carCargasId", fetch = FetchType.LAZY)
    private List<CarCargaRegistros> carCargaRegistrosList;

    public CarCargas() {
    }

    public CarCargas(Integer id) {
        this.id = id;
    }

    public CarCargas(Integer id, int estado, String nombreArchivo, String ruta, String archivo, boolean existe, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.estado = estado;
        this.nombreArchivo = nombreArchivo;
        this.ruta = ruta;
        this.archivo = archivo;
        this.existe = existe;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public Integer getRegistros() {
        return registros;
    }

    public void setRegistros(Integer registros) {
        this.registros = registros;
    }

    public Integer getExitosos() {
        return exitosos;
    }

    public void setExitosos(Integer exitosos) {
        this.exitosos = exitosos;
    }

    public Integer getFallidos() {
        return fallidos;
    }

    public void setFallidos(Integer fallidos) {
        this.fallidos = fallidos;
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

    public CarPeriodos getCarPeriodosId() {
        return carPeriodosId;
    }

    public void setCarPeriodosId(CarPeriodos carPeriodosId) {
        this.carPeriodosId = carPeriodosId;
    }

    public CarProcesos getCarProcesosId() {
        return carProcesosId;
    }

    public void setCarProcesosId(CarProcesos carProcesosId) {
        this.carProcesosId = carProcesosId;
    }

    public CntPrestadores getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    public void setCntPrestadoresId(CntPrestadores cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
    }

    public GnEmpresas getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(GnEmpresas gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
    }

    @XmlTransient
    public List<CarCargaRegistros> getCarCargaRegistrosList() {
        return carCargaRegistrosList;
    }

    public void setCarCargaRegistrosList(List<CarCargaRegistros> carCargaRegistrosList) {
        this.carCargaRegistrosList = carCargaRegistrosList;
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
        if (!(object instanceof CarCargas)) {
            return false;
        }
        CarCargas other = (CarCargas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CarCargas[ id=" + id + " ]";
    }
    
}
