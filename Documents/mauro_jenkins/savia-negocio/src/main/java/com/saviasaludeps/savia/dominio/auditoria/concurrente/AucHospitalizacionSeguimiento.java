/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.auditoria.concurrente;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestador;
import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author sgiraldov
 */
public class AucHospitalizacionSeguimiento extends Auditoria {

    public static final String DESCRIPCION_NO_APTO_RESCATE = "NO APTO PARA RESCATE | ";
    public static final String DESCRIPCION_SE_DESMARCA_NO_RESCATE = "SE DESMARCA EL NO APTO Y SE ENVIA A RESCATE | ";
    public static final int TAMANIO_DESCRIPCION = 65;
    public static final int ORIGEN_MANUAL = 1;
    public static final int ORIGEN_CARGA_MASIVA = 2;

    public static final int AUC_SEGUIMIENTO = 342;
    public static final int AUC_SEGUIMIENTO_GESTORES = 357;
    public static final int AUC_SEGUIMIENTO_ESTADO = 358;

    public static final String MANUAL = "Manual";
    public static final String CARGA_MASIVA = "Carga Masiva";

    private Integer id;
    private int maeTipoSeguimientoId;
    private String maeTipoSeguimientoCodigo;
    private String maeTipoSeguimientoValor;
    private String maeTipoSeguimientoTipo;
    private Integer maeTipoGestionId;
    private String maeTipoGestionCodigo;
    private String maeTipoGestionValor;
    private String maeTipoGestionTipo;
    private Integer maeTipoGestionEstadoId;
    private String maeTipoGestionEstadoCodigo;
    private String maeTipoGestionEstadoValor;
    private String maeTipoGestionEstadoTipo;
    private CntPrestador cntPrestadoresId;
    private CntPrestadorSede cntPrestadorSedesId;

    private Boolean borrado;
    private String borradoObservacion;
    private String usuarioBorra;
    private String terminalBorra;
    private Date fechaHoraBorra;
    private String descripcion;
    private String descripcionCorto;
    private Date fechaCierreGestion;
    private AucHospitalizacion aucHospitalizacionId;
    private Integer maeDestinoId;
    private String maeDestinoCodigo;
    private String maeDestinoValor;
    private String maeDestinoTipo;
    
    private int pos;
    //2023-09-01 jyperez nuevos campos
    private int origen;
    //variables adicionales
    private boolean habilitarCampoIpsReceptora;
    private boolean habilitarCampoFechaCierreGestion;

    public AucHospitalizacionSeguimiento() {
    }

    public AucHospitalizacionSeguimiento(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeTipoSeguimientoId() {
        return maeTipoSeguimientoId;
    }

    public void setMaeTipoSeguimientoId(int maeTipoSeguimientoId) {
        this.maeTipoSeguimientoId = maeTipoSeguimientoId;
    }

    public String getMaeTipoSeguimientoCodigo() {
        return maeTipoSeguimientoCodigo;
    }

    public void setMaeTipoSeguimientoCodigo(String maeTipoSeguimientoCodigo) {
        this.maeTipoSeguimientoCodigo = maeTipoSeguimientoCodigo;
    }

    public String getMaeTipoSeguimientoValor() {
        return maeTipoSeguimientoValor;
    }

    public String getMaeTipoSeguimientoTipo() {
        return maeTipoSeguimientoTipo;
    }

    public void setMaeTipoSeguimientoTipo(String maeTipoSeguimientoTipo) {
        this.maeTipoSeguimientoTipo = maeTipoSeguimientoTipo;
    }

    public void setMaeTipoSeguimientoValor(String maeTipoSeguimientoValor) {
        this.maeTipoSeguimientoValor = maeTipoSeguimientoValor;
    }

    public Integer getMaeTipoGestionId() {
        return maeTipoGestionId;
    }

    public void setMaeTipoGestionId(Integer maeTipoGestionId) {
        this.maeTipoGestionId = maeTipoGestionId;
    }

    public String getMaeTipoGestionCodigo() {
        return maeTipoGestionCodigo;
    }

    public void setMaeTipoGestionCodigo(String maeTipoGestionCodigo) {
        this.maeTipoGestionCodigo = maeTipoGestionCodigo;
    }

    public String getMaeTipoGestionValor() {
        return maeTipoGestionValor;
    }

    public void setMaeTipoGestionValor(String maeTipoGestionValor) {
        this.maeTipoGestionValor = maeTipoGestionValor;
    }

    public String getMaeTipoGestionTipo() {
        return maeTipoGestionTipo;
    }

    public void setMaeTipoGestionTipo(String maeTipoGestionTipo) {
        this.maeTipoGestionTipo = maeTipoGestionTipo;
    }

    public Integer getMaeTipoGestionEstadoId() {
        return maeTipoGestionEstadoId;
    }

    public void setMaeTipoGestionEstadoId(Integer maeTipoGestionEstadoId) {
        this.maeTipoGestionEstadoId = maeTipoGestionEstadoId;
    }

    public String getMaeTipoGestionEstadoCodigo() {
        return maeTipoGestionEstadoCodigo;
    }

    public void setMaeTipoGestionEstadoCodigo(String maeTipoGestionEstadoCodigo) {
        this.maeTipoGestionEstadoCodigo = maeTipoGestionEstadoCodigo;
    }

    public String getMaeTipoGestionEstadoValor() {
        return maeTipoGestionEstadoValor;
    }

    public void setMaeTipoGestionEstadoValor(String maeTipoGestionEstadoValor) {
        this.maeTipoGestionEstadoValor = maeTipoGestionEstadoValor;
    }

    public String getMaeTipoGestionEstadoTipo() {
        return maeTipoGestionEstadoTipo;
    }

    public void setMaeTipoGestionEstadoTipo(String maeTipoGestionEstadoTipo) {
        this.maeTipoGestionEstadoTipo = maeTipoGestionEstadoTipo;
    }

    public CntPrestador getCntPrestadoresId() {
        return cntPrestadoresId;
    }

    public void setCntPrestadoresId(CntPrestador cntPrestadoresId) {
        this.cntPrestadoresId = cntPrestadoresId;
    }

    public CntPrestadorSede getCntPrestadorSedesId() {
        return cntPrestadorSedesId;
    }

    public void setCntPrestadorSedesId(CntPrestadorSede cntPrestadorSedesId) {
        this.cntPrestadorSedesId = cntPrestadorSedesId;
    }

    public Boolean getBorrado() {
        return borrado;
    }

    public void setBorrado(Boolean borrado) {
        this.borrado = borrado;
    }

    public String getBorradoObservacion() {
        return borradoObservacion;
    }

    public void setBorradoObservacion(String borradoObservacion) {
        this.borradoObservacion = borradoObservacion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCierreGestion() {
        return fechaCierreGestion;
    }

    public void setFechaCierreGestion(Date fechaCierreGestion) {
        this.fechaCierreGestion = fechaCierreGestion;
    }

    public String getDescripcionCorto() {
        if (getDescripcion() != null) {
            descripcionCorto = getDescripcion();
            if (getDescripcion().length() >= TAMANIO_DESCRIPCION) {
                return descripcionCorto.substring(0, TAMANIO_DESCRIPCION) + "..";
            } else {
                return descripcionCorto;
            }
        }
        return descripcionCorto;
    }

    public void setDescripcionCorto(String descripcionCorto) {
        this.descripcionCorto = descripcionCorto;
    }

    public AucHospitalizacion getAucHospitalizacionId() {
        return aucHospitalizacionId;
    }

    public void setAucHospitalizacionId(AucHospitalizacion aucHospitalizacionId) {
        this.aucHospitalizacionId = aucHospitalizacionId;
    }

    public Integer getMaeDestinoId() {
        return maeDestinoId;
    }

    public void setMaeDestinoId(Integer maeDestinoId) {
        this.maeDestinoId = maeDestinoId;
    }

    public String getMaeDestinoCodigo() {
        return maeDestinoCodigo;
    }

    public void setMaeDestinoCodigo(String maeDestinoCodigo) {
        this.maeDestinoCodigo = maeDestinoCodigo;
    }

    public String getMaeDestinoValor() {
        return maeDestinoValor;
    }

    public void setMaeDestinoValor(String maeDestinoValor) {
        this.maeDestinoValor = maeDestinoValor;
    }

    public String getMaeDestinoTipo() {
        return maeDestinoTipo;
    }

    public void setMaeDestinoTipo(String maeDestinoTipo) {
        this.maeDestinoTipo = maeDestinoTipo;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    /**
     * @return the origen
     */
    public int getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public boolean isHabilitarCampoIpsReceptora() {
        return habilitarCampoIpsReceptora;
    }

    public void setHabilitarCampoIpsReceptora(boolean habilitarCampoIpsReceptora) {
        this.habilitarCampoIpsReceptora = habilitarCampoIpsReceptora;
    }

    public boolean isHabilitarCampoFechaCierreGestion() {
        return habilitarCampoFechaCierreGestion;
    }

    public void setHabilitarCampoFechaCierreGestion(boolean habilitarCampoFechaCierreGestion) {
        this.habilitarCampoFechaCierreGestion = habilitarCampoFechaCierreGestion;
    }

    /**
     * retorna nombre de campo origen
     *
     * @return
     */
    public String getOrigenStr() {
        String texto = "";
        switch (origen) {
            case ORIGEN_MANUAL:
                texto = MANUAL;
                break;
            case ORIGEN_CARGA_MASIVA:
                texto = CARGA_MASIVA;
        }
        return texto;
    }

    @Override
    public String toString() {
        return "AucHospitalizacionSeguimiento{" + "id=" + id + ", maeTipoSeguimientoId=" + maeTipoSeguimientoId + ", maeTipoSeguimientoCodigo=" + maeTipoSeguimientoCodigo + ", maeTipoSeguimientoValor=" + maeTipoSeguimientoValor + ", maeTipoSeguimientoTipo=" + maeTipoSeguimientoTipo + ", maeTipoGestionId=" + maeTipoGestionId + ", maeTipoGestionCodigo=" + maeTipoGestionCodigo + ", maeTipoGestionValor=" + maeTipoGestionValor + ", maeTipoGestionTipo=" + maeTipoGestionTipo + ", maeTipoGestionEstadoId=" + maeTipoGestionEstadoId + ", maeTipoGestionEstadoCodigo=" + maeTipoGestionEstadoCodigo + ", maeTipoGestionEstadoValor=" + maeTipoGestionEstadoValor + ", maeTipoGestionEstadoTipo=" + maeTipoGestionEstadoTipo + ", borrado=" + borrado + ", borradoObservacion=" + borradoObservacion + ", usuarioBorra=" + usuarioBorra + ", terminalBorra=" + terminalBorra + ", fechaHoraBorra=" + fechaHoraBorra + ", descripcion=" + descripcion + ", origen=" + origen + '}';
    }
}
