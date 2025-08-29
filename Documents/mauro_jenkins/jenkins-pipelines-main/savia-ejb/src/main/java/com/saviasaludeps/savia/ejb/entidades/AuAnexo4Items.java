/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "au_anexo4_items")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuAnexo4Items.findAll", query = "SELECT a FROM AuAnexo4Items a"),
    @NamedQuery(name = "AuAnexo4Items.findById", query = "SELECT a FROM AuAnexo4Items a WHERE a.id = :id"),
    @NamedQuery(name = "AuAnexo4Items.findByTipoTecnologia", query = "SELECT a FROM AuAnexo4Items a WHERE a.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "AuAnexo4Items.findByMaTecnologiaId", query = "SELECT a FROM AuAnexo4Items a WHERE a.maTecnologiaId = :maTecnologiaId"),
    @NamedQuery(name = "AuAnexo4Items.findByMaTecnologiaCodigo", query = "SELECT a FROM AuAnexo4Items a WHERE a.maTecnologiaCodigo = :maTecnologiaCodigo"),
    @NamedQuery(name = "AuAnexo4Items.findByMaTecnologiaValor", query = "SELECT a FROM AuAnexo4Items a WHERE a.maTecnologiaValor = :maTecnologiaValor"),
    @NamedQuery(name = "AuAnexo4Items.findByMaMedicamentoId", query = "SELECT a FROM AuAnexo4Items a WHERE a.maMedicamentoId = :maMedicamentoId"),
    @NamedQuery(name = "AuAnexo4Items.findByMaMedicamentoCodigo", query = "SELECT a FROM AuAnexo4Items a WHERE a.maMedicamentoCodigo = :maMedicamentoCodigo"),
    @NamedQuery(name = "AuAnexo4Items.findByMaMedicamentoValor", query = "SELECT a FROM AuAnexo4Items a WHERE a.maMedicamentoValor = :maMedicamentoValor"),
    @NamedQuery(name = "AuAnexo4Items.findByCantidadAutorizada", query = "SELECT a FROM AuAnexo4Items a WHERE a.cantidadAutorizada = :cantidadAutorizada"),
    @NamedQuery(name = "AuAnexo4Items.findByCostoServicio", query = "SELECT a FROM AuAnexo4Items a WHERE a.costoServicio = :costoServicio"),
    @NamedQuery(name = "AuAnexo4Items.findByEntrega", query = "SELECT a FROM AuAnexo4Items a WHERE a.entrega = :entrega"),
    @NamedQuery(name = "AuAnexo4Items.findByEntregaObservacion", query = "SELECT a FROM AuAnexo4Items a WHERE a.entregaObservacion = :entregaObservacion"),
    @NamedQuery(name = "AuAnexo4Items.findByUsuarioCrea", query = "SELECT a FROM AuAnexo4Items a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuAnexo4Items.findByTerminalCrea", query = "SELECT a FROM AuAnexo4Items a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuAnexo4Items.findByFechaHoraCrea", query = "SELECT a FROM AuAnexo4Items a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AuAnexo4Items.findByUsuarioModifica", query = "SELECT a FROM AuAnexo4Items a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AuAnexo4Items.findByTerminalModifica", query = "SELECT a FROM AuAnexo4Items a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AuAnexo4Items.findByFechaHoraModifica", query = "SELECT a FROM AuAnexo4Items a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AuAnexo4Items implements Serializable {

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
    @Size(max = 32)
    @Column(name = "ma_tecnologia_codigo")
    private String maTecnologiaCodigo;
    @Size(max = 512)
    @Column(name = "ma_tecnologia_valor")
    private String maTecnologiaValor;
    @Column(name = "ma_medicamento_id")
    private Integer maMedicamentoId;
    @Size(max = 32)
    @Column(name = "ma_medicamento_codigo")
    private String maMedicamentoCodigo;
    @Size(max = 512)
    @Column(name = "ma_medicamento_valor")
    private String maMedicamentoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad_autorizada")
    private int cantidadAutorizada;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "costo_servicio")
    private BigDecimal costoServicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "entrega")
    private int entrega;
    @Size(max = 1024)
    @Column(name = "entrega_observacion")
    private String entregaObservacion;
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
    @JoinColumn(name = "au_anexo2_items_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuAnexo2Items auAnexo2ItemsId;
    @JoinColumn(name = "au_anexo3_items_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuAnexo3Items auAnexo3ItemsId;
    @JoinColumn(name = "au_anexos4_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AuAnexos4 auAnexos4Id;
    @JoinColumn(name = "au_cotizaciones_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuCotizaciones auCotizacionesId;
    @JoinColumn(name = "cnt_contrato_detalles_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntContratoDetalles cntContratoDetallesId;
    @OneToMany(mappedBy = "auAnexo4ItemsId", fetch = FetchType.LAZY)
    private List<AuAnexo4Entregas> auAnexo4EntregasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auAnexos4ItemsId", fetch = FetchType.LAZY)
    private List<AusCasoServicioCostos> ausCasoServicioCostosList;

    public AuAnexo4Items() {
    }

    public AuAnexo4Items(Integer id) {
        this.id = id;
    }

    public AuAnexo4Items(Integer id, int tipoTecnologia, int maTecnologiaId, int cantidadAutorizada, BigDecimal costoServicio, int entrega, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.tipoTecnologia = tipoTecnologia;
        this.maTecnologiaId = maTecnologiaId;
        this.cantidadAutorizada = cantidadAutorizada;
        this.costoServicio = costoServicio;
        this.entrega = entrega;
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

    public int getCantidadAutorizada() {
        return cantidadAutorizada;
    }

    public void setCantidadAutorizada(int cantidadAutorizada) {
        this.cantidadAutorizada = cantidadAutorizada;
    }

    public BigDecimal getCostoServicio() {
        return costoServicio;
    }

    public void setCostoServicio(BigDecimal costoServicio) {
        this.costoServicio = costoServicio;
    }

    public int getEntrega() {
        return entrega;
    }

    public void setEntrega(int entrega) {
        this.entrega = entrega;
    }

    public String getEntregaObservacion() {
        return entregaObservacion;
    }

    public void setEntregaObservacion(String entregaObservacion) {
        this.entregaObservacion = entregaObservacion;
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

    public AuAnexo2Items getAuAnexo2ItemsId() {
        return auAnexo2ItemsId;
    }

    public void setAuAnexo2ItemsId(AuAnexo2Items auAnexo2ItemsId) {
        this.auAnexo2ItemsId = auAnexo2ItemsId;
    }

    public AuAnexo3Items getAuAnexo3ItemsId() {
        return auAnexo3ItemsId;
    }

    public void setAuAnexo3ItemsId(AuAnexo3Items auAnexo3ItemsId) {
        this.auAnexo3ItemsId = auAnexo3ItemsId;
    }

    public AuAnexos4 getAuAnexos4Id() {
        return auAnexos4Id;
    }

    public void setAuAnexos4Id(AuAnexos4 auAnexos4Id) {
        this.auAnexos4Id = auAnexos4Id;
    }

    public AuCotizaciones getAuCotizacionesId() {
        return auCotizacionesId;
    }

    public void setAuCotizacionesId(AuCotizaciones auCotizacionesId) {
        this.auCotizacionesId = auCotizacionesId;
    }

    public CntContratoDetalles getCntContratoDetallesId() {
        return cntContratoDetallesId;
    }

    public void setCntContratoDetallesId(CntContratoDetalles cntContratoDetallesId) {
        this.cntContratoDetallesId = cntContratoDetallesId;
    }

    @XmlTransient
    public List<AuAnexo4Entregas> getAuAnexo4EntregasList() {
        return auAnexo4EntregasList;
    }

    public void setAuAnexo4EntregasList(List<AuAnexo4Entregas> auAnexo4EntregasList) {
        this.auAnexo4EntregasList = auAnexo4EntregasList;
    }

    @XmlTransient
    public List<AusCasoServicioCostos> getAusCasoServicioCostosList() {
        return ausCasoServicioCostosList;
    }

    public void setAusCasoServicioCostosList(List<AusCasoServicioCostos> ausCasoServicioCostosList) {
        this.ausCasoServicioCostosList = ausCasoServicioCostosList;
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
        if (!(object instanceof AuAnexo4Items)) {
            return false;
        }
        AuAnexo4Items other = (AuAnexo4Items) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuAnexo4Items[ id=" + id + " ]";
    }
    
}
