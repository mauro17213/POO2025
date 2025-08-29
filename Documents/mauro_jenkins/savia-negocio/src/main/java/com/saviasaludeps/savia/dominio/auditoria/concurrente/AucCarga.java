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
import java.util.Date;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public class AucCarga extends Auditoria {

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
    
    //TIPOS DE CARGAS MASIVAS
    public static final int TIPO_CENSO = 1;
    public static final int TIPO_SEGUIMIENTO = 2;
    
    public static final String CENSO = "Censo";
    public static final String SEGUIMIENTO = "Seguimiento";
    
    private Integer id;
    private int estado;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private int registrosTotal;
    private int registrosExitosos;
    private int registrosRechazados;
    private short hopitalizados;
    private String nombreArchivo;
    private String ruta;
    private String archivo;
    private boolean existe;
    //2023-09-01 nuevos campos
    private int tipo;
    private List<AucCargaFallo> aucCargaFalloList;
    private CntPrestadorSede cntPrestadorSedeId;
    private CntPrestador cntPrestadorId;
    private Empresa gnEmpresaId;

    //Variables auxiliares
    private transient InputStream adjuntoStream;
    private String afiliadosExcel;

    public AucCarga() {
    }

    public AucCarga(Integer id) {
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

    public List<AucCargaFallo> getAucCargaFalloList() {
        return aucCargaFalloList;
    }

    public void setAucCargaFalloList(List<AucCargaFallo> aucCargaFalloList) {
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

    public String getAfiliadosExcel() {
        return afiliadosExcel;
    }

    public void setAfiliadosExcel(String afiliadosExcel) {
        this.afiliadosExcel = afiliadosExcel;
    }

    public short getHopitalizados() {
        return hopitalizados;
    }

    public void setHopitalizados(short hopitalizados) {
        this.hopitalizados = hopitalizados;
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

    /**
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    /**
     * @return the tipo
     */
    public String getTipoStr() {
        String est = "";
        switch (tipo) {
            case TIPO_CENSO:
                est = CENSO;
                break;
            case TIPO_SEGUIMIENTO:
                est = SEGUIMIENTO;
                break;
        }
        return est;
    }

}
