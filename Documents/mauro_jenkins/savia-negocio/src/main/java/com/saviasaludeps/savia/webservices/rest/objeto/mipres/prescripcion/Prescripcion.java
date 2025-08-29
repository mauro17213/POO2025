/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.webservices.rest.objeto.mipres.prescripcion;

import com.saviasaludeps.savia.dominio.mipres.MpConsumoFallo;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author yjimenez
 */
public class Prescripcion implements Serializable {

    private PrescripcionDetalle prescripcion;
    private List<Medicamento> medicamentos;
    private List<Procedimiento> procedimientos;
    private List<Dispositivo> dispositivos;
    private List<ProductoNutricional> productosnutricionales;
    private List<ServicioComplementario> serviciosComplementarios;
    private MpConsumoFallo fallo;

    public PrescripcionDetalle getPrescripcion() {
        return prescripcion;
    }

    public void setPrescripcion(PrescripcionDetalle prescripcion) {
        this.prescripcion = prescripcion;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public List<Procedimiento> getProcedimientos() {
        return procedimientos;
    }

    public void setProcedimientos(List<Procedimiento> procedimientos) {
        this.procedimientos = procedimientos;
    }

    public List<Dispositivo> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(List<Dispositivo> dispositivos) {
        this.dispositivos = dispositivos;
    }

    public List<ProductoNutricional> getProductosnutricionales() {
        return productosnutricionales;
    }

    public void setProductosnutricionales(List<ProductoNutricional> productosnutricionales) {
        this.productosnutricionales = productosnutricionales;
    }

    public List<ServicioComplementario> getServiciosComplementarios() {
        return serviciosComplementarios;
    }

    public void setServiciosComplementarios(List<ServicioComplementario> serviciosComplementarios) {
        this.serviciosComplementarios = serviciosComplementarios;
    }

    public MpConsumoFallo getFallo() {
        return fallo;
    }

    public void setFallo(MpConsumoFallo fallo) {
        this.fallo = fallo;
    }

    }
