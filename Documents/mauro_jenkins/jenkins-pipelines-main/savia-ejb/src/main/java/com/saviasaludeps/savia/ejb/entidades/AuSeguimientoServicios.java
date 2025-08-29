/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "au_seguimiento_servicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuSeguimientoServicios.findAll", query = "SELECT a FROM AuSeguimientoServicios a"),
    @NamedQuery(name = "AuSeguimientoServicios.findById", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.id = :id"),
    @NamedQuery(name = "AuSeguimientoServicios.findByMaeSeguimientoServicioId", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.maeSeguimientoServicioId = :maeSeguimientoServicioId"),
    @NamedQuery(name = "AuSeguimientoServicios.findByMaeSeguimientoServicioCodigo", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.maeSeguimientoServicioCodigo = :maeSeguimientoServicioCodigo"),
    @NamedQuery(name = "AuSeguimientoServicios.findByMaeSeguimientoServicioValor", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.maeSeguimientoServicioValor = :maeSeguimientoServicioValor"),
    @NamedQuery(name = "AuSeguimientoServicios.findByLitros", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.litros = :litros"),
    @NamedQuery(name = "AuSeguimientoServicios.findByTiempoUso", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.tiempoUso = :tiempoUso"),
    @NamedQuery(name = "AuSeguimientoServicios.findByTiempoDuracionTratamiento", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.tiempoDuracionTratamiento = :tiempoDuracionTratamiento"),
    @NamedQuery(name = "AuSeguimientoServicios.findByGasesArteriales", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.gasesArteriales = :gasesArteriales"),
    @NamedQuery(name = "AuSeguimientoServicios.findByPresion", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.presion = :presion"),
    @NamedQuery(name = "AuSeguimientoServicios.findByRampa", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.rampa = :rampa"),
    @NamedQuery(name = "AuSeguimientoServicios.findByMaeTipoMascaraId", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.maeTipoMascaraId = :maeTipoMascaraId"),
    @NamedQuery(name = "AuSeguimientoServicios.findByMaeTipoMascaraCodigo", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.maeTipoMascaraCodigo = :maeTipoMascaraCodigo"),
    @NamedQuery(name = "AuSeguimientoServicios.findByMaeTipoMascaraValor", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.maeTipoMascaraValor = :maeTipoMascaraValor"),
    @NamedQuery(name = "AuSeguimientoServicios.findByMaeTallaMascaraId", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.maeTallaMascaraId = :maeTallaMascaraId"),
    @NamedQuery(name = "AuSeguimientoServicios.findByMaeTallaMascaraCodigo", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.maeTallaMascaraCodigo = :maeTallaMascaraCodigo"),
    @NamedQuery(name = "AuSeguimientoServicios.findByMaeTallaMascaraValor", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.maeTallaMascaraValor = :maeTallaMascaraValor"),
    @NamedQuery(name = "AuSeguimientoServicios.findByMaeTipoSondaId", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.maeTipoSondaId = :maeTipoSondaId"),
    @NamedQuery(name = "AuSeguimientoServicios.findByMaeTipoSondaCodigo", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.maeTipoSondaCodigo = :maeTipoSondaCodigo"),
    @NamedQuery(name = "AuSeguimientoServicios.findByMaeTipoSondaValor", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.maeTipoSondaValor = :maeTipoSondaValor"),
    @NamedQuery(name = "AuSeguimientoServicios.findByPresiones", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.presiones = :presiones"),
    @NamedQuery(name = "AuSeguimientoServicios.findByTipoMascaraTos", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.tipoMascaraTos = :tipoMascaraTos"),
    @NamedQuery(name = "AuSeguimientoServicios.findByObservaciones", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.observaciones = :observaciones"),
    @NamedQuery(name = "AuSeguimientoServicios.findByBorrado", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.borrado = :borrado"),
    @NamedQuery(name = "AuSeguimientoServicios.findByMaeTipoInsumoId", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.maeTipoInsumoId = :maeTipoInsumoId"),
    @NamedQuery(name = "AuSeguimientoServicios.findByMaeTipoInsumoCodigo", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.maeTipoInsumoCodigo = :maeTipoInsumoCodigo"),
    @NamedQuery(name = "AuSeguimientoServicios.findByMaeTipoInsumoValor", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.maeTipoInsumoValor = :maeTipoInsumoValor"),
    @NamedQuery(name = "AuSeguimientoServicios.findByUsuarioCrea", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuSeguimientoServicios.findByTerminalCrea", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuSeguimientoServicios.findByFechaHoraCrea", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuSeguimientoServicios.findByUsuarioModifica", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuSeguimientoServicios.findByTerminalModifica", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuSeguimientoServicios.findByFechaHoraModifica", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "AuSeguimientoServicios.findByUsuarioBorra", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "AuSeguimientoServicios.findByTerminalBorra", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "AuSeguimientoServicios.findByFechaHoraBorra", query = "SELECT a FROM AuSeguimientoServicios a WHERE a.fechaHoraBorra = :fechaHoraBorra")})
public class AuSeguimientoServicios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_seguimiento_servicio_id")
    private int maeSeguimientoServicioId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_seguimiento_servicio_codigo")
    private String maeSeguimientoServicioCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_seguimiento_servicio_valor")
    private String maeSeguimientoServicioValor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "litros")
    private BigDecimal litros;
    @Column(name = "tiempo_uso")
    private Integer tiempoUso;
    @Column(name = "tiempo_duracion_tratamiento")
    private Integer tiempoDuracionTratamiento;
    @Column(name = "gases_arteriales")
    private Boolean gasesArteriales;
    @Column(name = "presion")
    private Integer presion;
    @Column(name = "rampa")
    private Integer rampa;
    @Column(name = "mae_tipo_mascara_id")
    private Integer maeTipoMascaraId;
    @Size(max = 8)
    @Column(name = "mae_tipo_mascara_codigo")
    private String maeTipoMascaraCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_mascara_valor")
    private String maeTipoMascaraValor;
    @Column(name = "mae_talla_mascara_id")
    private Integer maeTallaMascaraId;
    @Size(max = 8)
    @Column(name = "mae_talla_mascara_codigo")
    private String maeTallaMascaraCodigo;
    @Size(max = 128)
    @Column(name = "mae_talla_mascara_valor")
    private String maeTallaMascaraValor;
    @Column(name = "mae_tipo_sonda_id")
    private Integer maeTipoSondaId;
    @Size(max = 8)
    @Column(name = "mae_tipo_sonda_codigo")
    private String maeTipoSondaCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_sonda_valor")
    private String maeTipoSondaValor;
    @Column(name = "presiones")
    private Integer presiones;
    @Size(max = 256)
    @Column(name = "tipo_mascara_tos")
    private String tipoMascaraTos;
    @Size(max = 2048)
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "borrado")
    private Boolean borrado;
    @Column(name = "mae_tipo_insumo_id")
    private Integer maeTipoInsumoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_insumo_codigo")
    private String maeTipoInsumoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_insumo_valor")
    private String maeTipoInsumoValor;
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
    @Size(max = 128)
    @Column(name = "usuario_borra")
    private String usuarioBorra;
    @Size(max = 16)
    @Column(name = "terminal_borra")
    private String terminalBorra;
    @Column(name = "fecha_hora_borra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraBorra;
    @JoinColumn(name = "au_seguimiento_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuSeguimientos auSeguimientoId;

    public AuSeguimientoServicios() {
    }

    public AuSeguimientoServicios(Integer id) {
        this.id = id;
    }

    public AuSeguimientoServicios(Integer id, int maeSeguimientoServicioId, String maeSeguimientoServicioCodigo, String maeSeguimientoServicioValor, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeSeguimientoServicioId = maeSeguimientoServicioId;
        this.maeSeguimientoServicioCodigo = maeSeguimientoServicioCodigo;
        this.maeSeguimientoServicioValor = maeSeguimientoServicioValor;
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

    public int getMaeSeguimientoServicioId() {
        return maeSeguimientoServicioId;
    }

    public void setMaeSeguimientoServicioId(int maeSeguimientoServicioId) {
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

    public BigDecimal getLitros() {
        return litros;
    }

    public void setLitros(BigDecimal litros) {
        this.litros = litros;
    }

    public Integer getTiempoUso() {
        return tiempoUso;
    }

    public void setTiempoUso(Integer tiempoUso) {
        this.tiempoUso = tiempoUso;
    }

    public Integer getTiempoDuracionTratamiento() {
        return tiempoDuracionTratamiento;
    }

    public void setTiempoDuracionTratamiento(Integer tiempoDuracionTratamiento) {
        this.tiempoDuracionTratamiento = tiempoDuracionTratamiento;
    }

    public Boolean getGasesArteriales() {
        return gasesArteriales;
    }

    public void setGasesArteriales(Boolean gasesArteriales) {
        this.gasesArteriales = gasesArteriales;
    }

    public Integer getPresion() {
        return presion;
    }

    public void setPresion(Integer presion) {
        this.presion = presion;
    }

    public Integer getRampa() {
        return rampa;
    }

    public void setRampa(Integer rampa) {
        this.rampa = rampa;
    }

    public Integer getMaeTipoMascaraId() {
        return maeTipoMascaraId;
    }

    public void setMaeTipoMascaraId(Integer maeTipoMascaraId) {
        this.maeTipoMascaraId = maeTipoMascaraId;
    }

    public String getMaeTipoMascaraCodigo() {
        return maeTipoMascaraCodigo;
    }

    public void setMaeTipoMascaraCodigo(String maeTipoMascaraCodigo) {
        this.maeTipoMascaraCodigo = maeTipoMascaraCodigo;
    }

    public String getMaeTipoMascaraValor() {
        return maeTipoMascaraValor;
    }

    public void setMaeTipoMascaraValor(String maeTipoMascaraValor) {
        this.maeTipoMascaraValor = maeTipoMascaraValor;
    }

    public Integer getMaeTallaMascaraId() {
        return maeTallaMascaraId;
    }

    public void setMaeTallaMascaraId(Integer maeTallaMascaraId) {
        this.maeTallaMascaraId = maeTallaMascaraId;
    }

    public String getMaeTallaMascaraCodigo() {
        return maeTallaMascaraCodigo;
    }

    public void setMaeTallaMascaraCodigo(String maeTallaMascaraCodigo) {
        this.maeTallaMascaraCodigo = maeTallaMascaraCodigo;
    }

    public String getMaeTallaMascaraValor() {
        return maeTallaMascaraValor;
    }

    public void setMaeTallaMascaraValor(String maeTallaMascaraValor) {
        this.maeTallaMascaraValor = maeTallaMascaraValor;
    }

    public Integer getMaeTipoSondaId() {
        return maeTipoSondaId;
    }

    public void setMaeTipoSondaId(Integer maeTipoSondaId) {
        this.maeTipoSondaId = maeTipoSondaId;
    }

    public String getMaeTipoSondaCodigo() {
        return maeTipoSondaCodigo;
    }

    public void setMaeTipoSondaCodigo(String maeTipoSondaCodigo) {
        this.maeTipoSondaCodigo = maeTipoSondaCodigo;
    }

    public String getMaeTipoSondaValor() {
        return maeTipoSondaValor;
    }

    public void setMaeTipoSondaValor(String maeTipoSondaValor) {
        this.maeTipoSondaValor = maeTipoSondaValor;
    }

    public Integer getPresiones() {
        return presiones;
    }

    public void setPresiones(Integer presiones) {
        this.presiones = presiones;
    }

    public String getTipoMascaraTos() {
        return tipoMascaraTos;
    }

    public void setTipoMascaraTos(String tipoMascaraTos) {
        this.tipoMascaraTos = tipoMascaraTos;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public Integer getMaeTipoInsumoId() {
        return maeTipoInsumoId;
    }

    public void setMaeTipoInsumoId(Integer maeTipoInsumoId) {
        this.maeTipoInsumoId = maeTipoInsumoId;
    }

    public String getMaeTipoInsumoCodigo() {
        return maeTipoInsumoCodigo;
    }

    public void setMaeTipoInsumoCodigo(String maeTipoInsumoCodigo) {
        this.maeTipoInsumoCodigo = maeTipoInsumoCodigo;
    }

    public String getMaeTipoInsumoValor() {
        return maeTipoInsumoValor;
    }

    public void setMaeTipoInsumoValor(String maeTipoInsumoValor) {
        this.maeTipoInsumoValor = maeTipoInsumoValor;
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

    public String getUsuarioBorra() {
        return usuarioBorra;
    }

    public void setUsuarioBorra(String usuarioBorra) {
        this.usuarioBorra = usuarioBorra;
    }

    public String getTerminalBorra() {
        return terminalBorra;
    }

    public void setTerminalBorra(String terminalBorra) {
        this.terminalBorra = terminalBorra;
    }

    public Date getFechaHoraBorra() {
        return fechaHoraBorra;
    }

    public void setFechaHoraBorra(Date fechaHoraBorra) {
        this.fechaHoraBorra = fechaHoraBorra;
    }

    public AuSeguimientos getAuSeguimientoId() {
        return auSeguimientoId;
    }

    public void setAuSeguimientoId(AuSeguimientos auSeguimientoId) {
        this.auSeguimientoId = auSeguimientoId;
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
        if (!(object instanceof AuSeguimientoServicios)) {
            return false;
        }
        AuSeguimientoServicios other = (AuSeguimientoServicios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuSeguimientoServicios[ id=" + id + " ]";
    }
    
}
