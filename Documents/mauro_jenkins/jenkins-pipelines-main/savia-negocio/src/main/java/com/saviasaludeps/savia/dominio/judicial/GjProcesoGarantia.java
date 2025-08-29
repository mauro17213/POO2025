package com.saviasaludeps.savia.dominio.judicial;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author BStevenG
 */
public class GjProcesoGarantia extends Auditoria {

    private Integer id;
    private int maeTipoDocumentoId;
    private String maeTipoDocumentoCodigo;
    private String maeTipoDocumentoValor;
    private String documento;
    private String nombres;
    private String apellidos;
    private GjProcesoHistorico gjProcesoHistoricosId;
    private GjProceso gjProcesosId;

    public GjProcesoGarantia() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    public void setMaeTipoDocumentoId(int maeTipoDocumentoId) {
        this.maeTipoDocumentoId = maeTipoDocumentoId;
    }

    public String getMaeTipoDocumentoCodigo() {
        return maeTipoDocumentoCodigo;
    }

    public void setMaeTipoDocumentoCodigo(String maeTipoDocumentoCodigo) {
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
    }

    public String getMaeTipoDocumentoValor() {
        return maeTipoDocumentoValor;
    }

    public void setMaeTipoDocumentoValor(String maeTipoDocumentoValor) {
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public GjProcesoHistorico getGjProcesoHistoricosId() {
        if (gjProcesoHistoricosId == null) {
            gjProcesoHistoricosId = new GjProcesoHistorico();
        }
        return gjProcesoHistoricosId;
    }

    public void setGjProcesoHistoricosId(GjProcesoHistorico gjProcesoHistoricosId) {
        this.gjProcesoHistoricosId = gjProcesoHistoricosId;
    }

    public GjProceso getGjProcesosId() {
        if (gjProcesosId == null) {
            gjProcesosId = new GjProceso();
        }
        return gjProcesosId;
    }

    public void setGjProcesosId(GjProceso gjProcesosId) {
        this.gjProcesosId = gjProcesosId;
    }

    @Override
    public String toString() {
        return "GjProcesoGarantia{" + "id=" + id + ", maeTipoDocumentoId=" + maeTipoDocumentoId + ", maeTipoDocumentoCodigo=" + maeTipoDocumentoCodigo + ", maeTipoDocumentoValor=" + maeTipoDocumentoValor + ", documento=" + documento + ", nombres=" + nombres + ", apellidos=" + apellidos + ", gjProcesoHistoricosId=" + gjProcesoHistoricosId + ", gjProcesosId=" + gjProcesosId + '}';
    }

}
