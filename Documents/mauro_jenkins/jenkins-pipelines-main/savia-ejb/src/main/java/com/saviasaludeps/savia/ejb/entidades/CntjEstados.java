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
@Table(name = "cntj_estados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CntjEstados.findAll", query = "SELECT c FROM CntjEstados c"),
    @NamedQuery(name = "CntjEstados.findById", query = "SELECT c FROM CntjEstados c WHERE c.id = :id"),
    @NamedQuery(name = "CntjEstados.findByTipo", query = "SELECT c FROM CntjEstados c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "CntjEstados.findByNombre", query = "SELECT c FROM CntjEstados c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CntjEstados.findByDescripcion", query = "SELECT c FROM CntjEstados c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CntjEstados.findByActivo", query = "SELECT c FROM CntjEstados c WHERE c.activo = :activo"),
    @NamedQuery(name = "CntjEstados.findByModificaFecha", query = "SELECT c FROM CntjEstados c WHERE c.modificaFecha = :modificaFecha"),
    @NamedQuery(name = "CntjEstados.findByModificaDatos", query = "SELECT c FROM CntjEstados c WHERE c.modificaDatos = :modificaDatos"),
    @NamedQuery(name = "CntjEstados.findByValidaGrupo", query = "SELECT c FROM CntjEstados c WHERE c.validaGrupo = :validaGrupo"),
    @NamedQuery(name = "CntjEstados.findByResultadoComite", query = "SELECT c FROM CntjEstados c WHERE c.resultadoComite = :resultadoComite"),
    @NamedQuery(name = "CntjEstados.findByUsuarioCrea", query = "SELECT c FROM CntjEstados c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CntjEstados.findByTerminalCrea", query = "SELECT c FROM CntjEstados c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CntjEstados.findByFechaHoraCrea", query = "SELECT c FROM CntjEstados c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CntjEstados.findByUsuarioModifica", query = "SELECT c FROM CntjEstados c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CntjEstados.findByTerminalModifica", query = "SELECT c FROM CntjEstados c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CntjEstados.findByFechaHoraModifica", query = "SELECT c FROM CntjEstados c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CntjEstados implements Serializable {

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
    @Size(min = 1, max = 64)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 512)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "modifica_fecha")
    private boolean modificaFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "modifica_datos")
    private boolean modificaDatos;
    @Column(name = "valida_grupo")
    private Boolean validaGrupo;
    @Column(name = "resultado_comite")
    private Integer resultadoComite;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjEstadosId", fetch = FetchType.LAZY)
    private List<CntjEstadoPlantillas> cntjEstadoPlantillasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjEstadosId", fetch = FetchType.LAZY)
    private List<CntjEstadoEjecuciones> cntjEstadoEjecucionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjEstadosId", fetch = FetchType.LAZY)
    private List<CntjTransiciones> cntjTransicionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjEstadosId", fetch = FetchType.LAZY)
    private List<CntjEstadoUsuarios> cntjEstadoUsuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjEstadosId", fetch = FetchType.LAZY)
    private List<CntjEstadoProcesoDocumentos> cntjEstadoProcesoDocumentosList;
    @OneToMany(mappedBy = "cntjEstadosActualId", fetch = FetchType.LAZY)
    private List<CntjExpedientes> cntjExpedientesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cntjEstadosId", fetch = FetchType.LAZY)
    private List<CntjEstadoGrupos> cntjEstadoGruposList;
    @JoinColumn(name = "cntj_procesos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CntjProcesos cntjProcesosId;
    @JoinColumn(name = "cntj_transiciones_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntjTransiciones cntjTransicionesId;

    public CntjEstados() {
    }

    public CntjEstados(Integer id) {
        this.id = id;
    }

    public CntjEstados(Integer id, int tipo, String nombre, boolean activo, boolean modificaFecha, boolean modificaDatos, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.activo = activo;
        this.modificaFecha = modificaFecha;
        this.modificaDatos = modificaDatos;
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

    public boolean getModificaFecha() {
        return modificaFecha;
    }

    public void setModificaFecha(boolean modificaFecha) {
        this.modificaFecha = modificaFecha;
    }

    public boolean getModificaDatos() {
        return modificaDatos;
    }

    public void setModificaDatos(boolean modificaDatos) {
        this.modificaDatos = modificaDatos;
    }

    public Boolean getValidaGrupo() {
        return validaGrupo;
    }

    public void setValidaGrupo(Boolean validaGrupo) {
        this.validaGrupo = validaGrupo;
    }

    public Integer getResultadoComite() {
        return resultadoComite;
    }

    public void setResultadoComite(Integer resultadoComite) {
        this.resultadoComite = resultadoComite;
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
    public List<CntjEstadoPlantillas> getCntjEstadoPlantillasList() {
        return cntjEstadoPlantillasList;
    }

    public void setCntjEstadoPlantillasList(List<CntjEstadoPlantillas> cntjEstadoPlantillasList) {
        this.cntjEstadoPlantillasList = cntjEstadoPlantillasList;
    }

    @XmlTransient
    public List<CntjEstadoEjecuciones> getCntjEstadoEjecucionesList() {
        return cntjEstadoEjecucionesList;
    }

    public void setCntjEstadoEjecucionesList(List<CntjEstadoEjecuciones> cntjEstadoEjecucionesList) {
        this.cntjEstadoEjecucionesList = cntjEstadoEjecucionesList;
    }

    @XmlTransient
    public List<CntjTransiciones> getCntjTransicionesList() {
        return cntjTransicionesList;
    }

    public void setCntjTransicionesList(List<CntjTransiciones> cntjTransicionesList) {
        this.cntjTransicionesList = cntjTransicionesList;
    }

    @XmlTransient
    public List<CntjEstadoUsuarios> getCntjEstadoUsuariosList() {
        return cntjEstadoUsuariosList;
    }

    public void setCntjEstadoUsuariosList(List<CntjEstadoUsuarios> cntjEstadoUsuariosList) {
        this.cntjEstadoUsuariosList = cntjEstadoUsuariosList;
    }

    @XmlTransient
    public List<CntjEstadoProcesoDocumentos> getCntjEstadoProcesoDocumentosList() {
        return cntjEstadoProcesoDocumentosList;
    }

    public void setCntjEstadoProcesoDocumentosList(List<CntjEstadoProcesoDocumentos> cntjEstadoProcesoDocumentosList) {
        this.cntjEstadoProcesoDocumentosList = cntjEstadoProcesoDocumentosList;
    }

    @XmlTransient
    public List<CntjExpedientes> getCntjExpedientesList() {
        return cntjExpedientesList;
    }

    public void setCntjExpedientesList(List<CntjExpedientes> cntjExpedientesList) {
        this.cntjExpedientesList = cntjExpedientesList;
    }

    @XmlTransient
    public List<CntjEstadoGrupos> getCntjEstadoGruposList() {
        return cntjEstadoGruposList;
    }

    public void setCntjEstadoGruposList(List<CntjEstadoGrupos> cntjEstadoGruposList) {
        this.cntjEstadoGruposList = cntjEstadoGruposList;
    }

    public CntjProcesos getCntjProcesosId() {
        return cntjProcesosId;
    }

    public void setCntjProcesosId(CntjProcesos cntjProcesosId) {
        this.cntjProcesosId = cntjProcesosId;
    }

    public CntjTransiciones getCntjTransicionesId() {
        return cntjTransicionesId;
    }

    public void setCntjTransicionesId(CntjTransiciones cntjTransicionesId) {
        this.cntjTransicionesId = cntjTransicionesId;
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
        if (!(object instanceof CntjEstados)) {
            return false;
        }
        CntjEstados other = (CntjEstados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CntjEstados[ id=" + id + " ]";
    }
    
}
