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
@Table(name = "inf_informe_generados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InfInformeGenerados.findAll", query = "SELECT i FROM InfInformeGenerados i"),
    @NamedQuery(name = "InfInformeGenerados.findById", query = "SELECT i FROM InfInformeGenerados i WHERE i.id = :id"),
    @NamedQuery(name = "InfInformeGenerados.findByArchivo", query = "SELECT i FROM InfInformeGenerados i WHERE i.archivo = :archivo"),
    @NamedQuery(name = "InfInformeGenerados.findByNombreArchivo", query = "SELECT i FROM InfInformeGenerados i WHERE i.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "InfInformeGenerados.findByDescripcion", query = "SELECT i FROM InfInformeGenerados i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "InfInformeGenerados.findByRuta", query = "SELECT i FROM InfInformeGenerados i WHERE i.ruta = :ruta"),
    @NamedQuery(name = "InfInformeGenerados.findByExiste", query = "SELECT i FROM InfInformeGenerados i WHERE i.existe = :existe"),
    @NamedQuery(name = "InfInformeGenerados.findByPlantilla", query = "SELECT i FROM InfInformeGenerados i WHERE i.plantilla = :plantilla"),
    @NamedQuery(name = "InfInformeGenerados.findByEstado", query = "SELECT i FROM InfInformeGenerados i WHERE i.estado = :estado"),
    @NamedQuery(name = "InfInformeGenerados.findByFechaHoraInicio", query = "SELECT i FROM InfInformeGenerados i WHERE i.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "InfInformeGenerados.findByFechaHoraFin", query = "SELECT i FROM InfInformeGenerados i WHERE i.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "InfInformeGenerados.findByGnEmpresasId", query = "SELECT i FROM InfInformeGenerados i WHERE i.gnEmpresasId = :gnEmpresasId"),
    @NamedQuery(name = "InfInformeGenerados.findByTiempo", query = "SELECT i FROM InfInformeGenerados i WHERE i.tiempo = :tiempo"),
    @NamedQuery(name = "InfInformeGenerados.findByNombre", query = "SELECT i FROM InfInformeGenerados i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "InfInformeGenerados.findByUsuarioCrea", query = "SELECT i FROM InfInformeGenerados i WHERE i.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "InfInformeGenerados.findByFechaHoraCrea", query = "SELECT i FROM InfInformeGenerados i WHERE i.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "InfInformeGenerados.findByTerminalCrea", query = "SELECT i FROM InfInformeGenerados i WHERE i.terminalCrea = :terminalCrea")})
public class InfInformeGenerados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "archivo")
    private String archivo;
    @Size(max = 256)
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @Size(max = 512)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "ruta")
    private String ruta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "existe")
    private boolean existe;
    @Column(name = "plantilla")
    private Boolean plantilla;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;
    @Column(name = "fecha_hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;
    @Column(name = "gn_empresas_id")
    private Integer gnEmpresasId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tiempo")
    private int tiempo;
    @Size(max = 256)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "infInformeGeneradosId", fetch = FetchType.LAZY)
    private List<InfInformeDescargados> infInformeDescargadosList;
    @JoinColumn(name = "inf_informes_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InfInformes infInformesId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "infGeneradosId", fetch = FetchType.LAZY)
    private List<InfInformeValores> infInformeValoresList;

    public InfInformeGenerados() {
    }

    public InfInformeGenerados(Integer id) {
        this.id = id;
    }

    public InfInformeGenerados(Integer id, String archivo, String ruta, boolean existe, int tiempo, String usuarioCrea, Date fechaHoraCrea, String terminalCrea) {
        this.id = id;
        this.archivo = archivo;
        this.ruta = ruta;
        this.existe = existe;
        this.tiempo = tiempo;
        this.usuarioCrea = usuarioCrea;
        this.fechaHoraCrea = fechaHoraCrea;
        this.terminalCrea = terminalCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public Boolean getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(Boolean plantilla) {
        this.plantilla = plantilla;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
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

    public Integer getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(Integer gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public String getTerminalCrea() {
        return terminalCrea;
    }

    public void setTerminalCrea(String terminalCrea) {
        this.terminalCrea = terminalCrea;
    }

    @XmlTransient
    public List<InfInformeDescargados> getInfInformeDescargadosList() {
        return infInformeDescargadosList;
    }

    public void setInfInformeDescargadosList(List<InfInformeDescargados> infInformeDescargadosList) {
        this.infInformeDescargadosList = infInformeDescargadosList;
    }

    public InfInformes getInfInformesId() {
        return infInformesId;
    }

    public void setInfInformesId(InfInformes infInformesId) {
        this.infInformesId = infInformesId;
    }

    @XmlTransient
    public List<InfInformeValores> getInfInformeValoresList() {
        return infInformeValoresList;
    }

    public void setInfInformeValoresList(List<InfInformeValores> infInformeValoresList) {
        this.infInformeValoresList = infInformeValoresList;
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
        if (!(object instanceof InfInformeGenerados)) {
            return false;
        }
        InfInformeGenerados other = (InfInformeGenerados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.InfInformeGenerados[ id=" + id + " ]";
    }
    
}
