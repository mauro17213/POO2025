/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.gestionAtencion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author sgiraldov
 */
public class GatServicioUmbral extends Auditoria {
    
    private Integer id;
    private int maeTipoServicioId;
    private String maeTipoServicioCodigo;
    private String maeTipoServicioValor;
    private int tiempo;
    private Integer tipo;
    
    //Auxiliares
    private boolean modificado;

    public GatServicioUmbral() {
    }

    public GatServicioUmbral(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeTipoServicioId() {
        return maeTipoServicioId;
    }

    public void setMaeTipoServicioId(int maeTipoServicioId) {
        this.maeTipoServicioId = maeTipoServicioId;
    }

    public String getMaeTipoServicioCodigo() {
        return maeTipoServicioCodigo;
    }

    public void setMaeTipoServicioCodigo(String maeTipoServicioCodigo) {
        this.maeTipoServicioCodigo = maeTipoServicioCodigo;
    }

    public String getMaeTipoServicioValor() {
        return maeTipoServicioValor;
    }

    public void setMaeTipoServicioValor(String maeTipoServicioValor) {
        this.maeTipoServicioValor = maeTipoServicioValor;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
    
    
    
    //Metodos auxiliares

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }


    @Override
    public String toString() {
        return "GatServicioUmbral{" + "id=" + id + ", maeTipoServicioId=" + maeTipoServicioId + ", maeTipoServicioCodigo=" + maeTipoServicioCodigo + ", maeTipoServicioValor=" + maeTipoServicioValor + ", tiempo=" + tiempo + ", tipo=" + tipo + '}';
    }
        
}
