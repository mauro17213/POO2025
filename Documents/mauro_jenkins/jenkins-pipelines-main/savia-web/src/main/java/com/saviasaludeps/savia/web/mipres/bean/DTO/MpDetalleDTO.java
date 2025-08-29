/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.web.mipres.bean.DTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @BSGomez
 */
public class MpDetalleDTO implements Serializable {

    public static final int ID_JUNTA_PROFESIONAL_NO_REQUIERE = 1;
    public static final int ID_JUNTA_PROFESIONAL_PENDIENTE = 2;
    public static final int ID_JUNTA_PROFESIONAL_APROBADA = 3;
    public static final int ID_JUNTA_PROFESIONAL_NO_APROBADA = 4;

    public static final int ID_TIPO_MEDICAMENTO_MEDICAMENTO = 1;
    public static final int ID_TIPO_MEDICAMENTO_VITAL_NO_DISPO = 2;
    public static final int ID_TIPO_MEDICAMENTO_PREPARA_MAGISTRAL = 3;
    public static final int ID_TIPO_MEDICAMENTO_UNIRS = 7;
    public static final int ID_TIPO_MEDICAMENTO_URGENCIA_MEDICA = 9;

    public static final String ID_TIPO_TECNOLOGIA_MEDICAMENTO = "1";
    public static final String ID_TIPO_TECNOLOGIA_PROCEDIMIENTOS = "2";
    public static final String ID_TIPO_TECNOLOGIA_DISPOSITIVO = "3";
    public static final String ID_TIPO_TECNOLOGIA_PRODUCTOS_NUTRICIONALES = "4";
    public static final String ID_TIPO_TECNOLOGIA_SERVICIO_COMPLEMENTARIO = "5";

    public static final String ID_TIPO_MEDICAMENTO_MEDICAMENTO1 = "1";
    public static final String ID_TIPO_MEDICAMENTO_VITAL_NO_DISPO1 = "2";
    public static final String ID_TIPO_MEDICAMENTO_PREPARA_MAGISTRAL1 = "3";
    public static final String ID_TIPO_MEDICAMENTO_UNIRS1 = "7";
    public static final String ID_TIPO_MEDICAMENTO_URGENCIA_MEDICA1 = "9";

    public static final int ID_TIPO_TECNOLOGIA_MEDICAMENTO1 = 1;
    public static final int ID_TIPO_TECNOLOGIA_PROCEDIMIENTOS1 = 2;
    public static final int ID_TIPO_TECNOLOGIA_DISPOSITIVO1 = 3;
    public static final int ID_TIPO_TECNOLOGIA_PRODUCTOS_NUTRICIONALES1 = 4;
    public static final int ID_TIPO_TECNOLOGIA_SERVICIO_COMPLEMENTARIO1 = 5;

    public static final int ID_TIPO_PRESTACION_UNICA = 1;
    public static final int ID_TIPO_PRESTACION_SUCESIVA = 2;

    public static final int ID_ESTADO_DIRECCIONADO = 1;
    public static final int ID_ESTADO_NO_DIRECCIONADO = 2;
    public static final int ID_ESTADO_PENDIENTE = 3;
    public static final int ID_ESTADO_ANULADO_DIRECCIONADO = 4;
    public static final int ID_ESTADO_ANULADO_NO_DIRECCIONADO = 5;
    public static final int ID_ESTADO_PROGRAMADO = 6;
    public static final int ID_ESTADO_ENTREGADO = 7;
    public static final int ID_ESTADO_REPORTADO = 8;

    public static final int ID_FRECUENCIA_TRATAMIENTO_MINUTOS = 1;
    public static final int ID_FRECUENCIA_TRATAMIENTO_HORAS = 2;
    public static final int ID_FRECUENCIA_TRATAMIENTO_DIAS = 3;
    public static final int ID_FRECUENCIA_TRATAMIENTO_SEMANAS = 4;
    public static final int ID_FRECUENCIA_TRATAMIENTO_MESES = 5;
    public static final int ID_FRECUENCIA_TRATAMIENTO_ANIO = 6;
    public static final int ID_FRECUENCIA_TRATAMIENTO_RTA = 7;

    public static final int ID_INDICACIONES_UNICA = 1;
    public static final int ID_INDICACIONES_INMEDIATA = 2;
    public static final int ID_INDICACIONES_BOLO = 3;
    public static final int ID_INDICACIONES_GOTEO = 4;
    public static final int ID_INDICACIONES_CONTINUA = 5;
    public static final int ID_INDICACIONES_INTERMITENTE = 6;
    public static final int ID_INDICACIONES_INTERMITENTE_OTRA = 7;
    public static final int ID_INDICACIONES_MICROGOTEO = 8;
    public static final int ID_INDICACIONES_PERFUSION = 9;
    public static final int ID_INDICACIONES_SIN_INDICACION = 10;

    private Integer intTipoMedicamento;
    public static final int ID_ESTADO_ENTREGA_PARCIAL = 1;
    public static final int ID_ESTADO_ENTREGA_TOTAL = 2;
    public static final int ID_ESTADO_ENTREGA_DIFERIDA = 3;

    private Integer id;
    private Integer consecutivo;
    private Integer numeroTransaccion;
    private String tipoTecnologia;
    private Integer tipoTecnologiaId;
    private String tipoMedicamento;
    private String tipoPrestacion;
    private String estado;
    private String codigoFormaFarmaceutica;
    private String nombreTecnologiaPrescripta;
    private Integer duracionTratamientoOrdenado;
    private String cantidadTotalPrescrita;
    private String codigoTecnologia;
    private String nombreTecnologia;
    private String codigoTecnologiaAvalEps;
    private String nombreTecnologiaAvalEps;
    private Integer cantidadTotal;
    private BigDecimal numeroEntregas;
    private BigDecimal pendientes;
    private Integer unidadesAprobadasPeriodo;
    private String conceptoJuntaProfesional;
    private String conceptoEvaluacion;
    private Date fechaDireccionamiento;

    private String obtenerTipoTecnologia;
    private String optenerTipoMedicamento;

    public Integer getIntTipoMedicamento() {
        return intTipoMedicamento;
    }

    public void setIntTipoMedicamento(Integer intTipoMedicamento) {
        this.intTipoMedicamento = intTipoMedicamento;
    }
        

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Integer getNumeroTransaccion() {
        return numeroTransaccion;
    }

    public void setNumeroTransaccion(Integer numeroTransaccion) {
        this.numeroTransaccion = numeroTransaccion;
    }

    public String getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(String tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
    }

    public String getTipoMedicamento() {
        return tipoMedicamento;
    }

    public String getOptenerTipoMedicamento() {
        if (getTipoMedicamento() != null) {
            switch (this.getTipoMedicamento()) {
                case MpDetalleDTO.ID_TIPO_MEDICAMENTO_MEDICAMENTO1:
                    optenerTipoMedicamento = "Medicamento";
                    break;
                case MpDetalleDTO.ID_TIPO_MEDICAMENTO_VITAL_NO_DISPO1:
                    optenerTipoMedicamento = "Vital No Disponible ";
                    break;
                case MpDetalleDTO.ID_TIPO_MEDICAMENTO_PREPARA_MAGISTRAL1:
                    optenerTipoMedicamento = "Preparación Magistral";
                    break;
                case MpDetalleDTO.ID_TIPO_MEDICAMENTO_UNIRS1:
                    optenerTipoMedicamento = "UNIRS";
                    break;
                case MpDetalleDTO.ID_TIPO_MEDICAMENTO_URGENCIA_MEDICA1:
                    optenerTipoMedicamento = "Urgencia Médica ";
                    break;
                default:
                    optenerTipoMedicamento = "-- -- --";
                    break;
            }
        } else {
            optenerTipoMedicamento = "-- -- --";
        }
        return optenerTipoMedicamento;
    }

    public void setOptenerTipoMedicamento(String optenerTipoMedicamento) {
        this.optenerTipoMedicamento = optenerTipoMedicamento;
    }

    public void setTipoMedicamento(String tipoMedicamento) {
        this.tipoMedicamento = tipoMedicamento;
    }

    public String getTipoPrestacion() {
        return tipoPrestacion;
    }

    public void setTipoPrestacion(String tipoPrestacion) {
        this.tipoPrestacion = tipoPrestacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigoFormaFarmaceutica() {
        return codigoFormaFarmaceutica;
    }

    public void setCodigoFormaFarmaceutica(String codigoFormaFarmaceutica) {
        this.codigoFormaFarmaceutica = codigoFormaFarmaceutica;
    }

    public String getNombreTecnologiaPrescripta() {
        return nombreTecnologiaPrescripta;
    }

    public void setNombreTecnologiaPrescripta(String nombreTecnologiaPrescripta) {
        this.nombreTecnologiaPrescripta = nombreTecnologiaPrescripta;
    }

    public Integer getDuracionTratamientoOrdenado() {
        return duracionTratamientoOrdenado;
    }

    public void setDuracionTratamientoOrdenado(Integer duracionTratamientoOrdenado) {
        this.duracionTratamientoOrdenado = duracionTratamientoOrdenado;
    }

    public String getCantidadTotalPrescrita() {
        return cantidadTotalPrescrita;
    }

    public void setCantidadTotalPrescrita(String cantidadTotalPrescrita) {
        this.cantidadTotalPrescrita = cantidadTotalPrescrita;
    }

    public String getCodigoTecnologia() {
        return codigoTecnologia;
    }

    public void setCodigoTecnologia(String codigoTecnologia) {
        this.codigoTecnologia = codigoTecnologia;
    }

    public String getNombreTecnologia() {
        return nombreTecnologia;
    }

    public void setNombreTecnologia(String nombreTecnologia) {
        this.nombreTecnologia = nombreTecnologia;
    }

    public String getCodigoTecnologiaAvalEps() {
        return codigoTecnologiaAvalEps;
    }

    public void setCodigoTecnologiaAvalEps(String codigoTecnologiaAvalEps) {
        this.codigoTecnologiaAvalEps = codigoTecnologiaAvalEps;
    }

    public String getNombreTecnologiaAvalEps() {
        return nombreTecnologiaAvalEps;
    }

    public void setNombreTecnologiaAvalEps(String nombreTecnologiaAvalEps) {
        this.nombreTecnologiaAvalEps = nombreTecnologiaAvalEps;
    }

    public Integer getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(Integer cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public BigDecimal getNumeroEntregas() {
        return numeroEntregas;
    }

    public void setNumeroEntregas(BigDecimal numeroEntregas) {
        this.numeroEntregas = numeroEntregas;
    }

    public Integer getUnidadesAprobadasPeriodo() {
        return unidadesAprobadasPeriodo;
    }

    public void setUnidadesAprobadasPeriodo(Integer unidadesAprobadasPeriodo) {
        this.unidadesAprobadasPeriodo = unidadesAprobadasPeriodo;
    }

    public String getConceptoJuntaProfesional() {
        return conceptoJuntaProfesional;
    }

    public void setConceptoJuntaProfesional(String conceptoJuntaProfesional) {
        this.conceptoJuntaProfesional = conceptoJuntaProfesional;
    }

    public String getConceptoEvaluacion() {
        return conceptoEvaluacion;
    }

    public void setConceptoEvaluacion(String conceptoEvaluacion) {
        this.conceptoEvaluacion = conceptoEvaluacion;
    }

    public Date getFechaDireccionamiento() {
        return fechaDireccionamiento;
    }

    public void setFechaDireccionamiento(Date fechaDireccionamiento) {
        this.fechaDireccionamiento = fechaDireccionamiento;
    }

    public Integer getTipoTecnologiaId() {
        return tipoTecnologiaId;
    }

    public void setTipoTecnologiaId(Integer tipoTecnologiaId) {
        this.tipoTecnologiaId = tipoTecnologiaId;
    }

    public BigDecimal getPendientes() {
        return pendientes;
    }

    public void setPendientes(BigDecimal pendientes) {
        this.pendientes = pendientes;
    }

    public void setObtenerTipoTecnologia(String obtenerTipoTecnologia) {
        this.obtenerTipoTecnologia = obtenerTipoTecnologia;
    }

    public String getObtenerTipoTecnologia() {
        if (getTipoTecnologia() != null) {
            switch (this.getTipoTecnologia()) {
                case MpDetalleDTO.ID_TIPO_TECNOLOGIA_MEDICAMENTO:
                    obtenerTipoTecnologia = "Medicamento";
                    break;
                case MpDetalleDTO.ID_TIPO_TECNOLOGIA_PROCEDIMIENTOS:
                    obtenerTipoTecnologia = "Procedimientos";
                    break;
                case MpDetalleDTO.ID_TIPO_TECNOLOGIA_DISPOSITIVO:
                    obtenerTipoTecnologia = "Dispositivos";
                    break;
                case MpDetalleDTO.ID_TIPO_TECNOLOGIA_PRODUCTOS_NUTRICIONALES:
                    obtenerTipoTecnologia = "Productos Nutricionales";
                    break;
                case MpDetalleDTO.ID_TIPO_TECNOLOGIA_SERVICIO_COMPLEMENTARIO:
                    obtenerTipoTecnologia = "Servicios Complementarios";
                    break;
                default:
                    obtenerTipoTecnologia = "-- -- --";
                    break;
            }
        } else {
            obtenerTipoTecnologia = "-- -- --";
        }
        return obtenerTipoTecnologia;
    }
}
