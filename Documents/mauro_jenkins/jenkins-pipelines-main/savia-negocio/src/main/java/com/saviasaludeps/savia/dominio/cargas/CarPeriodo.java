/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.cargas;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author aguevara
 */
public class CarPeriodo extends Auditoria{
    
    private Integer id;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private CarProceso carProceso;
    
     public CarPeriodo(){
         
     }

    public CarPeriodo(Integer id) {
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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public CarProceso getCarProceso() {
        return carProceso;
    }

    public void setCarProceso(CarProceso carProceso) {
        this.carProceso = carProceso;
    }

    @Override
    public String toString() {
        return "CarPeriodo{" + "id=" + id + ", nombre=" + nombre + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", carProceso=" + carProceso + '}';
    }
    
    

    
    
    
    
    
    
    
    
}
