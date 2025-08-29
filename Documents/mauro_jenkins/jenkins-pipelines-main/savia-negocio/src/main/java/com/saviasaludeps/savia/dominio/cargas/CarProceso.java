/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.cargas;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

/**
 *
 * @author aguevara
 */
public class CarProceso extends Auditoria{
    
    private Integer id;
    private String nombre;
    private String descripcion;
    private boolean activo;
    private boolean editable;
    private String estructuraJson;
    
    private List<CarProcesoUsuario> carProcesoUsuariosList;
    private List<CarProcesoPrestador> carProcesoPrestadoresList;
    
    public CarProceso() {
        
    }

    public CarProceso(Integer id) {
        this.id = id;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getEstructuraJson() {
        return estructuraJson;
    }

    public void setEstructuraJson(String estructuraJson) {
        this.estructuraJson = estructuraJson;
    }

    public List<CarProcesoUsuario> getCarProcesoUsuariosList() {
        return carProcesoUsuariosList;
    }

    public void setCarProcesoUsuariosList(List<CarProcesoUsuario> carProcesoUsuariosList) {
        this.carProcesoUsuariosList = carProcesoUsuariosList;
    }

    public List<CarProcesoPrestador> getCarProcesoPrestadoresList() {
        return carProcesoPrestadoresList;
    }

    public void setCarProcesoPrestadoresList(List<CarProcesoPrestador> carProcesoPrestadoresList) {
        this.carProcesoPrestadoresList = carProcesoPrestadoresList;
    }
    
    public boolean isValidJson(String json) {
        try {
            JsonParser.parseString(json);
            return true; // El JSON es válido
        } catch (JsonSyntaxException e) {
            return false; // El JSON no es válido
        }
    }
    
    

    @Override
    public String toString() {
        return "CarProcesos{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", activo=" + activo + ", editable=" + editable + ", estructuraJson=" + estructuraJson + '}';
    }
    
    

    
    
    
}
