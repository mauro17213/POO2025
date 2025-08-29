package com.saviasaludeps.savia.dominio.recobro;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;

/**
 *
 * @author Stiven Giraldo
 */
public class RcoConciliacionAdjunto extends Auditoria {
    
    public static final int TIPO_ACTA = 0;
    public static final int TIPO_CONCILIACION = 1;
    public static final int TIPO_ADJUNTOS = 2;
    
    private Integer id;
    private Integer maeTipoArchivoId;
    private String maeTipoArchivoCodigo;
    private String maeTipoArchivoValor;
    private Integer tipo;
    private String nombreArchivo;
    private String ruta;
    private String archivo;
    private boolean existe;
    private RcoConciliacion rcoConciliacionId;
    //Variables Auxiliares
    private transient InputStream adjuntoStream;
    
    public RcoConciliacionAdjunto() {
    }

    public RcoConciliacionAdjunto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
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

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public RcoConciliacion getRcoConciliacionId() {
        return rcoConciliacionId;
    }

    public void setRcoConciliacionId(RcoConciliacion rcoConciliacionId) {
        this.rcoConciliacionId = rcoConciliacionId;
    }

    public InputStream getAdjuntoStream() {
        return adjuntoStream;
    }

    public void setAdjuntoStream(InputStream adjuntoStream) {
        this.adjuntoStream = adjuntoStream;
    }
    
    public String getTipoStr(){
        String tipoStr = "";
        if(tipo != null){
            switch(tipo){
                case TIPO_ACTA:
                    tipoStr = "Acta";
                    break;
                case TIPO_CONCILIACION:
                    tipoStr = "Conciliación";
                    break;
                case TIPO_ADJUNTOS:
                    tipoStr = "Adjuntos";
                    break;
            }
        }
        return tipoStr;
    }
    
    @Override
    public String toString() {
        return "RcoConciliacionAdjunto{" + "id=" + id + ", maeTipoArchivoId=" + maeTipoArchivoId + ", maeTipoArchivoCodigo=" + maeTipoArchivoCodigo + ", maeTipoArchivoValor=" + maeTipoArchivoValor + ", tipo=" + tipo + ", nombreArchivo=" + nombreArchivo + ", ruta=" + ruta + ", archivo=" + archivo + ", existe=" + existe + '}';
    }
    
}
