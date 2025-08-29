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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "sc_indicadores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScIndicadores.findAll", query = "SELECT s FROM ScIndicadores s"),
    @NamedQuery(name = "ScIndicadores.findById", query = "SELECT s FROM ScIndicadores s WHERE s.id = :id"),
    @NamedQuery(name = "ScIndicadores.findByCodigo", query = "SELECT s FROM ScIndicadores s WHERE s.codigo = :codigo"),
    @NamedQuery(name = "ScIndicadores.findByNombre", query = "SELECT s FROM ScIndicadores s WHERE s.nombre = :nombre"),
    @NamedQuery(name = "ScIndicadores.findByDescripcion", query = "SELECT s FROM ScIndicadores s WHERE s.descripcion = :descripcion"),
    @NamedQuery(name = "ScIndicadores.findByObjetivo", query = "SELECT s FROM ScIndicadores s WHERE s.objetivo = :objetivo"),
    @NamedQuery(name = "ScIndicadores.findByMaeClaseId", query = "SELECT s FROM ScIndicadores s WHERE s.maeClaseId = :maeClaseId"),
    @NamedQuery(name = "ScIndicadores.findByMaeClaseCodigo", query = "SELECT s FROM ScIndicadores s WHERE s.maeClaseCodigo = :maeClaseCodigo"),
    @NamedQuery(name = "ScIndicadores.findByMaeClaseValor", query = "SELECT s FROM ScIndicadores s WHERE s.maeClaseValor = :maeClaseValor"),
    @NamedQuery(name = "ScIndicadores.findByMaeClaseTipo", query = "SELECT s FROM ScIndicadores s WHERE s.maeClaseTipo = :maeClaseTipo"),
    @NamedQuery(name = "ScIndicadores.findByMaeMacroprocesoId", query = "SELECT s FROM ScIndicadores s WHERE s.maeMacroprocesoId = :maeMacroprocesoId"),
    @NamedQuery(name = "ScIndicadores.findByMaeMacroprocesoCodigo", query = "SELECT s FROM ScIndicadores s WHERE s.maeMacroprocesoCodigo = :maeMacroprocesoCodigo"),
    @NamedQuery(name = "ScIndicadores.findByMaeMacroprocesoValor", query = "SELECT s FROM ScIndicadores s WHERE s.maeMacroprocesoValor = :maeMacroprocesoValor"),
    @NamedQuery(name = "ScIndicadores.findByMaeMacroprocesoTipo", query = "SELECT s FROM ScIndicadores s WHERE s.maeMacroprocesoTipo = :maeMacroprocesoTipo"),
    @NamedQuery(name = "ScIndicadores.findByMaeProcesoId", query = "SELECT s FROM ScIndicadores s WHERE s.maeProcesoId = :maeProcesoId"),
    @NamedQuery(name = "ScIndicadores.findByMaeProcesoCodigo", query = "SELECT s FROM ScIndicadores s WHERE s.maeProcesoCodigo = :maeProcesoCodigo"),
    @NamedQuery(name = "ScIndicadores.findByMaeProcesoValor", query = "SELECT s FROM ScIndicadores s WHERE s.maeProcesoValor = :maeProcesoValor"),
    @NamedQuery(name = "ScIndicadores.findByMaeProcesoTipo", query = "SELECT s FROM ScIndicadores s WHERE s.maeProcesoTipo = :maeProcesoTipo"),
    @NamedQuery(name = "ScIndicadores.findByMaeAreaId", query = "SELECT s FROM ScIndicadores s WHERE s.maeAreaId = :maeAreaId"),
    @NamedQuery(name = "ScIndicadores.findByMaeAreaCodigo", query = "SELECT s FROM ScIndicadores s WHERE s.maeAreaCodigo = :maeAreaCodigo"),
    @NamedQuery(name = "ScIndicadores.findByMaeAreaValor", query = "SELECT s FROM ScIndicadores s WHERE s.maeAreaValor = :maeAreaValor"),
    @NamedQuery(name = "ScIndicadores.findByMaeAreaTipo", query = "SELECT s FROM ScIndicadores s WHERE s.maeAreaTipo = :maeAreaTipo"),
    @NamedQuery(name = "ScIndicadores.findByTipo", query = "SELECT s FROM ScIndicadores s WHERE s.tipo = :tipo"),
    @NamedQuery(name = "ScIndicadores.findByNormativa", query = "SELECT s FROM ScIndicadores s WHERE s.normativa = :normativa"),
    @NamedQuery(name = "ScIndicadores.findByActivo", query = "SELECT s FROM ScIndicadores s WHERE s.activo = :activo"),
    @NamedQuery(name = "ScIndicadores.findByBorrado", query = "SELECT s FROM ScIndicadores s WHERE s.borrado = :borrado"),
    @NamedQuery(name = "ScIndicadores.findByBorradoObservacion", query = "SELECT s FROM ScIndicadores s WHERE s.borradoObservacion = :borradoObservacion"),
    @NamedQuery(name = "ScIndicadores.findByUsuarioCrea", query = "SELECT s FROM ScIndicadores s WHERE s.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "ScIndicadores.findByTerminalCrea", query = "SELECT s FROM ScIndicadores s WHERE s.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "ScIndicadores.findByFechaHoraCrea", query = "SELECT s FROM ScIndicadores s WHERE s.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "ScIndicadores.findByUsuarioModifica", query = "SELECT s FROM ScIndicadores s WHERE s.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "ScIndicadores.findByTerminalModifica", query = "SELECT s FROM ScIndicadores s WHERE s.terminalModifica = :terminalModifica"),
    @NamedQuery(name = "ScIndicadores.findByFechaHoraModifica", query = "SELECT s FROM ScIndicadores s WHERE s.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "ScIndicadores.findByUsuarioBorra", query = "SELECT s FROM ScIndicadores s WHERE s.usuarioBorra = :usuarioBorra"),
    @NamedQuery(name = "ScIndicadores.findByTerminalBorra", query = "SELECT s FROM ScIndicadores s WHERE s.terminalBorra = :terminalBorra"),
    @NamedQuery(name = "ScIndicadores.findByFechaHoraBorra", query = "SELECT s FROM ScIndicadores s WHERE s.fechaHoraBorra = :fechaHoraBorra")})
public class ScIndicadores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 512)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 1024)
    @Column(name = "objetivo")
    private String objetivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_clase_id")
    private int maeClaseId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_clase_codigo")
    private String maeClaseCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_clase_valor")
    private String maeClaseValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "mae_clase_tipo")
    private String maeClaseTipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_macroproceso_id")
    private int maeMacroprocesoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "mae_macroproceso_codigo")
    private String maeMacroprocesoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_macroproceso_valor")
    private String maeMacroprocesoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "mae_macroproceso_tipo")
    private String maeMacroprocesoTipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_proceso_id")
    private int maeProcesoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_proceso_codigo")
    private String maeProcesoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_proceso_valor")
    private String maeProcesoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "mae_proceso_tipo")
    private String maeProcesoTipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mae_area_id")
    private int maeAreaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_area_codigo")
    private String maeAreaCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "mae_area_valor")
    private String maeAreaValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "mae_area_tipo")
    private String maeAreaTipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo")
    private int tipo;
    @Size(max = 2048)
    @Column(name = "normativa")
    private String normativa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "borrado")
    private boolean borrado;
    @Size(max = 2048)
    @Column(name = "borrado_observacion")
    private String borradoObservacion;
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

    public ScIndicadores() {
    }

    public ScIndicadores(Integer id) {
        this.id = id;
    }

    public ScIndicadores(Integer id, String codigo, String nombre, int maeClaseId, String maeClaseCodigo, String maeClaseValor, String maeClaseTipo, int maeMacroprocesoId, String maeMacroprocesoCodigo, String maeMacroprocesoValor, String maeMacroprocesoTipo, int maeProcesoId, String maeProcesoCodigo, String maeProcesoValor, String maeProcesoTipo, int maeAreaId, String maeAreaCodigo, String maeAreaValor, String maeAreaTipo, int tipo, boolean activo, boolean borrado, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.maeClaseId = maeClaseId;
        this.maeClaseCodigo = maeClaseCodigo;
        this.maeClaseValor = maeClaseValor;
        this.maeClaseTipo = maeClaseTipo;
        this.maeMacroprocesoId = maeMacroprocesoId;
        this.maeMacroprocesoCodigo = maeMacroprocesoCodigo;
        this.maeMacroprocesoValor = maeMacroprocesoValor;
        this.maeMacroprocesoTipo = maeMacroprocesoTipo;
        this.maeProcesoId = maeProcesoId;
        this.maeProcesoCodigo = maeProcesoCodigo;
        this.maeProcesoValor = maeProcesoValor;
        this.maeProcesoTipo = maeProcesoTipo;
        this.maeAreaId = maeAreaId;
        this.maeAreaCodigo = maeAreaCodigo;
        this.maeAreaValor = maeAreaValor;
        this.maeAreaTipo = maeAreaTipo;
        this.tipo = tipo;
        this.activo = activo;
        this.borrado = borrado;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public int getMaeClaseId() {
        return maeClaseId;
    }

    public void setMaeClaseId(int maeClaseId) {
        this.maeClaseId = maeClaseId;
    }

    public String getMaeClaseCodigo() {
        return maeClaseCodigo;
    }

    public void setMaeClaseCodigo(String maeClaseCodigo) {
        this.maeClaseCodigo = maeClaseCodigo;
    }

    public String getMaeClaseValor() {
        return maeClaseValor;
    }

    public void setMaeClaseValor(String maeClaseValor) {
        this.maeClaseValor = maeClaseValor;
    }

    public String getMaeClaseTipo() {
        return maeClaseTipo;
    }

    public void setMaeClaseTipo(String maeClaseTipo) {
        this.maeClaseTipo = maeClaseTipo;
    }

    public int getMaeMacroprocesoId() {
        return maeMacroprocesoId;
    }

    public void setMaeMacroprocesoId(int maeMacroprocesoId) {
        this.maeMacroprocesoId = maeMacroprocesoId;
    }

    public String getMaeMacroprocesoCodigo() {
        return maeMacroprocesoCodigo;
    }

    public void setMaeMacroprocesoCodigo(String maeMacroprocesoCodigo) {
        this.maeMacroprocesoCodigo = maeMacroprocesoCodigo;
    }

    public String getMaeMacroprocesoValor() {
        return maeMacroprocesoValor;
    }

    public void setMaeMacroprocesoValor(String maeMacroprocesoValor) {
        this.maeMacroprocesoValor = maeMacroprocesoValor;
    }

    public String getMaeMacroprocesoTipo() {
        return maeMacroprocesoTipo;
    }

    public void setMaeMacroprocesoTipo(String maeMacroprocesoTipo) {
        this.maeMacroprocesoTipo = maeMacroprocesoTipo;
    }

    public int getMaeProcesoId() {
        return maeProcesoId;
    }

    public void setMaeProcesoId(int maeProcesoId) {
        this.maeProcesoId = maeProcesoId;
    }

    public String getMaeProcesoCodigo() {
        return maeProcesoCodigo;
    }

    public void setMaeProcesoCodigo(String maeProcesoCodigo) {
        this.maeProcesoCodigo = maeProcesoCodigo;
    }

    public String getMaeProcesoValor() {
        return maeProcesoValor;
    }

    public void setMaeProcesoValor(String maeProcesoValor) {
        this.maeProcesoValor = maeProcesoValor;
    }

    public String getMaeProcesoTipo() {
        return maeProcesoTipo;
    }

    public void setMaeProcesoTipo(String maeProcesoTipo) {
        this.maeProcesoTipo = maeProcesoTipo;
    }

    public int getMaeAreaId() {
        return maeAreaId;
    }

    public void setMaeAreaId(int maeAreaId) {
        this.maeAreaId = maeAreaId;
    }

    public String getMaeAreaCodigo() {
        return maeAreaCodigo;
    }

    public void setMaeAreaCodigo(String maeAreaCodigo) {
        this.maeAreaCodigo = maeAreaCodigo;
    }

    public String getMaeAreaValor() {
        return maeAreaValor;
    }

    public void setMaeAreaValor(String maeAreaValor) {
        this.maeAreaValor = maeAreaValor;
    }

    public String getMaeAreaTipo() {
        return maeAreaTipo;
    }

    public void setMaeAreaTipo(String maeAreaTipo) {
        this.maeAreaTipo = maeAreaTipo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNormativa() {
        return normativa;
    }

    public void setNormativa(String normativa) {
        this.normativa = normativa;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public String getBorradoObservacion() {
        return borradoObservacion;
    }

    public void setBorradoObservacion(String borradoObservacion) {
        this.borradoObservacion = borradoObservacion;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScIndicadores)) {
            return false;
        }
        ScIndicadores other = (ScIndicadores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.ScIndicadores[ id=" + id + " ]";
    }
    
}
