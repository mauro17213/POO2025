/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.mipres;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @BSGomez
 */
public class MpDetalleItem extends Auditoria {

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

    public static final int ID_ESTADO_ENTREGA_PARCIAL = 1;
    public static final int ID_ESTADO_ENTREGA_TOTAL = 2;
    public static final int ID_ESTADO_ENTREGA_DIFERIDA = 3;

    private Integer id;
    private Integer consecutivo;
    private String tipoTecnologia;
    private String tipoPrestacion;
    private String estado;
    private String codigoFormaFarmaceutica;
    private String nombreTecnologiaPrescripta;
    private Integer duracionTratamientoOrdenado;
    private String cantidadTotalPrescrita;
    private String codigoTecnologia;
    private String codigoTecnologiaAvalEps;
    private String nombreTecnologiaAvalEps;
    private String obtenerTipoTecnologia;
    private String maDiagnosticoPrincipalValor;//prescripcion
    private String maDiagnosticoRelacionado1Valor;//prescripcion
    private String maDiagnosticoRelacionado2Valor;//prescripcion
    private boolean enfermedadHuerfana;//prescripcion
    private String codigoEnfermedadHuerfana;//prescripcion
    private String justificacionNoPbs;
    private int estadoJuntaProfesionales;
    private Boolean tieneCups;//tecnologias
    private Boolean combinacionCups;
    private Boolean faseExperimental;
    private Boolean financiadoPbsUpc;
    private Boolean utilizoProcedExistentePbsUpc;
    private Boolean pbsUtilizado;
    private Boolean descartoNoExistePbs;
    private Boolean servicioPrestaraColombia;
    private Integer cantidadTotal;

    public MpDetalleItem() {
    }

    public MpDetalleItem(Integer id) {
        this.id = id;
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

    public String getTipoTecnologia() {
        return tipoTecnologia;
    }

    public void setTipoTecnologia(String tipoTecnologia) {
        this.tipoTecnologia = tipoTecnologia;
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

    public String getMaDiagnosticoPrincipalValor() {
        return maDiagnosticoPrincipalValor;
    }

    public void setMaDiagnosticoPrincipalValor(String maDiagnosticoPrincipalValor) {
        this.maDiagnosticoPrincipalValor = maDiagnosticoPrincipalValor;
    }

    public String getMaDiagnosticoRelacionado1Valor() {
        return maDiagnosticoRelacionado1Valor;
    }

    public void setMaDiagnosticoRelacionado1Valor(String maDiagnosticoRelacionado1Valor) {
        this.maDiagnosticoRelacionado1Valor = maDiagnosticoRelacionado1Valor;
    }

    public String getMaDiagnosticoRelacionado2Valor() {
        return maDiagnosticoRelacionado2Valor;
    }

    public void setMaDiagnosticoRelacionado2Valor(String maDiagnosticoRelacionado2Valor) {
        this.maDiagnosticoRelacionado2Valor = maDiagnosticoRelacionado2Valor;
    }

    public boolean isEnfermedadHuerfana() {
        return enfermedadHuerfana;
    }

    public void setEnfermedadHuerfana(boolean enfermedadHuerfana) {
        this.enfermedadHuerfana = enfermedadHuerfana;
    }

    public String getCodigoEnfermedadHuerfana() {
        return codigoEnfermedadHuerfana;
    }

    public void setCodigoEnfermedadHuerfana(String codigoEnfermedadHuerfana) {
        this.codigoEnfermedadHuerfana = codigoEnfermedadHuerfana;
    }

    public String getJustificacionNoPbs() {
        return justificacionNoPbs;
    }

    public void setJustificacionNoPbs(String justificacionNoPbs) {
        this.justificacionNoPbs = justificacionNoPbs;
    }

    public int getEstadoJuntaProfesionales() {
        return estadoJuntaProfesionales;
    }

    public void setEstadoJuntaProfesionales(int estadoJuntaProfesionales) {
        this.estadoJuntaProfesionales = estadoJuntaProfesionales;
    }

    public Boolean getTieneCups() {
        return tieneCups;
    }

    public void setTieneCups(Boolean tieneCups) {
        this.tieneCups = tieneCups;
    }

    public Boolean getCombinacionCups() {
        return combinacionCups;
    }

    public void setCombinacionCups(Boolean combinacionCups) {
        this.combinacionCups = combinacionCups;
    }

    public Boolean getFaseExperimental() {
        return faseExperimental;
    }

    public void setFaseExperimental(Boolean faseExperimental) {
        this.faseExperimental = faseExperimental;
    }

    public Boolean getFinanciadoPbsUpc() {
        return financiadoPbsUpc;
    }

    public void setFinanciadoPbsUpc(Boolean financiadoPbsUpc) {
        this.financiadoPbsUpc = financiadoPbsUpc;
    }

    public Boolean getUtilizoProcedExistentePbsUpc() {
        return utilizoProcedExistentePbsUpc;
    }

    public void setUtilizoProcedExistentePbsUpc(Boolean utilizoProcedExistentePbsUpc) {
        this.utilizoProcedExistentePbsUpc = utilizoProcedExistentePbsUpc;
    }

    public Boolean getPbsUtilizado() {
        return pbsUtilizado;
    }

    public void setPbsUtilizado(Boolean pbsUtilizado) {
        this.pbsUtilizado = pbsUtilizado;
    }

    public Boolean getDescartoNoExistePbs() {
        return descartoNoExistePbs;
    }

    public void setDescartoNoExistePbs(Boolean descartoNoExistePbs) {
        this.descartoNoExistePbs = descartoNoExistePbs;
    }

    public Boolean getServicioPrestaraColombia() {
        return servicioPrestaraColombia;
    }

    public void setServicioPrestaraColombia(Boolean servicioPrestaraColombia) {
        this.servicioPrestaraColombia = servicioPrestaraColombia;
    }

    public String getObtenerTipoTecnologia() {
        if (getTipoTecnologia() != null) {
            switch (this.getTipoTecnologia()) {
                case MpDetalleItem.ID_TIPO_TECNOLOGIA_MEDICAMENTO:
                    obtenerTipoTecnologia = "Medicamento";
                    break;
                case MpDetalleItem.ID_TIPO_TECNOLOGIA_PROCEDIMIENTOS:
                    obtenerTipoTecnologia = "Procedimientos";
                    break;
                case MpDetalleItem.ID_TIPO_TECNOLOGIA_DISPOSITIVO:
                    obtenerTipoTecnologia = "Dispositivos";
                    break;
                case MpDetalleItem.ID_TIPO_TECNOLOGIA_PRODUCTOS_NUTRICIONALES:
                    obtenerTipoTecnologia = "Productos Nutricionales";
                    break;
                case MpDetalleItem.ID_TIPO_TECNOLOGIA_SERVICIO_COMPLEMENTARIO:
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

    @Override
    public String toString() {
        return "MpDetalleItem{" + "id=" + id + ", consecutivo=" + consecutivo + ", tipoTecnologia=" + tipoTecnologia + ", tipoPrestacion=" + tipoPrestacion + ", estado=" + estado + ", codigoFormaFarmaceutica=" + codigoFormaFarmaceutica + ", nombreTecnologiaPrescripta=" + nombreTecnologiaPrescripta + ", duracionTratamientoOrdenado=" + duracionTratamientoOrdenado + ", cantidadTotalPrescrita=" + cantidadTotalPrescrita + ", codigoTecnologia=" + codigoTecnologia + ", codigoTecnologiaAvalEps=" + codigoTecnologiaAvalEps + ", nombreTecnologiaAvalEps=" + nombreTecnologiaAvalEps + ", obtenerTipoTecnologia=" + obtenerTipoTecnologia + ", maDiagnosticoPrincipalValor=" + maDiagnosticoPrincipalValor + ", maDiagnosticoRelacionado1Valor=" + maDiagnosticoRelacionado1Valor + ", maDiagnosticoRelacionado2Valor=" + maDiagnosticoRelacionado2Valor + ", enfermedadHuerfana=" + enfermedadHuerfana + ", codigoEnfermedadHuerfana=" + codigoEnfermedadHuerfana + ", justificacionNoPbs=" + justificacionNoPbs + ", estadoJuntaProfesionales=" + estadoJuntaProfesionales + ", tieneCups=" + tieneCups + ", combinacionCups=" + combinacionCups + ", faseExperimental=" + faseExperimental + ", financiadoPbsUpc=" + financiadoPbsUpc + ", utilizoProcedExistentePbsUpc=" + utilizoProcedExistentePbsUpc + ", pbsUtilizado=" + pbsUtilizado + ", descartoNoExistePbs=" + descartoNoExistePbs + ", servicioPrestaraColombia=" + servicioPrestaraColombia + ", cantidadTotal=" + cantidadTotal + '}';
    }

}
