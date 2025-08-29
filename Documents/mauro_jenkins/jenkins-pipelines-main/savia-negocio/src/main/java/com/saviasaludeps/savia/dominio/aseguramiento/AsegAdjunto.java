/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.aseguramiento;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jyperez
 */
public class AsegAdjunto extends Auditoria {

    public static final int TIPO_ACTIVACION_AFILIACION = 1;
    public static final int TIPO_CARNETIZACION_AFILIADO = 8;
    public static final int TIPO_CONTRATO_MUNICIPIO = 9;
    public static final int TIPO_INACTIVAR_AFILIACION = 16;
    public static final int TIPO_INGRESO_AFILIACION = 17;
    public static final int TIPO_MODIFICACION_AFILIACION = 21;
    public static final int TIPO_MODIFICACION_AFILIACION_2 = 22;
    public static final int TIPO_MODIFICACION_DATOS_PERSONALES = 23;
    public static final int TIPO_MODIFICACION_DATOS_RESIDENCIA = 24;
    public static final int TIPO_OCULTAR_AFILIACION = 25;
    public static final int TIPO_PORTABILIDAD = 27;
    public static final int TIPO_RETIRO_AFILIACION = 29;
    public static final int TIPO_SUSPENCION_AFILIACION = 30;
    public static final int TIPO_SUSPENCION_CONTRIBUTIVO_AFILIACION = 31;

    public static final int TIPO_FUA_NOVEDAD = 50;
    public static final int TIPO_FUA_NOVEDAD_SOPORTE = 51;
    public static final int TIPO_DOCUMENTO_IDENTIDAD = 52;
    public static final int TIPO_DEBERES_DERECHOS = 53;
    public static final int TIPO_CERTIFICADO_SISBEN = 54;
    public static final int TIPO_ESTADO_BDUA = 55;
    public static final int TIPO_CERTIFICADO_DEFUNCION = 56;
    public static final int TIPO_CERTIFICADO_NACIDO_VIVO = 57;
    public static final int TIPO_CERTIFICADO_DISCAPACIDAD = 58;
    public static final int TIPO_CERTIFICADO_FALLO_DE_TUTELA = 59;

    private Integer id;
    private int tipoArchivo;
    private String nombreArchivo;
    private String ruta;
    private String archivo;
    private String observacion;
    private transient InputStream adjuntoStream;
    private String extensionAdjunto;
    private AsegRadicadoNovedad radicadoNovedad;

    public AsegAdjunto() {
    }

    public AsegAdjunto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipoArchivo() {
        return tipoArchivo;
    }

    public String getTipoArchivoStr() {
        String str = "";
        switch (tipoArchivo) {
            case TIPO_ACTIVACION_AFILIACION:
                str = "Afiliación - Activación";
                break;
            case TIPO_CARNETIZACION_AFILIADO:
                str = "Carnetización";
                break;
            case TIPO_CONTRATO_MUNICIPIO:
                str = "Contrato Municipio";
                break;
            case TIPO_INACTIVAR_AFILIACION:
                str = "Afiliación - Inactivación";
                break;
            case TIPO_INGRESO_AFILIACION:
                str = "Afiliación - Ingreso";
                break;
            case TIPO_MODIFICACION_AFILIACION:
                str = "Afiliación - Modificación";
                break;
            case TIPO_MODIFICACION_AFILIACION_2:
                str = "Afiliación - Modificación";
                break;
            case TIPO_MODIFICACION_DATOS_PERSONALES:
                str = "Datos personales - Modificación";
                break;
            case TIPO_MODIFICACION_DATOS_RESIDENCIA:
                str = "Datos residencia - Modificación";
                break;
            case TIPO_OCULTAR_AFILIACION:
                str = "Afiliación - Ocultar";
                break;
            case TIPO_PORTABILIDAD:
                str = "Portabilidad";
                break;
            case TIPO_RETIRO_AFILIACION:
                str = "Afiliación - Retiro";
                break;
            case TIPO_SUSPENCION_AFILIACION:
                str = "Afiliació - Sustención";
                break;
            case TIPO_SUSPENCION_CONTRIBUTIVO_AFILIACION:
                str = "Afiliación contributivo - Suspención";
                break;
            case TIPO_FUA_NOVEDAD:
                str = "FUA - Novedades";
                break;
            case TIPO_FUA_NOVEDAD_SOPORTE:
                str = "FUA - Novedades con Soportes";
                break;
            case TIPO_DOCUMENTO_IDENTIDAD:
                str = "Documento de Identidad";
                break;
            case TIPO_DEBERES_DERECHOS:
                str = "Carta de Deberes y Derechos FO-GA-15";
                break;
            case TIPO_CERTIFICADO_SISBEN:
                str = "Certificado Sisbén";
                break;
            case TIPO_ESTADO_BDUA:
                str = "Estado usuario en la BDUA";
                break;
            case TIPO_CERTIFICADO_DEFUNCION:
                str = "Certificado de Defunción";
                break;
            case TIPO_CERTIFICADO_NACIDO_VIVO:
                str = "Certificado de Nacido Vivo";
                break;
            case TIPO_CERTIFICADO_DISCAPACIDAD:
                str = "Certificado de Discapacidad";
                break;
            case TIPO_CERTIFICADO_FALLO_DE_TUTELA:
            str = "FALLO DE TUTELA";
            break;
            default:
                str = "DESCONOCIDO";
                break;
        }
        return str;
    }

    public void setTipoArchivo(int tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public InputStream getAdjuntoStream() {
        return adjuntoStream;
    }

    public void setAdjuntoStream(InputStream adjuntoStream) {
        this.adjuntoStream = adjuntoStream;
    }

    public String getExtensionAdjunto() {
        return extensionAdjunto;
    }

    public void setExtensionAdjunto(String extensionAdjunto) {
        this.extensionAdjunto = extensionAdjunto;
    }

    public AsegRadicadoNovedad getRadicadoNovedad() {
        return radicadoNovedad;
    }

    public void setRadicadoNovedad(AsegRadicadoNovedad radicadoNovedad) {
        this.radicadoNovedad = radicadoNovedad;
    }
    
    public String getFechaStr(Date fecha) {
        String mensaje = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            mensaje = sdf.format(fecha);
        }catch (Exception ex) {
            mensaje= "";
        }
        return mensaje;
    }

    @Override
    public String toString() {
        return "AsegAdjunto{" + "id=" + id + ", tipoArchivo=" + tipoArchivo + ", nombreArchivo=" + nombreArchivo + ", ruta=" + ruta + ", archivo=" + archivo + ", observacion=" + observacion + ", adjuntoStream=" + adjuntoStream + ", extensionAdjunto=" + extensionAdjunto + ", radicadoNovedad=" + radicadoNovedad + ", usuarioCrea=" + getUsuarioCrea() + ", terminalCrea=" + getTerminalCrea() + ", fechaHoraCrea=" + getFechaStr(getFechaHoraCrea()) + ", usuarioModifica=" + getUsuarioModifica() + ", terminalModifica=" + getTerminalModifica() + ", fechaHoraModifica=" + getFechaStr(getFechaHoraModifica()) + '}';
    }

}
