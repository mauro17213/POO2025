/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.ejb.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "v_aseg_digitacion_novedad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VAsegDigitacionNovedad.findAll", query = "SELECT v FROM VAsegDigitacionNovedad v"),
    @NamedQuery(name = "VAsegDigitacionNovedad.findByIdNovedad", query = "SELECT v FROM VAsegDigitacionNovedad v WHERE v.idNovedad = :idNovedad"),
    @NamedQuery(name = "VAsegDigitacionNovedad.findByContratoAfiliado", query = "SELECT v FROM VAsegDigitacionNovedad v WHERE v.contratoAfiliado = :contratoAfiliado"),
    @NamedQuery(name = "VAsegDigitacionNovedad.findByPrimerApellido", query = "SELECT v FROM VAsegDigitacionNovedad v WHERE v.primerApellido = :primerApellido"),
    @NamedQuery(name = "VAsegDigitacionNovedad.findBySegundoApellido", query = "SELECT v FROM VAsegDigitacionNovedad v WHERE v.segundoApellido = :segundoApellido"),
    @NamedQuery(name = "VAsegDigitacionNovedad.findByPrimerNombre", query = "SELECT v FROM VAsegDigitacionNovedad v WHERE v.primerNombre = :primerNombre"),
    @NamedQuery(name = "VAsegDigitacionNovedad.findBySegundoNombre", query = "SELECT v FROM VAsegDigitacionNovedad v WHERE v.segundoNombre = :segundoNombre"),
    @NamedQuery(name = "VAsegDigitacionNovedad.findByFechaNacimiento", query = "SELECT v FROM VAsegDigitacionNovedad v WHERE v.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "VAsegDigitacionNovedad.findByCodigoDepartamentoAfiliacion", query = "SELECT v FROM VAsegDigitacionNovedad v WHERE v.codigoDepartamentoAfiliacion = :codigoDepartamentoAfiliacion"),
    @NamedQuery(name = "VAsegDigitacionNovedad.findByCodigoMunicipioAfiliacion", query = "SELECT v FROM VAsegDigitacionNovedad v WHERE v.codigoMunicipioAfiliacion = :codigoMunicipioAfiliacion"),
    @NamedQuery(name = "VAsegDigitacionNovedad.findByDescripcionMunicipioAfiliacion", query = "SELECT v FROM VAsegDigitacionNovedad v WHERE v.descripcionMunicipioAfiliacion = :descripcionMunicipioAfiliacion"),
    @NamedQuery(name = "VAsegDigitacionNovedad.findByRadicadoNovedad", query = "SELECT v FROM VAsegDigitacionNovedad v WHERE v.radicadoNovedad = :radicadoNovedad"),
    @NamedQuery(name = "VAsegDigitacionNovedad.findByCodigoNovedad", query = "SELECT v FROM VAsegDigitacionNovedad v WHERE v.codigoNovedad = :codigoNovedad"),
    @NamedQuery(name = "VAsegDigitacionNovedad.findByDescripcionNovedad", query = "SELECT v FROM VAsegDigitacionNovedad v WHERE v.descripcionNovedad = :descripcionNovedad"),
    @NamedQuery(name = "VAsegDigitacionNovedad.findByFechaNovedad", query = "SELECT v FROM VAsegDigitacionNovedad v WHERE v.fechaNovedad = :fechaNovedad"),
    @NamedQuery(name = "VAsegDigitacionNovedad.findByUsuarioDigita", query = "SELECT v FROM VAsegDigitacionNovedad v WHERE v.usuarioDigita = :usuarioDigita"),
    @NamedQuery(name = "VAsegDigitacionNovedad.findByFechaDigitracion", query = "SELECT v FROM VAsegDigitacionNovedad v WHERE v.fechaDigitracion = :fechaDigitracion")})
public class VAsegDigitacionNovedad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_novedad")
    @Id
    private int idNovedad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "contrato_afiliado")
    private String contratoAfiliado;
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
    @Size(min = 1, max = 2)
    @Column(name = "codigo_departamento_afiliacion")
    private String codigoDepartamentoAfiliacion;
    @Size(max = 5)
    @Column(name = "codigo_municipio_afiliacion")
    private String codigoMunicipioAfiliacion;
    @Size(max = 16)
    @Column(name = "descripcion_municipio_afiliacion")
    private String descripcionMunicipioAfiliacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "radicado_novedad")
    private int radicadoNovedad;
    @Column(name = "codigo_novedad")
    private BigInteger codigoNovedad;
    @Size(max = 128)
    @Column(name = "descripcion_novedad")
    private String descripcionNovedad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_novedad")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNovedad;
    @Lob
    @Size(max = 65535)
    @Column(name = "valor_nuevo")
    private String valorNuevo;
    @Lob
    @Size(max = 65535)
    @Column(name = "valor_anterior")
    private String valorAnterior;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "usuario_digita")
    private String usuarioDigita;
    @Column(name = "fecha_digitracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDigitracion;

    public VAsegDigitacionNovedad() {
    }

    public int getIdNovedad() {
        return idNovedad;
    }

    public void setIdNovedad(int idNovedad) {
        this.idNovedad = idNovedad;
    }

    public String getContratoAfiliado() {
        return contratoAfiliado;
    }

    public void setContratoAfiliado(String contratoAfiliado) {
        this.contratoAfiliado = contratoAfiliado;
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

    public String getDescripcionMunicipioAfiliacion() {
        return descripcionMunicipioAfiliacion;
    }

    public void setDescripcionMunicipioAfiliacion(String descripcionMunicipioAfiliacion) {
        this.descripcionMunicipioAfiliacion = descripcionMunicipioAfiliacion;
    }

    public int getRadicadoNovedad() {
        return radicadoNovedad;
    }

    public void setRadicadoNovedad(int radicadoNovedad) {
        this.radicadoNovedad = radicadoNovedad;
    }

    public BigInteger getCodigoNovedad() {
        return codigoNovedad;
    }

    public void setCodigoNovedad(BigInteger codigoNovedad) {
        this.codigoNovedad = codigoNovedad;
    }

    public String getDescripcionNovedad() {
        return descripcionNovedad;
    }

    public void setDescripcionNovedad(String descripcionNovedad) {
        this.descripcionNovedad = descripcionNovedad;
    }

    public Date getFechaNovedad() {
        return fechaNovedad;
    }

    public void setFechaNovedad(Date fechaNovedad) {
        this.fechaNovedad = fechaNovedad;
    }

    public String getValorNuevo() {
        return valorNuevo;
    }

    public void setValorNuevo(String valorNuevo) {
        this.valorNuevo = valorNuevo;
    }

    public String getValorAnterior() {
        return valorAnterior;
    }

    public void setValorAnterior(String valorAnterior) {
        this.valorAnterior = valorAnterior;
    }

    public String getUsuarioDigita() {
        return usuarioDigita;
    }

    public void setUsuarioDigita(String usuarioDigita) {
        this.usuarioDigita = usuarioDigita;
    }

    public Date getFechaDigitracion() {
        return fechaDigitracion;
    }

    public void setFechaDigitracion(Date fechaDigitracion) {
        this.fechaDigitracion = fechaDigitracion;
    }
    
}
