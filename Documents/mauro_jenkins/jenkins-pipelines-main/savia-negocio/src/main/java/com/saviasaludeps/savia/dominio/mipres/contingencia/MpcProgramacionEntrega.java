/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.mipres.contingencia;

import com.saviasaludeps.savia.dominio.administracion.Empresa;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author rpalacic
 */
public class MpcProgramacionEntrega extends Auditoria{

    public static final short ESTADO_ENTREGADO = 1;
    public static final short ESTADO_NO_ENTREGADO = 2;
    
    public static final short CAUSA_NO_APLICA = 0;
    public static final short CAUSA_NO_ENTREGA_NIEGA = 1;
    public static final short CAUSA_NO_ENTREGA_FALLECIDO = 2;
    public static final short CAUSA_NO_ENTREGA_CONTACTO = 3;
    
    private Integer id;
    private Empresa Empresa;
    private MpcPrescripcion mpcPrescripcion;
    private short estado;
    private short numeroEntregaTotal;
    private short numeroEntrega;
    private Integer cantidad;
    private boolean entregaTotal;
    private short causaNoEntrega;
    private Date fechaEntrega;

    public MpcProgramacionEntrega() {
    }

    public MpcProgramacionEntrega(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(Empresa Empresa) {
        this.Empresa = Empresa;
    }

    public MpcPrescripcion getMpcPrescripcion() {
        return mpcPrescripcion;
    }

    public void setMpcPrescripcion(MpcPrescripcion mpcPrescripcion) {
        this.mpcPrescripcion = mpcPrescripcion;
    }

    public short getEstado() {
        return estado;
    }
    
    public String getEstadoStr() {
        switch (estado) {
            case ESTADO_ENTREGADO:
                return "Entregado";
            case ESTADO_NO_ENTREGADO:
                return "No Entregado";
            default:
                return "";
        }
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public short getNumeroEntregaTotal() {
        return numeroEntregaTotal;
    }

    public void setNumeroEntregaTotal(short numeroEntregaTotal) {
        this.numeroEntregaTotal = numeroEntregaTotal;
    }

    public short getNumeroEntrega() {
        return numeroEntrega;
    }

    public void setNumeroEntrega(short numeroEntrega) {
        this.numeroEntrega = numeroEntrega;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isEntregaTotal() {
        return entregaTotal;
    }

    public void setEntregaTotal(boolean entregaTotal) {
        this.entregaTotal = entregaTotal;
    }

    public short getCausaNoEntrega() {
        return causaNoEntrega;
    }
    
    public String getCausaNoEntregaStr() {
        switch (causaNoEntrega) {
            case CAUSA_NO_APLICA:
                return "N/A";
            case CAUSA_NO_ENTREGA_NIEGA:
                return "El paciente se niega a recibir el suministro";
            case CAUSA_NO_ENTREGA_FALLECIDO:
                return "Paciente fallecido";
            case CAUSA_NO_ENTREGA_CONTACTO:
                return "No fue posible contactar al paciente";
            default:
                return "";
        }
    }

    public void setCausaNoEntrega(short causaNoEntrega) {
        this.causaNoEntrega = causaNoEntrega;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    @Override
    public String toString() {
        return "MpcProgramacionEntrega{" + "id=" + id + ", Empresa=" + Empresa + ", mpcPrescripcion=" + mpcPrescripcion + ", estado=" + estado + ", numeroEntregaTotal=" + numeroEntregaTotal + ", numeroEntrega=" + numeroEntrega + ", cantidad=" + cantidad + ", entregaTotal=" + entregaTotal + ", causaNoEntrega=" + causaNoEntrega + ", fechaEntrega=" + fechaEntrega + '}';
    }

    

}
