/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.contratacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jyperez
 */
public class CntContratoSede extends Auditoria {

    public final static int TIPO_CONTRATO_SUBSIDIADO = 1;
    public final static int TIPO_CONTRATO_CONTRIBUTIVO = 2;
    
    public final static String MODALIDAD_CAPITA = "01";
    public final static String MODALIDAD_EVENTO = "02";
    public final static String MODALIDAD_PGP= "03";
    public final static String MODALIDAD_PAQUETES= "04";
    public final static String MODALIDAD_COVID= "05";
    
    private Integer id;
    private Integer complejidad;
    private Integer numAfiliados;
    private int maeModalidadContratoId;
    private String maeModalidadContratoCodigo;
    private String maeModalidadContratoValor;
    private Integer nivelAtencion;
    private Date fechaInicio;
    private Date fechaFin;
    private BigDecimal valorUpcAfiliado;
    private BigDecimal valorContrato;
    private String observacion;
    private Boolean aplicaSubsidiado;
    private Boolean aplicaContribuitivo;
    private Boolean aplicaPac;
    private Boolean aplicaGlosaExtemporanea;
    private Boolean aplicaAuditoria;
    private Boolean aplicaPortabilidad;
    private Boolean aplicaAgendamiento;
    private Boolean aplicaAutorizacion;
    private Boolean aplicaRecaudoCopagosIps;
    private CntContrato cntContrato;
    private CntPrestadorSede cntPrestadorSede;
    private List<CntContratoDetalle> cntContratoDetalle;
    // campos Contratacion
    private boolean nuevoRegistro;
    private boolean editado;

    //2022-07-14 jyperez campos cobertura
    private List<CntContratoCobertura> listaCntContratoCobertura;
    private List<Integer> seleccion;
    
    public CntContratoSede() {
        seleccion = new ArrayList();
        listaCntContratoCobertura = new ArrayList();
        nuevoRegistro = false;
        editado = false;
    }

    public CntContratoSede(Integer id) {
        seleccion = new ArrayList();
        listaCntContratoCobertura = new ArrayList();
        this.id = id;
        nuevoRegistro = false;
        editado = false;
    }

    public CntContratoSede(Integer id, int maeModalidadContratoId, Date fechaInicio, Date fechaFin) {
        seleccion = new ArrayList();
        listaCntContratoCobertura = new ArrayList();
        this.id = id;
        this.maeModalidadContratoId = maeModalidadContratoId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        nuevoRegistro = false;
        editado = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getComplejidad() {
        return complejidad;
    }

    public void setComplejidad(Integer complejidad) {
        this.complejidad = complejidad;
    }

    public Integer getNumAfiliados() {
        return numAfiliados;
    }

    public void setNumAfiliados(Integer numAfiliados) {
        this.numAfiliados = numAfiliados;
    }

    public int getMaeModalidadContratoId() {
        return maeModalidadContratoId;
    }

    public void setMaeModalidadContratoId(int maeModalidadContratoId) {
        this.maeModalidadContratoId = maeModalidadContratoId;
    }

    public String getMaeModalidadContratoCodigo() {
        return maeModalidadContratoCodigo;
    }

    public void setMaeModalidadContratoCodigo(String maeModalidadContratoCodigo) {
        this.maeModalidadContratoCodigo = maeModalidadContratoCodigo;
    }

    public String getMaeModalidadContratoValor() {
        return maeModalidadContratoValor;
    }

    public void setMaeModalidadContratoValor(String maeModalidadContratoValor) {
        this.maeModalidadContratoValor = maeModalidadContratoValor;
    }

    public Integer getNivelAtencion() {
        return nivelAtencion;
    }

    public void setNivelAtencion(Integer nivelAtencion) {
        this.nivelAtencion = nivelAtencion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getValorUpcAfiliado() {
        return valorUpcAfiliado;
    }

    public void setValorUpcAfiliado(BigDecimal valorUpcAfiliado) {
        this.valorUpcAfiliado = valorUpcAfiliado;
    }

    public BigDecimal getValorContrato() {
        return valorContrato;
    }

    public void setValorContrato(BigDecimal valorContrato) {
        this.valorContrato = valorContrato;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Boolean getAplicaSubsidiado() {
        return aplicaSubsidiado;
    }

    public void setAplicaSubsidiado(Boolean aplicaSubsidiado) {
        this.aplicaSubsidiado = aplicaSubsidiado;
    }

    public Boolean getAplicaContribuitivo() {
        return aplicaContribuitivo;
    }

    public void setAplicaContribuitivo(Boolean aplicaContribuitivo) {
        this.aplicaContribuitivo = aplicaContribuitivo;
    }

    public Boolean getAplicaPac() {
        return aplicaPac;
    }

    public void setAplicaPac(Boolean aplicaPac) {
        this.aplicaPac = aplicaPac;
    }

    public Boolean getAplicaGlosaExtemporanea() {
        return aplicaGlosaExtemporanea;
    }

    public void setAplicaGlosaExtemporanea(Boolean aplicaGlosaExtemporanea) {
        this.aplicaGlosaExtemporanea = aplicaGlosaExtemporanea;
    }

    public Boolean getAplicaAuditoria() {
        return aplicaAuditoria;
    }

    public void setAplicaAuditoria(Boolean aplicaAuditoria) {
        this.aplicaAuditoria = aplicaAuditoria;
    }

    public Boolean getAplicaPortabilidad() {
        return aplicaPortabilidad;
    }

    public void setAplicaPortabilidad(Boolean aplicaPortabilidad) {
        this.aplicaPortabilidad = aplicaPortabilidad;
    }

    public Boolean getAplicaAgendamiento() {
        return aplicaAgendamiento;
    }

    public void setAplicaAgendamiento(Boolean aplicaAgendamiento) {
        this.aplicaAgendamiento = aplicaAgendamiento;
    }

    public Boolean getAplicaAutorizacion() {
        return aplicaAutorizacion;
    }

    public void setAplicaAutorizacion(Boolean aplicaAutorizacion) {
        this.aplicaAutorizacion = aplicaAutorizacion;
    }

    public Boolean getAplicaRecaudoCopagosIps() {
        return aplicaRecaudoCopagosIps;
    }

    public void setAplicaRecaudoCopagosIps(Boolean aplicaRecaudoCopagosIps) {
        this.aplicaRecaudoCopagosIps = aplicaRecaudoCopagosIps;
    }

    /**
     * @return the cntContrato
     */
    public CntContrato getCntContrato() {
        return cntContrato;
    }

    /**
     * @param cntContrato the cntContrato to set
     */
    public void setCntContrato(CntContrato cntContrato) {
        this.cntContrato = cntContrato;
    }

    /**
     * @return the cntPrestadorSede
     */
    public CntPrestadorSede getCntPrestadorSede() {
        return cntPrestadorSede;
    }

    /**
     * @param cntPrestadorSede the cntPrestadorSede to set
     */
    public void setCntPrestadorSede(CntPrestadorSede cntPrestadorSede) {
        this.cntPrestadorSede = cntPrestadorSede;
    }

    /**
     * @return the cntContratoDetalle
     */
    public List<CntContratoDetalle> getCntContratoDetalle() {
        return cntContratoDetalle;
    }

    /**
     * @param cntContratoDetalle the cntContratoDetalle to set
     */
    public void setCntContratoDetalle(List<CntContratoDetalle> cntContratoDetalle) {
        this.cntContratoDetalle = cntContratoDetalle;
    }

    /**
     * @return the nuevoRegistro
     */
    public boolean isNuevoRegistro() {
        return nuevoRegistro;
    }

    /**
     * @param nuevoRegistro the nuevoRegistro to set
     */
    public void setNuevoRegistro(boolean nuevoRegistro) {
        this.nuevoRegistro = nuevoRegistro;
    }

    /**
     * @return the editado
     */
    public boolean isEditado() {
        return editado;
    }

    /**
     * @param editado the editado to set
     */
    public void setEditado(boolean editado) {
        this.editado = editado;
    }

    @Override
    public String toString() {
        String mensaje = "CntContratoSede{" + "id=" + id + ", complejidad=" + complejidad + ", numAfiliados=" + numAfiliados + ", maeModalidadContratoId=" + maeModalidadContratoId + ", maeModalidadContratoCodigo=" + maeModalidadContratoCodigo + ", maeModalidadContratoValor=" + maeModalidadContratoValor + ", nivelAtencion=" + nivelAtencion + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", valorUpcAfiliado=" + valorUpcAfiliado + ", valorContrato=" + valorContrato + ", observacion=" + observacion + ", aplicaSubsidiado=" + aplicaSubsidiado + ", aplicaContribuitivo=" + aplicaContribuitivo + ", aplicaPac=" + aplicaPac + ", aplicaGlosaExtemporanea=" + aplicaGlosaExtemporanea + ", aplicaAuditoria=" + aplicaAuditoria + ", aplicaPortabilidad=" + aplicaPortabilidad + ", aplicaAgendamiento=" + aplicaAgendamiento + ", aplicaAutorizacion=" + aplicaAutorizacion + ", aplicaRecaudoCopagosIps=" + aplicaRecaudoCopagosIps + ", cntContrato=";
        if (cntContrato != null && cntContrato.getId() != null) {
            mensaje = mensaje + cntContrato.getId() + ", cntPrestadorSede=" + cntPrestadorSede + ", cntContratoDetalle=" + cntContratoDetalle + ", nuevoRegistro=" + nuevoRegistro + ", editado=" + editado + '}';
        } else {
            mensaje = mensaje +  "null, cntPrestadorSede=" + cntPrestadorSede + ", cntContratoDetalle=" + cntContratoDetalle + ", nuevoRegistro=" + nuevoRegistro + ", editado=" + editado + '}';
        }
        return mensaje;
    }
    
    /**
     * Se obtiene una texto de los municipios de Cobertura separados por coma
     * @return 
     */
    public String getCoberturas(){
        String mensaje = "";
        if (this.listaCntContratoCobertura != null && !this.listaCntContratoCobertura.isEmpty()) {
            for (CntContratoCobertura cobertura: this.listaCntContratoCobertura) {
                if (mensaje.equals("")) {
                    mensaje = cobertura.getUbicacion().getNombreDepartamentoCiudad();
                } else {
                    mensaje = mensaje + "," + cobertura.getUbicacion().getNombreDepartamentoCiudad();
                }
            }
        }
        return mensaje;
    }

    /**
     * @return the listaCntContratoCobertura
     */
    public List<CntContratoCobertura> getListaCntContratoCobertura() {
        return listaCntContratoCobertura;
    }

    /**
     * @param listaCntContratoCobertura the listaCntContratoCobertura to set
     */
    public void setListaCntContratoCobertura(List<CntContratoCobertura> listaCntContratoCobertura) {
        this.listaCntContratoCobertura = listaCntContratoCobertura;
    }

    /**
     * @return the seleccion
     */
    public List<Integer> getSeleccion() {
        return seleccion;
    }

    /**
     * @param seleccion the seleccion to set
     */
    public void setSeleccion(List<Integer> seleccion) {
        this.seleccion = seleccion;
    }
}
