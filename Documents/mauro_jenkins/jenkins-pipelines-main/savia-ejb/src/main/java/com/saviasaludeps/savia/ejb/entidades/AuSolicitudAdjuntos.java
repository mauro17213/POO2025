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
@Table(name = "au_solicitud_adjuntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuSolicitudAdjuntos.findAll", query = "SELECT a FROM AuSolicitudAdjuntos a"),
    @NamedQuery(name = "AuSolicitudAdjuntos.findById", query = "SELECT a FROM AuSolicitudAdjuntos a WHERE a.id = :id"),
    @NamedQuery(name = "AuSolicitudAdjuntos.findByOrigen", query = "SELECT a FROM AuSolicitudAdjuntos a WHERE a.origen = :origen"),
    @NamedQuery(name = "AuSolicitudAdjuntos.findByAuTopeAfiliadosId", query = "SELECT a FROM AuSolicitudAdjuntos a WHERE a.auTopeAfiliadosId = :auTopeAfiliadosId"),
    @NamedQuery(name = "AuSolicitudAdjuntos.findByMaeTipoArchivoId", query = "SELECT a FROM AuSolicitudAdjuntos a WHERE a.maeTipoArchivoId = :maeTipoArchivoId"),
    @NamedQuery(name = "AuSolicitudAdjuntos.findByMaeTipoArchivoCodigo", query = "SELECT a FROM AuSolicitudAdjuntos a WHERE a.maeTipoArchivoCodigo = :maeTipoArchivoCodigo"),
    @NamedQuery(name = "AuSolicitudAdjuntos.findByMaeTipoArchivoValor", query = "SELECT a FROM AuSolicitudAdjuntos a WHERE a.maeTipoArchivoValor = :maeTipoArchivoValor"),
    @NamedQuery(name = "AuSolicitudAdjuntos.findByNombre", query = "SELECT a FROM AuSolicitudAdjuntos a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "AuSolicitudAdjuntos.findByRuta", query = "SELECT a FROM AuSolicitudAdjuntos a WHERE a.ruta = :ruta"),
    @NamedQuery(name = "AuSolicitudAdjuntos.findByArchivo", query = "SELECT a FROM AuSolicitudAdjuntos a WHERE a.archivo = :archivo"),
    @NamedQuery(name = "AuSolicitudAdjuntos.findByExiste", query = "SELECT a FROM AuSolicitudAdjuntos a WHERE a.existe = :existe"),
    @NamedQuery(name = "AuSolicitudAdjuntos.findByUsuarioCrea", query = "SELECT a FROM AuSolicitudAdjuntos a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AuSolicitudAdjuntos.findByTerminalCrea", query = "SELECT a FROM AuSolicitudAdjuntos a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AuSolicitudAdjuntos.findByFechaHoraCrea", query = "SELECT a FROM AuSolicitudAdjuntos a WHERE a.fechaHoraCrea = :fechaHoraCrea")})
public class AuSolicitudAdjuntos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "origen")
    private int origen;
    @Column(name = "au_tope_afiliados_id")
    private Integer auTopeAfiliadosId;
    @Column(name = "mae_tipo_archivo_id")
    private Integer maeTipoArchivoId;
    @Size(max = 8)
    @Column(name = "mae_tipo_archivo_codigo")
    private String maeTipoArchivoCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_archivo_valor")
    private String maeTipoArchivoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "ruta")
    private String ruta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "archivo")
    private String archivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "existe")
    private boolean existe;
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
    @JoinColumn(name = "au_seguimiento_afiliado_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuSeguimientoAfiliados auSeguimientoAfiliadoId;
    @JoinColumn(name = "au_seguimientos_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuSeguimientos auSeguimientosId;
    @JoinColumn(name = "au_anexo2_rescates_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuAnexo2Rescates auAnexo2RescatesId;
    @JoinColumn(name = "au_anexos2_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuAnexos2 auAnexos2Id;
    @JoinColumn(name = "au_anexos3_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuAnexos3 auAnexos3Id;
    @JoinColumn(name = "au_anexos4_entregas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuAnexo4Entregas auAnexos4EntregasId;
    @JoinColumn(name = "au_cotizaciones_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuCotizaciones auCotizacionesId;
    @JoinColumn(name = "au_no_solicitud_entregas_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuNoSolicitudEntregas auNoSolicitudEntregasId;
    @JoinColumn(name = "au_no_solicitudes_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuNoSolicitudes auNoSolicitudesId;
    @JoinColumn(name = "au_seguimiento_gestiones_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AuSeguimientoGestiones auSeguimientoGestionesId;

    public AuSolicitudAdjuntos() {
    }

    public AuSolicitudAdjuntos(Integer id) {
        this.id = id;
    }

    public AuSolicitudAdjuntos(Integer id, int origen, String nombre, String ruta, String archivo, boolean existe, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.origen = origen;
        this.nombre = nombre;
        this.ruta = ruta;
        this.archivo = archivo;
        this.existe = existe;
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

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public Integer getAuTopeAfiliadosId() {
        return auTopeAfiliadosId;
    }

    public void setAuTopeAfiliadosId(Integer auTopeAfiliadosId) {
        this.auTopeAfiliadosId = auTopeAfiliadosId;
    }

    public Integer getMaeTipoArchivoId() {
        return maeTipoArchivoId;
    }

    public void setMaeTipoArchivoId(Integer maeTipoArchivoId) {
        this.maeTipoArchivoId = maeTipoArchivoId;
    }

    public String getMaeTipoArchivoCodigo() {
        return maeTipoArchivoCodigo;
    }

    public void setMaeTipoArchivoCodigo(String maeTipoArchivoCodigo) {
        this.maeTipoArchivoCodigo = maeTipoArchivoCodigo;
    }

    public String getMaeTipoArchivoValor() {
        return maeTipoArchivoValor;
    }

    public void setMaeTipoArchivoValor(String maeTipoArchivoValor) {
        this.maeTipoArchivoValor = maeTipoArchivoValor;
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

    public AuSeguimientoAfiliados getAuSeguimientoAfiliadoId() {
        return auSeguimientoAfiliadoId;
    }

    public void setAuSeguimientoAfiliadoId(AuSeguimientoAfiliados auSeguimientoAfiliadoId) {
        this.auSeguimientoAfiliadoId = auSeguimientoAfiliadoId;
    }

    public AuSeguimientos getAuSeguimientosId() {
        return auSeguimientosId;
    }

    public void setAuSeguimientosId(AuSeguimientos auSeguimientosId) {
        this.auSeguimientosId = auSeguimientosId;
    }

    public AuAnexo2Rescates getAuAnexo2RescatesId() {
        return auAnexo2RescatesId;
    }

    public void setAuAnexo2RescatesId(AuAnexo2Rescates auAnexo2RescatesId) {
        this.auAnexo2RescatesId = auAnexo2RescatesId;
    }

    public AuAnexos2 getAuAnexos2Id() {
        return auAnexos2Id;
    }

    public void setAuAnexos2Id(AuAnexos2 auAnexos2Id) {
        this.auAnexos2Id = auAnexos2Id;
    }

    public AuAnexos3 getAuAnexos3Id() {
        return auAnexos3Id;
    }

    public void setAuAnexos3Id(AuAnexos3 auAnexos3Id) {
        this.auAnexos3Id = auAnexos3Id;
    }

    public AuAnexo4Entregas getAuAnexos4EntregasId() {
        return auAnexos4EntregasId;
    }

    public void setAuAnexos4EntregasId(AuAnexo4Entregas auAnexos4EntregasId) {
        this.auAnexos4EntregasId = auAnexos4EntregasId;
    }

    public AuCotizaciones getAuCotizacionesId() {
        return auCotizacionesId;
    }

    public void setAuCotizacionesId(AuCotizaciones auCotizacionesId) {
        this.auCotizacionesId = auCotizacionesId;
    }

    public AuNoSolicitudEntregas getAuNoSolicitudEntregasId() {
        return auNoSolicitudEntregasId;
    }

    public void setAuNoSolicitudEntregasId(AuNoSolicitudEntregas auNoSolicitudEntregasId) {
        this.auNoSolicitudEntregasId = auNoSolicitudEntregasId;
    }

    public AuNoSolicitudes getAuNoSolicitudesId() {
        return auNoSolicitudesId;
    }

    public void setAuNoSolicitudesId(AuNoSolicitudes auNoSolicitudesId) {
        this.auNoSolicitudesId = auNoSolicitudesId;
    }

    public AuSeguimientoGestiones getAuSeguimientoGestionesId() {
        return auSeguimientoGestionesId;
    }

    public void setAuSeguimientoGestionesId(AuSeguimientoGestiones auSeguimientoGestionesId) {
        this.auSeguimientoGestionesId = auSeguimientoGestionesId;
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
        if (!(object instanceof AuSolicitudAdjuntos)) {
            return false;
        }
        AuSolicitudAdjuntos other = (AuSolicitudAdjuntos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AuSolicitudAdjuntos[ id=" + id + " ]";
    }
    
}
