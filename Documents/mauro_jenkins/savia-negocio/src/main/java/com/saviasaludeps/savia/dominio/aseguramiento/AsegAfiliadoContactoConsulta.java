/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author 
 */
public class AsegAfiliadoContactoConsulta extends Auditoria {
    
    // oRIGENES CONSULTA CONTACTO AFILIADO
    public final static String ORIGEN_TUTELA = "Tutelas";
    public final static String ORIGEN_ATENCION_USUARIO = "Atención al usuario";
    public final static String ORIGEN_GESTION_RIESGO = "Gestión del riesgo";
    public final static String ORIGEN_AUDIATORIA_CONCURRENTE = "Auditoria concurrente";
    public final static String ORIGEN_GESTION_JUDICIAL = "Gestión judicial";
    public final static String ORIGEN_ASEGURAMIENTO = "Aseguramiento";

    //TIPO CONTACTO POR DEFECTO CUANDO NO TIENE DEFINIDO
    public final static String TIPO_CONTACTO_NULL = "- -";
    public final static String TIPO_CONTACTO_FIJO = "Fijo";
    public final static String TIPO_CONTACTO_CELULAR = "Celular";
    public final static String TIPO_CONTACTO_OFICINA = "Oficina";

    private Integer id;
    private String numeroContacto;
    private int maeTipoContactoId;
    private String maeTipoContactoCodigo;
    private String maeTipoContactoValor;
    private String observacion;
    private String origen;

    public AsegAfiliadoContactoConsulta() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
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
        if (maeTipoContactoValor.equals("1")) {
            this.maeTipoContactoValor = TIPO_CONTACTO_FIJO;
        } else if (maeTipoContactoValor.equals("2")) {
            this.maeTipoContactoValor = TIPO_CONTACTO_CELULAR;
        } else if (maeTipoContactoValor.equals("3")) {
            this.maeTipoContactoValor = TIPO_CONTACTO_OFICINA;
        } else {
            this.maeTipoContactoValor = maeTipoContactoValor;
        }      
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    @Override
    public String toString() {
        return "AsegContactoAfiliado{" + "id=" + id + ", numeroContacto=" + numeroContacto + ", maeTipoContactoId=" + maeTipoContactoId + ", maeTipoContactoCodigo=" + maeTipoContactoCodigo + ", maeTipoContactoValor=" + maeTipoContactoValor + ", observacion=" + observacion + ", origen=" + origen + '}';
    }
    
    

}
