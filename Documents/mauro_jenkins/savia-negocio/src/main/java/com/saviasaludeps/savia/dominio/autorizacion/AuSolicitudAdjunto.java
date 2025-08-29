/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.crue.AuAnexo2;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Rescate;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 *
 * @author iavenegas
 */
public class AuSolicitudAdjunto extends Auditoria {

    public static final int ORIGEN_COTIZACION = 1;
    public static final int ORIGEN_ANEXO2 = 2;
    public static final int ORIGEN_ANEXO3 = 3;
    public static final int ORIGEN_HOSPITALIZACION = 4;
    public static final int ORIGEN_SEGUIMIENTO = 5;
    public static final int ORIGEN_SEGUIMIENTO_AFILIADOS = 6;
    public static final int ORIGEN_SEGUIMIENTO_GESTIONES = 7;
    public static final int ORIGEN_RESCATES = 8;
    public static final int ORIGEN_TOPES = 9;
    public static final int ORIGEN_NO_SOLICITUDES = 10;
    public static final int ORIGEN_NO_SOLICITUDES_ENTREGAS = 11;
    public static final int ORIGEN_ANEXOS4_ENTREGAS = 12;

    private Integer id;
    private int origen;
    private Integer maeTipoArchivoId;
    private String maeTipoArchivoCodigo;
    private String maeTipoArchivoValor;
    private String nombre;
    private String ruta;
    private String archivo;
    private boolean existe;
    private AuAnexo2 auAnexo2;
    private AuAnexo3 auAnexo3;
    private AuAnexo2Rescate auAnexo2Rescate;
    private AuTopeAfiliado auTopeAfiliadosId;
    private AuCotizacion auCotizacion;
    private AuSeguimientoAfiliado auSeguimientoAfiliado;
    private AuSeguimiento auSeguimiento;
    private AuSeguimientoGestion auSeguimientoGestion;
    private AuNoSolicitud auNoSolicitud;
    private AuNoSolicitudEntrega auNoSolicitudEntregasId;
    //private AuAnexos4Entregas AuAnexos4EntregasId;

    //Atributos temporales
    private transient InputStream adjuntoStream;
    private String extension;
    private byte[] byteStream;

    public AuSolicitudAdjunto() {
    }

    public AuSolicitudAdjunto(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public Integer getMaeTipoArchivoId() {
        return maeTipoArchivoId;
    }

    public void setMaeTipoArchivoId(Integer maeTipoArchivoId) {
        this.maeTipoArchivoId = maeTipoArchivoId;
    }

    public String getMaeTipoArchivoCodigo() {
        return maeTipoArchivoCodigo;
    }

    public void setMaeTipoArchivoCodigo(String maeTipoArchivoCodigo) {
        this.maeTipoArchivoCodigo = maeTipoArchivoCodigo;
    }

    public String getMaeTipoArchivoValor() {
        return maeTipoArchivoValor;
    }

    public void setMaeTipoArchivoValor(String maeTipoArchivoValor) {
        this.maeTipoArchivoValor = maeTipoArchivoValor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public AuAnexo2 getAuAnexo2() {
        return auAnexo2;
    }

    public void setAuAnexo2(AuAnexo2 auAnexo2) {
        this.auAnexo2 = auAnexo2;
    }

    public AuAnexo3 getAuAnexo3() {
        return auAnexo3;
    }

    public void setAuAnexo3(AuAnexo3 auAnexo3) {
        this.auAnexo3 = auAnexo3;
    }

    public AuCotizacion getAuCotizacion() {
        return auCotizacion;
    }

    public void setAuCotizacion(AuCotizacion auCotizacion) {
        this.auCotizacion = auCotizacion;
    }

    public InputStream getAdjuntoStream() {
        if (adjuntoStream == null) {
            if (getByteStream().length > 0) {
                adjuntoStream = new ByteArrayInputStream(getByteStream());
            }
        }
        return adjuntoStream;
    }

    public void setAdjuntoStream(InputStream adjuntoStream) {
        this.adjuntoStream = adjuntoStream;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public byte[] getByteStream() {
        return byteStream;
    }

    public void setByteStream(byte[] byteStream) {
        this.byteStream = byteStream;
    }

    public AuSeguimientoAfiliado getAuSeguimientoAfiliado() {
        return auSeguimientoAfiliado;
    }

    public void setAuSeguimientoAfiliado(AuSeguimientoAfiliado auSeguimientoAfiliado) {
        this.auSeguimientoAfiliado = auSeguimientoAfiliado;
    }

    public AuSeguimiento getAuSeguimiento() {
        return auSeguimiento;
    }

    public void setAuSeguimiento(AuSeguimiento auSeguimiento) {
        this.auSeguimiento = auSeguimiento;
    }

    public AuAnexo2Rescate getAuAnexo2Rescate() {
        return auAnexo2Rescate;
    }

    public void setAuAnexo2Rescate(AuAnexo2Rescate auAnexo2Rescate) {
        this.auAnexo2Rescate = auAnexo2Rescate;
    }

    public AuTopeAfiliado getAuTopeAfiliadosId() {
        return auTopeAfiliadosId;
    }

    public void setAuTopeAfiliadosId(AuTopeAfiliado auTopeAfiliadosId) {
        this.auTopeAfiliadosId = auTopeAfiliadosId;
    }
    
    /**
     * @return the auSeguimientoGestion
     */
    public AuSeguimientoGestion getAuSeguimientoGestion() {
        return auSeguimientoGestion;
    }

    /**
     * @param auSeguimientoGestion the auSeguimientoGestion to set
     */
    public void setAuSeguimientoGestion(AuSeguimientoGestion auSeguimientoGestion) {
        this.auSeguimientoGestion = auSeguimientoGestion;
    }

    public AuNoSolicitud getAuNoSolicitud() {
        return auNoSolicitud;
    }

    public void setAuNoSolicitud(AuNoSolicitud auNoSolicitud) {
        this.auNoSolicitud = auNoSolicitud;
    }

    public AuNoSolicitudEntrega getAuNoSolicitudEntregasId() {
        return auNoSolicitudEntregasId;
    }

    public void setAuNoSolicitudEntregasId(AuNoSolicitudEntrega auNoSolicitudEntregasId) {
        this.auNoSolicitudEntregasId = auNoSolicitudEntregasId;
    }
    
    public String getOrigenStr() {
        String str;
        switch (this.origen) {
            case ORIGEN_COTIZACION:
                str = "Cotización";
                break;
            case ORIGEN_ANEXO2:
                str = "Anexo 2";
                break;
            case ORIGEN_ANEXO3:
                str = "Anexo 3";
                break;
            case ORIGEN_HOSPITALIZACION:
                str = "Hospitalización";
                break;
            case ORIGEN_SEGUIMIENTO:
                str = "Seguimiento";
                break;
            case ORIGEN_SEGUIMIENTO_AFILIADOS:
                str = "Seguimiento Afiliados";
                break;
            case ORIGEN_SEGUIMIENTO_GESTIONES:
                str = "Seguimiento Gestión";
                break;
            case ORIGEN_RESCATES:
                str = "Rescates";
                break;
            case ORIGEN_NO_SOLICITUDES:
                str = "Sin Autorizaciónes";
                break;
            case ORIGEN_NO_SOLICITUDES_ENTREGAS:
                str = "Sin Autorizaciónes Entregas";
                break;
            case ORIGEN_ANEXOS4_ENTREGAS:
                str = "Anexos 4 Entregas";
                break;
            default:
                str = "";
                break;
        }
        return str;
    }

     @Override
    public String toString() {
        return "AuSolicitudAdjunto{" + "id=" + id + ", origen=" + origen + ", maeTipoArchivoId=" + maeTipoArchivoId
                + ", maeTipoArchivoCodigo=" + maeTipoArchivoCodigo + ", maeTipoArchivoValor=" + maeTipoArchivoValor + ", nombre=" + nombre
                + ", ruta=" + ruta + ", archivo=" + archivo + ", existe=" + existe + ", auAnexo2=" + auAnexo2 + ", auAnexo3=" + auAnexo3
                + ", auCotizacion=" + auCotizacion + '}';
    }
}
