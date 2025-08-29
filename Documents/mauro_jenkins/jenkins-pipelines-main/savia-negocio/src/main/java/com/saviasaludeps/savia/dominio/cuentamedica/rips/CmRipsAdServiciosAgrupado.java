package com.saviasaludeps.savia.dominio.cuentamedica.rips;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

public class CmRipsAdServiciosAgrupado extends Auditoria {

    private Integer id;
    private int fila;
    private String archivoControl;
    private String numeroFactura;
    private String codigoReps;
    private String conceptoRips;
    private String cantidadServicio;
    private String valorUnitario;
    private String valorConcepto;
    private String archivoNombreOriginal;
    private String archivoRuta;
    private String archivoNombre;
    private CmRipsCarga cmRipsCarga;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArchivoControl() {
        return archivoControl;
    }

    public void setArchivoControl(String archivoControl) {
        this.archivoControl = archivoControl;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getCodigoReps() {
        return codigoReps;
    }

    public void setCodigoReps(String codigoReps) {
        this.codigoReps = codigoReps;
    }

    public String getConceptoRips() {
        return conceptoRips;
    }

    public void setConceptoRips(String conceptoRips) {
        this.conceptoRips = conceptoRips;
    }

    public String getCantidadServicio() {
        return cantidadServicio;
    }

    public void setCantidadServicio(String cantidadServicio) {
        this.cantidadServicio = cantidadServicio;
    }

    public String getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(String valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getValorConcepto() {
        return valorConcepto;
    }

    public void setValorConcepto(String valorConcepto) {
        this.valorConcepto = valorConcepto;
    }

    public String getArchivoNombreOriginal() {
        return archivoNombreOriginal;
    }

    public void setArchivoNombreOriginal(String archivoNombreOriginal) {
        this.archivoNombreOriginal = archivoNombreOriginal;
    }

    public String getArchivoRuta() {
        return archivoRuta;
    }

    public void setArchivoRuta(String archivoRuta) {
        this.archivoRuta = archivoRuta;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public CmRipsCarga getCmRipsCarga() {
        return cmRipsCarga;
    }

    public void setCmRipsCarga(CmRipsCarga cmRipsCargas) {
        this.cmRipsCarga = cmRipsCargas;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }
    
    @Override
    public String toString() {
        return "CmRipsAdServiciosAgrupado{" + "id=" + id + ", archivoControl=" + archivoControl + ", numeroFactura=" + numeroFactura + ", cmRipsCargaId=" + cmRipsCarga.getId() + '}';
    }

}
