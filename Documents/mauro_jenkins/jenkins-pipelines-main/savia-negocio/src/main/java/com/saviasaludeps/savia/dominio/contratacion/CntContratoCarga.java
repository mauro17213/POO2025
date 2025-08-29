/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.contratacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jyperez
 */
public class CntContratoCarga extends Auditoria {
    
    private Integer id;
    private int estado;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private int registrosTotal;
    private Integer registrosExitosos;
    private Integer registrosRechazados;
    private String nombreArchivo;
    private int tipo;
    private String ruta;
    private String archivo;
    private boolean existe;
    //2025-02-12 jyperez campos nuevos
    private String respNombre;
    private String respRuta;
    private String respArchivo;
    private boolean respExiste;
    // variables para carga de adjunto
    private transient InputStream adjuntoStream;
    //objetos
    private List<CntContratoCargaSuceso> listaCntContratoCargaSuceso;
    //constantes
    public final static int ESTADO_CARGA_EN_COLA = 0;
    public static int ESTADO_CARGA_PROCESANDO = 1;
    
    public final static int TIPO_CONTRATO_DETALLE = 1;
    public final static int TIPO_NOTA_TECNICA = 2;
    public final static int TIPO_COBERTURA = 3;
    public final static int TIPO_MARCACION = 4;
    public final static int TIPO_REPS_PRESTADORES = 5;

    public CntContratoCarga() {
    }

    public CntContratoCarga(Integer id) {
        this.id = id;
    }

    public CntContratoCarga(Integer id, int estado, Date fechaHoraInicio, int registrosTotal, boolean existe) {
        this.id = id;
        this.estado = estado;
        this.fechaHoraInicio = fechaHoraInicio;
        this.registrosTotal = registrosTotal;
        this.existe = existe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public int getRegistrosTotal() {
        return registrosTotal;
    }

    public void setRegistrosTotal(int registrosTotal) {
        this.registrosTotal = registrosTotal;
    }

    public Integer getRegistrosExitosos() {
        return registrosExitosos;
    }

    public void setRegistrosExitosos(Integer registrosExitosos) {
        this.registrosExitosos = registrosExitosos;
    }

    public Integer getRegistrosRechazados() {
        return registrosRechazados;
    }

    public void setRegistrosRechazados(Integer registrosRechazados) {
        this.registrosRechazados = registrosRechazados;
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

    public boolean getExiste() {
        return isExiste();
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    public String getExisteStr() {
        if (isExiste()) {
            return "Si";
        }else {
            return "No";
        }
    }

    public InputStream getAdjuntoStream() {
        return adjuntoStream;
    }

    public void setAdjuntoStream(InputStream adjuntoStream) {
        this.adjuntoStream = adjuntoStream;
    }
    
    public Integer getRegistrosPendientes() {
        return registrosExitosos + registrosRechazados;
    }

    /**
     * @return the listaCntContratoCargaSuceso
     */
    public List<CntContratoCargaSuceso> getListaCntContratoCargaSuceso() {
        return listaCntContratoCargaSuceso;
    }

    /**
     * @param listaCntContratoCargaSuceso the listaCntContratoCargaSuceso to set
     */
    public void setListaCntContratoCargaSuceso(List<CntContratoCargaSuceso> listaCntContratoCargaSuceso) {
        this.listaCntContratoCargaSuceso = listaCntContratoCargaSuceso;
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
        return "CntContratoCarga{" + "id=" + id + ", estado=" + estado + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin + ", registrosTotal=" + registrosTotal + ", registrosExitosos=" + registrosExitosos + ", registrosRechazados=" + registrosRechazados + ", nombreArchivo=" + nombreArchivo + ", tipo=" + tipo + ", ruta=" + ruta + ", archivo=" + archivo + ", existe=" +  getExisteStr() + ", respNombre=" + respNombre + ", respRuta=" + respRuta + ", respArchivo=" + respArchivo + ", respExiste=" + respExiste + '}';
    }

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }
    
    /**
     * @return the tipo
     */
    public String getTipoStr() {
        String mensaje = "";
        switch(tipo){
            case TIPO_CONTRATO_DETALLE:
                mensaje = "Contrato Detalle";
            break;
            case TIPO_NOTA_TECNICA:
                mensaje = "Nota Técnica";
            break;
            case TIPO_COBERTURA:
                mensaje = "Cobertura";
            break;
            case TIPO_MARCACION:
                mensaje = "Marcación";
            break;
            case TIPO_REPS_PRESTADORES:
                mensaje = "REPS Prestadores";
            break;
        }
        return mensaje;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the existe
     */
    public boolean isExiste() {
        return existe;
    }

    /**
     * @return the respNombre
     */
    public String getRespNombre() {
        return respNombre;
    }

    /**
     * @param respNombre the respNombre to set
     */
    public void setRespNombre(String respNombre) {
        this.respNombre = respNombre;
    }

    /**
     * @return the respRuta
     */
    public String getRespRuta() {
        return respRuta;
    }

    /**
     * @param respRuta the respRuta to set
     */
    public void setRespRuta(String respRuta) {
        this.respRuta = respRuta;
    }

    /**
     * @return the respArchivo
     */
    public String getRespArchivo() {
        return respArchivo;
    }

    /**
     * @param respArchivo the respArchivo to set
     */
    public void setRespArchivo(String respArchivo) {
        this.respArchivo = respArchivo;
    }

    /**
     * @return the respExiste
     */
    public boolean isRespExiste() {
        return respExiste;
    }

    /**
     * @param respExiste the respExiste to set
     */
    public void setRespExiste(boolean respExiste) {
        this.respExiste = respExiste;
    }
    
}
