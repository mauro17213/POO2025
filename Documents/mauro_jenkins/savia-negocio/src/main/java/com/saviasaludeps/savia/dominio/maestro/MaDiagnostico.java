/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.maestro;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

/**
 *
 * @author Jaime Andres Olarte
 */
public class MaDiagnostico extends Auditoria {
    
    private Integer id;
    private int maeDiagnosticoCapituloId;
    private String maeDiagnosticoCapituloCodigo;
    private String maeDiagnosticoCapituloValor;
    private int maeDiagnosticoCategoriaId;
    private String maeDiagnosticoCategoriaCodigo;
    private String maeDiagnosticoCategoriaValor;
    private String codigo;
    private String nombre;
    private boolean activo;
    private int sexoAplica;
    private String edadInferior;
    private String edadSuperior;
    private String grupoMortalidad;
    private String grupoMortalidadLista;
    private Integer valorLimiteInferior;
    private Integer valorLimiteSuperior;
    private Boolean excentoCobro;
    private boolean altoCosto;
    private Boolean priorizarCrue;
        //2025-05-06 jyperez nuevos campos
    private Integer maCacId;
    private String maCacCodigo;
    private String maCacValor;
    private List<MaDiagnosticoMedicamento> ListaMaDiagnosticoMedicamento;
    //2022-05-19 jyperez campos para la carga masiva
    private String errorCarga;
    private boolean actualizar;
    private String registroArchivo;
    private boolean estadoInicialActivo;

    //Identificador para el bean AuSolicitudBean.java
    private boolean principal;
    private Maestro tipoDiagnostico;

    public MaDiagnostico() {
    }

    public MaDiagnostico(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeDiagnosticoCapituloId() {
        return maeDiagnosticoCapituloId;
    }

    public void setMaeDiagnosticoCapituloId(int maeDiagnosticoCapituloId) {
        this.maeDiagnosticoCapituloId = maeDiagnosticoCapituloId;
    }

    public String getMaeDiagnosticoCapituloCodigo() {
        return maeDiagnosticoCapituloCodigo;
    }

    public void setMaeDiagnosticoCapituloCodigo(String maeDiagnosticoCapituloCodigo) {
        this.maeDiagnosticoCapituloCodigo = maeDiagnosticoCapituloCodigo;
    }

    public String getMaeDiagnosticoCapituloValor() {
        return maeDiagnosticoCapituloValor;
    }

    public void setMaeDiagnosticoCapituloValor(String maeDiagnosticoCapituloValor) {
        this.maeDiagnosticoCapituloValor = maeDiagnosticoCapituloValor;
    }

    public int getMaeDiagnosticoCategoriaId() {
        return maeDiagnosticoCategoriaId;
    }

    public void setMaeDiagnosticoCategoriaId(int maeDiagnosticoCategoriaId) {
        this.maeDiagnosticoCategoriaId = maeDiagnosticoCategoriaId;
    }

    public String getMaeDiagnosticoCategoriaCodigo() {
        return maeDiagnosticoCategoriaCodigo;
    }

    public void setMaeDiagnosticoCategoriaCodigo(String maeDiagnosticoCategoriaCodigo) {
        this.maeDiagnosticoCategoriaCodigo = maeDiagnosticoCategoriaCodigo;
    }

    public String getMaeDiagnosticoCategoriaValor() {
        return maeDiagnosticoCategoriaValor;
    }

    public void setMaeDiagnosticoCategoriaValor(String maeDiagnosticoCategoriaValor) {
        this.maeDiagnosticoCategoriaValor = maeDiagnosticoCategoriaValor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getSexoAplica() {
        return sexoAplica;
    }

    public void setSexoAplica(int sexoAplica) {
        this.sexoAplica = sexoAplica;
    }

    public String getEdadInferior() {
        return edadInferior;
    }

    public void setEdadInferior(String edadInferior) {
        this.edadInferior = edadInferior;
    }

    public String getEdadSuperior() {
        return edadSuperior;
    }

    public void setEdadSuperior(String edadSuperior) {
        this.edadSuperior = edadSuperior;
    }

    public String getGrupoMortalidad() {
        return grupoMortalidad;
    }

    public void setGrupoMortalidad(String grupoMortalidad) {
        this.grupoMortalidad = grupoMortalidad;
    }

    public String getGrupoMortalidadLista() {
        return grupoMortalidadLista;
    }

    public void setGrupoMortalidadLista(String grupoMortalidadLista) {
        this.grupoMortalidadLista = grupoMortalidadLista;
    }

    public Integer getValorLimiteInferior() {
        return valorLimiteInferior;
    }

    public void setValorLimiteInferior(Integer valorLimiteInferior) {
        this.valorLimiteInferior = valorLimiteInferior;
    }

    public Integer getValorLimiteSuperior() {
        return valorLimiteSuperior;
    }

    public void setValorLimiteSuperior(Integer valorLimiteSuperior) {
        this.valorLimiteSuperior = valorLimiteSuperior;
    }

    public Boolean getExcentoCobro() {
        return excentoCobro;
    }

    public void setExcentoCobro(Boolean excentoCobro) {
        this.excentoCobro = excentoCobro;
    }

    public boolean isAltoCosto() {
        return altoCosto;
    }

    public void setAltoCosto(boolean altoCosto) {
        this.altoCosto = altoCosto;
    }

    public List<MaDiagnosticoMedicamento> getListaMaDiagnosticoMedicamento() {
        return ListaMaDiagnosticoMedicamento;
    }

    public void setListaMaDiagnosticoMedicamento(List<MaDiagnosticoMedicamento> ListaMaDiagnosticoMedicamento) {
        this.ListaMaDiagnosticoMedicamento = ListaMaDiagnosticoMedicamento;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public Maestro getTipoDiagnostico() {
        return tipoDiagnostico;
    }

    public void setTipoDiagnostico(Maestro tipoDiagnostico) {
        this.tipoDiagnostico = tipoDiagnostico;
    }

    public Boolean getPriorizarCrue() {
        return priorizarCrue;
    }

    public void setPriorizarCrue(Boolean priorizarCrue) {
        this.priorizarCrue = priorizarCrue;
    }

    @Override
    public String toString() {
        return "MaDiagnostico{" + "id=" + id + "maeDiagnosticoCapituloId=" + maeDiagnosticoCapituloId + "maeDiagnosticoCapituloCodigo=" + maeDiagnosticoCapituloCodigo + 
                "maeDiagnosticoCapituloValor=" + maeDiagnosticoCapituloValor + "maeDiagnosticoCategoriaId=" + maeDiagnosticoCategoriaId +
                "maeDiagnosticoCategoriaCodigo=" + maeDiagnosticoCategoriaCodigo + "maeDiagnosticoCategoriaValor=" + maeDiagnosticoCategoriaValor + 
                "codigo=" + codigo + "nombre=" + nombre + "activo=" + activo + "sexoAplica=" + sexoAplica + "edadInferior=" + edadInferior + "edadSuperior=" + edadSuperior +
                "grupoMortalidad=" + grupoMortalidad + "grupoMortalidad=" + grupoMortalidad + "grupoMortalidadLista=" + grupoMortalidadLista + "valorLimiteInferior=" + valorLimiteInferior + 
                "valorLimiteSuperior=" + valorLimiteSuperior + "excentoCobro=" + excentoCobro + "altoCosto=" + altoCosto + "maCacId=" + maCacId+"maCacCodigo=" + maCacCodigo+"maCacValor=" + maCacValor+"}";
    }

    /**
     * @return the errorCarga
     */
    public String getErrorCarga() {
        return errorCarga;
    }

    /**
     * @param errorCarga the errorCarga to set
     */
    public void setErrorCarga(String errorCarga) {
        this.errorCarga = errorCarga;
    }

    /**
     * @return the actualizar
     */
    public boolean isActualizar() {
        return actualizar;
    }

    /**
     * @param actualizar the actualizar to set
     */
    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

    /**
     * @return the registroArchivo
     */
    public String getRegistroArchivo() {
        return registroArchivo;
    }

    /**
     * @param registroArchivo the registroArchivo to set
     */
    public void setRegistroArchivo(String registroArchivo) {
        this.registroArchivo = registroArchivo;
    }

    /**
     * @return the estadoInicialActivo
     */
    public boolean isEstadoInicialActivo() {
        return estadoInicialActivo;
    }

    /**
     * @param estadoInicialActivo the estadoInicialActivo to set
     */
    public void setEstadoInicialActivo(boolean estadoInicialActivo) {
        this.estadoInicialActivo = estadoInicialActivo;
    }

    /**
     * @return the maCacId
     */
    public Integer getMaCacId() {
        return maCacId;
    }

    /**
     * @param maCacId the maCacId to set
     */
    public void setMaCacId(Integer maCacId) {
        this.maCacId = maCacId;
    }

    /**
     * @return the maCacCodigo
     */
    public String getMaCacCodigo() {
        return maCacCodigo;
    }

    /**
     * @param maCacCodigo the maCacCodigo to set
     */
    public void setMaCacCodigo(String maCacCodigo) {
        this.maCacCodigo = maCacCodigo;
    }

    /**
     * @return the maCacValor
     */
    public String getMaCacValor() {
        return maCacValor;
    }

    /**
     * @param maCacValor the maCacValor to set
     */
    public void setMaCacValor(String maCacValor) {
        this.maCacValor = maCacValor;
    }
    
    
    
}
