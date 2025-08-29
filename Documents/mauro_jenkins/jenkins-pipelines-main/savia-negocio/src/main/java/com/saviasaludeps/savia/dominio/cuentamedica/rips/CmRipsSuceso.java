package com.saviasaludeps.savia.dominio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

public class CmRipsSuceso extends Auditoria {

    private Integer id;
    private String archivoNombre;
    private int archivoFila;
    private int cmRipsReglasId;
    private int cmRipsReglasMensajesId;
    private String nombreRegla;
    private int tipoRegla;
    private int alerta;
    private String descripcionMensaje;
    private CmRipsCarga cmRipsCarga;

    public final static int TIPO_NORMATIVA = 2;
    public final static int TIPO_NEGOCIO = 3;
    public final static int TIPO_ESTRUCTURA = 1;

    public final static int ALERTA_ERROR = 0;
    public final static int ALERTA_ADVERTENCIA = 1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public int getArchivoFila() {
        return archivoFila;
    }

    public void setArchivoFila(int archivoFila) {
        this.archivoFila = archivoFila;
    }

    public int getCmRipsReglasId() {
        return cmRipsReglasId;
    }

    public void setCmRipsReglasId(int cmRipsReglasId) {
        this.cmRipsReglasId = cmRipsReglasId;
    }

    public int getCmRipsReglasMensajesId() {
        return cmRipsReglasMensajesId;
    }

    public void setCmRipsReglasMensajesId(int cmRipsReglasMensajesId) {
        this.cmRipsReglasMensajesId = cmRipsReglasMensajesId;
    }

    public String getNombreRegla() {
        return nombreRegla;
    }

    public void setNombreRegla(String nombreRegla) {
        this.nombreRegla = nombreRegla;
    }

    public int getTipoRegla() {
        return tipoRegla;
    }

    public void setTipoRegla(int tipoRegla) {
        this.tipoRegla = tipoRegla;
    }

    public int getAlerta() {
        return alerta;
    }

    public void setAlerta(int alerta) {
        this.alerta = alerta;
    }

    public String getDescripcionMensaje() {
        return descripcionMensaje;
    }

    public void setDescripcionMensaje(String descripcionMensaje) {
        this.descripcionMensaje = descripcionMensaje;
    }

    public CmRipsCarga getCmRipsCarga() {
        return cmRipsCarga;
    }

    public void setCmRipsCarga(CmRipsCarga cmRipsCarga) {
        this.cmRipsCarga = cmRipsCarga;
    }

    public String getTipoStr() {
        String str = "";
        switch (this.tipoRegla) {
            case TIPO_ESTRUCTURA:
                str = "ESTRUCTURA";
                break;
            case TIPO_NEGOCIO:
                str = "NEGOCIO";
                break;
            case TIPO_NORMATIVA:
                str = "NORMATIVA";
                break;
        }
        return str;
    }

    public String getAlertaStr() {
        String str = "";
        switch (this.alerta) {
            case ALERTA_ADVERTENCIA:
                str = "ADVERTENCIA";
                break;
            case ALERTA_ERROR:
                str = "ERROR";
                break;
        }
        return str;
    }

}
