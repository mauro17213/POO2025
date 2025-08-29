/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.solicitud;

import com.saviasaludeps.savia.dominio.administracion.Usuario;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author jramirez
 */
public class GsZonaUsuario extends Auditoria {

    private Integer id;
    private int maeSala;
    private String gestiones;
    private GsZona gsZona;
    private Usuario usuario;
    private boolean activo = false;
    private boolean gestion01 = false;
    private boolean gestion02 = false;
    private boolean gestion03 = false;
    private boolean gestion04 = false;
    private boolean gestion05 = false;
    private boolean gestion06 = false;
    private boolean gestion07 = false;
    private boolean gestion08 = false;
    private boolean gestion09 = false;
    private boolean gestion10 = false;

    String arregloGestiones[] = {
        "",
        "Afiliación",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "Autorización"
    };

    public GsZonaUsuario() {
    }

    public GsZonaUsuario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeSala() {
        return maeSala;
    }

    public void setMaeSala(int maeSala) {
        this.maeSala = maeSala;
    }

    public String getGestiones() {
        return gestiones;
    }

    public String getGestionesStr() {
        String str = "";
        if (isGestion01()) {
            str += "" + arregloGestiones[1];
        }
        if (isGestion02()) {
            str += ((str.isEmpty()) ? "" : " - ") + arregloGestiones[2];
        }
        if (isGestion03()) {
            str += ((str.isEmpty()) ? "" : " - ") + arregloGestiones[3];
        }
        if (isGestion04()) {
            str += ((str.isEmpty()) ? "" : " - ") + arregloGestiones[4];
        }
        if (isGestion05()) {
            str += ((str.isEmpty()) ? "" : " - ") + arregloGestiones[5];
        }
        if (isGestion06()) {
            str += ((str.isEmpty()) ? "" : " - ") + arregloGestiones[6];
        }
        if (isGestion07()) {
            str += ((str.isEmpty()) ? "" : " - ") + arregloGestiones[7];
        }
        if (isGestion08()) {
            str += ((str.isEmpty()) ? "" : " - ") + arregloGestiones[8];
        }
        if (isGestion09()) {
            str += ((str.isEmpty()) ? "" : " - ") + arregloGestiones[9];
        }
        if (isGestion10()) {
            str += ((str.isEmpty()) ? "" : " - ") + arregloGestiones[10];
        }
        return str;
    }

    public void setGestiones(String gestiones) {
        this.gestiones = gestiones;
    }

    public GsZona getGsZona() {
        return gsZona;
    }

    public void setGsZona(GsZona gsZona) {
        this.gsZona = gsZona;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getActivoStr() {
        return (activo) ? "SI" : "NO";
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getGestionNombre(int i) {
        return arregloGestiones[i];
    }

    public boolean isGestion01() {
        return gestion01;
    }

    public void setGestion01(boolean gestion01) {
        this.gestion01 = gestion01;
    }

    public boolean isGestion02() {
        return gestion02;
    }

    public void setGestion02(boolean gestion02) {
        this.gestion02 = gestion02;
    }

    public boolean isGestion03() {
        return gestion03;
    }

    public void setGestion03(boolean gestion03) {
        this.gestion03 = gestion03;
    }

    public boolean isGestion04() {
        return gestion04;
    }

    public void setGestion04(boolean gestion04) {
        this.gestion04 = gestion04;
    }

    public boolean isGestion05() {
        return gestion05;
    }

    public void setGestion05(boolean gestion05) {
        this.gestion05 = gestion05;
    }

    public boolean isGestion06() {
        return gestion06;
    }

    public void setGestion06(boolean gestion06) {
        this.gestion06 = gestion06;
    }

    public boolean isGestion07() {
        return gestion07;
    }

    public void setGestion07(boolean gestion07) {
        this.gestion07 = gestion07;
    }

    public boolean isGestion08() {
        return gestion08;
    }

    public void setGestion08(boolean gestion08) {
        this.gestion08 = gestion08;
    }

    public boolean isGestion09() {
        return gestion09;
    }

    public void setGestion09(boolean gestion09) {
        this.gestion09 = gestion09;
    }

    public boolean isGestion10() {
        return gestion10;
    }

    public void setGestion10(boolean gestion10) {
        this.gestion10 = gestion10;
    }

    @Override
    public String toString() {
        return "GsZonaUsuario{" + "id=" + id + ", maeSala=" + maeSala + ", gestiones=" + gestiones + ", gsZona=" + gsZona + ", usuario=" + usuario + ", activo=" + activo + ", gestion01=" + gestion01 + ", gestion02=" + gestion02 + ", gestion03=" + gestion03 + ", gestion04=" + gestion04 + ", gestion05=" + gestion05 + ", gestion06=" + gestion06 + ", gestion07=" + gestion07 + ", gestion08=" + gestion08 + ", gestion09=" + gestion09 + ", gestion10=" + gestion10 + '}';
    }
}
