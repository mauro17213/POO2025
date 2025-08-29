/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author rpalacic
 */
public class AuGrupoTecnologia extends Auditoria {

    //Tipo Tecnologia
    public static final int ID_TECNOLOGIA = 1;
    public static final int ID_MEDICAMENTO = 2;
    public static final int ID_INSUMO = 3;
    public static final int ID_PAQUETE = 4;
    public static final int ID_AGRUPADOR_MEDICAMENTO = 5;
    public static final int ID_NA = 0;
    private static final String TECNOLOGIA = "Procedimiento";
    private static final String MEDICAMENTO = "Medicamento";
    private static final String INSUMO = "Insumo";
    private static final String PAQUETE = "Paquete";
    private static final String AGRUPADOR_MEDICAMENTO = "Agrupador Medicamento";

    //Tipo Auditor
    private static final int ID_MEDICO = 1;
    private static final int ID_ENFERMERO = 2;
    private static final int ID_AUXILIAR = 3;
    private static final int ID_ODONTOLOGO = 4;
    private static final int ID_ORAL = 5;
    private static final String NA = "N/A";
    private static final String MEDICO = "MÉDICO";
    private static final String ENFERMERO = "ENFERMERO";
    private static final String AUXILIAR = "AUXILIAR ENFERMERIA";
    private static final String ODONTOLOGO = "ODONTOLOGO";
    private static final String ORAL = "AUXILIAR ORAL";

    private Integer id;
    private int tipoTecnologia;
    private Integer maTecnologiaId;
    private String maTecnologiaCodigo;
    private String maTecnologiasValor;
    private int tipo;
    private Integer maeTipoAuditorId;
    private String maeTipoAuditorCodigo;
    private String maeTipoAuditorValor;
    private Boolean aplicaSeguimiento;
    private Integer maeSeguimientoServicioId;
    private String maeSeguimientoServicioCodigo;
    private String maeSeguimientoServicioValor;
    private int nivelComplejidad;
    private Integer maeMedicamentoId;
    private String maeMedicamentoCodigo;
    private String maeMedicamentoValor;
    private AuGrupo auGrupo;

    public AuGrupoTecnologia() {
    }

    public AuGrupoTecnologia(Integer id) {
        this.id = id;
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

    public Integer getMaTecnologiaId() {
        return maTecnologiaId;
    }

    public void setMaTecnologiaId(Integer maTecnologiaId) {
        this.maTecnologiaId = maTecnologiaId;
    }

    public String getMaTecnologiaCodigo() {
        return maTecnologiaCodigo;
    }

    public void setMaTecnologiaCodigo(String maTecnologiaCodigo) {
        this.maTecnologiaCodigo = maTecnologiaCodigo;
    }

    public String getMaTecnologiasValor() {
        return maTecnologiasValor;
    }

    public void setMaTecnologiasValor(String maTecnologiasValor) {
        this.maTecnologiasValor = maTecnologiasValor;
    }

    public AuGrupo getAuGrupo() {
        return auGrupo;
    }

    public void setAuGrupo(AuGrupo auGrupo) {
        this.auGrupo = auGrupo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getNivelComplejidad() {
        return nivelComplejidad;
    }

    public void setNivelComplejidad(int nivelComplejidad) {
        this.nivelComplejidad = nivelComplejidad;
    }

    public Integer getMaeTipoAuditorId() {
        return maeTipoAuditorId;
    }

    public void setMaeTipoAuditorId(Integer maeTipoAuditorId) {
        this.maeTipoAuditorId = maeTipoAuditorId;
    }

    public String getMaeTipoAuditorCodigo() {
        return maeTipoAuditorCodigo;
    }

    public void setMaeTipoAuditorCodigo(String maeTipoAuditorCodigo) {
        this.maeTipoAuditorCodigo = maeTipoAuditorCodigo;
    }

    public String getMaeTipoAuditorValor() {
        return maeTipoAuditorValor;
    }

    public void setMaeTipoAuditorValor(String maeTipoAuditorValor) {
        this.maeTipoAuditorValor = maeTipoAuditorValor;
    }

    public Boolean getAplicaSeguimiento() {
        return aplicaSeguimiento;
    }

    public void setAplicaSeguimiento(Boolean aplicaSeguimiento) {
        this.aplicaSeguimiento = aplicaSeguimiento;
    }

    public Integer getMaeSeguimientoServicioId() {
        return maeSeguimientoServicioId;
    }

    public void setMaeSeguimientoServicioId(Integer maeSeguimientoServicioId) {
        this.maeSeguimientoServicioId = maeSeguimientoServicioId;
    }

    public String getMaeSeguimientoServicioCodigo() {
        return maeSeguimientoServicioCodigo;
    }

    public void setMaeSeguimientoServicioCodigo(String maeSeguimientoServicioCodigo) {
        this.maeSeguimientoServicioCodigo = maeSeguimientoServicioCodigo;
    }

    public String getMaeSeguimientoServicioValor() {
        return maeSeguimientoServicioValor;
    }

    public void setMaeSeguimientoServicioValor(String maeSeguimientoServicioValor) {
        this.maeSeguimientoServicioValor = maeSeguimientoServicioValor;
    }

    public Integer getMaeMedicamentoId() {
        return maeMedicamentoId;
    }

    public void setMaeMedicamentoId(Integer maeMedicamentoId) {
        this.maeMedicamentoId = maeMedicamentoId;
    }

    public String getMaeMedicamentoCodigo() {
        return maeMedicamentoCodigo;
    }

    public void setMaeMedicamentoCodigo(String maeMedicamentoCodigo) {
        this.maeMedicamentoCodigo = maeMedicamentoCodigo;
    }

    public String getMaeMedicamentoValor() {
        return maeMedicamentoValor;
    }

    public void setMaeMedicamentoValor(String maeMedicamentoValor) {
        this.maeMedicamentoValor = maeMedicamentoValor;
    }

    public String obtenerAplicaSeguimiento() {
        return obtenerBoolean(getAplicaSeguimiento());
    }

    public String obtenerBoolean(boolean valor) {
        if (valor) {
            return "Sí";
        } else {
            return "No";
        }
    }

    public String obtenerTipoTecnologia() {
        switch (tipoTecnologia) {
            case ID_TECNOLOGIA:
                return TECNOLOGIA;
            case ID_MEDICAMENTO:
                return MEDICAMENTO;
            case ID_INSUMO:
                return INSUMO;
            case ID_PAQUETE:
                return PAQUETE;
            case ID_AGRUPADOR_MEDICAMENTO:
                return AGRUPADOR_MEDICAMENTO;
        }
        return NA;
    }

    public String obtenerTipo() {
        switch (tipo) {
            case ID_MEDICO:
                return MEDICO;
            case ID_ENFERMERO:
                return ENFERMERO;
            case ID_AUXILIAR:
                return AUXILIAR;
            case ID_ODONTOLOGO:
                return ODONTOLOGO;
            case ID_ORAL:
                return ORAL;
        }
        return NA;
    }

    @Override
    public String toString() {
        return "AuGrupoTecnologia{" + "id=" + id + ", tipoTecnologia=" + tipoTecnologia + ", maTecnologiaId=" + maTecnologiaId + ", maTecnologiaCodigo=" + maTecnologiaCodigo + ", maTecnologiasValor=" + maTecnologiasValor + ", tipo=" + tipo + ", nivelComplejidad=" + nivelComplejidad + ", auGrupo=" + auGrupo + '}';
    }

}
