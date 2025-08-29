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
public class VAsegNovedadSubsidiado extends Auditoria {

    private int id;
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
    private String valor7;
    private String contratoAfiliado;
    private String estadoAfiliacion;
    private String causaNovedad;
    private Date fechaHoraCrea;
    private String usuarioCrea;
    // 2020-08-24 jyperez INC 280 cambios Reportes
    private Integer codigoCausaNovedad;
    // 2020-09-09 jyperez nuevos campos - campo id reporte
    private Date fechaHoraReporte;
    private Integer idReporte;
    //2022-02-24 jyperez nuevos campos
    private String metodologiaGrupoPoblacional;
    private String subgrupoSisbenIv;
    private String condicionBeneficiario;
    private String tipoDocumentoCabeza;
    private String numeroIdentificacionCabeza;
    private String parentescoCabeza;
    private String tipoAfiliado;
    
    // campos adicionales
    private boolean novedadActualizada;

    public VAsegNovedadSubsidiado() {
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

    public String getValor7() {
        return valor7;
    }

    public void setValor7(String valor7) {
        this.valor7 = valor7;
    }

    public String getContratoAfiliado() {
        return contratoAfiliado;
    }

    public void setContratoAfiliado(String contratoAfiliado) {
        this.contratoAfiliado = contratoAfiliado;
    }

    public String getEstadoAfiliacion() {
        return estadoAfiliacion;
    }

    public void setEstadoAfiliacion(String estadoAfiliacion) {
        this.estadoAfiliacion = estadoAfiliacion;
    }

    public String getCausaNovedad() {
        return causaNovedad;
    }

    public void setCausaNovedad(String causaNovedad) {
        this.causaNovedad = causaNovedad;
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
    
        /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the codigoCausaNovedad
     */
    public Integer getCodigoCausaNovedad() {
        return codigoCausaNovedad;
    }

    /**
     * @param codigoCausaNovedad the codigoCausaNovedad to set
     */
    public void setCodigoCausaNovedad(Integer codigoCausaNovedad) {
        this.codigoCausaNovedad = codigoCausaNovedad;
    }

    /**
     * @return the fechaHoraReporte
     */
    public Date getFechaHoraReporte() {
        return fechaHoraReporte;
    }

    /**
     * @param fechaHoraReporte the fechaHoraReporte to set
     */
    public void setFechaHoraReporte(Date fechaHoraReporte) {
        this.fechaHoraReporte = fechaHoraReporte;
    }

    /**
     * @return the idReporte
     */
    public Integer getIdReporte() {
        return idReporte;
    }

    /**
     * @param idReporte the idReporte to set
     */
    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }
    
    @Override
    public String toString() {
        return id + "," +
        codigoEps + "," +
        tipoDocumento + "," +
        numeroDocumento + "," +
        primerApellido + "," +
        transformarNuloEnVacio(segundoApellido) + "," +
        primerNombre + "," +
        transformarNuloEnVacio(segundoNombre) + "," +
        fechaNacimiento + "," +
        codigoDepartamento + "," +
        codigoMunicipio + "," +
        codigoNovedad + "," +
        fechaNovedad + "," +
        transformarNuloEnVacio(valor1) + "," +
        transformarNuloEnVacio(valor2) + "," +
        transformarNuloEnVacio(valor3) + "," +
        transformarNuloEnVacio(valor4) + "," +
        transformarNuloEnVacio(valor5) + "," +
        transformarNuloEnVacio(valor6) + "," +
        transformarNuloEnVacio(valor7) + "," +
        contratoAfiliado + "," +
        estadoAfiliacion + "," +
        transformarNuloEnVacio(causaNovedad) + "," +
        transformarNuloEnVacio(codigoCausaNovedad) + "," +
        transformarNuloEnVacio(metodologiaGrupoPoblacional) + "," +
        transformarNuloEnVacio(subgrupoSisbenIv) + "," +
        transformarNuloEnVacio(condicionBeneficiario) + "," +
        transformarNuloEnVacio(tipoDocumentoCabeza) + "," +
        transformarNuloEnVacio(numeroIdentificacionCabeza) + "," +
        cambiarComasPipeLine(parentescoCabeza) + "," +
        transformarNuloEnVacio(tipoAfiliado) + "," +
        fechaHoraCrea + "," +
        usuarioCrea + "\n";
    }
  
    public static String getEncabezado() {
        return "id,codigo_eps,tipo_documento,numero_documento,primer_apellido,segundo_apellido,primer_nombre,segundo_nombre,fecha_nacimiento,"
                            + "codigo_departamento,codigo_municipio,codigo_novedad,fecha_novedad,valor_1,valor_2,valor_3,valor_4,valor_5,valor_6,valor_7,contrato_afiliado,"
                            + "estado_afiliacion,causa_novedad,codigo_causa_novedad,metodologia_grupo_poblacional,subgrupo_sisben_iv,condicion_beneficiario,tipo_documento_cabeza,numero_identificacion_cabeza,parentesco_cabeza,tipo_afiliado,fecha_hora_crea,usuario_crea\n";
    }
    
    /**
     * Función para transformar un valor null de Texto en un valor de cadena vacia ""
     * @param texto
     * @return 
     */
    public String transformarNuloEnVacio(String texto) {
        if (texto == null) {
            return "";
        } else {
            return texto;
        }
    }
    
    /**
     * Función para transformar un valor Integer null de Texto en un valor de cadena vacia ""
     * @param texto
     * @return 
     */
    public String transformarNuloEnVacio(Integer numero) {
        if (numero == null) {
            return "";
        } else {
            return numero.toString();
        }
    }

    /**
     * @return the metodologiaGrupoPoblacional
     */
    public String getMetodologiaGrupoPoblacional() {
        return metodologiaGrupoPoblacional;
    }

    /**
     * @param metodologiaGrupoPoblacional the metodologiaGrupoPoblacional to set
     */
    public void setMetodologiaGrupoPoblacional(String metodologiaGrupoPoblacional) {
        this.metodologiaGrupoPoblacional = metodologiaGrupoPoblacional;
    }

    /**
     * @return the subgrupoSisbenIv
     */
    public String getSubgrupoSisbenIv() {
        return subgrupoSisbenIv;
    }

    /**
     * @param subgrupoSisbenIv the subgrupoSisbenIv to set
     */
    public void setSubgrupoSisbenIv(String subgrupoSisbenIv) {
        this.subgrupoSisbenIv = subgrupoSisbenIv;
    }

    /**
     * @return the condicionBeneficiario
     */
    public String getCondicionBeneficiario() {
        return condicionBeneficiario;
    }

    /**
     * @param condicionBeneficiario the condicionBeneficiario to set
     */
    public void setCondicionBeneficiario(String condicionBeneficiario) {
        this.condicionBeneficiario = condicionBeneficiario;
    }

    /**
     * @return the tipoDocumentoCabeza
     */
    public String getTipoDocumentoCabeza() {
        return tipoDocumentoCabeza;
    }

    /**
     * @param tipoDocumentoCabeza the tipoDocumentoCabeza to set
     */
    public void setTipoDocumentoCabeza(String tipoDocumentoCabeza) {
        this.tipoDocumentoCabeza = tipoDocumentoCabeza;
    }

    /**
     * @return the numeroIdentificacionCabeza
     */
    public String getNumeroIdentificacionCabeza() {
        return numeroIdentificacionCabeza;
    }

    /**
     * @param numeroIdentificacionCabeza the numeroIdentificacionCabeza to set
     */
    public void setNumeroIdentificacionCabeza(String numeroIdentificacionCabeza) {
        this.numeroIdentificacionCabeza = numeroIdentificacionCabeza;
    }

    /**
     * @return the parentescoCabeza
     */
    public String getParentescoCabeza() {
        return parentescoCabeza;
    }

    /**
     * @param parentescoCabeza the parentescoCabeza to set
     */
    public void setParentescoCabeza(String parentescoCabeza) {
        this.parentescoCabeza = parentescoCabeza;
    }

    /**
     * @return the tipoAfiliado
     */
    public String getTipoAfiliado() {
        return tipoAfiliado;
    }

    /**
     * @param tipoAfiliado the tipoAfiliado to set
     */
    public void setTipoAfiliado(String tipoAfiliado) {
        this.tipoAfiliado = tipoAfiliado;
    }
        
    /**
     * Función para transformar las comas en el texto por | , esto debido a que estas se usan para división de 
     * columnas en el formato.
     * @param texto
     * @return 
     */
    public String cambiarComasPipeLine(String texto) {
        if (texto == null) {
            return "";
        } else {            
            return texto.replace(',', '|');
        }
    }
}
