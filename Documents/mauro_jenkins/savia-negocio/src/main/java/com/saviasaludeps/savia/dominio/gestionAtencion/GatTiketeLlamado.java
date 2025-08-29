/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.gestionAtencion;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author acuartas
 */
public class GatTiketeLlamado extends Auditoria {
    
    public static final Short ESTADO_LLAMADO = 1;
    public static final Short ESTADO_CANCELADO = 2;

    private Integer id;
    private short estado;
    private Integer cantidad;
    private Integer maximo;
    private GatSedeTaquilla gatSedeTaquillaId;
    private GatTiquete gatTiqueteId;
    private Usuario gnUsuarioId;

    public GatTiketeLlamado() {
        this.estado = ESTADO_LLAMADO;
    }

    public GatTiketeLlamado(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        if (cantidad == null) {
            this.cantidad = 0;
        } else {
            this.cantidad = cantidad;
        }        
    }

    public Integer getMaximo() {
        return maximo;
    }

    public void setMaximo(Integer maximo) {
        if (maximo == null) {
            this.maximo = 3;
        } else {
            this.maximo = maximo;
        }
    }

    public GatSedeTaquilla getGatSedeTaquillaId() {
        return gatSedeTaquillaId;
    }

    public void setGatSedeTaquillaId(GatSedeTaquilla gatSedeTaquillaId) {
        this.gatSedeTaquillaId = gatSedeTaquillaId;
    }

    public GatTiquete getGatTiqueteId() {
        return gatTiqueteId;
    }

    public void setGatTiqueteId(GatTiquete gatTiqueteId) {
        this.gatTiqueteId = gatTiqueteId;
    }

    public Usuario getGnUsuarioId() {
        return gnUsuarioId;
    }

    public void setGnUsuarioId(Usuario gnUsuarioId) {
        this.gnUsuarioId = gnUsuarioId;
    }

    @Override
    public String toString() {
        return "GatTiketeLlamado{" + "id=" + id + ", estado=" + estado + ", gatSedeTaquillaId=" + gatSedeTaquillaId + ", gatTiqueteId=" + gatTiqueteId + ", gnUsuarioId=" + gnUsuarioId + '}';
    }
     
}
