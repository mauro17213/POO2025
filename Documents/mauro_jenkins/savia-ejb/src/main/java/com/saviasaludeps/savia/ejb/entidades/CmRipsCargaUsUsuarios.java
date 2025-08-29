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
@Table(name = "cm_rips_carga_us_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findAll", query = "SELECT c FROM CmRipsCargaUsUsuarios c"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findById", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.id = :id"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByFila", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.fila = :fila"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByMaeTipoDocumentoCodigo", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.maeTipoDocumentoCodigo = :maeTipoDocumentoCodigo"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByMaeTipoDocumentoId", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.maeTipoDocumentoId = :maeTipoDocumentoId"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByMaeTipoDocumentoValor", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.maeTipoDocumentoValor = :maeTipoDocumentoValor"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByDocumentoAfiliado", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.documentoAfiliado = :documentoAfiliado"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByAdministradora", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.administradora = :administradora"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByMaeTipoUsuarioCodigo", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.maeTipoUsuarioCodigo = :maeTipoUsuarioCodigo"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByMaeTipoUsuarioId", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.maeTipoUsuarioId = :maeTipoUsuarioId"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByMaeTipoUsuarioValor", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.maeTipoUsuarioValor = :maeTipoUsuarioValor"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByPrimerApellido", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.primerApellido = :primerApellido"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findBySegundoApellido", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByPrimerNombre", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.primerNombre = :primerNombre"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findBySegundoNombre", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByEdad", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.edad = :edad"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByCodigoUnidadMedidaEdad", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.codigoUnidadMedidaEdad = :codigoUnidadMedidaEdad"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByMaeSexoCodigo", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.maeSexoCodigo = :maeSexoCodigo"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByMaeSexoId", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.maeSexoId = :maeSexoId"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByMaeSexoValor", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.maeSexoValor = :maeSexoValor"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByDepartamentoCodigo", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.departamentoCodigo = :departamentoCodigo"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByDepartamentoId", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.departamentoId = :departamentoId"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByDepartamentoNombre", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.departamentoNombre = :departamentoNombre"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByCiudadCodigo", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.ciudadCodigo = :ciudadCodigo"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByCiudadId", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.ciudadId = :ciudadId"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByCiudadNombre", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.ciudadNombre = :ciudadNombre"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByMaeZonaResidenciaCodigo", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.maeZonaResidenciaCodigo = :maeZonaResidenciaCodigo"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByMaeZonaResidenciaId", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.maeZonaResidenciaId = :maeZonaResidenciaId"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByMaeZonaResidenciaValor", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.maeZonaResidenciaValor = :maeZonaResidenciaValor"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByArchivoNombreOriginal", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.archivoNombreOriginal = :archivoNombreOriginal"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByArchivoRuta", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.archivoRuta = :archivoRuta"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByArchivoNombre", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.archivoNombre = :archivoNombre"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByUsuarioCrea", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.usuarioCrea = :usuarioCrea"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByTerminalCrea", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.terminalCrea = :terminalCrea"),
    @NamedQuery(name = "CmRipsCargaUsUsuarios.findByFechaHoraCrea", query = "SELECT c FROM CmRipsCargaUsUsuarios c WHERE c.fechaHoraCrea = :fechaHoraCrea")})
public class CmRipsCargaUsUsuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fila")
    private int fila;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_documento_codigo")
    private String maeTipoDocumentoCodigo;
    @Column(name = "mae_tipo_documento_id")
    private Integer maeTipoDocumentoId;
    @Size(max = 128)
    @Column(name = "mae_tipo_documento_valor")
    private String maeTipoDocumentoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "documento_afiliado")
    private String documentoAfiliado;
    @Size(max = 8)
    @Column(name = "administradora")
    private String administradora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_tipo_usuario_codigo")
    private String maeTipoUsuarioCodigo;
    @Column(name = "mae_tipo_usuario_id")
    private Integer maeTipoUsuarioId;
    @Size(max = 128)
    @Column(name = "mae_tipo_usuario_valor")
    private String maeTipoUsuarioValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "primer_apellido")
    private String primerApellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "segundo_apellido")
    private String segundoApellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "primer_nombre")
    private String primerNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    @Column(name = "edad")
    private Integer edad;
    @Size(max = 45)
    @Column(name = "codigo_unidad_medida_edad")
    private String codigoUnidadMedidaEdad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_sexo_codigo")
    private String maeSexoCodigo;
    @Column(name = "mae_sexo_id")
    private Integer maeSexoId;
    @Size(max = 128)
    @Column(name = "mae_sexo_valor")
    private String maeSexoValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "departamento_codigo")
    private String departamentoCodigo;
    @Column(name = "departamento_id")
    private Integer departamentoId;
    @Size(max = 16)
    @Column(name = "departamento_nombre")
    private String departamentoNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "ciudad_codigo")
    private String ciudadCodigo;
    @Column(name = "ciudad_id")
    private Integer ciudadId;
    @Size(max = 16)
    @Column(name = "ciudad_nombre")
    private String ciudadNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "mae_zona_residencia_codigo")
    private String maeZonaResidenciaCodigo;
    @Column(name = "mae_zona_residencia_id")
    private Integer maeZonaResidenciaId;
    @Size(max = 128)
    @Column(name = "mae_zona_residencia_valor")
    private String maeZonaResidenciaValor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "archivo_nombre_original")
    private String archivoNombreOriginal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "archivo_ruta")
    private String archivoRuta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "archivo_nombre")
    private String archivoNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "usuario_crea")
    private String usuarioCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "terminal_crea")
    private String terminalCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @JoinColumn(name = "cm_rips_cargas_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CmRipsCargas cmRipsCargasId;

    public CmRipsCargaUsUsuarios() {
    }

    public CmRipsCargaUsUsuarios(Integer id) {
        this.id = id;
    }

    public CmRipsCargaUsUsuarios(Integer id, int fila, String maeTipoDocumentoCodigo, String documentoAfiliado, String maeTipoUsuarioCodigo, String primerApellido, String segundoApellido, String primerNombre, String segundoNombre, String maeSexoCodigo, String departamentoCodigo, String ciudadCodigo, String maeZonaResidenciaCodigo, String archivoNombreOriginal, String archivoRuta, String archivoNombre, String usuarioCrea, String terminalCrea, Date fechaHoraCrea) {
        this.id = id;
        this.fila = fila;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.documentoAfiliado = documentoAfiliado;
        this.maeTipoUsuarioCodigo = maeTipoUsuarioCodigo;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.maeSexoCodigo = maeSexoCodigo;
        this.departamentoCodigo = departamentoCodigo;
        this.ciudadCodigo = ciudadCodigo;
        this.maeZonaResidenciaCodigo = maeZonaResidenciaCodigo;
        this.archivoNombreOriginal = archivoNombreOriginal;
        this.archivoRuta = archivoRuta;
        this.archivoNombre = archivoNombre;
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

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public String getMaeTipoDocumentoCodigo() {
        return maeTipoDocumentoCodigo;
    }

    public void setMaeTipoDocumentoCodigo(String maeTipoDocumentoCodigo) {
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
    }

    public Integer getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    public void setMaeTipoDocumentoId(Integer maeTipoDocumentoId) {
        this.maeTipoDocumentoId = maeTipoDocumentoId;
    }

    public String getMaeTipoDocumentoValor() {
        return maeTipoDocumentoValor;
    }

    public void setMaeTipoDocumentoValor(String maeTipoDocumentoValor) {
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
    }

    public String getDocumentoAfiliado() {
        return documentoAfiliado;
    }

    public void setDocumentoAfiliado(String documentoAfiliado) {
        this.documentoAfiliado = documentoAfiliado;
    }

    public String getAdministradora() {
        return administradora;
    }

    public void setAdministradora(String administradora) {
        this.administradora = administradora;
    }

    public String getMaeTipoUsuarioCodigo() {
        return maeTipoUsuarioCodigo;
    }

    public void setMaeTipoUsuarioCodigo(String maeTipoUsuarioCodigo) {
        this.maeTipoUsuarioCodigo = maeTipoUsuarioCodigo;
    }

    public Integer getMaeTipoUsuarioId() {
        return maeTipoUsuarioId;
    }

    public void setMaeTipoUsuarioId(Integer maeTipoUsuarioId) {
        this.maeTipoUsuarioId = maeTipoUsuarioId;
    }

    public String getMaeTipoUsuarioValor() {
        return maeTipoUsuarioValor;
    }

    public void setMaeTipoUsuarioValor(String maeTipoUsuarioValor) {
        this.maeTipoUsuarioValor = maeTipoUsuarioValor;
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

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getCodigoUnidadMedidaEdad() {
        return codigoUnidadMedidaEdad;
    }

    public void setCodigoUnidadMedidaEdad(String codigoUnidadMedidaEdad) {
        this.codigoUnidadMedidaEdad = codigoUnidadMedidaEdad;
    }

    public String getMaeSexoCodigo() {
        return maeSexoCodigo;
    }

    public void setMaeSexoCodigo(String maeSexoCodigo) {
        this.maeSexoCodigo = maeSexoCodigo;
    }

    public Integer getMaeSexoId() {
        return maeSexoId;
    }

    public void setMaeSexoId(Integer maeSexoId) {
        this.maeSexoId = maeSexoId;
    }

    public String getMaeSexoValor() {
        return maeSexoValor;
    }

    public void setMaeSexoValor(String maeSexoValor) {
        this.maeSexoValor = maeSexoValor;
    }

    public String getDepartamentoCodigo() {
        return departamentoCodigo;
    }

    public void setDepartamentoCodigo(String departamentoCodigo) {
        this.departamentoCodigo = departamentoCodigo;
    }

    public Integer getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Integer departamentoId) {
        this.departamentoId = departamentoId;
    }

    public String getDepartamentoNombre() {
        return departamentoNombre;
    }

    public void setDepartamentoNombre(String departamentoNombre) {
        this.departamentoNombre = departamentoNombre;
    }

    public String getCiudadCodigo() {
        return ciudadCodigo;
    }

    public void setCiudadCodigo(String ciudadCodigo) {
        this.ciudadCodigo = ciudadCodigo;
    }

    public Integer getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(Integer ciudadId) {
        this.ciudadId = ciudadId;
    }

    public String getCiudadNombre() {
        return ciudadNombre;
    }

    public void setCiudadNombre(String ciudadNombre) {
        this.ciudadNombre = ciudadNombre;
    }

    public String getMaeZonaResidenciaCodigo() {
        return maeZonaResidenciaCodigo;
    }

    public void setMaeZonaResidenciaCodigo(String maeZonaResidenciaCodigo) {
        this.maeZonaResidenciaCodigo = maeZonaResidenciaCodigo;
    }

    public Integer getMaeZonaResidenciaId() {
        return maeZonaResidenciaId;
    }

    public void setMaeZonaResidenciaId(Integer maeZonaResidenciaId) {
        this.maeZonaResidenciaId = maeZonaResidenciaId;
    }

    public String getMaeZonaResidenciaValor() {
        return maeZonaResidenciaValor;
    }

    public void setMaeZonaResidenciaValor(String maeZonaResidenciaValor) {
        this.maeZonaResidenciaValor = maeZonaResidenciaValor;
    }

    public String getArchivoNombreOriginal() {
        return archivoNombreOriginal;
    }

    public void setArchivoNombreOriginal(String archivoNombreOriginal) {
        this.archivoNombreOriginal = archivoNombreOriginal;
    }

    public String getArchivoRuta() {
        return archivoRuta;
    }

    public void setArchivoRuta(String archivoRuta) {
        this.archivoRuta = archivoRuta;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
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

    public CmRipsCargas getCmRipsCargasId() {
        return cmRipsCargasId;
    }

    public void setCmRipsCargasId(CmRipsCargas cmRipsCargasId) {
        this.cmRipsCargasId = cmRipsCargasId;
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
        if (!(object instanceof CmRipsCargaUsUsuarios)) {
            return false;
        }
        CmRipsCargaUsUsuarios other = (CmRipsCargaUsUsuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.saviasaludeps.savia.ejb.entidades.CmRipsCargaUsUsuarios[ id=" + id + " ]";
    }
    
}
