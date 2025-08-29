/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.auditoria.concurrente;

import static com.saviasaludeps.savia.dominio.auditoria.concurrente.AucCargaFallo.TIPO_AFILIADO_HOSPITALIZADO;
import static com.saviasaludeps.savia.dominio.auditoria.concurrente.AucCargaFallo.TIPO_ESTRUCTURA;
import static com.saviasaludeps.savia.dominio.auditoria.concurrente.AucCargaFallo.TIPO_REGISTRO_EGRESO;
import static com.saviasaludeps.savia.dominio.auditoria.concurrente.AucCargaFallo.TIPO_REGISTRO_INGRESO;
import static com.saviasaludeps.savia.dominio.auditoria.concurrente.AucCargaFallo.TIPO_VALIDACION;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author idbohorquez
 */
public class AucCargaCierreSuceso extends Auditoria{
    
     //Tipos
    public static final int TIPO_ESTRUCTURA = 1;
    public static final int TIPO_VALIDACION = 2;
    public static final int TIPO_AFILIADO_HOSPITALIZADO = 3;
    public static final int TIPO_REGISTRO_INGRESO = 4;
    public static final int TIPO_REGISTRO_EGRESO = 5;

    //Texto
    private static final String ESTRUCTURA = "Estructura";
    private static final String VALIDACION = "Validación";
    private static final String AFILIADO_HOSPITALIZADO = "Afiliado hospitalizado";
    private static final String REGISTRO_INGRESO = "Registro exitoso";
    private static final String REGISTRO_EGRESO = "Registro egreso";
    
    private Integer id;
    private short tipo;
    private String descripcion;
    private short fila;
    private short columna;
    private AucCargaCierre aucCargaCierreId;

    public AucCargaCierreSuceso() {
    }

    public AucCargaCierreSuceso(Integer id) {
        this.id = id;
    }

    public AucCargaCierreSuceso(Integer id, short tipo, String descripcion, short fila, short columna, AucCargaCierre aucCargaCierreId) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fila = fila;
        this.columna = columna;
        this.aucCargaCierreId = aucCargaCierreId;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getTipo() {
        return tipo;
    }

    public void setTipo(short tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public short getFila() {
        return fila;
    }

    public void setFila(short fila) {
        this.fila = fila;
    }

    public short getColumna() {
        return columna;
    }

    public void setColumna(short columna) {
        this.columna = columna;
    }
    

    public AucCargaCierre getAucCargaCierreId() {
        return aucCargaCierreId;
    }

    public void setAucCargaCierreId(AucCargaCierre aucCargaCierreId) {
        this.aucCargaCierreId = aucCargaCierreId;
    }
    
    //Metodos
    public String getTipoStr() {
        String tip = "";
        switch (getTipo()) {
            case TIPO_ESTRUCTURA:
                tip = ESTRUCTURA;
                break;
            case TIPO_VALIDACION:
                tip = VALIDACION;
                break;
            case TIPO_AFILIADO_HOSPITALIZADO:
                tip = AFILIADO_HOSPITALIZADO;
                break;
            case TIPO_REGISTRO_INGRESO:
                tip = REGISTRO_INGRESO;
                break;
            case TIPO_REGISTRO_EGRESO:
                tip = REGISTRO_EGRESO;
                break;
        }
        return tip;
    }

    @Override
    public String toString() {
        return "AucCargaCierreSuceso{" + "id=" + id + ", tipo=" + tipo + ", descripcion=" + descripcion + ", fila=" + fila + ", columna=" + columna + ", aucCargaCierreId=" + aucCargaCierreId + '}';
    }

   
    
    
    
}
