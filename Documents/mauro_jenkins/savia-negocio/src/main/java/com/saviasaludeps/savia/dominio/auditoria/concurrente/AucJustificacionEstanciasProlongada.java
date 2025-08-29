/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author pavacca
 */
public class AucJustificacionEstanciasProlongada extends Auditoria {
    
    private Integer id; 
    private Integer maeCausaEstanciaProlongadaId;
    private String maeCausaEstanciaProlongadaCodigo;
    private String maeCausaEstanciaProlongadaValor;
    private Integer maePropuestaIntervensionId;
    private String maePropuestaIntervensionCodigo;
    private String maePropuestaIntervensionValor;
    private String resumenClinico;
    private String resumenClinicoCorto;
    private AucHospitalizacion aucHospitalizacionId;
    private int pos;
    
    public static final int TAMANIO_RESUMEN_CLINICO = 20;
    
    public AucJustificacionEstanciasProlongada(){
        
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaeCausaEstanciaProlongadaId() {
        return maeCausaEstanciaProlongadaId;
    }

    public void setMaeCausaEstanciaProlongadaId(Integer maeCausaEstanciaProlongadaId) {
        this.maeCausaEstanciaProlongadaId = maeCausaEstanciaProlongadaId;
    }

    public String getMaeCausaEstanciaProlongadaCodigo() {
        return maeCausaEstanciaProlongadaCodigo;
    }

    public void setMaeCausaEstanciaProlongadaCodigo(String maeCausaEstanciaProlongadaCodigo) {
        this.maeCausaEstanciaProlongadaCodigo = maeCausaEstanciaProlongadaCodigo;
    }

    public String getMaeCausaEstanciaProlongadaValor() {
        return maeCausaEstanciaProlongadaValor;
    }

    public void setMaeCausaEstanciaProlongadaValor(String maeCausaEstanciaProlongadaValor) {
        this.maeCausaEstanciaProlongadaValor = maeCausaEstanciaProlongadaValor;
    }

    public Integer getMaePropuestaIntervensionId() {
        return maePropuestaIntervensionId;
    }

    public void setMaePropuestaIntervensionId(Integer maePropuestaIntervensionId) {
        this.maePropuestaIntervensionId = maePropuestaIntervensionId;
    }

    public String getMaePropuestaIntervensionCodigo() {
        return maePropuestaIntervensionCodigo;
    }

    public void setMaePropuestaIntervensionCodigo(String maePropuestaIntervensionCodigo) {
        this.maePropuestaIntervensionCodigo = maePropuestaIntervensionCodigo;
    }

    public String getMaePropuestaIntervensionValor() {
        return maePropuestaIntervensionValor;
    }

    public void setMaePropuestaIntervensionValor(String maePropuestaIntervensionValor) {
        this.maePropuestaIntervensionValor = maePropuestaIntervensionValor;
    }

    public String getResumenClinico() {
        return resumenClinico;
    }

    public void setResumenClinico(String resumenClinico) {
        this.resumenClinico = resumenClinico;
    }
    
    public String getResumenClinicoCorto() {
        if (getResumenClinico() != null) {
            resumenClinicoCorto = getResumenClinico();
            if (getResumenClinico().length() >= TAMANIO_RESUMEN_CLINICO) {
                return resumenClinico.substring(0, TAMANIO_RESUMEN_CLINICO) + "..";
            } else {
                return resumenClinico;
            }
        }
        return resumenClinico;
    }

    public void setResumenClinicoCorto(String resumenClinicoCorto) {
        this.resumenClinicoCorto = resumenClinicoCorto;
    }
    
    public AucHospitalizacion getAucHospitalizacionId() {
        return aucHospitalizacionId;
    }

    public void setAucHospitalizacionId(AucHospitalizacion aucHospitalizacionId) {
        this.aucHospitalizacionId = aucHospitalizacionId;
    }
    
    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "AucJustificacionEstanciasProlongada{" + "id=" + id + ", maeCausaEstanciaProlongadaId=" + maeCausaEstanciaProlongadaId + ", maeCausaEstanciaProlongadaCodigo=" + maeCausaEstanciaProlongadaCodigo + ", maeCausaEstanciaProlongadaValor=" + maeCausaEstanciaProlongadaValor + ", maePropuestaIntervensionId=" + maePropuestaIntervensionId + ", maePropuestaIntervensionCodigo=" + maePropuestaIntervensionCodigo + ", maePropuestaIntervensionValor=" + maePropuestaIntervensionValor + ", resumenClinico=" + resumenClinico + ", resumenClinicoCorto=" + resumenClinicoCorto + '}';
    }
}
