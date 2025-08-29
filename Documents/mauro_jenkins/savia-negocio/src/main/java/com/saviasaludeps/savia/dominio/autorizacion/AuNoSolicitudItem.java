/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.contratacion.CntContratoDetalle;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author NEXOS
 */
public class AuNoSolicitudItem extends Auditoria {

    //estado
    public static final int ESTADO_PENDIENTE = 1;
    public static final int ESTADO_EN_GESTION = 2;
    public static final int ESTADO_GESTIONADO = 3;
    public static final int ESTADO_ANULADO = 4;

    private Integer id;
    private AuNoSolicitud auNoSolicitudesId;
    private CntContratoDetalle cntContratoDetallesId;
    private int estado;
    private String estadoJustificacion;
    private int tipoTecnologia;
    private int maTecnologiaId;
    private String maTecnologiaCodigo;
    private String maTecnologiaValor;
    private Integer maMedicamentosId;
    private String maMedicamentosCodigo;
    private String maMedicamentosValor;
    private int cantidadSolicitada;
    private Integer maServicioHabilitacionId;
    private String maServicioHabilitacionCodigo;
    private String maServicioHabilitacionValor;
    private BigDecimal dosis;
    private Integer frecuencia;
    private String posologia;
    private Integer maeViaAdministracionId;
    private String maeViaAdministracionCodigo;
    private String maeViaAdministracionValor;
    private String maeViaAdministracionTipo;
    private Integer duracionTratamiento;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;
    private int numEntregas;
    private boolean pbs;
    private Integer maeAtc1Id;
    private String maeAtc1Codigo;
    private String maeAtc1Valor;
    private Integer maeGrupoTerapeuticoPpalId;
    private String maeGrupoTerapeuticoPpalCodigo;
    private String maeGrupoTerapeuticoPpalValor;
    private String registroSanitario;
    private String descripcionLargaEstandarizada;
    private boolean borrado;
    private String usuarioBorra;
    private String terminalBorra;
    private Date fechaHoraBorra;

    private List<AuNoSolicitudEntrega> listAuSolicitudEntrega;
    private List<AuNoSolicitudEntregaDetalle> listAuNoSolicitudEntregaDetalle;
    //variables auxiliares
    private int posicion;
    private int tipoEntrega;
    private int cantidadEntrega;
    private int cantidadPendiente;

    private boolean habilitarValorUnitario;

    public AuNoSolicitudItem() {

    }

    public AuNoSolicitudItem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuNoSolicitud getAuNoSolicitudesId() {
        return auNoSolicitudesId;
    }

    public void setAuNoSolicitudesId(AuNoSolicitud auNoSolicitudesId) {
        this.auNoSolicitudesId = auNoSolicitudesId;
    }

    public CntContratoDetalle getCntContratoDetallesId() {
        return cntContratoDetallesId;
    }

    public void setCntContratoDetallesId(CntContratoDetalle cntContratoDetallesId) {
        this.cntContratoDetallesId = cntContratoDetallesId;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getEstadoJustificacion() {
        return estadoJustificacion;
    }

    public void setEstadoJustificacion(String estadoJustificacion) {
        this.estadoJustificacion = estadoJustificacion;
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

    public Integer getMaMedicamentosId() {
        return maMedicamentosId;
    }

    public void setMaMedicamentosId(Integer maMedicamentosId) {
        this.maMedicamentosId = maMedicamentosId;
    }

    public String getMaMedicamentosCodigo() {
        return maMedicamentosCodigo;
    }

    public void setMaMedicamentosCodigo(String maMedicamentosCodigo) {
        this.maMedicamentosCodigo = maMedicamentosCodigo;
    }

    public String getMaMedicamentosValor() {
        return maMedicamentosValor;
    }

    public void setMaMedicamentosValor(String maMedicamentosValor) {
        this.maMedicamentosValor = maMedicamentosValor;
    }

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(int cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public Integer getMaServicioHabilitacionId() {
        return maServicioHabilitacionId;
    }

    public void setMaServicioHabilitacionId(Integer maServicioHabilitacionId) {
        this.maServicioHabilitacionId = maServicioHabilitacionId;
    }

    public String getMaServicioHabilitacionCodigo() {
        return maServicioHabilitacionCodigo;
    }

    public void setMaServicioHabilitacionCodigo(String maServicioHabilitacionCodigo) {
        this.maServicioHabilitacionCodigo = maServicioHabilitacionCodigo;
    }

    public String getMaServicioHabilitacionValor() {
        return maServicioHabilitacionValor;
    }

    public void setMaServicioHabilitacionValor(String maServicioHabilitacionValor) {
        this.maServicioHabilitacionValor = maServicioHabilitacionValor;
    }

    public BigDecimal getDosis() {
        return dosis;
    }

    public void setDosis(BigDecimal dosis) {
        this.dosis = dosis;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getPosologia() {
        return posologia;
    }

    public void setPosologia(String posologia) {
        this.posologia = posologia;
    }

    public Integer getMaeViaAdministracionId() {
        return maeViaAdministracionId;
    }

    public void setMaeViaAdministracionId(Integer maeViaAdministracionId) {
        this.maeViaAdministracionId = maeViaAdministracionId;
    }

    public String getMaeViaAdministracionCodigo() {
        return maeViaAdministracionCodigo;
    }

    public void setMaeViaAdministracionCodigo(String maeViaAdministracionCodigo) {
        this.maeViaAdministracionCodigo = maeViaAdministracionCodigo;
    }

    public String getMaeViaAdministracionValor() {
        return maeViaAdministracionValor;
    }

    public void setMaeViaAdministracionValor(String maeViaAdministracionValor) {
        this.maeViaAdministracionValor = maeViaAdministracionValor;
    }

    public String getMaeViaAdministracionTipo() {
        return maeViaAdministracionTipo;
    }

    public void setMaeViaAdministracionTipo(String maeViaAdministracionTipo) {
        this.maeViaAdministracionTipo = maeViaAdministracionTipo;
    }

    public Integer getDuracionTratamiento() {
        return duracionTratamiento;
    }

    public void setDuracionTratamiento(Integer duracionTratamiento) {
        this.duracionTratamiento = duracionTratamiento;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getNumEntregas() {
        return numEntregas;
    }

    public void setNumEntregas(int numEntregas) {
        this.numEntregas = numEntregas;
    }

    public boolean isPbs() {
        return pbs;
    }

    public void setPbs(boolean pbs) {
        this.pbs = pbs;
    }

    public Integer getMaeAtc1Id() {
        return maeAtc1Id;
    }

    public void setMaeAtc1Id(Integer maeAtc1Id) {
        this.maeAtc1Id = maeAtc1Id;
    }

    public String getMaeAtc1Codigo() {
        return maeAtc1Codigo;
    }

    public void setMaeAtc1Codigo(String maeAtc1Codigo) {
        this.maeAtc1Codigo = maeAtc1Codigo;
    }

    public String getMaeAtc1Valor() {
        return maeAtc1Valor;
    }

    public void setMaeAtc1Valor(String maeAtc1Valor) {
        this.maeAtc1Valor = maeAtc1Valor;
    }

    public Integer getMaeGrupoTerapeuticoPpalId() {
        return maeGrupoTerapeuticoPpalId;
    }

    public void setMaeGrupoTerapeuticoPpalId(Integer maeGrupoTerapeuticoPpalId) {
        this.maeGrupoTerapeuticoPpalId = maeGrupoTerapeuticoPpalId;
    }

    public String getMaeGrupoTerapeuticoPpalCodigo() {
        return maeGrupoTerapeuticoPpalCodigo;
    }

    public void setMaeGrupoTerapeuticoPpalCodigo(String maeGrupoTerapeuticoPpalCodigo) {
        this.maeGrupoTerapeuticoPpalCodigo = maeGrupoTerapeuticoPpalCodigo;
    }

    public String getMaeGrupoTerapeuticoPpalValor() {
        return maeGrupoTerapeuticoPpalValor;
    }

    public void setMaeGrupoTerapeuticoPpalValor(String maeGrupoTerapeuticoPpalValor) {
        this.maeGrupoTerapeuticoPpalValor = maeGrupoTerapeuticoPpalValor;
    }

    public String getRegistroSanitario() {
        return registroSanitario;
    }

    public void setRegistroSanitario(String registroSanitario) {
        this.registroSanitario = registroSanitario;
    }

    public String getDescripcionLargaEstandarizada() {
        return descripcionLargaEstandarizada;
    }

    public void setDescripcionLargaEstandarizada(String descripcionLargaEstandarizada) {
        this.descripcionLargaEstandarizada = descripcionLargaEstandarizada;
    }

    public boolean isBorrado() {
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

    public List<AuNoSolicitudEntrega> getListAuSolicitudEntrega() {
        return listAuSolicitudEntrega;
    }

    public void setListAuSolicitudEntrega(List<AuNoSolicitudEntrega> listAuSolicitudEntrega) {
        this.listAuSolicitudEntrega = listAuSolicitudEntrega;
    }

    public List<AuNoSolicitudEntregaDetalle> getListAuNoSolicitudEntregaDetalle() {
        return listAuNoSolicitudEntregaDetalle;
    }

    public void setListAuNoSolicitudEntregaDetalle(List<AuNoSolicitudEntregaDetalle> listAuNoSolicitudEntregaDetalle) {
        this.listAuNoSolicitudEntregaDetalle = listAuNoSolicitudEntregaDetalle;
    }
    
    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(int tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public int getCantidadEntrega() {
        return cantidadEntrega;
    }

    public void setCantidadEntrega(int cantidadEntrega) {
        this.cantidadEntrega = cantidadEntrega;
    }

    public int getCantidadPendiente() {
        return cantidadPendiente;
    }

    public void setCantidadPendiente(int cantidadPendiente) {
        this.cantidadPendiente = cantidadPendiente;
    }

    public boolean isHabilitarValorUnitario() {
        return habilitarValorUnitario;
    }

    public void setHabilitarValorUnitario(boolean habilitarValorUnitario) {
        this.habilitarValorUnitario = habilitarValorUnitario;
    }

    //Metodo auxiliar
    public String getTipoTecnologiaStr() {
        String tipo = "";
        switch (tipoTecnologia) {
            case 1:
                tipo = "Procedimiento";
                break;
            case 2:
                tipo = "Medicamento";
                break;
            case 3:
                tipo = "Insumo";
                break;
            case 4:
                tipo = "Paquete";
                break;
            case 5:
                tipo = "Agrupador Medicamento";
                break;
        }
        return tipo;
    }

    //Metodo auxiliar
    public String getEstadoStr() {
        String estadoStr = "";
        switch (estado) {
            case 1:
                estadoStr = "Pendiente";
                break;
            case 2:
                estadoStr = "En Gestión";
                break;
            case 3:
                estadoStr = "Gestionado";
                break;
            case 4:
                estadoStr = "Anulado";
                break;
        }
        return estadoStr;
    }

    public String getTipoEntregaStr() {
        String tipoEntregaStr = "";
        switch (tipoEntrega) {
            case AuNoSolicitudEntrega.TIPO_NO_ENTREGADO:
                tipoEntregaStr = "No Entregado";
                break;
            case AuNoSolicitudEntrega.TIPO_ENTREGA_TOTAL:
                tipoEntregaStr = "Total";
                break;
            case AuNoSolicitudEntrega.TIPO_ENTREGA_PARCIAL:
                tipoEntregaStr = "Parcial";
                break;
            case AuNoSolicitudEntrega.TIPO_ENTREGA_RECLAMA_SIN_ENTREGAS:
                tipoEntregaStr = "Reclama sin entregas";
                break;
            case AuNoSolicitudEntrega.TIPO_ENTREGA_ANULADO:
                tipoEntregaStr = "Anulado";
                break;
            case AuNoSolicitudEntrega.TIPO_ENTREGA_NO_PRESTADO:
                tipoEntregaStr = "No Prestado";
                break;
            default:
                break;
        }
        return tipoEntregaStr;
    }

    public String establecerColorTipoEntrega() {
        String color = "white";
        try {
            switch (tipoEntrega) {
                case AuNoSolicitudEntrega.TIPO_NO_ENTREGADO:
                    color = "red";
                    break;
                case AuNoSolicitudEntrega.TIPO_ENTREGA_TOTAL:
                    color = "green";
                    break;
                case AuNoSolicitudEntrega.TIPO_ENTREGA_PARCIAL:
                    color = "yellow";
                    break;
                case AuNoSolicitudEntrega.TIPO_ENTREGA_RECLAMA_SIN_ENTREGAS:
                    color = "blue";
                    break;
                case AuNoSolicitudEntrega.TIPO_ENTREGA_ANULADO:
                    color = "orange";
                    break;
                case AuNoSolicitudEntrega.TIPO_ENTREGA_NO_PRESTADO:
                    color = "black";
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            this.addError("Ocurrio un error color rescate");
        }
        return color;
    }

    public String getPbsStr() {
        String pbsStr = "No";
        if (pbs) {
            pbsStr = "Si";
        }
        return pbsStr;
    }

    @Override
    public String toString() {
        return "AuNoSolicitudItem{" + "id=" + id + ", auNoSolicitudesId=" + auNoSolicitudesId + ", estado=" + estado + ", estadoJustificacion=" + estadoJustificacion + ", tipoTecnologia=" + tipoTecnologia + ", maTecnologiaId=" + maTecnologiaId + ", maTecnologiaCodigo=" + maTecnologiaCodigo + ", maTecnologiaValor=" + maTecnologiaValor + ", maMedicamentosId=" + maMedicamentosId + ", maMedicamentosCodigo=" + maMedicamentosCodigo + ", maMedicamentosValor=" + maMedicamentosValor + ", cantidadSolicitada=" + cantidadSolicitada + ", maServicioHabilitacionId=" + maServicioHabilitacionId + ", maServicioHabilitacionCodigo=" + maServicioHabilitacionCodigo + ", maServicioHabilitacionValor=" + maServicioHabilitacionValor + ", dosis=" + dosis + ", frecuencia=" + frecuencia + ", posologia=" + posologia + ", maeViaAdministracionId=" + maeViaAdministracionId + ", maeViaAdministracionCodigo=" + maeViaAdministracionCodigo + ", maeViaAdministracionValor=" + maeViaAdministracionValor + ", maeViaAdministracionTipo=" + maeViaAdministracionTipo + '}';
    }

}
