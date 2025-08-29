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
public class VAsegTraslado extends Auditoria {

    private String id;
    private String codigoEntidad;
    private String tipoDocumentoBdua;
    private String numeroDocumentoBdua;
    private String primerApellidoBdua;
    private String segundoApellidoBdua;
    private String primerNombreBdua;
    private String segundoNombreBdua;
    private String fechaNacimientoBdua;
    private String generoBdua;
    private String tipoDocumento;
    private String numeroDocumento;
    private String primerApellido;
    private String segundoApellido;
    private String primerNombre;
    private String segundoNombre;
    private String fechaNacimiento;
    private String genero;
    // 2020-08-24 jyperez INC 280 cambios Reportes
    private String codigoDepartamentoAfiliacion;
    private String codigoMunicipioAfiliacion;
    private String zona;
    private String fechaAfiliacionEps;
    private String codigoGrupoPoblacional;
    private String nivelSisben;
    private String tipoTraslado;
    private String estadoAfiliado;
    private String fechaHoraCrea;
    // 2020-08-24 jyperez INC 280 cambios Reportes
    private String usuarioCrea;
    private String contratoAfiliado;
    private String origenAfiliado;
    private String epsAnterior;
    private String codigoEpsAnterior;
    //2020-09-15 jyperez cambios adicionales reporte
    private String idReporte;
    private String fechaMarcacion;
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

    public VAsegTraslado() {
    }

    public String getCodigoEntidad() {
        return codigoEntidad;
    }

    public void setCodigoEntidad(String codigoEntidad) {
        this.codigoEntidad = codigoEntidad;
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

    public String getPrimerApellidoBdua() {
        return primerApellidoBdua;
    }

    public void setPrimerApellidoBdua(String primerApellidoBdua) {
        this.primerApellidoBdua = primerApellidoBdua;
    }

    public String getSegundoApellidoBdua() {
        return segundoApellidoBdua;
    }

    public void setSegundoApellidoBdua(String segundoApellidoBdua) {
        this.segundoApellidoBdua = segundoApellidoBdua;
    }

    public String getPrimerNombreBdua() {
        return primerNombreBdua;
    }

    public void setPrimerNombreBdua(String primerNombreBdua) {
        this.primerNombreBdua = primerNombreBdua;
    }

    public String getSegundoNombreBdua() {
        return segundoNombreBdua;
    }

    public void setSegundoNombreBdua(String segundoNombreBdua) {
        this.segundoNombreBdua = segundoNombreBdua;
    }

    public String getFechaNacimientoBdua() {
        return fechaNacimientoBdua;
    }

    public void setFechaNacimientoBdua(String fechaNacimientoBdua) {
        this.fechaNacimientoBdua = fechaNacimientoBdua;
    }

    public String getGeneroBdua() {
        return generoBdua;
    }

    public void setGeneroBdua(String generoBdua) {
        this.generoBdua = generoBdua;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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

    public String getCodigoGrupoPoblacional() {
        return codigoGrupoPoblacional;
    }

    public void setCodigoGrupoPoblacional(String codigoGrupoPoblacional) {
        this.codigoGrupoPoblacional = codigoGrupoPoblacional;
    }

    public String getNivelSisben() {
        return nivelSisben;
    }

    public void setNivelSisben(String nivelSisben) {
        this.nivelSisben = nivelSisben;
    }

    public String getTipoTraslado() {
        return tipoTraslado;
    }

    public void setTipoTraslado(String tipoTraslado) {
        this.tipoTraslado = tipoTraslado;
    }

    public String getEstadoAfiliado() {
        return estadoAfiliado;
    }

    public void setEstadoAfiliado(String estadoAfiliado) {
        this.estadoAfiliado = estadoAfiliado;
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
        return id + "," +
            codigoEntidad + "," +
            tipoDocumentoBdua + "," +
            numeroDocumentoBdua + "," +
            primerApellidoBdua + "," +
            segundoApellidoBdua + "," +
            primerNombreBdua + "," +
            segundoNombreBdua + "," +
            fechaNacimientoBdua + "," +
            generoBdua + "," +
            tipoDocumento + "," +
            numeroDocumento + "," +
            primerApellido + "," +
            segundoApellido + "," +
            primerNombre + "," +
            segundoNombre + "," +
            fechaNacimiento + "," +
            genero + "," +
            codigoDepartamentoAfiliacion + "," +
            codigoMunicipioAfiliacion + "," +
            zona + "," +
            fechaAfiliacionEps + "," +
            codigoGrupoPoblacional + "," +
            nivelSisben + "," +
            tipoTraslado + "," +
            estadoAfiliado + "," +
            fechaHoraCrea + "," +
            usuarioCrea + "," +
            contratoAfiliado + "," +
            origenAfiliado + "," +
            epsAnterior + "," +
            codigoEpsAnterior + "," +
            transformarNuloEnVacio(metodologiaGrupoPoblacional) + "," +
            transformarNuloEnVacio(subgrupoSisbenIv) + "," +
            transformarNuloEnVacio(condicionBeneficiario) + "," +
            transformarNuloEnVacio(tipoDocumentoCabeza) + "," +
            transformarNuloEnVacio(numeroIdentificacionCabeza) + "," +
            cambiarComasPipeLine(parentescoCabeza) + "," +
            transformarNuloEnVacio(tipoAfiliado) + "\n";
        
    }
    
    public static String getEncabezado() {
        return "id,codigo_entidad,tipo_documento_bdua,numero_documento_bdua,primer_apellido_bdua,segundo_apellido_bdua,primer_nombre_bdua,segundo_nombre_bdua,fecha_nacimiento_bdua,genero_bdua,tipo_documento,numero_documento,primer_apellido,segundo_apellido,"
                            + "primer_nombre,segundo_nombre,fecha_nacimiento,genero,codigo_departamento_afiliacion,codigo_municipio_afiliacion,zona,fecha_afiliacion_eps,codigo_grupo_poblacional,nivel_sisben,"
                            + "tipo_traslado,estado_afiliado,fecha_hora_crea,usuario_crea,contrato_afiliado,origen_afiliado,eps_anterior,codigo_eps_anterior,metodologia_grupo_poblacional,subgrupo_sisben_iv,condicion_beneficiario,tipo_documento_cabeza,numero_identificacion_cabeza,parentesco_cabeza,tipo_afiliado\n";
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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
     * @return the origenAfiliado
     */
    public String getOrigenAfiliado() {
        return origenAfiliado;
    }

    /**
     * @param origenAfiliado the origenAfiliado to set
     */
    public void setOrigenAfiliado(String origenAfiliado) {
        this.origenAfiliado = origenAfiliado;
    }

    /**
     * @return the epsAnterior
     */
    public String getEpsAnterior() {
        return epsAnterior;
    }

    /**
     * @param epsAnterior the epsAnterior to set
     */
    public void setEpsAnterior(String epsAnterior) {
        this.epsAnterior = epsAnterior;
    }

    /**
     * @return the codigoEpsAnterior
     */
    public String getCodigoEpsAnterior() {
        return codigoEpsAnterior;
    }

    /**
     * @param codigoEpsAnterior the codigoEpsAnterior to set
     */
    public void setCodigoEpsAnterior(String codigoEpsAnterior) {
        this.codigoEpsAnterior = codigoEpsAnterior;
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
