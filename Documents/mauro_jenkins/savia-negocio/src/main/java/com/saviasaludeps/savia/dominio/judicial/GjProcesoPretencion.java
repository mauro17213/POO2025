/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.judicial;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

public class GjProcesoPretencion extends Auditoria {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private int maePretencionId;
    private String maePretencionCodigo;
    private String maePretencionValor;

    private GjProceso gjProcesosId;

    public GjProcesoPretencion() {
    }

    public GjProcesoPretencion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaePretencionId() {
        return maePretencionId;
    }

    public void setMaePretencionId(int maePretencionId) {
        this.maePretencionId = maePretencionId;
    }

    public String getMaePretencionCodigo() {
        return maePretencionCodigo;
    }

    public void setMaePretencionCodigo(String maePretencionCodigo) {
        this.maePretencionCodigo = maePretencionCodigo;
    }

    public String getMaePretencionValor() {
        return maePretencionValor;
    }

    public void setMaePretencionValor(String maePretencionValor) {
        this.maePretencionValor = maePretencionValor;
    }

    public GjProceso getGjProcesosId() {
        return gjProcesosId;
    }

    public void setGjProcesosId(GjProceso gjProcesosId) {
        this.gjProcesosId = gjProcesosId;
    }

    @Override
    public String toString() {
        return "GjProcesoPretencion{" + "id=" + id + ", maePretencionId=" + maePretencionId + ", maePretencionCodigo=" + maePretencionCodigo + ", maePretencionValor=" + maePretencionValor + ", gjProcesosId=" + gjProcesosId + '}';
    }

}
