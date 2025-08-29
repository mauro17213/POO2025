/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jyperez
 */
public class AsegReporteNovedad extends Auditoria {

    private Integer id;
    private String codigoEps;
    private String tipoDocumento;
    private String numeroDocumento;
    private String primerApellido;
    private String segundoApellido;
    private String primerNombre;
    private String segundoNombre;
    private Date fechaNacimiento;
    private String codigoDepartamento;
    private String codigoMunicipio;
    private String codigoNovedad;
    private Date fechaNovedad;
    private String valor1;
    private String valor2;
    private String valor3;
    private String valor4;
    private String valor5;
    private String valor6;
    private Date fechaReporte;
    private AsegAfiliado asegAfiliado;
    private List<AsegRegistroNovedad> listaAsegRegistroNovedades;

    public AsegReporteNovedad() {
    }

    public AsegReporteNovedad(Integer id) {
        this.id = id;
    }

    public AsegReporteNovedad(Integer id, String codigoEps, String tipoDocumento, String numeroDocumento, String primerApellido, String primerNombre, Date fechaNacimiento, String codigoDepartamento, String codigoMunicipio, String codigoNovedad, Date fechaNovedad) {
        this.id = id;
        this.codigoEps = codigoEps;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.primerApellido = primerApellido;
        this.primerNombre = primerNombre;
        this.fechaNacimiento = fechaNacimiento;
        this.codigoDepartamento = codigoDepartamento;
        this.codigoMunicipio = codigoMunicipio;
        this.codigoNovedad = codigoNovedad;
        this.fechaNovedad = fechaNovedad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoEps() {
        return codigoEps;
    }

    public void setCodigoEps(String codigoEps) {
        this.codigoEps = codigoEps;
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

    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getCodigoNovedad() {
        return codigoNovedad;
    }

    public void setCodigoNovedad(String codigoNovedad) {
        this.codigoNovedad = codigoNovedad;
    }

    public Date getFechaNovedad() {
        return fechaNovedad;
    }

    public void setFechaNovedad(Date fechaNovedad) {
        this.fechaNovedad = fechaNovedad;
    }

    public String getValor1() {
        return valor1;
    }

    public void setValor1(String valor1) {
        this.valor1 = valor1;
    }

    public String getValor2() {
        return valor2;
    }

    public void setValor2(String valor2) {
        this.valor2 = valor2;
    }

    public String getValor3() {
        return valor3;
    }

    public void setValor3(String valor3) {
        this.valor3 = valor3;
    }

    public String getValor4() {
        return valor4;
    }

    public void setValor4(String valor4) {
        this.valor4 = valor4;
    }

    public String getValor5() {
        return valor5;
    }

    public void setValor5(String valor5) {
        this.valor5 = valor5;
    }

    public String getValor6() {
        return valor6;
    }

    public void setValor6(String valor6) {
        this.valor6 = valor6;
    }

    public Date getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public AsegAfiliado getAsegAfiliado() {
        return asegAfiliado;
    }

    public void setAsegAfiliado(AsegAfiliado asegAfiliado) {
        this.asegAfiliado = asegAfiliado;
    }

    public List<AsegRegistroNovedad> getListaAsegRegistroNovedades() {
        return listaAsegRegistroNovedades;
    }

    public void setListaAsegRegistroNovedades(List<AsegRegistroNovedad> listaAsegRegistroNovedades) {
        this.listaAsegRegistroNovedades = listaAsegRegistroNovedades;
    }

    @Override
    public String toString() {
        return "AsegReporteNovedad{" + "id=" + id + ", codigoEps=" + codigoEps + ", tipoDocumento=" + tipoDocumento + ", numeroDocumento=" + numeroDocumento + ", primerApellido=" + primerApellido + ", segundoApellido=" + segundoApellido + ", primerNombre=" + primerNombre + ", segundoNombre=" + segundoNombre + ", fechaNacimiento=" + fechaNacimiento + ", codigoDepartamento=" + codigoDepartamento + ", codigoMunicipio=" + codigoMunicipio + ", codigoNovedad=" + codigoNovedad + ", fechaNovedad=" + fechaNovedad + ", valor1=" + valor1 + ", valor2=" + valor2 + ", valor3=" + valor3 + ", valor4=" + valor4 + ", valor5=" + valor5 + ", valor6=" + valor6 + ", fechaReporte=" + fechaReporte + ", asegAfiliado=" + asegAfiliado + '}';
    }
    
}
