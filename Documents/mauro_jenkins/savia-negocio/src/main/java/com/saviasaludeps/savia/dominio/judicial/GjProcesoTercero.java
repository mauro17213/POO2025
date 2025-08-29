
package com.saviasaludeps.savia.dominio.judicial;

import com.saviasaludeps.savia.dominio.generico.Auditoria;

/**
 *
 * @author bsgomez
 */
public class GjProcesoTercero extends Auditoria {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private int posicion;
    private int maeCalidadActuaId;
    private String maeCalidadActuaValor;
    private String maeCalidadActuaCodigo;
    private Integer maeTipoDocumentoId;
    private Integer maeTipoDocumentorepreId;
    private String maeTipoDocumentoCodigo;
    private String maeTipoDocumentoValor;
    private String documento;
    private String nombres;
    private String apellidos;
    private String razonSocial;
    private String telefono;

    private GjTercero gjTercerosId;

    private GjProceso gjProcesosId;
    private String calidadActuacion;

    public GjProcesoTercero() {
    }

    public GjProcesoTercero(Integer id) {
        this.id = id;
    }

    public GjProcesoTercero(Integer id, short calidadActua, int maeCalidadActuaId, String maeCalidadActuaValor, Integer maeTipoDocumentoId, String maeTipoDocumentoCodigo, String maeTipoDocumentoValor, String documento, String nombres, String apellidos, String razonSocial, String telefono, GjProceso gjProcesosId, GjTercero gjTercerosId) {
        this.id = id;
        this.maeCalidadActuaId = maeCalidadActuaId;
        this.maeCalidadActuaValor = maeCalidadActuaValor;
        this.maeTipoDocumentoId = maeTipoDocumentoId;
        this.maeTipoDocumentoCodigo = maeTipoDocumentoCodigo;
        this.maeTipoDocumentoValor = maeTipoDocumentoValor;
        this.documento = documento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.razonSocial = razonSocial;
        this.telefono = telefono;
        this.gjProcesosId = gjProcesosId;
        this.gjTercerosId = gjTercerosId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getMaeCalidadActuaId() {
        return maeCalidadActuaId;
    }

    public void setMaeCalidadActuaId(int maeCalidadActuaId) {
        this.maeCalidadActuaId = maeCalidadActuaId;
    }

    public String getMaeCalidadActuaValor() {
        return maeCalidadActuaValor;
    }

    public void setMaeCalidadActuaValor(String maeCalidadActuaValor) {
        this.maeCalidadActuaValor = maeCalidadActuaValor;
    }

    public String getMaeCalidadActuaCodigo() {
        return maeCalidadActuaCodigo;
    }

    public void setMaeCalidadActuaCodigo(String maeCalidadActuaCodigo) {
        this.maeCalidadActuaCodigo = maeCalidadActuaCodigo;
    }

    public Integer getMaeTipoDocumentoId() {
        return maeTipoDocumentoId;
    }

    public void setMaeTipoDocumentoId(Integer maeTipoDocumentoId) {
        this.maeTipoDocumentoId = maeTipoDocumentoId;
    }

    public Integer getMaeTipoDocumentorepreId() {
        return maeTipoDocumentorepreId;
    }

    public void setMaeTipoDocumentorepreId(Integer maeTipoDocumentorepreId) {
        this.maeTipoDocumentorepreId = maeTipoDocumentorepreId;
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

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public GjProceso getGjProcesosId() {
        return gjProcesosId;
    }

    public void setGjProcesosId(GjProceso gjProcesosId) {
        this.gjProcesosId = gjProcesosId;
    }

    public void setCalidadActuacion(String calidadActuacion) {
        this.calidadActuacion = calidadActuacion;
    }

    public GjTercero getGjTercerosId() {
        if (gjTercerosId == null) {
            gjTercerosId = new GjTercero();
        }
        return gjTercerosId;
    }

    public void setGjTercerosId(GjTercero gjTercerosId) {
        this.gjTercerosId = gjTercerosId;
    }

    @Override
    public String toString() {
        return "GjProcesoTercero{" + "id=" + id + ", maeCalidadActuaId=" + maeCalidadActuaId + ", maeCalidadActuaValor=" + maeCalidadActuaValor + ", maeCalidadActuaCodigo=" + maeCalidadActuaCodigo + ", maeTipoDocumentoId=" + maeTipoDocumentoId + ", maeTipoDocumentorepreId=" + maeTipoDocumentorepreId + ", maeTipoDocumentoCodigo=" + maeTipoDocumentoCodigo + ", maeTipoDocumentoValor=" + maeTipoDocumentoValor + ", documento=" + documento + ", nombres=" + nombres + ", apellidos=" + apellidos + ", razonSocial=" + razonSocial + ", telefono=" + telefono + ", gjTercerosId=" + gjTercerosId + ", gjProcesosId=" + gjProcesosId + ", calidadActuacion=" + calidadActuacion + '}';
    }

    public void setGjTercerosId(GjProcesoTercero adjunto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
