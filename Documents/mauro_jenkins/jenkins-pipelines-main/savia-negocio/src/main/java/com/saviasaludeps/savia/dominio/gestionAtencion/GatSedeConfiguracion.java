/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.gestionAtencion;

import com.saviasaludeps.savia.dominio.administracion.GnSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author sgiraldov
 */
public class GatSedeConfiguracion extends Auditoria {

    private static final int CODIGO_SIN_TURNERO = 0;
    private static final int CODIGO_TURNERO = 1;
    private static final int CODIGO_TURNERO_PARCIAL = 2;

    private static final String SIN_TUNERO = "SIN TURNERO";
    private static final String TURNERO = "TURNERO";
    private static final String TUNERO_PARCIAL = "TURNERO PARCIAL";

    private Integer id;
    private int turnero;
    private GnSede gnSedeId;

    public GatSedeConfiguracion() {
    }

    public GatSedeConfiguracion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTurnero() {
        return turnero;
    }

    public void setTurnero(int turnero) {
        this.turnero = turnero;
    }

    public GnSede getGnSedeId() {
        return gnSedeId;
    }

    public void setGnSedeId(GnSede gnSedeId) {
        this.gnSedeId = gnSedeId;
    }

    //Metodos auxiliares
    public String getTurneroStr() {
        switch (getTurnero()) {
            case CODIGO_SIN_TURNERO:
                return SIN_TUNERO;
            case CODIGO_TURNERO:
                return TURNERO;
            case CODIGO_TURNERO_PARCIAL:
                return TUNERO_PARCIAL;
            default:
                return "";
        }
    }
    
    public boolean isTurneroNormal() {
        return this.turnero == 1;
    }

    @Override
    public String toString() {
        return "GatSedeConfiguracion{" + "id=" + id + ", turnero=" + turnero + ", gnSedeId=" + gnSedeId + '}';
    }

}
