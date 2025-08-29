/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.cargas;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
/**
 *
 * @author aguevara
 */
public class CarProcesoPrestador extends Auditoria {

    private Integer id;
    private boolean activo;
    private CntPrestador prestador; // Inicializa la lista
    private CarProceso proceso;
    
    //Variables auxiliares
    private boolean modificado;
    private Usuario usuarioAnterior;

    public CarProcesoPrestador() {
        // Constructor por defecto
    }

    public CarProcesoPrestador(Integer id) {
        this.id = id;
    }
  
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CarProceso getProceso() {
        return proceso;
    }

    public void setProceso(CarProceso proceso) {
        this.proceso = proceso;
    }

    public CntPrestador getPrestador() {
        return prestador;
    }

    public void setPrestador(CntPrestador prestador) {
        this.prestador = prestador;
    }

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }

    public Usuario getUsuarioAnterior() {
        return usuarioAnterior;
    }

    public void setUsuarioAnterior(Usuario usuarioAnterior) {
        this.usuarioAnterior = usuarioAnterior;
    }
    
    



    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "CarProcesoPrestador{" + "id=" + id + ", activo=" + activo + ", prestador=" + prestador + ", proceso=" + proceso + '}';
    }


    
    

    
    
    
    
            
    
}
