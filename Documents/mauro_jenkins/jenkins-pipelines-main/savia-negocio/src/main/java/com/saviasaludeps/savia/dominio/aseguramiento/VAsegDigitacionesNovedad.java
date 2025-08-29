/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author jyperez
 */
public class VAsegDigitacionesNovedad extends Auditoria {

    private int idNovedad;
    private String contratoAfiliado;
    private String primerApellido;
    private String segundoApellido;
    private String primerNombre;
    private String segundoNombre;
    private Date fechaNacimiento;
    // 2020-08-24 jyperez INC 280 cambios Reportes
    private String codigoDepartamentoAfiliacion;
    private String codigoMunicipioAfiliacion;
    private String descripcionMunicipioAfiliacion;
    private int radicadoNovedad;
    private BigInteger codigoNovedad;
    private String descripcionNovedad;
    private Date fechaNovedad;
    private String valorNuevo;
    private String valorAnterior;
    private String usuarioDigita;
    private Date fechaDigitacion;
    
    // campos adicionales
    private boolean novedadActualizada;

    public VAsegDigitacionesNovedad() {
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

    public Date getFechaDigitacion() {
        return fechaDigitacion;
    }

    public void setFechaDigitacion(Date fechaDigitacion) {
        this.fechaDigitacion = fechaDigitacion;
    }

    /**
     * @return the novedadActualizada
     */
    public boolean isNovedadActualizada() {
        return novedadActualizada;
    }

    /**
     * @param novedadActualizada the novedadActualizada to set
     */
    public void setNovedadActualizada(boolean novedadActualizada) {
        this.novedadActualizada = novedadActualizada;
    }
    
    @Override
    public String toString() {
        return idNovedad + "," +
        contratoAfiliado + "," +
        primerApellido + "," +
        segundoApellido + "," +
        primerNombre + "," +
        segundoNombre + "," +
        fechaNacimiento + "," +
        codigoDepartamentoAfiliacion + "," +
        codigoMunicipioAfiliacion + "," +
        descripcionMunicipioAfiliacion + "," +
        radicadoNovedad + "," +
        codigoNovedad + "," +
        descripcionNovedad + "," +
        fechaNovedad + "," +
        valorNuevo + "," +
        valorAnterior + "," +
        usuarioDigita + "," +
        fechaDigitacion + "\n";
    }
    
    public static String getEncabezado() {
        return "id_novedad,contrato_afiliado,primer_apellido,segundo_apellido,primer_nombre,segundo_nombre,fecha_nacimiento,codigo_departamento_afiliacion,codigo_municipio_afiliacion,"
                + "descripcion_municipio_afiliacion,radicado_novedad,"
                            + "codigo_novedad,descripcion_novedad,fecha_novedad,valor_nuevo,valor_anterior,usuario_digita,fecha_digitacion\n";
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
