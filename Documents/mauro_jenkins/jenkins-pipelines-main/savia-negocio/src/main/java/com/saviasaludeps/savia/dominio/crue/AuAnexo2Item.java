/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.crue;

import com.saviasaludeps.savia.dominio.autorizacion.AuAnexo4Item;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.List;

/**
 *
 * @author AlexanderDiaz
 */
public class AuAnexo2Item extends Auditoria {
    private Integer id;
    private String maTecnologiaCodigo;
    private int maTecnologiaId;
    private String maTecnologiaValor;
    private int cantidadSolicitada;
    private Integer maServicioSolicitadoId;
    private String maServicioSolicitadoCodigo;
    private String maServicioSolicitadoValor;
    private List<AuAnexo4Item> auAnexo4ItemsList;
    private AuAnexo2 auAnexos2Id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public int getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(int maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
    }

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(int cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public Integer getMaServicioSolicitadoId() {
        return maServicioSolicitadoId;
    }

    public void setMaServicioSolicitadoId(Integer maServicioSolicitadoId) {
        this.maServicioSolicitadoId = maServicioSolicitadoId;
    }

    public String getMaServicioSolicitadoCodigo() {
        return maServicioSolicitadoCodigo;
    }

    public void setMaServicioSolicitadoCodigo(String maServicioSolicitadoCodigo) {
        this.maServicioSolicitadoCodigo = maServicioSolicitadoCodigo;
    }

    public String getMaServicioSolicitadoValor() {
        return maServicioSolicitadoValor;
    }

    public void setMaServicioSolicitadoValor(String maServicioSolicitadoValor) {
        this.maServicioSolicitadoValor = maServicioSolicitadoValor;
    }

    public List<AuAnexo4Item> getAuAnexo4ItemsList() {
        return auAnexo4ItemsList;
    }

    public void setAuAnexo4ItemsList(List<AuAnexo4Item> auAnexo4ItemsList) {
        this.auAnexo4ItemsList = auAnexo4ItemsList;
    }

    public AuAnexo2 getAuAnexos2Id() {
        return auAnexos2Id;
    }

    public void setAuAnexos2Id(AuAnexo2 auAnexos2Id) {
        this.auAnexos2Id = auAnexos2Id;
    }

    @Override
    public String toString() {
        return "AuAnexo2Item{" + "id=" + id + ", maTecnologiaCodigo=" + maTecnologiaCodigo + ", maTecnologiaId=" + maTecnologiaId + ", maTecnologiaValor=" + maTecnologiaValor + ", cantidadSolicitada=" + cantidadSolicitada + ", maServicioSolicitadoId=" + maServicioSolicitadoId + ", maServicioSolicitadoCodigo=" + maServicioSolicitadoCodigo + ", maServicioSolicitadoValor=" + maServicioSolicitadoValor + ", auAnexo4ItemsList=" + auAnexo4ItemsList + ", auAnexos2Id=" + auAnexos2Id + '}';
    }
    
}
