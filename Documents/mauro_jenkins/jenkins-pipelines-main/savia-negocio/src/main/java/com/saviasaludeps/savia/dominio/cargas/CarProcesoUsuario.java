/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.cargas;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author aguevara
 */
public class CarProcesoUsuario extends Auditoria{
    
    private Integer id;
    private boolean activo;
    private boolean admin;
    private CarProceso proceso;
    private Usuario usuario; 
    
    //Variables auxiliares
    private boolean modificado;
    private Usuario usuarioAnterior;
    
    public CarProcesoUsuario() {

    }

    public CarProcesoUsuario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public CarProceso getProceso() {
        return proceso;
    }

    public void setProceso(CarProceso proceso) {
        this.proceso = proceso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        
    }

    @Override
    public String toString() {
        return "CarProcesoUsuario{" + "id=" + id + ", activo=" + activo + ", admin=" + admin + ", proceso=" + proceso + ", usuario=" + usuario + ", modificado=" + modificado + ", usuarioAnterior=" + usuarioAnterior + '}';
    }


    
    //Metodos Auxiliares
    public String getActivoStr() {
        if (isActivo()) {
            return "Sí";
        } else {
            return "No";
        }
    }
    
     public String getAdminStr() {
        if (isActivo()) {
            return "Administrador";
        } else {
            return "Operativo";
        }
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
    
    
    
    
    
    
}
