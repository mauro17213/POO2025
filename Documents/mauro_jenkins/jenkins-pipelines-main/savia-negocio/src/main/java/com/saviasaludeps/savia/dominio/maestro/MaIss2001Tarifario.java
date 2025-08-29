/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.maestro;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;

/**
 *
 * @author AlexanderDiaz
 */
public class MaIss2001Tarifario extends Auditoria {
    private Integer id;
    private String referencia;
    private String codigo;
    private String descripcion;
    private int tipo;
    private Integer uvr;
    private BigDecimal monto;
    
    public MaIss2001Tarifario() {
    
    }
    
    public MaIss2001Tarifario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Integer getUvr() {
        return uvr;
    }

    public void setUvr(Integer uvr) {
        this.uvr = uvr;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
    
    
    @Override
    public String toString() {
        return "MaIss2001{" + "id=" + id + "referencia=" + referencia + "codigo=" + codigo + "descripcion=" + descripcion + "tipo=" + tipo + "uvr=" + uvr + "monto=" + monto + "}" ;
    }
}
