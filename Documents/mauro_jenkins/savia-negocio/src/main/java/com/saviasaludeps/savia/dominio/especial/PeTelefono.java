/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.especial;

import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author Jaime Andres Olarte
 */
public class PeTelefono  extends Auditoria {
    
    public static final String PE_TELEFONO_TIPO_FIJO = "Fijo";
    public static final String PE_TELEFONO_TIPO_MOVIL = "Celular";
    public static final String PE_TELEFONO_TIPO_OFICINA = "Oficina";
    public static final String PE_TELEFONO_TIPO_NA = "No definido";
    
    private Integer id;
    private int tipo;
    private String numero;
    private String observacion;
    private AsegAfiliado asegAfiliadosId;
    private int maeTipoContactoId;
    private String maeTipoContactoCodigo;
    private String maeTipoContactoValor;
    private boolean nuevo;

    public PeTelefono() {
    }

    public PeTelefono(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public AsegAfiliado getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliado asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    public int getMaeTipoContactoId() {
        return maeTipoContactoId;
    }

    public void setMaeTipoContactoId(int maeTipoContactoId) {
        this.maeTipoContactoId = maeTipoContactoId;
    }

    public String getMaeTipoContactoCodigo() {
        return maeTipoContactoCodigo;
    }

    public void setMaeTipoContactoCodigo(String maeTipoContactoCodigo) {
        this.maeTipoContactoCodigo = maeTipoContactoCodigo;
    }

    public String getMaeTipoContactoValor() {
        return maeTipoContactoValor;
    }

    public void setMaeTipoContactoValor(String maeTipoContactoValor) {
        this.maeTipoContactoValor = maeTipoContactoValor;
    }

    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    
    
    @Override
    public String toString() {
        return "PeAdjunto{" + "id=" + id + ", Tipo=" + tipo + ", Numero=" + numero + ", Observacion=" + observacion + ", Afiliado=" + asegAfiliadosId + '}';
    }
    
}
