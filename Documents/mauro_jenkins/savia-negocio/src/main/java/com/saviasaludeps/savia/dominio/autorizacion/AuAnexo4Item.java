/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.crue.AuAnexo2Item;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Stiven Giraldo
 */
public class AuAnexo4Item extends Auditoria {

    //Tipos
    public static final String TIPO_MEDICAMENTO = "Medicamento";
    public static final String TIPO_TECNOLOGIA = "Procedimiento";
    public static final String TIPO_INSUMO = "Insumo";
    public static final String TIPO_PAQUETE = "Paquete";
    public static final String TIPO_AGRUPADOR_MEDICAMENTO = "Agrupador Medicamento";
    
    public static final int TIPO_ENTREGA_NO_PRESTADO = 5;
    
    private Integer id;
    private int tipoTecnologia;
    private int maTecnologiaId;
    private String maTecnologiaCodigo;
    private String maTecnologiaValor;
    private int maMedicamentoId;
    private String maMedicamentoCodigo;
    private String maMedicamentoValor;
    private int cantidadAutorizada;
    private BigDecimal costoServicio;
    private AuAnexo2Item auAnexo2ItemId;
    private AuAnexo3Item auAnexo3ItemId;
    private AuAnexo4 auAnexo4Id;
    private List<AuAnexo4Entrega> auAnexo4EntregasList;
    private CntContratoDetalle cntContratoDetalle;
    private AuCotizacion auCotizacion;
    private String entregaObservacion;
    private int entrega;
    
    //Auxiliares
    private int cantidadPendiente;
    private boolean entregada;
    private String estadoEntrega;
    private boolean anularEntrega;
    private Date fechaPrestacion;
    private Integer numeroFactura;
    
    private String fechaAutorizacionPeriodo;
    private Integer cantidadAutorizacionPeriodo;

    public AuAnexo4Item() {
    }

    public AuAnexo4Item(int id) {
        this.id = id;
    }
    
    public AuAnexo4Item(String fechaAutorizacionPeriodo, Integer cantidadAutorizacionPeriodo) {
        this.fechaAutorizacionPeriodo = fechaAutorizacionPeriodo;
        this.cantidadAutorizacionPeriodo = cantidadAutorizacionPeriodo;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(int tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public int getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(int maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public String getMaTecnologiaValor() {
        return maTecnologiaValor;
    }

    public void setMaTecnologiaValor(String maTecnologiaValor) {
        this.maTecnologiaValor = maTecnologiaValor;
    }

    public int getCantidadAutorizada() {
        return cantidadAutorizada;
    }

    public void setCantidadAutorizada(int cantidadAutorizada) {
        this.cantidadAutorizada = cantidadAutorizada;
    }

    public BigDecimal getCostoServicio() {
        return costoServicio;
    }

    public void setCostoServicio(BigDecimal costoServicio) {
        this.costoServicio = costoServicio;
    }

    public AuAnexo2Item getAuAnexo2ItemId() {
        return auAnexo2ItemId;
    }

    public void setAuAnexo2ItemId(AuAnexo2Item auAnexo2ItemId) {
        this.auAnexo2ItemId = auAnexo2ItemId;
    }

    public AuAnexo3Item getAuAnexo3ItemId() {
        return auAnexo3ItemId;
    }

    public void setAuAnexo3ItemId(AuAnexo3Item auAnexo3ItemId) {
        this.auAnexo3ItemId = auAnexo3ItemId;
    }

    public String getEntregaObservacion() {
        return entregaObservacion;
    }

    public void setEntregaObservacion(String entregaObservacion) {
        this.entregaObservacion = entregaObservacion;
    }

    public int getEntrega() {
        return entrega;
    }

    public void setEntrega(int entrega) {
        this.entrega = entrega;
    }

    public AuAnexo4 getAuAnexo4Id() {
        return auAnexo4Id;
    }

    public void setAuAnexo4Id(AuAnexo4 auAnexo4Id) {
        this.auAnexo4Id = auAnexo4Id;
    }

    public List<AuAnexo4Entrega> getAuAnexo4EntregasList() {
        return auAnexo4EntregasList;
    }

    public void setAuAnexo4EntregasList(List<AuAnexo4Entrega> auAnexo4EntregasList) {
        this.auAnexo4EntregasList = auAnexo4EntregasList;
    }

    public int getCantidadPendiente() {
        return cantidadPendiente;
    }

    public void setCantidadPendiente(int cantidadPendiente) {
        this.cantidadPendiente = cantidadPendiente;
    }

    public boolean isEntregada() {
        return entregada;
    }

    public void setEntregada(boolean entregada) {
        this.entregada = entregada;
    }

    public String getEstadoEntrega() {
        return estadoEntrega;
    }

    public void setEstadoEntrega(String estadoEntrega) {
        this.estadoEntrega = estadoEntrega;
    }

    public boolean isAnularEntrega() {
        return anularEntrega;
    }

    public void setAnularEntrega(boolean anularEntrega) {
        this.anularEntrega = anularEntrega;
    }

    public Date getFechaPrestacion() {
        if (fechaPrestacion != null) {
            return fechaPrestacion;
        } else {
            return new Date();
        }
    }

    public void setFechaPrestacion(Date fechaPrestacion) {
        this.fechaPrestacion = fechaPrestacion;
    }

    public Integer getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Integer numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public AuCotizacion getAuCotizacion() {
        return auCotizacion;
    }

    public void setAuCotizacion(AuCotizacion auCotizacion) {
        this.auCotizacion = auCotizacion;
    }

    public CntContratoDetalle getCntContratoDetalle() {
        return cntContratoDetalle;
    }

    public int getMaMedicamentoId() {
        return maMedicamentoId;
    }

    public void setMaMedicamentoId(int maMedicamentoId) {
        this.maMedicamentoId = maMedicamentoId;
    }

    public String getMaMedicamentoCodigo() {
        return maMedicamentoCodigo;
    }

    public void setMaMedicamentoCodigo(String maMedicamentoCodigo) {
        this.maMedicamentoCodigo = maMedicamentoCodigo;
    }

    public String getMaMedicamentoValor() {
        return maMedicamentoValor;
    }

    public void setMaMedicamentoValor(String maMedicamentoValor) {
        this.maMedicamentoValor = maMedicamentoValor;
    }

    public void setCntContratoDetalle(CntContratoDetalle cntContratoDetalle) {
        this.cntContratoDetalle = cntContratoDetalle;
    }

    public String getFechaAutorizacionPeriodo() {
        return fechaAutorizacionPeriodo;
    }

    public void setFechaAutorizacionPeriodo(String fechaAutorizacionPeriodo) {
        this.fechaAutorizacionPeriodo = fechaAutorizacionPeriodo;
    }

    public Integer getCantidadAutorizacionPeriodo() {
        return cantidadAutorizacionPeriodo;
    }

    public void setCantidadAutorizacionPeriodo(Integer cantidadAutorizacionPeriodo) {
        this.cantidadAutorizacionPeriodo = cantidadAutorizacionPeriodo;
    }

    public String obtenerTipoTecnologia() {
        switch (tipoTecnologia) {
            case 1:
                return TIPO_TECNOLOGIA;
            case 2:
                return TIPO_MEDICAMENTO;
            case 3:
                return TIPO_INSUMO;
            case 4:
                return TIPO_PAQUETE;
            case 5:
                return TIPO_AGRUPADOR_MEDICAMENTO;
        }
        return "";
    }

    @Override
    public String toString() {
        return "AuAnexo4Item{" + "id=" + id + ", tipoTecnologia=" + tipoTecnologia + ", maTecnologiaId=" + maTecnologiaId + ", maTecnologiaCodigo=" + maTecnologiaCodigo + ", maTecnologiaValor=" + maTecnologiaValor + ", maMedicamentoId=" + maMedicamentoId + ", maMedicamentoCodigo=" + maMedicamentoCodigo + ", maMedicamentoValor=" + maMedicamentoValor + ", cantidadAutorizada=" + cantidadAutorizada + ", costoServicio=" + costoServicio + ", entregaObservacion=" + entregaObservacion + ", entrega=" + entrega + ", cantidadPendiente=" + cantidadPendiente + ", entregada=" + entregada + ", estadoEntrega=" + estadoEntrega + ", anularEntrega=" + anularEntrega + ", fechaPrestacion=" + fechaPrestacion + ", numeroFactura=" + numeroFactura + '}';
    }
}
