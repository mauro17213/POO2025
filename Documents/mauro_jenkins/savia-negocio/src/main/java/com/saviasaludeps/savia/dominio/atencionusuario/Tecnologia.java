/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.saviasaludeps.savia.dominio.atencionusuario;

/**
 *
 * @author jeperez
 */
public class Tecnologia {
    
    public static final int TIPO_TECNOLOGIA_TECNOLOGIA = 1;
    public static final int TIPO_TECNOLOGIA_MEDICAMENTO = 2;
    public static final int TIPO_TECNOLOGIA_INSUMO = 3;
    public static final int TIPO_TECNOLOGIA_PAQUETE = 4;
    public static final int TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO = 5;
    
    public static final String TIPO_TECNOLOGIA_TECNOLOGIA_STR = "Tecnología";
    public static final String TIPO_TECNOLOGIA_MEDICAMENTO_STR = "Medicamento";
    public static final String TIPO_TECNOLOGIA_INSUMO_STR = "Insumo";
    public static final String TIPO_TECNOLOGIA_PAQUETE_STR = "Paquete";
    public static final String TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO_STR = "Agrupador Medicamento";
    
    private int id;
    private Short tipoTecnologia;
    private Integer maTecnologiaId;
    private String maTecnologiaCodigo;
    private String maTecnologiaValor;

    public Tecnologia() {
        this.id = 0;
    }
    
    public Tecnologia(int id) {
        this.id = id;
    }
    
    
    public String getTipoTecnologiaStr() {
        String str="";
        if (tipoTecnologia != null) {
            switch (tipoTecnologia) {
                case TIPO_TECNOLOGIA_TECNOLOGIA:
                    str = TIPO_TECNOLOGIA_TECNOLOGIA_STR;
                    break;
                case TIPO_TECNOLOGIA_MEDICAMENTO:
                    str = TIPO_TECNOLOGIA_MEDICAMENTO_STR;
                    break;
                case TIPO_TECNOLOGIA_INSUMO:
                    str = TIPO_TECNOLOGIA_INSUMO_STR;
                    break;
                case TIPO_TECNOLOGIA_PAQUETE:
                    str = TIPO_TECNOLOGIA_PAQUETE_STR;
                    break;
                case TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO:
                    str = TIPO_TECNOLOGIA_AGRUPADOR_MEDICAMENTO_STR;
                    break;
                default:
                    str = "";
                    break;
            }
        }
        return str;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the tipoTecnologia
     */
    public Short getTipoTecnologia() {
        return tipoTecnologia;
    }

    /**
     * @param tipoTecnologia the tipoTecnologia to set
     */
    public void setTipoTecnologia(Short tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    /**
     * @return the maTecnologiaId
     */
    public Integer getMaTecnologiaId() {
        return maTecnologiaId;
    }

    /**
     * @param maTecnologiaId the maTecnologiaId to set
     */
    public void setMaTecnologiaId(Integer maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    /**
     * @return the maTecnologiaCodigo
     */
    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    /**
     * @param maTecnologiaCodigo the maTecnologiaCodigo to set
     */
    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    /**
     * @return the maTecnologiaValor
     */
    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }

    /**
     * @param maTecnologiaValor the maTecnologiaValor to set
     */
    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
    }
    
}
