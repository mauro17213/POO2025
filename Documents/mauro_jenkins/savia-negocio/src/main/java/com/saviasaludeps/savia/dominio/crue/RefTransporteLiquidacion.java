/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.crue;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author Jhonatan Jimenez
 */
public class RefTransporteLiquidacion  extends Auditoria {
    
    private Integer id;
    private int maeTransporteLiquidacionId;
    private String maeTransporteLiquidacionCodigo;
    private String maeTransporteLiquidacionValor;
    private RefTransporte refTransporte;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeTransporteLiquidacionId() {
        return maeTransporteLiquidacionId;
    }

    public void setMaeTransporteLiquidacionId(int maeTransporteLiquidacionId) {
        this.maeTransporteLiquidacionId = maeTransporteLiquidacionId;
    }

    public String getMaeTransporteLiquidacionCodigo() {
        return maeTransporteLiquidacionCodigo;
    }

    public void setMaeTransporteLiquidacionCodigo(String maeTransporteLiquidacionCodigo) {
        this.maeTransporteLiquidacionCodigo = maeTransporteLiquidacionCodigo;
    }

    public String getMaeTransporteLiquidacionValor() {
        return maeTransporteLiquidacionValor;
    }

    public void setMaeTransporteLiquidacionValor(String maeTransporteLiquidacionValor) {
        this.maeTransporteLiquidacionValor = maeTransporteLiquidacionValor;
    }

    public RefTransporte getRefTransporte() {
        return refTransporte;
    }

    public void setRefTransporte(RefTransporte refTransporte) {
        this.refTransporte = refTransporte;
    }

    @Override
    public String toString() {
        return "RefTransporteLiquidacion{" + "id=" + id + ", maeTransporteLiquidacionId=" + maeTransporteLiquidacionId + ", maeTransporteLiquidacionCodigo=" + maeTransporteLiquidacionCodigo + ", maeTransporteLiquidacionValor=" + maeTransporteLiquidacionValor + ", refTransporte=" + refTransporte + '}';
    }
}
