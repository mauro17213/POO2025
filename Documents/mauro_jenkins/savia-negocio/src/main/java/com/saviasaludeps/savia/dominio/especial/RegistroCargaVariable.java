/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.especial;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author jdlopez
 * Representa un registro de la carga de variable
 */
public class RegistroCargaVariable  implements Serializable {
    
    //datos afiliado
    private Integer consecutivo;
    private Integer tipoDocumento;
    private String documento;
    private List<PeTelefono> telefonos;
    //valores de variable
    private List<PeVariableValor> valores;
    //errores al momento de procesamiento
    private String errorCarga;
    private String advertenciaCarga;
    private String lineaCarga;
    private AsegAfiliado asegAfiliado;
    private PeAfiliadosPrograma afiliadoPrograma;

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Integer getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(Integer tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public List<PeTelefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<PeTelefono> numeroTelefonos) {
        this.telefonos = numeroTelefonos;
    }

    public List<PeVariableValor> getValores() {
        return valores;
    }

    public void setValores(List<PeVariableValor> valores) {
        this.valores = valores;
    }

    public String getErrorCarga() {
        return errorCarga;
    }

    public void setErrorCarga(String errorCarga) {
        this.errorCarga = errorCarga;
    }

    public String getAdvertenciaCarga() {
        return advertenciaCarga;
    }

    public void setAdvertenciaCarga(String advertenciaCarga) {
        this.advertenciaCarga = advertenciaCarga;
    }
    
    public String getLineaCarga() {
        return lineaCarga;
    }

    public void setLineaCarga(String lineaCarga) {
        this.lineaCarga = lineaCarga;
    }

    public AsegAfiliado getAsegAfiliado() {
        return asegAfiliado;
    }

    public void setAsegAfiliado(AsegAfiliado asegAfiliado) {
        this.asegAfiliado = asegAfiliado;
    }

    public PeAfiliadosPrograma getAfiliadoPrograma() {
        return afiliadoPrograma;
    }

    public void setAfiliadoPrograma(PeAfiliadosPrograma afiliadoPrograma) {
        this.afiliadoPrograma = afiliadoPrograma;
    }
    
    @Override
    public String toString() {
        return "RegistroCargaVariable{" + "consecutivo=" + consecutivo + ", tipoDocumento=" + tipoDocumento + ", documento=" + documento 
                + ", telefonos=" + telefonos + ", valores=" + valores + ", asegAfiliado=" + (asegAfiliado != null ? asegAfiliado.getId() : null) 
                + ", afiliadoPrograma=" + (afiliadoPrograma != null ? afiliadoPrograma.getId() : null) + ", advertenciaCarga=" + advertenciaCarga + ", errorCarga=" + errorCarga + '}';
    }
    
    
}
