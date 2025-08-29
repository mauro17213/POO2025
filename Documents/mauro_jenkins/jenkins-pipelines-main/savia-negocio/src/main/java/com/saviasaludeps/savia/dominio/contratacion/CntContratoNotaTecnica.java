/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.contratacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jyperez
 */
public class CntContratoNotaTecnica extends Auditoria {
    
    private Integer id;
    private int maeTipoTecnologiaId;
    private String maeTipoTecnologiaCodigo;
    private String maeTipoTecnologiaValor;
    private Integer tipoTecnologia;
    private int maTecnologiaId;
    private String maTecnologiaCodigo;
    private String maTecnologiaValor;
    private BigDecimal costoPromedio;
    private Integer tipoFrecuencia;
    private BigDecimal frecuenciaUso;
    private int cantidadAfiliados;
    private int maeAmbitoId;
    private String maeAmbitoCodigo;
    private String maeAmbitoValor;
    private String observacion;
    private Date fechaInicio;
    private Date fechaFin;
    
    //20219-09-29 jyperez campos para la carga masiva
    private String errorCarga;
    private boolean actualizar;
    private String registroArchivo;
    
    public final static int TIPO_TECNOLOGIA_TECNOLOGIA = 1;
    public final static int TIPO_TECNOLOGIA_MEDICAMENTO = 2;
    public final static int TIPO_TECNOLOGIA_INSUMO = 3;
    public final static int TIPO_TECNOLOGIA_PAQUETE = 4;
    
    public final static int TIPO_FRECUENCIA_ANIO = 1;
    public final static int TIPO_FRECUENCIA_MES = 2;
    public final static int TIPO_FRECUENCIA_DIA = 3;
    
    
    private CntContrato cntContrato;

    public CntContratoNotaTecnica() {
    }

    public CntContratoNotaTecnica(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the maeTipoTecnologiaId
     */
    public int getMaeTipoTecnologiaId() {
        return maeTipoTecnologiaId;
    }

    /**
     * @param maeTipoTecnologiaId the maeTipoTecnologiaId to set
     */
    public void setMaeTipoTecnologiaId(int maeTipoTecnologiaId) {
        this.maeTipoTecnologiaId = maeTipoTecnologiaId;
    }

    /**
     * @return the maeTipoTecnologiaCodigo
     */
    public String getMaeTipoTecnologiaCodigo() {
        return maeTipoTecnologiaCodigo;
    }

    /**
     * @param maeTipoTecnologiaCodigo the maeTipoTecnologiaCodigo to set
     */
    public void setMaeTipoTecnologiaCodigo(String maeTipoTecnologiaCodigo) {
        this.maeTipoTecnologiaCodigo = maeTipoTecnologiaCodigo;
    }

    /**
     * @return the maeTipoTecnologiaValor
     */
    public String getMaeTipoTecnologiaValor() {
        return maeTipoTecnologiaValor;
    }

    /**
     * @param maeTipoTecnologiaValor the maeTipoTecnologiaValor to set
     */
    public void setMaeTipoTecnologiaValor(String maeTipoTecnologiaValor) {
        this.maeTipoTecnologiaValor = maeTipoTecnologiaValor;
    }

    /**
     * @return the tipoTecnologia
     */
    public Integer getTipoTecnologia() {
        return tipoTecnologia;
    }
    
    /**
     * 
     * @return nombre del tipo de tecnología
     */
    public String getTipoTecnologiaStr() {
        String mensaje = "";
        if (tipoTecnologia!= null) {
            switch(tipoTecnologia) {
                case TIPO_TECNOLOGIA_INSUMO:
                    mensaje = "Insumo";
                break;
                case TIPO_TECNOLOGIA_MEDICAMENTO:
                    mensaje = "Medicamento";
                break;
                case TIPO_TECNOLOGIA_PAQUETE:
                    mensaje = "Paquete";
                break;
                case TIPO_TECNOLOGIA_TECNOLOGIA:
                    mensaje = "Tecnología";
                break;
            }
        }
        return mensaje;
    }

    /**
     * @param tipoTecnologia the tipoTecnologia to set
     */
    public void setTipoTecnologia(Integer tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    /**
     * @return the maTecnologiaId
     */
    public int getMaTecnologiaId() {
        return maTecnologiaId;
    }

    /**
     * @param maTecnologiaId the maTecnologiaId to set
     */
    public void setMaTecnologiaId(int maTecnologiaId) {
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

    /**
     * @return the costoPromedio
     */
    public BigDecimal getCostoPromedio() {
        return costoPromedio;
    }

    /**
     * @param costoPromedio the costoPromedio to set
     */
    public void setCostoPromedio(BigDecimal costoPromedio) {
        this.costoPromedio = costoPromedio;
    }

    /**
     * @return the tipoFrecuencia
     */
    public Integer getTipoFrecuencia() {
        return tipoFrecuencia;
    }
    
    /**
     * retorna el nombre del tipo de frecuencia
     * @return 
     */
    public String getTipoFrecuenciaStr() {
        String mensaje = "";
        if (tipoFrecuencia != null) {
            switch(tipoFrecuencia) {
                case TIPO_FRECUENCIA_ANIO:
                    mensaje = "Año";
                break;
                case TIPO_FRECUENCIA_MES:
                    mensaje = "Mes";
                break;
                case TIPO_FRECUENCIA_DIA:
                    mensaje = "Día";
                break;
            }
        }
        return mensaje;
    }

    /**
     * @param tipoFrecuencia the tipoFrecuencia to set
     */
    public void setTipoFrecuencia(Integer tipoFrecuencia) {
        this.tipoFrecuencia = tipoFrecuencia;
    }

    /**
     * @return the frecuenciaUso
     */
    public BigDecimal getFrecuenciaUso() {
        return frecuenciaUso;
    }

    /**
     * @param frecuenciaUso the frecuenciaUso to set
     */
    public void setFrecuenciaUso(BigDecimal frecuenciaUso) {
        this.frecuenciaUso = frecuenciaUso;
    }

    /**
     * @return the cantidadAfiliados
     */
    public int getCantidadAfiliados() {
        return cantidadAfiliados;
    }

    /**
     * @param cantidadAfiliados the cantidadAfiliados to set
     */
    public void setCantidadAfiliados(int cantidadAfiliados) {
        this.cantidadAfiliados = cantidadAfiliados;
    }

    /**
     * @return the maeAmbitoId
     */
    public int getMaeAmbitoId() {
        return maeAmbitoId;
    }

    /**
     * @param maeAmbitoId the maeAmbitoId to set
     */
    public void setMaeAmbitoId(int maeAmbitoId) {
        this.maeAmbitoId = maeAmbitoId;     
    }

    /**
     * @return the maeAmbitoCodigo
     */
    public String getMaeAmbitoCodigo() {
        return maeAmbitoCodigo;
    }

    /**
     * @param maeAmbitoCodigo the maeAmbitoCodigo to set
     */
    public void setMaeAmbitoCodigo(String maeAmbitoCodigo) {
        this.maeAmbitoCodigo = maeAmbitoCodigo;
    }

    /**
     * @return the maeAmbitoValor
     */
    public String getMaeAmbitoValor() {
        return maeAmbitoValor;
    }

    /**
     * @param maeAmbitoValor the maeAmbitoValor to set
     */
    public void setMaeAmbitoValor(String maeAmbitoValor) {
        this.maeAmbitoValor = maeAmbitoValor;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the cntContrato
     */
    public CntContrato getCntContrato() {
        return cntContrato;
    }

    /**
     * @param cntContrato the cntContrato to set
     */
    public void setCntContrato(CntContrato cntContrato) {
        this.cntContrato = cntContrato;
    }

    @Override
    public String toString() {
        return "CntContratoNotaTecnica{" + "id=" + id + ", maeTipoTecnologiaId=" + maeTipoTecnologiaId + ", maeTipoTecnologiaCodigo=" + maeTipoTecnologiaCodigo + ", maeTipoTecnologiaValor=" + maeTipoTecnologiaValor + ", tipoTecnologia=" + tipoTecnologia + ", maTecnologiaId=" + maTecnologiaId + ", maTecnologiaCodigo=" + maTecnologiaCodigo + ", maTecnologiaValor=" + maTecnologiaValor + ", costoPromedio=" + costoPromedio + ", tipoFrecuencia=" + tipoFrecuencia + ", frecuenciaUso=" + frecuenciaUso + ", cantidadAfiliados=" + cantidadAfiliados + ", maeAmbitoId=" + maeAmbitoId + ", maeAmbitoCodigo=" + maeAmbitoCodigo + ", maeAmbitoValor=" + maeAmbitoValor + ", observacion=" + observacion + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", cntContrato=" + cntContrato + '}';
    }
    
    public String generarTextoFormatoCargaMasiva(int consecutivo, int actualizar) {
        String mensaje = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        mensaje = consecutivo + "," + actualizar + ",";
        mensaje = mensaje + id + ",";
        if (cntContrato != null) {
            mensaje = mensaje + cntContrato.getContrato() + ",";
        } else {
            mensaje = mensaje + ",";
        }
        mensaje = mensaje + tipoTecnologia + ",";
        mensaje = mensaje + maTecnologiaCodigo + ",";
        mensaje = mensaje + validarBigDecimalNulo(costoPromedio) + ",";
        mensaje = mensaje + validarBigDecimalNulo(frecuenciaUso) + ",";
        mensaje = mensaje + tipoFrecuencia + ",";
        mensaje = mensaje + cantidadAfiliados + ",";
        mensaje = mensaje + maeAmbitoCodigo + ",";
        mensaje = mensaje + validarCadenaNula(observacion) + ",";
        mensaje = mensaje + sdf.format(fechaInicio) + ",";
        mensaje = mensaje + sdf.format(fechaFin) + "\n";
        
        return mensaje;
    }
    
    public int valorBanderaEntero(boolean bandera) {
        if (bandera) {
            return 1;
        }
        return 0;
    }

    public String validarCadenaNula(String cadena) {
        if (cadena == null) {
            return "";
        }
        return cadena;
    }

    public String validarEnteroNulo(Integer entero) {
        if (entero == null) {
            return "";
        }
        return entero + "";
    }

    public String validarBigDecimalNulo(BigDecimal valor) {
        if (valor == null) {
            return "";
        }
        return valor + "";
    }

    /**
     * @return the errorCarga
     */
    public String getErrorCarga() {
        return errorCarga;
    }

    /**
     * @param errorCarga the errorCarga to set
     */
    public void setErrorCarga(String errorCarga) {
        this.errorCarga = errorCarga;
    }

    /**
     * @return the actualizar
     */
    public boolean isActualizar() {
        return actualizar;
    }

    /**
     * @param actualizar the actualizar to set
     */
    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

    /**
     * @return the registroArchivo
     */
    public String getRegistroArchivo() {
        return registroArchivo;
    }

    /**
     * @param registroArchivo the registroArchivo to set
     */
    public void setRegistroArchivo(String registroArchivo) {
        this.registroArchivo = registroArchivo;
    }
}
