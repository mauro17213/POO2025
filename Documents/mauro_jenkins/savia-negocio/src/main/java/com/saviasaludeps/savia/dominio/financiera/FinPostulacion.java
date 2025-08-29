package com.saviasaludeps.savia.dominio.financiera;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author jeperez
 */
public class FinPostulacion extends Auditoria implements Serializable {
    
    public static final int TIPO_GIRO_DIRECTO_SUBSIDIADO     = 1;
    public static final int TIPO_GIRO_DIRECTO_CONTRIBUTIVO   = 2;
    public static final int TIPO_GIRO_DIRECTO_PRESUPUESTOS_MAXIMOS = 3;
    public static final int TIPO_OTROS_PAGOS  = 4;
    

    private Integer id;
    private int tipoPostulacion;
    private String prestadorNit;
    private String prestadorRazonSocial;
    private String prestadorDepartamento;
    private String prestadorMunicipio;
    private String prestadorNaturaleza;
    private String prestadorEstadoAdres;
    private String postulacionTexto;
    private String postulacionTextoError;
    private BigDecimal valorCapita;
    private BigDecimal valorCapitaReajuste;
    private BigDecimal valorPgp;
    private BigDecimal valorCompromisos;
    private BigDecimal valorEvento;
    private BigDecimal valorProgramadoTotal;
    private boolean borrado;
    private BigDecimal totalPagado;
    private String usuarioBorra;
    private String terminalBorra;
    private Date fechaHoraBorra;
    private CntPrestador cntPrestador;
    private FinGiro finGiro;
    private Empresa empresa;
    

    public FinPostulacion() {
    }


    
    

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipoPostulacion() {
        return tipoPostulacion;
    }

    public void setTipoPostulacion(int tipoPostulacion) {
        this.tipoPostulacion = tipoPostulacion;
    }

    public String getPrestadorNit() {
        return prestadorNit;
    }

    public void setPrestadorNit(String prestadorNit) {
        this.prestadorNit = prestadorNit;
    }

    public String getPrestadorRazonSocial() {
        return prestadorRazonSocial;
    }

    public void setPrestadorRazonSocial(String prestadorRazonSocial) {
        this.prestadorRazonSocial = prestadorRazonSocial;
    }

    public String getPrestadorDepartamento() {
        return prestadorDepartamento;
    }

    public void setPrestadorDepartamento(String prestadorDepartamento) {
        this.prestadorDepartamento = prestadorDepartamento;
    }

    public String getPrestadorMunicipio() {
        return prestadorMunicipio;
    }

    public void setPrestadorMunicipio(String prestadorMunicipio) {
        this.prestadorMunicipio = prestadorMunicipio;
    }

    public String getPrestadorNaturaleza() {
        return prestadorNaturaleza;
    }

    public void setPrestadorNaturaleza(String prestadorNaturaleza) {
        this.prestadorNaturaleza = prestadorNaturaleza;
    }

    public String getPrestadorEstadoAdres() {
        return prestadorEstadoAdres;
    }

    public void setPrestadorEstadoAdres(String prestadorEstadoAdres) {
        this.prestadorEstadoAdres = prestadorEstadoAdres;
    }

    public BigDecimal getValorCapita() {
        return valorCapita;
    }

    public void setValorCapita(BigDecimal valorCapita) {
        this.valorCapita = valorCapita;
    }

    public BigDecimal getValorCapitaReajuste() {
        return valorCapitaReajuste;
    }

    public void setValorCapitaReajuste(BigDecimal valorCapitaReajuste) {
        this.valorCapitaReajuste = valorCapitaReajuste;
    }

    public BigDecimal getValorPgp() {
        return valorPgp;
    }

    public void setValorPgp(BigDecimal valorPgp) {
        this.valorPgp = valorPgp;
    }

    public BigDecimal getValorCompromisos() {
        return valorCompromisos;
    }

    public void setValorCompromisos(BigDecimal valorCompromisos) {
        this.valorCompromisos = valorCompromisos;
    }

    public BigDecimal getValorEvento() {
        return valorEvento;
    }

    public void setValorEvento(BigDecimal valorEvento) {
        this.valorEvento = valorEvento;
    }

    public BigDecimal getValorProgramadoTotal() {
        return valorProgramadoTotal;
    }

    public void setValorProgramadoTotal(BigDecimal valorProgramadoTotal) {
        this.valorProgramadoTotal = valorProgramadoTotal;
    }

    public boolean isBorrado() {
        return borrado;
    }
    
    public boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
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

    public CntPrestador getCntPrestador() {
        return cntPrestador;
    }

    public void setCntPrestador(CntPrestador cntPrestador) {
        this.cntPrestador = cntPrestador;
    }

    public FinGiro getFinGiro() {
        return finGiro;
    }

    public void setFinGiro(FinGiro finGiro) {
        this.finGiro = finGiro;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public BigDecimal getTotalPagado() {
        return totalPagado;
    }

    public void setTotalPagado(BigDecimal totalPagado) {
        this.totalPagado = totalPagado;
    }

    public String getPostulacionTexto() {
        return postulacionTexto;
    }

    public void setPostulacionTexto(String postulacionTexto) {
        this.postulacionTexto = postulacionTexto;
    }

    public String getPostulacionTextoError() {
        return postulacionTextoError;
    }

    public void setPostulacionTextoError(String postulacionTextoError) {
        this.postulacionTextoError = postulacionTextoError;
    }
    
    public boolean getNoHayPosulacionTextoError(){
        return getPostulacionTextoError()== null || 
               getPostulacionTextoError().equals("");
    }
   
    public String getTipoPostulacionStr() {
        return FinPostulacion.getTipoPostulacionStr(getTipoPostulacion());
    }
    
    public static String getTipoPostulacionStr(int tipo) {
        switch (tipo) {
            case TIPO_GIRO_DIRECTO_CONTRIBUTIVO:
                return "Giro directo contributivo ";
            case TIPO_GIRO_DIRECTO_PRESUPUESTOS_MAXIMOS:
                return "Giro directo presupuestos máximos ";
            case TIPO_GIRO_DIRECTO_SUBSIDIADO:
                return "Giro directo subsidiado ";
                 case TIPO_OTROS_PAGOS:
                return "Otros Pagos ";
            default:
                return "";
        }
    }

    @Override
    public String toString() {
        return "FinPostulacion{" + "id=" + id + ", tipoPostulacion=" + tipoPostulacion + ", prestadorNit=" + prestadorNit + ", prestadorRazonSocial=" + prestadorRazonSocial + ", prestadorDepartamento=" + prestadorDepartamento + ", prestadorMunicipio=" + prestadorMunicipio + ", prestadorNaturaleza=" + prestadorNaturaleza + ", prestadorEstadoAdres=" + prestadorEstadoAdres + ", valorCapita=" + valorCapita + ", valorCapitaReajuste=" + valorCapitaReajuste + ", valorPgp=" + valorPgp + ", valorCompromisos=" + valorCompromisos + ", valorEvento=" + valorEvento + ", valorProgramadoTotal=" + valorProgramadoTotal + ", borrado=" + borrado + ", usuarioBorra=" + usuarioBorra + ", terminalBorra=" + terminalBorra + ", fechaHoraBorra=" + fechaHoraBorra + '}';
    }
    
    
}
