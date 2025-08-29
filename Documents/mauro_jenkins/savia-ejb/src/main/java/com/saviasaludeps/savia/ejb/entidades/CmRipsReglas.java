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
import javax.persistence.Lob;
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
@Table(name = "cm_rips_reglas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmRipsReglas.findAll", query = "SELECT c FROM CmRipsReglas c"),
    @NamedQuery(name = "CmRipsReglas.findById", query = "SELECT c FROM CmRipsReglas c WHERE c.id = :id"),
    @NamedQuery(name = "CmRipsReglas.findByTipo", query = "SELECT c FROM CmRipsReglas c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "CmRipsReglas.findByMaeCntTipoContratoCodigo", query = "SELECT c FROM CmRipsReglas c WHERE c.maeCntTipoContratoCodigo = :maeCntTipoContratoCodigo"),
    @NamedQuery(name = "CmRipsReglas.findByMaeCntTipoContratoId", query = "SELECT c FROM CmRipsReglas c WHERE c.maeCntTipoContratoId = :maeCntTipoContratoId"),
    @NamedQuery(name = "CmRipsReglas.findByMaeCntTipoContratoValor", query = "SELECT c FROM CmRipsReglas c WHERE c.maeCntTipoContratoValor = :maeCntTipoContratoValor"),
    @NamedQuery(name = "CmRipsReglas.findByNombre", query = "SELECT c FROM CmRipsReglas c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CmRipsReglas.findByDescripcion", query = "SELECT c FROM CmRipsReglas c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "CmRipsReglas.findByAlerta", query = "SELECT c FROM CmRipsReglas c WHERE c.alerta = :alerta"),
    @NamedQuery(name = "CmRipsReglas.findByOrden", query = "SELECT c FROM CmRipsReglas c WHERE c.orden = :orden"),
    @NamedQuery(name = "CmRipsReglas.findByFechaInicial", query = "SELECT c FROM CmRipsReglas c WHERE c.fechaInicial = :fechaInicial"),
    @NamedQuery(name = "CmRipsReglas.findByFechaFinal", query = "SELECT c FROM CmRipsReglas c WHERE c.fechaFinal = :fechaFinal"),
    @NamedQuery(name = "CmRipsReglas.findByActiva", query = "SELECT c FROM CmRipsReglas c WHERE c.activa = :activa"),
    @NamedQuery(name = "CmRipsReglas.findByArchivo", query = "SELECT c FROM CmRipsReglas c WHERE c.archivo = :archivo"),
    @NamedQuery(name = "CmRipsReglas.findByFuncion", query = "SELECT c FROM CmRipsReglas c WHERE c.funcion = :funcion"),
    @NamedQuery(name = "CmRipsReglas.findByBdEjecucion", query = "SELECT c FROM CmRipsReglas c WHERE c.bdEjecucion = :bdEjecucion"),
    @NamedQuery(name = "CmRipsReglas.findByUsuarioCrea", query = "SELECT c FROM CmRipsReglas c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmRipsReglas.findByTerminalCrea", query = "SELECT c FROM CmRipsReglas c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmRipsReglas.findByFechaHoraCrea", query = "SELECT c FROM CmRipsReglas c WHERE c.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "CmRipsReglas.findByUsuarioModifica", query = "SELECT c FROM CmRipsReglas c WHERE c.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "CmRipsReglas.findByTerminalModifica", query = "SELECT c FROM CmRipsReglas c WHERE c.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "CmRipsReglas.findByFechaHoraModifica", query = "SELECT c FROM CmRipsReglas c WHERE c.fechaHoraModifica = :fechaHoraModifica")})
public class CmRipsReglas implements Serializable {

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
    @Size(max = 8)
    @Column(name = "mae_cnt_tipo_contrato_codigo")
    private String maeCntTipoContratoCodigo;
    @Column(name = "mae_cnt_tipo_contrato_id")
    private Integer maeCntTipoContratoId;
    @Size(max = 128)
    @Column(name = "mae_cnt_tipo_contrato_valor")
    private String maeCntTipoContratoValor;
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
    @Column(name = "alerta")
    private int alerta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "orden")
    private int orden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicial")
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activa")
    private boolean activa;
    @Size(max = 45)
    @Column(name = "archivo")
    private String archivo;
    @Size(max = 64)
    @Column(name = "funcion")
    private String funcion;
    @Lob
    @Column(name = "json_regla")
    private byte[] jsonRegla;
    @Column(name = "bd_ejecucion")
    private Short bdEjecucion;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsReglasId", fetch = FetchType.LAZY)
    private List<CmRipsReglaEntradas> cmRipsReglaEntradasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmRipsReglasId", fetch = FetchType.LAZY)
    private List<CmRipsReglaSalidas> cmRipsReglaSalidasList;

    public CmRipsReglas() {
    }

    public CmRipsReglas(Integer id) {
        this.id = id;
    }

    public CmRipsReglas(Integer id, int tipo, String nombre, int alerta, int orden, Date fechaInicial, boolean activa, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.alerta = alerta;
        this.orden = orden;
        this.fechaInicial = fechaInicial;
        this.activa = activa;
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

    public String getMaeCntTipoContratoCodigo() {
        return maeCntTipoContratoCodigo;
    }

    public void setMaeCntTipoContratoCodigo(String maeCntTipoContratoCodigo) {
        this.maeCntTipoContratoCodigo = maeCntTipoContratoCodigo;
    }

    public Integer getMaeCntTipoContratoId() {
        return maeCntTipoContratoId;
    }

    public void setMaeCntTipoContratoId(Integer maeCntTipoContratoId) {
        this.maeCntTipoContratoId = maeCntTipoContratoId;
    }

    public String getMaeCntTipoContratoValor() {
        return maeCntTipoContratoValor;
    }

    public void setMaeCntTipoContratoValor(String maeCntTipoContratoValor) {
        this.maeCntTipoContratoValor = maeCntTipoContratoValor;
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

    public int getAlerta() {
        return alerta;
    }

    public void setAlerta(int alerta) {
        this.alerta = alerta;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public boolean getActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public byte[] getJsonRegla() {
        return jsonRegla;
    }

    public void setJsonRegla(byte[] jsonRegla) {
        this.jsonRegla = jsonRegla;
    }

    public Short getBdEjecucion() {
        return bdEjecucion;
    }

    public void setBdEjecucion(Short bdEjecucion) {
        this.bdEjecucion = bdEjecucion;
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
    public List<CmRipsReglaEntradas> getCmRipsReglaEntradasList() {
        return cmRipsReglaEntradasList;
    }

    public void setCmRipsReglaEntradasList(List<CmRipsReglaEntradas> cmRipsReglaEntradasList) {
        this.cmRipsReglaEntradasList = cmRipsReglaEntradasList;
    }

    @XmlTransient
    public List<CmRipsReglaSalidas> getCmRipsReglaSalidasList() {
        return cmRipsReglaSalidasList;
    }

    public void setCmRipsReglaSalidasList(List<CmRipsReglaSalidas> cmRipsReglaSalidasList) {
        this.cmRipsReglaSalidasList = cmRipsReglaSalidasList;
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
        if (!(object instanceof CmRipsReglas)) {
            return false;
        }
        CmRipsReglas other = (CmRipsReglas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmRipsReglas[ id=" + id + " ]";
    }
    
}
