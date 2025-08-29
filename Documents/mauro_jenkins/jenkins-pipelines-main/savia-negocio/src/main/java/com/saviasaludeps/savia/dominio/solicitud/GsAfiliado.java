/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saviasaludeps.savia.dominio.solicitud;

import com.saviasaludeps.savia.dominio.administracion.Ubicacion;
import com.saviasaludeps.savia.dominio.generico.Auditoria;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jramirez
 */
public class GsAfiliado extends Auditoria {

    private Integer id;
    private String documentoTipo;
    private String documentoNumero;
    private Date documentoFechaExpedicion;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private Date fechaNacimiento;
    private String sexo;
    private String residenciaDireccion;
    private String residenciaObservacion;
    private String residenciaZonaTipo;
    private Ubicacion residenciaUbicacion;
    private Ubicacion atencionUbicacion;
    private String departamentoResidencia;

    private List<GsSolicitud> listaGsSolicitudes;

    private String viaDireccion;
    private String numeroDireccion;
    private String orden1Direccion;
    private String orden2Direccion;
    private String orientacionDireccion;
    private String placaDireccion;
    private String descripcionDireccion;
    private String observacionDireccion;

    private String atencionUbicacionNombre;
    private String residenciaUbicacionNombre;

    public static final String ZONA_URBANA = "Urbana";
    public static final String ZONA_RURAL = "Rural";

    public GsAfiliado() {
    }

    public GsAfiliado(Integer id) {
        this.id = id;
    }

    public GsAfiliado(Integer id, String documentoTipo, String documentoNumero, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido) {
        this.id = id;
        this.documentoTipo = documentoTipo;
        this.documentoNumero = documentoNumero;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumentoTipo() {
        return documentoTipo;
    }

    public void setDocumentoTipo(String documentoTipo) {
        this.documentoTipo = documentoTipo;
    }

    public String getDocumentoNumero() {
        return documentoNumero;
    }

    public void setDocumentoNumero(String documentoNumero) {
        this.documentoNumero = documentoNumero;
    }

    public Date getDocumentoFechaExpedicion() {
        return documentoFechaExpedicion;
    }

    public void setDocumentoFechaExpedicion(Date documentoFechaExpedicion) {
        this.documentoFechaExpedicion = documentoFechaExpedicion;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public static Map<String, String> getTipoZonas() {
        Map<String, String> tipos = new LinkedHashMap<>();
        tipos.put(GsAfiliado.getZonaStr(ZONA_URBANA), GsAfiliado.ZONA_URBANA);
        tipos.put(GsAfiliado.getZonaStr(ZONA_RURAL), GsAfiliado.ZONA_RURAL);
        return tipos;
    }

    public static String getZonaStr(String tipoEstado) {
        String str;
        switch (tipoEstado) {
            case ZONA_URBANA:
                str = "Urbana";
                break;
            case ZONA_RURAL:
                str = "Rural";
                break;
            default:
                str = "Ninguno";
                break;
        }
        return str;
    }

    public String getResidenciaDireccionGenerar() {
        StringBuilder strBuffer = new StringBuilder();
        strBuffer.append(this.getViaDireccion());
        strBuffer.append(" ");
        strBuffer.append(this.getNumeroDireccion());
        strBuffer.append(" ");
        strBuffer.append(this.getOrden1Direccion());
        strBuffer.append(" ");
        strBuffer.append(this.getOrientacionDireccion());
        strBuffer.append(" ");
        strBuffer.append(this.getPlacaDireccion());
        strBuffer.append(" ");
        strBuffer.append(this.getOrden2Direccion());
        strBuffer.append(" ");
        strBuffer.append(this.getDescripcionDireccion());
        strBuffer.append(" ");
        strBuffer.append(this.getObservacionDireccion());
        strBuffer.append(" ");
        return strBuffer.toString();
    }

    public String getObservacionDireccion() {
        if (observacionDireccion == null) {
            observacionDireccion = "";
        }
        return observacionDireccion;
    }

    public void setObservacionDireccion(String observacionDireccion) {
        this.observacionDireccion = observacionDireccion;
    }

    public String getResidenciaDireccion() {
        return residenciaDireccion;
    }

    public void setResidenciaDireccion(String residenciaDireccion) {
        this.residenciaDireccion = residenciaDireccion;
    }

    public String getResidenciaObservacion() {
        return residenciaObservacion;
    }

    public void setResidenciaObservacion(String residenciaObservacion) {
        this.residenciaObservacion = residenciaObservacion;
    }

    public String getResidenciaZonaTipo() {
        return residenciaZonaTipo;
    }

    public void setResidenciaZonaTipo(String residenciaZonaTipo) {
        this.residenciaZonaTipo = residenciaZonaTipo;
    }

    public List<GsSolicitud> getListaGsSolicitudes() {
        return listaGsSolicitudes;
    }

    public void setListaGsSolicitudes(List<GsSolicitud> listaGsSolicitudes) {
        this.listaGsSolicitudes = listaGsSolicitudes;
    }

    public Ubicacion getResidenciaUbicacion() {
        return residenciaUbicacion;
    }

    public void setResidenciaUbicacion(Ubicacion residenciaUbicacion) {
        this.residenciaUbicacion = residenciaUbicacion;
    }

    public String getDepartamentoResidencia() {
        return departamentoResidencia;
    }

    public void setDepartamentoResidencia(String departamentoResidencia) {
        this.departamentoResidencia = departamentoResidencia;
    }

    public String getViaDireccion() {
        if (viaDireccion == null) {
            viaDireccion = "";
        }
        return viaDireccion;
    }

    public void setViaDireccion(String viaDireccion) {
        this.viaDireccion = viaDireccion;
    }

    public String getNumeroDireccion() {
        if (numeroDireccion == null) {
            numeroDireccion = "";
        }
        return numeroDireccion;
    }

    public void setNumeroDireccion(String numeroDireccion) {
        this.numeroDireccion = numeroDireccion;
    }

    public String getPlacaDireccion() {
        if (placaDireccion == null) {
            placaDireccion = "";
        }
        return placaDireccion;
    }

    public void setPlacaDireccion(String placaDireccion) {
        this.placaDireccion = placaDireccion;
    }

    public String getDescripcionDireccion() {
        if (descripcionDireccion == null) {
            descripcionDireccion = "";
        }
        return descripcionDireccion;
    }

    public void setDescripcionDireccion(String descripcionDireccion) {
        this.descripcionDireccion = descripcionDireccion;
    }

    public String getOrden1Direccion() {
        if (orden1Direccion == null) {
            orden1Direccion = "";
        }
        return orden1Direccion;
    }

    public void setOrden1Direccion(String orden1Direccion) {
        this.orden1Direccion = orden1Direccion;
    }

    public String getOrientacionDireccion() {
        if (orientacionDireccion == null) {
            orientacionDireccion = "";
        }
        return orientacionDireccion;
    }

    public void setOrientacionDireccion(String orientacionDireccion) {
        this.orientacionDireccion = orientacionDireccion;
    }

    public String getOrden2Direccion() {
        if (orden2Direccion == null) {
            orden2Direccion = "";
        }
        return orden2Direccion;
    }

    public void setOrden2Direccion(String orden2Direccion) {
        this.orden2Direccion = orden2Direccion;
    }

    public Ubicacion getAtencionUbicacion() {
        return atencionUbicacion;
    }

    public void setAtencionUbicacion(Ubicacion atencionUbicacion) {
        this.atencionUbicacion = atencionUbicacion;
    }

    public String getAtencionUbicacionNombre() {
        return atencionUbicacionNombre;
    }

    public void setAtencionUbicacionNombre(String atencionUbicacionNombre) {
        this.atencionUbicacionNombre = atencionUbicacionNombre;
    }

    public String getResidenciaUbicacionNombre() {
        return residenciaUbicacionNombre;
    }

    public void setResidenciaUbicacionNombre(String residenciaUbicacionNombre) {
        this.residenciaUbicacionNombre = residenciaUbicacionNombre;
    }

    public String getNombres() {
        return ((primerNombre == null || primerNombre.equals("")) ? "" : primerNombre) + ((segundoNombre == null || segundoNombre.equals("")) ? "" : " " + segundoNombre);
    }

    public String getApellidos() {
        return ((primerApellido == null || primerApellido.equals("")) ? "" : primerApellido) + ((segundoApellido == null || segundoApellido.equals("")) ? "" : " " + segundoApellido);
    }

    public String getDatosAuditoria() {
        String str = "";
        str += (primerNombre == null) ? "" : primerNombre;
        str += (segundoNombre == null) ? "" : " " + segundoNombre;
        str += (primerApellido == null) ? "" : " " + primerApellido;
        str += (segundoApellido == null) ? "" : " " + segundoApellido;
        str += (documentoTipo == null) ? "" : " " + documentoTipo;
        str += (documentoNumero == null) ? "" : " " + documentoNumero + " ";
        return str;
    }

    @Override
    public String toString() {
        return "GsAfiliado{" + "id=" + id + ", documentoTipo=" + documentoTipo + ", documentoNumero=" + documentoNumero + ", documentoFechaExpedicion=" + documentoFechaExpedicion + ", primerNombre=" + primerNombre + ", segundoNombre=" + segundoNombre + ", primerApellido=" + primerApellido + ", segundoApellido=" + segundoApellido + ", fechaNacimiento=" + fechaNacimiento + ", sexo=" + sexo + ", residenciaDireccion=" + residenciaDireccion + ", residenciaObservacion=" + residenciaObservacion + ", residenciaZonaTipo=" + residenciaZonaTipo + ", listaGsSolicitudes=" + listaGsSolicitudes + ", residenciaUbicacion=" + residenciaUbicacion + '}';
    }
}
