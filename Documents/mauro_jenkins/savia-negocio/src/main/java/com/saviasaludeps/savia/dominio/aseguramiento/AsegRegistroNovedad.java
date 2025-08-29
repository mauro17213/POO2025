/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author jyperez
 */
public class AsegRegistroNovedad extends Auditoria {

    private Integer id;
    private int idAfiliado;
    private Date fechaNovedad;
    private String valorAnterior;
    private String descripcionValorAnterior;
    private String valorNuevo;
    private String descripcionValorNuevo;
    private Date fechaMarcacion;
    private Integer asegInformesIdMarcacion;
    private String maeEstadoNovedad;
    private String observacion;
    private Integer sincronizado;
    private Integer origenUltimoRegistro;
    private String descripcionOrigenUltimoRegistro;
    private AsegRadicadoNovedad radicadoNovedad;
    private AsegMaNovedad asegMaNovedad;
    private AsegReporteNovedad asegReporteNovedad;
    
    public final static String ORIGEN_ULTIMO_REGISTRO_PANTALLA = "Pantalla Aplicación";
    public final static int ORIGEN_ULTIMO_REGISTRO_ID_PANTALLA = 1;
    public final static String ORIGEN_ULTIMO_REGISTRO_CARGA_MASIVA = "Carga Masiva";
    public final static int ORIGEN_ULTIMO_REGISTRO_ID_CARGA_MASIVA = 2;
    public final static String ORIGEN_ULTIMO_REGISTRO_SERVICIO_WEB = "Servicio Web";
    public final static int ORIGEN_ULTIMO_REGISTRO_ID_SERVICIO_WEB = 3;
    public final static String ORIGEN_ULTIMO_REGISTRO_ANEXO_1 = "Sincronizado desde Anexo1";
    public final static int ORIGEN_ULTIMO_REGISTRO_ID_ANEXO_1 = 4;
   
    
    public AsegRegistroNovedad() {
    }

    public AsegRegistroNovedad(Integer id) {
        this.id = id;
    }

    public AsegRegistroNovedad(Integer id, int idAfiliado, Date fechaNovedad, String maeEstadoNovedad, String usuarioCrea) {
        this.id = id;
        this.idAfiliado = idAfiliado;
        this.fechaNovedad = fechaNovedad;
        this.maeEstadoNovedad = maeEstadoNovedad;
        super.setUsuarioCrea(usuarioCrea);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdAfiliado() {
        return idAfiliado;
    }

    public void setIdAfiliado(int idAfiliado) {
        this.idAfiliado = idAfiliado;
    }

    public Date getFechaNovedad() {
        return fechaNovedad;
    }

    public void setFechaNovedad(Date fechaNovedad) {
        this.fechaNovedad = fechaNovedad;
    }

    public String getValorAnterior() {
        return valorAnterior;
    }

    public void setValorAnterior(String valorAnterior) {
        this.valorAnterior = valorAnterior;
    }

    public String getValorNuevo() {
        return valorNuevo;
    }

    public void setValorNuevo(String valorNuevo) {
        this.valorNuevo = valorNuevo;
    }

    public Date getFechaMarcacion() {
        return fechaMarcacion;
    }

    public void setFechaMarcacion(Date fechaMarcacion) {
        this.fechaMarcacion = fechaMarcacion;
    }

    public String getMaeEstadoNovedad() {
        return maeEstadoNovedad;
    }

    public void setMaeEstadoNovedad(String maeEstadoNovedad) {
        this.maeEstadoNovedad = maeEstadoNovedad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(Integer sincronizado) {
        this.sincronizado = sincronizado;
    }

    public AsegRadicadoNovedad getRadicadoNovedad() {
        return radicadoNovedad;
    }

    public void setRadicadoNovedad(AsegRadicadoNovedad radicadoNovedad) {
        this.radicadoNovedad = radicadoNovedad;
    }

    public AsegMaNovedad getAsegMaNovedad() {
        return asegMaNovedad;
    }

    public void setAsegMaNovedad(AsegMaNovedad asegMaNovedad) {
        this.asegMaNovedad = asegMaNovedad;
    }

    public AsegReporteNovedad getAsegReporteNovedad() {
        return asegReporteNovedad;
    }

    public void setAsegReporteNovedad(AsegReporteNovedad asegReporteNovedad) {
        this.asegReporteNovedad = asegReporteNovedad;
    }

    @Override
    public String toString() {
        return "AsegRegistroNovedad{" + "id=" + id + ", idAfiliado=" + idAfiliado + ", fechaNovedad=" + fechaNovedad + ", valorAnterior=" + valorAnterior + ", valorNuevo=" + valorNuevo + ", fechaMarcacion=" + fechaMarcacion + ", maeEstadoNovedad=" + maeEstadoNovedad + ", observacion=" + observacion + ", sincronizado=" + sincronizado + ", radicadoNovedad=" + radicadoNovedad + ", asegMaNovedad=" + asegMaNovedad + ", asegReporteNovedad=" + asegReporteNovedad + '}';
    }

    /**
     * @return the asegInformesIdMarcacion
     */
    public Integer getAsegInformesIdMarcacion() {
        return asegInformesIdMarcacion;
    }

    /**
     * @param asegInformesIdMarcacion the asegInformesIdMarcacion to set
     */
    public void setAsegInformesIdMarcacion(Integer asegInformesIdMarcacion) {
        this.asegInformesIdMarcacion = asegInformesIdMarcacion;
    }

    /**
     * @return the descripcionValorAnterior
     */
    public String getDescripcionValorAnterior() {
        return descripcionValorAnterior;
    }

    /**
     * @param descripcionValorAnterior the descripcionValorAnterior to set
     */
    public void setDescripcionValorAnterior(String descripcionValorAnterior) {
        this.descripcionValorAnterior = descripcionValorAnterior;
    }

    /**
     * @return the descripcionValorNuevo
     */
    public String getDescripcionValorNuevo() {
        return descripcionValorNuevo;
    }

    /**
     * @param descripcionValorNuevo the descripcionValorNuevo to set
     */
    public void setDescripcionValorNuevo(String descripcionValorNuevo) {
        this.descripcionValorNuevo = descripcionValorNuevo;
    }

    /**
     * @return the origenUltimoRegistro
     */
    public Integer getOrigenUltimoRegistro() {
        return origenUltimoRegistro;
    }

    /**
     * @param origenUltimoRegistro the origenUltimoRegistro to set
     */
    public void setOrigenUltimoRegistro(Integer origenUltimoRegistro) {
        this.origenUltimoRegistro = origenUltimoRegistro;
    }
    
    public String getOrigenUltimoRegistroStr() {
        String mensaje = "";
        if (origenUltimoRegistro != null) {
            switch(origenUltimoRegistro) {
                case ORIGEN_ULTIMO_REGISTRO_ID_PANTALLA:
                    mensaje = ORIGEN_ULTIMO_REGISTRO_PANTALLA;
                    break;
                case ORIGEN_ULTIMO_REGISTRO_ID_CARGA_MASIVA:
                    mensaje = ORIGEN_ULTIMO_REGISTRO_CARGA_MASIVA;
                    break;
                case ORIGEN_ULTIMO_REGISTRO_ID_SERVICIO_WEB:
                    mensaje = ORIGEN_ULTIMO_REGISTRO_SERVICIO_WEB;
                    break;
                case ORIGEN_ULTIMO_REGISTRO_ID_ANEXO_1:
                    mensaje = ORIGEN_ULTIMO_REGISTRO_ANEXO_1;
                    break;
                
            }
        }
        return mensaje;
    }

    /**
     * @return the descripcionOrigenUltimoRegistro
     */
    public String getDescripcionOrigenUltimoRegistro() {
        return descripcionOrigenUltimoRegistro;
    }

    /**
     * @param descripcionOrigenUltimoRegistro the descripcionOrigenUltimoRegistro to set
     */
    public void setDescripcionOrigenUltimoRegistro(String descripcionOrigenUltimoRegistro) {
        this.descripcionOrigenUltimoRegistro = descripcionOrigenUltimoRegistro;
    }
    
}
