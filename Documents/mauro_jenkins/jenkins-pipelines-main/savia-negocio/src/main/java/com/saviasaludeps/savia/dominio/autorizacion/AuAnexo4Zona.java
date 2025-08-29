/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public class AuAnexo4Zona extends Auditoria {
    
    private Integer id;
    private int ubicacionId;
    private String ubicacionValor;
    private String nombre;
    private boolean activo;
    private List<AuAnexo4Destino> auAnexo4DestinoList;
    
    //Variables auxiliares
    private int ordenMaximo;

    public AuAnexo4Zona(Integer id) {
        this.id = id;
    }

    public AuAnexo4Zona() {
    }   

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(int ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    public String getUbicacionValor() {
        return ubicacionValor;
    }

    public void setUbicacionValor(String ubicacionValor) {
        this.ubicacionValor = ubicacionValor;
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

    public List<AuAnexo4Destino> getAuAnexo4DestinoList() {
        return auAnexo4DestinoList;
    }

    public void setAuAnexo4DestinoList(List<AuAnexo4Destino> auAnexo4DestinoList) {
        this.ordenMaximo = auAnexo4DestinoList.size();
        this.auAnexo4DestinoList = auAnexo4DestinoList;
    }

    public int getOrdenMaximo() {
        return ordenMaximo;
    }

    public void setOrdenMaximo(int ordenMaximo) {
        this.ordenMaximo = ordenMaximo;
    }

    @Override
    public String toString() {
        return "AuAnexo4Zona{" + "id=" + id + ", ubicacionId=" + ubicacionId + ", ubicacionValor=" + ubicacionValor + ", nombre=" + nombre + ", activo=" + activo + '}';
    }

        
}
