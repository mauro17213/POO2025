/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.administracion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rpalacic
 */
public class Maestro extends Auditoria {

//    //General
    private Integer id;
    private String nombre = "";
    private String valor = "";
    private String tipo = "";
    private MaestroTipo maestroTipo;
    private GnValidacionCampo validacionCampoId;
    private String descripcion = "";
    private boolean activo = true;
    private static LinkedHashMap<String, String> tipos = new LinkedHashMap<>();
    private List<Maestro> maestroPadres;
    private List<MaestroAccion> maestroAcciones;

    public Maestro() {
        this.validacionCampoId = new GnValidacionCampo();
    }

    public Maestro(Integer id) {
        this.id = id;
        this.validacionCampoId = new GnValidacionCampo();
    }

    public Maestro(Integer id, String nombre, String tipo, String valor, String descripcion, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.valor = valor;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public static String getTipoStr(String tipo) {
        return tipos.get(tipo);
    }

    public MaestroTipo getMaestroTipo() {
        if (maestroTipo == null) {
            maestroTipo = new MaestroTipo();
        }
        return maestroTipo;
    }

    public void setMaestroTipo(MaestroTipo maestroTipo) {
        this.maestroTipo = maestroTipo;
    }

    public static LinkedHashMap<String, String> getTipos() {
        return tipos;
    }

    public static LinkedHashMap<String, String> getTiposListar() {
        LinkedHashMap<String, String> tiposLista = new LinkedHashMap();
        for (Map.Entry<String, String> entry : tipos.entrySet()) {
            tiposLista.put(entry.getValue(), entry.getKey());
        }
        return tiposLista;
    }

    public static Map<String, String> getTipoDependencia() {
        Map<String, String> tipos = new LinkedHashMap<>();
        return tipos;
    }

    public static Map<String, Integer> getTipoAcciones() {
        Map<String, Integer> tiposAcciones = new LinkedHashMap<>();
        return tiposAcciones;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDescripcionCorta() {
        if (descripcion != null && descripcion.length() > 32) {
            return descripcion.substring(0, 32);
        } else {
            return descripcion;
        }
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public String getActivoStr() {
        return (isActivo()) ? "SI" : "NO";
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * Valida si este maestro contiene una accion
     *
     * @param id (int) id de la accion a validar
     * @return (boolean) indica si la eccion está contenida o no
     */
    public boolean contieneAccion(int id) {
        boolean contiene = false;
        if (maestroAcciones != null) {
            for (MaestroAccion accion : maestroAcciones) {
                if (accion.getId() == id) {
                    contiene = true;
                    break;
                }
            }
        }
        return contiene;
    }

    public List<MaestroAccion> getMaestroAcciones() {
        return maestroAcciones;
    }

    public void setMaestroAcciones(List<MaestroAccion> maestroAcciones) {
        this.maestroAcciones = maestroAcciones;
    }

    public List<Maestro> getMaestroPadres() {
        return maestroPadres;
    }

    public void setMaestroPadres(List<Maestro> maestroPadres) {
        this.maestroPadres = maestroPadres;
    }

    public GnValidacionCampo getValidacionCampoId() {
        return validacionCampoId;
    }

    public void setValidacionCampoId(GnValidacionCampo validacionCampoId) {
        this.validacionCampoId = validacionCampoId;
    }

    @Override
    public String toString() {
//        return "Maestro{" + "id=" + id + ", tipo=" + getTipo() + ", valor=" + valor + ", nombre=" + nombre + ", descripcion=" + descripcion + ", activo=" + getActivoStr() + ", maestroAccion=" + maestroAccion + '}';
        return "Maestro{" + "id=" + id + ", tipo=" + getTipo() + ", valor=" + valor + ", nombre=" + nombre + ", descripcion=" + descripcion + ", activo=" + getActivoStr() + '}';
    }

}
