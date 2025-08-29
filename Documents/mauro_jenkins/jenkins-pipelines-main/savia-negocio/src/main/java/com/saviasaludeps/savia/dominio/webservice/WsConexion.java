/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.webservice;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

/**
 *
 * @author Fabian Coronel
 */
public class WsConexion extends Auditoria{
    
    public static final int TIPO_AUTENTICACION_SIN_SEGURIDAD = 0;
    public static final int TIPO_AUTENTICACION_USR_PASS = 1;
    public static final int TIPO_AUTENTICACION_USR_PASS_TOKEN  = 2;
    public static final int TIPO_AUTENTICACION_USR_PASS_IP  = 3;

    private Integer id;
    private String nombre;
    private int tipoAutenticacion;
    private String usuario;
    private String contrasena;
    private String contrasenaNueva;
    private String llave;
    private String ip;
    private boolean activo;
    private WsServicio servicio;
    private List<WsConexionServicio> listaWsConexiones;
    private List<WsTransaccion> listaWsTransaciones;
    private List<WsToken> listaWsTokens;
    private List<WsServicio> listaWsServicios;
    
    public WsConexion() {
    }

    public WsConexion(Integer id) {
        this.id = id;
    }

    public WsConexion(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public WsConexion(Integer id, String nombre, int tipoAutenticacion, String usuario, String contrasena, String llave, String ip, boolean activo, WsServicio servicio) {
        this.id = id;
        this.nombre = nombre;
        this.tipoAutenticacion = tipoAutenticacion;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.llave = llave;
        this.ip = ip;
        this.activo = activo;
        this.servicio = servicio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipoAutenticacion() {
        return tipoAutenticacion;
    }

    public String getTipoAutenticacionStr() {
        switch(tipoAutenticacion){
            case TIPO_AUTENTICACION_SIN_SEGURIDAD:
                return "Sin segurida";
            case TIPO_AUTENTICACION_USR_PASS:
                return "usr - pass";
            case TIPO_AUTENTICACION_USR_PASS_TOKEN:
                return "usr - pass - token";
            case TIPO_AUTENTICACION_USR_PASS_IP:
                return "usr - pass - ip";
            default:
                return "";
        }
    }

    public void setTipoAutenticacion(int tipoAutenticacion) {
        this.tipoAutenticacion = tipoAutenticacion;
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

    public String getContrasenaNueva() {
        return contrasenaNueva;
    }

    public void setContrasenaNueva(String contrasenaNueva) {
        this.contrasenaNueva = contrasenaNueva;
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isActivo() {
        return activo;
    }
    
    public String getActivoStr() {
        try {
            return (activo) ? "SI" : "NO";
        } catch (Exception e) {
            return "";
        }
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public WsServicio getServicio() {
        return servicio;
    }

    public void setServicio(WsServicio servicio) {
        this.servicio = servicio;
    }

    public List<WsConexionServicio> getListaWsConexiones() {
        return listaWsConexiones;
    }

    public void setListaWsConexiones(List<WsConexionServicio> listaWsConexiones) {
        this.listaWsConexiones = listaWsConexiones;
    }

    public List<WsTransaccion> getListaWsTransaciones() {
        return listaWsTransaciones;
    }

    public void setListaWsTransaciones(List<WsTransaccion> listaWsTransaciones) {
        this.listaWsTransaciones = listaWsTransaciones;
    }

    public List<WsToken> getListaWsTokens() {
        return listaWsTokens;
    }

    public void setListaWsTokens(List<WsToken> listaWsTokens) {
        this.listaWsTokens = listaWsTokens;
    }

    public List<WsServicio> getListaWsServicios() {
        return listaWsServicios;
    }

    public void setListaWsServicios(List<WsServicio> listaWsServicios) {
        this.listaWsServicios = listaWsServicios;
    }
    
}
