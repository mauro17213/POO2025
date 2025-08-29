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
@Table(name = "aus_caso_servicio_costos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AusCasoServicioCostos.findAll", query = "SELECT a FROM AusCasoServicioCostos a"),
    @NamedQuery(name = "AusCasoServicioCostos.findById", query = "SELECT a FROM AusCasoServicioCostos a WHERE a.id = :id"),
    @NamedQuery(name = "AusCasoServicioCostos.findByAuAnexos4Id", query = "SELECT a FROM AusCasoServicioCostos a WHERE a.auAnexos4Id = :auAnexos4Id"),
    @NamedQuery(name = "AusCasoServicioCostos.findByCntContratoDetalleId", query = "SELECT a FROM AusCasoServicioCostos a WHERE a.cntContratoDetalleId = :cntContratoDetalleId"),
    @NamedQuery(name = "AusCasoServicioCostos.findByMaTecnologiaId", query = "SELECT a FROM AusCasoServicioCostos a WHERE a.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "AusCasoServicioCostos.findByMaTecnologiaCodigo", query = "SELECT a FROM AusCasoServicioCostos a WHERE a.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "AusCasoServicioCostos.findByMaTecnologiaValor", query = "SELECT a FROM AusCasoServicioCostos a WHERE a.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "AusCasoServicioCostos.findByMaMedicamentoId", query = "SELECT a FROM AusCasoServicioCostos a WHERE a.maMedicamentoId = :maMedicamentoId"),
    @NamedQuery(name = "AusCasoServicioCostos.findByMaMedicamentoCodigo", query = "SELECT a FROM AusCasoServicioCostos a WHERE a.maMedicamentoCodigo = :maMedicamentoCodigo"),
    @NamedQuery(name = "AusCasoServicioCostos.findByMaMedicamentoValor", query = "SELECT a FROM AusCasoServicioCostos a WHERE a.maMedicamentoValor = :maMedicamentoValor"),
    @NamedQuery(name = "AusCasoServicioCostos.findByCostoServicio", query = "SELECT a FROM AusCasoServicioCostos a WHERE a.costoServicio = :costoServicio"),
    @NamedQuery(name = "AusCasoServicioCostos.findByUsuarioCrea", query = "SELECT a FROM AusCasoServicioCostos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AusCasoServicioCostos.findByTerminalCrea", query = "SELECT a FROM AusCasoServicioCostos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AusCasoServicioCostos.findByFechaHoraCrea", query = "SELECT a FROM AusCasoServicioCostos a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AusCasoServicioCostos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "au_anexos4_id")
    private int auAnexos4Id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cnt_contrato_detalle_id")
    private int cntContratoDetalleId;
    @Column(name = "ma_tecnologia_id")
    private Integer maTecnologiaId;
    @Size(max = 16)
    @Column(name = "ma_tecnologia_codigo")
    private String maTecnologiaCodigo;
    @Size(max = 512)
    @Column(name = "ma_tecnologia_valor")
    private String maTecnologiaValor;
    @Column(name = "ma_medicamento_id")
    private Integer maMedicamentoId;
    @Size(max = 16)
    @Column(name = "ma_medicamento_codigo")
    private String maMedicamentoCodigo;
    @Size(max = 512)
    @Column(name = "ma_medicamento_valor")
    private String maMedicamentoValor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "costo_servicio")
    private BigDecimal costoServicio;
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
    @JoinColumn(name = "au_anexos4_items_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuAnexo4Items auAnexos4ItemsId;
    @JoinColumn(name = "aus_caso_servicios_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AusCasoServicios ausCasoServiciosId;

    public AusCasoServicioCostos() {
    }

    public AusCasoServicioCostos(Integer id) {
        this.id = id;
    }

    public AusCasoServicioCostos(Integer id, int auAnexos4Id, int cntContratoDetalleId, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.auAnexos4Id = auAnexos4Id;
        this.cntContratoDetalleId = cntContratoDetalleId;
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

    public int getAuAnexos4Id() {
        return auAnexos4Id;
    }

    public void setAuAnexos4Id(int auAnexos4Id) {
        this.auAnexos4Id = auAnexos4Id;
    }

    public int getCntContratoDetalleId() {
        return cntContratoDetalleId;
    }

    public void setCntContratoDetalleId(int cntContratoDetalleId) {
        this.cntContratoDetalleId = cntContratoDetalleId;
    }

    public Integer getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(Integer maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
    }

    public Integer getMaMedicamentoId() {
        return maMedicamentoId;
    }

    public void setMaMedicamentoId(Integer maMedicamentoId) {
        this.maMedicamentoId = maMedicamentoId;
    }

    public String getMaMedicamentoCodigo() {
        return maMedicamentoCodigo;
    }

    public void setMaMedicamentoCodigo(String maMedicamentoCodigo) {
        this.maMedicamentoCodigo = maMedicamentoCodigo;
    }

    public String getMaMedicamentoValor() {
        return maMedicamentoValor;
    }

    public void setMaMedicamentoValor(String maMedicamentoValor) {
        this.maMedicamentoValor = maMedicamentoValor;
    }

    public BigDecimal getCostoServicio() {
        return costoServicio;
    }

    public void setCostoServicio(BigDecimal costoServicio) {
        this.costoServicio = costoServicio;
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

    public AuAnexo4Items getAuAnexos4ItemsId() {
        return auAnexos4ItemsId;
    }

    public void setAuAnexos4ItemsId(AuAnexo4Items auAnexos4ItemsId) {
        this.auAnexos4ItemsId = auAnexos4ItemsId;
    }

    public AusCasoServicios getAusCasoServiciosId() {
        return ausCasoServiciosId;
    }

    public void setAusCasoServiciosId(AusCasoServicios ausCasoServiciosId) {
        this.ausCasoServiciosId = ausCasoServiciosId;
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
        if (!(object instanceof AusCasoServicioCostos)) {
            return false;
        }
        AusCasoServicioCostos other = (AusCasoServicioCostos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AusCasoServicioCostos[ id=" + id + " ]";
    }
    
}
