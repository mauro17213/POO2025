package com.saviasaludeps.savia.dominio.autorizacion;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author NEXOS
 */
public class AuNoSolicitudDiagnostico extends Auditoria {

    private Integer id;
    private AuNoSolicitud auNoSolicitudesId;
    private boolean principal;
    private Integer maDiagnosticosId;
    private String maDiagnosticosCodigo;
    private String maDiagnosticosValor;
    private boolean altoCosto;

    //variables auxiliares
    private int posicion;

    public AuNoSolicitudDiagnostico() {
    }

    public AuNoSolicitudDiagnostico(AuNoSolicitud auNoSolicitudesId, boolean principal, Integer maDiagnosticosId, String maDiagnosticosCodigo, String maDiagnosticosValor) {
        this.auNoSolicitudesId = auNoSolicitudesId;
        this.principal = principal;
        this.maDiagnosticosId = maDiagnosticosId;
        this.maDiagnosticosCodigo = maDiagnosticosCodigo;
        this.maDiagnosticosValor = maDiagnosticosValor;
    }

    public AuNoSolicitudDiagnostico(Integer id) {
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

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public int getMaDiagnosticosId() {
        return maDiagnosticosId;
    }

    public void setMaDiagnosticosId(Integer maDiagnosticosId) {
        this.maDiagnosticosId = maDiagnosticosId;
    }

    public String getMaDiagnosticosCodigo() {
        return maDiagnosticosCodigo;
    }

    public void setMaDiagnosticosCodigo(String maDiagnosticosCodigo) {
        this.maDiagnosticosCodigo = maDiagnosticosCodigo;
    }

    public String getMaDiagnosticosValor() {
        return maDiagnosticosValor;
    }

    public void setMaDiagnosticosValor(String maDiagnosticosValor) {
        this.maDiagnosticosValor = maDiagnosticosValor;
    }

    public boolean isAltoCosto() {
        return altoCosto;
    }

    public void setAltoCosto(boolean altoCosto) {
        this.altoCosto = altoCosto;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    // metodos
    public String getPrincipalStr() {
        String principalStr = "No";
        if (principal) {
            principalStr = "Si";
        }
        return principalStr;
    }

    public String getAltoCostoStr() {
        String altoCostoStr = "No";
        if (altoCosto) {
            altoCostoStr = "Si";
        }
        return altoCostoStr;
    }

    @Override
    public String toString() {
        return "AuNoSolicitudDiagnostico{" + "id=" + id + ", auNoSolicitudesId=" + auNoSolicitudesId + ", principal=" + principal + ", maDiagnosticosId=" + maDiagnosticosId + ", maDiagnosticosCodigo=" + maDiagnosticosCodigo + ", maDiagnosticosValor=" + maDiagnosticosValor + '}';
    }

}
