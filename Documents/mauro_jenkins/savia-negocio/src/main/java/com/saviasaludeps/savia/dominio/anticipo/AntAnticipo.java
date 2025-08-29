/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.anticipo;

import com.saviasaludeps.savia.dominio.administracion.*;
import com.saviasaludeps.savia.dominio.aseguramiento.AsegAfiliado;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author rpalacic
 */
public class AntAnticipo extends Auditoria {
    //estados
    public static final int ESTADO_PENDIENTE_COTIZACION = 1;
    public static final int ESTADO_CON_COTIZACION = 2;
    public static final int ESTADO_GESTION_FIRMAS = 3;
    public static final int ESTADO_AUTORIZADO = 4;
    public static final int ESTADO_GESTION_TESORERIA = 5;
    public static final int ESTADO_PAGADO = 6;
    public static final int ESTADO_DEVUELTO = 7;
    public static final int ESTADO_CANCELADO = 8;
    //tipo
    public static final int TIPO_INDIVIDUAL = 1;
    public static final int TIPO_PAQUETE = 2;
    //validacion retencion porcentaje
    public static final int PORCENTAJE_RETENCION_SUGERIDA  = 100;
    
    private Integer id;
    private Integer estado;
    private Integer tipo;
    private boolean pbs;
    
    private Integer maeClasificacionId;
    private String maeClasificacionCodigo;
    private String maeClasificacionValor;
    private String maeClasificacionTipo;
    private String justificacion;
    private BigDecimal valorCotizado;
    private BigDecimal valorPagado;
    private BigDecimal valorDisponible;
    private BigDecimal retencionSugerida;
    private AsegAfiliado asegAfiliadosId;
    private CntPrestadorSede cntPrestadorSedesId;
    private CntPrestador cntPrestadoresId;
    private Empresa gnEmpresasId;
    private Usuario gnUsuariosId;
    private Integer maDiagnosticoId;
    private String maDiagnosticoCodigo;
    private String maDiagnosticoValor;
    private String clasificacionObservacion;
    private String codigoCompensacionSap;
    private String codigoContabilizacionSap;
    private BigDecimal retencionAplicada;
    private boolean aplicaRetencion;
    private String observacionDevolver;
    private String observacionPago;
    private String observacionCancelar;
    private String observacionAutorizar;
    private List<AntAnticipoAdjunto> antAnticipoAdjuntosList;
    private List<AntAnticipoGestion> antAnticipoGestionesList;
    private List<AntAnticipoItem> antAnticipoItemsList;
    private List<AntAnticipoValor> antAnticipoValoresList;
    // variables adicionales
    private boolean habilitarGestionar;
    private boolean habilitarRetecionSugerida;
    
    public AntAnticipo() {
    }

    public AntAnticipo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public boolean isPbs() {
        return pbs;
    }

    public void setPbs(boolean pbs) {
        this.pbs = pbs;
    }

    public String getMaeClasificacionCodigo() {
        return maeClasificacionCodigo;
    }

    public void setMaeClasificacionCodigo(String maeClasificacionCodigo) {
        this.maeClasificacionCodigo = maeClasificacionCodigo;
    }

    public String getMaeClasificacionValor() {
        return maeClasificacionValor;
    }

    public void setMaeClasificacionValor(String maeClasificacionValor) {
        this.maeClasificacionValor = maeClasificacionValor;
    }

    public String getMaeClasificacionTipo() {
        return maeClasificacionTipo;
    }

    public void setMaeClasificacionTipo(String maeClasificacionTipo) {
        this.maeClasificacionTipo = maeClasificacionTipo;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public BigDecimal getValorCotizado() {
        return valorCotizado;
    }

    public void setValorCotizado(BigDecimal valorCotizado) {
        this.valorCotizado = valorCotizado;
    }

    public BigDecimal getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(BigDecimal valorPagado) {
        this.valorPagado = valorPagado;
    }

    public BigDecimal getValorDisponible() {
        return valorDisponible;
    }

    public void setValorDisponible(BigDecimal valorDisponible) {
        this.valorDisponible = valorDisponible;
    }

    public BigDecimal getRetencionSugerida() {
        return retencionSugerida;
    }

    public void setRetencionSugerida(BigDecimal retencionSugerida) {
        this.retencionSugerida = retencionSugerida;
    }

    public AsegAfiliado getAsegAfiliadosId() {
        return asegAfiliadosId;
    }

    public void setAsegAfiliadosId(AsegAfiliado asegAfiliadosId) {
        this.asegAfiliadosId = asegAfiliadosId;
    }

    public CntPrestadorSede getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSede cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    public CntPrestador getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    public void setCntPrestadoresId(CntPrestador cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
    }

    public Empresa getGnEmpresasId() {
        return gnEmpresasId;
    }

    public void setGnEmpresasId(Empresa gnEmpresasId) {
        this.gnEmpresasId = gnEmpresasId;
    }

    public Usuario getGnUsuariosId() {
        return gnUsuariosId;
    }

    public void setGnUsuariosId(Usuario gnUsuariosId) {
        this.gnUsuariosId = gnUsuariosId;
    }

    public Integer getMaDiagnosticoId() {
        return maDiagnosticoId;
    }

    public void setMaDiagnosticoId(Integer maDiagnosticoId) {
        this.maDiagnosticoId = maDiagnosticoId;
    }

    public String getMaDiagnosticoCodigo() {
        return maDiagnosticoCodigo;
    }

    public void setMaDiagnosticoCodigo(String maDiagnosticoCodigo) {
        this.maDiagnosticoCodigo = maDiagnosticoCodigo;
    }

    public String getMaDiagnosticoValor() {
        return maDiagnosticoValor;
    }

    public void setMaDiagnosticoValor(String maDiagnosticoValor) {
        this.maDiagnosticoValor = maDiagnosticoValor;
    }

    public String getClasificacionObservacion() {
        return clasificacionObservacion;
    }

    public void setClasificacionObservacion(String clasificacionObservacion) {
        this.clasificacionObservacion = clasificacionObservacion;
    }

    public String getCodigoCompensacionSap() {
        return codigoCompensacionSap;
    }

    public void setCodigoCompensacionSap(String codigoCompensacionSap) {
        this.codigoCompensacionSap = codigoCompensacionSap;
    }

    public String getCodigoContabilizacionSap() {
        return codigoContabilizacionSap;
    }

    public void setCodigoContabilizacionSap(String codigoContabilizacionSap) {
        this.codigoContabilizacionSap = codigoContabilizacionSap;
    }

    public BigDecimal getRetencionAplicada() {
        return retencionAplicada;
    }

    public void setRetencionAplicada(BigDecimal retencionAplicada) {
        this.retencionAplicada = retencionAplicada;
    }

    public boolean isAplicaRetencion() {
        return aplicaRetencion;
    }

    public void setAplicaRetencion(boolean aplicaRetencion) {
        this.aplicaRetencion = aplicaRetencion;
    }

    public String getObservacionDevolver() {
        return observacionDevolver;
    }

    public void setObservacionDevolver(String observacionDevolver) {
        this.observacionDevolver = observacionDevolver;
    }

    public String getObservacionPago() {
        return observacionPago;
    }

    public void setObservacionPago(String observacionPago) {
        this.observacionPago = observacionPago;
    }

    public String getObservacionCancelar() {
        return observacionCancelar;
    }

    public void setObservacionCancelar(String observacionCancelar) {
        this.observacionCancelar = observacionCancelar;
    }

    public String getObservacionAutorizar() {
        return observacionAutorizar;
    }

    public void setObservacionAutorizar(String observacionAutorizar) {
        this.observacionAutorizar = observacionAutorizar;
    }

    public Integer getMaeClasificacionId() {
        return maeClasificacionId;
    }

    public void setMaeClasificacionId(Integer maeClasificacionId) {
        this.maeClasificacionId = maeClasificacionId;
    }

    public List<AntAnticipoAdjunto> getAntAnticipoAdjuntosList() {
        return antAnticipoAdjuntosList;
    }

    public void setAntAnticipoAdjuntosList(List<AntAnticipoAdjunto> antAnticipoAdjuntosList) {
        this.antAnticipoAdjuntosList = antAnticipoAdjuntosList;
    }

    public List<AntAnticipoGestion> getAntAnticipoGestionesList() {
        return antAnticipoGestionesList;
    }

    public void setAntAnticipoGestionesList(List<AntAnticipoGestion> antAnticipoGestionesList) {
        this.antAnticipoGestionesList = antAnticipoGestionesList;
    }

    public List<AntAnticipoItem> getAntAnticipoItemsList() {
        return antAnticipoItemsList;
    }

    public void setAntAnticipoItemsList(List<AntAnticipoItem> antAnticipoItemsList) {
        this.antAnticipoItemsList = antAnticipoItemsList;
    }

    public List<AntAnticipoValor> getAntAnticipoValoresList() {
        return antAnticipoValoresList;
    }

    public void setAntAnticipoValoresList(List<AntAnticipoValor> antAnticipoValoresList) {
        this.antAnticipoValoresList = antAnticipoValoresList;
    }

    public boolean isHabilitarGestionar() {
        return habilitarGestionar;
    }

    public void setHabilitarGestionar(boolean habilitarGestionar) {
        this.habilitarGestionar = habilitarGestionar;
    }

    public boolean isHabilitarRetecionSugerida() {
        return habilitarRetecionSugerida;
    }

    public void setHabilitarRetecionSugerida(boolean habilitarRetecionSugerida) {
        this.habilitarRetecionSugerida = habilitarRetecionSugerida;
    }
    
    //metodos
    public String getTipoStr(){
        String nombreTipo = "";
        if(tipo != null){
            switch(tipo){
            case TIPO_INDIVIDUAL:
                nombreTipo = "Individual";
                break;
            case TIPO_PAQUETE:
                nombreTipo = "Paquete";
                break;
            default:
                break;
            }  
        }
        return nombreTipo;
    }
   
    public String getEstadoStr(){
        String nombreEstado = "";
        if(estado != null){
            switch(estado){
                case ESTADO_PENDIENTE_COTIZACION:
                    nombreEstado = "Pendiente Cotización";
                    break;
                case ESTADO_CON_COTIZACION:
                    nombreEstado = "Con Cotizacion";
                    break;
                case ESTADO_GESTION_FIRMAS:
                    nombreEstado = "Gestion Firmas";
                    break;
                case ESTADO_AUTORIZADO:
                    nombreEstado = "Autorizado";
                    break;
                case ESTADO_GESTION_TESORERIA:
                    nombreEstado = "Gestion Tesorería";
                    break;
                case ESTADO_PAGADO:
                    nombreEstado = "Pagado";
                    break;
                case ESTADO_DEVUELTO:
                    nombreEstado = "Devuelto";
                    break;
                case ESTADO_CANCELADO:
                    nombreEstado = "Cancelado";
                    break;
                default:
                    break;
            }     
        }
        return nombreEstado;
    }
    
    public String getPbsStr(){
        String nombrePbs = "No";
        if(pbs){
            nombrePbs = "Si";
        }
        return nombrePbs;
    }

    public String getAplicaRetencionStr(){
        String aplicaRetencionStr = "No";
        if(aplicaRetencion){
            aplicaRetencionStr = "Si";
        }
        return aplicaRetencionStr;
    }
    
    @Override
    public String toString() {
        return "AntAnticipo{" + "id=" + id + ", estado=" + estado + ", tipo=" + tipo + ", pbs=" + pbs + ", maeClasificacionId=" + maeClasificacionId + ", maeClasificacionCodigo=" + maeClasificacionCodigo + ", maeClasificacionValor=" + maeClasificacionValor + ", maeClasificacionTipo=" + maeClasificacionTipo + ", justificacion=" + justificacion + ", valorCotizado=" + valorCotizado + ", valorPagado=" + valorPagado + ", valorDisponible=" + valorDisponible + ", retencionSugerida=" + retencionSugerida + ", asegAfiliadosId=" + asegAfiliadosId + ", cntPrestadorSedesId=" + cntPrestadorSedesId + ", cntPrestadoresId=" + cntPrestadoresId + ", gnEmpresasId=" + gnEmpresasId + ", gnUsuariosId=" + gnUsuariosId + '}';
    }
}
