/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.crue;

import com.saviasaludeps.savia.dominio.contratacion.CntPrestadorSede;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;

/**
 *
 * @author Jaime Andres Olarte
 */
public class RefAnexo9Gestion extends Auditoria {

    public static final int ORIGEN_EPS = 0;
    public static final int ORIGEN_IPS = 1;

    public static final String ESTADO_GESTION_TIPO = "85";
    public static final String ESTADO_PREADMITIDA_CODIGO = "01";
    public static final String ESTADO_ADMITIDA_CODIGO = "02";
    public static final String ESTADO_ANULADA_CODIGO = "05";
    public static final String ESTADO_CANCELADA_CODIGO = "06";
    public static final String ESTADO_CERRADA_CODIGO = "13";
    public static final String ESTADO_DIRECCIONA_CODIGO = "07";
    public static final String ESTADO_RECHAZADA_CODIGO = "04";
    public static final String ESTADO_GESTION_DE_REGULACION_CODIGO = "14";
    public static final String ESTADO_AUDITORIA_CODIGO = "15";
    public static final String ESTADO_PERTINENCIA_MEDICA_CODIGO = "16";
    public static final String ESTADO_GESTION_NOTA_CODIGO = "11";
    public static final String ESTADO_GESTION_TELEASISTENCIA_CODIGO = "17";
    public static final String ESTADO_CONCEPTO_TELEASISTENCIA_CODIGO = "18";
    public static final String ESTADO_CAMA_FIJA = "19";
    
    public static final String ESTADO_MOTIVO_REMITIDO = "38";
    
    public final static short TAMANIO_OBSERVACION = 45;

    private Integer id;
    private int origen;
    private int maeTipoId;
    private String maeTipoCodigo;
    private String maeTipoValor;
    private Integer maeMotivoId;
    private String maeMotivoCodigo;
    private String maeMotivoValor;
    private String observacion;
    private String contactoNombre;
    private String contactoTelefono;
    private Date fechaHoraAceptacion;
    private Date fechaHoraEgreso;
    private CntPrestadorSede cntPrestadorSede;
    private RefAnexo9 refAnexo9;
    //aux
    private String observacionCorto;
    private String prestadorSede;
    
    public RefAnexo9Gestion() {
    }

    public RefAnexo9Gestion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RefAnexo9 getRefAnexo9() {
        return refAnexo9;
    }

    public void setRefAnexo9(RefAnexo9 refAnexo9) {
        this.refAnexo9 = refAnexo9;
    }

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public int getMaeTipoId() {
        return maeTipoId;
    }

    public void setMaeTipoId(int maeTipoId) {
        this.maeTipoId = maeTipoId;
    }

    public String getMaeTipoCodigo() {
        return maeTipoCodigo;
    }

    public void setMaeTipoCodigo(String maeTipoCodigo) {
        this.maeTipoCodigo = maeTipoCodigo;
    }

    public String getMaeTipoValor() {
        return maeTipoValor;
    }

    public void setMaeTipoValor(String maeTipoValor) {
        this.maeTipoValor = maeTipoValor;
    }

    public Integer getMaeMotivoId() {
        return maeMotivoId;
    }

    public void setMaeMotivoId(Integer maeMotivoId) {
        this.maeMotivoId = maeMotivoId;
    }

    public String getMaeMotivoCodigo() {
        return maeMotivoCodigo;
    }

    public void setMaeMotivoCodigo(String maeMotivoCodigo) {
        this.maeMotivoCodigo = maeMotivoCodigo;
    }

    public String getMaeMotivoValor() {
        return maeMotivoValor;
    }

    public void setMaeMotivoValor(String maeMotivoValor) {
        this.maeMotivoValor = maeMotivoValor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getContactoNombre() {
        return contactoNombre;
    }

    public void setContactoNombre(String contactoNombre) {
        this.contactoNombre = contactoNombre;
    }

    public String getContactoTelefono() {
        return contactoTelefono;
    }

    public void setContactoTelefono(String contactoTelefono) {
        this.contactoTelefono = contactoTelefono;
    }

    public CntPrestadorSede getCntPrestadorSede() {
        return cntPrestadorSede;
    }

    public void setCntPrestadorSede(CntPrestadorSede cntPrestadorSede) {
        this.cntPrestadorSede = cntPrestadorSede;
    }

    public Date getFechaHoraAceptacion() {
        return fechaHoraAceptacion;
    }

    public void setFechaHoraAceptacion(Date fechaHoraAceptacion) {
        this.fechaHoraAceptacion = fechaHoraAceptacion;
    }

    public Date getFechaHoraEgreso() {
        return fechaHoraEgreso;
    }

    public void setFechaHoraEgreso(Date fechaHoraEgreso) {
        this.fechaHoraEgreso = fechaHoraEgreso;
    }

    public String getPrestadorSede() {
        return prestadorSede;
    }

    public void setPrestadorSede(String prestadorSede) {
        this.prestadorSede = prestadorSede;
    }

    public String observacionCorto() {
        if (observacion != null) {
            observacionCorto = observacion;
            if (observacion.length() >= TAMANIO_OBSERVACION) {
                return observacionCorto.substring(0, TAMANIO_OBSERVACION) + "..";
            } else {
                return observacionCorto;
            }
        }
        return observacionCorto;
    }

    @Override
    public String toString() {
        return "RefAnexo9Gestion{" + "id=" + id + ", origen=" + origen + ", maeTipoId=" + maeTipoId + ", maeTipoCodigo=" + maeTipoCodigo + ", maeTipoCodigo=" + maeTipoCodigo
                + ", maeTipoValor=" + maeTipoValor + ", maeMotivoId=" + maeMotivoId + ", maeMotivoCodigo=" + maeMotivoCodigo + ", maeMotivoValor=" + maeMotivoValor
                + ", observacion=" + observacion + ", contactoNombre=" + contactoNombre + '}';
    }
}
