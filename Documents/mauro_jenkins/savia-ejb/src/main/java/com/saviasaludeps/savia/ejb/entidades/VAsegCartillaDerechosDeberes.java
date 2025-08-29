/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author jyperez
 */
@Entity
@Table(name = "v_aseg_cartilla_derechos_deberes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VAsegCartillaDerechosDeberes.findAll", query = "SELECT v FROM VAsegCartillaDerechosDeberes v"),
    @NamedQuery(name = "VAsegCartillaDerechosDeberes.findById", query = "SELECT v FROM VAsegCartillaDerechosDeberes v WHERE v.id = :id"),
    @NamedQuery(name = "VAsegCartillaDerechosDeberes.findByConsecutivoPregunta", query = "SELECT v FROM VAsegCartillaDerechosDeberes v WHERE v.consecutivoPregunta = :consecutivoPregunta"),
    @NamedQuery(name = "VAsegCartillaDerechosDeberes.findByContratoAfiliado", query = "SELECT v FROM VAsegCartillaDerechosDeberes v WHERE v.contratoAfiliado = :contratoAfiliado"),
    @NamedQuery(name = "VAsegCartillaDerechosDeberes.findByTipoDocumento", query = "SELECT v FROM VAsegCartillaDerechosDeberes v WHERE v.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "VAsegCartillaDerechosDeberes.findByNumeroDocumento", query = "SELECT v FROM VAsegCartillaDerechosDeberes v WHERE v.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "VAsegCartillaDerechosDeberes.findByPrimerApellido", query = "SELECT v FROM VAsegCartillaDerechosDeberes v WHERE v.primerApellido = :primerApellido"),
    @NamedQuery(name = "VAsegCartillaDerechosDeberes.findBySegundoApellido", query = "SELECT v FROM VAsegCartillaDerechosDeberes v WHERE v.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "VAsegCartillaDerechosDeberes.findByPrimerNombre", query = "SELECT v FROM VAsegCartillaDerechosDeberes v WHERE v.primerNombre = :primerNombre"),
    @NamedQuery(name = "VAsegCartillaDerechosDeberes.findBySegundoNombre", query = "SELECT v FROM VAsegCartillaDerechosDeberes v WHERE v.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "VAsegCartillaDerechosDeberes.findByFechaNacimiento", query = "SELECT v FROM VAsegCartillaDerechosDeberes v WHERE v.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "VAsegCartillaDerechosDeberes.findByMunicipioAfiliacion", query = "SELECT v FROM VAsegCartillaDerechosDeberes v WHERE v.municipioAfiliacion = :municipioAfiliacion"),
    @NamedQuery(name = "VAsegCartillaDerechosDeberes.findByCodigoDepartamentoAfiliacion", query = "SELECT v FROM VAsegCartillaDerechosDeberes v WHERE v.codigoDepartamentoAfiliacion = :codigoDepartamentoAfiliacion"),
    @NamedQuery(name = "VAsegCartillaDerechosDeberes.findByCodigoMunicipioAfiliacion", query = "SELECT v FROM VAsegCartillaDerechosDeberes v WHERE v.codigoMunicipioAfiliacion = :codigoMunicipioAfiliacion"),
    @NamedQuery(name = "VAsegCartillaDerechosDeberes.findByDireccionResidencia", query = "SELECT v FROM VAsegCartillaDerechosDeberes v WHERE v.direccionResidencia = :direccionResidencia"),
    @NamedQuery(name = "VAsegCartillaDerechosDeberes.findByTelefonoFijo", query = "SELECT v FROM VAsegCartillaDerechosDeberes v WHERE v.telefonoFijo = :telefonoFijo"),
    @NamedQuery(name = "VAsegCartillaDerechosDeberes.findByTelefonoMovil", query = "SELECT v FROM VAsegCartillaDerechosDeberes v WHERE v.telefonoMovil = :telefonoMovil"),
    @NamedQuery(name = "VAsegCartillaDerechosDeberes.findByPregunta", query = "SELECT v FROM VAsegCartillaDerechosDeberes v WHERE v.pregunta = :pregunta"),
    @NamedQuery(name = "VAsegCartillaDerechosDeberes.findByRespuesta", query = "SELECT v FROM VAsegCartillaDerechosDeberes v WHERE v.respuesta = :respuesta"),
    @NamedQuery(name = "VAsegCartillaDerechosDeberes.findByFechaHoraCrea", query = "SELECT v FROM VAsegCartillaDerechosDeberes v WHERE v.fechaHoraCrea = :fechaHoraCrea"),
    @NamedQuery(name = "VAsegCartillaDerechosDeberes.findByUsuarioCrea", query = "SELECT v FROM VAsegCartillaDerechosDeberes v WHERE v.usuarioCrea = :usuarioCrea")})
public class VAsegCartillaDerechosDeberes implements Serializable {

    @Size(max = 7)
    @Column(name = "divipola_municipio_afiliacion")
    private String divipolaMunicipioAfiliacion;

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    @Id
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "consecutivo_pregunta")
    private int consecutivoPregunta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "contrato_afiliado")
    private String contratoAfiliado;
    @Size(max = 16)
    @Column(name = "tipo_documento")
    private String tipoDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "numero_documento")
    private String numeroDocumento;
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
    @Size(max = 512)
    @Column(name = "municipio_afiliacion")
    private String municipioAfiliacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "codigo_departamento_afiliacion")
    private String codigoDepartamentoAfiliacion;
    @Size(max = 5)
    @Column(name = "codigo_municipio_afiliacion")
    private String codigoMunicipioAfiliacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "direccion_residencia")
    private String direccionResidencia;
    @Size(max = 32)
    @Column(name = "telefono_fijo")
    private String telefonoFijo;
    @Size(max = 32)
    @Column(name = "telefono_movil")
    private String telefonoMovil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1024)
    @Column(name = "pregunta")
    private String pregunta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "respuesta")
    private String respuesta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_hora_crea")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraCrea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "usuario_crea")
    private String usuarioCrea;

    public VAsegCartillaDerechosDeberes() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConsecutivoPregunta() {
        return consecutivoPregunta;
    }

    public void setConsecutivoPregunta(int consecutivoPregunta) {
        this.consecutivoPregunta = consecutivoPregunta;
    }

    public String getContratoAfiliado() {
        return contratoAfiliado;
    }

    public void setContratoAfiliado(String contratoAfiliado) {
        this.contratoAfiliado = contratoAfiliado;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
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

    public String getMunicipioAfiliacion() {
        return municipioAfiliacion;
    }

    public void setMunicipioAfiliacion(String municipioAfiliacion) {
        this.municipioAfiliacion = municipioAfiliacion;
    }

    public String getCodigoDepartamentoAfiliacion() {
        return codigoDepartamentoAfiliacion;
    }

    public void setCodigoDepartamentoAfiliacion(String codigoDepartamentoAfiliacion) {
        this.codigoDepartamentoAfiliacion = codigoDepartamentoAfiliacion;
    }

    public String getCodigoMunicipioAfiliacion() {
        return codigoMunicipioAfiliacion;
    }

    public void setCodigoMunicipioAfiliacion(String codigoMunicipioAfiliacion) {
        this.codigoMunicipioAfiliacion = codigoMunicipioAfiliacion;
    }

    public String getDireccionResidencia() {
        return direccionResidencia;
    }

    public void setDireccionResidencia(String direccionResidencia) {
        this.direccionResidencia = direccionResidencia;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getDivipolaMunicipioAfiliacion() {
        return divipolaMunicipioAfiliacion;
    }

    public void setDivipolaMunicipioAfiliacion(String divipolaMunicipioAfiliacion) {
        this.divipolaMunicipioAfiliacion = divipolaMunicipioAfiliacion;
    }
    
}
