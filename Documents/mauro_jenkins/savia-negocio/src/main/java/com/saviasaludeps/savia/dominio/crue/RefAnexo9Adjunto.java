/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.crue;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;
import java.util.Date;

/**
 *
 * @author Jaime Andres Olarte
 */
public class RefAnexo9Adjunto extends Auditoria {
    
    private Integer id;
    private int maeTipoArchivoId;
    private String maeTipoArchivoCodigo;
    private String maeTipoArchivoValor;
    private String nombreArchivo;
    private String ruta;
    private String archivo;    
    private RefAnexo9 refAnexo9;
    private int borrado;
    protected String usuarioBorra;
    protected String terminalBorra;
    protected Date fechaHoraBorra;
    private String borradoObservacion;
    private int existe;
    
    //Atributos temporales que se utilizan en el módulo de centro regulador.
    private String nombre;
    private InputStream adjuntoStream;
    private String extension;

    public RefAnexo9Adjunto() {
    }

    public RefAnexo9Adjunto(Integer id) {
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

    public RefAnexo9 getRefAnexo9() {
        return refAnexo9;
    }

    public int getBorrado() {
        return borrado;
    }

    public void setBorrado(int borrado) {
        this.borrado = borrado;
    }

    public String getBorradoObservacion() {
        return borradoObservacion;
    }

    public void setBorradoObservacion(String borradoObservacion) {
        this.borradoObservacion = borradoObservacion;
    }

    public String getUsuarioBorra() {
        return usuarioBorra;
    }

    public void setUsuarioBorra(String usuarioBorra) {
        this.usuarioBorra = usuarioBorra;
    }

    public String getTerminalBorra() {
        return terminalBorra;
    }

    public void setTerminalBorra(String terminalBorra) {
        this.terminalBorra = terminalBorra;
    }

    public Date getFechaHoraBorra() {
        return fechaHoraBorra;
    }

    public void setFechaHoraBorra(Date fechaHoraBorra) {
        this.fechaHoraBorra = fechaHoraBorra;
    }

    public int getExiste() {
        return existe;
    }

    public void setExiste(int existe) {
        this.existe = existe;
    }
    
    public void setRefAnexo9(RefAnexo9 refAnexo9) {
        this.refAnexo9 = refAnexo9;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public InputStream getAdjuntoStream() {
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
    //
    public String colorAdjuntoBorrado(){
        String color = "";
        if(getBorrado() == 1){
            color = "color: red ; text-decoration-line: line-through ;";
        }
        return color;
    }
   
    @Override
    public String toString() {
        return "RefAnexo9Adjunto{" + "id=" + id + ", maeTipoArchivoId=" + maeTipoArchivoId + ", maeTipoArchivoCodigo=" + maeTipoArchivoCodigo + ", maeTipoArchivoValor=" + maeTipoArchivoValor + ", nombreArchivo=" + nombreArchivo + ", ruta=" + ruta + ", archivo=" + archivo + ", nombre=" + nombre + ", adjuntoStream=" + adjuntoStream + ", extension=" + extension + '}';
    }
    
    
}
