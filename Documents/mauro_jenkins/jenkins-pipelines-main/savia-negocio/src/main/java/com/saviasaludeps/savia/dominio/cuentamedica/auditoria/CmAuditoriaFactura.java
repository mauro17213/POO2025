/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.cuentamedica.auditoria;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.cuentamedica.rips.CmRipsCarga;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class CmAuditoriaFactura extends Auditoria implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private int cmRipsCargasId;
    private int maeTipoContratoId;
    private String nit;
    private String ips;
    private int numeroRadicado;
    private String numeroFacturado;
    private Date fechaPrestacion;
    private Date fechaRadicacion;
    private boolean multiusuario;
    private BigDecimal valorPendienteActual;
    private BigDecimal valorInicialGlosa;
    private String usuarioGestiona;
    private int marcacion;
    private Date fechaMarcacion;
    private Date fechaVencimiento;
    private int tipoAuditoria;
    private String historialProceso;
    private BigDecimal valorFactura;
    private String maeTipoContratoCodigo;
    private String maeTipoContratoValor;
    private int maeRegimenId;   
    private String maeRegimenCodigo;
    private String maeRegimenValor; 
    private int estadoFactura; 
    private CntPrestador cntPrestadoresId;
    private int idGnEmpresas;
     
    public CmAuditoriaFactura() {
    }

    public CmAuditoriaFactura(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCmRipsCargasId() {
        return cmRipsCargasId;
    }

    public void setCmRipsCargasId(int cmRipsCargasId) {
        this.cmRipsCargasId = cmRipsCargasId;
    }

    public int getMaeTipoContratoId() {
        return maeTipoContratoId;
    }

    public void setMaeTipoContratoId(int maeTipoContratoId) {
        this.maeTipoContratoId = maeTipoContratoId;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }

    public int getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(int numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }

    public String getNumeroFacturado() {
        return numeroFacturado;
    }

    public void setNumeroFacturado(String numeroFacturado) {
        this.numeroFacturado = numeroFacturado;
    }

    public Date getFechaPrestacion() {
        return fechaPrestacion;
    }

    public void setFechaPrestacion(Date fechaPrestacion) {
        this.fechaPrestacion = fechaPrestacion;
    }

    public Date getFechaRadicacion() {
        return fechaRadicacion;
    }

    public void setFechaRadicacion(Date fechaRadicacion) {
        this.fechaRadicacion = fechaRadicacion;
    }

    public boolean isMultiusuario() {
        return multiusuario;
    }

    public void setMultiusuario(boolean multiusuario) {
        this.multiusuario = multiusuario;
    }

    public BigDecimal getValorPendienteActual() {
        return valorPendienteActual;
    }

    public void setValorPendienteActual(BigDecimal valorPendienteActual) {
        this.valorPendienteActual = valorPendienteActual;
    }

    public BigDecimal getValorInicialGlosa() {
        return valorInicialGlosa;
    }

    public void setValorInicialGlosa(BigDecimal valorInicialGlosa) {
        this.valorInicialGlosa = valorInicialGlosa;
    }

    public String getUsuarioGestiona() {
        return usuarioGestiona;
    }

    public void setUsuarioGestiona(String usuarioGestiona) {
        this.usuarioGestiona = usuarioGestiona;
    }

    public int getMarcacion() {
        return marcacion;
    }

    public void setMarcacion(int marcacion) {
        this.marcacion = marcacion;
    }

    public Date getFechaMarcacion() {
        return fechaMarcacion;
    }

    public void setFechaMarcacion(Date fechaMarcacion) {
        this.fechaMarcacion = fechaMarcacion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getTipoAuditoria() {
        return tipoAuditoria;
    }

    public void setTipoAuditoria(int tipoAuditoria) {
        this.tipoAuditoria = tipoAuditoria;
    }

    public String getHistorialProceso() {
        return historialProceso;
    }

    public void setHistorialProceso(String historialProceso) {
        this.historialProceso = historialProceso;
    }

    public BigDecimal getValorFactura() {
        return valorFactura;
    }

    public void setValorFactura(BigDecimal valorFactura) {
        this.valorFactura = valorFactura;
    }

    public String getMaeTipoContratoCodigo() {
        return maeTipoContratoCodigo;
    }

    public void setMaeTipoContratoCodigo(String maeTipoContratoCodigo) {
        this.maeTipoContratoCodigo = maeTipoContratoCodigo;
    }

    public String getMaeTipoContratoValor() {
        return maeTipoContratoValor;
    }

    public void setMaeTipoContratoValor(String maeTipoContratoValor) {
        this.maeTipoContratoValor = maeTipoContratoValor;
    }

    public int getMaeRegimenId() {
        return maeRegimenId;
    }

    public void setMaeRegimenId(int maeRegimenId) {
        this.maeRegimenId = maeRegimenId;
    }

    public String getMaeRegimenCodigo() {
        return maeRegimenCodigo;
    }

    public void setMaeRegimenCodigo(String maeRegimenCodigo) {
        this.maeRegimenCodigo = maeRegimenCodigo;
    }

    public String getMaeRegimenValor() {
        return maeRegimenValor;
    }

    public void setMaeRegimenValor(String maeRegimenValor) {
        this.maeRegimenValor = maeRegimenValor;
    }

    public int getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(int estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public CntPrestador getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    public void setCntPrestadoresId(CntPrestador cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
    }

    public int getIdGnEmpresas() {
        return idGnEmpresas;
    }

    public void setIdGnEmpresas(int idGnEmpresas) {
        this.idGnEmpresas = idGnEmpresas;
    }

   
    
    
}
