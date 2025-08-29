/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.especial;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;

/**
 *
 * @author idbohorquez
 */
public class PeDireccionadoGestion extends Auditoria implements Serializable {

    private Integer id;
    private int maeTipoId;
    private String maeTipoCodigo;
    private String maeTipoValor;
    private String descripcion;
    private PeDireccionado peDireccionadosId;

    public PeDireccionadoGestion() {
    }

    public PeDireccionadoGestion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }    

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public PeDireccionado getPeDireccionadosId() {
        return peDireccionadosId;
    }

    public void setPeDireccionadosId(PeDireccionado peDireccionadosId) {
        this.peDireccionadosId = peDireccionadosId;
    }

    public int getMaeTipoId() {
        return maeTipoId;
    }

    public void setMaeTipoId(int maeTipoId) {
        this.maeTipoId = maeTipoId;
    }

    public String getMaeTipoCodigo() {
        return maeTipoCodigo;
    }

    public void setMaeTipoCodigo(String maeTipoCodigo) {
        this.maeTipoCodigo = maeTipoCodigo;
    }

    public String getMaeTipoValor() {
        return maeTipoValor;
    }

    public void setMaeTipoValor(String maeTipoValor) {
        this.maeTipoValor = maeTipoValor;
    }

}
