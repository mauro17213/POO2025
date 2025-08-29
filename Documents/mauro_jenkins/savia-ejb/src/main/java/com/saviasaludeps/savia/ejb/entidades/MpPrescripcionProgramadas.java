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
@Table(name = "mp_prescripcion_programadas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MpPrescripcionProgramadas.findAll", query = "SELECT m FROM MpPrescripcionProgramadas m"),
    @NamedQuery(name = "MpPrescripcionProgramadas.findById", query = "SELECT m FROM MpPrescripcionProgramadas m WHERE m.id = :id"),
    @NamedQuery(name = "MpPrescripcionProgramadas.findByMaeTipoDocumentoPrestadorId", query = "SELECT m FROM MpPrescripcionProgramadas m WHERE m.maeTipoDocumentoPrestadorId = :maeTipoDocumentoPrestadorId"),
    @NamedQuery(name = "MpPrescripcionProgramadas.findByMaeTipoDocumentoPrestadorCodigo", query = "SELECT m FROM MpPrescripcionProgramadas m WHERE m.maeTipoDocumentoPrestadorCodigo = :maeTipoDocumentoPrestadorCodigo"),
    @NamedQuery(name = "MpPrescripcionProgramadas.findByMaeTipoDocumentoPrestadorValor", query = "SELECT m FROM MpPrescripcionProgramadas m WHERE m.maeTipoDocumentoPrestadorValor = :maeTipoDocumentoPrestadorValor"),
    @NamedQuery(name = "MpPrescripcionProgramadas.findByPrestadorNumeroDocumento", query = "SELECT m FROM MpPrescripcionProgramadas m WHERE m.prestadorNumeroDocumento = :prestadorNumeroDocumento"),
    @NamedQuery(name = "MpPrescripcionProgramadas.findByPrestadorRazonSocial", query = "SELECT m FROM MpPrescripcionProgramadas m WHERE m.prestadorRazonSocial = :prestadorRazonSocial"),
    @NamedQuery(name = "MpPrescripcionProgramadas.findBySedeCodigoHabilitacion", query = "SELECT m FROM MpPrescripcionProgramadas m WHERE m.sedeCodigoHabilitacion = :sedeCodigoHabilitacion"),
    @NamedQuery(name = "MpPrescripcionProgramadas.findBySedeDireccion", query = "SELECT m FROM MpPrescripcionProgramadas m WHERE m.sedeDireccion = :sedeDireccion"),
    @NamedQuery(name = "MpPrescripcionProgramadas.findBySedeTelefono", query = "SELECT m FROM MpPrescripcionProgramadas m WHERE m.sedeTelefono = :sedeTelefono"),
    @NamedQuery(name = "MpPrescripcionProgramadas.findByTipoTecnologia", query = "SELECT m FROM MpPrescripcionProgramadas m WHERE m.tipoTecnologia = :tipoTecnologia"),
    @NamedQuery(name = "MpPrescripcionProgramadas.findByEstado", query = "SELECT m FROM MpPrescripcionProgramadas m WHERE m.estado = :estado"),
    @NamedQuery(name = "MpPrescripcionProgramadas.findByIdTransaccion", query = "SELECT m FROM MpPrescripcionProgramadas m WHERE m.idTransaccion = :idTransaccion"),
    @NamedQuery(name = "MpPrescripcionProgramadas.findByIdDireccionamiento", query = "SELECT m FROM MpPrescripcionProgramadas m WHERE m.idDireccionamiento = :idDireccionamiento"),
    @NamedQuery(name = "MpPrescripcionProgramadas.findByFechaDireccionamiento", query = "SELECT m FROM MpPrescripcionProgramadas m WHERE m.fechaDireccionamiento = :fechaDireccionamiento"),
    @NamedQuery(name = "MpPrescripcionProgramadas.findByFechaMaxEntrega", query = "SELECT m FROM MpPrescripcionProgramadas m WHERE m.fechaMaxEntrega = :fechaMaxEntrega"),
    @NamedQuery(name = "MpPrescripcionProgramadas.findByEntregaNumero", query = "SELECT m FROM MpPrescripcionProgramadas m WHERE m.entregaNumero = :entregaNumero"),
    @NamedQuery(name = "MpPrescripcionProgramadas.findByEntregaCantidad", query = "SELECT m FROM MpPrescripcionProgramadas m WHERE m.entregaCantidad = :entregaCantidad"),
    @NamedQuery(name = "MpPrescripcionProgramadas.findByEntregaTotal", query = "SELECT m FROM MpPrescripcionProgramadas m WHERE m.entregaTotal = :entregaTotal"),
    @NamedQuery(name = "MpPrescripcionProgramadas.findByEntregadoNumero", query = "SELECT m FROM MpPrescripcionProgramadas m WHERE m.entregadoNumero = :entregadoNumero"),
    @NamedQuery(name = "MpPrescripcionProgramadas.findByEntregadoTotal", query = "SELECT m FROM MpPrescripcionProgramadas m WHERE m.entregadoTotal = :entregadoTotal"),
    @NamedQuery(name = "MpPrescripcionProgramadas.findByEntregadoPendiente", query = "SELECT m FROM MpPrescripcionProgramadas m WHERE m.entregadoPendiente = :entregadoPendiente"),
    @NamedQuery(name = "MpPrescripcionProgramadas.findByUsuarioCrea", query = "SELECT m FROM MpPrescripcionProgramadas m WHERE m.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "MpPrescripcionProgramadas.findByTerminalCrea", query = "SELECT m FROM MpPrescripcionProgramadas m WHERE m.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "MpPrescripcionProgramadas.findByFechaHoraCrea", query = "SELECT m FROM MpPrescripcionProgramadas m WHERE m.fechaHoraCrea = :fechaHoraCrea")})
public class MpPrescripcionProgramadas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "mae_tipo_documento_prestador_id")
    private Integer maeTipoDocumentoPrestadorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_documento_prestador_codigo")
    private String maeTipoDocumentoPrestadorCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_documento_prestador_valor")
    private String maeTipoDocumentoPrestadorValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "prestador_numero_documento")
    private String prestadorNumeroDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "prestador_razon_social")
    private String prestadorRazonSocial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "sede_codigo_habilitacion")
    private String sedeCodigoHabilitacion;
    @Size(max = 256)
    @Column(name = "sede_direccion")
    private String sedeDireccion;
    @Size(max = 50)
    @Column(name = "sede_telefono")
    private String sedeTelefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_tecnologia")
    private int tipoTecnologia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private int estado;
    @Column(name = "id_transaccion")
    private Integer idTransaccion;
    @Column(name = "id_direccionamiento")
    private Integer idDireccionamiento;
    @Column(name = "fecha_direccionamiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDireccionamiento;
    @Column(name = "fecha_max_entrega")
    @Temporal(TemporalType.DATE)
    private Date fechaMaxEntrega;
    @Column(name = "entrega_numero")
    private Integer entregaNumero;
    @Column(name = "entrega_cantidad")
    private Integer entregaCantidad;
    @Column(name = "entrega_total")
    private Integer entregaTotal;
    @Column(name = "entregado_numero")
    private Integer entregadoNumero;
    @Column(name = "entregado_total")
    private Integer entregadoTotal;
    @Column(name = "entregado_pendiente")
    private Integer entregadoPendiente;
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
    @OneToMany(mappedBy = "mpPrescripcionProgramadasId", fetch = FetchType.LAZY)
    private List<MpProgramadaEntregas> mpProgramadaEntregasList;
    @JoinColumn(name = "mp_prescripciones_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpPrescripciones mpPrescripcionesId;
    @JoinColumn(name = "mp_prescripcion_insumos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpPrescripcionInsumos mpPrescripcionInsumosId;
    @JoinColumn(name = "mp_prescripcion_medicamentos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpPrescripcionMedicamentos mpPrescripcionMedicamentosId;
    @JoinColumn(name = "mp_prescripcion_tecnologias_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private MpPrescripcionTecnologias mpPrescripcionTecnologiasId;

    public MpPrescripcionProgramadas() {
    }

    public MpPrescripcionProgramadas(Integer id) {
        this.id = id;
    }

    public MpPrescripcionProgramadas(Integer id, String maeTipoDocumentoPrestadorCodigo, String prestadorNumeroDocumento, String prestadorRazonSocial, String sedeCodigoHabilitacion, int tipoTecnologia, int estado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.maeTipoDocumentoPrestadorCodigo = maeTipoDocumentoPrestadorCodigo;
        this.prestadorNumeroDocumento = prestadorNumeroDocumento;
        this.prestadorRazonSocial = prestadorRazonSocial;
        this.sedeCodigoHabilitacion = sedeCodigoHabilitacion;
        this.tipoTecnologia = tipoTecnologia;
        this.estado = estado;
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

    public Integer getMaeTipoDocumentoPrestadorId() {
        return maeTipoDocumentoPrestadorId;
    }

    public void setMaeTipoDocumentoPrestadorId(Integer maeTipoDocumentoPrestadorId) {
        this.maeTipoDocumentoPrestadorId = maeTipoDocumentoPrestadorId;
    }

    public String getMaeTipoDocumentoPrestadorCodigo() {
        return maeTipoDocumentoPrestadorCodigo;
    }

    public void setMaeTipoDocumentoPrestadorCodigo(String maeTipoDocumentoPrestadorCodigo) {
        this.maeTipoDocumentoPrestadorCodigo = maeTipoDocumentoPrestadorCodigo;
    }

    public String getMaeTipoDocumentoPrestadorValor() {
        return maeTipoDocumentoPrestadorValor;
    }

    public void setMaeTipoDocumentoPrestadorValor(String maeTipoDocumentoPrestadorValor) {
        this.maeTipoDocumentoPrestadorValor = maeTipoDocumentoPrestadorValor;
    }

    public String getPrestadorNumeroDocumento() {
        return prestadorNumeroDocumento;
    }

    public void setPrestadorNumeroDocumento(String prestadorNumeroDocumento) {
        this.prestadorNumeroDocumento = prestadorNumeroDocumento;
    }

    public String getPrestadorRazonSocial() {
        return prestadorRazonSocial;
    }

    public void setPrestadorRazonSocial(String prestadorRazonSocial) {
        this.prestadorRazonSocial = prestadorRazonSocial;
    }

    public String getSedeCodigoHabilitacion() {
        return sedeCodigoHabilitacion;
    }

    public void setSedeCodigoHabilitacion(String sedeCodigoHabilitacion) {
        this.sedeCodigoHabilitacion = sedeCodigoHabilitacion;
    }

    public String getSedeDireccion() {
        return sedeDireccion;
    }

    public void setSedeDireccion(String sedeDireccion) {
        this.sedeDireccion = sedeDireccion;
    }

    public String getSedeTelefono() {
        return sedeTelefono;
    }

    public void setSedeTelefono(String sedeTelefono) {
        this.sedeTelefono = sedeTelefono;
    }

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Integer getIdDireccionamiento() {
        return idDireccionamiento;
    }

    public void setIdDireccionamiento(Integer idDireccionamiento) {
        this.idDireccionamiento = idDireccionamiento;
    }

    public Date getFechaDireccionamiento() {
        return fechaDireccionamiento;
    }

    public void setFechaDireccionamiento(Date fechaDireccionamiento) {
        this.fechaDireccionamiento = fechaDireccionamiento;
    }

    public Date getFechaMaxEntrega() {
        return fechaMaxEntrega;
    }

    public void setFechaMaxEntrega(Date fechaMaxEntrega) {
        this.fechaMaxEntrega = fechaMaxEntrega;
    }

    public Integer getEntregaNumero() {
        return entregaNumero;
    }

    public void setEntregaNumero(Integer entregaNumero) {
        this.entregaNumero = entregaNumero;
    }

    public Integer getEntregaCantidad() {
        return entregaCantidad;
    }

    public void setEntregaCantidad(Integer entregaCantidad) {
        this.entregaCantidad = entregaCantidad;
    }

    public Integer getEntregaTotal() {
        return entregaTotal;
    }

    public void setEntregaTotal(Integer entregaTotal) {
        this.entregaTotal = entregaTotal;
    }

    public Integer getEntregadoNumero() {
        return entregadoNumero;
    }

    public void setEntregadoNumero(Integer entregadoNumero) {
        this.entregadoNumero = entregadoNumero;
    }

    public Integer getEntregadoTotal() {
        return entregadoTotal;
    }

    public void setEntregadoTotal(Integer entregadoTotal) {
        this.entregadoTotal = entregadoTotal;
    }

    public Integer getEntregadoPendiente() {
        return entregadoPendiente;
    }

    public void setEntregadoPendiente(Integer entregadoPendiente) {
        this.entregadoPendiente = entregadoPendiente;
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

    @XmlTransient
    public List<MpProgramadaEntregas> getMpProgramadaEntregasList() {
        return mpProgramadaEntregasList;
    }

    public void setMpProgramadaEntregasList(List<MpProgramadaEntregas> mpProgramadaEntregasList) {
        this.mpProgramadaEntregasList = mpProgramadaEntregasList;
    }

    public MpPrescripciones getMpPrescripcionesId() {
        return mpPrescripcionesId;
    }

    public void setMpPrescripcionesId(MpPrescripciones mpPrescripcionesId) {
        this.mpPrescripcionesId = mpPrescripcionesId;
    }

    public MpPrescripcionInsumos getMpPrescripcionInsumosId() {
        return mpPrescripcionInsumosId;
    }

    public void setMpPrescripcionInsumosId(MpPrescripcionInsumos mpPrescripcionInsumosId) {
        this.mpPrescripcionInsumosId = mpPrescripcionInsumosId;
    }

    public MpPrescripcionMedicamentos getMpPrescripcionMedicamentosId() {
        return mpPrescripcionMedicamentosId;
    }

    public void setMpPrescripcionMedicamentosId(MpPrescripcionMedicamentos mpPrescripcionMedicamentosId) {
        this.mpPrescripcionMedicamentosId = mpPrescripcionMedicamentosId;
    }

    public MpPrescripcionTecnologias getMpPrescripcionTecnologiasId() {
        return mpPrescripcionTecnologiasId;
    }

    public void setMpPrescripcionTecnologiasId(MpPrescripcionTecnologias mpPrescripcionTecnologiasId) {
        this.mpPrescripcionTecnologiasId = mpPrescripcionTecnologiasId;
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
        if (!(object instanceof MpPrescripcionProgramadas)) {
            return false;
        }
        MpPrescripcionProgramadas other = (MpPrescripcionProgramadas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.MpPrescripcionProgramadas[ id=" + id + " ]";
    }
    
}
