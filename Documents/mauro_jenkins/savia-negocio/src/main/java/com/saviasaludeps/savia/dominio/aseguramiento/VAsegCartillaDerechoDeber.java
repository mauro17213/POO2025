/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author jyperez
 */

public class VAsegCartillaDerechoDeber extends Auditoria {

    private int id;
    private int consecutivoPregunta;
    private String tipoDocumento;
    private String numeroDocumento;
    private String primerApellido;
    private String segundoApellido;
    private String primerNombre;
    private String segundoNombre;
    private Date fechaNacimiento;
    private String municipioAfiliacion;
    private String direccionResidencia;
    private String telefonoFijo;
    private String telefonoMovil;
    private String pregunta;
    private String respuesta;
    private Date fechaHoraCrea;
    private String usuarioCrea;
    // 2020-08-24 jyperez INC 280 cambios Reportes
    private String contratoAfiliado;
    private String codigoDepartamentoAfiliacion;
    private String codigoMunicipioAfiliacion;

    public VAsegCartillaDerechoDeber() {
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
    
    @Override
    public String toString() {
        return id + "," +
        consecutivoPregunta + "," +
        tipoDocumento + "," +
        numeroDocumento + "," +
        primerApellido + "," +
        segundoApellido + "," +
        primerNombre + "," +
        segundoNombre + "," +
        fechaNacimiento + "," +
        municipioAfiliacion + "," +
        codigoDepartamentoAfiliacion + "," +
        codigoMunicipioAfiliacion + "," +
        direccionResidencia + "," +
        telefonoFijo + "," +
        telefonoMovil + "," +
        pregunta + "," +
        respuesta + "," +
        fechaHoraCrea + "," +
        usuarioCrea + "," +
        contratoAfiliado + "\n";
    }
    
    public static String getEncabezado() {
        return "id,consecutivo_pregunta,tipo_documento,numero_documento,primer_apellido,segundo_apellido,primer_nombre,segundo_nombre,fecha_nacimiento,municipio_afiliacion,"
                + "codigo_departamento_afiliacion,codigo_municipio_afiliacion,direccion_residencia,telefono_fijo,telefono_movil,pregunta,respuesta,fecha_hora_crea,usuario_crea,contrato_afiliado\n";
    }

    /**
     * @return the contratoAfiliado
     */
    public String getContratoAfiliado() {
        return contratoAfiliado;
    }

    /**
     * @param contratoAfiliado the contratoAfiliado to set
     */
    public void setContratoAfiliado(String contratoAfiliado) {
        this.contratoAfiliado = contratoAfiliado;
    }

    /**
     * @return the codigoDepartamentoAfiliacion
     */
    public String getCodigoDepartamentoAfiliacion() {
        return codigoDepartamentoAfiliacion;
    }

    /**
     * @param codigoDepartamentoAfiliacion the codigoDepartamentoAfiliacion to set
     */
    public void setCodigoDepartamentoAfiliacion(String codigoDepartamentoAfiliacion) {
        this.codigoDepartamentoAfiliacion = codigoDepartamentoAfiliacion;
    }

    /**
     * @return the codigoMunicipioAfiliacion
     */
    public String getCodigoMunicipioAfiliacion() {
        return codigoMunicipioAfiliacion;
    }

    /**
     * @param codigoMunicipioAfiliacion the codigoMunicipioAfiliacion to set
     */
    public void setCodigoMunicipioAfiliacion(String codigoMunicipioAfiliacion) {
        this.codigoMunicipioAfiliacion = codigoMunicipioAfiliacion;
    }
    
}
