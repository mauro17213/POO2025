/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.judicial;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author rpalacios
 */
public class GjProcesoAdjunto extends Auditoria {
      
    private Integer id;
    private String nombreArchivo;
    private String ruta;
    private String archivo;
    private int maeTipoId;
    private String maeTipoCodigo;
    private String maeTipoValor;

    private GjProcesoHistorico gjProcesoHistoricosId;
    private GjProceso gjProcesosId;

    public GjProcesoAdjunto() {
    }

    public GjProcesoAdjunto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public int getMaeTipoId() {
        return maeTipoId;
    }

    public void setMaeTipoId(int maeTipoId) {
        this.maeTipoId = maeTipoId;
    }

    public String getMaeTipoCodigo() {
        return maeTipoCodigo;
    }

    public void setMaeTipoCodigo(String maeTipoCodigo) {
        this.maeTipoCodigo = maeTipoCodigo;
    }

    public String getMaeTipoValor() {
        return maeTipoValor;
    }

    public void setMaeTipoValor(String maeTipoValor) {
        this.maeTipoValor = maeTipoValor;
    }

    public GjProcesoHistorico getGjProcesoHistoricosId() {
        return gjProcesoHistoricosId;
    }

    public void setGjProcesoHistoricosId(GjProcesoHistorico gjProcesoHistoricosId) {
        this.gjProcesoHistoricosId = gjProcesoHistoricosId;
    }

    public GjProceso getGjProcesosId() {
        return gjProcesosId;
    }

    public void setGjProcesosId(GjProceso gjProcesosId) {
        this.gjProcesosId = gjProcesosId;
    }

    @Override
    public String toString() {
        return "GjProcesoAdjunto{" + "id=" + id + ", nombreArchivo=" + nombreArchivo + ", ruta=" + ruta + ", archivo=" + archivo + ", maeTipoId=" + maeTipoId + ", maeTipoCodigo=" + maeTipoCodigo + ", maeTipoValor=" + maeTipoValor + ", gjProcesoHistoricosId=" + gjProcesoHistoricosId + ", gjProcesosId=" + gjProcesosId + '}';
    }

}
