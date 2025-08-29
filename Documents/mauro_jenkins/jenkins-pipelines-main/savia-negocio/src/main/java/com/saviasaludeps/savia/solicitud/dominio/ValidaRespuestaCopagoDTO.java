package com.saviasaludeps.savia.solicitud.dominio;

import java.io.Serializable;

public class ValidaRespuestaCopagoDTO implements Serializable {

    private final static int CODIGO_OK = 0;
    private final static int CODIGO_ERROR = 1;
    private final static int CODIGO_ERROR_MYSQL = 99;

    private String valor;
    private double codigo;
    private double codigo2;
    private double codigo3;
    private double codigo4;
    private double codigo5;
    private double codigo6;
    private double codigo7;
    private String motivoExentoCobro;

    public double getCodigo() {
        return codigo;
    }

    public void setCodigo(double codigo) {
        this.codigo = codigo;
    }

    public double getCodigo2() {
        return codigo2;
    }

    public void setCodigo2(double codigo2) {
        this.codigo2 = codigo2;
    }

    public double getCodigo3() {
        return codigo3;
    }

    public void setCodigo3(double codigo3) {
        this.codigo3 = codigo3;
    }

    public double getCodigo4() {
        return codigo4;
    }

    public void setCodigo4(double codigo4) {
        this.codigo4 = codigo4;
    }

    public double getCodigo5() {
        return codigo5;
    }

    public void setCodigo5(double codigo5) {
        this.codigo5 = codigo5;
    }

    public double getCodigo6() {
        return codigo6;
    }

    public void setCodigo6(double codigo6) {
        this.codigo6 = codigo6;
    }

    public double getCodigo7() {
        return codigo7;
    }

    public void setCodigo7(double codigo7) {
        this.codigo7 = codigo7;
    }

    public String getMotivoExentoCobro() {
        return motivoExentoCobro;
    }

    public void setMotivoExentoCobro(String motivoExentoCobro) {
        this.motivoExentoCobro = motivoExentoCobro;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "ValidaRespuestaCopagoDTO{" + "codigo=" + codigo + ", codigo2=" + codigo2 + ", codigo3=" + codigo3 + ", codigo4=" + codigo4 + ", codigo5=" + codigo5 + ", codigo6=" + codigo6 + '}';
    }

}
