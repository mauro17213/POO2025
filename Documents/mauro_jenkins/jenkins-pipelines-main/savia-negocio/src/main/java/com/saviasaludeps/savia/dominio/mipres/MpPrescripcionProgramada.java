/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author Jaime Andres Olarte
 */
public class MpPrescripcionProgramada extends Auditoria {
    
    private Integer id;
    private Integer maeTipoDocumentoPrestadorId;
    private String maeTipoDocumentoPrestadorCodigo;
    private String maeTipoDocumentoPrestadorValor;
    private String prestadorNumeroDocumento;
    private String prestadorRazonSocial;
    private String sedeCodigoHabilitacion;
    private String sedeDireccion;
    private String sedeTelefono;
    private int tipoTecnologia;
    private Integer entregaNumero;
    private Integer entregaCantidad;
    private Integer entregaTotal;
    private Integer entregadoNumero;
    private Integer entregadoTotal;
    private Integer entregadoPendiente;
    private Integer idTransaccion;
    private Integer idDireccionamiento;
    private Integer estado;
    private Date fechaMaximaEntrega;
    private Date fechaDireccionamiento;
    private MpPrescripcionInsumo mpPrescripcionInsumos;
    private MpPrescripcion mpPrescripcion;
    private MpPrescripcionMedicamento mpPrescripcionMedicamentos;
    private MpPrescripcionTecnologia mpPrescripcionTecnologias;

    public MpPrescripcionProgramada() {
    }    

    public MpPrescripcionProgramada(Integer id) {
        this.id = id;
    }    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaeTipoDocumentoPrestadorId() {
        return maeTipoDocumentoPrestadorId;
    }

    public void setMaeTipoDocumentoPrestadorId(Integer maeTipoDocumentoPrestadorId) {
        this.maeTipoDocumentoPrestadorId = maeTipoDocumentoPrestadorId;
    }

    public String getMaeTipoDocumentoPrestadorCodigo() {
        return maeTipoDocumentoPrestadorCodigo;
    }

    public void setMaeTipoDocumentoPrestadorCodigo(String maeTipoDocumentoPrestadorCodigo) {
        this.maeTipoDocumentoPrestadorCodigo = maeTipoDocumentoPrestadorCodigo;
    }

    public String getMaeTipoDocumentoPrestadorValor() {
        return maeTipoDocumentoPrestadorValor;
    }

    public void setMaeTipoDocumentoPrestadorValor(String maeTipoDocumentoPrestadorValor) {
        this.maeTipoDocumentoPrestadorValor = maeTipoDocumentoPrestadorValor;
    }

    public String getPrestadorNumeroDocumento() {
        return prestadorNumeroDocumento;
    }

    public void setPrestadorNumeroDocumento(String prestadorNumeroDocumento) {
        this.prestadorNumeroDocumento = prestadorNumeroDocumento;
    }

    public String getPrestadorRazonSocial() {
        return prestadorRazonSocial;
    }

    public void setPrestadorRazonSocial(String prestadorRazonSocial) {
        this.prestadorRazonSocial = prestadorRazonSocial;
    }

    public String getSedeCodigoHabilitacion() {
        return sedeCodigoHabilitacion;
    }

    public void setSedeCodigoHabilitacion(String sedeCodigoHabilitacion) {
        this.sedeCodigoHabilitacion = sedeCodigoHabilitacion;
    }

    public String getSedeDireccion() {
        return sedeDireccion;
    }

    public void setSedeDireccion(String sedeDireccion) {
        this.sedeDireccion = sedeDireccion;
    }

    public String getSedeTelefono() {
        return sedeTelefono;
    }

    public void setSedeTelefono(String sedeTelefono) {
        this.sedeTelefono = sedeTelefono;
    }

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public Integer getEntregaNumero() {
        return entregaNumero;
    }

    public void setEntregaNumero(Integer entregaNumero) {
        this.entregaNumero = entregaNumero;
    }

    public Integer getEntregaCantidad() {
        return entregaCantidad;
    }

    public void setEntregaCantidad(Integer entregaCantidad) {
        this.entregaCantidad = entregaCantidad;
    }

    public Integer getEntregaTotal() {
        return entregaTotal;
    }

    public void setEntregaTotal(Integer entregaTotal) {
        this.entregaTotal = entregaTotal;
    }

    public Integer getEntregadoNumero() {
        return entregadoNumero;
    }

    public void setEntregadoNumero(Integer entregadoNumero) {
        this.entregadoNumero = entregadoNumero;
    }

    public Integer getEntregadoTotal() {
        return entregadoTotal;
    }

    public void setEntregadoTotal(Integer entregadoTotal) {
        this.entregadoTotal = entregadoTotal;
    }

    public Integer getEntregadoPendiente() {
        return entregadoPendiente;
    }

    public void setEntregadoPendiente(Integer entregadoPendiente) {
        this.entregadoPendiente = entregadoPendiente;
    }

    public MpPrescripcionInsumo getMpPrescripcionInsumos() {
        return mpPrescripcionInsumos;
    }

    public void setMpPrescripcionInsumos(MpPrescripcionInsumo mpPrescripcionInsumos) {
        this.mpPrescripcionInsumos = mpPrescripcionInsumos;
    }

    public MpPrescripcionMedicamento getMpPrescripcionMedicamentos() {
        return mpPrescripcionMedicamentos;
    }

    public void setMpPrescripcionMedicamentos(MpPrescripcionMedicamento mpPrescripcionMedicamentos) {
        this.mpPrescripcionMedicamentos = mpPrescripcionMedicamentos;
    }

    public MpPrescripcionTecnologia getMpPrescripcionTecnologias() {
        return mpPrescripcionTecnologias;
    }

    public void setMpPrescripcionTecnologias(MpPrescripcionTecnologia mpPrescripcionTecnologias) {
        this.mpPrescripcionTecnologias = mpPrescripcionTecnologias;
    }

    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Integer getIdDireccionamiento() {
        return idDireccionamiento;
    }

    public void setIdDireccionamiento(Integer idDireccionamiento) {
        this.idDireccionamiento = idDireccionamiento;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getFechaMaximaEntrega() {
        return fechaMaximaEntrega;
    }

    public void setFechaMaximaEntrega(Date fechaMaximaEntrega) {
        this.fechaMaximaEntrega = fechaMaximaEntrega;
    }

    public Date getFechaDireccionamiento() {
        return fechaDireccionamiento;
    }

    public void setFechaDireccionamiento(Date fechaDireccionamiento) {
        this.fechaDireccionamiento = fechaDireccionamiento;
    }

    public MpPrescripcion getMpPrescripcion() {
        return mpPrescripcion;
    }

    public void setMpPrescripcion(MpPrescripcion mpPrescripcion) {
        this.mpPrescripcion = mpPrescripcion;
    }
    
    
}
