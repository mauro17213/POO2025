package com.saviasaludeps.savia.dominio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

public class CmFeRipsCargaContenido extends Auditoria {

    private Integer id;
    private int tipo;
    private String json;
    private String xml;
    private String cuv;
    private String cuvJson;
    private CmFeRipsCarga cmFeRipsCarga;
    
    public static final int TIPO_XML  = 0;
    public static final int TIPO_JSON = 1;
    public static final int TIPO_TXT  = 2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getCuv() {
        return cuv;
    }

    public void setCuv(String cuv) {
        this.cuv = cuv;
    }

    public CmFeRipsCarga getCmFeRipsCarga() {
        return cmFeRipsCarga;
    }

    public void setCmFeRipsCarga(CmFeRipsCarga cmFeRipsCarga) {
        this.cmFeRipsCarga = cmFeRipsCarga;
    }

    public String getCuvJson() {
        return cuvJson;
    }

    public void setCuvJson(String cuvJson) {
        this.cuvJson = cuvJson;
    }

    @Override
    public String toString() {
        return "CmFeRipsCargaContenido{" + "id=" + id + ", tipo=" + tipo + ", json=" + json + ", xml=" + xml + ", cuv=" + cuv + '}';
    }
    
}
