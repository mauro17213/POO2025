/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.informe;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.List;

public class InfInforme extends Auditoria {

    private Integer id;
    private String nombre;
    private String nombreSp;
    private String descripcion;
    private Integer estado;
    private boolean activo;
    private boolean programado;
    private boolean requiereAprobacion;
    private Integer periodicidad;
    private Integer tipoPeriodicidad;
    private String nombreArchivo;
    private Date fechaProgramadaInicio;
    private Date fechaProgramadoFin;
    private String carpeta;
    private Boolean multipleGeneracion;
    private Boolean multipleEmpresa;
    private Integer maeTipoFormatoId;
    private String maeTipoFormatoCodigo;
    private String maeTipoFormatoValor;
    private InfGrupo grupo;
    private InfGenerado generado;

    private List<InfInformeVariable> listaVariables;
    private Date fechaUltimaGestion;

    public final static int TIPO_DIA = 0;
    public final static int TIPO_SEMANAL = 1;
    public final static int TIPO_MENSUAL = 2;
    public final static int TIPO_ANUAL = 3;

    public final static int PERIODICIDAD_DIA = 0;
    public final static int PERIODICIDAD_SEMANA = 1;
    public final static int PERIODICIDAD_MENSUAL = 2;
    public final static int PERIODICIDAD_ANUAL = 3;

    public InfInforme() {
    }

    public InfInforme(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreSp() {
        return nombreSp;
    }

    public void setNombreSp(String nombreSp) {
        this.nombreSp = nombreSp;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public boolean isProgramado() {
        return programado;
    }    

    public void setProgramado(boolean programado) {
        this.programado = programado;
    }

    public Integer getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(Integer periodicidad) {
        this.periodicidad = periodicidad;
    }

    public Date getFechaProgramadaInicio() {
        return fechaProgramadaInicio;
    }

    public void setFechaProgramadaInicio(Date fechaProgramadaInicio) {
        this.fechaProgramadaInicio = fechaProgramadaInicio;
    }

    public Date getFechaProgramadoFin() {
        return fechaProgramadoFin;
    }

    public void setFechaProgramadoFin(Date fechaProgramadoFin) {
        this.fechaProgramadoFin = fechaProgramadoFin;
    }

    public List<InfInformeVariable> getListaVariables() {
        return listaVariables;
    }

    public void setListaVariables(List<InfInformeVariable> listaVariables) {
        this.listaVariables = listaVariables;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public InfGrupo getGrupo() {
        return grupo;
    }

    public void setGrupo(InfGrupo grupo) {
        this.grupo = grupo;
    }

    public boolean isActivo() {
        return activo;
    }    

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getMultipleGeneracion() {
        return multipleGeneracion;
    }

    public void setMultipleGeneracion(Boolean multipleGeneracion) {
        this.multipleGeneracion = multipleGeneracion;
    }

    public Boolean getMultipleEmpresa() {
        return multipleEmpresa;
    }

    public void setMultipleEmpresa(Boolean multipleEmpresa) {
        this.multipleEmpresa = multipleEmpresa;
    }    

    public Integer getMaeTipoFormatoId() {
        return maeTipoFormatoId;
    }

    public void setMaeTipoFormatoId(Integer maeTipoFormatoId) {
        this.maeTipoFormatoId = maeTipoFormatoId;
    }

    public String getMaeTipoFormatoCodigo() {
        return maeTipoFormatoCodigo;
    }

    public void setMaeTipoFormatoCodigo(String maeTipoFormatoCodigo) {
        this.maeTipoFormatoCodigo = maeTipoFormatoCodigo;
    }

    public String getMaeTipoFormatoValor() {
        return maeTipoFormatoValor;
    }

    public void setMaeTipoFormatoValor(String maeTipoFormatoValor) {
        this.maeTipoFormatoValor = maeTipoFormatoValor;
    }
    

    public Date getFechaUltimaGestion() {
        return fechaUltimaGestion;
    }

    public void setFechaUltimaGestion(Date fechaUltimaGestion) {
        this.fechaUltimaGestion = fechaUltimaGestion;
    }

    public Integer getTipoPeriodicidad() {
        return tipoPeriodicidad;
    }

    public void setTipoPeriodicidad(Integer tipoPeriodicidad) {
        this.tipoPeriodicidad = tipoPeriodicidad;
    }

    public boolean isRequiereAprobacion() {
        return requiereAprobacion;
    }

    public void setRequiereAprobacion(boolean requiereAprobacion) {
        this.requiereAprobacion = requiereAprobacion;
    }

    public InfGenerado getGenerado() {
        return generado;
    }

    public void setGenerado(InfGenerado generado) {
        this.generado = generado;
    }

    public String getCarpeta() {
        return carpeta;
    }

    public void setCarpeta(String carpeta) {
        this.carpeta = carpeta;
    }
    
    //Metodos
    public String getActivoStr() {
        return (activo) ? "SI" : "NO";
    }
    
    public String getProgramadoStr() {
        return (programado) ? "SI" : "NO";
    }
    
    public String getMultipleGeneracionStr() {
        if (getMultipleGeneracion() != null && getMultipleGeneracion()) {
            return "Si";
        } else {
            return "No";
        }
    }

    public String getTipoPeriodicidadStr() {
        String str = "";
        if (tipoPeriodicidad != null) {
            switch (tipoPeriodicidad) {
                case TIPO_DIA:
                    str = "DIARIO";
                    break;
                case TIPO_SEMANAL:
                    str = "SEMANAL";
                    break;
                case TIPO_MENSUAL:
                    str = "MENSUAL";
                    break;
                case TIPO_ANUAL:
                    str = "ANUAL";
                    break;
                default:
                    break;
            }
        }
        return str;
    }
    
    public String getMultipleEmpresaStr() {
        if (getMultipleEmpresa() != null && getMultipleEmpresa()) {
            return "Si";
        } else {
            return "No";
        }
    }

    @Override
    public String toString() {
        return "InfInforme{" + "id=" + id + ", nombre=" + nombre + ", nombreSp=" + nombreSp + ", descripcion=" + descripcion + ", estado=" + estado + ", activo=" + activo + ", programado=" + programado + ", requiereAprobacion=" + requiereAprobacion + ", periodicidad=" + periodicidad + ", tipoPeriodicidad=" + tipoPeriodicidad + ", nombreArchivo=" + nombreArchivo + ", fechaProgramadaInicio=" + fechaProgramadaInicio + ", fechaProgramadoFin=" + fechaProgramadoFin + ", carpeta=" + carpeta + ", multipleGeneracion=" + multipleGeneracion + ", multipleEmpresa=" + multipleEmpresa + ", grupo=" + grupo + ", generado=" + generado + ", fechaUltimaGestion=" + fechaUltimaGestion + '}';
    }

}
