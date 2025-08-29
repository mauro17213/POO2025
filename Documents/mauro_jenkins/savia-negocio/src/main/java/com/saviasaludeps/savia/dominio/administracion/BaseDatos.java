/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.administracion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author rpalacic
 */
public class BaseDatos extends Auditoria {
    
    public static final int TIPO_SQL_SERVER = 0;
    public static final int TIPO_ORACLE = 1;
    public static final int TIPO_MYSQL = 2;
    public static final int TIPO_POSGRET = 3;

    private Integer id;
    private int tipo = TIPO_SQL_SERVER;
    private String cadena;
    private String nombre;
    private String usuario;
    private String contrasena;

    public BaseDatos() {
    }

    public BaseDatos(Integer id) {
        this.id = id;
    }

    public BaseDatos(Integer id, String cadena, String nombre, String usuario, String contrasena) {
        this.id = id;
        this.cadena = cadena;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasena = contrasena;
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

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    public String getTipoStr() {
        String str = "";
        switch(tipo){
            case TIPO_SQL_SERVER:
                str = "SQL Server";
                break;
            case TIPO_MYSQL:
                str = "MySQL";
                break;
            case TIPO_ORACLE:
                str = "Oracle";
                break;
            case TIPO_POSGRET:
                str = "Posgret";
                break;
        }
        return str;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "BaseDatos{" + "id=" + id + ", tipo=" + tipo + ", cadena=" + cadena + ", nombre=" + nombre + ", usuario=" + usuario + ", contrasena=" + contrasena + '}';
    }
    
}
