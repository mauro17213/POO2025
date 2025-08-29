/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.administracion.Maestro;
import com.saviasaludeps.savia.dominio.cuentamedica.conciliacion.CmDetalle;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Optional;

public class CmAuditoriaMotivoGlosa extends Auditoria implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public static final int TIPOLOGIA_NORMAL           = 0;
    public static final int TIPOLOGIA_GLOSA_AUTOMATICA = 1;
    public static final int TIPOLOGIA_ADVERTENCIA      = 2;

    private Integer id;
    private int maeMotivoId;
    private String maeMotivoCodigo;
    private String maeMotivoValor;
    private int maeMotivoEspecificoId;
    private int tipologia;
    private String maeMotivoEspecificoCodigo;
    private String maeMotivoEspecificoValor;
        
    private Integer maeMotivoGlosaAplicacionId;
    private String maeMotivoGlosaAplicacionCodigo;
    private String maeMotivoGlosaAplicacionValor;
    private String maeMotivoGlosaAplicacionDescripcion;
    
    private Integer posInsertar;
    private CmDetalle cmDetalle;
      
    private Maestro maestroMotivo;
    private Maestro maestroMotivoEspecifico;
    private Maestro maestroMotivoAplicacion;
    private BigDecimal porcentaje;
    private BigDecimal valorMotivo;
    private BigDecimal valorMotivoMaximo;
    private String observacion;
    private String motivoAplicacionCorto;
    private String motivoAplicacionCompleto;
    
    public static final int TAMANIO_OBSERVACION = 20;
    public static final int TAMANIO_MOTIVO_APLICACION = 80;
    
    public boolean esValorUnificado;

    public CmAuditoriaMotivoGlosa() {
    }

    public CmAuditoriaMotivoGlosa(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPosInsertar() {
        return posInsertar;
    }

    public void setPosInsertar(Integer posInsertar) {
        this.posInsertar = posInsertar;
    }

    public int getMaeMotivoId() {
        return maeMotivoId;
    }

    public void setMaeMotivoId(int maeMotivoId) {
        this.maeMotivoId = maeMotivoId;
    }

    public String getMaeMotivoCodigo() {
        return maeMotivoCodigo;
    }

    public void setMaeMotivoCodigo(String maeMotivoCodigo) {
        this.maeMotivoCodigo = maeMotivoCodigo;
    }

    public String getMaeMotivoValor() {
        return maeMotivoValor;
    }

    public void setMaeMotivoValor(String maeMotivoValor) {
        this.maeMotivoValor = maeMotivoValor;
    }

    public int getMaeMotivoEspecificoId() {
        return maeMotivoEspecificoId;
    }

    public void setMaeMotivoEspecificoId(int maeMotivoEspecificoId) {
        this.maeMotivoEspecificoId = maeMotivoEspecificoId;
    }

    public String getMaeMotivoEspecificoCodigo() {
        return maeMotivoEspecificoCodigo;
    }

    public void setMaeMotivoEspecificoCodigo(String maeMotivoEspecificoCodigo) {
        this.maeMotivoEspecificoCodigo = maeMotivoEspecificoCodigo;
    }

    public String getMaeMotivoEspecificoValor() {
        return maeMotivoEspecificoValor;
    }

    public void setMaeMotivoEspecificoValor(String maeMotivoEspecificoValor) {
        this.maeMotivoEspecificoValor = maeMotivoEspecificoValor;
    }

    public Integer getMaeMotivoGlosaAplicacionId() {
        return maeMotivoGlosaAplicacionId;
    }

    public void setMaeMotivoGlosaAplicacionId(Integer maeMotivoGlosaAplicacionId) {
        this.maeMotivoGlosaAplicacionId = maeMotivoGlosaAplicacionId;
    }

    public String getMaeMotivoGlosaAplicacionCodigo() {
        return maeMotivoGlosaAplicacionCodigo;
    }

    public void setMaeMotivoGlosaAplicacionCodigo(String maeMotivoGlosaAplicacionCodigo) {
        this.maeMotivoGlosaAplicacionCodigo = maeMotivoGlosaAplicacionCodigo;
    }

    public String getMaeMotivoGlosaAplicacionValor() {
        return maeMotivoGlosaAplicacionValor;
    }

    public void setMaeMotivoGlosaAplicacionValor(String maeMotivoGlosaAplicacionValor) {
        this.maeMotivoGlosaAplicacionValor = maeMotivoGlosaAplicacionValor;
    }

    public String getMaeMotivoGlosaAplicacionDescripcion() {
        return maeMotivoGlosaAplicacionDescripcion;
    }

    public void setMaeMotivoGlosaAplicacionDescripcion(String maeMotivoGlosaAplicacionDescripcion) {
        this.maeMotivoGlosaAplicacionDescripcion = maeMotivoGlosaAplicacionDescripcion;
    }

    public CmDetalle getCmDetalle() {
        if(cmDetalle == null){
            cmDetalle = new CmDetalle();
        }
        return cmDetalle;
    }

    public void setCmDetalle(CmDetalle cmDetalle) {
        this.cmDetalle = cmDetalle;
    }

    public Maestro getMaestroMotivo() {
        if(maestroMotivo == null){
            maestroMotivo = new Maestro();
        }
        return maestroMotivo;
    }

    public void setMaestroMotivo(Maestro maestroMotivo) {
        this.maestroMotivo = maestroMotivo;
    }

    public Maestro getMaestroMotivoEspecifico() {
        if(maestroMotivoEspecifico == null){
            maestroMotivoEspecifico = new Maestro();
        }
        return maestroMotivoEspecifico;
    }

    public void setMaestroMotivoEspecifico(Maestro maestroMotivoEspecifico) {
        this.maestroMotivoEspecifico = maestroMotivoEspecifico;
    }

    public Maestro getMaestroMotivoAplicacion() {
        if(maestroMotivoAplicacion == null){
            maestroMotivoAplicacion = new Maestro();
        }
        return maestroMotivoAplicacion;
    }

    public void setMaestroMotivoAplicacion(Maestro maestroMotivoAplicacion) {
        this.maestroMotivoAplicacion = maestroMotivoAplicacion;
    }
      
    public void castMaestroMotivo(Maestro motivo){
        this.maeMotivoId = motivo.getId();
        this.maeMotivoCodigo = motivo.getValor();
        this.maeMotivoValor = motivo.getNombre();
    }
    
    public void castMaestroMotivoEspecifico(Maestro motivoEspecifico){
        this.maeMotivoEspecificoId = motivoEspecifico.getId();
        this.maeMotivoEspecificoCodigo = motivoEspecifico.getValor();
        this.maeMotivoEspecificoValor = motivoEspecifico.getNombre();
    }
    
    public void castMaestroMotivoAplicacion(Maestro motivoAplicacion){
        this.maeMotivoGlosaAplicacionId      = motivoAplicacion.getId();
        this.maeMotivoGlosaAplicacionCodigo  = motivoAplicacion.getValor();
        this.maeMotivoGlosaAplicacionValor = motivoAplicacion.getNombre();
        this.maeMotivoGlosaAplicacionDescripcion = motivoAplicacion.getDescripcion();
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public BigDecimal getValorMotivo() {
        return valorMotivo;
    }

    public void setValorMotivo(BigDecimal valorMotivo) {
        this.valorMotivo = valorMotivo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    public String getObservacionCorto() {
        if (observacion != null && observacion.length() >= TAMANIO_OBSERVACION) {
            return observacion.substring(0, TAMANIO_OBSERVACION) + "...   ";
        } else {
            return observacion;
        }
        
    }
     
    public String getMotivoAplicacionCorto() {
        String motivoConcatenado = getMotivoAplicacionCompleto();
        if ( motivoConcatenado.length() >= TAMANIO_MOTIVO_APLICACION ) {
            return motivoConcatenado.substring(0, TAMANIO_MOTIVO_APLICACION) + "...  ";
        } else {
            return motivoConcatenado;
        }
    }
    
    public String getMotivoAplicacionCompleto() {
        return  Optional.ofNullable(getMaestroMotivoAplicacion().getValor()).orElse("") + " - "+ 
                Optional.ofNullable(getMaestroMotivoAplicacion().getDescripcion()).orElse("");
    }
    
    public boolean getEsMotivoAplicacionExtenso(){
     return getMotivoAplicacionCompleto().length() > TAMANIO_MOTIVO_APLICACION;
    }

    public boolean isEsValorUnificado() {
        return esValorUnificado;
    }

    public void setEsValorUnificado(boolean esValorUnificado) {
        this.esValorUnificado = esValorUnificado;
    }
    
    public BigDecimal getValorMotivoMaximo() {
        return valorMotivoMaximo;
    }

    public void setValorMotivoMaximo(BigDecimal valorMotivoMaximo) {
        this.valorMotivoMaximo = valorMotivoMaximo;
    }

    public int getTipologia() {
        return tipologia;
    }

    public void setTipologia(int tipologia) {
        this.tipologia = tipologia;
    }

    public String getTipologiaStr() {
        return CmAuditoriaMotivoGlosa.getTipologiaStr(getTipologia());
    }
    
    public static String getTipologiaStr(int tipologia) {
        String str;
        switch (tipologia) {
            case TIPOLOGIA_NORMAL:
                str = "Normal";
                break;
            case TIPOLOGIA_GLOSA_AUTOMATICA:
                str = "Glosa Automática";
                break;
            case TIPOLOGIA_ADVERTENCIA:
                str = "Advertencia";
                break; 
            default:
                str = "";
                break;
        }
        return str;
    }

    
    @Override
    public String toString() {
        return "CmAuditoriaMotivoGlosa{" + "id=" + id + ", maeMotivoId=" + maeMotivoId + ", maeMotivoCodigo=" + maeMotivoCodigo + ", maeMotivoValor=" + maeMotivoValor + ", maeMotivoEspecificoId=" + maeMotivoEspecificoId + ", maeMotivoEspecificoCodigo=" + maeMotivoEspecificoCodigo + ", maeMotivoEspecificoValor=" + maeMotivoEspecificoValor + ", maeMotivoGlosaAplicacionValor = " +maeMotivoGlosaAplicacionValor+ ", mae_motivo_glosa_aplicacion_codigo="+ maeMotivoGlosaAplicacionCodigo+ ", cmDetalle Id =" + getCmDetalle().getId() + ", porcentaje=" + porcentaje + ", valorMotivo=" + valorMotivo + ", observacion =" + observacion +  ", usuario Crea =" + getUsuarioCrea() +'}';
    }
  
   
}
