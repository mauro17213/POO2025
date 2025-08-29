/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.gestionAtencion;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author idbohorquez
 */
public class GatTiempo extends Auditoria{
    
    private Integer id;
    private int tipo;
    private Date fechaInicio;
    private Date fechaFin;
    private int tiempoTotal;
    private GatAtencion atencionesId;
    private Usuario usuariosId;
    private boolean activo;
//    private GatServicioUmbral servicioUmbral;
    
    //Auxiliares
    private Integer servicioUbralId;

    public GatTiempo() {
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
    
    public String getTipoStr() {
        String _str;
        switch (tipo) {
            case 1:
                _str = "Refrigerio 15 minutos";
                break;
            case 2:
                _str = "Almuerzo 60 minutos";
                break;
            case 3:
                _str = "Reunion 60 minutos";
                break;
            case 4:
                _str = "WC 5 minutos";
                break;
            default:
                _str = "Indefinido";
                break;
        }
        return _str;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
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

    public int getTiempoTotal() {
        return tiempoTotal;
    }

    public void setTiempoTotal(int tiempoTotal) {
        this.tiempoTotal = tiempoTotal;
    }

    public GatAtencion getAtencionesId() {
        return atencionesId;
    }

    public void setAtencionesId(GatAtencion atencionesId) {
        this.atencionesId = atencionesId;
    }

    public Usuario getUsuariosId() {
        return usuariosId;
    }

    public void setUsuariosId(Usuario usuariosId) {
        this.usuariosId = usuariosId;
    }

    public Integer getServicioUbralId() {
        return servicioUbralId;
    }

    public void setServicioUbralId(Integer servicioUbralId) {
        this.servicioUbralId = servicioUbralId;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }    

//    public GatServicioUmbral getServicioUmbral() {
//        return servicioUmbral;
//    }
//
//    public void setServicioUmbral(GatServicioUmbral servicioUmbral) {
//        this.servicioUmbral = servicioUmbral;
//    }

    @Override
    public String toString() {
        return "GatTiempo{" + "id=" + id + ", tipo=" + tipo + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", tiempoTotal=" + tiempoTotal + ", atencionesId=" + atencionesId + ", usuariosId=" + usuariosId + '}';
    }
     
}
