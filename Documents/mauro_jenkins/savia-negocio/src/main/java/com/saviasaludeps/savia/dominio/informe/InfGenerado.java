/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.informe;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.List;
import com.saviasaludeps.savia.utilidades.FH;

/**
 *
 * @author oquiroz
 */
public class InfGenerado extends Auditoria {

    public final static int ESTADO_EN_COLA = 0;
    public final static int ESTADO_PROCESANDO = 1;
    public final static int ESTADO_GENERADO = 2;
    public final static int ESTADO_ERROR = 4;

    private Integer id;
    private String archivo;
    private String nombreArchivo;
    private String descripcion;
    private String ruta;
    private boolean existe;
    private Boolean plantilla;
    private Integer estado;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private Integer gnEmpresa;
    private int tiempo;    
    private List<InfDescargado> listaDescargados;
    private InfInforme infInforme;
    private List<InfInformeValor> listaValores;

    public InfGenerado() {
    }

    public InfGenerado(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public InfInforme getInfInforme() {
        return infInforme;
    }

    public void setInfInforme(InfInforme infInforme) {
        this.infInforme = infInforme;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Boolean getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(Boolean plantilla) {
        this.plantilla = plantilla;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public List<InfInformeValor> getListaValores() {
        return listaValores;
    }

    public void setListaValores(List<InfInformeValor> listaValores) {
        this.listaValores = listaValores;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Integer getGnEmpresa() {
        return gnEmpresa;
    }

    public void setGnEmpresa(Integer gnEmpresa) {
        this.gnEmpresa = gnEmpresa;
    }

    public List<InfDescargado> getListaDescargados() {
        return listaDescargados;
    }

    public void setListaDescargados(List<InfDescargado> listaDescargados) {
        this.listaDescargados = listaDescargados;
    }

    public String getEstadoStr() {
        String str = "";
        switch (estado) {
            case ESTADO_EN_COLA:
                str = "EN COLA";
                break;
            case ESTADO_PROCESANDO:
                str = "PROCESANDO";
                break;
            case ESTADO_GENERADO:
                str = "GENERADO";
                break;
            case ESTADO_ERROR:
                str = "ERROR";
                break;
            default:
                break;
        }
        return str;
    }
    
    public String getTiempoStr() {
        return FH.formatoSegHMS(getTiempo());      
    }

    @Override
    public String toString() {
        return "InfGenerado{" + "id=" + id + ", archivo=" + archivo + ", nombreArchivo=" + nombreArchivo + ", descripcion=" + descripcion + ", ruta=" + ruta + ", existe=" + existe + ", plantilla=" + plantilla + ", estado=" + estado + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin + ", tiempo=" + tiempo + ", infInforme=" + infInforme + ", listaValores=" + listaValores + '}';
    }


}
