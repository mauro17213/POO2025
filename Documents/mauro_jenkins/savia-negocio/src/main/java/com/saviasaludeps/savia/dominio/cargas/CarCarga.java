/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.cargas;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import com.saviasaludeps.savia.utilidades.FH;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author aguevara
 */
public class CarCarga extends Auditoria implements Serializable{
    
    // Parametros para carga masiva
    public static final short ESTADO_EN_COLA = 0;  
    public static final short ESTADO_EN_PROCESO = 1;
    public static final short ESTADO_PROCESADA = 2;
    public static final short ESTADO_FALLIDA = 3;
    
    private Integer id;
    private int estado;
    private String nombreArchivo;
    private String ruta;
    private String archivo;
    private boolean existe;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private int registros;
    private int exitosos;
    private int fallidos;
    
    private CntPrestador prestador;
    private CarProceso carProcesoId;
    private CarPeriodo carPeriodoId;
    private CarProcesoPrestador prestadorId;
    private Empresa empresaId;
    
    private List<CarCargaRegistro> carCargaRegistrosList;
    
    //2025-06-10 jyperez campos adicionales
    private int tiempo;

    public CarCarga(Integer id) {
        this.id = id;

    }
    
    public CarCarga(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
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

    public int getRegistros() {
        return registros;
    }

    public void setRegistros(int registros) {
        this.registros = registros;
    }

    public int getExitosos() {
        return exitosos;
    }

    public void setExitosos(int exitosos) {
        this.exitosos = exitosos;
    }

    public int getFallidos() {
        return fallidos;
    }

    public void setFallidos(int fallidos) {
        this.fallidos = fallidos;
    }

    public CarProceso getCarProcesoId() {
        return carProcesoId;
    }

    public void setCarProcesoId(CarProceso carProcesoId) {
        this.carProcesoId = carProcesoId;
    }

    public CarPeriodo getCarPeriodoId() {
        return carPeriodoId;
    }

    public void setCarPeriodoId(CarPeriodo carPeriodoId) {
        this.carPeriodoId = carPeriodoId;
    }

    public CarProcesoPrestador getPrestadorId() {
        return prestadorId;
    }

    public void setPrestadorId(CarProcesoPrestador prestadorId) {
        this.prestadorId = prestadorId;
    }

    public Empresa getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Empresa empresaId) {
        this.empresaId = empresaId;
    }

    public CntPrestador getPrestador() {
        return prestador;
    }

    public void setPrestador(CntPrestador prestador) {
        this.prestador = prestador;
    }

    public List<CarCargaRegistro> getCarCargaRegistrosList() {
        return carCargaRegistrosList;
    }

    public void setCarCargaRegistrosList(List<CarCargaRegistro> carCargaRegistrosList) {
        this.carCargaRegistrosList = carCargaRegistrosList;
    }
    
    

    @Override
    public String toString() {
        return "CarCarga{" + "id=" + id + ", estado=" + estado + ", nombreArchivo=" + nombreArchivo + ", ruta=" + ruta + ", archivo=" + archivo + ", existe=" + existe + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin + ", registros=" + registros + ", exitosos=" + exitosos + ", fallidos=" + fallidos + ", prestador=" + prestador + ", carProcesoId=" + carProcesoId + ", carPeriodoId=" + carPeriodoId + ", prestadorId=" + prestadorId + ", empresaId=" + empresaId + '}';
    }

    /**
     * @return the tiempo
     */
    public int getTiempo() {
        if (this.fechaHoraInicio != null && this.fechaHoraFin != null) {
            tiempo = (int)(this.fechaHoraFin.getTime() - this.fechaHoraInicio.getTime());
        }
        return tiempo;
    }

    /**
     * @param tiempo the tiempo to set
     */
    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
    
public String getTiempoStr() {
        return FH.formatoMiliHMS(getTiempo());      
    }
}
