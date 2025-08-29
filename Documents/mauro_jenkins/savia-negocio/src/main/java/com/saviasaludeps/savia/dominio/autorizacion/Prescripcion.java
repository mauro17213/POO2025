/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.maestro.MaDiagnostico;
import com.saviasaludeps.savia.dominio.maestro.MaInsumo;
import com.saviasaludeps.savia.dominio.maestro.MaMedicamento;
import com.saviasaludeps.savia.dominio.maestro.MaPaquete;
import com.saviasaludeps.savia.dominio.maestro.MaTecnologia;
import java.util.Date;

/**
 *
 * @author Stiven Giraldo
 */
public class Prescripcion {
    
    private Maestro tipoPrescripcion;
    private String codigoPrescripcion;
    private String descripcionPrescripcion;
    private int cantidadPrescripcion;
    private Date fechaPrescripcion;
    private MaDiagnostico diagnostico;
    private MaTecnologia tecnologia;
    private MaMedicamento medicamento;
    private MaInsumo insumo;
    private MaPaquete paquete;
    private String indicaciones;
    private boolean posfechado;
    private int duracion;
    private int numeroEntregas;
    private Maestro causaExterna;
    private Maestro finalidad;
    private Maestro tipoCatastrofico;
    private int dosis;
    private int frecuencia;
    private Maestro tipoFrecuencia;
    private Maestro viaAdministracion;
    private String posologia;
    private boolean efectoAdverso;

    public Maestro getTipoPrescripcion() {
        return tipoPrescripcion;
    }

    public void setTipoPrescripcion(Maestro tipoPrescripcion) {
        this.tipoPrescripcion = tipoPrescripcion;
    }

    public String getCodigoPrescripcion() {
        return codigoPrescripcion;
    }

    public void setCodigoPrescripcion(String codigoPrescripcion) {
        this.codigoPrescripcion = codigoPrescripcion;
    }

    public String getDescripcionPrescripcion() {
        return descripcionPrescripcion;
    }

    public void setDescripcionPrescripcion(String descripcionPrescripcion) {
        this.descripcionPrescripcion = descripcionPrescripcion;
    }

    public int getCantidadPrescripcion() {
        return cantidadPrescripcion;
    }

    public void setCantidadPrescripcion(int cantidadPrescripcion) {
        this.cantidadPrescripcion = cantidadPrescripcion;
    }

    public Date getFechaPrescripcion() {
        return fechaPrescripcion;
    }

    public void setFechaPrescripcion(Date fechaPrescripcion) {
        this.fechaPrescripcion = fechaPrescripcion;
    }

    public MaDiagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(MaDiagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }

    public MaTecnologia getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(MaTecnologia tecnologia) {
        this.tecnologia = tecnologia;
    }

    public MaMedicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(MaMedicamento medicamento) {
        this.medicamento = medicamento;
    }

    public MaInsumo getInsumo() {
        return insumo;
    }

    public void setInsumo(MaInsumo insumo) {
        this.insumo = insumo;
    }

    public MaPaquete getPaquete() {
        return paquete;
    }

    public void setPaquete(MaPaquete paquete) {
        this.paquete = paquete;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public boolean isPosfechado() {
        return posfechado;
    }

    public void setPosfechado(boolean posfechado) {
        this.posfechado = posfechado;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getNumeroEntregas() {
        return numeroEntregas;
    }

    public void setNumeroEntregas(int numeroEntregas) {
        this.numeroEntregas = numeroEntregas;
    }

    public Maestro getCausaExterna() {
        return causaExterna;
    }

    public void setCausaExterna(Maestro causaExterna) {
        this.causaExterna = causaExterna;
    }

    public Maestro getFinalidad() {
        return finalidad;
    }

    public void setFinalidad(Maestro finalidad) {
        this.finalidad = finalidad;
    }

    public Maestro getTipoCatastrofico() {
        return tipoCatastrofico;
    }

    public void setTipoCatastrofico(Maestro tipoCatastrofico) {
        this.tipoCatastrofico = tipoCatastrofico;
    }

    public int getDosis() {
        return dosis;
    }

    public void setDosis(int dosis) {
        this.dosis = dosis;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Maestro getTipoFrecuencia() {
        return tipoFrecuencia;
    }

    public void setTipoFrecuencia(Maestro tipoFrecuencia) {
        this.tipoFrecuencia = tipoFrecuencia;
    }

    public Maestro getViaAdministracion() {
        return viaAdministracion;
    }

    public void setViaAdministracion(Maestro viaAdministracion) {
        this.viaAdministracion = viaAdministracion;
    }

    public String getPosologia() {
        return posologia;
    }

    public void setPosologia(String posologia) {
        this.posologia = posologia;
    }

    public boolean isEfectoAdverso() {
        return efectoAdverso;
    }

    public void setEfectoAdverso(boolean efectoAdverso) {
        this.efectoAdverso = efectoAdverso;
    }
    
    
    
}
