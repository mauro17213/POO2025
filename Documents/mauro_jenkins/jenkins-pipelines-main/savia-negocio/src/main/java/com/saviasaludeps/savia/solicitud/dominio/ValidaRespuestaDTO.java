package com.saviasaludeps.savia.solicitud.dominio;

import java.io.Serializable;

public class ValidaRespuestaDTO implements Serializable {

    public final static int CODIGO_OK = 0;
    public final static int CODIGO_ERROR = 1;
    public final static int CODIGO_ERROR_MYSQL = 99;
    private int codigo;
    private String mensaje;
    private String funcion;
    private int fila;

    public ValidaRespuestaDTO() {
    }
    
    public ValidaRespuestaDTO(int fila, int codigo, String mensaje, String funcion) {
        this.fila = fila;
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.funcion = funcion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getCodigoStr() {
        String str = "";
        switch (this.codigo) {
            case CODIGO_OK:
                str = "OK";
                break;
            case CODIGO_ERROR:
                str = "ERROR";
                break;
            case CODIGO_ERROR_MYSQL:
                str = "ERROR MYSQL";
                break;
            default:
                break;
        }
        return str;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }
    
    @Override
    public String toString() {
        return "RespuestaDTO{" + "codigo=" + codigo + ", mensaje=" + mensaje + '}';
    }

}
