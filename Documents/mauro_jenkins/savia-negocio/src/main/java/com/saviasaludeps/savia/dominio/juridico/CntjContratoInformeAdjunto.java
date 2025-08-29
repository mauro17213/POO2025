package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;

/**
 *
 * @author StivenGV
 */
public class CntjContratoInformeAdjunto extends Auditoria {
    
    private Integer id;
    private int maeTipoArchivoId;
    private String maeTipoArchivoCodigo;
    private String maeTipoArchivoValor;
    private String nombre;
    private String ruta;
    private String archivo;
    private boolean existe;
    private CntjContratoInforme cntjContratoInformeId;
    
    //Auxiliares
    private transient InputStream adjuntoStream;

    public CntjContratoInformeAdjunto() {
    }

    public CntjContratoInformeAdjunto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeTipoArchivoId() {
        return maeTipoArchivoId;
    }

    public void setMaeTipoArchivoId(int maeTipoArchivoId) {
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

    public CntjContratoInforme getCntjContratoInformeId() {
        return cntjContratoInformeId;
    }

    public void setCntjContratoInformeId(CntjContratoInforme cntjContratoInformeId) {
        this.cntjContratoInformeId = cntjContratoInformeId;
    }
    
    public InputStream getAdjuntoStream() {
        return adjuntoStream;
    }

    //Auxiliares
    public void setAdjuntoStream(InputStream adjuntoStream) {    
        this.adjuntoStream = adjuntoStream;
    }
    

    @Override
    public String toString() {
        return "CntjContratoInformeAdjunto{" + "id=" + id + ", maeTipoArchivoId=" + maeTipoArchivoId + ", maeTipoArchivoCodigo=" + maeTipoArchivoCodigo + ", maeTipoArchivoValor=" + maeTipoArchivoValor + ", nombre=" + nombre + ", ruta=" + ruta + ", archivo=" + archivo + ", existe=" + existe + '}';
    }
    
    
}
