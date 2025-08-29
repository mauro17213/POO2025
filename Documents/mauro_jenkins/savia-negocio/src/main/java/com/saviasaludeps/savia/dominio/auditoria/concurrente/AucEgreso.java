/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author sgiraldov
 */
public class AucEgreso extends Auditoria {

    private Integer id;
    private String numCertificado;
    private Date fechaEgreso;
    private boolean fallecido;
    private int maeDestinoEgresoId;
    private String maeDestinoEgresoCodigo;
    private String maeDestinoEgresoValor;
    private int maeConductaEgresoId;
    private String maeConductaEgresoCodigo;
    private String maeConductaEgresoValor;
    private int maEspecialidadesId;
    private String maEspecialidadesCodigo;
    private String maEspecialidadesValor;
    private BigDecimal valorEstancia;
    private Integer facturado;
    private boolean ipsEntregaValor;
    private int fuenteOrigen;
    private List<AucDiagnostico> aucDiagnosticosList;
    private List<AucHospitalizacion> aucHospitalizacionList;
    private int pos;
    private int habilitarADvertenciaValorEstancia;
    public AucEgreso() {
    }

    public AucEgreso(Integer id) {
        this.id = id;
    }

    public AucEgreso(Date fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumCertificado() {
        return numCertificado;
    }

    public void setNumCertificado(String numCertificado) {
        this.numCertificado = numCertificado;
    }

    public Date getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(Date fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public boolean isFallecido() {
        return fallecido;
    }

    public void setFallecido(boolean fallecido) {
        this.fallecido = fallecido;
    }

    public int getMaeDestinoEgresoId() {
        return maeDestinoEgresoId;
    }

    public void setMaeDestinoEgresoId(int maeDestinoEgresoId) {
        this.maeDestinoEgresoId = maeDestinoEgresoId;
    }

    public String getMaeDestinoEgresoCodigo() {
        return maeDestinoEgresoCodigo;
    }

    public void setMaeDestinoEgresoCodigo(String maeDestinoEgresoCodigo) {
        this.maeDestinoEgresoCodigo = maeDestinoEgresoCodigo;
    }

    public String getMaeDestinoEgresoValor() {
        return maeDestinoEgresoValor;
    }

    public void setMaeDestinoEgresoValor(String maeDestinoEgresoValor) {
        this.maeDestinoEgresoValor = maeDestinoEgresoValor;
    }

    public int getMaeConductaEgresoId() {
        return maeConductaEgresoId;
    }

    public void setMaeConductaEgresoId(int maeConductaEgresoId) {
        this.maeConductaEgresoId = maeConductaEgresoId;
    }

    public String getMaeConductaEgresoCodigo() {
        return maeConductaEgresoCodigo;
    }

    public void setMaeConductaEgresoCodigo(String maeConductaEgresoCodigo) {
        this.maeConductaEgresoCodigo = maeConductaEgresoCodigo;
    }

    public String getMaeConductaEgresoValor() {
        return maeConductaEgresoValor;
    }

    public void setMaeConductaEgresoValor(String maeConductaEgresoValor) {
        this.maeConductaEgresoValor = maeConductaEgresoValor;
    }

    public int getMaEspecialidadesId() {
        return maEspecialidadesId;
    }

    public void setMaEspecialidadesId(int maEspecialidadesId) {
        this.maEspecialidadesId = maEspecialidadesId;
    }

    public String getMaEspecialidadesCodigo() {
        return maEspecialidadesCodigo;
    }

    public void setMaEspecialidadesCodigo(String maEspecialidadesCodigo) {
        this.maEspecialidadesCodigo = maEspecialidadesCodigo;
    }

    public String getMaEspecialidadesValor() {
        return maEspecialidadesValor;
    }

    public void setMaEspecialidadesValor(String maEspecialidadesValor) {
        this.maEspecialidadesValor = maEspecialidadesValor;
    }

    public BigDecimal getValorEstancia() {
        return valorEstancia;
    }

    public void setValorEstancia(BigDecimal valorEstancia) {
        this.valorEstancia = valorEstancia;
    }

    public Integer getFacturado() {
        return facturado;
    }

    public void setFacturado(Integer facturado) {
        this.facturado = facturado;
    }

    public boolean isIpsEntregaValor() {
        return ipsEntregaValor;
    }

    public void setIpsEntregaValor(boolean ipsEntregaValor) {
        this.ipsEntregaValor = ipsEntregaValor;
    }

    public int getFuenteOrigen() {
        return fuenteOrigen;
    }

    public void setFuenteOrigen(int fuenteOrigen) {
        this.fuenteOrigen = fuenteOrigen;
    }

    public List<AucHospitalizacion> getAucHospitalizacionList() {
        return aucHospitalizacionList;
    }

    public void setAucHospitalizacionList(List<AucHospitalizacion> aucHospitalizacionList) {
        this.aucHospitalizacionList = aucHospitalizacionList;
    }

    public List<AucDiagnostico> getAucDiagnosticosList() {
        if (aucDiagnosticosList == null) {
            aucDiagnosticosList = new ArrayList<>();
        }
        return aucDiagnosticosList;
    }

    public void setAucDiagnosticosList(List<AucDiagnostico> aucDiagnosticosList) {
        this.aucDiagnosticosList = aucDiagnosticosList;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getHabilitarADvertenciaValorEstancia() {
        return habilitarADvertenciaValorEstancia;
    }

    public void setHabilitarADvertenciaValorEstancia(int habilitarADvertenciaValorEstancia) {
        this.habilitarADvertenciaValorEstancia = habilitarADvertenciaValorEstancia;
    }

    //Metodos auxiliares
    public String getFallecidoStr() {
        if (fallecido) {
            return "Sí";
        } else {
            return "No";
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat formatFecha = new SimpleDateFormat("yyyy-MM-dd");
        String feEgegra = (fechaEgreso != null) ? formatFecha.format(fechaEgreso): null;
        return "AucEgreso{" + "id=" + id + ", numCertificado=" + numCertificado + ", fechaEgreso=" + fechaEgreso + ", fallecido=" + fallecido + ", maeDestinoEgresoId=" + maeDestinoEgresoId + ", maeDestinoEgresoCodigo=" + maeDestinoEgresoCodigo + ", maeDestinoEgresoValor=" + maeDestinoEgresoValor + ", maeConductaEgresoId=" + maeConductaEgresoId + ", maeConductaEgresoCodigo=" + maeConductaEgresoCodigo + ", maeConductaEgresoValor=" + maeConductaEgresoValor + ", maEspecialidadesId=" + maEspecialidadesId + ", maEspecialidadesCodigo=" + maEspecialidadesCodigo + ", maEspecialidadesValor=" + maEspecialidadesValor + ", valorEstancia=" + valorEstancia + ", facturado=" + facturado + ", ipsEntregaValor=" + ipsEntregaValor + ", fuenteOrigen=" + fuenteOrigen + '}';
    }

}
