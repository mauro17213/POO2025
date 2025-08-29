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
@Table(name = "au_grupo_tecnologias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuGrupoTecnologias.findAll", query = "SELECT a FROM AuGrupoTecnologias a"),
    @NamedQuery(name = "AuGrupoTecnologias.findById", query = "SELECT a FROM AuGrupoTecnologias a WHERE a.id = :id"),
    @NamedQuery(name = "AuGrupoTecnologias.findByTipoTecnologia", query = "SELECT a FROM AuGrupoTecnologias a WHERE a.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "AuGrupoTecnologias.findByMaTecnologiaId", query = "SELECT a FROM AuGrupoTecnologias a WHERE a.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "AuGrupoTecnologias.findByMaTecnologiaCodigo", query = "SELECT a FROM AuGrupoTecnologias a WHERE a.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "AuGrupoTecnologias.findByMaTecnologiasValor", query = "SELECT a FROM AuGrupoTecnologias a WHERE a.maTecnologiasValor = :maTecnologiasValor"),
    @NamedQuery(name = "AuGrupoTecnologias.findByTipo", query = "SELECT a FROM AuGrupoTecnologias a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "AuGrupoTecnologias.findByNivelComplejidad", query = "SELECT a FROM AuGrupoTecnologias a WHERE a.nivelComplejidad = :nivelComplejidad"),
    @NamedQuery(name = "AuGrupoTecnologias.findByAplicaSeguimiento", query = "SELECT a FROM AuGrupoTecnologias a WHERE a.aplicaSeguimiento = :aplicaSeguimiento"),
    @NamedQuery(name = "AuGrupoTecnologias.findByMaeSeguimientoServicioId", query = "SELECT a FROM AuGrupoTecnologias a WHERE a.maeSeguimientoServicioId = :maeSeguimientoServicioId"),
    @NamedQuery(name = "AuGrupoTecnologias.findByMaeSeguimientoServicioCodigo", query = "SELECT a FROM AuGrupoTecnologias a WHERE a.maeSeguimientoServicioCodigo = :maeSeguimientoServicioCodigo"),
    @NamedQuery(name = "AuGrupoTecnologias.findByMaeSeguimientoServicioValor", query = "SELECT a FROM AuGrupoTecnologias a WHERE a.maeSeguimientoServicioValor = :maeSeguimientoServicioValor"),
    @NamedQuery(name = "AuGrupoTecnologias.findByMaeTipoAuditorId", query = "SELECT a FROM AuGrupoTecnologias a WHERE a.maeTipoAuditorId = :maeTipoAuditorId"),
    @NamedQuery(name = "AuGrupoTecnologias.findByMaeTipoAuditorCodigo", query = "SELECT a FROM AuGrupoTecnologias a WHERE a.maeTipoAuditorCodigo = :maeTipoAuditorCodigo"),
    @NamedQuery(name = "AuGrupoTecnologias.findByMaeTipoAuditorValor", query = "SELECT a FROM AuGrupoTecnologias a WHERE a.maeTipoAuditorValor = :maeTipoAuditorValor"),
    @NamedQuery(name = "AuGrupoTecnologias.findByMaeMedicamentoId", query = "SELECT a FROM AuGrupoTecnologias a WHERE a.maeMedicamentoId = :maeMedicamentoId"),
    @NamedQuery(name = "AuGrupoTecnologias.findByMaeMedicamentoCodigo", query = "SELECT a FROM AuGrupoTecnologias a WHERE a.maeMedicamentoCodigo = :maeMedicamentoCodigo"),
    @NamedQuery(name = "AuGrupoTecnologias.findByMaeMedicamentoValor", query = "SELECT a FROM AuGrupoTecnologias a WHERE a.maeMedicamentoValor = :maeMedicamentoValor"),
    @NamedQuery(name = "AuGrupoTecnologias.findByUsuarioCrea", query = "SELECT a FROM AuGrupoTecnologias a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuGrupoTecnologias.findByTerminalCrea", query = "SELECT a FROM AuGrupoTecnologias a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuGrupoTecnologias.findByFechaHoraCrea", query = "SELECT a FROM AuGrupoTecnologias a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuGrupoTecnologias.findByUsuarioModifica", query = "SELECT a FROM AuGrupoTecnologias a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuGrupoTecnologias.findByTerminalModifica", query = "SELECT a FROM AuGrupoTecnologias a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuGrupoTecnologias.findByFechaHoraModifica", query = "SELECT a FROM AuGrupoTecnologias a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AuGrupoTecnologias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_tecnologia")
    private int tipoTecnologia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ma_tecnologia_id")
    private int maTecnologiaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "ma_tecnologia_codigo")
    private String maTecnologiaCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4256)
    @Column(name = "ma_tecnologias_valor")
    private String maTecnologiasValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nivel_complejidad")
    private int nivelComplejidad;
    @Column(name = "aplica_seguimiento")
    private Boolean aplicaSeguimiento;
    @Column(name = "mae_seguimiento_servicio_id")
    private Integer maeSeguimientoServicioId;
    @Size(max = 8)
    @Column(name = "mae_seguimiento_servicio_codigo")
    private String maeSeguimientoServicioCodigo;
    @Size(max = 128)
    @Column(name = "mae_seguimiento_servicio_valor")
    private String maeSeguimientoServicioValor;
    @Column(name = "mae_tipo_auditor_id")
    private Integer maeTipoAuditorId;
    @Size(max = 8)
    @Column(name = "mae_tipo_auditor_codigo")
    private String maeTipoAuditorCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_auditor_valor")
    private String maeTipoAuditorValor;
    @Column(name = "mae_medicamento_id")
    private Integer maeMedicamentoId;
    @Size(max = 32)
    @Column(name = "mae_medicamento_codigo")
    private String maeMedicamentoCodigo;
    @Size(max = 512)
    @Column(name = "mae_medicamento_valor")
    private String maeMedicamentoValor;
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
    @JoinColumn(name = "au_grupos_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuGrupos auGruposId;

    public AuGrupoTecnologias() {
    }

    public AuGrupoTecnologias(Integer id) {
        this.id = id;
    }

    public AuGrupoTecnologias(Integer id, int tipoTecnologia, int maTecnologiaId, String maTecnologiaCodigo, String maTecnologiasValor, int tipo, int nivelComplejidad, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipoTecnologia = tipoTecnologia;
        this.maTecnologiaId = maTecnologiaId;
        this.maTecnologiaCodigo = maTecnologiaCodigo;
        this.maTecnologiasValor = maTecnologiasValor;
        this.tipo = tipo;
        this.nivelComplejidad = nivelComplejidad;
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

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public int getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(int maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public String getMaTecnologiasValor() {
        return maTecnologiasValor;
    }

    public void setMaTecnologiasValor(String maTecnologiasValor) {
        this.maTecnologiasValor = maTecnologiasValor;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getNivelComplejidad() {
        return nivelComplejidad;
    }

    public void setNivelComplejidad(int nivelComplejidad) {
        this.nivelComplejidad = nivelComplejidad;
    }

    public Boolean getAplicaSeguimiento() {
        return aplicaSeguimiento;
    }

    public void setAplicaSeguimiento(Boolean aplicaSeguimiento) {
        this.aplicaSeguimiento = aplicaSeguimiento;
    }

    public Integer getMaeSeguimientoServicioId() {
        return maeSeguimientoServicioId;
    }

    public void setMaeSeguimientoServicioId(Integer maeSeguimientoServicioId) {
        this.maeSeguimientoServicioId = maeSeguimientoServicioId;
    }

    public String getMaeSeguimientoServicioCodigo() {
        return maeSeguimientoServicioCodigo;
    }

    public void setMaeSeguimientoServicioCodigo(String maeSeguimientoServicioCodigo) {
        this.maeSeguimientoServicioCodigo = maeSeguimientoServicioCodigo;
    }

    public String getMaeSeguimientoServicioValor() {
        return maeSeguimientoServicioValor;
    }

    public void setMaeSeguimientoServicioValor(String maeSeguimientoServicioValor) {
        this.maeSeguimientoServicioValor = maeSeguimientoServicioValor;
    }

    public Integer getMaeTipoAuditorId() {
        return maeTipoAuditorId;
    }

    public void setMaeTipoAuditorId(Integer maeTipoAuditorId) {
        this.maeTipoAuditorId = maeTipoAuditorId;
    }

    public String getMaeTipoAuditorCodigo() {
        return maeTipoAuditorCodigo;
    }

    public void setMaeTipoAuditorCodigo(String maeTipoAuditorCodigo) {
        this.maeTipoAuditorCodigo = maeTipoAuditorCodigo;
    }

    public String getMaeTipoAuditorValor() {
        return maeTipoAuditorValor;
    }

    public void setMaeTipoAuditorValor(String maeTipoAuditorValor) {
        this.maeTipoAuditorValor = maeTipoAuditorValor;
    }

    public Integer getMaeMedicamentoId() {
        return maeMedicamentoId;
    }

    public void setMaeMedicamentoId(Integer maeMedicamentoId) {
        this.maeMedicamentoId = maeMedicamentoId;
    }

    public String getMaeMedicamentoCodigo() {
        return maeMedicamentoCodigo;
    }

    public void setMaeMedicamentoCodigo(String maeMedicamentoCodigo) {
        this.maeMedicamentoCodigo = maeMedicamentoCodigo;
    }

    public String getMaeMedicamentoValor() {
        return maeMedicamentoValor;
    }

    public void setMaeMedicamentoValor(String maeMedicamentoValor) {
        this.maeMedicamentoValor = maeMedicamentoValor;
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

    public AuGrupos getAuGruposId() {
        return auGruposId;
    }

    public void setAuGruposId(AuGrupos auGruposId) {
        this.auGruposId = auGruposId;
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
        if (!(object instanceof AuGrupoTecnologias)) {
            return false;
        }
        AuGrupoTecnologias other = (AuGrupoTecnologias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuGrupoTecnologias[ id=" + id + " ]";
    }
    
}
