/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.informe;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 *
 * @author aguevara
 */
public class InfProcedimiento extends Auditoria{
    
    public static final int TAMANIO_SERVICIO= 20;
    
    private Integer id;
    private String nombreScript;
    private byte[] script;
    private boolean exitoso;
    private String descripcion;
    
    public InfProcedimiento() {
    }

    public InfProcedimiento(Integer id) {
        this.id = id;
    }
    
    public InfProcedimiento(Integer id, String nombreScript, byte[] script, boolean exitoso) {
        this.id = id;
        this.nombreScript = nombreScript;
        this.script = script;
        this.exitoso = exitoso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreScript() {
        return nombreScript;
    }

    public void setNombreScript(String nombreScript) {
        this.nombreScript = nombreScript;
    }
    
    public byte[] getScript() {
        return script;
    }

    public void setScript(byte[] script) {
        this.script = script;
    }
    
    public String getScriptCorto() {
        String texto = new String(script, StandardCharsets.UTF_8);
        if (texto != null && texto.length() >= TAMANIO_SERVICIO) {
            return texto.substring(0, TAMANIO_SERVICIO) + "...";
        } else {
            return texto;
        }
    }

    public boolean isExitoso() {
        return exitoso;
    }

    public void setExitoso(boolean exitoso) {
        this.exitoso = exitoso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcionCorto() {
        
        if (descripcion != null && descripcion.length() >= TAMANIO_SERVICIO) {
            return descripcion.substring(0, TAMANIO_SERVICIO) + "...";
        } else {
            return descripcion;
        }
    }

    @Override
    public String toString() {
        return "InfProcedimientos{" + "id=" + id + ", nombreScript=" + nombreScript + ", script=" + script + ", exitoso=" + exitoso + ", descripcion=" + descripcion + '}';
    }
    
    
}
