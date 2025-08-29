/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
@Table(name = "pe_cargas_variables")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeCargasVariables.findAll", query = "SELECT p FROM PeCargasVariables p"),
    @NamedQuery(name = "PeCargasVariables.findById", query = "SELECT p FROM PeCargasVariables p WHERE p.id = :id"),
    @NamedQuery(name = "PeCargasVariables.findByNombre", query = "SELECT p FROM PeCargasVariables p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "PeCargasVariables.findByRuta", query = "SELECT p FROM PeCargasVariables p WHERE p.ruta = :ruta"),
    @NamedQuery(name = "PeCargasVariables.findByArchivo", query = "SELECT p FROM PeCargasVariables p WHERE p.archivo = :archivo"),
    @NamedQuery(name = "PeCargasVariables.findByExiste", query = "SELECT p FROM PeCargasVariables p WHERE p.existe = :existe"),
    @NamedQuery(name = "PeCargasVariables.findByRespNombre", query = "SELECT p FROM PeCargasVariables p WHERE p.respNombre = :respNombre"),
    @NamedQuery(name = "PeCargasVariables.findByRespRuta", query = "SELECT p FROM PeCargasVariables p WHERE p.respRuta = :respRuta"),
    @NamedQuery(name = "PeCargasVariables.findByRespArchivo", query = "SELECT p FROM PeCargasVariables p WHERE p.respArchivo = :respArchivo"),
    @NamedQuery(name = "PeCargasVariables.findByRespExiste", query = "SELECT p FROM PeCargasVariables p WHERE p.respExiste = :respExiste"),
    @NamedQuery(name = "PeCargasVariables.findByEstado", query = "SELECT p FROM PeCargasVariables p WHERE p.estado = :estado"),
    @NamedQuery(name = "PeCargasVariables.findByRegistros", query = "SELECT p FROM PeCargasVariables p WHERE p.registros = :registros"),
    @NamedQuery(name = "PeCargasVariables.findByExitosos", query = "SELECT p FROM PeCargasVariables p WHERE p.exitosos = :exitosos"),
    @NamedQuery(name = "PeCargasVariables.findByFallidos", query = "SELECT p FROM PeCargasVariables p WHERE p.fallidos = :fallidos"),
    @NamedQuery(name = "PeCargasVariables.findByDetalle", query = "SELECT p FROM PeCargasVariables p WHERE p.detalle = :detalle"),
    @NamedQuery(name = "PeCargasVariables.findByPeriodoCargue", query = "SELECT p FROM PeCargasVariables p WHERE p.periodoCargue = :periodoCargue"),
    @NamedQuery(name = "PeCargasVariables.findByFechaHoraInicio", query = "SELECT p FROM PeCargasVariables p WHERE p.fechaHoraInicio = :fechaHoraInicio"),
    @NamedQuery(name = "PeCargasVariables.findByFechaHoraFin", query = "SELECT p FROM PeCargasVariables p WHERE p.fechaHoraFin = :fechaHoraFin"),
    @NamedQuery(name = "PeCargasVariables.findByUsuarioCrea", query = "SELECT p FROM PeCargasVariables p WHERE p.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "PeCargasVariables.findByTerminalCrea", query = "SELECT p FROM PeCargasVariables p WHERE p.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "PeCargasVariables.findByFechaHoraCrea", query = "SELECT p FROM PeCargasVariables p WHERE p.fechaHoraCrea = :fechaHoraCrea")})
public class PeCargasVariables implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "nombre")
    private String nombre;
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
    @Size(max = 256)
    @Column(name = "resp_nombre")
    private String respNombre;
    @Size(max = 512)
    @Column(name = "resp_ruta")
    private String respRuta;
    @Size(max = 128)
    @Column(name = "resp_archivo")
    private String respArchivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "resp_existe")
    private boolean respExiste;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Column(name = "registros")
    private Integer registros;
    @Column(name = "exitosos")
    private Integer exitosos;
    @Column(name = "fallidos")
    private Integer fallidos;
    @Size(max = 512)
    @Column(name = "detalle")
    private String detalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "periodo_cargue")
    private int periodoCargue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;
    @Column(name = "fecha_hora_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;
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
    @JoinColumn(name = "gn_empresas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnEmpresas gnEmpresasId;
    @JoinColumn(name = "pe_programas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PeProgramas peProgramasId;
    @OneToMany(mappedBy = "peCargasVariablesId", fetch = FetchType.LAZY)
    private List<PeVariablesValores> peVariablesValoresList;
    @OneToMany(mappedBy = "peCargasVariablesId", fetch = FetchType.LAZY)
    private List<PeVariablesValoresHistoricos> peVariablesValoresHistoricosList;

    public PeCargasVariables() {
    }

    public PeCargasVariables(Integer id) {
        this.id = id;
    }

    public PeCargasVariables(Integer id, String nombre, String ruta, String archivo, boolean existe, boolean respExiste, int estado, int periodoCargue, Date fechaHoraInicio, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.ruta = ruta;
        this.archivo = archivo;
        this.existe = existe;
        this.respExiste = respExiste;
        this.estado = estado;
        this.periodoCargue = periodoCargue;
        this.fechaHoraInicio = fechaHoraInicio;
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

    public String getRespNombre() {
        return respNombre;
    }

    public void setRespNombre(String respNombre) {
        this.respNombre = respNombre;
    }

    public String getRespRuta() {
        return respRuta;
    }

    public void setRespRuta(String respRuta) {
        this.respRuta = respRuta;
    }

    public String getRespArchivo() {
        return respArchivo;
    }

    public void setRespArchivo(String respArchivo) {
        this.respArchivo = respArchivo;
    }

    public boolean getRespExiste() {
        return respExiste;
    }

    public void setRespExiste(boolean respExiste) {
        this.respExiste = respExiste;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getPeriodoCargue() {
        return periodoCargue;
    }

    public void setPeriodoCargue(int periodoCargue) {
        this.periodoCargue = periodoCargue;
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

    public GnEmpresas getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(GnEmpresas gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
    }

    public PeProgramas getPeProgramasId() {
        return peProgramasId;
    }

    public void setPeProgramasId(PeProgramas peProgramasId) {
        this.peProgramasId = peProgramasId;
    }

    @XmlTransient
    public List<PeVariablesValores> getPeVariablesValoresList() {
        return peVariablesValoresList;
    }

    public void setPeVariablesValoresList(List<PeVariablesValores> peVariablesValoresList) {
        this.peVariablesValoresList = peVariablesValoresList;
    }

    @XmlTransient
    public List<PeVariablesValoresHistoricos> getPeVariablesValoresHistoricosList() {
        return peVariablesValoresHistoricosList;
    }

    public void setPeVariablesValoresHistoricosList(List<PeVariablesValoresHistoricos> peVariablesValoresHistoricosList) {
        this.peVariablesValoresHistoricosList = peVariablesValoresHistoricosList;
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
        if (!(object instanceof PeCargasVariables)) {
            return false;
        }
        PeCargasVariables other = (PeCargasVariables) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.PeCargasVariables[ id=" + id + " ]";
    }
    
}
