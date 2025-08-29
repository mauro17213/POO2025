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
import javax.persistence.Lob;
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
@Table(name = "aseg_portabilidades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsegPortabilidades.findAll", query = "SELECT a FROM AsegPortabilidades a"),
    @NamedQuery(name = "AsegPortabilidades.findById", query = "SELECT a FROM AsegPortabilidades a WHERE a.id = :id"),
    @NamedQuery(name = "AsegPortabilidades.findByPeriodoInicial", query = "SELECT a FROM AsegPortabilidades a WHERE a.periodoInicial = :periodoInicial"),
    @NamedQuery(name = "AsegPortabilidades.findByPeriodoFinal", query = "SELECT a FROM AsegPortabilidades a WHERE a.periodoFinal = :periodoFinal"),
    @NamedQuery(name = "AsegPortabilidades.findByOrigenSolicitud", query = "SELECT a FROM AsegPortabilidades a WHERE a.origenSolicitud = :origenSolicitud"),
    @NamedQuery(name = "AsegPortabilidades.findByMaeOrigenSolicitudId", query = "SELECT a FROM AsegPortabilidades a WHERE a.maeOrigenSolicitudId = :maeOrigenSolicitudId"),
    @NamedQuery(name = "AsegPortabilidades.findByMaeOrigenSolicitudCodigo", query = "SELECT a FROM AsegPortabilidades a WHERE a.maeOrigenSolicitudCodigo = :maeOrigenSolicitudCodigo"),
    @NamedQuery(name = "AsegPortabilidades.findByMaeOrigenSolicitudValor", query = "SELECT a FROM AsegPortabilidades a WHERE a.maeOrigenSolicitudValor = :maeOrigenSolicitudValor"),
    @NamedQuery(name = "AsegPortabilidades.findByTipoPortabilidad", query = "SELECT a FROM AsegPortabilidades a WHERE a.tipoPortabilidad = :tipoPortabilidad"),
    @NamedQuery(name = "AsegPortabilidades.findByMaeTipoPortabilidadId", query = "SELECT a FROM AsegPortabilidades a WHERE a.maeTipoPortabilidadId = :maeTipoPortabilidadId"),
    @NamedQuery(name = "AsegPortabilidades.findByMaeTipoPortabilidadCodigo", query = "SELECT a FROM AsegPortabilidades a WHERE a.maeTipoPortabilidadCodigo = :maeTipoPortabilidadCodigo"),
    @NamedQuery(name = "AsegPortabilidades.findByMaeTipoPortabilidadValor", query = "SELECT a FROM AsegPortabilidades a WHERE a.maeTipoPortabilidadValor = :maeTipoPortabilidadValor"),
    @NamedQuery(name = "AsegPortabilidades.findByMaeMotivoId", query = "SELECT a FROM AsegPortabilidades a WHERE a.maeMotivoId = :maeMotivoId"),
    @NamedQuery(name = "AsegPortabilidades.findByMaeMotivoCodigo", query = "SELECT a FROM AsegPortabilidades a WHERE a.maeMotivoCodigo = :maeMotivoCodigo"),
    @NamedQuery(name = "AsegPortabilidades.findByMaeMotivoValor", query = "SELECT a FROM AsegPortabilidades a WHERE a.maeMotivoValor = :maeMotivoValor"),
    @NamedQuery(name = "AsegPortabilidades.findByEstadoPortabilidad", query = "SELECT a FROM AsegPortabilidades a WHERE a.estadoPortabilidad = :estadoPortabilidad"),
    @NamedQuery(name = "AsegPortabilidades.findByMaeEstadoPortabilidadId", query = "SELECT a FROM AsegPortabilidades a WHERE a.maeEstadoPortabilidadId = :maeEstadoPortabilidadId"),
    @NamedQuery(name = "AsegPortabilidades.findByMaeEstadoPortabilidadCodigo", query = "SELECT a FROM AsegPortabilidades a WHERE a.maeEstadoPortabilidadCodigo = :maeEstadoPortabilidadCodigo"),
    @NamedQuery(name = "AsegPortabilidades.findByMaeEstadoPortabilidadValor", query = "SELECT a FROM AsegPortabilidades a WHERE a.maeEstadoPortabilidadValor = :maeEstadoPortabilidadValor"),
    @NamedQuery(name = "AsegPortabilidades.findByDireccion", query = "SELECT a FROM AsegPortabilidades a WHERE a.direccion = :direccion"),
    @NamedQuery(name = "AsegPortabilidades.findByTelefonoContacto", query = "SELECT a FROM AsegPortabilidades a WHERE a.telefonoContacto = :telefonoContacto"),
    @NamedQuery(name = "AsegPortabilidades.findByTelefonoContacto2", query = "SELECT a FROM AsegPortabilidades a WHERE a.telefonoContacto2 = :telefonoContacto2"),
    @NamedQuery(name = "AsegPortabilidades.findByCorreoElectronico", query = "SELECT a FROM AsegPortabilidades a WHERE a.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "AsegPortabilidades.findByEnvioCorreo", query = "SELECT a FROM AsegPortabilidades a WHERE a.envioCorreo = :envioCorreo"),
    @NamedQuery(name = "AsegPortabilidades.findByFechaSolicitudCancelacion", query = "SELECT a FROM AsegPortabilidades a WHERE a.fechaSolicitudCancelacion = :fechaSolicitudCancelacion"),
    @NamedQuery(name = "AsegPortabilidades.findByFechaCancelacion", query = "SELECT a FROM AsegPortabilidades a WHERE a.fechaCancelacion = :fechaCancelacion"),
    @NamedQuery(name = "AsegPortabilidades.findByUsuarioCancela", query = "SELECT a FROM AsegPortabilidades a WHERE a.usuarioCancela = :usuarioCancela"),
    @NamedQuery(name = "AsegPortabilidades.findByNumeroProrroga", query = "SELECT a FROM AsegPortabilidades a WHERE a.numeroProrroga = :numeroProrroga"),
    @NamedQuery(name = "AsegPortabilidades.findByMesesProrroga", query = "SELECT a FROM AsegPortabilidades a WHERE a.mesesProrroga = :mesesProrroga"),
    @NamedQuery(name = "AsegPortabilidades.findByFechaProrroga", query = "SELECT a FROM AsegPortabilidades a WHERE a.fechaProrroga = :fechaProrroga"),
    @NamedQuery(name = "AsegPortabilidades.findByUsuarioCrea", query = "SELECT a FROM AsegPortabilidades a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AsegPortabilidades.findByTerminalCrea", query = "SELECT a FROM AsegPortabilidades a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AsegPortabilidades.findByFechaHoraCrea", query = "SELECT a FROM AsegPortabilidades a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AsegPortabilidades.findByUsuarioModifica", query = "SELECT a FROM AsegPortabilidades a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AsegPortabilidades.findByTerminalModifica", query = "SELECT a FROM AsegPortabilidades a WHERE a.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "AsegPortabilidades.findByFechaHoraModifica", query = "SELECT a FROM AsegPortabilidades a WHERE a.fechaHoraModifica = :fechaHoraModifica")})
public class AsegPortabilidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "periodo_inicial")
    @Temporal(TemporalType.DATE)
    private Date periodoInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "periodo_final")
    @Temporal(TemporalType.DATE)
    private Date periodoFinal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "origen_solicitud")
    private int origenSolicitud;
    @Column(name = "mae_origen_solicitud_id")
    private Integer maeOrigenSolicitudId;
    @Size(max = 8)
    @Column(name = "mae_origen_solicitud_codigo")
    private String maeOrigenSolicitudCodigo;
    @Size(max = 128)
    @Column(name = "mae_origen_solicitud_valor")
    private String maeOrigenSolicitudValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_portabilidad")
    private int tipoPortabilidad;
    @Column(name = "mae_tipo_portabilidad_id")
    private Integer maeTipoPortabilidadId;
    @Size(max = 8)
    @Column(name = "mae_tipo_portabilidad_codigo")
    private String maeTipoPortabilidadCodigo;
    @Size(max = 128)
    @Column(name = "mae_tipo_portabilidad_valor")
    private String maeTipoPortabilidadValor;
    @Column(name = "mae_motivo_id")
    private Integer maeMotivoId;
    @Size(max = 8)
    @Column(name = "mae_motivo_codigo")
    private String maeMotivoCodigo;
    @Size(max = 128)
    @Column(name = "mae_motivo_valor")
    private String maeMotivoValor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_portabilidad")
    private int estadoPortabilidad;
    @Column(name = "mae_estado_portabilidad_id")
    private Integer maeEstadoPortabilidadId;
    @Size(max = 8)
    @Column(name = "mae_estado_portabilidad_codigo")
    private String maeEstadoPortabilidadCodigo;
    @Size(max = 128)
    @Column(name = "mae_estado_portabilidad_valor")
    private String maeEstadoPortabilidadValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 10)
    @Column(name = "telefono_contacto")
    private String telefonoContacto;
    @Size(max = 10)
    @Column(name = "telefono_contacto_2")
    private String telefonoContacto2;
    @Size(max = 512)
    @Column(name = "correo_electronico")
    private String correoElectronico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "envio_correo")
    private short envioCorreo;
    @Lob
    @Size(max = 65535)
    @Column(name = "observacion_usuario")
    private String observacionUsuario;
    @Lob
    @Size(max = 65535)
    @Column(name = "observacion_aseguramiento")
    private String observacionAseguramiento;
    @Column(name = "fecha_solicitud_cancelacion")
    @Temporal(TemporalType.DATE)
    private Date fechaSolicitudCancelacion;
    @Column(name = "fecha_cancelacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCancelacion;
    @Size(max = 128)
    @Column(name = "usuario_cancela")
    private String usuarioCancela;
    @Lob
    @Size(max = 65535)
    @Column(name = "observacion_cancelacion")
    private String observacionCancelacion;
    @Column(name = "numero_prorroga")
    private Integer numeroProrroga;
    @Column(name = "meses_prorroga")
    private Integer mesesProrroga;
    @Column(name = "fecha_prorroga")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaProrroga;
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
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;
    @JoinColumn(name = "ubicaciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private GnUbicaciones ubicacionesId;
    @JoinColumn(name = "cnt_prestador_sedes_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CntPrestadorSedes cntPrestadorSedesId;

    public AsegPortabilidades() {
    }

    public AsegPortabilidades(Integer id) {
        this.id = id;
    }

    public AsegPortabilidades(Integer id, Date periodoInicial, Date periodoFinal, int origenSolicitud, int tipoPortabilidad, int estadoPortabilidad, String direccion, short envioCorreo, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.periodoInicial = periodoInicial;
        this.periodoFinal = periodoFinal;
        this.origenSolicitud = origenSolicitud;
        this.tipoPortabilidad = tipoPortabilidad;
        this.estadoPortabilidad = estadoPortabilidad;
        this.direccion = direccion;
        this.envioCorreo = envioCorreo;
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

    public Date getPeriodoInicial() {
        return periodoInicial;
    }

    public void setPeriodoInicial(Date periodoInicial) {
        this.periodoInicial = periodoInicial;
    }

    public Date getPeriodoFinal() {
        return periodoFinal;
    }

    public void setPeriodoFinal(Date periodoFinal) {
        this.periodoFinal = periodoFinal;
    }

    public int getOrigenSolicitud() {
        return origenSolicitud;
    }

    public void setOrigenSolicitud(int origenSolicitud) {
        this.origenSolicitud = origenSolicitud;
    }

    public Integer getMaeOrigenSolicitudId() {
        return maeOrigenSolicitudId;
    }

    public void setMaeOrigenSolicitudId(Integer maeOrigenSolicitudId) {
        this.maeOrigenSolicitudId = maeOrigenSolicitudId;
    }

    public String getMaeOrigenSolicitudCodigo() {
        return maeOrigenSolicitudCodigo;
    }

    public void setMaeOrigenSolicitudCodigo(String maeOrigenSolicitudCodigo) {
        this.maeOrigenSolicitudCodigo = maeOrigenSolicitudCodigo;
    }

    public String getMaeOrigenSolicitudValor() {
        return maeOrigenSolicitudValor;
    }

    public void setMaeOrigenSolicitudValor(String maeOrigenSolicitudValor) {
        this.maeOrigenSolicitudValor = maeOrigenSolicitudValor;
    }

    public int getTipoPortabilidad() {
        return tipoPortabilidad;
    }

    public void setTipoPortabilidad(int tipoPortabilidad) {
        this.tipoPortabilidad = tipoPortabilidad;
    }

    public Integer getMaeTipoPortabilidadId() {
        return maeTipoPortabilidadId;
    }

    public void setMaeTipoPortabilidadId(Integer maeTipoPortabilidadId) {
        this.maeTipoPortabilidadId = maeTipoPortabilidadId;
    }

    public String getMaeTipoPortabilidadCodigo() {
        return maeTipoPortabilidadCodigo;
    }

    public void setMaeTipoPortabilidadCodigo(String maeTipoPortabilidadCodigo) {
        this.maeTipoPortabilidadCodigo = maeTipoPortabilidadCodigo;
    }

    public String getMaeTipoPortabilidadValor() {
        return maeTipoPortabilidadValor;
    }

    public void setMaeTipoPortabilidadValor(String maeTipoPortabilidadValor) {
        this.maeTipoPortabilidadValor = maeTipoPortabilidadValor;
    }

    public Integer getMaeMotivoId() {
        return maeMotivoId;
    }

    public void setMaeMotivoId(Integer maeMotivoId) {
        this.maeMotivoId = maeMotivoId;
    }

    public String getMaeMotivoCodigo() {
        return maeMotivoCodigo;
    }

    public void setMaeMotivoCodigo(String maeMotivoCodigo) {
        this.maeMotivoCodigo = maeMotivoCodigo;
    }

    public String getMaeMotivoValor() {
        return maeMotivoValor;
    }

    public void setMaeMotivoValor(String maeMotivoValor) {
        this.maeMotivoValor = maeMotivoValor;
    }

    public int getEstadoPortabilidad() {
        return estadoPortabilidad;
    }

    public void setEstadoPortabilidad(int estadoPortabilidad) {
        this.estadoPortabilidad = estadoPortabilidad;
    }

    public Integer getMaeEstadoPortabilidadId() {
        return maeEstadoPortabilidadId;
    }

    public void setMaeEstadoPortabilidadId(Integer maeEstadoPortabilidadId) {
        this.maeEstadoPortabilidadId = maeEstadoPortabilidadId;
    }

    public String getMaeEstadoPortabilidadCodigo() {
        return maeEstadoPortabilidadCodigo;
    }

    public void setMaeEstadoPortabilidadCodigo(String maeEstadoPortabilidadCodigo) {
        this.maeEstadoPortabilidadCodigo = maeEstadoPortabilidadCodigo;
    }

    public String getMaeEstadoPortabilidadValor() {
        return maeEstadoPortabilidadValor;
    }

    public void setMaeEstadoPortabilidadValor(String maeEstadoPortabilidadValor) {
        this.maeEstadoPortabilidadValor = maeEstadoPortabilidadValor;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getTelefonoContacto2() {
        return telefonoContacto2;
    }

    public void setTelefonoContacto2(String telefonoContacto2) {
        this.telefonoContacto2 = telefonoContacto2;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public short getEnvioCorreo() {
        return envioCorreo;
    }

    public void setEnvioCorreo(short envioCorreo) {
        this.envioCorreo = envioCorreo;
    }

    public String getObservacionUsuario() {
        return observacionUsuario;
    }

    public void setObservacionUsuario(String observacionUsuario) {
        this.observacionUsuario = observacionUsuario;
    }

    public String getObservacionAseguramiento() {
        return observacionAseguramiento;
    }

    public void setObservacionAseguramiento(String observacionAseguramiento) {
        this.observacionAseguramiento = observacionAseguramiento;
    }

    public Date getFechaSolicitudCancelacion() {
        return fechaSolicitudCancelacion;
    }

    public void setFechaSolicitudCancelacion(Date fechaSolicitudCancelacion) {
        this.fechaSolicitudCancelacion = fechaSolicitudCancelacion;
    }

    public Date getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(Date fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public String getUsuarioCancela() {
        return usuarioCancela;
    }

    public void setUsuarioCancela(String usuarioCancela) {
        this.usuarioCancela = usuarioCancela;
    }

    public String getObservacionCancelacion() {
        return observacionCancelacion;
    }

    public void setObservacionCancelacion(String observacionCancelacion) {
        this.observacionCancelacion = observacionCancelacion;
    }

    public Integer getNumeroProrroga() {
        return numeroProrroga;
    }

    public void setNumeroProrroga(Integer numeroProrroga) {
        this.numeroProrroga = numeroProrroga;
    }

    public Integer getMesesProrroga() {
        return mesesProrroga;
    }

    public void setMesesProrroga(Integer mesesProrroga) {
        this.mesesProrroga = mesesProrroga;
    }

    public Date getFechaProrroga() {
        return fechaProrroga;
    }

    public void setFechaProrroga(Date fechaProrroga) {
        this.fechaProrroga = fechaProrroga;
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

    public AsegAfiliados getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliados asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    public GnUbicaciones getUbicacionesId() {
        return ubicacionesId;
    }

    public void setUbicacionesId(GnUbicaciones ubicacionesId) {
        this.ubicacionesId = ubicacionesId;
    }

    public CntPrestadorSedes getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSedes cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
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
        if (!(object instanceof AsegPortabilidades)) {
            return false;
        }
        AsegPortabilidades other = (AsegPortabilidades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AsegPortabilidades[ id=" + id + " ]";
    }
    
}
