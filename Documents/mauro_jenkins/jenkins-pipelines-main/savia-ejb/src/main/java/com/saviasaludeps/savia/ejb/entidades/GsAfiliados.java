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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author stive
 */
@Entity
@Table(name = "gs_afiliados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GsAfiliados.findAll", query = "SELECT g FROM GsAfiliados g"),
    @NamedQuery(name = "GsAfiliados.findById", query = "SELECT g FROM GsAfiliados g WHERE g.id = :id"),
    @NamedQuery(name = "GsAfiliados.findByDocumentoTipo", query = "SELECT g FROM GsAfiliados g WHERE g.documentoTipo = :documentoTipo"),
    @NamedQuery(name = "GsAfiliados.findByDocumentoNumero", query = "SELECT g FROM GsAfiliados g WHERE g.documentoNumero = :documentoNumero"),
    @NamedQuery(name = "GsAfiliados.findByDocumentoFechaExpedicion", query = "SELECT g FROM GsAfiliados g WHERE g.documentoFechaExpedicion = :documentoFechaExpedicion"),
    @NamedQuery(name = "GsAfiliados.findByPrimerNombre", query = "SELECT g FROM GsAfiliados g WHERE g.primerNombre = :primerNombre"),
    @NamedQuery(name = "GsAfiliados.findBySegundoNombre", query = "SELECT g FROM GsAfiliados g WHERE g.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "GsAfiliados.findByPrimerApellido", query = "SELECT g FROM GsAfiliados g WHERE g.primerApellido = :primerApellido"),
    @NamedQuery(name = "GsAfiliados.findBySegundoApellido", query = "SELECT g FROM GsAfiliados g WHERE g.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "GsAfiliados.findByFechaNacimiento", query = "SELECT g FROM GsAfiliados g WHERE g.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "GsAfiliados.findBySexo", query = "SELECT g FROM GsAfiliados g WHERE g.sexo = :sexo"),
    @NamedQuery(name = "GsAfiliados.findByResidenciaUbicacionNombre", query = "SELECT g FROM GsAfiliados g WHERE g.residenciaUbicacionNombre = :residenciaUbicacionNombre"),
    @NamedQuery(name = "GsAfiliados.findByResidenciaDireccion", query = "SELECT g FROM GsAfiliados g WHERE g.residenciaDireccion = :residenciaDireccion"),
    @NamedQuery(name = "GsAfiliados.findByResidenciaZonaTipo", query = "SELECT g FROM GsAfiliados g WHERE g.residenciaZonaTipo = :residenciaZonaTipo"),
    @NamedQuery(name = "GsAfiliados.findByAtencionUbicacionNombre", query = "SELECT g FROM GsAfiliados g WHERE g.atencionUbicacionNombre = :atencionUbicacionNombre")})
public class GsAfiliados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 8)
    @Column(name = "documento_tipo")
    private String documentoTipo;
    @Size(max = 16)
    @Column(name = "documento_numero")
    private String documentoNumero;
    @Column(name = "documento_fecha_expedicion")
    @Temporal(TemporalType.DATE)
    private Date documentoFechaExpedicion;
    @Size(max = 64)
    @Column(name = "primer_nombre")
    private String primerNombre;
    @Size(max = 64)
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    @Size(max = 64)
    @Column(name = "primer_apellido")
    private String primerApellido;
    @Size(max = 64)
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Size(max = 16)
    @Column(name = "sexo")
    private String sexo;
    @Size(max = 512)
    @Column(name = "residencia_ubicacion_nombre")
    private String residenciaUbicacionNombre;
    @Size(max = 256)
    @Column(name = "residencia_direccion")
    private String residenciaDireccion;
    @Size(max = 16)
    @Column(name = "residencia_zona_tipo")
    private String residenciaZonaTipo;
    @Size(max = 512)
    @Column(name = "atencion_ubicacion_nombre")
    private String atencionUbicacionNombre;
    @JoinColumn(name = "residencia_ubicaciones_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUbicaciones residenciaUbicacionesId;
    @JoinColumn(name = "atencion_ubicaciones_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GnUbicaciones atencionUbicacionesId;
    @OneToMany(mappedBy = "gsAfiliadosId", fetch = FetchType.LAZY)
    private List<GsSolicitudes> gsSolicitudesList;

    public GsAfiliados() {
    }

    public GsAfiliados(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumentoTipo() {
        return documentoTipo;
    }

    public void setDocumentoTipo(String documentoTipo) {
        this.documentoTipo = documentoTipo;
    }

    public String getDocumentoNumero() {
        return documentoNumero;
    }

    public void setDocumentoNumero(String documentoNumero) {
        this.documentoNumero = documentoNumero;
    }

    public Date getDocumentoFechaExpedicion() {
        return documentoFechaExpedicion;
    }

    public void setDocumentoFechaExpedicion(Date documentoFechaExpedicion) {
        this.documentoFechaExpedicion = documentoFechaExpedicion;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getResidenciaUbicacionNombre() {
        return residenciaUbicacionNombre;
    }

    public void setResidenciaUbicacionNombre(String residenciaUbicacionNombre) {
        this.residenciaUbicacionNombre = residenciaUbicacionNombre;
    }

    public String getResidenciaDireccion() {
        return residenciaDireccion;
    }

    public void setResidenciaDireccion(String residenciaDireccion) {
        this.residenciaDireccion = residenciaDireccion;
    }

    public String getResidenciaZonaTipo() {
        return residenciaZonaTipo;
    }

    public void setResidenciaZonaTipo(String residenciaZonaTipo) {
        this.residenciaZonaTipo = residenciaZonaTipo;
    }

    public String getAtencionUbicacionNombre() {
        return atencionUbicacionNombre;
    }

    public void setAtencionUbicacionNombre(String atencionUbicacionNombre) {
        this.atencionUbicacionNombre = atencionUbicacionNombre;
    }

    public GnUbicaciones getResidenciaUbicacionesId() {
        return residenciaUbicacionesId;
    }

    public void setResidenciaUbicacionesId(GnUbicaciones residenciaUbicacionesId) {
        this.residenciaUbicacionesId = residenciaUbicacionesId;
    }

    public GnUbicaciones getAtencionUbicacionesId() {
        return atencionUbicacionesId;
    }

    public void setAtencionUbicacionesId(GnUbicaciones atencionUbicacionesId) {
        this.atencionUbicacionesId = atencionUbicacionesId;
    }

    @XmlTransient
    public List<GsSolicitudes> getGsSolicitudesList() {
        return gsSolicitudesList;
    }

    public void setGsSolicitudesList(List<GsSolicitudes> gsSolicitudesList) {
        this.gsSolicitudesList = gsSolicitudesList;
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
        if (!(object instanceof GsAfiliados)) {
            return false;
        }
        GsAfiliados other = (GsAfiliados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.GsAfiliados[ id=" + id + " ]";
    }
    
}
