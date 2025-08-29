/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.aseguramiento;

import com.google.gson.annotations.SerializedName;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jjmosquera
 */
public class ConsumoAfiliadoDTO extends Auditoria {

    @SerializedName("id")
    private Integer id;
    @SerializedName("nut")
    public String nut;
    @SerializedName("fechaHoraTransaccion")
    private String fechaHoraTransaccion;
    @SerializedName("cantidadRegistros")
    private int cantidadRegistros;
    
    @SerializedName("afiliadoNovedades")
    private AfiliadoNovedadesDTO afiliadoNovedades;

    public List<String> errores = new ArrayList();
    public List<String> valoresNoPermitidosTelefonoFijo = new ArrayList();

    public List<String> getErrores() {
        return errores;
    }

    public String getErroresStr() {
        String strError = "";
        for (String str : errores) {
            String cambio = str.replace(',', '|');
            strError += cambio + ". ";
        }
        return strError;
    }

    public boolean isError() {
        return (!this.errores.isEmpty());
    }

    public void setErrores(List<String> errores) {
        this.errores = errores;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNut() {
        return nut;
    }

    public void setNut(String nut) {
        this.nut = nut;
    }

    public String getFechaHoraTransaccion() {
        return fechaHoraTransaccion;
    }

    public void setFechaHoraTransaccion(String fechaHoraTransaccion) {
        this.fechaHoraTransaccion = fechaHoraTransaccion;
    }

    public int getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(int cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public AfiliadoNovedadesDTO getAfiliadoNovedades() {
        return afiliadoNovedades;
    }

    public void setAfiliadoNovedades(AfiliadoNovedadesDTO afiliadoNovedades) {
        this.afiliadoNovedades = afiliadoNovedades;
    }
}
