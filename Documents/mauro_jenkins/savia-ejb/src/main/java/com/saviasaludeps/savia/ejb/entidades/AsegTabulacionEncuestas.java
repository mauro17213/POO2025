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
@Table(name = "aseg_tabulacion_encuestas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsegTabulacionEncuestas.findAll", query = "SELECT a FROM AsegTabulacionEncuestas a"),
    @NamedQuery(name = "AsegTabulacionEncuestas.findById", query = "SELECT a FROM AsegTabulacionEncuestas a WHERE a.id = :id"),
    @NamedQuery(name = "AsegTabulacionEncuestas.findByPrimerApellido", query = "SELECT a FROM AsegTabulacionEncuestas a WHERE a.primerApellido = :primerApellido"),
    @NamedQuery(name = "AsegTabulacionEncuestas.findBySegundoApellido", query = "SELECT a FROM AsegTabulacionEncuestas a WHERE a.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "AsegTabulacionEncuestas.findByPrimerNombre", query = "SELECT a FROM AsegTabulacionEncuestas a WHERE a.primerNombre = :primerNombre"),
    @NamedQuery(name = "AsegTabulacionEncuestas.findBySegundoNombre", query = "SELECT a FROM AsegTabulacionEncuestas a WHERE a.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "AsegTabulacionEncuestas.findByFechaNacimiento", query = "SELECT a FROM AsegTabulacionEncuestas a WHERE a.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "AsegTabulacionEncuestas.findByUbicacionesId", query = "SELECT a FROM AsegTabulacionEncuestas a WHERE a.ubicacionesId = :ubicacionesId"),
    @NamedQuery(name = "AsegTabulacionEncuestas.findByRespuesta", query = "SELECT a FROM AsegTabulacionEncuestas a WHERE a.respuesta = :respuesta"),
    @NamedQuery(name = "AsegTabulacionEncuestas.findByObservacion", query = "SELECT a FROM AsegTabulacionEncuestas a WHERE a.observacion = :observacion"),
    @NamedQuery(name = "AsegTabulacionEncuestas.findByUsuarioCrea", query = "SELECT a FROM AsegTabulacionEncuestas a WHERE a.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "AsegTabulacionEncuestas.findByFechaHoraCrea", query = "SELECT a FROM AsegTabulacionEncuestas a WHERE a.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "AsegTabulacionEncuestas.findByTerminalCrea", query = "SELECT a FROM AsegTabulacionEncuestas a WHERE a.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "AsegTabulacionEncuestas.findByUsuarioModifica", query = "SELECT a FROM AsegTabulacionEncuestas a WHERE a.usuarioModifica = :usuarioModifica"),
    @NamedQuery(name = "AsegTabulacionEncuestas.findByFechaHoraModifica", query = "SELECT a FROM AsegTabulacionEncuestas a WHERE a.fechaHoraModifica = :fechaHoraModifica"),
    @NamedQuery(name = "AsegTabulacionEncuestas.findByTerminalModifica", query = "SELECT a FROM AsegTabulacionEncuestas a WHERE a.terminalModifica = :terminalModifica")})
public class AsegTabulacionEncuestas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "primer_apellido")
    private String primerApellido;
    @Size(max = 64)
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "primer_nombre")
    private String primerNombre;
    @Size(max = 64)
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ubicaciones_id")
    private int ubicacionesId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "respuesta")
    private boolean respuesta;
    @Size(max = 256)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Size(max = 64)
    @Column(name = "usuario_modifica")
    private String usuarioModifica;
    @Column(name = "fecha_hora_modifica")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraModifica;
    @Size(max = 64)
    @Column(name = "terminal_modifica")
    private String terminalModifica;
    @JoinColumn(name = "aseg_afiliados_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegAfiliados asegAfiliadosId;
    @JoinColumn(name = "aseg_encuesta_preguntas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AsegEncuestaPreguntas asegEncuestaPreguntasId;

    public AsegTabulacionEncuestas() {
    }

    public AsegTabulacionEncuestas(Integer id) {
        this.id = id;
    }

    public AsegTabulacionEncuestas(Integer id, String primerApellido, String primerNombre, Date fechaNacimiento, int ubicacionesId, boolean respuesta, String usuarioCrea, Date fechaHoraCrea, String terminalCrea) {
        this.id = id;
        this.primerApellido = primerApellido;
        this.primerNombre = primerNombre;
        this.fechaNacimiento = fechaNacimiento;
        this.ubicacionesId = ubicacionesId;
        this.respuesta = respuesta;
        this.usuarioCrea = usuarioCrea;
        this.fechaHoraCrea = fechaHoraCrea;
        this.terminalCrea = terminalCrea;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getUbicacionesId() {
        return ubicacionesId;
    }

    public void setUbicacionesId(int ubicacionesId) {
        this.ubicacionesId = ubicacionesId;
    }

    public boolean getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(boolean respuesta) {
        this.respuesta = respuesta;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public String getTerminalCrea() {
        return terminalCrea;
    }

    public void setTerminalCrea(String terminalCrea) {
        this.terminalCrea = terminalCrea;
    }

    public String getUsuarioModifica() {
        return usuarioModifica;
    }

    public void setUsuarioModifica(String usuarioModifica) {
        this.usuarioModifica = usuarioModifica;
    }

    public Date getFechaHoraModifica() {
        return fechaHoraModifica;
    }

    public void setFechaHoraModifica(Date fechaHoraModifica) {
        this.fechaHoraModifica = fechaHoraModifica;
    }

    public String getTerminalModifica() {
        return terminalModifica;
    }

    public void setTerminalModifica(String terminalModifica) {
        this.terminalModifica = terminalModifica;
    }

    public AsegAfiliados getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliados asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    public AsegEncuestaPreguntas getAsegEncuestaPreguntasId() {
        return asegEncuestaPreguntasId;
    }

    public void setAsegEncuestaPreguntasId(AsegEncuestaPreguntas asegEncuestaPreguntasId) {
        this.asegEncuestaPreguntasId = asegEncuestaPreguntasId;
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
        if (!(object instanceof AsegTabulacionEncuestas)) {
            return false;
        }
        AsegTabulacionEncuestas other = (AsegTabulacionEncuestas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.AsegTabulacionEncuestas[ id=" + id + " ]";
    }
    
}
