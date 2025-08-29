/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Stiven Giraldo
 */
public class AuAnexo4Reporte extends Auditoria {
    
    public static final int ESTADO_EN_PROCESO = 0;
    public static final int ESTADO_PROCESADO = 1;
    public static final int ESTADO_FALLIDO = 2;
    
    public static final String EN_PROCESO = "En Proceso";
    public static final String PROCESADO = "Procesado";
    public static final String FALLIDO = "Fallido";
    
    private Integer id;
    private String archivo;
    private String ruta;
    private Integer cantidadProcesada;
    private Integer cantidadTotal;
    private Date fechaInicio;
    private Date fechaFin;
    private int estado;
    private String descripcion;
    private int usuarioCreaId;
    
    //Variables Auxiliares
    private int empresaId;
    private CntPrestadorSede sede;
    private Integer prestador;
    

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

    public Integer getCantidadProcesada() {
        return cantidadProcesada;
    }

    public void setCantidadProcesada(Integer cantidadProcesada) {
        this.cantidadProcesada = cantidadProcesada;
    }

    public Integer getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(Integer cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getUsuarioCreaId() {
        return usuarioCreaId;
    }

    public void setUsuarioCreaId(int usuarioCreaId) {
        this.usuarioCreaId = usuarioCreaId;
    }

    public int getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(int empresaId) {
        this.empresaId = empresaId;
    }

    public CntPrestadorSede getSede() {
        return sede;
    }

    public void setSede(CntPrestadorSede sede) {
        this.sede = sede;
    }

    public Integer getPrestador() {
        return prestador;
    }

    public void setPrestador(Integer prestador) {
        this.prestador = prestador;
    }

    @Override
    public String toString() {
        return "AuAnexo4Reporte{" + "id=" + id + ", archivo=" + archivo + ", ruta=" + ruta + ", cantidadProcesada=" + cantidadProcesada + ", cantidadTotal=" + cantidadTotal + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", estado=" + estado + ", descripcion=" + descripcion + ", usuarioCreaId=" + usuarioCreaId + '}';
    }
    
    public Date obtenerFechaMinima() {
        Calendar fechaMinima = Calendar.getInstance();
        if (getFechaFin() != null) {
            fechaMinima.setTime(getFechaFin());
        }
        fechaMinima.add(Calendar.MONTH, -1);
        return fechaMinima.getTime();
    }
    
    public int obtenerProgreso() {
        if (getCantidadTotal() != 0) {
            return (getCantidadProcesada()*100) / getCantidadTotal(); 
        } else {
            return 0; 
        }               
    }
    
     public String obtenerEstadoReporte() {
        String texto = "";
        switch (estado) {
            case ESTADO_EN_PROCESO:
                texto = EN_PROCESO;
                break;
            case ESTADO_PROCESADO:
                texto = PROCESADO;
                break;
            case ESTADO_FALLIDO:
                texto = FALLIDO;
                break;
        }
        return texto;
    }
        
}
