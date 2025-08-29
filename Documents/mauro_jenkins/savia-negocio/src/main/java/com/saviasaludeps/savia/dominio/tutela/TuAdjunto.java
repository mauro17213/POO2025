/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.tutela;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author pavacca
 */
public class TuAdjunto extends Auditoria{

    private Integer id;
    private Integer cntPrestadorSedeId;
    private Integer maeTipoAnexoId;
    private String maeTipoAnexoCodigo;
    private String maeTipoAnexoValor;
    private String nombreArchivo;
    private String archivo;
    private String ruta;
    private String observacion;
    private int pos;
    private TuSeguimiento tuSeguimientosId;
    private TuTutelaEstado tuTutelaEstadosId;
    private TuTutelaItem tuTutelaItemsId;
    
    public TuAdjunto(){
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCntPrestadorSedeId() {
        return cntPrestadorSedeId;
    }

    public void setCntPrestadorSedeId(Integer cntPrestadorSedeId) {
        this.cntPrestadorSedeId = cntPrestadorSedeId;
    }

    public Integer getMaeTipoAnexoId() {
        return maeTipoAnexoId;
    }

    public void setMaeTipoAnexoId(Integer maeTipoAnexoId) {
        this.maeTipoAnexoId = maeTipoAnexoId;
    }

    public String getMaeTipoAnexoCodigo() {
        return maeTipoAnexoCodigo;
    }

    public void setMaeTipoAnexoCodigo(String maeTipoAnexoCodigo) {
        this.maeTipoAnexoCodigo = maeTipoAnexoCodigo;
    }

    public String getMaeTipoAnexoValor() {
        return maeTipoAnexoValor;
    }

    public void setMaeTipoAnexoValor(String maeTipoAnexoValor) {
        this.maeTipoAnexoValor = maeTipoAnexoValor;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
    
    public TuSeguimiento getTuSeguimientosId() {
        return tuSeguimientosId;
    }

    public void setTuSeguimientosId(TuSeguimiento tuSeguimientosId) {
        this.tuSeguimientosId = tuSeguimientosId;
    }

    public TuTutelaEstado getTuTutelaEstadosId() {
        return tuTutelaEstadosId;
    }

    public void setTuTutelaEstadosId(TuTutelaEstado tuTutelaEstadosId) {
        this.tuTutelaEstadosId = tuTutelaEstadosId;
    }

    public TuTutelaItem getTuTutelaItemsId() {
        return tuTutelaItemsId;
    }

    public void setTuTutelaItemsId(TuTutelaItem tuTutelaItemsId) {
        this.tuTutelaItemsId = tuTutelaItemsId;
    }
    
    
}
