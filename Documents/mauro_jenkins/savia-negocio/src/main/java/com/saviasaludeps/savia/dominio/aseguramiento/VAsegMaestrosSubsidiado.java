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

public class VAsegMaestrosSubsidiado extends Auditoria {

    private String codigoEps;
    private String tipoDocumento;
    private String numeroDocumento;
    private String primerApellido;
    private String segundoApellido;
    private String primerNombre;
    private String segundoNombre;
    private String fechaNacimiento;
    private String sexo;
    private String codigoDepartamento;
    private String codigoMunicipio;
    private String zona;
    private String fechaAfiliacionEps;
    private String grupoPoblacional;
    private String nivelSisben;
    private String codigoHabilitacionSede;
    private String contratoAfiliado;
    private String estadoAfiliacion;
    private String tipoDocumentoBdua;
    private String numeroDocumentoBdua;
    private String origenAfiliado;
    private String fechaHoraCrea;
    // 2020-08-24 jyperez INC 280 cambios Reportes
    private String usuarioCrea;
    //2020-09-15 jyperez cambios adicionales reporte
    private String idReporte;
    private String fechaMarcacion;
    //2022-02-24 jyperes nuevos campos
    private String metodologiaGrupoPoblacional;
    private String subgrupoSisbenIv;
    private String condicionBeneficiario;
    private String tipoDocumentoCabeza;
    private String numeroIdentificacionCabeza;
    private String parentescoCabeza;
    private String tipoAfiliado;
    //campos adicionales
    private boolean novedadActualizada;

    public VAsegMaestrosSubsidiado() {
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getFechaAfiliacionEps() {
        return fechaAfiliacionEps;
    }

    public void setFechaAfiliacionEps(String fechaAfiliacionEps) {
        this.fechaAfiliacionEps = fechaAfiliacionEps;
    }

    public String getGrupoPoblacional() {
        return grupoPoblacional;
    }

    public void setGrupoPoblacional(String grupoPoblacional) {
        this.grupoPoblacional = grupoPoblacional;
    }

    public String getNivelSisben() {
        return nivelSisben;
    }

    public void setNivelSisben(String nivelSisben) {
        this.nivelSisben = nivelSisben;
    }

    public String getCodigoHabilitacionSede() {
        return codigoHabilitacionSede;
    }

    public void setCodigoHabilitacionSede(String codigoHabilitacionSede) {
        this.codigoHabilitacionSede = codigoHabilitacionSede;
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

    public String getTipoDocumentoBdua() {
        return tipoDocumentoBdua;
    }

    public void setTipoDocumentoBdua(String tipoDocumentoBdua) {
        this.tipoDocumentoBdua = tipoDocumentoBdua;
    }

    public String getNumeroDocumentoBdua() {
        return numeroDocumentoBdua;
    }

    public void setNumeroDocumentoBdua(String numeroDocumentoBdua) {
        this.numeroDocumentoBdua = numeroDocumentoBdua;
    }

    public String getOrigenAfiliado() {
        return origenAfiliado;
    }

    public void setOrigenAfiliado(String origenAfiliado) {
        this.origenAfiliado = origenAfiliado;
    }

    public String getFechaHoraCreaString() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(String fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
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
        return codigoEps + "," +
                tipoDocumento + "," +
                numeroDocumento+ "," +
                primerApellido+ "," +
                transformarNuloEnVacio(segundoApellido)+ "," +
                primerNombre+ "," +
                transformarNuloEnVacio(segundoNombre)+ "," +
                fechaNacimiento+ "," +
                sexo+ "," +
                codigoDepartamento+ "," +
                codigoMunicipio+ "," +
                zona+ "," +
                fechaAfiliacionEps+ "," +
                grupoPoblacional+ "," +
                nivelSisben+ "," +
                codigoHabilitacionSede+ "," +
                contratoAfiliado+ "," +
                estadoAfiliacion+ "," +
                transformarNuloEnVacio(tipoDocumentoBdua)+ "," +
                transformarNuloEnVacio(numeroDocumentoBdua)+ "," +
                origenAfiliado + "," +
                transformarNuloEnVacio(metodologiaGrupoPoblacional) + "," +
                transformarNuloEnVacio(subgrupoSisbenIv) + "," +
                transformarNuloEnVacio(condicionBeneficiario) + "," +
                transformarNuloEnVacio(tipoDocumentoCabeza) + "," +
                transformarNuloEnVacio(numeroIdentificacionCabeza) + "," +
                cambiarComasPipeLine(parentescoCabeza) + "," +
                transformarNuloEnVacio(tipoAfiliado) + "," +
                fechaHoraCrea+ "," +
                usuarioCrea+ "\n";
    }
    
    public static String getEncabezado() {
        return "codigo_eps,tipo_documento,numero_documento,primer_apellido,segundo_apellido,primer_nombre,segundo_nombre,fecha_nacimiento,sexo,codigo_departamento,codigo_municipio,"
                            + "zona,fecha_afiliacion_eps,grupo_poblacional,nivel_sisben,codigo_habilitacion_sede,contrato_afiliado,estado_afiliacion,tipo_documento_bdua,numero_documento_bdua,"
                            + "origen_afiliado,metodologia_grupo_poblacional,subgrupo_sisben_iv,condicion_beneficiario,tipo_documento_cabeza,numero_identificacion_cabeza,parentesco_cabeza,tipo_afiliado,fecha_hora_crea,usuario_crea\n";
    }

    /**
     * @return the usuarioCrea
     */
    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    /**
     * @param usuarioCrea the usuarioCrea to set
     */
    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    /**
     * @return the idReporte
     */
    public String getIdReporte() {
        return idReporte;
    }

    /**
     * @param idReporte the idReporte to set
     */
    public void setIdReporte(String idReporte) {
        this.idReporte = idReporte;
    }

    /**
     * @return the fechaMarcacion
     */
    public String getFechaMarcacion() {
        return fechaMarcacion;
    }

    /**
     * @param fechaMarcacion the fechaMarcacion to set
     */
    public void setFechaMarcacion(String fechaMarcacion) {
        this.fechaMarcacion = fechaMarcacion;
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
