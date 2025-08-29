/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public class AucCargaCierre extends Auditoria {

    //Estados
    public static final int ESTADO_EN_COLA = 0;
    public static final int ESTADO_EN_PROCESO = 1;
    public static final int ESTADO_PROCESADO = 2;
    public static final int ESTADO_CANCELADO = 3;
    public static final int ESTADO_PROCESADO_INCOMPLETO = 4;

    //TEXTOS
    public static final String EN_COLA = "En Cola";
    public static final String EN_PROCESO = "En Proceso";
    public static final String PROCESADO = "Procesado";
    public static final String CANCELADO = "Cancelado";
    public static final String PROCESADO_INCOMPLETO = "Procesado Incompleto";
    
    //Formato adjunto
    public final static SimpleDateFormat FORMATO_EXEL = new SimpleDateFormat("YYYYMMddHHmmss");

    private Integer id;
    private int estado;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private int registrosTotal;
    private int registrosExitosos;
    private int registrosRechazados;
    private String nombreArchivo;
    private String ruta;
    private String archivo;
    private boolean existe;
    private List<AucCargaCierreSuceso> aucCargaFalloList;
    private CntPrestadorSede cntPrestadorSedeId;
    private CntPrestador cntPrestadorId;
    private Empresa gnEmpresaId;

    //Variables auxiliares
    private transient InputStream adjuntoStream;

    public AucCargaCierre() {
    }

    public AucCargaCierre(Integer id) {
        this.id = id;
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

    public int getRegistrosExitosos() {
        return registrosExitosos;
    }

    public void setRegistrosExitosos(int registrosExitosos) {
        this.registrosExitosos = registrosExitosos;
    }

    public int getRegistrosRechazados() {
        return registrosRechazados;
    }

    public void setRegistrosRechazados(int registrosRechazados) {
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

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public List<AucCargaCierreSuceso> getAucCargaFalloList() {
        return aucCargaFalloList;
    }

    public void setAucCargaFalloList(List<AucCargaCierreSuceso> aucCargaFalloList) {
        this.aucCargaFalloList = aucCargaFalloList;
    }

    public CntPrestadorSede getCntPrestadorSedeId() {
        return cntPrestadorSedeId;
    }

    public void setCntPrestadorSedeId(CntPrestadorSede cntPrestadorSedeId) {
        this.cntPrestadorSedeId = cntPrestadorSedeId;
    }

    public CntPrestador getCntPrestadorId() {
        return cntPrestadorId;
    }

    public void setCntPrestadorId(CntPrestador cntPrestadorId) {
        this.cntPrestadorId = cntPrestadorId;
    }

    public Empresa getGnEmpresaId() {
        return gnEmpresaId;
    }

    public void setGnEmpresaId(Empresa gnEmpresaId) {
        this.gnEmpresaId = gnEmpresaId;
    }

    public InputStream getAdjuntoStream() {
        return adjuntoStream;
    }

    public void setAdjuntoStream(InputStream adjuntoStream) {
        this.adjuntoStream = adjuntoStream;
    }

    

    //Metodos auxiliares
    public String getEstadoStr() {
        String est = "";
        switch (estado) {
            case ESTADO_EN_COLA:
                est = EN_COLA;
                break;
            case ESTADO_EN_PROCESO:
                est = EN_PROCESO;
                break;
            case ESTADO_PROCESADO:
                est = PROCESADO;
                break;
            case ESTADO_CANCELADO:
                est = CANCELADO;
                break;
            case ESTADO_PROCESADO_INCOMPLETO:
                est = PROCESADO_INCOMPLETO;
                break;
        }
        return est;
    }

    @Override
    public String toString() {
        return "AucCarga{" + "id=" + id + ", estado=" + estado + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin + ", registrosToal=" + registrosTotal + ", registrosExitosos=" + registrosExitosos + ", registrosRechazados=" + registrosRechazados + ", nombreArchivo=" + nombreArchivo + ", ruta=" + ruta + ", archivo=" + archivo + ", existe=" + existe + ", cntPrestadorSedeId=" + cntPrestadorSedeId + ", cntPrestadorId=" + cntPrestadorId + ", gnEmpresaId=" + gnEmpresaId + '}';
    }

}
