/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.auditoria.concurrente;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author pavacca
 */
public class AucHospitalizacionHistorico extends Auditoria{
    
    private Integer id;
    private String tostringHospitalizacion;
    private AucHospitalizacion aucHospitalizacionesId;
    
    public AucHospitalizacionHistorico(){
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTostringHospitalizacion() {
        return tostringHospitalizacion;
    }

    public void setTostringHospitalizacion(String tostringHospitalizacion) {
        this.tostringHospitalizacion = tostringHospitalizacion;
    }

    public AucHospitalizacion getAucHospitalizacionesId() {
        return aucHospitalizacionesId;
    }

    public void setAucHospitalizacionesId(AucHospitalizacion aucHospitalizacionesId) {
        this.aucHospitalizacionesId = aucHospitalizacionesId;
    }

    @Override
    public String toString() {
        return "AucHospitalizacionHistorico{" + "id=" + id + ", tostringHospitalizacion=" + tostringHospitalizacion + ", aucHospitalizacionesId=" + aucHospitalizacionesId + '}';
    }
    
    public double toStringRevertValorEstancia(String str) {
        double valorEstanciaRetorno = 0.0;
        String strNew = str.replace("AucEgreso{", "").replace("}", "").trim();
        for (String arreglo : strNew.split(",")) {
            if (arreglo.split("=")[0].trim().equals("valorEstancia")) {
                valorEstanciaRetorno = Double.valueOf(arreglo.split("=")[1].trim());
                break;
            }
        }
        return valorEstanciaRetorno;
    }
}
