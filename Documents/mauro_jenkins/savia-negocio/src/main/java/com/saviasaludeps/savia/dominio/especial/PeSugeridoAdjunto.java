package com.saviasaludeps.savia.dominio.especial;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author idbohorquez
 */
public class PeSugeridoAdjunto extends Auditoria{
    
    
    private Integer id;
    private int maeTipoArchivoId;
    private String maeTipoArchivoCodigo;
    private String maeTipoArchivoValor;
    private String nombre;
    private String ruta;
    private String archivo;
    private String observacion;
    private boolean existe;
    private PeAfiliadoSugerido peAfiliadoSugerido;
    private int posicion;

    public PeSugeridoAdjunto() {
    }

    public PeSugeridoAdjunto(Integer id) {
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
    
    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public PeAfiliadoSugerido getPeAfiliadoSugerido() {
        return peAfiliadoSugerido;
    }

    public void setPeAfiliadoSugerido(PeAfiliadoSugerido peAfiliadoSugerido) {
        this.peAfiliadoSugerido = peAfiliadoSugerido;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
    
    @Override
    public String toString() {
        return "PeSugeridoAdjunto{" + "id=" + id + ", maeTipoArchivoId=" + maeTipoArchivoId + ", maeTipoArchivoCodigo=" + maeTipoArchivoCodigo + ", maeTipoArchivoValor=" + maeTipoArchivoValor + ", nombre=" + nombre + ", ruta=" + ruta + ", archivo=" + archivo + ", existe=" + existe + ", peAfiliadoSugerido=" + peAfiliadoSugerido + '}';
    }
    
    
    
    
}
