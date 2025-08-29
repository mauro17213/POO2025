/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.supervisioncontrato;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author aguevara
 */
public class ScIndicador extends Auditoria{
    
    public static final int TAMANIO_NOMBRE = 35;
    public static final int TAMANIO_DESCRIPCION = 45;
        
        
    private Integer id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String objetivo;
    private int maeClaseId;
    private String maeClaseCodigo;
    private String maeClaseValor;
    private String maeClaseTipo;
    private int maeMacroprocesoId;
    private String maeMacroprocesoCodigo;
    private String maeMacroprocesoValor;
    private String maeMacroprocesoTipo;
    private int maeProcesoId;
    private String maeProcesoCodigo;
    private String maeProcesoValor;
    private String maeProcesoTipo;
    private int maeAreaId;
    private String maeAreaCodigo;
    private String maeAreaValor;
    private String maeAreaTipo;
    private int tipo;
    private String normativa;
    private boolean activo;
    private boolean borrado;
    private String borradoObservacion;
    
    private String usuarioBorra;
    private String terminalBorra;
    private Date fechaHoraBorra;
    
    private String nombreCorto;
    private String descripcionCorto;

    public ScIndicador() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public int getMaeClaseId() {
        return maeClaseId;
    }

    public void setMaeClaseId(int maeClaseId) {
        this.maeClaseId = maeClaseId;
    }

    public String getMaeClaseCodigo() {
        return maeClaseCodigo;
    }

    public void setMaeClaseCodigo(String maeClaseCodigo) {
        this.maeClaseCodigo = maeClaseCodigo;
    }

    public String getMaeClaseValor() {
        return maeClaseValor;
    }

    public void setMaeClaseValor(String maeClaseValor) {
        this.maeClaseValor = maeClaseValor;
    }

    public String getMaeClaseTipo() {
        return maeClaseTipo;
    }

    public void setMaeClaseTipo(String maeClaseTipo) {
        this.maeClaseTipo = maeClaseTipo;
    }

    public int getMaeMacroprocesoId() {
        return maeMacroprocesoId;
    }

    public void setMaeMacroprocesoId(int maeMacroprocesoId) {
        this.maeMacroprocesoId = maeMacroprocesoId;
    }

    public String getMaeMacroprocesoCodigo() {
        return maeMacroprocesoCodigo;
    }

    public void setMaeMacroprocesoCodigo(String maeMacroprocesoCodigo) {
        this.maeMacroprocesoCodigo = maeMacroprocesoCodigo;
    }

    public String getMaeMacroprocesoValor() {
        return maeMacroprocesoValor;
    }

    public void setMaeMacroprocesoValor(String maeMacroprocesoValor) {
        this.maeMacroprocesoValor = maeMacroprocesoValor;
    }

    public String getMaeMacroprocesoTipo() {
        return maeMacroprocesoTipo;
    }

    public void setMaeMacroprocesoTipo(String maeMacroprocesoTipo) {
        this.maeMacroprocesoTipo = maeMacroprocesoTipo;
    }

    public int getMaeProcesoId() {
        return maeProcesoId;
    }

    public void setMaeProcesoId(int maeProcesoId) {
        this.maeProcesoId = maeProcesoId;
    }

    public String getMaeProcesoCodigo() {
        return maeProcesoCodigo;
    }

    public void setMaeProcesoCodigo(String maeProcesoCodigo) {
        this.maeProcesoCodigo = maeProcesoCodigo;
    }

    public String getMaeProcesoValor() {
        return maeProcesoValor;
    }

    public void setMaeProcesoValor(String maeProcesoValor) {
        this.maeProcesoValor = maeProcesoValor;
    }

    public String getMaeProcesoTipo() {
        return maeProcesoTipo;
    }

    public void setMaeProcesoTipo(String maeProcesoTipo) {
        this.maeProcesoTipo = maeProcesoTipo;
    }

    public int getMaeAreaId() {
        return maeAreaId;
    }

    public void setMaeAreaId(int maeAreaId) {
        this.maeAreaId = maeAreaId;
    }

    public String getMaeAreaCodigo() {
        return maeAreaCodigo;
    }

    public void setMaeAreaCodigo(String maeAreaCodigo) {
        this.maeAreaCodigo = maeAreaCodigo;
    }

    public String getMaeAreaValor() {
        return maeAreaValor;
    }

    public void setMaeAreaValor(String maeAreaValor) {
        this.maeAreaValor = maeAreaValor;
    }

    public String getMaeAreaTipo() {
        return maeAreaTipo;
    }

    public void setMaeAreaTipo(String maeAreaTipo) {
        this.maeAreaTipo = maeAreaTipo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNormativa() {
        return normativa;
    }

    public void setNormativa(String normativa) {
        this.normativa = normativa;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public String getBorradoObservacion() {
        return borradoObservacion;
    }

    public void setBorradoObservacion(String borradoObservacion) {
        this.borradoObservacion = borradoObservacion;
    }

    public String getUsuarioBorra() {
        return usuarioBorra;
    }

    public void setUsuarioBorra(String usuarioBorra) {
        this.usuarioBorra = usuarioBorra;
    }

    public String getTerminalBorra() {
        return terminalBorra;
    }

    public void setTerminalBorra(String terminalBorra) {
        this.terminalBorra = terminalBorra;
    }

    public Date getFechaHoraBorra() {
        return fechaHoraBorra;
    }

    public void setFechaHoraBorra(Date fechaHoraBorra) {
        this.fechaHoraBorra = fechaHoraBorra;
    }

    public String getNombreCorto() {
       if (getNombre()!= null) {
            nombreCorto = getNombre();
            if (getDescripcion().length() >= TAMANIO_NOMBRE) {
                return nombreCorto.substring(0, TAMANIO_NOMBRE) + "..";
            } else {
                return nombreCorto;
            }
        }
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public String getDescripcionCorto() {
        if (getDescripcion() != null) {
            descripcionCorto = getDescripcion();
            if (getDescripcion().length() >= TAMANIO_DESCRIPCION) {
                return descripcionCorto.substring(0, TAMANIO_DESCRIPCION) + "..";
            } else {
                return descripcionCorto;
            }
        }
        return descripcionCorto;
    }

    public void setDescripcionCorto(String descripcionCorto) {
        this.descripcionCorto = descripcionCorto;
    }
    
    @Override
    public String toString() {
        return "ScIndicador{" + "id=" + id + ", codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", objetivo=" + objetivo + ", maeClaseId=" + maeClaseId + ", maeClaseCodigo=" + maeClaseCodigo + ", maeClaseValor=" + maeClaseValor + ", maeClaseTipo=" + maeClaseTipo + ", maeMacroprocesoId=" + maeMacroprocesoId + ", maeMacroprocesoCodigo=" + maeMacroprocesoCodigo + ", maeMacroprocesoValor=" + maeMacroprocesoValor + ", maeMacroprocesoTipo=" + maeMacroprocesoTipo + ", maeProcesoId=" + maeProcesoId + ", maeProcesoCodigo=" + maeProcesoCodigo + ", maeProcesoValor=" + maeProcesoValor + ", maeProcesoTipo=" + maeProcesoTipo + ", maeAreaId=" + maeAreaId + ", maeAreaCodigo=" + maeAreaCodigo + ", maeAreaValor=" + maeAreaValor + ", maeAreaTipo=" + maeAreaTipo + ", tipo=" + tipo + ", normativa=" + normativa + ", activo=" + activo + ", borrado=" + borrado + ", borradoObservacion=" + borradoObservacion + ", usuarioBorra=" + usuarioBorra + ", terminalBorra=" + terminalBorra + ", fechaHoraBorra=" + fechaHoraBorra + ", nombreCorto=" + nombreCorto + '}';
    }
    
    
    
}
