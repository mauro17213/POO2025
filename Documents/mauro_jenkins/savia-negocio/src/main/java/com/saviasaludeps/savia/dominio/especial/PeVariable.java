/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.especial;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

/**
 *
 * @author jdlopez
 */
public class PeVariable extends Auditoria {
    private Integer id;
    private String nombre;
    private String descripcion;
    private boolean activa;
    private Integer tipo;
    private boolean recurrente;
    private boolean obligatoria;
    private boolean editable;
    private PePrograma pePrograma;
    private List<PeValidacion> validaciones;
    private List<PeCorrelacion> validacionesCorrelacion;
    private PeInsumoCalculo insumoCalculo;
    
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public Integer getTipo() {
        return tipo;
    }
    
    public String getTipoStr() {
        return PeTipoVariable.getNombreById(this.getTipo());
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public boolean isRecurrente() {
        return recurrente;
    }

    public void setRecurrente(boolean recurrente) {
        this.recurrente = recurrente;
    }

    public PePrograma getPePrograma() {
        return pePrograma;
    }

    public void setPePrograma(PePrograma pePrograma) {
        this.pePrograma = pePrograma;
    }
    
    public String getActivaStr() {
        return (this.isActiva()) ? "SI" : "NO";
    }
    
    public String getRecurrenteStr() {
        return (this.isRecurrente()) ? "SI" : "NO";
    }

    public boolean isObligatoria() {
        return obligatoria;
    }

    public void setObligatoria(boolean obligatoria) {
        this.obligatoria = obligatoria;
    }

    public String getObligatoriaStr() {
        return (this.isObligatoria()) ? "SI" : "NO";
    }
    
    public String getEditableStr() {
        return (this.isEditable()) ? "SI" : "NO";
    }

    public List<PeValidacion> getValidaciones() {
        return validaciones;
    }

    public void setValidaciones(List<PeValidacion> validaciones) {
        this.validaciones = validaciones;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public PeInsumoCalculo getInsumoCalculo() {
        return insumoCalculo;
    }

    public void setInsumoCalculo(PeInsumoCalculo insumoCalculo) {
        this.insumoCalculo = insumoCalculo;
    }

    public List<PeCorrelacion> getValidacionesCorrelacion() {
        return validacionesCorrelacion;
    }

    public void setValidacionesCorrelacion(List<PeCorrelacion> validacionesCorrelacion) {
        this.validacionesCorrelacion = validacionesCorrelacion;
    }
    
    @Override
    public String toString() {
        return "PeVariable{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", activa=" + activa + ", tipo=" + tipo  
                + ", editable=" + editable + ", recurrente=" + recurrente + ", obligatoria=" + obligatoria + ", programa=" + (pePrograma != null ? pePrograma.getId() : null) 
                + ", validaciones=" + validaciones + ", validacionesCorrelacion=" + validacionesCorrelacion + ", insumoCalculo=" + insumoCalculo + '}';
    }

}
