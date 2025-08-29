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
@Table(name = "inf_informes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InfInformes.findAll", query = "SELECT i FROM InfInformes i"),
    @NamedQuery(name = "InfInformes.findById", query = "SELECT i FROM InfInformes i WHERE i.id = :id"),
    @NamedQuery(name = "InfInformes.findByNombre", query = "SELECT i FROM InfInformes i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "InfInformes.findByNombreSp", query = "SELECT i FROM InfInformes i WHERE i.nombreSp = :nombreSp"),
    @NamedQuery(name = "InfInformes.findByDescripcion", query = "SELECT i FROM InfInformes i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "InfInformes.findByEstado", query = "SELECT i FROM InfInformes i WHERE i.estado = :estado"),
    @NamedQuery(name = "InfInformes.findByActivo", query = "SELECT i FROM InfInformes i WHERE i.activo = :activo"),
    @NamedQuery(name = "InfInformes.findByProgramado", query = "SELECT i FROM InfInformes i WHERE i.programado = :programado"),
    @NamedQuery(name = "InfInformes.findByRequiereAprobacion", query = "SELECT i FROM InfInformes i WHERE i.requiereAprobacion = :requiereAprobacion"),
    @NamedQuery(name = "InfInformes.findByPeriodicidad", query = "SELECT i FROM InfInformes i WHERE i.periodicidad = :periodicidad"),
    @NamedQuery(name = "InfInformes.findByTipoPeriodicidad", query = "SELECT i FROM InfInformes i WHERE i.tipoPeriodicidad = :tipoPeriodicidad"),
    @NamedQuery(name = "InfInformes.findByNombreArchivo", query = "SELECT i FROM InfInformes i WHERE i.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "InfInformes.findByFechaProgramadaInicio", query = "SELECT i FROM InfInformes i WHERE i.fechaProgramadaInicio = :fechaProgramadaInicio"),
    @NamedQuery(name = "InfInformes.findByFechaProgramadoFin", query = "SELECT i FROM InfInformes i WHERE i.fechaProgramadoFin = :fechaProgramadoFin"),
    @NamedQuery(name = "InfInformes.findByCarpeta", query = "SELECT i FROM InfInformes i WHERE i.carpeta = :carpeta"),
    @NamedQuery(name = "InfInformes.findByMultipleGeneracion", query = "SELECT i FROM InfInformes i WHERE i.multipleGeneracion = :multipleGeneracion"),
    @NamedQuery(name = "InfInformes.findByMultipleEmpresa", query = "SELECT i FROM InfInformes i WHERE i.multipleEmpresa = :multipleEmpresa"),
    @NamedQuery(name = "InfInformes.findByMaeTipoFormatoId", query = "SELECT i FROM InfInformes i WHERE i.maeTipoFormatoId = :maeTipoFormatoId"),
    @NamedQuery(name = "InfInformes.findByMaeTipoFormatoCodigo", query = "SELECT i FROM InfInformes i WHERE i.maeTipoFormatoCodigo = :maeTipoFormatoCodigo"),
    @NamedQuery(name = "InfInformes.findByMaeTipoFormatoValor", query = "SELECT i FROM InfInformes i WHERE i.maeTipoFormatoValor = :maeTipoFormatoValor"),
    @NamedQuery(name = "InfInformes.findByUsuarioCrea", query = "SELECT i FROM InfInformes i WHERE i.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "InfInformes.findByTerminalCrea", query = "SELECT i FROM InfInformes i WHERE i.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "InfInformes.findByFechaHoraCrea", query = "SELECT i FROM InfInformes i WHERE i.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "InfInformes.findByUsuarioModifica", query = "SELECT i FROM InfInformes i WHERE i.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "InfInformes.findByTerminalModifica", query = "SELECT i FROM InfInformes i WHERE i.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "InfInformes.findByFechaHoraModifica", query = "SELECT i FROM InfInformes i WHERE i.fechaHoraModifica = :fechaHoraModifica")})
public class InfInformes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nombre_sp")
    private String nombreSp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "programado")
    private boolean programado;
    @Column(name = "requiere_aprobacion")
    private Boolean requiereAprobacion;
    @Column(name = "periodicidad")
    private Integer periodicidad;
    @Column(name = "tipo_periodicidad")
    private Integer tipoPeriodicidad;
    @Size(max = 255)
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @Column(name = "fecha_programada_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaProgramadaInicio;
    @Column(name = "fecha_programado_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaProgramadoFin;
    @Size(max = 32)
    @Column(name = "carpeta")
    private String carpeta;
    @Column(name = "multiple_generacion")
    private Boolean multipleGeneracion;
    @Column(name = "multiple_empresa")
    private Boolean multipleEmpresa;
    @Column(name = "mae_tipo_formato_id")
    private Integer maeTipoFormatoId;
    @Size(max = 16)
    @Column(name = "mae_tipo_formato_codigo")
    private String maeTipoFormatoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_formato_valor")
    private String maeTipoFormatoValor;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "infInformesId", fetch = FetchType.LAZY)
    private List<InfInformeVariables> infInformeVariablesList;
    @JoinColumn(name = "inf_grupos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private InfGrupos infGruposId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "infInformesId", fetch = FetchType.LAZY)
    private List<InfInformeGenerados> infInformeGeneradosList;

    public InfInformes() {
    }

    public InfInformes(Integer id) {
        this.id = id;
    }

    public InfInformes(Integer id, String nombre, String nombreSp, String descripcion, int estado, boolean activo, boolean programado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.nombreSp = nombreSp;
        this.descripcion = descripcion;
        this.estado = estado;
        this.activo = activo;
        this.programado = programado;
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

    public String getNombreSp() {
        return nombreSp;
    }

    public void setNombreSp(String nombreSp) {
        this.nombreSp = nombreSp;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean getProgramado() {
        return programado;
    }

    public void setProgramado(boolean programado) {
        this.programado = programado;
    }

    public Boolean getRequiereAprobacion() {
        return requiereAprobacion;
    }

    public void setRequiereAprobacion(Boolean requiereAprobacion) {
        this.requiereAprobacion = requiereAprobacion;
    }

    public Integer getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(Integer periodicidad) {
        this.periodicidad = periodicidad;
    }

    public Integer getTipoPeriodicidad() {
        return tipoPeriodicidad;
    }

    public void setTipoPeriodicidad(Integer tipoPeriodicidad) {
        this.tipoPeriodicidad = tipoPeriodicidad;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Date getFechaProgramadaInicio() {
        return fechaProgramadaInicio;
    }

    public void setFechaProgramadaInicio(Date fechaProgramadaInicio) {
        this.fechaProgramadaInicio = fechaProgramadaInicio;
    }

    public Date getFechaProgramadoFin() {
        return fechaProgramadoFin;
    }

    public void setFechaProgramadoFin(Date fechaProgramadoFin) {
        this.fechaProgramadoFin = fechaProgramadoFin;
    }

    public String getCarpeta() {
        return carpeta;
    }

    public void setCarpeta(String carpeta) {
        this.carpeta = carpeta;
    }

    public Boolean getMultipleGeneracion() {
        return multipleGeneracion;
    }

    public void setMultipleGeneracion(Boolean multipleGeneracion) {
        this.multipleGeneracion = multipleGeneracion;
    }

    public Boolean getMultipleEmpresa() {
        return multipleEmpresa;
    }

    public void setMultipleEmpresa(Boolean multipleEmpresa) {
        this.multipleEmpresa = multipleEmpresa;
    }

    public Integer getMaeTipoFormatoId() {
        return maeTipoFormatoId;
    }

    public void setMaeTipoFormatoId(Integer maeTipoFormatoId) {
        this.maeTipoFormatoId = maeTipoFormatoId;
    }

    public String getMaeTipoFormatoCodigo() {
        return maeTipoFormatoCodigo;
    }

    public void setMaeTipoFormatoCodigo(String maeTipoFormatoCodigo) {
        this.maeTipoFormatoCodigo = maeTipoFormatoCodigo;
    }

    public String getMaeTipoFormatoValor() {
        return maeTipoFormatoValor;
    }

    public void setMaeTipoFormatoValor(String maeTipoFormatoValor) {
        this.maeTipoFormatoValor = maeTipoFormatoValor;
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
    public List<InfInformeVariables> getInfInformeVariablesList() {
        return infInformeVariablesList;
    }

    public void setInfInformeVariablesList(List<InfInformeVariables> infInformeVariablesList) {
        this.infInformeVariablesList = infInformeVariablesList;
    }

    public InfGrupos getInfGruposId() {
        return infGruposId;
    }

    public void setInfGruposId(InfGrupos infGruposId) {
        this.infGruposId = infGruposId;
    }

    @XmlTransient
    public List<InfInformeGenerados> getInfInformeGeneradosList() {
        return infInformeGeneradosList;
    }

    public void setInfInformeGeneradosList(List<InfInformeGenerados> infInformeGeneradosList) {
        this.infInformeGeneradosList = infInformeGeneradosList;
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
        if (!(object instanceof InfInformes)) {
            return false;
        }
        InfInformes other = (InfInformes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.InfInformes[ id=" + id + " ]";
    }
    
}
