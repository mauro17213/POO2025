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
@Table(name = "au_grupos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuGrupos.findAll", query = "SELECT a FROM AuGrupos a"),
    @NamedQuery(name = "AuGrupos.findById", query = "SELECT a FROM AuGrupos a WHERE a.id = :id"),
    @NamedQuery(name = "AuGrupos.findByNombre", query = "SELECT a FROM AuGrupos a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "AuGrupos.findByDescripcion", query = "SELECT a FROM AuGrupos a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "AuGrupos.findByOrden", query = "SELECT a FROM AuGrupos a WHERE a.orden = :orden"),
    @NamedQuery(name = "AuGrupos.findByActivo", query = "SELECT a FROM AuGrupos a WHERE a.activo = :activo"),
    @NamedQuery(name = "AuGrupos.findBySoloGrupo", query = "SELECT a FROM AuGrupos a WHERE a.soloGrupo = :soloGrupo"),
    @NamedQuery(name = "AuGrupos.findByUltimoUsuarioMedicoId", query = "SELECT a FROM AuGrupos a WHERE a.ultimoUsuarioMedicoId = :ultimoUsuarioMedicoId"),
    @NamedQuery(name = "AuGrupos.findByUltimoUsuarioEnfermeroId", query = "SELECT a FROM AuGrupos a WHERE a.ultimoUsuarioEnfermeroId = :ultimoUsuarioEnfermeroId"),
    @NamedQuery(name = "AuGrupos.findByUltimoUsuarioAuxiliarId", query = "SELECT a FROM AuGrupos a WHERE a.ultimoUsuarioAuxiliarId = :ultimoUsuarioAuxiliarId"),
    @NamedQuery(name = "AuGrupos.findByUltimoUsuarioOdontologoId", query = "SELECT a FROM AuGrupos a WHERE a.ultimoUsuarioOdontologoId = :ultimoUsuarioOdontologoId"),
    @NamedQuery(name = "AuGrupos.findByUltimoUsuarioAuxiliarOralId", query = "SELECT a FROM AuGrupos a WHERE a.ultimoUsuarioAuxiliarOralId = :ultimoUsuarioAuxiliarOralId"),
    @NamedQuery(name = "AuGrupos.findByMaeAmbitoId", query = "SELECT a FROM AuGrupos a WHERE a.maeAmbitoId = :maeAmbitoId"),
    @NamedQuery(name = "AuGrupos.findByMaeAmbitoCodigo", query = "SELECT a FROM AuGrupos a WHERE a.maeAmbitoCodigo = :maeAmbitoCodigo"),
    @NamedQuery(name = "AuGrupos.findByMaeAmbitoValor", query = "SELECT a FROM AuGrupos a WHERE a.maeAmbitoValor = :maeAmbitoValor"),
    @NamedQuery(name = "AuGrupos.findByTutela", query = "SELECT a FROM AuGrupos a WHERE a.tutela = :tutela"),
    @NamedQuery(name = "AuGrupos.findByPbs", query = "SELECT a FROM AuGrupos a WHERE a.pbs = :pbs"),
    @NamedQuery(name = "AuGrupos.findByGenerico", query = "SELECT a FROM AuGrupos a WHERE a.generico = :generico"),
    @NamedQuery(name = "AuGrupos.findByEsTecnologia", query = "SELECT a FROM AuGrupos a WHERE a.esTecnologia = :esTecnologia"),
    @NamedQuery(name = "AuGrupos.findByEsInsumo", query = "SELECT a FROM AuGrupos a WHERE a.esInsumo = :esInsumo"),
    @NamedQuery(name = "AuGrupos.findByEsMedicamento", query = "SELECT a FROM AuGrupos a WHERE a.esMedicamento = :esMedicamento"),
    @NamedQuery(name = "AuGrupos.findByEsPaquete", query = "SELECT a FROM AuGrupos a WHERE a.esPaquete = :esPaquete"),
    @NamedQuery(name = "AuGrupos.findByAplicaSeguimiento", query = "SELECT a FROM AuGrupos a WHERE a.aplicaSeguimiento = :aplicaSeguimiento"),
    @NamedQuery(name = "AuGrupos.findByUsuarioCrea", query = "SELECT a FROM AuGrupos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuGrupos.findByTerminalCrea", query = "SELECT a FROM AuGrupos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuGrupos.findByFechaHoraCrea", query = "SELECT a FROM AuGrupos a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuGrupos.findByUsuarioModifica", query = "SELECT a FROM AuGrupos a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuGrupos.findByTerminalModifica", query = "SELECT a FROM AuGrupos a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuGrupos.findByFechaHoraModifica", query = "SELECT a FROM AuGrupos a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AuGrupos implements Serializable {

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
    @Size(max = 512)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "orden")
    private int orden;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "solo_grupo")
    private boolean soloGrupo;
    @Column(name = "ultimo_usuario_medico_id")
    private Integer ultimoUsuarioMedicoId;
    @Column(name = "ultimo_usuario_enfermero_id")
    private Integer ultimoUsuarioEnfermeroId;
    @Column(name = "ultimo_usuario_auxiliar_id")
    private Integer ultimoUsuarioAuxiliarId;
    @Column(name = "ultimo_usuario_odontologo_id")
    private Integer ultimoUsuarioOdontologoId;
    @Column(name = "ultimo_usuario_auxiliar_oral_id")
    private Integer ultimoUsuarioAuxiliarOralId;
    @Column(name = "mae_ambito_id")
    private Integer maeAmbitoId;
    @Size(max = 8)
    @Column(name = "mae_ambito_codigo")
    private String maeAmbitoCodigo;
    @Size(max = 128)
    @Column(name = "mae_ambito_valor")
    private String maeAmbitoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tutela")
    private boolean tutela;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pbs")
    private boolean pbs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "generico")
    private boolean generico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "es_tecnologia")
    private boolean esTecnologia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "es_insumo")
    private boolean esInsumo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "es_medicamento")
    private boolean esMedicamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "es_paquete")
    private boolean esPaquete;
    @Column(name = "aplica_seguimiento")
    private Boolean aplicaSeguimiento;
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
    @OneToMany(mappedBy = "auGruposId", fetch = FetchType.LAZY)
    private List<GnUsuarios> gnUsuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auGruposId", fetch = FetchType.LAZY)
    private List<AuGrupoProgramas> auGrupoProgramasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auGruposId", fetch = FetchType.LAZY)
    private List<AuGrupoDiagnosticos> auGrupoDiagnosticosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auGruposId", fetch = FetchType.LAZY)
    private List<AuGrupoSedes> auGrupoSedesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auGruposId", fetch = FetchType.LAZY)
    private List<AuGrupoTecnologias> auGrupoTecnologiasList;
    @OneToMany(mappedBy = "auGruposId", fetch = FetchType.LAZY)
    private List<AuAnexo3Items> auAnexo3ItemsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auGruposId", fetch = FetchType.LAZY)
    private List<AuGrupoRegiones> auGrupoRegionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auGruposId", fetch = FetchType.LAZY)
    private List<AuGrupoUsuarios> auGrupoUsuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auGruposId", fetch = FetchType.LAZY)
    private List<AuSeguimientos> auSeguimientosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auGruposId", fetch = FetchType.LAZY)
    private List<AuGrupoHistoricos> auGrupoHistoricosList;

    public AuGrupos() {
    }

    public AuGrupos(Integer id) {
        this.id = id;
    }

    public AuGrupos(Integer id, String nombre, int orden, boolean activo, boolean soloGrupo, boolean tutela, boolean pbs, boolean generico, boolean esTecnologia, boolean esInsumo, boolean esMedicamento, boolean esPaquete, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.nombre = nombre;
        this.orden = orden;
        this.activo = activo;
        this.soloGrupo = soloGrupo;
        this.tutela = tutela;
        this.pbs = pbs;
        this.generico = generico;
        this.esTecnologia = esTecnologia;
        this.esInsumo = esInsumo;
        this.esMedicamento = esMedicamento;
        this.esPaquete = esPaquete;
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

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean getSoloGrupo() {
        return soloGrupo;
    }

    public void setSoloGrupo(boolean soloGrupo) {
        this.soloGrupo = soloGrupo;
    }

    public Integer getUltimoUsuarioMedicoId() {
        return ultimoUsuarioMedicoId;
    }

    public void setUltimoUsuarioMedicoId(Integer ultimoUsuarioMedicoId) {
        this.ultimoUsuarioMedicoId = ultimoUsuarioMedicoId;
    }

    public Integer getUltimoUsuarioEnfermeroId() {
        return ultimoUsuarioEnfermeroId;
    }

    public void setUltimoUsuarioEnfermeroId(Integer ultimoUsuarioEnfermeroId) {
        this.ultimoUsuarioEnfermeroId = ultimoUsuarioEnfermeroId;
    }

    public Integer getUltimoUsuarioAuxiliarId() {
        return ultimoUsuarioAuxiliarId;
    }

    public void setUltimoUsuarioAuxiliarId(Integer ultimoUsuarioAuxiliarId) {
        this.ultimoUsuarioAuxiliarId = ultimoUsuarioAuxiliarId;
    }

    public Integer getUltimoUsuarioOdontologoId() {
        return ultimoUsuarioOdontologoId;
    }

    public void setUltimoUsuarioOdontologoId(Integer ultimoUsuarioOdontologoId) {
        this.ultimoUsuarioOdontologoId = ultimoUsuarioOdontologoId;
    }

    public Integer getUltimoUsuarioAuxiliarOralId() {
        return ultimoUsuarioAuxiliarOralId;
    }

    public void setUltimoUsuarioAuxiliarOralId(Integer ultimoUsuarioAuxiliarOralId) {
        this.ultimoUsuarioAuxiliarOralId = ultimoUsuarioAuxiliarOralId;
    }

    public Integer getMaeAmbitoId() {
        return maeAmbitoId;
    }

    public void setMaeAmbitoId(Integer maeAmbitoId) {
        this.maeAmbitoId = maeAmbitoId;
    }

    public String getMaeAmbitoCodigo() {
        return maeAmbitoCodigo;
    }

    public void setMaeAmbitoCodigo(String maeAmbitoCodigo) {
        this.maeAmbitoCodigo = maeAmbitoCodigo;
    }

    public String getMaeAmbitoValor() {
        return maeAmbitoValor;
    }

    public void setMaeAmbitoValor(String maeAmbitoValor) {
        this.maeAmbitoValor = maeAmbitoValor;
    }

    public boolean getTutela() {
        return tutela;
    }

    public void setTutela(boolean tutela) {
        this.tutela = tutela;
    }

    public boolean getPbs() {
        return pbs;
    }

    public void setPbs(boolean pbs) {
        this.pbs = pbs;
    }

    public boolean getGenerico() {
        return generico;
    }

    public void setGenerico(boolean generico) {
        this.generico = generico;
    }

    public boolean getEsTecnologia() {
        return esTecnologia;
    }

    public void setEsTecnologia(boolean esTecnologia) {
        this.esTecnologia = esTecnologia;
    }

    public boolean getEsInsumo() {
        return esInsumo;
    }

    public void setEsInsumo(boolean esInsumo) {
        this.esInsumo = esInsumo;
    }

    public boolean getEsMedicamento() {
        return esMedicamento;
    }

    public void setEsMedicamento(boolean esMedicamento) {
        this.esMedicamento = esMedicamento;
    }

    public boolean getEsPaquete() {
        return esPaquete;
    }

    public void setEsPaquete(boolean esPaquete) {
        this.esPaquete = esPaquete;
    }

    public Boolean getAplicaSeguimiento() {
        return aplicaSeguimiento;
    }

    public void setAplicaSeguimiento(Boolean aplicaSeguimiento) {
        this.aplicaSeguimiento = aplicaSeguimiento;
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
    public List<GnUsuarios> getGnUsuariosList() {
        return gnUsuariosList;
    }

    public void setGnUsuariosList(List<GnUsuarios> gnUsuariosList) {
        this.gnUsuariosList = gnUsuariosList;
    }

    @XmlTransient
    public List<AuGrupoProgramas> getAuGrupoProgramasList() {
        return auGrupoProgramasList;
    }

    public void setAuGrupoProgramasList(List<AuGrupoProgramas> auGrupoProgramasList) {
        this.auGrupoProgramasList = auGrupoProgramasList;
    }

    @XmlTransient
    public List<AuGrupoDiagnosticos> getAuGrupoDiagnosticosList() {
        return auGrupoDiagnosticosList;
    }

    public void setAuGrupoDiagnosticosList(List<AuGrupoDiagnosticos> auGrupoDiagnosticosList) {
        this.auGrupoDiagnosticosList = auGrupoDiagnosticosList;
    }

    @XmlTransient
    public List<AuGrupoSedes> getAuGrupoSedesList() {
        return auGrupoSedesList;
    }

    public void setAuGrupoSedesList(List<AuGrupoSedes> auGrupoSedesList) {
        this.auGrupoSedesList = auGrupoSedesList;
    }

    @XmlTransient
    public List<AuGrupoTecnologias> getAuGrupoTecnologiasList() {
        return auGrupoTecnologiasList;
    }

    public void setAuGrupoTecnologiasList(List<AuGrupoTecnologias> auGrupoTecnologiasList) {
        this.auGrupoTecnologiasList = auGrupoTecnologiasList;
    }

    @XmlTransient
    public List<AuAnexo3Items> getAuAnexo3ItemsList() {
        return auAnexo3ItemsList;
    }

    public void setAuAnexo3ItemsList(List<AuAnexo3Items> auAnexo3ItemsList) {
        this.auAnexo3ItemsList = auAnexo3ItemsList;
    }

    @XmlTransient
    public List<AuGrupoRegiones> getAuGrupoRegionesList() {
        return auGrupoRegionesList;
    }

    public void setAuGrupoRegionesList(List<AuGrupoRegiones> auGrupoRegionesList) {
        this.auGrupoRegionesList = auGrupoRegionesList;
    }

    @XmlTransient
    public List<AuGrupoUsuarios> getAuGrupoUsuariosList() {
        return auGrupoUsuariosList;
    }

    public void setAuGrupoUsuariosList(List<AuGrupoUsuarios> auGrupoUsuariosList) {
        this.auGrupoUsuariosList = auGrupoUsuariosList;
    }

    @XmlTransient
    public List<AuSeguimientos> getAuSeguimientosList() {
        return auSeguimientosList;
    }

    public void setAuSeguimientosList(List<AuSeguimientos> auSeguimientosList) {
        this.auSeguimientosList = auSeguimientosList;
    }

    @XmlTransient
    public List<AuGrupoHistoricos> getAuGrupoHistoricosList() {
        return auGrupoHistoricosList;
    }

    public void setAuGrupoHistoricosList(List<AuGrupoHistoricos> auGrupoHistoricosList) {
        this.auGrupoHistoricosList = auGrupoHistoricosList;
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
        if (!(object instanceof AuGrupos)) {
            return false;
        }
        AuGrupos other = (AuGrupos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuGrupos[ id=" + id + " ]";
    }
    
}
