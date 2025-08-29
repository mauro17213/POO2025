package com.saviasaludeps.savia.dominio.juridico;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;

/**
 *
 * @author idbohorquez
 */
public class CntjOtrosiAdjunto extends Auditoria {
        
    private Integer id;
    private CntjOtrosi otrosiId;
    private Integer maetipoArchivoId;
    private String maetipoArchivoCodigo;
    private String maetipoArchivoValor;
    private String nombre;
    private String ruta;
    private String archivo;
    private boolean existe;
    private transient InputStream adjuntoStream;

    public CntjOtrosiAdjunto() {
    }

    public CntjOtrosiAdjunto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CntjOtrosi getOtrosiId() {
        return otrosiId;
    }

    public void setOtrosiId(CntjOtrosi otrosiId) {
        this.otrosiId = otrosiId;
    }

    public Integer getMaetipoArchivoId() {
        return maetipoArchivoId;
    }

    public void setMaetipoArchivoId(Integer maetipoArchivoId) {
        this.maetipoArchivoId = maetipoArchivoId;
    }

    public String getMaetipoArchivoCodigo() {
        return maetipoArchivoCodigo;
    }

    public void setMaetipoArchivoCodigo(String maetipoArchivoCodigo) {
        this.maetipoArchivoCodigo = maetipoArchivoCodigo;
    }

    public String getMaetipoArchivoValor() {
        return maetipoArchivoValor;
    }

    public void setMaetipoArchivoValor(String maetipoArchivoValor) {
        this.maetipoArchivoValor = maetipoArchivoValor;
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

    public InputStream getAdjuntoStream() {
        return adjuntoStream;
    }

    public void setAdjuntoStream(InputStream adjuntoStream) {
        this.adjuntoStream = adjuntoStream;
    }
    
    public String getExisteStr(){
        if(this.existe){
            return "Si";
        }
        return "No";
    }
    
    

    @Override
    public String toString() {
        return "CntjOtrosiAdjunto{" + "id=" + id + ", otrosiId=" + otrosiId + ", maetipoArchivoId=" + maetipoArchivoId + ", maetipoArchivoCodigo=" + maetipoArchivoCodigo + ", maetipoArchivoValor=" + maetipoArchivoValor + ", nombre=" + nombre + ", ruta=" + ruta + ", archivo=" + archivo + ", existe=" + existe + '}';
    }
    
}
