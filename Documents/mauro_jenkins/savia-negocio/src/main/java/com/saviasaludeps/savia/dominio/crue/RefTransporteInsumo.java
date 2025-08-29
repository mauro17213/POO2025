/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.crue;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author Jaime Andres Olarte
 */
public class RefTransporteInsumo extends Auditoria {
    
    private Integer id;
    private int maInsumoId;
    private String maInsumoCodigo;
    private String maInsumoValor;
    private RefTransporte refTransporte;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaInsumoId() {
        return maInsumoId;
    }

    public void setMaInsumoId(int maInsumoId) {
        this.maInsumoId = maInsumoId;
    }

    public String getMaInsumoCodigo() {
        return maInsumoCodigo;
    }

    public void setMaInsumoCodigo(String maInsumoCodigo) {
        this.maInsumoCodigo = maInsumoCodigo;
    }

    public String getMaInsumoValor() {
        return maInsumoValor;
    }

    public void setMaInsumoValor(String maInsumoValor) {
        this.maInsumoValor = maInsumoValor;
    }

    public RefTransporte getRefTransporte() {
        return refTransporte;
    }

    public void setRefTransporte(RefTransporte refTransporte) {
        this.refTransporte = refTransporte;
    }     

    @Override
    public String toString() {
        return "RefTransporteInsumo{" + "id=" + id + ", maInsumoId=" + maInsumoId + ", maInsumoCodigo=" + maInsumoCodigo + ", maInsumoValor=" + maInsumoValor + '}';
    }
   
}
